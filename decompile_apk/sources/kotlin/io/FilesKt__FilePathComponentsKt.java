package kotlin.io;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\u001a\u0011\u0010\u000b\u001a\u00020\f*\u00020\bH\u0002¢\u0006\u0002\b\r\u001a\u001c\u0010\u000e\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\fH\u0000\u001a\f\u0010\u0011\u001a\u00020\u0012*\u00020\u0002H\u0000\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0000\u0010\u0003\"\u0018\u0010\u0004\u001a\u00020\u0002*\u00020\u00028@X\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006\"\u0018\u0010\u0007\u001a\u00020\b*\u00020\u00028@X\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\n¨\u0006\u0013"}, d2 = {"isRooted", "", "Ljava/io/File;", "(Ljava/io/File;)Z", "root", "getRoot", "(Ljava/io/File;)Ljava/io/File;", "rootName", "", "getRootName", "(Ljava/io/File;)Ljava/lang/String;", "getRootLength", "", "getRootLength$FilesKt__FilePathComponentsKt", "subPath", "beginIndex", "endIndex", "toComponents", "Lkotlin/io/FilePathComponents;", "kotlin-stdlib"}, k = 5, mv = {1, 1, 13}, xi = 1, xs = "kotlin/io/FilesKt")
/* compiled from: FilePathComponents.kt */
class FilesKt__FilePathComponentsKt {
    private static final int getRootLength$FilesKt__FilePathComponentsKt(@NotNull String str) {
        CharSequence charSequence = str;
        int indexOf$default = StringsKt__StringsKt.indexOf$default(charSequence, File.separatorChar, 0, false, 4, null);
        if (indexOf$default == 0) {
            if (str.length() > 1 && str.charAt(1) == File.separatorChar) {
                indexOf$default = StringsKt__StringsKt.indexOf$default(charSequence, File.separatorChar, 2, false, 4, null);
                if (indexOf$default >= 0) {
                    int i = indexOf$default + 1;
                    indexOf$default = StringsKt__StringsKt.indexOf$default(charSequence, File.separatorChar, i, false, 4, null);
                    if (indexOf$default >= 0) {
                        return indexOf$default + 1;
                    }
                    return str.length();
                }
            }
            return 1;
        } else if (indexOf$default > 0 && str.charAt(indexOf$default - 1) == ':') {
            return indexOf$default + 1;
        } else {
            if (indexOf$default == -1 && StringsKt__StringsKt.endsWith$default(charSequence, ':', false, 2, null)) {
                return str.length();
            }
            return 0;
        }
    }

    @NotNull
    public static final String getRootName(@NotNull File file) {
        Intrinsics.checkParameterIsNotNull(file, "receiver$0");
        String path = file.getPath();
        Intrinsics.checkExpressionValueIsNotNull(path, "path");
        file = file.getPath();
        Intrinsics.checkExpressionValueIsNotNull(file, "path");
        file = getRootLength$FilesKt__FilePathComponentsKt(file);
        if (path != null) {
            file = path.substring(0, file);
            Intrinsics.checkExpressionValueIsNotNull(file, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            return file;
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }

    @NotNull
    public static final File getRoot(@NotNull File file) {
        Intrinsics.checkParameterIsNotNull(file, "receiver$0");
        return new File(getRootName(file));
    }

    public static final boolean isRooted(@NotNull File file) {
        Intrinsics.checkParameterIsNotNull(file, "receiver$0");
        file = file.getPath();
        Intrinsics.checkExpressionValueIsNotNull(file, "path");
        return getRootLength$FilesKt__FilePathComponentsKt(file) > null ? true : null;
    }

    @NotNull
    public static final FilePathComponents toComponents(@NotNull File file) {
        Intrinsics.checkParameterIsNotNull(file, "receiver$0");
        file = file.getPath();
        Intrinsics.checkExpressionValueIsNotNull(file, "path");
        int rootLength$FilesKt__FilePathComponentsKt = getRootLength$FilesKt__FilePathComponentsKt(file);
        String substring = file.substring(0, rootLength$FilesKt__FilePathComponentsKt);
        Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        file = file.substring(rootLength$FilesKt__FilePathComponentsKt);
        Intrinsics.checkExpressionValueIsNotNull(file, "(this as java.lang.String).substring(startIndex)");
        CharSequence charSequence = (CharSequence) file;
        if ((charSequence.length() == null ? true : null) != null) {
            file = CollectionsKt__CollectionsKt.emptyList();
        } else {
            Iterable<String> split$default = StringsKt__StringsKt.split$default(charSequence, new char[]{File.separatorChar}, false, 0, 6, null);
            Collection arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(split$default, 10));
            for (String file2 : split$default) {
                arrayList.add(new File(file2));
            }
            file = (List) arrayList;
        }
        return new FilePathComponents(new File(substring), file);
    }

    @NotNull
    public static final File subPath(@NotNull File file, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(file, "receiver$0");
        return toComponents(file).subPath(i, i2);
    }
}
