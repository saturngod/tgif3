package kotlin.jvm.internal;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a#\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00012\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\bH\u0007¢\u0006\u0004\b\t\u0010\n\u001a5\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00012\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\b2\u0010\u0010\u000b\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0018\u00010\u0001H\u0007¢\u0006\u0004\b\t\u0010\f\u001a~\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00012\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\b2\u0014\u0010\u000e\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00010\u000f2\u001a\u0010\u0010\u001a\u0016\u0012\u0004\u0012\u00020\u0005\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00010\u00112(\u0010\u0012\u001a$\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001\u0012\u0004\u0012\u00020\u0005\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00010\u0013H\b¢\u0006\u0002\u0010\u0014\"\u0018\u0010\u0000\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001X\u0004¢\u0006\u0004\n\u0002\u0010\u0003\"\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"EMPTY", "", "", "[Ljava/lang/Object;", "MAX_SIZE", "", "collectionToArray", "collection", "", "toArray", "(Ljava/util/Collection;)[Ljava/lang/Object;", "a", "(Ljava/util/Collection;[Ljava/lang/Object;)[Ljava/lang/Object;", "toArrayImpl", "empty", "Lkotlin/Function0;", "alloc", "Lkotlin/Function1;", "trim", "Lkotlin/Function2;", "(Ljava/util/Collection;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;)[Ljava/lang/Object;", "kotlin-stdlib"}, k = 2, mv = {1, 1, 13})
@JvmName(name = "CollectionToArray")
/* compiled from: CollectionToArray.kt */
public final class CollectionToArray {
    private static final Object[] EMPTY = new Object[0];
    private static final int MAX_SIZE = 2147483645;

    private static final Object[] toArrayImpl(Collection<?> collection, Function0<Object[]> function0, Function1<? super Integer, Object[]> function1, Function2<? super Object[], ? super Integer, Object[]> function2) {
        int size = collection.size();
        if (size == 0) {
            return (Object[]) function0.invoke();
        }
        collection = collection.iterator();
        if (!collection.hasNext()) {
            return (Object[]) function0.invoke();
        }
        function0 = (Object[]) function1.invoke(Integer.valueOf(size));
        function1 = null;
        while (true) {
            size = function1 + 1;
            function0[function1] = collection.next();
            if (size >= function0.length) {
                if (collection.hasNext() == null) {
                    return function0;
                }
                function1 = ((size * 3) + 1) >>> 1;
                if (function1 <= size) {
                    if (size < MAX_SIZE) {
                        function1 = MAX_SIZE;
                    } else {
                        throw ((Throwable) new OutOfMemoryError());
                    }
                }
                function0 = Arrays.copyOf(function0, function1);
                Intrinsics.checkExpressionValueIsNotNull(function0, "Arrays.copyOf(result, newSize)");
            } else if (collection.hasNext() == null) {
                return (Object[]) function2.invoke(function0, Integer.valueOf(size));
            }
            function1 = size;
        }
    }

    @NotNull
    @JvmName(name = "toArray")
    public static final Object[] toArray(@NotNull Collection<?> collection) {
        Intrinsics.checkParameterIsNotNull(collection, "collection");
        int size = collection.size();
        if (size != 0) {
            collection = collection.iterator();
            if (collection.hasNext()) {
                Object[] objArr = new Object[size];
                int i = 0;
                while (true) {
                    int i2 = i + 1;
                    objArr[i] = collection.next();
                    if (i2 >= objArr.length) {
                        if (!collection.hasNext()) {
                            return objArr;
                        }
                        i = ((i2 * 3) + 1) >>> 1;
                        if (i <= i2) {
                            if (i2 < MAX_SIZE) {
                                i = MAX_SIZE;
                            } else {
                                throw ((Throwable) new OutOfMemoryError());
                            }
                        }
                        objArr = Arrays.copyOf(objArr, i);
                        Intrinsics.checkExpressionValueIsNotNull(objArr, "Arrays.copyOf(result, newSize)");
                    } else if (!collection.hasNext()) {
                        collection = Arrays.copyOf(objArr, i2);
                        Intrinsics.checkExpressionValueIsNotNull(collection, "Arrays.copyOf(result, size)");
                        return collection;
                    }
                    i = i2;
                }
            }
        }
        return EMPTY;
    }

    @NotNull
    @JvmName(name = "toArray")
    public static final Object[] toArray(@NotNull Collection<?> collection, @Nullable Object[] objArr) {
        Intrinsics.checkParameterIsNotNull(collection, "collection");
        if (objArr != null) {
            int size = collection.size();
            int i = 0;
            if (size != 0) {
                collection = collection.iterator();
                if (collection.hasNext()) {
                    Object[] objArr2;
                    int i2;
                    if (size <= objArr.length) {
                        objArr2 = objArr;
                    } else {
                        Object newInstance = Array.newInstance(objArr.getClass().getComponentType(), size);
                        if (newInstance != null) {
                            objArr2 = (Object[]) newInstance;
                        } else {
                            throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
                        }
                    }
                    while (true) {
                        i2 = i + 1;
                        objArr2[i] = collection.next();
                        if (i2 >= objArr2.length) {
                            if (!collection.hasNext()) {
                                return objArr2;
                            }
                            i = ((i2 * 3) + 1) >>> 1;
                            if (i <= i2) {
                                if (i2 < MAX_SIZE) {
                                    i = MAX_SIZE;
                                } else {
                                    throw ((Throwable) new OutOfMemoryError());
                                }
                            }
                            objArr2 = Arrays.copyOf(objArr2, i);
                            Intrinsics.checkExpressionValueIsNotNull(objArr2, "Arrays.copyOf(result, newSize)");
                        } else if (!collection.hasNext()) {
                            break;
                        }
                        i = i2;
                    }
                    if (objArr2 == objArr) {
                        objArr[i2] = null;
                        return objArr;
                    }
                    collection = Arrays.copyOf(objArr2, i2);
                    Intrinsics.checkExpressionValueIsNotNull(collection, "Arrays.copyOf(result, size)");
                    return collection;
                } else if (objArr.length <= null) {
                    return objArr;
                } else {
                    objArr[0] = null;
                    return objArr;
                }
            } else if (objArr.length <= null) {
                return objArr;
            } else {
                objArr[0] = null;
                return objArr;
            }
        }
        throw ((Throwable) new NullPointerException());
    }
}
