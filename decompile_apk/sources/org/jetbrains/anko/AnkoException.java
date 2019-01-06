package org.jetbrains.anko;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0016\u0018\u00002\u00060\u0001j\u0002`\u0002B\u000f\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005¨\u0006\u0006"}, d2 = {"Lorg/jetbrains/anko/AnkoException;", "Ljava/lang/RuntimeException;", "Lkotlin/RuntimeException;", "message", "", "(Ljava/lang/String;)V", "commons-base_release"}, k = 1, mv = {1, 1, 13})
/* compiled from: Helpers.kt */
public class AnkoException extends RuntimeException {
    public AnkoException() {
        this(null, 1, null);
    }

    public AnkoException(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "message");
        super(str);
    }

    public /* synthetic */ AnkoException(String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 1) != 0) {
            str = "";
        }
        this(str);
    }
}
