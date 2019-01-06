package android.support.v13.view.inputmethod;

import android.content.ClipDescription;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;
import android.view.inputmethod.InputContentInfo;

public final class InputConnectionCompat {
    private static final String COMMIT_CONTENT_ACTION = "android.support.v13.view.inputmethod.InputConnectionCompat.COMMIT_CONTENT";
    private static final String COMMIT_CONTENT_CONTENT_URI_KEY = "android.support.v13.view.inputmethod.InputConnectionCompat.CONTENT_URI";
    private static final String COMMIT_CONTENT_DESCRIPTION_KEY = "android.support.v13.view.inputmethod.InputConnectionCompat.CONTENT_DESCRIPTION";
    private static final String COMMIT_CONTENT_FLAGS_KEY = "android.support.v13.view.inputmethod.InputConnectionCompat.CONTENT_FLAGS";
    private static final String COMMIT_CONTENT_LINK_URI_KEY = "android.support.v13.view.inputmethod.InputConnectionCompat.CONTENT_LINK_URI";
    private static final String COMMIT_CONTENT_OPTS_KEY = "android.support.v13.view.inputmethod.InputConnectionCompat.CONTENT_OPTS";
    private static final String COMMIT_CONTENT_RESULT_RECEIVER = "android.support.v13.view.inputmethod.InputConnectionCompat.CONTENT_RESULT_RECEIVER";
    public static final int INPUT_CONTENT_GRANT_READ_URI_PERMISSION = 1;

    public interface OnCommitContentListener {
        boolean onCommitContent(InputContentInfoCompat inputContentInfoCompat, int i, Bundle bundle);
    }

    static boolean handlePerformPrivateCommand(@Nullable String str, @NonNull Bundle bundle, @NonNull OnCommitContentListener onCommitContentListener) {
        ResultReceiver resultReceiver;
        if (TextUtils.equals(COMMIT_CONTENT_ACTION, str) == null || bundle == null) {
            return false;
        }
        try {
            resultReceiver = (ResultReceiver) bundle.getParcelable(COMMIT_CONTENT_RESULT_RECEIVER);
            try {
                Uri uri = (Uri) bundle.getParcelable(COMMIT_CONTENT_CONTENT_URI_KEY);
                ClipDescription clipDescription = (ClipDescription) bundle.getParcelable(COMMIT_CONTENT_DESCRIPTION_KEY);
                Uri uri2 = (Uri) bundle.getParcelable(COMMIT_CONTENT_LINK_URI_KEY);
                bundle = onCommitContentListener.onCommitContent(new InputContentInfoCompat(uri, clipDescription, uri2), bundle.getInt(COMMIT_CONTENT_FLAGS_KEY), (Bundle) bundle.getParcelable(COMMIT_CONTENT_OPTS_KEY));
                if (resultReceiver != null) {
                    resultReceiver.send(bundle, null);
                }
                return bundle;
            } catch (Throwable th) {
                bundle = th;
                if (resultReceiver != null) {
                    resultReceiver.send(0, null);
                }
                throw bundle;
            }
        } catch (Throwable th2) {
            bundle = th2;
            resultReceiver = null;
            if (resultReceiver != null) {
                resultReceiver.send(0, null);
            }
            throw bundle;
        }
    }

    public static boolean commitContent(@NonNull InputConnection inputConnection, @NonNull EditorInfo editorInfo, @NonNull InputContentInfoCompat inputContentInfoCompat, int i, @Nullable Bundle bundle) {
        ClipDescription description = inputContentInfoCompat.getDescription();
        for (String hasMimeType : EditorInfoCompat.getContentMimeTypes(editorInfo)) {
            if (description.hasMimeType(hasMimeType)) {
                editorInfo = true;
                break;
            }
        }
        editorInfo = null;
        if (editorInfo == null) {
            return false;
        }
        if (VERSION.SDK_INT >= 25) {
            return inputConnection.commitContent((InputContentInfo) inputContentInfoCompat.unwrap(), i, bundle);
        }
        editorInfo = new Bundle();
        editorInfo.putParcelable(COMMIT_CONTENT_CONTENT_URI_KEY, inputContentInfoCompat.getContentUri());
        editorInfo.putParcelable(COMMIT_CONTENT_DESCRIPTION_KEY, inputContentInfoCompat.getDescription());
        editorInfo.putParcelable(COMMIT_CONTENT_LINK_URI_KEY, inputContentInfoCompat.getLinkUri());
        editorInfo.putInt(COMMIT_CONTENT_FLAGS_KEY, i);
        editorInfo.putParcelable(COMMIT_CONTENT_OPTS_KEY, bundle);
        return inputConnection.performPrivateCommand(COMMIT_CONTENT_ACTION, editorInfo);
    }

    @NonNull
    public static InputConnection createWrapper(@NonNull InputConnection inputConnection, @NonNull EditorInfo editorInfo, @NonNull final OnCommitContentListener onCommitContentListener) {
        if (inputConnection == null) {
            throw new IllegalArgumentException("inputConnection must be non-null");
        } else if (editorInfo == null) {
            throw new IllegalArgumentException("editorInfo must be non-null");
        } else if (onCommitContentListener == null) {
            throw new IllegalArgumentException("onCommitContentListener must be non-null");
        } else if (VERSION.SDK_INT >= 25) {
            return new InputConnectionWrapper(inputConnection, false) {
                public boolean commitContent(InputContentInfo inputContentInfo, int i, Bundle bundle) {
                    if (onCommitContentListener.onCommitContent(InputContentInfoCompat.wrap(inputContentInfo), i, bundle)) {
                        return true;
                    }
                    return super.commitContent(inputContentInfo, i, bundle);
                }
            };
        } else {
            if (EditorInfoCompat.getContentMimeTypes(editorInfo).length == null) {
                return inputConnection;
            }
            return new InputConnectionWrapper(inputConnection, false) {
                public boolean performPrivateCommand(String str, Bundle bundle) {
                    if (InputConnectionCompat.handlePerformPrivateCommand(str, bundle, onCommitContentListener)) {
                        return true;
                    }
                    return super.performPrivateCommand(str, bundle);
                }
            };
        }
    }
}
