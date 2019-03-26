package ru.javawebinar.topjava.web.meal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalDate;
import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalTime;

@Controller
@RequestMapping(value = "/meals")
public class JspMealController extends AbstractMealController {

    @GetMapping
    public String meals(Model model) {
        model.addAttribute("meals", getAll());
        return "meals";
    }

    @GetMapping(value = "/create")
    public ModelAndView create(Model model, @RequestParam(value = "id", required = false) String id) {
        Meal meal = StringUtils.isEmpty(id) ? new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000)
                : get(Integer.parseInt(id));
        model.addAttribute("meal", meal);
        return new ModelAndView("mealForm");
    }

    @GetMapping(value = "/delete")
    public String delete(@RequestParam(value = "id") String id) {
        delete(Integer.parseInt(id));
        return "redirect:/meals";
    }

    @PostMapping(value = "/create")
    public String save(Model model
            , @RequestParam("dateTime") String dateTime
            , @RequestParam("description") String description
            , @RequestParam("calories") String calories
            , @RequestParam(value = "id", required = false) String id) {

        Meal meal = new Meal(
                LocalDateTime.parse(dateTime), description, Integer.parseInt(calories));

        if (StringUtils.isEmpty(id)) {
            create(meal);
        } else {
            update(meal, Integer.parseInt(id));
        }
        model.addAttribute("meals", getAll());
        return "meals";
    }

    @PostMapping(value = "/filter")
    public String filterMeals(Model model
            , @RequestParam("startDate") String startDate
            , @RequestParam("endDate") String endDate
            , @RequestParam("startTime") String startTime
            , @RequestParam("endTime") String endTime) {

        LocalDate sDate = parseLocalDate(startDate);
        LocalDate eDate = parseLocalDate(endDate);
        LocalTime sTime = parseLocalTime(startTime);
        LocalTime eTime = parseLocalTime(endTime);
        model.addAttribute("meals", getBetween(sDate, sTime, eDate, eTime));
        return "meals";
    }
}

