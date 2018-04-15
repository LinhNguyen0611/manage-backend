package vn.uit.mobilestore.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class ReflectUtils {

    private static Boolean isParameterizedType(Type type) {
        Boolean isParameterizedType = Boolean.FALSE;
        try {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            isParameterizedType = Boolean.TRUE;
        }
        catch (Exception e) { }

        return isParameterizedType;
    }

    public static Type getParameterType(Class currentClass, Integer index)
    {
        Type genericClass = currentClass;
        while (!isParameterizedType(genericClass) && !genericClass.equals(Object.class)) {
            genericClass = ((Class) genericClass).getGenericSuperclass();
        }

        return ((ParameterizedType) genericClass).getActualTypeArguments()[index];
    }

    public static <T> Class<T> getGenericType(Class clazz) {
        Class resultClass;
        Type genericSuperclass = clazz.getGenericSuperclass();
        if (genericSuperclass instanceof Class) {
            resultClass = (Class)((ParameterizedType)((Class)genericSuperclass).getGenericSuperclass()).getActualTypeArguments()[1];
        } else {
            resultClass = (Class)((ParameterizedType)(clazz.getGenericSuperclass())).getActualTypeArguments()[1];
        }
        return resultClass;
    }

    public static <T> T newInstance(Class<?> aClass) {
        try {
            return (T) aClass.newInstance();
        } catch (Exception e) { }

        return null;
    }

    public static <T> T newInstanceFromParameter(Class<?> currentClass, Integer index) {
        Class aClass = (Class) getParameterType(currentClass, index);

        return newInstance(aClass);
    }
}
