package kotlin;

import java.util.Arrays;
import java.util.Collection;
import java.util.NoSuchElementException;
import kotlin.collections.UShortIterator;
import kotlin.jvm.internal.CollectionToArray;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@ExperimentalUnsignedTypes
@SinceKotlin(version = "1.3")
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0017\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\u0000\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\b@\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001-B\u0014\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006B\u0014\b\u0001\u0012\u0006\u0010\u0007\u001a\u00020\bø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\tJ\u001b\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0002H\u0002ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0012J \u0010\u0013\u001a\u00020\u000f2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\u0016ø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0016J\u0013\u0010\u0017\u001a\u00020\u000f2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019HÖ\u0003J\u001b\u0010\u001a\u001a\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u0004H\u0002ø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001dJ\t\u0010\u001e\u001a\u00020\u0004HÖ\u0001J\u000f\u0010\u001f\u001a\u00020\u000fH\u0016¢\u0006\u0004\b \u0010!J\u0010\u0010\"\u001a\u00020#H\u0002¢\u0006\u0004\b$\u0010%J#\u0010&\u001a\u00020'2\u0006\u0010\u001b\u001a\u00020\u00042\u0006\u0010(\u001a\u00020\u0002H\u0002ø\u0001\u0000¢\u0006\u0004\b)\u0010*J\t\u0010+\u001a\u00020,HÖ\u0001R\u0014\u0010\u0003\u001a\u00020\u00048VX\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0016\u0010\u0007\u001a\u00020\b8\u0000X\u0004¢\u0006\b\n\u0000\u0012\u0004\b\f\u0010\rø\u0001\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006."}, d2 = {"Lkotlin/UShortArray;", "", "Lkotlin/UShort;", "size", "", "constructor-impl", "(I)[S", "storage", "", "([S)[S", "getSize-impl", "([S)I", "storage$annotations", "()V", "contains", "", "element", "contains-xj2QHRw", "([SS)Z", "containsAll", "elements", "containsAll-impl", "([SLjava/util/Collection;)Z", "equals", "other", "", "get", "index", "get-impl", "([SI)S", "hashCode", "isEmpty", "isEmpty-impl", "([S)Z", "iterator", "Lkotlin/collections/UShortIterator;", "iterator-impl", "([S)Lkotlin/collections/UShortIterator;", "set", "", "value", "set-01HTLdE", "([SIS)V", "toString", "", "Iterator", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
/* compiled from: UShortArray.kt */
public final class UShortArray implements Collection<UShort>, KMappedMarker {
    @NotNull
    private final short[] storage;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0017\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\bH\u0002J\u0010\u0010\t\u001a\u00020\nH\u0016ø\u0001\u0000¢\u0006\u0002\u0010\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\f"}, d2 = {"Lkotlin/UShortArray$Iterator;", "Lkotlin/collections/UShortIterator;", "array", "", "([S)V", "index", "", "hasNext", "", "nextUShort", "Lkotlin/UShort;", "()S", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
    /* compiled from: UShortArray.kt */
    private static final class Iterator extends UShortIterator {
        private final short[] array;
        private int index;

        public Iterator(@NotNull short[] sArr) {
            Intrinsics.checkParameterIsNotNull(sArr, "array");
            this.array = sArr;
        }

        public boolean hasNext() {
            return this.index < this.array.length;
        }

        public short nextUShort() {
            if (this.index < this.array.length) {
                short[] sArr = this.array;
                int i = this.index;
                this.index = i + 1;
                return UShort.constructor-impl(sArr[i]);
            }
            throw new NoSuchElementException(String.valueOf(this.index));
        }
    }

    @NotNull
    @PublishedApi
    public static short[] constructor-impl(@NotNull short[] sArr) {
        Intrinsics.checkParameterIsNotNull(sArr, "storage");
        return sArr;
    }

    public static boolean equals-impl(short[] sArr, @Nullable Object obj) {
        return (obj instanceof UShortArray) && Intrinsics.areEqual((Object) sArr, ((UShortArray) obj).unbox-impl());
    }

    public static final boolean equals-impl0(@NotNull short[] sArr, @NotNull short[] sArr2) {
        Intrinsics.checkParameterIsNotNull(sArr, "p1");
        Intrinsics.checkParameterIsNotNull(sArr2, "p2");
        throw null;
    }

    public static int hashCode-impl(short[] sArr) {
        return sArr != null ? Arrays.hashCode(sArr) : 0;
    }

    @PublishedApi
    public static /* synthetic */ void storage$annotations() {
    }

    @NotNull
    public static String toString-impl(short[] sArr) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("UShortArray(storage=");
        stringBuilder.append(Arrays.toString(sArr));
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public /* synthetic */ boolean add(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public boolean add-xj2QHRw(short s) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public boolean addAll(Collection<? extends UShort> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public void clear() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public boolean contains-xj2QHRw(short s) {
        return contains-xj2QHRw(this.storage, s);
    }

    public boolean containsAll(@NotNull Collection<? extends Object> collection) {
        return containsAll-impl(this.storage, collection);
    }

    public boolean equals(Object obj) {
        return equals-impl(this.storage, obj);
    }

    public int getSize() {
        return getSize-impl(this.storage);
    }

    public int hashCode() {
        return hashCode-impl(this.storage);
    }

    public boolean isEmpty() {
        return isEmpty-impl(this.storage);
    }

    @NotNull
    public UShortIterator iterator() {
        return iterator-impl(this.storage);
    }

    public boolean remove(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public boolean removeAll(Collection<? extends Object> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public boolean retainAll(Collection<? extends Object> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public Object[] toArray() {
        return CollectionToArray.toArray(this);
    }

    public <T> T[] toArray(T[] tArr) {
        return CollectionToArray.toArray(this, tArr);
    }

    public String toString() {
        return toString-impl(this.storage);
    }

    @NotNull
    public final /* synthetic */ short[] unbox-impl() {
        return this.storage;
    }

    public final /* bridge */ boolean contains(Object obj) {
        return obj instanceof UShort ? contains-xj2QHRw(((UShort) obj).unbox-impl()) : null;
    }

    public final /* bridge */ int size() {
        return getSize();
    }

    @PublishedApi
    private /* synthetic */ UShortArray(@NotNull short[] sArr) {
        Intrinsics.checkParameterIsNotNull(sArr, "storage");
        this.storage = sArr;
    }

    @NotNull
    public static short[] constructor-impl(int i) {
        return constructor-impl(new short[i]);
    }

    public static final short get-impl(short[] sArr, int i) {
        return UShort.constructor-impl(sArr[i]);
    }

    public static final void set-01HTLdE(short[] sArr, int i, short s) {
        sArr[i] = s;
    }

    public static int getSize-impl(short[] sArr) {
        return sArr.length;
    }

    @NotNull
    public static UShortIterator iterator-impl(short[] sArr) {
        return new Iterator(sArr);
    }

    public static boolean contains-xj2QHRw(short[] sArr, short s) {
        return ArraysKt___ArraysKt.contains(sArr, s);
    }

    public static boolean containsAll-impl(short[] sArr, @NotNull Collection<UShort> collection) {
        Intrinsics.checkParameterIsNotNull(collection, "elements");
        Iterable<UShort> iterable = collection;
        if (((Collection) iterable).isEmpty()) {
            return true;
        }
        for (UShort unbox-impl : iterable) {
            if (!ArraysKt___ArraysKt.contains(sArr, unbox-impl.unbox-impl())) {
                return false;
            }
        }
        return true;
    }

    public static boolean isEmpty-impl(short[] sArr) {
        return sArr.length == null ? 1 : null;
    }
}
