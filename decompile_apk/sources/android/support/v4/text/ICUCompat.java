package android.support.v4.text;

import android.os.Build.VERSION;
import android.support.annotation.Nullable;
import android.util.Log;
import java.lang.reflect.Method;
import java.util.Locale;

public final class ICUCompat {
    private static final String TAG = "ICUCompat";
    private static Method sAddLikelySubtagsMethod;
    private static Method sGetScriptMethod;

    static {
        if (VERSION.SDK_INT >= 21) {
            try {
                sAddLikelySubtagsMethod = Class.forName("libcore.icu.ICU").getMethod("addLikelySubtags", new Class[]{Locale.class});
                return;
            } catch (Throwable e) {
                throw new IllegalStateException(e);
            }
        }
        try {
            Class cls = Class.forName("libcore.icu.ICU");
            if (cls != null) {
                sGetScriptMethod = cls.getMethod("getScript", new Class[]{String.class});
                sAddLikelySubtagsMethod = cls.getMethod("addLikelySubtags", new Class[]{String.class});
            }
        } catch (Throwable e2) {
            sGetScriptMethod = null;
            sAddLikelySubtagsMethod = null;
            Log.w(TAG, e2);
        }
    }

    @Nullable
    public static String maximizeAndGetScript(Locale locale) {
        if (VERSION.SDK_INT >= 21) {
            try {
                return ((Locale) sAddLikelySubtagsMethod.invoke(null, new Object[]{locale})).getScript();
            } catch (Throwable e) {
                Log.w(TAG, e);
                return locale.getScript();
            } catch (Throwable e2) {
                Log.w(TAG, e2);
                return locale.getScript();
            }
        }
        locale = addLikelySubtags(locale);
        if (locale != null) {
            return getScript(locale);
        }
        return null;
    }

    private static String getScript(String str) {
        try {
            if (sGetScriptMethod != null) {
                return (String) sGetScriptMethod.invoke(null, new Object[]{str});
            }
        } catch (String str2) {
            Log.w(TAG, str2);
        } catch (String str22) {
            Log.w(TAG, str22);
        }
        return null;
    }

    private static String addLikelySubtags(Locale locale) {
        locale = locale.toString();
        try {
            if (sAddLikelySubtagsMethod != null) {
                return (String) sAddLikelySubtagsMethod.invoke(null, new Object[]{locale});
            }
        } catch (Throwable e) {
            Log.w(TAG, e);
        } catch (Throwable e2) {
            Log.w(TAG, e2);
        }
        return locale;
    }

    private ICUCompat() {
    }
}
