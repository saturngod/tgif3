package khttp;

import java.util.List;
import java.util.Map;
import khttp.requests.GenericRequest;
import khttp.responses.GenericResponse;
import khttp.responses.Response;
import khttp.structures.authorization.Authorization;
import khttp.structures.files.FileLike;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 0}, d1 = {"\u0000@\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u000b\u001a­\u0001\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0014\b\u0002\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u00072\u0014\b\u0002\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\u0016\b\u0002\u0010\u000e\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00072\b\b\u0002\u0010\u000f\u001a\u00020\u00012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00112\u000e\b\u0002\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014H\u0007¢\u0006\u0002\u0010\u0016\u001a­\u0001\u0010\u0017\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0014\b\u0002\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u00072\u0014\b\u0002\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\u0016\b\u0002\u0010\u000e\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00072\b\b\u0002\u0010\u000f\u001a\u00020\u00012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00112\u000e\b\u0002\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014H\u0007¢\u0006\u0002\u0010\u0016\u001a­\u0001\u0010\u0018\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0014\b\u0002\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u00072\u0014\b\u0002\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\u0016\b\u0002\u0010\u000e\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00072\b\b\u0002\u0010\u000f\u001a\u00020\u00012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00112\u000e\b\u0002\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014H\u0007¢\u0006\u0002\u0010\u0016\u001a­\u0001\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0014\b\u0002\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u00072\u0014\b\u0002\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\u0016\b\u0002\u0010\u000e\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00072\b\b\u0002\u0010\u000f\u001a\u00020\u00012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00112\u000e\b\u0002\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014H\u0007¢\u0006\u0002\u0010\u0016\u001a­\u0001\u0010\u001a\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0014\b\u0002\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u00072\u0014\b\u0002\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\u0016\b\u0002\u0010\u000e\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00072\b\b\u0002\u0010\u000f\u001a\u00020\u00012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00112\u000e\b\u0002\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014H\u0007¢\u0006\u0002\u0010\u0016\u001a­\u0001\u0010\u001b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0014\b\u0002\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u00072\u0014\b\u0002\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\u0016\b\u0002\u0010\u000e\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00072\b\b\u0002\u0010\u000f\u001a\u00020\u00012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00112\u000e\b\u0002\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014H\u0007¢\u0006\u0002\u0010\u0016\u001a­\u0001\u0010\u001c\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0014\b\u0002\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u00072\u0014\b\u0002\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\u0016\b\u0002\u0010\u000e\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00072\b\b\u0002\u0010\u000f\u001a\u00020\u00012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00112\u000e\b\u0002\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014H\u0007¢\u0006\u0002\u0010\u0016\u001aµ\u0001\u0010\u001d\u001a\u00020\u00032\u0006\u0010\u001e\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00052\u0014\b\u0002\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u00072\u0014\b\u0002\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\u0016\b\u0002\u0010\u000e\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00072\b\b\u0002\u0010\u000f\u001a\u00020\u00012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00112\u000e\b\u0002\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014H\u0007¢\u0006\u0002\u0010\u001f\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"DEFAULT_TIMEOUT", "", "delete", "Lkhttp/responses/Response;", "url", "", "headers", "", "params", "data", "", "json", "auth", "Lkhttp/structures/authorization/Authorization;", "cookies", "timeout", "allowRedirects", "", "stream", "files", "", "Lkhttp/structures/files/FileLike;", "(Ljava/lang/String;Ljava/util/Map;Ljava/util/Map;Ljava/lang/Object;Ljava/lang/Object;Lkhttp/structures/authorization/Authorization;Ljava/util/Map;DLjava/lang/Boolean;ZLjava/util/List;)Lkhttp/responses/Response;", "get", "head", "options", "patch", "post", "put", "request", "method", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Ljava/util/Map;Ljava/lang/Object;Ljava/lang/Object;Lkhttp/structures/authorization/Authorization;Ljava/util/Map;DLjava/lang/Boolean;ZLjava/util/List;)Lkhttp/responses/Response;", "khttp"}, k = 2, mv = {1, 1, 1})
@JvmName(name = "KHttp")
/* compiled from: KHttp.kt */
public final class KHttp {
    public static final double DEFAULT_TIMEOUT = 30.0d;

    @NotNull
    @JvmOverloads
    public static Response delete(@NotNull String str) {
        return delete$default(str, null, null, null, null, null, null, 0.0d, null, false, null, 2046, null);
    }

    @NotNull
    @JvmOverloads
    public static Response delete(@NotNull String str, @NotNull Map<String, String> map) {
        return delete$default(str, map, null, null, null, null, null, 0.0d, null, false, null, 2044, null);
    }

    @NotNull
    @JvmOverloads
    public static Response delete(@NotNull String str, @NotNull Map<String, String> map, @NotNull Map<String, String> map2) {
        return delete$default(str, map, map2, null, null, null, null, 0.0d, null, false, null, 2040, null);
    }

    @NotNull
    @JvmOverloads
    public static Response delete(@NotNull String str, @NotNull Map<String, String> map, @NotNull Map<String, String> map2, @Nullable Object obj) {
        return delete$default(str, map, map2, obj, null, null, null, 0.0d, null, false, null, 2032, null);
    }

    @NotNull
    @JvmOverloads
    public static Response delete(@NotNull String str, @NotNull Map<String, String> map, @NotNull Map<String, String> map2, @Nullable Object obj, @Nullable Object obj2) {
        return delete$default(str, map, map2, obj, obj2, null, null, 0.0d, null, false, null, 2016, null);
    }

    @NotNull
    @JvmOverloads
    public static Response delete(@NotNull String str, @NotNull Map<String, String> map, @NotNull Map<String, String> map2, @Nullable Object obj, @Nullable Object obj2, @Nullable Authorization authorization) {
        return delete$default(str, map, map2, obj, obj2, authorization, null, 0.0d, null, false, null, 1984, null);
    }

