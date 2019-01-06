package android.support.v4.app;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Person;
import android.app.RemoteInput;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.SystemClock;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.compat.C0014R;
import android.support.v4.text.BidiFormatter;
import android.support.v4.view.ViewCompat;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.TextAppearanceSpan;
import android.widget.RemoteViews;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class NotificationCompat {
    public static final int BADGE_ICON_LARGE = 2;
    public static final int BADGE_ICON_NONE = 0;
    public static final int BADGE_ICON_SMALL = 1;
    public static final String CATEGORY_ALARM = "alarm";
    public static final String CATEGORY_CALL = "call";
    public static final String CATEGORY_EMAIL = "email";
    public static final String CATEGORY_ERROR = "err";
    public static final String CATEGORY_EVENT = "event";
    public static final String CATEGORY_MESSAGE = "msg";
    public static final String CATEGORY_PROGRESS = "progress";
    public static final String CATEGORY_PROMO = "promo";
    public static final String CATEGORY_RECOMMENDATION = "recommendation";
    public static final String CATEGORY_REMINDER = "reminder";
    public static final String CATEGORY_SERVICE = "service";
    public static final String CATEGORY_SOCIAL = "social";
    public static final String CATEGORY_STATUS = "status";
    public static final String CATEGORY_SYSTEM = "sys";
    public static final String CATEGORY_TRANSPORT = "transport";
    @ColorInt
    public static final int COLOR_DEFAULT = 0;
    public static final int DEFAULT_ALL = -1;
    public static final int DEFAULT_LIGHTS = 4;
    public static final int DEFAULT_SOUND = 1;
    public static final int DEFAULT_VIBRATE = 2;
    public static final String EXTRA_AUDIO_CONTENTS_URI = "android.audioContents";
    public static final String EXTRA_BACKGROUND_IMAGE_URI = "android.backgroundImageUri";
    public static final String EXTRA_BIG_TEXT = "android.bigText";
    public static final String EXTRA_COMPACT_ACTIONS = "android.compactActions";
    public static final String EXTRA_CONVERSATION_TITLE = "android.conversationTitle";
    public static final String EXTRA_HIDDEN_CONVERSATION_TITLE = "android.hiddenConversationTitle";
    public static final String EXTRA_INFO_TEXT = "android.infoText";
    public static final String EXTRA_IS_GROUP_CONVERSATION = "android.isGroupConversation";
    public static final String EXTRA_LARGE_ICON = "android.largeIcon";
    public static final String EXTRA_LARGE_ICON_BIG = "android.largeIcon.big";
    public static final String EXTRA_MEDIA_SESSION = "android.mediaSession";
    public static final String EXTRA_MESSAGES = "android.messages";
    public static final String EXTRA_MESSAGING_STYLE_USER = "android.messagingStyleUser";
    public static final String EXTRA_PEOPLE = "android.people";
    public static final String EXTRA_PICTURE = "android.picture";
    public static final String EXTRA_PROGRESS = "android.progress";
    public static final String EXTRA_PROGRESS_INDETERMINATE = "android.progressIndeterminate";
    public static final String EXTRA_PROGRESS_MAX = "android.progressMax";
    public static final String EXTRA_REMOTE_INPUT_HISTORY = "android.remoteInputHistory";
    public static final String EXTRA_SELF_DISPLAY_NAME = "android.selfDisplayName";
    public static final String EXTRA_SHOW_CHRONOMETER = "android.showChronometer";
    public static final String EXTRA_SHOW_WHEN = "android.showWhen";
    public static final String EXTRA_SMALL_ICON = "android.icon";
    public static final String EXTRA_SUB_TEXT = "android.subText";
    public static final String EXTRA_SUMMARY_TEXT = "android.summaryText";
    public static final String EXTRA_TEMPLATE = "android.template";
    public static final String EXTRA_TEXT = "android.text";
    public static final String EXTRA_TEXT_LINES = "android.textLines";
    public static final String EXTRA_TITLE = "android.title";
    public static final String EXTRA_TITLE_BIG = "android.title.big";
    public static final int FLAG_AUTO_CANCEL = 16;
    public static final int FLAG_FOREGROUND_SERVICE = 64;
    public static final int FLAG_GROUP_SUMMARY = 512;
    @Deprecated
    public static final int FLAG_HIGH_PRIORITY = 128;
    public static final int FLAG_INSISTENT = 4;
    public static final int FLAG_LOCAL_ONLY = 256;
    public static final int FLAG_NO_CLEAR = 32;
    public static final int FLAG_ONGOING_EVENT = 2;
    public static final int FLAG_ONLY_ALERT_ONCE = 8;
    public static final int FLAG_SHOW_LIGHTS = 1;
    public static final int GROUP_ALERT_ALL = 0;
    public static final int GROUP_ALERT_CHILDREN = 2;
    public static final int GROUP_ALERT_SUMMARY = 1;
    public static final int PRIORITY_DEFAULT = 0;
    public static final int PRIORITY_HIGH = 1;
    public static final int PRIORITY_LOW = -1;
    public static final int PRIORITY_MAX = 2;
    public static final int PRIORITY_MIN = -2;
    public static final int STREAM_DEFAULT = -1;
    public static final int VISIBILITY_PRIVATE = 0;
    public static final int VISIBILITY_PUBLIC = 1;
    public static final int VISIBILITY_SECRET = -1;

    public static class Action {
        static final String EXTRA_SEMANTIC_ACTION = "android.support.action.semanticAction";
        static final String EXTRA_SHOWS_USER_INTERFACE = "android.support.action.showsUserInterface";
        public static final int SEMANTIC_ACTION_ARCHIVE = 5;
        public static final int SEMANTIC_ACTION_CALL = 10;
        public static final int SEMANTIC_ACTION_DELETE = 4;
        public static final int SEMANTIC_ACTION_MARK_AS_READ = 2;
        public static final int SEMANTIC_ACTION_MARK_AS_UNREAD = 3;
        public static final int SEMANTIC_ACTION_MUTE = 6;
        public static final int SEMANTIC_ACTION_NONE = 0;
        public static final int SEMANTIC_ACTION_REPLY = 1;
        public static final int SEMANTIC_ACTION_THUMBS_DOWN = 9;
        public static final int SEMANTIC_ACTION_THUMBS_UP = 8;
        public static final int SEMANTIC_ACTION_UNMUTE = 7;
        public PendingIntent actionIntent;
        public int icon;
        private boolean mAllowGeneratedReplies;
        private final RemoteInput[] mDataOnlyRemoteInputs;
        final Bundle mExtras;
        private final RemoteInput[] mRemoteInputs;
        private final int mSemanticAction;
        boolean mShowsUserInterface;
        public CharSequence title;

        public static final class Builder {
            private boolean mAllowGeneratedReplies;
            private final Bundle mExtras;
            private final int mIcon;
            private final PendingIntent mIntent;
            private ArrayList<RemoteInput> mRemoteInputs;
            private int mSemanticAction;
            private boolean mShowsUserInterface;
            private final CharSequence mTitle;

            public Builder(int i, CharSequence charSequence, PendingIntent pendingIntent) {
                this(i, charSequence, pendingIntent, new Bundle(), null, true, 0, true);
            }

            public Builder(Action action) {
                this(action.icon, action.title, action.actionIntent, new Bundle(action.mExtras), action.getRemoteInputs(), action.getAllowGeneratedReplies(), action.getSemanticAction(), action.mShowsUserInterface);
            }

            private Builder(int i, CharSequence charSequence, PendingIntent pendingIntent, Bundle bundle, RemoteInput[] remoteInputArr, boolean z, int i2, boolean z2) {
                this.mAllowGeneratedReplies = true;
                this.mShowsUserInterface = true;
                this.mIcon = i;
                this.mTitle = Builder.limitCharSequenceLength(charSequence);
                this.mIntent = pendingIntent;
                this.mExtras = bundle;
                if (remoteInputArr == null) {
                    i = 0;
                } else {
                    i = new ArrayList(Arrays.asList(remoteInputArr));
                }
                this.mRemoteInputs = i;
                this.mAllowGeneratedReplies = z;
                this.mSemanticAction = i2;
                this.mShowsUserInterface = z2;
            }

            public Builder addExtras(Bundle bundle) {
                if (bundle != null) {
                    this.mExtras.putAll(bundle);
                }
                return this;
            }

            public Bundle getExtras() {
                return this.mExtras;
            }

            public Builder addRemoteInput(RemoteInput remoteInput) {
                if (this.mRemoteInputs == null) {
                    this.mRemoteInputs = new ArrayList();
                }
                this.mRemoteInputs.add(remoteInput);
                return this;
            }

            public Builder setAllowGeneratedReplies(boolean z) {
                this.mAllowGeneratedReplies = z;
                return this;
            }

            public Builder setSemanticAction(int i) {
                this.mSemanticAction = i;
                return this;
            }

            public Builder setShowsUserInterface(boolean z) {
                this.mShowsUserInterface = z;
                return this;
            }

            public Builder extend(Extender extender) {
                extender.extend(this);
                return this;
            }

            public Action build() {
                RemoteInput[] remoteInputArr;
                List arrayList = new ArrayList();
                List arrayList2 = new ArrayList();
                if (this.mRemoteInputs != null) {
                    Iterator it = this.mRemoteInputs.iterator();
                    while (it.hasNext()) {
                        RemoteInput remoteInput = (RemoteInput) it.next();
                        if (remoteInput.isDataOnly()) {
                            arrayList.add(remoteInput);
                        } else {
                            arrayList2.add(remoteInput);
                        }
                    }
                }
                RemoteInput[] remoteInputArr2 = null;
                if (arrayList.isEmpty()) {
                    remoteInputArr = null;
                } else {
                    remoteInputArr = (RemoteInput[]) arrayList.toArray(new RemoteInput[arrayList.size()]);
                }
                if (!arrayList2.isEmpty()) {
                    remoteInputArr2 = (RemoteInput[]) arrayList2.toArray(new RemoteInput[arrayList2.size()]);
                }
                return new Action(this.mIcon, this.mTitle, this.mIntent, this.mExtras, remoteInputArr2, remoteInputArr, this.mAllowGeneratedReplies, this.mSemanticAction, this.mShowsUserInterface);
            }
        }

        public interface Extender {
            Builder extend(Builder builder);
        }

        @Retention(RetentionPolicy.SOURCE)
        public @interface SemanticAction {
        }

        public static final class WearableExtender implements Extender {
            private static final int DEFAULT_FLAGS = 1;
            private static final String EXTRA_WEARABLE_EXTENSIONS = "android.wearable.EXTENSIONS";
            private static final int FLAG_AVAILABLE_OFFLINE = 1;
            private static final int FLAG_HINT_DISPLAY_INLINE = 4;
            private static final int FLAG_HINT_LAUNCHES_ACTIVITY = 2;
            private static final String KEY_CANCEL_LABEL = "cancelLabel";
            private static final String KEY_CONFIRM_LABEL = "confirmLabel";
            private static final String KEY_FLAGS = "flags";
            private static final String KEY_IN_PROGRESS_LABEL = "inProgressLabel";
            private CharSequence mCancelLabel;
            private CharSequence mConfirmLabel;
            private int mFlags = 1;
            private CharSequence mInProgressLabel;

            public WearableExtender(Action action) {
                action = action.getExtras().getBundle(EXTRA_WEARABLE_EXTENSIONS);
                if (action != null) {
                    this.mFlags = action.getInt(KEY_FLAGS, 1);
                    this.mInProgressLabel = action.getCharSequence(KEY_IN_PROGRESS_LABEL);
                    this.mConfirmLabel = action.getCharSequence(KEY_CONFIRM_LABEL);
                    this.mCancelLabel = action.getCharSequence(KEY_CANCEL_LABEL);
                }
            }

            public Builder extend(Builder builder) {
                Bundle bundle = new Bundle();
                if (this.mFlags != 1) {
                    bundle.putInt(KEY_FLAGS, this.mFlags);
                }
                if (this.mInProgressLabel != null) {
                    bundle.putCharSequence(KEY_IN_PROGRESS_LABEL, this.mInProgressLabel);
                }
                if (this.mConfirmLabel != null) {
                    bundle.putCharSequence(KEY_CONFIRM_LABEL, this.mConfirmLabel);
                }
                if (this.mCancelLabel != null) {
                    bundle.putCharSequence(KEY_CANCEL_LABEL, this.mCancelLabel);
                }
                builder.getExtras().putBundle(EXTRA_WEARABLE_EXTENSIONS, bundle);
                return builder;
            }

            public WearableExtender clone() {
                WearableExtender wearableExtender = new WearableExtender();
                wearableExtender.mFlags = this.mFlags;
                wearableExtender.mInProgressLabel = this.mInProgressLabel;
                wearableExtender.mConfirmLabel = this.mConfirmLabel;
                wearableExtender.mCancelLabel = this.mCancelLabel;
                return wearableExtender;
            }

            public WearableExtender setAvailableOffline(boolean z) {
                setFlag(1, z);
                return this;
            }

            public boolean isAvailableOffline() {
                return (this.mFlags & 1) != 0;
            }

            private void setFlag(int i, boolean z) {
                if (z) {
                    this.mFlags = i | this.mFlags;
                    return;
                }
                this.mFlags = (~i) & this.mFlags;
            }

            @Deprecated
            public WearableExtender setInProgressLabel(CharSequence charSequence) {
                this.mInProgressLabel = charSequence;
                return this;
            }

            @Deprecated
            public CharSequence getInProgressLabel() {
                return this.mInProgressLabel;
            }

            @Deprecated
            public WearableExtender setConfirmLabel(CharSequence charSequence) {
                this.mConfirmLabel = charSequence;
                return this;
            }

            @Deprecated
            public CharSequence getConfirmLabel() {
                return this.mConfirmLabel;
            }

            @Deprecated
            public WearableExtender setCancelLabel(CharSequence charSequence) {
                this.mCancelLabel = charSequence;
                return this;
            }

            @Deprecated
            public CharSequence getCancelLabel() {
                return this.mCancelLabel;
            }

            public WearableExtender setHintLaunchesActivity(boolean z) {
                setFlag(2, z);
                return this;
            }

            public boolean getHintLaunchesActivity() {
                return (this.mFlags & 2) != 0;
            }

            public WearableExtender setHintDisplayActionInline(boolean z) {
                setFlag(4, z);
                return this;
            }

            public boolean getHintDisplayActionInline() {
                return (this.mFlags & 4) != 0;
            }
        }

        public Action(int i, CharSequence charSequence, PendingIntent pendingIntent) {
            this(i, charSequence, pendingIntent, new Bundle(), null, null, true, 0, true);
        }

        Action(int i, CharSequence charSequence, PendingIntent pendingIntent, Bundle bundle, RemoteInput[] remoteInputArr, RemoteInput[] remoteInputArr2, boolean z, int i2, boolean z2) {
            this.mShowsUserInterface = true;
            this.icon = i;
            this.title = Builder.limitCharSequenceLength(charSequence);
            this.actionIntent = pendingIntent;
            if (bundle == null) {
                bundle = new Bundle();
            }
            this.mExtras = bundle;
            this.mRemoteInputs = remoteInputArr;
            this.mDataOnlyRemoteInputs = remoteInputArr2;
            this.mAllowGeneratedReplies = z;
            this.mSemanticAction = i2;
            this.mShowsUserInterface = z2;
        }

        public int getIcon() {
            return this.icon;
        }

        public CharSequence getTitle() {
            return this.title;
        }

        public PendingIntent getActionIntent() {
            return this.actionIntent;
        }

        public Bundle getExtras() {
            return this.mExtras;
        }

        public boolean getAllowGeneratedReplies() {
            return this.mAllowGeneratedReplies;
        }

        public RemoteInput[] getRemoteInputs() {
            return this.mRemoteInputs;
        }

        public int getSemanticAction() {
            return this.mSemanticAction;
        }

        public RemoteInput[] getDataOnlyRemoteInputs() {
            return this.mDataOnlyRemoteInputs;
        }

        public boolean getShowsUserInterface() {
            return this.mShowsUserInterface;
        }
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface BadgeIconType {
    }

    public static class Builder {
        private static final int MAX_CHARSEQUENCE_LENGTH = 5120;
        @RestrictTo({Scope.LIBRARY_GROUP})
        public ArrayList<Action> mActions;
        int mBadgeIcon;
        RemoteViews mBigContentView;
        String mCategory;
        String mChannelId;
        int mColor;
        boolean mColorized;
        boolean mColorizedSet;
        CharSequence mContentInfo;
        PendingIntent mContentIntent;
        CharSequence mContentText;
        CharSequence mContentTitle;
        RemoteViews mContentView;
        @RestrictTo({Scope.LIBRARY_GROUP})
        public Context mContext;
        Bundle mExtras;
        PendingIntent mFullScreenIntent;
        int mGroupAlertBehavior;
        String mGroupKey;
        boolean mGroupSummary;
        RemoteViews mHeadsUpContentView;
        ArrayList<Action> mInvisibleActions;
        Bitmap mLargeIcon;
        boolean mLocalOnly;
        Notification mNotification;
        int mNumber;
        @Deprecated
        public ArrayList<String> mPeople;
        int mPriority;
        int mProgress;
        boolean mProgressIndeterminate;
        int mProgressMax;
        Notification mPublicVersion;
        CharSequence[] mRemoteInputHistory;
        String mShortcutId;
        boolean mShowWhen;
        String mSortKey;
        Style mStyle;
        CharSequence mSubText;
        RemoteViews mTickerView;
        long mTimeout;
        boolean mUseChronometer;
        int mVisibility;

        public Builder(@NonNull Context context, @NonNull String str) {
            this.mActions = new ArrayList();
            this.mInvisibleActions = new ArrayList();
            this.mShowWhen = true;
            this.mLocalOnly = false;
            this.mColor = 0;
            this.mVisibility = 0;
            this.mBadgeIcon = 0;
            this.mGroupAlertBehavior = 0;
            this.mNotification = new Notification();
            this.mContext = context;
            this.mChannelId = str;
            this.mNotification.when = System.currentTimeMillis();
            this.mNotification.audioStreamType = -1;
            this.mPriority = 0;
            this.mPeople = new ArrayList();
        }

        @Deprecated
        public Builder(Context context) {
            this(context, null);
        }

        public Builder setWhen(long j) {
            this.mNotification.when = j;
            return this;
        }

        public Builder setShowWhen(boolean z) {
            this.mShowWhen = z;
            return this;
        }

        public Builder setUsesChronometer(boolean z) {
            this.mUseChronometer = z;
            return this;
        }

        public Builder setSmallIcon(int i) {
            this.mNotification.icon = i;
            return this;
        }

        public Builder setSmallIcon(int i, int i2) {
            this.mNotification.icon = i;
            this.mNotification.iconLevel = i2;
            return this;
        }

        public Builder setContentTitle(CharSequence charSequence) {
            this.mContentTitle = limitCharSequenceLength(charSequence);
            return this;
        }

        public Builder setContentText(CharSequence charSequence) {
            this.mContentText = limitCharSequenceLength(charSequence);
            return this;
        }

        public Builder setSubText(CharSequence charSequence) {
            this.mSubText = limitCharSequenceLength(charSequence);
            return this;
        }

        public Builder setRemoteInputHistory(CharSequence[] charSequenceArr) {
            this.mRemoteInputHistory = charSequenceArr;
            return this;
        }

        public Builder setNumber(int i) {
            this.mNumber = i;
            return this;
        }

        public Builder setContentInfo(CharSequence charSequence) {
            this.mContentInfo = limitCharSequenceLength(charSequence);
            return this;
        }

        public Builder setProgress(int i, int i2, boolean z) {
            this.mProgressMax = i;
            this.mProgress = i2;
            this.mProgressIndeterminate = z;
            return this;
        }

        public Builder setContent(RemoteViews remoteViews) {
            this.mNotification.contentView = remoteViews;
            return this;
        }

        public Builder setContentIntent(PendingIntent pendingIntent) {
            this.mContentIntent = pendingIntent;
            return this;
        }

        public Builder setDeleteIntent(PendingIntent pendingIntent) {
            this.mNotification.deleteIntent = pendingIntent;
            return this;
        }

        public Builder setFullScreenIntent(PendingIntent pendingIntent, boolean z) {
            this.mFullScreenIntent = pendingIntent;
            setFlag(128, z);
            return this;
        }

        public Builder setTicker(CharSequence charSequence) {
            this.mNotification.tickerText = limitCharSequenceLength(charSequence);
            return this;
        }

        public Builder setTicker(CharSequence charSequence, RemoteViews remoteViews) {
            this.mNotification.tickerText = limitCharSequenceLength(charSequence);
            this.mTickerView = remoteViews;
            return this;
        }

        public Builder setLargeIcon(Bitmap bitmap) {
            this.mLargeIcon = reduceLargeIconSize(bitmap);
            return this;
        }

        private Bitmap reduceLargeIconSize(Bitmap bitmap) {
            if (bitmap != null) {
                if (VERSION.SDK_INT < 27) {
                    Resources resources = this.mContext.getResources();
                    int dimensionPixelSize = resources.getDimensionPixelSize(C0014R.dimen.compat_notification_large_icon_max_width);
                    int dimensionPixelSize2 = resources.getDimensionPixelSize(C0014R.dimen.compat_notification_large_icon_max_height);
                    if (bitmap.getWidth() <= dimensionPixelSize && bitmap.getHeight() <= dimensionPixelSize2) {
                        return bitmap;
                    }
                    double min = Math.min(((double) dimensionPixelSize) / ((double) Math.max(1, bitmap.getWidth())), ((double) dimensionPixelSize2) / ((double) Math.max(1, bitmap.getHeight())));
                    return Bitmap.createScaledBitmap(bitmap, (int) Math.ceil(((double) bitmap.getWidth()) * min), (int) Math.ceil(((double) bitmap.getHeight()) * min), true);
                }
            }
            return bitmap;
        }

        public Builder setSound(Uri uri) {
            this.mNotification.sound = uri;
            this.mNotification.audioStreamType = -1;
            if (VERSION.SDK_INT >= 21) {
                this.mNotification.audioAttributes = new android.media.AudioAttributes.Builder().setContentType(4).setUsage(5).build();
            }
            return this;
        }

        public Builder setSound(Uri uri, int i) {
            this.mNotification.sound = uri;
            this.mNotification.audioStreamType = i;
            if (VERSION.SDK_INT >= 21) {
                this.mNotification.audioAttributes = new android.media.AudioAttributes.Builder().setContentType(4).setLegacyStreamType(i).build();
            }
            return this;
        }

        public Builder setVibrate(long[] jArr) {
            this.mNotification.vibrate = jArr;
            return this;
        }

        public Builder setLights(@ColorInt int i, int i2, int i3) {
            this.mNotification.ledARGB = i;
            this.mNotification.ledOnMS = i2;
            this.mNotification.ledOffMS = i3;
            i = (this.mNotification.ledOnMS == 0 || this.mNotification.ledOffMS == 0) ? 0 : 1;
            this.mNotification.flags = i | (this.mNotification.flags & -2);
            return this;
        }

        public Builder setOngoing(boolean z) {
            setFlag(2, z);
            return this;
        }

        public Builder setColorized(boolean z) {
            this.mColorized = z;
            this.mColorizedSet = true;
            return this;
        }

        public Builder setOnlyAlertOnce(boolean z) {
            setFlag(8, z);
            return this;
        }

        public Builder setAutoCancel(boolean z) {
            setFlag(16, z);
            return this;
        }

        public Builder setLocalOnly(boolean z) {
            this.mLocalOnly = z;
            return this;
        }

        public Builder setCategory(String str) {
            this.mCategory = str;
            return this;
        }

        public Builder setDefaults(int i) {
            this.mNotification.defaults = i;
            if ((i & 4) != 0) {
                i = this.mNotification;
                i.flags |= 1;
            }
            return this;
        }

        private void setFlag(int i, boolean z) {
            if (z) {
                z = this.mNotification;
                z.flags = i | z.flags;
                return;
            }
            z = this.mNotification;
            z.flags = (~i) & z.flags;
        }

        public Builder setPriority(int i) {
            this.mPriority = i;
            return this;
        }

        public Builder addPerson(String str) {
            this.mPeople.add(str);
            return this;
        }

        public Builder setGroup(String str) {
            this.mGroupKey = str;
            return this;
        }

        public Builder setGroupSummary(boolean z) {
            this.mGroupSummary = z;
            return this;
        }

        public Builder setSortKey(String str) {
            this.mSortKey = str;
            return this;
        }

        public Builder addExtras(Bundle bundle) {
            if (bundle != null) {
                if (this.mExtras == null) {
                    this.mExtras = new Bundle(bundle);
                } else {
                    this.mExtras.putAll(bundle);
                }
            }
            return this;
        }

        public Builder setExtras(Bundle bundle) {
            this.mExtras = bundle;
            return this;
        }

        public Bundle getExtras() {
            if (this.mExtras == null) {
                this.mExtras = new Bundle();
            }
            return this.mExtras;
        }

        public Builder addAction(int i, CharSequence charSequence, PendingIntent pendingIntent) {
            this.mActions.add(new Action(i, charSequence, pendingIntent));
            return this;
        }

        public Builder addAction(Action action) {
            this.mActions.add(action);
            return this;
        }

        @RequiresApi(21)
        public Builder addInvisibleAction(int i, CharSequence charSequence, PendingIntent pendingIntent) {
            return addInvisibleAction(new Action(i, charSequence, pendingIntent));
        }

        @RequiresApi(21)
        public Builder addInvisibleAction(Action action) {
            this.mInvisibleActions.add(action);
            return this;
        }

        public Builder setStyle(Style style) {
            if (this.mStyle != style) {
                this.mStyle = style;
                if (this.mStyle != null) {
                    this.mStyle.setBuilder(this);
                }
            }
            return this;
        }

        public Builder setColor(@ColorInt int i) {
            this.mColor = i;
            return this;
        }

        public Builder setVisibility(int i) {
            this.mVisibility = i;
            return this;
        }

        public Builder setPublicVersion(Notification notification) {
            this.mPublicVersion = notification;
            return this;
        }

        public Builder setCustomContentView(RemoteViews remoteViews) {
            this.mContentView = remoteViews;
            return this;
        }

        public Builder setCustomBigContentView(RemoteViews remoteViews) {
            this.mBigContentView = remoteViews;
            return this;
        }

        public Builder setCustomHeadsUpContentView(RemoteViews remoteViews) {
            this.mHeadsUpContentView = remoteViews;
            return this;
        }

        public Builder setChannelId(@NonNull String str) {
            this.mChannelId = str;
            return this;
        }

        public Builder setTimeoutAfter(long j) {
            this.mTimeout = j;
            return this;
        }

        public Builder setShortcutId(String str) {
            this.mShortcutId = str;
            return this;
        }

        public Builder setBadgeIconType(int i) {
            this.mBadgeIcon = i;
            return this;
        }

        public Builder setGroupAlertBehavior(int i) {
            this.mGroupAlertBehavior = i;
            return this;
        }

        public Builder extend(Extender extender) {
            extender.extend(this);
            return this;
        }

        @Deprecated
        public Notification getNotification() {
            return build();
        }

        public Notification build() {
            return new NotificationCompatBuilder(this).build();
        }

        protected static CharSequence limitCharSequenceLength(CharSequence charSequence) {
            if (charSequence == null) {
                return charSequence;
            }
            if (charSequence.length() > MAX_CHARSEQUENCE_LENGTH) {
                charSequence = charSequence.subSequence(0, MAX_CHARSEQUENCE_LENGTH);
            }
            return charSequence;
        }

        @RestrictTo({Scope.LIBRARY_GROUP})
        public RemoteViews getContentView() {
            return this.mContentView;
        }

        @RestrictTo({Scope.LIBRARY_GROUP})
        public RemoteViews getBigContentView() {
            return this.mBigContentView;
        }

        @RestrictTo({Scope.LIBRARY_GROUP})
        public RemoteViews getHeadsUpContentView() {
            return this.mHeadsUpContentView;
        }

        @RestrictTo({Scope.LIBRARY_GROUP})
        public long getWhenIfShowing() {
            return this.mShowWhen ? this.mNotification.when : 0;
        }

        @RestrictTo({Scope.LIBRARY_GROUP})
        public int getPriority() {
            return this.mPriority;
        }

        @RestrictTo({Scope.LIBRARY_GROUP})
        public int getColor() {
            return this.mColor;
        }
    }

    public interface Extender {
        Builder extend(Builder builder);
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface GroupAlertBehavior {
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface NotificationVisibility {
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface StreamType {
    }

    public static abstract class Style {
        CharSequence mBigContentTitle;
        @RestrictTo({Scope.LIBRARY_GROUP})
        protected Builder mBuilder;
        CharSequence mSummaryText;
        boolean mSummaryTextSet = false;

        private static float constrain(float f, float f2, float f3) {
            return f < f2 ? f2 : f > f3 ? f3 : f;
        }

        @RestrictTo({Scope.LIBRARY_GROUP})
        public void addCompatExtras(Bundle bundle) {
        }

        @RestrictTo({Scope.LIBRARY_GROUP})
        public void apply(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
        }

        @RestrictTo({Scope.LIBRARY_GROUP})
        public RemoteViews makeBigContentView(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            return null;
        }

        @RestrictTo({Scope.LIBRARY_GROUP})
        public RemoteViews makeContentView(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            return null;
        }

        @RestrictTo({Scope.LIBRARY_GROUP})
        public RemoteViews makeHeadsUpContentView(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            return null;
        }

        @RestrictTo({Scope.LIBRARY_GROUP})
        protected void restoreFromCompatExtras(Bundle bundle) {
        }

        public void setBuilder(Builder builder) {
            if (this.mBuilder != builder) {
                this.mBuilder = builder;
                if (this.mBuilder != null) {
                    this.mBuilder.setStyle(this);
                }
            }
        }

        public Notification build() {
            return this.mBuilder != null ? this.mBuilder.build() : null;
        }

        @RestrictTo({Scope.LIBRARY_GROUP})
        public RemoteViews applyStandardTemplate(boolean z, int i, boolean z2) {
            Object obj;
            Resources resources = this.mBuilder.mContext.getResources();
            RemoteViews remoteViews = new RemoteViews(this.mBuilder.mContext.getPackageName(), i);
            int i2 = 0;
            i = this.mBuilder.getPriority() < -1 ? 1 : 0;
            if (VERSION.SDK_INT >= 16 && VERSION.SDK_INT < 21) {
                if (i != 0) {
                    remoteViews.setInt(C0014R.id.notification_background, "setBackgroundResource", C0014R.drawable.notification_bg_low);
                    remoteViews.setInt(C0014R.id.icon, "setBackgroundResource", C0014R.drawable.notification_template_icon_low_bg);
                } else {
                    remoteViews.setInt(C0014R.id.notification_background, "setBackgroundResource", C0014R.drawable.notification_bg);
                    remoteViews.setInt(C0014R.id.icon, "setBackgroundResource", C0014R.drawable.notification_template_icon_bg);
                }
            }
            if (this.mBuilder.mLargeIcon != 0) {
                if (VERSION.SDK_INT >= 16) {
                    remoteViews.setViewVisibility(C0014R.id.icon, 0);
                    remoteViews.setImageViewBitmap(C0014R.id.icon, this.mBuilder.mLargeIcon);
                } else {
                    remoteViews.setViewVisibility(C0014R.id.icon, 8);
                }
                if (z && this.mBuilder.mNotification.icon) {
                    z = resources.getDimensionPixelSize(C0014R.dimen.notification_right_icon_size);
                    i = z - (resources.getDimensionPixelSize(C0014R.dimen.notification_small_icon_background_padding) * 2);
                    if (VERSION.SDK_INT >= 21) {
                        remoteViews.setImageViewBitmap(C0014R.id.right_icon, createIconWithBackground(this.mBuilder.mNotification.icon, z, i, this.mBuilder.getColor()));
                    } else {
                        remoteViews.setImageViewBitmap(C0014R.id.right_icon, createColoredBitmap(this.mBuilder.mNotification.icon, -1));
                    }
                    remoteViews.setViewVisibility(C0014R.id.right_icon, 0);
                }
            } else if (z && this.mBuilder.mNotification.icon) {
                remoteViews.setViewVisibility(C0014R.id.icon, 0);
                if (VERSION.SDK_INT >= true) {
                    remoteViews.setImageViewBitmap(C0014R.id.icon, createIconWithBackground(this.mBuilder.mNotification.icon, resources.getDimensionPixelSize(C0014R.dimen.notification_large_icon_width) - resources.getDimensionPixelSize(C0014R.dimen.notification_big_circle_margin), resources.getDimensionPixelSize(C0014R.dimen.notification_small_icon_size_as_large), this.mBuilder.getColor()));
                } else {
                    remoteViews.setImageViewBitmap(C0014R.id.icon, createColoredBitmap(this.mBuilder.mNotification.icon, -1));
                }
            }
            if (this.mBuilder.mContentTitle) {
                remoteViews.setTextViewText(C0014R.id.title, this.mBuilder.mContentTitle);
            }
            if (this.mBuilder.mContentText) {
                remoteViews.setTextViewText(C0014R.id.text, this.mBuilder.mContentText);
                z = true;
            } else {
                z = false;
            }
            i = (VERSION.SDK_INT >= 21 || this.mBuilder.mLargeIcon == 0) ? 0 : 1;
            if (this.mBuilder.mContentInfo != null) {
                remoteViews.setTextViewText(C0014R.id.info, this.mBuilder.mContentInfo);
                remoteViews.setViewVisibility(C0014R.id.info, 0);
            } else if (this.mBuilder.mNumber > 0) {
                if (this.mBuilder.mNumber > resources.getInteger(C0014R.integer.status_bar_notification_info_maxnum)) {
                    remoteViews.setTextViewText(C0014R.id.info, resources.getString(C0014R.string.status_bar_notification_info_overflow));
                } else {
                    remoteViews.setTextViewText(C0014R.id.info, NumberFormat.getIntegerInstance().format((long) this.mBuilder.mNumber));
                }
                remoteViews.setViewVisibility(C0014R.id.info, 0);
            } else {
                remoteViews.setViewVisibility(C0014R.id.info, 8);
                if (this.mBuilder.mSubText != null && VERSION.SDK_INT >= 16) {
                    remoteViews.setTextViewText(C0014R.id.text, this.mBuilder.mSubText);
                    if (this.mBuilder.mContentText == null) {
                        remoteViews.setTextViewText(C0014R.id.text2, this.mBuilder.mContentText);
                        remoteViews.setViewVisibility(C0014R.id.text2, 0);
                        obj = 1;
                        if (obj != null && VERSION.SDK_INT >= 16) {
                            if (z2) {
                                remoteViews.setTextViewTextSize(C0014R.id.text, 0, (float) resources.getDimensionPixelSize(C0014R.dimen.notification_subtext_size));
                            }
                            remoteViews.setViewPadding(C0014R.id.line1, 0, 0, 0, 0);
                        }
                        if (this.mBuilder.getWhenIfShowing() != 0) {
                            if (this.mBuilder.mUseChronometer != 0 || VERSION.SDK_INT < 16) {
                                remoteViews.setViewVisibility(C0014R.id.time, 0);
                                remoteViews.setLong(C0014R.id.time, "setTime", this.mBuilder.getWhenIfShowing());
                            } else {
                                remoteViews.setViewVisibility(C0014R.id.chronometer, 0);
                                remoteViews.setLong(C0014R.id.chronometer, "setBase", this.mBuilder.getWhenIfShowing() + (SystemClock.elapsedRealtime() - System.currentTimeMillis()));
                                remoteViews.setBoolean(C0014R.id.chronometer, "setStarted", true);
                            }
                            i = 1;
                        }
                        remoteViews.setViewVisibility(C0014R.id.right_side, i == 0 ? 0 : 8);
                        i = C0014R.id.line3;
                        if (!z) {
                            i2 = 8;
                        }
                        remoteViews.setViewVisibility(i, i2);
                        return remoteViews;
                    }
                    remoteViews.setViewVisibility(C0014R.id.text2, 8);
                }
                obj = null;
                if (z2) {
                    remoteViews.setTextViewTextSize(C0014R.id.text, 0, (float) resources.getDimensionPixelSize(C0014R.dimen.notification_subtext_size));
                }
                remoteViews.setViewPadding(C0014R.id.line1, 0, 0, 0, 0);
                if (this.mBuilder.getWhenIfShowing() != 0) {
                    if (this.mBuilder.mUseChronometer != 0) {
                    }
                    remoteViews.setViewVisibility(C0014R.id.time, 0);
                    remoteViews.setLong(C0014R.id.time, "setTime", this.mBuilder.getWhenIfShowing());
                    i = 1;
                }
                if (i == 0) {
                }
                remoteViews.setViewVisibility(C0014R.id.right_side, i == 0 ? 0 : 8);
                i = C0014R.id.line3;
                if (!z) {
                    i2 = 8;
                }
                remoteViews.setViewVisibility(i, i2);
                return remoteViews;
            }
            z = true;
            i = 1;
            remoteViews.setTextViewText(C0014R.id.text, this.mBuilder.mSubText);
            if (this.mBuilder.mContentText == null) {
                remoteViews.setViewVisibility(C0014R.id.text2, 8);
                obj = null;
                if (z2) {
                    remoteViews.setTextViewTextSize(C0014R.id.text, 0, (float) resources.getDimensionPixelSize(C0014R.dimen.notification_subtext_size));
                }
                remoteViews.setViewPadding(C0014R.id.line1, 0, 0, 0, 0);
                if (this.mBuilder.getWhenIfShowing() != 0) {
                    if (this.mBuilder.mUseChronometer != 0) {
                    }
                    remoteViews.setViewVisibility(C0014R.id.time, 0);
                    remoteViews.setLong(C0014R.id.time, "setTime", this.mBuilder.getWhenIfShowing());
                    i = 1;
                }
                if (i == 0) {
                }
                remoteViews.setViewVisibility(C0014R.id.right_side, i == 0 ? 0 : 8);
                i = C0014R.id.line3;
                if (!z) {
                    i2 = 8;
                }
                remoteViews.setViewVisibility(i, i2);
                return remoteViews;
            }
            remoteViews.setTextViewText(C0014R.id.text2, this.mBuilder.mContentText);
            remoteViews.setViewVisibility(C0014R.id.text2, 0);
            obj = 1;
            if (z2) {
                remoteViews.setTextViewTextSize(C0014R.id.text, 0, (float) resources.getDimensionPixelSize(C0014R.dimen.notification_subtext_size));
            }
            remoteViews.setViewPadding(C0014R.id.line1, 0, 0, 0, 0);
            if (this.mBuilder.getWhenIfShowing() != 0) {
                if (this.mBuilder.mUseChronometer != 0) {
                }
                remoteViews.setViewVisibility(C0014R.id.time, 0);
                remoteViews.setLong(C0014R.id.time, "setTime", this.mBuilder.getWhenIfShowing());
                i = 1;
            }
            if (i == 0) {
            }
            remoteViews.setViewVisibility(C0014R.id.right_side, i == 0 ? 0 : 8);
            i = C0014R.id.line3;
            if (!z) {
                i2 = 8;
            }
            remoteViews.setViewVisibility(i, i2);
            return remoteViews;
        }

        @RestrictTo({Scope.LIBRARY_GROUP})
        public Bitmap createColoredBitmap(int i, int i2) {
            return createColoredBitmap(i, i2, 0);
        }

        private Bitmap createColoredBitmap(int i, int i2, int i3) {
            i = this.mBuilder.mContext.getResources().getDrawable(i);
            int intrinsicWidth = i3 == 0 ? i.getIntrinsicWidth() : i3;
            if (i3 == 0) {
                i3 = i.getIntrinsicHeight();
            }
            Bitmap createBitmap = Bitmap.createBitmap(intrinsicWidth, i3, Config.ARGB_8888);
            i.setBounds(0, 0, intrinsicWidth, i3);
            if (i2 != 0) {
                i.mutate().setColorFilter(new PorterDuffColorFilter(i2, Mode.SRC_IN));
            }
            i.draw(new Canvas(createBitmap));
            return createBitmap;
        }

        private Bitmap createIconWithBackground(int i, int i2, int i3, int i4) {
            int i5 = C0014R.drawable.notification_icon_background;
            if (i4 == 0) {
                i4 = 0;
            }
            i4 = createColoredBitmap(i5, i4, i2);
            Canvas canvas = new Canvas(i4);
            i = this.mBuilder.mContext.getResources().getDrawable(i).mutate();
            i.setFilterBitmap(true);
            i2 = (i2 - i3) / 2;
            i3 += i2;
            i.setBounds(i2, i2, i3, i3);
            i.setColorFilter(new PorterDuffColorFilter(-1, Mode.SRC_ATOP));
            i.draw(canvas);
            return i4;
        }

        @RestrictTo({Scope.LIBRARY_GROUP})
        public void buildIntoRemoteViews(RemoteViews remoteViews, RemoteViews remoteViews2) {
            hideNormalContent(remoteViews);
            remoteViews.removeAllViews(C0014R.id.notification_main_column);
            remoteViews.addView(C0014R.id.notification_main_column, remoteViews2.clone());
            remoteViews.setViewVisibility(C0014R.id.notification_main_column, 0);
            if (VERSION.SDK_INT >= 21) {
                remoteViews.setViewPadding(C0014R.id.notification_main_column_container, 0, calculateTopPadding(), 0, 0);
            }
        }

        private void hideNormalContent(RemoteViews remoteViews) {
            remoteViews.setViewVisibility(C0014R.id.title, 8);
            remoteViews.setViewVisibility(C0014R.id.text2, 8);
            remoteViews.setViewVisibility(C0014R.id.text, 8);
        }

        private int calculateTopPadding() {
            Resources resources = this.mBuilder.mContext.getResources();
            int dimensionPixelSize = resources.getDimensionPixelSize(C0014R.dimen.notification_top_pad);
            int dimensionPixelSize2 = resources.getDimensionPixelSize(C0014R.dimen.notification_top_pad_large_text);
            float constrain = (constrain(resources.getConfiguration().fontScale, 1.0f, 1.3f) - 1.0f) / 0.29999995f;
            return Math.round(((1.0f - constrain) * ((float) dimensionPixelSize)) + (constrain * ((float) dimensionPixelSize2)));
        }
    }

    public static class BigPictureStyle extends Style {
        private Bitmap mBigLargeIcon;
        private boolean mBigLargeIconSet;
        private Bitmap mPicture;

        public BigPictureStyle(Builder builder) {
            setBuilder(builder);
        }

        public BigPictureStyle setBigContentTitle(CharSequence charSequence) {
            this.mBigContentTitle = Builder.limitCharSequenceLength(charSequence);
            return this;
        }

        public BigPictureStyle setSummaryText(CharSequence charSequence) {
            this.mSummaryText = Builder.limitCharSequenceLength(charSequence);
            this.mSummaryTextSet = true;
            return this;
        }

        public BigPictureStyle bigPicture(Bitmap bitmap) {
            this.mPicture = bitmap;
            return this;
        }

        public BigPictureStyle bigLargeIcon(Bitmap bitmap) {
            this.mBigLargeIcon = bitmap;
            this.mBigLargeIconSet = true;
            return this;
        }

        @RestrictTo({Scope.LIBRARY_GROUP})
        public void apply(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            if (VERSION.SDK_INT >= 16) {
                notificationBuilderWithBuilderAccessor = new android.app.Notification.BigPictureStyle(notificationBuilderWithBuilderAccessor.getBuilder()).setBigContentTitle(this.mBigContentTitle).bigPicture(this.mPicture);
                if (this.mBigLargeIconSet) {
                    notificationBuilderWithBuilderAccessor.bigLargeIcon(this.mBigLargeIcon);
                }
                if (this.mSummaryTextSet) {
                    notificationBuilderWithBuilderAccessor.setSummaryText(this.mSummaryText);
                }
            }
        }
    }

    public static class BigTextStyle extends Style {
        private CharSequence mBigText;

        public BigTextStyle(Builder builder) {
            setBuilder(builder);
        }

        public BigTextStyle setBigContentTitle(CharSequence charSequence) {
            this.mBigContentTitle = Builder.limitCharSequenceLength(charSequence);
            return this;
        }

        public BigTextStyle setSummaryText(CharSequence charSequence) {
            this.mSummaryText = Builder.limitCharSequenceLength(charSequence);
            this.mSummaryTextSet = true;
            return this;
        }

        public BigTextStyle bigText(CharSequence charSequence) {
            this.mBigText = Builder.limitCharSequenceLength(charSequence);
            return this;
        }

        @RestrictTo({Scope.LIBRARY_GROUP})
        public void apply(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            if (VERSION.SDK_INT >= 16) {
                notificationBuilderWithBuilderAccessor = new android.app.Notification.BigTextStyle(notificationBuilderWithBuilderAccessor.getBuilder()).setBigContentTitle(this.mBigContentTitle).bigText(this.mBigText);
                if (this.mSummaryTextSet) {
                    notificationBuilderWithBuilderAccessor.setSummaryText(this.mSummaryText);
                }
            }
        }
    }

    public static final class CarExtender implements Extender {
        @RestrictTo({Scope.LIBRARY_GROUP})
        static final String EXTRA_CAR_EXTENDER = "android.car.EXTENSIONS";
        private static final String EXTRA_COLOR = "app_color";
        private static final String EXTRA_CONVERSATION = "car_conversation";
        @RestrictTo({Scope.LIBRARY_GROUP})
        static final String EXTRA_INVISIBLE_ACTIONS = "invisible_actions";
        private static final String EXTRA_LARGE_ICON = "large_icon";
        private static final String KEY_AUTHOR = "author";
        private static final String KEY_MESSAGES = "messages";
        private static final String KEY_ON_READ = "on_read";
        private static final String KEY_ON_REPLY = "on_reply";
        private static final String KEY_PARTICIPANTS = "participants";
        private static final String KEY_REMOTE_INPUT = "remote_input";
        private static final String KEY_TEXT = "text";
        private static final String KEY_TIMESTAMP = "timestamp";
        private int mColor = 0;
        private Bitmap mLargeIcon;
        private UnreadConversation mUnreadConversation;

        public static class UnreadConversation {
            private final long mLatestTimestamp;
            private final String[] mMessages;
            private final String[] mParticipants;
            private final PendingIntent mReadPendingIntent;
            private final RemoteInput mRemoteInput;
            private final PendingIntent mReplyPendingIntent;

            public static class Builder {
                private long mLatestTimestamp;
                private final List<String> mMessages = new ArrayList();
                private final String mParticipant;
                private PendingIntent mReadPendingIntent;
                private RemoteInput mRemoteInput;
                private PendingIntent mReplyPendingIntent;

                public Builder(String str) {
                    this.mParticipant = str;
                }

                public Builder addMessage(String str) {
                    this.mMessages.add(str);
                    return this;
                }

                public Builder setReplyAction(PendingIntent pendingIntent, RemoteInput remoteInput) {
                    this.mRemoteInput = remoteInput;
                    this.mReplyPendingIntent = pendingIntent;
                    return this;
                }

                public Builder setReadPendingIntent(PendingIntent pendingIntent) {
                    this.mReadPendingIntent = pendingIntent;
                    return this;
                }

                public Builder setLatestTimestamp(long j) {
                    this.mLatestTimestamp = j;
                    return this;
                }

                public UnreadConversation build() {
                    return new UnreadConversation((String[]) this.mMessages.toArray(new String[this.mMessages.size()]), this.mRemoteInput, this.mReplyPendingIntent, this.mReadPendingIntent, new String[]{this.mParticipant}, this.mLatestTimestamp);
                }
            }

            UnreadConversation(String[] strArr, RemoteInput remoteInput, PendingIntent pendingIntent, PendingIntent pendingIntent2, String[] strArr2, long j) {
                this.mMessages = strArr;
                this.mRemoteInput = remoteInput;
                this.mReadPendingIntent = pendingIntent2;
                this.mReplyPendingIntent = pendingIntent;
                this.mParticipants = strArr2;
                this.mLatestTimestamp = j;
            }

            public String[] getMessages() {
                return this.mMessages;
            }

            public RemoteInput getRemoteInput() {
                return this.mRemoteInput;
            }

            public PendingIntent getReplyPendingIntent() {
                return this.mReplyPendingIntent;
            }

            public PendingIntent getReadPendingIntent() {
                return this.mReadPendingIntent;
            }

            public String[] getParticipants() {
                return this.mParticipants;
            }

            public String getParticipant() {
                return this.mParticipants.length > 0 ? this.mParticipants[0] : null;
            }

            public long getLatestTimestamp() {
                return this.mLatestTimestamp;
            }
        }

        public CarExtender(Notification notification) {
            if (VERSION.SDK_INT >= 21) {
                if (NotificationCompat.getExtras(notification) == null) {
                    notification = null;
                } else {
                    notification = NotificationCompat.getExtras(notification).getBundle(EXTRA_CAR_EXTENDER);
                }
                if (notification != null) {
                    this.mLargeIcon = (Bitmap) notification.getParcelable(EXTRA_LARGE_ICON);
                    this.mColor = notification.getInt(EXTRA_COLOR, 0);
                    this.mUnreadConversation = getUnreadConversationFromBundle(notification.getBundle(EXTRA_CONVERSATION));
                }
            }
        }

        @RequiresApi(21)
        private static UnreadConversation getUnreadConversationFromBundle(@Nullable Bundle bundle) {
            Bundle bundle2 = bundle;
            UnreadConversation unreadConversation = null;
            if (bundle2 == null) {
                return null;
            }
            String[] strArr;
            Parcelable[] parcelableArray = bundle2.getParcelableArray(KEY_MESSAGES);
            if (parcelableArray != null) {
                String[] strArr2 = new String[parcelableArray.length];
                Object obj = null;
                for (int i = 0; i < strArr2.length; i++) {
                    if (!(parcelableArray[i] instanceof Bundle)) {
                        break;
                    }
                    strArr2[i] = ((Bundle) parcelableArray[i]).getString(KEY_TEXT);
                    if (strArr2[i] == null) {
                        break;
                    }
                }
                obj = 1;
                if (obj == null) {
                    return null;
                }
                strArr = strArr2;
            } else {
                strArr = null;
            }
            PendingIntent pendingIntent = (PendingIntent) bundle2.getParcelable(KEY_ON_READ);
            PendingIntent pendingIntent2 = (PendingIntent) bundle2.getParcelable(KEY_ON_REPLY);
            RemoteInput remoteInput = (RemoteInput) bundle2.getParcelable(KEY_REMOTE_INPUT);
            String[] stringArray = bundle2.getStringArray(KEY_PARTICIPANTS);
            if (stringArray != null) {
                if (stringArray.length == 1) {
                    if (remoteInput != null) {
                        UnreadConversation remoteInput2 = new RemoteInput(remoteInput.getResultKey(), remoteInput.getLabel(), remoteInput.getChoices(), remoteInput.getAllowFreeFormInput(), remoteInput.getExtras(), null);
                    }
                    return new UnreadConversation(strArr, unreadConversation, pendingIntent2, pendingIntent, stringArray, bundle2.getLong(KEY_TIMESTAMP));
                }
            }
            return null;
        }

        @RequiresApi(21)
        private static Bundle getBundleForUnreadConversation(@NonNull UnreadConversation unreadConversation) {
            Bundle bundle = new Bundle();
            int i = 0;
            String str = (unreadConversation.getParticipants() == null || unreadConversation.getParticipants().length <= 1) ? null : unreadConversation.getParticipants()[0];
            Parcelable[] parcelableArr = new Parcelable[unreadConversation.getMessages().length];
            while (i < parcelableArr.length) {
                Bundle bundle2 = new Bundle();
                bundle2.putString(KEY_TEXT, unreadConversation.getMessages()[i]);
                bundle2.putString(KEY_AUTHOR, str);
                parcelableArr[i] = bundle2;
                i++;
            }
            bundle.putParcelableArray(KEY_MESSAGES, parcelableArr);
            RemoteInput remoteInput = unreadConversation.getRemoteInput();
            if (remoteInput != null) {
                bundle.putParcelable(KEY_REMOTE_INPUT, new android.app.RemoteInput.Builder(remoteInput.getResultKey()).setLabel(remoteInput.getLabel()).setChoices(remoteInput.getChoices()).setAllowFreeFormInput(remoteInput.getAllowFreeFormInput()).addExtras(remoteInput.getExtras()).build());
            }
            bundle.putParcelable(KEY_ON_REPLY, unreadConversation.getReplyPendingIntent());
            bundle.putParcelable(KEY_ON_READ, unreadConversation.getReadPendingIntent());
            bundle.putStringArray(KEY_PARTICIPANTS, unreadConversation.getParticipants());
            bundle.putLong(KEY_TIMESTAMP, unreadConversation.getLatestTimestamp());
            return bundle;
        }

        public Builder extend(Builder builder) {
            if (VERSION.SDK_INT < 21) {
                return builder;
            }
            Bundle bundle = new Bundle();
            if (this.mLargeIcon != null) {
                bundle.putParcelable(EXTRA_LARGE_ICON, this.mLargeIcon);
            }
            if (this.mColor != 0) {
                bundle.putInt(EXTRA_COLOR, this.mColor);
            }
            if (this.mUnreadConversation != null) {
                bundle.putBundle(EXTRA_CONVERSATION, getBundleForUnreadConversation(this.mUnreadConversation));
            }
            builder.getExtras().putBundle(EXTRA_CAR_EXTENDER, bundle);
            return builder;
        }

        public CarExtender setColor(@ColorInt int i) {
            this.mColor = i;
            return this;
        }

        @ColorInt
        public int getColor() {
            return this.mColor;
        }

        public CarExtender setLargeIcon(Bitmap bitmap) {
            this.mLargeIcon = bitmap;
            return this;
        }

        public Bitmap getLargeIcon() {
            return this.mLargeIcon;
        }

        public CarExtender setUnreadConversation(UnreadConversation unreadConversation) {
            this.mUnreadConversation = unreadConversation;
            return this;
        }

        public UnreadConversation getUnreadConversation() {
            return this.mUnreadConversation;
        }
    }

    public static class DecoratedCustomViewStyle extends Style {
        private static final int MAX_ACTION_BUTTONS = 3;

        @RestrictTo({Scope.LIBRARY_GROUP})
        public void apply(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            if (VERSION.SDK_INT >= 24) {
                notificationBuilderWithBuilderAccessor.getBuilder().setStyle(new android.app.Notification.DecoratedCustomViewStyle());
            }
        }

        @RestrictTo({Scope.LIBRARY_GROUP})
        public RemoteViews makeContentView(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            if (VERSION.SDK_INT < 24 && this.mBuilder.getContentView() != null) {
                return createRemoteViews(this.mBuilder.getContentView(), false);
            }
            return null;
        }

        @RestrictTo({Scope.LIBRARY_GROUP})
        public RemoteViews makeBigContentView(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            if (VERSION.SDK_INT >= 24) {
                return null;
            }
            notificationBuilderWithBuilderAccessor = this.mBuilder.getBigContentView();
            if (notificationBuilderWithBuilderAccessor == null) {
                notificationBuilderWithBuilderAccessor = this.mBuilder.getContentView();
            }
            if (notificationBuilderWithBuilderAccessor == null) {
                return null;
            }
            return createRemoteViews(notificationBuilderWithBuilderAccessor, true);
        }

        @RestrictTo({Scope.LIBRARY_GROUP})
        public RemoteViews makeHeadsUpContentView(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            if (VERSION.SDK_INT >= 24) {
                return null;
            }
            RemoteViews remoteViews;
            notificationBuilderWithBuilderAccessor = this.mBuilder.getHeadsUpContentView();
            if (notificationBuilderWithBuilderAccessor != null) {
                remoteViews = notificationBuilderWithBuilderAccessor;
            } else {
                remoteViews = this.mBuilder.getContentView();
            }
            if (notificationBuilderWithBuilderAccessor == null) {
                return null;
            }
            return createRemoteViews(remoteViews, true);
        }

        private RemoteViews createRemoteViews(RemoteViews remoteViews, boolean z) {
            boolean z2 = true;
            int i = 0;
            RemoteViews applyStandardTemplate = applyStandardTemplate(true, C0014R.layout.notification_template_custom_big, false);
            applyStandardTemplate.removeAllViews(C0014R.id.actions);
            if (z && this.mBuilder.mActions) {
                z = Math.min(this.mBuilder.mActions.size(), 3);
                if (z <= false) {
                    for (boolean z3 = false; z3 < z; z3++) {
                        applyStandardTemplate.addView(C0014R.id.actions, generateActionButton((Action) this.mBuilder.mActions.get(z3)));
                    }
                    if (z2) {
                        i = 8;
                    }
                    applyStandardTemplate.setViewVisibility(C0014R.id.actions, i);
                    applyStandardTemplate.setViewVisibility(C0014R.id.action_divider, i);
                    buildIntoRemoteViews(applyStandardTemplate, remoteViews);
                    return applyStandardTemplate;
                }
            }
            z2 = false;
            if (z2) {
                i = 8;
            }
            applyStandardTemplate.setViewVisibility(C0014R.id.actions, i);
            applyStandardTemplate.setViewVisibility(C0014R.id.action_divider, i);
            buildIntoRemoteViews(applyStandardTemplate, remoteViews);
            return applyStandardTemplate;
        }

        private RemoteViews generateActionButton(Action action) {
            Object obj = action.actionIntent == null ? 1 : null;
            RemoteViews remoteViews = new RemoteViews(this.mBuilder.mContext.getPackageName(), obj != null ? C0014R.layout.notification_action_tombstone : C0014R.layout.notification_action);
            remoteViews.setImageViewBitmap(C0014R.id.action_image, createColoredBitmap(action.getIcon(), this.mBuilder.mContext.getResources().getColor(C0014R.color.notification_action_color_filter)));
            remoteViews.setTextViewText(C0014R.id.action_text, action.title);
            if (obj == null) {
                remoteViews.setOnClickPendingIntent(C0014R.id.action_container, action.actionIntent);
            }
            if (VERSION.SDK_INT >= 15) {
                remoteViews.setContentDescription(C0014R.id.action_container, action.title);
            }
            return remoteViews;
        }
    }

    public static class InboxStyle extends Style {
        private ArrayList<CharSequence> mTexts = new ArrayList();

        public InboxStyle(Builder builder) {
            setBuilder(builder);
        }

        public InboxStyle setBigContentTitle(CharSequence charSequence) {
            this.mBigContentTitle = Builder.limitCharSequenceLength(charSequence);
            return this;
        }

        public InboxStyle setSummaryText(CharSequence charSequence) {
            this.mSummaryText = Builder.limitCharSequenceLength(charSequence);
            this.mSummaryTextSet = true;
            return this;
        }

        public InboxStyle addLine(CharSequence charSequence) {
            this.mTexts.add(Builder.limitCharSequenceLength(charSequence));
            return this;
        }

        @RestrictTo({Scope.LIBRARY_GROUP})
        public void apply(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            if (VERSION.SDK_INT >= 16) {
                notificationBuilderWithBuilderAccessor = new android.app.Notification.InboxStyle(notificationBuilderWithBuilderAccessor.getBuilder()).setBigContentTitle(this.mBigContentTitle);
                if (this.mSummaryTextSet) {
                    notificationBuilderWithBuilderAccessor.setSummaryText(this.mSummaryText);
                }
                Iterator it = this.mTexts.iterator();
                while (it.hasNext()) {
                    notificationBuilderWithBuilderAccessor.addLine((CharSequence) it.next());
                }
            }
        }
    }

    public static class MessagingStyle extends Style {
        public static final int MAXIMUM_RETAINED_MESSAGES = 25;
        @Nullable
        private CharSequence mConversationTitle;
        @Nullable
        private Boolean mIsGroupConversation;
        private final List<Message> mMessages = new ArrayList();
        private Person mUser;

        public static final class Message {
            static final String KEY_DATA_MIME_TYPE = "type";
            static final String KEY_DATA_URI = "uri";
            static final String KEY_EXTRAS_BUNDLE = "extras";
            static final String KEY_NOTIFICATION_PERSON = "sender_person";
            static final String KEY_PERSON = "person";
            static final String KEY_SENDER = "sender";
            static final String KEY_TEXT = "text";
            static final String KEY_TIMESTAMP = "time";
            @Nullable
            private String mDataMimeType;
            @Nullable
            private Uri mDataUri;
            private Bundle mExtras;
            @Nullable
            private final Person mPerson;
            private final CharSequence mText;
            private final long mTimestamp;

            public Message(CharSequence charSequence, long j, @Nullable Person person) {
                this.mExtras = new Bundle();
                this.mText = charSequence;
                this.mTimestamp = j;
                this.mPerson = person;
            }

            @Deprecated
            public Message(CharSequence charSequence, long j, CharSequence charSequence2) {
                this(charSequence, j, new android.support.v4.app.Person.Builder().setName(charSequence2).build());
            }

            public Message setData(String str, Uri uri) {
                this.mDataMimeType = str;
                this.mDataUri = uri;
                return this;
            }

            @NonNull
            public CharSequence getText() {
                return this.mText;
            }

            public long getTimestamp() {
                return this.mTimestamp;
            }

            @NonNull
            public Bundle getExtras() {
                return this.mExtras;
            }

            @Nullable
            @Deprecated
            public CharSequence getSender() {
                return this.mPerson == null ? null : this.mPerson.getName();
            }

            @Nullable
            public Person getPerson() {
                return this.mPerson;
            }

            @Nullable
            public String getDataMimeType() {
                return this.mDataMimeType;
            }

            @Nullable
            public Uri getDataUri() {
                return this.mDataUri;
            }

            private Bundle toBundle() {
                Bundle bundle = new Bundle();
                if (this.mText != null) {
                    bundle.putCharSequence(KEY_TEXT, this.mText);
                }
                bundle.putLong(KEY_TIMESTAMP, this.mTimestamp);
                if (this.mPerson != null) {
                    bundle.putCharSequence(KEY_SENDER, this.mPerson.getName());
                    if (VERSION.SDK_INT >= 28) {
                        bundle.putParcelable(KEY_NOTIFICATION_PERSON, this.mPerson.toAndroidPerson());
                    } else {
                        bundle.putBundle(KEY_PERSON, this.mPerson.toBundle());
                    }
                }
                if (this.mDataMimeType != null) {
                    bundle.putString(KEY_DATA_MIME_TYPE, this.mDataMimeType);
                }
                if (this.mDataUri != null) {
                    bundle.putParcelable(KEY_DATA_URI, this.mDataUri);
                }
                if (this.mExtras != null) {
                    bundle.putBundle(KEY_EXTRAS_BUNDLE, this.mExtras);
                }
                return bundle;
            }

            @NonNull
            static Bundle[] getBundleArrayForMessages(List<Message> list) {
                Bundle[] bundleArr = new Bundle[list.size()];
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    bundleArr[i] = ((Message) list.get(i)).toBundle();
                }
                return bundleArr;
            }

            @NonNull
            static List<Message> getMessagesFromBundleArray(Parcelable[] parcelableArr) {
                List<Message> arrayList = new ArrayList(parcelableArr.length);
                for (int i = 0; i < parcelableArr.length; i++) {
                    if (parcelableArr[i] instanceof Bundle) {
                        Message messageFromBundle = getMessageFromBundle((Bundle) parcelableArr[i]);
                        if (messageFromBundle != null) {
                            arrayList.add(messageFromBundle);
                        }
                    }
                }
                return arrayList;
            }

            @android.support.annotation.Nullable
            static android.support.v4.app.NotificationCompat.MessagingStyle.Message getMessageFromBundle(android.os.Bundle r6) {
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
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1603198149.run(Unknown Source)
*/
                /*
                r0 = 0;
                r1 = "text";	 Catch:{ ClassCastException -> 0x00a7 }
                r1 = r6.containsKey(r1);	 Catch:{ ClassCastException -> 0x00a7 }
                if (r1 == 0) goto L_0x00a6;	 Catch:{ ClassCastException -> 0x00a7 }
            L_0x0009:
                r1 = "time";	 Catch:{ ClassCastException -> 0x00a7 }
                r1 = r6.containsKey(r1);	 Catch:{ ClassCastException -> 0x00a7 }
                if (r1 != 0) goto L_0x0013;	 Catch:{ ClassCastException -> 0x00a7 }
            L_0x0011:
                goto L_0x00a6;	 Catch:{ ClassCastException -> 0x00a7 }
            L_0x0013:
                r1 = "person";	 Catch:{ ClassCastException -> 0x00a7 }
                r1 = r6.containsKey(r1);	 Catch:{ ClassCastException -> 0x00a7 }
                if (r1 == 0) goto L_0x0026;	 Catch:{ ClassCastException -> 0x00a7 }
            L_0x001b:
                r1 = "person";	 Catch:{ ClassCastException -> 0x00a7 }
                r1 = r6.getBundle(r1);	 Catch:{ ClassCastException -> 0x00a7 }
                r1 = android.support.v4.app.Person.fromBundle(r1);	 Catch:{ ClassCastException -> 0x00a7 }
                goto L_0x005e;	 Catch:{ ClassCastException -> 0x00a7 }
            L_0x0026:
                r1 = "sender_person";	 Catch:{ ClassCastException -> 0x00a7 }
                r1 = r6.containsKey(r1);	 Catch:{ ClassCastException -> 0x00a7 }
                if (r1 == 0) goto L_0x0041;	 Catch:{ ClassCastException -> 0x00a7 }
            L_0x002e:
                r1 = android.os.Build.VERSION.SDK_INT;	 Catch:{ ClassCastException -> 0x00a7 }
                r2 = 28;	 Catch:{ ClassCastException -> 0x00a7 }
                if (r1 < r2) goto L_0x0041;	 Catch:{ ClassCastException -> 0x00a7 }
            L_0x0034:
                r1 = "sender_person";	 Catch:{ ClassCastException -> 0x00a7 }
                r1 = r6.getParcelable(r1);	 Catch:{ ClassCastException -> 0x00a7 }
                r1 = (android.app.Person) r1;	 Catch:{ ClassCastException -> 0x00a7 }
                r1 = android.support.v4.app.Person.fromAndroidPerson(r1);	 Catch:{ ClassCastException -> 0x00a7 }
                goto L_0x005e;	 Catch:{ ClassCastException -> 0x00a7 }
            L_0x0041:
                r1 = "sender";	 Catch:{ ClassCastException -> 0x00a7 }
                r1 = r6.containsKey(r1);	 Catch:{ ClassCastException -> 0x00a7 }
                if (r1 == 0) goto L_0x005d;	 Catch:{ ClassCastException -> 0x00a7 }
            L_0x0049:
                r1 = new android.support.v4.app.Person$Builder;	 Catch:{ ClassCastException -> 0x00a7 }
                r1.<init>();	 Catch:{ ClassCastException -> 0x00a7 }
                r2 = "sender";	 Catch:{ ClassCastException -> 0x00a7 }
                r2 = r6.getCharSequence(r2);	 Catch:{ ClassCastException -> 0x00a7 }
                r1 = r1.setName(r2);	 Catch:{ ClassCastException -> 0x00a7 }
                r1 = r1.build();	 Catch:{ ClassCastException -> 0x00a7 }
                goto L_0x005e;	 Catch:{ ClassCastException -> 0x00a7 }
            L_0x005d:
                r1 = r0;	 Catch:{ ClassCastException -> 0x00a7 }
            L_0x005e:
                r2 = new android.support.v4.app.NotificationCompat$MessagingStyle$Message;	 Catch:{ ClassCastException -> 0x00a7 }
                r3 = "text";	 Catch:{ ClassCastException -> 0x00a7 }
                r3 = r6.getCharSequence(r3);	 Catch:{ ClassCastException -> 0x00a7 }
                r4 = "time";	 Catch:{ ClassCastException -> 0x00a7 }
                r4 = r6.getLong(r4);	 Catch:{ ClassCastException -> 0x00a7 }
                r2.<init>(r3, r4, r1);	 Catch:{ ClassCastException -> 0x00a7 }
                r1 = "type";	 Catch:{ ClassCastException -> 0x00a7 }
                r1 = r6.containsKey(r1);	 Catch:{ ClassCastException -> 0x00a7 }
                if (r1 == 0) goto L_0x0090;	 Catch:{ ClassCastException -> 0x00a7 }
            L_0x0077:
                r1 = "uri";	 Catch:{ ClassCastException -> 0x00a7 }
                r1 = r6.containsKey(r1);	 Catch:{ ClassCastException -> 0x00a7 }
                if (r1 == 0) goto L_0x0090;	 Catch:{ ClassCastException -> 0x00a7 }
            L_0x007f:
                r1 = "type";	 Catch:{ ClassCastException -> 0x00a7 }
                r1 = r6.getString(r1);	 Catch:{ ClassCastException -> 0x00a7 }
                r3 = "uri";	 Catch:{ ClassCastException -> 0x00a7 }
                r3 = r6.getParcelable(r3);	 Catch:{ ClassCastException -> 0x00a7 }
                r3 = (android.net.Uri) r3;	 Catch:{ ClassCastException -> 0x00a7 }
                r2.setData(r1, r3);	 Catch:{ ClassCastException -> 0x00a7 }
            L_0x0090:
                r1 = "extras";	 Catch:{ ClassCastException -> 0x00a7 }
                r1 = r6.containsKey(r1);	 Catch:{ ClassCastException -> 0x00a7 }
                if (r1 == 0) goto L_0x00a5;	 Catch:{ ClassCastException -> 0x00a7 }
            L_0x0098:
                r1 = r2.getExtras();	 Catch:{ ClassCastException -> 0x00a7 }
                r3 = "extras";	 Catch:{ ClassCastException -> 0x00a7 }
                r6 = r6.getBundle(r3);	 Catch:{ ClassCastException -> 0x00a7 }
                r1.putAll(r6);	 Catch:{ ClassCastException -> 0x00a7 }
            L_0x00a5:
                return r2;
            L_0x00a6:
                return r0;
            L_0x00a7:
                return r0;
                */
                throw new UnsupportedOperationException("Method not decompiled: android.support.v4.app.NotificationCompat.MessagingStyle.Message.getMessageFromBundle(android.os.Bundle):android.support.v4.app.NotificationCompat$MessagingStyle$Message");
            }
        }

        private MessagingStyle() {
        }

        @Deprecated
        public MessagingStyle(@NonNull CharSequence charSequence) {
            this.mUser = new android.support.v4.app.Person.Builder().setName(charSequence).build();
        }

        public MessagingStyle(@NonNull Person person) {
            if (TextUtils.isEmpty(person.getName())) {
                throw new IllegalArgumentException("User's name must not be empty.");
            }
            this.mUser = person;
        }

        @Deprecated
        public CharSequence getUserDisplayName() {
            return this.mUser.getName();
        }

        public Person getUser() {
            return this.mUser;
        }

        public MessagingStyle setConversationTitle(@Nullable CharSequence charSequence) {
            this.mConversationTitle = charSequence;
            return this;
        }

        @Nullable
        public CharSequence getConversationTitle() {
            return this.mConversationTitle;
        }

        @Deprecated
        public MessagingStyle addMessage(CharSequence charSequence, long j, CharSequence charSequence2) {
            this.mMessages.add(new Message(charSequence, j, new android.support.v4.app.Person.Builder().setName(charSequence2).build()));
            if (this.mMessages.size() > 25) {
                this.mMessages.remove(0);
            }
            return this;
        }

        public MessagingStyle addMessage(CharSequence charSequence, long j, Person person) {
            addMessage(new Message(charSequence, j, person));
            return this;
        }

        public MessagingStyle addMessage(Message message) {
            this.mMessages.add(message);
            if (this.mMessages.size() > 25) {
                this.mMessages.remove(0);
            }
            return this;
        }

        public List<Message> getMessages() {
            return this.mMessages;
        }

        public MessagingStyle setGroupConversation(boolean z) {
            this.mIsGroupConversation = Boolean.valueOf(z);
            return this;
        }

        public boolean isGroupConversation() {
            boolean z = false;
            if (this.mBuilder == null || this.mBuilder.mContext.getApplicationInfo().targetSdkVersion >= 28 || this.mIsGroupConversation != null) {
                if (this.mIsGroupConversation != null) {
                    z = this.mIsGroupConversation.booleanValue();
                }
                return z;
            }
            if (this.mConversationTitle != null) {
                z = true;
            }
            return z;
        }

        @android.support.annotation.Nullable
        public static android.support.v4.app.NotificationCompat.MessagingStyle extractMessagingStyleFromNotification(android.app.Notification r2) {
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
            r2 = android.support.v4.app.NotificationCompat.getExtras(r2);
            r0 = 0;
            if (r2 == 0) goto L_0x0018;
        L_0x0007:
            r1 = "android.selfDisplayName";
            r1 = r2.containsKey(r1);
            if (r1 != 0) goto L_0x0018;
        L_0x000f:
            r1 = "android.messagingStyleUser";
            r1 = r2.containsKey(r1);
            if (r1 != 0) goto L_0x0018;
        L_0x0017:
            return r0;
        L_0x0018:
            r1 = new android.support.v4.app.NotificationCompat$MessagingStyle;	 Catch:{ ClassCastException -> 0x0021 }
            r1.<init>();	 Catch:{ ClassCastException -> 0x0021 }
            r1.restoreFromCompatExtras(r2);	 Catch:{ ClassCastException -> 0x0021 }
            return r1;
        L_0x0021:
            return r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.v4.app.NotificationCompat.MessagingStyle.extractMessagingStyleFromNotification(android.app.Notification):android.support.v4.app.NotificationCompat$MessagingStyle");
        }

        @RestrictTo({Scope.LIBRARY_GROUP})
        public void apply(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            setGroupConversation(isGroupConversation());
            if (VERSION.SDK_INT >= 24) {
                android.app.Notification.MessagingStyle messagingStyle;
                if (VERSION.SDK_INT >= 28) {
                    messagingStyle = new android.app.Notification.MessagingStyle(this.mUser.toAndroidPerson());
                } else {
                    messagingStyle = new android.app.Notification.MessagingStyle(this.mUser.getName());
                }
                if (this.mIsGroupConversation.booleanValue() || VERSION.SDK_INT >= 28) {
                    messagingStyle.setConversationTitle(this.mConversationTitle);
                }
                if (VERSION.SDK_INT >= 28) {
                    messagingStyle.setGroupConversation(this.mIsGroupConversation.booleanValue());
                }
                for (Message message : this.mMessages) {
                    android.app.Notification.MessagingStyle.Message message2;
                    if (VERSION.SDK_INT >= 28) {
                        Person person;
                        Person person2 = message.getPerson();
                        CharSequence text = message.getText();
                        long timestamp = message.getTimestamp();
                        if (person2 == null) {
                            person = null;
                        } else {
                            person = person2.toAndroidPerson();
                        }
                        message2 = new android.app.Notification.MessagingStyle.Message(text, timestamp, person);
                    } else {
                        message2 = new android.app.Notification.MessagingStyle.Message(message.getText(), message.getTimestamp(), message.getPerson() != null ? message.getPerson().getName() : null);
                    }
                    if (message.getDataMimeType() != null) {
                        message2.setData(message.getDataMimeType(), message.getDataUri());
                    }
                    messagingStyle.addMessage(message2);
                }
                messagingStyle.setBuilder(notificationBuilderWithBuilderAccessor.getBuilder());
                return;
            }
            CharSequence makeMessageLine;
            Message findLatestIncomingMessage = findLatestIncomingMessage();
            if (this.mConversationTitle != null && this.mIsGroupConversation.booleanValue()) {
                notificationBuilderWithBuilderAccessor.getBuilder().setContentTitle(this.mConversationTitle);
            } else if (findLatestIncomingMessage != null) {
                notificationBuilderWithBuilderAccessor.getBuilder().setContentTitle("");
                if (findLatestIncomingMessage.getPerson() != null) {
                    notificationBuilderWithBuilderAccessor.getBuilder().setContentTitle(findLatestIncomingMessage.getPerson().getName());
                }
            }
            if (findLatestIncomingMessage != null) {
                android.app.Notification.Builder builder = notificationBuilderWithBuilderAccessor.getBuilder();
                if (this.mConversationTitle != null) {
                    makeMessageLine = makeMessageLine(findLatestIncomingMessage);
                } else {
                    makeMessageLine = findLatestIncomingMessage.getText();
                }
                builder.setContentText(makeMessageLine);
            }
            if (VERSION.SDK_INT >= 16) {
                Object obj;
                int size;
                Message message3;
                CharSequence makeMessageLine2;
                makeMessageLine = new SpannableStringBuilder();
                if (this.mConversationTitle == null) {
                    if (!hasMessagesWithoutSender()) {
                        obj = null;
                        for (size = this.mMessages.size() - 1; size >= 0; size--) {
                            message3 = (Message) this.mMessages.get(size);
                            makeMessageLine2 = obj == null ? makeMessageLine(message3) : message3.getText();
                            if (size != this.mMessages.size() - 1) {
                                makeMessageLine.insert(0, "\n");
                            }
                            makeMessageLine.insert(0, makeMessageLine2);
                        }
                        new android.app.Notification.BigTextStyle(notificationBuilderWithBuilderAccessor.getBuilder()).setBigContentTitle(null).bigText(makeMessageLine);
                    }
                }
                obj = 1;
                for (size = this.mMessages.size() - 1; size >= 0; size--) {
                    message3 = (Message) this.mMessages.get(size);
                    if (obj == null) {
                    }
                    if (size != this.mMessages.size() - 1) {
                        makeMessageLine.insert(0, "\n");
                    }
                    makeMessageLine.insert(0, makeMessageLine2);
                }
                new android.app.Notification.BigTextStyle(notificationBuilderWithBuilderAccessor.getBuilder()).setBigContentTitle(null).bigText(makeMessageLine);
            }
        }

        @Nullable
        private Message findLatestIncomingMessage() {
            for (int size = this.mMessages.size() - 1; size >= 0; size--) {
                Message message = (Message) this.mMessages.get(size);
                if (message.getPerson() != null && !TextUtils.isEmpty(message.getPerson().getName())) {
                    return message;
                }
            }
            return !this.mMessages.isEmpty() ? (Message) this.mMessages.get(this.mMessages.size() - 1) : null;
        }

        private boolean hasMessagesWithoutSender() {
            for (int size = this.mMessages.size() - 1; size >= 0; size--) {
                Message message = (Message) this.mMessages.get(size);
                if (message.getPerson() != null && message.getPerson().getName() == null) {
                    return true;
                }
            }
            return false;
        }

        private CharSequence makeMessageLine(Message message) {
            BidiFormatter instance = BidiFormatter.getInstance();
            CharSequence spannableStringBuilder = new SpannableStringBuilder();
            Object obj = VERSION.SDK_INT >= 21 ? 1 : null;
            int i = obj != null ? ViewCompat.MEASURED_STATE_MASK : -1;
            CharSequence name = message.getPerson() == null ? "" : message.getPerson().getName();
            if (TextUtils.isEmpty(name)) {
                name = this.mUser.getName();
                if (!(obj == null || this.mBuilder.getColor() == 0)) {
                    i = this.mBuilder.getColor();
                }
            }
            CharSequence unicodeWrap = instance.unicodeWrap(name);
            spannableStringBuilder.append(unicodeWrap);
            spannableStringBuilder.setSpan(makeFontColorSpan(i), spannableStringBuilder.length() - unicodeWrap.length(), spannableStringBuilder.length(), 33);
            spannableStringBuilder.append("  ").append(instance.unicodeWrap(message.getText() == null ? "" : message.getText()));
            return spannableStringBuilder;
        }

        @NonNull
        private TextAppearanceSpan makeFontColorSpan(int i) {
            return new TextAppearanceSpan(null, 0, 0, ColorStateList.valueOf(i), null);
        }

        public void addCompatExtras(Bundle bundle) {
            super.addCompatExtras(bundle);
            bundle.putCharSequence(NotificationCompat.EXTRA_SELF_DISPLAY_NAME, this.mUser.getName());
            bundle.putBundle(NotificationCompat.EXTRA_MESSAGING_STYLE_USER, this.mUser.toBundle());
            bundle.putCharSequence(NotificationCompat.EXTRA_HIDDEN_CONVERSATION_TITLE, this.mConversationTitle);
            if (this.mConversationTitle != null && this.mIsGroupConversation.booleanValue()) {
                bundle.putCharSequence(NotificationCompat.EXTRA_CONVERSATION_TITLE, this.mConversationTitle);
            }
            if (!this.mMessages.isEmpty()) {
                bundle.putParcelableArray(NotificationCompat.EXTRA_MESSAGES, Message.getBundleArrayForMessages(this.mMessages));
            }
            if (this.mIsGroupConversation != null) {
                bundle.putBoolean(NotificationCompat.EXTRA_IS_GROUP_CONVERSATION, this.mIsGroupConversation.booleanValue());
            }
        }

        @RestrictTo({Scope.LIBRARY_GROUP})
        protected void restoreFromCompatExtras(Bundle bundle) {
            this.mMessages.clear();
            if (bundle.containsKey(NotificationCompat.EXTRA_MESSAGING_STYLE_USER)) {
                this.mUser = Person.fromBundle(bundle.getBundle(NotificationCompat.EXTRA_MESSAGING_STYLE_USER));
            } else {
                this.mUser = new android.support.v4.app.Person.Builder().setName(bundle.getString(NotificationCompat.EXTRA_SELF_DISPLAY_NAME)).build();
            }
            this.mConversationTitle = bundle.getCharSequence(NotificationCompat.EXTRA_CONVERSATION_TITLE);
            if (this.mConversationTitle == null) {
                this.mConversationTitle = bundle.getCharSequence(NotificationCompat.EXTRA_HIDDEN_CONVERSATION_TITLE);
            }
            Parcelable[] parcelableArray = bundle.getParcelableArray(NotificationCompat.EXTRA_MESSAGES);
            if (parcelableArray != null) {
                this.mMessages.addAll(Message.getMessagesFromBundleArray(parcelableArray));
            }
            if (bundle.containsKey(NotificationCompat.EXTRA_IS_GROUP_CONVERSATION)) {
                this.mIsGroupConversation = Boolean.valueOf(bundle.getBoolean(NotificationCompat.EXTRA_IS_GROUP_CONVERSATION));
            }
        }
    }

    public static final class WearableExtender implements Extender {
        private static final int DEFAULT_CONTENT_ICON_GRAVITY = 8388613;
        private static final int DEFAULT_FLAGS = 1;
        private static final int DEFAULT_GRAVITY = 80;
        private static final String EXTRA_WEARABLE_EXTENSIONS = "android.wearable.EXTENSIONS";
        private static final int FLAG_BIG_PICTURE_AMBIENT = 32;
        private static final int FLAG_CONTENT_INTENT_AVAILABLE_OFFLINE = 1;
        private static final int FLAG_HINT_AVOID_BACKGROUND_CLIPPING = 16;
        private static final int FLAG_HINT_CONTENT_INTENT_LAUNCHES_ACTIVITY = 64;
        private static final int FLAG_HINT_HIDE_ICON = 2;
        private static final int FLAG_HINT_SHOW_BACKGROUND_ONLY = 4;
        private static final int FLAG_START_SCROLL_BOTTOM = 8;
        private static final String KEY_ACTIONS = "actions";
        private static final String KEY_BACKGROUND = "background";
        private static final String KEY_BRIDGE_TAG = "bridgeTag";
        private static final String KEY_CONTENT_ACTION_INDEX = "contentActionIndex";
        private static final String KEY_CONTENT_ICON = "contentIcon";
        private static final String KEY_CONTENT_ICON_GRAVITY = "contentIconGravity";
        private static final String KEY_CUSTOM_CONTENT_HEIGHT = "customContentHeight";
        private static final String KEY_CUSTOM_SIZE_PRESET = "customSizePreset";
        private static final String KEY_DISMISSAL_ID = "dismissalId";
        private static final String KEY_DISPLAY_INTENT = "displayIntent";
        private static final String KEY_FLAGS = "flags";
        private static final String KEY_GRAVITY = "gravity";
        private static final String KEY_HINT_SCREEN_TIMEOUT = "hintScreenTimeout";
        private static final String KEY_PAGES = "pages";
        public static final int SCREEN_TIMEOUT_LONG = -1;
        public static final int SCREEN_TIMEOUT_SHORT = 0;
        public static final int SIZE_DEFAULT = 0;
        public static final int SIZE_FULL_SCREEN = 5;
        public static final int SIZE_LARGE = 4;
        public static final int SIZE_MEDIUM = 3;
        public static final int SIZE_SMALL = 2;
        public static final int SIZE_XSMALL = 1;
        public static final int UNSET_ACTION_INDEX = -1;
        private ArrayList<Action> mActions = new ArrayList();
        private Bitmap mBackground;
        private String mBridgeTag;
        private int mContentActionIndex = -1;
        private int mContentIcon;
        private int mContentIconGravity = 8388613;
        private int mCustomContentHeight;
        private int mCustomSizePreset = 0;
        private String mDismissalId;
        private PendingIntent mDisplayIntent;
        private int mFlags = 1;
        private int mGravity = 80;
        private int mHintScreenTimeout;
        private ArrayList<Notification> mPages = new ArrayList();

        public WearableExtender(Notification notification) {
            notification = NotificationCompat.getExtras(notification);
            notification = notification != null ? notification.getBundle(EXTRA_WEARABLE_EXTENSIONS) : null;
            if (notification != null) {
                ArrayList parcelableArrayList = notification.getParcelableArrayList(KEY_ACTIONS);
                if (VERSION.SDK_INT >= 16 && parcelableArrayList != null) {
                    Action[] actionArr = new Action[parcelableArrayList.size()];
                    for (int i = 0; i < actionArr.length; i++) {
                        if (VERSION.SDK_INT >= 20) {
                            actionArr[i] = NotificationCompat.getActionCompatFromAction((android.app.Notification.Action) parcelableArrayList.get(i));
                        } else if (VERSION.SDK_INT >= 16) {
                            actionArr[i] = NotificationCompatJellybean.getActionFromBundle((Bundle) parcelableArrayList.get(i));
                        }
                    }
                    Collections.addAll(this.mActions, actionArr);
                }
                this.mFlags = notification.getInt(KEY_FLAGS, 1);
                this.mDisplayIntent = (PendingIntent) notification.getParcelable(KEY_DISPLAY_INTENT);
                Notification[] notificationArrayFromBundle = NotificationCompat.getNotificationArrayFromBundle(notification, KEY_PAGES);
                if (notificationArrayFromBundle != null) {
                    Collections.addAll(this.mPages, notificationArrayFromBundle);
                }
                this.mBackground = (Bitmap) notification.getParcelable(KEY_BACKGROUND);
                this.mContentIcon = notification.getInt(KEY_CONTENT_ICON);
                this.mContentIconGravity = notification.getInt(KEY_CONTENT_ICON_GRAVITY, 8388613);
                this.mContentActionIndex = notification.getInt(KEY_CONTENT_ACTION_INDEX, -1);
                this.mCustomSizePreset = notification.getInt(KEY_CUSTOM_SIZE_PRESET, 0);
                this.mCustomContentHeight = notification.getInt(KEY_CUSTOM_CONTENT_HEIGHT);
                this.mGravity = notification.getInt(KEY_GRAVITY, 80);
                this.mHintScreenTimeout = notification.getInt(KEY_HINT_SCREEN_TIMEOUT);
                this.mDismissalId = notification.getString(KEY_DISMISSAL_ID);
                this.mBridgeTag = notification.getString(KEY_BRIDGE_TAG);
            }
        }

        public Builder extend(Builder builder) {
            Bundle bundle = new Bundle();
            if (!this.mActions.isEmpty()) {
                if (VERSION.SDK_INT >= 16) {
                    ArrayList arrayList = new ArrayList(this.mActions.size());
                    Iterator it = this.mActions.iterator();
                    while (it.hasNext()) {
                        Action action = (Action) it.next();
                        if (VERSION.SDK_INT >= 20) {
                            arrayList.add(getActionFromActionCompat(action));
                        } else if (VERSION.SDK_INT >= 16) {
                            arrayList.add(NotificationCompatJellybean.getBundleForAction(action));
                        }
                    }
                    bundle.putParcelableArrayList(KEY_ACTIONS, arrayList);
                } else {
                    bundle.putParcelableArrayList(KEY_ACTIONS, null);
                }
            }
            if (this.mFlags != 1) {
                bundle.putInt(KEY_FLAGS, this.mFlags);
            }
            if (this.mDisplayIntent != null) {
                bundle.putParcelable(KEY_DISPLAY_INTENT, this.mDisplayIntent);
            }
            if (!this.mPages.isEmpty()) {
                bundle.putParcelableArray(KEY_PAGES, (Parcelable[]) this.mPages.toArray(new Notification[this.mPages.size()]));
            }
            if (this.mBackground != null) {
                bundle.putParcelable(KEY_BACKGROUND, this.mBackground);
            }
            if (this.mContentIcon != 0) {
                bundle.putInt(KEY_CONTENT_ICON, this.mContentIcon);
            }
            if (this.mContentIconGravity != 8388613) {
                bundle.putInt(KEY_CONTENT_ICON_GRAVITY, this.mContentIconGravity);
            }
            if (this.mContentActionIndex != -1) {
                bundle.putInt(KEY_CONTENT_ACTION_INDEX, this.mContentActionIndex);
            }
            if (this.mCustomSizePreset != 0) {
                bundle.putInt(KEY_CUSTOM_SIZE_PRESET, this.mCustomSizePreset);
            }
            if (this.mCustomContentHeight != 0) {
                bundle.putInt(KEY_CUSTOM_CONTENT_HEIGHT, this.mCustomContentHeight);
            }
            if (this.mGravity != 80) {
                bundle.putInt(KEY_GRAVITY, this.mGravity);
            }
            if (this.mHintScreenTimeout != 0) {
                bundle.putInt(KEY_HINT_SCREEN_TIMEOUT, this.mHintScreenTimeout);
            }
            if (this.mDismissalId != null) {
                bundle.putString(KEY_DISMISSAL_ID, this.mDismissalId);
            }
            if (this.mBridgeTag != null) {
                bundle.putString(KEY_BRIDGE_TAG, this.mBridgeTag);
            }
            builder.getExtras().putBundle(EXTRA_WEARABLE_EXTENSIONS, bundle);
            return builder;
        }

        @RequiresApi(20)
        private static android.app.Notification.Action getActionFromActionCompat(Action action) {
            Bundle bundle;
            android.app.Notification.Action.Builder builder = new android.app.Notification.Action.Builder(action.getIcon(), action.getTitle(), action.getActionIntent());
            if (action.getExtras() != null) {
                bundle = new Bundle(action.getExtras());
            } else {
                bundle = new Bundle();
            }
            bundle.putBoolean("android.support.allowGeneratedReplies", action.getAllowGeneratedReplies());
            if (VERSION.SDK_INT >= 24) {
                builder.setAllowGeneratedReplies(action.getAllowGeneratedReplies());
            }
            builder.addExtras(bundle);
            RemoteInput[] remoteInputs = action.getRemoteInputs();
            if (remoteInputs != null) {
                for (RemoteInput addRemoteInput : RemoteInput.fromCompat(remoteInputs)) {
                    builder.addRemoteInput(addRemoteInput);
                }
            }
            return builder.build();
        }

        public WearableExtender clone() {
            WearableExtender wearableExtender = new WearableExtender();
            wearableExtender.mActions = new ArrayList(this.mActions);
            wearableExtender.mFlags = this.mFlags;
            wearableExtender.mDisplayIntent = this.mDisplayIntent;
            wearableExtender.mPages = new ArrayList(this.mPages);
            wearableExtender.mBackground = this.mBackground;
            wearableExtender.mContentIcon = this.mContentIcon;
            wearableExtender.mContentIconGravity = this.mContentIconGravity;
            wearableExtender.mContentActionIndex = this.mContentActionIndex;
            wearableExtender.mCustomSizePreset = this.mCustomSizePreset;
            wearableExtender.mCustomContentHeight = this.mCustomContentHeight;
            wearableExtender.mGravity = this.mGravity;
            wearableExtender.mHintScreenTimeout = this.mHintScreenTimeout;
            wearableExtender.mDismissalId = this.mDismissalId;
            wearableExtender.mBridgeTag = this.mBridgeTag;
            return wearableExtender;
        }

        public WearableExtender addAction(Action action) {
            this.mActions.add(action);
            return this;
        }

        public WearableExtender addActions(List<Action> list) {
            this.mActions.addAll(list);
            return this;
        }

        public WearableExtender clearActions() {
            this.mActions.clear();
            return this;
        }

        public List<Action> getActions() {
            return this.mActions;
        }

        public WearableExtender setDisplayIntent(PendingIntent pendingIntent) {
            this.mDisplayIntent = pendingIntent;
            return this;
        }

        public PendingIntent getDisplayIntent() {
            return this.mDisplayIntent;
        }

        public WearableExtender addPage(Notification notification) {
            this.mPages.add(notification);
            return this;
        }

        public WearableExtender addPages(List<Notification> list) {
            this.mPages.addAll(list);
            return this;
        }

        public WearableExtender clearPages() {
            this.mPages.clear();
            return this;
        }

        public List<Notification> getPages() {
            return this.mPages;
        }

        public WearableExtender setBackground(Bitmap bitmap) {
            this.mBackground = bitmap;
            return this;
        }

        public Bitmap getBackground() {
            return this.mBackground;
        }

        @Deprecated
        public WearableExtender setContentIcon(int i) {
            this.mContentIcon = i;
            return this;
        }

        @Deprecated
        public int getContentIcon() {
            return this.mContentIcon;
        }

        @Deprecated
        public WearableExtender setContentIconGravity(int i) {
            this.mContentIconGravity = i;
            return this;
        }

        @Deprecated
        public int getContentIconGravity() {
            return this.mContentIconGravity;
        }

        public WearableExtender setContentAction(int i) {
            this.mContentActionIndex = i;
            return this;
        }

        public int getContentAction() {
            return this.mContentActionIndex;
        }

        @Deprecated
        public WearableExtender setGravity(int i) {
            this.mGravity = i;
            return this;
        }

        @Deprecated
        public int getGravity() {
            return this.mGravity;
        }

        @Deprecated
        public WearableExtender setCustomSizePreset(int i) {
            this.mCustomSizePreset = i;
            return this;
        }

        @Deprecated
        public int getCustomSizePreset() {
            return this.mCustomSizePreset;
        }

        @Deprecated
        public WearableExtender setCustomContentHeight(int i) {
            this.mCustomContentHeight = i;
            return this;
        }

        @Deprecated
        public int getCustomContentHeight() {
            return this.mCustomContentHeight;
        }

        public WearableExtender setStartScrollBottom(boolean z) {
            setFlag(8, z);
            return this;
        }

        public boolean getStartScrollBottom() {
            return (this.mFlags & 8) != 0;
        }

        public WearableExtender setContentIntentAvailableOffline(boolean z) {
            setFlag(1, z);
            return this;
        }

        public boolean getContentIntentAvailableOffline() {
            return (this.mFlags & 1) != 0;
        }

        @Deprecated
        public WearableExtender setHintHideIcon(boolean z) {
            setFlag(2, z);
            return this;
        }

        @Deprecated
        public boolean getHintHideIcon() {
            return (this.mFlags & 2) != 0;
        }

        @Deprecated
        public WearableExtender setHintShowBackgroundOnly(boolean z) {
            setFlag(4, z);
            return this;
        }

        @Deprecated
        public boolean getHintShowBackgroundOnly() {
            return (this.mFlags & 4) != 0;
        }

        @Deprecated
        public WearableExtender setHintAvoidBackgroundClipping(boolean z) {
            setFlag(16, z);
            return this;
        }

        @Deprecated
        public boolean getHintAvoidBackgroundClipping() {
            return (this.mFlags & 16) != 0;
        }

        @Deprecated
        public WearableExtender setHintScreenTimeout(int i) {
            this.mHintScreenTimeout = i;
            return this;
        }

        @Deprecated
        public int getHintScreenTimeout() {
            return this.mHintScreenTimeout;
        }

        public WearableExtender setHintAmbientBigPicture(boolean z) {
            setFlag(32, z);
            return this;
        }

        public boolean getHintAmbientBigPicture() {
            return (this.mFlags & 32) != 0;
        }

        public WearableExtender setHintContentIntentLaunchesActivity(boolean z) {
            setFlag(64, z);
            return this;
        }

        public boolean getHintContentIntentLaunchesActivity() {
            return (this.mFlags & 64) != 0;
        }

        public WearableExtender setDismissalId(String str) {
            this.mDismissalId = str;
            return this;
        }

        public String getDismissalId() {
            return this.mDismissalId;
        }

        public WearableExtender setBridgeTag(String str) {
            this.mBridgeTag = str;
            return this;
        }

        public String getBridgeTag() {
            return this.mBridgeTag;
        }

        private void setFlag(int i, boolean z) {
            if (z) {
                this.mFlags = i | this.mFlags;
                return;
            }
            this.mFlags = (~i) & this.mFlags;
        }
    }

    static Notification[] getNotificationArrayFromBundle(Bundle bundle, String str) {
        Parcelable[] parcelableArray = bundle.getParcelableArray(str);
        if (!(parcelableArray instanceof Notification[])) {
            if (parcelableArray != null) {
                Object[] objArr = new Notification[parcelableArray.length];
                for (int i = 0; i < parcelableArray.length; i++) {
                    objArr[i] = (Notification) parcelableArray[i];
                }
                bundle.putParcelableArray(str, objArr);
                return objArr;
            }
        }
        return (Notification[]) parcelableArray;
    }

    @Nullable
    public static Bundle getExtras(Notification notification) {
        if (VERSION.SDK_INT >= 19) {
            return notification.extras;
        }
        return VERSION.SDK_INT >= 16 ? NotificationCompatJellybean.getExtras(notification) : null;
    }

    public static int getActionCount(Notification notification) {
        int i = 0;
        if (VERSION.SDK_INT >= 19) {
            if (notification.actions != null) {
                i = notification.actions.length;
            }
            return i;
        } else if (VERSION.SDK_INT >= 16) {
            return NotificationCompatJellybean.getActionCount(notification);
        } else {
            return 0;
        }
    }

    public static Action getAction(Notification notification, int i) {
        if (VERSION.SDK_INT >= 20) {
            return getActionCompatFromAction(notification.actions[i]);
        }
        Bundle bundle = null;
        if (VERSION.SDK_INT >= 19) {
            android.app.Notification.Action action = notification.actions[i];
            notification = notification.extras.getSparseParcelableArray(NotificationCompatExtras.EXTRA_ACTION_EXTRAS);
            if (notification != null) {
                bundle = (Bundle) notification.get(i);
            }
            return NotificationCompatJellybean.readAction(action.icon, action.title, action.actionIntent, bundle);
        } else if (VERSION.SDK_INT >= 16) {
            return NotificationCompatJellybean.getAction(notification, i);
        } else {
            return null;
        }
    }

    @RequiresApi(20)
    static Action getActionCompatFromAction(android.app.Notification.Action action) {
        RemoteInput[] remoteInputArr;
        boolean z;
        int semanticAction;
        RemoteInput[] remoteInputs = action.getRemoteInputs();
        if (remoteInputs == null) {
            remoteInputArr = null;
        } else {
            RemoteInput[] remoteInputArr2 = new RemoteInput[remoteInputs.length];
            for (int i = 0; i < remoteInputs.length; i++) {
                RemoteInput remoteInput = remoteInputs[i];
                remoteInputArr2[i] = new RemoteInput(remoteInput.getResultKey(), remoteInput.getLabel(), remoteInput.getChoices(), remoteInput.getAllowFreeFormInput(), remoteInput.getExtras(), null);
            }
            remoteInputArr = remoteInputArr2;
        }
        if (VERSION.SDK_INT >= 24) {
            if (!action.getExtras().getBoolean("android.support.allowGeneratedReplies")) {
                if (!action.getAllowGeneratedReplies()) {
                    z = false;
                }
            }
            z = true;
        } else {
            z = action.getExtras().getBoolean("android.support.allowGeneratedReplies");
        }
        boolean z2 = z;
        boolean z3 = action.getExtras().getBoolean("android.support.action.showsUserInterface", true);
        if (VERSION.SDK_INT >= 28) {
            semanticAction = action.getSemanticAction();
        } else {
            semanticAction = action.getExtras().getInt("android.support.action.semanticAction", 0);
        }
        return new Action(action.icon, action.title, action.actionIntent, action.getExtras(), remoteInputArr, null, z2, semanticAction, z3);
    }

    @RequiresApi(21)
    public static List<Action> getInvisibleActions(Notification notification) {
        List<Action> arrayList = new ArrayList();
        notification = notification.extras.getBundle("android.car.EXTENSIONS");
        if (notification == null) {
            return arrayList;
        }
        notification = notification.getBundle("invisible_actions");
        if (notification != null) {
            for (int i = 0; i < notification.size(); i++) {
                arrayList.add(NotificationCompatJellybean.getActionFromBundle(notification.getBundle(Integer.toString(i))));
            }
        }
        return arrayList;
    }

    @RequiresApi(19)
    public static CharSequence getContentTitle(Notification notification) {
        return notification.extras.getCharSequence(EXTRA_TITLE);
    }

    public static String getCategory(Notification notification) {
        return VERSION.SDK_INT >= 21 ? notification.category : null;
    }

    public static boolean getLocalOnly(Notification notification) {
        boolean z = false;
        if (VERSION.SDK_INT >= 20) {
            if ((notification.flags & 256) != null) {
                z = true;
            }
            return z;
        } else if (VERSION.SDK_INT >= 19) {
            return notification.extras.getBoolean(NotificationCompatExtras.EXTRA_LOCAL_ONLY);
        } else {
            if (VERSION.SDK_INT >= 16) {
                return NotificationCompatJellybean.getExtras(notification).getBoolean(NotificationCompatExtras.EXTRA_LOCAL_ONLY);
            }
            return false;
        }
    }

    public static String getGroup(Notification notification) {
        if (VERSION.SDK_INT >= 20) {
            return notification.getGroup();
        }
        if (VERSION.SDK_INT >= 19) {
            return notification.extras.getString(NotificationCompatExtras.EXTRA_GROUP_KEY);
        }
        return VERSION.SDK_INT >= 16 ? NotificationCompatJellybean.getExtras(notification).getString(NotificationCompatExtras.EXTRA_GROUP_KEY) : null;
    }

    public static boolean isGroupSummary(Notification notification) {
        boolean z = false;
        if (VERSION.SDK_INT >= 20) {
            if ((notification.flags & 512) != null) {
                z = true;
            }
            return z;
        } else if (VERSION.SDK_INT >= 19) {
            return notification.extras.getBoolean(NotificationCompatExtras.EXTRA_GROUP_SUMMARY);
        } else {
            if (VERSION.SDK_INT >= 16) {
                return NotificationCompatJellybean.getExtras(notification).getBoolean(NotificationCompatExtras.EXTRA_GROUP_SUMMARY);
            }
            return false;
        }
    }

    public static String getSortKey(Notification notification) {
        if (VERSION.SDK_INT >= 20) {
            return notification.getSortKey();
        }
        if (VERSION.SDK_INT >= 19) {
            return notification.extras.getString(NotificationCompatExtras.EXTRA_SORT_KEY);
        }
        return VERSION.SDK_INT >= 16 ? NotificationCompatJellybean.getExtras(notification).getString(NotificationCompatExtras.EXTRA_SORT_KEY) : null;
    }

    public static String getChannelId(Notification notification) {
        return VERSION.SDK_INT >= 26 ? notification.getChannelId() : null;
    }

    public static long getTimeoutAfter(Notification notification) {
        return VERSION.SDK_INT >= 26 ? notification.getTimeoutAfter() : 0;
    }

    public static int getBadgeIconType(Notification notification) {
        return VERSION.SDK_INT >= 26 ? notification.getBadgeIconType() : null;
    }

    public static String getShortcutId(Notification notification) {
        return VERSION.SDK_INT >= 26 ? notification.getShortcutId() : null;
    }

    public static int getGroupAlertBehavior(Notification notification) {
        return VERSION.SDK_INT >= 26 ? notification.getGroupAlertBehavior() : null;
    }
}
