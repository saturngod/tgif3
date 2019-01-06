package khttp.structures.authorization;

import kotlin.Metadata;
import kotlin.Pair;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 0}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001R\u001e\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lkhttp/structures/authorization/Authorization;", "", "header", "Lkotlin/Pair;", "", "getHeader", "()Lkotlin/Pair;", "khttp"}, k = 1, mv = {1, 1, 1})
/* compiled from: Authorization.kt */
public interface Authorization {
    @NotNull
    Pair<String, String> getHeader();
}
