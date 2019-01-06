package android.support.v4.graphics;

import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;

@RequiresApi(21)
@RestrictTo({Scope.LIBRARY_GROUP})
class TypefaceCompatApi21Impl extends TypefaceCompatBaseImpl {
    private static final String TAG = "TypefaceCompatApi21Impl";

    TypefaceCompatApi21Impl() {
    }

    private java.io.File getFile(android.os.ParcelFileDescriptor r4) {
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
        r3 = this;
        r0 = 0;
        r1 = new java.lang.StringBuilder;	 Catch:{ ErrnoException -> 0x002d }
        r1.<init>();	 Catch:{ ErrnoException -> 0x002d }
        r2 = "/proc/self/fd/";	 Catch:{ ErrnoException -> 0x002d }
        r1.append(r2);	 Catch:{ ErrnoException -> 0x002d }
        r4 = r4.getFd();	 Catch:{ ErrnoException -> 0x002d }
        r1.append(r4);	 Catch:{ ErrnoException -> 0x002d }
        r4 = r1.toString();	 Catch:{ ErrnoException -> 0x002d }
        r4 = android.system.Os.readlink(r4);	 Catch:{ ErrnoException -> 0x002d }
        r1 = android.system.Os.stat(r4);	 Catch:{ ErrnoException -> 0x002d }
        r1 = r1.st_mode;	 Catch:{ ErrnoException -> 0x002d }
        r1 = android.system.OsConstants.S_ISREG(r1);	 Catch:{ ErrnoException -> 0x002d }
        if (r1 == 0) goto L_0x002c;	 Catch:{ ErrnoException -> 0x002d }
    L_0x0026:
        r1 = new java.io.File;	 Catch:{ ErrnoException -> 0x002d }
        r1.<init>(r4);	 Catch:{ ErrnoException -> 0x002d }
        return r1;
    L_0x002c:
        return r0;
    L_0x002d:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.graphics.TypefaceCompatApi21Impl.getFile(android.os.ParcelFileDescriptor):java.io.File");
    }

    public android.graphics.Typeface createFromFontInfo(android.content.Context r5, android.os.CancellationSignal r6, @android.support.annotation.NonNull android.support.v4.provider.FontsContractCompat.FontInfo[] r7, int r8) {
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
        r4 = this;
        r0 = r7.length;
        r1 = 0;
        r2 = 1;
        if (r0 >= r2) goto L_0x0006;
    L_0x0005:
        return r1;
    L_0x0006:
        r7 = r4.findBestInfo(r7, r8);
        r8 = r5.getContentResolver();
        r7 = r7.getUri();	 Catch:{ IOException -> 0x0077 }
        r0 = "r";	 Catch:{ IOException -> 0x0077 }
        r6 = r8.openFileDescriptor(r7, r0, r6);	 Catch:{ IOException -> 0x0077 }
        r7 = r4.getFile(r6);	 Catch:{ Throwable -> 0x0060, all -> 0x005d }
        if (r7 == 0) goto L_0x002f;	 Catch:{ Throwable -> 0x0060, all -> 0x005d }
    L_0x001e:
        r8 = r7.canRead();	 Catch:{ Throwable -> 0x0060, all -> 0x005d }
        if (r8 != 0) goto L_0x0025;	 Catch:{ Throwable -> 0x0060, all -> 0x005d }
    L_0x0024:
        goto L_0x002f;	 Catch:{ Throwable -> 0x0060, all -> 0x005d }
    L_0x0025:
        r5 = android.graphics.Typeface.createFromFile(r7);	 Catch:{ Throwable -> 0x0060, all -> 0x005d }
        if (r6 == 0) goto L_0x002e;
    L_0x002b:
        r6.close();	 Catch:{ IOException -> 0x0077 }
    L_0x002e:
        return r5;
    L_0x002f:
        r7 = new java.io.FileInputStream;	 Catch:{ Throwable -> 0x0060, all -> 0x005d }
        r8 = r6.getFileDescriptor();	 Catch:{ Throwable -> 0x0060, all -> 0x005d }
        r7.<init>(r8);	 Catch:{ Throwable -> 0x0060, all -> 0x005d }
        r5 = super.createFromInputStream(r5, r7);	 Catch:{ Throwable -> 0x0048, all -> 0x0045 }
        r7.close();	 Catch:{ Throwable -> 0x0060, all -> 0x005d }
        if (r6 == 0) goto L_0x0044;
    L_0x0041:
        r6.close();	 Catch:{ IOException -> 0x0077 }
    L_0x0044:
        return r5;
    L_0x0045:
        r5 = move-exception;
        r8 = r1;
        goto L_0x004e;
    L_0x0048:
        r5 = move-exception;
        throw r5;	 Catch:{ all -> 0x004a }
    L_0x004a:
        r8 = move-exception;
        r3 = r8;
        r8 = r5;
        r5 = r3;
    L_0x004e:
        if (r8 == 0) goto L_0x0059;
    L_0x0050:
        r7.close();	 Catch:{ Throwable -> 0x0054, all -> 0x005d }
        goto L_0x005c;
    L_0x0054:
        r7 = move-exception;
        r8.addSuppressed(r7);	 Catch:{ Throwable -> 0x0060, all -> 0x005d }
        goto L_0x005c;	 Catch:{ Throwable -> 0x0060, all -> 0x005d }
    L_0x0059:
        r7.close();	 Catch:{ Throwable -> 0x0060, all -> 0x005d }
    L_0x005c:
        throw r5;	 Catch:{ Throwable -> 0x0060, all -> 0x005d }
    L_0x005d:
        r5 = move-exception;
        r7 = r1;
        goto L_0x0066;
    L_0x0060:
        r5 = move-exception;
        throw r5;	 Catch:{ all -> 0x0062 }
    L_0x0062:
        r7 = move-exception;
        r3 = r7;
        r7 = r5;
        r5 = r3;
    L_0x0066:
        if (r6 == 0) goto L_0x0076;
    L_0x0068:
        if (r7 == 0) goto L_0x0073;
    L_0x006a:
        r6.close();	 Catch:{ Throwable -> 0x006e }
        goto L_0x0076;
    L_0x006e:
        r6 = move-exception;
        r7.addSuppressed(r6);	 Catch:{ IOException -> 0x0077 }
        goto L_0x0076;	 Catch:{ IOException -> 0x0077 }
    L_0x0073:
        r6.close();	 Catch:{ IOException -> 0x0077 }
    L_0x0076:
        throw r5;	 Catch:{ IOException -> 0x0077 }
    L_0x0077:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.graphics.TypefaceCompatApi21Impl.createFromFontInfo(android.content.Context, android.os.CancellationSignal, android.support.v4.provider.FontsContractCompat$FontInfo[], int):android.graphics.Typeface");
    }
}
