package kotlin.collections;

import java.util.Arrays;
import java.util.NoSuchElementException;
import kotlin.ExperimentalUnsignedTypes;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.UByte;
import kotlin.UByteArray;
import kotlin.UInt;
import kotlin.UIntArray;
import kotlin.ULong;
import kotlin.ULongArray;
import kotlin.UShort;
import kotlin.UShortArray;
import kotlin.internal.InlineOnly;
import kotlin.internal.PlatformImplementationsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000z\n\u0000\n\u0002\u0010\u0012\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0016\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0017\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b/\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u0011\n\u0002\b\r\u001a\u0017\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\bø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004\u001a\u0017\u0010\u0005\u001a\u00020\u0006*\u00020\u0007H\bø\u0001\u0000¢\u0006\u0004\b\b\u0010\t\u001a\u0017\u0010\n\u001a\u00020\u000b*\u00020\fH\bø\u0001\u0000¢\u0006\u0004\b\r\u0010\u000e\u001a\u0017\u0010\u000f\u001a\u00020\u0010*\u00020\u0011H\bø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u0013\u001a\u0015\u0010\u0014\u001a\u00020\u0002*\u00020\u0001H\bø\u0001\u0000¢\u0006\u0002\u0010\u0004\u001a\u0015\u0010\u0015\u001a\u00020\u0007*\u00020\u0006H\bø\u0001\u0000¢\u0006\u0002\u0010\t\u001a\u0015\u0010\u0016\u001a\u00020\f*\u00020\u000bH\bø\u0001\u0000¢\u0006\u0002\u0010\u000e\u001a\u0015\u0010\u0017\u001a\u00020\u0011*\u00020\u0010H\bø\u0001\u0000¢\u0006\u0002\u0010\u0013\u001a\u001f\u0010\u0018\u001a\u00020\u0019*\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u0002H\u0004ø\u0001\u0000¢\u0006\u0004\b\u001b\u0010\u001c\u001a\u001f\u0010\u0018\u001a\u00020\u0019*\u00020\u00072\u0006\u0010\u001a\u001a\u00020\u0007H\u0004ø\u0001\u0000¢\u0006\u0004\b\u001d\u0010\u001e\u001a\u001f\u0010\u0018\u001a\u00020\u0019*\u00020\f2\u0006\u0010\u001a\u001a\u00020\fH\u0004ø\u0001\u0000¢\u0006\u0004\b\u001f\u0010 \u001a\u001f\u0010\u0018\u001a\u00020\u0019*\u00020\u00112\u0006\u0010\u001a\u001a\u00020\u0011H\u0004ø\u0001\u0000¢\u0006\u0004\b!\u0010\"\u001a\u0016\u0010#\u001a\u00020$*\u00020\u0002H\u0007ø\u0001\u0000¢\u0006\u0004\b%\u0010&\u001a\u0016\u0010#\u001a\u00020$*\u00020\u0007H\u0007ø\u0001\u0000¢\u0006\u0004\b'\u0010(\u001a\u0016\u0010#\u001a\u00020$*\u00020\fH\u0007ø\u0001\u0000¢\u0006\u0004\b)\u0010*\u001a\u0016\u0010#\u001a\u00020$*\u00020\u0011H\u0007ø\u0001\u0000¢\u0006\u0004\b+\u0010,\u001a\u0016\u0010-\u001a\u00020.*\u00020\u0002H\u0007ø\u0001\u0000¢\u0006\u0004\b/\u00100\u001a\u0016\u0010-\u001a\u00020.*\u00020\u0007H\u0007ø\u0001\u0000¢\u0006\u0004\b1\u00102\u001a\u0016\u0010-\u001a\u00020.*\u00020\fH\u0007ø\u0001\u0000¢\u0006\u0004\b3\u00104\u001a\u0016\u0010-\u001a\u00020.*\u00020\u0011H\u0007ø\u0001\u0000¢\u0006\u0004\b5\u00106\u001a=\u00107\u001a\u00020\u0002*\u00020\u00022\u0006\u00108\u001a\u00020\u00022\b\b\u0002\u00109\u001a\u00020$2\b\b\u0002\u0010:\u001a\u00020$2\b\b\u0002\u0010;\u001a\u00020$H\bø\u0001\u0000¢\u0006\u0004\b<\u0010=\u001a=\u00107\u001a\u00020\u0007*\u00020\u00072\u0006\u00108\u001a\u00020\u00072\b\b\u0002\u00109\u001a\u00020$2\b\b\u0002\u0010:\u001a\u00020$2\b\b\u0002\u0010;\u001a\u00020$H\bø\u0001\u0000¢\u0006\u0004\b>\u0010?\u001a=\u00107\u001a\u00020\f*\u00020\f2\u0006\u00108\u001a\u00020\f2\b\b\u0002\u00109\u001a\u00020$2\b\b\u0002\u0010:\u001a\u00020$2\b\b\u0002\u0010;\u001a\u00020$H\bø\u0001\u0000¢\u0006\u0004\b@\u0010A\u001a=\u00107\u001a\u00020\u0011*\u00020\u00112\u0006\u00108\u001a\u00020\u00112\b\b\u0002\u00109\u001a\u00020$2\b\b\u0002\u0010:\u001a\u00020$2\b\b\u0002\u0010;\u001a\u00020$H\bø\u0001\u0000¢\u0006\u0004\bB\u0010C\u001a\u0017\u0010D\u001a\u00020\u0002*\u00020\u0002H\bø\u0001\u0000¢\u0006\u0004\bE\u0010\u0004\u001a\u001f\u0010D\u001a\u00020\u0002*\u00020\u00022\u0006\u0010F\u001a\u00020$H\bø\u0001\u0000¢\u0006\u0004\bG\u0010H\u001a\u0017\u0010D\u001a\u00020\u0007*\u00020\u0007H\bø\u0001\u0000¢\u0006\u0004\bI\u0010\t\u001a\u001f\u0010D\u001a\u00020\u0007*\u00020\u00072\u0006\u0010F\u001a\u00020$H\bø\u0001\u0000¢\u0006\u0004\bJ\u0010K\u001a\u0017\u0010D\u001a\u00020\f*\u00020\fH\bø\u0001\u0000¢\u0006\u0004\bL\u0010\u000e\u001a\u001f\u0010D\u001a\u00020\f*\u00020\f2\u0006\u0010F\u001a\u00020$H\bø\u0001\u0000¢\u0006\u0004\bM\u0010N\u001a\u0017\u0010D\u001a\u00020\u0011*\u00020\u0011H\bø\u0001\u0000¢\u0006\u0004\bO\u0010\u0013\u001a\u001f\u0010D\u001a\u00020\u0011*\u00020\u00112\u0006\u0010F\u001a\u00020$H\bø\u0001\u0000¢\u0006\u0004\bP\u0010Q\u001a'\u0010R\u001a\u00020\u0002*\u00020\u00022\u0006\u0010S\u001a\u00020$2\u0006\u0010T\u001a\u00020$H\bø\u0001\u0000¢\u0006\u0004\bU\u0010V\u001a'\u0010R\u001a\u00020\u0007*\u00020\u00072\u0006\u0010S\u001a\u00020$2\u0006\u0010T\u001a\u00020$H\bø\u0001\u0000¢\u0006\u0004\bW\u0010X\u001a'\u0010R\u001a\u00020\f*\u00020\f2\u0006\u0010S\u001a\u00020$2\u0006\u0010T\u001a\u00020$H\bø\u0001\u0000¢\u0006\u0004\bY\u0010Z\u001a'\u0010R\u001a\u00020\u0011*\u00020\u00112\u0006\u0010S\u001a\u00020$2\u0006\u0010T\u001a\u00020$H\bø\u0001\u0000¢\u0006\u0004\b[\u0010\\\u001a\u0017\u0010]\u001a\u00020^*\u00020\u0002H\bø\u0001\u0000¢\u0006\u0004\b_\u0010`\u001a\u001e\u0010]\u001a\u00020^*\u00020\u00022\u0006\u0010]\u001a\u00020aH\u0007ø\u0001\u0000¢\u0006\u0004\bb\u0010c\u001a\u0017\u0010]\u001a\u00020d*\u00020\u0007H\bø\u0001\u0000¢\u0006\u0004\be\u0010(\u001a\u001e\u0010]\u001a\u00020d*\u00020\u00072\u0006\u0010]\u001a\u00020aH\u0007ø\u0001\u0000¢\u0006\u0004\bf\u0010g\u001a\u0017\u0010]\u001a\u00020h*\u00020\fH\bø\u0001\u0000¢\u0006\u0004\bi\u0010j\u001a\u001e\u0010]\u001a\u00020h*\u00020\f2\u0006\u0010]\u001a\u00020aH\u0007ø\u0001\u0000¢\u0006\u0004\bk\u0010l\u001a\u0017\u0010]\u001a\u00020m*\u00020\u0011H\bø\u0001\u0000¢\u0006\u0004\bn\u0010o\u001a\u001e\u0010]\u001a\u00020m*\u00020\u00112\u0006\u0010]\u001a\u00020aH\u0007ø\u0001\u0000¢\u0006\u0004\bp\u0010q\u001a\u0017\u0010r\u001a\u00020\u0001*\u00020\u0002H\bø\u0001\u0000¢\u0006\u0004\bs\u0010\u0004\u001a\u0017\u0010t\u001a\u00020\u0006*\u00020\u0007H\bø\u0001\u0000¢\u0006\u0004\bu\u0010\t\u001a\u0017\u0010v\u001a\u00020\u000b*\u00020\fH\bø\u0001\u0000¢\u0006\u0004\bw\u0010\u000e\u001a\u0017\u0010x\u001a\u00020\u0010*\u00020\u0011H\bø\u0001\u0000¢\u0006\u0004\by\u0010\u0013\u001a\u001c\u0010z\u001a\b\u0012\u0004\u0012\u00020^0{*\u00020\u0002H\u0007ø\u0001\u0000¢\u0006\u0004\b|\u0010}\u001a\u001c\u0010z\u001a\b\u0012\u0004\u0012\u00020d0{*\u00020\u0007H\u0007ø\u0001\u0000¢\u0006\u0004\b~\u0010\u001a\u001e\u0010z\u001a\b\u0012\u0004\u0012\u00020h0{*\u00020\fH\u0007ø\u0001\u0000¢\u0006\u0006\b\u0001\u0010\u0001\u001a\u001e\u0010z\u001a\b\u0012\u0004\u0012\u00020m0{*\u00020\u0011H\u0007ø\u0001\u0000¢\u0006\u0006\b\u0001\u0010\u0001\u001a\u0016\u0010\u0001\u001a\u00020\u0002*\u00020\u0001H\bø\u0001\u0000¢\u0006\u0002\u0010\u0004\u001a\u0016\u0010\u0001\u001a\u00020\u0007*\u00020\u0006H\bø\u0001\u0000¢\u0006\u0002\u0010\t\u001a\u0016\u0010\u0001\u001a\u00020\f*\u00020\u000bH\bø\u0001\u0000¢\u0006\u0002\u0010\u000e\u001a\u0016\u0010\u0001\u001a\u00020\u0011*\u00020\u0010H\bø\u0001\u0000¢\u0006\u0002\u0010\u0013\u0002\u0004\n\u0002\b\u0019¨\u0006\u0001"}, d2 = {"asByteArray", "", "Lkotlin/UByteArray;", "asByteArray-GBYM_sE", "([B)[B", "asIntArray", "", "Lkotlin/UIntArray;", "asIntArray--ajY-9A", "([I)[I", "asLongArray", "", "Lkotlin/ULongArray;", "asLongArray-QwZRm1k", "([J)[J", "asShortArray", "", "Lkotlin/UShortArray;", "asShortArray-rL5Bavg", "([S)[S", "asUByteArray", "asUIntArray", "asULongArray", "asUShortArray", "contentEquals", "", "other", "contentEquals-kdPth3s", "([B[B)Z", "contentEquals-ctEhBpI", "([I[I)Z", "contentEquals-us8wMrg", "([J[J)Z", "contentEquals-mazbYpA", "([S[S)Z", "contentHashCode", "", "contentHashCode-GBYM_sE", "([B)I", "contentHashCode--ajY-9A", "([I)I", "contentHashCode-QwZRm1k", "([J)I", "contentHashCode-rL5Bavg", "([S)I", "contentToString", "", "contentToString-GBYM_sE", "([B)Ljava/lang/String;", "contentToString--ajY-9A", "([I)Ljava/lang/String;", "contentToString-QwZRm1k", "([J)Ljava/lang/String;", "contentToString-rL5Bavg", "([S)Ljava/lang/String;", "copyInto", "destination", "destinationOffset", "startIndex", "endIndex", "copyInto-FUQE5sA", "([B[BIII)[B", "copyInto-sIZ3KeM", "([I[IIII)[I", "copyInto--B0-L2c", "([J[JIII)[J", "copyInto-9-ak10g", "([S[SIII)[S", "copyOf", "copyOf-GBYM_sE", "newSize", "copyOf-PpDY95g", "([BI)[B", "copyOf--ajY-9A", "copyOf-qFRl0hI", "([II)[I", "copyOf-QwZRm1k", "copyOf-r7IrZao", "([JI)[J", "copyOf-rL5Bavg", "copyOf-nggk6HY", "([SI)[S", "copyOfRange", "fromIndex", "toIndex", "copyOfRange-4UcCI2c", "([BII)[B", "copyOfRange-oBK06Vg", "([III)[I", "copyOfRange--nroSd4", "([JII)[J", "copyOfRange-Aa5vz7o", "([SII)[S", "random", "Lkotlin/UByte;", "random-GBYM_sE", "([B)B", "Lkotlin/random/Random;", "random-oSF2wD8", "([BLkotlin/random/Random;)B", "Lkotlin/UInt;", "random--ajY-9A", "random-2D5oskM", "([ILkotlin/random/Random;)I", "Lkotlin/ULong;", "random-QwZRm1k", "([J)J", "random-JzugnMA", "([JLkotlin/random/Random;)J", "Lkotlin/UShort;", "random-rL5Bavg", "([S)S", "random-s5X_as8", "([SLkotlin/random/Random;)S", "toByteArray", "toByteArray-GBYM_sE", "toIntArray", "toIntArray--ajY-9A", "toLongArray", "toLongArray-QwZRm1k", "toShortArray", "toShortArray-rL5Bavg", "toTypedArray", "", "toTypedArray-GBYM_sE", "([B)[Lkotlin/UByte;", "toTypedArray--ajY-9A", "([I)[Lkotlin/UInt;", "toTypedArray-QwZRm1k", "([J)[Lkotlin/ULong;", "toTypedArray-rL5Bavg", "([S)[Lkotlin/UShort;", "toUByteArray", "toUIntArray", "toULongArray", "toUShortArray", "kotlin-stdlib"}, k = 5, mv = {1, 1, 13}, xi = 1, xs = "kotlin/collections/UArraysKt")
/* compiled from: _UArrays.kt */
class UArraysKt___UArraysKt {
    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final byte[] asByteArray-GBYM_sE(@NotNull byte[] bArr) {
        return bArr;
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final int[] asIntArray--ajY-9A(@NotNull int[] iArr) {
        return iArr;
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final long[] asLongArray-QwZRm1k(@NotNull long[] jArr) {
        return jArr;
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final short[] asShortArray-rL5Bavg(@NotNull short[] sArr) {
        return sArr;
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final int random--ajY-9A(@NotNull int[] iArr) {
        return random-2D5oskM(iArr, Random.Default);
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final long random-QwZRm1k(@NotNull long[] jArr) {
        return random-JzugnMA(jArr, Random.Default);
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final byte random-GBYM_sE(@NotNull byte[] bArr) {
        return random-oSF2wD8(bArr, Random.Default);
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final short random-rL5Bavg(@NotNull short[] sArr) {
        return random-s5X_as8(sArr, Random.Default);
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    public static final int random-2D5oskM(@NotNull int[] iArr, @NotNull Random random) {
        Intrinsics.checkParameterIsNotNull(iArr, "receiver$0");
        Intrinsics.checkParameterIsNotNull(random, "random");
        if (!UIntArray.isEmpty-impl(iArr)) {
            return UIntArray.get-impl(iArr, random.nextInt(UIntArray.getSize-impl(iArr)));
        }
        throw ((Throwable) new NoSuchElementException("Array is empty."));
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    public static final long random-JzugnMA(@NotNull long[] jArr, @NotNull Random random) {
        Intrinsics.checkParameterIsNotNull(jArr, "receiver$0");
        Intrinsics.checkParameterIsNotNull(random, "random");
        if (!ULongArray.isEmpty-impl(jArr)) {
            return ULongArray.get-impl(jArr, random.nextInt(ULongArray.getSize-impl(jArr)));
        }
        throw ((Throwable) new NoSuchElementException("Array is empty."));
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    public static final byte random-oSF2wD8(@NotNull byte[] bArr, @NotNull Random random) {
        Intrinsics.checkParameterIsNotNull(bArr, "receiver$0");
        Intrinsics.checkParameterIsNotNull(random, "random");
        if (!UByteArray.isEmpty-impl(bArr)) {
            return UByteArray.get-impl(bArr, random.nextInt(UByteArray.getSize-impl(bArr)));
        }
        throw ((Throwable) new NoSuchElementException("Array is empty."));
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    public static final short random-s5X_as8(@NotNull short[] sArr, @NotNull Random random) {
        Intrinsics.checkParameterIsNotNull(sArr, "receiver$0");
        Intrinsics.checkParameterIsNotNull(random, "random");
        if (!UShortArray.isEmpty-impl(sArr)) {
            return UShortArray.get-impl(sArr, random.nextInt(UShortArray.getSize-impl(sArr)));
        }
        throw ((Throwable) new NoSuchElementException("Array is empty."));
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final byte[] asUByteArray(@NotNull byte[] bArr) {
        return UByteArray.constructor-impl(bArr);
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final int[] asUIntArray(@NotNull int[] iArr) {
        return UIntArray.constructor-impl(iArr);
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final long[] asULongArray(@NotNull long[] jArr) {
        return ULongArray.constructor-impl(jArr);
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final short[] asUShortArray(@NotNull short[] sArr) {
        return UShortArray.constructor-impl(sArr);
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    public static final boolean contentEquals-ctEhBpI(@NotNull int[] iArr, @NotNull int[] iArr2) {
        Intrinsics.checkParameterIsNotNull(iArr, "receiver$0");
        Intrinsics.checkParameterIsNotNull(iArr2, "other");
        return Arrays.equals(iArr, iArr2);
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    public static final boolean contentEquals-us8wMrg(@NotNull long[] jArr, @NotNull long[] jArr2) {
        Intrinsics.checkParameterIsNotNull(jArr, "receiver$0");
        Intrinsics.checkParameterIsNotNull(jArr2, "other");
        return Arrays.equals(jArr, jArr2);
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    public static final boolean contentEquals-kdPth3s(@NotNull byte[] bArr, @NotNull byte[] bArr2) {
        Intrinsics.checkParameterIsNotNull(bArr, "receiver$0");
        Intrinsics.checkParameterIsNotNull(bArr2, "other");
        return Arrays.equals(bArr, bArr2);
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    public static final boolean contentEquals-mazbYpA(@NotNull short[] sArr, @NotNull short[] sArr2) {
        Intrinsics.checkParameterIsNotNull(sArr, "receiver$0");
        Intrinsics.checkParameterIsNotNull(sArr2, "other");
        return Arrays.equals(sArr, sArr2);
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    public static final int contentHashCode--ajY-9A(@NotNull int[] iArr) {
        Intrinsics.checkParameterIsNotNull(iArr, "receiver$0");
        return Arrays.hashCode(iArr);
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    public static final int contentHashCode-QwZRm1k(@NotNull long[] jArr) {
        Intrinsics.checkParameterIsNotNull(jArr, "receiver$0");
        return Arrays.hashCode(jArr);
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    public static final int contentHashCode-GBYM_sE(@NotNull byte[] bArr) {
        Intrinsics.checkParameterIsNotNull(bArr, "receiver$0");
        return Arrays.hashCode(bArr);
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    public static final int contentHashCode-rL5Bavg(@NotNull short[] sArr) {
        Intrinsics.checkParameterIsNotNull(sArr, "receiver$0");
        return Arrays.hashCode(sArr);
    }

    @ExperimentalUnsignedTypes
    @NotNull
    @SinceKotlin(version = "1.3")
    public static final String contentToString--ajY-9A(@NotNull int[] iArr) {
        Intrinsics.checkParameterIsNotNull(iArr, "receiver$0");
        return CollectionsKt___CollectionsKt.joinToString$default(Intrinsics.checkParameterIsNotNull(iArr, "v"), ", ", "[", "]", 0, null, null, 56, null);
    }

    @ExperimentalUnsignedTypes
    @NotNull
    @SinceKotlin(version = "1.3")
    public static final String contentToString-QwZRm1k(@NotNull long[] jArr) {
        Intrinsics.checkParameterIsNotNull(jArr, "receiver$0");
        return CollectionsKt___CollectionsKt.joinToString$default(Intrinsics.checkParameterIsNotNull(jArr, "v"), ", ", "[", "]", 0, null, null, 56, null);
    }

    @ExperimentalUnsignedTypes
    @NotNull
    @SinceKotlin(version = "1.3")
    public static final String contentToString-GBYM_sE(@NotNull byte[] bArr) {
        Intrinsics.checkParameterIsNotNull(bArr, "receiver$0");
        return CollectionsKt___CollectionsKt.joinToString$default(Intrinsics.checkParameterIsNotNull(bArr, "v"), ", ", "[", "]", 0, null, null, 56, null);
    }

    @ExperimentalUnsignedTypes
    @NotNull
    @SinceKotlin(version = "1.3")
    public static final String contentToString-rL5Bavg(@NotNull short[] sArr) {
        Intrinsics.checkParameterIsNotNull(sArr, "receiver$0");
        return CollectionsKt___CollectionsKt.joinToString$default(Intrinsics.checkParameterIsNotNull(sArr, "v"), ", ", "[", "]", 0, null, null, 56, null);
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @InlineOnly
    static /* synthetic */ int[] copyInto-sIZ3KeM$default(int[] iArr, int[] iArr2, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 2) != null) {
            i = 0;
        }
        if ((i4 & 4) != null) {
            i2 = 0;
        }
        if ((i4 & 8) != 0) {
            i3 = UIntArray.getSize-impl(iArr);
        }
        return UIntArray.constructor-impl(ArraysKt___ArraysJvmKt.copyInto(iArr, iArr2, i, i2, i3));
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final int[] copyInto-sIZ3KeM(@NotNull int[] iArr, int[] iArr2, int i, int i2, int i3) {
        return UIntArray.constructor-impl(ArraysKt___ArraysJvmKt.copyInto(iArr, iArr2, i, i2, i3));
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @InlineOnly
    static /* synthetic */ long[] copyInto--B0-L2c$default(long[] jArr, long[] jArr2, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 2) != null) {
            i = 0;
        }
        if ((i4 & 4) != null) {
            i2 = 0;
        }
        if ((i4 & 8) != 0) {
            i3 = ULongArray.getSize-impl(jArr);
        }
        return ULongArray.constructor-impl(ArraysKt___ArraysJvmKt.copyInto(jArr, jArr2, i, i2, i3));
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final long[] copyInto--B0-L2c(@NotNull long[] jArr, long[] jArr2, int i, int i2, int i3) {
        return ULongArray.constructor-impl(ArraysKt___ArraysJvmKt.copyInto(jArr, jArr2, i, i2, i3));
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @InlineOnly
    static /* synthetic */ byte[] copyInto-FUQE5sA$default(byte[] bArr, byte[] bArr2, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 2) != null) {
            i = 0;
        }
        if ((i4 & 4) != null) {
            i2 = 0;
        }
        if ((i4 & 8) != 0) {
            i3 = UByteArray.getSize-impl(bArr);
        }
        return UByteArray.constructor-impl(ArraysKt___ArraysJvmKt.copyInto(bArr, bArr2, i, i2, i3));
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final byte[] copyInto-FUQE5sA(@NotNull byte[] bArr, byte[] bArr2, int i, int i2, int i3) {
        return UByteArray.constructor-impl(ArraysKt___ArraysJvmKt.copyInto(bArr, bArr2, i, i2, i3));
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @InlineOnly
    static /* synthetic */ short[] copyInto-9-ak10g$default(short[] sArr, short[] sArr2, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 2) != null) {
            i = 0;
        }
        if ((i4 & 4) != null) {
            i2 = 0;
        }
        if ((i4 & 8) != 0) {
            i3 = UShortArray.getSize-impl(sArr);
        }
        return UShortArray.constructor-impl(ArraysKt___ArraysJvmKt.copyInto(sArr, sArr2, i, i2, i3));
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final short[] copyInto-9-ak10g(@NotNull short[] sArr, short[] sArr2, int i, int i2, int i3) {
        return UShortArray.constructor-impl(ArraysKt___ArraysJvmKt.copyInto(sArr, sArr2, i, i2, i3));
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final int[] copyOf--ajY-9A(@NotNull int[] iArr) {
        iArr = Arrays.copyOf(iArr, iArr.length);
        Intrinsics.checkExpressionValueIsNotNull(iArr, "java.util.Arrays.copyOf(this, size)");
        return UIntArray.constructor-impl(iArr);
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final long[] copyOf-QwZRm1k(@NotNull long[] jArr) {
        jArr = Arrays.copyOf(jArr, jArr.length);
        Intrinsics.checkExpressionValueIsNotNull(jArr, "java.util.Arrays.copyOf(this, size)");
        return ULongArray.constructor-impl(jArr);
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final byte[] copyOf-GBYM_sE(@NotNull byte[] bArr) {
        bArr = Arrays.copyOf(bArr, bArr.length);
        Intrinsics.checkExpressionValueIsNotNull(bArr, "java.util.Arrays.copyOf(this, size)");
        return UByteArray.constructor-impl(bArr);
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final short[] copyOf-rL5Bavg(@NotNull short[] sArr) {
        sArr = Arrays.copyOf(sArr, sArr.length);
        Intrinsics.checkExpressionValueIsNotNull(sArr, "java.util.Arrays.copyOf(this, size)");
        return UShortArray.constructor-impl(sArr);
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final int[] copyOf-qFRl0hI(@NotNull int[] iArr, int i) {
        iArr = Arrays.copyOf(iArr, i);
        Intrinsics.checkExpressionValueIsNotNull(iArr, "java.util.Arrays.copyOf(this, newSize)");
        return UIntArray.constructor-impl(iArr);
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final long[] copyOf-r7IrZao(@NotNull long[] jArr, int i) {
        jArr = Arrays.copyOf(jArr, i);
        Intrinsics.checkExpressionValueIsNotNull(jArr, "java.util.Arrays.copyOf(this, newSize)");
        return ULongArray.constructor-impl(jArr);
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final byte[] copyOf-PpDY95g(@NotNull byte[] bArr, int i) {
        bArr = Arrays.copyOf(bArr, i);
        Intrinsics.checkExpressionValueIsNotNull(bArr, "java.util.Arrays.copyOf(this, newSize)");
        return UByteArray.constructor-impl(bArr);
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final short[] copyOf-nggk6HY(@NotNull short[] sArr, int i) {
        sArr = Arrays.copyOf(sArr, i);
        Intrinsics.checkExpressionValueIsNotNull(sArr, "java.util.Arrays.copyOf(this, newSize)");
        return UShortArray.constructor-impl(sArr);
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final int[] copyOfRange-oBK06Vg(@NotNull int[] iArr, int i, int i2) {
        if (PlatformImplementationsKt.apiVersionIsAtLeast(1, 3, 0)) {
            iArr = ArraysKt___ArraysJvmKt.copyOfRange(iArr, i, i2);
        } else if (i2 <= iArr.length) {
            iArr = Arrays.copyOfRange(iArr, i, i2);
            Intrinsics.checkExpressionValueIsNotNull(iArr, "java.util.Arrays.copyOfR…this, fromIndex, toIndex)");
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("toIndex: ");
            stringBuilder.append(i2);
            stringBuilder.append(", size: ");
            stringBuilder.append(iArr.length);
            throw ((Throwable) new IndexOutOfBoundsException(stringBuilder.toString()));
        }
        return UIntArray.constructor-impl(iArr);
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final long[] copyOfRange--nroSd4(@NotNull long[] jArr, int i, int i2) {
        if (PlatformImplementationsKt.apiVersionIsAtLeast(1, 3, 0)) {
            jArr = ArraysKt___ArraysJvmKt.copyOfRange(jArr, i, i2);
        } else if (i2 <= jArr.length) {
            jArr = Arrays.copyOfRange(jArr, i, i2);
            Intrinsics.checkExpressionValueIsNotNull(jArr, "java.util.Arrays.copyOfR…this, fromIndex, toIndex)");
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("toIndex: ");
            stringBuilder.append(i2);
            stringBuilder.append(", size: ");
            stringBuilder.append(jArr.length);
            throw ((Throwable) new IndexOutOfBoundsException(stringBuilder.toString()));
        }
        return ULongArray.constructor-impl(jArr);
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final byte[] copyOfRange-4UcCI2c(@NotNull byte[] bArr, int i, int i2) {
        if (PlatformImplementationsKt.apiVersionIsAtLeast(1, 3, 0)) {
            bArr = ArraysKt___ArraysJvmKt.copyOfRange(bArr, i, i2);
        } else if (i2 <= bArr.length) {
            bArr = Arrays.copyOfRange(bArr, i, i2);
            Intrinsics.checkExpressionValueIsNotNull(bArr, "java.util.Arrays.copyOfR…this, fromIndex, toIndex)");
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("toIndex: ");
            stringBuilder.append(i2);
            stringBuilder.append(", size: ");
            stringBuilder.append(bArr.length);
            throw ((Throwable) new IndexOutOfBoundsException(stringBuilder.toString()));
        }
        return UByteArray.constructor-impl(bArr);
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final short[] copyOfRange-Aa5vz7o(@NotNull short[] sArr, int i, int i2) {
        if (PlatformImplementationsKt.apiVersionIsAtLeast(1, 3, 0)) {
            sArr = ArraysKt___ArraysJvmKt.copyOfRange(sArr, i, i2);
        } else if (i2 <= sArr.length) {
            sArr = Arrays.copyOfRange(sArr, i, i2);
            Intrinsics.checkExpressionValueIsNotNull(sArr, "java.util.Arrays.copyOfR…this, fromIndex, toIndex)");
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("toIndex: ");
            stringBuilder.append(i2);
            stringBuilder.append(", size: ");
            stringBuilder.append(sArr.length);
            throw ((Throwable) new IndexOutOfBoundsException(stringBuilder.toString()));
        }
        return UShortArray.constructor-impl(sArr);
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final byte[] toByteArray-GBYM_sE(@NotNull byte[] bArr) {
        bArr = Arrays.copyOf(bArr, bArr.length);
        Intrinsics.checkExpressionValueIsNotNull(bArr, "java.util.Arrays.copyOf(this, size)");
        return bArr;
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final int[] toIntArray--ajY-9A(@NotNull int[] iArr) {
        iArr = Arrays.copyOf(iArr, iArr.length);
        Intrinsics.checkExpressionValueIsNotNull(iArr, "java.util.Arrays.copyOf(this, size)");
        return iArr;
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final long[] toLongArray-QwZRm1k(@NotNull long[] jArr) {
        jArr = Arrays.copyOf(jArr, jArr.length);
        Intrinsics.checkExpressionValueIsNotNull(jArr, "java.util.Arrays.copyOf(this, size)");
        return jArr;
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final short[] toShortArray-rL5Bavg(@NotNull short[] sArr) {
        sArr = Arrays.copyOf(sArr, sArr.length);
        Intrinsics.checkExpressionValueIsNotNull(sArr, "java.util.Arrays.copyOf(this, size)");
        return sArr;
    }

    @ExperimentalUnsignedTypes
    @NotNull
    @SinceKotlin(version = "1.3")
    public static final UInt[] toTypedArray--ajY-9A(@NotNull int[] iArr) {
        Intrinsics.checkParameterIsNotNull(iArr, "receiver$0");
        UInt[] uIntArr = new UInt[UIntArray.getSize-impl(iArr)];
        int length = uIntArr.length;
        for (int i = 0; i < length; i++) {
            uIntArr[i] = new UInt(UIntArray.get-impl(iArr, i));
        }
        return uIntArr;
    }

    @ExperimentalUnsignedTypes
    @NotNull
    @SinceKotlin(version = "1.3")
    public static final ULong[] toTypedArray-QwZRm1k(@NotNull long[] jArr) {
        Intrinsics.checkParameterIsNotNull(jArr, "receiver$0");
        ULong[] uLongArr = new ULong[ULongArray.getSize-impl(jArr)];
        int length = uLongArr.length;
        for (int i = 0; i < length; i++) {
            uLongArr[i] = new ULong(ULongArray.get-impl(jArr, i));
        }
        return uLongArr;
    }

    @ExperimentalUnsignedTypes
    @NotNull
    @SinceKotlin(version = "1.3")
    public static final UByte[] toTypedArray-GBYM_sE(@NotNull byte[] bArr) {
        Intrinsics.checkParameterIsNotNull(bArr, "receiver$0");
        UByte[] uByteArr = new UByte[UByteArray.getSize-impl(bArr)];
        int length = uByteArr.length;
        for (int i = 0; i < length; i++) {
            uByteArr[i] = new UByte(UByteArray.get-impl(bArr, i));
        }
        return uByteArr;
    }

    @ExperimentalUnsignedTypes
    @NotNull
    @SinceKotlin(version = "1.3")
    public static final UShort[] toTypedArray-rL5Bavg(@NotNull short[] sArr) {
        Intrinsics.checkParameterIsNotNull(sArr, "receiver$0");
        UShort[] uShortArr = new UShort[UShortArray.getSize-impl(sArr)];
        int length = uShortArr.length;
        for (int i = 0; i < length; i++) {
            uShortArr[i] = new UShort(UShortArray.get-impl(sArr, i));
        }
        return uShortArr;
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final byte[] toUByteArray(@NotNull byte[] bArr) {
        bArr = Arrays.copyOf(bArr, bArr.length);
        Intrinsics.checkExpressionValueIsNotNull(bArr, "java.util.Arrays.copyOf(this, size)");
        return UByteArray.constructor-impl(bArr);
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final int[] toUIntArray(@NotNull int[] iArr) {
        iArr = Arrays.copyOf(iArr, iArr.length);
        Intrinsics.checkExpressionValueIsNotNull(iArr, "java.util.Arrays.copyOf(this, size)");
        return UIntArray.constructor-impl(iArr);
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final long[] toULongArray(@NotNull long[] jArr) {
        jArr = Arrays.copyOf(jArr, jArr.length);
        Intrinsics.checkExpressionValueIsNotNull(jArr, "java.util.Arrays.copyOf(this, size)");
        return ULongArray.constructor-impl(jArr);
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final short[] toUShortArray(@NotNull short[] sArr) {
        sArr = Arrays.copyOf(sArr, sArr.length);
        Intrinsics.checkExpressionValueIsNotNull(sArr, "java.util.Arrays.copyOf(this, size)");
        return UShortArray.constructor-impl(sArr);
    }
}
