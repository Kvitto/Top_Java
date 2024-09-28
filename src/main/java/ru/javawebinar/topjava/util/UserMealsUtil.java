package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExcess;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.System.*;
import static ru.javawebinar.topjava.util.TimeUtil.isBetweenHalfOpen;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> meals = Arrays.asList(
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
        );

        List<UserMealWithExcess> mealsTo = filteredByCycles(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        mealsTo.forEach(out::println);

        out.println(filteredByStreams(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000));

        out.println(DailyMeals.getMealsWithExcess(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000));
        out.println(DailyMeals.getMealsWithExcess(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000));
    }

    public static List<UserMealWithExcess> filteredByCycles(List<UserMeal> meals, LocalTime startTime,
                                                            LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> dayCalories = new HashMap<>();
        meals.forEach(meal -> dayCalories.merge(meal.getDateTime().toLocalDate(), meal.getCalories(), Integer::sum));

        List<UserMealWithExcess> mealsWithExcess = new ArrayList<>();
        meals.forEach(meal -> {
            if (isBetweenHalfOpen(meal.getTime(), startTime, endTime)) {
                mealsWithExcess.add(createMealWithExcess(meal, dayCalories.get(meal.getDate()) > caloriesPerDay));
            }
        });
        return mealsWithExcess;
    }

    public static List<UserMealWithExcess> filteredByStreams(List<UserMeal> meals, LocalTime startTime,
                                                             LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> dayCalories = meals.stream()
                .collect(Collectors.groupingBy(m -> m.getDateTime().toLocalDate(), Collectors.summingInt(UserMeal::getCalories)));
        return meals.stream()
                .filter(m -> isBetweenHalfOpen(m.getDateTime().toLocalTime(), startTime, endTime))
                .map(m -> createMealWithExcess(m, dayCalories.get(m.getDateTime().toLocalDate()) > caloriesPerDay))
                .toList();
    }

    private static UserMealWithExcess createMealWithExcess(UserMeal meal, boolean excess) {
        return new UserMealWithExcess(meal.getDateTime(), meal.getDescription(), meal.getCalories(), excess);
    }

    private static class DailyMeals {
        static Map<LocalDate, DailyMeals> dailyMap;
        int calories;
        boolean excess;
        List<UserMeal> meals;

        private DailyMeals(UserMeal meal, int caloriesPerDay) {
            this.calories = meal.getCalories();
            this.excess = calories > caloriesPerDay;
            meals = new ArrayList<>(List.of(meal));
        }

        private static void addMeal(UserMeal meal, int caloriesPerDay) {
            if (!dailyMap.containsKey(meal.getDate())){
                dailyMap.put(meal.getDate(), new DailyMeals(meal, caloriesPerDay));
            } else {
                DailyMeals dailyMeals = dailyMap.get(meal.getDate());
                dailyMeals.calories += meal.getCalories();
                dailyMeals.excess = dailyMeals.calories > caloriesPerDay;
                dailyMeals.meals.add(meal);
            }
        }

        public static List<UserMealWithExcess> getMealsWithExcess(List<UserMeal> meals, LocalTime startTime,
                                                                  LocalTime endTime, int caloriesPerDay) {
            dailyMap = new HashMap<>();
            List<UserMealWithExcess> mealWithExcesses = meals.stream()
                    .peek(m -> DailyMeals.addMeal(m, caloriesPerDay))
                    .map(m -> createMealWithExcess(m, false))
                    .toList();
            return mealWithExcesses.stream()
                    .peek(m -> m.setExcess(dailyMap.get(m.getDateTime().toLocalDate()).excess))
                    .filter(m -> isBetweenHalfOpen(m.getDateTime().toLocalTime(), startTime, endTime))
                    .toList();
        }
    }

}
