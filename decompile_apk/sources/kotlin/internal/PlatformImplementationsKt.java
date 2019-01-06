package kotlin.internal;

import kotlin.KotlinVersion;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.SinceKotlin;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0004\u001a \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0005H\u0001\u001a\"\u0010\b\u001a\u0002H\t\"\n\b\u0000\u0010\t\u0018\u0001*\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\b¢\u0006\u0002\u0010\f\u001a\b\u0010\r\u001a\u00020\u0005H\u0002\"\u0010\u0010\u0000\u001a\u00020\u00018\u0000X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"IMPLEMENTATIONS", "Lkotlin/internal/PlatformImplementations;", "apiVersionIsAtLeast", "", "major", "", "minor", "patch", "castToBaseType", "T", "", "instance", "(Ljava/lang/Object;)Ljava/lang/Object;", "getJavaVersion", "kotlin-stdlib"}, k = 2, mv = {1, 1, 13})
/* compiled from: PlatformImplementations.kt */
public final class PlatformImplementationsKt {
    @NotNull
    @JvmField
    public static final PlatformImplementations IMPLEMENTATIONS;

    static {
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
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1603198149.run(Unknown Source)
*/
        /*
        r0 = getJavaVersion();
        r1 = 65544; // 0x10008 float:9.1847E-41 double:3.2383E-319;
        if (r0 < r1) goto L_0x00b7;
    L_0x0009:
        r1 = "kotlin.internal.jdk8.JDK8PlatformImplementations";	 Catch:{ ClassNotFoundException -> 0x0060 }
        r1 = java.lang.Class.forName(r1);	 Catch:{ ClassNotFoundException -> 0x0060 }
        r1 = r1.newInstance();	 Catch:{ ClassNotFoundException -> 0x0060 }
        r2 = "Class.forName(\"kotlin.in…entations\").newInstance()";	 Catch:{ ClassNotFoundException -> 0x0060 }
        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r2);	 Catch:{ ClassNotFoundException -> 0x0060 }
        if (r1 == 0) goto L_0x001f;
    L_0x001a:
        r2 = r1;	 Catch:{ ClassCastException -> 0x0027 }
        r2 = (kotlin.internal.PlatformImplementations) r2;	 Catch:{ ClassCastException -> 0x0027 }
        goto L_0x016e;	 Catch:{ ClassCastException -> 0x0027 }
    L_0x001f:
        r2 = new kotlin.TypeCastException;	 Catch:{ ClassCastException -> 0x0027 }
        r3 = "null cannot be cast to non-null type kotlin.internal.PlatformImplementations";	 Catch:{ ClassCastException -> 0x0027 }
        r2.<init>(r3);	 Catch:{ ClassCastException -> 0x0027 }
        throw r2;	 Catch:{ ClassCastException -> 0x0027 }
    L_0x0027:
        r2 = move-exception;
        r1 = r1.getClass();	 Catch:{ ClassNotFoundException -> 0x0060 }
        r1 = r1.getClassLoader();	 Catch:{ ClassNotFoundException -> 0x0060 }
        r3 = kotlin.internal.PlatformImplementations.class;	 Catch:{ ClassNotFoundException -> 0x0060 }
        r3 = r3.getClassLoader();	 Catch:{ ClassNotFoundException -> 0x0060 }
        r4 = new java.lang.ClassCastException;	 Catch:{ ClassNotFoundException -> 0x0060 }
        r5 = new java.lang.StringBuilder;	 Catch:{ ClassNotFoundException -> 0x0060 }
        r5.<init>();	 Catch:{ ClassNotFoundException -> 0x0060 }
        r6 = "Instance classloader: ";	 Catch:{ ClassNotFoundException -> 0x0060 }
        r5.append(r6);	 Catch:{ ClassNotFoundException -> 0x0060 }
        r5.append(r1);	 Catch:{ ClassNotFoundException -> 0x0060 }
        r1 = ", base type classloader: ";	 Catch:{ ClassNotFoundException -> 0x0060 }
        r5.append(r1);	 Catch:{ ClassNotFoundException -> 0x0060 }
        r5.append(r3);	 Catch:{ ClassNotFoundException -> 0x0060 }
        r1 = r5.toString();	 Catch:{ ClassNotFoundException -> 0x0060 }
        r4.<init>(r1);	 Catch:{ ClassNotFoundException -> 0x0060 }
        r2 = (java.lang.Throwable) r2;	 Catch:{ ClassNotFoundException -> 0x0060 }
        r1 = r4.initCause(r2);	 Catch:{ ClassNotFoundException -> 0x0060 }
        r2 = "ClassCastException(\"Inst…baseTypeCL\").initCause(e)";	 Catch:{ ClassNotFoundException -> 0x0060 }
        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r2);	 Catch:{ ClassNotFoundException -> 0x0060 }
        throw r1;	 Catch:{ ClassNotFoundException -> 0x0060 }
    L_0x0060:
        r1 = "kotlin.internal.JRE8PlatformImplementations";	 Catch:{ ClassNotFoundException -> 0x00b7 }
        r1 = java.lang.Class.forName(r1);	 Catch:{ ClassNotFoundException -> 0x00b7 }
        r1 = r1.newInstance();	 Catch:{ ClassNotFoundException -> 0x00b7 }
        r2 = "Class.forName(\"kotlin.in…entations\").newInstance()";	 Catch:{ ClassNotFoundException -> 0x00b7 }
        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r2);	 Catch:{ ClassNotFoundException -> 0x00b7 }
        if (r1 == 0) goto L_0x0076;
    L_0x0071:
        r2 = r1;	 Catch:{ ClassCastException -> 0x007e }
        r2 = (kotlin.internal.PlatformImplementations) r2;	 Catch:{ ClassCastException -> 0x007e }
        goto L_0x016e;	 Catch:{ ClassCastException -> 0x007e }
    L_0x0076:
        r2 = new kotlin.TypeCastException;	 Catch:{ ClassCastException -> 0x007e }
        r3 = "null cannot be cast to non-null type kotlin.internal.PlatformImplementations";	 Catch:{ ClassCastException -> 0x007e }
        r2.<init>(r3);	 Catch:{ ClassCastException -> 0x007e }
        throw r2;	 Catch:{ ClassCastException -> 0x007e }
    L_0x007e:
        r2 = move-exception;
        r1 = r1.getClass();	 Catch:{ ClassNotFoundException -> 0x00b7 }
        r1 = r1.getClassLoader();	 Catch:{ ClassNotFoundException -> 0x00b7 }
        r3 = kotlin.internal.PlatformImplementations.class;	 Catch:{ ClassNotFoundException -> 0x00b7 }
        r3 = r3.getClassLoader();	 Catch:{ ClassNotFoundException -> 0x00b7 }
        r4 = new java.lang.ClassCastException;	 Catch:{ ClassNotFoundException -> 0x00b7 }
        r5 = new java.lang.StringBuilder;	 Catch:{ ClassNotFoundException -> 0x00b7 }
        r5.<init>();	 Catch:{ ClassNotFoundException -> 0x00b7 }
        r6 = "Instance classloader: ";	 Catch:{ ClassNotFoundException -> 0x00b7 }
        r5.append(r6);	 Catch:{ ClassNotFoundException -> 0x00b7 }
        r5.append(r1);	 Catch:{ ClassNotFoundException -> 0x00b7 }
        r1 = ", base type classloader: ";	 Catch:{ ClassNotFoundException -> 0x00b7 }
        r5.append(r1);	 Catch:{ ClassNotFoundException -> 0x00b7 }
        r5.append(r3);	 Catch:{ ClassNotFoundException -> 0x00b7 }
        r1 = r5.toString();	 Catch:{ ClassNotFoundException -> 0x00b7 }
        r4.<init>(r1);	 Catch:{ ClassNotFoundException -> 0x00b7 }
        r2 = (java.lang.Throwable) r2;	 Catch:{ ClassNotFoundException -> 0x00b7 }
        r1 = r4.initCause(r2);	 Catch:{ ClassNotFoundException -> 0x00b7 }
        r2 = "ClassCastException(\"Inst…baseTypeCL\").initCause(e)";	 Catch:{ ClassNotFoundException -> 0x00b7 }
        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r2);	 Catch:{ ClassNotFoundException -> 0x00b7 }
        throw r1;	 Catch:{ ClassNotFoundException -> 0x00b7 }
    L_0x00b7:
        r1 = 65543; // 0x10007 float:9.1845E-41 double:3.23825E-319;
        if (r0 < r1) goto L_0x0169;
    L_0x00bc:
        r0 = "kotlin.internal.jdk7.JDK7PlatformImplementations";	 Catch:{ ClassNotFoundException -> 0x0113 }
        r0 = java.lang.Class.forName(r0);	 Catch:{ ClassNotFoundException -> 0x0113 }
        r0 = r0.newInstance();	 Catch:{ ClassNotFoundException -> 0x0113 }
        r1 = "Class.forName(\"kotlin.in…entations\").newInstance()";	 Catch:{ ClassNotFoundException -> 0x0113 }
        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1);	 Catch:{ ClassNotFoundException -> 0x0113 }
        if (r0 == 0) goto L_0x00d2;
    L_0x00cd:
        r2 = r0;	 Catch:{ ClassCastException -> 0x00da }
        r2 = (kotlin.internal.PlatformImplementations) r2;	 Catch:{ ClassCastException -> 0x00da }
        goto L_0x016e;	 Catch:{ ClassCastException -> 0x00da }
    L_0x00d2:
        r1 = new kotlin.TypeCastException;	 Catch:{ ClassCastException -> 0x00da }
        r2 = "null cannot be cast to non-null type kotlin.internal.PlatformImplementations";	 Catch:{ ClassCastException -> 0x00da }
        r1.<init>(r2);	 Catch:{ ClassCastException -> 0x00da }
        throw r1;	 Catch:{ ClassCastException -> 0x00da }
    L_0x00da:
        r1 = move-exception;
        r0 = r0.getClass();	 Catch:{ ClassNotFoundException -> 0x0113 }
        r0 = r0.getClassLoader();	 Catch:{ ClassNotFoundException -> 0x0113 }
        r2 = kotlin.internal.PlatformImplementations.class;	 Catch:{ ClassNotFoundException -> 0x0113 }
        r2 = r2.getClassLoader();	 Catch:{ ClassNotFoundException -> 0x0113 }
        r3 = new java.lang.ClassCastException;	 Catch:{ ClassNotFoundException -> 0x0113 }
        r4 = new java.lang.StringBuilder;	 Catch:{ ClassNotFoundException -> 0x0113 }
        r4.<init>();	 Catch:{ ClassNotFoundException -> 0x0113 }
        r5 = "Instance classloader: ";	 Catch:{ ClassNotFoundException -> 0x0113 }
        r4.append(r5);	 Catch:{ ClassNotFoundException -> 0x0113 }
        r4.append(r0);	 Catch:{ ClassNotFoundException -> 0x0113 }
        r0 = ", base type classloader: ";	 Catch:{ ClassNotFoundException -> 0x0113 }
        r4.append(r0);	 Catch:{ ClassNotFoundException -> 0x0113 }
        r4.append(r2);	 Catch:{ ClassNotFoundException -> 0x0113 }
        r0 = r4.toString();	 Catch:{ ClassNotFoundException -> 0x0113 }
        r3.<init>(r0);	 Catch:{ ClassNotFoundException -> 0x0113 }
        r1 = (java.lang.Throwable) r1;	 Catch:{ ClassNotFoundException -> 0x0113 }
        r0 = r3.initCause(r1);	 Catch:{ ClassNotFoundException -> 0x0113 }
        r1 = "ClassCastException(\"Inst…baseTypeCL\").initCause(e)";	 Catch:{ ClassNotFoundException -> 0x0113 }
        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1);	 Catch:{ ClassNotFoundException -> 0x0113 }
        throw r0;	 Catch:{ ClassNotFoundException -> 0x0113 }
    L_0x0113:
        r0 = "kotlin.internal.JRE7PlatformImplementations";	 Catch:{ ClassNotFoundException -> 0x0169 }
        r0 = java.lang.Class.forName(r0);	 Catch:{ ClassNotFoundException -> 0x0169 }
        r0 = r0.newInstance();	 Catch:{ ClassNotFoundException -> 0x0169 }
        r1 = "Class.forName(\"kotlin.in…entations\").newInstance()";	 Catch:{ ClassNotFoundException -> 0x0169 }
        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1);	 Catch:{ ClassNotFoundException -> 0x0169 }
        if (r0 == 0) goto L_0x0128;
    L_0x0124:
        r2 = r0;	 Catch:{ ClassCastException -> 0x0130 }
        r2 = (kotlin.internal.PlatformImplementations) r2;	 Catch:{ ClassCastException -> 0x0130 }
        goto L_0x016e;	 Catch:{ ClassCastException -> 0x0130 }
    L_0x0128:
        r1 = new kotlin.TypeCastException;	 Catch:{ ClassCastException -> 0x0130 }
        r2 = "null cannot be cast to non-null type kotlin.internal.PlatformImplementations";	 Catch:{ ClassCastException -> 0x0130 }
        r1.<init>(r2);	 Catch:{ ClassCastException -> 0x0130 }
        throw r1;	 Catch:{ ClassCastException -> 0x0130 }
    L_0x0130:
        r1 = move-exception;
        r0 = r0.getClass();	 Catch:{ ClassNotFoundException -> 0x0169 }
        r0 = r0.getClassLoader();	 Catch:{ ClassNotFoundException -> 0x0169 }
        r2 = kotlin.internal.PlatformImplementations.class;	 Catch:{ ClassNotFoundException -> 0x0169 }
        r2 = r2.getClassLoader();	 Catch:{ ClassNotFoundException -> 0x0169 }
        r3 = new java.lang.ClassCastException;	 Catch:{ ClassNotFoundException -> 0x0169 }
        r4 = new java.lang.StringBuilder;	 Catch:{ ClassNotFoundException -> 0x0169 }
        r4.<init>();	 Catch:{ ClassNotFoundException -> 0x0169 }
        r5 = "Instance classloader: ";	 Catch:{ ClassNotFoundException -> 0x0169 }
        r4.append(r5);	 Catch:{ ClassNotFoundException -> 0x0169 }
        r4.append(r0);	 Catch:{ ClassNotFoundException -> 0x0169 }
        r0 = ", base type classloader: ";	 Catch:{ ClassNotFoundException -> 0x0169 }
        r4.append(r0);	 Catch:{ ClassNotFoundException -> 0x0169 }
        r4.append(r2);	 Catch:{ ClassNotFoundException -> 0x0169 }
        r0 = r4.toString();	 Catch:{ ClassNotFoundException -> 0x0169 }
        r3.<init>(r0);	 Catch:{ ClassNotFoundException -> 0x0169 }
        r1 = (java.lang.Throwable) r1;	 Catch:{ ClassNotFoundException -> 0x0169 }
        r0 = r3.initCause(r1);	 Catch:{ ClassNotFoundException -> 0x0169 }
        r1 = "ClassCastException(\"Inst…baseTypeCL\").initCause(e)";	 Catch:{ ClassNotFoundException -> 0x0169 }
        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1);	 Catch:{ ClassNotFoundException -> 0x0169 }
        throw r0;	 Catch:{ ClassNotFoundException -> 0x0169 }
    L_0x0169:
        r2 = new kotlin.internal.PlatformImplementations;
        r2.<init>();
    L_0x016e:
        IMPLEMENTATIONS = r2;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.internal.PlatformImplementationsKt.<clinit>():void");
    }

    @InlineOnly
    private static final <T> T castToBaseType(Object obj) {
        try {
            Intrinsics.reifiedOperationMarker(1, "T");
            return obj;
        } catch (ClassCastException e) {
            obj = obj.getClass().getClassLoader();
            Intrinsics.reifiedOperationMarker(4, "T");
            ClassLoader classLoader = Object.class.getClassLoader();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Instance classloader: ");
            stringBuilder.append(obj);
            stringBuilder.append(", base type classloader: ");
            stringBuilder.append(classLoader);
            obj = new ClassCastException(stringBuilder.toString()).initCause(e);
            Intrinsics.checkExpressionValueIsNotNull(obj, "ClassCastException(\"Inst…baseTypeCL\").initCause(e)");
            throw obj;
        }
    }

    private static final int getJavaVersion() {
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
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1603198149.run(Unknown Source)
*/
        /*
        r0 = "java.specification.version";
        r0 = java.lang.System.getProperty(r0);
        r1 = 65542; // 0x10006 float:9.1844E-41 double:3.2382E-319;
        if (r0 == 0) goto L_0x006b;
    L_0x000b:
        r8 = r0;
        r8 = (java.lang.CharSequence) r8;
        r3 = 46;
        r4 = 0;
        r5 = 0;
        r6 = 6;
        r7 = 0;
        r2 = r8;
        r9 = kotlin.text.StringsKt__StringsKt.indexOf$default(r2, r3, r4, r5, r6, r7);
        r10 = 65536; // 0x10000 float:9.18355E-41 double:3.2379E-319;
        if (r9 >= 0) goto L_0x0024;
    L_0x001d:
        r0 = java.lang.Integer.parseInt(r0);	 Catch:{ NumberFormatException -> 0x0023 }
        r1 = r0 * r10;
    L_0x0023:
        return r1;
    L_0x0024:
        r3 = 46;
        r11 = r9 + 1;
        r5 = 0;
        r6 = 4;
        r7 = 0;
        r2 = r8;
        r4 = r11;
        r2 = kotlin.text.StringsKt__StringsKt.indexOf$default(r2, r3, r4, r5, r6, r7);
        if (r2 >= 0) goto L_0x0037;
    L_0x0033:
        r2 = r0.length();
    L_0x0037:
        r3 = 0;
        if (r0 == 0) goto L_0x0063;
    L_0x003a:
        r3 = r0.substring(r3, r9);
        r4 = "(this as java.lang.Strin…ing(startIndex, endIndex)";
        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r3, r4);
        if (r0 == 0) goto L_0x005b;
    L_0x0045:
        r0 = r0.substring(r11, r2);
        r2 = "(this as java.lang.Strin…ing(startIndex, endIndex)";
        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r2);
        r2 = java.lang.Integer.parseInt(r3);	 Catch:{ NumberFormatException -> 0x005a }
        r2 = r2 * r10;	 Catch:{ NumberFormatException -> 0x005a }
        r0 = java.lang.Integer.parseInt(r0);	 Catch:{ NumberFormatException -> 0x005a }
        r1 = r2 + r0;
    L_0x005a:
        return r1;
    L_0x005b:
        r0 = new kotlin.TypeCastException;
        r1 = "null cannot be cast to non-null type java.lang.String";
        r0.<init>(r1);
        throw r0;
    L_0x0063:
        r0 = new kotlin.TypeCastException;
        r1 = "null cannot be cast to non-null type java.lang.String";
        r0.<init>(r1);
        throw r0;
    L_0x006b:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.internal.PlatformImplementationsKt.getJavaVersion():int");
    }

    @SinceKotlin(version = "1.2")
    @PublishedApi
    public static final boolean apiVersionIsAtLeast(int i, int i2, int i3) {
        return KotlinVersion.CURRENT.isAtLeast(i, i2, i3);
    }
}
