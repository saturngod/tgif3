package kotlin.text;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.Unit;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000B\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\r\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0007\u001a.\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u001b\u0010\u0004\u001a\u0017\u0012\b\u0012\u00060\u0006j\u0002`\u0007\u0012\u0004\u0012\u00020\b0\u0005¢\u0006\u0002\b\tH\b\u001a&\u0010\u0000\u001a\u00020\u00012\u001b\u0010\u0004\u001a\u0017\u0012\b\u0012\u00060\u0006j\u0002`\u0007\u0012\u0004\u0012\u00020\b0\u0005¢\u0006\u0002\b\tH\b\u001a5\u0010\n\u001a\u0002H\u000b\"\f\b\u0000\u0010\u000b*\u00060\fj\u0002`\r*\u0002H\u000b2\u0016\u0010\u000e\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00100\u000f\"\u0004\u0018\u00010\u0010¢\u0006\u0002\u0010\u0011\u001a/\u0010\n\u001a\u00060\u0006j\u0002`\u0007*\u00060\u0006j\u0002`\u00072\u0016\u0010\u000e\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00120\u000f\"\u0004\u0018\u00010\u0012¢\u0006\u0002\u0010\u0013\u001a/\u0010\n\u001a\u00060\u0006j\u0002`\u0007*\u00060\u0006j\u0002`\u00072\u0016\u0010\u000e\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u000f\"\u0004\u0018\u00010\u0001¢\u0006\u0002\u0010\u0014\u001a9\u0010\u0015\u001a\u00020\b\"\u0004\b\u0000\u0010\u000b*\u00060\fj\u0002`\r2\u0006\u0010\u0016\u001a\u0002H\u000b2\u0014\u0010\u0017\u001a\u0010\u0012\u0004\u0012\u0002H\u000b\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u0005H\u0000¢\u0006\u0002\u0010\u0018¨\u0006\u0019"}, d2 = {"buildString", "", "capacity", "", "builderAction", "Lkotlin/Function1;", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "", "Lkotlin/ExtensionFunctionType;", "append", "T", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "value", "", "", "(Ljava/lang/Appendable;[Ljava/lang/CharSequence;)Ljava/lang/Appendable;", "", "(Ljava/lang/StringBuilder;[Ljava/lang/Object;)Ljava/lang/StringBuilder;", "(Ljava/lang/StringBuilder;[Ljava/lang/String;)Ljava/lang/StringBuilder;", "appendElement", "element", "transform", "(Ljava/lang/Appendable;Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)V", "kotlin-stdlib"}, k = 5, mv = {1, 1, 13}, xi = 1, xs = "kotlin/text/StringsKt")
/* compiled from: StringBuilder.kt */
class StringsKt__StringBuilderKt extends StringsKt__StringBuilderJVMKt {
    @InlineOnly
    private static final String buildString(Function1<? super StringBuilder, Unit> function1) {
        StringBuilder stringBuilder = new StringBuilder();
        function1.invoke(stringBuilder);
        function1 = stringBuilder.toString();
        Intrinsics.checkExpressionValueIsNotNull(function1, "StringBuilder().apply(builderAction).toString()");
        return function1;
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final String buildString(int i, Function1<? super StringBuilder, Unit> function1) {
        StringBuilder stringBuilder = new StringBuilder(i);
        function1.invoke(stringBuilder);
        i = stringBuilder.toString();
        Intrinsics.checkExpressionValueIsNotNull(i, "StringBuilder(capacity).…builderAction).toString()");
        return i;
    }

    @NotNull
    public static final <T extends Appendable> T append(@NotNull T t, @NotNull CharSequence... charSequenceArr) {
        Intrinsics.checkParameterIsNotNull(t, "receiver$0");
        Intrinsics.checkParameterIsNotNull(charSequenceArr, "value");
        for (CharSequence append : charSequenceArr) {
            t.append(append);
        }
        return t;
    }

    @NotNull
    public static final StringBuilder append(@NotNull StringBuilder stringBuilder, @NotNull String... strArr) {
        Intrinsics.checkParameterIsNotNull(stringBuilder, "receiver$0");
        Intrinsics.checkParameterIsNotNull(strArr, "value");
        for (String append : strArr) {
            stringBuilder.append(append);
        }
        return stringBuilder;
    }

    @NotNull
    public static final StringBuilder append(@NotNull StringBuilder stringBuilder, @NotNull Object... objArr) {
        Intrinsics.checkParameterIsNotNull(stringBuilder, "receiver$0");
        Intrinsics.checkParameterIsNotNull(objArr, "value");
        for (Object append : objArr) {
            stringBuilder.append(append);
        }
        return stringBuilder;
    }

    public static final <T> void appendElement(@NotNull Appendable appendable, T t, @Nullable Function1<? super T, ? extends CharSequence> function1) {
        Intrinsics.checkParameterIsNotNull(appendable, "receiver$0");
        if (function1 != null) {
            appendable.append((CharSequence) function1.invoke(t));
            return;
        }
        if ((t != null ? t instanceof CharSequence : true) != null) {
            appendable.append((CharSequence) t);
        } else if ((t instanceof Character) != null) {
            appendable.append(((Character) t).charValue());
        } else {
            appendable.append(String.valueOf(t));
        }
    }
}
