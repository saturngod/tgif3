package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.selects.SelectInstance;
import kotlinx.coroutines.selects.SelectKt;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0010\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0015\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00028\u0000H\u0014¢\u0006\u0002\u0010\rJ!\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00028\u00002\n\u0010\u000f\u001a\u0006\u0012\u0002\b\u00030\u0010H\u0014¢\u0006\u0002\u0010\u0011J\u0016\u0010\u0012\u001a\u00020\u00132\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00028\u00000\u0015H\u0014R\u0014\u0010\u0004\u001a\u00020\u00058DX\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u00058DX\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\u0006R\u0014\u0010\b\u001a\u00020\u00058DX\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\u0006R\u0014\u0010\t\u001a\u00020\u00058DX\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\u0006¨\u0006\u0016"}, d2 = {"Lkotlinx/coroutines/channels/ConflatedChannel;", "E", "Lkotlinx/coroutines/channels/AbstractChannel;", "()V", "isBufferAlwaysEmpty", "", "()Z", "isBufferAlwaysFull", "isBufferEmpty", "isBufferFull", "offerInternal", "", "element", "(Ljava/lang/Object;)Ljava/lang/Object;", "offerSelectInternal", "select", "Lkotlinx/coroutines/selects/SelectInstance;", "(Ljava/lang/Object;Lkotlinx/coroutines/selects/SelectInstance;)Ljava/lang/Object;", "onClosed", "", "closed", "Lkotlinx/coroutines/channels/Closed;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
/* compiled from: ConflatedChannel.kt */
public class ConflatedChannel<E> extends AbstractChannel<E> {
    protected final boolean isBufferAlwaysEmpty() {
        return true;
    }

    protected final boolean isBufferAlwaysFull() {
        return false;
    }

    protected final boolean isBufferEmpty() {
        return true;
    }

    protected final boolean isBufferFull() {
        return false;
    }

    protected void onClosed(@NotNull Closed<? super E> closed) {
        Intrinsics.checkParameterIsNotNull(closed, "closed");
        conflatePreviousSendBuffered(closed);
    }

    @NotNull
    protected Object offerInternal(E e) {
        ReceiveOrClosed sendConflated;
        do {
            Object offerInternal = super.offerInternal(e);
            if (offerInternal == AbstractChannelKt.OFFER_SUCCESS) {
                return AbstractChannelKt.OFFER_SUCCESS;
            }
            if (offerInternal == AbstractChannelKt.OFFER_FAILED) {
                sendConflated = sendConflated(e);
                if (sendConflated == null) {
                    return AbstractChannelKt.OFFER_SUCCESS;
                }
            } else if ((offerInternal instanceof Closed) != null) {
                return offerInternal;
            } else {
                e = new StringBuilder();
                e.append("Invalid offerInternal result ");
                e.append(offerInternal);
                throw new IllegalStateException(e.toString().toString());
            }
        } while (!(sendConflated instanceof Closed));
        return sendConflated;
    }

    @NotNull
    protected Object offerSelectInternal(E e, @NotNull SelectInstance<?> selectInstance) {
        Intrinsics.checkParameterIsNotNull(selectInstance, "select");
        while (true) {
            Object offerSelectInternal;
            if (getHasReceiveOrClosed()) {
                offerSelectInternal = super.offerSelectInternal(e, selectInstance);
            } else {
                offerSelectInternal = selectInstance.performAtomicTrySelect(describeSendConflated(e));
                if (offerSelectInternal == null) {
                    offerSelectInternal = AbstractChannelKt.OFFER_SUCCESS;
                }
            }
            if (offerSelectInternal != SelectKt.getALREADY_SELECTED()) {
                if (offerSelectInternal != AbstractChannelKt.OFFER_SUCCESS) {
                    if (offerSelectInternal != AbstractChannelKt.OFFER_FAILED) {
                        break;
                    }
                } else {
                    return AbstractChannelKt.OFFER_SUCCESS;
                }
            }
            return SelectKt.getALREADY_SELECTED();
        }
        if ((offerSelectInternal instanceof Closed) != null) {
            return offerSelectInternal;
        }
        e = new StringBuilder();
        e.append("Invalid result ");
        e.append(offerSelectInternal);
        throw ((Throwable) new IllegalStateException(e.toString().toString()));
    }
}