    @NotNull
    @JvmOverloads
    public static Response delete(@NotNull String str, @NotNull Map<String, String> map, @NotNull Map<String, String> map2, @Nullable Object obj, @Nullable Object obj2, @Nullable Authorization authorization, @Nullable Map<String, String> map3) {
        return delete$default(str, map, map2, obj, obj2, authorization, map3, 0.0d, null, false, null, 1920, null);
    }

    @NotNull
    @JvmOverloads
    public static Response delete(@NotNull String str, @NotNull Map<String, String> map, @NotNull Map<String, String> map2, @Nullable Object obj, @Nullable Object obj2, @Nullable Authorization authorization, @Nullable Map<String, String> map3, double d) {
        return delete$default(str, map, map2, obj, obj2, authorization, map3, d, null, false, null, 1792, null);
    }

    @NotNull
    @JvmOverloads
    public static Response delete(@NotNull String str, @NotNull Map<String, String> map, @NotNull Map<String, String> map2, @Nullable Object obj, @Nullable Object obj2, @Nullable Authorization authorization, @Nullable Map<String, String> map3, double d, @Nullable Boolean bool) {
        return delete$default(str, map, map2, obj, obj2, authorization, map3, d, bool, false, null, 1536, null);
    }

    @NotNull
    @JvmOverloads
    public static Response delete(@NotNull String str, @NotNull Map<String, String> map, @NotNull Map<String, String> map2, @Nullable Object obj, @Nullable Object obj2, @Nullable Authorization authorization, @Nullable Map<String, String> map3, double d, @Nullable Boolean bool, boolean z) {
        return delete$default(str, map, map2, obj, obj2, authorization, map3, d, bool, z, null, 1024, null);
    }

    @NotNull
    @JvmOverloads
    public static Response get(@NotNull String str) {
        return get$default(str, null, null, null, null, null, null, 0.0d, null, false, null, 2046, null);
    }

    @NotNull
    @JvmOverloads
    public static Response get(@NotNull String str, @NotNull Map<String, String> map) {
        return get$default(str, map, null, null, null, null, null, 0.0d, null, false, null, 2044, null);
    }

    @NotNull
    @JvmOverloads
    public static Response get(@NotNull String str, @NotNull Map<String, String> map, @NotNull Map<String, String> map2) {
        return get$default(str, map, map2, null, null, null, null, 0.0d, null, false, null, 2040, null);
    }

    @NotNull
    @JvmOverloads
    public static Response get(@NotNull String str, @NotNull Map<String, String> map, @NotNull Map<String, String> map2, @Nullable Object obj) {
        return get$default(str, map, map2, obj, null, null, null, 0.0d, null, false, null, 2032, null);
    }

    @NotNull
    @JvmOverloads
    public static Response get(@NotNull String str, @NotNull Map<String, String> map, @NotNull Map<String, String> map2, @Nullable Object obj, @Nullable Object obj2) {
        return get$default(str, map, map2, obj, obj2, null, null, 0.0d, null, false, null, 2016, null);
    }

    @NotNull
    @JvmOverloads
    public static Response get(@NotNull String str, @NotNull Map<String, String> map, @NotNull Map<String, String> map2, @Nullable Object obj, @Nullable Object obj2, @Nullable Authorization authorization) {
        return get$default(str, map, map2, obj, obj2, authorization, null, 0.0d, null, false, null, 1984, null);
    }

    @NotNull
    @JvmOverloads
    public static Response get(@NotNull String str, @NotNull Map<String, String> map, @NotNull Map<String, String> map2, @Nullable Object obj, @Nullable Object obj2, @Nullable Authorization authorization, @Nullable Map<String, String> map3) {
        return get$default(str, map, map2, obj, obj2, authorization, map3, 0.0d, null, false, null, 1920, null);
    }

    @NotNull
    @JvmOverloads
    public static Response get(@NotNull String str, @NotNull Map<String, String> map, @NotNull Map<String, String> map2, @Nullable Object obj, @Nullable Object obj2, @Nullable Authorization authorization, @Nullable Map<String, String> map3, double d) {
        return get$default(str, map, map2, obj, obj2, authorization, map3, d, null, false, null, 1792, null);
    }

    @NotNull
    @JvmOverloads
    public static Response get(@NotNull String str, @NotNull Map<String, String> map, @NotNull Map<String, String> map2, @Nullable Object obj, @Nullable Object obj2, @Nullable Authorization authorization, @Nullable Map<String, String> map3, double d, @Nullable Boolean bool) {
        return get$default(str, map, map2, obj, obj2, authorization, map3, d, bool, false, null, 1536, null);
    }

    @NotNull
    @JvmOverloads
    public static Response get(@NotNull String str, @NotNull Map<String, String> map, @NotNull Map<String, String> map2, @Nullable Object obj, @Nullable Object obj2, @Nullable Authorization authorization, @Nullable Map<String, String> map3, double d, @Nullable Boolean bool, boolean z) {
        return get$default(str, map, map2, obj, obj2, authorization, map3, d, bool, z, null, 1024, null);
    }

    @NotNull
    @JvmOverloads
    public static Response head(@NotNull String str) {
        return head$default(str, null, null, null, null, null, null, 0.0d, null, false, null, 2046, null);
    }

    @NotNull
    @JvmOverloads
    public static Response head(@NotNull String str, @NotNull Map<String, String> map) {
        return head$default(str, map, null, null, null, null, null, 0.0d, null, false, null, 2044, null);
    }

    @NotNull
    @JvmOverloads
    public static Response head(@NotNull String str, @NotNull Map<String, String> map, @NotNull Map<String, String> map2) {
        return head$default(str, map, map2, null, null, null, null, 0.0d, null, false, null, 2040, null);
    }

    @NotNull
    @JvmOverloads
    public static Response head(@NotNull String str, @NotNull Map<String, String> map, @NotNull Map<String, String> map2, @Nullable Object obj) {
        return head$default(str, map, map2, obj, null, null, null, 0.0d, null, false, null, 2032, null);
    }

