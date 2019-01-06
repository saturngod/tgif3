package android.support.v4.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public final class ArraySet<E> implements Collection<E>, Set<E> {
    private static final int BASE_SIZE = 4;
    private static final int CACHE_SIZE = 10;
    private static final boolean DEBUG = false;
    private static final int[] INT = new int[0];
    private static final Object[] OBJECT = new Object[0];
    private static final String TAG = "ArraySet";
    @Nullable
    private static Object[] sBaseCache;
    private static int sBaseCacheSize;
    @Nullable
    private static Object[] sTwiceBaseCache;
    private static int sTwiceBaseCacheSize;
    Object[] mArray;
    private MapCollections<E, E> mCollections;
    private int[] mHashes;
    int mSize;

    /* renamed from: android.support.v4.util.ArraySet$1 */
    class C02941 extends MapCollections<E, E> {
        C02941() {
        }

        protected int colGetSize() {
            return ArraySet.this.mSize;
        }

        protected Object colGetEntry(int i, int i2) {
            return ArraySet.this.mArray[i];
        }

        protected int colIndexOfKey(Object obj) {
            return ArraySet.this.indexOf(obj);
        }

        protected int colIndexOfValue(Object obj) {
            return ArraySet.this.indexOf(obj);
        }

        protected Map<E, E> colGetMap() {
            throw new UnsupportedOperationException("not a map");
        }

        protected void colPut(E e, E e2) {
            ArraySet.this.add(e);
        }

        protected E colSetValue(int i, E e) {
            throw new UnsupportedOperationException("not a map");
        }

        protected void colRemoveAt(int i) {
            ArraySet.this.removeAt(i);
        }

        protected void colClear() {
            ArraySet.this.clear();
        }
    }

    private int indexOf(Object obj, int i) {
        int i2 = this.mSize;
        if (i2 == 0) {
            return -1;
        }
        int binarySearch = ContainerHelpers.binarySearch(this.mHashes, i2, i);
        if (binarySearch < 0 || obj.equals(this.mArray[binarySearch])) {
            return binarySearch;
        }
        int i3 = binarySearch + 1;
        while (i3 < i2 && this.mHashes[i3] == i) {
            if (obj.equals(this.mArray[i3])) {
                return i3;
            }
            i3++;
        }
        binarySearch--;
        while (binarySearch >= 0 && this.mHashes[binarySearch] == i) {
            if (obj.equals(this.mArray[binarySearch])) {
                return binarySearch;
            }
            binarySearch--;
        }
        return ~i3;
    }

    private int indexOfNull() {
        int i = this.mSize;
        if (i == 0) {
            return -1;
        }
        int binarySearch = ContainerHelpers.binarySearch(this.mHashes, i, 0);
        if (binarySearch < 0 || this.mArray[binarySearch] == null) {
            return binarySearch;
        }
        int i2 = binarySearch + 1;
        while (i2 < i && this.mHashes[i2] == 0) {
            if (this.mArray[i2] == null) {
                return i2;
            }
            i2++;
        }
        binarySearch--;
        while (binarySearch >= 0 && this.mHashes[binarySearch] == 0) {
            if (this.mArray[binarySearch] == null) {
                return binarySearch;
            }
            binarySearch--;
        }
        return ~i2;
    }

    private void allocArrays(int i) {
        if (i == 8) {
            synchronized (ArraySet.class) {
                if (sTwiceBaseCache != null) {
                    i = sTwiceBaseCache;
                    this.mArray = i;
                    sTwiceBaseCache = (Object[]) i[0];
                    this.mHashes = (int[]) i[1];
                    i[1] = null;
                    i[0] = null;
                    sTwiceBaseCacheSize--;
                    return;
                }
            }
        } else if (i == 4) {
            synchronized (ArraySet.class) {
                if (sBaseCache != null) {
                    i = sBaseCache;
                    this.mArray = i;
                    sBaseCache = (Object[]) i[0];
                    this.mHashes = (int[]) i[1];
                    i[1] = null;
                    i[0] = null;
                    sBaseCacheSize--;
                    return;
                }
            }
        }
        this.mHashes = new int[i];
        this.mArray = new Object[i];
    }

    private static void freeArrays(int[] iArr, Object[] objArr, int i) {
        if (iArr.length == 8) {
            synchronized (ArraySet.class) {
                if (sTwiceBaseCacheSize < 10) {
                    objArr[0] = sTwiceBaseCache;
                    objArr[1] = iArr;
                    for (i--; i >= 2; i--) {
                        objArr[i] = null;
                    }
                    sTwiceBaseCache = objArr;
                    sTwiceBaseCacheSize += 1;
                }
            }
        } else if (iArr.length == 4) {
            synchronized (ArraySet.class) {
                if (sBaseCacheSize < 10) {
                    objArr[0] = sBaseCache;
                    objArr[1] = iArr;
                    for (i--; i >= 2; i--) {
                        objArr[i] = null;
                    }
                    sBaseCache = objArr;
                    sBaseCacheSize += 1;
                }
            }
        }
    }

    public ArraySet() {
        this(0);
    }

    public ArraySet(int i) {
        if (i == 0) {
            this.mHashes = INT;
            this.mArray = OBJECT;
        } else {
            allocArrays(i);
        }
        this.mSize = 0;
    }

    public ArraySet(@Nullable ArraySet<E> arraySet) {
        this();
        if (arraySet != null) {
            addAll((ArraySet) arraySet);
        }
    }

    public ArraySet(@Nullable Collection<E> collection) {
        this();
        if (collection != null) {
            addAll((Collection) collection);
        }
    }

    public void clear() {
        if (this.mSize != 0) {
            freeArrays(this.mHashes, this.mArray, this.mSize);
            this.mHashes = INT;
            this.mArray = OBJECT;
            this.mSize = 0;
        }
    }

    public void ensureCapacity(int i) {
        if (this.mHashes.length < i) {
            Object obj = this.mHashes;
            Object obj2 = this.mArray;
            allocArrays(i);
            if (this.mSize > 0) {
                System.arraycopy(obj, 0, this.mHashes, 0, this.mSize);
                System.arraycopy(obj2, 0, this.mArray, 0, this.mSize);
            }
            freeArrays(obj, obj2, this.mSize);
        }
    }

    public boolean contains(@Nullable Object obj) {
        return indexOf(obj) >= null ? true : null;
    }

    public int indexOf(@Nullable Object obj) {
        return obj == null ? indexOfNull() : indexOf(obj, obj.hashCode());
    }

    @Nullable
    public E valueAt(int i) {
        return this.mArray[i];
    }

    public boolean isEmpty() {
        return this.mSize <= 0;
    }

    public boolean add(@Nullable E e) {
        int indexOfNull;
        int i;
        if (e == null) {
            indexOfNull = indexOfNull();
            i = 0;
        } else {
            indexOfNull = e.hashCode();
            i = indexOfNull;
            indexOfNull = indexOf(e, indexOfNull);
        }
        if (indexOfNull >= 0) {
            return false;
        }
        int i2;
        indexOfNull = ~indexOfNull;
        if (this.mSize >= this.mHashes.length) {
            i2 = 4;
            if (this.mSize >= 8) {
                i2 = (this.mSize >> 1) + this.mSize;
            } else if (this.mSize >= 4) {
                i2 = 8;
            }
            Object obj = this.mHashes;
            Object obj2 = this.mArray;
            allocArrays(i2);
            if (this.mHashes.length > 0) {
                System.arraycopy(obj, 0, this.mHashes, 0, obj.length);
                System.arraycopy(obj2, 0, this.mArray, 0, obj2.length);
            }
            freeArrays(obj, obj2, this.mSize);
        }
        if (indexOfNull < this.mSize) {
            i2 = indexOfNull + 1;
            System.arraycopy(this.mHashes, indexOfNull, this.mHashes, i2, this.mSize - indexOfNull);
            System.arraycopy(this.mArray, indexOfNull, this.mArray, i2, this.mSize - indexOfNull);
        }
        this.mHashes[indexOfNull] = i;
        this.mArray[indexOfNull] = e;
        this.mSize += 1;
        return true;
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public void append(E e) {
        int i;
        int i2 = this.mSize;
        if (e == null) {
            i = 0;
        } else {
            i = e.hashCode();
        }
        if (i2 >= this.mHashes.length) {
            throw new IllegalStateException("Array is full");
        } else if (i2 <= 0 || this.mHashes[i2 - 1] <= i) {
            this.mSize = i2 + 1;
            this.mHashes[i2] = i;
            this.mArray[i2] = e;
        } else {
            add(e);
        }
    }

    public void addAll(@NonNull ArraySet<? extends E> arraySet) {
        int i = arraySet.mSize;
        ensureCapacity(this.mSize + i);
        int i2 = 0;
        if (this.mSize != 0) {
            while (i2 < i) {
                add(arraySet.valueAt(i2));
                i2++;
            }
        } else if (i > 0) {
            System.arraycopy(arraySet.mHashes, 0, this.mHashes, 0, i);
            System.arraycopy(arraySet.mArray, 0, this.mArray, 0, i);
            this.mSize = i;
        }
    }

    public boolean remove(@Nullable Object obj) {
        obj = indexOf(obj);
        if (obj < null) {
            return null;
        }
        removeAt(obj);
        return true;
    }

    public E removeAt(int i) {
        E e = this.mArray[i];
        if (this.mSize <= 1) {
            freeArrays(this.mHashes, this.mArray, this.mSize);
            this.mHashes = INT;
            this.mArray = OBJECT;
            this.mSize = 0;
        } else {
            int i2 = 8;
            int i3;
            if (this.mHashes.length <= 8 || this.mSize >= this.mHashes.length / 3) {
                this.mSize--;
                if (i < this.mSize) {
                    i3 = i + 1;
                    System.arraycopy(this.mHashes, i3, this.mHashes, i, this.mSize - i);
                    System.arraycopy(this.mArray, i3, this.mArray, i, this.mSize - i);
                }
                this.mArray[this.mSize] = null;
            } else {
                if (this.mSize > 8) {
                    i2 = (this.mSize >> 1) + this.mSize;
                }
                Object obj = this.mHashes;
                Object obj2 = this.mArray;
                allocArrays(i2);
                this.mSize--;
                if (i > 0) {
                    System.arraycopy(obj, 0, this.mHashes, 0, i);
                    System.arraycopy(obj2, 0, this.mArray, 0, i);
                }
                if (i < this.mSize) {
                    i3 = i + 1;
                    System.arraycopy(obj, i3, this.mHashes, i, this.mSize - i);
                    System.arraycopy(obj2, i3, this.mArray, i, this.mSize - i);
                }
            }
        }
        return e;
    }

    public boolean removeAll(@NonNull ArraySet<? extends E> arraySet) {
        int i = arraySet.mSize;
        int i2 = this.mSize;
        for (int i3 = 0; i3 < i; i3++) {
            remove(arraySet.valueAt(i3));
        }
        if (i2 != this.mSize) {
            return true;
        }
        return false;
    }

    public int size() {
        return this.mSize;
    }

    @NonNull
    public Object[] toArray() {
        Object obj = new Object[this.mSize];
        System.arraycopy(this.mArray, 0, obj, 0, this.mSize);
        return obj;
    }

    @NonNull
    public <T> T[] toArray(@NonNull T[] tArr) {
        if (tArr.length < this.mSize) {
            tArr = (Object[]) Array.newInstance(tArr.getClass().getComponentType(), this.mSize);
        }
        System.arraycopy(this.mArray, 0, tArr, 0, this.mSize);
        if (tArr.length > this.mSize) {
            tArr[this.mSize] = null;
        }
        return tArr;
    }

    public boolean equals(java.lang.Object r5) {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1603198149.run(Unknown Source)
*/
        /*
        r4 = this;
        r0 = 1;
        if (r4 != r5) goto L_0x0004;
    L_0x0003:
        return r0;
    L_0x0004:
        r1 = r5 instanceof java.util.Set;
        r2 = 0;
        if (r1 == 0) goto L_0x002c;
    L_0x0009:
        r5 = (java.util.Set) r5;
        r1 = r4.size();
        r3 = r5.size();
        if (r1 == r3) goto L_0x0016;
    L_0x0015:
        return r2;
    L_0x0016:
        r1 = 0;
    L_0x0017:
        r3 = r4.mSize;	 Catch:{ NullPointerException -> 0x002b, ClassCastException -> 0x002a }
        if (r1 >= r3) goto L_0x0029;	 Catch:{ NullPointerException -> 0x002b, ClassCastException -> 0x002a }
    L_0x001b:
        r3 = r4.valueAt(r1);	 Catch:{ NullPointerException -> 0x002b, ClassCastException -> 0x002a }
        r3 = r5.contains(r3);	 Catch:{ NullPointerException -> 0x002b, ClassCastException -> 0x002a }
        if (r3 != 0) goto L_0x0026;
    L_0x0025:
        return r2;
    L_0x0026:
        r1 = r1 + 1;
        goto L_0x0017;
    L_0x0029:
        return r0;
    L_0x002a:
        return r2;
    L_0x002b:
        return r2;
    L_0x002c:
        return r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.util.ArraySet.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        int[] iArr = this.mHashes;
        int i = 0;
        for (int i2 = 0; i2 < this.mSize; i2++) {
            i += iArr[i2];
        }
        return i;
    }

    public String toString() {
        if (isEmpty()) {
            return "{}";
        }
        StringBuilder stringBuilder = new StringBuilder(this.mSize * 14);
        stringBuilder.append('{');
        for (int i = 0; i < this.mSize; i++) {
            if (i > 0) {
                stringBuilder.append(", ");
            }
            ArraySet valueAt = valueAt(i);
            if (valueAt != this) {
                stringBuilder.append(valueAt);
            } else {
                stringBuilder.append("(this Set)");
            }
        }
        stringBuilder.append('}');
        return stringBuilder.toString();
    }

    private MapCollections<E, E> getCollection() {
        if (this.mCollections == null) {
            this.mCollections = new C02941();
        }
        return this.mCollections;
    }

    public Iterator<E> iterator() {
        return getCollection().getKeySet().iterator();
    }

    public boolean containsAll(@NonNull Collection<?> collection) {
        for (Object contains : collection) {
            if (!contains(contains)) {
                return null;
            }
        }
        return true;
    }

    public boolean addAll(@NonNull Collection<? extends E> collection) {
        ensureCapacity(this.mSize + collection.size());
        boolean z = false;
        for (Object add : collection) {
            z |= add(add);
        }
        return z;
    }

    public boolean removeAll(@NonNull Collection<?> collection) {
        boolean z = false;
        for (Object remove : collection) {
            z |= remove(remove);
        }
        return z;
    }

    public boolean retainAll(@NonNull Collection<?> collection) {
        boolean z = false;
        for (int i = this.mSize - 1; i >= 0; i--) {
            if (!collection.contains(this.mArray[i])) {
                removeAt(i);
                z = true;
            }
        }
        return z;
    }
}
