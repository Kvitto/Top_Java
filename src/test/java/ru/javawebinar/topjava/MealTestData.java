package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final Meal userMeal1 = new Meal(START_SEQ + 3, LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "User - Завтрак", 500);
    public static final Meal userMeal2 = new Meal(START_SEQ + 4, LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "User - Обед", 1000);
    public static final Meal userMeal3 = new Meal(START_SEQ + 5, LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "User - Ужин", 500);
    public static final Meal userMeal4 = new Meal(START_SEQ + 6, LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "User - Еда на граничное значение", 100);
    public static final Meal userMeal5 = new Meal(START_SEQ + 7, LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "User - Завтрак", 1000);
    public static final Meal userMeal6 = new Meal(START_SEQ + 8, LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "User - Обед", 500);
    public static final Meal userMeal7 = new Meal(START_SEQ + 9, LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "User - Ужин", 410);
    public static final Meal adminMeal1 = new Meal(START_SEQ + 10, LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "User - Завтрак", 500);
    public static final Meal adminMeal2 = new Meal(START_SEQ + 11, LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "User - Обед", 1000);
    public static final Meal adminMeal3 = new Meal(START_SEQ + 12, LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "User - Ужин", 500);
    public static final Meal adminMeal4 = new Meal(START_SEQ + 13, LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "User - Еда на граничное значение", 100);
    public static final Meal adminMeal5 = new Meal(START_SEQ + 14, LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "User - Завтрак", 1000);
    public static final Meal adminMeal6 = new Meal(START_SEQ + 15, LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "User - Обед", 500);
    public static final Meal adminMeal7 = new Meal(START_SEQ + 16, LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "User - Ужин", 410);


    public static Meal getNewMeal() {
        return new Meal(null, LocalDateTime.of(2020, Month.JANUARY, 31, 22, 30), "User - поздний ужин", 999);
    }

    public static Meal getUpdatedMeal(){
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
