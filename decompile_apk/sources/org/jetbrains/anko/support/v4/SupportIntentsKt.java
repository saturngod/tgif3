package org.jetbrains.anko.support.v4;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NotificationCompat;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.anko.IntentsKt;
import org.jetbrains.anko.internals.AnkoInternals;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000H\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0001\u001a&\u0010\u0006\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u0004\u001aN\u0010\t\u001a\u00020\n\"\n\b\u0000\u0010\u000b\u0018\u0001*\u00020\f*\u00020\u00022.\u0010\r\u001a\u0018\u0012\u0014\b\u0001\u0012\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000f0\u000e\"\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000fH\b¢\u0006\u0002\u0010\u0010\u001a\u0012\u0010\u0011\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u0004\u001a\u001c\u0010\u0013\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u0004\u001a\u001c\u0010\u0014\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\b\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u0004\u001aN\u0010\u0015\u001a\u00020\u0016\"\n\b\u0000\u0010\u000b\u0018\u0001*\u00020\u0017*\u00020\u00022.\u0010\r\u001a\u0018\u0012\u0014\b\u0001\u0012\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000f0\u000e\"\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000fH\b¢\u0006\u0002\u0010\u0018\u001aV\u0010\u0019\u001a\u00020\u0016\"\n\b\u0000\u0010\u000b\u0018\u0001*\u00020\u0017*\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u001b2.\u0010\r\u001a\u0018\u0012\u0014\b\u0001\u0012\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000f0\u000e\"\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000fH\b¢\u0006\u0002\u0010\u001c\u001aN\u0010\u001d\u001a\u00020\u0016\"\n\b\u0000\u0010\u000b\u0018\u0001*\u00020\u001e*\u00020\u00022.\u0010\r\u001a\u0018\u0012\u0014\b\u0001\u0012\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000f0\u000e\"\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000fH\b¢\u0006\u0002\u0010\u0018\u001aN\u0010\u001f\u001a\u00020\u0016\"\n\b\u0000\u0010\u000b\u0018\u0001*\u00020\u001e*\u00020\u00022.\u0010\r\u001a\u0018\u0012\u0014\b\u0001\u0012\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000f0\u000e\"\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000fH\b¢\u0006\u0002\u0010\u0018¨\u0006 "}, d2 = {"browse", "", "Landroid/support/v4/app/Fragment;", "url", "", "newTask", "email", "subject", "text", "intentFor", "Landroid/content/Intent;", "T", "", "params", "", "Lkotlin/Pair;", "(Landroid/support/v4/app/Fragment;[Lkotlin/Pair;)Landroid/content/Intent;", "makeCall", "number", "sendSMS", "share", "startActivity", "", "Landroid/app/Activity;", "(Landroid/support/v4/app/Fragment;[Lkotlin/Pair;)V", "startActivityForResult", "requestCode", "", "(Landroid/support/v4/app/Fragment;I[Lkotlin/Pair;)V", "startService", "Landroid/app/Service;", "stopService", "supportV4-base_release"}, k = 2, mv = {1, 1, 13})
/* compiled from: SupportIntents.kt */
public final class SupportIntentsKt {
    public static final boolean browse(@NotNull Fragment fragment, @NotNull String str, boolean z) {
        Intrinsics.checkParameterIsNotNull(fragment, "receiver$0");
        Intrinsics.checkParameterIsNotNull(str, "url");
        fragment = fragment.requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(fragment, "requireActivity()");
        return IntentsKt.browse((Context) fragment, str, z);
    }

    public static /* synthetic */ boolean browse$default(Fragment fragment, String str, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return browse(fragment, str, z);
    }

    public static final boolean share(@NotNull Fragment fragment, @NotNull String str, @NotNull String str2) {
        Intrinsics.checkParameterIsNotNull(fragment, "receiver$0");
        Intrinsics.checkParameterIsNotNull(str, "text");
        Intrinsics.checkParameterIsNotNull(str2, "subject");
        fragment = fragment.requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(fragment, "requireActivity()");
        return IntentsKt.share((Context) fragment, str, str2);
    }

