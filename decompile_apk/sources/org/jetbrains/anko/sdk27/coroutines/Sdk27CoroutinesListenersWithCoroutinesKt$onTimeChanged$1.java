package org.jetbrains.anko.sdk27.coroutines;

import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;
import kotlin.Metadata;
import kotlin.Result.Failure;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.GlobalScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\n¢\u0006\u0002\b\b"}, d2 = {"<anonymous>", "", "view", "Landroid/widget/TimePicker;", "kotlin.jvm.PlatformType", "hourOfDay", "", "minute", "onTimeChanged"}, k = 3, mv = {1, 1, 13})
/* compiled from: ListenersWithCoroutines.kt */
final class Sdk27CoroutinesListenersWithCoroutinesKt$onTimeChanged$1 implements OnTimeChangedListener {
    final /* synthetic */ CoroutineContext $context;
    final /* synthetic */ Function5 $handler;

    Sdk27CoroutinesListenersWithCoroutinesKt$onTimeChanged$1(CoroutineContext coroutineContext, Function5 function5) {
        this.$context = coroutineContext;
        this.$handler = function5;
    }

    public final void onTimeChanged(TimePicker timePicker, int i, int i2) {
        final Sdk27CoroutinesListenersWithCoroutinesKt$onTimeChanged$1 sdk27CoroutinesListenersWithCoroutinesKt$onTimeChanged$1 = this;
        final TimePicker timePicker2 = timePicker;
        final int i3 = i;
        final int i4 = i2;
        BuildersKt.launch(GlobalScope.INSTANCE, this.$context, CoroutineStart.DEFAULT, new Function2<CoroutineScope, Continuation<? super Unit>, Object>(null) {
            int label;
            private CoroutineScope p$;

            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                Intrinsics.checkParameterIsNotNull(continuation, "completion");
                Continuation c03911 = /* anonymous class already generated */;
                c03911.p$ = (CoroutineScope) obj;
                return c03911;
            }

            public final Object invoke(Object obj, Object obj2) {
                return ((C03911) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
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
                        Function5 function5 = sdk27CoroutinesListenersWithCoroutinesKt$onTimeChanged$1.$handler;
                        TimePicker timePicker = timePicker2;
                        Integer boxInt = Boxing.boxInt(i3);
                        Integer boxInt2 = Boxing.boxInt(i4);
                        this.label = 1;
                        if (function5.invoke(coroutineScope, timePicker, boxInt, boxInt2, this) == coroutine_suspended) {
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
