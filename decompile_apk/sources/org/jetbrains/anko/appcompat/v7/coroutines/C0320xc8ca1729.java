package org.jetbrains.anko.appcompat.v7.coroutines;

import android.graphics.Rect;
import android.support.v7.widget.FitWindowsViewGroup.OnFitSystemWindowsListener;
import kotlin.Metadata;
import kotlin.Result.Failure;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.GlobalScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "insets", "Landroid/graphics/Rect;", "kotlin.jvm.PlatformType", "onFitSystemWindows"}, k = 3, mv = {1, 1, 13})
/* compiled from: ListenersWithCoroutines.kt */
/* renamed from: org.jetbrains.anko.appcompat.v7.coroutines.AppcompatV7CoroutinesListenersWithCoroutinesKt$onFitSystemWindows$1 */
final class C0320xc8ca1729 implements OnFitSystemWindowsListener {
    final /* synthetic */ CoroutineContext $context;
    final /* synthetic */ Function3 $handler;

    C0320xc8ca1729(CoroutineContext coroutineContext, Function3 function3) {
        this.$context = coroutineContext;
        this.$handler = function3;
    }

    public final void onFitSystemWindows(final Rect rect) {
        BuildersKt.launch(GlobalScope.INSTANCE, this.$context, CoroutineStart.DEFAULT, new Function2<CoroutineScope, Continuation<? super Unit>, Object>(null) {
            int label;
            private CoroutineScope p$;

            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                Intrinsics.checkParameterIsNotNull(continuation, "completion");
                Continuation c03461 = /* anonymous class already generated */;
                c03461.p$ = (CoroutineScope) obj;
                return c03461;
            }

            public final Object invoke(Object obj, Object obj2) {
                return ((C03461) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
            }

            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (this.label) {
                    case 0:
                        if (obj instanceof Failure) {
                            throw ((Failure) obj).exception;
                        }
                        obj = this.p$;
                        Function3 function3 = this.$handler;
                        Rect rect = rect;
                        this.label = 1;
                        if (function3.invoke(obj, rect, this) == coroutine_suspended) {
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
        });
    }
}
