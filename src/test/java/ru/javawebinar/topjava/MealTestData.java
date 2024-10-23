package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final Meal userMeal1 = new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "User - Завтрак", 500);
    public static final Meal adminMeal2 = new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Admin - Завтрак", 500);
    public static final Meal userMeal3 = new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "User - Обед", 1000);
    public static final Meal adminMeal4 = new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Admin - Обед", 1000);
    public static final Meal userMeal5 = new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "User - Ужин", 500);
    public static final Meal adminMeal6 = new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Admin - Ужин", 500);
    public static final Meal userMeal7 = new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "User - Еда на граничное значение", 100);
    public static final Meal adminMeal8 = new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Admin - Еда на граничное значение", 100);
    public static final Meal userMeal9 = new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "User - Завтрак", 1000);
    public static final Meal adminMeal10 = new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Admin - Завтрак", 1000);
    public static final Meal userMeal11 = new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "User - Обед", 500);
    public static final Meal adminMeal12 = new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Admin - Обед", 500);
    public static final Meal userMeal13 = new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "User - Ужин", 410);
    public static final Meal adminMeal14 = new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Admin - Ужин", 410);

    static {
        List<Meal> meals = new ArrayList<>(Arrays.asList(
                userMeal1, adminMeal2, userMeal3, adminMeal4, userMeal5, adminMeal6, userMeal7,
                adminMeal8, userMeal9, adminMeal10, userMeal11, adminMeal12, userMeal13, adminMeal14
        ));
        for (int i = 0; i < meals.size(); i++) {
            meals.get(i).setId(START_SEQ + 3 + i); // 3 - shift of users id in sequence
        }
    }
    
    public static Meal getNew() {
        return new Meal(null, LocalDateTime.of(2020, Month.JANUARY, 31, 22, 30), "User - поздний ужин", 999);
    }

    public static Meal getUpdated(){
        Meal meal = new Meal(userMeal1);
        meal.setDateTime(LocalDateTime.of(2020, Month.JANUARY, 30, 16, 0));
        meal.setDescription("User - новая еда");
        meal.setCalories(888);
        return meal;
    }

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingRecursiveFieldByFieldElementComparator().isEqualTo(expected);
    }
}
