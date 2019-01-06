package androidx.versionedparcelable;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcelable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.internal.view.SupportMenu;
import android.util.SparseArray;
import androidx.versionedparcelable.VersionedParcel.ParcelException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Set;

@RestrictTo({Scope.LIBRARY})
class VersionedParcelStream extends VersionedParcel {
    private static final int TYPE_BOOLEAN = 5;
    private static final int TYPE_BOOLEAN_ARRAY = 6;
    private static final int TYPE_DOUBLE = 7;
    private static final int TYPE_DOUBLE_ARRAY = 8;
    private static final int TYPE_FLOAT = 13;
    private static final int TYPE_FLOAT_ARRAY = 14;
    private static final int TYPE_INT = 9;
    private static final int TYPE_INT_ARRAY = 10;
    private static final int TYPE_LONG = 11;
    private static final int TYPE_LONG_ARRAY = 12;
    private static final int TYPE_NULL = 0;
    private static final int TYPE_STRING = 3;
    private static final int TYPE_STRING_ARRAY = 4;
    private static final int TYPE_SUB_BUNDLE = 1;
    private static final int TYPE_SUB_PERSISTABLE_BUNDLE = 2;
    private static final Charset UTF_16 = Charset.forName("UTF-16");
    private final SparseArray<InputBuffer> mCachedFields = new SparseArray();
    private DataInputStream mCurrentInput;
    private DataOutputStream mCurrentOutput;
    private FieldBuffer mFieldBuffer;
    private boolean mIgnoreParcelables;
    private final DataInputStream mMasterInput;
    private final DataOutputStream mMasterOutput;

    private static class FieldBuffer {
        final DataOutputStream mDataStream = new DataOutputStream(this.mOutput);
        private final int mFieldId;
        final ByteArrayOutputStream mOutput = new ByteArrayOutputStream();
        private final DataOutputStream mTarget;

        FieldBuffer(int i, DataOutputStream dataOutputStream) {
            this.mFieldId = i;
            this.mTarget = dataOutputStream;
        }

        void flushField() throws IOException {
            this.mDataStream.flush();
            int size = this.mOutput.size();
            this.mTarget.writeInt((this.mFieldId << 16) | (size >= SupportMenu.USER_MASK ? SupportMenu.USER_MASK : size));
            if (size >= SupportMenu.USER_MASK) {
                this.mTarget.writeInt(size);
            }
            this.mOutput.writeTo(this.mTarget);
        }
    }

    private static class InputBuffer {
        final int mFieldId;
        final DataInputStream mInputStream;
        private final int mSize;

        InputBuffer(int i, int i2, DataInputStream dataInputStream) throws IOException {
            this.mSize = i2;
            this.mFieldId = i;
            i = new byte[this.mSize];
            dataInputStream.readFully(i);
            this.mInputStream = new DataInputStream(new ByteArrayInputStream(i));
        }
    }

    public boolean isStream() {
        return true;
    }

    public <T extends Parcelable> T readParcelable() {
        return null;
    }

    public IBinder readStrongBinder() {
        return null;
    }

    public VersionedParcelStream(InputStream inputStream, OutputStream outputStream) {
        DataOutputStream dataOutputStream = null;
        this.mMasterInput = inputStream != null ? new DataInputStream(inputStream) : null;
        if (outputStream != null) {
            dataOutputStream = new DataOutputStream(outputStream);
        }
        this.mMasterOutput = dataOutputStream;
        this.mCurrentInput = this.mMasterInput;
        this.mCurrentOutput = this.mMasterOutput;
    }

    public void setSerializationFlags(boolean z, boolean z2) {
        if (z) {
            this.mIgnoreParcelables = z2;
            return;
        }
        throw new RuntimeException("Serialization of this object is not allowed");
    }

    public void closeField() {
        if (this.mFieldBuffer != null) {
            try {
                if (this.mFieldBuffer.mOutput.size() != 0) {
                    this.mFieldBuffer.flushField();
                }
                this.mFieldBuffer = null;
            } catch (Throwable e) {
                throw new ParcelException(e);
            }
        }
    }

    protected VersionedParcel createSubParcel() {
        return new VersionedParcelStream(this.mCurrentInput, this.mCurrentOutput);
    }

