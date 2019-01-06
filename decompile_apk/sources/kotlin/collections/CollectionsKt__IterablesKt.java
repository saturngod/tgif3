package kotlin.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.PublishedApi;
import kotlin.TuplesKt;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0000\n\u0002\u0010\u001c\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010(\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u001e\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a+\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0014\b\u0004\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00050\u0004H\b\u001a \u0010\u0006\u001a\u00020\u0007\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\b\u001a\u00020\u0007H\u0001\u001a\u001f\u0010\t\u001a\u0004\u0018\u00010\u0007\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0001H\u0001¢\u0006\u0002\u0010\n\u001a\u001e\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\u00020\f\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0001H\u0000\u001a,\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\u00020\f\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001H\u0000\u001a\"\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0010\"\u0004\b\u0000\u0010\u0002*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00010\u0001\u001a\u001d\u0010\u0011\u001a\u00020\u0012\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\fH\u0002¢\u0006\u0002\b\u0013\u001a@\u0010\u0014\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00160\u00100\u0015\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0016*\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00160\u00150\u0001¨\u0006\u0017"}, d2 = {"Iterable", "", "T", "iterator", "Lkotlin/Function0;", "", "collectionSizeOrDefault", "", "default", "collectionSizeOrNull", "(Ljava/lang/Iterable;)Ljava/lang/Integer;", "convertToSetForSetOperation", "", "convertToSetForSetOperationWith", "source", "flatten", "", "safeToConvertToSet", "", "safeToConvertToSet$CollectionsKt__IterablesKt", "unzip", "Lkotlin/Pair;", "R", "kotlin-stdlib"}, k = 5, mv = {1, 1, 13}, xi = 1, xs = "kotlin/collections/CollectionsKt")
/* compiled from: Iterables.kt */
class CollectionsKt__IterablesKt extends CollectionsKt__CollectionsKt {
    @InlineOnly
    private static final <T> Iterable<T> Iterable(Function0<? extends Iterator<? extends T>> function0) {
        return new CollectionsKt__IterablesKt$Iterable$1(function0);
    }

    @Nullable
    @PublishedApi
    public static final <T> Integer collectionSizeOrNull(@NotNull Iterable<? extends T> iterable) {
        Intrinsics.checkParameterIsNotNull(iterable, "receiver$0");
        return iterable instanceof Collection ? Integer.valueOf(((Collection) iterable).size()) : null;
    }

    @PublishedApi
    public static final <T> int collectionSizeOrDefault(@NotNull Iterable<? extends T> iterable, int i) {
        Intrinsics.checkParameterIsNotNull(iterable, "receiver$0");
        return iterable instanceof Collection ? ((Collection) iterable).size() : i;
    }

    private static final <T> boolean safeToConvertToSet$CollectionsKt__IterablesKt(@NotNull Collection<? extends T> collection) {
        return (collection.size() <= 2 || (collection instanceof ArrayList) == null) ? null : true;
    }

    @NotNull
    public static final <T> Collection<T> convertToSetForSetOperationWith(@NotNull Iterable<? extends T> iterable, @NotNull Iterable<? extends T> iterable2) {
        Intrinsics.checkParameterIsNotNull(iterable, "receiver$0");
        Intrinsics.checkParameterIsNotNull(iterable2, "source");
        if (iterable instanceof Set) {
            return (Collection) iterable;
        }
        if (!(iterable instanceof Collection)) {
            return CollectionsKt___CollectionsKt.toHashSet(iterable);
        }
        if ((iterable2 instanceof Collection) && ((Collection) iterable2).size() < 2) {
            return (Collection) iterable;
        }
        Collection collection = (Collection) iterable;
        return safeToConvertToSet$CollectionsKt__IterablesKt(collection) ? CollectionsKt___CollectionsKt.toHashSet(iterable) : collection;
    }

    @NotNull
    public static final <T> Collection<T> convertToSetForSetOperation(@NotNull Iterable<? extends T> iterable) {
        Intrinsics.checkParameterIsNotNull(iterable, "receiver$0");
        if (iterable instanceof Set) {
            return (Collection) iterable;
        }
        if (!(iterable instanceof Collection)) {
            return CollectionsKt___CollectionsKt.toHashSet(iterable);
        }
        Collection collection = (Collection) iterable;
        return safeToConvertToSet$CollectionsKt__IterablesKt(collection) ? CollectionsKt___CollectionsKt.toHashSet(iterable) : collection;
    }

    @NotNull
    public static final <T> List<T> flatten(@NotNull Iterable<? extends Iterable<? extends T>> iterable) {
        Intrinsics.checkParameterIsNotNull(iterable, "receiver$0");
        ArrayList arrayList = new ArrayList();
        for (Iterable addAll : iterable) {
            CollectionsKt__MutableCollectionsKt.addAll((Collection) arrayList, addAll);
        }
        return arrayList;
    }

    @NotNull
    public static final <T, R> Pair<List<T>, List<R>> unzip(@NotNull Iterable<? extends Pair<? extends T, ? extends R>> iterable) {
        Intrinsics.checkParameterIsNotNull(iterable, "receiver$0");
        int collectionSizeOrDefault = collectionSizeOrDefault(iterable, 10);
        ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
        ArrayList arrayList2 = new ArrayList(collectionSizeOrDefault);
        for (Pair pair : iterable) {
            arrayList.add(pair.getFirst());
            arrayList2.add(pair.getSecond());
        }
        return TuplesKt.to(arrayList, arrayList2);
    }
}
