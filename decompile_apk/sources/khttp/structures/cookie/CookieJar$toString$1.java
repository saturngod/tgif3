package khttp.structures.cookie;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 0}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Lkhttp/structures/cookie/Cookie;", "invoke"}, k = 3, mv = {1, 1, 1})
/* compiled from: CookieJar.kt */
final class CookieJar$toString$1 extends Lambda implements Function1<Cookie, String> {
    public static final CookieJar$toString$1 INSTANCE = new CookieJar$toString$1();

    CookieJar$toString$1() {
        super(1);
    }

    @NotNull
    public final String invoke(@NotNull Cookie cookie) {
        Intrinsics.checkParameterIsNotNull(cookie, "it");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(cookie.getKey());
        stringBuilder.append("=");
        stringBuilder.append(cookie.getValue());
        return stringBuilder.toString();
    }
}
