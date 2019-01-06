package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\u001a\u0010\u0010\u000f\u001a\u00020\u0005*\u0006\u0012\u0002\b\u00030\u0010H\u0000\"\u0014\u0010\u0000\u001a\u00020\u0001X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003\"\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0007\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000\"\u000e\u0010\b\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000\"\u0018\u0010\t\u001a\u00020\u0005*\u00020\n8@X\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f\"\u0018\u0010\r\u001a\u00020\u0005*\u00020\n8@X\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\f¨\u0006\u0011"}, d2 = {"DEBUG", "", "getDEBUG", "()Z", "DEBUG_PROPERTY_NAME", "", "DEBUG_PROPERTY_VALUE_AUTO", "DEBUG_PROPERTY_VALUE_OFF", "DEBUG_PROPERTY_VALUE_ON", "classSimpleName", "", "getClassSimpleName", "(Ljava/lang/Object;)Ljava/lang/String;", "hexAddress", "getHexAddress", "toDebugString", "Lkotlin/coroutines/Continuation;", "kotlinx-coroutines-core"}, k = 2, mv = {1, 1, 13})
/* compiled from: Debug.kt */
public final class DebugKt {
    private static final boolean DEBUG;
    @NotNull
    public static final String DEBUG_PROPERTY_NAME = "kotlinx.coroutines.debug";
    @NotNull
    public static final String DEBUG_PROPERTY_VALUE_AUTO = "auto";
    @NotNull
    public static final String DEBUG_PROPERTY_VALUE_OFF = "off";
    @NotNull
    public static final String DEBUG_PROPERTY_VALUE_ON = "on";

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = "kotlinx.coroutines.debug";
        r0 = kotlinx.coroutines.internal.SystemPropsKt.systemProp(r0);
        if (r0 != 0) goto L_0x0009;
    L_0x0008:
        goto L_0x0025;
    L_0x0009:
        r1 = r0.hashCode();
        if (r1 == 0) goto L_0x003f;
    L_0x000f:
        r2 = 3551; // 0xddf float:4.976E-42 double:1.7544E-320;
        if (r1 == r2) goto L_0x0036;
    L_0x0013:
        r2 = 109935; // 0x1ad6f float:1.54052E-40 double:5.4315E-319;
        if (r1 == r2) goto L_0x002c;
    L_0x0018:
        r2 = 3005871; // 0x2dddaf float:4.212122E-39 double:1.4850976E-317;
        if (r1 != r2) goto L_0x004b;
    L_0x001d:
        r1 = "auto";
        r1 = r0.equals(r1);
        if (r1 == 0) goto L_0x004b;
    L_0x0025:
        r0 = kotlinx.coroutines.CoroutineId.class;
        r0 = r0.desiredAssertionStatus();
        goto L_0x0048;
    L_0x002c:
        r1 = "off";
        r1 = r0.equals(r1);
        if (r1 == 0) goto L_0x004b;
    L_0x0034:
        r0 = 0;
        goto L_0x0048;
    L_0x0036:
        r1 = "on";
        r1 = r0.equals(r1);
        if (r1 == 0) goto L_0x004b;
    L_0x003e:
        goto L_0x0047;
    L_0x003f:
        r1 = "";
        r1 = r0.equals(r1);
        if (r1 == 0) goto L_0x004b;
    L_0x0047:
        r0 = 1;
    L_0x0048:
        DEBUG = r0;
        return;
    L_0x004b:
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "System property 'kotlinx.coroutines.debug' has unrecognized value '";
        r1.append(r2);
        r1.append(r0);
        r0 = 39;
        r1.append(r0);
        r0 = r1.toString();
        r1 = new java.lang.IllegalStateException;
        r0 = r0.toString();
        r1.<init>(r0);
        r1 = (java.lang.Throwable) r1;
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.DebugKt.<clinit>():void");
    }

    public static final boolean getDEBUG() {
        return DEBUG;
    }

    @NotNull
    public static final String getHexAddress(@NotNull Object obj) {
        Intrinsics.checkParameterIsNotNull(obj, "receiver$0");
        obj = Integer.toHexString(System.identityHashCode(obj));
        Intrinsics.checkExpressionValueIsNotNull(obj, "Integer.toHexString(System.identityHashCode(this))");
        return obj;
    }

    @NotNull
    public static final String toDebugString(@NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "receiver$0");
        if (continuation instanceof DispatchedContinuation) {
            return continuation.toString();
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(continuation);
        stringBuilder.append('@');
        stringBuilder.append(getHexAddress(continuation));
        return stringBuilder.toString();
    }

    @NotNull
    public static final String getClassSimpleName(@NotNull Object obj) {
        Intrinsics.checkParameterIsNotNull(obj, "receiver$0");
        obj = obj.getClass().getSimpleName();
        Intrinsics.checkExpressionValueIsNotNull(obj, "this::class.java.simpleName");
        return obj;
    }
}
