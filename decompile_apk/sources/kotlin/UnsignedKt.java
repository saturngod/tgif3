package kotlin;

import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0001\u001a\"\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0002\u001a\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u0005H\u0001ø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\u0007\u001a\"\u0010\b\u001a\u00020\u00052\u0006\u0010\u0002\u001a\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u0005H\u0001ø\u0001\u0000¢\u0006\u0004\b\t\u0010\u0007\u001a\u0018\u0010\n\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u000b2\u0006\u0010\u0003\u001a\u00020\u000bH\u0001\u001a\"\u0010\f\u001a\u00020\r2\u0006\u0010\u0002\u001a\u00020\r2\u0006\u0010\u0003\u001a\u00020\rH\u0001ø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000f\u001a\"\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0002\u001a\u00020\r2\u0006\u0010\u0003\u001a\u00020\rH\u0001ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u000f\u001a\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u000bH\u0000\u001a\u0018\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u000b2\u0006\u0010\u0015\u001a\u00020\u0001H\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u0016"}, d2 = {"uintCompare", "", "v1", "v2", "uintDivide", "Lkotlin/UInt;", "uintDivide-J1ME1BU", "(II)I", "uintRemainder", "uintRemainder-J1ME1BU", "ulongCompare", "", "ulongDivide", "Lkotlin/ULong;", "ulongDivide-eb3DHEI", "(JJ)J", "ulongRemainder", "ulongRemainder-eb3DHEI", "ulongToString", "", "v", "base", "kotlin-stdlib"}, k = 2, mv = {1, 1, 13})
@JvmName(name = "UnsignedKt")
/* compiled from: UnsignedUtils.kt */
public final class UnsignedKt {
    @PublishedApi
    public static final int ulongCompare(long j, long j2) {
        return ((j ^ Long.MIN_VALUE) > (j2 ^ Long.MIN_VALUE) ? 1 : ((j ^ Long.MIN_VALUE) == (j2 ^ Long.MIN_VALUE) ? 0 : -1));
    }

    @PublishedApi
    public static final int uintCompare(int i, int i2) {
        return Intrinsics.compare(i ^ Integer.MIN_VALUE, i2 ^ Integer.MIN_VALUE);
    }

    @PublishedApi
    public static final int uintDivide-J1ME1BU(int i, int i2) {
        return UInt.constructor-impl((int) ((((long) i) & 4294967295L) / (((long) i2) & -1)));
    }

    @PublishedApi
    public static final int uintRemainder-J1ME1BU(int i, int i2) {
        return UInt.constructor-impl((int) ((((long) i) & 4294967295L) % (((long) i2) & -1)));
    }

    @PublishedApi
    public static final long ulongDivide-eb3DHEI(long j, long j2) {
        if (j2 < 0) {
            return ulongCompare(j, j2) < null ? ULong.constructor-impl(0) : ULong.constructor-impl(1);
        } else if (j >= 0) {
            return ULong.constructor-impl(j / j2);
        } else {
            int i = 1;
            long j3 = ((j >>> 1) / j2) << 1;
            if (ulongCompare(ULong.constructor-impl(j - (j3 * j2)), ULong.constructor-impl(j2)) < null) {
                i = 0;
            }
            return ULong.constructor-impl(j3 + ((long) i));
        }
    }

    @PublishedApi
    public static final long ulongRemainder-eb3DHEI(long j, long j2) {
        if (j2 < 0) {
            if (ulongCompare(j, j2) >= 0) {
                j = ULong.constructor-impl(j - j2);
            }
            return j;
        } else if (j >= 0) {
            return ULong.constructor-impl(j % j2);
        } else {
            j -= (((j >>> 1) / j2) << 1) * j2;
            if (ulongCompare(ULong.constructor-impl(j), ULong.constructor-impl(j2)) < 0) {
                j2 = 0;
            }
            return ULong.constructor-impl(j - j2);
        }
    }

    @NotNull
    public static final String ulongToString(long j) {
        return ulongToString(j, 10);
    }

    @NotNull
    public static final String ulongToString(long j, int i) {
        if (j >= 0) {
            j = Long.toString(j, CharsKt__CharJVMKt.checkRadix(i));
            Intrinsics.checkExpressionValueIsNotNull(j, "java.lang.Long.toString(this, checkRadix(radix))");
            return j;
        }
        long j2 = (long) i;
        long j3 = ((j >>> 1) / j2) << 1;
        j -= j3 * j2;
        if (j >= j2) {
            j -= j2;
            j3++;
        }
        StringBuilder stringBuilder = new StringBuilder();
        String l = Long.toString(j3, CharsKt__CharJVMKt.checkRadix(i));
        Intrinsics.checkExpressionValueIsNotNull(l, "java.lang.Long.toString(this, checkRadix(radix))");
        stringBuilder.append(l);
        j = Long.toString(j, CharsKt__CharJVMKt.checkRadix(i));
        Intrinsics.checkExpressionValueIsNotNull(j, "java.lang.Long.toString(this, checkRadix(radix))");
        stringBuilder.append(j);
        return stringBuilder.toString();
    }
}
