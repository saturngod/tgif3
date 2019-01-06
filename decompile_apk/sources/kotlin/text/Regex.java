package kotlin.text;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 ,2\u00060\u0001j\u0002`\u0002:\u0002,-B\u000f\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005B\u0017\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bB\u001d\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00070\n¢\u0006\u0002\u0010\u000bB\u000f\b\u0001\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u000eJ\u000e\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017J\u001a\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010\u001a\u001a\u00020\u001bJ\u001e\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00190\u001d2\u0006\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010\u001a\u001a\u00020\u001bJ\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u0016\u001a\u00020\u0017J\u0011\u0010\u001f\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0004J\"\u0010 \u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u00172\u0012\u0010!\u001a\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00170\"J\u0016\u0010 \u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010#\u001a\u00020\u0004J\u0016\u0010$\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010#\u001a\u00020\u0004J\u001e\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00040&2\u0006\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010'\u001a\u00020\u001bJ\u0006\u0010(\u001a\u00020\rJ\b\u0010)\u001a\u00020\u0004H\u0016J\b\u0010*\u001a\u00020+H\u0002R\u0016\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\nX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00070\n8F¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0003\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013¨\u0006."}, d2 = {"Lkotlin/text/Regex;", "Ljava/io/Serializable;", "Lkotlin/io/Serializable;", "pattern", "", "(Ljava/lang/String;)V", "option", "Lkotlin/text/RegexOption;", "(Ljava/lang/String;Lkotlin/text/RegexOption;)V", "options", "", "(Ljava/lang/String;Ljava/util/Set;)V", "nativePattern", "Ljava/util/regex/Pattern;", "(Ljava/util/regex/Pattern;)V", "_options", "getOptions", "()Ljava/util/Set;", "getPattern", "()Ljava/lang/String;", "containsMatchIn", "", "input", "", "find", "Lkotlin/text/MatchResult;", "startIndex", "", "findAll", "Lkotlin/sequences/Sequence;", "matchEntire", "matches", "replace", "transform", "Lkotlin/Function1;", "replacement", "replaceFirst", "split", "", "limit", "toPattern", "toString", "writeReplace", "", "Companion", "Serialized", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
/* compiled from: Regex.kt */
public final class Regex implements Serializable {
    public static final Companion Companion = new Companion();
    private Set<? extends RegexOption> _options;
    private final Pattern nativePattern;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0002J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007J\u000e\u0010\t\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007J\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\u0007¨\u0006\f"}, d2 = {"Lkotlin/text/Regex$Companion;", "", "()V", "ensureUnicodeCase", "", "flags", "escape", "", "literal", "escapeReplacement", "fromLiteral", "Lkotlin/text/Regex;", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
    /* compiled from: Regex.kt */
    public static final class Companion {
        private final int ensureUnicodeCase(int i) {
            return (i & 2) != 0 ? i | 64 : i;
        }

        private Companion() {
        }

        @NotNull
        public final Regex fromLiteral(@NotNull String str) {
            Intrinsics.checkParameterIsNotNull(str, "literal");
            return new Regex(str, RegexOption.LITERAL);
        }

        @NotNull
        public final String escape(@NotNull String str) {
            Intrinsics.checkParameterIsNotNull(str, "literal");
            str = Pattern.quote(str);
            Intrinsics.checkExpressionValueIsNotNull(str, "Pattern.quote(literal)");
            return str;
        }

        @NotNull
        public final String escapeReplacement(@NotNull String str) {
            Intrinsics.checkParameterIsNotNull(str, "literal");
            str = Matcher.quoteReplacement(str);
            Intrinsics.checkExpressionValueIsNotNull(str, "Matcher.quoteReplacement(literal)");
            return str;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0002\u0018\u0000 \u000e2\u00060\u0001j\u0002`\u0002:\u0001\u000eB\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\b\u0010\f\u001a\u00020\rH\u0002R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u000f"}, d2 = {"Lkotlin/text/Regex$Serialized;", "Ljava/io/Serializable;", "Lkotlin/io/Serializable;", "pattern", "", "flags", "", "(Ljava/lang/String;I)V", "getFlags", "()I", "getPattern", "()Ljava/lang/String;", "readResolve", "", "Companion", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
    /* compiled from: Regex.kt */
    private static final class Serialized implements Serializable {
        public static final Companion Companion = new Companion();
        private static final long serialVersionUID = 0;
        private final int flags;
        @NotNull
        private final String pattern;

        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lkotlin/text/Regex$Serialized$Companion;", "", "()V", "serialVersionUID", "", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
        /* compiled from: Regex.kt */
        public static final class Companion {
            private Companion() {
            }
        }

        public Serialized(@NotNull String str, int i) {
            Intrinsics.checkParameterIsNotNull(str, "pattern");
            this.pattern = str;
            this.flags = i;
        }

        public final int getFlags() {
            return this.flags;
        }

        @NotNull
        public final String getPattern() {
            return this.pattern;
        }

        private final Object readResolve() {
            Pattern compile = Pattern.compile(this.pattern, this.flags);
            Intrinsics.checkExpressionValueIsNotNull(compile, "Pattern.compile(pattern, flags)");
            return new Regex(compile);
        }
    }

    @PublishedApi
    public Regex(@NotNull Pattern pattern) {
        Intrinsics.checkParameterIsNotNull(pattern, "nativePattern");
        this.nativePattern = pattern;
    }

    public Regex(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "pattern");
        Pattern compile = Pattern.compile(str);
        Intrinsics.checkExpressionValueIsNotNull(compile, "Pattern.compile(pattern)");
        this(compile);
    }

    public Regex(@NotNull String str, @NotNull RegexOption regexOption) {
        Intrinsics.checkParameterIsNotNull(str, "pattern");
        Intrinsics.checkParameterIsNotNull(regexOption, "option");
        Pattern compile = Pattern.compile(str, Companion.ensureUnicodeCase(regexOption.getValue()));
        Intrinsics.checkExpressionValueIsNotNull(compile, "Pattern.compile(pattern,…nicodeCase(option.value))");
        this(compile);
    }

    public Regex(@NotNull String str, @NotNull Set<? extends RegexOption> set) {
        Intrinsics.checkParameterIsNotNull(str, "pattern");
        Intrinsics.checkParameterIsNotNull(set, "options");
        Pattern compile = Pattern.compile(str, Companion.ensureUnicodeCase(RegexKt.toInt(set)));
        Intrinsics.checkExpressionValueIsNotNull(compile, "Pattern.compile(pattern,…odeCase(options.toInt()))");
        this(compile);
    }

    @NotNull
    public final String getPattern() {
        String pattern = this.nativePattern.pattern();
        Intrinsics.checkExpressionValueIsNotNull(pattern, "nativePattern.pattern()");
        return pattern;
    }

    @NotNull
    public final Set<RegexOption> getOptions() {
        Set<RegexOption> set = this._options;
        if (set != null) {
            return set;
        }
        int flags = this.nativePattern.flags();
        EnumSet allOf = EnumSet.allOf(RegexOption.class);
        CollectionsKt__MutableCollectionsKt.retainAll((Iterable) allOf, (Function1) new Regex$fromInt$$inlined$apply$lambda$1(flags));
        set = Collections.unmodifiableSet(allOf);
        Intrinsics.checkExpressionValueIsNotNull(set, "Collections.unmodifiable…mask == it.value }\n    })");
        this._options = set;
        return set;
    }

    public final boolean matches(@NotNull CharSequence charSequence) {
        Intrinsics.checkParameterIsNotNull(charSequence, "input");
        return this.nativePattern.matcher(charSequence).matches();
    }

    public final boolean containsMatchIn(@NotNull CharSequence charSequence) {
        Intrinsics.checkParameterIsNotNull(charSequence, "input");
        return this.nativePattern.matcher(charSequence).find();
    }

    @Nullable
    public static /* synthetic */ MatchResult find$default(Regex regex, CharSequence charSequence, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        return regex.find(charSequence, i);
    }

    @Nullable
    public final MatchResult find(@NotNull CharSequence charSequence, int i) {
        Intrinsics.checkParameterIsNotNull(charSequence, "input");
        Matcher matcher = this.nativePattern.matcher(charSequence);
        Intrinsics.checkExpressionValueIsNotNull(matcher, "nativePattern.matcher(input)");
        return RegexKt.findNext(matcher, i, charSequence);
    }

    @NotNull
    public static /* synthetic */ Sequence findAll$default(Regex regex, CharSequence charSequence, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        return regex.findAll(charSequence, i);
    }

    @NotNull
    public final Sequence<MatchResult> findAll(@NotNull CharSequence charSequence, int i) {
        Intrinsics.checkParameterIsNotNull(charSequence, "input");
        return SequencesKt__SequencesKt.generateSequence((Function0) new Regex$findAll$1(this, charSequence, i), (Function1) Regex$findAll$2.INSTANCE);
    }

    @Nullable
    public final MatchResult matchEntire(@NotNull CharSequence charSequence) {
        Intrinsics.checkParameterIsNotNull(charSequence, "input");
        Matcher matcher = this.nativePattern.matcher(charSequence);
        Intrinsics.checkExpressionValueIsNotNull(matcher, "nativePattern.matcher(input)");
        return RegexKt.matchEntire(matcher, charSequence);
    }

    @NotNull
    public final String replace(@NotNull CharSequence charSequence, @NotNull String str) {
        Intrinsics.checkParameterIsNotNull(charSequence, "input");
        Intrinsics.checkParameterIsNotNull(str, "replacement");
        charSequence = this.nativePattern.matcher(charSequence).replaceAll(str);
        Intrinsics.checkExpressionValueIsNotNull(charSequence, "nativePattern.matcher(in…).replaceAll(replacement)");
        return charSequence;
    }

    @NotNull
    public final String replace(@NotNull CharSequence charSequence, @NotNull Function1<? super MatchResult, ? extends CharSequence> function1) {
        Intrinsics.checkParameterIsNotNull(charSequence, "input");
        Intrinsics.checkParameterIsNotNull(function1, "transform");
        int i = 0;
        MatchResult find$default = find$default(this, charSequence, 0, 2, null);
        if (find$default == null) {
            return charSequence.toString();
        }
        int length = charSequence.length();
        StringBuilder stringBuilder = new StringBuilder(length);
        do {
            if (find$default == null) {
                Intrinsics.throwNpe();
            }
            stringBuilder.append(charSequence, i, find$default.getRange().getStart().intValue());
            stringBuilder.append((CharSequence) function1.invoke(find$default));
            i = find$default.getRange().getEndInclusive().intValue() + 1;
            find$default = find$default.next();
            if (i >= length) {
                break;
            }
        } while (find$default != null);
        if (i < length) {
            stringBuilder.append(charSequence, i, length);
        }
        charSequence = stringBuilder.toString();
        Intrinsics.checkExpressionValueIsNotNull(charSequence, "sb.toString()");
        return charSequence;
    }

    @NotNull
    public final String replaceFirst(@NotNull CharSequence charSequence, @NotNull String str) {
        Intrinsics.checkParameterIsNotNull(charSequence, "input");
        Intrinsics.checkParameterIsNotNull(str, "replacement");
        charSequence = this.nativePattern.matcher(charSequence).replaceFirst(str);
        Intrinsics.checkExpressionValueIsNotNull(charSequence, "nativePattern.matcher(in…replaceFirst(replacement)");
        return charSequence;
    }

    @NotNull
    public static /* synthetic */ List split$default(Regex regex, CharSequence charSequence, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        return regex.split(charSequence, i);
    }

    @NotNull
    public final List<String> split(@NotNull CharSequence charSequence, int i) {
        Intrinsics.checkParameterIsNotNull(charSequence, "input");
        int i2 = 0;
        if ((i >= 0 ? 1 : null) != null) {
            Matcher matcher = this.nativePattern.matcher(charSequence);
            if (matcher.find()) {
                if (i != 1) {
                    int i3 = 10;
                    if (i > 0) {
                        i3 = RangesKt___RangesKt.coerceAtMost(i, 10);
                    }
                    ArrayList arrayList = new ArrayList(i3);
                    i--;
                    do {
                        arrayList.add(charSequence.subSequence(i2, matcher.start()).toString());
                        i2 = matcher.end();
                        if (i >= 0 && arrayList.size() == i) {
                            break;
                        }
                    } while (matcher.find());
                    arrayList.add(charSequence.subSequence(i2, charSequence.length()).toString());
                    return arrayList;
                }
            }
            return CollectionsKt__CollectionsJVMKt.listOf(charSequence.toString());
        }
        charSequence = new StringBuilder();
        charSequence.append("Limit must be non-negative, but was ");
        charSequence.append(i);
        charSequence.append(46);
        throw ((Throwable) new IllegalArgumentException(charSequence.toString().toString()));
    }

    @NotNull
    public String toString() {
        String pattern = this.nativePattern.toString();
        Intrinsics.checkExpressionValueIsNotNull(pattern, "nativePattern.toString()");
        return pattern;
    }

    @NotNull
    public final Pattern toPattern() {
        return this.nativePattern;
    }

    private final Object writeReplace() {
        String pattern = this.nativePattern.pattern();
        Intrinsics.checkExpressionValueIsNotNull(pattern, "nativePattern.pattern()");
        return new Serialized(pattern, this.nativePattern.flags());
    }
}