    public static /* synthetic */ boolean share$default(Fragment fragment, String str, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = "";
        }
        return share(fragment, str, str2);
    }

    public static /* synthetic */ boolean email$default(Fragment fragment, String str, String str2, String str3, int i, Object obj) {
        if ((i & 2) != null) {
            str2 = "";
        }
        if ((i & 4) != 0) {
            str3 = "";
        }
        return email(fragment, str, str2, str3);
    }

    public static final boolean email(@NotNull Fragment fragment, @NotNull String str, @NotNull String str2, @NotNull String str3) {
        Intrinsics.checkParameterIsNotNull(fragment, "receiver$0");
        Intrinsics.checkParameterIsNotNull(str, NotificationCompat.CATEGORY_EMAIL);
        Intrinsics.checkParameterIsNotNull(str2, "subject");
        Intrinsics.checkParameterIsNotNull(str3, "text");
        fragment = fragment.requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(fragment, "requireActivity()");
        return IntentsKt.email((Context) fragment, str, str2, str3);
    }

    public static final boolean makeCall(@NotNull Fragment fragment, @NotNull String str) {
        Intrinsics.checkParameterIsNotNull(fragment, "receiver$0");
        Intrinsics.checkParameterIsNotNull(str, "number");
        fragment = fragment.requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(fragment, "requireActivity()");
        return IntentsKt.makeCall((Context) fragment, str);
    }

    public static final boolean sendSMS(@NotNull Fragment fragment, @NotNull String str, @NotNull String str2) {
        Intrinsics.checkParameterIsNotNull(fragment, "receiver$0");
        Intrinsics.checkParameterIsNotNull(str, "number");
        Intrinsics.checkParameterIsNotNull(str2, "text");
        fragment = fragment.requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(fragment, "requireActivity()");
        return IntentsKt.sendSMS((Context) fragment, str, str2);
    }

    public static /* synthetic */ boolean sendSMS$default(Fragment fragment, String str, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = "";
        }
        return sendSMS(fragment, str, str2);
    }

    private static final <T extends Activity> void startActivity(@NotNull Fragment fragment, Pair<String, ? extends Object>... pairArr) {
        fragment = fragment.requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(fragment, "requireActivity()");
        Context context = (Context) fragment;
        Intrinsics.reifiedOperationMarker(4, "T");
        AnkoInternals.internalStartActivity(context, Activity.class, pairArr);
    }

    private static final <T extends Activity> void startActivityForResult(@NotNull Fragment fragment, int i, Pair<String, ? extends Object>... pairArr) {
        FragmentActivity requireActivity = fragment.requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(requireActivity, "requireActivity()");
        Context context = requireActivity;
        Intrinsics.reifiedOperationMarker(4, "T");
        fragment.startActivityForResult(AnkoInternals.createIntent(context, Activity.class, pairArr), i);
    }

    private static final <T extends Service> void startService(@NotNull Fragment fragment, Pair<String, ? extends Object>... pairArr) {
        fragment = fragment.requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(fragment, "requireActivity()");
        Context context = (Context) fragment;
        Intrinsics.reifiedOperationMarker(4, "T");
        AnkoInternals.internalStartService(context, Service.class, pairArr);
    }

    private static final <T extends Service> void stopService(@NotNull Fragment fragment, Pair<String, ? extends Object>... pairArr) {
        fragment = fragment.requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(fragment, "requireActivity()");
        Context context = (Context) fragment;
        Intrinsics.reifiedOperationMarker(4, "T");
        AnkoInternals.internalStopService(context, Service.class, pairArr);
    }

    private static final <T> Intent intentFor(@NotNull Fragment fragment, Pair<String, ? extends Object>... pairArr) {
        fragment = fragment.requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(fragment, "requireActivity()");
        Context context = (Context) fragment;
        Intrinsics.reifiedOperationMarker(4, "T");
        return AnkoInternals.createIntent(context, Object.class, pairArr);
    }
}
