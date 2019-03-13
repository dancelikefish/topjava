package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class DataJpaMealRepositoryImpl implements MealRepository {

    private static final Sort SORT_DATE_TIME = new Sort(Sort.Direction.DESC, "dateTime");

    @Autowired
    private CrudMealRepository crudRepository;
    @Autowired
    private CrudUserRepository crudUserRepository;


    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public Meal save(Meal meal, int userId) {
        if (!meal.isNew() && get(meal.getId(), userId) == null) {
            return null;
        }
        User user = crudUserRepository.getOne(userId);
        meal.setUser(user);
        return crudRepository.save(meal);
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public boolean delete(int id, int userId) {
        return crudRepository.deleteByIdAndId(id, userId) != 0;
    }

    @Override
    public Meal get(int id, int userId) {
        return crudRepository.findByIdAndId(id, userId).orElse(null);
    }

    @Override
    public List<Meal> getAll(int userId) {
        return crudRepository.findAllById(userId, SORT_DATE_TIME);
    }

    @Override
    public List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return crudRepository.findAllByIdAndDateTimeBetween(userId, startDate, endDate, SORT_DATE_TIME);
    }
}
