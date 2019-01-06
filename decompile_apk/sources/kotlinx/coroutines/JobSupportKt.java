package kotlinx.coroutines;

import kotlin.Metadata;
import kotlinx.coroutines.internal.Symbol;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0007\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\b\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\t\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\f\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"COMPLETING_ALREADY_COMPLETING", "", "COMPLETING_COMPLETED", "COMPLETING_RETRY", "COMPLETING_WAITING_CHILDREN", "EMPTY_ACTIVE", "Lkotlinx/coroutines/Empty;", "EMPTY_NEW", "FALSE", "RETRY", "SEALED", "Lkotlinx/coroutines/internal/Symbol;", "TRUE", "kotlinx-coroutines-core"}, k = 2, mv = {1, 1, 13})
/* compiled from: JobSupport.kt */
public final class JobSupportKt {
    private static final int COMPLETING_ALREADY_COMPLETING = 0;
    private static final int COMPLETING_COMPLETED = 1;
    private static final int COMPLETING_RETRY = 3;
    private static final int COMPLETING_WAITING_CHILDREN = 2;
    private static final Empty EMPTY_ACTIVE = new Empty(true);
    private static final Empty EMPTY_NEW = new Empty(false);
    private static final int FALSE = 0;
    private static final int RETRY = -1;
    private static final Symbol SEALED = new Symbol("SEALED");
    private static final int TRUE = 1;
}
