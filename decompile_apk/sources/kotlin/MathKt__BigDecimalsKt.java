package kotlin;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import kotlin.internal.InlineOnly;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0010\b\n\u0002\u0010\t\n\u0002\b\u0002\u001a\r\u0010\u0000\u001a\u00020\u0001*\u00020\u0001H\n\u001a\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\n\u001a\r\u0010\u0004\u001a\u00020\u0001*\u00020\u0001H\n\u001a\u0015\u0010\u0005\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\n\u001a\u0015\u0010\u0006\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\n\u001a\u0015\u0010\u0007\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\n\u001a\u0015\u0010\b\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\n\u001a\u0015\u0010\t\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\n\u001a\r\u0010\n\u001a\u00020\u0001*\u00020\u000bH\b\u001a\u0015\u0010\n\u001a\u00020\u0001*\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\b\u001a\r\u0010\n\u001a\u00020\u0001*\u00020\u000eH\b\u001a\u0015\u0010\n\u001a\u00020\u0001*\u00020\u000e2\u0006\u0010\f\u001a\u00020\rH\b\u001a\r\u0010\n\u001a\u00020\u0001*\u00020\u000fH\b\u001a\u0015\u0010\n\u001a\u00020\u0001*\u00020\u000f2\u0006\u0010\f\u001a\u00020\rH\b\u001a\r\u0010\n\u001a\u00020\u0001*\u00020\u0010H\b\u001a\u0015\u0010\n\u001a\u00020\u0001*\u00020\u00102\u0006\u0010\f\u001a\u00020\rH\b\u001a\r\u0010\u0011\u001a\u00020\u0001*\u00020\u0001H\n¨\u0006\u0012"}, d2 = {"dec", "Ljava/math/BigDecimal;", "div", "other", "inc", "minus", "mod", "plus", "rem", "times", "toBigDecimal", "", "mathContext", "Ljava/math/MathContext;", "", "", "", "unaryMinus", "kotlin-stdlib"}, k = 5, mv = {1, 1, 13}, xi = 1, xs = "kotlin/MathKt")
/* compiled from: BigDecimals.kt */
class MathKt__BigDecimalsKt {
    @InlineOnly
    private static final BigDecimal plus(@NotNull BigDecimal bigDecimal, BigDecimal bigDecimal2) {
        Intrinsics.checkParameterIsNotNull(bigDecimal, "receiver$0");
        bigDecimal = bigDecimal.add(bigDecimal2);
        Intrinsics.checkExpressionValueIsNotNull(bigDecimal, "this.add(other)");
        return bigDecimal;
    }

    @InlineOnly
    private static final BigDecimal minus(@NotNull BigDecimal bigDecimal, BigDecimal bigDecimal2) {
        Intrinsics.checkParameterIsNotNull(bigDecimal, "receiver$0");
        bigDecimal = bigDecimal.subtract(bigDecimal2);
        Intrinsics.checkExpressionValueIsNotNull(bigDecimal, "this.subtract(other)");
        return bigDecimal;
    }

    @InlineOnly
    private static final BigDecimal times(@NotNull BigDecimal bigDecimal, BigDecimal bigDecimal2) {
        Intrinsics.checkParameterIsNotNull(bigDecimal, "receiver$0");
        bigDecimal = bigDecimal.multiply(bigDecimal2);
        Intrinsics.checkExpressionValueIsNotNull(bigDecimal, "this.multiply(other)");
        return bigDecimal;
    }

    @InlineOnly
    private static final BigDecimal div(@NotNull BigDecimal bigDecimal, BigDecimal bigDecimal2) {
        Intrinsics.checkParameterIsNotNull(bigDecimal, "receiver$0");
        bigDecimal = bigDecimal.divide(bigDecimal2, RoundingMode.HALF_EVEN);
        Intrinsics.checkExpressionValueIsNotNull(bigDecimal, "this.divide(other, RoundingMode.HALF_EVEN)");
        return bigDecimal;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use rem(other) instead", replaceWith = @ReplaceWith(expression = "rem(other)", imports = {}))
    @InlineOnly
    private static final BigDecimal mod(@NotNull BigDecimal bigDecimal, BigDecimal bigDecimal2) {
        Intrinsics.checkParameterIsNotNull(bigDecimal, "receiver$0");
        bigDecimal = bigDecimal.remainder(bigDecimal2);
        Intrinsics.checkExpressionValueIsNotNull(bigDecimal, "this.remainder(other)");
        return bigDecimal;
    }

    @InlineOnly
    private static final BigDecimal rem(@NotNull BigDecimal bigDecimal, BigDecimal bigDecimal2) {
        Intrinsics.checkParameterIsNotNull(bigDecimal, "receiver$0");
        bigDecimal = bigDecimal.remainder(bigDecimal2);
        Intrinsics.checkExpressionValueIsNotNull(bigDecimal, "this.remainder(other)");
        return bigDecimal;
    }

    @InlineOnly
    private static final BigDecimal unaryMinus(@NotNull BigDecimal bigDecimal) {
        Intrinsics.checkParameterIsNotNull(bigDecimal, "receiver$0");
        bigDecimal = bigDecimal.negate();
        Intrinsics.checkExpressionValueIsNotNull(bigDecimal, "this.negate()");
        return bigDecimal;
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigDecimal inc(@NotNull BigDecimal bigDecimal) {
        Intrinsics.checkParameterIsNotNull(bigDecimal, "receiver$0");
        bigDecimal = bigDecimal.add(BigDecimal.ONE);
        Intrinsics.checkExpressionValueIsNotNull(bigDecimal, "this.add(BigDecimal.ONE)");
        return bigDecimal;
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigDecimal dec(@NotNull BigDecimal bigDecimal) {
        Intrinsics.checkParameterIsNotNull(bigDecimal, "receiver$0");
        bigDecimal = bigDecimal.subtract(BigDecimal.ONE);
        Intrinsics.checkExpressionValueIsNotNull(bigDecimal, "this.subtract(BigDecimal.ONE)");
        return bigDecimal;
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigDecimal toBigDecimal(int i) {
        i = BigDecimal.valueOf((long) i);
        Intrinsics.checkExpressionValueIsNotNull(i, "BigDecimal.valueOf(this.toLong())");
        return i;
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigDecimal toBigDecimal(int i, MathContext mathContext) {
        return new BigDecimal(i, mathContext);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigDecimal toBigDecimal(long j) {
        j = BigDecimal.valueOf(j);
        Intrinsics.checkExpressionValueIsNotNull(j, "BigDecimal.valueOf(this)");
        return j;
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigDecimal toBigDecimal(long j, MathContext mathContext) {
        return new BigDecimal(j, mathContext);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigDecimal toBigDecimal(float f) {
        return new BigDecimal(String.valueOf(f));
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigDecimal toBigDecimal(float f, MathContext mathContext) {
        return new BigDecimal(String.valueOf(f), mathContext);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigDecimal toBigDecimal(double d) {
        return new BigDecimal(String.valueOf(d));
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigDecimal toBigDecimal(double d, MathContext mathContext) {
        return new BigDecimal(String.valueOf(d), mathContext);
    }
}
