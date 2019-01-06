package org.jetbrains.anko.collections;

import android.util.SparseIntArray;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.sequences.Sequence;
import org.jetbrains.annotations.NotNull;

@PublishedApi
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010(\n\u0002\b\u0002\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\bB\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u000f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u0007H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lorg/jetbrains/anko/collections/SparseIntArraySequence;", "Lkotlin/sequences/Sequence;", "", "a", "Landroid/util/SparseIntArray;", "(Landroid/util/SparseIntArray;)V", "iterator", "", "SparseIntArrayIterator", "commons-base_release"}, k = 1, mv = {1, 1, 13})
/* compiled from: Arrays.kt */
public final class SparseIntArraySequence implements Sequence<Integer> {
    /* renamed from: a */
    private final SparseIntArray f20a;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010(\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\t\u0010\u0006\u001a\u00020\u0007H\u0002J\u000e\u0010\b\u001a\u00020\u0002H\u0002¢\u0006\u0002\u0010\tR\u000e\u0010\u0004\u001a\u00020\u0002X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0002X\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lorg/jetbrains/anko/collections/SparseIntArraySequence$SparseIntArrayIterator;", "", "", "(Lorg/jetbrains/anko/collections/SparseIntArraySequence;)V", "index", "size", "hasNext", "", "next", "()Ljava/lang/Integer;", "commons-base_release"}, k = 1, mv = {1, 1, 13})
    /* compiled from: Arrays.kt */
    private final class SparseIntArrayIterator implements Iterator<Integer>, KMappedMarker {
        private int index;
        private final int size;

        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        public SparseIntArrayIterator() {
            this.size = SparseIntArraySequence.this.f20a.size();
        }

        public boolean hasNext() {
            return this.size > this.index;
        }

        @NotNull
        public Integer next() {
            if (SparseIntArraySequence.this.f20a.size() == this.size) {
                SparseIntArray access$getA$p = SparseIntArraySequence.this.f20a;
                int i = this.index;
                this.index = i + 1;
                return Integer.valueOf(access$getA$p.get(i));
            }
            throw new ConcurrentModificationException();
        }
    }

    public SparseIntArraySequence(@NotNull SparseIntArray sparseIntArray) {
        Intrinsics.checkParameterIsNotNull(sparseIntArray, "a");
        this.f20a = sparseIntArray;
    }

    @NotNull
    public Iterator<Integer> iterator() {
        return new SparseIntArrayIterator();
    }
}
