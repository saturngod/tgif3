package kotlin.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.SinceKotlin;
import kotlin.Unit;
import kotlin.internal.HidesMembers;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000h\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010&\n\u0002\b\u0002\n\u0002\u0010\u001c\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010\u001f\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000f\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\u001aG\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010\u0005\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u00020\u00010\u0006H\b\u001a$\u0010\b\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0004\u001aG\u0010\b\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010\u0005\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u00020\u00010\u0006H\b\u001a9\u0010\t\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00070\n\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0004H\b\u001a6\u0010\u000b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00070\f\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0004\u001a'\u0010\r\u001a\u00020\u000e\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0004H\b\u001aG\u0010\r\u001a\u00020\u000e\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010\u0005\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u00020\u00010\u0006H\b\u001aY\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u00110\u0010\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0011*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042$\u0010\u0012\u001a \u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00110\n0\u0006H\b\u001ar\u0010\u0013\u001a\u0002H\u0014\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0011\"\u0010\b\u0003\u0010\u0014*\n\u0012\u0006\b\u0000\u0012\u0002H\u00110\u0015*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u0006\u0010\u0016\u001a\u0002H\u00142$\u0010\u0012\u001a \u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00110\n0\u0006H\b¢\u0006\u0002\u0010\u0017\u001aG\u0010\u0018\u001a\u00020\u0019\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010\u001a\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u00020\u00190\u0006H\b\u001aS\u0010\u001b\u001a\b\u0012\u0004\u0012\u0002H\u00110\u0010\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0011*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010\u0012\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u0002H\u00110\u0006H\b\u001aY\u0010\u001c\u001a\b\u0012\u0004\u0012\u0002H\u00110\u0010\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\b\b\u0002\u0010\u0011*\u00020\u001d*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042 \u0010\u0012\u001a\u001c\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0006\u0012\u0004\u0018\u0001H\u00110\u0006H\b\u001ar\u0010\u001e\u001a\u0002H\u0014\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\b\b\u0002\u0010\u0011*\u00020\u001d\"\u0010\b\u0003\u0010\u0014*\n\u0012\u0006\b\u0000\u0012\u0002H\u00110\u0015*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u0006\u0010\u0016\u001a\u0002H\u00142 \u0010\u0012\u001a\u001c\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0006\u0012\u0004\u0018\u0001H\u00110\u0006H\b¢\u0006\u0002\u0010\u0017\u001al\u0010\u001f\u001a\u0002H\u0014\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0011\"\u0010\b\u0003\u0010\u0014*\n\u0012\u0006\b\u0000\u0012\u0002H\u00110\u0015*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u0006\u0010\u0016\u001a\u0002H\u00142\u001e\u0010\u0012\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u0002H\u00110\u0006H\b¢\u0006\u0002\u0010\u0017\u001ae\u0010 \u001a\u0010\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0003\u0018\u00010\u0007\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u000e\b\u0002\u0010\u0011*\b\u0012\u0004\u0012\u0002H\u00110!*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010\"\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u0002H\u00110\u0006H\b\u001ai\u0010#\u001a\u0010\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0003\u0018\u00010\u0007\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u000422\u0010$\u001a.\u0012\u0012\b\u0000\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00070%j\u0016\u0012\u0012\b\u0000\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007`&H\b\u001ae\u0010'\u001a\u0010\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0003\u0018\u00010\u0007\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u000e\b\u0002\u0010\u0011*\b\u0012\u0004\u0012\u0002H\u00110!*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010\"\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u0002H\u00110\u0006H\b\u001af\u0010(\u001a\u0010\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0003\u0018\u00010\u0007\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u000422\u0010$\u001a.\u0012\u0012\b\u0000\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00070%j\u0016\u0012\u0012\b\u0000\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007`&\u001a$\u0010)\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0004\u001aG\u0010)\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010\u0005\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u00020\u00010\u0006H\b\u001aV\u0010*\u001a\u0002H+\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0016\b\u0002\u0010+*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0004*\u0002H+2\u001e\u0010\u001a\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u00020\u00190\u0006H\b¢\u0006\u0002\u0010,\u001a6\u0010-\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030.0\u0010\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0004¨\u0006/"}, d2 = {"all", "", "K", "V", "", "predicate", "Lkotlin/Function1;", "", "any", "asIterable", "", "asSequence", "Lkotlin/sequences/Sequence;", "count", "", "flatMap", "", "R", "transform", "flatMapTo", "C", "", "destination", "(Ljava/util/Map;Ljava/util/Collection;Lkotlin/jvm/functions/Function1;)Ljava/util/Collection;", "forEach", "", "action", "map", "mapNotNull", "", "mapNotNullTo", "mapTo", "maxBy", "", "selector", "maxWith", "comparator", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "minBy", "minWith", "none", "onEach", "M", "(Ljava/util/Map;Lkotlin/jvm/functions/Function1;)Ljava/util/Map;", "toList", "Lkotlin/Pair;", "kotlin-stdlib"}, k = 5, mv = {1, 1, 13}, xi = 1, xs = "kotlin/collections/MapsKt")
/* compiled from: _Maps.kt */
class MapsKt___MapsKt extends MapsKt__MapsKt {
    @NotNull
    public static final <K, V> List<Pair<K, V>> toList(@NotNull Map<? extends K, ? extends V> map) {
        Intrinsics.checkParameterIsNotNull(map, "receiver$0");
        if (map.size() == 0) {
            return CollectionsKt__CollectionsKt.emptyList();
        }
        Iterator it = map.entrySet().iterator();
        if (!it.hasNext()) {
            return CollectionsKt__CollectionsKt.emptyList();
        }
        Entry entry = (Entry) it.next();
        if (!it.hasNext()) {
            return CollectionsKt__CollectionsJVMKt.listOf(new Pair(entry.getKey(), entry.getValue()));
        }
        ArrayList arrayList = new ArrayList(map.size());
        arrayList.add(new Pair(entry.getKey(), entry.getValue()));
        do {
            Entry entry2 = (Entry) it.next();
            arrayList.add(new Pair(entry2.getKey(), entry2.getValue()));
        } while (it.hasNext() != null);
        return arrayList;
    }

