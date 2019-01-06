package android.support.v4.view;

import android.content.Context;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.LayoutInflater.Factory;
import android.view.LayoutInflater.Factory2;
import android.view.View;
import java.lang.reflect.Field;

public final class LayoutInflaterCompat {
    private static final String TAG = "LayoutInflaterCompatHC";
    private static boolean sCheckedField;
    private static Field sLayoutInflaterFactory2Field;

    static class Factory2Wrapper implements Factory2 {
        final LayoutInflaterFactory mDelegateFactory;

        Factory2Wrapper(LayoutInflaterFactory layoutInflaterFactory) {
            this.mDelegateFactory = layoutInflaterFactory;
        }

        public View onCreateView(String str, Context context, AttributeSet attributeSet) {
            return this.mDelegateFactory.onCreateView(null, str, context, attributeSet);
        }

        public View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
            return this.mDelegateFactory.onCreateView(view, str, context, attributeSet);
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(getClass().getName());
            stringBuilder.append("{");
            stringBuilder.append(this.mDelegateFactory);
            stringBuilder.append("}");
            return stringBuilder.toString();
        }
    }

    private static void forceSetFactory2(LayoutInflater layoutInflater, Factory2 factory2) {
        if (!sCheckedField) {
            try {
                sLayoutInflaterFactory2Field = LayoutInflater.class.getDeclaredField("mFactory2");
                sLayoutInflaterFactory2Field.setAccessible(true);
            } catch (Throwable e) {
                String str = TAG;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("forceSetFactory2 Could not find field 'mFactory2' on class ");
                stringBuilder.append(LayoutInflater.class.getName());
                stringBuilder.append("; inflation may have unexpected results.");
                Log.e(str, stringBuilder.toString(), e);
            }
            sCheckedField = true;
        }
        if (sLayoutInflaterFactory2Field != null) {
            try {
                sLayoutInflaterFactory2Field.set(layoutInflater, factory2);
            } catch (Factory2 factory22) {
                String str2 = TAG;
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("forceSetFactory2 could not set the Factory2 on LayoutInflater ");
                stringBuilder2.append(layoutInflater);
                stringBuilder2.append("; inflation may have unexpected results.");
                Log.e(str2, stringBuilder2.toString(), factory22);
            }
        }
    }

    private LayoutInflaterCompat() {
    }

    @Deprecated
    public static void setFactory(@NonNull LayoutInflater layoutInflater, @NonNull LayoutInflaterFactory layoutInflaterFactory) {
        Factory2 factory2 = null;
        if (VERSION.SDK_INT >= 21) {
            if (layoutInflaterFactory != null) {
                factory2 = new Factory2Wrapper(layoutInflaterFactory);
            }
            layoutInflater.setFactory2(factory2);
            return;
        }
        if (layoutInflaterFactory != null) {
            factory2 = new Factory2Wrapper(layoutInflaterFactory);
        }
        layoutInflater.setFactory2(factory2);
        layoutInflaterFactory = layoutInflater.getFactory();
        if (layoutInflaterFactory instanceof Factory2) {
            forceSetFactory2(layoutInflater, (Factory2) layoutInflaterFactory);
        } else {
            forceSetFactory2(layoutInflater, factory2);
        }
    }

    public static void setFactory2(@NonNull LayoutInflater layoutInflater, @NonNull Factory2 factory2) {
        layoutInflater.setFactory2(factory2);
        if (VERSION.SDK_INT < 21) {
            Factory factory = layoutInflater.getFactory();
            if (factory instanceof Factory2) {
                forceSetFactory2(layoutInflater, (Factory2) factory);
            } else {
                forceSetFactory2(layoutInflater, factory2);
            }
        }
    }

    @Deprecated
    public static LayoutInflaterFactory getFactory(LayoutInflater layoutInflater) {
        layoutInflater = layoutInflater.getFactory();
        return layoutInflater instanceof Factory2Wrapper ? ((Factory2Wrapper) layoutInflater).mDelegateFactory : null;
    }
}
