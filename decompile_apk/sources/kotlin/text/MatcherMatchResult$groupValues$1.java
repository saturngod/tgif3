package kotlin.text;

import kotlin.Metadata;
import kotlin.collections.AbstractList;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0011\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0004H\u0002R\u0014\u0010\u0003\u001a\u00020\u00048VX\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"kotlin/text/MatcherMatchResult$groupValues$1", "Lkotlin/collections/AbstractList;", "", "size", "", "getSize", "()I", "get", "index", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
/* compiled from: Regex.kt */
public final class MatcherMatchResult$groupValues$1 extends AbstractList<String> {
    final /* synthetic */ MatcherMatchResult this$0;

    MatcherMatchResult$groupValues$1(MatcherMatchResult matcherMatchResult) {
        this.this$0 = matcherMatchResult;
    }

    public final /* bridge */ boolean contains(Object obj) {
        return obj instanceof String ? contains((String) obj) : null;
    }

    public /* bridge */ boolean contains(String str) {
        return super.contains(str);
    }

    public final /* bridge */ int indexOf(Object obj) {
        return obj instanceof String ? indexOf((String) obj) : -1;
    }

    public /* bridge */ int indexOf(String str) {
        return super.indexOf(str);
    }

    public final /* bridge */ int lastIndexOf(Object obj) {
        return obj instanceof String ? lastIndexOf((String) obj) : -1;
    }

    public /* bridge */ int lastIndexOf(String str) {
        return super.lastIndexOf(str);
    }

    public int getSize() {
        return this.this$0.matchResult.groupCount() + 1;
    }

    @NotNull
    public String get(int i) {
        i = this.this$0.matchResult.group(i);
        return i != 0 ? i : "";
    }
}
