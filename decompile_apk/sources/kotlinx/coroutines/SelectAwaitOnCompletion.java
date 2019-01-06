package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.selects.SelectInstance;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\b\u0012\u0004\u0012\u00020\u00040\u0003BB\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00010\u0007\u0012\"\u0010\b\u001a\u001e\b\u0001\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\tø\u0001\u0000¢\u0006\u0002\u0010\fJ\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0002J\b\u0010\u0012\u001a\u00020\u0013H\u0016R/\u0010\b\u001a\u001e\b\u0001\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\tX\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\rR\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00010\u0007X\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u0014"}, d2 = {"Lkotlinx/coroutines/SelectAwaitOnCompletion;", "T", "R", "Lkotlinx/coroutines/JobNode;", "Lkotlinx/coroutines/JobSupport;", "job", "select", "Lkotlinx/coroutines/selects/SelectInstance;", "block", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "", "(Lkotlinx/coroutines/JobSupport;Lkotlinx/coroutines/selects/SelectInstance;Lkotlin/jvm/functions/Function2;)V", "Lkotlin/jvm/functions/Function2;", "invoke", "", "cause", "", "toString", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
/* compiled from: JobSupport.kt */
final class SelectAwaitOnCompletion<T, R> extends JobNode<JobSupport> {
    private final Function2<T, Continuation<? super R>, Object> block;
    private final SelectInstance<R> select;

    public SelectAwaitOnCompletion(@NotNull JobSupport jobSupport, @NotNull SelectInstance<? super R> selectInstance, @NotNull Function2<? super T, ? super Continuation<? super R>, ? extends Object> function2) {
        Intrinsics.checkParameterIsNotNull(jobSupport, "job");
        Intrinsics.checkParameterIsNotNull(selectInstance, "select");
        Intrinsics.checkParameterIsNotNull(function2, "block");
        super(jobSupport);
        this.select = selectInstance;
        this.block = function2;
    }

    public void invoke(@Nullable Throwable th) {
        if (this.select.trySelect(null) != null) {
            ((JobSupport) this.job).selectAwaitCompletion$kotlinx_coroutines_core(this.select, this.block);
        }
    }

    @NotNull
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SelectAwaitOnCompletion[");
        stringBuilder.append(this.select);
        stringBuilder.append(']');
        return stringBuilder.toString();
    }
}
