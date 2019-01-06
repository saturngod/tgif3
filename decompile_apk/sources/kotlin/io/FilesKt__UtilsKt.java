package kotlin.io;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\u001a(\u0010\t\u001a\u00020\u00022\b\b\u0002\u0010\n\u001a\u00020\u00012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0002\u001a(\u0010\r\u001a\u00020\u00022\b\b\u0002\u0010\n\u001a\u00020\u00012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0002\u001a8\u0010\u000e\u001a\u00020\u000f*\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u00022\b\b\u0002\u0010\u0011\u001a\u00020\u000f2\u001a\b\u0002\u0010\u0012\u001a\u0014\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00150\u0013\u001a&\u0010\u0016\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u00022\b\b\u0002\u0010\u0011\u001a\u00020\u000f2\b\b\u0002\u0010\u0017\u001a\u00020\u0018\u001a\n\u0010\u0019\u001a\u00020\u000f*\u00020\u0002\u001a\u0012\u0010\u001a\u001a\u00020\u000f*\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u0002\u001a\u0012\u0010\u001a\u001a\u00020\u000f*\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u0001\u001a\n\u0010\u001c\u001a\u00020\u0002*\u00020\u0002\u001a\u001d\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00020\u001d*\b\u0012\u0004\u0012\u00020\u00020\u001dH\u0002¢\u0006\u0002\b\u001e\u001a\u0011\u0010\u001c\u001a\u00020\u001f*\u00020\u001fH\u0002¢\u0006\u0002\b\u001e\u001a\u0012\u0010 \u001a\u00020\u0002*\u00020\u00022\u0006\u0010!\u001a\u00020\u0002\u001a\u0014\u0010\"\u001a\u0004\u0018\u00010\u0002*\u00020\u00022\u0006\u0010!\u001a\u00020\u0002\u001a\u0012\u0010#\u001a\u00020\u0002*\u00020\u00022\u0006\u0010!\u001a\u00020\u0002\u001a\u0012\u0010$\u001a\u00020\u0002*\u00020\u00022\u0006\u0010%\u001a\u00020\u0002\u001a\u0012\u0010$\u001a\u00020\u0002*\u00020\u00022\u0006\u0010%\u001a\u00020\u0001\u001a\u0012\u0010&\u001a\u00020\u0002*\u00020\u00022\u0006\u0010%\u001a\u00020\u0002\u001a\u0012\u0010&\u001a\u00020\u0002*\u00020\u00022\u0006\u0010%\u001a\u00020\u0001\u001a\u0012\u0010'\u001a\u00020\u000f*\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u0002\u001a\u0012\u0010'\u001a\u00020\u000f*\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u0001\u001a\u0012\u0010(\u001a\u00020\u0001*\u00020\u00022\u0006\u0010!\u001a\u00020\u0002\u001a\u001b\u0010)\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010!\u001a\u00020\u0002H\u0002¢\u0006\u0002\b*\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0015\u0010\u0005\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0004\"\u0015\u0010\u0007\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\b\u0010\u0004¨\u0006+"}, d2 = {"extension", "", "Ljava/io/File;", "getExtension", "(Ljava/io/File;)Ljava/lang/String;", "invariantSeparatorsPath", "getInvariantSeparatorsPath", "nameWithoutExtension", "getNameWithoutExtension", "createTempDir", "prefix", "suffix", "directory", "createTempFile", "copyRecursively", "", "target", "overwrite", "onError", "Lkotlin/Function2;", "Ljava/io/IOException;", "Lkotlin/io/OnErrorAction;", "copyTo", "bufferSize", "", "deleteRecursively", "endsWith", "other", "normalize", "", "normalize$FilesKt__UtilsKt", "Lkotlin/io/FilePathComponents;", "relativeTo", "base", "relativeToOrNull", "relativeToOrSelf", "resolve", "relative", "resolveSibling", "startsWith", "toRelativeString", "toRelativeStringOrNull", "toRelativeStringOrNull$FilesKt__UtilsKt", "kotlin-stdlib"}, k = 5, mv = {1, 1, 13}, xi = 1, xs = "kotlin/io/FilesKt")
/* compiled from: Utils.kt */
class FilesKt__UtilsKt extends FilesKt__FileTreeWalkKt {
    @NotNull
    public static /* synthetic */ File createTempDir$default(String str, String str2, File file, int i, Object obj) {
        if ((i & 1) != null) {
            str = "tmp";
        }
        if ((i & 2) != null) {
            str2 = null;
        }
        if ((i & 4) != 0) {
            file = null;
        }
        return createTempDir(str, str2, file);
    }

