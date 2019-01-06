package kotlin.text;

import java.util.Map;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\f\n\u0002\b \b\u0001\u0018\u0000 -2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001-B\u0017\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0011\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nj\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001bj\u0002\b\u001cj\u0002\b\u001dj\u0002\b\u001ej\u0002\b\u001fj\u0002\b j\u0002\b!j\u0002\b\"j\u0002\b#j\u0002\b$j\u0002\b%j\u0002\b&j\u0002\b'j\u0002\b(j\u0002\b)j\u0002\b*j\u0002\b+j\u0002\b,¨\u0006."}, d2 = {"Lkotlin/text/CharCategory;", "", "value", "", "code", "", "(Ljava/lang/String;IILjava/lang/String;)V", "getCode", "()Ljava/lang/String;", "getValue", "()I", "contains", "", "char", "", "UNASSIGNED", "UPPERCASE_LETTER", "LOWERCASE_LETTER", "TITLECASE_LETTER", "MODIFIER_LETTER", "OTHER_LETTER", "NON_SPACING_MARK", "ENCLOSING_MARK", "COMBINING_SPACING_MARK", "DECIMAL_DIGIT_NUMBER", "LETTER_NUMBER", "OTHER_NUMBER", "SPACE_SEPARATOR", "LINE_SEPARATOR", "PARAGRAPH_SEPARATOR", "CONTROL", "FORMAT", "PRIVATE_USE", "SURROGATE", "DASH_PUNCTUATION", "START_PUNCTUATION", "END_PUNCTUATION", "CONNECTOR_PUNCTUATION", "OTHER_PUNCTUATION", "MATH_SYMBOL", "CURRENCY_SYMBOL", "MODIFIER_SYMBOL", "OTHER_SYMBOL", "INITIAL_QUOTE_PUNCTUATION", "FINAL_QUOTE_PUNCTUATION", "Companion", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
/* compiled from: CharCategory.kt */
public enum CharCategory {
    ;
    
    public static final Companion Companion = null;
    private static final Lazy categoryMap$delegate = null;
    @NotNull
    private final String code;
    private final int value;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u0005R'\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00048BX\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\b¨\u0006\r"}, d2 = {"Lkotlin/text/CharCategory$Companion;", "", "()V", "categoryMap", "", "", "Lkotlin/text/CharCategory;", "getCategoryMap", "()Ljava/util/Map;", "categoryMap$delegate", "Lkotlin/Lazy;", "valueOf", "category", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
    /* compiled from: CharCategory.kt */
    public static final class Companion {
        static final /* synthetic */ KProperty[] $$delegatedProperties = null;

        static {
            $$delegatedProperties = new KProperty[]{Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(Companion.class), "categoryMap", "getCategoryMap()Ljava/util/Map;"))};
        }

        private final Map<Integer, CharCategory> getCategoryMap() {
            Lazy access$getCategoryMap$cp = CharCategory.categoryMap$delegate;
            KProperty kProperty = $$delegatedProperties[0];
            return (Map) access$getCategoryMap$cp.getValue();
        }

        private Companion() {
        }

        @NotNull
        public final CharCategory valueOf(int i) {
            CharCategory charCategory = (CharCategory) getCategoryMap().get(Integer.valueOf(i));
            if (charCategory != null) {
                return charCategory;
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Category #");
            stringBuilder.append(i);
            stringBuilder.append(" is not defined.");
            throw new IllegalArgumentException(stringBuilder.toString());
        }
    }

    protected CharCategory(int i, @NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "code");
        this.value = i;
        this.code = str;
    }

    @NotNull
    public final String getCode() {
        return this.code;
    }

    public final int getValue() {
        return this.value;
    }

    static {
        Companion = new Companion();
        categoryMap$delegate = LazyKt__LazyJVMKt.lazy(CharCategory$Companion$categoryMap$2.INSTANCE);
    }

    public final boolean contains(char c) {
        return Character.getType(c) == this.value;
    }
}