    @NotNull
    public static final <K, V, R> List<R> flatMap(@NotNull Map<? extends K, ? extends V> map, @NotNull Function1<? super Entry<? extends K, ? extends V>, ? extends Iterable<? extends R>> function1) {
        Intrinsics.checkParameterIsNotNull(map, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "transform");
        Collection arrayList = new ArrayList();
        map = map.entrySet().iterator();
        while (map.hasNext()) {
            CollectionsKt__MutableCollectionsKt.addAll(arrayList, (Iterable) function1.invoke((Entry) map.next()));
        }
        return (List) arrayList;
    }

    @NotNull
    public static final <K, V, R, C extends Collection<? super R>> C flatMapTo(@NotNull Map<? extends K, ? extends V> map, @NotNull C c, @NotNull Function1<? super Entry<? extends K, ? extends V>, ? extends Iterable<? extends R>> function1) {
        Intrinsics.checkParameterIsNotNull(map, "receiver$0");
        Intrinsics.checkParameterIsNotNull(c, "destination");
        Intrinsics.checkParameterIsNotNull(function1, "transform");
        map = map.entrySet().iterator();
        while (map.hasNext()) {
            CollectionsKt__MutableCollectionsKt.addAll((Collection) c, (Iterable) function1.invoke((Entry) map.next()));
        }
        return c;
    }

    @NotNull
    public static final <K, V, R> List<R> map(@NotNull Map<? extends K, ? extends V> map, @NotNull Function1<? super Entry<? extends K, ? extends V>, ? extends R> function1) {
        Intrinsics.checkParameterIsNotNull(map, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "transform");
        Collection arrayList = new ArrayList(map.size());
        map = map.entrySet().iterator();
        while (map.hasNext()) {
            arrayList.add(function1.invoke((Entry) map.next()));
        }
        return (List) arrayList;
    }

