package org.jetbrains.anko.db;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.anko.AnkoException;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\u001a \u0010\u0000\u001a\u0004\u0018\u00010\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u00012\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0004H\u0002\u001a\u001b\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00070\u0006\"\n\b\u0000\u0010\u0007\u0018\u0001*\u00020\u0001H\b\u001a\"\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00070\u0006\"\u0004\b\u0000\u0010\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00070\u0004H\u0001\u001a\u0014\u0010\t\u001a\u00020\n2\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0004H\u0002¨\u0006\u000b"}, d2 = {"castValue", "", "value", "type", "Ljava/lang/Class;", "classParser", "Lorg/jetbrains/anko/db/RowParser;", "T", "clazz", "hasApplicableType", "", "sqlite-base_release"}, k = 2, mv = {1, 1, 13})
/* compiled from: ClassParser.kt */
public final class ClassParserKt {
    private static final <T> RowParser<T> classParser() {
        Intrinsics.reifiedOperationMarker(4, "T");
        return classParser(Object.class);
    }

    @NotNull
    @PublishedApi
    public static final <T> RowParser<T> classParser(@NotNull Class<T> cls) {
        Intrinsics.checkParameterIsNotNull(cls, "clazz");
        Object declaredConstructors = cls.getDeclaredConstructors();
        Intrinsics.checkExpressionValueIsNotNull(declaredConstructors, "clazz.declaredConstructors");
        Collection arrayList = new ArrayList();
        int length = declaredConstructors.length;
        int i = 0;
        while (true) {
            int i2 = 1;
            if (i >= length) {
                break;
            }
            Constructor constructor = declaredConstructors[i];
            Intrinsics.checkExpressionValueIsNotNull(constructor, "ctr");
            if (!constructor.isVarArgs()) {
                if (Modifier.isPublic(constructor.getModifiers())) {
                    Class[] parameterTypes = constructor.getParameterTypes();
                    if (parameterTypes != null) {
                        if (((parameterTypes.length == 0 ? 1 : 0) ^ 1) != 0) {
                            Object obj;
                            for (Class hasApplicableType : parameterTypes) {
                                if (!hasApplicableType(hasApplicableType)) {
                                    obj = null;
                                    break;
                                }
                            }
                            obj = 1;
                            if (obj != null) {
                                if (i2 != 0) {
                                    arrayList.add(constructor);
                                }
                                i++;
                            }
                        }
                    }
                }
            }
            i2 = 0;
            if (i2 != 0) {
                arrayList.add(constructor);
            }
            i++;
        }
        List list = (List) arrayList;
        if (list.isEmpty()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Can't initialize object parser for ");
            stringBuilder.append(cls.getCanonicalName());
            stringBuilder.append(", no acceptable constructors found");
            throw new AnkoException(stringBuilder.toString());
        }
        if (list.size() > 1) {
            Collection collection = (Collection) new ArrayList();
            for (Object next : list) {
                if (((Constructor) next).isAnnotationPresent(ClassParserConstructor.class)) {
                    collection.add(next);
                }
            }
            cls = (Constructor) CollectionsKt___CollectionsKt.singleOrNull((List) collection);
            if (cls == null) {
                throw ((Throwable) new AnkoException("Several constructors are annotated with ClassParserConstructor"));
            }
        } else {
            cls = (Constructor) list.get(0);
        }
        return new ClassParserKt$classParser$1(cls);
    }

