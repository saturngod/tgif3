package android.support.v4.content.res;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.annotation.FloatRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.compat.C0014R;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.StateSet;
import android.util.Xml;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

@RestrictTo({Scope.LIBRARY_GROUP})
public final class ColorStateListInflaterCompat {
    private static final int DEFAULT_COLOR = -65536;

    private ColorStateListInflaterCompat() {
    }

    @NonNull
    public static ColorStateList createFromXml(@NonNull Resources resources, @NonNull XmlPullParser xmlPullParser, @Nullable Theme theme) throws XmlPullParserException, IOException {
        AttributeSet asAttributeSet = Xml.asAttributeSet(xmlPullParser);
        while (true) {
            int next = xmlPullParser.next();
            if (next == 2 || next == 1) {
                if (next == 2) {
                    return createFromXmlInner(resources, xmlPullParser, asAttributeSet, theme);
                }
                throw new XmlPullParserException("No start tag found");
            }
        }
        if (next == 2) {
            return createFromXmlInner(resources, xmlPullParser, asAttributeSet, theme);
        }
        throw new XmlPullParserException("No start tag found");
    }

    @NonNull
    public static ColorStateList createFromXmlInner(@NonNull Resources resources, @NonNull XmlPullParser xmlPullParser, @NonNull AttributeSet attributeSet, @Nullable Theme theme) throws XmlPullParserException, IOException {
        String name = xmlPullParser.getName();
        if (name.equals("selector")) {
            return inflate(resources, xmlPullParser, attributeSet, theme);
        }
        attributeSet = new StringBuilder();
        attributeSet.append(xmlPullParser.getPositionDescription());
        attributeSet.append(": invalid color state list tag ");
        attributeSet.append(name);
        throw new XmlPullParserException(attributeSet.toString());
    }

    private static ColorStateList inflate(@NonNull Resources resources, @NonNull XmlPullParser xmlPullParser, @NonNull AttributeSet attributeSet, @Nullable Theme theme) throws XmlPullParserException, IOException {
        AttributeSet attributeSet2 = attributeSet;
        int i = 1;
        int depth = xmlPullParser.getDepth() + 1;
        Object obj = new int[20][];
        Object obj2 = new int[obj.length];
        int i2 = 0;
        while (true) {
            int next = xmlPullParser.next();
            if (next == i) {
                break;
            }
            int depth2 = xmlPullParser.getDepth();
            if (depth2 < depth && next == 3) {
                break;
            }
            if (next == 2 && depth2 <= depth) {
                if (xmlPullParser.getName().equals("item")) {
                    TypedArray obtainAttributes = obtainAttributes(resources, theme, attributeSet2, C0014R.styleable.ColorStateListItem);
                    int color = obtainAttributes.getColor(C0014R.styleable.ColorStateListItem_android_color, -65281);
                    float f = 1.0f;
                    if (obtainAttributes.hasValue(C0014R.styleable.ColorStateListItem_android_alpha)) {
                        f = obtainAttributes.getFloat(C0014R.styleable.ColorStateListItem_android_alpha, 1.0f);
                    } else if (obtainAttributes.hasValue(C0014R.styleable.ColorStateListItem_alpha)) {
                        f = obtainAttributes.getFloat(C0014R.styleable.ColorStateListItem_alpha, 1.0f);
                    }
                    obtainAttributes.recycle();
                    next = attributeSet.getAttributeCount();
                    int[] iArr = new int[next];
                    int i3 = 0;
                    for (int i4 = 0; i4 < next; i4++) {
                        int attributeNameResource = attributeSet2.getAttributeNameResource(i4);
                        if (!(attributeNameResource == 16843173 || attributeNameResource == 16843551 || attributeNameResource == C0014R.attr.alpha)) {
                            i = i3 + 1;
                            if (!attributeSet2.getAttributeBooleanValue(i4, false)) {
                                attributeNameResource = -attributeNameResource;
                            }
                            iArr[i3] = attributeNameResource;
                            i3 = i;
                        }
                    }
                    Object trimStateSet = StateSet.trimStateSet(iArr, i3);
                    next = modulateColorAlpha(color, f);
                    if (i2 != 0) {
                        color = trimStateSet.length;
                    }
                    obj2 = GrowingArrayUtils.append((int[]) obj2, i2, next);
                    obj = (int[][]) GrowingArrayUtils.append((Object[]) obj, i2, trimStateSet);
                    i2++;
                    i = 1;
                }
            }
            Resources resources2 = resources;
            Theme theme2 = theme;
            i = 1;
        }
        Object obj3 = new int[i2];
        Object obj4 = new int[i2][];
        System.arraycopy(obj2, 0, obj3, 0, i2);
        System.arraycopy(obj, 0, obj4, 0, i2);
        return new ColorStateList(obj4, obj3);
    }

    private static TypedArray obtainAttributes(Resources resources, Theme theme, AttributeSet attributeSet, int[] iArr) {
        if (theme == null) {
            return resources.obtainAttributes(attributeSet, iArr);
        }
        return theme.obtainStyledAttributes(attributeSet, iArr, 0, 0);
    }

    @ColorInt
    private static int modulateColorAlpha(@ColorInt int i, @FloatRange(from = 0.0d, to = 1.0d) float f) {
        return (i & ViewCompat.MEASURED_SIZE_MASK) | (Math.round(((float) Color.alpha(i)) * f) << 24);
    }
}
