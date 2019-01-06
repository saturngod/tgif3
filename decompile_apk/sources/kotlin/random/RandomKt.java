package kotlin.random;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.LongCompanionObject;
import kotlin.ranges.IntRange;
import kotlin.ranges.LongRange;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0007\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0004H\u0007\u001a\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0000\u001a\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\fH\u0000\u001a\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0003H\u0000\u001a\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u0004H\u0000\u001a\u0014\u0010\r\u001a\u00020\u0003*\u00020\u00012\u0006\u0010\u000e\u001a\u00020\u000fH\u0007\u001a\u0014\u0010\u0010\u001a\u00020\u0004*\u00020\u00012\u0006\u0010\u000e\u001a\u00020\u0011H\u0007\u001a\u0014\u0010\u0012\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u0003H\u0000¨\u0006\u0014"}, d2 = {"Random", "Lkotlin/random/Random;", "seed", "", "", "boundsErrorMessage", "", "from", "", "until", "checkRangeBounds", "", "", "nextInt", "range", "Lkotlin/ranges/IntRange;", "nextLong", "Lkotlin/ranges/LongRange;", "takeUpperBits", "bitCount", "kotlin-stdlib"}, k = 2, mv = {1, 1, 13})
/* compiled from: Random.kt */
public final class RandomKt {
    public static final int takeUpperBits(int i, int i2) {
        return (i >>> (32 - i2)) & ((-i2) >> 31);
    }

    @NotNull
    @SinceKotlin(version = "1.3")
    public static final Random Random(int i) {
        return new XorWowRandom(i, i >> 31);
    }

    @NotNull
    @SinceKotlin(version = "1.3")
    public static final Random Random(long j) {
        return new XorWowRandom((int) j, (int) (j >> 32));
    }

    @SinceKotlin(version = "1.3")
    public static final int nextInt(@NotNull Random random, @NotNull IntRange intRange) {
        Intrinsics.checkParameterIsNotNull(random, "receiver$0");
        Intrinsics.checkParameterIsNotNull(intRange, "range");
        if (intRange.isEmpty()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Cannot get random in empty range: ");
            stringBuilder.append(intRange);
            throw ((Throwable) new IllegalArgumentException(stringBuilder.toString()));
        } else if (intRange.getLast() < Integer.MAX_VALUE) {
            return random.nextInt(intRange.getFirst(), intRange.getLast() + 1);
        } else {
            if (intRange.getFirst() > Integer.MIN_VALUE) {
                return random.nextInt(intRange.getFirst() - 1, intRange.getLast()) + 1;
            }
            return random.nextInt();
        }
    }

    @SinceKotlin(version = "1.3")
    public static final long nextLong(@NotNull Random random, @NotNull LongRange longRange) {
        Intrinsics.checkParameterIsNotNull(random, "receiver$0");
        Intrinsics.checkParameterIsNotNull(longRange, "range");
        if (longRange.isEmpty()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Cannot get random in empty range: ");
            stringBuilder.append(longRange);
            throw ((Throwable) new IllegalArgumentException(stringBuilder.toString()));
        } else if (longRange.getLast() < LongCompanionObject.MAX_VALUE) {
            return random.nextLong(longRange.getStart().longValue(), longRange.getEndInclusive().longValue() + 1);
        } else {
            if (longRange.getStart().longValue() > Long.MIN_VALUE) {
                return random.nextLong(longRange.getStart().longValue() - 1, longRange.getEndInclusive().longValue()) + 1;
            }
            return random.nextLong();
        }
    }

    public static final void checkRangeBounds(int i, int i2) {
        if ((i2 > i ? 1 : null) == null) {
            throw ((Throwable) new IllegalArgumentException(boundsErrorMessage(Integer.valueOf(i), Integer.valueOf(i2)).toString()));
        }
    }

    public static final void checkRangeBounds(long j, long j2) {
        if ((j2 > j ? 1 : null) == null) {
            throw new IllegalArgumentException(boundsErrorMessage(Long.valueOf(j), Long.valueOf(j2)).toString());
        }
    }

    public static final void checkRangeBounds(double d, double d2) {
        if ((d2 > d ? 1 : null) == null) {
            throw new IllegalArgumentException(boundsErrorMessage(Double.valueOf(d), Double.valueOf(d2)).toString());
        }
    }

    @NotNull
    public static final String boundsErrorMessage(@NotNull Object obj, @NotNull Object obj2) {
        Intrinsics.checkParameterIsNotNull(obj, "from");
        Intrinsics.checkParameterIsNotNull(obj2, "until");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Random range is empty: [");
        stringBuilder.append(obj);
        stringBuilder.append(", ");
        stringBuilder.append(obj2);
        stringBuilder.append(").");
        return stringBuilder.toString();
    }
}
