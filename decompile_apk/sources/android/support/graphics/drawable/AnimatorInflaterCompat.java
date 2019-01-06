package android.support.graphics.drawable;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.os.Build.VERSION;
import android.support.annotation.AnimatorRes;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.content.res.TypedArrayUtils;
import android.support.v4.graphics.PathParser;
import android.support.v4.graphics.PathParser.PathDataNode;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.util.Xml;
import android.view.InflateException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

@RestrictTo({Scope.LIBRARY_GROUP})
public class AnimatorInflaterCompat {
    private static final boolean DBG_ANIMATOR_INFLATER = false;
    private static final int MAX_NUM_POINTS = 100;
    private static final String TAG = "AnimatorInflater";
    private static final int TOGETHER = 0;
    private static final int VALUE_TYPE_COLOR = 3;
    private static final int VALUE_TYPE_FLOAT = 0;
    private static final int VALUE_TYPE_INT = 1;
    private static final int VALUE_TYPE_PATH = 2;
    private static final int VALUE_TYPE_UNDEFINED = 4;

    private static class PathDataEvaluator implements TypeEvaluator<PathDataNode[]> {
        private PathDataNode[] mNodeArray;

        PathDataEvaluator() {
        }

        PathDataEvaluator(PathDataNode[] pathDataNodeArr) {
            this.mNodeArray = pathDataNodeArr;
        }

        public PathDataNode[] evaluate(float f, PathDataNode[] pathDataNodeArr, PathDataNode[] pathDataNodeArr2) {
            if (PathParser.canMorph(pathDataNodeArr, pathDataNodeArr2)) {
                if (this.mNodeArray == null || !PathParser.canMorph(this.mNodeArray, pathDataNodeArr)) {
                    this.mNodeArray = PathParser.deepCopyNodes(pathDataNodeArr);
                }
                for (int i = 0; i < pathDataNodeArr.length; i++) {
                    this.mNodeArray[i].interpolatePathDataNode(pathDataNodeArr[i], pathDataNodeArr2[i], f);
                }
                return this.mNodeArray;
            }
            throw new IllegalArgumentException("Can't interpolate between two incompatible pathData");
        }
    }

    private static boolean isColorType(int i) {
        return i >= 28 && i <= 31;
    }

    public static Animator loadAnimator(Context context, @AnimatorRes int i) throws NotFoundException {
        if (VERSION.SDK_INT >= 24) {
            return AnimatorInflater.loadAnimator(context, i);
        }
        return loadAnimator(context, context.getResources(), context.getTheme(), i);
    }

    public static Animator loadAnimator(Context context, Resources resources, Theme theme, @AnimatorRes int i) throws NotFoundException {
        return loadAnimator(context, resources, theme, i, 1.0f);
    }

    public static Animator loadAnimator(Context context, Resources resources, Theme theme, @AnimatorRes int i, float f) throws NotFoundException {
        XmlResourceParser xmlResourceParser = null;
        try {
            XmlResourceParser animation = resources.getAnimation(i);
            try {
                context = createAnimatorFromXml(context, resources, theme, animation, f);
                if (animation != null) {
                    animation.close();
                }
                return context;
            } catch (XmlPullParserException e) {
                context = e;
                xmlResourceParser = animation;
                theme = new StringBuilder();
                theme.append("Can't load animation resource ID #0x");
                theme.append(Integer.toHexString(i));
                resources = new NotFoundException(theme.toString());
                resources.initCause(context);
                throw resources;
            } catch (IOException e2) {
                context = e2;
                xmlResourceParser = animation;
                theme = new StringBuilder();
                theme.append("Can't load animation resource ID #0x");
                theme.append(Integer.toHexString(i));
                resources = new NotFoundException(theme.toString());
                resources.initCause(context);
                throw resources;
            } catch (Throwable th) {
                context = th;
                xmlResourceParser = animation;
                if (xmlResourceParser != null) {
                    xmlResourceParser.close();
                }
                throw context;
            }
        } catch (XmlPullParserException e3) {
            context = e3;
            theme = new StringBuilder();
            theme.append("Can't load animation resource ID #0x");
            theme.append(Integer.toHexString(i));
            resources = new NotFoundException(theme.toString());
            resources.initCause(context);
            throw resources;
        } catch (IOException e4) {
            context = e4;
            theme = new StringBuilder();
            theme.append("Can't load animation resource ID #0x");
            theme.append(Integer.toHexString(i));
            resources = new NotFoundException(theme.toString());
            resources.initCause(context);
            throw resources;
        } catch (Throwable th2) {
            context = th2;
            if (xmlResourceParser != null) {
                xmlResourceParser.close();
            }
            throw context;
        }
    }

