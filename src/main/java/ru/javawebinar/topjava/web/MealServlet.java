package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.storage.MapMealStorage;
import ru.javawebinar.topjava.storage.Storage;
import ru.javawebinar.topjava.util.HtmlUtil;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(UserServlet.class);
    private Storage storage;

    @Override
    public void init() throws ServletException {
        super.init();
        storage = new MapMealStorage();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        int id;

        if (action != null) {
            switch (action) {
                case "delete":
                    id = Integer.parseInt(req.getParameter("id"));
                    storage.delete(id);
                    resp.sendRedirect("meals");
                    return;

                case "add":
                    req.setAttribute("meal", MealsUtil.EMPTY);
                    req.getRequestDispatcher("/editMeal.jsp").forward(req, resp);
                    return;

                case "update":
                    id = Integer.parseInt(req.getParameter("id"));
                    req.setAttribute("meal", storage.get(id));
                    req.getRequestDispatcher("/editMeal.jsp").forward(req, resp);
                    return;
            }
        }
        req.setAttribute("mealsWithExcess", MealsUtil.getFilteredWithExcessInOnePass2(
                storage.getAll(), LocalTime.MIN, LocalTime.MAX, 2000));
        log.debug("send forward() to meals.jsp");
        req.getRequestDispatcher("/meals.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String stringDate = req.getParameter("dateTime");
        LocalDateTime dateTime = LocalDateTime.parse(stringDate, HtmlUtil.DATE_TIME_FORMATTER);
        String description = req.getParameter("description");
        int calories = Integer.parseInt(req.getParameter("calories"));

        String stringId = req.getParameter("id");
        if (stringId.equals("0")) {
            storage.save(new Meal(MapMealStorage.ID_COUNTER.incrementAndGet(), dateTime, description, calories));
        } else {
            int id = Integer.parseInt(req.getParameter("id"));
            storage.update(new Meal(id, dateTime, description, calories));
        }
        resp.sendRedirect("meals");
    }
}



