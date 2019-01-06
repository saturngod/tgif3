package com.comquas.tgif3_demo;

import java.util.Map;
import khttp.KHttp;
import khttp.responses.Response;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref.ObjectRef;
import org.jetbrains.anko.AnkoAsyncContext;
import org.jetbrains.anko.AsyncKt;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "Lorg/jetbrains/anko/AnkoAsyncContext;", "Lcom/comquas/tgif3_demo/MainActivity;", "invoke"}, k = 3, mv = {1, 1, 11})
/* compiled from: MainActivity.kt */
final class MainActivity$sendDataRequest$1 extends Lambda implements Function1<AnkoAsyncContext<MainActivity>, Unit> {
    final /* synthetic */ ObjectRef $jsonVal;
    final /* synthetic */ MainActivity this$0;

    MainActivity$sendDataRequest$1(MainActivity mainActivity, ObjectRef objectRef) {
        this.this$0 = mainActivity;
        this.$jsonVal = objectRef;
        super(1);
    }

    public final void invoke(@NotNull AnkoAsyncContext<MainActivity> ankoAsyncContext) {
        final MainActivity$sendDataRequest$1 mainActivity$sendDataRequest$1 = this;
        AnkoAsyncContext<MainActivity> ankoAsyncContext2 = ankoAsyncContext;
        Intrinsics.checkParameterIsNotNull(ankoAsyncContext2, "$receiver");
        final Response post$default = KHttp.post$default("http://10.0.2.2:8000/tgif/mim", null, null, null, (Map) mainActivity$sendDataRequest$1.$jsonVal.element, null, null, 0.0d, null, false, null, 2030, null);
        AsyncKt.uiThread(ankoAsyncContext2, new Function1<MainActivity, Unit>() {
            public final void invoke(@NotNull MainActivity mainActivity) {
                Intrinsics.checkParameterIsNotNull(mainActivity, "it");
                mainActivity = mainActivity$sendDataRequest$1.this$0.getResponseView();
                if (mainActivity != null) {
                    mainActivity.setText(post$default.getText());
                }
            }
        });
    }
}
