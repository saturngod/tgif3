package org.jetbrains.anko.sdk27.coroutines;

import android.widget.AbsListView;
import kotlin.Metadata;
import kotlin.Result.Failure;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function6;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 13})
@DebugMetadata(c = "org/jetbrains/anko/sdk27/coroutines/__AbsListView_OnScrollListener$onScroll$1", f = "ListenersWithCoroutines.kt", i = {}, l = {526, 528}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: ListenersWithCoroutines.kt */
final class __AbsListView_OnScrollListener$onScroll$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ int $firstVisibleItem;
    final /* synthetic */ Function6 $handler;
    final /* synthetic */ int $totalItemCount;
    final /* synthetic */ AbsListView $view;
    final /* synthetic */ int $visibleItemCount;
    int label;
    private CoroutineScope p$;

    __AbsListView_OnScrollListener$onScroll$1(Function6 function6, AbsListView absListView, int i, int i2, int i3, Continuation continuation) {
        this.$handler = function6;
        this.$view = absListView;
        this.$firstVisibleItem = i;
        this.$visibleItemCount = i2;
        this.$totalItemCount = i3;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        Continuation __abslistview_onscrolllistener_onscroll_1 = new __AbsListView_OnScrollListener$onScroll$1(this.$handler, this.$view, this.$firstVisibleItem, this.$visibleItemCount, this.$totalItemCount, continuation);
        __abslistview_onscrolllistener_onscroll_1.p$ = (CoroutineScope) obj;
        return __abslistview_onscrolllistener_onscroll_1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((__AbsListView_OnScrollListener$onScroll$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                if (obj instanceof Failure) {
                    throw ((Failure) obj).exception;
                }
                CoroutineScope coroutineScope = this.p$;
                Function6 function6 = this.$handler;
                AbsListView absListView = this.$view;
                Integer boxInt = Boxing.boxInt(this.$firstVisibleItem);
                Integer boxInt2 = Boxing.boxInt(this.$visibleItemCount);
                Integer boxInt3 = Boxing.boxInt(this.$totalItemCount);
                this.label = 1;
                if (function6.invoke(coroutineScope, absListView, boxInt, boxInt2, boxInt3, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                break;
            case 1:
                if (!(obj instanceof Failure)) {
                    break;
                }
                throw ((Failure) obj).exception;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
