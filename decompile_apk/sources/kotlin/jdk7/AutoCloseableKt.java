package kotlin.jdk7;

import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.SinceKotlin;
import kotlin.jvm.JvmName;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0018\u0010\u0000\u001a\u00020\u0001*\u0004\u0018\u00010\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\u0001\u001a8\u0010\u0005\u001a\u0002H\u0006\"\n\b\u0000\u0010\u0007*\u0004\u0018\u00010\u0002\"\u0004\b\u0001\u0010\u0006*\u0002H\u00072\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u0002H\u00060\tH\b¢\u0006\u0002\u0010\n¨\u0006\u000b"}, d2 = {"closeFinally", "", "Ljava/lang/AutoCloseable;", "cause", "", "use", "R", "T", "block", "Lkotlin/Function1;", "(Ljava/lang/AutoCloseable;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "kotlin-stdlib-jdk7"}, k = 2, mv = {1, 1, 11}, pn = "kotlin")
@JvmName(name = "AutoCloseableKt")
/* compiled from: AutoCloseable.kt */
public final class AutoCloseableKt {
    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @kotlin.SinceKotlin(version = "1.2")
    @kotlin.internal.InlineOnly
    private static final <T extends java.lang.AutoCloseable, R> R use(T r2, kotlin.jvm.functions.Function1<? super T, ? extends R> r3) {
        /*
        r0 = 0;
        r0 = (java.lang.Throwable) r0;
        r1 = 1;
        r3 = r3.invoke(r2);	 Catch:{ Throwable -> 0x0014 }
        kotlin.jvm.internal.InlineMarker.finallyStart(r1);
        closeFinally(r2, r0);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r1);
        return r3;
    L_0x0012:
        r3 = move-exception;
        goto L_0x0016;
    L_0x0014:
        r0 = move-exception;
        throw r0;	 Catch:{ all -> 0x0012 }
    L_0x0016:
        kotlin.jvm.internal.InlineMarker.finallyStart(r1);
        closeFinally(r2, r0);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r1);
        throw r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.jdk7.AutoCloseableKt.use(java.lang.AutoCloseable, kotlin.jvm.functions.Function1):R");
    }

    @SinceKotlin(version = "1.2")
    @PublishedApi
    public static final void closeFinally(@Nullable AutoCloseable autoCloseable, @Nullable Throwable th) {
        if (autoCloseable != null) {
            if (th == null) {
                autoCloseable.close();
                return;
            }
            try {
                autoCloseable.close();
            } catch (AutoCloseable autoCloseable2) {
                th.addSuppressed(autoCloseable2);
            }
        }
    }
}
