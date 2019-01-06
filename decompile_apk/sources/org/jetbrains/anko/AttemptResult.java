package org.jetbrains.anko;

import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\u00020\u0002B\u001b\b\u0001\u0012\b\u0010\u0003\u001a\u0004\u0018\u00018\u0000\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u0011\u001a\u0004\u0018\u00018\u0000HÆ\u0003¢\u0006\u0002\u0010\u000fJ\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0005HÆ\u0003J,\u0010\u0013\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00018\u00002\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001¢\u0006\u0002\u0010\u0014J\u0013\u0010\u0015\u001a\u00020\n2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0002HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J)\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\u001a0\u0000\"\u0004\b\u0001\u0010\u001a2\u0012\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u0002H\u001a0\u001cH\bJ\t\u0010\u001d\u001a\u00020\u001eHÖ\u0001R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0012\u0010\t\u001a\u00020\n8Æ\u0002¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0012\u0010\r\u001a\u00020\n8Æ\u0002¢\u0006\u0006\u001a\u0004\b\r\u0010\fR\u0015\u0010\u0003\u001a\u0004\u0018\u00018\u0000¢\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001f"}, d2 = {"Lorg/jetbrains/anko/AttemptResult;", "T", "", "value", "error", "", "(Ljava/lang/Object;Ljava/lang/Throwable;)V", "getError", "()Ljava/lang/Throwable;", "hasValue", "", "getHasValue", "()Z", "isError", "getValue", "()Ljava/lang/Object;", "Ljava/lang/Object;", "component1", "component2", "copy", "(Ljava/lang/Object;Ljava/lang/Throwable;)Lorg/jetbrains/anko/AttemptResult;", "equals", "other", "hashCode", "", "then", "R", "f", "Lkotlin/Function1;", "toString", "", "commons-base_release"}, k = 1, mv = {1, 1, 13})
/* compiled from: Helpers.kt */
public final class AttemptResult<T> {
    @Nullable
    private final Throwable error;
    @Nullable
    private final T value;

    @NotNull
    public static /* synthetic */ AttemptResult copy$default(AttemptResult attemptResult, Object obj, Throwable th, int i, Object obj2) {
        if ((i & 1) != 0) {
            obj = attemptResult.value;
        }
        if ((i & 2) != 0) {
            th = attemptResult.error;
        }
        return attemptResult.copy(obj, th);
    }

    @Nullable
    public final T component1() {
        return this.value;
    }

    @Nullable
    public final Throwable component2() {
        return this.error;
    }

    @NotNull
    public final AttemptResult<T> copy(@Nullable T t, @Nullable Throwable th) {
        return new AttemptResult(t, th);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof AttemptResult) {
                AttemptResult attemptResult = (AttemptResult) obj;
                if (Intrinsics.areEqual(this.value, attemptResult.value) && Intrinsics.areEqual(this.error, attemptResult.error)) {
                }
            }
            return false;
        }
        return true;
    }

    public int hashCode() {
        Object obj = this.value;
        int i = 0;
        int hashCode = (obj != null ? obj.hashCode() : 0) * 31;
        Throwable th = this.error;
        if (th != null) {
            i = th.hashCode();
        }
        return hashCode + i;
    }

    @NotNull
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("AttemptResult(value=");
        stringBuilder.append(this.value);
        stringBuilder.append(", error=");
        stringBuilder.append(this.error);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    @PublishedApi
    public AttemptResult(@Nullable T t, @Nullable Throwable th) {
        this.value = t;
        this.error = th;
    }

    @Nullable
    public final Throwable getError() {
        return this.error;
    }

    @Nullable
    public final T getValue() {
        return this.value;
    }

    public final boolean isError() {
        return getError() != null;
    }

    public final boolean getHasValue() {
        return getError() == null;
    }

    @NotNull
    public final <R> AttemptResult<R> then(@NotNull Function1<? super T, ? extends R> function1) {
        Intrinsics.checkParameterIsNotNull(function1, "f");
        if ((getError() != null ? 1 : null) != null) {
            return this;
        }
        Throwable th = (Throwable) null;
        try {
            function1 = function1.invoke(getValue());
        } catch (Function1<? super T, ? extends R> function12) {
            th = function12;
            function12 = null;
        }
        return new AttemptResult(function12, th);
    }
}
