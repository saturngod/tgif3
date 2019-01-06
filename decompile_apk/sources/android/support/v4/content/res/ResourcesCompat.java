package android.support.v4.content.res;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.content.res.Resources.Theme;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.FontRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.content.res.FontResourcesParserCompat.FamilyResourceEntry;
import android.support.v4.graphics.TypefaceCompat;
import android.support.v4.util.Preconditions;
import android.util.Log;
import android.util.TypedValue;

public final class ResourcesCompat {
    private static final String TAG = "ResourcesCompat";

    public static abstract class FontCallback {
        public abstract void onFontRetrievalFailed(int i);

        public abstract void onFontRetrieved(@NonNull Typeface typeface);

        @RestrictTo({Scope.LIBRARY_GROUP})
        public final void callbackSuccessAsync(final Typeface typeface, @Nullable Handler handler) {
            if (handler == null) {
                handler = new Handler(Looper.getMainLooper());
            }
            handler.post(new Runnable() {
                public void run() {
                    FontCallback.this.onFontRetrieved(typeface);
                }
            });
        }

        @RestrictTo({Scope.LIBRARY_GROUP})
        public final void callbackFailAsync(final int i, @Nullable Handler handler) {
            if (handler == null) {
                handler = new Handler(Looper.getMainLooper());
            }
            handler.post(new Runnable() {
                public void run() {
                    FontCallback.this.onFontRetrievalFailed(i);
                }
            });
        }
    }

    @Nullable
    public static Drawable getDrawable(@NonNull Resources resources, @DrawableRes int i, @Nullable Theme theme) throws NotFoundException {
        if (VERSION.SDK_INT >= 21) {
            return resources.getDrawable(i, theme);
        }
        return resources.getDrawable(i);
    }

    @Nullable
    public static Drawable getDrawableForDensity(@NonNull Resources resources, @DrawableRes int i, int i2, @Nullable Theme theme) throws NotFoundException {
        if (VERSION.SDK_INT >= 21) {
            return resources.getDrawableForDensity(i, i2, theme);
        }
        if (VERSION.SDK_INT >= 15) {
            return resources.getDrawableForDensity(i, i2);
        }
        return resources.getDrawable(i);
    }

    @ColorInt
    public static int getColor(@NonNull Resources resources, @ColorRes int i, @Nullable Theme theme) throws NotFoundException {
        if (VERSION.SDK_INT >= 23) {
            return resources.getColor(i, theme);
        }
        return resources.getColor(i);
    }

    @Nullable
    public static ColorStateList getColorStateList(@NonNull Resources resources, @ColorRes int i, @Nullable Theme theme) throws NotFoundException {
        if (VERSION.SDK_INT >= 23) {
            return resources.getColorStateList(i, theme);
        }
        return resources.getColorStateList(i);
    }

    @Nullable
    public static Typeface getFont(@NonNull Context context, @FontRes int i) throws NotFoundException {
        if (context.isRestricted()) {
            return null;
        }
        return loadFont(context, i, new TypedValue(), 0, null, null, false);
    }

    public static void getFont(@NonNull Context context, @FontRes int i, @NonNull FontCallback fontCallback, @Nullable Handler handler) throws NotFoundException {
        Preconditions.checkNotNull(fontCallback);
        if (context.isRestricted()) {
            fontCallback.callbackFailAsync(-4, handler);
            return;
        }
        loadFont(context, i, new TypedValue(), 0, fontCallback, handler, false);
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public static Typeface getFont(@NonNull Context context, @FontRes int i, TypedValue typedValue, int i2, @Nullable FontCallback fontCallback) throws NotFoundException {
        if (context.isRestricted()) {
            return null;
        }
        return loadFont(context, i, typedValue, i2, fontCallback, null, true);
    }

    private static Typeface loadFont(@NonNull Context context, int i, TypedValue typedValue, int i2, @Nullable FontCallback fontCallback, @Nullable Handler handler, boolean z) {
        Resources resources = context.getResources();
        resources.getValue(i, typedValue, true);
        context = loadFont(context, resources, typedValue, i, i2, fontCallback, handler, z);
        if (context == null) {
            if (fontCallback == null) {
                typedValue = new StringBuilder();
                typedValue.append("Font resource ID #0x");
                typedValue.append(Integer.toHexString(i));
                typedValue.append(" could not be retrieved.");
                throw new NotFoundException(typedValue.toString());
            }
        }
        return context;
    }

    private static Typeface loadFont(@NonNull Context context, Resources resources, TypedValue typedValue, int i, int i2, @Nullable FontCallback fontCallback, @Nullable Handler handler, boolean z) {
        String str;
        StringBuilder stringBuilder;
        Resources resources2 = resources;
        TypedValue typedValue2 = typedValue;
        int i3 = i;
        int i4 = i2;
        FontCallback fontCallback2 = fontCallback;
        Handler handler2 = handler;
        if (typedValue2.string != null) {
            String charSequence = typedValue2.string.toString();
            if (charSequence.startsWith("res/")) {
                Typeface findFromCache = TypefaceCompat.findFromCache(resources, i3, i4);
                if (findFromCache != null) {
                    if (fontCallback2 != null) {
                        fontCallback2.callbackSuccessAsync(findFromCache, handler2);
                    }
                    return findFromCache;
                }
                try {
                    if (charSequence.toLowerCase().endsWith(".xml")) {
                        FamilyResourceEntry parse = FontResourcesParserCompat.parse(resources.getXml(i3), resources);
                        if (parse != null) {
                            return TypefaceCompat.createFromResourcesFamilyXml(context, parse, resources, i, i2, fontCallback, handler, z);
                        }
                        Log.e(TAG, "Failed to find font-family tag");
                        if (fontCallback2 != null) {
                            fontCallback2.callbackFailAsync(-3, handler2);
                        }
                        return null;
                    }
                    Context context2 = context;
                    Typeface createFromResourcesFontFile = TypefaceCompat.createFromResourcesFontFile(context, resources, i3, charSequence, i4);
                    if (fontCallback2 != null) {
                        if (createFromResourcesFontFile != null) {
                            fontCallback2.callbackSuccessAsync(createFromResourcesFontFile, handler2);
                        } else {
                            fontCallback2.callbackFailAsync(-3, handler2);
                        }
                    }
                    return createFromResourcesFontFile;
                } catch (Throwable e) {
                    str = TAG;
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("Failed to parse xml resource ");
                    stringBuilder.append(charSequence);
                    Log.e(str, stringBuilder.toString(), e);
                    if (fontCallback2 != null) {
                        fontCallback2.callbackFailAsync(-3, handler2);
                    }
                    return null;
                } catch (Throwable e2) {
                    str = TAG;
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("Failed to read xml resource ");
                    stringBuilder.append(charSequence);
                    Log.e(str, stringBuilder.toString(), e2);
                    if (fontCallback2 != null) {
                        fontCallback2.callbackFailAsync(-3, handler2);
                    }
                    return null;
                }
            }
            if (fontCallback2 != null) {
                fontCallback2.callbackFailAsync(-3, handler2);
            }
            return null;
        }
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("Resource \"");
        stringBuilder2.append(resources.getResourceName(i3));
        stringBuilder2.append("\" (");
        stringBuilder2.append(Integer.toHexString(i));
        stringBuilder2.append(") is not a Font: ");
        stringBuilder2.append(typedValue2);
        throw new NotFoundException(stringBuilder2.toString());
    }

    private ResourcesCompat() {
    }
}
