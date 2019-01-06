package android.support.v4.provider;

import android.content.Context;
import android.net.Uri;
import android.os.Build.VERSION;
import android.provider.DocumentsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.io.File;

public abstract class DocumentFile {
    static final String TAG = "DocumentFile";
    @Nullable
    private final DocumentFile mParent;

    public abstract boolean canRead();

    public abstract boolean canWrite();

    @Nullable
    public abstract DocumentFile createDirectory(@NonNull String str);

    @Nullable
    public abstract DocumentFile createFile(@NonNull String str, @NonNull String str2);

    public abstract boolean delete();

    public abstract boolean exists();

    @Nullable
    public abstract String getName();

    @Nullable
    public abstract String getType();

    @NonNull
    public abstract Uri getUri();

    public abstract boolean isDirectory();

    public abstract boolean isFile();

    public abstract boolean isVirtual();

    public abstract long lastModified();

    public abstract long length();

    @NonNull
    public abstract DocumentFile[] listFiles();

    public abstract boolean renameTo(@NonNull String str);

    DocumentFile(@Nullable DocumentFile documentFile) {
        this.mParent = documentFile;
    }

    @NonNull
    public static DocumentFile fromFile(@NonNull File file) {
        return new RawDocumentFile(null, file);
    }

    @Nullable
    public static DocumentFile fromSingleUri(@NonNull Context context, @NonNull Uri uri) {
        if (VERSION.SDK_INT >= 19) {
            return new SingleDocumentFile(null, context, uri);
        }
        return null;
    }

    @Nullable
    public static DocumentFile fromTreeUri(@NonNull Context context, @NonNull Uri uri) {
        if (VERSION.SDK_INT >= 21) {
            return new TreeDocumentFile(null, context, DocumentsContract.buildDocumentUriUsingTree(uri, DocumentsContract.getTreeDocumentId(uri)));
        }
        return null;
    }

    public static boolean isDocumentUri(@NonNull Context context, @Nullable Uri uri) {
        return VERSION.SDK_INT >= 19 ? DocumentsContract.isDocumentUri(context, uri) : null;
    }

    @Nullable
    public DocumentFile getParentFile() {
        return this.mParent;
    }

    @Nullable
    public DocumentFile findFile(@NonNull String str) {
        for (DocumentFile documentFile : listFiles()) {
            if (str.equals(documentFile.getName())) {
                return documentFile;
            }
        }
        return null;
    }
}
