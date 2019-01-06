package android.support.v4.accessibilityservice;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.pm.PackageManager;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public final class AccessibilityServiceInfoCompat {
    public static final int CAPABILITY_CAN_FILTER_KEY_EVENTS = 8;
    public static final int CAPABILITY_CAN_REQUEST_ENHANCED_WEB_ACCESSIBILITY = 4;
    public static final int CAPABILITY_CAN_REQUEST_TOUCH_EXPLORATION = 2;
    public static final int CAPABILITY_CAN_RETRIEVE_WINDOW_CONTENT = 1;
    public static final int FEEDBACK_ALL_MASK = -1;
    public static final int FEEDBACK_BRAILLE = 32;
    public static final int FLAG_INCLUDE_NOT_IMPORTANT_VIEWS = 2;
    public static final int FLAG_REPORT_VIEW_IDS = 16;
    public static final int FLAG_REQUEST_ENHANCED_WEB_ACCESSIBILITY = 8;
    public static final int FLAG_REQUEST_FILTER_KEY_EVENTS = 32;
    public static final int FLAG_REQUEST_TOUCH_EXPLORATION_MODE = 4;

    @NonNull
    public static String capabilityToString(int i) {
        if (i == 4) {
            return "CAPABILITY_CAN_REQUEST_ENHANCED_WEB_ACCESSIBILITY";
        }
        if (i == 8) {
            return "CAPABILITY_CAN_FILTER_KEY_EVENTS";
        }
        switch (i) {
            case 1:
                return "CAPABILITY_CAN_RETRIEVE_WINDOW_CONTENT";
            case 2:
                return "CAPABILITY_CAN_REQUEST_TOUCH_EXPLORATION";
            default:
                return "UNKNOWN";
        }
    }

    @Nullable
    public static String flagToString(int i) {
        if (i == 4) {
            return "FLAG_REQUEST_TOUCH_EXPLORATION_MODE";
        }
        if (i == 8) {
            return "FLAG_REQUEST_ENHANCED_WEB_ACCESSIBILITY";
        }
        if (i == 16) {
            return "FLAG_REPORT_VIEW_IDS";
        }
        if (i == 32) {
            return "FLAG_REQUEST_FILTER_KEY_EVENTS";
        }
        switch (i) {
            case 1:
                return "DEFAULT";
            case 2:
                return "FLAG_INCLUDE_NOT_IMPORTANT_VIEWS";
            default:
                return null;
        }
    }

    private AccessibilityServiceInfoCompat() {
    }

    @Nullable
    public static String loadDescription(@NonNull AccessibilityServiceInfo accessibilityServiceInfo, @NonNull PackageManager packageManager) {
        if (VERSION.SDK_INT >= 16) {
            return accessibilityServiceInfo.loadDescription(packageManager);
        }
        return accessibilityServiceInfo.getDescription();
    }

    @NonNull
    public static String feedbackTypeToString(int i) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        while (i > 0) {
            int numberOfTrailingZeros = 1 << Integer.numberOfTrailingZeros(i);
            i &= ~numberOfTrailingZeros;
            if (stringBuilder.length() > 1) {
                stringBuilder.append(", ");
            }
            if (numberOfTrailingZeros == 4) {
                stringBuilder.append("FEEDBACK_AUDIBLE");
            } else if (numberOfTrailingZeros == 8) {
                stringBuilder.append("FEEDBACK_VISUAL");
            } else if (numberOfTrailingZeros != 16) {
                switch (numberOfTrailingZeros) {
                    case 1:
                        stringBuilder.append("FEEDBACK_SPOKEN");
                        break;
                    case 2:
                        stringBuilder.append("FEEDBACK_HAPTIC");
                        break;
                    default:
                        break;
                }
            } else {
                stringBuilder.append("FEEDBACK_GENERIC");
            }
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    public static int getCapabilities(@NonNull AccessibilityServiceInfo accessibilityServiceInfo) {
        if (VERSION.SDK_INT >= 18) {
            return accessibilityServiceInfo.getCapabilities();
        }
        return accessibilityServiceInfo.getCanRetrieveWindowContent() != null ? 1 : null;
    }
}
