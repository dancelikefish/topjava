package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;

import java.util.Collection;
import java.util.List;

public interface MealRepository {
    Meal save(Integer userId, Meal meal);

    boolean delete(int id);

    Meal get(int id);

    List<Meal> getAll(Integer userId);
}
