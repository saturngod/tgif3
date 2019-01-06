package khttp.structures.maps;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 0}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0010&\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u001e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\t\u0018\u0000*\u0004\b\u0000\u0010\u00012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u0002H\u00010\u0002B\u0019\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00028\u00000\u0002¢\u0006\u0002\u0010\u0005J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0003H\u0016J\u0016\u0010\u0018\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00028\u0000H\u0001¢\u0006\u0002\u0010\u001aJ\u0018\u0010\u001b\u001a\u0004\u0018\u00018\u00002\u0006\u0010\u0017\u001a\u00020\u0003H\u0002¢\u0006\u0002\u0010\u001cJ\t\u0010\u001d\u001a\u00020\u0016H\u0001J\b\u0010\u001e\u001a\u00020\u0003H\u0016R$\u0010\u0006\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00028\u00000\b0\u0007X\u0005¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0018\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007X\u0005¢\u0006\u0006\u001a\u0004\b\f\u0010\nR\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00028\u00000\u0002X\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\r\u001a\u00020\u000eX\u0005¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0018\u0010\u0011\u001a\b\u0012\u0004\u0012\u00028\u00000\u0012X\u0005¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u001f"}, d2 = {"Lkhttp/structures/maps/CaseInsensitiveMap;", "V", "", "", "map", "(Ljava/util/Map;)V", "entries", "", "", "getEntries", "()Ljava/util/Set;", "keys", "getKeys", "size", "", "getSize", "()I", "values", "", "getValues", "()Ljava/util/Collection;", "containsKey", "", "key", "containsValue", "value", "(Ljava/lang/Object;)Z", "get", "(Ljava/lang/String;)Ljava/lang/Object;", "isEmpty", "toString", "khttp"}, k = 1, mv = {1, 1, 1})
/* compiled from: CaseInsensitiveMap.kt */
public final class CaseInsensitiveMap<V> implements Map<String, V>, KMappedMarker {
    private final Map<String, V> map;

    public void clear() {
        throw new UnsupportedOperationException("Mutating immutable collection");
    }

    public boolean containsValue(Object obj) {
        return this.map.containsValue(obj);
    }

    @NotNull
    public Set<Entry<String, V>> getEntries() {
        return this.map.entrySet();
    }

    @NotNull
    public Set<String> getKeys() {
        return this.map.keySet();
    }

    public int getSize() {
        return this.map.size();
    }

    @NotNull
    public Collection<V> getValues() {
        return this.map.values();
    }

    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    public /* synthetic */ Object put(Object obj, Object obj2) {
        throw new UnsupportedOperationException("Mutating immutable collection");
    }

    public V put(String str, V v) {
        throw new UnsupportedOperationException("Mutating immutable collection");
    }

    public void putAll(Map<? extends String, ? extends V> map) {
        throw new UnsupportedOperationException("Mutating immutable collection");
    }

    public V remove(Object obj) {
        throw new UnsupportedOperationException("Mutating immutable collection");
    }

    public V remove(Object obj) {
        throw new UnsupportedOperationException("Mutating immutable collection");
    }

    public CaseInsensitiveMap(@NotNull Map<String, ? extends V> map) {
        Intrinsics.checkParameterIsNotNull(map, "map");
        this.map = map;
    }

    public final /* bridge */ boolean containsKey(Object obj) {
        return !(obj instanceof String) ? null : containsKey((String) obj);
    }

    public final /* bridge */ Set entrySet() {
        return getEntries();
    }

    public final /* bridge */ Object get(Object obj) {
        return !(obj instanceof String) ? null : get((String) obj);
    }

    public final /* bridge */ Set keySet() {
        return getKeys();
    }

    public final /* bridge */ int size() {
        return getSize();
    }

    public final /* bridge */ Collection values() {
        return getValues();
    }

    public boolean containsKey(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "key");
        for (String str2 : this.map.keySet()) {
            if (str != null) {
                String toLowerCase = str.toLowerCase();
                Intrinsics.checkExpressionValueIsNotNull(toLowerCase, "(this as java.lang.String).toLowerCase()");
                if (StringsKt__StringsJVMKt.equals(str2, toLowerCase, true)) {
                    return true;
                }
            }
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        return false;
    }

    @Nullable
    public V get(@NotNull Object obj) {
        Intrinsics.checkParameterIsNotNull(obj, "key");
        Map linkedHashMap = new LinkedHashMap();
        for (Entry entry : this.map.entrySet()) {
            String str = (String) entry.getKey();
            if (obj != null) {
                String toLowerCase = ((String) obj).toLowerCase();
                Intrinsics.checkExpressionValueIsNotNull(toLowerCase, "(this as java.lang.String).toLowerCase()");
                if (StringsKt__StringsJVMKt.equals(str, toLowerCase, true)) {
                    linkedHashMap.put(entry.getKey(), entry.getValue());
                }
            } else {
                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
            }
        }
        Collection collection = (Collection) new ArrayList(linkedHashMap.size());
        for (Entry value : linkedHashMap.entrySet()) {
            collection.add(value.getValue());
        }
        return CollectionsKt___CollectionsKt.firstOrNull((List) collection);
    }

    @NotNull
    public String toString() {
        return this.map.toString();
    }
}
