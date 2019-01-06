package android.support.v4.text;

import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import java.util.Locale;
import kotlin.text.Typography;

public final class TextUtilsCompat {
    private static final String ARAB_SCRIPT_SUBTAG = "Arab";
    private static final String HEBR_SCRIPT_SUBTAG = "Hebr";
    private static final Locale ROOT = new Locale("", "");

    @NonNull
    public static String htmlEncode(@NonNull String str) {
        if (VERSION.SDK_INT >= 17) {
            return TextUtils.htmlEncode(str);
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (charAt == Typography.quote) {
                stringBuilder.append("&quot;");
            } else if (charAt == Typography.less) {
                stringBuilder.append("&lt;");
            } else if (charAt != Typography.greater) {
                switch (charAt) {
                    case '&':
                        stringBuilder.append("&amp;");
                        break;
                    case '\'':
                        stringBuilder.append("&#39;");
                        break;
                    default:
                        stringBuilder.append(charAt);
                        break;
                }
            } else {
                stringBuilder.append("&gt;");
            }
        }
        return stringBuilder.toString();
    }

    public static int getLayoutDirectionFromLocale(@Nullable Locale locale) {
        if (VERSION.SDK_INT >= 17) {
            return TextUtils.getLayoutDirectionFromLocale(locale);
        }
        if (!(locale == null || locale.equals(ROOT))) {
            String maximizeAndGetScript = ICUCompat.maximizeAndGetScript(locale);
            if (maximizeAndGetScript == null) {
                return getLayoutDirectionFromFirstChar(locale);
            }
            if (!(maximizeAndGetScript.equalsIgnoreCase(ARAB_SCRIPT_SUBTAG) == null && maximizeAndGetScript.equalsIgnoreCase(HEBR_SCRIPT_SUBTAG) == null)) {
                return 1;
            }
        }
        return null;
    }

    private static int getLayoutDirectionFromFirstChar(@NonNull Locale locale) {
        switch (Character.getDirectionality(locale.getDisplayName(locale).charAt(0))) {
            case 1:
            case 2:
                return 1;
            default:
                return 0;
        }
    }

    private TextUtilsCompat() {
    }
}
