package ru.javawebinar.topjava.repository.jdbc;

import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.model.Meal;

@Repository
@Profile(Profiles.POSTGRES_DB)
public class JdbcMealRepositoryForPostgre extends JdbcMealRepository {

    public JdbcMealRepositoryForPostgre(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(jdbcTemplate, namedParameterJdbcTemplate);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected <T> T getDateTimeDependsOnImpl(Meal meal) {
        return (T) meal.getDateTime();
    }
}
