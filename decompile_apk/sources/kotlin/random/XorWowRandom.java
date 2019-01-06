package kotlin.random;

import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\r\b\u0000\u0018\u00002\u00020\u0001B\u0017\b\u0010\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005B7\b\u0000\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\u0003¢\u0006\u0002\u0010\fJ\u0010\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u0003H\u0016J\b\u0010\u000f\u001a\u00020\u0003H\u0016R\u000e\u0010\u000b\u001a\u00020\u0003X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0003X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0003X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0003X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0003X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0003X\u000e¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lkotlin/random/XorWowRandom;", "Lkotlin/random/Random;", "seed1", "", "seed2", "(II)V", "x", "y", "z", "w", "v", "addend", "(IIIIII)V", "nextBits", "bitCount", "nextInt", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
/* compiled from: XorWowRandom.kt */
public final class XorWowRandom extends Random {
    private int addend;
    /* renamed from: v */
    private int f13v;
    /* renamed from: w */
    private int f14w;
    /* renamed from: x */
    private int f15x;
    /* renamed from: y */
    private int f16y;
    /* renamed from: z */
    private int f17z;

    public XorWowRandom(int i, int i2, int i3, int i4, int i5, int i6) {
        this.f15x = i;
        this.f16y = i2;
        this.f17z = i3;
        this.f14w = i4;
        this.f13v = i5;
        this.addend = i6;
        if ((((((this.f15x | this.f16y) | this.f17z) | this.f14w) | this.f13v) != 0 ? 1 : 0) != 0) {
            for (i2 = 0; i2 < 64; i2++) {
                nextInt();
            }
            return;
        }
        throw ((Throwable) new IllegalArgumentException("Initial state must have at least one non-zero element.".toString()));
    }

    public XorWowRandom(int i, int i2) {
        int i3 = i;
        int i4 = i2;
        this(i3, i4, 0, 0, ~i, (i << 10) ^ (i2 >>> 4));
    }

    public int nextInt() {
        int i = this.f15x;
        i ^= i >>> 2;
        this.f15x = this.f16y;
        this.f16y = this.f17z;
        this.f17z = this.f14w;
        int i2 = this.f13v;
        this.f14w = i2;
        i = ((i ^ (i << 1)) ^ i2) ^ (i2 << 4);
        this.f13v = i;
        this.addend += 362437;
        return i + this.addend;
    }

    public int nextBits(int i) {
        return RandomKt.takeUpperBits(nextInt(), i);
    }
}
