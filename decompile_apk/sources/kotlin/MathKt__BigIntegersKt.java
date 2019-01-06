package kotlin;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import kotlin.internal.InlineOnly;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\f\u001a\r\u0010\u0003\u001a\u00020\u0001*\u00020\u0001H\n\u001a\u0015\u0010\u0004\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n\u001a\r\u0010\u0005\u001a\u00020\u0001*\u00020\u0001H\n\u001a\r\u0010\u0006\u001a\u00020\u0001*\u00020\u0001H\b\u001a\u0015\u0010\u0007\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n\u001a\u0015\u0010\b\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\f\u001a\u0015\u0010\t\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n\u001a\u0015\u0010\n\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n\u001a\u0015\u0010\u000b\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\f\u001a\u00020\rH\f\u001a\u0015\u0010\u000e\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\f\u001a\u00020\rH\f\u001a\u0015\u0010\u000f\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n\u001a\r\u0010\u0010\u001a\u00020\u0011*\u00020\u0001H\b\u001a!\u0010\u0010\u001a\u00020\u0011*\u00020\u00012\b\b\u0002\u0010\u0012\u001a\u00020\r2\b\b\u0002\u0010\u0013\u001a\u00020\u0014H\b\u001a\r\u0010\u0015\u001a\u00020\u0001*\u00020\rH\b\u001a\r\u0010\u0015\u001a\u00020\u0001*\u00020\u0016H\b\u001a\r\u0010\u0017\u001a\u00020\u0001*\u00020\u0001H\n\u001a\u0015\u0010\u0018\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\f¨\u0006\u0019"}, d2 = {"and", "Ljava/math/BigInteger;", "other", "dec", "div", "inc", "inv", "minus", "or", "plus", "rem", "shl", "n", "", "shr", "times", "toBigDecimal", "Ljava/math/BigDecimal;", "scale", "mathContext", "Ljava/math/MathContext;", "toBigInteger", "", "unaryMinus", "xor", "kotlin-stdlib"}, k = 5, mv = {1, 1, 13}, xi = 1, xs = "kotlin/MathKt")
/* compiled from: BigIntegers.kt */
class MathKt__BigIntegersKt extends MathKt__BigDecimalsKt {
    @InlineOnly
    private static final BigInteger plus(@NotNull BigInteger bigInteger, BigInteger bigInteger2) {
        Intrinsics.checkParameterIsNotNull(bigInteger, "receiver$0");
        bigInteger = bigInteger.add(bigInteger2);
        Intrinsics.checkExpressionValueIsNotNull(bigInteger, "this.add(other)");
        return bigInteger;
    }

    @InlineOnly
    private static final BigInteger minus(@NotNull BigInteger bigInteger, BigInteger bigInteger2) {
        Intrinsics.checkParameterIsNotNull(bigInteger, "receiver$0");
        bigInteger = bigInteger.subtract(bigInteger2);
        Intrinsics.checkExpressionValueIsNotNull(bigInteger, "this.subtract(other)");
        return bigInteger;
    }

    @InlineOnly
    private static final BigInteger times(@NotNull BigInteger bigInteger, BigInteger bigInteger2) {
        Intrinsics.checkParameterIsNotNull(bigInteger, "receiver$0");
        bigInteger = bigInteger.multiply(bigInteger2);
        Intrinsics.checkExpressionValueIsNotNull(bigInteger, "this.multiply(other)");
        return bigInteger;
    }

