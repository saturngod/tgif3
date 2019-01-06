package android.support.v4.graphics.drawable;

import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import androidx.versionedparcelable.VersionedParcel;

@RestrictTo({Scope.LIBRARY})
public final class IconCompatParcelizer extends androidx.core.graphics.drawable.IconCompatParcelizer {
    public static IconCompat read(VersionedParcel versionedParcel) {
        return androidx.core.graphics.drawable.IconCompatParcelizer.read(versionedParcel);
    }

    public static void write(IconCompat iconCompat, VersionedParcel versionedParcel) {
        androidx.core.graphics.drawable.IconCompatParcelizer.write(iconCompat, versionedParcel);
    }
}
