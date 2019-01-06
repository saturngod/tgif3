package kotlin.text;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.SinceKotlin;
import kotlin.TuplesKt;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.Grouping;
import kotlin.collections.IndexedValue;
import kotlin.collections.IndexingIterable;
import kotlin.collections.SlidingWindowKt;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import kotlin.ranges.IntProgression;
import kotlin.ranges.IntRange;
import kotlin.sequences.Sequence;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000Ü\u0001\n\u0000\n\u0002\u0010\u000b\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\f\n\u0002\b\u0002\n\u0002\u0010\u001c\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010%\n\u0002\b\b\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u001f\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0000\n\u0002\b\b\n\u0002\u0010\u000f\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a!\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\b\u001a\n\u0010\u0006\u001a\u00020\u0001*\u00020\u0002\u001a!\u0010\u0006\u001a\u00020\u0001*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\b\u001a\u0010\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\b*\u00020\u0002\u001a\u0010\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\n*\u00020\u0002\u001aE\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u0002H\r\u0012\u0004\u0012\u0002H\u000e0\f\"\u0004\b\u0000\u0010\r\"\u0004\b\u0001\u0010\u000e*\u00020\u00022\u001e\u0010\u000f\u001a\u001a\u0012\u0004\u0012\u00020\u0005\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\r\u0012\u0004\u0012\u0002H\u000e0\u00100\u0004H\b\u001a3\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u0002H\r\u0012\u0004\u0012\u00020\u00050\f\"\u0004\b\u0000\u0010\r*\u00020\u00022\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H\r0\u0004H\b\u001aM\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u0002H\r\u0012\u0004\u0012\u0002H\u000e0\f\"\u0004\b\u0000\u0010\r\"\u0004\b\u0001\u0010\u000e*\u00020\u00022\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H\r0\u00042\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H\u000e0\u0004H\b\u001aN\u0010\u0014\u001a\u0002H\u0015\"\u0004\b\u0000\u0010\r\"\u0018\b\u0001\u0010\u0015*\u0012\u0012\u0006\b\u0000\u0012\u0002H\r\u0012\u0006\b\u0000\u0012\u00020\u00050\u0016*\u00020\u00022\u0006\u0010\u0017\u001a\u0002H\u00152\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H\r0\u0004H\b¢\u0006\u0002\u0010\u0018\u001ah\u0010\u0014\u001a\u0002H\u0015\"\u0004\b\u0000\u0010\r\"\u0004\b\u0001\u0010\u000e\"\u0018\b\u0002\u0010\u0015*\u0012\u0012\u0006\b\u0000\u0012\u0002H\r\u0012\u0006\b\u0000\u0012\u0002H\u000e0\u0016*\u00020\u00022\u0006\u0010\u0017\u001a\u0002H\u00152\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H\r0\u00042\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H\u000e0\u0004H\b¢\u0006\u0002\u0010\u0019\u001a`\u0010\u001a\u001a\u0002H\u0015\"\u0004\b\u0000\u0010\r\"\u0004\b\u0001\u0010\u000e\"\u0018\b\u0002\u0010\u0015*\u0012\u0012\u0006\b\u0000\u0012\u0002H\r\u0012\u0006\b\u0000\u0012\u0002H\u000e0\u0016*\u00020\u00022\u0006\u0010\u0017\u001a\u0002H\u00152\u001e\u0010\u000f\u001a\u001a\u0012\u0004\u0012\u00020\u0005\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\r\u0012\u0004\u0012\u0002H\u000e0\u00100\u0004H\b¢\u0006\u0002\u0010\u0018\u001a3\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H\u000e0\f\"\u0004\b\u0000\u0010\u000e*\u00020\u00022\u0012\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H\u000e0\u0004H\b\u001aN\u0010\u001d\u001a\u0002H\u0015\"\u0004\b\u0000\u0010\u000e\"\u0018\b\u0001\u0010\u0015*\u0012\u0012\u0006\b\u0000\u0012\u00020\u0005\u0012\u0006\b\u0000\u0012\u0002H\u000e0\u0016*\u00020\u00022\u0006\u0010\u0017\u001a\u0002H\u00152\u0012\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H\u000e0\u0004H\b¢\u0006\u0002\u0010\u0018\u001a\u001a\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020 0\u001f*\u00020\u00022\u0006\u0010!\u001a\u00020\"H\u0007\u001a4\u0010\u001e\u001a\b\u0012\u0004\u0012\u0002H#0\u001f\"\u0004\b\u0000\u0010#*\u00020\u00022\u0006\u0010!\u001a\u00020\"2\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u0002H#0\u0004H\u0007\u001a\u001a\u0010$\u001a\b\u0012\u0004\u0012\u00020 0\n*\u00020\u00022\u0006\u0010!\u001a\u00020\"H\u0007\u001a4\u0010$\u001a\b\u0012\u0004\u0012\u0002H#0\n\"\u0004\b\u0000\u0010#*\u00020\u00022\u0006\u0010!\u001a\u00020\"2\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u0002H#0\u0004H\u0007\u001a\r\u0010%\u001a\u00020\"*\u00020\u0002H\b\u001a!\u0010%\u001a\u00020\"*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\b\u001a\u0012\u0010&\u001a\u00020\u0002*\u00020\u00022\u0006\u0010'\u001a\u00020\"\u001a\u0012\u0010&\u001a\u00020 *\u00020 2\u0006\u0010'\u001a\u00020\"\u001a\u0012\u0010(\u001a\u00020\u0002*\u00020\u00022\u0006\u0010'\u001a\u00020\"\u001a\u0012\u0010(\u001a\u00020 *\u00020 2\u0006\u0010'\u001a\u00020\"\u001a!\u0010)\u001a\u00020\u0002*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\b\u001a!\u0010)\u001a\u00020 *\u00020 2\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\b\u001a!\u0010*\u001a\u00020\u0002*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\b\u001a!\u0010*\u001a\u00020 *\u00020 2\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\b\u001a\u0015\u0010+\u001a\u00020\u0005*\u00020\u00022\u0006\u0010,\u001a\u00020\"H\b\u001a)\u0010-\u001a\u00020\u0005*\u00020\u00022\u0006\u0010,\u001a\u00020\"2\u0012\u0010.\u001a\u000e\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020\u00050\u0004H\b\u001a\u001c\u0010/\u001a\u0004\u0018\u00010\u0005*\u00020\u00022\u0006\u0010,\u001a\u00020\"H\b¢\u0006\u0002\u00100\u001a!\u00101\u001a\u00020\u0002*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\b\u001a!\u00101\u001a\u00020 *\u00020 2\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\b\u001a6\u00102\u001a\u00020\u0002*\u00020\u00022'\u0010\u0003\u001a#\u0012\u0013\u0012\u00110\"¢\u0006\f\b4\u0012\b\b5\u0012\u0004\b\b(,\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u000103H\b\u001a6\u00102\u001a\u00020 *\u00020 2'\u0010\u0003\u001a#\u0012\u0013\u0012\u00110\"¢\u0006\f\b4\u0012\b\b5\u0012\u0004\b\b(,\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u000103H\b\u001aQ\u00106\u001a\u0002H7\"\f\b\u0000\u00107*\u000608j\u0002`9*\u00020\u00022\u0006\u0010\u0017\u001a\u0002H72'\u0010\u0003\u001a#\u0012\u0013\u0012\u00110\"¢\u0006\f\b4\u0012\b\b5\u0012\u0004\b\b(,\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u000103H\b¢\u0006\u0002\u0010:\u001a!\u0010;\u001a\u00020\u0002*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\b\u001a!\u0010;\u001a\u00020 *\u00020 2\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\b\u001a<\u0010<\u001a\u0002H7\"\f\b\u0000\u00107*\u000608j\u0002`9*\u00020\u00022\u0006\u0010\u0017\u001a\u0002H72\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\b¢\u0006\u0002\u0010=\u001a<\u0010>\u001a\u0002H7\"\f\b\u0000\u00107*\u000608j\u0002`9*\u00020\u00022\u0006\u0010\u0017\u001a\u0002H72\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\b¢\u0006\u0002\u0010=\u001a(\u0010?\u001a\u0004\u0018\u00010\u0005*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\b¢\u0006\u0002\u0010@\u001a(\u0010A\u001a\u0004\u0018\u00010\u0005*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\b¢\u0006\u0002\u0010@\u001a\n\u0010B\u001a\u00020\u0005*\u00020\u0002\u001a!\u0010B\u001a\u00020\u0005*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\b\u001a\u0011\u0010C\u001a\u0004\u0018\u00010\u0005*\u00020\u0002¢\u0006\u0002\u0010D\u001a(\u0010C\u001a\u0004\u0018\u00010\u0005*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\b¢\u0006\u0002\u0010@\u001a3\u0010E\u001a\b\u0012\u0004\u0012\u0002H#0\u001f\"\u0004\b\u0000\u0010#*\u00020\u00022\u0018\u0010\u000f\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u0002H#0\b0\u0004H\b\u001aL\u0010F\u001a\u0002H7\"\u0004\b\u0000\u0010#\"\u0010\b\u0001\u00107*\n\u0012\u0006\b\u0000\u0012\u0002H#0G*\u00020\u00022\u0006\u0010\u0017\u001a\u0002H72\u0018\u0010\u000f\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u0002H#0\b0\u0004H\b¢\u0006\u0002\u0010H\u001aI\u0010I\u001a\u0002H#\"\u0004\b\u0000\u0010#*\u00020\u00022\u0006\u0010J\u001a\u0002H#2'\u0010K\u001a#\u0012\u0013\u0012\u0011H#¢\u0006\f\b4\u0012\b\b5\u0012\u0004\b\b(L\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H#03H\b¢\u0006\u0002\u0010M\u001a^\u0010N\u001a\u0002H#\"\u0004\b\u0000\u0010#*\u00020\u00022\u0006\u0010J\u001a\u0002H#2<\u0010K\u001a8\u0012\u0013\u0012\u00110\"¢\u0006\f\b4\u0012\b\b5\u0012\u0004\b\b(,\u0012\u0013\u0012\u0011H#¢\u0006\f\b4\u0012\b\b5\u0012\u0004\b\b(L\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H#0OH\b¢\u0006\u0002\u0010P\u001aI\u0010Q\u001a\u0002H#\"\u0004\b\u0000\u0010#*\u00020\u00022\u0006\u0010J\u001a\u0002H#2'\u0010K\u001a#\u0012\u0004\u0012\u00020\u0005\u0012\u0013\u0012\u0011H#¢\u0006\f\b4\u0012\b\b5\u0012\u0004\b\b(L\u0012\u0004\u0012\u0002H#03H\b¢\u0006\u0002\u0010M\u001a^\u0010R\u001a\u0002H#\"\u0004\b\u0000\u0010#*\u00020\u00022\u0006\u0010J\u001a\u0002H#2<\u0010K\u001a8\u0012\u0013\u0012\u00110\"¢\u0006\f\b4\u0012\b\b5\u0012\u0004\b\b(,\u0012\u0004\u0012\u00020\u0005\u0012\u0013\u0012\u0011H#¢\u0006\f\b4\u0012\b\b5\u0012\u0004\b\b(L\u0012\u0004\u0012\u0002H#0OH\b¢\u0006\u0002\u0010P\u001a!\u0010S\u001a\u00020T*\u00020\u00022\u0012\u0010U\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020T0\u0004H\b\u001a6\u0010V\u001a\u00020T*\u00020\u00022'\u0010U\u001a#\u0012\u0013\u0012\u00110\"¢\u0006\f\b4\u0012\b\b5\u0012\u0004\b\b(,\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020T03H\b\u001a)\u0010W\u001a\u00020\u0005*\u00020\u00022\u0006\u0010,\u001a\u00020\"2\u0012\u0010.\u001a\u000e\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020\u00050\u0004H\b\u001a\u0019\u0010X\u001a\u0004\u0018\u00010\u0005*\u00020\u00022\u0006\u0010,\u001a\u00020\"¢\u0006\u0002\u00100\u001a9\u0010Y\u001a\u0014\u0012\u0004\u0012\u0002H\r\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u001f0\f\"\u0004\b\u0000\u0010\r*\u00020\u00022\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H\r0\u0004H\b\u001aS\u0010Y\u001a\u0014\u0012\u0004\u0012\u0002H\r\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000e0\u001f0\f\"\u0004\b\u0000\u0010\r\"\u0004\b\u0001\u0010\u000e*\u00020\u00022\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H\r0\u00042\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H\u000e0\u0004H\b\u001aR\u0010Z\u001a\u0002H\u0015\"\u0004\b\u0000\u0010\r\"\u001c\b\u0001\u0010\u0015*\u0016\u0012\u0006\b\u0000\u0012\u0002H\r\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050[0\u0016*\u00020\u00022\u0006\u0010\u0017\u001a\u0002H\u00152\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H\r0\u0004H\b¢\u0006\u0002\u0010\u0018\u001al\u0010Z\u001a\u0002H\u0015\"\u0004\b\u0000\u0010\r\"\u0004\b\u0001\u0010\u000e\"\u001c\b\u0002\u0010\u0015*\u0016\u0012\u0006\b\u0000\u0012\u0002H\r\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000e0[0\u0016*\u00020\u00022\u0006\u0010\u0017\u001a\u0002H\u00152\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H\r0\u00042\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H\u000e0\u0004H\b¢\u0006\u0002\u0010\u0019\u001a5\u0010\\\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H\r0]\"\u0004\b\u0000\u0010\r*\u00020\u00022\u0014\b\u0004\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H\r0\u0004H\b\u001a!\u0010^\u001a\u00020\"*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\b\u001a!\u0010_\u001a\u00020\"*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\b\u001a\n\u0010`\u001a\u00020\u0005*\u00020\u0002\u001a!\u0010`\u001a\u00020\u0005*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\b\u001a\u0011\u0010a\u001a\u0004\u0018\u00010\u0005*\u00020\u0002¢\u0006\u0002\u0010D\u001a(\u0010a\u001a\u0004\u0018\u00010\u0005*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\b¢\u0006\u0002\u0010@\u001a-\u0010b\u001a\b\u0012\u0004\u0012\u0002H#0\u001f\"\u0004\b\u0000\u0010#*\u00020\u00022\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H#0\u0004H\b\u001aB\u0010c\u001a\b\u0012\u0004\u0012\u0002H#0\u001f\"\u0004\b\u0000\u0010#*\u00020\u00022'\u0010\u000f\u001a#\u0012\u0013\u0012\u00110\"¢\u0006\f\b4\u0012\b\b5\u0012\u0004\b\b(,\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H#03H\b\u001aH\u0010d\u001a\b\u0012\u0004\u0012\u0002H#0\u001f\"\b\b\u0000\u0010#*\u00020e*\u00020\u00022)\u0010\u000f\u001a%\u0012\u0013\u0012\u00110\"¢\u0006\f\b4\u0012\b\b5\u0012\u0004\b\b(,\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u0001H#03H\b\u001aa\u0010f\u001a\u0002H7\"\b\b\u0000\u0010#*\u00020e\"\u0010\b\u0001\u00107*\n\u0012\u0006\b\u0000\u0012\u0002H#0G*\u00020\u00022\u0006\u0010\u0017\u001a\u0002H72)\u0010\u000f\u001a%\u0012\u0013\u0012\u00110\"¢\u0006\f\b4\u0012\b\b5\u0012\u0004\b\b(,\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u0001H#03H\b¢\u0006\u0002\u0010g\u001a[\u0010h\u001a\u0002H7\"\u0004\b\u0000\u0010#\"\u0010\b\u0001\u00107*\n\u0012\u0006\b\u0000\u0012\u0002H#0G*\u00020\u00022\u0006\u0010\u0017\u001a\u0002H72'\u0010\u000f\u001a#\u0012\u0013\u0012\u00110\"¢\u0006\f\b4\u0012\b\b5\u0012\u0004\b\b(,\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H#03H\b¢\u0006\u0002\u0010g\u001a3\u0010i\u001a\b\u0012\u0004\u0012\u0002H#0\u001f\"\b\b\u0000\u0010#*\u00020e*\u00020\u00022\u0014\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u0001H#0\u0004H\b\u001aL\u0010j\u001a\u0002H7\"\b\b\u0000\u0010#*\u00020e\"\u0010\b\u0001\u00107*\n\u0012\u0006\b\u0000\u0012\u0002H#0G*\u00020\u00022\u0006\u0010\u0017\u001a\u0002H72\u0014\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u0001H#0\u0004H\b¢\u0006\u0002\u0010H\u001aF\u0010k\u001a\u0002H7\"\u0004\b\u0000\u0010#\"\u0010\b\u0001\u00107*\n\u0012\u0006\b\u0000\u0012\u0002H#0G*\u00020\u00022\u0006\u0010\u0017\u001a\u0002H72\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H#0\u0004H\b¢\u0006\u0002\u0010H\u001a\u0011\u0010l\u001a\u0004\u0018\u00010\u0005*\u00020\u0002¢\u0006\u0002\u0010D\u001a8\u0010m\u001a\u0004\u0018\u00010\u0005\"\u000e\b\u0000\u0010#*\b\u0012\u0004\u0012\u0002H#0n*\u00020\u00022\u0012\u0010o\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H#0\u0004H\b¢\u0006\u0002\u0010@\u001a-\u0010p\u001a\u0004\u0018\u00010\u0005*\u00020\u00022\u001a\u0010q\u001a\u0016\u0012\u0006\b\u0000\u0012\u00020\u00050rj\n\u0012\u0006\b\u0000\u0012\u00020\u0005`s¢\u0006\u0002\u0010t\u001a\u0011\u0010u\u001a\u0004\u0018\u00010\u0005*\u00020\u0002¢\u0006\u0002\u0010D\u001a8\u0010v\u001a\u0004\u0018\u00010\u0005\"\u000e\b\u0000\u0010#*\b\u0012\u0004\u0012\u0002H#0n*\u00020\u00022\u0012\u0010o\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H#0\u0004H\b¢\u0006\u0002\u0010@\u001a-\u0010w\u001a\u0004\u0018\u00010\u0005*\u00020\u00022\u001a\u0010q\u001a\u0016\u0012\u0006\b\u0000\u0012\u00020\u00050rj\n\u0012\u0006\b\u0000\u0012\u00020\u0005`s¢\u0006\u0002\u0010t\u001a\n\u0010x\u001a\u00020\u0001*\u00020\u0002\u001a!\u0010x\u001a\u00020\u0001*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\b\u001a0\u0010y\u001a\u0002Hz\"\b\b\u0000\u0010z*\u00020\u0002*\u0002Hz2\u0012\u0010U\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020T0\u0004H\b¢\u0006\u0002\u0010{\u001a-\u0010|\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u0010*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\b\u001a-\u0010|\u001a\u000e\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020 0\u0010*\u00020 2\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\b\u001a\r\u0010}\u001a\u00020\u0005*\u00020\u0002H\b\u001a\u0014\u0010}\u001a\u00020\u0005*\u00020\u00022\u0006\u0010}\u001a\u00020~H\u0007\u001a6\u0010\u001a\u00020\u0005*\u00020\u00022'\u0010K\u001a#\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b4\u0012\b\b5\u0012\u0004\b\b(L\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u000503H\b\u001aL\u0010\u0001\u001a\u00020\u0005*\u00020\u00022<\u0010K\u001a8\u0012\u0013\u0012\u00110\"¢\u0006\f\b4\u0012\b\b5\u0012\u0004\b\b(,\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b4\u0012\b\b5\u0012\u0004\b\b(L\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050OH\b\u001a7\u0010\u0001\u001a\u00020\u0005*\u00020\u00022'\u0010K\u001a#\u0012\u0004\u0012\u00020\u0005\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b4\u0012\b\b5\u0012\u0004\b\b(L\u0012\u0004\u0012\u00020\u000503H\b\u001aL\u0010\u0001\u001a\u00020\u0005*\u00020\u00022<\u0010K\u001a8\u0012\u0013\u0012\u00110\"¢\u0006\f\b4\u0012\b\b5\u0012\u0004\b\b(,\u0012\u0004\u0012\u00020\u0005\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b4\u0012\b\b5\u0012\u0004\b\b(L\u0012\u0004\u0012\u00020\u00050OH\b\u001a\u000b\u0010\u0001\u001a\u00020\u0002*\u00020\u0002\u001a\u000e\u0010\u0001\u001a\u00020 *\u00020 H\b\u001a\u000b\u0010\u0001\u001a\u00020\u0005*\u00020\u0002\u001a\"\u0010\u0001\u001a\u00020\u0005*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\b\u001a\u0012\u0010\u0001\u001a\u0004\u0018\u00010\u0005*\u00020\u0002¢\u0006\u0002\u0010D\u001a)\u0010\u0001\u001a\u0004\u0018\u00010\u0005*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\b¢\u0006\u0002\u0010@\u001a\u001a\u0010\u0001\u001a\u00020\u0002*\u00020\u00022\r\u0010\u0001\u001a\b\u0012\u0004\u0012\u00020\"0\b\u001a\u0015\u0010\u0001\u001a\u00020\u0002*\u00020\u00022\b\u0010\u0001\u001a\u00030\u0001\u001a\u001d\u0010\u0001\u001a\u00020 *\u00020 2\r\u0010\u0001\u001a\b\u0012\u0004\u0012\u00020\"0\bH\b\u001a\u0015\u0010\u0001\u001a\u00020 *\u00020 2\b\u0010\u0001\u001a\u00030\u0001\u001a\"\u0010\u0001\u001a\u00020\"*\u00020\u00022\u0012\u0010o\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\"0\u0004H\b\u001a$\u0010\u0001\u001a\u00030\u0001*\u00020\u00022\u0013\u0010o\u001a\u000f\u0012\u0004\u0012\u00020\u0005\u0012\u0005\u0012\u00030\u00010\u0004H\b\u001a\u0013\u0010\u0001\u001a\u00020\u0002*\u00020\u00022\u0006\u0010'\u001a\u00020\"\u001a\u0013\u0010\u0001\u001a\u00020 *\u00020 2\u0006\u0010'\u001a\u00020\"\u001a\u0013\u0010\u0001\u001a\u00020\u0002*\u00020\u00022\u0006\u0010'\u001a\u00020\"\u001a\u0013\u0010\u0001\u001a\u00020 *\u00020 2\u0006\u0010'\u001a\u00020\"\u001a\"\u0010\u0001\u001a\u00020\u0002*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\b\u001a\"\u0010\u0001\u001a\u00020 *\u00020 2\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\b\u001a\"\u0010\u0001\u001a\u00020\u0002*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\b\u001a\"\u0010\u0001\u001a\u00020 *\u00020 2\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\b\u001a+\u0010\u0001\u001a\u0002H7\"\u0010\b\u0000\u00107*\n\u0012\u0006\b\u0000\u0012\u00020\u00050G*\u00020\u00022\u0006\u0010\u0017\u001a\u0002H7¢\u0006\u0003\u0010\u0001\u001a\u001d\u0010\u0001\u001a\u0014\u0012\u0004\u0012\u00020\u00050\u0001j\t\u0012\u0004\u0012\u00020\u0005`\u0001*\u00020\u0002\u001a\u0011\u0010\u0001\u001a\b\u0012\u0004\u0012\u00020\u00050\u001f*\u00020\u0002\u001a\u0011\u0010\u0001\u001a\b\u0012\u0004\u0012\u00020\u00050[*\u00020\u0002\u001a\u0012\u0010\u0001\u001a\t\u0012\u0004\u0012\u00020\u00050\u0001*\u00020\u0002\u001a1\u0010\u0001\u001a\b\u0012\u0004\u0012\u00020 0\u001f*\u00020\u00022\u0006\u0010!\u001a\u00020\"2\t\b\u0002\u0010\u0001\u001a\u00020\"2\t\b\u0002\u0010\u0001\u001a\u00020\u0001H\u0007\u001aK\u0010\u0001\u001a\b\u0012\u0004\u0012\u0002H#0\u001f\"\u0004\b\u0000\u0010#*\u00020\u00022\u0006\u0010!\u001a\u00020\"2\t\b\u0002\u0010\u0001\u001a\u00020\"2\t\b\u0002\u0010\u0001\u001a\u00020\u00012\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u0002H#0\u0004H\u0007\u001a1\u0010\u0001\u001a\b\u0012\u0004\u0012\u00020 0\n*\u00020\u00022\u0006\u0010!\u001a\u00020\"2\t\b\u0002\u0010\u0001\u001a\u00020\"2\t\b\u0002\u0010\u0001\u001a\u00020\u0001H\u0007\u001aK\u0010\u0001\u001a\b\u0012\u0004\u0012\u0002H#0\n\"\u0004\b\u0000\u0010#*\u00020\u00022\u0006\u0010!\u001a\u00020\"2\t\b\u0002\u0010\u0001\u001a\u00020\"2\t\b\u0002\u0010\u0001\u001a\u00020\u00012\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u0002H#0\u0004H\u0007\u001a\u0018\u0010\u0001\u001a\u000f\u0012\u000b\u0012\t\u0012\u0004\u0012\u00020\u00050\u00010\b*\u00020\u0002\u001a)\u0010\u0001\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u00100\u001f*\u00020\u00022\u0007\u0010 \u0001\u001a\u00020\u0002H\u0004\u001a]\u0010\u0001\u001a\b\u0012\u0004\u0012\u0002H\u000e0\u001f\"\u0004\b\u0000\u0010\u000e*\u00020\u00022\u0007\u0010 \u0001\u001a\u00020\u000228\u0010\u000f\u001a4\u0012\u0014\u0012\u00120\u0005¢\u0006\r\b4\u0012\t\b5\u0012\u0005\b\b(¡\u0001\u0012\u0014\u0012\u00120\u0005¢\u0006\r\b4\u0012\t\b5\u0012\u0005\b\b(¢\u0001\u0012\u0004\u0012\u0002H\u000e03H\b\u001a\u001f\u0010£\u0001\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u00100\u001f*\u00020\u0002H\u0007\u001aT\u0010£\u0001\u001a\b\u0012\u0004\u0012\u0002H#0\u001f\"\u0004\b\u0000\u0010#*\u00020\u000228\u0010\u000f\u001a4\u0012\u0014\u0012\u00120\u0005¢\u0006\r\b4\u0012\t\b5\u0012\u0005\b\b(¡\u0001\u0012\u0014\u0012\u00120\u0005¢\u0006\r\b4\u0012\t\b5\u0012\u0005\b\b(¢\u0001\u0012\u0004\u0012\u0002H#03H\b¨\u0006¤\u0001"}, d2 = {"all", "", "", "predicate", "Lkotlin/Function1;", "", "any", "asIterable", "", "asSequence", "Lkotlin/sequences/Sequence;", "associate", "", "K", "V", "transform", "Lkotlin/Pair;", "associateBy", "keySelector", "valueTransform", "associateByTo", "M", "", "destination", "(Ljava/lang/CharSequence;Ljava/util/Map;Lkotlin/jvm/functions/Function1;)Ljava/util/Map;", "(Ljava/lang/CharSequence;Ljava/util/Map;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)Ljava/util/Map;", "associateTo", "associateWith", "valueSelector", "associateWithTo", "chunked", "", "", "size", "", "R", "chunkedSequence", "count", "drop", "n", "dropLast", "dropLastWhile", "dropWhile", "elementAt", "index", "elementAtOrElse", "defaultValue", "elementAtOrNull", "(Ljava/lang/CharSequence;I)Ljava/lang/Character;", "filter", "filterIndexed", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "filterIndexedTo", "C", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "(Ljava/lang/CharSequence;Ljava/lang/Appendable;Lkotlin/jvm/functions/Function2;)Ljava/lang/Appendable;", "filterNot", "filterNotTo", "(Ljava/lang/CharSequence;Ljava/lang/Appendable;Lkotlin/jvm/functions/Function1;)Ljava/lang/Appendable;", "filterTo", "find", "(Ljava/lang/CharSequence;Lkotlin/jvm/functions/Function1;)Ljava/lang/Character;", "findLast", "first", "firstOrNull", "(Ljava/lang/CharSequence;)Ljava/lang/Character;", "flatMap", "flatMapTo", "", "(Ljava/lang/CharSequence;Ljava/util/Collection;Lkotlin/jvm/functions/Function1;)Ljava/util/Collection;", "fold", "initial", "operation", "acc", "(Ljava/lang/CharSequence;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "foldIndexed", "Lkotlin/Function3;", "(Ljava/lang/CharSequence;Ljava/lang/Object;Lkotlin/jvm/functions/Function3;)Ljava/lang/Object;", "foldRight", "foldRightIndexed", "forEach", "", "action", "forEachIndexed", "getOrElse", "getOrNull", "groupBy", "groupByTo", "", "groupingBy", "Lkotlin/collections/Grouping;", "indexOfFirst", "indexOfLast", "last", "lastOrNull", "map", "mapIndexed", "mapIndexedNotNull", "", "mapIndexedNotNullTo", "(Ljava/lang/CharSequence;Ljava/util/Collection;Lkotlin/jvm/functions/Function2;)Ljava/util/Collection;", "mapIndexedTo", "mapNotNull", "mapNotNullTo", "mapTo", "max", "maxBy", "", "selector", "maxWith", "comparator", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "(Ljava/lang/CharSequence;Ljava/util/Comparator;)Ljava/lang/Character;", "min", "minBy", "minWith", "none", "onEach", "S", "(Ljava/lang/CharSequence;Lkotlin/jvm/functions/Function1;)Ljava/lang/CharSequence;", "partition", "random", "Lkotlin/random/Random;", "reduce", "reduceIndexed", "reduceRight", "reduceRightIndexed", "reversed", "single", "singleOrNull", "slice", "indices", "Lkotlin/ranges/IntRange;", "sumBy", "sumByDouble", "", "take", "takeLast", "takeLastWhile", "takeWhile", "toCollection", "(Ljava/lang/CharSequence;Ljava/util/Collection;)Ljava/util/Collection;", "toHashSet", "Ljava/util/HashSet;", "Lkotlin/collections/HashSet;", "toList", "toMutableList", "toSet", "", "windowed", "step", "partialWindows", "windowedSequence", "withIndex", "Lkotlin/collections/IndexedValue;", "zip", "other", "a", "b", "zipWithNext", "kotlin-stdlib"}, k = 5, mv = {1, 1, 13}, xi = 1, xs = "kotlin/text/StringsKt")
/* compiled from: _Strings.kt */
class StringsKt___StringsKt extends StringsKt___StringsJvmKt {
    @InlineOnly
    private static final char elementAt(@NotNull CharSequence charSequence, int i) {
        return charSequence.charAt(i);
    }

    @InlineOnly
    private static final char elementAtOrElse(@NotNull CharSequence charSequence, int i, Function1<? super Integer, Character> function1) {
        return (i < 0 || i > StringsKt__StringsKt.getLastIndex(charSequence)) ? ((Character) function1.invoke(Integer.valueOf(i))).charValue() : charSequence.charAt(i);
    }

    @InlineOnly
    private static final Character elementAtOrNull(@NotNull CharSequence charSequence, int i) {
        return getOrNull(charSequence, i);
    }

    public static final char first(@NotNull CharSequence charSequence) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        if ((charSequence.length() == 0 ? 1 : null) == null) {
            return charSequence.charAt(0);
        }
        throw ((Throwable) new NoSuchElementException("Char sequence is empty."));
    }

    public static final char first(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "predicate");
        for (int i = 0; i < charSequence.length(); i++) {
            char charAt = charSequence.charAt(i);
            if (((Boolean) function1.invoke(Character.valueOf(charAt))).booleanValue()) {
                return charAt;
            }
        }
        throw ((Throwable) new NoSuchElementException("Char sequence contains no character matching the predicate."));
    }

    @Nullable
    public static final Character firstOrNull(@NotNull CharSequence charSequence) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        return (charSequence.length() == 0 ? 1 : null) != null ? null : Character.valueOf(charSequence.charAt(0));
    }

    @Nullable
    public static final Character firstOrNull(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "predicate");
        for (int i = 0; i < charSequence.length(); i++) {
            char charAt = charSequence.charAt(i);
            if (((Boolean) function1.invoke(Character.valueOf(charAt))).booleanValue()) {
                return Character.valueOf(charAt);
            }
        }
        return null;
    }

    @InlineOnly
    private static final char getOrElse(@NotNull CharSequence charSequence, int i, Function1<? super Integer, Character> function1) {
        return (i < 0 || i > StringsKt__StringsKt.getLastIndex(charSequence)) ? ((Character) function1.invoke(Integer.valueOf(i))).charValue() : charSequence.charAt(i);
    }

    @Nullable
    public static final Character getOrNull(@NotNull CharSequence charSequence, int i) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        return (i < 0 || i > StringsKt__StringsKt.getLastIndex(charSequence)) ? null : Character.valueOf(charSequence.charAt(i));
    }

    public static final int indexOfFirst(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "predicate");
        int length = charSequence.length();
        for (int i = 0; i < length; i++) {
            if (((Boolean) function1.invoke(Character.valueOf(charSequence.charAt(i)))).booleanValue()) {
                return i;
            }
        }
        return -1;
    }

    public static final int indexOfLast(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "predicate");
        for (int length = charSequence.length() - 1; length >= 0; length--) {
            if (((Boolean) function1.invoke(Character.valueOf(charSequence.charAt(length)))).booleanValue()) {
                return length;
            }
        }
        return -1;
    }

    public static final char last(@NotNull CharSequence charSequence) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        if ((charSequence.length() == 0 ? 1 : null) == null) {
            return charSequence.charAt(StringsKt__StringsKt.getLastIndex(charSequence));
        }
        throw ((Throwable) new NoSuchElementException("Char sequence is empty."));
    }

    public static final char last(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, Boolean> function1) {
        char charAt;
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "predicate");
        int length = charSequence.length();
        do {
            length--;
            if (length >= 0) {
                charAt = charSequence.charAt(length);
            } else {
                throw ((Throwable) new NoSuchElementException("Char sequence contains no character matching the predicate."));
            }
        } while (!((Boolean) function1.invoke(Character.valueOf(charAt))).booleanValue());
        return charAt;
    }

    @Nullable
    public static final Character lastOrNull(@NotNull CharSequence charSequence) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        return (charSequence.length() == 0 ? 1 : null) != null ? null : Character.valueOf(charSequence.charAt(charSequence.length() - 1));
    }

    @Nullable
    public static final Character lastOrNull(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, Boolean> function1) {
        char charAt;
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "predicate");
        int length = charSequence.length();
        do {
            length--;
            if (length < 0) {
                return null;
            }
            charAt = charSequence.charAt(length);
        } while (!((Boolean) function1.invoke(Character.valueOf(charAt))).booleanValue());
        return Character.valueOf(charAt);
    }

    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final char random(@NotNull CharSequence charSequence) {
        return random(charSequence, Random.Default);
    }

    @SinceKotlin(version = "1.3")
    public static final char random(@NotNull CharSequence charSequence, @NotNull Random random) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        Intrinsics.checkParameterIsNotNull(random, "random");
        if ((charSequence.length() == 0 ? 1 : null) == null) {
            return charSequence.charAt(random.nextInt(charSequence.length()));
        }
        throw ((Throwable) new NoSuchElementException("Char sequence is empty."));
    }

    public static final char single(@NotNull CharSequence charSequence) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        switch (charSequence.length()) {
            case 0:
                throw ((Throwable) new NoSuchElementException("Char sequence is empty."));
            case 1:
                return charSequence.charAt(0);
            default:
                throw ((Throwable) new IllegalArgumentException("Char sequence has more than one element."));
        }
    }

    public static final char single(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "predicate");
        Character ch = (Character) null;
        Object obj = null;
        for (int i = 0; i < charSequence.length(); i++) {
            char charAt = charSequence.charAt(i);
            if (((Boolean) function1.invoke(Character.valueOf(charAt))).booleanValue()) {
                if (obj == null) {
                    ch = Character.valueOf(charAt);
                    obj = 1;
                } else {
                    throw ((Throwable) new IllegalArgumentException("Char sequence contains more than one matching element."));
                }
            }
        }
        if (obj == null) {
            throw ((Throwable) new NoSuchElementException("Char sequence contains no character matching the predicate."));
        } else if (ch != null) {
            return ch.charValue();
        } else {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.Char");
        }
    }

    @Nullable
    public static final Character singleOrNull(@NotNull CharSequence charSequence) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        return charSequence.length() == 1 ? Character.valueOf(charSequence.charAt(0)) : null;
    }

    @Nullable
    public static final Character singleOrNull(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "predicate");
        Character ch = null;
        Object obj = null;
        for (int i = 0; i < charSequence.length(); i++) {
            char charAt = charSequence.charAt(i);
            if (((Boolean) function1.invoke(Character.valueOf(charAt))).booleanValue()) {
                if (obj != null) {
                    return null;
                }
                ch = Character.valueOf(charAt);
                obj = 1;
            }
        }
        if (obj == null) {
            return null;
        }
        return ch;
    }

    @NotNull
    public static final CharSequence drop(@NotNull CharSequence charSequence, int i) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        if ((i >= 0 ? 1 : null) != null) {
            return charSequence.subSequence(RangesKt___RangesKt.coerceAtMost(i, charSequence.length()), charSequence.length());
        }
        charSequence = new StringBuilder();
        charSequence.append("Requested character count ");
        charSequence.append(i);
        charSequence.append(" is less than zero.");
        throw ((Throwable) new IllegalArgumentException(charSequence.toString().toString()));
    }

    @NotNull
    public static final String drop(@NotNull String str, int i) {
        Intrinsics.checkParameterIsNotNull(str, "receiver$0");
        if ((i >= 0 ? 1 : null) != null) {
            str = str.substring(RangesKt___RangesKt.coerceAtMost(i, str.length()));
            Intrinsics.checkExpressionValueIsNotNull(str, "(this as java.lang.String).substring(startIndex)");
            return str;
        }
        str = new StringBuilder();
        str.append("Requested character count ");
        str.append(i);
        str.append(" is less than zero.");
        throw ((Throwable) new IllegalArgumentException(str.toString().toString()));
    }

    @NotNull
    public static final CharSequence dropLast(@NotNull CharSequence charSequence, int i) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        if ((i >= 0 ? 1 : null) != null) {
            return take(charSequence, RangesKt___RangesKt.coerceAtLeast(charSequence.length() - i, 0));
        }
        charSequence = new StringBuilder();
        charSequence.append("Requested character count ");
        charSequence.append(i);
        charSequence.append(" is less than zero.");
        throw ((Throwable) new IllegalArgumentException(charSequence.toString().toString()));
    }

    @NotNull
    public static final String dropLast(@NotNull String str, int i) {
        Intrinsics.checkParameterIsNotNull(str, "receiver$0");
        if ((i >= 0 ? 1 : null) != null) {
            return take(str, RangesKt___RangesKt.coerceAtLeast(str.length() - i, 0));
        }
        str = new StringBuilder();
        str.append("Requested character count ");
        str.append(i);
        str.append(" is less than zero.");
        throw ((Throwable) new IllegalArgumentException(str.toString().toString()));
    }

    @NotNull
    public static final CharSequence dropLastWhile(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "predicate");
        for (int lastIndex = StringsKt__StringsKt.getLastIndex(charSequence); lastIndex >= 0; lastIndex--) {
            if (!((Boolean) function1.invoke(Character.valueOf(charSequence.charAt(lastIndex)))).booleanValue()) {
                return charSequence.subSequence(null, lastIndex + 1);
            }
        }
        return "";
    }

    @NotNull
    public static final String dropLastWhile(@NotNull String str, @NotNull Function1<? super Character, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(str, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "predicate");
        int lastIndex = StringsKt__StringsKt.getLastIndex(str);
        while (lastIndex >= 0) {
            if (((Boolean) function1.invoke(Character.valueOf(str.charAt(lastIndex)))).booleanValue()) {
                lastIndex--;
            } else {
                str = str.substring(null, lastIndex + 1);
                Intrinsics.checkExpressionValueIsNotNull(str, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                return str;
            }
        }
        return "";
    }

    @NotNull
    public static final CharSequence dropWhile(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "predicate");
        int length = charSequence.length();
        for (int i = 0; i < length; i++) {
            if (!((Boolean) function1.invoke(Character.valueOf(charSequence.charAt(i)))).booleanValue()) {
                return charSequence.subSequence(i, charSequence.length());
            }
        }
        return "";
    }

    @NotNull
    public static final String dropWhile(@NotNull String str, @NotNull Function1<? super Character, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(str, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "predicate");
        int length = str.length();
        int i = 0;
        while (i < length) {
            if (((Boolean) function1.invoke(Character.valueOf(str.charAt(i)))).booleanValue()) {
                i++;
            } else {
                str = str.substring(i);
                Intrinsics.checkExpressionValueIsNotNull(str, "(this as java.lang.String).substring(startIndex)");
                return str;
            }
        }
        return "";
    }

    @NotNull
    public static final CharSequence filter(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "predicate");
        Appendable stringBuilder = new StringBuilder();
        int length = charSequence.length();
        for (int i = 0; i < length; i++) {
            char charAt = charSequence.charAt(i);
            if (((Boolean) function1.invoke(Character.valueOf(charAt))).booleanValue()) {
                stringBuilder.append(charAt);
            }
        }
        return (CharSequence) stringBuilder;
    }

    @NotNull
    public static final String filter(@NotNull String str, @NotNull Function1<? super Character, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(str, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "predicate");
        CharSequence charSequence = str;
        Appendable stringBuilder = new StringBuilder();
        int length = charSequence.length();
        for (int i = 0; i < length; i++) {
            char charAt = charSequence.charAt(i);
            if (((Boolean) function1.invoke(Character.valueOf(charAt))).booleanValue()) {
                stringBuilder.append(charAt);
            }
        }
        str = ((StringBuilder) stringBuilder).toString();
        Intrinsics.checkExpressionValueIsNotNull(str, "filterTo(StringBuilder(), predicate).toString()");
        return str;
    }

    @NotNull
    public static final CharSequence filterIndexed(@NotNull CharSequence charSequence, @NotNull Function2<? super Integer, ? super Character, Boolean> function2) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function2, "predicate");
        Appendable stringBuilder = new StringBuilder();
        int i = 0;
        int i2 = 0;
        while (i < charSequence.length()) {
            char charAt = charSequence.charAt(i);
            int i3 = i2 + 1;
            if (((Boolean) function2.invoke(Integer.valueOf(i2), Character.valueOf(charAt))).booleanValue()) {
                stringBuilder.append(charAt);
            }
            i++;
            i2 = i3;
        }
        return (CharSequence) stringBuilder;
    }

    @NotNull
    public static final String filterIndexed(@NotNull String str, @NotNull Function2<? super Integer, ? super Character, Boolean> function2) {
        Intrinsics.checkParameterIsNotNull(str, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function2, "predicate");
        CharSequence charSequence = str;
        Appendable stringBuilder = new StringBuilder();
        int i = 0;
        int i2 = 0;
        while (i < charSequence.length()) {
            char charAt = charSequence.charAt(i);
            int i3 = i2 + 1;
            if (((Boolean) function2.invoke(Integer.valueOf(i2), Character.valueOf(charAt))).booleanValue()) {
                stringBuilder.append(charAt);
            }
            i++;
            i2 = i3;
        }
        str = ((StringBuilder) stringBuilder).toString();
        Intrinsics.checkExpressionValueIsNotNull(str, "filterIndexedTo(StringBu…(), predicate).toString()");
        return str;
    }

    @NotNull
    public static final CharSequence filterNot(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "predicate");
        Appendable stringBuilder = new StringBuilder();
        for (int i = 0; i < charSequence.length(); i++) {
            char charAt = charSequence.charAt(i);
            if (!((Boolean) function1.invoke(Character.valueOf(charAt))).booleanValue()) {
                stringBuilder.append(charAt);
            }
        }
        return (CharSequence) stringBuilder;
    }

    @NotNull
    public static final String filterNot(@NotNull String str, @NotNull Function1<? super Character, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(str, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "predicate");
        CharSequence charSequence = str;
        Appendable stringBuilder = new StringBuilder();
        for (int i = 0; i < charSequence.length(); i++) {
            char charAt = charSequence.charAt(i);
            if (!((Boolean) function1.invoke(Character.valueOf(charAt))).booleanValue()) {
                stringBuilder.append(charAt);
            }
        }
        str = ((StringBuilder) stringBuilder).toString();
        Intrinsics.checkExpressionValueIsNotNull(str, "filterNotTo(StringBuilder(), predicate).toString()");
        return str;
    }

    @NotNull
    public static final <C extends Appendable> C filterNotTo(@NotNull CharSequence charSequence, @NotNull C c, @NotNull Function1<? super Character, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        Intrinsics.checkParameterIsNotNull(c, "destination");
        Intrinsics.checkParameterIsNotNull(function1, "predicate");
        for (int i = 0; i < charSequence.length(); i++) {
            char charAt = charSequence.charAt(i);
            if (!((Boolean) function1.invoke(Character.valueOf(charAt))).booleanValue()) {
                c.append(charAt);
            }
        }
        return c;
    }

    @NotNull
    public static final <C extends Appendable> C filterTo(@NotNull CharSequence charSequence, @NotNull C c, @NotNull Function1<? super Character, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        Intrinsics.checkParameterIsNotNull(c, "destination");
        Intrinsics.checkParameterIsNotNull(function1, "predicate");
        int length = charSequence.length();
        for (int i = 0; i < length; i++) {
            char charAt = charSequence.charAt(i);
            if (((Boolean) function1.invoke(Character.valueOf(charAt))).booleanValue()) {
                c.append(charAt);
            }
        }
        return c;
    }

    @NotNull
    public static final CharSequence slice(@NotNull CharSequence charSequence, @NotNull IntRange intRange) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        Intrinsics.checkParameterIsNotNull(intRange, "indices");
        if (intRange.isEmpty()) {
            return "";
        }
        return StringsKt__StringsKt.subSequence(charSequence, intRange);
    }

    @NotNull
    public static final String slice(@NotNull String str, @NotNull IntRange intRange) {
        Intrinsics.checkParameterIsNotNull(str, "receiver$0");
        Intrinsics.checkParameterIsNotNull(intRange, "indices");
        if (intRange.isEmpty()) {
            return "";
        }
        return StringsKt__StringsKt.substring(str, intRange);
    }

    @NotNull
    public static final CharSequence slice(@NotNull CharSequence charSequence, @NotNull Iterable<Integer> iterable) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        Intrinsics.checkParameterIsNotNull(iterable, "indices");
        int collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(iterable, 10);
        if (collectionSizeOrDefault == 0) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder(collectionSizeOrDefault);
        for (Number intValue : iterable) {
            stringBuilder.append(charSequence.charAt(intValue.intValue()));
        }
        return stringBuilder;
    }

    @InlineOnly
    private static final String slice(@NotNull String str, Iterable<Integer> iterable) {
        if (str != null) {
            return slice((CharSequence) str, (Iterable) iterable).toString();
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
    }

    @NotNull
    public static final CharSequence take(@NotNull CharSequence charSequence, int i) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        if ((i >= 0 ? 1 : null) != null) {
            return charSequence.subSequence(0, RangesKt___RangesKt.coerceAtMost(i, charSequence.length()));
        }
        charSequence = new StringBuilder();
        charSequence.append("Requested character count ");
        charSequence.append(i);
        charSequence.append(" is less than zero.");
        throw ((Throwable) new IllegalArgumentException(charSequence.toString().toString()));
    }

    @NotNull
    public static final String take(@NotNull String str, int i) {
        Intrinsics.checkParameterIsNotNull(str, "receiver$0");
        if ((i >= 0 ? 1 : null) != null) {
            str = str.substring(0, RangesKt___RangesKt.coerceAtMost(i, str.length()));
            Intrinsics.checkExpressionValueIsNotNull(str, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            return str;
        }
        str = new StringBuilder();
        str.append("Requested character count ");
        str.append(i);
        str.append(" is less than zero.");
        throw ((Throwable) new IllegalArgumentException(str.toString().toString()));
    }

    @NotNull
    public static final CharSequence takeLast(@NotNull CharSequence charSequence, int i) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        if ((i >= 0 ? 1 : null) != null) {
            int length = charSequence.length();
            return charSequence.subSequence(length - RangesKt___RangesKt.coerceAtMost(i, length), length);
        }
        charSequence = new StringBuilder();
        charSequence.append("Requested character count ");
        charSequence.append(i);
        charSequence.append(" is less than zero.");
        throw ((Throwable) new IllegalArgumentException(charSequence.toString().toString()));
    }

    @NotNull
    public static final String takeLast(@NotNull String str, int i) {
        Intrinsics.checkParameterIsNotNull(str, "receiver$0");
        if ((i >= 0 ? 1 : null) != null) {
            int length = str.length();
            str = str.substring(length - RangesKt___RangesKt.coerceAtMost(i, length));
            Intrinsics.checkExpressionValueIsNotNull(str, "(this as java.lang.String).substring(startIndex)");
            return str;
        }
        str = new StringBuilder();
        str.append("Requested character count ");
        str.append(i);
        str.append(" is less than zero.");
        throw ((Throwable) new IllegalArgumentException(str.toString().toString()));
    }

    @NotNull
    public static final CharSequence takeLastWhile(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "predicate");
        for (int lastIndex = StringsKt__StringsKt.getLastIndex(charSequence); lastIndex >= 0; lastIndex--) {
            if (!((Boolean) function1.invoke(Character.valueOf(charSequence.charAt(lastIndex)))).booleanValue()) {
                return charSequence.subSequence(lastIndex + 1, charSequence.length());
            }
        }
        return charSequence.subSequence(null, charSequence.length());
    }

    @NotNull
    public static final String takeLastWhile(@NotNull String str, @NotNull Function1<? super Character, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(str, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "predicate");
        int lastIndex = StringsKt__StringsKt.getLastIndex(str);
        while (lastIndex >= 0) {
            if (((Boolean) function1.invoke(Character.valueOf(str.charAt(lastIndex)))).booleanValue()) {
                lastIndex--;
            } else {
                str = str.substring(lastIndex + 1);
                Intrinsics.checkExpressionValueIsNotNull(str, "(this as java.lang.String).substring(startIndex)");
                return str;
            }
        }
        return str;
    }

    @NotNull
    public static final CharSequence takeWhile(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "predicate");
        int length = charSequence.length();
        for (int i = 0; i < length; i++) {
            if (!((Boolean) function1.invoke(Character.valueOf(charSequence.charAt(i)))).booleanValue()) {
                return charSequence.subSequence(0, i);
            }
        }
        return charSequence.subSequence(0, charSequence.length());
    }

    @NotNull
    public static final String takeWhile(@NotNull String str, @NotNull Function1<? super Character, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(str, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "predicate");
        int length = str.length();
        int i = 0;
        while (i < length) {
            if (((Boolean) function1.invoke(Character.valueOf(str.charAt(i)))).booleanValue()) {
                i++;
            } else {
                str = str.substring(0, i);
                Intrinsics.checkExpressionValueIsNotNull(str, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                return str;
            }
        }
        return str;
    }

    @NotNull
    public static final CharSequence reversed(@NotNull CharSequence charSequence) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        charSequence = new StringBuilder(charSequence).reverse();
        Intrinsics.checkExpressionValueIsNotNull(charSequence, "StringBuilder(this).reverse()");
        return charSequence;
    }

    @InlineOnly
    private static final String reversed(@NotNull String str) {
        if (str != null) {
            return reversed((CharSequence) str).toString();
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
    }

    @NotNull
    public static final <K, V> Map<K, V> associate(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, ? extends Pair<? extends K, ? extends V>> function1) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "transform");
        Map<K, V> linkedHashMap = new LinkedHashMap(RangesKt___RangesKt.coerceAtLeast(MapsKt__MapsKt.mapCapacity(charSequence.length()), 16));
        for (int i = 0; i < charSequence.length(); i++) {
            Pair pair = (Pair) function1.invoke(Character.valueOf(charSequence.charAt(i)));
            linkedHashMap.put(pair.getFirst(), pair.getSecond());
        }
        return linkedHashMap;
    }

    @NotNull
    public static final <K> Map<K, Character> associateBy(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, ? extends K> function1) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "keySelector");
        Map<K, Character> linkedHashMap = new LinkedHashMap(RangesKt___RangesKt.coerceAtLeast(MapsKt__MapsKt.mapCapacity(charSequence.length()), 16));
        for (int i = 0; i < charSequence.length(); i++) {
            char charAt = charSequence.charAt(i);
            linkedHashMap.put(function1.invoke(Character.valueOf(charAt)), Character.valueOf(charAt));
        }
        return linkedHashMap;
    }

    @NotNull
    public static final <K, V> Map<K, V> associateBy(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, ? extends K> function1, @NotNull Function1<? super Character, ? extends V> function12) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "keySelector");
        Intrinsics.checkParameterIsNotNull(function12, "valueTransform");
        Map<K, V> linkedHashMap = new LinkedHashMap(RangesKt___RangesKt.coerceAtLeast(MapsKt__MapsKt.mapCapacity(charSequence.length()), 16));
        for (int i = 0; i < charSequence.length(); i++) {
            char charAt = charSequence.charAt(i);
            linkedHashMap.put(function1.invoke(Character.valueOf(charAt)), function12.invoke(Character.valueOf(charAt)));
        }
        return linkedHashMap;
    }

    @NotNull
    public static final <K, M extends Map<? super K, ? super Character>> M associateByTo(@NotNull CharSequence charSequence, @NotNull M m, @NotNull Function1<? super Character, ? extends K> function1) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        Intrinsics.checkParameterIsNotNull(m, "destination");
        Intrinsics.checkParameterIsNotNull(function1, "keySelector");
        for (int i = 0; i < charSequence.length(); i++) {
            char charAt = charSequence.charAt(i);
            m.put(function1.invoke(Character.valueOf(charAt)), Character.valueOf(charAt));
        }
        return m;
    }

    @NotNull
    public static final <K, V, M extends Map<? super K, ? super V>> M associateByTo(@NotNull CharSequence charSequence, @NotNull M m, @NotNull Function1<? super Character, ? extends K> function1, @NotNull Function1<? super Character, ? extends V> function12) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        Intrinsics.checkParameterIsNotNull(m, "destination");
        Intrinsics.checkParameterIsNotNull(function1, "keySelector");
        Intrinsics.checkParameterIsNotNull(function12, "valueTransform");
        for (int i = 0; i < charSequence.length(); i++) {
            char charAt = charSequence.charAt(i);
            m.put(function1.invoke(Character.valueOf(charAt)), function12.invoke(Character.valueOf(charAt)));
        }
        return m;
    }

    @NotNull
    public static final <K, V, M extends Map<? super K, ? super V>> M associateTo(@NotNull CharSequence charSequence, @NotNull M m, @NotNull Function1<? super Character, ? extends Pair<? extends K, ? extends V>> function1) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        Intrinsics.checkParameterIsNotNull(m, "destination");
        Intrinsics.checkParameterIsNotNull(function1, "transform");
        for (int i = 0; i < charSequence.length(); i++) {
            Pair pair = (Pair) function1.invoke(Character.valueOf(charSequence.charAt(i)));
            m.put(pair.getFirst(), pair.getSecond());
        }
        return m;
    }

    @NotNull
    @SinceKotlin(version = "1.3")
    public static final <V> Map<Character, V> associateWith(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, ? extends V> function1) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "valueSelector");
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt___RangesKt.coerceAtLeast(MapsKt__MapsKt.mapCapacity(charSequence.length()), 16));
        for (int i = 0; i < charSequence.length(); i++) {
            char charAt = charSequence.charAt(i);
            linkedHashMap.put(Character.valueOf(charAt), function1.invoke(Character.valueOf(charAt)));
        }
        return linkedHashMap;
    }

    @NotNull
    @SinceKotlin(version = "1.3")
    public static final <V, M extends Map<? super Character, ? super V>> M associateWithTo(@NotNull CharSequence charSequence, @NotNull M m, @NotNull Function1<? super Character, ? extends V> function1) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        Intrinsics.checkParameterIsNotNull(m, "destination");
        Intrinsics.checkParameterIsNotNull(function1, "valueSelector");
        for (int i = 0; i < charSequence.length(); i++) {
            char charAt = charSequence.charAt(i);
            m.put(Character.valueOf(charAt), function1.invoke(Character.valueOf(charAt)));
        }
        return m;
    }

    @NotNull
    public static final <C extends Collection<? super Character>> C toCollection(@NotNull CharSequence charSequence, @NotNull C c) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        Intrinsics.checkParameterIsNotNull(c, "destination");
        for (int i = 0; i < charSequence.length(); i++) {
            c.add(Character.valueOf(charSequence.charAt(i)));
        }
        return c;
    }

    @NotNull
    public static final HashSet<Character> toHashSet(@NotNull CharSequence charSequence) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        return (HashSet) toCollection(charSequence, new HashSet(MapsKt__MapsKt.mapCapacity(charSequence.length())));
    }

    @NotNull
    public static final List<Character> toList(@NotNull CharSequence charSequence) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        switch (charSequence.length()) {
            case 0:
                return CollectionsKt__CollectionsKt.emptyList();
            case 1:
                return CollectionsKt__CollectionsJVMKt.listOf(Character.valueOf(charSequence.charAt(0)));
            default:
                return toMutableList(charSequence);
        }
    }

    @NotNull
    public static final List<Character> toMutableList(@NotNull CharSequence charSequence) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        return (List) toCollection(charSequence, new ArrayList(charSequence.length()));
    }

    @NotNull
    public static final Set<Character> toSet(@NotNull CharSequence charSequence) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        switch (charSequence.length()) {
            case 0:
                return SetsKt__SetsKt.emptySet();
            case 1:
                return SetsKt__SetsJVMKt.setOf(Character.valueOf(charSequence.charAt(0)));
            default:
                return (Set) toCollection(charSequence, new LinkedHashSet(MapsKt__MapsKt.mapCapacity(charSequence.length())));
        }
    }

    @NotNull
    public static final <R> List<R> flatMap(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, ? extends Iterable<? extends R>> function1) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "transform");
        Collection arrayList = new ArrayList();
        for (int i = 0; i < charSequence.length(); i++) {
            CollectionsKt__MutableCollectionsKt.addAll(arrayList, (Iterable) function1.invoke(Character.valueOf(charSequence.charAt(i))));
        }
        return (List) arrayList;
    }

    @NotNull
    public static final <R, C extends Collection<? super R>> C flatMapTo(@NotNull CharSequence charSequence, @NotNull C c, @NotNull Function1<? super Character, ? extends Iterable<? extends R>> function1) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        Intrinsics.checkParameterIsNotNull(c, "destination");
        Intrinsics.checkParameterIsNotNull(function1, "transform");
        for (int i = 0; i < charSequence.length(); i++) {
            CollectionsKt__MutableCollectionsKt.addAll((Collection) c, (Iterable) function1.invoke(Character.valueOf(charSequence.charAt(i))));
        }
        return c;
    }

    @NotNull
    public static final <K> Map<K, List<Character>> groupBy(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, ? extends K> function1) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "keySelector");
        Map<K, List<Character>> linkedHashMap = new LinkedHashMap();
        for (int i = 0; i < charSequence.length(); i++) {
            char charAt = charSequence.charAt(i);
            Object invoke = function1.invoke(Character.valueOf(charAt));
            ArrayList arrayList = linkedHashMap.get(invoke);
            if (arrayList == null) {
                arrayList = new ArrayList();
                linkedHashMap.put(invoke, arrayList);
            }
            arrayList.add(Character.valueOf(charAt));
        }
        return linkedHashMap;
    }

    @NotNull
    public static final <K, V> Map<K, List<V>> groupBy(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, ? extends K> function1, @NotNull Function1<? super Character, ? extends V> function12) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "keySelector");
        Intrinsics.checkParameterIsNotNull(function12, "valueTransform");
        Map<K, List<V>> linkedHashMap = new LinkedHashMap();
        for (int i = 0; i < charSequence.length(); i++) {
            char charAt = charSequence.charAt(i);
            Object invoke = function1.invoke(Character.valueOf(charAt));
            ArrayList arrayList = linkedHashMap.get(invoke);
            if (arrayList == null) {
                arrayList = new ArrayList();
                linkedHashMap.put(invoke, arrayList);
            }
            arrayList.add(function12.invoke(Character.valueOf(charAt)));
        }
        return linkedHashMap;
    }

    @NotNull
    public static final <K, M extends Map<? super K, List<Character>>> M groupByTo(@NotNull CharSequence charSequence, @NotNull M m, @NotNull Function1<? super Character, ? extends K> function1) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        Intrinsics.checkParameterIsNotNull(m, "destination");
        Intrinsics.checkParameterIsNotNull(function1, "keySelector");
        for (int i = 0; i < charSequence.length(); i++) {
            char charAt = charSequence.charAt(i);
            Object invoke = function1.invoke(Character.valueOf(charAt));
            ArrayList arrayList = m.get(invoke);
            if (arrayList == null) {
                arrayList = new ArrayList();
                m.put(invoke, arrayList);
            }
            arrayList.add(Character.valueOf(charAt));
        }
        return m;
    }

    @NotNull
    public static final <K, V, M extends Map<? super K, List<V>>> M groupByTo(@NotNull CharSequence charSequence, @NotNull M m, @NotNull Function1<? super Character, ? extends K> function1, @NotNull Function1<? super Character, ? extends V> function12) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        Intrinsics.checkParameterIsNotNull(m, "destination");
        Intrinsics.checkParameterIsNotNull(function1, "keySelector");
        Intrinsics.checkParameterIsNotNull(function12, "valueTransform");
        for (int i = 0; i < charSequence.length(); i++) {
            char charAt = charSequence.charAt(i);
            Object invoke = function1.invoke(Character.valueOf(charAt));
            ArrayList arrayList = m.get(invoke);
            if (arrayList == null) {
                arrayList = new ArrayList();
                m.put(invoke, arrayList);
            }
            arrayList.add(function12.invoke(Character.valueOf(charAt)));
        }
        return m;
    }

    @NotNull
    @SinceKotlin(version = "1.1")
    public static final <K> Grouping<Character, K> groupingBy(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, ? extends K> function1) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "keySelector");
        return new StringsKt___StringsKt$groupingBy$1(charSequence, function1);
    }

    @NotNull
    public static final <R> List<R> map(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, ? extends R> function1) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "transform");
        Collection arrayList = new ArrayList(charSequence.length());
        for (int i = 0; i < charSequence.length(); i++) {
            arrayList.add(function1.invoke(Character.valueOf(charSequence.charAt(i))));
        }
        return (List) arrayList;
    }

    @NotNull
    public static final <R> List<R> mapIndexed(@NotNull CharSequence charSequence, @NotNull Function2<? super Integer, ? super Character, ? extends R> function2) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function2, "transform");
        Collection arrayList = new ArrayList(charSequence.length());
        int i = 0;
        for (int i2 = 0; i2 < charSequence.length(); i2++) {
            char charAt = charSequence.charAt(i2);
            Integer valueOf = Integer.valueOf(i);
            i++;
            arrayList.add(function2.invoke(valueOf, Character.valueOf(charAt)));
        }
        return (List) arrayList;
    }

    @NotNull
    public static final <R> List<R> mapIndexedNotNull(@NotNull CharSequence charSequence, @NotNull Function2<? super Integer, ? super Character, ? extends R> function2) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function2, "transform");
        Collection arrayList = new ArrayList();
        int i = 0;
        int i2 = 0;
        while (i < charSequence.length()) {
            int i3 = i2 + 1;
            Object invoke = function2.invoke(Integer.valueOf(i2), Character.valueOf(charSequence.charAt(i)));
            if (invoke != null) {
                arrayList.add(invoke);
            }
            i++;
            i2 = i3;
        }
        return (List) arrayList;
    }

    @NotNull
    public static final <R, C extends Collection<? super R>> C mapIndexedTo(@NotNull CharSequence charSequence, @NotNull C c, @NotNull Function2<? super Integer, ? super Character, ? extends R> function2) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        Intrinsics.checkParameterIsNotNull(c, "destination");
        Intrinsics.checkParameterIsNotNull(function2, "transform");
        int i = 0;
        for (int i2 = 0; i2 < charSequence.length(); i2++) {
            char charAt = charSequence.charAt(i2);
            Integer valueOf = Integer.valueOf(i);
            i++;
            c.add(function2.invoke(valueOf, Character.valueOf(charAt)));
        }
        return c;
    }

    @NotNull
    public static final <R> List<R> mapNotNull(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, ? extends R> function1) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "transform");
        Collection arrayList = new ArrayList();
        for (int i = 0; i < charSequence.length(); i++) {
            Object invoke = function1.invoke(Character.valueOf(charSequence.charAt(i)));
            if (invoke != null) {
                arrayList.add(invoke);
            }
        }
        return (List) arrayList;
    }

    @NotNull
    public static final <R, C extends Collection<? super R>> C mapTo(@NotNull CharSequence charSequence, @NotNull C c, @NotNull Function1<? super Character, ? extends R> function1) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        Intrinsics.checkParameterIsNotNull(c, "destination");
        Intrinsics.checkParameterIsNotNull(function1, "transform");
        for (int i = 0; i < charSequence.length(); i++) {
            c.add(function1.invoke(Character.valueOf(charSequence.charAt(i))));
        }
        return c;
    }

    @NotNull
    public static final Iterable<IndexedValue<Character>> withIndex(@NotNull CharSequence charSequence) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        return new IndexingIterable(new StringsKt___StringsKt$withIndex$1(charSequence));
    }

    public static final boolean all(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "predicate");
        for (int i = 0; i < charSequence.length(); i++) {
            if (!((Boolean) function1.invoke(Character.valueOf(charSequence.charAt(i)))).booleanValue()) {
                return false;
            }
        }
        return true;
    }

    public static final boolean any(@NotNull CharSequence charSequence) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        return (charSequence.length() == null ? true : null) ^ 1;
    }

    public static final boolean any(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "predicate");
        for (int i = 0; i < charSequence.length(); i++) {
            if (((Boolean) function1.invoke(Character.valueOf(charSequence.charAt(i)))).booleanValue()) {
                return true;
            }
        }
        return false;
    }

    @InlineOnly
    private static final int count(@NotNull CharSequence charSequence) {
        return charSequence.length();
    }

    public static final int count(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "predicate");
        int i = 0;
        for (int i2 = 0; i2 < charSequence.length(); i2++) {
            if (((Boolean) function1.invoke(Character.valueOf(charSequence.charAt(i2)))).booleanValue()) {
                i++;
            }
        }
        return i;
    }

    public static final <R> R fold(@NotNull CharSequence charSequence, R r, @NotNull Function2<? super R, ? super Character, ? extends R> function2) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function2, "operation");
        for (int i = 0; i < charSequence.length(); i++) {
            r = function2.invoke(r, Character.valueOf(charSequence.charAt(i)));
        }
        return r;
    }

    public static final <R> R foldIndexed(@NotNull CharSequence charSequence, R r, @NotNull Function3<? super Integer, ? super R, ? super Character, ? extends R> function3) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function3, "operation");
        R r2 = r;
        r = null;
        for (int i = 0; i < charSequence.length(); i++) {
            char charAt = charSequence.charAt(i);
            Integer valueOf = Integer.valueOf(r);
            r++;
            r2 = function3.invoke(valueOf, r2, Character.valueOf(charAt));
        }
        return r2;
    }

    public static final <R> R foldRight(@NotNull CharSequence charSequence, R r, @NotNull Function2<? super Character, ? super R, ? extends R> function2) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function2, "operation");
        int lastIndex = StringsKt__StringsKt.getLastIndex(charSequence);
        while (lastIndex >= 0) {
            int i = lastIndex - 1;
            r = function2.invoke(Character.valueOf(charSequence.charAt(lastIndex)), r);
            lastIndex = i;
        }
        return r;
    }

    public static final <R> R foldRightIndexed(@NotNull CharSequence charSequence, R r, @NotNull Function3<? super Integer, ? super Character, ? super R, ? extends R> function3) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function3, "operation");
        for (int lastIndex = StringsKt__StringsKt.getLastIndex(charSequence); lastIndex >= 0; lastIndex--) {
            r = function3.invoke(Integer.valueOf(lastIndex), Character.valueOf(charSequence.charAt(lastIndex)), r);
        }
        return r;
    }

    public static final void forEach(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "action");
        for (int i = 0; i < charSequence.length(); i++) {
            function1.invoke(Character.valueOf(charSequence.charAt(i)));
        }
    }

    public static final void forEachIndexed(@NotNull CharSequence charSequence, @NotNull Function2<? super Integer, ? super Character, Unit> function2) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function2, "action");
        int i = 0;
        for (int i2 = 0; i2 < charSequence.length(); i2++) {
            char charAt = charSequence.charAt(i2);
            Integer valueOf = Integer.valueOf(i);
            i++;
            function2.invoke(valueOf, Character.valueOf(charAt));
        }
    }

    @Nullable
    public static final Character max(@NotNull CharSequence charSequence) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        int i = 1;
        if ((charSequence.length() == 0 ? 1 : null) != null) {
            return null;
        }
        char charAt = charSequence.charAt(0);
        int lastIndex = StringsKt__StringsKt.getLastIndex(charSequence);
        if (1 <= lastIndex) {
            while (true) {
                char charAt2 = charSequence.charAt(i);
                if (charAt < charAt2) {
                    charAt = charAt2;
                }
                if (i == lastIndex) {
                    break;
                }
                i++;
            }
        }
        return Character.valueOf(charAt);
    }

    @Nullable
    public static final <R extends Comparable<? super R>> Character maxBy(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, ? extends R> function1) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "selector");
        int i = 1;
        if ((charSequence.length() == 0 ? 1 : null) != null) {
            return null;
        }
        char charAt = charSequence.charAt(0);
        Comparable comparable = (Comparable) function1.invoke(Character.valueOf(charAt));
        int lastIndex = StringsKt__StringsKt.getLastIndex(charSequence);
        if (1 <= lastIndex) {
            while (true) {
                char charAt2 = charSequence.charAt(i);
                Comparable comparable2 = (Comparable) function1.invoke(Character.valueOf(charAt2));
                if (comparable.compareTo(comparable2) < 0) {
                    charAt = charAt2;
                    comparable = comparable2;
                }
                if (i == lastIndex) {
                    break;
                }
                i++;
            }
        }
        return Character.valueOf(charAt);
    }

    @Nullable
    public static final Character maxWith(@NotNull CharSequence charSequence, @NotNull Comparator<? super Character> comparator) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        Intrinsics.checkParameterIsNotNull(comparator, "comparator");
        int i = 1;
        if ((charSequence.length() == 0 ? 1 : null) != null) {
            return null;
        }
        char charAt = charSequence.charAt(0);
        int lastIndex = StringsKt__StringsKt.getLastIndex(charSequence);
        if (1 <= lastIndex) {
            while (true) {
                char charAt2 = charSequence.charAt(i);
                if (comparator.compare(Character.valueOf(charAt), Character.valueOf(charAt2)) < 0) {
                    charAt = charAt2;
                }
                if (i == lastIndex) {
                    break;
                }
                i++;
            }
        }
        return Character.valueOf(charAt);
    }

    @Nullable
    public static final Character min(@NotNull CharSequence charSequence) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        int i = 1;
        if ((charSequence.length() == 0 ? 1 : null) != null) {
            return null;
        }
        char charAt = charSequence.charAt(0);
        int lastIndex = StringsKt__StringsKt.getLastIndex(charSequence);
        if (1 <= lastIndex) {
            while (true) {
                char charAt2 = charSequence.charAt(i);
                if (charAt > charAt2) {
                    charAt = charAt2;
                }
                if (i == lastIndex) {
                    break;
                }
                i++;
            }
        }
        return Character.valueOf(charAt);
    }

    @Nullable
    public static final <R extends Comparable<? super R>> Character minBy(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, ? extends R> function1) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "selector");
        int i = 1;
        if ((charSequence.length() == 0 ? 1 : null) != null) {
            return null;
        }
        char charAt = charSequence.charAt(0);
        Comparable comparable = (Comparable) function1.invoke(Character.valueOf(charAt));
        int lastIndex = StringsKt__StringsKt.getLastIndex(charSequence);
        if (1 <= lastIndex) {
            while (true) {
                char charAt2 = charSequence.charAt(i);
                Comparable comparable2 = (Comparable) function1.invoke(Character.valueOf(charAt2));
                if (comparable.compareTo(comparable2) > 0) {
                    charAt = charAt2;
                    comparable = comparable2;
                }
                if (i == lastIndex) {
                    break;
                }
                i++;
            }
        }
        return Character.valueOf(charAt);
    }

    @Nullable
    public static final Character minWith(@NotNull CharSequence charSequence, @NotNull Comparator<? super Character> comparator) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        Intrinsics.checkParameterIsNotNull(comparator, "comparator");
        int i = 1;
        if ((charSequence.length() == 0 ? 1 : null) != null) {
            return null;
        }
        char charAt = charSequence.charAt(0);
        int lastIndex = StringsKt__StringsKt.getLastIndex(charSequence);
        if (1 <= lastIndex) {
            while (true) {
                char charAt2 = charSequence.charAt(i);
                if (comparator.compare(Character.valueOf(charAt), Character.valueOf(charAt2)) > 0) {
                    charAt = charAt2;
                }
                if (i == lastIndex) {
                    break;
                }
                i++;
            }
        }
        return Character.valueOf(charAt);
    }

    public static final boolean none(@NotNull CharSequence charSequence) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        return charSequence.length() == null ? true : null;
    }

    public static final boolean none(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "predicate");
        for (int i = 0; i < charSequence.length(); i++) {
            if (((Boolean) function1.invoke(Character.valueOf(charSequence.charAt(i)))).booleanValue()) {
                return false;
            }
        }
        return true;
    }

    @NotNull
    @SinceKotlin(version = "1.1")
    public static final <S extends CharSequence> S onEach(@NotNull S s, @NotNull Function1<? super Character, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(s, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "action");
        for (int i = 0; i < s.length(); i++) {
            function1.invoke(Character.valueOf(s.charAt(i)));
        }
        return s;
    }

    public static final char reduce(@NotNull CharSequence charSequence, @NotNull Function2<? super Character, ? super Character, Character> function2) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function2, "operation");
        int i = 1;
        if ((charSequence.length() == 0 ? 1 : null) == null) {
            char charAt = charSequence.charAt(0);
            int lastIndex = StringsKt__StringsKt.getLastIndex(charSequence);
            if (1 <= lastIndex) {
                while (true) {
                    charAt = ((Character) function2.invoke(Character.valueOf(charAt), Character.valueOf(charSequence.charAt(i)))).charValue();
                    if (i == lastIndex) {
                        break;
                    }
                    i++;
                }
            }
            return charAt;
        }
        throw ((Throwable) new UnsupportedOperationException("Empty char sequence can't be reduced."));
    }

    public static final char reduceIndexed(@NotNull CharSequence charSequence, @NotNull Function3<? super Integer, ? super Character, ? super Character, Character> function3) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function3, "operation");
        int i = 1;
        if ((charSequence.length() == 0 ? 1 : null) == null) {
            char charAt = charSequence.charAt(0);
            int lastIndex = StringsKt__StringsKt.getLastIndex(charSequence);
            if (1 <= lastIndex) {
                while (true) {
                    charAt = ((Character) function3.invoke(Integer.valueOf(i), Character.valueOf(charAt), Character.valueOf(charSequence.charAt(i)))).charValue();
                    if (i == lastIndex) {
                        break;
                    }
                    i++;
                }
            }
            return charAt;
        }
        throw ((Throwable) new UnsupportedOperationException("Empty char sequence can't be reduced."));
    }

    public static final char reduceRight(@NotNull CharSequence charSequence, @NotNull Function2<? super Character, ? super Character, Character> function2) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function2, "operation");
        int lastIndex = StringsKt__StringsKt.getLastIndex(charSequence);
        if (lastIndex >= 0) {
            int i = lastIndex - 1;
            char charAt = charSequence.charAt(lastIndex);
            while (i >= 0) {
                int i2 = i - 1;
                charAt = ((Character) function2.invoke(Character.valueOf(charSequence.charAt(i)), Character.valueOf(charAt))).charValue();
                i = i2;
            }
            return charAt;
        }
        throw ((Throwable) new UnsupportedOperationException("Empty char sequence can't be reduced."));
    }

    public static final char reduceRightIndexed(@NotNull CharSequence charSequence, @NotNull Function3<? super Integer, ? super Character, ? super Character, Character> function3) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function3, "operation");
        int lastIndex = StringsKt__StringsKt.getLastIndex(charSequence);
        if (lastIndex >= 0) {
            char charAt = charSequence.charAt(lastIndex);
            for (int i = lastIndex - 1; i >= 0; i--) {
                charAt = ((Character) function3.invoke(Integer.valueOf(i), Character.valueOf(charSequence.charAt(i)), Character.valueOf(charAt))).charValue();
            }
            return charAt;
        }
        throw ((Throwable) new UnsupportedOperationException("Empty char sequence can't be reduced."));
    }

    public static final int sumBy(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, Integer> function1) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "selector");
        int i = 0;
        for (int i2 = 0; i2 < charSequence.length(); i2++) {
            i += ((Number) function1.invoke(Character.valueOf(charSequence.charAt(i2)))).intValue();
        }
        return i;
    }

    public static final double sumByDouble(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, Double> function1) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "selector");
        double d = 0.0d;
        for (int i = 0; i < charSequence.length(); i++) {
            d += ((Number) function1.invoke(Character.valueOf(charSequence.charAt(i)))).doubleValue();
        }
        return d;
    }

    @NotNull
    @SinceKotlin(version = "1.2")
    public static final List<String> chunked(@NotNull CharSequence charSequence, int i) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        return windowed(charSequence, i, i, true);
    }

    @NotNull
    @SinceKotlin(version = "1.2")
    public static final <R> List<R> chunked(@NotNull CharSequence charSequence, int i, @NotNull Function1<? super CharSequence, ? extends R> function1) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "transform");
        return windowed(charSequence, i, i, true, function1);
    }

    @NotNull
    @SinceKotlin(version = "1.2")
    public static final Sequence<String> chunkedSequence(@NotNull CharSequence charSequence, int i) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        return chunkedSequence(charSequence, i, StringsKt___StringsKt$chunkedSequence$1.INSTANCE);
    }

    @NotNull
    @SinceKotlin(version = "1.2")
    public static final <R> Sequence<R> chunkedSequence(@NotNull CharSequence charSequence, int i, @NotNull Function1<? super CharSequence, ? extends R> function1) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "transform");
        return windowedSequence(charSequence, i, i, true, function1);
    }

    @NotNull
    public static final Pair<CharSequence, CharSequence> partition(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "predicate");
        StringBuilder stringBuilder = new StringBuilder();
        StringBuilder stringBuilder2 = new StringBuilder();
        for (int i = 0; i < charSequence.length(); i++) {
            char charAt = charSequence.charAt(i);
            if (((Boolean) function1.invoke(Character.valueOf(charAt))).booleanValue()) {
                stringBuilder.append(charAt);
            } else {
                stringBuilder2.append(charAt);
            }
        }
        return new Pair(stringBuilder, stringBuilder2);
    }

    @NotNull
    public static final Pair<String, String> partition(@NotNull String str, @NotNull Function1<? super Character, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(str, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "predicate");
        StringBuilder stringBuilder = new StringBuilder();
        StringBuilder stringBuilder2 = new StringBuilder();
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (((Boolean) function1.invoke(Character.valueOf(charAt))).booleanValue()) {
                stringBuilder.append(charAt);
            } else {
                stringBuilder2.append(charAt);
            }
        }
        return new Pair(stringBuilder.toString(), stringBuilder2.toString());
    }

    @NotNull
    @SinceKotlin(version = "1.2")
    public static /* synthetic */ List windowed$default(CharSequence charSequence, int i, int i2, boolean z, int i3, Object obj) {
        if ((i3 & 2) != null) {
            i2 = 1;
        }
        if ((i3 & 4) != 0) {
            z = false;
        }
        return windowed(charSequence, i, i2, z);
    }

    @NotNull
    @SinceKotlin(version = "1.2")
    public static final List<String> windowed(@NotNull CharSequence charSequence, int i, int i2, boolean z) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        return windowed(charSequence, i, i2, z, StringsKt___StringsKt$windowed$1.INSTANCE);
    }

    @NotNull
    @SinceKotlin(version = "1.2")
    public static /* synthetic */ List windowed$default(CharSequence charSequence, int i, int i2, boolean z, Function1 function1, int i3, Object obj) {
        if ((i3 & 2) != null) {
            i2 = 1;
        }
        if ((i3 & 4) != 0) {
            z = false;
        }
        return windowed(charSequence, i, i2, z, function1);
    }

    @NotNull
    @SinceKotlin(version = "1.2")
    public static final <R> List<R> windowed(@NotNull CharSequence charSequence, int i, int i2, boolean z, @NotNull Function1<? super CharSequence, ? extends R> function1) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "transform");
        SlidingWindowKt.checkWindowSizeStep(i, i2);
        int length = charSequence.length();
        ArrayList arrayList = new ArrayList(((length + i2) - 1) / i2);
        int i3 = 0;
        while (i3 < length) {
            int i4 = i3 + i;
            if (i4 > length) {
                if (!z) {
                    break;
                }
                i4 = length;
            }
            arrayList.add(function1.invoke(charSequence.subSequence(i3, i4)));
            i3 += i2;
        }
        return arrayList;
    }

    @NotNull
    @SinceKotlin(version = "1.2")
    public static /* synthetic */ Sequence windowedSequence$default(CharSequence charSequence, int i, int i2, boolean z, int i3, Object obj) {
        if ((i3 & 2) != null) {
            i2 = 1;
        }
        if ((i3 & 4) != 0) {
            z = false;
        }
        return windowedSequence(charSequence, i, i2, z);
    }

    @NotNull
    @SinceKotlin(version = "1.2")
    public static final Sequence<String> windowedSequence(@NotNull CharSequence charSequence, int i, int i2, boolean z) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        return windowedSequence(charSequence, i, i2, z, StringsKt___StringsKt$windowedSequence$1.INSTANCE);
    }

    @NotNull
    @SinceKotlin(version = "1.2")
    public static /* synthetic */ Sequence windowedSequence$default(CharSequence charSequence, int i, int i2, boolean z, Function1 function1, int i3, Object obj) {
        if ((i3 & 2) != null) {
            i2 = 1;
        }
        if ((i3 & 4) != 0) {
            z = false;
        }
        return windowedSequence(charSequence, i, i2, z, function1);
    }

    @NotNull
    @SinceKotlin(version = "1.2")
    public static final <R> Sequence<R> windowedSequence(@NotNull CharSequence charSequence, int i, int i2, boolean z, @NotNull Function1<? super CharSequence, ? extends R> function1) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "transform");
        SlidingWindowKt.checkWindowSizeStep(i, i2);
        return SequencesKt___SequencesKt.map(CollectionsKt___CollectionsKt.asSequence(RangesKt___RangesKt.step((IntProgression) (z ? StringsKt__StringsKt.getIndices(charSequence) : RangesKt___RangesKt.until((int) false, (charSequence.length() - i) + 1)), i2)), (Function1) new StringsKt___StringsKt$windowedSequence$2(charSequence, function1, i));
    }

    @NotNull
    public static final <V> List<V> zip(@NotNull CharSequence charSequence, @NotNull CharSequence charSequence2, @NotNull Function2<? super Character, ? super Character, ? extends V> function2) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        Intrinsics.checkParameterIsNotNull(charSequence2, "other");
        Intrinsics.checkParameterIsNotNull(function2, "transform");
        int min = Math.min(charSequence.length(), charSequence2.length());
        ArrayList arrayList = new ArrayList(min);
        for (int i = 0; i < min; i++) {
            arrayList.add(function2.invoke(Character.valueOf(charSequence.charAt(i)), Character.valueOf(charSequence2.charAt(i))));
        }
        return arrayList;
    }

    @NotNull
    @SinceKotlin(version = "1.2")
    public static final <R> List<R> zipWithNext(@NotNull CharSequence charSequence, @NotNull Function2<? super Character, ? super Character, ? extends R> function2) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function2, "transform");
        int length = charSequence.length() - 1;
        if (length < 1) {
            return CollectionsKt__CollectionsKt.emptyList();
        }
        ArrayList arrayList = new ArrayList(length);
        int i = 0;
        while (i < length) {
            i++;
            arrayList.add(function2.invoke(Character.valueOf(charSequence.charAt(i)), Character.valueOf(charSequence.charAt(i))));
        }
        return arrayList;
    }

    @NotNull
    public static final Iterable<Character> asIterable(@NotNull CharSequence charSequence) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        if (charSequence instanceof String) {
            if ((charSequence.length() == 0 ? 1 : null) != null) {
                return CollectionsKt__CollectionsKt.emptyList();
            }
        }
        return new StringsKt___StringsKt$asIterable$$inlined$Iterable$1(charSequence);
    }

    @NotNull
    public static final Sequence<Character> asSequence(@NotNull CharSequence charSequence) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        if (charSequence instanceof String) {
            if ((charSequence.length() == 0 ? 1 : null) != null) {
                return SequencesKt__SequencesKt.emptySequence();
            }
        }
        return new StringsKt___StringsKt$asSequence$$inlined$Sequence$1(charSequence);
    }

    @InlineOnly
    private static final Character find(@NotNull CharSequence charSequence, Function1<? super Character, Boolean> function1) {
        for (int i = 0; i < charSequence.length(); i++) {
            char charAt = charSequence.charAt(i);
            if (((Boolean) function1.invoke(Character.valueOf(charAt))).booleanValue()) {
                return Character.valueOf(charAt);
            }
        }
        return null;
    }

    @InlineOnly
    private static final Character findLast(@NotNull CharSequence charSequence, Function1<? super Character, Boolean> function1) {
        char charAt;
        int length = charSequence.length();
        do {
            length--;
            if (length < 0) {
                return null;
            }
            charAt = charSequence.charAt(length);
        } while (!((Boolean) function1.invoke(Character.valueOf(charAt))).booleanValue());
        return Character.valueOf(charAt);
    }

    @NotNull
    public static final <C extends Appendable> C filterIndexedTo(@NotNull CharSequence charSequence, @NotNull C c, @NotNull Function2<? super Integer, ? super Character, Boolean> function2) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        Intrinsics.checkParameterIsNotNull(c, "destination");
        Intrinsics.checkParameterIsNotNull(function2, "predicate");
        int i = 0;
        int i2 = 0;
        while (i < charSequence.length()) {
            char charAt = charSequence.charAt(i);
            int i3 = i2 + 1;
            if (((Boolean) function2.invoke(Integer.valueOf(i2), Character.valueOf(charAt))).booleanValue()) {
                c.append(charAt);
            }
            i++;
            i2 = i3;
        }
        return c;
    }

    @NotNull
    public static final <R, C extends Collection<? super R>> C mapIndexedNotNullTo(@NotNull CharSequence charSequence, @NotNull C c, @NotNull Function2<? super Integer, ? super Character, ? extends R> function2) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        Intrinsics.checkParameterIsNotNull(c, "destination");
        Intrinsics.checkParameterIsNotNull(function2, "transform");
        int i = 0;
        int i2 = 0;
        while (i < charSequence.length()) {
            int i3 = i2 + 1;
            Object invoke = function2.invoke(Integer.valueOf(i2), Character.valueOf(charSequence.charAt(i)));
            if (invoke != null) {
                c.add(invoke);
            }
            i++;
            i2 = i3;
        }
        return c;
    }

    @NotNull
    public static final <R, C extends Collection<? super R>> C mapNotNullTo(@NotNull CharSequence charSequence, @NotNull C c, @NotNull Function1<? super Character, ? extends R> function1) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        Intrinsics.checkParameterIsNotNull(c, "destination");
        Intrinsics.checkParameterIsNotNull(function1, "transform");
        for (int i = 0; i < charSequence.length(); i++) {
            Object invoke = function1.invoke(Character.valueOf(charSequence.charAt(i)));
            if (invoke != null) {
                c.add(invoke);
            }
        }
        return c;
    }

    @NotNull
    public static final List<Pair<Character, Character>> zip(@NotNull CharSequence charSequence, @NotNull CharSequence charSequence2) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        Intrinsics.checkParameterIsNotNull(charSequence2, "other");
        int min = Math.min(charSequence.length(), charSequence2.length());
        ArrayList arrayList = new ArrayList(min);
        for (int i = 0; i < min; i++) {
            arrayList.add(TuplesKt.to(Character.valueOf(charSequence.charAt(i)), Character.valueOf(charSequence2.charAt(i))));
        }
        return arrayList;
    }

    @NotNull
    @SinceKotlin(version = "1.2")
    public static final List<Pair<Character, Character>> zipWithNext(@NotNull CharSequence charSequence) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        int length = charSequence.length() - 1;
        if (length < 1) {
            return CollectionsKt__CollectionsKt.emptyList();
        }
        ArrayList arrayList = new ArrayList(length);
        int i = 0;
        while (i < length) {
            char charAt = charSequence.charAt(i);
            i++;
            arrayList.add(TuplesKt.to(Character.valueOf(charAt), Character.valueOf(charSequence.charAt(i))));
        }
        return arrayList;
    }
}
