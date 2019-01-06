package android.support.v4.media;

import android.content.Context;
import android.media.browse.MediaBrowser.MediaItem;
import android.os.Bundle;
import android.os.Parcel;
import android.service.media.MediaBrowserService;
import android.service.media.MediaBrowserService.Result;
import android.support.annotation.RequiresApi;
import android.util.Log;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@RequiresApi(26)
class MediaBrowserServiceCompatApi26 {
    private static final String TAG = "MBSCompatApi26";
    private static Field sResultFlags;

    static class ResultWrapper {
        Result mResultObj;

        ResultWrapper(Result result) {
            this.mResultObj = result;
        }

        public void sendResult(List<Parcel> list, int i) {
            try {
                MediaBrowserServiceCompatApi26.sResultFlags.setInt(this.mResultObj, i);
            } catch (int i2) {
                Log.w(MediaBrowserServiceCompatApi26.TAG, i2);
            }
            this.mResultObj.sendResult(parcelListToItemList(list));
        }

        public void detach() {
            this.mResultObj.detach();
        }

        List<MediaItem> parcelListToItemList(List<Parcel> list) {
            if (list == null) {
                return null;
            }
            List<MediaItem> arrayList = new ArrayList();
            for (Parcel parcel : list) {
                parcel.setDataPosition(0);
                arrayList.add(MediaItem.CREATOR.createFromParcel(parcel));
                parcel.recycle();
            }
            return arrayList;
        }
    }

    static class MediaBrowserServiceAdaptor extends MediaBrowserServiceAdaptor {
        MediaBrowserServiceAdaptor(Context context, ServiceCompatProxy serviceCompatProxy) {
            super(context, serviceCompatProxy);
        }

        public void onLoadChildren(String str, Result<List<MediaItem>> result, Bundle bundle) {
            ((ServiceCompatProxy) this.mServiceProxy).onLoadChildren(str, new ResultWrapper(result), bundle);
        }
    }

    public interface ServiceCompatProxy extends android.support.v4.media.MediaBrowserServiceCompatApi23.ServiceCompatProxy {
        void onLoadChildren(String str, ResultWrapper resultWrapper, Bundle bundle);
    }

    MediaBrowserServiceCompatApi26() {
    }

    static {
        try {
            sResultFlags = Result.class.getDeclaredField("mFlags");
            sResultFlags.setAccessible(true);
        } catch (Throwable e) {
            Log.w(TAG, e);
        }
    }

    public static Object createService(Context context, ServiceCompatProxy serviceCompatProxy) {
        return new MediaBrowserServiceAdaptor(context, serviceCompatProxy);
    }

    public static void notifyChildrenChanged(Object obj, String str, Bundle bundle) {
        ((MediaBrowserService) obj).notifyChildrenChanged(str, bundle);
    }

    public static Bundle getBrowserRootHints(Object obj) {
        return ((MediaBrowserService) obj).getBrowserRootHints();
    }
}
