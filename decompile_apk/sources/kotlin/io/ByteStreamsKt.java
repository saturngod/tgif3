package kotlin.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.SinceKotlin;
import kotlin.TypeCastException;
import kotlin.collections.ByteIterator;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000Z\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0017\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u0004H\b\u001a\u0017\u0010\u0000\u001a\u00020\u0005*\u00020\u00062\b\b\u0002\u0010\u0003\u001a\u00020\u0004H\b\u001a\u0017\u0010\u0007\u001a\u00020\b*\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\nH\b\u001a\u0017\u0010\u000b\u001a\u00020\f*\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\nH\b\u001a\u0017\u0010\r\u001a\u00020\u000e*\u00020\u000f2\b\b\u0002\u0010\t\u001a\u00020\nH\b\u001a\u001c\u0010\u0010\u001a\u00020\u0011*\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u00062\b\b\u0002\u0010\u0003\u001a\u00020\u0004\u001a\r\u0010\u0013\u001a\u00020\u000e*\u00020\u0014H\b\u001a\u001d\u0010\u0013\u001a\u00020\u000e*\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u0004H\b\u001a\r\u0010\u0017\u001a\u00020\u0018*\u00020\u0001H\u0002\u001a\f\u0010\u0019\u001a\u00020\u0014*\u00020\u0002H\u0007\u001a\u0016\u0010\u0019\u001a\u00020\u0014*\u00020\u00022\b\b\u0002\u0010\u001a\u001a\u00020\u0004H\u0007\u001a\u0017\u0010\u001b\u001a\u00020\u001c*\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\nH\b\u001a\u0017\u0010\u001d\u001a\u00020\u001e*\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\nH\b¨\u0006\u001f"}, d2 = {"buffered", "Ljava/io/BufferedInputStream;", "Ljava/io/InputStream;", "bufferSize", "", "Ljava/io/BufferedOutputStream;", "Ljava/io/OutputStream;", "bufferedReader", "Ljava/io/BufferedReader;", "charset", "Ljava/nio/charset/Charset;", "bufferedWriter", "Ljava/io/BufferedWriter;", "byteInputStream", "Ljava/io/ByteArrayInputStream;", "", "copyTo", "", "out", "inputStream", "", "offset", "length", "iterator", "Lkotlin/collections/ByteIterator;", "readBytes", "estimatedSize", "reader", "Ljava/io/InputStreamReader;", "writer", "Ljava/io/OutputStreamWriter;", "kotlin-stdlib"}, k = 2, mv = {1, 1, 13})
@JvmName(name = "ByteStreamsKt")
/* compiled from: IOStreams.kt */
public final class ByteStreamsKt {
    @NotNull
    public static final ByteIterator iterator(@NotNull BufferedInputStream bufferedInputStream) {
        Intrinsics.checkParameterIsNotNull(bufferedInputStream, "receiver$0");
        return new ByteStreamsKt$iterator$1(bufferedInputStream);
    }

