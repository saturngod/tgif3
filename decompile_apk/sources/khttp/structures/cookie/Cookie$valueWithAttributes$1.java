package khttp.structures.cookie;

import java.util.Map.Entry;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 0}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010&\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0000\u0010\u0000\u001a\u00020\u00012\u0014\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0003H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "it", "", "", "", "invoke"}, k = 3, mv = {1, 1, 1})
/* compiled from: Cookie.kt */
final class Cookie$valueWithAttributes$1 extends Lambda implements Function1<Entry<? extends String, ? extends Object>, CharSequence> {
    public static final Cookie$valueWithAttributes$1 INSTANCE = new Cookie$valueWithAttributes$1();

    Cookie$valueWithAttributes$1() {
        super(1);
    }

    @NotNull
    public final CharSequence invoke(@NotNull Entry<String, ? extends Object> entry) {
        Intrinsics.checkParameterIsNotNull(entry, "it");
        if (entry.getValue() != null) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append((String) entry.getKey());
            stringBuilder.append("=");
            stringBuilder.append(entry.getValue());
            entry = stringBuilder.toString();
        } else {
            entry = String.valueOf(entry.getKey());
        }
        return (CharSequence) entry;
    }
}
