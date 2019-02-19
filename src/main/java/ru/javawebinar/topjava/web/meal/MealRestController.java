package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;
import static ru.javawebinar.topjava.util.ValidationUtil.assureIdConsistent;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNew;

@Controller
public class MealRestController {
    private static final Logger log = getLogger(MealRestController.class);

    private final MealService mealService;

    @Autowired
    public MealRestController(MealService mealService) {
        this.mealService = mealService;
    }

    public Meal save(Meal meal) {
        log.info("Save {}", meal);
        checkNew(meal);
        return mealService.save(SecurityUtil.getAuthUserId(), meal);
    }

    public void update(Meal meal, int id) {
        log.info("Update meal {}", meal.getId());
        assureIdConsistent(meal, id);
        mealService.update(SecurityUtil.getAuthUserId(), meal);
    }

    public void delete(int id) throws NotFoundException {
        log.info("Delete meal by id {}", id);
        mealService.delete(SecurityUtil.getAuthUserId(), id);
    }

    public Meal get(int id) throws NotFoundException {
        log.info("Get meal by id {}", id);
        return mealService.get(SecurityUtil.getAuthUserId(), id);
    }

    public List<MealTo> getAll() {
        log.info("Get all");
        int userId = SecurityUtil.getAuthUserId();
        return MealsUtil.getWithExcess(mealService.getAll(userId, LocalDate.MIN, LocalDate.MAX), SecurityUtil.authUserCaloriesPerDay());
    }

    public List<MealTo> getAll(LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime) {
        log.info("Get all with filter");
        int userId = SecurityUtil.getAuthUserId();
        startDate = startDate == null ? LocalDate.MIN : startDate;
        endDate = endDate == null ? LocalDate.MAX : endDate;
        startTime = startTime == null ? LocalTime.MIN : startTime;
        endTime = endTime == null ? LocalTime.MAX : endTime;
        return MealsUtil.getFilteredWithExcess(mealService.getAll(userId, startDate, endDate)
                , SecurityUtil.authUserCaloriesPerDay(), startTime, endTime);
    }
}