package kotlinx.coroutines.channels;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.coroutines.Continuation;
import kotlin.jvm.JvmName;
import kotlinx.coroutines.ExperimentalCoroutinesApi;
import kotlinx.coroutines.ObsoleteCoroutinesApi;
import kotlinx.coroutines.selects.SelectClause1;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\bf\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\u00020\u0002J\b\u0010\u0011\u001a\u00020\u0012H&J\u0014\u0010\u0011\u001a\u00020\u00042\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0014H'J\r\u0010\u0015\u001a\u00020\u0004H\u0017¢\u0006\u0002\b\u0011J\u000f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00028\u00000\u0017H¦\u0002J\u000f\u0010\u0018\u001a\u0004\u0018\u00018\u0000H&¢\u0006\u0002\u0010\u0019J\u0011\u0010\u001a\u001a\u00028\u0000H¦@ø\u0001\u0000¢\u0006\u0002\u0010\u001bJ\u0013\u0010\u001c\u001a\u0004\u0018\u00018\u0000H§@ø\u0001\u0000¢\u0006\u0002\u0010\u001bR\u001a\u0010\u0003\u001a\u00020\u00048&X§\u0004¢\u0006\f\u0012\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0003\u0010\u0007R\u001a\u0010\b\u001a\u00020\u00048&X§\u0004¢\u0006\f\u0012\u0004\b\t\u0010\u0006\u001a\u0004\b\b\u0010\u0007R\u0018\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\u000bX¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\"\u0010\u000e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u000b8&X§\u0004¢\u0006\f\u0012\u0004\b\u000f\u0010\u0006\u001a\u0004\b\u0010\u0010\r\u0002\u0004\n\u0002\b\u0019¨\u0006\u001d"}, d2 = {"Lkotlinx/coroutines/channels/ReceiveChannel;", "E", "", "isClosedForReceive", "", "isClosedForReceive$annotations", "()V", "()Z", "isEmpty", "isEmpty$annotations", "onReceive", "Lkotlinx/coroutines/selects/SelectClause1;", "getOnReceive", "()Lkotlinx/coroutines/selects/SelectClause1;", "onReceiveOrNull", "onReceiveOrNull$annotations", "getOnReceiveOrNull", "cancel", "", "cause", "", "cancel0", "iterator", "Lkotlinx/coroutines/channels/ChannelIterator;", "poll", "()Ljava/lang/Object;", "receive", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "receiveOrNull", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
/* compiled from: Channel.kt */
public interface ReceiveChannel<E> {

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 13})
    /* compiled from: Channel.kt */
    public static final class DefaultImpls {
        @ExperimentalCoroutinesApi
        public static /* synthetic */ void isClosedForReceive$annotations() {
        }

        @ExperimentalCoroutinesApi
        public static /* synthetic */ void isEmpty$annotations() {
        }

        @ExperimentalCoroutinesApi
        public static /* synthetic */ void onReceiveOrNull$annotations() {
        }

        @Deprecated(level = DeprecationLevel.WARNING, message = "Use cancel without cause", replaceWith = @ReplaceWith(expression = "cancel()", imports = {}))
        @ObsoleteCoroutinesApi
        public static /* synthetic */ boolean cancel$default(ReceiveChannel receiveChannel, Throwable th, int i, Object obj) {
            if (obj == null) {
                if ((i & 1) != 0) {
                    th = null;
                }
                return receiveChannel.cancel(th);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: cancel");
        }
    }

    void cancel();

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Left here for binary compatibility")
    @JvmName(name = "cancel")
    /* renamed from: cancel */
    /* synthetic */ boolean m20cancel();

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use cancel without cause", replaceWith = @ReplaceWith(expression = "cancel()", imports = {}))
    @ObsoleteCoroutinesApi
    boolean cancel(@Nullable Throwable th);

    @NotNull
    SelectClause1<E> getOnReceive();

    @NotNull
    SelectClause1<E> getOnReceiveOrNull();

    boolean isClosedForReceive();

    boolean isEmpty();

    @NotNull
    ChannelIterator<E> iterator();

    @Nullable
    E poll();

    @Nullable
    Object receive(@NotNull Continuation<? super E> continuation);

    @Nullable
    @ObsoleteCoroutinesApi
    Object receiveOrNull(@NotNull Continuation<? super E> continuation);
}
