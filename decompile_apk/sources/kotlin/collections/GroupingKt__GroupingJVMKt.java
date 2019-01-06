package kotlin.collections;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.SinceKotlin;
import kotlin.TypeCastException;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref.IntRef;
import kotlin.jvm.internal.TypeIntrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0000\n\u0002\u0010$\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010&\n\u0000\u001a0\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00030\u0001\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0002*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00020\u0005H\u0007\u001aW\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\b0\u0007\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\t\"\u0004\b\u0002\u0010\b*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\t0\u00072\u001e\u0010\n\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\t0\f\u0012\u0004\u0012\u0002H\b0\u000bH\b¨\u0006\r"}, d2 = {"eachCount", "", "K", "", "T", "Lkotlin/collections/Grouping;", "mapValuesInPlace", "", "R", "V", "f", "Lkotlin/Function1;", "", "kotlin-stdlib"}, k = 5, mv = {1, 1, 13}, xi = 1, xs = "kotlin/collections/GroupingKt")
/* compiled from: GroupingJVM.kt */
class GroupingKt__GroupingJVMKt {
    @NotNull
    @SinceKotlin(version = "1.1")
    public static final <T, K> Map<K, Integer> eachCount(@NotNull Grouping<T, ? extends K> grouping) {
        Intrinsics.checkParameterIsNotNull(grouping, "receiver$0");
        Map linkedHashMap = new LinkedHashMap();
        Iterator sourceIterator = grouping.sourceIterator();
        while (sourceIterator.hasNext()) {
            Object keyOf = grouping.keyOf(sourceIterator.next());
            IntRef intRef = linkedHashMap.get(keyOf);
            Object obj = (intRef != null || linkedHashMap.containsKey(keyOf)) ? null : 1;
            if (obj != null) {
                intRef = new IntRef();
            }
            intRef = intRef;
            intRef.element++;
            linkedHashMap.put(keyOf, intRef);
        }
        grouping = linkedHashMap.entrySet().iterator();
        while (grouping.hasNext()) {
            Entry entry = (Entry) grouping.next();
            if (entry != null) {
                TypeIntrinsics.asMutableMapEntry(entry).setValue(Integer.valueOf(((IntRef) entry.getValue()).element));
            } else {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.MutableMap.MutableEntry<K, R>");
            }
        }
        return TypeIntrinsics.asMutableMap(linkedHashMap);
    }

    @PublishedApi
    @InlineOnly
    private static final <K, V, R> Map<K, R> mapValuesInPlace(@NotNull Map<K, V> map, Function1<? super Entry<? extends K, ? extends V>, ? extends R> function1) {
        for (Entry entry : map.entrySet()) {
            if (entry != null) {
                TypeIntrinsics.asMutableMapEntry(entry).setValue(function1.invoke(entry));
            } else {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.MutableMap.MutableEntry<K, R>");
            }
        }
        if (map != null) {
            return TypeIntrinsics.asMutableMap(map);
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.MutableMap<K, R>");
    }
}