    public boolean readField(int r6) {
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
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1603198149.run(Unknown Source)
*/
        /*
        r5 = this;
        r0 = r5.mCachedFields;
        r0 = r0.get(r6);
        r0 = (androidx.versionedparcelable.VersionedParcelStream.InputBuffer) r0;
        r1 = 1;
        if (r0 == 0) goto L_0x0015;
    L_0x000b:
        r2 = r5.mCachedFields;
        r2.remove(r6);
        r6 = r0.mInputStream;
        r5.mCurrentInput = r6;
        return r1;
    L_0x0015:
        r0 = r5.mMasterInput;	 Catch:{ IOException -> 0x0043 }
        r0 = r0.readInt();	 Catch:{ IOException -> 0x0043 }
        r2 = 65535; // 0xffff float:9.1834E-41 double:3.23786E-319;	 Catch:{ IOException -> 0x0043 }
        r3 = r0 & r2;	 Catch:{ IOException -> 0x0043 }
        if (r3 != r2) goto L_0x0028;	 Catch:{ IOException -> 0x0043 }
    L_0x0022:
        r3 = r5.mMasterInput;	 Catch:{ IOException -> 0x0043 }
        r3 = r3.readInt();	 Catch:{ IOException -> 0x0043 }
    L_0x0028:
        r4 = new androidx.versionedparcelable.VersionedParcelStream$InputBuffer;	 Catch:{ IOException -> 0x0043 }
        r0 = r0 >> 16;	 Catch:{ IOException -> 0x0043 }
        r0 = r0 & r2;	 Catch:{ IOException -> 0x0043 }
        r2 = r5.mMasterInput;	 Catch:{ IOException -> 0x0043 }
        r4.<init>(r0, r3, r2);	 Catch:{ IOException -> 0x0043 }
        r0 = r4.mFieldId;	 Catch:{ IOException -> 0x0043 }
        if (r0 != r6) goto L_0x003b;	 Catch:{ IOException -> 0x0043 }
    L_0x0036:
        r6 = r4.mInputStream;	 Catch:{ IOException -> 0x0043 }
        r5.mCurrentInput = r6;	 Catch:{ IOException -> 0x0043 }
        return r1;	 Catch:{ IOException -> 0x0043 }
    L_0x003b:
        r0 = r5.mCachedFields;	 Catch:{ IOException -> 0x0043 }
        r2 = r4.mFieldId;	 Catch:{ IOException -> 0x0043 }
        r0.put(r2, r4);	 Catch:{ IOException -> 0x0043 }
        goto L_0x0015;
    L_0x0043:
        r6 = 0;
        return r6;
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.versionedparcelable.VersionedParcelStream.readField(int):boolean");
    }

    public void setOutputField(int i) {
        closeField();
        this.mFieldBuffer = new FieldBuffer(i, this.mMasterOutput);
        this.mCurrentOutput = this.mFieldBuffer.mDataStream;
    }

    public void writeByteArray(byte[] bArr) {
        if (bArr != null) {
            try {
                this.mCurrentOutput.writeInt(bArr.length);
                this.mCurrentOutput.write(bArr);
                return;
            } catch (byte[] bArr2) {
                throw new ParcelException(bArr2);
            }
        }
        this.mCurrentOutput.writeInt(-1);
    }

    public void writeByteArray(byte[] bArr, int i, int i2) {
        if (bArr != null) {
            try {
                this.mCurrentOutput.writeInt(i2);
                this.mCurrentOutput.write(bArr, i, i2);
                return;
            } catch (byte[] bArr2) {
                throw new ParcelException(bArr2);
            }
        }
        this.mCurrentOutput.writeInt(-1);
    }

    public void writeInt(int i) {
        try {
            this.mCurrentOutput.writeInt(i);
        } catch (int i2) {
            throw new ParcelException(i2);
        }
    }

    public void writeLong(long j) {
        try {
            this.mCurrentOutput.writeLong(j);
        } catch (long j2) {
            throw new ParcelException(j2);
        }
    }

    public void writeFloat(float f) {
        try {
            this.mCurrentOutput.writeFloat(f);
        } catch (float f2) {
            throw new ParcelException(f2);
        }
    }

    public void writeDouble(double d) {
        try {
            this.mCurrentOutput.writeDouble(d);
        } catch (double d2) {
            throw new ParcelException(d2);
        }
    }

    public void writeString(String str) {
        if (str != null) {
            try {
                str = str.getBytes(UTF_16);
                this.mCurrentOutput.writeInt(str.length);
                this.mCurrentOutput.write(str);
                return;
            } catch (String str2) {
                throw new ParcelException(str2);
            }
        }
        this.mCurrentOutput.writeInt(-1);
    }

    public void writeBoolean(boolean z) {
        try {
            this.mCurrentOutput.writeBoolean(z);
        } catch (boolean z2) {
            throw new ParcelException(z2);
        }
    }

    public void writeStrongBinder(IBinder iBinder) {
        if (this.mIgnoreParcelables == null) {
            throw new RuntimeException("Binders cannot be written to an OutputStream");
        }
    }

    public void writeParcelable(Parcelable parcelable) {
        if (this.mIgnoreParcelables == null) {
            throw new RuntimeException("Parcelables cannot be written to an OutputStream");
        }
    }

    public void writeStrongInterface(IInterface iInterface) {
        if (this.mIgnoreParcelables == null) {
            throw new RuntimeException("Binders cannot be written to an OutputStream");
        }
    }

    public int readInt() {
        try {
            return this.mCurrentInput.readInt();
        } catch (Throwable e) {
            throw new ParcelException(e);
        }
    }

    public long readLong() {
        try {
            return this.mCurrentInput.readLong();
        } catch (Throwable e) {
            throw new ParcelException(e);
        }
    }

    public float readFloat() {
        try {
            return this.mCurrentInput.readFloat();
        } catch (Throwable e) {
            throw new ParcelException(e);
        }
    }

    public double readDouble() {
        try {
            return this.mCurrentInput.readDouble();
        } catch (Throwable e) {
            throw new ParcelException(e);
        }
    }

    public String readString() {
        try {
            int readInt = this.mCurrentInput.readInt();
            if (readInt <= 0) {
                return null;
            }
            byte[] bArr = new byte[readInt];
            this.mCurrentInput.readFully(bArr);
            return new String(bArr, UTF_16);
        } catch (Throwable e) {
            throw new ParcelException(e);
        }
    }

    public byte[] readByteArray() {
        try {
            int readInt = this.mCurrentInput.readInt();
            if (readInt <= 0) {
                return null;
            }
            byte[] bArr = new byte[readInt];
            this.mCurrentInput.readFully(bArr);
            return bArr;
        } catch (Throwable e) {
            throw new ParcelException(e);
        }
    }

    public boolean readBoolean() {
        try {
            return this.mCurrentInput.readBoolean();
        } catch (Throwable e) {
            throw new ParcelException(e);
        }
    }

    public void writeBundle(Bundle bundle) {
        if (bundle != null) {
            try {
                Set<String> keySet = bundle.keySet();
                this.mCurrentOutput.writeInt(keySet.size());
                for (String str : keySet) {
                    writeString(str);
                    writeObject(bundle.get(str));
                }
                return;
            } catch (Bundle bundle2) {
                throw new ParcelException(bundle2);
            }
        }
        this.mCurrentOutput.writeInt(-1);
    }

    public Bundle readBundle() {
        int readInt = readInt();
        if (readInt < 0) {
            return null;
        }
        Bundle bundle = new Bundle();
        for (int i = 0; i < readInt; i++) {
            readObject(readInt(), readString(), bundle);
        }
        return bundle;
    }

    private void writeObject(Object obj) {
        if (obj == null) {
            writeInt(null);
        } else if (obj instanceof Bundle) {
            writeInt(1);
            writeBundle((Bundle) obj);
        } else if (obj instanceof String) {
            writeInt(3);
            writeString((String) obj);
        } else if (obj instanceof String[]) {
            writeInt(4);
            writeArray((String[]) obj);
        } else if (obj instanceof Boolean) {
            writeInt(5);
            writeBoolean(((Boolean) obj).booleanValue());
        } else if (obj instanceof boolean[]) {
            writeInt(6);
            writeBooleanArray((boolean[]) obj);
        } else if (obj instanceof Double) {
            writeInt(7);
            writeDouble(((Double) obj).doubleValue());
        } else if (obj instanceof double[]) {
            writeInt(8);
            writeDoubleArray((double[]) obj);
        } else if (obj instanceof Integer) {
            writeInt(9);
            writeInt(((Integer) obj).intValue());
        } else if (obj instanceof int[]) {
            writeInt(10);
            writeIntArray((int[]) obj);
        } else if (obj instanceof Long) {
            writeInt(11);
            writeLong(((Long) obj).longValue());
        } else if (obj instanceof long[]) {
            writeInt(12);
            writeLongArray((long[]) obj);
        } else if (obj instanceof Float) {
            writeInt(13);
            writeFloat(((Float) obj).floatValue());
        } else if (obj instanceof float[]) {
            writeInt(14);
            writeFloatArray((float[]) obj);
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Unsupported type ");
            stringBuilder.append(obj.getClass());
            throw new IllegalArgumentException(stringBuilder.toString());
        }
    }

    private void readObject(int i, String str, Bundle bundle) {
        switch (i) {
            case 0:
                bundle.putParcelable(str, 0);
                return;
            case 1:
                bundle.putBundle(str, readBundle());
                return;
            case 2:
                bundle.putBundle(str, readBundle());
                return;
            case 3:
                bundle.putString(str, readString());
                return;
            case 4:
                bundle.putStringArray(str, (String[]) readArray(new String[0]));
                return;
            case 5:
                bundle.putBoolean(str, readBoolean());
                return;
            case 6:
                bundle.putBooleanArray(str, readBooleanArray());
                return;
            case 7:
                bundle.putDouble(str, readDouble());
                return;
            case 8:
                bundle.putDoubleArray(str, readDoubleArray());
                return;
            case 9:
                bundle.putInt(str, readInt());
                return;
            case 10:
                bundle.putIntArray(str, readIntArray());
                return;
            case 11:
                bundle.putLong(str, readLong());
                return;
            case 12:
                bundle.putLongArray(str, readLongArray());
                return;
            case 13:
                bundle.putFloat(str, readFloat());
                return;
            case 14:
                bundle.putFloatArray(str, readFloatArray());
                return;
            default:
                bundle = new StringBuilder();
                bundle.append("Unknown type ");
                bundle.append(i);
                throw new RuntimeException(bundle.toString());
        }
    }
}
