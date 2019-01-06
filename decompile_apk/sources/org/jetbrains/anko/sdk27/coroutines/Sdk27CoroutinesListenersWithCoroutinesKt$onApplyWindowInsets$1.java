package org.jetbrains.anko.sdk27.coroutines;

import android.view.View;
import android.view.View.OnApplyWindowInsetsListener;
import android.view.WindowInsets;
import kotlin.Metadata;
import kotlin.Result.Failure;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.GlobalScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u000e\u0010\u0005\u001a\n \u0004*\u0004\u0018\u00010\u00010\u0001H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "Landroid/view/WindowInsets;", "v", "Landroid/view/View;", "kotlin.jvm.PlatformType", "insets", "onApplyWindowInsets"}, k = 3, mv = {1, 1, 13})
/* compiled from: ListenersWithCoroutines.kt */
final class Sdk27CoroutinesListenersWithCoroutinesKt$onApplyWindowInsets$1 implements OnApplyWindowInsetsListener {
    final /* synthetic */ CoroutineContext $context;
    final /* synthetic */ Function4 $handler;
    final /* synthetic */ WindowInsets $returnValue;

    Sdk27CoroutinesListenersWithCoroutinesKt$onApplyWindowInsets$1(CoroutineContext coroutineContext, Function4 function4, WindowInsets windowInsets) {
        this.$context = coroutineContext;
        this.$handler = function4;
        this.$returnValue = windowInsets;
    }

    @NotNull
    public final WindowInsets onApplyWindowInsets(final View view, final WindowInsets windowInsets) {
        BuildersKt.launch(GlobalScope.INSTANCE, this.$context, CoroutineStart.DEFAULT, new Function2<CoroutineScope, Continuation<? super Unit>, Object>(null) {
            int label;
            private CoroutineScope p$;

            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                Intrinsics.checkParameterIsNotNull(continuation, "completion");
                Continuation c03521 = /* anonymous class already generated */;
                c03521.p$ = (CoroutineScope) obj;
                return c03521;
            }

            public final Object invoke(Object obj, Object obj2) {
                return ((C03521) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
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
                        Function4 function4 = this.$handler;
                        View view = view;
                        WindowInsets windowInsets = windowInsets;
                        this.label = 1;
                        if (function4.invoke(obj, view, windowInsets, this) == coroutine_suspended) {
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
        return this.$returnValue;
    }
}
