package kotlinx.coroutines.internal;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.LongCompanionObject;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\u001a\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0000\u001a\u0018\u0010\u0004\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0007H\u0000\u001a,\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00012\b\b\u0002\u0010\t\u001a\u00020\u00012\b\b\u0002\u0010\n\u001a\u00020\u0001H\u0000\u001a,\u0010\u0004\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u000b2\b\b\u0002\u0010\t\u001a\u00020\u000b2\b\b\u0002\u0010\n\u001a\u00020\u000bH\u0000\"\u0014\u0010\u0000\u001a\u00020\u0001X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003¨\u0006\f"}, d2 = {"AVAILABLE_PROCESSORS", "", "getAVAILABLE_PROCESSORS", "()I", "systemProp", "", "propertyName", "", "defaultValue", "minValue", "maxValue", "", "kotlinx-coroutines-core"}, k = 2, mv = {1, 1, 13})
/* compiled from: SystemProps.kt */
public final class SystemPropsKt {
    private static final int AVAILABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();

    public static final int getAVAILABLE_PROCESSORS() {
        return AVAILABLE_PROCESSORS;
    }

    @org.jetbrains.annotations.Nullable
    public static final java.lang.String systemProp(@org.jetbrains.annotations.NotNull java.lang.String r1) {
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
        r0 = "propertyName";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r1, r0);
        r1 = java.lang.System.getProperty(r1);	 Catch:{ SecurityException -> 0x000a }
        goto L_0x000b;
    L_0x000a:
        r1 = 0;
    L_0x000b:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.internal.SystemPropsKt.systemProp(java.lang.String):java.lang.String");
    }

    public static final boolean systemProp(@org.jetbrains.annotations.NotNull java.lang.String r1, boolean r2) {
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
        r0 = "propertyName";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r1, r0);
        r1 = java.lang.System.getProperty(r1);	 Catch:{ SecurityException -> 0x0010 }
        if (r1 == 0) goto L_0x0010;	 Catch:{ SecurityException -> 0x0010 }
    L_0x000b:
        r1 = java.lang.Boolean.parseBoolean(r1);	 Catch:{ SecurityException -> 0x0010 }
        r2 = r1;
    L_0x0010:
        return r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.internal.SystemPropsKt.systemProp(java.lang.String, boolean):boolean");
    }

    public static /* synthetic */ int systemProp$default(String str, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 4) != null) {
            i2 = 1;
        }
        if ((i4 & 8) != 0) {
            i3 = Integer.MAX_VALUE;
        }
        return systemProp(str, i, i2, i3);
    }

    public static final int systemProp(@NotNull String str, int i, int i2, int i3) {
        Intrinsics.checkParameterIsNotNull(str, "propertyName");
        return (int) systemProp(str, (long) i, (long) i2, (long) i3);
    }

    public static /* synthetic */ long systemProp$default(String str, long j, long j2, long j3, int i, Object obj) {
        if ((i & 4) != null) {
            j2 = 1;
        }
        long j4 = j2;
        if ((i & 8) != null) {
            j3 = LongCompanionObject.MAX_VALUE;
        }
        return systemProp(str, j, j4, j3);
    }

    public static final long systemProp(@NotNull String str, long j, long j2, long j3) {
        Intrinsics.checkParameterIsNotNull(str, "propertyName");
        String systemProp = systemProp(str);
        if (systemProp == null) {
            return j;
        }
        j = StringsKt__StringNumberConversionsKt.toLongOrNull(systemProp);
        if (j != null) {
            long longValue = j.longValue();
            if (j2 <= longValue && j3 >= longValue) {
                return longValue;
            }
            j = new StringBuilder();
            j.append("System property '");
            j.append(str);
            j.append("' should be in range ");
            j.append(j2);
            j.append("..");
            j.append(j3);
            j.append(", but is '");
            j.append(longValue);
            j.append('\'');
            throw ((Throwable) new IllegalStateException(j.toString().toString()));
        }
        j = new StringBuilder();
        j.append("System property '");
        j.append(str);
        j.append("' has unrecognized value '");
        j.append(systemProp);
        j.append('\'');
        throw ((Throwable) new IllegalStateException(j.toString().toString()));
    }
}
