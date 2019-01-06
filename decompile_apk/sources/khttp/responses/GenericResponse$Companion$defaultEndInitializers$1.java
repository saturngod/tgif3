package khttp.responses;

import java.net.HttpURLConnection;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 0}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "response", "Lkhttp/responses/GenericResponse;", "connection", "Ljava/net/HttpURLConnection;", "invoke"}, k = 3, mv = {1, 1, 1})
/* compiled from: GenericResponse.kt */
final class GenericResponse$Companion$defaultEndInitializers$1 extends Lambda implements Function2<GenericResponse, HttpURLConnection, Unit> {
    public static final GenericResponse$Companion$defaultEndInitializers$1 INSTANCE = new GenericResponse$Companion$defaultEndInitializers$1();

    GenericResponse$Companion$defaultEndInitializers$1() {
        super(2);
    }

    public final void invoke(@org.jetbrains.annotations.NotNull khttp.responses.GenericResponse r4, @org.jetbrains.annotations.NotNull java.net.HttpURLConnection r5) {
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
        r3 = this;
        r0 = "response";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r4, r0);
        r0 = "connection";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r5, r0);
        r4 = r4.getRequest();
        r4 = r4.getBody();
        r0 = r4.length;
        r1 = 0;
        r2 = 1;
        if (r0 != 0) goto L_0x0019;
    L_0x0017:
        r0 = 1;
        goto L_0x001a;
    L_0x0019:
        r0 = 0;
    L_0x001a:
        if (r0 == 0) goto L_0x001d;
    L_0x001c:
        return;
    L_0x001d:
        r5.setDoOutput(r2);
        r5 = r5.getOutputStream();
        r5 = (java.io.Closeable) r5;
        r0 = r5;	 Catch:{ Exception -> 0x0034, all -> 0x0032 }
        r0 = (java.io.OutputStream) r0;	 Catch:{ Exception -> 0x0034, all -> 0x0032 }
        r0.write(r4);	 Catch:{ Exception -> 0x0034, all -> 0x0032 }
        r4 = kotlin.Unit.INSTANCE;	 Catch:{ Exception -> 0x0034, all -> 0x0032 }
        r5.close();
        return;
    L_0x0032:
        r4 = move-exception;
        goto L_0x003f;
    L_0x0034:
        r4 = move-exception;
        r5.close();	 Catch:{ Exception -> 0x003c }
        goto L_0x003c;
    L_0x0039:
        r4 = move-exception;
        r1 = 1;
        goto L_0x003f;
    L_0x003c:
        r4 = (java.lang.Throwable) r4;	 Catch:{ all -> 0x0039 }
        throw r4;	 Catch:{ all -> 0x0039 }
    L_0x003f:
        if (r1 != 0) goto L_0x0044;
    L_0x0041:
        r5.close();
    L_0x0044:
        throw r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: khttp.responses.GenericResponse$Companion$defaultEndInitializers$1.invoke(khttp.responses.GenericResponse, java.net.HttpURLConnection):void");
    }
}
