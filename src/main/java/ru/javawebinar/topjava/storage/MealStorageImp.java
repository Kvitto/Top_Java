package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.model.Meal;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

public class MealStorageImp implements Storage {
    private static final Logger log = Logger.getLogger(MealStorageImp.class.getName());
    private final Map<Integer, Meal> storage = new HashMap<>();
    private static AtomicInteger sequence;

    @Override
    public Meal create(Meal m) {
        log.info("Storage create " + m);
        if (m.getId() == null) {
            m = new Meal(generateId(), m.getDateTime(), m.getDescription(), m.getCalories());
            storage.put(m.getId(), m);
        } else {
            log.info("Can`t create! Storage already exists id = " + m.getId());
        }
        return m;
    }

    @Override
    public Meal get(int id) {
        log.info("Storage get " + id);
        if (storage.containsKey(id)) {
            return storage.get(id);
        } else {
            log.info("Can`t get! Storage does not exist id = " + id);
            return null;
        }
    }

    @Override
    public Meal update(Meal m) {
        log.info("Storage update " + m);
        if (storage.containsKey(m.getId())) {
            storage.put(m.getId(), m);
        } else {
            log.info("Can`t update! Storage does not exist id = " + m.getId());
        }
        return m;
    }

    @Override
    public void delete(int id) {
        log.info("Storage delete " + id);
        if (storage.containsKey(id)) {
            storage.remove(id);
        } else {
            log.info("Can`t delete! Storage does not exist id = " + id);
        }
    }

    @Override
    public List<Meal> getAll() {
        log.info("Storage get all sorted");
        return new ArrayList<>(storage.values());
    }

    private static int generateId() {
        if (sequence == null) {
            sequence = new AtomicInteger(0);
        }
        return sequence.getAndIncrement();
    }
}
