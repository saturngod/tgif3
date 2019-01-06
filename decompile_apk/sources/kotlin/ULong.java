package kotlin;

import kotlin.internal.InlineOnly;
import kotlin.ranges.ULongRange;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@ExperimentalUnsignedTypes
@SinceKotlin(version = "1.3")
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u0005\n\u0002\b\b\n\u0002\u0010\n\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u000e\b@\u0018\u0000 e2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001eB\u0014\b\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005J\u001b\u0010\b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\fø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000bJ\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u000eH\nø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0010J\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0011H\nø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u0013J\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0015J\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0016H\nø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0018J\u0013\u0010\u0019\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u0005J\u001b\u0010\u001b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u000eH\nø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001dJ\u001b\u0010\u001b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0011H\nø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u001fJ\u001b\u0010\u001b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b \u0010\u000bJ\u001b\u0010\u001b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0016H\nø\u0001\u0000¢\u0006\u0004\b!\u0010\"J\u0013\u0010#\u001a\u00020$2\b\u0010\t\u001a\u0004\u0018\u00010%HÖ\u0003J\t\u0010&\u001a\u00020\rHÖ\u0001J\u0013\u0010'\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b(\u0010\u0005J\u0013\u0010)\u001a\u00020\u0000H\bø\u0001\u0000¢\u0006\u0004\b*\u0010\u0005J\u001b\u0010+\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u000eH\nø\u0001\u0000¢\u0006\u0004\b,\u0010\u001dJ\u001b\u0010+\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0011H\nø\u0001\u0000¢\u0006\u0004\b-\u0010\u001fJ\u001b\u0010+\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b.\u0010\u000bJ\u001b\u0010+\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0016H\nø\u0001\u0000¢\u0006\u0004\b/\u0010\"J\u001b\u00100\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\fø\u0001\u0000¢\u0006\u0004\b1\u0010\u000bJ\u001b\u00102\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u000eH\nø\u0001\u0000¢\u0006\u0004\b3\u0010\u001dJ\u001b\u00102\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0011H\nø\u0001\u0000¢\u0006\u0004\b4\u0010\u001fJ\u001b\u00102\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b5\u0010\u000bJ\u001b\u00102\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0016H\nø\u0001\u0000¢\u0006\u0004\b6\u0010\"J\u001b\u00107\u001a\u0002082\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b9\u0010:J\u001b\u0010;\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u000eH\nø\u0001\u0000¢\u0006\u0004\b<\u0010\u001dJ\u001b\u0010;\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0011H\nø\u0001\u0000¢\u0006\u0004\b=\u0010\u001fJ\u001b\u0010;\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b>\u0010\u000bJ\u001b\u0010;\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0016H\nø\u0001\u0000¢\u0006\u0004\b?\u0010\"J\u001b\u0010@\u001a\u00020\u00002\u0006\u0010A\u001a\u00020\rH\fø\u0001\u0000¢\u0006\u0004\bB\u0010\u001fJ\u001b\u0010C\u001a\u00020\u00002\u0006\u0010A\u001a\u00020\rH\fø\u0001\u0000¢\u0006\u0004\bD\u0010\u001fJ\u001b\u0010E\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u000eH\nø\u0001\u0000¢\u0006\u0004\bF\u0010\u001dJ\u001b\u0010E\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0011H\nø\u0001\u0000¢\u0006\u0004\bG\u0010\u001fJ\u001b\u0010E\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\bH\u0010\u000bJ\u001b\u0010E\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0016H\nø\u0001\u0000¢\u0006\u0004\bI\u0010\"J\u0010\u0010J\u001a\u00020KH\b¢\u0006\u0004\bL\u0010MJ\u0010\u0010N\u001a\u00020\rH\b¢\u0006\u0004\bO\u0010PJ\u0010\u0010Q\u001a\u00020\u0003H\b¢\u0006\u0004\bR\u0010\u0005J\u0010\u0010S\u001a\u00020TH\b¢\u0006\u0004\bU\u0010VJ\u000f\u0010W\u001a\u00020XH\u0016¢\u0006\u0004\bY\u0010ZJ\u0013\u0010[\u001a\u00020\u000eH\bø\u0001\u0000¢\u0006\u0004\b\\\u0010MJ\u0013\u0010]\u001a\u00020\u0011H\bø\u0001\u0000¢\u0006\u0004\b^\u0010PJ\u0013\u0010_\u001a\u00020\u0000H\bø\u0001\u0000¢\u0006\u0004\b`\u0010\u0005J\u0013\u0010a\u001a\u00020\u0016H\bø\u0001\u0000¢\u0006\u0004\bb\u0010VJ\u001b\u0010c\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\fø\u0001\u0000¢\u0006\u0004\bd\u0010\u000bR\u0016\u0010\u0002\u001a\u00020\u00038\u0000X\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0006\u0010\u0007ø\u0001\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006f"}, d2 = {"Lkotlin/ULong;", "", "data", "", "constructor-impl", "(J)J", "data$annotations", "()V", "and", "other", "and-VKZWuLQ", "(JJ)J", "compareTo", "", "Lkotlin/UByte;", "compareTo-7apg3OU", "(JB)I", "Lkotlin/UInt;", "compareTo-WZ4Q5Ns", "(JI)I", "compareTo-VKZWuLQ", "(JJ)I", "Lkotlin/UShort;", "compareTo-xj2QHRw", "(JS)I", "dec", "dec-impl", "div", "div-7apg3OU", "(JB)J", "div-WZ4Q5Ns", "(JI)J", "div-VKZWuLQ", "div-xj2QHRw", "(JS)J", "equals", "", "", "hashCode", "inc", "inc-impl", "inv", "inv-impl", "minus", "minus-7apg3OU", "minus-WZ4Q5Ns", "minus-VKZWuLQ", "minus-xj2QHRw", "or", "or-VKZWuLQ", "plus", "plus-7apg3OU", "plus-WZ4Q5Ns", "plus-VKZWuLQ", "plus-xj2QHRw", "rangeTo", "Lkotlin/ranges/ULongRange;", "rangeTo-VKZWuLQ", "(JJ)Lkotlin/ranges/ULongRange;", "rem", "rem-7apg3OU", "rem-WZ4Q5Ns", "rem-VKZWuLQ", "rem-xj2QHRw", "shl", "bitCount", "shl-impl", "shr", "shr-impl", "times", "times-7apg3OU", "times-WZ4Q5Ns", "times-VKZWuLQ", "times-xj2QHRw", "toByte", "", "toByte-impl", "(J)B", "toInt", "toInt-impl", "(J)I", "toLong", "toLong-impl", "toShort", "", "toShort-impl", "(J)S", "toString", "", "toString-impl", "(J)Ljava/lang/String;", "toUByte", "toUByte-impl", "toUInt", "toUInt-impl", "toULong", "toULong-impl", "toUShort", "toUShort-impl", "xor", "xor-VKZWuLQ", "Companion", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
/* compiled from: ULong.kt */
public final class ULong implements Comparable<ULong> {
    public static final Companion Companion = new Companion();
    public static final long MAX_VALUE = -1;
    public static final long MIN_VALUE = 0;
    public static final int SIZE_BITS = 64;
    public static final int SIZE_BYTES = 8;
    private final long data;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0013\u0010\u0003\u001a\u00020\u0004XTø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0005R\u0013\u0010\u0006\u001a\u00020\u0004XTø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0005R\u000e\u0010\u0007\u001a\u00020\bXT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bXT¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\n"}, d2 = {"Lkotlin/ULong$Companion;", "", "()V", "MAX_VALUE", "Lkotlin/ULong;", "J", "MIN_VALUE", "SIZE_BITS", "", "SIZE_BYTES", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
    /* compiled from: ULong.kt */
    public static final class Companion {
        private Companion() {
        }
    }

    @InlineOnly
    private int compareTo-VKZWuLQ(long j) {
        return compareTo-VKZWuLQ(this.data, j);
    }

    @PublishedApi
    public static long constructor-impl(long j) {
        return j;
    }

    @PublishedApi
    public static /* synthetic */ void data$annotations() {
    }

    public static boolean equals-impl(long j, @Nullable Object obj) {
        if (obj instanceof ULong) {
            if ((j == ((ULong) obj).unbox-impl() ? 1 : null) != null) {
                return true;
            }
        }
        return false;
    }

    public static final boolean equals-impl0(long j, long j2) {
        throw null;
    }

    public static int hashCode-impl(long j) {
        return (int) (j ^ (j >>> 32));
    }

    @InlineOnly
    private static final byte toByte-impl(long j) {
        return (byte) ((int) j);
    }

    @InlineOnly
    private static final int toInt-impl(long j) {
        return (int) j;
    }

    @InlineOnly
    private static final long toLong-impl(long j) {
        return j;
    }

    @InlineOnly
    private static final short toShort-impl(long j) {
        return (short) ((int) j);
    }

    @InlineOnly
    private static final long toULong-impl(long j) {
        return j;
    }

    public boolean equals(Object obj) {
        return equals-impl(this.data, obj);
    }

    public int hashCode() {
        return hashCode-impl(this.data);
    }

    @NotNull
    public String toString() {
        return toString-impl(this.data);
    }

    public final /* synthetic */ long unbox-impl() {
        return this.data;
    }

    @PublishedApi
    private /* synthetic */ ULong(long j) {
        this.data = j;
    }

    public /* bridge */ /* synthetic */ int compareTo(Object obj) {
        return compareTo-VKZWuLQ(((ULong) obj).unbox-impl());
    }

    @InlineOnly
    private static final int compareTo-7apg3OU(long j, byte b) {
        return UnsignedKt.ulongCompare(j, constructor-impl(((long) b) & 255));
    }

    @InlineOnly
    private static final int compareTo-xj2QHRw(long j, short s) {
        return UnsignedKt.ulongCompare(j, constructor-impl(((long) s) & 65535));
    }

    @InlineOnly
    private static final int compareTo-WZ4Q5Ns(long j, int i) {
        return UnsignedKt.ulongCompare(j, constructor-impl(((long) i) & 4294967295L));
    }

    @InlineOnly
    private static int compareTo-VKZWuLQ(long j, long j2) {
        return UnsignedKt.ulongCompare(j, j2);
    }

    @InlineOnly
    private static final long plus-7apg3OU(long j, byte b) {
        return constructor-impl(j + constructor-impl(((long) b) & 255));
    }

    @InlineOnly
    private static final long plus-xj2QHRw(long j, short s) {
        return constructor-impl(j + constructor-impl(((long) s) & 65535));
    }

    @InlineOnly
    private static final long plus-WZ4Q5Ns(long j, int i) {
        return constructor-impl(j + constructor-impl(((long) i) & 4294967295L));
    }

    @InlineOnly
    private static final long plus-VKZWuLQ(long j, long j2) {
        return constructor-impl(j + j2);
    }

    @InlineOnly
    private static final long minus-7apg3OU(long j, byte b) {
        return constructor-impl(j - constructor-impl(((long) b) & 255));
    }

    @InlineOnly
    private static final long minus-xj2QHRw(long j, short s) {
        return constructor-impl(j - constructor-impl(((long) s) & 65535));
    }

    @InlineOnly
    private static final long minus-WZ4Q5Ns(long j, int i) {
        return constructor-impl(j - constructor-impl(((long) i) & 4294967295L));
    }

    @InlineOnly
    private static final long minus-VKZWuLQ(long j, long j2) {
        return constructor-impl(j - j2);
    }

    @InlineOnly
    private static final long times-7apg3OU(long j, byte b) {
        return constructor-impl(j * constructor-impl(((long) b) & 255));
    }

    @InlineOnly
    private static final long times-xj2QHRw(long j, short s) {
        return constructor-impl(j * constructor-impl(((long) s) & 65535));
    }

    @InlineOnly
    private static final long times-WZ4Q5Ns(long j, int i) {
        return constructor-impl(j * constructor-impl(((long) i) & 4294967295L));
    }

    @InlineOnly
    private static final long times-VKZWuLQ(long j, long j2) {
        return constructor-impl(j * j2);
    }

    @InlineOnly
    private static final long div-7apg3OU(long j, byte b) {
        return UnsignedKt.ulongDivide-eb3DHEI(j, constructor-impl(((long) b) & 255));
    }

    @InlineOnly
    private static final long div-xj2QHRw(long j, short s) {
        return UnsignedKt.ulongDivide-eb3DHEI(j, constructor-impl(((long) s) & 65535));
    }

    @InlineOnly
    private static final long div-WZ4Q5Ns(long j, int i) {
        return UnsignedKt.ulongDivide-eb3DHEI(j, constructor-impl(((long) i) & 4294967295L));
    }

    @InlineOnly
    private static final long div-VKZWuLQ(long j, long j2) {
        return UnsignedKt.ulongDivide-eb3DHEI(j, j2);
    }

    @InlineOnly
    private static final long rem-7apg3OU(long j, byte b) {
        return UnsignedKt.ulongRemainder-eb3DHEI(j, constructor-impl(((long) b) & 255));
    }

    @InlineOnly
    private static final long rem-xj2QHRw(long j, short s) {
        return UnsignedKt.ulongRemainder-eb3DHEI(j, constructor-impl(((long) s) & 65535));
    }

    @InlineOnly
    private static final long rem-WZ4Q5Ns(long j, int i) {
        return UnsignedKt.ulongRemainder-eb3DHEI(j, constructor-impl(((long) i) & 4294967295L));
    }

    @InlineOnly
    private static final long rem-VKZWuLQ(long j, long j2) {
        return UnsignedKt.ulongRemainder-eb3DHEI(j, j2);
    }

    @InlineOnly
    private static final long inc-impl(long j) {
        return constructor-impl(j + 1);
    }

    @InlineOnly
    private static final long dec-impl(long j) {
        return constructor-impl(j - 1);
    }

    @InlineOnly
    private static final ULongRange rangeTo-VKZWuLQ(long j, long j2) {
        return new ULongRange(j, j2);
    }

    @InlineOnly
    private static final long shl-impl(long j, int i) {
        return constructor-impl(j << i);
    }

    @InlineOnly
    private static final long shr-impl(long j, int i) {
        return constructor-impl(j >>> i);
    }

    @InlineOnly
    private static final long and-VKZWuLQ(long j, long j2) {
        return constructor-impl(j & j2);
    }

    @InlineOnly
    private static final long or-VKZWuLQ(long j, long j2) {
        return constructor-impl(j | j2);
    }

    @InlineOnly
    private static final long xor-VKZWuLQ(long j, long j2) {
        return constructor-impl(j ^ j2);
    }

    @InlineOnly
    private static final long inv-impl(long j) {
        return constructor-impl(~j);
    }

    @InlineOnly
    private static final byte toUByte-impl(long j) {
        return UByte.constructor-impl((byte) ((int) j));
    }

    @InlineOnly
    private static final short toUShort-impl(long j) {
        return UShort.constructor-impl((short) ((int) j));
    }

    @InlineOnly
    private static final int toUInt-impl(long j) {
        return UInt.constructor-impl((int) j);
    }

    @NotNull
    public static String toString-impl(long j) {
        return UnsignedKt.ulongToString(j);
    }
}
