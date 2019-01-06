package khttp.structures.cookie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.TypeCastException;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMutableMap;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 0}, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010#\n\u0002\u0010'\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u001f\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000f\u0018\u0000 -2\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u0001:\u0001-B\u001b\b\u0016\u0012\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0002\u0010\u0006B\u001b\u0012\u0014\b\u0002\u0010\u0003\u001a\n\u0012\u0006\b\u0001\u0012\u00020\b0\u0007\"\u00020\b¢\u0006\u0002\u0010\tJ\t\u0010\u001c\u001a\u00020\u001dH\u0001J\u0011\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u0002H\u0001J\u0011\u0010!\u001a\u00020\u001f2\u0006\u0010\"\u001a\u00020\u0002H\u0001J\u0013\u0010#\u001a\u0004\u0018\u00010\u00022\u0006\u0010 \u001a\u00020\u0002H\u0003J\u0010\u0010$\u001a\u0004\u0018\u00010\b2\u0006\u0010 \u001a\u00020\u0002J\t\u0010%\u001a\u00020\u001fH\u0001J\u001b\u0010&\u001a\u0004\u0018\u00010\u00022\u0006\u0010 \u001a\u00020\u00022\u0006\u0010\"\u001a\u00020\u0002H\u0001J\u001f\u0010'\u001a\u00020\u001d2\u0014\u0010(\u001a\u0010\u0012\u0006\b\u0001\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u0004H\u0001J\u0013\u0010)\u001a\u0004\u0018\u00010\u00022\u0006\u0010 \u001a\u00020\u0002H\u0001J\u000e\u0010*\u001a\u00020\u001d2\u0006\u0010+\u001a\u00020\bJ\b\u0010,\u001a\u00020\u0002H\u0016R\u001b\u0010\u0003\u001a\n\u0012\u0006\b\u0001\u0012\u00020\b0\u0007¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000bR$\u0010\r\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u000f0\u000eX\u0005¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0018\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00020\u000eX\u0005¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0011R\u0012\u0010\u0014\u001a\u00020\u0015X\u0005¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u0018\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00020\u0019X\u0005¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001b¨\u0006."}, d2 = {"Lkhttp/structures/cookie/CookieJar;", "", "", "cookies", "", "", "(Ljava/util/Map;)V", "", "Lkhttp/structures/cookie/Cookie;", "([Lkhttp/structures/cookie/Cookie;)V", "getCookies", "()[Lkhttp/structures/cookie/Cookie;", "[Lkhttp/structures/cookie/Cookie;", "entries", "", "", "getEntries", "()Ljava/util/Set;", "keys", "getKeys", "size", "", "getSize", "()I", "values", "", "getValues", "()Ljava/util/Collection;", "clear", "", "containsKey", "", "key", "containsValue", "value", "get", "getCookie", "isEmpty", "put", "putAll", "from", "remove", "setCookie", "cookie", "toString", "Companion", "khttp"}, k = 1, mv = {1, 1, 1})
/* compiled from: CookieJar.kt */
public final class CookieJar implements Map<String, String>, KMutableMap {
    public static final Companion Companion = new Companion();
    private final /* synthetic */ HashMap $delegate_0;
    @NotNull
    private final Cookie[] cookies;

