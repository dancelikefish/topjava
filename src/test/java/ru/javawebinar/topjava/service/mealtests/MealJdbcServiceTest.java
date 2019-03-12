package ru.javawebinar.topjava.service.mealtests;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;

@ActiveProfiles(Profiles.JDBC)
public class MealJdbcServiceTest extends MealAbstractServiceTest {
}
