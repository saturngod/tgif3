package kotlin.text;

import java.util.Iterator;
import java.util.regex.MatchResult;
import kotlin.Metadata;
import kotlin.collections.AbstractCollection;
import kotlin.internal.PlatformImplementations;
import kotlin.internal.PlatformImplementationsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000-\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010(\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u00012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0002J\u0013\u0010\b\u001a\u0004\u0018\u00010\u00032\u0006\u0010\t\u001a\u00020\u0005H\u0002J\u0013\u0010\b\u001a\u0004\u0018\u00010\u00032\u0006\u0010\n\u001a\u00020\u000bH\u0002J\b\u0010\f\u001a\u00020\rH\u0016J\u0011\u0010\u000e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u000fH\u0002R\u0014\u0010\u0004\u001a\u00020\u00058VX\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0010"}, d2 = {"kotlin/text/MatcherMatchResult$groups$1", "Lkotlin/text/MatchNamedGroupCollection;", "Lkotlin/collections/AbstractCollection;", "Lkotlin/text/MatchGroup;", "size", "", "getSize", "()I", "get", "index", "name", "", "isEmpty", "", "iterator", "", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
/* compiled from: Regex.kt */
public final class MatcherMatchResult$groups$1 extends AbstractCollection<MatchGroup> implements MatchNamedGroupCollection {
    final /* synthetic */ MatcherMatchResult this$0;

    public boolean isEmpty() {
        return false;
    }

    MatcherMatchResult$groups$1(MatcherMatchResult matcherMatchResult) {
        this.this$0 = matcherMatchResult;
    }

    public final /* bridge */ boolean contains(Object obj) {
        return obj != null ? obj instanceof MatchGroup : true ? contains((MatchGroup) obj) : null;
    }

    public /* bridge */ boolean contains(MatchGroup matchGroup) {
        return super.contains(matchGroup);
    }

    public int getSize() {
        return this.this$0.matchResult.groupCount() + 1;
    }

    @NotNull
    public Iterator<MatchGroup> iterator() {
        return SequencesKt___SequencesKt.map(CollectionsKt___CollectionsKt.asSequence(CollectionsKt__CollectionsKt.getIndices(this)), new MatcherMatchResult$groups$1$iterator$1(this)).iterator();
    }

    @Nullable
    public MatchGroup get(int i) {
        MatchResult access$getMatchResult$p = this.this$0.matchResult;
        Intrinsics.checkExpressionValueIsNotNull(access$getMatchResult$p, "matchResult");
        IntRange access$range = RegexKt.range(access$getMatchResult$p, i);
        if (access$range.getStart().intValue() < 0) {
            return null;
        }
        i = this.this$0.matchResult.group(i);
        Intrinsics.checkExpressionValueIsNotNull(i, "matchResult.group(index)");
        return new MatchGroup(i, access$range);
    }

    @Nullable
    public MatchGroup get(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "name");
        PlatformImplementations platformImplementations = PlatformImplementationsKt.IMPLEMENTATIONS;
        MatchResult access$getMatchResult$p = this.this$0.matchResult;
        Intrinsics.checkExpressionValueIsNotNull(access$getMatchResult$p, "matchResult");
        return platformImplementations.getMatchResultNamedGroup(access$getMatchResult$p, str);
    }
}
