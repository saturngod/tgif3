package android.support.v4.graphics.drawable;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.Intent.ShortcutIconResource;
import android.content.pm.ApplicationInfo;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.annotation.VisibleForTesting;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.util.Preconditions;
import android.text.TextUtils;
import android.util.Log;
import androidx.versionedparcelable.CustomVersionedParcelable;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.nio.charset.Charset;

public class IconCompat extends CustomVersionedParcelable {
    private static final float ADAPTIVE_ICON_INSET_FACTOR = 0.25f;
    private static final int AMBIENT_SHADOW_ALPHA = 30;
    private static final float BLUR_FACTOR = 0.010416667f;
    static final Mode DEFAULT_TINT_MODE = Mode.SRC_IN;
    private static final float DEFAULT_VIEW_PORT_SCALE = 0.6666667f;
    private static final String EXTRA_INT1 = "int1";
    private static final String EXTRA_INT2 = "int2";
    private static final String EXTRA_OBJ = "obj";
    private static final String EXTRA_TINT_LIST = "tint_list";
    private static final String EXTRA_TINT_MODE = "tint_mode";
    private static final String EXTRA_TYPE = "type";
    private static final float ICON_DIAMETER_FACTOR = 0.9166667f;
    private static final int KEY_SHADOW_ALPHA = 61;
    private static final float KEY_SHADOW_OFFSET_FACTOR = 0.020833334f;
    private static final String TAG = "IconCompat";
    public static final int TYPE_UNKNOWN = -1;
    @RestrictTo({Scope.LIBRARY})
    public byte[] mData;
    @RestrictTo({Scope.LIBRARY})
    public int mInt1;
    @RestrictTo({Scope.LIBRARY})
    public int mInt2;
    Object mObj1;
    @RestrictTo({Scope.LIBRARY})
    public Parcelable mParcelable;
    @RestrictTo({Scope.LIBRARY})
    public ColorStateList mTintList = null;
    Mode mTintMode = DEFAULT_TINT_MODE;
    @RestrictTo({Scope.LIBRARY})
    public String mTintModeStr;
    @RestrictTo({Scope.LIBRARY})
    public int mType;

    @RestrictTo({Scope.LIBRARY})
    @Retention(RetentionPolicy.SOURCE)
    public @interface IconType {
    }

    private static String typeToString(int i) {
        switch (i) {
            case 1:
                return "BITMAP";
            case 2:
                return "RESOURCE";
            case 3:
                return "DATA";
            case 4:
                return "URI";
            case 5:
                return "BITMAP_MASKABLE";
            default:
                return "UNKNOWN";
        }
    }

    public static IconCompat createWithResource(Context context, @DrawableRes int i) {
        if (context != null) {
            return createWithResource(context.getResources(), context.getPackageName(), i);
        }
        throw new IllegalArgumentException("Context must not be null.");
    }