    private static PropertyValuesHolder getPVH(TypedArray typedArray, int i, int i2, int i3, String str) {
        TypedValue peekValue = typedArray.peekValue(i2);
        Object obj = peekValue != null ? 1 : null;
        int i4 = obj != null ? peekValue.type : 0;
        TypedValue peekValue2 = typedArray.peekValue(i3);
        Object obj2 = peekValue2 != null ? 1 : null;
        int i5 = obj2 != null ? peekValue2.type : 0;
        if (i == 4) {
            i = ((obj == null || isColorType(i4) == 0) && (obj2 == null || isColorType(i5) == 0)) ? 0 : 3;
        }
        Object obj3 = i == 0 ? 1 : null;
        PropertyValuesHolder propertyValuesHolder = null;
        if (i == 2) {
            i = typedArray.getString(i2);
            typedArray = typedArray.getString(i3);
            i2 = PathParser.createNodesFromPathData(i);
            i3 = PathParser.createNodesFromPathData(typedArray);
            if (i2 == 0 && i3 == 0) {
                return null;
            }
            if (i2 != 0) {
                TypeEvaluator pathDataEvaluator = new PathDataEvaluator();
                if (i3 == 0) {
                    typedArray = PropertyValuesHolder.ofObject(str, pathDataEvaluator, new Object[]{i2});
                } else if (PathParser.canMorph(i2, i3)) {
                    typedArray = PropertyValuesHolder.ofObject(str, pathDataEvaluator, new Object[]{i2, i3});
                } else {
                    i3 = new StringBuilder();
                    i3.append(" Can't morph from ");
                    i3.append(i);
                    i3.append(" to ");
                    i3.append(typedArray);
                    throw new InflateException(i3.toString());
                }
                return typedArray;
            } else if (i3 == 0) {
                return null;
            } else {
                return PropertyValuesHolder.ofObject(str, new PathDataEvaluator(), new Object[]{i3});
            }
        }
        i = i == 3 ? ArgbEvaluator.getInstance() : 0;
        if (obj3 != null) {
            if (obj != null) {
                if (i4 == 5) {
                    i2 = typedArray.getDimension(i2, 0.0f);
                } else {
                    i2 = typedArray.getFloat(i2, 0.0f);
                }
                if (obj2 != null) {
                    if (i5 == 5) {
                        typedArray = typedArray.getDimension(i3, 0.0f);
                    } else {
                        typedArray = typedArray.getFloat(i3, 0.0f);
                    }
                    typedArray = PropertyValuesHolder.ofFloat(str, new float[]{i2, typedArray});
                } else {
                    typedArray = PropertyValuesHolder.ofFloat(str, new float[]{i2});
                }
            } else {
                if (i5 == 5) {
                    typedArray = typedArray.getDimension(i3, 0.0f);
                } else {
                    typedArray = typedArray.getFloat(i3, 0.0f);
                }
                typedArray = PropertyValuesHolder.ofFloat(str, new float[]{typedArray});
            }
            propertyValuesHolder = typedArray;
        } else if (obj != null) {
            if (i4 == 5) {
                i2 = (int) typedArray.getDimension(i2, 0.0f);
            } else if (isColorType(i4)) {
                i2 = typedArray.getColor(i2, 0);
            } else {
                i2 = typedArray.getInt(i2, 0);
            }
            if (obj2 != null) {
                if (i5 == 5) {
                    typedArray = (int) typedArray.getDimension(i3, 0.0f);
                } else if (isColorType(i5)) {
                    typedArray = typedArray.getColor(i3, 0);
                } else {
                    typedArray = typedArray.getInt(i3, 0);
                }
                propertyValuesHolder = PropertyValuesHolder.ofInt(str, new int[]{i2, typedArray});
            } else {
                propertyValuesHolder = PropertyValuesHolder.ofInt(str, new int[]{i2});
            }
        } else if (obj2 != null) {
            if (i5 == 5) {
                typedArray = (int) typedArray.getDimension(i3, 0.0f);
            } else if (isColorType(i5) != 0) {
                typedArray = typedArray.getColor(i3, 0);
            } else {
                typedArray = typedArray.getInt(i3, 0);
            }
            propertyValuesHolder = PropertyValuesHolder.ofInt(str, new int[]{typedArray});
        }
        if (propertyValuesHolder == null || i == 0) {
            return propertyValuesHolder;
        }
        propertyValuesHolder.setEvaluator(i);
        return propertyValuesHolder;
    }

