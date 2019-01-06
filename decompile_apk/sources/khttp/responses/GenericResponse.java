package khttp.responses;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import khttp.requests.GenericRequest;
import khttp.requests.Request;
import khttp.structures.authorization.Authorization;
import khttp.structures.cookie.Cookie;
import khttp.structures.cookie.CookieJar;
import khttp.structures.maps.CaseInsensitiveMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.text.Charsets;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONObject;

@Metadata(bv = {1, 0, 0}, d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010!\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0012\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010(\n\u0002\b\b\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 e2\u00020\u0001:\u0001eB\u000f\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010M\u001a\b\u0012\u0004\u0012\u00020\b0N2\u0006\u0010O\u001a\u00020\u001cH\u0016J\r\u0010P\u001a\u000203H\u0000¢\u0006\u0002\bQJ \u0010R\u001a\b\u0012\u0004\u0012\u00020\b0N2\u0006\u0010O\u001a\u00020\u001c2\b\u0010S\u001a\u0004\u0018\u00010\bH\u0016J\b\u0010T\u001a\u00020\u0012H\u0016J\b\u0010U\u001a\u000203H\u0002J3\u0010V\u001a\u0004\u0018\u00010W\"\b\b\u0000\u0010X*\u00020Y*\b\u0012\u0004\u0012\u0002HX0Z2\u0006\u0010[\u001a\u00020\u00122\u0006\u0010\\\u001a\u0002HXH\u0002¢\u0006\u0002\u0010]J2\u0010^\u001a\u00020\u0006*\u00020_2\u0006\u0010`\u001a\u00020\u00012\u0017\u0010a\u001a\u0013\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u0002030b¢\u0006\u0002\bcH\u0000¢\u0006\u0002\bdR\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\"\u0010\r\u001a\u0004\u0018\u00010\f2\b\u0010\u000b\u001a\u0004\u0018\u00010\f@BX\u000e¢\u0006\b\n\u0000\"\u0004\b\u000e\u0010\u000fR\u001c\u0010\u0010\u001a\u0010\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u0011X\u000e¢\u0006\u0002\n\u0000R \u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00010\u0014X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u000e¢\u0006\u0004\n\u0002\u0010\u001dR\u0014\u0010\u001e\u001a\u00020\u00068VX\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010 R\u0014\u0010!\u001a\u00020\b8VX\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010#R\u0014\u0010$\u001a\u00020\n8VX\u0004¢\u0006\u0006\u001a\u0004\b%\u0010&R$\u0010'\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\f8V@VX\u000e¢\u0006\f\u001a\u0004\b(\u0010)\"\u0004\b*\u0010\u000fR \u0010+\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00120\u00118VX\u0004¢\u0006\u0006\u001a\u0004\b,\u0010-R\u001a\u0010.\u001a\b\u0012\u0004\u0012\u00020\u00010/8VX\u0004¢\u0006\u0006\u001a\u0004\b0\u0010\u0016R)\u00101\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u000203020\u0014¢\u0006\b\n\u0000\u001a\u0004\b4\u0010\u0016R\u0014\u00105\u001a\u0002068VX\u0004¢\u0006\u0006\u001a\u0004\b7\u00108R\u0014\u00109\u001a\u00020:8VX\u0004¢\u0006\u0006\u001a\u0004\b;\u0010<R\u0014\u0010=\u001a\u00020\u001a8VX\u0004¢\u0006\u0006\u001a\u0004\b>\u0010?R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b@\u0010AR\u0014\u0010B\u001a\u00020\u001c8VX\u0004¢\u0006\u0006\u001a\u0004\bC\u0010DR\u0014\u0010E\u001a\u00020\u00128VX\u0004¢\u0006\u0006\u001a\u0004\bF\u0010GR\u0014\u0010H\u001a\u00020\u00128VX\u0004¢\u0006\u0006\u001a\u0004\bI\u0010GR\u0018\u0010J\u001a\u00020\u001a*\u00020\u00068BX\u0004¢\u0006\u0006\u001a\u0004\bK\u0010L¨\u0006f"}, d2 = {"Lkhttp/responses/GenericResponse;", "Lkhttp/responses/Response;", "request", "Lkhttp/requests/Request;", "(Lkhttp/requests/Request;)V", "_connection", "Ljava/net/HttpURLConnection;", "_content", "", "_cookies", "Lkhttp/structures/cookie/CookieJar;", "value", "Ljava/nio/charset/Charset;", "_encoding", "set_encoding", "(Ljava/nio/charset/Charset;)V", "_headers", "", "", "_history", "", "get_history$khttp", "()Ljava/util/List;", "set_history$khttp", "(Ljava/util/List;)V", "_raw", "Ljava/io/InputStream;", "_statusCode", "", "Ljava/lang/Integer;", "connection", "getConnection", "()Ljava/net/HttpURLConnection;", "content", "getContent", "()[B", "cookies", "getCookies", "()Lkhttp/structures/cookie/CookieJar;", "encoding", "getEncoding", "()Ljava/nio/charset/Charset;", "setEncoding", "headers", "getHeaders", "()Ljava/util/Map;", "history", "", "getHistory", "initializers", "Lkotlin/Function2;", "", "getInitializers", "jsonArray", "Lorg/json/JSONArray;", "getJsonArray", "()Lorg/json/JSONArray;", "jsonObject", "Lorg/json/JSONObject;", "getJsonObject", "()Lorg/json/JSONObject;", "raw", "getRaw", "()Ljava/io/InputStream;", "getRequest", "()Lkhttp/requests/Request;", "statusCode", "getStatusCode", "()I", "text", "getText", "()Ljava/lang/String;", "url", "getUrl", "realInputStream", "getRealInputStream", "(Ljava/net/HttpURLConnection;)Ljava/io/InputStream;", "contentIterator", "", "chunkSize", "init", "init$khttp", "lineIterator", "delimiter", "toString", "updateRequestHeaders", "getField", "", "T", "Ljava/net/URLConnection;", "Ljava/lang/Class;", "name", "instance", "(Ljava/lang/Class;Ljava/lang/String;Ljava/net/URLConnection;)Ljava/lang/Object;", "openRedirectingConnection", "Ljava/net/URL;", "first", "receiver", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "openRedirectingConnection$khttp", "Companion", "khttp"}, k = 1, mv = {1, 1, 1})
/* compiled from: GenericResponse.kt */
public final class GenericResponse implements Response {
    public static final Companion Companion = new Companion();
    @NotNull
    private static final List<Function2<GenericResponse, HttpURLConnection, Unit>> defaultEndInitializers = CollectionsKt__CollectionsKt.arrayListOf(GenericResponse$Companion$defaultEndInitializers$1.INSTANCE, GenericResponse$Companion$defaultEndInitializers$2.INSTANCE, GenericResponse$Companion$defaultEndInitializers$3.INSTANCE);
    @NotNull
    private static final List<Function2<GenericResponse, HttpURLConnection, Unit>> defaultStartInitializers = CollectionsKt__CollectionsKt.arrayListOf(GenericResponse$Companion$defaultStartInitializers$1.INSTANCE, GenericResponse$Companion$defaultStartInitializers$2.INSTANCE, GenericResponse$Companion$defaultStartInitializers$3.INSTANCE, GenericResponse$Companion$defaultStartInitializers$4.INSTANCE, GenericResponse$Companion$defaultStartInitializers$5.INSTANCE);
    private HttpURLConnection _connection;
    private byte[] _content;
    private final CookieJar _cookies = new CookieJar(null, 1, null);
    private Charset _encoding;
    private Map<String, String> _headers;
    @NotNull
    private List<Response> _history = CollectionsKt__CollectionsKt.arrayListOf(new Response[0]);
    private InputStream _raw;
    private Integer _statusCode;
    @NotNull
    private final List<Function2<GenericResponse, HttpURLConnection, Unit>> initializers = CollectionsKt__CollectionsKt.arrayListOf(new Function2[0]);
    @NotNull
    private final Request request;

