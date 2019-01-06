package kotlin;

import android.support.v4.internal.view.SupportMenu;
import kotlin.internal.InlineOnly;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.UIntRange;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@ExperimentalUnsignedTypes
@SinceKotlin(version = "1.3")
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\n\n\u0002\b\t\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u0005\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u000e\b@\u0018\u0000 ^2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001^B\u0014\b\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005J\u001b\u0010\b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\fø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000bJ\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u000eH\nø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0010J\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0011H\nø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u0013J\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0014H\nø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0016J\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0018J\u0013\u0010\u0019\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u0005J\u001b\u0010\u001b\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u000eH\nø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u0010J\u001b\u0010\u001b\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\nø\u0001\u0000¢\u0006\u0004\b\u001d\u0010\u0013J\u001b\u0010\u001b\u001a\u00020\u00142\u0006\u0010\t\u001a\u00020\u0014H\nø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u001fJ\u001b\u0010\u001b\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b \u0010\u0018J\u0013\u0010!\u001a\u00020\"2\b\u0010\t\u001a\u0004\u0018\u00010#HÖ\u0003J\t\u0010$\u001a\u00020\rHÖ\u0001J\u0013\u0010%\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b&\u0010\u0005J\u0013\u0010'\u001a\u00020\u0000H\bø\u0001\u0000¢\u0006\u0004\b(\u0010\u0005J\u001b\u0010)\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u000eH\nø\u0001\u0000¢\u0006\u0004\b*\u0010\u0010J\u001b\u0010)\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\nø\u0001\u0000¢\u0006\u0004\b+\u0010\u0013J\u001b\u0010)\u001a\u00020\u00142\u0006\u0010\t\u001a\u00020\u0014H\nø\u0001\u0000¢\u0006\u0004\b,\u0010\u001fJ\u001b\u0010)\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b-\u0010\u0018J\u001b\u0010.\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\fø\u0001\u0000¢\u0006\u0004\b/\u0010\u000bJ\u001b\u00100\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u000eH\nø\u0001\u0000¢\u0006\u0004\b1\u0010\u0010J\u001b\u00100\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\nø\u0001\u0000¢\u0006\u0004\b2\u0010\u0013J\u001b\u00100\u001a\u00020\u00142\u0006\u0010\t\u001a\u00020\u0014H\nø\u0001\u0000¢\u0006\u0004\b3\u0010\u001fJ\u001b\u00100\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b4\u0010\u0018J\u001b\u00105\u001a\u0002062\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b7\u00108J\u001b\u00109\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u000eH\nø\u0001\u0000¢\u0006\u0004\b:\u0010\u0010J\u001b\u00109\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\nø\u0001\u0000¢\u0006\u0004\b;\u0010\u0013J\u001b\u00109\u001a\u00020\u00142\u0006\u0010\t\u001a\u00020\u0014H\nø\u0001\u0000¢\u0006\u0004\b<\u0010\u001fJ\u001b\u00109\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b=\u0010\u0018J\u001b\u0010>\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u000eH\nø\u0001\u0000¢\u0006\u0004\b?\u0010\u0010J\u001b\u0010>\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\nø\u0001\u0000¢\u0006\u0004\b@\u0010\u0013J\u001b\u0010>\u001a\u00020\u00142\u0006\u0010\t\u001a\u00020\u0014H\nø\u0001\u0000¢\u0006\u0004\bA\u0010\u001fJ\u001b\u0010>\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\bB\u0010\u0018J\u0010\u0010C\u001a\u00020DH\b¢\u0006\u0004\bE\u0010FJ\u0010\u0010G\u001a\u00020\rH\b¢\u0006\u0004\bH\u0010IJ\u0010\u0010J\u001a\u00020KH\b¢\u0006\u0004\bL\u0010MJ\u0010\u0010N\u001a\u00020\u0003H\b¢\u0006\u0004\bO\u0010\u0005J\u000f\u0010P\u001a\u00020QH\u0016¢\u0006\u0004\bR\u0010SJ\u0013\u0010T\u001a\u00020\u000eH\bø\u0001\u0000¢\u0006\u0004\bU\u0010FJ\u0013\u0010V\u001a\u00020\u0011H\bø\u0001\u0000¢\u0006\u0004\bW\u0010IJ\u0013\u0010X\u001a\u00020\u0014H\bø\u0001\u0000¢\u0006\u0004\bY\u0010MJ\u0013\u0010Z\u001a\u00020\u0000H\bø\u0001\u0000¢\u0006\u0004\b[\u0010\u0005J\u001b\u0010\\\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\fø\u0001\u0000¢\u0006\u0004\b]\u0010\u000bR\u0016\u0010\u0002\u001a\u00020\u00038\u0000X\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0006\u0010\u0007ø\u0001\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006_"}, d2 = {"Lkotlin/UShort;", "", "data", "", "constructor-impl", "(S)S", "data$annotations", "()V", "and", "other", "and-xj2QHRw", "(SS)S", "compareTo", "", "Lkotlin/UByte;", "compareTo-7apg3OU", "(SB)I", "Lkotlin/UInt;", "compareTo-WZ4Q5Ns", "(SI)I", "Lkotlin/ULong;", "compareTo-VKZWuLQ", "(SJ)I", "compareTo-xj2QHRw", "(SS)I", "dec", "dec-impl", "div", "div-7apg3OU", "div-WZ4Q5Ns", "div-VKZWuLQ", "(SJ)J", "div-xj2QHRw", "equals", "", "", "hashCode", "inc", "inc-impl", "inv", "inv-impl", "minus", "minus-7apg3OU", "minus-WZ4Q5Ns", "minus-VKZWuLQ", "minus-xj2QHRw", "or", "or-xj2QHRw", "plus", "plus-7apg3OU", "plus-WZ4Q5Ns", "plus-VKZWuLQ", "plus-xj2QHRw", "rangeTo", "Lkotlin/ranges/UIntRange;", "rangeTo-xj2QHRw", "(SS)Lkotlin/ranges/UIntRange;", "rem", "rem-7apg3OU", "rem-WZ4Q5Ns", "rem-VKZWuLQ", "rem-xj2QHRw", "times", "times-7apg3OU", "times-WZ4Q5Ns", "times-VKZWuLQ", "times-xj2QHRw", "toByte", "", "toByte-impl", "(S)B", "toInt", "toInt-impl", "(S)I", "toLong", "", "toLong-impl", "(S)J", "toShort", "toShort-impl", "toString", "", "toString-impl", "(S)Ljava/lang/String;", "toUByte", "toUByte-impl", "toUInt", "toUInt-impl", "toULong", "toULong-impl", "toUShort", "toUShort-impl", "xor", "xor-xj2QHRw", "Companion", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
/* compiled from: UShort.kt */
public final class UShort implements Comparable<UShort> {
    public static final Companion Companion = new Companion();
    public static final short MAX_VALUE = (short) -1;
    public static final short MIN_VALUE = (short) 0;
    public static final int SIZE_BITS = 16;
    public static final int SIZE_BYTES = 2;
    private final short data;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0013\u0010\u0003\u001a\u00020\u0004XTø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0005R\u0013\u0010\u0006\u001a\u00020\u0004XTø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0005R\u000e\u0010\u0007\u001a\u00020\bXT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bXT¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\n"}, d2 = {"Lkotlin/UShort$Companion;", "", "()V", "MAX_VALUE", "Lkotlin/UShort;", "S", "MIN_VALUE", "SIZE_BITS", "", "SIZE_BYTES", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
    /* compiled from: UShort.kt */
    public static final class Companion {
        private Companion() {
        }
    }

    @InlineOnly
    private int compareTo-xj2QHRw(short s) {
        return compareTo-xj2QHRw(this.data, s);
    }

    @PublishedApi
    public static short constructor-impl(short s) {
        return s;
    }

    @PublishedApi
    public static /* synthetic */ void data$annotations() {
    }

    public static boolean equals-impl(short s, @Nullable Object obj) {
        if (obj instanceof UShort) {
            if ((s == ((UShort) obj).unbox-impl() ? 1 : null) != null) {
                return true;
            }
        }
        return false;
    }

    public static final boolean equals-impl0(short s, short s2) {
        throw null;
    }

    public static int hashCode-impl(short s) {
        return s;
    }

    @InlineOnly
    private static final byte toByte-impl(short s) {
        return (byte) s;
    }

    @InlineOnly
    private static final int toInt-impl(short s) {
        return s & SupportMenu.USER_MASK;
    }

    @InlineOnly
    private static final long toLong-impl(short s) {
        return ((long) s) & 65535;
    }

    @InlineOnly
    private static final short toShort-impl(short s) {
        return s;
    }

    @InlineOnly
    private static final short toUShort-impl(short s) {
        return s;
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

    public final /* synthetic */ short unbox-impl() {
        return this.data;
    }

    @PublishedApi
    private /* synthetic */ UShort(short s) {
        this.data = s;
    }

    public /* bridge */ /* synthetic */ int compareTo(Object obj) {
        return compareTo-xj2QHRw(((UShort) obj).unbox-impl());
    }

    @InlineOnly
    private static final int compareTo-7apg3OU(short s, byte b) {
        return Intrinsics.compare(s & MAX_VALUE, b & 255);
    }

    @InlineOnly
    private static int compareTo-xj2QHRw(short s, short s2) {
        return Intrinsics.compare(s & MAX_VALUE, s2 & MAX_VALUE);
    }

    @InlineOnly
    private static final int compareTo-WZ4Q5Ns(short s, int i) {
        return UnsignedKt.uintCompare(UInt.constructor-impl(s & MAX_VALUE), i);
    }

    @InlineOnly
    private static final int compareTo-VKZWuLQ(short s, long j) {
        return UnsignedKt.ulongCompare(ULong.constructor-impl(((long) s) & 65535), j);
    }

    @InlineOnly
    private static final int plus-7apg3OU(short s, byte b) {
        return UInt.constructor-impl(UInt.constructor-impl(s & MAX_VALUE) + UInt.constructor-impl(b & 255));
    }

    @InlineOnly
    private static final int plus-xj2QHRw(short s, short s2) {
        return UInt.constructor-impl(UInt.constructor-impl(s & MAX_VALUE) + UInt.constructor-impl(s2 & MAX_VALUE));
    }

    @InlineOnly
    private static final int plus-WZ4Q5Ns(short s, int i) {
        return UInt.constructor-impl(UInt.constructor-impl(s & MAX_VALUE) + i);
    }

    @InlineOnly
    private static final long plus-VKZWuLQ(short s, long j) {
        return ULong.constructor-impl(ULong.constructor-impl(((long) s) & 65535) + j);
    }

    @InlineOnly
    private static final int minus-7apg3OU(short s, byte b) {
        return UInt.constructor-impl(UInt.constructor-impl(s & MAX_VALUE) - UInt.constructor-impl(b & 255));
    }

    @InlineOnly
    private static final int minus-xj2QHRw(short s, short s2) {
        return UInt.constructor-impl(UInt.constructor-impl(s & MAX_VALUE) - UInt.constructor-impl(s2 & MAX_VALUE));
    }

    @InlineOnly
    private static final int minus-WZ4Q5Ns(short s, int i) {
        return UInt.constructor-impl(UInt.constructor-impl(s & MAX_VALUE) - i);
    }

    @InlineOnly
    private static final long minus-VKZWuLQ(short s, long j) {
        return ULong.constructor-impl(ULong.constructor-impl(((long) s) & 65535) - j);
    }

    @InlineOnly
    private static final int times-7apg3OU(short s, byte b) {
        return UInt.constructor-impl(UInt.constructor-impl(s & MAX_VALUE) * UInt.constructor-impl(b & 255));
    }

    @InlineOnly
    private static final int times-xj2QHRw(short s, short s2) {
        return UInt.constructor-impl(UInt.constructor-impl(s & MAX_VALUE) * UInt.constructor-impl(s2 & MAX_VALUE));
    }

    @InlineOnly
    private static final int times-WZ4Q5Ns(short s, int i) {
        return UInt.constructor-impl(UInt.constructor-impl(s & MAX_VALUE) * i);
    }

    @InlineOnly
    private static final long times-VKZWuLQ(short s, long j) {
        return ULong.constructor-impl(ULong.constructor-impl(((long) s) & 65535) * j);
    }

    @InlineOnly
    private static final int div-7apg3OU(short s, byte b) {
        return UnsignedKt.uintDivide-J1ME1BU(UInt.constructor-impl(s & MAX_VALUE), UInt.constructor-impl(b & 255));
    }

    @InlineOnly
    private static final int div-xj2QHRw(short s, short s2) {
        return UnsignedKt.uintDivide-J1ME1BU(UInt.constructor-impl(s & MAX_VALUE), UInt.constructor-impl(s2 & MAX_VALUE));
    }

    @InlineOnly
    private static final int div-WZ4Q5Ns(short s, int i) {
        return UnsignedKt.uintDivide-J1ME1BU(UInt.constructor-impl(s & MAX_VALUE), i);
    }

    @InlineOnly
    private static final long div-VKZWuLQ(short s, long j) {
        return UnsignedKt.ulongDivide-eb3DHEI(ULong.constructor-impl(((long) s) & 65535), j);
    }

    @InlineOnly
    private static final int rem-7apg3OU(short s, byte b) {
        return UnsignedKt.uintRemainder-J1ME1BU(UInt.constructor-impl(s & MAX_VALUE), UInt.constructor-impl(b & 255));
    }

    @InlineOnly
    private static final int rem-xj2QHRw(short s, short s2) {
        return UnsignedKt.uintRemainder-J1ME1BU(UInt.constructor-impl(s & MAX_VALUE), UInt.constructor-impl(s2 & MAX_VALUE));
    }

    @InlineOnly
    private static final int rem-WZ4Q5Ns(short s, int i) {
        return UnsignedKt.uintRemainder-J1ME1BU(UInt.constructor-impl(s & MAX_VALUE), i);
    }

    @InlineOnly
    private static final long rem-VKZWuLQ(short s, long j) {
        return UnsignedKt.ulongRemainder-eb3DHEI(ULong.constructor-impl(((long) s) & 65535), j);
    }

    @InlineOnly
    private static final short inc-impl(short s) {
        return constructor-impl((short) (s + 1));
    }

    @InlineOnly
    private static final short dec-impl(short s) {
        return constructor-impl((short) (s - 1));
    }

    @InlineOnly
    private static final UIntRange rangeTo-xj2QHRw(short s, short s2) {
        return new UIntRange(UInt.constructor-impl(s & MAX_VALUE), UInt.constructor-impl(s2 & MAX_VALUE));
    }

    @InlineOnly
    private static final short and-xj2QHRw(short s, short s2) {
        return constructor-impl((short) (s & s2));
    }

    @InlineOnly
    private static final short or-xj2QHRw(short s, short s2) {
        return constructor-impl((short) (s | s2));
    }

    @InlineOnly
    private static final short xor-xj2QHRw(short s, short s2) {
        return constructor-impl((short) (s ^ s2));
    }

    @InlineOnly
    private static final short inv-impl(short s) {
        return constructor-impl((short) (~s));
    }

    @InlineOnly
    private static final byte toUByte-impl(short s) {
        return UByte.constructor-impl((byte) s);
    }

    @InlineOnly
    private static final int toUInt-impl(short s) {
        return UInt.constructor-impl(s & MAX_VALUE);
    }

    @InlineOnly
    private static final long toULong-impl(short s) {
        return ULong.constructor-impl(((long) s) & 65535);
    }

    @NotNull
    public static String toString-impl(short s) {
        return String.valueOf(s & MAX_VALUE);
    }
}
