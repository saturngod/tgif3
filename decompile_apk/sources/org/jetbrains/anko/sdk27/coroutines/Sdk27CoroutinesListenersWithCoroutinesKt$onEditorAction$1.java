package org.jetbrains.anko.sdk27.coroutines;

import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
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

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u000e\u0010\u0007\u001a\n \u0004*\u0004\u0018\u00010\b0\bH\n¢\u0006\u0002\b\t"}, d2 = {"<anonymous>", "", "v", "Landroid/widget/TextView;", "kotlin.jvm.PlatformType", "actionId", "", "event", "Landroid/view/KeyEvent;", "onEditorAction"}, k = 3, mv = {1, 1, 13})
/* compiled from: ListenersWithCoroutines.kt */
final class Sdk27CoroutinesListenersWithCoroutinesKt$onEditorAction$1 implements OnEditorActionListener {
    final /* synthetic */ CoroutineContext $context;
    final /* synthetic */ Function5 $handler;
    final /* synthetic */ boolean $returnValue;

    Sdk27CoroutinesListenersWithCoroutinesKt$onEditorAction$1(CoroutineContext coroutineContext, Function5 function5, boolean z) {
        this.$context = coroutineContext;
        this.$handler = function5;
        this.$returnValue = z;
    }

    public final boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        final Sdk27CoroutinesListenersWithCoroutinesKt$onEditorAction$1 sdk27CoroutinesListenersWithCoroutinesKt$onEditorAction$1 = this;
        final TextView textView2 = textView;
        final int i2 = i;
        final KeyEvent keyEvent2 = keyEvent;
        BuildersKt.launch(GlobalScope.INSTANCE, this.$context, CoroutineStart.DEFAULT, new Function2<CoroutineScope, Continuation<? super Unit>, Object>(null) {
            int label;
            private CoroutineScope p$;

            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                Intrinsics.checkParameterIsNotNull(continuation, "completion");
                Continuation c03651 = /* anonymous class already generated */;
                c03651.p$ = (CoroutineScope) obj;
                return c03651;
            }

            public final Object invoke(Object obj, Object obj2) {
                return ((C03651) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
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
                        Function5 function5 = sdk27CoroutinesListenersWithCoroutinesKt$onEditorAction$1.$handler;
                        TextView textView = textView2;
                        Integer boxInt = Boxing.boxInt(i2);
                        KeyEvent keyEvent = keyEvent2;
                        this.label = 1;
                        if (function5.invoke(coroutineScope, textView, boxInt, keyEvent, this) == coroutine_suspended) {
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
