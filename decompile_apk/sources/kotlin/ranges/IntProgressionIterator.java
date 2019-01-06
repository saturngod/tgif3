package kotlin.ranges;

import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.collections.IntIterator;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\b\u001a\u00020\tH\u0002J\b\u0010\r\u001a\u00020\u0003H\u0016R\u000e\u0010\u0007\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0003X\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u000e"}, d2 = {"Lkotlin/ranges/IntProgressionIterator;", "Lkotlin/collections/IntIterator;", "first", "", "last", "step", "(III)V", "finalElement", "hasNext", "", "next", "getStep", "()I", "nextInt", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
/* compiled from: ProgressionIterators.kt */
public final class IntProgressionIterator extends IntIterator {
    private final int finalElement;
    private boolean hasNext;
    private int next;
    private final int step;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public IntProgressionIterator(int r3, int r4, int r5) {
        /*
        r2 = this;
        r2.<init>();
        r2.step = r5;
        r2.finalElement = r4;
        r5 = r2.step;
        r0 = 0;
        r1 = 1;
        if (r5 <= 0) goto L_0x0011;
    L_0x000d:
        if (r3 > r4) goto L_0x0014;
    L_0x000f:
        r0 = 1;
        goto L_0x0014;
    L_0x0011:
        if (r3 < r4) goto L_0x0014;
    L_0x0013:
        goto L_0x000f;
    L_0x0014:
        r2.hasNext = r0;
        r4 = r2.hasNext;
        if (r4 == 0) goto L_0x001b;
    L_0x001a:
        goto L_0x001d;
    L_0x001b:
        r3 = r2.finalElement;
    L_0x001d:
        r2.next = r3;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.ranges.IntProgressionIterator.<init>(int, int, int):void");
    }

    public final int getStep() {
        return this.step;
    }

    public boolean hasNext() {
        return this.hasNext;
    }

    public int nextInt() {
        int i = this.next;
        if (i != this.finalElement) {
            this.next += this.step;
        } else if (this.hasNext) {
            this.hasNext = false;
        } else {
            throw new NoSuchElementException();
        }
        return i;
    }
}
