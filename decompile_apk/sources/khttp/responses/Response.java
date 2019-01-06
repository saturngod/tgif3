package khttp.responses;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import khttp.requests.Request;
import khttp.structures.cookie.CookieJar;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONObject;

@Metadata(bv = {1, 0, 0}, d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010(\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u0018\u00106\u001a\b\u0012\u0004\u0012\u00020\u0007072\b\b\u0002\u00108\u001a\u00020.H&J$\u00109\u001a\b\u0012\u0004\u0012\u00020\u0007072\b\b\u0002\u00108\u001a\u00020.2\n\b\u0002\u0010:\u001a\u0004\u0018\u00010\u0007H&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0012\u0010\n\u001a\u00020\u000bX¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0018\u0010\u000e\u001a\u00020\u000fX¦\u000e¢\u0006\f\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001e\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00160\u0015X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R\u0018\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00000\u001aX¦\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001cR\u0012\u0010\u001d\u001a\u00020\u001eX¦\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010 R\u0012\u0010!\u001a\u00020\"X¦\u0004¢\u0006\u0006\u001a\u0004\b#\u0010$R\u0012\u0010%\u001a\u00020&X¦\u0004¢\u0006\u0006\u001a\u0004\b'\u0010(R\u0012\u0010)\u001a\u00020*X¦\u0004¢\u0006\u0006\u001a\u0004\b+\u0010,R\u0012\u0010-\u001a\u00020.X¦\u0004¢\u0006\u0006\u001a\u0004\b/\u00100R\u0012\u00101\u001a\u00020\u0016X¦\u0004¢\u0006\u0006\u001a\u0004\b2\u00103R\u0012\u00104\u001a\u00020\u0016X¦\u0004¢\u0006\u0006\u001a\u0004\b5\u00103¨\u0006;"}, d2 = {"Lkhttp/responses/Response;", "", "connection", "Ljava/net/HttpURLConnection;", "getConnection", "()Ljava/net/HttpURLConnection;", "content", "", "getContent", "()[B", "cookies", "Lkhttp/structures/cookie/CookieJar;", "getCookies", "()Lkhttp/structures/cookie/CookieJar;", "encoding", "Ljava/nio/charset/Charset;", "getEncoding", "()Ljava/nio/charset/Charset;", "setEncoding", "(Ljava/nio/charset/Charset;)V", "headers", "", "", "getHeaders", "()Ljava/util/Map;", "history", "", "getHistory", "()Ljava/util/List;", "jsonArray", "Lorg/json/JSONArray;", "getJsonArray", "()Lorg/json/JSONArray;", "jsonObject", "Lorg/json/JSONObject;", "getJsonObject", "()Lorg/json/JSONObject;", "raw", "Ljava/io/InputStream;", "getRaw", "()Ljava/io/InputStream;", "request", "Lkhttp/requests/Request;", "getRequest", "()Lkhttp/requests/Request;", "statusCode", "", "getStatusCode", "()I", "text", "getText", "()Ljava/lang/String;", "url", "getUrl", "contentIterator", "", "chunkSize", "lineIterator", "delimiter", "khttp"}, k = 1, mv = {1, 1, 1})
/* compiled from: Response.kt */
public interface Response {

    @Metadata(bv = {1, 0, 0}, k = 3, mv = {1, 1, 1})
    /* compiled from: Response.kt */
    public static final class DefaultImpls {
        @NotNull
        public static /* bridge */ /* synthetic */ Iterator contentIterator$default(Response response, int i, int i2, Object obj) {
            if (obj == null) {
                if ((i2 & 1) != 0) {
                    i = 1;
                }
                return response.contentIterator(i);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: contentIterator");
        }

        @NotNull
        public static /* bridge */ /* synthetic */ Iterator lineIterator$default(Response response, int i, byte[] bArr, int i2, Object obj) {
            if (obj == null) {
                if ((i2 & 1) != null) {
                    i = 512;
                }
                if ((i2 & 2) != 0) {
                    bArr = null;
                }
                return response.lineIterator(i, bArr);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: lineIterator");
        }
    }

    @NotNull
    Iterator<byte[]> contentIterator(int i);

    @NotNull
    HttpURLConnection getConnection();

    @NotNull
    byte[] getContent();

    @NotNull
    CookieJar getCookies();

    @NotNull
    Charset getEncoding();

    @NotNull
    Map<String, String> getHeaders();

    @NotNull
    List<Response> getHistory();

    @NotNull
    JSONArray getJsonArray();

    @NotNull
    JSONObject getJsonObject();

    @NotNull
    InputStream getRaw();

    @NotNull
    Request getRequest();

    int getStatusCode();

    @NotNull
    String getText();

    @NotNull
    String getUrl();

    @NotNull
    Iterator<byte[]> lineIterator(int i, @Nullable byte[] bArr);

    void setEncoding(@NotNull Charset charset);
}
