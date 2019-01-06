package kotlinx.coroutines.channels;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.IndexedValue;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.ObsoleteCoroutinesApi;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000Ð\u0001\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010%\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u001f\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010 \n\u0000\n\u0002\u0010!\n\u0002\b\u0011\n\u0002\u0010\u000f\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u0006\n\u0002\b\t\n\u0002\u0010#\n\u0000\n\u0002\u0010\"\n\u0002\b\u0006\u001aJ\u0010\u0002\u001a#\u0012\u0015\u0012\u0013\u0018\u00010\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u0003j\u0002`\t2\u001a\u0010\n\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030\f0\u000b\"\u0006\u0012\u0002\b\u00030\fH\u0007¢\u0006\u0002\u0010\r\u001a5\u0010\u000e\u001a\u00020\u000f\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u00020\u000f0\u0003HHø\u0001\u0000¢\u0006\u0002\u0010\u0012\u001a!\u0010\u0013\u001a\u00020\u000f\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\fH@ø\u0001\u0000¢\u0006\u0002\u0010\u0014\u001a5\u0010\u0013\u001a\u00020\u000f\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u00020\u000f0\u0003HHø\u0001\u0000¢\u0006\u0002\u0010\u0012\u001aY\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u0002H\u0017\u0012\u0004\u0012\u0002H\u00180\u0016\"\u0004\b\u0000\u0010\u0010\"\u0004\b\u0001\u0010\u0017\"\u0004\b\u0002\u0010\u0018*\b\u0012\u0004\u0012\u0002H\u00100\f2\u001e\u0010\u0019\u001a\u001a\u0012\u0004\u0012\u0002H\u0010\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0017\u0012\u0004\u0012\u0002H\u00180\u001a0\u0003HHø\u0001\u0000¢\u0006\u0002\u0010\u0012\u001aG\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u0002H\u0017\u0012\u0004\u0012\u0002H\u00100\u0016\"\u0004\b\u0000\u0010\u0010\"\u0004\b\u0001\u0010\u0017*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0012\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H\u00170\u0003HHø\u0001\u0000¢\u0006\u0002\u0010\u0012\u001aa\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u0002H\u0017\u0012\u0004\u0012\u0002H\u00180\u0016\"\u0004\b\u0000\u0010\u0010\"\u0004\b\u0001\u0010\u0017\"\u0004\b\u0002\u0010\u0018*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0012\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H\u00170\u00032\u0012\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H\u00180\u0003HHø\u0001\u0000¢\u0006\u0002\u0010\u001e\u001a]\u0010\u001f\u001a\u0002H \"\u0004\b\u0000\u0010\u0010\"\u0004\b\u0001\u0010\u0017\"\u0018\b\u0002\u0010 *\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0017\u0012\u0006\b\u0000\u0012\u0002H\u00100!*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0006\u0010\"\u001a\u0002H 2\u0012\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H\u00170\u0003HHø\u0001\u0000¢\u0006\u0002\u0010#\u001aw\u0010\u001f\u001a\u0002H \"\u0004\b\u0000\u0010\u0010\"\u0004\b\u0001\u0010\u0017\"\u0004\b\u0002\u0010\u0018\"\u0018\b\u0003\u0010 *\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0017\u0012\u0006\b\u0000\u0012\u0002H\u00180!*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0006\u0010\"\u001a\u0002H 2\u0012\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H\u00170\u00032\u0012\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H\u00180\u0003HHø\u0001\u0000¢\u0006\u0002\u0010$\u001ao\u0010%\u001a\u0002H \"\u0004\b\u0000\u0010\u0010\"\u0004\b\u0001\u0010\u0017\"\u0004\b\u0002\u0010\u0018\"\u0018\b\u0003\u0010 *\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0017\u0012\u0006\b\u0000\u0012\u0002H\u00180!*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0006\u0010\"\u001a\u0002H 2\u001e\u0010\u0019\u001a\u001a\u0012\u0004\u0012\u0002H\u0010\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0017\u0012\u0004\u0012\u0002H\u00180\u001a0\u0003HHø\u0001\u0000¢\u0006\u0002\u0010#\u001aC\u0010&\u001a\u0002H'\"\u0004\b\u0000\u0010\u0010\"\u0004\b\u0001\u0010'*\b\u0012\u0004\u0012\u0002H\u00100(2\u001d\u0010)\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00100\f\u0012\u0004\u0012\u0002H'0\u0003¢\u0006\u0002\b*H\b¢\u0006\u0002\u0010+\u001aC\u0010&\u001a\u0002H'\"\u0004\b\u0000\u0010\u0010\"\u0004\b\u0001\u0010'*\b\u0012\u0004\u0012\u0002H\u00100\f2\u001d\u0010)\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00100\f\u0012\u0004\u0012\u0002H'0\u0003¢\u0006\u0002\b*H\b¢\u0006\u0002\u0010,\u001a5\u0010-\u001a\u00020\b\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100(2\u0012\u0010.\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u00020\b0\u0003HHø\u0001\u0000¢\u0006\u0002\u0010/\u001a5\u0010-\u001a\u00020\b\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0012\u0010.\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u00020\b0\u0003HHø\u0001\u0000¢\u0006\u0002\u0010\u0012\u001a;\u00100\u001a\u00020\b\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0018\u0010.\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u001001\u0012\u0004\u0012\u00020\b0\u0003HHø\u0001\u0000¢\u0006\u0002\u0010\u0012\u001a1\u00102\u001a#\u0012\u0015\u0012\u0013\u0018\u00010\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u0003j\u0002`\t*\u0006\u0012\u0002\b\u00030\fH\u0007\u001a!\u00103\u001a\u000204\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\fH@ø\u0001\u0000¢\u0006\u0002\u0010\u0014\u001a5\u00103\u001a\u000204\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u00020\u000f0\u0003HHø\u0001\u0000¢\u0006\u0002\u0010\u0012\u001a\u001e\u00105\u001a\b\u0012\u0004\u0012\u0002H\u00100\f\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\fH\u0007\u001aZ\u00106\u001a\b\u0012\u0004\u0012\u0002H\u00100\f\"\u0004\b\u0000\u0010\u0010\"\u0004\b\u0001\u0010\u0017*\b\u0012\u0004\u0012\u0002H\u00100\f2\b\b\u0002\u00107\u001a\u0002082\"\u00109\u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00170;\u0012\u0006\u0012\u0004\u0018\u00010<0:H\u0007ø\u0001\u0000¢\u0006\u0002\u0010=\u001a0\u0010>\u001a\b\u0012\u0004\u0012\u0002H\u00100\f\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0006\u0010?\u001a\u0002042\b\b\u0002\u00107\u001a\u000208H\u0007\u001aT\u0010@\u001a\b\u0012\u0004\u0012\u0002H\u00100\f\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\f2\b\b\u0002\u00107\u001a\u0002082\"\u0010\u0011\u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0;\u0012\u0006\u0012\u0004\u0018\u00010<0:H\u0007ø\u0001\u0000¢\u0006\u0002\u0010=\u001a)\u0010A\u001a\u0002H\u0010\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0006\u0010B\u001a\u000204H@ø\u0001\u0000¢\u0006\u0002\u0010C\u001a=\u0010D\u001a\u0002H\u0010\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0006\u0010B\u001a\u0002042\u0012\u0010E\u001a\u000e\u0012\u0004\u0012\u000204\u0012\u0004\u0012\u0002H\u00100\u0003HHø\u0001\u0000¢\u0006\u0002\u0010F\u001a+\u0010G\u001a\u0004\u0018\u0001H\u0010\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0006\u0010B\u001a\u000204H@ø\u0001\u0000¢\u0006\u0002\u0010C\u001aT\u0010H\u001a\b\u0012\u0004\u0012\u0002H\u00100\f\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\f2\b\b\u0002\u00107\u001a\u0002082\"\u0010\u0011\u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0;\u0012\u0006\u0012\u0004\u0018\u00010<0:H\u0007ø\u0001\u0000¢\u0006\u0002\u0010=\u001ai\u0010I\u001a\b\u0012\u0004\u0012\u0002H\u00100\f\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\f2\b\b\u0002\u00107\u001a\u00020827\u0010\u0011\u001a3\b\u0001\u0012\u0013\u0012\u001104¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(B\u0012\u0004\u0012\u0002H\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0;\u0012\u0006\u0012\u0004\u0018\u00010<0JH\u0007ø\u0001\u0000¢\u0006\u0002\u0010K\u001ad\u0010L\u001a\u0002HM\"\u0004\b\u0000\u0010\u0010\"\u0010\b\u0001\u0010M*\n\u0012\u0006\b\u0000\u0012\u0002H\u00100N*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0006\u0010\"\u001a\u0002HM2'\u0010\u0011\u001a#\u0012\u0013\u0012\u001104¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(B\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u00020\u000f0:HHø\u0001\u0000¢\u0006\u0002\u0010O\u001ab\u0010L\u001a\u0002HM\"\u0004\b\u0000\u0010\u0010\"\u000e\b\u0001\u0010M*\b\u0012\u0004\u0012\u0002H\u00100P*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0006\u0010\"\u001a\u0002HM2'\u0010\u0011\u001a#\u0012\u0013\u0012\u001104¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(B\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u00020\u000f0:HHø\u0001\u0000¢\u0006\u0002\u0010Q\u001aT\u0010R\u001a\b\u0012\u0004\u0012\u0002H\u00100\f\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\f2\b\b\u0002\u00107\u001a\u0002082\"\u0010\u0011\u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0;\u0012\u0006\u0012\u0004\u0018\u00010<0:H\u0007ø\u0001\u0000¢\u0006\u0002\u0010=\u001a$\u0010S\u001a\b\u0012\u0004\u0012\u0002H\u00100\f\"\b\b\u0000\u0010\u0010*\u00020<*\n\u0012\u0006\u0012\u0004\u0018\u0001H\u00100\fH\u0007\u001aA\u0010T\u001a\u0002HM\"\b\b\u0000\u0010\u0010*\u00020<\"\u0010\b\u0001\u0010M*\n\u0012\u0006\b\u0000\u0012\u0002H\u00100N*\n\u0012\u0006\u0012\u0004\u0018\u0001H\u00100\f2\u0006\u0010\"\u001a\u0002HMH@ø\u0001\u0000¢\u0006\u0002\u0010U\u001a?\u0010T\u001a\u0002HM\"\b\b\u0000\u0010\u0010*\u00020<\"\u000e\b\u0001\u0010M*\b\u0012\u0004\u0012\u0002H\u00100P*\n\u0012\u0006\u0012\u0004\u0018\u0001H\u00100\f2\u0006\u0010\"\u001a\u0002HMH@ø\u0001\u0000¢\u0006\u0002\u0010V\u001aO\u0010W\u001a\u0002HM\"\u0004\b\u0000\u0010\u0010\"\u0010\b\u0001\u0010M*\n\u0012\u0006\b\u0000\u0012\u0002H\u00100N*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0006\u0010\"\u001a\u0002HM2\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u00020\u000f0\u0003HHø\u0001\u0000¢\u0006\u0002\u0010X\u001aM\u0010W\u001a\u0002HM\"\u0004\b\u0000\u0010\u0010\"\u000e\b\u0001\u0010M*\b\u0012\u0004\u0012\u0002H\u00100P*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0006\u0010\"\u001a\u0002HM2\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u00020\u000f0\u0003HHø\u0001\u0000¢\u0006\u0002\u0010Y\u001aO\u0010Z\u001a\u0002HM\"\u0004\b\u0000\u0010\u0010\"\u0010\b\u0001\u0010M*\n\u0012\u0006\b\u0000\u0012\u0002H\u00100N*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0006\u0010\"\u001a\u0002HM2\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u00020\u000f0\u0003HHø\u0001\u0000¢\u0006\u0002\u0010X\u001aM\u0010Z\u001a\u0002HM\"\u0004\b\u0000\u0010\u0010\"\u000e\b\u0001\u0010M*\b\u0012\u0004\u0012\u0002H\u00100P*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0006\u0010\"\u001a\u0002HM2\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u00020\u000f0\u0003HHø\u0001\u0000¢\u0006\u0002\u0010Y\u001a7\u0010[\u001a\u0004\u0018\u0001H\u0010\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u00020\u000f0\u0003HHø\u0001\u0000¢\u0006\u0002\u0010\u0012\u001a7\u0010\\\u001a\u0004\u0018\u0001H\u0010\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u00020\u000f0\u0003HHø\u0001\u0000¢\u0006\u0002\u0010\u0012\u001a!\u0010]\u001a\u0002H\u0010\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\fH@ø\u0001\u0000¢\u0006\u0002\u0010\u0014\u001a5\u0010]\u001a\u0002H\u0010\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u00020\u000f0\u0003HHø\u0001\u0000¢\u0006\u0002\u0010\u0012\u001a#\u0010^\u001a\u0004\u0018\u0001H\u0010\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\fH@ø\u0001\u0000¢\u0006\u0002\u0010\u0014\u001a7\u0010^\u001a\u0004\u0018\u0001H\u0010\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u00020\u000f0\u0003HHø\u0001\u0000¢\u0006\u0002\u0010\u0012\u001a`\u0010_\u001a\b\u0012\u0004\u0012\u0002H'0\f\"\u0004\b\u0000\u0010\u0010\"\u0004\b\u0001\u0010'*\b\u0012\u0004\u0012\u0002H\u00100\f2\b\b\u0002\u00107\u001a\u0002082(\u0010\u0019\u001a$\b\u0001\u0012\u0004\u0012\u0002H\u0010\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H'0\f0;\u0012\u0006\u0012\u0004\u0018\u00010<0:H\u0007ø\u0001\u0000¢\u0006\u0002\u0010=\u001aX\u0010`\u001a\u0002H'\"\u0004\b\u0000\u0010\u0010\"\u0004\b\u0001\u0010'*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0006\u0010a\u001a\u0002H'2'\u0010b\u001a#\u0012\u0013\u0012\u0011H'¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(c\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H'0:HHø\u0001\u0000¢\u0006\u0002\u0010d\u001am\u0010e\u001a\u0002H'\"\u0004\b\u0000\u0010\u0010\"\u0004\b\u0001\u0010'*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0006\u0010a\u001a\u0002H'2<\u0010b\u001a8\u0012\u0013\u0012\u001104¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(B\u0012\u0013\u0012\u0011H'¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(c\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H'0JHHø\u0001\u0000¢\u0006\u0002\u0010f\u001aM\u0010g\u001a\u0014\u0012\u0004\u0012\u0002H\u0017\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00100h0\u0016\"\u0004\b\u0000\u0010\u0010\"\u0004\b\u0001\u0010\u0017*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0012\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H\u00170\u0003HHø\u0001\u0000¢\u0006\u0002\u0010\u0012\u001ag\u0010g\u001a\u0014\u0012\u0004\u0012\u0002H\u0017\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00180h0\u0016\"\u0004\b\u0000\u0010\u0010\"\u0004\b\u0001\u0010\u0017\"\u0004\b\u0002\u0010\u0018*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0012\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H\u00170\u00032\u0012\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H\u00180\u0003HHø\u0001\u0000¢\u0006\u0002\u0010\u001e\u001aa\u0010i\u001a\u0002H \"\u0004\b\u0000\u0010\u0010\"\u0004\b\u0001\u0010\u0017\"\u001c\b\u0002\u0010 *\u0016\u0012\u0006\b\u0000\u0012\u0002H\u0017\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00100j0!*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0006\u0010\"\u001a\u0002H 2\u0012\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H\u00170\u0003HHø\u0001\u0000¢\u0006\u0002\u0010#\u001a{\u0010i\u001a\u0002H \"\u0004\b\u0000\u0010\u0010\"\u0004\b\u0001\u0010\u0017\"\u0004\b\u0002\u0010\u0018\"\u001c\b\u0003\u0010 *\u0016\u0012\u0006\b\u0000\u0012\u0002H\u0017\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00180j0!*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0006\u0010\"\u001a\u0002H 2\u0012\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H\u00170\u00032\u0012\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H\u00180\u0003HHø\u0001\u0000¢\u0006\u0002\u0010$\u001a)\u0010k\u001a\u000204\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0006\u0010l\u001a\u0002H\u0010H@ø\u0001\u0000¢\u0006\u0002\u0010m\u001a5\u0010n\u001a\u000204\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u00020\u000f0\u0003HHø\u0001\u0000¢\u0006\u0002\u0010\u0012\u001a5\u0010o\u001a\u000204\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u00020\u000f0\u0003HHø\u0001\u0000¢\u0006\u0002\u0010\u0012\u001a!\u0010p\u001a\u0002H\u0010\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\fH@ø\u0001\u0000¢\u0006\u0002\u0010\u0014\u001a5\u0010p\u001a\u0002H\u0010\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u00020\u000f0\u0003HHø\u0001\u0000¢\u0006\u0002\u0010\u0012\u001a)\u0010q\u001a\u000204\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0006\u0010l\u001a\u0002H\u0010H@ø\u0001\u0000¢\u0006\u0002\u0010m\u001a#\u0010r\u001a\u0004\u0018\u0001H\u0010\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\fH@ø\u0001\u0000¢\u0006\u0002\u0010\u0014\u001a7\u0010r\u001a\u0004\u0018\u0001H\u0010\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u00020\u000f0\u0003HHø\u0001\u0000¢\u0006\u0002\u0010\u0012\u001aX\u0010s\u001a\b\u0012\u0004\u0012\u0002H'0\f\"\u0004\b\u0000\u0010\u0010\"\u0004\b\u0001\u0010'*\b\u0012\u0004\u0012\u0002H\u00100\f2\b\b\u0002\u00107\u001a\u0002082\"\u0010\u0019\u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u0002H'0;\u0012\u0006\u0012\u0004\u0018\u00010<0:ø\u0001\u0000¢\u0006\u0002\u0010=\u001ao\u0010t\u001a\b\u0012\u0004\u0012\u0002H'0\f\"\u0004\b\u0000\u0010\u0010\"\u0004\b\u0001\u0010'*\b\u0012\u0004\u0012\u0002H\u00100\f2\b\b\u0002\u00107\u001a\u00020827\u0010\u0019\u001a3\b\u0001\u0012\u0013\u0012\u001104¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(B\u0012\u0004\u0012\u0002H\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u0002H'0;\u0012\u0006\u0012\u0004\u0018\u00010<0JH\u0007ø\u0001\u0000¢\u0006\u0002\u0010K\u001au\u0010u\u001a\b\u0012\u0004\u0012\u0002H'0\f\"\u0004\b\u0000\u0010\u0010\"\b\b\u0001\u0010'*\u00020<*\b\u0012\u0004\u0012\u0002H\u00100\f2\b\b\u0002\u00107\u001a\u00020829\u0010\u0019\u001a5\b\u0001\u0012\u0013\u0012\u001104¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(B\u0012\u0004\u0012\u0002H\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u0001H'0;\u0012\u0006\u0012\u0004\u0018\u00010<0JH\u0007ø\u0001\u0000¢\u0006\u0002\u0010K\u001ap\u0010v\u001a\u0002HM\"\u0004\b\u0000\u0010\u0010\"\b\b\u0001\u0010'*\u00020<\"\u0010\b\u0002\u0010M*\n\u0012\u0006\b\u0000\u0012\u0002H'0N*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0006\u0010\"\u001a\u0002HM2)\u0010\u0019\u001a%\u0012\u0013\u0012\u001104¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(B\u0012\u0004\u0012\u0002H\u0010\u0012\u0006\u0012\u0004\u0018\u0001H'0:HHø\u0001\u0000¢\u0006\u0002\u0010O\u001an\u0010v\u001a\u0002HM\"\u0004\b\u0000\u0010\u0010\"\b\b\u0001\u0010'*\u00020<\"\u000e\b\u0002\u0010M*\b\u0012\u0004\u0012\u0002H'0P*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0006\u0010\"\u001a\u0002HM2)\u0010\u0019\u001a%\u0012\u0013\u0012\u001104¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(B\u0012\u0004\u0012\u0002H\u0010\u0012\u0006\u0012\u0004\u0018\u0001H'0:HHø\u0001\u0000¢\u0006\u0002\u0010Q\u001aj\u0010w\u001a\u0002HM\"\u0004\b\u0000\u0010\u0010\"\u0004\b\u0001\u0010'\"\u0010\b\u0002\u0010M*\n\u0012\u0006\b\u0000\u0012\u0002H'0N*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0006\u0010\"\u001a\u0002HM2'\u0010\u0019\u001a#\u0012\u0013\u0012\u001104¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(B\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H'0:HHø\u0001\u0000¢\u0006\u0002\u0010O\u001ah\u0010w\u001a\u0002HM\"\u0004\b\u0000\u0010\u0010\"\u0004\b\u0001\u0010'\"\u000e\b\u0002\u0010M*\b\u0012\u0004\u0012\u0002H'0P*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0006\u0010\"\u001a\u0002HM2'\u0010\u0019\u001a#\u0012\u0013\u0012\u001104¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(B\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H'0:HHø\u0001\u0000¢\u0006\u0002\u0010Q\u001a`\u0010x\u001a\b\u0012\u0004\u0012\u0002H'0\f\"\u0004\b\u0000\u0010\u0010\"\b\b\u0001\u0010'*\u00020<*\b\u0012\u0004\u0012\u0002H\u00100\f2\b\b\u0002\u00107\u001a\u0002082$\u0010\u0019\u001a \b\u0001\u0012\u0004\u0012\u0002H\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u0001H'0;\u0012\u0006\u0012\u0004\u0018\u00010<0:H\u0007ø\u0001\u0000¢\u0006\u0002\u0010=\u001a[\u0010y\u001a\u0002HM\"\u0004\b\u0000\u0010\u0010\"\b\b\u0001\u0010'*\u00020<\"\u0010\b\u0002\u0010M*\n\u0012\u0006\b\u0000\u0012\u0002H'0N*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0006\u0010\"\u001a\u0002HM2\u0014\u0010\u0019\u001a\u0010\u0012\u0004\u0012\u0002H\u0010\u0012\u0006\u0012\u0004\u0018\u0001H'0\u0003HHø\u0001\u0000¢\u0006\u0002\u0010X\u001aY\u0010y\u001a\u0002HM\"\u0004\b\u0000\u0010\u0010\"\b\b\u0001\u0010'*\u00020<\"\u000e\b\u0002\u0010M*\b\u0012\u0004\u0012\u0002H'0P*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0006\u0010\"\u001a\u0002HM2\u0014\u0010\u0019\u001a\u0010\u0012\u0004\u0012\u0002H\u0010\u0012\u0006\u0012\u0004\u0018\u0001H'0\u0003HHø\u0001\u0000¢\u0006\u0002\u0010Y\u001aU\u0010z\u001a\u0002HM\"\u0004\b\u0000\u0010\u0010\"\u0004\b\u0001\u0010'\"\u0010\b\u0002\u0010M*\n\u0012\u0006\b\u0000\u0012\u0002H'0N*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0006\u0010\"\u001a\u0002HM2\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H'0\u0003HHø\u0001\u0000¢\u0006\u0002\u0010X\u001aS\u0010z\u001a\u0002HM\"\u0004\b\u0000\u0010\u0010\"\u0004\b\u0001\u0010'\"\u000e\b\u0002\u0010M*\b\u0012\u0004\u0012\u0002H'0P*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0006\u0010\"\u001a\u0002HM2\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H'0\u0003HHø\u0001\u0000¢\u0006\u0002\u0010Y\u001aG\u0010{\u001a\u0004\u0018\u0001H\u0010\"\u0004\b\u0000\u0010\u0010\"\u000e\b\u0001\u0010'*\b\u0012\u0004\u0012\u0002H'0|*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0012\u00109\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H'0\u0003HHø\u0001\u0000¢\u0006\u0002\u0010\u0012\u001aA\u0010}\u001a\u0004\u0018\u0001H\u0010\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\f2\u001b\u0010~\u001a\u0017\u0012\u0006\b\u0000\u0012\u0002H\u00100j\u000b\u0012\u0006\b\u0000\u0012\u0002H\u0010`\u0001H@ø\u0001\u0000¢\u0006\u0003\u0010\u0001\u001aH\u0010\u0001\u001a\u0004\u0018\u0001H\u0010\"\u0004\b\u0000\u0010\u0010\"\u000e\b\u0001\u0010'*\b\u0012\u0004\u0012\u0002H'0|*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0012\u00109\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H'0\u0003HHø\u0001\u0000¢\u0006\u0002\u0010\u0012\u001aB\u0010\u0001\u001a\u0004\u0018\u0001H\u0010\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\f2\u001b\u0010~\u001a\u0017\u0012\u0006\b\u0000\u0012\u0002H\u00100j\u000b\u0012\u0006\b\u0000\u0012\u0002H\u0010`\u0001H@ø\u0001\u0000¢\u0006\u0003\u0010\u0001\u001a\"\u0010\u0001\u001a\u00020\u000f\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\fH@ø\u0001\u0000¢\u0006\u0002\u0010\u0014\u001a6\u0010\u0001\u001a\u00020\u000f\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u00020\u000f0\u0003HHø\u0001\u0000¢\u0006\u0002\u0010\u0012\u001aN\u0010\u0001\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00100h\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00100h0\u001a\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u00020\u000f0\u0003HHø\u0001\u0000¢\u0006\u0002\u0010\u0012\u001a[\u0010\u0001\u001a\u0003H\u0001\"\u0005\b\u0000\u0010\u0001\"\t\b\u0001\u0010\u0010*\u0003H\u0001*\b\u0012\u0004\u0012\u0002H\u00100\f2)\u0010b\u001a%\u0012\u0014\u0012\u0012H\u0001¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(c\u0012\u0004\u0012\u0002H\u0010\u0012\u0005\u0012\u0003H\u00010:HHø\u0001\u0000¢\u0006\u0003\u0010\u0001\u001ap\u0010\u0001\u001a\u0003H\u0001\"\u0005\b\u0000\u0010\u0001\"\t\b\u0001\u0010\u0010*\u0003H\u0001*\b\u0012\u0004\u0012\u0002H\u00100\f2>\u0010b\u001a:\u0012\u0013\u0012\u001104¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(B\u0012\u0014\u0012\u0012H\u0001¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(c\u0012\u0004\u0012\u0002H\u0010\u0012\u0005\u0012\u0003H\u00010JHHø\u0001\u0000¢\u0006\u0003\u0010\u0001\u001a%\u0010\u0001\u001a\b\u0012\u0004\u0012\u0002H\u00100\f\"\b\b\u0000\u0010\u0010*\u00020<*\n\u0012\u0006\u0012\u0004\u0018\u0001H\u00100\fH\u0007\u001a\"\u0010\u0001\u001a\u0002H\u0010\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\fH@ø\u0001\u0000¢\u0006\u0002\u0010\u0014\u001a6\u0010\u0001\u001a\u0002H\u0010\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u00020\u000f0\u0003HHø\u0001\u0000¢\u0006\u0002\u0010\u0012\u001a$\u0010\u0001\u001a\u0004\u0018\u0001H\u0010\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\fH@ø\u0001\u0000¢\u0006\u0002\u0010\u0014\u001a8\u0010\u0001\u001a\u0004\u0018\u0001H\u0010\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u00020\u000f0\u0003HHø\u0001\u0000¢\u0006\u0002\u0010\u0012\u001a6\u0010\u0001\u001a\u000204\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0012\u00109\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002040\u0003HHø\u0001\u0000¢\u0006\u0002\u0010\u0012\u001a8\u0010\u0001\u001a\u00030\u0001\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0013\u00109\u001a\u000f\u0012\u0004\u0012\u0002H\u0010\u0012\u0005\u0012\u00030\u00010\u0003HHø\u0001\u0000¢\u0006\u0002\u0010\u0012\u001a1\u0010\u0001\u001a\b\u0012\u0004\u0012\u0002H\u00100\f\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0006\u0010?\u001a\u0002042\b\b\u0002\u00107\u001a\u000208H\u0007\u001aU\u0010\u0001\u001a\b\u0012\u0004\u0012\u0002H\u00100\f\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\f2\b\b\u0002\u00107\u001a\u0002082\"\u0010\u0011\u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0;\u0012\u0006\u0012\u0004\u0018\u00010<0:H\u0007ø\u0001\u0000¢\u0006\u0002\u0010=\u001a:\u0010\u0001\u001a\u0002HM\"\u0004\b\u0000\u0010\u0010\"\u000e\b\u0001\u0010M*\b\u0012\u0004\u0012\u0002H\u00100P*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0006\u0010\"\u001a\u0002HMH@ø\u0001\u0000¢\u0006\u0002\u0010V\u001a<\u0010\u0001\u001a\u0002HM\"\u0004\b\u0000\u0010\u0010\"\u0010\b\u0001\u0010M*\n\u0012\u0006\b\u0000\u0012\u0002H\u00100N*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0006\u0010\"\u001a\u0002HMH@ø\u0001\u0000¢\u0006\u0002\u0010U\u001a(\u0010\u0001\u001a\b\u0012\u0004\u0012\u0002H\u00100h\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\fH@ø\u0001\u0000¢\u0006\u0002\u0010\u0014\u001a@\u0010\u0001\u001a\u000e\u0012\u0004\u0012\u0002H\u0017\u0012\u0004\u0012\u0002H\u00180\u0016\"\u0004\b\u0000\u0010\u0017\"\u0004\b\u0001\u0010\u0018*\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0017\u0012\u0004\u0012\u0002H\u00180\u001a0\fH@ø\u0001\u0000¢\u0006\u0002\u0010\u0014\u001aW\u0010\u0001\u001a\u0002H \"\u0004\b\u0000\u0010\u0017\"\u0004\b\u0001\u0010\u0018\"\u0018\b\u0002\u0010 *\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0017\u0012\u0006\b\u0000\u0012\u0002H\u00180!*\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0017\u0012\u0004\u0012\u0002H\u00180\u001a0\f2\u0006\u0010\"\u001a\u0002H H@ø\u0001\u0000¢\u0006\u0003\u0010\u0001\u001a(\u0010\u0001\u001a\b\u0012\u0004\u0012\u0002H\u00100j\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\fH@ø\u0001\u0000¢\u0006\u0002\u0010\u0014\u001a)\u0010\u0001\u001a\t\u0012\u0004\u0012\u0002H\u00100\u0001\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\fH@ø\u0001\u0000¢\u0006\u0002\u0010\u0014\u001a)\u0010\u0001\u001a\t\u0012\u0004\u0012\u0002H\u00100\u0001\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\fH@ø\u0001\u0000¢\u0006\u0002\u0010\u0014\u001a/\u0010\u0001\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u0010010\f\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\f2\b\b\u0002\u00107\u001a\u000208H\u0007\u001aA\u0010\u0001\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H'0\u001a0\f\"\u0004\b\u0000\u0010\u0010\"\u0004\b\u0001\u0010'*\b\u0012\u0004\u0012\u0002H\u00100\f2\r\u0010\u0001\u001a\b\u0012\u0004\u0012\u0002H'0\fH\u0004\u001a~\u0010\u0001\u001a\b\u0012\u0004\u0012\u0002H\u00180\f\"\u0004\b\u0000\u0010\u0010\"\u0004\b\u0001\u0010'\"\u0004\b\u0002\u0010\u0018*\b\u0012\u0004\u0012\u0002H\u00100\f2\r\u0010\u0001\u001a\b\u0012\u0004\u0012\u0002H'0\f2\b\b\u0002\u00107\u001a\u00020828\u0010\u0019\u001a4\u0012\u0014\u0012\u0012H\u0010¢\u0006\r\b\u0005\u0012\t\b\u0006\u0012\u0005\b\b( \u0001\u0012\u0014\u0012\u0012H'¢\u0006\r\b\u0005\u0012\t\b\u0006\u0012\u0005\b\b(¡\u0001\u0012\u0004\u0012\u0002H\u00180:H\u0007\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006¢\u0001"}, d2 = {"DEFAULT_CLOSE_MESSAGE", "", "consumesAll", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "cause", "", "Lkotlinx/coroutines/CompletionHandler;", "channels", "", "Lkotlinx/coroutines/channels/ReceiveChannel;", "([Lkotlinx/coroutines/channels/ReceiveChannel;)Lkotlin/jvm/functions/Function1;", "all", "", "E", "predicate", "(Lkotlinx/coroutines/channels/ReceiveChannel;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "any", "(Lkotlinx/coroutines/channels/ReceiveChannel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "associate", "", "K", "V", "transform", "Lkotlin/Pair;", "associateBy", "keySelector", "valueTransform", "(Lkotlinx/coroutines/channels/ReceiveChannel;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "associateByTo", "M", "", "destination", "(Lkotlinx/coroutines/channels/ReceiveChannel;Ljava/util/Map;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "(Lkotlinx/coroutines/channels/ReceiveChannel;Ljava/util/Map;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "associateTo", "consume", "R", "Lkotlinx/coroutines/channels/BroadcastChannel;", "block", "Lkotlin/ExtensionFunctionType;", "(Lkotlinx/coroutines/channels/BroadcastChannel;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "(Lkotlinx/coroutines/channels/ReceiveChannel;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "consumeEach", "action", "(Lkotlinx/coroutines/channels/BroadcastChannel;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "consumeEachIndexed", "Lkotlin/collections/IndexedValue;", "consumes", "count", "", "distinct", "distinctBy", "context", "Lkotlin/coroutines/CoroutineContext;", "selector", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "", "(Lkotlinx/coroutines/channels/ReceiveChannel;Lkotlin/coroutines/CoroutineContext;Lkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/channels/ReceiveChannel;", "drop", "n", "dropWhile", "elementAt", "index", "(Lkotlinx/coroutines/channels/ReceiveChannel;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "elementAtOrElse", "defaultValue", "(Lkotlinx/coroutines/channels/ReceiveChannel;ILkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "elementAtOrNull", "filter", "filterIndexed", "Lkotlin/Function3;", "(Lkotlinx/coroutines/channels/ReceiveChannel;Lkotlin/coroutines/CoroutineContext;Lkotlin/jvm/functions/Function3;)Lkotlinx/coroutines/channels/ReceiveChannel;", "filterIndexedTo", "C", "", "(Lkotlinx/coroutines/channels/ReceiveChannel;Ljava/util/Collection;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lkotlinx/coroutines/channels/SendChannel;", "(Lkotlinx/coroutines/channels/ReceiveChannel;Lkotlinx/coroutines/channels/SendChannel;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "filterNot", "filterNotNull", "filterNotNullTo", "(Lkotlinx/coroutines/channels/ReceiveChannel;Ljava/util/Collection;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "(Lkotlinx/coroutines/channels/ReceiveChannel;Lkotlinx/coroutines/channels/SendChannel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "filterNotTo", "(Lkotlinx/coroutines/channels/ReceiveChannel;Ljava/util/Collection;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "(Lkotlinx/coroutines/channels/ReceiveChannel;Lkotlinx/coroutines/channels/SendChannel;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "filterTo", "find", "findLast", "first", "firstOrNull", "flatMap", "fold", "initial", "operation", "acc", "(Lkotlinx/coroutines/channels/ReceiveChannel;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "foldIndexed", "(Lkotlinx/coroutines/channels/ReceiveChannel;Ljava/lang/Object;Lkotlin/jvm/functions/Function3;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "groupBy", "", "groupByTo", "", "indexOf", "element", "(Lkotlinx/coroutines/channels/ReceiveChannel;Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "indexOfFirst", "indexOfLast", "last", "lastIndexOf", "lastOrNull", "map", "mapIndexed", "mapIndexedNotNull", "mapIndexedNotNullTo", "mapIndexedTo", "mapNotNull", "mapNotNullTo", "mapTo", "maxBy", "", "maxWith", "comparator", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "(Lkotlinx/coroutines/channels/ReceiveChannel;Ljava/util/Comparator;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "minBy", "minWith", "none", "partition", "reduce", "S", "(Lkotlinx/coroutines/channels/ReceiveChannel;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "reduceIndexed", "(Lkotlinx/coroutines/channels/ReceiveChannel;Lkotlin/jvm/functions/Function3;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "requireNoNulls", "single", "singleOrNull", "sumBy", "sumByDouble", "", "take", "takeWhile", "toChannel", "toCollection", "toList", "toMap", "(Lkotlinx/coroutines/channels/ReceiveChannel;Ljava/util/Map;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "toMutableList", "toMutableSet", "", "toSet", "", "withIndex", "zip", "other", "a", "b", "kotlinx-coroutines-core"}, k = 5, mv = {1, 1, 13}, xs = "kotlinx/coroutines/channels/ChannelsKt")
/* compiled from: Channels.common.kt */
final /* synthetic */ class ChannelsKt__Channels_commonKt {
    @ObsoleteCoroutinesApi
    public static final <E, R> R consume(@NotNull BroadcastChannel<E> broadcastChannel, @NotNull Function1<? super ReceiveChannel<? extends E>, ? extends R> function1) {
        Intrinsics.checkParameterIsNotNull(broadcastChannel, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "block");
        broadcastChannel = broadcastChannel.openSubscription();
        try {
            function1 = function1.invoke(broadcastChannel);
            return function1;
        } finally {
            InlineMarker.finallyStart(1);
            broadcastChannel.cancel();
            InlineMarker.finallyEnd(1);
        }
    }

    @org.jetbrains.annotations.Nullable
    @kotlinx.coroutines.ObsoleteCoroutinesApi
    public static final <E> java.lang.Object consumeEach(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.BroadcastChannel<E> r9, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super E, kotlin.Unit> r10, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r11) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:37)
	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:61)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1603198149.run(Unknown Source)
*/
        /*
        r0 = r11 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$consumeEach$1;
        if (r0 == 0) goto L_0x0014;
    L_0x0004:
        r0 = r11;
        r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$consumeEach$1) r0;
        r1 = r0.label;
        r2 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r1 = r1 & r2;
        if (r1 == 0) goto L_0x0014;
    L_0x000e:
        r11 = r0.label;
        r11 = r11 - r2;
        r0.label = r11;
        goto L_0x0019;
    L_0x0014:
        r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$consumeEach$1;
        r0.<init>(r11);
    L_0x0019:
        r11 = r0.result;
        r1 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        r2 = r0.label;
        r3 = 1;
        switch(r2) {
            case 0: goto L_0x007b;
            case 1: goto L_0x0054;
            case 2: goto L_0x002d;
            default: goto L_0x0025;
        };
    L_0x0025:
        r9 = new java.lang.IllegalStateException;
        r10 = "call to 'resume' before 'invoke' with coroutine";
        r9.<init>(r10);
        throw r9;
    L_0x002d:
        r9 = r0.L$5;
        r9 = (kotlinx.coroutines.channels.ChannelIterator) r9;
        r10 = r0.L$4;
        r10 = (kotlinx.coroutines.channels.ReceiveChannel) r10;
        r2 = r0.L$3;
        r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2;
        r4 = r0.L$2;
        r4 = (kotlinx.coroutines.channels.BroadcastChannel) r4;
        r5 = r0.L$1;
        r5 = (kotlin.jvm.functions.Function1) r5;
        r6 = r0.L$0;
        r6 = (kotlinx.coroutines.channels.BroadcastChannel) r6;
        r7 = r11 instanceof kotlin.Result.Failure;	 Catch:{ all -> 0x0079 }
        if (r7 != 0) goto L_0x004f;	 Catch:{ all -> 0x0079 }
    L_0x0049:
        r8 = r11;	 Catch:{ all -> 0x0079 }
        r11 = r9;	 Catch:{ all -> 0x0079 }
        r9 = r10;	 Catch:{ all -> 0x0079 }
        r10 = r8;	 Catch:{ all -> 0x0079 }
        goto L_0x00c4;	 Catch:{ all -> 0x0079 }
    L_0x004f:
        r11 = (kotlin.Result.Failure) r11;	 Catch:{ all -> 0x0079 }
        r9 = r11.exception;	 Catch:{ all -> 0x0079 }
        throw r9;	 Catch:{ all -> 0x0079 }
    L_0x0054:
        r9 = r0.L$5;
        r9 = (kotlinx.coroutines.channels.ChannelIterator) r9;
        r10 = r0.L$4;
        r10 = (kotlinx.coroutines.channels.ReceiveChannel) r10;
        r2 = r0.L$3;
        r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2;
        r4 = r0.L$2;
        r4 = (kotlinx.coroutines.channels.BroadcastChannel) r4;
        r5 = r0.L$1;
        r5 = (kotlin.jvm.functions.Function1) r5;
        r6 = r0.L$0;
        r6 = (kotlinx.coroutines.channels.BroadcastChannel) r6;
        r7 = r11 instanceof kotlin.Result.Failure;	 Catch:{ all -> 0x0079 }
        if (r7 != 0) goto L_0x0074;	 Catch:{ all -> 0x0079 }
    L_0x0070:
        r8 = r11;	 Catch:{ all -> 0x0079 }
        r11 = r10;	 Catch:{ all -> 0x0079 }
        r10 = r8;	 Catch:{ all -> 0x0079 }
        goto L_0x00a3;	 Catch:{ all -> 0x0079 }
    L_0x0074:
        r11 = (kotlin.Result.Failure) r11;	 Catch:{ all -> 0x0079 }
        r9 = r11.exception;	 Catch:{ all -> 0x0079 }
        throw r9;	 Catch:{ all -> 0x0079 }
    L_0x0079:
        r9 = move-exception;
        goto L_0x00d4;
    L_0x007b:
        r2 = r11 instanceof kotlin.Result.Failure;
        if (r2 != 0) goto L_0x00de;
    L_0x007f:
        r2 = r9.openSubscription();
        r11 = r2.iterator();	 Catch:{ all -> 0x0079 }
        r4 = r9;	 Catch:{ all -> 0x0079 }
        r6 = r4;	 Catch:{ all -> 0x0079 }
        r5 = r10;	 Catch:{ all -> 0x0079 }
        r9 = r2;	 Catch:{ all -> 0x0079 }
    L_0x008b:
        r0.L$0 = r6;	 Catch:{ all -> 0x0079 }
        r0.L$1 = r5;	 Catch:{ all -> 0x0079 }
        r0.L$2 = r4;	 Catch:{ all -> 0x0079 }
        r0.L$3 = r2;	 Catch:{ all -> 0x0079 }
        r0.L$4 = r9;	 Catch:{ all -> 0x0079 }
        r0.L$5 = r11;	 Catch:{ all -> 0x0079 }
        r0.label = r3;	 Catch:{ all -> 0x0079 }
        r10 = r11.hasNext(r0);	 Catch:{ all -> 0x0079 }
        if (r10 != r1) goto L_0x00a0;	 Catch:{ all -> 0x0079 }
    L_0x009f:
        return r1;	 Catch:{ all -> 0x0079 }
    L_0x00a0:
        r8 = r11;	 Catch:{ all -> 0x0079 }
        r11 = r9;	 Catch:{ all -> 0x0079 }
        r9 = r8;	 Catch:{ all -> 0x0079 }
    L_0x00a3:
        r10 = (java.lang.Boolean) r10;	 Catch:{ all -> 0x0079 }
        r10 = r10.booleanValue();	 Catch:{ all -> 0x0079 }
        if (r10 == 0) goto L_0x00c8;	 Catch:{ all -> 0x0079 }
    L_0x00ab:
        r0.L$0 = r6;	 Catch:{ all -> 0x0079 }
        r0.L$1 = r5;	 Catch:{ all -> 0x0079 }
        r0.L$2 = r4;	 Catch:{ all -> 0x0079 }
        r0.L$3 = r2;	 Catch:{ all -> 0x0079 }
        r0.L$4 = r11;	 Catch:{ all -> 0x0079 }
        r0.L$5 = r9;	 Catch:{ all -> 0x0079 }
        r10 = 2;	 Catch:{ all -> 0x0079 }
        r0.label = r10;	 Catch:{ all -> 0x0079 }
        r10 = r9.next(r0);	 Catch:{ all -> 0x0079 }
        if (r10 != r1) goto L_0x00c1;	 Catch:{ all -> 0x0079 }
    L_0x00c0:
        return r1;	 Catch:{ all -> 0x0079 }
    L_0x00c1:
        r8 = r11;	 Catch:{ all -> 0x0079 }
        r11 = r9;	 Catch:{ all -> 0x0079 }
        r9 = r8;	 Catch:{ all -> 0x0079 }
    L_0x00c4:
        r5.invoke(r10);	 Catch:{ all -> 0x0079 }
        goto L_0x008b;	 Catch:{ all -> 0x0079 }
    L_0x00c8:
        r9 = kotlin.Unit.INSTANCE;	 Catch:{ all -> 0x0079 }
        kotlin.jvm.internal.InlineMarker.finallyStart(r3);
        r2.cancel();
        kotlin.jvm.internal.InlineMarker.finallyEnd(r3);
        return r9;
    L_0x00d4:
        kotlin.jvm.internal.InlineMarker.finallyStart(r3);
        r2.cancel();
        kotlin.jvm.internal.InlineMarker.finallyEnd(r3);
        throw r9;
    L_0x00de:
        r11 = (kotlin.Result.Failure) r11;
        r9 = r11.exception;
        throw r9;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.consumeEach(kotlinx.coroutines.channels.BroadcastChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @NotNull
    @ObsoleteCoroutinesApi
    public static final Function1<Throwable, Unit> consumes(@NotNull ReceiveChannel<?> receiveChannel) {
        Intrinsics.checkParameterIsNotNull(receiveChannel, "receiver$0");
        return new ChannelsKt__Channels_commonKt$consumes$1(receiveChannel);
    }

    @NotNull
    @ObsoleteCoroutinesApi
    public static final Function1<Throwable, Unit> consumesAll(@NotNull ReceiveChannel<?>... receiveChannelArr) {
        Intrinsics.checkParameterIsNotNull(receiveChannelArr, "channels");
        return new ChannelsKt__Channels_commonKt$consumesAll$1(receiveChannelArr);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @kotlinx.coroutines.ObsoleteCoroutinesApi
    public static final <E, R> R consume(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r2, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super kotlinx.coroutines.channels.ReceiveChannel<? extends E>, ? extends R> r3) {
        /*
        r0 = "receiver$0";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r2, r0);
        r0 = "block";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r3, r0);
        r0 = 0;
        r0 = (java.lang.Throwable) r0;
        r1 = 1;
        r3 = r3.invoke(r2);	 Catch:{ Throwable -> 0x001e }
        kotlin.jvm.internal.InlineMarker.finallyStart(r1);
        r2.cancel(r0);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r1);
        return r3;
    L_0x001c:
        r3 = move-exception;
        goto L_0x0020;
    L_0x001e:
        r0 = move-exception;
        throw r0;	 Catch:{ all -> 0x001c }
    L_0x0020:
        kotlin.jvm.internal.InlineMarker.finallyStart(r1);
        r2.cancel(r0);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r1);
        throw r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.consume(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function1):R");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @org.jetbrains.annotations.Nullable
    @kotlinx.coroutines.ObsoleteCoroutinesApi
    public static final <E> java.lang.Object consumeEach(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r9, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super E, kotlin.Unit> r10, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r11) {
        /*
        r0 = r11 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$consumeEach$3;
        if (r0 == 0) goto L_0x0014;
    L_0x0004:
        r0 = r11;
        r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$consumeEach$3) r0;
        r1 = r0.label;
        r2 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r1 = r1 & r2;
        if (r1 == 0) goto L_0x0014;
    L_0x000e:
        r11 = r0.label;
        r11 = r11 - r2;
        r0.label = r11;
        goto L_0x0019;
    L_0x0014:
        r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$consumeEach$3;
        r0.<init>(r11);
    L_0x0019:
        r11 = r0.result;
        r1 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        r2 = r0.label;
        r3 = 1;
        switch(r2) {
            case 0: goto L_0x007a;
            case 1: goto L_0x0050;
            case 2: goto L_0x002d;
            default: goto L_0x0025;
        };
    L_0x0025:
        r9 = new java.lang.IllegalStateException;
        r10 = "call to 'resume' before 'invoke' with coroutine";
        r9.<init>(r10);
        throw r9;
    L_0x002d:
        r9 = r0.L$5;
        r9 = (kotlinx.coroutines.channels.ChannelIterator) r9;
        r10 = r0.L$4;
        r10 = (kotlinx.coroutines.channels.ReceiveChannel) r10;
        r2 = r0.L$3;
        r2 = (java.lang.Throwable) r2;
        r4 = r0.L$2;
        r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4;
        r5 = r0.L$1;
        r5 = (kotlin.jvm.functions.Function1) r5;
        r6 = r0.L$0;
        r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6;
        r7 = r11 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x0075, all -> 0x0072 }
        if (r7 != 0) goto L_0x004b;
    L_0x0049:
        goto L_0x00c0;
    L_0x004b:
        r11 = (kotlin.Result.Failure) r11;	 Catch:{ Throwable -> 0x0075, all -> 0x0072 }
        r9 = r11.exception;	 Catch:{ Throwable -> 0x0075, all -> 0x0072 }
        throw r9;	 Catch:{ Throwable -> 0x0075, all -> 0x0072 }
    L_0x0050:
        r9 = r0.L$5;
        r9 = (kotlinx.coroutines.channels.ChannelIterator) r9;
        r10 = r0.L$4;
        r10 = (kotlinx.coroutines.channels.ReceiveChannel) r10;
        r2 = r0.L$3;
        r2 = (java.lang.Throwable) r2;
        r4 = r0.L$2;
        r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4;
        r5 = r0.L$1;
        r5 = (kotlin.jvm.functions.Function1) r5;
        r6 = r0.L$0;
        r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6;
        r7 = r11 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x0075, all -> 0x0072 }
        if (r7 != 0) goto L_0x006d;
    L_0x006c:
        goto L_0x00a2;
    L_0x006d:
        r11 = (kotlin.Result.Failure) r11;	 Catch:{ Throwable -> 0x0075, all -> 0x0072 }
        r9 = r11.exception;	 Catch:{ Throwable -> 0x0075, all -> 0x0072 }
        throw r9;	 Catch:{ Throwable -> 0x0075, all -> 0x0072 }
    L_0x0072:
        r9 = move-exception;
        goto L_0x00d9;
    L_0x0075:
        r9 = move-exception;
        r2 = r9;
        r9 = r4;
        goto L_0x00d8;
    L_0x007a:
        r2 = r11 instanceof kotlin.Result.Failure;
        if (r2 != 0) goto L_0x00e3;
    L_0x007e:
        r11 = 0;
        r2 = r11;
        r2 = (java.lang.Throwable) r2;
        r11 = r9.iterator();	 Catch:{ Throwable -> 0x00d6 }
        r6 = r9;
        r5 = r10;
        r10 = r6;
    L_0x0089:
        r0.L$0 = r6;	 Catch:{ Throwable -> 0x00d6 }
        r0.L$1 = r5;	 Catch:{ Throwable -> 0x00d6 }
        r0.L$2 = r9;	 Catch:{ Throwable -> 0x00d6 }
        r0.L$3 = r2;	 Catch:{ Throwable -> 0x00d6 }
        r0.L$4 = r10;	 Catch:{ Throwable -> 0x00d6 }
        r0.L$5 = r11;	 Catch:{ Throwable -> 0x00d6 }
        r0.label = r3;	 Catch:{ Throwable -> 0x00d6 }
        r4 = r11.hasNext(r0);	 Catch:{ Throwable -> 0x00d6 }
        if (r4 != r1) goto L_0x009e;
    L_0x009d:
        return r1;
    L_0x009e:
        r8 = r4;
        r4 = r9;
        r9 = r11;
        r11 = r8;
    L_0x00a2:
        r11 = (java.lang.Boolean) r11;	 Catch:{ Throwable -> 0x0075, all -> 0x0072 }
        r11 = r11.booleanValue();	 Catch:{ Throwable -> 0x0075, all -> 0x0072 }
        if (r11 == 0) goto L_0x00c6;
    L_0x00aa:
        r0.L$0 = r6;	 Catch:{ Throwable -> 0x0075, all -> 0x0072 }
        r0.L$1 = r5;	 Catch:{ Throwable -> 0x0075, all -> 0x0072 }
        r0.L$2 = r4;	 Catch:{ Throwable -> 0x0075, all -> 0x0072 }
        r0.L$3 = r2;	 Catch:{ Throwable -> 0x0075, all -> 0x0072 }
        r0.L$4 = r10;	 Catch:{ Throwable -> 0x0075, all -> 0x0072 }
        r0.L$5 = r9;	 Catch:{ Throwable -> 0x0075, all -> 0x0072 }
        r11 = 2;
        r0.label = r11;	 Catch:{ Throwable -> 0x0075, all -> 0x0072 }
        r11 = r9.next(r0);	 Catch:{ Throwable -> 0x0075, all -> 0x0072 }
        if (r11 != r1) goto L_0x00c0;
    L_0x00bf:
        return r1;
    L_0x00c0:
        r5.invoke(r11);	 Catch:{ Throwable -> 0x0075, all -> 0x0072 }
        r11 = r9;
        r9 = r4;
        goto L_0x0089;
    L_0x00c6:
        r9 = kotlin.Unit.INSTANCE;	 Catch:{ Throwable -> 0x0075, all -> 0x0072 }
        kotlin.jvm.internal.InlineMarker.finallyStart(r3);
        r4.cancel(r2);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r3);
        return r9;
    L_0x00d2:
        r10 = move-exception;
        r4 = r9;
        r9 = r10;
        goto L_0x00d9;
    L_0x00d6:
        r10 = move-exception;
        r2 = r10;
    L_0x00d8:
        throw r2;	 Catch:{ all -> 0x00d2 }
    L_0x00d9:
        kotlin.jvm.internal.InlineMarker.finallyStart(r3);
        r4.cancel(r2);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r3);
        throw r9;
    L_0x00e3:
        r11 = (kotlin.Result.Failure) r11;
        r9 = r11.exception;
        throw r9;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.consumeEach(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @org.jetbrains.annotations.Nullable
    @kotlinx.coroutines.ObsoleteCoroutinesApi
    public static final <E> java.lang.Object consumeEachIndexed(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r16, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super kotlin.collections.IndexedValue<? extends E>, kotlin.Unit> r17, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r18) {
        /*
        r0 = r18;
        r1 = r0 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$consumeEachIndexed$1;
        if (r1 == 0) goto L_0x0016;
    L_0x0006:
        r1 = r0;
        r1 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$consumeEachIndexed$1) r1;
        r2 = r1.label;
        r3 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r2 = r2 & r3;
        if (r2 == 0) goto L_0x0016;
    L_0x0010:
        r0 = r1.label;
        r0 = r0 - r3;
        r1.label = r0;
        goto L_0x001b;
    L_0x0016:
        r1 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$consumeEachIndexed$1;
        r1.<init>(r0);
    L_0x001b:
        r0 = r1.result;
        r2 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        r3 = r1.label;
        r4 = 1;
        switch(r3) {
            case 0: goto L_0x0093;
            case 1: goto L_0x005d;
            case 2: goto L_0x002f;
            default: goto L_0x0027;
        };
    L_0x0027:
        r0 = new java.lang.IllegalStateException;
        r1 = "call to 'resume' before 'invoke' with coroutine";
        r0.<init>(r1);
        throw r0;
    L_0x002f:
        r3 = r1.L$7;
        r3 = (kotlinx.coroutines.channels.ChannelIterator) r3;
        r5 = r1.L$6;
        r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5;
        r6 = r1.L$5;
        r6 = (java.lang.Throwable) r6;
        r7 = r1.L$4;
        r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7;
        r8 = r1.L$3;
        r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8;
        r9 = r1.L$2;
        r9 = (kotlin.jvm.internal.Ref.IntRef) r9;
        r10 = r1.L$1;
        r10 = (kotlin.jvm.functions.Function1) r10;
        r11 = r1.L$0;
        r11 = (kotlinx.coroutines.channels.ReceiveChannel) r11;
        r12 = r0 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x008e, all -> 0x008a }
        if (r12 != 0) goto L_0x0058;
    L_0x0053:
        r15 = r7;
        r7 = r2;
        r2 = r15;
        goto L_0x00ed;
    L_0x0058:
        r0 = (kotlin.Result.Failure) r0;	 Catch:{ Throwable -> 0x008e, all -> 0x008a }
        r0 = r0.exception;	 Catch:{ Throwable -> 0x008e, all -> 0x008a }
        throw r0;	 Catch:{ Throwable -> 0x008e, all -> 0x008a }
    L_0x005d:
        r3 = r1.L$7;
        r3 = (kotlinx.coroutines.channels.ChannelIterator) r3;
        r5 = r1.L$6;
        r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5;
        r6 = r1.L$5;
        r6 = (java.lang.Throwable) r6;
        r7 = r1.L$4;
        r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7;
        r8 = r1.L$3;
        r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8;
        r9 = r1.L$2;
        r9 = (kotlin.jvm.internal.Ref.IntRef) r9;
        r10 = r1.L$1;
        r10 = (kotlin.jvm.functions.Function1) r10;
        r11 = r1.L$0;
        r11 = (kotlinx.coroutines.channels.ReceiveChannel) r11;
        r12 = r0 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x008e, all -> 0x008a }
        if (r12 != 0) goto L_0x0085;
    L_0x0081:
        r15 = r7;
        r7 = r2;
        r2 = r15;
        goto L_0x00cb;
    L_0x0085:
        r0 = (kotlin.Result.Failure) r0;	 Catch:{ Throwable -> 0x008e, all -> 0x008a }
        r0 = r0.exception;	 Catch:{ Throwable -> 0x008e, all -> 0x008a }
        throw r0;	 Catch:{ Throwable -> 0x008e, all -> 0x008a }
    L_0x008a:
        r0 = move-exception;
        r2 = r7;
        goto L_0x0117;
    L_0x008e:
        r0 = move-exception;
        r6 = r0;
        r2 = r7;
        goto L_0x0115;
    L_0x0093:
        r3 = r0 instanceof kotlin.Result.Failure;
        if (r3 != 0) goto L_0x0121;
    L_0x0097:
        r0 = new kotlin.jvm.internal.Ref$IntRef;
        r0.<init>();
        r3 = 0;
        r0.element = r3;
        r3 = 0;
        r6 = r3;
        r6 = (java.lang.Throwable) r6;
        r3 = r16.iterator();	 Catch:{ Throwable -> 0x0111, all -> 0x010d }
        r5 = r16;
        r8 = r5;
        r11 = r8;
        r10 = r17;
        r7 = r2;
        r2 = r11;
    L_0x00af:
        r1.L$0 = r11;	 Catch:{ Throwable -> 0x010b }
        r1.L$1 = r10;	 Catch:{ Throwable -> 0x010b }
        r1.L$2 = r0;	 Catch:{ Throwable -> 0x010b }
        r1.L$3 = r8;	 Catch:{ Throwable -> 0x010b }
        r1.L$4 = r2;	 Catch:{ Throwable -> 0x010b }
        r1.L$5 = r6;	 Catch:{ Throwable -> 0x010b }
        r1.L$6 = r5;	 Catch:{ Throwable -> 0x010b }
        r1.L$7 = r3;	 Catch:{ Throwable -> 0x010b }
        r1.label = r4;	 Catch:{ Throwable -> 0x010b }
        r9 = r3.hasNext(r1);	 Catch:{ Throwable -> 0x010b }
        if (r9 != r7) goto L_0x00c8;
    L_0x00c7:
        return r7;
    L_0x00c8:
        r15 = r9;
        r9 = r0;
        r0 = r15;
    L_0x00cb:
        r0 = (java.lang.Boolean) r0;	 Catch:{ Throwable -> 0x010b }
        r0 = r0.booleanValue();	 Catch:{ Throwable -> 0x010b }
        if (r0 == 0) goto L_0x00fd;
    L_0x00d3:
        r1.L$0 = r11;	 Catch:{ Throwable -> 0x010b }
        r1.L$1 = r10;	 Catch:{ Throwable -> 0x010b }
        r1.L$2 = r9;	 Catch:{ Throwable -> 0x010b }
        r1.L$3 = r8;	 Catch:{ Throwable -> 0x010b }
        r1.L$4 = r2;	 Catch:{ Throwable -> 0x010b }
        r1.L$5 = r6;	 Catch:{ Throwable -> 0x010b }
        r1.L$6 = r5;	 Catch:{ Throwable -> 0x010b }
        r1.L$7 = r3;	 Catch:{ Throwable -> 0x010b }
        r0 = 2;
        r1.label = r0;	 Catch:{ Throwable -> 0x010b }
        r0 = r3.next(r1);	 Catch:{ Throwable -> 0x010b }
        if (r0 != r7) goto L_0x00ed;
    L_0x00ec:
        return r7;
    L_0x00ed:
        r12 = new kotlin.collections.IndexedValue;	 Catch:{ Throwable -> 0x010b }
        r13 = r9.element;	 Catch:{ Throwable -> 0x010b }
        r14 = r13 + 1;
        r9.element = r14;	 Catch:{ Throwable -> 0x010b }
        r12.<init>(r13, r0);	 Catch:{ Throwable -> 0x010b }
        r10.invoke(r12);	 Catch:{ Throwable -> 0x010b }
        r0 = r9;
        goto L_0x00af;
    L_0x00fd:
        r0 = kotlin.Unit.INSTANCE;	 Catch:{ Throwable -> 0x010b }
        kotlin.jvm.internal.InlineMarker.finallyStart(r4);
        r2.cancel(r6);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r4);
        r0 = kotlin.Unit.INSTANCE;
        return r0;
    L_0x010b:
        r0 = move-exception;
        goto L_0x0114;
    L_0x010d:
        r0 = move-exception;
        r2 = r16;
        goto L_0x0117;
    L_0x0111:
        r0 = move-exception;
        r2 = r16;
    L_0x0114:
        r6 = r0;
    L_0x0115:
        throw r6;	 Catch:{ all -> 0x0116 }
    L_0x0116:
        r0 = move-exception;
    L_0x0117:
        kotlin.jvm.internal.InlineMarker.finallyStart(r4);
        r2.cancel(r6);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r4);
        throw r0;
    L_0x0121:
        r0 = (kotlin.Result.Failure) r0;
        r0 = r0.exception;
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.consumeEachIndexed(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @org.jetbrains.annotations.Nullable
    @kotlinx.coroutines.ObsoleteCoroutinesApi
    public static final <E> java.lang.Object elementAt(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r12, int r13, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super E> r14) {
        /*
        r0 = r14 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$elementAt$1;
        if (r0 == 0) goto L_0x0014;
    L_0x0004:
        r0 = r14;
        r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$elementAt$1) r0;
        r1 = r0.label;
        r2 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r1 = r1 & r2;
        if (r1 == 0) goto L_0x0014;
    L_0x000e:
        r14 = r0.label;
        r14 = r14 - r2;
        r0.label = r14;
        goto L_0x0019;
    L_0x0014:
        r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$elementAt$1;
        r0.<init>(r14);
    L_0x0019:
        r14 = r0.result;
        r1 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        r2 = r0.label;
        r3 = 46;
        switch(r2) {
            case 0: goto L_0x008c;
            case 1: goto L_0x005a;
            case 2: goto L_0x002e;
            default: goto L_0x0026;
        };
    L_0x0026:
        r12 = new java.lang.IllegalStateException;
        r13 = "call to 'resume' before 'invoke' with coroutine";
        r12.<init>(r13);
        throw r12;
    L_0x002e:
        r12 = r0.L$5;
        r12 = (kotlinx.coroutines.channels.ChannelIterator) r12;
        r13 = r0.I$1;
        r2 = r0.L$4;
        r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2;
        r4 = r0.L$3;
        r4 = (java.lang.Throwable) r4;
        r5 = r0.L$2;
        r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5;
        r6 = r0.L$1;
        r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6;
        r7 = r0.I$0;
        r8 = r0.L$0;
        r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8;
        r9 = r14 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x0087, all -> 0x0084 }
        if (r9 != 0) goto L_0x0055;
    L_0x004e:
        r10 = r2;
        r2 = r12;
        r12 = r5;
        r5 = r1;
        r1 = r10;
        goto L_0x00e8;
    L_0x0055:
        r14 = (kotlin.Result.Failure) r14;	 Catch:{ Throwable -> 0x0087, all -> 0x0084 }
        r12 = r14.exception;	 Catch:{ Throwable -> 0x0087, all -> 0x0084 }
        throw r12;	 Catch:{ Throwable -> 0x0087, all -> 0x0084 }
    L_0x005a:
        r12 = r0.L$5;
        r12 = (kotlinx.coroutines.channels.ChannelIterator) r12;
        r13 = r0.I$1;
        r2 = r0.L$4;
        r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2;
        r4 = r0.L$3;
        r4 = (java.lang.Throwable) r4;
        r5 = r0.L$2;
        r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5;
        r6 = r0.L$1;
        r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6;
        r7 = r0.I$0;
        r8 = r0.L$0;
        r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8;
        r9 = r14 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x0087, all -> 0x0084 }
        if (r9 != 0) goto L_0x007f;
    L_0x007a:
        r10 = r1;
        r1 = r13;
        r13 = r2;
        r2 = r10;
        goto L_0x00bf;
    L_0x007f:
        r14 = (kotlin.Result.Failure) r14;	 Catch:{ Throwable -> 0x0087, all -> 0x0084 }
        r12 = r14.exception;	 Catch:{ Throwable -> 0x0087, all -> 0x0084 }
        throw r12;	 Catch:{ Throwable -> 0x0087, all -> 0x0084 }
    L_0x0084:
        r12 = move-exception;
        goto L_0x0145;
    L_0x0087:
        r12 = move-exception;
        r4 = r12;
        r12 = r5;
        goto L_0x0144;
    L_0x008c:
        r2 = r14 instanceof kotlin.Result.Failure;
        if (r2 != 0) goto L_0x0149;
    L_0x0090:
        r14 = 0;
        r4 = r14;
        r4 = (java.lang.Throwable) r4;
        if (r13 < 0) goto L_0x0119;
    L_0x0096:
        r14 = 0;
        r2 = r12.iterator();	 Catch:{ Throwable -> 0x0142 }
        r6 = r12;
        r8 = r6;
        r7 = r13;
        r13 = r8;
    L_0x009f:
        r0.L$0 = r8;	 Catch:{ Throwable -> 0x0142 }
        r0.I$0 = r7;	 Catch:{ Throwable -> 0x0142 }
        r0.L$1 = r6;	 Catch:{ Throwable -> 0x0142 }
        r0.L$2 = r12;	 Catch:{ Throwable -> 0x0142 }
        r0.L$3 = r4;	 Catch:{ Throwable -> 0x0142 }
        r0.L$4 = r13;	 Catch:{ Throwable -> 0x0142 }
        r0.I$1 = r14;	 Catch:{ Throwable -> 0x0142 }
        r0.L$5 = r2;	 Catch:{ Throwable -> 0x0142 }
        r5 = 1;
        r0.label = r5;	 Catch:{ Throwable -> 0x0142 }
        r5 = r2.hasNext(r0);	 Catch:{ Throwable -> 0x0142 }
        if (r5 != r1) goto L_0x00b9;
    L_0x00b8:
        return r1;
    L_0x00b9:
        r10 = r5;
        r5 = r12;
        r12 = r2;
        r2 = r1;
        r1 = r14;
        r14 = r10;
    L_0x00bf:
        r14 = (java.lang.Boolean) r14;	 Catch:{ Throwable -> 0x0087, all -> 0x0084 }
        r14 = r14.booleanValue();	 Catch:{ Throwable -> 0x0087, all -> 0x0084 }
        if (r14 == 0) goto L_0x00f4;
    L_0x00c7:
        r0.L$0 = r8;	 Catch:{ Throwable -> 0x0087, all -> 0x0084 }
        r0.I$0 = r7;	 Catch:{ Throwable -> 0x0087, all -> 0x0084 }
        r0.L$1 = r6;	 Catch:{ Throwable -> 0x0087, all -> 0x0084 }
        r0.L$2 = r5;	 Catch:{ Throwable -> 0x0087, all -> 0x0084 }
        r0.L$3 = r4;	 Catch:{ Throwable -> 0x0087, all -> 0x0084 }
        r0.L$4 = r13;	 Catch:{ Throwable -> 0x0087, all -> 0x0084 }
        r0.I$1 = r1;	 Catch:{ Throwable -> 0x0087, all -> 0x0084 }
        r0.L$5 = r12;	 Catch:{ Throwable -> 0x0087, all -> 0x0084 }
        r14 = 2;
        r0.label = r14;	 Catch:{ Throwable -> 0x0087, all -> 0x0084 }
        r14 = r12.next(r0);	 Catch:{ Throwable -> 0x0087, all -> 0x0084 }
        if (r14 != r2) goto L_0x00e1;
    L_0x00e0:
        return r2;
    L_0x00e1:
        r10 = r2;
        r2 = r12;
        r12 = r5;
        r5 = r10;
        r11 = r1;
        r1 = r13;
        r13 = r11;
    L_0x00e8:
        r9 = r13 + 1;
        if (r7 != r13) goto L_0x00f0;
    L_0x00ec:
        r12.cancel(r4);
        return r14;
    L_0x00f0:
        r13 = r1;
        r1 = r5;
        r14 = r9;
        goto L_0x009f;
    L_0x00f4:
        r12 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r7);	 Catch:{ Throwable -> 0x0087, all -> 0x0084 }
        r12 = (java.lang.Number) r12;	 Catch:{ Throwable -> 0x0087, all -> 0x0084 }
        r12.intValue();	 Catch:{ Throwable -> 0x0087, all -> 0x0084 }
        r12 = new java.lang.IndexOutOfBoundsException;	 Catch:{ Throwable -> 0x0087, all -> 0x0084 }
        r13 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0087, all -> 0x0084 }
        r13.<init>();	 Catch:{ Throwable -> 0x0087, all -> 0x0084 }
        r14 = "ReceiveChannel doesn't contain element at index ";
        r13.append(r14);	 Catch:{ Throwable -> 0x0087, all -> 0x0084 }
        r13.append(r7);	 Catch:{ Throwable -> 0x0087, all -> 0x0084 }
        r13.append(r3);	 Catch:{ Throwable -> 0x0087, all -> 0x0084 }
        r13 = r13.toString();	 Catch:{ Throwable -> 0x0087, all -> 0x0084 }
        r12.<init>(r13);	 Catch:{ Throwable -> 0x0087, all -> 0x0084 }
        r12 = (java.lang.Throwable) r12;	 Catch:{ Throwable -> 0x0087, all -> 0x0084 }
        throw r12;	 Catch:{ Throwable -> 0x0087, all -> 0x0084 }
    L_0x0119:
        r14 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r13);	 Catch:{ Throwable -> 0x0142 }
        r14 = (java.lang.Number) r14;	 Catch:{ Throwable -> 0x0142 }
        r14.intValue();	 Catch:{ Throwable -> 0x0142 }
        r14 = new java.lang.IndexOutOfBoundsException;	 Catch:{ Throwable -> 0x0142 }
        r0 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0142 }
        r0.<init>();	 Catch:{ Throwable -> 0x0142 }
        r1 = "ReceiveChannel doesn't contain element at index ";
        r0.append(r1);	 Catch:{ Throwable -> 0x0142 }
        r0.append(r13);	 Catch:{ Throwable -> 0x0142 }
        r0.append(r3);	 Catch:{ Throwable -> 0x0142 }
        r13 = r0.toString();	 Catch:{ Throwable -> 0x0142 }
        r14.<init>(r13);	 Catch:{ Throwable -> 0x0142 }
        r14 = (java.lang.Throwable) r14;	 Catch:{ Throwable -> 0x0142 }
        throw r14;	 Catch:{ Throwable -> 0x0142 }
    L_0x013e:
        r13 = move-exception;
        r5 = r12;
        r12 = r13;
        goto L_0x0145;
    L_0x0142:
        r13 = move-exception;
        r4 = r13;
    L_0x0144:
        throw r4;	 Catch:{ all -> 0x013e }
    L_0x0145:
        r5.cancel(r4);
        throw r12;
    L_0x0149:
        r14 = (kotlin.Result.Failure) r14;
        r12 = r14.exception;
        throw r12;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.elementAt(kotlinx.coroutines.channels.ReceiveChannel, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @org.jetbrains.annotations.Nullable
    @kotlinx.coroutines.ObsoleteCoroutinesApi
    public static final <E> java.lang.Object elementAtOrElse(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r11, int r12, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super java.lang.Integer, ? extends E> r13, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super E> r14) {
        /*
        r0 = r14 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$elementAtOrElse$1;
        if (r0 == 0) goto L_0x0014;
    L_0x0004:
        r0 = r14;
        r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$elementAtOrElse$1) r0;
        r1 = r0.label;
        r2 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r1 = r1 & r2;
        if (r1 == 0) goto L_0x0014;
    L_0x000e:
        r14 = r0.label;
        r14 = r14 - r2;
        r0.label = r14;
        goto L_0x0019;
    L_0x0014:
        r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$elementAtOrElse$1;
        r0.<init>(r14);
    L_0x0019:
        r14 = r0.result;
        r1 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        r2 = r0.label;
        r3 = 1;
        switch(r2) {
            case 0: goto L_0x008b;
            case 1: goto L_0x005a;
            case 2: goto L_0x002d;
            default: goto L_0x0025;
        };
    L_0x0025:
        r11 = new java.lang.IllegalStateException;
        r12 = "call to 'resume' before 'invoke' with coroutine";
        r11.<init>(r12);
        throw r11;
    L_0x002d:
        r11 = r0.L$5;
        r11 = (kotlinx.coroutines.channels.ChannelIterator) r11;
        r12 = r0.I$1;
        r13 = r0.L$4;
        r13 = (kotlinx.coroutines.channels.ReceiveChannel) r13;
        r2 = r0.L$3;
        r2 = (java.lang.Throwable) r2;
        r4 = r0.L$2;
        r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4;
        r5 = r0.L$1;
        r5 = (kotlin.jvm.functions.Function1) r5;
        r6 = r0.I$0;
        r7 = r0.L$0;
        r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7;
        r8 = r14 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x0086, all -> 0x0083 }
        if (r8 != 0) goto L_0x0055;
    L_0x004d:
        r9 = r4;
        r4 = r11;
        r11 = r9;
        r10 = r14;
        r14 = r12;
        r12 = r10;
        goto L_0x00fb;
    L_0x0055:
        r14 = (kotlin.Result.Failure) r14;	 Catch:{ Throwable -> 0x0086, all -> 0x0083 }
        r11 = r14.exception;	 Catch:{ Throwable -> 0x0086, all -> 0x0083 }
        throw r11;	 Catch:{ Throwable -> 0x0086, all -> 0x0083 }
    L_0x005a:
        r11 = r0.L$5;
        r11 = (kotlinx.coroutines.channels.ChannelIterator) r11;
        r12 = r0.I$1;
        r13 = r0.L$4;
        r13 = (kotlinx.coroutines.channels.ReceiveChannel) r13;
        r2 = r0.L$3;
        r2 = (java.lang.Throwable) r2;
        r4 = r0.L$2;
        r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4;
        r5 = r0.L$1;
        r5 = (kotlin.jvm.functions.Function1) r5;
        r6 = r0.I$0;
        r7 = r0.L$0;
        r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7;
        r8 = r14 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x0086, all -> 0x0083 }
        if (r8 != 0) goto L_0x007e;
    L_0x007a:
        r9 = r14;
        r14 = r12;
        r12 = r9;
        goto L_0x00d6;
    L_0x007e:
        r14 = (kotlin.Result.Failure) r14;	 Catch:{ Throwable -> 0x0086, all -> 0x0083 }
        r11 = r14.exception;	 Catch:{ Throwable -> 0x0086, all -> 0x0083 }
        throw r11;	 Catch:{ Throwable -> 0x0086, all -> 0x0083 }
    L_0x0083:
        r11 = move-exception;
        goto L_0x0119;
    L_0x0086:
        r11 = move-exception;
        r2 = r11;
        r11 = r4;
        goto L_0x0118;
    L_0x008b:
        r2 = r14 instanceof kotlin.Result.Failure;
        if (r2 != 0) goto L_0x0123;
    L_0x008f:
        r14 = 0;
        r2 = r14;
        r2 = (java.lang.Throwable) r2;
        if (r12 >= 0) goto L_0x00b1;
    L_0x0095:
        r12 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r12);	 Catch:{ Throwable -> 0x00ad }
        r12 = r13.invoke(r12);	 Catch:{ Throwable -> 0x00ad }
        r13 = 4;
        kotlin.jvm.internal.InlineMarker.finallyStart(r13);
    L_0x00a1:
        r11.cancel(r2);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r13);
        return r12;
    L_0x00a8:
        r12 = move-exception;
        r4 = r11;
        r11 = r12;
        goto L_0x0119;
    L_0x00ad:
        r12 = move-exception;
        r2 = r12;
        goto L_0x0118;
    L_0x00b1:
        r14 = 0;
        r4 = r11.iterator();	 Catch:{ Throwable -> 0x00ad }
        r7 = r11;
        r6 = r12;
        r5 = r13;
        r13 = r7;
    L_0x00ba:
        r0.L$0 = r7;	 Catch:{ Throwable -> 0x00ad }
        r0.I$0 = r6;	 Catch:{ Throwable -> 0x00ad }
        r0.L$1 = r5;	 Catch:{ Throwable -> 0x00ad }
        r0.L$2 = r11;	 Catch:{ Throwable -> 0x00ad }
        r0.L$3 = r2;	 Catch:{ Throwable -> 0x00ad }
        r0.L$4 = r13;	 Catch:{ Throwable -> 0x00ad }
        r0.I$1 = r14;	 Catch:{ Throwable -> 0x00ad }
        r0.L$5 = r4;	 Catch:{ Throwable -> 0x00ad }
        r0.label = r3;	 Catch:{ Throwable -> 0x00ad }
        r12 = r4.hasNext(r0);	 Catch:{ Throwable -> 0x00ad }
        if (r12 != r1) goto L_0x00d3;
    L_0x00d2:
        return r1;
    L_0x00d3:
        r9 = r4;
        r4 = r11;
        r11 = r9;
    L_0x00d6:
        r12 = (java.lang.Boolean) r12;	 Catch:{ Throwable -> 0x0086, all -> 0x0083 }
        r12 = r12.booleanValue();	 Catch:{ Throwable -> 0x0086, all -> 0x0083 }
        r8 = 2;
        if (r12 == 0) goto L_0x0106;
    L_0x00df:
        r0.L$0 = r7;	 Catch:{ Throwable -> 0x0086, all -> 0x0083 }
        r0.I$0 = r6;	 Catch:{ Throwable -> 0x0086, all -> 0x0083 }
        r0.L$1 = r5;	 Catch:{ Throwable -> 0x0086, all -> 0x0083 }
        r0.L$2 = r4;	 Catch:{ Throwable -> 0x0086, all -> 0x0083 }
        r0.L$3 = r2;	 Catch:{ Throwable -> 0x0086, all -> 0x0083 }
        r0.L$4 = r13;	 Catch:{ Throwable -> 0x0086, all -> 0x0083 }
        r0.I$1 = r14;	 Catch:{ Throwable -> 0x0086, all -> 0x0083 }
        r0.L$5 = r11;	 Catch:{ Throwable -> 0x0086, all -> 0x0083 }
        r0.label = r8;	 Catch:{ Throwable -> 0x0086, all -> 0x0083 }
        r12 = r11.next(r0);	 Catch:{ Throwable -> 0x0086, all -> 0x0083 }
        if (r12 != r1) goto L_0x00f8;
    L_0x00f7:
        return r1;
    L_0x00f8:
        r9 = r4;
        r4 = r11;
        r11 = r9;
    L_0x00fb:
        r8 = r14 + 1;
        if (r6 != r14) goto L_0x0104;
    L_0x00ff:
        r13 = 3;
        kotlin.jvm.internal.InlineMarker.finallyStart(r13);
        goto L_0x00a1;
    L_0x0104:
        r14 = r8;
        goto L_0x00ba;
    L_0x0106:
        r11 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r6);	 Catch:{ Throwable -> 0x0086, all -> 0x0083 }
        r11 = r5.invoke(r11);	 Catch:{ Throwable -> 0x0086, all -> 0x0083 }
        kotlin.jvm.internal.InlineMarker.finallyStart(r8);
        r4.cancel(r2);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r8);
        return r11;
    L_0x0118:
        throw r2;	 Catch:{ all -> 0x00a8 }
    L_0x0119:
        kotlin.jvm.internal.InlineMarker.finallyStart(r3);
        r4.cancel(r2);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r3);
        throw r11;
    L_0x0123:
        r14 = (kotlin.Result.Failure) r14;
        r11 = r14.exception;
        throw r11;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.elementAtOrElse(kotlinx.coroutines.channels.ReceiveChannel, int, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @org.jetbrains.annotations.Nullable
    @kotlinx.coroutines.ObsoleteCoroutinesApi
    public static final <E> java.lang.Object elementAtOrNull(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r11, int r12, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super E> r13) {
        /*
        r0 = r13 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$elementAtOrNull$1;
        if (r0 == 0) goto L_0x0014;
    L_0x0004:
        r0 = r13;
        r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$elementAtOrNull$1) r0;
        r1 = r0.label;
        r2 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r1 = r1 & r2;
        if (r1 == 0) goto L_0x0014;
    L_0x000e:
        r13 = r0.label;
        r13 = r13 - r2;
        r0.label = r13;
        goto L_0x0019;
    L_0x0014:
        r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$elementAtOrNull$1;
        r0.<init>(r13);
    L_0x0019:
        r13 = r0.result;
        r1 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        r2 = r0.label;
        r3 = 0;
        switch(r2) {
            case 0: goto L_0x0083;
            case 1: goto L_0x0055;
            case 2: goto L_0x002d;
            default: goto L_0x0025;
        };
    L_0x0025:
        r11 = new java.lang.IllegalStateException;
        r12 = "call to 'resume' before 'invoke' with coroutine";
        r11.<init>(r12);
        throw r11;
    L_0x002d:
        r11 = r0.L$4;
        r11 = (kotlinx.coroutines.channels.ChannelIterator) r11;
        r12 = r0.I$1;
        r2 = r0.L$3;
        r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2;
        r4 = r0.L$2;
        r4 = (java.lang.Throwable) r4;
        r5 = r0.L$1;
        r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5;
        r6 = r0.I$0;
        r7 = r0.L$0;
        r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7;
        r8 = r13 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x007e, all -> 0x007b }
        if (r8 != 0) goto L_0x0050;
    L_0x0049:
        r9 = r2;
        r2 = r11;
        r11 = r5;
        r5 = r1;
        r1 = r9;
        goto L_0x00dd;
    L_0x0050:
        r13 = (kotlin.Result.Failure) r13;	 Catch:{ Throwable -> 0x007e, all -> 0x007b }
        r11 = r13.exception;	 Catch:{ Throwable -> 0x007e, all -> 0x007b }
        throw r11;	 Catch:{ Throwable -> 0x007e, all -> 0x007b }
    L_0x0055:
        r11 = r0.L$4;
        r11 = (kotlinx.coroutines.channels.ChannelIterator) r11;
        r12 = r0.I$1;
        r2 = r0.L$3;
        r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2;
        r4 = r0.L$2;
        r4 = (java.lang.Throwable) r4;
        r5 = r0.L$1;
        r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5;
        r6 = r0.I$0;
        r7 = r0.L$0;
        r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7;
        r8 = r13 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x007e, all -> 0x007b }
        if (r8 != 0) goto L_0x0076;
    L_0x0071:
        r9 = r1;
        r1 = r12;
        r12 = r2;
        r2 = r9;
        goto L_0x00b6;
    L_0x0076:
        r13 = (kotlin.Result.Failure) r13;	 Catch:{ Throwable -> 0x007e, all -> 0x007b }
        r11 = r13.exception;	 Catch:{ Throwable -> 0x007e, all -> 0x007b }
        throw r11;	 Catch:{ Throwable -> 0x007e, all -> 0x007b }
    L_0x007b:
        r11 = move-exception;
        goto L_0x00f4;
    L_0x007e:
        r11 = move-exception;
        r4 = r11;
        r11 = r5;
        goto L_0x00f3;
    L_0x0083:
        r2 = r13 instanceof kotlin.Result.Failure;
        if (r2 != 0) goto L_0x00f8;
    L_0x0087:
        r4 = r3;
        r4 = (java.lang.Throwable) r4;
        if (r12 >= 0) goto L_0x0090;
    L_0x008c:
        r11.cancel(r4);
        return r3;
    L_0x0090:
        r13 = 0;
        r2 = r11.iterator();	 Catch:{ Throwable -> 0x00f1 }
        r7 = r11;
        r6 = r12;
        r12 = r7;
    L_0x0098:
        r0.L$0 = r7;	 Catch:{ Throwable -> 0x00f1 }
        r0.I$0 = r6;	 Catch:{ Throwable -> 0x00f1 }
        r0.L$1 = r11;	 Catch:{ Throwable -> 0x00f1 }
        r0.L$2 = r4;	 Catch:{ Throwable -> 0x00f1 }
        r0.L$3 = r12;	 Catch:{ Throwable -> 0x00f1 }
        r0.I$1 = r13;	 Catch:{ Throwable -> 0x00f1 }
        r0.L$4 = r2;	 Catch:{ Throwable -> 0x00f1 }
        r5 = 1;
        r0.label = r5;	 Catch:{ Throwable -> 0x00f1 }
        r5 = r2.hasNext(r0);	 Catch:{ Throwable -> 0x00f1 }
        if (r5 != r1) goto L_0x00b0;
    L_0x00af:
        return r1;
    L_0x00b0:
        r9 = r5;
        r5 = r11;
        r11 = r2;
        r2 = r1;
        r1 = r13;
        r13 = r9;
    L_0x00b6:
        r13 = (java.lang.Boolean) r13;	 Catch:{ Throwable -> 0x007e, all -> 0x007b }
        r13 = r13.booleanValue();	 Catch:{ Throwable -> 0x007e, all -> 0x007b }
        if (r13 == 0) goto L_0x00e9;
    L_0x00be:
        r0.L$0 = r7;	 Catch:{ Throwable -> 0x007e, all -> 0x007b }
        r0.I$0 = r6;	 Catch:{ Throwable -> 0x007e, all -> 0x007b }
        r0.L$1 = r5;	 Catch:{ Throwable -> 0x007e, all -> 0x007b }
        r0.L$2 = r4;	 Catch:{ Throwable -> 0x007e, all -> 0x007b }
        r0.L$3 = r12;	 Catch:{ Throwable -> 0x007e, all -> 0x007b }
        r0.I$1 = r1;	 Catch:{ Throwable -> 0x007e, all -> 0x007b }
        r0.L$4 = r11;	 Catch:{ Throwable -> 0x007e, all -> 0x007b }
        r13 = 2;
        r0.label = r13;	 Catch:{ Throwable -> 0x007e, all -> 0x007b }
        r13 = r11.next(r0);	 Catch:{ Throwable -> 0x007e, all -> 0x007b }
        if (r13 != r2) goto L_0x00d6;
    L_0x00d5:
        return r2;
    L_0x00d6:
        r9 = r2;
        r2 = r11;
        r11 = r5;
        r5 = r9;
        r10 = r1;
        r1 = r12;
        r12 = r10;
    L_0x00dd:
        r8 = r12 + 1;
        if (r6 != r12) goto L_0x00e5;
    L_0x00e1:
        r11.cancel(r4);
        return r13;
    L_0x00e5:
        r12 = r1;
        r1 = r5;
        r13 = r8;
        goto L_0x0098;
    L_0x00e9:
        r5.cancel(r4);
        return r3;
    L_0x00ed:
        r12 = move-exception;
        r5 = r11;
        r11 = r12;
        goto L_0x00f4;
    L_0x00f1:
        r12 = move-exception;
        r4 = r12;
    L_0x00f3:
        throw r4;	 Catch:{ all -> 0x00ed }
    L_0x00f4:
        r5.cancel(r4);
        throw r11;
    L_0x00f8:
        r13 = (kotlin.Result.Failure) r13;
        r11 = r13.exception;
        throw r11;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.elementAtOrNull(kotlinx.coroutines.channels.ReceiveChannel, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @org.jetbrains.annotations.Nullable
    @kotlinx.coroutines.ObsoleteCoroutinesApi
    public static final <E> java.lang.Object find(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r13, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super E, java.lang.Boolean> r14, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super E> r15) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:37)
	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:61)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1603198149.run(Unknown Source)
*/
        /*
        r0 = r15 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$find$1;
        if (r0 == 0) goto L_0x0014;
    L_0x0004:
        r0 = r15;
        r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$find$1) r0;
        r1 = r0.label;
        r2 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r1 = r1 & r2;
        if (r1 == 0) goto L_0x0014;
    L_0x000e:
        r15 = r0.label;
        r15 = r15 - r2;
        r0.label = r15;
        goto L_0x0019;
    L_0x0014:
        r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$find$1;
        r0.<init>(r15);
    L_0x0019:
        r15 = r0.result;
        r1 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        r2 = r0.label;
        r3 = 0;
        r4 = 2;
        r5 = 1;
        switch(r2) {
            case 0: goto L_0x0091;
            case 1: goto L_0x005f;
            case 2: goto L_0x002f;
            default: goto L_0x0027;
        };
    L_0x0027:
        r13 = new java.lang.IllegalStateException;
        r14 = "call to 'resume' before 'invoke' with coroutine";
        r13.<init>(r14);
        throw r13;
    L_0x002f:
        r13 = r0.L$7;
        r13 = (kotlinx.coroutines.channels.ChannelIterator) r13;
        r14 = r0.L$6;
        r14 = (kotlinx.coroutines.channels.ReceiveChannel) r14;
        r2 = r0.L$5;
        r2 = (java.lang.Throwable) r2;
        r6 = r0.L$4;
        r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6;
        r7 = r0.L$3;
        r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7;
        r8 = r0.L$2;
        r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8;
        r9 = r0.L$1;
        r9 = (kotlin.jvm.functions.Function1) r9;
        r10 = r0.L$0;
        r10 = (kotlinx.coroutines.channels.ReceiveChannel) r10;
        r11 = r15 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x008c, all -> 0x0089 }
        if (r11 != 0) goto L_0x005a;	 Catch:{ Throwable -> 0x008c, all -> 0x0089 }
    L_0x0053:
        r12 = r14;	 Catch:{ Throwable -> 0x008c, all -> 0x0089 }
        r14 = r13;	 Catch:{ Throwable -> 0x008c, all -> 0x0089 }
        r13 = r6;	 Catch:{ Throwable -> 0x008c, all -> 0x0089 }
        r6 = r1;	 Catch:{ Throwable -> 0x008c, all -> 0x0089 }
        r1 = r12;	 Catch:{ Throwable -> 0x008c, all -> 0x0089 }
        goto L_0x00df;	 Catch:{ Throwable -> 0x008c, all -> 0x0089 }
    L_0x005a:
        r15 = (kotlin.Result.Failure) r15;	 Catch:{ Throwable -> 0x008c, all -> 0x0089 }
        r13 = r15.exception;	 Catch:{ Throwable -> 0x008c, all -> 0x0089 }
        throw r13;	 Catch:{ Throwable -> 0x008c, all -> 0x0089 }
    L_0x005f:
        r13 = r0.L$7;
        r13 = (kotlinx.coroutines.channels.ChannelIterator) r13;
        r14 = r0.L$6;
        r14 = (kotlinx.coroutines.channels.ReceiveChannel) r14;
        r2 = r0.L$5;
        r2 = (java.lang.Throwable) r2;
        r6 = r0.L$4;
        r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6;
        r7 = r0.L$3;
        r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7;
        r8 = r0.L$2;
        r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8;
        r9 = r0.L$1;
        r9 = (kotlin.jvm.functions.Function1) r9;
        r10 = r0.L$0;
        r10 = (kotlinx.coroutines.channels.ReceiveChannel) r10;
        r11 = r15 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x008c, all -> 0x0089 }
        if (r11 != 0) goto L_0x0084;	 Catch:{ Throwable -> 0x008c, all -> 0x0089 }
    L_0x0083:
        goto L_0x00be;	 Catch:{ Throwable -> 0x008c, all -> 0x0089 }
    L_0x0084:
        r15 = (kotlin.Result.Failure) r15;	 Catch:{ Throwable -> 0x008c, all -> 0x0089 }
        r13 = r15.exception;	 Catch:{ Throwable -> 0x008c, all -> 0x0089 }
        throw r13;	 Catch:{ Throwable -> 0x008c, all -> 0x0089 }
    L_0x0089:
        r13 = move-exception;
        goto L_0x010d;
    L_0x008c:
        r13 = move-exception;
        r2 = r13;
        r13 = r6;
        goto L_0x010c;
    L_0x0091:
        r2 = r15 instanceof kotlin.Result.Failure;
        if (r2 != 0) goto L_0x0117;
    L_0x0095:
        r2 = r3;
        r2 = (java.lang.Throwable) r2;
        r15 = r13.iterator();	 Catch:{ Throwable -> 0x010a }
        r7 = r13;	 Catch:{ Throwable -> 0x010a }
        r8 = r7;	 Catch:{ Throwable -> 0x010a }
        r10 = r8;	 Catch:{ Throwable -> 0x010a }
        r9 = r14;	 Catch:{ Throwable -> 0x010a }
        r14 = r10;	 Catch:{ Throwable -> 0x010a }
    L_0x00a1:
        r0.L$0 = r10;	 Catch:{ Throwable -> 0x010a }
        r0.L$1 = r9;	 Catch:{ Throwable -> 0x010a }
        r0.L$2 = r8;	 Catch:{ Throwable -> 0x010a }
        r0.L$3 = r7;	 Catch:{ Throwable -> 0x010a }
        r0.L$4 = r13;	 Catch:{ Throwable -> 0x010a }
        r0.L$5 = r2;	 Catch:{ Throwable -> 0x010a }
        r0.L$6 = r14;	 Catch:{ Throwable -> 0x010a }
        r0.L$7 = r15;	 Catch:{ Throwable -> 0x010a }
        r0.label = r5;	 Catch:{ Throwable -> 0x010a }
        r6 = r15.hasNext(r0);	 Catch:{ Throwable -> 0x010a }
        if (r6 != r1) goto L_0x00ba;
    L_0x00b9:
        return r1;
    L_0x00ba:
        r12 = r6;
        r6 = r13;
        r13 = r15;
        r15 = r12;
    L_0x00be:
        r15 = (java.lang.Boolean) r15;	 Catch:{ Throwable -> 0x008c, all -> 0x0089 }
        r15 = r15.booleanValue();	 Catch:{ Throwable -> 0x008c, all -> 0x0089 }
        if (r15 == 0) goto L_0x00f9;	 Catch:{ Throwable -> 0x008c, all -> 0x0089 }
    L_0x00c6:
        r0.L$0 = r10;	 Catch:{ Throwable -> 0x008c, all -> 0x0089 }
        r0.L$1 = r9;	 Catch:{ Throwable -> 0x008c, all -> 0x0089 }
        r0.L$2 = r8;	 Catch:{ Throwable -> 0x008c, all -> 0x0089 }
        r0.L$3 = r7;	 Catch:{ Throwable -> 0x008c, all -> 0x0089 }
        r0.L$4 = r6;	 Catch:{ Throwable -> 0x008c, all -> 0x0089 }
        r0.L$5 = r2;	 Catch:{ Throwable -> 0x008c, all -> 0x0089 }
        r0.L$6 = r14;	 Catch:{ Throwable -> 0x008c, all -> 0x0089 }
        r0.L$7 = r13;	 Catch:{ Throwable -> 0x008c, all -> 0x0089 }
        r0.label = r4;	 Catch:{ Throwable -> 0x008c, all -> 0x0089 }
        r15 = r13.next(r0);	 Catch:{ Throwable -> 0x008c, all -> 0x0089 }
        if (r15 != r1) goto L_0x0053;
    L_0x00de:
        return r1;
    L_0x00df:
        r11 = r9.invoke(r15);	 Catch:{ Throwable -> 0x010a }
        r11 = (java.lang.Boolean) r11;	 Catch:{ Throwable -> 0x010a }
        r11 = r11.booleanValue();	 Catch:{ Throwable -> 0x010a }
        if (r11 == 0) goto L_0x00f5;
    L_0x00eb:
        kotlin.jvm.internal.InlineMarker.finallyStart(r4);
        r13.cancel(r2);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r4);
        goto L_0x0105;
    L_0x00f5:
        r15 = r14;
        r14 = r1;
        r1 = r6;
        goto L_0x00a1;
    L_0x00f9:
        r13 = kotlin.Unit.INSTANCE;	 Catch:{ Throwable -> 0x008c, all -> 0x0089 }
        kotlin.jvm.internal.InlineMarker.finallyStart(r5);
        r6.cancel(r2);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r5);
        r15 = r3;
    L_0x0105:
        return r15;
    L_0x0106:
        r14 = move-exception;
        r6 = r13;
        r13 = r14;
        goto L_0x010d;
    L_0x010a:
        r14 = move-exception;
        r2 = r14;
    L_0x010c:
        throw r2;	 Catch:{ all -> 0x0106 }
    L_0x010d:
        kotlin.jvm.internal.InlineMarker.finallyStart(r5);
        r6.cancel(r2);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r5);
        throw r13;
    L_0x0117:
        r15 = (kotlin.Result.Failure) r15;
        r13 = r15.exception;
        throw r13;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.find(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @org.jetbrains.annotations.Nullable
    @kotlinx.coroutines.ObsoleteCoroutinesApi
    public static final <E> java.lang.Object findLast(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r13, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super E, java.lang.Boolean> r14, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super E> r15) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:37)
	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:61)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1603198149.run(Unknown Source)
*/
        /*
        r0 = r15 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$findLast$1;
        if (r0 == 0) goto L_0x0014;
    L_0x0004:
        r0 = r15;
        r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$findLast$1) r0;
        r1 = r0.label;
        r2 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r1 = r1 & r2;
        if (r1 == 0) goto L_0x0014;
    L_0x000e:
        r15 = r0.label;
        r15 = r15 - r2;
        r0.label = r15;
        goto L_0x0019;
    L_0x0014:
        r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$findLast$1;
        r0.<init>(r15);
    L_0x0019:
        r15 = r0.result;
        r1 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        r2 = r0.label;
        r3 = 1;
        switch(r2) {
            case 0: goto L_0x0095;
            case 1: goto L_0x005f;
            case 2: goto L_0x002d;
            default: goto L_0x0025;
        };
    L_0x0025:
        r13 = new java.lang.IllegalStateException;
        r14 = "call to 'resume' before 'invoke' with coroutine";
        r13.<init>(r14);
        throw r13;
    L_0x002d:
        r13 = r0.L$8;
        r13 = (kotlinx.coroutines.channels.ChannelIterator) r13;
        r14 = r0.L$7;
        r14 = (kotlinx.coroutines.channels.ReceiveChannel) r14;
        r2 = r0.L$6;
        r2 = (java.lang.Throwable) r2;
        r4 = r0.L$5;
        r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4;
        r5 = r0.L$4;
        r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5;
        r6 = r0.L$3;
        r6 = (kotlin.jvm.internal.Ref.ObjectRef) r6;
        r7 = r0.L$2;
        r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7;
        r8 = r0.L$1;
        r8 = (kotlin.jvm.functions.Function1) r8;
        r9 = r0.L$0;
        r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9;
        r10 = r15 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x0090, all -> 0x008d }
        if (r10 != 0) goto L_0x005a;	 Catch:{ Throwable -> 0x0090, all -> 0x008d }
    L_0x0055:
        r11 = r4;	 Catch:{ Throwable -> 0x0090, all -> 0x008d }
        r4 = r13;	 Catch:{ Throwable -> 0x0090, all -> 0x008d }
        r13 = r11;	 Catch:{ Throwable -> 0x0090, all -> 0x008d }
        goto L_0x00f1;	 Catch:{ Throwable -> 0x0090, all -> 0x008d }
    L_0x005a:
        r15 = (kotlin.Result.Failure) r15;	 Catch:{ Throwable -> 0x0090, all -> 0x008d }
        r13 = r15.exception;	 Catch:{ Throwable -> 0x0090, all -> 0x008d }
        throw r13;	 Catch:{ Throwable -> 0x0090, all -> 0x008d }
    L_0x005f:
        r13 = r0.L$8;
        r13 = (kotlinx.coroutines.channels.ChannelIterator) r13;
        r14 = r0.L$7;
        r14 = (kotlinx.coroutines.channels.ReceiveChannel) r14;
        r2 = r0.L$6;
        r2 = (java.lang.Throwable) r2;
        r4 = r0.L$5;
        r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4;
        r5 = r0.L$4;
        r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5;
        r6 = r0.L$3;
        r6 = (kotlin.jvm.internal.Ref.ObjectRef) r6;
        r7 = r0.L$2;
        r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7;
        r8 = r0.L$1;
        r8 = (kotlin.jvm.functions.Function1) r8;
        r9 = r0.L$0;
        r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9;
        r10 = r15 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x0090, all -> 0x008d }
        if (r10 != 0) goto L_0x0088;	 Catch:{ Throwable -> 0x0090, all -> 0x008d }
    L_0x0087:
        goto L_0x00cd;	 Catch:{ Throwable -> 0x0090, all -> 0x008d }
    L_0x0088:
        r15 = (kotlin.Result.Failure) r15;	 Catch:{ Throwable -> 0x0090, all -> 0x008d }
        r13 = r15.exception;	 Catch:{ Throwable -> 0x0090, all -> 0x008d }
        throw r13;	 Catch:{ Throwable -> 0x0090, all -> 0x008d }
    L_0x008d:
        r13 = move-exception;
        goto L_0x0116;
    L_0x0090:
        r13 = move-exception;
        r2 = r13;
        r13 = r4;
        goto L_0x0115;
    L_0x0095:
        r2 = r15 instanceof kotlin.Result.Failure;
        if (r2 != 0) goto L_0x0120;
    L_0x0099:
        r15 = new kotlin.jvm.internal.Ref$ObjectRef;
        r15.<init>();
        r2 = 0;
        r15.element = r2;
        r2 = (java.lang.Throwable) r2;
        r4 = r13.iterator();	 Catch:{ Throwable -> 0x0113 }
        r5 = r13;	 Catch:{ Throwable -> 0x0113 }
        r7 = r5;	 Catch:{ Throwable -> 0x0113 }
        r9 = r7;	 Catch:{ Throwable -> 0x0113 }
        r8 = r14;	 Catch:{ Throwable -> 0x0113 }
        r14 = r9;	 Catch:{ Throwable -> 0x0113 }
    L_0x00ac:
        r0.L$0 = r9;	 Catch:{ Throwable -> 0x0113 }
        r0.L$1 = r8;	 Catch:{ Throwable -> 0x0113 }
        r0.L$2 = r7;	 Catch:{ Throwable -> 0x0113 }
        r0.L$3 = r15;	 Catch:{ Throwable -> 0x0113 }
        r0.L$4 = r5;	 Catch:{ Throwable -> 0x0113 }
        r0.L$5 = r13;	 Catch:{ Throwable -> 0x0113 }
        r0.L$6 = r2;	 Catch:{ Throwable -> 0x0113 }
        r0.L$7 = r14;	 Catch:{ Throwable -> 0x0113 }
        r0.L$8 = r4;	 Catch:{ Throwable -> 0x0113 }
        r0.label = r3;	 Catch:{ Throwable -> 0x0113 }
        r6 = r4.hasNext(r0);	 Catch:{ Throwable -> 0x0113 }
        if (r6 != r1) goto L_0x00c7;
    L_0x00c6:
        return r1;
    L_0x00c7:
        r11 = r4;
        r4 = r13;
        r13 = r11;
        r12 = r6;
        r6 = r15;
        r15 = r12;
    L_0x00cd:
        r15 = (java.lang.Boolean) r15;	 Catch:{ Throwable -> 0x0090, all -> 0x008d }
        r15 = r15.booleanValue();	 Catch:{ Throwable -> 0x0090, all -> 0x008d }
        if (r15 == 0) goto L_0x0101;	 Catch:{ Throwable -> 0x0090, all -> 0x008d }
    L_0x00d5:
        r0.L$0 = r9;	 Catch:{ Throwable -> 0x0090, all -> 0x008d }
        r0.L$1 = r8;	 Catch:{ Throwable -> 0x0090, all -> 0x008d }
        r0.L$2 = r7;	 Catch:{ Throwable -> 0x0090, all -> 0x008d }
        r0.L$3 = r6;	 Catch:{ Throwable -> 0x0090, all -> 0x008d }
        r0.L$4 = r5;	 Catch:{ Throwable -> 0x0090, all -> 0x008d }
        r0.L$5 = r4;	 Catch:{ Throwable -> 0x0090, all -> 0x008d }
        r0.L$6 = r2;	 Catch:{ Throwable -> 0x0090, all -> 0x008d }
        r0.L$7 = r14;	 Catch:{ Throwable -> 0x0090, all -> 0x008d }
        r0.L$8 = r13;	 Catch:{ Throwable -> 0x0090, all -> 0x008d }
        r15 = 2;	 Catch:{ Throwable -> 0x0090, all -> 0x008d }
        r0.label = r15;	 Catch:{ Throwable -> 0x0090, all -> 0x008d }
        r15 = r13.next(r0);	 Catch:{ Throwable -> 0x0090, all -> 0x008d }
        if (r15 != r1) goto L_0x0055;
    L_0x00f0:
        return r1;
    L_0x00f1:
        r10 = r8.invoke(r15);	 Catch:{ Throwable -> 0x0113 }
        r10 = (java.lang.Boolean) r10;	 Catch:{ Throwable -> 0x0113 }
        r10 = r10.booleanValue();	 Catch:{ Throwable -> 0x0113 }
        if (r10 == 0) goto L_0x00ff;	 Catch:{ Throwable -> 0x0113 }
    L_0x00fd:
        r6.element = r15;	 Catch:{ Throwable -> 0x0113 }
    L_0x00ff:
        r15 = r6;
        goto L_0x00ac;
    L_0x0101:
        r13 = kotlin.Unit.INSTANCE;	 Catch:{ Throwable -> 0x0090, all -> 0x008d }
        kotlin.jvm.internal.InlineMarker.finallyStart(r3);
        r4.cancel(r2);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r3);
        r13 = r6.element;
        return r13;
    L_0x010f:
        r14 = move-exception;
        r4 = r13;
        r13 = r14;
        goto L_0x0116;
    L_0x0113:
        r14 = move-exception;
        r2 = r14;
    L_0x0115:
        throw r2;	 Catch:{ all -> 0x010f }
    L_0x0116:
        kotlin.jvm.internal.InlineMarker.finallyStart(r3);
        r4.cancel(r2);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r3);
        throw r13;
    L_0x0120:
        r15 = (kotlin.Result.Failure) r15;
        r13 = r15.exception;
        throw r13;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.findLast(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @org.jetbrains.annotations.Nullable
    @kotlinx.coroutines.ObsoleteCoroutinesApi
    public static final <E> java.lang.Object first(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r8, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super E> r9) {
        /*
        r0 = r9 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$first$1;
        if (r0 == 0) goto L_0x0014;
    L_0x0004:
        r0 = r9;
        r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$first$1) r0;
        r1 = r0.label;
        r2 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r1 = r1 & r2;
        if (r1 == 0) goto L_0x0014;
    L_0x000e:
        r9 = r0.label;
        r9 = r9 - r2;
        r0.label = r9;
        goto L_0x0019;
    L_0x0014:
        r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$first$1;
        r0.<init>(r9);
    L_0x0019:
        r9 = r0.result;
        r1 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        r2 = r0.label;
        switch(r2) {
            case 0: goto L_0x0077;
            case 1: goto L_0x0054;
            case 2: goto L_0x002c;
            default: goto L_0x0024;
        };
    L_0x0024:
        r8 = new java.lang.IllegalStateException;
        r9 = "call to 'resume' before 'invoke' with coroutine";
        r8.<init>(r9);
        throw r8;
    L_0x002c:
        r8 = r0.L$4;
        r8 = (kotlinx.coroutines.channels.ChannelIterator) r8;
        r8 = r0.L$3;
        r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8;
        r8 = r0.L$2;
        r8 = (java.lang.Throwable) r8;
        r1 = r0.L$1;
        r1 = (kotlinx.coroutines.channels.ReceiveChannel) r1;
        r0 = r0.L$0;
        r0 = (kotlinx.coroutines.channels.ReceiveChannel) r0;
        r0 = r9 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x0050, all -> 0x004b }
        if (r0 != 0) goto L_0x0046;
    L_0x0044:
        goto L_0x00bb;
    L_0x0046:
        r9 = (kotlin.Result.Failure) r9;	 Catch:{ Throwable -> 0x0050, all -> 0x004b }
        r9 = r9.exception;	 Catch:{ Throwable -> 0x0050, all -> 0x004b }
        throw r9;	 Catch:{ Throwable -> 0x0050, all -> 0x004b }
    L_0x004b:
        r9 = move-exception;
        r3 = r8;
        r4 = r1;
        goto L_0x00d4;
    L_0x0050:
        r8 = move-exception;
        r4 = r1;
        goto L_0x00d1;
    L_0x0054:
        r8 = r0.L$4;
        r8 = (kotlinx.coroutines.channels.ChannelIterator) r8;
        r2 = r0.L$3;
        r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2;
        r3 = r0.L$2;
        r3 = (java.lang.Throwable) r3;
        r4 = r0.L$1;
        r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4;
        r5 = r0.L$0;
        r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5;
        r6 = r9 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x0075, all -> 0x0072 }
        if (r6 != 0) goto L_0x006d;
    L_0x006c:
        goto L_0x009d;
    L_0x006d:
        r9 = (kotlin.Result.Failure) r9;	 Catch:{ Throwable -> 0x0075, all -> 0x0072 }
        r8 = r9.exception;	 Catch:{ Throwable -> 0x0075, all -> 0x0072 }
        throw r8;	 Catch:{ Throwable -> 0x0075, all -> 0x0072 }
    L_0x0072:
        r9 = move-exception;
        goto L_0x00d4;
    L_0x0075:
        r8 = move-exception;
        goto L_0x00d1;
    L_0x0077:
        r2 = r9 instanceof kotlin.Result.Failure;
        if (r2 != 0) goto L_0x00d8;
    L_0x007b:
        r9 = 0;
        r9 = (java.lang.Throwable) r9;
        r2 = r8.iterator();	 Catch:{ Throwable -> 0x00ce, all -> 0x00c9 }
        r0.L$0 = r8;	 Catch:{ Throwable -> 0x00ce, all -> 0x00c9 }
        r0.L$1 = r8;	 Catch:{ Throwable -> 0x00ce, all -> 0x00c9 }
        r0.L$2 = r9;	 Catch:{ Throwable -> 0x00ce, all -> 0x00c9 }
        r0.L$3 = r8;	 Catch:{ Throwable -> 0x00ce, all -> 0x00c9 }
        r0.L$4 = r2;	 Catch:{ Throwable -> 0x00ce, all -> 0x00c9 }
        r3 = 1;
        r0.label = r3;	 Catch:{ Throwable -> 0x00ce, all -> 0x00c9 }
        r3 = r2.hasNext(r0);	 Catch:{ Throwable -> 0x00ce, all -> 0x00c9 }
        if (r3 != r1) goto L_0x0096;
    L_0x0095:
        return r1;
    L_0x0096:
        r4 = r8;
        r5 = r4;
        r8 = r2;
        r2 = r5;
        r7 = r3;
        r3 = r9;
        r9 = r7;
    L_0x009d:
        r9 = (java.lang.Boolean) r9;	 Catch:{ Throwable -> 0x0075, all -> 0x0072 }
        r9 = r9.booleanValue();	 Catch:{ Throwable -> 0x0075, all -> 0x0072 }
        if (r9 == 0) goto L_0x00bf;
    L_0x00a5:
        r0.L$0 = r5;	 Catch:{ Throwable -> 0x0075, all -> 0x0072 }
        r0.L$1 = r4;	 Catch:{ Throwable -> 0x0075, all -> 0x0072 }
        r0.L$2 = r3;	 Catch:{ Throwable -> 0x0075, all -> 0x0072 }
        r0.L$3 = r2;	 Catch:{ Throwable -> 0x0075, all -> 0x0072 }
        r0.L$4 = r8;	 Catch:{ Throwable -> 0x0075, all -> 0x0072 }
        r9 = 2;
        r0.label = r9;	 Catch:{ Throwable -> 0x0075, all -> 0x0072 }
        r9 = r8.next(r0);	 Catch:{ Throwable -> 0x0075, all -> 0x0072 }
        if (r9 != r1) goto L_0x00b9;
    L_0x00b8:
        return r1;
    L_0x00b9:
        r8 = r3;
        r1 = r4;
    L_0x00bb:
        r1.cancel(r8);
        return r9;
    L_0x00bf:
        r8 = new java.util.NoSuchElementException;	 Catch:{ Throwable -> 0x0075, all -> 0x0072 }
        r9 = "ReceiveChannel is empty.";
        r8.<init>(r9);	 Catch:{ Throwable -> 0x0075, all -> 0x0072 }
        r8 = (java.lang.Throwable) r8;	 Catch:{ Throwable -> 0x0075, all -> 0x0072 }
        throw r8;	 Catch:{ Throwable -> 0x0075, all -> 0x0072 }
    L_0x00c9:
        r0 = move-exception;
        r4 = r8;
        r3 = r9;
        r9 = r0;
        goto L_0x00d4;
    L_0x00ce:
        r9 = move-exception;
        r4 = r8;
        r8 = r9;
    L_0x00d1:
        throw r8;	 Catch:{ all -> 0x00d2 }
    L_0x00d2:
        r9 = move-exception;
        r3 = r8;
    L_0x00d4:
        r4.cancel(r3);
        throw r9;
    L_0x00d8:
        r9 = (kotlin.Result.Failure) r9;
        r8 = r9.exception;
        throw r8;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.first(kotlinx.coroutines.channels.ReceiveChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @org.jetbrains.annotations.Nullable
    @kotlinx.coroutines.ObsoleteCoroutinesApi
    public static final <E> java.lang.Object first(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r11, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super E, java.lang.Boolean> r12, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super E> r13) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:37)
	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:61)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1603198149.run(Unknown Source)
*/
        /*
        r0 = r13 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$first$3;
        if (r0 == 0) goto L_0x0014;
    L_0x0004:
        r0 = r13;
        r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$first$3) r0;
        r1 = r0.label;
        r2 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r1 = r1 & r2;
        if (r1 == 0) goto L_0x0014;
    L_0x000e:
        r13 = r0.label;
        r13 = r13 - r2;
        r0.label = r13;
        goto L_0x0019;
    L_0x0014:
        r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$first$3;
        r0.<init>(r13);
    L_0x0019:
        r13 = r0.result;
        r1 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        r2 = r0.label;
        r3 = 2;
        r4 = 1;
        switch(r2) {
            case 0: goto L_0x0088;
            case 1: goto L_0x005a;
            case 2: goto L_0x002e;
            default: goto L_0x0026;
        };
    L_0x0026:
        r11 = new java.lang.IllegalStateException;
        r12 = "call to 'resume' before 'invoke' with coroutine";
        r11.<init>(r12);
        throw r11;
    L_0x002e:
        r11 = r0.L$6;
        r11 = (kotlinx.coroutines.channels.ChannelIterator) r11;
        r12 = r0.L$5;
        r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12;
        r2 = r0.L$4;
        r2 = (java.lang.Throwable) r2;
        r5 = r0.L$3;
        r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5;
        r6 = r0.L$2;
        r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6;
        r7 = r0.L$1;
        r7 = (kotlin.jvm.functions.Function1) r7;
        r8 = r0.L$0;
        r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8;
        r9 = r13 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
        if (r9 != 0) goto L_0x0055;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
    L_0x004e:
        r10 = r12;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
        r12 = r11;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
        r11 = r5;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
        r5 = r1;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
        r1 = r10;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
        goto L_0x00d2;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
    L_0x0055:
        r13 = (kotlin.Result.Failure) r13;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
        r11 = r13.exception;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
        throw r11;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
    L_0x005a:
        r11 = r0.L$6;
        r11 = (kotlinx.coroutines.channels.ChannelIterator) r11;
        r12 = r0.L$5;
        r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12;
        r2 = r0.L$4;
        r2 = (java.lang.Throwable) r2;
        r5 = r0.L$3;
        r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5;
        r6 = r0.L$2;
        r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6;
        r7 = r0.L$1;
        r7 = (kotlin.jvm.functions.Function1) r7;
        r8 = r0.L$0;
        r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8;
        r9 = r13 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
        if (r9 != 0) goto L_0x007b;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
    L_0x007a:
        goto L_0x00b3;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
    L_0x007b:
        r13 = (kotlin.Result.Failure) r13;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
        r11 = r13.exception;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
        throw r11;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
    L_0x0080:
        r11 = move-exception;
        goto L_0x0108;
    L_0x0083:
        r11 = move-exception;
        r2 = r11;
        r11 = r5;
        goto L_0x0107;
    L_0x0088:
        r2 = r13 instanceof kotlin.Result.Failure;
        if (r2 != 0) goto L_0x0112;
    L_0x008c:
        r13 = 0;
        r2 = r13;
        r2 = (java.lang.Throwable) r2;
        r13 = r11.iterator();	 Catch:{ Throwable -> 0x0105 }
        r6 = r11;	 Catch:{ Throwable -> 0x0105 }
        r8 = r6;	 Catch:{ Throwable -> 0x0105 }
        r7 = r12;	 Catch:{ Throwable -> 0x0105 }
        r12 = r8;	 Catch:{ Throwable -> 0x0105 }
    L_0x0098:
        r0.L$0 = r8;	 Catch:{ Throwable -> 0x0105 }
        r0.L$1 = r7;	 Catch:{ Throwable -> 0x0105 }
        r0.L$2 = r6;	 Catch:{ Throwable -> 0x0105 }
        r0.L$3 = r11;	 Catch:{ Throwable -> 0x0105 }
        r0.L$4 = r2;	 Catch:{ Throwable -> 0x0105 }
        r0.L$5 = r12;	 Catch:{ Throwable -> 0x0105 }
        r0.L$6 = r13;	 Catch:{ Throwable -> 0x0105 }
        r0.label = r4;	 Catch:{ Throwable -> 0x0105 }
        r5 = r13.hasNext(r0);	 Catch:{ Throwable -> 0x0105 }
        if (r5 != r1) goto L_0x00af;
    L_0x00ae:
        return r1;
    L_0x00af:
        r10 = r5;
        r5 = r11;
        r11 = r13;
        r13 = r10;
    L_0x00b3:
        r13 = (java.lang.Boolean) r13;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
        r13 = r13.booleanValue();	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
        if (r13 == 0) goto L_0x00ec;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
    L_0x00bb:
        r0.L$0 = r8;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
        r0.L$1 = r7;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
        r0.L$2 = r6;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
        r0.L$3 = r5;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
        r0.L$4 = r2;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
        r0.L$5 = r12;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
        r0.L$6 = r11;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
        r0.label = r3;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
        r13 = r11.next(r0);	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
        if (r13 != r1) goto L_0x004e;
    L_0x00d1:
        return r1;
    L_0x00d2:
        r9 = r7.invoke(r13);	 Catch:{ Throwable -> 0x0105 }
        r9 = (java.lang.Boolean) r9;	 Catch:{ Throwable -> 0x0105 }
        r9 = r9.booleanValue();	 Catch:{ Throwable -> 0x0105 }
        if (r9 == 0) goto L_0x00e8;
    L_0x00de:
        kotlin.jvm.internal.InlineMarker.finallyStart(r3);
        r11.cancel(r2);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r3);
        return r13;
    L_0x00e8:
        r13 = r12;
        r12 = r1;
        r1 = r5;
        goto L_0x0098;
    L_0x00ec:
        r11 = kotlin.Unit.INSTANCE;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
        kotlin.jvm.internal.InlineMarker.finallyStart(r4);
        r5.cancel(r2);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r4);
        r11 = new java.util.NoSuchElementException;
        r12 = "ReceiveChannel contains no element matching the predicate.";
        r11.<init>(r12);
        r11 = (java.lang.Throwable) r11;
        throw r11;
    L_0x0101:
        r12 = move-exception;
        r5 = r11;
        r11 = r12;
        goto L_0x0108;
    L_0x0105:
        r12 = move-exception;
        r2 = r12;
    L_0x0107:
        throw r2;	 Catch:{ all -> 0x0101 }
    L_0x0108:
        kotlin.jvm.internal.InlineMarker.finallyStart(r4);
        r5.cancel(r2);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r4);
        throw r11;
    L_0x0112:
        r13 = (kotlin.Result.Failure) r13;
        r11 = r13.exception;
        throw r11;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.first(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @org.jetbrains.annotations.Nullable
    @kotlinx.coroutines.ObsoleteCoroutinesApi
    public static final <E> java.lang.Object firstOrNull(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r9, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super E> r10) {
        /*
        r0 = r10 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$firstOrNull$1;
        if (r0 == 0) goto L_0x0014;
    L_0x0004:
        r0 = r10;
        r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$firstOrNull$1) r0;
        r1 = r0.label;
        r2 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r1 = r1 & r2;
        if (r1 == 0) goto L_0x0014;
    L_0x000e:
        r10 = r0.label;
        r10 = r10 - r2;
        r0.label = r10;
        goto L_0x0019;
    L_0x0014:
        r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$firstOrNull$1;
        r0.<init>(r10);
    L_0x0019:
        r10 = r0.result;
        r1 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        r2 = r0.label;
        r3 = 0;
        switch(r2) {
            case 0: goto L_0x007e;
            case 1: goto L_0x0056;
            case 2: goto L_0x002d;
            default: goto L_0x0025;
        };
    L_0x0025:
        r9 = new java.lang.IllegalStateException;
        r10 = "call to 'resume' before 'invoke' with coroutine";
        r9.<init>(r10);
        throw r9;
    L_0x002d:
        r9 = r0.L$4;
        r9 = (kotlinx.coroutines.channels.ChannelIterator) r9;
        r9 = r0.L$3;
        r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9;
        r9 = r0.L$2;
        r9 = (java.lang.Throwable) r9;
        r1 = r0.L$1;
        r1 = (kotlinx.coroutines.channels.ReceiveChannel) r1;
        r0 = r0.L$0;
        r0 = (kotlinx.coroutines.channels.ReceiveChannel) r0;
        r0 = r10 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x0053 }
        if (r0 != 0) goto L_0x0049;
    L_0x0045:
        r4 = r9;
        r9 = r1;
        goto L_0x00c2;
    L_0x0049:
        r10 = (kotlin.Result.Failure) r10;	 Catch:{ Throwable -> 0x0053 }
        r10 = r10.exception;	 Catch:{ Throwable -> 0x0053 }
        throw r10;	 Catch:{ Throwable -> 0x0053 }
    L_0x004e:
        r10 = move-exception;
        r4 = r9;
        r9 = r1;
        goto L_0x00d0;
    L_0x0053:
        r9 = move-exception;
        goto L_0x00cf;
    L_0x0056:
        r9 = r0.L$4;
        r9 = (kotlinx.coroutines.channels.ChannelIterator) r9;
        r2 = r0.L$3;
        r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2;
        r4 = r0.L$2;
        r4 = (java.lang.Throwable) r4;
        r5 = r0.L$1;
        r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5;
        r6 = r0.L$0;
        r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6;
        r7 = r10 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x007b, all -> 0x0078 }
        if (r7 != 0) goto L_0x0073;
    L_0x006e:
        r8 = r2;
        r2 = r9;
        r9 = r5;
        r5 = r8;
        goto L_0x00a2;
    L_0x0073:
        r10 = (kotlin.Result.Failure) r10;	 Catch:{ Throwable -> 0x007b, all -> 0x0078 }
        r9 = r10.exception;	 Catch:{ Throwable -> 0x007b, all -> 0x0078 }
        throw r9;	 Catch:{ Throwable -> 0x007b, all -> 0x0078 }
    L_0x0078:
        r10 = move-exception;
        r9 = r5;
        goto L_0x00d0;
    L_0x007b:
        r9 = move-exception;
        r1 = r5;
        goto L_0x00cf;
    L_0x007e:
        r2 = r10 instanceof kotlin.Result.Failure;
        if (r2 != 0) goto L_0x00d4;
    L_0x0082:
        r10 = r3;
        r10 = (java.lang.Throwable) r10;
        r2 = r9.iterator();	 Catch:{ Throwable -> 0x00cc, all -> 0x00c8 }
        r0.L$0 = r9;	 Catch:{ Throwable -> 0x00cc, all -> 0x00c8 }
        r0.L$1 = r9;	 Catch:{ Throwable -> 0x00cc, all -> 0x00c8 }
        r0.L$2 = r10;	 Catch:{ Throwable -> 0x00cc, all -> 0x00c8 }
        r0.L$3 = r9;	 Catch:{ Throwable -> 0x00cc, all -> 0x00c8 }
        r0.L$4 = r2;	 Catch:{ Throwable -> 0x00cc, all -> 0x00c8 }
        r4 = 1;
        r0.label = r4;	 Catch:{ Throwable -> 0x00cc, all -> 0x00c8 }
        r4 = r2.hasNext(r0);	 Catch:{ Throwable -> 0x00cc, all -> 0x00c8 }
        if (r4 != r1) goto L_0x009d;
    L_0x009c:
        return r1;
    L_0x009d:
        r5 = r9;
        r6 = r5;
        r8 = r4;
        r4 = r10;
        r10 = r8;
    L_0x00a2:
        r10 = (java.lang.Boolean) r10;	 Catch:{ Throwable -> 0x00cc, all -> 0x00c6 }
        r10 = r10.booleanValue();	 Catch:{ Throwable -> 0x00cc, all -> 0x00c6 }
        if (r10 != 0) goto L_0x00ae;
    L_0x00aa:
        r9.cancel(r4);
        return r3;
    L_0x00ae:
        r0.L$0 = r6;	 Catch:{ Throwable -> 0x00cc, all -> 0x00c6 }
        r0.L$1 = r9;	 Catch:{ Throwable -> 0x00cc, all -> 0x00c6 }
        r0.L$2 = r4;	 Catch:{ Throwable -> 0x00cc, all -> 0x00c6 }
        r0.L$3 = r5;	 Catch:{ Throwable -> 0x00cc, all -> 0x00c6 }
        r0.L$4 = r2;	 Catch:{ Throwable -> 0x00cc, all -> 0x00c6 }
        r10 = 2;
        r0.label = r10;	 Catch:{ Throwable -> 0x00cc, all -> 0x00c6 }
        r10 = r2.next(r0);	 Catch:{ Throwable -> 0x00cc, all -> 0x00c6 }
        if (r10 != r1) goto L_0x00c2;
    L_0x00c1:
        return r1;
    L_0x00c2:
        r9.cancel(r4);
        return r10;
    L_0x00c6:
        r10 = move-exception;
        goto L_0x00d0;
    L_0x00c8:
        r0 = move-exception;
        r4 = r10;
        r10 = r0;
        goto L_0x00d0;
    L_0x00cc:
        r10 = move-exception;
        r1 = r9;
        r9 = r10;
    L_0x00cf:
        throw r9;	 Catch:{ all -> 0x004e }
    L_0x00d0:
        r9.cancel(r4);
        throw r10;
    L_0x00d4:
        r10 = (kotlin.Result.Failure) r10;
        r9 = r10.exception;
        throw r9;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.firstOrNull(kotlinx.coroutines.channels.ReceiveChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @org.jetbrains.annotations.Nullable
    @kotlinx.coroutines.ObsoleteCoroutinesApi
    public static final <E> java.lang.Object firstOrNull(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r12, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super E, java.lang.Boolean> r13, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super E> r14) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:37)
	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:61)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1603198149.run(Unknown Source)
*/
        /*
        r0 = r14 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$firstOrNull$3;
        if (r0 == 0) goto L_0x0014;
    L_0x0004:
        r0 = r14;
        r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$firstOrNull$3) r0;
        r1 = r0.label;
        r2 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r1 = r1 & r2;
        if (r1 == 0) goto L_0x0014;
    L_0x000e:
        r14 = r0.label;
        r14 = r14 - r2;
        r0.label = r14;
        goto L_0x0019;
    L_0x0014:
        r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$firstOrNull$3;
        r0.<init>(r14);
    L_0x0019:
        r14 = r0.result;
        r1 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        r2 = r0.label;
        r3 = 0;
        r4 = 2;
        r5 = 1;
        switch(r2) {
            case 0: goto L_0x0089;
            case 1: goto L_0x005b;
            case 2: goto L_0x002f;
            default: goto L_0x0027;
        };
    L_0x0027:
        r12 = new java.lang.IllegalStateException;
        r13 = "call to 'resume' before 'invoke' with coroutine";
        r12.<init>(r13);
        throw r12;
    L_0x002f:
        r12 = r0.L$6;
        r12 = (kotlinx.coroutines.channels.ChannelIterator) r12;
        r13 = r0.L$5;
        r13 = (kotlinx.coroutines.channels.ReceiveChannel) r13;
        r2 = r0.L$4;
        r2 = (java.lang.Throwable) r2;
        r6 = r0.L$3;
        r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6;
        r7 = r0.L$2;
        r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7;
        r8 = r0.L$1;
        r8 = (kotlin.jvm.functions.Function1) r8;
        r9 = r0.L$0;
        r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9;
        r10 = r14 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x0084, all -> 0x0081 }
        if (r10 != 0) goto L_0x0056;	 Catch:{ Throwable -> 0x0084, all -> 0x0081 }
    L_0x004f:
        r11 = r13;	 Catch:{ Throwable -> 0x0084, all -> 0x0081 }
        r13 = r12;	 Catch:{ Throwable -> 0x0084, all -> 0x0081 }
        r12 = r6;	 Catch:{ Throwable -> 0x0084, all -> 0x0081 }
        r6 = r1;	 Catch:{ Throwable -> 0x0084, all -> 0x0081 }
        r1 = r11;	 Catch:{ Throwable -> 0x0084, all -> 0x0081 }
        goto L_0x00d2;	 Catch:{ Throwable -> 0x0084, all -> 0x0081 }
    L_0x0056:
        r14 = (kotlin.Result.Failure) r14;	 Catch:{ Throwable -> 0x0084, all -> 0x0081 }
        r12 = r14.exception;	 Catch:{ Throwable -> 0x0084, all -> 0x0081 }
        throw r12;	 Catch:{ Throwable -> 0x0084, all -> 0x0081 }
    L_0x005b:
        r12 = r0.L$6;
        r12 = (kotlinx.coroutines.channels.ChannelIterator) r12;
        r13 = r0.L$5;
        r13 = (kotlinx.coroutines.channels.ReceiveChannel) r13;
        r2 = r0.L$4;
        r2 = (java.lang.Throwable) r2;
        r6 = r0.L$3;
        r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6;
        r7 = r0.L$2;
        r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7;
        r8 = r0.L$1;
        r8 = (kotlin.jvm.functions.Function1) r8;
        r9 = r0.L$0;
        r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9;
        r10 = r14 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x0084, all -> 0x0081 }
        if (r10 != 0) goto L_0x007c;	 Catch:{ Throwable -> 0x0084, all -> 0x0081 }
    L_0x007b:
        goto L_0x00b3;	 Catch:{ Throwable -> 0x0084, all -> 0x0081 }
    L_0x007c:
        r14 = (kotlin.Result.Failure) r14;	 Catch:{ Throwable -> 0x0084, all -> 0x0081 }
        r12 = r14.exception;	 Catch:{ Throwable -> 0x0084, all -> 0x0081 }
        throw r12;	 Catch:{ Throwable -> 0x0084, all -> 0x0081 }
    L_0x0081:
        r12 = move-exception;
        goto L_0x00ff;
    L_0x0084:
        r12 = move-exception;
        r2 = r12;
        r12 = r6;
        goto L_0x00fe;
    L_0x0089:
        r2 = r14 instanceof kotlin.Result.Failure;
        if (r2 != 0) goto L_0x0109;
    L_0x008d:
        r2 = r3;
        r2 = (java.lang.Throwable) r2;
        r14 = r12.iterator();	 Catch:{ Throwable -> 0x00fc }
        r7 = r12;	 Catch:{ Throwable -> 0x00fc }
        r9 = r7;	 Catch:{ Throwable -> 0x00fc }
        r8 = r13;	 Catch:{ Throwable -> 0x00fc }
        r13 = r9;	 Catch:{ Throwable -> 0x00fc }
    L_0x0098:
        r0.L$0 = r9;	 Catch:{ Throwable -> 0x00fc }
        r0.L$1 = r8;	 Catch:{ Throwable -> 0x00fc }
        r0.L$2 = r7;	 Catch:{ Throwable -> 0x00fc }
        r0.L$3 = r12;	 Catch:{ Throwable -> 0x00fc }
        r0.L$4 = r2;	 Catch:{ Throwable -> 0x00fc }
        r0.L$5 = r13;	 Catch:{ Throwable -> 0x00fc }
        r0.L$6 = r14;	 Catch:{ Throwable -> 0x00fc }
        r0.label = r5;	 Catch:{ Throwable -> 0x00fc }
        r6 = r14.hasNext(r0);	 Catch:{ Throwable -> 0x00fc }
        if (r6 != r1) goto L_0x00af;
    L_0x00ae:
        return r1;
    L_0x00af:
        r11 = r6;
        r6 = r12;
        r12 = r14;
        r14 = r11;
    L_0x00b3:
        r14 = (java.lang.Boolean) r14;	 Catch:{ Throwable -> 0x0084, all -> 0x0081 }
        r14 = r14.booleanValue();	 Catch:{ Throwable -> 0x0084, all -> 0x0081 }
        if (r14 == 0) goto L_0x00ec;	 Catch:{ Throwable -> 0x0084, all -> 0x0081 }
    L_0x00bb:
        r0.L$0 = r9;	 Catch:{ Throwable -> 0x0084, all -> 0x0081 }
        r0.L$1 = r8;	 Catch:{ Throwable -> 0x0084, all -> 0x0081 }
        r0.L$2 = r7;	 Catch:{ Throwable -> 0x0084, all -> 0x0081 }
        r0.L$3 = r6;	 Catch:{ Throwable -> 0x0084, all -> 0x0081 }
        r0.L$4 = r2;	 Catch:{ Throwable -> 0x0084, all -> 0x0081 }
        r0.L$5 = r13;	 Catch:{ Throwable -> 0x0084, all -> 0x0081 }
        r0.L$6 = r12;	 Catch:{ Throwable -> 0x0084, all -> 0x0081 }
        r0.label = r4;	 Catch:{ Throwable -> 0x0084, all -> 0x0081 }
        r14 = r12.next(r0);	 Catch:{ Throwable -> 0x0084, all -> 0x0081 }
        if (r14 != r1) goto L_0x004f;
    L_0x00d1:
        return r1;
    L_0x00d2:
        r10 = r8.invoke(r14);	 Catch:{ Throwable -> 0x00fc }
        r10 = (java.lang.Boolean) r10;	 Catch:{ Throwable -> 0x00fc }
        r10 = r10.booleanValue();	 Catch:{ Throwable -> 0x00fc }
        if (r10 == 0) goto L_0x00e8;
    L_0x00de:
        kotlin.jvm.internal.InlineMarker.finallyStart(r4);
        r12.cancel(r2);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r4);
        return r14;
    L_0x00e8:
        r14 = r13;
        r13 = r1;
        r1 = r6;
        goto L_0x0098;
    L_0x00ec:
        r12 = kotlin.Unit.INSTANCE;	 Catch:{ Throwable -> 0x0084, all -> 0x0081 }
        kotlin.jvm.internal.InlineMarker.finallyStart(r5);
        r6.cancel(r2);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r5);
        return r3;
    L_0x00f8:
        r13 = move-exception;
        r6 = r12;
        r12 = r13;
        goto L_0x00ff;
    L_0x00fc:
        r13 = move-exception;
        r2 = r13;
    L_0x00fe:
        throw r2;	 Catch:{ all -> 0x00f8 }
    L_0x00ff:
        kotlin.jvm.internal.InlineMarker.finallyStart(r5);
        r6.cancel(r2);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r5);
        throw r12;
    L_0x0109:
        r14 = (kotlin.Result.Failure) r14;
        r12 = r14.exception;
        throw r12;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.firstOrNull(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @org.jetbrains.annotations.Nullable
    @kotlinx.coroutines.ObsoleteCoroutinesApi
    public static final <E> java.lang.Object indexOf(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r12, E r13, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Integer> r14) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:37)
	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:61)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1603198149.run(Unknown Source)
*/
        /*
        r0 = r14 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$indexOf$1;
        if (r0 == 0) goto L_0x0014;
    L_0x0004:
        r0 = r14;
        r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$indexOf$1) r0;
        r1 = r0.label;
        r2 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r1 = r1 & r2;
        if (r1 == 0) goto L_0x0014;
    L_0x000e:
        r14 = r0.label;
        r14 = r14 - r2;
        r0.label = r14;
        goto L_0x0019;
    L_0x0014:
        r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$indexOf$1;
        r0.<init>(r14);
    L_0x0019:
        r14 = r0.result;
        r1 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        r2 = r0.label;
        r3 = 1;
        switch(r2) {
            case 0: goto L_0x0089;
            case 1: goto L_0x0059;
            case 2: goto L_0x002d;
            default: goto L_0x0025;
        };
    L_0x0025:
        r12 = new java.lang.IllegalStateException;
        r13 = "call to 'resume' before 'invoke' with coroutine";
        r12.<init>(r13);
        throw r12;
    L_0x002d:
        r12 = r0.L$7;
        r12 = (kotlinx.coroutines.channels.ChannelIterator) r12;
        r13 = r0.L$6;
        r13 = (kotlinx.coroutines.channels.ReceiveChannel) r13;
        r2 = r0.L$5;
        r2 = (java.lang.Throwable) r2;
        r4 = r0.L$4;
        r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4;
        r5 = r0.L$3;
        r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5;
        r6 = r0.L$2;
        r6 = (kotlin.jvm.internal.Ref.IntRef) r6;
        r7 = r0.L$1;
        r8 = r0.L$0;
        r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8;
        r9 = r14 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x0084, all -> 0x0081 }
        if (r9 != 0) goto L_0x0054;	 Catch:{ Throwable -> 0x0084, all -> 0x0081 }
    L_0x004f:
        r10 = r4;	 Catch:{ Throwable -> 0x0084, all -> 0x0081 }
        r4 = r12;	 Catch:{ Throwable -> 0x0084, all -> 0x0081 }
        r12 = r10;	 Catch:{ Throwable -> 0x0084, all -> 0x0081 }
        goto L_0x00e1;	 Catch:{ Throwable -> 0x0084, all -> 0x0081 }
    L_0x0054:
        r14 = (kotlin.Result.Failure) r14;	 Catch:{ Throwable -> 0x0084, all -> 0x0081 }
        r12 = r14.exception;	 Catch:{ Throwable -> 0x0084, all -> 0x0081 }
        throw r12;	 Catch:{ Throwable -> 0x0084, all -> 0x0081 }
    L_0x0059:
        r12 = r0.L$7;
        r12 = (kotlinx.coroutines.channels.ChannelIterator) r12;
        r13 = r0.L$6;
        r13 = (kotlinx.coroutines.channels.ReceiveChannel) r13;
        r2 = r0.L$5;
        r2 = (java.lang.Throwable) r2;
        r4 = r0.L$4;
        r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4;
        r5 = r0.L$3;
        r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5;
        r6 = r0.L$2;
        r6 = (kotlin.jvm.internal.Ref.IntRef) r6;
        r7 = r0.L$1;
        r8 = r0.L$0;
        r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8;
        r9 = r14 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x0084, all -> 0x0081 }
        if (r9 != 0) goto L_0x007c;	 Catch:{ Throwable -> 0x0084, all -> 0x0081 }
    L_0x007b:
        goto L_0x00bf;	 Catch:{ Throwable -> 0x0084, all -> 0x0081 }
    L_0x007c:
        r14 = (kotlin.Result.Failure) r14;	 Catch:{ Throwable -> 0x0084, all -> 0x0081 }
        r12 = r14.exception;	 Catch:{ Throwable -> 0x0084, all -> 0x0081 }
        throw r12;	 Catch:{ Throwable -> 0x0084, all -> 0x0081 }
    L_0x0081:
        r12 = move-exception;
        goto L_0x010a;
    L_0x0084:
        r12 = move-exception;
        r2 = r12;
        r12 = r4;
        goto L_0x0109;
    L_0x0089:
        r2 = r14 instanceof kotlin.Result.Failure;
        if (r2 != 0) goto L_0x010e;
    L_0x008d:
        r14 = new kotlin.jvm.internal.Ref$IntRef;
        r14.<init>();
        r2 = 0;
        r14.element = r2;
        r2 = 0;
        r2 = (java.lang.Throwable) r2;
        r4 = r12.iterator();	 Catch:{ Throwable -> 0x0107 }
        r5 = r12;	 Catch:{ Throwable -> 0x0107 }
        r8 = r5;	 Catch:{ Throwable -> 0x0107 }
        r7 = r13;	 Catch:{ Throwable -> 0x0107 }
        r13 = r8;	 Catch:{ Throwable -> 0x0107 }
    L_0x00a0:
        r0.L$0 = r8;	 Catch:{ Throwable -> 0x0107 }
        r0.L$1 = r7;	 Catch:{ Throwable -> 0x0107 }
        r0.L$2 = r14;	 Catch:{ Throwable -> 0x0107 }
        r0.L$3 = r5;	 Catch:{ Throwable -> 0x0107 }
        r0.L$4 = r12;	 Catch:{ Throwable -> 0x0107 }
        r0.L$5 = r2;	 Catch:{ Throwable -> 0x0107 }
        r0.L$6 = r13;	 Catch:{ Throwable -> 0x0107 }
        r0.L$7 = r4;	 Catch:{ Throwable -> 0x0107 }
        r0.label = r3;	 Catch:{ Throwable -> 0x0107 }
        r6 = r4.hasNext(r0);	 Catch:{ Throwable -> 0x0107 }
        if (r6 != r1) goto L_0x00b9;
    L_0x00b8:
        return r1;
    L_0x00b9:
        r10 = r4;
        r4 = r12;
        r12 = r10;
        r11 = r6;
        r6 = r14;
        r14 = r11;
    L_0x00bf:
        r14 = (java.lang.Boolean) r14;	 Catch:{ Throwable -> 0x0084, all -> 0x0081 }
        r14 = r14.booleanValue();	 Catch:{ Throwable -> 0x0084, all -> 0x0081 }
        if (r14 == 0) goto L_0x00f8;	 Catch:{ Throwable -> 0x0084, all -> 0x0081 }
    L_0x00c7:
        r0.L$0 = r8;	 Catch:{ Throwable -> 0x0084, all -> 0x0081 }
        r0.L$1 = r7;	 Catch:{ Throwable -> 0x0084, all -> 0x0081 }
        r0.L$2 = r6;	 Catch:{ Throwable -> 0x0084, all -> 0x0081 }
        r0.L$3 = r5;	 Catch:{ Throwable -> 0x0084, all -> 0x0081 }
        r0.L$4 = r4;	 Catch:{ Throwable -> 0x0084, all -> 0x0081 }
        r0.L$5 = r2;	 Catch:{ Throwable -> 0x0084, all -> 0x0081 }
        r0.L$6 = r13;	 Catch:{ Throwable -> 0x0084, all -> 0x0081 }
        r0.L$7 = r12;	 Catch:{ Throwable -> 0x0084, all -> 0x0081 }
        r14 = 2;	 Catch:{ Throwable -> 0x0084, all -> 0x0081 }
        r0.label = r14;	 Catch:{ Throwable -> 0x0084, all -> 0x0081 }
        r14 = r12.next(r0);	 Catch:{ Throwable -> 0x0084, all -> 0x0081 }
        if (r14 != r1) goto L_0x004f;
    L_0x00e0:
        return r1;
    L_0x00e1:
        r14 = kotlin.jvm.internal.Intrinsics.areEqual(r7, r14);	 Catch:{ Throwable -> 0x0107 }
        if (r14 == 0) goto L_0x00f1;	 Catch:{ Throwable -> 0x0107 }
    L_0x00e7:
        r13 = r6.element;	 Catch:{ Throwable -> 0x0107 }
        r13 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r13);	 Catch:{ Throwable -> 0x0107 }
        r12.cancel(r2);
        return r13;
    L_0x00f1:
        r14 = r6.element;	 Catch:{ Throwable -> 0x0107 }
        r14 = r14 + r3;	 Catch:{ Throwable -> 0x0107 }
        r6.element = r14;	 Catch:{ Throwable -> 0x0107 }
        r14 = r6;
        goto L_0x00a0;
    L_0x00f8:
        r12 = kotlin.Unit.INSTANCE;	 Catch:{ Throwable -> 0x0084, all -> 0x0081 }
        r4.cancel(r2);
        r12 = -1;
        r12 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r12);
        return r12;
    L_0x0103:
        r13 = move-exception;
        r4 = r12;
        r12 = r13;
        goto L_0x010a;
    L_0x0107:
        r13 = move-exception;
        r2 = r13;
    L_0x0109:
        throw r2;	 Catch:{ all -> 0x0103 }
    L_0x010a:
        r4.cancel(r2);
        throw r12;
    L_0x010e:
        r14 = (kotlin.Result.Failure) r14;
        r12 = r14.exception;
        throw r12;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.indexOf(kotlinx.coroutines.channels.ReceiveChannel, java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @org.jetbrains.annotations.Nullable
    @kotlinx.coroutines.ObsoleteCoroutinesApi
    public static final <E> java.lang.Object indexOfFirst(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r13, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super E, java.lang.Boolean> r14, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Integer> r15) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:37)
	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:61)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1603198149.run(Unknown Source)
*/
        /*
        r0 = r15 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$indexOfFirst$1;
        if (r0 == 0) goto L_0x0014;
    L_0x0004:
        r0 = r15;
        r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$indexOfFirst$1) r0;
        r1 = r0.label;
        r2 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r1 = r1 & r2;
        if (r1 == 0) goto L_0x0014;
    L_0x000e:
        r15 = r0.label;
        r15 = r15 - r2;
        r0.label = r15;
        goto L_0x0019;
    L_0x0014:
        r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$indexOfFirst$1;
        r0.<init>(r15);
    L_0x0019:
        r15 = r0.result;
        r1 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        r2 = r0.label;
        r3 = 2;
        r4 = 1;
        switch(r2) {
            case 0: goto L_0x008e;
            case 1: goto L_0x005c;
            case 2: goto L_0x002e;
            default: goto L_0x0026;
        };
    L_0x0026:
        r13 = new java.lang.IllegalStateException;
        r14 = "call to 'resume' before 'invoke' with coroutine";
        r13.<init>(r14);
        throw r13;
    L_0x002e:
        r13 = r0.L$7;
        r13 = (kotlinx.coroutines.channels.ChannelIterator) r13;
        r14 = r0.L$6;
        r14 = (kotlinx.coroutines.channels.ReceiveChannel) r14;
        r2 = r0.L$5;
        r2 = (java.lang.Throwable) r2;
        r5 = r0.L$4;
        r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5;
        r6 = r0.L$3;
        r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6;
        r7 = r0.L$2;
        r7 = (kotlin.jvm.internal.Ref.IntRef) r7;
        r8 = r0.L$1;
        r8 = (kotlin.jvm.functions.Function1) r8;
        r9 = r0.L$0;
        r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9;
        r10 = r15 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x0089, all -> 0x0086 }
        if (r10 != 0) goto L_0x0057;	 Catch:{ Throwable -> 0x0089, all -> 0x0086 }
    L_0x0052:
        r11 = r5;	 Catch:{ Throwable -> 0x0089, all -> 0x0086 }
        r5 = r13;	 Catch:{ Throwable -> 0x0089, all -> 0x0086 }
        r13 = r11;	 Catch:{ Throwable -> 0x0089, all -> 0x0086 }
        goto L_0x00e5;	 Catch:{ Throwable -> 0x0089, all -> 0x0086 }
    L_0x0057:
        r15 = (kotlin.Result.Failure) r15;	 Catch:{ Throwable -> 0x0089, all -> 0x0086 }
        r13 = r15.exception;	 Catch:{ Throwable -> 0x0089, all -> 0x0086 }
        throw r13;	 Catch:{ Throwable -> 0x0089, all -> 0x0086 }
    L_0x005c:
        r13 = r0.L$7;
        r13 = (kotlinx.coroutines.channels.ChannelIterator) r13;
        r14 = r0.L$6;
        r14 = (kotlinx.coroutines.channels.ReceiveChannel) r14;
        r2 = r0.L$5;
        r2 = (java.lang.Throwable) r2;
        r5 = r0.L$4;
        r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5;
        r6 = r0.L$3;
        r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6;
        r7 = r0.L$2;
        r7 = (kotlin.jvm.internal.Ref.IntRef) r7;
        r8 = r0.L$1;
        r8 = (kotlin.jvm.functions.Function1) r8;
        r9 = r0.L$0;
        r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9;
        r10 = r15 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x0089, all -> 0x0086 }
        if (r10 != 0) goto L_0x0081;	 Catch:{ Throwable -> 0x0089, all -> 0x0086 }
    L_0x0080:
        goto L_0x00c4;	 Catch:{ Throwable -> 0x0089, all -> 0x0086 }
    L_0x0081:
        r15 = (kotlin.Result.Failure) r15;	 Catch:{ Throwable -> 0x0089, all -> 0x0086 }
        r13 = r15.exception;	 Catch:{ Throwable -> 0x0089, all -> 0x0086 }
        throw r13;	 Catch:{ Throwable -> 0x0089, all -> 0x0086 }
    L_0x0086:
        r13 = move-exception;
        goto L_0x0120;
    L_0x0089:
        r13 = move-exception;
        r2 = r13;
        r13 = r5;
        goto L_0x011f;
    L_0x008e:
        r2 = r15 instanceof kotlin.Result.Failure;
        if (r2 != 0) goto L_0x012a;
    L_0x0092:
        r15 = new kotlin.jvm.internal.Ref$IntRef;
        r15.<init>();
        r2 = 0;
        r15.element = r2;
        r2 = 0;
        r2 = (java.lang.Throwable) r2;
        r5 = r13.iterator();	 Catch:{ Throwable -> 0x011d }
        r6 = r13;	 Catch:{ Throwable -> 0x011d }
        r9 = r6;	 Catch:{ Throwable -> 0x011d }
        r8 = r14;	 Catch:{ Throwable -> 0x011d }
        r14 = r9;	 Catch:{ Throwable -> 0x011d }
    L_0x00a5:
        r0.L$0 = r9;	 Catch:{ Throwable -> 0x011d }
        r0.L$1 = r8;	 Catch:{ Throwable -> 0x011d }
        r0.L$2 = r15;	 Catch:{ Throwable -> 0x011d }
        r0.L$3 = r6;	 Catch:{ Throwable -> 0x011d }
        r0.L$4 = r13;	 Catch:{ Throwable -> 0x011d }
        r0.L$5 = r2;	 Catch:{ Throwable -> 0x011d }
        r0.L$6 = r14;	 Catch:{ Throwable -> 0x011d }
        r0.L$7 = r5;	 Catch:{ Throwable -> 0x011d }
        r0.label = r4;	 Catch:{ Throwable -> 0x011d }
        r7 = r5.hasNext(r0);	 Catch:{ Throwable -> 0x011d }
        if (r7 != r1) goto L_0x00be;
    L_0x00bd:
        return r1;
    L_0x00be:
        r11 = r5;
        r5 = r13;
        r13 = r11;
        r12 = r7;
        r7 = r15;
        r15 = r12;
    L_0x00c4:
        r15 = (java.lang.Boolean) r15;	 Catch:{ Throwable -> 0x0089, all -> 0x0086 }
        r15 = r15.booleanValue();	 Catch:{ Throwable -> 0x0089, all -> 0x0086 }
        if (r15 == 0) goto L_0x0108;	 Catch:{ Throwable -> 0x0089, all -> 0x0086 }
    L_0x00cc:
        r0.L$0 = r9;	 Catch:{ Throwable -> 0x0089, all -> 0x0086 }
        r0.L$1 = r8;	 Catch:{ Throwable -> 0x0089, all -> 0x0086 }
        r0.L$2 = r7;	 Catch:{ Throwable -> 0x0089, all -> 0x0086 }
        r0.L$3 = r6;	 Catch:{ Throwable -> 0x0089, all -> 0x0086 }
        r0.L$4 = r5;	 Catch:{ Throwable -> 0x0089, all -> 0x0086 }
        r0.L$5 = r2;	 Catch:{ Throwable -> 0x0089, all -> 0x0086 }
        r0.L$6 = r14;	 Catch:{ Throwable -> 0x0089, all -> 0x0086 }
        r0.L$7 = r13;	 Catch:{ Throwable -> 0x0089, all -> 0x0086 }
        r0.label = r3;	 Catch:{ Throwable -> 0x0089, all -> 0x0086 }
        r15 = r13.next(r0);	 Catch:{ Throwable -> 0x0089, all -> 0x0086 }
        if (r15 != r1) goto L_0x0052;
    L_0x00e4:
        return r1;
    L_0x00e5:
        r15 = r8.invoke(r15);	 Catch:{ Throwable -> 0x011d }
        r15 = (java.lang.Boolean) r15;	 Catch:{ Throwable -> 0x011d }
        r15 = r15.booleanValue();	 Catch:{ Throwable -> 0x011d }
        if (r15 == 0) goto L_0x0101;	 Catch:{ Throwable -> 0x011d }
    L_0x00f1:
        r14 = r7.element;	 Catch:{ Throwable -> 0x011d }
        r14 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r14);	 Catch:{ Throwable -> 0x011d }
        kotlin.jvm.internal.InlineMarker.finallyStart(r3);
        r13.cancel(r2);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r3);
        return r14;
    L_0x0101:
        r15 = r7.element;	 Catch:{ Throwable -> 0x011d }
        r15 = r15 + r4;	 Catch:{ Throwable -> 0x011d }
        r7.element = r15;	 Catch:{ Throwable -> 0x011d }
        r15 = r7;
        goto L_0x00a5;
    L_0x0108:
        r13 = kotlin.Unit.INSTANCE;	 Catch:{ Throwable -> 0x0089, all -> 0x0086 }
        kotlin.jvm.internal.InlineMarker.finallyStart(r4);
        r5.cancel(r2);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r4);
        r13 = -1;
        r13 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r13);
        return r13;
    L_0x0119:
        r14 = move-exception;
        r5 = r13;
        r13 = r14;
        goto L_0x0120;
    L_0x011d:
        r14 = move-exception;
        r2 = r14;
    L_0x011f:
        throw r2;	 Catch:{ all -> 0x0119 }
    L_0x0120:
        kotlin.jvm.internal.InlineMarker.finallyStart(r4);
        r5.cancel(r2);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r4);
        throw r13;
    L_0x012a:
        r15 = (kotlin.Result.Failure) r15;
        r13 = r15.exception;
        throw r13;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.indexOfFirst(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @org.jetbrains.annotations.Nullable
    @kotlinx.coroutines.ObsoleteCoroutinesApi
    public static final <E> java.lang.Object indexOfLast(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r16, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super E, java.lang.Boolean> r17, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Integer> r18) {
        /*
        r0 = r18;
        r1 = r0 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$indexOfLast$1;
        if (r1 == 0) goto L_0x0016;
    L_0x0006:
        r1 = r0;
        r1 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$indexOfLast$1) r1;
        r2 = r1.label;
        r3 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r2 = r2 & r3;
        if (r2 == 0) goto L_0x0016;
    L_0x0010:
        r0 = r1.label;
        r0 = r0 - r3;
        r1.label = r0;
        goto L_0x001b;
    L_0x0016:
        r1 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$indexOfLast$1;
        r1.<init>(r0);
    L_0x001b:
        r0 = r1.result;
        r2 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        r3 = r1.label;
        r4 = 1;
        switch(r3) {
            case 0: goto L_0x009e;
            case 1: goto L_0x0064;
            case 2: goto L_0x002f;
            default: goto L_0x0027;
        };
    L_0x0027:
        r0 = new java.lang.IllegalStateException;
        r1 = "call to 'resume' before 'invoke' with coroutine";
        r0.<init>(r1);
        throw r0;
    L_0x002f:
        r3 = r1.L$8;
        r3 = (kotlinx.coroutines.channels.ChannelIterator) r3;
        r5 = r1.L$7;
        r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5;
        r6 = r1.L$6;
        r6 = (java.lang.Throwable) r6;
        r7 = r1.L$5;
        r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7;
        r8 = r1.L$4;
        r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8;
        r9 = r1.L$3;
        r9 = (kotlin.jvm.internal.Ref.IntRef) r9;
        r10 = r1.L$2;
        r10 = (kotlin.jvm.internal.Ref.IntRef) r10;
        r11 = r1.L$1;
        r11 = (kotlin.jvm.functions.Function1) r11;
        r12 = r1.L$0;
        r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12;
        r13 = r0 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x0099, all -> 0x0095 }
        if (r13 != 0) goto L_0x005f;
    L_0x0057:
        r14 = r9;
        r9 = r2;
        r2 = r7;
        r7 = r5;
        r5 = r3;
        r3 = r14;
        goto L_0x010e;
    L_0x005f:
        r0 = (kotlin.Result.Failure) r0;	 Catch:{ Throwable -> 0x0099, all -> 0x0095 }
        r0 = r0.exception;	 Catch:{ Throwable -> 0x0099, all -> 0x0095 }
        throw r0;	 Catch:{ Throwable -> 0x0099, all -> 0x0095 }
    L_0x0064:
        r3 = r1.L$8;
        r3 = (kotlinx.coroutines.channels.ChannelIterator) r3;
        r5 = r1.L$7;
        r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5;
        r6 = r1.L$6;
        r6 = (java.lang.Throwable) r6;
        r7 = r1.L$5;
        r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7;
        r8 = r1.L$4;
        r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8;
        r9 = r1.L$3;
        r9 = (kotlin.jvm.internal.Ref.IntRef) r9;
        r10 = r1.L$2;
        r10 = (kotlin.jvm.internal.Ref.IntRef) r10;
        r11 = r1.L$1;
        r11 = (kotlin.jvm.functions.Function1) r11;
        r12 = r1.L$0;
        r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12;
        r13 = r0 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x0099, all -> 0x0095 }
        if (r13 != 0) goto L_0x0090;
    L_0x008c:
        r14 = r7;
        r7 = r2;
        r2 = r14;
        goto L_0x00e5;
    L_0x0090:
        r0 = (kotlin.Result.Failure) r0;	 Catch:{ Throwable -> 0x0099, all -> 0x0095 }
        r0 = r0.exception;	 Catch:{ Throwable -> 0x0099, all -> 0x0095 }
        throw r0;	 Catch:{ Throwable -> 0x0099, all -> 0x0095 }
    L_0x0095:
        r0 = move-exception;
        r2 = r7;
        goto L_0x0143;
    L_0x0099:
        r0 = move-exception;
        r6 = r0;
        r2 = r7;
        goto L_0x0141;
    L_0x009e:
        r3 = r0 instanceof kotlin.Result.Failure;
        if (r3 != 0) goto L_0x014d;
    L_0x00a2:
        r0 = new kotlin.jvm.internal.Ref$IntRef;
        r0.<init>();
        r3 = -1;
        r0.element = r3;
        r3 = new kotlin.jvm.internal.Ref$IntRef;
        r3.<init>();
        r5 = 0;
        r3.element = r5;
        r5 = 0;
        r6 = r5;
        r6 = (java.lang.Throwable) r6;
        r5 = r16.iterator();	 Catch:{ Throwable -> 0x013d, all -> 0x0139 }
        r7 = r16;
        r8 = r7;
        r12 = r8;
        r11 = r17;
        r9 = r2;
        r2 = r12;
    L_0x00c2:
        r1.L$0 = r12;	 Catch:{ Throwable -> 0x0137 }
        r1.L$1 = r11;	 Catch:{ Throwable -> 0x0137 }
        r1.L$2 = r0;	 Catch:{ Throwable -> 0x0137 }
        r1.L$3 = r3;	 Catch:{ Throwable -> 0x0137 }
        r1.L$4 = r8;	 Catch:{ Throwable -> 0x0137 }
        r1.L$5 = r2;	 Catch:{ Throwable -> 0x0137 }
        r1.L$6 = r6;	 Catch:{ Throwable -> 0x0137 }
        r1.L$7 = r7;	 Catch:{ Throwable -> 0x0137 }
        r1.L$8 = r5;	 Catch:{ Throwable -> 0x0137 }
        r1.label = r4;	 Catch:{ Throwable -> 0x0137 }
        r10 = r5.hasNext(r1);	 Catch:{ Throwable -> 0x0137 }
        if (r10 != r9) goto L_0x00dd;
    L_0x00dc:
        return r9;
    L_0x00dd:
        r14 = r10;
        r10 = r0;
        r0 = r14;
        r15 = r9;
        r9 = r3;
        r3 = r5;
        r5 = r7;
        r7 = r15;
    L_0x00e5:
        r0 = (java.lang.Boolean) r0;	 Catch:{ Throwable -> 0x0137 }
        r0 = r0.booleanValue();	 Catch:{ Throwable -> 0x0137 }
        if (r0 == 0) goto L_0x0125;
    L_0x00ed:
        r1.L$0 = r12;	 Catch:{ Throwable -> 0x0137 }
        r1.L$1 = r11;	 Catch:{ Throwable -> 0x0137 }
        r1.L$2 = r10;	 Catch:{ Throwable -> 0x0137 }
        r1.L$3 = r9;	 Catch:{ Throwable -> 0x0137 }
        r1.L$4 = r8;	 Catch:{ Throwable -> 0x0137 }
        r1.L$5 = r2;	 Catch:{ Throwable -> 0x0137 }
        r1.L$6 = r6;	 Catch:{ Throwable -> 0x0137 }
        r1.L$7 = r5;	 Catch:{ Throwable -> 0x0137 }
        r1.L$8 = r3;	 Catch:{ Throwable -> 0x0137 }
        r0 = 2;
        r1.label = r0;	 Catch:{ Throwable -> 0x0137 }
        r0 = r3.next(r1);	 Catch:{ Throwable -> 0x0137 }
        if (r0 != r7) goto L_0x0109;
    L_0x0108:
        return r7;
    L_0x0109:
        r14 = r5;
        r5 = r3;
        r3 = r9;
        r9 = r7;
        r7 = r14;
    L_0x010e:
        r0 = r11.invoke(r0);	 Catch:{ Throwable -> 0x0137 }
        r0 = (java.lang.Boolean) r0;	 Catch:{ Throwable -> 0x0137 }
        r0 = r0.booleanValue();	 Catch:{ Throwable -> 0x0137 }
        if (r0 == 0) goto L_0x011e;
    L_0x011a:
        r0 = r3.element;	 Catch:{ Throwable -> 0x0137 }
        r10.element = r0;	 Catch:{ Throwable -> 0x0137 }
    L_0x011e:
        r0 = r3.element;	 Catch:{ Throwable -> 0x0137 }
        r0 = r0 + r4;
        r3.element = r0;	 Catch:{ Throwable -> 0x0137 }
        r0 = r10;
        goto L_0x00c2;
    L_0x0125:
        r0 = kotlin.Unit.INSTANCE;	 Catch:{ Throwable -> 0x0137 }
        kotlin.jvm.internal.InlineMarker.finallyStart(r4);
        r2.cancel(r6);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r4);
        r0 = r10.element;
        r0 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r0);
        return r0;
    L_0x0137:
        r0 = move-exception;
        goto L_0x0140;
    L_0x0139:
        r0 = move-exception;
        r2 = r16;
        goto L_0x0143;
    L_0x013d:
        r0 = move-exception;
        r2 = r16;
    L_0x0140:
        r6 = r0;
    L_0x0141:
        throw r6;	 Catch:{ all -> 0x0142 }
    L_0x0142:
        r0 = move-exception;
    L_0x0143:
        kotlin.jvm.internal.InlineMarker.finallyStart(r4);
        r2.cancel(r6);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r4);
        throw r0;
    L_0x014d:
        r0 = (kotlin.Result.Failure) r0;
        r0 = r0.exception;
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.indexOfLast(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @org.jetbrains.annotations.Nullable
    @kotlinx.coroutines.ObsoleteCoroutinesApi
    public static final <E> java.lang.Object last(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r9, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super E> r10) {
        /*
        r0 = r10 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$last$1;
        if (r0 == 0) goto L_0x0014;
    L_0x0004:
        r0 = r10;
        r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$last$1) r0;
        r1 = r0.label;
        r2 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r1 = r1 & r2;
        if (r1 == 0) goto L_0x0014;
    L_0x000e:
        r10 = r0.label;
        r10 = r10 - r2;
        r0.label = r10;
        goto L_0x0019;
    L_0x0014:
        r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$last$1;
        r0.<init>(r10);
    L_0x0019:
        r10 = r0.result;
        r1 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        r2 = r0.label;
        switch(r2) {
            case 0: goto L_0x00c5;
            case 1: goto L_0x009f;
            case 2: goto L_0x0080;
            case 3: goto L_0x004d;
            case 4: goto L_0x002c;
            default: goto L_0x0024;
        };
    L_0x0024:
        r9 = new java.lang.IllegalStateException;
        r10 = "call to 'resume' before 'invoke' with coroutine";
        r9.<init>(r10);
        throw r9;
    L_0x002c:
        r9 = r0.L$5;
        r9 = r0.L$4;
        r9 = (kotlinx.coroutines.channels.ChannelIterator) r9;
        r2 = r0.L$3;
        r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2;
        r3 = r0.L$2;
        r3 = (java.lang.Throwable) r3;
        r4 = r0.L$1;
        r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4;
        r5 = r0.L$0;
        r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5;
        r6 = r10 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x00c0, all -> 0x00bd }
        if (r6 != 0) goto L_0x0048;
    L_0x0046:
        goto L_0x0106;
    L_0x0048:
        r10 = (kotlin.Result.Failure) r10;	 Catch:{ Throwable -> 0x00c0, all -> 0x00bd }
        r9 = r10.exception;	 Catch:{ Throwable -> 0x00c0, all -> 0x00bd }
        throw r9;	 Catch:{ Throwable -> 0x00c0, all -> 0x00bd }
    L_0x004d:
        r9 = r0.L$5;
        r2 = r0.L$4;
        r2 = (kotlinx.coroutines.channels.ChannelIterator) r2;
        r3 = r0.L$3;
        r3 = (kotlinx.coroutines.channels.ReceiveChannel) r3;
        r4 = r0.L$2;
        r4 = (java.lang.Throwable) r4;
        r5 = r0.L$1;
        r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5;
        r6 = r0.L$0;
        r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6;
        r7 = r10 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x007b, all -> 0x0076 }
        if (r7 != 0) goto L_0x0071;
    L_0x0067:
        r8 = r1;
        r1 = r9;
        r9 = r2;
        r2 = r3;
        r3 = r4;
        r4 = r5;
        r5 = r6;
    L_0x006e:
        r6 = r8;
        goto L_0x0121;
    L_0x0071:
        r10 = (kotlin.Result.Failure) r10;	 Catch:{ Throwable -> 0x007b, all -> 0x0076 }
        r9 = r10.exception;	 Catch:{ Throwable -> 0x007b, all -> 0x0076 }
        throw r9;	 Catch:{ Throwable -> 0x007b, all -> 0x0076 }
    L_0x0076:
        r9 = move-exception;
        r3 = r4;
        r4 = r5;
        goto L_0x0156;
    L_0x007b:
        r9 = move-exception;
        r3 = r9;
        r9 = r5;
        goto L_0x0155;
    L_0x0080:
        r9 = r0.L$4;
        r9 = (kotlinx.coroutines.channels.ChannelIterator) r9;
        r2 = r0.L$3;
        r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2;
        r3 = r0.L$2;
        r3 = (java.lang.Throwable) r3;
        r4 = r0.L$1;
        r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4;
        r5 = r0.L$0;
        r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5;
        r6 = r10 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x00c0, all -> 0x00bd }
        if (r6 != 0) goto L_0x009a;
    L_0x0098:
        goto L_0x0106;
    L_0x009a:
        r10 = (kotlin.Result.Failure) r10;	 Catch:{ Throwable -> 0x00c0, all -> 0x00bd }
        r9 = r10.exception;	 Catch:{ Throwable -> 0x00c0, all -> 0x00bd }
        throw r9;	 Catch:{ Throwable -> 0x00c0, all -> 0x00bd }
    L_0x009f:
        r9 = r0.L$4;
        r9 = (kotlinx.coroutines.channels.ChannelIterator) r9;
        r2 = r0.L$3;
        r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2;
        r3 = r0.L$2;
        r3 = (java.lang.Throwable) r3;
        r4 = r0.L$1;
        r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4;
        r5 = r0.L$0;
        r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5;
        r6 = r10 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x00c0, all -> 0x00bd }
        if (r6 != 0) goto L_0x00b8;
    L_0x00b7:
        goto L_0x00ea;
    L_0x00b8:
        r10 = (kotlin.Result.Failure) r10;	 Catch:{ Throwable -> 0x00c0, all -> 0x00bd }
        r9 = r10.exception;	 Catch:{ Throwable -> 0x00c0, all -> 0x00bd }
        throw r9;	 Catch:{ Throwable -> 0x00c0, all -> 0x00bd }
    L_0x00bd:
        r9 = move-exception;
        goto L_0x0156;
    L_0x00c0:
        r9 = move-exception;
        r3 = r9;
        r9 = r4;
        goto L_0x0155;
    L_0x00c5:
        r2 = r10 instanceof kotlin.Result.Failure;
        if (r2 != 0) goto L_0x015a;
    L_0x00c9:
        r10 = 0;
        r3 = r10;
        r3 = (java.lang.Throwable) r3;
        r10 = r9.iterator();	 Catch:{ Throwable -> 0x0153 }
        r0.L$0 = r9;	 Catch:{ Throwable -> 0x0153 }
        r0.L$1 = r9;	 Catch:{ Throwable -> 0x0153 }
        r0.L$2 = r3;	 Catch:{ Throwable -> 0x0153 }
        r0.L$3 = r9;	 Catch:{ Throwable -> 0x0153 }
        r0.L$4 = r10;	 Catch:{ Throwable -> 0x0153 }
        r2 = 1;
        r0.label = r2;	 Catch:{ Throwable -> 0x0153 }
        r2 = r10.hasNext(r0);	 Catch:{ Throwable -> 0x0153 }
        if (r2 != r1) goto L_0x00e5;
    L_0x00e4:
        return r1;
    L_0x00e5:
        r4 = r9;
        r5 = r4;
        r9 = r10;
        r10 = r2;
        r2 = r5;
    L_0x00ea:
        r10 = (java.lang.Boolean) r10;	 Catch:{ Throwable -> 0x00c0, all -> 0x00bd }
        r10 = r10.booleanValue();	 Catch:{ Throwable -> 0x00c0, all -> 0x00bd }
        if (r10 == 0) goto L_0x0145;
    L_0x00f2:
        r0.L$0 = r5;	 Catch:{ Throwable -> 0x00c0, all -> 0x00bd }
        r0.L$1 = r4;	 Catch:{ Throwable -> 0x00c0, all -> 0x00bd }
        r0.L$2 = r3;	 Catch:{ Throwable -> 0x00c0, all -> 0x00bd }
        r0.L$3 = r2;	 Catch:{ Throwable -> 0x00c0, all -> 0x00bd }
        r0.L$4 = r9;	 Catch:{ Throwable -> 0x00c0, all -> 0x00bd }
        r10 = 2;
        r0.label = r10;	 Catch:{ Throwable -> 0x00c0, all -> 0x00bd }
        r10 = r9.next(r0);	 Catch:{ Throwable -> 0x00c0, all -> 0x00bd }
        if (r10 != r1) goto L_0x0106;
    L_0x0105:
        return r1;
    L_0x0106:
        r0.L$0 = r5;	 Catch:{ Throwable -> 0x00c0, all -> 0x00bd }
        r0.L$1 = r4;	 Catch:{ Throwable -> 0x00c0, all -> 0x00bd }
        r0.L$2 = r3;	 Catch:{ Throwable -> 0x00c0, all -> 0x00bd }
        r0.L$3 = r2;	 Catch:{ Throwable -> 0x00c0, all -> 0x00bd }
        r0.L$4 = r9;	 Catch:{ Throwable -> 0x00c0, all -> 0x00bd }
        r0.L$5 = r10;	 Catch:{ Throwable -> 0x00c0, all -> 0x00bd }
        r6 = 3;
        r0.label = r6;	 Catch:{ Throwable -> 0x00c0, all -> 0x00bd }
        r6 = r9.hasNext(r0);	 Catch:{ Throwable -> 0x00c0, all -> 0x00bd }
        if (r6 != r1) goto L_0x011c;
    L_0x011b:
        return r1;
    L_0x011c:
        r8 = r1;
        r1 = r10;
        r10 = r6;
        goto L_0x006e;
    L_0x0121:
        r10 = (java.lang.Boolean) r10;	 Catch:{ Throwable -> 0x00c0, all -> 0x00bd }
        r10 = r10.booleanValue();	 Catch:{ Throwable -> 0x00c0, all -> 0x00bd }
        if (r10 == 0) goto L_0x0141;
    L_0x0129:
        r0.L$0 = r5;	 Catch:{ Throwable -> 0x00c0, all -> 0x00bd }
        r0.L$1 = r4;	 Catch:{ Throwable -> 0x00c0, all -> 0x00bd }
        r0.L$2 = r3;	 Catch:{ Throwable -> 0x00c0, all -> 0x00bd }
        r0.L$3 = r2;	 Catch:{ Throwable -> 0x00c0, all -> 0x00bd }
        r0.L$4 = r9;	 Catch:{ Throwable -> 0x00c0, all -> 0x00bd }
        r0.L$5 = r1;	 Catch:{ Throwable -> 0x00c0, all -> 0x00bd }
        r10 = 4;
        r0.label = r10;	 Catch:{ Throwable -> 0x00c0, all -> 0x00bd }
        r10 = r9.next(r0);	 Catch:{ Throwable -> 0x00c0, all -> 0x00bd }
        if (r10 != r6) goto L_0x013f;
    L_0x013e:
        return r6;
    L_0x013f:
        r1 = r6;
        goto L_0x0106;
    L_0x0141:
        r4.cancel(r3);
        return r1;
    L_0x0145:
        r9 = new java.util.NoSuchElementException;	 Catch:{ Throwable -> 0x00c0, all -> 0x00bd }
        r10 = "ReceiveChannel is empty.";
        r9.<init>(r10);	 Catch:{ Throwable -> 0x00c0, all -> 0x00bd }
        r9 = (java.lang.Throwable) r9;	 Catch:{ Throwable -> 0x00c0, all -> 0x00bd }
        throw r9;	 Catch:{ Throwable -> 0x00c0, all -> 0x00bd }
    L_0x014f:
        r10 = move-exception;
        r4 = r9;
        r9 = r10;
        goto L_0x0156;
    L_0x0153:
        r10 = move-exception;
        r3 = r10;
    L_0x0155:
        throw r3;	 Catch:{ all -> 0x014f }
    L_0x0156:
        r4.cancel(r3);
        throw r9;
    L_0x015a:
        r10 = (kotlin.Result.Failure) r10;
        r9 = r10.exception;
        throw r9;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.last(kotlinx.coroutines.channels.ReceiveChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @org.jetbrains.annotations.Nullable
    @kotlinx.coroutines.ObsoleteCoroutinesApi
    public static final <E> java.lang.Object last(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r16, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super E, java.lang.Boolean> r17, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super E> r18) {
        /*
        r0 = r18;
        r1 = r0 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$last$3;
        if (r1 == 0) goto L_0x0016;
    L_0x0006:
        r1 = r0;
        r1 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$last$3) r1;
        r2 = r1.label;
        r3 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r2 = r2 & r3;
        if (r2 == 0) goto L_0x0016;
    L_0x0010:
        r0 = r1.label;
        r0 = r0 - r3;
        r1.label = r0;
        goto L_0x001b;
    L_0x0016:
        r1 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$last$3;
        r1.<init>(r0);
    L_0x001b:
        r0 = r1.result;
        r2 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        r3 = r1.label;
        r4 = 1;
        switch(r3) {
            case 0: goto L_0x009d;
            case 1: goto L_0x0063;
            case 2: goto L_0x002f;
            default: goto L_0x0027;
        };
    L_0x0027:
        r0 = new java.lang.IllegalStateException;
        r1 = "call to 'resume' before 'invoke' with coroutine";
        r0.<init>(r1);
        throw r0;
    L_0x002f:
        r3 = r1.L$8;
        r3 = (kotlinx.coroutines.channels.ChannelIterator) r3;
        r5 = r1.L$7;
        r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5;
        r6 = r1.L$6;
        r6 = (java.lang.Throwable) r6;
        r7 = r1.L$5;
        r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7;
        r8 = r1.L$4;
        r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8;
        r9 = r1.L$3;
        r9 = (kotlin.jvm.internal.Ref.BooleanRef) r9;
        r10 = r1.L$2;
        r10 = (kotlin.jvm.internal.Ref.ObjectRef) r10;
        r11 = r1.L$1;
        r11 = (kotlin.jvm.functions.Function1) r11;
        r12 = r1.L$0;
        r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12;
        r13 = r0 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x0098, all -> 0x0094 }
        if (r13 != 0) goto L_0x005e;
    L_0x0057:
        r14 = r9;
        r9 = r2;
        r2 = r7;
        r7 = r5;
        r5 = r14;
        goto L_0x010a;
    L_0x005e:
        r0 = (kotlin.Result.Failure) r0;	 Catch:{ Throwable -> 0x0098, all -> 0x0094 }
        r0 = r0.exception;	 Catch:{ Throwable -> 0x0098, all -> 0x0094 }
        throw r0;	 Catch:{ Throwable -> 0x0098, all -> 0x0094 }
    L_0x0063:
        r3 = r1.L$8;
        r3 = (kotlinx.coroutines.channels.ChannelIterator) r3;
        r5 = r1.L$7;
        r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5;
        r6 = r1.L$6;
        r6 = (java.lang.Throwable) r6;
        r7 = r1.L$5;
        r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7;
        r8 = r1.L$4;
        r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8;
        r9 = r1.L$3;
        r9 = (kotlin.jvm.internal.Ref.BooleanRef) r9;
        r10 = r1.L$2;
        r10 = (kotlin.jvm.internal.Ref.ObjectRef) r10;
        r11 = r1.L$1;
        r11 = (kotlin.jvm.functions.Function1) r11;
        r12 = r1.L$0;
        r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12;
        r13 = r0 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x0098, all -> 0x0094 }
        if (r13 != 0) goto L_0x008f;
    L_0x008b:
        r14 = r7;
        r7 = r2;
        r2 = r14;
        goto L_0x00e2;
    L_0x008f:
        r0 = (kotlin.Result.Failure) r0;	 Catch:{ Throwable -> 0x0098, all -> 0x0094 }
        r0 = r0.exception;	 Catch:{ Throwable -> 0x0098, all -> 0x0094 }
        throw r0;	 Catch:{ Throwable -> 0x0098, all -> 0x0094 }
    L_0x0094:
        r0 = move-exception;
        r2 = r7;
        goto L_0x0144;
    L_0x0098:
        r0 = move-exception;
        r6 = r0;
        r2 = r7;
        goto L_0x0142;
    L_0x009d:
        r3 = r0 instanceof kotlin.Result.Failure;
        if (r3 != 0) goto L_0x014e;
    L_0x00a1:
        r0 = new kotlin.jvm.internal.Ref$ObjectRef;
        r0.<init>();
        r3 = 0;
        r0.element = r3;
        r5 = new kotlin.jvm.internal.Ref$BooleanRef;
        r5.<init>();
        r6 = 0;
        r5.element = r6;
        r6 = r3;
        r6 = (java.lang.Throwable) r6;
        r3 = r16.iterator();	 Catch:{ Throwable -> 0x013e, all -> 0x013a }
        r7 = r16;
        r8 = r7;
        r12 = r8;
        r11 = r17;
        r9 = r2;
        r2 = r12;
    L_0x00c0:
        r1.L$0 = r12;	 Catch:{ Throwable -> 0x0138 }
        r1.L$1 = r11;	 Catch:{ Throwable -> 0x0138 }
        r1.L$2 = r0;	 Catch:{ Throwable -> 0x0138 }
        r1.L$3 = r5;	 Catch:{ Throwable -> 0x0138 }
        r1.L$4 = r8;	 Catch:{ Throwable -> 0x0138 }
        r1.L$5 = r2;	 Catch:{ Throwable -> 0x0138 }
        r1.L$6 = r6;	 Catch:{ Throwable -> 0x0138 }
        r1.L$7 = r7;	 Catch:{ Throwable -> 0x0138 }
        r1.L$8 = r3;	 Catch:{ Throwable -> 0x0138 }
        r1.label = r4;	 Catch:{ Throwable -> 0x0138 }
        r10 = r3.hasNext(r1);	 Catch:{ Throwable -> 0x0138 }
        if (r10 != r9) goto L_0x00db;
    L_0x00da:
        return r9;
    L_0x00db:
        r14 = r10;
        r10 = r0;
        r0 = r14;
        r15 = r9;
        r9 = r5;
        r5 = r7;
        r7 = r15;
    L_0x00e2:
        r0 = (java.lang.Boolean) r0;	 Catch:{ Throwable -> 0x0138 }
        r0 = r0.booleanValue();	 Catch:{ Throwable -> 0x0138 }
        if (r0 == 0) goto L_0x011c;
    L_0x00ea:
        r1.L$0 = r12;	 Catch:{ Throwable -> 0x0138 }
        r1.L$1 = r11;	 Catch:{ Throwable -> 0x0138 }
        r1.L$2 = r10;	 Catch:{ Throwable -> 0x0138 }
        r1.L$3 = r9;	 Catch:{ Throwable -> 0x0138 }
        r1.L$4 = r8;	 Catch:{ Throwable -> 0x0138 }
        r1.L$5 = r2;	 Catch:{ Throwable -> 0x0138 }
        r1.L$6 = r6;	 Catch:{ Throwable -> 0x0138 }
        r1.L$7 = r5;	 Catch:{ Throwable -> 0x0138 }
        r1.L$8 = r3;	 Catch:{ Throwable -> 0x0138 }
        r0 = 2;
        r1.label = r0;	 Catch:{ Throwable -> 0x0138 }
        r0 = r3.next(r1);	 Catch:{ Throwable -> 0x0138 }
        if (r0 != r7) goto L_0x0106;
    L_0x0105:
        return r7;
    L_0x0106:
        r14 = r7;
        r7 = r5;
        r5 = r9;
        r9 = r14;
    L_0x010a:
        r13 = r11.invoke(r0);	 Catch:{ Throwable -> 0x0138 }
        r13 = (java.lang.Boolean) r13;	 Catch:{ Throwable -> 0x0138 }
        r13 = r13.booleanValue();	 Catch:{ Throwable -> 0x0138 }
        if (r13 == 0) goto L_0x011a;
    L_0x0116:
        r10.element = r0;	 Catch:{ Throwable -> 0x0138 }
        r5.element = r4;	 Catch:{ Throwable -> 0x0138 }
    L_0x011a:
        r0 = r10;
        goto L_0x00c0;
    L_0x011c:
        r0 = kotlin.Unit.INSTANCE;	 Catch:{ Throwable -> 0x0138 }
        kotlin.jvm.internal.InlineMarker.finallyStart(r4);
        r2.cancel(r6);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r4);
        r0 = r9.element;
        if (r0 == 0) goto L_0x012e;
    L_0x012b:
        r0 = r10.element;
        return r0;
    L_0x012e:
        r0 = new java.util.NoSuchElementException;
        r1 = "ReceiveChannel contains no element matching the predicate.";
        r0.<init>(r1);
        r0 = (java.lang.Throwable) r0;
        throw r0;
    L_0x0138:
        r0 = move-exception;
        goto L_0x0141;
    L_0x013a:
        r0 = move-exception;
        r2 = r16;
        goto L_0x0144;
    L_0x013e:
        r0 = move-exception;
        r2 = r16;
    L_0x0141:
        r6 = r0;
    L_0x0142:
        throw r6;	 Catch:{ all -> 0x0143 }
    L_0x0143:
        r0 = move-exception;
    L_0x0144:
        kotlin.jvm.internal.InlineMarker.finallyStart(r4);
        r2.cancel(r6);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r4);
        throw r0;
    L_0x014e:
        r0 = (kotlin.Result.Failure) r0;
        r0 = r0.exception;
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.last(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @org.jetbrains.annotations.Nullable
    @kotlinx.coroutines.ObsoleteCoroutinesApi
    public static final <E> java.lang.Object lastIndexOf(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r16, E r17, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Integer> r18) {
        /*
        r0 = r18;
        r1 = r0 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$lastIndexOf$1;
        if (r1 == 0) goto L_0x0016;
    L_0x0006:
        r1 = r0;
        r1 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$lastIndexOf$1) r1;
        r2 = r1.label;
        r3 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r2 = r2 & r3;
        if (r2 == 0) goto L_0x0016;
    L_0x0010:
        r0 = r1.label;
        r0 = r0 - r3;
        r1.label = r0;
        goto L_0x001b;
    L_0x0016:
        r1 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$lastIndexOf$1;
        r1.<init>(r0);
    L_0x001b:
        r0 = r1.result;
        r2 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        r3 = r1.label;
        r4 = 1;
        switch(r3) {
            case 0: goto L_0x009a;
            case 1: goto L_0x0062;
            case 2: goto L_0x002f;
            default: goto L_0x0027;
        };
    L_0x0027:
        r0 = new java.lang.IllegalStateException;
        r1 = "call to 'resume' before 'invoke' with coroutine";
        r0.<init>(r1);
        throw r0;
    L_0x002f:
        r3 = r1.L$8;
        r3 = (kotlinx.coroutines.channels.ChannelIterator) r3;
        r5 = r1.L$7;
        r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5;
        r6 = r1.L$6;
        r6 = (java.lang.Throwable) r6;
        r7 = r1.L$5;
        r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7;
        r8 = r1.L$4;
        r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8;
        r9 = r1.L$3;
        r9 = (kotlin.jvm.internal.Ref.IntRef) r9;
        r10 = r1.L$2;
        r10 = (kotlin.jvm.internal.Ref.IntRef) r10;
        r11 = r1.L$1;
        r12 = r1.L$0;
        r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12;
        r13 = r0 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x0095, all -> 0x0091 }
        if (r13 != 0) goto L_0x005d;
    L_0x0055:
        r14 = r9;
        r9 = r2;
        r2 = r7;
        r7 = r5;
        r5 = r3;
        r3 = r14;
        goto L_0x010a;
    L_0x005d:
        r0 = (kotlin.Result.Failure) r0;	 Catch:{ Throwable -> 0x0095, all -> 0x0091 }
        r0 = r0.exception;	 Catch:{ Throwable -> 0x0095, all -> 0x0091 }
        throw r0;	 Catch:{ Throwable -> 0x0095, all -> 0x0091 }
    L_0x0062:
        r3 = r1.L$8;
        r3 = (kotlinx.coroutines.channels.ChannelIterator) r3;
        r5 = r1.L$7;
        r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5;
        r6 = r1.L$6;
        r6 = (java.lang.Throwable) r6;
        r7 = r1.L$5;
        r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7;
        r8 = r1.L$4;
        r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8;
        r9 = r1.L$3;
        r9 = (kotlin.jvm.internal.Ref.IntRef) r9;
        r10 = r1.L$2;
        r10 = (kotlin.jvm.internal.Ref.IntRef) r10;
        r11 = r1.L$1;
        r12 = r1.L$0;
        r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12;
        r13 = r0 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x0095, all -> 0x0091 }
        if (r13 != 0) goto L_0x008c;
    L_0x0088:
        r14 = r7;
        r7 = r2;
        r2 = r14;
        goto L_0x00e1;
    L_0x008c:
        r0 = (kotlin.Result.Failure) r0;	 Catch:{ Throwable -> 0x0095, all -> 0x0091 }
        r0 = r0.exception;	 Catch:{ Throwable -> 0x0095, all -> 0x0091 }
        throw r0;	 Catch:{ Throwable -> 0x0095, all -> 0x0091 }
    L_0x0091:
        r0 = move-exception;
        r2 = r7;
        goto L_0x0133;
    L_0x0095:
        r0 = move-exception;
        r6 = r0;
        r2 = r7;
        goto L_0x0131;
    L_0x009a:
        r3 = r0 instanceof kotlin.Result.Failure;
        if (r3 != 0) goto L_0x0137;
    L_0x009e:
        r0 = new kotlin.jvm.internal.Ref$IntRef;
        r0.<init>();
        r3 = -1;
        r0.element = r3;
        r3 = new kotlin.jvm.internal.Ref$IntRef;
        r3.<init>();
        r5 = 0;
        r3.element = r5;
        r5 = 0;
        r6 = r5;
        r6 = (java.lang.Throwable) r6;
        r5 = r16.iterator();	 Catch:{ Throwable -> 0x012d, all -> 0x0129 }
        r7 = r16;
        r8 = r7;
        r12 = r8;
        r11 = r17;
        r9 = r2;
        r2 = r12;
    L_0x00be:
        r1.L$0 = r12;	 Catch:{ Throwable -> 0x0127 }
        r1.L$1 = r11;	 Catch:{ Throwable -> 0x0127 }
        r1.L$2 = r0;	 Catch:{ Throwable -> 0x0127 }
        r1.L$3 = r3;	 Catch:{ Throwable -> 0x0127 }
        r1.L$4 = r8;	 Catch:{ Throwable -> 0x0127 }
        r1.L$5 = r2;	 Catch:{ Throwable -> 0x0127 }
        r1.L$6 = r6;	 Catch:{ Throwable -> 0x0127 }
        r1.L$7 = r7;	 Catch:{ Throwable -> 0x0127 }
        r1.L$8 = r5;	 Catch:{ Throwable -> 0x0127 }
        r1.label = r4;	 Catch:{ Throwable -> 0x0127 }
        r10 = r5.hasNext(r1);	 Catch:{ Throwable -> 0x0127 }
        if (r10 != r9) goto L_0x00d9;
    L_0x00d8:
        return r9;
    L_0x00d9:
        r14 = r10;
        r10 = r0;
        r0 = r14;
        r15 = r9;
        r9 = r3;
        r3 = r5;
        r5 = r7;
        r7 = r15;
    L_0x00e1:
        r0 = (java.lang.Boolean) r0;	 Catch:{ Throwable -> 0x0127 }
        r0 = r0.booleanValue();	 Catch:{ Throwable -> 0x0127 }
        if (r0 == 0) goto L_0x011b;
    L_0x00e9:
        r1.L$0 = r12;	 Catch:{ Throwable -> 0x0127 }
        r1.L$1 = r11;	 Catch:{ Throwable -> 0x0127 }
        r1.L$2 = r10;	 Catch:{ Throwable -> 0x0127 }
        r1.L$3 = r9;	 Catch:{ Throwable -> 0x0127 }
        r1.L$4 = r8;	 Catch:{ Throwable -> 0x0127 }
        r1.L$5 = r2;	 Catch:{ Throwable -> 0x0127 }
        r1.L$6 = r6;	 Catch:{ Throwable -> 0x0127 }
        r1.L$7 = r5;	 Catch:{ Throwable -> 0x0127 }
        r1.L$8 = r3;	 Catch:{ Throwable -> 0x0127 }
        r0 = 2;
        r1.label = r0;	 Catch:{ Throwable -> 0x0127 }
        r0 = r3.next(r1);	 Catch:{ Throwable -> 0x0127 }
        if (r0 != r7) goto L_0x0105;
    L_0x0104:
        return r7;
    L_0x0105:
        r14 = r5;
        r5 = r3;
        r3 = r9;
        r9 = r7;
        r7 = r14;
    L_0x010a:
        r0 = kotlin.jvm.internal.Intrinsics.areEqual(r11, r0);	 Catch:{ Throwable -> 0x0127 }
        if (r0 == 0) goto L_0x0114;
    L_0x0110:
        r0 = r3.element;	 Catch:{ Throwable -> 0x0127 }
        r10.element = r0;	 Catch:{ Throwable -> 0x0127 }
    L_0x0114:
        r0 = r3.element;	 Catch:{ Throwable -> 0x0127 }
        r0 = r0 + r4;
        r3.element = r0;	 Catch:{ Throwable -> 0x0127 }
        r0 = r10;
        goto L_0x00be;
    L_0x011b:
        r0 = kotlin.Unit.INSTANCE;	 Catch:{ Throwable -> 0x0127 }
        r2.cancel(r6);
        r0 = r10.element;
        r0 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r0);
        return r0;
    L_0x0127:
        r0 = move-exception;
        goto L_0x0130;
    L_0x0129:
        r0 = move-exception;
        r2 = r16;
        goto L_0x0133;
    L_0x012d:
        r0 = move-exception;
        r2 = r16;
    L_0x0130:
        r6 = r0;
    L_0x0131:
        throw r6;	 Catch:{ all -> 0x0132 }
    L_0x0132:
        r0 = move-exception;
    L_0x0133:
        r2.cancel(r6);
        throw r0;
    L_0x0137:
        r0 = (kotlin.Result.Failure) r0;
        r0 = r0.exception;
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.lastIndexOf(kotlinx.coroutines.channels.ReceiveChannel, java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @org.jetbrains.annotations.Nullable
    @kotlinx.coroutines.ObsoleteCoroutinesApi
    public static final <E> java.lang.Object lastOrNull(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r9, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super E> r10) {
        /*
        r0 = r10 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$lastOrNull$1;
        if (r0 == 0) goto L_0x0014;
    L_0x0004:
        r0 = r10;
        r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$lastOrNull$1) r0;
        r1 = r0.label;
        r2 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r1 = r1 & r2;
        if (r1 == 0) goto L_0x0014;
    L_0x000e:
        r10 = r0.label;
        r10 = r10 - r2;
        r0.label = r10;
        goto L_0x0019;
    L_0x0014:
        r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$lastOrNull$1;
        r0.<init>(r10);
    L_0x0019:
        r10 = r0.result;
        r1 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        r2 = r0.label;
        r3 = 0;
        switch(r2) {
            case 0: goto L_0x00cb;
            case 1: goto L_0x009e;
            case 2: goto L_0x0077;
            case 3: goto L_0x004e;
            case 4: goto L_0x002d;
            default: goto L_0x0025;
        };
    L_0x0025:
        r9 = new java.lang.IllegalStateException;
        r10 = "call to 'resume' before 'invoke' with coroutine";
        r9.<init>(r10);
        throw r9;
    L_0x002d:
        r9 = r0.L$5;
        r9 = r0.L$4;
        r9 = (kotlinx.coroutines.channels.ChannelIterator) r9;
        r2 = r0.L$3;
        r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2;
        r3 = r0.L$2;
        r3 = (java.lang.Throwable) r3;
        r4 = r0.L$1;
        r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4;
        r5 = r0.L$0;
        r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5;
        r6 = r10 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x0099, all -> 0x0096 }
        if (r6 != 0) goto L_0x0049;
    L_0x0047:
        goto L_0x0113;
    L_0x0049:
        r10 = (kotlin.Result.Failure) r10;	 Catch:{ Throwable -> 0x0099, all -> 0x0096 }
        r9 = r10.exception;	 Catch:{ Throwable -> 0x0099, all -> 0x0096 }
        throw r9;	 Catch:{ Throwable -> 0x0099, all -> 0x0096 }
    L_0x004e:
        r9 = r0.L$5;
        r2 = r0.L$4;
        r2 = (kotlinx.coroutines.channels.ChannelIterator) r2;
        r3 = r0.L$3;
        r3 = (kotlinx.coroutines.channels.ReceiveChannel) r3;
        r4 = r0.L$2;
        r4 = (java.lang.Throwable) r4;
        r5 = r0.L$1;
        r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5;
        r6 = r0.L$0;
        r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6;
        r7 = r10 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x00c6, all -> 0x00c1 }
        if (r7 != 0) goto L_0x0072;
    L_0x0068:
        r8 = r1;
        r1 = r9;
        r9 = r2;
        r2 = r3;
        r3 = r4;
        r4 = r5;
        r5 = r6;
    L_0x006f:
        r6 = r8;
        goto L_0x012e;
    L_0x0072:
        r10 = (kotlin.Result.Failure) r10;	 Catch:{ Throwable -> 0x00c6, all -> 0x00c1 }
        r9 = r10.exception;	 Catch:{ Throwable -> 0x00c6, all -> 0x00c1 }
        throw r9;	 Catch:{ Throwable -> 0x00c6, all -> 0x00c1 }
    L_0x0077:
        r9 = r0.L$4;
        r9 = (kotlinx.coroutines.channels.ChannelIterator) r9;
        r2 = r0.L$3;
        r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2;
        r3 = r0.L$2;
        r3 = (java.lang.Throwable) r3;
        r4 = r0.L$1;
        r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4;
        r5 = r0.L$0;
        r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5;
        r6 = r10 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x0099, all -> 0x0096 }
        if (r6 != 0) goto L_0x0091;
    L_0x008f:
        goto L_0x0113;
    L_0x0091:
        r10 = (kotlin.Result.Failure) r10;	 Catch:{ Throwable -> 0x0099, all -> 0x0096 }
        r9 = r10.exception;	 Catch:{ Throwable -> 0x0099, all -> 0x0096 }
        throw r9;	 Catch:{ Throwable -> 0x0099, all -> 0x0096 }
    L_0x0096:
        r9 = move-exception;
        goto L_0x0160;
    L_0x0099:
        r9 = move-exception;
        r3 = r9;
        r9 = r4;
        goto L_0x015c;
    L_0x009e:
        r9 = r0.L$4;
        r9 = (kotlinx.coroutines.channels.ChannelIterator) r9;
        r2 = r0.L$3;
        r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2;
        r4 = r0.L$2;
        r4 = (java.lang.Throwable) r4;
        r5 = r0.L$1;
        r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5;
        r6 = r0.L$0;
        r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6;
        r7 = r10 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x00c6, all -> 0x00c1 }
        if (r7 != 0) goto L_0x00bc;
    L_0x00b6:
        r8 = r2;
        r2 = r9;
        r9 = r5;
        r5 = r6;
        r6 = r8;
        goto L_0x00ef;
    L_0x00bc:
        r10 = (kotlin.Result.Failure) r10;	 Catch:{ Throwable -> 0x00c6, all -> 0x00c1 }
        r9 = r10.exception;	 Catch:{ Throwable -> 0x00c6, all -> 0x00c1 }
        throw r9;	 Catch:{ Throwable -> 0x00c6, all -> 0x00c1 }
    L_0x00c1:
        r9 = move-exception;
        r3 = r4;
        r4 = r5;
        goto L_0x0160;
    L_0x00c6:
        r9 = move-exception;
        r3 = r9;
        r9 = r5;
        goto L_0x015c;
    L_0x00cb:
        r2 = r10 instanceof kotlin.Result.Failure;
        if (r2 != 0) goto L_0x0164;
    L_0x00cf:
        r10 = r3;
        r10 = (java.lang.Throwable) r10;
        r2 = r9.iterator();	 Catch:{ Throwable -> 0x015a, all -> 0x0155 }
        r0.L$0 = r9;	 Catch:{ Throwable -> 0x015a, all -> 0x0155 }
        r0.L$1 = r9;	 Catch:{ Throwable -> 0x015a, all -> 0x0155 }
        r0.L$2 = r10;	 Catch:{ Throwable -> 0x015a, all -> 0x0155 }
        r0.L$3 = r9;	 Catch:{ Throwable -> 0x015a, all -> 0x0155 }
        r0.L$4 = r2;	 Catch:{ Throwable -> 0x015a, all -> 0x0155 }
        r4 = 1;
        r0.label = r4;	 Catch:{ Throwable -> 0x015a, all -> 0x0155 }
        r4 = r2.hasNext(r0);	 Catch:{ Throwable -> 0x015a, all -> 0x0155 }
        if (r4 != r1) goto L_0x00ea;
    L_0x00e9:
        return r1;
    L_0x00ea:
        r5 = r9;
        r6 = r5;
        r8 = r4;
        r4 = r10;
        r10 = r8;
    L_0x00ef:
        r10 = (java.lang.Boolean) r10;	 Catch:{ Throwable -> 0x015a, all -> 0x0152 }
        r10 = r10.booleanValue();	 Catch:{ Throwable -> 0x015a, all -> 0x0152 }
        if (r10 != 0) goto L_0x00fb;
    L_0x00f7:
        r9.cancel(r4);
        return r3;
    L_0x00fb:
        r0.L$0 = r5;	 Catch:{ Throwable -> 0x015a, all -> 0x0152 }
        r0.L$1 = r9;	 Catch:{ Throwable -> 0x015a, all -> 0x0152 }
        r0.L$2 = r4;	 Catch:{ Throwable -> 0x015a, all -> 0x0152 }
        r0.L$3 = r6;	 Catch:{ Throwable -> 0x015a, all -> 0x0152 }
        r0.L$4 = r2;	 Catch:{ Throwable -> 0x015a, all -> 0x0152 }
        r10 = 2;
        r0.label = r10;	 Catch:{ Throwable -> 0x015a, all -> 0x0152 }
        r10 = r2.next(r0);	 Catch:{ Throwable -> 0x015a, all -> 0x0152 }
        if (r10 != r1) goto L_0x010f;
    L_0x010e:
        return r1;
    L_0x010f:
        r3 = r4;
        r4 = r9;
        r9 = r2;
        r2 = r6;
    L_0x0113:
        r0.L$0 = r5;	 Catch:{ Throwable -> 0x0099, all -> 0x0096 }
        r0.L$1 = r4;	 Catch:{ Throwable -> 0x0099, all -> 0x0096 }
        r0.L$2 = r3;	 Catch:{ Throwable -> 0x0099, all -> 0x0096 }
        r0.L$3 = r2;	 Catch:{ Throwable -> 0x0099, all -> 0x0096 }
        r0.L$4 = r9;	 Catch:{ Throwable -> 0x0099, all -> 0x0096 }
        r0.L$5 = r10;	 Catch:{ Throwable -> 0x0099, all -> 0x0096 }
        r6 = 3;
        r0.label = r6;	 Catch:{ Throwable -> 0x0099, all -> 0x0096 }
        r6 = r9.hasNext(r0);	 Catch:{ Throwable -> 0x0099, all -> 0x0096 }
        if (r6 != r1) goto L_0x0129;
    L_0x0128:
        return r1;
    L_0x0129:
        r8 = r1;
        r1 = r10;
        r10 = r6;
        goto L_0x006f;
    L_0x012e:
        r10 = (java.lang.Boolean) r10;	 Catch:{ Throwable -> 0x0099, all -> 0x0096 }
        r10 = r10.booleanValue();	 Catch:{ Throwable -> 0x0099, all -> 0x0096 }
        if (r10 == 0) goto L_0x014e;
    L_0x0136:
        r0.L$0 = r5;	 Catch:{ Throwable -> 0x0099, all -> 0x0096 }
        r0.L$1 = r4;	 Catch:{ Throwable -> 0x0099, all -> 0x0096 }
        r0.L$2 = r3;	 Catch:{ Throwable -> 0x0099, all -> 0x0096 }
        r0.L$3 = r2;	 Catch:{ Throwable -> 0x0099, all -> 0x0096 }
        r0.L$4 = r9;	 Catch:{ Throwable -> 0x0099, all -> 0x0096 }
        r0.L$5 = r1;	 Catch:{ Throwable -> 0x0099, all -> 0x0096 }
        r10 = 4;
        r0.label = r10;	 Catch:{ Throwable -> 0x0099, all -> 0x0096 }
        r10 = r9.next(r0);	 Catch:{ Throwable -> 0x0099, all -> 0x0096 }
        if (r10 != r6) goto L_0x014c;
    L_0x014b:
        return r6;
    L_0x014c:
        r1 = r6;
        goto L_0x0113;
    L_0x014e:
        r4.cancel(r3);
        return r1;
    L_0x0152:
        r10 = move-exception;
        r3 = r4;
        goto L_0x015e;
    L_0x0155:
        r0 = move-exception;
        r4 = r9;
        r3 = r10;
        r9 = r0;
        goto L_0x0160;
    L_0x015a:
        r10 = move-exception;
        r3 = r10;
    L_0x015c:
        throw r3;	 Catch:{ all -> 0x015d }
    L_0x015d:
        r10 = move-exception;
    L_0x015e:
        r4 = r9;
        r9 = r10;
    L_0x0160:
        r4.cancel(r3);
        throw r9;
    L_0x0164:
        r10 = (kotlin.Result.Failure) r10;
        r9 = r10.exception;
        throw r9;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.lastOrNull(kotlinx.coroutines.channels.ReceiveChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @org.jetbrains.annotations.Nullable
    @kotlinx.coroutines.ObsoleteCoroutinesApi
    public static final <E> java.lang.Object lastOrNull(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r12, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super E, java.lang.Boolean> r13, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super E> r14) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:37)
	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:61)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1603198149.run(Unknown Source)
*/
        /*
        r0 = r14 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$lastOrNull$3;
        if (r0 == 0) goto L_0x0014;
    L_0x0004:
        r0 = r14;
        r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$lastOrNull$3) r0;
        r1 = r0.label;
        r2 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r1 = r1 & r2;
        if (r1 == 0) goto L_0x0014;
    L_0x000e:
        r14 = r0.label;
        r14 = r14 - r2;
        r0.label = r14;
        goto L_0x0019;
    L_0x0014:
        r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$lastOrNull$3;
        r0.<init>(r14);
    L_0x0019:
        r14 = r0.result;
        r1 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        r2 = r0.label;
        r3 = 1;
        switch(r2) {
            case 0: goto L_0x008d;
            case 1: goto L_0x005b;
            case 2: goto L_0x002d;
            default: goto L_0x0025;
        };
    L_0x0025:
        r12 = new java.lang.IllegalStateException;
        r13 = "call to 'resume' before 'invoke' with coroutine";
        r12.<init>(r13);
        throw r12;
    L_0x002d:
        r12 = r0.L$7;
        r12 = (kotlinx.coroutines.channels.ChannelIterator) r12;
        r13 = r0.L$6;
        r13 = (kotlinx.coroutines.channels.ReceiveChannel) r13;
        r2 = r0.L$5;
        r2 = (java.lang.Throwable) r2;
        r4 = r0.L$4;
        r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4;
        r5 = r0.L$3;
        r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5;
        r6 = r0.L$2;
        r6 = (kotlin.jvm.internal.Ref.ObjectRef) r6;
        r7 = r0.L$1;
        r7 = (kotlin.jvm.functions.Function1) r7;
        r8 = r0.L$0;
        r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8;
        r9 = r14 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        if (r9 != 0) goto L_0x0056;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
    L_0x0051:
        r10 = r4;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r4 = r12;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r12 = r10;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        goto L_0x00e4;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
    L_0x0056:
        r14 = (kotlin.Result.Failure) r14;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r12 = r14.exception;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        throw r12;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
    L_0x005b:
        r12 = r0.L$7;
        r12 = (kotlinx.coroutines.channels.ChannelIterator) r12;
        r13 = r0.L$6;
        r13 = (kotlinx.coroutines.channels.ReceiveChannel) r13;
        r2 = r0.L$5;
        r2 = (java.lang.Throwable) r2;
        r4 = r0.L$4;
        r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4;
        r5 = r0.L$3;
        r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5;
        r6 = r0.L$2;
        r6 = (kotlin.jvm.internal.Ref.ObjectRef) r6;
        r7 = r0.L$1;
        r7 = (kotlin.jvm.functions.Function1) r7;
        r8 = r0.L$0;
        r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8;
        r9 = r14 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        if (r9 != 0) goto L_0x0080;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
    L_0x007f:
        goto L_0x00c2;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
    L_0x0080:
        r14 = (kotlin.Result.Failure) r14;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r12 = r14.exception;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        throw r12;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
    L_0x0085:
        r12 = move-exception;
        goto L_0x0109;
    L_0x0088:
        r12 = move-exception;
        r2 = r12;
        r12 = r4;
        goto L_0x0108;
    L_0x008d:
        r2 = r14 instanceof kotlin.Result.Failure;
        if (r2 != 0) goto L_0x0113;
    L_0x0091:
        r14 = new kotlin.jvm.internal.Ref$ObjectRef;
        r14.<init>();
        r2 = 0;
        r14.element = r2;
        r2 = (java.lang.Throwable) r2;
        r4 = r12.iterator();	 Catch:{ Throwable -> 0x0106 }
        r5 = r12;	 Catch:{ Throwable -> 0x0106 }
        r8 = r5;	 Catch:{ Throwable -> 0x0106 }
        r7 = r13;	 Catch:{ Throwable -> 0x0106 }
        r13 = r8;	 Catch:{ Throwable -> 0x0106 }
    L_0x00a3:
        r0.L$0 = r8;	 Catch:{ Throwable -> 0x0106 }
        r0.L$1 = r7;	 Catch:{ Throwable -> 0x0106 }
        r0.L$2 = r14;	 Catch:{ Throwable -> 0x0106 }
        r0.L$3 = r5;	 Catch:{ Throwable -> 0x0106 }
        r0.L$4 = r12;	 Catch:{ Throwable -> 0x0106 }
        r0.L$5 = r2;	 Catch:{ Throwable -> 0x0106 }
        r0.L$6 = r13;	 Catch:{ Throwable -> 0x0106 }
        r0.L$7 = r4;	 Catch:{ Throwable -> 0x0106 }
        r0.label = r3;	 Catch:{ Throwable -> 0x0106 }
        r6 = r4.hasNext(r0);	 Catch:{ Throwable -> 0x0106 }
        if (r6 != r1) goto L_0x00bc;
    L_0x00bb:
        return r1;
    L_0x00bc:
        r10 = r4;
        r4 = r12;
        r12 = r10;
        r11 = r6;
        r6 = r14;
        r14 = r11;
    L_0x00c2:
        r14 = (java.lang.Boolean) r14;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r14 = r14.booleanValue();	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        if (r14 == 0) goto L_0x00f4;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
    L_0x00ca:
        r0.L$0 = r8;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r0.L$1 = r7;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r0.L$2 = r6;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r0.L$3 = r5;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r0.L$4 = r4;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r0.L$5 = r2;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r0.L$6 = r13;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r0.L$7 = r12;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r14 = 2;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r0.label = r14;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r14 = r12.next(r0);	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        if (r14 != r1) goto L_0x0051;
    L_0x00e3:
        return r1;
    L_0x00e4:
        r9 = r7.invoke(r14);	 Catch:{ Throwable -> 0x0106 }
        r9 = (java.lang.Boolean) r9;	 Catch:{ Throwable -> 0x0106 }
        r9 = r9.booleanValue();	 Catch:{ Throwable -> 0x0106 }
        if (r9 == 0) goto L_0x00f2;	 Catch:{ Throwable -> 0x0106 }
    L_0x00f0:
        r6.element = r14;	 Catch:{ Throwable -> 0x0106 }
    L_0x00f2:
        r14 = r6;
        goto L_0x00a3;
    L_0x00f4:
        r12 = kotlin.Unit.INSTANCE;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        kotlin.jvm.internal.InlineMarker.finallyStart(r3);
        r4.cancel(r2);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r3);
        r12 = r6.element;
        return r12;
    L_0x0102:
        r13 = move-exception;
        r4 = r12;
        r12 = r13;
        goto L_0x0109;
    L_0x0106:
        r13 = move-exception;
        r2 = r13;
    L_0x0108:
        throw r2;	 Catch:{ all -> 0x0102 }
    L_0x0109:
        kotlin.jvm.internal.InlineMarker.finallyStart(r3);
        r4.cancel(r2);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r3);
        throw r12;
    L_0x0113:
        r14 = (kotlin.Result.Failure) r14;
        r12 = r14.exception;
        throw r12;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.lastOrNull(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @org.jetbrains.annotations.Nullable
    @kotlinx.coroutines.ObsoleteCoroutinesApi
    public static final <E> java.lang.Object single(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r8, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super E> r9) {
        /*
        r0 = r9 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$single$1;
        if (r0 == 0) goto L_0x0014;
    L_0x0004:
        r0 = r9;
        r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$single$1) r0;
        r1 = r0.label;
        r2 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r1 = r1 & r2;
        if (r1 == 0) goto L_0x0014;
    L_0x000e:
        r9 = r0.label;
        r9 = r9 - r2;
        r0.label = r9;
        goto L_0x0019;
    L_0x0014:
        r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$single$1;
        r0.<init>(r9);
    L_0x0019:
        r9 = r0.result;
        r1 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        r2 = r0.label;
        switch(r2) {
            case 0: goto L_0x009f;
            case 1: goto L_0x0079;
            case 2: goto L_0x0057;
            case 3: goto L_0x002c;
            default: goto L_0x0024;
        };
    L_0x0024:
        r8 = new java.lang.IllegalStateException;
        r9 = "call to 'resume' before 'invoke' with coroutine";
        r8.<init>(r9);
        throw r8;
    L_0x002c:
        r8 = r0.L$5;
        r1 = r0.L$4;
        r1 = (kotlinx.coroutines.channels.ChannelIterator) r1;
        r1 = r0.L$3;
        r1 = (kotlinx.coroutines.channels.ReceiveChannel) r1;
        r1 = r0.L$2;
        r1 = (java.lang.Throwable) r1;
        r2 = r0.L$1;
        r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2;
        r0 = r0.L$0;
        r0 = (kotlinx.coroutines.channels.ReceiveChannel) r0;
        r0 = r9 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x0052, all -> 0x004d }
        if (r0 != 0) goto L_0x0048;
    L_0x0046:
        goto L_0x00f9;
    L_0x0048:
        r9 = (kotlin.Result.Failure) r9;	 Catch:{ Throwable -> 0x0052, all -> 0x004d }
        r8 = r9.exception;	 Catch:{ Throwable -> 0x0052, all -> 0x004d }
        throw r8;	 Catch:{ Throwable -> 0x0052, all -> 0x004d }
    L_0x004d:
        r8 = move-exception;
        r3 = r1;
        r4 = r2;
        goto L_0x0125;
    L_0x0052:
        r8 = move-exception;
        r1 = r8;
        r8 = r2;
        goto L_0x0120;
    L_0x0057:
        r8 = r0.L$4;
        r8 = (kotlinx.coroutines.channels.ChannelIterator) r8;
        r2 = r0.L$3;
        r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2;
        r3 = r0.L$2;
        r3 = (java.lang.Throwable) r3;
        r4 = r0.L$1;
        r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4;
        r5 = r0.L$0;
        r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5;
        r6 = r9 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x009a, all -> 0x0097 }
        if (r6 != 0) goto L_0x0074;
    L_0x006f:
        r7 = r9;
        r9 = r8;
        r8 = r7;
        goto L_0x00e1;
    L_0x0074:
        r9 = (kotlin.Result.Failure) r9;	 Catch:{ Throwable -> 0x009a, all -> 0x0097 }
        r8 = r9.exception;	 Catch:{ Throwable -> 0x009a, all -> 0x0097 }
        throw r8;	 Catch:{ Throwable -> 0x009a, all -> 0x0097 }
    L_0x0079:
        r8 = r0.L$4;
        r8 = (kotlinx.coroutines.channels.ChannelIterator) r8;
        r2 = r0.L$3;
        r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2;
        r3 = r0.L$2;
        r3 = (java.lang.Throwable) r3;
        r4 = r0.L$1;
        r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4;
        r5 = r0.L$0;
        r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5;
        r6 = r9 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x009a, all -> 0x0097 }
        if (r6 != 0) goto L_0x0092;
    L_0x0091:
        goto L_0x00c5;
    L_0x0092:
        r9 = (kotlin.Result.Failure) r9;	 Catch:{ Throwable -> 0x009a, all -> 0x0097 }
        r8 = r9.exception;	 Catch:{ Throwable -> 0x009a, all -> 0x0097 }
        throw r8;	 Catch:{ Throwable -> 0x009a, all -> 0x0097 }
    L_0x0097:
        r8 = move-exception;
        goto L_0x0125;
    L_0x009a:
        r8 = move-exception;
        r1 = r8;
        r8 = r4;
        goto L_0x0120;
    L_0x009f:
        r2 = r9 instanceof kotlin.Result.Failure;
        if (r2 != 0) goto L_0x0129;
    L_0x00a3:
        r9 = 0;
        r9 = (java.lang.Throwable) r9;
        r2 = r8.iterator();	 Catch:{ Throwable -> 0x011e, all -> 0x0119 }
        r0.L$0 = r8;	 Catch:{ Throwable -> 0x011e, all -> 0x0119 }
        r0.L$1 = r8;	 Catch:{ Throwable -> 0x011e, all -> 0x0119 }
        r0.L$2 = r9;	 Catch:{ Throwable -> 0x011e, all -> 0x0119 }
        r0.L$3 = r8;	 Catch:{ Throwable -> 0x011e, all -> 0x0119 }
        r0.L$4 = r2;	 Catch:{ Throwable -> 0x011e, all -> 0x0119 }
        r3 = 1;
        r0.label = r3;	 Catch:{ Throwable -> 0x011e, all -> 0x0119 }
        r3 = r2.hasNext(r0);	 Catch:{ Throwable -> 0x011e, all -> 0x0119 }
        if (r3 != r1) goto L_0x00be;
    L_0x00bd:
        return r1;
    L_0x00be:
        r4 = r8;
        r5 = r4;
        r8 = r2;
        r2 = r5;
        r7 = r3;
        r3 = r9;
        r9 = r7;
    L_0x00c5:
        r9 = (java.lang.Boolean) r9;	 Catch:{ Throwable -> 0x009a, all -> 0x0097 }
        r9 = r9.booleanValue();	 Catch:{ Throwable -> 0x009a, all -> 0x0097 }
        if (r9 == 0) goto L_0x010f;
    L_0x00cd:
        r0.L$0 = r5;	 Catch:{ Throwable -> 0x009a, all -> 0x0097 }
        r0.L$1 = r4;	 Catch:{ Throwable -> 0x009a, all -> 0x0097 }
        r0.L$2 = r3;	 Catch:{ Throwable -> 0x009a, all -> 0x0097 }
        r0.L$3 = r2;	 Catch:{ Throwable -> 0x009a, all -> 0x0097 }
        r0.L$4 = r8;	 Catch:{ Throwable -> 0x009a, all -> 0x0097 }
        r9 = 2;
        r0.label = r9;	 Catch:{ Throwable -> 0x009a, all -> 0x0097 }
        r9 = r8.next(r0);	 Catch:{ Throwable -> 0x009a, all -> 0x0097 }
        if (r9 != r1) goto L_0x006f;
    L_0x00e0:
        return r1;
    L_0x00e1:
        r0.L$0 = r5;	 Catch:{ Throwable -> 0x009a, all -> 0x0097 }
        r0.L$1 = r4;	 Catch:{ Throwable -> 0x009a, all -> 0x0097 }
        r0.L$2 = r3;	 Catch:{ Throwable -> 0x009a, all -> 0x0097 }
        r0.L$3 = r2;	 Catch:{ Throwable -> 0x009a, all -> 0x0097 }
        r0.L$4 = r9;	 Catch:{ Throwable -> 0x009a, all -> 0x0097 }
        r0.L$5 = r8;	 Catch:{ Throwable -> 0x009a, all -> 0x0097 }
        r2 = 3;
        r0.label = r2;	 Catch:{ Throwable -> 0x009a, all -> 0x0097 }
        r9 = r9.hasNext(r0);	 Catch:{ Throwable -> 0x009a, all -> 0x0097 }
        if (r9 != r1) goto L_0x00f7;
    L_0x00f6:
        return r1;
    L_0x00f7:
        r1 = r3;
        r2 = r4;
    L_0x00f9:
        r9 = (java.lang.Boolean) r9;	 Catch:{ Throwable -> 0x0052, all -> 0x004d }
        r9 = r9.booleanValue();	 Catch:{ Throwable -> 0x0052, all -> 0x004d }
        if (r9 != 0) goto L_0x0105;
    L_0x0101:
        r2.cancel(r1);
        return r8;
    L_0x0105:
        r8 = new java.lang.IllegalArgumentException;	 Catch:{ Throwable -> 0x0052, all -> 0x004d }
        r9 = "ReceiveChannel has more than one element.";
        r8.<init>(r9);	 Catch:{ Throwable -> 0x0052, all -> 0x004d }
        r8 = (java.lang.Throwable) r8;	 Catch:{ Throwable -> 0x0052, all -> 0x004d }
        throw r8;	 Catch:{ Throwable -> 0x0052, all -> 0x004d }
    L_0x010f:
        r8 = new java.util.NoSuchElementException;	 Catch:{ Throwable -> 0x009a, all -> 0x0097 }
        r9 = "ReceiveChannel is empty.";
        r8.<init>(r9);	 Catch:{ Throwable -> 0x009a, all -> 0x0097 }
        r8 = (java.lang.Throwable) r8;	 Catch:{ Throwable -> 0x009a, all -> 0x0097 }
        throw r8;	 Catch:{ Throwable -> 0x009a, all -> 0x0097 }
    L_0x0119:
        r0 = move-exception;
        r4 = r8;
        r3 = r9;
        r8 = r0;
        goto L_0x0125;
    L_0x011e:
        r9 = move-exception;
        r1 = r9;
    L_0x0120:
        throw r1;	 Catch:{ all -> 0x0121 }
    L_0x0121:
        r9 = move-exception;
        r4 = r8;
        r8 = r9;
        r3 = r1;
    L_0x0125:
        r4.cancel(r3);
        throw r8;
    L_0x0129:
        r9 = (kotlin.Result.Failure) r9;
        r8 = r9.exception;
        throw r8;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.single(kotlinx.coroutines.channels.ReceiveChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @org.jetbrains.annotations.Nullable
    @kotlinx.coroutines.ObsoleteCoroutinesApi
    public static final <E> java.lang.Object single(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r16, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super E, java.lang.Boolean> r17, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super E> r18) {
        /*
        r0 = r18;
        r1 = r0 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$single$3;
        if (r1 == 0) goto L_0x0016;
    L_0x0006:
        r1 = r0;
        r1 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$single$3) r1;
        r2 = r1.label;
        r3 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r2 = r2 & r3;
        if (r2 == 0) goto L_0x0016;
    L_0x0010:
        r0 = r1.label;
        r0 = r0 - r3;
        r1.label = r0;
        goto L_0x001b;
    L_0x0016:
        r1 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$single$3;
        r1.<init>(r0);
    L_0x001b:
        r0 = r1.result;
        r2 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        r3 = r1.label;
        r4 = 1;
        switch(r3) {
            case 0: goto L_0x009d;
            case 1: goto L_0x0063;
            case 2: goto L_0x002f;
            default: goto L_0x0027;
        };
    L_0x0027:
        r0 = new java.lang.IllegalStateException;
        r1 = "call to 'resume' before 'invoke' with coroutine";
        r0.<init>(r1);
        throw r0;
    L_0x002f:
        r3 = r1.L$8;
        r3 = (kotlinx.coroutines.channels.ChannelIterator) r3;
        r5 = r1.L$7;
        r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5;
        r6 = r1.L$6;
        r6 = (java.lang.Throwable) r6;
        r7 = r1.L$5;
        r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7;
        r8 = r1.L$4;
        r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8;
        r9 = r1.L$3;
        r9 = (kotlin.jvm.internal.Ref.BooleanRef) r9;
        r10 = r1.L$2;
        r10 = (kotlin.jvm.internal.Ref.ObjectRef) r10;
        r11 = r1.L$1;
        r11 = (kotlin.jvm.functions.Function1) r11;
        r12 = r1.L$0;
        r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12;
        r13 = r0 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x0098, all -> 0x0094 }
        if (r13 != 0) goto L_0x005e;
    L_0x0057:
        r14 = r9;
        r9 = r2;
        r2 = r7;
        r7 = r5;
        r5 = r14;
        goto L_0x010a;
    L_0x005e:
        r0 = (kotlin.Result.Failure) r0;	 Catch:{ Throwable -> 0x0098, all -> 0x0094 }
        r0 = r0.exception;	 Catch:{ Throwable -> 0x0098, all -> 0x0094 }
        throw r0;	 Catch:{ Throwable -> 0x0098, all -> 0x0094 }
    L_0x0063:
        r3 = r1.L$8;
        r3 = (kotlinx.coroutines.channels.ChannelIterator) r3;
        r5 = r1.L$7;
        r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5;
        r6 = r1.L$6;
        r6 = (java.lang.Throwable) r6;
        r7 = r1.L$5;
        r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7;
        r8 = r1.L$4;
        r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8;
        r9 = r1.L$3;
        r9 = (kotlin.jvm.internal.Ref.BooleanRef) r9;
        r10 = r1.L$2;
        r10 = (kotlin.jvm.internal.Ref.ObjectRef) r10;
        r11 = r1.L$1;
        r11 = (kotlin.jvm.functions.Function1) r11;
        r12 = r1.L$0;
        r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12;
        r13 = r0 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x0098, all -> 0x0094 }
        if (r13 != 0) goto L_0x008f;
    L_0x008b:
        r14 = r7;
        r7 = r2;
        r2 = r14;
        goto L_0x00e2;
    L_0x008f:
        r0 = (kotlin.Result.Failure) r0;	 Catch:{ Throwable -> 0x0098, all -> 0x0094 }
        r0 = r0.exception;	 Catch:{ Throwable -> 0x0098, all -> 0x0094 }
        throw r0;	 Catch:{ Throwable -> 0x0098, all -> 0x0094 }
    L_0x0094:
        r0 = move-exception;
        r2 = r7;
        goto L_0x0153;
    L_0x0098:
        r0 = move-exception;
        r6 = r0;
        r2 = r7;
        goto L_0x0151;
    L_0x009d:
        r3 = r0 instanceof kotlin.Result.Failure;
        if (r3 != 0) goto L_0x015d;
    L_0x00a1:
        r0 = new kotlin.jvm.internal.Ref$ObjectRef;
        r0.<init>();
        r3 = 0;
        r0.element = r3;
        r5 = new kotlin.jvm.internal.Ref$BooleanRef;
        r5.<init>();
        r6 = 0;
        r5.element = r6;
        r6 = r3;
        r6 = (java.lang.Throwable) r6;
        r3 = r16.iterator();	 Catch:{ Throwable -> 0x014d, all -> 0x0149 }
        r7 = r16;
        r8 = r7;
        r12 = r8;
        r11 = r17;
        r9 = r2;
        r2 = r12;
    L_0x00c0:
        r1.L$0 = r12;	 Catch:{ Throwable -> 0x0147 }
        r1.L$1 = r11;	 Catch:{ Throwable -> 0x0147 }
        r1.L$2 = r0;	 Catch:{ Throwable -> 0x0147 }
        r1.L$3 = r5;	 Catch:{ Throwable -> 0x0147 }
        r1.L$4 = r8;	 Catch:{ Throwable -> 0x0147 }
        r1.L$5 = r2;	 Catch:{ Throwable -> 0x0147 }
        r1.L$6 = r6;	 Catch:{ Throwable -> 0x0147 }
        r1.L$7 = r7;	 Catch:{ Throwable -> 0x0147 }
        r1.L$8 = r3;	 Catch:{ Throwable -> 0x0147 }
        r1.label = r4;	 Catch:{ Throwable -> 0x0147 }
        r10 = r3.hasNext(r1);	 Catch:{ Throwable -> 0x0147 }
        if (r10 != r9) goto L_0x00db;
    L_0x00da:
        return r9;
    L_0x00db:
        r14 = r10;
        r10 = r0;
        r0 = r14;
        r15 = r9;
        r9 = r5;
        r5 = r7;
        r7 = r15;
    L_0x00e2:
        r0 = (java.lang.Boolean) r0;	 Catch:{ Throwable -> 0x0147 }
        r0 = r0.booleanValue();	 Catch:{ Throwable -> 0x0147 }
        if (r0 == 0) goto L_0x012b;
    L_0x00ea:
        r1.L$0 = r12;	 Catch:{ Throwable -> 0x0147 }
        r1.L$1 = r11;	 Catch:{ Throwable -> 0x0147 }
        r1.L$2 = r10;	 Catch:{ Throwable -> 0x0147 }
        r1.L$3 = r9;	 Catch:{ Throwable -> 0x0147 }
        r1.L$4 = r8;	 Catch:{ Throwable -> 0x0147 }
        r1.L$5 = r2;	 Catch:{ Throwable -> 0x0147 }
        r1.L$6 = r6;	 Catch:{ Throwable -> 0x0147 }
        r1.L$7 = r5;	 Catch:{ Throwable -> 0x0147 }
        r1.L$8 = r3;	 Catch:{ Throwable -> 0x0147 }
        r0 = 2;
        r1.label = r0;	 Catch:{ Throwable -> 0x0147 }
        r0 = r3.next(r1);	 Catch:{ Throwable -> 0x0147 }
        if (r0 != r7) goto L_0x0106;
    L_0x0105:
        return r7;
    L_0x0106:
        r14 = r7;
        r7 = r5;
        r5 = r9;
        r9 = r14;
    L_0x010a:
        r13 = r11.invoke(r0);	 Catch:{ Throwable -> 0x0147 }
        r13 = (java.lang.Boolean) r13;	 Catch:{ Throwable -> 0x0147 }
        r13 = r13.booleanValue();	 Catch:{ Throwable -> 0x0147 }
        if (r13 == 0) goto L_0x0129;
    L_0x0116:
        r13 = r5.element;	 Catch:{ Throwable -> 0x0147 }
        if (r13 != 0) goto L_0x011f;
    L_0x011a:
        r10.element = r0;	 Catch:{ Throwable -> 0x0147 }
        r5.element = r4;	 Catch:{ Throwable -> 0x0147 }
        goto L_0x0129;
    L_0x011f:
        r0 = new java.lang.IllegalArgumentException;	 Catch:{ Throwable -> 0x0147 }
        r1 = "ReceiveChannel contains more than one matching element.";
        r0.<init>(r1);	 Catch:{ Throwable -> 0x0147 }
        r0 = (java.lang.Throwable) r0;	 Catch:{ Throwable -> 0x0147 }
        throw r0;	 Catch:{ Throwable -> 0x0147 }
    L_0x0129:
        r0 = r10;
        goto L_0x00c0;
    L_0x012b:
        r0 = kotlin.Unit.INSTANCE;	 Catch:{ Throwable -> 0x0147 }
        kotlin.jvm.internal.InlineMarker.finallyStart(r4);
        r2.cancel(r6);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r4);
        r0 = r9.element;
        if (r0 == 0) goto L_0x013d;
    L_0x013a:
        r0 = r10.element;
        return r0;
    L_0x013d:
        r0 = new java.util.NoSuchElementException;
        r1 = "ReceiveChannel contains no element matching the predicate.";
        r0.<init>(r1);
        r0 = (java.lang.Throwable) r0;
        throw r0;
    L_0x0147:
        r0 = move-exception;
        goto L_0x0150;
    L_0x0149:
        r0 = move-exception;
        r2 = r16;
        goto L_0x0153;
    L_0x014d:
        r0 = move-exception;
        r2 = r16;
    L_0x0150:
        r6 = r0;
    L_0x0151:
        throw r6;	 Catch:{ all -> 0x0152 }
    L_0x0152:
        r0 = move-exception;
    L_0x0153:
        kotlin.jvm.internal.InlineMarker.finallyStart(r4);
        r2.cancel(r6);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r4);
        throw r0;
    L_0x015d:
        r0 = (kotlin.Result.Failure) r0;
        r0 = r0.exception;
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.single(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @org.jetbrains.annotations.Nullable
    @kotlinx.coroutines.ObsoleteCoroutinesApi
    public static final <E> java.lang.Object singleOrNull(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r9, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super E> r10) {
        /*
        r0 = r10 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$singleOrNull$1;
        if (r0 == 0) goto L_0x0014;
    L_0x0004:
        r0 = r10;
        r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$singleOrNull$1) r0;
        r1 = r0.label;
        r2 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r1 = r1 & r2;
        if (r1 == 0) goto L_0x0014;
    L_0x000e:
        r10 = r0.label;
        r10 = r10 - r2;
        r0.label = r10;
        goto L_0x0019;
    L_0x0014:
        r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$singleOrNull$1;
        r0.<init>(r10);
    L_0x0019:
        r10 = r0.result;
        r1 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        r2 = r0.label;
        r3 = 0;
        switch(r2) {
            case 0: goto L_0x00a7;
            case 1: goto L_0x007d;
            case 2: goto L_0x005a;
            case 3: goto L_0x002d;
            default: goto L_0x0025;
        };
    L_0x0025:
        r9 = new java.lang.IllegalStateException;
        r10 = "call to 'resume' before 'invoke' with coroutine";
        r9.<init>(r10);
        throw r9;
    L_0x002d:
        r9 = r0.L$5;
        r1 = r0.L$4;
        r1 = (kotlinx.coroutines.channels.ChannelIterator) r1;
        r1 = r0.L$3;
        r1 = (kotlinx.coroutines.channels.ReceiveChannel) r1;
        r1 = r0.L$2;
        r1 = (java.lang.Throwable) r1;
        r2 = r0.L$1;
        r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2;
        r0 = r0.L$0;
        r0 = (kotlinx.coroutines.channels.ReceiveChannel) r0;
        r0 = r10 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x0055, all -> 0x0050 }
        if (r0 != 0) goto L_0x004b;
    L_0x0047:
        r0 = r9;
        r9 = r2;
        goto L_0x0107;
    L_0x004b:
        r10 = (kotlin.Result.Failure) r10;	 Catch:{ Throwable -> 0x0055, all -> 0x0050 }
        r9 = r10.exception;	 Catch:{ Throwable -> 0x0055, all -> 0x0050 }
        throw r9;	 Catch:{ Throwable -> 0x0055, all -> 0x0050 }
    L_0x0050:
        r9 = move-exception;
        r4 = r1;
        r5 = r2;
        goto L_0x0127;
    L_0x0055:
        r9 = move-exception;
        r1 = r9;
        r9 = r2;
        goto L_0x0122;
    L_0x005a:
        r9 = r0.L$4;
        r9 = (kotlinx.coroutines.channels.ChannelIterator) r9;
        r2 = r0.L$3;
        r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2;
        r4 = r0.L$2;
        r4 = (java.lang.Throwable) r4;
        r5 = r0.L$1;
        r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5;
        r6 = r0.L$0;
        r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6;
        r7 = r10 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x00a2, all -> 0x009f }
        if (r7 != 0) goto L_0x0078;
    L_0x0072:
        r8 = r2;
        r2 = r9;
    L_0x0074:
        r9 = r10;
        r10 = r8;
        goto L_0x00ee;
    L_0x0078:
        r10 = (kotlin.Result.Failure) r10;	 Catch:{ Throwable -> 0x00a2, all -> 0x009f }
        r9 = r10.exception;	 Catch:{ Throwable -> 0x00a2, all -> 0x009f }
        throw r9;	 Catch:{ Throwable -> 0x00a2, all -> 0x009f }
    L_0x007d:
        r9 = r0.L$4;
        r9 = (kotlinx.coroutines.channels.ChannelIterator) r9;
        r2 = r0.L$3;
        r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2;
        r4 = r0.L$2;
        r4 = (java.lang.Throwable) r4;
        r5 = r0.L$1;
        r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5;
        r6 = r0.L$0;
        r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6;
        r7 = r10 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x00a2, all -> 0x009f }
        if (r7 != 0) goto L_0x009a;
    L_0x0095:
        r8 = r2;
        r2 = r9;
        r9 = r5;
        r5 = r8;
        goto L_0x00cb;
    L_0x009a:
        r10 = (kotlin.Result.Failure) r10;	 Catch:{ Throwable -> 0x00a2, all -> 0x009f }
        r9 = r10.exception;	 Catch:{ Throwable -> 0x00a2, all -> 0x009f }
        throw r9;	 Catch:{ Throwable -> 0x00a2, all -> 0x009f }
    L_0x009f:
        r9 = move-exception;
        goto L_0x0127;
    L_0x00a2:
        r9 = move-exception;
        r1 = r9;
        r9 = r5;
        goto L_0x0122;
    L_0x00a7:
        r2 = r10 instanceof kotlin.Result.Failure;
        if (r2 != 0) goto L_0x012b;
    L_0x00ab:
        r10 = r3;
        r10 = (java.lang.Throwable) r10;
        r2 = r9.iterator();	 Catch:{ Throwable -> 0x0120, all -> 0x011b }
        r0.L$0 = r9;	 Catch:{ Throwable -> 0x0120, all -> 0x011b }
        r0.L$1 = r9;	 Catch:{ Throwable -> 0x0120, all -> 0x011b }
        r0.L$2 = r10;	 Catch:{ Throwable -> 0x0120, all -> 0x011b }
        r0.L$3 = r9;	 Catch:{ Throwable -> 0x0120, all -> 0x011b }
        r0.L$4 = r2;	 Catch:{ Throwable -> 0x0120, all -> 0x011b }
        r4 = 1;
        r0.label = r4;	 Catch:{ Throwable -> 0x0120, all -> 0x011b }
        r4 = r2.hasNext(r0);	 Catch:{ Throwable -> 0x0120, all -> 0x011b }
        if (r4 != r1) goto L_0x00c6;
    L_0x00c5:
        return r1;
    L_0x00c6:
        r5 = r9;
        r6 = r5;
        r8 = r4;
        r4 = r10;
        r10 = r8;
    L_0x00cb:
        r10 = (java.lang.Boolean) r10;	 Catch:{ Throwable -> 0x0120, all -> 0x0117 }
        r10 = r10.booleanValue();	 Catch:{ Throwable -> 0x0120, all -> 0x0117 }
        if (r10 != 0) goto L_0x00d7;
    L_0x00d3:
        r9.cancel(r4);
        return r3;
    L_0x00d7:
        r0.L$0 = r6;	 Catch:{ Throwable -> 0x0120, all -> 0x0117 }
        r0.L$1 = r9;	 Catch:{ Throwable -> 0x0120, all -> 0x0117 }
        r0.L$2 = r4;	 Catch:{ Throwable -> 0x0120, all -> 0x0117 }
        r0.L$3 = r5;	 Catch:{ Throwable -> 0x0120, all -> 0x0117 }
        r0.L$4 = r2;	 Catch:{ Throwable -> 0x0120, all -> 0x0117 }
        r10 = 2;
        r0.label = r10;	 Catch:{ Throwable -> 0x0120, all -> 0x0117 }
        r10 = r2.next(r0);	 Catch:{ Throwable -> 0x0120, all -> 0x0117 }
        if (r10 != r1) goto L_0x00eb;
    L_0x00ea:
        return r1;
    L_0x00eb:
        r8 = r5;
        r5 = r9;
        goto L_0x0074;
    L_0x00ee:
        r0.L$0 = r6;	 Catch:{ Throwable -> 0x00a2, all -> 0x009f }
        r0.L$1 = r5;	 Catch:{ Throwable -> 0x00a2, all -> 0x009f }
        r0.L$2 = r4;	 Catch:{ Throwable -> 0x00a2, all -> 0x009f }
        r0.L$3 = r10;	 Catch:{ Throwable -> 0x00a2, all -> 0x009f }
        r0.L$4 = r2;	 Catch:{ Throwable -> 0x00a2, all -> 0x009f }
        r0.L$5 = r9;	 Catch:{ Throwable -> 0x00a2, all -> 0x009f }
        r10 = 3;
        r0.label = r10;	 Catch:{ Throwable -> 0x00a2, all -> 0x009f }
        r10 = r2.hasNext(r0);	 Catch:{ Throwable -> 0x00a2, all -> 0x009f }
        if (r10 != r1) goto L_0x0104;
    L_0x0103:
        return r1;
    L_0x0104:
        r0 = r9;
        r1 = r4;
        r9 = r5;
    L_0x0107:
        r10 = (java.lang.Boolean) r10;	 Catch:{ Throwable -> 0x0120 }
        r10 = r10.booleanValue();	 Catch:{ Throwable -> 0x0120 }
        if (r10 == 0) goto L_0x0113;
    L_0x010f:
        r9.cancel(r1);
        return r3;
    L_0x0113:
        r9.cancel(r1);
        return r0;
    L_0x0117:
        r10 = move-exception;
        r5 = r9;
        r9 = r10;
        goto L_0x0127;
    L_0x011b:
        r0 = move-exception;
        r5 = r9;
        r4 = r10;
        r9 = r0;
        goto L_0x0127;
    L_0x0120:
        r10 = move-exception;
        r1 = r10;
    L_0x0122:
        throw r1;	 Catch:{ all -> 0x0123 }
    L_0x0123:
        r10 = move-exception;
        r5 = r9;
        r9 = r10;
        r4 = r1;
    L_0x0127:
        r5.cancel(r4);
        throw r9;
    L_0x012b:
        r10 = (kotlin.Result.Failure) r10;
        r9 = r10.exception;
        throw r9;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.singleOrNull(kotlinx.coroutines.channels.ReceiveChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @org.jetbrains.annotations.Nullable
    @kotlinx.coroutines.ObsoleteCoroutinesApi
    public static final <E> java.lang.Object singleOrNull(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r18, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super E, java.lang.Boolean> r19, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super E> r20) {
        /*
        r0 = r20;
        r1 = r0 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$singleOrNull$3;
        if (r1 == 0) goto L_0x0016;
    L_0x0006:
        r1 = r0;
        r1 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$singleOrNull$3) r1;
        r2 = r1.label;
        r3 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r2 = r2 & r3;
        if (r2 == 0) goto L_0x0016;
    L_0x0010:
        r0 = r1.label;
        r0 = r0 - r3;
        r1.label = r0;
        goto L_0x001b;
    L_0x0016:
        r1 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$singleOrNull$3;
        r1.<init>(r0);
    L_0x001b:
        r0 = r1.result;
        r2 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        r3 = r1.label;
        r4 = 2;
        r5 = 0;
        r6 = 1;
        switch(r3) {
            case 0: goto L_0x00a4;
            case 1: goto L_0x0068;
            case 2: goto L_0x0031;
            default: goto L_0x0029;
        };
    L_0x0029:
        r0 = new java.lang.IllegalStateException;
        r1 = "call to 'resume' before 'invoke' with coroutine";
        r0.<init>(r1);
        throw r0;
    L_0x0031:
        r3 = r1.L$8;
        r3 = (kotlinx.coroutines.channels.ChannelIterator) r3;
        r7 = r1.L$7;
        r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7;
        r8 = r1.L$6;
        r8 = (java.lang.Throwable) r8;
        r9 = r1.L$5;
        r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9;
        r10 = r1.L$4;
        r10 = (kotlinx.coroutines.channels.ReceiveChannel) r10;
        r11 = r1.L$3;
        r11 = (kotlin.jvm.internal.Ref.BooleanRef) r11;
        r12 = r1.L$2;
        r12 = (kotlin.jvm.internal.Ref.ObjectRef) r12;
        r13 = r1.L$1;
        r13 = (kotlin.jvm.functions.Function1) r13;
        r14 = r1.L$0;
        r14 = (kotlinx.coroutines.channels.ReceiveChannel) r14;
        r15 = r0 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x009f, all -> 0x009b }
        if (r15 != 0) goto L_0x0063;
    L_0x0059:
        r16 = r11;
        r11 = r2;
        r2 = r9;
        r9 = r7;
        r7 = r3;
        r3 = r16;
        goto L_0x0117;
    L_0x0063:
        r0 = (kotlin.Result.Failure) r0;	 Catch:{ Throwable -> 0x009f, all -> 0x009b }
        r0 = r0.exception;	 Catch:{ Throwable -> 0x009f, all -> 0x009b }
        throw r0;	 Catch:{ Throwable -> 0x009f, all -> 0x009b }
    L_0x0068:
        r3 = r1.L$8;
        r3 = (kotlinx.coroutines.channels.ChannelIterator) r3;
        r7 = r1.L$7;
        r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7;
        r8 = r1.L$6;
        r8 = (java.lang.Throwable) r8;
        r9 = r1.L$5;
        r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9;
        r10 = r1.L$4;
        r10 = (kotlinx.coroutines.channels.ReceiveChannel) r10;
        r11 = r1.L$3;
        r11 = (kotlin.jvm.internal.Ref.BooleanRef) r11;
        r12 = r1.L$2;
        r12 = (kotlin.jvm.internal.Ref.ObjectRef) r12;
        r13 = r1.L$1;
        r13 = (kotlin.jvm.functions.Function1) r13;
        r14 = r1.L$0;
        r14 = (kotlinx.coroutines.channels.ReceiveChannel) r14;
        r15 = r0 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x009f, all -> 0x009b }
        if (r15 != 0) goto L_0x0096;
    L_0x0090:
        r16 = r9;
        r9 = r2;
        r2 = r16;
        goto L_0x00ed;
    L_0x0096:
        r0 = (kotlin.Result.Failure) r0;	 Catch:{ Throwable -> 0x009f, all -> 0x009b }
        r0 = r0.exception;	 Catch:{ Throwable -> 0x009f, all -> 0x009b }
        throw r0;	 Catch:{ Throwable -> 0x009f, all -> 0x009b }
    L_0x009b:
        r0 = move-exception;
        r2 = r9;
        goto L_0x0156;
    L_0x009f:
        r0 = move-exception;
        r8 = r0;
        r2 = r9;
        goto L_0x0154;
    L_0x00a4:
        r3 = r0 instanceof kotlin.Result.Failure;
        if (r3 != 0) goto L_0x0160;
    L_0x00a8:
        r0 = new kotlin.jvm.internal.Ref$ObjectRef;
        r0.<init>();
        r0.element = r5;
        r3 = new kotlin.jvm.internal.Ref$BooleanRef;
        r3.<init>();
        r7 = 0;
        r3.element = r7;
        r8 = r5;
        r8 = (java.lang.Throwable) r8;
        r7 = r18.iterator();	 Catch:{ Throwable -> 0x0150, all -> 0x014c }
        r9 = r18;
        r10 = r9;
        r14 = r10;
        r13 = r19;
        r11 = r2;
        r2 = r14;
    L_0x00c6:
        r1.L$0 = r14;	 Catch:{ Throwable -> 0x014a }
        r1.L$1 = r13;	 Catch:{ Throwable -> 0x014a }
        r1.L$2 = r0;	 Catch:{ Throwable -> 0x014a }
        r1.L$3 = r3;	 Catch:{ Throwable -> 0x014a }
        r1.L$4 = r10;	 Catch:{ Throwable -> 0x014a }
        r1.L$5 = r2;	 Catch:{ Throwable -> 0x014a }
        r1.L$6 = r8;	 Catch:{ Throwable -> 0x014a }
        r1.L$7 = r9;	 Catch:{ Throwable -> 0x014a }
        r1.L$8 = r7;	 Catch:{ Throwable -> 0x014a }
        r1.label = r6;	 Catch:{ Throwable -> 0x014a }
        r12 = r7.hasNext(r1);	 Catch:{ Throwable -> 0x014a }
        if (r12 != r11) goto L_0x00e1;
    L_0x00e0:
        return r11;
    L_0x00e1:
        r16 = r12;
        r12 = r0;
        r0 = r16;
        r17 = r11;
        r11 = r3;
        r3 = r7;
        r7 = r9;
        r9 = r17;
    L_0x00ed:
        r0 = (java.lang.Boolean) r0;	 Catch:{ Throwable -> 0x014a }
        r0 = r0.booleanValue();	 Catch:{ Throwable -> 0x014a }
        if (r0 == 0) goto L_0x0137;
    L_0x00f5:
        r1.L$0 = r14;	 Catch:{ Throwable -> 0x014a }
        r1.L$1 = r13;	 Catch:{ Throwable -> 0x014a }
        r1.L$2 = r12;	 Catch:{ Throwable -> 0x014a }
        r1.L$3 = r11;	 Catch:{ Throwable -> 0x014a }
        r1.L$4 = r10;	 Catch:{ Throwable -> 0x014a }
        r1.L$5 = r2;	 Catch:{ Throwable -> 0x014a }
        r1.L$6 = r8;	 Catch:{ Throwable -> 0x014a }
        r1.L$7 = r7;	 Catch:{ Throwable -> 0x014a }
        r1.L$8 = r3;	 Catch:{ Throwable -> 0x014a }
        r1.label = r4;	 Catch:{ Throwable -> 0x014a }
        r0 = r3.next(r1);	 Catch:{ Throwable -> 0x014a }
        if (r0 != r9) goto L_0x0110;
    L_0x010f:
        return r9;
    L_0x0110:
        r16 = r7;
        r7 = r3;
        r3 = r11;
        r11 = r9;
        r9 = r16;
    L_0x0117:
        r15 = r13.invoke(r0);	 Catch:{ Throwable -> 0x014a }
        r15 = (java.lang.Boolean) r15;	 Catch:{ Throwable -> 0x014a }
        r15 = r15.booleanValue();	 Catch:{ Throwable -> 0x014a }
        if (r15 == 0) goto L_0x0135;
    L_0x0123:
        r15 = r3.element;	 Catch:{ Throwable -> 0x014a }
        if (r15 == 0) goto L_0x0131;
    L_0x0127:
        kotlin.jvm.internal.InlineMarker.finallyStart(r4);
        r2.cancel(r8);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r4);
        return r5;
    L_0x0131:
        r12.element = r0;	 Catch:{ Throwable -> 0x014a }
        r3.element = r6;	 Catch:{ Throwable -> 0x014a }
    L_0x0135:
        r0 = r12;
        goto L_0x00c6;
    L_0x0137:
        r0 = kotlin.Unit.INSTANCE;	 Catch:{ Throwable -> 0x014a }
        kotlin.jvm.internal.InlineMarker.finallyStart(r6);
        r2.cancel(r8);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r6);
        r0 = r11.element;
        if (r0 != 0) goto L_0x0147;
    L_0x0146:
        return r5;
    L_0x0147:
        r0 = r12.element;
        return r0;
    L_0x014a:
        r0 = move-exception;
        goto L_0x0153;
    L_0x014c:
        r0 = move-exception;
        r2 = r18;
        goto L_0x0156;
    L_0x0150:
        r0 = move-exception;
        r2 = r18;
    L_0x0153:
        r8 = r0;
    L_0x0154:
        throw r8;	 Catch:{ all -> 0x0155 }
    L_0x0155:
        r0 = move-exception;
    L_0x0156:
        kotlin.jvm.internal.InlineMarker.finallyStart(r6);
        r2.cancel(r8);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r6);
        throw r0;
    L_0x0160:
        r0 = (kotlin.Result.Failure) r0;
        r0 = r0.exception;
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.singleOrNull(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @NotNull
    @ObsoleteCoroutinesApi
    public static /* synthetic */ ReceiveChannel drop$default(ReceiveChannel receiveChannel, int i, CoroutineContext coroutineContext, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return ChannelsKt.drop(receiveChannel, i, coroutineContext);
    }

    @NotNull
    @ObsoleteCoroutinesApi
    public static final <E> ReceiveChannel<E> drop(@NotNull ReceiveChannel<? extends E> receiveChannel, int i, @NotNull CoroutineContext coroutineContext) {
        Intrinsics.checkParameterIsNotNull(receiveChannel, "receiver$0");
        Intrinsics.checkParameterIsNotNull(coroutineContext, "context");
        return ProduceKt.produce$default(GlobalScope.INSTANCE, coroutineContext, 0, ChannelsKt.consumes(receiveChannel), new ChannelsKt__Channels_commonKt$drop$1(receiveChannel, i, null), 2, null);
    }

    @NotNull
    @ObsoleteCoroutinesApi
    public static /* synthetic */ ReceiveChannel dropWhile$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return ChannelsKt.dropWhile(receiveChannel, coroutineContext, function2);
    }

    @NotNull
    @ObsoleteCoroutinesApi
    public static final <E> ReceiveChannel<E> dropWhile(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull CoroutineContext coroutineContext, @NotNull Function2<? super E, ? super Continuation<? super Boolean>, ? extends Object> function2) {
        Intrinsics.checkParameterIsNotNull(receiveChannel, "receiver$0");
        Intrinsics.checkParameterIsNotNull(coroutineContext, "context");
        Intrinsics.checkParameterIsNotNull(function2, "predicate");
        return ProduceKt.produce$default(GlobalScope.INSTANCE, coroutineContext, 0, ChannelsKt.consumes(receiveChannel), new ChannelsKt__Channels_commonKt$dropWhile$1(receiveChannel, function2, null), 2, null);
    }

    @NotNull
    @ObsoleteCoroutinesApi
    public static /* synthetic */ ReceiveChannel filter$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return ChannelsKt.filter(receiveChannel, coroutineContext, function2);
    }

    @NotNull
    @ObsoleteCoroutinesApi
    public static final <E> ReceiveChannel<E> filter(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull CoroutineContext coroutineContext, @NotNull Function2<? super E, ? super Continuation<? super Boolean>, ? extends Object> function2) {
        Intrinsics.checkParameterIsNotNull(receiveChannel, "receiver$0");
        Intrinsics.checkParameterIsNotNull(coroutineContext, "context");
        Intrinsics.checkParameterIsNotNull(function2, "predicate");
        return ProduceKt.produce$default(GlobalScope.INSTANCE, coroutineContext, 0, ChannelsKt.consumes(receiveChannel), new ChannelsKt__Channels_commonKt$filter$1(receiveChannel, function2, null), 2, null);
    }

    @NotNull
    @ObsoleteCoroutinesApi
    public static /* synthetic */ ReceiveChannel filterIndexed$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function3 function3, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return ChannelsKt.filterIndexed(receiveChannel, coroutineContext, function3);
    }

    @NotNull
    @ObsoleteCoroutinesApi
    public static final <E> ReceiveChannel<E> filterIndexed(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull CoroutineContext coroutineContext, @NotNull Function3<? super Integer, ? super E, ? super Continuation<? super Boolean>, ? extends Object> function3) {
        Intrinsics.checkParameterIsNotNull(receiveChannel, "receiver$0");
        Intrinsics.checkParameterIsNotNull(coroutineContext, "context");
        Intrinsics.checkParameterIsNotNull(function3, "predicate");
        return ProduceKt.produce$default(GlobalScope.INSTANCE, coroutineContext, 0, ChannelsKt.consumes(receiveChannel), new ChannelsKt__Channels_commonKt$filterIndexed$1(receiveChannel, function3, null), 2, null);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @org.jetbrains.annotations.Nullable
    @kotlinx.coroutines.ObsoleteCoroutinesApi
    public static final <E, C extends java.util.Collection<? super E>> java.lang.Object filterIndexedTo(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r17, @org.jetbrains.annotations.NotNull C r18, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function2<? super java.lang.Integer, ? super E, java.lang.Boolean> r19, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super C> r20) {
        /*
        r0 = r20;
        r1 = r0 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filterIndexedTo$1;
        if (r1 == 0) goto L_0x0016;
    L_0x0006:
        r1 = r0;
        r1 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filterIndexedTo$1) r1;
        r2 = r1.label;
        r3 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r2 = r2 & r3;
        if (r2 == 0) goto L_0x0016;
    L_0x0010:
        r0 = r1.label;
        r0 = r0 - r3;
        r1.label = r0;
        goto L_0x001b;
    L_0x0016:
        r1 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filterIndexedTo$1;
        r1.<init>(r0);
    L_0x001b:
        r0 = r1.result;
        r2 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        r3 = r1.label;
        r4 = 1;
        switch(r3) {
            case 0: goto L_0x00a8;
            case 1: goto L_0x0067;
            case 2: goto L_0x002f;
            default: goto L_0x0027;
        };
    L_0x0027:
        r0 = new java.lang.IllegalStateException;
        r1 = "call to 'resume' before 'invoke' with coroutine";
        r0.<init>(r1);
        throw r0;
    L_0x002f:
        r3 = r1.L$9;
        r3 = (kotlinx.coroutines.channels.ChannelIterator) r3;
        r5 = r1.L$8;
        r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5;
        r6 = r1.L$7;
        r6 = (java.lang.Throwable) r6;
        r7 = r1.L$6;
        r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7;
        r8 = r1.L$5;
        r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8;
        r9 = r1.L$4;
        r9 = (kotlin.jvm.internal.Ref.IntRef) r9;
        r10 = r1.L$3;
        r10 = (kotlinx.coroutines.channels.ReceiveChannel) r10;
        r11 = r1.L$2;
        r11 = (kotlin.jvm.functions.Function2) r11;
        r12 = r1.L$1;
        r12 = (java.util.Collection) r12;
        r13 = r1.L$0;
        r13 = (kotlinx.coroutines.channels.ReceiveChannel) r13;
        r14 = r0 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x00a3, all -> 0x009e }
        if (r14 != 0) goto L_0x0062;
    L_0x005b:
        r16 = r7;
        r7 = r2;
        r2 = r16;
        goto L_0x010f;
    L_0x0062:
        r0 = (kotlin.Result.Failure) r0;	 Catch:{ Throwable -> 0x00a3, all -> 0x009e }
        r0 = r0.exception;	 Catch:{ Throwable -> 0x00a3, all -> 0x009e }
        throw r0;	 Catch:{ Throwable -> 0x00a3, all -> 0x009e }
    L_0x0067:
        r3 = r1.L$9;
        r3 = (kotlinx.coroutines.channels.ChannelIterator) r3;
        r5 = r1.L$8;
        r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5;
        r6 = r1.L$7;
        r6 = (java.lang.Throwable) r6;
        r7 = r1.L$6;
        r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7;
        r8 = r1.L$5;
        r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8;
        r9 = r1.L$4;
        r9 = (kotlin.jvm.internal.Ref.IntRef) r9;
        r10 = r1.L$3;
        r10 = (kotlinx.coroutines.channels.ReceiveChannel) r10;
        r11 = r1.L$2;
        r11 = (kotlin.jvm.functions.Function2) r11;
        r12 = r1.L$1;
        r12 = (java.util.Collection) r12;
        r13 = r1.L$0;
        r13 = (kotlinx.coroutines.channels.ReceiveChannel) r13;
        r14 = r0 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x00a3, all -> 0x009e }
        if (r14 != 0) goto L_0x0099;
    L_0x0093:
        r16 = r7;
        r7 = r2;
        r2 = r16;
        goto L_0x00e9;
    L_0x0099:
        r0 = (kotlin.Result.Failure) r0;	 Catch:{ Throwable -> 0x00a3, all -> 0x009e }
        r0 = r0.exception;	 Catch:{ Throwable -> 0x00a3, all -> 0x009e }
        throw r0;	 Catch:{ Throwable -> 0x00a3, all -> 0x009e }
    L_0x009e:
        r0 = move-exception;
        r2 = r7;
    L_0x00a0:
        r1 = 1;
        goto L_0x0154;
    L_0x00a3:
        r0 = move-exception;
        r6 = r0;
        r2 = r7;
        goto L_0x0150;
    L_0x00a8:
        r3 = r0 instanceof kotlin.Result.Failure;
        if (r3 != 0) goto L_0x015e;
    L_0x00ac:
        r0 = new kotlin.jvm.internal.Ref$IntRef;
        r0.<init>();
        r3 = 0;
        r0.element = r3;
        r3 = 0;
        r6 = r3;
        r6 = (java.lang.Throwable) r6;
        r3 = r17.iterator();	 Catch:{ Throwable -> 0x014c, all -> 0x0147 }
        r5 = r17;
        r8 = r5;
        r10 = r8;
        r13 = r10;
        r12 = r18;
        r11 = r19;
        r7 = r2;
        r2 = r13;
    L_0x00c7:
        r1.L$0 = r13;	 Catch:{ Throwable -> 0x0145 }
        r1.L$1 = r12;	 Catch:{ Throwable -> 0x0145 }
        r1.L$2 = r11;	 Catch:{ Throwable -> 0x0145 }
        r1.L$3 = r10;	 Catch:{ Throwable -> 0x0145 }
        r1.L$4 = r0;	 Catch:{ Throwable -> 0x0145 }
        r1.L$5 = r8;	 Catch:{ Throwable -> 0x0145 }
        r1.L$6 = r2;	 Catch:{ Throwable -> 0x0145 }
        r1.L$7 = r6;	 Catch:{ Throwable -> 0x0145 }
        r1.L$8 = r5;	 Catch:{ Throwable -> 0x0145 }
        r1.L$9 = r3;	 Catch:{ Throwable -> 0x0145 }
        r1.label = r4;	 Catch:{ Throwable -> 0x0145 }
        r9 = r3.hasNext(r1);	 Catch:{ Throwable -> 0x0145 }
        if (r9 != r7) goto L_0x00e4;
    L_0x00e3:
        return r7;
    L_0x00e4:
        r16 = r9;
        r9 = r0;
        r0 = r16;
    L_0x00e9:
        r0 = (java.lang.Boolean) r0;	 Catch:{ Throwable -> 0x0145 }
        r0 = r0.booleanValue();	 Catch:{ Throwable -> 0x0145 }
        if (r0 == 0) goto L_0x0138;
    L_0x00f1:
        r1.L$0 = r13;	 Catch:{ Throwable -> 0x0145 }
        r1.L$1 = r12;	 Catch:{ Throwable -> 0x0145 }
        r1.L$2 = r11;	 Catch:{ Throwable -> 0x0145 }
        r1.L$3 = r10;	 Catch:{ Throwable -> 0x0145 }
        r1.L$4 = r9;	 Catch:{ Throwable -> 0x0145 }
        r1.L$5 = r8;	 Catch:{ Throwable -> 0x0145 }
        r1.L$6 = r2;	 Catch:{ Throwable -> 0x0145 }
        r1.L$7 = r6;	 Catch:{ Throwable -> 0x0145 }
        r1.L$8 = r5;	 Catch:{ Throwable -> 0x0145 }
        r1.L$9 = r3;	 Catch:{ Throwable -> 0x0145 }
        r0 = 2;
        r1.label = r0;	 Catch:{ Throwable -> 0x0145 }
        r0 = r3.next(r1);	 Catch:{ Throwable -> 0x0145 }
        if (r0 != r7) goto L_0x010f;
    L_0x010e:
        return r7;
    L_0x010f:
        r14 = new kotlin.collections.IndexedValue;	 Catch:{ Throwable -> 0x0145 }
        r15 = r9.element;	 Catch:{ Throwable -> 0x0145 }
        r4 = r15 + 1;
        r9.element = r4;	 Catch:{ Throwable -> 0x0145 }
        r14.<init>(r15, r0);	 Catch:{ Throwable -> 0x0145 }
        r0 = r14.component1();	 Catch:{ Throwable -> 0x0145 }
        r4 = r14.component2();	 Catch:{ Throwable -> 0x0145 }
        r0 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r0);	 Catch:{ Throwable -> 0x0145 }
        r0 = r11.invoke(r0, r4);	 Catch:{ Throwable -> 0x0145 }
        r0 = (java.lang.Boolean) r0;	 Catch:{ Throwable -> 0x0145 }
        r0 = r0.booleanValue();	 Catch:{ Throwable -> 0x0145 }
        if (r0 == 0) goto L_0x0135;
    L_0x0132:
        r12.add(r4);	 Catch:{ Throwable -> 0x0145 }
    L_0x0135:
        r0 = r9;
        r4 = 1;
        goto L_0x00c7;
    L_0x0138:
        r0 = kotlin.Unit.INSTANCE;	 Catch:{ Throwable -> 0x0145 }
        r1 = 1;
        kotlin.jvm.internal.InlineMarker.finallyStart(r1);
        r2.cancel(r6);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r1);
        return r12;
    L_0x0145:
        r0 = move-exception;
        goto L_0x014f;
    L_0x0147:
        r0 = move-exception;
        r2 = r17;
        goto L_0x00a0;
    L_0x014c:
        r0 = move-exception;
        r2 = r17;
    L_0x014f:
        r6 = r0;
    L_0x0150:
        throw r6;	 Catch:{ all -> 0x0151 }
    L_0x0151:
        r0 = move-exception;
        goto L_0x00a0;
    L_0x0154:
        kotlin.jvm.internal.InlineMarker.finallyStart(r1);
        r2.cancel(r6);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r1);
        throw r0;
    L_0x015e:
        r0 = (kotlin.Result.Failure) r0;
        r0 = r0.exception;
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.filterIndexedTo(kotlinx.coroutines.channels.ReceiveChannel, java.util.Collection, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @org.jetbrains.annotations.Nullable
    @kotlinx.coroutines.ObsoleteCoroutinesApi
    public static final <E, C extends kotlinx.coroutines.channels.SendChannel<? super E>> java.lang.Object filterIndexedTo(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r18, @org.jetbrains.annotations.NotNull C r19, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function2<? super java.lang.Integer, ? super E, java.lang.Boolean> r20, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super C> r21) {
        /*
        r0 = r21;
        r1 = r0 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filterIndexedTo$3;
        if (r1 == 0) goto L_0x0016;
    L_0x0006:
        r1 = r0;
        r1 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filterIndexedTo$3) r1;
        r2 = r1.label;
        r3 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r2 = r2 & r3;
        if (r2 == 0) goto L_0x0016;
    L_0x0010:
        r0 = r1.label;
        r0 = r0 - r3;
        r1.label = r0;
        goto L_0x001b;
    L_0x0016:
        r1 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filterIndexedTo$3;
        r1.<init>(r0);
    L_0x001b:
        r0 = r1.result;
        r2 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        r3 = r1.label;
        r4 = 1;
        switch(r3) {
            case 0: goto L_0x00ec;
            case 1: goto L_0x00ab;
            case 2: goto L_0x0073;
            case 3: goto L_0x002f;
            default: goto L_0x0027;
        };
    L_0x0027:
        r0 = new java.lang.IllegalStateException;
        r1 = "call to 'resume' before 'invoke' with coroutine";
        r0.<init>(r1);
        throw r0;
    L_0x002f:
        r3 = r1.L$13;
        r3 = r1.I$0;
        r3 = r1.L$12;
        r3 = (kotlin.collections.IndexedValue) r3;
        r3 = r1.L$11;
        r3 = r1.L$10;
        r3 = r1.L$9;
        r3 = (kotlinx.coroutines.channels.ChannelIterator) r3;
        r5 = r1.L$8;
        r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5;
        r6 = r1.L$7;
        r6 = (java.lang.Throwable) r6;
        r7 = r1.L$6;
        r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7;
        r8 = r1.L$5;
        r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8;
        r9 = r1.L$4;
        r9 = (kotlin.jvm.internal.Ref.IntRef) r9;
        r10 = r1.L$3;
        r10 = (kotlinx.coroutines.channels.ReceiveChannel) r10;
        r11 = r1.L$2;
        r11 = (kotlin.jvm.functions.Function2) r11;
        r12 = r1.L$1;
        r12 = (kotlinx.coroutines.channels.SendChannel) r12;
        r13 = r1.L$0;
        r13 = (kotlinx.coroutines.channels.ReceiveChannel) r13;
        r14 = r0 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x00e7, all -> 0x00e2 }
        if (r14 != 0) goto L_0x006e;
    L_0x0067:
        r17 = r7;
        r7 = r2;
        r2 = r17;
        goto L_0x01a3;
    L_0x006e:
        r0 = (kotlin.Result.Failure) r0;	 Catch:{ Throwable -> 0x00e7, all -> 0x00e2 }
        r0 = r0.exception;	 Catch:{ Throwable -> 0x00e7, all -> 0x00e2 }
        throw r0;	 Catch:{ Throwable -> 0x00e7, all -> 0x00e2 }
    L_0x0073:
        r3 = r1.L$9;
        r3 = (kotlinx.coroutines.channels.ChannelIterator) r3;
        r5 = r1.L$8;
        r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5;
        r6 = r1.L$7;
        r6 = (java.lang.Throwable) r6;
        r7 = r1.L$6;
        r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7;
        r8 = r1.L$5;
        r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8;
        r9 = r1.L$4;
        r9 = (kotlin.jvm.internal.Ref.IntRef) r9;
        r10 = r1.L$3;
        r10 = (kotlinx.coroutines.channels.ReceiveChannel) r10;
        r11 = r1.L$2;
        r11 = (kotlin.jvm.functions.Function2) r11;
        r12 = r1.L$1;
        r12 = (kotlinx.coroutines.channels.SendChannel) r12;
        r13 = r1.L$0;
        r13 = (kotlinx.coroutines.channels.ReceiveChannel) r13;
        r14 = r0 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x00e7, all -> 0x00e2 }
        if (r14 != 0) goto L_0x00a6;
    L_0x009f:
        r17 = r7;
        r7 = r2;
        r2 = r17;
        goto L_0x0154;
    L_0x00a6:
        r0 = (kotlin.Result.Failure) r0;	 Catch:{ Throwable -> 0x00e7, all -> 0x00e2 }
        r0 = r0.exception;	 Catch:{ Throwable -> 0x00e7, all -> 0x00e2 }
        throw r0;	 Catch:{ Throwable -> 0x00e7, all -> 0x00e2 }
    L_0x00ab:
        r3 = r1.L$9;
        r3 = (kotlinx.coroutines.channels.ChannelIterator) r3;
        r5 = r1.L$8;
        r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5;
        r6 = r1.L$7;
        r6 = (java.lang.Throwable) r6;
        r7 = r1.L$6;
        r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7;
        r8 = r1.L$5;
        r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8;
        r9 = r1.L$4;
        r9 = (kotlin.jvm.internal.Ref.IntRef) r9;
        r10 = r1.L$3;
        r10 = (kotlinx.coroutines.channels.ReceiveChannel) r10;
        r11 = r1.L$2;
        r11 = (kotlin.jvm.functions.Function2) r11;
        r12 = r1.L$1;
        r12 = (kotlinx.coroutines.channels.SendChannel) r12;
        r13 = r1.L$0;
        r13 = (kotlinx.coroutines.channels.ReceiveChannel) r13;
        r14 = r0 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x00e7, all -> 0x00e2 }
        if (r14 != 0) goto L_0x00dd;
    L_0x00d7:
        r17 = r7;
        r7 = r2;
        r2 = r17;
        goto L_0x012e;
    L_0x00dd:
        r0 = (kotlin.Result.Failure) r0;	 Catch:{ Throwable -> 0x00e7, all -> 0x00e2 }
        r0 = r0.exception;	 Catch:{ Throwable -> 0x00e7, all -> 0x00e2 }
        throw r0;	 Catch:{ Throwable -> 0x00e7, all -> 0x00e2 }
    L_0x00e2:
        r0 = move-exception;
        r2 = r7;
    L_0x00e4:
        r1 = 1;
        goto L_0x01c7;
    L_0x00e7:
        r0 = move-exception;
        r6 = r0;
        r2 = r7;
        goto L_0x01c3;
    L_0x00ec:
        r3 = r0 instanceof kotlin.Result.Failure;
        if (r3 != 0) goto L_0x01d1;
    L_0x00f0:
        r0 = new kotlin.jvm.internal.Ref$IntRef;
        r0.<init>();
        r3 = 0;
        r0.element = r3;
        r3 = 0;
        r6 = r3;
        r6 = (java.lang.Throwable) r6;
        r3 = r18.iterator();	 Catch:{ Throwable -> 0x01bf, all -> 0x01ba }
        r5 = r18;
        r8 = r5;
        r10 = r8;
        r13 = r10;
        r12 = r19;
        r11 = r20;
        r9 = r0;
        r0 = r2;
        r2 = r13;
    L_0x010c:
        r1.L$0 = r13;	 Catch:{ Throwable -> 0x01b8 }
        r1.L$1 = r12;	 Catch:{ Throwable -> 0x01b8 }
        r1.L$2 = r11;	 Catch:{ Throwable -> 0x01b8 }
        r1.L$3 = r10;	 Catch:{ Throwable -> 0x01b8 }
        r1.L$4 = r9;	 Catch:{ Throwable -> 0x01b8 }
        r1.L$5 = r8;	 Catch:{ Throwable -> 0x01b8 }
        r1.L$6 = r2;	 Catch:{ Throwable -> 0x01b8 }
        r1.L$7 = r6;	 Catch:{ Throwable -> 0x01b8 }
        r1.L$8 = r5;	 Catch:{ Throwable -> 0x01b8 }
        r1.L$9 = r3;	 Catch:{ Throwable -> 0x01b8 }
        r1.label = r4;	 Catch:{ Throwable -> 0x01b8 }
        r7 = r3.hasNext(r1);	 Catch:{ Throwable -> 0x01b8 }
        if (r7 != r0) goto L_0x0129;
    L_0x0128:
        return r0;
    L_0x0129:
        r17 = r7;
        r7 = r0;
        r0 = r17;
    L_0x012e:
        r0 = (java.lang.Boolean) r0;	 Catch:{ Throwable -> 0x01b8 }
        r0 = r0.booleanValue();	 Catch:{ Throwable -> 0x01b8 }
        if (r0 == 0) goto L_0x01ab;
    L_0x0136:
        r1.L$0 = r13;	 Catch:{ Throwable -> 0x01b8 }
        r1.L$1 = r12;	 Catch:{ Throwable -> 0x01b8 }
        r1.L$2 = r11;	 Catch:{ Throwable -> 0x01b8 }
        r1.L$3 = r10;	 Catch:{ Throwable -> 0x01b8 }
        r1.L$4 = r9;	 Catch:{ Throwable -> 0x01b8 }
        r1.L$5 = r8;	 Catch:{ Throwable -> 0x01b8 }
        r1.L$6 = r2;	 Catch:{ Throwable -> 0x01b8 }
        r1.L$7 = r6;	 Catch:{ Throwable -> 0x01b8 }
        r1.L$8 = r5;	 Catch:{ Throwable -> 0x01b8 }
        r1.L$9 = r3;	 Catch:{ Throwable -> 0x01b8 }
        r0 = 2;
        r1.label = r0;	 Catch:{ Throwable -> 0x01b8 }
        r0 = r3.next(r1);	 Catch:{ Throwable -> 0x01b8 }
        if (r0 != r7) goto L_0x0154;
    L_0x0153:
        return r7;
    L_0x0154:
        r14 = new kotlin.collections.IndexedValue;	 Catch:{ Throwable -> 0x01b8 }
        r15 = r9.element;	 Catch:{ Throwable -> 0x01b8 }
        r4 = r15 + 1;
        r9.element = r4;	 Catch:{ Throwable -> 0x01b8 }
        r14.<init>(r15, r0);	 Catch:{ Throwable -> 0x01b8 }
        r4 = r14.component1();	 Catch:{ Throwable -> 0x01b8 }
        r15 = r14.component2();	 Catch:{ Throwable -> 0x01b8 }
        r16 = r7;
        r7 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r4);	 Catch:{ Throwable -> 0x01b8 }
        r7 = r11.invoke(r7, r15);	 Catch:{ Throwable -> 0x01b8 }
        r7 = (java.lang.Boolean) r7;	 Catch:{ Throwable -> 0x01b8 }
        r7 = r7.booleanValue();	 Catch:{ Throwable -> 0x01b8 }
        if (r7 == 0) goto L_0x01a5;
    L_0x0179:
        r1.L$0 = r13;	 Catch:{ Throwable -> 0x01b8 }
        r1.L$1 = r12;	 Catch:{ Throwable -> 0x01b8 }
        r1.L$2 = r11;	 Catch:{ Throwable -> 0x01b8 }
        r1.L$3 = r10;	 Catch:{ Throwable -> 0x01b8 }
        r1.L$4 = r9;	 Catch:{ Throwable -> 0x01b8 }
        r1.L$5 = r8;	 Catch:{ Throwable -> 0x01b8 }
        r1.L$6 = r2;	 Catch:{ Throwable -> 0x01b8 }
        r1.L$7 = r6;	 Catch:{ Throwable -> 0x01b8 }
        r1.L$8 = r5;	 Catch:{ Throwable -> 0x01b8 }
        r1.L$9 = r3;	 Catch:{ Throwable -> 0x01b8 }
        r1.L$10 = r0;	 Catch:{ Throwable -> 0x01b8 }
        r1.L$11 = r0;	 Catch:{ Throwable -> 0x01b8 }
        r1.L$12 = r14;	 Catch:{ Throwable -> 0x01b8 }
        r1.I$0 = r4;	 Catch:{ Throwable -> 0x01b8 }
        r1.L$13 = r15;	 Catch:{ Throwable -> 0x01b8 }
        r0 = 3;
        r1.label = r0;	 Catch:{ Throwable -> 0x01b8 }
        r0 = r12.send(r15, r1);	 Catch:{ Throwable -> 0x01b8 }
        r7 = r16;
        if (r0 != r7) goto L_0x01a3;
    L_0x01a2:
        return r7;
    L_0x01a3:
        r0 = r7;
        goto L_0x01a8;
    L_0x01a5:
        r7 = r16;
        goto L_0x01a3;
    L_0x01a8:
        r4 = 1;
        goto L_0x010c;
    L_0x01ab:
        r0 = kotlin.Unit.INSTANCE;	 Catch:{ Throwable -> 0x01b8 }
        r1 = 1;
        kotlin.jvm.internal.InlineMarker.finallyStart(r1);
        r2.cancel(r6);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r1);
        return r12;
    L_0x01b8:
        r0 = move-exception;
        goto L_0x01c2;
    L_0x01ba:
        r0 = move-exception;
        r2 = r18;
        goto L_0x00e4;
    L_0x01bf:
        r0 = move-exception;
        r2 = r18;
    L_0x01c2:
        r6 = r0;
    L_0x01c3:
        throw r6;	 Catch:{ all -> 0x01c4 }
    L_0x01c4:
        r0 = move-exception;
        goto L_0x00e4;
    L_0x01c7:
        kotlin.jvm.internal.InlineMarker.finallyStart(r1);
        r2.cancel(r6);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r1);
        throw r0;
    L_0x01d1:
        r0 = (kotlin.Result.Failure) r0;
        r0 = r0.exception;
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.filterIndexedTo(kotlinx.coroutines.channels.ReceiveChannel, kotlinx.coroutines.channels.SendChannel, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @NotNull
    @ObsoleteCoroutinesApi
    public static /* synthetic */ ReceiveChannel filterNot$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return ChannelsKt.filterNot(receiveChannel, coroutineContext, function2);
    }

    @NotNull
    @ObsoleteCoroutinesApi
    public static final <E> ReceiveChannel<E> filterNot(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull CoroutineContext coroutineContext, @NotNull Function2<? super E, ? super Continuation<? super Boolean>, ? extends Object> function2) {
        Intrinsics.checkParameterIsNotNull(receiveChannel, "receiver$0");
        Intrinsics.checkParameterIsNotNull(coroutineContext, "context");
        Intrinsics.checkParameterIsNotNull(function2, "predicate");
        return ChannelsKt.filter(receiveChannel, coroutineContext, new ChannelsKt__Channels_commonKt$filterNot$1(function2, null));
    }

    @NotNull
    @ObsoleteCoroutinesApi
    public static final <E> ReceiveChannel<E> filterNotNull(@NotNull ReceiveChannel<? extends E> receiveChannel) {
        Intrinsics.checkParameterIsNotNull(receiveChannel, "receiver$0");
        receiveChannel = filter$default(receiveChannel, null, new ChannelsKt__Channels_commonKt$filterNotNull$1(null), 1, null);
        if (receiveChannel != null) {
            return receiveChannel;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.channels.ReceiveChannel<E>");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @org.jetbrains.annotations.Nullable
    @kotlinx.coroutines.ObsoleteCoroutinesApi
    public static final <E, C extends java.util.Collection<? super E>> java.lang.Object filterNotNullTo(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r9, @org.jetbrains.annotations.NotNull C r10, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super C> r11) {
        /*
        r0 = r11 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filterNotNullTo$1;
        if (r0 == 0) goto L_0x0014;
    L_0x0004:
        r0 = r11;
        r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filterNotNullTo$1) r0;
        r1 = r0.label;
        r2 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r1 = r1 & r2;
        if (r1 == 0) goto L_0x0014;
    L_0x000e:
        r11 = r0.label;
        r11 = r11 - r2;
        r0.label = r11;
        goto L_0x0019;
    L_0x0014:
        r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filterNotNullTo$1;
        r0.<init>(r11);
    L_0x0019:
        r11 = r0.result;
        r1 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        r2 = r0.label;
        switch(r2) {
            case 0: goto L_0x0081;
            case 1: goto L_0x0053;
            case 2: goto L_0x002c;
            default: goto L_0x0024;
        };
    L_0x0024:
        r9 = new java.lang.IllegalStateException;
        r10 = "call to 'resume' before 'invoke' with coroutine";
        r9.<init>(r10);
        throw r9;
    L_0x002c:
        r9 = r0.L$6;
        r9 = (kotlinx.coroutines.channels.ChannelIterator) r9;
        r10 = r0.L$5;
        r10 = (kotlinx.coroutines.channels.ReceiveChannel) r10;
        r2 = r0.L$4;
        r2 = (java.lang.Throwable) r2;
        r3 = r0.L$3;
        r3 = (kotlinx.coroutines.channels.ReceiveChannel) r3;
        r4 = r0.L$2;
        r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4;
        r5 = r0.L$1;
        r5 = (java.util.Collection) r5;
        r6 = r0.L$0;
        r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6;
        r7 = r11 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x007c, all -> 0x0079 }
        if (r7 != 0) goto L_0x004e;
    L_0x004c:
        goto L_0x00cd;
    L_0x004e:
        r11 = (kotlin.Result.Failure) r11;	 Catch:{ Throwable -> 0x007c, all -> 0x0079 }
        r9 = r11.exception;	 Catch:{ Throwable -> 0x007c, all -> 0x0079 }
        throw r9;	 Catch:{ Throwable -> 0x007c, all -> 0x0079 }
    L_0x0053:
        r9 = r0.L$6;
        r9 = (kotlinx.coroutines.channels.ChannelIterator) r9;
        r10 = r0.L$5;
        r10 = (kotlinx.coroutines.channels.ReceiveChannel) r10;
        r2 = r0.L$4;
        r2 = (java.lang.Throwable) r2;
        r3 = r0.L$3;
        r3 = (kotlinx.coroutines.channels.ReceiveChannel) r3;
        r4 = r0.L$2;
        r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4;
        r5 = r0.L$1;
        r5 = (java.util.Collection) r5;
        r6 = r0.L$0;
        r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6;
        r7 = r11 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x007c, all -> 0x0079 }
        if (r7 != 0) goto L_0x0074;
    L_0x0073:
        goto L_0x00ad;
    L_0x0074:
        r11 = (kotlin.Result.Failure) r11;	 Catch:{ Throwable -> 0x007c, all -> 0x0079 }
        r9 = r11.exception;	 Catch:{ Throwable -> 0x007c, all -> 0x0079 }
        throw r9;	 Catch:{ Throwable -> 0x007c, all -> 0x0079 }
    L_0x0079:
        r9 = move-exception;
        goto L_0x00e2;
    L_0x007c:
        r9 = move-exception;
        r2 = r9;
        r9 = r3;
        goto L_0x00e1;
    L_0x0081:
        r2 = r11 instanceof kotlin.Result.Failure;
        if (r2 != 0) goto L_0x00e6;
    L_0x0085:
        r11 = 0;
        r2 = r11;
        r2 = (java.lang.Throwable) r2;
        r11 = r9.iterator();	 Catch:{ Throwable -> 0x00df }
        r4 = r9;
        r6 = r4;
        r5 = r10;
        r10 = r6;
    L_0x0091:
        r0.L$0 = r6;	 Catch:{ Throwable -> 0x00df }
        r0.L$1 = r5;	 Catch:{ Throwable -> 0x00df }
        r0.L$2 = r4;	 Catch:{ Throwable -> 0x00df }
        r0.L$3 = r9;	 Catch:{ Throwable -> 0x00df }
        r0.L$4 = r2;	 Catch:{ Throwable -> 0x00df }
        r0.L$5 = r10;	 Catch:{ Throwable -> 0x00df }
        r0.L$6 = r11;	 Catch:{ Throwable -> 0x00df }
        r3 = 1;
        r0.label = r3;	 Catch:{ Throwable -> 0x00df }
        r3 = r11.hasNext(r0);	 Catch:{ Throwable -> 0x00df }
        if (r3 != r1) goto L_0x00a9;
    L_0x00a8:
        return r1;
    L_0x00a9:
        r8 = r3;
        r3 = r9;
        r9 = r11;
        r11 = r8;
    L_0x00ad:
        r11 = (java.lang.Boolean) r11;	 Catch:{ Throwable -> 0x007c, all -> 0x0079 }
        r11 = r11.booleanValue();	 Catch:{ Throwable -> 0x007c, all -> 0x0079 }
        if (r11 == 0) goto L_0x00d5;
    L_0x00b5:
        r0.L$0 = r6;	 Catch:{ Throwable -> 0x007c, all -> 0x0079 }
        r0.L$1 = r5;	 Catch:{ Throwable -> 0x007c, all -> 0x0079 }
        r0.L$2 = r4;	 Catch:{ Throwable -> 0x007c, all -> 0x0079 }
        r0.L$3 = r3;	 Catch:{ Throwable -> 0x007c, all -> 0x0079 }
        r0.L$4 = r2;	 Catch:{ Throwable -> 0x007c, all -> 0x0079 }
        r0.L$5 = r10;	 Catch:{ Throwable -> 0x007c, all -> 0x0079 }
        r0.L$6 = r9;	 Catch:{ Throwable -> 0x007c, all -> 0x0079 }
        r11 = 2;
        r0.label = r11;	 Catch:{ Throwable -> 0x007c, all -> 0x0079 }
        r11 = r9.next(r0);	 Catch:{ Throwable -> 0x007c, all -> 0x0079 }
        if (r11 != r1) goto L_0x00cd;
    L_0x00cc:
        return r1;
    L_0x00cd:
        if (r11 == 0) goto L_0x00d2;
    L_0x00cf:
        r5.add(r11);	 Catch:{ Throwable -> 0x007c, all -> 0x0079 }
    L_0x00d2:
        r11 = r9;
        r9 = r3;
        goto L_0x0091;
    L_0x00d5:
        r9 = kotlin.Unit.INSTANCE;	 Catch:{ Throwable -> 0x007c, all -> 0x0079 }
        r3.cancel(r2);
        return r5;
    L_0x00db:
        r10 = move-exception;
        r3 = r9;
        r9 = r10;
        goto L_0x00e2;
    L_0x00df:
        r10 = move-exception;
        r2 = r10;
    L_0x00e1:
        throw r2;	 Catch:{ all -> 0x00db }
    L_0x00e2:
        r3.cancel(r2);
        throw r9;
    L_0x00e6:
        r11 = (kotlin.Result.Failure) r11;
        r9 = r11.exception;
        throw r9;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.filterNotNullTo(kotlinx.coroutines.channels.ReceiveChannel, java.util.Collection, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @org.jetbrains.annotations.Nullable
    @kotlinx.coroutines.ObsoleteCoroutinesApi
    public static final <E, C extends kotlinx.coroutines.channels.SendChannel<? super E>> java.lang.Object filterNotNullTo(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r9, @org.jetbrains.annotations.NotNull C r10, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super C> r11) {
        /*
        r0 = r11 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filterNotNullTo$3;
        if (r0 == 0) goto L_0x0014;
    L_0x0004:
        r0 = r11;
        r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filterNotNullTo$3) r0;
        r1 = r0.label;
        r2 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r1 = r1 & r2;
        if (r1 == 0) goto L_0x0014;
    L_0x000e:
        r11 = r0.label;
        r11 = r11 - r2;
        r0.label = r11;
        goto L_0x0019;
    L_0x0014:
        r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filterNotNullTo$3;
        r0.<init>(r11);
    L_0x0019:
        r11 = r0.result;
        r1 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        r2 = r0.label;
        switch(r2) {
            case 0: goto L_0x00b9;
            case 1: goto L_0x0086;
            case 2: goto L_0x005b;
            case 3: goto L_0x002c;
            default: goto L_0x0024;
        };
    L_0x0024:
        r9 = new java.lang.IllegalStateException;
        r10 = "call to 'resume' before 'invoke' with coroutine";
        r9.<init>(r10);
        throw r9;
    L_0x002c:
        r9 = r0.L$8;
        r9 = r0.L$7;
        r9 = r0.L$6;
        r9 = (kotlinx.coroutines.channels.ChannelIterator) r9;
        r10 = r0.L$5;
        r10 = (kotlinx.coroutines.channels.ReceiveChannel) r10;
        r2 = r0.L$4;
        r2 = (java.lang.Throwable) r2;
        r3 = r0.L$3;
        r3 = (kotlinx.coroutines.channels.ReceiveChannel) r3;
        r4 = r0.L$2;
        r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4;
        r5 = r0.L$1;
        r5 = (kotlinx.coroutines.channels.SendChannel) r5;
        r6 = r0.L$0;
        r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6;
        r7 = r11 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x00b4, all -> 0x00b0 }
        if (r7 != 0) goto L_0x0056;
    L_0x0050:
        r8 = r1;
        r1 = r10;
        r10 = r3;
        r3 = r8;
        goto L_0x0125;
    L_0x0056:
        r11 = (kotlin.Result.Failure) r11;	 Catch:{ Throwable -> 0x00b4, all -> 0x00b0 }
        r9 = r11.exception;	 Catch:{ Throwable -> 0x00b4, all -> 0x00b0 }
        throw r9;	 Catch:{ Throwable -> 0x00b4, all -> 0x00b0 }
    L_0x005b:
        r9 = r0.L$6;
        r9 = (kotlinx.coroutines.channels.ChannelIterator) r9;
        r10 = r0.L$5;
        r10 = (kotlinx.coroutines.channels.ReceiveChannel) r10;
        r2 = r0.L$4;
        r2 = (java.lang.Throwable) r2;
        r3 = r0.L$3;
        r3 = (kotlinx.coroutines.channels.ReceiveChannel) r3;
        r4 = r0.L$2;
        r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4;
        r5 = r0.L$1;
        r5 = (kotlinx.coroutines.channels.SendChannel) r5;
        r6 = r0.L$0;
        r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6;
        r7 = r11 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x00b4, all -> 0x00b0 }
        if (r7 != 0) goto L_0x0081;
    L_0x007b:
        r8 = r1;
        r1 = r10;
        r10 = r3;
        r3 = r8;
        goto L_0x0107;
    L_0x0081:
        r11 = (kotlin.Result.Failure) r11;	 Catch:{ Throwable -> 0x00b4, all -> 0x00b0 }
        r9 = r11.exception;	 Catch:{ Throwable -> 0x00b4, all -> 0x00b0 }
        throw r9;	 Catch:{ Throwable -> 0x00b4, all -> 0x00b0 }
    L_0x0086:
        r9 = r0.L$6;
        r9 = (kotlinx.coroutines.channels.ChannelIterator) r9;
        r10 = r0.L$5;
        r10 = (kotlinx.coroutines.channels.ReceiveChannel) r10;
        r2 = r0.L$4;
        r2 = (java.lang.Throwable) r2;
        r3 = r0.L$3;
        r3 = (kotlinx.coroutines.channels.ReceiveChannel) r3;
        r4 = r0.L$2;
        r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4;
        r5 = r0.L$1;
        r5 = (kotlinx.coroutines.channels.SendChannel) r5;
        r6 = r0.L$0;
        r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6;
        r7 = r11 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x00b4, all -> 0x00b0 }
        if (r7 != 0) goto L_0x00ab;
    L_0x00a6:
        r8 = r1;
        r1 = r10;
        r10 = r3;
    L_0x00a9:
        r3 = r8;
        goto L_0x00e7;
    L_0x00ab:
        r11 = (kotlin.Result.Failure) r11;	 Catch:{ Throwable -> 0x00b4, all -> 0x00b0 }
        r9 = r11.exception;	 Catch:{ Throwable -> 0x00b4, all -> 0x00b0 }
        throw r9;	 Catch:{ Throwable -> 0x00b4, all -> 0x00b0 }
    L_0x00b0:
        r9 = move-exception;
        r10 = r3;
        goto L_0x013c;
    L_0x00b4:
        r9 = move-exception;
        r2 = r9;
        r9 = r3;
        goto L_0x013b;
    L_0x00b9:
        r2 = r11 instanceof kotlin.Result.Failure;
        if (r2 != 0) goto L_0x0140;
    L_0x00bd:
        r11 = 0;
        r2 = r11;
        r2 = (java.lang.Throwable) r2;
        r11 = r9.iterator();	 Catch:{ Throwable -> 0x0139 }
        r4 = r9;
        r6 = r4;
        r5 = r10;
        r10 = r6;
        r9 = r11;
        r11 = r10;
    L_0x00cb:
        r0.L$0 = r6;	 Catch:{ Throwable -> 0x0130, all -> 0x012e }
        r0.L$1 = r5;	 Catch:{ Throwable -> 0x0130, all -> 0x012e }
        r0.L$2 = r4;	 Catch:{ Throwable -> 0x0130, all -> 0x012e }
        r0.L$3 = r10;	 Catch:{ Throwable -> 0x0130, all -> 0x012e }
        r0.L$4 = r2;	 Catch:{ Throwable -> 0x0130, all -> 0x012e }
        r0.L$5 = r11;	 Catch:{ Throwable -> 0x0130, all -> 0x012e }
        r0.L$6 = r9;	 Catch:{ Throwable -> 0x0130, all -> 0x012e }
        r3 = 1;
        r0.label = r3;	 Catch:{ Throwable -> 0x0130, all -> 0x012e }
        r3 = r9.hasNext(r0);	 Catch:{ Throwable -> 0x0130, all -> 0x012e }
        if (r3 != r1) goto L_0x00e3;
    L_0x00e2:
        return r1;
    L_0x00e3:
        r8 = r1;
        r1 = r11;
        r11 = r3;
        goto L_0x00a9;
    L_0x00e7:
        r11 = (java.lang.Boolean) r11;	 Catch:{ Throwable -> 0x0130, all -> 0x012e }
        r11 = r11.booleanValue();	 Catch:{ Throwable -> 0x0130, all -> 0x012e }
        if (r11 == 0) goto L_0x0128;
    L_0x00ef:
        r0.L$0 = r6;	 Catch:{ Throwable -> 0x0130, all -> 0x012e }
        r0.L$1 = r5;	 Catch:{ Throwable -> 0x0130, all -> 0x012e }
        r0.L$2 = r4;	 Catch:{ Throwable -> 0x0130, all -> 0x012e }
        r0.L$3 = r10;	 Catch:{ Throwable -> 0x0130, all -> 0x012e }
        r0.L$4 = r2;	 Catch:{ Throwable -> 0x0130, all -> 0x012e }
        r0.L$5 = r1;	 Catch:{ Throwable -> 0x0130, all -> 0x012e }
        r0.L$6 = r9;	 Catch:{ Throwable -> 0x0130, all -> 0x012e }
        r11 = 2;
        r0.label = r11;	 Catch:{ Throwable -> 0x0130, all -> 0x012e }
        r11 = r9.next(r0);	 Catch:{ Throwable -> 0x0130, all -> 0x012e }
        if (r11 != r3) goto L_0x0107;
    L_0x0106:
        return r3;
    L_0x0107:
        if (r11 == 0) goto L_0x0125;
    L_0x0109:
        r0.L$0 = r6;	 Catch:{ Throwable -> 0x0130, all -> 0x012e }
        r0.L$1 = r5;	 Catch:{ Throwable -> 0x0130, all -> 0x012e }
        r0.L$2 = r4;	 Catch:{ Throwable -> 0x0130, all -> 0x012e }
        r0.L$3 = r10;	 Catch:{ Throwable -> 0x0130, all -> 0x012e }
        r0.L$4 = r2;	 Catch:{ Throwable -> 0x0130, all -> 0x012e }
        r0.L$5 = r1;	 Catch:{ Throwable -> 0x0130, all -> 0x012e }
        r0.L$6 = r9;	 Catch:{ Throwable -> 0x0130, all -> 0x012e }
        r0.L$7 = r11;	 Catch:{ Throwable -> 0x0130, all -> 0x012e }
        r0.L$8 = r11;	 Catch:{ Throwable -> 0x0130, all -> 0x012e }
        r7 = 3;
        r0.label = r7;	 Catch:{ Throwable -> 0x0130, all -> 0x012e }
        r11 = r5.send(r11, r0);	 Catch:{ Throwable -> 0x0130, all -> 0x012e }
        if (r11 != r3) goto L_0x0125;
    L_0x0124:
        return r3;
    L_0x0125:
        r11 = r1;
        r1 = r3;
        goto L_0x00cb;
    L_0x0128:
        r9 = kotlin.Unit.INSTANCE;	 Catch:{ Throwable -> 0x0130, all -> 0x012e }
        r10.cancel(r2);
        return r5;
    L_0x012e:
        r9 = move-exception;
        goto L_0x013c;
    L_0x0130:
        r9 = move-exception;
        r2 = r9;
        r9 = r10;
        goto L_0x013b;
    L_0x0134:
        r10 = move-exception;
        r8 = r10;
        r10 = r9;
        r9 = r8;
        goto L_0x013c;
    L_0x0139:
        r10 = move-exception;
        r2 = r10;
    L_0x013b:
        throw r2;	 Catch:{ all -> 0x0134 }
    L_0x013c:
        r10.cancel(r2);
        throw r9;
    L_0x0140:
        r11 = (kotlin.Result.Failure) r11;
        r9 = r11.exception;
        throw r9;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.filterNotNullTo(kotlinx.coroutines.channels.ReceiveChannel, kotlinx.coroutines.channels.SendChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @org.jetbrains.annotations.Nullable
    @kotlinx.coroutines.ObsoleteCoroutinesApi
    public static final <E, C extends java.util.Collection<? super E>> java.lang.Object filterNotTo(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r10, @org.jetbrains.annotations.NotNull C r11, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super E, java.lang.Boolean> r12, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super C> r13) {
        /*
        r0 = r13 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filterNotTo$1;
        if (r0 == 0) goto L_0x0014;
    L_0x0004:
        r0 = r13;
        r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filterNotTo$1) r0;
        r1 = r0.label;
        r2 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r1 = r1 & r2;
        if (r1 == 0) goto L_0x0014;
    L_0x000e:
        r13 = r0.label;
        r13 = r13 - r2;
        r0.label = r13;
        goto L_0x0019;
    L_0x0014:
        r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filterNotTo$1;
        r0.<init>(r13);
    L_0x0019:
        r13 = r0.result;
        r1 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        r2 = r0.label;
        r3 = 1;
        switch(r2) {
            case 0: goto L_0x008d;
            case 1: goto L_0x005b;
            case 2: goto L_0x002d;
            default: goto L_0x0025;
        };
    L_0x0025:
        r10 = new java.lang.IllegalStateException;
        r11 = "call to 'resume' before 'invoke' with coroutine";
        r10.<init>(r11);
        throw r10;
    L_0x002d:
        r10 = r0.L$7;
        r10 = (kotlinx.coroutines.channels.ChannelIterator) r10;
        r11 = r0.L$6;
        r11 = (kotlinx.coroutines.channels.ReceiveChannel) r11;
        r12 = r0.L$5;
        r12 = (java.lang.Throwable) r12;
        r2 = r0.L$4;
        r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2;
        r4 = r0.L$3;
        r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4;
        r5 = r0.L$2;
        r5 = (kotlin.jvm.functions.Function1) r5;
        r6 = r0.L$1;
        r6 = (java.util.Collection) r6;
        r7 = r0.L$0;
        r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7;
        r8 = r13 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        if (r8 != 0) goto L_0x0056;
    L_0x0051:
        r9 = r2;
        r2 = r10;
        r10 = r9;
        goto L_0x00dc;
    L_0x0056:
        r13 = (kotlin.Result.Failure) r13;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r10 = r13.exception;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        throw r10;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
    L_0x005b:
        r10 = r0.L$7;
        r10 = (kotlinx.coroutines.channels.ChannelIterator) r10;
        r11 = r0.L$6;
        r11 = (kotlinx.coroutines.channels.ReceiveChannel) r11;
        r12 = r0.L$5;
        r12 = (java.lang.Throwable) r12;
        r2 = r0.L$4;
        r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2;
        r4 = r0.L$3;
        r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4;
        r5 = r0.L$2;
        r5 = (kotlin.jvm.functions.Function1) r5;
        r6 = r0.L$1;
        r6 = (java.util.Collection) r6;
        r7 = r0.L$0;
        r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7;
        r8 = r13 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        if (r8 != 0) goto L_0x0080;
    L_0x007f:
        goto L_0x00ba;
    L_0x0080:
        r13 = (kotlin.Result.Failure) r13;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r10 = r13.exception;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        throw r10;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
    L_0x0085:
        r10 = move-exception;
        goto L_0x0103;
    L_0x0088:
        r10 = move-exception;
        r12 = r10;
        r10 = r2;
        goto L_0x00ff;
    L_0x008d:
        r2 = r13 instanceof kotlin.Result.Failure;
        if (r2 != 0) goto L_0x010d;
    L_0x0091:
        r13 = 0;
        r13 = (java.lang.Throwable) r13;
        r2 = r10.iterator();	 Catch:{ Throwable -> 0x00fd, all -> 0x00f8 }
        r4 = r10;
        r7 = r4;
        r6 = r11;
        r5 = r12;
        r12 = r13;
        r11 = r7;
    L_0x009e:
        r0.L$0 = r7;	 Catch:{ Throwable -> 0x00fd }
        r0.L$1 = r6;	 Catch:{ Throwable -> 0x00fd }
        r0.L$2 = r5;	 Catch:{ Throwable -> 0x00fd }
        r0.L$3 = r4;	 Catch:{ Throwable -> 0x00fd }
        r0.L$4 = r10;	 Catch:{ Throwable -> 0x00fd }
        r0.L$5 = r12;	 Catch:{ Throwable -> 0x00fd }
        r0.L$6 = r11;	 Catch:{ Throwable -> 0x00fd }
        r0.L$7 = r2;	 Catch:{ Throwable -> 0x00fd }
        r0.label = r3;	 Catch:{ Throwable -> 0x00fd }
        r13 = r2.hasNext(r0);	 Catch:{ Throwable -> 0x00fd }
        if (r13 != r1) goto L_0x00b7;
    L_0x00b6:
        return r1;
    L_0x00b7:
        r9 = r2;
        r2 = r10;
        r10 = r9;
    L_0x00ba:
        r13 = (java.lang.Boolean) r13;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r13 = r13.booleanValue();	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        if (r13 == 0) goto L_0x00ec;
    L_0x00c2:
        r0.L$0 = r7;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r0.L$1 = r6;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r0.L$2 = r5;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r0.L$3 = r4;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r0.L$4 = r2;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r0.L$5 = r12;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r0.L$6 = r11;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r0.L$7 = r10;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r13 = 2;
        r0.label = r13;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r13 = r10.next(r0);	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        if (r13 != r1) goto L_0x0051;
    L_0x00db:
        return r1;
    L_0x00dc:
        r8 = r5.invoke(r13);	 Catch:{ Throwable -> 0x00fd }
        r8 = (java.lang.Boolean) r8;	 Catch:{ Throwable -> 0x00fd }
        r8 = r8.booleanValue();	 Catch:{ Throwable -> 0x00fd }
        if (r8 != 0) goto L_0x009e;
    L_0x00e8:
        r6.add(r13);	 Catch:{ Throwable -> 0x00fd }
        goto L_0x009e;
    L_0x00ec:
        r10 = kotlin.Unit.INSTANCE;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        kotlin.jvm.internal.InlineMarker.finallyStart(r3);
        r2.cancel(r12);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r3);
        return r6;
    L_0x00f8:
        r11 = move-exception;
        r2 = r10;
        r10 = r11;
        r12 = r13;
        goto L_0x0103;
    L_0x00fd:
        r11 = move-exception;
        r12 = r11;
    L_0x00ff:
        throw r12;	 Catch:{ all -> 0x0100 }
    L_0x0100:
        r11 = move-exception;
        r2 = r10;
        r10 = r11;
    L_0x0103:
        kotlin.jvm.internal.InlineMarker.finallyStart(r3);
        r2.cancel(r12);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r3);
        throw r10;
    L_0x010d:
        r13 = (kotlin.Result.Failure) r13;
        r10 = r13.exception;
        throw r10;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.filterNotTo(kotlinx.coroutines.channels.ReceiveChannel, java.util.Collection, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @org.jetbrains.annotations.Nullable
    @kotlinx.coroutines.ObsoleteCoroutinesApi
    public static final <E, C extends kotlinx.coroutines.channels.SendChannel<? super E>> java.lang.Object filterNotTo(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r10, @org.jetbrains.annotations.NotNull C r11, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super E, java.lang.Boolean> r12, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super C> r13) {
        /*
        r0 = r13 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filterNotTo$3;
        if (r0 == 0) goto L_0x0014;
    L_0x0004:
        r0 = r13;
        r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filterNotTo$3) r0;
        r1 = r0.label;
        r2 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r1 = r1 & r2;
        if (r1 == 0) goto L_0x0014;
    L_0x000e:
        r13 = r0.label;
        r13 = r13 - r2;
        r0.label = r13;
        goto L_0x0019;
    L_0x0014:
        r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filterNotTo$3;
        r0.<init>(r13);
    L_0x0019:
        r13 = r0.result;
        r1 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        r2 = r0.label;
        r3 = 1;
        switch(r2) {
            case 0: goto L_0x00c6;
            case 1: goto L_0x008f;
            case 2: goto L_0x0060;
            case 3: goto L_0x002d;
            default: goto L_0x0025;
        };
    L_0x0025:
        r10 = new java.lang.IllegalStateException;
        r11 = "call to 'resume' before 'invoke' with coroutine";
        r10.<init>(r11);
        throw r10;
    L_0x002d:
        r10 = r0.L$9;
        r10 = r0.L$8;
        r10 = r0.L$7;
        r10 = (kotlinx.coroutines.channels.ChannelIterator) r10;
        r11 = r0.L$6;
        r11 = (kotlinx.coroutines.channels.ReceiveChannel) r11;
        r12 = r0.L$5;
        r12 = (java.lang.Throwable) r12;
        r2 = r0.L$4;
        r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2;
        r4 = r0.L$3;
        r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4;
        r5 = r0.L$2;
        r5 = (kotlin.jvm.functions.Function1) r5;
        r6 = r0.L$1;
        r6 = (kotlinx.coroutines.channels.SendChannel) r6;
        r7 = r0.L$0;
        r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7;
        r8 = r13 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x00c1, all -> 0x00bd }
        if (r8 != 0) goto L_0x005b;
    L_0x0055:
        r9 = r1;
        r1 = r11;
        r11 = r2;
        r2 = r9;
        goto L_0x0142;
    L_0x005b:
        r13 = (kotlin.Result.Failure) r13;	 Catch:{ Throwable -> 0x00c1, all -> 0x00bd }
        r10 = r13.exception;	 Catch:{ Throwable -> 0x00c1, all -> 0x00bd }
        throw r10;	 Catch:{ Throwable -> 0x00c1, all -> 0x00bd }
    L_0x0060:
        r10 = r0.L$7;
        r10 = (kotlinx.coroutines.channels.ChannelIterator) r10;
        r11 = r0.L$6;
        r11 = (kotlinx.coroutines.channels.ReceiveChannel) r11;
        r12 = r0.L$5;
        r12 = (java.lang.Throwable) r12;
        r2 = r0.L$4;
        r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2;
        r4 = r0.L$3;
        r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4;
        r5 = r0.L$2;
        r5 = (kotlin.jvm.functions.Function1) r5;
        r6 = r0.L$1;
        r6 = (kotlinx.coroutines.channels.SendChannel) r6;
        r7 = r0.L$0;
        r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7;
        r8 = r13 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x00c1, all -> 0x00bd }
        if (r8 != 0) goto L_0x008a;
    L_0x0084:
        r9 = r1;
        r1 = r11;
        r11 = r2;
        r2 = r9;
        goto L_0x0118;
    L_0x008a:
        r13 = (kotlin.Result.Failure) r13;	 Catch:{ Throwable -> 0x00c1, all -> 0x00bd }
        r10 = r13.exception;	 Catch:{ Throwable -> 0x00c1, all -> 0x00bd }
        throw r10;	 Catch:{ Throwable -> 0x00c1, all -> 0x00bd }
    L_0x008f:
        r10 = r0.L$7;
        r10 = (kotlinx.coroutines.channels.ChannelIterator) r10;
        r11 = r0.L$6;
        r11 = (kotlinx.coroutines.channels.ReceiveChannel) r11;
        r12 = r0.L$5;
        r12 = (java.lang.Throwable) r12;
        r2 = r0.L$4;
        r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2;
        r4 = r0.L$3;
        r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4;
        r5 = r0.L$2;
        r5 = (kotlin.jvm.functions.Function1) r5;
        r6 = r0.L$1;
        r6 = (kotlinx.coroutines.channels.SendChannel) r6;
        r7 = r0.L$0;
        r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7;
        r8 = r13 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x00c1, all -> 0x00bd }
        if (r8 != 0) goto L_0x00b8;
    L_0x00b3:
        r9 = r1;
        r1 = r11;
        r11 = r2;
    L_0x00b6:
        r2 = r9;
        goto L_0x00f6;
    L_0x00b8:
        r13 = (kotlin.Result.Failure) r13;	 Catch:{ Throwable -> 0x00c1, all -> 0x00bd }
        r10 = r13.exception;	 Catch:{ Throwable -> 0x00c1, all -> 0x00bd }
        throw r10;	 Catch:{ Throwable -> 0x00c1, all -> 0x00bd }
    L_0x00bd:
        r10 = move-exception;
        r11 = r2;
        goto L_0x0162;
    L_0x00c1:
        r10 = move-exception;
        r12 = r10;
        r10 = r2;
        goto L_0x015f;
    L_0x00c6:
        r2 = r13 instanceof kotlin.Result.Failure;
        if (r2 != 0) goto L_0x016c;
    L_0x00ca:
        r13 = 0;
        r13 = (java.lang.Throwable) r13;
        r2 = r10.iterator();	 Catch:{ Throwable -> 0x015d, all -> 0x0157 }
        r4 = r10;
        r7 = r4;
        r6 = r11;
        r5 = r12;
        r12 = r13;
        r11 = r7;
        r13 = r11;
        r10 = r2;
    L_0x00d9:
        r0.L$0 = r7;	 Catch:{ Throwable -> 0x0153, all -> 0x0151 }
        r0.L$1 = r6;	 Catch:{ Throwable -> 0x0153, all -> 0x0151 }
        r0.L$2 = r5;	 Catch:{ Throwable -> 0x0153, all -> 0x0151 }
        r0.L$3 = r4;	 Catch:{ Throwable -> 0x0153, all -> 0x0151 }
        r0.L$4 = r11;	 Catch:{ Throwable -> 0x0153, all -> 0x0151 }
        r0.L$5 = r12;	 Catch:{ Throwable -> 0x0153, all -> 0x0151 }
        r0.L$6 = r13;	 Catch:{ Throwable -> 0x0153, all -> 0x0151 }
        r0.L$7 = r10;	 Catch:{ Throwable -> 0x0153, all -> 0x0151 }
        r0.label = r3;	 Catch:{ Throwable -> 0x0153, all -> 0x0151 }
        r2 = r10.hasNext(r0);	 Catch:{ Throwable -> 0x0153, all -> 0x0151 }
        if (r2 != r1) goto L_0x00f2;
    L_0x00f1:
        return r1;
    L_0x00f2:
        r9 = r1;
        r1 = r13;
        r13 = r2;
        goto L_0x00b6;
    L_0x00f6:
        r13 = (java.lang.Boolean) r13;	 Catch:{ Throwable -> 0x0153, all -> 0x0151 }
        r13 = r13.booleanValue();	 Catch:{ Throwable -> 0x0153, all -> 0x0151 }
        if (r13 == 0) goto L_0x0145;
    L_0x00fe:
        r0.L$0 = r7;	 Catch:{ Throwable -> 0x0153, all -> 0x0151 }
        r0.L$1 = r6;	 Catch:{ Throwable -> 0x0153, all -> 0x0151 }
        r0.L$2 = r5;	 Catch:{ Throwable -> 0x0153, all -> 0x0151 }
        r0.L$3 = r4;	 Catch:{ Throwable -> 0x0153, all -> 0x0151 }
        r0.L$4 = r11;	 Catch:{ Throwable -> 0x0153, all -> 0x0151 }
        r0.L$5 = r12;	 Catch:{ Throwable -> 0x0153, all -> 0x0151 }
        r0.L$6 = r1;	 Catch:{ Throwable -> 0x0153, all -> 0x0151 }
        r0.L$7 = r10;	 Catch:{ Throwable -> 0x0153, all -> 0x0151 }
        r13 = 2;
        r0.label = r13;	 Catch:{ Throwable -> 0x0153, all -> 0x0151 }
        r13 = r10.next(r0);	 Catch:{ Throwable -> 0x0153, all -> 0x0151 }
        if (r13 != r2) goto L_0x0118;
    L_0x0117:
        return r2;
    L_0x0118:
        r8 = r5.invoke(r13);	 Catch:{ Throwable -> 0x0153, all -> 0x0151 }
        r8 = (java.lang.Boolean) r8;	 Catch:{ Throwable -> 0x0153, all -> 0x0151 }
        r8 = r8.booleanValue();	 Catch:{ Throwable -> 0x0153, all -> 0x0151 }
        if (r8 != 0) goto L_0x0142;
    L_0x0124:
        r0.L$0 = r7;	 Catch:{ Throwable -> 0x0153, all -> 0x0151 }
        r0.L$1 = r6;	 Catch:{ Throwable -> 0x0153, all -> 0x0151 }
        r0.L$2 = r5;	 Catch:{ Throwable -> 0x0153, all -> 0x0151 }
        r0.L$3 = r4;	 Catch:{ Throwable -> 0x0153, all -> 0x0151 }
        r0.L$4 = r11;	 Catch:{ Throwable -> 0x0153, all -> 0x0151 }
        r0.L$5 = r12;	 Catch:{ Throwable -> 0x0153, all -> 0x0151 }
        r0.L$6 = r1;	 Catch:{ Throwable -> 0x0153, all -> 0x0151 }
        r0.L$7 = r10;	 Catch:{ Throwable -> 0x0153, all -> 0x0151 }
        r0.L$8 = r13;	 Catch:{ Throwable -> 0x0153, all -> 0x0151 }
        r0.L$9 = r13;	 Catch:{ Throwable -> 0x0153, all -> 0x0151 }
        r8 = 3;
        r0.label = r8;	 Catch:{ Throwable -> 0x0153, all -> 0x0151 }
        r13 = r6.send(r13, r0);	 Catch:{ Throwable -> 0x0153, all -> 0x0151 }
        if (r13 != r2) goto L_0x0142;
    L_0x0141:
        return r2;
    L_0x0142:
        r13 = r1;
        r1 = r2;
        goto L_0x00d9;
    L_0x0145:
        r10 = kotlin.Unit.INSTANCE;	 Catch:{ Throwable -> 0x0153, all -> 0x0151 }
        kotlin.jvm.internal.InlineMarker.finallyStart(r3);
        r11.cancel(r12);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r3);
        return r6;
    L_0x0151:
        r10 = move-exception;
        goto L_0x0162;
    L_0x0153:
        r10 = move-exception;
        r12 = r10;
        r10 = r11;
        goto L_0x015f;
    L_0x0157:
        r11 = move-exception;
        r12 = r13;
    L_0x0159:
        r9 = r11;
        r11 = r10;
        r10 = r9;
        goto L_0x0162;
    L_0x015d:
        r11 = move-exception;
        r12 = r11;
    L_0x015f:
        throw r12;	 Catch:{ all -> 0x0160 }
    L_0x0160:
        r11 = move-exception;
        goto L_0x0159;
    L_0x0162:
        kotlin.jvm.internal.InlineMarker.finallyStart(r3);
        r11.cancel(r12);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r3);
        throw r10;
    L_0x016c:
        r13 = (kotlin.Result.Failure) r13;
        r10 = r13.exception;
        throw r10;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.filterNotTo(kotlinx.coroutines.channels.ReceiveChannel, kotlinx.coroutines.channels.SendChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @org.jetbrains.annotations.Nullable
    @kotlinx.coroutines.ObsoleteCoroutinesApi
    public static final <E, C extends java.util.Collection<? super E>> java.lang.Object filterTo(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r10, @org.jetbrains.annotations.NotNull C r11, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super E, java.lang.Boolean> r12, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super C> r13) {
        /*
        r0 = r13 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filterTo$1;
        if (r0 == 0) goto L_0x0014;
    L_0x0004:
        r0 = r13;
        r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filterTo$1) r0;
        r1 = r0.label;
        r2 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r1 = r1 & r2;
        if (r1 == 0) goto L_0x0014;
    L_0x000e:
        r13 = r0.label;
        r13 = r13 - r2;
        r0.label = r13;
        goto L_0x0019;
    L_0x0014:
        r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filterTo$1;
        r0.<init>(r13);
    L_0x0019:
        r13 = r0.result;
        r1 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        r2 = r0.label;
        r3 = 1;
        switch(r2) {
            case 0: goto L_0x008d;
            case 1: goto L_0x005b;
            case 2: goto L_0x002d;
            default: goto L_0x0025;
        };
    L_0x0025:
        r10 = new java.lang.IllegalStateException;
        r11 = "call to 'resume' before 'invoke' with coroutine";
        r10.<init>(r11);
        throw r10;
    L_0x002d:
        r10 = r0.L$7;
        r10 = (kotlinx.coroutines.channels.ChannelIterator) r10;
        r11 = r0.L$6;
        r11 = (kotlinx.coroutines.channels.ReceiveChannel) r11;
        r12 = r0.L$5;
        r12 = (java.lang.Throwable) r12;
        r2 = r0.L$4;
        r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2;
        r4 = r0.L$3;
        r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4;
        r5 = r0.L$2;
        r5 = (kotlin.jvm.functions.Function1) r5;
        r6 = r0.L$1;
        r6 = (java.util.Collection) r6;
        r7 = r0.L$0;
        r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7;
        r8 = r13 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        if (r8 != 0) goto L_0x0056;
    L_0x0051:
        r9 = r2;
        r2 = r10;
        r10 = r9;
        goto L_0x00dc;
    L_0x0056:
        r13 = (kotlin.Result.Failure) r13;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r10 = r13.exception;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        throw r10;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
    L_0x005b:
        r10 = r0.L$7;
        r10 = (kotlinx.coroutines.channels.ChannelIterator) r10;
        r11 = r0.L$6;
        r11 = (kotlinx.coroutines.channels.ReceiveChannel) r11;
        r12 = r0.L$5;
        r12 = (java.lang.Throwable) r12;
        r2 = r0.L$4;
        r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2;
        r4 = r0.L$3;
        r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4;
        r5 = r0.L$2;
        r5 = (kotlin.jvm.functions.Function1) r5;
        r6 = r0.L$1;
        r6 = (java.util.Collection) r6;
        r7 = r0.L$0;
        r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7;
        r8 = r13 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        if (r8 != 0) goto L_0x0080;
    L_0x007f:
        goto L_0x00ba;
    L_0x0080:
        r13 = (kotlin.Result.Failure) r13;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r10 = r13.exception;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        throw r10;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
    L_0x0085:
        r10 = move-exception;
        goto L_0x0103;
    L_0x0088:
        r10 = move-exception;
        r12 = r10;
        r10 = r2;
        goto L_0x00ff;
    L_0x008d:
        r2 = r13 instanceof kotlin.Result.Failure;
        if (r2 != 0) goto L_0x010d;
    L_0x0091:
        r13 = 0;
        r13 = (java.lang.Throwable) r13;
        r2 = r10.iterator();	 Catch:{ Throwable -> 0x00fd, all -> 0x00f8 }
        r4 = r10;
        r7 = r4;
        r6 = r11;
        r5 = r12;
        r12 = r13;
        r11 = r7;
    L_0x009e:
        r0.L$0 = r7;	 Catch:{ Throwable -> 0x00fd }
        r0.L$1 = r6;	 Catch:{ Throwable -> 0x00fd }
        r0.L$2 = r5;	 Catch:{ Throwable -> 0x00fd }
        r0.L$3 = r4;	 Catch:{ Throwable -> 0x00fd }
        r0.L$4 = r10;	 Catch:{ Throwable -> 0x00fd }
        r0.L$5 = r12;	 Catch:{ Throwable -> 0x00fd }
        r0.L$6 = r11;	 Catch:{ Throwable -> 0x00fd }
        r0.L$7 = r2;	 Catch:{ Throwable -> 0x00fd }
        r0.label = r3;	 Catch:{ Throwable -> 0x00fd }
        r13 = r2.hasNext(r0);	 Catch:{ Throwable -> 0x00fd }
        if (r13 != r1) goto L_0x00b7;
    L_0x00b6:
        return r1;
    L_0x00b7:
        r9 = r2;
        r2 = r10;
        r10 = r9;
    L_0x00ba:
        r13 = (java.lang.Boolean) r13;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r13 = r13.booleanValue();	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        if (r13 == 0) goto L_0x00ec;
    L_0x00c2:
        r0.L$0 = r7;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r0.L$1 = r6;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r0.L$2 = r5;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r0.L$3 = r4;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r0.L$4 = r2;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r0.L$5 = r12;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r0.L$6 = r11;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r0.L$7 = r10;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r13 = 2;
        r0.label = r13;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r13 = r10.next(r0);	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        if (r13 != r1) goto L_0x0051;
    L_0x00db:
        return r1;
    L_0x00dc:
        r8 = r5.invoke(r13);	 Catch:{ Throwable -> 0x00fd }
        r8 = (java.lang.Boolean) r8;	 Catch:{ Throwable -> 0x00fd }
        r8 = r8.booleanValue();	 Catch:{ Throwable -> 0x00fd }
        if (r8 == 0) goto L_0x009e;
    L_0x00e8:
        r6.add(r13);	 Catch:{ Throwable -> 0x00fd }
        goto L_0x009e;
    L_0x00ec:
        r10 = kotlin.Unit.INSTANCE;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        kotlin.jvm.internal.InlineMarker.finallyStart(r3);
        r2.cancel(r12);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r3);
        return r6;
    L_0x00f8:
        r11 = move-exception;
        r2 = r10;
        r10 = r11;
        r12 = r13;
        goto L_0x0103;
    L_0x00fd:
        r11 = move-exception;
        r12 = r11;
    L_0x00ff:
        throw r12;	 Catch:{ all -> 0x0100 }
    L_0x0100:
        r11 = move-exception;
        r2 = r10;
        r10 = r11;
    L_0x0103:
        kotlin.jvm.internal.InlineMarker.finallyStart(r3);
        r2.cancel(r12);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r3);
        throw r10;
    L_0x010d:
        r13 = (kotlin.Result.Failure) r13;
        r10 = r13.exception;
        throw r10;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.filterTo(kotlinx.coroutines.channels.ReceiveChannel, java.util.Collection, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @org.jetbrains.annotations.Nullable
    @kotlinx.coroutines.ObsoleteCoroutinesApi
    public static final <E, C extends kotlinx.coroutines.channels.SendChannel<? super E>> java.lang.Object filterTo(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r10, @org.jetbrains.annotations.NotNull C r11, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super E, java.lang.Boolean> r12, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super C> r13) {
        /*
        r0 = r13 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filterTo$3;
        if (r0 == 0) goto L_0x0014;
    L_0x0004:
        r0 = r13;
        r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filterTo$3) r0;
        r1 = r0.label;
        r2 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r1 = r1 & r2;
        if (r1 == 0) goto L_0x0014;
    L_0x000e:
        r13 = r0.label;
        r13 = r13 - r2;
        r0.label = r13;
        goto L_0x0019;
    L_0x0014:
        r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filterTo$3;
        r0.<init>(r13);
    L_0x0019:
        r13 = r0.result;
        r1 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        r2 = r0.label;
        r3 = 1;
        switch(r2) {
            case 0: goto L_0x00c6;
            case 1: goto L_0x008f;
            case 2: goto L_0x0060;
            case 3: goto L_0x002d;
            default: goto L_0x0025;
        };
    L_0x0025:
        r10 = new java.lang.IllegalStateException;
        r11 = "call to 'resume' before 'invoke' with coroutine";
        r10.<init>(r11);
        throw r10;
    L_0x002d:
        r10 = r0.L$9;
        r10 = r0.L$8;
        r10 = r0.L$7;
        r10 = (kotlinx.coroutines.channels.ChannelIterator) r10;
        r11 = r0.L$6;
        r11 = (kotlinx.coroutines.channels.ReceiveChannel) r11;
        r12 = r0.L$5;
        r12 = (java.lang.Throwable) r12;
        r2 = r0.L$4;
        r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2;
        r4 = r0.L$3;
        r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4;
        r5 = r0.L$2;
        r5 = (kotlin.jvm.functions.Function1) r5;
        r6 = r0.L$1;
        r6 = (kotlinx.coroutines.channels.SendChannel) r6;
        r7 = r0.L$0;
        r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7;
        r8 = r13 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x00c1, all -> 0x00bd }
        if (r8 != 0) goto L_0x005b;
    L_0x0055:
        r9 = r1;
        r1 = r11;
        r11 = r2;
        r2 = r9;
        goto L_0x0142;
    L_0x005b:
        r13 = (kotlin.Result.Failure) r13;	 Catch:{ Throwable -> 0x00c1, all -> 0x00bd }
        r10 = r13.exception;	 Catch:{ Throwable -> 0x00c1, all -> 0x00bd }
        throw r10;	 Catch:{ Throwable -> 0x00c1, all -> 0x00bd }
    L_0x0060:
        r10 = r0.L$7;
        r10 = (kotlinx.coroutines.channels.ChannelIterator) r10;
        r11 = r0.L$6;
        r11 = (kotlinx.coroutines.channels.ReceiveChannel) r11;
        r12 = r0.L$5;
        r12 = (java.lang.Throwable) r12;
        r2 = r0.L$4;
        r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2;
        r4 = r0.L$3;
        r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4;
        r5 = r0.L$2;
        r5 = (kotlin.jvm.functions.Function1) r5;
        r6 = r0.L$1;
        r6 = (kotlinx.coroutines.channels.SendChannel) r6;
        r7 = r0.L$0;
        r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7;
        r8 = r13 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x00c1, all -> 0x00bd }
        if (r8 != 0) goto L_0x008a;
    L_0x0084:
        r9 = r1;
        r1 = r11;
        r11 = r2;
        r2 = r9;
        goto L_0x0118;
    L_0x008a:
        r13 = (kotlin.Result.Failure) r13;	 Catch:{ Throwable -> 0x00c1, all -> 0x00bd }
        r10 = r13.exception;	 Catch:{ Throwable -> 0x00c1, all -> 0x00bd }
        throw r10;	 Catch:{ Throwable -> 0x00c1, all -> 0x00bd }
    L_0x008f:
        r10 = r0.L$7;
        r10 = (kotlinx.coroutines.channels.ChannelIterator) r10;
        r11 = r0.L$6;
        r11 = (kotlinx.coroutines.channels.ReceiveChannel) r11;
        r12 = r0.L$5;
        r12 = (java.lang.Throwable) r12;
        r2 = r0.L$4;
        r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2;
        r4 = r0.L$3;
        r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4;
        r5 = r0.L$2;
        r5 = (kotlin.jvm.functions.Function1) r5;
        r6 = r0.L$1;
        r6 = (kotlinx.coroutines.channels.SendChannel) r6;
        r7 = r0.L$0;
        r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7;
        r8 = r13 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x00c1, all -> 0x00bd }
        if (r8 != 0) goto L_0x00b8;
    L_0x00b3:
        r9 = r1;
        r1 = r11;
        r11 = r2;
    L_0x00b6:
        r2 = r9;
        goto L_0x00f6;
    L_0x00b8:
        r13 = (kotlin.Result.Failure) r13;	 Catch:{ Throwable -> 0x00c1, all -> 0x00bd }
        r10 = r13.exception;	 Catch:{ Throwable -> 0x00c1, all -> 0x00bd }
        throw r10;	 Catch:{ Throwable -> 0x00c1, all -> 0x00bd }
    L_0x00bd:
        r10 = move-exception;
        r11 = r2;
        goto L_0x0162;
    L_0x00c1:
        r10 = move-exception;
        r12 = r10;
        r10 = r2;
        goto L_0x015f;
    L_0x00c6:
        r2 = r13 instanceof kotlin.Result.Failure;
        if (r2 != 0) goto L_0x016c;
    L_0x00ca:
        r13 = 0;
        r13 = (java.lang.Throwable) r13;
        r2 = r10.iterator();	 Catch:{ Throwable -> 0x015d, all -> 0x0157 }
        r4 = r10;
        r7 = r4;
        r6 = r11;
        r5 = r12;
        r12 = r13;
        r11 = r7;
        r13 = r11;
        r10 = r2;
    L_0x00d9:
        r0.L$0 = r7;	 Catch:{ Throwable -> 0x0153, all -> 0x0151 }
        r0.L$1 = r6;	 Catch:{ Throwable -> 0x0153, all -> 0x0151 }
        r0.L$2 = r5;	 Catch:{ Throwable -> 0x0153, all -> 0x0151 }
        r0.L$3 = r4;	 Catch:{ Throwable -> 0x0153, all -> 0x0151 }
        r0.L$4 = r11;	 Catch:{ Throwable -> 0x0153, all -> 0x0151 }
        r0.L$5 = r12;	 Catch:{ Throwable -> 0x0153, all -> 0x0151 }
        r0.L$6 = r13;	 Catch:{ Throwable -> 0x0153, all -> 0x0151 }
        r0.L$7 = r10;	 Catch:{ Throwable -> 0x0153, all -> 0x0151 }
        r0.label = r3;	 Catch:{ Throwable -> 0x0153, all -> 0x0151 }
        r2 = r10.hasNext(r0);	 Catch:{ Throwable -> 0x0153, all -> 0x0151 }
        if (r2 != r1) goto L_0x00f2;
    L_0x00f1:
        return r1;
    L_0x00f2:
        r9 = r1;
        r1 = r13;
        r13 = r2;
        goto L_0x00b6;
    L_0x00f6:
        r13 = (java.lang.Boolean) r13;	 Catch:{ Throwable -> 0x0153, all -> 0x0151 }
        r13 = r13.booleanValue();	 Catch:{ Throwable -> 0x0153, all -> 0x0151 }
        if (r13 == 0) goto L_0x0145;
    L_0x00fe:
        r0.L$0 = r7;	 Catch:{ Throwable -> 0x0153, all -> 0x0151 }
        r0.L$1 = r6;	 Catch:{ Throwable -> 0x0153, all -> 0x0151 }
        r0.L$2 = r5;	 Catch:{ Throwable -> 0x0153, all -> 0x0151 }
        r0.L$3 = r4;	 Catch:{ Throwable -> 0x0153, all -> 0x0151 }
        r0.L$4 = r11;	 Catch:{ Throwable -> 0x0153, all -> 0x0151 }
        r0.L$5 = r12;	 Catch:{ Throwable -> 0x0153, all -> 0x0151 }
        r0.L$6 = r1;	 Catch:{ Throwable -> 0x0153, all -> 0x0151 }
        r0.L$7 = r10;	 Catch:{ Throwable -> 0x0153, all -> 0x0151 }
        r13 = 2;
        r0.label = r13;	 Catch:{ Throwable -> 0x0153, all -> 0x0151 }
        r13 = r10.next(r0);	 Catch:{ Throwable -> 0x0153, all -> 0x0151 }
        if (r13 != r2) goto L_0x0118;
    L_0x0117:
        return r2;
    L_0x0118:
        r8 = r5.invoke(r13);	 Catch:{ Throwable -> 0x0153, all -> 0x0151 }
        r8 = (java.lang.Boolean) r8;	 Catch:{ Throwable -> 0x0153, all -> 0x0151 }
        r8 = r8.booleanValue();	 Catch:{ Throwable -> 0x0153, all -> 0x0151 }
        if (r8 == 0) goto L_0x0142;
    L_0x0124:
        r0.L$0 = r7;	 Catch:{ Throwable -> 0x0153, all -> 0x0151 }
        r0.L$1 = r6;	 Catch:{ Throwable -> 0x0153, all -> 0x0151 }
        r0.L$2 = r5;	 Catch:{ Throwable -> 0x0153, all -> 0x0151 }
        r0.L$3 = r4;	 Catch:{ Throwable -> 0x0153, all -> 0x0151 }
        r0.L$4 = r11;	 Catch:{ Throwable -> 0x0153, all -> 0x0151 }
        r0.L$5 = r12;	 Catch:{ Throwable -> 0x0153, all -> 0x0151 }
        r0.L$6 = r1;	 Catch:{ Throwable -> 0x0153, all -> 0x0151 }
        r0.L$7 = r10;	 Catch:{ Throwable -> 0x0153, all -> 0x0151 }
        r0.L$8 = r13;	 Catch:{ Throwable -> 0x0153, all -> 0x0151 }
        r0.L$9 = r13;	 Catch:{ Throwable -> 0x0153, all -> 0x0151 }
        r8 = 3;
        r0.label = r8;	 Catch:{ Throwable -> 0x0153, all -> 0x0151 }
        r13 = r6.send(r13, r0);	 Catch:{ Throwable -> 0x0153, all -> 0x0151 }
        if (r13 != r2) goto L_0x0142;
    L_0x0141:
        return r2;
    L_0x0142:
        r13 = r1;
        r1 = r2;
        goto L_0x00d9;
    L_0x0145:
        r10 = kotlin.Unit.INSTANCE;	 Catch:{ Throwable -> 0x0153, all -> 0x0151 }
        kotlin.jvm.internal.InlineMarker.finallyStart(r3);
        r11.cancel(r12);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r3);
        return r6;
    L_0x0151:
        r10 = move-exception;
        goto L_0x0162;
    L_0x0153:
        r10 = move-exception;
        r12 = r10;
        r10 = r11;
        goto L_0x015f;
    L_0x0157:
        r11 = move-exception;
        r12 = r13;
    L_0x0159:
        r9 = r11;
        r11 = r10;
        r10 = r9;
        goto L_0x0162;
    L_0x015d:
        r11 = move-exception;
        r12 = r11;
    L_0x015f:
        throw r12;	 Catch:{ all -> 0x0160 }
    L_0x0160:
        r11 = move-exception;
        goto L_0x0159;
    L_0x0162:
        kotlin.jvm.internal.InlineMarker.finallyStart(r3);
        r11.cancel(r12);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r3);
        throw r10;
    L_0x016c:
        r13 = (kotlin.Result.Failure) r13;
        r10 = r13.exception;
        throw r10;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.filterTo(kotlinx.coroutines.channels.ReceiveChannel, kotlinx.coroutines.channels.SendChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @NotNull
    @ObsoleteCoroutinesApi
    public static /* synthetic */ ReceiveChannel take$default(ReceiveChannel receiveChannel, int i, CoroutineContext coroutineContext, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return ChannelsKt.take(receiveChannel, i, coroutineContext);
    }

    @NotNull
    @ObsoleteCoroutinesApi
    public static final <E> ReceiveChannel<E> take(@NotNull ReceiveChannel<? extends E> receiveChannel, int i, @NotNull CoroutineContext coroutineContext) {
        Intrinsics.checkParameterIsNotNull(receiveChannel, "receiver$0");
        Intrinsics.checkParameterIsNotNull(coroutineContext, "context");
        return ProduceKt.produce$default(GlobalScope.INSTANCE, coroutineContext, 0, ChannelsKt.consumes(receiveChannel), new ChannelsKt__Channels_commonKt$take$1(receiveChannel, i, null), 2, null);
    }

    @NotNull
    @ObsoleteCoroutinesApi
    public static /* synthetic */ ReceiveChannel takeWhile$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return ChannelsKt.takeWhile(receiveChannel, coroutineContext, function2);
    }

    @NotNull
    @ObsoleteCoroutinesApi
    public static final <E> ReceiveChannel<E> takeWhile(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull CoroutineContext coroutineContext, @NotNull Function2<? super E, ? super Continuation<? super Boolean>, ? extends Object> function2) {
        Intrinsics.checkParameterIsNotNull(receiveChannel, "receiver$0");
        Intrinsics.checkParameterIsNotNull(coroutineContext, "context");
        Intrinsics.checkParameterIsNotNull(function2, "predicate");
        return ProduceKt.produce$default(GlobalScope.INSTANCE, coroutineContext, 0, ChannelsKt.consumes(receiveChannel), new ChannelsKt__Channels_commonKt$takeWhile$1(receiveChannel, function2, null), 2, null);
    }

    @org.jetbrains.annotations.Nullable
    @kotlinx.coroutines.ObsoleteCoroutinesApi
    public static final <E, K, V> java.lang.Object associate(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r13, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super E, ? extends kotlin.Pair<? extends K, ? extends V>> r14, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.util.Map<K, ? extends V>> r15) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:37)
	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:61)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1603198149.run(Unknown Source)
*/
        /*
        r0 = r15 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$associate$1;
        if (r0 == 0) goto L_0x0014;
    L_0x0004:
        r0 = r15;
        r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$associate$1) r0;
        r1 = r0.label;
        r2 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r1 = r1 & r2;
        if (r1 == 0) goto L_0x0014;
    L_0x000e:
        r15 = r0.label;
        r15 = r15 - r2;
        r0.label = r15;
        goto L_0x0019;
    L_0x0014:
        r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$associate$1;
        r0.<init>(r15);
    L_0x0019:
        r15 = r0.result;
        r1 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        r2 = r0.label;
        r3 = 1;
        switch(r2) {
            case 0: goto L_0x0095;
            case 1: goto L_0x005f;
            case 2: goto L_0x002d;
            default: goto L_0x0025;
        };
    L_0x0025:
        r13 = new java.lang.IllegalStateException;
        r14 = "call to 'resume' before 'invoke' with coroutine";
        r13.<init>(r14);
        throw r13;
    L_0x002d:
        r13 = r0.L$8;
        r13 = (kotlinx.coroutines.channels.ChannelIterator) r13;
        r14 = r0.L$7;
        r14 = (kotlinx.coroutines.channels.ReceiveChannel) r14;
        r2 = r0.L$6;
        r2 = (java.lang.Throwable) r2;
        r4 = r0.L$5;
        r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4;
        r5 = r0.L$4;
        r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5;
        r6 = r0.L$3;
        r6 = (java.util.Map) r6;
        r7 = r0.L$2;
        r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7;
        r8 = r0.L$1;
        r8 = (kotlin.jvm.functions.Function1) r8;
        r9 = r0.L$0;
        r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9;
        r10 = r15 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x0090, all -> 0x008d }
        if (r10 != 0) goto L_0x005a;	 Catch:{ Throwable -> 0x0090, all -> 0x008d }
    L_0x0055:
        r11 = r4;	 Catch:{ Throwable -> 0x0090, all -> 0x008d }
        r4 = r13;	 Catch:{ Throwable -> 0x0090, all -> 0x008d }
        r13 = r11;	 Catch:{ Throwable -> 0x0090, all -> 0x008d }
        goto L_0x00f1;	 Catch:{ Throwable -> 0x0090, all -> 0x008d }
    L_0x005a:
        r15 = (kotlin.Result.Failure) r15;	 Catch:{ Throwable -> 0x0090, all -> 0x008d }
        r13 = r15.exception;	 Catch:{ Throwable -> 0x0090, all -> 0x008d }
        throw r13;	 Catch:{ Throwable -> 0x0090, all -> 0x008d }
    L_0x005f:
        r13 = r0.L$8;
        r13 = (kotlinx.coroutines.channels.ChannelIterator) r13;
        r14 = r0.L$7;
        r14 = (kotlinx.coroutines.channels.ReceiveChannel) r14;
        r2 = r0.L$6;
        r2 = (java.lang.Throwable) r2;
        r4 = r0.L$5;
        r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4;
        r5 = r0.L$4;
        r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5;
        r6 = r0.L$3;
        r6 = (java.util.Map) r6;
        r7 = r0.L$2;
        r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7;
        r8 = r0.L$1;
        r8 = (kotlin.jvm.functions.Function1) r8;
        r9 = r0.L$0;
        r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9;
        r10 = r15 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x0090, all -> 0x008d }
        if (r10 != 0) goto L_0x0088;	 Catch:{ Throwable -> 0x0090, all -> 0x008d }
    L_0x0087:
        goto L_0x00cd;	 Catch:{ Throwable -> 0x0090, all -> 0x008d }
    L_0x0088:
        r15 = (kotlin.Result.Failure) r15;	 Catch:{ Throwable -> 0x0090, all -> 0x008d }
        r13 = r15.exception;	 Catch:{ Throwable -> 0x0090, all -> 0x008d }
        throw r13;	 Catch:{ Throwable -> 0x0090, all -> 0x008d }
    L_0x008d:
        r13 = move-exception;
        goto L_0x0117;
    L_0x0090:
        r13 = move-exception;
        r2 = r13;
        r13 = r4;
        goto L_0x0116;
    L_0x0095:
        r2 = r15 instanceof kotlin.Result.Failure;
        if (r2 != 0) goto L_0x0121;
    L_0x0099:
        r15 = new java.util.LinkedHashMap;
        r15.<init>();
        r15 = (java.util.Map) r15;
        r2 = 0;
        r2 = (java.lang.Throwable) r2;
        r4 = r13.iterator();	 Catch:{ Throwable -> 0x0114 }
        r5 = r13;	 Catch:{ Throwable -> 0x0114 }
        r7 = r5;	 Catch:{ Throwable -> 0x0114 }
        r9 = r7;	 Catch:{ Throwable -> 0x0114 }
        r8 = r14;	 Catch:{ Throwable -> 0x0114 }
        r14 = r9;	 Catch:{ Throwable -> 0x0114 }
    L_0x00ac:
        r0.L$0 = r9;	 Catch:{ Throwable -> 0x0114 }
        r0.L$1 = r8;	 Catch:{ Throwable -> 0x0114 }
        r0.L$2 = r7;	 Catch:{ Throwable -> 0x0114 }
        r0.L$3 = r15;	 Catch:{ Throwable -> 0x0114 }
        r0.L$4 = r5;	 Catch:{ Throwable -> 0x0114 }
        r0.L$5 = r13;	 Catch:{ Throwable -> 0x0114 }
        r0.L$6 = r2;	 Catch:{ Throwable -> 0x0114 }
        r0.L$7 = r14;	 Catch:{ Throwable -> 0x0114 }
        r0.L$8 = r4;	 Catch:{ Throwable -> 0x0114 }
        r0.label = r3;	 Catch:{ Throwable -> 0x0114 }
        r6 = r4.hasNext(r0);	 Catch:{ Throwable -> 0x0114 }
        if (r6 != r1) goto L_0x00c7;
    L_0x00c6:
        return r1;
    L_0x00c7:
        r11 = r4;
        r4 = r13;
        r13 = r11;
        r12 = r6;
        r6 = r15;
        r15 = r12;
    L_0x00cd:
        r15 = (java.lang.Boolean) r15;	 Catch:{ Throwable -> 0x0090, all -> 0x008d }
        r15 = r15.booleanValue();	 Catch:{ Throwable -> 0x0090, all -> 0x008d }
        if (r15 == 0) goto L_0x0104;	 Catch:{ Throwable -> 0x0090, all -> 0x008d }
    L_0x00d5:
        r0.L$0 = r9;	 Catch:{ Throwable -> 0x0090, all -> 0x008d }
        r0.L$1 = r8;	 Catch:{ Throwable -> 0x0090, all -> 0x008d }
        r0.L$2 = r7;	 Catch:{ Throwable -> 0x0090, all -> 0x008d }
        r0.L$3 = r6;	 Catch:{ Throwable -> 0x0090, all -> 0x008d }
        r0.L$4 = r5;	 Catch:{ Throwable -> 0x0090, all -> 0x008d }
        r0.L$5 = r4;	 Catch:{ Throwable -> 0x0090, all -> 0x008d }
        r0.L$6 = r2;	 Catch:{ Throwable -> 0x0090, all -> 0x008d }
        r0.L$7 = r14;	 Catch:{ Throwable -> 0x0090, all -> 0x008d }
        r0.L$8 = r13;	 Catch:{ Throwable -> 0x0090, all -> 0x008d }
        r15 = 2;	 Catch:{ Throwable -> 0x0090, all -> 0x008d }
        r0.label = r15;	 Catch:{ Throwable -> 0x0090, all -> 0x008d }
        r15 = r13.next(r0);	 Catch:{ Throwable -> 0x0090, all -> 0x008d }
        if (r15 != r1) goto L_0x0055;
    L_0x00f0:
        return r1;
    L_0x00f1:
        r15 = r8.invoke(r15);	 Catch:{ Throwable -> 0x0114 }
        r15 = (kotlin.Pair) r15;	 Catch:{ Throwable -> 0x0114 }
        r10 = r15.getFirst();	 Catch:{ Throwable -> 0x0114 }
        r15 = r15.getSecond();	 Catch:{ Throwable -> 0x0114 }
        r6.put(r10, r15);	 Catch:{ Throwable -> 0x0114 }
        r15 = r6;
        goto L_0x00ac;
    L_0x0104:
        r13 = kotlin.Unit.INSTANCE;	 Catch:{ Throwable -> 0x0090, all -> 0x008d }
        kotlin.jvm.internal.InlineMarker.finallyStart(r3);
        r4.cancel(r2);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r3);
        return r6;
    L_0x0110:
        r14 = move-exception;
        r4 = r13;
        r13 = r14;
        goto L_0x0117;
    L_0x0114:
        r14 = move-exception;
        r2 = r14;
    L_0x0116:
        throw r2;	 Catch:{ all -> 0x0110 }
    L_0x0117:
        kotlin.jvm.internal.InlineMarker.finallyStart(r3);
        r4.cancel(r2);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r3);
        throw r13;
    L_0x0121:
        r15 = (kotlin.Result.Failure) r15;
        r13 = r15.exception;
        throw r13;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.associate(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Nullable
    @ObsoleteCoroutinesApi
    private static final Object associate$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Function1 function1, @NotNull Continuation continuation) {
        Function1 function12;
        Map linkedHashMap = new LinkedHashMap();
        Throwable th = (Throwable) null;
        try {
            ChannelIterator it = receiveChannel.iterator();
            while (true) {
                InlineMarker.mark(0);
                Object hasNext = it.hasNext(continuation);
                InlineMarker.mark(1);
                if (((Boolean) hasNext).booleanValue()) {
                    InlineMarker.mark(0);
                    Object next = it.next(continuation);
                    InlineMarker.mark(1);
                    Pair pair = (Pair) function1.invoke(next);
                    linkedHashMap.put(pair.getFirst(), pair.getSecond());
                } else {
                    function1 = Unit.INSTANCE;
                    InlineMarker.finallyStart(1);
                    receiveChannel.cancel(th);
                    InlineMarker.finallyEnd(1);
                    return linkedHashMap;
                }
            }
        } catch (Throwable th2) {
            InlineMarker.finallyStart(1);
            receiveChannel.cancel(function12);
            InlineMarker.finallyEnd(1);
        }
    }

    @org.jetbrains.annotations.Nullable
    @kotlinx.coroutines.ObsoleteCoroutinesApi
    public static final <E, K> java.lang.Object associateBy(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r13, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super E, ? extends K> r14, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.util.Map<K, ? extends E>> r15) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:37)
	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:61)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1603198149.run(Unknown Source)
*/
        /*
        r0 = r15 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$associateBy$1;
        if (r0 == 0) goto L_0x0014;
    L_0x0004:
        r0 = r15;
        r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$associateBy$1) r0;
        r1 = r0.label;
        r2 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r1 = r1 & r2;
        if (r1 == 0) goto L_0x0014;
    L_0x000e:
        r15 = r0.label;
        r15 = r15 - r2;
        r0.label = r15;
        goto L_0x0019;
    L_0x0014:
        r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$associateBy$1;
        r0.<init>(r15);
    L_0x0019:
        r15 = r0.result;
        r1 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        r2 = r0.label;
        r3 = 1;
        switch(r2) {
            case 0: goto L_0x0095;
            case 1: goto L_0x005f;
            case 2: goto L_0x002d;
            default: goto L_0x0025;
        };
    L_0x0025:
        r13 = new java.lang.IllegalStateException;
        r14 = "call to 'resume' before 'invoke' with coroutine";
        r13.<init>(r14);
        throw r13;
    L_0x002d:
        r13 = r0.L$8;
        r13 = (kotlinx.coroutines.channels.ChannelIterator) r13;
        r14 = r0.L$7;
        r14 = (kotlinx.coroutines.channels.ReceiveChannel) r14;
        r2 = r0.L$6;
        r2 = (java.lang.Throwable) r2;
        r4 = r0.L$5;
        r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4;
        r5 = r0.L$4;
        r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5;
        r6 = r0.L$3;
        r6 = (java.util.Map) r6;
        r7 = r0.L$2;
        r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7;
        r8 = r0.L$1;
        r8 = (kotlin.jvm.functions.Function1) r8;
        r9 = r0.L$0;
        r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9;
        r10 = r15 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x0090, all -> 0x008d }
        if (r10 != 0) goto L_0x005a;	 Catch:{ Throwable -> 0x0090, all -> 0x008d }
    L_0x0055:
        r11 = r4;	 Catch:{ Throwable -> 0x0090, all -> 0x008d }
        r4 = r13;	 Catch:{ Throwable -> 0x0090, all -> 0x008d }
        r13 = r11;	 Catch:{ Throwable -> 0x0090, all -> 0x008d }
        goto L_0x00f1;	 Catch:{ Throwable -> 0x0090, all -> 0x008d }
    L_0x005a:
        r15 = (kotlin.Result.Failure) r15;	 Catch:{ Throwable -> 0x0090, all -> 0x008d }
        r13 = r15.exception;	 Catch:{ Throwable -> 0x0090, all -> 0x008d }
        throw r13;	 Catch:{ Throwable -> 0x0090, all -> 0x008d }
    L_0x005f:
        r13 = r0.L$8;
        r13 = (kotlinx.coroutines.channels.ChannelIterator) r13;
        r14 = r0.L$7;
        r14 = (kotlinx.coroutines.channels.ReceiveChannel) r14;
        r2 = r0.L$6;
        r2 = (java.lang.Throwable) r2;
        r4 = r0.L$5;
        r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4;
        r5 = r0.L$4;
        r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5;
        r6 = r0.L$3;
        r6 = (java.util.Map) r6;
        r7 = r0.L$2;
        r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7;
        r8 = r0.L$1;
        r8 = (kotlin.jvm.functions.Function1) r8;
        r9 = r0.L$0;
        r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9;
        r10 = r15 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x0090, all -> 0x008d }
        if (r10 != 0) goto L_0x0088;	 Catch:{ Throwable -> 0x0090, all -> 0x008d }
    L_0x0087:
        goto L_0x00cd;	 Catch:{ Throwable -> 0x0090, all -> 0x008d }
    L_0x0088:
        r15 = (kotlin.Result.Failure) r15;	 Catch:{ Throwable -> 0x0090, all -> 0x008d }
        r13 = r15.exception;	 Catch:{ Throwable -> 0x0090, all -> 0x008d }
        throw r13;	 Catch:{ Throwable -> 0x0090, all -> 0x008d }
    L_0x008d:
        r13 = move-exception;
        goto L_0x010d;
    L_0x0090:
        r13 = move-exception;
        r2 = r13;
        r13 = r4;
        goto L_0x010c;
    L_0x0095:
        r2 = r15 instanceof kotlin.Result.Failure;
        if (r2 != 0) goto L_0x0117;
    L_0x0099:
        r15 = new java.util.LinkedHashMap;
        r15.<init>();
        r15 = (java.util.Map) r15;
        r2 = 0;
        r2 = (java.lang.Throwable) r2;
        r4 = r13.iterator();	 Catch:{ Throwable -> 0x010a }
        r5 = r13;	 Catch:{ Throwable -> 0x010a }
        r7 = r5;	 Catch:{ Throwable -> 0x010a }
        r9 = r7;	 Catch:{ Throwable -> 0x010a }
        r8 = r14;	 Catch:{ Throwable -> 0x010a }
        r14 = r9;	 Catch:{ Throwable -> 0x010a }
    L_0x00ac:
        r0.L$0 = r9;	 Catch:{ Throwable -> 0x010a }
        r0.L$1 = r8;	 Catch:{ Throwable -> 0x010a }
        r0.L$2 = r7;	 Catch:{ Throwable -> 0x010a }
        r0.L$3 = r15;	 Catch:{ Throwable -> 0x010a }
        r0.L$4 = r5;	 Catch:{ Throwable -> 0x010a }
        r0.L$5 = r13;	 Catch:{ Throwable -> 0x010a }
        r0.L$6 = r2;	 Catch:{ Throwable -> 0x010a }
        r0.L$7 = r14;	 Catch:{ Throwable -> 0x010a }
        r0.L$8 = r4;	 Catch:{ Throwable -> 0x010a }
        r0.label = r3;	 Catch:{ Throwable -> 0x010a }
        r6 = r4.hasNext(r0);	 Catch:{ Throwable -> 0x010a }
        if (r6 != r1) goto L_0x00c7;
    L_0x00c6:
        return r1;
    L_0x00c7:
        r11 = r4;
        r4 = r13;
        r13 = r11;
        r12 = r6;
        r6 = r15;
        r15 = r12;
    L_0x00cd:
        r15 = (java.lang.Boolean) r15;	 Catch:{ Throwable -> 0x0090, all -> 0x008d }
        r15 = r15.booleanValue();	 Catch:{ Throwable -> 0x0090, all -> 0x008d }
        if (r15 == 0) goto L_0x00fa;	 Catch:{ Throwable -> 0x0090, all -> 0x008d }
    L_0x00d5:
        r0.L$0 = r9;	 Catch:{ Throwable -> 0x0090, all -> 0x008d }
        r0.L$1 = r8;	 Catch:{ Throwable -> 0x0090, all -> 0x008d }
        r0.L$2 = r7;	 Catch:{ Throwable -> 0x0090, all -> 0x008d }
        r0.L$3 = r6;	 Catch:{ Throwable -> 0x0090, all -> 0x008d }
        r0.L$4 = r5;	 Catch:{ Throwable -> 0x0090, all -> 0x008d }
        r0.L$5 = r4;	 Catch:{ Throwable -> 0x0090, all -> 0x008d }
        r0.L$6 = r2;	 Catch:{ Throwable -> 0x0090, all -> 0x008d }
        r0.L$7 = r14;	 Catch:{ Throwable -> 0x0090, all -> 0x008d }
        r0.L$8 = r13;	 Catch:{ Throwable -> 0x0090, all -> 0x008d }
        r15 = 2;	 Catch:{ Throwable -> 0x0090, all -> 0x008d }
        r0.label = r15;	 Catch:{ Throwable -> 0x0090, all -> 0x008d }
        r15 = r13.next(r0);	 Catch:{ Throwable -> 0x0090, all -> 0x008d }
        if (r15 != r1) goto L_0x0055;
    L_0x00f0:
        return r1;
    L_0x00f1:
        r10 = r8.invoke(r15);	 Catch:{ Throwable -> 0x010a }
        r6.put(r10, r15);	 Catch:{ Throwable -> 0x010a }
        r15 = r6;
        goto L_0x00ac;
    L_0x00fa:
        r13 = kotlin.Unit.INSTANCE;	 Catch:{ Throwable -> 0x0090, all -> 0x008d }
        kotlin.jvm.internal.InlineMarker.finallyStart(r3);
        r4.cancel(r2);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r3);
        return r6;
    L_0x0106:
        r14 = move-exception;
        r4 = r13;
        r13 = r14;
        goto L_0x010d;
    L_0x010a:
        r14 = move-exception;
        r2 = r14;
    L_0x010c:
        throw r2;	 Catch:{ all -> 0x0106 }
    L_0x010d:
        kotlin.jvm.internal.InlineMarker.finallyStart(r3);
        r4.cancel(r2);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r3);
        throw r13;
    L_0x0117:
        r15 = (kotlin.Result.Failure) r15;
        r13 = r15.exception;
        throw r13;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.associateBy(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Nullable
    @ObsoleteCoroutinesApi
    private static final Object associateBy$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Function1 function1, @NotNull Continuation continuation) {
        Function1 function12;
        Map linkedHashMap = new LinkedHashMap();
        Throwable th = (Throwable) null;
        try {
            ChannelIterator it = receiveChannel.iterator();
            while (true) {
                InlineMarker.mark(0);
                Object hasNext = it.hasNext(continuation);
                InlineMarker.mark(1);
                if (((Boolean) hasNext).booleanValue()) {
                    InlineMarker.mark(0);
                    Object next = it.next(continuation);
                    InlineMarker.mark(1);
                    linkedHashMap.put(function1.invoke(next), next);
                } else {
                    function1 = Unit.INSTANCE;
                    InlineMarker.finallyStart(1);
                    receiveChannel.cancel(th);
                    InlineMarker.finallyEnd(1);
                    return linkedHashMap;
                }
            }
        } catch (Throwable th2) {
            InlineMarker.finallyStart(1);
            receiveChannel.cancel(function12);
            InlineMarker.finallyEnd(1);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @org.jetbrains.annotations.Nullable
    @kotlinx.coroutines.ObsoleteCoroutinesApi
    public static final <E, K, V> java.lang.Object associateBy(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r16, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super E, ? extends K> r17, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super E, ? extends V> r18, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.util.Map<K, ? extends V>> r19) {
        /*
        r0 = r19;
        r1 = r0 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$associateBy$2;
        if (r1 == 0) goto L_0x0016;
    L_0x0006:
        r1 = r0;
        r1 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$associateBy$2) r1;
        r2 = r1.label;
        r3 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r2 = r2 & r3;
        if (r2 == 0) goto L_0x0016;
    L_0x0010:
        r0 = r1.label;
        r0 = r0 - r3;
        r1.label = r0;
        goto L_0x001b;
    L_0x0016:
        r1 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$associateBy$2;
        r1.<init>(r0);
    L_0x001b:
        r0 = r1.result;
        r2 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        r3 = r1.label;
        r4 = 1;
        switch(r3) {
            case 0: goto L_0x00a3;
            case 1: goto L_0x0065;
            case 2: goto L_0x002f;
            default: goto L_0x0027;
        };
    L_0x0027:
        r0 = new java.lang.IllegalStateException;
        r1 = "call to 'resume' before 'invoke' with coroutine";
        r0.<init>(r1);
        throw r0;
    L_0x002f:
        r3 = r1.L$9;
        r3 = (kotlinx.coroutines.channels.ChannelIterator) r3;
        r5 = r1.L$8;
        r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5;
        r6 = r1.L$7;
        r6 = (java.lang.Throwable) r6;
        r7 = r1.L$6;
        r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7;
        r8 = r1.L$5;
        r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8;
        r9 = r1.L$4;
        r9 = (java.util.Map) r9;
        r10 = r1.L$3;
        r10 = (kotlinx.coroutines.channels.ReceiveChannel) r10;
        r11 = r1.L$2;
        r11 = (kotlin.jvm.functions.Function1) r11;
        r12 = r1.L$1;
        r12 = (kotlin.jvm.functions.Function1) r12;
        r13 = r1.L$0;
        r13 = (kotlinx.coroutines.channels.ReceiveChannel) r13;
        r14 = r0 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x009e, all -> 0x009a }
        if (r14 != 0) goto L_0x0060;
    L_0x005b:
        r15 = r7;
        r7 = r2;
        r2 = r15;
        goto L_0x0107;
    L_0x0060:
        r0 = (kotlin.Result.Failure) r0;	 Catch:{ Throwable -> 0x009e, all -> 0x009a }
        r0 = r0.exception;	 Catch:{ Throwable -> 0x009e, all -> 0x009a }
        throw r0;	 Catch:{ Throwable -> 0x009e, all -> 0x009a }
    L_0x0065:
        r3 = r1.L$9;
        r3 = (kotlinx.coroutines.channels.ChannelIterator) r3;
        r5 = r1.L$8;
        r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5;
        r6 = r1.L$7;
        r6 = (java.lang.Throwable) r6;
        r7 = r1.L$6;
        r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7;
        r8 = r1.L$5;
        r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8;
        r9 = r1.L$4;
        r9 = (java.util.Map) r9;
        r10 = r1.L$3;
        r10 = (kotlinx.coroutines.channels.ReceiveChannel) r10;
        r11 = r1.L$2;
        r11 = (kotlin.jvm.functions.Function1) r11;
        r12 = r1.L$1;
        r12 = (kotlin.jvm.functions.Function1) r12;
        r13 = r1.L$0;
        r13 = (kotlinx.coroutines.channels.ReceiveChannel) r13;
        r14 = r0 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x009e, all -> 0x009a }
        if (r14 != 0) goto L_0x0095;
    L_0x0091:
        r15 = r7;
        r7 = r2;
        r2 = r15;
        goto L_0x00e1;
    L_0x0095:
        r0 = (kotlin.Result.Failure) r0;	 Catch:{ Throwable -> 0x009e, all -> 0x009a }
        r0 = r0.exception;	 Catch:{ Throwable -> 0x009e, all -> 0x009a }
        throw r0;	 Catch:{ Throwable -> 0x009e, all -> 0x009a }
    L_0x009a:
        r0 = move-exception;
        r2 = r7;
        goto L_0x012c;
    L_0x009e:
        r0 = move-exception;
        r6 = r0;
        r2 = r7;
        goto L_0x012a;
    L_0x00a3:
        r3 = r0 instanceof kotlin.Result.Failure;
        if (r3 != 0) goto L_0x0136;
    L_0x00a7:
        r0 = new java.util.LinkedHashMap;
        r0.<init>();
        r0 = (java.util.Map) r0;
        r3 = 0;
        r6 = r3;
        r6 = (java.lang.Throwable) r6;
        r3 = r16.iterator();	 Catch:{ Throwable -> 0x0126, all -> 0x0122 }
        r5 = r16;
        r8 = r5;
        r10 = r8;
        r13 = r10;
        r12 = r17;
        r11 = r18;
        r7 = r2;
        r2 = r13;
    L_0x00c1:
        r1.L$0 = r13;	 Catch:{ Throwable -> 0x0120 }
        r1.L$1 = r12;	 Catch:{ Throwable -> 0x0120 }
        r1.L$2 = r11;	 Catch:{ Throwable -> 0x0120 }
        r1.L$3 = r10;	 Catch:{ Throwable -> 0x0120 }
        r1.L$4 = r0;	 Catch:{ Throwable -> 0x0120 }
        r1.L$5 = r8;	 Catch:{ Throwable -> 0x0120 }
        r1.L$6 = r2;	 Catch:{ Throwable -> 0x0120 }
        r1.L$7 = r6;	 Catch:{ Throwable -> 0x0120 }
        r1.L$8 = r5;	 Catch:{ Throwable -> 0x0120 }
        r1.L$9 = r3;	 Catch:{ Throwable -> 0x0120 }
        r1.label = r4;	 Catch:{ Throwable -> 0x0120 }
        r9 = r3.hasNext(r1);	 Catch:{ Throwable -> 0x0120 }
        if (r9 != r7) goto L_0x00de;
    L_0x00dd:
        return r7;
    L_0x00de:
        r15 = r9;
        r9 = r0;
        r0 = r15;
    L_0x00e1:
        r0 = (java.lang.Boolean) r0;	 Catch:{ Throwable -> 0x0120 }
        r0 = r0.booleanValue();	 Catch:{ Throwable -> 0x0120 }
        if (r0 == 0) goto L_0x0114;
    L_0x00e9:
        r1.L$0 = r13;	 Catch:{ Throwable -> 0x0120 }
        r1.L$1 = r12;	 Catch:{ Throwable -> 0x0120 }
        r1.L$2 = r11;	 Catch:{ Throwable -> 0x0120 }
        r1.L$3 = r10;	 Catch:{ Throwable -> 0x0120 }
        r1.L$4 = r9;	 Catch:{ Throwable -> 0x0120 }
        r1.L$5 = r8;	 Catch:{ Throwable -> 0x0120 }
        r1.L$6 = r2;	 Catch:{ Throwable -> 0x0120 }
        r1.L$7 = r6;	 Catch:{ Throwable -> 0x0120 }
        r1.L$8 = r5;	 Catch:{ Throwable -> 0x0120 }
        r1.L$9 = r3;	 Catch:{ Throwable -> 0x0120 }
        r0 = 2;
        r1.label = r0;	 Catch:{ Throwable -> 0x0120 }
        r0 = r3.next(r1);	 Catch:{ Throwable -> 0x0120 }
        if (r0 != r7) goto L_0x0107;
    L_0x0106:
        return r7;
    L_0x0107:
        r14 = r12.invoke(r0);	 Catch:{ Throwable -> 0x0120 }
        r0 = r11.invoke(r0);	 Catch:{ Throwable -> 0x0120 }
        r9.put(r14, r0);	 Catch:{ Throwable -> 0x0120 }
        r0 = r9;
        goto L_0x00c1;
    L_0x0114:
        r0 = kotlin.Unit.INSTANCE;	 Catch:{ Throwable -> 0x0120 }
        kotlin.jvm.internal.InlineMarker.finallyStart(r4);
        r2.cancel(r6);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r4);
        return r9;
    L_0x0120:
        r0 = move-exception;
        goto L_0x0129;
    L_0x0122:
        r0 = move-exception;
        r2 = r16;
        goto L_0x012c;
    L_0x0126:
        r0 = move-exception;
        r2 = r16;
    L_0x0129:
        r6 = r0;
    L_0x012a:
        throw r6;	 Catch:{ all -> 0x012b }
    L_0x012b:
        r0 = move-exception;
    L_0x012c:
        kotlin.jvm.internal.InlineMarker.finallyStart(r4);
        r2.cancel(r6);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r4);
        throw r0;
    L_0x0136:
        r0 = (kotlin.Result.Failure) r0;
        r0 = r0.exception;
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.associateBy(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Nullable
    @ObsoleteCoroutinesApi
    private static final Object associateBy$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Function1 function1, @NotNull Function1 function12, @NotNull Continuation continuation) {
        Function1 function13;
        Map linkedHashMap = new LinkedHashMap();
        Throwable th = (Throwable) null;
        try {
            ChannelIterator it = receiveChannel.iterator();
            while (true) {
                InlineMarker.mark(0);
                Object hasNext = it.hasNext(continuation);
                InlineMarker.mark(1);
                if (((Boolean) hasNext).booleanValue()) {
                    InlineMarker.mark(0);
                    Object next = it.next(continuation);
                    InlineMarker.mark(1);
                    linkedHashMap.put(function1.invoke(next), function12.invoke(next));
                } else {
                    function1 = Unit.INSTANCE;
                    InlineMarker.finallyStart(1);
                    receiveChannel.cancel(th);
                    InlineMarker.finallyEnd(1);
                    return linkedHashMap;
                }
            }
        } catch (Throwable th2) {
            InlineMarker.finallyStart(1);
            receiveChannel.cancel(function13);
            InlineMarker.finallyEnd(1);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @org.jetbrains.annotations.Nullable
    @kotlinx.coroutines.ObsoleteCoroutinesApi
    public static final <E, K, M extends java.util.Map<? super K, ? super E>> java.lang.Object associateByTo(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r10, @org.jetbrains.annotations.NotNull M r11, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super E, ? extends K> r12, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super M> r13) {
        /*
        r0 = r13 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$associateByTo$1;
        if (r0 == 0) goto L_0x0014;
    L_0x0004:
        r0 = r13;
        r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$associateByTo$1) r0;
        r1 = r0.label;
        r2 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r1 = r1 & r2;
        if (r1 == 0) goto L_0x0014;
    L_0x000e:
        r13 = r0.label;
        r13 = r13 - r2;
        r0.label = r13;
        goto L_0x0019;
    L_0x0014:
        r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$associateByTo$1;
        r0.<init>(r13);
    L_0x0019:
        r13 = r0.result;
        r1 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        r2 = r0.label;
        r3 = 1;
        switch(r2) {
            case 0: goto L_0x008d;
            case 1: goto L_0x005b;
            case 2: goto L_0x002d;
            default: goto L_0x0025;
        };
    L_0x0025:
        r10 = new java.lang.IllegalStateException;
        r11 = "call to 'resume' before 'invoke' with coroutine";
        r10.<init>(r11);
        throw r10;
    L_0x002d:
        r10 = r0.L$7;
        r10 = (kotlinx.coroutines.channels.ChannelIterator) r10;
        r11 = r0.L$6;
        r11 = (kotlinx.coroutines.channels.ReceiveChannel) r11;
        r12 = r0.L$5;
        r12 = (java.lang.Throwable) r12;
        r2 = r0.L$4;
        r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2;
        r4 = r0.L$3;
        r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4;
        r5 = r0.L$2;
        r5 = (kotlin.jvm.functions.Function1) r5;
        r6 = r0.L$1;
        r6 = (java.util.Map) r6;
        r7 = r0.L$0;
        r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7;
        r8 = r13 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        if (r8 != 0) goto L_0x0056;
    L_0x0051:
        r9 = r2;
        r2 = r10;
        r10 = r9;
        goto L_0x00dc;
    L_0x0056:
        r13 = (kotlin.Result.Failure) r13;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r10 = r13.exception;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        throw r10;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
    L_0x005b:
        r10 = r0.L$7;
        r10 = (kotlinx.coroutines.channels.ChannelIterator) r10;
        r11 = r0.L$6;
        r11 = (kotlinx.coroutines.channels.ReceiveChannel) r11;
        r12 = r0.L$5;
        r12 = (java.lang.Throwable) r12;
        r2 = r0.L$4;
        r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2;
        r4 = r0.L$3;
        r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4;
        r5 = r0.L$2;
        r5 = (kotlin.jvm.functions.Function1) r5;
        r6 = r0.L$1;
        r6 = (java.util.Map) r6;
        r7 = r0.L$0;
        r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7;
        r8 = r13 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        if (r8 != 0) goto L_0x0080;
    L_0x007f:
        goto L_0x00ba;
    L_0x0080:
        r13 = (kotlin.Result.Failure) r13;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r10 = r13.exception;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        throw r10;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
    L_0x0085:
        r10 = move-exception;
        goto L_0x00fb;
    L_0x0088:
        r10 = move-exception;
        r12 = r10;
        r10 = r2;
        goto L_0x00f7;
    L_0x008d:
        r2 = r13 instanceof kotlin.Result.Failure;
        if (r2 != 0) goto L_0x0105;
    L_0x0091:
        r13 = 0;
        r13 = (java.lang.Throwable) r13;
        r2 = r10.iterator();	 Catch:{ Throwable -> 0x00f5, all -> 0x00f0 }
        r4 = r10;
        r7 = r4;
        r6 = r11;
        r5 = r12;
        r12 = r13;
        r11 = r7;
    L_0x009e:
        r0.L$0 = r7;	 Catch:{ Throwable -> 0x00f5 }
        r0.L$1 = r6;	 Catch:{ Throwable -> 0x00f5 }
        r0.L$2 = r5;	 Catch:{ Throwable -> 0x00f5 }
        r0.L$3 = r4;	 Catch:{ Throwable -> 0x00f5 }
        r0.L$4 = r10;	 Catch:{ Throwable -> 0x00f5 }
        r0.L$5 = r12;	 Catch:{ Throwable -> 0x00f5 }
        r0.L$6 = r11;	 Catch:{ Throwable -> 0x00f5 }
        r0.L$7 = r2;	 Catch:{ Throwable -> 0x00f5 }
        r0.label = r3;	 Catch:{ Throwable -> 0x00f5 }
        r13 = r2.hasNext(r0);	 Catch:{ Throwable -> 0x00f5 }
        if (r13 != r1) goto L_0x00b7;
    L_0x00b6:
        return r1;
    L_0x00b7:
        r9 = r2;
        r2 = r10;
        r10 = r9;
    L_0x00ba:
        r13 = (java.lang.Boolean) r13;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r13 = r13.booleanValue();	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        if (r13 == 0) goto L_0x00e4;
    L_0x00c2:
        r0.L$0 = r7;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r0.L$1 = r6;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r0.L$2 = r5;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r0.L$3 = r4;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r0.L$4 = r2;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r0.L$5 = r12;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r0.L$6 = r11;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r0.L$7 = r10;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r13 = 2;
        r0.label = r13;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r13 = r10.next(r0);	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        if (r13 != r1) goto L_0x0051;
    L_0x00db:
        return r1;
    L_0x00dc:
        r8 = r5.invoke(r13);	 Catch:{ Throwable -> 0x00f5 }
        r6.put(r8, r13);	 Catch:{ Throwable -> 0x00f5 }
        goto L_0x009e;
    L_0x00e4:
        r10 = kotlin.Unit.INSTANCE;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        kotlin.jvm.internal.InlineMarker.finallyStart(r3);
        r2.cancel(r12);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r3);
        return r6;
    L_0x00f0:
        r11 = move-exception;
        r2 = r10;
        r10 = r11;
        r12 = r13;
        goto L_0x00fb;
    L_0x00f5:
        r11 = move-exception;
        r12 = r11;
    L_0x00f7:
        throw r12;	 Catch:{ all -> 0x00f8 }
    L_0x00f8:
        r11 = move-exception;
        r2 = r10;
        r10 = r11;
    L_0x00fb:
        kotlin.jvm.internal.InlineMarker.finallyStart(r3);
        r2.cancel(r12);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r3);
        throw r10;
    L_0x0105:
        r13 = (kotlin.Result.Failure) r13;
        r10 = r13.exception;
        throw r10;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.associateByTo(kotlinx.coroutines.channels.ReceiveChannel, java.util.Map, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @org.jetbrains.annotations.Nullable
    @kotlinx.coroutines.ObsoleteCoroutinesApi
    public static final <E, K, V, M extends java.util.Map<? super K, ? super V>> java.lang.Object associateByTo(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r11, @org.jetbrains.annotations.NotNull M r12, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super E, ? extends K> r13, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super E, ? extends V> r14, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super M> r15) {
        /*
        r0 = r15 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$associateByTo$3;
        if (r0 == 0) goto L_0x0014;
    L_0x0004:
        r0 = r15;
        r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$associateByTo$3) r0;
        r1 = r0.label;
        r2 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r1 = r1 & r2;
        if (r1 == 0) goto L_0x0014;
    L_0x000e:
        r15 = r0.label;
        r15 = r15 - r2;
        r0.label = r15;
        goto L_0x0019;
    L_0x0014:
        r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$associateByTo$3;
        r0.<init>(r15);
    L_0x0019:
        r15 = r0.result;
        r1 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        r2 = r0.label;
        r3 = 1;
        switch(r2) {
            case 0: goto L_0x009c;
            case 1: goto L_0x0062;
            case 2: goto L_0x002d;
            default: goto L_0x0025;
        };
    L_0x0025:
        r11 = new java.lang.IllegalStateException;
        r12 = "call to 'resume' before 'invoke' with coroutine";
        r11.<init>(r12);
        throw r11;
    L_0x002d:
        r11 = r0.L$8;
        r11 = (kotlinx.coroutines.channels.ChannelIterator) r11;
        r12 = r0.L$7;
        r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12;
        r13 = r0.L$6;
        r13 = (java.lang.Throwable) r13;
        r14 = r0.L$5;
        r14 = (kotlinx.coroutines.channels.ReceiveChannel) r14;
        r2 = r0.L$4;
        r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2;
        r4 = r0.L$3;
        r4 = (kotlin.jvm.functions.Function1) r4;
        r5 = r0.L$2;
        r5 = (kotlin.jvm.functions.Function1) r5;
        r6 = r0.L$1;
        r6 = (java.util.Map) r6;
        r7 = r0.L$0;
        r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7;
        r8 = r15 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x0097, all -> 0x0093 }
        if (r8 != 0) goto L_0x005d;
    L_0x0055:
        r9 = r2;
        r2 = r11;
        r11 = r9;
        r10 = r14;
        r14 = r12;
        r12 = r10;
        goto L_0x00f3;
    L_0x005d:
        r15 = (kotlin.Result.Failure) r15;	 Catch:{ Throwable -> 0x0097, all -> 0x0093 }
        r11 = r15.exception;	 Catch:{ Throwable -> 0x0097, all -> 0x0093 }
        throw r11;	 Catch:{ Throwable -> 0x0097, all -> 0x0093 }
    L_0x0062:
        r11 = r0.L$8;
        r11 = (kotlinx.coroutines.channels.ChannelIterator) r11;
        r12 = r0.L$7;
        r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12;
        r13 = r0.L$6;
        r13 = (java.lang.Throwable) r13;
        r14 = r0.L$5;
        r14 = (kotlinx.coroutines.channels.ReceiveChannel) r14;
        r2 = r0.L$4;
        r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2;
        r4 = r0.L$3;
        r4 = (kotlin.jvm.functions.Function1) r4;
        r5 = r0.L$2;
        r5 = (kotlin.jvm.functions.Function1) r5;
        r6 = r0.L$1;
        r6 = (java.util.Map) r6;
        r7 = r0.L$0;
        r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7;
        r8 = r15 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x0097, all -> 0x0093 }
        if (r8 != 0) goto L_0x008e;
    L_0x008a:
        r9 = r14;
        r14 = r12;
        r12 = r9;
        goto L_0x00cc;
    L_0x008e:
        r15 = (kotlin.Result.Failure) r15;	 Catch:{ Throwable -> 0x0097, all -> 0x0093 }
        r11 = r15.exception;	 Catch:{ Throwable -> 0x0097, all -> 0x0093 }
        throw r11;	 Catch:{ Throwable -> 0x0097, all -> 0x0093 }
    L_0x0093:
        r11 = move-exception;
        r12 = r14;
        goto L_0x011c;
    L_0x0097:
        r11 = move-exception;
        r13 = r11;
        r11 = r14;
        goto L_0x0119;
    L_0x009c:
        r2 = r15 instanceof kotlin.Result.Failure;
        if (r2 != 0) goto L_0x0126;
    L_0x00a0:
        r15 = 0;
        r15 = (java.lang.Throwable) r15;
        r2 = r11.iterator();	 Catch:{ Throwable -> 0x0117, all -> 0x0111 }
        r7 = r11;
        r6 = r12;
        r5 = r13;
        r4 = r14;
        r13 = r15;
        r12 = r7;
        r14 = r12;
    L_0x00ae:
        r0.L$0 = r7;	 Catch:{ Throwable -> 0x010d, all -> 0x010b }
        r0.L$1 = r6;	 Catch:{ Throwable -> 0x010d, all -> 0x010b }
        r0.L$2 = r5;	 Catch:{ Throwable -> 0x010d, all -> 0x010b }
        r0.L$3 = r4;	 Catch:{ Throwable -> 0x010d, all -> 0x010b }
        r0.L$4 = r11;	 Catch:{ Throwable -> 0x010d, all -> 0x010b }
        r0.L$5 = r12;	 Catch:{ Throwable -> 0x010d, all -> 0x010b }
        r0.L$6 = r13;	 Catch:{ Throwable -> 0x010d, all -> 0x010b }
        r0.L$7 = r14;	 Catch:{ Throwable -> 0x010d, all -> 0x010b }
        r0.L$8 = r2;	 Catch:{ Throwable -> 0x010d, all -> 0x010b }
        r0.label = r3;	 Catch:{ Throwable -> 0x010d, all -> 0x010b }
        r15 = r2.hasNext(r0);	 Catch:{ Throwable -> 0x010d, all -> 0x010b }
        if (r15 != r1) goto L_0x00c9;
    L_0x00c8:
        return r1;
    L_0x00c9:
        r9 = r2;
        r2 = r11;
        r11 = r9;
    L_0x00cc:
        r15 = (java.lang.Boolean) r15;	 Catch:{ Throwable -> 0x010d, all -> 0x010b }
        r15 = r15.booleanValue();	 Catch:{ Throwable -> 0x010d, all -> 0x010b }
        if (r15 == 0) goto L_0x00ff;
    L_0x00d4:
        r0.L$0 = r7;	 Catch:{ Throwable -> 0x010d, all -> 0x010b }
        r0.L$1 = r6;	 Catch:{ Throwable -> 0x010d, all -> 0x010b }
        r0.L$2 = r5;	 Catch:{ Throwable -> 0x010d, all -> 0x010b }
        r0.L$3 = r4;	 Catch:{ Throwable -> 0x010d, all -> 0x010b }
        r0.L$4 = r2;	 Catch:{ Throwable -> 0x010d, all -> 0x010b }
        r0.L$5 = r12;	 Catch:{ Throwable -> 0x010d, all -> 0x010b }
        r0.L$6 = r13;	 Catch:{ Throwable -> 0x010d, all -> 0x010b }
        r0.L$7 = r14;	 Catch:{ Throwable -> 0x010d, all -> 0x010b }
        r0.L$8 = r11;	 Catch:{ Throwable -> 0x010d, all -> 0x010b }
        r15 = 2;
        r0.label = r15;	 Catch:{ Throwable -> 0x010d, all -> 0x010b }
        r15 = r11.next(r0);	 Catch:{ Throwable -> 0x010d, all -> 0x010b }
        if (r15 != r1) goto L_0x00f0;
    L_0x00ef:
        return r1;
    L_0x00f0:
        r9 = r2;
        r2 = r11;
        r11 = r9;
    L_0x00f3:
        r8 = r5.invoke(r15);	 Catch:{ Throwable -> 0x010d, all -> 0x010b }
        r15 = r4.invoke(r15);	 Catch:{ Throwable -> 0x010d, all -> 0x010b }
        r6.put(r8, r15);	 Catch:{ Throwable -> 0x010d, all -> 0x010b }
        goto L_0x00ae;
    L_0x00ff:
        r11 = kotlin.Unit.INSTANCE;	 Catch:{ Throwable -> 0x010d, all -> 0x010b }
        kotlin.jvm.internal.InlineMarker.finallyStart(r3);
        r12.cancel(r13);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r3);
        return r6;
    L_0x010b:
        r11 = move-exception;
        goto L_0x011c;
    L_0x010d:
        r11 = move-exception;
        r13 = r11;
        r11 = r12;
        goto L_0x0119;
    L_0x0111:
        r12 = move-exception;
        r13 = r15;
    L_0x0113:
        r9 = r12;
        r12 = r11;
        r11 = r9;
        goto L_0x011c;
    L_0x0117:
        r12 = move-exception;
        r13 = r12;
    L_0x0119:
        throw r13;	 Catch:{ all -> 0x011a }
    L_0x011a:
        r12 = move-exception;
        goto L_0x0113;
    L_0x011c:
        kotlin.jvm.internal.InlineMarker.finallyStart(r3);
        r12.cancel(r13);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r3);
        throw r11;
    L_0x0126:
        r15 = (kotlin.Result.Failure) r15;
        r11 = r15.exception;
        throw r11;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.associateByTo(kotlinx.coroutines.channels.ReceiveChannel, java.util.Map, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @org.jetbrains.annotations.Nullable
    @kotlinx.coroutines.ObsoleteCoroutinesApi
    public static final <E, K, V, M extends java.util.Map<? super K, ? super V>> java.lang.Object associateTo(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r10, @org.jetbrains.annotations.NotNull M r11, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super E, ? extends kotlin.Pair<? extends K, ? extends V>> r12, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super M> r13) {
        /*
        r0 = r13 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$associateTo$1;
        if (r0 == 0) goto L_0x0014;
    L_0x0004:
        r0 = r13;
        r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$associateTo$1) r0;
        r1 = r0.label;
        r2 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r1 = r1 & r2;
        if (r1 == 0) goto L_0x0014;
    L_0x000e:
        r13 = r0.label;
        r13 = r13 - r2;
        r0.label = r13;
        goto L_0x0019;
    L_0x0014:
        r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$associateTo$1;
        r0.<init>(r13);
    L_0x0019:
        r13 = r0.result;
        r1 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        r2 = r0.label;
        r3 = 1;
        switch(r2) {
            case 0: goto L_0x008d;
            case 1: goto L_0x005b;
            case 2: goto L_0x002d;
            default: goto L_0x0025;
        };
    L_0x0025:
        r10 = new java.lang.IllegalStateException;
        r11 = "call to 'resume' before 'invoke' with coroutine";
        r10.<init>(r11);
        throw r10;
    L_0x002d:
        r10 = r0.L$7;
        r10 = (kotlinx.coroutines.channels.ChannelIterator) r10;
        r11 = r0.L$6;
        r11 = (kotlinx.coroutines.channels.ReceiveChannel) r11;
        r12 = r0.L$5;
        r12 = (java.lang.Throwable) r12;
        r2 = r0.L$4;
        r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2;
        r4 = r0.L$3;
        r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4;
        r5 = r0.L$2;
        r5 = (kotlin.jvm.functions.Function1) r5;
        r6 = r0.L$1;
        r6 = (java.util.Map) r6;
        r7 = r0.L$0;
        r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7;
        r8 = r13 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        if (r8 != 0) goto L_0x0056;
    L_0x0051:
        r9 = r2;
        r2 = r10;
        r10 = r9;
        goto L_0x00dc;
    L_0x0056:
        r13 = (kotlin.Result.Failure) r13;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r10 = r13.exception;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        throw r10;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
    L_0x005b:
        r10 = r0.L$7;
        r10 = (kotlinx.coroutines.channels.ChannelIterator) r10;
        r11 = r0.L$6;
        r11 = (kotlinx.coroutines.channels.ReceiveChannel) r11;
        r12 = r0.L$5;
        r12 = (java.lang.Throwable) r12;
        r2 = r0.L$4;
        r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2;
        r4 = r0.L$3;
        r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4;
        r5 = r0.L$2;
        r5 = (kotlin.jvm.functions.Function1) r5;
        r6 = r0.L$1;
        r6 = (java.util.Map) r6;
        r7 = r0.L$0;
        r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7;
        r8 = r13 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        if (r8 != 0) goto L_0x0080;
    L_0x007f:
        goto L_0x00ba;
    L_0x0080:
        r13 = (kotlin.Result.Failure) r13;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r10 = r13.exception;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        throw r10;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
    L_0x0085:
        r10 = move-exception;
        goto L_0x0105;
    L_0x0088:
        r10 = move-exception;
        r12 = r10;
        r10 = r2;
        goto L_0x0101;
    L_0x008d:
        r2 = r13 instanceof kotlin.Result.Failure;
        if (r2 != 0) goto L_0x010f;
    L_0x0091:
        r13 = 0;
        r13 = (java.lang.Throwable) r13;
        r2 = r10.iterator();	 Catch:{ Throwable -> 0x00ff, all -> 0x00fa }
        r4 = r10;
        r7 = r4;
        r6 = r11;
        r5 = r12;
        r12 = r13;
        r11 = r7;
    L_0x009e:
        r0.L$0 = r7;	 Catch:{ Throwable -> 0x00ff }
        r0.L$1 = r6;	 Catch:{ Throwable -> 0x00ff }
        r0.L$2 = r5;	 Catch:{ Throwable -> 0x00ff }
        r0.L$3 = r4;	 Catch:{ Throwable -> 0x00ff }
        r0.L$4 = r10;	 Catch:{ Throwable -> 0x00ff }
        r0.L$5 = r12;	 Catch:{ Throwable -> 0x00ff }
        r0.L$6 = r11;	 Catch:{ Throwable -> 0x00ff }
        r0.L$7 = r2;	 Catch:{ Throwable -> 0x00ff }
        r0.label = r3;	 Catch:{ Throwable -> 0x00ff }
        r13 = r2.hasNext(r0);	 Catch:{ Throwable -> 0x00ff }
        if (r13 != r1) goto L_0x00b7;
    L_0x00b6:
        return r1;
    L_0x00b7:
        r9 = r2;
        r2 = r10;
        r10 = r9;
    L_0x00ba:
        r13 = (java.lang.Boolean) r13;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r13 = r13.booleanValue();	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        if (r13 == 0) goto L_0x00ee;
    L_0x00c2:
        r0.L$0 = r7;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r0.L$1 = r6;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r0.L$2 = r5;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r0.L$3 = r4;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r0.L$4 = r2;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r0.L$5 = r12;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r0.L$6 = r11;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r0.L$7 = r10;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r13 = 2;
        r0.label = r13;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r13 = r10.next(r0);	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        if (r13 != r1) goto L_0x0051;
    L_0x00db:
        return r1;
    L_0x00dc:
        r13 = r5.invoke(r13);	 Catch:{ Throwable -> 0x00ff }
        r13 = (kotlin.Pair) r13;	 Catch:{ Throwable -> 0x00ff }
        r8 = r13.getFirst();	 Catch:{ Throwable -> 0x00ff }
        r13 = r13.getSecond();	 Catch:{ Throwable -> 0x00ff }
        r6.put(r8, r13);	 Catch:{ Throwable -> 0x00ff }
        goto L_0x009e;
    L_0x00ee:
        r10 = kotlin.Unit.INSTANCE;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        kotlin.jvm.internal.InlineMarker.finallyStart(r3);
        r2.cancel(r12);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r3);
        return r6;
    L_0x00fa:
        r11 = move-exception;
        r2 = r10;
        r10 = r11;
        r12 = r13;
        goto L_0x0105;
    L_0x00ff:
        r11 = move-exception;
        r12 = r11;
    L_0x0101:
        throw r12;	 Catch:{ all -> 0x0102 }
    L_0x0102:
        r11 = move-exception;
        r2 = r10;
        r10 = r11;
    L_0x0105:
        kotlin.jvm.internal.InlineMarker.finallyStart(r3);
        r2.cancel(r12);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r3);
        throw r10;
    L_0x010f:
        r13 = (kotlin.Result.Failure) r13;
        r10 = r13.exception;
        throw r10;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.associateTo(kotlinx.coroutines.channels.ReceiveChannel, java.util.Map, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @org.jetbrains.annotations.Nullable
    @kotlinx.coroutines.ObsoleteCoroutinesApi
    public static final <E, C extends kotlinx.coroutines.channels.SendChannel<? super E>> java.lang.Object toChannel(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r9, @org.jetbrains.annotations.NotNull C r10, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super C> r11) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:37)
	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:61)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1603198149.run(Unknown Source)
*/
        /*
        r0 = r11 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$toChannel$1;
        if (r0 == 0) goto L_0x0014;
    L_0x0004:
        r0 = r11;
        r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$toChannel$1) r0;
        r1 = r0.label;
        r2 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r1 = r1 & r2;
        if (r1 == 0) goto L_0x0014;
    L_0x000e:
        r11 = r0.label;
        r11 = r11 - r2;
        r0.label = r11;
        goto L_0x0019;
    L_0x0014:
        r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$toChannel$1;
        r0.<init>(r11);
    L_0x0019:
        r11 = r0.result;
        r1 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        r2 = r0.label;
        switch(r2) {
            case 0: goto L_0x00be;
            case 1: goto L_0x008a;
            case 2: goto L_0x005f;
            case 3: goto L_0x002c;
            default: goto L_0x0024;
        };
    L_0x0024:
        r9 = new java.lang.IllegalStateException;
        r10 = "call to 'resume' before 'invoke' with coroutine";
        r9.<init>(r10);
        throw r9;
    L_0x002c:
        r9 = r0.L$8;
        r9 = r0.L$7;
        r9 = r0.L$6;
        r9 = (kotlinx.coroutines.channels.ChannelIterator) r9;
        r10 = r0.L$5;
        r10 = (kotlinx.coroutines.channels.ReceiveChannel) r10;
        r2 = r0.L$4;
        r2 = (java.lang.Throwable) r2;
        r3 = r0.L$3;
        r3 = (kotlinx.coroutines.channels.ReceiveChannel) r3;
        r4 = r0.L$2;
        r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4;
        r5 = r0.L$1;
        r5 = (kotlinx.coroutines.channels.SendChannel) r5;
        r6 = r0.L$0;
        r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6;
        r7 = r11 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x00b9, all -> 0x00b3 }
        if (r7 != 0) goto L_0x005a;	 Catch:{ Throwable -> 0x00b9, all -> 0x00b3 }
    L_0x0050:
        r11 = r5;	 Catch:{ Throwable -> 0x00b9, all -> 0x00b3 }
        r5 = r1;	 Catch:{ Throwable -> 0x00b9, all -> 0x00b3 }
        r1 = r4;	 Catch:{ Throwable -> 0x00b9, all -> 0x00b3 }
        r4 = r10;	 Catch:{ Throwable -> 0x00b9, all -> 0x00b3 }
        r10 = r6;	 Catch:{ Throwable -> 0x00b9, all -> 0x00b3 }
        r8 = r3;	 Catch:{ Throwable -> 0x00b9, all -> 0x00b3 }
        r3 = r2;	 Catch:{ Throwable -> 0x00b9, all -> 0x00b3 }
        r2 = r8;	 Catch:{ Throwable -> 0x00b9, all -> 0x00b3 }
        goto L_0x00d2;	 Catch:{ Throwable -> 0x00b9, all -> 0x00b3 }
    L_0x005a:
        r11 = (kotlin.Result.Failure) r11;	 Catch:{ Throwable -> 0x00b9, all -> 0x00b3 }
        r9 = r11.exception;	 Catch:{ Throwable -> 0x00b9, all -> 0x00b3 }
        throw r9;	 Catch:{ Throwable -> 0x00b9, all -> 0x00b3 }
    L_0x005f:
        r9 = r0.L$6;
        r9 = (kotlinx.coroutines.channels.ChannelIterator) r9;
        r10 = r0.L$5;
        r10 = (kotlinx.coroutines.channels.ReceiveChannel) r10;
        r2 = r0.L$4;
        r2 = (java.lang.Throwable) r2;
        r3 = r0.L$3;
        r3 = (kotlinx.coroutines.channels.ReceiveChannel) r3;
        r4 = r0.L$2;
        r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4;
        r5 = r0.L$1;
        r5 = (kotlinx.coroutines.channels.SendChannel) r5;
        r6 = r0.L$0;
        r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6;
        r7 = r11 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x00b9, all -> 0x00b3 }
        if (r7 != 0) goto L_0x0085;	 Catch:{ Throwable -> 0x00b9, all -> 0x00b3 }
    L_0x007f:
        r8 = r1;	 Catch:{ Throwable -> 0x00b9, all -> 0x00b3 }
        r1 = r9;	 Catch:{ Throwable -> 0x00b9, all -> 0x00b3 }
        r9 = r3;	 Catch:{ Throwable -> 0x00b9, all -> 0x00b3 }
    L_0x0082:
        r3 = r8;	 Catch:{ Throwable -> 0x00b9, all -> 0x00b3 }
        goto L_0x0117;	 Catch:{ Throwable -> 0x00b9, all -> 0x00b3 }
    L_0x0085:
        r11 = (kotlin.Result.Failure) r11;	 Catch:{ Throwable -> 0x00b9, all -> 0x00b3 }
        r9 = r11.exception;	 Catch:{ Throwable -> 0x00b9, all -> 0x00b3 }
        throw r9;	 Catch:{ Throwable -> 0x00b9, all -> 0x00b3 }
    L_0x008a:
        r9 = r0.L$6;
        r9 = (kotlinx.coroutines.channels.ChannelIterator) r9;
        r10 = r0.L$5;
        r10 = (kotlinx.coroutines.channels.ReceiveChannel) r10;
        r2 = r0.L$4;
        r2 = (java.lang.Throwable) r2;
        r3 = r0.L$3;
        r3 = (kotlinx.coroutines.channels.ReceiveChannel) r3;
        r4 = r0.L$2;
        r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4;
        r5 = r0.L$1;
        r5 = (kotlinx.coroutines.channels.SendChannel) r5;
        r6 = r0.L$0;
        r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6;
        r7 = r11 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x00b9, all -> 0x00b3 }
        if (r7 != 0) goto L_0x00ae;	 Catch:{ Throwable -> 0x00b9, all -> 0x00b3 }
    L_0x00aa:
        r8 = r3;	 Catch:{ Throwable -> 0x00b9, all -> 0x00b3 }
        r3 = r2;	 Catch:{ Throwable -> 0x00b9, all -> 0x00b3 }
        r2 = r8;	 Catch:{ Throwable -> 0x00b9, all -> 0x00b3 }
        goto L_0x00f1;	 Catch:{ Throwable -> 0x00b9, all -> 0x00b3 }
    L_0x00ae:
        r11 = (kotlin.Result.Failure) r11;	 Catch:{ Throwable -> 0x00b9, all -> 0x00b3 }
        r9 = r11.exception;	 Catch:{ Throwable -> 0x00b9, all -> 0x00b3 }
        throw r9;	 Catch:{ Throwable -> 0x00b9, all -> 0x00b3 }
    L_0x00b3:
        r9 = move-exception;
        r8 = r3;
        r3 = r2;
        r2 = r8;
        goto L_0x0151;
    L_0x00b9:
        r9 = move-exception;
        r2 = r9;
        r9 = r3;
        goto L_0x0150;
    L_0x00be:
        r2 = r11 instanceof kotlin.Result.Failure;
        if (r2 != 0) goto L_0x0155;
    L_0x00c2:
        r11 = 0;
        r2 = r11;
        r2 = (java.lang.Throwable) r2;
        r11 = r9.iterator();	 Catch:{ Throwable -> 0x014e }
        r4 = r9;
        r5 = r1;
        r3 = r2;
        r1 = r4;
        r2 = r1;
        r9 = r11;
        r11 = r10;
        r10 = r2;
    L_0x00d2:
        r0.L$0 = r10;	 Catch:{ Throwable -> 0x0144, all -> 0x0142 }
        r0.L$1 = r11;	 Catch:{ Throwable -> 0x0144, all -> 0x0142 }
        r0.L$2 = r1;	 Catch:{ Throwable -> 0x0144, all -> 0x0142 }
        r0.L$3 = r2;	 Catch:{ Throwable -> 0x0144, all -> 0x0142 }
        r0.L$4 = r3;	 Catch:{ Throwable -> 0x0144, all -> 0x0142 }
        r0.L$5 = r4;	 Catch:{ Throwable -> 0x0144, all -> 0x0142 }
        r0.L$6 = r9;	 Catch:{ Throwable -> 0x0144, all -> 0x0142 }
        r6 = 1;	 Catch:{ Throwable -> 0x0144, all -> 0x0142 }
        r0.label = r6;	 Catch:{ Throwable -> 0x0144, all -> 0x0142 }
        r6 = r9.hasNext(r0);	 Catch:{ Throwable -> 0x0144, all -> 0x0142 }
        if (r6 != r5) goto L_0x00ea;	 Catch:{ Throwable -> 0x0144, all -> 0x0142 }
    L_0x00e9:
        return r5;	 Catch:{ Throwable -> 0x0144, all -> 0x0142 }
    L_0x00ea:
        r8 = r6;	 Catch:{ Throwable -> 0x0144, all -> 0x0142 }
        r6 = r10;	 Catch:{ Throwable -> 0x0144, all -> 0x0142 }
        r10 = r4;	 Catch:{ Throwable -> 0x0144, all -> 0x0142 }
        r4 = r1;	 Catch:{ Throwable -> 0x0144, all -> 0x0142 }
        r1 = r5;	 Catch:{ Throwable -> 0x0144, all -> 0x0142 }
        r5 = r11;	 Catch:{ Throwable -> 0x0144, all -> 0x0142 }
        r11 = r8;	 Catch:{ Throwable -> 0x0144, all -> 0x0142 }
    L_0x00f1:
        r11 = (java.lang.Boolean) r11;	 Catch:{ Throwable -> 0x0144, all -> 0x0142 }
        r11 = r11.booleanValue();	 Catch:{ Throwable -> 0x0144, all -> 0x0142 }
        if (r11 == 0) goto L_0x013c;	 Catch:{ Throwable -> 0x0144, all -> 0x0142 }
    L_0x00f9:
        r0.L$0 = r6;	 Catch:{ Throwable -> 0x0144, all -> 0x0142 }
        r0.L$1 = r5;	 Catch:{ Throwable -> 0x0144, all -> 0x0142 }
        r0.L$2 = r4;	 Catch:{ Throwable -> 0x0144, all -> 0x0142 }
        r0.L$3 = r2;	 Catch:{ Throwable -> 0x0144, all -> 0x0142 }
        r0.L$4 = r3;	 Catch:{ Throwable -> 0x0144, all -> 0x0142 }
        r0.L$5 = r10;	 Catch:{ Throwable -> 0x0144, all -> 0x0142 }
        r0.L$6 = r9;	 Catch:{ Throwable -> 0x0144, all -> 0x0142 }
        r11 = 2;	 Catch:{ Throwable -> 0x0144, all -> 0x0142 }
        r0.label = r11;	 Catch:{ Throwable -> 0x0144, all -> 0x0142 }
        r11 = r9.next(r0);	 Catch:{ Throwable -> 0x0144, all -> 0x0142 }
        if (r11 != r1) goto L_0x0111;
    L_0x0110:
        return r1;
    L_0x0111:
        r8 = r1;
        r1 = r9;
        r9 = r2;
        r2 = r3;
        goto L_0x0082;
    L_0x0117:
        r0.L$0 = r6;	 Catch:{ Throwable -> 0x014e }
        r0.L$1 = r5;	 Catch:{ Throwable -> 0x014e }
        r0.L$2 = r4;	 Catch:{ Throwable -> 0x014e }
        r0.L$3 = r9;	 Catch:{ Throwable -> 0x014e }
        r0.L$4 = r2;	 Catch:{ Throwable -> 0x014e }
        r0.L$5 = r10;	 Catch:{ Throwable -> 0x014e }
        r0.L$6 = r1;	 Catch:{ Throwable -> 0x014e }
        r0.L$7 = r11;	 Catch:{ Throwable -> 0x014e }
        r0.L$8 = r11;	 Catch:{ Throwable -> 0x014e }
        r7 = 3;	 Catch:{ Throwable -> 0x014e }
        r0.label = r7;	 Catch:{ Throwable -> 0x014e }
        r11 = r5.send(r11, r0);	 Catch:{ Throwable -> 0x014e }
        if (r11 != r3) goto L_0x0133;
    L_0x0132:
        return r3;
    L_0x0133:
        r11 = r5;
        r5 = r3;
        r3 = r2;
        r2 = r9;
        r9 = r1;
        r1 = r4;
        r4 = r10;
        r10 = r6;
        goto L_0x00d2;
    L_0x013c:
        r9 = kotlin.Unit.INSTANCE;	 Catch:{ Throwable -> 0x0144, all -> 0x0142 }
        r2.cancel(r3);
        return r5;
    L_0x0142:
        r9 = move-exception;
        goto L_0x0151;
    L_0x0144:
        r9 = move-exception;
        r8 = r2;
        r2 = r9;
        r9 = r8;
        goto L_0x0150;
    L_0x0149:
        r10 = move-exception;
        r3 = r2;
        r2 = r9;
        r9 = r10;
        goto L_0x0151;
    L_0x014e:
        r10 = move-exception;
        r2 = r10;
    L_0x0150:
        throw r2;	 Catch:{ all -> 0x0149 }
    L_0x0151:
        r2.cancel(r3);
        throw r9;
    L_0x0155:
        r11 = (kotlin.Result.Failure) r11;
        r9 = r11.exception;
        throw r9;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.toChannel(kotlinx.coroutines.channels.ReceiveChannel, kotlinx.coroutines.channels.SendChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @org.jetbrains.annotations.Nullable
    @kotlinx.coroutines.ObsoleteCoroutinesApi
    public static final <E, C extends java.util.Collection<? super E>> java.lang.Object toCollection(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r9, @org.jetbrains.annotations.NotNull C r10, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super C> r11) {
        /*
        r0 = r11 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$toCollection$1;
        if (r0 == 0) goto L_0x0014;
    L_0x0004:
        r0 = r11;
        r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$toCollection$1) r0;
        r1 = r0.label;
        r2 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r1 = r1 & r2;
        if (r1 == 0) goto L_0x0014;
    L_0x000e:
        r11 = r0.label;
        r11 = r11 - r2;
        r0.label = r11;
        goto L_0x0019;
    L_0x0014:
        r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$toCollection$1;
        r0.<init>(r11);
    L_0x0019:
        r11 = r0.result;
        r1 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        r2 = r0.label;
        switch(r2) {
            case 0: goto L_0x0081;
            case 1: goto L_0x0053;
            case 2: goto L_0x002c;
            default: goto L_0x0024;
        };
    L_0x0024:
        r9 = new java.lang.IllegalStateException;
        r10 = "call to 'resume' before 'invoke' with coroutine";
        r9.<init>(r10);
        throw r9;
    L_0x002c:
        r9 = r0.L$6;
        r9 = (kotlinx.coroutines.channels.ChannelIterator) r9;
        r10 = r0.L$5;
        r10 = (kotlinx.coroutines.channels.ReceiveChannel) r10;
        r2 = r0.L$4;
        r2 = (java.lang.Throwable) r2;
        r3 = r0.L$3;
        r3 = (kotlinx.coroutines.channels.ReceiveChannel) r3;
        r4 = r0.L$2;
        r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4;
        r5 = r0.L$1;
        r5 = (java.util.Collection) r5;
        r6 = r0.L$0;
        r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6;
        r7 = r11 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x007c, all -> 0x0079 }
        if (r7 != 0) goto L_0x004e;
    L_0x004c:
        goto L_0x00cd;
    L_0x004e:
        r11 = (kotlin.Result.Failure) r11;	 Catch:{ Throwable -> 0x007c, all -> 0x0079 }
        r9 = r11.exception;	 Catch:{ Throwable -> 0x007c, all -> 0x0079 }
        throw r9;	 Catch:{ Throwable -> 0x007c, all -> 0x0079 }
    L_0x0053:
        r9 = r0.L$6;
        r9 = (kotlinx.coroutines.channels.ChannelIterator) r9;
        r10 = r0.L$5;
        r10 = (kotlinx.coroutines.channels.ReceiveChannel) r10;
        r2 = r0.L$4;
        r2 = (java.lang.Throwable) r2;
        r3 = r0.L$3;
        r3 = (kotlinx.coroutines.channels.ReceiveChannel) r3;
        r4 = r0.L$2;
        r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4;
        r5 = r0.L$1;
        r5 = (java.util.Collection) r5;
        r6 = r0.L$0;
        r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6;
        r7 = r11 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x007c, all -> 0x0079 }
        if (r7 != 0) goto L_0x0074;
    L_0x0073:
        goto L_0x00ad;
    L_0x0074:
        r11 = (kotlin.Result.Failure) r11;	 Catch:{ Throwable -> 0x007c, all -> 0x0079 }
        r9 = r11.exception;	 Catch:{ Throwable -> 0x007c, all -> 0x0079 }
        throw r9;	 Catch:{ Throwable -> 0x007c, all -> 0x0079 }
    L_0x0079:
        r9 = move-exception;
        goto L_0x00e0;
    L_0x007c:
        r9 = move-exception;
        r2 = r9;
        r9 = r3;
        goto L_0x00df;
    L_0x0081:
        r2 = r11 instanceof kotlin.Result.Failure;
        if (r2 != 0) goto L_0x00e4;
    L_0x0085:
        r11 = 0;
        r2 = r11;
        r2 = (java.lang.Throwable) r2;
        r11 = r9.iterator();	 Catch:{ Throwable -> 0x00dd }
        r4 = r9;
        r6 = r4;
        r5 = r10;
        r10 = r6;
    L_0x0091:
        r0.L$0 = r6;	 Catch:{ Throwable -> 0x00dd }
        r0.L$1 = r5;	 Catch:{ Throwable -> 0x00dd }
        r0.L$2 = r4;	 Catch:{ Throwable -> 0x00dd }
        r0.L$3 = r9;	 Catch:{ Throwable -> 0x00dd }
        r0.L$4 = r2;	 Catch:{ Throwable -> 0x00dd }
        r0.L$5 = r10;	 Catch:{ Throwable -> 0x00dd }
        r0.L$6 = r11;	 Catch:{ Throwable -> 0x00dd }
        r3 = 1;
        r0.label = r3;	 Catch:{ Throwable -> 0x00dd }
        r3 = r11.hasNext(r0);	 Catch:{ Throwable -> 0x00dd }
        if (r3 != r1) goto L_0x00a9;
    L_0x00a8:
        return r1;
    L_0x00a9:
        r8 = r3;
        r3 = r9;
        r9 = r11;
        r11 = r8;
    L_0x00ad:
        r11 = (java.lang.Boolean) r11;	 Catch:{ Throwable -> 0x007c, all -> 0x0079 }
        r11 = r11.booleanValue();	 Catch:{ Throwable -> 0x007c, all -> 0x0079 }
        if (r11 == 0) goto L_0x00d3;
    L_0x00b5:
        r0.L$0 = r6;	 Catch:{ Throwable -> 0x007c, all -> 0x0079 }
        r0.L$1 = r5;	 Catch:{ Throwable -> 0x007c, all -> 0x0079 }
        r0.L$2 = r4;	 Catch:{ Throwable -> 0x007c, all -> 0x0079 }
        r0.L$3 = r3;	 Catch:{ Throwable -> 0x007c, all -> 0x0079 }
        r0.L$4 = r2;	 Catch:{ Throwable -> 0x007c, all -> 0x0079 }
        r0.L$5 = r10;	 Catch:{ Throwable -> 0x007c, all -> 0x0079 }
        r0.L$6 = r9;	 Catch:{ Throwable -> 0x007c, all -> 0x0079 }
        r11 = 2;
        r0.label = r11;	 Catch:{ Throwable -> 0x007c, all -> 0x0079 }
        r11 = r9.next(r0);	 Catch:{ Throwable -> 0x007c, all -> 0x0079 }
        if (r11 != r1) goto L_0x00cd;
    L_0x00cc:
        return r1;
    L_0x00cd:
        r5.add(r11);	 Catch:{ Throwable -> 0x007c, all -> 0x0079 }
        r11 = r9;
        r9 = r3;
        goto L_0x0091;
    L_0x00d3:
        r9 = kotlin.Unit.INSTANCE;	 Catch:{ Throwable -> 0x007c, all -> 0x0079 }
        r3.cancel(r2);
        return r5;
    L_0x00d9:
        r10 = move-exception;
        r3 = r9;
        r9 = r10;
        goto L_0x00e0;
    L_0x00dd:
        r10 = move-exception;
        r2 = r10;
    L_0x00df:
        throw r2;	 Catch:{ all -> 0x00d9 }
    L_0x00e0:
        r3.cancel(r2);
        throw r9;
    L_0x00e4:
        r11 = (kotlin.Result.Failure) r11;
        r9 = r11.exception;
        throw r9;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.toCollection(kotlinx.coroutines.channels.ReceiveChannel, java.util.Collection, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Nullable
    public static final <E> Object toList(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Continuation<? super List<? extends E>> continuation) {
        return ChannelsKt.toMutableList(receiveChannel, continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <K, V> Object toMap(@NotNull ReceiveChannel<? extends Pair<? extends K, ? extends V>> receiveChannel, @NotNull Continuation<? super Map<K, ? extends V>> continuation) {
        return ChannelsKt.toMap(receiveChannel, new LinkedHashMap(), continuation);
    }

    @org.jetbrains.annotations.Nullable
    @kotlinx.coroutines.ObsoleteCoroutinesApi
    public static final <K, V, M extends java.util.Map<? super K, ? super V>> java.lang.Object toMap(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends kotlin.Pair<? extends K, ? extends V>> r9, @org.jetbrains.annotations.NotNull M r10, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super M> r11) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:37)
	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:61)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1603198149.run(Unknown Source)
*/
        /*
        r0 = r11 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$toMap$2;
        if (r0 == 0) goto L_0x0014;
    L_0x0004:
        r0 = r11;
        r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$toMap$2) r0;
        r1 = r0.label;
        r2 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r1 = r1 & r2;
        if (r1 == 0) goto L_0x0014;
    L_0x000e:
        r11 = r0.label;
        r11 = r11 - r2;
        r0.label = r11;
        goto L_0x0019;
    L_0x0014:
        r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$toMap$2;
        r0.<init>(r11);
    L_0x0019:
        r11 = r0.result;
        r1 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        r2 = r0.label;
        switch(r2) {
            case 0: goto L_0x0086;
            case 1: goto L_0x0058;
            case 2: goto L_0x002c;
            default: goto L_0x0024;
        };
    L_0x0024:
        r9 = new java.lang.IllegalStateException;
        r10 = "call to 'resume' before 'invoke' with coroutine";
        r9.<init>(r10);
        throw r9;
    L_0x002c:
        r9 = r0.L$6;
        r9 = (kotlinx.coroutines.channels.ChannelIterator) r9;
        r10 = r0.L$5;
        r10 = (kotlinx.coroutines.channels.ReceiveChannel) r10;
        r2 = r0.L$4;
        r2 = (java.lang.Throwable) r2;
        r3 = r0.L$3;
        r3 = (kotlinx.coroutines.channels.ReceiveChannel) r3;
        r4 = r0.L$2;
        r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4;
        r5 = r0.L$1;
        r5 = (java.util.Map) r5;
        r6 = r0.L$0;
        r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6;
        r7 = r11 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x0081, all -> 0x007e }
        if (r7 != 0) goto L_0x0053;	 Catch:{ Throwable -> 0x0081, all -> 0x007e }
    L_0x004c:
        r8 = r10;	 Catch:{ Throwable -> 0x0081, all -> 0x007e }
        r10 = r9;	 Catch:{ Throwable -> 0x0081, all -> 0x007e }
        r9 = r3;	 Catch:{ Throwable -> 0x0081, all -> 0x007e }
        r3 = r1;	 Catch:{ Throwable -> 0x0081, all -> 0x007e }
        r1 = r8;	 Catch:{ Throwable -> 0x0081, all -> 0x007e }
        goto L_0x00d2;	 Catch:{ Throwable -> 0x0081, all -> 0x007e }
    L_0x0053:
        r11 = (kotlin.Result.Failure) r11;	 Catch:{ Throwable -> 0x0081, all -> 0x007e }
        r9 = r11.exception;	 Catch:{ Throwable -> 0x0081, all -> 0x007e }
        throw r9;	 Catch:{ Throwable -> 0x0081, all -> 0x007e }
    L_0x0058:
        r9 = r0.L$6;
        r9 = (kotlinx.coroutines.channels.ChannelIterator) r9;
        r10 = r0.L$5;
        r10 = (kotlinx.coroutines.channels.ReceiveChannel) r10;
        r2 = r0.L$4;
        r2 = (java.lang.Throwable) r2;
        r3 = r0.L$3;
        r3 = (kotlinx.coroutines.channels.ReceiveChannel) r3;
        r4 = r0.L$2;
        r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4;
        r5 = r0.L$1;
        r5 = (java.util.Map) r5;
        r6 = r0.L$0;
        r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6;
        r7 = r11 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x0081, all -> 0x007e }
        if (r7 != 0) goto L_0x0079;	 Catch:{ Throwable -> 0x0081, all -> 0x007e }
    L_0x0078:
        goto L_0x00b2;	 Catch:{ Throwable -> 0x0081, all -> 0x007e }
    L_0x0079:
        r11 = (kotlin.Result.Failure) r11;	 Catch:{ Throwable -> 0x0081, all -> 0x007e }
        r9 = r11.exception;	 Catch:{ Throwable -> 0x0081, all -> 0x007e }
        throw r9;	 Catch:{ Throwable -> 0x0081, all -> 0x007e }
    L_0x007e:
        r9 = move-exception;
        goto L_0x00f0;
    L_0x0081:
        r9 = move-exception;
        r2 = r9;
        r9 = r3;
        goto L_0x00ef;
    L_0x0086:
        r2 = r11 instanceof kotlin.Result.Failure;
        if (r2 != 0) goto L_0x00f4;
    L_0x008a:
        r11 = 0;
        r2 = r11;
        r2 = (java.lang.Throwable) r2;
        r11 = r9.iterator();	 Catch:{ Throwable -> 0x00ed }
        r4 = r9;	 Catch:{ Throwable -> 0x00ed }
        r6 = r4;	 Catch:{ Throwable -> 0x00ed }
        r5 = r10;	 Catch:{ Throwable -> 0x00ed }
        r10 = r6;	 Catch:{ Throwable -> 0x00ed }
    L_0x0096:
        r0.L$0 = r6;	 Catch:{ Throwable -> 0x00ed }
        r0.L$1 = r5;	 Catch:{ Throwable -> 0x00ed }
        r0.L$2 = r4;	 Catch:{ Throwable -> 0x00ed }
        r0.L$3 = r9;	 Catch:{ Throwable -> 0x00ed }
        r0.L$4 = r2;	 Catch:{ Throwable -> 0x00ed }
        r0.L$5 = r10;	 Catch:{ Throwable -> 0x00ed }
        r0.L$6 = r11;	 Catch:{ Throwable -> 0x00ed }
        r3 = 1;	 Catch:{ Throwable -> 0x00ed }
        r0.label = r3;	 Catch:{ Throwable -> 0x00ed }
        r3 = r11.hasNext(r0);	 Catch:{ Throwable -> 0x00ed }
        if (r3 != r1) goto L_0x00ae;
    L_0x00ad:
        return r1;
    L_0x00ae:
        r8 = r3;
        r3 = r9;
        r9 = r11;
        r11 = r8;
    L_0x00b2:
        r11 = (java.lang.Boolean) r11;	 Catch:{ Throwable -> 0x0081, all -> 0x007e }
        r11 = r11.booleanValue();	 Catch:{ Throwable -> 0x0081, all -> 0x007e }
        if (r11 == 0) goto L_0x00e3;	 Catch:{ Throwable -> 0x0081, all -> 0x007e }
    L_0x00ba:
        r0.L$0 = r6;	 Catch:{ Throwable -> 0x0081, all -> 0x007e }
        r0.L$1 = r5;	 Catch:{ Throwable -> 0x0081, all -> 0x007e }
        r0.L$2 = r4;	 Catch:{ Throwable -> 0x0081, all -> 0x007e }
        r0.L$3 = r3;	 Catch:{ Throwable -> 0x0081, all -> 0x007e }
        r0.L$4 = r2;	 Catch:{ Throwable -> 0x0081, all -> 0x007e }
        r0.L$5 = r10;	 Catch:{ Throwable -> 0x0081, all -> 0x007e }
        r0.L$6 = r9;	 Catch:{ Throwable -> 0x0081, all -> 0x007e }
        r11 = 2;	 Catch:{ Throwable -> 0x0081, all -> 0x007e }
        r0.label = r11;	 Catch:{ Throwable -> 0x0081, all -> 0x007e }
        r11 = r9.next(r0);	 Catch:{ Throwable -> 0x0081, all -> 0x007e }
        if (r11 != r1) goto L_0x004c;
    L_0x00d1:
        return r1;
    L_0x00d2:
        r11 = (kotlin.Pair) r11;	 Catch:{ Throwable -> 0x00ed }
        r7 = r11.getFirst();	 Catch:{ Throwable -> 0x00ed }
        r11 = r11.getSecond();	 Catch:{ Throwable -> 0x00ed }
        r5.put(r7, r11);	 Catch:{ Throwable -> 0x00ed }
        r11 = r10;
        r10 = r1;
        r1 = r3;
        goto L_0x0096;
    L_0x00e3:
        r9 = kotlin.Unit.INSTANCE;	 Catch:{ Throwable -> 0x0081, all -> 0x007e }
        r3.cancel(r2);
        return r5;
    L_0x00e9:
        r10 = move-exception;
        r3 = r9;
        r9 = r10;
        goto L_0x00f0;
    L_0x00ed:
        r10 = move-exception;
        r2 = r10;
    L_0x00ef:
        throw r2;	 Catch:{ all -> 0x00e9 }
    L_0x00f0:
        r3.cancel(r2);
        throw r9;
    L_0x00f4:
        r11 = (kotlin.Result.Failure) r11;
        r9 = r11.exception;
        throw r9;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.toMap(kotlinx.coroutines.channels.ReceiveChannel, java.util.Map, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E> Object toMutableList(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Continuation<? super List<E>> continuation) {
        return ChannelsKt.toCollection(receiveChannel, new ArrayList(), continuation);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E> Object toSet(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Continuation<? super Set<? extends E>> continuation) {
        return ChannelsKt.toMutableSet(receiveChannel, continuation);
    }

    @NotNull
    @ObsoleteCoroutinesApi
    public static /* synthetic */ ReceiveChannel flatMap$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return ChannelsKt.flatMap(receiveChannel, coroutineContext, function2);
    }

    @NotNull
    @ObsoleteCoroutinesApi
    public static final <E, R> ReceiveChannel<R> flatMap(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull CoroutineContext coroutineContext, @NotNull Function2<? super E, ? super Continuation<? super ReceiveChannel<? extends R>>, ? extends Object> function2) {
        Intrinsics.checkParameterIsNotNull(receiveChannel, "receiver$0");
        Intrinsics.checkParameterIsNotNull(coroutineContext, "context");
        Intrinsics.checkParameterIsNotNull(function2, "transform");
        return ProduceKt.produce$default(GlobalScope.INSTANCE, coroutineContext, 0, ChannelsKt.consumes(receiveChannel), new ChannelsKt__Channels_commonKt$flatMap$1(receiveChannel, function2, null), 2, null);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @org.jetbrains.annotations.Nullable
    @kotlinx.coroutines.ObsoleteCoroutinesApi
    public static final <E, K> java.lang.Object groupBy(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r16, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super E, ? extends K> r17, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.util.Map<K, ? extends java.util.List<? extends E>>> r18) {
        /*
        r0 = r18;
        r1 = r0 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$groupBy$1;
        if (r1 == 0) goto L_0x0016;
    L_0x0006:
        r1 = r0;
        r1 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$groupBy$1) r1;
        r2 = r1.label;
        r3 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r2 = r2 & r3;
        if (r2 == 0) goto L_0x0016;
    L_0x0010:
        r0 = r1.label;
        r0 = r0 - r3;
        r1.label = r0;
        goto L_0x001b;
    L_0x0016:
        r1 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$groupBy$1;
        r1.<init>(r0);
    L_0x001b:
        r0 = r1.result;
        r2 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        r3 = r1.label;
        r4 = 1;
        switch(r3) {
            case 0: goto L_0x009b;
            case 1: goto L_0x0061;
            case 2: goto L_0x002f;
            default: goto L_0x0027;
        };
    L_0x0027:
        r0 = new java.lang.IllegalStateException;
        r1 = "call to 'resume' before 'invoke' with coroutine";
        r0.<init>(r1);
        throw r0;
    L_0x002f:
        r3 = r1.L$8;
        r3 = (kotlinx.coroutines.channels.ChannelIterator) r3;
        r5 = r1.L$7;
        r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5;
        r6 = r1.L$6;
        r6 = (java.lang.Throwable) r6;
        r7 = r1.L$5;
        r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7;
        r8 = r1.L$4;
        r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8;
        r9 = r1.L$3;
        r9 = (java.util.Map) r9;
        r10 = r1.L$2;
        r10 = (kotlinx.coroutines.channels.ReceiveChannel) r10;
        r11 = r1.L$1;
        r11 = (kotlin.jvm.functions.Function1) r11;
        r12 = r1.L$0;
        r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12;
        r13 = r0 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x0096, all -> 0x0092 }
        if (r13 != 0) goto L_0x005c;
    L_0x0057:
        r15 = r7;
        r7 = r2;
        r2 = r15;
        goto L_0x00f9;
    L_0x005c:
        r0 = (kotlin.Result.Failure) r0;	 Catch:{ Throwable -> 0x0096, all -> 0x0092 }
        r0 = r0.exception;	 Catch:{ Throwable -> 0x0096, all -> 0x0092 }
        throw r0;	 Catch:{ Throwable -> 0x0096, all -> 0x0092 }
    L_0x0061:
        r3 = r1.L$8;
        r3 = (kotlinx.coroutines.channels.ChannelIterator) r3;
        r5 = r1.L$7;
        r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5;
        r6 = r1.L$6;
        r6 = (java.lang.Throwable) r6;
        r7 = r1.L$5;
        r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7;
        r8 = r1.L$4;
        r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8;
        r9 = r1.L$3;
        r9 = (java.util.Map) r9;
        r10 = r1.L$2;
        r10 = (kotlinx.coroutines.channels.ReceiveChannel) r10;
        r11 = r1.L$1;
        r11 = (kotlin.jvm.functions.Function1) r11;
        r12 = r1.L$0;
        r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12;
        r13 = r0 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x0096, all -> 0x0092 }
        if (r13 != 0) goto L_0x008d;
    L_0x0089:
        r15 = r7;
        r7 = r2;
        r2 = r15;
        goto L_0x00d5;
    L_0x008d:
        r0 = (kotlin.Result.Failure) r0;	 Catch:{ Throwable -> 0x0096, all -> 0x0092 }
        r0 = r0.exception;	 Catch:{ Throwable -> 0x0096, all -> 0x0092 }
        throw r0;	 Catch:{ Throwable -> 0x0096, all -> 0x0092 }
    L_0x0092:
        r0 = move-exception;
        r2 = r7;
        goto L_0x012a;
    L_0x0096:
        r0 = move-exception;
        r6 = r0;
        r2 = r7;
        goto L_0x0128;
    L_0x009b:
        r3 = r0 instanceof kotlin.Result.Failure;
        if (r3 != 0) goto L_0x0134;
    L_0x009f:
        r0 = new java.util.LinkedHashMap;
        r0.<init>();
        r0 = (java.util.Map) r0;
        r3 = 0;
        r6 = r3;
        r6 = (java.lang.Throwable) r6;
        r3 = r16.iterator();	 Catch:{ Throwable -> 0x0124, all -> 0x0120 }
        r5 = r16;
        r8 = r5;
        r10 = r8;
        r12 = r10;
        r11 = r17;
        r7 = r2;
        r2 = r12;
    L_0x00b7:
        r1.L$0 = r12;	 Catch:{ Throwable -> 0x011e }
        r1.L$1 = r11;	 Catch:{ Throwable -> 0x011e }
        r1.L$2 = r10;	 Catch:{ Throwable -> 0x011e }
        r1.L$3 = r0;	 Catch:{ Throwable -> 0x011e }
        r1.L$4 = r8;	 Catch:{ Throwable -> 0x011e }
        r1.L$5 = r2;	 Catch:{ Throwable -> 0x011e }
        r1.L$6 = r6;	 Catch:{ Throwable -> 0x011e }
        r1.L$7 = r5;	 Catch:{ Throwable -> 0x011e }
        r1.L$8 = r3;	 Catch:{ Throwable -> 0x011e }
        r1.label = r4;	 Catch:{ Throwable -> 0x011e }
        r9 = r3.hasNext(r1);	 Catch:{ Throwable -> 0x011e }
        if (r9 != r7) goto L_0x00d2;
    L_0x00d1:
        return r7;
    L_0x00d2:
        r15 = r9;
        r9 = r0;
        r0 = r15;
    L_0x00d5:
        r0 = (java.lang.Boolean) r0;	 Catch:{ Throwable -> 0x011e }
        r0 = r0.booleanValue();	 Catch:{ Throwable -> 0x011e }
        if (r0 == 0) goto L_0x0112;
    L_0x00dd:
        r1.L$0 = r12;	 Catch:{ Throwable -> 0x011e }
        r1.L$1 = r11;	 Catch:{ Throwable -> 0x011e }
        r1.L$2 = r10;	 Catch:{ Throwable -> 0x011e }
        r1.L$3 = r9;	 Catch:{ Throwable -> 0x011e }
        r1.L$4 = r8;	 Catch:{ Throwable -> 0x011e }
        r1.L$5 = r2;	 Catch:{ Throwable -> 0x011e }
        r1.L$6 = r6;	 Catch:{ Throwable -> 0x011e }
        r1.L$7 = r5;	 Catch:{ Throwable -> 0x011e }
        r1.L$8 = r3;	 Catch:{ Throwable -> 0x011e }
        r0 = 2;
        r1.label = r0;	 Catch:{ Throwable -> 0x011e }
        r0 = r3.next(r1);	 Catch:{ Throwable -> 0x011e }
        if (r0 != r7) goto L_0x00f9;
    L_0x00f8:
        return r7;
    L_0x00f9:
        r13 = r11.invoke(r0);	 Catch:{ Throwable -> 0x011e }
        r14 = r9.get(r13);	 Catch:{ Throwable -> 0x011e }
        if (r14 != 0) goto L_0x010b;
    L_0x0103:
        r14 = new java.util.ArrayList;	 Catch:{ Throwable -> 0x011e }
        r14.<init>();	 Catch:{ Throwable -> 0x011e }
        r9.put(r13, r14);	 Catch:{ Throwable -> 0x011e }
    L_0x010b:
        r14 = (java.util.List) r14;	 Catch:{ Throwable -> 0x011e }
        r14.add(r0);	 Catch:{ Throwable -> 0x011e }
        r0 = r9;
        goto L_0x00b7;
    L_0x0112:
        r0 = kotlin.Unit.INSTANCE;	 Catch:{ Throwable -> 0x011e }
        kotlin.jvm.internal.InlineMarker.finallyStart(r4);
        r2.cancel(r6);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r4);
        return r9;
    L_0x011e:
        r0 = move-exception;
        goto L_0x0127;
    L_0x0120:
        r0 = move-exception;
        r2 = r16;
        goto L_0x012a;
    L_0x0124:
        r0 = move-exception;
        r2 = r16;
    L_0x0127:
        r6 = r0;
    L_0x0128:
        throw r6;	 Catch:{ all -> 0x0129 }
    L_0x0129:
        r0 = move-exception;
    L_0x012a:
        kotlin.jvm.internal.InlineMarker.finallyStart(r4);
        r2.cancel(r6);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r4);
        throw r0;
    L_0x0134:
        r0 = (kotlin.Result.Failure) r0;
        r0 = r0.exception;
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.groupBy(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Nullable
    @ObsoleteCoroutinesApi
    private static final Object groupBy$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Function1 function1, @NotNull Continuation continuation) {
        Function1 function12;
        Map linkedHashMap = new LinkedHashMap();
        Throwable th = (Throwable) null;
        try {
            ChannelIterator it = receiveChannel.iterator();
            while (true) {
                InlineMarker.mark(0);
                Object hasNext = it.hasNext(continuation);
                InlineMarker.mark(1);
                if (((Boolean) hasNext).booleanValue()) {
                    InlineMarker.mark(0);
                    Object next = it.next(continuation);
                    InlineMarker.mark(1);
                    hasNext = function1.invoke(next);
                    ArrayList arrayList = linkedHashMap.get(hasNext);
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                        linkedHashMap.put(hasNext, arrayList);
                    }
                    arrayList.add(next);
                } else {
                    function1 = Unit.INSTANCE;
                    InlineMarker.finallyStart(1);
                    receiveChannel.cancel(th);
                    InlineMarker.finallyEnd(1);
                    return linkedHashMap;
                }
            }
        } catch (Throwable th2) {
            InlineMarker.finallyStart(1);
            receiveChannel.cancel(function12);
            InlineMarker.finallyEnd(1);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @org.jetbrains.annotations.Nullable
    @kotlinx.coroutines.ObsoleteCoroutinesApi
    public static final <E, K, V> java.lang.Object groupBy(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r17, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super E, ? extends K> r18, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super E, ? extends V> r19, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.util.Map<K, ? extends java.util.List<? extends V>>> r20) {
        /*
        r0 = r20;
        r1 = r0 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$groupBy$2;
        if (r1 == 0) goto L_0x0016;
    L_0x0006:
        r1 = r0;
        r1 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$groupBy$2) r1;
        r2 = r1.label;
        r3 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r2 = r2 & r3;
        if (r2 == 0) goto L_0x0016;
    L_0x0010:
        r0 = r1.label;
        r0 = r0 - r3;
        r1.label = r0;
        goto L_0x001b;
    L_0x0016:
        r1 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$groupBy$2;
        r1.<init>(r0);
    L_0x001b:
        r0 = r1.result;
        r2 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        r3 = r1.label;
        r4 = 1;
        switch(r3) {
            case 0: goto L_0x00a7;
            case 1: goto L_0x0067;
            case 2: goto L_0x002f;
            default: goto L_0x0027;
        };
    L_0x0027:
        r0 = new java.lang.IllegalStateException;
        r1 = "call to 'resume' before 'invoke' with coroutine";
        r0.<init>(r1);
        throw r0;
    L_0x002f:
        r3 = r1.L$9;
        r3 = (kotlinx.coroutines.channels.ChannelIterator) r3;
        r5 = r1.L$8;
        r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5;
        r6 = r1.L$7;
        r6 = (java.lang.Throwable) r6;
        r7 = r1.L$6;
        r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7;
        r8 = r1.L$5;
        r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8;
        r9 = r1.L$4;
        r9 = (java.util.Map) r9;
        r10 = r1.L$3;
        r10 = (kotlinx.coroutines.channels.ReceiveChannel) r10;
        r11 = r1.L$2;
        r11 = (kotlin.jvm.functions.Function1) r11;
        r12 = r1.L$1;
        r12 = (kotlin.jvm.functions.Function1) r12;
        r13 = r1.L$0;
        r13 = (kotlinx.coroutines.channels.ReceiveChannel) r13;
        r14 = r0 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x00a2, all -> 0x009e }
        if (r14 != 0) goto L_0x0062;
    L_0x005b:
        r16 = r7;
        r7 = r2;
        r2 = r16;
        goto L_0x010d;
    L_0x0062:
        r0 = (kotlin.Result.Failure) r0;	 Catch:{ Throwable -> 0x00a2, all -> 0x009e }
        r0 = r0.exception;	 Catch:{ Throwable -> 0x00a2, all -> 0x009e }
        throw r0;	 Catch:{ Throwable -> 0x00a2, all -> 0x009e }
    L_0x0067:
        r3 = r1.L$9;
        r3 = (kotlinx.coroutines.channels.ChannelIterator) r3;
        r5 = r1.L$8;
        r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5;
        r6 = r1.L$7;
        r6 = (java.lang.Throwable) r6;
        r7 = r1.L$6;
        r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7;
        r8 = r1.L$5;
        r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8;
        r9 = r1.L$4;
        r9 = (java.util.Map) r9;
        r10 = r1.L$3;
        r10 = (kotlinx.coroutines.channels.ReceiveChannel) r10;
        r11 = r1.L$2;
        r11 = (kotlin.jvm.functions.Function1) r11;
        r12 = r1.L$1;
        r12 = (kotlin.jvm.functions.Function1) r12;
        r13 = r1.L$0;
        r13 = (kotlinx.coroutines.channels.ReceiveChannel) r13;
        r14 = r0 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x00a2, all -> 0x009e }
        if (r14 != 0) goto L_0x0099;
    L_0x0093:
        r16 = r7;
        r7 = r2;
        r2 = r16;
        goto L_0x00e7;
    L_0x0099:
        r0 = (kotlin.Result.Failure) r0;	 Catch:{ Throwable -> 0x00a2, all -> 0x009e }
        r0 = r0.exception;	 Catch:{ Throwable -> 0x00a2, all -> 0x009e }
        throw r0;	 Catch:{ Throwable -> 0x00a2, all -> 0x009e }
    L_0x009e:
        r0 = move-exception;
        r2 = r7;
        goto L_0x0142;
    L_0x00a2:
        r0 = move-exception;
        r6 = r0;
        r2 = r7;
        goto L_0x0140;
    L_0x00a7:
        r3 = r0 instanceof kotlin.Result.Failure;
        if (r3 != 0) goto L_0x014c;
    L_0x00ab:
        r0 = new java.util.LinkedHashMap;
        r0.<init>();
        r0 = (java.util.Map) r0;
        r3 = 0;
        r6 = r3;
        r6 = (java.lang.Throwable) r6;
        r3 = r17.iterator();	 Catch:{ Throwable -> 0x013c, all -> 0x0138 }
        r5 = r17;
        r8 = r5;
        r10 = r8;
        r13 = r10;
        r12 = r18;
        r11 = r19;
        r7 = r2;
        r2 = r13;
    L_0x00c5:
        r1.L$0 = r13;	 Catch:{ Throwable -> 0x0136 }
        r1.L$1 = r12;	 Catch:{ Throwable -> 0x0136 }
        r1.L$2 = r11;	 Catch:{ Throwable -> 0x0136 }
        r1.L$3 = r10;	 Catch:{ Throwable -> 0x0136 }
        r1.L$4 = r0;	 Catch:{ Throwable -> 0x0136 }
        r1.L$5 = r8;	 Catch:{ Throwable -> 0x0136 }
        r1.L$6 = r2;	 Catch:{ Throwable -> 0x0136 }
        r1.L$7 = r6;	 Catch:{ Throwable -> 0x0136 }
        r1.L$8 = r5;	 Catch:{ Throwable -> 0x0136 }
        r1.L$9 = r3;	 Catch:{ Throwable -> 0x0136 }
        r1.label = r4;	 Catch:{ Throwable -> 0x0136 }
        r9 = r3.hasNext(r1);	 Catch:{ Throwable -> 0x0136 }
        if (r9 != r7) goto L_0x00e2;
    L_0x00e1:
        return r7;
    L_0x00e2:
        r16 = r9;
        r9 = r0;
        r0 = r16;
    L_0x00e7:
        r0 = (java.lang.Boolean) r0;	 Catch:{ Throwable -> 0x0136 }
        r0 = r0.booleanValue();	 Catch:{ Throwable -> 0x0136 }
        if (r0 == 0) goto L_0x012a;
    L_0x00ef:
        r1.L$0 = r13;	 Catch:{ Throwable -> 0x0136 }
        r1.L$1 = r12;	 Catch:{ Throwable -> 0x0136 }
        r1.L$2 = r11;	 Catch:{ Throwable -> 0x0136 }
        r1.L$3 = r10;	 Catch:{ Throwable -> 0x0136 }
        r1.L$4 = r9;	 Catch:{ Throwable -> 0x0136 }
        r1.L$5 = r8;	 Catch:{ Throwable -> 0x0136 }
        r1.L$6 = r2;	 Catch:{ Throwable -> 0x0136 }
        r1.L$7 = r6;	 Catch:{ Throwable -> 0x0136 }
        r1.L$8 = r5;	 Catch:{ Throwable -> 0x0136 }
        r1.L$9 = r3;	 Catch:{ Throwable -> 0x0136 }
        r0 = 2;
        r1.label = r0;	 Catch:{ Throwable -> 0x0136 }
        r0 = r3.next(r1);	 Catch:{ Throwable -> 0x0136 }
        if (r0 != r7) goto L_0x010d;
    L_0x010c:
        return r7;
    L_0x010d:
        r14 = r12.invoke(r0);	 Catch:{ Throwable -> 0x0136 }
        r15 = r9.get(r14);	 Catch:{ Throwable -> 0x0136 }
        if (r15 != 0) goto L_0x011f;
    L_0x0117:
        r15 = new java.util.ArrayList;	 Catch:{ Throwable -> 0x0136 }
        r15.<init>();	 Catch:{ Throwable -> 0x0136 }
        r9.put(r14, r15);	 Catch:{ Throwable -> 0x0136 }
    L_0x011f:
        r15 = (java.util.List) r15;	 Catch:{ Throwable -> 0x0136 }
        r0 = r11.invoke(r0);	 Catch:{ Throwable -> 0x0136 }
        r15.add(r0);	 Catch:{ Throwable -> 0x0136 }
        r0 = r9;
        goto L_0x00c5;
    L_0x012a:
        r0 = kotlin.Unit.INSTANCE;	 Catch:{ Throwable -> 0x0136 }
        kotlin.jvm.internal.InlineMarker.finallyStart(r4);
        r2.cancel(r6);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r4);
        return r9;
    L_0x0136:
        r0 = move-exception;
        goto L_0x013f;
    L_0x0138:
        r0 = move-exception;
        r2 = r17;
        goto L_0x0142;
    L_0x013c:
        r0 = move-exception;
        r2 = r17;
    L_0x013f:
        r6 = r0;
    L_0x0140:
        throw r6;	 Catch:{ all -> 0x0141 }
    L_0x0141:
        r0 = move-exception;
    L_0x0142:
        kotlin.jvm.internal.InlineMarker.finallyStart(r4);
        r2.cancel(r6);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r4);
        throw r0;
    L_0x014c:
        r0 = (kotlin.Result.Failure) r0;
        r0 = r0.exception;
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.groupBy(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Nullable
    @ObsoleteCoroutinesApi
    private static final Object groupBy$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Function1 function1, @NotNull Function1 function12, @NotNull Continuation continuation) {
        Function1 function13;
        Map linkedHashMap = new LinkedHashMap();
        Throwable th = (Throwable) null;
        try {
            ChannelIterator it = receiveChannel.iterator();
            while (true) {
                InlineMarker.mark(0);
                Object hasNext = it.hasNext(continuation);
                InlineMarker.mark(1);
                if (((Boolean) hasNext).booleanValue()) {
                    InlineMarker.mark(0);
                    Object next = it.next(continuation);
                    InlineMarker.mark(1);
                    hasNext = function1.invoke(next);
                    ArrayList arrayList = linkedHashMap.get(hasNext);
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                        linkedHashMap.put(hasNext, arrayList);
                    }
                    arrayList.add(function12.invoke(next));
                } else {
                    function1 = Unit.INSTANCE;
                    InlineMarker.finallyStart(1);
                    receiveChannel.cancel(th);
                    InlineMarker.finallyEnd(1);
                    return linkedHashMap;
                }
            }
        } catch (Throwable th2) {
            InlineMarker.finallyStart(1);
            receiveChannel.cancel(function13);
            InlineMarker.finallyEnd(1);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @org.jetbrains.annotations.Nullable
    @kotlinx.coroutines.ObsoleteCoroutinesApi
    public static final <E, K, M extends java.util.Map<? super K, java.util.List<E>>> java.lang.Object groupByTo(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r11, @org.jetbrains.annotations.NotNull M r12, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super E, ? extends K> r13, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super M> r14) {
        /*
        r0 = r14 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$groupByTo$1;
        if (r0 == 0) goto L_0x0014;
    L_0x0004:
        r0 = r14;
        r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$groupByTo$1) r0;
        r1 = r0.label;
        r2 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r1 = r1 & r2;
        if (r1 == 0) goto L_0x0014;
    L_0x000e:
        r14 = r0.label;
        r14 = r14 - r2;
        r0.label = r14;
        goto L_0x0019;
    L_0x0014:
        r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$groupByTo$1;
        r0.<init>(r14);
    L_0x0019:
        r14 = r0.result;
        r1 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        r2 = r0.label;
        r3 = 1;
        switch(r2) {
            case 0: goto L_0x008d;
            case 1: goto L_0x005b;
            case 2: goto L_0x002d;
            default: goto L_0x0025;
        };
    L_0x0025:
        r11 = new java.lang.IllegalStateException;
        r12 = "call to 'resume' before 'invoke' with coroutine";
        r11.<init>(r12);
        throw r11;
    L_0x002d:
        r11 = r0.L$7;
        r11 = (kotlinx.coroutines.channels.ChannelIterator) r11;
        r12 = r0.L$6;
        r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12;
        r13 = r0.L$5;
        r13 = (java.lang.Throwable) r13;
        r2 = r0.L$4;
        r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2;
        r4 = r0.L$3;
        r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4;
        r5 = r0.L$2;
        r5 = (kotlin.jvm.functions.Function1) r5;
        r6 = r0.L$1;
        r6 = (java.util.Map) r6;
        r7 = r0.L$0;
        r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7;
        r8 = r14 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        if (r8 != 0) goto L_0x0056;
    L_0x0051:
        r10 = r2;
        r2 = r11;
        r11 = r10;
        goto L_0x00dc;
    L_0x0056:
        r14 = (kotlin.Result.Failure) r14;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r11 = r14.exception;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        throw r11;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
    L_0x005b:
        r11 = r0.L$7;
        r11 = (kotlinx.coroutines.channels.ChannelIterator) r11;
        r12 = r0.L$6;
        r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12;
        r13 = r0.L$5;
        r13 = (java.lang.Throwable) r13;
        r2 = r0.L$4;
        r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2;
        r4 = r0.L$3;
        r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4;
        r5 = r0.L$2;
        r5 = (kotlin.jvm.functions.Function1) r5;
        r6 = r0.L$1;
        r6 = (java.util.Map) r6;
        r7 = r0.L$0;
        r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7;
        r8 = r14 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        if (r8 != 0) goto L_0x0080;
    L_0x007f:
        goto L_0x00ba;
    L_0x0080:
        r14 = (kotlin.Result.Failure) r14;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r11 = r14.exception;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        throw r11;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
    L_0x0085:
        r11 = move-exception;
        goto L_0x010b;
    L_0x0088:
        r11 = move-exception;
        r13 = r11;
        r11 = r2;
        goto L_0x0107;
    L_0x008d:
        r2 = r14 instanceof kotlin.Result.Failure;
        if (r2 != 0) goto L_0x0115;
    L_0x0091:
        r14 = 0;
        r14 = (java.lang.Throwable) r14;
        r2 = r11.iterator();	 Catch:{ Throwable -> 0x0105, all -> 0x0100 }
        r4 = r11;
        r7 = r4;
        r6 = r12;
        r5 = r13;
        r13 = r14;
        r12 = r7;
    L_0x009e:
        r0.L$0 = r7;	 Catch:{ Throwable -> 0x0105 }
        r0.L$1 = r6;	 Catch:{ Throwable -> 0x0105 }
        r0.L$2 = r5;	 Catch:{ Throwable -> 0x0105 }
        r0.L$3 = r4;	 Catch:{ Throwable -> 0x0105 }
        r0.L$4 = r11;	 Catch:{ Throwable -> 0x0105 }
        r0.L$5 = r13;	 Catch:{ Throwable -> 0x0105 }
        r0.L$6 = r12;	 Catch:{ Throwable -> 0x0105 }
        r0.L$7 = r2;	 Catch:{ Throwable -> 0x0105 }
        r0.label = r3;	 Catch:{ Throwable -> 0x0105 }
        r14 = r2.hasNext(r0);	 Catch:{ Throwable -> 0x0105 }
        if (r14 != r1) goto L_0x00b7;
    L_0x00b6:
        return r1;
    L_0x00b7:
        r10 = r2;
        r2 = r11;
        r11 = r10;
    L_0x00ba:
        r14 = (java.lang.Boolean) r14;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r14 = r14.booleanValue();	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        if (r14 == 0) goto L_0x00f4;
    L_0x00c2:
        r0.L$0 = r7;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r0.L$1 = r6;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r0.L$2 = r5;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r0.L$3 = r4;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r0.L$4 = r2;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r0.L$5 = r13;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r0.L$6 = r12;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r0.L$7 = r11;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r14 = 2;
        r0.label = r14;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r14 = r11.next(r0);	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        if (r14 != r1) goto L_0x0051;
    L_0x00db:
        return r1;
    L_0x00dc:
        r8 = r5.invoke(r14);	 Catch:{ Throwable -> 0x0105 }
        r9 = r6.get(r8);	 Catch:{ Throwable -> 0x0105 }
        if (r9 != 0) goto L_0x00ee;
    L_0x00e6:
        r9 = new java.util.ArrayList;	 Catch:{ Throwable -> 0x0105 }
        r9.<init>();	 Catch:{ Throwable -> 0x0105 }
        r6.put(r8, r9);	 Catch:{ Throwable -> 0x0105 }
    L_0x00ee:
        r9 = (java.util.List) r9;	 Catch:{ Throwable -> 0x0105 }
        r9.add(r14);	 Catch:{ Throwable -> 0x0105 }
        goto L_0x009e;
    L_0x00f4:
        r11 = kotlin.Unit.INSTANCE;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        kotlin.jvm.internal.InlineMarker.finallyStart(r3);
        r2.cancel(r13);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r3);
        return r6;
    L_0x0100:
        r12 = move-exception;
        r2 = r11;
        r11 = r12;
        r13 = r14;
        goto L_0x010b;
    L_0x0105:
        r12 = move-exception;
        r13 = r12;
    L_0x0107:
        throw r13;	 Catch:{ all -> 0x0108 }
    L_0x0108:
        r12 = move-exception;
        r2 = r11;
        r11 = r12;
    L_0x010b:
        kotlin.jvm.internal.InlineMarker.finallyStart(r3);
        r2.cancel(r13);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r3);
        throw r11;
    L_0x0115:
        r14 = (kotlin.Result.Failure) r14;
        r11 = r14.exception;
        throw r11;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.groupByTo(kotlinx.coroutines.channels.ReceiveChannel, java.util.Map, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @org.jetbrains.annotations.Nullable
    @kotlinx.coroutines.ObsoleteCoroutinesApi
    public static final <E, K, V, M extends java.util.Map<? super K, java.util.List<V>>> java.lang.Object groupByTo(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r16, @org.jetbrains.annotations.NotNull M r17, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super E, ? extends K> r18, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super E, ? extends V> r19, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super M> r20) {
        /*
        r0 = r20;
        r1 = r0 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$groupByTo$3;
        if (r1 == 0) goto L_0x0016;
    L_0x0006:
        r1 = r0;
        r1 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$groupByTo$3) r1;
        r2 = r1.label;
        r3 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r2 = r2 & r3;
        if (r2 == 0) goto L_0x0016;
    L_0x0010:
        r0 = r1.label;
        r0 = r0 - r3;
        r1.label = r0;
        goto L_0x001b;
    L_0x0016:
        r1 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$groupByTo$3;
        r1.<init>(r0);
    L_0x001b:
        r0 = r1.result;
        r2 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        r3 = r1.label;
        r4 = 1;
        switch(r3) {
            case 0: goto L_0x009b;
            case 1: goto L_0x0061;
            case 2: goto L_0x002f;
            default: goto L_0x0027;
        };
    L_0x0027:
        r0 = new java.lang.IllegalStateException;
        r1 = "call to 'resume' before 'invoke' with coroutine";
        r0.<init>(r1);
        throw r0;
    L_0x002f:
        r3 = r1.L$8;
        r3 = (kotlinx.coroutines.channels.ChannelIterator) r3;
        r5 = r1.L$7;
        r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5;
        r6 = r1.L$6;
        r6 = (java.lang.Throwable) r6;
        r7 = r1.L$5;
        r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7;
        r8 = r1.L$4;
        r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8;
        r9 = r1.L$3;
        r9 = (kotlin.jvm.functions.Function1) r9;
        r10 = r1.L$2;
        r10 = (kotlin.jvm.functions.Function1) r10;
        r11 = r1.L$1;
        r11 = (java.util.Map) r11;
        r12 = r1.L$0;
        r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12;
        r13 = r0 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x0096, all -> 0x0092 }
        if (r13 != 0) goto L_0x005c;
    L_0x0057:
        r15 = r7;
        r7 = r2;
        r2 = r15;
        goto L_0x00f6;
    L_0x005c:
        r0 = (kotlin.Result.Failure) r0;	 Catch:{ Throwable -> 0x0096, all -> 0x0092 }
        r0 = r0.exception;	 Catch:{ Throwable -> 0x0096, all -> 0x0092 }
        throw r0;	 Catch:{ Throwable -> 0x0096, all -> 0x0092 }
    L_0x0061:
        r3 = r1.L$8;
        r3 = (kotlinx.coroutines.channels.ChannelIterator) r3;
        r5 = r1.L$7;
        r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5;
        r6 = r1.L$6;
        r6 = (java.lang.Throwable) r6;
        r7 = r1.L$5;
        r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7;
        r8 = r1.L$4;
        r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8;
        r9 = r1.L$3;
        r9 = (kotlin.jvm.functions.Function1) r9;
        r10 = r1.L$2;
        r10 = (kotlin.jvm.functions.Function1) r10;
        r11 = r1.L$1;
        r11 = (java.util.Map) r11;
        r12 = r1.L$0;
        r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12;
        r13 = r0 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x0096, all -> 0x0092 }
        if (r13 != 0) goto L_0x008d;
    L_0x0089:
        r15 = r7;
        r7 = r2;
        r2 = r15;
        goto L_0x00d2;
    L_0x008d:
        r0 = (kotlin.Result.Failure) r0;	 Catch:{ Throwable -> 0x0096, all -> 0x0092 }
        r0 = r0.exception;	 Catch:{ Throwable -> 0x0096, all -> 0x0092 }
        throw r0;	 Catch:{ Throwable -> 0x0096, all -> 0x0092 }
    L_0x0092:
        r0 = move-exception;
        r2 = r7;
        goto L_0x012c;
    L_0x0096:
        r0 = move-exception;
        r6 = r0;
        r2 = r7;
        goto L_0x012a;
    L_0x009b:
        r3 = r0 instanceof kotlin.Result.Failure;
        if (r3 != 0) goto L_0x0136;
    L_0x009f:
        r0 = 0;
        r6 = r0;
        r6 = (java.lang.Throwable) r6;
        r0 = r16.iterator();	 Catch:{ Throwable -> 0x0126, all -> 0x0122 }
        r5 = r16;
        r8 = r5;
        r12 = r8;
        r11 = r17;
        r10 = r18;
        r9 = r19;
        r3 = r2;
        r2 = r12;
    L_0x00b3:
        r1.L$0 = r12;	 Catch:{ Throwable -> 0x0120 }
        r1.L$1 = r11;	 Catch:{ Throwable -> 0x0120 }
        r1.L$2 = r10;	 Catch:{ Throwable -> 0x0120 }
        r1.L$3 = r9;	 Catch:{ Throwable -> 0x0120 }
        r1.L$4 = r8;	 Catch:{ Throwable -> 0x0120 }
        r1.L$5 = r2;	 Catch:{ Throwable -> 0x0120 }
        r1.L$6 = r6;	 Catch:{ Throwable -> 0x0120 }
        r1.L$7 = r5;	 Catch:{ Throwable -> 0x0120 }
        r1.L$8 = r0;	 Catch:{ Throwable -> 0x0120 }
        r1.label = r4;	 Catch:{ Throwable -> 0x0120 }
        r7 = r0.hasNext(r1);	 Catch:{ Throwable -> 0x0120 }
        if (r7 != r3) goto L_0x00ce;
    L_0x00cd:
        return r3;
    L_0x00ce:
        r15 = r3;
        r3 = r0;
        r0 = r7;
        r7 = r15;
    L_0x00d2:
        r0 = (java.lang.Boolean) r0;	 Catch:{ Throwable -> 0x0120 }
        r0 = r0.booleanValue();	 Catch:{ Throwable -> 0x0120 }
        if (r0 == 0) goto L_0x0114;
    L_0x00da:
        r1.L$0 = r12;	 Catch:{ Throwable -> 0x0120 }
        r1.L$1 = r11;	 Catch:{ Throwable -> 0x0120 }
        r1.L$2 = r10;	 Catch:{ Throwable -> 0x0120 }
        r1.L$3 = r9;	 Catch:{ Throwable -> 0x0120 }
        r1.L$4 = r8;	 Catch:{ Throwable -> 0x0120 }
        r1.L$5 = r2;	 Catch:{ Throwable -> 0x0120 }
        r1.L$6 = r6;	 Catch:{ Throwable -> 0x0120 }
        r1.L$7 = r5;	 Catch:{ Throwable -> 0x0120 }
        r1.L$8 = r3;	 Catch:{ Throwable -> 0x0120 }
        r0 = 2;
        r1.label = r0;	 Catch:{ Throwable -> 0x0120 }
        r0 = r3.next(r1);	 Catch:{ Throwable -> 0x0120 }
        if (r0 != r7) goto L_0x00f6;
    L_0x00f5:
        return r7;
    L_0x00f6:
        r13 = r10.invoke(r0);	 Catch:{ Throwable -> 0x0120 }
        r14 = r11.get(r13);	 Catch:{ Throwable -> 0x0120 }
        if (r14 != 0) goto L_0x0108;
    L_0x0100:
        r14 = new java.util.ArrayList;	 Catch:{ Throwable -> 0x0120 }
        r14.<init>();	 Catch:{ Throwable -> 0x0120 }
        r11.put(r13, r14);	 Catch:{ Throwable -> 0x0120 }
    L_0x0108:
        r14 = (java.util.List) r14;	 Catch:{ Throwable -> 0x0120 }
        r0 = r9.invoke(r0);	 Catch:{ Throwable -> 0x0120 }
        r14.add(r0);	 Catch:{ Throwable -> 0x0120 }
        r0 = r3;
        r3 = r7;
        goto L_0x00b3;
    L_0x0114:
        r0 = kotlin.Unit.INSTANCE;	 Catch:{ Throwable -> 0x0120 }
        kotlin.jvm.internal.InlineMarker.finallyStart(r4);
        r2.cancel(r6);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r4);
        return r11;
    L_0x0120:
        r0 = move-exception;
        goto L_0x0129;
    L_0x0122:
        r0 = move-exception;
        r2 = r16;
        goto L_0x012c;
    L_0x0126:
        r0 = move-exception;
        r2 = r16;
    L_0x0129:
        r6 = r0;
    L_0x012a:
        throw r6;	 Catch:{ all -> 0x012b }
    L_0x012b:
        r0 = move-exception;
    L_0x012c:
        kotlin.jvm.internal.InlineMarker.finallyStart(r4);
        r2.cancel(r6);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r4);
        throw r0;
    L_0x0136:
        r0 = (kotlin.Result.Failure) r0;
        r0 = r0.exception;
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.groupByTo(kotlinx.coroutines.channels.ReceiveChannel, java.util.Map, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @NotNull
    public static /* synthetic */ ReceiveChannel map$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return ChannelsKt.map(receiveChannel, coroutineContext, function2);
    }

    @NotNull
    public static final <E, R> ReceiveChannel<R> map(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull CoroutineContext coroutineContext, @NotNull Function2<? super E, ? super Continuation<? super R>, ? extends Object> function2) {
        Intrinsics.checkParameterIsNotNull(receiveChannel, "receiver$0");
        Intrinsics.checkParameterIsNotNull(coroutineContext, "context");
        Intrinsics.checkParameterIsNotNull(function2, "transform");
        return ProduceKt.produce$default(GlobalScope.INSTANCE, coroutineContext, 0, ChannelsKt.consumes(receiveChannel), new ChannelsKt__Channels_commonKt$map$1(receiveChannel, function2, null), 2, null);
    }

    @NotNull
    @ObsoleteCoroutinesApi
    public static /* synthetic */ ReceiveChannel mapIndexed$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function3 function3, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return ChannelsKt.mapIndexed(receiveChannel, coroutineContext, function3);
    }

    @NotNull
    @ObsoleteCoroutinesApi
    public static final <E, R> ReceiveChannel<R> mapIndexed(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull CoroutineContext coroutineContext, @NotNull Function3<? super Integer, ? super E, ? super Continuation<? super R>, ? extends Object> function3) {
        Intrinsics.checkParameterIsNotNull(receiveChannel, "receiver$0");
        Intrinsics.checkParameterIsNotNull(coroutineContext, "context");
        Intrinsics.checkParameterIsNotNull(function3, "transform");
        return ProduceKt.produce$default(GlobalScope.INSTANCE, coroutineContext, 0, ChannelsKt.consumes(receiveChannel), new ChannelsKt__Channels_commonKt$mapIndexed$1(receiveChannel, function3, null), 2, null);
    }

    @NotNull
    @ObsoleteCoroutinesApi
    public static /* synthetic */ ReceiveChannel mapIndexedNotNull$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function3 function3, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return ChannelsKt.mapIndexedNotNull(receiveChannel, coroutineContext, function3);
    }

    @NotNull
    @ObsoleteCoroutinesApi
    public static final <E, R> ReceiveChannel<R> mapIndexedNotNull(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull CoroutineContext coroutineContext, @NotNull Function3<? super Integer, ? super E, ? super Continuation<? super R>, ? extends Object> function3) {
        Intrinsics.checkParameterIsNotNull(receiveChannel, "receiver$0");
        Intrinsics.checkParameterIsNotNull(coroutineContext, "context");
        Intrinsics.checkParameterIsNotNull(function3, "transform");
        return ChannelsKt.filterNotNull(ChannelsKt.mapIndexed(receiveChannel, coroutineContext, function3));
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @org.jetbrains.annotations.Nullable
    @kotlinx.coroutines.ObsoleteCoroutinesApi
    public static final <E, R, C extends java.util.Collection<? super R>> java.lang.Object mapIndexedNotNullTo(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r17, @org.jetbrains.annotations.NotNull C r18, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function2<? super java.lang.Integer, ? super E, ? extends R> r19, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super C> r20) {
        /*
        r0 = r20;
        r1 = r0 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$mapIndexedNotNullTo$1;
        if (r1 == 0) goto L_0x0016;
    L_0x0006:
        r1 = r0;
        r1 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$mapIndexedNotNullTo$1) r1;
        r2 = r1.label;
        r3 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r2 = r2 & r3;
        if (r2 == 0) goto L_0x0016;
    L_0x0010:
        r0 = r1.label;
        r0 = r0 - r3;
        r1.label = r0;
        goto L_0x001b;
    L_0x0016:
        r1 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$mapIndexedNotNullTo$1;
        r1.<init>(r0);
    L_0x001b:
        r0 = r1.result;
        r2 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        r3 = r1.label;
        r4 = 1;
        switch(r3) {
            case 0: goto L_0x00a8;
            case 1: goto L_0x0067;
            case 2: goto L_0x002f;
            default: goto L_0x0027;
        };
    L_0x0027:
        r0 = new java.lang.IllegalStateException;
        r1 = "call to 'resume' before 'invoke' with coroutine";
        r0.<init>(r1);
        throw r0;
    L_0x002f:
        r3 = r1.L$9;
        r3 = (kotlinx.coroutines.channels.ChannelIterator) r3;
        r5 = r1.L$8;
        r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5;
        r6 = r1.L$7;
        r6 = (java.lang.Throwable) r6;
        r7 = r1.L$6;
        r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7;
        r8 = r1.L$5;
        r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8;
        r9 = r1.L$4;
        r9 = (kotlin.jvm.internal.Ref.IntRef) r9;
        r10 = r1.L$3;
        r10 = (kotlinx.coroutines.channels.ReceiveChannel) r10;
        r11 = r1.L$2;
        r11 = (kotlin.jvm.functions.Function2) r11;
        r12 = r1.L$1;
        r12 = (java.util.Collection) r12;
        r13 = r1.L$0;
        r13 = (kotlinx.coroutines.channels.ReceiveChannel) r13;
        r14 = r0 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x00a3, all -> 0x009e }
        if (r14 != 0) goto L_0x0062;
    L_0x005b:
        r16 = r7;
        r7 = r2;
        r2 = r16;
        goto L_0x010f;
    L_0x0062:
        r0 = (kotlin.Result.Failure) r0;	 Catch:{ Throwable -> 0x00a3, all -> 0x009e }
        r0 = r0.exception;	 Catch:{ Throwable -> 0x00a3, all -> 0x009e }
        throw r0;	 Catch:{ Throwable -> 0x00a3, all -> 0x009e }
    L_0x0067:
        r3 = r1.L$9;
        r3 = (kotlinx.coroutines.channels.ChannelIterator) r3;
        r5 = r1.L$8;
        r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5;
        r6 = r1.L$7;
        r6 = (java.lang.Throwable) r6;
        r7 = r1.L$6;
        r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7;
        r8 = r1.L$5;
        r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8;
        r9 = r1.L$4;
        r9 = (kotlin.jvm.internal.Ref.IntRef) r9;
        r10 = r1.L$3;
        r10 = (kotlinx.coroutines.channels.ReceiveChannel) r10;
        r11 = r1.L$2;
        r11 = (kotlin.jvm.functions.Function2) r11;
        r12 = r1.L$1;
        r12 = (java.util.Collection) r12;
        r13 = r1.L$0;
        r13 = (kotlinx.coroutines.channels.ReceiveChannel) r13;
        r14 = r0 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x00a3, all -> 0x009e }
        if (r14 != 0) goto L_0x0099;
    L_0x0093:
        r16 = r7;
        r7 = r2;
        r2 = r16;
        goto L_0x00e9;
    L_0x0099:
        r0 = (kotlin.Result.Failure) r0;	 Catch:{ Throwable -> 0x00a3, all -> 0x009e }
        r0 = r0.exception;	 Catch:{ Throwable -> 0x00a3, all -> 0x009e }
        throw r0;	 Catch:{ Throwable -> 0x00a3, all -> 0x009e }
    L_0x009e:
        r0 = move-exception;
        r2 = r7;
    L_0x00a0:
        r1 = 1;
        goto L_0x0152;
    L_0x00a3:
        r0 = move-exception;
        r6 = r0;
        r2 = r7;
        goto L_0x014e;
    L_0x00a8:
        r3 = r0 instanceof kotlin.Result.Failure;
        if (r3 != 0) goto L_0x015c;
    L_0x00ac:
        r0 = new kotlin.jvm.internal.Ref$IntRef;
        r0.<init>();
        r3 = 0;
        r0.element = r3;
        r3 = 0;
        r6 = r3;
        r6 = (java.lang.Throwable) r6;
        r3 = r17.iterator();	 Catch:{ Throwable -> 0x014a, all -> 0x0145 }
        r5 = r17;
        r8 = r5;
        r10 = r8;
        r13 = r10;
        r12 = r18;
        r11 = r19;
        r7 = r2;
        r2 = r13;
    L_0x00c7:
        r1.L$0 = r13;	 Catch:{ Throwable -> 0x0143 }
        r1.L$1 = r12;	 Catch:{ Throwable -> 0x0143 }
        r1.L$2 = r11;	 Catch:{ Throwable -> 0x0143 }
        r1.L$3 = r10;	 Catch:{ Throwable -> 0x0143 }
        r1.L$4 = r0;	 Catch:{ Throwable -> 0x0143 }
        r1.L$5 = r8;	 Catch:{ Throwable -> 0x0143 }
        r1.L$6 = r2;	 Catch:{ Throwable -> 0x0143 }
        r1.L$7 = r6;	 Catch:{ Throwable -> 0x0143 }
        r1.L$8 = r5;	 Catch:{ Throwable -> 0x0143 }
        r1.L$9 = r3;	 Catch:{ Throwable -> 0x0143 }
        r1.label = r4;	 Catch:{ Throwable -> 0x0143 }
        r9 = r3.hasNext(r1);	 Catch:{ Throwable -> 0x0143 }
        if (r9 != r7) goto L_0x00e4;
    L_0x00e3:
        return r7;
    L_0x00e4:
        r16 = r9;
        r9 = r0;
        r0 = r16;
    L_0x00e9:
        r0 = (java.lang.Boolean) r0;	 Catch:{ Throwable -> 0x0143 }
        r0 = r0.booleanValue();	 Catch:{ Throwable -> 0x0143 }
        if (r0 == 0) goto L_0x0136;
    L_0x00f1:
        r1.L$0 = r13;	 Catch:{ Throwable -> 0x0143 }
        r1.L$1 = r12;	 Catch:{ Throwable -> 0x0143 }
        r1.L$2 = r11;	 Catch:{ Throwable -> 0x0143 }
        r1.L$3 = r10;	 Catch:{ Throwable -> 0x0143 }
        r1.L$4 = r9;	 Catch:{ Throwable -> 0x0143 }
        r1.L$5 = r8;	 Catch:{ Throwable -> 0x0143 }
        r1.L$6 = r2;	 Catch:{ Throwable -> 0x0143 }
        r1.L$7 = r6;	 Catch:{ Throwable -> 0x0143 }
        r1.L$8 = r5;	 Catch:{ Throwable -> 0x0143 }
        r1.L$9 = r3;	 Catch:{ Throwable -> 0x0143 }
        r0 = 2;
        r1.label = r0;	 Catch:{ Throwable -> 0x0143 }
        r0 = r3.next(r1);	 Catch:{ Throwable -> 0x0143 }
        if (r0 != r7) goto L_0x010f;
    L_0x010e:
        return r7;
    L_0x010f:
        r14 = new kotlin.collections.IndexedValue;	 Catch:{ Throwable -> 0x0143 }
        r15 = r9.element;	 Catch:{ Throwable -> 0x0143 }
        r4 = r15 + 1;
        r9.element = r4;	 Catch:{ Throwable -> 0x0143 }
        r14.<init>(r15, r0);	 Catch:{ Throwable -> 0x0143 }
        r0 = r14.component1();	 Catch:{ Throwable -> 0x0143 }
        r4 = r14.component2();	 Catch:{ Throwable -> 0x0143 }
        r0 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r0);	 Catch:{ Throwable -> 0x0143 }
        r0 = r11.invoke(r0, r4);	 Catch:{ Throwable -> 0x0143 }
        if (r0 == 0) goto L_0x0133;
    L_0x012c:
        r0 = r12.add(r0);	 Catch:{ Throwable -> 0x0143 }
        kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r0);	 Catch:{ Throwable -> 0x0143 }
    L_0x0133:
        r0 = r9;
        r4 = 1;
        goto L_0x00c7;
    L_0x0136:
        r0 = kotlin.Unit.INSTANCE;	 Catch:{ Throwable -> 0x0143 }
        r1 = 1;
        kotlin.jvm.internal.InlineMarker.finallyStart(r1);
        r2.cancel(r6);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r1);
        return r12;
    L_0x0143:
        r0 = move-exception;
        goto L_0x014d;
    L_0x0145:
        r0 = move-exception;
        r2 = r17;
        goto L_0x00a0;
    L_0x014a:
        r0 = move-exception;
        r2 = r17;
    L_0x014d:
        r6 = r0;
    L_0x014e:
        throw r6;	 Catch:{ all -> 0x014f }
    L_0x014f:
        r0 = move-exception;
        goto L_0x00a0;
    L_0x0152:
        kotlin.jvm.internal.InlineMarker.finallyStart(r1);
        r2.cancel(r6);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r1);
        throw r0;
    L_0x015c:
        r0 = (kotlin.Result.Failure) r0;
        r0 = r0.exception;
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.mapIndexedNotNullTo(kotlinx.coroutines.channels.ReceiveChannel, java.util.Collection, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @org.jetbrains.annotations.Nullable
    @kotlinx.coroutines.ObsoleteCoroutinesApi
    public static final <E, R, C extends kotlinx.coroutines.channels.SendChannel<? super R>> java.lang.Object mapIndexedNotNullTo(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r18, @org.jetbrains.annotations.NotNull C r19, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function2<? super java.lang.Integer, ? super E, ? extends R> r20, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super C> r21) {
        /*
        r0 = r21;
        r1 = r0 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$mapIndexedNotNullTo$3;
        if (r1 == 0) goto L_0x0016;
    L_0x0006:
        r1 = r0;
        r1 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$mapIndexedNotNullTo$3) r1;
        r2 = r1.label;
        r3 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r2 = r2 & r3;
        if (r2 == 0) goto L_0x0016;
    L_0x0010:
        r0 = r1.label;
        r0 = r0 - r3;
        r1.label = r0;
        goto L_0x001b;
    L_0x0016:
        r1 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$mapIndexedNotNullTo$3;
        r1.<init>(r0);
    L_0x001b:
        r0 = r1.result;
        r2 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        r3 = r1.label;
        r4 = 1;
        switch(r3) {
            case 0: goto L_0x00ee;
            case 1: goto L_0x00ad;
            case 2: goto L_0x0075;
            case 3: goto L_0x002f;
            default: goto L_0x0027;
        };
    L_0x0027:
        r0 = new java.lang.IllegalStateException;
        r1 = "call to 'resume' before 'invoke' with coroutine";
        r0.<init>(r1);
        throw r0;
    L_0x002f:
        r3 = r1.L$14;
        r3 = r1.L$13;
        r3 = r1.I$0;
        r3 = r1.L$12;
        r3 = (kotlin.collections.IndexedValue) r3;
        r3 = r1.L$11;
        r3 = r1.L$10;
        r3 = r1.L$9;
        r3 = (kotlinx.coroutines.channels.ChannelIterator) r3;
        r5 = r1.L$8;
        r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5;
        r6 = r1.L$7;
        r6 = (java.lang.Throwable) r6;
        r7 = r1.L$6;
        r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7;
        r8 = r1.L$5;
        r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8;
        r9 = r1.L$4;
        r9 = (kotlin.jvm.internal.Ref.IntRef) r9;
        r10 = r1.L$3;
        r10 = (kotlinx.coroutines.channels.ReceiveChannel) r10;
        r11 = r1.L$2;
        r11 = (kotlin.jvm.functions.Function2) r11;
        r12 = r1.L$1;
        r12 = (kotlinx.coroutines.channels.SendChannel) r12;
        r13 = r1.L$0;
        r13 = (kotlinx.coroutines.channels.ReceiveChannel) r13;
        r14 = r0 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x00e9, all -> 0x00e4 }
        if (r14 != 0) goto L_0x0070;
    L_0x0069:
        r17 = r7;
        r7 = r2;
        r2 = r17;
        goto L_0x01a1;
    L_0x0070:
        r0 = (kotlin.Result.Failure) r0;	 Catch:{ Throwable -> 0x00e9, all -> 0x00e4 }
        r0 = r0.exception;	 Catch:{ Throwable -> 0x00e9, all -> 0x00e4 }
        throw r0;	 Catch:{ Throwable -> 0x00e9, all -> 0x00e4 }
    L_0x0075:
        r3 = r1.L$9;
        r3 = (kotlinx.coroutines.channels.ChannelIterator) r3;
        r5 = r1.L$8;
        r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5;
        r6 = r1.L$7;
        r6 = (java.lang.Throwable) r6;
        r7 = r1.L$6;
        r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7;
        r8 = r1.L$5;
        r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8;
        r9 = r1.L$4;
        r9 = (kotlin.jvm.internal.Ref.IntRef) r9;
        r10 = r1.L$3;
        r10 = (kotlinx.coroutines.channels.ReceiveChannel) r10;
        r11 = r1.L$2;
        r11 = (kotlin.jvm.functions.Function2) r11;
        r12 = r1.L$1;
        r12 = (kotlinx.coroutines.channels.SendChannel) r12;
        r13 = r1.L$0;
        r13 = (kotlinx.coroutines.channels.ReceiveChannel) r13;
        r14 = r0 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x00e9, all -> 0x00e4 }
        if (r14 != 0) goto L_0x00a8;
    L_0x00a1:
        r17 = r7;
        r7 = r2;
        r2 = r17;
        goto L_0x0156;
    L_0x00a8:
        r0 = (kotlin.Result.Failure) r0;	 Catch:{ Throwable -> 0x00e9, all -> 0x00e4 }
        r0 = r0.exception;	 Catch:{ Throwable -> 0x00e9, all -> 0x00e4 }
        throw r0;	 Catch:{ Throwable -> 0x00e9, all -> 0x00e4 }
    L_0x00ad:
        r3 = r1.L$9;
        r3 = (kotlinx.coroutines.channels.ChannelIterator) r3;
        r5 = r1.L$8;
        r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5;
        r6 = r1.L$7;
        r6 = (java.lang.Throwable) r6;
        r7 = r1.L$6;
        r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7;
        r8 = r1.L$5;
        r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8;
        r9 = r1.L$4;
        r9 = (kotlin.jvm.internal.Ref.IntRef) r9;
        r10 = r1.L$3;
        r10 = (kotlinx.coroutines.channels.ReceiveChannel) r10;
        r11 = r1.L$2;
        r11 = (kotlin.jvm.functions.Function2) r11;
        r12 = r1.L$1;
        r12 = (kotlinx.coroutines.channels.SendChannel) r12;
        r13 = r1.L$0;
        r13 = (kotlinx.coroutines.channels.ReceiveChannel) r13;
        r14 = r0 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x00e9, all -> 0x00e4 }
        if (r14 != 0) goto L_0x00df;
    L_0x00d9:
        r17 = r7;
        r7 = r2;
        r2 = r17;
        goto L_0x0130;
    L_0x00df:
        r0 = (kotlin.Result.Failure) r0;	 Catch:{ Throwable -> 0x00e9, all -> 0x00e4 }
        r0 = r0.exception;	 Catch:{ Throwable -> 0x00e9, all -> 0x00e4 }
        throw r0;	 Catch:{ Throwable -> 0x00e9, all -> 0x00e4 }
    L_0x00e4:
        r0 = move-exception;
        r2 = r7;
    L_0x00e6:
        r1 = 1;
        goto L_0x01c5;
    L_0x00e9:
        r0 = move-exception;
        r6 = r0;
        r2 = r7;
        goto L_0x01c1;
    L_0x00ee:
        r3 = r0 instanceof kotlin.Result.Failure;
        if (r3 != 0) goto L_0x01cf;
    L_0x00f2:
        r0 = new kotlin.jvm.internal.Ref$IntRef;
        r0.<init>();
        r3 = 0;
        r0.element = r3;
        r3 = 0;
        r6 = r3;
        r6 = (java.lang.Throwable) r6;
        r3 = r18.iterator();	 Catch:{ Throwable -> 0x01bd, all -> 0x01b8 }
        r5 = r18;
        r8 = r5;
        r10 = r8;
        r13 = r10;
        r12 = r19;
        r11 = r20;
        r9 = r0;
        r0 = r2;
        r2 = r13;
    L_0x010e:
        r1.L$0 = r13;	 Catch:{ Throwable -> 0x01b6 }
        r1.L$1 = r12;	 Catch:{ Throwable -> 0x01b6 }
        r1.L$2 = r11;	 Catch:{ Throwable -> 0x01b6 }
        r1.L$3 = r10;	 Catch:{ Throwable -> 0x01b6 }
        r1.L$4 = r9;	 Catch:{ Throwable -> 0x01b6 }
        r1.L$5 = r8;	 Catch:{ Throwable -> 0x01b6 }
        r1.L$6 = r2;	 Catch:{ Throwable -> 0x01b6 }
        r1.L$7 = r6;	 Catch:{ Throwable -> 0x01b6 }
        r1.L$8 = r5;	 Catch:{ Throwable -> 0x01b6 }
        r1.L$9 = r3;	 Catch:{ Throwable -> 0x01b6 }
        r1.label = r4;	 Catch:{ Throwable -> 0x01b6 }
        r7 = r3.hasNext(r1);	 Catch:{ Throwable -> 0x01b6 }
        if (r7 != r0) goto L_0x012b;
    L_0x012a:
        return r0;
    L_0x012b:
        r17 = r7;
        r7 = r0;
        r0 = r17;
    L_0x0130:
        r0 = (java.lang.Boolean) r0;	 Catch:{ Throwable -> 0x01b6 }
        r0 = r0.booleanValue();	 Catch:{ Throwable -> 0x01b6 }
        if (r0 == 0) goto L_0x01a9;
    L_0x0138:
        r1.L$0 = r13;	 Catch:{ Throwable -> 0x01b6 }
        r1.L$1 = r12;	 Catch:{ Throwable -> 0x01b6 }
        r1.L$2 = r11;	 Catch:{ Throwable -> 0x01b6 }
        r1.L$3 = r10;	 Catch:{ Throwable -> 0x01b6 }
        r1.L$4 = r9;	 Catch:{ Throwable -> 0x01b6 }
        r1.L$5 = r8;	 Catch:{ Throwable -> 0x01b6 }
        r1.L$6 = r2;	 Catch:{ Throwable -> 0x01b6 }
        r1.L$7 = r6;	 Catch:{ Throwable -> 0x01b6 }
        r1.L$8 = r5;	 Catch:{ Throwable -> 0x01b6 }
        r1.L$9 = r3;	 Catch:{ Throwable -> 0x01b6 }
        r0 = 2;
        r1.label = r0;	 Catch:{ Throwable -> 0x01b6 }
        r0 = r3.next(r1);	 Catch:{ Throwable -> 0x01b6 }
        if (r0 != r7) goto L_0x0156;
    L_0x0155:
        return r7;
    L_0x0156:
        r14 = new kotlin.collections.IndexedValue;	 Catch:{ Throwable -> 0x01b6 }
        r15 = r9.element;	 Catch:{ Throwable -> 0x01b6 }
        r4 = r15 + 1;
        r9.element = r4;	 Catch:{ Throwable -> 0x01b6 }
        r14.<init>(r15, r0);	 Catch:{ Throwable -> 0x01b6 }
        r4 = r14.component1();	 Catch:{ Throwable -> 0x01b6 }
        r15 = r14.component2();	 Catch:{ Throwable -> 0x01b6 }
        r16 = r7;
        r7 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r4);	 Catch:{ Throwable -> 0x01b6 }
        r7 = r11.invoke(r7, r15);	 Catch:{ Throwable -> 0x01b6 }
        if (r7 == 0) goto L_0x01a3;
    L_0x0175:
        r1.L$0 = r13;	 Catch:{ Throwable -> 0x01b6 }
        r1.L$1 = r12;	 Catch:{ Throwable -> 0x01b6 }
        r1.L$2 = r11;	 Catch:{ Throwable -> 0x01b6 }
        r1.L$3 = r10;	 Catch:{ Throwable -> 0x01b6 }
        r1.L$4 = r9;	 Catch:{ Throwable -> 0x01b6 }
        r1.L$5 = r8;	 Catch:{ Throwable -> 0x01b6 }
        r1.L$6 = r2;	 Catch:{ Throwable -> 0x01b6 }
        r1.L$7 = r6;	 Catch:{ Throwable -> 0x01b6 }
        r1.L$8 = r5;	 Catch:{ Throwable -> 0x01b6 }
        r1.L$9 = r3;	 Catch:{ Throwable -> 0x01b6 }
        r1.L$10 = r0;	 Catch:{ Throwable -> 0x01b6 }
        r1.L$11 = r0;	 Catch:{ Throwable -> 0x01b6 }
        r1.L$12 = r14;	 Catch:{ Throwable -> 0x01b6 }
        r1.I$0 = r4;	 Catch:{ Throwable -> 0x01b6 }
        r1.L$13 = r15;	 Catch:{ Throwable -> 0x01b6 }
        r1.L$14 = r7;	 Catch:{ Throwable -> 0x01b6 }
        r0 = 3;
        r1.label = r0;	 Catch:{ Throwable -> 0x01b6 }
        r0 = r12.send(r7, r1);	 Catch:{ Throwable -> 0x01b6 }
        r7 = r16;
        if (r0 != r7) goto L_0x01a1;
    L_0x01a0:
        return r7;
    L_0x01a1:
        r0 = r7;
        goto L_0x01a6;
    L_0x01a3:
        r7 = r16;
        goto L_0x01a1;
    L_0x01a6:
        r4 = 1;
        goto L_0x010e;
    L_0x01a9:
        r0 = kotlin.Unit.INSTANCE;	 Catch:{ Throwable -> 0x01b6 }
        r1 = 1;
        kotlin.jvm.internal.InlineMarker.finallyStart(r1);
        r2.cancel(r6);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r1);
        return r12;
    L_0x01b6:
        r0 = move-exception;
        goto L_0x01c0;
    L_0x01b8:
        r0 = move-exception;
        r2 = r18;
        goto L_0x00e6;
    L_0x01bd:
        r0 = move-exception;
        r2 = r18;
    L_0x01c0:
        r6 = r0;
    L_0x01c1:
        throw r6;	 Catch:{ all -> 0x01c2 }
    L_0x01c2:
        r0 = move-exception;
        goto L_0x00e6;
    L_0x01c5:
        kotlin.jvm.internal.InlineMarker.finallyStart(r1);
        r2.cancel(r6);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r1);
        throw r0;
    L_0x01cf:
        r0 = (kotlin.Result.Failure) r0;
        r0 = r0.exception;
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.mapIndexedNotNullTo(kotlinx.coroutines.channels.ReceiveChannel, kotlinx.coroutines.channels.SendChannel, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @org.jetbrains.annotations.Nullable
    @kotlinx.coroutines.ObsoleteCoroutinesApi
    public static final <E, R, C extends java.util.Collection<? super R>> java.lang.Object mapIndexedTo(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r16, @org.jetbrains.annotations.NotNull C r17, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function2<? super java.lang.Integer, ? super E, ? extends R> r18, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super C> r19) {
        /*
        r0 = r19;
        r1 = r0 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$mapIndexedTo$1;
        if (r1 == 0) goto L_0x0016;
    L_0x0006:
        r1 = r0;
        r1 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$mapIndexedTo$1) r1;
        r2 = r1.label;
        r3 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r2 = r2 & r3;
        if (r2 == 0) goto L_0x0016;
    L_0x0010:
        r0 = r1.label;
        r0 = r0 - r3;
        r1.label = r0;
        goto L_0x001b;
    L_0x0016:
        r1 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$mapIndexedTo$1;
        r1.<init>(r0);
    L_0x001b:
        r0 = r1.result;
        r2 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        r3 = r1.label;
        r4 = 1;
        switch(r3) {
            case 0: goto L_0x009b;
            case 1: goto L_0x0061;
            case 2: goto L_0x002f;
            default: goto L_0x0027;
        };
    L_0x0027:
        r0 = new java.lang.IllegalStateException;
        r1 = "call to 'resume' before 'invoke' with coroutine";
        r0.<init>(r1);
        throw r0;
    L_0x002f:
        r3 = r1.L$8;
        r3 = (kotlinx.coroutines.channels.ChannelIterator) r3;
        r5 = r1.L$7;
        r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5;
        r6 = r1.L$6;
        r6 = (java.lang.Throwable) r6;
        r7 = r1.L$5;
        r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7;
        r8 = r1.L$4;
        r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8;
        r9 = r1.L$3;
        r9 = (kotlin.jvm.internal.Ref.IntRef) r9;
        r10 = r1.L$2;
        r10 = (kotlin.jvm.functions.Function2) r10;
        r11 = r1.L$1;
        r11 = (java.util.Collection) r11;
        r12 = r1.L$0;
        r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12;
        r13 = r0 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x0096, all -> 0x0092 }
        if (r13 != 0) goto L_0x005c;
    L_0x0057:
        r15 = r7;
        r7 = r2;
        r2 = r15;
        goto L_0x00fb;
    L_0x005c:
        r0 = (kotlin.Result.Failure) r0;	 Catch:{ Throwable -> 0x0096, all -> 0x0092 }
        r0 = r0.exception;	 Catch:{ Throwable -> 0x0096, all -> 0x0092 }
        throw r0;	 Catch:{ Throwable -> 0x0096, all -> 0x0092 }
    L_0x0061:
        r3 = r1.L$8;
        r3 = (kotlinx.coroutines.channels.ChannelIterator) r3;
        r5 = r1.L$7;
        r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5;
        r6 = r1.L$6;
        r6 = (java.lang.Throwable) r6;
        r7 = r1.L$5;
        r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7;
        r8 = r1.L$4;
        r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8;
        r9 = r1.L$3;
        r9 = (kotlin.jvm.internal.Ref.IntRef) r9;
        r10 = r1.L$2;
        r10 = (kotlin.jvm.functions.Function2) r10;
        r11 = r1.L$1;
        r11 = (java.util.Collection) r11;
        r12 = r1.L$0;
        r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12;
        r13 = r0 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x0096, all -> 0x0092 }
        if (r13 != 0) goto L_0x008d;
    L_0x0089:
        r15 = r7;
        r7 = r2;
        r2 = r15;
        goto L_0x00d7;
    L_0x008d:
        r0 = (kotlin.Result.Failure) r0;	 Catch:{ Throwable -> 0x0096, all -> 0x0092 }
        r0 = r0.exception;	 Catch:{ Throwable -> 0x0096, all -> 0x0092 }
        throw r0;	 Catch:{ Throwable -> 0x0096, all -> 0x0092 }
    L_0x0092:
        r0 = move-exception;
        r2 = r7;
        goto L_0x0126;
    L_0x0096:
        r0 = move-exception;
        r6 = r0;
        r2 = r7;
        goto L_0x0124;
    L_0x009b:
        r3 = r0 instanceof kotlin.Result.Failure;
        if (r3 != 0) goto L_0x0130;
    L_0x009f:
        r0 = new kotlin.jvm.internal.Ref$IntRef;
        r0.<init>();
        r3 = 0;
        r0.element = r3;
        r3 = 0;
        r6 = r3;
        r6 = (java.lang.Throwable) r6;
        r3 = r16.iterator();	 Catch:{ Throwable -> 0x0120, all -> 0x011c }
        r5 = r16;
        r8 = r5;
        r12 = r8;
        r11 = r17;
        r10 = r18;
        r7 = r2;
        r2 = r12;
    L_0x00b9:
        r1.L$0 = r12;	 Catch:{ Throwable -> 0x011a }
        r1.L$1 = r11;	 Catch:{ Throwable -> 0x011a }
        r1.L$2 = r10;	 Catch:{ Throwable -> 0x011a }
        r1.L$3 = r0;	 Catch:{ Throwable -> 0x011a }
        r1.L$4 = r8;	 Catch:{ Throwable -> 0x011a }
        r1.L$5 = r2;	 Catch:{ Throwable -> 0x011a }
        r1.L$6 = r6;	 Catch:{ Throwable -> 0x011a }
        r1.L$7 = r5;	 Catch:{ Throwable -> 0x011a }
        r1.L$8 = r3;	 Catch:{ Throwable -> 0x011a }
        r1.label = r4;	 Catch:{ Throwable -> 0x011a }
        r9 = r3.hasNext(r1);	 Catch:{ Throwable -> 0x011a }
        if (r9 != r7) goto L_0x00d4;
    L_0x00d3:
        return r7;
    L_0x00d4:
        r15 = r9;
        r9 = r0;
        r0 = r15;
    L_0x00d7:
        r0 = (java.lang.Boolean) r0;	 Catch:{ Throwable -> 0x011a }
        r0 = r0.booleanValue();	 Catch:{ Throwable -> 0x011a }
        if (r0 == 0) goto L_0x010e;
    L_0x00df:
        r1.L$0 = r12;	 Catch:{ Throwable -> 0x011a }
        r1.L$1 = r11;	 Catch:{ Throwable -> 0x011a }
        r1.L$2 = r10;	 Catch:{ Throwable -> 0x011a }
        r1.L$3 = r9;	 Catch:{ Throwable -> 0x011a }
        r1.L$4 = r8;	 Catch:{ Throwable -> 0x011a }
        r1.L$5 = r2;	 Catch:{ Throwable -> 0x011a }
        r1.L$6 = r6;	 Catch:{ Throwable -> 0x011a }
        r1.L$7 = r5;	 Catch:{ Throwable -> 0x011a }
        r1.L$8 = r3;	 Catch:{ Throwable -> 0x011a }
        r0 = 2;
        r1.label = r0;	 Catch:{ Throwable -> 0x011a }
        r0 = r3.next(r1);	 Catch:{ Throwable -> 0x011a }
        if (r0 != r7) goto L_0x00fb;
    L_0x00fa:
        return r7;
    L_0x00fb:
        r13 = r9.element;	 Catch:{ Throwable -> 0x011a }
        r14 = r13 + 1;
        r9.element = r14;	 Catch:{ Throwable -> 0x011a }
        r13 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r13);	 Catch:{ Throwable -> 0x011a }
        r0 = r10.invoke(r13, r0);	 Catch:{ Throwable -> 0x011a }
        r11.add(r0);	 Catch:{ Throwable -> 0x011a }
        r0 = r9;
        goto L_0x00b9;
    L_0x010e:
        r0 = kotlin.Unit.INSTANCE;	 Catch:{ Throwable -> 0x011a }
        kotlin.jvm.internal.InlineMarker.finallyStart(r4);
        r2.cancel(r6);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r4);
        return r11;
    L_0x011a:
        r0 = move-exception;
        goto L_0x0123;
    L_0x011c:
        r0 = move-exception;
        r2 = r16;
        goto L_0x0126;
    L_0x0120:
        r0 = move-exception;
        r2 = r16;
    L_0x0123:
        r6 = r0;
    L_0x0124:
        throw r6;	 Catch:{ all -> 0x0125 }
    L_0x0125:
        r0 = move-exception;
    L_0x0126:
        kotlin.jvm.internal.InlineMarker.finallyStart(r4);
        r2.cancel(r6);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r4);
        throw r0;
    L_0x0130:
        r0 = (kotlin.Result.Failure) r0;
        r0 = r0.exception;
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.mapIndexedTo(kotlinx.coroutines.channels.ReceiveChannel, java.util.Collection, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @org.jetbrains.annotations.Nullable
    @kotlinx.coroutines.ObsoleteCoroutinesApi
    public static final <E, R, C extends kotlinx.coroutines.channels.SendChannel<? super R>> java.lang.Object mapIndexedTo(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r18, @org.jetbrains.annotations.NotNull C r19, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function2<? super java.lang.Integer, ? super E, ? extends R> r20, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super C> r21) {
        /*
        r0 = r21;
        r1 = r0 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$mapIndexedTo$3;
        if (r1 == 0) goto L_0x0016;
    L_0x0006:
        r1 = r0;
        r1 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$mapIndexedTo$3) r1;
        r2 = r1.label;
        r3 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r2 = r2 & r3;
        if (r2 == 0) goto L_0x0016;
    L_0x0010:
        r0 = r1.label;
        r0 = r0 - r3;
        r1.label = r0;
        goto L_0x001b;
    L_0x0016:
        r1 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$mapIndexedTo$3;
        r1.<init>(r0);
    L_0x001b:
        r0 = r1.result;
        r2 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        r3 = r1.label;
        r4 = 1;
        switch(r3) {
            case 0: goto L_0x00da;
            case 1: goto L_0x00a2;
            case 2: goto L_0x0070;
            case 3: goto L_0x002f;
            default: goto L_0x0027;
        };
    L_0x0027:
        r0 = new java.lang.IllegalStateException;
        r1 = "call to 'resume' before 'invoke' with coroutine";
        r0.<init>(r1);
        throw r0;
    L_0x002f:
        r3 = r1.L$10;
        r3 = r1.L$9;
        r3 = r1.L$8;
        r3 = (kotlinx.coroutines.channels.ChannelIterator) r3;
        r5 = r1.L$7;
        r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5;
        r6 = r1.L$6;
        r6 = (java.lang.Throwable) r6;
        r7 = r1.L$5;
        r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7;
        r8 = r1.L$4;
        r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8;
        r9 = r1.L$3;
        r9 = (kotlin.jvm.internal.Ref.IntRef) r9;
        r10 = r1.L$2;
        r10 = (kotlin.jvm.functions.Function2) r10;
        r11 = r1.L$1;
        r11 = (kotlinx.coroutines.channels.SendChannel) r11;
        r12 = r1.L$0;
        r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12;
        r13 = r0 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x00d6 }
        if (r13 != 0) goto L_0x006b;
    L_0x005b:
        r0 = r12;
    L_0x005c:
        r15 = r11;
        r11 = r2;
        r2 = r15;
        r16 = r10;
        r10 = r5;
        r5 = r16;
        r17 = r8;
        r8 = r6;
        r6 = r17;
        goto L_0x00fa;
    L_0x006b:
        r0 = (kotlin.Result.Failure) r0;	 Catch:{ Throwable -> 0x00d6 }
        r0 = r0.exception;	 Catch:{ Throwable -> 0x00d6 }
        throw r0;	 Catch:{ Throwable -> 0x00d6 }
    L_0x0070:
        r3 = r1.L$8;
        r3 = (kotlinx.coroutines.channels.ChannelIterator) r3;
        r5 = r1.L$7;
        r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5;
        r6 = r1.L$6;
        r6 = (java.lang.Throwable) r6;
        r7 = r1.L$5;
        r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7;
        r8 = r1.L$4;
        r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8;
        r9 = r1.L$3;
        r9 = (kotlin.jvm.internal.Ref.IntRef) r9;
        r10 = r1.L$2;
        r10 = (kotlin.jvm.functions.Function2) r10;
        r11 = r1.L$1;
        r11 = (kotlinx.coroutines.channels.SendChannel) r11;
        r12 = r1.L$0;
        r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12;
        r13 = r0 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x00d6 }
        if (r13 != 0) goto L_0x009d;
    L_0x0098:
        r15 = r12;
        r12 = r0;
        r0 = r15;
        goto L_0x0146;
    L_0x009d:
        r0 = (kotlin.Result.Failure) r0;	 Catch:{ Throwable -> 0x00d6 }
        r0 = r0.exception;	 Catch:{ Throwable -> 0x00d6 }
        throw r0;	 Catch:{ Throwable -> 0x00d6 }
    L_0x00a2:
        r3 = r1.L$8;
        r3 = (kotlinx.coroutines.channels.ChannelIterator) r3;
        r5 = r1.L$7;
        r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5;
        r6 = r1.L$6;
        r6 = (java.lang.Throwable) r6;
        r7 = r1.L$5;
        r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7;
        r8 = r1.L$4;
        r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8;
        r9 = r1.L$3;
        r9 = (kotlin.jvm.internal.Ref.IntRef) r9;
        r10 = r1.L$2;
        r10 = (kotlin.jvm.functions.Function2) r10;
        r11 = r1.L$1;
        r11 = (kotlinx.coroutines.channels.SendChannel) r11;
        r12 = r1.L$0;
        r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12;
        r13 = r0 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x00d6 }
        if (r13 != 0) goto L_0x00ce;
    L_0x00ca:
        r15 = r12;
        r12 = r0;
        r0 = r15;
        goto L_0x0122;
    L_0x00ce:
        r0 = (kotlin.Result.Failure) r0;	 Catch:{ Throwable -> 0x00d6 }
        r0 = r0.exception;	 Catch:{ Throwable -> 0x00d6 }
        throw r0;	 Catch:{ Throwable -> 0x00d6 }
    L_0x00d3:
        r0 = move-exception;
        goto L_0x018d;
    L_0x00d6:
        r0 = move-exception;
    L_0x00d7:
        r6 = r0;
        goto L_0x018c;
    L_0x00da:
        r3 = r0 instanceof kotlin.Result.Failure;
        if (r3 != 0) goto L_0x0197;
    L_0x00de:
        r0 = new kotlin.jvm.internal.Ref$IntRef;
        r0.<init>();
        r3 = 0;
        r0.element = r3;
        r3 = 0;
        r6 = r3;
        r6 = (java.lang.Throwable) r6;
        r3 = r18.iterator();	 Catch:{ Throwable -> 0x0187, all -> 0x0183 }
        r7 = r18;
        r10 = r7;
        r5 = r20;
        r9 = r0;
        r11 = r2;
        r8 = r6;
        r0 = r10;
        r6 = r0;
        r2 = r19;
    L_0x00fa:
        r1.L$0 = r0;	 Catch:{ Throwable -> 0x00d6, all -> 0x0180 }
        r1.L$1 = r2;	 Catch:{ Throwable -> 0x00d6, all -> 0x0180 }
        r1.L$2 = r5;	 Catch:{ Throwable -> 0x00d6, all -> 0x0180 }
        r1.L$3 = r9;	 Catch:{ Throwable -> 0x00d6, all -> 0x0180 }
        r1.L$4 = r6;	 Catch:{ Throwable -> 0x00d6, all -> 0x0180 }
        r1.L$5 = r7;	 Catch:{ Throwable -> 0x00d6, all -> 0x0180 }
        r1.L$6 = r8;	 Catch:{ Throwable -> 0x00d6, all -> 0x0180 }
        r1.L$7 = r10;	 Catch:{ Throwable -> 0x00d6, all -> 0x0180 }
        r1.L$8 = r3;	 Catch:{ Throwable -> 0x00d6, all -> 0x0180 }
        r1.label = r4;	 Catch:{ Throwable -> 0x00d6, all -> 0x0180 }
        r12 = r3.hasNext(r1);	 Catch:{ Throwable -> 0x00d6, all -> 0x0180 }
        if (r12 != r11) goto L_0x0115;
    L_0x0114:
        return r11;
    L_0x0115:
        r15 = r11;
        r11 = r2;
        r2 = r15;
        r16 = r10;
        r10 = r5;
        r5 = r16;
        r17 = r8;
        r8 = r6;
        r6 = r17;
    L_0x0122:
        r12 = (java.lang.Boolean) r12;	 Catch:{ Throwable -> 0x00d6 }
        r12 = r12.booleanValue();	 Catch:{ Throwable -> 0x00d6 }
        if (r12 == 0) goto L_0x0174;
    L_0x012a:
        r1.L$0 = r0;	 Catch:{ Throwable -> 0x00d6 }
        r1.L$1 = r11;	 Catch:{ Throwable -> 0x00d6 }
        r1.L$2 = r10;	 Catch:{ Throwable -> 0x00d6 }
        r1.L$3 = r9;	 Catch:{ Throwable -> 0x00d6 }
        r1.L$4 = r8;	 Catch:{ Throwable -> 0x00d6 }
        r1.L$5 = r7;	 Catch:{ Throwable -> 0x00d6 }
        r1.L$6 = r6;	 Catch:{ Throwable -> 0x00d6 }
        r1.L$7 = r5;	 Catch:{ Throwable -> 0x00d6 }
        r1.L$8 = r3;	 Catch:{ Throwable -> 0x00d6 }
        r12 = 2;
        r1.label = r12;	 Catch:{ Throwable -> 0x00d6 }
        r12 = r3.next(r1);	 Catch:{ Throwable -> 0x00d6 }
        if (r12 != r2) goto L_0x0146;
    L_0x0145:
        return r2;
    L_0x0146:
        r13 = r9.element;	 Catch:{ Throwable -> 0x00d6 }
        r14 = r13 + 1;
        r9.element = r14;	 Catch:{ Throwable -> 0x00d6 }
        r13 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r13);	 Catch:{ Throwable -> 0x00d6 }
        r13 = r10.invoke(r13, r12);	 Catch:{ Throwable -> 0x00d6 }
        r1.L$0 = r0;	 Catch:{ Throwable -> 0x00d6 }
        r1.L$1 = r11;	 Catch:{ Throwable -> 0x00d6 }
        r1.L$2 = r10;	 Catch:{ Throwable -> 0x00d6 }
        r1.L$3 = r9;	 Catch:{ Throwable -> 0x00d6 }
        r1.L$4 = r8;	 Catch:{ Throwable -> 0x00d6 }
        r1.L$5 = r7;	 Catch:{ Throwable -> 0x00d6 }
        r1.L$6 = r6;	 Catch:{ Throwable -> 0x00d6 }
        r1.L$7 = r5;	 Catch:{ Throwable -> 0x00d6 }
        r1.L$8 = r3;	 Catch:{ Throwable -> 0x00d6 }
        r1.L$9 = r12;	 Catch:{ Throwable -> 0x00d6 }
        r1.L$10 = r12;	 Catch:{ Throwable -> 0x00d6 }
        r12 = 3;
        r1.label = r12;	 Catch:{ Throwable -> 0x00d6 }
        r12 = r11.send(r13, r1);	 Catch:{ Throwable -> 0x00d6 }
        if (r12 != r2) goto L_0x005c;
    L_0x0173:
        return r2;
    L_0x0174:
        r0 = kotlin.Unit.INSTANCE;	 Catch:{ Throwable -> 0x00d6 }
        kotlin.jvm.internal.InlineMarker.finallyStart(r4);
        r7.cancel(r6);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r4);
        return r11;
    L_0x0180:
        r0 = move-exception;
        r6 = r8;
        goto L_0x018d;
    L_0x0183:
        r0 = move-exception;
        r7 = r18;
        goto L_0x018d;
    L_0x0187:
        r0 = move-exception;
        r7 = r18;
        goto L_0x00d7;
    L_0x018c:
        throw r6;	 Catch:{ all -> 0x00d3 }
    L_0x018d:
        kotlin.jvm.internal.InlineMarker.finallyStart(r4);
        r7.cancel(r6);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r4);
        throw r0;
    L_0x0197:
        r0 = (kotlin.Result.Failure) r0;
        r0 = r0.exception;
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.mapIndexedTo(kotlinx.coroutines.channels.ReceiveChannel, kotlinx.coroutines.channels.SendChannel, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @NotNull
    @ObsoleteCoroutinesApi
    public static /* synthetic */ ReceiveChannel mapNotNull$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return ChannelsKt.mapNotNull(receiveChannel, coroutineContext, function2);
    }

    @NotNull
    @ObsoleteCoroutinesApi
    public static final <E, R> ReceiveChannel<R> mapNotNull(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull CoroutineContext coroutineContext, @NotNull Function2<? super E, ? super Continuation<? super R>, ? extends Object> function2) {
        Intrinsics.checkParameterIsNotNull(receiveChannel, "receiver$0");
        Intrinsics.checkParameterIsNotNull(coroutineContext, "context");
        Intrinsics.checkParameterIsNotNull(function2, "transform");
        return ChannelsKt.filterNotNull(ChannelsKt.map(receiveChannel, coroutineContext, function2));
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @org.jetbrains.annotations.Nullable
    @kotlinx.coroutines.ObsoleteCoroutinesApi
    public static final <E, R, C extends java.util.Collection<? super R>> java.lang.Object mapNotNullTo(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r10, @org.jetbrains.annotations.NotNull C r11, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super E, ? extends R> r12, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super C> r13) {
        /*
        r0 = r13 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$mapNotNullTo$1;
        if (r0 == 0) goto L_0x0014;
    L_0x0004:
        r0 = r13;
        r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$mapNotNullTo$1) r0;
        r1 = r0.label;
        r2 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r1 = r1 & r2;
        if (r1 == 0) goto L_0x0014;
    L_0x000e:
        r13 = r0.label;
        r13 = r13 - r2;
        r0.label = r13;
        goto L_0x0019;
    L_0x0014:
        r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$mapNotNullTo$1;
        r0.<init>(r13);
    L_0x0019:
        r13 = r0.result;
        r1 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        r2 = r0.label;
        r3 = 1;
        switch(r2) {
            case 0: goto L_0x008d;
            case 1: goto L_0x005b;
            case 2: goto L_0x002d;
            default: goto L_0x0025;
        };
    L_0x0025:
        r10 = new java.lang.IllegalStateException;
        r11 = "call to 'resume' before 'invoke' with coroutine";
        r10.<init>(r11);
        throw r10;
    L_0x002d:
        r10 = r0.L$7;
        r10 = (kotlinx.coroutines.channels.ChannelIterator) r10;
        r11 = r0.L$6;
        r11 = (kotlinx.coroutines.channels.ReceiveChannel) r11;
        r12 = r0.L$5;
        r12 = (java.lang.Throwable) r12;
        r2 = r0.L$4;
        r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2;
        r4 = r0.L$3;
        r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4;
        r5 = r0.L$2;
        r5 = (kotlin.jvm.functions.Function1) r5;
        r6 = r0.L$1;
        r6 = (java.util.Collection) r6;
        r7 = r0.L$0;
        r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7;
        r8 = r13 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        if (r8 != 0) goto L_0x0056;
    L_0x0051:
        r9 = r2;
        r2 = r10;
        r10 = r9;
        goto L_0x00dc;
    L_0x0056:
        r13 = (kotlin.Result.Failure) r13;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r10 = r13.exception;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        throw r10;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
    L_0x005b:
        r10 = r0.L$7;
        r10 = (kotlinx.coroutines.channels.ChannelIterator) r10;
        r11 = r0.L$6;
        r11 = (kotlinx.coroutines.channels.ReceiveChannel) r11;
        r12 = r0.L$5;
        r12 = (java.lang.Throwable) r12;
        r2 = r0.L$4;
        r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2;
        r4 = r0.L$3;
        r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4;
        r5 = r0.L$2;
        r5 = (kotlin.jvm.functions.Function1) r5;
        r6 = r0.L$1;
        r6 = (java.util.Collection) r6;
        r7 = r0.L$0;
        r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7;
        r8 = r13 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        if (r8 != 0) goto L_0x0080;
    L_0x007f:
        goto L_0x00ba;
    L_0x0080:
        r13 = (kotlin.Result.Failure) r13;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r10 = r13.exception;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        throw r10;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
    L_0x0085:
        r10 = move-exception;
        goto L_0x0101;
    L_0x0088:
        r10 = move-exception;
        r12 = r10;
        r10 = r2;
        goto L_0x00fd;
    L_0x008d:
        r2 = r13 instanceof kotlin.Result.Failure;
        if (r2 != 0) goto L_0x010b;
    L_0x0091:
        r13 = 0;
        r13 = (java.lang.Throwable) r13;
        r2 = r10.iterator();	 Catch:{ Throwable -> 0x00fb, all -> 0x00f6 }
        r4 = r10;
        r7 = r4;
        r6 = r11;
        r5 = r12;
        r12 = r13;
        r11 = r7;
    L_0x009e:
        r0.L$0 = r7;	 Catch:{ Throwable -> 0x00fb }
        r0.L$1 = r6;	 Catch:{ Throwable -> 0x00fb }
        r0.L$2 = r5;	 Catch:{ Throwable -> 0x00fb }
        r0.L$3 = r4;	 Catch:{ Throwable -> 0x00fb }
        r0.L$4 = r10;	 Catch:{ Throwable -> 0x00fb }
        r0.L$5 = r12;	 Catch:{ Throwable -> 0x00fb }
        r0.L$6 = r11;	 Catch:{ Throwable -> 0x00fb }
        r0.L$7 = r2;	 Catch:{ Throwable -> 0x00fb }
        r0.label = r3;	 Catch:{ Throwable -> 0x00fb }
        r13 = r2.hasNext(r0);	 Catch:{ Throwable -> 0x00fb }
        if (r13 != r1) goto L_0x00b7;
    L_0x00b6:
        return r1;
    L_0x00b7:
        r9 = r2;
        r2 = r10;
        r10 = r9;
    L_0x00ba:
        r13 = (java.lang.Boolean) r13;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r13 = r13.booleanValue();	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        if (r13 == 0) goto L_0x00ea;
    L_0x00c2:
        r0.L$0 = r7;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r0.L$1 = r6;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r0.L$2 = r5;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r0.L$3 = r4;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r0.L$4 = r2;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r0.L$5 = r12;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r0.L$6 = r11;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r0.L$7 = r10;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r13 = 2;
        r0.label = r13;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r13 = r10.next(r0);	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        if (r13 != r1) goto L_0x0051;
    L_0x00db:
        return r1;
    L_0x00dc:
        r13 = r5.invoke(r13);	 Catch:{ Throwable -> 0x00fb }
        if (r13 == 0) goto L_0x009e;
    L_0x00e2:
        r13 = r6.add(r13);	 Catch:{ Throwable -> 0x00fb }
        kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r13);	 Catch:{ Throwable -> 0x00fb }
        goto L_0x009e;
    L_0x00ea:
        r10 = kotlin.Unit.INSTANCE;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        kotlin.jvm.internal.InlineMarker.finallyStart(r3);
        r2.cancel(r12);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r3);
        return r6;
    L_0x00f6:
        r11 = move-exception;
        r2 = r10;
        r10 = r11;
        r12 = r13;
        goto L_0x0101;
    L_0x00fb:
        r11 = move-exception;
        r12 = r11;
    L_0x00fd:
        throw r12;	 Catch:{ all -> 0x00fe }
    L_0x00fe:
        r11 = move-exception;
        r2 = r10;
        r10 = r11;
    L_0x0101:
        kotlin.jvm.internal.InlineMarker.finallyStart(r3);
        r2.cancel(r12);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r3);
        throw r10;
    L_0x010b:
        r13 = (kotlin.Result.Failure) r13;
        r10 = r13.exception;
        throw r10;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.mapNotNullTo(kotlinx.coroutines.channels.ReceiveChannel, java.util.Collection, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @org.jetbrains.annotations.Nullable
    @kotlinx.coroutines.ObsoleteCoroutinesApi
    public static final <E, R, C extends kotlinx.coroutines.channels.SendChannel<? super R>> java.lang.Object mapNotNullTo(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r10, @org.jetbrains.annotations.NotNull C r11, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super E, ? extends R> r12, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super C> r13) {
        /*
        r0 = r13 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$mapNotNullTo$3;
        if (r0 == 0) goto L_0x0014;
    L_0x0004:
        r0 = r13;
        r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$mapNotNullTo$3) r0;
        r1 = r0.label;
        r2 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r1 = r1 & r2;
        if (r1 == 0) goto L_0x0014;
    L_0x000e:
        r13 = r0.label;
        r13 = r13 - r2;
        r0.label = r13;
        goto L_0x0019;
    L_0x0014:
        r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$mapNotNullTo$3;
        r0.<init>(r13);
    L_0x0019:
        r13 = r0.result;
        r1 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        r2 = r0.label;
        r3 = 1;
        switch(r2) {
            case 0: goto L_0x00c8;
            case 1: goto L_0x0091;
            case 2: goto L_0x0062;
            case 3: goto L_0x002d;
            default: goto L_0x0025;
        };
    L_0x0025:
        r10 = new java.lang.IllegalStateException;
        r11 = "call to 'resume' before 'invoke' with coroutine";
        r10.<init>(r11);
        throw r10;
    L_0x002d:
        r10 = r0.L$10;
        r10 = r0.L$9;
        r10 = r0.L$8;
        r10 = r0.L$7;
        r10 = (kotlinx.coroutines.channels.ChannelIterator) r10;
        r11 = r0.L$6;
        r11 = (kotlinx.coroutines.channels.ReceiveChannel) r11;
        r12 = r0.L$5;
        r12 = (java.lang.Throwable) r12;
        r2 = r0.L$4;
        r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2;
        r4 = r0.L$3;
        r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4;
        r5 = r0.L$2;
        r5 = (kotlin.jvm.functions.Function1) r5;
        r6 = r0.L$1;
        r6 = (kotlinx.coroutines.channels.SendChannel) r6;
        r7 = r0.L$0;
        r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7;
        r8 = r13 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x00c3, all -> 0x00bf }
        if (r8 != 0) goto L_0x005d;
    L_0x0057:
        r9 = r1;
        r1 = r11;
        r11 = r2;
        r2 = r9;
        goto L_0x0140;
    L_0x005d:
        r13 = (kotlin.Result.Failure) r13;	 Catch:{ Throwable -> 0x00c3, all -> 0x00bf }
        r10 = r13.exception;	 Catch:{ Throwable -> 0x00c3, all -> 0x00bf }
        throw r10;	 Catch:{ Throwable -> 0x00c3, all -> 0x00bf }
    L_0x0062:
        r10 = r0.L$7;
        r10 = (kotlinx.coroutines.channels.ChannelIterator) r10;
        r11 = r0.L$6;
        r11 = (kotlinx.coroutines.channels.ReceiveChannel) r11;
        r12 = r0.L$5;
        r12 = (java.lang.Throwable) r12;
        r2 = r0.L$4;
        r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2;
        r4 = r0.L$3;
        r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4;
        r5 = r0.L$2;
        r5 = (kotlin.jvm.functions.Function1) r5;
        r6 = r0.L$1;
        r6 = (kotlinx.coroutines.channels.SendChannel) r6;
        r7 = r0.L$0;
        r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7;
        r8 = r13 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x00c3, all -> 0x00bf }
        if (r8 != 0) goto L_0x008c;
    L_0x0086:
        r9 = r1;
        r1 = r11;
        r11 = r2;
        r2 = r9;
        goto L_0x011a;
    L_0x008c:
        r13 = (kotlin.Result.Failure) r13;	 Catch:{ Throwable -> 0x00c3, all -> 0x00bf }
        r10 = r13.exception;	 Catch:{ Throwable -> 0x00c3, all -> 0x00bf }
        throw r10;	 Catch:{ Throwable -> 0x00c3, all -> 0x00bf }
    L_0x0091:
        r10 = r0.L$7;
        r10 = (kotlinx.coroutines.channels.ChannelIterator) r10;
        r11 = r0.L$6;
        r11 = (kotlinx.coroutines.channels.ReceiveChannel) r11;
        r12 = r0.L$5;
        r12 = (java.lang.Throwable) r12;
        r2 = r0.L$4;
        r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2;
        r4 = r0.L$3;
        r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4;
        r5 = r0.L$2;
        r5 = (kotlin.jvm.functions.Function1) r5;
        r6 = r0.L$1;
        r6 = (kotlinx.coroutines.channels.SendChannel) r6;
        r7 = r0.L$0;
        r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7;
        r8 = r13 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x00c3, all -> 0x00bf }
        if (r8 != 0) goto L_0x00ba;
    L_0x00b5:
        r9 = r1;
        r1 = r11;
        r11 = r2;
    L_0x00b8:
        r2 = r9;
        goto L_0x00f8;
    L_0x00ba:
        r13 = (kotlin.Result.Failure) r13;	 Catch:{ Throwable -> 0x00c3, all -> 0x00bf }
        r10 = r13.exception;	 Catch:{ Throwable -> 0x00c3, all -> 0x00bf }
        throw r10;	 Catch:{ Throwable -> 0x00c3, all -> 0x00bf }
    L_0x00bf:
        r10 = move-exception;
        r11 = r2;
        goto L_0x0160;
    L_0x00c3:
        r10 = move-exception;
        r12 = r10;
        r10 = r2;
        goto L_0x015d;
    L_0x00c8:
        r2 = r13 instanceof kotlin.Result.Failure;
        if (r2 != 0) goto L_0x016a;
    L_0x00cc:
        r13 = 0;
        r13 = (java.lang.Throwable) r13;
        r2 = r10.iterator();	 Catch:{ Throwable -> 0x015b, all -> 0x0155 }
        r4 = r10;
        r7 = r4;
        r6 = r11;
        r5 = r12;
        r12 = r13;
        r11 = r7;
        r13 = r11;
        r10 = r2;
    L_0x00db:
        r0.L$0 = r7;	 Catch:{ Throwable -> 0x0151, all -> 0x014f }
        r0.L$1 = r6;	 Catch:{ Throwable -> 0x0151, all -> 0x014f }
        r0.L$2 = r5;	 Catch:{ Throwable -> 0x0151, all -> 0x014f }
        r0.L$3 = r4;	 Catch:{ Throwable -> 0x0151, all -> 0x014f }
        r0.L$4 = r11;	 Catch:{ Throwable -> 0x0151, all -> 0x014f }
        r0.L$5 = r12;	 Catch:{ Throwable -> 0x0151, all -> 0x014f }
        r0.L$6 = r13;	 Catch:{ Throwable -> 0x0151, all -> 0x014f }
        r0.L$7 = r10;	 Catch:{ Throwable -> 0x0151, all -> 0x014f }
        r0.label = r3;	 Catch:{ Throwable -> 0x0151, all -> 0x014f }
        r2 = r10.hasNext(r0);	 Catch:{ Throwable -> 0x0151, all -> 0x014f }
        if (r2 != r1) goto L_0x00f4;
    L_0x00f3:
        return r1;
    L_0x00f4:
        r9 = r1;
        r1 = r13;
        r13 = r2;
        goto L_0x00b8;
    L_0x00f8:
        r13 = (java.lang.Boolean) r13;	 Catch:{ Throwable -> 0x0151, all -> 0x014f }
        r13 = r13.booleanValue();	 Catch:{ Throwable -> 0x0151, all -> 0x014f }
        if (r13 == 0) goto L_0x0143;
    L_0x0100:
        r0.L$0 = r7;	 Catch:{ Throwable -> 0x0151, all -> 0x014f }
        r0.L$1 = r6;	 Catch:{ Throwable -> 0x0151, all -> 0x014f }
        r0.L$2 = r5;	 Catch:{ Throwable -> 0x0151, all -> 0x014f }
        r0.L$3 = r4;	 Catch:{ Throwable -> 0x0151, all -> 0x014f }
        r0.L$4 = r11;	 Catch:{ Throwable -> 0x0151, all -> 0x014f }
        r0.L$5 = r12;	 Catch:{ Throwable -> 0x0151, all -> 0x014f }
        r0.L$6 = r1;	 Catch:{ Throwable -> 0x0151, all -> 0x014f }
        r0.L$7 = r10;	 Catch:{ Throwable -> 0x0151, all -> 0x014f }
        r13 = 2;
        r0.label = r13;	 Catch:{ Throwable -> 0x0151, all -> 0x014f }
        r13 = r10.next(r0);	 Catch:{ Throwable -> 0x0151, all -> 0x014f }
        if (r13 != r2) goto L_0x011a;
    L_0x0119:
        return r2;
    L_0x011a:
        r8 = r5.invoke(r13);	 Catch:{ Throwable -> 0x0151, all -> 0x014f }
        if (r8 == 0) goto L_0x0140;
    L_0x0120:
        r0.L$0 = r7;	 Catch:{ Throwable -> 0x0151, all -> 0x014f }
        r0.L$1 = r6;	 Catch:{ Throwable -> 0x0151, all -> 0x014f }
        r0.L$2 = r5;	 Catch:{ Throwable -> 0x0151, all -> 0x014f }
        r0.L$3 = r4;	 Catch:{ Throwable -> 0x0151, all -> 0x014f }
        r0.L$4 = r11;	 Catch:{ Throwable -> 0x0151, all -> 0x014f }
        r0.L$5 = r12;	 Catch:{ Throwable -> 0x0151, all -> 0x014f }
        r0.L$6 = r1;	 Catch:{ Throwable -> 0x0151, all -> 0x014f }
        r0.L$7 = r10;	 Catch:{ Throwable -> 0x0151, all -> 0x014f }
        r0.L$8 = r13;	 Catch:{ Throwable -> 0x0151, all -> 0x014f }
        r0.L$9 = r13;	 Catch:{ Throwable -> 0x0151, all -> 0x014f }
        r0.L$10 = r8;	 Catch:{ Throwable -> 0x0151, all -> 0x014f }
        r13 = 3;
        r0.label = r13;	 Catch:{ Throwable -> 0x0151, all -> 0x014f }
        r13 = r6.send(r8, r0);	 Catch:{ Throwable -> 0x0151, all -> 0x014f }
        if (r13 != r2) goto L_0x0140;
    L_0x013f:
        return r2;
    L_0x0140:
        r13 = r1;
        r1 = r2;
        goto L_0x00db;
    L_0x0143:
        r10 = kotlin.Unit.INSTANCE;	 Catch:{ Throwable -> 0x0151, all -> 0x014f }
        kotlin.jvm.internal.InlineMarker.finallyStart(r3);
        r11.cancel(r12);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r3);
        return r6;
    L_0x014f:
        r10 = move-exception;
        goto L_0x0160;
    L_0x0151:
        r10 = move-exception;
        r12 = r10;
        r10 = r11;
        goto L_0x015d;
    L_0x0155:
        r11 = move-exception;
        r12 = r13;
    L_0x0157:
        r9 = r11;
        r11 = r10;
        r10 = r9;
        goto L_0x0160;
    L_0x015b:
        r11 = move-exception;
        r12 = r11;
    L_0x015d:
        throw r12;	 Catch:{ all -> 0x015e }
    L_0x015e:
        r11 = move-exception;
        goto L_0x0157;
    L_0x0160:
        kotlin.jvm.internal.InlineMarker.finallyStart(r3);
        r11.cancel(r12);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r3);
        throw r10;
    L_0x016a:
        r13 = (kotlin.Result.Failure) r13;
        r10 = r13.exception;
        throw r10;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.mapNotNullTo(kotlinx.coroutines.channels.ReceiveChannel, kotlinx.coroutines.channels.SendChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @org.jetbrains.annotations.Nullable
    @kotlinx.coroutines.ObsoleteCoroutinesApi
    public static final <E, R, C extends java.util.Collection<? super R>> java.lang.Object mapTo(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r10, @org.jetbrains.annotations.NotNull C r11, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super E, ? extends R> r12, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super C> r13) {
        /*
        r0 = r13 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$mapTo$1;
        if (r0 == 0) goto L_0x0014;
    L_0x0004:
        r0 = r13;
        r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$mapTo$1) r0;
        r1 = r0.label;
        r2 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r1 = r1 & r2;
        if (r1 == 0) goto L_0x0014;
    L_0x000e:
        r13 = r0.label;
        r13 = r13 - r2;
        r0.label = r13;
        goto L_0x0019;
    L_0x0014:
        r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$mapTo$1;
        r0.<init>(r13);
    L_0x0019:
        r13 = r0.result;
        r1 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        r2 = r0.label;
        r3 = 1;
        switch(r2) {
            case 0: goto L_0x008d;
            case 1: goto L_0x005b;
            case 2: goto L_0x002d;
            default: goto L_0x0025;
        };
    L_0x0025:
        r10 = new java.lang.IllegalStateException;
        r11 = "call to 'resume' before 'invoke' with coroutine";
        r10.<init>(r11);
        throw r10;
    L_0x002d:
        r10 = r0.L$7;
        r10 = (kotlinx.coroutines.channels.ChannelIterator) r10;
        r11 = r0.L$6;
        r11 = (kotlinx.coroutines.channels.ReceiveChannel) r11;
        r12 = r0.L$5;
        r12 = (java.lang.Throwable) r12;
        r2 = r0.L$4;
        r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2;
        r4 = r0.L$3;
        r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4;
        r5 = r0.L$2;
        r5 = (kotlin.jvm.functions.Function1) r5;
        r6 = r0.L$1;
        r6 = (java.util.Collection) r6;
        r7 = r0.L$0;
        r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7;
        r8 = r13 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        if (r8 != 0) goto L_0x0056;
    L_0x0051:
        r9 = r2;
        r2 = r10;
        r10 = r9;
        goto L_0x00dc;
    L_0x0056:
        r13 = (kotlin.Result.Failure) r13;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r10 = r13.exception;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        throw r10;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
    L_0x005b:
        r10 = r0.L$7;
        r10 = (kotlinx.coroutines.channels.ChannelIterator) r10;
        r11 = r0.L$6;
        r11 = (kotlinx.coroutines.channels.ReceiveChannel) r11;
        r12 = r0.L$5;
        r12 = (java.lang.Throwable) r12;
        r2 = r0.L$4;
        r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2;
        r4 = r0.L$3;
        r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4;
        r5 = r0.L$2;
        r5 = (kotlin.jvm.functions.Function1) r5;
        r6 = r0.L$1;
        r6 = (java.util.Collection) r6;
        r7 = r0.L$0;
        r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7;
        r8 = r13 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        if (r8 != 0) goto L_0x0080;
    L_0x007f:
        goto L_0x00ba;
    L_0x0080:
        r13 = (kotlin.Result.Failure) r13;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r10 = r13.exception;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        throw r10;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
    L_0x0085:
        r10 = move-exception;
        goto L_0x00fb;
    L_0x0088:
        r10 = move-exception;
        r12 = r10;
        r10 = r2;
        goto L_0x00f7;
    L_0x008d:
        r2 = r13 instanceof kotlin.Result.Failure;
        if (r2 != 0) goto L_0x0105;
    L_0x0091:
        r13 = 0;
        r13 = (java.lang.Throwable) r13;
        r2 = r10.iterator();	 Catch:{ Throwable -> 0x00f5, all -> 0x00f0 }
        r4 = r10;
        r7 = r4;
        r6 = r11;
        r5 = r12;
        r12 = r13;
        r11 = r7;
    L_0x009e:
        r0.L$0 = r7;	 Catch:{ Throwable -> 0x00f5 }
        r0.L$1 = r6;	 Catch:{ Throwable -> 0x00f5 }
        r0.L$2 = r5;	 Catch:{ Throwable -> 0x00f5 }
        r0.L$3 = r4;	 Catch:{ Throwable -> 0x00f5 }
        r0.L$4 = r10;	 Catch:{ Throwable -> 0x00f5 }
        r0.L$5 = r12;	 Catch:{ Throwable -> 0x00f5 }
        r0.L$6 = r11;	 Catch:{ Throwable -> 0x00f5 }
        r0.L$7 = r2;	 Catch:{ Throwable -> 0x00f5 }
        r0.label = r3;	 Catch:{ Throwable -> 0x00f5 }
        r13 = r2.hasNext(r0);	 Catch:{ Throwable -> 0x00f5 }
        if (r13 != r1) goto L_0x00b7;
    L_0x00b6:
        return r1;
    L_0x00b7:
        r9 = r2;
        r2 = r10;
        r10 = r9;
    L_0x00ba:
        r13 = (java.lang.Boolean) r13;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r13 = r13.booleanValue();	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        if (r13 == 0) goto L_0x00e4;
    L_0x00c2:
        r0.L$0 = r7;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r0.L$1 = r6;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r0.L$2 = r5;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r0.L$3 = r4;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r0.L$4 = r2;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r0.L$5 = r12;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r0.L$6 = r11;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r0.L$7 = r10;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r13 = 2;
        r0.label = r13;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r13 = r10.next(r0);	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        if (r13 != r1) goto L_0x0051;
    L_0x00db:
        return r1;
    L_0x00dc:
        r13 = r5.invoke(r13);	 Catch:{ Throwable -> 0x00f5 }
        r6.add(r13);	 Catch:{ Throwable -> 0x00f5 }
        goto L_0x009e;
    L_0x00e4:
        r10 = kotlin.Unit.INSTANCE;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        kotlin.jvm.internal.InlineMarker.finallyStart(r3);
        r2.cancel(r12);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r3);
        return r6;
    L_0x00f0:
        r11 = move-exception;
        r2 = r10;
        r10 = r11;
        r12 = r13;
        goto L_0x00fb;
    L_0x00f5:
        r11 = move-exception;
        r12 = r11;
    L_0x00f7:
        throw r12;	 Catch:{ all -> 0x00f8 }
    L_0x00f8:
        r11 = move-exception;
        r2 = r10;
        r10 = r11;
    L_0x00fb:
        kotlin.jvm.internal.InlineMarker.finallyStart(r3);
        r2.cancel(r12);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r3);
        throw r10;
    L_0x0105:
        r13 = (kotlin.Result.Failure) r13;
        r10 = r13.exception;
        throw r10;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.mapTo(kotlinx.coroutines.channels.ReceiveChannel, java.util.Collection, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @org.jetbrains.annotations.Nullable
    @kotlinx.coroutines.ObsoleteCoroutinesApi
    public static final <E, R, C extends kotlinx.coroutines.channels.SendChannel<? super R>> java.lang.Object mapTo(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r11, @org.jetbrains.annotations.NotNull C r12, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super E, ? extends R> r13, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super C> r14) {
        /*
        r0 = r14 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$mapTo$3;
        if (r0 == 0) goto L_0x0014;
    L_0x0004:
        r0 = r14;
        r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$mapTo$3) r0;
        r1 = r0.label;
        r2 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r1 = r1 & r2;
        if (r1 == 0) goto L_0x0014;
    L_0x000e:
        r14 = r0.label;
        r14 = r14 - r2;
        r0.label = r14;
        goto L_0x0019;
    L_0x0014:
        r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$mapTo$3;
        r0.<init>(r14);
    L_0x0019:
        r14 = r0.result;
        r1 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        r2 = r0.label;
        r3 = 1;
        switch(r2) {
            case 0: goto L_0x00c5;
            case 1: goto L_0x0093;
            case 2: goto L_0x0064;
            case 3: goto L_0x002d;
            default: goto L_0x0025;
        };
    L_0x0025:
        r11 = new java.lang.IllegalStateException;
        r12 = "call to 'resume' before 'invoke' with coroutine";
        r11.<init>(r12);
        throw r11;
    L_0x002d:
        r11 = r0.L$9;
        r11 = r0.L$8;
        r11 = r0.L$7;
        r11 = (kotlinx.coroutines.channels.ChannelIterator) r11;
        r12 = r0.L$6;
        r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12;
        r13 = r0.L$5;
        r13 = (java.lang.Throwable) r13;
        r2 = r0.L$4;
        r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2;
        r4 = r0.L$3;
        r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4;
        r5 = r0.L$2;
        r5 = (kotlin.jvm.functions.Function1) r5;
        r6 = r0.L$1;
        r6 = (kotlinx.coroutines.channels.SendChannel) r6;
        r7 = r0.L$0;
        r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7;
        r8 = r14 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x00c0, all -> 0x00bd }
        if (r8 != 0) goto L_0x005f;
    L_0x0055:
        r14 = r5;
        r5 = r12;
        r12 = r7;
        r9 = r4;
        r4 = r13;
        r13 = r6;
        r6 = r1;
        r1 = r9;
        goto L_0x00d9;
    L_0x005f:
        r14 = (kotlin.Result.Failure) r14;	 Catch:{ Throwable -> 0x00c0, all -> 0x00bd }
        r11 = r14.exception;	 Catch:{ Throwable -> 0x00c0, all -> 0x00bd }
        throw r11;	 Catch:{ Throwable -> 0x00c0, all -> 0x00bd }
    L_0x0064:
        r11 = r0.L$7;
        r11 = (kotlinx.coroutines.channels.ChannelIterator) r11;
        r12 = r0.L$6;
        r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12;
        r13 = r0.L$5;
        r13 = (java.lang.Throwable) r13;
        r2 = r0.L$4;
        r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2;
        r4 = r0.L$3;
        r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4;
        r5 = r0.L$2;
        r5 = (kotlin.jvm.functions.Function1) r5;
        r6 = r0.L$1;
        r6 = (kotlinx.coroutines.channels.SendChannel) r6;
        r7 = r0.L$0;
        r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7;
        r8 = r14 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x00c0, all -> 0x00bd }
        if (r8 != 0) goto L_0x008e;
    L_0x0088:
        r9 = r1;
        r1 = r11;
        r11 = r2;
        r2 = r9;
        goto L_0x011e;
    L_0x008e:
        r14 = (kotlin.Result.Failure) r14;	 Catch:{ Throwable -> 0x00c0, all -> 0x00bd }
        r11 = r14.exception;	 Catch:{ Throwable -> 0x00c0, all -> 0x00bd }
        throw r11;	 Catch:{ Throwable -> 0x00c0, all -> 0x00bd }
    L_0x0093:
        r11 = r0.L$7;
        r11 = (kotlinx.coroutines.channels.ChannelIterator) r11;
        r12 = r0.L$6;
        r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12;
        r13 = r0.L$5;
        r13 = (java.lang.Throwable) r13;
        r2 = r0.L$4;
        r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2;
        r4 = r0.L$3;
        r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4;
        r5 = r0.L$2;
        r5 = (kotlin.jvm.functions.Function1) r5;
        r6 = r0.L$1;
        r6 = (kotlinx.coroutines.channels.SendChannel) r6;
        r7 = r0.L$0;
        r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7;
        r8 = r14 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x00c0, all -> 0x00bd }
        if (r8 != 0) goto L_0x00b8;
    L_0x00b7:
        goto L_0x00fc;
    L_0x00b8:
        r14 = (kotlin.Result.Failure) r14;	 Catch:{ Throwable -> 0x00c0, all -> 0x00bd }
        r11 = r14.exception;	 Catch:{ Throwable -> 0x00c0, all -> 0x00bd }
        throw r11;	 Catch:{ Throwable -> 0x00c0, all -> 0x00bd }
    L_0x00bd:
        r11 = move-exception;
        goto L_0x0165;
    L_0x00c0:
        r11 = move-exception;
        r13 = r11;
        r11 = r2;
        goto L_0x0161;
    L_0x00c5:
        r2 = r14 instanceof kotlin.Result.Failure;
        if (r2 != 0) goto L_0x016f;
    L_0x00c9:
        r14 = 0;
        r14 = (java.lang.Throwable) r14;
        r2 = r11.iterator();	 Catch:{ Throwable -> 0x015f, all -> 0x015a }
        r5 = r11;
        r4 = r14;
        r6 = r1;
        r1 = r5;
        r14 = r13;
        r11 = r2;
        r2 = r1;
        r13 = r12;
        r12 = r2;
    L_0x00d9:
        r0.L$0 = r12;	 Catch:{ Throwable -> 0x00c0, all -> 0x0157 }
        r0.L$1 = r13;	 Catch:{ Throwable -> 0x00c0, all -> 0x0157 }
        r0.L$2 = r14;	 Catch:{ Throwable -> 0x00c0, all -> 0x0157 }
        r0.L$3 = r1;	 Catch:{ Throwable -> 0x00c0, all -> 0x0157 }
        r0.L$4 = r2;	 Catch:{ Throwable -> 0x00c0, all -> 0x0157 }
        r0.L$5 = r4;	 Catch:{ Throwable -> 0x00c0, all -> 0x0157 }
        r0.L$6 = r5;	 Catch:{ Throwable -> 0x00c0, all -> 0x0157 }
        r0.L$7 = r11;	 Catch:{ Throwable -> 0x00c0, all -> 0x0157 }
        r0.label = r3;	 Catch:{ Throwable -> 0x00c0, all -> 0x0157 }
        r7 = r11.hasNext(r0);	 Catch:{ Throwable -> 0x00c0, all -> 0x0157 }
        if (r7 != r6) goto L_0x00f2;
    L_0x00f1:
        return r6;
    L_0x00f2:
        r9 = r7;
        r7 = r12;
        r12 = r5;
        r5 = r14;
        r14 = r9;
        r10 = r6;
        r6 = r13;
        r13 = r4;
        r4 = r1;
        r1 = r10;
    L_0x00fc:
        r14 = (java.lang.Boolean) r14;	 Catch:{ Throwable -> 0x00c0, all -> 0x00bd }
        r14 = r14.booleanValue();	 Catch:{ Throwable -> 0x00c0, all -> 0x00bd }
        if (r14 == 0) goto L_0x014b;
    L_0x0104:
        r0.L$0 = r7;	 Catch:{ Throwable -> 0x00c0, all -> 0x00bd }
        r0.L$1 = r6;	 Catch:{ Throwable -> 0x00c0, all -> 0x00bd }
        r0.L$2 = r5;	 Catch:{ Throwable -> 0x00c0, all -> 0x00bd }
        r0.L$3 = r4;	 Catch:{ Throwable -> 0x00c0, all -> 0x00bd }
        r0.L$4 = r2;	 Catch:{ Throwable -> 0x00c0, all -> 0x00bd }
        r0.L$5 = r13;	 Catch:{ Throwable -> 0x00c0, all -> 0x00bd }
        r0.L$6 = r12;	 Catch:{ Throwable -> 0x00c0, all -> 0x00bd }
        r0.L$7 = r11;	 Catch:{ Throwable -> 0x00c0, all -> 0x00bd }
        r14 = 2;
        r0.label = r14;	 Catch:{ Throwable -> 0x00c0, all -> 0x00bd }
        r14 = r11.next(r0);	 Catch:{ Throwable -> 0x00c0, all -> 0x00bd }
        if (r14 != r1) goto L_0x0088;
    L_0x011d:
        return r1;
    L_0x011e:
        r8 = r5.invoke(r14);	 Catch:{ Throwable -> 0x015f }
        r0.L$0 = r7;	 Catch:{ Throwable -> 0x015f }
        r0.L$1 = r6;	 Catch:{ Throwable -> 0x015f }
        r0.L$2 = r5;	 Catch:{ Throwable -> 0x015f }
        r0.L$3 = r4;	 Catch:{ Throwable -> 0x015f }
        r0.L$4 = r11;	 Catch:{ Throwable -> 0x015f }
        r0.L$5 = r13;	 Catch:{ Throwable -> 0x015f }
        r0.L$6 = r12;	 Catch:{ Throwable -> 0x015f }
        r0.L$7 = r1;	 Catch:{ Throwable -> 0x015f }
        r0.L$8 = r14;	 Catch:{ Throwable -> 0x015f }
        r0.L$9 = r14;	 Catch:{ Throwable -> 0x015f }
        r14 = 3;
        r0.label = r14;	 Catch:{ Throwable -> 0x015f }
        r14 = r6.send(r8, r0);	 Catch:{ Throwable -> 0x015f }
        if (r14 != r2) goto L_0x0140;
    L_0x013f:
        return r2;
    L_0x0140:
        r14 = r5;
        r5 = r12;
        r12 = r7;
        r9 = r2;
        r2 = r11;
        r11 = r1;
        r1 = r4;
        r4 = r13;
        r13 = r6;
        r6 = r9;
        goto L_0x00d9;
    L_0x014b:
        r11 = kotlin.Unit.INSTANCE;	 Catch:{ Throwable -> 0x00c0, all -> 0x00bd }
        kotlin.jvm.internal.InlineMarker.finallyStart(r3);
        r2.cancel(r13);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r3);
        return r6;
    L_0x0157:
        r11 = move-exception;
        r13 = r4;
        goto L_0x0165;
    L_0x015a:
        r12 = move-exception;
        r2 = r11;
        r11 = r12;
        r13 = r14;
        goto L_0x0165;
    L_0x015f:
        r12 = move-exception;
        r13 = r12;
    L_0x0161:
        throw r13;	 Catch:{ all -> 0x0162 }
    L_0x0162:
        r12 = move-exception;
        r2 = r11;
        r11 = r12;
    L_0x0165:
        kotlin.jvm.internal.InlineMarker.finallyStart(r3);
        r2.cancel(r13);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r3);
        throw r11;
    L_0x016f:
        r14 = (kotlin.Result.Failure) r14;
        r11 = r14.exception;
        throw r11;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.mapTo(kotlinx.coroutines.channels.ReceiveChannel, kotlinx.coroutines.channels.SendChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @NotNull
    @ObsoleteCoroutinesApi
    public static /* synthetic */ ReceiveChannel withIndex$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return ChannelsKt.withIndex(receiveChannel, coroutineContext);
    }

    @NotNull
    @ObsoleteCoroutinesApi
    public static final <E> ReceiveChannel<IndexedValue<E>> withIndex(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull CoroutineContext coroutineContext) {
        Intrinsics.checkParameterIsNotNull(receiveChannel, "receiver$0");
        Intrinsics.checkParameterIsNotNull(coroutineContext, "context");
        return ProduceKt.produce$default(GlobalScope.INSTANCE, coroutineContext, 0, ChannelsKt.consumes(receiveChannel), new ChannelsKt__Channels_commonKt$withIndex$1(receiveChannel, null), 2, null);
    }

    @NotNull
    @ObsoleteCoroutinesApi
    public static final <E> ReceiveChannel<E> distinct(@NotNull ReceiveChannel<? extends E> receiveChannel) {
        Intrinsics.checkParameterIsNotNull(receiveChannel, "receiver$0");
        return distinctBy$default(receiveChannel, null, new ChannelsKt__Channels_commonKt$distinct$1(null), 1, null);
    }

    @NotNull
    @ObsoleteCoroutinesApi
    public static /* synthetic */ ReceiveChannel distinctBy$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return ChannelsKt.distinctBy(receiveChannel, coroutineContext, function2);
    }

    @NotNull
    @ObsoleteCoroutinesApi
    public static final <E, K> ReceiveChannel<E> distinctBy(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull CoroutineContext coroutineContext, @NotNull Function2<? super E, ? super Continuation<? super K>, ? extends Object> function2) {
        Intrinsics.checkParameterIsNotNull(receiveChannel, "receiver$0");
        Intrinsics.checkParameterIsNotNull(coroutineContext, "context");
        Intrinsics.checkParameterIsNotNull(function2, "selector");
        return ProduceKt.produce$default(GlobalScope.INSTANCE, coroutineContext, 0, ChannelsKt.consumes(receiveChannel), new ChannelsKt__Channels_commonKt$distinctBy$1(receiveChannel, function2, null), 2, null);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    public static final <E> Object toMutableSet(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Continuation<? super Set<E>> continuation) {
        return ChannelsKt.toCollection(receiveChannel, new LinkedHashSet(), continuation);
    }

    @org.jetbrains.annotations.Nullable
    @kotlinx.coroutines.ObsoleteCoroutinesApi
    public static final <E> java.lang.Object all(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r11, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super E, java.lang.Boolean> r12, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Boolean> r13) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:37)
	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:61)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1603198149.run(Unknown Source)
*/
        /*
        r0 = r13 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$all$1;
        if (r0 == 0) goto L_0x0014;
    L_0x0004:
        r0 = r13;
        r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$all$1) r0;
        r1 = r0.label;
        r2 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r1 = r1 & r2;
        if (r1 == 0) goto L_0x0014;
    L_0x000e:
        r13 = r0.label;
        r13 = r13 - r2;
        r0.label = r13;
        goto L_0x0019;
    L_0x0014:
        r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$all$1;
        r0.<init>(r13);
    L_0x0019:
        r13 = r0.result;
        r1 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        r2 = r0.label;
        r3 = 2;
        r4 = 1;
        switch(r2) {
            case 0: goto L_0x0088;
            case 1: goto L_0x005a;
            case 2: goto L_0x002e;
            default: goto L_0x0026;
        };
    L_0x0026:
        r11 = new java.lang.IllegalStateException;
        r12 = "call to 'resume' before 'invoke' with coroutine";
        r11.<init>(r12);
        throw r11;
    L_0x002e:
        r11 = r0.L$6;
        r11 = (kotlinx.coroutines.channels.ChannelIterator) r11;
        r12 = r0.L$5;
        r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12;
        r2 = r0.L$4;
        r2 = (java.lang.Throwable) r2;
        r5 = r0.L$3;
        r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5;
        r6 = r0.L$2;
        r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6;
        r7 = r0.L$1;
        r7 = (kotlin.jvm.functions.Function1) r7;
        r8 = r0.L$0;
        r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8;
        r9 = r13 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
        if (r9 != 0) goto L_0x0055;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
    L_0x004e:
        r10 = r12;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
        r12 = r11;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
        r11 = r5;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
        r5 = r1;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
        r1 = r10;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
        goto L_0x00d2;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
    L_0x0055:
        r13 = (kotlin.Result.Failure) r13;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
        r11 = r13.exception;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
        throw r11;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
    L_0x005a:
        r11 = r0.L$6;
        r11 = (kotlinx.coroutines.channels.ChannelIterator) r11;
        r12 = r0.L$5;
        r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12;
        r2 = r0.L$4;
        r2 = (java.lang.Throwable) r2;
        r5 = r0.L$3;
        r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5;
        r6 = r0.L$2;
        r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6;
        r7 = r0.L$1;
        r7 = (kotlin.jvm.functions.Function1) r7;
        r8 = r0.L$0;
        r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8;
        r9 = r13 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
        if (r9 != 0) goto L_0x007b;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
    L_0x007a:
        goto L_0x00b3;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
    L_0x007b:
        r13 = (kotlin.Result.Failure) r13;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
        r11 = r13.exception;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
        throw r11;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
    L_0x0080:
        r11 = move-exception;
        goto L_0x0108;
    L_0x0083:
        r11 = move-exception;
        r2 = r11;
        r11 = r5;
        goto L_0x0107;
    L_0x0088:
        r2 = r13 instanceof kotlin.Result.Failure;
        if (r2 != 0) goto L_0x0112;
    L_0x008c:
        r13 = 0;
        r2 = r13;
        r2 = (java.lang.Throwable) r2;
        r13 = r11.iterator();	 Catch:{ Throwable -> 0x0105 }
        r6 = r11;	 Catch:{ Throwable -> 0x0105 }
        r8 = r6;	 Catch:{ Throwable -> 0x0105 }
        r7 = r12;	 Catch:{ Throwable -> 0x0105 }
        r12 = r8;	 Catch:{ Throwable -> 0x0105 }
    L_0x0098:
        r0.L$0 = r8;	 Catch:{ Throwable -> 0x0105 }
        r0.L$1 = r7;	 Catch:{ Throwable -> 0x0105 }
        r0.L$2 = r6;	 Catch:{ Throwable -> 0x0105 }
        r0.L$3 = r11;	 Catch:{ Throwable -> 0x0105 }
        r0.L$4 = r2;	 Catch:{ Throwable -> 0x0105 }
        r0.L$5 = r12;	 Catch:{ Throwable -> 0x0105 }
        r0.L$6 = r13;	 Catch:{ Throwable -> 0x0105 }
        r0.label = r4;	 Catch:{ Throwable -> 0x0105 }
        r5 = r13.hasNext(r0);	 Catch:{ Throwable -> 0x0105 }
        if (r5 != r1) goto L_0x00af;
    L_0x00ae:
        return r1;
    L_0x00af:
        r10 = r5;
        r5 = r11;
        r11 = r13;
        r13 = r10;
    L_0x00b3:
        r13 = (java.lang.Boolean) r13;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
        r13 = r13.booleanValue();	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
        if (r13 == 0) goto L_0x00f1;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
    L_0x00bb:
        r0.L$0 = r8;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
        r0.L$1 = r7;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
        r0.L$2 = r6;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
        r0.L$3 = r5;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
        r0.L$4 = r2;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
        r0.L$5 = r12;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
        r0.L$6 = r11;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
        r0.label = r3;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
        r13 = r11.next(r0);	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
        if (r13 != r1) goto L_0x004e;
    L_0x00d1:
        return r1;
    L_0x00d2:
        r13 = r7.invoke(r13);	 Catch:{ Throwable -> 0x0105 }
        r13 = (java.lang.Boolean) r13;	 Catch:{ Throwable -> 0x0105 }
        r13 = r13.booleanValue();	 Catch:{ Throwable -> 0x0105 }
        if (r13 != 0) goto L_0x00ed;	 Catch:{ Throwable -> 0x0105 }
    L_0x00de:
        r12 = 0;	 Catch:{ Throwable -> 0x0105 }
        r12 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r12);	 Catch:{ Throwable -> 0x0105 }
        kotlin.jvm.internal.InlineMarker.finallyStart(r3);
        r11.cancel(r2);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r3);
        return r12;
    L_0x00ed:
        r13 = r12;
        r12 = r1;
        r1 = r5;
        goto L_0x0098;
    L_0x00f1:
        r11 = kotlin.Unit.INSTANCE;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
        kotlin.jvm.internal.InlineMarker.finallyStart(r4);
        r5.cancel(r2);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r4);
        r11 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r4);
        return r11;
    L_0x0101:
        r12 = move-exception;
        r5 = r11;
        r11 = r12;
        goto L_0x0108;
    L_0x0105:
        r12 = move-exception;
        r2 = r12;
    L_0x0107:
        throw r2;	 Catch:{ all -> 0x0101 }
    L_0x0108:
        kotlin.jvm.internal.InlineMarker.finallyStart(r4);
        r5.cancel(r2);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r4);
        throw r11;
    L_0x0112:
        r13 = (kotlin.Result.Failure) r13;
        r11 = r13.exception;
        throw r11;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.all(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @org.jetbrains.annotations.Nullable
    @kotlinx.coroutines.ObsoleteCoroutinesApi
    public static final <E> java.lang.Object any(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r5, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Boolean> r6) {
        /*
        r0 = r6 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$any$1;
        if (r0 == 0) goto L_0x0014;
    L_0x0004:
        r0 = r6;
        r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$any$1) r0;
        r1 = r0.label;
        r2 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r1 = r1 & r2;
        if (r1 == 0) goto L_0x0014;
    L_0x000e:
        r6 = r0.label;
        r6 = r6 - r2;
        r0.label = r6;
        goto L_0x0019;
    L_0x0014:
        r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$any$1;
        r0.<init>(r6);
    L_0x0019:
        r6 = r0.result;
        r1 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        r2 = r0.label;
        switch(r2) {
            case 0: goto L_0x004f;
            case 1: goto L_0x002c;
            default: goto L_0x0024;
        };
    L_0x0024:
        r5 = new java.lang.IllegalStateException;
        r6 = "call to 'resume' before 'invoke' with coroutine";
        r5.<init>(r6);
        throw r5;
    L_0x002c:
        r5 = r0.L$3;
        r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5;
        r5 = r0.L$2;
        r5 = (java.lang.Throwable) r5;
        r1 = r0.L$1;
        r1 = (kotlinx.coroutines.channels.ReceiveChannel) r1;
        r0 = r0.L$0;
        r0 = (kotlinx.coroutines.channels.ReceiveChannel) r0;
        r0 = r6 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x004d }
        if (r0 != 0) goto L_0x0043;
    L_0x0040:
        r0 = r5;
        r5 = r1;
        goto L_0x006f;
    L_0x0043:
        r6 = (kotlin.Result.Failure) r6;	 Catch:{ Throwable -> 0x004d }
        r6 = r6.exception;	 Catch:{ Throwable -> 0x004d }
        throw r6;	 Catch:{ Throwable -> 0x004d }
    L_0x0048:
        r6 = move-exception;
        r0 = r6;
        r6 = r5;
        r5 = r1;
        goto L_0x0079;
    L_0x004d:
        r5 = move-exception;
        goto L_0x0078;
    L_0x004f:
        r2 = r6 instanceof kotlin.Result.Failure;
        if (r2 != 0) goto L_0x007d;
    L_0x0053:
        r6 = 0;
        r6 = (java.lang.Throwable) r6;
        r2 = r5.iterator();	 Catch:{ Throwable -> 0x0075, all -> 0x0073 }
        r0.L$0 = r5;	 Catch:{ Throwable -> 0x0075, all -> 0x0073 }
        r0.L$1 = r5;	 Catch:{ Throwable -> 0x0075, all -> 0x0073 }
        r0.L$2 = r6;	 Catch:{ Throwable -> 0x0075, all -> 0x0073 }
        r0.L$3 = r5;	 Catch:{ Throwable -> 0x0075, all -> 0x0073 }
        r3 = 1;
        r0.label = r3;	 Catch:{ Throwable -> 0x0075, all -> 0x0073 }
        r0 = r2.hasNext(r0);	 Catch:{ Throwable -> 0x0075, all -> 0x0073 }
        if (r0 != r1) goto L_0x006c;
    L_0x006b:
        return r1;
    L_0x006c:
        r4 = r0;
        r0 = r6;
        r6 = r4;
    L_0x006f:
        r5.cancel(r0);
        return r6;
    L_0x0073:
        r0 = move-exception;
        goto L_0x0079;
    L_0x0075:
        r6 = move-exception;
        r1 = r5;
        r5 = r6;
    L_0x0078:
        throw r5;	 Catch:{ all -> 0x0048 }
    L_0x0079:
        r5.cancel(r6);
        throw r0;
    L_0x007d:
        r6 = (kotlin.Result.Failure) r6;
        r5 = r6.exception;
        throw r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.any(kotlinx.coroutines.channels.ReceiveChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @org.jetbrains.annotations.Nullable
    @kotlinx.coroutines.ObsoleteCoroutinesApi
    public static final <E> java.lang.Object any(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r11, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super E, java.lang.Boolean> r12, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Boolean> r13) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:37)
	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:61)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1603198149.run(Unknown Source)
*/
        /*
        r0 = r13 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$any$3;
        if (r0 == 0) goto L_0x0014;
    L_0x0004:
        r0 = r13;
        r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$any$3) r0;
        r1 = r0.label;
        r2 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r1 = r1 & r2;
        if (r1 == 0) goto L_0x0014;
    L_0x000e:
        r13 = r0.label;
        r13 = r13 - r2;
        r0.label = r13;
        goto L_0x0019;
    L_0x0014:
        r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$any$3;
        r0.<init>(r13);
    L_0x0019:
        r13 = r0.result;
        r1 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        r2 = r0.label;
        r3 = 2;
        r4 = 1;
        switch(r2) {
            case 0: goto L_0x0088;
            case 1: goto L_0x005a;
            case 2: goto L_0x002e;
            default: goto L_0x0026;
        };
    L_0x0026:
        r11 = new java.lang.IllegalStateException;
        r12 = "call to 'resume' before 'invoke' with coroutine";
        r11.<init>(r12);
        throw r11;
    L_0x002e:
        r11 = r0.L$6;
        r11 = (kotlinx.coroutines.channels.ChannelIterator) r11;
        r12 = r0.L$5;
        r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12;
        r2 = r0.L$4;
        r2 = (java.lang.Throwable) r2;
        r5 = r0.L$3;
        r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5;
        r6 = r0.L$2;
        r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6;
        r7 = r0.L$1;
        r7 = (kotlin.jvm.functions.Function1) r7;
        r8 = r0.L$0;
        r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8;
        r9 = r13 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
        if (r9 != 0) goto L_0x0055;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
    L_0x004e:
        r10 = r12;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
        r12 = r11;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
        r11 = r5;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
        r5 = r1;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
        r1 = r10;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
        goto L_0x00d2;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
    L_0x0055:
        r13 = (kotlin.Result.Failure) r13;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
        r11 = r13.exception;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
        throw r11;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
    L_0x005a:
        r11 = r0.L$6;
        r11 = (kotlinx.coroutines.channels.ChannelIterator) r11;
        r12 = r0.L$5;
        r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12;
        r2 = r0.L$4;
        r2 = (java.lang.Throwable) r2;
        r5 = r0.L$3;
        r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5;
        r6 = r0.L$2;
        r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6;
        r7 = r0.L$1;
        r7 = (kotlin.jvm.functions.Function1) r7;
        r8 = r0.L$0;
        r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8;
        r9 = r13 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
        if (r9 != 0) goto L_0x007b;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
    L_0x007a:
        goto L_0x00b3;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
    L_0x007b:
        r13 = (kotlin.Result.Failure) r13;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
        r11 = r13.exception;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
        throw r11;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
    L_0x0080:
        r11 = move-exception;
        goto L_0x0108;
    L_0x0083:
        r11 = move-exception;
        r2 = r11;
        r11 = r5;
        goto L_0x0107;
    L_0x0088:
        r2 = r13 instanceof kotlin.Result.Failure;
        if (r2 != 0) goto L_0x0112;
    L_0x008c:
        r13 = 0;
        r2 = r13;
        r2 = (java.lang.Throwable) r2;
        r13 = r11.iterator();	 Catch:{ Throwable -> 0x0105 }
        r6 = r11;	 Catch:{ Throwable -> 0x0105 }
        r8 = r6;	 Catch:{ Throwable -> 0x0105 }
        r7 = r12;	 Catch:{ Throwable -> 0x0105 }
        r12 = r8;	 Catch:{ Throwable -> 0x0105 }
    L_0x0098:
        r0.L$0 = r8;	 Catch:{ Throwable -> 0x0105 }
        r0.L$1 = r7;	 Catch:{ Throwable -> 0x0105 }
        r0.L$2 = r6;	 Catch:{ Throwable -> 0x0105 }
        r0.L$3 = r11;	 Catch:{ Throwable -> 0x0105 }
        r0.L$4 = r2;	 Catch:{ Throwable -> 0x0105 }
        r0.L$5 = r12;	 Catch:{ Throwable -> 0x0105 }
        r0.L$6 = r13;	 Catch:{ Throwable -> 0x0105 }
        r0.label = r4;	 Catch:{ Throwable -> 0x0105 }
        r5 = r13.hasNext(r0);	 Catch:{ Throwable -> 0x0105 }
        if (r5 != r1) goto L_0x00af;
    L_0x00ae:
        return r1;
    L_0x00af:
        r10 = r5;
        r5 = r11;
        r11 = r13;
        r13 = r10;
    L_0x00b3:
        r13 = (java.lang.Boolean) r13;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
        r13 = r13.booleanValue();	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
        if (r13 == 0) goto L_0x00f0;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
    L_0x00bb:
        r0.L$0 = r8;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
        r0.L$1 = r7;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
        r0.L$2 = r6;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
        r0.L$3 = r5;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
        r0.L$4 = r2;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
        r0.L$5 = r12;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
        r0.L$6 = r11;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
        r0.label = r3;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
        r13 = r11.next(r0);	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
        if (r13 != r1) goto L_0x004e;
    L_0x00d1:
        return r1;
    L_0x00d2:
        r13 = r7.invoke(r13);	 Catch:{ Throwable -> 0x0105 }
        r13 = (java.lang.Boolean) r13;	 Catch:{ Throwable -> 0x0105 }
        r13 = r13.booleanValue();	 Catch:{ Throwable -> 0x0105 }
        if (r13 == 0) goto L_0x00ec;	 Catch:{ Throwable -> 0x0105 }
    L_0x00de:
        r12 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r4);	 Catch:{ Throwable -> 0x0105 }
        kotlin.jvm.internal.InlineMarker.finallyStart(r3);
        r11.cancel(r2);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r3);
        return r12;
    L_0x00ec:
        r13 = r12;
        r12 = r1;
        r1 = r5;
        goto L_0x0098;
    L_0x00f0:
        r11 = kotlin.Unit.INSTANCE;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
        kotlin.jvm.internal.InlineMarker.finallyStart(r4);
        r5.cancel(r2);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r4);
        r11 = 0;
        r11 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r11);
        return r11;
    L_0x0101:
        r12 = move-exception;
        r5 = r11;
        r11 = r12;
        goto L_0x0108;
    L_0x0105:
        r12 = move-exception;
        r2 = r12;
    L_0x0107:
        throw r2;	 Catch:{ all -> 0x0101 }
    L_0x0108:
        kotlin.jvm.internal.InlineMarker.finallyStart(r4);
        r5.cancel(r2);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r4);
        throw r11;
    L_0x0112:
        r13 = (kotlin.Result.Failure) r13;
        r11 = r13.exception;
        throw r11;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.any(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @org.jetbrains.annotations.Nullable
    @kotlinx.coroutines.ObsoleteCoroutinesApi
    public static final <E> java.lang.Object count(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r12, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Integer> r13) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:37)
	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:61)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1603198149.run(Unknown Source)
*/
        /*
        r0 = r13 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$count$1;
        if (r0 == 0) goto L_0x0014;
    L_0x0004:
        r0 = r13;
        r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$count$1) r0;
        r1 = r0.label;
        r2 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r1 = r1 & r2;
        if (r1 == 0) goto L_0x0014;
    L_0x000e:
        r13 = r0.label;
        r13 = r13 - r2;
        r0.label = r13;
        goto L_0x0019;
    L_0x0014:
        r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$count$1;
        r0.<init>(r13);
    L_0x0019:
        r13 = r0.result;
        r1 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        r2 = r0.label;
        r3 = 1;
        switch(r2) {
            case 0: goto L_0x008b;
            case 1: goto L_0x005a;
            case 2: goto L_0x002d;
            default: goto L_0x0025;
        };
    L_0x0025:
        r12 = new java.lang.IllegalStateException;
        r13 = "call to 'resume' before 'invoke' with coroutine";
        r12.<init>(r13);
        throw r12;
    L_0x002d:
        r12 = r0.L$6;
        r12 = (kotlinx.coroutines.channels.ChannelIterator) r12;
        r2 = r0.L$5;
        r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2;
        r4 = r0.L$4;
        r4 = (java.lang.Throwable) r4;
        r5 = r0.L$3;
        r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5;
        r6 = r0.L$2;
        r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6;
        r7 = r0.L$1;
        r7 = (kotlin.jvm.internal.Ref.IntRef) r7;
        r8 = r0.L$0;
        r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8;
        r9 = r13 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x0086, all -> 0x0083 }
        if (r9 != 0) goto L_0x0055;	 Catch:{ Throwable -> 0x0086, all -> 0x0083 }
    L_0x004d:
        r13 = r7;	 Catch:{ Throwable -> 0x0086, all -> 0x0083 }
        r10 = r2;	 Catch:{ Throwable -> 0x0086, all -> 0x0083 }
        r2 = r12;	 Catch:{ Throwable -> 0x0086, all -> 0x0083 }
        r12 = r5;	 Catch:{ Throwable -> 0x0086, all -> 0x0083 }
        r5 = r1;	 Catch:{ Throwable -> 0x0086, all -> 0x0083 }
        r1 = r10;	 Catch:{ Throwable -> 0x0086, all -> 0x0083 }
        goto L_0x00e6;	 Catch:{ Throwable -> 0x0086, all -> 0x0083 }
    L_0x0055:
        r13 = (kotlin.Result.Failure) r13;	 Catch:{ Throwable -> 0x0086, all -> 0x0083 }
        r12 = r13.exception;	 Catch:{ Throwable -> 0x0086, all -> 0x0083 }
        throw r12;	 Catch:{ Throwable -> 0x0086, all -> 0x0083 }
    L_0x005a:
        r12 = r0.L$6;
        r12 = (kotlinx.coroutines.channels.ChannelIterator) r12;
        r2 = r0.L$5;
        r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2;
        r4 = r0.L$4;
        r4 = (java.lang.Throwable) r4;
        r5 = r0.L$3;
        r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5;
        r6 = r0.L$2;
        r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6;
        r7 = r0.L$1;
        r7 = (kotlin.jvm.internal.Ref.IntRef) r7;
        r8 = r0.L$0;
        r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8;
        r9 = r13 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x0086, all -> 0x0083 }
        if (r9 != 0) goto L_0x007e;	 Catch:{ Throwable -> 0x0086, all -> 0x0083 }
    L_0x007a:
        r10 = r2;	 Catch:{ Throwable -> 0x0086, all -> 0x0083 }
        r2 = r1;	 Catch:{ Throwable -> 0x0086, all -> 0x0083 }
        r1 = r10;	 Catch:{ Throwable -> 0x0086, all -> 0x0083 }
        goto L_0x00c1;	 Catch:{ Throwable -> 0x0086, all -> 0x0083 }
    L_0x007e:
        r13 = (kotlin.Result.Failure) r13;	 Catch:{ Throwable -> 0x0086, all -> 0x0083 }
        r12 = r13.exception;	 Catch:{ Throwable -> 0x0086, all -> 0x0083 }
        throw r12;	 Catch:{ Throwable -> 0x0086, all -> 0x0083 }
    L_0x0083:
        r12 = move-exception;
        goto L_0x00ff;
    L_0x0086:
        r12 = move-exception;
        r4 = r12;
        r12 = r5;
        goto L_0x00fe;
    L_0x008b:
        r2 = r13 instanceof kotlin.Result.Failure;
        if (r2 != 0) goto L_0x0103;
    L_0x008f:
        r13 = new kotlin.jvm.internal.Ref$IntRef;
        r13.<init>();
        r2 = 0;
        r13.element = r2;
        r2 = 0;
        r4 = r2;
        r4 = (java.lang.Throwable) r4;
        r2 = r12.iterator();	 Catch:{ Throwable -> 0x00fc }
        r6 = r12;	 Catch:{ Throwable -> 0x00fc }
        r8 = r6;	 Catch:{ Throwable -> 0x00fc }
        r5 = r1;	 Catch:{ Throwable -> 0x00fc }
        r1 = r8;	 Catch:{ Throwable -> 0x00fc }
    L_0x00a3:
        r0.L$0 = r8;	 Catch:{ Throwable -> 0x00fc }
        r0.L$1 = r13;	 Catch:{ Throwable -> 0x00fc }
        r0.L$2 = r6;	 Catch:{ Throwable -> 0x00fc }
        r0.L$3 = r12;	 Catch:{ Throwable -> 0x00fc }
        r0.L$4 = r4;	 Catch:{ Throwable -> 0x00fc }
        r0.L$5 = r1;	 Catch:{ Throwable -> 0x00fc }
        r0.L$6 = r2;	 Catch:{ Throwable -> 0x00fc }
        r0.label = r3;	 Catch:{ Throwable -> 0x00fc }
        r7 = r2.hasNext(r0);	 Catch:{ Throwable -> 0x00fc }
        if (r7 != r5) goto L_0x00ba;
    L_0x00b9:
        return r5;
    L_0x00ba:
        r10 = r5;
        r5 = r12;
        r12 = r2;
        r2 = r10;
        r11 = r7;
        r7 = r13;
        r13 = r11;
    L_0x00c1:
        r13 = (java.lang.Boolean) r13;	 Catch:{ Throwable -> 0x0086, all -> 0x0083 }
        r13 = r13.booleanValue();	 Catch:{ Throwable -> 0x0086, all -> 0x0083 }
        if (r13 == 0) goto L_0x00ec;	 Catch:{ Throwable -> 0x0086, all -> 0x0083 }
    L_0x00c9:
        r0.L$0 = r8;	 Catch:{ Throwable -> 0x0086, all -> 0x0083 }
        r0.L$1 = r7;	 Catch:{ Throwable -> 0x0086, all -> 0x0083 }
        r0.L$2 = r6;	 Catch:{ Throwable -> 0x0086, all -> 0x0083 }
        r0.L$3 = r5;	 Catch:{ Throwable -> 0x0086, all -> 0x0083 }
        r0.L$4 = r4;	 Catch:{ Throwable -> 0x0086, all -> 0x0083 }
        r0.L$5 = r1;	 Catch:{ Throwable -> 0x0086, all -> 0x0083 }
        r0.L$6 = r12;	 Catch:{ Throwable -> 0x0086, all -> 0x0083 }
        r13 = 2;	 Catch:{ Throwable -> 0x0086, all -> 0x0083 }
        r0.label = r13;	 Catch:{ Throwable -> 0x0086, all -> 0x0083 }
        r13 = r12.next(r0);	 Catch:{ Throwable -> 0x0086, all -> 0x0083 }
        if (r13 != r2) goto L_0x00e1;
    L_0x00e0:
        return r2;
    L_0x00e1:
        r13 = r7;
        r10 = r2;
        r2 = r12;
        r12 = r5;
        r5 = r10;
    L_0x00e6:
        r7 = r13.element;	 Catch:{ Throwable -> 0x00fc }
        r7 = r7 + r3;	 Catch:{ Throwable -> 0x00fc }
        r13.element = r7;	 Catch:{ Throwable -> 0x00fc }
        goto L_0x00a3;
    L_0x00ec:
        r12 = kotlin.Unit.INSTANCE;	 Catch:{ Throwable -> 0x0086, all -> 0x0083 }
        r5.cancel(r4);
        r12 = r7.element;
        r12 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r12);
        return r12;
    L_0x00f8:
        r13 = move-exception;
        r5 = r12;
        r12 = r13;
        goto L_0x00ff;
    L_0x00fc:
        r13 = move-exception;
        r4 = r13;
    L_0x00fe:
        throw r4;	 Catch:{ all -> 0x00f8 }
    L_0x00ff:
        r5.cancel(r4);
        throw r12;
    L_0x0103:
        r13 = (kotlin.Result.Failure) r13;
        r12 = r13.exception;
        throw r12;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.count(kotlinx.coroutines.channels.ReceiveChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @org.jetbrains.annotations.Nullable
    @kotlinx.coroutines.ObsoleteCoroutinesApi
    public static final <E> java.lang.Object count(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r12, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super E, java.lang.Boolean> r13, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Integer> r14) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:37)
	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:61)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1603198149.run(Unknown Source)
*/
        /*
        r0 = r14 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$count$3;
        if (r0 == 0) goto L_0x0014;
    L_0x0004:
        r0 = r14;
        r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$count$3) r0;
        r1 = r0.label;
        r2 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r1 = r1 & r2;
        if (r1 == 0) goto L_0x0014;
    L_0x000e:
        r14 = r0.label;
        r14 = r14 - r2;
        r0.label = r14;
        goto L_0x0019;
    L_0x0014:
        r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$count$3;
        r0.<init>(r14);
    L_0x0019:
        r14 = r0.result;
        r1 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        r2 = r0.label;
        r3 = 1;
        switch(r2) {
            case 0: goto L_0x008d;
            case 1: goto L_0x005b;
            case 2: goto L_0x002d;
            default: goto L_0x0025;
        };
    L_0x0025:
        r12 = new java.lang.IllegalStateException;
        r13 = "call to 'resume' before 'invoke' with coroutine";
        r12.<init>(r13);
        throw r12;
    L_0x002d:
        r12 = r0.L$7;
        r12 = (kotlinx.coroutines.channels.ChannelIterator) r12;
        r13 = r0.L$6;
        r13 = (kotlinx.coroutines.channels.ReceiveChannel) r13;
        r2 = r0.L$5;
        r2 = (java.lang.Throwable) r2;
        r4 = r0.L$4;
        r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4;
        r5 = r0.L$3;
        r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5;
        r6 = r0.L$2;
        r6 = (kotlin.jvm.internal.Ref.IntRef) r6;
        r7 = r0.L$1;
        r7 = (kotlin.jvm.functions.Function1) r7;
        r8 = r0.L$0;
        r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8;
        r9 = r14 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        if (r9 != 0) goto L_0x0056;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
    L_0x0051:
        r10 = r4;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r4 = r12;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r12 = r10;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        goto L_0x00e5;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
    L_0x0056:
        r14 = (kotlin.Result.Failure) r14;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r12 = r14.exception;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        throw r12;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
    L_0x005b:
        r12 = r0.L$7;
        r12 = (kotlinx.coroutines.channels.ChannelIterator) r12;
        r13 = r0.L$6;
        r13 = (kotlinx.coroutines.channels.ReceiveChannel) r13;
        r2 = r0.L$5;
        r2 = (java.lang.Throwable) r2;
        r4 = r0.L$4;
        r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4;
        r5 = r0.L$3;
        r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5;
        r6 = r0.L$2;
        r6 = (kotlin.jvm.internal.Ref.IntRef) r6;
        r7 = r0.L$1;
        r7 = (kotlin.jvm.functions.Function1) r7;
        r8 = r0.L$0;
        r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8;
        r9 = r14 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        if (r9 != 0) goto L_0x0080;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
    L_0x007f:
        goto L_0x00c3;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
    L_0x0080:
        r14 = (kotlin.Result.Failure) r14;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r12 = r14.exception;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        throw r12;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
    L_0x0085:
        r12 = move-exception;
        goto L_0x0111;
    L_0x0088:
        r12 = move-exception;
        r2 = r12;
        r12 = r4;
        goto L_0x0110;
    L_0x008d:
        r2 = r14 instanceof kotlin.Result.Failure;
        if (r2 != 0) goto L_0x011b;
    L_0x0091:
        r14 = new kotlin.jvm.internal.Ref$IntRef;
        r14.<init>();
        r2 = 0;
        r14.element = r2;
        r2 = 0;
        r2 = (java.lang.Throwable) r2;
        r4 = r12.iterator();	 Catch:{ Throwable -> 0x010e }
        r5 = r12;	 Catch:{ Throwable -> 0x010e }
        r8 = r5;	 Catch:{ Throwable -> 0x010e }
        r7 = r13;	 Catch:{ Throwable -> 0x010e }
        r13 = r8;	 Catch:{ Throwable -> 0x010e }
    L_0x00a4:
        r0.L$0 = r8;	 Catch:{ Throwable -> 0x010e }
        r0.L$1 = r7;	 Catch:{ Throwable -> 0x010e }
        r0.L$2 = r14;	 Catch:{ Throwable -> 0x010e }
        r0.L$3 = r5;	 Catch:{ Throwable -> 0x010e }
        r0.L$4 = r12;	 Catch:{ Throwable -> 0x010e }
        r0.L$5 = r2;	 Catch:{ Throwable -> 0x010e }
        r0.L$6 = r13;	 Catch:{ Throwable -> 0x010e }
        r0.L$7 = r4;	 Catch:{ Throwable -> 0x010e }
        r0.label = r3;	 Catch:{ Throwable -> 0x010e }
        r6 = r4.hasNext(r0);	 Catch:{ Throwable -> 0x010e }
        if (r6 != r1) goto L_0x00bd;
    L_0x00bc:
        return r1;
    L_0x00bd:
        r10 = r4;
        r4 = r12;
        r12 = r10;
        r11 = r6;
        r6 = r14;
        r14 = r11;
    L_0x00c3:
        r14 = (java.lang.Boolean) r14;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r14 = r14.booleanValue();	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        if (r14 == 0) goto L_0x00f8;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
    L_0x00cb:
        r0.L$0 = r8;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r0.L$1 = r7;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r0.L$2 = r6;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r0.L$3 = r5;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r0.L$4 = r4;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r0.L$5 = r2;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r0.L$6 = r13;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r0.L$7 = r12;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r14 = 2;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r0.label = r14;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r14 = r12.next(r0);	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        if (r14 != r1) goto L_0x0051;
    L_0x00e4:
        return r1;
    L_0x00e5:
        r14 = r7.invoke(r14);	 Catch:{ Throwable -> 0x010e }
        r14 = (java.lang.Boolean) r14;	 Catch:{ Throwable -> 0x010e }
        r14 = r14.booleanValue();	 Catch:{ Throwable -> 0x010e }
        if (r14 == 0) goto L_0x00f6;	 Catch:{ Throwable -> 0x010e }
    L_0x00f1:
        r14 = r6.element;	 Catch:{ Throwable -> 0x010e }
        r14 = r14 + r3;	 Catch:{ Throwable -> 0x010e }
        r6.element = r14;	 Catch:{ Throwable -> 0x010e }
    L_0x00f6:
        r14 = r6;
        goto L_0x00a4;
    L_0x00f8:
        r12 = kotlin.Unit.INSTANCE;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        kotlin.jvm.internal.InlineMarker.finallyStart(r3);
        r4.cancel(r2);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r3);
        r12 = r6.element;
        r12 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r12);
        return r12;
    L_0x010a:
        r13 = move-exception;
        r4 = r12;
        r12 = r13;
        goto L_0x0111;
    L_0x010e:
        r13 = move-exception;
        r2 = r13;
    L_0x0110:
        throw r2;	 Catch:{ all -> 0x010a }
    L_0x0111:
        kotlin.jvm.internal.InlineMarker.finallyStart(r3);
        r4.cancel(r2);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r3);
        throw r12;
    L_0x011b:
        r14 = (kotlin.Result.Failure) r14;
        r12 = r14.exception;
        throw r12;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.count(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @org.jetbrains.annotations.Nullable
    @kotlinx.coroutines.ObsoleteCoroutinesApi
    public static final <E, R> java.lang.Object fold(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r12, R r13, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function2<? super R, ? super E, ? extends R> r14, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super R> r15) {
        /*
        r0 = r15 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$fold$1;
        if (r0 == 0) goto L_0x0014;
    L_0x0004:
        r0 = r15;
        r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$fold$1) r0;
        r1 = r0.label;
        r2 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r1 = r1 & r2;
        if (r1 == 0) goto L_0x0014;
    L_0x000e:
        r15 = r0.label;
        r15 = r15 - r2;
        r0.label = r15;
        goto L_0x0019;
    L_0x0014:
        r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$fold$1;
        r0.<init>(r15);
    L_0x0019:
        r15 = r0.result;
        r1 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        r2 = r0.label;
        r3 = 1;
        switch(r2) {
            case 0: goto L_0x009a;
            case 1: goto L_0x0061;
            case 2: goto L_0x002d;
            default: goto L_0x0025;
        };
    L_0x0025:
        r12 = new java.lang.IllegalStateException;
        r13 = "call to 'resume' before 'invoke' with coroutine";
        r12.<init>(r13);
        throw r12;
    L_0x002d:
        r12 = r0.L$8;
        r12 = (kotlinx.coroutines.channels.ChannelIterator) r12;
        r13 = r0.L$7;
        r13 = (kotlinx.coroutines.channels.ReceiveChannel) r13;
        r14 = r0.L$6;
        r14 = (java.lang.Throwable) r14;
        r2 = r0.L$5;
        r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2;
        r4 = r0.L$4;
        r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4;
        r5 = r0.L$3;
        r5 = (kotlin.jvm.internal.Ref.ObjectRef) r5;
        r6 = r0.L$2;
        r6 = (kotlin.jvm.functions.Function2) r6;
        r7 = r0.L$1;
        r8 = r0.L$0;
        r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8;
        r9 = r15 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x0095, all -> 0x0091 }
        if (r9 != 0) goto L_0x005c;
    L_0x0053:
        r10 = r4;
        r4 = r12;
        r12 = r10;
        r11 = r1;
        r1 = r13;
        r13 = r2;
        r2 = r11;
        goto L_0x00fb;
    L_0x005c:
        r15 = (kotlin.Result.Failure) r15;	 Catch:{ Throwable -> 0x0095, all -> 0x0091 }
        r12 = r15.exception;	 Catch:{ Throwable -> 0x0095, all -> 0x0091 }
        throw r12;	 Catch:{ Throwable -> 0x0095, all -> 0x0091 }
    L_0x0061:
        r12 = r0.L$8;
        r12 = (kotlinx.coroutines.channels.ChannelIterator) r12;
        r13 = r0.L$7;
        r13 = (kotlinx.coroutines.channels.ReceiveChannel) r13;
        r14 = r0.L$6;
        r14 = (java.lang.Throwable) r14;
        r2 = r0.L$5;
        r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2;
        r4 = r0.L$4;
        r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4;
        r5 = r0.L$3;
        r5 = (kotlin.jvm.internal.Ref.ObjectRef) r5;
        r6 = r0.L$2;
        r6 = (kotlin.jvm.functions.Function2) r6;
        r7 = r0.L$1;
        r8 = r0.L$0;
        r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8;
        r9 = r15 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x0095, all -> 0x0091 }
        if (r9 != 0) goto L_0x008c;
    L_0x0087:
        r10 = r1;
        r1 = r13;
        r13 = r2;
        r2 = r10;
        goto L_0x00d4;
    L_0x008c:
        r15 = (kotlin.Result.Failure) r15;	 Catch:{ Throwable -> 0x0095, all -> 0x0091 }
        r12 = r15.exception;	 Catch:{ Throwable -> 0x0095, all -> 0x0091 }
        throw r12;	 Catch:{ Throwable -> 0x0095, all -> 0x0091 }
    L_0x0091:
        r12 = move-exception;
        r13 = r2;
        goto L_0x0124;
    L_0x0095:
        r12 = move-exception;
        r14 = r12;
        r12 = r2;
        goto L_0x0121;
    L_0x009a:
        r2 = r15 instanceof kotlin.Result.Failure;
        if (r2 != 0) goto L_0x012e;
    L_0x009e:
        r15 = new kotlin.jvm.internal.Ref$ObjectRef;
        r15.<init>();
        r15.element = r13;
        r2 = 0;
        r2 = (java.lang.Throwable) r2;
        r4 = r12.iterator();	 Catch:{ Throwable -> 0x011f, all -> 0x0119 }
        r8 = r12;
        r7 = r13;
        r6 = r14;
        r14 = r2;
        r13 = r8;
        r2 = r1;
        r1 = r13;
    L_0x00b3:
        r0.L$0 = r8;	 Catch:{ Throwable -> 0x0115, all -> 0x0113 }
        r0.L$1 = r7;	 Catch:{ Throwable -> 0x0115, all -> 0x0113 }
        r0.L$2 = r6;	 Catch:{ Throwable -> 0x0115, all -> 0x0113 }
        r0.L$3 = r15;	 Catch:{ Throwable -> 0x0115, all -> 0x0113 }
        r0.L$4 = r12;	 Catch:{ Throwable -> 0x0115, all -> 0x0113 }
        r0.L$5 = r13;	 Catch:{ Throwable -> 0x0115, all -> 0x0113 }
        r0.L$6 = r14;	 Catch:{ Throwable -> 0x0115, all -> 0x0113 }
        r0.L$7 = r1;	 Catch:{ Throwable -> 0x0115, all -> 0x0113 }
        r0.L$8 = r4;	 Catch:{ Throwable -> 0x0115, all -> 0x0113 }
        r0.label = r3;	 Catch:{ Throwable -> 0x0115, all -> 0x0113 }
        r5 = r4.hasNext(r0);	 Catch:{ Throwable -> 0x0115, all -> 0x0113 }
        if (r5 != r2) goto L_0x00ce;
    L_0x00cd:
        return r2;
    L_0x00ce:
        r10 = r4;
        r4 = r12;
        r12 = r10;
        r11 = r5;
        r5 = r15;
        r15 = r11;
    L_0x00d4:
        r15 = (java.lang.Boolean) r15;	 Catch:{ Throwable -> 0x0115, all -> 0x0113 }
        r15 = r15.booleanValue();	 Catch:{ Throwable -> 0x0115, all -> 0x0113 }
        if (r15 == 0) goto L_0x0105;
    L_0x00dc:
        r0.L$0 = r8;	 Catch:{ Throwable -> 0x0115, all -> 0x0113 }
        r0.L$1 = r7;	 Catch:{ Throwable -> 0x0115, all -> 0x0113 }
        r0.L$2 = r6;	 Catch:{ Throwable -> 0x0115, all -> 0x0113 }
        r0.L$3 = r5;	 Catch:{ Throwable -> 0x0115, all -> 0x0113 }
        r0.L$4 = r4;	 Catch:{ Throwable -> 0x0115, all -> 0x0113 }
        r0.L$5 = r13;	 Catch:{ Throwable -> 0x0115, all -> 0x0113 }
        r0.L$6 = r14;	 Catch:{ Throwable -> 0x0115, all -> 0x0113 }
        r0.L$7 = r1;	 Catch:{ Throwable -> 0x0115, all -> 0x0113 }
        r0.L$8 = r12;	 Catch:{ Throwable -> 0x0115, all -> 0x0113 }
        r15 = 2;
        r0.label = r15;	 Catch:{ Throwable -> 0x0115, all -> 0x0113 }
        r15 = r12.next(r0);	 Catch:{ Throwable -> 0x0115, all -> 0x0113 }
        if (r15 != r2) goto L_0x00f8;
    L_0x00f7:
        return r2;
    L_0x00f8:
        r10 = r4;
        r4 = r12;
        r12 = r10;
    L_0x00fb:
        r9 = r5.element;	 Catch:{ Throwable -> 0x0115, all -> 0x0113 }
        r15 = r6.invoke(r9, r15);	 Catch:{ Throwable -> 0x0115, all -> 0x0113 }
        r5.element = r15;	 Catch:{ Throwable -> 0x0115, all -> 0x0113 }
        r15 = r5;
        goto L_0x00b3;
    L_0x0105:
        r12 = kotlin.Unit.INSTANCE;	 Catch:{ Throwable -> 0x0115, all -> 0x0113 }
        kotlin.jvm.internal.InlineMarker.finallyStart(r3);
        r13.cancel(r14);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r3);
        r12 = r5.element;
        return r12;
    L_0x0113:
        r12 = move-exception;
        goto L_0x0124;
    L_0x0115:
        r12 = move-exception;
        r14 = r12;
        r12 = r13;
        goto L_0x0121;
    L_0x0119:
        r13 = move-exception;
        r14 = r2;
    L_0x011b:
        r10 = r13;
        r13 = r12;
        r12 = r10;
        goto L_0x0124;
    L_0x011f:
        r13 = move-exception;
        r14 = r13;
    L_0x0121:
        throw r14;	 Catch:{ all -> 0x0122 }
    L_0x0122:
        r13 = move-exception;
        goto L_0x011b;
    L_0x0124:
        kotlin.jvm.internal.InlineMarker.finallyStart(r3);
        r13.cancel(r14);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r3);
        throw r12;
    L_0x012e:
        r15 = (kotlin.Result.Failure) r15;
        r12 = r15.exception;
        throw r12;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.fold(kotlinx.coroutines.channels.ReceiveChannel, java.lang.Object, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @org.jetbrains.annotations.Nullable
    @kotlinx.coroutines.ObsoleteCoroutinesApi
    public static final <E, R> java.lang.Object foldIndexed(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r18, R r19, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function3<? super java.lang.Integer, ? super R, ? super E, ? extends R> r20, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super R> r21) {
        /*
        r0 = r21;
        r1 = r0 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$foldIndexed$1;
        if (r1 == 0) goto L_0x0016;
    L_0x0006:
        r1 = r0;
        r1 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$foldIndexed$1) r1;
        r2 = r1.label;
        r3 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r2 = r2 & r3;
        if (r2 == 0) goto L_0x0016;
    L_0x0010:
        r0 = r1.label;
        r0 = r0 - r3;
        r1.label = r0;
        goto L_0x001b;
    L_0x0016:
        r1 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$foldIndexed$1;
        r1.<init>(r0);
    L_0x001b:
        r0 = r1.result;
        r2 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        r3 = r1.label;
        r4 = 1;
        switch(r3) {
            case 0: goto L_0x00a5;
            case 1: goto L_0x0067;
            case 2: goto L_0x002f;
            default: goto L_0x0027;
        };
    L_0x0027:
        r0 = new java.lang.IllegalStateException;
        r1 = "call to 'resume' before 'invoke' with coroutine";
        r0.<init>(r1);
        throw r0;
    L_0x002f:
        r3 = r1.L$9;
        r3 = (kotlinx.coroutines.channels.ChannelIterator) r3;
        r5 = r1.L$8;
        r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5;
        r6 = r1.L$7;
        r6 = (java.lang.Throwable) r6;
        r7 = r1.L$6;
        r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7;
        r8 = r1.L$5;
        r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8;
        r9 = r1.L$4;
        r9 = (kotlin.jvm.internal.Ref.ObjectRef) r9;
        r10 = r1.L$3;
        r10 = (kotlin.jvm.internal.Ref.IntRef) r10;
        r11 = r1.L$2;
        r11 = (kotlin.jvm.functions.Function3) r11;
        r12 = r1.L$1;
        r13 = r1.L$0;
        r13 = (kotlinx.coroutines.channels.ReceiveChannel) r13;
        r14 = r0 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x00a0, all -> 0x009c }
        if (r14 != 0) goto L_0x0062;
    L_0x0059:
        r16 = r9;
        r9 = r2;
        r2 = r7;
        r7 = r3;
        r3 = r16;
        goto L_0x011e;
    L_0x0062:
        r0 = (kotlin.Result.Failure) r0;	 Catch:{ Throwable -> 0x00a0, all -> 0x009c }
        r0 = r0.exception;	 Catch:{ Throwable -> 0x00a0, all -> 0x009c }
        throw r0;	 Catch:{ Throwable -> 0x00a0, all -> 0x009c }
    L_0x0067:
        r3 = r1.L$9;
        r3 = (kotlinx.coroutines.channels.ChannelIterator) r3;
        r5 = r1.L$8;
        r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5;
        r6 = r1.L$7;
        r6 = (java.lang.Throwable) r6;
        r7 = r1.L$6;
        r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7;
        r8 = r1.L$5;
        r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8;
        r9 = r1.L$4;
        r9 = (kotlin.jvm.internal.Ref.ObjectRef) r9;
        r10 = r1.L$3;
        r10 = (kotlin.jvm.internal.Ref.IntRef) r10;
        r11 = r1.L$2;
        r11 = (kotlin.jvm.functions.Function3) r11;
        r12 = r1.L$1;
        r13 = r1.L$0;
        r13 = (kotlinx.coroutines.channels.ReceiveChannel) r13;
        r14 = r0 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x00a0, all -> 0x009c }
        if (r14 != 0) goto L_0x0097;
    L_0x0091:
        r16 = r7;
        r7 = r2;
        r2 = r16;
        goto L_0x00f2;
    L_0x0097:
        r0 = (kotlin.Result.Failure) r0;	 Catch:{ Throwable -> 0x00a0, all -> 0x009c }
        r0 = r0.exception;	 Catch:{ Throwable -> 0x00a0, all -> 0x009c }
        throw r0;	 Catch:{ Throwable -> 0x00a0, all -> 0x009c }
    L_0x009c:
        r0 = move-exception;
        r2 = r7;
        goto L_0x014c;
    L_0x00a0:
        r0 = move-exception;
        r6 = r0;
        r2 = r7;
        goto L_0x014a;
    L_0x00a5:
        r3 = r0 instanceof kotlin.Result.Failure;
        if (r3 != 0) goto L_0x0156;
    L_0x00a9:
        r0 = new kotlin.jvm.internal.Ref$IntRef;
        r0.<init>();
        r3 = 0;
        r0.element = r3;
        r3 = new kotlin.jvm.internal.Ref$ObjectRef;
        r3.<init>();
        r5 = r19;
        r3.element = r5;
        r6 = 0;
        r6 = (java.lang.Throwable) r6;
        r7 = r18.iterator();	 Catch:{ Throwable -> 0x0146, all -> 0x0142 }
        r8 = r18;
        r13 = r8;
        r11 = r20;
        r9 = r2;
        r12 = r5;
        r2 = r13;
        r5 = r2;
    L_0x00ca:
        r1.L$0 = r13;	 Catch:{ Throwable -> 0x0140 }
        r1.L$1 = r12;	 Catch:{ Throwable -> 0x0140 }
        r1.L$2 = r11;	 Catch:{ Throwable -> 0x0140 }
        r1.L$3 = r0;	 Catch:{ Throwable -> 0x0140 }
        r1.L$4 = r3;	 Catch:{ Throwable -> 0x0140 }
        r1.L$5 = r8;	 Catch:{ Throwable -> 0x0140 }
        r1.L$6 = r2;	 Catch:{ Throwable -> 0x0140 }
        r1.L$7 = r6;	 Catch:{ Throwable -> 0x0140 }
        r1.L$8 = r5;	 Catch:{ Throwable -> 0x0140 }
        r1.L$9 = r7;	 Catch:{ Throwable -> 0x0140 }
        r1.label = r4;	 Catch:{ Throwable -> 0x0140 }
        r10 = r7.hasNext(r1);	 Catch:{ Throwable -> 0x0140 }
        if (r10 != r9) goto L_0x00e7;
    L_0x00e6:
        return r9;
    L_0x00e7:
        r16 = r10;
        r10 = r0;
        r0 = r16;
        r17 = r9;
        r9 = r3;
        r3 = r7;
        r7 = r17;
    L_0x00f2:
        r0 = (java.lang.Boolean) r0;	 Catch:{ Throwable -> 0x0140 }
        r0 = r0.booleanValue();	 Catch:{ Throwable -> 0x0140 }
        if (r0 == 0) goto L_0x0132;
    L_0x00fa:
        r1.L$0 = r13;	 Catch:{ Throwable -> 0x0140 }
        r1.L$1 = r12;	 Catch:{ Throwable -> 0x0140 }
        r1.L$2 = r11;	 Catch:{ Throwable -> 0x0140 }
        r1.L$3 = r10;	 Catch:{ Throwable -> 0x0140 }
        r1.L$4 = r9;	 Catch:{ Throwable -> 0x0140 }
        r1.L$5 = r8;	 Catch:{ Throwable -> 0x0140 }
        r1.L$6 = r2;	 Catch:{ Throwable -> 0x0140 }
        r1.L$7 = r6;	 Catch:{ Throwable -> 0x0140 }
        r1.L$8 = r5;	 Catch:{ Throwable -> 0x0140 }
        r1.L$9 = r3;	 Catch:{ Throwable -> 0x0140 }
        r0 = 2;
        r1.label = r0;	 Catch:{ Throwable -> 0x0140 }
        r0 = r3.next(r1);	 Catch:{ Throwable -> 0x0140 }
        if (r0 != r7) goto L_0x0118;
    L_0x0117:
        return r7;
    L_0x0118:
        r16 = r7;
        r7 = r3;
        r3 = r9;
        r9 = r16;
    L_0x011e:
        r14 = r10.element;	 Catch:{ Throwable -> 0x0140 }
        r15 = r14 + 1;
        r10.element = r15;	 Catch:{ Throwable -> 0x0140 }
        r14 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r14);	 Catch:{ Throwable -> 0x0140 }
        r15 = r3.element;	 Catch:{ Throwable -> 0x0140 }
        r0 = r11.invoke(r14, r15, r0);	 Catch:{ Throwable -> 0x0140 }
        r3.element = r0;	 Catch:{ Throwable -> 0x0140 }
        r0 = r10;
        goto L_0x00ca;
    L_0x0132:
        r0 = kotlin.Unit.INSTANCE;	 Catch:{ Throwable -> 0x0140 }
        kotlin.jvm.internal.InlineMarker.finallyStart(r4);
        r2.cancel(r6);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r4);
        r0 = r9.element;
        return r0;
    L_0x0140:
        r0 = move-exception;
        goto L_0x0149;
    L_0x0142:
        r0 = move-exception;
        r2 = r18;
        goto L_0x014c;
    L_0x0146:
        r0 = move-exception;
        r2 = r18;
    L_0x0149:
        r6 = r0;
    L_0x014a:
        throw r6;	 Catch:{ all -> 0x014b }
    L_0x014b:
        r0 = move-exception;
    L_0x014c:
        kotlin.jvm.internal.InlineMarker.finallyStart(r4);
        r2.cancel(r6);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r4);
        throw r0;
    L_0x0156:
        r0 = (kotlin.Result.Failure) r0;
        r0 = r0.exception;
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.foldIndexed(kotlinx.coroutines.channels.ReceiveChannel, java.lang.Object, kotlin.jvm.functions.Function3, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @org.jetbrains.annotations.Nullable
    @kotlinx.coroutines.ObsoleteCoroutinesApi
    public static final <E, R extends java.lang.Comparable<? super R>> java.lang.Object maxBy(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r18, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super E, ? extends R> r19, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super E> r20) {
        /*
        r1 = r18;
        r0 = r20;
        r2 = r0 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$maxBy$1;
        if (r2 == 0) goto L_0x0018;
    L_0x0008:
        r2 = r0;
        r2 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$maxBy$1) r2;
        r3 = r2.label;
        r4 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r3 = r3 & r4;
        if (r3 == 0) goto L_0x0018;
    L_0x0012:
        r0 = r2.label;
        r0 = r0 - r4;
        r2.label = r0;
        goto L_0x001d;
    L_0x0018:
        r2 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$maxBy$1;
        r2.<init>(r0);
    L_0x001d:
        r0 = r2.result;
        r3 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        r4 = r2.label;
        r5 = 0;
        r6 = 2;
        r7 = 3;
        r8 = 1;
        switch(r4) {
            case 0: goto L_0x0106;
            case 1: goto L_0x00d2;
            case 2: goto L_0x00a5;
            case 3: goto L_0x006c;
            case 4: goto L_0x0034;
            default: goto L_0x002c;
        };
    L_0x002c:
        r0 = new java.lang.IllegalStateException;
        r1 = "call to 'resume' before 'invoke' with coroutine";
        r0.<init>(r1);
        throw r0;
    L_0x0034:
        r1 = r2.L$7;
        r1 = (java.lang.Comparable) r1;
        r4 = r2.L$6;
        r5 = r2.L$5;
        r5 = (kotlinx.coroutines.channels.ChannelIterator) r5;
        r9 = r2.L$4;
        r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9;
        r10 = r2.L$3;
        r10 = (java.lang.Throwable) r10;
        r11 = r2.L$2;
        r11 = (kotlinx.coroutines.channels.ReceiveChannel) r11;
        r12 = r2.L$1;
        r12 = (kotlin.jvm.functions.Function1) r12;
        r13 = r2.L$0;
        r13 = (kotlinx.coroutines.channels.ReceiveChannel) r13;
        r14 = r0 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x00a0, all -> 0x009c }
        if (r14 != 0) goto L_0x0067;
    L_0x0056:
        r16 = r13;
        r13 = r0;
        r0 = r1;
        r1 = r5;
        r5 = r11;
        r11 = r16;
    L_0x005e:
        r17 = r12;
        r12 = r3;
        r3 = r4;
        r4 = r9;
        r9 = r17;
        goto L_0x01ae;
    L_0x0067:
        r0 = (kotlin.Result.Failure) r0;	 Catch:{ Throwable -> 0x00a0, all -> 0x009c }
        r0 = r0.exception;	 Catch:{ Throwable -> 0x00a0, all -> 0x009c }
        throw r0;	 Catch:{ Throwable -> 0x00a0, all -> 0x009c }
    L_0x006c:
        r1 = r2.L$7;
        r1 = (java.lang.Comparable) r1;
        r4 = r2.L$6;
        r5 = r2.L$5;
        r5 = (kotlinx.coroutines.channels.ChannelIterator) r5;
        r9 = r2.L$4;
        r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9;
        r10 = r2.L$3;
        r10 = (java.lang.Throwable) r10;
        r11 = r2.L$2;
        r11 = (kotlinx.coroutines.channels.ReceiveChannel) r11;
        r12 = r2.L$1;
        r12 = (kotlin.jvm.functions.Function1) r12;
        r13 = r2.L$0;
        r13 = (kotlinx.coroutines.channels.ReceiveChannel) r13;
        r14 = r0 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x00a0, all -> 0x009c }
        if (r14 != 0) goto L_0x0097;
    L_0x008e:
        r16 = r13;
        r13 = r0;
        r0 = r1;
        r1 = r11;
        r11 = r16;
        goto L_0x0185;
    L_0x0097:
        r0 = (kotlin.Result.Failure) r0;	 Catch:{ Throwable -> 0x00a0, all -> 0x009c }
        r0 = r0.exception;	 Catch:{ Throwable -> 0x00a0, all -> 0x009c }
        throw r0;	 Catch:{ Throwable -> 0x00a0, all -> 0x009c }
    L_0x009c:
        r0 = move-exception;
        r1 = r11;
        goto L_0x01d0;
    L_0x00a0:
        r0 = move-exception;
        r10 = r0;
        r1 = r11;
        goto L_0x01cf;
    L_0x00a5:
        r1 = r2.L$5;
        r1 = (kotlinx.coroutines.channels.ChannelIterator) r1;
        r4 = r2.L$4;
        r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4;
        r5 = r2.L$3;
        r10 = r5;
        r10 = (java.lang.Throwable) r10;
        r5 = r2.L$2;
        r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5;
        r9 = r2.L$1;
        r9 = (kotlin.jvm.functions.Function1) r9;
        r11 = r2.L$0;
        r11 = (kotlinx.coroutines.channels.ReceiveChannel) r11;
        r12 = r0 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x00cd, all -> 0x00c9 }
        if (r12 != 0) goto L_0x00c4;
    L_0x00c2:
        goto L_0x015a;
    L_0x00c4:
        r0 = (kotlin.Result.Failure) r0;	 Catch:{ Throwable -> 0x00cd, all -> 0x00c9 }
        r0 = r0.exception;	 Catch:{ Throwable -> 0x00cd, all -> 0x00c9 }
        throw r0;	 Catch:{ Throwable -> 0x00cd, all -> 0x00c9 }
    L_0x00c9:
        r0 = move-exception;
        r1 = r5;
        goto L_0x01d0;
    L_0x00cd:
        r0 = move-exception;
        r10 = r0;
        r1 = r5;
        goto L_0x01cf;
    L_0x00d2:
        r1 = r2.L$5;
        r1 = (kotlinx.coroutines.channels.ChannelIterator) r1;
        r4 = r2.L$4;
        r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4;
        r9 = r2.L$3;
        r10 = r9;
        r10 = (java.lang.Throwable) r10;
        r9 = r2.L$2;
        r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9;
        r11 = r2.L$1;
        r11 = (kotlin.jvm.functions.Function1) r11;
        r12 = r2.L$0;
        r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12;
        r13 = r0 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x0101, all -> 0x00fd }
        if (r13 != 0) goto L_0x00f8;
    L_0x00ef:
        r16 = r4;
        r4 = r1;
        r1 = r9;
        r9 = r11;
        r11 = r12;
        r12 = r16;
        goto L_0x0130;
    L_0x00f8:
        r0 = (kotlin.Result.Failure) r0;	 Catch:{ Throwable -> 0x0101, all -> 0x00fd }
        r0 = r0.exception;	 Catch:{ Throwable -> 0x0101, all -> 0x00fd }
        throw r0;	 Catch:{ Throwable -> 0x0101, all -> 0x00fd }
    L_0x00fd:
        r0 = move-exception;
        r1 = r9;
        goto L_0x01d0;
    L_0x0101:
        r0 = move-exception;
        r10 = r0;
        r1 = r9;
        goto L_0x01cf;
    L_0x0106:
        r4 = r0 instanceof kotlin.Result.Failure;
        if (r4 != 0) goto L_0x01da;
    L_0x010a:
        r10 = r5;
        r10 = (java.lang.Throwable) r10;
        r0 = r18.iterator();	 Catch:{ Throwable -> 0x01cd }
        r2.L$0 = r1;	 Catch:{ Throwable -> 0x01cd }
        r4 = r19;
        r2.L$1 = r4;	 Catch:{ Throwable -> 0x01cd }
        r2.L$2 = r1;	 Catch:{ Throwable -> 0x01cd }
        r2.L$3 = r10;	 Catch:{ Throwable -> 0x01cd }
        r2.L$4 = r1;	 Catch:{ Throwable -> 0x01cd }
        r2.L$5 = r0;	 Catch:{ Throwable -> 0x01cd }
        r2.label = r8;	 Catch:{ Throwable -> 0x01cd }
        r9 = r0.hasNext(r2);	 Catch:{ Throwable -> 0x01cd }
        if (r9 != r3) goto L_0x0128;
    L_0x0127:
        return r3;
    L_0x0128:
        r11 = r1;
        r12 = r11;
        r16 = r4;
        r4 = r0;
        r0 = r9;
        r9 = r16;
    L_0x0130:
        r0 = (java.lang.Boolean) r0;	 Catch:{ Throwable -> 0x01cd }
        r0 = r0.booleanValue();	 Catch:{ Throwable -> 0x01cd }
        if (r0 != 0) goto L_0x0142;
    L_0x0138:
        kotlin.jvm.internal.InlineMarker.finallyStart(r7);
        r1.cancel(r10);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r7);
        return r5;
    L_0x0142:
        r2.L$0 = r11;	 Catch:{ Throwable -> 0x01cd }
        r2.L$1 = r9;	 Catch:{ Throwable -> 0x01cd }
        r2.L$2 = r1;	 Catch:{ Throwable -> 0x01cd }
        r2.L$3 = r10;	 Catch:{ Throwable -> 0x01cd }
        r2.L$4 = r12;	 Catch:{ Throwable -> 0x01cd }
        r2.L$5 = r4;	 Catch:{ Throwable -> 0x01cd }
        r2.label = r6;	 Catch:{ Throwable -> 0x01cd }
        r0 = r4.next(r2);	 Catch:{ Throwable -> 0x01cd }
        if (r0 != r3) goto L_0x0157;
    L_0x0156:
        return r3;
    L_0x0157:
        r5 = r1;
        r1 = r4;
        r4 = r12;
    L_0x015a:
        r12 = r9.invoke(r0);	 Catch:{ Throwable -> 0x00cd, all -> 0x00c9 }
        r12 = (java.lang.Comparable) r12;	 Catch:{ Throwable -> 0x00cd, all -> 0x00c9 }
    L_0x0160:
        r2.L$0 = r11;	 Catch:{ Throwable -> 0x00cd, all -> 0x00c9 }
        r2.L$1 = r9;	 Catch:{ Throwable -> 0x00cd, all -> 0x00c9 }
        r2.L$2 = r5;	 Catch:{ Throwable -> 0x00cd, all -> 0x00c9 }
        r2.L$3 = r10;	 Catch:{ Throwable -> 0x00cd, all -> 0x00c9 }
        r2.L$4 = r4;	 Catch:{ Throwable -> 0x00cd, all -> 0x00c9 }
        r2.L$5 = r1;	 Catch:{ Throwable -> 0x00cd, all -> 0x00c9 }
        r2.L$6 = r0;	 Catch:{ Throwable -> 0x00cd, all -> 0x00c9 }
        r2.L$7 = r12;	 Catch:{ Throwable -> 0x00cd, all -> 0x00c9 }
        r2.label = r7;	 Catch:{ Throwable -> 0x00cd, all -> 0x00c9 }
        r13 = r1.hasNext(r2);	 Catch:{ Throwable -> 0x00cd, all -> 0x00c9 }
        if (r13 != r3) goto L_0x0179;
    L_0x0178:
        return r3;
    L_0x0179:
        r16 = r4;
        r4 = r0;
        r0 = r12;
        r12 = r9;
        r9 = r16;
        r17 = r5;
        r5 = r1;
        r1 = r17;
    L_0x0185:
        r13 = (java.lang.Boolean) r13;	 Catch:{ Throwable -> 0x01cd }
        r13 = r13.booleanValue();	 Catch:{ Throwable -> 0x01cd }
        if (r13 == 0) goto L_0x01c1;
    L_0x018d:
        r2.L$0 = r11;	 Catch:{ Throwable -> 0x01cd }
        r2.L$1 = r12;	 Catch:{ Throwable -> 0x01cd }
        r2.L$2 = r1;	 Catch:{ Throwable -> 0x01cd }
        r2.L$3 = r10;	 Catch:{ Throwable -> 0x01cd }
        r2.L$4 = r9;	 Catch:{ Throwable -> 0x01cd }
        r2.L$5 = r5;	 Catch:{ Throwable -> 0x01cd }
        r2.L$6 = r4;	 Catch:{ Throwable -> 0x01cd }
        r2.L$7 = r0;	 Catch:{ Throwable -> 0x01cd }
        r13 = 4;
        r2.label = r13;	 Catch:{ Throwable -> 0x01cd }
        r13 = r5.next(r2);	 Catch:{ Throwable -> 0x01cd }
        if (r13 != r3) goto L_0x01a7;
    L_0x01a6:
        return r3;
    L_0x01a7:
        r16 = r5;
        r5 = r1;
        r1 = r16;
        goto L_0x005e;
    L_0x01ae:
        r14 = r9.invoke(r13);	 Catch:{ Throwable -> 0x00cd, all -> 0x00c9 }
        r14 = (java.lang.Comparable) r14;	 Catch:{ Throwable -> 0x00cd, all -> 0x00c9 }
        r15 = r0.compareTo(r14);	 Catch:{ Throwable -> 0x00cd, all -> 0x00c9 }
        if (r15 >= 0) goto L_0x01bc;
    L_0x01ba:
        r0 = r13;
        goto L_0x01be;
    L_0x01bc:
        r14 = r0;
        r0 = r3;
    L_0x01be:
        r3 = r12;
        r12 = r14;
        goto L_0x0160;
    L_0x01c1:
        kotlin.jvm.internal.InlineMarker.finallyStart(r6);
        r1.cancel(r10);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r6);
        return r4;
    L_0x01cb:
        r0 = move-exception;
        goto L_0x01d0;
    L_0x01cd:
        r0 = move-exception;
        r10 = r0;
    L_0x01cf:
        throw r10;	 Catch:{ all -> 0x01cb }
    L_0x01d0:
        kotlin.jvm.internal.InlineMarker.finallyStart(r8);
        r1.cancel(r10);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r8);
        throw r0;
    L_0x01da:
        r0 = (kotlin.Result.Failure) r0;
        r0 = r0.exception;
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.maxBy(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @org.jetbrains.annotations.Nullable
    @kotlinx.coroutines.ObsoleteCoroutinesApi
    public static final <E> java.lang.Object maxWith(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r9, @org.jetbrains.annotations.NotNull java.util.Comparator<? super E> r10, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super E> r11) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.maxWith(kotlinx.coroutines.channels.ReceiveChannel, java.util.Comparator, kotlin.coroutines.Continuation):java.lang.Object. bs: [B:11:0x0047, B:39:0x00c7]
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:86)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1603198149.run(Unknown Source)
*/
        /*
        r0 = r11 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$maxWith$1;
        if (r0 == 0) goto L_0x0014;
    L_0x0004:
        r0 = r11;
        r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$maxWith$1) r0;
        r1 = r0.label;
        r2 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r1 = r1 & r2;
        if (r1 == 0) goto L_0x0014;
    L_0x000e:
        r11 = r0.label;
        r11 = r11 - r2;
        r0.label = r11;
        goto L_0x0019;
    L_0x0014:
        r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$maxWith$1;
        r0.<init>(r11);
    L_0x0019:
        r11 = r0.result;
        r1 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        r2 = r0.label;
        r3 = 0;
        switch(r2) {
            case 0: goto L_0x00e1;
            case 1: goto L_0x00af;
            case 2: goto L_0x0083;
            case 3: goto L_0x0058;
            case 4: goto L_0x002d;
            default: goto L_0x0025;
        };
    L_0x0025:
        r9 = new java.lang.IllegalStateException;
        r10 = "call to 'resume' before 'invoke' with coroutine";
        r9.<init>(r10);
        throw r9;
    L_0x002d:
        r9 = r0.L$6;
        r10 = r0.L$5;
        r10 = (kotlinx.coroutines.channels.ChannelIterator) r10;
        r2 = r0.L$4;
        r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2;
        r3 = r0.L$3;
        r3 = (java.lang.Throwable) r3;
        r4 = r0.L$2;
        r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4;
        r5 = r0.L$1;
        r5 = (java.util.Comparator) r5;
        r6 = r0.L$0;
        r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6;
        r7 = r11 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x00dc, all -> 0x0081 }
        if (r7 != 0) goto L_0x0053;	 Catch:{ Throwable -> 0x00dc, all -> 0x0081 }
    L_0x004b:
        r8 = r6;	 Catch:{ Throwable -> 0x00dc, all -> 0x0081 }
        r6 = r1;	 Catch:{ Throwable -> 0x00dc, all -> 0x0081 }
        r1 = r2;	 Catch:{ Throwable -> 0x00dc, all -> 0x0081 }
        r2 = r4;	 Catch:{ Throwable -> 0x00dc, all -> 0x0081 }
    L_0x004f:
        r4 = r5;	 Catch:{ Throwable -> 0x00dc, all -> 0x0081 }
        r5 = r8;	 Catch:{ Throwable -> 0x00dc, all -> 0x0081 }
        goto L_0x0173;	 Catch:{ Throwable -> 0x00dc, all -> 0x0081 }
    L_0x0053:
        r11 = (kotlin.Result.Failure) r11;	 Catch:{ Throwable -> 0x00dc, all -> 0x0081 }
        r9 = r11.exception;	 Catch:{ Throwable -> 0x00dc, all -> 0x0081 }
        throw r9;	 Catch:{ Throwable -> 0x00dc, all -> 0x0081 }
    L_0x0058:
        r9 = r0.L$6;
        r10 = r0.L$5;
        r10 = (kotlinx.coroutines.channels.ChannelIterator) r10;
        r2 = r0.L$4;
        r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2;
        r3 = r0.L$3;
        r3 = (java.lang.Throwable) r3;
        r4 = r0.L$2;
        r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4;
        r5 = r0.L$1;
        r5 = (java.util.Comparator) r5;
        r6 = r0.L$0;
        r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6;
        r7 = r11 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x00dc, all -> 0x0081 }
        if (r7 != 0) goto L_0x007c;	 Catch:{ Throwable -> 0x00dc, all -> 0x0081 }
    L_0x0076:
        r8 = r4;	 Catch:{ Throwable -> 0x00dc, all -> 0x0081 }
        r4 = r1;	 Catch:{ Throwable -> 0x00dc, all -> 0x0081 }
        r1 = r2;	 Catch:{ Throwable -> 0x00dc, all -> 0x0081 }
        r2 = r8;	 Catch:{ Throwable -> 0x00dc, all -> 0x0081 }
        goto L_0x014f;	 Catch:{ Throwable -> 0x00dc, all -> 0x0081 }
    L_0x007c:
        r11 = (kotlin.Result.Failure) r11;	 Catch:{ Throwable -> 0x00dc, all -> 0x0081 }
        r9 = r11.exception;	 Catch:{ Throwable -> 0x00dc, all -> 0x0081 }
        throw r9;	 Catch:{ Throwable -> 0x00dc, all -> 0x0081 }
    L_0x0081:
        r9 = move-exception;
        goto L_0x00d9;
    L_0x0083:
        r9 = r0.L$5;
        r9 = (kotlinx.coroutines.channels.ChannelIterator) r9;
        r10 = r0.L$4;
        r10 = (kotlinx.coroutines.channels.ReceiveChannel) r10;
        r2 = r0.L$3;
        r3 = r2;
        r3 = (java.lang.Throwable) r3;
        r2 = r0.L$2;
        r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2;
        r4 = r0.L$1;
        r4 = (java.util.Comparator) r4;
        r5 = r0.L$0;
        r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5;
        r6 = r11 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
        if (r6 != 0) goto L_0x00a2;	 Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
    L_0x00a0:
        goto L_0x012f;	 Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
    L_0x00a2:
        r11 = (kotlin.Result.Failure) r11;	 Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
        r9 = r11.exception;	 Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
        throw r9;	 Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
    L_0x00a7:
        r9 = move-exception;
        goto L_0x0191;
    L_0x00aa:
        r9 = move-exception;
        r3 = r9;
        r9 = r2;
        goto L_0x018d;
    L_0x00af:
        r9 = r0.L$5;
        r9 = (kotlinx.coroutines.channels.ChannelIterator) r9;
        r10 = r0.L$4;
        r10 = (kotlinx.coroutines.channels.ReceiveChannel) r10;
        r2 = r0.L$3;
        r2 = (java.lang.Throwable) r2;
        r4 = r0.L$2;
        r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4;
        r5 = r0.L$1;
        r5 = (java.util.Comparator) r5;
        r6 = r0.L$0;
        r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6;
        r7 = r11 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x00dc, all -> 0x00d7 }
        if (r7 != 0) goto L_0x00d2;	 Catch:{ Throwable -> 0x00dc, all -> 0x00d7 }
    L_0x00cb:
        r8 = r10;	 Catch:{ Throwable -> 0x00dc, all -> 0x00d7 }
        r10 = r9;	 Catch:{ Throwable -> 0x00dc, all -> 0x00d7 }
        r9 = r4;	 Catch:{ Throwable -> 0x00dc, all -> 0x00d7 }
        r4 = r5;	 Catch:{ Throwable -> 0x00dc, all -> 0x00d7 }
        r5 = r6;	 Catch:{ Throwable -> 0x00dc, all -> 0x00d7 }
        r6 = r8;	 Catch:{ Throwable -> 0x00dc, all -> 0x00d7 }
        goto L_0x0109;	 Catch:{ Throwable -> 0x00dc, all -> 0x00d7 }
    L_0x00d2:
        r11 = (kotlin.Result.Failure) r11;	 Catch:{ Throwable -> 0x00dc, all -> 0x00d7 }
        r9 = r11.exception;	 Catch:{ Throwable -> 0x00dc, all -> 0x00d7 }
        throw r9;	 Catch:{ Throwable -> 0x00dc, all -> 0x00d7 }
    L_0x00d7:
        r9 = move-exception;
        r3 = r2;
    L_0x00d9:
        r2 = r4;
        goto L_0x0191;
    L_0x00dc:
        r9 = move-exception;
        r3 = r9;
        r9 = r4;
        goto L_0x018d;
    L_0x00e1:
        r2 = r11 instanceof kotlin.Result.Failure;
        if (r2 != 0) goto L_0x0195;
    L_0x00e5:
        r11 = r3;
        r11 = (java.lang.Throwable) r11;
        r2 = r9.iterator();	 Catch:{ Throwable -> 0x018b, all -> 0x0186 }
        r0.L$0 = r9;	 Catch:{ Throwable -> 0x018b, all -> 0x0186 }
        r0.L$1 = r10;	 Catch:{ Throwable -> 0x018b, all -> 0x0186 }
        r0.L$2 = r9;	 Catch:{ Throwable -> 0x018b, all -> 0x0186 }
        r0.L$3 = r11;	 Catch:{ Throwable -> 0x018b, all -> 0x0186 }
        r0.L$4 = r9;	 Catch:{ Throwable -> 0x018b, all -> 0x0186 }
        r0.L$5 = r2;	 Catch:{ Throwable -> 0x018b, all -> 0x0186 }
        r4 = 1;	 Catch:{ Throwable -> 0x018b, all -> 0x0186 }
        r0.label = r4;	 Catch:{ Throwable -> 0x018b, all -> 0x0186 }
        r4 = r2.hasNext(r0);	 Catch:{ Throwable -> 0x018b, all -> 0x0186 }
        if (r4 != r1) goto L_0x0102;
    L_0x0101:
        return r1;
    L_0x0102:
        r5 = r9;
        r6 = r5;
        r8 = r4;
        r4 = r10;
        r10 = r2;
        r2 = r11;
        r11 = r8;
    L_0x0109:
        r11 = (java.lang.Boolean) r11;	 Catch:{ Throwable -> 0x018b, all -> 0x0183 }
        r11 = r11.booleanValue();	 Catch:{ Throwable -> 0x018b, all -> 0x0183 }
        if (r11 != 0) goto L_0x0115;
    L_0x0111:
        r9.cancel(r2);
        return r3;
    L_0x0115:
        r0.L$0 = r5;	 Catch:{ Throwable -> 0x018b, all -> 0x0183 }
        r0.L$1 = r4;	 Catch:{ Throwable -> 0x018b, all -> 0x0183 }
        r0.L$2 = r9;	 Catch:{ Throwable -> 0x018b, all -> 0x0183 }
        r0.L$3 = r2;	 Catch:{ Throwable -> 0x018b, all -> 0x0183 }
        r0.L$4 = r6;	 Catch:{ Throwable -> 0x018b, all -> 0x0183 }
        r0.L$5 = r10;	 Catch:{ Throwable -> 0x018b, all -> 0x0183 }
        r11 = 2;	 Catch:{ Throwable -> 0x018b, all -> 0x0183 }
        r0.label = r11;	 Catch:{ Throwable -> 0x018b, all -> 0x0183 }
        r11 = r10.next(r0);	 Catch:{ Throwable -> 0x018b, all -> 0x0183 }
        if (r11 != r1) goto L_0x012b;
    L_0x012a:
        return r1;
    L_0x012b:
        r3 = r2;
        r2 = r9;
        r9 = r10;
        r10 = r6;
    L_0x012f:
        r0.L$0 = r5;	 Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
        r0.L$1 = r4;	 Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
        r0.L$2 = r2;	 Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
        r0.L$3 = r3;	 Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
        r0.L$4 = r10;	 Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
        r0.L$5 = r9;	 Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
        r0.L$6 = r11;	 Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
        r6 = 3;	 Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
        r0.label = r6;	 Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
        r6 = r9.hasNext(r0);	 Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
        if (r6 != r1) goto L_0x0147;	 Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
    L_0x0146:
        return r1;	 Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
    L_0x0147:
        r8 = r10;	 Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
        r10 = r9;	 Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
        r9 = r11;	 Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
        r11 = r6;	 Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
        r6 = r5;	 Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
        r5 = r4;	 Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
        r4 = r1;	 Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
        r1 = r8;	 Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
    L_0x014f:
        r11 = (java.lang.Boolean) r11;	 Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
        r11 = r11.booleanValue();	 Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
        if (r11 == 0) goto L_0x017f;	 Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
    L_0x0157:
        r0.L$0 = r6;	 Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
        r0.L$1 = r5;	 Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
        r0.L$2 = r2;	 Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
        r0.L$3 = r3;	 Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
        r0.L$4 = r1;	 Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
        r0.L$5 = r10;	 Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
        r0.L$6 = r9;	 Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
        r11 = 4;	 Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
        r0.label = r11;	 Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
        r11 = r10.next(r0);	 Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
        if (r11 != r4) goto L_0x016f;	 Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
    L_0x016e:
        return r4;	 Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
    L_0x016f:
        r8 = r6;	 Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
        r6 = r4;	 Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
        goto L_0x004f;	 Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
    L_0x0173:
        r7 = r4.compare(r9, r11);	 Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
        if (r7 >= 0) goto L_0x017a;
    L_0x0179:
        goto L_0x017b;
    L_0x017a:
        r11 = r9;
    L_0x017b:
        r9 = r10;
        r10 = r1;
        r1 = r6;
        goto L_0x012f;
    L_0x017f:
        r2.cancel(r3);
        return r9;
    L_0x0183:
        r10 = move-exception;
        r3 = r2;
        goto L_0x018f;
    L_0x0186:
        r10 = move-exception;
        r2 = r9;
        r9 = r10;
        r3 = r11;
        goto L_0x0191;
    L_0x018b:
        r10 = move-exception;
        r3 = r10;
    L_0x018d:
        throw r3;	 Catch:{ all -> 0x018e }
    L_0x018e:
        r10 = move-exception;
    L_0x018f:
        r2 = r9;
        r9 = r10;
    L_0x0191:
        r2.cancel(r3);
        throw r9;
    L_0x0195:
        r11 = (kotlin.Result.Failure) r11;
        r9 = r11.exception;
        throw r9;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.maxWith(kotlinx.coroutines.channels.ReceiveChannel, java.util.Comparator, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @org.jetbrains.annotations.Nullable
    @kotlinx.coroutines.ObsoleteCoroutinesApi
    public static final <E, R extends java.lang.Comparable<? super R>> java.lang.Object minBy(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r18, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super E, ? extends R> r19, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super E> r20) {
        /*
        r1 = r18;
        r0 = r20;
        r2 = r0 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$minBy$1;
        if (r2 == 0) goto L_0x0018;
    L_0x0008:
        r2 = r0;
        r2 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$minBy$1) r2;
        r3 = r2.label;
        r4 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r3 = r3 & r4;
        if (r3 == 0) goto L_0x0018;
    L_0x0012:
        r0 = r2.label;
        r0 = r0 - r4;
        r2.label = r0;
        goto L_0x001d;
    L_0x0018:
        r2 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$minBy$1;
        r2.<init>(r0);
    L_0x001d:
        r0 = r2.result;
        r3 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        r4 = r2.label;
        r5 = 0;
        r6 = 2;
        r7 = 3;
        r8 = 1;
        switch(r4) {
            case 0: goto L_0x0106;
            case 1: goto L_0x00d2;
            case 2: goto L_0x00a5;
            case 3: goto L_0x006c;
            case 4: goto L_0x0034;
            default: goto L_0x002c;
        };
    L_0x002c:
        r0 = new java.lang.IllegalStateException;
        r1 = "call to 'resume' before 'invoke' with coroutine";
        r0.<init>(r1);
        throw r0;
    L_0x0034:
        r1 = r2.L$7;
        r1 = (java.lang.Comparable) r1;
        r4 = r2.L$6;
        r5 = r2.L$5;
        r5 = (kotlinx.coroutines.channels.ChannelIterator) r5;
        r9 = r2.L$4;
        r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9;
        r10 = r2.L$3;
        r10 = (java.lang.Throwable) r10;
        r11 = r2.L$2;
        r11 = (kotlinx.coroutines.channels.ReceiveChannel) r11;
        r12 = r2.L$1;
        r12 = (kotlin.jvm.functions.Function1) r12;
        r13 = r2.L$0;
        r13 = (kotlinx.coroutines.channels.ReceiveChannel) r13;
        r14 = r0 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x00a0, all -> 0x009c }
        if (r14 != 0) goto L_0x0067;
    L_0x0056:
        r16 = r13;
        r13 = r0;
        r0 = r1;
        r1 = r5;
        r5 = r11;
        r11 = r16;
    L_0x005e:
        r17 = r12;
        r12 = r3;
        r3 = r4;
        r4 = r9;
        r9 = r17;
        goto L_0x01ae;
    L_0x0067:
        r0 = (kotlin.Result.Failure) r0;	 Catch:{ Throwable -> 0x00a0, all -> 0x009c }
        r0 = r0.exception;	 Catch:{ Throwable -> 0x00a0, all -> 0x009c }
        throw r0;	 Catch:{ Throwable -> 0x00a0, all -> 0x009c }
    L_0x006c:
        r1 = r2.L$7;
        r1 = (java.lang.Comparable) r1;
        r4 = r2.L$6;
        r5 = r2.L$5;
        r5 = (kotlinx.coroutines.channels.ChannelIterator) r5;
        r9 = r2.L$4;
        r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9;
        r10 = r2.L$3;
        r10 = (java.lang.Throwable) r10;
        r11 = r2.L$2;
        r11 = (kotlinx.coroutines.channels.ReceiveChannel) r11;
        r12 = r2.L$1;
        r12 = (kotlin.jvm.functions.Function1) r12;
        r13 = r2.L$0;
        r13 = (kotlinx.coroutines.channels.ReceiveChannel) r13;
        r14 = r0 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x00a0, all -> 0x009c }
        if (r14 != 0) goto L_0x0097;
    L_0x008e:
        r16 = r13;
        r13 = r0;
        r0 = r1;
        r1 = r11;
        r11 = r16;
        goto L_0x0185;
    L_0x0097:
        r0 = (kotlin.Result.Failure) r0;	 Catch:{ Throwable -> 0x00a0, all -> 0x009c }
        r0 = r0.exception;	 Catch:{ Throwable -> 0x00a0, all -> 0x009c }
        throw r0;	 Catch:{ Throwable -> 0x00a0, all -> 0x009c }
    L_0x009c:
        r0 = move-exception;
        r1 = r11;
        goto L_0x01d0;
    L_0x00a0:
        r0 = move-exception;
        r10 = r0;
        r1 = r11;
        goto L_0x01cf;
    L_0x00a5:
        r1 = r2.L$5;
        r1 = (kotlinx.coroutines.channels.ChannelIterator) r1;
        r4 = r2.L$4;
        r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4;
        r5 = r2.L$3;
        r10 = r5;
        r10 = (java.lang.Throwable) r10;
        r5 = r2.L$2;
        r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5;
        r9 = r2.L$1;
        r9 = (kotlin.jvm.functions.Function1) r9;
        r11 = r2.L$0;
        r11 = (kotlinx.coroutines.channels.ReceiveChannel) r11;
        r12 = r0 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x00cd, all -> 0x00c9 }
        if (r12 != 0) goto L_0x00c4;
    L_0x00c2:
        goto L_0x015a;
    L_0x00c4:
        r0 = (kotlin.Result.Failure) r0;	 Catch:{ Throwable -> 0x00cd, all -> 0x00c9 }
        r0 = r0.exception;	 Catch:{ Throwable -> 0x00cd, all -> 0x00c9 }
        throw r0;	 Catch:{ Throwable -> 0x00cd, all -> 0x00c9 }
    L_0x00c9:
        r0 = move-exception;
        r1 = r5;
        goto L_0x01d0;
    L_0x00cd:
        r0 = move-exception;
        r10 = r0;
        r1 = r5;
        goto L_0x01cf;
    L_0x00d2:
        r1 = r2.L$5;
        r1 = (kotlinx.coroutines.channels.ChannelIterator) r1;
        r4 = r2.L$4;
        r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4;
        r9 = r2.L$3;
        r10 = r9;
        r10 = (java.lang.Throwable) r10;
        r9 = r2.L$2;
        r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9;
        r11 = r2.L$1;
        r11 = (kotlin.jvm.functions.Function1) r11;
        r12 = r2.L$0;
        r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12;
        r13 = r0 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x0101, all -> 0x00fd }
        if (r13 != 0) goto L_0x00f8;
    L_0x00ef:
        r16 = r4;
        r4 = r1;
        r1 = r9;
        r9 = r11;
        r11 = r12;
        r12 = r16;
        goto L_0x0130;
    L_0x00f8:
        r0 = (kotlin.Result.Failure) r0;	 Catch:{ Throwable -> 0x0101, all -> 0x00fd }
        r0 = r0.exception;	 Catch:{ Throwable -> 0x0101, all -> 0x00fd }
        throw r0;	 Catch:{ Throwable -> 0x0101, all -> 0x00fd }
    L_0x00fd:
        r0 = move-exception;
        r1 = r9;
        goto L_0x01d0;
    L_0x0101:
        r0 = move-exception;
        r10 = r0;
        r1 = r9;
        goto L_0x01cf;
    L_0x0106:
        r4 = r0 instanceof kotlin.Result.Failure;
        if (r4 != 0) goto L_0x01da;
    L_0x010a:
        r10 = r5;
        r10 = (java.lang.Throwable) r10;
        r0 = r18.iterator();	 Catch:{ Throwable -> 0x01cd }
        r2.L$0 = r1;	 Catch:{ Throwable -> 0x01cd }
        r4 = r19;
        r2.L$1 = r4;	 Catch:{ Throwable -> 0x01cd }
        r2.L$2 = r1;	 Catch:{ Throwable -> 0x01cd }
        r2.L$3 = r10;	 Catch:{ Throwable -> 0x01cd }
        r2.L$4 = r1;	 Catch:{ Throwable -> 0x01cd }
        r2.L$5 = r0;	 Catch:{ Throwable -> 0x01cd }
        r2.label = r8;	 Catch:{ Throwable -> 0x01cd }
        r9 = r0.hasNext(r2);	 Catch:{ Throwable -> 0x01cd }
        if (r9 != r3) goto L_0x0128;
    L_0x0127:
        return r3;
    L_0x0128:
        r11 = r1;
        r12 = r11;
        r16 = r4;
        r4 = r0;
        r0 = r9;
        r9 = r16;
    L_0x0130:
        r0 = (java.lang.Boolean) r0;	 Catch:{ Throwable -> 0x01cd }
        r0 = r0.booleanValue();	 Catch:{ Throwable -> 0x01cd }
        if (r0 != 0) goto L_0x0142;
    L_0x0138:
        kotlin.jvm.internal.InlineMarker.finallyStart(r7);
        r1.cancel(r10);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r7);
        return r5;
    L_0x0142:
        r2.L$0 = r11;	 Catch:{ Throwable -> 0x01cd }
        r2.L$1 = r9;	 Catch:{ Throwable -> 0x01cd }
        r2.L$2 = r1;	 Catch:{ Throwable -> 0x01cd }
        r2.L$3 = r10;	 Catch:{ Throwable -> 0x01cd }
        r2.L$4 = r12;	 Catch:{ Throwable -> 0x01cd }
        r2.L$5 = r4;	 Catch:{ Throwable -> 0x01cd }
        r2.label = r6;	 Catch:{ Throwable -> 0x01cd }
        r0 = r4.next(r2);	 Catch:{ Throwable -> 0x01cd }
        if (r0 != r3) goto L_0x0157;
    L_0x0156:
        return r3;
    L_0x0157:
        r5 = r1;
        r1 = r4;
        r4 = r12;
    L_0x015a:
        r12 = r9.invoke(r0);	 Catch:{ Throwable -> 0x00cd, all -> 0x00c9 }
        r12 = (java.lang.Comparable) r12;	 Catch:{ Throwable -> 0x00cd, all -> 0x00c9 }
    L_0x0160:
        r2.L$0 = r11;	 Catch:{ Throwable -> 0x00cd, all -> 0x00c9 }
        r2.L$1 = r9;	 Catch:{ Throwable -> 0x00cd, all -> 0x00c9 }
        r2.L$2 = r5;	 Catch:{ Throwable -> 0x00cd, all -> 0x00c9 }
        r2.L$3 = r10;	 Catch:{ Throwable -> 0x00cd, all -> 0x00c9 }
        r2.L$4 = r4;	 Catch:{ Throwable -> 0x00cd, all -> 0x00c9 }
        r2.L$5 = r1;	 Catch:{ Throwable -> 0x00cd, all -> 0x00c9 }
        r2.L$6 = r0;	 Catch:{ Throwable -> 0x00cd, all -> 0x00c9 }
        r2.L$7 = r12;	 Catch:{ Throwable -> 0x00cd, all -> 0x00c9 }
        r2.label = r7;	 Catch:{ Throwable -> 0x00cd, all -> 0x00c9 }
        r13 = r1.hasNext(r2);	 Catch:{ Throwable -> 0x00cd, all -> 0x00c9 }
        if (r13 != r3) goto L_0x0179;
    L_0x0178:
        return r3;
    L_0x0179:
        r16 = r4;
        r4 = r0;
        r0 = r12;
        r12 = r9;
        r9 = r16;
        r17 = r5;
        r5 = r1;
        r1 = r17;
    L_0x0185:
        r13 = (java.lang.Boolean) r13;	 Catch:{ Throwable -> 0x01cd }
        r13 = r13.booleanValue();	 Catch:{ Throwable -> 0x01cd }
        if (r13 == 0) goto L_0x01c1;
    L_0x018d:
        r2.L$0 = r11;	 Catch:{ Throwable -> 0x01cd }
        r2.L$1 = r12;	 Catch:{ Throwable -> 0x01cd }
        r2.L$2 = r1;	 Catch:{ Throwable -> 0x01cd }
        r2.L$3 = r10;	 Catch:{ Throwable -> 0x01cd }
        r2.L$4 = r9;	 Catch:{ Throwable -> 0x01cd }
        r2.L$5 = r5;	 Catch:{ Throwable -> 0x01cd }
        r2.L$6 = r4;	 Catch:{ Throwable -> 0x01cd }
        r2.L$7 = r0;	 Catch:{ Throwable -> 0x01cd }
        r13 = 4;
        r2.label = r13;	 Catch:{ Throwable -> 0x01cd }
        r13 = r5.next(r2);	 Catch:{ Throwable -> 0x01cd }
        if (r13 != r3) goto L_0x01a7;
    L_0x01a6:
        return r3;
    L_0x01a7:
        r16 = r5;
        r5 = r1;
        r1 = r16;
        goto L_0x005e;
    L_0x01ae:
        r14 = r9.invoke(r13);	 Catch:{ Throwable -> 0x00cd, all -> 0x00c9 }
        r14 = (java.lang.Comparable) r14;	 Catch:{ Throwable -> 0x00cd, all -> 0x00c9 }
        r15 = r0.compareTo(r14);	 Catch:{ Throwable -> 0x00cd, all -> 0x00c9 }
        if (r15 <= 0) goto L_0x01bc;
    L_0x01ba:
        r0 = r13;
        goto L_0x01be;
    L_0x01bc:
        r14 = r0;
        r0 = r3;
    L_0x01be:
        r3 = r12;
        r12 = r14;
        goto L_0x0160;
    L_0x01c1:
        kotlin.jvm.internal.InlineMarker.finallyStart(r6);
        r1.cancel(r10);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r6);
        return r4;
    L_0x01cb:
        r0 = move-exception;
        goto L_0x01d0;
    L_0x01cd:
        r0 = move-exception;
        r10 = r0;
    L_0x01cf:
        throw r10;	 Catch:{ all -> 0x01cb }
    L_0x01d0:
        kotlin.jvm.internal.InlineMarker.finallyStart(r8);
        r1.cancel(r10);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r8);
        throw r0;
    L_0x01da:
        r0 = (kotlin.Result.Failure) r0;
        r0 = r0.exception;
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.minBy(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @org.jetbrains.annotations.Nullable
    @kotlinx.coroutines.ObsoleteCoroutinesApi
    public static final <E> java.lang.Object minWith(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r9, @org.jetbrains.annotations.NotNull java.util.Comparator<? super E> r10, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super E> r11) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.minWith(kotlinx.coroutines.channels.ReceiveChannel, java.util.Comparator, kotlin.coroutines.Continuation):java.lang.Object. bs: [B:11:0x0047, B:39:0x00c7]
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:86)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1603198149.run(Unknown Source)
*/
        /*
        r0 = r11 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$minWith$1;
        if (r0 == 0) goto L_0x0014;
    L_0x0004:
        r0 = r11;
        r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$minWith$1) r0;
        r1 = r0.label;
        r2 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r1 = r1 & r2;
        if (r1 == 0) goto L_0x0014;
    L_0x000e:
        r11 = r0.label;
        r11 = r11 - r2;
        r0.label = r11;
        goto L_0x0019;
    L_0x0014:
        r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$minWith$1;
        r0.<init>(r11);
    L_0x0019:
        r11 = r0.result;
        r1 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        r2 = r0.label;
        r3 = 0;
        switch(r2) {
            case 0: goto L_0x00e1;
            case 1: goto L_0x00af;
            case 2: goto L_0x0083;
            case 3: goto L_0x0058;
            case 4: goto L_0x002d;
            default: goto L_0x0025;
        };
    L_0x0025:
        r9 = new java.lang.IllegalStateException;
        r10 = "call to 'resume' before 'invoke' with coroutine";
        r9.<init>(r10);
        throw r9;
    L_0x002d:
        r9 = r0.L$6;
        r10 = r0.L$5;
        r10 = (kotlinx.coroutines.channels.ChannelIterator) r10;
        r2 = r0.L$4;
        r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2;
        r3 = r0.L$3;
        r3 = (java.lang.Throwable) r3;
        r4 = r0.L$2;
        r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4;
        r5 = r0.L$1;
        r5 = (java.util.Comparator) r5;
        r6 = r0.L$0;
        r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6;
        r7 = r11 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x00dc, all -> 0x0081 }
        if (r7 != 0) goto L_0x0053;	 Catch:{ Throwable -> 0x00dc, all -> 0x0081 }
    L_0x004b:
        r8 = r6;	 Catch:{ Throwable -> 0x00dc, all -> 0x0081 }
        r6 = r1;	 Catch:{ Throwable -> 0x00dc, all -> 0x0081 }
        r1 = r2;	 Catch:{ Throwable -> 0x00dc, all -> 0x0081 }
        r2 = r4;	 Catch:{ Throwable -> 0x00dc, all -> 0x0081 }
    L_0x004f:
        r4 = r5;	 Catch:{ Throwable -> 0x00dc, all -> 0x0081 }
        r5 = r8;	 Catch:{ Throwable -> 0x00dc, all -> 0x0081 }
        goto L_0x0173;	 Catch:{ Throwable -> 0x00dc, all -> 0x0081 }
    L_0x0053:
        r11 = (kotlin.Result.Failure) r11;	 Catch:{ Throwable -> 0x00dc, all -> 0x0081 }
        r9 = r11.exception;	 Catch:{ Throwable -> 0x00dc, all -> 0x0081 }
        throw r9;	 Catch:{ Throwable -> 0x00dc, all -> 0x0081 }
    L_0x0058:
        r9 = r0.L$6;
        r10 = r0.L$5;
        r10 = (kotlinx.coroutines.channels.ChannelIterator) r10;
        r2 = r0.L$4;
        r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2;
        r3 = r0.L$3;
        r3 = (java.lang.Throwable) r3;
        r4 = r0.L$2;
        r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4;
        r5 = r0.L$1;
        r5 = (java.util.Comparator) r5;
        r6 = r0.L$0;
        r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6;
        r7 = r11 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x00dc, all -> 0x0081 }
        if (r7 != 0) goto L_0x007c;	 Catch:{ Throwable -> 0x00dc, all -> 0x0081 }
    L_0x0076:
        r8 = r4;	 Catch:{ Throwable -> 0x00dc, all -> 0x0081 }
        r4 = r1;	 Catch:{ Throwable -> 0x00dc, all -> 0x0081 }
        r1 = r2;	 Catch:{ Throwable -> 0x00dc, all -> 0x0081 }
        r2 = r8;	 Catch:{ Throwable -> 0x00dc, all -> 0x0081 }
        goto L_0x014f;	 Catch:{ Throwable -> 0x00dc, all -> 0x0081 }
    L_0x007c:
        r11 = (kotlin.Result.Failure) r11;	 Catch:{ Throwable -> 0x00dc, all -> 0x0081 }
        r9 = r11.exception;	 Catch:{ Throwable -> 0x00dc, all -> 0x0081 }
        throw r9;	 Catch:{ Throwable -> 0x00dc, all -> 0x0081 }
    L_0x0081:
        r9 = move-exception;
        goto L_0x00d9;
    L_0x0083:
        r9 = r0.L$5;
        r9 = (kotlinx.coroutines.channels.ChannelIterator) r9;
        r10 = r0.L$4;
        r10 = (kotlinx.coroutines.channels.ReceiveChannel) r10;
        r2 = r0.L$3;
        r3 = r2;
        r3 = (java.lang.Throwable) r3;
        r2 = r0.L$2;
        r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2;
        r4 = r0.L$1;
        r4 = (java.util.Comparator) r4;
        r5 = r0.L$0;
        r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5;
        r6 = r11 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
        if (r6 != 0) goto L_0x00a2;	 Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
    L_0x00a0:
        goto L_0x012f;	 Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
    L_0x00a2:
        r11 = (kotlin.Result.Failure) r11;	 Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
        r9 = r11.exception;	 Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
        throw r9;	 Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
    L_0x00a7:
        r9 = move-exception;
        goto L_0x0191;
    L_0x00aa:
        r9 = move-exception;
        r3 = r9;
        r9 = r2;
        goto L_0x018d;
    L_0x00af:
        r9 = r0.L$5;
        r9 = (kotlinx.coroutines.channels.ChannelIterator) r9;
        r10 = r0.L$4;
        r10 = (kotlinx.coroutines.channels.ReceiveChannel) r10;
        r2 = r0.L$3;
        r2 = (java.lang.Throwable) r2;
        r4 = r0.L$2;
        r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4;
        r5 = r0.L$1;
        r5 = (java.util.Comparator) r5;
        r6 = r0.L$0;
        r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6;
        r7 = r11 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x00dc, all -> 0x00d7 }
        if (r7 != 0) goto L_0x00d2;	 Catch:{ Throwable -> 0x00dc, all -> 0x00d7 }
    L_0x00cb:
        r8 = r10;	 Catch:{ Throwable -> 0x00dc, all -> 0x00d7 }
        r10 = r9;	 Catch:{ Throwable -> 0x00dc, all -> 0x00d7 }
        r9 = r4;	 Catch:{ Throwable -> 0x00dc, all -> 0x00d7 }
        r4 = r5;	 Catch:{ Throwable -> 0x00dc, all -> 0x00d7 }
        r5 = r6;	 Catch:{ Throwable -> 0x00dc, all -> 0x00d7 }
        r6 = r8;	 Catch:{ Throwable -> 0x00dc, all -> 0x00d7 }
        goto L_0x0109;	 Catch:{ Throwable -> 0x00dc, all -> 0x00d7 }
    L_0x00d2:
        r11 = (kotlin.Result.Failure) r11;	 Catch:{ Throwable -> 0x00dc, all -> 0x00d7 }
        r9 = r11.exception;	 Catch:{ Throwable -> 0x00dc, all -> 0x00d7 }
        throw r9;	 Catch:{ Throwable -> 0x00dc, all -> 0x00d7 }
    L_0x00d7:
        r9 = move-exception;
        r3 = r2;
    L_0x00d9:
        r2 = r4;
        goto L_0x0191;
    L_0x00dc:
        r9 = move-exception;
        r3 = r9;
        r9 = r4;
        goto L_0x018d;
    L_0x00e1:
        r2 = r11 instanceof kotlin.Result.Failure;
        if (r2 != 0) goto L_0x0195;
    L_0x00e5:
        r11 = r3;
        r11 = (java.lang.Throwable) r11;
        r2 = r9.iterator();	 Catch:{ Throwable -> 0x018b, all -> 0x0186 }
        r0.L$0 = r9;	 Catch:{ Throwable -> 0x018b, all -> 0x0186 }
        r0.L$1 = r10;	 Catch:{ Throwable -> 0x018b, all -> 0x0186 }
        r0.L$2 = r9;	 Catch:{ Throwable -> 0x018b, all -> 0x0186 }
        r0.L$3 = r11;	 Catch:{ Throwable -> 0x018b, all -> 0x0186 }
        r0.L$4 = r9;	 Catch:{ Throwable -> 0x018b, all -> 0x0186 }
        r0.L$5 = r2;	 Catch:{ Throwable -> 0x018b, all -> 0x0186 }
        r4 = 1;	 Catch:{ Throwable -> 0x018b, all -> 0x0186 }
        r0.label = r4;	 Catch:{ Throwable -> 0x018b, all -> 0x0186 }
        r4 = r2.hasNext(r0);	 Catch:{ Throwable -> 0x018b, all -> 0x0186 }
        if (r4 != r1) goto L_0x0102;
    L_0x0101:
        return r1;
    L_0x0102:
        r5 = r9;
        r6 = r5;
        r8 = r4;
        r4 = r10;
        r10 = r2;
        r2 = r11;
        r11 = r8;
    L_0x0109:
        r11 = (java.lang.Boolean) r11;	 Catch:{ Throwable -> 0x018b, all -> 0x0183 }
        r11 = r11.booleanValue();	 Catch:{ Throwable -> 0x018b, all -> 0x0183 }
        if (r11 != 0) goto L_0x0115;
    L_0x0111:
        r9.cancel(r2);
        return r3;
    L_0x0115:
        r0.L$0 = r5;	 Catch:{ Throwable -> 0x018b, all -> 0x0183 }
        r0.L$1 = r4;	 Catch:{ Throwable -> 0x018b, all -> 0x0183 }
        r0.L$2 = r9;	 Catch:{ Throwable -> 0x018b, all -> 0x0183 }
        r0.L$3 = r2;	 Catch:{ Throwable -> 0x018b, all -> 0x0183 }
        r0.L$4 = r6;	 Catch:{ Throwable -> 0x018b, all -> 0x0183 }
        r0.L$5 = r10;	 Catch:{ Throwable -> 0x018b, all -> 0x0183 }
        r11 = 2;	 Catch:{ Throwable -> 0x018b, all -> 0x0183 }
        r0.label = r11;	 Catch:{ Throwable -> 0x018b, all -> 0x0183 }
        r11 = r10.next(r0);	 Catch:{ Throwable -> 0x018b, all -> 0x0183 }
        if (r11 != r1) goto L_0x012b;
    L_0x012a:
        return r1;
    L_0x012b:
        r3 = r2;
        r2 = r9;
        r9 = r10;
        r10 = r6;
    L_0x012f:
        r0.L$0 = r5;	 Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
        r0.L$1 = r4;	 Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
        r0.L$2 = r2;	 Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
        r0.L$3 = r3;	 Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
        r0.L$4 = r10;	 Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
        r0.L$5 = r9;	 Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
        r0.L$6 = r11;	 Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
        r6 = 3;	 Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
        r0.label = r6;	 Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
        r6 = r9.hasNext(r0);	 Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
        if (r6 != r1) goto L_0x0147;	 Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
    L_0x0146:
        return r1;	 Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
    L_0x0147:
        r8 = r10;	 Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
        r10 = r9;	 Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
        r9 = r11;	 Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
        r11 = r6;	 Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
        r6 = r5;	 Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
        r5 = r4;	 Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
        r4 = r1;	 Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
        r1 = r8;	 Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
    L_0x014f:
        r11 = (java.lang.Boolean) r11;	 Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
        r11 = r11.booleanValue();	 Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
        if (r11 == 0) goto L_0x017f;	 Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
    L_0x0157:
        r0.L$0 = r6;	 Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
        r0.L$1 = r5;	 Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
        r0.L$2 = r2;	 Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
        r0.L$3 = r3;	 Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
        r0.L$4 = r1;	 Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
        r0.L$5 = r10;	 Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
        r0.L$6 = r9;	 Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
        r11 = 4;	 Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
        r0.label = r11;	 Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
        r11 = r10.next(r0);	 Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
        if (r11 != r4) goto L_0x016f;	 Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
    L_0x016e:
        return r4;	 Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
    L_0x016f:
        r8 = r6;	 Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
        r6 = r4;	 Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
        goto L_0x004f;	 Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
    L_0x0173:
        r7 = r4.compare(r9, r11);	 Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
        if (r7 <= 0) goto L_0x017a;
    L_0x0179:
        goto L_0x017b;
    L_0x017a:
        r11 = r9;
    L_0x017b:
        r9 = r10;
        r10 = r1;
        r1 = r6;
        goto L_0x012f;
    L_0x017f:
        r2.cancel(r3);
        return r9;
    L_0x0183:
        r10 = move-exception;
        r3 = r2;
        goto L_0x018f;
    L_0x0186:
        r10 = move-exception;
        r2 = r9;
        r9 = r10;
        r3 = r11;
        goto L_0x0191;
    L_0x018b:
        r10 = move-exception;
        r3 = r10;
    L_0x018d:
        throw r3;	 Catch:{ all -> 0x018e }
    L_0x018e:
        r10 = move-exception;
    L_0x018f:
        r2 = r9;
        r9 = r10;
    L_0x0191:
        r2.cancel(r3);
        throw r9;
    L_0x0195:
        r11 = (kotlin.Result.Failure) r11;
        r9 = r11.exception;
        throw r9;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.minWith(kotlinx.coroutines.channels.ReceiveChannel, java.util.Comparator, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @org.jetbrains.annotations.Nullable
    @kotlinx.coroutines.ObsoleteCoroutinesApi
    public static final <E> java.lang.Object none(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r5, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Boolean> r6) {
        /*
        r0 = r6 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$none$1;
        if (r0 == 0) goto L_0x0014;
    L_0x0004:
        r0 = r6;
        r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$none$1) r0;
        r1 = r0.label;
        r2 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r1 = r1 & r2;
        if (r1 == 0) goto L_0x0014;
    L_0x000e:
        r6 = r0.label;
        r6 = r6 - r2;
        r0.label = r6;
        goto L_0x0019;
    L_0x0014:
        r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$none$1;
        r0.<init>(r6);
    L_0x0019:
        r6 = r0.result;
        r1 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        r2 = r0.label;
        r3 = 1;
        switch(r2) {
            case 0: goto L_0x004f;
            case 1: goto L_0x002d;
            default: goto L_0x0025;
        };
    L_0x0025:
        r5 = new java.lang.IllegalStateException;
        r6 = "call to 'resume' before 'invoke' with coroutine";
        r5.<init>(r6);
        throw r5;
    L_0x002d:
        r5 = r0.L$3;
        r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5;
        r5 = r0.L$2;
        r5 = (java.lang.Throwable) r5;
        r1 = r0.L$1;
        r1 = (kotlinx.coroutines.channels.ReceiveChannel) r1;
        r0 = r0.L$0;
        r0 = (kotlinx.coroutines.channels.ReceiveChannel) r0;
        r0 = r6 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x004d }
        if (r0 != 0) goto L_0x0044;
    L_0x0041:
        r0 = r5;
        r5 = r1;
        goto L_0x006e;
    L_0x0044:
        r6 = (kotlin.Result.Failure) r6;	 Catch:{ Throwable -> 0x004d }
        r6 = r6.exception;	 Catch:{ Throwable -> 0x004d }
        throw r6;	 Catch:{ Throwable -> 0x004d }
    L_0x0049:
        r6 = move-exception;
        r0 = r5;
        r5 = r1;
        goto L_0x0088;
    L_0x004d:
        r5 = move-exception;
        goto L_0x0087;
    L_0x004f:
        r2 = r6 instanceof kotlin.Result.Failure;
        if (r2 != 0) goto L_0x008c;
    L_0x0053:
        r6 = 0;
        r6 = (java.lang.Throwable) r6;
        r2 = r5.iterator();	 Catch:{ Throwable -> 0x0084, all -> 0x007f }
        r0.L$0 = r5;	 Catch:{ Throwable -> 0x0084, all -> 0x007f }
        r0.L$1 = r5;	 Catch:{ Throwable -> 0x0084, all -> 0x007f }
        r0.L$2 = r6;	 Catch:{ Throwable -> 0x0084, all -> 0x007f }
        r0.L$3 = r5;	 Catch:{ Throwable -> 0x0084, all -> 0x007f }
        r0.label = r3;	 Catch:{ Throwable -> 0x0084, all -> 0x007f }
        r0 = r2.hasNext(r0);	 Catch:{ Throwable -> 0x0084, all -> 0x007f }
        if (r0 != r1) goto L_0x006b;
    L_0x006a:
        return r1;
    L_0x006b:
        r4 = r0;
        r0 = r6;
        r6 = r4;
    L_0x006e:
        r6 = (java.lang.Boolean) r6;	 Catch:{ Throwable -> 0x0084, all -> 0x007d }
        r6 = r6.booleanValue();	 Catch:{ Throwable -> 0x0084, all -> 0x007d }
        r6 = r6 ^ r3;
        r6 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r6);	 Catch:{ Throwable -> 0x0084, all -> 0x007d }
        r5.cancel(r0);
        return r6;
    L_0x007d:
        r6 = move-exception;
        goto L_0x0088;
    L_0x007f:
        r0 = move-exception;
        r4 = r0;
        r0 = r6;
        r6 = r4;
        goto L_0x0088;
    L_0x0084:
        r6 = move-exception;
        r1 = r5;
        r5 = r6;
    L_0x0087:
        throw r5;	 Catch:{ all -> 0x0049 }
    L_0x0088:
        r5.cancel(r0);
        throw r6;
    L_0x008c:
        r6 = (kotlin.Result.Failure) r6;
        r5 = r6.exception;
        throw r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.none(kotlinx.coroutines.channels.ReceiveChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @org.jetbrains.annotations.Nullable
    @kotlinx.coroutines.ObsoleteCoroutinesApi
    public static final <E> java.lang.Object none(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r11, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super E, java.lang.Boolean> r12, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Boolean> r13) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:37)
	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:61)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1603198149.run(Unknown Source)
*/
        /*
        r0 = r13 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$none$3;
        if (r0 == 0) goto L_0x0014;
    L_0x0004:
        r0 = r13;
        r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$none$3) r0;
        r1 = r0.label;
        r2 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r1 = r1 & r2;
        if (r1 == 0) goto L_0x0014;
    L_0x000e:
        r13 = r0.label;
        r13 = r13 - r2;
        r0.label = r13;
        goto L_0x0019;
    L_0x0014:
        r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$none$3;
        r0.<init>(r13);
    L_0x0019:
        r13 = r0.result;
        r1 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        r2 = r0.label;
        r3 = 2;
        r4 = 1;
        switch(r2) {
            case 0: goto L_0x0088;
            case 1: goto L_0x005a;
            case 2: goto L_0x002e;
            default: goto L_0x0026;
        };
    L_0x0026:
        r11 = new java.lang.IllegalStateException;
        r12 = "call to 'resume' before 'invoke' with coroutine";
        r11.<init>(r12);
        throw r11;
    L_0x002e:
        r11 = r0.L$6;
        r11 = (kotlinx.coroutines.channels.ChannelIterator) r11;
        r12 = r0.L$5;
        r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12;
        r2 = r0.L$4;
        r2 = (java.lang.Throwable) r2;
        r5 = r0.L$3;
        r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5;
        r6 = r0.L$2;
        r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6;
        r7 = r0.L$1;
        r7 = (kotlin.jvm.functions.Function1) r7;
        r8 = r0.L$0;
        r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8;
        r9 = r13 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
        if (r9 != 0) goto L_0x0055;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
    L_0x004e:
        r10 = r12;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
        r12 = r11;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
        r11 = r5;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
        r5 = r1;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
        r1 = r10;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
        goto L_0x00d2;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
    L_0x0055:
        r13 = (kotlin.Result.Failure) r13;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
        r11 = r13.exception;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
        throw r11;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
    L_0x005a:
        r11 = r0.L$6;
        r11 = (kotlinx.coroutines.channels.ChannelIterator) r11;
        r12 = r0.L$5;
        r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12;
        r2 = r0.L$4;
        r2 = (java.lang.Throwable) r2;
        r5 = r0.L$3;
        r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5;
        r6 = r0.L$2;
        r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6;
        r7 = r0.L$1;
        r7 = (kotlin.jvm.functions.Function1) r7;
        r8 = r0.L$0;
        r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8;
        r9 = r13 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
        if (r9 != 0) goto L_0x007b;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
    L_0x007a:
        goto L_0x00b3;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
    L_0x007b:
        r13 = (kotlin.Result.Failure) r13;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
        r11 = r13.exception;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
        throw r11;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
    L_0x0080:
        r11 = move-exception;
        goto L_0x0108;
    L_0x0083:
        r11 = move-exception;
        r2 = r11;
        r11 = r5;
        goto L_0x0107;
    L_0x0088:
        r2 = r13 instanceof kotlin.Result.Failure;
        if (r2 != 0) goto L_0x0112;
    L_0x008c:
        r13 = 0;
        r2 = r13;
        r2 = (java.lang.Throwable) r2;
        r13 = r11.iterator();	 Catch:{ Throwable -> 0x0105 }
        r6 = r11;	 Catch:{ Throwable -> 0x0105 }
        r8 = r6;	 Catch:{ Throwable -> 0x0105 }
        r7 = r12;	 Catch:{ Throwable -> 0x0105 }
        r12 = r8;	 Catch:{ Throwable -> 0x0105 }
    L_0x0098:
        r0.L$0 = r8;	 Catch:{ Throwable -> 0x0105 }
        r0.L$1 = r7;	 Catch:{ Throwable -> 0x0105 }
        r0.L$2 = r6;	 Catch:{ Throwable -> 0x0105 }
        r0.L$3 = r11;	 Catch:{ Throwable -> 0x0105 }
        r0.L$4 = r2;	 Catch:{ Throwable -> 0x0105 }
        r0.L$5 = r12;	 Catch:{ Throwable -> 0x0105 }
        r0.L$6 = r13;	 Catch:{ Throwable -> 0x0105 }
        r0.label = r4;	 Catch:{ Throwable -> 0x0105 }
        r5 = r13.hasNext(r0);	 Catch:{ Throwable -> 0x0105 }
        if (r5 != r1) goto L_0x00af;
    L_0x00ae:
        return r1;
    L_0x00af:
        r10 = r5;
        r5 = r11;
        r11 = r13;
        r13 = r10;
    L_0x00b3:
        r13 = (java.lang.Boolean) r13;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
        r13 = r13.booleanValue();	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
        if (r13 == 0) goto L_0x00f1;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
    L_0x00bb:
        r0.L$0 = r8;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
        r0.L$1 = r7;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
        r0.L$2 = r6;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
        r0.L$3 = r5;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
        r0.L$4 = r2;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
        r0.L$5 = r12;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
        r0.L$6 = r11;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
        r0.label = r3;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
        r13 = r11.next(r0);	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
        if (r13 != r1) goto L_0x004e;
    L_0x00d1:
        return r1;
    L_0x00d2:
        r13 = r7.invoke(r13);	 Catch:{ Throwable -> 0x0105 }
        r13 = (java.lang.Boolean) r13;	 Catch:{ Throwable -> 0x0105 }
        r13 = r13.booleanValue();	 Catch:{ Throwable -> 0x0105 }
        if (r13 == 0) goto L_0x00ed;	 Catch:{ Throwable -> 0x0105 }
    L_0x00de:
        r12 = 0;	 Catch:{ Throwable -> 0x0105 }
        r12 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r12);	 Catch:{ Throwable -> 0x0105 }
        kotlin.jvm.internal.InlineMarker.finallyStart(r3);
        r11.cancel(r2);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r3);
        return r12;
    L_0x00ed:
        r13 = r12;
        r12 = r1;
        r1 = r5;
        goto L_0x0098;
    L_0x00f1:
        r11 = kotlin.Unit.INSTANCE;	 Catch:{ Throwable -> 0x0083, all -> 0x0080 }
        kotlin.jvm.internal.InlineMarker.finallyStart(r4);
        r5.cancel(r2);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r4);
        r11 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r4);
        return r11;
    L_0x0101:
        r12 = move-exception;
        r5 = r11;
        r11 = r12;
        goto L_0x0108;
    L_0x0105:
        r12 = move-exception;
        r2 = r12;
    L_0x0107:
        throw r2;	 Catch:{ all -> 0x0101 }
    L_0x0108:
        kotlin.jvm.internal.InlineMarker.finallyStart(r4);
        r5.cancel(r2);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r4);
        throw r11;
    L_0x0112:
        r13 = (kotlin.Result.Failure) r13;
        r11 = r13.exception;
        throw r11;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.none(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @org.jetbrains.annotations.Nullable
    @kotlinx.coroutines.ObsoleteCoroutinesApi
    public static final <S, E extends S> java.lang.Object reduce(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r13, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function2<? super S, ? super E, ? extends S> r14, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super S> r15) {
        /*
        r0 = r15 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$reduce$1;
        if (r0 == 0) goto L_0x0014;
    L_0x0004:
        r0 = r15;
        r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$reduce$1) r0;
        r1 = r0.label;
        r2 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r1 = r1 & r2;
        if (r1 == 0) goto L_0x0014;
    L_0x000e:
        r15 = r0.label;
        r15 = r15 - r2;
        r0.label = r15;
        goto L_0x0019;
    L_0x0014:
        r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$reduce$1;
        r0.<init>(r15);
    L_0x0019:
        r15 = r0.result;
        r1 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        r2 = r0.label;
        r3 = 2;
        r4 = 1;
        switch(r2) {
            case 0: goto L_0x00ee;
            case 1: goto L_0x00c3;
            case 2: goto L_0x009f;
            case 3: goto L_0x0069;
            case 4: goto L_0x002e;
            default: goto L_0x0026;
        };
    L_0x0026:
        r13 = new java.lang.IllegalStateException;
        r14 = "call to 'resume' before 'invoke' with coroutine";
        r13.<init>(r14);
        throw r13;
    L_0x002e:
        r13 = r0.L$8;
        r13 = (kotlin.jvm.functions.Function2) r13;
        r14 = r0.L$7;
        r2 = r0.L$6;
        r2 = r0.L$5;
        r2 = (kotlinx.coroutines.channels.ChannelIterator) r2;
        r5 = r0.L$4;
        r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5;
        r6 = r0.L$3;
        r6 = (java.lang.Throwable) r6;
        r7 = r0.L$2;
        r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7;
        r8 = r0.L$1;
        r8 = (kotlin.jvm.functions.Function2) r8;
        r9 = r0.L$0;
        r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9;
        r10 = r15 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x0064, all -> 0x0060 }
        if (r10 != 0) goto L_0x005b;
    L_0x0052:
        r11 = r9;
        r9 = r1;
        r1 = r7;
        r7 = r11;
        r12 = r8;
        r8 = r5;
        r5 = r12;
        goto L_0x017d;
    L_0x005b:
        r15 = (kotlin.Result.Failure) r15;	 Catch:{ Throwable -> 0x0064, all -> 0x0060 }
        r13 = r15.exception;	 Catch:{ Throwable -> 0x0064, all -> 0x0060 }
        throw r13;	 Catch:{ Throwable -> 0x0064, all -> 0x0060 }
    L_0x0060:
        r13 = move-exception;
        r2 = r7;
        goto L_0x01a8;
    L_0x0064:
        r13 = move-exception;
        r6 = r13;
        r13 = r7;
        goto L_0x01a7;
    L_0x0069:
        r13 = r0.L$6;
        r14 = r0.L$5;
        r14 = (kotlinx.coroutines.channels.ChannelIterator) r14;
        r2 = r0.L$4;
        r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2;
        r5 = r0.L$3;
        r6 = r5;
        r6 = (java.lang.Throwable) r6;
        r5 = r0.L$2;
        r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5;
        r7 = r0.L$1;
        r7 = (kotlin.jvm.functions.Function2) r7;
        r8 = r0.L$0;
        r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8;
        r9 = r15 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x009a, all -> 0x0096 }
        if (r9 != 0) goto L_0x0091;
    L_0x0088:
        r9 = r8;
        r11 = r14;
        r14 = r13;
        r13 = r7;
        r7 = r1;
        r1 = r2;
        r2 = r11;
        goto L_0x0153;
    L_0x0091:
        r15 = (kotlin.Result.Failure) r15;	 Catch:{ Throwable -> 0x009a, all -> 0x0096 }
        r13 = r15.exception;	 Catch:{ Throwable -> 0x009a, all -> 0x0096 }
        throw r13;	 Catch:{ Throwable -> 0x009a, all -> 0x0096 }
    L_0x0096:
        r13 = move-exception;
        r2 = r5;
        goto L_0x01a8;
    L_0x009a:
        r13 = move-exception;
        r6 = r13;
        r13 = r5;
        goto L_0x01a7;
    L_0x009f:
        r13 = r0.L$5;
        r13 = (kotlinx.coroutines.channels.ChannelIterator) r13;
        r14 = r0.L$4;
        r14 = (kotlinx.coroutines.channels.ReceiveChannel) r14;
        r2 = r0.L$3;
        r6 = r2;
        r6 = (java.lang.Throwable) r6;
        r2 = r0.L$2;
        r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2;
        r5 = r0.L$1;
        r5 = (kotlin.jvm.functions.Function2) r5;
        r7 = r0.L$0;
        r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7;
        r8 = r15 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x00e9, all -> 0x00e6 }
        if (r8 != 0) goto L_0x00be;
    L_0x00bc:
        goto L_0x0132;
    L_0x00be:
        r15 = (kotlin.Result.Failure) r15;	 Catch:{ Throwable -> 0x00e9, all -> 0x00e6 }
        r13 = r15.exception;	 Catch:{ Throwable -> 0x00e9, all -> 0x00e6 }
        throw r13;	 Catch:{ Throwable -> 0x00e9, all -> 0x00e6 }
    L_0x00c3:
        r13 = r0.L$5;
        r13 = (kotlinx.coroutines.channels.ChannelIterator) r13;
        r14 = r0.L$4;
        r14 = (kotlinx.coroutines.channels.ReceiveChannel) r14;
        r2 = r0.L$3;
        r6 = r2;
        r6 = (java.lang.Throwable) r6;
        r2 = r0.L$2;
        r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2;
        r5 = r0.L$1;
        r5 = (kotlin.jvm.functions.Function2) r5;
        r7 = r0.L$0;
        r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7;
        r8 = r15 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x00e9, all -> 0x00e6 }
        if (r8 != 0) goto L_0x00e1;
    L_0x00e0:
        goto L_0x0115;
    L_0x00e1:
        r15 = (kotlin.Result.Failure) r15;	 Catch:{ Throwable -> 0x00e9, all -> 0x00e6 }
        r13 = r15.exception;	 Catch:{ Throwable -> 0x00e9, all -> 0x00e6 }
        throw r13;	 Catch:{ Throwable -> 0x00e9, all -> 0x00e6 }
    L_0x00e6:
        r13 = move-exception;
        goto L_0x01a8;
    L_0x00e9:
        r13 = move-exception;
        r6 = r13;
        r13 = r2;
        goto L_0x01a7;
    L_0x00ee:
        r2 = r15 instanceof kotlin.Result.Failure;
        if (r2 != 0) goto L_0x01b2;
    L_0x00f2:
        r15 = 0;
        r6 = r15;
        r6 = (java.lang.Throwable) r6;
        r15 = r13.iterator();	 Catch:{ Throwable -> 0x01a5 }
        r0.L$0 = r13;	 Catch:{ Throwable -> 0x01a5 }
        r0.L$1 = r14;	 Catch:{ Throwable -> 0x01a5 }
        r0.L$2 = r13;	 Catch:{ Throwable -> 0x01a5 }
        r0.L$3 = r6;	 Catch:{ Throwable -> 0x01a5 }
        r0.L$4 = r13;	 Catch:{ Throwable -> 0x01a5 }
        r0.L$5 = r15;	 Catch:{ Throwable -> 0x01a5 }
        r0.label = r4;	 Catch:{ Throwable -> 0x01a5 }
        r2 = r15.hasNext(r0);	 Catch:{ Throwable -> 0x01a5 }
        if (r2 != r1) goto L_0x010f;
    L_0x010e:
        return r1;
    L_0x010f:
        r7 = r13;
        r5 = r14;
        r14 = r7;
        r13 = r15;
        r15 = r2;
        r2 = r14;
    L_0x0115:
        r15 = (java.lang.Boolean) r15;	 Catch:{ Throwable -> 0x00e9, all -> 0x00e6 }
        r15 = r15.booleanValue();	 Catch:{ Throwable -> 0x00e9, all -> 0x00e6 }
        if (r15 == 0) goto L_0x0197;
    L_0x011d:
        r0.L$0 = r7;	 Catch:{ Throwable -> 0x00e9, all -> 0x00e6 }
        r0.L$1 = r5;	 Catch:{ Throwable -> 0x00e9, all -> 0x00e6 }
        r0.L$2 = r2;	 Catch:{ Throwable -> 0x00e9, all -> 0x00e6 }
        r0.L$3 = r6;	 Catch:{ Throwable -> 0x00e9, all -> 0x00e6 }
        r0.L$4 = r14;	 Catch:{ Throwable -> 0x00e9, all -> 0x00e6 }
        r0.L$5 = r13;	 Catch:{ Throwable -> 0x00e9, all -> 0x00e6 }
        r0.label = r3;	 Catch:{ Throwable -> 0x00e9, all -> 0x00e6 }
        r15 = r13.next(r0);	 Catch:{ Throwable -> 0x00e9, all -> 0x00e6 }
        if (r15 != r1) goto L_0x0132;
    L_0x0131:
        return r1;
    L_0x0132:
        r0.L$0 = r7;	 Catch:{ Throwable -> 0x00e9, all -> 0x00e6 }
        r0.L$1 = r5;	 Catch:{ Throwable -> 0x00e9, all -> 0x00e6 }
        r0.L$2 = r2;	 Catch:{ Throwable -> 0x00e9, all -> 0x00e6 }
        r0.L$3 = r6;	 Catch:{ Throwable -> 0x00e9, all -> 0x00e6 }
        r0.L$4 = r14;	 Catch:{ Throwable -> 0x00e9, all -> 0x00e6 }
        r0.L$5 = r13;	 Catch:{ Throwable -> 0x00e9, all -> 0x00e6 }
        r0.L$6 = r15;	 Catch:{ Throwable -> 0x00e9, all -> 0x00e6 }
        r8 = 3;
        r0.label = r8;	 Catch:{ Throwable -> 0x00e9, all -> 0x00e6 }
        r8 = r13.hasNext(r0);	 Catch:{ Throwable -> 0x00e9, all -> 0x00e6 }
        if (r8 != r1) goto L_0x014a;
    L_0x0149:
        return r1;
    L_0x014a:
        r9 = r7;
        r7 = r1;
        r1 = r14;
        r14 = r15;
        r15 = r8;
        r11 = r2;
        r2 = r13;
        r13 = r5;
        r5 = r11;
    L_0x0153:
        r15 = (java.lang.Boolean) r15;	 Catch:{ Throwable -> 0x009a, all -> 0x0096 }
        r15 = r15.booleanValue();	 Catch:{ Throwable -> 0x009a, all -> 0x0096 }
        if (r15 == 0) goto L_0x018d;
    L_0x015b:
        r0.L$0 = r9;	 Catch:{ Throwable -> 0x009a, all -> 0x0096 }
        r0.L$1 = r13;	 Catch:{ Throwable -> 0x009a, all -> 0x0096 }
        r0.L$2 = r5;	 Catch:{ Throwable -> 0x009a, all -> 0x0096 }
        r0.L$3 = r6;	 Catch:{ Throwable -> 0x009a, all -> 0x0096 }
        r0.L$4 = r1;	 Catch:{ Throwable -> 0x009a, all -> 0x0096 }
        r0.L$5 = r2;	 Catch:{ Throwable -> 0x009a, all -> 0x0096 }
        r0.L$6 = r14;	 Catch:{ Throwable -> 0x009a, all -> 0x0096 }
        r0.L$7 = r14;	 Catch:{ Throwable -> 0x009a, all -> 0x0096 }
        r0.L$8 = r13;	 Catch:{ Throwable -> 0x009a, all -> 0x0096 }
        r15 = 4;
        r0.label = r15;	 Catch:{ Throwable -> 0x009a, all -> 0x0096 }
        r15 = r2.next(r0);	 Catch:{ Throwable -> 0x009a, all -> 0x0096 }
        if (r15 != r7) goto L_0x0177;
    L_0x0176:
        return r7;
    L_0x0177:
        r8 = r1;
        r1 = r5;
        r5 = r13;
        r11 = r9;
        r9 = r7;
        r7 = r11;
    L_0x017d:
        r15 = r13.invoke(r14, r15);	 Catch:{ Throwable -> 0x0189, all -> 0x0186 }
        r13 = r2;
        r14 = r8;
        r2 = r1;
        r1 = r9;
        goto L_0x0132;
    L_0x0186:
        r13 = move-exception;
        r2 = r1;
        goto L_0x01a8;
    L_0x0189:
        r13 = move-exception;
        r6 = r13;
        r13 = r1;
        goto L_0x01a7;
    L_0x018d:
        kotlin.jvm.internal.InlineMarker.finallyStart(r3);
        r5.cancel(r6);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r3);
        return r14;
    L_0x0197:
        r13 = new java.lang.UnsupportedOperationException;	 Catch:{ Throwable -> 0x00e9, all -> 0x00e6 }
        r14 = "Empty channel can't be reduced.";
        r13.<init>(r14);	 Catch:{ Throwable -> 0x00e9, all -> 0x00e6 }
        r13 = (java.lang.Throwable) r13;	 Catch:{ Throwable -> 0x00e9, all -> 0x00e6 }
        throw r13;	 Catch:{ Throwable -> 0x00e9, all -> 0x00e6 }
    L_0x01a1:
        r14 = move-exception;
        r2 = r13;
        r13 = r14;
        goto L_0x01a8;
    L_0x01a5:
        r14 = move-exception;
        r6 = r14;
    L_0x01a7:
        throw r6;	 Catch:{ all -> 0x01a1 }
    L_0x01a8:
        kotlin.jvm.internal.InlineMarker.finallyStart(r4);
        r2.cancel(r6);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r4);
        throw r13;
    L_0x01b2:
        r15 = (kotlin.Result.Failure) r15;
        r13 = r15.exception;
        throw r13;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.reduce(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @org.jetbrains.annotations.Nullable
    @kotlinx.coroutines.ObsoleteCoroutinesApi
    public static final <S, E extends S> java.lang.Object reduceIndexed(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r18, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function3<? super java.lang.Integer, ? super S, ? super E, ? extends S> r19, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super S> r20) {
        /*
        r1 = r18;
        r0 = r20;
        r2 = r0 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$reduceIndexed$1;
        if (r2 == 0) goto L_0x0018;
    L_0x0008:
        r2 = r0;
        r2 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$reduceIndexed$1) r2;
        r3 = r2.label;
        r4 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r3 = r3 & r4;
        if (r3 == 0) goto L_0x0018;
    L_0x0012:
        r0 = r2.label;
        r0 = r0 - r4;
        r2.label = r0;
        goto L_0x001d;
    L_0x0018:
        r2 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$reduceIndexed$1;
        r2.<init>(r0);
    L_0x001d:
        r0 = r2.result;
        r3 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        r4 = r2.label;
        r5 = 2;
        r6 = 1;
        switch(r4) {
            case 0: goto L_0x0115;
            case 1: goto L_0x00e1;
            case 2: goto L_0x00ad;
            case 3: goto L_0x0072;
            case 4: goto L_0x0032;
            default: goto L_0x002a;
        };
    L_0x002a:
        r0 = new java.lang.IllegalStateException;
        r1 = "call to 'resume' before 'invoke' with coroutine";
        r0.<init>(r1);
        throw r0;
    L_0x0032:
        r1 = r2.L$9;
        r1 = (kotlin.jvm.functions.Function3) r1;
        r4 = r2.L$8;
        r4 = (java.lang.Integer) r4;
        r7 = r2.L$7;
        r8 = r2.L$6;
        r8 = r2.I$0;
        r9 = r2.L$5;
        r9 = (kotlinx.coroutines.channels.ChannelIterator) r9;
        r10 = r2.L$4;
        r10 = (kotlinx.coroutines.channels.ReceiveChannel) r10;
        r11 = r2.L$3;
        r11 = (java.lang.Throwable) r11;
        r12 = r2.L$2;
        r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12;
        r13 = r2.L$1;
        r13 = (kotlin.jvm.functions.Function3) r13;
        r14 = r2.L$0;
        r14 = (kotlinx.coroutines.channels.ReceiveChannel) r14;
        r15 = r0 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x006d, all -> 0x0069 }
        if (r15 != 0) goto L_0x0064;
    L_0x005c:
        r16 = r14;
        r14 = r3;
        r3 = r10;
        r10 = r16;
        goto L_0x01c2;
    L_0x0064:
        r0 = (kotlin.Result.Failure) r0;	 Catch:{ Throwable -> 0x006d, all -> 0x0069 }
        r0 = r0.exception;	 Catch:{ Throwable -> 0x006d, all -> 0x0069 }
        throw r0;	 Catch:{ Throwable -> 0x006d, all -> 0x0069 }
    L_0x0069:
        r0 = move-exception;
        r1 = r12;
        goto L_0x01e5;
    L_0x006d:
        r0 = move-exception;
        r11 = r0;
        r1 = r12;
        goto L_0x01e4;
    L_0x0072:
        r1 = r2.L$6;
        r4 = r2.I$0;
        r7 = r2.L$5;
        r7 = (kotlinx.coroutines.channels.ChannelIterator) r7;
        r8 = r2.L$4;
        r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8;
        r9 = r2.L$3;
        r11 = r9;
        r11 = (java.lang.Throwable) r11;
        r9 = r2.L$2;
        r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9;
        r10 = r2.L$1;
        r10 = (kotlin.jvm.functions.Function3) r10;
        r12 = r2.L$0;
        r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12;
        r13 = r0 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x00a8, all -> 0x00a4 }
        if (r13 != 0) goto L_0x009f;
    L_0x0093:
        r14 = r12;
        r16 = r7;
        r7 = r1;
        r1 = r10;
        r10 = r8;
        r8 = r3;
        r3 = r2;
        r2 = r16;
        goto L_0x0187;
    L_0x009f:
        r0 = (kotlin.Result.Failure) r0;	 Catch:{ Throwable -> 0x00a8, all -> 0x00a4 }
        r0 = r0.exception;	 Catch:{ Throwable -> 0x00a8, all -> 0x00a4 }
        throw r0;	 Catch:{ Throwable -> 0x00a8, all -> 0x00a4 }
    L_0x00a4:
        r0 = move-exception;
        r1 = r9;
        goto L_0x01e5;
    L_0x00a8:
        r0 = move-exception;
        r11 = r0;
        r1 = r9;
        goto L_0x01e4;
    L_0x00ad:
        r1 = r2.I$0;
        r4 = r2.L$5;
        r4 = (kotlinx.coroutines.channels.ChannelIterator) r4;
        r7 = r2.L$4;
        r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7;
        r8 = r2.L$3;
        r11 = r8;
        r11 = (java.lang.Throwable) r11;
        r8 = r2.L$2;
        r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8;
        r9 = r2.L$1;
        r9 = (kotlin.jvm.functions.Function3) r9;
        r10 = r2.L$0;
        r10 = (kotlinx.coroutines.channels.ReceiveChannel) r10;
        r12 = r0 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x00dc, all -> 0x00d8 }
        if (r12 != 0) goto L_0x00d3;
    L_0x00cc:
        r16 = r8;
        r8 = r1;
        r1 = r16;
        goto L_0x015d;
    L_0x00d3:
        r0 = (kotlin.Result.Failure) r0;	 Catch:{ Throwable -> 0x00dc, all -> 0x00d8 }
        r0 = r0.exception;	 Catch:{ Throwable -> 0x00dc, all -> 0x00d8 }
        throw r0;	 Catch:{ Throwable -> 0x00dc, all -> 0x00d8 }
    L_0x00d8:
        r0 = move-exception;
        r1 = r8;
        goto L_0x01e5;
    L_0x00dc:
        r0 = move-exception;
        r11 = r0;
        r1 = r8;
        goto L_0x01e4;
    L_0x00e1:
        r1 = r2.L$5;
        r1 = (kotlinx.coroutines.channels.ChannelIterator) r1;
        r4 = r2.L$4;
        r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4;
        r7 = r2.L$3;
        r11 = r7;
        r11 = (java.lang.Throwable) r11;
        r7 = r2.L$2;
        r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7;
        r8 = r2.L$1;
        r8 = (kotlin.jvm.functions.Function3) r8;
        r9 = r2.L$0;
        r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9;
        r10 = r0 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x0110, all -> 0x010c }
        if (r10 != 0) goto L_0x0107;
    L_0x00fe:
        r10 = r9;
        r9 = r8;
        r16 = r4;
        r4 = r1;
        r1 = r7;
        r7 = r16;
        goto L_0x013d;
    L_0x0107:
        r0 = (kotlin.Result.Failure) r0;	 Catch:{ Throwable -> 0x0110, all -> 0x010c }
        r0 = r0.exception;	 Catch:{ Throwable -> 0x0110, all -> 0x010c }
        throw r0;	 Catch:{ Throwable -> 0x0110, all -> 0x010c }
    L_0x010c:
        r0 = move-exception;
        r1 = r7;
        goto L_0x01e5;
    L_0x0110:
        r0 = move-exception;
        r11 = r0;
        r1 = r7;
        goto L_0x01e4;
    L_0x0115:
        r4 = r0 instanceof kotlin.Result.Failure;
        if (r4 != 0) goto L_0x01ef;
    L_0x0119:
        r0 = 0;
        r11 = r0;
        r11 = (java.lang.Throwable) r11;
        r0 = r18.iterator();	 Catch:{ Throwable -> 0x01e2 }
        r2.L$0 = r1;	 Catch:{ Throwable -> 0x01e2 }
        r4 = r19;
        r2.L$1 = r4;	 Catch:{ Throwable -> 0x01e2 }
        r2.L$2 = r1;	 Catch:{ Throwable -> 0x01e2 }
        r2.L$3 = r11;	 Catch:{ Throwable -> 0x01e2 }
        r2.L$4 = r1;	 Catch:{ Throwable -> 0x01e2 }
        r2.L$5 = r0;	 Catch:{ Throwable -> 0x01e2 }
        r2.label = r6;	 Catch:{ Throwable -> 0x01e2 }
        r7 = r0.hasNext(r2);	 Catch:{ Throwable -> 0x01e2 }
        if (r7 != r3) goto L_0x0138;
    L_0x0137:
        return r3;
    L_0x0138:
        r10 = r1;
        r9 = r4;
        r4 = r0;
        r0 = r7;
        r7 = r10;
    L_0x013d:
        r0 = (java.lang.Boolean) r0;	 Catch:{ Throwable -> 0x01e2 }
        r0 = r0.booleanValue();	 Catch:{ Throwable -> 0x01e2 }
        if (r0 == 0) goto L_0x01d6;
    L_0x0145:
        r2.L$0 = r10;	 Catch:{ Throwable -> 0x01e2 }
        r2.L$1 = r9;	 Catch:{ Throwable -> 0x01e2 }
        r2.L$2 = r1;	 Catch:{ Throwable -> 0x01e2 }
        r2.L$3 = r11;	 Catch:{ Throwable -> 0x01e2 }
        r2.L$4 = r7;	 Catch:{ Throwable -> 0x01e2 }
        r2.L$5 = r4;	 Catch:{ Throwable -> 0x01e2 }
        r2.I$0 = r6;	 Catch:{ Throwable -> 0x01e2 }
        r2.label = r5;	 Catch:{ Throwable -> 0x01e2 }
        r0 = r4.next(r2);	 Catch:{ Throwable -> 0x01e2 }
        if (r0 != r3) goto L_0x015c;
    L_0x015b:
        return r3;
    L_0x015c:
        r8 = 1;
    L_0x015d:
        r2.L$0 = r10;	 Catch:{ Throwable -> 0x01e2 }
        r2.L$1 = r9;	 Catch:{ Throwable -> 0x01e2 }
        r2.L$2 = r1;	 Catch:{ Throwable -> 0x01e2 }
        r2.L$3 = r11;	 Catch:{ Throwable -> 0x01e2 }
        r2.L$4 = r7;	 Catch:{ Throwable -> 0x01e2 }
        r2.L$5 = r4;	 Catch:{ Throwable -> 0x01e2 }
        r2.I$0 = r8;	 Catch:{ Throwable -> 0x01e2 }
        r2.L$6 = r0;	 Catch:{ Throwable -> 0x01e2 }
        r12 = 3;
        r2.label = r12;	 Catch:{ Throwable -> 0x01e2 }
        r12 = r4.hasNext(r2);	 Catch:{ Throwable -> 0x01e2 }
        if (r12 != r3) goto L_0x0177;
    L_0x0176:
        return r3;
    L_0x0177:
        r14 = r10;
        r10 = r7;
        r7 = r0;
        r0 = r12;
        r16 = r9;
        r9 = r1;
        r1 = r16;
        r17 = r3;
        r3 = r2;
        r2 = r4;
        r4 = r8;
        r8 = r17;
    L_0x0187:
        r0 = (java.lang.Boolean) r0;	 Catch:{ Throwable -> 0x00a8, all -> 0x00a4 }
        r0 = r0.booleanValue();	 Catch:{ Throwable -> 0x00a8, all -> 0x00a4 }
        if (r0 == 0) goto L_0x01cc;
    L_0x018f:
        r0 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r4);	 Catch:{ Throwable -> 0x00a8, all -> 0x00a4 }
        r4 = r4 + 1;
        r3.L$0 = r14;	 Catch:{ Throwable -> 0x00a8, all -> 0x00a4 }
        r3.L$1 = r1;	 Catch:{ Throwable -> 0x00a8, all -> 0x00a4 }
        r3.L$2 = r9;	 Catch:{ Throwable -> 0x00a8, all -> 0x00a4 }
        r3.L$3 = r11;	 Catch:{ Throwable -> 0x00a8, all -> 0x00a4 }
        r3.L$4 = r10;	 Catch:{ Throwable -> 0x00a8, all -> 0x00a4 }
        r3.L$5 = r2;	 Catch:{ Throwable -> 0x00a8, all -> 0x00a4 }
        r3.I$0 = r4;	 Catch:{ Throwable -> 0x00a8, all -> 0x00a4 }
        r3.L$6 = r7;	 Catch:{ Throwable -> 0x00a8, all -> 0x00a4 }
        r3.L$7 = r7;	 Catch:{ Throwable -> 0x00a8, all -> 0x00a4 }
        r3.L$8 = r0;	 Catch:{ Throwable -> 0x00a8, all -> 0x00a4 }
        r3.L$9 = r1;	 Catch:{ Throwable -> 0x00a8, all -> 0x00a4 }
        r12 = 4;
        r3.label = r12;	 Catch:{ Throwable -> 0x00a8, all -> 0x00a4 }
        r12 = r2.next(r3);	 Catch:{ Throwable -> 0x00a8, all -> 0x00a4 }
        if (r12 != r8) goto L_0x01b5;
    L_0x01b4:
        return r8;
    L_0x01b5:
        r13 = r1;
        r16 = r4;
        r4 = r0;
        r0 = r12;
        r12 = r9;
        r9 = r2;
        r2 = r3;
        r3 = r10;
        r10 = r14;
        r14 = r8;
        r8 = r16;
    L_0x01c2:
        r0 = r1.invoke(r4, r7, r0);	 Catch:{ Throwable -> 0x006d, all -> 0x0069 }
        r7 = r3;
        r4 = r9;
        r1 = r12;
        r9 = r13;
        r3 = r14;
        goto L_0x015d;
    L_0x01cc:
        kotlin.jvm.internal.InlineMarker.finallyStart(r5);
        r9.cancel(r11);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r5);
        return r7;
    L_0x01d6:
        r0 = new java.lang.UnsupportedOperationException;	 Catch:{ Throwable -> 0x01e2 }
        r2 = "Empty channel can't be reduced.";
        r0.<init>(r2);	 Catch:{ Throwable -> 0x01e2 }
        r0 = (java.lang.Throwable) r0;	 Catch:{ Throwable -> 0x01e2 }
        throw r0;	 Catch:{ Throwable -> 0x01e2 }
    L_0x01e0:
        r0 = move-exception;
        goto L_0x01e5;
    L_0x01e2:
        r0 = move-exception;
        r11 = r0;
    L_0x01e4:
        throw r11;	 Catch:{ all -> 0x01e0 }
    L_0x01e5:
        kotlin.jvm.internal.InlineMarker.finallyStart(r6);
        r1.cancel(r11);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r6);
        throw r0;
    L_0x01ef:
        r0 = (kotlin.Result.Failure) r0;
        r0 = r0.exception;
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.reduceIndexed(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function3, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @org.jetbrains.annotations.Nullable
    @kotlinx.coroutines.ObsoleteCoroutinesApi
    public static final <E> java.lang.Object sumBy(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r12, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super E, java.lang.Integer> r13, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Integer> r14) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:37)
	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:61)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1603198149.run(Unknown Source)
*/
        /*
        r0 = r14 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$sumBy$1;
        if (r0 == 0) goto L_0x0014;
    L_0x0004:
        r0 = r14;
        r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$sumBy$1) r0;
        r1 = r0.label;
        r2 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r1 = r1 & r2;
        if (r1 == 0) goto L_0x0014;
    L_0x000e:
        r14 = r0.label;
        r14 = r14 - r2;
        r0.label = r14;
        goto L_0x0019;
    L_0x0014:
        r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$sumBy$1;
        r0.<init>(r14);
    L_0x0019:
        r14 = r0.result;
        r1 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        r2 = r0.label;
        r3 = 1;
        switch(r2) {
            case 0: goto L_0x008d;
            case 1: goto L_0x005b;
            case 2: goto L_0x002d;
            default: goto L_0x0025;
        };
    L_0x0025:
        r12 = new java.lang.IllegalStateException;
        r13 = "call to 'resume' before 'invoke' with coroutine";
        r12.<init>(r13);
        throw r12;
    L_0x002d:
        r12 = r0.L$7;
        r12 = (kotlinx.coroutines.channels.ChannelIterator) r12;
        r13 = r0.L$6;
        r13 = (kotlinx.coroutines.channels.ReceiveChannel) r13;
        r2 = r0.L$5;
        r2 = (java.lang.Throwable) r2;
        r4 = r0.L$4;
        r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4;
        r5 = r0.L$3;
        r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5;
        r6 = r0.L$2;
        r6 = (kotlin.jvm.internal.Ref.IntRef) r6;
        r7 = r0.L$1;
        r7 = (kotlin.jvm.functions.Function1) r7;
        r8 = r0.L$0;
        r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8;
        r9 = r14 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        if (r9 != 0) goto L_0x0056;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
    L_0x0051:
        r10 = r4;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r4 = r12;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r12 = r10;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        goto L_0x00e5;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
    L_0x0056:
        r14 = (kotlin.Result.Failure) r14;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r12 = r14.exception;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        throw r12;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
    L_0x005b:
        r12 = r0.L$7;
        r12 = (kotlinx.coroutines.channels.ChannelIterator) r12;
        r13 = r0.L$6;
        r13 = (kotlinx.coroutines.channels.ReceiveChannel) r13;
        r2 = r0.L$5;
        r2 = (java.lang.Throwable) r2;
        r4 = r0.L$4;
        r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4;
        r5 = r0.L$3;
        r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5;
        r6 = r0.L$2;
        r6 = (kotlin.jvm.internal.Ref.IntRef) r6;
        r7 = r0.L$1;
        r7 = (kotlin.jvm.functions.Function1) r7;
        r8 = r0.L$0;
        r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8;
        r9 = r14 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        if (r9 != 0) goto L_0x0080;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
    L_0x007f:
        goto L_0x00c3;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
    L_0x0080:
        r14 = (kotlin.Result.Failure) r14;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r12 = r14.exception;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        throw r12;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
    L_0x0085:
        r12 = move-exception;
        goto L_0x010f;
    L_0x0088:
        r12 = move-exception;
        r2 = r12;
        r12 = r4;
        goto L_0x010e;
    L_0x008d:
        r2 = r14 instanceof kotlin.Result.Failure;
        if (r2 != 0) goto L_0x0119;
    L_0x0091:
        r14 = new kotlin.jvm.internal.Ref$IntRef;
        r14.<init>();
        r2 = 0;
        r14.element = r2;
        r2 = 0;
        r2 = (java.lang.Throwable) r2;
        r4 = r12.iterator();	 Catch:{ Throwable -> 0x010c }
        r5 = r12;	 Catch:{ Throwable -> 0x010c }
        r8 = r5;	 Catch:{ Throwable -> 0x010c }
        r7 = r13;	 Catch:{ Throwable -> 0x010c }
        r13 = r8;	 Catch:{ Throwable -> 0x010c }
    L_0x00a4:
        r0.L$0 = r8;	 Catch:{ Throwable -> 0x010c }
        r0.L$1 = r7;	 Catch:{ Throwable -> 0x010c }
        r0.L$2 = r14;	 Catch:{ Throwable -> 0x010c }
        r0.L$3 = r5;	 Catch:{ Throwable -> 0x010c }
        r0.L$4 = r12;	 Catch:{ Throwable -> 0x010c }
        r0.L$5 = r2;	 Catch:{ Throwable -> 0x010c }
        r0.L$6 = r13;	 Catch:{ Throwable -> 0x010c }
        r0.L$7 = r4;	 Catch:{ Throwable -> 0x010c }
        r0.label = r3;	 Catch:{ Throwable -> 0x010c }
        r6 = r4.hasNext(r0);	 Catch:{ Throwable -> 0x010c }
        if (r6 != r1) goto L_0x00bd;
    L_0x00bc:
        return r1;
    L_0x00bd:
        r10 = r4;
        r4 = r12;
        r12 = r10;
        r11 = r6;
        r6 = r14;
        r14 = r11;
    L_0x00c3:
        r14 = (java.lang.Boolean) r14;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r14 = r14.booleanValue();	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        if (r14 == 0) goto L_0x00f6;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
    L_0x00cb:
        r0.L$0 = r8;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r0.L$1 = r7;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r0.L$2 = r6;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r0.L$3 = r5;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r0.L$4 = r4;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r0.L$5 = r2;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r0.L$6 = r13;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r0.L$7 = r12;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r14 = 2;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r0.label = r14;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        r14 = r12.next(r0);	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        if (r14 != r1) goto L_0x0051;
    L_0x00e4:
        return r1;
    L_0x00e5:
        r9 = r6.element;	 Catch:{ Throwable -> 0x010c }
        r14 = r7.invoke(r14);	 Catch:{ Throwable -> 0x010c }
        r14 = (java.lang.Number) r14;	 Catch:{ Throwable -> 0x010c }
        r14 = r14.intValue();	 Catch:{ Throwable -> 0x010c }
        r9 = r9 + r14;	 Catch:{ Throwable -> 0x010c }
        r6.element = r9;	 Catch:{ Throwable -> 0x010c }
        r14 = r6;
        goto L_0x00a4;
    L_0x00f6:
        r12 = kotlin.Unit.INSTANCE;	 Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        kotlin.jvm.internal.InlineMarker.finallyStart(r3);
        r4.cancel(r2);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r3);
        r12 = r6.element;
        r12 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r12);
        return r12;
    L_0x0108:
        r13 = move-exception;
        r4 = r12;
        r12 = r13;
        goto L_0x010f;
    L_0x010c:
        r13 = move-exception;
        r2 = r13;
    L_0x010e:
        throw r2;	 Catch:{ all -> 0x0108 }
    L_0x010f:
        kotlin.jvm.internal.InlineMarker.finallyStart(r3);
        r4.cancel(r2);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r3);
        throw r12;
    L_0x0119:
        r14 = (kotlin.Result.Failure) r14;
        r12 = r14.exception;
        throw r12;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.sumBy(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @org.jetbrains.annotations.Nullable
    @kotlinx.coroutines.ObsoleteCoroutinesApi
    public static final <E> java.lang.Object sumByDouble(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r17, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super E, java.lang.Double> r18, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Double> r19) {
        /*
        r0 = r19;
        r1 = r0 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$sumByDouble$1;
        if (r1 == 0) goto L_0x0016;
    L_0x0006:
        r1 = r0;
        r1 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$sumByDouble$1) r1;
        r2 = r1.label;
        r3 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r2 = r2 & r3;
        if (r2 == 0) goto L_0x0016;
    L_0x0010:
        r0 = r1.label;
        r0 = r0 - r3;
        r1.label = r0;
        goto L_0x001b;
    L_0x0016:
        r1 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$sumByDouble$1;
        r1.<init>(r0);
    L_0x001b:
        r0 = r1.result;
        r2 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        r3 = r1.label;
        r4 = 1;
        switch(r3) {
            case 0: goto L_0x0097;
            case 1: goto L_0x005f;
            case 2: goto L_0x002f;
            default: goto L_0x0027;
        };
    L_0x0027:
        r0 = new java.lang.IllegalStateException;
        r1 = "call to 'resume' before 'invoke' with coroutine";
        r0.<init>(r1);
        throw r0;
    L_0x002f:
        r3 = r1.L$7;
        r3 = (kotlinx.coroutines.channels.ChannelIterator) r3;
        r5 = r1.L$6;
        r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5;
        r6 = r1.L$5;
        r6 = (java.lang.Throwable) r6;
        r7 = r1.L$4;
        r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7;
        r8 = r1.L$3;
        r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8;
        r9 = r1.L$2;
        r9 = (kotlin.jvm.internal.Ref.DoubleRef) r9;
        r10 = r1.L$1;
        r10 = (kotlin.jvm.functions.Function1) r10;
        r11 = r1.L$0;
        r11 = (kotlinx.coroutines.channels.ReceiveChannel) r11;
        r12 = r0 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x0092, all -> 0x008e }
        if (r12 != 0) goto L_0x005a;
    L_0x0053:
        r16 = r7;
        r7 = r2;
        r2 = r16;
        goto L_0x00f4;
    L_0x005a:
        r0 = (kotlin.Result.Failure) r0;	 Catch:{ Throwable -> 0x0092, all -> 0x008e }
        r0 = r0.exception;	 Catch:{ Throwable -> 0x0092, all -> 0x008e }
        throw r0;	 Catch:{ Throwable -> 0x0092, all -> 0x008e }
    L_0x005f:
        r3 = r1.L$7;
        r3 = (kotlinx.coroutines.channels.ChannelIterator) r3;
        r5 = r1.L$6;
        r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5;
        r6 = r1.L$5;
        r6 = (java.lang.Throwable) r6;
        r7 = r1.L$4;
        r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7;
        r8 = r1.L$3;
        r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8;
        r9 = r1.L$2;
        r9 = (kotlin.jvm.internal.Ref.DoubleRef) r9;
        r10 = r1.L$1;
        r10 = (kotlin.jvm.functions.Function1) r10;
        r11 = r1.L$0;
        r11 = (kotlinx.coroutines.channels.ReceiveChannel) r11;
        r12 = r0 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x0092, all -> 0x008e }
        if (r12 != 0) goto L_0x0089;
    L_0x0083:
        r16 = r7;
        r7 = r2;
        r2 = r16;
        goto L_0x00d2;
    L_0x0089:
        r0 = (kotlin.Result.Failure) r0;	 Catch:{ Throwable -> 0x0092, all -> 0x008e }
        r0 = r0.exception;	 Catch:{ Throwable -> 0x0092, all -> 0x008e }
        throw r0;	 Catch:{ Throwable -> 0x0092, all -> 0x008e }
    L_0x008e:
        r0 = move-exception;
        r2 = r7;
        goto L_0x0124;
    L_0x0092:
        r0 = move-exception;
        r6 = r0;
        r2 = r7;
        goto L_0x0122;
    L_0x0097:
        r3 = r0 instanceof kotlin.Result.Failure;
        if (r3 != 0) goto L_0x012e;
    L_0x009b:
        r0 = new kotlin.jvm.internal.Ref$DoubleRef;
        r0.<init>();
        r5 = 0;
        r0.element = r5;
        r3 = 0;
        r6 = r3;
        r6 = (java.lang.Throwable) r6;
        r3 = r17.iterator();	 Catch:{ Throwable -> 0x011e, all -> 0x011a }
        r5 = r17;
        r8 = r5;
        r11 = r8;
        r10 = r18;
        r7 = r2;
        r2 = r11;
    L_0x00b4:
        r1.L$0 = r11;	 Catch:{ Throwable -> 0x0118 }
        r1.L$1 = r10;	 Catch:{ Throwable -> 0x0118 }
        r1.L$2 = r0;	 Catch:{ Throwable -> 0x0118 }
        r1.L$3 = r8;	 Catch:{ Throwable -> 0x0118 }
        r1.L$4 = r2;	 Catch:{ Throwable -> 0x0118 }
        r1.L$5 = r6;	 Catch:{ Throwable -> 0x0118 }
        r1.L$6 = r5;	 Catch:{ Throwable -> 0x0118 }
        r1.L$7 = r3;	 Catch:{ Throwable -> 0x0118 }
        r1.label = r4;	 Catch:{ Throwable -> 0x0118 }
        r9 = r3.hasNext(r1);	 Catch:{ Throwable -> 0x0118 }
        if (r9 != r7) goto L_0x00cd;
    L_0x00cc:
        return r7;
    L_0x00cd:
        r16 = r9;
        r9 = r0;
        r0 = r16;
    L_0x00d2:
        r0 = (java.lang.Boolean) r0;	 Catch:{ Throwable -> 0x0118 }
        r0 = r0.booleanValue();	 Catch:{ Throwable -> 0x0118 }
        if (r0 == 0) goto L_0x0106;
    L_0x00da:
        r1.L$0 = r11;	 Catch:{ Throwable -> 0x0118 }
        r1.L$1 = r10;	 Catch:{ Throwable -> 0x0118 }
        r1.L$2 = r9;	 Catch:{ Throwable -> 0x0118 }
        r1.L$3 = r8;	 Catch:{ Throwable -> 0x0118 }
        r1.L$4 = r2;	 Catch:{ Throwable -> 0x0118 }
        r1.L$5 = r6;	 Catch:{ Throwable -> 0x0118 }
        r1.L$6 = r5;	 Catch:{ Throwable -> 0x0118 }
        r1.L$7 = r3;	 Catch:{ Throwable -> 0x0118 }
        r0 = 2;
        r1.label = r0;	 Catch:{ Throwable -> 0x0118 }
        r0 = r3.next(r1);	 Catch:{ Throwable -> 0x0118 }
        if (r0 != r7) goto L_0x00f4;
    L_0x00f3:
        return r7;
    L_0x00f4:
        r12 = r9.element;	 Catch:{ Throwable -> 0x0118 }
        r0 = r10.invoke(r0);	 Catch:{ Throwable -> 0x0118 }
        r0 = (java.lang.Number) r0;	 Catch:{ Throwable -> 0x0118 }
        r14 = r0.doubleValue();	 Catch:{ Throwable -> 0x0118 }
        r0 = 0;
        r12 = r12 + r14;
        r9.element = r12;	 Catch:{ Throwable -> 0x0118 }
        r0 = r9;
        goto L_0x00b4;
    L_0x0106:
        r0 = kotlin.Unit.INSTANCE;	 Catch:{ Throwable -> 0x0118 }
        kotlin.jvm.internal.InlineMarker.finallyStart(r4);
        r2.cancel(r6);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r4);
        r0 = r9.element;
        r0 = kotlin.coroutines.jvm.internal.Boxing.boxDouble(r0);
        return r0;
    L_0x0118:
        r0 = move-exception;
        goto L_0x0121;
    L_0x011a:
        r0 = move-exception;
        r2 = r17;
        goto L_0x0124;
    L_0x011e:
        r0 = move-exception;
        r2 = r17;
    L_0x0121:
        r6 = r0;
    L_0x0122:
        throw r6;	 Catch:{ all -> 0x0123 }
    L_0x0123:
        r0 = move-exception;
    L_0x0124:
        kotlin.jvm.internal.InlineMarker.finallyStart(r4);
        r2.cancel(r6);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r4);
        throw r0;
    L_0x012e:
        r0 = (kotlin.Result.Failure) r0;
        r0 = r0.exception;
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.sumByDouble(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @NotNull
    @ObsoleteCoroutinesApi
    public static final <E> ReceiveChannel<E> requireNoNulls(@NotNull ReceiveChannel<? extends E> receiveChannel) {
        Intrinsics.checkParameterIsNotNull(receiveChannel, "receiver$0");
        return map$default(receiveChannel, null, new ChannelsKt__Channels_commonKt$requireNoNulls$1(receiveChannel, null), 1, null);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @org.jetbrains.annotations.Nullable
    @kotlinx.coroutines.ObsoleteCoroutinesApi
    public static final <E> java.lang.Object partition(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r16, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super E, java.lang.Boolean> r17, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Pair<? extends java.util.List<? extends E>, ? extends java.util.List<? extends E>>> r18) {
        /*
        r0 = r18;
        r1 = r0 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$partition$1;
        if (r1 == 0) goto L_0x0016;
    L_0x0006:
        r1 = r0;
        r1 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$partition$1) r1;
        r2 = r1.label;
        r3 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r2 = r2 & r3;
        if (r2 == 0) goto L_0x0016;
    L_0x0010:
        r0 = r1.label;
        r0 = r0 - r3;
        r1.label = r0;
        goto L_0x001b;
    L_0x0016:
        r1 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$partition$1;
        r1.<init>(r0);
    L_0x001b:
        r0 = r1.result;
        r2 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        r3 = r1.label;
        r4 = 1;
        switch(r3) {
            case 0: goto L_0x009e;
            case 1: goto L_0x0064;
            case 2: goto L_0x002f;
            default: goto L_0x0027;
        };
    L_0x0027:
        r0 = new java.lang.IllegalStateException;
        r1 = "call to 'resume' before 'invoke' with coroutine";
        r0.<init>(r1);
        throw r0;
    L_0x002f:
        r3 = r1.L$8;
        r3 = (kotlinx.coroutines.channels.ChannelIterator) r3;
        r5 = r1.L$7;
        r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5;
        r6 = r1.L$6;
        r6 = (java.lang.Throwable) r6;
        r7 = r1.L$5;
        r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7;
        r8 = r1.L$4;
        r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8;
        r9 = r1.L$3;
        r9 = (java.util.ArrayList) r9;
        r10 = r1.L$2;
        r10 = (java.util.ArrayList) r10;
        r11 = r1.L$1;
        r11 = (kotlin.jvm.functions.Function1) r11;
        r12 = r1.L$0;
        r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12;
        r13 = r0 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x0099, all -> 0x0095 }
        if (r13 != 0) goto L_0x005f;
    L_0x0057:
        r14 = r9;
        r9 = r2;
        r2 = r7;
        r7 = r5;
        r5 = r3;
        r3 = r14;
        goto L_0x0108;
    L_0x005f:
        r0 = (kotlin.Result.Failure) r0;	 Catch:{ Throwable -> 0x0099, all -> 0x0095 }
        r0 = r0.exception;	 Catch:{ Throwable -> 0x0099, all -> 0x0095 }
        throw r0;	 Catch:{ Throwable -> 0x0099, all -> 0x0095 }
    L_0x0064:
        r3 = r1.L$8;
        r3 = (kotlinx.coroutines.channels.ChannelIterator) r3;
        r5 = r1.L$7;
        r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5;
        r6 = r1.L$6;
        r6 = (java.lang.Throwable) r6;
        r7 = r1.L$5;
        r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7;
        r8 = r1.L$4;
        r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8;
        r9 = r1.L$3;
        r9 = (java.util.ArrayList) r9;
        r10 = r1.L$2;
        r10 = (java.util.ArrayList) r10;
        r11 = r1.L$1;
        r11 = (kotlin.jvm.functions.Function1) r11;
        r12 = r1.L$0;
        r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12;
        r13 = r0 instanceof kotlin.Result.Failure;	 Catch:{ Throwable -> 0x0099, all -> 0x0095 }
        if (r13 != 0) goto L_0x0090;
    L_0x008c:
        r14 = r7;
        r7 = r2;
        r2 = r14;
        goto L_0x00df;
    L_0x0090:
        r0 = (kotlin.Result.Failure) r0;	 Catch:{ Throwable -> 0x0099, all -> 0x0095 }
        r0 = r0.exception;	 Catch:{ Throwable -> 0x0099, all -> 0x0095 }
        throw r0;	 Catch:{ Throwable -> 0x0099, all -> 0x0095 }
    L_0x0095:
        r0 = move-exception;
        r2 = r7;
        goto L_0x013a;
    L_0x0099:
        r0 = move-exception;
        r6 = r0;
        r2 = r7;
        goto L_0x0138;
    L_0x009e:
        r3 = r0 instanceof kotlin.Result.Failure;
        if (r3 != 0) goto L_0x0144;
    L_0x00a2:
        r0 = new java.util.ArrayList;
        r0.<init>();
        r3 = new java.util.ArrayList;
        r3.<init>();
        r5 = 0;
        r6 = r5;
        r6 = (java.lang.Throwable) r6;
        r5 = r16.iterator();	 Catch:{ Throwable -> 0x0134, all -> 0x0130 }
        r7 = r16;
        r8 = r7;
        r12 = r8;
        r11 = r17;
        r9 = r2;
        r2 = r12;
    L_0x00bc:
        r1.L$0 = r12;	 Catch:{ Throwable -> 0x012e }
        r1.L$1 = r11;	 Catch:{ Throwable -> 0x012e }
        r1.L$2 = r0;	 Catch:{ Throwable -> 0x012e }
        r1.L$3 = r3;	 Catch:{ Throwable -> 0x012e }
        r1.L$4 = r8;	 Catch:{ Throwable -> 0x012e }
        r1.L$5 = r2;	 Catch:{ Throwable -> 0x012e }
        r1.L$6 = r6;	 Catch:{ Throwable -> 0x012e }
        r1.L$7 = r7;	 Catch:{ Throwable -> 0x012e }
        r1.L$8 = r5;	 Catch:{ Throwable -> 0x012e }
        r1.label = r4;	 Catch:{ Throwable -> 0x012e }
        r10 = r5.hasNext(r1);	 Catch:{ Throwable -> 0x012e }
        if (r10 != r9) goto L_0x00d7;
    L_0x00d6:
        return r9;
    L_0x00d7:
        r14 = r10;
        r10 = r0;
        r0 = r14;
        r15 = r9;
        r9 = r3;
        r3 = r5;
        r5 = r7;
        r7 = r15;
    L_0x00df:
        r0 = (java.lang.Boolean) r0;	 Catch:{ Throwable -> 0x012e }
        r0 = r0.booleanValue();	 Catch:{ Throwable -> 0x012e }
        if (r0 == 0) goto L_0x011d;
    L_0x00e7:
        r1.L$0 = r12;	 Catch:{ Throwable -> 0x012e }
        r1.L$1 = r11;	 Catch:{ Throwable -> 0x012e }
        r1.L$2 = r10;	 Catch:{ Throwable -> 0x012e }
        r1.L$3 = r9;	 Catch:{ Throwable -> 0x012e }
        r1.L$4 = r8;	 Catch:{ Throwable -> 0x012e }
        r1.L$5 = r2;	 Catch:{ Throwable -> 0x012e }
        r1.L$6 = r6;	 Catch:{ Throwable -> 0x012e }
        r1.L$7 = r5;	 Catch:{ Throwable -> 0x012e }
        r1.L$8 = r3;	 Catch:{ Throwable -> 0x012e }
        r0 = 2;
        r1.label = r0;	 Catch:{ Throwable -> 0x012e }
        r0 = r3.next(r1);	 Catch:{ Throwable -> 0x012e }
        if (r0 != r7) goto L_0x0103;
    L_0x0102:
        return r7;
    L_0x0103:
        r14 = r5;
        r5 = r3;
        r3 = r9;
        r9 = r7;
        r7 = r14;
    L_0x0108:
        r13 = r11.invoke(r0);	 Catch:{ Throwable -> 0x012e }
        r13 = (java.lang.Boolean) r13;	 Catch:{ Throwable -> 0x012e }
        r13 = r13.booleanValue();	 Catch:{ Throwable -> 0x012e }
        if (r13 == 0) goto L_0x0118;
    L_0x0114:
        r10.add(r0);	 Catch:{ Throwable -> 0x012e }
        goto L_0x011b;
    L_0x0118:
        r3.add(r0);	 Catch:{ Throwable -> 0x012e }
    L_0x011b:
        r0 = r10;
        goto L_0x00bc;
    L_0x011d:
        r0 = kotlin.Unit.INSTANCE;	 Catch:{ Throwable -> 0x012e }
        kotlin.jvm.internal.InlineMarker.finallyStart(r4);
        r2.cancel(r6);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r4);
        r0 = new kotlin.Pair;
        r0.<init>(r10, r9);
        return r0;
    L_0x012e:
        r0 = move-exception;
        goto L_0x0137;
    L_0x0130:
        r0 = move-exception;
        r2 = r16;
        goto L_0x013a;
    L_0x0134:
        r0 = move-exception;
        r2 = r16;
    L_0x0137:
        r6 = r0;
    L_0x0138:
        throw r6;	 Catch:{ all -> 0x0139 }
    L_0x0139:
        r0 = move-exception;
    L_0x013a:
        kotlin.jvm.internal.InlineMarker.finallyStart(r4);
        r2.cancel(r6);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r4);
        throw r0;
    L_0x0144:
        r0 = (kotlin.Result.Failure) r0;
        r0 = r0.exception;
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.partition(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Nullable
    @ObsoleteCoroutinesApi
    private static final Object partition$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Function1 function1, @NotNull Continuation continuation) {
        Function1 function12;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        Throwable th = (Throwable) null;
        try {
            ChannelIterator it = receiveChannel.iterator();
            while (true) {
                InlineMarker.mark(0);
                Object hasNext = it.hasNext(continuation);
                InlineMarker.mark(1);
                if (((Boolean) hasNext).booleanValue()) {
                    InlineMarker.mark(0);
                    Object next = it.next(continuation);
                    InlineMarker.mark(1);
                    if (((Boolean) function1.invoke(next)).booleanValue()) {
                        arrayList.add(next);
                    } else {
                        arrayList2.add(next);
                    }
                } else {
                    function1 = Unit.INSTANCE;
                    InlineMarker.finallyStart(1);
                    receiveChannel.cancel(th);
                    InlineMarker.finallyEnd(1);
                    return new Pair(arrayList, arrayList2);
                }
            }
        } catch (Throwable th2) {
            InlineMarker.finallyStart(1);
            receiveChannel.cancel(function12);
            InlineMarker.finallyEnd(1);
        }
    }

    @NotNull
    @ObsoleteCoroutinesApi
    public static final <E, R> ReceiveChannel<Pair<E, R>> zip(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull ReceiveChannel<? extends R> receiveChannel2) {
        Intrinsics.checkParameterIsNotNull(receiveChannel, "receiver$0");
        Intrinsics.checkParameterIsNotNull(receiveChannel2, "other");
        return zip$default(receiveChannel, receiveChannel2, null, ChannelsKt__Channels_commonKt$zip$1.INSTANCE, 2, null);
    }

    @NotNull
    @ObsoleteCoroutinesApi
    public static /* synthetic */ ReceiveChannel zip$default(ReceiveChannel receiveChannel, ReceiveChannel receiveChannel2, CoroutineContext coroutineContext, Function2 function2, int i, Object obj) {
        if ((i & 2) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return ChannelsKt.zip(receiveChannel, receiveChannel2, coroutineContext, function2);
    }

    @NotNull
    @ObsoleteCoroutinesApi
    public static final <E, R, V> ReceiveChannel<V> zip(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull ReceiveChannel<? extends R> receiveChannel2, @NotNull CoroutineContext coroutineContext, @NotNull Function2<? super E, ? super R, ? extends V> function2) {
        Intrinsics.checkParameterIsNotNull(receiveChannel, "receiver$0");
        Intrinsics.checkParameterIsNotNull(receiveChannel2, "other");
        Intrinsics.checkParameterIsNotNull(coroutineContext, "context");
        Intrinsics.checkParameterIsNotNull(function2, "transform");
        return ProduceKt.produce$default(GlobalScope.INSTANCE, coroutineContext, 0, ChannelsKt.consumesAll(receiveChannel, receiveChannel2), new ChannelsKt__Channels_commonKt$zip$2(receiveChannel, receiveChannel2, function2, null), 2, null);
    }

    @Nullable
    @ObsoleteCoroutinesApi
    private static final Object consumeEach$$forInline(@NotNull BroadcastChannel broadcastChannel, @NotNull Function1 function1, @NotNull Continuation continuation) {
        broadcastChannel = broadcastChannel.openSubscription();
        try {
            ChannelIterator it = broadcastChannel.iterator();
            while (true) {
                InlineMarker.mark(0);
                Object hasNext = it.hasNext(continuation);
                InlineMarker.mark(1);
                if (!((Boolean) hasNext).booleanValue()) {
                    break;
                }
                InlineMarker.mark(0);
                Object next = it.next(continuation);
                InlineMarker.mark(1);
                function1.invoke(next);
            }
            function1 = Unit.INSTANCE;
            return function1;
        } finally {
            InlineMarker.finallyStart(1);
            broadcastChannel.cancel();
            InlineMarker.finallyEnd(1);
        }
    }

    @Nullable
    @ObsoleteCoroutinesApi
    private static final Object consumeEach$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Function1 function1, @NotNull Continuation continuation) {
        Function1 function12;
        Throwable th = (Throwable) null;
        try {
            ChannelIterator it = receiveChannel.iterator();
            while (true) {
                InlineMarker.mark(0);
                Object hasNext = it.hasNext(continuation);
                InlineMarker.mark(1);
                if (((Boolean) hasNext).booleanValue()) {
                    InlineMarker.mark(0);
                    Object next = it.next(continuation);
                    InlineMarker.mark(1);
                    function1.invoke(next);
                } else {
                    function1 = Unit.INSTANCE;
                    InlineMarker.finallyStart(1);
                    receiveChannel.cancel(th);
                    InlineMarker.finallyEnd(1);
                    return function1;
                }
            }
        } catch (Throwable th2) {
            InlineMarker.finallyStart(1);
            receiveChannel.cancel(function12);
            InlineMarker.finallyEnd(1);
        }
    }

    @Nullable
    @ObsoleteCoroutinesApi
    private static final Object consumeEachIndexed$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Function1 function1, @NotNull Continuation continuation) {
        Function1 function12;
        Throwable th = (Throwable) null;
        try {
            ChannelIterator it = receiveChannel.iterator();
            int i = 0;
            while (true) {
                InlineMarker.mark(0);
                Object hasNext = it.hasNext(continuation);
                InlineMarker.mark(1);
                if (((Boolean) hasNext).booleanValue()) {
                    InlineMarker.mark(0);
                    hasNext = it.next(continuation);
                    InlineMarker.mark(1);
                    int i2 = i + 1;
                    function1.invoke(new IndexedValue(i, hasNext));
                    i = i2;
                } else {
                    function1 = Unit.INSTANCE;
                    InlineMarker.finallyStart(1);
                    receiveChannel.cancel(th);
                    InlineMarker.finallyEnd(1);
                    return Unit.INSTANCE;
                }
            }
        } catch (Throwable th2) {
            InlineMarker.finallyStart(1);
            receiveChannel.cancel(function12);
            InlineMarker.finallyEnd(1);
        }
    }

    @Nullable
    @ObsoleteCoroutinesApi
    private static final Object elementAtOrElse$$forInline(@NotNull ReceiveChannel receiveChannel, int i, @NotNull Function1 function1, @NotNull Continuation continuation) {
        Throwable th = (Throwable) null;
        if (i < 0) {
            try {
                i = function1.invoke(Integer.valueOf(i));
                function1 = 4;
                InlineMarker.finallyStart(4);
            } catch (Throwable th2) {
                InlineMarker.finallyStart(1);
                receiveChannel.cancel(th);
                InlineMarker.finallyEnd(1);
            }
        } else {
            ChannelIterator it = receiveChannel.iterator();
            int i2 = 0;
            while (true) {
                InlineMarker.mark(0);
                Object hasNext = it.hasNext(continuation);
                InlineMarker.mark(1);
                if (!((Boolean) hasNext).booleanValue()) {
                    break;
                }
                InlineMarker.mark(0);
                hasNext = it.next(continuation);
                InlineMarker.mark(1);
                int i3 = i2 + 1;
                if (i == i2) {
                    InlineMarker.finallyStart(3);
                    receiveChannel.cancel(th);
                    InlineMarker.finallyEnd(3);
                    return hasNext;
                }
                i2 = i3;
            }
            i = function1.invoke(Integer.valueOf(i));
            int i4 = 2;
            InlineMarker.finallyStart(2);
        }
        receiveChannel.cancel(th);
        InlineMarker.finallyEnd(function1);
        return i;
    }

    @Nullable
    @ObsoleteCoroutinesApi
    private static final Object find$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Function1 function1, @NotNull Continuation continuation) {
        Function1 function12;
        Throwable th = (Throwable) null;
        try {
            Object next;
            ChannelIterator it = receiveChannel.iterator();
            do {
                InlineMarker.mark(0);
                Object hasNext = it.hasNext(continuation);
                InlineMarker.mark(1);
                if (((Boolean) hasNext).booleanValue()) {
                    InlineMarker.mark(0);
                    next = it.next(continuation);
                    InlineMarker.mark(1);
                } else {
                    function1 = Unit.INSTANCE;
                    InlineMarker.finallyStart(1);
                    receiveChannel.cancel(th);
                    InlineMarker.finallyEnd(1);
                    return null;
                }
            } while (!((Boolean) function1.invoke(next)).booleanValue());
            InlineMarker.finallyStart(2);
            receiveChannel.cancel(th);
            InlineMarker.finallyEnd(2);
            return next;
        } catch (Throwable th2) {
            InlineMarker.finallyStart(1);
            receiveChannel.cancel(function12);
            InlineMarker.finallyEnd(1);
        }
    }

    @Nullable
    @ObsoleteCoroutinesApi
    private static final Object findLast$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Function1 function1, @NotNull Continuation continuation) {
        Function1 function12;
        Object obj = null;
        Throwable th = (Throwable) null;
        try {
            ChannelIterator it = receiveChannel.iterator();
            while (true) {
                InlineMarker.mark(0);
                Object hasNext = it.hasNext(continuation);
                InlineMarker.mark(1);
                if (((Boolean) hasNext).booleanValue()) {
                    InlineMarker.mark(0);
                    Object next = it.next(continuation);
                    InlineMarker.mark(1);
                    if (((Boolean) function1.invoke(next)).booleanValue()) {
                        obj = next;
                    }
                } else {
                    function1 = Unit.INSTANCE;
                    InlineMarker.finallyStart(1);
                    receiveChannel.cancel(th);
                    InlineMarker.finallyEnd(1);
                    return obj;
                }
            }
        } catch (Throwable th2) {
            InlineMarker.finallyStart(1);
            receiveChannel.cancel(function12);
            InlineMarker.finallyEnd(1);
        }
    }

    @Nullable
    @ObsoleteCoroutinesApi
    private static final Object first$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Function1 function1, @NotNull Continuation continuation) {
        Function1 function12;
        Throwable th = (Throwable) null;
        try {
            Object next;
            ChannelIterator it = receiveChannel.iterator();
            do {
                InlineMarker.mark(0);
                Object hasNext = it.hasNext(continuation);
                InlineMarker.mark(1);
                if (((Boolean) hasNext).booleanValue()) {
                    InlineMarker.mark(0);
                    next = it.next(continuation);
                    InlineMarker.mark(1);
                } else {
                    function1 = Unit.INSTANCE;
                    InlineMarker.finallyStart(1);
                    receiveChannel.cancel(th);
                    InlineMarker.finallyEnd(1);
                    throw ((Throwable) new NoSuchElementException("ReceiveChannel contains no element matching the predicate."));
                }
            } while (!((Boolean) function1.invoke(next)).booleanValue());
            InlineMarker.finallyStart(2);
            receiveChannel.cancel(th);
            InlineMarker.finallyEnd(2);
            return next;
        } catch (Throwable th2) {
            InlineMarker.finallyStart(1);
            receiveChannel.cancel(function12);
            InlineMarker.finallyEnd(1);
        }
    }

    @Nullable
    @ObsoleteCoroutinesApi
    private static final Object firstOrNull$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Function1 function1, @NotNull Continuation continuation) {
        Function1 function12;
        Throwable th = (Throwable) null;
        try {
            Object next;
            ChannelIterator it = receiveChannel.iterator();
            do {
                InlineMarker.mark(0);
                Object hasNext = it.hasNext(continuation);
                InlineMarker.mark(1);
                if (((Boolean) hasNext).booleanValue()) {
                    InlineMarker.mark(0);
                    next = it.next(continuation);
                    InlineMarker.mark(1);
                } else {
                    function1 = Unit.INSTANCE;
                    InlineMarker.finallyStart(1);
                    receiveChannel.cancel(th);
                    InlineMarker.finallyEnd(1);
                    return null;
                }
            } while (!((Boolean) function1.invoke(next)).booleanValue());
            InlineMarker.finallyStart(2);
            receiveChannel.cancel(th);
            InlineMarker.finallyEnd(2);
            return next;
        } catch (Throwable th2) {
            InlineMarker.finallyStart(1);
            receiveChannel.cancel(function12);
            InlineMarker.finallyEnd(1);
        }
    }

    @Nullable
    @ObsoleteCoroutinesApi
    private static final Object indexOfFirst$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Function1 function1, @NotNull Continuation continuation) {
        Function1 function12;
        Throwable th = (Throwable) null;
        try {
            ChannelIterator it = receiveChannel.iterator();
            int i = 0;
            while (true) {
                InlineMarker.mark(0);
                Object hasNext = it.hasNext(continuation);
                InlineMarker.mark(1);
                if (((Boolean) hasNext).booleanValue()) {
                    InlineMarker.mark(0);
                    hasNext = it.next(continuation);
                    InlineMarker.mark(1);
                    if (((Boolean) function1.invoke(hasNext)).booleanValue()) {
                        function1 = Integer.valueOf(i);
                        InlineMarker.finallyStart(2);
                        receiveChannel.cancel(th);
                        InlineMarker.finallyEnd(2);
                        return function1;
                    }
                    i++;
                } else {
                    function1 = Unit.INSTANCE;
                    InlineMarker.finallyStart(1);
                    receiveChannel.cancel(th);
                    InlineMarker.finallyEnd(1);
                    return Integer.valueOf(-1);
                }
            }
        } catch (Throwable th2) {
            InlineMarker.finallyStart(1);
            receiveChannel.cancel(function12);
            InlineMarker.finallyEnd(1);
        }
    }

    @Nullable
    @ObsoleteCoroutinesApi
    private static final Object indexOfLast$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Function1 function1, @NotNull Continuation continuation) {
        Function1 function12;
        Throwable th = (Throwable) null;
        try {
            ChannelIterator it = receiveChannel.iterator();
            int i = -1;
            int i2 = 0;
            while (true) {
                InlineMarker.mark(0);
                Object hasNext = it.hasNext(continuation);
                InlineMarker.mark(1);
                if (((Boolean) hasNext).booleanValue()) {
                    InlineMarker.mark(0);
                    hasNext = it.next(continuation);
                    InlineMarker.mark(1);
                    if (((Boolean) function1.invoke(hasNext)).booleanValue()) {
                        i = i2;
                    }
                    i2++;
                } else {
                    function1 = Unit.INSTANCE;
                    InlineMarker.finallyStart(1);
                    receiveChannel.cancel(th);
                    InlineMarker.finallyEnd(1);
                    return Integer.valueOf(i);
                }
            }
        } catch (Throwable th2) {
            InlineMarker.finallyStart(1);
            receiveChannel.cancel(function12);
            InlineMarker.finallyEnd(1);
        }
    }

    @Nullable
    @ObsoleteCoroutinesApi
    private static final Object last$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Function1 function1, @NotNull Continuation continuation) {
        Function1 function12;
        Throwable th = (Throwable) null;
        try {
            ChannelIterator it = receiveChannel.iterator();
            Object obj = null;
            Object obj2 = null;
            while (true) {
                InlineMarker.mark(0);
                Object hasNext = it.hasNext(continuation);
                InlineMarker.mark(1);
                if (!((Boolean) hasNext).booleanValue()) {
                    break;
                }
                InlineMarker.mark(0);
                hasNext = it.next(continuation);
                InlineMarker.mark(1);
                if (((Boolean) function1.invoke(hasNext)).booleanValue()) {
                    obj = hasNext;
                    obj2 = 1;
                }
            }
            function1 = Unit.INSTANCE;
            InlineMarker.finallyStart(1);
            receiveChannel.cancel(th);
            InlineMarker.finallyEnd(1);
            if (obj2 != null) {
                return obj;
            }
            throw ((Throwable) new NoSuchElementException("ReceiveChannel contains no element matching the predicate."));
        } catch (Throwable th2) {
            InlineMarker.finallyStart(1);
            receiveChannel.cancel(function12);
            InlineMarker.finallyEnd(1);
        }
    }

    @Nullable
    @ObsoleteCoroutinesApi
    private static final Object lastOrNull$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Function1 function1, @NotNull Continuation continuation) {
        Function1 function12;
        Object obj = null;
        Throwable th = (Throwable) null;
        try {
            ChannelIterator it = receiveChannel.iterator();
            while (true) {
                InlineMarker.mark(0);
                Object hasNext = it.hasNext(continuation);
                InlineMarker.mark(1);
                if (((Boolean) hasNext).booleanValue()) {
                    InlineMarker.mark(0);
                    Object next = it.next(continuation);
                    InlineMarker.mark(1);
                    if (((Boolean) function1.invoke(next)).booleanValue()) {
                        obj = next;
                    }
                } else {
                    function1 = Unit.INSTANCE;
                    InlineMarker.finallyStart(1);
                    receiveChannel.cancel(th);
                    InlineMarker.finallyEnd(1);
                    return obj;
                }
            }
        } catch (Throwable th2) {
            InlineMarker.finallyStart(1);
            receiveChannel.cancel(function12);
            InlineMarker.finallyEnd(1);
        }
    }

    @Nullable
    @ObsoleteCoroutinesApi
    private static final Object single$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Function1 function1, @NotNull Continuation continuation) {
        Function1 function12;
        Throwable th = (Throwable) null;
        try {
            ChannelIterator it = receiveChannel.iterator();
            Object obj = null;
            Object obj2 = null;
            while (true) {
                InlineMarker.mark(0);
                Object hasNext = it.hasNext(continuation);
                InlineMarker.mark(1);
                if (!((Boolean) hasNext).booleanValue()) {
                    break;
                }
                InlineMarker.mark(0);
                hasNext = it.next(continuation);
                InlineMarker.mark(1);
                if (((Boolean) function1.invoke(hasNext)).booleanValue()) {
                    if (obj2 == null) {
                        obj = hasNext;
                        obj2 = 1;
                    } else {
                        throw ((Throwable) new IllegalArgumentException("ReceiveChannel contains more than one matching element."));
                    }
                }
            }
            function1 = Unit.INSTANCE;
            InlineMarker.finallyStart(1);
            receiveChannel.cancel(th);
            InlineMarker.finallyEnd(1);
            if (obj2 != null) {
                return obj;
            }
            throw ((Throwable) new NoSuchElementException("ReceiveChannel contains no element matching the predicate."));
        } catch (Throwable th2) {
            InlineMarker.finallyStart(1);
            receiveChannel.cancel(function12);
            InlineMarker.finallyEnd(1);
        }
    }

    @Nullable
    @ObsoleteCoroutinesApi
    private static final Object singleOrNull$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Function1 function1, @NotNull Continuation continuation) {
        Function1 function12;
        Throwable th = (Throwable) null;
        try {
            ChannelIterator it = receiveChannel.iterator();
            Object obj = null;
            Object obj2 = null;
            while (true) {
                InlineMarker.mark(0);
                Object hasNext = it.hasNext(continuation);
                InlineMarker.mark(1);
                if (!((Boolean) hasNext).booleanValue()) {
                    break;
                }
                InlineMarker.mark(0);
                hasNext = it.next(continuation);
                InlineMarker.mark(1);
                if (((Boolean) function1.invoke(hasNext)).booleanValue()) {
                    if (obj2 != null) {
                        InlineMarker.finallyStart(2);
                        receiveChannel.cancel(th);
                        InlineMarker.finallyEnd(2);
                        return null;
                    }
                    obj = hasNext;
                    obj2 = 1;
                }
            }
            function1 = Unit.INSTANCE;
            InlineMarker.finallyStart(1);
            receiveChannel.cancel(th);
            InlineMarker.finallyEnd(1);
            if (obj2 == null) {
                return null;
            }
            return obj;
        } catch (Throwable th2) {
            InlineMarker.finallyStart(1);
            receiveChannel.cancel(function12);
            InlineMarker.finallyEnd(1);
        }
    }

    @Nullable
    @ObsoleteCoroutinesApi
    private static final Object filterIndexedTo$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Collection collection, @NotNull Function2 function2, @NotNull Continuation continuation) {
        Collection collection2;
        Throwable th = (Throwable) null;
        try {
            ChannelIterator it = receiveChannel.iterator();
            int i = 0;
            while (true) {
                InlineMarker.mark(0);
                Object hasNext = it.hasNext(continuation);
                InlineMarker.mark(1);
                if (((Boolean) hasNext).booleanValue()) {
                    InlineMarker.mark(0);
                    hasNext = it.next(continuation);
                    InlineMarker.mark(1);
                    int i2 = i + 1;
                    IndexedValue indexedValue = new IndexedValue(i, hasNext);
                    i = indexedValue.component1();
                    hasNext = indexedValue.component2();
                    if (((Boolean) function2.invoke(Integer.valueOf(i), hasNext)).booleanValue()) {
                        collection.add(hasNext);
                    }
                    i = i2;
                } else {
                    function2 = Unit.INSTANCE;
                    InlineMarker.finallyStart(1);
                    receiveChannel.cancel(th);
                    InlineMarker.finallyEnd(1);
                    return collection;
                }
            }
        } catch (Throwable th2) {
            InlineMarker.finallyStart(1);
            receiveChannel.cancel(collection2);
            InlineMarker.finallyEnd(1);
        }
    }

    @Nullable
    @ObsoleteCoroutinesApi
    private static final Object filterIndexedTo$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull SendChannel sendChannel, @NotNull Function2 function2, @NotNull Continuation continuation) {
        SendChannel sendChannel2;
        Throwable th = (Throwable) null;
        try {
            ChannelIterator it = receiveChannel.iterator();
            int i = 0;
            while (true) {
                InlineMarker.mark(0);
                Object hasNext = it.hasNext(continuation);
                InlineMarker.mark(1);
                if (((Boolean) hasNext).booleanValue()) {
                    InlineMarker.mark(0);
                    hasNext = it.next(continuation);
                    InlineMarker.mark(1);
                    int i2 = i + 1;
                    IndexedValue indexedValue = new IndexedValue(i, hasNext);
                    i = indexedValue.component1();
                    hasNext = indexedValue.component2();
                    if (((Boolean) function2.invoke(Integer.valueOf(i), hasNext)).booleanValue()) {
                        InlineMarker.mark(0);
                        sendChannel.send(hasNext, continuation);
                        InlineMarker.mark(2);
                        InlineMarker.mark(1);
                    }
                    i = i2;
                } else {
                    function2 = Unit.INSTANCE;
                    InlineMarker.finallyStart(1);
                    receiveChannel.cancel(th);
                    InlineMarker.finallyEnd(1);
                    return sendChannel;
                }
            }
        } catch (Throwable th2) {
            InlineMarker.finallyStart(1);
            receiveChannel.cancel(sendChannel2);
            InlineMarker.finallyEnd(1);
        }
    }

    @Nullable
    @ObsoleteCoroutinesApi
    private static final Object filterNotTo$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Collection collection, @NotNull Function1 function1, @NotNull Continuation continuation) {
        Collection collection2;
        Throwable th = (Throwable) null;
        try {
            ChannelIterator it = receiveChannel.iterator();
            while (true) {
                InlineMarker.mark(0);
                Object hasNext = it.hasNext(continuation);
                InlineMarker.mark(1);
                if (((Boolean) hasNext).booleanValue()) {
                    InlineMarker.mark(0);
                    Object next = it.next(continuation);
                    InlineMarker.mark(1);
                    if (!((Boolean) function1.invoke(next)).booleanValue()) {
                        collection.add(next);
                    }
                } else {
                    function1 = Unit.INSTANCE;
                    InlineMarker.finallyStart(1);
                    receiveChannel.cancel(th);
                    InlineMarker.finallyEnd(1);
                    return collection;
                }
            }
        } catch (Throwable th2) {
            InlineMarker.finallyStart(1);
            receiveChannel.cancel(collection2);
            InlineMarker.finallyEnd(1);
        }
    }

    @Nullable
    @ObsoleteCoroutinesApi
    private static final Object filterNotTo$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull SendChannel sendChannel, @NotNull Function1 function1, @NotNull Continuation continuation) {
        SendChannel sendChannel2;
        Throwable th = (Throwable) null;
        try {
            ChannelIterator it = receiveChannel.iterator();
            while (true) {
                InlineMarker.mark(0);
                Object hasNext = it.hasNext(continuation);
                InlineMarker.mark(1);
                if (((Boolean) hasNext).booleanValue()) {
                    InlineMarker.mark(0);
                    hasNext = it.next(continuation);
                    InlineMarker.mark(1);
                    if (!((Boolean) function1.invoke(hasNext)).booleanValue()) {
                        InlineMarker.mark(0);
                        sendChannel.send(hasNext, continuation);
                        InlineMarker.mark(2);
                        InlineMarker.mark(1);
                    }
                } else {
                    function1 = Unit.INSTANCE;
                    InlineMarker.finallyStart(1);
                    receiveChannel.cancel(th);
                    InlineMarker.finallyEnd(1);
                    return sendChannel;
                }
            }
        } catch (Throwable th2) {
            InlineMarker.finallyStart(1);
            receiveChannel.cancel(sendChannel2);
            InlineMarker.finallyEnd(1);
        }
    }

    @Nullable
    @ObsoleteCoroutinesApi
    private static final Object filterTo$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Collection collection, @NotNull Function1 function1, @NotNull Continuation continuation) {
        Collection collection2;
        Throwable th = (Throwable) null;
        try {
            ChannelIterator it = receiveChannel.iterator();
            while (true) {
                InlineMarker.mark(0);
                Object hasNext = it.hasNext(continuation);
                InlineMarker.mark(1);
                if (((Boolean) hasNext).booleanValue()) {
                    InlineMarker.mark(0);
                    Object next = it.next(continuation);
                    InlineMarker.mark(1);
                    if (((Boolean) function1.invoke(next)).booleanValue()) {
                        collection.add(next);
                    }
                } else {
                    function1 = Unit.INSTANCE;
                    InlineMarker.finallyStart(1);
                    receiveChannel.cancel(th);
                    InlineMarker.finallyEnd(1);
                    return collection;
                }
            }
        } catch (Throwable th2) {
            InlineMarker.finallyStart(1);
            receiveChannel.cancel(collection2);
            InlineMarker.finallyEnd(1);
        }
    }

    @Nullable
    @ObsoleteCoroutinesApi
    private static final Object filterTo$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull SendChannel sendChannel, @NotNull Function1 function1, @NotNull Continuation continuation) {
        SendChannel sendChannel2;
        Throwable th = (Throwable) null;
        try {
            ChannelIterator it = receiveChannel.iterator();
            while (true) {
                InlineMarker.mark(0);
                Object hasNext = it.hasNext(continuation);
                InlineMarker.mark(1);
                if (((Boolean) hasNext).booleanValue()) {
                    InlineMarker.mark(0);
                    hasNext = it.next(continuation);
                    InlineMarker.mark(1);
                    if (((Boolean) function1.invoke(hasNext)).booleanValue()) {
                        InlineMarker.mark(0);
                        sendChannel.send(hasNext, continuation);
                        InlineMarker.mark(2);
                        InlineMarker.mark(1);
                    }
                } else {
                    function1 = Unit.INSTANCE;
                    InlineMarker.finallyStart(1);
                    receiveChannel.cancel(th);
                    InlineMarker.finallyEnd(1);
                    return sendChannel;
                }
            }
        } catch (Throwable th2) {
            InlineMarker.finallyStart(1);
            receiveChannel.cancel(sendChannel2);
            InlineMarker.finallyEnd(1);
        }
    }

    @Nullable
    @ObsoleteCoroutinesApi
    private static final Object associateByTo$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Map map, @NotNull Function1 function1, @NotNull Continuation continuation) {
        Map map2;
        Throwable th = (Throwable) null;
        try {
            ChannelIterator it = receiveChannel.iterator();
            while (true) {
                InlineMarker.mark(0);
                Object hasNext = it.hasNext(continuation);
                InlineMarker.mark(1);
                if (((Boolean) hasNext).booleanValue()) {
                    InlineMarker.mark(0);
                    Object next = it.next(continuation);
                    InlineMarker.mark(1);
                    map.put(function1.invoke(next), next);
                } else {
                    function1 = Unit.INSTANCE;
                    InlineMarker.finallyStart(1);
                    receiveChannel.cancel(th);
                    InlineMarker.finallyEnd(1);
                    return map;
                }
            }
        } catch (Throwable th2) {
            InlineMarker.finallyStart(1);
            receiveChannel.cancel(map2);
            InlineMarker.finallyEnd(1);
        }
    }

    @Nullable
    @ObsoleteCoroutinesApi
    private static final Object associateByTo$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Map map, @NotNull Function1 function1, @NotNull Function1 function12, @NotNull Continuation continuation) {
        Map map2;
        Throwable th = (Throwable) null;
        try {
            ChannelIterator it = receiveChannel.iterator();
            while (true) {
                InlineMarker.mark(0);
                Object hasNext = it.hasNext(continuation);
                InlineMarker.mark(1);
                if (((Boolean) hasNext).booleanValue()) {
                    InlineMarker.mark(0);
                    Object next = it.next(continuation);
                    InlineMarker.mark(1);
                    map.put(function1.invoke(next), function12.invoke(next));
                } else {
                    function1 = Unit.INSTANCE;
                    InlineMarker.finallyStart(1);
                    receiveChannel.cancel(th);
                    InlineMarker.finallyEnd(1);
                    return map;
                }
            }
        } catch (Throwable th2) {
            InlineMarker.finallyStart(1);
            receiveChannel.cancel(map2);
            InlineMarker.finallyEnd(1);
        }
    }

    @Nullable
    @ObsoleteCoroutinesApi
    private static final Object associateTo$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Map map, @NotNull Function1 function1, @NotNull Continuation continuation) {
        Map map2;
        Throwable th = (Throwable) null;
        try {
            ChannelIterator it = receiveChannel.iterator();
            while (true) {
                InlineMarker.mark(0);
                Object hasNext = it.hasNext(continuation);
                InlineMarker.mark(1);
                if (((Boolean) hasNext).booleanValue()) {
                    InlineMarker.mark(0);
                    Object next = it.next(continuation);
                    InlineMarker.mark(1);
                    Pair pair = (Pair) function1.invoke(next);
                    map.put(pair.getFirst(), pair.getSecond());
                } else {
                    function1 = Unit.INSTANCE;
                    InlineMarker.finallyStart(1);
                    receiveChannel.cancel(th);
                    InlineMarker.finallyEnd(1);
                    return map;
                }
            }
        } catch (Throwable th2) {
            InlineMarker.finallyStart(1);
            receiveChannel.cancel(map2);
            InlineMarker.finallyEnd(1);
        }
    }

    @Nullable
    @ObsoleteCoroutinesApi
    private static final Object groupByTo$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Map map, @NotNull Function1 function1, @NotNull Continuation continuation) {
        Map map2;
        Throwable th = (Throwable) null;
        try {
            ChannelIterator it = receiveChannel.iterator();
            while (true) {
                InlineMarker.mark(0);
                Object hasNext = it.hasNext(continuation);
                InlineMarker.mark(1);
                if (((Boolean) hasNext).booleanValue()) {
                    InlineMarker.mark(0);
                    Object next = it.next(continuation);
                    InlineMarker.mark(1);
                    hasNext = function1.invoke(next);
                    ArrayList arrayList = map.get(hasNext);
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                        map.put(hasNext, arrayList);
                    }
                    arrayList.add(next);
                } else {
                    function1 = Unit.INSTANCE;
                    InlineMarker.finallyStart(1);
                    receiveChannel.cancel(th);
                    InlineMarker.finallyEnd(1);
                    return map;
                }
            }
        } catch (Throwable th2) {
            InlineMarker.finallyStart(1);
            receiveChannel.cancel(map2);
            InlineMarker.finallyEnd(1);
        }
    }

    @Nullable
    @ObsoleteCoroutinesApi
    private static final Object groupByTo$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Map map, @NotNull Function1 function1, @NotNull Function1 function12, @NotNull Continuation continuation) {
        Map map2;
        Throwable th = (Throwable) null;
        try {
            ChannelIterator it = receiveChannel.iterator();
            while (true) {
                InlineMarker.mark(0);
                Object hasNext = it.hasNext(continuation);
                InlineMarker.mark(1);
                if (((Boolean) hasNext).booleanValue()) {
                    InlineMarker.mark(0);
                    Object next = it.next(continuation);
                    InlineMarker.mark(1);
                    hasNext = function1.invoke(next);
                    ArrayList arrayList = map.get(hasNext);
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                        map.put(hasNext, arrayList);
                    }
                    arrayList.add(function12.invoke(next));
                } else {
                    function1 = Unit.INSTANCE;
                    InlineMarker.finallyStart(1);
                    receiveChannel.cancel(th);
                    InlineMarker.finallyEnd(1);
                    return map;
                }
            }
        } catch (Throwable th2) {
            InlineMarker.finallyStart(1);
            receiveChannel.cancel(map2);
            InlineMarker.finallyEnd(1);
        }
    }

    @Nullable
    @ObsoleteCoroutinesApi
    private static final Object mapIndexedNotNullTo$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Collection collection, @NotNull Function2 function2, @NotNull Continuation continuation) {
        Collection collection2;
        Throwable th = (Throwable) null;
        try {
            ChannelIterator it = receiveChannel.iterator();
            int i = 0;
            while (true) {
                InlineMarker.mark(0);
                Object hasNext = it.hasNext(continuation);
                InlineMarker.mark(1);
                if (((Boolean) hasNext).booleanValue()) {
                    InlineMarker.mark(0);
                    hasNext = it.next(continuation);
                    InlineMarker.mark(1);
                    int i2 = i + 1;
                    IndexedValue indexedValue = new IndexedValue(i, hasNext);
                    i = indexedValue.component1();
                    Object invoke = function2.invoke(Integer.valueOf(i), indexedValue.component2());
                    if (invoke != null) {
                        collection.add(invoke);
                    }
                    i = i2;
                } else {
                    function2 = Unit.INSTANCE;
                    InlineMarker.finallyStart(1);
                    receiveChannel.cancel(th);
                    InlineMarker.finallyEnd(1);
                    return collection;
                }
            }
        } catch (Throwable th2) {
            InlineMarker.finallyStart(1);
            receiveChannel.cancel(collection2);
            InlineMarker.finallyEnd(1);
        }
    }

    @Nullable
    @ObsoleteCoroutinesApi
    private static final Object mapIndexedNotNullTo$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull SendChannel sendChannel, @NotNull Function2 function2, @NotNull Continuation continuation) {
        SendChannel sendChannel2;
        Throwable th = (Throwable) null;
        try {
            ChannelIterator it = receiveChannel.iterator();
            int i = 0;
            while (true) {
                InlineMarker.mark(0);
                Object hasNext = it.hasNext(continuation);
                InlineMarker.mark(1);
                if (((Boolean) hasNext).booleanValue()) {
                    InlineMarker.mark(0);
                    hasNext = it.next(continuation);
                    InlineMarker.mark(1);
                    int i2 = i + 1;
                    IndexedValue indexedValue = new IndexedValue(i, hasNext);
                    i = indexedValue.component1();
                    Object invoke = function2.invoke(Integer.valueOf(i), indexedValue.component2());
                    if (invoke != null) {
                        InlineMarker.mark(0);
                        sendChannel.send(invoke, continuation);
                        InlineMarker.mark(2);
                        InlineMarker.mark(1);
                    }
                    i = i2;
                } else {
                    function2 = Unit.INSTANCE;
                    InlineMarker.finallyStart(1);
                    receiveChannel.cancel(th);
                    InlineMarker.finallyEnd(1);
                    return sendChannel;
                }
            }
        } catch (Throwable th2) {
            InlineMarker.finallyStart(1);
            receiveChannel.cancel(sendChannel2);
            InlineMarker.finallyEnd(1);
        }
    }

    @Nullable
    @ObsoleteCoroutinesApi
    private static final Object mapIndexedTo$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Collection collection, @NotNull Function2 function2, @NotNull Continuation continuation) {
        Collection collection2;
        Throwable th = (Throwable) null;
        try {
            ChannelIterator it = receiveChannel.iterator();
            int i = 0;
            while (true) {
                InlineMarker.mark(0);
                Object hasNext = it.hasNext(continuation);
                InlineMarker.mark(1);
                if (((Boolean) hasNext).booleanValue()) {
                    InlineMarker.mark(0);
                    hasNext = it.next(continuation);
                    InlineMarker.mark(1);
                    int i2 = i + 1;
                    collection.add(function2.invoke(Integer.valueOf(i), hasNext));
                    i = i2;
                } else {
                    function2 = Unit.INSTANCE;
                    InlineMarker.finallyStart(1);
                    receiveChannel.cancel(th);
                    InlineMarker.finallyEnd(1);
                    return collection;
                }
            }
        } catch (Throwable th2) {
            InlineMarker.finallyStart(1);
            receiveChannel.cancel(collection2);
            InlineMarker.finallyEnd(1);
        }
    }

    @Nullable
    @ObsoleteCoroutinesApi
    private static final Object mapIndexedTo$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull SendChannel sendChannel, @NotNull Function2 function2, @NotNull Continuation continuation) {
        SendChannel sendChannel2;
        Throwable th = (Throwable) null;
        try {
            ChannelIterator it = receiveChannel.iterator();
            int i = 0;
            while (true) {
                InlineMarker.mark(0);
                Object hasNext = it.hasNext(continuation);
                InlineMarker.mark(1);
                if (((Boolean) hasNext).booleanValue()) {
                    InlineMarker.mark(0);
                    hasNext = it.next(continuation);
                    InlineMarker.mark(1);
                    int i2 = i + 1;
                    Object invoke = function2.invoke(Integer.valueOf(i), hasNext);
                    InlineMarker.mark(0);
                    sendChannel.send(invoke, continuation);
                    InlineMarker.mark(2);
                    InlineMarker.mark(1);
                    i = i2;
                } else {
                    function2 = Unit.INSTANCE;
                    InlineMarker.finallyStart(1);
                    receiveChannel.cancel(th);
                    InlineMarker.finallyEnd(1);
                    return sendChannel;
                }
            }
        } catch (Throwable th2) {
            InlineMarker.finallyStart(1);
            receiveChannel.cancel(sendChannel2);
            InlineMarker.finallyEnd(1);
        }
    }

    @Nullable
    @ObsoleteCoroutinesApi
    private static final Object mapNotNullTo$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Collection collection, @NotNull Function1 function1, @NotNull Continuation continuation) {
        Collection collection2;
        Throwable th = (Throwable) null;
        try {
            ChannelIterator it = receiveChannel.iterator();
            while (true) {
                InlineMarker.mark(0);
                Object hasNext = it.hasNext(continuation);
                InlineMarker.mark(1);
                if (((Boolean) hasNext).booleanValue()) {
                    InlineMarker.mark(0);
                    Object next = it.next(continuation);
                    InlineMarker.mark(1);
                    next = function1.invoke(next);
                    if (next != null) {
                        collection.add(next);
                    }
                } else {
                    function1 = Unit.INSTANCE;
                    InlineMarker.finallyStart(1);
                    receiveChannel.cancel(th);
                    InlineMarker.finallyEnd(1);
                    return collection;
                }
            }
        } catch (Throwable th2) {
            InlineMarker.finallyStart(1);
            receiveChannel.cancel(collection2);
            InlineMarker.finallyEnd(1);
        }
    }

    @Nullable
    @ObsoleteCoroutinesApi
    private static final Object mapNotNullTo$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull SendChannel sendChannel, @NotNull Function1 function1, @NotNull Continuation continuation) {
        SendChannel sendChannel2;
        Throwable th = (Throwable) null;
        try {
            ChannelIterator it = receiveChannel.iterator();
            while (true) {
                InlineMarker.mark(0);
                Object hasNext = it.hasNext(continuation);
                InlineMarker.mark(1);
                if (((Boolean) hasNext).booleanValue()) {
                    InlineMarker.mark(0);
                    hasNext = it.next(continuation);
                    InlineMarker.mark(1);
                    hasNext = function1.invoke(hasNext);
                    if (hasNext != null) {
                        InlineMarker.mark(0);
                        sendChannel.send(hasNext, continuation);
                        InlineMarker.mark(2);
                        InlineMarker.mark(1);
                    }
                } else {
                    function1 = Unit.INSTANCE;
                    InlineMarker.finallyStart(1);
                    receiveChannel.cancel(th);
                    InlineMarker.finallyEnd(1);
                    return sendChannel;
                }
            }
        } catch (Throwable th2) {
            InlineMarker.finallyStart(1);
            receiveChannel.cancel(sendChannel2);
            InlineMarker.finallyEnd(1);
        }
    }

    @Nullable
    @ObsoleteCoroutinesApi
    private static final Object mapTo$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Collection collection, @NotNull Function1 function1, @NotNull Continuation continuation) {
        Collection collection2;
        Throwable th = (Throwable) null;
        try {
            ChannelIterator it = receiveChannel.iterator();
            while (true) {
                InlineMarker.mark(0);
                Object hasNext = it.hasNext(continuation);
                InlineMarker.mark(1);
                if (((Boolean) hasNext).booleanValue()) {
                    InlineMarker.mark(0);
                    Object next = it.next(continuation);
                    InlineMarker.mark(1);
                    collection.add(function1.invoke(next));
                } else {
                    function1 = Unit.INSTANCE;
                    InlineMarker.finallyStart(1);
                    receiveChannel.cancel(th);
                    InlineMarker.finallyEnd(1);
                    return collection;
                }
            }
        } catch (Throwable th2) {
            InlineMarker.finallyStart(1);
            receiveChannel.cancel(collection2);
            InlineMarker.finallyEnd(1);
        }
    }

    @Nullable
    @ObsoleteCoroutinesApi
    private static final Object mapTo$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull SendChannel sendChannel, @NotNull Function1 function1, @NotNull Continuation continuation) {
        SendChannel sendChannel2;
        Throwable th = (Throwable) null;
        try {
            ChannelIterator it = receiveChannel.iterator();
            while (true) {
                InlineMarker.mark(0);
                Object hasNext = it.hasNext(continuation);
                InlineMarker.mark(1);
                if (((Boolean) hasNext).booleanValue()) {
                    InlineMarker.mark(0);
                    hasNext = it.next(continuation);
                    InlineMarker.mark(1);
                    hasNext = function1.invoke(hasNext);
                    InlineMarker.mark(0);
                    sendChannel.send(hasNext, continuation);
                    InlineMarker.mark(2);
                    InlineMarker.mark(1);
                } else {
                    function1 = Unit.INSTANCE;
                    InlineMarker.finallyStart(1);
                    receiveChannel.cancel(th);
                    InlineMarker.finallyEnd(1);
                    return sendChannel;
                }
            }
        } catch (Throwable th2) {
            InlineMarker.finallyStart(1);
            receiveChannel.cancel(sendChannel2);
            InlineMarker.finallyEnd(1);
        }
    }

    @Nullable
    @ObsoleteCoroutinesApi
    private static final Object all$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Function1 function1, @NotNull Continuation continuation) {
        Function1 function12;
        Throwable th = (Throwable) null;
        try {
            ChannelIterator it = receiveChannel.iterator();
            Object hasNext;
            do {
                InlineMarker.mark(0);
                hasNext = it.hasNext(continuation);
                InlineMarker.mark(1);
                if (((Boolean) hasNext).booleanValue()) {
                    InlineMarker.mark(0);
                    hasNext = it.next(continuation);
                    InlineMarker.mark(1);
                } else {
                    function1 = Unit.INSTANCE;
                    InlineMarker.finallyStart(1);
                    receiveChannel.cancel(th);
                    InlineMarker.finallyEnd(1);
                    return Boolean.valueOf(true);
                }
            } while (((Boolean) function1.invoke(hasNext)).booleanValue());
            function1 = Boolean.valueOf(false);
            InlineMarker.finallyStart(2);
            receiveChannel.cancel(th);
            InlineMarker.finallyEnd(2);
            return function1;
        } catch (Throwable th2) {
            InlineMarker.finallyStart(1);
            receiveChannel.cancel(function12);
            InlineMarker.finallyEnd(1);
        }
    }

    @Nullable
    @ObsoleteCoroutinesApi
    private static final Object any$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Function1 function1, @NotNull Continuation continuation) {
        Function1 function12;
        Throwable th = (Throwable) null;
        try {
            ChannelIterator it = receiveChannel.iterator();
            Object next;
            do {
                InlineMarker.mark(0);
                Object hasNext = it.hasNext(continuation);
                InlineMarker.mark(1);
                if (((Boolean) hasNext).booleanValue()) {
                    InlineMarker.mark(0);
                    next = it.next(continuation);
                    InlineMarker.mark(1);
                } else {
                    function1 = Unit.INSTANCE;
                    InlineMarker.finallyStart(1);
                    receiveChannel.cancel(th);
                    InlineMarker.finallyEnd(1);
                    return Boolean.valueOf(false);
                }
            } while (!((Boolean) function1.invoke(next)).booleanValue());
            function1 = Boolean.valueOf(true);
            InlineMarker.finallyStart(2);
            receiveChannel.cancel(th);
            InlineMarker.finallyEnd(2);
            return function1;
        } catch (Throwable th2) {
            InlineMarker.finallyStart(1);
            receiveChannel.cancel(function12);
            InlineMarker.finallyEnd(1);
        }
    }

    @Nullable
    @ObsoleteCoroutinesApi
    private static final Object count$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Function1 function1, @NotNull Continuation continuation) {
        Function1 function12;
        Throwable th = (Throwable) null;
        try {
            ChannelIterator it = receiveChannel.iterator();
            int i = 0;
            while (true) {
                InlineMarker.mark(0);
                Object hasNext = it.hasNext(continuation);
                InlineMarker.mark(1);
                if (((Boolean) hasNext).booleanValue()) {
                    InlineMarker.mark(0);
                    hasNext = it.next(continuation);
                    InlineMarker.mark(1);
                    if (((Boolean) function1.invoke(hasNext)).booleanValue()) {
                        i++;
                    }
                } else {
                    function1 = Unit.INSTANCE;
                    InlineMarker.finallyStart(1);
                    receiveChannel.cancel(th);
                    InlineMarker.finallyEnd(1);
                    return Integer.valueOf(i);
                }
            }
        } catch (Throwable th2) {
            InlineMarker.finallyStart(1);
            receiveChannel.cancel(function12);
            InlineMarker.finallyEnd(1);
        }
    }

    @Nullable
    @ObsoleteCoroutinesApi
    private static final Object fold$$forInline(@NotNull ReceiveChannel receiveChannel, Object obj, @NotNull Function2 function2, @NotNull Continuation continuation) {
        Throwable th = (Throwable) null;
        try {
            ChannelIterator it = receiveChannel.iterator();
            while (true) {
                InlineMarker.mark(0);
                Object hasNext = it.hasNext(continuation);
                InlineMarker.mark(1);
                if (((Boolean) hasNext).booleanValue()) {
                    InlineMarker.mark(0);
                    Object next = it.next(continuation);
                    InlineMarker.mark(1);
                    obj = function2.invoke(obj, next);
                } else {
                    function2 = Unit.INSTANCE;
                    InlineMarker.finallyStart(1);
                    receiveChannel.cancel(th);
                    InlineMarker.finallyEnd(1);
                    return obj;
                }
            }
        } catch (Throwable th2) {
            InlineMarker.finallyStart(1);
            receiveChannel.cancel(th);
            InlineMarker.finallyEnd(1);
        }
    }

    @Nullable
    @ObsoleteCoroutinesApi
    private static final Object foldIndexed$$forInline(@NotNull ReceiveChannel receiveChannel, Object obj, @NotNull Function3 function3, @NotNull Continuation continuation) {
        Throwable th = (Throwable) null;
        try {
            ChannelIterator it = receiveChannel.iterator();
            int i = 0;
            while (true) {
                InlineMarker.mark(0);
                Object hasNext = it.hasNext(continuation);
                InlineMarker.mark(1);
                if (((Boolean) hasNext).booleanValue()) {
                    InlineMarker.mark(0);
                    hasNext = it.next(continuation);
                    InlineMarker.mark(1);
                    int i2 = i + 1;
                    obj = function3.invoke(Integer.valueOf(i), obj, hasNext);
                    i = i2;
                } else {
                    function3 = Unit.INSTANCE;
                    InlineMarker.finallyStart(1);
                    receiveChannel.cancel(th);
                    InlineMarker.finallyEnd(1);
                    return obj;
                }
            }
        } catch (Throwable th2) {
            InlineMarker.finallyStart(1);
            receiveChannel.cancel(th);
            InlineMarker.finallyEnd(1);
        }
    }

    @Nullable
    @ObsoleteCoroutinesApi
    private static final Object maxBy$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Function1 function1, @NotNull Continuation continuation) {
        Function1 function12;
        Object obj = null;
        Throwable th = (Throwable) null;
        try {
            ChannelIterator it = receiveChannel.iterator();
            InlineMarker.mark(0);
            Object hasNext = it.hasNext(continuation);
            InlineMarker.mark(1);
            if (((Boolean) hasNext).booleanValue()) {
                InlineMarker.mark(0);
                obj = it.next(continuation);
                InlineMarker.mark(1);
                Comparable comparable = (Comparable) function1.invoke(obj);
                while (true) {
                    InlineMarker.mark(0);
                    Object hasNext2 = it.hasNext(continuation);
                    InlineMarker.mark(1);
                    if (!((Boolean) hasNext2).booleanValue()) {
                        break;
                    }
                    InlineMarker.mark(0);
                    hasNext2 = it.next(continuation);
                    InlineMarker.mark(1);
                    Comparable comparable2 = (Comparable) function1.invoke(hasNext2);
                    if (comparable.compareTo(comparable2) < 0) {
                        obj = hasNext2;
                        comparable = comparable2;
                    }
                }
                int i = 2;
                InlineMarker.finallyStart(2);
            } else {
                function1 = 3;
                InlineMarker.finallyStart(3);
            }
            receiveChannel.cancel(th);
            InlineMarker.finallyEnd(function1);
            return obj;
        } catch (Throwable th2) {
            InlineMarker.finallyStart(1);
            receiveChannel.cancel(function12);
            InlineMarker.finallyEnd(1);
        }
    }

    @Nullable
    @ObsoleteCoroutinesApi
    private static final Object minBy$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Function1 function1, @NotNull Continuation continuation) {
        Function1 function12;
        Object obj = null;
        Throwable th = (Throwable) null;
        try {
            ChannelIterator it = receiveChannel.iterator();
            InlineMarker.mark(0);
            Object hasNext = it.hasNext(continuation);
            InlineMarker.mark(1);
            if (((Boolean) hasNext).booleanValue()) {
                InlineMarker.mark(0);
                obj = it.next(continuation);
                InlineMarker.mark(1);
                Comparable comparable = (Comparable) function1.invoke(obj);
                while (true) {
                    InlineMarker.mark(0);
                    Object hasNext2 = it.hasNext(continuation);
                    InlineMarker.mark(1);
                    if (!((Boolean) hasNext2).booleanValue()) {
                        break;
                    }
                    InlineMarker.mark(0);
                    hasNext2 = it.next(continuation);
                    InlineMarker.mark(1);
                    Comparable comparable2 = (Comparable) function1.invoke(hasNext2);
                    if (comparable.compareTo(comparable2) > 0) {
                        obj = hasNext2;
                        comparable = comparable2;
                    }
                }
                int i = 2;
                InlineMarker.finallyStart(2);
            } else {
                function1 = 3;
                InlineMarker.finallyStart(3);
            }
            receiveChannel.cancel(th);
            InlineMarker.finallyEnd(function1);
            return obj;
        } catch (Throwable th2) {
            InlineMarker.finallyStart(1);
            receiveChannel.cancel(function12);
            InlineMarker.finallyEnd(1);
        }
    }

    @Nullable
    @ObsoleteCoroutinesApi
    private static final Object none$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Function1 function1, @NotNull Continuation continuation) {
        Function1 function12;
        Throwable th = (Throwable) null;
        try {
            ChannelIterator it = receiveChannel.iterator();
            Object hasNext;
            do {
                InlineMarker.mark(0);
                hasNext = it.hasNext(continuation);
                InlineMarker.mark(1);
                if (((Boolean) hasNext).booleanValue()) {
                    InlineMarker.mark(0);
                    hasNext = it.next(continuation);
                    InlineMarker.mark(1);
                } else {
                    function1 = Unit.INSTANCE;
                    InlineMarker.finallyStart(1);
                    receiveChannel.cancel(th);
                    InlineMarker.finallyEnd(1);
                    return Boolean.valueOf(true);
                }
            } while (!((Boolean) function1.invoke(hasNext)).booleanValue());
            function1 = Boolean.valueOf(false);
            InlineMarker.finallyStart(2);
            receiveChannel.cancel(th);
            InlineMarker.finallyEnd(2);
            return function1;
        } catch (Throwable th2) {
            InlineMarker.finallyStart(1);
            receiveChannel.cancel(function12);
            InlineMarker.finallyEnd(1);
        }
    }

    @Nullable
    @ObsoleteCoroutinesApi
    private static final Object reduce$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Function2 function2, @NotNull Continuation continuation) {
        Function2 function22;
        Throwable th = (Throwable) null;
        try {
            ChannelIterator it = receiveChannel.iterator();
            InlineMarker.mark(0);
            Object hasNext = it.hasNext(continuation);
            InlineMarker.mark(1);
            if (((Boolean) hasNext).booleanValue()) {
                InlineMarker.mark(0);
                hasNext = it.next(continuation);
                InlineMarker.mark(1);
                while (true) {
                    InlineMarker.mark(0);
                    Object hasNext2 = it.hasNext(continuation);
                    InlineMarker.mark(1);
                    if (((Boolean) hasNext2).booleanValue()) {
                        InlineMarker.mark(0);
                        hasNext2 = it.next(continuation);
                        InlineMarker.mark(1);
                        hasNext = function2.invoke(hasNext, hasNext2);
                    } else {
                        InlineMarker.finallyStart(2);
                        receiveChannel.cancel(th);
                        InlineMarker.finallyEnd(2);
                        return hasNext;
                    }
                }
            }
            throw ((Throwable) new UnsupportedOperationException("Empty channel can't be reduced."));
        } catch (Throwable th2) {
            InlineMarker.finallyStart(1);
            receiveChannel.cancel(function22);
            InlineMarker.finallyEnd(1);
        }
    }

    @Nullable
    @ObsoleteCoroutinesApi
    private static final Object reduceIndexed$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Function3 function3, @NotNull Continuation continuation) {
        Function3 function32;
        Throwable th = (Throwable) null;
        try {
            ChannelIterator it = receiveChannel.iterator();
            InlineMarker.mark(0);
            Object hasNext = it.hasNext(continuation);
            InlineMarker.mark(1);
            if (((Boolean) hasNext).booleanValue()) {
                InlineMarker.mark(0);
                hasNext = it.next(continuation);
                InlineMarker.mark(1);
                Object obj = hasNext;
                int i = 1;
                while (true) {
                    InlineMarker.mark(0);
                    Object hasNext2 = it.hasNext(continuation);
                    InlineMarker.mark(1);
                    if (((Boolean) hasNext2).booleanValue()) {
                        Integer valueOf = Integer.valueOf(i);
                        i++;
                        InlineMarker.mark(0);
                        Object next = it.next(continuation);
                        InlineMarker.mark(1);
                        obj = function3.invoke(valueOf, obj, next);
                    } else {
                        InlineMarker.finallyStart(2);
                        receiveChannel.cancel(th);
                        InlineMarker.finallyEnd(2);
                        return obj;
                    }
                }
            }
            throw ((Throwable) new UnsupportedOperationException("Empty channel can't be reduced."));
        } catch (Throwable th2) {
            InlineMarker.finallyStart(1);
            receiveChannel.cancel(function32);
            InlineMarker.finallyEnd(1);
        }
    }

    @Nullable
    @ObsoleteCoroutinesApi
    private static final Object sumBy$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Function1 function1, @NotNull Continuation continuation) {
        Function1 function12;
        Throwable th = (Throwable) null;
        try {
            ChannelIterator it = receiveChannel.iterator();
            int i = 0;
            while (true) {
                InlineMarker.mark(0);
                Object hasNext = it.hasNext(continuation);
                InlineMarker.mark(1);
                if (((Boolean) hasNext).booleanValue()) {
                    InlineMarker.mark(0);
                    hasNext = it.next(continuation);
                    InlineMarker.mark(1);
                    i += ((Number) function1.invoke(hasNext)).intValue();
                } else {
                    function1 = Unit.INSTANCE;
                    InlineMarker.finallyStart(1);
                    receiveChannel.cancel(th);
                    InlineMarker.finallyEnd(1);
                    return Integer.valueOf(i);
                }
            }
        } catch (Throwable th2) {
            InlineMarker.finallyStart(1);
            receiveChannel.cancel(function12);
            InlineMarker.finallyEnd(1);
        }
    }

    @Nullable
    @ObsoleteCoroutinesApi
    private static final Object sumByDouble$$forInline(@NotNull ReceiveChannel receiveChannel, @NotNull Function1 function1, @NotNull Continuation continuation) {
        Function1 function12;
        Throwable th = (Throwable) null;
        try {
            ChannelIterator it = receiveChannel.iterator();
            double d = 0.0d;
            while (true) {
                InlineMarker.mark(0);
                Object hasNext = it.hasNext(continuation);
                InlineMarker.mark(1);
                if (((Boolean) hasNext).booleanValue()) {
                    InlineMarker.mark(0);
                    Object next = it.next(continuation);
                    InlineMarker.mark(1);
                    d += ((Number) function1.invoke(next)).doubleValue();
                } else {
                    function1 = Unit.INSTANCE;
                    InlineMarker.finallyStart(1);
                    receiveChannel.cancel(th);
                    InlineMarker.finallyEnd(1);
                    return Double.valueOf(d);
                }
            }
        } catch (Throwable th2) {
            InlineMarker.finallyStart(1);
            receiveChannel.cancel(function12);
            InlineMarker.finallyEnd(1);
        }
    }
}
