package kotlinx.coroutines.internal;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.jvm.JvmField;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\b\b\u0000\u0018\u0000*\u0012\b\u0000\u0010\u0001*\u00020\u0002*\b\u0012\u0004\u0012\u0002H\u00010\u00032\u00060\u0004j\u0002`\u0005B\u0005¢\u0006\u0002\u0010\u0006J\u0015\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00028\u0000H\u0001¢\u0006\u0002\u0010\u0013J\u0013\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00028\u0000¢\u0006\u0002\u0010\u0013J$\u0010\u0015\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00028\u00002\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0017H\b¢\u0006\u0002\u0010\u0018J\u0006\u0010\u0019\u001a\u00020\u0011J\u000f\u0010\u001a\u001a\u0004\u0018\u00018\u0000H\u0001¢\u0006\u0002\u0010\u001bJ\r\u0010\u001c\u001a\u0004\u0018\u00018\u0000¢\u0006\u0002\u0010\u001bJ\u0015\u0010\u001d\u001a\n\u0012\u0006\u0012\u0004\u0018\u00018\u00000\bH\u0002¢\u0006\u0002\u0010\u001eJ\u0013\u0010\u001f\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00028\u0000¢\u0006\u0002\u0010 J\u0015\u0010!\u001a\u00028\u00002\u0006\u0010\"\u001a\u00020\u000eH\u0001¢\u0006\u0002\u0010#J$\u0010$\u001a\u0004\u0018\u00018\u00002\u0012\u0010%\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u000b0&H\b¢\u0006\u0002\u0010'J\r\u0010(\u001a\u0004\u0018\u00018\u0000¢\u0006\u0002\u0010\u001bJ\u0011\u0010)\u001a\u00020\u00112\u0006\u0010*\u001a\u00020\u000eH\u0010J\u0011\u0010+\u001a\u00020\u00112\u0006\u0010*\u001a\u00020\u000eH\u0010J\u0018\u0010,\u001a\u00020\u00112\u0006\u0010*\u001a\u00020\u000e2\u0006\u0010-\u001a\u00020\u000eH\u0002R\u001a\u0010\u0007\u001a\f\u0012\u0006\u0012\u0004\u0018\u00018\u0000\u0018\u00010\bX\u000e¢\u0006\u0004\n\u0002\u0010\tR\u0011\u0010\n\u001a\u00020\u000b8F¢\u0006\u0006\u001a\u0004\b\n\u0010\fR\u0018\u0010\r\u001a\u00020\u000e8\u0000@\u0000X\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u000f\u0010\u0006¨\u0006."}, d2 = {"Lkotlinx/coroutines/internal/ThreadSafeHeap;", "T", "Lkotlinx/coroutines/internal/ThreadSafeHeapNode;", "", "", "Lkotlinx/coroutines/internal/SynchronizedObject;", "()V", "a", "", "[Lkotlinx/coroutines/internal/ThreadSafeHeapNode;", "isEmpty", "", "()Z", "size", "", "size$annotations", "addImpl", "", "node", "(Lkotlinx/coroutines/internal/ThreadSafeHeapNode;)V", "addLast", "addLastIf", "cond", "Lkotlin/Function0;", "(Lkotlinx/coroutines/internal/ThreadSafeHeapNode;Lkotlin/jvm/functions/Function0;)Z", "clear", "firstImpl", "()Lkotlinx/coroutines/internal/ThreadSafeHeapNode;", "peek", "realloc", "()[Lkotlinx/coroutines/internal/ThreadSafeHeapNode;", "remove", "(Lkotlinx/coroutines/internal/ThreadSafeHeapNode;)Z", "removeAtImpl", "index", "(I)Lkotlinx/coroutines/internal/ThreadSafeHeapNode;", "removeFirstIf", "predicate", "Lkotlin/Function1;", "(Lkotlin/jvm/functions/Function1;)Lkotlinx/coroutines/internal/ThreadSafeHeapNode;", "removeFirstOrNull", "siftDownFrom", "i", "siftUpFrom", "swap", "j", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
/* compiled from: ThreadSafeHeap.kt */
public final class ThreadSafeHeap<T extends ThreadSafeHeapNode & Comparable<? super T>> {
    /* renamed from: a */
    private T[] f9a;
    @JvmField
    public volatile int size;

    @PublishedApi
    public static /* synthetic */ void size$annotations() {
    }

    public final boolean isEmpty() {
        return this.size == 0;
    }

    public final synchronized void clear() {
        Arrays.fill(this.f9a, 0, this.size, null);
        this.size = 0;
    }

    @Nullable
    public final synchronized T peek() {
        return firstImpl();
    }

    @Nullable
    public final synchronized T removeFirstOrNull() {
        return this.size > 0 ? removeAtImpl(0) : null;
    }

    public final synchronized void addLast(@NotNull T t) {
        Intrinsics.checkParameterIsNotNull(t, "node");
        addImpl(t);
    }

    public final synchronized boolean remove(@NotNull T t) {
        boolean z;
        Intrinsics.checkParameterIsNotNull(t, "node");
        z = true;
        Object obj = null;
        if (t.getHeap() == null) {
            z = false;
        } else {
            t = t.getIndex();
            if (t >= null) {
                obj = 1;
            }
            if (obj != null) {
                removeAtImpl(t);
            } else {
                throw new IllegalStateException("Check failed.".toString());
            }
        }
        return z;
    }

    @Nullable
    @PublishedApi
    public final T firstImpl() {
        ThreadSafeHeapNode[] threadSafeHeapNodeArr = this.f9a;
        return threadSafeHeapNodeArr != null ? threadSafeHeapNodeArr[0] : null;
    }

    @NotNull
    @PublishedApi
    public final T removeAtImpl(int i) {
        Object obj = null;
        if ((this.size > 0 ? 1 : null) != null) {
            ThreadSafeHeapNode[] threadSafeHeapNodeArr = this.f9a;
            if (threadSafeHeapNodeArr == null) {
                Intrinsics.throwNpe();
            }
            this.size--;
            if (i < this.size) {
                swap(i, this.size);
                int i2 = (i - 1) / 2;
                if (i > 0) {
                    ThreadSafeHeapNode threadSafeHeapNode = threadSafeHeapNodeArr[i];
                    if (threadSafeHeapNode == null) {
                        Intrinsics.throwNpe();
                    }
                    Comparable comparable = (Comparable) threadSafeHeapNode;
                    Object obj2 = threadSafeHeapNodeArr[i2];
                    if (obj2 == null) {
                        Intrinsics.throwNpe();
                    }
                    if (comparable.compareTo(obj2) < 0) {
                        swap(i, i2);
                        siftUpFrom(i2);
                    }
                }
                siftDownFrom(i);
            }
            i = threadSafeHeapNodeArr[this.size];
            if (i == 0) {
                Intrinsics.throwNpe();
            }
            if (i.getHeap() == this) {
                obj = 1;
            }
            if (obj != null) {
                i.setHeap((ThreadSafeHeap) null);
                i.setIndex(-1);
                threadSafeHeapNodeArr[this.size] = (ThreadSafeHeapNode) null;
                return i;
            }
            throw ((Throwable) new IllegalStateException("Check failed.".toString()));
        }
        throw ((Throwable) new IllegalStateException("Check failed.".toString()));
    }

    @PublishedApi
    public final void addImpl(@NotNull T t) {
        Intrinsics.checkParameterIsNotNull(t, "node");
        if ((t.getHeap() == null ? 1 : null) != null) {
            t.setHeap(this);
            ThreadSafeHeapNode[] realloc = realloc();
            int i = this.size;
            this.size = i + 1;
            realloc[i] = t;
            t.setIndex(i);
            siftUpFrom(i);
            return;
        }
        throw ((Throwable) new IllegalStateException("Check failed.".toString()));
    }

    private final void siftUpFrom(int i) {
        while (i > 0) {
            ThreadSafeHeapNode[] threadSafeHeapNodeArr = this.f9a;
            if (threadSafeHeapNodeArr == null) {
                Intrinsics.throwNpe();
            }
            int i2 = (i - 1) / 2;
            ThreadSafeHeapNode threadSafeHeapNode = threadSafeHeapNodeArr[i2];
            if (threadSafeHeapNode == null) {
                Intrinsics.throwNpe();
            }
            Comparable comparable = (Comparable) threadSafeHeapNode;
            Object obj = threadSafeHeapNodeArr[i];
            if (obj == null) {
                Intrinsics.throwNpe();
            }
            if (comparable.compareTo(obj) > 0) {
                swap(i, i2);
                i = i2;
            } else {
                return;
            }
        }
    }

    private final void siftDownFrom(int i) {
        while (true) {
            int i2 = (i * 2) + 1;
            if (i2 < this.size) {
                ThreadSafeHeapNode[] threadSafeHeapNodeArr = this.f9a;
                if (threadSafeHeapNodeArr == null) {
                    Intrinsics.throwNpe();
                }
                int i3 = i2 + 1;
                if (i3 < this.size) {
                    ThreadSafeHeapNode threadSafeHeapNode = threadSafeHeapNodeArr[i3];
                    if (threadSafeHeapNode == null) {
                        Intrinsics.throwNpe();
                    }
                    Comparable comparable = (Comparable) threadSafeHeapNode;
                    Object obj = threadSafeHeapNodeArr[i2];
                    if (obj == null) {
                        Intrinsics.throwNpe();
                    }
                    if (comparable.compareTo(obj) < 0) {
                        i2 = i3;
                    }
                }
                ThreadSafeHeapNode threadSafeHeapNode2 = threadSafeHeapNodeArr[i];
                if (threadSafeHeapNode2 == null) {
                    Intrinsics.throwNpe();
                }
                Comparable comparable2 = (Comparable) threadSafeHeapNode2;
                Object obj2 = threadSafeHeapNodeArr[i2];
                if (obj2 == null) {
                    Intrinsics.throwNpe();
                }
                if (comparable2.compareTo(obj2) > 0) {
                    swap(i, i2);
                    i = i2;
                } else {
                    return;
                }
            }
            return;
        }
    }

    private final T[] realloc() {
        T[] tArr = this.f9a;
        if (tArr == null) {
            tArr = new ThreadSafeHeapNode[4];
            this.f9a = tArr;
            return tArr;
        } else if (this.size < tArr.length) {
            return tArr;
        } else {
            Object copyOf = Arrays.copyOf(tArr, this.size * 2);
            Intrinsics.checkExpressionValueIsNotNull(copyOf, "java.util.Arrays.copyOf(this, newSize)");
            ThreadSafeHeapNode[] threadSafeHeapNodeArr = (ThreadSafeHeapNode[]) copyOf;
            this.f9a = threadSafeHeapNodeArr;
            return threadSafeHeapNodeArr;
        }
    }

    private final void swap(int i, int i2) {
        ThreadSafeHeapNode[] threadSafeHeapNodeArr = this.f9a;
        if (threadSafeHeapNodeArr == null) {
            Intrinsics.throwNpe();
        }
        ThreadSafeHeapNode threadSafeHeapNode = threadSafeHeapNodeArr[i2];
        if (threadSafeHeapNode == null) {
            Intrinsics.throwNpe();
        }
        ThreadSafeHeapNode threadSafeHeapNode2 = threadSafeHeapNodeArr[i];
        if (threadSafeHeapNode2 == null) {
            Intrinsics.throwNpe();
        }
        threadSafeHeapNodeArr[i] = threadSafeHeapNode;
        threadSafeHeapNodeArr[i2] = threadSafeHeapNode2;
        threadSafeHeapNode.setIndex(i);
        threadSafeHeapNode2.setIndex(i2);
    }

    @Nullable
    public final T removeFirstIf(@NotNull Function1<? super T, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(function1, "predicate");
        synchronized (this) {
            try {
                ThreadSafeHeapNode firstImpl = firstImpl();
                T t = null;
                if (firstImpl != null) {
                    if (((Boolean) function1.invoke(firstImpl)).booleanValue() != null) {
                        t = removeAtImpl(null);
                    }
                    InlineMarker.finallyStart(1);
                    InlineMarker.finallyEnd(1);
                    return t;
                }
                InlineMarker.finallyStart(2);
                InlineMarker.finallyEnd(2);
                return null;
            } catch (Throwable th) {
                InlineMarker.finallyStart(1);
                InlineMarker.finallyEnd(1);
            }
        }
    }

    public final boolean addLastIf(@NotNull T t, @NotNull Function0<Boolean> function0) {
        Intrinsics.checkParameterIsNotNull(t, "node");
        Intrinsics.checkParameterIsNotNull(function0, "cond");
        synchronized (this) {
            try {
                if (((Boolean) function0.invoke()).booleanValue() != null) {
                    addImpl(t);
                    t = true;
                } else {
                    t = null;
                }
                InlineMarker.finallyStart(1);
            } catch (Throwable th) {
                InlineMarker.finallyStart(1);
                InlineMarker.finallyEnd(1);
            }
        }
        InlineMarker.finallyEnd(1);
        return t;
    }
}
