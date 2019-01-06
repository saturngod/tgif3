package android.support.v13.view.inputmethod;

import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.inputmethod.EditorInfo;

public final class EditorInfoCompat {
    private static final String CONTENT_MIME_TYPES_KEY = "android.support.v13.view.inputmethod.EditorInfoCompat.CONTENT_MIME_TYPES";
    private static final String[] EMPTY_STRING_ARRAY = new String[0];
    public static final int IME_FLAG_FORCE_ASCII = Integer.MIN_VALUE;
    public static final int IME_FLAG_NO_PERSONALIZED_LEARNING = 16777216;

    public static void setContentMimeTypes(@NonNull EditorInfo editorInfo, @Nullable String[] strArr) {
        if (VERSION.SDK_INT >= 25) {
            editorInfo.contentMimeTypes = strArr;
            return;
        }
        if (editorInfo.extras == null) {
            editorInfo.extras = new Bundle();
        }
        editorInfo.extras.putStringArray(CONTENT_MIME_TYPES_KEY, strArr);
    }

    @NonNull
    public static String[] getContentMimeTypes(EditorInfo editorInfo) {
        if (VERSION.SDK_INT >= 25) {
            editorInfo = editorInfo.contentMimeTypes;
            if (editorInfo == null) {
                editorInfo = EMPTY_STRING_ARRAY;
            }
            return editorInfo;
        } else if (editorInfo.extras == null) {
            return EMPTY_STRING_ARRAY;
        } else {
            editorInfo = editorInfo.extras.getStringArray(CONTENT_MIME_TYPES_KEY);
            if (editorInfo == null) {
                editorInfo = EMPTY_STRING_ARRAY;
            }
            return editorInfo;
        }
    }
}
