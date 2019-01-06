package kotlin.ranges;

import java.util.NoSuchElementException;
import kotlin.ExperimentalUnsignedTypes;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.ULong;
import kotlin.collections.ULongIterator;

@ExperimentalUnsignedTypes
@SinceKotlin(version = "1.3")
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0003\u0018\u00002\u00020\u0001B \u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006ø\u0001\u0000¢\u0006\u0002\u0010\u0007J\t\u0010\n\u001a\u00020\u000bH\u0002J\u0010\u0010\r\u001a\u00020\u0003H\u0016ø\u0001\u0000¢\u0006\u0002\u0010\u000eR\u0013\u0010\b\u001a\u00020\u0003X\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u000e¢\u0006\u0002\n\u0000R\u0013\u0010\f\u001a\u00020\u0003X\u000eø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\tR\u0013\u0010\u0005\u001a\u00020\u0003X\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\t\u0002\u0004\n\u0002\b\u0019¨\u0006\u000f"}, d2 = {"Lkotlin/ranges/ULongProgressionIterator;", "Lkotlin/collections/ULongIterator;", "first", "Lkotlin/ULong;", "last", "step", "", "(JJJLkotlin/jvm/internal/DefaultConstructorMarker;)V", "finalElement", "J", "hasNext", "", "next", "nextULong", "()J", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
/* compiled from: ULongRange.kt */
final class ULongProgressionIterator extends ULongIterator {
    private final long finalElement;
    private boolean hasNext;
    private long next;
    private final long step;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private ULongProgressionIterator(long r5, long r7, long r9) {
        /*
        r4 = this;
        r4.<init>();
        r4.finalElement = r7;
        r0 = 0;
        r1 = 1;
        r2 = 0;
        r2 = (r9 > r2 ? 1 : (r9 == r2 ? 0 : -1));
        if (r2 <= 0) goto L_0x0015;
    L_0x000d:
        r7 = kotlin.UnsignedKt.ulongCompare(r5, r7);
        if (r7 > 0) goto L_0x001c;
    L_0x0013:
        r0 = 1;
        goto L_0x001c;
    L_0x0015:
        r7 = kotlin.UnsignedKt.ulongCompare(r5, r7);
        if (r7 < 0) goto L_0x001c;
    L_0x001b:
        goto L_0x0013;
    L_0x001c:
        r4.hasNext = r0;
        r7 = kotlin.ULong.constructor-impl(r9);
        r4.step = r7;
        r7 = r4.hasNext;
        if (r7 == 0) goto L_0x0029;
    L_0x0028:
        goto L_0x002b;
    L_0x0029:
        r5 = r4.finalElement;
    L_0x002b:
        r4.next = r5;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.ranges.ULongProgressionIterator.<init>(long, long, long):void");
    }

    public boolean hasNext() {
        return this.hasNext;
    }

    public long nextULong() {
        long j = this.next;
        if (j != this.finalElement) {
            this.next = ULong.constructor-impl(this.next + this.step);
        } else if (this.hasNext) {
            this.hasNext = false;
        } else {
            throw new NoSuchElementException();
        }
        return j;
    }
}
