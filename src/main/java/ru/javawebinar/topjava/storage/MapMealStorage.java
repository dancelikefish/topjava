package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.model.Meal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MapMealStorage implements Storage {

    private static final Map<Integer, Meal> meals = new ConcurrentHashMap<>();
    public static final AtomicInteger ID_COUNTER = new AtomicInteger(0);

    @Override
    public Meal save(Meal meal) {
        if (!meals.containsKey(meal.getId())) {
            int id = ID_COUNTER.incrementAndGet();
            return meals.put(id, new Meal(id, meal.getDateTime(), meal.getDescription(), meal.getCalories()));
        } else return null;
    }

    @Override
    public Meal get(int id) {
        return meals.getOrDefault(id, null);
    }

    @Override
    public Meal update(Meal meal) {
        if (meals.containsKey(meal.getId())) {
            meals.remove(meal.getId());
            return meals.put(meal.getId()
                    , new Meal(meal.getId(), meal.getDateTime(), meal.getDescription(), meal.getCalories()));
        } else return null;
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
