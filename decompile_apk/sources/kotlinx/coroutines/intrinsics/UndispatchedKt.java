package kotlinx.coroutines.intrinsics;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.Result.Companion;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlinx.coroutines.AbstractCoroutine;
import kotlinx.coroutines.CompletedExceptionally;
import kotlinx.coroutines.internal.ThreadContextKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a-\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00042\u000e\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006H\b\u001a>\u0010\b\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00070\t2\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0004H\u0000ø\u0001\u0000¢\u0006\u0002\u0010\n\u001aR\u0010\b\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u000b\"\u0004\b\u0001\u0010\u0002*\u001e\b\u0001\u0012\u0004\u0012\u0002H\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00070\f2\u0006\u0010\r\u001a\u0002H\u000b2\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0004H\u0000ø\u0001\u0000¢\u0006\u0002\u0010\u000e\u001a>\u0010\u000f\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00070\t2\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0004H\u0000ø\u0001\u0000¢\u0006\u0002\u0010\n\u001aR\u0010\u000f\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u000b\"\u0004\b\u0001\u0010\u0002*\u001e\b\u0001\u0012\u0004\u0012\u0002H\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00070\f2\u0006\u0010\r\u001a\u0002H\u000b2\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0004H\u0000ø\u0001\u0000¢\u0006\u0002\u0010\u000e\u001aY\u0010\u0010\u001a\u0004\u0018\u00010\u0007\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u000b*\b\u0012\u0004\u0012\u0002H\u00020\u00112\u0006\u0010\r\u001a\u0002H\u000b2'\u0010\u0005\u001a#\b\u0001\u0012\u0004\u0012\u0002H\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00070\f¢\u0006\u0002\b\u0012H\u0000ø\u0001\u0000¢\u0006\u0002\u0010\u0013\u001a+\u0010\u0014\u001a\u0004\u0018\u00010\u0007\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00112\u000e\u0010\u0015\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006H\b\u0002\u0004\n\u0002\b\u0019¨\u0006\u0016"}, d2 = {"startDirect", "", "T", "completion", "Lkotlin/coroutines/Continuation;", "block", "Lkotlin/Function0;", "", "startCoroutineUndispatched", "Lkotlin/Function1;", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)V", "R", "Lkotlin/Function2;", "receiver", "(Lkotlin/jvm/functions/Function2;Ljava/lang/Object;Lkotlin/coroutines/Continuation;)V", "startCoroutineUnintercepted", "startUndispatchedOrReturn", "Lkotlinx/coroutines/AbstractCoroutine;", "Lkotlin/ExtensionFunctionType;", "(Lkotlinx/coroutines/AbstractCoroutine;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "undispatchedResult", "startBlock", "kotlinx-coroutines-core"}, k = 2, mv = {1, 1, 13})
/* compiled from: Undispatched.kt */
public final class UndispatchedKt {
    public static final <T> void startCoroutineUnintercepted(@NotNull Function1<? super Continuation<? super T>, ? extends Object> function1, @NotNull Continuation<? super T> continuation) {
        Intrinsics.checkParameterIsNotNull(function1, "receiver$0");
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        Companion companion;
        try {
            function1 = ((Function1) TypeIntrinsics.beforeCheckcastToFunctionOfArity(function1, 1)).invoke(continuation);
            if (function1 != IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                companion = Result.Companion;
                continuation.resumeWith(Result.constructor-impl(function1));
            }
        } catch (Function1<? super Continuation<? super T>, ? extends Object> function12) {
            companion = Result.Companion;
            continuation.resumeWith(Result.constructor-impl(ResultKt.createFailure(function12)));
        }
    }

    public static final <R, T> void startCoroutineUnintercepted(@NotNull Function2<? super R, ? super Continuation<? super T>, ? extends Object> function2, R r, @NotNull Continuation<? super T> continuation) {
        Intrinsics.checkParameterIsNotNull(function2, "receiver$0");
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        try {
            R invoke = ((Function2) TypeIntrinsics.beforeCheckcastToFunctionOfArity(function2, 2)).invoke(r, continuation);
            if (invoke != IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                r = Result.Companion;
                continuation.resumeWith(Result.constructor-impl(invoke));
            }
        } catch (Function2<? super R, ? super Continuation<? super T>, ? extends Object> function22) {
            r = Result.Companion;
            continuation.resumeWith(Result.constructor-impl(ResultKt.createFailure(function22)));
        }
    }

