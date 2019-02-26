package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final int MEAL_ID = START_SEQ + 2;

    public static final Meal NULL_MEAL = new Meal(LocalDateTime.of(2010, 5, 5, 0, 0, 0)
            , "null meal", 400);
    public static final Meal MEAL1 = new Meal(MEAL_ID, LocalDateTime.of(2011, 5, 5, 0, 0, 0)
            , "test meal1", 400);
    public static final Meal MEAL2 = new Meal(MEAL_ID + 1, LocalDateTime.of(2012, 5, 5, 0, 0, 0)
            , "test meal2", 500);
    public static final Meal MEAL3 = new Meal(MEAL_ID + 2, LocalDateTime.of(2013, 5, 5, 0, 0, 0)
            , "test meal3", 600);
    public static final Meal MEAL4 = new Meal(MEAL_ID + 3, LocalDateTime.of(2014, 5, 5, 0, 0, 0)
            , "test meal4", 700);

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).usingDefaultComparator().isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingDefaultComparator().isEqualTo(expected);
    }
}