    @NotNull
    @JvmOverloads
    public static Response head(@NotNull String str, @NotNull Map<String, String> map, @NotNull Map<String, String> map2, @Nullable Object obj, @Nullable Object obj2) {
        return head$default(str, map, map2, obj, obj2, null, null, 0.0d, null, false, null, 2016, null);
    }

    @NotNull
    @JvmOverloads
    public static Response head(@NotNull String str, @NotNull Map<String, String> map, @NotNull Map<String, String> map2, @Nullable Object obj, @Nullable Object obj2, @Nullable Authorization authorization) {
        return head$default(str, map, map2, obj, obj2, authorization, null, 0.0d, null, false, null, 1984, null);
    }

    @NotNull
    @JvmOverloads
    public static Response head(@NotNull String str, @NotNull Map<String, String> map, @NotNull Map<String, String> map2, @Nullable Object obj, @Nullable Object obj2, @Nullable Authorization authorization, @Nullable Map<String, String> map3) {
        return head$default(str, map, map2, obj, obj2, authorization, map3, 0.0d, null, false, null, 1920, null);
    }

    @NotNull
    @JvmOverloads
    public static Response head(@NotNull String str, @NotNull Map<String, String> map, @NotNull Map<String, String> map2, @Nullable Object obj, @Nullable Object obj2, @Nullable Authorization authorization, @Nullable Map<String, String> map3, double d) {
        return head$default(str, map, map2, obj, obj2, authorization, map3, d, null, false, null, 1792, null);
    }

    @NotNull
    @JvmOverloads
    public static Response head(@NotNull String str, @NotNull Map<String, String> map, @NotNull Map<String, String> map2, @Nullable Object obj, @Nullable Object obj2, @Nullable Authorization authorization, @Nullable Map<String, String> map3, double d, @Nullable Boolean bool) {
        return head$default(str, map, map2, obj, obj2, authorization, map3, d, bool, false, null, 1536, null);
    }

    @NotNull
    @JvmOverloads
    public static Response head(@NotNull String str, @NotNull Map<String, String> map, @NotNull Map<String, String> map2, @Nullable Object obj, @Nullable Object obj2, @Nullable Authorization authorization, @Nullable Map<String, String> map3, double d, @Nullable Boolean bool, boolean z) {
        return head$default(str, map, map2, obj, obj2, authorization, map3, d, bool, z, null, 1024, null);
    }

    @NotNull
    @JvmOverloads
    public static Response options(@NotNull String str) {
        return options$default(str, null, null, null, null, null, null, 0.0d, null, false, null, 2046, null);
    }

    @NotNull
    @JvmOverloads
    public static Response options(@NotNull String str, @NotNull Map<String, String> map) {
        return options$default(str, map, null, null, null, null, null, 0.0d, null, false, null, 2044, null);
    }

    @NotNull
    @JvmOverloads
    public static Response options(@NotNull String str, @NotNull Map<String, String> map, @NotNull Map<String, String> map2) {
        return options$default(str, map, map2, null, null, null, null, 0.0d, null, false, null, 2040, null);
    }

    @NotNull
    @JvmOverloads
    public static Response options(@NotNull String str, @NotNull Map<String, String> map, @NotNull Map<String, String> map2, @Nullable Object obj) {
        return options$default(str, map, map2, obj, null, null, null, 0.0d, null, false, null, 2032, null);
    }

    @NotNull
    @JvmOverloads
    public static Response options(@NotNull String str, @NotNull Map<String, String> map, @NotNull Map<String, String> map2, @Nullable Object obj, @Nullable Object obj2) {
        return options$default(str, map, map2, obj, obj2, null, null, 0.0d, null, false, null, 2016, null);
    }

    @NotNull
    @JvmOverloads
    public static Response options(@NotNull String str, @NotNull Map<String, String> map, @NotNull Map<String, String> map2, @Nullable Object obj, @Nullable Object obj2, @Nullable Authorization authorization) {
        return options$default(str, map, map2, obj, obj2, authorization, null, 0.0d, null, false, null, 1984, null);
    }

    @NotNull
    @JvmOverloads
    public static Response options(@NotNull String str, @NotNull Map<String, String> map, @NotNull Map<String, String> map2, @Nullable Object obj, @Nullable Object obj2, @Nullable Authorization authorization, @Nullable Map<String, String> map3) {
        return options$default(str, map, map2, obj, obj2, authorization, map3, 0.0d, null, false, null, 1920, null);
    }

    @NotNull
    @JvmOverloads
    public static Response options(@NotNull String str, @NotNull Map<String, String> map, @NotNull Map<String, String> map2, @Nullable Object obj, @Nullable Object obj2, @Nullable Authorization authorization, @Nullable Map<String, String> map3, double d) {
        return options$default(str, map, map2, obj, obj2, authorization, map3, d, null, false, null, 1792, null);
    }

    @NotNull
    @JvmOverloads
    public static Response options(@NotNull String str, @NotNull Map<String, String> map, @NotNull Map<String, String> map2, @Nullable Object obj, @Nullable Object obj2, @Nullable Authorization authorization, @Nullable Map<String, String> map3, double d, @Nullable Boolean bool) {
        return options$default(str, map, map2, obj, obj2, authorization, map3, d, bool, false, null, 1536, null);
    }

    @NotNull
    @JvmOverloads
    public static Response options(@NotNull String str, @NotNull Map<String, String> map, @NotNull Map<String, String> map2, @Nullable Object obj, @Nullable Object obj2, @Nullable Authorization authorization, @Nullable Map<String, String> map3, double d, @Nullable Boolean bool, boolean z) {
        return options$default(str, map, map2, obj, obj2, authorization, map3, d, bool, z, null, 1024, null);
    }

