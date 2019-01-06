package kotlin.coroutines.experimental;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.experimental.jvm.internal.CoroutineIntrinsics;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010(\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000e\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u00032\b\u0012\u0004\u0012\u00020\u00050\u0004B\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0016\u001a\u00020\u0017H\u0002J\t\u0010\u0018\u001a\u00020\u0019H\u0002J\u000e\u0010\u001a\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010\u001bJ\r\u0010\u001c\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010\u001bJ\u0015\u0010\u001d\u001a\u00020\u00052\u0006\u0010\u001e\u001a\u00020\u0005H\u0016¢\u0006\u0002\u0010\u001fJ\u0010\u0010 \u001a\u00020\u00052\u0006\u0010!\u001a\u00020\u0017H\u0016J\u0019\u0010\"\u001a\u00020\u00052\u0006\u0010\u001e\u001a\u00028\u0000H@ø\u0001\u0000¢\u0006\u0002\u0010#J\u001f\u0010$\u001a\u00020\u00052\f\u0010%\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003H@ø\u0001\u0000¢\u0006\u0002\u0010&R\u0014\u0010\u0007\u001a\u00020\b8VX\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0016\u0010\u000b\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0003X\u000e¢\u0006\u0002\n\u0000R\"\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u0012\u0010\u0011\u001a\u0004\u0018\u00018\u0000X\u000e¢\u0006\u0004\n\u0002\u0010\u0012R\u0012\u0010\u0013\u001a\u00060\u0014j\u0002`\u0015X\u000e¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\t¨\u0006'"}, d2 = {"Lkotlin/coroutines/experimental/SequenceBuilderIterator;", "T", "Lkotlin/coroutines/experimental/SequenceBuilder;", "", "Lkotlin/coroutines/experimental/Continuation;", "", "()V", "context", "Lkotlin/coroutines/experimental/CoroutineContext;", "getContext", "()Lkotlin/coroutines/experimental/CoroutineContext;", "nextIterator", "nextStep", "getNextStep", "()Lkotlin/coroutines/experimental/Continuation;", "setNextStep", "(Lkotlin/coroutines/experimental/Continuation;)V", "nextValue", "Ljava/lang/Object;", "state", "", "Lkotlin/coroutines/experimental/State;", "exceptionalState", "", "hasNext", "", "next", "()Ljava/lang/Object;", "nextNotReady", "resume", "value", "(Lkotlin/Unit;)V", "resumeWithException", "exception", "yield", "(Ljava/lang/Object;Lkotlin/coroutines/experimental/Continuation;)Ljava/lang/Object;", "yieldAll", "iterator", "(Ljava/util/Iterator;Lkotlin/coroutines/experimental/Continuation;)Ljava/lang/Object;", "kotlin-stdlib_coroutines"}, k = 1, mv = {1, 1, 13})
/* compiled from: SequenceBuilder.kt */
final class SequenceBuilderIterator<T> extends SequenceBuilder<T> implements Iterator<T>, Continuation<Unit>, KMappedMarker {
    private Iterator<? extends T> nextIterator;
    @Nullable
    private Continuation<? super Unit> nextStep;
    private T nextValue;
    private int state;

    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Nullable
    public final Continuation<Unit> getNextStep() {
        return this.nextStep;
    }

    public final void setNextStep(@Nullable Continuation<? super Unit> continuation) {
        this.nextStep = continuation;
    }

    public boolean hasNext() {
        while (true) {
            switch (this.state) {
                case 0:
                    break;
                case 1:
                    Iterator it = this.nextIterator;
                    if (it == null) {
                        Intrinsics.throwNpe();
                    }
                    if (!it.hasNext()) {
                        this.nextIterator = (Iterator) null;
                        break;
                    }
                    this.state = 2;
                    return true;
                case 2:
                case 3:
                    return true;
                case 4:
                    return false;
                default:
                    throw exceptionalState();
            }
            this.state = 5;
            Continuation continuation = this.nextStep;
            if (continuation == null) {
                Intrinsics.throwNpe();
            }
            this.nextStep = (Continuation) null;
            continuation.resume(Unit.INSTANCE);
        }
    }

    public T next() {
        switch (this.state) {
            case 0:
            case 1:
                return nextNotReady();
            case 2:
                this.state = 1;
                Iterator it = this.nextIterator;
                if (it == null) {
                    Intrinsics.throwNpe();
                }
                return it.next();
            case 3:
                this.state = 0;
                T t = this.nextValue;
                this.nextValue = null;
                return t;
            default:
                throw exceptionalState();
        }
    }

    private final T nextNotReady() {
        if (hasNext()) {
            return next();
        }
        throw new NoSuchElementException();
    }

    private final Throwable exceptionalState() {
        switch (this.state) {
            case 4:
                return new NoSuchElementException();
            case 5:
                return new IllegalStateException("Iterator has failed.");
            default:
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Unexpected state of the iterator: ");
                stringBuilder.append(this.state);
                return new IllegalStateException(stringBuilder.toString());
        }
    }

    @Nullable
    public Object yield(T t, @NotNull Continuation<? super Unit> continuation) {
        this.nextValue = t;
        this.state = 3;
        setNextStep(CoroutineIntrinsics.normalizeContinuation(continuation));
        return IntrinsicsKt__IntrinsicsJvmKt.getCOROUTINE_SUSPENDED();
    }

    @Nullable
    public Object yieldAll(@NotNull Iterator<? extends T> it, @NotNull Continuation<? super Unit> continuation) {
        if (!it.hasNext()) {
            return Unit.INSTANCE;
        }
        this.nextIterator = it;
        this.state = 2;
        setNextStep(CoroutineIntrinsics.normalizeContinuation(continuation));
        return IntrinsicsKt__IntrinsicsJvmKt.getCOROUTINE_SUSPENDED();
    }

    public void resume(@NotNull Unit unit) {
        Intrinsics.checkParameterIsNotNull(unit, "value");
        this.state = 4;
    }

    public void resumeWithException(@NotNull Throwable th) {
        Intrinsics.checkParameterIsNotNull(th, "exception");
        throw th;
    }

    @NotNull
    public CoroutineContext getContext() {
        return EmptyCoroutineContext.INSTANCE;
    }
}
