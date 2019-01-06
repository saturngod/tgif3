package kotlin.text;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0000\n\u0002\u0010\u0001\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0005\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\n\n\u0002\b\u0003\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0000\u001a\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005*\u00020\u0003H\u0007¢\u0006\u0002\u0010\u0006\u001a\u001b\u0010\u0004\u001a\u0004\u0018\u00010\u0005*\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH\u0007¢\u0006\u0002\u0010\t\u001a\u0013\u0010\n\u001a\u0004\u0018\u00010\b*\u00020\u0003H\u0007¢\u0006\u0002\u0010\u000b\u001a\u001b\u0010\n\u001a\u0004\u0018\u00010\b*\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH\u0007¢\u0006\u0002\u0010\f\u001a\u0013\u0010\r\u001a\u0004\u0018\u00010\u000e*\u00020\u0003H\u0007¢\u0006\u0002\u0010\u000f\u001a\u001b\u0010\r\u001a\u0004\u0018\u00010\u000e*\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH\u0007¢\u0006\u0002\u0010\u0010\u001a\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u0012*\u00020\u0003H\u0007¢\u0006\u0002\u0010\u0013\u001a\u001b\u0010\u0011\u001a\u0004\u0018\u00010\u0012*\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH\u0007¢\u0006\u0002\u0010\u0014¨\u0006\u0015"}, d2 = {"numberFormatError", "", "input", "", "toByteOrNull", "", "(Ljava/lang/String;)Ljava/lang/Byte;", "radix", "", "(Ljava/lang/String;I)Ljava/lang/Byte;", "toIntOrNull", "(Ljava/lang/String;)Ljava/lang/Integer;", "(Ljava/lang/String;I)Ljava/lang/Integer;", "toLongOrNull", "", "(Ljava/lang/String;)Ljava/lang/Long;", "(Ljava/lang/String;I)Ljava/lang/Long;", "toShortOrNull", "", "(Ljava/lang/String;)Ljava/lang/Short;", "(Ljava/lang/String;I)Ljava/lang/Short;", "kotlin-stdlib"}, k = 5, mv = {1, 1, 13}, xi = 1, xs = "kotlin/text/StringsKt")
/* compiled from: StringNumberConversions.kt */
class StringsKt__StringNumberConversionsKt extends StringsKt__StringNumberConversionsJVMKt {
    @SinceKotlin(version = "1.1")
    @Nullable
    public static final Byte toByteOrNull(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "receiver$0");
        return toByteOrNull(str, 10);
    }

    @SinceKotlin(version = "1.1")
    @Nullable
    public static final Byte toByteOrNull(@NotNull String str, int i) {
        Intrinsics.checkParameterIsNotNull(str, "receiver$0");
        str = toIntOrNull(str, i);
        if (str == null) {
            return null;
        }
        str = str.intValue();
        if (str >= -128) {
            if (str <= 127) {
                return Byte.valueOf((byte) str);
            }
        }
        return null;
    }

    @SinceKotlin(version = "1.1")
    @Nullable
    public static final Short toShortOrNull(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "receiver$0");
        return toShortOrNull(str, 10);
    }

    @SinceKotlin(version = "1.1")
    @Nullable
    public static final Short toShortOrNull(@NotNull String str, int i) {
        Intrinsics.checkParameterIsNotNull(str, "receiver$0");
        str = toIntOrNull(str, i);
        if (str == null) {
            return null;
        }
        str = str.intValue();
        if (str >= -32768) {
            if (str <= 32767) {
                return Short.valueOf((short) str);
            }
        }
        return null;
    }

    @SinceKotlin(version = "1.1")
    @Nullable
    public static final Integer toIntOrNull(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "receiver$0");
        return toIntOrNull(str, 10);
    }

    @SinceKotlin(version = "1.1")
    @Nullable
    public static final Integer toIntOrNull(@NotNull String str, int i) {
        Intrinsics.checkParameterIsNotNull(str, "receiver$0");
        CharsKt__CharJVMKt.checkRadix(i);
        int length = str.length();
        if (length == 0) {
            return null;
        }
        int i2;
        Object obj;
        int i3;
        int digitOf;
        int i4 = 0;
        char charAt = str.charAt(0);
        int i5 = -2147483647;
        if (charAt >= '0') {
            i2 = 0;
        } else if (length == 1) {
            return null;
        } else {
            if (charAt == '-') {
                i5 = Integer.MIN_VALUE;
                i2 = 1;
                obj = 1;
                i3 = i5 / i;
                length--;
                if (i2 <= length) {
                    while (true) {
                        digitOf = CharsKt__CharJVMKt.digitOf(str.charAt(i2), i);
                        if (digitOf < 0 && i4 >= i3) {
                            i4 *= i;
                            if (i4 >= i5 + digitOf) {
                                i4 -= digitOf;
                                if (i2 != length) {
                                    break;
                                }
                                i2++;
                            } else {
                                return null;
                            }
                        }
                        return null;
                    }
                }
                return obj == null ? Integer.valueOf(i4) : Integer.valueOf(-i4);
            } else if (charAt != '+') {
                return null;
            } else {
                i2 = 1;
            }
        }
        obj = null;
        i3 = i5 / i;
        length--;
        if (i2 <= length) {
            while (true) {
                digitOf = CharsKt__CharJVMKt.digitOf(str.charAt(i2), i);
                if (digitOf < 0) {
                    return null;
                }
                i4 *= i;
                if (i4 >= i5 + digitOf) {
                    return null;
                }
                i4 -= digitOf;
                if (i2 != length) {
                    break;
                }
                i2++;
            }
        }
        if (obj == null) {
        }
        return obj == null ? Integer.valueOf(i4) : Integer.valueOf(-i4);
    }

    @SinceKotlin(version = "1.1")
    @Nullable
    public static final Long toLongOrNull(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "receiver$0");
        return toLongOrNull(str, 10);
    }

    @SinceKotlin(version = "1.1")
    @Nullable
    public static final Long toLongOrNull(@NotNull String str, int i) {
        String str2 = str;
        int i2 = i;
        Intrinsics.checkParameterIsNotNull(str2, "receiver$0");
        CharsKt__CharJVMKt.checkRadix(i);
        int length = str.length();
        Long l = null;
        if (length == 0) {
            return null;
        }
        Object obj;
        long j;
        long j2;
        long j3;
        int digitOf;
        int i3;
        long j4;
        int i4 = 0;
        char charAt = str2.charAt(0);
        long j5 = -9223372036854775807L;
        if (charAt < '0') {
            if (length == 1) {
                return null;
            }
            if (charAt == '-') {
                j5 = Long.MIN_VALUE;
                i4 = 1;
                obj = 1;
                j = (long) i2;
                j2 = j5 / j;
                j3 = 0;
                length--;
                if (i4 <= length) {
                    while (true) {
                        digitOf = CharsKt__CharJVMKt.digitOf(str2.charAt(i4), i2);
                        if (digitOf < 0 && j3 >= j2) {
                            j3 *= j;
                            i3 = i4;
                            j4 = (long) digitOf;
                            if (j3 >= j5 + j4) {
                                j3 -= j4;
                                i4 = i3;
                                if (i4 != length) {
                                    break;
                                }
                                i4++;
                                l = null;
                            } else {
                                return null;
                            }
                        }
                        return l;
                    }
                }
                return obj == null ? Long.valueOf(j3) : Long.valueOf(-j3);
            } else if (charAt != '+') {
                return null;
            } else {
                i4 = 1;
            }
        }
        obj = null;
        j = (long) i2;
        j2 = j5 / j;
        j3 = 0;
        length--;
        if (i4 <= length) {
            while (true) {
                digitOf = CharsKt__CharJVMKt.digitOf(str2.charAt(i4), i2);
                if (digitOf < 0) {
                    return l;
                }
                j3 *= j;
                i3 = i4;
                j4 = (long) digitOf;
                if (j3 >= j5 + j4) {
                    return null;
                }
                j3 -= j4;
                i4 = i3;
                if (i4 != length) {
                    break;
                }
                i4++;
                l = null;
            }
        }
        if (obj == null) {
        }
        return obj == null ? Long.valueOf(j3) : Long.valueOf(-j3);
    }

    @NotNull
    public static final Void numberFormatError(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "input");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Invalid number format: '");
        stringBuilder.append(str);
        stringBuilder.append('\'');
        throw new NumberFormatException(stringBuilder.toString());
    }
}
