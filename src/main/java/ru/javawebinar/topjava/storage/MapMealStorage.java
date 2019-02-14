package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.model.Meal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MapMealStorage implements Storage {

    private static final Map<Integer, Meal> meals = new ConcurrentHashMap<>();
    private static final AtomicInteger ID_COUNTER = new AtomicInteger(0);

    @Override
    public Meal save(Meal meal) {
        int id = ID_COUNTER.incrementAndGet();
        return meals.putIfAbsent(id, new Meal(id, meal.getDateTime(), meal.getDescription(), meal.getCalories()));
    }

    @Override
    public Meal get(int id) {
        return meals.get(id);
    }

    @Override
    public Meal update(Meal meal) {
        return meals.replace(meal.getId()
                    , new Meal(meal.getId(), meal.getDateTime(), meal.getDescription(), meal.getCalories()));
    }

    @Override
    public void delete(int id) {
        meals.remove(id);
    }

    @Override
    public List<Meal> getAll() {
        return new ArrayList<>(meals.values());
    }
}
