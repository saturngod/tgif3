package org.jetbrains.anko.sdk27.coroutines;

import android.widget.CalendarView;
import android.widget.CalendarView.OnDateChangeListener;
import kotlin.Metadata;
import kotlin.Result.Failure;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function6;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.GlobalScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006H\n¢\u0006\u0002\b\t"}, d2 = {"<anonymous>", "", "view", "Landroid/widget/CalendarView;", "kotlin.jvm.PlatformType", "year", "", "month", "dayOfMonth", "onSelectedDayChange"}, k = 3, mv = {1, 1, 13})
/* compiled from: ListenersWithCoroutines.kt */
final class Sdk27CoroutinesListenersWithCoroutinesKt$onDateChange$1 implements OnDateChangeListener {
    final /* synthetic */ CoroutineContext $context;
    final /* synthetic */ Function6 $handler;

    Sdk27CoroutinesListenersWithCoroutinesKt$onDateChange$1(CoroutineContext coroutineContext, Function6 function6) {
        this.$context = coroutineContext;
        this.$handler = function6;
    }

    public final void onSelectedDayChange(CalendarView calendarView, int i, int i2, int i3) {
        final Sdk27CoroutinesListenersWithCoroutinesKt$onDateChange$1 sdk27CoroutinesListenersWithCoroutinesKt$onDateChange$1 = this;
        final CalendarView calendarView2 = calendarView;
        final int i4 = i;
        final int i5 = i2;
        final int i6 = i3;
        BuildersKt.launch(GlobalScope.INSTANCE, this.$context, CoroutineStart.DEFAULT, new Function2<CoroutineScope, Continuation<? super Unit>, Object>(null) {
            int label;
            private CoroutineScope p$;

            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                Intrinsics.checkParameterIsNotNull(continuation, "completion");
                Continuation c03621 = /* anonymous class already generated */;
                c03621.p$ = (CoroutineScope) obj;
                return c03621;
            }

            public final Object invoke(Object obj, Object obj2) {
                return ((C03621) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
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
                        Function6 function6 = sdk27CoroutinesListenersWithCoroutinesKt$onDateChange$1.$handler;
                        CalendarView calendarView = calendarView2;
                        Integer boxInt = Boxing.boxInt(i4);
                        Integer boxInt2 = Boxing.boxInt(i5);
                        Integer boxInt3 = Boxing.boxInt(i6);
                        this.label = 1;
                        if (function6.invoke(coroutineScope, calendarView, boxInt, boxInt2, boxInt3, this) == coroutine_suspended) {
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
