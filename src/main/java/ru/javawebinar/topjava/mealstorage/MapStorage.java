package ru.javawebinar.topjava.mealstorage;

import ru.javawebinar.topjava.exception.NotExistStorageException;
import ru.javawebinar.topjava.exception.StorageException;
import ru.javawebinar.topjava.model.Meal;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MapStorage implements Storage {

    private static final Map<Integer, Meal> meals = new ConcurrentHashMap<>();
    public static final AtomicInteger idCounter = new AtomicInteger(0);

    @Override
    public void save(Meal meal) {
        if (meal != null && !isValid(meal.getId())) {
            int id = idCounter.incrementAndGet();
            meals.put(id, new Meal(id, meal.getDateTime(), meal.getDescription(), meal.getCalories()));
        } else throw new StorageException("Storage exception", 0);
    }

    @Override
    public Meal get(int id) {
        if (isValid(id)) {
            return meals.get(id);
        } else throw new NotExistStorageException(id);
    }

    @Override
    public void update(Meal meal) {
        if (isValid(meal.getId())) {
            meals.remove(meal.getId());
            meals.put(meal.getId()
                    , new Meal(meal.getId(), meal.getDateTime(), meal.getDescription(), meal.getCalories()));
        } else throw new NotExistStorageException(meal.getId());
    }

    @Override
    public void delete(int id) {
        if (isValid(id)) {
            meals.remove(id);
        } else throw new NotExistStorageException(id);
    }

    @Override
    public List<Meal> getAll() {
        return new ArrayList<>(meals.values());
    }

    private boolean isValid(int id) {
        return meals.containsKey(id);
    }
}