    @NotNull
    public static final <K, V, R> List<R> mapNotNull(@NotNull Map<? extends K, ? extends V> map, @NotNull Function1<? super Entry<? extends K, ? extends V>, ? extends R> function1) {
        Intrinsics.checkParameterIsNotNull(map, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "transform");
        Collection arrayList = new ArrayList();
        map = map.entrySet().iterator();
        while (map.hasNext()) {
            Object invoke = function1.invoke((Entry) map.next());
            if (invoke != null) {
                arrayList.add(invoke);
            }
        }
        return (List) arrayList;
    }

    @NotNull
    public static final <K, V, R, C extends Collection<? super R>> C mapTo(@NotNull Map<? extends K, ? extends V> map, @NotNull C c, @NotNull Function1<? super Entry<? extends K, ? extends V>, ? extends R> function1) {
        Intrinsics.checkParameterIsNotNull(map, "receiver$0");
        Intrinsics.checkParameterIsNotNull(c, "destination");
        Intrinsics.checkParameterIsNotNull(function1, "transform");
        map = map.entrySet().iterator();
        while (map.hasNext()) {
            c.add(function1.invoke((Entry) map.next()));
        }
        return c;
    }

    public static final <K, V> boolean all(@NotNull Map<? extends K, ? extends V> map, @NotNull Function1<? super Entry<? extends K, ? extends V>, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(map, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "predicate");
        if (map.isEmpty()) {
            return true;
        }
        map = map.entrySet().iterator();
        while (map.hasNext()) {
            if (!((Boolean) function1.invoke((Entry) map.next())).booleanValue()) {
                return null;
            }
        }
        return true;
    }

    public static final <K, V> boolean any(@NotNull Map<? extends K, ? extends V> map) {
        Intrinsics.checkParameterIsNotNull(map, "receiver$0");
        return map.isEmpty() ^ 1;
    }

    public static final <K, V> boolean any(@NotNull Map<? extends K, ? extends V> map, @NotNull Function1<? super Entry<? extends K, ? extends V>, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(map, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "predicate");
        if (map.isEmpty()) {
            return false;
        }
        map = map.entrySet().iterator();
        while (map.hasNext()) {
            if (((Boolean) function1.invoke((Entry) map.next())).booleanValue()) {
                return true;
            }
        }
        return false;
    }

    @InlineOnly
    private static final <K, V> int count(@NotNull Map<? extends K, ? extends V> map) {
        return map.size();
    }

    public static final <K, V> int count(@NotNull Map<? extends K, ? extends V> map, @NotNull Function1<? super Entry<? extends K, ? extends V>, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(map, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "predicate");
        int i = 0;
        if (map.isEmpty()) {
            return 0;
        }
        map = map.entrySet().iterator();
        while (map.hasNext()) {
            if (((Boolean) function1.invoke((Entry) map.next())).booleanValue()) {
                i++;
            }
        }
        return i;
    }

    @HidesMembers
    public static final <K, V> void forEach(@NotNull Map<? extends K, ? extends V> map, @NotNull Function1<? super Entry<? extends K, ? extends V>, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(map, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "action");
        map = map.entrySet().iterator();
        while (map.hasNext()) {
            function1.invoke((Entry) map.next());
        }
    }

    @InlineOnly
    private static final <K, V, R extends Comparable<? super R>> Entry<K, V> maxBy(@NotNull Map<? extends K, ? extends V> map, Function1<? super Entry<? extends K, ? extends V>, ? extends R> function1) {
        map = map.entrySet().iterator();
        if (map.hasNext()) {
            Object next = map.next();
            Comparable comparable = (Comparable) function1.invoke(next);
            while (map.hasNext()) {
                Object next2 = map.next();
                Comparable comparable2 = (Comparable) function1.invoke(next2);
                if (comparable.compareTo(comparable2) < 0) {
                    next = next2;
                    comparable = comparable2;
                }
            }
            map = next;
        } else {
            map = null;
        }
        return (Entry) map;
    }

