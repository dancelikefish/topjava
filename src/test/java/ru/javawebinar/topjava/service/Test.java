package ru.javawebinar.topjava.service;

import org.junit.AfterClass;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.rules.Stopwatch;
import org.junit.runner.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public abstract class Test {

    static {
        SLF4JBridgeHandler.install();
    }

    protected static final Logger logger = LoggerFactory.getLogger(UserServiceTest.class.getName());
    protected static final List<String> logInfo = new ArrayList<>();

    protected static void logInfo(Description description, long nanos) {
        String testName = description.getMethodName();
        logInfo.add((String.format("Test %s %s, spent %d microseconds",
                testName, "finished", TimeUnit.NANOSECONDS.toMillis(nanos))));
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Rule
    public Stopwatch stopwatch = new Stopwatch() {
        @Override
        protected void finished(long nanos, Description description) {
            logInfo(description, nanos);
        }
    };

    @AfterClass
    public static void after() throws Exception {
        logInfo.forEach(logger::info);
    }
}
