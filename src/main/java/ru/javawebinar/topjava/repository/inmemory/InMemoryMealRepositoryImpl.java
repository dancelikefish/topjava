package ru.javawebinar.topjava.repository.inmemory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryMealRepositoryImpl.class);
    private Map<Integer, Map<Integer, Meal>> mealRepository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach(MEAL -> save(1, MEAL));
        save(2, new Meal(LocalDateTime.MAX, "test", 1000));
    }

    @Override
    public Meal save(Integer userId, Meal meal) {
        log.info("save meal {}", meal.getId());
        if (meal.isNew() && mealRepository.get(userId) == null) {
            Map<Integer, Meal> userMealMap = new HashMap<>();
            meal.setId(counter.incrementAndGet());
            userMealMap.put(meal.getId(), meal);
            return mealRepository.put(userId, userMealMap) == null ? null : meal;
        } else if (meal.isNew() && mealRepository.get(userId) != null) {
            Map<Integer, Meal> userMealMap = mealRepository.get(userId);
            meal.setId(counter.incrementAndGet());
            userMealMap.put(meal.getId(), meal);
            return mealRepository.computeIfPresent(userId, (mealId, mealMap) -> userMealMap) == null ? null : meal;
        } else {
            return mealRepository.get(userId).computeIfPresent(meal.getId(), (mealId, oldMeal) -> meal);
        }
    }

    @Override
    public boolean delete(Integer userId, int id) {
        log.info("delete meal {}", id);
        if (mealRepository.get(userId).get(id) != null) {
            return mealRepository.get(userId).remove(id) != null;
        }
        return false;
    }

    @Override
    public Meal get(Integer userId, int id) {
        log.info("get meal {}", id);
        return mealRepository.get(userId).get(id);
    }

    @Override
    public List<Meal> getAll(Integer userId, LocalDate startDate, LocalDate endDate) {
        log.info("getAll user's meals {}", userId);
        return new ArrayList<>(mealRepository.get(userId).values())
                .stream()
                .filter(meal -> DateTimeUtil.isBetweenDateOrTime(meal.getDate(), startDate, endDate))
                .sorted(Comparator.comparing(Meal::getDateTime)
                        .reversed())
                .collect(Collectors.toList());

    }
}

