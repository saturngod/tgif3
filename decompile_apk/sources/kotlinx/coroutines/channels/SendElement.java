package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.Result.Companion;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u001d\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\u0010\bJ\u0010\u0010\u000b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0004H\u0016J\u0014\u0010\r\u001a\u00020\u00072\n\u0010\u000e\u001a\u0006\u0012\u0002\b\u00030\u000fH\u0016J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\u0014\u0010\u0012\u001a\u0004\u0018\u00010\u00042\b\u0010\u0013\u001a\u0004\u0018\u00010\u0004H\u0016R\u0016\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00068\u0006X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, d2 = {"Lkotlinx/coroutines/channels/SendElement;", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "Lkotlinx/coroutines/channels/Send;", "pollResult", "", "cont", "Lkotlinx/coroutines/CancellableContinuation;", "", "(Ljava/lang/Object;Lkotlinx/coroutines/CancellableContinuation;)V", "getPollResult", "()Ljava/lang/Object;", "completeResumeSend", "token", "resumeSendClosed", "closed", "Lkotlinx/coroutines/channels/Closed;", "toString", "", "tryResumeSend", "idempotent", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
/* compiled from: AbstractChannel.kt */
public final class SendElement extends LockFreeLinkedListNode implements Send {
    @NotNull
    @JvmField
    public final CancellableContinuation<Unit> cont;
    @Nullable
    private final Object pollResult;

    @Nullable
    public Object getPollResult() {
        return this.pollResult;
    }

    public SendElement(@Nullable Object obj, @NotNull CancellableContinuation<? super Unit> cancellableContinuation) {
        Intrinsics.checkParameterIsNotNull(cancellableContinuation, "cont");
        this.pollResult = obj;
        this.cont = cancellableContinuation;
    }

    @Nullable
    public Object tryResumeSend(@Nullable Object obj) {
        return this.cont.tryResume(Unit.INSTANCE, obj);
    }

    public void completeResumeSend(@NotNull Object obj) {
        Intrinsics.checkParameterIsNotNull(obj, "token");
        this.cont.completeResume(obj);
    }

    public void resumeSendClosed(@NotNull Closed<?> closed) {
        Intrinsics.checkParameterIsNotNull(closed, "closed");
        Continuation continuation = this.cont;
        closed = closed.getSendException();
        Companion companion = Result.Companion;
        continuation.resumeWith(Result.constructor-impl(ResultKt.createFailure(closed)));
    }

    @NotNull
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SendElement(");
        stringBuilder.append(getPollResult());
        stringBuilder.append(")[");
        stringBuilder.append(this.cont);
        stringBuilder.append(']');
        return stringBuilder.toString();
    }
}
