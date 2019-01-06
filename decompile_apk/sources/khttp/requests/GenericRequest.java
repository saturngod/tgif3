package khttp.requests;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.net.IDN;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import khttp.extensions.ExtensionsKt;
import khttp.structures.authorization.Authorization;
import khttp.structures.files.FileLike;
import khttp.structures.parameters.Parameters;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.HTTP;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONWriter;

@Metadata(bv = {1, 0, 0}, d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u001b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u0000 <2\u00020\u0001:\u0001<B\u0001\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0006\u0012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0006\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\t\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\u0014\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0006\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0011\u0012\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014¢\u0006\u0002\u0010\u0016J\u0010\u0010/\u001a\u00020\u00032\u0006\u00100\u001a\u00020\tH\u0002J\u0010\u00101\u001a\u00020\u00032\u0006\u00102\u001a\u00020\u0003H\u0002J\f\u00103\u001a\u000204*\u000204H\u0002J1\u00105\u001a\u00020\u0003\"\u0004\b\u0000\u00106*\u0002H62\u0018\u00107\u001a\u0014\u0012\u0004\u0012\u000209\u0012\u0004\u0012\u0002H6\u0012\u0004\u0012\u00020:08H\u0002¢\u0006\u0002\u0010;R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\u00020\u0011X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0016\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0014\u0010\u001d\u001a\u00020\u00188VX\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001fR\"\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0016\u0010\b\u001a\u0004\u0018\u00010\tX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u001a\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014X\u0004¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%R \u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b&\u0010!R\u0016\u0010\n\u001a\u0004\u0018\u00010\tX\u0004¢\u0006\b\n\u0000\u001a\u0004\b'\u0010#R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b(\u0010)R \u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b*\u0010!R\u0014\u0010\u0012\u001a\u00020\u0011X\u0004¢\u0006\b\n\u0000\u001a\u0004\b+\u0010\u001aR\u0014\u0010\u000e\u001a\u00020\u000fX\u0004¢\u0006\b\n\u0000\u001a\u0004\b,\u0010-R\u0014\u0010\u0004\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b.\u0010)¨\u0006="}, d2 = {"Lkhttp/requests/GenericRequest;", "Lkhttp/requests/Request;", "method", "", "url", "params", "", "headers", "data", "", "json", "auth", "Lkhttp/structures/authorization/Authorization;", "cookies", "timeout", "", "allowRedirects", "", "stream", "files", "", "Lkhttp/structures/files/FileLike;", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Ljava/util/Map;Ljava/lang/Object;Ljava/lang/Object;Lkhttp/structures/authorization/Authorization;Ljava/util/Map;DLjava/lang/Boolean;ZLjava/util/List;)V", "_body", "", "getAllowRedirects", "()Z", "getAuth", "()Lkhttp/structures/authorization/Authorization;", "body", "getBody", "()[B", "getCookies", "()Ljava/util/Map;", "getData", "()Ljava/lang/Object;", "getFiles", "()Ljava/util/List;", "getHeaders", "getJson", "getMethod", "()Ljava/lang/String;", "getParams", "getStream", "getTimeout", "()D", "getUrl", "coerceToJSON", "any", "makeRoute", "route", "toIDN", "Ljava/net/URL;", "withJSONWriter", "T", "converter", "Lkotlin/Function2;", "Lorg/json/JSONWriter;", "", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Ljava/lang/String;", "Companion", "khttp"}, k = 1, mv = {1, 1, 1})
/* compiled from: GenericRequest.kt */
public final class GenericRequest implements Request {
    public static final Companion Companion = new Companion();
    @NotNull
    private static final Map<String, String> DEFAULT_DATA_HEADERS = MapsKt__MapsJVMKt.mapOf(TuplesKt.to("Content-Type", "text/plain"));
    @NotNull
    private static final Map<String, String> DEFAULT_FORM_HEADERS = MapsKt__MapsJVMKt.mapOf(TuplesKt.to("Content-Type", "application/x-www-form-urlencoded"));
    @NotNull
    private static final Map<String, String> DEFAULT_HEADERS = MapsKt__MapsKt.mapOf(TuplesKt.to("Accept", "*/*"), TuplesKt.to("Accept-Encoding", "gzip, deflate"), TuplesKt.to("User-Agent", "khttp/1.0.0-SNAPSHOT"));
    @NotNull
    private static final Map<String, String> DEFAULT_JSON_HEADERS = MapsKt__MapsJVMKt.mapOf(TuplesKt.to("Content-Type", "application/json"));
    @NotNull
    private static final Map<String, String> DEFAULT_UPLOAD_HEADERS = MapsKt__MapsJVMKt.mapOf(TuplesKt.to("Content-Type", "multipart/form-data; boundary=%s"));
    private byte[] _body;
    private final boolean allowRedirects;
    @Nullable
    private final Authorization auth;
    @Nullable
    private final Map<String, String> cookies;
    @Nullable
    private final Object data;
    @NotNull
    private final List<FileLike> files;
    @NotNull
    private final Map<String, String> headers;
    @Nullable
    private final Object json;
    @NotNull
    private final String method;
    @NotNull
    private final Map<String, String> params;
    private final boolean stream;
    private final double timeout;
    @NotNull
    private final String url;

