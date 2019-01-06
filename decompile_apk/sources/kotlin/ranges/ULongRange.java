package kotlin.ranges;

import kotlin.ExperimentalUnsignedTypes;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.ULong;
import kotlin.UnsignedKt;
import org.jetbrains.annotations.NotNull;

@ExperimentalUnsignedTypes
@SinceKotlin(version = "1.3")
@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0007\u0018\u0000 \u00172\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u0002:\u0001\u0017B\u0018\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0002\u0010\u0006J\u001b\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0003H\u0002ø\u0001\u0000¢\u0006\u0004\b\r\u0010\u000eJ\u0013\u0010\u000f\u001a\u00020\u000b2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0002J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\b\u0010\u0014\u001a\u00020\u000bH\u0016J\b\u0010\u0015\u001a\u00020\u0016H\u0016R\u0017\u0010\u0005\u001a\u00020\u00038VX\u0004ø\u0001\u0000¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0017\u0010\u0004\u001a\u00020\u00038VX\u0004ø\u0001\u0000¢\u0006\u0006\u001a\u0004\b\t\u0010\bø\u0001\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u0018"}, d2 = {"Lkotlin/ranges/ULongRange;", "Lkotlin/ranges/ULongProgression;", "Lkotlin/ranges/ClosedRange;", "Lkotlin/ULong;", "start", "endInclusive", "(JJLkotlin/jvm/internal/DefaultConstructorMarker;)V", "getEndInclusive", "()Lkotlin/ULong;", "getStart", "contains", "", "value", "contains-VKZWuLQ", "(J)Z", "equals", "other", "", "hashCode", "", "isEmpty", "toString", "", "Companion", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
/* compiled from: ULongRange.kt */
public final class ULongRange extends ULongProgression implements ClosedRange<ULong> {
    public static final Companion Companion = new Companion();
    @NotNull
    private static final ULongRange EMPTY = new ULongRange(-1, 0);

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lkotlin/ranges/ULongRange$Companion;", "", "()V", "EMPTY", "Lkotlin/ranges/ULongRange;", "getEMPTY", "()Lkotlin/ranges/ULongRange;", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
    /* compiled from: ULongRange.kt */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final ULongRange getEMPTY() {
            return ULongRange.EMPTY;
        }
    }

    private ULongRange(long j, long j2) {
        super(j, j2, 1);
    }

    public /* bridge */ /* synthetic */ boolean contains(Comparable comparable) {
        return contains-VKZWuLQ(((ULong) comparable).unbox-impl());
    }

    @NotNull
    public ULong getStart() {
        return new ULong(getFirst());
    }

    @NotNull
    public ULong getEndInclusive() {
        return new ULong(getLast());
    }

    public boolean contains-VKZWuLQ(long j) {
        return (UnsignedKt.ulongCompare(getFirst(), j) > 0 || UnsignedKt.ulongCompare(j, getLast()) > null) ? 0 : 1;
    }

    public boolean isEmpty() {
        return UnsignedKt.ulongCompare(getFirst(), getLast()) > 0;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(@org.jetbrains.annotations.Nullable java.lang.Object r5) {
        /*
        r4 = this;
        r0 = r5 instanceof kotlin.ranges.ULongRange;
        if (r0 == 0) goto L_0x002f;
    L_0x0004:
        r0 = r4.isEmpty();
        if (r0 == 0) goto L_0x0013;
    L_0x000a:
        r0 = r5;
        r0 = (kotlin.ranges.ULongRange) r0;
        r0 = r0.isEmpty();
        if (r0 != 0) goto L_0x002d;
    L_0x0013:
        r0 = r4.getFirst();
        r5 = (kotlin.ranges.ULongRange) r5;
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
        throw new UnsupportedOperationException("Method not decompiled: kotlin.ranges.ULongRange.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        return isEmpty() ? -1 : (((int) ULong.constructor-impl(getFirst() ^ ULong.constructor-impl(getFirst() >>> 32))) * 31) + ((int) ULong.constructor-impl(getLast() ^ ULong.constructor-impl(getLast() >>> 32)));
    }

    @NotNull
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(new ULong(getFirst()));
        stringBuilder.append("..");
        stringBuilder.append(new ULong(getLast()));
        return stringBuilder.toString();
    }
}