    @InlineOnly
    private static final BigInteger div(@NotNull BigInteger bigInteger, BigInteger bigInteger2) {
        Intrinsics.checkParameterIsNotNull(bigInteger, "receiver$0");
        bigInteger = bigInteger.divide(bigInteger2);
        Intrinsics.checkExpressionValueIsNotNull(bigInteger, "this.divide(other)");
        return bigInteger;
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final BigInteger rem(@NotNull BigInteger bigInteger, BigInteger bigInteger2) {
        Intrinsics.checkParameterIsNotNull(bigInteger, "receiver$0");
        bigInteger = bigInteger.remainder(bigInteger2);
        Intrinsics.checkExpressionValueIsNotNull(bigInteger, "this.remainder(other)");
        return bigInteger;
    }

    @InlineOnly
    private static final BigInteger unaryMinus(@NotNull BigInteger bigInteger) {
        Intrinsics.checkParameterIsNotNull(bigInteger, "receiver$0");
        bigInteger = bigInteger.negate();
        Intrinsics.checkExpressionValueIsNotNull(bigInteger, "this.negate()");
        return bigInteger;
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigInteger inc(@NotNull BigInteger bigInteger) {
        Intrinsics.checkParameterIsNotNull(bigInteger, "receiver$0");
        bigInteger = bigInteger.add(BigInteger.ONE);
        Intrinsics.checkExpressionValueIsNotNull(bigInteger, "this.add(BigInteger.ONE)");
        return bigInteger;
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigInteger dec(@NotNull BigInteger bigInteger) {
        Intrinsics.checkParameterIsNotNull(bigInteger, "receiver$0");
        bigInteger = bigInteger.subtract(BigInteger.ONE);
        Intrinsics.checkExpressionValueIsNotNull(bigInteger, "this.subtract(BigInteger.ONE)");
        return bigInteger;
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigInteger inv(@NotNull BigInteger bigInteger) {
        bigInteger = bigInteger.not();
        Intrinsics.checkExpressionValueIsNotNull(bigInteger, "this.not()");
        return bigInteger;
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigInteger and(@NotNull BigInteger bigInteger, BigInteger bigInteger2) {
        bigInteger = bigInteger.and(bigInteger2);
        Intrinsics.checkExpressionValueIsNotNull(bigInteger, "this.and(other)");
        return bigInteger;
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigInteger or(@NotNull BigInteger bigInteger, BigInteger bigInteger2) {
        bigInteger = bigInteger.or(bigInteger2);
        Intrinsics.checkExpressionValueIsNotNull(bigInteger, "this.or(other)");
        return bigInteger;
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigInteger xor(@NotNull BigInteger bigInteger, BigInteger bigInteger2) {
        bigInteger = bigInteger.xor(bigInteger2);
        Intrinsics.checkExpressionValueIsNotNull(bigInteger, "this.xor(other)");
        return bigInteger;
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigInteger shl(@NotNull BigInteger bigInteger, int i) {
        bigInteger = bigInteger.shiftLeft(i);
        Intrinsics.checkExpressionValueIsNotNull(bigInteger, "this.shiftLeft(n)");
        return bigInteger;
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigInteger shr(@NotNull BigInteger bigInteger, int i) {
        bigInteger = bigInteger.shiftRight(i);
        Intrinsics.checkExpressionValueIsNotNull(bigInteger, "this.shiftRight(n)");
        return bigInteger;
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigInteger toBigInteger(int i) {
        i = BigInteger.valueOf((long) i);
        Intrinsics.checkExpressionValueIsNotNull(i, "BigInteger.valueOf(this.toLong())");
        return i;
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigInteger toBigInteger(long j) {
        j = BigInteger.valueOf(j);
        Intrinsics.checkExpressionValueIsNotNull(j, "BigInteger.valueOf(this)");
        return j;
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigDecimal toBigDecimal(@NotNull BigInteger bigInteger) {
        return new BigDecimal(bigInteger);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    static /* synthetic */ BigDecimal toBigDecimal$default(BigInteger bigInteger, int i, MathContext mathContext, int i2, Object obj) {
        if ((i2 & 1) != null) {
            i = 0;
        }
        if ((i2 & 2) != 0) {
            mathContext = MathContext.UNLIMITED;
            Intrinsics.checkExpressionValueIsNotNull(mathContext, "MathContext.UNLIMITED");
        }
        return new BigDecimal(bigInteger, i, mathContext);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigDecimal toBigDecimal(@NotNull BigInteger bigInteger, int i, MathContext mathContext) {
        return new BigDecimal(bigInteger, i, mathContext);
    }
}
