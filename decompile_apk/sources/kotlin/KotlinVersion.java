package kotlin;

import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SinceKotlin(version = "1.1")
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0007\u0018\u0000 \u00172\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0017B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\u0011\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u0000H\u0002J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u000e\u001a\u0004\u0018\u00010\u0011H\u0002J\b\u0010\u0012\u001a\u00020\u0003H\u0016J\u0016\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003J\u001e\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0003J\b\u0010\u0014\u001a\u00020\u0015H\u0016J \u0010\u0016\u001a\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0003H\u0002R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\tR\u000e\u0010\f\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lkotlin/KotlinVersion;", "", "major", "", "minor", "(II)V", "patch", "(III)V", "getMajor", "()I", "getMinor", "getPatch", "version", "compareTo", "other", "equals", "", "", "hashCode", "isAtLeast", "toString", "", "versionOf", "Companion", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
/* compiled from: KotlinVersion.kt */
public final class KotlinVersion implements Comparable<KotlinVersion> {
    @NotNull
    @JvmField
    public static final KotlinVersion CURRENT = new KotlinVersion(1, 3, 0);
    public static final Companion Companion = new Companion();
    public static final int MAX_COMPONENT_VALUE = 255;
    private final int major;
    private final int minor;
    private final int patch;
    private final int version;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lkotlin/KotlinVersion$Companion;", "", "()V", "CURRENT", "Lkotlin/KotlinVersion;", "MAX_COMPONENT_VALUE", "", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
    /* compiled from: KotlinVersion.kt */
    public static final class Companion {
        private Companion() {
        }
    }

    public KotlinVersion(int i, int i2, int i3) {
        this.major = i;
        this.minor = i2;
        this.patch = i3;
        this.version = versionOf(this.major, this.minor, this.patch);
    }

    public final int getMajor() {
        return this.major;
    }

    public final int getMinor() {
        return this.minor;
    }

    public final int getPatch() {
        return this.patch;
    }

    public KotlinVersion(int i, int i2) {
        this(i, i2, 0);
    }

    private final int versionOf(int i, int i2, int i3) {
        Object obj;
        if (i >= 0) {
            if (255 >= i) {
                if (i2 >= 0) {
                    if (255 >= i2) {
                        if (i3 >= 0) {
                            if (255 >= i3) {
                                obj = 1;
                                if (obj != null) {
                                    return ((i << 16) + (i2 << 8)) + i3;
                                }
                                StringBuilder stringBuilder = new StringBuilder();
                                stringBuilder.append("Version components are out of range: ");
                                stringBuilder.append(i);
                                stringBuilder.append('.');
                                stringBuilder.append(i2);
                                stringBuilder.append('.');
                                stringBuilder.append(i3);
                                throw ((Throwable) new IllegalArgumentException(stringBuilder.toString().toString()));
                            }
                        }
                    }
                }
            }
        }
        obj = null;
        if (obj != null) {
            return ((i << 16) + (i2 << 8)) + i3;
        }
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("Version components are out of range: ");
        stringBuilder2.append(i);
        stringBuilder2.append('.');
        stringBuilder2.append(i2);
        stringBuilder2.append('.');
        stringBuilder2.append(i3);
        throw ((Throwable) new IllegalArgumentException(stringBuilder2.toString().toString()));
    }

    @NotNull
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.major);
        stringBuilder.append('.');
        stringBuilder.append(this.minor);
        stringBuilder.append('.');
        stringBuilder.append(this.patch);
        return stringBuilder.toString();
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof KotlinVersion)) {
            obj = null;
        }
        KotlinVersion kotlinVersion = (KotlinVersion) obj;
        boolean z = false;
        if (kotlinVersion == null) {
            return false;
        }
        if (this.version == kotlinVersion.version) {
            z = true;
        }
        return z;
    }

    public int hashCode() {
        return this.version;
    }

    public int compareTo(@NotNull KotlinVersion kotlinVersion) {
        Intrinsics.checkParameterIsNotNull(kotlinVersion, "other");
        return this.version - kotlinVersion.version;
    }

    public final boolean isAtLeast(int i, int i2) {
        if (this.major <= i) {
            if (this.major != i || this.minor < i2) {
                return false;
            }
        }
        return true;
    }

    public final boolean isAtLeast(int i, int i2, int i3) {
        if (this.major <= i) {
            if (this.major == i) {
                if (this.minor <= i2) {
                    if (this.minor == i2 && this.patch >= i3) {
                    }
                }
            }
            return false;
        }
        return true;
    }
}
