package android.support.v7.graphics.drawable;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.content.res.TypedArrayUtils;
import android.support.v7.appcompat.C0192R;
import android.support.v7.content.res.AppCompatResources;
import android.util.AttributeSet;
import android.util.StateSet;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

@RestrictTo({Scope.LIBRARY_GROUP})
class StateListDrawable extends DrawableContainer {
    private static final boolean DEBUG = false;
    private static final String TAG = "StateListDrawable";
    private boolean mMutated;
    private StateListState mStateListState;

    static class StateListState extends DrawableContainerState {
        int[][] mStateSets;

        StateListState(StateListState stateListState, StateListDrawable stateListDrawable, Resources resources) {
            super(stateListState, stateListDrawable, resources);
            if (stateListState != null) {
                this.mStateSets = stateListState.mStateSets;
            } else {
                this.mStateSets = new int[getCapacity()][];
            }
        }

        void mutate() {
            int[][] iArr = new int[this.mStateSets.length][];
            for (int length = this.mStateSets.length - 1; length >= 0; length--) {
                iArr[length] = this.mStateSets[length] != null ? (int[]) this.mStateSets[length].clone() : null;
            }
            this.mStateSets = iArr;
        }

        int addStateSet(int[] iArr, Drawable drawable) {
            drawable = addChild(drawable);
            this.mStateSets[drawable] = iArr;
            return drawable;
        }

        int indexOfStateSet(int[] iArr) {
            int[][] iArr2 = this.mStateSets;
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                if (StateSet.stateSetMatches(iArr2[i], iArr)) {
                    return i;
                }
            }
            return -1;
        }

        @NonNull
        public Drawable newDrawable() {
            return new StateListDrawable(this, null);
        }

        @NonNull
        public Drawable newDrawable(Resources resources) {
            return new StateListDrawable(this, resources);
        }

