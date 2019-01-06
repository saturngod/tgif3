package kotlin;

import kotlin.internal.InlineOnly;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\n\n\u0002\b\u0002\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\bø\u0001\u0000¢\u0006\u0002\u0010\u0003\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u0004H\bø\u0001\u0000¢\u0006\u0002\u0010\u0005\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u0006H\bø\u0001\u0000¢\u0006\u0002\u0010\u0007\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\bH\bø\u0001\u0000¢\u0006\u0002\u0010\t\u0002\u0004\n\u0002\b\u0019¨\u0006\n"}, d2 = {"toULong", "Lkotlin/ULong;", "", "(B)J", "", "(I)J", "", "(J)J", "", "(S)J", "kotlin-stdlib"}, k = 2, mv = {1, 1, 13})
/* compiled from: ULong.kt */
public final class ULongKt {
    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final long toULong(byte b) {
        return ULong.constructor-impl((long) b);
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final long toULong(short s) {
        return ULong.constructor-impl((long) s);
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final long toULong(int i) {
        return ULong.constructor-impl((long) i);
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final long toULong(long j) {
        return ULong.constructor-impl(j);
    }
}
