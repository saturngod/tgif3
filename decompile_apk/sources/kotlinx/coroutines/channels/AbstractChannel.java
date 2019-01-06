package kotlinx.coroutines.channels;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.Result.Companion;
import kotlin.ResultKt;
import kotlin.TypeCastException;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.ContinuationKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancelHandler;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuation.DefaultImpls;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.internal.LockFreeLinkedListHead;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;
import kotlinx.coroutines.internal.LockFreeLinkedListNode.AddLastDesc;
import kotlinx.coroutines.internal.LockFreeLinkedListNode.CondAddOp;
import kotlinx.coroutines.internal.LockFreeLinkedListNode.RemoveFirstDesc;
import kotlinx.coroutines.intrinsics.UndispatchedKt;
import kotlinx.coroutines.selects.SelectClause1;
import kotlinx.coroutines.selects.SelectInstance;
import kotlinx.coroutines.selects.SelectKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\b \u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003:\b<=>?@ABCB\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\u0012\u0010\u0013\u001a\u00020\u00062\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016J\b\u0010\u0017\u001a\u00020\u0014H\u0014J\u000e\u0010\u0018\u001a\b\u0012\u0004\u0012\u00028\u00000\u0019H\u0004J\u0016\u0010\u001a\u001a\u00020\u00062\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00028\u00000\u001cH\u0002J\u000f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00028\u00000\u001eH\u0002J\b\u0010\u001f\u001a\u00020\u0014H\u0014J\b\u0010 \u001a\u00020\u0014H\u0014J\r\u0010!\u001a\u0004\u0018\u00018\u0000¢\u0006\u0002\u0010\"J\n\u0010#\u001a\u0004\u0018\u00010$H\u0014J\u0016\u0010%\u001a\u0004\u0018\u00010$2\n\u0010&\u001a\u0006\u0012\u0002\b\u00030'H\u0014J\u0011\u0010\u001b\u001a\u00028\u0000H@ø\u0001\u0000¢\u0006\u0002\u0010(J\u0013\u0010)\u001a\u0004\u0018\u00018\u0000H@ø\u0001\u0000¢\u0006\u0002\u0010(J\u0019\u0010*\u001a\u0004\u0018\u00018\u00002\b\u0010+\u001a\u0004\u0018\u00010$H\u0002¢\u0006\u0002\u0010,J\u0013\u0010-\u001a\u0004\u0018\u00018\u0000H@ø\u0001\u0000¢\u0006\u0002\u0010(J\u0017\u0010.\u001a\u00028\u00002\b\u0010+\u001a\u0004\u0018\u00010$H\u0002¢\u0006\u0002\u0010,J\u0011\u0010/\u001a\u00028\u0000H@ø\u0001\u0000¢\u0006\u0002\u0010(JH\u00100\u001a\u00020\u0014\"\u0004\b\u0001\u001012\f\u0010&\u001a\b\u0012\u0004\u0012\u0002H10'2\"\u00102\u001a\u001e\b\u0001\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u0002H104\u0012\u0006\u0012\u0004\u0018\u00010$03H\u0002ø\u0001\u0000¢\u0006\u0002\u00105JJ\u00106\u001a\u00020\u0014\"\u0004\b\u0001\u001012\f\u0010&\u001a\b\u0012\u0004\u0012\u0002H10'2$\u00102\u001a \b\u0001\u0012\u0006\u0012\u0004\u0018\u00018\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u0002H104\u0012\u0006\u0012\u0004\u0018\u00010$03H\u0002ø\u0001\u0000¢\u0006\u0002\u00105J \u00107\u001a\u00020\u00142\n\u00108\u001a\u0006\u0012\u0002\b\u0003092\n\u0010\u001b\u001a\u0006\u0012\u0002\b\u00030\u001cH\u0002J\u0010\u0010:\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010;H\u0014R\u0014\u0010\u0005\u001a\u00020\u00068DX\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0012\u0010\t\u001a\u00020\u0006X¤\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\bR\u0012\u0010\n\u001a\u00020\u0006X¤\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\bR\u0011\u0010\u000b\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\bR\u0011\u0010\f\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\f\u0010\bR\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00000\u000e8F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0019\u0010\u0011\u001a\n\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u000e8F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0010\u0002\u0004\n\u0002\b\u0019¨\u0006D"}, d2 = {"Lkotlinx/coroutines/channels/AbstractChannel;", "E", "Lkotlinx/coroutines/channels/AbstractSendChannel;", "Lkotlinx/coroutines/channels/Channel;", "()V", "hasReceiveOrClosed", "", "getHasReceiveOrClosed", "()Z", "isBufferAlwaysEmpty", "isBufferEmpty", "isClosedForReceive", "isEmpty", "onReceive", "Lkotlinx/coroutines/selects/SelectClause1;", "getOnReceive", "()Lkotlinx/coroutines/selects/SelectClause1;", "onReceiveOrNull", "getOnReceiveOrNull", "cancel", "", "cause", "", "cleanupSendQueueOnCancel", "describeTryPoll", "Lkotlinx/coroutines/channels/AbstractChannel$TryPollDesc;", "enqueueReceive", "receive", "Lkotlinx/coroutines/channels/Receive;", "iterator", "Lkotlinx/coroutines/channels/ChannelIterator;", "onReceiveDequeued", "onReceiveEnqueued", "poll", "()Ljava/lang/Object;", "pollInternal", "", "pollSelectInternal", "select", "Lkotlinx/coroutines/selects/SelectInstance;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "receiveOrNull", "receiveOrNullResult", "result", "(Ljava/lang/Object;)Ljava/lang/Object;", "receiveOrNullSuspend", "receiveResult", "receiveSuspend", "registerSelectReceive", "R", "block", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "(Lkotlinx/coroutines/selects/SelectInstance;Lkotlin/jvm/functions/Function2;)V", "registerSelectReceiveOrNull", "removeReceiveOnCancel", "cont", "Lkotlinx/coroutines/CancellableContinuation;", "takeFirstReceiveOrPeekClosed", "Lkotlinx/coroutines/channels/ReceiveOrClosed;", "IdempotentTokenValue", "Itr", "ReceiveElement", "ReceiveHasNext", "ReceiveSelect", "RemoveReceiveOnCancel", "TryEnqueueReceiveDesc", "TryPollDesc", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
/* compiled from: AbstractChannel.kt */
public abstract class AbstractChannel<E> extends AbstractSendChannel<E> implements Channel<E> {

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0005\b\u0002\u0018\u0000*\u0006\b\u0001\u0010\u0001 \u00012\u00020\u0002B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00028\u0001¢\u0006\u0002\u0010\u0005R\u0010\u0010\u0003\u001a\u00020\u00028\u0006X\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0004\u001a\u00028\u00018\u0006X\u0004¢\u0006\u0004\n\u0002\u0010\u0006\u0002\u0004\n\u0002\b\u0019¨\u0006\u0007"}, d2 = {"Lkotlinx/coroutines/channels/AbstractChannel$IdempotentTokenValue;", "E", "", "token", "value", "(Ljava/lang/Object;Ljava/lang/Object;)V", "Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
    /* compiled from: AbstractChannel.kt */
    private static final class IdempotentTokenValue<E> {
        @NotNull
        @JvmField
        public final Object token;
        @JvmField
        public final E value;

        public IdempotentTokenValue(@NotNull Object obj, E e) {
            Intrinsics.checkParameterIsNotNull(obj, "token");
            this.token = obj;
            this.value = e;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0002\u0018\u0000*\u0004\b\u0001\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0013\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00010\u0004¢\u0006\u0002\u0010\u0005J\u0011\u0010\u000e\u001a\u00020\u000fHBø\u0001\u0000¢\u0006\u0002\u0010\u0010J\u0012\u0010\u0011\u001a\u00020\u000f2\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0002J\u0011\u0010\u0012\u001a\u00020\u000fH@ø\u0001\u0000¢\u0006\u0002\u0010\u0010J\u0011\u0010\u0013\u001a\u00028\u0001HBø\u0001\u0000¢\u0006\u0002\u0010\u0010R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001c\u0010\b\u001a\u0004\u0018\u00010\tX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r\u0002\u0004\n\u0002\b\u0019¨\u0006\u0014"}, d2 = {"Lkotlinx/coroutines/channels/AbstractChannel$Itr;", "E", "Lkotlinx/coroutines/channels/ChannelIterator;", "channel", "Lkotlinx/coroutines/channels/AbstractChannel;", "(Lkotlinx/coroutines/channels/AbstractChannel;)V", "getChannel", "()Lkotlinx/coroutines/channels/AbstractChannel;", "result", "", "getResult", "()Ljava/lang/Object;", "setResult", "(Ljava/lang/Object;)V", "hasNext", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "hasNextResult", "hasNextSuspend", "next", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
    /* compiled from: AbstractChannel.kt */
    private static final class Itr<E> implements ChannelIterator<E> {
        @NotNull
        private final AbstractChannel<E> channel;
        @Nullable
        private Object result = AbstractChannelKt.POLL_FAILED;

        public Itr(@NotNull AbstractChannel<E> abstractChannel) {
            Intrinsics.checkParameterIsNotNull(abstractChannel, "channel");
            this.channel = abstractChannel;
        }

        @NotNull
        public final AbstractChannel<E> getChannel() {
            return this.channel;
        }

        @Nullable
        public final Object getResult() {
            return this.result;
        }

        public final void setResult(@Nullable Object obj) {
            this.result = obj;
        }

        @Nullable
        public Object hasNext(@NotNull Continuation<? super Boolean> continuation) {
            if (this.result != AbstractChannelKt.POLL_FAILED) {
                return Boxing.boxBoolean(hasNextResult(this.result));
            }
            this.result = this.channel.pollInternal();
            if (this.result != AbstractChannelKt.POLL_FAILED) {
                return Boxing.boxBoolean(hasNextResult(this.result));
            }
            return hasNextSuspend(continuation);
        }

        private final boolean hasNextResult(Object obj) {
            if (!(obj instanceof Closed)) {
                return true;
            }
            Closed closed = (Closed) obj;
            if (closed.closeCause == null) {
                return null;
            }
            throw closed.getReceiveException();
        }

        @Nullable
        public Object next(@NotNull Continuation<? super E> continuation) {
            Object obj = this.result;
            if (obj instanceof Closed) {
                throw ((Closed) obj).getReceiveException();
            } else if (obj == AbstractChannelKt.POLL_FAILED) {
                return this.channel.receive(continuation);
            } else {
                this.result = AbstractChannelKt.POLL_FAILED;
                return obj;
            }
        }

        @Nullable
        final /* synthetic */ Object hasNextSuspend(@NotNull Continuation<? super Boolean> continuation) {
            Continuation continuation2;
            Boolean boxBoolean;
            Companion companion;
            CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt__IntrinsicsJvmKt.intercepted(continuation), 0);
            CancellableContinuation cancellableContinuation = cancellableContinuationImpl;
            ReceiveHasNext receiveHasNext = new ReceiveHasNext(this, cancellableContinuation);
            Object pollInternal;
            do {
                Receive receive = receiveHasNext;
                if (getChannel().enqueueReceive(receive)) {
                    cancellableContinuation.initCancellability();
                    getChannel().removeReceiveOnCancel(cancellableContinuation, receive);
                    break;
                }
                pollInternal = getChannel().pollInternal();
                setResult(pollInternal);
                if (pollInternal instanceof Closed) {
                    Closed closed = (Closed) pollInternal;
                    if (closed.closeCause == null) {
                        continuation2 = cancellableContinuation;
                        boxBoolean = Boxing.boxBoolean(false);
                        companion = Result.Companion;
                        continuation2.resumeWith(Result.constructor-impl(boxBoolean));
                    } else {
                        continuation2 = cancellableContinuation;
                        Throwable receiveException = closed.getReceiveException();
                        companion = Result.Companion;
                        continuation2.resumeWith(Result.constructor-impl(ResultKt.createFailure(receiveException)));
                    }
                }
            } while (pollInternal == AbstractChannelKt.POLL_FAILED);
            continuation2 = cancellableContinuation;
            boxBoolean = Boxing.boxBoolean(true);
            companion = Result.Companion;
            continuation2.resumeWith(Result.constructor-impl(boxBoolean));
            Object result = cancellableContinuationImpl.getResult();
            if (result == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                DebugProbesKt.probeCoroutineSuspended(continuation);
            }
            return result;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0002\u0018\u0000*\u0006\b\u0001\u0010\u0001 \u00002\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u001d\u0012\u000e\u0010\u0003\u001a\n\u0012\u0006\u0012\u0004\u0018\u00018\u00010\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0014\u0010\f\u001a\u00020\t2\n\u0010\r\u001a\u0006\u0012\u0002\b\u00030\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u0010H\u0016J!\u0010\u0011\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0012\u001a\u00028\u00012\b\u0010\u0013\u001a\u0004\u0018\u00010\u000bH\u0016¢\u0006\u0002\u0010\u0014R\u0018\u0010\u0003\u001a\n\u0012\u0006\u0012\u0004\u0018\u00018\u00010\u00048\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u00020\u00068\u0006X\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u0015"}, d2 = {"Lkotlinx/coroutines/channels/AbstractChannel$ReceiveElement;", "E", "Lkotlinx/coroutines/channels/Receive;", "cont", "Lkotlinx/coroutines/CancellableContinuation;", "nullOnClose", "", "(Lkotlinx/coroutines/CancellableContinuation;Z)V", "completeResumeReceive", "", "token", "", "resumeReceiveClosed", "closed", "Lkotlinx/coroutines/channels/Closed;", "toString", "", "tryResumeReceive", "value", "idempotent", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
    /* compiled from: AbstractChannel.kt */
    private static final class ReceiveElement<E> extends Receive<E> {
        @NotNull
        @JvmField
        public final CancellableContinuation<E> cont;
        @JvmField
        public final boolean nullOnClose;

        public ReceiveElement(@NotNull CancellableContinuation<? super E> cancellableContinuation, boolean z) {
            Intrinsics.checkParameterIsNotNull(cancellableContinuation, "cont");
            this.cont = cancellableContinuation;
            this.nullOnClose = z;
        }

        @Nullable
        public Object tryResumeReceive(E e, @Nullable Object obj) {
            return this.cont.tryResume(e, obj);
        }

        public void completeResumeReceive(@NotNull Object obj) {
            Intrinsics.checkParameterIsNotNull(obj, "token");
            this.cont.completeResume(obj);
        }

        public void resumeReceiveClosed(@NotNull Closed<?> closed) {
            Intrinsics.checkParameterIsNotNull(closed, "closed");
            if (closed.closeCause == null && this.nullOnClose) {
                Continuation continuation = (Continuation) this.cont;
                Companion companion = Result.Companion;
                continuation.resumeWith(Result.constructor-impl(null));
                return;
            }
            Continuation continuation2 = this.cont;
            closed = closed.getReceiveException();
            companion = Result.Companion;
            continuation2.resumeWith(Result.constructor-impl(ResultKt.createFailure(closed)));
        }

        @NotNull
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("ReceiveElement[");
            stringBuilder.append(this.cont);
            stringBuilder.append(",nullOnClose=");
            stringBuilder.append(this.nullOnClose);
            stringBuilder.append(']');
            return stringBuilder.toString();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0002\u0018\u0000*\u0004\b\u0001\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B!\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00010\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\u0010\bJ\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0014\u0010\r\u001a\u00020\n2\n\u0010\u000e\u001a\u0006\u0012\u0002\b\u00030\u000fH\u0016J\b\u0010\u0010\u001a\u00020\u0011H\u0016J!\u0010\u0012\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0013\u001a\u00028\u00012\b\u0010\u0014\u001a\u0004\u0018\u00010\fH\u0016¢\u0006\u0002\u0010\u0015R\u0016\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00068\u0006X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00010\u00048\u0006X\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u0016"}, d2 = {"Lkotlinx/coroutines/channels/AbstractChannel$ReceiveHasNext;", "E", "Lkotlinx/coroutines/channels/Receive;", "iterator", "Lkotlinx/coroutines/channels/AbstractChannel$Itr;", "cont", "Lkotlinx/coroutines/CancellableContinuation;", "", "(Lkotlinx/coroutines/channels/AbstractChannel$Itr;Lkotlinx/coroutines/CancellableContinuation;)V", "completeResumeReceive", "", "token", "", "resumeReceiveClosed", "closed", "Lkotlinx/coroutines/channels/Closed;", "toString", "", "tryResumeReceive", "value", "idempotent", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
    /* compiled from: AbstractChannel.kt */
    private static final class ReceiveHasNext<E> extends Receive<E> {
        @NotNull
        @JvmField
        public final CancellableContinuation<Boolean> cont;
        @NotNull
        @JvmField
        public final Itr<E> iterator;

        public ReceiveHasNext(@NotNull Itr<E> itr, @NotNull CancellableContinuation<? super Boolean> cancellableContinuation) {
            Intrinsics.checkParameterIsNotNull(itr, "iterator");
            Intrinsics.checkParameterIsNotNull(cancellableContinuation, "cont");
            this.iterator = itr;
            this.cont = cancellableContinuation;
        }

        @Nullable
        public Object tryResumeReceive(E e, @Nullable Object obj) {
            Object tryResume = this.cont.tryResume(Boolean.valueOf(true), obj);
            if (tryResume != null) {
                if (obj != null) {
                    return new IdempotentTokenValue(tryResume, e);
                }
                this.iterator.setResult(e);
            }
            return tryResume;
        }

        public void completeResumeReceive(@NotNull Object obj) {
            Intrinsics.checkParameterIsNotNull(obj, "token");
            if (obj instanceof IdempotentTokenValue) {
                IdempotentTokenValue idempotentTokenValue = (IdempotentTokenValue) obj;
                this.iterator.setResult(idempotentTokenValue.value);
                this.cont.completeResume(idempotentTokenValue.token);
                return;
            }
            this.cont.completeResume(obj);
        }

        public void resumeReceiveClosed(@NotNull Closed<?> closed) {
            Object tryResume$default;
            Intrinsics.checkParameterIsNotNull(closed, "closed");
            if (closed.closeCause == null) {
                tryResume$default = DefaultImpls.tryResume$default(this.cont, Boolean.valueOf(false), null, 2, null);
            } else {
                tryResume$default = this.cont.tryResumeWithException(closed.getReceiveException());
            }
            if (tryResume$default != null) {
                this.iterator.setResult(closed);
                this.cont.completeResume(tryResume$default);
            }
        }

        @NotNull
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("ReceiveHasNext[");
            stringBuilder.append(this.cont);
            stringBuilder.append(']');
            return stringBuilder.toString();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0004\u0018\u0000*\u0004\b\u0001\u0010\u0001*\u0006\b\u0002\u0010\u0002 \u00002\b\u0012\u0004\u0012\u0002H\u00020\u00032\u00020\u0004BD\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00010\u0006\u0012$\u0010\u0007\u001a \b\u0001\u0012\u0006\u0012\u0004\u0018\u00018\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\t\u0012\u0006\u0012\u0004\u0018\u00010\n0\b\u0012\u0006\u0010\u000b\u001a\u00020\fø\u0001\u0000¢\u0006\u0002\u0010\rJ\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\nH\u0016J\b\u0010\u0012\u001a\u00020\u0010H\u0016J\u0006\u0010\u0013\u001a\u00020\u0010J\u0014\u0010\u0014\u001a\u00020\u00102\n\u0010\u0015\u001a\u0006\u0012\u0002\b\u00030\u0016H\u0016J\b\u0010\u0017\u001a\u00020\u0018H\u0016J!\u0010\u0019\u001a\u0004\u0018\u00010\n2\u0006\u0010\u001a\u001a\u00028\u00022\b\u0010\u001b\u001a\u0004\u0018\u00010\nH\u0016¢\u0006\u0002\u0010\u001cR3\u0010\u0007\u001a \b\u0001\u0012\u0006\u0012\u0004\u0018\u00018\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\t\u0012\u0006\u0012\u0004\u0018\u00010\n0\b8\u0006X\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u000eR\u0010\u0010\u000b\u001a\u00020\f8\u0006X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00010\u00068\u0006X\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u001d"}, d2 = {"Lkotlinx/coroutines/channels/AbstractChannel$ReceiveSelect;", "R", "E", "Lkotlinx/coroutines/channels/Receive;", "Lkotlinx/coroutines/DisposableHandle;", "select", "Lkotlinx/coroutines/selects/SelectInstance;", "block", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "", "nullOnClose", "", "(Lkotlinx/coroutines/channels/AbstractChannel;Lkotlinx/coroutines/selects/SelectInstance;Lkotlin/jvm/functions/Function2;Z)V", "Lkotlin/jvm/functions/Function2;", "completeResumeReceive", "", "token", "dispose", "removeOnSelectCompletion", "resumeReceiveClosed", "closed", "Lkotlinx/coroutines/channels/Closed;", "toString", "", "tryResumeReceive", "value", "idempotent", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
    /* compiled from: AbstractChannel.kt */
    private final class ReceiveSelect<R, E> extends Receive<E> implements DisposableHandle {
        @NotNull
        @JvmField
        public final Function2<E, Continuation<? super R>, Object> block;
        @JvmField
        public final boolean nullOnClose;
        @NotNull
        @JvmField
        public final SelectInstance<R> select;
        final /* synthetic */ AbstractChannel this$0;

        public ReceiveSelect(@NotNull AbstractChannel abstractChannel, @NotNull SelectInstance<? super R> selectInstance, Function2<? super E, ? super Continuation<? super R>, ? extends Object> function2, boolean z) {
            Intrinsics.checkParameterIsNotNull(selectInstance, "select");
            Intrinsics.checkParameterIsNotNull(function2, "block");
            this.this$0 = abstractChannel;
            this.select = selectInstance;
            this.block = function2;
            this.nullOnClose = z;
        }

        @Nullable
        public Object tryResumeReceive(E e, @Nullable Object obj) {
            if (this.select.trySelect(obj) != null) {
                return e != null ? e : AbstractChannelKt.NULL_VALUE;
            } else {
                return null;
            }
        }

        public void completeResumeReceive(@NotNull Object obj) {
            Intrinsics.checkParameterIsNotNull(obj, "token");
            if (obj == AbstractChannelKt.NULL_VALUE) {
                obj = null;
            }
            ContinuationKt.startCoroutine(this.block, obj, this.select.getCompletion());
        }

        public void resumeReceiveClosed(@NotNull Closed<?> closed) {
            Intrinsics.checkParameterIsNotNull(closed, "closed");
            if (!this.select.trySelect(null)) {
                return;
            }
            if (closed.closeCause == null && this.nullOnClose) {
                ContinuationKt.startCoroutine(this.block, null, this.select.getCompletion());
            } else {
                this.select.resumeSelectCancellableWithException(closed.getReceiveException());
            }
        }

        public final void removeOnSelectCompletion() {
            this.select.disposeOnSelect(this);
        }

        public void dispose() {
            if (remove()) {
                this.this$0.onReceiveDequeued();
            }
        }

        @NotNull
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("ReceiveSelect[");
            stringBuilder.append(this.select);
            stringBuilder.append(",nullOnClose=");
            stringBuilder.append(this.nullOnClose);
            stringBuilder.append(']');
            return stringBuilder.toString();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0004\u0018\u0000*\u0004\b\u0001\u0010\u0001*\u0004\b\u0002\u0010\u00022>\u0012\u001a\u0012\u0018\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00010\u0004R\b\u0012\u0004\u0012\u00028\u00000\u00050\u0003j\u001e\u0012\u001a\u0012\u0018\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00010\u0004R\b\u0012\u0004\u0012\u00028\u00000\u0005`\u0006BD\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00020\b\u0012$\u0010\t\u001a \b\u0001\u0012\u0006\u0012\u0004\u0018\u00018\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00020\u000b\u0012\u0006\u0012\u0004\u0018\u00010\f0\n\u0012\u0006\u0010\r\u001a\u00020\u000eø\u0001\u0000¢\u0006\u0002\u0010\u000fJ\u001a\u0010\u0010\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\fH\u0014J\u0018\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0012H\u0014J\u001a\u0010\u0016\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0012H\u0014\u0002\u0004\n\u0002\b\u0019¨\u0006\u0017"}, d2 = {"Lkotlinx/coroutines/channels/AbstractChannel$TryEnqueueReceiveDesc;", "E", "R", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$AddLastDesc;", "Lkotlinx/coroutines/channels/AbstractChannel$ReceiveSelect;", "Lkotlinx/coroutines/channels/AbstractChannel;", "Lkotlinx/coroutines/internal/AddLastDesc;", "select", "Lkotlinx/coroutines/selects/SelectInstance;", "block", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "", "nullOnClose", "", "(Lkotlinx/coroutines/channels/AbstractChannel;Lkotlinx/coroutines/selects/SelectInstance;Lkotlin/jvm/functions/Function2;Z)V", "failure", "affected", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "next", "finishOnSuccess", "", "onPrepare", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
    /* compiled from: AbstractChannel.kt */
    private final class TryEnqueueReceiveDesc<E, R> extends AddLastDesc<ReceiveSelect<R, ? super E>> {
        final /* synthetic */ AbstractChannel this$0;

        public TryEnqueueReceiveDesc(@NotNull AbstractChannel abstractChannel, @NotNull SelectInstance<? super R> selectInstance, Function2<? super E, ? super Continuation<? super R>, ? extends Object> function2, boolean z) {
            Intrinsics.checkParameterIsNotNull(selectInstance, "select");
            Intrinsics.checkParameterIsNotNull(function2, "block");
            this.this$0 = abstractChannel;
            super(abstractChannel.getQueue(), new ReceiveSelect(abstractChannel, selectInstance, function2, z));
        }

        @Nullable
        protected Object failure(@NotNull LockFreeLinkedListNode lockFreeLinkedListNode, @NotNull Object obj) {
            Intrinsics.checkParameterIsNotNull(lockFreeLinkedListNode, "affected");
            Intrinsics.checkParameterIsNotNull(obj, "next");
            return (lockFreeLinkedListNode instanceof Send) != null ? AbstractChannelKt.ENQUEUE_FAILED : null;
        }

        @Nullable
        protected Object onPrepare(@NotNull LockFreeLinkedListNode lockFreeLinkedListNode, @NotNull LockFreeLinkedListNode lockFreeLinkedListNode2) {
            Intrinsics.checkParameterIsNotNull(lockFreeLinkedListNode, "affected");
            Intrinsics.checkParameterIsNotNull(lockFreeLinkedListNode2, "next");
            if (this.this$0.isBufferEmpty()) {
                return super.onPrepare(lockFreeLinkedListNode, lockFreeLinkedListNode2);
            }
            return AbstractChannelKt.ENQUEUE_FAILED;
        }

        protected void finishOnSuccess(@NotNull LockFreeLinkedListNode lockFreeLinkedListNode, @NotNull LockFreeLinkedListNode lockFreeLinkedListNode2) {
            Intrinsics.checkParameterIsNotNull(lockFreeLinkedListNode, "affected");
            Intrinsics.checkParameterIsNotNull(lockFreeLinkedListNode2, "next");
            super.finishOnSuccess(lockFreeLinkedListNode, lockFreeLinkedListNode2);
            this.this$0.onReceiveEnqueued();
            ((ReceiveSelect) this.node).removeOnSelectCompletion();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0004\u0018\u0000*\u0004\b\u0001\u0010\u00012\u0012\u0012\u0004\u0012\u00020\u00030\u0002j\b\u0012\u0004\u0012\u00020\u0003`\u0004B\r\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u001a\u0010\f\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000bH\u0014J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0003H\u0014R\u0016\u0010\b\u001a\u0004\u0018\u00018\u00018\u0006@\u0006X\u000e¢\u0006\u0004\n\u0002\u0010\tR\u0014\u0010\n\u001a\u0004\u0018\u00010\u000b8\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lkotlinx/coroutines/channels/AbstractChannel$TryPollDesc;", "E", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$RemoveFirstDesc;", "Lkotlinx/coroutines/channels/Send;", "Lkotlinx/coroutines/internal/RemoveFirstDesc;", "queue", "Lkotlinx/coroutines/internal/LockFreeLinkedListHead;", "(Lkotlinx/coroutines/internal/LockFreeLinkedListHead;)V", "pollResult", "Ljava/lang/Object;", "resumeToken", "", "failure", "affected", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "next", "validatePrepared", "", "node", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
    /* compiled from: AbstractChannel.kt */
    protected static final class TryPollDesc<E> extends RemoveFirstDesc<Send> {
        @Nullable
        @JvmField
        public E pollResult;
        @Nullable
        @JvmField
        public Object resumeToken;

        public TryPollDesc(@NotNull LockFreeLinkedListHead lockFreeLinkedListHead) {
            Intrinsics.checkParameterIsNotNull(lockFreeLinkedListHead, "queue");
            super(lockFreeLinkedListHead);
        }

        @Nullable
        protected Object failure(@NotNull LockFreeLinkedListNode lockFreeLinkedListNode, @NotNull Object obj) {
            Intrinsics.checkParameterIsNotNull(lockFreeLinkedListNode, "affected");
            Intrinsics.checkParameterIsNotNull(obj, "next");
            if ((lockFreeLinkedListNode instanceof Closed) != null) {
                return lockFreeLinkedListNode;
            }
            return (lockFreeLinkedListNode instanceof Send) == null ? AbstractChannelKt.POLL_FAILED : null;
        }

        protected boolean validatePrepared(@NotNull Send send) {
            Intrinsics.checkParameterIsNotNull(send, "node");
            Object tryResumeSend = send.tryResumeSend(this);
            if (tryResumeSend == null) {
                return null;
            }
            this.resumeToken = tryResumeSend;
            this.pollResult = send.getPollResult();
            return true;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0004\u0018\u00002\u00020\u0001B\u0011\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003¢\u0006\u0002\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0002J\b\u0010\t\u001a\u00020\nH\u0016R\u0012\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003X\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u000b"}, d2 = {"Lkotlinx/coroutines/channels/AbstractChannel$RemoveReceiveOnCancel;", "Lkotlinx/coroutines/CancelHandler;", "receive", "Lkotlinx/coroutines/channels/Receive;", "(Lkotlinx/coroutines/channels/AbstractChannel;Lkotlinx/coroutines/channels/Receive;)V", "invoke", "", "cause", "", "toString", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
    /* compiled from: AbstractChannel.kt */
    private final class RemoveReceiveOnCancel extends CancelHandler {
        private final Receive<?> receive;
        final /* synthetic */ AbstractChannel this$0;

        public RemoveReceiveOnCancel(@NotNull AbstractChannel abstractChannel, Receive<?> receive) {
            Intrinsics.checkParameterIsNotNull(receive, "receive");
            this.this$0 = abstractChannel;
            this.receive = receive;
        }

        public void invoke(@Nullable Throwable th) {
            if (this.receive.remove() != null) {
                this.this$0.onReceiveDequeued();
            }
        }

        @NotNull
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("RemoveReceiveOnCancel[");
            stringBuilder.append(this.receive);
            stringBuilder.append(']');
            return stringBuilder.toString();
        }
    }

    protected abstract boolean isBufferAlwaysEmpty();

    protected abstract boolean isBufferEmpty();

    protected void onReceiveDequeued() {
    }

    protected void onReceiveEnqueued() {
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Left here for binary compatibility")
    @JvmName(name = "cancel")
    /* renamed from: cancel */
    public /* synthetic */ boolean m23cancel() {
        return cancel(null);
    }

    @Nullable
    protected Object pollInternal() {
        Object tryResumeSend;
        Send takeFirstSendOrPeekClosed;
        do {
            takeFirstSendOrPeekClosed = takeFirstSendOrPeekClosed();
            if (takeFirstSendOrPeekClosed == null) {
                return AbstractChannelKt.POLL_FAILED;
            }
            tryResumeSend = takeFirstSendOrPeekClosed.tryResumeSend(null);
        } while (tryResumeSend == null);
        takeFirstSendOrPeekClosed.completeResumeSend(tryResumeSend);
        return takeFirstSendOrPeekClosed.getPollResult();
    }

    @Nullable
    protected Object pollSelectInternal(@NotNull SelectInstance<?> selectInstance) {
        Intrinsics.checkParameterIsNotNull(selectInstance, "select");
        TryPollDesc describeTryPoll = describeTryPoll();
        selectInstance = selectInstance.performAtomicTrySelect(describeTryPoll);
        if (selectInstance != null) {
            return selectInstance;
        }
        Send send = (Send) describeTryPoll.getResult();
        Object obj = describeTryPoll.resumeToken;
        if (obj == null) {
            Intrinsics.throwNpe();
        }
        send.completeResumeSend(obj);
        return describeTryPoll.pollResult;
    }

    protected final boolean getHasReceiveOrClosed() {
        return getQueue().getNextNode() instanceof ReceiveOrClosed;
    }

    public final boolean isClosedForReceive() {
        return getClosedForReceive() != null && isBufferEmpty();
    }

    public final boolean isEmpty() {
        return !(getQueue().getNextNode() instanceof Send) && isBufferEmpty();
    }

    @Nullable
    public final Object receive(@NotNull Continuation<? super E> continuation) {
        Object pollInternal = pollInternal();
        if (pollInternal != AbstractChannelKt.POLL_FAILED) {
            return receiveResult(pollInternal);
        }
        return receiveSuspend(continuation);
    }

    private final E receiveResult(Object obj) {
        if (!(obj instanceof Closed)) {
            return obj;
        }
        throw ((Closed) obj).getReceiveException();
    }

    private final boolean enqueueReceive(Receive<? super E> receive) {
        boolean z = false;
        LockFreeLinkedListNode queue;
        if (isBufferAlwaysEmpty()) {
            queue = getQueue();
            LockFreeLinkedListNode lockFreeLinkedListNode;
            do {
                Object prev = queue.getPrev();
                if (prev != null) {
                    lockFreeLinkedListNode = (LockFreeLinkedListNode) prev;
                    if ((!(lockFreeLinkedListNode instanceof Send) ? 1 : null) == null) {
                        break;
                    }
                } else {
                    throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */");
                }
            } while (!lockFreeLinkedListNode.addNext(receive, queue));
        }
        queue = getQueue();
        LockFreeLinkedListNode lockFreeLinkedListNode2 = receive;
        CondAddOp abstractChannel$enqueueReceive$$inlined$addLastIfPrevAndIf$1 = new AbstractChannel$enqueueReceive$$inlined$addLastIfPrevAndIf$1(lockFreeLinkedListNode2, lockFreeLinkedListNode2, this);
        while (true) {
            Object prev2 = queue.getPrev();
            if (prev2 != null) {
                LockFreeLinkedListNode lockFreeLinkedListNode3 = (LockFreeLinkedListNode) prev2;
                if ((!(lockFreeLinkedListNode3 instanceof Send) ? 1 : null) != null) {
                    switch (lockFreeLinkedListNode3.tryCondAddNext(lockFreeLinkedListNode2, queue, abstractChannel$enqueueReceive$$inlined$addLastIfPrevAndIf$1)) {
                        case 1:
                            break;
                        case 2:
                            break;
                        default:
                    }
                }
                if (z) {
                    onReceiveEnqueued();
                }
                return z;
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */");
        }
        z = true;
        if (z) {
            onReceiveEnqueued();
        }
        return z;
    }

    @Nullable
    public final Object receiveOrNull(@NotNull Continuation<? super E> continuation) {
        Object pollInternal = pollInternal();
        if (pollInternal != AbstractChannelKt.POLL_FAILED) {
            return receiveOrNullResult(pollInternal);
        }
        return receiveOrNullSuspend(continuation);
    }

    private final E receiveOrNullResult(Object obj) {
        if (!(obj instanceof Closed)) {
            return obj;
        }
        Closed closed = (Closed) obj;
        if (closed.closeCause == null) {
            return null;
        }
        throw closed.closeCause;
    }

    @Nullable
    public final E poll() {
        Object pollInternal = pollInternal();
        return pollInternal == AbstractChannelKt.POLL_FAILED ? null : receiveOrNullResult(pollInternal);
    }

    public void cancel() {
        cancel(null);
    }

    public boolean cancel(@Nullable Throwable th) {
        th = close(th);
        cleanupSendQueueOnCancel();
        return th;
    }

    protected void cleanupSendQueueOnCancel() {
        Send closedForSend = getClosedForSend();
        if (closedForSend != null) {
            Send takeFirstSendOrPeekClosed;
            while (true) {
                takeFirstSendOrPeekClosed = takeFirstSendOrPeekClosed();
                if (takeFirstSendOrPeekClosed == null) {
                    throw new IllegalStateException("Cannot happen".toString());
                } else if (takeFirstSendOrPeekClosed instanceof Closed) {
                    break;
                } else {
                    takeFirstSendOrPeekClosed.resumeSendClosed(closedForSend);
                }
            }
            if ((takeFirstSendOrPeekClosed == closedForSend ? 1 : null) == null) {
                throw new IllegalStateException("Check failed.".toString());
            }
            return;
        }
        throw new IllegalStateException("Cannot happen".toString());
    }

    @NotNull
    public final ChannelIterator<E> iterator() {
        return new Itr(this);
    }

    @NotNull
    protected final TryPollDesc<E> describeTryPoll() {
        return new TryPollDesc(getQueue());
    }

    @NotNull
    public final SelectClause1<E> getOnReceive() {
        return new AbstractChannel$onReceive$1(this);
    }

    private final <R> void registerSelectReceive(SelectInstance<? super R> selectInstance, Function2<? super E, ? super Continuation<? super R>, ? extends Object> function2) {
        while (!selectInstance.isSelected()) {
            Object pollSelectInternal;
            if (!isEmpty()) {
                pollSelectInternal = pollSelectInternal(selectInstance);
                if (pollSelectInternal != SelectKt.getALREADY_SELECTED()) {
                    if (pollSelectInternal != AbstractChannelKt.POLL_FAILED) {
                        if (pollSelectInternal instanceof Closed) {
                            throw ((Closed) pollSelectInternal).getReceiveException();
                        }
                        UndispatchedKt.startCoroutineUnintercepted(function2, pollSelectInternal, selectInstance.getCompletion());
                        return;
                    }
                } else {
                    return;
                }
            } else if (function2 != null) {
                pollSelectInternal = selectInstance.performAtomicIfNotSelected(new TryEnqueueReceiveDesc(this, selectInstance, function2, false));
                if (pollSelectInternal != null && pollSelectInternal != SelectKt.getALREADY_SELECTED()) {
                    if (pollSelectInternal != AbstractChannelKt.ENQUEUE_FAILED) {
                        selectInstance = new StringBuilder();
                        selectInstance.append("performAtomicIfNotSelected(TryEnqueueReceiveDesc) returned ");
                        selectInstance.append(pollSelectInternal);
                        throw ((Throwable) new IllegalStateException(selectInstance.toString().toString()));
                    }
                } else {
                    return;
                }
            } else {
                throw new TypeCastException("null cannot be cast to non-null type suspend (E?) -> R");
            }
        }
    }

    @NotNull
    public final SelectClause1<E> getOnReceiveOrNull() {
        return new AbstractChannel$onReceiveOrNull$1(this);
    }

    private final <R> void registerSelectReceiveOrNull(SelectInstance<? super R> selectInstance, Function2<? super E, ? super Continuation<? super R>, ? extends Object> function2) {
        while (!selectInstance.isSelected()) {
            Object performAtomicIfNotSelected;
            if (isEmpty()) {
                performAtomicIfNotSelected = selectInstance.performAtomicIfNotSelected(new TryEnqueueReceiveDesc(this, selectInstance, function2, true));
                if (performAtomicIfNotSelected != null && performAtomicIfNotSelected != SelectKt.getALREADY_SELECTED()) {
                    if (performAtomicIfNotSelected != AbstractChannelKt.ENQUEUE_FAILED) {
                        selectInstance = new StringBuilder();
                        selectInstance.append("performAtomicIfNotSelected(TryEnqueueReceiveDesc) returned ");
                        selectInstance.append(performAtomicIfNotSelected);
                        throw ((Throwable) new IllegalStateException(selectInstance.toString().toString()));
                    }
                } else {
                    return;
                }
            }
            performAtomicIfNotSelected = pollSelectInternal(selectInstance);
            if (performAtomicIfNotSelected != SelectKt.getALREADY_SELECTED()) {
                if (performAtomicIfNotSelected != AbstractChannelKt.POLL_FAILED) {
                    if (performAtomicIfNotSelected instanceof Closed) {
                        Closed closed = (Closed) performAtomicIfNotSelected;
                        if (closed.closeCause == null) {
                            if (selectInstance.trySelect(null)) {
                                UndispatchedKt.startCoroutineUnintercepted(function2, null, selectInstance.getCompletion());
                            }
                            return;
                        }
                        throw closed.closeCause;
                    }
                    UndispatchedKt.startCoroutineUnintercepted(function2, performAtomicIfNotSelected, selectInstance.getCompletion());
                    return;
                }
            } else {
                return;
            }
        }
    }

    @Nullable
    protected ReceiveOrClosed<E> takeFirstReceiveOrPeekClosed() {
        ReceiveOrClosed<E> takeFirstReceiveOrPeekClosed = super.takeFirstReceiveOrPeekClosed();
        if (!(takeFirstReceiveOrPeekClosed == null || (takeFirstReceiveOrPeekClosed instanceof Closed))) {
            onReceiveDequeued();
        }
        return takeFirstReceiveOrPeekClosed;
    }

    private final void removeReceiveOnCancel(CancellableContinuation<?> cancellableContinuation, Receive<?> receive) {
        cancellableContinuation.invokeOnCancellation(new RemoveReceiveOnCancel(this, receive));
    }

    @Nullable
    final /* synthetic */ Object receiveSuspend(@NotNull Continuation<? super E> continuation) {
        Object pollInternal;
        Continuation continuation2;
        Companion companion;
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt__IntrinsicsJvmKt.intercepted(continuation), 0);
        CancellableContinuation cancellableContinuation = cancellableContinuationImpl;
        ReceiveElement receiveElement = new ReceiveElement(cancellableContinuation, false);
        do {
            Receive receive = receiveElement;
            if (enqueueReceive(receive)) {
                cancellableContinuation.initCancellability();
                removeReceiveOnCancel(cancellableContinuation, receive);
                break;
            }
            pollInternal = pollInternal();
            if (pollInternal instanceof Closed) {
                continuation2 = cancellableContinuation;
                Throwable receiveException = ((Closed) pollInternal).getReceiveException();
                companion = Result.Companion;
                continuation2.resumeWith(Result.constructor-impl(ResultKt.createFailure(receiveException)));
                break;
            }
        } while (pollInternal == AbstractChannelKt.POLL_FAILED);
        continuation2 = cancellableContinuation;
        companion = Result.Companion;
        continuation2.resumeWith(Result.constructor-impl(pollInternal));
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }

    @Nullable
    final /* synthetic */ Object receiveOrNullSuspend(@NotNull Continuation<? super E> continuation) {
        Object pollInternal;
        Continuation continuation2;
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt__IntrinsicsJvmKt.intercepted(continuation), 0);
        CancellableContinuation cancellableContinuation = cancellableContinuationImpl;
        ReceiveElement receiveElement = new ReceiveElement(cancellableContinuation, true);
        do {
            Receive receive = receiveElement;
            if (enqueueReceive(receive)) {
                cancellableContinuation.initCancellability();
                removeReceiveOnCancel(cancellableContinuation, receive);
                break;
            }
            pollInternal = pollInternal();
            if (pollInternal instanceof Closed) {
                Closed closed = (Closed) pollInternal;
                Companion companion;
                if (closed.closeCause == null) {
                    continuation2 = cancellableContinuation;
                    companion = Result.Companion;
                    continuation2.resumeWith(Result.constructor-impl(null));
                } else {
                    continuation2 = cancellableContinuation;
                    Throwable th = closed.closeCause;
                    companion = Result.Companion;
                    continuation2.resumeWith(Result.constructor-impl(ResultKt.createFailure(th)));
                }
            }
        } while (pollInternal == AbstractChannelKt.POLL_FAILED);
        continuation2 = cancellableContinuation;
        Companion companion2 = Result.Companion;
        continuation2.resumeWith(Result.constructor-impl(pollInternal));
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }
}
