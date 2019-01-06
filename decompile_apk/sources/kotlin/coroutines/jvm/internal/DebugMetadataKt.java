package kotlin.coroutines.jvm.internal;

import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.TypeCastException;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0002\u001a\u000e\u0010\u0006\u001a\u0004\u0018\u00010\u0007*\u00020\bH\u0002\u001a\f\u0010\t\u001a\u00020\u0001*\u00020\bH\u0002\u001a\u0019\u0010\n\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b*\u00020\bH\u0001¢\u0006\u0002\u0010\r\u001a\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u000f*\u00020\bH\u0001¢\u0006\u0002\b\u0010\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"COROUTINES_DEBUG_METADATA_VERSION", "", "checkDebugMetadataVersion", "", "expected", "actual", "getDebugMetadataAnnotation", "Lkotlin/coroutines/jvm/internal/DebugMetadata;", "Lkotlin/coroutines/jvm/internal/BaseContinuationImpl;", "getLabel", "getSpilledVariableFieldMapping", "", "", "(Lkotlin/coroutines/jvm/internal/BaseContinuationImpl;)[Ljava/lang/String;", "getStackTraceElementImpl", "Ljava/lang/StackTraceElement;", "getStackTraceElement", "kotlin-stdlib"}, k = 2, mv = {1, 1, 13})
/* compiled from: DebugMetadata.kt */
public final class DebugMetadataKt {
    private static final int COROUTINES_DEBUG_METADATA_VERSION = 1;

    @SinceKotlin(version = "1.3")
    @Nullable
    @JvmName(name = "getStackTraceElement")
    public static final StackTraceElement getStackTraceElement(@NotNull BaseContinuationImpl baseContinuationImpl) {
        Intrinsics.checkParameterIsNotNull(baseContinuationImpl, "receiver$0");
        DebugMetadata debugMetadataAnnotation = getDebugMetadataAnnotation(baseContinuationImpl);
        if (debugMetadataAnnotation == null) {
            return null;
        }
        checkDebugMetadataVersion(1, debugMetadataAnnotation.m13v());
        baseContinuationImpl = getLabel(baseContinuationImpl);
        if (baseContinuationImpl < null) {
            baseContinuationImpl = -1;
        } else {
            baseContinuationImpl = debugMetadataAnnotation.m9l()[baseContinuationImpl];
        }
        return new StackTraceElement(debugMetadataAnnotation.m6c(), debugMetadataAnnotation.m10m(), debugMetadataAnnotation.m7f(), baseContinuationImpl);
    }

    private static final DebugMetadata getDebugMetadataAnnotation(@NotNull BaseContinuationImpl baseContinuationImpl) {
        return (DebugMetadata) baseContinuationImpl.getClass().getAnnotation(DebugMetadata.class);
    }

    private static final int getLabel(@org.jetbrains.annotations.NotNull kotlin.coroutines.jvm.internal.BaseContinuationImpl r2) {
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
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1603198149.run(Unknown Source)
*/
        /*
        r0 = r2.getClass();	 Catch:{ Exception -> 0x0028 }
        r1 = "label";	 Catch:{ Exception -> 0x0028 }
        r0 = r0.getDeclaredField(r1);	 Catch:{ Exception -> 0x0028 }
        r1 = "field";	 Catch:{ Exception -> 0x0028 }
        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1);	 Catch:{ Exception -> 0x0028 }
        r1 = 1;	 Catch:{ Exception -> 0x0028 }
        r0.setAccessible(r1);	 Catch:{ Exception -> 0x0028 }
        r2 = r0.get(r2);	 Catch:{ Exception -> 0x0028 }
        r0 = r2 instanceof java.lang.Integer;	 Catch:{ Exception -> 0x0028 }
        if (r0 != 0) goto L_0x001c;	 Catch:{ Exception -> 0x0028 }
    L_0x001b:
        r2 = 0;	 Catch:{ Exception -> 0x0028 }
    L_0x001c:
        r2 = (java.lang.Integer) r2;	 Catch:{ Exception -> 0x0028 }
        if (r2 == 0) goto L_0x0025;	 Catch:{ Exception -> 0x0028 }
    L_0x0020:
        r2 = r2.intValue();	 Catch:{ Exception -> 0x0028 }
        goto L_0x0026;
    L_0x0025:
        r2 = 0;
    L_0x0026:
        r2 = r2 - r1;
        goto L_0x0029;
    L_0x0028:
        r2 = -1;
    L_0x0029:
        return r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.coroutines.jvm.internal.DebugMetadataKt.getLabel(kotlin.coroutines.jvm.internal.BaseContinuationImpl):int");
    }

    private static final void checkDebugMetadataVersion(int i, int i2) {
        if (i2 > i) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Debug metadata version mismatch. Expected: ");
            stringBuilder.append(i);
            stringBuilder.append(", got ");
            stringBuilder.append(i2);
            stringBuilder.append(". Please update the Kotlin standard library.");
            throw ((Throwable) new IllegalStateException(stringBuilder.toString().toString()));
        }
    }

    @SinceKotlin(version = "1.3")
    @Nullable
    @JvmName(name = "getSpilledVariableFieldMapping")
    public static final String[] getSpilledVariableFieldMapping(@NotNull BaseContinuationImpl baseContinuationImpl) {
        Intrinsics.checkParameterIsNotNull(baseContinuationImpl, "receiver$0");
        DebugMetadata debugMetadataAnnotation = getDebugMetadataAnnotation(baseContinuationImpl);
        if (debugMetadataAnnotation == null) {
            return null;
        }
        checkDebugMetadataVersion(1, debugMetadataAnnotation.m13v());
        ArrayList arrayList = new ArrayList();
        baseContinuationImpl = getLabel(baseContinuationImpl);
        int[] i = debugMetadataAnnotation.m8i();
        int length = i.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (i[i2] == baseContinuationImpl) {
                arrayList.add(debugMetadataAnnotation.m12s()[i2]);
                arrayList.add(debugMetadataAnnotation.m11n()[i2]);
            }
        }
        baseContinuationImpl = arrayList.toArray(new String[0]);
        if (baseContinuationImpl != null) {
            return (String[]) baseContinuationImpl;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
    }
}
