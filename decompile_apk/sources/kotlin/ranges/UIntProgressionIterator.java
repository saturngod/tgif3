package kotlin.ranges;

import java.util.NoSuchElementException;
import kotlin.ExperimentalUnsignedTypes;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.UInt;
import kotlin.collections.UIntIterator;

@ExperimentalUnsignedTypes
@SinceKotlin(version = "1.3")
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0003\u0018\u00002\u00020\u0001B \u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006ø\u0001\u0000¢\u0006\u0002\u0010\u0007J\t\u0010\n\u001a\u00020\u000bH\u0002J\u0010\u0010\r\u001a\u00020\u0003H\u0016ø\u0001\u0000¢\u0006\u0002\u0010\u000eR\u0013\u0010\b\u001a\u00020\u0003X\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u000e¢\u0006\u0002\n\u0000R\u0013\u0010\f\u001a\u00020\u0003X\u000eø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\tR\u0013\u0010\u0005\u001a\u00020\u0003X\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\t\u0002\u0004\n\u0002\b\u0019¨\u0006\u000f"}, d2 = {"Lkotlin/ranges/UIntProgressionIterator;", "Lkotlin/collections/UIntIterator;", "first", "Lkotlin/UInt;", "last", "step", "", "(IIILkotlin/jvm/internal/DefaultConstructorMarker;)V", "finalElement", "I", "hasNext", "", "next", "nextUInt", "()I", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
/* compiled from: UIntRange.kt */
final class UIntProgressionIterator extends UIntIterator {
    private final int finalElement;
    private boolean hasNext;
    private int next;
    private final int step;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private UIntProgressionIterator(int r3, int r4, int r5) {
        /*
        r2 = this;
        r2.<init>();
        r2.finalElement = r4;
        r0 = 0;
        r1 = 1;
        if (r5 <= 0) goto L_0x0011;
    L_0x0009:
        r4 = kotlin.UnsignedKt.uintCompare(r3, r4);
        if (r4 > 0) goto L_0x0018;
    L_0x000f:
        r0 = 1;
        goto L_0x0018;
    L_0x0011:
        r4 = kotlin.UnsignedKt.uintCompare(r3, r4);
        if (r4 < 0) goto L_0x0018;
    L_0x0017:
        goto L_0x000f;
    L_0x0018:
        r2.hasNext = r0;
        r4 = kotlin.UInt.constructor-impl(r5);
        r2.step = r4;
        r4 = r2.hasNext;
        if (r4 == 0) goto L_0x0025;
    L_0x0024:
        goto L_0x0027;
    L_0x0025:
        r3 = r2.finalElement;
    L_0x0027:
        r2.next = r3;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.ranges.UIntProgressionIterator.<init>(int, int, int):void");
    }

    public boolean hasNext() {
        return this.hasNext;
    }

    public int nextUInt() {
        int i = this.next;
        if (i != this.finalElement) {
            this.next = UInt.constructor-impl(this.next + this.step);
        } else if (this.hasNext) {
            this.hasNext = false;
        } else {
            throw new NoSuchElementException();
        }
        return i;
    }
}
