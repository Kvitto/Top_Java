package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.storage.MealStorageImp;
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
        storage = new MealStorageImp();
        MealsUtil.populateStorage(storage);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to meals doPost");
        request.setCharacterEncoding("UTF-8");
        Integer id = tryParse(request.getParameter("id"));
        LocalDateTime date = LocalDateTime.parse(request.getParameter("datetime"));
        String description = request.getParameter("description");
        int calories = Integer.parseInt(request.getParameter("calories"));
        Meal meal;
        if (id == null) {
            log.info("Receive new meal");
            meal = new Meal(null, date, description, calories);
            storage.create(meal);
        } else if (id > -1) {
            log.info("Edit meal id={}", id);
            meal = new Meal(id, date, description, calories);
            storage.update(meal);
        } else {
            log.info("Not valid meal id={}", id);
        }
        response.sendRedirect("meals");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to meals doGet");
        String action = request.getParameter("action");
        Integer id = tryParse(request.getParameter("id"));
        if (action != null && (id == null || id > -1)) {
            Meal meal = new Meal(null, LocalDateTime.now(), "", 0);
            if (id != null) {
                switch (action) {
                    case "edit":
                        meal = storage.get(id);
                        break;
                    case "delete": // "delete" always before "default" or add response.sendRedirect("meals");
                        storage.delete(id);
                    default:
                        response.sendRedirect("meals");
                        return;
                }
            }
            request.setAttribute("meal", meal);
            request.getRequestDispatcher("WEB-INF/jsp/mealEdit.jsp").forward(request, response);
            return;
        }
        request.setAttribute("mealsTo", MealsUtil.getMealsTo(storage.getAll(), CALORIES_PER_DAY));
        request.getRequestDispatcher("WEB-INF/jsp/meals.jsp").forward(request, response);
    }

    public static Integer tryParse(String text) {
        if (!"null".equals(text)) {
            try {
                return Integer.parseInt(text);
            } catch (NumberFormatException e) {
                return -1;
            }
        }
        return null;
    }
}
