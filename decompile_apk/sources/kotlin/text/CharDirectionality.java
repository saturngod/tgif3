package kotlin.text;

import java.util.Map;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u0019\b\u0001\u0018\u0000 \u001b2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u001bB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001a¨\u0006\u001c"}, d2 = {"Lkotlin/text/CharDirectionality;", "", "value", "", "(Ljava/lang/String;II)V", "getValue", "()I", "UNDEFINED", "LEFT_TO_RIGHT", "RIGHT_TO_LEFT", "RIGHT_TO_LEFT_ARABIC", "EUROPEAN_NUMBER", "EUROPEAN_NUMBER_SEPARATOR", "EUROPEAN_NUMBER_TERMINATOR", "ARABIC_NUMBER", "COMMON_NUMBER_SEPARATOR", "NONSPACING_MARK", "BOUNDARY_NEUTRAL", "PARAGRAPH_SEPARATOR", "SEGMENT_SEPARATOR", "WHITESPACE", "OTHER_NEUTRALS", "LEFT_TO_RIGHT_EMBEDDING", "LEFT_TO_RIGHT_OVERRIDE", "RIGHT_TO_LEFT_EMBEDDING", "RIGHT_TO_LEFT_OVERRIDE", "POP_DIRECTIONAL_FORMAT", "Companion", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
/* compiled from: CharDirectionality.kt */
public enum CharDirectionality {
    ;
    
    public static final Companion Companion = null;
    private static final Lazy directionalityMap$delegate = null;
    private final int value;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u0005R'\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00048BX\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\b¨\u0006\r"}, d2 = {"Lkotlin/text/CharDirectionality$Companion;", "", "()V", "directionalityMap", "", "", "Lkotlin/text/CharDirectionality;", "getDirectionalityMap", "()Ljava/util/Map;", "directionalityMap$delegate", "Lkotlin/Lazy;", "valueOf", "directionality", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
    /* compiled from: CharDirectionality.kt */
    public static final class Companion {
        static final /* synthetic */ KProperty[] $$delegatedProperties = null;

        static {
            $$delegatedProperties = new KProperty[]{Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(Companion.class), "directionalityMap", "getDirectionalityMap()Ljava/util/Map;"))};
        }

        private final Map<Integer, CharDirectionality> getDirectionalityMap() {
            Lazy access$getDirectionalityMap$cp = CharDirectionality.directionalityMap$delegate;
            KProperty kProperty = $$delegatedProperties[0];
            return (Map) access$getDirectionalityMap$cp.getValue();
        }

        private Companion() {
        }

        @NotNull
        public final CharDirectionality valueOf(int i) {
            CharDirectionality charDirectionality = (CharDirectionality) getDirectionalityMap().get(Integer.valueOf(i));
            if (charDirectionality != null) {
                return charDirectionality;
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Directionality #");
            stringBuilder.append(i);
            stringBuilder.append(" is not defined.");
            throw new IllegalArgumentException(stringBuilder.toString());
        }
    }

    protected CharDirectionality(int i) {
        this.value = i;
    }

    public final int getValue() {
        return this.value;
    }

    static {
        Companion = new Companion();
        directionalityMap$delegate = LazyKt__LazyJVMKt.lazy(CharDirectionality$Companion$directionalityMap$2.INSTANCE);
    }
}
