package com.comquas.tgif3_demo;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.Arrays;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.Charsets;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004J\u000e\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0006J\u0016\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0004¨\u0006\u000b"}, d2 = {"Lcom/comquas/tgif3_demo/HmacSha256;", "", "()V", "hash", "", "payload", "", "hex", "data", "hmacSHA256", "key", "app_release"}, k = 1, mv = {1, 1, 11})
/* compiled from: HashDemoActivity.kt */
public final class HmacSha256 {
    public static final HmacSha256 INSTANCE = new HmacSha256();

    private HmacSha256() {
    }

    @NotNull
    public final String hash(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "payload");
        byte[] bytes = str.getBytes(Charsets.UTF_8);
        Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
        return hash(bytes);
    }

    @NotNull
    public final String hash(@NotNull byte[] bArr) {
        Intrinsics.checkParameterIsNotNull(bArr, "payload");
        try {
            bArr = MessageDigest.getInstance("SHA-256").digest(bArr);
            Intrinsics.checkExpressionValueIsNotNull(bArr, "MessageDigest.getInstanc…SHA-256\").digest(payload)");
            return hex(bArr);
        } catch (byte[] bArr2) {
            throw new RuntimeException((Throwable) bArr2);
        }
    }

    @NotNull
    public final byte[] hmacSHA256(@NotNull byte[] bArr, @NotNull String str) {
        Intrinsics.checkParameterIsNotNull(bArr, "key");
        Intrinsics.checkParameterIsNotNull(str, "data");
        try {
            String str2 = "HmacSHA256";
            Mac instance = Mac.getInstance(str2);
            instance.init(new SecretKeySpec(bArr, str2));
            bArr = Charset.forName("UTF8");
            Intrinsics.checkExpressionValueIsNotNull(bArr, "Charset.forName(charsetName)");
            bArr = str.getBytes(bArr);
            Intrinsics.checkExpressionValueIsNotNull(bArr, "(this as java.lang.String).getBytes(charset)");
            bArr = instance.doFinal(bArr);
            Intrinsics.checkExpressionValueIsNotNull(bArr, "doFinal(data.toByteArray(charset(\"UTF8\")))");
            Intrinsics.checkExpressionValueIsNotNull(bArr, "Mac.getInstance(algorith…arset(\"UTF8\")))\n        }");
            return bArr;
        } catch (byte[] bArr2) {
            throw ((Throwable) new RuntimeException("Could not run HMAC SHA256", (Throwable) bArr2));
        }
    }

    @NotNull
    public final String hex(@NotNull byte[] bArr) {
        Intrinsics.checkParameterIsNotNull(bArr, "data");
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bArr) {
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            Object[] objArr = new Object[]{Byte.valueOf(b)};
            String format = String.format("%02x", Arrays.copyOf(objArr, objArr.length));
            Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
            stringBuilder.append(format);
            Intrinsics.checkExpressionValueIsNotNull(stringBuilder, "acc.append(String.format(\"%02x\", next))");
        }
        bArr = stringBuilder.toString();
        Intrinsics.checkExpressionValueIsNotNull(bArr, "data.fold(StringBuilder(…, next))\n    }.toString()");
        if (bArr != null) {
            bArr = bArr.toLowerCase();
            Intrinsics.checkExpressionValueIsNotNull(bArr, "(this as java.lang.String).toLowerCase()");
            return bArr;
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }
}
