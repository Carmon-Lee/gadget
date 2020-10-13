package com.my.ioc.twomap;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MyBeanFactory {

    private Map<Class<?>, Object> singleObjects = new ConcurrentHashMap<>();
    private Map<Class<?>, Object> earlySingletonObjects = new ConcurrentHashMap<>();

    public Object getBean(Class<?> type) {
        Object singletonObject = singleObjects.get(type);
        if (singletonObject != null) {
            return singletonObject;
        }

        singletonObject = earlySingletonObjects.get(type);
        if (singletonObject != null) {
            populateInstance(singletonObject);
            return singletonObject;
        }

        return createBean(type);
    }

    Object createBean(Class<?> type) {
        Constructor<?>[] constructors = type.getConstructors();
        Object instance = null;
        try {
            instance = constructors[0].newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        populateInstance(instance);
        // 只有完成了变量赋值，才能放到单例缓存当中
        singleObjects.put(type, instance);
        return instance;
    }

    void populateInstance(Object instance) {
        Class<?> instanceClass = instance.getClass();
        Field[] fields = instanceClass.getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            try {
                if (field.get(instance) == null) {
                    field.set(instance, getBean(field.getType()));
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

    }


}
