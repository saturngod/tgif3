package android.support.v4.util;

public final class CircularIntArray {
    private int mCapacityBitmask;
    private int[] mElements;
    private int mHead;
    private int mTail;

    private void doubleCapacity() {
        int length = this.mElements.length;
        int i = length - this.mHead;
        int i2 = length << 1;
        if (i2 >= 0) {
            Object obj = new int[i2];
            System.arraycopy(this.mElements, this.mHead, obj, 0, i);
            System.arraycopy(this.mElements, 0, obj, i, this.mHead);
            this.mElements = obj;
            this.mHead = 0;
            this.mTail = length;
            this.mCapacityBitmask = i2 - 1;
            return;
        }
        throw new RuntimeException("Max array capacity exceeded");
    }

    public CircularIntArray() {
        this(8);
    }

    public CircularIntArray(int i) {
        if (i < 1) {
            throw new IllegalArgumentException("capacity must be >= 1");
        } else if (i <= 1073741824) {
            if (Integer.bitCount(i) != 1) {
                i = Integer.highestOneBit(i - 1) << 1;
            }
            this.mCapacityBitmask = i - 1;
            this.mElements = new int[i];
        } else {
            throw new IllegalArgumentException("capacity must be <= 2^30");
        }
    }

    public void addFirst(int i) {
        this.mHead = (this.mHead - 1) & this.mCapacityBitmask;
        this.mElements[this.mHead] = i;
        if (this.mHead == this.mTail) {
            doubleCapacity();
        }
    }

    public void addLast(int i) {
        this.mElements[this.mTail] = i;
        this.mTail = (this.mTail + 1) & this.mCapacityBitmask;
        if (this.mTail == this.mHead) {
            doubleCapacity();
        }
    }

    public int popFirst() {
        if (this.mHead != this.mTail) {
            int i = this.mElements[this.mHead];
            this.mHead = (this.mHead + 1) & this.mCapacityBitmask;
            return i;
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    public int popLast() {
        if (this.mHead != this.mTail) {
            int i = (this.mTail - 1) & this.mCapacityBitmask;
            int i2 = this.mElements[i];
            this.mTail = i;
            return i2;
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    public void clear() {
        this.mTail = this.mHead;
    }

    public void removeFromStart(int i) {
        if (i > 0) {
            if (i <= size()) {
                this.mHead = this.mCapacityBitmask & (this.mHead + i);
                return;
            }
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public void removeFromEnd(int i) {
        if (i > 0) {
            if (i <= size()) {
                this.mTail = this.mCapacityBitmask & (this.mTail - i);
                return;
            }
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public int getFirst() {
        if (this.mHead != this.mTail) {
            return this.mElements[this.mHead];
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    public int getLast() {
        if (this.mHead != this.mTail) {
            return this.mElements[(this.mTail - 1) & this.mCapacityBitmask];
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    public int get(int i) {
        if (i < 0 || i >= size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return this.mElements[this.mCapacityBitmask & (this.mHead + i)];
    }

    public int size() {
        return (this.mTail - this.mHead) & this.mCapacityBitmask;
    }

    public boolean isEmpty() {
        return this.mHead == this.mTail;
    }
}
