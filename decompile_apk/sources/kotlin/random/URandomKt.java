package kotlin.random;

import kotlin.ExperimentalUnsignedTypes;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.UByteArray;
import kotlin.UInt;
import kotlin.ULong;
import kotlin.UnsignedKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.UIntRange;
import kotlin.ranges.ULongRange;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\"\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\u0001ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006\u001a\"\u0010\u0007\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\bH\u0001ø\u0001\u0000¢\u0006\u0004\b\t\u0010\n\u001a\u001c\u0010\u000b\u001a\u00020\f*\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0010\u001a\u001e\u0010\u000b\u001a\u00020\f*\u00020\r2\u0006\u0010\u0011\u001a\u00020\fH\u0007ø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u0013\u001a2\u0010\u000b\u001a\u00020\f*\u00020\r2\u0006\u0010\u0011\u001a\u00020\f2\b\b\u0002\u0010\u0014\u001a\u00020\u000f2\b\b\u0002\u0010\u0015\u001a\u00020\u000fH\u0007ø\u0001\u0000¢\u0006\u0004\b\u0016\u0010\u0017\u001a\u0014\u0010\u0018\u001a\u00020\u0003*\u00020\rH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0019\u001a\u001e\u0010\u0018\u001a\u00020\u0003*\u00020\r2\u0006\u0010\u0004\u001a\u00020\u0003H\u0007ø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u001b\u001a&\u0010\u0018\u001a\u00020\u0003*\u00020\r2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\u0007ø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001d\u001a\u001c\u0010\u0018\u001a\u00020\u0003*\u00020\r2\u0006\u0010\u001e\u001a\u00020\u001fH\u0007ø\u0001\u0000¢\u0006\u0002\u0010 \u001a\u0014\u0010!\u001a\u00020\b*\u00020\rH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\"\u001a\u001e\u0010!\u001a\u00020\b*\u00020\r2\u0006\u0010\u0004\u001a\u00020\bH\u0007ø\u0001\u0000¢\u0006\u0004\b#\u0010$\u001a&\u0010!\u001a\u00020\b*\u00020\r2\u0006\u0010\u0002\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\bH\u0007ø\u0001\u0000¢\u0006\u0004\b%\u0010&\u001a\u001c\u0010!\u001a\u00020\b*\u00020\r2\u0006\u0010\u001e\u001a\u00020'H\u0007ø\u0001\u0000¢\u0006\u0002\u0010(\u0002\u0004\n\u0002\b\u0019¨\u0006)"}, d2 = {"checkUIntRangeBounds", "", "from", "Lkotlin/UInt;", "until", "checkUIntRangeBounds-J1ME1BU", "(II)V", "checkULongRangeBounds", "Lkotlin/ULong;", "checkULongRangeBounds-eb3DHEI", "(JJ)V", "nextUBytes", "Lkotlin/UByteArray;", "Lkotlin/random/Random;", "size", "", "(Lkotlin/random/Random;I)[B", "array", "nextUBytes-EVgfTAA", "(Lkotlin/random/Random;[B)[B", "fromIndex", "toIndex", "nextUBytes-Wvrt4B4", "(Lkotlin/random/Random;[BII)[B", "nextUInt", "(Lkotlin/random/Random;)I", "nextUInt-qCasIEU", "(Lkotlin/random/Random;I)I", "nextUInt-a8DCA5k", "(Lkotlin/random/Random;II)I", "range", "Lkotlin/ranges/UIntRange;", "(Lkotlin/random/Random;Lkotlin/ranges/UIntRange;)I", "nextULong", "(Lkotlin/random/Random;)J", "nextULong-V1Xi4fY", "(Lkotlin/random/Random;J)J", "nextULong-jmpaW-c", "(Lkotlin/random/Random;JJ)J", "Lkotlin/ranges/ULongRange;", "(Lkotlin/random/Random;Lkotlin/ranges/ULongRange;)J", "kotlin-stdlib"}, k = 2, mv = {1, 1, 13})
/* compiled from: URandom.kt */
public final class URandomKt {
    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    public static final int nextUInt(@NotNull Random random) {
        Intrinsics.checkParameterIsNotNull(random, "receiver$0");
        return UInt.constructor-impl(random.nextInt());
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    public static final int nextUInt-qCasIEU(@NotNull Random random, int i) {
        Intrinsics.checkParameterIsNotNull(random, "receiver$0");
        return nextUInt-a8DCA5k(random, 0, i);
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    public static final int nextUInt-a8DCA5k(@NotNull Random random, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(random, "receiver$0");
        checkUIntRangeBounds-J1ME1BU(i, i2);
        return UInt.constructor-impl(random.nextInt(i ^ Integer.MIN_VALUE, i2 ^ Integer.MIN_VALUE) ^ -2147483648);
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    public static final int nextUInt(@NotNull Random random, @NotNull UIntRange uIntRange) {
        Intrinsics.checkParameterIsNotNull(random, "receiver$0");
        Intrinsics.checkParameterIsNotNull(uIntRange, "range");
        if (uIntRange.isEmpty()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Cannot get random in empty range: ");
            stringBuilder.append(uIntRange);
            throw ((Throwable) new IllegalArgumentException(stringBuilder.toString()));
        } else if (UnsignedKt.uintCompare(uIntRange.getLast(), -1) < 0) {
            return nextUInt-a8DCA5k(random, uIntRange.getFirst(), UInt.constructor-impl(uIntRange.getLast() + 1));
        } else {
            if (UnsignedKt.uintCompare(uIntRange.getFirst(), 0) > 0) {
                return UInt.constructor-impl(nextUInt-a8DCA5k(random, UInt.constructor-impl(uIntRange.getFirst() - 1), uIntRange.getLast()) + 1);
            }
            return nextUInt(random);
        }
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    public static final long nextULong(@NotNull Random random) {
        Intrinsics.checkParameterIsNotNull(random, "receiver$0");
        return ULong.constructor-impl(random.nextLong());
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    public static final long nextULong-V1Xi4fY(@NotNull Random random, long j) {
        Intrinsics.checkParameterIsNotNull(random, "receiver$0");
        return nextULong-jmpaW-c(random, 0, j);
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    public static final long nextULong-jmpaW-c(@NotNull Random random, long j, long j2) {
        Intrinsics.checkParameterIsNotNull(random, "receiver$0");
        checkULongRangeBounds-eb3DHEI(j, j2);
        return ULong.constructor-impl(random.nextLong(j ^ Long.MIN_VALUE, j2 ^ Long.MIN_VALUE) ^ -9223372036854775808);
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    public static final long nextULong(@NotNull Random random, @NotNull ULongRange uLongRange) {
        Intrinsics.checkParameterIsNotNull(random, "receiver$0");
        Intrinsics.checkParameterIsNotNull(uLongRange, "range");
        if (uLongRange.isEmpty()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Cannot get random in empty range: ");
            stringBuilder.append(uLongRange);
            throw ((Throwable) new IllegalArgumentException(stringBuilder.toString()));
        } else if (UnsignedKt.ulongCompare(uLongRange.getLast(), -1) < 0) {
            return nextULong-jmpaW-c(random, uLongRange.getFirst(), ULong.constructor-impl(uLongRange.getLast() + ULong.constructor-impl(((long) 1) & 4294967295L)));
        } else {
            if (UnsignedKt.ulongCompare(uLongRange.getFirst(), 0) <= 0) {
                return nextULong(random);
            }
            long j = ((long) 1) & 4294967295L;
            return ULong.constructor-impl(nextULong-jmpaW-c(random, ULong.constructor-impl(uLongRange.getFirst() - ULong.constructor-impl(j)), uLongRange.getLast()) + ULong.constructor-impl(j));
        }
    }

    @ExperimentalUnsignedTypes
    @NotNull
    @SinceKotlin(version = "1.3")
    public static final byte[] nextUBytes-EVgfTAA(@NotNull Random random, @NotNull byte[] bArr) {
        Intrinsics.checkParameterIsNotNull(random, "receiver$0");
        Intrinsics.checkParameterIsNotNull(bArr, "array");
        random.nextBytes(bArr);
        return bArr;
    }

    @ExperimentalUnsignedTypes
    @NotNull
    @SinceKotlin(version = "1.3")
    public static final byte[] nextUBytes(@NotNull Random random, int i) {
        Intrinsics.checkParameterIsNotNull(random, "receiver$0");
        return UByteArray.constructor-impl(random.nextBytes(i));
    }

    @ExperimentalUnsignedTypes
    @NotNull
    @SinceKotlin(version = "1.3")
    public static /* synthetic */ byte[] nextUBytes-Wvrt4B4$default(Random random, byte[] bArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != null) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = UByteArray.getSize-impl(bArr);
        }
        return nextUBytes-Wvrt4B4(random, bArr, i, i2);
    }

    @ExperimentalUnsignedTypes
    @NotNull
    @SinceKotlin(version = "1.3")
    public static final byte[] nextUBytes-Wvrt4B4(@NotNull Random random, @NotNull byte[] bArr, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(random, "receiver$0");
        Intrinsics.checkParameterIsNotNull(bArr, "array");
        random.nextBytes(bArr, i, i2);
        return bArr;
    }

    @ExperimentalUnsignedTypes
    public static final void checkUIntRangeBounds-J1ME1BU(int i, int i2) {
        if ((UnsignedKt.uintCompare(i2, i) > 0 ? 1 : null) == null) {
            throw ((Throwable) new IllegalArgumentException(RandomKt.boundsErrorMessage(new UInt(i), new UInt(i2)).toString()));
        }
    }

    @ExperimentalUnsignedTypes
    public static final void checkULongRangeBounds-eb3DHEI(long j, long j2) {
        if ((UnsignedKt.ulongCompare(j2, j) > 0 ? 1 : null) == null) {
            throw new IllegalArgumentException(RandomKt.boundsErrorMessage(new ULong(j), new ULong(j2)).toString());
        }
    }
}
