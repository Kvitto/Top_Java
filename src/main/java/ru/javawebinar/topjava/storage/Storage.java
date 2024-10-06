package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface Storage {
    void clear();

    void update(Meal m);

    void save(Meal m);

    Meal get(String uuid);

    void delete(String uuid);

    List<Meal> getAllSorted();

    int size();
}