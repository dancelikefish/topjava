package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.service.UserService;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@Controller
public class MealRestController {
    private static final Logger log = getLogger(MealRestController.class);

    private final UserService userService;
    private final MealService mealService;

    @Autowired
    public MealRestController(MealService mealService, UserService userService) {
        this.mealService = mealService;
        this.userService = userService;
    }

    public Meal save(Meal meal) {
        log.info("Save {}", meal);
        return mealService.save(SecurityUtil.getAuthUserId(), meal);
    }

    public void update(Meal meal) {
        log.info("Update meal {}", meal.getId());
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
        return MealsUtil.getWithExcess(mealService.getAll(userId, LocalDate.MIN, LocalDate.MAX), userService.get(userId).getCaloriesPerDay());
    }

    public List<MealTo> getAll(LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime) {
        log.info("Get all with filter");
        int userId = SecurityUtil.getAuthUserId();
        return MealsUtil.getFilteredWithExcess(mealService.getAll(userId, startDate, endDate)
                , userService.get(userId).getCaloriesPerDay(), startTime, endTime);
    }

}