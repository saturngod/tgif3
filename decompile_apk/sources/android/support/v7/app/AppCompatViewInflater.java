package android.support.v7.app;

import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.C0192R;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatCheckedTextView;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatMultiAutoCompleteTextView;
import android.support.v7.widget.AppCompatRadioButton;
import android.support.v7.widget.AppCompatRatingBar;
import android.support.v7.widget.AppCompatSeekBar;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.TintContextWrapper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Map;

public class AppCompatViewInflater {
    private static final String LOG_TAG = "AppCompatViewInflater";
    private static final String[] sClassPrefixList = new String[]{"android.widget.", "android.view.", "android.webkit."};
    private static final Map<String, Constructor<? extends View>> sConstructorMap = new ArrayMap();
    private static final Class<?>[] sConstructorSignature = new Class[]{Context.class, AttributeSet.class};
    private static final int[] sOnClickAttrs = new int[]{16843375};
    private final Object[] mConstructorArgs = new Object[2];

    private static class DeclaredOnClickListener implements OnClickListener {
        private final View mHostView;
        private final String mMethodName;
        private Context mResolvedContext;
        private Method mResolvedMethod;

        public DeclaredOnClickListener(@NonNull View view, @NonNull String str) {
            this.mHostView = view;
            this.mMethodName = str;
        }

        public void onClick(@NonNull View view) {
            if (this.mResolvedMethod == null) {
                resolveMethod(this.mHostView.getContext(), this.mMethodName);
            }
            try {
                this.mResolvedMethod.invoke(this.mResolvedContext, new Object[]{view});
            } catch (View view2) {
                throw new IllegalStateException("Could not execute non-public method for android:onClick", view2);
            } catch (View view22) {
                throw new IllegalStateException("Could not execute method for android:onClick", view22);
            }
        }