    private static void parseAnimatorFromTypeArray(ValueAnimator valueAnimator, TypedArray typedArray, TypedArray typedArray2, float f, XmlPullParser xmlPullParser) {
        long namedInt = (long) TypedArrayUtils.getNamedInt(typedArray, xmlPullParser, "duration", 1, 300);
        long namedInt2 = (long) TypedArrayUtils.getNamedInt(typedArray, xmlPullParser, "startOffset", 2, 0);
        int namedInt3 = TypedArrayUtils.getNamedInt(typedArray, xmlPullParser, "valueType", 7, 4);
        if (TypedArrayUtils.hasAttribute(xmlPullParser, "valueFrom") && TypedArrayUtils.hasAttribute(xmlPullParser, "valueTo")) {
            if (namedInt3 == 4) {
                namedInt3 = inferValueTypeFromValues(typedArray, 5, 6);
            }
            if (getPVH(typedArray, namedInt3, 5, 6, "") != null) {
                valueAnimator.setValues(new PropertyValuesHolder[]{getPVH(typedArray, namedInt3, 5, 6, "")});
            }
        }
        valueAnimator.setDuration(namedInt);
        valueAnimator.setStartDelay(namedInt2);
        valueAnimator.setRepeatCount(TypedArrayUtils.getNamedInt(typedArray, xmlPullParser, "repeatCount", 3, 0));
        valueAnimator.setRepeatMode(TypedArrayUtils.getNamedInt(typedArray, xmlPullParser, "repeatMode", 4, 1));
        if (typedArray2 != null) {
            setupObjectAnimator(valueAnimator, typedArray2, namedInt3, f, xmlPullParser);
        }
    }

    private static void setupObjectAnimator(ValueAnimator valueAnimator, TypedArray typedArray, int i, float f, XmlPullParser xmlPullParser) {
        ObjectAnimator objectAnimator = (ObjectAnimator) valueAnimator;
        String namedString = TypedArrayUtils.getNamedString(typedArray, xmlPullParser, "pathData", 1);
        if (namedString != null) {
            String namedString2 = TypedArrayUtils.getNamedString(typedArray, xmlPullParser, "propertyXName", 2);
            xmlPullParser = TypedArrayUtils.getNamedString(typedArray, xmlPullParser, "propertyYName", 3);
            if (i != 2) {
            }
            if (namedString2 == null) {
                if (xmlPullParser == null) {
                    i = new StringBuilder();
                    i.append(typedArray.getPositionDescription());
                    i.append(" propertyXName or propertyYName is needed for PathData");
                    throw new InflateException(i.toString());
                }
            }
            setupPathMotion(PathParser.createPathFromPathData(namedString), objectAnimator, f * 1056964608, namedString2, xmlPullParser);
            return;
        }
        objectAnimator.setPropertyName(TypedArrayUtils.getNamedString(typedArray, xmlPullParser, "propertyName", 0.0f));
    }

