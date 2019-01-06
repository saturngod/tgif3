package android.support.v4.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class SparseArrayCompat<E> implements Cloneable {
    private static final Object DELETED = new Object();
    private boolean mGarbage;
    private int[] mKeys;
    private int mSize;
    private Object[] mValues;

    public SparseArrayCompat() {
        this(10);
    }

    public SparseArrayCompat(int i) {
        this.mGarbage = false;
        if (i == 0) {
            this.mKeys = ContainerHelpers.EMPTY_INTS;
            this.mValues = ContainerHelpers.EMPTY_OBJECTS;
        } else {
            i = ContainerHelpers.idealIntArraySize(i);
            this.mKeys = new int[i];
            this.mValues = new Object[i];
        }
        this.mSize = 0;
    }

    public SparseArrayCompat<E> clone() {
        try {
            SparseArrayCompat<E> sparseArrayCompat = (SparseArrayCompat) super.clone();
            sparseArrayCompat.mKeys = (int[]) this.mKeys.clone();
            sparseArrayCompat.mValues = (Object[]) this.mValues.clone();
            return sparseArrayCompat;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    @Nullable
    public E get(int i) {
        return get(i, null);
    }

    public E get(int i, E e) {
        i = ContainerHelpers.binarySearch(this.mKeys, this.mSize, i);
        if (i >= 0) {
            if (this.mValues[i] != DELETED) {
                return this.mValues[i];
            }
        }
        return e;
    }

    public void delete(int i) {
        i = ContainerHelpers.binarySearch(this.mKeys, this.mSize, i);
        if (i >= 0 && this.mValues[i] != DELETED) {
            this.mValues[i] = DELETED;
            this.mGarbage = true;
        }
    }

    public void remove(int i) {
        delete(i);
    }

    public void removeAt(int i) {
        if (this.mValues[i] != DELETED) {
            this.mValues[i] = DELETED;
            this.mGarbage = true;
        }
    }

    public void removeAtRange(int i, int i2) {
        i2 = Math.min(this.mSize, i2 + i);
        while (i < i2) {
            removeAt(i);
            i++;
        }
    }

    private void gc() {
        int i = this.mSize;
        int[] iArr = this.mKeys;
        Object[] objArr = this.mValues;
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            Object obj = objArr[i3];
            if (obj != DELETED) {
                if (i3 != i2) {
                    iArr[i2] = iArr[i3];
                    objArr[i2] = obj;
                    objArr[i3] = null;
                }
                i2++;
            }
        }
        this.mGarbage = false;
        this.mSize = i2;
    }

    public void put(int i, E e) {
        int binarySearch = ContainerHelpers.binarySearch(this.mKeys, this.mSize, i);
        if (binarySearch >= 0) {
            this.mValues[binarySearch] = e;
        } else {
            binarySearch = ~binarySearch;
            if (binarySearch >= this.mSize || this.mValues[binarySearch] != DELETED) {
                if (this.mGarbage && this.mSize >= this.mKeys.length) {
                    gc();
                    binarySearch = ~ContainerHelpers.binarySearch(this.mKeys, this.mSize, i);
                }
                if (this.mSize >= this.mKeys.length) {
                    int idealIntArraySize = ContainerHelpers.idealIntArraySize(this.mSize + 1);
                    Object obj = new int[idealIntArraySize];
                    Object obj2 = new Object[idealIntArraySize];
                    System.arraycopy(this.mKeys, 0, obj, 0, this.mKeys.length);
                    System.arraycopy(this.mValues, 0, obj2, 0, this.mValues.length);
                    this.mKeys = obj;
                    this.mValues = obj2;
                }
                if (this.mSize - binarySearch != 0) {
                    int i2 = binarySearch + 1;
                    System.arraycopy(this.mKeys, binarySearch, this.mKeys, i2, this.mSize - binarySearch);
                    System.arraycopy(this.mValues, binarySearch, this.mValues, i2, this.mSize - binarySearch);
                }
                this.mKeys[binarySearch] = i;
                this.mValues[binarySearch] = e;
                this.mSize++;
            } else {
                this.mKeys[binarySearch] = i;
                this.mValues[binarySearch] = e;
            }
        }
    }

    public void putAll(@NonNull SparseArrayCompat<? extends E> sparseArrayCompat) {
        int size = sparseArrayCompat.size();
        for (int i = 0; i < size; i++) {
            put(sparseArrayCompat.keyAt(i), sparseArrayCompat.valueAt(i));
        }
    }

    public int size() {
        if (this.mGarbage) {
            gc();
        }
        return this.mSize;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int keyAt(int i) {
        if (this.mGarbage) {
            gc();
        }
        return this.mKeys[i];
    }

    public E valueAt(int i) {
        if (this.mGarbage) {
            gc();
        }
        return this.mValues[i];
    }

    public void setValueAt(int i, E e) {
        if (this.mGarbage) {
            gc();
        }
        this.mValues[i] = e;
    }

    public int indexOfKey(int i) {
        if (this.mGarbage) {
            gc();
        }
        return ContainerHelpers.binarySearch(this.mKeys, this.mSize, i);
    }

    public int indexOfValue(E e) {
        if (this.mGarbage) {
            gc();
        }
        for (int i = 0; i < this.mSize; i++) {
            if (this.mValues[i] == e) {
                return i;
            }
        }
        return -1;
    }

    public boolean containsKey(int i) {
        return indexOfKey(i) >= 0;
    }

    public boolean containsValue(E e) {
        return indexOfValue(e) >= null ? true : null;
    }

    public void clear() {
        int i = this.mSize;
        Object[] objArr = this.mValues;
        for (int i2 = 0; i2 < i; i2++) {
            objArr[i2] = null;
        }
        this.mSize = 0;
        this.mGarbage = false;
    }

    public void append(int i, E e) {
        if (this.mSize == 0 || i > this.mKeys[this.mSize - 1]) {
            if (this.mGarbage && this.mSize >= this.mKeys.length) {
                gc();
            }
            int i2 = this.mSize;
            if (i2 >= this.mKeys.length) {
                int idealIntArraySize = ContainerHelpers.idealIntArraySize(i2 + 1);
                Object obj = new int[idealIntArraySize];
                Object obj2 = new Object[idealIntArraySize];
                System.arraycopy(this.mKeys, 0, obj, 0, this.mKeys.length);
                System.arraycopy(this.mValues, 0, obj2, 0, this.mValues.length);
                this.mKeys = obj;
                this.mValues = obj2;
            }
            this.mKeys[i2] = i;
            this.mValues[i2] = e;
            this.mSize = i2 + 1;
            return;
        }
        put(i, e);
    }

    public String toString() {
        if (size() <= 0) {
            return "{}";
        }
        StringBuilder stringBuilder = new StringBuilder(this.mSize * 28);
        stringBuilder.append('{');
        for (int i = 0; i < this.mSize; i++) {
            if (i > 0) {
                stringBuilder.append(", ");
            }
            stringBuilder.append(keyAt(i));
            stringBuilder.append('=');
            SparseArrayCompat valueAt = valueAt(i);
            if (valueAt != this) {
                stringBuilder.append(valueAt);
            } else {
                stringBuilder.append("(this Map)");
            }
        }
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}
