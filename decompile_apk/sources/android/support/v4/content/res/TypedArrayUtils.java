package android.support.v4.content.res;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.AnyRes;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.annotation.StyleableRes;
import android.util.AttributeSet;
import android.util.TypedValue;
import org.xmlpull.v1.XmlPullParser;

@RestrictTo({Scope.LIBRARY_GROUP})
public class TypedArrayUtils {
    private static final String NAMESPACE = "http://schemas.android.com/apk/res/android";

    public static boolean hasAttribute(@NonNull XmlPullParser xmlPullParser, @NonNull String str) {
        return xmlPullParser.getAttributeValue(NAMESPACE, str) != null ? true : null;
    }

    public static float getNamedFloat(@NonNull TypedArray typedArray, @NonNull XmlPullParser xmlPullParser, @NonNull String str, @StyleableRes int i, float f) {
        if (hasAttribute(xmlPullParser, str) == null) {
            return f;
        }
        return typedArray.getFloat(i, f);
    }

    public static boolean getNamedBoolean(@NonNull TypedArray typedArray, @NonNull XmlPullParser xmlPullParser, @NonNull String str, @StyleableRes int i, boolean z) {
        if (hasAttribute(xmlPullParser, str) == null) {
            return z;
        }
        return typedArray.getBoolean(i, z);
    }

    public static int getNamedInt(@NonNull TypedArray typedArray, @NonNull XmlPullParser xmlPullParser, @NonNull String str, @StyleableRes int i, int i2) {
        if (hasAttribute(xmlPullParser, str) == null) {
            return i2;
        }
        return typedArray.getInt(i, i2);
    }

    @ColorInt
    public static int getNamedColor(@NonNull TypedArray typedArray, @NonNull XmlPullParser xmlPullParser, @NonNull String str, @StyleableRes int i, @ColorInt int i2) {
        if (hasAttribute(xmlPullParser, str) == null) {
            return i2;
        }
        return typedArray.getColor(i, i2);
    }

    public static ComplexColorCompat getNamedComplexColor(@NonNull TypedArray typedArray, @NonNull XmlPullParser xmlPullParser, @Nullable Theme theme, @NonNull String str, @StyleableRes int i, @ColorInt int i2) {
        if (hasAttribute(xmlPullParser, str) != null) {
            xmlPullParser = new TypedValue();
            typedArray.getValue(i, xmlPullParser);
            if (xmlPullParser.type >= 28 && xmlPullParser.type <= 31) {
                return ComplexColorCompat.from(xmlPullParser.data);
            }
            typedArray = ComplexColorCompat.inflate(typedArray.getResources(), typedArray.getResourceId(i, null), theme);
            if (typedArray != null) {
                return typedArray;
            }
        }
        return ComplexColorCompat.from(i2);
    }

    @AnyRes
    public static int getNamedResourceId(@NonNull TypedArray typedArray, @NonNull XmlPullParser xmlPullParser, @NonNull String str, @StyleableRes int i, @AnyRes int i2) {
        if (hasAttribute(xmlPullParser, str) == null) {
            return i2;
        }
        return typedArray.getResourceId(i, i2);
    }

    @Nullable
    public static String getNamedString(@NonNull TypedArray typedArray, @NonNull XmlPullParser xmlPullParser, @NonNull String str, @StyleableRes int i) {
        if (hasAttribute(xmlPullParser, str) == null) {
            return null;
        }
        return typedArray.getString(i);
    }

    @Nullable
    public static TypedValue peekNamedValue(@NonNull TypedArray typedArray, @NonNull XmlPullParser xmlPullParser, @NonNull String str, int i) {
        if (hasAttribute(xmlPullParser, str) == null) {
            return null;
        }
        return typedArray.peekValue(i);
    }

    @NonNull
    public static TypedArray obtainAttributes(@NonNull Resources resources, @Nullable Theme theme, @NonNull AttributeSet attributeSet, @NonNull int[] iArr) {
        if (theme == null) {
            return resources.obtainAttributes(attributeSet, iArr);
        }
        return theme.obtainStyledAttributes(attributeSet, iArr, 0, 0);
    }

    public static boolean getBoolean(@NonNull TypedArray typedArray, @StyleableRes int i, @StyleableRes int i2, boolean z) {
        return typedArray.getBoolean(i, typedArray.getBoolean(i2, z));
    }

    @Nullable
    public static Drawable getDrawable(@NonNull TypedArray typedArray, @StyleableRes int i, @StyleableRes int i2) {
        i = typedArray.getDrawable(i);
        return i == 0 ? typedArray.getDrawable(i2) : i;
    }

    public static int getInt(@NonNull TypedArray typedArray, @StyleableRes int i, @StyleableRes int i2, int i3) {
        return typedArray.getInt(i, typedArray.getInt(i2, i3));
    }

    @AnyRes
    public static int getResourceId(@NonNull TypedArray typedArray, @StyleableRes int i, @StyleableRes int i2, @AnyRes int i3) {
        return typedArray.getResourceId(i, typedArray.getResourceId(i2, i3));
    }

    @Nullable
    public static String getString(@NonNull TypedArray typedArray, @StyleableRes int i, @StyleableRes int i2) {
        i = typedArray.getString(i);
        return i == 0 ? typedArray.getString(i2) : i;
    }

    @Nullable
    public static CharSequence getText(@NonNull TypedArray typedArray, @StyleableRes int i, @StyleableRes int i2) {
        i = typedArray.getText(i);
        return i == 0 ? typedArray.getText(i2) : i;
    }

    @Nullable
    public static CharSequence[] getTextArray(@NonNull TypedArray typedArray, @StyleableRes int i, @StyleableRes int i2) {
        i = typedArray.getTextArray(i);
        return i == 0 ? typedArray.getTextArray(i2) : i;
    }

    public static int getAttr(@NonNull Context context, int i, int i2) {
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(i, typedValue, true);
        return typedValue.resourceId != null ? i : i2;
    }

    private TypedArrayUtils() {
    }
}