    @InlineOnly
    private static final <K, V> Entry<K, V> maxWith(@NotNull Map<? extends K, ? extends V> map, Comparator<? super Entry<? extends K, ? extends V>> comparator) {
        return (Entry) CollectionsKt___CollectionsKt.maxWith(map.entrySet(), comparator);
    }

    @Nullable
    public static final <K, V, R extends Comparable<? super R>> Entry<K, V> minBy(@NotNull Map<? extends K, ? extends V> map, @NotNull Function1<? super Entry<? extends K, ? extends V>, ? extends R> function1) {
        Intrinsics.checkParameterIsNotNull(map, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "selector");
        map = map.entrySet().iterator();
        if (map.hasNext()) {
            Object next = map.next();
            Comparable comparable = (Comparable) function1.invoke(next);
            while (map.hasNext()) {
                Object next2 = map.next();
                Comparable comparable2 = (Comparable) function1.invoke(next2);
                if (comparable.compareTo(comparable2) > 0) {
                    next = next2;
                    comparable = comparable2;
                }
            }
            map = next;
        } else {
            map = null;
        }
        return (Entry) map;
    }

    @Nullable
    public static final <K, V> Entry<K, V> minWith(@NotNull Map<? extends K, ? extends V> map, @NotNull Comparator<? super Entry<? extends K, ? extends V>> comparator) {
        Intrinsics.checkParameterIsNotNull(map, "receiver$0");
        Intrinsics.checkParameterIsNotNull(comparator, "comparator");
        return (Entry) CollectionsKt___CollectionsKt.minWith(map.entrySet(), comparator);
    }

    public static final <K, V> boolean none(@NotNull Map<? extends K, ? extends V> map) {
        Intrinsics.checkParameterIsNotNull(map, "receiver$0");
        return map.isEmpty();
    }

    public static final <K, V> boolean none(@NotNull Map<? extends K, ? extends V> map, @NotNull Function1<? super Entry<? extends K, ? extends V>, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(map, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "predicate");
        if (map.isEmpty()) {
            return true;
        }
        map = map.entrySet().iterator();
        while (map.hasNext()) {
            if (((Boolean) function1.invoke((Entry) map.next())).booleanValue()) {
                return null;
            }
        }
        return true;
    }

    @NotNull
    @SinceKotlin(version = "1.1")
    public static final <K, V, M extends Map<? extends K, ? extends V>> M onEach(@NotNull M m, @NotNull Function1<? super Entry<? extends K, ? extends V>, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(m, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "action");
        for (Entry invoke : m.entrySet()) {
            function1.invoke(invoke);
        }
        return m;
    }

    @InlineOnly
    private static final <K, V> Iterable<Entry<K, V>> asIterable(@NotNull Map<? extends K, ? extends V> map) {
        return map.entrySet();
    }

    @NotNull
    public static final <K, V> Sequence<Entry<K, V>> asSequence(@NotNull Map<? extends K, ? extends V> map) {
        Intrinsics.checkParameterIsNotNull(map, "receiver$0");
        return CollectionsKt___CollectionsKt.asSequence(map.entrySet());
    }

    @NotNull
    public static final <K, V, R, C extends Collection<? super R>> C mapNotNullTo(@NotNull Map<? extends K, ? extends V> map, @NotNull C c, @NotNull Function1<? super Entry<? extends K, ? extends V>, ? extends R> function1) {
        Intrinsics.checkParameterIsNotNull(map, "receiver$0");
        Intrinsics.checkParameterIsNotNull(c, "destination");
        Intrinsics.checkParameterIsNotNull(function1, "transform");
        map = map.entrySet().iterator();
        while (map.hasNext()) {
            Object invoke = function1.invoke((Entry) map.next());
            if (invoke != null) {
                c.add(invoke);
            }
        }
        return c;
    }
}
