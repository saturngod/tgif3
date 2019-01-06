package kotlin.ranges;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002¢\u0006\u0002\u0010\u0005J\u0011\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0002H\u0002J\u0013\u0010\u000e\u001a\u00020\f2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0002J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\b\u0010\u0013\u001a\u00020\fH\u0016J\u0018\u0010\u0014\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\u00022\u0006\u0010\u0016\u001a\u00020\u0002H\u0016J\b\u0010\u0017\u001a\u00020\u0018H\u0016R\u000e\u0010\u0006\u001a\u00020\u0002X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0002X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\u00020\u00028VX\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0014\u0010\u0003\u001a\u00020\u00028VX\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\t¨\u0006\u0019"}, d2 = {"Lkotlin/ranges/ClosedDoubleRange;", "Lkotlin/ranges/ClosedFloatingPointRange;", "", "start", "endInclusive", "(DD)V", "_endInclusive", "_start", "getEndInclusive", "()Ljava/lang/Double;", "getStart", "contains", "", "value", "equals", "other", "", "hashCode", "", "isEmpty", "lessThanOrEquals", "a", "b", "toString", "", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
/* compiled from: Ranges.kt */
final class ClosedDoubleRange implements ClosedFloatingPointRange<Double> {
    private final double _endInclusive;
    private final double _start;

    public boolean lessThanOrEquals(double d, double d2) {
        return d <= d2;
    }

    public ClosedDoubleRange(double d, double d2) {
        this._start = d;
        this._endInclusive = d2;
    }

    @NotNull
    public Double getStart() {
        return Double.valueOf(this._start);
    }

    @NotNull
    public Double getEndInclusive() {
        return Double.valueOf(this._endInclusive);
    }

    public boolean contains(double d) {
        return (d < this._start || d > this._endInclusive) ? 0.0d : Double.MIN_VALUE;
    }

    public boolean isEmpty() {
        return this._start > this._endInclusive;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(@org.jetbrains.annotations.Nullable java.lang.Object r5) {
        /*
        r4 = this;
        r0 = r5 instanceof kotlin.ranges.ClosedDoubleRange;
        if (r0 == 0) goto L_0x0027;
    L_0x0004:
        r0 = r4.isEmpty();
        if (r0 == 0) goto L_0x0013;
    L_0x000a:
        r0 = r5;
        r0 = (kotlin.ranges.ClosedDoubleRange) r0;
        r0 = r0.isEmpty();
        if (r0 != 0) goto L_0x0025;
    L_0x0013:
        r0 = r4._start;
        r5 = (kotlin.ranges.ClosedDoubleRange) r5;
        r2 = r5._start;
        r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r0 != 0) goto L_0x0027;
    L_0x001d:
        r0 = r4._endInclusive;
        r2 = r5._endInclusive;
        r5 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r5 != 0) goto L_0x0027;
    L_0x0025:
        r5 = 1;
        goto L_0x0028;
    L_0x0027:
        r5 = 0;
    L_0x0028:
        return r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.ranges.ClosedDoubleRange.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        return isEmpty() ? -1 : (Double.valueOf(this._start).hashCode() * 31) + Double.valueOf(this._endInclusive).hashCode();
    }

    @NotNull
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this._start);
        stringBuilder.append("..");
        stringBuilder.append(this._endInclusive);
        return stringBuilder.toString();
    }
}