    @Metadata(bv = {1, 0, 0}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0019\u0010\u0011\u001a\u00020\b*\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u0013H\u0000¢\u0006\u0002\b\u0014R,\u0010\u0003\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u00050\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR,\u0010\u000b\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u00050\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\nR\u0018\u0010\r\u001a\u00020\u000e*\u00020\u00078@X\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0015"}, d2 = {"Lkhttp/responses/GenericResponse$Companion;", "", "()V", "defaultEndInitializers", "", "Lkotlin/Function2;", "Lkhttp/responses/GenericResponse;", "Ljava/net/HttpURLConnection;", "", "getDefaultEndInitializers$khttp", "()Ljava/util/List;", "defaultStartInitializers", "getDefaultStartInitializers$khttp", "cookieJar", "Lkhttp/structures/cookie/CookieJar;", "getCookieJar$khttp", "(Ljava/net/HttpURLConnection;)Lkhttp/structures/cookie/CookieJar;", "forceMethod", "method", "", "forceMethod$khttp", "khttp"}, k = 1, mv = {1, 1, 1})
    /* compiled from: GenericResponse.kt */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final CookieJar getCookieJar$khttp(@NotNull HttpURLConnection httpURLConnection) {
            Intrinsics.checkParameterIsNotNull(httpURLConnection, "$receiver");
            Map linkedHashMap = new LinkedHashMap();
            for (Entry entry : httpURLConnection.getHeaderFields().entrySet()) {
                if (Intrinsics.areEqual((String) entry.getKey(), (Object) "Set-Cookie")) {
                    linkedHashMap.put(entry.getKey(), entry.getValue());
                }
            }
            Collection collection = (Collection) new ArrayList();
            for (Entry entry2 : linkedHashMap.entrySet()) {
                CollectionsKt__MutableCollectionsKt.addAll(collection, (Iterable) (List) entry2.getValue());
            }
            Iterable<String> iterable = (List) collection;
            Collection arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(iterable, 10));
            for (String str : iterable) {
                Intrinsics.checkExpressionValueIsNotNull(str, "it");
                arrayList.add(new Cookie(str));
            }
            arrayList = (List) arrayList;
            httpURLConnection = arrayList.toArray(new Cookie[arrayList.size()]);
            if (httpURLConnection != null) {
                Cookie[] cookieArr = (Cookie[]) ((Object[]) httpURLConnection);
                return new CookieJar((Cookie[]) Arrays.copyOf(cookieArr, cookieArr.length));
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
        }

        public final void forceMethod$khttp(@org.jetbrains.annotations.NotNull java.net.HttpURLConnection r5, @org.jetbrains.annotations.NotNull java.lang.String r6) {
            /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1603198149.run(Unknown Source)
*/
            /*
            r4 = this;
            r0 = "$receiver";
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r5, r0);
            r0 = "method";
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r6, r0);
            r5.setRequestMethod(r6);	 Catch:{ ProtocolException -> 0x000e }
            goto L_0x006a;
        L_0x000e:
            r0 = 1;
            r1 = r5.getClass();	 Catch:{ NoSuchFieldException -> 0x0030 }
            r2 = "delegate";	 Catch:{ NoSuchFieldException -> 0x0030 }
            r1 = r1.getDeclaredField(r2);	 Catch:{ NoSuchFieldException -> 0x0030 }
            r2 = r1;	 Catch:{ NoSuchFieldException -> 0x0030 }
            r2 = (java.lang.reflect.Field) r2;	 Catch:{ NoSuchFieldException -> 0x0030 }
            r2.setAccessible(r0);	 Catch:{ NoSuchFieldException -> 0x0030 }
            r2 = kotlin.Unit.INSTANCE;	 Catch:{ NoSuchFieldException -> 0x0030 }
            r1 = (java.lang.reflect.Field) r1;	 Catch:{ NoSuchFieldException -> 0x0030 }
            r1 = r1.get(r5);	 Catch:{ NoSuchFieldException -> 0x0030 }
            r1 = (java.net.HttpURLConnection) r1;	 Catch:{ NoSuchFieldException -> 0x0030 }
            if (r1 == 0) goto L_0x0030;	 Catch:{ NoSuchFieldException -> 0x0030 }
        L_0x002b:
            r4.forceMethod$khttp(r1, r6);	 Catch:{ NoSuchFieldException -> 0x0030 }
            r1 = kotlin.Unit.INSTANCE;	 Catch:{ NoSuchFieldException -> 0x0030 }
        L_0x0030:
            r1 = r5.getClass();
            r1 = khttp.extensions.ExtensionsKt.getSuperclasses(r1);
            r1 = (java.util.Collection) r1;
            r2 = r5.getClass();
            r1 = kotlin.collections.CollectionsKt___CollectionsKt.plus(r1, r2);
            r1 = (java.lang.Iterable) r1;
            r1 = r1.iterator();
        L_0x0048:
            r2 = r1.hasNext();
            if (r2 == 0) goto L_0x006a;
        L_0x004e:
            r2 = r1.next();
            r2 = (java.lang.Class) r2;
            r3 = "method";	 Catch:{ NoSuchFieldException -> 0x0067 }
            r2 = r2.getDeclaredField(r3);	 Catch:{ NoSuchFieldException -> 0x0067 }
            r3 = r2;	 Catch:{ NoSuchFieldException -> 0x0067 }
            r3 = (java.lang.reflect.Field) r3;	 Catch:{ NoSuchFieldException -> 0x0067 }
            r3.setAccessible(r0);	 Catch:{ NoSuchFieldException -> 0x0067 }
            r3 = kotlin.Unit.INSTANCE;	 Catch:{ NoSuchFieldException -> 0x0067 }
            r2 = (java.lang.reflect.Field) r2;	 Catch:{ NoSuchFieldException -> 0x0067 }
            r2.set(r5, r6);	 Catch:{ NoSuchFieldException -> 0x0067 }
        L_0x0067:
            r2 = kotlin.Unit.INSTANCE;
            goto L_0x0048;
        L_0x006a:
            r5 = r5.getRequestMethod();
            r5 = kotlin.jvm.internal.Intrinsics.areEqual(r5, r6);
            if (r5 == 0) goto L_0x0075;
        L_0x0074:
            return;
        L_0x0075:
            r5 = new java.lang.IllegalStateException;
            r6 = "Check failed.";
            r6 = r6.toString();
            r5.<init>(r6);
            r5 = (java.lang.Throwable) r5;
            throw r5;
            */
            throw new UnsupportedOperationException("Method not decompiled: khttp.responses.GenericResponse.Companion.forceMethod$khttp(java.net.HttpURLConnection, java.lang.String):void");
        }

        @NotNull
        public final List<Function2<GenericResponse, HttpURLConnection, Unit>> getDefaultStartInitializers$khttp() {
            return GenericResponse.defaultStartInitializers;
        }

        @NotNull
        public final List<Function2<GenericResponse, HttpURLConnection, Unit>> getDefaultEndInitializers$khttp() {
            return GenericResponse.defaultEndInitializers;
        }
    }

