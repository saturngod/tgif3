package kotlinx.coroutines;

import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0000\u0018\u00002\u00060\u0001j\u0002`\u0002B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0002J\b\u0010\u000e\u001a\u00020\u0006H\u0016J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u0004H\u0016R\u0010\u0010\u0007\u001a\u00020\b8\u0000X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lkotlinx/coroutines/JobCancellationException;", "Ljava/util/concurrent/CancellationException;", "Lkotlinx/coroutines/CancellationException;", "message", "", "cause", "", "job", "Lkotlinx/coroutines/Job;", "(Ljava/lang/String;Ljava/lang/Throwable;Lkotlinx/coroutines/Job;)V", "equals", "", "other", "", "fillInStackTrace", "hashCode", "", "toString", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
/* compiled from: Exceptions.kt */
public final class JobCancellationException extends CancellationException {
    @NotNull
    @JvmField
    public final Job job;

    public JobCancellationException(@NotNull String str, @Nullable Throwable th, @NotNull Job job) {
        Intrinsics.checkParameterIsNotNull(str, "message");
        Intrinsics.checkParameterIsNotNull(job, "job");
        super(str);
        this.job = job;
        if (th != null) {
            initCause(th);
        }
    }

    @NotNull
    public Throwable fillInStackTrace() {
        if (!DebugKt.getDEBUG()) {
            return this;
        }
        Throwable fillInStackTrace = super.fillInStackTrace();
        Intrinsics.checkExpressionValueIsNotNull(fillInStackTrace, "super.fillInStackTrace()");
        return fillInStackTrace;
    }

    @NotNull
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(super.toString());
        stringBuilder.append("; job=");
        stringBuilder.append(this.job);
        return stringBuilder.toString();
    }

    public boolean equals(@Nullable Object obj) {
        if (obj != this) {
            if (obj instanceof JobCancellationException) {
                JobCancellationException jobCancellationException = (JobCancellationException) obj;
                if (Intrinsics.areEqual(jobCancellationException.getMessage(), getMessage()) && Intrinsics.areEqual(jobCancellationException.job, this.job) && Intrinsics.areEqual(jobCancellationException.getCause(), getCause()) != null) {
                }
            }
            return null;
        }
        return true;
    }

    public int hashCode() {
        String message = getMessage();
        if (message == null) {
            Intrinsics.throwNpe();
        }
        int hashCode = ((message.hashCode() * 31) + this.job.hashCode()) * 31;
        Throwable cause = getCause();
        return hashCode + (cause != null ? cause.hashCode() : 0);
    }
}