    @InlineOnly
    private static final ByteArrayInputStream byteInputStream(@NotNull String str, Charset charset) {
        if (str != null) {
            str = str.getBytes(charset);
            Intrinsics.checkExpressionValueIsNotNull(str, "(this as java.lang.String).getBytes(charset)");
            return new ByteArrayInputStream(str);
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }

    @InlineOnly
    static /* synthetic */ ByteArrayInputStream byteInputStream$default(String str, Charset charset, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        if (str != null) {
            str = str.getBytes(charset);
            Intrinsics.checkExpressionValueIsNotNull(str, "(this as java.lang.String).getBytes(charset)");
            return new ByteArrayInputStream(str);
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }

    @InlineOnly
    private static final ByteArrayInputStream inputStream(@NotNull byte[] bArr) {
        return new ByteArrayInputStream(bArr);
    }

    @InlineOnly
    private static final ByteArrayInputStream inputStream(@NotNull byte[] bArr, int i, int i2) {
        return new ByteArrayInputStream(bArr, i, i2);
    }

    @InlineOnly
    private static final BufferedInputStream buffered(@NotNull InputStream inputStream, int i) {
        return inputStream instanceof BufferedInputStream ? (BufferedInputStream) inputStream : new BufferedInputStream(inputStream, i);
    }

    @InlineOnly
    private static final InputStreamReader reader(@NotNull InputStream inputStream, Charset charset) {
        return new InputStreamReader(inputStream, charset);
    }

    @InlineOnly
    static /* synthetic */ InputStreamReader reader$default(InputStream inputStream, Charset charset, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        return new InputStreamReader(inputStream, charset);
    }

    @InlineOnly
    private static final BufferedReader bufferedReader(@NotNull InputStream inputStream, Charset charset) {
        Reader inputStreamReader = new InputStreamReader(inputStream, charset);
        return (inputStreamReader instanceof BufferedReader) != null ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, 8192);
    }

    @InlineOnly
    static /* synthetic */ BufferedReader bufferedReader$default(InputStream inputStream, Charset charset, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        Reader reader = (Reader) new InputStreamReader(inputStream, charset);
        return (reader instanceof BufferedReader) != null ? (BufferedReader) reader : new BufferedReader(reader, 8192);
    }

    @InlineOnly
    private static final BufferedOutputStream buffered(@NotNull OutputStream outputStream, int i) {
        return outputStream instanceof BufferedOutputStream ? (BufferedOutputStream) outputStream : new BufferedOutputStream(outputStream, i);
    }

    @InlineOnly
    private static final OutputStreamWriter writer(@NotNull OutputStream outputStream, Charset charset) {
        return new OutputStreamWriter(outputStream, charset);
    }

    @InlineOnly
    static /* synthetic */ OutputStreamWriter writer$default(OutputStream outputStream, Charset charset, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        return new OutputStreamWriter(outputStream, charset);
    }

    @InlineOnly
    private static final BufferedWriter bufferedWriter(@NotNull OutputStream outputStream, Charset charset) {
        Writer outputStreamWriter = new OutputStreamWriter(outputStream, charset);
        return (outputStreamWriter instanceof BufferedWriter) != null ? (BufferedWriter) outputStreamWriter : new BufferedWriter(outputStreamWriter, 8192);
    }

    @InlineOnly
    static /* synthetic */ BufferedWriter bufferedWriter$default(OutputStream outputStream, Charset charset, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        Writer writer = (Writer) new OutputStreamWriter(outputStream, charset);
        return (writer instanceof BufferedWriter) != null ? (BufferedWriter) writer : new BufferedWriter(writer, 8192);
    }

    public static /* synthetic */ long copyTo$default(InputStream inputStream, OutputStream outputStream, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 8192;
        }
        return copyTo(inputStream, outputStream, i);
    }

    public static final long copyTo(@NotNull InputStream inputStream, @NotNull OutputStream outputStream, int i) {
        Intrinsics.checkParameterIsNotNull(inputStream, "receiver$0");
        Intrinsics.checkParameterIsNotNull(outputStream, "out");
        i = new byte[i];
        int read = inputStream.read(i);
        long j = 0;
        while (read >= 0) {
            outputStream.write(i, 0, read);
            j += (long) read;
            read = inputStream.read(i);
        }
        return j;
    }

    @NotNull
    @Deprecated(message = "Use readBytes() overload without estimatedSize parameter", replaceWith = @ReplaceWith(expression = "readBytes()", imports = {}))
    public static /* synthetic */ byte[] readBytes$default(InputStream inputStream, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 8192;
        }
        return readBytes(inputStream, i);
    }

    @NotNull
    @Deprecated(message = "Use readBytes() overload without estimatedSize parameter", replaceWith = @ReplaceWith(expression = "readBytes()", imports = {}))
    public static final byte[] readBytes(@NotNull InputStream inputStream, int i) {
        Intrinsics.checkParameterIsNotNull(inputStream, "receiver$0");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(Math.max(i, inputStream.available()));
        copyTo$default(inputStream, byteArrayOutputStream, 0, 2, null);
        inputStream = byteArrayOutputStream.toByteArray();
        Intrinsics.checkExpressionValueIsNotNull(inputStream, "buffer.toByteArray()");
        return inputStream;
    }

    @NotNull
    @SinceKotlin(version = "1.3")
    public static final byte[] readBytes(@NotNull InputStream inputStream) {
        Intrinsics.checkParameterIsNotNull(inputStream, "receiver$0");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(Math.max(8192, inputStream.available()));
        copyTo$default(inputStream, byteArrayOutputStream, 0, 2, null);
        inputStream = byteArrayOutputStream.toByteArray();
        Intrinsics.checkExpressionValueIsNotNull(inputStream, "buffer.toByteArray()");
        return inputStream;
    }

    @InlineOnly
    static /* synthetic */ BufferedInputStream buffered$default(InputStream inputStream, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 8192;
        }
        return (inputStream instanceof BufferedInputStream) != 0 ? (BufferedInputStream) inputStream : new BufferedInputStream(inputStream, i);
    }

    @InlineOnly
    static /* synthetic */ BufferedOutputStream buffered$default(OutputStream outputStream, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 8192;
        }
        return (outputStream instanceof BufferedOutputStream) != 0 ? (BufferedOutputStream) outputStream : new BufferedOutputStream(outputStream, i);
    }
}
