package kotlin.random;

import java.util.Random;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.internal.InlineOnly;
import kotlin.internal.PlatformImplementationsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\t\u0010\u0000\u001a\u00020\u0001H\b\u001a\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0000\u001a\u0010\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0005H\u0000\u001a\f\u0010\t\u001a\u00020\n*\u00020\u0001H\u0007\u001a\f\u0010\u000b\u001a\u00020\u0001*\u00020\nH\u0007¨\u0006\f"}, d2 = {"defaultPlatformRandom", "Lkotlin/random/Random;", "doubleFromParts", "", "hi26", "", "low27", "fastLog2", "value", "asJavaRandom", "Ljava/util/Random;", "asKotlinRandom", "kotlin-stdlib"}, k = 2, mv = {1, 1, 13})
/* compiled from: PlatformRandom.kt */
public final class PlatformRandomKt {
    public static final double doubleFromParts(int i, int i2) {
        return ((double) ((((long) i) << 27) + ((long) i2))) / ((double) 9007199254740992L);
    }

    @NotNull
    @SinceKotlin(version = "1.3")
    public static final Random asJavaRandom(@NotNull Random random) {
        Intrinsics.checkParameterIsNotNull(random, "receiver$0");
        AbstractPlatformRandom abstractPlatformRandom = (AbstractPlatformRandom) (!(random instanceof AbstractPlatformRandom) ? null : random);
        if (abstractPlatformRandom != null) {
            Random impl = abstractPlatformRandom.getImpl();
            if (impl != null) {
                return impl;
            }
        }
        return new KotlinRandom(random);
    }

    @NotNull
    @SinceKotlin(version = "1.3")
    public static final Random asKotlinRandom(@NotNull Random random) {
        Intrinsics.checkParameterIsNotNull(random, "receiver$0");
        KotlinRandom kotlinRandom = (KotlinRandom) (!(random instanceof KotlinRandom) ? null : random);
        if (kotlinRandom != null) {
            Random impl = kotlinRandom.getImpl();
            if (impl != null) {
                return impl;
            }
        }
        return new PlatformRandom(random);
    }

    @InlineOnly
    private static final Random defaultPlatformRandom() {
        return PlatformImplementationsKt.IMPLEMENTATIONS.defaultPlatformRandom();
    }

    public static final int fastLog2(int i) {
        return 31 - Integer.numberOfLeadingZeros(i);
    }
}