    public GenericResponse(@NotNull Request request) {
        Intrinsics.checkParameterIsNotNull(request, "request");
        this.request = request;
    }

    @NotNull
    public Request getRequest() {
        return this.request;
    }

    @NotNull
    public final HttpURLConnection openRedirectingConnection$khttp(@NotNull URL url, @NotNull Response response, @NotNull Function1<? super HttpURLConnection, Unit> function1) {
        Response response2 = response;
        Function1<? super HttpURLConnection, Unit> function12 = function1;
        Intrinsics.checkParameterIsNotNull(url, "$receiver");
        Intrinsics.checkParameterIsNotNull(response2, "first");
        Intrinsics.checkParameterIsNotNull(function12, "receiver");
        URLConnection openConnection = url.openConnection();
        if (openConnection != null) {
            HttpURLConnection httpURLConnection = (HttpURLConnection) openConnection;
            httpURLConnection.setInstanceFollowRedirects(false);
            function12.invoke(httpURLConnection);
            httpURLConnection.connect();
            Unit unit = Unit.INSTANCE;
            if (response.getRequest().getAllowRedirects()) {
                int i = 1;
                int responseCode = httpURLConnection.getResponseCode();
                int i2 = responseCode < 301 ? 0 : 1;
                if (responseCode > 303) {
                    i = 0;
                }
                if ((i & i2) != 0) {
                    CookieJar cookieJar$khttp = Companion.getCookieJar$khttp(httpURLConnection);
                    Request request = response.getRequest();
                    String method = request.getMethod();
                    String toASCIIString = url.toURI().resolve(httpURLConnection.getHeaderField("Location")).toASCIIString();
                    Intrinsics.checkExpressionValueIsNotNull(toASCIIString, "this@openRedirectingConn…cation\")).toASCIIString()");
                    Map headers = request.getHeaders();
                    Map params = request.getParams();
                    Object data = request.getData();
                    Object json = request.getJson();
                    Authorization auth = request.getAuth();
                    Map map = cookieJar$khttp;
                    Map cookies = request.getCookies();
                    if (cookies == null) {
                        cookies = MapsKt__MapsKt.emptyMap();
                    }
                    GenericResponse genericResponse = new GenericResponse(new GenericRequest(method, toASCIIString, params, headers, data, json, auth, MapsKt__MapsKt.plus(map, cookies), request.getTimeout(), Boolean.valueOf(false), request.getStream(), request.getFiles()));
                    genericResponse._cookies.putAll(map);
                    genericResponse._history.addAll(response.getHistory());
                    if (response2 != null) {
                        ((GenericResponse) response2)._history.add(genericResponse);
                        genericResponse.init$khttp();
                    } else {
                        throw new TypeCastException("null cannot be cast to non-null type khttp.responses.GenericResponse");
                    }
                }
            }
            return httpURLConnection;
        }
        throw new TypeCastException("null cannot be cast to non-null type java.net.HttpURLConnection");
    }

