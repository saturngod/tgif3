package kotlin;

import java.io.PrintStream;
import java.io.PrintWriter;
import kotlin.internal.InlineOnly;
import kotlin.internal.PlatformImplementationsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0012\u0010\b\u001a\u00020\t*\u00020\u00032\u0006\u0010\n\u001a\u00020\u0003\u001a\r\u0010\u000b\u001a\u00020\t*\u00020\u0003H\b\u001a\u0015\u0010\u000b\u001a\u00020\t*\u00020\u00032\u0006\u0010\f\u001a\u00020\rH\b\u001a\u0015\u0010\u000b\u001a\u00020\t*\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u000fH\b\"!\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00038F¢\u0006\f\u0012\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0010"}, d2 = {"stackTrace", "", "Ljava/lang/StackTraceElement;", "", "stackTrace$annotations", "(Ljava/lang/Throwable;)V", "getStackTrace", "(Ljava/lang/Throwable;)[Ljava/lang/StackTraceElement;", "addSuppressed", "", "exception", "printStackTrace", "stream", "Ljava/io/PrintStream;", "writer", "Ljava/io/PrintWriter;", "kotlin-stdlib"}, k = 5, mv = {1, 1, 13}, xi = 1, xs = "kotlin/ExceptionsKt")
/* compiled from: Exceptions.kt */
class ExceptionsKt__ExceptionsKt {
    public static /* synthetic */ void stackTrace$annotations(Throwable th) {
    }

    @InlineOnly
    private static final void printStackTrace(@NotNull Throwable th) {
        if (th != null) {
            th.printStackTrace();
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.Throwable");
    }

    @InlineOnly
    private static final void printStackTrace(@NotNull Throwable th, PrintWriter printWriter) {
        if (th != null) {
            th.printStackTrace(printWriter);
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.Throwable");
    }

    @InlineOnly
    private static final void printStackTrace(@NotNull Throwable th, PrintStream printStream) {
        if (th != null) {
            th.printStackTrace(printStream);
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.Throwable");
    }

    @NotNull
    public static final StackTraceElement[] getStackTrace(@NotNull Throwable th) {
        Intrinsics.checkParameterIsNotNull(th, "receiver$0");
        th = th.getStackTrace();
        if (th == null) {
            Intrinsics.throwNpe();
        }
        return th;
    }

    public static final void addSuppressed(@NotNull Throwable th, @NotNull Throwable th2) {
        Intrinsics.checkParameterIsNotNull(th, "receiver$0");
        Intrinsics.checkParameterIsNotNull(th2, "exception");
        PlatformImplementationsKt.IMPLEMENTATIONS.addSuppressed(th, th2);
    }
}
