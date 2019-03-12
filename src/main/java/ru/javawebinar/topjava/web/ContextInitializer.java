package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import ru.javawebinar.topjava.Profiles;

import java.util.Arrays;

public class ContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    private static final Logger logger = LoggerFactory.getLogger(ContextInitializer.class);

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        ConfigurableEnvironment configurableEnvironment = applicationContext.getEnvironment();
        configurableEnvironment.addActiveProfile(Profiles.DATAJPA);
        configurableEnvironment.addActiveProfile(Profiles.POSTGRES_DB);
        logger.info("Profiles are setted: " + Arrays.toString(configurableEnvironment.getActiveProfiles()));
    }
}
