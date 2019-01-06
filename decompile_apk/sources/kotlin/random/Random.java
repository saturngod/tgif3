package kotlin.random;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.internal.PlatformImplementationsKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.DoubleCompanionObject;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@SinceKotlin(version = "1.3")
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\b'\u0018\u0000 \u00182\u00020\u0001:\u0002\u0017\u0018B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H&J\b\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0016J$\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u000b\u001a\u00020\u00042\b\b\u0002\u0010\f\u001a\u00020\u0004H\u0016J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u0004H\u0016J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000fH\u0016J\u0018\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000fH\u0016J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\b\u0010\u0014\u001a\u00020\u0004H\u0016J\u0010\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u0004H\u0016J\u0018\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u0004H\u0016J\b\u0010\u0015\u001a\u00020\u0016H\u0016J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0010\u001a\u00020\u0016H\u0016J\u0018\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0011\u001a\u00020\u00162\u0006\u0010\u0010\u001a\u00020\u0016H\u0016¨\u0006\u0019"}, d2 = {"Lkotlin/random/Random;", "", "()V", "nextBits", "", "bitCount", "nextBoolean", "", "nextBytes", "", "array", "fromIndex", "toIndex", "size", "nextDouble", "", "until", "from", "nextFloat", "", "nextInt", "nextLong", "", "Companion", "Default", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
/* compiled from: Random.kt */
public abstract class Random {
    @NotNull
    @JvmField
    public static final Companion Companion = Companion.INSTANCE;
    public static final Default Default = new Default();
    private static final Random defaultRandom = PlatformImplementationsKt.IMPLEMENTATIONS.defaultPlatformRandom();

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0016¨\u0006\u0006"}, d2 = {"Lkotlin/random/Random$Companion;", "Lkotlin/random/Random;", "()V", "nextBits", "", "bitCount", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Use Default companion object instead")
    /* compiled from: Random.kt */
    public static final class Companion extends Random {
        public static final Companion INSTANCE = new Companion();

        private Companion() {
        }

        public int nextBits(int i) {
            return Random.Default.nextBits(i);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0016J\b\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH\u0016J \u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\bH\u0016J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\bH\u0016J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0013H\u0016J\u0018\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0013H\u0016J\b\u0010\u0016\u001a\u00020\u0017H\u0016J\b\u0010\u0018\u001a\u00020\bH\u0016J\u0010\u0010\u0018\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\bH\u0016J\u0018\u0010\u0018\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\bH\u0016J\b\u0010\u0019\u001a\u00020\u001aH\u0016J\u0010\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0014\u001a\u00020\u001aH\u0016J\u0018\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0015\u001a\u00020\u001a2\u0006\u0010\u0014\u001a\u00020\u001aH\u0016R\u0016\u0010\u0003\u001a\u00020\u00048\u0006X\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0002R\u000e\u0010\u0006\u001a\u00020\u0001X\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lkotlin/random/Random$Default;", "Lkotlin/random/Random;", "()V", "Companion", "Lkotlin/random/Random$Companion;", "Companion$annotations", "defaultRandom", "nextBits", "", "bitCount", "nextBoolean", "", "nextBytes", "", "array", "fromIndex", "toIndex", "size", "nextDouble", "", "until", "from", "nextFloat", "", "nextInt", "nextLong", "", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
    /* compiled from: Random.kt */
    public static final class Default extends Random {
        @Deprecated(level = DeprecationLevel.HIDDEN, message = "Use Default companion object instead")
        public static /* synthetic */ void Companion$annotations() {
        }

        private Default() {
        }

        public int nextBits(int i) {
            return Random.defaultRandom.nextBits(i);
        }

        public int nextInt() {
            return Random.defaultRandom.nextInt();
        }

        public int nextInt(int i) {
            return Random.defaultRandom.nextInt(i);
        }

        public int nextInt(int i, int i2) {
            return Random.defaultRandom.nextInt(i, i2);
        }

        public long nextLong() {
            return Random.defaultRandom.nextLong();
        }

        public long nextLong(long j) {
            return Random.defaultRandom.nextLong(j);
        }

        public long nextLong(long j, long j2) {
            return Random.defaultRandom.nextLong(j, j2);
        }

        public boolean nextBoolean() {
            return Random.defaultRandom.nextBoolean();
        }

        public double nextDouble() {
            return Random.defaultRandom.nextDouble();
        }

        public double nextDouble(double d) {
            return Random.defaultRandom.nextDouble(d);
        }

        public double nextDouble(double d, double d2) {
            return Random.defaultRandom.nextDouble(d, d2);
        }

        public float nextFloat() {
            return Random.defaultRandom.nextFloat();
        }

        @NotNull
        public byte[] nextBytes(@NotNull byte[] bArr) {
            Intrinsics.checkParameterIsNotNull(bArr, "array");
            return Random.defaultRandom.nextBytes(bArr);
        }

        @NotNull
        public byte[] nextBytes(int i) {
            return Random.defaultRandom.nextBytes(i);
        }

        @NotNull
        public byte[] nextBytes(@NotNull byte[] bArr, int i, int i2) {
            Intrinsics.checkParameterIsNotNull(bArr, "array");
            return Random.defaultRandom.nextBytes(bArr, i, i2);
        }
    }

    public abstract int nextBits(int i);

    public int nextInt() {
        return nextBits(32);
    }

    public int nextInt(int i) {
        return nextInt(0, i);
    }

    public int nextInt(int i, int i2) {
        RandomKt.checkRangeBounds(i, i2);
        int i3 = i2 - i;
        if (i3 <= 0) {
            if (i3 != Integer.MIN_VALUE) {
                while (true) {
                    i3 = nextInt();
                    if (i <= i3) {
                        if (i2 > i3) {
                            return i3;
                        }
                    }
                }
            }
        }
        if (((-i3) & i3) == i3) {
            i2 = nextBits(PlatformRandomKt.fastLog2(i3));
        } else {
            int i4;
            do {
                i2 = nextInt() >>> 1;
                i4 = i2 % i3;
            } while ((i2 - i4) + (i3 - 1) < 0);
            i2 = i4;
        }
        return i + i2;
    }

    public long nextLong() {
        return (((long) nextInt()) << 32) + ((long) nextInt());
    }

    public long nextLong(long j) {
        return nextLong(0, j);
    }

    public long nextLong(long j, long j2) {
        RandomKt.checkRangeBounds(j, j2);
        long j3 = j2 - j;
        if (j3 > 0) {
            if (((-j3) & j3) == j3) {
                j2 = (int) j3;
                int i = (int) (j3 >>> 32);
                if (j2 != null) {
                    j2 = ((long) nextBits(PlatformRandomKt.fastLog2(j2))) & 4294967295L;
                } else if (i == 1) {
                    j2 = ((long) nextInt()) & 4294967295L;
                } else {
                    j2 = (((long) nextBits(PlatformRandomKt.fastLog2(i))) << 32) + ((long) nextInt());
                }
            } else {
                long j4;
                do {
                    j2 = nextLong() >>> 1;
                    j4 = j2 % j3;
                } while ((j2 - j4) + (j3 - 1) < 0);
                j2 = j4;
            }
            return j + j2;
        }
        while (true) {
            j3 = nextLong();
            if (j <= j3) {
                if (j2 > j3) {
                    return j3;
                }
            }
        }
    }

    public boolean nextBoolean() {
        return nextBits(1) != 0;
    }

    public double nextDouble() {
        return PlatformRandomKt.doubleFromParts(nextBits(26), nextBits(27));
    }

    public double nextDouble(double d) {
        return nextDouble(0.0d, d);
    }

    public double nextDouble(double d, double d2) {
        RandomKt.checkRangeBounds(d, d2);
        double d3 = d2 - d;
        if (Double.isInfinite(d3)) {
            Object obj = 1;
            Object obj2 = (Double.isInfinite(d) || Double.isNaN(d)) ? null : 1;
            if (obj2 != null) {
                if (Double.isInfinite(d2) || Double.isNaN(d2)) {
                    obj = null;
                }
                if (obj != null) {
                    double d4 = (double) 2;
                    d3 = nextDouble() * ((d2 / d4) - (d / d4));
                    d = (d + d3) + d3;
                    return d < d2 ? Math.nextAfter(d2, DoubleCompanionObject.INSTANCE.getNEGATIVE_INFINITY()) : d;
                }
            }
        }
        d += nextDouble() * d3;
        if (d < d2) {
        }
    }

    public float nextFloat() {
        return ((float) nextBits(24)) / ((float) 16777216);
    }

    @NotNull
    public static /* synthetic */ byte[] nextBytes$default(Random random, byte[] bArr, int i, int i2, int i3, Object obj) {
        if (obj == null) {
            if ((i3 & 2) != null) {
                i = 0;
            }
            if ((i3 & 4) != 0) {
                i2 = bArr.length;
            }
            return random.nextBytes(bArr, i, i2);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: nextBytes");
    }

    @NotNull
    public byte[] nextBytes(@NotNull byte[] bArr, int i, int i2) {
        Object obj;
        int i3;
        int i4;
        Intrinsics.checkParameterIsNotNull(bArr, "array");
        int length = bArr.length;
        Object obj2 = 1;
        if (i >= 0) {
            if (length >= i) {
                length = bArr.length;
                if (i2 >= 0) {
                    if (length >= i2) {
                        obj = 1;
                        if (obj == null) {
                            if (i <= i2) {
                                obj2 = null;
                            }
                            if (obj2 == null) {
                                length = (i2 - i) / 4;
                                i3 = i;
                                for (i = 0; i < length; i++) {
                                    int nextInt = nextInt();
                                    bArr[i3] = (byte) nextInt;
                                    bArr[i3 + 1] = (byte) (nextInt >>> 8);
                                    bArr[i3 + 2] = (byte) (nextInt >>> 16);
                                    bArr[i3 + 3] = (byte) (nextInt >>> 24);
                                    i3 += 4;
                                }
                                i2 -= i3;
                                i = nextBits(i2 * 8);
                                for (i4 = 0; i4 < i2; i4++) {
                                    bArr[i3 + i4] = (byte) (i >>> (i4 * 8));
                                }
                                return bArr;
                            }
                            bArr = new StringBuilder();
                            bArr.append("fromIndex (");
                            bArr.append(i);
                            bArr.append(") must be not greater than toIndex (");
                            bArr.append(i2);
                            bArr.append(").");
                            throw ((Throwable) new IllegalArgumentException(bArr.toString().toString()));
                        }
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("fromIndex (");
                        stringBuilder.append(i);
                        stringBuilder.append(") or toIndex (");
                        stringBuilder.append(i2);
                        stringBuilder.append(") are out of range: 0..");
                        stringBuilder.append(bArr.length);
                        stringBuilder.append(46);
                        throw ((Throwable) new IllegalArgumentException(stringBuilder.toString().toString()));
                    }
                }
            }
        }
        obj = null;
        if (obj == null) {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("fromIndex (");
            stringBuilder2.append(i);
            stringBuilder2.append(") or toIndex (");
            stringBuilder2.append(i2);
            stringBuilder2.append(") are out of range: 0..");
            stringBuilder2.append(bArr.length);
            stringBuilder2.append(46);
            throw ((Throwable) new IllegalArgumentException(stringBuilder2.toString().toString()));
        }
        if (i <= i2) {
            obj2 = null;
        }
        if (obj2 == null) {
            bArr = new StringBuilder();
            bArr.append("fromIndex (");
            bArr.append(i);
            bArr.append(") must be not greater than toIndex (");
            bArr.append(i2);
            bArr.append(").");
            throw ((Throwable) new IllegalArgumentException(bArr.toString().toString()));
        }
        length = (i2 - i) / 4;
        i3 = i;
        for (i = 0; i < length; i++) {
            int nextInt2 = nextInt();
            bArr[i3] = (byte) nextInt2;
            bArr[i3 + 1] = (byte) (nextInt2 >>> 8);
            bArr[i3 + 2] = (byte) (nextInt2 >>> 16);
            bArr[i3 + 3] = (byte) (nextInt2 >>> 24);
            i3 += 4;
        }
        i2 -= i3;
        i = nextBits(i2 * 8);
        for (i4 = 0; i4 < i2; i4++) {
            bArr[i3 + i4] = (byte) (i >>> (i4 * 8));
        }
        return bArr;
    }

    @NotNull
    public byte[] nextBytes(@NotNull byte[] bArr) {
        Intrinsics.checkParameterIsNotNull(bArr, "array");
        return nextBytes(bArr, 0, bArr.length);
    }

    @NotNull
    public byte[] nextBytes(int i) {
        return nextBytes(new byte[i]);
    }
}
