package org.jetbrains.anko.sdk27.coroutines;

import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import kotlin.Metadata;
import kotlin.Result.Failure;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function7;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.GlobalScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u000e\u0010\u0005\u001a\n \u0004*\u0004\u0018\u00010\u00060\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000bH\n¢\u0006\u0002\b\f"}, d2 = {"<anonymous>", "", "parent", "Landroid/widget/ExpandableListView;", "kotlin.jvm.PlatformType", "v", "Landroid/view/View;", "groupPosition", "", "childPosition", "id", "", "onChildClick"}, k = 3, mv = {1, 1, 13})
/* compiled from: ListenersWithCoroutines.kt */
final class Sdk27CoroutinesListenersWithCoroutinesKt$onChildClick$1 implements OnChildClickListener {
    final /* synthetic */ CoroutineContext $context;
    final /* synthetic */ Function7 $handler;
    final /* synthetic */ boolean $returnValue;

    Sdk27CoroutinesListenersWithCoroutinesKt$onChildClick$1(CoroutineContext coroutineContext, Function7 function7, boolean z) {
        this.$context = coroutineContext;
        this.$handler = function7;
        this.$returnValue = z;
    }

    public final boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i2, long j) {
        final Sdk27CoroutinesListenersWithCoroutinesKt$onChildClick$1 sdk27CoroutinesListenersWithCoroutinesKt$onChildClick$1 = this;
        final ExpandableListView expandableListView2 = expandableListView;
        final View view2 = view;
        final int i3 = i;
        final int i4 = i2;
        final long j2 = j;
        BuildersKt.launch(GlobalScope.INSTANCE, this.$context, CoroutineStart.DEFAULT, new Function2<CoroutineScope, Continuation<? super Unit>, Object>(null) {
            int label;
            private CoroutineScope p$;

            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                Intrinsics.checkParameterIsNotNull(continuation, "completion");
                Continuation c03561 = /* anonymous class already generated */;
                c03561.p$ = (CoroutineScope) obj;
                return c03561;
            }

            public final Object invoke(Object obj, Object obj2) {
                return ((C03561) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
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
                        Function7 function7 = sdk27CoroutinesListenersWithCoroutinesKt$onChildClick$1.$handler;
                        ExpandableListView expandableListView = expandableListView2;
                        View view = view2;
                        Integer boxInt = Boxing.boxInt(i3);
                        Integer boxInt2 = Boxing.boxInt(i4);
                        Long boxLong = Boxing.boxLong(j2);
                        this.label = 1;
                        if (function7.invoke(coroutineScope, expandableListView, view, boxInt, boxInt2, boxLong, this) == coroutine_suspended) {
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