        public void growArray(int i, int i2) {
            super.growArray(i, i2);
            i2 = new int[i2][];
            System.arraycopy(this.mStateSets, 0, i2, 0, i);
            this.mStateSets = i2;
        }
    }

    public boolean isStateful() {
        return true;
    }

    StateListDrawable() {
        this(null, null);
    }

    public void addState(int[] iArr, Drawable drawable) {
        if (drawable != null) {
            this.mStateListState.addStateSet(iArr, drawable);
            onStateChange(getState());
        }
    }

    protected boolean onStateChange(int[] iArr) {
        boolean onStateChange = super.onStateChange(iArr);
        iArr = this.mStateListState.indexOfStateSet(iArr);
        if (iArr < null) {
            iArr = this.mStateListState.indexOfStateSet(StateSet.WILD_CARD);
        }
        if (selectDrawable(iArr) == null) {
            if (!onStateChange) {
                return null;
            }
        }
        return 1;
    }

    public void inflate(@NonNull Context context, @NonNull Resources resources, @NonNull XmlPullParser xmlPullParser, @NonNull AttributeSet attributeSet, @Nullable Theme theme) throws XmlPullParserException, IOException {
        TypedArray obtainAttributes = TypedArrayUtils.obtainAttributes(resources, theme, attributeSet, C0192R.styleable.StateListDrawable);
        setVisible(obtainAttributes.getBoolean(C0192R.styleable.StateListDrawable_android_visible, true), true);
        updateStateFromTypedArray(obtainAttributes);
        updateDensity(resources);
        obtainAttributes.recycle();
        inflateChildElements(context, resources, xmlPullParser, attributeSet, theme);
        onStateChange(getState());
    }

    private void updateStateFromTypedArray(TypedArray typedArray) {
        StateListState stateListState = this.mStateListState;
        if (VERSION.SDK_INT >= 21) {
            stateListState.mChangingConfigurations |= typedArray.getChangingConfigurations();
        }
        stateListState.mVariablePadding = typedArray.getBoolean(C0192R.styleable.StateListDrawable_android_variablePadding, stateListState.mVariablePadding);
        stateListState.mConstantSize = typedArray.getBoolean(C0192R.styleable.StateListDrawable_android_constantSize, stateListState.mConstantSize);
        stateListState.mEnterFadeDuration = typedArray.getInt(C0192R.styleable.StateListDrawable_android_enterFadeDuration, stateListState.mEnterFadeDuration);
        stateListState.mExitFadeDuration = typedArray.getInt(C0192R.styleable.StateListDrawable_android_exitFadeDuration, stateListState.mExitFadeDuration);
        stateListState.mDither = typedArray.getBoolean(C0192R.styleable.StateListDrawable_android_dither, stateListState.mDither);
    }

    private void inflateChildElements(Context context, Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Theme theme) throws XmlPullParserException, IOException {
        StateListState stateListState = this.mStateListState;
        int depth = xmlPullParser.getDepth() + 1;
        while (true) {
            int next = xmlPullParser.next();
            if (next != 1) {
                int depth2 = xmlPullParser.getDepth();
                if (depth2 < depth && next == 3) {
                    return;
                }
                if (next == 2) {
                    if (depth2 > depth) {
                        continue;
                    } else if (xmlPullParser.getName().equals("item")) {
                        TypedArray obtainAttributes = TypedArrayUtils.obtainAttributes(resources, theme, attributeSet, C0192R.styleable.StateListDrawableItem);
                        Drawable drawable = null;
                        int resourceId = obtainAttributes.getResourceId(C0192R.styleable.StateListDrawableItem_android_drawable, -1);
                        if (resourceId > 0) {
                            drawable = AppCompatResources.getDrawable(context, resourceId);
                        }
                        obtainAttributes.recycle();
                        int[] extractStateSet = extractStateSet(attributeSet);
                        if (drawable == null) {
                            while (true) {
                                depth2 = xmlPullParser.next();
                                if (depth2 != 4) {
                                    break;
                                }
                            }
                            if (depth2 != 2) {
                                resources = new StringBuilder();
                                resources.append(xmlPullParser.getPositionDescription());
                                resources.append(": <item> tag requires a 'drawable' attribute or ");
                                resources.append("child tag defining a drawable");
                                throw new XmlPullParserException(resources.toString());
                            } else if (VERSION.SDK_INT >= 21) {
                                drawable = Drawable.createFromXmlInner(resources, xmlPullParser, attributeSet, theme);
                            } else {
                                drawable = Drawable.createFromXmlInner(resources, xmlPullParser, attributeSet);
                            }
                        }
                        stateListState.addStateSet(extractStateSet, drawable);
                    }
                }
            } else {
                return;
            }
        }
    }

    int[] extractStateSet(AttributeSet attributeSet) {
        int attributeCount = attributeSet.getAttributeCount();
        int[] iArr = new int[attributeCount];
        int i = 0;
        for (int i2 = 0; i2 < attributeCount; i2++) {
            int attributeNameResource = attributeSet.getAttributeNameResource(i2);
            if (!(attributeNameResource == 0 || attributeNameResource == 16842960 || attributeNameResource == 16843161)) {
                int i3 = i + 1;
                if (!attributeSet.getAttributeBooleanValue(i2, false)) {
                    attributeNameResource = -attributeNameResource;
                }
                iArr[i] = attributeNameResource;
                i = i3;
            }
        }
        return StateSet.trimStateSet(iArr, i);
    }

    StateListState getStateListState() {
        return this.mStateListState;
    }

    int getStateCount() {
        return this.mStateListState.getChildCount();
    }

    int[] getStateSet(int i) {
        return this.mStateListState.mStateSets[i];
    }

    Drawable getStateDrawable(int i) {
        return this.mStateListState.getChild(i);
    }

    int getStateDrawableIndex(int[] iArr) {
        return this.mStateListState.indexOfStateSet(iArr);
    }

    @NonNull
    public Drawable mutate() {
        if (!this.mMutated && super.mutate() == this) {
            this.mStateListState.mutate();
            this.mMutated = true;
        }
        return this;
    }

    StateListState cloneConstantState() {
        return new StateListState(this.mStateListState, this, null);
    }

    void clearMutated() {
        super.clearMutated();
        this.mMutated = false;
    }

    @RequiresApi(21)
    public void applyTheme(@NonNull Theme theme) {
        super.applyTheme(theme);
        onStateChange(getState());
    }

    protected void setConstantState(@NonNull DrawableContainerState drawableContainerState) {
        super.setConstantState(drawableContainerState);
        if (drawableContainerState instanceof StateListState) {
            this.mStateListState = (StateListState) drawableContainerState;
        }
    }

    StateListDrawable(StateListState stateListState, Resources resources) {
        setConstantState(new StateListState(stateListState, this, resources));
        onStateChange(getState());
    }

    StateListDrawable(@Nullable StateListState stateListState) {
        if (stateListState != null) {
            setConstantState(stateListState);
        }
    }
}
