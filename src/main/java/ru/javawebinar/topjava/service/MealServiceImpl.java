package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MealServiceImpl implements MealService {

    private MealRepository repository;

    @Autowired
    public MealServiceImpl(MealRepository repository) {
        this.repository = repository;
    }

    @Override
    public Meal save(Integer userId, Meal meal) {
        return repository.save(userId, meal);
    }

    @Override
    public void update(Integer userId, Meal meal) {
        checkNotFoundWithId(repository.save(userId, meal), meal.getId());
    }

    @Override
    public void delete(Integer userId, int id) throws NotFoundException {
        checkNotFoundWithId(repository.delete(userId, id), id);
    }

    @Override
    public Meal get(Integer userId, int id) throws NotFoundException {
        return checkNotFoundWithId(repository.get(userId, id), id);

    }

    @Override
    public List<Meal> getAll(Integer userId, LocalDate startDate, LocalDate endDate) {
        return repository.getAll(userId, startDate, endDate);
    }
}