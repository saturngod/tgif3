package khttp.structures.files;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Path;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 0}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bB\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bB\u000f\b\u0016\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\fB\u000f\b\u0016\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\rB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u000e¢\u0006\u0002\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0014"}, d2 = {"Lkhttp/structures/files/FileLike;", "", "name", "", "contents", "(Ljava/lang/String;Ljava/lang/String;)V", "file", "Ljava/io/File;", "(Ljava/lang/String;Ljava/io/File;)V", "path", "Ljava/nio/file/Path;", "(Ljava/lang/String;Ljava/nio/file/Path;)V", "(Ljava/io/File;)V", "(Ljava/nio/file/Path;)V", "", "(Ljava/lang/String;[B)V", "getContents", "()[B", "getName", "()Ljava/lang/String;", "khttp"}, k = 1, mv = {1, 1, 1})
/* compiled from: FileLike.kt */
public final class FileLike {
    @NotNull
    private final byte[] contents;
    @NotNull
    private final String name;

    public FileLike(@NotNull String str, @NotNull byte[] bArr) {
        Intrinsics.checkParameterIsNotNull(str, "name");
        Intrinsics.checkParameterIsNotNull(bArr, "contents");
        this.name = str;
        this.contents = bArr;
    }

    @NotNull
    public final byte[] getContents() {
        return this.contents;
    }

    @NotNull
    public final String getName() {
        return this.name;
    }

    public FileLike(@NotNull String str, @NotNull String str2) {
        Intrinsics.checkParameterIsNotNull(str, "name");
        Intrinsics.checkParameterIsNotNull(str2, "contents");
        Charset charset = Charsets.UTF_8;
        if (str2 != null) {
            byte[] bytes = str2.getBytes(charset);
            Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
            this(str, bytes);
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }

    public FileLike(@NotNull String str, @NotNull File file) {
        Intrinsics.checkParameterIsNotNull(str, "name");
        Intrinsics.checkParameterIsNotNull(file, "file");
        this(str, FilesKt__FileReadWriteKt.readBytes(file));
    }

    public FileLike(@NotNull String str, @NotNull Path path) {
        Intrinsics.checkParameterIsNotNull(str, "name");
        Intrinsics.checkParameterIsNotNull(path, "path");
        File toFile = path.toFile();
        Intrinsics.checkExpressionValueIsNotNull(toFile, "path.toFile()");
        this(str, toFile);
    }

    public FileLike(@NotNull File file) {
        Intrinsics.checkParameterIsNotNull(file, "file");
        String name = file.getName();
        Intrinsics.checkExpressionValueIsNotNull(name, "file.name");
        this(name, file);
    }

    public FileLike(@NotNull Path path) {
        Intrinsics.checkParameterIsNotNull(path, "path");
        File toFile = path.toFile();
        Intrinsics.checkExpressionValueIsNotNull(toFile, "path.toFile()");
        this(toFile);
    }
}
