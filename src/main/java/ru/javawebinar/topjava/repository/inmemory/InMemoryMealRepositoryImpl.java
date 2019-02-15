package ru.javawebinar.topjava.repository.inmemory;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryMealRepositoryImpl implements MealRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryMealRepositoryImpl.class);
    private Multimap<Integer, Meal> repository = Multimaps.synchronizedMultimap(HashMultimap.create());
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach(MEAL -> save(1, MEAL));
    }

    @Override
    public Meal save(Integer userId, Meal meal) {
        log.info("save user {}", meal);
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            repository.put(userId, meal);
            return meal;
        }
        delete(meal.getId());
        repository.
        return repository.computeIfPresent(userId, (id, oldMeal) -> meal);
    }

    @Override
    public boolean delete(int id) {
        return repository.entries().removeIf(entry -> entry.getValue().getId() == id);
    }

    @Override
    public Meal get(int id) {
        return repository.values().stream().filter(meal -> meal.getId() == id).findFirst().orElse(null);
    }

    @Override
    public List<Meal> getAll(Integer userId) {
        return new ArrayList<>(repository.get(userId));

    }
}

