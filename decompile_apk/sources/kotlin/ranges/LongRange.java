package kotlin.ranges;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 \u00152\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u0002:\u0001\u0015B\u0015\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\u0011\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0003H\u0002J\u0013\u0010\r\u001a\u00020\u000b2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0002J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0012\u001a\u00020\u000bH\u0016J\b\u0010\u0013\u001a\u00020\u0014H\u0016R\u0014\u0010\u0005\u001a\u00020\u00038VX\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u0004\u001a\u00020\u00038VX\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\b¨\u0006\u0016"}, d2 = {"Lkotlin/ranges/LongRange;", "Lkotlin/ranges/LongProgression;", "Lkotlin/ranges/ClosedRange;", "", "start", "endInclusive", "(JJ)V", "getEndInclusive", "()Ljava/lang/Long;", "getStart", "contains", "", "value", "equals", "other", "", "hashCode", "", "isEmpty", "toString", "", "Companion", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
/* compiled from: Ranges.kt */
public final class LongRange extends LongProgression implements ClosedRange<Long> {
    public static final Companion Companion = new Companion();
    @NotNull
    private static final LongRange EMPTY = new LongRange(1, 0);

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lkotlin/ranges/LongRange$Companion;", "", "()V", "EMPTY", "Lkotlin/ranges/LongRange;", "getEMPTY", "()Lkotlin/ranges/LongRange;", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
    /* compiled from: Ranges.kt */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final LongRange getEMPTY() {
            return LongRange.EMPTY;
        }
    }

    public LongRange(long j, long j2) {
        super(j, j2, 1);
    }

    @NotNull
    public Long getStart() {
        return Long.valueOf(getFirst());
    }

    @NotNull
    public Long getEndInclusive() {
        return Long.valueOf(getLast());
    }

    public boolean contains(long j) {
        return (getFirst() > j || j > getLast()) ? 0 : 1;
    }

    public boolean isEmpty() {
        return getFirst() > getLast();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(@org.jetbrains.annotations.Nullable java.lang.Object r5) {
        /*
        r4 = this;
        r0 = r5 instanceof kotlin.ranges.LongRange;
        if (r0 == 0) goto L_0x002f;
    L_0x0004:
        r0 = r4.isEmpty();
        if (r0 == 0) goto L_0x0013;
    L_0x000a:
        r0 = r5;
        r0 = (kotlin.ranges.LongRange) r0;
        r0 = r0.isEmpty();
        if (r0 != 0) goto L_0x002d;
    L_0x0013:
        r0 = r4.getFirst();
        r5 = (kotlin.ranges.LongRange) r5;
        r2 = r5.getFirst();
        r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r0 != 0) goto L_0x002f;
    L_0x0021:
        r0 = r4.getLast();
        r2 = r5.getLast();
        r5 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r5 != 0) goto L_0x002f;
    L_0x002d:
        r5 = 1;
        goto L_0x0030;
    L_0x002f:
        r5 = 0;
    L_0x0030:
        return r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.ranges.LongRange.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        return isEmpty() ? -1 : (int) ((((long) 31) * (getFirst() ^ (getFirst() >>> 32))) + (getLast() ^ (getLast() >>> 32)));
    }

    @NotNull
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getFirst());
        stringBuilder.append("..");
        stringBuilder.append(getLast());
        return stringBuilder.toString();
    }
}