        @android.support.annotation.NonNull
        private void resolveMethod(@android.support.annotation.Nullable android.content.Context r5, @android.support.annotation.NonNull java.lang.String r6) {
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
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1603198149.run(Unknown Source)
*/
            /*
            r4 = this;
        L_0x0000:
            if (r5 == 0) goto L_0x002e;
        L_0x0002:
            r6 = r5.isRestricted();	 Catch:{ NoSuchMethodException -> 0x0021 }
            if (r6 != 0) goto L_0x0021;	 Catch:{ NoSuchMethodException -> 0x0021 }
        L_0x0008:
            r6 = r5.getClass();	 Catch:{ NoSuchMethodException -> 0x0021 }
            r0 = r4.mMethodName;	 Catch:{ NoSuchMethodException -> 0x0021 }
            r1 = 1;	 Catch:{ NoSuchMethodException -> 0x0021 }
            r1 = new java.lang.Class[r1];	 Catch:{ NoSuchMethodException -> 0x0021 }
            r2 = 0;	 Catch:{ NoSuchMethodException -> 0x0021 }
            r3 = android.view.View.class;	 Catch:{ NoSuchMethodException -> 0x0021 }
            r1[r2] = r3;	 Catch:{ NoSuchMethodException -> 0x0021 }
            r6 = r6.getMethod(r0, r1);	 Catch:{ NoSuchMethodException -> 0x0021 }
            if (r6 == 0) goto L_0x0021;	 Catch:{ NoSuchMethodException -> 0x0021 }
        L_0x001c:
            r4.mResolvedMethod = r6;	 Catch:{ NoSuchMethodException -> 0x0021 }
            r4.mResolvedContext = r5;	 Catch:{ NoSuchMethodException -> 0x0021 }
            return;
        L_0x0021:
            r6 = r5 instanceof android.content.ContextWrapper;
            if (r6 == 0) goto L_0x002c;
        L_0x0025:
            r5 = (android.content.ContextWrapper) r5;
            r5 = r5.getBaseContext();
            goto L_0x0000;
        L_0x002c:
            r5 = 0;
            goto L_0x0000;
        L_0x002e:
            r5 = r4.mHostView;
            r5 = r5.getId();
            r6 = -1;
            if (r5 != r6) goto L_0x003a;
        L_0x0037:
            r5 = "";
            goto L_0x005e;
        L_0x003a:
            r6 = new java.lang.StringBuilder;
            r6.<init>();
            r0 = " with id '";
            r6.append(r0);
            r0 = r4.mHostView;
            r0 = r0.getContext();
            r0 = r0.getResources();
            r5 = r0.getResourceEntryName(r5);
            r6.append(r5);
            r5 = "'";
            r6.append(r5);
            r5 = r6.toString();
        L_0x005e:
            r6 = new java.lang.IllegalStateException;
            r0 = new java.lang.StringBuilder;
            r0.<init>();
            r1 = "Could not find method ";
            r0.append(r1);
            r1 = r4.mMethodName;
            r0.append(r1);
            r1 = "(View) in a parent or ancestor Context for android:onClick ";
            r0.append(r1);
            r1 = "attribute defined on view ";
            r0.append(r1);
            r1 = r4.mHostView;
            r1 = r1.getClass();
            r0.append(r1);
            r0.append(r5);
            r5 = r0.toString();
            r6.<init>(r5);
            throw r6;
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.v7.app.AppCompatViewInflater.DeclaredOnClickListener.resolveMethod(android.content.Context, java.lang.String):void");
        }
    }

    @Nullable
    protected View createView(Context context, String str, AttributeSet attributeSet) {
        return null;
    }

    final View createView(View view, String str, @NonNull Context context, @NonNull AttributeSet attributeSet, boolean z, boolean z2, boolean z3, boolean z4) {
        Context context2 = (!z || view == null) ? context : view.getContext();
        if (z2 || z3) {
            context2 = themifyContext(context2, attributeSet, z2, z3);
        }
        if (z4) {
            context2 = TintContextWrapper.wrap(context2);
        }
        z = true;
        switch (str.hashCode()) {
            case true:
                if (str.equals("RatingBar")) {
                    z = true;
                    break;
                }
                break;
            case true:
                if (str.equals("CheckedTextView")) {
                    z = true;
                    break;
                }
                break;
            case true:
                if (str.equals("MultiAutoCompleteTextView")) {
                    z = true;
                    break;
                }
                break;
            case true:
                if (str.equals("TextView")) {
                    z = false;
                    break;
                }
                break;
            case true:
                if (str.equals("ImageButton")) {
                    z = true;
                    break;
                }
                break;
            case true:
                if (str.equals("SeekBar")) {
                    z = true;
                    break;
                }
                break;
            case true:
                if (str.equals("Spinner")) {
                    z = true;
                    break;
                }
                break;
            case true:
                if (str.equals("RadioButton")) {
                    z = true;
                    break;
                }
                break;
            case true:
                if (str.equals("ImageView")) {
                    z = true;
                    break;
                }
                break;
            case true:
                if (str.equals("AutoCompleteTextView")) {
                    z = true;
                    break;
                }
                break;
            case true:
                if (str.equals("CheckBox")) {
                    z = true;
                    break;
                }
                break;
            case true:
                if (str.equals("EditText")) {
                    z = true;
                    break;
                }
                break;
            case true:
                if (str.equals("Button")) {
                    z = true;
                    break;
                }
                break;
            default:
                break;
        }
        switch (z) {
            case false:
                z = createTextView(context2, attributeSet);
                verifyNotNull(z, str);
                break;
            case true:
                z = createImageView(context2, attributeSet);
                verifyNotNull(z, str);
                break;
            case true:
                z = createButton(context2, attributeSet);
                verifyNotNull(z, str);
                break;
            case true:
                z = createEditText(context2, attributeSet);
                verifyNotNull(z, str);
                break;
            case true:
                z = createSpinner(context2, attributeSet);
                verifyNotNull(z, str);
                break;
            case true:
                z = createImageButton(context2, attributeSet);
                verifyNotNull(z, str);
                break;
            case true:
                z = createCheckBox(context2, attributeSet);
                verifyNotNull(z, str);
                break;
            case true:
                z = createRadioButton(context2, attributeSet);
                verifyNotNull(z, str);
                break;
            case true:
                z = createCheckedTextView(context2, attributeSet);
                verifyNotNull(z, str);
                break;
            case true:
                z = createAutoCompleteTextView(context2, attributeSet);
                verifyNotNull(z, str);
                break;
            case true:
                z = createMultiAutoCompleteTextView(context2, attributeSet);
                verifyNotNull(z, str);
                break;
            case true:
                z = createRatingBar(context2, attributeSet);
                verifyNotNull(z, str);
                break;
            case true:
                z = createSeekBar(context2, attributeSet);
                verifyNotNull(z, str);
                break;
            default:
                z = createView(context2, str, attributeSet);
                break;
        }
        if (!(z || context == context2)) {
            z = createViewFromTag(context2, str, attributeSet);
        }
        if (z) {
            checkOnClickListener(z, attributeSet);
        }
        return z;
    }

    @NonNull
    protected AppCompatTextView createTextView(Context context, AttributeSet attributeSet) {
        return new AppCompatTextView(context, attributeSet);
    }

    @NonNull
    protected AppCompatImageView createImageView(Context context, AttributeSet attributeSet) {
        return new AppCompatImageView(context, attributeSet);
    }

    @NonNull
    protected AppCompatButton createButton(Context context, AttributeSet attributeSet) {
        return new AppCompatButton(context, attributeSet);
    }

    @NonNull
    protected AppCompatEditText createEditText(Context context, AttributeSet attributeSet) {
        return new AppCompatEditText(context, attributeSet);
    }

    @NonNull
    protected AppCompatSpinner createSpinner(Context context, AttributeSet attributeSet) {
        return new AppCompatSpinner(context, attributeSet);
    }

    @NonNull
    protected AppCompatImageButton createImageButton(Context context, AttributeSet attributeSet) {
        return new AppCompatImageButton(context, attributeSet);
    }

    @NonNull
    protected AppCompatCheckBox createCheckBox(Context context, AttributeSet attributeSet) {
        return new AppCompatCheckBox(context, attributeSet);
    }

    @NonNull
    protected AppCompatRadioButton createRadioButton(Context context, AttributeSet attributeSet) {
        return new AppCompatRadioButton(context, attributeSet);
    }

    @NonNull
    protected AppCompatCheckedTextView createCheckedTextView(Context context, AttributeSet attributeSet) {
        return new AppCompatCheckedTextView(context, attributeSet);
    }

    @NonNull
    protected AppCompatAutoCompleteTextView createAutoCompleteTextView(Context context, AttributeSet attributeSet) {
        return new AppCompatAutoCompleteTextView(context, attributeSet);
    }

    @NonNull
    protected AppCompatMultiAutoCompleteTextView createMultiAutoCompleteTextView(Context context, AttributeSet attributeSet) {
        return new AppCompatMultiAutoCompleteTextView(context, attributeSet);
    }

    @NonNull
    protected AppCompatRatingBar createRatingBar(Context context, AttributeSet attributeSet) {
        return new AppCompatRatingBar(context, attributeSet);
    }

    @NonNull
    protected AppCompatSeekBar createSeekBar(Context context, AttributeSet attributeSet) {
        return new AppCompatSeekBar(context, attributeSet);
    }

    private void verifyNotNull(View view, String str) {
        if (view == null) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(getClass().getName());
            stringBuilder.append(" asked to inflate view for <");
            stringBuilder.append(str);
            stringBuilder.append(">, but returned null");
            throw new IllegalStateException(stringBuilder.toString());
        }
    }

    private android.view.View createViewFromTag(android.content.Context r5, java.lang.String r6, android.util.AttributeSet r7) {
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
        r0 = "view";
        r0 = r6.equals(r0);
        r1 = 0;
        if (r0 == 0) goto L_0x000f;
    L_0x0009:
        r6 = "class";
        r6 = r7.getAttributeValue(r1, r6);
    L_0x000f:
        r0 = 1;
        r2 = 0;
        r3 = r4.mConstructorArgs;	 Catch:{ Exception -> 0x005e, all -> 0x0054 }
        r3[r2] = r5;	 Catch:{ Exception -> 0x005e, all -> 0x0054 }
        r3 = r4.mConstructorArgs;	 Catch:{ Exception -> 0x005e, all -> 0x0054 }
        r3[r0] = r7;	 Catch:{ Exception -> 0x005e, all -> 0x0054 }
        r7 = -1;	 Catch:{ Exception -> 0x005e, all -> 0x0054 }
        r3 = 46;	 Catch:{ Exception -> 0x005e, all -> 0x0054 }
        r3 = r6.indexOf(r3);	 Catch:{ Exception -> 0x005e, all -> 0x0054 }
        if (r7 != r3) goto L_0x0047;	 Catch:{ Exception -> 0x005e, all -> 0x0054 }
    L_0x0022:
        r7 = 0;	 Catch:{ Exception -> 0x005e, all -> 0x0054 }
    L_0x0023:
        r3 = sClassPrefixList;	 Catch:{ Exception -> 0x005e, all -> 0x0054 }
        r3 = r3.length;	 Catch:{ Exception -> 0x005e, all -> 0x0054 }
        if (r7 >= r3) goto L_0x003e;	 Catch:{ Exception -> 0x005e, all -> 0x0054 }
    L_0x0028:
        r3 = sClassPrefixList;	 Catch:{ Exception -> 0x005e, all -> 0x0054 }
        r3 = r3[r7];	 Catch:{ Exception -> 0x005e, all -> 0x0054 }
        r3 = r4.createViewByPrefix(r5, r6, r3);	 Catch:{ Exception -> 0x005e, all -> 0x0054 }
        if (r3 == 0) goto L_0x003b;
    L_0x0032:
        r5 = r4.mConstructorArgs;
        r5[r2] = r1;
        r5 = r4.mConstructorArgs;
        r5[r0] = r1;
        return r3;
    L_0x003b:
        r7 = r7 + 1;
        goto L_0x0023;
    L_0x003e:
        r5 = r4.mConstructorArgs;
        r5[r2] = r1;
        r5 = r4.mConstructorArgs;
        r5[r0] = r1;
        return r1;
    L_0x0047:
        r5 = r4.createViewByPrefix(r5, r6, r1);	 Catch:{ Exception -> 0x005e, all -> 0x0054 }
        r6 = r4.mConstructorArgs;
        r6[r2] = r1;
        r6 = r4.mConstructorArgs;
        r6[r0] = r1;
        return r5;
    L_0x0054:
        r5 = move-exception;
        r6 = r4.mConstructorArgs;
        r6[r2] = r1;
        r6 = r4.mConstructorArgs;
        r6[r0] = r1;
        throw r5;
    L_0x005e:
        r5 = r4.mConstructorArgs;
        r5[r2] = r1;
        r5 = r4.mConstructorArgs;
        r5[r0] = r1;
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.app.AppCompatViewInflater.createViewFromTag(android.content.Context, java.lang.String, android.util.AttributeSet):android.view.View");
    }

    private void checkOnClickListener(View view, AttributeSet attributeSet) {
        Context context = view.getContext();
        if (context instanceof ContextWrapper) {
            if (VERSION.SDK_INT < 15 || ViewCompat.hasOnClickListeners(view)) {
                attributeSet = context.obtainStyledAttributes(attributeSet, sOnClickAttrs);
                String string = attributeSet.getString(0);
                if (string != null) {
                    view.setOnClickListener(new DeclaredOnClickListener(view, string));
                }
                attributeSet.recycle();
            }
        }
    }

    private android.view.View createViewByPrefix(android.content.Context r2, java.lang.String r3, java.lang.String r4) throws java.lang.ClassNotFoundException, android.view.InflateException {
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
        r1 = this;
        r0 = sConstructorMap;
        r0 = r0.get(r3);
        r0 = (java.lang.reflect.Constructor) r0;
        if (r0 != 0) goto L_0x0036;
    L_0x000a:
        r2 = r2.getClassLoader();	 Catch:{ Exception -> 0x0043 }
        if (r4 == 0) goto L_0x0020;	 Catch:{ Exception -> 0x0043 }
    L_0x0010:
        r0 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0043 }
        r0.<init>();	 Catch:{ Exception -> 0x0043 }
        r0.append(r4);	 Catch:{ Exception -> 0x0043 }
        r0.append(r3);	 Catch:{ Exception -> 0x0043 }
        r4 = r0.toString();	 Catch:{ Exception -> 0x0043 }
        goto L_0x0021;	 Catch:{ Exception -> 0x0043 }
    L_0x0020:
        r4 = r3;	 Catch:{ Exception -> 0x0043 }
    L_0x0021:
        r2 = r2.loadClass(r4);	 Catch:{ Exception -> 0x0043 }
        r4 = android.view.View.class;	 Catch:{ Exception -> 0x0043 }
        r2 = r2.asSubclass(r4);	 Catch:{ Exception -> 0x0043 }
        r4 = sConstructorSignature;	 Catch:{ Exception -> 0x0043 }
        r0 = r2.getConstructor(r4);	 Catch:{ Exception -> 0x0043 }
        r2 = sConstructorMap;	 Catch:{ Exception -> 0x0043 }
        r2.put(r3, r0);	 Catch:{ Exception -> 0x0043 }
    L_0x0036:
        r2 = 1;	 Catch:{ Exception -> 0x0043 }
        r0.setAccessible(r2);	 Catch:{ Exception -> 0x0043 }
        r2 = r1.mConstructorArgs;	 Catch:{ Exception -> 0x0043 }
        r2 = r0.newInstance(r2);	 Catch:{ Exception -> 0x0043 }
        r2 = (android.view.View) r2;	 Catch:{ Exception -> 0x0043 }
        return r2;
    L_0x0043:
        r2 = 0;
        return r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.app.AppCompatViewInflater.createViewByPrefix(android.content.Context, java.lang.String, java.lang.String):android.view.View");
    }

    private static Context themifyContext(Context context, AttributeSet attributeSet, boolean z, boolean z2) {
        attributeSet = context.obtainStyledAttributes(attributeSet, C0192R.styleable.View, 0, 0);
        int resourceId = z ? attributeSet.getResourceId(C0192R.styleable.View_android_theme, 0) : false;
        if (z2 && !resourceId != false) {
            resourceId = attributeSet.getResourceId(C0192R.styleable.View_theme, 0);
            if (resourceId == true) {
                Log.i(LOG_TAG, "app:theme is now deprecated. Please move to using android:theme instead.");
            }
        }
        attributeSet.recycle();
        if (resourceId == true) {
            return ((context instanceof ContextThemeWrapper) == null || ((ContextThemeWrapper) context).getThemeResId() != resourceId) ? new ContextThemeWrapper(context, resourceId) : context;
        } else {
            return context;
        }
    }
}
