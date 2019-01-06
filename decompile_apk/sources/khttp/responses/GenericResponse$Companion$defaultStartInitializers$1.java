package khttp.responses;

import java.net.HttpURLConnection;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 0}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "response", "Lkhttp/responses/GenericResponse;", "connection", "Ljava/net/HttpURLConnection;", "invoke"}, k = 3, mv = {1, 1, 1})
/* compiled from: GenericResponse.kt */
final class GenericResponse$Companion$defaultStartInitializers$1 extends Lambda implements Function2<GenericResponse, HttpURLConnection, Unit> {
    public static final GenericResponse$Companion$defaultStartInitializers$1 INSTANCE = new GenericResponse$Companion$defaultStartInitializers$1();

    GenericResponse$Companion$defaultStartInitializers$1() {
        super(2);
    }

    public final void invoke(@NotNull GenericResponse genericResponse, @NotNull HttpURLConnection httpURLConnection) {
        Intrinsics.checkParameterIsNotNull(genericResponse, "response");
        Intrinsics.checkParameterIsNotNull(httpURLConnection, "connection");
        GenericResponse.Companion.forceMethod$khttp(httpURLConnection, genericResponse.getRequest().getMethod());
    }
}
