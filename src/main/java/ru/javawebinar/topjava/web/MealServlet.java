package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.storage.Storage;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final int CALORIES_PER_DAY = 2000;
    private static final Logger log = getLogger(MealServlet.class);
    private List<MealTo> mealsTo;

    @Override
    public void init() throws ServletException {
        super.init();
        mealsTo = MealsUtil.getMealsTo(Storage.getMeals(), CALORIES_PER_DAY);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to meals");
        request.setAttribute("mealsToStorage", mealsTo);
        request.getRequestDispatcher("meals.jsp").forward(request, response);
    }
}
