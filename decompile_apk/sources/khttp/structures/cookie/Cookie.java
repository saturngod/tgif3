package khttp.structures.cookie;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 0}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010$\n\u0002\b\u000f\b\b\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u000f\b\u0010\u0012\u0006\u0010\u0005\u001a\u00020\u0000¢\u0006\u0002\u0010\u0006B-\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0001\u0012\u0016\b\u0002\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00010\n¢\u0006\u0002\u0010\u000bJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0001HÆ\u0003J\u0017\u0010\u0016\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00010\nHÆ\u0003J5\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00012\u0016\b\u0002\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00010\nHÆ\u0001R\u001f\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\b\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0012\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u000f¨\u0006\u0019"}, d2 = {"Lkhttp/structures/cookie/Cookie;", "", "string", "", "(Ljava/lang/String;)V", "cookie", "(Lkhttp/structures/cookie/Cookie;)V", "key", "value", "attributes", "", "(Ljava/lang/String;Ljava/lang/Object;Ljava/util/Map;)V", "getAttributes", "()Ljava/util/Map;", "getKey", "()Ljava/lang/String;", "getValue", "()Ljava/lang/Object;", "valueWithAttributes", "getValueWithAttributes", "component1", "component2", "component3", "copy", "Companion", "khttp"}, k = 1, mv = {1, 1, 1})
/* compiled from: Cookie.kt */
public final class Cookie {
    public static final Companion Companion = new Companion();
    @NotNull
    private final Map<String, Object> attributes;
    @NotNull
    private final String key;
    @NotNull
    private final Object value;