    @NotNull
    public static final File createTempDir(@NotNull String str, @Nullable String str2, @Nullable File file) {
        Intrinsics.checkParameterIsNotNull(str, "prefix");
        str = File.createTempFile(str, str2, file);
        str.delete();
        if (str.mkdir() != null) {
            Intrinsics.checkExpressionValueIsNotNull(str, "dir");
            return str;
        }
        file = new StringBuilder();
        file.append("Unable to create temporary directory ");
        file.append(str);
        file.append('.');
        throw ((Throwable) new IOException(file.toString()));
    }

    @NotNull
    public static /* synthetic */ File createTempFile$default(String str, String str2, File file, int i, Object obj) {
        if ((i & 1) != null) {
            str = "tmp";
        }
        if ((i & 2) != null) {
            str2 = null;
        }
        if ((i & 4) != 0) {
            file = null;
        }
        return createTempFile(str, str2, file);
    }

    @NotNull
    public static final File createTempFile(@NotNull String str, @Nullable String str2, @Nullable File file) {
        Intrinsics.checkParameterIsNotNull(str, "prefix");
        str = File.createTempFile(str, str2, file);
        Intrinsics.checkExpressionValueIsNotNull(str, "File.createTempFile(prefix, suffix, directory)");
        return str;
    }

    @NotNull
    public static final String getExtension(@NotNull File file) {
        Intrinsics.checkParameterIsNotNull(file, "receiver$0");
        file = file.getName();
        Intrinsics.checkExpressionValueIsNotNull(file, "name");
        return StringsKt__StringsKt.substringAfterLast((String) file, '.', "");
    }

    @NotNull
    public static final String getInvariantSeparatorsPath(@NotNull File file) {
        Intrinsics.checkParameterIsNotNull(file, "receiver$0");
        if (File.separatorChar != '/') {
            String path = file.getPath();
            Intrinsics.checkExpressionValueIsNotNull(path, "path");
            return StringsKt__StringsJVMKt.replace$default(path, File.separatorChar, '/', false, 4, null);
        }
        file = file.getPath();
        Intrinsics.checkExpressionValueIsNotNull(file, "path");
        return file;
    }

    @NotNull
    public static final String getNameWithoutExtension(@NotNull File file) {
        Intrinsics.checkParameterIsNotNull(file, "receiver$0");
        file = file.getName();
        Intrinsics.checkExpressionValueIsNotNull(file, "name");
        return StringsKt__StringsKt.substringBeforeLast$default((String) file, ".", null, 2, null);
    }

