package khttp.responses;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 0}, d1 = {"\u0000!\n\u0000\n\u0002\u0010(\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\t\u0010\b\u001a\u00020\tH\u0002J\t\u0010\n\u001a\u00020\u0002H\u0002R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000b"}, d2 = {"khttp/responses/GenericResponse$contentIterator$1", "", "", "(Lkhttp/responses/GenericResponse;I)V", "stream", "Ljava/io/InputStream;", "getStream", "()Ljava/io/InputStream;", "hasNext", "", "next", "khttp"}, k = 1, mv = {1, 1, 1})
/* compiled from: GenericResponse.kt */
public final class GenericResponse$contentIterator$1 implements Iterator<byte[]>, KMappedMarker {
    final /* synthetic */ int $chunkSize;
    @NotNull
    private final InputStream stream;
    final /* synthetic */ GenericResponse this$0;

    public void remove() {
        throw new UnsupportedOperationException("Mutating immutable collection");
    }

    GenericResponse$contentIterator$1(GenericResponse genericResponse, int i) {
        this.this$0 = genericResponse;
        this.$chunkSize = i;
        genericResponse = genericResponse;
        this.stream = genericResponse.getRequest().getStream() != 0 ? genericResponse.getRaw() : (InputStream) new ByteArrayInputStream(genericResponse.getContent());
    }

    @NotNull
    public final InputStream getStream() {
        return this.stream;
    }

    @NotNull
    public byte[] next() {
        byte[] bArr = new byte[Math.min(this.$chunkSize, this.stream.available())];
        this.stream.read(bArr);
        Unit unit = Unit.INSTANCE;
        return bArr;
    }

    public boolean hasNext() {
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
        r0 = 0;
        r1 = r3.this$0;	 Catch:{ IOException -> 0x001a }
        r1 = r1.getRaw();	 Catch:{ IOException -> 0x001a }
        r1 = r1.available();	 Catch:{ IOException -> 0x001a }
        if (r1 <= 0) goto L_0x000f;	 Catch:{ IOException -> 0x001a }
    L_0x000d:
        r1 = 1;	 Catch:{ IOException -> 0x001a }
        goto L_0x0010;	 Catch:{ IOException -> 0x001a }
    L_0x000f:
        r1 = 0;	 Catch:{ IOException -> 0x001a }
    L_0x0010:
        if (r1 != 0) goto L_0x0017;	 Catch:{ IOException -> 0x001a }
    L_0x0012:
        r2 = r3.stream;	 Catch:{ IOException -> 0x001a }
        r2.close();	 Catch:{ IOException -> 0x001a }
    L_0x0017:
        r2 = kotlin.Unit.INSTANCE;	 Catch:{ IOException -> 0x001a }
        r0 = r1;
    L_0x001a:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: khttp.responses.GenericResponse$contentIterator$1.hasNext():boolean");
    }
}
