package khttp.extensions;

import java.io.File;
import java.io.Writer;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import khttp.structures.files.FileLike;
import kotlin.Metadata;
import kotlin.collections.IndexedValue;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 0}, d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u0004\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0005\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004\u001a&\u0010\u0006\u001a\u0010\u0012\f\u0012\n\u0012\u0006\b\u0000\u0012\u0002H\t0\b0\u0007\"\u0004\b\u0000\u0010\t*\b\u0012\u0004\u0012\u0002H\t0\bH\u0000\u001a\u0018\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0007*\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b\u001a\u0010\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0007*\u00020\u000b\u001a\u0014\u0010\u000e\u001a\u00020\u000f*\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0004H\u0000¨\u0006\u0012"}, d2 = {"fileLike", "Lkhttp/structures/files/FileLike;", "Ljava/io/File;", "name", "", "Ljava/nio/file/Path;", "getSuperclasses", "", "Ljava/lang/Class;", "T", "split", "", "delimiter", "splitLines", "writeAndFlush", "", "Ljava/io/Writer;", "string", "khttp"}, k = 2, mv = {1, 1, 1})
/* compiled from: Extensions.kt */
public final class ExtensionsKt {
    @NotNull
    public static final FileLike fileLike(@NotNull File file, @NotNull String str) {
        Intrinsics.checkParameterIsNotNull(file, "$receiver");
        Intrinsics.checkParameterIsNotNull(str, "name");
        return new FileLike(str, file);
    }

    @NotNull
    public static /* bridge */ /* synthetic */ FileLike fileLike$default(File file, String str, int i, Object obj) {
        if (obj == null) {
            if ((i & 1) != 0) {
                str = file.getName();
                Intrinsics.checkExpressionValueIsNotNull(str, "this.name");
            }
            return fileLike(file, str);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: fileLike");
    }

    @NotNull
    public static final FileLike fileLike(@NotNull Path path) {
        Intrinsics.checkParameterIsNotNull(path, "$receiver");
        return new FileLike(path);
    }

    @NotNull
    public static final FileLike fileLike(@NotNull Path path, @NotNull String str) {
        Intrinsics.checkParameterIsNotNull(path, "$receiver");
        Intrinsics.checkParameterIsNotNull(str, "name");
        return new FileLike(str, path);
    }

    @NotNull
    public static final FileLike fileLike(@NotNull String str, @NotNull String str2) {
        Intrinsics.checkParameterIsNotNull(str, "$receiver");
        Intrinsics.checkParameterIsNotNull(str2, "name");
        return new FileLike(str2, str);
    }

    public static final void writeAndFlush(@NotNull Writer writer, @NotNull String str) {
        Intrinsics.checkParameterIsNotNull(writer, "$receiver");
        Intrinsics.checkParameterIsNotNull(str, "string");
        writer.write(str);
        writer.flush();
    }

    @NotNull
    public static final List<byte[]> splitLines(@NotNull byte[] bArr) {
        Intrinsics.checkParameterIsNotNull(bArr, "$receiver");
        if (bArr.length == 0) {
            return CollectionsKt__CollectionsKt.emptyList();
        }
        int i = 0;
        ArrayList arrayListOf = CollectionsKt__CollectionsKt.arrayListOf(new byte[0][]);
        int i2 = 0;
        for (IndexedValue indexedValue : ArraysKt___ArraysKt.withIndex(bArr)) {
            int component1 = indexedValue.component1();
            byte byteValue = ((Number) indexedValue.component2()).byteValue();
            if (i > 0) {
                i--;
            } else {
                byte b = (byte) 10;
                if (byteValue == b) {
                    arrayListOf.add(ArraysKt___ArraysKt.sliceArray(bArr, new IntRange(i2, component1 - 1)));
                    component1++;
                } else {
                    byte b2 = (byte) 13;
                    if (byteValue == b2) {
                        int i3 = component1 + 1;
                        if (i3 < bArr.length && bArr[i3] == b) {
                            arrayListOf.add(ArraysKt___ArraysKt.sliceArray(bArr, new IntRange(i2, component1 - 1)));
                            i2 = component1 + 2;
                            i = 1;
                        }
                    }
                    if (byteValue == b2) {
                        arrayListOf.add(ArraysKt___ArraysKt.sliceArray(bArr, new IntRange(i2, component1 - 1)));
                        component1++;
                    }
                }
                i2 = component1;
            }
        }
        arrayListOf.add(ArraysKt___ArraysKt.sliceArray(bArr, new IntRange(i2, bArr.length - 1)));
        return arrayListOf;
    }

    @NotNull
    public static final List<byte[]> split(@NotNull byte[] bArr, @NotNull byte[] bArr2) {
        Intrinsics.checkParameterIsNotNull(bArr, "$receiver");
        Intrinsics.checkParameterIsNotNull(bArr2, "delimiter");
        int i = 0;
        ArrayList arrayListOf = CollectionsKt__CollectionsKt.arrayListOf(new byte[0][]);
        int length = bArr.length - 1;
        if (length >= 0) {
            int i2 = 0;
            int i3 = 0;
            while (true) {
                if (i > 0) {
                    i--;
                } else if (Intrinsics.areEqual(ArraysKt___ArraysKt.toList(ArraysKt___ArraysKt.sliceArray(bArr, new IntRange(i2, (bArr2.length + i2) - 1))), ArraysKt___ArraysKt.toList(bArr2))) {
                    i = bArr2.length;
                    arrayListOf.add(ArraysKt___ArraysKt.sliceArray(bArr, new IntRange(i3, i2 - 1)));
                    i3 = bArr2.length + i2;
                }
                if (i2 == length) {
                    break;
                }
                i2++;
            }
            i = i3;
        }
        arrayListOf.add(ArraysKt___ArraysKt.sliceArray(bArr, new IntRange(i, bArr.length - 1)));
        return arrayListOf;
    }

    @NotNull
    public static final <T> List<Class<? super T>> getSuperclasses(@NotNull Class<T> cls) {
        Intrinsics.checkParameterIsNotNull(cls, "$receiver");
        ArrayList arrayListOf = CollectionsKt__CollectionsKt.arrayListOf(new Class[0]);
        for (cls = cls.getSuperclass(); cls != null; cls = cls.getSuperclass()) {
            arrayListOf.add(cls);
        }
        return arrayListOf;
    }
}
