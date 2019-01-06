package kotlin.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.text.Charsets;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000X\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a\u0017\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u0004H\b\u001a\u0017\u0010\u0000\u001a\u00020\u0005*\u00020\u00062\b\b\u0002\u0010\u0003\u001a\u00020\u0004H\b\u001a\u001c\u0010\u0007\u001a\u00020\b*\u00020\u00022\u0006\u0010\t\u001a\u00020\u00062\b\b\u0002\u0010\u0003\u001a\u00020\u0004\u001a\u001e\u0010\n\u001a\u00020\u000b*\u00020\u00022\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000b0\r\u001a\u0010\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0010*\u00020\u0001\u001a\n\u0010\u0011\u001a\u00020\u0012*\u00020\u0013\u001a\u0010\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0015*\u00020\u0002\u001a\n\u0010\u0016\u001a\u00020\u000e*\u00020\u0002\u001a\u0017\u0010\u0016\u001a\u00020\u000e*\u00020\u00132\b\b\u0002\u0010\u0017\u001a\u00020\u0018H\b\u001a\r\u0010\u0019\u001a\u00020\u001a*\u00020\u000eH\b\u001a5\u0010\u001b\u001a\u0002H\u001c\"\u0004\b\u0000\u0010\u001c*\u00020\u00022\u0018\u0010\u001d\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u0010\u0012\u0004\u0012\u0002H\u001c0\rH\bø\u0001\u0000¢\u0006\u0002\u0010\u001f\u0002\b\n\u0006\b\u0011(\u001e0\u0001¨\u0006 "}, d2 = {"buffered", "Ljava/io/BufferedReader;", "Ljava/io/Reader;", "bufferSize", "", "Ljava/io/BufferedWriter;", "Ljava/io/Writer;", "copyTo", "", "out", "forEachLine", "", "action", "Lkotlin/Function1;", "", "lineSequence", "Lkotlin/sequences/Sequence;", "readBytes", "", "Ljava/net/URL;", "readLines", "", "readText", "charset", "Ljava/nio/charset/Charset;", "reader", "Ljava/io/StringReader;", "useLines", "T", "block", "Requires newer compiler version to be inlined correctly.", "(Ljava/io/Reader;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "kotlin-stdlib"}, k = 2, mv = {1, 1, 13})
@JvmName(name = "TextStreamsKt")
/* compiled from: ReadWrite.kt */
public final class TextStreamsKt {
    @InlineOnly
    private static final BufferedReader buffered(@NotNull Reader reader, int i) {
        return reader instanceof BufferedReader ? (BufferedReader) reader : new BufferedReader(reader, i);
    }

    @InlineOnly
    private static final BufferedWriter buffered(@NotNull Writer writer, int i) {
        return writer instanceof BufferedWriter ? (BufferedWriter) writer : new BufferedWriter(writer, i);
    }

    @NotNull
    public static final List<String> readLines(@NotNull Reader reader) {
        Intrinsics.checkParameterIsNotNull(reader, "receiver$0");
        ArrayList arrayList = new ArrayList();
        forEachLine(reader, new TextStreamsKt$readLines$1(arrayList));
        return arrayList;
    }

    public static final <T> T useLines(@org.jetbrains.annotations.NotNull java.io.Reader r4, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super kotlin.sequences.Sequence<java.lang.String>, ? extends T> r5) {
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
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r4, r0);
        r0 = "block";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r5, r0);
        r0 = r4 instanceof java.io.BufferedReader;
        if (r0 == 0) goto L_0x0011;
    L_0x000e:
        r4 = (java.io.BufferedReader) r4;
        goto L_0x0019;
    L_0x0011:
        r0 = new java.io.BufferedReader;
        r1 = 8192; // 0x2000 float:1.14794E-41 double:4.0474E-320;
        r0.<init>(r4, r1);
        r4 = r0;
    L_0x0019:
        r4 = (java.io.Closeable) r4;
        r0 = 0;
        r0 = (java.lang.Throwable) r0;
        r1 = 0;
        r2 = 1;
        r3 = r4;	 Catch:{ Throwable -> 0x0041 }
        r3 = (java.io.BufferedReader) r3;	 Catch:{ Throwable -> 0x0041 }
        r3 = lineSequence(r3);	 Catch:{ Throwable -> 0x0041 }
        r5 = r5.invoke(r3);	 Catch:{ Throwable -> 0x0041 }
        kotlin.jvm.internal.InlineMarker.finallyStart(r2);
        r1 = kotlin.internal.PlatformImplementationsKt.apiVersionIsAtLeast(r2, r2, r1);
        if (r1 == 0) goto L_0x0038;
    L_0x0034:
        kotlin.io.CloseableKt.closeFinally(r4, r0);
        goto L_0x003b;
    L_0x0038:
        r4.close();
    L_0x003b:
        kotlin.jvm.internal.InlineMarker.finallyEnd(r2);
        return r5;
    L_0x003f:
        r5 = move-exception;
        goto L_0x0044;
    L_0x0041:
        r5 = move-exception;
        r0 = r5;
        throw r0;	 Catch:{ all -> 0x003f }
    L_0x0044:
        kotlin.jvm.internal.InlineMarker.finallyStart(r2);
        r1 = kotlin.internal.PlatformImplementationsKt.apiVersionIsAtLeast(r2, r2, r1);
        if (r1 != 0) goto L_0x0057;
    L_0x004d:
        if (r0 != 0) goto L_0x0053;
    L_0x004f:
        r4.close();
        goto L_0x005a;
    L_0x0053:
        r4.close();	 Catch:{ Throwable -> 0x005a }
        goto L_0x005a;
    L_0x0057:
        kotlin.io.CloseableKt.closeFinally(r4, r0);
    L_0x005a:
        kotlin.jvm.internal.InlineMarker.finallyEnd(r2);
        throw r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.io.TextStreamsKt.useLines(java.io.Reader, kotlin.jvm.functions.Function1):T");
    }

    @InlineOnly
    private static final StringReader reader(@NotNull String str) {
        return new StringReader(str);
    }

    @NotNull
    public static final Sequence<String> lineSequence(@NotNull BufferedReader bufferedReader) {
        Intrinsics.checkParameterIsNotNull(bufferedReader, "receiver$0");
        return SequencesKt__SequencesKt.constrainOnce(new LinesSequence(bufferedReader));
    }

    @NotNull
    public static final String readText(@NotNull Reader reader) {
        Intrinsics.checkParameterIsNotNull(reader, "receiver$0");
        StringWriter stringWriter = new StringWriter();
        copyTo$default(reader, stringWriter, 0, 2, null);
        reader = stringWriter.toString();
        Intrinsics.checkExpressionValueIsNotNull(reader, "buffer.toString()");
        return reader;
    }

    public static /* synthetic */ long copyTo$default(Reader reader, Writer writer, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 8192;
        }
        return copyTo(reader, writer, i);
    }

    public static final long copyTo(@NotNull Reader reader, @NotNull Writer writer, int i) {
        Intrinsics.checkParameterIsNotNull(reader, "receiver$0");
        Intrinsics.checkParameterIsNotNull(writer, "out");
        i = new char[i];
        int read = reader.read(i);
        long j = 0;
        while (read >= 0) {
            writer.write(i, 0, read);
            j += (long) read;
            read = reader.read(i);
        }
        return j;
    }

    @InlineOnly
    private static final String readText(@NotNull URL url, Charset charset) {
        return new String(readBytes(url), charset);
    }

    @InlineOnly
    static /* synthetic */ String readText$default(URL url, Charset charset, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        return new String(readBytes(url), charset);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @org.jetbrains.annotations.NotNull
    public static final byte[] readBytes(@org.jetbrains.annotations.NotNull java.net.URL r3) {
        /*
        r0 = "receiver$0";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r3, r0);
        r3 = r3.openStream();
        r3 = (java.io.Closeable) r3;
        r0 = 0;
        r0 = (java.lang.Throwable) r0;
        r1 = r3;
        r1 = (java.io.InputStream) r1;	 Catch:{ Throwable -> 0x0020 }
        r2 = "it";
        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r2);	 Catch:{ Throwable -> 0x0020 }
        r1 = kotlin.io.ByteStreamsKt.readBytes(r1);	 Catch:{ Throwable -> 0x0020 }
        kotlin.io.CloseableKt.closeFinally(r3, r0);
        return r1;
    L_0x001e:
        r1 = move-exception;
        goto L_0x0022;
    L_0x0020:
        r0 = move-exception;
        throw r0;	 Catch:{ all -> 0x001e }
    L_0x0022:
        kotlin.io.CloseableKt.closeFinally(r3, r0);
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.io.TextStreamsKt.readBytes(java.net.URL):byte[]");
    }

    @InlineOnly
    static /* synthetic */ BufferedReader buffered$default(Reader reader, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 8192;
        }
        return (reader instanceof BufferedReader) != 0 ? (BufferedReader) reader : new BufferedReader(reader, i);
    }

    @InlineOnly
    static /* synthetic */ BufferedWriter buffered$default(Writer writer, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 8192;
        }
        return (writer instanceof BufferedWriter) != 0 ? (BufferedWriter) writer : new BufferedWriter(writer, i);
    }

    public static final void forEachLine(@NotNull Reader reader, @NotNull Function1<? super String, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(reader, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "action");
        Closeable bufferedReader = reader instanceof BufferedReader ? (BufferedReader) reader : new BufferedReader(reader, 8192);
        Throwable th = (Throwable) null;
        try {
            for (Object invoke : lineSequence((BufferedReader) bufferedReader)) {
                function1.invoke(invoke);
            }
            function1 = Unit.INSTANCE;
            CloseableKt.closeFinally(bufferedReader, th);
        } catch (Throwable th2) {
            CloseableKt.closeFinally(bufferedReader, function1);
        }
    }
}
