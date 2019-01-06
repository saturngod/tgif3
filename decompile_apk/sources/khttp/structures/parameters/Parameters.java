package khttp.structures.parameters;

import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 0}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0010&\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u001e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u0001B\u001b\b\u0016\u0012\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u0001¢\u0006\u0002\u0010\u0004B1\u0012*\u0010\u0003\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u00060\u0005\"\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0011\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0002H\u0001J\u0011\u0010\u001d\u001a\u00020\u001b2\u0006\u0010\u001e\u001a\u00020\u0002H\u0001J\u0013\u0010\u001f\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u001c\u001a\u00020\u0002H\u0003J\t\u0010 \u001a\u00020\u001bH\u0001J\b\u0010!\u001a\u00020\u0002H\u0016R$\u0010\b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\n0\tX\u0005¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0018\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00020\tX\u0005¢\u0006\u0006\u001a\u0004\b\u000e\u0010\fR'\u0010\u0003\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u00060\u0005¢\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u000f\u0010\u0010R\u0012\u0010\u0012\u001a\u00020\u0013X\u0005¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u0018\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00020\u0017X\u0005¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019¨\u0006\""}, d2 = {"Lkhttp/structures/parameters/Parameters;", "", "", "parameters", "(Ljava/util/Map;)V", "", "Lkotlin/Pair;", "([Lkotlin/Pair;)V", "entries", "", "", "getEntries", "()Ljava/util/Set;", "keys", "getKeys", "getParameters", "()[Lkotlin/Pair;", "[Lkotlin/Pair;", "size", "", "getSize", "()I", "values", "", "getValues", "()Ljava/util/Collection;", "containsKey", "", "key", "containsValue", "value", "get", "isEmpty", "toString", "khttp"}, k = 1, mv = {1, 1, 1})
/* compiled from: Parameters.kt */
public final class Parameters implements Map<String, String>, KMappedMarker {
    private final /* synthetic */ Map $delegate_0;
    @NotNull
    private final Pair<String, String>[] parameters;

    public void clear() {
        throw new UnsupportedOperationException("Mutating immutable collection");
    }

    public boolean containsKey(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "key");
        return this.$delegate_0.containsKey(str);
    }

    public boolean containsValue(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "value");
        return this.$delegate_0.containsValue(str);
    }

    @Nullable
    public String get(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "key");
        return (String) this.$delegate_0.get(str);
    }

    @NotNull
    public Set<Entry<String, String>> getEntries() {
        return this.$delegate_0.entrySet();
    }

    @NotNull
    public Set<String> getKeys() {
        return this.$delegate_0.keySet();
    }

    public int getSize() {
        return this.$delegate_0.size();
    }

    @NotNull
    public Collection<String> getValues() {
        return this.$delegate_0.values();
    }

    public boolean isEmpty() {
        return this.$delegate_0.isEmpty();
    }

    public /* synthetic */ Object put(Object obj, Object obj2) {
        throw new UnsupportedOperationException("Mutating immutable collection");
    }

    public String put(String str, String str2) {
        throw new UnsupportedOperationException("Mutating immutable collection");
    }

    public void putAll(Map<? extends String, ? extends String> map) {
        throw new UnsupportedOperationException("Mutating immutable collection");
    }

    public Object remove(Object obj) {
        throw new UnsupportedOperationException("Mutating immutable collection");
    }

    public String remove(String str) {
        throw new UnsupportedOperationException("Mutating immutable collection");
    }

    public Parameters(@NotNull Pair<String, String>... pairArr) {
        Intrinsics.checkParameterIsNotNull(pairArr, "parameters");
        this.$delegate_0 = MapsKt__MapsKt.mapOf((Pair[]) Arrays.copyOf(pairArr, pairArr.length));
        this.parameters = pairArr;
    }

    public final /* bridge */ boolean containsKey(Object obj) {
        return !(obj instanceof String) ? null : containsKey((String) obj);
    }

    public final /* bridge */ boolean containsValue(Object obj) {
        return !(obj instanceof String) ? null : containsValue((String) obj);
    }

    public final /* bridge */ Set entrySet() {
        return getEntries();
    }

    public final /* bridge */ Object get(Object obj) {
        return !(obj instanceof String) ? null : get((String) obj);
    }

    @NotNull
    public final Pair<String, String>[] getParameters() {
        return this.parameters;
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

    public Parameters(@NotNull Map<String, String> map) {
        Intrinsics.checkParameterIsNotNull(map, "parameters");
        Collection toList = MapsKt___MapsKt.toList(map);
        if (toList != null) {
            toList = toList;
            map = toList.toArray(new Pair[toList.size()]);
            if (map != null) {
                Pair[] pairArr = (Pair[]) ((Object[]) map);
                this((Pair[]) Arrays.copyOf(pairArr, pairArr.length));
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
        }
        throw new TypeCastException("null cannot be cast to non-null type java.util.Collection<T>");
    }

    @NotNull
    public String toString() {
        if (size() < 1) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (Entry entry : entrySet()) {
            String str = (String) entry.getKey();
            String str2 = (String) entry.getValue();
            if (stringBuilder.length() > 0) {
                stringBuilder.append("&");
            }
            StringsKt__StringBuilderKt.append(stringBuilder, str, "=", URLEncoder.encode(str2, "UTF-8"));
        }
        Unit unit = Unit.INSTANCE;
        String stringBuilder2 = stringBuilder.toString();
        Intrinsics.checkExpressionValueIsNotNull(stringBuilder2, "StringBuilder().apply(builderAction).toString()");
        return stringBuilder2;
    }
}
