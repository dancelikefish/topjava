package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface CrudMealRepository extends JpaRepository<Meal, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Meal m WHERE m.user.id=:userId AND m.id=:id")
    int deleteByIdAndId(@Param("id") int id, @Param("userId") int userId);

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    Meal save(Meal meal);

    @Query("SELECT m FROM Meal m WHERE m.id=:id AND m.user.id=:userId")
    Optional<Meal> findByIdAndId(@Param("id") Integer id, @Param("userId") Integer userId);

    @Query("SELECT m FROM Meal m WHERE m.user.id=:userId")
    List<Meal> findAllById(@Param("userId") int userId, Sort sort);

    @Query("SELECT m FROM Meal m WHERE m.user.id=:userId AND m.dateTime BETWEEN :startDate AND :endDate")
    List<Meal> findAllByIdAndDateTimeBetween(@Param("userId") Integer userId, @Param("startDate") LocalDateTime startDate
            , @Param("endDate") LocalDateTime endDate, Sort sort);
}
