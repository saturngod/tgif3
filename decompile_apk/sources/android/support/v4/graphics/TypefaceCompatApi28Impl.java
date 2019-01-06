package android.support.v4.graphics;

import android.graphics.Typeface;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import java.lang.reflect.Array;
import java.lang.reflect.Method;

@RequiresApi(28)
@RestrictTo({Scope.LIBRARY_GROUP})
public class TypefaceCompatApi28Impl extends TypefaceCompatApi26Impl {
    private static final String CREATE_FROM_FAMILIES_WITH_DEFAULT_METHOD = "createFromFamiliesWithDefault";
    private static final String DEFAULT_FAMILY = "sans-serif";
    private static final int RESOLVE_BY_FONT_TABLE = -1;
    private static final String TAG = "TypefaceCompatApi28Impl";

    protected Typeface createFromFamiliesWithDefault(Object obj) {
        try {
            Array.set(Array.newInstance(this.mFontFamily, 1), 0, obj);
            return (Typeface) this.mCreateFromFamiliesWithDefault.invoke(null, new Object[]{r0, DEFAULT_FAMILY, Integer.valueOf(-1), Integer.valueOf(-1)});
        } catch (Object obj2) {
            throw new RuntimeException(obj2);
        }
    }

    protected Method obtainCreateFromFamiliesWithDefaultMethod(Class cls) throws NoSuchMethodException {
        cls = Array.newInstance(cls, 1);
        cls = Typeface.class.getDeclaredMethod(CREATE_FROM_FAMILIES_WITH_DEFAULT_METHOD, new Class[]{cls.getClass(), String.class, Integer.TYPE, Integer.TYPE});
        cls.setAccessible(true);
        return cls;
    }
}
