package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface Storage {
    void clear();

    void update(Meal m);

    void save(Meal m);

    Meal get(Integer id);

    void delete(Integer id);

    List<Meal> getAllSorted();

    int size();
}