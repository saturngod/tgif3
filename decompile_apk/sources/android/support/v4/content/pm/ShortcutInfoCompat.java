package android.support.v4.content.pm;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.graphics.drawable.IconCompat;
import android.text.TextUtils;
import java.util.Arrays;

public class ShortcutInfoCompat {
    ComponentName mActivity;
    Context mContext;
    CharSequence mDisabledMessage;
    IconCompat mIcon;
    String mId;
    Intent[] mIntents;
    boolean mIsAlwaysBadged;
    CharSequence mLabel;
    CharSequence mLongLabel;

    public static class Builder {
        private final ShortcutInfoCompat mInfo = new ShortcutInfoCompat();

        public Builder(@NonNull Context context, @NonNull String str) {
            this.mInfo.mContext = context;
            this.mInfo.mId = str;
        }

        @NonNull
        public Builder setShortLabel(@NonNull CharSequence charSequence) {
            this.mInfo.mLabel = charSequence;
            return this;
        }

        @NonNull
        public Builder setLongLabel(@NonNull CharSequence charSequence) {
            this.mInfo.mLongLabel = charSequence;
            return this;
        }

        @NonNull
        public Builder setDisabledMessage(@NonNull CharSequence charSequence) {
            this.mInfo.mDisabledMessage = charSequence;
            return this;
        }

        @NonNull
        public Builder setIntent(@NonNull Intent intent) {
            return setIntents(new Intent[]{intent});
        }

        @NonNull
        public Builder setIntents(@NonNull Intent[] intentArr) {
            this.mInfo.mIntents = intentArr;
            return this;
        }

        @NonNull
        public Builder setIcon(IconCompat iconCompat) {
            this.mInfo.mIcon = iconCompat;
            return this;
        }

        @NonNull
        public Builder setActivity(@NonNull ComponentName componentName) {
            this.mInfo.mActivity = componentName;
            return this;
        }

        public Builder setAlwaysBadged() {
            this.mInfo.mIsAlwaysBadged = true;
            return this;
        }

        @NonNull
        public ShortcutInfoCompat build() {
            if (TextUtils.isEmpty(this.mInfo.mLabel)) {
                throw new IllegalArgumentException("Shortcut must have a non-empty label");
            } else if (this.mInfo.mIntents != null && this.mInfo.mIntents.length != 0) {
                return this.mInfo;
            } else {
                throw new IllegalArgumentException("Shortcut must have an intent");
            }
        }
    }

    ShortcutInfoCompat() {
    }

    @RequiresApi(25)
    public ShortcutInfo toShortcutInfo() {
        android.content.pm.ShortcutInfo.Builder intents = new android.content.pm.ShortcutInfo.Builder(this.mContext, this.mId).setShortLabel(this.mLabel).setIntents(this.mIntents);
        if (this.mIcon != null) {
            intents.setIcon(this.mIcon.toIcon());
        }
        if (!TextUtils.isEmpty(this.mLongLabel)) {
            intents.setLongLabel(this.mLongLabel);
        }
        if (!TextUtils.isEmpty(this.mDisabledMessage)) {
            intents.setDisabledMessage(this.mDisabledMessage);
        }
        if (this.mActivity != null) {
            intents.setActivity(this.mActivity);
        }
        return intents.build();
    }

    android.content.Intent addToIntent(android.content.Intent r4) {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1603198149.run(Unknown Source)
*/
        /*
        r3 = this;
        r0 = "android.intent.extra.shortcut.INTENT";
        r1 = r3.mIntents;
        r2 = r3.mIntents;
        r2 = r2.length;
        r2 = r2 + -1;
        r1 = r1[r2];
        r0 = r4.putExtra(r0, r1);
        r1 = "android.intent.extra.shortcut.NAME";
        r2 = r3.mLabel;
        r2 = r2.toString();
        r0.putExtra(r1, r2);
        r0 = r3.mIcon;
        if (r0 == 0) goto L_0x0047;
    L_0x001e:
        r0 = 0;
        r1 = r3.mIsAlwaysBadged;
        if (r1 == 0) goto L_0x0040;
    L_0x0023:
        r1 = r3.mContext;
        r1 = r1.getPackageManager();
        r2 = r3.mActivity;
        if (r2 == 0) goto L_0x0034;
    L_0x002d:
        r2 = r3.mActivity;	 Catch:{ NameNotFoundException -> 0x0034 }
        r2 = r1.getActivityIcon(r2);	 Catch:{ NameNotFoundException -> 0x0034 }
        r0 = r2;
    L_0x0034:
        if (r0 != 0) goto L_0x0040;
    L_0x0036:
        r0 = r3.mContext;
        r0 = r0.getApplicationInfo();
        r0 = r0.loadIcon(r1);
    L_0x0040:
        r1 = r3.mIcon;
        r2 = r3.mContext;
        r1.addToShortcutIntent(r4, r0, r2);
    L_0x0047:
        return r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.content.pm.ShortcutInfoCompat.addToIntent(android.content.Intent):android.content.Intent");
    }

    @NonNull
    public String getId() {
        return this.mId;
    }

    @Nullable
    public ComponentName getActivity() {
        return this.mActivity;
    }

    @NonNull
    public CharSequence getShortLabel() {
        return this.mLabel;
    }

    @Nullable
    public CharSequence getLongLabel() {
        return this.mLongLabel;
    }

    @Nullable
    public CharSequence getDisabledMessage() {
        return this.mDisabledMessage;
    }

    @NonNull
    public Intent getIntent() {
        return this.mIntents[this.mIntents.length - 1];
    }

    @NonNull
    public Intent[] getIntents() {
        return (Intent[]) Arrays.copyOf(this.mIntents, this.mIntents.length);
    }
}
