package kotlinx.coroutines.android;

import java.lang.reflect.Method;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0002"}, d2 = {"getter", "Ljava/lang/reflect/Method;", "kotlinx-coroutines-android"}, k = 2, mv = {1, 1, 13})
/* compiled from: AndroidExceptionPreHandler.kt */
public final class AndroidExceptionPreHandlerKt {
    private static final Method getter;

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
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1603198149.run(Unknown Source)
*/
        /*
        r0 = 0;
        r1 = java.lang.Thread.class;	 Catch:{ Throwable -> 0x0029 }
        r2 = "getUncaughtExceptionPreHandler";	 Catch:{ Throwable -> 0x0029 }
        r3 = 0;	 Catch:{ Throwable -> 0x0029 }
        r4 = new java.lang.Class[r3];	 Catch:{ Throwable -> 0x0029 }
        r1 = r1.getDeclaredMethod(r2, r4);	 Catch:{ Throwable -> 0x0029 }
        r2 = "it";	 Catch:{ Throwable -> 0x0029 }
        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r2);	 Catch:{ Throwable -> 0x0029 }
        r2 = r1.getModifiers();	 Catch:{ Throwable -> 0x0029 }
        r2 = java.lang.reflect.Modifier.isPublic(r2);	 Catch:{ Throwable -> 0x0029 }
        if (r2 == 0) goto L_0x0026;	 Catch:{ Throwable -> 0x0029 }
    L_0x001b:
        r2 = r1.getModifiers();	 Catch:{ Throwable -> 0x0029 }
        r2 = java.lang.reflect.Modifier.isStatic(r2);	 Catch:{ Throwable -> 0x0029 }
        if (r2 == 0) goto L_0x0026;
    L_0x0025:
        r3 = 1;
    L_0x0026:
        if (r3 == 0) goto L_0x0029;
    L_0x0028:
        r0 = r1;
    L_0x0029:
        getter = r0;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.android.AndroidExceptionPreHandlerKt.<clinit>():void");
    }
}
