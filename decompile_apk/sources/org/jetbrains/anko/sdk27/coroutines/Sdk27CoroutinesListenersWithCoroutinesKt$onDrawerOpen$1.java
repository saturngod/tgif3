package org.jetbrains.anko.sdk27.coroutines;

import android.widget.SlidingDrawer.OnDrawerOpenListener;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.GlobalScope;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "onDrawerOpened"}, k = 3, mv = {1, 1, 13})
/* compiled from: ListenersWithCoroutines.kt */
final class Sdk27CoroutinesListenersWithCoroutinesKt$onDrawerOpen$1 implements OnDrawerOpenListener {
    final /* synthetic */ CoroutineContext $context;
    final /* synthetic */ Function2 $handler;

    Sdk27CoroutinesListenersWithCoroutinesKt$onDrawerOpen$1(CoroutineContext coroutineContext, Function2 function2) {
        this.$context = coroutineContext;
        this.$handler = function2;
    }

    public final void onDrawerOpened() {
        BuildersKt.launch(GlobalScope.INSTANCE, this.$context, CoroutineStart.DEFAULT, this.$handler);
    }
}
