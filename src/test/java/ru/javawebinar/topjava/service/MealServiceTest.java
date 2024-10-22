package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    static {
        // Only for postgres driver logging
        // It uses java.util.logging and logged via jul-to-slf4j bridge
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService service;

    @Test
    public void create() {
        Meal created = service.create(getNew(), USER_ID);
        Integer id = created.getId();
        Meal newMeal = getNew();
        newMeal.setId(id);
        assertMatch(created, newMeal);
        assertMatch(service.get(id, USER_ID), newMeal);
    }

    @Test
    public void get() {
        Meal meal = service.get(MEAL_1.getId(), USER_ID);
        assertMatch(MEAL_1, meal);
    }

    @Test
    public void update() {
        Meal meal = getUpdated();
        service.update(meal, USER_ID);
        assertMatch(service.get(meal.getId(), USER_ID), getUpdated());
    }

    @Test
    public void delete() {
        service.delete(MEAL_1.getId(), USER_ID);
        assertThrows(NotFoundException.class, () -> service.get(MEAL_1.getId(), USER_ID));
    }

    @Test
    public void getBetweenInclusive() {
        List<Meal> all = service.getBetweenInclusive(LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 30), USER_ID);
        assertThat(all).doesNotContain(MEAL_7);
    }

    @Test
    public void getAll() {
        List<Meal> all = service.getAll(USER_ID);
        assertMatch(all, MEAL_13, MEAL_11, MEAL_9, MEAL_7, MEAL_5, MEAL_3, MEAL_1);
    }

    @Test
    public void deleteAlien() {
        assertThrows(NotFoundException.class, () -> service.delete(MEAL_1.getId(), ADMIN_ID));
    }

    @Test
    public void getAlien() {
        assertThrows(NotFoundException.class, () -> service.get(MEAL_1.getId(), ADMIN_ID));
    }

    @Test
    public void updateAlien() {
        assertThrows(NotFoundException.class, () -> service.update(getUpdated(), ADMIN_ID));
    }

    @Test
    public void duplicateDateTimeCreate() {
        assertThrows(DataAccessException.class, () ->
                service.create(new Meal(null, LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "User - поздний ужин", 999), USER_ID));
    }
}