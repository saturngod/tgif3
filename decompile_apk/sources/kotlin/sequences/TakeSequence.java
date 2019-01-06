package kotlin.sequences;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010(\n\u0002\b\u0002\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u001b\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0016\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u00022\u0006\u0010\t\u001a\u00020\u0006H\u0016J\u000f\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\u000bH\u0002J\u0016\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\u00022\u0006\u0010\t\u001a\u00020\u0006H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002X\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lkotlin/sequences/TakeSequence;", "T", "Lkotlin/sequences/Sequence;", "Lkotlin/sequences/DropTakeSequence;", "sequence", "count", "", "(Lkotlin/sequences/Sequence;I)V", "drop", "n", "iterator", "", "take", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
/* compiled from: Sequences.kt */
public final class TakeSequence<T> implements Sequence<T>, DropTakeSequence<T> {
    private final int count;
    private final Sequence<T> sequence;

    public TakeSequence(@NotNull Sequence<? extends T> sequence, int i) {
        Intrinsics.checkParameterIsNotNull(sequence, "sequence");
        this.sequence = sequence;
        this.count = i;
        if ((this.count >= null ? true : null) == null) {
            sequence = new StringBuilder();
            sequence.append("count must be non-negative, but was ");
            sequence.append(this.count);
            sequence.append(46);
            throw ((Throwable) new IllegalArgumentException(sequence.toString().toString()));
        }
    }

    @NotNull
    public Sequence<T> drop(int i) {
        return i >= this.count ? SequencesKt__SequencesKt.emptySequence() : new SubSequence(this.sequence, i, this.count);
    }

    @NotNull
    public Sequence<T> take(int i) {
        return i >= this.count ? this : new TakeSequence(this.sequence, i);
    }

    @NotNull
    public Iterator<T> iterator() {
        return new TakeSequence$iterator$1(this);
    }
}
