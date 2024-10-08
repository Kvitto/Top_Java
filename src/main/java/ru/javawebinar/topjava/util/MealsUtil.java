package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.storage.Storage;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class MealsUtil {
    private static AtomicInteger sequence;

    public static List<MealTo> getMealsTo(List<Meal> meals, int caloriesPerDay) {
        return filteredByStreams(meals, LocalTime.MIN, LocalTime.MAX, caloriesPerDay);
    }

    public static List<MealTo> filteredByStreams(List<Meal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> caloriesSumByDate = meals.stream()
                .collect(
                        Collectors.groupingBy(Meal::getDate, Collectors.summingInt(Meal::getCalories))
                );

        return meals.stream()
                .filter(meal -> TimeUtil.isBetweenHalfOpen(meal.getTime(), startTime, endTime))
                .map(meal -> new MealTo(meal, caloriesSumByDate.get(meal.getDate()) > caloriesPerDay))
                .collect(Collectors.toList());
    }

    public static int generateId() {
        if (sequence == null) {
            sequence = new AtomicInteger(0);
        }
        return sequence.incrementAndGet();
    }

    public static void populateStorage(Storage storage){
        storage.save(new Meal(generateId(), LocalDateTime.of(2020, Month.JANUARY, 29, 23, 59), "Яблоко", 50));
        storage.save(new Meal(generateId(), LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500));
        storage.save(new Meal(generateId(), LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000));
        storage.save(new Meal(generateId(), LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500));
        storage.save(new Meal(generateId(), LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100));
        storage.save(new Meal(generateId(), LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000));
        storage.save(new Meal(generateId(), LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500));
        storage.save(new Meal(generateId(), LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410));
    }
}