    @NotNull
    @JvmOverloads
    public static Response patch(@NotNull String str) {
        return patch$default(str, null, null, null, null, null, null, 0.0d, null, false, null, 2046, null);
    }

    @NotNull
    @JvmOverloads
    public static Response patch(@NotNull String str, @NotNull Map<String, String> map) {
        return patch$default(str, map, null, null, null, null, null, 0.0d, null, false, null, 2044, null);
    }

    @NotNull
    @JvmOverloads
    public static Response patch(@NotNull String str, @NotNull Map<String, String> map, @NotNull Map<String, String> map2) {
        return patch$default(str, map, map2, null, null, null, null, 0.0d, null, false, null, 2040, null);
    }

    @NotNull
    @JvmOverloads
    public static Response patch(@NotNull String str, @NotNull Map<String, String> map, @NotNull Map<String, String> map2, @Nullable Object obj) {
        return patch$default(str, map, map2, obj, null, null, null, 0.0d, null, false, null, 2032, null);
    }

    @NotNull
    @JvmOverloads
    public static Response patch(@NotNull String str, @NotNull Map<String, String> map, @NotNull Map<String, String> map2, @Nullable Object obj, @Nullable Object obj2) {
        return patch$default(str, map, map2, obj, obj2, null, null, 0.0d, null, false, null, 2016, null);
    }

    @NotNull
    @JvmOverloads
    public static Response patch(@NotNull String str, @NotNull Map<String, String> map, @NotNull Map<String, String> map2, @Nullable Object obj, @Nullable Object obj2, @Nullable Authorization authorization) {
        return patch$default(str, map, map2, obj, obj2, authorization, null, 0.0d, null, false, null, 1984, null);
    }

    @NotNull
    @JvmOverloads
    public static Response patch(@NotNull String str, @NotNull Map<String, String> map, @NotNull Map<String, String> map2, @Nullable Object obj, @Nullable Object obj2, @Nullable Authorization authorization, @Nullable Map<String, String> map3) {
        return patch$default(str, map, map2, obj, obj2, authorization, map3, 0.0d, null, false, null, 1920, null);
    }

    @NotNull
    @JvmOverloads
    public static Response patch(@NotNull String str, @NotNull Map<String, String> map, @NotNull Map<String, String> map2, @Nullable Object obj, @Nullable Object obj2, @Nullable Authorization authorization, @Nullable Map<String, String> map3, double d) {
        return patch$default(str, map, map2, obj, obj2, authorization, map3, d, null, false, null, 1792, null);
    }

    @NotNull
    @JvmOverloads
    public static Response patch(@NotNull String str, @NotNull Map<String, String> map, @NotNull Map<String, String> map2, @Nullable Object obj, @Nullable Object obj2, @Nullable Authorization authorization, @Nullable Map<String, String> map3, double d, @Nullable Boolean bool) {
        return patch$default(str, map, map2, obj, obj2, authorization, map3, d, bool, false, null, 1536, null);
    }

    @NotNull
    @JvmOverloads
    public static Response patch(@NotNull String str, @NotNull Map<String, String> map, @NotNull Map<String, String> map2, @Nullable Object obj, @Nullable Object obj2, @Nullable Authorization authorization, @Nullable Map<String, String> map3, double d, @Nullable Boolean bool, boolean z) {
        return patch$default(str, map, map2, obj, obj2, authorization, map3, d, bool, z, null, 1024, null);
    }

    @NotNull
    @JvmOverloads
    public static Response post(@NotNull String str) {
        return post$default(str, null, null, null, null, null, null, 0.0d, null, false, null, 2046, null);
    }

    @NotNull
    @JvmOverloads
    public static Response post(@NotNull String str, @NotNull Map<String, String> map) {
        return post$default(str, map, null, null, null, null, null, 0.0d, null, false, null, 2044, null);
    }

    @NotNull
    @JvmOverloads
    public static Response post(@NotNull String str, @NotNull Map<String, String> map, @NotNull Map<String, String> map2) {
        return post$default(str, map, map2, null, null, null, null, 0.0d, null, false, null, 2040, null);
    }

    @NotNull
    @JvmOverloads
    public static Response post(@NotNull String str, @NotNull Map<String, String> map, @NotNull Map<String, String> map2, @Nullable Object obj) {
        return post$default(str, map, map2, obj, null, null, null, 0.0d, null, false, null, 2032, null);
    }

    @NotNull
    @JvmOverloads
    public static Response post(@NotNull String str, @NotNull Map<String, String> map, @NotNull Map<String, String> map2, @Nullable Object obj, @Nullable Object obj2) {
        return post$default(str, map, map2, obj, obj2, null, null, 0.0d, null, false, null, 2016, null);
    }

    @NotNull
    @JvmOverloads
    public static Response post(@NotNull String str, @NotNull Map<String, String> map, @NotNull Map<String, String> map2, @Nullable Object obj, @Nullable Object obj2, @Nullable Authorization authorization) {
        return post$default(str, map, map2, obj, obj2, authorization, null, 0.0d, null, false, null, 1984, null);
    }

    @NotNull
    @JvmOverloads
    public static Response post(@NotNull String str, @NotNull Map<String, String> map, @NotNull Map<String, String> map2, @Nullable Object obj, @Nullable Object obj2, @Nullable Authorization authorization, @Nullable Map<String, String> map3) {
        return post$default(str, map, map2, obj, obj2, authorization, map3, 0.0d, null, false, null, 1920, null);
    }

    @NotNull
    @JvmOverloads
    public static Response post(@NotNull String str, @NotNull Map<String, String> map, @NotNull Map<String, String> map2, @Nullable Object obj, @Nullable Object obj2, @Nullable Authorization authorization, @Nullable Map<String, String> map3, double d) {
        return post$default(str, map, map2, obj, obj2, authorization, map3, d, null, false, null, 1792, null);
    }

