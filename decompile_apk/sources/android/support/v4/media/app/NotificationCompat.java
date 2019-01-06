package android.support.v4.media.app;

import android.app.Notification;
import android.app.PendingIntent;
import android.media.session.MediaSession;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.mediacompat.C0038R;
import android.support.v4.app.BundleCompat;
import android.support.v4.app.NotificationBuilderWithBuilderAccessor;
import android.support.v4.app.NotificationCompat.Action;
import android.support.v4.app.NotificationCompat.Builder;
import android.support.v4.app.NotificationCompat.Style;
import android.support.v4.media.session.MediaSessionCompat.Token;
import android.widget.RemoteViews;

public class NotificationCompat {

    public static class MediaStyle extends Style {
        private static final int MAX_MEDIA_BUTTONS = 5;
        private static final int MAX_MEDIA_BUTTONS_IN_COMPACT = 3;
        int[] mActionsToShowInCompact = null;
        PendingIntent mCancelButtonIntent;
        boolean mShowCancelButton;
        Token mToken;

        public static Token getMediaSession(Notification notification) {
            notification = android.support.v4.app.NotificationCompat.getExtras(notification);
            if (notification != null) {
                if (VERSION.SDK_INT >= 21) {
                    notification = notification.getParcelable(android.support.v4.app.NotificationCompat.EXTRA_MEDIA_SESSION);
                    if (notification != null) {
                        return Token.fromToken(notification);
                    }
                }
                notification = BundleCompat.getBinder(notification, android.support.v4.app.NotificationCompat.EXTRA_MEDIA_SESSION);
                if (notification != null) {
                    Parcel obtain = Parcel.obtain();
                    obtain.writeStrongBinder(notification);
                    obtain.setDataPosition(null);
                    Token token = (Token) Token.CREATOR.createFromParcel(obtain);
                    obtain.recycle();
                    return token;
                }
            }
            return null;
        }

        public MediaStyle(Builder builder) {
            setBuilder(builder);
        }

        public MediaStyle setShowActionsInCompactView(int... iArr) {
            this.mActionsToShowInCompact = iArr;
            return this;
        }

        public MediaStyle setMediaSession(Token token) {
            this.mToken = token;
            return this;
        }

        public MediaStyle setShowCancelButton(boolean z) {
            if (VERSION.SDK_INT < 21) {
                this.mShowCancelButton = z;
            }
            return this;
        }

        public MediaStyle setCancelButtonIntent(PendingIntent pendingIntent) {
            this.mCancelButtonIntent = pendingIntent;
            return this;
        }

        @RestrictTo({Scope.LIBRARY_GROUP})
        public void apply(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            if (VERSION.SDK_INT >= 21) {
                notificationBuilderWithBuilderAccessor.getBuilder().setStyle(fillInMediaStyle(new android.app.Notification.MediaStyle()));
            } else if (this.mShowCancelButton) {
                notificationBuilderWithBuilderAccessor.getBuilder().setOngoing(true);
            }
        }

        @RequiresApi(21)
        android.app.Notification.MediaStyle fillInMediaStyle(android.app.Notification.MediaStyle mediaStyle) {
            if (this.mActionsToShowInCompact != null) {
                mediaStyle.setShowActionsInCompactView(this.mActionsToShowInCompact);
            }
            if (this.mToken != null) {
                mediaStyle.setMediaSession((MediaSession.Token) this.mToken.getToken());
            }
            return mediaStyle;
        }

        @RestrictTo({Scope.LIBRARY_GROUP})
        public RemoteViews makeContentView(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            if (VERSION.SDK_INT >= 21) {
                return null;
            }
            return generateContentView();
        }

