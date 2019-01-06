package kotlin.ranges;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.ClosedRange.DefaultImpls;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000f\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0012\u0018\u0000*\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u0015\u0012\u0006\u0010\u0004\u001a\u00028\u0000\u0012\u0006\u0010\u0005\u001a\u00028\u0000¢\u0006\u0002\u0010\u0006J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0002J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u0012H\u0016R\u0016\u0010\u0005\u001a\u00028\u0000X\u0004¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0007\u0010\bR\u0016\u0010\u0004\u001a\u00028\u0000X\u0004¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\n\u0010\b¨\u0006\u0013"}, d2 = {"Lkotlin/ranges/ComparableRange;", "T", "", "Lkotlin/ranges/ClosedRange;", "start", "endInclusive", "(Ljava/lang/Comparable;Ljava/lang/Comparable;)V", "getEndInclusive", "()Ljava/lang/Comparable;", "Ljava/lang/Comparable;", "getStart", "equals", "", "other", "", "hashCode", "", "toString", "", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
/* compiled from: Ranges.kt */
class ComparableRange<T extends Comparable<? super T>> implements ClosedRange<T> {
    @NotNull
    private final T endInclusive;
    @NotNull
    private final T start;

    public ComparableRange(@NotNull T t, @NotNull T t2) {
        Intrinsics.checkParameterIsNotNull(t, "start");
        Intrinsics.checkParameterIsNotNull(t2, "endInclusive");
        this.start = t;
        this.endInclusive = t2;
    }

    public boolean contains(@NotNull T t) {
        Intrinsics.checkParameterIsNotNull(t, "value");
        return DefaultImpls.contains(this, t);
    }

    public boolean isEmpty() {
        return DefaultImpls.isEmpty(this);
    }

    @NotNull
    public T getStart() {
        return this.start;
    }

    @NotNull
    public T getEndInclusive() {
        return this.endInclusive;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(@org.jetbrains.annotations.Nullable java.lang.Object r3) {
        /*
        r2 = this;
        r0 = r3 instanceof kotlin.ranges.ComparableRange;
        if (r0 == 0) goto L_0x0033;
    L_0x0004:
        r0 = r2.isEmpty();
        if (r0 == 0) goto L_0x0013;
    L_0x000a:
        r0 = r3;
        r0 = (kotlin.ranges.ComparableRange) r0;
        r0 = r0.isEmpty();
        if (r0 != 0) goto L_0x0031;
    L_0x0013:
        r0 = r2.getStart();
        r3 = (kotlin.ranges.ComparableRange) r3;
        r1 = r3.getStart();
        r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1);
        if (r0 == 0) goto L_0x0033;
    L_0x0023:
        r0 = r2.getEndInclusive();
        r3 = r3.getEndInclusive();
        r3 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r3);
        if (r3 == 0) goto L_0x0033;
    L_0x0031:
        r3 = 1;
        goto L_0x0034;
    L_0x0033:
        r3 = 0;
    L_0x0034:
        return r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.ranges.ComparableRange.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        return isEmpty() ? -1 : (getStart().hashCode() * 31) + getEndInclusive().hashCode();
    }

    @NotNull
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getStart());
        stringBuilder.append("..");
        stringBuilder.append(getEndInclusive());
        return stringBuilder.toString();
    }
}