    @NotNull
    public final List<Response> get_history$khttp() {
        return this._history;
    }

    public final void set_history$khttp(@NotNull List<Response> list) {
        Intrinsics.checkParameterIsNotNull(list, "<set-?>");
        this._history = list;
    }

    @NotNull
    public List<Response> getHistory() {
        List<Response> unmodifiableList = Collections.unmodifiableList(this._history);
        Intrinsics.checkExpressionValueIsNotNull(unmodifiableList, "Collections.unmodifiableList(this._history)");
        return unmodifiableList;
    }

    @NotNull
    public HttpURLConnection getConnection() {
        if (this._connection == null) {
            URL url = new URL(getRequest().getUrl());
            Response response = (Response) CollectionsKt___CollectionsKt.firstOrNull(this._history);
            if (response == null) {
                GenericResponse genericResponse = this;
                genericResponse._history.add(genericResponse);
                Unit unit = Unit.INSTANCE;
                response = this;
            }
            this._connection = openRedirectingConnection$khttp(url, response, new GenericResponse$connection$2(this));
        }
        HttpURLConnection httpURLConnection = this._connection;
        if (httpURLConnection != null) {
            return httpURLConnection;
        }
        throw new IllegalStateException("Set to null by another thread");
    }

    public int getStatusCode() {
        if (this._statusCode == null) {
            this._statusCode = Integer.valueOf(getConnection().getResponseCode());
        }
        Integer num = this._statusCode;
        if (num != null) {
            return num.intValue();
        }
        throw new IllegalStateException("Set to null by another thread");
    }