        RemoteViews generateContentView() {
            int i;
            RemoteViews applyStandardTemplate = applyStandardTemplate(false, getContentViewLayoutResource(), true);
            int size = this.mBuilder.mActions.size();
            if (this.mActionsToShowInCompact == null) {
                i = 0;
            } else {
                i = Math.min(this.mActionsToShowInCompact.length, 3);
            }
            applyStandardTemplate.removeAllViews(C0038R.id.media_actions);
            if (i > 0) {
                int i2 = 0;
                while (i2 < i) {
                    if (i2 < size) {
                        applyStandardTemplate.addView(C0038R.id.media_actions, generateMediaActionButton((Action) this.mBuilder.mActions.get(this.mActionsToShowInCompact[i2])));
                        i2++;
                    } else {
                        throw new IllegalArgumentException(String.format("setShowActionsInCompactView: action %d out of bounds (max %d)", new Object[]{Integer.valueOf(i2), Integer.valueOf(size - 1)}));
                    }
                }
            }
            if (this.mShowCancelButton) {
                applyStandardTemplate.setViewVisibility(C0038R.id.end_padder, 8);
                applyStandardTemplate.setViewVisibility(C0038R.id.cancel_action, 0);
                applyStandardTemplate.setOnClickPendingIntent(C0038R.id.cancel_action, this.mCancelButtonIntent);
                applyStandardTemplate.setInt(C0038R.id.cancel_action, "setAlpha", this.mBuilder.mContext.getResources().getInteger(C0038R.integer.cancel_button_image_alpha));
            } else {
                applyStandardTemplate.setViewVisibility(C0038R.id.end_padder, 0);
                applyStandardTemplate.setViewVisibility(C0038R.id.cancel_action, 8);
            }
            return applyStandardTemplate;
        }

        private RemoteViews generateMediaActionButton(Action action) {
            Object obj = action.getActionIntent() == null ? 1 : null;
            RemoteViews remoteViews = new RemoteViews(this.mBuilder.mContext.getPackageName(), C0038R.layout.notification_media_action);
            remoteViews.setImageViewResource(C0038R.id.action0, action.getIcon());
            if (obj == null) {
                remoteViews.setOnClickPendingIntent(C0038R.id.action0, action.getActionIntent());
            }
            if (VERSION.SDK_INT >= 15) {
                remoteViews.setContentDescription(C0038R.id.action0, action.getTitle());
            }
            return remoteViews;
        }

        int getContentViewLayoutResource() {
            return C0038R.layout.notification_template_media;
        }

        @RestrictTo({Scope.LIBRARY_GROUP})
        public RemoteViews makeBigContentView(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            if (VERSION.SDK_INT >= 21) {
                return null;
            }
            return generateBigContentView();
        }

        RemoteViews generateBigContentView() {
            int min = Math.min(this.mBuilder.mActions.size(), 5);
            RemoteViews applyStandardTemplate = applyStandardTemplate(false, getBigContentViewLayoutResource(min), false);
            applyStandardTemplate.removeAllViews(C0038R.id.media_actions);
            if (min > 0) {
                for (int i = 0; i < min; i++) {
                    applyStandardTemplate.addView(C0038R.id.media_actions, generateMediaActionButton((Action) this.mBuilder.mActions.get(i)));
                }
            }
            if (this.mShowCancelButton) {
                applyStandardTemplate.setViewVisibility(C0038R.id.cancel_action, 0);
                applyStandardTemplate.setInt(C0038R.id.cancel_action, "setAlpha", this.mBuilder.mContext.getResources().getInteger(C0038R.integer.cancel_button_image_alpha));
                applyStandardTemplate.setOnClickPendingIntent(C0038R.id.cancel_action, this.mCancelButtonIntent);
            } else {
                applyStandardTemplate.setViewVisibility(C0038R.id.cancel_action, 8);
            }
            return applyStandardTemplate;
        }

        int getBigContentViewLayoutResource(int i) {
            return i <= 3 ? C0038R.layout.notification_template_big_media_narrow : C0038R.layout.notification_template_big_media;
        }
    }

