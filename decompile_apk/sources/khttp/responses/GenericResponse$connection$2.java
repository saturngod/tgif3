package khttp.responses;

import java.net.HttpURLConnection;
import java.util.Collection;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 0}, d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "Ljava/net/HttpURLConnection;", "invoke"}, k = 3, mv = {1, 1, 1})
/* compiled from: GenericResponse.kt */
final class GenericResponse$connection$2 extends Lambda implements Function1<HttpURLConnection, Unit> {
    final /* synthetic */ GenericResponse this$0;

    GenericResponse$connection$2(GenericResponse genericResponse) {
        this.this$0 = genericResponse;
        super(1);
    }

    public final void invoke(@NotNull HttpURLConnection httpURLConnection) {
        Intrinsics.checkParameterIsNotNull(httpURLConnection, "$receiver");
        for (Function2 invoke : CollectionsKt___CollectionsKt.plus((Collection) CollectionsKt___CollectionsKt.plus((Collection) GenericResponse.Companion.getDefaultStartInitializers$khttp(), (Iterable) this.this$0.getInitializers()), (Iterable) GenericResponse.Companion.getDefaultEndInitializers$khttp())) {
            invoke.invoke(this.this$0, httpURLConnection);
            Unit unit = Unit.INSTANCE;
        }
    }
}
