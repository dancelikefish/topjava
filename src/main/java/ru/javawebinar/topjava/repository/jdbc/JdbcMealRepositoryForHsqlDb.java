package ru.javawebinar.topjava.repository.jdbc;

import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.model.Meal;

import java.time.format.DateTimeFormatter;

@Repository
@Profile(Profiles.HSQL_DB)
public class JdbcMealRepositoryForHsqlDb extends JdbcMealRepository {

    public JdbcMealRepositoryForHsqlDb(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(jdbcTemplate, namedParameterJdbcTemplate);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected <T> T getDateTimeDependsOnImpl(Meal meal) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-mm-dd hh-mm-ss");
        return (T) meal.getDateTime().format(dtf);
    }
}
