package android.support.v4.media.session;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.BroadcastReceiver.PendingResult;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build.VERSION;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v4.media.MediaBrowserCompat.ConnectionCallback;
import android.support.v4.media.MediaBrowserServiceCompat;
import android.util.Log;
import android.view.KeyEvent;
import java.util.List;

public class MediaButtonReceiver extends BroadcastReceiver {
    private static final String TAG = "MediaButtonReceiver";

    private static class MediaButtonConnectionCallback extends ConnectionCallback {
        private final Context mContext;
        private final Intent mIntent;
        private MediaBrowserCompat mMediaBrowser;
        private final PendingResult mPendingResult;

        MediaButtonConnectionCallback(Context context, Intent intent, PendingResult pendingResult) {
            this.mContext = context;
            this.mIntent = intent;
            this.mPendingResult = pendingResult;
        }

        void setMediaBrowser(MediaBrowserCompat mediaBrowserCompat) {
            this.mMediaBrowser = mediaBrowserCompat;
        }

        public void onConnected() {
            try {
                new MediaControllerCompat(this.mContext, this.mMediaBrowser.getSessionToken()).dispatchMediaButtonEvent((KeyEvent) this.mIntent.getParcelableExtra("android.intent.extra.KEY_EVENT"));
            } catch (Throwable e) {
                Log.e(MediaButtonReceiver.TAG, "Failed to create a media controller", e);
            }
            finish();
        }

        public void onConnectionSuspended() {
            finish();
        }

        public void onConnectionFailed() {
            finish();
        }

        private void finish() {
            this.mMediaBrowser.disconnect();
            this.mPendingResult.finish();
        }
    }

    public void onReceive(Context context, Intent intent) {
        if (intent != null && "android.intent.action.MEDIA_BUTTON".equals(intent.getAction())) {
            if (intent.hasExtra("android.intent.extra.KEY_EVENT")) {
                ComponentName serviceComponentByAction = getServiceComponentByAction(context, "android.intent.action.MEDIA_BUTTON");
                if (serviceComponentByAction != null) {
                    intent.setComponent(serviceComponentByAction);
                    startForegroundService(context, intent);
                    return;
                }
                serviceComponentByAction = getServiceComponentByAction(context, MediaBrowserServiceCompat.SERVICE_INTERFACE);
                if (serviceComponentByAction != null) {
                    PendingResult goAsync = goAsync();
                    context = context.getApplicationContext();
                    ConnectionCallback mediaButtonConnectionCallback = new MediaButtonConnectionCallback(context, intent, goAsync);
                    intent = new MediaBrowserCompat(context, serviceComponentByAction, mediaButtonConnectionCallback, null);
                    mediaButtonConnectionCallback.setMediaBrowser(intent);
                    intent.connect();
                    return;
                }
                throw new IllegalStateException("Could not find any Service that handles android.intent.action.MEDIA_BUTTON or implements a media browser service.");
            }
        }
        context = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Ignore unsupported intent: ");
        stringBuilder.append(intent);
        Log.d(context, stringBuilder.toString());
    }

    public static KeyEvent handleIntent(MediaSessionCompat mediaSessionCompat, Intent intent) {
        if (!(mediaSessionCompat == null || intent == null || !"android.intent.action.MEDIA_BUTTON".equals(intent.getAction()))) {
            if (intent.hasExtra("android.intent.extra.KEY_EVENT")) {
                KeyEvent keyEvent = (KeyEvent) intent.getParcelableExtra("android.intent.extra.KEY_EVENT");
                mediaSessionCompat.getController().dispatchMediaButtonEvent(keyEvent);
                return keyEvent;
            }
        }
        return null;
    }

    public static PendingIntent buildMediaButtonPendingIntent(Context context, long j) {
        ComponentName mediaButtonReceiverComponent = getMediaButtonReceiverComponent(context);
        if (mediaButtonReceiverComponent != null) {
            return buildMediaButtonPendingIntent(context, mediaButtonReceiverComponent, j);
        }
        Log.w(TAG, "A unique media button receiver could not be found in the given context, so couldn't build a pending intent.");
        return null;
    }

    public static PendingIntent buildMediaButtonPendingIntent(Context context, ComponentName componentName, long j) {
        if (componentName == null) {
            Log.w(TAG, "The component name of media button receiver should be provided.");
            return null;
        }
        int toKeyCode = PlaybackStateCompat.toKeyCode(j);
        if (toKeyCode == 0) {
            context = TAG;
            componentName = new StringBuilder();
            componentName.append("Cannot build a media button pending intent with the given action: ");
            componentName.append(j);
            Log.w(context, componentName.toString());
            return null;
        }
        j = new Intent("android.intent.action.MEDIA_BUTTON");
        j.setComponent(componentName);
        j.putExtra("android.intent.extra.KEY_EVENT", new KeyEvent(0, toKeyCode));
        return PendingIntent.getBroadcast(context, toKeyCode, j, 0);
    }

    static ComponentName getMediaButtonReceiverComponent(Context context) {
        Intent intent = new Intent("android.intent.action.MEDIA_BUTTON");
        intent.setPackage(context.getPackageName());
        context = context.getPackageManager().queryBroadcastReceivers(intent, 0);
        if (context.size() == 1) {
            ResolveInfo resolveInfo = (ResolveInfo) context.get(0);
            return new ComponentName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name);
        }
        if (context.size() > 1) {
            Log.w(TAG, "More than one BroadcastReceiver that handles android.intent.action.MEDIA_BUTTON was found, returning null.");
        }
        return null;
    }

    private static void startForegroundService(Context context, Intent intent) {
        if (VERSION.SDK_INT >= 26) {
            context.startForegroundService(intent);
        } else {
            context.startService(intent);
        }
    }

    private static ComponentName getServiceComponentByAction(Context context, String str) {
        PackageManager packageManager = context.getPackageManager();
        Intent intent = new Intent(str);
        intent.setPackage(context.getPackageName());
        List queryIntentServices = packageManager.queryIntentServices(intent, 0);
        if (queryIntentServices.size() == 1) {
            ResolveInfo resolveInfo = (ResolveInfo) queryIntentServices.get(0);
            return new ComponentName(resolveInfo.serviceInfo.packageName, resolveInfo.serviceInfo.name);
        } else if (queryIntentServices.isEmpty() != null) {
            return null;
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Expected 1 service that handles ");
            stringBuilder.append(str);
            stringBuilder.append(", found ");
            stringBuilder.append(queryIntentServices.size());
            throw new IllegalStateException(stringBuilder.toString());
        }
    }
}
