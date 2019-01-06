package android.support.v4.content;

import android.os.Binder;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

abstract class ModernAsyncTask<Params, Progress, Result> {
    private static final int CORE_POOL_SIZE = 5;
    private static final int KEEP_ALIVE = 1;
    private static final String LOG_TAG = "AsyncTask";
    private static final int MAXIMUM_POOL_SIZE = 128;
    private static final int MESSAGE_POST_PROGRESS = 2;
    private static final int MESSAGE_POST_RESULT = 1;
    public static final Executor THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(5, 128, 1, TimeUnit.SECONDS, sPoolWorkQueue, sThreadFactory);
    private static volatile Executor sDefaultExecutor = THREAD_POOL_EXECUTOR;
    private static InternalHandler sHandler;
    private static final BlockingQueue<Runnable> sPoolWorkQueue = new LinkedBlockingQueue(10);
    private static final ThreadFactory sThreadFactory = new C00751();
    final AtomicBoolean mCancelled = new AtomicBoolean();
    private final FutureTask<Result> mFuture = new FutureTask<Result>(this.mWorker) {
        protected void done() {
            /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1603198149.run(Unknown Source)
*/
            /*
            r3 = this;
            r0 = r3.get();	 Catch:{ InterruptedException -> 0x0027, ExecutionException -> 0x001a, CancellationException -> 0x0013, Throwable -> 0x000a }
            r1 = android.support.v4.content.ModernAsyncTask.this;	 Catch:{ InterruptedException -> 0x0027, ExecutionException -> 0x001a, CancellationException -> 0x0013, Throwable -> 0x000a }
            r1.postResultIfNotInvoked(r0);	 Catch:{ InterruptedException -> 0x0027, ExecutionException -> 0x001a, CancellationException -> 0x0013, Throwable -> 0x000a }
            goto L_0x002d;
        L_0x000a:
            r0 = move-exception;
            r1 = new java.lang.RuntimeException;
            r2 = "An error occurred while executing doInBackground()";
            r1.<init>(r2, r0);
            throw r1;
        L_0x0013:
            r0 = android.support.v4.content.ModernAsyncTask.this;
            r1 = 0;
            r0.postResultIfNotInvoked(r1);
            goto L_0x002d;
        L_0x001a:
            r0 = move-exception;
            r1 = new java.lang.RuntimeException;
            r0 = r0.getCause();
            r2 = "An error occurred while executing doInBackground()";
            r1.<init>(r2, r0);
            throw r1;
        L_0x0027:
            r0 = move-exception;
            r1 = "AsyncTask";
            android.util.Log.w(r1, r0);
        L_0x002d:
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.v4.content.ModernAsyncTask.3.done():void");
        }
    };
    private volatile Status mStatus = Status.PENDING;
    final AtomicBoolean mTaskInvoked = new AtomicBoolean();
    private final WorkerRunnable<Params, Result> mWorker = new C02772();

    /* renamed from: android.support.v4.content.ModernAsyncTask$1 */
    static class C00751 implements ThreadFactory {
        private final AtomicInteger mCount = new AtomicInteger(1);

        C00751() {
        }

        public Thread newThread(Runnable runnable) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("ModernAsyncTask #");
            stringBuilder.append(this.mCount.getAndIncrement());
            return new Thread(runnable, stringBuilder.toString());
        }
    }

    private static class AsyncTaskResult<Data> {
        final Data[] mData;
        final ModernAsyncTask mTask;

        AsyncTaskResult(ModernAsyncTask modernAsyncTask, Data... dataArr) {
            this.mTask = modernAsyncTask;
            this.mData = dataArr;
        }
    }

    private static class InternalHandler extends Handler {
        InternalHandler() {
            super(Looper.getMainLooper());
        }

        public void handleMessage(Message message) {
            AsyncTaskResult asyncTaskResult = (AsyncTaskResult) message.obj;
            switch (message.what) {
                case 1:
                    asyncTaskResult.mTask.finish(asyncTaskResult.mData[0]);
                    return;
                case 2:
                    asyncTaskResult.mTask.onProgressUpdate(asyncTaskResult.mData);
                    return;
                default:
                    return;
            }
        }
    }

    public enum Status {
        PENDING,
        RUNNING,
        FINISHED
    }

    private static abstract class WorkerRunnable<Params, Result> implements Callable<Result> {
        Params[] mParams;

        WorkerRunnable() {
        }
    }

    /* renamed from: android.support.v4.content.ModernAsyncTask$2 */
    class C02772 extends WorkerRunnable<Params, Result> {
        C02772() {
        }

        public Result call() throws Exception {
            Throwable th;
            Throwable th2;
            ModernAsyncTask.this.mTaskInvoked.set(true);
            Object obj = null;
            try {
                Process.setThreadPriority(10);
                Result doInBackground = ModernAsyncTask.this.doInBackground(this.mParams);
                try {
                    Binder.flushPendingCommands();
                    ModernAsyncTask.this.postResult(doInBackground);
                    return doInBackground;
                } catch (Throwable th3) {
                    th2 = th3;
                    obj = doInBackground;
                    ModernAsyncTask.this.postResult(obj);
                    throw th2;
                }
            } catch (Throwable th4) {
                th = th4;
                ModernAsyncTask.this.mCancelled.set(true);
                throw th;
            }
        }
    }

    protected abstract Result doInBackground(Params... paramsArr);

    protected void onCancelled() {
    }

    protected void onPostExecute(Result result) {
    }

    protected void onPreExecute() {
    }

    protected void onProgressUpdate(Progress... progressArr) {
    }

    private static Handler getHandler() {
        Handler handler;
        synchronized (ModernAsyncTask.class) {
            if (sHandler == null) {
                sHandler = new InternalHandler();
            }
            handler = sHandler;
        }
        return handler;
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public static void setDefaultExecutor(Executor executor) {
        sDefaultExecutor = executor;
    }

    ModernAsyncTask() {
    }

    void postResultIfNotInvoked(Result result) {
        if (!this.mTaskInvoked.get()) {
            postResult(result);
        }
    }

    Result postResult(Result result) {
        getHandler().obtainMessage(1, new AsyncTaskResult(this, result)).sendToTarget();
        return result;
    }

    public final Status getStatus() {
        return this.mStatus;
    }

    protected void onCancelled(Result result) {
        onCancelled();
    }

    public final boolean isCancelled() {
        return this.mCancelled.get();
    }

    public final boolean cancel(boolean z) {
        this.mCancelled.set(true);
        return this.mFuture.cancel(z);
    }

    public final Result get() throws InterruptedException, ExecutionException {
        return this.mFuture.get();
    }

    public final Result get(long j, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
        return this.mFuture.get(j, timeUnit);
    }

    public final ModernAsyncTask<Params, Progress, Result> execute(Params... paramsArr) {
        return executeOnExecutor(sDefaultExecutor, paramsArr);
    }

    public final ModernAsyncTask<Params, Progress, Result> executeOnExecutor(Executor executor, Params... paramsArr) {
        if (this.mStatus != Status.PENDING) {
            switch (this.mStatus) {
                case RUNNING:
                    throw new IllegalStateException("Cannot execute task: the task is already running.");
                case FINISHED:
                    throw new IllegalStateException("Cannot execute task: the task has already been executed (a task can be executed only once)");
                default:
                    throw new IllegalStateException("We should never reach this state");
            }
        }
        this.mStatus = Status.RUNNING;
        onPreExecute();
        this.mWorker.mParams = paramsArr;
        executor.execute(this.mFuture);
        return this;
    }

    public static void execute(Runnable runnable) {
        sDefaultExecutor.execute(runnable);
    }

    protected final void publishProgress(Progress... progressArr) {
        if (!isCancelled()) {
            getHandler().obtainMessage(2, new AsyncTaskResult(this, progressArr)).sendToTarget();
        }
    }

    void finish(Result result) {
        if (isCancelled()) {
            onCancelled(result);
        } else {
            onPostExecute(result);
        }
        this.mStatus = Status.FINISHED;
    }
}
