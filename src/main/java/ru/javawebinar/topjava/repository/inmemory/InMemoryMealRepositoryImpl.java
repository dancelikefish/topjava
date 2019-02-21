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
        Map<Integer, Meal> gottenMap = mealRepository.computeIfAbsent(userId, key -> new ConcurrentHashMap<>());
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            gottenMap.put(meal.getId(), meal);
            return meal;
        } else {
            return gottenMap.computeIfPresent(meal.getId(), (mealId, oldMeal) -> meal);
        }
    }

    @Override
    public boolean delete(Integer userId, int id) {
        log.info("delete meal {}", id);
        Map<Integer, Meal> gottenMap = mealRepository.get(userId);
        return gottenMap != null && gottenMap.remove(id) != null;
    }

    @Override
    public Meal get(Integer userId, int id) {
        log.info("get meal {}", id);
        Map<Integer, Meal> gottenMap = mealRepository.get(userId);
        return gottenMap == null ? null : gottenMap.get(id);
    }

    @Override
    public List<Meal> getAll(Integer userId) {
        log.info("getAll user's meals {}", userId);
        Map<Integer, Meal> meals = mealRepository.get(userId);
        return meals != null ? new ArrayList<>(mealRepository.get(userId).values())
                .stream()
                .sorted(Comparator.comparing(Meal::getDateTime)
                        .reversed())
                .collect(Collectors.toList()) : Collections.emptyList();
    }

    @Override
    public List<Meal> getBetween(Integer userId, LocalDate startDate, LocalDate endDate) {
        log.info("getAll user's meals with filter {}", userId);
        Objects.requireNonNull(startDate);
        Objects.requireNonNull(endDate);
        return getAll(userId).stream()
                .filter(meal -> DateTimeUtil.isBetweenDateOrTime(meal.getDate(), startDate, endDate))
                .sorted(Comparator.comparing(Meal::getDateTime)
                        .reversed())
                .collect(Collectors.toList());
    }
}