    @Metadata(bv = {1, 0, 0}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\f\u0010\u0003\u001a\u00020\u0004*\u00020\u0005H\u0002¨\u0006\u0006"}, d2 = {"Lkhttp/structures/cookie/Cookie$Companion;", "", "()V", "toCookie", "Lkhttp/structures/cookie/Cookie;", "", "khttp"}, k = 1, mv = {1, 1, 1})
    /* compiled from: Cookie.kt */
    public static final class Companion {
        private Companion() {
        }

        private final Cookie toCookie(@NotNull String str) {
            List split$default = StringsKt__StringsKt.split$default((CharSequence) str, new String[]{"="}, false, 2, 2, null);
            if ((split$default.size() == 2 ? 1 : null) != null) {
                str = (String) split$default.get(0);
                if (str != null) {
                    str = StringsKt__StringsKt.trim((CharSequence) str).toString();
                    split$default = StringsKt__StringsKt.split$default((CharSequence) split$default.get(1), new String[]{";"}, false, 0, 6, null);
                    String str2 = (String) split$default.get(0);
                    if (str2 != null) {
                        Map emptyMap;
                        str2 = StringsKt__StringsKt.trim((CharSequence) str2).toString();
                        if (split$default.size() < 2) {
                            emptyMap = MapsKt__MapsKt.emptyMap();
                        } else {
                            Iterable<CharSequence> subList = split$default.subList(1, split$default.size());
                            Map linkedHashMap = new LinkedHashMap(RangesKt___RangesKt.coerceAtLeast(MapsKt__MapsKt.mapCapacity(CollectionsKt__IterablesKt.collectionSizeOrDefault(subList, 10)), 16));
                            for (CharSequence charSequence : subList) {
                                String str3 = (String) StringsKt__StringsKt.split$default(charSequence, new String[]{"="}, false, 0, 6, null).get(0);
                                if (str3 != null) {
                                    Object obj;
                                    String obj2 = StringsKt__StringsKt.trim((CharSequence) str3).toString();
                                    String str4 = (String) CollectionsKt___CollectionsKt.getOrNull(StringsKt__StringsKt.split$default(charSequence, new String[]{"="}, false, 0, 6, null), 1);
                                    if (str4 == null) {
                                        obj = null;
                                    } else if (str4 != null) {
                                        obj = StringsKt__StringsKt.trim((CharSequence) str4).toString();
                                    } else {
                                        throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
                                    }
                                    Pair to = TuplesKt.to(obj2, obj);
                                    linkedHashMap.put(to.getFirst(), to.getSecond());
                                } else {
                                    throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
                                }
                            }
                            emptyMap = linkedHashMap;
                        }
                        return new Cookie(str, str2, emptyMap);
                    }
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
                }
                throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("\"");
            stringBuilder.append(str);
            stringBuilder.append("\"");
            stringBuilder.append(" is not a cookie.");
            throw new IllegalArgumentException(stringBuilder.toString().toString());
        }
    }

    @NotNull
    public static /* bridge */ /* synthetic */ Cookie copy$default(Cookie cookie, String str, Object obj, Map map, int i, Object obj2) {
        if (obj2 == null) {
            if ((i & 1) != 0) {
                str = cookie.key;
            }
            if ((i & 2) != 0) {
                obj = cookie.value;
            }
            if ((i & 4) != 0) {
                map = cookie.attributes;
            }
            return cookie.copy(str, obj, map);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: copy");
    }

    @NotNull
    public final String component1() {
        return this.key;
    }

    @NotNull
    public final Object component2() {
        return this.value;
    }

    @NotNull
    public final Map<String, Object> component3() {
        return this.attributes;
    }

    @NotNull
    public final Cookie copy(@NotNull String str, @NotNull Object obj, @NotNull Map<String, ? extends Object> map) {
        Intrinsics.checkParameterIsNotNull(str, "key");
        Intrinsics.checkParameterIsNotNull(obj, "value");
        Intrinsics.checkParameterIsNotNull(map, "attributes");
        return new Cookie(str, obj, map);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof Cookie) {
                Cookie cookie = (Cookie) obj;
                if (Intrinsics.areEqual(this.key, cookie.key) && Intrinsics.areEqual(this.value, cookie.value) && Intrinsics.areEqual(this.attributes, cookie.attributes)) {
                }
            }
            return false;
        }
        return true;
    }

    public int hashCode() {
        String str = this.key;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        Object obj = this.value;
        hashCode = (hashCode + (obj != null ? obj.hashCode() : 0)) * 31;
        Map map = this.attributes;
        if (map != null) {
            i = map.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Cookie(key=");
        stringBuilder.append(this.key);
        stringBuilder.append(", value=");
        stringBuilder.append(this.value);
        stringBuilder.append(", attributes=");
        stringBuilder.append(this.attributes);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public Cookie(@NotNull String str, @NotNull Object obj, @NotNull Map<String, ? extends Object> map) {
        Intrinsics.checkParameterIsNotNull(str, "key");
        Intrinsics.checkParameterIsNotNull(obj, "value");
        Intrinsics.checkParameterIsNotNull(map, "attributes");
        this.key = str;
        this.value = obj;
        this.attributes = map;
    }

    public /* synthetic */ Cookie(String str, Object obj, Map map, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 4) != 0) {
            map = MapsKt__MapsKt.emptyMap();
        }
        this(str, obj, map);
    }

    @NotNull
    public final Map<String, Object> getAttributes() {
        return this.attributes;
    }

    @NotNull
    public final String getKey() {
        return this.key;
    }

    @NotNull
    public final Object getValue() {
        return this.value;
    }

    public Cookie(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "string");
        this(Companion.toCookie(str));
    }

    public Cookie(@NotNull Cookie cookie) {
        Intrinsics.checkParameterIsNotNull(cookie, "cookie");
        this(cookie.key, cookie.value, cookie.attributes);
    }

    @NotNull
    public final String getValueWithAttributes() {
        if (this.attributes.size() < 1) {
            return this.value.toString();
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.value.toString());
        stringBuilder.append("; ");
        stringBuilder.append(SequencesKt___SequencesKt.joinToString$default(MapsKt___MapsKt.asSequence(this.attributes), "; ", null, null, 0, null, Cookie$valueWithAttributes$1.INSTANCE, 30, null));
        return stringBuilder.toString();
    }
}
