package com.basejava.storage;

import com.basejava.exception.ExistStorageException;
import com.basejava.exception.NotExistStorageException;
import com.basejava.model.Resume;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public abstract class AbstractStorage implements Storage {

    private static final Comparator<Resume> COMPARATOR_RESUME = Comparator.comparing(Resume::getFullName,
                    String::compareTo)
            .thenComparing(Resume::getUuid,
                    String::compareTo);

    @Override
    public void update(Resume r) {
        Object key = findExistingKey(r.getUuid());
        doUpdate(r, key);
    }

    @Override
    public void save(Resume r) {
        Object key = findNotExistingKey(r.getUuid());
        int index = key != null ? (Integer) key : 0;
        doSave(r, index);
    }

    @Override
    public Resume get(String uuid) {
        Object key = findExistingKey(uuid);
        return doGet(key);
    }

    @Override
    public void delete(String uuid) {
        Object key = findExistingKey(uuid);
        doDelete(key);
    }

    protected Object findExistingKey(String uuid) {
        Object key = findKey(uuid);
        if (isExist(key)) {
            return key;
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    protected Object findNotExistingKey(String uuid) {
        Object key = findKey(uuid);
        if (isExist(key)) {
            throw new ExistStorageException(uuid);
        }
        return key;
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> list = doGetAll();
        Collections.sort(list, COMPARATOR_RESUME);
        return list;
    }

    protected abstract Object findKey(String uuid);

    protected abstract void doUpdate(Resume resume, Object key);

    protected abstract void doSave(Resume r, int index);

    protected abstract Resume doGet(Object key);

    protected abstract void doDelete(Object key);

    protected abstract boolean isExist(Object key);

    protected abstract List<Resume> doGetAll();
}
