package kotlin;

import kotlin.Result.Companion;
import kotlin.Result.Failure;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0001\u001a+\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00060\u0005\"\u0004\b\u0000\u0010\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\u00060\bH\bø\u0001\u0000¢\u0006\u0002\u0010\t\u001a\u0001\u0010\n\u001a\u0002H\u0006\"\u0004\b\u0000\u0010\u0006\"\u0004\b\u0001\u0010\u000b*\b\u0012\u0004\u0012\u0002H\u000b0\u00052!\u0010\f\u001a\u001d\u0012\u0013\u0012\u0011H\u000b¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u0002H\u00060\r2!\u0010\u0011\u001a\u001d\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0002\u0012\u0004\u0012\u0002H\u00060\rH\bø\u0001\u0000\u0002\u0014\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0000\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0000¢\u0006\u0002\u0010\u0012\u001a3\u0010\u0013\u001a\u0002H\u0006\"\u0004\b\u0000\u0010\u0006\"\b\b\u0001\u0010\u000b*\u0002H\u0006*\b\u0012\u0004\u0012\u0002H\u000b0\u00052\u0006\u0010\u0014\u001a\u0002H\u0006H\bø\u0001\u0000¢\u0006\u0002\u0010\u0015\u001a[\u0010\u0016\u001a\u0002H\u0006\"\u0004\b\u0000\u0010\u0006\"\b\b\u0001\u0010\u000b*\u0002H\u0006*\b\u0012\u0004\u0012\u0002H\u000b0\u00052!\u0010\u0011\u001a\u001d\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0002\u0012\u0004\u0012\u0002H\u00060\rH\bø\u0001\u0000\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0000¢\u0006\u0002\u0010\u0017\u001a!\u0010\u0018\u001a\u0002H\u000b\"\u0004\b\u0000\u0010\u000b*\b\u0012\u0004\u0012\u0002H\u000b0\u0005H\bø\u0001\u0000¢\u0006\u0002\u0010\u0019\u001a]\u0010\u001a\u001a\b\u0012\u0004\u0012\u0002H\u00060\u0005\"\u0004\b\u0000\u0010\u0006\"\u0004\b\u0001\u0010\u000b*\b\u0012\u0004\u0012\u0002H\u000b0\u00052!\u0010\u001b\u001a\u001d\u0012\u0013\u0012\u0011H\u000b¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u0002H\u00060\rH\bø\u0001\u0000\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0000¢\u0006\u0002\u0010\u0017\u001aP\u0010\u001c\u001a\b\u0012\u0004\u0012\u0002H\u00060\u0005\"\u0004\b\u0000\u0010\u0006\"\u0004\b\u0001\u0010\u000b*\b\u0012\u0004\u0012\u0002H\u000b0\u00052!\u0010\u001b\u001a\u001d\u0012\u0013\u0012\u0011H\u000b¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u0002H\u00060\rH\bø\u0001\u0000¢\u0006\u0002\u0010\u0017\u001aW\u0010\u0011\u001a\b\u0012\u0004\u0012\u0002H\u000b0\u0005\"\u0004\b\u0000\u0010\u000b*\b\u0012\u0004\u0012\u0002H\u000b0\u00052!\u0010\u001d\u001a\u001d\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0002\u0012\u0004\u0012\u00020\u001e0\rH\bø\u0001\u0000\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0000¢\u0006\u0002\u0010\u0017\u001aW\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u000b0\u0005\"\u0004\b\u0000\u0010\u000b*\b\u0012\u0004\u0012\u0002H\u000b0\u00052!\u0010\u001d\u001a\u001d\u0012\u0013\u0012\u0011H\u000b¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\u001e0\rH\bø\u0001\u0000\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0000¢\u0006\u0002\u0010\u0017\u001aa\u0010\u001f\u001a\b\u0012\u0004\u0012\u0002H\u00060\u0005\"\u0004\b\u0000\u0010\u0006\"\b\b\u0001\u0010\u000b*\u0002H\u0006*\b\u0012\u0004\u0012\u0002H\u000b0\u00052!\u0010\u001b\u001a\u001d\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0002\u0012\u0004\u0012\u0002H\u00060\rH\bø\u0001\u0000\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0000¢\u0006\u0002\u0010\u0017\u001aT\u0010 \u001a\b\u0012\u0004\u0012\u0002H\u00060\u0005\"\u0004\b\u0000\u0010\u0006\"\b\b\u0001\u0010\u000b*\u0002H\u0006*\b\u0012\u0004\u0012\u0002H\u000b0\u00052!\u0010\u001b\u001a\u001d\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0002\u0012\u0004\u0012\u0002H\u00060\rH\bø\u0001\u0000¢\u0006\u0002\u0010\u0017\u001a@\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00060\u0005\"\u0004\b\u0000\u0010\u000b\"\u0004\b\u0001\u0010\u0006*\u0002H\u000b2\u0017\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u0002H\u000b\u0012\u0004\u0012\u0002H\u00060\r¢\u0006\u0002\b!H\bø\u0001\u0000¢\u0006\u0002\u0010\u0017\u001a\u0018\u0010\"\u001a\u00020\u001e*\u0006\u0012\u0002\b\u00030\u0005H\u0001ø\u0001\u0000¢\u0006\u0002\u0010#\u0002\u0004\n\u0002\b\u0019¨\u0006$"}, d2 = {"createFailure", "", "exception", "", "runCatching", "Lkotlin/Result;", "R", "block", "Lkotlin/Function0;", "(Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "fold", "T", "onSuccess", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "value", "onFailure", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "getOrDefault", "defaultValue", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "getOrElse", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "getOrThrow", "(Ljava/lang/Object;)Ljava/lang/Object;", "map", "transform", "mapCatching", "action", "", "recover", "recoverCatching", "Lkotlin/ExtensionFunctionType;", "throwOnFailure", "(Ljava/lang/Object;)V", "kotlin-stdlib"}, k = 2, mv = {1, 1, 13})
/* compiled from: Result.kt */
public final class ResultKt {
    @NotNull
    @SinceKotlin(version = "1.3")
    @PublishedApi
    public static final Object createFailure(@NotNull Throwable th) {
        Intrinsics.checkParameterIsNotNull(th, "exception");
        return new Failure(th);
    }

    @SinceKotlin(version = "1.3")
    @PublishedApi
    public static final void throwOnFailure(@NotNull Object obj) {
        if (obj instanceof Failure) {
            throw ((Failure) obj).exception;
        }
    }

    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final <R> Object runCatching(Function0<? extends R> function0) {
        Companion companion;
        try {
            companion = Result.Companion;
            return Result.constructor-impl(function0.invoke());
        } catch (Function0<? extends R> function02) {
            companion = Result.Companion;
            return Result.constructor-impl(createFailure(function02));
        }
    }

    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final <T, R> Object runCatching(T t, Function1<? super T, ? extends R> function1) {
        try {
            Companion companion = Result.Companion;
            return Result.constructor-impl(function1.invoke(t));
        } catch (T t2) {
            function1 = Result.Companion;
            return Result.constructor-impl(createFailure(t2));
        }
    }

    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final <T> T getOrThrow(@NotNull Object obj) {
        throwOnFailure(obj);
        return obj;
    }

    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final <R, T extends R> R getOrElse(@NotNull Object obj, Function1<? super Throwable, ? extends R> function1) {
        Throwable exceptionOrNull-impl = Result.exceptionOrNull-impl(obj);
        return exceptionOrNull-impl == null ? obj : function1.invoke(exceptionOrNull-impl);
    }

    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final <R, T extends R> R getOrDefault(@NotNull Object obj, R r) {
        return Result.isFailure-impl(obj) ? r : obj;
    }

    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final <R, T> R fold(@NotNull Object obj, Function1<? super T, ? extends R> function1, Function1<? super Throwable, ? extends R> function12) {
        Throwable exceptionOrNull-impl = Result.exceptionOrNull-impl(obj);
        if (exceptionOrNull-impl == null) {
            return function1.invoke(obj);
        }
        return function12.invoke(exceptionOrNull-impl);
    }

    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final <R, T> Object map(@NotNull Object obj, Function1<? super T, ? extends R> function1) {
        if (!Result.isSuccess-impl(obj)) {
            return Result.constructor-impl(obj);
        }
        Companion companion = Result.Companion;
        return Result.constructor-impl(function1.invoke(obj));
    }

    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final <R, T> Object mapCatching(@NotNull Object obj, Function1<? super T, ? extends R> function1) {
        if (!Result.isSuccess-impl(obj)) {
            return Result.constructor-impl(obj);
        }
        try {
            Companion companion = Result.Companion;
            return Result.constructor-impl(function1.invoke(obj));
        } catch (Object obj2) {
            function1 = Result.Companion;
            return Result.constructor-impl(createFailure(obj2));
        }
    }

    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final <R, T extends R> Object recover(@NotNull Object obj, Function1<? super Throwable, ? extends R> function1) {
        Throwable exceptionOrNull-impl = Result.exceptionOrNull-impl(obj);
        if (exceptionOrNull-impl == null) {
            return obj;
        }
        obj = Result.Companion;
        return Result.constructor-impl(function1.invoke(exceptionOrNull-impl));
    }

    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final <R, T extends R> Object recoverCatching(@NotNull Object obj, Function1<? super Throwable, ? extends R> function1) {
        Throwable exceptionOrNull-impl = Result.exceptionOrNull-impl(obj);
        if (exceptionOrNull-impl == null) {
            return obj;
        }
        try {
            obj = Result.Companion;
            return Result.constructor-impl(function1.invoke(exceptionOrNull-impl));
        } catch (Object obj2) {
            function1 = Result.Companion;
            return Result.constructor-impl(createFailure(obj2));
        }
    }

    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final <T> Object onFailure(@NotNull Object obj, Function1<? super Throwable, Unit> function1) {
        Throwable exceptionOrNull-impl = Result.exceptionOrNull-impl(obj);
        if (exceptionOrNull-impl != null) {
            function1.invoke(exceptionOrNull-impl);
        }
        return obj;
    }

    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final <T> Object onSuccess(@NotNull Object obj, Function1<? super T, Unit> function1) {
        if (Result.isSuccess-impl(obj)) {
            function1.invoke(obj);
        }
        return obj;
    }
}
