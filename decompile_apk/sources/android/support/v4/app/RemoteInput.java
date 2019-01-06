package android.support.v4.app;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Intent;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.Log;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public final class RemoteInput {
    private static final String EXTRA_DATA_TYPE_RESULTS_DATA = "android.remoteinput.dataTypeResultsData";
    public static final String EXTRA_RESULTS_DATA = "android.remoteinput.resultsData";
    public static final String RESULTS_CLIP_LABEL = "android.remoteinput.results";
    private static final String TAG = "RemoteInput";
    private final boolean mAllowFreeFormTextInput;
    private final Set<String> mAllowedDataTypes;
    private final CharSequence[] mChoices;
    private final Bundle mExtras;
    private final CharSequence mLabel;
    private final String mResultKey;

    public static final class Builder {
        private boolean mAllowFreeFormTextInput = true;
        private final Set<String> mAllowedDataTypes = new HashSet();
        private CharSequence[] mChoices;
        private final Bundle mExtras = new Bundle();
        private CharSequence mLabel;
        private final String mResultKey;

        public Builder(@NonNull String str) {
            if (str != null) {
                this.mResultKey = str;
                return;
            }
            throw new IllegalArgumentException("Result key can't be null");
        }

        @NonNull
        public Builder setLabel(@Nullable CharSequence charSequence) {
            this.mLabel = charSequence;
            return this;
        }

        @NonNull
        public Builder setChoices(@Nullable CharSequence[] charSequenceArr) {
            this.mChoices = charSequenceArr;
            return this;
        }

        @NonNull
        public Builder setAllowDataType(@NonNull String str, boolean z) {
            if (z) {
                this.mAllowedDataTypes.add(str);
            } else {
                this.mAllowedDataTypes.remove(str);
            }
            return this;
        }

        @NonNull
        public Builder setAllowFreeFormInput(boolean z) {
            this.mAllowFreeFormTextInput = z;
            return this;
        }

        @NonNull
        public Builder addExtras(@NonNull Bundle bundle) {
            if (bundle != null) {
                this.mExtras.putAll(bundle);
            }
            return this;
        }

        @NonNull
        public Bundle getExtras() {
            return this.mExtras;
        }

        @NonNull
        public RemoteInput build() {
            return new RemoteInput(this.mResultKey, this.mLabel, this.mChoices, this.mAllowFreeFormTextInput, this.mExtras, this.mAllowedDataTypes);
        }
    }

    RemoteInput(String str, CharSequence charSequence, CharSequence[] charSequenceArr, boolean z, Bundle bundle, Set<String> set) {
        this.mResultKey = str;
        this.mLabel = charSequence;
        this.mChoices = charSequenceArr;
        this.mAllowFreeFormTextInput = z;
        this.mExtras = bundle;
        this.mAllowedDataTypes = set;
    }

    public String getResultKey() {
        return this.mResultKey;
    }

    public CharSequence getLabel() {
        return this.mLabel;
    }

    public CharSequence[] getChoices() {
        return this.mChoices;
    }

    public Set<String> getAllowedDataTypes() {
        return this.mAllowedDataTypes;
    }

    public boolean isDataOnly() {
        return (getAllowFreeFormInput() || ((getChoices() != null && getChoices().length != 0) || getAllowedDataTypes() == null || getAllowedDataTypes().isEmpty())) ? false : true;
    }

    public boolean getAllowFreeFormInput() {
        return this.mAllowFreeFormTextInput;
    }

    public Bundle getExtras() {
        return this.mExtras;
    }

    public static Map<String, Uri> getDataResultsFromIntent(Intent intent, String str) {
        if (VERSION.SDK_INT >= 26) {
            return android.app.RemoteInput.getDataResultsFromIntent(intent, str);
        }
        if (VERSION.SDK_INT >= 16) {
            intent = getClipDataIntentFromIntent(intent);
            if (intent == null) {
                return null;
            }
            Map<String, Uri> hashMap = new HashMap();
            for (String str2 : intent.getExtras().keySet()) {
                String str22;
                if (str22.startsWith(EXTRA_DATA_TYPE_RESULTS_DATA)) {
                    String substring = str22.substring(EXTRA_DATA_TYPE_RESULTS_DATA.length());
                    if (!substring.isEmpty()) {
                        str22 = intent.getBundleExtra(str22).getString(str);
                        if (str22 != null) {
                            if (!str22.isEmpty()) {
                                hashMap.put(substring, Uri.parse(str22));
                            }
                        }
                    }
                }
            }
            if (hashMap.isEmpty() != null) {
                hashMap = null;
            }
            return hashMap;
        }
        Log.w(TAG, "RemoteInput is only supported from API Level 16");
        return null;
    }

    public static Bundle getResultsFromIntent(Intent intent) {
        if (VERSION.SDK_INT >= 20) {
            return android.app.RemoteInput.getResultsFromIntent(intent);
        }
        if (VERSION.SDK_INT >= 16) {
            intent = getClipDataIntentFromIntent(intent);
            if (intent == null) {
                return null;
            }
            return (Bundle) intent.getExtras().getParcelable(EXTRA_RESULTS_DATA);
        }
        Log.w(TAG, "RemoteInput is only supported from API Level 16");
        return null;
    }

    public static void addResultsToIntent(RemoteInput[] remoteInputArr, Intent intent, Bundle bundle) {
        if (VERSION.SDK_INT >= 26) {
            android.app.RemoteInput.addResultsToIntent(fromCompat(remoteInputArr), intent, bundle);
            return;
        }
        int i = 0;
        if (VERSION.SDK_INT >= 20) {
            Bundle resultsFromIntent = getResultsFromIntent(intent);
            if (resultsFromIntent != null) {
                resultsFromIntent.putAll(bundle);
                bundle = resultsFromIntent;
            }
            for (RemoteInput remoteInput : remoteInputArr) {
                Map dataResultsFromIntent = getDataResultsFromIntent(intent, remoteInput.getResultKey());
                android.app.RemoteInput.addResultsToIntent(fromCompat(new RemoteInput[]{remoteInput}), intent, bundle);
                if (dataResultsFromIntent != null) {
                    addDataResultToIntent(remoteInput, intent, dataResultsFromIntent);
                }
            }
        } else if (VERSION.SDK_INT >= 16) {
            Intent clipDataIntentFromIntent = getClipDataIntentFromIntent(intent);
            if (clipDataIntentFromIntent == null) {
                clipDataIntentFromIntent = new Intent();
            }
            Bundle bundleExtra = clipDataIntentFromIntent.getBundleExtra(EXTRA_RESULTS_DATA);
            if (bundleExtra == null) {
                bundleExtra = new Bundle();
            }
            int length = remoteInputArr.length;
            while (i < length) {
                RemoteInput remoteInput2 = remoteInputArr[i];
                Object obj = bundle.get(remoteInput2.getResultKey());
                if (obj instanceof CharSequence) {
                    bundleExtra.putCharSequence(remoteInput2.getResultKey(), (CharSequence) obj);
                }
                i++;
            }
            clipDataIntentFromIntent.putExtra(EXTRA_RESULTS_DATA, bundleExtra);
            intent.setClipData(ClipData.newIntent(RESULTS_CLIP_LABEL, clipDataIntentFromIntent));
        } else {
            Log.w(TAG, "RemoteInput is only supported from API Level 16");
        }
    }

    public static void addDataResultToIntent(RemoteInput remoteInput, Intent intent, Map<String, Uri> map) {
        if (VERSION.SDK_INT >= 26) {
            android.app.RemoteInput.addDataResultToIntent(fromCompat(remoteInput), intent, map);
        } else if (VERSION.SDK_INT >= 16) {
            Intent clipDataIntentFromIntent = getClipDataIntentFromIntent(intent);
            if (clipDataIntentFromIntent == null) {
                clipDataIntentFromIntent = new Intent();
            }
            map = map.entrySet().iterator();
            while (map.hasNext()) {
                Entry entry = (Entry) map.next();
                String str = (String) entry.getKey();
                Uri uri = (Uri) entry.getValue();
                if (str != null) {
                    Bundle bundleExtra = clipDataIntentFromIntent.getBundleExtra(getExtraResultsKeyForData(str));
                    if (bundleExtra == null) {
                        bundleExtra = new Bundle();
                    }
                    bundleExtra.putString(remoteInput.getResultKey(), uri.toString());
                    clipDataIntentFromIntent.putExtra(getExtraResultsKeyForData(str), bundleExtra);
                }
            }
            intent.setClipData(ClipData.newIntent(RESULTS_CLIP_LABEL, clipDataIntentFromIntent));
        } else {
            Log.w(TAG, "RemoteInput is only supported from API Level 16");
        }
    }

    private static String getExtraResultsKeyForData(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(EXTRA_DATA_TYPE_RESULTS_DATA);
        stringBuilder.append(str);
        return stringBuilder.toString();
    }

    @RequiresApi(20)
    static android.app.RemoteInput[] fromCompat(RemoteInput[] remoteInputArr) {
        if (remoteInputArr == null) {
            return null;
        }
        android.app.RemoteInput[] remoteInputArr2 = new android.app.RemoteInput[remoteInputArr.length];
        for (int i = 0; i < remoteInputArr.length; i++) {
            remoteInputArr2[i] = fromCompat(remoteInputArr[i]);
        }
        return remoteInputArr2;
    }

    @RequiresApi(20)
    static android.app.RemoteInput fromCompat(RemoteInput remoteInput) {
        return new android.app.RemoteInput.Builder(remoteInput.getResultKey()).setLabel(remoteInput.getLabel()).setChoices(remoteInput.getChoices()).setAllowFreeFormInput(remoteInput.getAllowFreeFormInput()).addExtras(remoteInput.getExtras()).build();
    }

    @RequiresApi(16)
    private static Intent getClipDataIntentFromIntent(Intent intent) {
        intent = intent.getClipData();
        if (intent == null) {
            return null;
        }
        ClipDescription description = intent.getDescription();
        if (description.hasMimeType("text/vnd.android.intent") && description.getLabel().equals(RESULTS_CLIP_LABEL)) {
            return intent.getItemAt(0).getIntent();
        }
        return null;
    }
}