    @Metadata(bv = {1, 0, 0}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u000b\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001d\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001d\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u001d\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0007R\u001d\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u0007R\u001d\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0007¨\u0006\u0010"}, d2 = {"Lkhttp/requests/GenericRequest$Companion;", "", "()V", "DEFAULT_DATA_HEADERS", "", "", "getDEFAULT_DATA_HEADERS", "()Ljava/util/Map;", "DEFAULT_FORM_HEADERS", "getDEFAULT_FORM_HEADERS", "DEFAULT_HEADERS", "getDEFAULT_HEADERS", "DEFAULT_JSON_HEADERS", "getDEFAULT_JSON_HEADERS", "DEFAULT_UPLOAD_HEADERS", "getDEFAULT_UPLOAD_HEADERS", "khttp"}, k = 1, mv = {1, 1, 1})
    /* compiled from: GenericRequest.kt */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final Map<String, String> getDEFAULT_HEADERS() {
            return GenericRequest.DEFAULT_HEADERS;
        }

        @NotNull
        public final Map<String, String> getDEFAULT_DATA_HEADERS() {
            return GenericRequest.DEFAULT_DATA_HEADERS;
        }

        @NotNull
        public final Map<String, String> getDEFAULT_FORM_HEADERS() {
            return GenericRequest.DEFAULT_FORM_HEADERS;
        }

        @NotNull
        public final Map<String, String> getDEFAULT_UPLOAD_HEADERS() {
            return GenericRequest.DEFAULT_UPLOAD_HEADERS;
        }

        @NotNull
        public final Map<String, String> getDEFAULT_JSON_HEADERS() {
            return GenericRequest.DEFAULT_JSON_HEADERS;
        }
    }

    public GenericRequest(@NotNull String str, @NotNull String str2, @NotNull Map<String, String> map, @NotNull Map<String, String> map2, @Nullable Object obj, @Nullable Object obj2, @Nullable Authorization authorization, @Nullable Map<String, String> map3, double d, @Nullable Boolean bool, boolean z, @NotNull List<FileLike> list) {
        Intrinsics.checkParameterIsNotNull(str, "method");
        Intrinsics.checkParameterIsNotNull(str2, "url");
        Intrinsics.checkParameterIsNotNull(map, "params");
        Intrinsics.checkParameterIsNotNull(map2, "headers");
        Intrinsics.checkParameterIsNotNull(list, "files");
        this.method = str;
        this.params = map;
        this.json = obj2;
        this.auth = authorization;
        this.cookies = map3;
        this.timeout = d;
        this.stream = z;
        this.files = list;
        if (bool != null) {
            map = bool.booleanValue();
        } else {
            map = Intrinsics.areEqual(getMethod(), (Object) "HEAD") ^ 1;
        }
        this.allowRedirects = map;
        str2 = makeRoute(str2);
        Intrinsics.checkExpressionValueIsNotNull(str2, "this.makeRoute(url)");
        this.url = str2;
        if ((SetsKt__SetsKt.setOf(new String[]{"http", "https"}).contains(new URI(getUrl()).getScheme()) ^ 1) == null) {
            str2 = getJson();
            map = MapsKt__MapsJVMKt.toSortedMap(map2);
            if (str2 == null) {
                this.data = obj;
                if (obj != null) {
                    map.putAll(Companion.getDEFAULT_DATA_HEADERS());
                }
            } else {
                this.data = coerceToJSON(str2);
                map.putAll(Companion.getDEFAULT_JSON_HEADERS());
            }
            for (Entry entry : Companion.getDEFAULT_HEADERS().entrySet()) {
                String str3 = (String) entry.getKey();
                String str4 = (String) entry.getValue();
                Map map4 = map;
                if (map4 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.Map<K, *>");
                } else if ((map4.containsKey(str3) ^ 1) != null) {
                    map4.put(str3, str4);
                }
            }
            if ((getFiles().isEmpty() ^ 1) != null) {
                Map map5 = map;
                map5.putAll(Companion.getDEFAULT_UPLOAD_HEADERS());
                map2 = "Content-Type";
                obj = map.get("Content-Type");
                if (obj == null) {
                    Intrinsics.throwNpe();
                }
                str = new Object[]{StringsKt__StringsJVMKt.replace$default((String) UUID.randomUUID().toString(), "-", "", (boolean) null, (int) true, (Object) null)};
                str = String.format((String) obj, Arrays.copyOf(str, str.length));
                Intrinsics.checkExpressionValueIsNotNull(str, "java.lang.String.format(this, *args)");
                map5.put(map2, str);
            } else if ((getData() instanceof Map) != null) {
                map.putAll(Companion.getDEFAULT_FORM_HEADERS());
            }
            str = getAuth();
            if (str != null) {
                str = str.getHeader();
                map.put(str.getFirst(), str.getSecond());
            }
            this.headers = map;
            return;
        }
        throw ((Throwable) new IllegalArgumentException("Invalid schema. Only http:// and https:// are supported."));
    }

