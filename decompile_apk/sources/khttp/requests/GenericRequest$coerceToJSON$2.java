package khttp.requests;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.json.JSONWriter;

@Metadata(bv = {1, 0, 0}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001c\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "jsonWriter", "Lorg/json/JSONWriter;", "iterable", "", "invoke"}, k = 3, mv = {1, 1, 1})
/* compiled from: GenericRequest.kt */
final class GenericRequest$coerceToJSON$2 extends Lambda implements Function2<JSONWriter, Iterable<?>, Unit> {
    final /* synthetic */ Object $any;

    GenericRequest$coerceToJSON$2(Object obj) {
        this.$any = obj;
        super(2);
    }

    public final void invoke(@NotNull JSONWriter jSONWriter, @NotNull Iterable<?> iterable) {
        Intrinsics.checkParameterIsNotNull(jSONWriter, "jsonWriter");
        Intrinsics.checkParameterIsNotNull(iterable, "iterable");
        jSONWriter.array();
        for (Object value : this.$any) {
            jSONWriter.value(value);
        }
        jSONWriter.endArray();
    }
}
