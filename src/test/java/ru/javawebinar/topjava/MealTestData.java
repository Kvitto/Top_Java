package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.UserTestData.*;

public class MealTestData {
    public static final Meal MEAL_1 = new Meal(USER_ID, LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "User - Завтрак", 500);
    public static final Meal MEAL_2 = new Meal(ADMIN_ID, LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Admin - Завтрак", 500);
    public static final Meal MEAL_3 = new Meal(USER_ID, LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "User - Обед", 1000);
    public static final Meal MEAL_4 = new Meal(ADMIN_ID, LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Admin - Обед", 1000);
    public static final Meal MEAL_5 = new Meal(USER_ID, LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "User - Ужин", 500);
    public static final Meal MEAL_6 = new Meal(ADMIN_ID, LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Admin - Ужин", 500);
    public static final Meal MEAL_7 = new Meal(USER_ID, LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "User - Еда на граничное значение", 100);
    public static final Meal MEAL_8 = new Meal(ADMIN_ID, LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Admin - Еда на граничное значение", 100);
    public static final Meal MEAL_9 = new Meal(USER_ID, LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "User - Завтрак", 1000);
    public static final Meal MEAL_10 = new Meal(ADMIN_ID, LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Admin - Завтрак", 1000);
    public static final Meal MEAL_11 = new Meal(USER_ID, LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "User - Обед", 500);
    public static final Meal MEAL_12 = new Meal(ADMIN_ID, LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Admin - Обед", 500);
    public static final Meal MEAL_13 = new Meal(USER_ID, LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "User - Ужин", 410);
    public static final Meal MEAL_14 = new Meal(ADMIN_ID, LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Admin - Ужин", 410);

    static {
        List<Meal> meals = new ArrayList<>(Arrays.asList(
                MEAL_1, MEAL_2, MEAL_3, MEAL_4, MEAL_5, MEAL_6, MEAL_7,
                MEAL_8, MEAL_9, MEAL_10, MEAL_11, MEAL_12, MEAL_13, MEAL_14
        ));
        for (int i = 0; i < meals.size(); i++) {
            meals.get(i).setId(GUEST_ID + 1 + i);
        }
    }
    
    public static Meal getNew() {
        return new Meal(null, LocalDateTime.of(2020, Month.JANUARY, 31, 22, 30), "User - поздний ужин", 999);
    }

    public static Meal getUpdated(){
        Meal meal = new Meal(MEAL_1);
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
