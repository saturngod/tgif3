package android.support.v4.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Build.VERSION;
import android.os.CancellationSignal;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.provider.FontsContractCompat.FontInfo;
import android.support.v4.util.LruCache;

@RestrictTo({Scope.LIBRARY_GROUP})
public class TypefaceCompat {
    private static final String TAG = "TypefaceCompat";
    private static final LruCache<String, Typeface> sTypefaceCache = new LruCache(16);
    private static final TypefaceCompatBaseImpl sTypefaceCompatImpl;

    static {
        if (VERSION.SDK_INT >= 28) {
            sTypefaceCompatImpl = new TypefaceCompatApi28Impl();
        } else if (VERSION.SDK_INT >= 26) {
            sTypefaceCompatImpl = new TypefaceCompatApi26Impl();
        } else if (VERSION.SDK_INT >= 24 && TypefaceCompatApi24Impl.isUsable()) {
            sTypefaceCompatImpl = new TypefaceCompatApi24Impl();
        } else if (VERSION.SDK_INT >= 21) {
            sTypefaceCompatImpl = new TypefaceCompatApi21Impl();
        } else {
            sTypefaceCompatImpl = new TypefaceCompatBaseImpl();
        }
    }

    private TypefaceCompat() {
    }

    @Nullable
    public static Typeface findFromCache(@NonNull Resources resources, int i, int i2) {
        return (Typeface) sTypefaceCache.get(createResourceUid(resources, i, i2));
    }

    private static String createResourceUid(Resources resources, int i, int i2) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(resources.getResourcePackageName(i));
        stringBuilder.append("-");
        stringBuilder.append(i);
        stringBuilder.append("-");
        stringBuilder.append(i2);
        return stringBuilder.toString();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @android.support.annotation.Nullable
    public static android.graphics.Typeface createFromResourcesFamilyXml(@android.support.annotation.NonNull android.content.Context r7, @android.support.annotation.NonNull android.support.v4.content.res.FontResourcesParserCompat.FamilyResourceEntry r8, @android.support.annotation.NonNull android.content.res.Resources r9, int r10, int r11, @android.support.annotation.Nullable android.support.v4.content.res.ResourcesCompat.FontCallback r12, @android.support.annotation.Nullable android.os.Handler r13, boolean r14) {
        /*
        r4 = r8 instanceof android.support.v4.content.res.FontResourcesParserCompat.ProviderResourceEntry;
        if (r4 == 0) goto L_0x002d;
    L_0x0004:
        r0 = r8;
        r0 = (android.support.v4.content.res.FontResourcesParserCompat.ProviderResourceEntry) r0;
        r4 = 0;
        r5 = 1;
        if (r14 == 0) goto L_0x0013;
    L_0x000b:
        r6 = r0.getFetchStrategy();
        if (r6 != 0) goto L_0x0016;
    L_0x0011:
        r4 = 1;
        goto L_0x0016;
    L_0x0013:
        if (r12 != 0) goto L_0x0016;
    L_0x0015:
        goto L_0x0011;
    L_0x0016:
        if (r14 == 0) goto L_0x001e;
    L_0x0018:
        r1 = r0.getTimeout();
        r5 = r1;
        goto L_0x0020;
    L_0x001e:
        r1 = -1;
        r5 = -1;
    L_0x0020:
        r1 = r0.getRequest();
        r0 = r7;
        r2 = r12;
        r3 = r13;
        r6 = r11;
        r0 = android.support.v4.provider.FontsContractCompat.getFontSync(r0, r1, r2, r3, r4, r5, r6);
        goto L_0x0042;
    L_0x002d:
        r1 = sTypefaceCompatImpl;
        r0 = r8;
        r0 = (android.support.v4.content.res.FontResourcesParserCompat.FontFamilyFilesResourceEntry) r0;
        r0 = r1.createFromFontFamilyFilesResourceEntry(r7, r0, r9, r11);
        if (r12 == 0) goto L_0x0042;
    L_0x0038:
        if (r0 == 0) goto L_0x003e;
    L_0x003a:
        r12.callbackSuccessAsync(r0, r13);
        goto L_0x0042;
    L_0x003e:
        r1 = -3;
        r12.callbackFailAsync(r1, r13);
    L_0x0042:
        if (r0 == 0) goto L_0x004d;
    L_0x0044:
        r1 = sTypefaceCache;
        r2 = createResourceUid(r9, r10, r11);
        r1.put(r2, r0);
    L_0x004d:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.graphics.TypefaceCompat.createFromResourcesFamilyXml(android.content.Context, android.support.v4.content.res.FontResourcesParserCompat$FamilyResourceEntry, android.content.res.Resources, int, int, android.support.v4.content.res.ResourcesCompat$FontCallback, android.os.Handler, boolean):android.graphics.Typeface");
    }

    @Nullable
    public static Typeface createFromResourcesFontFile(@NonNull Context context, @NonNull Resources resources, int i, String str, int i2) {
        context = sTypefaceCompatImpl.createFromResourcesFontFile(context, resources, i, str, i2);
        if (context != null) {
            sTypefaceCache.put(createResourceUid(resources, i, i2), context);
        }
        return context;
    }

    @Nullable
    public static Typeface createFromFontInfo(@NonNull Context context, @Nullable CancellationSignal cancellationSignal, @NonNull FontInfo[] fontInfoArr, int i) {
        return sTypefaceCompatImpl.createFromFontInfo(context, cancellationSignal, fontInfoArr, i);
    }
}
