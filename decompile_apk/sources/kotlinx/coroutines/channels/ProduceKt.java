package kotlinx.coroutines.channels;

import kotlin.BuilderInference;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineContextKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.ExperimentalCoroutinesApi;
import kotlinx.coroutines.InternalCoroutinesApi;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000L\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0001\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072-\b\u0002\u0010\b\u001a'\u0012\u0015\u0012\u0013\u0018\u00010\n¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u000e\u0018\u00010\tj\u0004\u0018\u0001`\u000f2/\b\u0001\u0010\u0010\u001a)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0012\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u0013\u0012\u0006\u0012\u0004\u0018\u00010\u00140\u0011¢\u0006\u0002\b\u0015H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0016\u001ae\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072/\b\u0001\u0010\u0010\u001a)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0012\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u0013\u0012\u0006\u0012\u0004\u0018\u00010\u00140\u0011¢\u0006\u0002\b\u0015H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0017\u0002\u0004\n\u0002\b\u0019¨\u0006\u0018"}, d2 = {"produce", "Lkotlinx/coroutines/channels/ReceiveChannel;", "E", "Lkotlinx/coroutines/CoroutineScope;", "context", "Lkotlin/coroutines/CoroutineContext;", "capacity", "", "onCompletion", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "cause", "", "Lkotlinx/coroutines/CompletionHandler;", "block", "Lkotlin/Function2;", "Lkotlinx/coroutines/channels/ProducerScope;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/CoroutineContext;ILkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/channels/ReceiveChannel;", "(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/CoroutineContext;ILkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/channels/ReceiveChannel;", "kotlinx-coroutines-core"}, k = 2, mv = {1, 1, 13})
/* compiled from: Produce.kt */
public final class ProduceKt {
    @NotNull
    @ExperimentalCoroutinesApi
    public static /* synthetic */ ReceiveChannel produce$default(CoroutineScope coroutineScope, CoroutineContext coroutineContext, int i, Function2 function2, int i2, Object obj) {
        if ((i2 & 1) != null) {
            coroutineContext = EmptyCoroutineContext.INSTANCE;
        }
        if ((i2 & 2) != 0) {
            i = 0;
        }
        return produce(coroutineScope, coroutineContext, i, function2);
    }

    @NotNull
    @ExperimentalCoroutinesApi
    public static final <E> ReceiveChannel<E> produce(@NotNull CoroutineScope coroutineScope, @NotNull CoroutineContext coroutineContext, int i, @NotNull @BuilderInference Function2<? super ProducerScope<? super E>, ? super Continuation<? super Unit>, ? extends Object> function2) {
        Intrinsics.checkParameterIsNotNull(coroutineScope, "receiver$0");
        Intrinsics.checkParameterIsNotNull(coroutineContext, "context");
        Intrinsics.checkParameterIsNotNull(function2, "block");
        coroutineContext = new ProducerCoroutine(CoroutineContextKt.newCoroutineContext(coroutineScope, coroutineContext), ChannelKt.Channel(i));
        coroutineContext.start(CoroutineStart.DEFAULT, coroutineContext, function2);
        return (ReceiveChannel) coroutineContext;
    }

    @NotNull
    @InternalCoroutinesApi
    public static /* synthetic */ ReceiveChannel produce$default(CoroutineScope coroutineScope, CoroutineContext coroutineContext, int i, Function1 function1, Function2 function2, int i2, Object obj) {
        if ((i2 & 1) != null) {
            coroutineContext = EmptyCoroutineContext.INSTANCE;
        }
        if ((i2 & 2) != null) {
            i = 0;
        }
        if ((i2 & 4) != 0) {
            function1 = null;
        }
        return produce(coroutineScope, coroutineContext, i, function1, function2);
    }

    @NotNull
    @InternalCoroutinesApi
    public static final <E> ReceiveChannel<E> produce(@NotNull CoroutineScope coroutineScope, @NotNull CoroutineContext coroutineContext, int i, @Nullable Function1<? super Throwable, Unit> function1, @NotNull @BuilderInference Function2<? super ProducerScope<? super E>, ? super Continuation<? super Unit>, ? extends Object> function2) {
        Intrinsics.checkParameterIsNotNull(coroutineScope, "receiver$0");
        Intrinsics.checkParameterIsNotNull(coroutineContext, "context");
        Intrinsics.checkParameterIsNotNull(function2, "block");
        coroutineContext = new ProducerCoroutine(CoroutineContextKt.newCoroutineContext(coroutineScope, coroutineContext), ChannelKt.Channel(i));
        if (function1 != null) {
            coroutineContext.invokeOnCompletion(function1);
        }
        coroutineContext.start(CoroutineStart.DEFAULT, coroutineContext, function2);
        return (ReceiveChannel) coroutineContext;
    }
}