    private static final boolean hasApplicableType(Class<?> cls) {
        boolean z = true;
        if (cls.isPrimitive()) {
            return true;
        }
        if (!Intrinsics.areEqual((Object) cls, (Object) String.class)) {
            if (!Intrinsics.areEqual((Object) cls, (Object) CharSequence.class)) {
                if (!Intrinsics.areEqual((Object) cls, (Object) Long.class)) {
                    if (!Intrinsics.areEqual((Object) cls, (Object) Integer.class)) {
                        if (!Intrinsics.areEqual((Object) cls, (Object) Byte.class)) {
                            if (!Intrinsics.areEqual((Object) cls, (Object) Character.class)) {
                                if (!Intrinsics.areEqual((Object) cls, (Object) Boolean.class)) {
                                    if (!Intrinsics.areEqual((Object) cls, (Object) Float.class)) {
                                        if (!Intrinsics.areEqual((Object) cls, (Object) Double.class)) {
                                            if (Intrinsics.areEqual((Object) cls, (Object) Short.class) == null) {
                                                z = false;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return z;
    }

    private static final Object castValue(Object obj, Class<?> cls) {
        if (obj == null) {
            if (cls.isPrimitive()) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("null can't be converted to the value of primitive type ");
                stringBuilder.append(cls.getCanonicalName());
                throw ((Throwable) new AnkoException(stringBuilder.toString()));
            }
        }
        if (obj != null) {
            if (!Intrinsics.areEqual((Object) cls, (Object) Object.class)) {
                if (cls.isPrimitive() && Intrinsics.areEqual((Class) JavaSqliteUtils.PRIMITIVES_TO_WRAPPERS.get(cls), obj.getClass())) {
                    return obj;
                }
                if ((obj instanceof Double) && (Intrinsics.areEqual((Object) cls, Float.TYPE) || Intrinsics.areEqual((Object) cls, (Object) Float.class))) {
                    return Float.valueOf((float) ((Number) obj).doubleValue());
                }
                if ((obj instanceof Float) && (Intrinsics.areEqual((Object) cls, Double.TYPE) || Intrinsics.areEqual((Object) cls, (Object) Double.class))) {
                    return Double.valueOf((double) ((Number) obj).floatValue());
                }
                if ((obj instanceof Character) && CharSequence.class.isAssignableFrom(cls)) {
                    return obj.toString();
                }
                if (obj instanceof Long) {
                    if (!Intrinsics.areEqual((Object) cls, Integer.TYPE)) {
                        if (!Intrinsics.areEqual((Object) cls, (Object) Integer.class)) {
                            if (!Intrinsics.areEqual((Object) cls, Short.TYPE)) {
                                if (!Intrinsics.areEqual((Object) cls, (Object) Short.class)) {
                                    if (!Intrinsics.areEqual((Object) cls, Byte.TYPE)) {
                                        if (!Intrinsics.areEqual((Object) cls, (Object) Byte.class)) {
                                            if (!Intrinsics.areEqual((Object) cls, Boolean.TYPE)) {
                                                if (!Intrinsics.areEqual((Object) cls, (Object) Boolean.class)) {
                                                    if (Intrinsics.areEqual((Object) cls, Character.TYPE) || Intrinsics.areEqual((Object) cls, (Object) Character.class)) {
                                                        return Character.valueOf((char) ((int) ((Number) obj).longValue()));
                                                    }
                                                }
                                            }
                                            return Boolean.valueOf(Intrinsics.areEqual(obj, Long.valueOf(0)) ^ 1);
                                        }
                                    }
                                    return Byte.valueOf((byte) ((int) ((Number) obj).longValue()));
                                }
                            }
                            return Short.valueOf((short) ((int) ((Number) obj).longValue()));
                        }
                    }
                    return Integer.valueOf((int) ((Number) obj).longValue());
                }
                if (obj instanceof Integer) {
                    if (!Intrinsics.areEqual((Object) cls, Long.TYPE)) {
                        if (!Intrinsics.areEqual((Object) cls, (Object) Long.class)) {
                            if (!Intrinsics.areEqual((Object) cls, Short.TYPE)) {
                                if (!Intrinsics.areEqual((Object) cls, (Object) Short.class)) {
                                    if (!Intrinsics.areEqual((Object) cls, Byte.TYPE)) {
                                        if (!Intrinsics.areEqual((Object) cls, (Object) Byte.class)) {
                                            if (!Intrinsics.areEqual((Object) cls, Boolean.TYPE)) {
                                                if (!Intrinsics.areEqual((Object) cls, (Object) Boolean.class)) {
                                                    if (Intrinsics.areEqual((Object) cls, Character.TYPE) || Intrinsics.areEqual((Object) cls, (Object) Character.class)) {
                                                        return Character.valueOf((char) ((Number) obj).intValue());
                                                    }
                                                }
                                            }
                                            return Boolean.valueOf(Intrinsics.areEqual(obj, Integer.valueOf(0)) ^ 1);
                                        }
                                    }
                                    return Byte.valueOf((byte) ((Number) obj).intValue());
                                }
                            }
                            return Short.valueOf((short) ((Number) obj).intValue());
                        }
                    }
                    return Long.valueOf((long) ((Number) obj).intValue());
                }
                if (obj instanceof String) {
                    String str = (String) obj;
                    if (str.length() == 1 && (Intrinsics.areEqual((Object) cls, Character.TYPE) || Intrinsics.areEqual((Object) cls, (Object) Character.class))) {
                        return Character.valueOf(str.charAt(0));
                    }
                }
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("Value ");
                stringBuilder2.append(obj);
                stringBuilder2.append(" of type ");
                stringBuilder2.append(obj.getClass());
                stringBuilder2.append(" can't be cast to ");
                stringBuilder2.append(cls.getCanonicalName());
                throw new AnkoException(stringBuilder2.toString());
            }
        }
        return obj;
    }
}