    public static class DecoratedMediaCustomViewStyle extends MediaStyle {
        @RestrictTo({Scope.LIBRARY_GROUP})
        public void apply(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            if (VERSION.SDK_INT >= 24) {
                notificationBuilderWithBuilderAccessor.getBuilder().setStyle(fillInMediaStyle(new android.app.Notification.DecoratedMediaCustomViewStyle()));
            } else {
                super.apply(notificationBuilderWithBuilderAccessor);
            }
        }

        @RestrictTo({Scope.LIBRARY_GROUP})
        public RemoteViews makeContentView(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            if (VERSION.SDK_INT >= 24) {
                return null;
            }
            Object obj = null;
            notificationBuilderWithBuilderAccessor = this.mBuilder.getContentView() != null ? true : null;
            if (VERSION.SDK_INT >= 21) {
                if (!(notificationBuilderWithBuilderAccessor == null && this.mBuilder.getBigContentView() == null)) {
                    obj = 1;
                }
                if (obj != null) {
                    RemoteViews generateContentView = generateContentView();
                    if (notificationBuilderWithBuilderAccessor != null) {
                        buildIntoRemoteViews(generateContentView, this.mBuilder.getContentView());
                    }
                    setBackgroundColor(generateContentView);
                    return generateContentView;
                }
            }
            RemoteViews generateContentView2 = generateContentView();
            if (notificationBuilderWithBuilderAccessor != null) {
                buildIntoRemoteViews(generateContentView2, this.mBuilder.getContentView());
                return generateContentView2;
            }
            return null;
        }

        int getContentViewLayoutResource() {
            if (this.mBuilder.getContentView() != null) {
                return C0038R.layout.notification_template_media_custom;
            }
            return super.getContentViewLayoutResource();
        }

        @RestrictTo({Scope.LIBRARY_GROUP})
        public RemoteViews makeBigContentView(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            if (VERSION.SDK_INT >= 24) {
                return null;
            }
            if (this.mBuilder.getBigContentView() != null) {
                notificationBuilderWithBuilderAccessor = this.mBuilder.getBigContentView();
            } else {
                notificationBuilderWithBuilderAccessor = this.mBuilder.getContentView();
            }
            if (notificationBuilderWithBuilderAccessor == null) {
                return null;
            }
            RemoteViews generateBigContentView = generateBigContentView();
            buildIntoRemoteViews(generateBigContentView, notificationBuilderWithBuilderAccessor);
            if (VERSION.SDK_INT >= 21) {
                setBackgroundColor(generateBigContentView);
            }
            return generateBigContentView;
        }

        int getBigContentViewLayoutResource(int i) {
            return i <= 3 ? C0038R.layout.notification_template_big_media_narrow_custom : C0038R.layout.notification_template_big_media_custom;
        }

        @RestrictTo({Scope.LIBRARY_GROUP})
        public RemoteViews makeHeadsUpContentView(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            if (VERSION.SDK_INT >= 24) {
                return null;
            }
            if (this.mBuilder.getHeadsUpContentView() != null) {
                notificationBuilderWithBuilderAccessor = this.mBuilder.getHeadsUpContentView();
            } else {
                notificationBuilderWithBuilderAccessor = this.mBuilder.getContentView();
            }
            if (notificationBuilderWithBuilderAccessor == null) {
                return null;
            }
            RemoteViews generateBigContentView = generateBigContentView();
            buildIntoRemoteViews(generateBigContentView, notificationBuilderWithBuilderAccessor);
            if (VERSION.SDK_INT >= 21) {
                setBackgroundColor(generateBigContentView);
            }
            return generateBigContentView;
        }

        private void setBackgroundColor(RemoteViews remoteViews) {
            int color;
            if (this.mBuilder.getColor() != 0) {
                color = this.mBuilder.getColor();
            } else {
                color = this.mBuilder.mContext.getResources().getColor(C0038R.color.notification_material_background_media_default_color);
            }
            remoteViews.setInt(C0038R.id.status_bar_latest_event_content, "setBackgroundColor", color);
        }
    }

    private NotificationCompat() {
    }
}