    @NotNull
    @JvmOverloads
    public static Response post(@NotNull String str, @NotNull Map<String, String> map, @NotNull Map<String, String> map2, @Nullable Object obj, @Nullable Object obj2, @Nullable Authorization authorization, @Nullable Map<String, String> map3, double d, @Nullable Boolean bool) {
        return post$default(str, map, map2, obj, obj2, authorization, map3, d, bool, false, null, 1536, null);
    }

    @NotNull
    @JvmOverloads
    public static Response post(@NotNull String str, @NotNull Map<String, String> map, @NotNull Map<String, String> map2, @Nullable Object obj, @Nullable Object obj2, @Nullable Authorization authorization, @Nullable Map<String, String> map3, double d, @Nullable Boolean bool, boolean z) {
        return post$default(str, map, map2, obj, obj2, authorization, map3, d, bool, z, null, 1024, null);
    }

    @NotNull
    @JvmOverloads
    public static Response put(@NotNull String str) {
        return put$default(str, null, null, null, null, null, null, 0.0d, null, false, null, 2046, null);
    }

    @NotNull
    @JvmOverloads
    public static Response put(@NotNull String str, @NotNull Map<String, String> map) {
        return put$default(str, map, null, null, null, null, null, 0.0d, null, false, null, 2044, null);
    }

    @NotNull
    @JvmOverloads
    public static Response put(@NotNull String str, @NotNull Map<String, String> map, @NotNull Map<String, String> map2) {
        return put$default(str, map, map2, null, null, null, null, 0.0d, null, false, null, 2040, null);
    }

    @NotNull
    @JvmOverloads
    public static Response put(@NotNull String str, @NotNull Map<String, String> map, @NotNull Map<String, String> map2, @Nullable Object obj) {
        return put$default(str, map, map2, obj, null, null, null, 0.0d, null, false, null, 2032, null);
    }

    @NotNull
    @JvmOverloads
    public static Response put(@NotNull String str, @NotNull Map<String, String> map, @NotNull Map<String, String> map2, @Nullable Object obj, @Nullable Object obj2) {
        return put$default(str, map, map2, obj, obj2, null, null, 0.0d, null, false, null, 2016, null);
    }

    @NotNull
    @JvmOverloads
    public static Response put(@NotNull String str, @NotNull Map<String, String> map, @NotNull Map<String, String> map2, @Nullable Object obj, @Nullable Object obj2, @Nullable Authorization authorization) {
        return put$default(str, map, map2, obj, obj2, authorization, null, 0.0d, null, false, null, 1984, null);
    }

    @NotNull
    @JvmOverloads
    public static Response put(@NotNull String str, @NotNull Map<String, String> map, @NotNull Map<String, String> map2, @Nullable Object obj, @Nullable Object obj2, @Nullable Authorization authorization, @Nullable Map<String, String> map3) {
        return put$default(str, map, map2, obj, obj2, authorization, map3, 0.0d, null, false, null, 1920, null);
    }

    @NotNull
    @JvmOverloads
    public static Response put(@NotNull String str, @NotNull Map<String, String> map, @NotNull Map<String, String> map2, @Nullable Object obj, @Nullable Object obj2, @Nullable Authorization authorization, @Nullable Map<String, String> map3, double d) {
        return put$default(str, map, map2, obj, obj2, authorization, map3, d, null, false, null, 1792, null);
    }

    @NotNull
    @JvmOverloads
    public static Response put(@NotNull String str, @NotNull Map<String, String> map, @NotNull Map<String, String> map2, @Nullable Object obj, @Nullable Object obj2, @Nullable Authorization authorization, @Nullable Map<String, String> map3, double d, @Nullable Boolean bool) {
        return put$default(str, map, map2, obj, obj2, authorization, map3, d, bool, false, null, 1536, null);
    }

    @NotNull
    @JvmOverloads
    public static Response put(@NotNull String str, @NotNull Map<String, String> map, @NotNull Map<String, String> map2, @Nullable Object obj, @Nullable Object obj2, @Nullable Authorization authorization, @Nullable Map<String, String> map3, double d, @Nullable Boolean bool, boolean z) {
        return put$default(str, map, map2, obj, obj2, authorization, map3, d, bool, z, null, 1024, null);
    }

    @NotNull
    @JvmOverloads
    public static Response request(@NotNull String str, @NotNull String str2) {
        return request$default(str, str2, null, null, null, null, null, null, 0.0d, null, false, null, 4092, null);
    }

    @NotNull
    @JvmOverloads
    public static Response request(@NotNull String str, @NotNull String str2, @NotNull Map<String, String> map) {
        return request$default(str, str2, map, null, null, null, null, null, 0.0d, null, false, null, 4088, null);
    }

    @NotNull
    @JvmOverloads
    public static Response request(@NotNull String str, @NotNull String str2, @NotNull Map<String, String> map, @NotNull Map<String, String> map2) {
        return request$default(str, str2, map, map2, null, null, null, null, 0.0d, null, false, null, 4080, null);
    }

    @NotNull
    @JvmOverloads
    public static Response request(@NotNull String str, @NotNull String str2, @NotNull Map<String, String> map, @NotNull Map<String, String> map2, @Nullable Object obj) {
        return request$default(str, str2, map, map2, obj, null, null, null, 0.0d, null, false, null, 4064, null);
    }

    @NotNull
    @JvmOverloads
    public static Response request(@NotNull String str, @NotNull String str2, @NotNull Map<String, String> map, @NotNull Map<String, String> map2, @Nullable Object obj, @Nullable Object obj2) {
        return request$default(str, str2, map, map2, obj, obj2, null, null, 0.0d, null, false, null, 4032, null);
    }

    @NotNull
    @JvmOverloads
    public static Response request(@NotNull String str, @NotNull String str2, @NotNull Map<String, String> map, @NotNull Map<String, String> map2, @Nullable Object obj, @Nullable Object obj2, @Nullable Authorization authorization) {
        return request$default(str, str2, map, map2, obj, obj2, authorization, null, 0.0d, null, false, null, 3968, null);
    }

