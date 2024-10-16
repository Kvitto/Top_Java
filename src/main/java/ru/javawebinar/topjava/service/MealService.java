package ru.javawebinar.topjava.service;

import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class MealService {
    private final MealRepository repository;

    public MealService(MealRepository repository) {
        this.repository = repository;
    }

    public Meal save(User user, Meal meal) {
        if (!meal.getUserId().equals(user.getId())) {
            throw new NotFoundException("meal does not belong to userId");
        }
        return repository.save(meal);
    }

    public boolean delete(User user, int id) {
        Meal meal = repository.get(id);
        if (!meal.getUserId().equals(user.getId())) {
            throw new NotFoundException("meal does not belong to userId");
        }
        return repository.delete(id);
    }

    public Meal get(User user, int id) {
        Meal meal = repository.get(id);
        if (meal == null || !meal.getUserId().equals(user.getId())) {
            throw new NotFoundException("meal does not belong to userId");
        }
        return meal;
    }

    public List<MealTo> getAll(User user) {
        return MealsUtil.getTos(repository.getByUser(user.getId()), user.getCaloriesPerDay());
    }

    public List<MealTo> getAllFiltered(User user, LocalDate startDay, LocalDate endDay, LocalTime startTime, LocalTime endTime) {
        return MealsUtil.filterByPredicate(
                repository.getByUser(user.getId()),
                user.getCaloriesPerDay(),
                (Meal meal) -> DateTimeUtil.isBetweenDays(meal.getDate(), startDay, endDay)
                            && DateTimeUtil.isBetweenTime(meal.getTime(), startTime, endTime));
    }

}