package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalTime;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(UserServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        req.setAttribute("mealsWithExcess", MealsUtil.getFilteredWithExcessInOnePass2(
                MealsUtil.getMeals(), LocalTime.of(0, 0), LocalTime.of(23, 0), 2000));
        log.debug("send forward() to meals.jsp");
        req.getRequestDispatcher("/meals.jsp").forward(req, resp);
    }
}
