package khttp.responses;

import java.net.HttpURLConnection;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 0}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "response", "Lkhttp/responses/GenericResponse;", "connection", "Ljava/net/HttpURLConnection;", "invoke"}, k = 3, mv = {1, 1, 1})
/* compiled from: GenericResponse.kt */
final class GenericResponse$Companion$defaultEndInitializers$2 extends Lambda implements Function2<GenericResponse, HttpURLConnection, Unit> {
    public static final GenericResponse$Companion$defaultEndInitializers$2 INSTANCE = new GenericResponse$Companion$defaultEndInitializers$2();

    GenericResponse$Companion$defaultEndInitializers$2() {
        super(2);
    }

    public final void invoke(@org.jetbrains.annotations.NotNull khttp.responses.GenericResponse r7, @org.jetbrains.annotations.NotNull java.net.HttpURLConnection r8) {
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
        r6 = this;
        r0 = "response";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r7, r0);
        r0 = "connection";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r8, r0);
        r0 = r7.getRequest();
        r0 = r0.getFiles();
        r7 = r7.getRequest();
        r7 = r7.getData();
        r0 = (java.util.Collection) r0;
        r0 = r0.isEmpty();
        r1 = 1;
        r0 = r0 ^ r1;
        if (r0 == 0) goto L_0x0025;
    L_0x0024:
        return;
    L_0x0025:
        r0 = r7 instanceof java.io.File;
        if (r0 == 0) goto L_0x0033;
    L_0x0029:
        r7 = (java.io.File) r7;
        r0 = new java.io.FileInputStream;
        r0.<init>(r7);
        r0 = (java.io.InputStream) r0;
        goto L_0x003a;
    L_0x0033:
        r0 = r7 instanceof java.io.InputStream;
        if (r0 == 0) goto L_0x00a4;
    L_0x0037:
        r0 = r7;
        r0 = (java.io.InputStream) r0;
    L_0x003a:
        r7 = r8.getDoOutput();
        if (r7 != 0) goto L_0x0043;
    L_0x0040:
        r8.setDoOutput(r1);
    L_0x0043:
        r0 = (java.io.Closeable) r0;
        r7 = 0;
        r2 = r0;	 Catch:{ Exception -> 0x0093, all -> 0x0091 }
        r2 = (java.io.InputStream) r2;	 Catch:{ Exception -> 0x0093, all -> 0x0091 }
        r8 = r8.getOutputStream();	 Catch:{ Exception -> 0x0093, all -> 0x0091 }
        r8 = (java.io.Closeable) r8;	 Catch:{ Exception -> 0x0093, all -> 0x0091 }
        r3 = r8;	 Catch:{ Exception -> 0x0080, all -> 0x007d }
        r3 = (java.io.OutputStream) r3;	 Catch:{ Exception -> 0x0080, all -> 0x007d }
    L_0x0052:
        r4 = r2.available();	 Catch:{ Exception -> 0x0080, all -> 0x007d }
        if (r4 <= 0) goto L_0x0072;	 Catch:{ Exception -> 0x0080, all -> 0x007d }
    L_0x0058:
        r4 = 4096; // 0x1000 float:5.74E-42 double:2.0237E-320;	 Catch:{ Exception -> 0x0080, all -> 0x007d }
        r5 = r2.available();	 Catch:{ Exception -> 0x0080, all -> 0x007d }
        r4 = java.lang.Math.min(r4, r5);	 Catch:{ Exception -> 0x0080, all -> 0x007d }
        r4 = new byte[r4];	 Catch:{ Exception -> 0x0080, all -> 0x007d }
        r5 = r4;	 Catch:{ Exception -> 0x0080, all -> 0x007d }
        r5 = (byte[]) r5;	 Catch:{ Exception -> 0x0080, all -> 0x007d }
        r2.read(r5);	 Catch:{ Exception -> 0x0080, all -> 0x007d }
        r5 = kotlin.Unit.INSTANCE;	 Catch:{ Exception -> 0x0080, all -> 0x007d }
        r4 = (byte[]) r4;	 Catch:{ Exception -> 0x0080, all -> 0x007d }
        r3.write(r4);	 Catch:{ Exception -> 0x0080, all -> 0x007d }
        goto L_0x0052;	 Catch:{ Exception -> 0x0080, all -> 0x007d }
    L_0x0072:
        r2 = kotlin.Unit.INSTANCE;	 Catch:{ Exception -> 0x0080, all -> 0x007d }
        r8.close();	 Catch:{ Exception -> 0x0093, all -> 0x0091 }
        r8 = kotlin.Unit.INSTANCE;	 Catch:{ Exception -> 0x0093, all -> 0x0091 }
        r0.close();
        return;
    L_0x007d:
        r2 = move-exception;
        r3 = 0;
        goto L_0x008b;
    L_0x0080:
        r2 = move-exception;
        r8.close();	 Catch:{ Exception -> 0x0088 }
        goto L_0x0088;
    L_0x0085:
        r2 = move-exception;
        r3 = 1;
        goto L_0x008b;
    L_0x0088:
        r2 = (java.lang.Throwable) r2;	 Catch:{ all -> 0x0085 }
        throw r2;	 Catch:{ all -> 0x0085 }
    L_0x008b:
        if (r3 != 0) goto L_0x0090;
    L_0x008d:
        r8.close();	 Catch:{ Exception -> 0x0093, all -> 0x0091 }
    L_0x0090:
        throw r2;	 Catch:{ Exception -> 0x0093, all -> 0x0091 }
    L_0x0091:
        r8 = move-exception;
        goto L_0x009e;
    L_0x0093:
        r7 = move-exception;
        r0.close();	 Catch:{ Exception -> 0x009b }
        goto L_0x009b;
    L_0x0098:
        r8 = move-exception;
        r7 = 1;
        goto L_0x009e;
    L_0x009b:
        r7 = (java.lang.Throwable) r7;	 Catch:{ all -> 0x0098 }
        throw r7;	 Catch:{ all -> 0x0098 }
    L_0x009e:
        if (r7 != 0) goto L_0x00a3;
    L_0x00a0:
        r0.close();
    L_0x00a3:
        throw r8;
    L_0x00a4:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: khttp.responses.GenericResponse$Companion$defaultEndInitializers$2.invoke(khttp.responses.GenericResponse, java.net.HttpURLConnection):void");
    }
}