    @NotNull
    @JvmOverloads
    public static Response request(@NotNull String str, @NotNull String str2, @NotNull Map<String, String> map, @NotNull Map<String, String> map2, @Nullable Object obj, @Nullable Object obj2, @Nullable Authorization authorization, @Nullable Map<String, String> map3) {
        return request$default(str, str2, map, map2, obj, obj2, authorization, map3, 0.0d, null, false, null, 3840, null);
    }

    @NotNull
    @JvmOverloads
    public static Response request(@NotNull String str, @NotNull String str2, @NotNull Map<String, String> map, @NotNull Map<String, String> map2, @Nullable Object obj, @Nullable Object obj2, @Nullable Authorization authorization, @Nullable Map<String, String> map3, double d) {
        return request$default(str, str2, map, map2, obj, obj2, authorization, map3, d, null, false, null, 3584, null);
    }

    @NotNull
    @JvmOverloads
    public static Response request(@NotNull String str, @NotNull String str2, @NotNull Map<String, String> map, @NotNull Map<String, String> map2, @Nullable Object obj, @Nullable Object obj2, @Nullable Authorization authorization, @Nullable Map<String, String> map3, double d, @Nullable Boolean bool) {
        return request$default(str, str2, map, map2, obj, obj2, authorization, map3, d, bool, false, null, 3072, null);
    }

    @NotNull
    @JvmOverloads
    public static Response request(@NotNull String str, @NotNull String str2, @NotNull Map<String, String> map, @NotNull Map<String, String> map2, @Nullable Object obj, @Nullable Object obj2, @Nullable Authorization authorization, @Nullable Map<String, String> map3, double d, @Nullable Boolean bool, boolean z) {
        return request$default(str, str2, map, map2, obj, obj2, authorization, map3, d, bool, z, null, 2048, null);
    }

