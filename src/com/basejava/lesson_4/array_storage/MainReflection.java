package com.basejava.lesson_4.array_storage;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {

    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        Resume r = new Resume();
        Class resumeClass = r.getClass();
        Field field = r.getClass().getDeclaredFields()[0];
        field.setAccessible(true);
        System.out.println(field.getName());
        System.out.println(field.get(r));
        field.set(r, "new_uuid");
        //TODO : invoke r.toString via reflection
        System.out.println(r);

        Method method = resumeClass.getMethod("toString");
        method.invoke(r);
    }
}
