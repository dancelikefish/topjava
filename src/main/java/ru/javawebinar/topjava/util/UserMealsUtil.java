package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 100),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 100),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 1, 10, 0), "Завтрак", 2000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 5, 11, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 2, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 2, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 2, 20, 0), "Ужин", 1000)

        );
        getFilteredWithExceededStream(mealList, LocalTime.of(0, 0), LocalTime.of(23, 0), 2000)
                .forEach(userMealWithExceed -> System.out.println(userMealWithExceed.toString()));
        System.out.println();
        for (UserMealWithExceed userMealWithExceed : getFilteredWithExceededLoop(mealList, LocalTime.of(0, 0), LocalTime.of(23, 0), 2000)) {
            System.out.println(userMealWithExceed.toString());
        }
    }

    public static List<UserMealWithExceed> getFilteredWithExceededStream(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> sumCaloriesByDay = mealList.stream()
                .collect(Collectors.toMap(UserMeal::getLocalDate, UserMeal::getCalories, (left, right) -> left + right));

        return mealList.stream()
                .filter(userMeal -> TimeUtil.isBetween(userMeal.getLocalTime(), startTime, endTime))
                .map(userMeal -> new UserMealWithExceed(userMeal.getDateTime(), userMeal.getDescription(), userMeal.getCalories(), sumCaloriesByDay.get(userMeal.getLocalDate()) > caloriesPerDay))
                .collect(Collectors.toList());
    }

    public static List<UserMealWithExceed> getFilteredWithExceededLoop(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> sumCaloriesByDay = new HashMap<>();
        for (UserMeal userMeal : mealList) {
            sumCaloriesByDay.merge(userMeal.getLocalDate(), userMeal.getCalories(), (oldValue, newValue) -> oldValue + newValue);
        }
        List<UserMealWithExceed> userMealsWithExceed = new ArrayList<>();
        for (UserMeal userMeal : mealList) {
            if (TimeUtil.isBetween(userMeal.getLocalTime(), startTime, endTime)) {
                userMealsWithExceed.add(new UserMealWithExceed(userMeal.getDateTime(), userMeal.getDescription()
                        , userMeal.getCalories(), sumCaloriesByDay.get(userMeal.getLocalDate()) > caloriesPerDay));
            }
        }
        return userMealsWithExceed;
    }
}
