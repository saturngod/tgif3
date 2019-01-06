package org.jetbrains.anko;

import android.accounts.AccountManager;
import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.AppOpsManager;
import android.app.DownloadManager;
import android.app.KeyguardManager;
import android.app.NotificationManager;
import android.app.SearchManager;
import android.app.UiModeManager;
import android.app.WallpaperManager;
import android.app.admin.DevicePolicyManager;
import android.app.usage.NetworkStatsManager;
import android.app.usage.StorageStatsManager;
import android.app.usage.UsageStatsManager;
import android.bluetooth.BluetoothManager;
import android.companion.CompanionDeviceManager;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.RestrictionsManager;
import android.content.pm.ShortcutManager;
import android.hardware.ConsumerIrManager;
import android.hardware.SensorManager;
import android.hardware.camera2.CameraManager;
import android.hardware.display.DisplayManager;
import android.hardware.fingerprint.FingerprintManager;
import android.hardware.input.InputManager;
import android.hardware.usb.UsbManager;
import android.location.LocationManager;
import android.media.AudioManager;
import android.media.midi.MidiManager;
import android.media.projection.MediaProjectionManager;
import android.media.session.MediaSessionManager;
import android.media.tv.TvInputManager;
import android.net.ConnectivityManager;
import android.net.nsd.NsdManager;
import android.net.wifi.WifiManager;
import android.net.wifi.aware.WifiAwareManager;
import android.net.wifi.p2p.WifiP2pManager;
import android.nfc.NfcManager;
import android.os.BatteryManager;
import android.os.HardwarePropertiesManager;
import android.os.PowerManager;
import android.os.UserManager;
import android.os.health.SystemHealthManager;
import android.os.storage.StorageManager;
import android.print.PrintManager;
import android.support.v4.app.NotificationCompat;
import android.telecom.TelecomManager;
import android.telephony.CarrierConfigManager;
import android.telephony.TelephonyManager;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.CaptioningManager;
import android.view.inputmethod.InputMethodManager;
import android.view.textclassifier.TextClassificationManager;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000®\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0015\u0010\u0005\u001a\u00020\u0006*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b\"\u0015\u0010\t\u001a\u00020\n*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f\"\u0015\u0010\r\u001a\u00020\u000e*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010\"\u0015\u0010\u0011\u001a\u00020\u0012*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014\"\u0015\u0010\u0015\u001a\u00020\u0016*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018\"\u0015\u0010\u0019\u001a\u00020\u001a*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001c\"\u0015\u0010\u001d\u001a\u00020\u001e*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u001f\u0010 \"\u0015\u0010!\u001a\u00020\"*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b#\u0010$\"\u0015\u0010%\u001a\u00020&*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b'\u0010(\"\u0015\u0010)\u001a\u00020**\u00020\u00028F¢\u0006\u0006\u001a\u0004\b+\u0010,\"\u0015\u0010-\u001a\u00020.*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b/\u00100\"\u0015\u00101\u001a\u000202*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b3\u00104\"\u0015\u00105\u001a\u000206*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b7\u00108\"\u0015\u00109\u001a\u00020:*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b;\u0010<\"\u0015\u0010=\u001a\u00020>*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b?\u0010@\"\u0015\u0010A\u001a\u00020B*\u00020\u00028F¢\u0006\u0006\u001a\u0004\bC\u0010D\"\u0015\u0010E\u001a\u00020F*\u00020\u00028F¢\u0006\u0006\u001a\u0004\bG\u0010H\"\u0015\u0010I\u001a\u00020J*\u00020\u00028F¢\u0006\u0006\u001a\u0004\bK\u0010L\"\u0015\u0010M\u001a\u00020N*\u00020\u00028F¢\u0006\u0006\u001a\u0004\bO\u0010P\"\u0015\u0010Q\u001a\u00020R*\u00020\u00028F¢\u0006\u0006\u001a\u0004\bS\u0010T\"\u0015\u0010U\u001a\u00020V*\u00020\u00028F¢\u0006\u0006\u001a\u0004\bW\u0010X\"\u0015\u0010Y\u001a\u00020Z*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b[\u0010\\\"\u0015\u0010]\u001a\u00020^*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b_\u0010`\"\u0015\u0010a\u001a\u00020b*\u00020\u00028F¢\u0006\u0006\u001a\u0004\bc\u0010d\"\u0015\u0010e\u001a\u00020f*\u00020\u00028F¢\u0006\u0006\u001a\u0004\bg\u0010h\"\u0015\u0010i\u001a\u00020j*\u00020\u00028F¢\u0006\u0006\u001a\u0004\bk\u0010l\"\u0015\u0010m\u001a\u00020n*\u00020\u00028F¢\u0006\u0006\u001a\u0004\bo\u0010p\"\u0015\u0010q\u001a\u00020r*\u00020\u00028F¢\u0006\u0006\u001a\u0004\bs\u0010t\"\u0015\u0010u\u001a\u00020v*\u00020\u00028F¢\u0006\u0006\u001a\u0004\bw\u0010x\"\u0015\u0010y\u001a\u00020z*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b{\u0010|\"\u0016\u0010}\u001a\u00020~*\u00020\u00028F¢\u0006\u0007\u001a\u0005\b\u0010\u0001\"\u0019\u0010\u0001\u001a\u00030\u0001*\u00020\u00028F¢\u0006\b\u001a\u0006\b\u0001\u0010\u0001\"\u0019\u0010\u0001\u001a\u00030\u0001*\u00020\u00028F¢\u0006\b\u001a\u0006\b\u0001\u0010\u0001\"\u0019\u0010\u0001\u001a\u00030\u0001*\u00020\u00028F¢\u0006\b\u001a\u0006\b\u0001\u0010\u0001\"\u0019\u0010\u0001\u001a\u00030\u0001*\u00020\u00028F¢\u0006\b\u001a\u0006\b\u0001\u0010\u0001\"\u0019\u0010\u0001\u001a\u00030\u0001*\u00020\u00028F¢\u0006\b\u001a\u0006\b\u0001\u0010\u0001\"\u0019\u0010\u0001\u001a\u00030\u0001*\u00020\u00028F¢\u0006\b\u001a\u0006\b\u0001\u0010\u0001\"\u0019\u0010\u0001\u001a\u00030\u0001*\u00020\u00028F¢\u0006\b\u001a\u0006\b\u0001\u0010\u0001\"\u0019\u0010\u0001\u001a\u00030\u0001*\u00020\u00028F¢\u0006\b\u001a\u0006\b\u0001\u0010 \u0001\"\u0019\u0010¡\u0001\u001a\u00030¢\u0001*\u00020\u00028F¢\u0006\b\u001a\u0006\b£\u0001\u0010¤\u0001\"\u0019\u0010¥\u0001\u001a\u00030¦\u0001*\u00020\u00028F¢\u0006\b\u001a\u0006\b§\u0001\u0010¨\u0001\"\u0019\u0010©\u0001\u001a\u00030ª\u0001*\u00020\u00028F¢\u0006\b\u001a\u0006\b«\u0001\u0010¬\u0001\"\u0019\u0010­\u0001\u001a\u00030®\u0001*\u00020\u00028F¢\u0006\b\u001a\u0006\b¯\u0001\u0010°\u0001\"\u0019\u0010±\u0001\u001a\u00030²\u0001*\u00020\u00028F¢\u0006\b\u001a\u0006\b³\u0001\u0010´\u0001\"\u0019\u0010µ\u0001\u001a\u00030¶\u0001*\u00020\u00028F¢\u0006\b\u001a\u0006\b·\u0001\u0010¸\u0001\"\u0019\u0010¹\u0001\u001a\u00030º\u0001*\u00020\u00028F¢\u0006\b\u001a\u0006\b»\u0001\u0010¼\u0001\"\u0019\u0010½\u0001\u001a\u00030¾\u0001*\u00020\u00028F¢\u0006\b\u001a\u0006\b¿\u0001\u0010À\u0001\"\u0019\u0010Á\u0001\u001a\u00030Â\u0001*\u00020\u00028F¢\u0006\b\u001a\u0006\bÃ\u0001\u0010Ä\u0001\"\u0019\u0010Å\u0001\u001a\u00030Æ\u0001*\u00020\u00028F¢\u0006\b\u001a\u0006\bÇ\u0001\u0010È\u0001\"\u0019\u0010É\u0001\u001a\u00030Ê\u0001*\u00020\u00028F¢\u0006\b\u001a\u0006\bË\u0001\u0010Ì\u0001\"\u0019\u0010Í\u0001\u001a\u00030Î\u0001*\u00020\u00028F¢\u0006\b\u001a\u0006\bÏ\u0001\u0010Ð\u0001\"\u0019\u0010Ñ\u0001\u001a\u00030Ò\u0001*\u00020\u00028F¢\u0006\b\u001a\u0006\bÓ\u0001\u0010Ô\u0001¨\u0006Õ\u0001"}, d2 = {"accessibilityManager", "Landroid/view/accessibility/AccessibilityManager;", "Landroid/content/Context;", "getAccessibilityManager", "(Landroid/content/Context;)Landroid/view/accessibility/AccessibilityManager;", "accountManager", "Landroid/accounts/AccountManager;", "getAccountManager", "(Landroid/content/Context;)Landroid/accounts/AccountManager;", "activityManager", "Landroid/app/ActivityManager;", "getActivityManager", "(Landroid/content/Context;)Landroid/app/ActivityManager;", "alarmManager", "Landroid/app/AlarmManager;", "getAlarmManager", "(Landroid/content/Context;)Landroid/app/AlarmManager;", "appOpsManager", "Landroid/app/AppOpsManager;", "getAppOpsManager", "(Landroid/content/Context;)Landroid/app/AppOpsManager;", "audioManager", "Landroid/media/AudioManager;", "getAudioManager", "(Landroid/content/Context;)Landroid/media/AudioManager;", "batteryManager", "Landroid/os/BatteryManager;", "getBatteryManager", "(Landroid/content/Context;)Landroid/os/BatteryManager;", "bluetoothManager", "Landroid/bluetooth/BluetoothManager;", "getBluetoothManager", "(Landroid/content/Context;)Landroid/bluetooth/BluetoothManager;", "cameraManager", "Landroid/hardware/camera2/CameraManager;", "getCameraManager", "(Landroid/content/Context;)Landroid/hardware/camera2/CameraManager;", "captioningManager", "Landroid/view/accessibility/CaptioningManager;", "getCaptioningManager", "(Landroid/content/Context;)Landroid/view/accessibility/CaptioningManager;", "carrierConfigManager", "Landroid/telephony/CarrierConfigManager;", "getCarrierConfigManager", "(Landroid/content/Context;)Landroid/telephony/CarrierConfigManager;", "clipboardManager", "Landroid/content/ClipboardManager;", "getClipboardManager", "(Landroid/content/Context;)Landroid/content/ClipboardManager;", "companionDeviceManager", "Landroid/companion/CompanionDeviceManager;", "getCompanionDeviceManager", "(Landroid/content/Context;)Landroid/companion/CompanionDeviceManager;", "connectivityManager", "Landroid/net/ConnectivityManager;", "getConnectivityManager", "(Landroid/content/Context;)Landroid/net/ConnectivityManager;", "consumerIrManager", "Landroid/hardware/ConsumerIrManager;", "getConsumerIrManager", "(Landroid/content/Context;)Landroid/hardware/ConsumerIrManager;", "devicePolicyManager", "Landroid/app/admin/DevicePolicyManager;", "getDevicePolicyManager", "(Landroid/content/Context;)Landroid/app/admin/DevicePolicyManager;", "displayManager", "Landroid/hardware/display/DisplayManager;", "getDisplayManager", "(Landroid/content/Context;)Landroid/hardware/display/DisplayManager;", "downloadManager", "Landroid/app/DownloadManager;", "getDownloadManager", "(Landroid/content/Context;)Landroid/app/DownloadManager;", "fingerprintManager", "Landroid/hardware/fingerprint/FingerprintManager;", "getFingerprintManager", "(Landroid/content/Context;)Landroid/hardware/fingerprint/FingerprintManager;", "hardwarePropertiesManager", "Landroid/os/HardwarePropertiesManager;", "getHardwarePropertiesManager", "(Landroid/content/Context;)Landroid/os/HardwarePropertiesManager;", "inputManager", "Landroid/hardware/input/InputManager;", "getInputManager", "(Landroid/content/Context;)Landroid/hardware/input/InputManager;", "inputMethodManager", "Landroid/view/inputmethod/InputMethodManager;", "getInputMethodManager", "(Landroid/content/Context;)Landroid/view/inputmethod/InputMethodManager;", "keyguardManager", "Landroid/app/KeyguardManager;", "getKeyguardManager", "(Landroid/content/Context;)Landroid/app/KeyguardManager;", "locationManager", "Landroid/location/LocationManager;", "getLocationManager", "(Landroid/content/Context;)Landroid/location/LocationManager;", "mediaProjectionManager", "Landroid/media/projection/MediaProjectionManager;", "getMediaProjectionManager", "(Landroid/content/Context;)Landroid/media/projection/MediaProjectionManager;", "mediaSessionManager", "Landroid/media/session/MediaSessionManager;", "getMediaSessionManager", "(Landroid/content/Context;)Landroid/media/session/MediaSessionManager;", "midiManager", "Landroid/media/midi/MidiManager;", "getMidiManager", "(Landroid/content/Context;)Landroid/media/midi/MidiManager;", "networkStatsManager", "Landroid/app/usage/NetworkStatsManager;", "getNetworkStatsManager", "(Landroid/content/Context;)Landroid/app/usage/NetworkStatsManager;", "nfcManager", "Landroid/nfc/NfcManager;", "getNfcManager", "(Landroid/content/Context;)Landroid/nfc/NfcManager;", "notificationManager", "Landroid/app/NotificationManager;", "getNotificationManager", "(Landroid/content/Context;)Landroid/app/NotificationManager;", "nsdManager", "Landroid/net/nsd/NsdManager;", "getNsdManager", "(Landroid/content/Context;)Landroid/net/nsd/NsdManager;", "powerManager", "Landroid/os/PowerManager;", "getPowerManager", "(Landroid/content/Context;)Landroid/os/PowerManager;", "printManager", "Landroid/print/PrintManager;", "getPrintManager", "(Landroid/content/Context;)Landroid/print/PrintManager;", "restrictionsManager", "Landroid/content/RestrictionsManager;", "getRestrictionsManager", "(Landroid/content/Context;)Landroid/content/RestrictionsManager;", "searchManager", "Landroid/app/SearchManager;", "getSearchManager", "(Landroid/content/Context;)Landroid/app/SearchManager;", "sensorManager", "Landroid/hardware/SensorManager;", "getSensorManager", "(Landroid/content/Context;)Landroid/hardware/SensorManager;", "shortcutManager", "Landroid/content/pm/ShortcutManager;", "getShortcutManager", "(Landroid/content/Context;)Landroid/content/pm/ShortcutManager;", "storageManager", "Landroid/os/storage/StorageManager;", "getStorageManager", "(Landroid/content/Context;)Landroid/os/storage/StorageManager;", "storageStatsManager", "Landroid/app/usage/StorageStatsManager;", "getStorageStatsManager", "(Landroid/content/Context;)Landroid/app/usage/StorageStatsManager;", "systemHealthManager", "Landroid/os/health/SystemHealthManager;", "getSystemHealthManager", "(Landroid/content/Context;)Landroid/os/health/SystemHealthManager;", "telecomManager", "Landroid/telecom/TelecomManager;", "getTelecomManager", "(Landroid/content/Context;)Landroid/telecom/TelecomManager;", "telephonyManager", "Landroid/telephony/TelephonyManager;", "getTelephonyManager", "(Landroid/content/Context;)Landroid/telephony/TelephonyManager;", "textClassificationManager", "Landroid/view/textclassifier/TextClassificationManager;", "getTextClassificationManager", "(Landroid/content/Context;)Landroid/view/textclassifier/TextClassificationManager;", "tvInputManager", "Landroid/media/tv/TvInputManager;", "getTvInputManager", "(Landroid/content/Context;)Landroid/media/tv/TvInputManager;", "uiModeManager", "Landroid/app/UiModeManager;", "getUiModeManager", "(Landroid/content/Context;)Landroid/app/UiModeManager;", "usageStatsManager", "Landroid/app/usage/UsageStatsManager;", "getUsageStatsManager", "(Landroid/content/Context;)Landroid/app/usage/UsageStatsManager;", "usbManager", "Landroid/hardware/usb/UsbManager;", "getUsbManager", "(Landroid/content/Context;)Landroid/hardware/usb/UsbManager;", "userManager", "Landroid/os/UserManager;", "getUserManager", "(Landroid/content/Context;)Landroid/os/UserManager;", "wallpaperManager", "Landroid/app/WallpaperManager;", "getWallpaperManager", "(Landroid/content/Context;)Landroid/app/WallpaperManager;", "wifiAwareManager", "Landroid/net/wifi/aware/WifiAwareManager;", "getWifiAwareManager", "(Landroid/content/Context;)Landroid/net/wifi/aware/WifiAwareManager;", "wifiManager", "Landroid/net/wifi/WifiManager;", "getWifiManager", "(Landroid/content/Context;)Landroid/net/wifi/WifiManager;", "wifiP2pManager", "Landroid/net/wifi/p2p/WifiP2pManager;", "getWifiP2pManager", "(Landroid/content/Context;)Landroid/net/wifi/p2p/WifiP2pManager;", "windowManager", "Landroid/view/WindowManager;", "getWindowManager", "(Landroid/content/Context;)Landroid/view/WindowManager;", "anko-sdk27_release"}, k = 2, mv = {1, 1, 13})
@JvmName(name = "Sdk27ServicesKt")
/* compiled from: Services.kt */
public final class Sdk27ServicesKt {
    @NotNull
    public static final AccessibilityManager getAccessibilityManager(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        context = context.getSystemService("accessibility");
        if (context != null) {
            return (AccessibilityManager) context;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.view.accessibility.AccessibilityManager");
    }

    @NotNull
    public static final AccountManager getAccountManager(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        context = context.getSystemService("account");
        if (context != null) {
            return (AccountManager) context;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.accounts.AccountManager");
    }

    @NotNull
    public static final ActivityManager getActivityManager(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        context = context.getSystemService("activity");
        if (context != null) {
            return (ActivityManager) context;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.app.ActivityManager");
    }

    @NotNull
    public static final AlarmManager getAlarmManager(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        context = context.getSystemService(NotificationCompat.CATEGORY_ALARM);
        if (context != null) {
            return (AlarmManager) context;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.app.AlarmManager");
    }

    @NotNull
    public static final AppOpsManager getAppOpsManager(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        context = context.getSystemService("appops");
        if (context != null) {
            return (AppOpsManager) context;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.app.AppOpsManager");
    }

    @NotNull
    public static final AudioManager getAudioManager(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        context = context.getSystemService("audio");
        if (context != null) {
            return (AudioManager) context;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.media.AudioManager");
    }

    @NotNull
    public static final BatteryManager getBatteryManager(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        context = context.getSystemService("batterymanager");
        if (context != null) {
            return (BatteryManager) context;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.os.BatteryManager");
    }

    @NotNull
    public static final BluetoothManager getBluetoothManager(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        context = context.getSystemService("bluetooth");
        if (context != null) {
            return (BluetoothManager) context;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.bluetooth.BluetoothManager");
    }

    @NotNull
    public static final CameraManager getCameraManager(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        context = context.getSystemService("camera");
        if (context != null) {
            return (CameraManager) context;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.hardware.camera2.CameraManager");
    }

    @NotNull
    public static final CaptioningManager getCaptioningManager(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        context = context.getSystemService("captioning");
        if (context != null) {
            return (CaptioningManager) context;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.view.accessibility.CaptioningManager");
    }

    @NotNull
    public static final CarrierConfigManager getCarrierConfigManager(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        context = context.getSystemService("carrier_config");
        if (context != null) {
            return (CarrierConfigManager) context;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.telephony.CarrierConfigManager");
    }

    @NotNull
    public static final ClipboardManager getClipboardManager(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        context = context.getSystemService("clipboard");
        if (context != null) {
            return (ClipboardManager) context;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.content.ClipboardManager");
    }

    @NotNull
    public static final CompanionDeviceManager getCompanionDeviceManager(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        context = context.getSystemService("companiondevice");
        if (context != null) {
            return (CompanionDeviceManager) context;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.companion.CompanionDeviceManager");
    }

    @NotNull
    public static final ConnectivityManager getConnectivityManager(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        context = context.getSystemService("connectivity");
        if (context != null) {
            return (ConnectivityManager) context;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.net.ConnectivityManager");
    }

    @NotNull
    public static final ConsumerIrManager getConsumerIrManager(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        context = context.getSystemService("consumer_ir");
        if (context != null) {
            return (ConsumerIrManager) context;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.hardware.ConsumerIrManager");
    }

    @NotNull
    public static final DevicePolicyManager getDevicePolicyManager(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        context = context.getSystemService("device_policy");
        if (context != null) {
            return (DevicePolicyManager) context;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.app.admin.DevicePolicyManager");
    }

    @NotNull
    public static final DisplayManager getDisplayManager(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        context = context.getSystemService("display");
        if (context != null) {
            return (DisplayManager) context;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.hardware.display.DisplayManager");
    }

    @NotNull
    public static final DownloadManager getDownloadManager(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        context = context.getSystemService("download");
        if (context != null) {
            return (DownloadManager) context;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.app.DownloadManager");
    }

    @NotNull
    public static final FingerprintManager getFingerprintManager(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        context = context.getSystemService("fingerprint");
        if (context != null) {
            return (FingerprintManager) context;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.hardware.fingerprint.FingerprintManager");
    }

    @NotNull
    public static final HardwarePropertiesManager getHardwarePropertiesManager(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        context = context.getSystemService("hardware_properties");
        if (context != null) {
            return (HardwarePropertiesManager) context;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.os.HardwarePropertiesManager");
    }

    @NotNull
    public static final InputManager getInputManager(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        context = context.getSystemService("input");
        if (context != null) {
            return (InputManager) context;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.hardware.input.InputManager");
    }

    @NotNull
    public static final InputMethodManager getInputMethodManager(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        context = context.getSystemService("input_method");
        if (context != null) {
            return (InputMethodManager) context;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.view.inputmethod.InputMethodManager");
    }

    @NotNull
    public static final KeyguardManager getKeyguardManager(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        context = context.getSystemService("keyguard");
        if (context != null) {
            return (KeyguardManager) context;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.app.KeyguardManager");
    }

    @NotNull
    public static final LocationManager getLocationManager(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        context = context.getSystemService("location");
        if (context != null) {
            return (LocationManager) context;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.location.LocationManager");
    }

    @NotNull
    public static final MediaProjectionManager getMediaProjectionManager(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        context = context.getSystemService("media_projection");
        if (context != null) {
            return (MediaProjectionManager) context;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.media.projection.MediaProjectionManager");
    }

    @NotNull
    public static final MediaSessionManager getMediaSessionManager(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        context = context.getSystemService("media_session");
        if (context != null) {
            return (MediaSessionManager) context;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.media.session.MediaSessionManager");
    }

    @NotNull
    public static final MidiManager getMidiManager(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        context = context.getSystemService("midi");
        if (context != null) {
            return (MidiManager) context;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.media.midi.MidiManager");
    }

    @NotNull
    public static final NetworkStatsManager getNetworkStatsManager(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        context = context.getSystemService("netstats");
        if (context != null) {
            return (NetworkStatsManager) context;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.app.usage.NetworkStatsManager");
    }

    @NotNull
    public static final NfcManager getNfcManager(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        context = context.getSystemService("nfc");
        if (context != null) {
            return (NfcManager) context;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.nfc.NfcManager");
    }

    @NotNull
    public static final NotificationManager getNotificationManager(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        context = context.getSystemService("notification");
        if (context != null) {
            return (NotificationManager) context;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.app.NotificationManager");
    }

    @NotNull
    public static final NsdManager getNsdManager(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        context = context.getSystemService("servicediscovery");
        if (context != null) {
            return (NsdManager) context;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.net.nsd.NsdManager");
    }

    @NotNull
    public static final PowerManager getPowerManager(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        context = context.getSystemService("power");
        if (context != null) {
            return (PowerManager) context;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.os.PowerManager");
    }

    @NotNull
    public static final PrintManager getPrintManager(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        context = context.getSystemService("print");
        if (context != null) {
            return (PrintManager) context;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.print.PrintManager");
    }

    @NotNull
    public static final RestrictionsManager getRestrictionsManager(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        context = context.getSystemService("restrictions");
        if (context != null) {
            return (RestrictionsManager) context;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.content.RestrictionsManager");
    }

    @NotNull
    public static final SearchManager getSearchManager(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        context = context.getSystemService("search");
        if (context != null) {
            return (SearchManager) context;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.app.SearchManager");
    }

    @NotNull
    public static final SensorManager getSensorManager(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        context = context.getSystemService("sensor");
        if (context != null) {
            return (SensorManager) context;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.hardware.SensorManager");
    }

    @NotNull
    public static final ShortcutManager getShortcutManager(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        context = context.getSystemService("shortcut");
        if (context != null) {
            return (ShortcutManager) context;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.content.pm.ShortcutManager");
    }

    @NotNull
    public static final StorageManager getStorageManager(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        context = context.getSystemService("storage");
        if (context != null) {
            return (StorageManager) context;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.os.storage.StorageManager");
    }

    @NotNull
    public static final StorageStatsManager getStorageStatsManager(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        context = context.getSystemService("storagestats");
        if (context != null) {
            return (StorageStatsManager) context;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.app.usage.StorageStatsManager");
    }

    @NotNull
    public static final SystemHealthManager getSystemHealthManager(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        context = context.getSystemService("systemhealth");
        if (context != null) {
            return (SystemHealthManager) context;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.os.health.SystemHealthManager");
    }

    @NotNull
    public static final TelecomManager getTelecomManager(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        context = context.getSystemService("telecom");
        if (context != null) {
            return (TelecomManager) context;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.telecom.TelecomManager");
    }

    @NotNull
    public static final TelephonyManager getTelephonyManager(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        context = context.getSystemService("phone");
        if (context != null) {
            return (TelephonyManager) context;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.telephony.TelephonyManager");
    }

    @NotNull
    public static final TextClassificationManager getTextClassificationManager(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        context = context.getSystemService("textclassification");
        if (context != null) {
            return (TextClassificationManager) context;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.view.textclassifier.TextClassificationManager");
    }

    @NotNull
    public static final TvInputManager getTvInputManager(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        context = context.getSystemService("tv_input");
        if (context != null) {
            return (TvInputManager) context;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.media.tv.TvInputManager");
    }

    @NotNull
    public static final UiModeManager getUiModeManager(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        context = context.getSystemService("uimode");
        if (context != null) {
            return (UiModeManager) context;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.app.UiModeManager");
    }

    @NotNull
    public static final UsageStatsManager getUsageStatsManager(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        context = context.getSystemService("usagestats");
        if (context != null) {
            return (UsageStatsManager) context;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.app.usage.UsageStatsManager");
    }

    @NotNull
    public static final UsbManager getUsbManager(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        context = context.getSystemService("usb");
        if (context != null) {
            return (UsbManager) context;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.hardware.usb.UsbManager");
    }

    @NotNull
    public static final UserManager getUserManager(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        context = context.getSystemService("user");
        if (context != null) {
            return (UserManager) context;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.os.UserManager");
    }

    @NotNull
    public static final WallpaperManager getWallpaperManager(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        context = context.getSystemService("wallpaper");
        if (context != null) {
            return (WallpaperManager) context;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.app.WallpaperManager");
    }

    @NotNull
    public static final WifiAwareManager getWifiAwareManager(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        context = context.getSystemService("wifiaware");
        if (context != null) {
            return (WifiAwareManager) context;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.net.wifi.aware.WifiAwareManager");
    }

    @NotNull
    public static final WifiManager getWifiManager(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        context = context.getSystemService("wifi");
        if (context != null) {
            return (WifiManager) context;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.net.wifi.WifiManager");
    }

    @NotNull
    public static final WifiP2pManager getWifiP2pManager(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        context = context.getSystemService("wifip2p");
        if (context != null) {
            return (WifiP2pManager) context;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.net.wifi.p2p.WifiP2pManager");
    }

    @NotNull
    public static final WindowManager getWindowManager(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        context = context.getSystemService("window");
        if (context != null) {
            return (WindowManager) context;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.view.WindowManager");
    }
}