    @NotNull
    public Map<String, String> getHeaders() {
        Map headerFields;
        if (this._headers == null) {
            headerFields = getConnection().getHeaderFields();
            Map linkedHashMap = new LinkedHashMap(MapsKt__MapsKt.mapCapacity(headerFields.size()));
            for (Entry entry : headerFields.entrySet()) {
                linkedHashMap.put(entry.getKey(), CollectionsKt___CollectionsKt.joinToString$default((Iterable) entry.getValue(), ", ", null, null, 0, null, null, 62, null));
            }
            LinkedHashMap linkedHashMap2 = new LinkedHashMap();
            for (Entry entry2 : linkedHashMap.entrySet()) {
                if ((((String) entry2.getKey()) != null ? 1 : null) != null) {
                    linkedHashMap2.put(entry2.getKey(), entry2.getValue());
                }
            }
            this._headers = linkedHashMap2;
        }
        headerFields = this._headers;
        if (headerFields != null) {
            return new CaseInsensitiveMap(headerFields);
        }
        throw new IllegalStateException("Set to null by another thread");
    }

    private final java.io.InputStream getRealInputStream(@org.jetbrains.annotations.NotNull java.net.HttpURLConnection r4) {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1603198149.run(Unknown Source)
*/
        /*
        r3 = this;
        r0 = r4.getInputStream();	 Catch:{ IOException -> 0x0005 }
        goto L_0x0009;
    L_0x0005:
        r0 = r4.getErrorStream();
    L_0x0009:
        r4 = r3.getHeaders();
        r1 = "Content-Encoding";
        r4 = r4.get(r1);
        r4 = (java.lang.String) r4;
        if (r4 == 0) goto L_0x002d;
    L_0x0017:
        if (r4 == 0) goto L_0x0025;
    L_0x0019:
        r4 = (java.lang.String) r4;
        r4 = r4.toLowerCase();
        r1 = "(this as java.lang.String).toLowerCase()";
        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r4, r1);
        goto L_0x002e;
    L_0x0025:
        r4 = new kotlin.TypeCastException;
        r0 = "null cannot be cast to non-null type java.lang.String";
        r4.<init>(r0);
        throw r4;
    L_0x002d:
        r4 = 0;
    L_0x002e:
        if (r4 != 0) goto L_0x0031;
    L_0x0030:
        goto L_0x0062;
    L_0x0031:
        r1 = r4.hashCode();
        r2 = 3189082; // 0x30a95a float:4.468856E-39 double:1.575616E-317;
        if (r1 == r2) goto L_0x0051;
    L_0x003a:
        r2 = 1545112619; // 0x5c188c2b float:1.71753251E17 double:7.63387064E-315;
        if (r1 == r2) goto L_0x0040;
    L_0x003f:
        goto L_0x0062;
    L_0x0040:
        r1 = "deflate";
        r4 = r4.equals(r1);
        if (r4 == 0) goto L_0x0062;
    L_0x0048:
        r4 = new java.util.zip.InflaterInputStream;
        r4.<init>(r0);
        r0 = r4;
        r0 = (java.io.InputStream) r0;
        goto L_0x0067;
    L_0x0051:
        r1 = "gzip";
        r4 = r4.equals(r1);
        if (r4 == 0) goto L_0x0062;
    L_0x0059:
        r4 = new java.util.zip.GZIPInputStream;
        r4.<init>(r0);
        r0 = r4;
        r0 = (java.io.InputStream) r0;
        goto L_0x0067;
    L_0x0062:
        r4 = "stream";
        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r4);
    L_0x0067:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: khttp.responses.GenericResponse.getRealInputStream(java.net.HttpURLConnection):java.io.InputStream");
    }

    @NotNull
    public InputStream getRaw() {
        if (this._raw == null) {
            this._raw = getRealInputStream(getConnection());
        }
        InputStream inputStream = this._raw;
        if (inputStream != null) {
            return inputStream;
        }
        throw new IllegalStateException("Set to null by another thread");
    }

    @org.jetbrains.annotations.NotNull
    public byte[] getContent() {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1603198149.run(Unknown Source)
*/
        /*
        r5 = this;
        r0 = r5._content;
        if (r0 != 0) goto L_0x0030;
    L_0x0004:
        r0 = r5.getRaw();
        r0 = (java.io.Closeable) r0;
        r1 = 1;
        r2 = 0;
        r3 = r0;	 Catch:{ Exception -> 0x0020, all -> 0x001c }
        r3 = (java.io.InputStream) r3;	 Catch:{ Exception -> 0x0020, all -> 0x001c }
        r4 = 0;	 Catch:{ Exception -> 0x0020, all -> 0x001c }
        r3 = kotlin.io.ByteStreamsKt.readBytes$default(r3, r2, r1, r4);	 Catch:{ Exception -> 0x0020, all -> 0x001c }
        r0.close();
        r3 = (byte[]) r3;
        r5._content = r3;
        goto L_0x0030;
    L_0x001c:
        r1 = move-exception;
        r2 = r1;
        r1 = 0;
        goto L_0x002a;
    L_0x0020:
        r2 = move-exception;
        r0.close();	 Catch:{ Exception -> 0x0027 }
        goto L_0x0027;
    L_0x0025:
        r2 = move-exception;
        goto L_0x002a;
    L_0x0027:
        r2 = (java.lang.Throwable) r2;	 Catch:{ all -> 0x0025 }
        throw r2;	 Catch:{ all -> 0x0025 }
    L_0x002a:
        if (r1 != 0) goto L_0x002f;
    L_0x002c:
        r0.close();
    L_0x002f:
        throw r2;
    L_0x0030:
        r0 = r5._content;
        if (r0 == 0) goto L_0x0035;
    L_0x0034:
        return r0;
    L_0x0035:
        r0 = new java.lang.IllegalStateException;
        r1 = "Set to null by another thread";
        r0.<init>(r1);
        r0 = (java.lang.Throwable) r0;
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: khttp.responses.GenericResponse.getContent():byte[]");
    }

    @NotNull
    public String getText() {
        return new String(getContent(), getEncoding());
    }

    @NotNull
    public JSONObject getJsonObject() {
        return new JSONObject(getText());
    }

    @NotNull
    public JSONArray getJsonArray() {
        return new JSONArray(getText());
    }

    @NotNull
    public CookieJar getCookies() {
        init$khttp();
        return this._cookies;
    }

    @NotNull
    public String getUrl() {
        String url = getConnection().getURL().toString();
        Intrinsics.checkExpressionValueIsNotNull(url, "this.connection.url.toString()");
        return url;
    }

    private final void set_encoding(Charset charset) {
        this._encoding = charset;
    }

    @NotNull
    public Charset getEncoding() {
        Charset charset;
        if (this._encoding != null) {
            charset = this._encoding;
            if (charset != null) {
                return charset;
            }
            throw new IllegalStateException("Set to null by another thread");
        }
        String str = (String) getHeaders().get("Content-Type");
        if (str == null) {
            return Charsets.UTF_8;
        }
        Iterable<String> split$default = StringsKt__StringsKt.split$default((CharSequence) str, new String[]{";"}, false, 0, 6, null);
        Collection arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(split$default, 10));
        for (String split$default2 : split$default) {
            arrayList.add(StringsKt__StringsKt.split$default((CharSequence) split$default2, new String[]{"="}, false, 0, 6, null));
        }
        Collection arrayList2 = new ArrayList();
        for (Object next : (List) arrayList) {
            Object next2;
            String str2 = (String) ((List) next2).get(0);
            if (str2 != null) {
                str2 = StringsKt__StringsKt.trim((CharSequence) str2).toString();
                if (str2 != null) {
                    Object toLowerCase = str2.toLowerCase();
                    Intrinsics.checkExpressionValueIsNotNull(toLowerCase, "(this as java.lang.String).toLowerCase()");
                    if (Intrinsics.areEqual(toLowerCase, (Object) "charset")) {
                        arrayList2.add(next2);
                    }
                } else {
                    throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                }
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
        }
        arrayList = new ArrayList();
        Iterator it = ((List) arrayList2).iterator();
        while (true) {
            int i = 1;
            if (!it.hasNext()) {
                break;
            }
            next2 = it.next();
            if (((List) next2).size() != 2) {
                i = 0;
            }
            if (i != 0) {
                arrayList.add(next2);
            }
        }
        Iterable<List> iterable = (List) arrayList;
        arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(iterable, 10));
        for (List list : iterable) {
            arrayList2.add((String) list.get(1));
        }
        str = (String) CollectionsKt___CollectionsKt.firstOrNull((List) arrayList2);
        if (str != null) {
            if (str != null) {
                str = str.toUpperCase();
                Intrinsics.checkExpressionValueIsNotNull(str, "(this as java.lang.String).toUpperCase()");
                if (str != null) {
                    charset = Charset.forName(str);
                    Intrinsics.checkExpressionValueIsNotNull(charset, "Charset.forName(charset?…?: Charsets.UTF_8.name())");
                    return charset;
                }
            }
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        str = Charsets.UTF_8.name();
        charset = Charset.forName(str);
        Intrinsics.checkExpressionValueIsNotNull(charset, "Charset.forName(charset?…?: Charsets.UTF_8.name())");
        return charset;
    }

    public void setEncoding(@NotNull Charset charset) {
        Intrinsics.checkParameterIsNotNull(charset, "value");
        set_encoding(charset);
    }

    @NotNull
    public final List<Function2<GenericResponse, HttpURLConnection, Unit>> getInitializers() {
        return this.initializers;
    }

    @NotNull
    public Iterator<byte[]> contentIterator(int i) {
        return new GenericResponse$contentIterator$1(this, i);
    }

    @NotNull
    public Iterator<byte[]> lineIterator(int i, @Nullable byte[] bArr) {
        return new GenericResponse$lineIterator$1(this, bArr, i);
    }

    @NotNull
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<Response [");
        stringBuilder.append(getStatusCode());
        stringBuilder.append("]>");
        return stringBuilder.toString();
    }

    private final <T extends java.net.URLConnection> java.lang.Object getField(@org.jetbrains.annotations.NotNull java.lang.Class<T> r5, java.lang.String r6, T r7) {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1603198149.run(Unknown Source)
*/
        /*
        r4 = this;
        r0 = khttp.extensions.ExtensionsKt.getSuperclasses(r5);
        r0 = (java.util.Collection) r0;
        r5 = kotlin.collections.CollectionsKt___CollectionsKt.plus(r0, r5);
        r5 = (java.lang.Iterable) r5;
        r5 = r5.iterator();
    L_0x0010:
        r0 = r5.hasNext();
        if (r0 == 0) goto L_0x0065;
    L_0x0016:
        r0 = r5.next();
        r0 = (java.lang.Class) r0;
        r1 = 1;
        r2 = r0.getDeclaredField(r6);	 Catch:{ Exception -> 0x003c }
        r3 = r2;	 Catch:{ Exception -> 0x003c }
        r3 = (java.lang.reflect.Field) r3;	 Catch:{ Exception -> 0x003c }
        r3.setAccessible(r1);	 Catch:{ Exception -> 0x003c }
        r3 = kotlin.Unit.INSTANCE;	 Catch:{ Exception -> 0x003c }
        r2 = (java.lang.reflect.Field) r2;	 Catch:{ Exception -> 0x003c }
        r2 = r2.get(r7);	 Catch:{ Exception -> 0x003c }
        if (r2 == 0) goto L_0x0034;	 Catch:{ Exception -> 0x003c }
    L_0x0031:
        r3 = kotlin.Unit.INSTANCE;	 Catch:{ Exception -> 0x003c }
        return r2;	 Catch:{ Exception -> 0x003c }
    L_0x0034:
        r2 = new java.lang.Exception;	 Catch:{ Exception -> 0x003c }
        r2.<init>();	 Catch:{ Exception -> 0x003c }
        r2 = (java.lang.Throwable) r2;	 Catch:{ Exception -> 0x003c }
        throw r2;	 Catch:{ Exception -> 0x003c }
    L_0x003c:
        r2 = "delegate";	 Catch:{ NoSuchFieldException -> 0x0062 }
        r0 = r0.getDeclaredField(r2);	 Catch:{ NoSuchFieldException -> 0x0062 }
        r2 = r0;	 Catch:{ NoSuchFieldException -> 0x0062 }
        r2 = (java.lang.reflect.Field) r2;	 Catch:{ NoSuchFieldException -> 0x0062 }
        r2.setAccessible(r1);	 Catch:{ NoSuchFieldException -> 0x0062 }
        r1 = kotlin.Unit.INSTANCE;	 Catch:{ NoSuchFieldException -> 0x0062 }
        r0 = (java.lang.reflect.Field) r0;	 Catch:{ NoSuchFieldException -> 0x0062 }
        r0 = r0.get(r7);	 Catch:{ NoSuchFieldException -> 0x0062 }
        r1 = r0 instanceof java.net.URLConnection;	 Catch:{ NoSuchFieldException -> 0x0062 }
        if (r1 == 0) goto L_0x0062;	 Catch:{ NoSuchFieldException -> 0x0062 }
    L_0x0054:
        r1 = r0;	 Catch:{ NoSuchFieldException -> 0x0062 }
        r1 = (java.net.URLConnection) r1;	 Catch:{ NoSuchFieldException -> 0x0062 }
        r1 = r1.getClass();	 Catch:{ NoSuchFieldException -> 0x0062 }
        r0 = (java.net.URLConnection) r0;	 Catch:{ NoSuchFieldException -> 0x0062 }
        r0 = r4.getField(r1, r6, r0);	 Catch:{ NoSuchFieldException -> 0x0062 }
        return r0;
    L_0x0062:
        r0 = kotlin.Unit.INSTANCE;
        goto L_0x0010;
    L_0x0065:
        r5 = 0;
        return r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: khttp.responses.GenericResponse.getField(java.lang.Class, java.lang.String, java.net.URLConnection):java.lang.Object");
    }

    private final void updateRequestHeaders() {
        Map headers = getRequest().getHeaders();
        if (headers != null) {
            headers = TypeIntrinsics.asMutableMap(headers);
            Object field = getField(getConnection().getClass(), "requests", getConnection());
            if (field != null) {
                Method declaredMethod = field.getClass().getDeclaredMethod("getHeaders", new Class[0]);
                declaredMethod.setAccessible(true);
                Unit unit = Unit.INSTANCE;
                field = declaredMethod.invoke(field, new Object[0]);
                if (field != null) {
                    Map map = (Map) field;
                    LinkedHashMap linkedHashMap = new LinkedHashMap();
                    for (Entry entry : map.entrySet()) {
                        if ((CollectionsKt___CollectionsKt.filterNotNull((List) entry.getValue()).isEmpty() ^ 1) != 0) {
                            linkedHashMap.put(entry.getKey(), entry.getValue());
                        }
                    }
                    Map map2 = linkedHashMap;
                    map = new LinkedHashMap(MapsKt__MapsKt.mapCapacity(map2.size()));
                    for (Entry entry2 : map2.entrySet()) {
                        map.put(entry2.getKey(), CollectionsKt___CollectionsKt.joinToString$default((Iterable) entry2.getValue(), ", ", null, null, 0, null, null, 62, null));
                    }
                    headers.putAll(map);
                    return;
                }
                throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.Map<kotlin.String, kotlin.collections.List<kotlin.String>>");
            }
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.MutableMap<kotlin.String, kotlin.String>");
    }

    public final void init$khttp() {
        if (getRequest().getStream()) {
            getConnection();
        } else {
            getContent();
        }
        updateRequestHeaders();
    }
}
