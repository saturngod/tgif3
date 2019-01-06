package android.support.v4.provider;

import android.support.annotation.ArrayRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.util.Preconditions;
import android.util.Base64;
import java.util.List;

public final class FontRequest {
    private final List<List<byte[]>> mCertificates;
    private final int mCertificatesArray;
    private final String mIdentifier;
    private final String mProviderAuthority;
    private final String mProviderPackage;
    private final String mQuery;

    public FontRequest(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull List<List<byte[]>> list) {
        this.mProviderAuthority = (String) Preconditions.checkNotNull(str);
        this.mProviderPackage = (String) Preconditions.checkNotNull(str2);
        this.mQuery = (String) Preconditions.checkNotNull(str3);
        this.mCertificates = (List) Preconditions.checkNotNull(list);
        this.mCertificatesArray = null;
        str = new StringBuilder(this.mProviderAuthority);
        str.append("-");
        str.append(this.mProviderPackage);
        str.append("-");
        str.append(this.mQuery);
        this.mIdentifier = str.toString();
    }

    public FontRequest(@NonNull String str, @NonNull String str2, @NonNull String str3, @ArrayRes int i) {
        this.mProviderAuthority = (String) Preconditions.checkNotNull(str);
        this.mProviderPackage = (String) Preconditions.checkNotNull(str2);
        this.mQuery = (String) Preconditions.checkNotNull(str3);
        this.mCertificates = null;
        Preconditions.checkArgument(i != 0 ? true : null);
        this.mCertificatesArray = i;
        str = new StringBuilder(this.mProviderAuthority);
        str.append("-");
        str.append(this.mProviderPackage);
        str.append("-");
        str.append(this.mQuery);
        this.mIdentifier = str.toString();
    }

    @NonNull
    public String getProviderAuthority() {
        return this.mProviderAuthority;
    }

    @NonNull
    public String getProviderPackage() {
        return this.mProviderPackage;
    }

    @NonNull
    public String getQuery() {
        return this.mQuery;
    }

    @Nullable
    public List<List<byte[]>> getCertificates() {
        return this.mCertificates;
    }

    @ArrayRes
    public int getCertificatesArrayResId() {
        return this.mCertificatesArray;
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public String getIdentifier() {
        return this.mIdentifier;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("FontRequest {mProviderAuthority: ");
        stringBuilder2.append(this.mProviderAuthority);
        stringBuilder2.append(", mProviderPackage: ");
        stringBuilder2.append(this.mProviderPackage);
        stringBuilder2.append(", mQuery: ");
        stringBuilder2.append(this.mQuery);
        stringBuilder2.append(", mCertificates:");
        stringBuilder.append(stringBuilder2.toString());
        for (int i = 0; i < this.mCertificates.size(); i++) {
            stringBuilder.append(" [");
            List list = (List) this.mCertificates.get(i);
            for (int i2 = 0; i2 < list.size(); i2++) {
                stringBuilder.append(" \"");
                stringBuilder.append(Base64.encodeToString((byte[]) list.get(i2), 0));
                stringBuilder.append("\"");
            }
            stringBuilder.append(" ]");
        }
        stringBuilder.append("}");
        stringBuilder2 = new StringBuilder();
        stringBuilder2.append("mCertificatesArray: ");
        stringBuilder2.append(this.mCertificatesArray);
        stringBuilder.append(stringBuilder2.toString());
        return stringBuilder.toString();
    }
}
