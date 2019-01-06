package org.jetbrains.anko.db;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

class JavaSqliteUtils {
    static final Map<Class<?>, Class<?>> PRIMITIVES_TO_WRAPPERS = new HashMap();

    private JavaSqliteUtils() {
    }

    private static <T> Class<T> wrap(Class<T> cls) {
        return cls.isPrimitive() ? (Class) PRIMITIVES_TO_WRAPPERS.get(cls) : cls;
    }

    static {
        PRIMITIVES_TO_WRAPPERS.put(Boolean.TYPE, Boolean.class);
        PRIMITIVES_TO_WRAPPERS.put(Byte.TYPE, Byte.class);
        PRIMITIVES_TO_WRAPPERS.put(Character.TYPE, Character.class);
        PRIMITIVES_TO_WRAPPERS.put(Double.TYPE, Double.class);
        PRIMITIVES_TO_WRAPPERS.put(Float.TYPE, Float.class);
        PRIMITIVES_TO_WRAPPERS.put(Integer.TYPE, Integer.class);
        PRIMITIVES_TO_WRAPPERS.put(Long.TYPE, Long.class);
        PRIMITIVES_TO_WRAPPERS.put(Short.TYPE, Short.class);
        PRIMITIVES_TO_WRAPPERS.put(Void.TYPE, Void.class);
    }

    static <T> T newInstance(Constructor<T> constructor, Object[] objArr) throws Exception {
        return constructor.newInstance(objArr);
    }
}
