package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.storage.MapResumeStorage;
import ru.javawebinar.topjava.storage.Storage;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final int CALORIES_PER_DAY = 2000;
    private static final Logger log = getLogger(MealServlet.class);
    private static Storage storage;

    @Override
    public void init() throws ServletException {
        storage = new MapResumeStorage();
        MealsUtil.populateStorage(storage);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to meals doPost");
        request.setCharacterEncoding("UTF-8");
        Integer id = parseInteger(request.getParameter("id"));
        LocalDateTime date = LocalDateTime.parse(request.getParameter("datetime"));
        String description = request.getParameter("description");
        int calories = parseInteger(request.getParameter("calories"));
        Meal meal;
        if (id == 0) {
            if (!"".equals(description) && calories != 0) {
                meal = new Meal(MealsUtil.generateId(), date, description, calories);
                storage.save(meal);
            }
        } else {
            meal = new Meal(id, date, description, calories);
            storage.update(meal);
        }
        response.sendRedirect("meals");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to meals doGet");
        String action = request.getParameter("action");
        if (action != null) {
            Integer id = parseInteger(request.getParameter("id"));
            Meal meal;
            switch (action) {
                case "delete":
                    storage.delete(id);
                    response.sendRedirect("meals");
                    return;
                case "edit":
                    if (id > 0) {
                        meal = storage.get(id);
                    } else {
                        meal = new Meal(0, null, null, 0);
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Unknown action: " + action);
            }
            request.setAttribute("meal", meal);
            request.getRequestDispatcher("WEB-INF/jsp/mealEdit.jsp").forward(request, response);
            return;
        }
        request.setAttribute("mealsTo", MealsUtil.getMealsTo(storage.getAllSorted(), CALORIES_PER_DAY));
        request.getRequestDispatcher("WEB-INF/jsp/meals.jsp").forward(request, response);
    }

    private Integer parseInteger(String s) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
