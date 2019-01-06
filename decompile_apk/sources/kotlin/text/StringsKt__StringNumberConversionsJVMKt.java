package kotlin.text;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.internal.InlineOnly;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000X\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000\n\u0002\u0010\n\n\u0002\b\u0002\u001a4\u0010\u0000\u001a\u0004\u0018\u0001H\u0001\"\u0004\b\u0000\u0010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u0002H\u00010\u0005H\b¢\u0006\u0004\b\u0006\u0010\u0007\u001a\r\u0010\b\u001a\u00020\t*\u00020\u0003H\b\u001a\u0015\u0010\b\u001a\u00020\t*\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH\b\u001a\u000e\u0010\f\u001a\u0004\u0018\u00010\t*\u00020\u0003H\u0007\u001a\u0016\u0010\f\u001a\u0004\u0018\u00010\t*\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH\u0007\u001a\r\u0010\r\u001a\u00020\u000e*\u00020\u0003H\b\u001a\u0015\u0010\r\u001a\u00020\u000e*\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0010H\b\u001a\u000e\u0010\u0011\u001a\u0004\u0018\u00010\u000e*\u00020\u0003H\u0007\u001a\u0016\u0010\u0011\u001a\u0004\u0018\u00010\u000e*\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0010H\u0007\u001a\r\u0010\u0012\u001a\u00020\u0013*\u00020\u0003H\b\u001a\r\u0010\u0014\u001a\u00020\u0015*\u00020\u0003H\b\u001a\u0015\u0010\u0014\u001a\u00020\u0015*\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0010H\b\u001a\r\u0010\u0016\u001a\u00020\u0017*\u00020\u0003H\b\u001a\u0013\u0010\u0018\u001a\u0004\u0018\u00010\u0017*\u00020\u0003H\u0007¢\u0006\u0002\u0010\u0019\u001a\r\u0010\u001a\u001a\u00020\u001b*\u00020\u0003H\b\u001a\u0013\u0010\u001c\u001a\u0004\u0018\u00010\u001b*\u00020\u0003H\u0007¢\u0006\u0002\u0010\u001d\u001a\r\u0010\u001e\u001a\u00020\u0010*\u00020\u0003H\b\u001a\u0015\u0010\u001e\u001a\u00020\u0010*\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0010H\b\u001a\r\u0010\u001f\u001a\u00020 *\u00020\u0003H\b\u001a\u0015\u0010\u001f\u001a\u00020 *\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0010H\b\u001a\r\u0010!\u001a\u00020\"*\u00020\u0003H\b\u001a\u0015\u0010!\u001a\u00020\"*\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0010H\b\u001a\u0015\u0010#\u001a\u00020\u0003*\u00020\u00152\u0006\u0010\u000f\u001a\u00020\u0010H\b\u001a\u0015\u0010#\u001a\u00020\u0003*\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u0010H\b\u001a\u0015\u0010#\u001a\u00020\u0003*\u00020 2\u0006\u0010\u000f\u001a\u00020\u0010H\b\u001a\u0015\u0010#\u001a\u00020\u0003*\u00020\"2\u0006\u0010\u000f\u001a\u00020\u0010H\b¨\u0006$"}, d2 = {"screenFloatValue", "T", "str", "", "parse", "Lkotlin/Function1;", "screenFloatValue$StringsKt__StringNumberConversionsJVMKt", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "toBigDecimal", "Ljava/math/BigDecimal;", "mathContext", "Ljava/math/MathContext;", "toBigDecimalOrNull", "toBigInteger", "Ljava/math/BigInteger;", "radix", "", "toBigIntegerOrNull", "toBoolean", "", "toByte", "", "toDouble", "", "toDoubleOrNull", "(Ljava/lang/String;)Ljava/lang/Double;", "toFloat", "", "toFloatOrNull", "(Ljava/lang/String;)Ljava/lang/Float;", "toInt", "toLong", "", "toShort", "", "toString", "kotlin-stdlib"}, k = 5, mv = {1, 1, 13}, xi = 1, xs = "kotlin/text/StringsKt")
/* compiled from: StringNumberConversionsJVM.kt */
class StringsKt__StringNumberConversionsJVMKt extends StringsKt__StringBuilderKt {
    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final String toString(byte b, int i) {
        b = Integer.toString(b, CharsKt__CharJVMKt.checkRadix(CharsKt__CharJVMKt.checkRadix(i)));
        Intrinsics.checkExpressionValueIsNotNull(b, "java.lang.Integer.toStri…(this, checkRadix(radix))");
        return b;
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final String toString(short s, int i) {
        s = Integer.toString(s, CharsKt__CharJVMKt.checkRadix(CharsKt__CharJVMKt.checkRadix(i)));
        Intrinsics.checkExpressionValueIsNotNull(s, "java.lang.Integer.toStri…(this, checkRadix(radix))");
        return s;
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final String toString(int i, int i2) {
        i = Integer.toString(i, CharsKt__CharJVMKt.checkRadix(i2));
        Intrinsics.checkExpressionValueIsNotNull(i, "java.lang.Integer.toStri…(this, checkRadix(radix))");
        return i;
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final String toString(long j, int i) {
        j = Long.toString(j, CharsKt__CharJVMKt.checkRadix(i));
        Intrinsics.checkExpressionValueIsNotNull(j, "java.lang.Long.toString(this, checkRadix(radix))");
        return j;
    }

    @InlineOnly
    private static final boolean toBoolean(@NotNull String str) {
        return Boolean.parseBoolean(str);
    }

    @InlineOnly
    private static final byte toByte(@NotNull String str) {
        return Byte.parseByte(str);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final byte toByte(@NotNull String str, int i) {
        return Byte.parseByte(str, CharsKt__CharJVMKt.checkRadix(i));
    }

    @InlineOnly
    private static final short toShort(@NotNull String str) {
        return Short.parseShort(str);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final short toShort(@NotNull String str, int i) {
        return Short.parseShort(str, CharsKt__CharJVMKt.checkRadix(i));
    }

    @InlineOnly
    private static final int toInt(@NotNull String str) {
        return Integer.parseInt(str);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final int toInt(@NotNull String str, int i) {
        return Integer.parseInt(str, CharsKt__CharJVMKt.checkRadix(i));
    }

    @InlineOnly
    private static final long toLong(@NotNull String str) {
        return Long.parseLong(str);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final long toLong(@NotNull String str, int i) {
        return Long.parseLong(str, CharsKt__CharJVMKt.checkRadix(i));
    }

    @InlineOnly
    private static final float toFloat(@NotNull String str) {
        return Float.parseFloat(str);
    }

    @InlineOnly
    private static final double toDouble(@NotNull String str) {
        return Double.parseDouble(str);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigInteger toBigInteger(@NotNull String str) {
        return new BigInteger(str);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigInteger toBigInteger(@NotNull String str, int i) {
        return new BigInteger(str, CharsKt__CharJVMKt.checkRadix(i));
    }

    @SinceKotlin(version = "1.2")
    @Nullable
    public static final BigInteger toBigIntegerOrNull(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "receiver$0");
        return toBigIntegerOrNull(str, 10);
    }

    @SinceKotlin(version = "1.2")
    @Nullable
    public static final BigInteger toBigIntegerOrNull(@NotNull String str, int i) {
        Intrinsics.checkParameterIsNotNull(str, "receiver$0");
        CharsKt__CharJVMKt.checkRadix(i);
        int length = str.length();
        int i2 = 0;
        switch (length) {
            case 0:
                return null;
            case 1:
                if (CharsKt__CharJVMKt.digitOf(str.charAt(0), i) < 0) {
                    return null;
                }
                break;
            default:
                if (str.charAt(0) == '-') {
                    i2 = 1;
                }
                while (i2 < length) {
                    if (CharsKt__CharJVMKt.digitOf(str.charAt(i2), i) < 0) {
                        return null;
                    }
                    i2++;
                }
                break;
        }
        return new BigInteger(str, CharsKt__CharJVMKt.checkRadix(i));
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigDecimal toBigDecimal(@NotNull String str) {
        return new BigDecimal(str);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigDecimal toBigDecimal(@NotNull String str, MathContext mathContext) {
        return new BigDecimal(str, mathContext);
    }

    private static final <T> T screenFloatValue$StringsKt__StringNumberConversionsJVMKt(java.lang.String r3, kotlin.jvm.functions.Function1<? super java.lang.String, ? extends T> r4) {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1603198149.run(Unknown Source)
*/
        /*
        r0 = 0;
        r1 = kotlin.text.ScreenFloatValueRegEx.value;	 Catch:{ NumberFormatException -> 0x0011 }
        r2 = r3;	 Catch:{ NumberFormatException -> 0x0011 }
        r2 = (java.lang.CharSequence) r2;	 Catch:{ NumberFormatException -> 0x0011 }
        r1 = r1.matches(r2);	 Catch:{ NumberFormatException -> 0x0011 }
        if (r1 == 0) goto L_0x0011;	 Catch:{ NumberFormatException -> 0x0011 }
    L_0x000c:
        r3 = r4.invoke(r3);	 Catch:{ NumberFormatException -> 0x0011 }
        r0 = r3;
    L_0x0011:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.text.StringsKt__StringNumberConversionsJVMKt.screenFloatValue$StringsKt__StringNumberConversionsJVMKt(java.lang.String, kotlin.jvm.functions.Function1):T");
    }

    @kotlin.SinceKotlin(version = "1.1")
    @org.jetbrains.annotations.Nullable
    public static final java.lang.Float toFloatOrNull(@org.jetbrains.annotations.NotNull java.lang.String r3) {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1603198149.run(Unknown Source)
*/
        /*
        r0 = "receiver$0";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r3, r0);
        r0 = 0;
        r1 = kotlin.text.ScreenFloatValueRegEx.value;	 Catch:{ NumberFormatException -> 0x001a }
        r2 = r3;	 Catch:{ NumberFormatException -> 0x001a }
        r2 = (java.lang.CharSequence) r2;	 Catch:{ NumberFormatException -> 0x001a }
        r1 = r1.matches(r2);	 Catch:{ NumberFormatException -> 0x001a }
        if (r1 == 0) goto L_0x001a;	 Catch:{ NumberFormatException -> 0x001a }
    L_0x0011:
        r3 = java.lang.Float.parseFloat(r3);	 Catch:{ NumberFormatException -> 0x001a }
        r3 = java.lang.Float.valueOf(r3);	 Catch:{ NumberFormatException -> 0x001a }
        r0 = r3;
    L_0x001a:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.text.StringsKt__StringNumberConversionsJVMKt.toFloatOrNull(java.lang.String):java.lang.Float");
    }

    @kotlin.SinceKotlin(version = "1.1")
    @org.jetbrains.annotations.Nullable
    public static final java.lang.Double toDoubleOrNull(@org.jetbrains.annotations.NotNull java.lang.String r3) {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1603198149.run(Unknown Source)
*/
        /*
        r0 = "receiver$0";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r3, r0);
        r0 = 0;
        r1 = kotlin.text.ScreenFloatValueRegEx.value;	 Catch:{ NumberFormatException -> 0x001a }
        r2 = r3;	 Catch:{ NumberFormatException -> 0x001a }
        r2 = (java.lang.CharSequence) r2;	 Catch:{ NumberFormatException -> 0x001a }
        r1 = r1.matches(r2);	 Catch:{ NumberFormatException -> 0x001a }
        if (r1 == 0) goto L_0x001a;	 Catch:{ NumberFormatException -> 0x001a }
    L_0x0011:
        r1 = java.lang.Double.parseDouble(r3);	 Catch:{ NumberFormatException -> 0x001a }
        r3 = java.lang.Double.valueOf(r1);	 Catch:{ NumberFormatException -> 0x001a }
        r0 = r3;
    L_0x001a:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.text.StringsKt__StringNumberConversionsJVMKt.toDoubleOrNull(java.lang.String):java.lang.Double");
    }

    @kotlin.SinceKotlin(version = "1.2")
    @org.jetbrains.annotations.Nullable
    public static final java.math.BigDecimal toBigDecimalOrNull(@org.jetbrains.annotations.NotNull java.lang.String r3) {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1603198149.run(Unknown Source)
*/
        /*
        r0 = "receiver$0";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r3, r0);
        r0 = 0;
        r1 = kotlin.text.ScreenFloatValueRegEx.value;	 Catch:{ NumberFormatException -> 0x0017 }
        r2 = r3;	 Catch:{ NumberFormatException -> 0x0017 }
        r2 = (java.lang.CharSequence) r2;	 Catch:{ NumberFormatException -> 0x0017 }
        r1 = r1.matches(r2);	 Catch:{ NumberFormatException -> 0x0017 }
        if (r1 == 0) goto L_0x0017;	 Catch:{ NumberFormatException -> 0x0017 }
    L_0x0011:
        r1 = new java.math.BigDecimal;	 Catch:{ NumberFormatException -> 0x0017 }
        r1.<init>(r3);	 Catch:{ NumberFormatException -> 0x0017 }
        r0 = r1;
    L_0x0017:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.text.StringsKt__StringNumberConversionsJVMKt.toBigDecimalOrNull(java.lang.String):java.math.BigDecimal");
    }

    @kotlin.SinceKotlin(version = "1.2")
    @org.jetbrains.annotations.Nullable
    public static final java.math.BigDecimal toBigDecimalOrNull(@org.jetbrains.annotations.NotNull java.lang.String r3, @org.jetbrains.annotations.NotNull java.math.MathContext r4) {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1603198149.run(Unknown Source)
*/
        /*
        r0 = "receiver$0";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r3, r0);
        r0 = "mathContext";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r4, r0);
        r0 = 0;
        r1 = kotlin.text.ScreenFloatValueRegEx.value;	 Catch:{ NumberFormatException -> 0x001c }
        r2 = r3;	 Catch:{ NumberFormatException -> 0x001c }
        r2 = (java.lang.CharSequence) r2;	 Catch:{ NumberFormatException -> 0x001c }
        r1 = r1.matches(r2);	 Catch:{ NumberFormatException -> 0x001c }
        if (r1 == 0) goto L_0x001c;	 Catch:{ NumberFormatException -> 0x001c }
    L_0x0016:
        r1 = new java.math.BigDecimal;	 Catch:{ NumberFormatException -> 0x001c }
        r1.<init>(r3, r4);	 Catch:{ NumberFormatException -> 0x001c }
        r0 = r1;
    L_0x001c:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.text.StringsKt__StringNumberConversionsJVMKt.toBigDecimalOrNull(java.lang.String, java.math.MathContext):java.math.BigDecimal");
    }
}
