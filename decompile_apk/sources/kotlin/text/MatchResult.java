package kotlin.text;

import java.util.List;
import kotlin.Metadata;
import kotlin.internal.InlineOnly;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\bf\u0018\u00002\u00020\u0001:\u0001\u0017J\n\u0010\u0016\u001a\u0004\u0018\u00010\u0000H&R\u0014\u0010\u0002\u001a\u00020\u00038VX\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0018\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0012\u0010\u000b\u001a\u00020\fX¦\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0012\u0010\u000f\u001a\u00020\u0010X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0012\u0010\u0013\u001a\u00020\bX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015¨\u0006\u0018"}, d2 = {"Lkotlin/text/MatchResult;", "", "destructured", "Lkotlin/text/MatchResult$Destructured;", "getDestructured", "()Lkotlin/text/MatchResult$Destructured;", "groupValues", "", "", "getGroupValues", "()Ljava/util/List;", "groups", "Lkotlin/text/MatchGroupCollection;", "getGroups", "()Lkotlin/text/MatchGroupCollection;", "range", "Lkotlin/ranges/IntRange;", "getRange", "()Lkotlin/ranges/IntRange;", "value", "getValue", "()Ljava/lang/String;", "next", "Destructured", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
/* compiled from: MatchResult.kt */
public interface MatchResult {

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 13})
    /* compiled from: MatchResult.kt */
    public static final class DefaultImpls {
        @NotNull
        public static Destructured getDestructured(MatchResult matchResult) {
            return new Destructured(matchResult);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010 \n\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\bH\nJ\t\u0010\t\u001a\u00020\bH\nJ\t\u0010\n\u001a\u00020\bH\nJ\t\u0010\u000b\u001a\u00020\bH\nJ\t\u0010\f\u001a\u00020\bH\nJ\t\u0010\r\u001a\u00020\bH\nJ\t\u0010\u000e\u001a\u00020\bH\nJ\t\u0010\u000f\u001a\u00020\bH\nJ\t\u0010\u0010\u001a\u00020\bH\nJ\t\u0010\u0011\u001a\u00020\bH\nJ\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\b0\u0013R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0014"}, d2 = {"Lkotlin/text/MatchResult$Destructured;", "", "match", "Lkotlin/text/MatchResult;", "(Lkotlin/text/MatchResult;)V", "getMatch", "()Lkotlin/text/MatchResult;", "component1", "", "component10", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "toList", "", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
    /* compiled from: MatchResult.kt */
    public static final class Destructured {
        @NotNull
        private final MatchResult match;

        public Destructured(@NotNull MatchResult matchResult) {
            Intrinsics.checkParameterIsNotNull(matchResult, "match");
            this.match = matchResult;
        }

        @NotNull
        public final MatchResult getMatch() {
            return this.match;
        }

        @InlineOnly
        private final String component1() {
            return (String) getMatch().getGroupValues().get(1);
        }

        @InlineOnly
        private final String component2() {
            return (String) getMatch().getGroupValues().get(2);
        }

        @InlineOnly
        private final String component3() {
            return (String) getMatch().getGroupValues().get(3);
        }

        @InlineOnly
        private final String component4() {
            return (String) getMatch().getGroupValues().get(4);
        }

        @InlineOnly
        private final String component5() {
            return (String) getMatch().getGroupValues().get(5);
        }

        @InlineOnly
        private final String component6() {
            return (String) getMatch().getGroupValues().get(6);
        }

        @InlineOnly
        private final String component7() {
            return (String) getMatch().getGroupValues().get(7);
        }

        @InlineOnly
        private final String component8() {
            return (String) getMatch().getGroupValues().get(8);
        }

        @InlineOnly
        private final String component9() {
            return (String) getMatch().getGroupValues().get(9);
        }

        @InlineOnly
        private final String component10() {
            return (String) getMatch().getGroupValues().get(10);
        }

        @NotNull
        public final List<String> toList() {
            return this.match.getGroupValues().subList(1, this.match.getGroupValues().size());
        }
    }

    @NotNull
    Destructured getDestructured();

    @NotNull
    List<String> getGroupValues();

    @NotNull
    MatchGroupCollection getGroups();

    @NotNull
    IntRange getRange();

    @NotNull
    String getValue();

    @Nullable
    MatchResult next();
}
