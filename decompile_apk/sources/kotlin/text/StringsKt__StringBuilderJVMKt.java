package kotlin.text;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.internal.InlineOnly;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000T\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\f\n\u0002\u0010\r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0002\u0010\u0005\n\u0002\u0010\u0019\n\u0002\u0010\u0006\n\u0002\u0010\u0007\n\u0002\u0010\b\n\u0002\u0010\t\n\u0002\u0010\n\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u001a\u0012\u0010\u0000\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u0002\u001a\u001d\u0010\u0000\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\b\u001a\u001f\u0010\u0000\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0005H\b\u001a\u0012\u0010\u0000\u001a\u00060\u0006j\u0002`\u0007*\u00060\u0006j\u0002`\u0007\u001a\u001f\u0010\u0000\u001a\u00060\u0006j\u0002`\u0007*\u00060\u0006j\u0002`\u00072\b\u0010\u0003\u001a\u0004\u0018\u00010\bH\b\u001a\u001f\u0010\u0000\u001a\u00060\u0006j\u0002`\u0007*\u00060\u0006j\u0002`\u00072\b\u0010\u0003\u001a\u0004\u0018\u00010\tH\b\u001a\u001d\u0010\u0000\u001a\u00060\u0006j\u0002`\u0007*\u00060\u0006j\u0002`\u00072\u0006\u0010\u0003\u001a\u00020\nH\b\u001a\u001d\u0010\u0000\u001a\u00060\u0006j\u0002`\u0007*\u00060\u0006j\u0002`\u00072\u0006\u0010\u0003\u001a\u00020\u000bH\b\u001a\u001d\u0010\u0000\u001a\u00060\u0006j\u0002`\u0007*\u00060\u0006j\u0002`\u00072\u0006\u0010\u0003\u001a\u00020\u0004H\b\u001a\u001d\u0010\u0000\u001a\u00060\u0006j\u0002`\u0007*\u00060\u0006j\u0002`\u00072\u0006\u0010\u0003\u001a\u00020\fH\b\u001a\u001f\u0010\u0000\u001a\u00060\u0006j\u0002`\u0007*\u00060\u0006j\u0002`\u00072\b\u0010\u0003\u001a\u0004\u0018\u00010\u0005H\b\u001a\u001d\u0010\u0000\u001a\u00060\u0006j\u0002`\u0007*\u00060\u0006j\u0002`\u00072\u0006\u0010\u0003\u001a\u00020\rH\b\u001a\u001d\u0010\u0000\u001a\u00060\u0006j\u0002`\u0007*\u00060\u0006j\u0002`\u00072\u0006\u0010\u0003\u001a\u00020\u000eH\b\u001a\u001d\u0010\u0000\u001a\u00060\u0006j\u0002`\u0007*\u00060\u0006j\u0002`\u00072\u0006\u0010\u0003\u001a\u00020\u000fH\b\u001a\u001d\u0010\u0000\u001a\u00060\u0006j\u0002`\u0007*\u00060\u0006j\u0002`\u00072\u0006\u0010\u0003\u001a\u00020\u0010H\b\u001a\u001d\u0010\u0000\u001a\u00060\u0006j\u0002`\u0007*\u00060\u0006j\u0002`\u00072\u0006\u0010\u0003\u001a\u00020\u0011H\b\u001a\u001f\u0010\u0000\u001a\u00060\u0006j\u0002`\u0007*\u00060\u0006j\u0002`\u00072\b\u0010\u0003\u001a\u0004\u0018\u00010\u0012H\b\u001a%\u0010\u0000\u001a\u00060\u0006j\u0002`\u0007*\u00060\u0006j\u0002`\u00072\u000e\u0010\u0003\u001a\n\u0018\u00010\u0006j\u0004\u0018\u0001`\u0007H\b\u001a\u0014\u0010\u0013\u001a\u00060\u0006j\u0002`\u0007*\u00060\u0006j\u0002`\u0007H\u0007\u001a!\u0010\u0014\u001a\u00020\u0015*\u00060\u0006j\u0002`\u00072\u0006\u0010\u0016\u001a\u00020\u000f2\u0006\u0010\u0003\u001a\u00020\u0004H\n¨\u0006\u0017"}, d2 = {"appendln", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "value", "", "", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "Ljava/lang/StringBuffer;", "", "", "", "", "", "", "", "", "", "", "clear", "set", "", "index", "kotlin-stdlib"}, k = 5, mv = {1, 1, 13}, xi = 1, xs = "kotlin/text/StringsKt")
/* compiled from: StringBuilderJVM.kt */
class StringsKt__StringBuilderJVMKt extends StringsKt__RegexExtensionsKt {
    @InlineOnly
    private static final void set(@NotNull StringBuilder stringBuilder, int i, char c) {
        Intrinsics.checkParameterIsNotNull(stringBuilder, "receiver$0");
        stringBuilder.setCharAt(i, c);
    }

    @NotNull
    @SinceKotlin(version = "1.3")
    public static final StringBuilder clear(@NotNull StringBuilder stringBuilder) {
        Intrinsics.checkParameterIsNotNull(stringBuilder, "receiver$0");
        stringBuilder.setLength(0);
        return stringBuilder;
    }

