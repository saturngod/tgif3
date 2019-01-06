package kotlinx.coroutines.selects;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.Result;
import kotlin.Result.Companion;
import kotlin.ResultKt;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.JvmField;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CompletedExceptionally;
import kotlinx.coroutines.CompletedExceptionallyKt;
import kotlinx.coroutines.CoroutineExceptionHandlerKt;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.DispatchedKt;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobCancellingNode;
import kotlinx.coroutines.internal.AtomicDesc;
import kotlinx.coroutines.internal.AtomicOp;
import kotlinx.coroutines.internal.LockFreeLinkedListHead;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;
import kotlinx.coroutines.internal.LockFreeLinkedListNode.CondAddOp;
import kotlinx.coroutines.internal.OpDescriptor;
import kotlinx.coroutines.intrinsics.UndispatchedKt;
import kotlinx.coroutines.selects.SelectBuilder.DefaultImpls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@PublishedApi
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0001\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00002\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u00032\b\u0012\u0004\u0012\u0002H\u00010\u00042\b\u0012\u0004\u0012\u0002H\u00010\u0005:\u0003DEFB\u0013\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005¢\u0006\u0002\u0010\u0007J\u0010\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0017H\u0016J\b\u0010\u001e\u001a\u00020\u001cH\u0002J'\u0010\u001f\u001a\u00020\u001c2\u000e\u0010 \u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0!2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u001c0!H\bJ\n\u0010#\u001a\u0004\u0018\u00010\nH\u0001J\u0010\u0010$\u001a\u00020\u001c2\u0006\u0010%\u001a\u00020&H\u0001J\b\u0010'\u001a\u00020\u001cH\u0002J6\u0010(\u001a\u00020\u001c2\u0006\u0010)\u001a\u00020*2\u001c\u0010\"\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0005\u0012\u0006\u0012\u0004\u0018\u00010\n0+H\u0016ø\u0001\u0000¢\u0006\u0002\u0010,J\u0012\u0010-\u001a\u0004\u0018\u00010\n2\u0006\u0010.\u001a\u00020/H\u0016J\u0012\u00100\u001a\u0004\u0018\u00010\n2\u0006\u0010.\u001a\u00020/H\u0016J\u0010\u00101\u001a\u00020\u001c2\u0006\u00102\u001a\u00020&H\u0016J\u001e\u00103\u001a\u00020\u001c2\f\u00104\u001a\b\u0012\u0004\u0012\u00028\u000005H\u0016ø\u0001\u0000¢\u0006\u0002\u00106J\u0012\u00107\u001a\u00020\u00142\b\u00108\u001a\u0004\u0018\u00010\nH\u0016J3\u00109\u001a\u00020\u001c*\u00020:2\u001c\u0010\"\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0005\u0012\u0006\u0012\u0004\u0018\u00010\n0+H\u0002ø\u0001\u0000¢\u0006\u0002\u0010;JE\u00109\u001a\u00020\u001c\"\u0004\b\u0001\u0010<*\b\u0012\u0004\u0012\u0002H<0=2\"\u0010\"\u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H<\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0005\u0012\u0006\u0012\u0004\u0018\u00010\n0>H\u0002ø\u0001\u0000¢\u0006\u0002\u0010?JY\u00109\u001a\u00020\u001c\"\u0004\b\u0001\u0010@\"\u0004\b\u0002\u0010<*\u000e\u0012\u0004\u0012\u0002H@\u0012\u0004\u0012\u0002H<0A2\u0006\u0010B\u001a\u0002H@2\"\u0010\"\u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H<\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0005\u0012\u0006\u0012\u0004\u0018\u00010\n0>H\u0002ø\u0001\u0000¢\u0006\u0002\u0010CR\u0016\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\tX\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\tX\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\u00058VX\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\u00020\u00108VX\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0013\u001a\u00020\u00148VX\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0015R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0018\u001a\u0004\u0018\u00010\n8BX\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aR\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005X\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006G"}, d2 = {"Lkotlinx/coroutines/selects/SelectBuilderImpl;", "R", "Lkotlinx/coroutines/internal/LockFreeLinkedListHead;", "Lkotlinx/coroutines/selects/SelectBuilder;", "Lkotlinx/coroutines/selects/SelectInstance;", "Lkotlin/coroutines/Continuation;", "uCont", "(Lkotlin/coroutines/Continuation;)V", "_result", "Lkotlinx/atomicfu/AtomicRef;", "", "_state", "completion", "getCompletion", "()Lkotlin/coroutines/Continuation;", "context", "Lkotlin/coroutines/CoroutineContext;", "getContext", "()Lkotlin/coroutines/CoroutineContext;", "isSelected", "", "()Z", "parentHandle", "Lkotlinx/coroutines/DisposableHandle;", "state", "getState", "()Ljava/lang/Object;", "disposeOnSelect", "", "handle", "doAfterSelect", "doResume", "value", "Lkotlin/Function0;", "block", "getResult", "handleBuilderException", "e", "", "initCancellability", "onTimeout", "timeMillis", "", "Lkotlin/Function1;", "(JLkotlin/jvm/functions/Function1;)V", "performAtomicIfNotSelected", "desc", "Lkotlinx/coroutines/internal/AtomicDesc;", "performAtomicTrySelect", "resumeSelectCancellableWithException", "exception", "resumeWith", "result", "Lkotlin/Result;", "(Ljava/lang/Object;)V", "trySelect", "idempotent", "invoke", "Lkotlinx/coroutines/selects/SelectClause0;", "(Lkotlinx/coroutines/selects/SelectClause0;Lkotlin/jvm/functions/Function1;)V", "Q", "Lkotlinx/coroutines/selects/SelectClause1;", "Lkotlin/Function2;", "(Lkotlinx/coroutines/selects/SelectClause1;Lkotlin/jvm/functions/Function2;)V", "P", "Lkotlinx/coroutines/selects/SelectClause2;", "param", "(Lkotlinx/coroutines/selects/SelectClause2;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)V", "AtomicSelectOp", "DisposeNode", "SelectOnCancelling", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
/* compiled from: Select.kt */
public final class SelectBuilderImpl<R> extends LockFreeLinkedListHead implements SelectBuilder<R>, SelectInstance<R>, Continuation<R> {
    static final AtomicReferenceFieldUpdater _result$FU = AtomicReferenceFieldUpdater.newUpdater(SelectBuilderImpl.class, Object.class, "_result");
    static final AtomicReferenceFieldUpdater _state$FU = AtomicReferenceFieldUpdater.newUpdater(SelectBuilderImpl.class, Object.class, "_state");
    volatile Object _result = SelectKt.UNDECIDED;
    volatile Object _state = this;
    private volatile DisposableHandle parentHandle;
    private final Continuation<R> uCont;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0010\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lkotlinx/coroutines/selects/SelectBuilderImpl$DisposeNode;", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "handle", "Lkotlinx/coroutines/DisposableHandle;", "(Lkotlinx/coroutines/DisposableHandle;)V", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
    /* compiled from: Select.kt */
    private static final class DisposeNode extends LockFreeLinkedListNode {
        @NotNull
        @JvmField
        public final DisposableHandle handle;

        public DisposeNode(@NotNull DisposableHandle disposableHandle) {
            Intrinsics.checkParameterIsNotNull(disposableHandle, "handle");
            this.handle = disposableHandle;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\b\u0004\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u001c\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u00022\b\u0010\u000b\u001a\u0004\u0018\u00010\u0002H\u0016J\u0012\u0010\f\u001a\u00020\t2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0002H\u0002J\u0014\u0010\r\u001a\u0004\u0018\u00010\u00022\b\u0010\n\u001a\u0004\u0018\u00010\u0002H\u0016J\b\u0010\u000e\u001a\u0004\u0018\u00010\u0002R\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u00020\u00068\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lkotlinx/coroutines/selects/SelectBuilderImpl$AtomicSelectOp;", "Lkotlinx/coroutines/internal/AtomicOp;", "", "desc", "Lkotlinx/coroutines/internal/AtomicDesc;", "select", "", "(Lkotlinx/coroutines/selects/SelectBuilderImpl;Lkotlinx/coroutines/internal/AtomicDesc;Z)V", "complete", "", "affected", "failure", "completeSelect", "prepare", "prepareIfNotSelected", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
    /* compiled from: Select.kt */
    private final class AtomicSelectOp extends AtomicOp<Object> {
        @NotNull
        @JvmField
        public final AtomicDesc desc;
        @JvmField
        public final boolean select;
        final /* synthetic */ SelectBuilderImpl this$0;

        public AtomicSelectOp(@NotNull SelectBuilderImpl selectBuilderImpl, AtomicDesc atomicDesc, boolean z) {
            Intrinsics.checkParameterIsNotNull(atomicDesc, "desc");
            this.this$0 = selectBuilderImpl;
            this.desc = atomicDesc;
            this.select = z;
        }

        @Nullable
        public Object prepare(@Nullable Object obj) {
            if (obj == null) {
                obj = prepareIfNotSelected();
                if (obj != null) {
                    return obj;
                }
            }
            return this.desc.prepare(this);
        }

        public void complete(@Nullable Object obj, @Nullable Object obj2) {
            completeSelect(obj2);
            this.desc.complete(this, obj2);
        }

        @Nullable
        public final Object prepareIfNotSelected() {
            SelectBuilderImpl selectBuilderImpl = this.this$0;
            while (true) {
                AtomicSelectOp atomicSelectOp = selectBuilderImpl._state;
                if (atomicSelectOp == this) {
                    return null;
                }
                if (atomicSelectOp instanceof OpDescriptor) {
                    atomicSelectOp.perform(this.this$0);
                } else if (atomicSelectOp != this.this$0) {
                    return SelectKt.getALREADY_SELECTED();
                } else {
                    if (SelectBuilderImpl._state$FU.compareAndSet(this.this$0, this.this$0, this)) {
                        return null;
                    }
                }
            }
        }

        private final void completeSelect(Object obj) {
            Object obj2;
            obj = (this.select && obj == null) ? true : null;
            if (obj != null) {
                obj2 = null;
            } else {
                obj2 = this.this$0;
            }
            if (SelectBuilderImpl._state$FU.compareAndSet(this.this$0, this, obj2) && obj != null) {
                this.this$0.doAfterSelect();
            }
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0002J\b\u0010\t\u001a\u00020\nH\u0016¨\u0006\u000b"}, d2 = {"Lkotlinx/coroutines/selects/SelectBuilderImpl$SelectOnCancelling;", "Lkotlinx/coroutines/JobCancellingNode;", "Lkotlinx/coroutines/Job;", "job", "(Lkotlinx/coroutines/selects/SelectBuilderImpl;Lkotlinx/coroutines/Job;)V", "invoke", "", "cause", "", "toString", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
    /* compiled from: Select.kt */
    private final class SelectOnCancelling extends JobCancellingNode<Job> {
        final /* synthetic */ SelectBuilderImpl this$0;

        public SelectOnCancelling(@NotNull SelectBuilderImpl selectBuilderImpl, Job job) {
            Intrinsics.checkParameterIsNotNull(job, "job");
            this.this$0 = selectBuilderImpl;
            super(job);
        }

        public void invoke(@Nullable Throwable th) {
            if (this.this$0.trySelect(null) != null) {
                this.this$0.resumeSelectCancellableWithException(this.job.getCancellationException());
            }
        }

        @NotNull
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("SelectOnCancelling[");
            stringBuilder.append(this.this$0);
            stringBuilder.append(']');
            return stringBuilder.toString();
        }
    }

    public <P, Q> void invoke(@NotNull SelectClause2<? super P, ? extends Q> selectClause2, @NotNull Function2<? super Q, ? super Continuation<? super R>, ? extends Object> function2) {
        Intrinsics.checkParameterIsNotNull(selectClause2, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function2, "block");
        DefaultImpls.invoke(this, selectClause2, function2);
    }

    public SelectBuilderImpl(@NotNull Continuation<? super R> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "uCont");
        this.uCont = continuation;
    }

    @NotNull
    public CoroutineContext getContext() {
        return this.uCont.getContext();
    }

    @NotNull
    public Continuation<R> getCompletion() {
        return this;
    }

    private final void doResume(Function0<? extends Object> function0, Function0<Unit> function02) {
        if (isSelected()) {
            while (true) {
                Object obj = this._result;
                if (obj == SelectKt.UNDECIDED) {
                    if (_result$FU.compareAndSet(this, SelectKt.UNDECIDED, function0.invoke())) {
                        return;
                    }
                } else if (obj != IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                    throw ((Throwable) new IllegalStateException("Already resumed"));
                } else if (_result$FU.compareAndSet(this, IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED(), SelectKt.RESUMED)) {
                    function02.invoke();
                    return;
                }
            }
        } else {
            throw ((Throwable) new IllegalStateException("Must be selected first".toString()));
        }
    }

    @Nullable
    @PublishedApi
    public final Object getResult() {
        if (!isSelected()) {
            initCancellability();
        }
        Object obj = this._result;
        if (obj == SelectKt.UNDECIDED) {
            if (_result$FU.compareAndSet(this, SelectKt.UNDECIDED, IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED())) {
                return IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            }
            obj = this._result;
        }
        if (obj == SelectKt.RESUMED) {
            throw new IllegalStateException("Already resumed");
        } else if (!(obj instanceof CompletedExceptionally)) {
            return obj;
        } else {
            throw ((CompletedExceptionally) obj).cause;
        }
    }

    private final void initCancellability() {
        Job job = (Job) getContext().get(Job.Key);
        if (job != null) {
            DisposableHandle invokeOnCompletion$default = Job.DefaultImpls.invokeOnCompletion$default(job, true, false, new SelectOnCancelling(this, job), 2, null);
            this.parentHandle = invokeOnCompletion$default;
            if (isSelected()) {
                invokeOnCompletion$default.dispose();
            }
        }
    }

    @PublishedApi
    public final void handleBuilderException(@NotNull Throwable th) {
        Intrinsics.checkParameterIsNotNull(th, "e");
        if (trySelect(null)) {
            Companion companion = Result.Companion;
            resumeWith(Result.constructor-impl(ResultKt.createFailure(th)));
            return;
        }
        CoroutineExceptionHandlerKt.handleCoroutineException$default(getContext(), th, null, 4, null);
    }

    public boolean isSelected() {
        return getState() != ((SelectBuilderImpl) this);
    }

    public void disposeOnSelect(@NotNull DisposableHandle disposableHandle) {
        Intrinsics.checkParameterIsNotNull(disposableHandle, "handle");
        DisposeNode disposeNode = new DisposeNode(disposableHandle);
        while (getState() == this) {
            LockFreeLinkedListNode lockFreeLinkedListNode = disposeNode;
            CondAddOp selectBuilderImpl$disposeOnSelect$$inlined$addLastIf$1 = new SelectBuilderImpl$disposeOnSelect$$inlined$addLastIf$1(lockFreeLinkedListNode, lockFreeLinkedListNode, this);
            while (true) {
                Object prev = getPrev();
                if (prev != null) {
                    Object obj;
                    switch (((LockFreeLinkedListNode) prev).tryCondAddNext(lockFreeLinkedListNode, this, selectBuilderImpl$disposeOnSelect$$inlined$addLastIf$1)) {
                        case 1:
                            obj = 1;
                            continue;
                        case 2:
                            obj = null;
                            continue;
                        default:
                    }
                    if (obj != null) {
                        return;
                    }
                }
                throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */");
            }
        }
        disposableHandle.dispose();
    }

    private final void doAfterSelect() {
        DisposableHandle disposableHandle = this.parentHandle;
        if (disposableHandle != null) {
            disposableHandle.dispose();
        }
        Object next = getNext();
        if (next != null) {
            for (next = (LockFreeLinkedListNode) next; (Intrinsics.areEqual(next, (Object) this) ^ 1) != 0; next = next.getNextNode()) {
                if (next instanceof DisposeNode) {
                    ((DisposeNode) next).handle.dispose();
                }
            }
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */");
    }

    public boolean trySelect(@Nullable Object obj) {
        if ((!(obj instanceof OpDescriptor) ? 1 : null) != null) {
            do {
                SelectBuilderImpl state = getState();
                if (state != this) {
                    return obj != null && state == obj;
                }
            } while (!_state$FU.compareAndSet(this, this, obj));
            doAfterSelect();
            return true;
        }
        throw ((Throwable) new IllegalStateException("cannot use OpDescriptor as idempotent marker".toString()));
    }

    @Nullable
    public Object performAtomicTrySelect(@NotNull AtomicDesc atomicDesc) {
        Intrinsics.checkParameterIsNotNull(atomicDesc, "desc");
        return new AtomicSelectOp(this, atomicDesc, true).perform(null);
    }

    @Nullable
    public Object performAtomicIfNotSelected(@NotNull AtomicDesc atomicDesc) {
        Intrinsics.checkParameterIsNotNull(atomicDesc, "desc");
        return new AtomicSelectOp(this, atomicDesc, false).perform(null);
    }

    public void invoke(@NotNull SelectClause0 selectClause0, @NotNull Function1<? super Continuation<? super R>, ? extends Object> function1) {
        Intrinsics.checkParameterIsNotNull(selectClause0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "block");
        selectClause0.registerSelectClause0(this, function1);
    }

    public <Q> void invoke(@NotNull SelectClause1<? extends Q> selectClause1, @NotNull Function2<? super Q, ? super Continuation<? super R>, ? extends Object> function2) {
        Intrinsics.checkParameterIsNotNull(selectClause1, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function2, "block");
        selectClause1.registerSelectClause1(this, function2);
    }

    public <P, Q> void invoke(@NotNull SelectClause2<? super P, ? extends Q> selectClause2, P p, @NotNull Function2<? super Q, ? super Continuation<? super R>, ? extends Object> function2) {
        Intrinsics.checkParameterIsNotNull(selectClause2, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function2, "block");
        selectClause2.registerSelectClause2(this, p, function2);
    }

    public void onTimeout(long j, @NotNull Function1<? super Continuation<? super R>, ? extends Object> function1) {
        Intrinsics.checkParameterIsNotNull(function1, "block");
        if (j <= 0) {
            if (trySelect(0) != null) {
                UndispatchedKt.startCoroutineUnintercepted(function1, getCompletion());
            }
            return;
        }
        disposeOnSelect(DelayKt.getDelay(getContext()).invokeOnTimeout(j, new SelectBuilderImpl$onTimeout$$inlined$Runnable$1(this, function1)));
    }

    public void resumeWith(@NotNull Object obj) {
        if (isSelected()) {
            while (true) {
                Object obj2 = this._result;
                if (obj2 == SelectKt.UNDECIDED) {
                    if (_result$FU.compareAndSet(this, SelectKt.UNDECIDED, CompletedExceptionallyKt.toState(obj))) {
                        return;
                    }
                } else if (obj2 != IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                    throw ((Throwable) new IllegalStateException("Already resumed"));
                } else if (_result$FU.compareAndSet(this, IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED(), SelectKt.RESUMED)) {
                    this.uCont.resumeWith(obj);
                    return;
                }
            }
        } else {
            throw ((Throwable) new IllegalStateException("Must be selected first".toString()));
        }
    }

    public void resumeSelectCancellableWithException(@NotNull Throwable th) {
        Intrinsics.checkParameterIsNotNull(th, "exception");
        if (isSelected()) {
            while (true) {
                Object obj = this._result;
                if (obj == SelectKt.UNDECIDED) {
                    if (_result$FU.compareAndSet(this, SelectKt.UNDECIDED, new CompletedExceptionally(th))) {
                        return;
                    }
                } else if (obj != IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                    throw new IllegalStateException("Already resumed");
                } else if (_result$FU.compareAndSet(this, IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED(), SelectKt.RESUMED)) {
                    DispatchedKt.resumeCancellableWithException(IntrinsicsKt__IntrinsicsJvmKt.intercepted(this.uCont), th);
                    return;
                }
            }
        } else {
            throw new IllegalStateException("Must be selected first".toString());
        }
    }

    private final Object getState() {
        while (true) {
            Object obj = this._state;
            if (!(obj instanceof OpDescriptor)) {
                return obj;
            }
            ((OpDescriptor) obj).perform(this);
        }
    }
}
