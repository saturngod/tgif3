package android.support.v13.view.inputmethod;

import android.content.ClipDescription;
import android.net.Uri;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.inputmethod.InputContentInfo;

public final class InputContentInfoCompat {
    private final InputContentInfoCompatImpl mImpl;

    private interface InputContentInfoCompatImpl {
        @NonNull
        Uri getContentUri();

        @NonNull
        ClipDescription getDescription();

        @Nullable
        Object getInputContentInfo();

        @Nullable
        Uri getLinkUri();

        void releasePermission();

        void requestPermission();
    }

    @RequiresApi(25)
    private static final class InputContentInfoCompatApi25Impl implements InputContentInfoCompatImpl {
        @NonNull
        final InputContentInfo mObject;

        InputContentInfoCompatApi25Impl(@NonNull Object obj) {
            this.mObject = (InputContentInfo) obj;
        }

        InputContentInfoCompatApi25Impl(@NonNull Uri uri, @NonNull ClipDescription clipDescription, @Nullable Uri uri2) {
            this.mObject = new InputContentInfo(uri, clipDescription, uri2);
        }

        @NonNull
        public Uri getContentUri() {
            return this.mObject.getContentUri();
        }

        @NonNull
        public ClipDescription getDescription() {
            return this.mObject.getDescription();
        }

        @Nullable
        public Uri getLinkUri() {
            return this.mObject.getLinkUri();
        }

        @Nullable
        public Object getInputContentInfo() {
            return this.mObject;
        }

        public void requestPermission() {
            this.mObject.requestPermission();
        }

        public void releasePermission() {
            this.mObject.releasePermission();
        }
    }

    private static final class InputContentInfoCompatBaseImpl implements InputContentInfoCompatImpl {
        @NonNull
        private final Uri mContentUri;
        @NonNull
        private final ClipDescription mDescription;
        @Nullable
        private final Uri mLinkUri;

        @Nullable
        public Object getInputContentInfo() {
            return null;
        }

        public void releasePermission() {
        }

        public void requestPermission() {
        }

        InputContentInfoCompatBaseImpl(@NonNull Uri uri, @NonNull ClipDescription clipDescription, @Nullable Uri uri2) {
            this.mContentUri = uri;
            this.mDescription = clipDescription;
            this.mLinkUri = uri2;
        }

        @NonNull
        public Uri getContentUri() {
            return this.mContentUri;
        }

        @NonNull
        public ClipDescription getDescription() {
            return this.mDescription;
        }

        @Nullable
        public Uri getLinkUri() {
            return this.mLinkUri;
        }
    }

    public InputContentInfoCompat(@NonNull Uri uri, @NonNull ClipDescription clipDescription, @Nullable Uri uri2) {
        if (VERSION.SDK_INT >= 25) {
            this.mImpl = new InputContentInfoCompatApi25Impl(uri, clipDescription, uri2);
        } else {
            this.mImpl = new InputContentInfoCompatBaseImpl(uri, clipDescription, uri2);
        }
    }

    private InputContentInfoCompat(@NonNull InputContentInfoCompatImpl inputContentInfoCompatImpl) {
        this.mImpl = inputContentInfoCompatImpl;
    }

    @NonNull
    public Uri getContentUri() {
        return this.mImpl.getContentUri();
    }

    @NonNull
    public ClipDescription getDescription() {
        return this.mImpl.getDescription();
    }

    @Nullable
    public Uri getLinkUri() {
        return this.mImpl.getLinkUri();
    }

    @Nullable
    public static InputContentInfoCompat wrap(@Nullable Object obj) {
        if (obj != null && VERSION.SDK_INT >= 25) {
            return new InputContentInfoCompat(new InputContentInfoCompatApi25Impl(obj));
        }
        return null;
    }

    @Nullable
    public Object unwrap() {
        return this.mImpl.getInputContentInfo();
    }

    public void requestPermission() {
        this.mImpl.requestPermission();
    }

    public void releasePermission() {
        this.mImpl.releasePermission();
    }
}