    @NotNull
    public static final String toRelativeString(@NotNull File file, @NotNull File file2) {
        Intrinsics.checkParameterIsNotNull(file, "receiver$0");
        Intrinsics.checkParameterIsNotNull(file2, "base");
        String toRelativeStringOrNull$FilesKt__UtilsKt = toRelativeStringOrNull$FilesKt__UtilsKt(file, file2);
        if (toRelativeStringOrNull$FilesKt__UtilsKt != null) {
            return toRelativeStringOrNull$FilesKt__UtilsKt;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("this and base files have different roots: ");
        stringBuilder.append(file);
        stringBuilder.append(" and ");
        stringBuilder.append(file2);
        stringBuilder.append('.');
        throw new IllegalArgumentException(stringBuilder.toString());
    }

    @NotNull
    public static final File relativeTo(@NotNull File file, @NotNull File file2) {
        Intrinsics.checkParameterIsNotNull(file, "receiver$0");
        Intrinsics.checkParameterIsNotNull(file2, "base");
        return new File(toRelativeString(file, file2));
    }

    @NotNull
    public static final File relativeToOrSelf(@NotNull File file, @NotNull File file2) {
        Intrinsics.checkParameterIsNotNull(file, "receiver$0");
        Intrinsics.checkParameterIsNotNull(file2, "base");
        file2 = toRelativeStringOrNull$FilesKt__UtilsKt(file, file2);
        return file2 != null ? new File(file2) : file;
    }

    @Nullable
    public static final File relativeToOrNull(@NotNull File file, @NotNull File file2) {
        Intrinsics.checkParameterIsNotNull(file, "receiver$0");
        Intrinsics.checkParameterIsNotNull(file2, "base");
        file = toRelativeStringOrNull$FilesKt__UtilsKt(file, file2);
        return file != null ? new File(file) : null;
    }

    private static final String toRelativeStringOrNull$FilesKt__UtilsKt(@NotNull File file, File file2) {
        FilePathComponents normalize$FilesKt__UtilsKt = normalize$FilesKt__UtilsKt(FilesKt__FilePathComponentsKt.toComponents(file));
        FilePathComponents normalize$FilesKt__UtilsKt2 = normalize$FilesKt__UtilsKt(FilesKt__FilePathComponentsKt.toComponents(file2));
        if ((Intrinsics.areEqual(normalize$FilesKt__UtilsKt.getRoot(), normalize$FilesKt__UtilsKt2.getRoot()) ^ 1) != 0) {
            return null;
        }
        int size = normalize$FilesKt__UtilsKt2.getSize();
        int size2 = normalize$FilesKt__UtilsKt.getSize();
        int i = 0;
        int min = Math.min(size2, size);
        while (i < min && Intrinsics.areEqual((File) normalize$FilesKt__UtilsKt.getSegments().get(i), (File) normalize$FilesKt__UtilsKt2.getSegments().get(i))) {
            i++;
        }
        StringBuilder stringBuilder = new StringBuilder();
        int i2 = size - 1;
        if (i2 >= i) {
            while (!Intrinsics.areEqual(((File) normalize$FilesKt__UtilsKt2.getSegments().get(i2)).getName(), (Object) "..")) {
                stringBuilder.append("..");
                if (i2 != i) {
                    stringBuilder.append(File.separatorChar);
                }
                if (i2 != i) {
                    i2--;
                }
            }
            return null;
        }
        if (i < size2) {
            if (i < size) {
                stringBuilder.append(File.separatorChar);
            }
            Iterable drop = CollectionsKt___CollectionsKt.drop(normalize$FilesKt__UtilsKt.getSegments(), i);
            Appendable appendable = stringBuilder;
            String str = File.separator;
            Intrinsics.checkExpressionValueIsNotNull(str, "File.separator");
            CollectionsKt___CollectionsKt.joinTo$default(drop, appendable, str, null, null, 0, null, null, 124, null);
        }
        return stringBuilder.toString();
    }

    @NotNull
    public static /* synthetic */ File copyTo$default(File file, File file2, boolean z, int i, int i2, Object obj) {
        if ((i2 & 2) != null) {
            z = false;
        }
        if ((i2 & 4) != 0) {
            i = 8192;
        }
        return copyTo(file, file2, z, i);
    }

    @NotNull
    public static final File copyTo(@NotNull File file, @NotNull File file2, boolean z, int i) {
        Closeable fileOutputStream;
        Intrinsics.checkParameterIsNotNull(file, "receiver$0");
        Intrinsics.checkParameterIsNotNull(file2, "target");
        if (file.exists()) {
            if (file2.exists()) {
                Object obj = 1;
                if (z) {
                    if (file2.delete()) {
                        obj = null;
                    }
                }
                if (obj != null) {
                    throw ((Throwable) new FileAlreadyExistsException(file, file2, "The destination file already exists."));
                }
            }
            if (!file.isDirectory()) {
                z = file2.getParentFile();
                if (z) {
                    z.mkdirs();
                }
                Closeable closeable = (Closeable) new FileInputStream(file);
                Throwable th = (Throwable) null;
                try {
                    fileOutputStream = new FileOutputStream(file2);
                    Throwable th2 = (Throwable) null;
                    ByteStreamsKt.copyTo((FileInputStream) closeable, (FileOutputStream) fileOutputStream, i);
                    CloseableKt.closeFinally(fileOutputStream, th2);
                    CloseableKt.closeFinally(closeable, th);
                } catch (File file3) {
                    try {
                    } catch (Throwable th3) {
                        CloseableKt.closeFinally(closeable, file3);
                    }
                }
            } else if (!file2.mkdirs()) {
                throw ((Throwable) new FileSystemException(file3, file2, "Failed to create target directory."));
            }
            return file2;
        }
        throw ((Throwable) new NoSuchFileException(file3, null, "The source file doesn't exist.", 2, null));
    }

    public static /* synthetic */ boolean copyRecursively$default(File file, File file2, boolean z, Function2 function2, int i, Object obj) {
        if ((i & 2) != null) {
            z = false;
        }
        if ((i & 4) != 0) {
            function2 = FilesKt__UtilsKt$copyRecursively$1.INSTANCE;
        }
        return copyRecursively(file, file2, z, function2);
    }

    public static final boolean copyRecursively(@org.jetbrains.annotations.NotNull java.io.File r11, @org.jetbrains.annotations.NotNull java.io.File r12, boolean r13, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function2<? super java.io.File, ? super java.io.IOException, ? extends kotlin.io.OnErrorAction> r14) {
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
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r11, r0);
        r0 = "target";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r12, r0);
        r0 = "onError";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r14, r0);
        r0 = r11.exists();
        r1 = 1;
        r2 = 0;
        if (r0 != 0) goto L_0x0030;
    L_0x0017:
        r12 = new kotlin.io.NoSuchFileException;
        r5 = 0;
        r6 = "The source file doesn't exist.";
        r7 = 2;
        r8 = 0;
        r3 = r12;
        r4 = r11;
        r3.<init>(r4, r5, r6, r7, r8);
        r11 = r14.invoke(r11, r12);
        r11 = (kotlin.io.OnErrorAction) r11;
        r12 = kotlin.io.OnErrorAction.TERMINATE;
        if (r11 == r12) goto L_0x002e;
    L_0x002d:
        goto L_0x002f;
    L_0x002e:
        r1 = 0;
    L_0x002f:
        return r1;
    L_0x0030:
        r0 = kotlin.io.FilesKt__FileTreeWalkKt.walkTopDown(r11);	 Catch:{ TerminateException -> 0x00e7 }
        r3 = new kotlin.io.FilesKt__UtilsKt$copyRecursively$2;	 Catch:{ TerminateException -> 0x00e7 }
        r3.<init>(r14);	 Catch:{ TerminateException -> 0x00e7 }
        r3 = (kotlin.jvm.functions.Function2) r3;	 Catch:{ TerminateException -> 0x00e7 }
        r0 = r0.onFail(r3);	 Catch:{ TerminateException -> 0x00e7 }
        r0 = r0.iterator();	 Catch:{ TerminateException -> 0x00e7 }
    L_0x0043:
        r3 = r0.hasNext();	 Catch:{ TerminateException -> 0x00e7 }
        if (r3 == 0) goto L_0x00e6;	 Catch:{ TerminateException -> 0x00e7 }
    L_0x0049:
        r3 = r0.next();	 Catch:{ TerminateException -> 0x00e7 }
        r3 = (java.io.File) r3;	 Catch:{ TerminateException -> 0x00e7 }
        r4 = r3.exists();	 Catch:{ TerminateException -> 0x00e7 }
        if (r4 != 0) goto L_0x006c;	 Catch:{ TerminateException -> 0x00e7 }
    L_0x0055:
        r10 = new kotlin.io.NoSuchFileException;	 Catch:{ TerminateException -> 0x00e7 }
        r6 = 0;	 Catch:{ TerminateException -> 0x00e7 }
        r7 = "The source file doesn't exist.";	 Catch:{ TerminateException -> 0x00e7 }
        r8 = 2;	 Catch:{ TerminateException -> 0x00e7 }
        r9 = 0;	 Catch:{ TerminateException -> 0x00e7 }
        r4 = r10;	 Catch:{ TerminateException -> 0x00e7 }
        r5 = r3;	 Catch:{ TerminateException -> 0x00e7 }
        r4.<init>(r5, r6, r7, r8, r9);	 Catch:{ TerminateException -> 0x00e7 }
        r3 = r14.invoke(r3, r10);	 Catch:{ TerminateException -> 0x00e7 }
        r3 = (kotlin.io.OnErrorAction) r3;	 Catch:{ TerminateException -> 0x00e7 }
        r4 = kotlin.io.OnErrorAction.TERMINATE;	 Catch:{ TerminateException -> 0x00e7 }
        if (r3 != r4) goto L_0x0043;	 Catch:{ TerminateException -> 0x00e7 }
    L_0x006b:
        return r2;	 Catch:{ TerminateException -> 0x00e7 }
    L_0x006c:
        r4 = toRelativeString(r3, r11);	 Catch:{ TerminateException -> 0x00e7 }
        r5 = new java.io.File;	 Catch:{ TerminateException -> 0x00e7 }
        r5.<init>(r12, r4);	 Catch:{ TerminateException -> 0x00e7 }
        r4 = r5.exists();	 Catch:{ TerminateException -> 0x00e7 }
        if (r4 == 0) goto L_0x00b5;	 Catch:{ TerminateException -> 0x00e7 }
    L_0x007b:
        r4 = r3.isDirectory();	 Catch:{ TerminateException -> 0x00e7 }
        if (r4 == 0) goto L_0x0087;	 Catch:{ TerminateException -> 0x00e7 }
    L_0x0081:
        r4 = r5.isDirectory();	 Catch:{ TerminateException -> 0x00e7 }
        if (r4 != 0) goto L_0x00b5;	 Catch:{ TerminateException -> 0x00e7 }
    L_0x0087:
        if (r13 != 0) goto L_0x008b;	 Catch:{ TerminateException -> 0x00e7 }
    L_0x0089:
        r4 = 1;	 Catch:{ TerminateException -> 0x00e7 }
        goto L_0x00a1;	 Catch:{ TerminateException -> 0x00e7 }
    L_0x008b:
        r4 = r5.isDirectory();	 Catch:{ TerminateException -> 0x00e7 }
        if (r4 == 0) goto L_0x009a;	 Catch:{ TerminateException -> 0x00e7 }
    L_0x0091:
        r4 = deleteRecursively(r5);	 Catch:{ TerminateException -> 0x00e7 }
        if (r4 != 0) goto L_0x0098;	 Catch:{ TerminateException -> 0x00e7 }
    L_0x0097:
        goto L_0x0089;	 Catch:{ TerminateException -> 0x00e7 }
    L_0x0098:
        r4 = 0;	 Catch:{ TerminateException -> 0x00e7 }
        goto L_0x00a1;	 Catch:{ TerminateException -> 0x00e7 }
    L_0x009a:
        r4 = r5.delete();	 Catch:{ TerminateException -> 0x00e7 }
        if (r4 != 0) goto L_0x0098;	 Catch:{ TerminateException -> 0x00e7 }
    L_0x00a0:
        goto L_0x0089;	 Catch:{ TerminateException -> 0x00e7 }
    L_0x00a1:
        if (r4 == 0) goto L_0x00b5;	 Catch:{ TerminateException -> 0x00e7 }
    L_0x00a3:
        r4 = new kotlin.io.FileAlreadyExistsException;	 Catch:{ TerminateException -> 0x00e7 }
        r6 = "The destination file already exists.";	 Catch:{ TerminateException -> 0x00e7 }
        r4.<init>(r3, r5, r6);	 Catch:{ TerminateException -> 0x00e7 }
        r3 = r14.invoke(r5, r4);	 Catch:{ TerminateException -> 0x00e7 }
        r3 = (kotlin.io.OnErrorAction) r3;	 Catch:{ TerminateException -> 0x00e7 }
        r4 = kotlin.io.OnErrorAction.TERMINATE;	 Catch:{ TerminateException -> 0x00e7 }
        if (r3 != r4) goto L_0x0043;	 Catch:{ TerminateException -> 0x00e7 }
    L_0x00b4:
        return r2;	 Catch:{ TerminateException -> 0x00e7 }
    L_0x00b5:
        r4 = r3.isDirectory();	 Catch:{ TerminateException -> 0x00e7 }
        if (r4 == 0) goto L_0x00bf;	 Catch:{ TerminateException -> 0x00e7 }
    L_0x00bb:
        r5.mkdirs();	 Catch:{ TerminateException -> 0x00e7 }
        goto L_0x0043;	 Catch:{ TerminateException -> 0x00e7 }
    L_0x00bf:
        r7 = 0;	 Catch:{ TerminateException -> 0x00e7 }
        r8 = 4;	 Catch:{ TerminateException -> 0x00e7 }
        r9 = 0;	 Catch:{ TerminateException -> 0x00e7 }
        r4 = r3;	 Catch:{ TerminateException -> 0x00e7 }
        r6 = r13;	 Catch:{ TerminateException -> 0x00e7 }
        r4 = copyTo$default(r4, r5, r6, r7, r8, r9);	 Catch:{ TerminateException -> 0x00e7 }
        r4 = r4.length();	 Catch:{ TerminateException -> 0x00e7 }
        r6 = r3.length();	 Catch:{ TerminateException -> 0x00e7 }
        r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));	 Catch:{ TerminateException -> 0x00e7 }
        if (r4 == 0) goto L_0x0043;	 Catch:{ TerminateException -> 0x00e7 }
    L_0x00d4:
        r4 = new java.io.IOException;	 Catch:{ TerminateException -> 0x00e7 }
        r5 = "Source file wasn't copied completely, length of destination file differs.";	 Catch:{ TerminateException -> 0x00e7 }
        r4.<init>(r5);	 Catch:{ TerminateException -> 0x00e7 }
        r3 = r14.invoke(r3, r4);	 Catch:{ TerminateException -> 0x00e7 }
        r3 = (kotlin.io.OnErrorAction) r3;	 Catch:{ TerminateException -> 0x00e7 }
        r4 = kotlin.io.OnErrorAction.TERMINATE;	 Catch:{ TerminateException -> 0x00e7 }
        if (r3 != r4) goto L_0x0043;
    L_0x00e5:
        return r2;
    L_0x00e6:
        return r1;
    L_0x00e7:
        return r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.io.FilesKt__UtilsKt.copyRecursively(java.io.File, java.io.File, boolean, kotlin.jvm.functions.Function2):boolean");
    }

    public static final boolean deleteRecursively(@NotNull File file) {
        Intrinsics.checkParameterIsNotNull(file, "receiver$0");
        while (true) {
            boolean z = true;
            for (File file2 : FilesKt__FileTreeWalkKt.walkBottomUp(file)) {
                if ((!file2.delete() && file2.exists()) || !z) {
                    z = false;
                }
            }
            return z;
        }
    }

    public static final boolean startsWith(@NotNull File file, @NotNull File file2) {
        Intrinsics.checkParameterIsNotNull(file, "receiver$0");
        Intrinsics.checkParameterIsNotNull(file2, "other");
        file = FilesKt__FilePathComponentsKt.toComponents(file);
        file2 = FilesKt__FilePathComponentsKt.toComponents(file2);
        boolean z = false;
        if ((Intrinsics.areEqual(file.getRoot(), file2.getRoot()) ^ 1) != 0) {
            return false;
        }
        if (file.getSize() >= file2.getSize()) {
            z = file.getSegments().subList(0, file2.getSize()).equals(file2.getSegments());
        }
        return z;
    }

    public static final boolean startsWith(@NotNull File file, @NotNull String str) {
        Intrinsics.checkParameterIsNotNull(file, "receiver$0");
        Intrinsics.checkParameterIsNotNull(str, "other");
        return startsWith(file, new File(str));
    }

    public static final boolean endsWith(@NotNull File file, @NotNull File file2) {
        Intrinsics.checkParameterIsNotNull(file, "receiver$0");
        Intrinsics.checkParameterIsNotNull(file2, "other");
        FilePathComponents toComponents = FilesKt__FilePathComponentsKt.toComponents(file);
        FilePathComponents toComponents2 = FilesKt__FilePathComponentsKt.toComponents(file2);
        if (toComponents2.isRooted()) {
            return Intrinsics.areEqual((Object) file, (Object) file2);
        }
        file = toComponents.getSize() - toComponents2.getSize();
        if (file < null) {
            file = null;
        } else {
            file = toComponents.getSegments().subList(file, toComponents.getSize()).equals(toComponents2.getSegments());
        }
        return file;
    }

    public static final boolean endsWith(@NotNull File file, @NotNull String str) {
        Intrinsics.checkParameterIsNotNull(file, "receiver$0");
        Intrinsics.checkParameterIsNotNull(str, "other");
        return endsWith(file, new File(str));
    }

    @NotNull
    public static final File normalize(@NotNull File file) {
        Intrinsics.checkParameterIsNotNull(file, "receiver$0");
        file = FilesKt__FilePathComponentsKt.toComponents(file);
        File root = file.getRoot();
        Iterable normalize$FilesKt__UtilsKt = normalize$FilesKt__UtilsKt(file.getSegments());
        file = File.separator;
        Intrinsics.checkExpressionValueIsNotNull(file, "File.separator");
        return resolve(root, (String) CollectionsKt___CollectionsKt.joinToString$default(normalize$FilesKt__UtilsKt, (CharSequence) file, null, null, 0, null, null, 62, null));
    }

    private static final FilePathComponents normalize$FilesKt__UtilsKt(@NotNull FilePathComponents filePathComponents) {
        return new FilePathComponents(filePathComponents.getRoot(), normalize$FilesKt__UtilsKt(filePathComponents.getSegments()));
    }

    private static final List<File> normalize$FilesKt__UtilsKt(@NotNull List<? extends File> list) {
        List<File> arrayList = new ArrayList(list.size());
        for (File file : list) {
            String name = file.getName();
            if (name != null) {
                int hashCode = name.hashCode();
                if (hashCode != 46) {
                    if (hashCode == 1472) {
                        if (name.equals("..")) {
                            if (arrayList.isEmpty() || (Intrinsics.areEqual(((File) CollectionsKt___CollectionsKt.last((List) arrayList)).getName(), (Object) "..") ^ 1) == 0) {
                                arrayList.add(file);
                            } else {
                                arrayList.remove(arrayList.size() - 1);
                            }
                        }
                    }
                } else if (name.equals(".")) {
                }
            }
            arrayList.add(file);
        }
        return arrayList;
    }

    @NotNull
    public static final File resolve(@NotNull File file, @NotNull File file2) {
        Intrinsics.checkParameterIsNotNull(file, "receiver$0");
        Intrinsics.checkParameterIsNotNull(file2, "relative");
        if (FilesKt__FilePathComponentsKt.isRooted(file2)) {
            return file2;
        }
        StringBuilder stringBuilder;
        File file3;
        file = file.toString();
        Intrinsics.checkExpressionValueIsNotNull(file, "this.toString()");
        CharSequence charSequence = (CharSequence) file;
        if ((charSequence.length() == 0 ? 1 : null) == null) {
            if (!StringsKt__StringsKt.endsWith$default(charSequence, File.separatorChar, false, 2, null)) {
                stringBuilder = new StringBuilder();
                stringBuilder.append(file);
                stringBuilder.append(File.separatorChar);
                stringBuilder.append(file2);
                file3 = new File(stringBuilder.toString());
                return file3;
            }
        }
        stringBuilder = new StringBuilder();
        stringBuilder.append(file);
        stringBuilder.append(file2);
        file3 = new File(stringBuilder.toString());
        return file3;
    }

    @NotNull
    public static final File resolve(@NotNull File file, @NotNull String str) {
        Intrinsics.checkParameterIsNotNull(file, "receiver$0");
        Intrinsics.checkParameterIsNotNull(str, "relative");
        return resolve(file, new File(str));
    }

    @NotNull
    public static final File resolveSibling(@NotNull File file, @NotNull File file2) {
        Intrinsics.checkParameterIsNotNull(file, "receiver$0");
        Intrinsics.checkParameterIsNotNull(file2, "relative");
        file = FilesKt__FilePathComponentsKt.toComponents(file);
        return resolve(resolve(file.getRoot(), file.getSize() == 0 ? new File("..") : file.subPath(0, file.getSize() - 1)), file2);
    }

    @NotNull
    public static final File resolveSibling(@NotNull File file, @NotNull String str) {
        Intrinsics.checkParameterIsNotNull(file, "receiver$0");
        Intrinsics.checkParameterIsNotNull(str, "relative");
        return resolveSibling(file, new File(str));
    }
}
