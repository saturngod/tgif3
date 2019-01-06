package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlinx.coroutines.internal.Symbol;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u0000\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0010\u0002\n\u0000\"\u0010\u0010\u0000\u001a\u00020\u00018\u0000X\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010\u0002\u001a\u00020\u00018\u0000X\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010\u0003\u001a\u00020\u00018\u0000X\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010\u0004\u001a\u00020\u00018\u0000X\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010\u0005\u001a\u00020\u00018\u0000X\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010\u0006\u001a\u00020\u00018\u0000X\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010\u0007\u001a\u00020\u00018\u0000X\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010\b\u001a\u00020\u00018\u0000X\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010\t\u001a\u00020\n8\u0000X\u0004¢\u0006\u0002\n\u0000*(\b\u0000\u0010\u000b\"\u0010\u0012\u0006\u0012\u0004\u0018\u00010\r\u0012\u0004\u0012\u00020\u000e0\f2\u0010\u0012\u0006\u0012\u0004\u0018\u00010\r\u0012\u0004\u0012\u00020\u000e0\f¨\u0006\u000f"}, d2 = {"CLOSE_RESUMED", "", "ENQUEUE_FAILED", "HANDLER_INVOKED", "NULL_VALUE", "OFFER_FAILED", "OFFER_SUCCESS", "POLL_FAILED", "SELECT_STARTED", "SEND_RESUMED", "Lkotlinx/coroutines/internal/Symbol;", "Handler", "Lkotlin/Function1;", "", "", "kotlinx-coroutines-core"}, k = 2, mv = {1, 1, 13})
/* compiled from: AbstractChannel.kt */
public final class AbstractChannelKt {
    @NotNull
    @JvmField
    public static final Object CLOSE_RESUMED = new Symbol("CLOSE_RESUMED");
    @NotNull
    @JvmField
    public static final Object ENQUEUE_FAILED = new Symbol("ENQUEUE_FAILED");
    @NotNull
    @JvmField
    public static final Object HANDLER_INVOKED = new Object();
    @NotNull
    @JvmField
    public static final Object NULL_VALUE = new Symbol("NULL_VALUE");
    @NotNull
    @JvmField
    public static final Object OFFER_FAILED = new Symbol("OFFER_FAILED");
    @NotNull
    @JvmField
    public static final Object OFFER_SUCCESS = new Symbol("OFFER_SUCCESS");
    @NotNull
    @JvmField
    public static final Object POLL_FAILED = new Symbol("POLL_FAILED");
    @NotNull
    @JvmField
    public static final Object SELECT_STARTED = new Symbol("SELECT_STARTED");
    @NotNull
    @JvmField
    public static final Symbol SEND_RESUMED = new Symbol("SEND_RESUMED");
}
