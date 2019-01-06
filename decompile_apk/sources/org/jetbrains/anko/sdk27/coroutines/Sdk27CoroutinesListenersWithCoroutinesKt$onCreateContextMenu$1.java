package org.jetbrains.anko.sdk27.coroutines;

import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View;
import android.view.View.OnCreateContextMenuListener;
import kotlin.Metadata;
import kotlin.Result.Failure;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.GlobalScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u000e\u0010\u0005\u001a\n \u0004*\u0004\u0018\u00010\u00060\u00062\u000e\u0010\u0007\u001a\n \u0004*\u0004\u0018\u00010\b0\bH\n¢\u0006\u0002\b\t"}, d2 = {"<anonymous>", "", "menu", "Landroid/view/ContextMenu;", "kotlin.jvm.PlatformType", "v", "Landroid/view/View;", "menuInfo", "Landroid/view/ContextMenu$ContextMenuInfo;", "onCreateContextMenu"}, k = 3, mv = {1, 1, 13})
/* compiled from: ListenersWithCoroutines.kt */
final class Sdk27CoroutinesListenersWithCoroutinesKt$onCreateContextMenu$1 implements OnCreateContextMenuListener {
    final /* synthetic */ CoroutineContext $context;
    final /* synthetic */ Function5 $handler;

    Sdk27CoroutinesListenersWithCoroutinesKt$onCreateContextMenu$1(CoroutineContext coroutineContext, Function5 function5) {
        this.$context = coroutineContext;
        this.$handler = function5;
    }

    public final void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenuInfo contextMenuInfo) {
        final Sdk27CoroutinesListenersWithCoroutinesKt$onCreateContextMenu$1 sdk27CoroutinesListenersWithCoroutinesKt$onCreateContextMenu$1 = this;
        final ContextMenu contextMenu2 = contextMenu;
        final View view2 = view;
        final ContextMenuInfo contextMenuInfo2 = contextMenuInfo;
        BuildersKt.launch(GlobalScope.INSTANCE, this.$context, CoroutineStart.DEFAULT, new Function2<CoroutineScope, Continuation<? super Unit>, Object>(null) {
            int label;
            private CoroutineScope p$;

            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                Intrinsics.checkParameterIsNotNull(continuation, "completion");
                Continuation c03611 = /* anonymous class already generated */;
                c03611.p$ = (CoroutineScope) obj;
                return c03611;
            }

            public final Object invoke(Object obj, Object obj2) {
                return ((C03611) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
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
                        Function5 function5 = sdk27CoroutinesListenersWithCoroutinesKt$onCreateContextMenu$1.$handler;
                        ContextMenu contextMenu = contextMenu2;
                        View view = view2;
                        ContextMenuInfo contextMenuInfo = contextMenuInfo2;
                        this.label = 1;
                        if (function5.invoke(coroutineScope, contextMenu, view, contextMenuInfo, this) == coroutine_suspended) {
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
