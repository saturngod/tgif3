package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.coroutines.AbstractCoroutineContextElement;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\b\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\b\u0010\u000f\u001a\u00020\u0003H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, d2 = {"Lkotlinx/coroutines/CoroutineName;", "Lkotlin/coroutines/AbstractCoroutineContextElement;", "name", "", "(Ljava/lang/String;)V", "getName", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "Key", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
/* compiled from: CoroutineName.kt */
public final class CoroutineName extends AbstractCoroutineContextElement {
    public static final Key Key = new Key();
    @NotNull
    private final String name;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lkotlinx/coroutines/CoroutineName$Key;", "Lkotlin/coroutines/CoroutineContext$Key;", "Lkotlinx/coroutines/CoroutineName;", "()V", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
    /* compiled from: CoroutineName.kt */
    public static final class Key implements kotlin.coroutines.CoroutineContext.Key<CoroutineName> {
        private Key() {
        }
    }

    @NotNull
    public static /* synthetic */ CoroutineName copy$default(CoroutineName coroutineName, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = coroutineName.name;
        }
        return coroutineName.copy(str);
    }

    @NotNull
    public final String component1() {
        return this.name;
    }

    @NotNull
    public final CoroutineName copy(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "name");
        return new CoroutineName(str);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof CoroutineName) {
                if (Intrinsics.areEqual(this.name, ((CoroutineName) obj).name)) {
                }
            }
            return false;
        }
        return true;
    }

    public int hashCode() {
        String str = this.name;
        return str != null ? str.hashCode() : 0;
    }

    @NotNull
    public final String getName() {
        return this.name;
    }

    public CoroutineName(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "name");
        super(Key);
        this.name = str;
    }

    @NotNull
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("CoroutineName(");
        stringBuilder.append(this.name);
        stringBuilder.append(')');
        return stringBuilder.toString();
    }
}