    @NotNull
    public static final Appendable appendln(@NotNull Appendable appendable) {
        Intrinsics.checkParameterIsNotNull(appendable, "receiver$0");
        appendable = appendable.append(SystemProperties.LINE_SEPARATOR);
        Intrinsics.checkExpressionValueIsNotNull(appendable, "append(SystemProperties.LINE_SEPARATOR)");
        return appendable;
    }

    @InlineOnly
    private static final Appendable appendln(@NotNull Appendable appendable, CharSequence charSequence) {
        appendable = appendable.append(charSequence);
        Intrinsics.checkExpressionValueIsNotNull(appendable, "append(value)");
        return appendln(appendable);
    }

    @InlineOnly
    private static final Appendable appendln(@NotNull Appendable appendable, char c) {
        appendable = appendable.append(c);
        Intrinsics.checkExpressionValueIsNotNull(appendable, "append(value)");
        return appendln(appendable);
    }

    @NotNull
    public static final StringBuilder appendln(@NotNull StringBuilder stringBuilder) {
        Intrinsics.checkParameterIsNotNull(stringBuilder, "receiver$0");
        stringBuilder.append(SystemProperties.LINE_SEPARATOR);
        Intrinsics.checkExpressionValueIsNotNull(stringBuilder, "append(SystemProperties.LINE_SEPARATOR)");
        return stringBuilder;
    }

    @InlineOnly
    private static final StringBuilder appendln(@NotNull StringBuilder stringBuilder, StringBuffer stringBuffer) {
        stringBuilder.append(stringBuffer);
        Intrinsics.checkExpressionValueIsNotNull(stringBuilder, "append(value)");
        return appendln(stringBuilder);
    }

    @InlineOnly
    private static final StringBuilder appendln(@NotNull StringBuilder stringBuilder, CharSequence charSequence) {
        stringBuilder.append(charSequence);
        Intrinsics.checkExpressionValueIsNotNull(stringBuilder, "append(value)");
        return appendln(stringBuilder);
    }

    @InlineOnly
    private static final StringBuilder appendln(@NotNull StringBuilder stringBuilder, String str) {
        stringBuilder.append(str);
        Intrinsics.checkExpressionValueIsNotNull(stringBuilder, "append(value)");
        return appendln(stringBuilder);
    }

    @InlineOnly
    private static final StringBuilder appendln(@NotNull StringBuilder stringBuilder, Object obj) {
        stringBuilder.append(obj);
        Intrinsics.checkExpressionValueIsNotNull(stringBuilder, "append(value)");
        return appendln(stringBuilder);
    }

    @InlineOnly
    private static final StringBuilder appendln(@NotNull StringBuilder stringBuilder, StringBuilder stringBuilder2) {
        stringBuilder.append(stringBuilder2);
        Intrinsics.checkExpressionValueIsNotNull(stringBuilder, "append(value)");
        return appendln(stringBuilder);
    }

    @InlineOnly
    private static final StringBuilder appendln(@NotNull StringBuilder stringBuilder, char[] cArr) {
        stringBuilder.append(cArr);
        Intrinsics.checkExpressionValueIsNotNull(stringBuilder, "append(value)");
        return appendln(stringBuilder);
    }

    @InlineOnly
    private static final StringBuilder appendln(@NotNull StringBuilder stringBuilder, char c) {
        stringBuilder.append(c);
        Intrinsics.checkExpressionValueIsNotNull(stringBuilder, "append(value)");
        return appendln(stringBuilder);
    }

    @InlineOnly
    private static final StringBuilder appendln(@NotNull StringBuilder stringBuilder, boolean z) {
        stringBuilder.append(z);
        Intrinsics.checkExpressionValueIsNotNull(stringBuilder, "append(value)");
        return appendln(stringBuilder);
    }

    @InlineOnly
    private static final StringBuilder appendln(@NotNull StringBuilder stringBuilder, int i) {
        stringBuilder.append(i);
        Intrinsics.checkExpressionValueIsNotNull(stringBuilder, "append(value)");
        return appendln(stringBuilder);
    }

    @InlineOnly
    private static final StringBuilder appendln(@NotNull StringBuilder stringBuilder, short s) {
        stringBuilder.append(s);
        Intrinsics.checkExpressionValueIsNotNull(stringBuilder, "append(value.toInt())");
        return appendln(stringBuilder);
    }

    @InlineOnly
    private static final StringBuilder appendln(@NotNull StringBuilder stringBuilder, byte b) {
        stringBuilder.append(b);
        Intrinsics.checkExpressionValueIsNotNull(stringBuilder, "append(value.toInt())");
        return appendln(stringBuilder);
    }

    @InlineOnly
    private static final StringBuilder appendln(@NotNull StringBuilder stringBuilder, long j) {
        stringBuilder.append(j);
        Intrinsics.checkExpressionValueIsNotNull(stringBuilder, "append(value)");
        return appendln(stringBuilder);
    }

    @InlineOnly
    private static final StringBuilder appendln(@NotNull StringBuilder stringBuilder, float f) {
        stringBuilder.append(f);
        Intrinsics.checkExpressionValueIsNotNull(stringBuilder, "append(value)");
        return appendln(stringBuilder);
    }

    @InlineOnly
    private static final StringBuilder appendln(@NotNull StringBuilder stringBuilder, double d) {
        stringBuilder.append(d);
        Intrinsics.checkExpressionValueIsNotNull(stringBuilder, "append(value)");
        return appendln(stringBuilder);
    }
}
