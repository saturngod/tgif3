package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.coroutines.AbstractCoroutineContextElement;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.CoroutineContext.Element;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.ThreadContextElement.DefaultImpls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\b\u0018\u0000 \u00182\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003:\u0001\u0018B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\t\u001a\u00020\u0005HÆ\u0003J\u0013\u0010\n\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eHÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\u0018\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0002H\u0016J\b\u0010\u0016\u001a\u00020\u0002H\u0016J\u0010\u0010\u0017\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u0014H\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0019"}, d2 = {"Lkotlinx/coroutines/CoroutineId;", "Lkotlinx/coroutines/ThreadContextElement;", "", "Lkotlin/coroutines/AbstractCoroutineContextElement;", "id", "", "(J)V", "getId", "()J", "component1", "copy", "equals", "", "other", "", "hashCode", "", "restoreThreadContext", "", "context", "Lkotlin/coroutines/CoroutineContext;", "oldState", "toString", "updateThreadContext", "Key", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
/* compiled from: CoroutineContext.kt */
public final class CoroutineId extends AbstractCoroutineContextElement implements ThreadContextElement<String> {
    public static final Key Key = new Key();
    private final long id;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lkotlinx/coroutines/CoroutineId$Key;", "Lkotlin/coroutines/CoroutineContext$Key;", "Lkotlinx/coroutines/CoroutineId;", "()V", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
    /* compiled from: CoroutineContext.kt */
    public static final class Key implements kotlin.coroutines.CoroutineContext.Key<CoroutineId> {
        private Key() {
        }
    }

    @NotNull
    public static /* synthetic */ CoroutineId copy$default(CoroutineId coroutineId, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            j = coroutineId.id;
        }
        return coroutineId.copy(j);
    }

    public final long component1() {
        return this.id;
    }

    @NotNull
    public final CoroutineId copy(long j) {
        return new CoroutineId(j);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof CoroutineId) {
                if ((this.id == ((CoroutineId) obj).id ? 1 : null) != null) {
                }
            }
            return false;
        }
        return true;
    }

    public int hashCode() {
        long j = this.id;
        return (int) (j ^ (j >>> 32));
    }

    public <R> R fold(R r, @NotNull Function2<? super R, ? super Element, ? extends R> function2) {
        Intrinsics.checkParameterIsNotNull(function2, "operation");
        return DefaultImpls.fold(this, r, function2);
    }

    @Nullable
    public <E extends Element> E get(@NotNull kotlin.coroutines.CoroutineContext.Key<E> key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        return DefaultImpls.get(this, key);
    }

    @NotNull
    public CoroutineContext minusKey(@NotNull kotlin.coroutines.CoroutineContext.Key<?> key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        return DefaultImpls.minusKey(this, key);
    }

    @NotNull
    public CoroutineContext plus(@NotNull CoroutineContext coroutineContext) {
        Intrinsics.checkParameterIsNotNull(coroutineContext, "context");
        return DefaultImpls.plus(this, coroutineContext);
    }

    public final long getId() {
        return this.id;
    }

    public CoroutineId(long j) {
        super(Key);
        this.id = j;
    }

    @NotNull
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("CoroutineId(");
        stringBuilder.append(this.id);
        stringBuilder.append(')');
        return stringBuilder.toString();
    }

    @NotNull
    public String updateThreadContext(@NotNull CoroutineContext coroutineContext) {
        Thread currentThread;
        String name;
        int lastIndexOf$default;
        StringBuilder stringBuilder;
        String substring;
        Intrinsics.checkParameterIsNotNull(coroutineContext, "context");
        CoroutineName coroutineName = (CoroutineName) coroutineContext.get(CoroutineName.Key);
        if (coroutineName != null) {
            coroutineContext = coroutineName.getName();
            if (coroutineContext != null) {
                currentThread = Thread.currentThread();
                Intrinsics.checkExpressionValueIsNotNull(currentThread, "currentThread");
                name = currentThread.getName();
                Intrinsics.checkExpressionValueIsNotNull(name, "oldName");
                lastIndexOf$default = StringsKt__StringsKt.lastIndexOf$default((CharSequence) name, " @", 0, false, 6, null);
                if (lastIndexOf$default < 0) {
                    lastIndexOf$default = name.length();
                }
                stringBuilder = new StringBuilder((coroutineContext.length() + lastIndexOf$default) + 10);
                substring = name.substring(0, lastIndexOf$default);
                Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                stringBuilder.append(substring);
                stringBuilder.append(" @");
                stringBuilder.append(coroutineContext);
                stringBuilder.append('#');
                stringBuilder.append(this.id);
                coroutineContext = stringBuilder.toString();
                Intrinsics.checkExpressionValueIsNotNull(coroutineContext, "StringBuilder(capacity).…builderAction).toString()");
                currentThread.setName(coroutineContext);
                return name;
            }
        }
        coroutineContext = "coroutine";
        currentThread = Thread.currentThread();
        Intrinsics.checkExpressionValueIsNotNull(currentThread, "currentThread");
        name = currentThread.getName();
        Intrinsics.checkExpressionValueIsNotNull(name, "oldName");
        lastIndexOf$default = StringsKt__StringsKt.lastIndexOf$default((CharSequence) name, " @", 0, false, 6, null);
        if (lastIndexOf$default < 0) {
            lastIndexOf$default = name.length();
        }
        stringBuilder = new StringBuilder((coroutineContext.length() + lastIndexOf$default) + 10);
        substring = name.substring(0, lastIndexOf$default);
        Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        stringBuilder.append(substring);
        stringBuilder.append(" @");
        stringBuilder.append(coroutineContext);
        stringBuilder.append('#');
        stringBuilder.append(this.id);
        coroutineContext = stringBuilder.toString();
        Intrinsics.checkExpressionValueIsNotNull(coroutineContext, "StringBuilder(capacity).…builderAction).toString()");
        currentThread.setName(coroutineContext);
        return name;
    }

    public void restoreThreadContext(@NotNull CoroutineContext coroutineContext, @NotNull String str) {
        Intrinsics.checkParameterIsNotNull(coroutineContext, "context");
        Intrinsics.checkParameterIsNotNull(str, "oldState");
        coroutineContext = Thread.currentThread();
        Intrinsics.checkExpressionValueIsNotNull(coroutineContext, "Thread.currentThread()");
        coroutineContext.setName(str);
    }
}