    @NotNull
    public String getMethod() {
        return this.method;
    }

    @NotNull
    public Map<String, String> getParams() {
        return this.params;
    }

    @Nullable
    public Object getJson() {
        return this.json;
    }

    @Nullable
    public Authorization getAuth() {
        return this.auth;
    }

    @Nullable
    public Map<String, String> getCookies() {
        return this.cookies;
    }

    public double getTimeout() {
        return this.timeout;
    }

    public boolean getStream() {
        return this.stream;
    }

    @NotNull
    public List<FileLike> getFiles() {
        return this.files;
    }

    @NotNull
    public String getUrl() {
        return this.url;
    }

    @NotNull
    public Map<String, String> getHeaders() {
        return this.headers;
    }

    @Nullable
    public Object getData() {
        return this.data;
    }

    public boolean getAllowRedirects() {
        return this.allowRedirects;
    }

    @NotNull
    public byte[] getBody() {
        byte[] bArr;
        if (this._body == null) {
            Object data = getData();
            List<FileLike> files = getFiles();
            if (data == null && files.isEmpty()) {
                this._body = new byte[0];
                bArr = this._body;
                if (bArr != null) {
                    return bArr;
                }
                throw new IllegalStateException("Set to null by another thread");
            }
            if (data == null) {
                data = null;
            } else if ((data instanceof Map) && !(data instanceof Parameters)) {
                Map map = (Map) data;
                Map linkedHashMap = new LinkedHashMap(MapsKt__MapsKt.mapCapacity(map.size()));
                for (Entry entry : map.entrySet()) {
                    linkedHashMap.put(String.valueOf(entry.getKey()), entry.getValue());
                }
                map = new LinkedHashMap(MapsKt__MapsKt.mapCapacity(linkedHashMap.size()));
                for (Entry entry2 : linkedHashMap.entrySet()) {
                    map.put(entry2.getKey(), String.valueOf(entry2.getValue()));
                }
                data = new Parameters(map);
            }
            if (!(data == null || (files.isEmpty() ^ 1) == 0)) {
                if (!(data instanceof Map)) {
                    throw new IllegalArgumentException("data must be a Map".toString());
                }
            }
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            if ((files.isEmpty() ^ 1) != 0) {
                Object obj = getHeaders().get("Content-Type");
                if (obj == null) {
                    Intrinsics.throwNpe();
                }
                String str = (String) StringsKt__StringsKt.split$default((CharSequence) obj, new String[]{"boundary="}, false, 0, 6, null).get(1);
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(byteArrayOutputStream, Charsets.UTF_8);
                if (data != null) {
                    if (data != null) {
                        for (Entry entry22 : ((Map) data).entrySet()) {
                            Object key = entry22.getKey();
                            obj = entry22.getValue();
                            Writer writer = outputStreamWriter;
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append("--");
                            stringBuilder.append(str);
                            stringBuilder.append("\r");
                            stringBuilder.append("\n");
                            ExtensionsKt.writeAndFlush(writer, stringBuilder.toString());
                            String valueOf = String.valueOf(key);
                            stringBuilder = new StringBuilder();
                            stringBuilder.append("Content-Disposition: form-data; name=");
                            stringBuilder.append("\"");
                            stringBuilder.append(valueOf);
                            stringBuilder.append("\"");
                            stringBuilder.append("\r");
                            stringBuilder.append("\n");
                            stringBuilder.append("\r");
                            stringBuilder.append("\n");
                            ExtensionsKt.writeAndFlush(writer, stringBuilder.toString());
                            ExtensionsKt.writeAndFlush(writer, String.valueOf(obj));
                            ExtensionsKt.writeAndFlush(writer, HTTP.CRLF);
                        }
                    } else {
                        throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.Map<*, *>");
                    }
                }
                for (FileLike fileLike : files) {
                    Writer writer2 = outputStreamWriter;
                    StringBuilder stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("--");
                    stringBuilder2.append(str);
                    stringBuilder2.append("\r");
                    stringBuilder2.append("\n");
                    ExtensionsKt.writeAndFlush(writer2, stringBuilder2.toString());
                    stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("Content-Disposition: form-data; name=");
                    stringBuilder2.append("\"");
                    stringBuilder2.append(fileLike.getName());
                    stringBuilder2.append("\"");
                    stringBuilder2.append("; filename=");
                    stringBuilder2.append("\"");
                    stringBuilder2.append(fileLike.getName());
                    stringBuilder2.append("\"");
                    stringBuilder2.append("\r");
                    stringBuilder2.append("\n");
                    stringBuilder2.append("\r");
                    stringBuilder2.append("\n");
                    ExtensionsKt.writeAndFlush(writer2, stringBuilder2.toString());
                    byteArrayOutputStream.write(fileLike.getContents());
                    ExtensionsKt.writeAndFlush(writer2, HTTP.CRLF);
                    Unit unit = Unit.INSTANCE;
                }
                Writer writer3 = outputStreamWriter;
                StringBuilder stringBuilder3 = new StringBuilder();
                stringBuilder3.append("--");
                stringBuilder3.append(str);
                stringBuilder3.append("--");
                stringBuilder3.append("\r");
                stringBuilder3.append("\n");
                ExtensionsKt.writeAndFlush(writer3, stringBuilder3.toString());
                outputStreamWriter.close();
            } else if (!((data instanceof File) || (data instanceof InputStream))) {
                String valueOf2 = String.valueOf(data);
                Charset charset = Charsets.UTF_8;
                if (valueOf2 != null) {
                    data = valueOf2.getBytes(charset);
                    Intrinsics.checkExpressionValueIsNotNull(data, "(this as java.lang.String).getBytes(charset)");
                    byteArrayOutputStream.write(data);
                } else {
                    throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                }
            }
            this._body = byteArrayOutputStream.toByteArray();
        }
        bArr = this._body;
        if (bArr != null) {
            return bArr;
        }
        throw new IllegalStateException("Set to null by another thread");
    }