    private static void setupPathMotion(Path path, ObjectAnimator objectAnimator, float f, String str, String str2) {
        Path path2 = path;
        ObjectAnimator objectAnimator2 = objectAnimator;
        String str3 = str;
        String str4 = str2;
        PathMeasure pathMeasure = new PathMeasure(path2, false);
        ArrayList arrayList = new ArrayList();
        arrayList.add(Float.valueOf(0.0f));
        float f2 = 0.0f;
        do {
            f2 += pathMeasure.getLength();
            arrayList.add(Float.valueOf(f2));
        } while (pathMeasure.nextContour());
        pathMeasure = new PathMeasure(path2, false);
        int min = Math.min(100, ((int) (f2 / f)) + 1);
        float[] fArr = new float[min];
        float[] fArr2 = new float[min];
        float[] fArr3 = new float[2];
        f2 /= (float) (min - 1);
        int i = 0;
        float f3 = 0.0f;
        int i2 = 0;
        while (true) {
            float[] fArr4 = null;
            if (i >= min) {
                break;
            }
            pathMeasure.getPosTan(f3 - ((Float) arrayList.get(i2)).floatValue(), fArr3, null);
            fArr[i] = fArr3[0];
            fArr2[i] = fArr3[1];
            f3 += f2;
            int i3 = i2 + 1;
            if (i3 < arrayList.size() && f3 > ((Float) arrayList.get(i3)).floatValue()) {
                pathMeasure.nextContour();
                i2 = i3;
            }
            i++;
        }
        PropertyValuesHolder ofFloat = str3 != null ? PropertyValuesHolder.ofFloat(str3, fArr) : null;
        if (str4 != null) {
            fArr4 = PropertyValuesHolder.ofFloat(str4, fArr2);
        }
        if (ofFloat == null) {
            objectAnimator2.setValues(new PropertyValuesHolder[]{fArr4});
        } else if (fArr4 == null) {
            objectAnimator2.setValues(new PropertyValuesHolder[]{ofFloat});
        } else {
            objectAnimator2.setValues(new PropertyValuesHolder[]{ofFloat, fArr4});
        }
    }

    private static Animator createAnimatorFromXml(Context context, Resources resources, Theme theme, XmlPullParser xmlPullParser, float f) throws XmlPullParserException, IOException {
        return createAnimatorFromXml(context, resources, theme, xmlPullParser, Xml.asAttributeSet(xmlPullParser), null, 0, f);
    }

