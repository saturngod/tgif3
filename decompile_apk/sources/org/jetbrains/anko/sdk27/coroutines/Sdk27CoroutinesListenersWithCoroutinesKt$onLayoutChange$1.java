package org.jetbrains.anko.sdk27.coroutines;

import android.view.View;
import android.view.View.OnLayoutChangeListener;
import kotlin.Metadata;
import kotlin.Result.Failure;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.functions.Function11;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.GlobalScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u0006H\n¢\u0006\u0002\b\u000e"}, d2 = {"<anonymous>", "", "v", "Landroid/view/View;", "kotlin.jvm.PlatformType", "left", "", "top", "right", "bottom", "oldLeft", "oldTop", "oldRight", "oldBottom", "onLayoutChange"}, k = 3, mv = {1, 1, 13})
/* compiled from: ListenersWithCoroutines.kt */
final class Sdk27CoroutinesListenersWithCoroutinesKt$onLayoutChange$1 implements OnLayoutChangeListener {
    final /* synthetic */ CoroutineContext $context;
    final /* synthetic */ Function11 $handler;

    Sdk27CoroutinesListenersWithCoroutinesKt$onLayoutChange$1(CoroutineContext coroutineContext, Function11 function11) {
        this.$context = coroutineContext;
        this.$handler = function11;
    }

    public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        final Sdk27CoroutinesListenersWithCoroutinesKt$onLayoutChange$1 sdk27CoroutinesListenersWithCoroutinesKt$onLayoutChange$1 = this;
        final View view2 = view;
        final int i9 = i;
        final int i10 = i2;
        final int i11 = i3;
        final int i12 = i4;
        final int i13 = i5;
        final int i14 = i6;
        final int i15 = i7;
        final int i16 = i8;
        BuildersKt.launch(GlobalScope.INSTANCE, this.$context, CoroutineStart.DEFAULT, new Function2<CoroutineScope, Continuation<? super Unit>, Object>(null) {
            int label;
            private CoroutineScope p$;

            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                Intrinsics.checkParameterIsNotNull(continuation, "completion");
                Continuation c03791 = /* anonymous class already generated */;
                c03791.p$ = (CoroutineScope) obj;
                return c03791;
            }

            public final Object invoke(Object obj, Object obj2) {
                return ((C03791) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
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
                        Function11 function11 = sdk27CoroutinesListenersWithCoroutinesKt$onLayoutChange$1.$handler;
                        View view = view2;
                        Integer boxInt = Boxing.boxInt(i9);
                        Integer boxInt2 = Boxing.boxInt(i10);
                        Integer boxInt3 = Boxing.boxInt(i11);
                        Integer boxInt4 = Boxing.boxInt(i12);
                        Integer boxInt5 = Boxing.boxInt(i13);
                        Integer boxInt6 = Boxing.boxInt(i14);
                        Integer boxInt7 = Boxing.boxInt(i15);
                        Integer boxInt8 = Boxing.boxInt(i16);
                        this.label = 1;
                        if (function11.invoke(coroutineScope, view, boxInt, boxInt2, boxInt3, boxInt4, boxInt5, boxInt6, boxInt7, boxInt8, this) == coroutine_suspended) {
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
