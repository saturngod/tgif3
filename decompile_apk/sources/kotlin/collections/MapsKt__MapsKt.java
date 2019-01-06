package kotlin.collections;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.PublishedApi;
import kotlin.SinceKotlin;
import kotlin.TypeCastException;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.sequences.Sequence;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000~\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010%\n\u0000\n\u0002\u0010&\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010(\n\u0002\u0010)\n\u0002\u0010'\n\u0002\b\n\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0016\u001a\u001e\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0003\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005\u001a1\u0010\u0006\u001a\u001e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0007j\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u0005`\b\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005H\b\u001a_\u0010\u0006\u001a\u001e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0007j\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u0005`\b\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u00052*\u0010\t\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u000b0\n\"\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u000b¢\u0006\u0002\u0010\f\u001a1\u0010\r\u001a\u001e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u000ej\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u0005`\u000f\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005H\b\u001a_\u0010\r\u001a\u001e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u000ej\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u0005`\u000f\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u00052*\u0010\t\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u000b0\n\"\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u000b¢\u0006\u0002\u0010\u0010\u001a\u0010\u0010\u0011\u001a\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u0001H\u0001\u001a!\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0003\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005H\b\u001aO\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0003\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u00052*\u0010\t\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u000b0\n\"\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u000b¢\u0006\u0002\u0010\u0014\u001a!\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0016\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005H\b\u001aO\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0016\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u00052*\u0010\t\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u000b0\n\"\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u000b¢\u0006\u0002\u0010\u0014\u001a*\u0010\u0017\u001a\u0002H\u0004\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0018H\n¢\u0006\u0002\u0010\u0019\u001a*\u0010\u001a\u001a\u0002H\u0005\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0018H\n¢\u0006\u0002\u0010\u0019\u001a9\u0010\u001b\u001a\u00020\u001c\"\t\b\u0000\u0010\u0004¢\u0006\u0002\b\u001d\"\u0004\b\u0001\u0010\u0005*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u00032\u0006\u0010\u001e\u001a\u0002H\u0004H\n¢\u0006\u0002\u0010\u001f\u001a1\u0010 \u001a\u00020\u001c\"\t\b\u0000\u0010\u0004¢\u0006\u0002\b\u001d*\u000e\u0012\u0006\b\u0001\u0012\u0002H\u0004\u0012\u0002\b\u00030\u00032\u0006\u0010\u001e\u001a\u0002H\u0004H\b¢\u0006\u0002\u0010\u001f\u001a7\u0010!\u001a\u00020\u001c\"\u0004\b\u0000\u0010\u0004\"\t\b\u0001\u0010\u0005¢\u0006\u0002\b\u001d*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u00032\u0006\u0010\"\u001a\u0002H\u0005H\b¢\u0006\u0002\u0010\u001f\u001aS\u0010#\u001a\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0003\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u00032\u001e\u0010$\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0018\u0012\u0004\u0012\u00020\u001c0%H\b\u001aG\u0010&\u001a\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0003\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u00032\u0012\u0010$\u001a\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u00020\u001c0%H\b\u001aS\u0010'\u001a\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0003\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u00032\u001e\u0010$\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0018\u0012\u0004\u0012\u00020\u001c0%H\b\u001an\u0010(\u001a\u0002H)\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005\"\u0018\b\u0002\u0010)*\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0004\u0012\u0006\b\u0000\u0012\u0002H\u00050\u0016*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u00032\u0006\u0010*\u001a\u0002H)2\u001e\u0010$\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0018\u0012\u0004\u0012\u00020\u001c0%H\b¢\u0006\u0002\u0010+\u001an\u0010,\u001a\u0002H)\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005\"\u0018\b\u0002\u0010)*\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0004\u0012\u0006\b\u0000\u0012\u0002H\u00050\u0016*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u00032\u0006\u0010*\u001a\u0002H)2\u001e\u0010$\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0018\u0012\u0004\u0012\u00020\u001c0%H\b¢\u0006\u0002\u0010+\u001aG\u0010-\u001a\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0003\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u00032\u0012\u0010$\u001a\u000e\u0012\u0004\u0012\u0002H\u0005\u0012\u0004\u0012\u00020\u001c0%H\b\u001a;\u0010.\u001a\u0004\u0018\u0001H\u0005\"\t\b\u0000\u0010\u0004¢\u0006\u0002\b\u001d\"\u0004\b\u0001\u0010\u0005*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u00032\u0006\u0010\u001e\u001a\u0002H\u0004H\n¢\u0006\u0002\u0010/\u001a@\u00100\u001a\u0002H\u0005\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u00032\u0006\u0010\u001e\u001a\u0002H\u00042\f\u00101\u001a\b\u0012\u0004\u0012\u0002H\u000502H\b¢\u0006\u0002\u00103\u001a@\u00104\u001a\u0002H\u0005\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u00032\u0006\u0010\u001e\u001a\u0002H\u00042\f\u00101\u001a\b\u0012\u0004\u0012\u0002H\u000502H\b¢\u0006\u0002\u00103\u001a@\u00105\u001a\u0002H\u0005\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u00162\u0006\u0010\u001e\u001a\u0002H\u00042\f\u00101\u001a\b\u0012\u0004\u0012\u0002H\u000502H\b¢\u0006\u0002\u00103\u001a1\u00106\u001a\u0002H\u0005\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u00032\u0006\u0010\u001e\u001a\u0002H\u0004H\u0007¢\u0006\u0002\u0010/\u001a<\u00107\u001a\u0002H8\"\u0014\b\u0000\u0010)*\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0003*\u0002H8\"\u0004\b\u0001\u00108*\u0002H)2\f\u00101\u001a\b\u0012\u0004\u0012\u0002H802H\b¢\u0006\u0002\u00109\u001a'\u0010:\u001a\u00020\u001c\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0003H\b\u001a:\u0010;\u001a\u00020\u001c\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005*\u0012\u0012\u0006\b\u0001\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u0005\u0018\u00010\u0003H\b\u0002\u000e\n\f\b\u0000\u0012\u0002\u0018\u0001\u001a\u0004\b\u0003\u0010\u0000\u001a9\u0010<\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u00180=\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0003H\n\u001a<\u0010<\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050?0>\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0016H\n¢\u0006\u0002\b@\u001aY\u0010A\u001a\u000e\u0012\u0004\u0012\u0002H8\u0012\u0004\u0012\u0002H\u00050\u0003\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005\"\u0004\b\u0002\u00108*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u00032\u001e\u0010B\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0018\u0012\u0004\u0012\u0002H80%H\b\u001at\u0010C\u001a\u0002H)\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005\"\u0004\b\u0002\u00108\"\u0018\b\u0003\u0010)*\u0012\u0012\u0006\b\u0000\u0012\u0002H8\u0012\u0006\b\u0000\u0012\u0002H\u00050\u0016*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u00032\u0006\u0010*\u001a\u0002H)2\u001e\u0010B\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0018\u0012\u0004\u0012\u0002H80%H\b¢\u0006\u0002\u0010+\u001aY\u0010D\u001a\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H80\u0003\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005\"\u0004\b\u0002\u00108*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u00032\u001e\u0010B\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0018\u0012\u0004\u0012\u0002H80%H\b\u001at\u0010E\u001a\u0002H)\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005\"\u0004\b\u0002\u00108\"\u0018\b\u0003\u0010)*\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0004\u0012\u0006\b\u0000\u0012\u0002H80\u0016*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u00032\u0006\u0010*\u001a\u0002H)2\u001e\u0010B\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0018\u0012\u0004\u0012\u0002H80%H\b¢\u0006\u0002\u0010+\u001a@\u0010F\u001a\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0003\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u00032\u0006\u0010\u001e\u001a\u0002H\u0004H\u0002¢\u0006\u0002\u0010G\u001aH\u0010F\u001a\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0003\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u00032\u000e\u0010H\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00040\nH\u0002¢\u0006\u0002\u0010I\u001aA\u0010F\u001a\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0003\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u00032\f\u0010H\u001a\b\u0012\u0004\u0012\u0002H\u00040JH\u0002\u001aA\u0010F\u001a\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0003\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u00032\f\u0010H\u001a\b\u0012\u0004\u0012\u0002H\u00040KH\u0002\u001a2\u0010L\u001a\u00020M\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u00162\u0006\u0010\u001e\u001a\u0002H\u0004H\n¢\u0006\u0002\u0010N\u001a:\u0010L\u001a\u00020M\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u00162\u000e\u0010H\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00040\nH\n¢\u0006\u0002\u0010O\u001a3\u0010L\u001a\u00020M\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u00162\f\u0010H\u001a\b\u0012\u0004\u0012\u0002H\u00040JH\n\u001a3\u0010L\u001a\u00020M\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u00162\f\u0010H\u001a\b\u0012\u0004\u0012\u0002H\u00040KH\n\u001a0\u0010P\u001a\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0003\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0003H\u0000\u001a3\u0010Q\u001a\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0003\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005*\u0010\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u0005\u0018\u00010\u0003H\b\u001aT\u0010R\u001a\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0003\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u00032\u001a\u0010\t\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u000b0\nH\u0002¢\u0006\u0002\u0010S\u001aG\u0010R\u001a\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0003\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u00032\u0012\u0010T\u001a\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u000bH\u0002\u001aM\u0010R\u001a\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0003\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u00032\u0018\u0010\t\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u000b0JH\u0002\u001aI\u0010R\u001a\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0003\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u00032\u0014\u0010U\u001a\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0003H\u0002\u001aM\u0010R\u001a\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0003\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u00032\u0018\u0010\t\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u000b0KH\u0002\u001aJ\u0010V\u001a\u00020M\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005*\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0004\u0012\u0006\b\u0000\u0012\u0002H\u00050\u00162\u001a\u0010\t\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u000b0\nH\n¢\u0006\u0002\u0010W\u001a=\u0010V\u001a\u00020M\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005*\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0004\u0012\u0006\b\u0000\u0012\u0002H\u00050\u00162\u0012\u0010T\u001a\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u000bH\n\u001aC\u0010V\u001a\u00020M\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005*\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0004\u0012\u0006\b\u0000\u0012\u0002H\u00050\u00162\u0018\u0010\t\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u000b0JH\n\u001a=\u0010V\u001a\u00020M\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005*\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0004\u0012\u0006\b\u0000\u0012\u0002H\u00050\u00162\u0012\u0010U\u001a\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0003H\n\u001aC\u0010V\u001a\u00020M\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005*\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0004\u0012\u0006\b\u0000\u0012\u0002H\u00050\u00162\u0018\u0010\t\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u000b0KH\n\u001aG\u0010X\u001a\u00020M\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005*\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0004\u0012\u0006\b\u0000\u0012\u0002H\u00050\u00162\u001a\u0010\t\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u000b0\n¢\u0006\u0002\u0010W\u001a@\u0010X\u001a\u00020M\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005*\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0004\u0012\u0006\b\u0000\u0012\u0002H\u00050\u00162\u0018\u0010\t\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u000b0J\u001a@\u0010X\u001a\u00020M\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005*\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0004\u0012\u0006\b\u0000\u0012\u0002H\u00050\u00162\u0018\u0010\t\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u000b0K\u001a;\u0010Y\u001a\u0004\u0018\u0001H\u0005\"\t\b\u0000\u0010\u0004¢\u0006\u0002\b\u001d\"\u0004\b\u0001\u0010\u0005*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u00162\u0006\u0010\u001e\u001a\u0002H\u0004H\b¢\u0006\u0002\u0010/\u001a:\u0010Z\u001a\u00020M\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u00162\u0006\u0010\u001e\u001a\u0002H\u00042\u0006\u0010\"\u001a\u0002H\u0005H\n¢\u0006\u0002\u0010[\u001a;\u0010\\\u001a\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0003\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005*\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u000b0\n¢\u0006\u0002\u0010\u0014\u001aQ\u0010\\\u001a\u0002H)\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005\"\u0018\b\u0002\u0010)*\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0004\u0012\u0006\b\u0000\u0012\u0002H\u00050\u0016*\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u000b0\n2\u0006\u0010*\u001a\u0002H)¢\u0006\u0002\u0010]\u001a4\u0010\\\u001a\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0003\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005*\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u000b0J\u001aO\u0010\\\u001a\u0002H)\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005\"\u0018\b\u0002\u0010)*\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0004\u0012\u0006\b\u0000\u0012\u0002H\u00050\u0016*\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u000b0J2\u0006\u0010*\u001a\u0002H)¢\u0006\u0002\u0010^\u001a2\u0010\\\u001a\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0003\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0003H\u0007\u001aM\u0010\\\u001a\u0002H)\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005\"\u0018\b\u0002\u0010)*\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0004\u0012\u0006\b\u0000\u0012\u0002H\u00050\u0016*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u00032\u0006\u0010*\u001a\u0002H)H\u0007¢\u0006\u0002\u0010_\u001a4\u0010\\\u001a\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0003\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005*\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u000b0K\u001aO\u0010\\\u001a\u0002H)\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005\"\u0018\b\u0002\u0010)*\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0004\u0012\u0006\b\u0000\u0012\u0002H\u00050\u0016*\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u000b0K2\u0006\u0010*\u001a\u0002H)¢\u0006\u0002\u0010`\u001a2\u0010a\u001a\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0016\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0003H\u0007\u001a1\u0010b\u001a\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u000b\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0018H\b\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000¨\u0006c"}, d2 = {"INT_MAX_POWER_OF_TWO", "", "emptyMap", "", "K", "V", "hashMapOf", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "pairs", "", "Lkotlin/Pair;", "([Lkotlin/Pair;)Ljava/util/HashMap;", "linkedMapOf", "Ljava/util/LinkedHashMap;", "Lkotlin/collections/LinkedHashMap;", "([Lkotlin/Pair;)Ljava/util/LinkedHashMap;", "mapCapacity", "expectedSize", "mapOf", "([Lkotlin/Pair;)Ljava/util/Map;", "mutableMapOf", "", "component1", "", "(Ljava/util/Map$Entry;)Ljava/lang/Object;", "component2", "contains", "", "Lkotlin/internal/OnlyInputTypes;", "key", "(Ljava/util/Map;Ljava/lang/Object;)Z", "containsKey", "containsValue", "value", "filter", "predicate", "Lkotlin/Function1;", "filterKeys", "filterNot", "filterNotTo", "M", "destination", "(Ljava/util/Map;Ljava/util/Map;Lkotlin/jvm/functions/Function1;)Ljava/util/Map;", "filterTo", "filterValues", "get", "(Ljava/util/Map;Ljava/lang/Object;)Ljava/lang/Object;", "getOrElse", "defaultValue", "Lkotlin/Function0;", "(Ljava/util/Map;Ljava/lang/Object;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "getOrElseNullable", "getOrPut", "getValue", "ifEmpty", "R", "(Ljava/util/Map;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "isNotEmpty", "isNullOrEmpty", "iterator", "", "", "", "mutableIterator", "mapKeys", "transform", "mapKeysTo", "mapValues", "mapValuesTo", "minus", "(Ljava/util/Map;Ljava/lang/Object;)Ljava/util/Map;", "keys", "(Ljava/util/Map;[Ljava/lang/Object;)Ljava/util/Map;", "", "Lkotlin/sequences/Sequence;", "minusAssign", "", "(Ljava/util/Map;Ljava/lang/Object;)V", "(Ljava/util/Map;[Ljava/lang/Object;)V", "optimizeReadOnlyMap", "orEmpty", "plus", "(Ljava/util/Map;[Lkotlin/Pair;)Ljava/util/Map;", "pair", "map", "plusAssign", "(Ljava/util/Map;[Lkotlin/Pair;)V", "putAll", "remove", "set", "(Ljava/util/Map;Ljava/lang/Object;Ljava/lang/Object;)V", "toMap", "([Lkotlin/Pair;Ljava/util/Map;)Ljava/util/Map;", "(Ljava/lang/Iterable;Ljava/util/Map;)Ljava/util/Map;", "(Ljava/util/Map;Ljava/util/Map;)Ljava/util/Map;", "(Lkotlin/sequences/Sequence;Ljava/util/Map;)Ljava/util/Map;", "toMutableMap", "toPair", "kotlin-stdlib"}, k = 5, mv = {1, 1, 13}, xi = 1, xs = "kotlin/collections/MapsKt")
/* compiled from: Maps.kt */
class MapsKt__MapsKt extends MapsKt__MapsJVMKt {
    private static final int INT_MAX_POWER_OF_TWO = 1073741824;