    @android.support.annotation.RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY})
    public static android.support.v4.graphics.drawable.IconCompat createWithResource(android.content.res.Resources r2, java.lang.String r3, @android.support.annotation.DrawableRes int r4) {
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
        if (r3 == 0) goto L_0x0028;
    L_0x0002:
        if (r4 == 0) goto L_0x0020;
    L_0x0004:
        r0 = new android.support.v4.graphics.drawable.IconCompat;
        r1 = 2;
        r0.<init>(r1);
        r0.mInt1 = r4;
        if (r2 == 0) goto L_0x001d;
    L_0x000e:
        r2 = r2.getResourceName(r4);	 Catch:{ NotFoundException -> 0x0015 }
        r0.mObj1 = r2;	 Catch:{ NotFoundException -> 0x0015 }
        goto L_0x001f;
    L_0x0015:
        r2 = new java.lang.IllegalArgumentException;
        r3 = "Icon resource cannot be found";
        r2.<init>(r3);
        throw r2;
    L_0x001d:
        r0.mObj1 = r3;
    L_0x001f:
        return r0;
    L_0x0020:
        r2 = new java.lang.IllegalArgumentException;
        r3 = "Drawable resource ID must not be 0";
        r2.<init>(r3);
        throw r2;
    L_0x0028:
        r2 = new java.lang.IllegalArgumentException;
        r3 = "Package must not be null.";
        r2.<init>(r3);
        throw r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.graphics.drawable.IconCompat.createWithResource(android.content.res.Resources, java.lang.String, int):android.support.v4.graphics.drawable.IconCompat");
    }

    public static IconCompat createWithBitmap(Bitmap bitmap) {
        if (bitmap != null) {
            IconCompat iconCompat = new IconCompat(1);
            iconCompat.mObj1 = bitmap;
            return iconCompat;
        }
        throw new IllegalArgumentException("Bitmap must not be null.");
    }

    public static IconCompat createWithAdaptiveBitmap(Bitmap bitmap) {
        if (bitmap != null) {
            IconCompat iconCompat = new IconCompat(5);
            iconCompat.mObj1 = bitmap;
            return iconCompat;
        }
        throw new IllegalArgumentException("Bitmap must not be null.");
    }

    public static IconCompat createWithData(byte[] bArr, int i, int i2) {
        if (bArr != null) {
            IconCompat iconCompat = new IconCompat(3);
            iconCompat.mObj1 = bArr;
            iconCompat.mInt1 = i;
            iconCompat.mInt2 = i2;
            return iconCompat;
        }
        throw new IllegalArgumentException("Data must not be null.");
    }

    public static IconCompat createWithContentUri(String str) {
        if (str != null) {
            IconCompat iconCompat = new IconCompat(4);
            iconCompat.mObj1 = str;
            return iconCompat;
        }
        throw new IllegalArgumentException("Uri must not be null.");
    }

    public static IconCompat createWithContentUri(Uri uri) {
        if (uri != null) {
            return createWithContentUri(uri.toString());
        }
        throw new IllegalArgumentException("Uri must not be null.");
    }

    private IconCompat(int i) {
        this.mType = i;
    }

    public int getType() {
        if (this.mType != -1 || VERSION.SDK_INT < 23) {
            return this.mType;
        }
        return getType((Icon) this.mObj1);
    }

    @NonNull
    public String getResPackage() {
        if (this.mType == -1 && VERSION.SDK_INT >= 23) {
            return getResPackage((Icon) this.mObj1);
        }
        if (this.mType == 2) {
            return ((String) this.mObj1).split(":", -1)[0];
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("called getResPackage() on ");
        stringBuilder.append(this);
        throw new IllegalStateException(stringBuilder.toString());
    }

    @IdRes
    public int getResId() {
        if (this.mType == -1 && VERSION.SDK_INT >= 23) {
            return getResId((Icon) this.mObj1);
        }
        if (this.mType == 2) {
            return this.mInt1;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("called getResId() on ");
        stringBuilder.append(this);
        throw new IllegalStateException(stringBuilder.toString());
    }

    @NonNull
    public Uri getUri() {
        if (this.mType != -1 || VERSION.SDK_INT < 23) {
            return Uri.parse((String) this.mObj1);
        }
        return getUri((Icon) this.mObj1);
    }

    public IconCompat setTint(@ColorInt int i) {
        return setTintList(ColorStateList.valueOf(i));
    }

    public IconCompat setTintList(ColorStateList colorStateList) {
        this.mTintList = colorStateList;
        return this;
    }

    public IconCompat setTintMode(Mode mode) {
        this.mTintMode = mode;
        return this;
    }

    @RequiresApi(23)
    public Icon toIcon() {
        int i = this.mType;
        if (i == -1) {
            return (Icon) this.mObj1;
        }
        Icon createWithBitmap;
        switch (i) {
            case 1:
                createWithBitmap = Icon.createWithBitmap((Bitmap) this.mObj1);
                break;
            case 2:
                createWithBitmap = Icon.createWithResource(getResPackage(), this.mInt1);
                break;
            case 3:
                createWithBitmap = Icon.createWithData((byte[]) this.mObj1, this.mInt1, this.mInt2);
                break;
            case 4:
                createWithBitmap = Icon.createWithContentUri((String) this.mObj1);
                break;
            case 5:
                if (VERSION.SDK_INT < 26) {
                    createWithBitmap = Icon.createWithBitmap(createLegacyIconFromAdaptiveIcon((Bitmap) this.mObj1, false));
                    break;
                }
                createWithBitmap = Icon.createWithAdaptiveBitmap((Bitmap) this.mObj1);
                break;
            default:
                throw new IllegalArgumentException("Unknown type");
        }
        if (this.mTintList != null) {
            createWithBitmap.setTintList(this.mTintList);
        }
        if (this.mTintMode != DEFAULT_TINT_MODE) {
            createWithBitmap.setTintMode(this.mTintMode);
        }
        return createWithBitmap;
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public void checkResource(Context context) {
        if (this.mType == 2) {
            String str = (String) this.mObj1;
            if (str.contains(":")) {
                String str2 = str.split(":", -1)[1];
                String str3 = str2.split("/", -1)[0];
                str2 = str2.split("/", -1)[1];
                str = str.split(":", -1)[0];
                context = getResources(context, str).getIdentifier(str2, str3, str);
                if (this.mInt1 != context) {
                    String str4 = TAG;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Id has changed for ");
                    stringBuilder.append(str);
                    stringBuilder.append("/");
                    stringBuilder.append(str2);
                    Log.i(str4, stringBuilder.toString());
                    this.mInt1 = context;
                }
            }
        }
    }

    public Drawable loadDrawable(Context context) {
        checkResource(context);
        if (VERSION.SDK_INT >= 23) {
            return toIcon().loadDrawable(context);
        }
        context = loadDrawableInner(context);
        if (!(context == null || (this.mTintList == null && this.mTintMode == DEFAULT_TINT_MODE))) {
            context.mutate();
            DrawableCompat.setTintList(context, this.mTintList);
            DrawableCompat.setTintMode(context, this.mTintMode);
        }
        return context;
    }

    private Drawable loadDrawableInner(Context context) {
        String str;
        StringBuilder stringBuilder;
        switch (this.mType) {
            case 1:
                return new BitmapDrawable(context.getResources(), (Bitmap) this.mObj1);
            case 2:
                String resPackage = getResPackage();
                if (TextUtils.isEmpty(resPackage)) {
                    resPackage = context.getPackageName();
                }
                try {
                    return ResourcesCompat.getDrawable(getResources(context, resPackage), this.mInt1, context.getTheme());
                } catch (Context context2) {
                    Log.e(TAG, String.format("Unable to load resource 0x%08x from pkg=%s", new Object[]{Integer.valueOf(this.mInt1), this.mObj1}), context2);
                    break;
                }
            case 3:
                return new BitmapDrawable(context2.getResources(), BitmapFactory.decodeByteArray((byte[]) this.mObj1, this.mInt1, this.mInt2));
            case 4:
                InputStream fileInputStream;
                Uri parse = Uri.parse((String) this.mObj1);
                String scheme = parse.getScheme();
                if (!"content".equals(scheme)) {
                    if (!"file".equals(scheme)) {
                        try {
                            fileInputStream = new FileInputStream(new File((String) this.mObj1));
                        } catch (Throwable e) {
                            str = TAG;
                            stringBuilder = new StringBuilder();
                            stringBuilder.append("Unable to load image from path: ");
                            stringBuilder.append(parse);
                            Log.w(str, stringBuilder.toString(), e);
                            fileInputStream = null;
                            if (fileInputStream != null) {
                                return new BitmapDrawable(context2.getResources(), BitmapFactory.decodeStream(fileInputStream));
                            }
                            return null;
                        }
                        if (fileInputStream != null) {
                            return new BitmapDrawable(context2.getResources(), BitmapFactory.decodeStream(fileInputStream));
                        }
                    }
                }
                try {
                    fileInputStream = context2.getContentResolver().openInputStream(parse);
                } catch (Throwable e2) {
                    str = TAG;
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("Unable to load image from URI: ");
                    stringBuilder.append(parse);
                    Log.w(str, stringBuilder.toString(), e2);
                    fileInputStream = null;
                    if (fileInputStream != null) {
                        return new BitmapDrawable(context2.getResources(), BitmapFactory.decodeStream(fileInputStream));
                    }
                    return null;
                }
                if (fileInputStream != null) {
                    return new BitmapDrawable(context2.getResources(), BitmapFactory.decodeStream(fileInputStream));
                }
            case 5:
                return new BitmapDrawable(context2.getResources(), createLegacyIconFromAdaptiveIcon((Bitmap) this.mObj1, false));
            default:
                break;
        }
        return null;
    }

    private static Resources getResources(Context context, String str) {
        if ("android".equals(str)) {
            return Resources.getSystem();
        }
        context = context.getPackageManager();
        try {
            ApplicationInfo applicationInfo = context.getApplicationInfo(str, 8192);
            if (applicationInfo != null) {
                return context.getResourcesForApplication(applicationInfo);
            }
            return null;
        } catch (Context context2) {
            Log.e(TAG, String.format("Unable to find pkg=%s for icon", new Object[]{str}), context2);
            return null;
        }
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public void addToShortcutIntent(@NonNull Intent intent, @Nullable Drawable drawable, @NonNull Context context) {
        checkResource(context);
        int i = this.mType;
        if (i != 5) {
            switch (i) {
                case 1:
                    context = (Bitmap) this.mObj1;
                    if (drawable != null) {
                        context = context.copy(context.getConfig(), true);
                        break;
                    }
                    break;
                case 2:
                    try {
                        context = context.createPackageContext(getResPackage(), 0);
                        if (drawable == null) {
                            intent.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", ShortcutIconResource.fromContext(context, this.mInt1));
                            return;
                        }
                        Drawable drawable2 = ContextCompat.getDrawable(context, this.mInt1);
                        if (drawable2.getIntrinsicWidth() > 0) {
                            if (drawable2.getIntrinsicHeight() > 0) {
                                context = Bitmap.createBitmap(drawable2.getIntrinsicWidth(), drawable2.getIntrinsicHeight(), Config.ARGB_8888);
                                drawable2.setBounds(0, 0, context.getWidth(), context.getHeight());
                                drawable2.draw(new Canvas(context));
                                break;
                            }
                        }
                        context = ((ActivityManager) context.getSystemService("activity")).getLauncherLargeIconSize();
                        context = Bitmap.createBitmap(context, context, Config.ARGB_8888);
                        drawable2.setBounds(0, 0, context.getWidth(), context.getHeight());
                        drawable2.draw(new Canvas(context));
                    } catch (Intent intent2) {
                        context = new StringBuilder();
                        context.append("Can't find package ");
                        context.append(this.mObj1);
                        throw new IllegalArgumentException(context.toString(), intent2);
                    }
                default:
                    throw new IllegalArgumentException("Icon type not supported for intent shortcuts");
            }
        }
        context = createLegacyIconFromAdaptiveIcon((Bitmap) this.mObj1, true);
        if (drawable != null) {
            i = context.getWidth();
            int height = context.getHeight();
            drawable.setBounds(i / 2, height / 2, i, height);
            drawable.draw(new Canvas(context));
        }
        intent2.putExtra("android.intent.extra.shortcut.ICON", context);
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        int i = this.mType;
        if (i != -1) {
            switch (i) {
                case 1:
                case 5:
                    bundle.putParcelable(EXTRA_OBJ, (Bitmap) this.mObj1);
                    break;
                case 2:
                case 4:
                    bundle.putString(EXTRA_OBJ, (String) this.mObj1);
                    break;
                case 3:
                    bundle.putByteArray(EXTRA_OBJ, (byte[]) this.mObj1);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid icon");
            }
        }
        bundle.putParcelable(EXTRA_OBJ, (Parcelable) this.mObj1);
        bundle.putInt(EXTRA_TYPE, this.mType);
        bundle.putInt(EXTRA_INT1, this.mInt1);
        bundle.putInt(EXTRA_INT2, this.mInt2);
        if (this.mTintList != null) {
            bundle.putParcelable(EXTRA_TINT_LIST, this.mTintList);
        }
        if (this.mTintMode != DEFAULT_TINT_MODE) {
            bundle.putString(EXTRA_TINT_MODE, this.mTintMode.name());
        }
        return bundle;
    }

    public String toString() {
        if (this.mType == -1) {
            return String.valueOf(this.mObj1);
        }
        StringBuilder stringBuilder = new StringBuilder("Icon(typ=");
        stringBuilder.append(typeToString(this.mType));
        switch (this.mType) {
            case 1:
            case 5:
                stringBuilder.append(" size=");
                stringBuilder.append(((Bitmap) this.mObj1).getWidth());
                stringBuilder.append("x");
                stringBuilder.append(((Bitmap) this.mObj1).getHeight());
                break;
            case 2:
                stringBuilder.append(" pkg=");
                stringBuilder.append(getResPackage());
                stringBuilder.append(" id=");
                stringBuilder.append(String.format("0x%08x", new Object[]{Integer.valueOf(getResId())}));
                break;
            case 3:
                stringBuilder.append(" len=");
                stringBuilder.append(this.mInt1);
                if (this.mInt2 != 0) {
                    stringBuilder.append(" off=");
                    stringBuilder.append(this.mInt2);
                    break;
                }
                break;
            case 4:
                stringBuilder.append(" uri=");
                stringBuilder.append(this.mObj1);
                break;
            default:
                break;
        }
        if (this.mTintList != null) {
            stringBuilder.append(" tint=");
            stringBuilder.append(this.mTintList);
        }
        if (this.mTintMode != DEFAULT_TINT_MODE) {
            stringBuilder.append(" mode=");
            stringBuilder.append(this.mTintMode);
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public void onPreParceling(boolean z) {
        this.mTintModeStr = this.mTintMode.name();
        int i = this.mType;
        if (i != -1) {
            switch (i) {
                case 1:
                case 5:
                    if (z) {
                        Bitmap bitmap = (Bitmap) this.mObj1;
                        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        bitmap.compress(CompressFormat.PNG, 90, byteArrayOutputStream);
                        this.mData = byteArrayOutputStream.toByteArray();
                        return;
                    }
                    this.mParcelable = (Parcelable) this.mObj1;
                    return;
                case 2:
                    this.mData = ((String) this.mObj1).getBytes(Charset.forName("UTF-16"));
                    return;
                case 3:
                    this.mData = (byte[]) this.mObj1;
                    return;
                case 4:
                    this.mData = this.mObj1.toString().getBytes(Charset.forName("UTF-16"));
                    return;
                default:
                    return;
            }
        } else if (z) {
            throw new IllegalArgumentException("Can't serialize Icon created with IconCompat#createFromIcon");
        } else {
            this.mParcelable = (Parcelable) this.mObj1;
        }
    }

    public void onPostParceling() {
        this.mTintMode = Mode.valueOf(this.mTintModeStr);
        int i = this.mType;
        if (i != -1) {
            switch (i) {
                case 1:
                case 5:
                    if (this.mParcelable != null) {
                        this.mObj1 = this.mParcelable;
                        return;
                    }
                    this.mObj1 = this.mData;
                    this.mType = 3;
                    this.mInt1 = 0;
                    this.mInt2 = this.mData.length;
                    return;
                case 2:
                case 4:
                    this.mObj1 = new String(this.mData, Charset.forName("UTF-16"));
                    return;
                case 3:
                    this.mObj1 = this.mData;
                    return;
                default:
                    return;
            }
        } else if (this.mParcelable != null) {
            this.mObj1 = this.mParcelable;
        } else {
            throw new IllegalArgumentException("Invalid icon");
        }
    }

    @Nullable
    public static IconCompat createFromBundle(@NonNull Bundle bundle) {
        int i = bundle.getInt(EXTRA_TYPE);
        IconCompat iconCompat = new IconCompat(i);
        iconCompat.mInt1 = bundle.getInt(EXTRA_INT1);
        iconCompat.mInt2 = bundle.getInt(EXTRA_INT2);
        if (bundle.containsKey(EXTRA_TINT_LIST)) {
            iconCompat.mTintList = (ColorStateList) bundle.getParcelable(EXTRA_TINT_LIST);
        }
        if (bundle.containsKey(EXTRA_TINT_MODE)) {
            iconCompat.mTintMode = Mode.valueOf(bundle.getString(EXTRA_TINT_MODE));
        }
        if (i != -1) {
            switch (i) {
                case 1:
                case 5:
                    break;
                case 2:
                case 4:
                    iconCompat.mObj1 = bundle.getString(EXTRA_OBJ);
                    break;
                case 3:
                    iconCompat.mObj1 = bundle.getByteArray(EXTRA_OBJ);
                    break;
                default:
                    bundle = TAG;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Unknown type ");
                    stringBuilder.append(i);
                    Log.w(bundle, stringBuilder.toString());
                    return null;
            }
        }
        iconCompat.mObj1 = bundle.getParcelable(EXTRA_OBJ);
        return iconCompat;
    }

    @android.support.annotation.Nullable
    @android.support.annotation.RequiresApi(23)
    public static android.support.v4.graphics.drawable.IconCompat createFromIcon(@android.support.annotation.NonNull android.content.Context r2, @android.support.annotation.NonNull android.graphics.drawable.Icon r3) {
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
        android.support.v4.util.Preconditions.checkNotNull(r3);
        r0 = getType(r3);
        r1 = 2;
        if (r0 == r1) goto L_0x001f;
    L_0x000a:
        r2 = 4;
        if (r0 == r2) goto L_0x0016;
    L_0x000d:
        r2 = new android.support.v4.graphics.drawable.IconCompat;
        r0 = -1;
        r2.<init>(r0);
        r2.mObj1 = r3;
        return r2;
    L_0x0016:
        r2 = getUri(r3);
        r2 = createWithContentUri(r2);
        return r2;
    L_0x001f:
        r0 = getResPackage(r3);
        r2 = getResources(r2, r0);	 Catch:{ NotFoundException -> 0x0030 }
        r3 = getResId(r3);	 Catch:{ NotFoundException -> 0x0030 }
        r2 = createWithResource(r2, r0, r3);	 Catch:{ NotFoundException -> 0x0030 }
        return r2;
    L_0x0030:
        r2 = new java.lang.IllegalArgumentException;
        r3 = "Icon resource cannot be found";
        r2.<init>(r3);
        throw r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.graphics.drawable.IconCompat.createFromIcon(android.content.Context, android.graphics.drawable.Icon):android.support.v4.graphics.drawable.IconCompat");
    }

    @Nullable
    @RequiresApi(23)
    @RestrictTo({Scope.LIBRARY_GROUP})
    public static IconCompat createFromIcon(@NonNull Icon icon) {
        Preconditions.checkNotNull(icon);
        int type = getType(icon);
        if (type == 2) {
            return createWithResource(null, getResPackage(icon), getResId(icon));
        }
        if (type == 4) {
            return createWithContentUri(getUri(icon));
        }
        IconCompat iconCompat = new IconCompat(-1);
        iconCompat.mObj1 = icon;
        return iconCompat;
    }

    @RequiresApi(23)
    private static int getType(@NonNull Icon icon) {
        String str;
        StringBuilder stringBuilder;
        if (VERSION.SDK_INT >= 28) {
            return icon.getType();
        }
        try {
            return ((Integer) icon.getClass().getMethod("getType", new Class[0]).invoke(icon, new Object[0])).intValue();
        } catch (Throwable e) {
            str = TAG;
            stringBuilder = new StringBuilder();
            stringBuilder.append("Unable to get icon type ");
            stringBuilder.append(icon);
            Log.e(str, stringBuilder.toString(), e);
            return -1;
        } catch (Throwable e2) {
            str = TAG;
            stringBuilder = new StringBuilder();
            stringBuilder.append("Unable to get icon type ");
            stringBuilder.append(icon);
            Log.e(str, stringBuilder.toString(), e2);
            return -1;
        } catch (Throwable e22) {
            str = TAG;
            stringBuilder = new StringBuilder();
            stringBuilder.append("Unable to get icon type ");
            stringBuilder.append(icon);
            Log.e(str, stringBuilder.toString(), e22);
            return -1;
        }
    }

    @Nullable
    @RequiresApi(23)
    private static String getResPackage(@NonNull Icon icon) {
        if (VERSION.SDK_INT >= 28) {
            return icon.getResPackage();
        }
        try {
            return (String) icon.getClass().getMethod("getResPackage", new Class[0]).invoke(icon, new Object[0]);
        } catch (Icon icon2) {
            Log.e(TAG, "Unable to get icon package", icon2);
            return null;
        } catch (Icon icon22) {
            Log.e(TAG, "Unable to get icon package", icon22);
            return null;
        } catch (Icon icon222) {
            Log.e(TAG, "Unable to get icon package", icon222);
            return null;
        }
    }

    @RequiresApi(23)
    @DrawableRes
    @IdRes
    private static int getResId(@NonNull Icon icon) {
        if (VERSION.SDK_INT >= 28) {
            return icon.getResId();
        }
        try {
            return ((Integer) icon.getClass().getMethod("getResId", new Class[0]).invoke(icon, new Object[0])).intValue();
        } catch (Icon icon2) {
            Log.e(TAG, "Unable to get icon resource", icon2);
            return 0;
        } catch (Icon icon22) {
            Log.e(TAG, "Unable to get icon resource", icon22);
            return 0;
        } catch (Icon icon222) {
            Log.e(TAG, "Unable to get icon resource", icon222);
            return 0;
        }
    }

    @Nullable
    @RequiresApi(23)
    private static Uri getUri(@NonNull Icon icon) {
        if (VERSION.SDK_INT >= 28) {
            return icon.getUri();
        }
        try {
            return (Uri) icon.getClass().getMethod("getUri", new Class[0]).invoke(icon, new Object[0]);
        } catch (Icon icon2) {
            Log.e(TAG, "Unable to get icon uri", icon2);
            return null;
        } catch (Icon icon22) {
            Log.e(TAG, "Unable to get icon uri", icon22);
            return null;
        } catch (Icon icon222) {
            Log.e(TAG, "Unable to get icon uri", icon222);
            return null;
        }
    }

    @VisibleForTesting
    static Bitmap createLegacyIconFromAdaptiveIcon(Bitmap bitmap, boolean z) {
        int min = (int) (((float) Math.min(bitmap.getWidth(), bitmap.getHeight())) * DEFAULT_VIEW_PORT_SCALE);
        Bitmap createBitmap = Bitmap.createBitmap(min, min, Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint(3);
        float f = (float) min;
        float f2 = 0.5f * f;
        float f3 = ICON_DIAMETER_FACTOR * f2;
        if (z) {
            z = true * f;
            paint.setColor(0);
            paint.setShadowLayer(z, 0.0f, f * KEY_SHADOW_OFFSET_FACTOR, 1023410176);
            canvas.drawCircle(f2, f2, f3, paint);
            paint.setShadowLayer(z, 0.0f, 0.0f, 503316480);
            canvas.drawCircle(f2, f2, f3, paint);
            paint.clearShadowLayer();
        }
        paint.setColor(true);
        z = new BitmapShader(bitmap, TileMode.CLAMP, TileMode.CLAMP);
        Matrix matrix = new Matrix();
        matrix.setTranslate((float) ((-(bitmap.getWidth() - min)) / 2), (float) ((-(bitmap.getHeight() - min)) / 2));
        z.setLocalMatrix(matrix);
        paint.setShader(z);
        canvas.drawCircle(f2, f2, f3, paint);
        canvas.setBitmap(null);
        return createBitmap;
    }
}