    public static final <T> void startCoroutineUndispatched(@NotNull Function1<? super Continuation<? super T>, ? extends Object> function1, @NotNull Continuation<? super T> continuation) {
        Intrinsics.checkParameterIsNotNull(function1, "receiver$0");
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        CoroutineContext context;
        Object updateThreadContext;
        Companion companion;
        try {
            context = continuation.getContext();
            updateThreadContext = ThreadContextKt.updateThreadContext(context, null);
            function1 = ((Function1) TypeIntrinsics.beforeCheckcastToFunctionOfArity(function1, 1)).invoke(continuation);
            ThreadContextKt.restoreThreadContext(context, updateThreadContext);
            if (function1 != IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                companion = Result.Companion;
                continuation.resumeWith(Result.constructor-impl(function1));
            }
        } catch (Function1<? super Continuation<? super T>, ? extends Object> function12) {
            companion = Result.Companion;
            continuation.resumeWith(Result.constructor-impl(ResultKt.createFailure(function12)));
        }
    }

    public static final <R, T> void startCoroutineUndispatched(@NotNull Function2<? super R, ? super Continuation<? super T>, ? extends Object> function2, R r, @NotNull Continuation<? super T> continuation) {
        Intrinsics.checkParameterIsNotNull(function2, "receiver$0");
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        CoroutineContext context;
        Object updateThreadContext;
        try {
            context = continuation.getContext();
            updateThreadContext = ThreadContextKt.updateThreadContext(context, null);
            R invoke = ((Function2) TypeIntrinsics.beforeCheckcastToFunctionOfArity(function2, 2)).invoke(r, continuation);
            ThreadContextKt.restoreThreadContext(context, updateThreadContext);
            if (invoke != IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                r = Result.Companion;
                continuation.resumeWith(Result.constructor-impl(invoke));
            }
        } catch (Function2<? super R, ? super Continuation<? super T>, ? extends Object> function22) {
            r = Result.Companion;
            continuation.resumeWith(Result.constructor-impl(ResultKt.createFailure(function22)));
        }
    }

    private static final <T> void startDirect(Continuation<? super T> continuation, Function0<? extends Object> function0) {
        Companion companion;
        try {
            function0 = function0.invoke();
            if (function0 != IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                companion = Result.Companion;
                continuation.resumeWith(Result.constructor-impl(function0));
            }
        } catch (Function0<? extends Object> function02) {
            companion = Result.Companion;
            continuation.resumeWith(Result.constructor-impl(ResultKt.createFailure(function02)));
        }
    }

    @Nullable
    public static final <T, R> Object startUndispatchedOrReturn(@NotNull AbstractCoroutine<? super T> abstractCoroutine, R r, @NotNull Function2<? super R, ? super Continuation<? super T>, ? extends Object> function2) {
        Intrinsics.checkParameterIsNotNull(abstractCoroutine, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function2, "block");
        abstractCoroutine.initParentJob$kotlinx_coroutines_core();
        try {
            r = ((Function2) TypeIntrinsics.beforeCheckcastToFunctionOfArity(function2, 2)).invoke(r, abstractCoroutine);
        } catch (R r2) {
            r2 = new CompletedExceptionally(r2);
        }
        if (r2 == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            return IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        }
        if (abstractCoroutine.makeCompletingOnce$kotlinx_coroutines_core(r2, 4) == null) {
            return IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        }
        if ((r2 instanceof CompletedExceptionally) == null) {
            return r2;
        }
        throw ((CompletedExceptionally) r2).cause;
    }

    private static final <T> Object undispatchedResult(@NotNull AbstractCoroutine<? super T> abstractCoroutine, Function0<? extends Object> function0) {
        try {
            function0 = function0.invoke();
        } catch (Function0<? extends Object> function02) {
            function02 = new CompletedExceptionally(function02);
        }
        if (function02 == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            return IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        }
        if (abstractCoroutine.makeCompletingOnce$kotlinx_coroutines_core(function02, 4) == null) {
            return IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        }
        if ((function02 instanceof CompletedExceptionally) == null) {
            return function02;
        }
        throw ((CompletedExceptionally) function02).cause;
    }
}
