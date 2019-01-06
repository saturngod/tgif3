package kotlin;

import java.io.Serializable;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SinceKotlin(version = "1.3")
@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\u0003\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\b@\u0018\u0000 \u001e*\u0006\b\u0000\u0010\u0001 \u00012\u00060\u0002j\u0002`\u0003:\u0002\u001e\u001fB\u0016\b\u0001\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005ø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\u0007J\u0013\u0010\u0010\u001a\u00020\t2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0005HÖ\u0003J\u000f\u0010\u0012\u001a\u0004\u0018\u00010\u0013¢\u0006\u0004\b\u0014\u0010\u0015J\u0012\u0010\u0016\u001a\u0004\u0018\u00018\u0000H\b¢\u0006\u0004\b\u0017\u0010\u0007J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\u000f\u0010\u001a\u001a\u00020\u001bH\u0016¢\u0006\u0004\b\u001c\u0010\u001dR\u0011\u0010\b\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\r\u0010\u000bR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0000X\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u000e\u0010\u000fø\u0001\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006 "}, d2 = {"Lkotlin/Result;", "T", "Ljava/io/Serializable;", "Lkotlin/io/Serializable;", "value", "", "constructor-impl", "(Ljava/lang/Object;)Ljava/lang/Object;", "isFailure", "", "isFailure-impl", "(Ljava/lang/Object;)Z", "isSuccess", "isSuccess-impl", "value$annotations", "()V", "equals", "other", "exceptionOrNull", "", "exceptionOrNull-impl", "(Ljava/lang/Object;)Ljava/lang/Throwable;", "getOrNull", "getOrNull-impl", "hashCode", "", "toString", "", "toString-impl", "(Ljava/lang/Object;)Ljava/lang/String;", "Companion", "Failure", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
/* compiled from: Result.kt */
public final class Result<T> implements Serializable {
    public static final Companion Companion = new Companion();
    @Nullable
    private final Object value;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0005\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J%\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0001\u0010\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\bø\u0001\u0000¢\u0006\u0002\u0010\bJ%\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0001\u0010\u00052\u0006\u0010\n\u001a\u0002H\u0005H\bø\u0001\u0000¢\u0006\u0002\u0010\u000b\u0002\u0004\n\u0002\b\u0019¨\u0006\f"}, d2 = {"Lkotlin/Result$Companion;", "", "()V", "failure", "Lkotlin/Result;", "T", "exception", "", "(Ljava/lang/Throwable;)Ljava/lang/Object;", "success", "value", "(Ljava/lang/Object;)Ljava/lang/Object;", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
    /* compiled from: Result.kt */
    public static final class Companion {
        private Companion() {
        }

        @InlineOnly
        private final <T> Object success(T t) {
            return Result.constructor-impl(t);
        }

        @InlineOnly
        private final <T> Object failure(Throwable th) {
            return Result.constructor-impl(ResultKt.createFailure(th));
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0000\u0018\u00002\u00060\u0001j\u0002`\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0013\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0002J\b\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010\f\u001a\u00020\rH\u0016R\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lkotlin/Result$Failure;", "Ljava/io/Serializable;", "Lkotlin/io/Serializable;", "exception", "", "(Ljava/lang/Throwable;)V", "equals", "", "other", "", "hashCode", "", "toString", "", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
    /* compiled from: Result.kt */
    public static final class Failure implements Serializable {
        @NotNull
        @JvmField
        public final Throwable exception;

        public Failure(@NotNull Throwable th) {
            Intrinsics.checkParameterIsNotNull(th, "exception");
            this.exception = th;
        }

        public boolean equals(@Nullable Object obj) {
            return (!(obj instanceof Failure) || Intrinsics.areEqual(this.exception, ((Failure) obj).exception) == null) ? null : true;
        }

        public int hashCode() {
            return this.exception.hashCode();
        }

        @NotNull
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Failure(");
            stringBuilder.append(this.exception);
            stringBuilder.append(')');
            return stringBuilder.toString();
        }
    }

    @NotNull
    @PublishedApi
    public static Object constructor-impl(@Nullable Object obj) {
        return obj;
    }

    public static boolean equals-impl(Object obj, @Nullable Object obj2) {
        return (obj2 instanceof Result) && Intrinsics.areEqual(obj, ((Result) obj2).unbox-impl());
    }

    public static final boolean equals-impl0(@Nullable Object obj, @Nullable Object obj2) {
        throw null;
    }

    public static int hashCode-impl(Object obj) {
        return obj != null ? obj.hashCode() : 0;
    }

    @PublishedApi
    public static /* synthetic */ void value$annotations() {
    }

    public boolean equals(Object obj) {
        return equals-impl(this.value, obj);
    }

    public int hashCode() {
        return hashCode-impl(this.value);
    }

    @NotNull
    public String toString() {
        return toString-impl(this.value);
    }

    @Nullable
    public final /* synthetic */ Object unbox-impl() {
        return this.value;
    }

    @PublishedApi
    private /* synthetic */ Result(@Nullable Object obj) {
        this.value = obj;
    }

    public static final boolean isSuccess-impl(Object obj) {
        return (obj instanceof Failure) == null ? true : null;
    }

    public static final boolean isFailure-impl(Object obj) {
        return obj instanceof Failure;
    }

    @InlineOnly
    private static final T getOrNull-impl(Object obj) {
        return isFailure-impl(obj) ? null : obj;
    }

    @Nullable
    public static final Throwable exceptionOrNull-impl(Object obj) {
        return obj instanceof Failure ? ((Failure) obj).exception : null;
    }

    @NotNull
    public static String toString-impl(Object obj) {
        if (obj instanceof Failure) {
            return obj.toString();
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Success(");
        stringBuilder.append(obj);
        stringBuilder.append(')');
        return stringBuilder.toString();
    }
}
