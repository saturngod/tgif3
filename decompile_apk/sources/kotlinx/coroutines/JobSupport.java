package kotlinx.coroutines;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.CoroutineContext.Element;
import kotlin.coroutines.CoroutineContext.Key;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlinx.coroutines.Job.DefaultImpls;
import kotlinx.coroutines.internal.ConcurrentKt;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;
import kotlinx.coroutines.internal.LockFreeLinkedListNode.CondAddOp;
import kotlinx.coroutines.internal.OpDescriptor;
import kotlinx.coroutines.intrinsics.CancellableKt;
import kotlinx.coroutines.intrinsics.UndispatchedKt;
import kotlinx.coroutines.selects.SelectClause0;
import kotlinx.coroutines.selects.SelectInstance;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000è\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0001\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0017\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004:\u0006¤\u0001¥\u0001¦\u0001B\r\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J$\u0010-\u001a\u00020\u00062\u0006\u0010.\u001a\u00020\n2\u0006\u0010/\u001a\u0002002\n\u00101\u001a\u0006\u0012\u0002\b\u000302H\u0002J\u000e\u00103\u001a\u00020\"2\u0006\u00104\u001a\u00020\u0002J\u0015\u00105\u001a\u0004\u0018\u00010\nH@ø\u0001\u0000¢\u0006\u0004\b6\u00107J\u0013\u00108\u001a\u0004\u0018\u00010\nH@ø\u0001\u0000¢\u0006\u0002\u00107J\b\u00109\u001a\u00020:H\u0016J\u0012\u00109\u001a\u00020\u00062\b\u0010;\u001a\u0004\u0018\u00010'H\u0016J\u0012\u0010<\u001a\u00020\u00062\b\u0010;\u001a\u0004\u0018\u00010\nH\u0002J\u0012\u0010=\u001a\u00020\u00062\b\u0010;\u001a\u0004\u0018\u00010\nH\u0002J\u0010\u0010>\u001a\u00020\u00062\u0006\u0010;\u001a\u00020'H\u0002J\u0010\u0010?\u001a\u00020\u00062\u0006\u0010;\u001a\u00020'H\u0016J*\u0010@\u001a\u00020:2\u0006\u0010#\u001a\u00020+2\b\u0010A\u001a\u0004\u0018\u00010\n2\u0006\u0010B\u001a\u00020C2\u0006\u0010D\u001a\u00020\u0006H\u0002J\"\u0010E\u001a\u00020:2\u0006\u0010#\u001a\u00020F2\u0006\u0010G\u001a\u00020H2\b\u0010I\u001a\u0004\u0018\u00010\nH\u0002J\u0012\u0010J\u001a\u00020'2\b\u0010;\u001a\u0004\u0018\u00010\nH\u0002J\b\u0010K\u001a\u00020LH\u0002J\u0012\u0010M\u001a\u0004\u0018\u00010H2\u0006\u0010#\u001a\u00020+H\u0002J\n\u0010N\u001a\u00060Oj\u0002`PJ\b\u0010Q\u001a\u00020'H\u0016J\u000f\u0010R\u001a\u0004\u0018\u00010\nH\u0000¢\u0006\u0002\bSJ\n\u0010T\u001a\u0004\u0018\u00010'H\u0004J\b\u0010U\u001a\u0004\u0018\u00010'J \u0010V\u001a\u0004\u0018\u00010'2\u0006\u0010#\u001a\u00020F2\f\u0010W\u001a\b\u0012\u0004\u0012\u00020'0XH\u0002J\u0012\u0010Y\u001a\u0004\u0018\u0001002\u0006\u0010#\u001a\u00020+H\u0002J\u0010\u0010Z\u001a\u00020:2\u0006\u0010[\u001a\u00020'H\u0014J\u0015\u0010\\\u001a\u00020:2\u0006\u0010[\u001a\u00020'H\u0010¢\u0006\u0002\b]J\u0017\u0010^\u001a\u00020:2\b\u0010_\u001a\u0004\u0018\u00010\u0001H\u0000¢\u0006\u0002\b`J?\u0010a\u001a\u00020b2\u0006\u0010c\u001a\u00020\u00062\u0006\u0010d\u001a\u00020\u00062'\u0010e\u001a#\u0012\u0015\u0012\u0013\u0018\u00010'¢\u0006\f\bg\u0012\b\bh\u0012\u0004\b\b(;\u0012\u0004\u0012\u00020:0fj\u0002`iJ/\u0010a\u001a\u00020b2'\u0010e\u001a#\u0012\u0015\u0012\u0013\u0018\u00010'¢\u0006\f\bg\u0012\b\bh\u0012\u0004\b\b(;\u0012\u0004\u0012\u00020:0fj\u0002`iJ\u0011\u0010j\u001a\u00020:H@ø\u0001\u0000¢\u0006\u0002\u00107J\b\u0010k\u001a\u00020\u0006H\u0002J\u0011\u0010l\u001a\u00020:H@ø\u0001\u0000¢\u0006\u0002\u00107J\u001f\u0010m\u001a\u00020n2\u0014\u0010o\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\n\u0012\u0004\u0012\u00020:0fH\bJ\u0012\u0010p\u001a\u00020\u00062\b\u0010;\u001a\u0004\u0018\u00010\nH\u0002J\u0017\u0010q\u001a\u00020\u00062\b\u0010I\u001a\u0004\u0018\u00010\nH\u0000¢\u0006\u0002\brJ\u001f\u0010s\u001a\u00020\u00062\b\u0010I\u001a\u0004\u0018\u00010\n2\u0006\u0010B\u001a\u00020CH\u0000¢\u0006\u0002\btJ=\u0010u\u001a\u0006\u0012\u0002\b\u0003022'\u0010e\u001a#\u0012\u0015\u0012\u0013\u0018\u00010'¢\u0006\f\bg\u0012\b\bh\u0012\u0004\b\b(;\u0012\u0004\u0012\u00020:0fj\u0002`i2\u0006\u0010c\u001a\u00020\u0006H\u0002J\r\u0010v\u001a\u00020wH\u0010¢\u0006\u0002\bxJ\u0018\u0010y\u001a\u00020:2\u0006\u0010/\u001a\u0002002\u0006\u0010;\u001a\u00020'H\u0002J+\u0010z\u001a\u00020:\"\u000e\b\u0000\u0010{\u0018\u0001*\u0006\u0012\u0002\b\u0003022\u0006\u0010/\u001a\u0002002\b\u0010;\u001a\u0004\u0018\u00010'H\bJ\u0012\u0010|\u001a\u00020:2\b\u0010;\u001a\u0004\u0018\u00010'H\u0014J'\u0010}\u001a\u00020:2\b\u0010#\u001a\u0004\u0018\u00010\n2\u0006\u0010B\u001a\u00020C2\u0006\u0010D\u001a\u00020\u0006H\u0010¢\u0006\u0002\b~J\u000e\u0010\u001a\u00020:H\u0010¢\u0006\u0003\b\u0001J\u0010\u0010\u0001\u001a\u00020:2\u0007\u0010\u0001\u001a\u00020\u0003J\u0012\u0010\u0001\u001a\u00020:2\u0007\u0010#\u001a\u00030\u0001H\u0002J\u0015\u0010\u0001\u001a\u00020:2\n\u0010#\u001a\u0006\u0012\u0002\b\u000302H\u0002JH\u0010\u0001\u001a\u00020:\"\u0005\b\u0000\u0010\u00012\u000f\u0010\u0001\u001a\n\u0012\u0005\u0012\u0003H\u00010\u00012\u001e\u0010o\u001a\u001a\b\u0001\u0012\f\u0012\n\u0012\u0005\u0012\u0003H\u00010\u0001\u0012\u0006\u0012\u0004\u0018\u00010\n0fø\u0001\u0000¢\u0006\u0003\u0010\u0001JZ\u0010\u0001\u001a\u00020:\"\u0004\b\u0000\u0010{\"\u0005\b\u0001\u0010\u00012\u000f\u0010\u0001\u001a\n\u0012\u0005\u0012\u0003H\u00010\u00012%\u0010o\u001a!\b\u0001\u0012\u0004\u0012\u0002H{\u0012\f\u0012\n\u0012\u0005\u0012\u0003H\u00010\u0001\u0012\u0006\u0012\u0004\u0018\u00010\n0\u0001H\u0000ø\u0001\u0000¢\u0006\u0006\b\u0001\u0010\u0001J\u001b\u0010\u0001\u001a\u00020:2\n\u00101\u001a\u0006\u0012\u0002\b\u000302H\u0000¢\u0006\u0003\b\u0001JZ\u0010\u0001\u001a\u00020:\"\u0004\b\u0000\u0010{\"\u0005\b\u0001\u0010\u00012\u000f\u0010\u0001\u001a\n\u0012\u0005\u0012\u0003H\u00010\u00012%\u0010o\u001a!\b\u0001\u0012\u0004\u0012\u0002H{\u0012\f\u0012\n\u0012\u0005\u0012\u0003H\u00010\u0001\u0012\u0006\u0012\u0004\u0018\u00010\n0\u0001H\u0000ø\u0001\u0000¢\u0006\u0006\b\u0001\u0010\u0001J\u0007\u0010\u0001\u001a\u00020\u0006J\u0013\u0010\u0001\u001a\u00020C2\b\u0010#\u001a\u0004\u0018\u00010\nH\u0002J\u0013\u0010\u0001\u001a\u00020w2\b\u0010#\u001a\u0004\u0018\u00010\nH\u0002J \u0010\u0001\u001a\u00020\u00062\u0007\u0010\u0001\u001a\u00020'2\f\u0010W\u001a\b\u0012\u0004\u0012\u00020'0XH\u0002J\t\u0010\u0001\u001a\u00020wH\u0016J#\u0010\u0001\u001a\u00020\u00062\u0006\u0010#\u001a\u00020F2\b\u0010I\u001a\u0004\u0018\u00010\n2\u0006\u0010B\u001a\u00020CH\u0002J#\u0010\u0001\u001a\u00020\u00062\u0006\u0010#\u001a\u00020+2\b\u0010A\u001a\u0004\u0018\u00010\n2\u0006\u0010B\u001a\u00020CH\u0002J\u001a\u0010\u0001\u001a\u00020\u00062\u0006\u0010#\u001a\u00020+2\u0007\u0010\u0001\u001a\u00020'H\u0002J%\u0010\u0001\u001a\u00020C2\b\u0010#\u001a\u0004\u0018\u00010\n2\b\u0010I\u001a\u0004\u0018\u00010\n2\u0006\u0010B\u001a\u00020CH\u0002J$\u0010\u0001\u001a\u00020\u00062\u0006\u0010#\u001a\u00020F2\u0006\u00104\u001a\u00020H2\b\u0010I\u001a\u0004\u0018\u00010\nH\u0010J\u0010\u0010\u0001\u001a\u0004\u0018\u00010H*\u00030 \u0001H\u0002J\u0017\u0010¡\u0001\u001a\u00020:*\u0002002\b\u0010;\u001a\u0004\u0018\u00010'H\u0002J\u001a\u0010¢\u0001\u001a\u00060Oj\u0002`P*\u00020'2\u0007\u0010£\u0001\u001a\u00020wH\u0002R\u0016\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\tX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\u00020\u00068TX\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00010\u000f8F¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0012\u001a\u00020\u00068TX\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\rR\u0014\u0010\u0014\u001a\u00020\u00068VX\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\rR\u0011\u0010\u0015\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\u0015\u0010\rR\u0011\u0010\u0016\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\u0016\u0010\rR\u0011\u0010\u0017\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\u0017\u0010\rR\u0015\u0010\u0018\u001a\u0006\u0012\u0002\b\u00030\u00198F¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001bR\u0014\u0010\u001c\u001a\u00020\u00068PX\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\rR\u0011\u0010\u001e\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u001f\u0010 R\u0010\u0010!\u001a\u0004\u0018\u00010\"X\u000e¢\u0006\u0002\n\u0000R\u0016\u0010#\u001a\u0004\u0018\u00010\n8@X\u0004¢\u0006\u0006\u001a\u0004\b$\u0010%R\u001c\u0010&\u001a\u0004\u0018\u00010'*\u0004\u0018\u00010\n8BX\u0004¢\u0006\u0006\u001a\u0004\b(\u0010)R\u0018\u0010*\u001a\u00020\u0006*\u00020+8BX\u0004¢\u0006\u0006\u001a\u0004\b*\u0010,\u0002\u0004\n\u0002\b\u0019¨\u0006§\u0001"}, d2 = {"Lkotlinx/coroutines/JobSupport;", "Lkotlinx/coroutines/Job;", "Lkotlinx/coroutines/ChildJob;", "Lkotlinx/coroutines/ParentJob;", "Lkotlinx/coroutines/selects/SelectClause0;", "active", "", "(Z)V", "_state", "Lkotlinx/atomicfu/AtomicRef;", "", "cancelsParent", "getCancelsParent", "()Z", "children", "Lkotlin/sequences/Sequence;", "getChildren", "()Lkotlin/sequences/Sequence;", "handlesException", "getHandlesException", "isActive", "isCancelled", "isCompleted", "isCompletedExceptionally", "key", "Lkotlin/coroutines/CoroutineContext$Key;", "getKey", "()Lkotlin/coroutines/CoroutineContext$Key;", "onCancelComplete", "getOnCancelComplete$kotlinx_coroutines_core", "onJoin", "getOnJoin", "()Lkotlinx/coroutines/selects/SelectClause0;", "parentHandle", "Lkotlinx/coroutines/ChildHandle;", "state", "getState$kotlinx_coroutines_core", "()Ljava/lang/Object;", "exceptionOrNull", "", "getExceptionOrNull", "(Ljava/lang/Object;)Ljava/lang/Throwable;", "isCancelling", "Lkotlinx/coroutines/Incomplete;", "(Lkotlinx/coroutines/Incomplete;)Z", "addLastAtomic", "expect", "list", "Lkotlinx/coroutines/NodeList;", "node", "Lkotlinx/coroutines/JobNode;", "attachChild", "child", "awaitInternal", "awaitInternal$kotlinx_coroutines_core", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "awaitSuspend", "cancel", "", "cause", "cancelImpl", "cancelMakeCompleting", "cancelParent", "childCancelled", "completeStateFinalization", "update", "mode", "", "suppressed", "continueCompleting", "Lkotlinx/coroutines/JobSupport$Finishing;", "lastChild", "Lkotlinx/coroutines/ChildHandleNode;", "proposedUpdate", "createCauseException", "createJobCancellationException", "Lkotlinx/coroutines/JobCancellationException;", "firstChild", "getCancellationException", "Ljava/util/concurrent/CancellationException;", "Lkotlinx/coroutines/CancellationException;", "getChildJobCancellationCause", "getCompletedInternal", "getCompletedInternal$kotlinx_coroutines_core", "getCompletionCause", "getCompletionExceptionOrNull", "getFinalRootCause", "exceptions", "", "getOrPromoteCancellingList", "handleJobException", "exception", "handleOnCompletionException", "handleOnCompletionException$kotlinx_coroutines_core", "initParentJobInternal", "parent", "initParentJobInternal$kotlinx_coroutines_core", "invokeOnCompletion", "Lkotlinx/coroutines/DisposableHandle;", "onCancelling", "invokeImmediately", "handler", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "Lkotlinx/coroutines/CompletionHandler;", "join", "joinInternal", "joinSuspend", "loopOnState", "", "block", "makeCancelling", "makeCompleting", "makeCompleting$kotlinx_coroutines_core", "makeCompletingOnce", "makeCompletingOnce$kotlinx_coroutines_core", "makeNode", "nameString", "", "nameString$kotlinx_coroutines_core", "notifyCancelling", "notifyHandlers", "T", "onCancellation", "onCompletionInternal", "onCompletionInternal$kotlinx_coroutines_core", "onStartInternal", "onStartInternal$kotlinx_coroutines_core", "parentCancelled", "parentJob", "promoteEmptyToNodeList", "Lkotlinx/coroutines/Empty;", "promoteSingleToNodeList", "registerSelectClause0", "R", "select", "Lkotlinx/coroutines/selects/SelectInstance;", "Lkotlin/coroutines/Continuation;", "(Lkotlinx/coroutines/selects/SelectInstance;Lkotlin/jvm/functions/Function1;)V", "registerSelectClause1Internal", "Lkotlin/Function2;", "registerSelectClause1Internal$kotlinx_coroutines_core", "(Lkotlinx/coroutines/selects/SelectInstance;Lkotlin/jvm/functions/Function2;)V", "removeNode", "removeNode$kotlinx_coroutines_core", "selectAwaitCompletion", "selectAwaitCompletion$kotlinx_coroutines_core", "start", "startInternal", "stateString", "suppressExceptions", "rootCause", "toString", "tryFinalizeFinishingState", "tryFinalizeSimpleState", "tryMakeCancelling", "tryMakeCompleting", "tryWaitForChild", "nextChild", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "notifyCompletion", "toCancellationException", "message", "AwaitContinuation", "ChildCompletion", "Finishing", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
@Deprecated(level = DeprecationLevel.ERROR, message = "This is internal API and may be removed in the future releases")
/* compiled from: JobSupport.kt */
public class JobSupport implements Job, ChildJob, ParentJob, SelectClause0 {
    private static final AtomicReferenceFieldUpdater _state$FU = AtomicReferenceFieldUpdater.newUpdater(JobSupport.class, Object.class, "_state");
    private volatile Object _state;
    private volatile ChildHandle parentHandle;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0003\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0002\u0018\u00002\u00060\u0001j\u0002`\u00022\u00020\u0003B\u001f\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nJ\u000e\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\tJ\u0018\u0010\u0015\u001a\u0012\u0012\u0004\u0012\u00020\t0\u0016j\b\u0012\u0004\u0012\u00020\t`\u0017H\u0002J\u0016\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\t0\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\tJ\b\u0010\u001b\u001a\u00020\u001cH\u0016R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\u0001X\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\u00020\u00078VX\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0011\u0010\u000e\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\rR\u0012\u0010\u0006\u001a\u00020\u00078\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u000f\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\rR\u0014\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\b\u001a\u0004\u0018\u00010\t8\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lkotlinx/coroutines/JobSupport$Finishing;", "", "Lkotlinx/coroutines/internal/SynchronizedObject;", "Lkotlinx/coroutines/Incomplete;", "list", "Lkotlinx/coroutines/NodeList;", "isCompleting", "", "rootCause", "", "(Lkotlinx/coroutines/NodeList;ZLjava/lang/Throwable;)V", "_exceptionsHolder", "isActive", "()Z", "isCancelling", "isSealed", "getList", "()Lkotlinx/coroutines/NodeList;", "addExceptionLocked", "", "exception", "allocateList", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "sealLocked", "", "proposedException", "toString", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
    /* compiled from: JobSupport.kt */
    private static final class Finishing implements Incomplete {
        private volatile Object _exceptionsHolder;
        @JvmField
        public volatile boolean isCompleting;
        @NotNull
        private final NodeList list;
        @Nullable
        @JvmField
        public volatile Throwable rootCause;

        @NotNull
        public NodeList getList() {
            return this.list;
        }

        public Finishing(@NotNull NodeList nodeList, boolean z, @Nullable Throwable th) {
            Intrinsics.checkParameterIsNotNull(nodeList, "list");
            this.list = nodeList;
            this.isCompleting = z;
            this.rootCause = th;
        }

        public final boolean isSealed() {
            return this._exceptionsHolder == JobSupportKt.SEALED;
        }

        public final boolean isCancelling() {
            return this.rootCause != null;
        }

        public boolean isActive() {
            return this.rootCause == null;
        }

        @NotNull
        public final List<Throwable> sealLocked(@Nullable Throwable th) {
            ArrayList allocateList;
            Object obj = this._exceptionsHolder;
            if (obj == null) {
                allocateList = allocateList();
            } else if (obj instanceof Throwable) {
                ArrayList allocateList2 = allocateList();
                allocateList2.add(obj);
                allocateList = allocateList2;
            } else if (!(obj instanceof ArrayList)) {
                th = new StringBuilder();
                th.append("State is ");
                th.append(obj);
                throw new IllegalStateException(th.toString().toString());
            } else if (obj != null) {
                allocateList = (ArrayList) obj;
            } else {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.ArrayList<kotlin.Throwable> /* = java.util.ArrayList<kotlin.Throwable> */");
            }
            Object obj2 = this.rootCause;
            if (obj2 != null) {
                allocateList.add(0, obj2);
            }
            if (!(th == null || (Intrinsics.areEqual((Object) th, obj2) ^ 1) == 0)) {
                allocateList.add(th);
            }
            this._exceptionsHolder = JobSupportKt.SEALED;
            return allocateList;
        }

        public final void addExceptionLocked(@NotNull Throwable th) {
            Intrinsics.checkParameterIsNotNull(th, "exception");
            Throwable th2 = this.rootCause;
            if (th2 == null) {
                this.rootCause = th;
            } else if (th != th2) {
                th2 = this._exceptionsHolder;
                if (th2 == null) {
                    this._exceptionsHolder = th;
                } else if (th2 instanceof Throwable) {
                    if (th != th2) {
                        ArrayList allocateList = allocateList();
                        allocateList.add(th2);
                        allocateList.add(th);
                        this._exceptionsHolder = allocateList;
                    }
                } else if (!(th2 instanceof ArrayList)) {
                    th = new StringBuilder();
                    th.append("State is ");
                    th.append(th2);
                    throw new IllegalStateException(th.toString().toString());
                } else if (th2 != null) {
                    ((ArrayList) th2).add(th);
                } else {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.ArrayList<kotlin.Throwable> /* = java.util.ArrayList<kotlin.Throwable> */");
                }
            }
        }

        private final ArrayList<Throwable> allocateList() {
            return new ArrayList(4);
        }

        @NotNull
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Finishing[cancelling=");
            stringBuilder.append(isCancelling());
            stringBuilder.append(", completing=");
            stringBuilder.append(this.isCompleting);
            stringBuilder.append(", rootCause=");
            stringBuilder.append(this.rootCause);
            stringBuilder.append(", exceptions=");
            stringBuilder.append(this._exceptionsHolder);
            stringBuilder.append(", list=");
            stringBuilder.append(getList());
            stringBuilder.append(']');
            return stringBuilder.toString();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u001b\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010\f\u001a\u00020\rH\u0014R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lkotlinx/coroutines/JobSupport$AwaitContinuation;", "T", "Lkotlinx/coroutines/CancellableContinuationImpl;", "delegate", "Lkotlin/coroutines/Continuation;", "job", "Lkotlinx/coroutines/JobSupport;", "(Lkotlin/coroutines/Continuation;Lkotlinx/coroutines/JobSupport;)V", "getContinuationCancellationCause", "", "parent", "Lkotlinx/coroutines/Job;", "nameString", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
    /* compiled from: JobSupport.kt */
    private static final class AwaitContinuation<T> extends CancellableContinuationImpl<T> {
        private final JobSupport job;

        public AwaitContinuation(@NotNull Continuation<? super T> continuation, @NotNull JobSupport jobSupport) {
            Intrinsics.checkParameterIsNotNull(continuation, "delegate");
            Intrinsics.checkParameterIsNotNull(jobSupport, "job");
            super(continuation, 1);
            this.job = jobSupport;
        }

        @NotNull
        public Throwable getContinuationCancellationCause(@NotNull Job job) {
            Intrinsics.checkParameterIsNotNull(job, "parent");
            Object state$kotlinx_coroutines_core = this.job.getState$kotlinx_coroutines_core();
            if (state$kotlinx_coroutines_core instanceof Finishing) {
                Throwable th = ((Finishing) state$kotlinx_coroutines_core).rootCause;
                if (th != null) {
                    return th;
                }
            }
            if (state$kotlinx_coroutines_core instanceof CompletedExceptionally) {
                return ((CompletedExceptionally) state$kotlinx_coroutines_core).cause;
            }
            return job.getCancellationException();
        }

        @NotNull
        protected String nameString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("AwaitContinuation(");
            stringBuilder.append(DebugKt.toDebugString(getDelegate()));
            stringBuilder.append(')');
            return stringBuilder.toString();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B'\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0002\u0010\u000bJ\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0002J\b\u0010\u0010\u001a\u00020\u0011H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lkotlinx/coroutines/JobSupport$ChildCompletion;", "Lkotlinx/coroutines/JobNode;", "Lkotlinx/coroutines/Job;", "parent", "Lkotlinx/coroutines/JobSupport;", "state", "Lkotlinx/coroutines/JobSupport$Finishing;", "child", "Lkotlinx/coroutines/ChildHandleNode;", "proposedUpdate", "", "(Lkotlinx/coroutines/JobSupport;Lkotlinx/coroutines/JobSupport$Finishing;Lkotlinx/coroutines/ChildHandleNode;Ljava/lang/Object;)V", "invoke", "", "cause", "", "toString", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
    /* compiled from: JobSupport.kt */
    private static final class ChildCompletion extends JobNode<Job> {
        private final ChildHandleNode child;
        private final JobSupport parent;
        private final Object proposedUpdate;
        private final Finishing state;

        public ChildCompletion(@NotNull JobSupport jobSupport, @NotNull Finishing finishing, @NotNull ChildHandleNode childHandleNode, @Nullable Object obj) {
            Intrinsics.checkParameterIsNotNull(jobSupport, "parent");
            Intrinsics.checkParameterIsNotNull(finishing, "state");
            Intrinsics.checkParameterIsNotNull(childHandleNode, "child");
            super(childHandleNode.childJob);
            this.parent = jobSupport;
            this.state = finishing;
            this.child = childHandleNode;
            this.proposedUpdate = obj;
        }

        public void invoke(@Nullable Throwable th) {
            this.parent.continueCompleting(this.state, this.child, this.proposedUpdate);
        }

        @NotNull
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("ChildCompletion[");
            stringBuilder.append(this.child);
            stringBuilder.append(", ");
            stringBuilder.append(this.proposedUpdate);
            stringBuilder.append(']');
            return stringBuilder.toString();
        }
    }

    protected boolean getCancelsParent() {
        return false;
    }

    protected boolean getHandlesException() {
        return true;
    }

    public boolean getOnCancelComplete$kotlinx_coroutines_core() {
        return false;
    }

    protected void handleJobException(@NotNull Throwable th) {
        Intrinsics.checkParameterIsNotNull(th, "exception");
    }

    protected void onCancellation(@Nullable Throwable th) {
    }

    public void onCompletionInternal$kotlinx_coroutines_core(@Nullable Object obj, int i, boolean z) {
    }

    public void onStartInternal$kotlinx_coroutines_core() {
    }

    public JobSupport(boolean z) {
        this._state = z ? JobSupportKt.EMPTY_ACTIVE : JobSupportKt.EMPTY_NEW;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Left here for binary compatibility")
    @JvmName(name = "cancel")
    /* renamed from: cancel */
    public /* synthetic */ boolean m25cancel() {
        return cancel(null);
    }

    public <R> R fold(R r, @NotNull Function2<? super R, ? super Element, ? extends R> function2) {
        Intrinsics.checkParameterIsNotNull(function2, "operation");
        return DefaultImpls.fold(this, r, function2);
    }

    @Nullable
    public <E extends Element> E get(@NotNull Key<E> key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        return DefaultImpls.get(this, key);
    }

    @NotNull
    public CoroutineContext minusKey(@NotNull Key<?> key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        return DefaultImpls.minusKey(this, key);
    }

    @NotNull
    public CoroutineContext plus(@NotNull CoroutineContext coroutineContext) {
        Intrinsics.checkParameterIsNotNull(coroutineContext, "context");
        return DefaultImpls.plus((Job) this, coroutineContext);
    }

    @NotNull
    @Deprecated(level = DeprecationLevel.ERROR, message = "Operator '+' on two Job objects is meaningless. Job is a coroutine context element and `+` is a set-sum operator for coroutine contexts. The job to the right of `+` just replaces the job the left of `+`.")
    public Job plus(@NotNull Job job) {
        Intrinsics.checkParameterIsNotNull(job, "other");
        return DefaultImpls.plus((Job) this, job);
    }

    @NotNull
    public final Key<?> getKey() {
        return Job.Key;
    }

    public final void initParentJobInternal$kotlinx_coroutines_core(@Nullable Job job) {
        if ((this.parentHandle == null ? 1 : null) == null) {
            throw ((Throwable) new IllegalStateException("Check failed.".toString()));
        } else if (job == null) {
            this.parentHandle = (ChildHandle) NonDisposableHandle.INSTANCE;
        } else {
            job.start();
            job = job.attachChild(this);
            this.parentHandle = job;
            if (isCompleted()) {
                job.dispose();
                this.parentHandle = (ChildHandle) NonDisposableHandle.INSTANCE;
            }
        }
    }

    private final Void loopOnState(Function1<Object, Unit> function1) {
        while (true) {
            function1.invoke(getState$kotlinx_coroutines_core());
        }
    }

    public boolean isActive() {
        Object state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
        return (state$kotlinx_coroutines_core instanceof Incomplete) && ((Incomplete) state$kotlinx_coroutines_core).isActive();
    }

    public final boolean isCompleted() {
        return !(getState$kotlinx_coroutines_core() instanceof Incomplete);
    }

    public final boolean isCancelled() {
        Object state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
        if (!(state$kotlinx_coroutines_core instanceof CompletedExceptionally)) {
            if (!(state$kotlinx_coroutines_core instanceof Finishing) || !((Finishing) state$kotlinx_coroutines_core).isCancelling()) {
                return false;
            }
        }
        return true;
    }

    private final boolean tryFinalizeFinishingState(Finishing finishing, Object obj, int i) {
        boolean z = false;
        if ((!(obj instanceof Incomplete) ? 1 : null) != null) {
            if ((getState$kotlinx_coroutines_core() == finishing ? 1 : null) == null) {
                throw ((Throwable) new IllegalArgumentException("Failed requirement.".toString()));
            } else if ((finishing.isSealed() ^ 1) == 0) {
                throw ((Throwable) new IllegalArgumentException("Failed requirement.".toString()));
            } else if (finishing.isCompleting) {
                Throwable finalRootCause;
                Throwable th = null;
                CompletedExceptionally completedExceptionally = (CompletedExceptionally) (!(obj instanceof CompletedExceptionally) ? null : obj);
                if (completedExceptionally != null) {
                    th = completedExceptionally.cause;
                }
                synchronized (finishing) {
                    List sealLocked = finishing.sealLocked(th);
                    finalRootCause = getFinalRootCause(finishing, sealLocked);
                    if (finalRootCause != null && (suppressExceptions(finalRootCause, sealLocked) || finalRootCause != finishing.rootCause)) {
                        z = true;
                    }
                }
                if (finalRootCause != null) {
                    if (finalRootCause != th) {
                        obj = new CompletedExceptionally(finalRootCause);
                    }
                }
                if (!(finalRootCause == null || cancelParent(finalRootCause))) {
                    handleJobException(finalRootCause);
                }
                if (_state$FU.compareAndSet(this, finishing, obj)) {
                    completeStateFinalization(finishing, obj, i, z);
                    return true;
                }
                i = new StringBuilder();
                i.append("Unexpected state: ");
                i.append(this._state);
                i.append(", expected: ");
                i.append(finishing);
                i.append(", update: ");
                i.append(obj);
                throw ((Throwable) new IllegalArgumentException(i.toString().toString()));
            } else {
                throw ((Throwable) new IllegalArgumentException("Failed requirement.".toString()));
            }
        }
        throw ((Throwable) new IllegalArgumentException("Failed requirement.".toString()));
    }

    private final Throwable getFinalRootCause(Finishing finishing, List<? extends Throwable> list) {
        if (!list.isEmpty()) {
            for (Object next : list) {
                Object obj;
                if (((Throwable) next) instanceof CancellationException) {
                    obj = null;
                    continue;
                } else {
                    obj = 1;
                    continue;
                }
                if (obj != null) {
                    break;
                }
            }
            Object next2 = null;
            Throwable th = (Throwable) next2;
            if (th == null) {
                th = (Throwable) list.get(0);
            }
            return th;
        } else if (finishing.isCancelling() != null) {
            return createJobCancellationException();
        } else {
            return null;
        }
    }

    private final boolean suppressExceptions(Throwable th, List<? extends Throwable> list) {
        boolean z = false;
        if (list.size() <= 1) {
            return false;
        }
        Set identitySet = ConcurrentKt.identitySet(list.size());
        for (Throwable th2 : list) {
            if (!(th2 == th || (th2 instanceof CancellationException) || !identitySet.add(th2))) {
                ExceptionsKt__ExceptionsKt.addSuppressed(th, th2);
                z = true;
            }
        }
        return z;
    }

    private final boolean tryFinalizeSimpleState(Incomplete incomplete, Object obj, int i) {
        Object obj2;
        if (!(incomplete instanceof Empty)) {
            if (!(incomplete instanceof JobNode)) {
                obj2 = null;
                if (obj2 == null) {
                    if ((obj instanceof CompletedExceptionally ? 1 : null) != null) {
                        throw ((Throwable) new IllegalStateException("Check failed.".toString()));
                    } else if (!_state$FU.compareAndSet(this, incomplete, obj)) {
                        return false;
                    } else {
                        completeStateFinalization(incomplete, obj, i, false);
                        return true;
                    }
                }
                throw ((Throwable) new IllegalStateException("Check failed.".toString()));
            }
        }
        obj2 = 1;
        if (obj2 == null) {
            throw ((Throwable) new IllegalStateException("Check failed.".toString()));
        }
        if (obj instanceof CompletedExceptionally) {
        }
        if ((obj instanceof CompletedExceptionally ? 1 : null) != null) {
            throw ((Throwable) new IllegalStateException("Check failed.".toString()));
        } else if (!_state$FU.compareAndSet(this, incomplete, obj)) {
            return false;
        } else {
            completeStateFinalization(incomplete, obj, i, false);
            return true;
        }
    }

    private final void completeStateFinalization(Incomplete incomplete, Object obj, int i, boolean z) {
        ChildHandle childHandle = this.parentHandle;
        if (childHandle != null) {
            childHandle.dispose();
            this.parentHandle = NonDisposableHandle.INSTANCE;
        }
        Throwable th = null;
        CompletedExceptionally completedExceptionally = (CompletedExceptionally) (!(obj instanceof CompletedExceptionally) ? null : obj);
        if (completedExceptionally != null) {
            th = completedExceptionally.cause;
        }
        if (!isCancelling(incomplete)) {
            onCancellation(th);
        }
        if (incomplete instanceof JobNode) {
            try {
                ((JobNode) incomplete).invoke(th);
            } catch (Throwable th2) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Exception in completion handler ");
                stringBuilder.append(incomplete);
                stringBuilder.append(" for ");
                stringBuilder.append(this);
                handleOnCompletionException$kotlinx_coroutines_core(new CompletionHandlerException(stringBuilder.toString(), th2));
            }
        } else {
            incomplete = incomplete.getList();
            if (incomplete != null) {
                notifyCompletion(incomplete, th);
            }
        }
        onCompletionInternal$kotlinx_coroutines_core(obj, i, z);
    }

    private final void notifyCancelling(NodeList nodeList, Throwable th) {
        onCancellation(th);
        Throwable th2 = (Throwable) null;
        Object next = nodeList.getNext();
        if (next != null) {
            for (next = (LockFreeLinkedListNode) next; (Intrinsics.areEqual(next, (Object) nodeList) ^ 1) != 0; next = next.getNextNode()) {
                if (next instanceof JobCancellingNode) {
                    JobNode jobNode = (JobNode) next;
                    try {
                        jobNode.invoke(th);
                    } catch (Throwable th3) {
                        if (th2 != null) {
                            ExceptionsKt__ExceptionsKt.addSuppressed(th2, th3);
                            if (th2 != null) {
                            }
                        }
                        JobSupport jobSupport = this;
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("Exception in completion handler ");
                        stringBuilder.append(jobNode);
                        stringBuilder.append(" for ");
                        stringBuilder.append(jobSupport);
                        Throwable completionHandlerException = new CompletionHandlerException(stringBuilder.toString(), th3);
                        Unit unit = Unit.INSTANCE;
                        th2 = completionHandlerException;
                    }
                }
            }
            if (th2 != null) {
                handleOnCompletionException$kotlinx_coroutines_core(th2);
            }
            cancelParent(th);
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */");
    }

    private final <T extends JobNode<?>> void notifyHandlers(NodeList nodeList, Throwable th) {
        Throwable th2 = (Throwable) null;
        Object next = nodeList.getNext();
        if (next != null) {
            for (next = (LockFreeLinkedListNode) next; (Intrinsics.areEqual(next, (Object) nodeList) ^ 1) != 0; next = next.getNextNode()) {
                Intrinsics.reifiedOperationMarker(3, "T");
                if (next instanceof LockFreeLinkedListNode) {
                    JobNode jobNode = (JobNode) next;
                    try {
                        jobNode.invoke(th);
                    } catch (Throwable th3) {
                        if (th2 != null) {
                            ExceptionsKt__ExceptionsKt.addSuppressed(th2, th3);
                            if (th2 != null) {
                            }
                        }
                        JobSupport jobSupport = this;
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("Exception in completion handler ");
                        stringBuilder.append(jobNode);
                        stringBuilder.append(" for ");
                        stringBuilder.append(jobSupport);
                        Throwable completionHandlerException = new CompletionHandlerException(stringBuilder.toString(), th3);
                        Unit unit = Unit.INSTANCE;
                        th2 = completionHandlerException;
                    }
                }
            }
            if (th2 != null) {
                handleOnCompletionException$kotlinx_coroutines_core(th2);
                return;
            }
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */");
    }

    private final int startInternal(Object obj) {
        if (obj instanceof Empty) {
            if (((Empty) obj).isActive()) {
                return 0;
            }
            if (_state$FU.compareAndSet(this, obj, JobSupportKt.EMPTY_ACTIVE) == null) {
                return -1;
            }
            onStartInternal$kotlinx_coroutines_core();
            return 1;
        } else if (!(obj instanceof InactiveNodeList)) {
            return 0;
        } else {
            if (_state$FU.compareAndSet(this, obj, ((InactiveNodeList) obj).getList()) == null) {
                return -1;
            }
            onStartInternal$kotlinx_coroutines_core();
            return 1;
        }
    }

    @NotNull
    public final CancellationException getCancellationException() {
        Object state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
        StringBuilder stringBuilder;
        if (state$kotlinx_coroutines_core instanceof Finishing) {
            Throwable th = ((Finishing) state$kotlinx_coroutines_core).rootCause;
            if (th != null) {
                CancellationException toCancellationException = toCancellationException(th, "Job is cancelling");
                if (toCancellationException != null) {
                    return toCancellationException;
                }
            }
            stringBuilder = new StringBuilder();
            stringBuilder.append("Job is still new or active: ");
            stringBuilder.append(this);
            throw new IllegalStateException(stringBuilder.toString().toString());
        } else if (state$kotlinx_coroutines_core instanceof Incomplete) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("Job is still new or active: ");
            stringBuilder.append(this);
            throw new IllegalStateException(stringBuilder.toString().toString());
        } else if (state$kotlinx_coroutines_core instanceof CompletedExceptionally) {
            return toCancellationException(((CompletedExceptionally) state$kotlinx_coroutines_core).cause, "Job was cancelled");
        } else {
            return new JobCancellationException("Job has completed normally", null, this);
        }
    }

    private final CancellationException toCancellationException(@NotNull Throwable th, String str) {
        CancellationException cancellationException = (CancellationException) (!(th instanceof CancellationException) ? null : th);
        return cancellationException != null ? cancellationException : new JobCancellationException(str, th, this);
    }

    @NotNull
    public final DisposableHandle invokeOnCompletion(@NotNull Function1<? super Throwable, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(function1, "handler");
        return invokeOnCompletion(false, true, function1);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.DisposableHandle invokeOnCompletion(boolean r8, boolean r9, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super java.lang.Throwable, kotlin.Unit> r10) {
        /*
        r7 = this;
        r0 = "handler";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r10, r0);
        r0 = 0;
        r1 = r0;
        r1 = (kotlinx.coroutines.JobNode) r1;
    L_0x0009:
        r2 = r7.getState$kotlinx_coroutines_core();
        r3 = r2 instanceof kotlinx.coroutines.Empty;
        if (r3 == 0) goto L_0x0030;
    L_0x0011:
        r3 = r2;
        r3 = (kotlinx.coroutines.Empty) r3;
        r4 = r3.isActive();
        if (r4 == 0) goto L_0x002c;
    L_0x001a:
        if (r1 == 0) goto L_0x001d;
    L_0x001c:
        goto L_0x0021;
    L_0x001d:
        r1 = r7.makeNode(r10, r8);
    L_0x0021:
        r3 = _state$FU;
        r2 = r3.compareAndSet(r7, r2, r1);
        if (r2 == 0) goto L_0x0009;
    L_0x0029:
        r1 = (kotlinx.coroutines.DisposableHandle) r1;
        return r1;
    L_0x002c:
        r7.promoteEmptyToNodeList(r3);
        goto L_0x0009;
    L_0x0030:
        r3 = r2 instanceof kotlinx.coroutines.Incomplete;
        if (r3 == 0) goto L_0x00a4;
    L_0x0034:
        r3 = r2;
        r3 = (kotlinx.coroutines.Incomplete) r3;
        r3 = r3.getList();
        if (r3 != 0) goto L_0x004d;
    L_0x003d:
        if (r2 == 0) goto L_0x0045;
    L_0x003f:
        r2 = (kotlinx.coroutines.JobNode) r2;
        r7.promoteSingleToNodeList(r2);
        goto L_0x0009;
    L_0x0045:
        r8 = new kotlin.TypeCastException;
        r9 = "null cannot be cast to non-null type kotlinx.coroutines.JobNode<*>";
        r8.<init>(r9);
        throw r8;
    L_0x004d:
        r4 = r0;
        r4 = (java.lang.Throwable) r4;
        r5 = kotlinx.coroutines.NonDisposableHandle.INSTANCE;
        r5 = (kotlinx.coroutines.DisposableHandle) r5;
        if (r8 == 0) goto L_0x008c;
    L_0x0056:
        r6 = r2 instanceof kotlinx.coroutines.JobSupport.Finishing;
        if (r6 == 0) goto L_0x008c;
    L_0x005a:
        monitor-enter(r2);
        r4 = r2;
        r4 = (kotlinx.coroutines.JobSupport.Finishing) r4;	 Catch:{ all -> 0x0089 }
        r4 = r4.rootCause;	 Catch:{ all -> 0x0089 }
        if (r4 == 0) goto L_0x006d;
    L_0x0062:
        r6 = r10 instanceof kotlinx.coroutines.ChildHandleNode;	 Catch:{ all -> 0x0089 }
        if (r6 == 0) goto L_0x0085;
    L_0x0066:
        r6 = r2;
        r6 = (kotlinx.coroutines.JobSupport.Finishing) r6;	 Catch:{ all -> 0x0089 }
        r6 = r6.isCompleting;	 Catch:{ all -> 0x0089 }
        if (r6 != 0) goto L_0x0085;
    L_0x006d:
        if (r1 == 0) goto L_0x0070;
    L_0x006f:
        goto L_0x0074;
    L_0x0070:
        r1 = r7.makeNode(r10, r8);	 Catch:{ all -> 0x0089 }
    L_0x0074:
        r5 = r7.addLastAtomic(r2, r3, r1);	 Catch:{ all -> 0x0089 }
        if (r5 != 0) goto L_0x007c;
    L_0x007a:
        monitor-exit(r2);
        goto L_0x0009;
    L_0x007c:
        if (r4 != 0) goto L_0x0082;
    L_0x007e:
        r1 = (kotlinx.coroutines.DisposableHandle) r1;	 Catch:{ all -> 0x0089 }
        monitor-exit(r2);
        return r1;
    L_0x0082:
        r5 = r1;
        r5 = (kotlinx.coroutines.DisposableHandle) r5;	 Catch:{ all -> 0x0089 }
    L_0x0085:
        r6 = kotlin.Unit.INSTANCE;	 Catch:{ all -> 0x0089 }
        monitor-exit(r2);
        goto L_0x008c;
    L_0x0089:
        r8 = move-exception;
        monitor-exit(r2);
        throw r8;
    L_0x008c:
        if (r4 == 0) goto L_0x0094;
    L_0x008e:
        if (r9 == 0) goto L_0x0093;
    L_0x0090:
        r10.invoke(r4);
    L_0x0093:
        return r5;
    L_0x0094:
        if (r1 == 0) goto L_0x0097;
    L_0x0096:
        goto L_0x009b;
    L_0x0097:
        r1 = r7.makeNode(r10, r8);
    L_0x009b:
        r2 = r7.addLastAtomic(r2, r3, r1);
        if (r2 == 0) goto L_0x0009;
    L_0x00a1:
        r1 = (kotlinx.coroutines.DisposableHandle) r1;
        return r1;
    L_0x00a4:
        if (r9 == 0) goto L_0x00b4;
    L_0x00a6:
        r8 = r2 instanceof kotlinx.coroutines.CompletedExceptionally;
        if (r8 != 0) goto L_0x00ab;
    L_0x00aa:
        r2 = r0;
    L_0x00ab:
        r2 = (kotlinx.coroutines.CompletedExceptionally) r2;
        if (r2 == 0) goto L_0x00b1;
    L_0x00af:
        r0 = r2.cause;
    L_0x00b1:
        r10.invoke(r0);
    L_0x00b4:
        r8 = kotlinx.coroutines.NonDisposableHandle.INSTANCE;
        r8 = (kotlinx.coroutines.DisposableHandle) r8;
        return r8;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.JobSupport.invokeOnCompletion(boolean, boolean, kotlin.jvm.functions.Function1):kotlinx.coroutines.DisposableHandle");
    }

    private final JobNode<?> makeNode(Function1<? super Throwable, Unit> function1, boolean z) {
        Object obj = null;
        Function1<? super Throwable, Unit> function12 = null;
        if (z) {
            if (function1 instanceof JobCancellingNode) {
                function12 = function1;
            }
            JobCancellingNode jobCancellingNode = (JobCancellingNode) function12;
            if (jobCancellingNode != null) {
                if (jobCancellingNode.job == this) {
                    obj = 1;
                }
                if (obj == null) {
                    throw ((Throwable) new IllegalArgumentException("Failed requirement.".toString()));
                } else if (jobCancellingNode != null) {
                    return jobCancellingNode;
                }
            }
            return (JobNode) new InvokeOnCancelling(this, function1);
        }
        if (function1 instanceof JobNode) {
            function12 = function1;
        }
        JobNode<?> jobNode = (JobNode) function12;
        if (jobNode != null) {
            if (jobNode.job == this && !(jobNode instanceof JobCancellingNode)) {
                obj = 1;
            }
            if (obj == null) {
                throw ((Throwable) new IllegalArgumentException("Failed requirement.".toString()));
            } else if (jobNode != null) {
                return jobNode;
            }
        }
        return (JobNode) new InvokeOnCompletion(this, function1);
    }

    private final void promoteEmptyToNodeList(Empty empty) {
        NodeList nodeList = new NodeList();
        _state$FU.compareAndSet(this, empty, empty.isActive() ? nodeList : new InactiveNodeList(nodeList));
    }

    private final void promoteSingleToNodeList(JobNode<?> jobNode) {
        jobNode.addOneIfEmpty(new NodeList());
        _state$FU.compareAndSet(this, jobNode, jobNode.getNextNode());
    }

    @Nullable
    public final Object join(@NotNull Continuation<? super Unit> continuation) {
        if (joinInternal()) {
            return joinSuspend(continuation);
        }
        YieldKt.checkCompletion(continuation.getContext());
        return Unit.INSTANCE;
    }

    @NotNull
    public final SelectClause0 getOnJoin() {
        return this;
    }

    public void cancel() {
        cancel(null);
    }

    public boolean cancel(@Nullable Throwable th) {
        return (cancelImpl(th) == null || getHandlesException() == null) ? null : true;
    }

    public final void parentCancelled(@NotNull ParentJob parentJob) {
        Intrinsics.checkParameterIsNotNull(parentJob, "parentJob");
        cancelImpl(parentJob);
    }

    public boolean childCancelled(@NotNull Throwable th) {
        Intrinsics.checkParameterIsNotNull(th, "cause");
        return (cancelImpl(th) == null || getHandlesException() == null) ? null : true;
    }

    private final boolean cancelImpl(Object obj) {
        if (getOnCancelComplete$kotlinx_coroutines_core() && cancelMakeCompleting(obj)) {
            return true;
        }
        return makeCancelling(obj);
    }

    private final JobCancellationException createJobCancellationException() {
        return new JobCancellationException("Job was cancelled", null, this);
    }

    @NotNull
    public Throwable getChildJobCancellationCause() {
        Throwable th;
        Object state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
        if (state$kotlinx_coroutines_core instanceof Finishing) {
            th = ((Finishing) state$kotlinx_coroutines_core).rootCause;
        } else if (state$kotlinx_coroutines_core instanceof Incomplete) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Cannot be cancelling child in this state: ");
            stringBuilder.append(state$kotlinx_coroutines_core);
            throw new IllegalStateException(stringBuilder.toString().toString());
        } else {
            th = state$kotlinx_coroutines_core instanceof CompletedExceptionally ? ((CompletedExceptionally) state$kotlinx_coroutines_core).cause : null;
        }
        if (th != null && (!getHandlesException() || (th instanceof CancellationException))) {
            return th;
        }
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("Parent job is ");
        stringBuilder2.append(stateString(state$kotlinx_coroutines_core));
        return new JobCancellationException(stringBuilder2.toString(), th, this);
    }

    private final Throwable createCauseException(Object obj) {
        if (obj != null ? obj instanceof Throwable : true) {
            if (obj == null) {
                obj = createJobCancellationException();
            }
            return (Throwable) obj;
        } else if (obj != null) {
            return ((ParentJob) obj).getChildJobCancellationCause();
        } else {
            throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.ParentJob");
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final boolean makeCancelling(java.lang.Object r8) {
        /*
        r7 = this;
        r0 = 0;
        r1 = r0;
        r1 = (java.lang.Throwable) r1;
    L_0x0004:
        r2 = r7.getState$kotlinx_coroutines_core();
        r3 = r2 instanceof kotlinx.coroutines.JobSupport.Finishing;
        r4 = 0;
        r5 = 1;
        if (r3 == 0) goto L_0x004d;
    L_0x000e:
        monitor-enter(r2);
        r3 = r2;
        r3 = (kotlinx.coroutines.JobSupport.Finishing) r3;	 Catch:{ all -> 0x004a }
        r3 = r3.isSealed();	 Catch:{ all -> 0x004a }
        if (r3 == 0) goto L_0x001a;
    L_0x0018:
        monitor-exit(r2);
        return r4;
    L_0x001a:
        r3 = r2;
        r3 = (kotlinx.coroutines.JobSupport.Finishing) r3;	 Catch:{ all -> 0x004a }
        r3 = r3.isCancelling();	 Catch:{ all -> 0x004a }
        if (r8 != 0) goto L_0x0025;
    L_0x0023:
        if (r3 != 0) goto L_0x0032;
    L_0x0025:
        if (r1 == 0) goto L_0x0028;
    L_0x0027:
        goto L_0x002c;
    L_0x0028:
        r1 = r7.createCauseException(r8);	 Catch:{ all -> 0x004a }
    L_0x002c:
        r8 = r2;
        r8 = (kotlinx.coroutines.JobSupport.Finishing) r8;	 Catch:{ all -> 0x004a }
        r8.addExceptionLocked(r1);	 Catch:{ all -> 0x004a }
    L_0x0032:
        r8 = r2;
        r8 = (kotlinx.coroutines.JobSupport.Finishing) r8;	 Catch:{ all -> 0x004a }
        r8 = r8.rootCause;	 Catch:{ all -> 0x004a }
        r1 = r3 ^ 1;
        if (r1 == 0) goto L_0x003c;
    L_0x003b:
        goto L_0x003d;
    L_0x003c:
        r8 = r0;
    L_0x003d:
        monitor-exit(r2);
        if (r8 == 0) goto L_0x0049;
    L_0x0040:
        r2 = (kotlinx.coroutines.JobSupport.Finishing) r2;
        r0 = r2.getList();
        r7.notifyCancelling(r0, r8);
    L_0x0049:
        return r5;
    L_0x004a:
        r8 = move-exception;
        monitor-exit(r2);
        throw r8;
    L_0x004d:
        r3 = r2 instanceof kotlinx.coroutines.Incomplete;
        if (r3 == 0) goto L_0x00a0;
    L_0x0051:
        if (r1 == 0) goto L_0x0054;
    L_0x0053:
        goto L_0x0058;
    L_0x0054:
        r1 = r7.createCauseException(r8);
    L_0x0058:
        r3 = r2;
        r3 = (kotlinx.coroutines.Incomplete) r3;
        r6 = r3.isActive();
        if (r6 == 0) goto L_0x0068;
    L_0x0061:
        r2 = r7.tryMakeCancelling(r3, r1);
        if (r2 == 0) goto L_0x0004;
    L_0x0067:
        return r5;
    L_0x0068:
        r3 = new kotlinx.coroutines.CompletedExceptionally;
        r3.<init>(r1);
        r3 = r7.tryMakeCompleting(r2, r3, r4);
        switch(r3) {
            case 0: goto L_0x0083;
            case 1: goto L_0x0082;
            case 2: goto L_0x0082;
            case 3: goto L_0x0004;
            default: goto L_0x0074;
        };
    L_0x0074:
        r8 = new java.lang.IllegalStateException;
        r0 = "unexpected result";
        r0 = r0.toString();
        r8.<init>(r0);
        r8 = (java.lang.Throwable) r8;
        throw r8;
    L_0x0082:
        return r5;
    L_0x0083:
        r8 = new java.lang.StringBuilder;
        r8.<init>();
        r0 = "Cannot happen in ";
        r8.append(r0);
        r8.append(r2);
        r8 = r8.toString();
        r0 = new java.lang.IllegalStateException;
        r8 = r8.toString();
        r0.<init>(r8);
        r0 = (java.lang.Throwable) r0;
        throw r0;
    L_0x00a0:
        return r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.JobSupport.makeCancelling(java.lang.Object):boolean");
    }

    private final NodeList getOrPromoteCancellingList(Incomplete incomplete) {
        NodeList list = incomplete.getList();
        if (list != null) {
            return list;
        }
        if (incomplete instanceof Empty) {
            return new NodeList();
        }
        if (incomplete instanceof JobNode) {
            promoteSingleToNodeList((JobNode) incomplete);
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("State should have list: ");
        stringBuilder.append(incomplete);
        throw new IllegalStateException(stringBuilder.toString().toString());
    }

    private final boolean tryMakeCancelling(Incomplete incomplete, Throwable th) {
        if ((!(incomplete instanceof Finishing) ? 1 : null) == null) {
            throw ((Throwable) new IllegalStateException("Check failed.".toString()));
        } else if (incomplete.isActive()) {
            NodeList orPromoteCancellingList = getOrPromoteCancellingList(incomplete);
            if (orPromoteCancellingList == null) {
                return false;
            }
            if (_state$FU.compareAndSet(this, incomplete, new Finishing(orPromoteCancellingList, false, th)) == null) {
                return false;
            }
            notifyCancelling(orPromoteCancellingList, th);
            return true;
        } else {
            throw ((Throwable) new IllegalStateException("Check failed.".toString()));
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final int tryMakeCompleting(java.lang.Object r9, java.lang.Object r10, int r11) {
        /*
        r8 = this;
        r0 = r9 instanceof kotlinx.coroutines.Incomplete;
        r1 = 0;
        if (r0 != 0) goto L_0x0006;
    L_0x0005:
        return r1;
    L_0x0006:
        r0 = r9 instanceof kotlinx.coroutines.Empty;
        r2 = 3;
        r3 = 1;
        if (r0 != 0) goto L_0x0010;
    L_0x000c:
        r0 = r9 instanceof kotlinx.coroutines.JobNode;
        if (r0 == 0) goto L_0x0022;
    L_0x0010:
        r0 = r9 instanceof kotlinx.coroutines.ChildHandleNode;
        if (r0 != 0) goto L_0x0022;
    L_0x0014:
        r0 = r10 instanceof kotlinx.coroutines.CompletedExceptionally;
        if (r0 != 0) goto L_0x0022;
    L_0x0018:
        r9 = (kotlinx.coroutines.Incomplete) r9;
        r9 = r8.tryFinalizeSimpleState(r9, r10, r11);
        if (r9 != 0) goto L_0x0021;
    L_0x0020:
        return r2;
    L_0x0021:
        return r3;
    L_0x0022:
        r0 = r9;
        r0 = (kotlinx.coroutines.Incomplete) r0;
        r4 = r8.getOrPromoteCancellingList(r0);
        if (r4 == 0) goto L_0x00a6;
    L_0x002b:
        r5 = r9 instanceof kotlinx.coroutines.JobSupport.Finishing;
        r6 = 0;
        if (r5 != 0) goto L_0x0032;
    L_0x0030:
        r5 = r6;
        goto L_0x0033;
    L_0x0032:
        r5 = r9;
    L_0x0033:
        r5 = (kotlinx.coroutines.JobSupport.Finishing) r5;
        if (r5 == 0) goto L_0x0038;
    L_0x0037:
        goto L_0x003d;
    L_0x0038:
        r5 = new kotlinx.coroutines.JobSupport$Finishing;
        r5.<init>(r4, r1, r6);
    L_0x003d:
        r7 = r6;
        r7 = (java.lang.Throwable) r7;
        monitor-enter(r5);
        r7 = r5.isCompleting;	 Catch:{ all -> 0x00a3 }
        if (r7 == 0) goto L_0x0047;
    L_0x0045:
        monitor-exit(r5);
        return r1;
    L_0x0047:
        r5.isCompleting = r3;	 Catch:{ all -> 0x00a3 }
        if (r5 == r9) goto L_0x0055;
    L_0x004b:
        r1 = _state$FU;	 Catch:{ all -> 0x00a3 }
        r9 = r1.compareAndSet(r8, r9, r5);	 Catch:{ all -> 0x00a3 }
        if (r9 != 0) goto L_0x0055;
    L_0x0053:
        monitor-exit(r5);
        return r2;
    L_0x0055:
        r9 = r5.isSealed();	 Catch:{ all -> 0x00a3 }
        r9 = r9 ^ r3;
        if (r9 == 0) goto L_0x0095;
    L_0x005c:
        r9 = r5.isCancelling();	 Catch:{ all -> 0x00a3 }
        r1 = r10 instanceof kotlinx.coroutines.CompletedExceptionally;	 Catch:{ all -> 0x00a3 }
        if (r1 != 0) goto L_0x0066;
    L_0x0064:
        r1 = r6;
        goto L_0x0067;
    L_0x0066:
        r1 = r10;
    L_0x0067:
        r1 = (kotlinx.coroutines.CompletedExceptionally) r1;	 Catch:{ all -> 0x00a3 }
        if (r1 == 0) goto L_0x0070;
    L_0x006b:
        r1 = r1.cause;	 Catch:{ all -> 0x00a3 }
        r5.addExceptionLocked(r1);	 Catch:{ all -> 0x00a3 }
    L_0x0070:
        r1 = r5.rootCause;	 Catch:{ all -> 0x00a3 }
        r9 = r9 ^ r3;
        if (r9 == 0) goto L_0x0076;
    L_0x0075:
        goto L_0x0077;
    L_0x0076:
        r1 = r6;
    L_0x0077:
        r9 = kotlin.Unit.INSTANCE;	 Catch:{ all -> 0x00a3 }
        monitor-exit(r5);
        if (r1 == 0) goto L_0x007f;
    L_0x007c:
        r8.notifyCancelling(r4, r1);
    L_0x007f:
        r9 = r8.firstChild(r0);
        if (r9 == 0) goto L_0x008d;
    L_0x0085:
        r9 = r8.tryWaitForChild(r5, r9, r10);
        if (r9 == 0) goto L_0x008d;
    L_0x008b:
        r9 = 2;
        return r9;
    L_0x008d:
        r9 = r8.tryFinalizeFinishingState(r5, r10, r11);
        if (r9 == 0) goto L_0x0094;
    L_0x0093:
        return r3;
    L_0x0094:
        return r2;
    L_0x0095:
        r9 = "Failed requirement.";
        r10 = new java.lang.IllegalArgumentException;	 Catch:{ all -> 0x00a3 }
        r9 = r9.toString();	 Catch:{ all -> 0x00a3 }
        r10.<init>(r9);	 Catch:{ all -> 0x00a3 }
        r10 = (java.lang.Throwable) r10;	 Catch:{ all -> 0x00a3 }
        throw r10;	 Catch:{ all -> 0x00a3 }
    L_0x00a3:
        r9 = move-exception;
        monitor-exit(r5);
        throw r9;
    L_0x00a6:
        return r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.JobSupport.tryMakeCompleting(java.lang.Object, java.lang.Object, int):int");
    }

    private final Throwable getExceptionOrNull(@Nullable Object obj) {
        if (!(obj instanceof CompletedExceptionally)) {
            obj = null;
        }
        CompletedExceptionally completedExceptionally = (CompletedExceptionally) obj;
        return completedExceptionally != null ? completedExceptionally.cause : null;
    }

    private final ChildHandleNode firstChild(Incomplete incomplete) {
        ChildHandleNode childHandleNode = !(incomplete instanceof ChildHandleNode) ? null : incomplete;
        if (childHandleNode != null) {
            return childHandleNode;
        }
        incomplete = incomplete.getList();
        return incomplete != null ? nextChild((LockFreeLinkedListNode) incomplete) : null;
    }

    private final boolean tryWaitForChild(Finishing finishing, ChildHandleNode childHandleNode, Object obj) {
        while (DefaultImpls.invokeOnCompletion$default(childHandleNode.childJob, false, false, new ChildCompletion(this, finishing, childHandleNode, obj), 1, null) == NonDisposableHandle.INSTANCE) {
            childHandleNode = nextChild(childHandleNode);
            if (childHandleNode == null) {
                return null;
            }
        }
        return true;
    }

    private final void continueCompleting(Finishing finishing, ChildHandleNode childHandleNode, Object obj) {
        if ((getState$kotlinx_coroutines_core() == finishing ? 1 : null) != null) {
            childHandleNode = nextChild(childHandleNode);
            if ((childHandleNode != null && tryWaitForChild(finishing, childHandleNode, obj) != null) || tryFinalizeFinishingState(finishing, obj, 0) == null) {
                return;
            }
            return;
        }
        throw ((Throwable) new IllegalArgumentException("Failed requirement.".toString()));
    }

    private final ChildHandleNode nextChild(@NotNull LockFreeLinkedListNode lockFreeLinkedListNode) {
        while (lockFreeLinkedListNode.isRemoved()) {
            lockFreeLinkedListNode = lockFreeLinkedListNode.getPrevNode();
        }
        while (true) {
            lockFreeLinkedListNode = lockFreeLinkedListNode.getNextNode();
            if (!lockFreeLinkedListNode.isRemoved()) {
                if (lockFreeLinkedListNode instanceof ChildHandleNode) {
                    return (ChildHandleNode) lockFreeLinkedListNode;
                }
                if (lockFreeLinkedListNode instanceof NodeList) {
                    return null;
                }
            }
        }
    }

    @NotNull
    public final Sequence<Job> getChildren() {
        return SequencesKt__SequenceBuilderKt.sequence(new JobSupport$children$1(this, null));
    }

    @NotNull
    public final ChildHandle attachChild(@NotNull ChildJob childJob) {
        Intrinsics.checkParameterIsNotNull(childJob, "child");
        childJob = DefaultImpls.invokeOnCompletion$default(this, true, false, new ChildHandleNode(this, childJob), 2, null);
        if (childJob != null) {
            return (ChildHandle) childJob;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.ChildHandle");
    }

    public void handleOnCompletionException$kotlinx_coroutines_core(@NotNull Throwable th) {
        Intrinsics.checkParameterIsNotNull(th, "exception");
        throw th;
    }

    private final boolean cancelParent(Throwable th) {
        boolean z = true;
        if (th instanceof CancellationException) {
            return true;
        }
        if (!getCancelsParent()) {
            return false;
        }
        ChildHandle childHandle = this.parentHandle;
        if (childHandle == null || childHandle.childCancelled(th) != 1) {
            z = false;
        }
        return z;
    }

    @NotNull
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(nameString$kotlinx_coroutines_core());
        stringBuilder.append('{');
        stringBuilder.append(stateString(getState$kotlinx_coroutines_core()));
        stringBuilder.append("}@");
        stringBuilder.append(DebugKt.getHexAddress(this));
        return stringBuilder.toString();
    }

    @NotNull
    public String nameString$kotlinx_coroutines_core() {
        return DebugKt.getClassSimpleName(this);
    }

    private final String stateString(Object obj) {
        if (!(obj instanceof Finishing)) {
            return obj instanceof Incomplete ? ((Incomplete) obj).isActive() != null ? "Active" : "New" : (obj instanceof CompletedExceptionally) != null ? "Cancelled" : "Completed";
        } else {
            Finishing finishing = (Finishing) obj;
            if (finishing.isCancelling()) {
                return "Cancelling";
            }
            return finishing.isCompleting != null ? "Completing" : "Active";
        }
    }

    private final boolean isCancelling(@NotNull Incomplete incomplete) {
        return (!(incomplete instanceof Finishing) || ((Finishing) incomplete).isCancelling() == null) ? null : true;
    }

    public final boolean isCompletedExceptionally() {
        return getState$kotlinx_coroutines_core() instanceof CompletedExceptionally;
    }

    @Nullable
    public final Throwable getCompletionExceptionOrNull() {
        Object state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
        if ((!(state$kotlinx_coroutines_core instanceof Incomplete) ? 1 : null) != null) {
            return getExceptionOrNull(state$kotlinx_coroutines_core);
        }
        throw new IllegalStateException("This job has not completed yet".toString());
    }

    @Nullable
    public final Object getCompletedInternal$kotlinx_coroutines_core() {
        Object state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
        if ((!(state$kotlinx_coroutines_core instanceof Incomplete) ? 1 : null) == null) {
            throw new IllegalStateException("This job has not completed yet".toString());
        } else if (!(state$kotlinx_coroutines_core instanceof CompletedExceptionally)) {
            return state$kotlinx_coroutines_core;
        } else {
            throw ((CompletedExceptionally) state$kotlinx_coroutines_core).cause;
        }
    }

    @Nullable
    public final Object awaitInternal$kotlinx_coroutines_core(@NotNull Continuation<Object> continuation) {
        Object state$kotlinx_coroutines_core;
        do {
            state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
            if (!(state$kotlinx_coroutines_core instanceof Incomplete)) {
                if ((state$kotlinx_coroutines_core instanceof CompletedExceptionally) == null) {
                    return state$kotlinx_coroutines_core;
                }
                throw ((CompletedExceptionally) state$kotlinx_coroutines_core).cause;
            }
        } while (startInternal(state$kotlinx_coroutines_core) < 0);
        return awaitSuspend(continuation);
    }

    @Nullable
    final /* synthetic */ Object awaitSuspend(@NotNull Continuation<Object> continuation) {
        AwaitContinuation awaitContinuation = new AwaitContinuation(IntrinsicsKt__IntrinsicsJvmKt.intercepted(continuation), this);
        awaitContinuation.initCancellability();
        invokeOnCompletion(new ResumeAwaitOnCompletion(this, awaitContinuation));
        Object result = awaitContinuation.getResult();
        if (result == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }

    public final <T, R> void selectAwaitCompletion$kotlinx_coroutines_core(@NotNull SelectInstance<? super R> selectInstance, @NotNull Function2<? super T, ? super Continuation<? super R>, ? extends Object> function2) {
        Intrinsics.checkParameterIsNotNull(selectInstance, "select");
        Intrinsics.checkParameterIsNotNull(function2, "block");
        Object state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
        if (state$kotlinx_coroutines_core instanceof CompletedExceptionally) {
            selectInstance.resumeSelectCancellableWithException(((CompletedExceptionally) state$kotlinx_coroutines_core).cause);
        } else {
            CancellableKt.startCoroutineCancellable(function2, state$kotlinx_coroutines_core, selectInstance.getCompletion());
        }
    }

    @Nullable
    public final Object getState$kotlinx_coroutines_core() {
        while (true) {
            Object obj = this._state;
            if (!(obj instanceof OpDescriptor)) {
                return obj;
            }
            ((OpDescriptor) obj).perform(this);
        }
    }

    private final void notifyCompletion(@NotNull NodeList nodeList, Throwable th) {
        Throwable th2 = (Throwable) null;
        Object next = nodeList.getNext();
        if (next != null) {
            for (next = (LockFreeLinkedListNode) next; (Intrinsics.areEqual(next, (Object) nodeList) ^ 1) != 0; next = next.getNextNode()) {
                if (next instanceof JobNode) {
                    JobNode jobNode = (JobNode) next;
                    try {
                        jobNode.invoke(th);
                    } catch (Throwable th3) {
                        if (th2 != null) {
                            ExceptionsKt__ExceptionsKt.addSuppressed(th2, th3);
                            if (th2 != null) {
                            }
                        }
                        JobSupport jobSupport = this;
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("Exception in completion handler ");
                        stringBuilder.append(jobNode);
                        stringBuilder.append(" for ");
                        stringBuilder.append(jobSupport);
                        Throwable completionHandlerException = new CompletionHandlerException(stringBuilder.toString(), th3);
                        Unit unit = Unit.INSTANCE;
                        th2 = completionHandlerException;
                    }
                }
            }
            if (th2 != null) {
                handleOnCompletionException$kotlinx_coroutines_core(th2);
                return;
            }
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */");
    }

    public final boolean start() {
        while (true) {
            switch (startInternal(getState$kotlinx_coroutines_core())) {
                case 0:
                    return false;
                case 1:
                    return true;
                default:
            }
        }
    }

    @Nullable
    protected final Throwable getCompletionCause() {
        Object state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
        StringBuilder stringBuilder;
        if (state$kotlinx_coroutines_core instanceof Finishing) {
            Throwable th = ((Finishing) state$kotlinx_coroutines_core).rootCause;
            if (th != null) {
                return th;
            }
            stringBuilder = new StringBuilder();
            stringBuilder.append("Job is still new or active: ");
            stringBuilder.append(this);
            throw new IllegalStateException(stringBuilder.toString().toString());
        } else if (!(state$kotlinx_coroutines_core instanceof Incomplete)) {
            return state$kotlinx_coroutines_core instanceof CompletedExceptionally ? ((CompletedExceptionally) state$kotlinx_coroutines_core).cause : null;
        } else {
            stringBuilder = new StringBuilder();
            stringBuilder.append("Job is still new or active: ");
            stringBuilder.append(this);
            throw new IllegalStateException(stringBuilder.toString().toString());
        }
    }

    private final boolean addLastAtomic(Object obj, NodeList nodeList, JobNode<?> jobNode) {
        LockFreeLinkedListNode lockFreeLinkedListNode = jobNode;
        CondAddOp jobSupport$addLastAtomic$$inlined$addLastIf$1 = new JobSupport$addLastAtomic$$inlined$addLastIf$1(lockFreeLinkedListNode, lockFreeLinkedListNode, this, obj);
        while (true) {
            obj = nodeList.getPrev();
            if (obj != null) {
                switch (((LockFreeLinkedListNode) obj).tryCondAddNext(lockFreeLinkedListNode, nodeList, jobSupport$addLastAtomic$$inlined$addLastIf$1)) {
                    case 1:
                        return true;
                    case 2:
                        return null;
                    default:
                }
            } else {
                throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */");
            }
        }
    }

    private final boolean joinInternal() {
        Object state$kotlinx_coroutines_core;
        do {
            state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
            if (!(state$kotlinx_coroutines_core instanceof Incomplete)) {
                return false;
            }
        } while (startInternal(state$kotlinx_coroutines_core) < 0);
        return true;
    }

    @Nullable
    final /* synthetic */ Object joinSuspend(@NotNull Continuation<? super Unit> continuation) {
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt__IntrinsicsJvmKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        CancellableContinuation cancellableContinuation = cancellableContinuationImpl;
        CancellableContinuationKt.disposeOnCancellation(cancellableContinuation, invokeOnCompletion(new ResumeOnCompletion(this, cancellableContinuation)));
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }

    public final <R> void registerSelectClause0(@NotNull SelectInstance<? super R> selectInstance, @NotNull Function1<? super Continuation<? super R>, ? extends Object> function1) {
        Intrinsics.checkParameterIsNotNull(selectInstance, "select");
        Intrinsics.checkParameterIsNotNull(function1, "block");
        Object state$kotlinx_coroutines_core;
        do {
            state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
            if (!selectInstance.isSelected()) {
                if (!(state$kotlinx_coroutines_core instanceof Incomplete)) {
                    if (selectInstance.trySelect(null)) {
                        YieldKt.checkCompletion(selectInstance.getCompletion().getContext());
                        UndispatchedKt.startCoroutineUnintercepted(function1, selectInstance.getCompletion());
                    }
                    return;
                }
            } else {
                return;
            }
        } while (startInternal(state$kotlinx_coroutines_core) != 0);
        selectInstance.disposeOnSelect(invokeOnCompletion(new SelectJoinOnCompletion(this, selectInstance, function1)));
    }

    public final void removeNode$kotlinx_coroutines_core(@NotNull JobNode<?> jobNode) {
        Intrinsics.checkParameterIsNotNull(jobNode, "node");
        JobNode<?> state$kotlinx_coroutines_core;
        do {
            state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
            if (!(state$kotlinx_coroutines_core instanceof JobNode)) {
                if ((state$kotlinx_coroutines_core instanceof Incomplete) && state$kotlinx_coroutines_core.getList() != null) {
                    jobNode.remove();
                }
                return;
            } else if (state$kotlinx_coroutines_core != jobNode) {
                return;
            }
        } while (!_state$FU.compareAndSet(this, state$kotlinx_coroutines_core, JobSupportKt.EMPTY_ACTIVE));
    }

    private final boolean cancelMakeCompleting(java.lang.Object r5) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:37)
	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:61)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:33)
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
        r4 = this;
    L_0x0000:
        r0 = r4.getState$kotlinx_coroutines_core();
        r1 = r0 instanceof kotlinx.coroutines.Incomplete;
        r2 = 0;
        if (r1 == 0) goto L_0x0036;
    L_0x0009:
        r1 = r0 instanceof kotlinx.coroutines.JobSupport.Finishing;
        if (r1 == 0) goto L_0x0015;
    L_0x000d:
        r1 = r0;
        r1 = (kotlinx.coroutines.JobSupport.Finishing) r1;
        r1 = r1.isCompleting;
        if (r1 == 0) goto L_0x0015;
    L_0x0014:
        goto L_0x0036;
    L_0x0015:
        r1 = new kotlinx.coroutines.CompletedExceptionally;
        r3 = r4.createCauseException(r5);
        r1.<init>(r3);
        r0 = r4.tryMakeCompleting(r0, r1, r2);
        switch(r0) {
            case 0: goto L_0x0035;
            case 1: goto L_0x0033;
            case 2: goto L_0x0033;
            case 3: goto L_0x0000;
            default: goto L_0x0025;
        };
    L_0x0025:
        r5 = new java.lang.IllegalStateException;
        r0 = "unexpected result";
        r0 = r0.toString();
        r5.<init>(r0);
        r5 = (java.lang.Throwable) r5;
        throw r5;
    L_0x0033:
        r5 = 1;
        return r5;
    L_0x0035:
        return r2;
    L_0x0036:
        return r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.JobSupport.cancelMakeCompleting(java.lang.Object):boolean");
    }

    public final boolean makeCompleting$kotlinx_coroutines_core(@org.jetbrains.annotations.Nullable java.lang.Object r3) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:37)
	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:61)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:33)
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
        r2 = this;
    L_0x0000:
        r0 = r2.getState$kotlinx_coroutines_core();
        r1 = 0;
        r0 = r2.tryMakeCompleting(r0, r3, r1);
        switch(r0) {
            case 0: goto L_0x001c;
            case 1: goto L_0x001a;
            case 2: goto L_0x001a;
            case 3: goto L_0x0000;
            default: goto L_0x000c;
        };
    L_0x000c:
        r3 = new java.lang.IllegalStateException;
        r0 = "unexpected result";
        r0 = r0.toString();
        r3.<init>(r0);
        r3 = (java.lang.Throwable) r3;
        throw r3;
    L_0x001a:
        r3 = 1;
        return r3;
    L_0x001c:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.JobSupport.makeCompleting$kotlinx_coroutines_core(java.lang.Object):boolean");
    }

    public final boolean makeCompletingOnce$kotlinx_coroutines_core(@org.jetbrains.annotations.Nullable java.lang.Object r3, int r4) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:37)
	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:61)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:33)
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
        r2 = this;
    L_0x0000:
        r0 = r2.getState$kotlinx_coroutines_core();
        r0 = r2.tryMakeCompleting(r0, r3, r4);
        switch(r0) {
            case 0: goto L_0x001d;
            case 1: goto L_0x001b;
            case 2: goto L_0x0019;
            case 3: goto L_0x0000;
            default: goto L_0x000b;
        };
    L_0x000b:
        r3 = new java.lang.IllegalStateException;
        r4 = "unexpected result";
        r4 = r4.toString();
        r3.<init>(r4);
        r3 = (java.lang.Throwable) r3;
        throw r3;
    L_0x0019:
        r3 = 0;
        return r3;
    L_0x001b:
        r3 = 1;
        return r3;
    L_0x001d:
        r4 = new java.lang.IllegalStateException;
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = "Job ";
        r0.append(r1);
        r0.append(r2);
        r1 = " is already complete or completing, ";
        r0.append(r1);
        r1 = "but is being completed with ";
        r0.append(r1);
        r0.append(r3);
        r0 = r0.toString();
        r3 = r2.getExceptionOrNull(r3);
        r4.<init>(r0, r3);
        r4 = (java.lang.Throwable) r4;
        throw r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.JobSupport.makeCompletingOnce$kotlinx_coroutines_core(java.lang.Object, int):boolean");
    }

    public final <T, R> void registerSelectClause1Internal$kotlinx_coroutines_core(@NotNull SelectInstance<? super R> selectInstance, @NotNull Function2<? super T, ? super Continuation<? super R>, ? extends Object> function2) {
        Intrinsics.checkParameterIsNotNull(selectInstance, "select");
        Intrinsics.checkParameterIsNotNull(function2, "block");
        Object state$kotlinx_coroutines_core;
        do {
            state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
            if (!selectInstance.isSelected()) {
                if (!(state$kotlinx_coroutines_core instanceof Incomplete)) {
                    if (selectInstance.trySelect(null)) {
                        if (state$kotlinx_coroutines_core instanceof CompletedExceptionally) {
                            selectInstance.resumeSelectCancellableWithException(((CompletedExceptionally) state$kotlinx_coroutines_core).cause);
                        } else {
                            UndispatchedKt.startCoroutineUnintercepted(function2, state$kotlinx_coroutines_core, selectInstance.getCompletion());
                        }
                    }
                    return;
                }
            } else {
                return;
            }
        } while (startInternal(state$kotlinx_coroutines_core) != 0);
        selectInstance.disposeOnSelect(invokeOnCompletion(new SelectAwaitOnCompletion(this, selectInstance, function2)));
    }
}
