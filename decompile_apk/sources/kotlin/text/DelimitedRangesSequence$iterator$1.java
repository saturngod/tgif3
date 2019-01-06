package kotlin.text;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.ranges.IntRange;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000%\n\u0000\n\u0002\u0010(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0013\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\b\u0010\u0017\u001a\u00020\u0018H\u0002J\t\u0010\u0019\u001a\u00020\u001aH\u0002J\t\u0010\u001b\u001a\u00020\u0002H\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001c\u0010\f\u001a\u0004\u0018\u00010\u0002X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0006\"\u0004\b\u0013\u0010\bR\u001a\u0010\u0014\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0006\"\u0004\b\u0016\u0010\b¨\u0006\u001c"}, d2 = {"kotlin/text/DelimitedRangesSequence$iterator$1", "", "Lkotlin/ranges/IntRange;", "counter", "", "getCounter", "()I", "setCounter", "(I)V", "currentStartIndex", "getCurrentStartIndex", "setCurrentStartIndex", "nextItem", "getNextItem", "()Lkotlin/ranges/IntRange;", "setNextItem", "(Lkotlin/ranges/IntRange;)V", "nextSearchIndex", "getNextSearchIndex", "setNextSearchIndex", "nextState", "getNextState", "setNextState", "calcNext", "", "hasNext", "", "next", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
/* compiled from: Strings.kt */
public final class DelimitedRangesSequence$iterator$1 implements Iterator<IntRange>, KMappedMarker {
    private int counter;
    private int currentStartIndex;
    @Nullable
    private IntRange nextItem;
    private int nextSearchIndex;
    private int nextState = -1;
    final /* synthetic */ DelimitedRangesSequence this$0;

    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    DelimitedRangesSequence$iterator$1(DelimitedRangesSequence delimitedRangesSequence) {
        this.this$0 = delimitedRangesSequence;
        this.currentStartIndex = RangesKt___RangesKt.coerceIn(delimitedRangesSequence.startIndex, 0, (int) delimitedRangesSequence.input.length());
        this.nextSearchIndex = this.currentStartIndex;
    }

    public final int getNextState() {
        return this.nextState;
    }

    public final void setNextState(int i) {
        this.nextState = i;
    }

    public final int getCurrentStartIndex() {
        return this.currentStartIndex;
    }

    public final void setCurrentStartIndex(int i) {
        this.currentStartIndex = i;
    }

    public final int getNextSearchIndex() {
        return this.nextSearchIndex;
    }

    public final void setNextSearchIndex(int i) {
        this.nextSearchIndex = i;
    }

    @Nullable
    public final IntRange getNextItem() {
        return this.nextItem;
    }

    public final void setNextItem(@Nullable IntRange intRange) {
        this.nextItem = intRange;
    }

    public final int getCounter() {
        return this.counter;
    }

    public final void setCounter(int i) {
        this.counter = i;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void calcNext() {
        /*
        r6 = this;
        r0 = r6.nextSearchIndex;
        r1 = 0;
        if (r0 >= 0) goto L_0x000e;
    L_0x0005:
        r6.nextState = r1;
        r0 = 0;
        r0 = (kotlin.ranges.IntRange) r0;
        r6.nextItem = r0;
        goto L_0x00a4;
    L_0x000e:
        r0 = r6.this$0;
        r0 = r0.limit;
        r2 = -1;
        r3 = 1;
        if (r0 <= 0) goto L_0x0027;
    L_0x0018:
        r0 = r6.counter;
        r0 = r0 + r3;
        r6.counter = r0;
        r0 = r6.counter;
        r4 = r6.this$0;
        r4 = r4.limit;
        if (r0 >= r4) goto L_0x0035;
    L_0x0027:
        r0 = r6.nextSearchIndex;
        r4 = r6.this$0;
        r4 = r4.input;
        r4 = r4.length();
        if (r0 <= r4) goto L_0x004b;
    L_0x0035:
        r0 = r6.currentStartIndex;
        r1 = new kotlin.ranges.IntRange;
        r4 = r6.this$0;
        r4 = r4.input;
        r4 = kotlin.text.StringsKt__StringsKt.getLastIndex(r4);
        r1.<init>(r0, r4);
        r6.nextItem = r1;
        r6.nextSearchIndex = r2;
        goto L_0x00a2;
    L_0x004b:
        r0 = r6.this$0;
        r0 = r0.getNextMatch;
        r4 = r6.this$0;
        r4 = r4.input;
        r5 = r6.nextSearchIndex;
        r5 = java.lang.Integer.valueOf(r5);
        r0 = r0.invoke(r4, r5);
        r0 = (kotlin.Pair) r0;
        if (r0 != 0) goto L_0x007b;
    L_0x0065:
        r0 = r6.currentStartIndex;
        r1 = new kotlin.ranges.IntRange;
        r4 = r6.this$0;
        r4 = r4.input;
        r4 = kotlin.text.StringsKt__StringsKt.getLastIndex(r4);
        r1.<init>(r0, r4);
        r6.nextItem = r1;
        r6.nextSearchIndex = r2;
        goto L_0x00a2;
    L_0x007b:
        r2 = r0.component1();
        r2 = (java.lang.Number) r2;
        r2 = r2.intValue();
        r0 = r0.component2();
        r0 = (java.lang.Number) r0;
        r0 = r0.intValue();
        r4 = r6.currentStartIndex;
        r4 = kotlin.ranges.RangesKt___RangesKt.until(r4, r2);
        r6.nextItem = r4;
        r2 = r2 + r0;
        r6.currentStartIndex = r2;
        r2 = r6.currentStartIndex;
        if (r0 != 0) goto L_0x009f;
    L_0x009e:
        r1 = 1;
    L_0x009f:
        r2 = r2 + r1;
        r6.nextSearchIndex = r2;
    L_0x00a2:
        r6.nextState = r3;
    L_0x00a4:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.text.DelimitedRangesSequence$iterator$1.calcNext():void");
    }

    @NotNull
    public IntRange next() {
        if (this.nextState == -1) {
            calcNext();
        }
        if (this.nextState != 0) {
            IntRange intRange = this.nextItem;
            if (intRange != null) {
                this.nextItem = (IntRange) null;
                this.nextState = -1;
                return intRange;
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.ranges.IntRange");
        }
        throw new NoSuchElementException();
    }

    public boolean hasNext() {
        if (this.nextState == -1) {
            calcNext();
        }
        return this.nextState == 1;
    }
}
