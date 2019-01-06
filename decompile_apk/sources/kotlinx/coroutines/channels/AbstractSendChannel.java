package kotlinx.coroutines.channels;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.ContinuationKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.internal.AtomicDesc;
import kotlinx.coroutines.internal.LockFreeLinkedListHead;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;
import kotlinx.coroutines.internal.LockFreeLinkedListNode.AddLastDesc;
import kotlinx.coroutines.internal.LockFreeLinkedListNode.CondAddOp;
import kotlinx.coroutines.internal.LockFreeLinkedListNode.RemoveFirstDesc;
import kotlinx.coroutines.intrinsics.UndispatchedKt;
import kotlinx.coroutines.selects.SelectClause2;
import kotlinx.coroutines.selects.SelectInstance;
import kotlinx.coroutines.selects.SelectKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000¦\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\b \u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002:\u0006WXYZ[\\B\u0005¢\u0006\u0002\u0010\u0003J\u0012\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010$H\u0014J\u0012\u0010%\u001a\u00020\u000f2\b\u0010#\u001a\u0004\u0018\u00010$H\u0016J\u0010\u0010&\u001a\u00020\"2\u0006\u0010'\u001a\u00020(H\u0004J\b\u0010)\u001a\u00020*H\u0002J!\u0010+\u001a\u000e\u0012\u0002\b\u00030,j\u0006\u0012\u0002\b\u0003`-2\u0006\u0010.\u001a\u00028\u0000H\u0004¢\u0006\u0002\u0010/J!\u00100\u001a\u000e\u0012\u0002\b\u00030,j\u0006\u0012\u0002\b\u0003`-2\u0006\u0010.\u001a\u00028\u0000H\u0004¢\u0006\u0002\u0010/J\u001b\u00101\u001a\b\u0012\u0004\u0012\u00028\u0000022\u0006\u0010.\u001a\u00028\u0000H\u0004¢\u0006\u0002\u00103J\u0012\u00104\u001a\u0004\u0018\u00010\u00162\u0006\u00105\u001a\u000206H\u0002J\u0014\u00107\u001a\u00020\"2\n\u00108\u001a\u0006\u0012\u0002\b\u00030\tH\u0002J\"\u00109\u001a\u00020\"2\u0018\u0010:\u001a\u0014\u0012\u0006\u0012\u0004\u0018\u00010$\u0012\u0004\u0012\u00020\"0;j\u0002`<H\u0016J\u0012\u0010=\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010$H\u0002J\u0013\u0010>\u001a\u00020\u000f2\u0006\u0010.\u001a\u00028\u0000¢\u0006\u0002\u0010?J\u0015\u0010@\u001a\u00020\u00162\u0006\u0010.\u001a\u00028\u0000H\u0014¢\u0006\u0002\u0010AJ!\u0010B\u001a\u00020\u00162\u0006\u0010.\u001a\u00028\u00002\n\u0010C\u001a\u0006\u0012\u0002\b\u00030DH\u0014¢\u0006\u0002\u0010EJ\u0016\u0010F\u001a\u00020\"2\f\u00108\u001a\b\u0012\u0004\u0012\u00028\u00000\tH\u0014JV\u0010G\u001a\u00020\"\"\u0004\b\u0001\u0010H2\f\u0010C\u001a\b\u0012\u0004\u0012\u0002HH0D2\u0006\u0010.\u001a\u00028\u00002(\u0010I\u001a$\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u0002HH0K\u0012\u0006\u0012\u0004\u0018\u00010\u00160JH\u0002ø\u0001\u0000¢\u0006\u0002\u0010LJ\u0019\u00105\u001a\u00020\"2\u0006\u0010.\u001a\u00028\u0000H@ø\u0001\u0000¢\u0006\u0002\u0010MJ\u001b\u0010N\u001a\b\u0012\u0002\b\u0003\u0018\u00010O2\u0006\u0010.\u001a\u00028\u0000H\u0004¢\u0006\u0002\u0010PJ\u001b\u0010Q\u001a\b\u0012\u0002\b\u0003\u0018\u00010O2\u0006\u0010.\u001a\u00028\u0000H\u0004¢\u0006\u0002\u0010PJ\u0019\u0010R\u001a\u00020\"2\u0006\u0010.\u001a\u00028\u0000H@ø\u0001\u0000¢\u0006\u0002\u0010MJ\u0010\u0010S\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010OH\u0014J\n\u0010T\u001a\u0004\u0018\u00010UH\u0004J\b\u0010V\u001a\u00020\u0005H\u0016R\u0014\u0010\u0004\u001a\u00020\u00058TX\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\b\u0012\u0002\b\u0003\u0018\u00010\t8DX\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\b\u0012\u0002\b\u0003\u0018\u00010\t8DX\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000bR\u0012\u0010\u000e\u001a\u00020\u000fX¤\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u0010R\u0012\u0010\u0011\u001a\u00020\u000fX¤\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0010R\u0011\u0010\u0012\u001a\u00020\u000f8F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0010R\u0011\u0010\u0013\u001a\u00020\u000f8F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0010R\u0016\u0010\u0014\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00160\u0015X\u0004¢\u0006\u0002\n\u0000R#\u0010\u0017\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00020\u00188F¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001b\u001a\u00020\u001cX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0014\u0010\u001f\u001a\u00020\u00058BX\u0004¢\u0006\u0006\u001a\u0004\b \u0010\u0007\u0002\u0004\n\u0002\b\u0019¨\u0006]"}, d2 = {"Lkotlinx/coroutines/channels/AbstractSendChannel;", "E", "Lkotlinx/coroutines/channels/SendChannel;", "()V", "bufferDebugString", "", "getBufferDebugString", "()Ljava/lang/String;", "closedForReceive", "Lkotlinx/coroutines/channels/Closed;", "getClosedForReceive", "()Lkotlinx/coroutines/channels/Closed;", "closedForSend", "getClosedForSend", "isBufferAlwaysFull", "", "()Z", "isBufferFull", "isClosedForSend", "isFull", "onCloseHandler", "Lkotlinx/atomicfu/AtomicRef;", "", "onSend", "Lkotlinx/coroutines/selects/SelectClause2;", "getOnSend", "()Lkotlinx/coroutines/selects/SelectClause2;", "queue", "Lkotlinx/coroutines/internal/LockFreeLinkedListHead;", "getQueue", "()Lkotlinx/coroutines/internal/LockFreeLinkedListHead;", "queueDebugStateString", "getQueueDebugStateString", "afterClose", "", "cause", "", "close", "conflatePreviousSendBuffered", "node", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "countQueueSize", "", "describeSendBuffered", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$AddLastDesc;", "Lkotlinx/coroutines/internal/AddLastDesc;", "element", "(Ljava/lang/Object;)Lkotlinx/coroutines/internal/LockFreeLinkedListNode$AddLastDesc;", "describeSendConflated", "describeTryOffer", "Lkotlinx/coroutines/channels/AbstractSendChannel$TryOfferDesc;", "(Ljava/lang/Object;)Lkotlinx/coroutines/channels/AbstractSendChannel$TryOfferDesc;", "enqueueSend", "send", "Lkotlinx/coroutines/channels/SendElement;", "helpClose", "closed", "invokeOnClose", "handler", "Lkotlin/Function1;", "Lkotlinx/coroutines/channels/Handler;", "invokeOnCloseHandler", "offer", "(Ljava/lang/Object;)Z", "offerInternal", "(Ljava/lang/Object;)Ljava/lang/Object;", "offerSelectInternal", "select", "Lkotlinx/coroutines/selects/SelectInstance;", "(Ljava/lang/Object;Lkotlinx/coroutines/selects/SelectInstance;)Ljava/lang/Object;", "onClosed", "registerSelectSend", "R", "block", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "(Lkotlinx/coroutines/selects/SelectInstance;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)V", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sendBuffered", "Lkotlinx/coroutines/channels/ReceiveOrClosed;", "(Ljava/lang/Object;)Lkotlinx/coroutines/channels/ReceiveOrClosed;", "sendConflated", "sendSuspend", "takeFirstReceiveOrPeekClosed", "takeFirstSendOrPeekClosed", "Lkotlinx/coroutines/channels/Send;", "toString", "SendBuffered", "SendBufferedDesc", "SendConflatedDesc", "SendSelect", "TryEnqueueSendDesc", "TryOfferDesc", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
/* compiled from: AbstractChannel.kt */
public abstract class AbstractSendChannel<E> implements SendChannel<E> {
    private static final AtomicReferenceFieldUpdater onCloseHandler$FU = AtomicReferenceFieldUpdater.newUpdater(AbstractSendChannel.class, Object.class, "onCloseHandler");
    private volatile Object onCloseHandler = null;
    @NotNull
    private final LockFreeLinkedListHead queue = new LockFreeLinkedListHead();

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u0000*\u0006\b\u0001\u0010\u0001 \u00012\u00020\u00022\u00020\u0003B\r\u0012\u0006\u0010\u0004\u001a\u00028\u0001¢\u0006\u0002\u0010\u0005J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\bH\u0016J\u0014\u0010\u000e\u001a\u00020\f2\n\u0010\u000f\u001a\u0006\u0012\u0002\b\u00030\u0010H\u0016J\u0014\u0010\u0011\u001a\u0004\u0018\u00010\b2\b\u0010\u0012\u001a\u0004\u0018\u00010\bH\u0016R\u0012\u0010\u0004\u001a\u00028\u00018\u0006X\u0004¢\u0006\u0004\n\u0002\u0010\u0006R\u0016\u0010\u0007\u001a\u0004\u0018\u00010\b8VX\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\n\u0002\u0004\n\u0002\b\u0019¨\u0006\u0013"}, d2 = {"Lkotlinx/coroutines/channels/AbstractSendChannel$SendBuffered;", "E", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "Lkotlinx/coroutines/channels/Send;", "element", "(Ljava/lang/Object;)V", "Ljava/lang/Object;", "pollResult", "", "getPollResult", "()Ljava/lang/Object;", "completeResumeSend", "", "token", "resumeSendClosed", "closed", "Lkotlinx/coroutines/channels/Closed;", "tryResumeSend", "idempotent", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
    /* compiled from: AbstractChannel.kt */
    private static final class SendBuffered<E> extends LockFreeLinkedListNode implements Send {
        @JvmField
        public final E element;

        public void resumeSendClosed(@NotNull Closed<?> closed) {
            Intrinsics.checkParameterIsNotNull(closed, "closed");
        }

        public SendBuffered(E e) {
            this.element = e;
        }

        @Nullable
        public Object getPollResult() {
            return this.element;
        }

        @Nullable
        public Object tryResumeSend(@Nullable Object obj) {
            return AbstractChannelKt.SEND_RESUMED;
        }

        public void completeResumeSend(@NotNull Object obj) {
            Intrinsics.checkParameterIsNotNull(obj, "token");
            if ((obj == AbstractChannelKt.SEND_RESUMED ? true : null) == null) {
                throw ((Throwable) new IllegalStateException("Check failed.".toString()));
            }
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0002\u0018\u0000*\u0004\b\u0001\u0010\u0001*\u0004\b\u0002\u0010\u00022\u00020\u00032\u00020\u00042\u00020\u0005BX\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00010\t\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00020\u000b\u0012(\u0010\f\u001a$\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\t\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00020\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u00070\rø\u0001\u0000¢\u0006\u0002\u0010\u000fJ\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0007H\u0016J\b\u0010\u0016\u001a\u00020\u0014H\u0016J\u0006\u0010\u0017\u001a\u00020\u0014J\u0014\u0010\u0018\u001a\u00020\u00142\n\u0010\u0019\u001a\u0006\u0012\u0002\b\u00030\u001aH\u0016J\b\u0010\u001b\u001a\u00020\u001cH\u0016J\u0014\u0010\u001d\u001a\u0004\u0018\u00010\u00072\b\u0010\u001e\u001a\u0004\u0018\u00010\u0007H\u0016R7\u0010\f\u001a$\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\t\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00020\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u00070\r8\u0006X\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0010R\u0016\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00010\t8\u0006X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0016\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00020\u000b8\u0006X\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u001f"}, d2 = {"Lkotlinx/coroutines/channels/AbstractSendChannel$SendSelect;", "E", "R", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "Lkotlinx/coroutines/channels/Send;", "Lkotlinx/coroutines/DisposableHandle;", "pollResult", "", "channel", "Lkotlinx/coroutines/channels/SendChannel;", "select", "Lkotlinx/coroutines/selects/SelectInstance;", "block", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "(Ljava/lang/Object;Lkotlinx/coroutines/channels/SendChannel;Lkotlinx/coroutines/selects/SelectInstance;Lkotlin/jvm/functions/Function2;)V", "Lkotlin/jvm/functions/Function2;", "getPollResult", "()Ljava/lang/Object;", "completeResumeSend", "", "token", "dispose", "disposeOnSelect", "resumeSendClosed", "closed", "Lkotlinx/coroutines/channels/Closed;", "toString", "", "tryResumeSend", "idempotent", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
    /* compiled from: AbstractChannel.kt */
    private static final class SendSelect<E, R> extends LockFreeLinkedListNode implements Send, DisposableHandle {
        @NotNull
        @JvmField
        public final Function2<SendChannel<? super E>, Continuation<? super R>, Object> block;
        @NotNull
        @JvmField
        public final SendChannel<E> channel;
        @Nullable
        private final Object pollResult;
        @NotNull
        @JvmField
        public final SelectInstance<R> select;

        @Nullable
        public Object getPollResult() {
            return this.pollResult;
        }

        public SendSelect(@Nullable Object obj, @NotNull SendChannel<? super E> sendChannel, @NotNull SelectInstance<? super R> selectInstance, @NotNull Function2<? super SendChannel<? super E>, ? super Continuation<? super R>, ? extends Object> function2) {
            Intrinsics.checkParameterIsNotNull(sendChannel, "channel");
            Intrinsics.checkParameterIsNotNull(selectInstance, "select");
            Intrinsics.checkParameterIsNotNull(function2, "block");
            this.pollResult = obj;
            this.channel = sendChannel;
            this.select = selectInstance;
            this.block = function2;
        }

        @Nullable
        public Object tryResumeSend(@Nullable Object obj) {
            return this.select.trySelect(obj) != null ? AbstractChannelKt.SELECT_STARTED : null;
        }

        public void completeResumeSend(@NotNull Object obj) {
            Intrinsics.checkParameterIsNotNull(obj, "token");
            if ((obj == AbstractChannelKt.SELECT_STARTED ? true : null) != null) {
                ContinuationKt.startCoroutine(this.block, this.channel, this.select.getCompletion());
                return;
            }
            throw ((Throwable) new IllegalStateException("Check failed.".toString()));
        }

        public final void disposeOnSelect() {
            this.select.disposeOnSelect(this);
        }

        public void dispose() {
            remove();
        }

        public void resumeSendClosed(@NotNull Closed<?> closed) {
            Intrinsics.checkParameterIsNotNull(closed, "closed");
            if (this.select.trySelect(null)) {
                this.select.resumeSelectCancellableWithException(closed.getSendException());
            }
        }

        @NotNull
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("SendSelect(");
            stringBuilder.append(getPollResult());
            stringBuilder.append(")[");
            stringBuilder.append(this.channel);
            stringBuilder.append(", ");
            stringBuilder.append(this.select);
            stringBuilder.append(']');
            return stringBuilder.toString();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0012\u0018\u0000*\u0004\b\u0001\u0010\u00012\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u00030\u0002j\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u0003`\u0004B\u0015\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00028\u0001¢\u0006\u0002\u0010\bJ\u001a\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\nH\u0014¨\u0006\u000e"}, d2 = {"Lkotlinx/coroutines/channels/AbstractSendChannel$SendBufferedDesc;", "E", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$AddLastDesc;", "Lkotlinx/coroutines/channels/AbstractSendChannel$SendBuffered;", "Lkotlinx/coroutines/internal/AddLastDesc;", "queue", "Lkotlinx/coroutines/internal/LockFreeLinkedListHead;", "element", "(Lkotlinx/coroutines/internal/LockFreeLinkedListHead;Ljava/lang/Object;)V", "failure", "", "affected", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "next", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
    /* compiled from: AbstractChannel.kt */
    private static class SendBufferedDesc<E> extends AddLastDesc<SendBuffered<? extends E>> {
        public SendBufferedDesc(@NotNull LockFreeLinkedListHead lockFreeLinkedListHead, E e) {
            Intrinsics.checkParameterIsNotNull(lockFreeLinkedListHead, "queue");
            super(lockFreeLinkedListHead, new SendBuffered(e));
        }

        @Nullable
        protected Object failure(@NotNull LockFreeLinkedListNode lockFreeLinkedListNode, @NotNull Object obj) {
            Intrinsics.checkParameterIsNotNull(lockFreeLinkedListNode, "affected");
            Intrinsics.checkParameterIsNotNull(obj, "next");
            return (lockFreeLinkedListNode instanceof ReceiveOrClosed) != null ? AbstractChannelKt.OFFER_FAILED : null;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0004\u0018\u0000*\u0004\b\u0001\u0010\u00012*\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u0002H\u00010\u00030\u0002j\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u0002H\u00010\u0003`\u0004BH\u0012\u0006\u0010\u0005\u001a\u00028\u0000\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00010\u0007\u0012(\u0010\b\u001a$\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\n\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u000b\u0012\u0006\u0012\u0004\u0018\u00010\f0\tø\u0001\u0000¢\u0006\u0002\u0010\rJ\u001a\u0010\u000e\u001a\u0004\u0018\u00010\f2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\fH\u0014J\u0018\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010H\u0014J\u001a\u0010\u0014\u001a\u0004\u0018\u00010\f2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010H\u0014\u0002\u0004\n\u0002\b\u0019¨\u0006\u0015"}, d2 = {"Lkotlinx/coroutines/channels/AbstractSendChannel$TryEnqueueSendDesc;", "R", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$AddLastDesc;", "Lkotlinx/coroutines/channels/AbstractSendChannel$SendSelect;", "Lkotlinx/coroutines/internal/AddLastDesc;", "element", "select", "Lkotlinx/coroutines/selects/SelectInstance;", "block", "Lkotlin/Function2;", "Lkotlinx/coroutines/channels/SendChannel;", "Lkotlin/coroutines/Continuation;", "", "(Lkotlinx/coroutines/channels/AbstractSendChannel;Ljava/lang/Object;Lkotlinx/coroutines/selects/SelectInstance;Lkotlin/jvm/functions/Function2;)V", "failure", "affected", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "next", "finishOnSuccess", "", "onPrepare", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
    /* compiled from: AbstractChannel.kt */
    private final class TryEnqueueSendDesc<R> extends AddLastDesc<SendSelect<E, R>> {
        final /* synthetic */ AbstractSendChannel this$0;

        public TryEnqueueSendDesc(AbstractSendChannel abstractSendChannel, @NotNull E e, @NotNull SelectInstance<? super R> selectInstance, Function2<? super SendChannel<? super E>, ? super Continuation<? super R>, ? extends Object> function2) {
            Intrinsics.checkParameterIsNotNull(selectInstance, "select");
            Intrinsics.checkParameterIsNotNull(function2, "block");
            this.this$0 = abstractSendChannel;
            super(abstractSendChannel.getQueue(), new SendSelect(e, abstractSendChannel, selectInstance, function2));
        }

        @Nullable
        protected Object failure(@NotNull LockFreeLinkedListNode lockFreeLinkedListNode, @NotNull Object obj) {
            Intrinsics.checkParameterIsNotNull(lockFreeLinkedListNode, "affected");
            Intrinsics.checkParameterIsNotNull(obj, "next");
            if ((lockFreeLinkedListNode instanceof ReceiveOrClosed) == null) {
                return null;
            }
            if ((lockFreeLinkedListNode instanceof Closed) == null) {
                lockFreeLinkedListNode = null;
            }
            lockFreeLinkedListNode = (Closed) lockFreeLinkedListNode;
            if (lockFreeLinkedListNode == null) {
                lockFreeLinkedListNode = AbstractChannelKt.ENQUEUE_FAILED;
            }
            return lockFreeLinkedListNode;
        }

        @Nullable
        protected Object onPrepare(@NotNull LockFreeLinkedListNode lockFreeLinkedListNode, @NotNull LockFreeLinkedListNode lockFreeLinkedListNode2) {
            Intrinsics.checkParameterIsNotNull(lockFreeLinkedListNode, "affected");
            Intrinsics.checkParameterIsNotNull(lockFreeLinkedListNode2, "next");
            if (this.this$0.isBufferFull()) {
                return super.onPrepare(lockFreeLinkedListNode, lockFreeLinkedListNode2);
            }
            return AbstractChannelKt.ENQUEUE_FAILED;
        }

        protected void finishOnSuccess(@NotNull LockFreeLinkedListNode lockFreeLinkedListNode, @NotNull LockFreeLinkedListNode lockFreeLinkedListNode2) {
            Intrinsics.checkParameterIsNotNull(lockFreeLinkedListNode, "affected");
            Intrinsics.checkParameterIsNotNull(lockFreeLinkedListNode2, "next");
            super.finishOnSuccess(lockFreeLinkedListNode, lockFreeLinkedListNode2);
            ((SendSelect) this.node).disposeOnSelect();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0004\u0018\u0000*\u0004\b\u0001\u0010\u00012\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u00030\u0002j\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u0003`\u0004B\u0015\u0012\u0006\u0010\u0005\u001a\u00028\u0001\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u001a\u0010\f\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000bH\u0014J\u0016\u0010\u0010\u001a\u00020\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00010\u0003H\u0014R\u0012\u0010\u0005\u001a\u00028\u00018\u0006X\u0004¢\u0006\u0004\n\u0002\u0010\tR\u0014\u0010\n\u001a\u0004\u0018\u00010\u000b8\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lkotlinx/coroutines/channels/AbstractSendChannel$TryOfferDesc;", "E", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$RemoveFirstDesc;", "Lkotlinx/coroutines/channels/ReceiveOrClosed;", "Lkotlinx/coroutines/internal/RemoveFirstDesc;", "element", "queue", "Lkotlinx/coroutines/internal/LockFreeLinkedListHead;", "(Ljava/lang/Object;Lkotlinx/coroutines/internal/LockFreeLinkedListHead;)V", "Ljava/lang/Object;", "resumeToken", "", "failure", "affected", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "next", "validatePrepared", "", "node", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
    /* compiled from: AbstractChannel.kt */
    protected static final class TryOfferDesc<E> extends RemoveFirstDesc<ReceiveOrClosed<? super E>> {
        @JvmField
        public final E element;
        @Nullable
        @JvmField
        public Object resumeToken;

        public TryOfferDesc(E e, @NotNull LockFreeLinkedListHead lockFreeLinkedListHead) {
            Intrinsics.checkParameterIsNotNull(lockFreeLinkedListHead, "queue");
            super(lockFreeLinkedListHead);
            this.element = e;
        }

        @Nullable
        protected Object failure(@NotNull LockFreeLinkedListNode lockFreeLinkedListNode, @NotNull Object obj) {
            Intrinsics.checkParameterIsNotNull(lockFreeLinkedListNode, "affected");
            Intrinsics.checkParameterIsNotNull(obj, "next");
            if ((lockFreeLinkedListNode instanceof ReceiveOrClosed) == null) {
                return AbstractChannelKt.OFFER_FAILED;
            }
            return (lockFreeLinkedListNode instanceof Closed) != null ? lockFreeLinkedListNode : null;
        }

        protected boolean validatePrepared(@NotNull ReceiveOrClosed<? super E> receiveOrClosed) {
            Intrinsics.checkParameterIsNotNull(receiveOrClosed, "node");
            receiveOrClosed = receiveOrClosed.tryResumeReceive(this.element, this);
            if (receiveOrClosed == null) {
                return null;
            }
            this.resumeToken = receiveOrClosed;
            return true;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u0000*\u0004\b\u0001\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00028\u0001¢\u0006\u0002\u0010\u0006J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0014¨\u0006\f"}, d2 = {"Lkotlinx/coroutines/channels/AbstractSendChannel$SendConflatedDesc;", "E", "Lkotlinx/coroutines/channels/AbstractSendChannel$SendBufferedDesc;", "queue", "Lkotlinx/coroutines/internal/LockFreeLinkedListHead;", "element", "(Lkotlinx/coroutines/internal/LockFreeLinkedListHead;Ljava/lang/Object;)V", "finishOnSuccess", "", "affected", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "next", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
    /* compiled from: AbstractChannel.kt */
    private static final class SendConflatedDesc<E> extends SendBufferedDesc<E> {
        public SendConflatedDesc(@NotNull LockFreeLinkedListHead lockFreeLinkedListHead, E e) {
            Intrinsics.checkParameterIsNotNull(lockFreeLinkedListHead, "queue");
            super(lockFreeLinkedListHead, e);
        }

        protected void finishOnSuccess(@NotNull LockFreeLinkedListNode lockFreeLinkedListNode, @NotNull LockFreeLinkedListNode lockFreeLinkedListNode2) {
            Intrinsics.checkParameterIsNotNull(lockFreeLinkedListNode, "affected");
            Intrinsics.checkParameterIsNotNull(lockFreeLinkedListNode2, "next");
            super.finishOnSuccess(lockFreeLinkedListNode, lockFreeLinkedListNode2);
            if ((lockFreeLinkedListNode instanceof SendBuffered) == null) {
                lockFreeLinkedListNode = null;
            }
            SendBuffered sendBuffered = (SendBuffered) lockFreeLinkedListNode;
            if (sendBuffered != null) {
                sendBuffered.remove();
            }
        }
    }

    protected void afterClose(@Nullable Throwable th) {
    }

    @NotNull
    protected String getBufferDebugString() {
        return "";
    }

    protected abstract boolean isBufferAlwaysFull();

    protected abstract boolean isBufferFull();

    protected void onClosed(@NotNull Closed<? super E> closed) {
        Intrinsics.checkParameterIsNotNull(closed, "closed");
    }

    @NotNull
    protected final LockFreeLinkedListHead getQueue() {
        return this.queue;
    }

    @NotNull
    protected Object offerInternal(E e) {
        Object tryResumeReceive;
        ReceiveOrClosed takeFirstReceiveOrPeekClosed;
        do {
            takeFirstReceiveOrPeekClosed = takeFirstReceiveOrPeekClosed();
            if (takeFirstReceiveOrPeekClosed == null) {
                return AbstractChannelKt.OFFER_FAILED;
            }
            tryResumeReceive = takeFirstReceiveOrPeekClosed.tryResumeReceive(e, null);
        } while (tryResumeReceive == null);
        takeFirstReceiveOrPeekClosed.completeResumeReceive(tryResumeReceive);
        return takeFirstReceiveOrPeekClosed.getOfferResult();
    }

    @NotNull
    protected Object offerSelectInternal(E e, @NotNull SelectInstance<?> selectInstance) {
        Intrinsics.checkParameterIsNotNull(selectInstance, "select");
        e = describeTryOffer(e);
        selectInstance = selectInstance.performAtomicTrySelect((AtomicDesc) e);
        if (selectInstance != null) {
            return selectInstance;
        }
        ReceiveOrClosed receiveOrClosed = (ReceiveOrClosed) e.getResult();
        e = e.resumeToken;
        if (e == null) {
            Intrinsics.throwNpe();
        }
        receiveOrClosed.completeResumeReceive(e);
        return receiveOrClosed.getOfferResult();
    }

    @Nullable
    protected final Closed<?> getClosedForSend() {
        LockFreeLinkedListNode prevNode = this.queue.getPrevNode();
        if (!(prevNode instanceof Closed)) {
            prevNode = null;
        }
        Closed<?> closed = (Closed) prevNode;
        if (closed == null) {
            return null;
        }
        helpClose(closed);
        return closed;
    }

    @Nullable
    protected final Closed<?> getClosedForReceive() {
        LockFreeLinkedListNode nextNode = this.queue.getNextNode();
        if (!(nextNode instanceof Closed)) {
            nextNode = null;
        }
        Closed<?> closed = (Closed) nextNode;
        if (closed == null) {
            return null;
        }
        helpClose(closed);
        return closed;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @org.jetbrains.annotations.Nullable
    protected final kotlinx.coroutines.channels.Send takeFirstSendOrPeekClosed() {
        /*
        r4 = this;
        r0 = r4.queue;
    L_0x0002:
        r1 = r0.getNext();
        if (r1 == 0) goto L_0x002c;
    L_0x0008:
        r1 = (kotlinx.coroutines.internal.LockFreeLinkedListNode) r1;
        r2 = r0;
        r2 = (kotlinx.coroutines.internal.LockFreeLinkedListNode) r2;
        r3 = 0;
        if (r1 != r2) goto L_0x0011;
    L_0x0010:
        goto L_0x0025;
    L_0x0011:
        r2 = r1 instanceof kotlinx.coroutines.channels.Send;
        if (r2 != 0) goto L_0x0016;
    L_0x0015:
        goto L_0x0025;
    L_0x0016:
        r2 = r1;
        r2 = (kotlinx.coroutines.channels.Send) r2;
        r2 = r2 instanceof kotlinx.coroutines.channels.Closed;
        if (r2 == 0) goto L_0x001e;
    L_0x001d:
        goto L_0x0024;
    L_0x001e:
        r2 = r1.remove();
        if (r2 == 0) goto L_0x0028;
    L_0x0024:
        r3 = r1;
    L_0x0025:
        r3 = (kotlinx.coroutines.channels.Send) r3;
        return r3;
    L_0x0028:
        r1.helpDelete();
        goto L_0x0002;
    L_0x002c:
        r0 = new kotlin.TypeCastException;
        r1 = "null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */";
        r0.<init>(r1);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.AbstractSendChannel.takeFirstSendOrPeekClosed():kotlinx.coroutines.channels.Send");
    }

    @Nullable
    protected final ReceiveOrClosed<?> sendBuffered(E e) {
        LockFreeLinkedListNode lockFreeLinkedListNode = this.queue;
        LockFreeLinkedListNode sendBuffered = new SendBuffered(e);
        LockFreeLinkedListNode lockFreeLinkedListNode2;
        do {
            e = lockFreeLinkedListNode.getPrev();
            if (e != null) {
                lockFreeLinkedListNode2 = (LockFreeLinkedListNode) e;
                if (lockFreeLinkedListNode2 instanceof ReceiveOrClosed) {
                    return (ReceiveOrClosed) lockFreeLinkedListNode2;
                }
            } else {
                throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */");
            }
        } while (lockFreeLinkedListNode2.addNext(sendBuffered, lockFreeLinkedListNode) == null);
        return null;
    }

    @Nullable
    protected final ReceiveOrClosed<?> sendConflated(E e) {
        SendBuffered sendBuffered = new SendBuffered(e);
        e = this.queue;
        LockFreeLinkedListNode lockFreeLinkedListNode;
        do {
            Object prev = e.getPrev();
            if (prev != null) {
                lockFreeLinkedListNode = (LockFreeLinkedListNode) prev;
                if (lockFreeLinkedListNode instanceof ReceiveOrClosed) {
                    return (ReceiveOrClosed) lockFreeLinkedListNode;
                }
            } else {
                throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */");
            }
        } while (!lockFreeLinkedListNode.addNext(sendBuffered, e));
        conflatePreviousSendBuffered(sendBuffered);
        return null;
    }

    protected final void conflatePreviousSendBuffered(@NotNull LockFreeLinkedListNode lockFreeLinkedListNode) {
        Intrinsics.checkParameterIsNotNull(lockFreeLinkedListNode, "node");
        lockFreeLinkedListNode = lockFreeLinkedListNode.getPrevNode();
        if (!(lockFreeLinkedListNode instanceof SendBuffered)) {
            lockFreeLinkedListNode = null;
        }
        SendBuffered sendBuffered = (SendBuffered) lockFreeLinkedListNode;
        if (sendBuffered != null) {
            sendBuffered.remove();
        }
    }

    @NotNull
    protected final AddLastDesc<?> describeSendBuffered(E e) {
        return new SendBufferedDesc(this.queue, e);
    }

    @NotNull
    protected final AddLastDesc<?> describeSendConflated(E e) {
        return new SendConflatedDesc(this.queue, e);
    }

    public final boolean isClosedForSend() {
        return getClosedForSend() != null;
    }

    public final boolean isFull() {
        return !(this.queue.getNextNode() instanceof ReceiveOrClosed) && isBufferFull();
    }

    @Nullable
    public final Object send(E e, @NotNull Continuation<? super Unit> continuation) {
        if (offer(e)) {
            return Unit.INSTANCE;
        }
        return sendSuspend(e, continuation);
    }

    public final boolean offer(E e) {
        e = offerInternal(e);
        if (e == AbstractChannelKt.OFFER_SUCCESS) {
            return true;
        }
        if (e == AbstractChannelKt.OFFER_FAILED) {
            e = getClosedForSend();
            if (e != null) {
                e = e.getSendException();
                if (e != null) {
                    throw e;
                }
            }
            return null;
        } else if (e instanceof Closed) {
            throw ((Closed) e).getSendException();
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("offerInternal returned ");
            stringBuilder.append(e);
            throw new IllegalStateException(stringBuilder.toString().toString());
        }
    }

    private final Object enqueueSend(SendElement sendElement) {
        LockFreeLinkedListNode lockFreeLinkedListNode;
        if (isBufferAlwaysFull()) {
            lockFreeLinkedListNode = this.queue;
            LockFreeLinkedListNode lockFreeLinkedListNode2;
            do {
                Object prev = lockFreeLinkedListNode.getPrev();
                if (prev != null) {
                    lockFreeLinkedListNode2 = (LockFreeLinkedListNode) prev;
                    if (lockFreeLinkedListNode2 instanceof ReceiveOrClosed) {
                        return lockFreeLinkedListNode2;
                    }
                } else {
                    throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */");
                }
            } while (!lockFreeLinkedListNode2.addNext(sendElement, lockFreeLinkedListNode));
        }
        lockFreeLinkedListNode = this.queue;
        LockFreeLinkedListNode lockFreeLinkedListNode3 = sendElement;
        CondAddOp abstractSendChannel$enqueueSend$$inlined$addLastIfPrevAndIf$1 = new AbstractSendChannel$enqueueSend$$inlined$addLastIfPrevAndIf$1(lockFreeLinkedListNode3, lockFreeLinkedListNode3, this);
        while (true) {
            Object prev2 = lockFreeLinkedListNode.getPrev();
            if (prev2 != null) {
                LockFreeLinkedListNode lockFreeLinkedListNode4 = (LockFreeLinkedListNode) prev2;
                if (lockFreeLinkedListNode4 instanceof ReceiveOrClosed) {
                    return lockFreeLinkedListNode4;
                }
                switch (lockFreeLinkedListNode4.tryCondAddNext(lockFreeLinkedListNode3, lockFreeLinkedListNode, abstractSendChannel$enqueueSend$$inlined$addLastIfPrevAndIf$1)) {
                    case 1:
                        sendElement = true;
                        break;
                    case 2:
                        sendElement = null;
                        break;
                    default:
                }
                if (sendElement == null) {
                    return AbstractChannelKt.ENQUEUE_FAILED;
                }
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */");
        }
        return null;
    }

    public boolean close(@Nullable Throwable th) {
        Object obj;
        Closed closed = new Closed(th);
        LockFreeLinkedListNode lockFreeLinkedListNode = this.queue;
        LockFreeLinkedListNode lockFreeLinkedListNode2;
        do {
            Object prev = lockFreeLinkedListNode.getPrev();
            if (prev != null) {
                lockFreeLinkedListNode2 = (LockFreeLinkedListNode) prev;
                if ((!(lockFreeLinkedListNode2 instanceof Closed) ? 1 : null) == null) {
                    obj = null;
                    break;
                }
            } else {
                throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */");
            }
        } while (!lockFreeLinkedListNode2.addNext(closed, lockFreeLinkedListNode));
        obj = 1;
        if (obj == null) {
            th = this.queue.getPrevNode();
            if (th != null) {
                helpClose((Closed) th);
                return false;
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.channels.Closed<*>");
        }
        helpClose(closed);
        invokeOnCloseHandler(th);
        onClosed(closed);
        afterClose(th);
        return true;
    }

    private final void invokeOnCloseHandler(Throwable th) {
        Object obj = this.onCloseHandler;
        if (obj != null && obj != AbstractChannelKt.HANDLER_INVOKED && onCloseHandler$FU.compareAndSet(this, obj, AbstractChannelKt.HANDLER_INVOKED)) {
            ((Function1) TypeIntrinsics.beforeCheckcastToFunctionOfArity(obj, 1)).invoke(th);
        }
    }

    public void invokeOnClose(@NotNull Function1<? super Throwable, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(function1, "handler");
        if (onCloseHandler$FU.compareAndSet(this, null, function1)) {
            Closed closedForSend = getClosedForSend();
            if (closedForSend != null && onCloseHandler$FU.compareAndSet(this, function1, AbstractChannelKt.HANDLER_INVOKED)) {
                function1.invoke(closedForSend.closeCause);
                return;
            }
            return;
        }
        function1 = this.onCloseHandler;
        if (function1 == AbstractChannelKt.HANDLER_INVOKED) {
            throw ((Throwable) new IllegalStateException("Another handler was already registered and successfully invoked"));
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Another handler was already registered: ");
        stringBuilder.append(function1);
        throw new IllegalStateException(stringBuilder.toString());
    }

    private final void helpClose(Closed<?> closed) {
        while (true) {
            LockFreeLinkedListNode prevNode = closed.getPrevNode();
            if (!(prevNode instanceof LockFreeLinkedListHead)) {
                if (!(prevNode instanceof Receive)) {
                    return;
                }
                if (prevNode.remove()) {
                    ((Receive) prevNode).resumeReceiveClosed(closed);
                } else {
                    prevNode.helpRemove();
                }
            } else {
                return;
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @org.jetbrains.annotations.Nullable
    protected kotlinx.coroutines.channels.ReceiveOrClosed<E> takeFirstReceiveOrPeekClosed() {
        /*
        r4 = this;
        r0 = r4.queue;
    L_0x0002:
        r1 = r0.getNext();
        if (r1 == 0) goto L_0x002c;
    L_0x0008:
        r1 = (kotlinx.coroutines.internal.LockFreeLinkedListNode) r1;
        r2 = r0;
        r2 = (kotlinx.coroutines.internal.LockFreeLinkedListNode) r2;
        r3 = 0;
        if (r1 != r2) goto L_0x0011;
    L_0x0010:
        goto L_0x0025;
    L_0x0011:
        r2 = r1 instanceof kotlinx.coroutines.channels.ReceiveOrClosed;
        if (r2 != 0) goto L_0x0016;
    L_0x0015:
        goto L_0x0025;
    L_0x0016:
        r2 = r1;
        r2 = (kotlinx.coroutines.channels.ReceiveOrClosed) r2;
        r2 = r2 instanceof kotlinx.coroutines.channels.Closed;
        if (r2 == 0) goto L_0x001e;
    L_0x001d:
        goto L_0x0024;
    L_0x001e:
        r2 = r1.remove();
        if (r2 == 0) goto L_0x0028;
    L_0x0024:
        r3 = r1;
    L_0x0025:
        r3 = (kotlinx.coroutines.channels.ReceiveOrClosed) r3;
        return r3;
    L_0x0028:
        r1.helpDelete();
        goto L_0x0002;
    L_0x002c:
        r0 = new kotlin.TypeCastException;
        r1 = "null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */";
        r0.<init>(r1);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.AbstractSendChannel.takeFirstReceiveOrPeekClosed():kotlinx.coroutines.channels.ReceiveOrClosed<E>");
    }

    @NotNull
    protected final TryOfferDesc<E> describeTryOffer(E e) {
        return new TryOfferDesc(e, this.queue);
    }

    @NotNull
    public final SelectClause2<E, SendChannel<E>> getOnSend() {
        return new AbstractSendChannel$onSend$1(this);
    }

    private final <R> void registerSelectSend(SelectInstance<? super R> selectInstance, E e, Function2<? super SendChannel<? super E>, ? super Continuation<? super R>, ? extends Object> function2) {
        while (!selectInstance.isSelected()) {
            if (isFull()) {
                Object performAtomicIfNotSelected = selectInstance.performAtomicIfNotSelected(new TryEnqueueSendDesc(this, e, selectInstance, function2));
                if (performAtomicIfNotSelected != null && performAtomicIfNotSelected != SelectKt.getALREADY_SELECTED()) {
                    if (performAtomicIfNotSelected != AbstractChannelKt.ENQUEUE_FAILED) {
                        if ((performAtomicIfNotSelected instanceof Closed) != null) {
                            throw ((Closed) performAtomicIfNotSelected).getSendException();
                        }
                        selectInstance = new StringBuilder();
                        selectInstance.append("performAtomicIfNotSelected(TryEnqueueSendDesc) returned ");
                        selectInstance.append(performAtomicIfNotSelected);
                        throw ((Throwable) new IllegalStateException(selectInstance.toString().toString()));
                    }
                } else {
                    return;
                }
            }
            E offerSelectInternal = offerSelectInternal(e, selectInstance);
            if (offerSelectInternal != SelectKt.getALREADY_SELECTED()) {
                if (offerSelectInternal != AbstractChannelKt.OFFER_FAILED) {
                    if (offerSelectInternal == AbstractChannelKt.OFFER_SUCCESS) {
                        UndispatchedKt.startCoroutineUnintercepted(function2, this, selectInstance.getCompletion());
                        return;
                    } else if ((offerSelectInternal instanceof Closed) != null) {
                        throw ((Closed) offerSelectInternal).getSendException();
                    } else {
                        selectInstance = new StringBuilder();
                        selectInstance.append("offerSelectInternal returned ");
                        selectInstance.append(offerSelectInternal);
                        throw ((Throwable) new IllegalStateException(selectInstance.toString().toString()));
                    }
                }
            } else {
                return;
            }
        }
    }

    @NotNull
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(DebugKt.getClassSimpleName(this));
        stringBuilder.append('@');
        stringBuilder.append(DebugKt.getHexAddress(this));
        stringBuilder.append('{');
        stringBuilder.append(getQueueDebugStateString());
        stringBuilder.append('}');
        stringBuilder.append(getBufferDebugString());
        return stringBuilder.toString();
    }

    private final String getQueueDebugStateString() {
        LockFreeLinkedListNode nextNode = this.queue.getNextNode();
        if (nextNode == this.queue) {
            return "EmptyQueue";
        }
        String lockFreeLinkedListNode;
        if (nextNode instanceof Closed) {
            lockFreeLinkedListNode = nextNode.toString();
        } else if (nextNode instanceof Receive) {
            lockFreeLinkedListNode = "ReceiveQueued";
        } else if (nextNode instanceof Send) {
            lockFreeLinkedListNode = "SendQueued";
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("UNEXPECTED:");
            stringBuilder.append(nextNode);
            lockFreeLinkedListNode = stringBuilder.toString();
        }
        LockFreeLinkedListNode prevNode = this.queue.getPrevNode();
        if (prevNode != nextNode) {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append(lockFreeLinkedListNode);
            stringBuilder2.append(",queueSize=");
            stringBuilder2.append(countQueueSize());
            lockFreeLinkedListNode = stringBuilder2.toString();
            if (prevNode instanceof Closed) {
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append(lockFreeLinkedListNode);
                stringBuilder2.append(",closedForSend=");
                stringBuilder2.append(prevNode);
                lockFreeLinkedListNode = stringBuilder2.toString();
            }
        }
        return lockFreeLinkedListNode;
    }

    private final int countQueueSize() {
        Object obj = this.queue;
        Object next = obj.getNext();
        if (next != null) {
            int i = 0;
            for (next = (LockFreeLinkedListNode) next; (Intrinsics.areEqual(next, obj) ^ 1) != 0; next = next.getNextNode()) {
                if (next instanceof LockFreeLinkedListNode) {
                    i++;
                }
            }
            return i;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @org.jetbrains.annotations.Nullable
    final /* synthetic */ java.lang.Object sendSuspend(E r6, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r7) {
        /*
        r5 = this;
        r0 = new kotlinx.coroutines.CancellableContinuationImpl;
        r1 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt.intercepted(r7);
        r2 = 0;
        r0.<init>(r1, r2);
        r1 = r0;
        r1 = (kotlinx.coroutines.CancellableContinuation) r1;
        r2 = new kotlinx.coroutines.channels.SendElement;
        r2.<init>(r6, r1);
    L_0x0012:
        r3 = r5.enqueueSend(r2);
        if (r3 != 0) goto L_0x0021;
    L_0x0018:
        r1.initCancellability();
        r2 = (kotlinx.coroutines.internal.LockFreeLinkedListNode) r2;
        kotlinx.coroutines.CancellableContinuationKt.removeOnCancellation(r1, r2);
        goto L_0x0075;
    L_0x0021:
        r4 = r3 instanceof kotlinx.coroutines.channels.Closed;
        if (r4 == 0) goto L_0x003e;
    L_0x0025:
        r3 = (kotlinx.coroutines.channels.Closed) r3;
        r5.helpClose(r3);
        r1 = (kotlin.coroutines.Continuation) r1;
        r6 = r3.getSendException();
        r2 = kotlin.Result.Companion;
        r6 = kotlin.ResultKt.createFailure(r6);
        r6 = kotlin.Result.constructor-impl(r6);
        r1.resumeWith(r6);
        goto L_0x0075;
    L_0x003e:
        r3 = r5.offerInternal(r6);
        r4 = kotlinx.coroutines.channels.AbstractChannelKt.OFFER_SUCCESS;
        if (r3 != r4) goto L_0x0054;
    L_0x0046:
        r1 = (kotlin.coroutines.Continuation) r1;
        r6 = kotlin.Unit.INSTANCE;
        r2 = kotlin.Result.Companion;
        r6 = kotlin.Result.constructor-impl(r6);
        r1.resumeWith(r6);
        goto L_0x0075;
    L_0x0054:
        r4 = kotlinx.coroutines.channels.AbstractChannelKt.OFFER_FAILED;
        if (r3 != r4) goto L_0x0059;
    L_0x0058:
        goto L_0x0012;
    L_0x0059:
        r6 = r3 instanceof kotlinx.coroutines.channels.Closed;
        if (r6 == 0) goto L_0x0083;
    L_0x005d:
        r3 = (kotlinx.coroutines.channels.Closed) r3;
        r5.helpClose(r3);
        r1 = (kotlin.coroutines.Continuation) r1;
        r6 = r3.getSendException();
        r2 = kotlin.Result.Companion;
        r6 = kotlin.ResultKt.createFailure(r6);
        r6 = kotlin.Result.constructor-impl(r6);
        r1.resumeWith(r6);
    L_0x0075:
        r6 = r0.getResult();
        r0 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (r6 != r0) goto L_0x0082;
    L_0x007f:
        kotlin.coroutines.jvm.internal.DebugProbesKt.probeCoroutineSuspended(r7);
    L_0x0082:
        return r6;
    L_0x0083:
        r6 = new java.lang.StringBuilder;
        r6.<init>();
        r7 = "offerInternal returned ";
        r6.append(r7);
        r6.append(r3);
        r6 = r6.toString();
        r7 = new java.lang.IllegalStateException;
        r6 = r6.toString();
        r7.<init>(r6);
        r7 = (java.lang.Throwable) r7;
        throw r7;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.AbstractSendChannel.sendSuspend(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
