package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@Transactional(readOnly = true)
public interface CrudMealRepository extends JpaRepository<Meal, Integer> {

//    @Transactional
//    @Modifying
//    @Query("DELETE FROM Meal m WHERE m.id=:id AND m.user.id=:userId")
//    int delete(@Param("id") int id, @Param("user_id") int userId);

    @Transactional
    int deleteByIdAndId(int id, int userId);

    @Override
    @Transactional
    Meal save(Meal meal);

    Optional<Meal> findByIdAndId(Integer id, Integer userId);

    List<Meal> findAllById(Integer userId, Sort sort);

    List<Meal> findAllByIdAndDateTimeBetween(Integer userId, LocalDateTime startDate, LocalDateTime endDate, Sort sort);
}
