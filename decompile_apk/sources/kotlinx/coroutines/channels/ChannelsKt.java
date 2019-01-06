package kotlinx.coroutines.channels;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.IndexedValue;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.ObsoleteCoroutinesApi;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"kotlinx/coroutines/channels/ChannelsKt__ChannelsKt", "kotlinx/coroutines/channels/ChannelsKt__Channels_commonKt"}, k = 4, mv = {1, 1, 13})
public final class ChannelsKt {
    @NotNull
    public static final String DEFAULT_CLOSE_MESSAGE = "Channel was closed";

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E> Object all(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Function1<? super E, Boolean> function1, @NotNull Continuation<? super Boolean> continuation) {
        return ChannelsKt__Channels_commonKt.all(receiveChannel, function1, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    private static final Object all$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Function1 function1, @NotNull Continuation continuation) {
        return ChannelsKt__Channels_commonKt.all(receiveChannel, function1, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E> Object any(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Continuation<? super Boolean> continuation) {
        return ChannelsKt__Channels_commonKt.any(receiveChannel, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E> Object any(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Function1<? super E, Boolean> function1, @NotNull Continuation<? super Boolean> continuation) {
        return ChannelsKt__Channels_commonKt.any(receiveChannel, function1, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    private static final Object any$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Function1 function1, @NotNull Continuation continuation) {
        return ChannelsKt__Channels_commonKt.any(receiveChannel, function1, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E, K, V> Object associate(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Function1<? super E, ? extends Pair<? extends K, ? extends V>> function1, @NotNull Continuation<? super Map<K, ? extends V>> continuation) {
        return ChannelsKt__Channels_commonKt.associate(receiveChannel, function1, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    private static final Object associate$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Function1 function1, @NotNull Continuation continuation) {
        return ChannelsKt__Channels_commonKt.associate(receiveChannel, function1, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E, K> Object associateBy(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Function1<? super E, ? extends K> function1, @NotNull Continuation<? super Map<K, ? extends E>> continuation) {
        return ChannelsKt__Channels_commonKt.associateBy(receiveChannel, function1, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E, K, V> Object associateBy(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Function1<? super E, ? extends K> function1, @NotNull Function1<? super E, ? extends V> function12, @NotNull Continuation<? super Map<K, ? extends V>> continuation) {
        return ChannelsKt__Channels_commonKt.associateBy(receiveChannel, function1, function12, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    private static final Object associateBy$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Function1 function1, @NotNull Continuation continuation) {
        return ChannelsKt__Channels_commonKt.associateBy(receiveChannel, function1, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    private static final Object associateBy$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Function1 function1, @NotNull Function1 function12, @NotNull Continuation continuation) {
        return ChannelsKt__Channels_commonKt.associateBy(receiveChannel, function1, function12, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E, K, M extends Map<? super K, ? super E>> Object associateByTo(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull M m, @NotNull Function1<? super E, ? extends K> function1, @NotNull Continuation<? super M> continuation) {
        return ChannelsKt__Channels_commonKt.associateByTo(receiveChannel, m, function1, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E, K, V, M extends Map<? super K, ? super V>> Object associateByTo(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull M m, @NotNull Function1<? super E, ? extends K> function1, @NotNull Function1<? super E, ? extends V> function12, @NotNull Continuation<? super M> continuation) {
        return ChannelsKt__Channels_commonKt.associateByTo(receiveChannel, m, function1, function12, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    private static final Object associateByTo$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Map map, @NotNull Function1 function1, @NotNull Continuation continuation) {
        return ChannelsKt__Channels_commonKt.associateByTo(receiveChannel, map, function1, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    private static final Object associateByTo$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Map map, @NotNull Function1 function1, @NotNull Function1 function12, @NotNull Continuation continuation) {
        return ChannelsKt__Channels_commonKt.associateByTo(receiveChannel, map, function1, function12, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E, K, V, M extends Map<? super K, ? super V>> Object associateTo(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull M m, @NotNull Function1<? super E, ? extends Pair<? extends K, ? extends V>> function1, @NotNull Continuation<? super M> continuation) {
        return ChannelsKt__Channels_commonKt.associateTo(receiveChannel, m, function1, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    private static final Object associateTo$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Map map, @NotNull Function1 function1, @NotNull Continuation continuation) {
        return ChannelsKt__Channels_commonKt.associateTo(receiveChannel, map, function1, continuation);
    }

    @ObsoleteCoroutinesApi
    public static final <E, R> R consume(@NotNull BroadcastChannel<E> broadcastChannel, @NotNull Function1<? super ReceiveChannel<? extends E>, ? extends R> function1) {
        return ChannelsKt__Channels_commonKt.consume((BroadcastChannel) broadcastChannel, (Function1) function1);
    }

    @ObsoleteCoroutinesApi
    public static final <E, R> R consume(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Function1<? super ReceiveChannel<? extends E>, ? extends R> function1) {
        return ChannelsKt__Channels_commonKt.consume((ReceiveChannel) receiveChannel, (Function1) function1);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E> Object consumeEach(@NotNull BroadcastChannel<E> broadcastChannel, @NotNull Function1<? super E, Unit> function1, @NotNull Continuation<? super Unit> continuation) {
        return ChannelsKt__Channels_commonKt.consumeEach((BroadcastChannel) broadcastChannel, (Function1) function1, (Continuation) continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E> Object consumeEach(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Function1<? super E, Unit> function1, @NotNull Continuation<? super Unit> continuation) {
        return ChannelsKt__Channels_commonKt.consumeEach((ReceiveChannel) receiveChannel, (Function1) function1, (Continuation) continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    private static final Object consumeEach$$forInline(@NotNull BroadcastChannel broadcastChannel, @NotNull Function1 function1, @NotNull Continuation continuation) {
        return ChannelsKt__Channels_commonKt.consumeEach(broadcastChannel, function1, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    private static final Object consumeEach$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Function1 function1, @NotNull Continuation continuation) {
        return ChannelsKt__Channels_commonKt.consumeEach(receiveChannel, function1, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E> Object consumeEachIndexed(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Function1<? super IndexedValue<? extends E>, Unit> function1, @NotNull Continuation<? super Unit> continuation) {
        return ChannelsKt__Channels_commonKt.consumeEachIndexed(receiveChannel, function1, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    private static final Object consumeEachIndexed$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Function1 function1, @NotNull Continuation continuation) {
        return ChannelsKt__Channels_commonKt.consumeEachIndexed(receiveChannel, function1, continuation);
    }

    @NotNull
    @ObsoleteCoroutinesApi
    public static final Function1<Throwable, Unit> consumes(@NotNull ReceiveChannel<?> receiveChannel) {
        return ChannelsKt__Channels_commonKt.consumes(receiveChannel);
    }

    @NotNull
    @ObsoleteCoroutinesApi
    public static final Function1<Throwable, Unit> consumesAll(@NotNull ReceiveChannel<?>... receiveChannelArr) {
        return ChannelsKt__Channels_commonKt.consumesAll(receiveChannelArr);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E> Object count(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Continuation<? super Integer> continuation) {
        return ChannelsKt__Channels_commonKt.count(receiveChannel, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E> Object count(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Function1<? super E, Boolean> function1, @NotNull Continuation<? super Integer> continuation) {
        return ChannelsKt__Channels_commonKt.count(receiveChannel, function1, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    private static final Object count$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Function1 function1, @NotNull Continuation continuation) {
        return ChannelsKt__Channels_commonKt.count(receiveChannel, function1, continuation);
    }

    @NotNull
    @ObsoleteCoroutinesApi
    public static final <E> ReceiveChannel<E> distinct(@NotNull ReceiveChannel<? extends E> receiveChannel) {
        return ChannelsKt__Channels_commonKt.distinct(receiveChannel);
    }

    @NotNull
    @ObsoleteCoroutinesApi
    public static final <E, K> ReceiveChannel<E> distinctBy(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull CoroutineContext coroutineContext, @NotNull Function2<? super E, ? super Continuation<? super K>, ? extends Object> function2) {
        return ChannelsKt__Channels_commonKt.distinctBy(receiveChannel, coroutineContext, function2);
    }

    @NotNull
    @ObsoleteCoroutinesApi
    public static final <E> ReceiveChannel<E> drop(@NotNull ReceiveChannel<? extends E> receiveChannel, int i, @NotNull CoroutineContext coroutineContext) {
        return ChannelsKt__Channels_commonKt.drop(receiveChannel, i, coroutineContext);
    }

    @NotNull
    @ObsoleteCoroutinesApi
    public static final <E> ReceiveChannel<E> dropWhile(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull CoroutineContext coroutineContext, @NotNull Function2<? super E, ? super Continuation<? super Boolean>, ? extends Object> function2) {
        return ChannelsKt__Channels_commonKt.dropWhile(receiveChannel, coroutineContext, function2);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E> Object elementAt(@NotNull ReceiveChannel<? extends E> receiveChannel, int i, @NotNull Continuation<? super E> continuation) {
        return ChannelsKt__Channels_commonKt.elementAt(receiveChannel, i, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E> Object elementAtOrElse(@NotNull ReceiveChannel<? extends E> receiveChannel, int i, @NotNull Function1<? super Integer, ? extends E> function1, @NotNull Continuation<? super E> continuation) {
        return ChannelsKt__Channels_commonKt.elementAtOrElse(receiveChannel, i, function1, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    private static final Object elementAtOrElse$$forInline(@NotNull ReceiveChannel receiveChannel, int i, @NotNull Function1 function1, @NotNull Continuation continuation) {
        return ChannelsKt__Channels_commonKt.elementAtOrElse(receiveChannel, i, function1, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E> Object elementAtOrNull(@NotNull ReceiveChannel<? extends E> receiveChannel, int i, @NotNull Continuation<? super E> continuation) {
        return ChannelsKt__Channels_commonKt.elementAtOrNull(receiveChannel, i, continuation);
    }

    @NotNull
    @ObsoleteCoroutinesApi
    public static final <E> ReceiveChannel<E> filter(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull CoroutineContext coroutineContext, @NotNull Function2<? super E, ? super Continuation<? super Boolean>, ? extends Object> function2) {
        return ChannelsKt__Channels_commonKt.filter(receiveChannel, coroutineContext, function2);
    }

    @NotNull
    @ObsoleteCoroutinesApi
    public static final <E> ReceiveChannel<E> filterIndexed(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull CoroutineContext coroutineContext, @NotNull Function3<? super Integer, ? super E, ? super Continuation<? super Boolean>, ? extends Object> function3) {
        return ChannelsKt__Channels_commonKt.filterIndexed(receiveChannel, coroutineContext, function3);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E, C extends Collection<? super E>> Object filterIndexedTo(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull C c, @NotNull Function2<? super Integer, ? super E, Boolean> function2, @NotNull Continuation<? super C> continuation) {
        return ChannelsKt__Channels_commonKt.filterIndexedTo((ReceiveChannel) receiveChannel, (Collection) c, (Function2) function2, (Continuation) continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E, C extends SendChannel<? super E>> Object filterIndexedTo(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull C c, @NotNull Function2<? super Integer, ? super E, Boolean> function2, @NotNull Continuation<? super C> continuation) {
        return ChannelsKt__Channels_commonKt.filterIndexedTo((ReceiveChannel) receiveChannel, (SendChannel) c, (Function2) function2, (Continuation) continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    private static final Object filterIndexedTo$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Collection collection, @NotNull Function2 function2, @NotNull Continuation continuation) {
        return ChannelsKt__Channels_commonKt.filterIndexedTo(receiveChannel, collection, function2, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    private static final Object filterIndexedTo$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull SendChannel sendChannel, @NotNull Function2 function2, @NotNull Continuation continuation) {
        return ChannelsKt__Channels_commonKt.filterIndexedTo(receiveChannel, sendChannel, function2, continuation);
    }

    @NotNull
    @ObsoleteCoroutinesApi
    public static final <E> ReceiveChannel<E> filterNot(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull CoroutineContext coroutineContext, @NotNull Function2<? super E, ? super Continuation<? super Boolean>, ? extends Object> function2) {
        return ChannelsKt__Channels_commonKt.filterNot(receiveChannel, coroutineContext, function2);
    }

    @NotNull
    @ObsoleteCoroutinesApi
    public static final <E> ReceiveChannel<E> filterNotNull(@NotNull ReceiveChannel<? extends E> receiveChannel) {
        return ChannelsKt__Channels_commonKt.filterNotNull(receiveChannel);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E, C extends Collection<? super E>> Object filterNotNullTo(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull C c, @NotNull Continuation<? super C> continuation) {
        return ChannelsKt__Channels_commonKt.filterNotNullTo((ReceiveChannel) receiveChannel, (Collection) c, (Continuation) continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E, C extends SendChannel<? super E>> Object filterNotNullTo(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull C c, @NotNull Continuation<? super C> continuation) {
        return ChannelsKt__Channels_commonKt.filterNotNullTo((ReceiveChannel) receiveChannel, (SendChannel) c, (Continuation) continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E, C extends Collection<? super E>> Object filterNotTo(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull C c, @NotNull Function1<? super E, Boolean> function1, @NotNull Continuation<? super C> continuation) {
        return ChannelsKt__Channels_commonKt.filterNotTo((ReceiveChannel) receiveChannel, (Collection) c, (Function1) function1, (Continuation) continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E, C extends SendChannel<? super E>> Object filterNotTo(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull C c, @NotNull Function1<? super E, Boolean> function1, @NotNull Continuation<? super C> continuation) {
        return ChannelsKt__Channels_commonKt.filterNotTo((ReceiveChannel) receiveChannel, (SendChannel) c, (Function1) function1, (Continuation) continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    private static final Object filterNotTo$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Collection collection, @NotNull Function1 function1, @NotNull Continuation continuation) {
        return ChannelsKt__Channels_commonKt.filterNotTo(receiveChannel, collection, function1, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    private static final Object filterNotTo$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull SendChannel sendChannel, @NotNull Function1 function1, @NotNull Continuation continuation) {
        return ChannelsKt__Channels_commonKt.filterNotTo(receiveChannel, sendChannel, function1, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E, C extends Collection<? super E>> Object filterTo(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull C c, @NotNull Function1<? super E, Boolean> function1, @NotNull Continuation<? super C> continuation) {
        return ChannelsKt__Channels_commonKt.filterTo((ReceiveChannel) receiveChannel, (Collection) c, (Function1) function1, (Continuation) continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E, C extends SendChannel<? super E>> Object filterTo(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull C c, @NotNull Function1<? super E, Boolean> function1, @NotNull Continuation<? super C> continuation) {
        return ChannelsKt__Channels_commonKt.filterTo((ReceiveChannel) receiveChannel, (SendChannel) c, (Function1) function1, (Continuation) continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    private static final Object filterTo$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Collection collection, @NotNull Function1 function1, @NotNull Continuation continuation) {
        return ChannelsKt__Channels_commonKt.filterTo(receiveChannel, collection, function1, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    private static final Object filterTo$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull SendChannel sendChannel, @NotNull Function1 function1, @NotNull Continuation continuation) {
        return ChannelsKt__Channels_commonKt.filterTo(receiveChannel, sendChannel, function1, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E> Object find(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Function1<? super E, Boolean> function1, @NotNull Continuation<? super E> continuation) {
        return ChannelsKt__Channels_commonKt.find(receiveChannel, function1, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    private static final Object find$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Function1 function1, @NotNull Continuation continuation) {
        return ChannelsKt__Channels_commonKt.find(receiveChannel, function1, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E> Object findLast(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Function1<? super E, Boolean> function1, @NotNull Continuation<? super E> continuation) {
        return ChannelsKt__Channels_commonKt.findLast(receiveChannel, function1, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    private static final Object findLast$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Function1 function1, @NotNull Continuation continuation) {
        return ChannelsKt__Channels_commonKt.findLast(receiveChannel, function1, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E> Object first(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Continuation<? super E> continuation) {
        return ChannelsKt__Channels_commonKt.first(receiveChannel, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E> Object first(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Function1<? super E, Boolean> function1, @NotNull Continuation<? super E> continuation) {
        return ChannelsKt__Channels_commonKt.first(receiveChannel, function1, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    private static final Object first$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Function1 function1, @NotNull Continuation continuation) {
        return ChannelsKt__Channels_commonKt.first(receiveChannel, function1, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E> Object firstOrNull(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Continuation<? super E> continuation) {
        return ChannelsKt__Channels_commonKt.firstOrNull(receiveChannel, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E> Object firstOrNull(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Function1<? super E, Boolean> function1, @NotNull Continuation<? super E> continuation) {
        return ChannelsKt__Channels_commonKt.firstOrNull(receiveChannel, function1, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    private static final Object firstOrNull$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Function1 function1, @NotNull Continuation continuation) {
        return ChannelsKt__Channels_commonKt.firstOrNull(receiveChannel, function1, continuation);
    }

    @NotNull
    @ObsoleteCoroutinesApi
    public static final <E, R> ReceiveChannel<R> flatMap(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull CoroutineContext coroutineContext, @NotNull Function2<? super E, ? super Continuation<? super ReceiveChannel<? extends R>>, ? extends Object> function2) {
        return ChannelsKt__Channels_commonKt.flatMap(receiveChannel, coroutineContext, function2);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E, R> Object fold(@NotNull ReceiveChannel<? extends E> receiveChannel, R r, @NotNull Function2<? super R, ? super E, ? extends R> function2, @NotNull Continuation<? super R> continuation) {
        return ChannelsKt__Channels_commonKt.fold(receiveChannel, r, function2, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    private static final Object fold$$forInline(@NotNull ReceiveChannel receiveChannel, Object obj, @NotNull Function2 function2, @NotNull Continuation continuation) {
        return ChannelsKt__Channels_commonKt.fold(receiveChannel, obj, function2, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E, R> Object foldIndexed(@NotNull ReceiveChannel<? extends E> receiveChannel, R r, @NotNull Function3<? super Integer, ? super R, ? super E, ? extends R> function3, @NotNull Continuation<? super R> continuation) {
        return ChannelsKt__Channels_commonKt.foldIndexed(receiveChannel, r, function3, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    private static final Object foldIndexed$$forInline(@NotNull ReceiveChannel receiveChannel, Object obj, @NotNull Function3 function3, @NotNull Continuation continuation) {
        return ChannelsKt__Channels_commonKt.foldIndexed(receiveChannel, obj, function3, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E, K> Object groupBy(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Function1<? super E, ? extends K> function1, @NotNull Continuation<? super Map<K, ? extends List<? extends E>>> continuation) {
        return ChannelsKt__Channels_commonKt.groupBy(receiveChannel, function1, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E, K, V> Object groupBy(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Function1<? super E, ? extends K> function1, @NotNull Function1<? super E, ? extends V> function12, @NotNull Continuation<? super Map<K, ? extends List<? extends V>>> continuation) {
        return ChannelsKt__Channels_commonKt.groupBy(receiveChannel, function1, function12, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    private static final Object groupBy$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Function1 function1, @NotNull Continuation continuation) {
        return ChannelsKt__Channels_commonKt.groupBy(receiveChannel, function1, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    private static final Object groupBy$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Function1 function1, @NotNull Function1 function12, @NotNull Continuation continuation) {
        return ChannelsKt__Channels_commonKt.groupBy(receiveChannel, function1, function12, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E, K, M extends Map<? super K, List<E>>> Object groupByTo(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull M m, @NotNull Function1<? super E, ? extends K> function1, @NotNull Continuation<? super M> continuation) {
        return ChannelsKt__Channels_commonKt.groupByTo(receiveChannel, m, function1, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E, K, V, M extends Map<? super K, List<V>>> Object groupByTo(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull M m, @NotNull Function1<? super E, ? extends K> function1, @NotNull Function1<? super E, ? extends V> function12, @NotNull Continuation<? super M> continuation) {
        return ChannelsKt__Channels_commonKt.groupByTo(receiveChannel, m, function1, function12, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    private static final Object groupByTo$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Map map, @NotNull Function1 function1, @NotNull Continuation continuation) {
        return ChannelsKt__Channels_commonKt.groupByTo(receiveChannel, map, function1, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    private static final Object groupByTo$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Map map, @NotNull Function1 function1, @NotNull Function1 function12, @NotNull Continuation continuation) {
        return ChannelsKt__Channels_commonKt.groupByTo(receiveChannel, map, function1, function12, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E> Object indexOf(@NotNull ReceiveChannel<? extends E> receiveChannel, E e, @NotNull Continuation<? super Integer> continuation) {
        return ChannelsKt__Channels_commonKt.indexOf(receiveChannel, e, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E> Object indexOfFirst(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Function1<? super E, Boolean> function1, @NotNull Continuation<? super Integer> continuation) {
        return ChannelsKt__Channels_commonKt.indexOfFirst(receiveChannel, function1, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    private static final Object indexOfFirst$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Function1 function1, @NotNull Continuation continuation) {
        return ChannelsKt__Channels_commonKt.indexOfFirst(receiveChannel, function1, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E> Object indexOfLast(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Function1<? super E, Boolean> function1, @NotNull Continuation<? super Integer> continuation) {
        return ChannelsKt__Channels_commonKt.indexOfLast(receiveChannel, function1, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    private static final Object indexOfLast$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Function1 function1, @NotNull Continuation continuation) {
        return ChannelsKt__Channels_commonKt.indexOfLast(receiveChannel, function1, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E> Object last(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Continuation<? super E> continuation) {
        return ChannelsKt__Channels_commonKt.last(receiveChannel, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E> Object last(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Function1<? super E, Boolean> function1, @NotNull Continuation<? super E> continuation) {
        return ChannelsKt__Channels_commonKt.last(receiveChannel, function1, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    private static final Object last$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Function1 function1, @NotNull Continuation continuation) {
        return ChannelsKt__Channels_commonKt.last(receiveChannel, function1, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E> Object lastIndexOf(@NotNull ReceiveChannel<? extends E> receiveChannel, E e, @NotNull Continuation<? super Integer> continuation) {
        return ChannelsKt__Channels_commonKt.lastIndexOf(receiveChannel, e, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E> Object lastOrNull(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Continuation<? super E> continuation) {
        return ChannelsKt__Channels_commonKt.lastOrNull(receiveChannel, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E> Object lastOrNull(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Function1<? super E, Boolean> function1, @NotNull Continuation<? super E> continuation) {
        return ChannelsKt__Channels_commonKt.lastOrNull(receiveChannel, function1, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    private static final Object lastOrNull$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Function1 function1, @NotNull Continuation continuation) {
        return ChannelsKt__Channels_commonKt.lastOrNull(receiveChannel, function1, continuation);
    }

    @NotNull
    public static final <E, R> ReceiveChannel<R> map(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull CoroutineContext coroutineContext, @NotNull Function2<? super E, ? super Continuation<? super R>, ? extends Object> function2) {
        return ChannelsKt__Channels_commonKt.map(receiveChannel, coroutineContext, function2);
    }

    @NotNull
    @ObsoleteCoroutinesApi
    public static final <E, R> ReceiveChannel<R> mapIndexed(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull CoroutineContext coroutineContext, @NotNull Function3<? super Integer, ? super E, ? super Continuation<? super R>, ? extends Object> function3) {
        return ChannelsKt__Channels_commonKt.mapIndexed(receiveChannel, coroutineContext, function3);
    }

    @NotNull
    @ObsoleteCoroutinesApi
    public static final <E, R> ReceiveChannel<R> mapIndexedNotNull(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull CoroutineContext coroutineContext, @NotNull Function3<? super Integer, ? super E, ? super Continuation<? super R>, ? extends Object> function3) {
        return ChannelsKt__Channels_commonKt.mapIndexedNotNull(receiveChannel, coroutineContext, function3);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E, R, C extends Collection<? super R>> Object mapIndexedNotNullTo(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull C c, @NotNull Function2<? super Integer, ? super E, ? extends R> function2, @NotNull Continuation<? super C> continuation) {
        return ChannelsKt__Channels_commonKt.mapIndexedNotNullTo((ReceiveChannel) receiveChannel, (Collection) c, (Function2) function2, (Continuation) continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E, R, C extends SendChannel<? super R>> Object mapIndexedNotNullTo(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull C c, @NotNull Function2<? super Integer, ? super E, ? extends R> function2, @NotNull Continuation<? super C> continuation) {
        return ChannelsKt__Channels_commonKt.mapIndexedNotNullTo((ReceiveChannel) receiveChannel, (SendChannel) c, (Function2) function2, (Continuation) continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    private static final Object mapIndexedNotNullTo$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Collection collection, @NotNull Function2 function2, @NotNull Continuation continuation) {
        return ChannelsKt__Channels_commonKt.mapIndexedNotNullTo(receiveChannel, collection, function2, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    private static final Object mapIndexedNotNullTo$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull SendChannel sendChannel, @NotNull Function2 function2, @NotNull Continuation continuation) {
        return ChannelsKt__Channels_commonKt.mapIndexedNotNullTo(receiveChannel, sendChannel, function2, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E, R, C extends Collection<? super R>> Object mapIndexedTo(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull C c, @NotNull Function2<? super Integer, ? super E, ? extends R> function2, @NotNull Continuation<? super C> continuation) {
        return ChannelsKt__Channels_commonKt.mapIndexedTo((ReceiveChannel) receiveChannel, (Collection) c, (Function2) function2, (Continuation) continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E, R, C extends SendChannel<? super R>> Object mapIndexedTo(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull C c, @NotNull Function2<? super Integer, ? super E, ? extends R> function2, @NotNull Continuation<? super C> continuation) {
        return ChannelsKt__Channels_commonKt.mapIndexedTo((ReceiveChannel) receiveChannel, (SendChannel) c, (Function2) function2, (Continuation) continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    private static final Object mapIndexedTo$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Collection collection, @NotNull Function2 function2, @NotNull Continuation continuation) {
        return ChannelsKt__Channels_commonKt.mapIndexedTo(receiveChannel, collection, function2, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    private static final Object mapIndexedTo$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull SendChannel sendChannel, @NotNull Function2 function2, @NotNull Continuation continuation) {
        return ChannelsKt__Channels_commonKt.mapIndexedTo(receiveChannel, sendChannel, function2, continuation);
    }

    @NotNull
    @ObsoleteCoroutinesApi
    public static final <E, R> ReceiveChannel<R> mapNotNull(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull CoroutineContext coroutineContext, @NotNull Function2<? super E, ? super Continuation<? super R>, ? extends Object> function2) {
        return ChannelsKt__Channels_commonKt.mapNotNull(receiveChannel, coroutineContext, function2);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E, R, C extends Collection<? super R>> Object mapNotNullTo(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull C c, @NotNull Function1<? super E, ? extends R> function1, @NotNull Continuation<? super C> continuation) {
        return ChannelsKt__Channels_commonKt.mapNotNullTo((ReceiveChannel) receiveChannel, (Collection) c, (Function1) function1, (Continuation) continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E, R, C extends SendChannel<? super R>> Object mapNotNullTo(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull C c, @NotNull Function1<? super E, ? extends R> function1, @NotNull Continuation<? super C> continuation) {
        return ChannelsKt__Channels_commonKt.mapNotNullTo((ReceiveChannel) receiveChannel, (SendChannel) c, (Function1) function1, (Continuation) continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    private static final Object mapNotNullTo$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Collection collection, @NotNull Function1 function1, @NotNull Continuation continuation) {
        return ChannelsKt__Channels_commonKt.mapNotNullTo(receiveChannel, collection, function1, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    private static final Object mapNotNullTo$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull SendChannel sendChannel, @NotNull Function1 function1, @NotNull Continuation continuation) {
        return ChannelsKt__Channels_commonKt.mapNotNullTo(receiveChannel, sendChannel, function1, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E, R, C extends Collection<? super R>> Object mapTo(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull C c, @NotNull Function1<? super E, ? extends R> function1, @NotNull Continuation<? super C> continuation) {
        return ChannelsKt__Channels_commonKt.mapTo((ReceiveChannel) receiveChannel, (Collection) c, (Function1) function1, (Continuation) continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E, R, C extends SendChannel<? super R>> Object mapTo(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull C c, @NotNull Function1<? super E, ? extends R> function1, @NotNull Continuation<? super C> continuation) {
        return ChannelsKt__Channels_commonKt.mapTo((ReceiveChannel) receiveChannel, (SendChannel) c, (Function1) function1, (Continuation) continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    private static final Object mapTo$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Collection collection, @NotNull Function1 function1, @NotNull Continuation continuation) {
        return ChannelsKt__Channels_commonKt.mapTo(receiveChannel, collection, function1, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    private static final Object mapTo$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull SendChannel sendChannel, @NotNull Function1 function1, @NotNull Continuation continuation) {
        return ChannelsKt__Channels_commonKt.mapTo(receiveChannel, sendChannel, function1, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E, R extends Comparable<? super R>> Object maxBy(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Function1<? super E, ? extends R> function1, @NotNull Continuation<? super E> continuation) {
        return ChannelsKt__Channels_commonKt.maxBy(receiveChannel, function1, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    private static final Object maxBy$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Function1 function1, @NotNull Continuation continuation) {
        return ChannelsKt__Channels_commonKt.maxBy(receiveChannel, function1, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E> Object maxWith(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Comparator<? super E> comparator, @NotNull Continuation<? super E> continuation) {
        return ChannelsKt__Channels_commonKt.maxWith(receiveChannel, comparator, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E, R extends Comparable<? super R>> Object minBy(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Function1<? super E, ? extends R> function1, @NotNull Continuation<? super E> continuation) {
        return ChannelsKt__Channels_commonKt.minBy(receiveChannel, function1, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    private static final Object minBy$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Function1 function1, @NotNull Continuation continuation) {
        return ChannelsKt__Channels_commonKt.minBy(receiveChannel, function1, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E> Object minWith(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Comparator<? super E> comparator, @NotNull Continuation<? super E> continuation) {
        return ChannelsKt__Channels_commonKt.minWith(receiveChannel, comparator, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E> Object none(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Continuation<? super Boolean> continuation) {
        return ChannelsKt__Channels_commonKt.none(receiveChannel, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E> Object none(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Function1<? super E, Boolean> function1, @NotNull Continuation<? super Boolean> continuation) {
        return ChannelsKt__Channels_commonKt.none(receiveChannel, function1, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    private static final Object none$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Function1 function1, @NotNull Continuation continuation) {
        return ChannelsKt__Channels_commonKt.none(receiveChannel, function1, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E> Object partition(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Function1<? super E, Boolean> function1, @NotNull Continuation<? super Pair<? extends List<? extends E>, ? extends List<? extends E>>> continuation) {
        return ChannelsKt__Channels_commonKt.partition(receiveChannel, function1, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    private static final Object partition$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Function1 function1, @NotNull Continuation continuation) {
        return ChannelsKt__Channels_commonKt.partition(receiveChannel, function1, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <S, E extends S> Object reduce(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Function2<? super S, ? super E, ? extends S> function2, @NotNull Continuation<? super S> continuation) {
        return ChannelsKt__Channels_commonKt.reduce(receiveChannel, function2, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    private static final Object reduce$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Function2 function2, @NotNull Continuation continuation) {
        return ChannelsKt__Channels_commonKt.reduce(receiveChannel, function2, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <S, E extends S> Object reduceIndexed(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Function3<? super Integer, ? super S, ? super E, ? extends S> function3, @NotNull Continuation<? super S> continuation) {
        return ChannelsKt__Channels_commonKt.reduceIndexed(receiveChannel, function3, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    private static final Object reduceIndexed$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Function3 function3, @NotNull Continuation continuation) {
        return ChannelsKt__Channels_commonKt.reduceIndexed(receiveChannel, function3, continuation);
    }

    @NotNull
    @ObsoleteCoroutinesApi
    public static final <E> ReceiveChannel<E> requireNoNulls(@NotNull ReceiveChannel<? extends E> receiveChannel) {
        return ChannelsKt__Channels_commonKt.requireNoNulls(receiveChannel);
    }

    public static final <E> void sendBlocking(@NotNull SendChannel<? super E> sendChannel, E e) {
        ChannelsKt__ChannelsKt.sendBlocking(sendChannel, e);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E> Object single(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Continuation<? super E> continuation) {
        return ChannelsKt__Channels_commonKt.single(receiveChannel, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E> Object single(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Function1<? super E, Boolean> function1, @NotNull Continuation<? super E> continuation) {
        return ChannelsKt__Channels_commonKt.single(receiveChannel, function1, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    private static final Object single$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Function1 function1, @NotNull Continuation continuation) {
        return ChannelsKt__Channels_commonKt.single(receiveChannel, function1, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E> Object singleOrNull(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Continuation<? super E> continuation) {
        return ChannelsKt__Channels_commonKt.singleOrNull(receiveChannel, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E> Object singleOrNull(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Function1<? super E, Boolean> function1, @NotNull Continuation<? super E> continuation) {
        return ChannelsKt__Channels_commonKt.singleOrNull(receiveChannel, function1, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    private static final Object singleOrNull$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Function1 function1, @NotNull Continuation continuation) {
        return ChannelsKt__Channels_commonKt.singleOrNull(receiveChannel, function1, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E> Object sumBy(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Function1<? super E, Integer> function1, @NotNull Continuation<? super Integer> continuation) {
        return ChannelsKt__Channels_commonKt.sumBy(receiveChannel, function1, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    private static final Object sumBy$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Function1 function1, @NotNull Continuation continuation) {
        return ChannelsKt__Channels_commonKt.sumBy(receiveChannel, function1, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E> Object sumByDouble(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Function1<? super E, Double> function1, @NotNull Continuation<? super Double> continuation) {
        return ChannelsKt__Channels_commonKt.sumByDouble(receiveChannel, function1, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    private static final Object sumByDouble$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Function1 function1, @NotNull Continuation continuation) {
        return ChannelsKt__Channels_commonKt.sumByDouble(receiveChannel, function1, continuation);
    }

    @NotNull
    @ObsoleteCoroutinesApi
    public static final <E> ReceiveChannel<E> take(@NotNull ReceiveChannel<? extends E> receiveChannel, int i, @NotNull CoroutineContext coroutineContext) {
        return ChannelsKt__Channels_commonKt.take(receiveChannel, i, coroutineContext);
    }

    @NotNull
    @ObsoleteCoroutinesApi
    public static final <E> ReceiveChannel<E> takeWhile(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull CoroutineContext coroutineContext, @NotNull Function2<? super E, ? super Continuation<? super Boolean>, ? extends Object> function2) {
        return ChannelsKt__Channels_commonKt.takeWhile(receiveChannel, coroutineContext, function2);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E, C extends SendChannel<? super E>> Object toChannel(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull C c, @NotNull Continuation<? super C> continuation) {
        return ChannelsKt__Channels_commonKt.toChannel(receiveChannel, c, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E, C extends Collection<? super E>> Object toCollection(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull C c, @NotNull Continuation<? super C> continuation) {
        return ChannelsKt__Channels_commonKt.toCollection(receiveChannel, c, continuation);
    }

    @Nullable
    public static final <E> Object toList(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Continuation<? super List<? extends E>> continuation) {
        return ChannelsKt__Channels_commonKt.toList(receiveChannel, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <K, V, M extends Map<? super K, ? super V>> Object toMap(@NotNull ReceiveChannel<? extends Pair<? extends K, ? extends V>> receiveChannel, @NotNull M m, @NotNull Continuation<? super M> continuation) {
        return ChannelsKt__Channels_commonKt.toMap(receiveChannel, m, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <K, V> Object toMap(@NotNull ReceiveChannel<? extends Pair<? extends K, ? extends V>> receiveChannel, @NotNull Continuation<? super Map<K, ? extends V>> continuation) {
        return ChannelsKt__Channels_commonKt.toMap(receiveChannel, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E> Object toMutableList(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Continuation<? super List<E>> continuation) {
        return ChannelsKt__Channels_commonKt.toMutableList(receiveChannel, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E> Object toMutableSet(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Continuation<? super Set<E>> continuation) {
        return ChannelsKt__Channels_commonKt.toMutableSet(receiveChannel, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E> Object toSet(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Continuation<? super Set<? extends E>> continuation) {
        return ChannelsKt__Channels_commonKt.toSet(receiveChannel, continuation);
    }

    @NotNull
    @ObsoleteCoroutinesApi
    public static final <E> ReceiveChannel<IndexedValue<E>> withIndex(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull CoroutineContext coroutineContext) {
        return ChannelsKt__Channels_commonKt.withIndex(receiveChannel, coroutineContext);
    }

    @NotNull
    @ObsoleteCoroutinesApi
    public static final <E, R> ReceiveChannel<Pair<E, R>> zip(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull ReceiveChannel<? extends R> receiveChannel2) {
        return ChannelsKt__Channels_commonKt.zip(receiveChannel, receiveChannel2);
    }

    @NotNull
    @ObsoleteCoroutinesApi
    public static final <E, R, V> ReceiveChannel<V> zip(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull ReceiveChannel<? extends R> receiveChannel2, @NotNull CoroutineContext coroutineContext, @NotNull Function2<? super E, ? super R, ? extends V> function2) {
        return ChannelsKt__Channels_commonKt.zip(receiveChannel, receiveChannel2, coroutineContext, function2);
    }
}
