package kotlin.collections;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.ArrayIteratorsKt;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lkotlin/collections/ShortIterator;", "invoke"}, k = 3, mv = {1, 1, 13})
/* compiled from: _Arrays.kt */
final class ArraysKt___ArraysKt$withIndex$3 extends Lambda implements Function0<ShortIterator> {
    final /* synthetic */ short[] $this_withIndex;

    ArraysKt___ArraysKt$withIndex$3(short[] sArr) {
        this.$this_withIndex = sArr;
        super(0);
    }

    @NotNull
    public final ShortIterator invoke() {
        return ArrayIteratorsKt.iterator(this.$this_withIndex);
    }
}
