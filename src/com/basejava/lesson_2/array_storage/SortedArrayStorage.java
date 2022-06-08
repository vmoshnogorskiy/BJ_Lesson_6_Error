package com.basejava.lesson_2.array_storage;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void save(Resume r) {
        if (saveVerification(r) == 0) {
            int indexKey = Arrays.binarySearch(storage, 0, size, r);
            //Индекс, под которым нужно хранить резюме
            indexKey = -(indexKey + 1);

            if (indexKey != size) {
                //Сдвигаем элементы массива вправо от Индексв
                for (int i = size; i > indexKey; i--) {
                    storage[i] = storage[i - 1];
                }
            }
            storage[indexKey] = r;
            size++;
        }
    }

    @Override
    public void delete(String uuid) {
        int index = findResume(uuid);
        if (index >= 0) {
            for (int i = index; i < size && size < STORAGE_LIMIT; i++) {
                storage[i] =  storage[i + 1];
            }
            size--;
        }
    }

    @Override
    protected int findResume(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
