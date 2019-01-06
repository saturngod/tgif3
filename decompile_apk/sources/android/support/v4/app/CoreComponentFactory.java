package android.support.v4.app;

import android.app.Activity;
import android.app.AppComponentFactory;
import android.app.Application;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ContentProvider;
import android.content.Intent;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;

@RequiresApi(api = 28)
@RestrictTo({Scope.LIBRARY_GROUP})
public class CoreComponentFactory extends AppComponentFactory {
    private static final String TAG = "CoreComponentFactory";

    @RestrictTo({Scope.LIBRARY_GROUP})
    public interface CompatWrapped {
        Object getWrapper();
    }

    public Activity instantiateActivity(ClassLoader classLoader, String str, Intent intent) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        return (Activity) checkCompatWrapper(super.instantiateActivity(classLoader, str, intent));
    }

    public Application instantiateApplication(ClassLoader classLoader, String str) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        return (Application) checkCompatWrapper(super.instantiateApplication(classLoader, str));
    }

    public BroadcastReceiver instantiateReceiver(ClassLoader classLoader, String str, Intent intent) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        return (BroadcastReceiver) checkCompatWrapper(super.instantiateReceiver(classLoader, str, intent));
    }

    public ContentProvider instantiateProvider(ClassLoader classLoader, String str) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        return (ContentProvider) checkCompatWrapper(super.instantiateProvider(classLoader, str));
    }

    public Service instantiateService(ClassLoader classLoader, String str, Intent intent) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        return (Service) checkCompatWrapper(super.instantiateService(classLoader, str, intent));
    }

    static <T> T checkCompatWrapper(T t) {
        if (t instanceof CompatWrapped) {
            T wrapper = ((CompatWrapped) t).getWrapper();
            if (wrapper != null) {
                return wrapper;
            }
        }
        return t;
    }
}
