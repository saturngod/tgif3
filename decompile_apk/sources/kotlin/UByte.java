package kotlin;

import kotlin.internal.InlineOnly;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.UIntRange;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@ExperimentalUnsignedTypes
@SinceKotlin(version = "1.3")
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\u0005\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\n\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u000e\b@\u0018\u0000 ^2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001^B\u0014\b\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005J\u001b\u0010\b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\fø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000bJ\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000fJ\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0010H\nø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0012J\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0013H\nø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0015J\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0016H\nø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0018J\u0013\u0010\u0019\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u0005J\u001b\u0010\u001b\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u000fJ\u001b\u0010\u001b\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0010H\nø\u0001\u0000¢\u0006\u0004\b\u001d\u0010\u0012J\u001b\u0010\u001b\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\u0013H\nø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u001fJ\u001b\u0010\u001b\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0016H\nø\u0001\u0000¢\u0006\u0004\b \u0010\u0018J\u0013\u0010!\u001a\u00020\"2\b\u0010\t\u001a\u0004\u0018\u00010#HÖ\u0003J\t\u0010$\u001a\u00020\rHÖ\u0001J\u0013\u0010%\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b&\u0010\u0005J\u0013\u0010'\u001a\u00020\u0000H\bø\u0001\u0000¢\u0006\u0004\b(\u0010\u0005J\u001b\u0010)\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b*\u0010\u000fJ\u001b\u0010)\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0010H\nø\u0001\u0000¢\u0006\u0004\b+\u0010\u0012J\u001b\u0010)\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\u0013H\nø\u0001\u0000¢\u0006\u0004\b,\u0010\u001fJ\u001b\u0010)\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0016H\nø\u0001\u0000¢\u0006\u0004\b-\u0010\u0018J\u001b\u0010.\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\fø\u0001\u0000¢\u0006\u0004\b/\u0010\u000bJ\u001b\u00100\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b1\u0010\u000fJ\u001b\u00100\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0010H\nø\u0001\u0000¢\u0006\u0004\b2\u0010\u0012J\u001b\u00100\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\u0013H\nø\u0001\u0000¢\u0006\u0004\b3\u0010\u001fJ\u001b\u00100\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0016H\nø\u0001\u0000¢\u0006\u0004\b4\u0010\u0018J\u001b\u00105\u001a\u0002062\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b7\u00108J\u001b\u00109\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b:\u0010\u000fJ\u001b\u00109\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0010H\nø\u0001\u0000¢\u0006\u0004\b;\u0010\u0012J\u001b\u00109\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\u0013H\nø\u0001\u0000¢\u0006\u0004\b<\u0010\u001fJ\u001b\u00109\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0016H\nø\u0001\u0000¢\u0006\u0004\b=\u0010\u0018J\u001b\u0010>\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b?\u0010\u000fJ\u001b\u0010>\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0010H\nø\u0001\u0000¢\u0006\u0004\b@\u0010\u0012J\u001b\u0010>\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\u0013H\nø\u0001\u0000¢\u0006\u0004\bA\u0010\u001fJ\u001b\u0010>\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0016H\nø\u0001\u0000¢\u0006\u0004\bB\u0010\u0018J\u0010\u0010C\u001a\u00020\u0003H\b¢\u0006\u0004\bD\u0010\u0005J\u0010\u0010E\u001a\u00020\rH\b¢\u0006\u0004\bF\u0010GJ\u0010\u0010H\u001a\u00020IH\b¢\u0006\u0004\bJ\u0010KJ\u0010\u0010L\u001a\u00020MH\b¢\u0006\u0004\bN\u0010OJ\u000f\u0010P\u001a\u00020QH\u0016¢\u0006\u0004\bR\u0010SJ\u0013\u0010T\u001a\u00020\u0000H\bø\u0001\u0000¢\u0006\u0004\bU\u0010\u0005J\u0013\u0010V\u001a\u00020\u0010H\bø\u0001\u0000¢\u0006\u0004\bW\u0010GJ\u0013\u0010X\u001a\u00020\u0013H\bø\u0001\u0000¢\u0006\u0004\bY\u0010KJ\u0013\u0010Z\u001a\u00020\u0016H\bø\u0001\u0000¢\u0006\u0004\b[\u0010OJ\u001b\u0010\\\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\fø\u0001\u0000¢\u0006\u0004\b]\u0010\u000bR\u0016\u0010\u0002\u001a\u00020\u00038\u0000X\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0006\u0010\u0007ø\u0001\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006_"}, d2 = {"Lkotlin/UByte;", "", "data", "", "constructor-impl", "(B)B", "data$annotations", "()V", "and", "other", "and-7apg3OU", "(BB)B", "compareTo", "", "compareTo-7apg3OU", "(BB)I", "Lkotlin/UInt;", "compareTo-WZ4Q5Ns", "(BI)I", "Lkotlin/ULong;", "compareTo-VKZWuLQ", "(BJ)I", "Lkotlin/UShort;", "compareTo-xj2QHRw", "(BS)I", "dec", "dec-impl", "div", "div-7apg3OU", "div-WZ4Q5Ns", "div-VKZWuLQ", "(BJ)J", "div-xj2QHRw", "equals", "", "", "hashCode", "inc", "inc-impl", "inv", "inv-impl", "minus", "minus-7apg3OU", "minus-WZ4Q5Ns", "minus-VKZWuLQ", "minus-xj2QHRw", "or", "or-7apg3OU", "plus", "plus-7apg3OU", "plus-WZ4Q5Ns", "plus-VKZWuLQ", "plus-xj2QHRw", "rangeTo", "Lkotlin/ranges/UIntRange;", "rangeTo-7apg3OU", "(BB)Lkotlin/ranges/UIntRange;", "rem", "rem-7apg3OU", "rem-WZ4Q5Ns", "rem-VKZWuLQ", "rem-xj2QHRw", "times", "times-7apg3OU", "times-WZ4Q5Ns", "times-VKZWuLQ", "times-xj2QHRw", "toByte", "toByte-impl", "toInt", "toInt-impl", "(B)I", "toLong", "", "toLong-impl", "(B)J", "toShort", "", "toShort-impl", "(B)S", "toString", "", "toString-impl", "(B)Ljava/lang/String;", "toUByte", "toUByte-impl", "toUInt", "toUInt-impl", "toULong", "toULong-impl", "toUShort", "toUShort-impl", "xor", "xor-7apg3OU", "Companion", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
/* compiled from: UByte.kt */
public final class UByte implements Comparable<UByte> {
    public static final Companion Companion = new Companion();
    public static final byte MAX_VALUE = (byte) -1;
    public static final byte MIN_VALUE = (byte) 0;
    public static final int SIZE_BITS = 8;
    public static final int SIZE_BYTES = 1;
    private final byte data;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0013\u0010\u0003\u001a\u00020\u0004XTø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0005R\u0013\u0010\u0006\u001a\u00020\u0004XTø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0005R\u000e\u0010\u0007\u001a\u00020\bXT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bXT¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\n"}, d2 = {"Lkotlin/UByte$Companion;", "", "()V", "MAX_VALUE", "Lkotlin/UByte;", "B", "MIN_VALUE", "SIZE_BITS", "", "SIZE_BYTES", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
    /* compiled from: UByte.kt */
    public static final class Companion {
        private Companion() {
        }
    }

    @InlineOnly
    private int compareTo-7apg3OU(byte b) {
        return compareTo-7apg3OU(this.data, b);
    }

    @PublishedApi
    public static byte constructor-impl(byte b) {
        return b;
    }

    @PublishedApi
    public static /* synthetic */ void data$annotations() {
    }

    public static boolean equals-impl(byte b, @Nullable Object obj) {
        if (obj instanceof UByte) {
            if ((b == ((UByte) obj).unbox-impl() ? 1 : null) != null) {
                return true;
            }
        }
        return false;
    }

    public static final boolean equals-impl0(byte b, byte b2) {
        throw null;
    }

    public static int hashCode-impl(byte b) {
        return b;
    }

    @InlineOnly
    private static final byte toByte-impl(byte b) {
        return b;
    }

    @InlineOnly
    private static final int toInt-impl(byte b) {
        return b & 255;
    }

    @InlineOnly
    private static final long toLong-impl(byte b) {
        return ((long) b) & 255;
    }

    @InlineOnly
    private static final short toShort-impl(byte b) {
        return (short) (((short) b) & 255);
    }

    @InlineOnly
    private static final byte toUByte-impl(byte b) {
        return b;
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

    public final /* synthetic */ byte unbox-impl() {
        return this.data;
    }

    @PublishedApi
    private /* synthetic */ UByte(byte b) {
        this.data = b;
    }

    public /* bridge */ /* synthetic */ int compareTo(Object obj) {
        return compareTo-7apg3OU(((UByte) obj).unbox-impl());
    }

    @InlineOnly
    private static int compareTo-7apg3OU(byte b, byte b2) {
        return Intrinsics.compare(b & 255, b2 & 255);
    }

    @InlineOnly
    private static final int compareTo-xj2QHRw(byte b, short s) {
        return Intrinsics.compare(b & 255, s & UShort.MAX_VALUE);
    }

    @InlineOnly
    private static final int compareTo-WZ4Q5Ns(byte b, int i) {
        return UnsignedKt.uintCompare(UInt.constructor-impl(b & 255), i);
    }

    @InlineOnly
    private static final int compareTo-VKZWuLQ(byte b, long j) {
        return UnsignedKt.ulongCompare(ULong.constructor-impl(((long) b) & 255), j);
    }

    @InlineOnly
    private static final int plus-7apg3OU(byte b, byte b2) {
        return UInt.constructor-impl(UInt.constructor-impl(b & 255) + UInt.constructor-impl(b2 & 255));
    }

    @InlineOnly
    private static final int plus-xj2QHRw(byte b, short s) {
        return UInt.constructor-impl(UInt.constructor-impl(b & 255) + UInt.constructor-impl(s & UShort.MAX_VALUE));
    }

    @InlineOnly
    private static final int plus-WZ4Q5Ns(byte b, int i) {
        return UInt.constructor-impl(UInt.constructor-impl(b & 255) + i);
    }

    @InlineOnly
    private static final long plus-VKZWuLQ(byte b, long j) {
        return ULong.constructor-impl(ULong.constructor-impl(((long) b) & 255) + j);
    }

    @InlineOnly
    private static final int minus-7apg3OU(byte b, byte b2) {
        return UInt.constructor-impl(UInt.constructor-impl(b & 255) - UInt.constructor-impl(b2 & 255));
    }

    @InlineOnly
    private static final int minus-xj2QHRw(byte b, short s) {
        return UInt.constructor-impl(UInt.constructor-impl(b & 255) - UInt.constructor-impl(s & UShort.MAX_VALUE));
    }

    @InlineOnly
    private static final int minus-WZ4Q5Ns(byte b, int i) {
        return UInt.constructor-impl(UInt.constructor-impl(b & 255) - i);
    }

    @InlineOnly
    private static final long minus-VKZWuLQ(byte b, long j) {
        return ULong.constructor-impl(ULong.constructor-impl(((long) b) & 255) - j);
    }

    @InlineOnly
    private static final int times-7apg3OU(byte b, byte b2) {
        return UInt.constructor-impl(UInt.constructor-impl(b & 255) * UInt.constructor-impl(b2 & 255));
    }

    @InlineOnly
    private static final int times-xj2QHRw(byte b, short s) {
        return UInt.constructor-impl(UInt.constructor-impl(b & 255) * UInt.constructor-impl(s & UShort.MAX_VALUE));
    }

    @InlineOnly
    private static final int times-WZ4Q5Ns(byte b, int i) {
        return UInt.constructor-impl(UInt.constructor-impl(b & 255) * i);
    }

    @InlineOnly
    private static final long times-VKZWuLQ(byte b, long j) {
        return ULong.constructor-impl(ULong.constructor-impl(((long) b) & 255) * j);
    }

    @InlineOnly
    private static final int div-7apg3OU(byte b, byte b2) {
        return UnsignedKt.uintDivide-J1ME1BU(UInt.constructor-impl(b & 255), UInt.constructor-impl(b2 & 255));
    }

    @InlineOnly
    private static final int div-xj2QHRw(byte b, short s) {
        return UnsignedKt.uintDivide-J1ME1BU(UInt.constructor-impl(b & 255), UInt.constructor-impl(s & UShort.MAX_VALUE));
    }

    @InlineOnly
    private static final int div-WZ4Q5Ns(byte b, int i) {
        return UnsignedKt.uintDivide-J1ME1BU(UInt.constructor-impl(b & 255), i);
    }

    @InlineOnly
    private static final long div-VKZWuLQ(byte b, long j) {
        return UnsignedKt.ulongDivide-eb3DHEI(ULong.constructor-impl(((long) b) & 255), j);
    }

    @InlineOnly
    private static final int rem-7apg3OU(byte b, byte b2) {
        return UnsignedKt.uintRemainder-J1ME1BU(UInt.constructor-impl(b & 255), UInt.constructor-impl(b2 & 255));
    }

    @InlineOnly
    private static final int rem-xj2QHRw(byte b, short s) {
        return UnsignedKt.uintRemainder-J1ME1BU(UInt.constructor-impl(b & 255), UInt.constructor-impl(s & UShort.MAX_VALUE));
    }

    @InlineOnly
    private static final int rem-WZ4Q5Ns(byte b, int i) {
        return UnsignedKt.uintRemainder-J1ME1BU(UInt.constructor-impl(b & 255), i);
    }

    @InlineOnly
    private static final long rem-VKZWuLQ(byte b, long j) {
        return UnsignedKt.ulongRemainder-eb3DHEI(ULong.constructor-impl(((long) b) & 255), j);
    }

    @InlineOnly
    private static final byte inc-impl(byte b) {
        return constructor-impl((byte) (b + 1));
    }

    @InlineOnly
    private static final byte dec-impl(byte b) {
        return constructor-impl((byte) (b - 1));
    }

    @InlineOnly
    private static final UIntRange rangeTo-7apg3OU(byte b, byte b2) {
        return new UIntRange(UInt.constructor-impl(b & 255), UInt.constructor-impl(b2 & 255));
    }

    @InlineOnly
    private static final byte and-7apg3OU(byte b, byte b2) {
        return constructor-impl((byte) (b & b2));
    }

    @InlineOnly
    private static final byte or-7apg3OU(byte b, byte b2) {
        return constructor-impl((byte) (b | b2));
    }

    @InlineOnly
    private static final byte xor-7apg3OU(byte b, byte b2) {
        return constructor-impl((byte) (b ^ b2));
    }

    @InlineOnly
    private static final byte inv-impl(byte b) {
        return constructor-impl((byte) (~b));
    }

    @InlineOnly
    private static final short toUShort-impl(byte b) {
        return UShort.constructor-impl((short) (((short) b) & 255));
    }

    @InlineOnly
    private static final int toUInt-impl(byte b) {
        return UInt.constructor-impl(b & 255);
    }

    @InlineOnly
    private static final long toULong-impl(byte b) {
        return ULong.constructor-impl(((long) b) & 255);
    }

    @NotNull
    public static String toString-impl(byte b) {
        return String.valueOf(b & 255);
    }
}
