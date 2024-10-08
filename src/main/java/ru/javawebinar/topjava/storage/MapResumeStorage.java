package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.model.Meal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapResumeStorage extends AbstractStorage<Meal> {
    protected Map<Integer, Meal> storage = new HashMap<>();

    @Override
    protected boolean isExist(Meal searchKey) {
        return searchKey != null;
    }

    @Override
    protected void doDelete(Meal searchKey) {
        storage.remove(searchKey.getId());
    }

    @Override
    protected Meal doGet(Meal searchKey) {
        return storage.get(searchKey.getId());
    }

    @Override
    protected void doSave(Meal searchKey, Meal m) {
        storage.put(m.getId(), m);
    }

    @Override
    protected void doUpdate(Meal searchKey, Meal m) {
        storage.put(m.getId(), m);
    }

    @Override
    protected Meal getSearchKey(Integer id) {
        return storage.get(id);
    }

    @Override
    protected List<Meal> getMealList() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public int size() {
        return storage.size();
    }
}