    private static Animator createAnimatorFromXml(Context context, Resources resources, Theme theme, XmlPullParser xmlPullParser, AttributeSet attributeSet, AnimatorSet animatorSet, int i, float f) throws XmlPullParserException, IOException {
        Resources resources2 = resources;
        Theme theme2 = theme;
        XmlPullParser xmlPullParser2 = xmlPullParser;
        AnimatorSet animatorSet2 = animatorSet;
        int depth = xmlPullParser.getDepth();
        Animator animator = null;
        ArrayList arrayList = null;
        while (true) {
            int next = xmlPullParser.next();
            int i2 = 0;
            if ((next != 3 || xmlPullParser.getDepth() > depth) && next != 1) {
                if (next == 2) {
                    Context context2;
                    String name = xmlPullParser.getName();
                    if (name.equals("objectAnimator")) {
                        animator = loadObjectAnimator(context, resources, theme, attributeSet, f, xmlPullParser);
                    } else if (name.equals("animator")) {
                        animator = loadAnimator(context, resources, theme, attributeSet, null, f, xmlPullParser);
                    } else {
                        if (name.equals("set")) {
                            Animator animatorSet3 = new AnimatorSet();
                            TypedArray obtainAttributes = TypedArrayUtils.obtainAttributes(resources2, theme2, attributeSet, AndroidResources.STYLEABLE_ANIMATOR_SET);
                            Context context3 = context;
                            Resources resources3 = resources;
                            Theme theme3 = theme;
                            XmlPullParser xmlPullParser3 = xmlPullParser;
                            AttributeSet attributeSet2 = attributeSet;
                            TypedArray typedArray = obtainAttributes;
                            createAnimatorFromXml(context3, resources3, theme3, xmlPullParser3, attributeSet2, (AnimatorSet) animatorSet3, TypedArrayUtils.getNamedInt(obtainAttributes, xmlPullParser2, "ordering", 0, 0), f);
                            typedArray.recycle();
                            context2 = context;
                            animator = animatorSet3;
                        } else if (name.equals("propertyValuesHolder")) {
                            PropertyValuesHolder[] loadValues = loadValues(context, resources2, theme2, xmlPullParser2, Xml.asAttributeSet(xmlPullParser));
                            if (!(loadValues == null || animator == null || !(animator instanceof ValueAnimator))) {
                                ((ValueAnimator) animator).setValues(loadValues);
                            }
                            i2 = 1;
                        } else {
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append("Unknown animator name: ");
                            stringBuilder.append(xmlPullParser.getName());
                            throw new RuntimeException(stringBuilder.toString());
                        }
                        if (animatorSet2 != null && r14 == 0) {
                            if (arrayList == null) {
                                arrayList = new ArrayList();
                            }
                            arrayList.add(animator);
                        }
                    }
                    context2 = context;
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    arrayList.add(animator);
                }
            }
        }
        if (!(animatorSet2 == null || arrayList == null)) {
            Animator[] animatorArr = new Animator[arrayList.size()];
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                int i3 = i2 + 1;
                animatorArr[i2] = (Animator) it.next();
                i2 = i3;
            }
            if (i == 0) {
                animatorSet2.playTogether(animatorArr);
            } else {
                animatorSet2.playSequentially(animatorArr);
            }
        }
        return animator;
    }

    private static PropertyValuesHolder[] loadValues(Context context, Resources resources, Theme theme, XmlPullParser xmlPullParser, AttributeSet attributeSet) throws XmlPullParserException, IOException {
        XmlPullParser xmlPullParser2 = xmlPullParser;
        PropertyValuesHolder[] propertyValuesHolderArr = null;
        ArrayList arrayList = null;
        while (true) {
            int eventType = xmlPullParser.getEventType();
            int i = 0;
            if (eventType == 3 || eventType == 1) {
                if (arrayList != null) {
                    eventType = arrayList.size();
                    propertyValuesHolderArr = new PropertyValuesHolder[eventType];
                    while (i < eventType) {
                        propertyValuesHolderArr[i] = (PropertyValuesHolder) arrayList.get(i);
                        i++;
                    }
                }
            } else if (eventType != 2) {
                xmlPullParser.next();
            } else {
                if (xmlPullParser.getName().equals("propertyValuesHolder")) {
                    TypedArray obtainAttributes = TypedArrayUtils.obtainAttributes(resources, theme, attributeSet, AndroidResources.STYLEABLE_PROPERTY_VALUES_HOLDER);
                    String namedString = TypedArrayUtils.getNamedString(obtainAttributes, xmlPullParser2, "propertyName", 3);
                    int namedInt = TypedArrayUtils.getNamedInt(obtainAttributes, xmlPullParser2, "valueType", 2, 4);
                    int i2 = namedInt;
                    Object loadPvh = loadPvh(context, resources, theme, xmlPullParser, namedString, namedInt);
                    if (loadPvh == null) {
                        loadPvh = getPVH(obtainAttributes, i2, 0, 1, namedString);
                    }
                    if (loadPvh != null) {
                        if (arrayList == null) {
                            arrayList = new ArrayList();
                        }
                        arrayList.add(loadPvh);
                    }
                    obtainAttributes.recycle();
                } else {
                    Resources resources2 = resources;
                    Theme theme2 = theme;
                    AttributeSet attributeSet2 = attributeSet;
                }
                xmlPullParser.next();
            }
        }
        if (arrayList != null) {
            eventType = arrayList.size();
            propertyValuesHolderArr = new PropertyValuesHolder[eventType];
            while (i < eventType) {
                propertyValuesHolderArr[i] = (PropertyValuesHolder) arrayList.get(i);
                i++;
            }
        }
        return propertyValuesHolderArr;
    }

    private static int inferValueTypeOfKeyframe(Resources resources, Theme theme, AttributeSet attributeSet, XmlPullParser xmlPullParser) {
        resources = TypedArrayUtils.obtainAttributes(resources, theme, attributeSet, AndroidResources.STYLEABLE_KEYFRAME);
        attributeSet = null;
        theme = TypedArrayUtils.peekNamedValue(resources, xmlPullParser, "value", 0);
        if (!((theme != null ? true : null) == null || isColorType(theme.type) == null)) {
            attributeSet = 3;
        }
        resources.recycle();
        return attributeSet;
    }

    private static int inferValueTypeFromValues(TypedArray typedArray, int i, int i2) {
        i = typedArray.peekValue(i);
        Object obj = 1;
        Object obj2 = i != 0 ? 1 : null;
        i = obj2 != null ? i.type : 0;
        typedArray = typedArray.peekValue(i2);
        if (typedArray == null) {
            obj = null;
        }
        typedArray = obj != null ? typedArray.type : null;
        if ((obj2 == null || isColorType(i) == 0) && (obj == null || isColorType(typedArray) == null)) {
            return 0;
        }
        return 3;
    }

    private static void dumpKeyframes(Object[] objArr, String str) {
        if (objArr != null) {
            if (objArr.length != 0) {
                Log.d(TAG, str);
                str = objArr.length;
                for (int i = 0; i < str; i++) {
                    Keyframe keyframe = (Keyframe) objArr[i];
                    String str2 = TAG;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Keyframe ");
                    stringBuilder.append(i);
                    stringBuilder.append(": fraction ");
                    stringBuilder.append(keyframe.getFraction() < 0.0f ? "null" : Float.valueOf(keyframe.getFraction()));
                    stringBuilder.append(", ");
                    stringBuilder.append(", value : ");
                    stringBuilder.append(keyframe.hasValue() ? keyframe.getValue() : "null");
                    Log.d(str2, stringBuilder.toString());
                }
            }
        }
    }

    private static PropertyValuesHolder loadPvh(Context context, Resources resources, Theme theme, XmlPullParser xmlPullParser, String str, int i) throws XmlPullParserException, IOException {
        Resources size;
        Keyframe keyframe;
        int i2;
        PropertyValuesHolder propertyValuesHolder = null;
        int i3 = i;
        i = 0;
        while (true) {
            Keyframe keyframe2;
            float fraction;
            int next = xmlPullParser.next();
            if (next == 3 || next == 1) {
                if (i != 0) {
                    size = i.size();
                    if (size > null) {
                        resources = null;
                        keyframe2 = (Keyframe) i.get(0);
                        keyframe = (Keyframe) i.get(size - 1);
                        fraction = keyframe.getFraction();
                        if (fraction < 1.0f) {
                            if (fraction >= 0.0f) {
                                keyframe.setFraction(1.0f);
                            } else {
                                i.add(i.size(), createNewKeyframe(keyframe, 1.0f));
                                size++;
                            }
                        }
                        xmlPullParser = keyframe2.getFraction();
                        if (xmlPullParser != null) {
                            if (xmlPullParser >= null) {
                                keyframe2.setFraction(0.0f);
                            } else {
                                i.add(0, createNewKeyframe(keyframe2, 0.0f));
                                size++;
                            }
                        }
                        theme = new Keyframe[size];
                        i.toArray(theme);
                        while (resources < size) {
                            xmlPullParser = theme[resources];
                            if (xmlPullParser.getFraction() >= 0) {
                                if (resources != null) {
                                    xmlPullParser.setFraction(0.0f);
                                } else {
                                    i = size - 1;
                                    if (resources != i) {
                                        xmlPullParser.setFraction(1.0f);
                                    } else {
                                        i2 = resources;
                                        for (xmlPullParser = resources + 1; xmlPullParser < i; xmlPullParser++) {
                                            if (theme[xmlPullParser].getFraction() >= 0.0f) {
                                                break;
                                            }
                                            i2 = xmlPullParser;
                                        }
                                        distributeKeyframes(theme, theme[i2 + 1].getFraction() - theme[resources - 1].getFraction(), resources, i2);
                                    }
                                }
                            }
                            resources++;
                        }
                        propertyValuesHolder = PropertyValuesHolder.ofKeyframe(str, theme);
                        if (i3 == 3) {
                            propertyValuesHolder.setEvaluator(ArgbEvaluator.getInstance());
                        }
                    }
                }
            } else if (xmlPullParser.getName().equals("keyframe")) {
                if (i3 == 4) {
                    i3 = inferValueTypeOfKeyframe(resources, theme, Xml.asAttributeSet(xmlPullParser), xmlPullParser);
                }
                Keyframe loadKeyframe = loadKeyframe(context, resources, theme, Xml.asAttributeSet(xmlPullParser), i3, xmlPullParser);
                if (loadKeyframe != null) {
                    if (i == 0) {
                        i = new ArrayList();
                    }
                    i.add(loadKeyframe);
                }
                xmlPullParser.next();
            }
        }
        if (i != 0) {
            size = i.size();
            if (size > null) {
                resources = null;
                keyframe2 = (Keyframe) i.get(0);
                keyframe = (Keyframe) i.get(size - 1);
                fraction = keyframe.getFraction();
                if (fraction < 1.0f) {
                    if (fraction >= 0.0f) {
                        i.add(i.size(), createNewKeyframe(keyframe, 1.0f));
                        size++;
                    } else {
                        keyframe.setFraction(1.0f);
                    }
                }
                xmlPullParser = keyframe2.getFraction();
                if (xmlPullParser != null) {
                    if (xmlPullParser >= null) {
                        i.add(0, createNewKeyframe(keyframe2, 0.0f));
                        size++;
                    } else {
                        keyframe2.setFraction(0.0f);
                    }
                }
                theme = new Keyframe[size];
                i.toArray(theme);
                while (resources < size) {
                    xmlPullParser = theme[resources];
                    if (xmlPullParser.getFraction() >= 0) {
                        if (resources != null) {
                            i = size - 1;
                            if (resources != i) {
                                i2 = resources;
                                for (xmlPullParser = resources + 1; xmlPullParser < i; xmlPullParser++) {
                                    if (theme[xmlPullParser].getFraction() >= 0.0f) {
                                        break;
                                    }
                                    i2 = xmlPullParser;
                                }
                                distributeKeyframes(theme, theme[i2 + 1].getFraction() - theme[resources - 1].getFraction(), resources, i2);
                            } else {
                                xmlPullParser.setFraction(1.0f);
                            }
                        } else {
                            xmlPullParser.setFraction(0.0f);
                        }
                    }
                    resources++;
                }
                propertyValuesHolder = PropertyValuesHolder.ofKeyframe(str, theme);
                if (i3 == 3) {
                    propertyValuesHolder.setEvaluator(ArgbEvaluator.getInstance());
                }
            }
        }
        return propertyValuesHolder;
    }

    private static Keyframe createNewKeyframe(Keyframe keyframe, float f) {
        if (keyframe.getType() == Float.TYPE) {
            return Keyframe.ofFloat(f);
        }
        if (keyframe.getType() == Integer.TYPE) {
            return Keyframe.ofInt(f);
        }
        return Keyframe.ofObject(f);
    }

    private static void distributeKeyframes(Keyframe[] keyframeArr, float f, int i, int i2) {
        f /= (float) ((i2 - i) + 2);
        while (i <= i2) {
            keyframeArr[i].setFraction(keyframeArr[i - 1].getFraction() + f);
            i++;
        }
    }

    private static Keyframe loadKeyframe(Context context, Resources resources, Theme theme, AttributeSet attributeSet, int i, XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        resources = TypedArrayUtils.obtainAttributes(resources, theme, attributeSet, AndroidResources.STYLEABLE_KEYFRAME);
        theme = TypedArrayUtils.getNamedFloat(resources, xmlPullParser, "fraction", 3, -1.0f);
        TypedValue peekNamedValue = TypedArrayUtils.peekNamedValue(resources, xmlPullParser, "value", 0);
        Object obj = peekNamedValue != null ? 1 : null;
        if (i == 4) {
            i = (obj == null || isColorType(peekNamedValue.type) == 0) ? 0 : 3;
        }
        if (obj != null) {
            if (i != 3) {
                switch (i) {
                    case 0:
                        theme = Keyframe.ofFloat(theme, TypedArrayUtils.getNamedFloat(resources, xmlPullParser, "value", 0, 0));
                        break;
                    case 1:
                        break;
                    default:
                        theme = null;
                        break;
                }
            }
            theme = Keyframe.ofInt(theme, TypedArrayUtils.getNamedInt(resources, xmlPullParser, "value", 0, 0));
        } else if (i == 0) {
            theme = Keyframe.ofFloat(theme);
        } else {
            theme = Keyframe.ofInt(theme);
        }
        attributeSet = TypedArrayUtils.getNamedResourceId(resources, xmlPullParser, "interpolator", 1, 0);
        if (attributeSet > null) {
            theme.setInterpolator(AnimationUtilsCompat.loadInterpolator(context, attributeSet));
        }
        resources.recycle();
        return theme;
    }

    private static ObjectAnimator loadObjectAnimator(Context context, Resources resources, Theme theme, AttributeSet attributeSet, float f, XmlPullParser xmlPullParser) throws NotFoundException {
        ValueAnimator objectAnimator = new ObjectAnimator();
        loadAnimator(context, resources, theme, attributeSet, objectAnimator, f, xmlPullParser);
        return objectAnimator;
    }

    private static ValueAnimator loadAnimator(Context context, Resources resources, Theme theme, AttributeSet attributeSet, ValueAnimator valueAnimator, float f, XmlPullParser xmlPullParser) throws NotFoundException {
        TypedArray obtainAttributes = TypedArrayUtils.obtainAttributes(resources, theme, attributeSet, AndroidResources.STYLEABLE_ANIMATOR);
        resources = TypedArrayUtils.obtainAttributes(resources, theme, attributeSet, AndroidResources.STYLEABLE_PROPERTY_ANIMATOR);
        if (valueAnimator == null) {
            valueAnimator = new ValueAnimator();
        }
        parseAnimatorFromTypeArray(valueAnimator, obtainAttributes, resources, f, xmlPullParser);
        theme = TypedArrayUtils.getNamedResourceId(obtainAttributes, xmlPullParser, "interpolator", 0, 0);
        if (theme > null) {
            valueAnimator.setInterpolator(AnimationUtilsCompat.loadInterpolator(context, theme));
        }
        obtainAttributes.recycle();
        if (resources != null) {
            resources.recycle();
        }
        return valueAnimator;
    }

    private AnimatorInflaterCompat() {
    }
}
