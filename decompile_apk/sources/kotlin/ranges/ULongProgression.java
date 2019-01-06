package kotlin.ranges;

import kotlin.ExperimentalUnsignedTypes;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.ULong;
import kotlin.UnsignedKt;
import kotlin.collections.ULongIterator;
import kotlin.internal.UProgressionUtilKt;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;

@ExperimentalUnsignedTypes
@SinceKotlin(version = "1.3")
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0017\u0018\u0000 \u001a2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u001aB\"\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0006ø\u0001\u0000¢\u0006\u0002\u0010\u0007J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0002J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\b\u0010\u0015\u001a\u00020\u0010H\u0016J\t\u0010\u0016\u001a\u00020\u0017H\u0002J\b\u0010\u0018\u001a\u00020\u0019H\u0016R\u0016\u0010\b\u001a\u00020\u0002ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\nR\u0016\u0010\f\u001a\u00020\u0002ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\r\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\nø\u0001\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u001b"}, d2 = {"Lkotlin/ranges/ULongProgression;", "", "Lkotlin/ULong;", "start", "endInclusive", "step", "", "(JJJLkotlin/jvm/internal/DefaultConstructorMarker;)V", "first", "getFirst", "()J", "J", "last", "getLast", "getStep", "equals", "", "other", "", "hashCode", "", "isEmpty", "iterator", "Lkotlin/collections/ULongIterator;", "toString", "", "Companion", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
/* compiled from: ULongRange.kt */
public class ULongProgression implements Iterable<ULong>, KMappedMarker {
    public static final Companion Companion = new Companion();
    private final long first;
    private final long last;
    private final long step;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J(\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000b\u0002\u0004\n\u0002\b\u0019¨\u0006\f"}, d2 = {"Lkotlin/ranges/ULongProgression$Companion;", "", "()V", "fromClosedRange", "Lkotlin/ranges/ULongProgression;", "rangeStart", "Lkotlin/ULong;", "rangeEnd", "step", "", "fromClosedRange-7ftBX0g", "(JJJ)Lkotlin/ranges/ULongProgression;", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
    /* compiled from: ULongRange.kt */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final ULongProgression fromClosedRange-7ftBX0g(long j, long j2, long j3) {
            return new ULongProgression(j, j2, j3);
        }
    }

    private ULongProgression(long j, long j2, long j3) {
        if (j3 == 0) {
            throw ((Throwable) new IllegalArgumentException("Step must be non-zero."));
        } else if (j3 != Long.MIN_VALUE) {
            this.first = j;
            this.last = UProgressionUtilKt.getProgressionLastElement-7ftBX0g(j, j2, j3);
            this.step = j3;
        } else {
            throw ((Throwable) new IllegalArgumentException("Step must be greater than Long.MIN_VALUE to avoid overflow on negation."));
        }
    }

    public final long getFirst() {
        return this.first;
    }

    public final long getLast() {
        return this.last;
    }

    public final long getStep() {
        return this.step;
    }

    @NotNull
    public ULongIterator iterator() {
        return new ULongProgressionIterator(this.first, this.last, this.step);
    }

    public boolean isEmpty() {
        if (this.step > 0) {
            if (UnsignedKt.ulongCompare(this.first, this.last) <= 0) {
                return false;
            }
        } else if (UnsignedKt.ulongCompare(this.first, this.last) >= 0) {
            return false;
        }
        return true;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(@org.jetbrains.annotations.Nullable java.lang.Object r5) {
        /*
        r4 = this;
        r0 = r5 instanceof kotlin.ranges.ULongProgression;
        if (r0 == 0) goto L_0x002f;
    L_0x0004:
        r0 = r4.isEmpty();
        if (r0 == 0) goto L_0x0013;
    L_0x000a:
        r0 = r5;
        r0 = (kotlin.ranges.ULongProgression) r0;
        r0 = r0.isEmpty();
        if (r0 != 0) goto L_0x002d;
    L_0x0013:
        r0 = r4.first;
        r5 = (kotlin.ranges.ULongProgression) r5;
        r2 = r5.first;
        r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r0 != 0) goto L_0x002f;
    L_0x001d:
        r0 = r4.last;
        r2 = r5.last;
        r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r0 != 0) goto L_0x002f;
    L_0x0025:
        r0 = r4.step;
        r2 = r5.step;
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
        throw new UnsupportedOperationException("Method not decompiled: kotlin.ranges.ULongProgression.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        return isEmpty() ? -1 : (((((int) ULong.constructor-impl(this.first ^ ULong.constructor-impl(this.first >>> 32))) * 31) + ((int) ULong.constructor-impl(this.last ^ ULong.constructor-impl(this.last >>> 32)))) * 31) + ((int) (this.step ^ (this.step >>> 32)));
    }

    @NotNull
    public String toString() {
        StringBuilder stringBuilder;
        long j;
        if (this.step > 0) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(new ULong(this.first));
            stringBuilder.append("..");
            stringBuilder.append(new ULong(this.last));
            stringBuilder.append(" step ");
            j = this.step;
        } else {
            stringBuilder = new StringBuilder();
            stringBuilder.append(new ULong(this.first));
            stringBuilder.append(" downTo ");
            stringBuilder.append(new ULong(this.last));
            stringBuilder.append(" step ");
            j = -this.step;
        }
        stringBuilder.append(j);
        return stringBuilder.toString();
    }
}
