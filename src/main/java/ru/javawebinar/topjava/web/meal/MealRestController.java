package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.web.user.ProfileRestController;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Controller
public class MealRestController {
    @Autowired
    private MealService mealService;
    @Autowired
    private ProfileRestController userController;

    public Meal save(Integer userId, Meal meal) {
        return mealService.save(userController.get(), meal);
    }

    public boolean delete(Integer userId, int id) {
        return mealService.delete(userController.get(), id);
    }

    public Meal get(Integer userId, int id) {
        return mealService.get(userController.get(), id);
    }

    public List<MealTo> getAll() {
        return mealService.getAll(userController.get());
    }

    public List<MealTo> getAllFiltered(Integer userId, LocalDate startDay, LocalDate endDay, LocalTime startTime, LocalTime endTime) {
        return mealService.getAllFiltered(userController.get(),
                startDay == null ? LocalDate.MIN : startDay,
                endDay == null ? LocalDate.MAX : endDay,
                startTime == null ? LocalTime.MIN : startTime,
                endTime == null ? LocalTime.MAX : endTime);
    }
}