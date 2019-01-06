package kotlin.sequences;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010(\n\u0002\b\u0002\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B#\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0002\u0010\bJ\u0016\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\u00022\u0006\u0010\r\u001a\u00020\u0006H\u0016J\u000f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\u000fH\u0002J\u0016\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00000\u00022\u0006\u0010\r\u001a\u00020\u0006H\u0016R\u0014\u0010\t\u001a\u00020\u00068BX\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\u0007\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lkotlin/sequences/SubSequence;", "T", "Lkotlin/sequences/Sequence;", "Lkotlin/sequences/DropTakeSequence;", "sequence", "startIndex", "", "endIndex", "(Lkotlin/sequences/Sequence;II)V", "count", "getCount", "()I", "drop", "n", "iterator", "", "take", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
/* compiled from: Sequences.kt */
public final class SubSequence<T> implements Sequence<T>, DropTakeSequence<T> {
    private final int endIndex;
    private final Sequence<T> sequence;
    private final int startIndex;

    public SubSequence(@NotNull Sequence<? extends T> sequence, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(sequence, "sequence");
        this.sequence = sequence;
        this.startIndex = i;
        this.endIndex = i2;
        i = 0;
        if ((this.startIndex >= null ? true : null) != null) {
            if ((this.endIndex >= null ? true : null) != null) {
                if (this.endIndex >= this.startIndex) {
                    i = 1;
                }
                if (i == 0) {
                    sequence = new StringBuilder();
                    sequence.append("endIndex should be not less than startIndex, but was ");
                    sequence.append(this.endIndex);
                    sequence.append(" < ");
                    sequence.append(this.startIndex);
                    throw ((Throwable) new IllegalArgumentException(sequence.toString().toString()));
                }
                return;
            }
            sequence = new StringBuilder();
            sequence.append("endIndex should be non-negative, but is ");
            sequence.append(this.endIndex);
            throw ((Throwable) new IllegalArgumentException(sequence.toString().toString()));
        }
        sequence = new StringBuilder();
        sequence.append("startIndex should be non-negative, but is ");
        sequence.append(this.startIndex);
        throw ((Throwable) new IllegalArgumentException(sequence.toString().toString()));
    }

    private final int getCount() {
        return this.endIndex - this.startIndex;
    }

    @NotNull
    public Sequence<T> drop(int i) {
        return i >= getCount() ? SequencesKt__SequencesKt.emptySequence() : new SubSequence(this.sequence, this.startIndex + i, this.endIndex);
    }

    @NotNull
    public Sequence<T> take(int i) {
        return i >= getCount() ? this : new SubSequence(this.sequence, this.startIndex, this.startIndex + i);
    }

    @NotNull
    public Iterator<T> iterator() {
        return new SubSequence$iterator$1(this);
    }
}
