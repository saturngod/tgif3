package khttp.structures.authorization;

import java.nio.charset.Charset;
import java.util.Base64;
import java.util.Base64.Encoder;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 0}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\b\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001R \u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u00078VX\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000b¨\u0006\u0010"}, d2 = {"Lkhttp/structures/authorization/BasicAuthorization;", "Lkhttp/structures/authorization/Authorization;", "user", "", "password", "(Ljava/lang/String;Ljava/lang/String;)V", "header", "Lkotlin/Pair;", "getHeader", "()Lkotlin/Pair;", "getPassword", "()Ljava/lang/String;", "getUser", "component1", "component2", "copy", "khttp"}, k = 1, mv = {1, 1, 1})
/* compiled from: BasicAuthorization.kt */
public final class BasicAuthorization implements Authorization {
    @NotNull
    private final String password;
    @NotNull
    private final String user;

    @NotNull
    public static /* bridge */ /* synthetic */ BasicAuthorization copy$default(BasicAuthorization basicAuthorization, String str, String str2, int i, Object obj) {
        if (obj == null) {
            if ((i & 1) != 0) {
                str = basicAuthorization.user;
            }
            if ((i & 2) != 0) {
                str2 = basicAuthorization.password;
            }
            return basicAuthorization.copy(str, str2);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: copy");
    }

    @NotNull
    public final String component1() {
        return this.user;
    }

    @NotNull
    public final String component2() {
        return this.password;
    }

    @NotNull
    public final BasicAuthorization copy(@NotNull String str, @NotNull String str2) {
        Intrinsics.checkParameterIsNotNull(str, "user");
        Intrinsics.checkParameterIsNotNull(str2, "password");
        return new BasicAuthorization(str, str2);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof BasicAuthorization) {
                BasicAuthorization basicAuthorization = (BasicAuthorization) obj;
                if (Intrinsics.areEqual(this.user, basicAuthorization.user) && Intrinsics.areEqual(this.password, basicAuthorization.password)) {
                }
            }
            return false;
        }
        return true;
    }

    public int hashCode() {
        String str = this.user;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.password;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("BasicAuthorization(user=");
        stringBuilder.append(this.user);
        stringBuilder.append(", password=");
        stringBuilder.append(this.password);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public BasicAuthorization(@NotNull String str, @NotNull String str2) {
        Intrinsics.checkParameterIsNotNull(str, "user");
        Intrinsics.checkParameterIsNotNull(str2, "password");
        this.user = str;
        this.password = str2;
    }

    @NotNull
    public final String getPassword() {
        return this.password;
    }

    @NotNull
    public final String getUser() {
        return this.user;
    }

    @NotNull
    public Pair<String, String> getHeader() {
        Encoder encoder = Base64.getEncoder();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.user);
        stringBuilder.append(":");
        stringBuilder.append(this.password);
        String stringBuilder2 = stringBuilder.toString();
        Charset charset = Charsets.UTF_8;
        if (stringBuilder2 != null) {
            Object bytes = stringBuilder2.getBytes(charset);
            Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
            String str = new String(encoder.encode(bytes), Charsets.UTF_8);
            stringBuilder = new StringBuilder();
            stringBuilder.append("Basic ");
            stringBuilder.append(str);
            return TuplesKt.to("Authorization", stringBuilder.toString());
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }
}
