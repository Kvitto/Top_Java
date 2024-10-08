package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.model.Meal;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage<SK> implements Storage {
    private static final Logger log = Logger.getLogger(AbstractStorage.class.getName());

    @Override
    public void update(Meal m) {
        log.info("update " + m);
        SK searchKey = getExistingSearchKey(m.getId());
        doUpdate(searchKey, m);
    }

    @Override
    public void save(Meal m) {
        log.info("save " + m);
        SK searchKey = getNotExistingSearchKey(m.getId());
        doSave(searchKey, m);
    }

    @Override
    public Meal get(Integer id) {
        log.info("get " + id);
        SK searchKey = getExistingSearchKey(id);
        return doGet(searchKey);
    }

    @Override
    public void delete(Integer id) {
        log.info("delete " + id);
        SK searchKey = getExistingSearchKey(id);
        doDelete(searchKey);
    }

    @Override
    public List<Meal> getAllSorted() {
        log.info("getAllSorted");
        List<Meal> mealList = getMealList();
        Collections.sort(mealList);
        return mealList;
    }

    protected SK getExistingSearchKey(Integer id) {
        log.warning("getExistingSearchKey " + id);
        SK searchKey = getSearchKey(id);
        if (!isExist(searchKey)) throw new RuntimeException(id + " - does not exist");
        return searchKey;
    }

    protected SK getNotExistingSearchKey(Integer id) {
        log.warning("getNotExistingSearchKey " + id);
        SK searchKey = getSearchKey(id);
        if (isExist(searchKey)) throw new RuntimeException(id + " - already exists");
        return searchKey;
    }

    protected abstract List<Meal> getMealList();

    protected abstract boolean isExist(SK searchKey);

    protected abstract void doDelete(SK searchKey);

    protected abstract Meal doGet(SK searchKey);

    protected abstract void doSave(SK searchKey, Meal m);

    protected abstract void doUpdate(SK searchKey, Meal m);

    protected abstract SK getSearchKey(Integer id);
}
