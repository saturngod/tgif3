package kotlin.ranges;

import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.collections.LongIterator;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\b\u001a\u00020\tH\u0002J\b\u0010\r\u001a\u00020\u0003H\u0016R\u000e\u0010\u0007\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0003X\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u000e"}, d2 = {"Lkotlin/ranges/LongProgressionIterator;", "Lkotlin/collections/LongIterator;", "first", "", "last", "step", "(JJJ)V", "finalElement", "hasNext", "", "next", "getStep", "()J", "nextLong", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
/* compiled from: ProgressionIterators.kt */
public final class LongProgressionIterator extends LongIterator {
    private final long finalElement;
    private boolean hasNext;
    private long next;
    private final long step;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public LongProgressionIterator(long r5, long r7, long r9) {
        /*
        r4 = this;
        r4.<init>();
        r4.step = r9;
        r4.finalElement = r7;
        r9 = r4.step;
        r0 = 0;
        r1 = 1;
        r2 = 0;
        r9 = (r9 > r2 ? 1 : (r9 == r2 ? 0 : -1));
        if (r9 <= 0) goto L_0x0017;
    L_0x0011:
        r7 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1));
        if (r7 > 0) goto L_0x001c;
    L_0x0015:
        r0 = 1;
        goto L_0x001c;
    L_0x0017:
        r7 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1));
        if (r7 < 0) goto L_0x001c;
    L_0x001b:
        goto L_0x0015;
    L_0x001c:
        r4.hasNext = r0;
        r7 = r4.hasNext;
        if (r7 == 0) goto L_0x0023;
    L_0x0022:
        goto L_0x0025;
    L_0x0023:
        r5 = r4.finalElement;
    L_0x0025:
        r4.next = r5;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.ranges.LongProgressionIterator.<init>(long, long, long):void");
    }

    public final long getStep() {
        return this.step;
    }

    public boolean hasNext() {
        return this.hasNext;
    }

    public long nextLong() {
        long j = this.next;
        if (j != this.finalElement) {
            this.next += this.step;
        } else if (this.hasNext) {
            this.hasNext = false;
        } else {
            throw new NoSuchElementException();
        }
        return j;
    }
}
