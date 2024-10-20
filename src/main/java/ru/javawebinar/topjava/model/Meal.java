package ru.javawebinar.topjava.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Meal extends AbstractBaseEntity {
    private LocalDateTime registered;

    private String description;

    private int calories;

    public Meal() {
    }

    public Meal(LocalDateTime registered, String description, int calories) {
        this(null, registered, description, calories);
    }

    public Meal(Integer id, LocalDateTime registered, String description, int calories) {
        super(id);
        this.registered = registered;
        this.description = description;
        this.calories = calories;
    }

    public LocalDateTime getRegistered() {
        return registered;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public LocalDate getDate() {
        return registered.toLocalDate();
    }

    public LocalTime getTime() {
        return registered.toLocalTime();
    }

    public void setRegistered(LocalDateTime registered) {
        this.registered = registered;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "id=" + id +
                ", dateTime=" + registered +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                '}';
    }
}
