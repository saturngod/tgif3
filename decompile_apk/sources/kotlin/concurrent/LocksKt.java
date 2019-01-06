package kotlin.concurrent;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import kotlin.Metadata;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.InlineMarker;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a&\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0004H\b¢\u0006\u0002\u0010\u0005\u001a&\u0010\u0006\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u00072\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0004H\b¢\u0006\u0002\u0010\b\u001a&\u0010\t\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0004H\b¢\u0006\u0002\u0010\u0005¨\u0006\n"}, d2 = {"read", "T", "Ljava/util/concurrent/locks/ReentrantReadWriteLock;", "action", "Lkotlin/Function0;", "(Ljava/util/concurrent/locks/ReentrantReadWriteLock;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "withLock", "Ljava/util/concurrent/locks/Lock;", "(Ljava/util/concurrent/locks/Lock;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "write", "kotlin-stdlib"}, k = 2, mv = {1, 1, 13})
@JvmName(name = "LocksKt")
/* compiled from: Locks.kt */
public final class LocksKt {
    @kotlin.internal.InlineOnly
    private static final <T> T write(@org.jetbrains.annotations.NotNull java.util.concurrent.locks.ReentrantReadWriteLock r4, kotlin.jvm.functions.Function0<? extends T> r5) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Incorrect nodes count for selectOther: B:20:0x0044 in [B:15:0x003e, B:20:0x0044, B:19:0x0031]
	at jadx.core.utils.BlockUtils.selectOther(BlockUtils.java:53)
	at jadx.core.dex.instructions.IfNode.initBlocks(IfNode.java:64)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.initBlocksInIfNodes(BlockFinish.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.visit(BlockFinish.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1603198149.run(Unknown Source)
*/
        /*
        r0 = r4.readLock();
        r1 = r4.getWriteHoldCount();
        r2 = 0;
        if (r1 != 0) goto L_0x0010;
    L_0x000b:
        r1 = r4.getReadHoldCount();
        goto L_0x0011;
    L_0x0010:
        r1 = 0;
    L_0x0011:
        r3 = 0;
    L_0x0012:
        if (r3 >= r1) goto L_0x001a;
    L_0x0014:
        r0.unlock();
        r3 = r3 + 1;
        goto L_0x0012;
    L_0x001a:
        r4 = r4.writeLock();
        r4.lock();
        r3 = 1;
        r5 = r5.invoke();	 Catch:{ all -> 0x0038 }
        kotlin.jvm.internal.InlineMarker.finallyStart(r3);
    L_0x0029:
        if (r2 >= r1) goto L_0x0031;
    L_0x002b:
        r0.lock();
        r2 = r2 + 1;
        goto L_0x0029;
    L_0x0031:
        r4.unlock();
        kotlin.jvm.internal.InlineMarker.finallyEnd(r3);
        return r5;
    L_0x0038:
        r5 = move-exception;
        kotlin.jvm.internal.InlineMarker.finallyStart(r3);
    L_0x003c:
        if (r2 >= r1) goto L_0x0044;
    L_0x003e:
        r0.lock();
        r2 = r2 + 1;
        goto L_0x003c;
    L_0x0044:
        r4.unlock();
        kotlin.jvm.internal.InlineMarker.finallyEnd(r3);
        throw r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.concurrent.LocksKt.write(java.util.concurrent.locks.ReentrantReadWriteLock, kotlin.jvm.functions.Function0):T");
    }

    @InlineOnly
    private static final <T> T withLock(@NotNull Lock lock, Function0<? extends T> function0) {
        lock.lock();
        try {
            function0 = function0.invoke();
            return function0;
        } finally {
            InlineMarker.finallyStart(1);
            lock.unlock();
            InlineMarker.finallyEnd(1);
        }
    }

    @InlineOnly
    private static final <T> T read(@NotNull ReentrantReadWriteLock reentrantReadWriteLock, Function0<? extends T> function0) {
        reentrantReadWriteLock = reentrantReadWriteLock.readLock();
        reentrantReadWriteLock.lock();
        try {
            function0 = function0.invoke();
            return function0;
        } finally {
            InlineMarker.finallyStart(1);
            reentrantReadWriteLock.unlock();
            InlineMarker.finallyEnd(1);
        }
    }
}
