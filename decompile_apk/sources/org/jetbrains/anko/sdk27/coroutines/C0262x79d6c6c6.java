package org.jetbrains.anko.sdk27.coroutines;

import android.view.View.OnSystemUiVisibilityChangeListener;
import kotlin.Metadata;
import kotlin.Result.Failure;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.GlobalScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "visibility", "", "onSystemUiVisibilityChange"}, k = 3, mv = {1, 1, 13})
/* compiled from: ListenersWithCoroutines.kt */
/* renamed from: org.jetbrains.anko.sdk27.coroutines.Sdk27CoroutinesListenersWithCoroutinesKt$onSystemUiVisibilityChange$1 */
final class C0262x79d6c6c6 implements OnSystemUiVisibilityChangeListener {
    final /* synthetic */ CoroutineContext $context;
    final /* synthetic */ Function3 $handler;

    C0262x79d6c6c6(CoroutineContext coroutineContext, Function3 function3) {
        this.$context = coroutineContext;
        this.$handler = function3;
    }

    public final void onSystemUiVisibilityChange(final int i) {
        BuildersKt.launch(GlobalScope.INSTANCE, this.$context, CoroutineStart.DEFAULT, new Function2<CoroutineScope, Continuation<? super Unit>, Object>(null) {
            int label;
            private CoroutineScope p$;

            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                Intrinsics.checkParameterIsNotNull(continuation, "completion");
                Continuation c03891 = /* anonymous class already generated */;
                c03891.p$ = (CoroutineScope) obj;
                return c03891;
            }

            public final Object invoke(Object obj, Object obj2) {
                return ((C03891) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
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
                        Integer boxInt = Boxing.boxInt(i);
                        this.label = 1;
                        if (function3.invoke(obj, boxInt, this) == coroutine_suspended) {
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