    @Metadata(bv = {1, 0, 0}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J#\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004*\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00010\u0006H\u0002¢\u0006\u0002\u0010\b¨\u0006\t"}, d2 = {"Lkhttp/structures/cookie/CookieJar$Companion;", "", "()V", "toCookieArray", "", "Lkhttp/structures/cookie/Cookie;", "", "", "(Ljava/util/Map;)[Lkhttp/structures/cookie/Cookie;", "khttp"}, k = 1, mv = {1, 1, 1})
    /* compiled from: CookieJar.kt */
    public static final class Companion {
        private Companion() {
        }

        private final Cookie[] toCookieArray(@NotNull Map<String, ? extends Object> map) {
            Collection arrayList = new ArrayList(map.size());
            for (Entry entry : map.entrySet()) {
                Map emptyMap;
                Iterable<String> split$default = StringsKt__StringsKt.split$default((CharSequence) entry.getValue().toString(), new String[]{";"}, false, 0, 6, null);
                Collection arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(split$default, 10));
                for (String str : split$default) {
                    if (str != null) {
                        arrayList2.add(StringsKt__StringsKt.trim((CharSequence) str).toString());
                    } else {
                        throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
                    }
                }
                List list = (List) arrayList2;
                String str2 = (String) list.get(0);
                if (list.size() < 2) {
                    emptyMap = MapsKt__MapsKt.emptyMap();
                } else {
                    Iterable<CharSequence> subList = list.subList(1, list.size());
                    Map linkedHashMap = new LinkedHashMap(RangesKt___RangesKt.coerceAtLeast(MapsKt__MapsKt.mapCapacity(CollectionsKt__IterablesKt.collectionSizeOrDefault(subList, 10)), 16));
                    for (CharSequence charSequence : subList) {
                        String str3 = (String) StringsKt__StringsKt.split$default(charSequence, new String[]{"="}, false, 0, 6, null).get(0);
                        if (str3 != null) {
                            String obj = StringsKt__StringsKt.trim((CharSequence) str3).toString();
                            List split$default2 = StringsKt__StringsKt.split$default(charSequence, new String[]{"="}, false, 0, 6, null);
                            Object obj2 = null;
                            String str4 = split$default2.size() > 1 ? (String) split$default2.get(1) : (String) null;
                            if (str4 != null) {
                                if (str4 != null) {
                                    obj2 = StringsKt__StringsKt.trim((CharSequence) str4).toString();
                                } else {
                                    throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
                                }
                            }
                            Pair to = TuplesKt.to(obj, obj2);
                            linkedHashMap.put(to.getFirst(), to.getSecond());
                        } else {
                            throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
                        }
                    }
                    emptyMap = linkedHashMap;
                }
                arrayList.add(new Cookie((String) entry.getKey(), str2, emptyMap));
            }
            arrayList = (List) arrayList;
            Object[] toArray = arrayList.toArray(new Cookie[arrayList.size()]);
            if (toArray != null) {
                return (Cookie[]) toArray;
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
        }
    }

    public CookieJar() {
        this(null, 1, null);
    }

    public void clear() {
        this.$delegate_0.clear();
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
        Set<Entry<String, String>> entrySet = this.$delegate_0.entrySet();
        Intrinsics.checkExpressionValueIsNotNull(entrySet, "<get-entries>(...)");
        return entrySet;
    }

    @NotNull
    public Set<String> getKeys() {
        Set<String> keySet = this.$delegate_0.keySet();
        Intrinsics.checkExpressionValueIsNotNull(keySet, "<get-keys>(...)");
        return keySet;
    }

    public int getSize() {
        return this.$delegate_0.size();
    }

    @NotNull
    public Collection<String> getValues() {
        Collection<String> values = this.$delegate_0.values();
        Intrinsics.checkExpressionValueIsNotNull(values, "<get-values>(...)");
        return values;
    }

    public boolean isEmpty() {
        return this.$delegate_0.isEmpty();
    }

    @Nullable
    public String put(@NotNull String str, @NotNull String str2) {
        Intrinsics.checkParameterIsNotNull(str, "key");
        Intrinsics.checkParameterIsNotNull(str2, "value");
        return (String) this.$delegate_0.put(str, str2);
    }

    public void putAll(@NotNull Map<? extends String, ? extends String> map) {
        Intrinsics.checkParameterIsNotNull(map, "from");
        this.$delegate_0.putAll(map);
    }

    @Nullable
    public String remove(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "key");
        return (String) this.$delegate_0.remove(str);
    }

    public CookieJar(@NotNull Cookie... cookieArr) {
        Intrinsics.checkParameterIsNotNull(cookieArr, "cookies");
        Object[] objArr = (Object[]) cookieArr;
        Map linkedHashMap = new LinkedHashMap(RangesKt___RangesKt.coerceAtLeast(MapsKt__MapsKt.mapCapacity(objArr.length), 16));
        for (Object obj : objArr) {
            Cookie cookie = (Cookie) obj;
            Pair to = TuplesKt.to(cookie.getKey(), cookie.getValueWithAttributes());
            linkedHashMap.put(to.getFirst(), to.getSecond());
        }
        this.$delegate_0 = (HashMap) linkedHashMap;
        this.cookies = cookieArr;
    }

    public /* synthetic */ CookieJar(Cookie[] cookieArr, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 1) != 0) {
            cookieArr = (Cookie[]) ((Object[]) new Cookie[null]);
        }
        this(cookieArr);
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
    public final Cookie[] getCookies() {
        return this.cookies;
    }

    public final /* bridge */ Set keySet() {
        return getKeys();
    }

    public final /* bridge */ Object remove(Object obj) {
        return !(obj instanceof String) ? null : remove((String) obj);
    }

    public final /* bridge */ int size() {
        return getSize();
    }

    public final /* bridge */ Collection values() {
        return getValues();
    }

    public CookieJar(@NotNull Map<String, ? extends Object> map) {
        Intrinsics.checkParameterIsNotNull(map, "cookies");
        map = Companion.toCookieArray(map);
        this((Cookie[]) Arrays.copyOf(map, map.length));
    }

    @Nullable
    public final Cookie getCookie(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "key");
        String str2 = (String) get((Object) str);
        if (str2 == null) {
            return (Cookie) null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append("=");
        stringBuilder.append(str2);
        return new Cookie(stringBuilder.toString());
    }

    public final void setCookie(@NotNull Cookie cookie) {
        Intrinsics.checkParameterIsNotNull(cookie, "cookie");
        put(cookie.getKey(), cookie.getValueWithAttributes());
    }

    @NotNull
    public String toString() {
        return ArraysKt___ArraysKt.joinToString$default((Object[]) this.cookies, (CharSequence) "; ", null, null, 0, null, (Function1) CookieJar$toString$1.INSTANCE, 30, null);
    }
}
