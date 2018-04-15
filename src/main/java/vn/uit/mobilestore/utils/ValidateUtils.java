package vn.uit.mobilestore.utils;

import java.util.Collection;
import java.util.Map;

public class ValidateUtils {
    
    public static Boolean isNullOrEmpty(String input) {
        return (input == null) || input.isEmpty();
    }

    public static Boolean isNullOrEmpty(Collection<?> collection) {
        return (collection == null) || (collection.size() == 0);
    }

    public static Boolean isNullOrEmpty(Map<?, ?> map) {
        return (map == null) || (map.size() == 0);
    }

    public static Boolean isNullOrEmpty(Object object) {
        if (object instanceof String) {
            return isNullOrEmpty((String) object);
        }
        if (object instanceof Collection<?>) {
            return isNullOrEmpty((Collection<?>) object);
        }
        if (object instanceof Map<?, ?>) {
            return isNullOrEmpty((Map<?, ?>) object);
        }

        return object == null;
    }

    public static Boolean isNotNullAndEmpty(String input) {
        return !isNullOrEmpty(input);
    }

    public static Boolean isNotNullAndEmpty(Collection<?> collection) {
        return !isNullOrEmpty(collection);
    }

    public static Boolean isNotNullAndEmpty(Map<?, ?> map) {
        return !isNullOrEmpty(map);
    }

    public static Boolean isNotNullAndEmpty(Object object) {
        if (object instanceof String) {
            return isNotNullAndEmpty((String) object);
        }
        if (object instanceof Collection<?>) {
            return isNotNullAndEmpty((Collection<?>) object);
        }
        if (object instanceof Map<?, ?>) {
            return isNotNullAndEmpty((Map<?, ?>) object);
        }

        return object != null;
    }
}
