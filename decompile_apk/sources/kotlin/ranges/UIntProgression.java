package kotlin.ranges;

import kotlin.ExperimentalUnsignedTypes;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.UInt;
import kotlin.UnsignedKt;
import kotlin.collections.UIntIterator;
import kotlin.internal.UProgressionUtilKt;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;

@ExperimentalUnsignedTypes
@SinceKotlin(version = "1.3")
@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0017\u0018\u0000 \u00192\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0019B\"\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0006ø\u0001\u0000¢\u0006\u0002\u0010\u0007J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0002J\b\u0010\u0013\u001a\u00020\u0006H\u0016J\b\u0010\u0014\u001a\u00020\u0010H\u0016J\t\u0010\u0015\u001a\u00020\u0016H\u0002J\b\u0010\u0017\u001a\u00020\u0018H\u0016R\u0016\u0010\b\u001a\u00020\u0002ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\nR\u0016\u0010\f\u001a\u00020\u0002ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\r\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\nø\u0001\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u001a"}, d2 = {"Lkotlin/ranges/UIntProgression;", "", "Lkotlin/UInt;", "start", "endInclusive", "step", "", "(IIILkotlin/jvm/internal/DefaultConstructorMarker;)V", "first", "getFirst", "()I", "I", "last", "getLast", "getStep", "equals", "", "other", "", "hashCode", "isEmpty", "iterator", "Lkotlin/collections/UIntIterator;", "toString", "", "Companion", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
/* compiled from: UIntRange.kt */
public class UIntProgression implements Iterable<UInt>, KMappedMarker {
    public static final Companion Companion = new Companion();
    private final int first;
    private final int last;
    private final int step;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J(\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000b\u0002\u0004\n\u0002\b\u0019¨\u0006\f"}, d2 = {"Lkotlin/ranges/UIntProgression$Companion;", "", "()V", "fromClosedRange", "Lkotlin/ranges/UIntProgression;", "rangeStart", "Lkotlin/UInt;", "rangeEnd", "step", "", "fromClosedRange-Nkh28Cs", "(III)Lkotlin/ranges/UIntProgression;", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
    /* compiled from: UIntRange.kt */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final UIntProgression fromClosedRange-Nkh28Cs(int i, int i2, int i3) {
            return new UIntProgression(i, i2, i3);
        }
    }

    private UIntProgression(int i, int i2, int i3) {
        if (i3 == 0) {
            throw ((Throwable) new IllegalArgumentException("Step must be non-zero."));
        } else if (i3 != Integer.MIN_VALUE) {
            this.first = i;
            this.last = UProgressionUtilKt.getProgressionLastElement-Nkh28Cs(i, i2, i3);
            this.step = i3;
        } else {
            throw ((Throwable) new IllegalArgumentException("Step must be greater than Int.MIN_VALUE to avoid overflow on negation."));
        }
    }

    public final int getFirst() {
        return this.first;
    }

    public final int getLast() {
        return this.last;
    }

    public final int getStep() {
        return this.step;
    }

    @NotNull
    public UIntIterator iterator() {
        return new UIntProgressionIterator(this.first, this.last, this.step);
    }

    public boolean isEmpty() {
        if (this.step > 0) {
            if (UnsignedKt.uintCompare(this.first, this.last) <= 0) {
                return false;
            }
        } else if (UnsignedKt.uintCompare(this.first, this.last) >= 0) {
            return false;
        }
        return true;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(@org.jetbrains.annotations.Nullable java.lang.Object r3) {
        /*
        r2 = this;
        r0 = r3 instanceof kotlin.ranges.UIntProgression;
        if (r0 == 0) goto L_0x0029;
    L_0x0004:
        r0 = r2.isEmpty();
        if (r0 == 0) goto L_0x0013;
    L_0x000a:
        r0 = r3;
        r0 = (kotlin.ranges.UIntProgression) r0;
        r0 = r0.isEmpty();
        if (r0 != 0) goto L_0x0027;
    L_0x0013:
        r0 = r2.first;
        r3 = (kotlin.ranges.UIntProgression) r3;
        r1 = r3.first;
        if (r0 != r1) goto L_0x0029;
    L_0x001b:
        r0 = r2.last;
        r1 = r3.last;
        if (r0 != r1) goto L_0x0029;
    L_0x0021:
        r0 = r2.step;
        r3 = r3.step;
        if (r0 != r3) goto L_0x0029;
    L_0x0027:
        r3 = 1;
        goto L_0x002a;
    L_0x0029:
        r3 = 0;
    L_0x002a:
        return r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.ranges.UIntProgression.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        return isEmpty() ? -1 : (((this.first * 31) + this.last) * 31) + this.step;
    }

    @NotNull
    public String toString() {
        StringBuilder stringBuilder;
        int i;
        if (this.step > 0) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(new UInt(this.first));
            stringBuilder.append("..");
            stringBuilder.append(new UInt(this.last));
            stringBuilder.append(" step ");
            i = this.step;
        } else {
            stringBuilder = new StringBuilder();
            stringBuilder.append(new UInt(this.first));
            stringBuilder.append(" downTo ");
            stringBuilder.append(new UInt(this.last));
            stringBuilder.append(" step ");
            i = -this.step;
        }
        stringBuilder.append(i);
        return stringBuilder.toString();
    }
}
