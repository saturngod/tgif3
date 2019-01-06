package androidx.versionedparcelable;

import android.os.Parcelable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import java.io.InputStream;
import java.io.OutputStream;

@RestrictTo({Scope.LIBRARY_GROUP})
public class ParcelUtils {
    private ParcelUtils() {
    }

    public static Parcelable toParcelable(VersionedParcelable versionedParcelable) {
        return new ParcelImpl(versionedParcelable);
    }

    public static <T extends VersionedParcelable> T fromParcelable(Parcelable parcelable) {
        if (parcelable instanceof ParcelImpl) {
            return ((ParcelImpl) parcelable).getVersionedParcel();
        }
        throw new IllegalArgumentException("Invalid parcel");
    }

    public static void toOutputStream(VersionedParcelable versionedParcelable, OutputStream outputStream) {
        VersionedParcelStream versionedParcelStream = new VersionedParcelStream(null, outputStream);
        versionedParcelStream.writeVersionedParcelable(versionedParcelable);
        versionedParcelStream.closeField();
    }

    public static <T extends VersionedParcelable> T fromInputStream(InputStream inputStream) {
        return new VersionedParcelStream(inputStream, null).readVersionedParcelable();
    }
}
