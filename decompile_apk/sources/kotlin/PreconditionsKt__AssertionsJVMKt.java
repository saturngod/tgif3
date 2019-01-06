package kotlin;

import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function0;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\u001a\u0011\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\b\u001a\u001f\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\b¨\u0006\u0007"}, d2 = {"assert", "", "value", "", "lazyMessage", "Lkotlin/Function0;", "", "kotlin-stdlib"}, k = 5, mv = {1, 1, 13}, xi = 1, xs = "kotlin/PreconditionsKt")
/* compiled from: AssertionsJVM.kt */
class PreconditionsKt__AssertionsJVMKt {
    @InlineOnly
    /* renamed from: assert */
    private static final void m4assert(boolean z) {
        if (!_Assertions.ENABLED) {
            return;
        }
        if (!z) {
            throw ((Throwable) new AssertionError("Assertion failed"));
        }
    }

    @InlineOnly
    /* renamed from: assert */
    private static final void m5assert(boolean z, Function0<? extends Object> function0) {
        if (!_Assertions.ENABLED) {
            return;
        }
        if (!z) {
            throw ((Throwable) new AssertionError(function0.invoke()));
        }
    }
}