    @NotNull
    @JvmOverloads
    public static /* bridge */ /* synthetic */ Response delete$default(String str, Map map, Map map2, Object obj, Object obj2, Authorization authorization, Map map3, double d, Boolean bool, boolean z, List list, int i, Object obj3) {
        int i2 = i;
        if (obj3 == null) {
            return delete(str, (i2 & 2) != 0 ? MapsKt__MapsKt.emptyMap() : map, (i2 & 4) != 0 ? MapsKt__MapsKt.emptyMap() : map2, (i2 & 8) != 0 ? null : obj, (i2 & 16) != 0 ? null : obj2, (i2 & 32) != 0 ? (Authorization) null : authorization, (i2 & 64) != 0 ? (Map) null : map3, (i2 & 128) != 0 ? DEFAULT_TIMEOUT : d, (i2 & 256) != 0 ? (Boolean) null : bool, (i2 & 512) != 0 ? false : z, (i2 & 1024) != 0 ? CollectionsKt__CollectionsKt.emptyList() : list);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: delete");
    }

    @NotNull
    @JvmOverloads
    public static final Response delete(@NotNull String str, @NotNull Map<String, String> map, @NotNull Map<String, String> map2, @Nullable Object obj, @Nullable Object obj2, @Nullable Authorization authorization, @Nullable Map<String, String> map3, double d, @Nullable Boolean bool, boolean z, @NotNull List<FileLike> list) {
        String str2 = str;
        Intrinsics.checkParameterIsNotNull(str, "url");
        Map<String, String> map4 = map;
        Intrinsics.checkParameterIsNotNull(map, "headers");
        Map<String, String> map5 = map2;
        Intrinsics.checkParameterIsNotNull(map5, "params");
        List<FileLike> list2 = list;
        Intrinsics.checkParameterIsNotNull(list2, "files");
        return request("DELETE", str2, map4, map5, obj, obj2, authorization, map3, d, bool, z, list2);
    }

    @NotNull
    @JvmOverloads
    public static /* bridge */ /* synthetic */ Response get$default(String str, Map map, Map map2, Object obj, Object obj2, Authorization authorization, Map map3, double d, Boolean bool, boolean z, List list, int i, Object obj3) {
        int i2 = i;
        if (obj3 == null) {
            return get(str, (i2 & 2) != 0 ? MapsKt__MapsKt.emptyMap() : map, (i2 & 4) != 0 ? MapsKt__MapsKt.emptyMap() : map2, (i2 & 8) != 0 ? null : obj, (i2 & 16) != 0 ? null : obj2, (i2 & 32) != 0 ? (Authorization) null : authorization, (i2 & 64) != 0 ? (Map) null : map3, (i2 & 128) != 0 ? DEFAULT_TIMEOUT : d, (i2 & 256) != 0 ? (Boolean) null : bool, (i2 & 512) != 0 ? false : z, (i2 & 1024) != 0 ? CollectionsKt__CollectionsKt.emptyList() : list);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: get");
    }

    @NotNull
    @JvmOverloads
    public static final Response get(@NotNull String str, @NotNull Map<String, String> map, @NotNull Map<String, String> map2, @Nullable Object obj, @Nullable Object obj2, @Nullable Authorization authorization, @Nullable Map<String, String> map3, double d, @Nullable Boolean bool, boolean z, @NotNull List<FileLike> list) {
        String str2 = str;
        Intrinsics.checkParameterIsNotNull(str, "url");
        Map<String, String> map4 = map;
        Intrinsics.checkParameterIsNotNull(map, "headers");
        Map<String, String> map5 = map2;
        Intrinsics.checkParameterIsNotNull(map5, "params");
        List<FileLike> list2 = list;
        Intrinsics.checkParameterIsNotNull(list2, "files");
        return request("GET", str2, map4, map5, obj, obj2, authorization, map3, d, bool, z, list2);
    }

    @NotNull
    @JvmOverloads
    public static /* bridge */ /* synthetic */ Response head$default(String str, Map map, Map map2, Object obj, Object obj2, Authorization authorization, Map map3, double d, Boolean bool, boolean z, List list, int i, Object obj3) {
        int i2 = i;
        if (obj3 == null) {
            return head(str, (i2 & 2) != 0 ? MapsKt__MapsKt.emptyMap() : map, (i2 & 4) != 0 ? MapsKt__MapsKt.emptyMap() : map2, (i2 & 8) != 0 ? null : obj, (i2 & 16) != 0 ? null : obj2, (i2 & 32) != 0 ? (Authorization) null : authorization, (i2 & 64) != 0 ? (Map) null : map3, (i2 & 128) != 0 ? DEFAULT_TIMEOUT : d, (i2 & 256) != 0 ? (Boolean) null : bool, (i2 & 512) != 0 ? false : z, (i2 & 1024) != 0 ? CollectionsKt__CollectionsKt.emptyList() : list);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: head");
    }

    @NotNull
    @JvmOverloads
    public static final Response head(@NotNull String str, @NotNull Map<String, String> map, @NotNull Map<String, String> map2, @Nullable Object obj, @Nullable Object obj2, @Nullable Authorization authorization, @Nullable Map<String, String> map3, double d, @Nullable Boolean bool, boolean z, @NotNull List<FileLike> list) {
        String str2 = str;
        Intrinsics.checkParameterIsNotNull(str, "url");
        Map<String, String> map4 = map;
        Intrinsics.checkParameterIsNotNull(map, "headers");
        Map<String, String> map5 = map2;
        Intrinsics.checkParameterIsNotNull(map5, "params");
        List<FileLike> list2 = list;
        Intrinsics.checkParameterIsNotNull(list2, "files");
        return request("HEAD", str2, map4, map5, obj, obj2, authorization, map3, d, bool, z, list2);
    }

    @NotNull
    @JvmOverloads
    public static /* bridge */ /* synthetic */ Response options$default(String str, Map map, Map map2, Object obj, Object obj2, Authorization authorization, Map map3, double d, Boolean bool, boolean z, List list, int i, Object obj3) {
        int i2 = i;
        if (obj3 == null) {
            return options(str, (i2 & 2) != 0 ? MapsKt__MapsKt.emptyMap() : map, (i2 & 4) != 0 ? MapsKt__MapsKt.emptyMap() : map2, (i2 & 8) != 0 ? null : obj, (i2 & 16) != 0 ? null : obj2, (i2 & 32) != 0 ? (Authorization) null : authorization, (i2 & 64) != 0 ? (Map) null : map3, (i2 & 128) != 0 ? DEFAULT_TIMEOUT : d, (i2 & 256) != 0 ? (Boolean) null : bool, (i2 & 512) != 0 ? false : z, (i2 & 1024) != 0 ? CollectionsKt__CollectionsKt.emptyList() : list);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: options");
    }

    @NotNull
    @JvmOverloads
    public static final Response options(@NotNull String str, @NotNull Map<String, String> map, @NotNull Map<String, String> map2, @Nullable Object obj, @Nullable Object obj2, @Nullable Authorization authorization, @Nullable Map<String, String> map3, double d, @Nullable Boolean bool, boolean z, @NotNull List<FileLike> list) {
        String str2 = str;
        Intrinsics.checkParameterIsNotNull(str, "url");
        Map<String, String> map4 = map;
        Intrinsics.checkParameterIsNotNull(map, "headers");
        Map<String, String> map5 = map2;
        Intrinsics.checkParameterIsNotNull(map5, "params");
        List<FileLike> list2 = list;
        Intrinsics.checkParameterIsNotNull(list2, "files");
        return request("OPTIONS", str2, map4, map5, obj, obj2, authorization, map3, d, bool, z, list2);
    }

    @NotNull
    @JvmOverloads
    public static /* bridge */ /* synthetic */ Response patch$default(String str, Map map, Map map2, Object obj, Object obj2, Authorization authorization, Map map3, double d, Boolean bool, boolean z, List list, int i, Object obj3) {
        int i2 = i;
        if (obj3 == null) {
            return patch(str, (i2 & 2) != 0 ? MapsKt__MapsKt.emptyMap() : map, (i2 & 4) != 0 ? MapsKt__MapsKt.emptyMap() : map2, (i2 & 8) != 0 ? null : obj, (i2 & 16) != 0 ? null : obj2, (i2 & 32) != 0 ? (Authorization) null : authorization, (i2 & 64) != 0 ? (Map) null : map3, (i2 & 128) != 0 ? DEFAULT_TIMEOUT : d, (i2 & 256) != 0 ? (Boolean) null : bool, (i2 & 512) != 0 ? false : z, (i2 & 1024) != 0 ? CollectionsKt__CollectionsKt.emptyList() : list);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: patch");
    }

    @NotNull
    @JvmOverloads
    public static final Response patch(@NotNull String str, @NotNull Map<String, String> map, @NotNull Map<String, String> map2, @Nullable Object obj, @Nullable Object obj2, @Nullable Authorization authorization, @Nullable Map<String, String> map3, double d, @Nullable Boolean bool, boolean z, @NotNull List<FileLike> list) {
        String str2 = str;
        Intrinsics.checkParameterIsNotNull(str, "url");
        Map<String, String> map4 = map;
        Intrinsics.checkParameterIsNotNull(map, "headers");
        Map<String, String> map5 = map2;
        Intrinsics.checkParameterIsNotNull(map5, "params");
        List<FileLike> list2 = list;
        Intrinsics.checkParameterIsNotNull(list2, "files");
        return request("PATCH", str2, map4, map5, obj, obj2, authorization, map3, d, bool, z, list2);
    }

    @NotNull
    @JvmOverloads
    public static /* bridge */ /* synthetic */ Response post$default(String str, Map map, Map map2, Object obj, Object obj2, Authorization authorization, Map map3, double d, Boolean bool, boolean z, List list, int i, Object obj3) {
        int i2 = i;
        if (obj3 == null) {
            return post(str, (i2 & 2) != 0 ? MapsKt__MapsKt.emptyMap() : map, (i2 & 4) != 0 ? MapsKt__MapsKt.emptyMap() : map2, (i2 & 8) != 0 ? null : obj, (i2 & 16) != 0 ? null : obj2, (i2 & 32) != 0 ? (Authorization) null : authorization, (i2 & 64) != 0 ? (Map) null : map3, (i2 & 128) != 0 ? DEFAULT_TIMEOUT : d, (i2 & 256) != 0 ? (Boolean) null : bool, (i2 & 512) != 0 ? false : z, (i2 & 1024) != 0 ? CollectionsKt__CollectionsKt.emptyList() : list);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: post");
    }

    @NotNull
    @JvmOverloads
    public static final Response post(@NotNull String str, @NotNull Map<String, String> map, @NotNull Map<String, String> map2, @Nullable Object obj, @Nullable Object obj2, @Nullable Authorization authorization, @Nullable Map<String, String> map3, double d, @Nullable Boolean bool, boolean z, @NotNull List<FileLike> list) {
        String str2 = str;
        Intrinsics.checkParameterIsNotNull(str, "url");
        Map<String, String> map4 = map;
        Intrinsics.checkParameterIsNotNull(map, "headers");
        Map<String, String> map5 = map2;
        Intrinsics.checkParameterIsNotNull(map5, "params");
        List<FileLike> list2 = list;
        Intrinsics.checkParameterIsNotNull(list2, "files");
        return request("POST", str2, map4, map5, obj, obj2, authorization, map3, d, bool, z, list2);
    }

    @NotNull
    @JvmOverloads
    public static /* bridge */ /* synthetic */ Response put$default(String str, Map map, Map map2, Object obj, Object obj2, Authorization authorization, Map map3, double d, Boolean bool, boolean z, List list, int i, Object obj3) {
        int i2 = i;
        if (obj3 == null) {
            return put(str, (i2 & 2) != 0 ? MapsKt__MapsKt.emptyMap() : map, (i2 & 4) != 0 ? MapsKt__MapsKt.emptyMap() : map2, (i2 & 8) != 0 ? null : obj, (i2 & 16) != 0 ? null : obj2, (i2 & 32) != 0 ? (Authorization) null : authorization, (i2 & 64) != 0 ? (Map) null : map3, (i2 & 128) != 0 ? DEFAULT_TIMEOUT : d, (i2 & 256) != 0 ? (Boolean) null : bool, (i2 & 512) != 0 ? false : z, (i2 & 1024) != 0 ? CollectionsKt__CollectionsKt.emptyList() : list);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: put");
    }

    @NotNull
    @JvmOverloads
    public static final Response put(@NotNull String str, @NotNull Map<String, String> map, @NotNull Map<String, String> map2, @Nullable Object obj, @Nullable Object obj2, @Nullable Authorization authorization, @Nullable Map<String, String> map3, double d, @Nullable Boolean bool, boolean z, @NotNull List<FileLike> list) {
        String str2 = str;
        Intrinsics.checkParameterIsNotNull(str, "url");
        Map<String, String> map4 = map;
        Intrinsics.checkParameterIsNotNull(map, "headers");
        Map<String, String> map5 = map2;
        Intrinsics.checkParameterIsNotNull(map5, "params");
        List<FileLike> list2 = list;
        Intrinsics.checkParameterIsNotNull(list2, "files");
        return request("PUT", str2, map4, map5, obj, obj2, authorization, map3, d, bool, z, list2);
    }

    @NotNull
    @JvmOverloads
    public static /* bridge */ /* synthetic */ Response request$default(String str, String str2, Map map, Map map2, Object obj, Object obj2, Authorization authorization, Map map3, double d, Boolean bool, boolean z, List list, int i, Object obj3) {
        int i2 = i;
        if (obj3 == null) {
            return request(str, str2, (i2 & 4) != 0 ? MapsKt__MapsKt.emptyMap() : map, (i2 & 8) != 0 ? MapsKt__MapsKt.emptyMap() : map2, (i2 & 16) != 0 ? null : obj, (i2 & 32) != 0 ? null : obj2, (i2 & 64) != 0 ? (Authorization) null : authorization, (i2 & 128) != 0 ? (Map) null : map3, (i2 & 256) != 0 ? DEFAULT_TIMEOUT : d, (i2 & 512) != 0 ? (Boolean) null : bool, (i2 & 1024) != 0 ? false : z, (i2 & 2048) != 0 ? CollectionsKt__CollectionsKt.emptyList() : list);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: request");
    }

    @NotNull
    @JvmOverloads
    public static final Response request(@NotNull String str, @NotNull String str2, @NotNull Map<String, String> map, @NotNull Map<String, String> map2, @Nullable Object obj, @Nullable Object obj2, @Nullable Authorization authorization, @Nullable Map<String, String> map3, double d, @Nullable Boolean bool, boolean z, @NotNull List<FileLike> list) {
        String str3 = str;
        Intrinsics.checkParameterIsNotNull(str3, "method");
        String str4 = str2;
        Intrinsics.checkParameterIsNotNull(str4, "url");
        Map<String, String> map4 = map;
        Intrinsics.checkParameterIsNotNull(map4, "headers");
        Map<String, String> map5 = map2;
        Intrinsics.checkParameterIsNotNull(map5, "params");
        List<FileLike> list2 = list;
        Intrinsics.checkParameterIsNotNull(list2, "files");
        GenericResponse genericResponse = new GenericResponse(new GenericRequest(str3, str4, map5, map4, obj, obj2, authorization, map3, d, bool, z, list2));
        genericResponse.init$khttp();
        Response response = (Response) CollectionsKt___CollectionsKt.last(genericResponse.get_history$khttp());
        genericResponse.get_history$khttp().remove(response);
        Unit unit = Unit.INSTANCE;
        return response;
    }
}
