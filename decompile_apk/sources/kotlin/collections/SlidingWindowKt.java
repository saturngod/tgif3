package kotlin.collections;

import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010(\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\u0000\u001aH\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\b0\u00070\u0006\"\u0004\b\u0000\u0010\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\b0\u00062\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0000\u001aD\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\b0\u00070\u000e\"\u0004\b\u0000\u0010\b*\b\u0012\u0004\u0012\u0002H\b0\u000e2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0000¨\u0006\u000f"}, d2 = {"checkWindowSizeStep", "", "size", "", "step", "windowedIterator", "", "", "T", "iterator", "partialWindows", "", "reuseBuffer", "windowedSequence", "Lkotlin/sequences/Sequence;", "kotlin-stdlib"}, k = 2, mv = {1, 1, 13})
/* compiled from: SlidingWindow.kt */
public final class SlidingWindowKt {
    public static final void checkWindowSizeStep(int i, int i2) {
        Object obj = (i <= 0 || i2 <= 0) ? null : 1;
        if (obj == null) {
            if (i != i2) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Both size ");
                stringBuilder.append(i);
                stringBuilder.append(" and step ");
                stringBuilder.append(i2);
                stringBuilder.append(" must be greater than zero.");
                i = stringBuilder.toString();
            } else {
                i2 = new StringBuilder();
                i2.append("size ");
                i2.append(i);
                i2.append(" must be greater than zero.");
                i = i2.toString();
            }
            throw ((Throwable) new IllegalArgumentException(i.toString()));
        }
    }

    @NotNull
    public static final <T> Sequence<List<T>> windowedSequence(@NotNull Sequence<? extends T> sequence, int i, int i2, boolean z, boolean z2) {
        Intrinsics.checkParameterIsNotNull(sequence, "receiver$0");
        checkWindowSizeStep(i, i2);
        return new SlidingWindowKt$windowedSequence$$inlined$Sequence$1(sequence, i, i2, z, z2);
    }

    @NotNull
    public static final <T> Iterator<List<T>> windowedIterator(@NotNull Iterator<? extends T> it, int i, int i2, boolean z, boolean z2) {
        Intrinsics.checkParameterIsNotNull(it, "iterator");
        if (it.hasNext()) {
            return SequencesKt__SequenceBuilderKt.iterator(new SlidingWindowKt$windowedIterator$1(i2, i, it, z2, z, null));
        }
        return EmptyIterator.INSTANCE;
    }
}
