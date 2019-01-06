package android.support.v4.provider;

import android.content.ContentUris;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ProviderInfo;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Typeface;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Build.VERSION;
import android.os.CancellationSignal;
import android.os.Handler;
import android.provider.BaseColumns;
import android.support.annotation.GuardedBy;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.annotation.VisibleForTesting;
import android.support.v4.content.res.FontResourcesParserCompat;
import android.support.v4.graphics.TypefaceCompat;
import android.support.v4.graphics.TypefaceCompatUtil;
import android.support.v4.provider.SelfDestructiveThread.ReplyCallback;
import android.support.v4.util.LruCache;
import android.support.v4.util.Preconditions;
import android.support.v4.util.SimpleArrayMap;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FontsContractCompat {
    private static final int BACKGROUND_THREAD_KEEP_ALIVE_DURATION_MS = 10000;
    @RestrictTo({Scope.LIBRARY_GROUP})
    public static final String PARCEL_FONT_RESULTS = "font_results";
    @RestrictTo({Scope.LIBRARY_GROUP})
    static final int RESULT_CODE_PROVIDER_NOT_FOUND = -1;
    @RestrictTo({Scope.LIBRARY_GROUP})
    static final int RESULT_CODE_WRONG_CERTIFICATES = -2;
    private static final String TAG = "FontsContractCompat";
    private static final SelfDestructiveThread sBackgroundThread = new SelfDestructiveThread("fonts", 10, BACKGROUND_THREAD_KEEP_ALIVE_DURATION_MS);
    private static final Comparator<byte[]> sByteArrayComparator = new C01405();
    static final Object sLock = new Object();
    @GuardedBy("sLock")
    static final SimpleArrayMap<String, ArrayList<ReplyCallback<TypefaceResult>>> sPendingReplies = new SimpleArrayMap();
    static final LruCache<String, Typeface> sTypefaceCache = new LruCache(16);

    /* renamed from: android.support.v4.provider.FontsContractCompat$5 */
    static class C01405 implements Comparator<byte[]> {
        C01405() {
        }

        public int compare(byte[] bArr, byte[] bArr2) {
            if (bArr.length != bArr2.length) {
                return bArr.length - bArr2.length;
            }
            for (int i = 0; i < bArr.length; i++) {
                if (bArr[i] != bArr2[i]) {
                    return bArr[i] - bArr2[i];
                }
            }
            return 0;
        }
    }

    public static final class Columns implements BaseColumns {
        public static final String FILE_ID = "file_id";
        public static final String ITALIC = "font_italic";
        public static final String RESULT_CODE = "result_code";
        public static final int RESULT_CODE_FONT_NOT_FOUND = 1;
        public static final int RESULT_CODE_FONT_UNAVAILABLE = 2;
        public static final int RESULT_CODE_MALFORMED_QUERY = 3;
        public static final int RESULT_CODE_OK = 0;
        public static final String TTC_INDEX = "font_ttc_index";
        public static final String VARIATION_SETTINGS = "font_variation_settings";
        public static final String WEIGHT = "font_weight";
    }

    public static class FontFamilyResult {
        public static final int STATUS_OK = 0;
        public static final int STATUS_UNEXPECTED_DATA_PROVIDED = 2;
        public static final int STATUS_WRONG_CERTIFICATES = 1;
        private final FontInfo[] mFonts;
        private final int mStatusCode;

        @RestrictTo({Scope.LIBRARY_GROUP})
        public FontFamilyResult(int i, @Nullable FontInfo[] fontInfoArr) {
            this.mStatusCode = i;
            this.mFonts = fontInfoArr;
        }

        public int getStatusCode() {
            return this.mStatusCode;
        }

        public FontInfo[] getFonts() {
            return this.mFonts;
        }
    }

    public static class FontInfo {
        private final boolean mItalic;
        private final int mResultCode;
        private final int mTtcIndex;
        private final Uri mUri;
        private final int mWeight;

        @RestrictTo({Scope.LIBRARY_GROUP})
        public FontInfo(@NonNull Uri uri, @IntRange(from = 0) int i, @IntRange(from = 1, to = 1000) int i2, boolean z, int i3) {
            this.mUri = (Uri) Preconditions.checkNotNull(uri);
            this.mTtcIndex = i;
            this.mWeight = i2;
            this.mItalic = z;
            this.mResultCode = i3;
        }

        @NonNull
        public Uri getUri() {
            return this.mUri;
        }

        @IntRange(from = 0)
        public int getTtcIndex() {
            return this.mTtcIndex;
        }

        @IntRange(from = 1, to = 1000)
        public int getWeight() {
            return this.mWeight;
        }

        public boolean isItalic() {
            return this.mItalic;
        }

        public int getResultCode() {
            return this.mResultCode;
        }
    }

    public static class FontRequestCallback {
        public static final int FAIL_REASON_FONT_LOAD_ERROR = -3;
        public static final int FAIL_REASON_FONT_NOT_FOUND = 1;
        public static final int FAIL_REASON_FONT_UNAVAILABLE = 2;
        public static final int FAIL_REASON_MALFORMED_QUERY = 3;
        public static final int FAIL_REASON_PROVIDER_NOT_FOUND = -1;
        public static final int FAIL_REASON_SECURITY_VIOLATION = -4;
        public static final int FAIL_REASON_WRONG_CERTIFICATES = -2;
        @RestrictTo({Scope.LIBRARY_GROUP})
        public static final int RESULT_OK = 0;

        @RestrictTo({Scope.LIBRARY_GROUP})
        @Retention(RetentionPolicy.SOURCE)
        public @interface FontRequestFailReason {
        }

        public void onTypefaceRequestFailed(int i) {
        }

        public void onTypefaceRetrieved(Typeface typeface) {
        }
    }

    private static final class TypefaceResult {
        final int mResult;
        final Typeface mTypeface;

        TypefaceResult(@Nullable Typeface typeface, int i) {
            this.mTypeface = typeface;
            this.mResult = i;
        }
    }

    private FontsContractCompat() {
    }

    @android.support.annotation.NonNull
    static android.support.v4.provider.FontsContractCompat.TypefaceResult getFontInternal(android.content.Context r3, android.support.v4.provider.FontRequest r4, int r5) {
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
        r0 = 0;
        r4 = fetchFonts(r3, r0, r4);	 Catch:{ NameNotFoundException -> 0x002b }
        r1 = r4.getStatusCode();
        r2 = -3;
        if (r1 != 0) goto L_0x001d;
    L_0x000c:
        r4 = r4.getFonts();
        r3 = android.support.v4.graphics.TypefaceCompat.createFromFontInfo(r3, r0, r4, r5);
        r4 = new android.support.v4.provider.FontsContractCompat$TypefaceResult;
        if (r3 == 0) goto L_0x0019;
    L_0x0018:
        r2 = 0;
    L_0x0019:
        r4.<init>(r3, r2);
        return r4;
    L_0x001d:
        r3 = r4.getStatusCode();
        r4 = 1;
        if (r3 != r4) goto L_0x0025;
    L_0x0024:
        r2 = -2;
    L_0x0025:
        r3 = new android.support.v4.provider.FontsContractCompat$TypefaceResult;
        r3.<init>(r0, r2);
        return r3;
    L_0x002b:
        r3 = new android.support.v4.provider.FontsContractCompat$TypefaceResult;
        r4 = -1;
        r3.<init>(r0, r4);
        return r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.provider.FontsContractCompat.getFontInternal(android.content.Context, android.support.v4.provider.FontRequest, int):android.support.v4.provider.FontsContractCompat$TypefaceResult");
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public static void resetCache() {
        sTypefaceCache.evictAll();
    }

    @android.support.annotation.RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
    public static android.graphics.Typeface getFontSync(final android.content.Context r2, final android.support.v4.provider.FontRequest r3, @android.support.annotation.Nullable final android.support.v4.content.res.ResourcesCompat.FontCallback r4, @android.support.annotation.Nullable final android.os.Handler r5, boolean r6, int r7, final int r8) {
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
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = r3.getIdentifier();
        r0.append(r1);
        r1 = "-";
        r0.append(r1);
        r0.append(r8);
        r0 = r0.toString();
        r1 = sTypefaceCache;
        r1 = r1.get(r0);
        r1 = (android.graphics.Typeface) r1;
        if (r1 == 0) goto L_0x0028;
    L_0x0022:
        if (r4 == 0) goto L_0x0027;
    L_0x0024:
        r4.onFontRetrieved(r1);
    L_0x0027:
        return r1;
    L_0x0028:
        if (r6 == 0) goto L_0x0045;
    L_0x002a:
        r1 = -1;
        if (r7 != r1) goto L_0x0045;
    L_0x002d:
        r2 = getFontInternal(r2, r3, r8);
        if (r4 == 0) goto L_0x0042;
    L_0x0033:
        r3 = r2.mResult;
        if (r3 != 0) goto L_0x003d;
    L_0x0037:
        r3 = r2.mTypeface;
        r4.callbackSuccessAsync(r3, r5);
        goto L_0x0042;
    L_0x003d:
        r3 = r2.mResult;
        r4.callbackFailAsync(r3, r5);
    L_0x0042:
        r2 = r2.mTypeface;
        return r2;
    L_0x0045:
        r1 = new android.support.v4.provider.FontsContractCompat$1;
        r1.<init>(r2, r3, r8, r0);
        r2 = 0;
        if (r6 == 0) goto L_0x0059;
    L_0x004d:
        r3 = sBackgroundThread;	 Catch:{ InterruptedException -> 0x0058 }
        r3 = r3.postAndWait(r1, r7);	 Catch:{ InterruptedException -> 0x0058 }
        r3 = (android.support.v4.provider.FontsContractCompat.TypefaceResult) r3;	 Catch:{ InterruptedException -> 0x0058 }
        r3 = r3.mTypeface;	 Catch:{ InterruptedException -> 0x0058 }
        return r3;
    L_0x0058:
        return r2;
    L_0x0059:
        if (r4 != 0) goto L_0x005d;
    L_0x005b:
        r3 = r2;
        goto L_0x0062;
    L_0x005d:
        r3 = new android.support.v4.provider.FontsContractCompat$2;
        r3.<init>(r4, r5);
    L_0x0062:
        r4 = sLock;
        monitor-enter(r4);
        r5 = sPendingReplies;	 Catch:{ all -> 0x0097 }
        r5 = r5.containsKey(r0);	 Catch:{ all -> 0x0097 }
        if (r5 == 0) goto L_0x007c;	 Catch:{ all -> 0x0097 }
    L_0x006d:
        if (r3 == 0) goto L_0x007a;	 Catch:{ all -> 0x0097 }
    L_0x006f:
        r5 = sPendingReplies;	 Catch:{ all -> 0x0097 }
        r5 = r5.get(r0);	 Catch:{ all -> 0x0097 }
        r5 = (java.util.ArrayList) r5;	 Catch:{ all -> 0x0097 }
        r5.add(r3);	 Catch:{ all -> 0x0097 }
    L_0x007a:
        monitor-exit(r4);	 Catch:{ all -> 0x0097 }
        return r2;	 Catch:{ all -> 0x0097 }
    L_0x007c:
        if (r3 == 0) goto L_0x008b;	 Catch:{ all -> 0x0097 }
    L_0x007e:
        r5 = new java.util.ArrayList;	 Catch:{ all -> 0x0097 }
        r5.<init>();	 Catch:{ all -> 0x0097 }
        r5.add(r3);	 Catch:{ all -> 0x0097 }
        r3 = sPendingReplies;	 Catch:{ all -> 0x0097 }
        r3.put(r0, r5);	 Catch:{ all -> 0x0097 }
    L_0x008b:
        monitor-exit(r4);	 Catch:{ all -> 0x0097 }
        r3 = sBackgroundThread;
        r4 = new android.support.v4.provider.FontsContractCompat$3;
        r4.<init>(r0);
        r3.postAndReply(r1, r4);
        return r2;
    L_0x0097:
        r2 = move-exception;
        monitor-exit(r4);	 Catch:{ all -> 0x0097 }
        throw r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.provider.FontsContractCompat.getFontSync(android.content.Context, android.support.v4.provider.FontRequest, android.support.v4.content.res.ResourcesCompat$FontCallback, android.os.Handler, boolean, int, int):android.graphics.Typeface");
    }

    public static void requestFont(@NonNull final Context context, @NonNull final FontRequest fontRequest, @NonNull final FontRequestCallback fontRequestCallback, @NonNull Handler handler) {
        final Handler handler2 = new Handler();
        handler.post(new Runnable() {

            /* renamed from: android.support.v4.provider.FontsContractCompat$4$1 */
            class C01301 implements Runnable {
                C01301() {
                }

                public void run() {
                    fontRequestCallback.onTypefaceRequestFailed(-1);
                }
            }

            /* renamed from: android.support.v4.provider.FontsContractCompat$4$2 */
            class C01312 implements Runnable {
                C01312() {
                }

                public void run() {
                    fontRequestCallback.onTypefaceRequestFailed(-2);
                }
            }

            /* renamed from: android.support.v4.provider.FontsContractCompat$4$3 */
            class C01323 implements Runnable {
                C01323() {
                }

                public void run() {
                    fontRequestCallback.onTypefaceRequestFailed(-3);
                }
            }

            /* renamed from: android.support.v4.provider.FontsContractCompat$4$4 */
            class C01334 implements Runnable {
                C01334() {
                }

                public void run() {
                    fontRequestCallback.onTypefaceRequestFailed(-3);
                }
            }

            /* renamed from: android.support.v4.provider.FontsContractCompat$4$5 */
            class C01345 implements Runnable {
                C01345() {
                }

                public void run() {
                    fontRequestCallback.onTypefaceRequestFailed(1);
                }
            }

            /* renamed from: android.support.v4.provider.FontsContractCompat$4$6 */
            class C01356 implements Runnable {
                C01356() {
                }

                public void run() {
                    fontRequestCallback.onTypefaceRequestFailed(-3);
                }
            }

            /* renamed from: android.support.v4.provider.FontsContractCompat$4$8 */
            class C01378 implements Runnable {
                C01378() {
                }

                public void run() {
                    fontRequestCallback.onTypefaceRequestFailed(-3);
                }
            }

            public void run() {
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
                r6 = this;
                r0 = r2;	 Catch:{ NameNotFoundException -> 0x0095 }
                r1 = r3;	 Catch:{ NameNotFoundException -> 0x0095 }
                r2 = 0;	 Catch:{ NameNotFoundException -> 0x0095 }
                r0 = android.support.v4.provider.FontsContractCompat.fetchFonts(r0, r2, r1);	 Catch:{ NameNotFoundException -> 0x0095 }
                r1 = r0.getStatusCode();
                if (r1 == 0) goto L_0x0037;
            L_0x000f:
                r0 = r0.getStatusCode();
                switch(r0) {
                    case 1: goto L_0x002c;
                    case 2: goto L_0x0021;
                    default: goto L_0x0016;
                };
            L_0x0016:
                r0 = r0;
                r1 = new android.support.v4.provider.FontsContractCompat$4$4;
                r1.<init>();
                r0.post(r1);
                return;
            L_0x0021:
                r0 = r0;
                r1 = new android.support.v4.provider.FontsContractCompat$4$3;
                r1.<init>();
                r0.post(r1);
                return;
            L_0x002c:
                r0 = r0;
                r1 = new android.support.v4.provider.FontsContractCompat$4$2;
                r1.<init>();
                r0.post(r1);
                return;
            L_0x0037:
                r0 = r0.getFonts();
                if (r0 == 0) goto L_0x008a;
            L_0x003d:
                r1 = r0.length;
                if (r1 != 0) goto L_0x0041;
            L_0x0040:
                goto L_0x008a;
            L_0x0041:
                r1 = r0.length;
                r3 = 0;
            L_0x0043:
                if (r3 >= r1) goto L_0x006c;
            L_0x0045:
                r4 = r0[r3];
                r5 = r4.getResultCode();
                if (r5 == 0) goto L_0x0069;
            L_0x004d:
                r0 = r4.getResultCode();
                if (r0 >= 0) goto L_0x005e;
            L_0x0053:
                r0 = r0;
                r1 = new android.support.v4.provider.FontsContractCompat$4$6;
                r1.<init>();
                r0.post(r1);
                goto L_0x0068;
            L_0x005e:
                r1 = r0;
                r2 = new android.support.v4.provider.FontsContractCompat$4$7;
                r2.<init>(r0);
                r1.post(r2);
            L_0x0068:
                return;
            L_0x0069:
                r3 = r3 + 1;
                goto L_0x0043;
            L_0x006c:
                r1 = r2;
                r0 = android.support.v4.provider.FontsContractCompat.buildTypeface(r1, r2, r0);
                if (r0 != 0) goto L_0x007f;
            L_0x0074:
                r0 = r0;
                r1 = new android.support.v4.provider.FontsContractCompat$4$8;
                r1.<init>();
                r0.post(r1);
                return;
            L_0x007f:
                r1 = r0;
                r2 = new android.support.v4.provider.FontsContractCompat$4$9;
                r2.<init>(r0);
                r1.post(r2);
                return;
            L_0x008a:
                r0 = r0;
                r1 = new android.support.v4.provider.FontsContractCompat$4$5;
                r1.<init>();
                r0.post(r1);
                return;
            L_0x0095:
                r0 = r0;
                r1 = new android.support.v4.provider.FontsContractCompat$4$1;
                r1.<init>();
                r0.post(r1);
                return;
                */
                throw new UnsupportedOperationException("Method not decompiled: android.support.v4.provider.FontsContractCompat.4.run():void");
            }
        });
    }

    @Nullable
    public static Typeface buildTypeface(@NonNull Context context, @Nullable CancellationSignal cancellationSignal, @NonNull FontInfo[] fontInfoArr) {
        return TypefaceCompat.createFromFontInfo(context, cancellationSignal, fontInfoArr, 0);
    }

    @RequiresApi(19)
    @RestrictTo({Scope.LIBRARY_GROUP})
    public static Map<Uri, ByteBuffer> prepareFontData(Context context, FontInfo[] fontInfoArr, CancellationSignal cancellationSignal) {
        Map hashMap = new HashMap();
        for (FontInfo fontInfo : fontInfoArr) {
            if (fontInfo.getResultCode() == 0) {
                Uri uri = fontInfo.getUri();
                if (!hashMap.containsKey(uri)) {
                    hashMap.put(uri, TypefaceCompatUtil.mmap(context, cancellationSignal, uri));
                }
            }
        }
        return Collections.unmodifiableMap(hashMap);
    }

    @NonNull
    public static FontFamilyResult fetchFonts(@NonNull Context context, @Nullable CancellationSignal cancellationSignal, @NonNull FontRequest fontRequest) throws NameNotFoundException {
        ProviderInfo provider = getProvider(context.getPackageManager(), fontRequest, context.getResources());
        if (provider == null) {
            return new FontFamilyResult(1, null);
        }
        return new FontFamilyResult(null, getFontFromProvider(context, fontRequest, provider.authority, cancellationSignal));
    }

    @VisibleForTesting
    @RestrictTo({Scope.LIBRARY_GROUP})
    @Nullable
    public static ProviderInfo getProvider(@NonNull PackageManager packageManager, @NonNull FontRequest fontRequest, @Nullable Resources resources) throws NameNotFoundException {
        String providerAuthority = fontRequest.getProviderAuthority();
        int i = 0;
        ProviderInfo resolveContentProvider = packageManager.resolveContentProvider(providerAuthority, 0);
        if (resolveContentProvider == null) {
            fontRequest = new StringBuilder();
            fontRequest.append("No package found for authority: ");
            fontRequest.append(providerAuthority);
            throw new NameNotFoundException(fontRequest.toString());
        } else if (resolveContentProvider.packageName.equals(fontRequest.getProviderPackage())) {
            packageManager = convertToByteArrayList(packageManager.getPackageInfo(resolveContentProvider.packageName, 64).signatures);
            Collections.sort(packageManager, sByteArrayComparator);
            fontRequest = getCertificates(fontRequest, resources);
            while (i < fontRequest.size()) {
                resources = new ArrayList((Collection) fontRequest.get(i));
                Collections.sort(resources, sByteArrayComparator);
                if (equalsByteArrayList(packageManager, resources) != null) {
                    return resolveContentProvider;
                }
                i++;
            }
            return null;
        } else {
            resources = new StringBuilder();
            resources.append("Found content provider ");
            resources.append(providerAuthority);
            resources.append(", but package was not ");
            resources.append(fontRequest.getProviderPackage());
            throw new NameNotFoundException(resources.toString());
        }
    }

    private static List<List<byte[]>> getCertificates(FontRequest fontRequest, Resources resources) {
        if (fontRequest.getCertificates() != null) {
            return fontRequest.getCertificates();
        }
        return FontResourcesParserCompat.readCerts(resources, fontRequest.getCertificatesArrayResId());
    }

    private static boolean equalsByteArrayList(List<byte[]> list, List<byte[]> list2) {
        if (list.size() != list2.size()) {
            return false;
        }
        for (int i = 0; i < list.size(); i++) {
            if (!Arrays.equals((byte[]) list.get(i), (byte[]) list2.get(i))) {
                return false;
            }
        }
        return true;
    }

    private static List<byte[]> convertToByteArrayList(Signature[] signatureArr) {
        List<byte[]> arrayList = new ArrayList();
        for (Signature toByteArray : signatureArr) {
            arrayList.add(toByteArray.toByteArray());
        }
        return arrayList;
    }

    @VisibleForTesting
    @NonNull
    static FontInfo[] getFontFromProvider(Context context, FontRequest fontRequest, String str, CancellationSignal cancellationSignal) {
        String str2 = str;
        ArrayList arrayList = new ArrayList();
        Uri build = new Builder().scheme("content").authority(str2).build();
        Uri build2 = new Builder().scheme("content").authority(str2).appendPath("file").build();
        Cursor cursor = null;
        try {
            Cursor query;
            if (VERSION.SDK_INT > 16) {
                query = context.getContentResolver().query(build, new String[]{"_id", Columns.FILE_ID, Columns.TTC_INDEX, Columns.VARIATION_SETTINGS, Columns.WEIGHT, Columns.ITALIC, Columns.RESULT_CODE}, "query = ?", new String[]{fontRequest.getQuery()}, null, cancellationSignal);
            } else {
                query = context.getContentResolver().query(build, new String[]{"_id", Columns.FILE_ID, Columns.TTC_INDEX, Columns.VARIATION_SETTINGS, Columns.WEIGHT, Columns.ITALIC, Columns.RESULT_CODE}, "query = ?", new String[]{fontRequest.getQuery()}, null);
            }
            cursor = query;
            if (cursor != null && cursor.getCount() > 0) {
                int columnIndex = cursor.getColumnIndex(Columns.RESULT_CODE);
                ArrayList arrayList2 = new ArrayList();
                int columnIndex2 = cursor.getColumnIndex("_id");
                int columnIndex3 = cursor.getColumnIndex(Columns.FILE_ID);
                int columnIndex4 = cursor.getColumnIndex(Columns.TTC_INDEX);
                int columnIndex5 = cursor.getColumnIndex(Columns.WEIGHT);
                int columnIndex6 = cursor.getColumnIndex(Columns.ITALIC);
                while (cursor.moveToNext()) {
                    Uri withAppendedId;
                    int i = columnIndex != -1 ? cursor.getInt(columnIndex) : 0;
                    int i2 = columnIndex4 != -1 ? cursor.getInt(columnIndex4) : 0;
                    if (columnIndex3 == -1) {
                        withAppendedId = ContentUris.withAppendedId(build, cursor.getLong(columnIndex2));
                    } else {
                        withAppendedId = ContentUris.withAppendedId(build2, cursor.getLong(columnIndex3));
                    }
                    Uri uri = withAppendedId;
                    int i3 = columnIndex5 != -1 ? cursor.getInt(columnIndex5) : 400;
                    boolean z = columnIndex6 != -1 && cursor.getInt(columnIndex6) == 1;
                    arrayList2.add(new FontInfo(uri, i2, i3, z, i));
                }
                arrayList = arrayList2;
            }
            if (cursor != null) {
                cursor.close();
            }
            return (FontInfo[]) arrayList.toArray(new FontInfo[0]);
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
        }
    }
}
