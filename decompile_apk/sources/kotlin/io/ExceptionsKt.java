package kotlin.io;

import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a$\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00032\b\u0010\u0005\u001a\u0004\u0018\u00010\u0001H\u0002¨\u0006\u0006"}, d2 = {"constructMessage", "", "file", "Ljava/io/File;", "other", "reason", "kotlin-stdlib"}, k = 2, mv = {1, 1, 13})
/* compiled from: Exceptions.kt */
public final class ExceptionsKt {
    private static final String constructMessage(File file, File file2, String str) {
        StringBuilder stringBuilder = new StringBuilder(file.toString());
        if (file2 != null) {
            file = new StringBuilder();
            file.append(" -> ");
            file.append(file2);
            stringBuilder.append(file.toString());
        }
        if (str != null) {
            file = new StringBuilder();
            file.append(": ");
            file.append(str);
            stringBuilder.append(file.toString());
        }
        file = stringBuilder.toString();
        Intrinsics.checkExpressionValueIsNotNull(file, "sb.toString()");
        return file;
    }
}