    @NotNull
    public static final <K, V> Map<K, V> emptyMap() {
        EmptyMap emptyMap = EmptyMap.INSTANCE;
        if (emptyMap != null) {
            return emptyMap;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.Map<K, V>");
    }

    @NotNull
    public static final <K, V> Map<K, V> mapOf(@NotNull Pair<? extends K, ? extends V>... pairArr) {
        Intrinsics.checkParameterIsNotNull(pairArr, "pairs");
        return pairArr.length > 0 ? toMap((Pair[]) pairArr, (Map) new LinkedHashMap(mapCapacity(pairArr.length))) : emptyMap();
    }

    @InlineOnly
    private static final <K, V> Map<K, V> mapOf() {
        return emptyMap();
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final <K, V> Map<K, V> mutableMapOf() {
        return new LinkedHashMap();
    }

    @NotNull
    public static final <K, V> Map<K, V> mutableMapOf(@NotNull Pair<? extends K, ? extends V>... pairArr) {
        Intrinsics.checkParameterIsNotNull(pairArr, "pairs");
        Map<K, V> linkedHashMap = new LinkedHashMap(mapCapacity(pairArr.length));
        putAll((Map) linkedHashMap, (Pair[]) pairArr);
        return linkedHashMap;
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final <K, V> HashMap<K, V> hashMapOf() {
        return new HashMap();
    }

    @NotNull
    public static final <K, V> HashMap<K, V> hashMapOf(@NotNull Pair<? extends K, ? extends V>... pairArr) {
        Intrinsics.checkParameterIsNotNull(pairArr, "pairs");
        HashMap<K, V> hashMap = new HashMap(mapCapacity(pairArr.length));
        putAll((Map) hashMap, (Pair[]) pairArr);
        return hashMap;
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final <K, V> LinkedHashMap<K, V> linkedMapOf() {
        return new LinkedHashMap();
    }

    @NotNull
    public static final <K, V> LinkedHashMap<K, V> linkedMapOf(@NotNull Pair<? extends K, ? extends V>... pairArr) {
        Intrinsics.checkParameterIsNotNull(pairArr, "pairs");
        return (LinkedHashMap) toMap((Pair[]) pairArr, (Map) new LinkedHashMap(mapCapacity(pairArr.length)));
    }

    @PublishedApi
    public static final int mapCapacity(int i) {
        if (i < 3) {
            return i + 1;
        }
        return i < INT_MAX_POWER_OF_TWO ? i + (i / 3) : Integer.MAX_VALUE;
    }

    @InlineOnly
    private static final <K, V> boolean isNotEmpty(@NotNull Map<? extends K, ? extends V> map) {
        return map.isEmpty() ^ 1;
    }

    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final <K, V> boolean isNullOrEmpty(@Nullable Map<? extends K, ? extends V> map) {
        if (map != null) {
            if (map.isEmpty() == null) {
                return null;
            }
        }
        return true;
    }

    @InlineOnly
    private static final <K, V> Map<K, V> orEmpty(@Nullable Map<K, ? extends V> map) {
        return map != null ? map : emptyMap();
    }

    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final <M extends Map<?, ?> & R, R> R ifEmpty(M m, Function0<? extends R> function0) {
        return m.isEmpty() ? function0.invoke() : m;
    }

    @InlineOnly
    private static final <K, V> boolean contains(@NotNull Map<? extends K, ? extends V> map, K k) {
        Intrinsics.checkParameterIsNotNull(map, "receiver$0");
        return map.containsKey(k);
    }

    @InlineOnly
    private static final <K, V> V get(@NotNull Map<? extends K, ? extends V> map, K k) {
        Intrinsics.checkParameterIsNotNull(map, "receiver$0");
        return map.get(k);
    }

    @InlineOnly
    private static final <K, V> void set(@NotNull Map<K, V> map, K k, V v) {
        Intrinsics.checkParameterIsNotNull(map, "receiver$0");
        map.put(k, v);
    }

    @InlineOnly
    private static final <K> boolean containsKey(@NotNull Map<? extends K, ?> map, K k) {
        if (map != null) {
            return map.containsKey(k);
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.Map<K, *>");
    }

    @InlineOnly
    private static final <K, V> boolean containsValue(@NotNull Map<K, ? extends V> map, V v) {
        return map.containsValue(v);
    }

    @InlineOnly
    private static final <K, V> V remove(@NotNull Map<? extends K, V> map, K k) {
        if (map != null) {
            return TypeIntrinsics.asMutableMap(map).remove(k);
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.MutableMap<K, V>");
    }

    @InlineOnly
    private static final <K, V> K component1(@NotNull Entry<? extends K, ? extends V> entry) {
        Intrinsics.checkParameterIsNotNull(entry, "receiver$0");
        return entry.getKey();
    }

    @InlineOnly
    private static final <K, V> V component2(@NotNull Entry<? extends K, ? extends V> entry) {
        Intrinsics.checkParameterIsNotNull(entry, "receiver$0");
        return entry.getValue();
    }

    @InlineOnly
    private static final <K, V> Pair<K, V> toPair(@NotNull Entry<? extends K, ? extends V> entry) {
        return new Pair(entry.getKey(), entry.getValue());
    }

    @InlineOnly
    private static final <K, V> V getOrElse(@NotNull Map<K, ? extends V> map, K k, Function0<? extends V> function0) {
        map = map.get(k);
        return map != null ? map : function0.invoke();
    }

    public static final <K, V> V getOrElseNullable(@NotNull Map<K, ? extends V> map, K k, @NotNull Function0<? extends V> function0) {
        Intrinsics.checkParameterIsNotNull(map, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function0, "defaultValue");
        V v = map.get(k);
        return (v == null && map.containsKey(k) == null) ? function0.invoke() : v;
    }

    @SinceKotlin(version = "1.1")
    public static final <K, V> V getValue(@NotNull Map<K, ? extends V> map, K k) {
        Intrinsics.checkParameterIsNotNull(map, "receiver$0");
        return MapsKt__MapWithDefaultKt.getOrImplicitDefaultNullable(map, k);
    }

    public static final <K, V> V getOrPut(@NotNull Map<K, V> map, K k, @NotNull Function0<? extends V> function0) {
        Intrinsics.checkParameterIsNotNull(map, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function0, "defaultValue");
        V v = map.get(k);
        if (v != null) {
            return v;
        }
        v = function0.invoke();
        map.put(k, v);
        return v;
    }

    @InlineOnly
    private static final <K, V> Iterator<Entry<K, V>> iterator(@NotNull Map<? extends K, ? extends V> map) {
        Intrinsics.checkParameterIsNotNull(map, "receiver$0");
        return map.entrySet().iterator();
    }

    @InlineOnly
    @JvmName(name = "mutableIterator")
    private static final <K, V> Iterator<Entry<K, V>> mutableIterator(@NotNull Map<K, V> map) {
        Intrinsics.checkParameterIsNotNull(map, "receiver$0");
        return map.entrySet().iterator();
    }

    @NotNull
    public static final <K, V, R, M extends Map<? super K, ? super R>> M mapValuesTo(@NotNull Map<? extends K, ? extends V> map, @NotNull M m, @NotNull Function1<? super Entry<? extends K, ? extends V>, ? extends R> function1) {
        Intrinsics.checkParameterIsNotNull(map, "receiver$0");
        Intrinsics.checkParameterIsNotNull(m, "destination");
        Intrinsics.checkParameterIsNotNull(function1, "transform");
        for (Object next : map.entrySet()) {
            m.put(((Entry) next).getKey(), function1.invoke(next));
        }
        return m;
    }

    @NotNull
    public static final <K, V, R, M extends Map<? super R, ? super V>> M mapKeysTo(@NotNull Map<? extends K, ? extends V> map, @NotNull M m, @NotNull Function1<? super Entry<? extends K, ? extends V>, ? extends R> function1) {
        Intrinsics.checkParameterIsNotNull(map, "receiver$0");
        Intrinsics.checkParameterIsNotNull(m, "destination");
        Intrinsics.checkParameterIsNotNull(function1, "transform");
        for (Object next : map.entrySet()) {
            m.put(function1.invoke(next), ((Entry) next).getValue());
        }
        return m;
    }

    public static final <K, V> void putAll(@NotNull Map<? super K, ? super V> map, @NotNull Pair<? extends K, ? extends V>[] pairArr) {
        Intrinsics.checkParameterIsNotNull(map, "receiver$0");
        Intrinsics.checkParameterIsNotNull(pairArr, "pairs");
        for (Pair pair : pairArr) {
            map.put(pair.component1(), pair.component2());
        }
    }

    public static final <K, V> void putAll(@NotNull Map<? super K, ? super V> map, @NotNull Iterable<? extends Pair<? extends K, ? extends V>> iterable) {
        Intrinsics.checkParameterIsNotNull(map, "receiver$0");
        Intrinsics.checkParameterIsNotNull(iterable, "pairs");
        for (Pair pair : iterable) {
            map.put(pair.component1(), pair.component2());
        }
    }

    public static final <K, V> void putAll(@NotNull Map<? super K, ? super V> map, @NotNull Sequence<? extends Pair<? extends K, ? extends V>> sequence) {
        Intrinsics.checkParameterIsNotNull(map, "receiver$0");
        Intrinsics.checkParameterIsNotNull(sequence, "pairs");
        for (Pair pair : sequence) {
            map.put(pair.component1(), pair.component2());
        }
    }

    @NotNull
    public static final <K, V, R> Map<K, R> mapValues(@NotNull Map<? extends K, ? extends V> map, @NotNull Function1<? super Entry<? extends K, ? extends V>, ? extends R> function1) {
        Intrinsics.checkParameterIsNotNull(map, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "transform");
        Map<K, R> linkedHashMap = new LinkedHashMap(mapCapacity(map.size()));
        for (Object next : map.entrySet()) {
            linkedHashMap.put(((Entry) next).getKey(), function1.invoke(next));
        }
        return linkedHashMap;
    }

    @NotNull
    public static final <K, V, R> Map<R, V> mapKeys(@NotNull Map<? extends K, ? extends V> map, @NotNull Function1<? super Entry<? extends K, ? extends V>, ? extends R> function1) {
        Intrinsics.checkParameterIsNotNull(map, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "transform");
        Map<R, V> linkedHashMap = new LinkedHashMap(mapCapacity(map.size()));
        for (Object next : map.entrySet()) {
            linkedHashMap.put(function1.invoke(next), ((Entry) next).getValue());
        }
        return linkedHashMap;
    }

    @NotNull
    public static final <K, V> Map<K, V> filterKeys(@NotNull Map<? extends K, ? extends V> map, @NotNull Function1<? super K, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(map, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "predicate");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        map = map.entrySet().iterator();
        while (map.hasNext()) {
            Entry entry = (Entry) map.next();
            if (((Boolean) function1.invoke(entry.getKey())).booleanValue()) {
                linkedHashMap.put(entry.getKey(), entry.getValue());
            }
        }
        return linkedHashMap;
    }

    @NotNull
    public static final <K, V> Map<K, V> filterValues(@NotNull Map<? extends K, ? extends V> map, @NotNull Function1<? super V, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(map, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "predicate");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        map = map.entrySet().iterator();
        while (map.hasNext()) {
            Entry entry = (Entry) map.next();
            if (((Boolean) function1.invoke(entry.getValue())).booleanValue()) {
                linkedHashMap.put(entry.getKey(), entry.getValue());
            }
        }
        return linkedHashMap;
    }

    @NotNull
    public static final <K, V, M extends Map<? super K, ? super V>> M filterTo(@NotNull Map<? extends K, ? extends V> map, @NotNull M m, @NotNull Function1<? super Entry<? extends K, ? extends V>, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(map, "receiver$0");
        Intrinsics.checkParameterIsNotNull(m, "destination");
        Intrinsics.checkParameterIsNotNull(function1, "predicate");
        map = map.entrySet().iterator();
        while (map.hasNext()) {
            Entry entry = (Entry) map.next();
            if (((Boolean) function1.invoke(entry)).booleanValue()) {
                m.put(entry.getKey(), entry.getValue());
            }
        }
        return m;
    }

    @NotNull
    public static final <K, V> Map<K, V> filter(@NotNull Map<? extends K, ? extends V> map, @NotNull Function1<? super Entry<? extends K, ? extends V>, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(map, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "predicate");
        Map<K, V> linkedHashMap = new LinkedHashMap();
        map = map.entrySet().iterator();
        while (map.hasNext()) {
            Entry entry = (Entry) map.next();
            if (((Boolean) function1.invoke(entry)).booleanValue()) {
                linkedHashMap.put(entry.getKey(), entry.getValue());
            }
        }
        return linkedHashMap;
    }

    @NotNull
    public static final <K, V, M extends Map<? super K, ? super V>> M filterNotTo(@NotNull Map<? extends K, ? extends V> map, @NotNull M m, @NotNull Function1<? super Entry<? extends K, ? extends V>, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(map, "receiver$0");
        Intrinsics.checkParameterIsNotNull(m, "destination");
        Intrinsics.checkParameterIsNotNull(function1, "predicate");
        map = map.entrySet().iterator();
        while (map.hasNext()) {
            Entry entry = (Entry) map.next();
            if (!((Boolean) function1.invoke(entry)).booleanValue()) {
                m.put(entry.getKey(), entry.getValue());
            }
        }
        return m;
    }

    @NotNull
    public static final <K, V> Map<K, V> filterNot(@NotNull Map<? extends K, ? extends V> map, @NotNull Function1<? super Entry<? extends K, ? extends V>, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(map, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "predicate");
        Map<K, V> linkedHashMap = new LinkedHashMap();
        map = map.entrySet().iterator();
        while (map.hasNext()) {
            Entry entry = (Entry) map.next();
            if (!((Boolean) function1.invoke(entry)).booleanValue()) {
                linkedHashMap.put(entry.getKey(), entry.getValue());
            }
        }
        return linkedHashMap;
    }

    @NotNull
    public static final <K, V> Map<K, V> toMap(@NotNull Iterable<? extends Pair<? extends K, ? extends V>> iterable) {
        Intrinsics.checkParameterIsNotNull(iterable, "receiver$0");
        if (!(iterable instanceof Collection)) {
            return optimizeReadOnlyMap(toMap((Iterable) iterable, (Map) new LinkedHashMap()));
        }
        Collection collection = (Collection) iterable;
        switch (collection.size()) {
            case 0:
                iterable = emptyMap();
                break;
            case 1:
                iterable = MapsKt__MapsJVMKt.mapOf((Pair) (iterable instanceof List ? ((List) iterable).get(0) : iterable.iterator().next()));
                break;
            default:
                iterable = toMap((Iterable) iterable, (Map) new LinkedHashMap(mapCapacity(collection.size())));
                break;
        }
        return iterable;
    }

    @NotNull
    public static final <K, V, M extends Map<? super K, ? super V>> M toMap(@NotNull Iterable<? extends Pair<? extends K, ? extends V>> iterable, @NotNull M m) {
        Intrinsics.checkParameterIsNotNull(iterable, "receiver$0");
        Intrinsics.checkParameterIsNotNull(m, "destination");
        putAll((Map) m, (Iterable) iterable);
        return m;
    }

    @NotNull
    public static final <K, V> Map<K, V> toMap(@NotNull Pair<? extends K, ? extends V>[] pairArr) {
        Intrinsics.checkParameterIsNotNull(pairArr, "receiver$0");
        switch (pairArr.length) {
            case 0:
                return emptyMap();
            case 1:
                return MapsKt__MapsJVMKt.mapOf(pairArr[0]);
            default:
                return toMap((Pair[]) pairArr, (Map) new LinkedHashMap(mapCapacity(pairArr.length)));
        }
    }

    @NotNull
    public static final <K, V, M extends Map<? super K, ? super V>> M toMap(@NotNull Pair<? extends K, ? extends V>[] pairArr, @NotNull M m) {
        Intrinsics.checkParameterIsNotNull(pairArr, "receiver$0");
        Intrinsics.checkParameterIsNotNull(m, "destination");
        putAll((Map) m, (Pair[]) pairArr);
        return m;
    }

    @NotNull
    public static final <K, V> Map<K, V> toMap(@NotNull Sequence<? extends Pair<? extends K, ? extends V>> sequence) {
        Intrinsics.checkParameterIsNotNull(sequence, "receiver$0");
        return optimizeReadOnlyMap(toMap((Sequence) sequence, (Map) new LinkedHashMap()));
    }

    @NotNull
    public static final <K, V, M extends Map<? super K, ? super V>> M toMap(@NotNull Sequence<? extends Pair<? extends K, ? extends V>> sequence, @NotNull M m) {
        Intrinsics.checkParameterIsNotNull(sequence, "receiver$0");
        Intrinsics.checkParameterIsNotNull(m, "destination");
        putAll((Map) m, (Sequence) sequence);
        return m;
    }

    @NotNull
    @SinceKotlin(version = "1.1")
    public static final <K, V> Map<K, V> toMap(@NotNull Map<? extends K, ? extends V> map) {
        Intrinsics.checkParameterIsNotNull(map, "receiver$0");
        switch (map.size()) {
            case 0:
                return emptyMap();
            case 1:
                return MapsKt__MapsJVMKt.toSingletonMap(map);
            default:
                return toMutableMap(map);
        }
    }

    @NotNull
    @SinceKotlin(version = "1.1")
    public static final <K, V> Map<K, V> toMutableMap(@NotNull Map<? extends K, ? extends V> map) {
        Intrinsics.checkParameterIsNotNull(map, "receiver$0");
        return new LinkedHashMap(map);
    }

    @NotNull
    @SinceKotlin(version = "1.1")
    public static final <K, V, M extends Map<? super K, ? super V>> M toMap(@NotNull Map<? extends K, ? extends V> map, @NotNull M m) {
        Intrinsics.checkParameterIsNotNull(map, "receiver$0");
        Intrinsics.checkParameterIsNotNull(m, "destination");
        m.putAll(map);
        return m;
    }

    @NotNull
    public static final <K, V> Map<K, V> plus(@NotNull Map<? extends K, ? extends V> map, @NotNull Pair<? extends K, ? extends V> pair) {
        Intrinsics.checkParameterIsNotNull(map, "receiver$0");
        Intrinsics.checkParameterIsNotNull(pair, "pair");
        if (map.isEmpty()) {
            return MapsKt__MapsJVMKt.mapOf(pair);
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(map);
        linkedHashMap.put(pair.getFirst(), pair.getSecond());
        return linkedHashMap;
    }

    @NotNull
    public static final <K, V> Map<K, V> plus(@NotNull Map<? extends K, ? extends V> map, @NotNull Iterable<? extends Pair<? extends K, ? extends V>> iterable) {
        Intrinsics.checkParameterIsNotNull(map, "receiver$0");
        Intrinsics.checkParameterIsNotNull(iterable, "pairs");
        if (map.isEmpty()) {
            return toMap((Iterable) iterable);
        }
        map = new LinkedHashMap(map);
        putAll((Map) map, (Iterable) iterable);
        return map;
    }

    @NotNull
    public static final <K, V> Map<K, V> plus(@NotNull Map<? extends K, ? extends V> map, @NotNull Pair<? extends K, ? extends V>[] pairArr) {
        Intrinsics.checkParameterIsNotNull(map, "receiver$0");
        Intrinsics.checkParameterIsNotNull(pairArr, "pairs");
        if (map.isEmpty()) {
            return toMap((Pair[]) pairArr);
        }
        map = new LinkedHashMap(map);
        putAll((Map) map, (Pair[]) pairArr);
        return map;
    }

    @NotNull
    public static final <K, V> Map<K, V> plus(@NotNull Map<? extends K, ? extends V> map, @NotNull Sequence<? extends Pair<? extends K, ? extends V>> sequence) {
        Intrinsics.checkParameterIsNotNull(map, "receiver$0");
        Intrinsics.checkParameterIsNotNull(sequence, "pairs");
        Map linkedHashMap = new LinkedHashMap(map);
        putAll(linkedHashMap, (Sequence) sequence);
        return optimizeReadOnlyMap(linkedHashMap);
    }

    @NotNull
    public static final <K, V> Map<K, V> plus(@NotNull Map<? extends K, ? extends V> map, @NotNull Map<? extends K, ? extends V> map2) {
        Intrinsics.checkParameterIsNotNull(map, "receiver$0");
        Intrinsics.checkParameterIsNotNull(map2, "map");
        LinkedHashMap linkedHashMap = new LinkedHashMap(map);
        linkedHashMap.putAll(map2);
        return linkedHashMap;
    }

    @InlineOnly
    private static final <K, V> void plusAssign(@NotNull Map<? super K, ? super V> map, Pair<? extends K, ? extends V> pair) {
        Intrinsics.checkParameterIsNotNull(map, "receiver$0");
        map.put(pair.getFirst(), pair.getSecond());
    }

    @InlineOnly
    private static final <K, V> void plusAssign(@NotNull Map<? super K, ? super V> map, Iterable<? extends Pair<? extends K, ? extends V>> iterable) {
        Intrinsics.checkParameterIsNotNull(map, "receiver$0");
        putAll((Map) map, (Iterable) iterable);
    }

    @InlineOnly
    private static final <K, V> void plusAssign(@NotNull Map<? super K, ? super V> map, Pair<? extends K, ? extends V>[] pairArr) {
        Intrinsics.checkParameterIsNotNull(map, "receiver$0");
        putAll((Map) map, (Pair[]) pairArr);
    }

    @InlineOnly
    private static final <K, V> void plusAssign(@NotNull Map<? super K, ? super V> map, Sequence<? extends Pair<? extends K, ? extends V>> sequence) {
        Intrinsics.checkParameterIsNotNull(map, "receiver$0");
        putAll((Map) map, (Sequence) sequence);
    }

    @InlineOnly
    private static final <K, V> void plusAssign(@NotNull Map<? super K, ? super V> map, Map<K, ? extends V> map2) {
        Intrinsics.checkParameterIsNotNull(map, "receiver$0");
        map.putAll(map2);
    }

    @NotNull
    @SinceKotlin(version = "1.1")
    public static final <K, V> Map<K, V> minus(@NotNull Map<? extends K, ? extends V> map, K k) {
        Intrinsics.checkParameterIsNotNull(map, "receiver$0");
        map = toMutableMap(map);
        map.remove(k);
        return optimizeReadOnlyMap(map);
    }

    @NotNull
    @SinceKotlin(version = "1.1")
    public static final <K, V> Map<K, V> minus(@NotNull Map<? extends K, ? extends V> map, @NotNull Iterable<? extends K> iterable) {
        Intrinsics.checkParameterIsNotNull(map, "receiver$0");
        Intrinsics.checkParameterIsNotNull(iterable, "keys");
        map = toMutableMap(map);
        CollectionsKt__MutableCollectionsKt.removeAll((Collection) map.keySet(), (Iterable) iterable);
        return optimizeReadOnlyMap(map);
    }

    @NotNull
    @SinceKotlin(version = "1.1")
    public static final <K, V> Map<K, V> minus(@NotNull Map<? extends K, ? extends V> map, @NotNull K[] kArr) {
        Intrinsics.checkParameterIsNotNull(map, "receiver$0");
        Intrinsics.checkParameterIsNotNull(kArr, "keys");
        map = toMutableMap(map);
        CollectionsKt__MutableCollectionsKt.removeAll((Collection) map.keySet(), (Object[]) kArr);
        return optimizeReadOnlyMap(map);
    }

    @NotNull
    @SinceKotlin(version = "1.1")
    public static final <K, V> Map<K, V> minus(@NotNull Map<? extends K, ? extends V> map, @NotNull Sequence<? extends K> sequence) {
        Intrinsics.checkParameterIsNotNull(map, "receiver$0");
        Intrinsics.checkParameterIsNotNull(sequence, "keys");
        map = toMutableMap(map);
        CollectionsKt__MutableCollectionsKt.removeAll((Collection) map.keySet(), (Sequence) sequence);
        return optimizeReadOnlyMap(map);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final <K, V> void minusAssign(@NotNull Map<K, V> map, K k) {
        Intrinsics.checkParameterIsNotNull(map, "receiver$0");
        map.remove(k);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final <K, V> void minusAssign(@NotNull Map<K, V> map, Iterable<? extends K> iterable) {
        Intrinsics.checkParameterIsNotNull(map, "receiver$0");
        CollectionsKt__MutableCollectionsKt.removeAll((Collection) map.keySet(), (Iterable) iterable);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final <K, V> void minusAssign(@NotNull Map<K, V> map, K[] kArr) {
        Intrinsics.checkParameterIsNotNull(map, "receiver$0");
        CollectionsKt__MutableCollectionsKt.removeAll((Collection) map.keySet(), (Object[]) kArr);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final <K, V> void minusAssign(@NotNull Map<K, V> map, Sequence<? extends K> sequence) {
        Intrinsics.checkParameterIsNotNull(map, "receiver$0");
        CollectionsKt__MutableCollectionsKt.removeAll((Collection) map.keySet(), (Sequence) sequence);
    }

    @NotNull
    public static final <K, V> Map<K, V> optimizeReadOnlyMap(@NotNull Map<K, ? extends V> map) {
        Intrinsics.checkParameterIsNotNull(map, "receiver$0");
        switch (map.size()) {
            case 0:
                return emptyMap();
            case 1:
                return MapsKt__MapsJVMKt.toSingletonMap(map);
            default:
                return map;
        }
    }
}