    private final String coerceToJSON(Object obj) {
        if (!(obj instanceof JSONObject)) {
            if (!(obj instanceof JSONArray)) {
                if (obj instanceof Map) {
                    Map map = (Map) obj;
                    Map linkedHashMap = new LinkedHashMap(MapsKt__MapsKt.mapCapacity(map.size()));
                    for (Entry entry : map.entrySet()) {
                        linkedHashMap.put(String.valueOf(entry.getKey()), entry.getValue());
                    }
                    obj = new JSONObject(linkedHashMap).toString();
                    Intrinsics.checkExpressionValueIsNotNull(obj, "JSONObject(any.mapKeys {….toString() }).toString()");
                    return obj;
                } else if (obj instanceof Collection) {
                    obj = new JSONArray((Collection) obj).toString();
                    Intrinsics.checkExpressionValueIsNotNull(obj, "JSONArray(any).toString()");
                    return obj;
                } else if (obj instanceof Iterable) {
                    return withJSONWriter(obj, new GenericRequest$coerceToJSON$2(obj));
                } else {
                    if (obj instanceof Object[]) {
                        obj = new JSONArray(obj).toString();
                        Intrinsics.checkExpressionValueIsNotNull(obj, "JSONArray(any).toString()");
                        return obj;
                    }
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Could not coerce ");
                    stringBuilder.append(obj.getClass().getSimpleName());
                    stringBuilder.append(" to JSON.");
                    throw new IllegalArgumentException(stringBuilder.toString());
                }
            }
        }
        return obj.toString();
    }

    private final <T> String withJSONWriter(T t, Function2<? super JSONWriter, ? super T, Unit> function2) {
        StringWriter stringWriter = new StringWriter();
        function2.invoke(new JSONWriter(stringWriter), t);
        t = stringWriter.toString();
        Intrinsics.checkExpressionValueIsNotNull(t, "stringWriter.toString()");
        return t;
    }

    private final URL toIDN(@NotNull URL url) {
        Object host;
        String toASCII = IDN.toASCII(url.getHost());
        Field declaredField = url.getClass().getDeclaredField("host");
        declaredField.setAccessible(true);
        Unit unit = Unit.INSTANCE;
        declaredField.set(url, toASCII);
        Field declaredField2 = url.getClass().getDeclaredField("authority");
        declaredField2.setAccessible(true);
        Unit unit2 = Unit.INSTANCE;
        if (url.getPort() == -1) {
            host = url.getHost();
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(url.getHost());
            stringBuilder.append(":");
            stringBuilder.append(url.getPort());
            host = stringBuilder.toString();
        }
        declaredField2.set(url, host);
        return new URL(url.toURI().toASCIIString());
    }

    private final String makeRoute(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        if (getParams().size() > null) {
            str = new StringBuilder();
            str.append("?");
            str.append(new Parameters(getParams()));
            str = str.toString();
        } else {
            str = "";
        }
        stringBuilder.append(str);
        return toIDN(new URL(stringBuilder.toString())).toString();
    }
}
