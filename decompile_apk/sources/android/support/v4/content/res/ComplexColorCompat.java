package android.support.v4.content.res;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.graphics.Shader;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParserException;

@RestrictTo({Scope.LIBRARY_GROUP})
public final class ComplexColorCompat {
    private static final String LOG_TAG = "ComplexColorCompat";
    private int mColor;
    private final ColorStateList mColorStateList;
    private final Shader mShader;

    private ComplexColorCompat(Shader shader, ColorStateList colorStateList, @ColorInt int i) {
        this.mShader = shader;
        this.mColorStateList = colorStateList;
        this.mColor = i;
    }

    static ComplexColorCompat from(@NonNull Shader shader) {
        return new ComplexColorCompat(shader, null, 0);
    }

    static ComplexColorCompat from(@NonNull ColorStateList colorStateList) {
        return new ComplexColorCompat(null, colorStateList, colorStateList.getDefaultColor());
    }

    static ComplexColorCompat from(@ColorInt int i) {
        return new ComplexColorCompat(null, null, i);
    }

    @Nullable
    public Shader getShader() {
        return this.mShader;
    }

    @ColorInt
    public int getColor() {
        return this.mColor;
    }

    public void setColor(@ColorInt int i) {
        this.mColor = i;
    }

    public boolean isGradient() {
        return this.mShader != null;
    }

    public boolean isStateful() {
        return this.mShader == null && this.mColorStateList != null && this.mColorStateList.isStateful();
    }

    public boolean onStateChanged(int[] iArr) {
        if (isStateful()) {
            iArr = this.mColorStateList.getColorForState(iArr, this.mColorStateList.getDefaultColor());
            if (iArr != this.mColor) {
                this.mColor = iArr;
                return true;
            }
        }
        return false;
    }

    public boolean willDraw() {
        if (!isGradient()) {
            if (this.mColor == 0) {
                return false;
            }
        }
        return true;
    }

    @Nullable
    public static ComplexColorCompat inflate(@NonNull Resources resources, @ColorRes int i, @Nullable Theme theme) {
        try {
            return createFromXml(resources, i, theme);
        } catch (Resources resources2) {
            Log.e(LOG_TAG, "Failed to inflate ComplexColor.", resources2);
            return null;
        }
    }

    @NonNull
    private static ComplexColorCompat createFromXml(@NonNull Resources resources, @ColorRes int i, @Nullable Theme theme) throws IOException, XmlPullParserException {
        int hashCode;
        i = resources.getXml(i);
        AttributeSet asAttributeSet = Xml.asAttributeSet(i);
        while (true) {
            int next = i.next();
            Object obj = 1;
            String name;
            if (next == 2 || next == 1) {
                if (next != 2) {
                    name = i.getName();
                    hashCode = name.hashCode();
                    if (hashCode == 89650992) {
                        if (hashCode != 1191572447) {
                            if (name.equals("selector")) {
                                obj = null;
                                switch (obj) {
                                    case null:
                                        return from(ColorStateListInflaterCompat.createFromXmlInner(resources, i, asAttributeSet, theme));
                                    case 1:
                                        return from(GradientColorInflaterCompat.createFromXmlInner(resources, i, asAttributeSet, theme));
                                    default:
                                        theme = new StringBuilder();
                                        theme.append(i.getPositionDescription());
                                        theme.append(": unsupported complex color tag ");
                                        theme.append(name);
                                        throw new XmlPullParserException(theme.toString());
                                }
                            }
                        }
                    } else if (name.equals("gradient")) {
                        switch (obj) {
                            case null:
                                return from(ColorStateListInflaterCompat.createFromXmlInner(resources, i, asAttributeSet, theme));
                            case 1:
                                return from(GradientColorInflaterCompat.createFromXmlInner(resources, i, asAttributeSet, theme));
                            default:
                                theme = new StringBuilder();
                                theme.append(i.getPositionDescription());
                                theme.append(": unsupported complex color tag ");
                                theme.append(name);
                                throw new XmlPullParserException(theme.toString());
                        }
                    }
                    obj = -1;
                    switch (obj) {
                        case null:
                            return from(ColorStateListInflaterCompat.createFromXmlInner(resources, i, asAttributeSet, theme));
                        case 1:
                            return from(GradientColorInflaterCompat.createFromXmlInner(resources, i, asAttributeSet, theme));
                        default:
                            theme = new StringBuilder();
                            theme.append(i.getPositionDescription());
                            theme.append(": unsupported complex color tag ");
                            theme.append(name);
                            throw new XmlPullParserException(theme.toString());
                    }
                }
                throw new XmlPullParserException("No start tag found");
            }
        }
        if (next != 2) {
            throw new XmlPullParserException("No start tag found");
        }
        name = i.getName();
        hashCode = name.hashCode();
        if (hashCode == 89650992) {
            if (name.equals("gradient")) {
                switch (obj) {
                    case null:
                        return from(ColorStateListInflaterCompat.createFromXmlInner(resources, i, asAttributeSet, theme));
                    case 1:
                        return from(GradientColorInflaterCompat.createFromXmlInner(resources, i, asAttributeSet, theme));
                    default:
                        theme = new StringBuilder();
                        theme.append(i.getPositionDescription());
                        theme.append(": unsupported complex color tag ");
                        theme.append(name);
                        throw new XmlPullParserException(theme.toString());
                }
            }
        } else if (hashCode != 1191572447) {
            if (name.equals("selector")) {
                obj = null;
                switch (obj) {
                    case null:
                        return from(ColorStateListInflaterCompat.createFromXmlInner(resources, i, asAttributeSet, theme));
                    case 1:
                        return from(GradientColorInflaterCompat.createFromXmlInner(resources, i, asAttributeSet, theme));
                    default:
                        theme = new StringBuilder();
                        theme.append(i.getPositionDescription());
                        theme.append(": unsupported complex color tag ");
                        theme.append(name);
                        throw new XmlPullParserException(theme.toString());
                }
            }
        }
        obj = -1;
        switch (obj) {
            case null:
                return from(ColorStateListInflaterCompat.createFromXmlInner(resources, i, asAttributeSet, theme));
            case 1:
                return from(GradientColorInflaterCompat.createFromXmlInner(resources, i, asAttributeSet, theme));
            default:
                theme = new StringBuilder();
                theme.append(i.getPositionDescription());
                theme.append(": unsupported complex color tag ");
                theme.append(name);
                throw new XmlPullParserException(theme.toString());
        }
    }
}
