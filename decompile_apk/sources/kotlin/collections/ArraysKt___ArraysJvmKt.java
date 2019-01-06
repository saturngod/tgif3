package kotlin.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.SinceKotlin;
import kotlin.TypeCastException;
import kotlin.internal.InlineOnly;
import kotlin.internal.PlatformImplementationsKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0001\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u000b\n\u0002\u0010\u0018\n\u0002\u0010\u0005\n\u0002\u0010\u0012\n\u0002\u0010\f\n\u0002\u0010\u0019\n\u0002\u0010\u0006\n\u0002\u0010\u0013\n\u0002\u0010\u0007\n\u0002\u0010\u0014\n\u0002\u0010\b\n\u0002\u0010\u0015\n\u0002\u0010\t\n\u0002\u0010\u0016\n\u0002\u0010\n\n\u0002\u0010\u0017\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000e\n\u0002\b\u0014\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u001f\n\u0002\b\u0005\n\u0002\u0010\u001e\n\u0002\b\u0004\n\u0002\u0010\u000f\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\f\u001a#\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0003¢\u0006\u0002\u0010\u0004\u001a\u0010\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00050\u0001*\u00020\u0006\u001a\u0010\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00070\u0001*\u00020\b\u001a\u0010\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\t0\u0001*\u00020\n\u001a\u0010\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0001*\u00020\f\u001a\u0010\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\r0\u0001*\u00020\u000e\u001a\u0010\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0001*\u00020\u0010\u001a\u0010\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00110\u0001*\u00020\u0012\u001a\u0010\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00130\u0001*\u00020\u0014\u001aU\u0010\u0015\u001a\u00020\u000f\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u00032\u0006\u0010\u0016\u001a\u0002H\u00022\u001a\u0010\u0017\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u00020\u0018j\n\u0012\u0006\b\u0000\u0012\u0002H\u0002`\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f¢\u0006\u0002\u0010\u001c\u001a9\u0010\u0015\u001a\u00020\u000f\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u00032\u0006\u0010\u0016\u001a\u0002H\u00022\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f¢\u0006\u0002\u0010\u001d\u001a&\u0010\u0015\u001a\u00020\u000f*\u00020\b2\u0006\u0010\u0016\u001a\u00020\u00072\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f\u001a&\u0010\u0015\u001a\u00020\u000f*\u00020\n2\u0006\u0010\u0016\u001a\u00020\t2\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f\u001a&\u0010\u0015\u001a\u00020\u000f*\u00020\f2\u0006\u0010\u0016\u001a\u00020\u000b2\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f\u001a&\u0010\u0015\u001a\u00020\u000f*\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\r2\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f\u001a&\u0010\u0015\u001a\u00020\u000f*\u00020\u00102\u0006\u0010\u0016\u001a\u00020\u000f2\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f\u001a&\u0010\u0015\u001a\u00020\u000f*\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u00112\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f\u001a&\u0010\u0015\u001a\u00020\u000f*\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u00132\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f\u001a2\u0010\u001e\u001a\u00020\u0005\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u00032\u000e\u0010\u001f\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0003H\f¢\u0006\u0004\b \u0010!\u001a\"\u0010\"\u001a\u00020\u000f\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0003H\b¢\u0006\u0004\b#\u0010$\u001a\"\u0010%\u001a\u00020&\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0003H\b¢\u0006\u0004\b'\u0010(\u001a0\u0010)\u001a\u00020\u0005\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u00032\u000e\u0010\u001f\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0003H\f¢\u0006\u0002\u0010!\u001a\u0015\u0010)\u001a\u00020\u0005*\u00020\u00062\u0006\u0010\u001f\u001a\u00020\u0006H\f\u001a\u0015\u0010)\u001a\u00020\u0005*\u00020\b2\u0006\u0010\u001f\u001a\u00020\bH\f\u001a\u0015\u0010)\u001a\u00020\u0005*\u00020\n2\u0006\u0010\u001f\u001a\u00020\nH\f\u001a\u0015\u0010)\u001a\u00020\u0005*\u00020\f2\u0006\u0010\u001f\u001a\u00020\fH\f\u001a\u0015\u0010)\u001a\u00020\u0005*\u00020\u000e2\u0006\u0010\u001f\u001a\u00020\u000eH\f\u001a\u0015\u0010)\u001a\u00020\u0005*\u00020\u00102\u0006\u0010\u001f\u001a\u00020\u0010H\f\u001a\u0015\u0010)\u001a\u00020\u0005*\u00020\u00122\u0006\u0010\u001f\u001a\u00020\u0012H\f\u001a\u0015\u0010)\u001a\u00020\u0005*\u00020\u00142\u0006\u0010\u001f\u001a\u00020\u0014H\f\u001a \u0010*\u001a\u00020\u000f\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0003H\b¢\u0006\u0002\u0010$\u001a\r\u0010*\u001a\u00020\u000f*\u00020\u0006H\b\u001a\r\u0010*\u001a\u00020\u000f*\u00020\bH\b\u001a\r\u0010*\u001a\u00020\u000f*\u00020\nH\b\u001a\r\u0010*\u001a\u00020\u000f*\u00020\fH\b\u001a\r\u0010*\u001a\u00020\u000f*\u00020\u000eH\b\u001a\r\u0010*\u001a\u00020\u000f*\u00020\u0010H\b\u001a\r\u0010*\u001a\u00020\u000f*\u00020\u0012H\b\u001a\r\u0010*\u001a\u00020\u000f*\u00020\u0014H\b\u001a \u0010+\u001a\u00020&\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0003H\b¢\u0006\u0002\u0010(\u001a\r\u0010+\u001a\u00020&*\u00020\u0006H\b\u001a\r\u0010+\u001a\u00020&*\u00020\bH\b\u001a\r\u0010+\u001a\u00020&*\u00020\nH\b\u001a\r\u0010+\u001a\u00020&*\u00020\fH\b\u001a\r\u0010+\u001a\u00020&*\u00020\u000eH\b\u001a\r\u0010+\u001a\u00020&*\u00020\u0010H\b\u001a\r\u0010+\u001a\u00020&*\u00020\u0012H\b\u001a\r\u0010+\u001a\u00020&*\u00020\u0014H\b\u001aQ\u0010,\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0003\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u00032\f\u0010-\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00032\b\b\u0002\u0010.\u001a\u00020\u000f2\b\b\u0002\u0010/\u001a\u00020\u000f2\b\b\u0002\u00100\u001a\u00020\u000fH\u0007¢\u0006\u0002\u00101\u001a2\u0010,\u001a\u00020\u0006*\u00020\u00062\u0006\u0010-\u001a\u00020\u00062\b\b\u0002\u0010.\u001a\u00020\u000f2\b\b\u0002\u0010/\u001a\u00020\u000f2\b\b\u0002\u00100\u001a\u00020\u000fH\u0007\u001a2\u0010,\u001a\u00020\b*\u00020\b2\u0006\u0010-\u001a\u00020\b2\b\b\u0002\u0010.\u001a\u00020\u000f2\b\b\u0002\u0010/\u001a\u00020\u000f2\b\b\u0002\u00100\u001a\u00020\u000fH\u0007\u001a2\u0010,\u001a\u00020\n*\u00020\n2\u0006\u0010-\u001a\u00020\n2\b\b\u0002\u0010.\u001a\u00020\u000f2\b\b\u0002\u0010/\u001a\u00020\u000f2\b\b\u0002\u00100\u001a\u00020\u000fH\u0007\u001a2\u0010,\u001a\u00020\f*\u00020\f2\u0006\u0010-\u001a\u00020\f2\b\b\u0002\u0010.\u001a\u00020\u000f2\b\b\u0002\u0010/\u001a\u00020\u000f2\b\b\u0002\u00100\u001a\u00020\u000fH\u0007\u001a2\u0010,\u001a\u00020\u000e*\u00020\u000e2\u0006\u0010-\u001a\u00020\u000e2\b\b\u0002\u0010.\u001a\u00020\u000f2\b\b\u0002\u0010/\u001a\u00020\u000f2\b\b\u0002\u00100\u001a\u00020\u000fH\u0007\u001a2\u0010,\u001a\u00020\u0010*\u00020\u00102\u0006\u0010-\u001a\u00020\u00102\b\b\u0002\u0010.\u001a\u00020\u000f2\b\b\u0002\u0010/\u001a\u00020\u000f2\b\b\u0002\u00100\u001a\u00020\u000fH\u0007\u001a2\u0010,\u001a\u00020\u0012*\u00020\u00122\u0006\u0010-\u001a\u00020\u00122\b\b\u0002\u0010.\u001a\u00020\u000f2\b\b\u0002\u0010/\u001a\u00020\u000f2\b\b\u0002\u00100\u001a\u00020\u000fH\u0007\u001a2\u0010,\u001a\u00020\u0014*\u00020\u00142\u0006\u0010-\u001a\u00020\u00142\b\b\u0002\u0010.\u001a\u00020\u000f2\b\b\u0002\u0010/\u001a\u00020\u000f2\b\b\u0002\u00100\u001a\u00020\u000fH\u0007\u001a$\u00102\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0003\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\b¢\u0006\u0002\u00103\u001a.\u00102\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001H\u00020\u0003\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u00104\u001a\u00020\u000fH\b¢\u0006\u0002\u00105\u001a\r\u00102\u001a\u00020\u0006*\u00020\u0006H\b\u001a\u0015\u00102\u001a\u00020\u0006*\u00020\u00062\u0006\u00104\u001a\u00020\u000fH\b\u001a\r\u00102\u001a\u00020\b*\u00020\bH\b\u001a\u0015\u00102\u001a\u00020\b*\u00020\b2\u0006\u00104\u001a\u00020\u000fH\b\u001a\r\u00102\u001a\u00020\n*\u00020\nH\b\u001a\u0015\u00102\u001a\u00020\n*\u00020\n2\u0006\u00104\u001a\u00020\u000fH\b\u001a\r\u00102\u001a\u00020\f*\u00020\fH\b\u001a\u0015\u00102\u001a\u00020\f*\u00020\f2\u0006\u00104\u001a\u00020\u000fH\b\u001a\r\u00102\u001a\u00020\u000e*\u00020\u000eH\b\u001a\u0015\u00102\u001a\u00020\u000e*\u00020\u000e2\u0006\u00104\u001a\u00020\u000fH\b\u001a\r\u00102\u001a\u00020\u0010*\u00020\u0010H\b\u001a\u0015\u00102\u001a\u00020\u0010*\u00020\u00102\u0006\u00104\u001a\u00020\u000fH\b\u001a\r\u00102\u001a\u00020\u0012*\u00020\u0012H\b\u001a\u0015\u00102\u001a\u00020\u0012*\u00020\u00122\u0006\u00104\u001a\u00020\u000fH\b\u001a\r\u00102\u001a\u00020\u0014*\u00020\u0014H\b\u001a\u0015\u00102\u001a\u00020\u0014*\u00020\u00142\u0006\u00104\u001a\u00020\u000fH\b\u001a6\u00106\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0003\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u000fH\b¢\u0006\u0004\b7\u00108\u001a\"\u00106\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u000fH\b¢\u0006\u0002\b7\u001a\"\u00106\u001a\u00020\b*\u00020\b2\u0006\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u000fH\b¢\u0006\u0002\b7\u001a\"\u00106\u001a\u00020\n*\u00020\n2\u0006\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u000fH\b¢\u0006\u0002\b7\u001a\"\u00106\u001a\u00020\f*\u00020\f2\u0006\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u000fH\b¢\u0006\u0002\b7\u001a\"\u00106\u001a\u00020\u000e*\u00020\u000e2\u0006\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u000fH\b¢\u0006\u0002\b7\u001a\"\u00106\u001a\u00020\u0010*\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u000fH\b¢\u0006\u0002\b7\u001a\"\u00106\u001a\u00020\u0012*\u00020\u00122\u0006\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u000fH\b¢\u0006\u0002\b7\u001a\"\u00106\u001a\u00020\u0014*\u00020\u00142\u0006\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u000fH\b¢\u0006\u0002\b7\u001a5\u00109\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0003\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u000fH\u0001¢\u0006\u0004\b6\u00108\u001a!\u00109\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u000fH\u0001¢\u0006\u0002\b6\u001a!\u00109\u001a\u00020\b*\u00020\b2\u0006\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u000fH\u0001¢\u0006\u0002\b6\u001a!\u00109\u001a\u00020\n*\u00020\n2\u0006\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u000fH\u0001¢\u0006\u0002\b6\u001a!\u00109\u001a\u00020\f*\u00020\f2\u0006\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u000fH\u0001¢\u0006\u0002\b6\u001a!\u00109\u001a\u00020\u000e*\u00020\u000e2\u0006\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u000fH\u0001¢\u0006\u0002\b6\u001a!\u00109\u001a\u00020\u0010*\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u000fH\u0001¢\u0006\u0002\b6\u001a!\u00109\u001a\u00020\u0012*\u00020\u00122\u0006\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u000fH\u0001¢\u0006\u0002\b6\u001a!\u00109\u001a\u00020\u0014*\u00020\u00142\u0006\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u000fH\u0001¢\u0006\u0002\b6\u001a7\u0010:\u001a\u00020;\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\u0016\u001a\u0002H\u00022\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f¢\u0006\u0002\u0010<\u001a&\u0010:\u001a\u00020;*\u00020\u00062\u0006\u0010\u0016\u001a\u00020\u00052\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f\u001a&\u0010:\u001a\u00020;*\u00020\b2\u0006\u0010\u0016\u001a\u00020\u00072\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f\u001a&\u0010:\u001a\u00020;*\u00020\n2\u0006\u0010\u0016\u001a\u00020\t2\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f\u001a&\u0010:\u001a\u00020;*\u00020\f2\u0006\u0010\u0016\u001a\u00020\u000b2\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f\u001a&\u0010:\u001a\u00020;*\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\r2\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f\u001a&\u0010:\u001a\u00020;*\u00020\u00102\u0006\u0010\u0016\u001a\u00020\u000f2\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f\u001a&\u0010:\u001a\u00020;*\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u00112\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f\u001a&\u0010:\u001a\u00020;*\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u00132\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f\u001a-\u0010=\u001a\b\u0012\u0004\u0012\u0002H>0\u0001\"\u0004\b\u0000\u0010>*\u0006\u0012\u0002\b\u00030\u00032\f\u0010?\u001a\b\u0012\u0004\u0012\u0002H>0@¢\u0006\u0002\u0010A\u001aA\u0010B\u001a\u0002HC\"\u0010\b\u0000\u0010C*\n\u0012\u0006\b\u0000\u0012\u0002H>0D\"\u0004\b\u0001\u0010>*\u0006\u0012\u0002\b\u00030\u00032\u0006\u0010-\u001a\u0002HC2\f\u0010?\u001a\b\u0012\u0004\u0012\u0002H>0@¢\u0006\u0002\u0010E\u001a,\u0010F\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0003\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\u0016\u001a\u0002H\u0002H\u0002¢\u0006\u0002\u0010G\u001a4\u0010F\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0003\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u000e\u0010H\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0003H\u0002¢\u0006\u0002\u0010I\u001a2\u0010F\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0003\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\f\u0010H\u001a\b\u0012\u0004\u0012\u0002H\u00020JH\u0002¢\u0006\u0002\u0010K\u001a\u0015\u0010F\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\u0016\u001a\u00020\u0005H\u0002\u001a\u0015\u0010F\u001a\u00020\u0006*\u00020\u00062\u0006\u0010H\u001a\u00020\u0006H\u0002\u001a\u001b\u0010F\u001a\u00020\u0006*\u00020\u00062\f\u0010H\u001a\b\u0012\u0004\u0012\u00020\u00050JH\u0002\u001a\u0015\u0010F\u001a\u00020\b*\u00020\b2\u0006\u0010\u0016\u001a\u00020\u0007H\u0002\u001a\u0015\u0010F\u001a\u00020\b*\u00020\b2\u0006\u0010H\u001a\u00020\bH\u0002\u001a\u001b\u0010F\u001a\u00020\b*\u00020\b2\f\u0010H\u001a\b\u0012\u0004\u0012\u00020\u00070JH\u0002\u001a\u0015\u0010F\u001a\u00020\n*\u00020\n2\u0006\u0010\u0016\u001a\u00020\tH\u0002\u001a\u0015\u0010F\u001a\u00020\n*\u00020\n2\u0006\u0010H\u001a\u00020\nH\u0002\u001a\u001b\u0010F\u001a\u00020\n*\u00020\n2\f\u0010H\u001a\b\u0012\u0004\u0012\u00020\t0JH\u0002\u001a\u0015\u0010F\u001a\u00020\f*\u00020\f2\u0006\u0010\u0016\u001a\u00020\u000bH\u0002\u001a\u0015\u0010F\u001a\u00020\f*\u00020\f2\u0006\u0010H\u001a\u00020\fH\u0002\u001a\u001b\u0010F\u001a\u00020\f*\u00020\f2\f\u0010H\u001a\b\u0012\u0004\u0012\u00020\u000b0JH\u0002\u001a\u0015\u0010F\u001a\u00020\u000e*\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\rH\u0002\u001a\u0015\u0010F\u001a\u00020\u000e*\u00020\u000e2\u0006\u0010H\u001a\u00020\u000eH\u0002\u001a\u001b\u0010F\u001a\u00020\u000e*\u00020\u000e2\f\u0010H\u001a\b\u0012\u0004\u0012\u00020\r0JH\u0002\u001a\u0015\u0010F\u001a\u00020\u0010*\u00020\u00102\u0006\u0010\u0016\u001a\u00020\u000fH\u0002\u001a\u0015\u0010F\u001a\u00020\u0010*\u00020\u00102\u0006\u0010H\u001a\u00020\u0010H\u0002\u001a\u001b\u0010F\u001a\u00020\u0010*\u00020\u00102\f\u0010H\u001a\b\u0012\u0004\u0012\u00020\u000f0JH\u0002\u001a\u0015\u0010F\u001a\u00020\u0012*\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u0011H\u0002\u001a\u0015\u0010F\u001a\u00020\u0012*\u00020\u00122\u0006\u0010H\u001a\u00020\u0012H\u0002\u001a\u001b\u0010F\u001a\u00020\u0012*\u00020\u00122\f\u0010H\u001a\b\u0012\u0004\u0012\u00020\u00110JH\u0002\u001a\u0015\u0010F\u001a\u00020\u0014*\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0013H\u0002\u001a\u0015\u0010F\u001a\u00020\u0014*\u00020\u00142\u0006\u0010H\u001a\u00020\u0014H\u0002\u001a\u001b\u0010F\u001a\u00020\u0014*\u00020\u00142\f\u0010H\u001a\b\u0012\u0004\u0012\u00020\u00130JH\u0002\u001a,\u0010L\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0003\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\u0016\u001a\u0002H\u0002H\b¢\u0006\u0002\u0010G\u001a\u001d\u0010M\u001a\u00020;\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0003¢\u0006\u0002\u0010N\u001a*\u0010M\u001a\u00020;\"\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020O*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0003H\b¢\u0006\u0002\u0010P\u001a1\u0010M\u001a\u00020;\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u00032\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f¢\u0006\u0002\u0010Q\u001a\n\u0010M\u001a\u00020;*\u00020\b\u001a\u001e\u0010M\u001a\u00020;*\u00020\b2\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f\u001a\n\u0010M\u001a\u00020;*\u00020\n\u001a\u001e\u0010M\u001a\u00020;*\u00020\n2\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f\u001a\n\u0010M\u001a\u00020;*\u00020\f\u001a\u001e\u0010M\u001a\u00020;*\u00020\f2\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f\u001a\n\u0010M\u001a\u00020;*\u00020\u000e\u001a\u001e\u0010M\u001a\u00020;*\u00020\u000e2\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f\u001a\n\u0010M\u001a\u00020;*\u00020\u0010\u001a\u001e\u0010M\u001a\u00020;*\u00020\u00102\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f\u001a\n\u0010M\u001a\u00020;*\u00020\u0012\u001a\u001e\u0010M\u001a\u00020;*\u00020\u00122\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f\u001a\n\u0010M\u001a\u00020;*\u00020\u0014\u001a\u001e\u0010M\u001a\u00020;*\u00020\u00142\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f\u001a9\u0010R\u001a\u00020;\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u00032\u001a\u0010\u0017\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u00020\u0018j\n\u0012\u0006\b\u0000\u0012\u0002H\u0002`\u0019¢\u0006\u0002\u0010S\u001aM\u0010R\u001a\u00020;\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u00032\u001a\u0010\u0017\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u00020\u0018j\n\u0012\u0006\b\u0000\u0012\u0002H\u0002`\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f¢\u0006\u0002\u0010T\u001a-\u0010U\u001a\b\u0012\u0004\u0012\u0002H\u00020V\"\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020O*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0003¢\u0006\u0002\u0010W\u001a?\u0010U\u001a\b\u0012\u0004\u0012\u0002H\u00020V\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u00032\u001a\u0010\u0017\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u00020\u0018j\n\u0012\u0006\b\u0000\u0012\u0002H\u0002`\u0019¢\u0006\u0002\u0010X\u001a\u0010\u0010U\u001a\b\u0012\u0004\u0012\u00020\u00050V*\u00020\u0006\u001a\u0010\u0010U\u001a\b\u0012\u0004\u0012\u00020\u00070V*\u00020\b\u001a\u0010\u0010U\u001a\b\u0012\u0004\u0012\u00020\t0V*\u00020\n\u001a\u0010\u0010U\u001a\b\u0012\u0004\u0012\u00020\u000b0V*\u00020\f\u001a\u0010\u0010U\u001a\b\u0012\u0004\u0012\u00020\r0V*\u00020\u000e\u001a\u0010\u0010U\u001a\b\u0012\u0004\u0012\u00020\u000f0V*\u00020\u0010\u001a\u0010\u0010U\u001a\b\u0012\u0004\u0012\u00020\u00110V*\u00020\u0012\u001a\u0010\u0010U\u001a\b\u0012\u0004\u0012\u00020\u00130V*\u00020\u0014\u001a\u0015\u0010Y\u001a\b\u0012\u0004\u0012\u00020\u00050\u0003*\u00020\u0006¢\u0006\u0002\u0010Z\u001a\u0015\u0010Y\u001a\b\u0012\u0004\u0012\u00020\u00070\u0003*\u00020\b¢\u0006\u0002\u0010[\u001a\u0015\u0010Y\u001a\b\u0012\u0004\u0012\u00020\t0\u0003*\u00020\n¢\u0006\u0002\u0010\\\u001a\u0015\u0010Y\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0003*\u00020\f¢\u0006\u0002\u0010]\u001a\u0015\u0010Y\u001a\b\u0012\u0004\u0012\u00020\r0\u0003*\u00020\u000e¢\u0006\u0002\u0010^\u001a\u0015\u0010Y\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0003*\u00020\u0010¢\u0006\u0002\u0010_\u001a\u0015\u0010Y\u001a\b\u0012\u0004\u0012\u00020\u00110\u0003*\u00020\u0012¢\u0006\u0002\u0010`\u001a\u0015\u0010Y\u001a\b\u0012\u0004\u0012\u00020\u00130\u0003*\u00020\u0014¢\u0006\u0002\u0010a¨\u0006b"}, d2 = {"asList", "", "T", "", "([Ljava/lang/Object;)Ljava/util/List;", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "binarySearch", "element", "comparator", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "fromIndex", "toIndex", "([Ljava/lang/Object;Ljava/lang/Object;Ljava/util/Comparator;II)I", "([Ljava/lang/Object;Ljava/lang/Object;II)I", "contentDeepEquals", "other", "contentDeepEqualsInline", "([Ljava/lang/Object;[Ljava/lang/Object;)Z", "contentDeepHashCode", "contentDeepHashCodeInline", "([Ljava/lang/Object;)I", "contentDeepToString", "", "contentDeepToStringInline", "([Ljava/lang/Object;)Ljava/lang/String;", "contentEquals", "contentHashCode", "contentToString", "copyInto", "destination", "destinationOffset", "startIndex", "endIndex", "([Ljava/lang/Object;[Ljava/lang/Object;III)[Ljava/lang/Object;", "copyOf", "([Ljava/lang/Object;)[Ljava/lang/Object;", "newSize", "([Ljava/lang/Object;I)[Ljava/lang/Object;", "copyOfRange", "copyOfRangeInline", "([Ljava/lang/Object;II)[Ljava/lang/Object;", "copyOfRangeImpl", "fill", "", "([Ljava/lang/Object;Ljava/lang/Object;II)V", "filterIsInstance", "R", "klass", "Ljava/lang/Class;", "([Ljava/lang/Object;Ljava/lang/Class;)Ljava/util/List;", "filterIsInstanceTo", "C", "", "([Ljava/lang/Object;Ljava/util/Collection;Ljava/lang/Class;)Ljava/util/Collection;", "plus", "([Ljava/lang/Object;Ljava/lang/Object;)[Ljava/lang/Object;", "elements", "([Ljava/lang/Object;[Ljava/lang/Object;)[Ljava/lang/Object;", "", "([Ljava/lang/Object;Ljava/util/Collection;)[Ljava/lang/Object;", "plusElement", "sort", "([Ljava/lang/Object;)V", "", "([Ljava/lang/Comparable;)V", "([Ljava/lang/Object;II)V", "sortWith", "([Ljava/lang/Object;Ljava/util/Comparator;)V", "([Ljava/lang/Object;Ljava/util/Comparator;II)V", "toSortedSet", "Ljava/util/SortedSet;", "([Ljava/lang/Comparable;)Ljava/util/SortedSet;", "([Ljava/lang/Object;Ljava/util/Comparator;)Ljava/util/SortedSet;", "toTypedArray", "([Z)[Ljava/lang/Boolean;", "([B)[Ljava/lang/Byte;", "([C)[Ljava/lang/Character;", "([D)[Ljava/lang/Double;", "([F)[Ljava/lang/Float;", "([I)[Ljava/lang/Integer;", "([J)[Ljava/lang/Long;", "([S)[Ljava/lang/Short;", "kotlin-stdlib"}, k = 5, mv = {1, 1, 13}, xi = 1, xs = "kotlin/collections/ArraysKt")
/* compiled from: _ArraysJvm.kt */
class ArraysKt___ArraysJvmKt extends ArraysKt__ArraysKt {
    @NotNull
    public static final <R> List<R> filterIsInstance(@NotNull Object[] objArr, @NotNull Class<R> cls) {
        Intrinsics.checkParameterIsNotNull(objArr, "receiver$0");
        Intrinsics.checkParameterIsNotNull(cls, "klass");
        return (List) filterIsInstanceTo(objArr, new ArrayList(), cls);
    }

    @NotNull
    public static final <C extends Collection<? super R>, R> C filterIsInstanceTo(@NotNull Object[] objArr, @NotNull C c, @NotNull Class<R> cls) {
        Intrinsics.checkParameterIsNotNull(objArr, "receiver$0");
        Intrinsics.checkParameterIsNotNull(c, "destination");
        Intrinsics.checkParameterIsNotNull(cls, "klass");
        for (Object obj : objArr) {
            if (cls.isInstance(obj)) {
                c.add(obj);
            }
        }
        return c;
    }

    @NotNull
    public static final <T> List<T> asList(@NotNull T[] tArr) {
        Intrinsics.checkParameterIsNotNull(tArr, "receiver$0");
        tArr = ArraysUtilJVM.asList(tArr);
        Intrinsics.checkExpressionValueIsNotNull(tArr, "ArraysUtilJVM.asList(this)");
        return tArr;
    }

    @NotNull
    public static final List<Byte> asList(@NotNull byte[] bArr) {
        Intrinsics.checkParameterIsNotNull(bArr, "receiver$0");
        return new ArraysKt___ArraysJvmKt$asList$1(bArr);
    }

    @NotNull
    public static final List<Short> asList(@NotNull short[] sArr) {
        Intrinsics.checkParameterIsNotNull(sArr, "receiver$0");
        return new ArraysKt___ArraysJvmKt$asList$2(sArr);
    }

    @NotNull
    public static final List<Integer> asList(@NotNull int[] iArr) {
        Intrinsics.checkParameterIsNotNull(iArr, "receiver$0");
        return new ArraysKt___ArraysJvmKt$asList$3(iArr);
    }

    @NotNull
    public static final List<Long> asList(@NotNull long[] jArr) {
        Intrinsics.checkParameterIsNotNull(jArr, "receiver$0");
        return new ArraysKt___ArraysJvmKt$asList$4(jArr);
    }

    @NotNull
    public static final List<Float> asList(@NotNull float[] fArr) {
        Intrinsics.checkParameterIsNotNull(fArr, "receiver$0");
        return new ArraysKt___ArraysJvmKt$asList$5(fArr);
    }

    @NotNull
    public static final List<Double> asList(@NotNull double[] dArr) {
        Intrinsics.checkParameterIsNotNull(dArr, "receiver$0");
        return new ArraysKt___ArraysJvmKt$asList$6(dArr);
    }

    @NotNull
    public static final List<Boolean> asList(@NotNull boolean[] zArr) {
        Intrinsics.checkParameterIsNotNull(zArr, "receiver$0");
        return new ArraysKt___ArraysJvmKt$asList$7(zArr);
    }

    @NotNull
    public static final List<Character> asList(@NotNull char[] cArr) {
        Intrinsics.checkParameterIsNotNull(cArr, "receiver$0");
        return new ArraysKt___ArraysJvmKt$asList$8(cArr);
    }

    public static /* synthetic */ int binarySearch$default(Object[] objArr, Object obj, Comparator comparator, int i, int i2, int i3, Object obj2) {
        if ((i3 & 4) != null) {
            i = 0;
        }
        if ((i3 & 8) != 0) {
            i2 = objArr.length;
        }
        return binarySearch(objArr, obj, comparator, i, i2);
    }

    public static final <T> int binarySearch(@NotNull T[] tArr, T t, @NotNull Comparator<? super T> comparator, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(tArr, "receiver$0");
        Intrinsics.checkParameterIsNotNull(comparator, "comparator");
        return Arrays.binarySearch(tArr, i, i2, t, comparator);
    }

    public static /* synthetic */ int binarySearch$default(Object[] objArr, Object obj, int i, int i2, int i3, Object obj2) {
        if ((i3 & 2) != null) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = objArr.length;
        }
        return binarySearch(objArr, obj, i, i2);
    }

    public static final <T> int binarySearch(@NotNull T[] tArr, T t, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(tArr, "receiver$0");
        return Arrays.binarySearch(tArr, i, i2, t);
    }

    public static /* synthetic */ int binarySearch$default(byte[] bArr, byte b, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != null) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = bArr.length;
        }
        return binarySearch(bArr, b, i, i2);
    }

    public static final int binarySearch(@NotNull byte[] bArr, byte b, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(bArr, "receiver$0");
        return Arrays.binarySearch(bArr, i, i2, b);
    }

    public static /* synthetic */ int binarySearch$default(short[] sArr, short s, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != null) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = sArr.length;
        }
        return binarySearch(sArr, s, i, i2);
    }

    public static final int binarySearch(@NotNull short[] sArr, short s, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(sArr, "receiver$0");
        return Arrays.binarySearch(sArr, i, i2, s);
    }

    public static /* synthetic */ int binarySearch$default(int[] iArr, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 2) != null) {
            i2 = 0;
        }
        if ((i4 & 4) != 0) {
            i3 = iArr.length;
        }
        return binarySearch(iArr, i, i2, i3);
    }

    public static final int binarySearch(@NotNull int[] iArr, int i, int i2, int i3) {
        Intrinsics.checkParameterIsNotNull(iArr, "receiver$0");
        return Arrays.binarySearch(iArr, i2, i3, i);
    }

    public static /* synthetic */ int binarySearch$default(long[] jArr, long j, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != null) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = jArr.length;
        }
        return binarySearch(jArr, j, i, i2);
    }

    public static final int binarySearch(@NotNull long[] jArr, long j, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(jArr, "receiver$0");
        return Arrays.binarySearch(jArr, i, i2, j);
    }

    public static /* synthetic */ int binarySearch$default(float[] fArr, float f, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != null) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = fArr.length;
        }
        return binarySearch(fArr, f, i, i2);
    }

    public static final int binarySearch(@NotNull float[] fArr, float f, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(fArr, "receiver$0");
        return Arrays.binarySearch(fArr, i, i2, f);
    }

    public static /* synthetic */ int binarySearch$default(double[] dArr, double d, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != null) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = dArr.length;
        }
        return binarySearch(dArr, d, i, i2);
    }

    public static final int binarySearch(@NotNull double[] dArr, double d, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(dArr, "receiver$0");
        return Arrays.binarySearch(dArr, i, i2, d);
    }

    public static /* synthetic */ int binarySearch$default(char[] cArr, char c, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != null) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = cArr.length;
        }
        return binarySearch(cArr, c, i, i2);
    }

    public static final int binarySearch(@NotNull char[] cArr, char c, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(cArr, "receiver$0");
        return Arrays.binarySearch(cArr, i, i2, c);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    @JvmName(name = "contentDeepEqualsInline")
    private static final <T> boolean contentDeepEqualsInline(@NotNull T[] tArr, T[] tArr2) {
        if (PlatformImplementationsKt.apiVersionIsAtLeast(1, 3, 0)) {
            return ArraysKt__ArraysKt.contentDeepEquals(tArr, tArr2);
        }
        return Arrays.deepEquals(tArr, tArr2);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    @JvmName(name = "contentDeepHashCodeInline")
    private static final <T> int contentDeepHashCodeInline(@NotNull T[] tArr) {
        if (PlatformImplementationsKt.apiVersionIsAtLeast(1, 3, 0)) {
            return ArraysKt__ArraysJVMKt.contentDeepHashCode(tArr);
        }
        return Arrays.deepHashCode(tArr);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    @JvmName(name = "contentDeepToStringInline")
    private static final <T> String contentDeepToStringInline(@NotNull T[] tArr) {
        if (PlatformImplementationsKt.apiVersionIsAtLeast(1, 3, 0)) {
            return ArraysKt__ArraysKt.contentDeepToString(tArr);
        }
        tArr = Arrays.deepToString(tArr);
        Intrinsics.checkExpressionValueIsNotNull(tArr, "java.util.Arrays.deepToString(this)");
        return tArr;
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final <T> boolean contentEquals(@NotNull T[] tArr, T[] tArr2) {
        return Arrays.equals(tArr, tArr2);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final boolean contentEquals(@NotNull byte[] bArr, byte[] bArr2) {
        return Arrays.equals(bArr, bArr2);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final boolean contentEquals(@NotNull short[] sArr, short[] sArr2) {
        return Arrays.equals(sArr, sArr2);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final boolean contentEquals(@NotNull int[] iArr, int[] iArr2) {
        return Arrays.equals(iArr, iArr2);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final boolean contentEquals(@NotNull long[] jArr, long[] jArr2) {
        return Arrays.equals(jArr, jArr2);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final boolean contentEquals(@NotNull float[] fArr, float[] fArr2) {
        return Arrays.equals(fArr, fArr2);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final boolean contentEquals(@NotNull double[] dArr, double[] dArr2) {
        return Arrays.equals(dArr, dArr2);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final boolean contentEquals(@NotNull boolean[] zArr, boolean[] zArr2) {
        return Arrays.equals(zArr, zArr2);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final boolean contentEquals(@NotNull char[] cArr, char[] cArr2) {
        return Arrays.equals(cArr, cArr2);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final <T> int contentHashCode(@NotNull T[] tArr) {
        return Arrays.hashCode(tArr);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final int contentHashCode(@NotNull byte[] bArr) {
        return Arrays.hashCode(bArr);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final int contentHashCode(@NotNull short[] sArr) {
        return Arrays.hashCode(sArr);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final int contentHashCode(@NotNull int[] iArr) {
        return Arrays.hashCode(iArr);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final int contentHashCode(@NotNull long[] jArr) {
        return Arrays.hashCode(jArr);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final int contentHashCode(@NotNull float[] fArr) {
        return Arrays.hashCode(fArr);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final int contentHashCode(@NotNull double[] dArr) {
        return Arrays.hashCode(dArr);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final int contentHashCode(@NotNull boolean[] zArr) {
        return Arrays.hashCode(zArr);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final int contentHashCode(@NotNull char[] cArr) {
        return Arrays.hashCode(cArr);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final <T> String contentToString(@NotNull T[] tArr) {
        tArr = Arrays.toString(tArr);
        Intrinsics.checkExpressionValueIsNotNull(tArr, "java.util.Arrays.toString(this)");
        return tArr;
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final String contentToString(@NotNull byte[] bArr) {
        bArr = Arrays.toString(bArr);
        Intrinsics.checkExpressionValueIsNotNull(bArr, "java.util.Arrays.toString(this)");
        return bArr;
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final String contentToString(@NotNull short[] sArr) {
        sArr = Arrays.toString(sArr);
        Intrinsics.checkExpressionValueIsNotNull(sArr, "java.util.Arrays.toString(this)");
        return sArr;
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final String contentToString(@NotNull int[] iArr) {
        iArr = Arrays.toString(iArr);
        Intrinsics.checkExpressionValueIsNotNull(iArr, "java.util.Arrays.toString(this)");
        return iArr;
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final String contentToString(@NotNull long[] jArr) {
        jArr = Arrays.toString(jArr);
        Intrinsics.checkExpressionValueIsNotNull(jArr, "java.util.Arrays.toString(this)");
        return jArr;
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final String contentToString(@NotNull float[] fArr) {
        fArr = Arrays.toString(fArr);
        Intrinsics.checkExpressionValueIsNotNull(fArr, "java.util.Arrays.toString(this)");
        return fArr;
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final String contentToString(@NotNull double[] dArr) {
        dArr = Arrays.toString(dArr);
        Intrinsics.checkExpressionValueIsNotNull(dArr, "java.util.Arrays.toString(this)");
        return dArr;
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final String contentToString(@NotNull boolean[] zArr) {
        zArr = Arrays.toString(zArr);
        Intrinsics.checkExpressionValueIsNotNull(zArr, "java.util.Arrays.toString(this)");
        return zArr;
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final String contentToString(@NotNull char[] cArr) {
        cArr = Arrays.toString(cArr);
        Intrinsics.checkExpressionValueIsNotNull(cArr, "java.util.Arrays.toString(this)");
        return cArr;
    }

    @NotNull
    @SinceKotlin(version = "1.3")
    public static /* synthetic */ Object[] copyInto$default(Object[] objArr, Object[] objArr2, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 2) != null) {
            i = 0;
        }
        if ((i4 & 4) != null) {
            i2 = 0;
        }
        if ((i4 & 8) != 0) {
            i3 = objArr.length;
        }
        return copyInto(objArr, objArr2, i, i2, i3);
    }

    @NotNull
    @SinceKotlin(version = "1.3")
    public static final <T> T[] copyInto(@NotNull T[] tArr, @NotNull T[] tArr2, int i, int i2, int i3) {
        Intrinsics.checkParameterIsNotNull(tArr, "receiver$0");
        Intrinsics.checkParameterIsNotNull(tArr2, "destination");
        System.arraycopy(tArr, i2, tArr2, i, i3 - i2);
        return tArr2;
    }

    @NotNull
    @SinceKotlin(version = "1.3")
    public static /* synthetic */ byte[] copyInto$default(byte[] bArr, byte[] bArr2, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 2) != null) {
            i = 0;
        }
        if ((i4 & 4) != null) {
            i2 = 0;
        }
        if ((i4 & 8) != 0) {
            i3 = bArr.length;
        }
        return copyInto(bArr, bArr2, i, i2, i3);
    }

    @NotNull
    @SinceKotlin(version = "1.3")
    public static final byte[] copyInto(@NotNull byte[] bArr, @NotNull byte[] bArr2, int i, int i2, int i3) {
        Intrinsics.checkParameterIsNotNull(bArr, "receiver$0");
        Intrinsics.checkParameterIsNotNull(bArr2, "destination");
        System.arraycopy(bArr, i2, bArr2, i, i3 - i2);
        return bArr2;
    }

    @NotNull
    @SinceKotlin(version = "1.3")
    public static /* synthetic */ short[] copyInto$default(short[] sArr, short[] sArr2, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 2) != null) {
            i = 0;
        }
        if ((i4 & 4) != null) {
            i2 = 0;
        }
        if ((i4 & 8) != 0) {
            i3 = sArr.length;
        }
        return copyInto(sArr, sArr2, i, i2, i3);
    }

    @NotNull
    @SinceKotlin(version = "1.3")
    public static final short[] copyInto(@NotNull short[] sArr, @NotNull short[] sArr2, int i, int i2, int i3) {
        Intrinsics.checkParameterIsNotNull(sArr, "receiver$0");
        Intrinsics.checkParameterIsNotNull(sArr2, "destination");
        System.arraycopy(sArr, i2, sArr2, i, i3 - i2);
        return sArr2;
    }

    @NotNull
    @SinceKotlin(version = "1.3")
    public static /* synthetic */ int[] copyInto$default(int[] iArr, int[] iArr2, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 2) != null) {
            i = 0;
        }
        if ((i4 & 4) != null) {
            i2 = 0;
        }
        if ((i4 & 8) != 0) {
            i3 = iArr.length;
        }
        return copyInto(iArr, iArr2, i, i2, i3);
    }

    @NotNull
    @SinceKotlin(version = "1.3")
    public static final int[] copyInto(@NotNull int[] iArr, @NotNull int[] iArr2, int i, int i2, int i3) {
        Intrinsics.checkParameterIsNotNull(iArr, "receiver$0");
        Intrinsics.checkParameterIsNotNull(iArr2, "destination");
        System.arraycopy(iArr, i2, iArr2, i, i3 - i2);
        return iArr2;
    }

    @NotNull
    @SinceKotlin(version = "1.3")
    public static /* synthetic */ long[] copyInto$default(long[] jArr, long[] jArr2, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 2) != null) {
            i = 0;
        }
        if ((i4 & 4) != null) {
            i2 = 0;
        }
        if ((i4 & 8) != 0) {
            i3 = jArr.length;
        }
        return copyInto(jArr, jArr2, i, i2, i3);
    }

    @NotNull
    @SinceKotlin(version = "1.3")
    public static final long[] copyInto(@NotNull long[] jArr, @NotNull long[] jArr2, int i, int i2, int i3) {
        Intrinsics.checkParameterIsNotNull(jArr, "receiver$0");
        Intrinsics.checkParameterIsNotNull(jArr2, "destination");
        System.arraycopy(jArr, i2, jArr2, i, i3 - i2);
        return jArr2;
    }

    @NotNull
    @SinceKotlin(version = "1.3")
    public static /* synthetic */ float[] copyInto$default(float[] fArr, float[] fArr2, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 2) != null) {
            i = 0;
        }
        if ((i4 & 4) != null) {
            i2 = 0;
        }
        if ((i4 & 8) != 0) {
            i3 = fArr.length;
        }
        return copyInto(fArr, fArr2, i, i2, i3);
    }

    @NotNull
    @SinceKotlin(version = "1.3")
    public static final float[] copyInto(@NotNull float[] fArr, @NotNull float[] fArr2, int i, int i2, int i3) {
        Intrinsics.checkParameterIsNotNull(fArr, "receiver$0");
        Intrinsics.checkParameterIsNotNull(fArr2, "destination");
        System.arraycopy(fArr, i2, fArr2, i, i3 - i2);
        return fArr2;
    }

    @NotNull
    @SinceKotlin(version = "1.3")
    public static /* synthetic */ double[] copyInto$default(double[] dArr, double[] dArr2, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 2) != null) {
            i = 0;
        }
        if ((i4 & 4) != null) {
            i2 = 0;
        }
        if ((i4 & 8) != 0) {
            i3 = dArr.length;
        }
        return copyInto(dArr, dArr2, i, i2, i3);
    }

    @NotNull
    @SinceKotlin(version = "1.3")
    public static final double[] copyInto(@NotNull double[] dArr, @NotNull double[] dArr2, int i, int i2, int i3) {
        Intrinsics.checkParameterIsNotNull(dArr, "receiver$0");
        Intrinsics.checkParameterIsNotNull(dArr2, "destination");
        System.arraycopy(dArr, i2, dArr2, i, i3 - i2);
        return dArr2;
    }

    @NotNull
    @SinceKotlin(version = "1.3")
    public static /* synthetic */ boolean[] copyInto$default(boolean[] zArr, boolean[] zArr2, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 2) != null) {
            i = 0;
        }
        if ((i4 & 4) != null) {
            i2 = 0;
        }
        if ((i4 & 8) != 0) {
            i3 = zArr.length;
        }
        return copyInto(zArr, zArr2, i, i2, i3);
    }

    @NotNull
    @SinceKotlin(version = "1.3")
    public static final boolean[] copyInto(@NotNull boolean[] zArr, @NotNull boolean[] zArr2, int i, int i2, int i3) {
        Intrinsics.checkParameterIsNotNull(zArr, "receiver$0");
        Intrinsics.checkParameterIsNotNull(zArr2, "destination");
        System.arraycopy(zArr, i2, zArr2, i, i3 - i2);
        return zArr2;
    }

    @NotNull
    @SinceKotlin(version = "1.3")
    public static /* synthetic */ char[] copyInto$default(char[] cArr, char[] cArr2, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 2) != null) {
            i = 0;
        }
        if ((i4 & 4) != null) {
            i2 = 0;
        }
        if ((i4 & 8) != 0) {
            i3 = cArr.length;
        }
        return copyInto(cArr, cArr2, i, i2, i3);
    }

    @NotNull
    @SinceKotlin(version = "1.3")
    public static final char[] copyInto(@NotNull char[] cArr, @NotNull char[] cArr2, int i, int i2, int i3) {
        Intrinsics.checkParameterIsNotNull(cArr, "receiver$0");
        Intrinsics.checkParameterIsNotNull(cArr2, "destination");
        System.arraycopy(cArr, i2, cArr2, i, i3 - i2);
        return cArr2;
    }

    @InlineOnly
    private static final <T> T[] copyOf(@NotNull T[] tArr) {
        tArr = Arrays.copyOf(tArr, tArr.length);
        Intrinsics.checkExpressionValueIsNotNull(tArr, "java.util.Arrays.copyOf(this, size)");
        return tArr;
    }

    @InlineOnly
    private static final byte[] copyOf(@NotNull byte[] bArr) {
        bArr = Arrays.copyOf(bArr, bArr.length);
        Intrinsics.checkExpressionValueIsNotNull(bArr, "java.util.Arrays.copyOf(this, size)");
        return bArr;
    }

    @InlineOnly
    private static final short[] copyOf(@NotNull short[] sArr) {
        sArr = Arrays.copyOf(sArr, sArr.length);
        Intrinsics.checkExpressionValueIsNotNull(sArr, "java.util.Arrays.copyOf(this, size)");
        return sArr;
    }

    @InlineOnly
    private static final int[] copyOf(@NotNull int[] iArr) {
        iArr = Arrays.copyOf(iArr, iArr.length);
        Intrinsics.checkExpressionValueIsNotNull(iArr, "java.util.Arrays.copyOf(this, size)");
        return iArr;
    }

    @InlineOnly
    private static final long[] copyOf(@NotNull long[] jArr) {
        jArr = Arrays.copyOf(jArr, jArr.length);
        Intrinsics.checkExpressionValueIsNotNull(jArr, "java.util.Arrays.copyOf(this, size)");
        return jArr;
    }

    @InlineOnly
    private static final float[] copyOf(@NotNull float[] fArr) {
        fArr = Arrays.copyOf(fArr, fArr.length);
        Intrinsics.checkExpressionValueIsNotNull(fArr, "java.util.Arrays.copyOf(this, size)");
        return fArr;
    }

    @InlineOnly
    private static final double[] copyOf(@NotNull double[] dArr) {
        dArr = Arrays.copyOf(dArr, dArr.length);
        Intrinsics.checkExpressionValueIsNotNull(dArr, "java.util.Arrays.copyOf(this, size)");
        return dArr;
    }

    @InlineOnly
    private static final boolean[] copyOf(@NotNull boolean[] zArr) {
        zArr = Arrays.copyOf(zArr, zArr.length);
        Intrinsics.checkExpressionValueIsNotNull(zArr, "java.util.Arrays.copyOf(this, size)");
        return zArr;
    }

    @InlineOnly
    private static final char[] copyOf(@NotNull char[] cArr) {
        cArr = Arrays.copyOf(cArr, cArr.length);
        Intrinsics.checkExpressionValueIsNotNull(cArr, "java.util.Arrays.copyOf(this, size)");
        return cArr;
    }

    @InlineOnly
    private static final byte[] copyOf(@NotNull byte[] bArr, int i) {
        bArr = Arrays.copyOf(bArr, i);
        Intrinsics.checkExpressionValueIsNotNull(bArr, "java.util.Arrays.copyOf(this, newSize)");
        return bArr;
    }

    @InlineOnly
    private static final short[] copyOf(@NotNull short[] sArr, int i) {
        sArr = Arrays.copyOf(sArr, i);
        Intrinsics.checkExpressionValueIsNotNull(sArr, "java.util.Arrays.copyOf(this, newSize)");
        return sArr;
    }

    @InlineOnly
    private static final int[] copyOf(@NotNull int[] iArr, int i) {
        iArr = Arrays.copyOf(iArr, i);
        Intrinsics.checkExpressionValueIsNotNull(iArr, "java.util.Arrays.copyOf(this, newSize)");
        return iArr;
    }

    @InlineOnly
    private static final long[] copyOf(@NotNull long[] jArr, int i) {
        jArr = Arrays.copyOf(jArr, i);
        Intrinsics.checkExpressionValueIsNotNull(jArr, "java.util.Arrays.copyOf(this, newSize)");
        return jArr;
    }

    @InlineOnly
    private static final float[] copyOf(@NotNull float[] fArr, int i) {
        fArr = Arrays.copyOf(fArr, i);
        Intrinsics.checkExpressionValueIsNotNull(fArr, "java.util.Arrays.copyOf(this, newSize)");
        return fArr;
    }

    @InlineOnly
    private static final double[] copyOf(@NotNull double[] dArr, int i) {
        dArr = Arrays.copyOf(dArr, i);
        Intrinsics.checkExpressionValueIsNotNull(dArr, "java.util.Arrays.copyOf(this, newSize)");
        return dArr;
    }

    @InlineOnly
    private static final boolean[] copyOf(@NotNull boolean[] zArr, int i) {
        zArr = Arrays.copyOf(zArr, i);
        Intrinsics.checkExpressionValueIsNotNull(zArr, "java.util.Arrays.copyOf(this, newSize)");
        return zArr;
    }

    @InlineOnly
    private static final char[] copyOf(@NotNull char[] cArr, int i) {
        cArr = Arrays.copyOf(cArr, i);
        Intrinsics.checkExpressionValueIsNotNull(cArr, "java.util.Arrays.copyOf(this, newSize)");
        return cArr;
    }

    @InlineOnly
    private static final <T> T[] copyOf(@NotNull T[] tArr, int i) {
        tArr = Arrays.copyOf(tArr, i);
        Intrinsics.checkExpressionValueIsNotNull(tArr, "java.util.Arrays.copyOf(this, newSize)");
        return tArr;
    }

    @InlineOnly
    @JvmName(name = "copyOfRangeInline")
    private static final <T> T[] copyOfRangeInline(@NotNull T[] tArr, int i, int i2) {
        if (PlatformImplementationsKt.apiVersionIsAtLeast(1, 3, 0)) {
            return copyOfRange((Object[]) tArr, i, i2);
        }
        if (i2 <= tArr.length) {
            tArr = Arrays.copyOfRange(tArr, i, i2);
            Intrinsics.checkExpressionValueIsNotNull(tArr, "java.util.Arrays.copyOfR…this, fromIndex, toIndex)");
            return tArr;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("toIndex: ");
        stringBuilder.append(i2);
        stringBuilder.append(", size: ");
        stringBuilder.append(tArr.length);
        throw ((Throwable) new IndexOutOfBoundsException(stringBuilder.toString()));
    }

    @InlineOnly
    @JvmName(name = "copyOfRangeInline")
    private static final byte[] copyOfRangeInline(@NotNull byte[] bArr, int i, int i2) {
        if (PlatformImplementationsKt.apiVersionIsAtLeast(1, 3, 0)) {
            return copyOfRange(bArr, i, i2);
        }
        if (i2 <= bArr.length) {
            bArr = Arrays.copyOfRange(bArr, i, i2);
            Intrinsics.checkExpressionValueIsNotNull(bArr, "java.util.Arrays.copyOfR…this, fromIndex, toIndex)");
            return bArr;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("toIndex: ");
        stringBuilder.append(i2);
        stringBuilder.append(", size: ");
        stringBuilder.append(bArr.length);
        throw ((Throwable) new IndexOutOfBoundsException(stringBuilder.toString()));
    }

    @InlineOnly
    @JvmName(name = "copyOfRangeInline")
    private static final short[] copyOfRangeInline(@NotNull short[] sArr, int i, int i2) {
        if (PlatformImplementationsKt.apiVersionIsAtLeast(1, 3, 0)) {
            return copyOfRange(sArr, i, i2);
        }
        if (i2 <= sArr.length) {
            sArr = Arrays.copyOfRange(sArr, i, i2);
            Intrinsics.checkExpressionValueIsNotNull(sArr, "java.util.Arrays.copyOfR…this, fromIndex, toIndex)");
            return sArr;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("toIndex: ");
        stringBuilder.append(i2);
        stringBuilder.append(", size: ");
        stringBuilder.append(sArr.length);
        throw ((Throwable) new IndexOutOfBoundsException(stringBuilder.toString()));
    }

    @InlineOnly
    @JvmName(name = "copyOfRangeInline")
    private static final int[] copyOfRangeInline(@NotNull int[] iArr, int i, int i2) {
        if (PlatformImplementationsKt.apiVersionIsAtLeast(1, 3, 0)) {
            return copyOfRange(iArr, i, i2);
        }
        if (i2 <= iArr.length) {
            iArr = Arrays.copyOfRange(iArr, i, i2);
            Intrinsics.checkExpressionValueIsNotNull(iArr, "java.util.Arrays.copyOfR…this, fromIndex, toIndex)");
            return iArr;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("toIndex: ");
        stringBuilder.append(i2);
        stringBuilder.append(", size: ");
        stringBuilder.append(iArr.length);
        throw ((Throwable) new IndexOutOfBoundsException(stringBuilder.toString()));
    }

    @InlineOnly
    @JvmName(name = "copyOfRangeInline")
    private static final long[] copyOfRangeInline(@NotNull long[] jArr, int i, int i2) {
        if (PlatformImplementationsKt.apiVersionIsAtLeast(1, 3, 0)) {
            return copyOfRange(jArr, i, i2);
        }
        if (i2 <= jArr.length) {
            jArr = Arrays.copyOfRange(jArr, i, i2);
            Intrinsics.checkExpressionValueIsNotNull(jArr, "java.util.Arrays.copyOfR…this, fromIndex, toIndex)");
            return jArr;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("toIndex: ");
        stringBuilder.append(i2);
        stringBuilder.append(", size: ");
        stringBuilder.append(jArr.length);
        throw ((Throwable) new IndexOutOfBoundsException(stringBuilder.toString()));
    }

    @InlineOnly
    @JvmName(name = "copyOfRangeInline")
    private static final float[] copyOfRangeInline(@NotNull float[] fArr, int i, int i2) {
        if (PlatformImplementationsKt.apiVersionIsAtLeast(1, 3, 0)) {
            return copyOfRange(fArr, i, i2);
        }
        if (i2 <= fArr.length) {
            fArr = Arrays.copyOfRange(fArr, i, i2);
            Intrinsics.checkExpressionValueIsNotNull(fArr, "java.util.Arrays.copyOfR…this, fromIndex, toIndex)");
            return fArr;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("toIndex: ");
        stringBuilder.append(i2);
        stringBuilder.append(", size: ");
        stringBuilder.append(fArr.length);
        throw ((Throwable) new IndexOutOfBoundsException(stringBuilder.toString()));
    }

    @InlineOnly
    @JvmName(name = "copyOfRangeInline")
    private static final double[] copyOfRangeInline(@NotNull double[] dArr, int i, int i2) {
        if (PlatformImplementationsKt.apiVersionIsAtLeast(1, 3, 0)) {
            return copyOfRange(dArr, i, i2);
        }
        if (i2 <= dArr.length) {
            dArr = Arrays.copyOfRange(dArr, i, i2);
            Intrinsics.checkExpressionValueIsNotNull(dArr, "java.util.Arrays.copyOfR…this, fromIndex, toIndex)");
            return dArr;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("toIndex: ");
        stringBuilder.append(i2);
        stringBuilder.append(", size: ");
        stringBuilder.append(dArr.length);
        throw ((Throwable) new IndexOutOfBoundsException(stringBuilder.toString()));
    }

    @InlineOnly
    @JvmName(name = "copyOfRangeInline")
    private static final boolean[] copyOfRangeInline(@NotNull boolean[] zArr, int i, int i2) {
        if (PlatformImplementationsKt.apiVersionIsAtLeast(1, 3, 0)) {
            return copyOfRange(zArr, i, i2);
        }
        if (i2 <= zArr.length) {
            zArr = Arrays.copyOfRange(zArr, i, i2);
            Intrinsics.checkExpressionValueIsNotNull(zArr, "java.util.Arrays.copyOfR…this, fromIndex, toIndex)");
            return zArr;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("toIndex: ");
        stringBuilder.append(i2);
        stringBuilder.append(", size: ");
        stringBuilder.append(zArr.length);
        throw ((Throwable) new IndexOutOfBoundsException(stringBuilder.toString()));
    }

    @InlineOnly
    @JvmName(name = "copyOfRangeInline")
    private static final char[] copyOfRangeInline(@NotNull char[] cArr, int i, int i2) {
        if (PlatformImplementationsKt.apiVersionIsAtLeast(1, 3, 0)) {
            return copyOfRange(cArr, i, i2);
        }
        if (i2 <= cArr.length) {
            cArr = Arrays.copyOfRange(cArr, i, i2);
            Intrinsics.checkExpressionValueIsNotNull(cArr, "java.util.Arrays.copyOfR…this, fromIndex, toIndex)");
            return cArr;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("toIndex: ");
        stringBuilder.append(i2);
        stringBuilder.append(", size: ");
        stringBuilder.append(cArr.length);
        throw ((Throwable) new IndexOutOfBoundsException(stringBuilder.toString()));
    }

    @SinceKotlin(version = "1.3")
    @PublishedApi
    @NotNull
    @JvmName(name = "copyOfRange")
    public static final <T> T[] copyOfRange(@NotNull T[] tArr, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(tArr, "receiver$0");
        ArraysKt__ArraysJVMKt.copyOfRangeToIndexCheck(i2, tArr.length);
        tArr = Arrays.copyOfRange(tArr, i, i2);
        Intrinsics.checkExpressionValueIsNotNull(tArr, "java.util.Arrays.copyOfR…this, fromIndex, toIndex)");
        return tArr;
    }

    @SinceKotlin(version = "1.3")
    @PublishedApi
    @NotNull
    @JvmName(name = "copyOfRange")
    public static final byte[] copyOfRange(@NotNull byte[] bArr, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(bArr, "receiver$0");
        ArraysKt__ArraysJVMKt.copyOfRangeToIndexCheck(i2, bArr.length);
        bArr = Arrays.copyOfRange(bArr, i, i2);
        Intrinsics.checkExpressionValueIsNotNull(bArr, "java.util.Arrays.copyOfR…this, fromIndex, toIndex)");
        return bArr;
    }

    @SinceKotlin(version = "1.3")
    @PublishedApi
    @NotNull
    @JvmName(name = "copyOfRange")
    public static final short[] copyOfRange(@NotNull short[] sArr, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(sArr, "receiver$0");
        ArraysKt__ArraysJVMKt.copyOfRangeToIndexCheck(i2, sArr.length);
        sArr = Arrays.copyOfRange(sArr, i, i2);
        Intrinsics.checkExpressionValueIsNotNull(sArr, "java.util.Arrays.copyOfR…this, fromIndex, toIndex)");
        return sArr;
    }

    @SinceKotlin(version = "1.3")
    @PublishedApi
    @NotNull
    @JvmName(name = "copyOfRange")
    public static final int[] copyOfRange(@NotNull int[] iArr, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(iArr, "receiver$0");
        ArraysKt__ArraysJVMKt.copyOfRangeToIndexCheck(i2, iArr.length);
        iArr = Arrays.copyOfRange(iArr, i, i2);
        Intrinsics.checkExpressionValueIsNotNull(iArr, "java.util.Arrays.copyOfR…this, fromIndex, toIndex)");
        return iArr;
    }

    @SinceKotlin(version = "1.3")
    @PublishedApi
    @NotNull
    @JvmName(name = "copyOfRange")
    public static final long[] copyOfRange(@NotNull long[] jArr, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(jArr, "receiver$0");
        ArraysKt__ArraysJVMKt.copyOfRangeToIndexCheck(i2, jArr.length);
        jArr = Arrays.copyOfRange(jArr, i, i2);
        Intrinsics.checkExpressionValueIsNotNull(jArr, "java.util.Arrays.copyOfR…this, fromIndex, toIndex)");
        return jArr;
    }

    @SinceKotlin(version = "1.3")
    @PublishedApi
    @NotNull
    @JvmName(name = "copyOfRange")
    public static final float[] copyOfRange(@NotNull float[] fArr, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(fArr, "receiver$0");
        ArraysKt__ArraysJVMKt.copyOfRangeToIndexCheck(i2, fArr.length);
        fArr = Arrays.copyOfRange(fArr, i, i2);
        Intrinsics.checkExpressionValueIsNotNull(fArr, "java.util.Arrays.copyOfR…this, fromIndex, toIndex)");
        return fArr;
    }

    @SinceKotlin(version = "1.3")
    @PublishedApi
    @NotNull
    @JvmName(name = "copyOfRange")
    public static final double[] copyOfRange(@NotNull double[] dArr, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(dArr, "receiver$0");
        ArraysKt__ArraysJVMKt.copyOfRangeToIndexCheck(i2, dArr.length);
        dArr = Arrays.copyOfRange(dArr, i, i2);
        Intrinsics.checkExpressionValueIsNotNull(dArr, "java.util.Arrays.copyOfR…this, fromIndex, toIndex)");
        return dArr;
    }

    @SinceKotlin(version = "1.3")
    @PublishedApi
    @NotNull
    @JvmName(name = "copyOfRange")
    public static final boolean[] copyOfRange(@NotNull boolean[] zArr, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(zArr, "receiver$0");
        ArraysKt__ArraysJVMKt.copyOfRangeToIndexCheck(i2, zArr.length);
        zArr = Arrays.copyOfRange(zArr, i, i2);
        Intrinsics.checkExpressionValueIsNotNull(zArr, "java.util.Arrays.copyOfR…this, fromIndex, toIndex)");
        return zArr;
    }

    @SinceKotlin(version = "1.3")
    @PublishedApi
    @NotNull
    @JvmName(name = "copyOfRange")
    public static final char[] copyOfRange(@NotNull char[] cArr, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(cArr, "receiver$0");
        ArraysKt__ArraysJVMKt.copyOfRangeToIndexCheck(i2, cArr.length);
        cArr = Arrays.copyOfRange(cArr, i, i2);
        Intrinsics.checkExpressionValueIsNotNull(cArr, "java.util.Arrays.copyOfR…this, fromIndex, toIndex)");
        return cArr;
    }

    public static /* synthetic */ void fill$default(Object[] objArr, Object obj, int i, int i2, int i3, Object obj2) {
        if ((i3 & 2) != null) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = objArr.length;
        }
        fill(objArr, obj, i, i2);
    }

    public static final <T> void fill(@NotNull T[] tArr, T t, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(tArr, "receiver$0");
        Arrays.fill(tArr, i, i2, t);
    }

    public static /* synthetic */ void fill$default(byte[] bArr, byte b, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != null) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = bArr.length;
        }
        fill(bArr, b, i, i2);
    }

    public static final void fill(@NotNull byte[] bArr, byte b, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(bArr, "receiver$0");
        Arrays.fill(bArr, i, i2, b);
    }

    public static /* synthetic */ void fill$default(short[] sArr, short s, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != null) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = sArr.length;
        }
        fill(sArr, s, i, i2);
    }

    public static final void fill(@NotNull short[] sArr, short s, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(sArr, "receiver$0");
        Arrays.fill(sArr, i, i2, s);
    }

    public static /* synthetic */ void fill$default(int[] iArr, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 2) != null) {
            i2 = 0;
        }
        if ((i4 & 4) != 0) {
            i3 = iArr.length;
        }
        fill(iArr, i, i2, i3);
    }

    public static final void fill(@NotNull int[] iArr, int i, int i2, int i3) {
        Intrinsics.checkParameterIsNotNull(iArr, "receiver$0");
        Arrays.fill(iArr, i2, i3, i);
    }

    public static /* synthetic */ void fill$default(long[] jArr, long j, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != null) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = jArr.length;
        }
        fill(jArr, j, i, i2);
    }

    public static final void fill(@NotNull long[] jArr, long j, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(jArr, "receiver$0");
        Arrays.fill(jArr, i, i2, j);
    }

    public static /* synthetic */ void fill$default(float[] fArr, float f, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != null) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = fArr.length;
        }
        fill(fArr, f, i, i2);
    }

    public static final void fill(@NotNull float[] fArr, float f, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(fArr, "receiver$0");
        Arrays.fill(fArr, i, i2, f);
    }

    public static /* synthetic */ void fill$default(double[] dArr, double d, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != null) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = dArr.length;
        }
        fill(dArr, d, i, i2);
    }

    public static final void fill(@NotNull double[] dArr, double d, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(dArr, "receiver$0");
        Arrays.fill(dArr, i, i2, d);
    }

    public static /* synthetic */ void fill$default(boolean[] zArr, boolean z, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != null) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = zArr.length;
        }
        fill(zArr, z, i, i2);
    }

    public static final void fill(@NotNull boolean[] zArr, boolean z, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(zArr, "receiver$0");
        Arrays.fill(zArr, i, i2, z);
    }

    public static /* synthetic */ void fill$default(char[] cArr, char c, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != null) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = cArr.length;
        }
        fill(cArr, c, i, i2);
    }

    public static final void fill(@NotNull char[] cArr, char c, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(cArr, "receiver$0");
        Arrays.fill(cArr, i, i2, c);
    }

    @NotNull
    public static final <T> T[] plus(@NotNull T[] tArr, T t) {
        Intrinsics.checkParameterIsNotNull(tArr, "receiver$0");
        int length = tArr.length;
        tArr = Arrays.copyOf(tArr, length + 1);
        tArr[length] = t;
        Intrinsics.checkExpressionValueIsNotNull(tArr, "result");
        return tArr;
    }

    @NotNull
    public static final byte[] plus(@NotNull byte[] bArr, byte b) {
        Intrinsics.checkParameterIsNotNull(bArr, "receiver$0");
        int length = bArr.length;
        bArr = Arrays.copyOf(bArr, length + 1);
        bArr[length] = b;
        Intrinsics.checkExpressionValueIsNotNull(bArr, "result");
        return bArr;
    }

    @NotNull
    public static final short[] plus(@NotNull short[] sArr, short s) {
        Intrinsics.checkParameterIsNotNull(sArr, "receiver$0");
        int length = sArr.length;
        sArr = Arrays.copyOf(sArr, length + 1);
        sArr[length] = s;
        Intrinsics.checkExpressionValueIsNotNull(sArr, "result");
        return sArr;
    }

    @NotNull
    public static final int[] plus(@NotNull int[] iArr, int i) {
        Intrinsics.checkParameterIsNotNull(iArr, "receiver$0");
        int length = iArr.length;
        iArr = Arrays.copyOf(iArr, length + 1);
        iArr[length] = i;
        Intrinsics.checkExpressionValueIsNotNull(iArr, "result");
        return iArr;
    }

    @NotNull
    public static final long[] plus(@NotNull long[] jArr, long j) {
        Intrinsics.checkParameterIsNotNull(jArr, "receiver$0");
        int length = jArr.length;
        jArr = Arrays.copyOf(jArr, length + 1);
        jArr[length] = j;
        Intrinsics.checkExpressionValueIsNotNull(jArr, "result");
        return jArr;
    }

    @NotNull
    public static final float[] plus(@NotNull float[] fArr, float f) {
        Intrinsics.checkParameterIsNotNull(fArr, "receiver$0");
        int length = fArr.length;
        fArr = Arrays.copyOf(fArr, length + 1);
        fArr[length] = f;
        Intrinsics.checkExpressionValueIsNotNull(fArr, "result");
        return fArr;
    }

    @NotNull
    public static final double[] plus(@NotNull double[] dArr, double d) {
        Intrinsics.checkParameterIsNotNull(dArr, "receiver$0");
        int length = dArr.length;
        dArr = Arrays.copyOf(dArr, length + 1);
        dArr[length] = d;
        Intrinsics.checkExpressionValueIsNotNull(dArr, "result");
        return dArr;
    }

    @NotNull
    public static final boolean[] plus(@NotNull boolean[] zArr, boolean z) {
        Intrinsics.checkParameterIsNotNull(zArr, "receiver$0");
        int length = zArr.length;
        zArr = Arrays.copyOf(zArr, length + 1);
        zArr[length] = z;
        Intrinsics.checkExpressionValueIsNotNull(zArr, "result");
        return zArr;
    }

    @NotNull
    public static final char[] plus(@NotNull char[] cArr, char c) {
        Intrinsics.checkParameterIsNotNull(cArr, "receiver$0");
        int length = cArr.length;
        cArr = Arrays.copyOf(cArr, length + 1);
        cArr[length] = c;
        Intrinsics.checkExpressionValueIsNotNull(cArr, "result");
        return cArr;
    }

    @NotNull
    public static final <T> T[] plus(@NotNull T[] tArr, @NotNull Collection<? extends T> collection) {
        Intrinsics.checkParameterIsNotNull(tArr, "receiver$0");
        Intrinsics.checkParameterIsNotNull(collection, "elements");
        int length = tArr.length;
        tArr = Arrays.copyOf(tArr, collection.size() + length);
        for (Object obj : collection) {
            int i = length + 1;
            tArr[length] = obj;
            length = i;
        }
        Intrinsics.checkExpressionValueIsNotNull(tArr, "result");
        return tArr;
    }

    @NotNull
    public static final byte[] plus(@NotNull byte[] bArr, @NotNull Collection<Byte> collection) {
        Intrinsics.checkParameterIsNotNull(bArr, "receiver$0");
        Intrinsics.checkParameterIsNotNull(collection, "elements");
        int length = bArr.length;
        bArr = Arrays.copyOf(bArr, collection.size() + length);
        for (Number byteValue : collection) {
            int i = length + 1;
            bArr[length] = byteValue.byteValue();
            length = i;
        }
        Intrinsics.checkExpressionValueIsNotNull(bArr, "result");
        return bArr;
    }

    @NotNull
    public static final short[] plus(@NotNull short[] sArr, @NotNull Collection<Short> collection) {
        Intrinsics.checkParameterIsNotNull(sArr, "receiver$0");
        Intrinsics.checkParameterIsNotNull(collection, "elements");
        int length = sArr.length;
        sArr = Arrays.copyOf(sArr, collection.size() + length);
        for (Number shortValue : collection) {
            int i = length + 1;
            sArr[length] = shortValue.shortValue();
            length = i;
        }
        Intrinsics.checkExpressionValueIsNotNull(sArr, "result");
        return sArr;
    }

    @NotNull
    public static final int[] plus(@NotNull int[] iArr, @NotNull Collection<Integer> collection) {
        Intrinsics.checkParameterIsNotNull(iArr, "receiver$0");
        Intrinsics.checkParameterIsNotNull(collection, "elements");
        int length = iArr.length;
        iArr = Arrays.copyOf(iArr, collection.size() + length);
        for (Number intValue : collection) {
            int i = length + 1;
            iArr[length] = intValue.intValue();
            length = i;
        }
        Intrinsics.checkExpressionValueIsNotNull(iArr, "result");
        return iArr;
    }

    @NotNull
    public static final long[] plus(@NotNull long[] jArr, @NotNull Collection<Long> collection) {
        Intrinsics.checkParameterIsNotNull(jArr, "receiver$0");
        Intrinsics.checkParameterIsNotNull(collection, "elements");
        int length = jArr.length;
        jArr = Arrays.copyOf(jArr, collection.size() + length);
        for (Number longValue : collection) {
            int i = length + 1;
            jArr[length] = longValue.longValue();
            length = i;
        }
        Intrinsics.checkExpressionValueIsNotNull(jArr, "result");
        return jArr;
    }

    @NotNull
    public static final float[] plus(@NotNull float[] fArr, @NotNull Collection<Float> collection) {
        Intrinsics.checkParameterIsNotNull(fArr, "receiver$0");
        Intrinsics.checkParameterIsNotNull(collection, "elements");
        int length = fArr.length;
        fArr = Arrays.copyOf(fArr, collection.size() + length);
        for (Number floatValue : collection) {
            int i = length + 1;
            fArr[length] = floatValue.floatValue();
            length = i;
        }
        Intrinsics.checkExpressionValueIsNotNull(fArr, "result");
        return fArr;
    }

    @NotNull
    public static final double[] plus(@NotNull double[] dArr, @NotNull Collection<Double> collection) {
        Intrinsics.checkParameterIsNotNull(dArr, "receiver$0");
        Intrinsics.checkParameterIsNotNull(collection, "elements");
        int length = dArr.length;
        dArr = Arrays.copyOf(dArr, collection.size() + length);
        for (Number doubleValue : collection) {
            int i = length + 1;
            dArr[length] = doubleValue.doubleValue();
            length = i;
        }
        Intrinsics.checkExpressionValueIsNotNull(dArr, "result");
        return dArr;
    }

    @NotNull
    public static final boolean[] plus(@NotNull boolean[] zArr, @NotNull Collection<Boolean> collection) {
        Intrinsics.checkParameterIsNotNull(zArr, "receiver$0");
        Intrinsics.checkParameterIsNotNull(collection, "elements");
        int length = zArr.length;
        zArr = Arrays.copyOf(zArr, collection.size() + length);
        for (Boolean booleanValue : collection) {
            int i = length + 1;
            zArr[length] = booleanValue.booleanValue();
            length = i;
        }
        Intrinsics.checkExpressionValueIsNotNull(zArr, "result");
        return zArr;
    }

    @NotNull
    public static final char[] plus(@NotNull char[] cArr, @NotNull Collection<Character> collection) {
        Intrinsics.checkParameterIsNotNull(cArr, "receiver$0");
        Intrinsics.checkParameterIsNotNull(collection, "elements");
        int length = cArr.length;
        cArr = Arrays.copyOf(cArr, collection.size() + length);
        for (Character charValue : collection) {
            int i = length + 1;
            cArr[length] = charValue.charValue();
            length = i;
        }
        Intrinsics.checkExpressionValueIsNotNull(cArr, "result");
        return cArr;
    }

    @NotNull
    public static final <T> T[] plus(@NotNull T[] tArr, @NotNull T[] tArr2) {
        Intrinsics.checkParameterIsNotNull(tArr, "receiver$0");
        Intrinsics.checkParameterIsNotNull(tArr2, "elements");
        int length = tArr.length;
        int length2 = tArr2.length;
        tArr = Arrays.copyOf(tArr, length + length2);
        System.arraycopy(tArr2, 0, tArr, length, length2);
        Intrinsics.checkExpressionValueIsNotNull(tArr, "result");
        return tArr;
    }

    @NotNull
    public static final byte[] plus(@NotNull byte[] bArr, @NotNull byte[] bArr2) {
        Intrinsics.checkParameterIsNotNull(bArr, "receiver$0");
        Intrinsics.checkParameterIsNotNull(bArr2, "elements");
        int length = bArr.length;
        int length2 = bArr2.length;
        bArr = Arrays.copyOf(bArr, length + length2);
        System.arraycopy(bArr2, 0, bArr, length, length2);
        Intrinsics.checkExpressionValueIsNotNull(bArr, "result");
        return bArr;
    }

    @NotNull
    public static final short[] plus(@NotNull short[] sArr, @NotNull short[] sArr2) {
        Intrinsics.checkParameterIsNotNull(sArr, "receiver$0");
        Intrinsics.checkParameterIsNotNull(sArr2, "elements");
        int length = sArr.length;
        int length2 = sArr2.length;
        sArr = Arrays.copyOf(sArr, length + length2);
        System.arraycopy(sArr2, 0, sArr, length, length2);
        Intrinsics.checkExpressionValueIsNotNull(sArr, "result");
        return sArr;
    }

    @NotNull
    public static final int[] plus(@NotNull int[] iArr, @NotNull int[] iArr2) {
        Intrinsics.checkParameterIsNotNull(iArr, "receiver$0");
        Intrinsics.checkParameterIsNotNull(iArr2, "elements");
        int length = iArr.length;
        int length2 = iArr2.length;
        iArr = Arrays.copyOf(iArr, length + length2);
        System.arraycopy(iArr2, 0, iArr, length, length2);
        Intrinsics.checkExpressionValueIsNotNull(iArr, "result");
        return iArr;
    }

    @NotNull
    public static final long[] plus(@NotNull long[] jArr, @NotNull long[] jArr2) {
        Intrinsics.checkParameterIsNotNull(jArr, "receiver$0");
        Intrinsics.checkParameterIsNotNull(jArr2, "elements");
        int length = jArr.length;
        int length2 = jArr2.length;
        jArr = Arrays.copyOf(jArr, length + length2);
        System.arraycopy(jArr2, 0, jArr, length, length2);
        Intrinsics.checkExpressionValueIsNotNull(jArr, "result");
        return jArr;
    }

    @NotNull
    public static final float[] plus(@NotNull float[] fArr, @NotNull float[] fArr2) {
        Intrinsics.checkParameterIsNotNull(fArr, "receiver$0");
        Intrinsics.checkParameterIsNotNull(fArr2, "elements");
        int length = fArr.length;
        int length2 = fArr2.length;
        fArr = Arrays.copyOf(fArr, length + length2);
        System.arraycopy(fArr2, 0, fArr, length, length2);
        Intrinsics.checkExpressionValueIsNotNull(fArr, "result");
        return fArr;
    }

    @NotNull
    public static final double[] plus(@NotNull double[] dArr, @NotNull double[] dArr2) {
        Intrinsics.checkParameterIsNotNull(dArr, "receiver$0");
        Intrinsics.checkParameterIsNotNull(dArr2, "elements");
        int length = dArr.length;
        int length2 = dArr2.length;
        dArr = Arrays.copyOf(dArr, length + length2);
        System.arraycopy(dArr2, 0, dArr, length, length2);
        Intrinsics.checkExpressionValueIsNotNull(dArr, "result");
        return dArr;
    }

    @NotNull
    public static final boolean[] plus(@NotNull boolean[] zArr, @NotNull boolean[] zArr2) {
        Intrinsics.checkParameterIsNotNull(zArr, "receiver$0");
        Intrinsics.checkParameterIsNotNull(zArr2, "elements");
        int length = zArr.length;
        int length2 = zArr2.length;
        zArr = Arrays.copyOf(zArr, length + length2);
        System.arraycopy(zArr2, 0, zArr, length, length2);
        Intrinsics.checkExpressionValueIsNotNull(zArr, "result");
        return zArr;
    }

    @NotNull
    public static final char[] plus(@NotNull char[] cArr, @NotNull char[] cArr2) {
        Intrinsics.checkParameterIsNotNull(cArr, "receiver$0");
        Intrinsics.checkParameterIsNotNull(cArr2, "elements");
        int length = cArr.length;
        int length2 = cArr2.length;
        cArr = Arrays.copyOf(cArr, length + length2);
        System.arraycopy(cArr2, 0, cArr, length, length2);
        Intrinsics.checkExpressionValueIsNotNull(cArr, "result");
        return cArr;
    }

    @InlineOnly
    private static final <T> T[] plusElement(@NotNull T[] tArr, T t) {
        return plus((Object[]) tArr, (Object) t);
    }

    public static final void sort(@NotNull int[] iArr) {
        Intrinsics.checkParameterIsNotNull(iArr, "receiver$0");
        if (iArr.length > 1) {
            Arrays.sort(iArr);
        }
    }

    public static final void sort(@NotNull long[] jArr) {
        Intrinsics.checkParameterIsNotNull(jArr, "receiver$0");
        if (jArr.length > 1) {
            Arrays.sort(jArr);
        }
    }

    public static final void sort(@NotNull byte[] bArr) {
        Intrinsics.checkParameterIsNotNull(bArr, "receiver$0");
        if (bArr.length > 1) {
            Arrays.sort(bArr);
        }
    }

    public static final void sort(@NotNull short[] sArr) {
        Intrinsics.checkParameterIsNotNull(sArr, "receiver$0");
        if (sArr.length > 1) {
            Arrays.sort(sArr);
        }
    }

    public static final void sort(@NotNull double[] dArr) {
        Intrinsics.checkParameterIsNotNull(dArr, "receiver$0");
        if (dArr.length > 1) {
            Arrays.sort(dArr);
        }
    }

    public static final void sort(@NotNull float[] fArr) {
        Intrinsics.checkParameterIsNotNull(fArr, "receiver$0");
        if (fArr.length > 1) {
            Arrays.sort(fArr);
        }
    }

    public static final void sort(@NotNull char[] cArr) {
        Intrinsics.checkParameterIsNotNull(cArr, "receiver$0");
        if (cArr.length > 1) {
            Arrays.sort(cArr);
        }
    }

    @InlineOnly
    private static final <T extends Comparable<? super T>> void sort(@NotNull T[] tArr) {
        if (tArr != null) {
            sort((Object[]) tArr);
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
    }

    public static final <T> void sort(@NotNull T[] tArr) {
        Intrinsics.checkParameterIsNotNull(tArr, "receiver$0");
        if (tArr.length > 1) {
            Arrays.sort(tArr);
        }
    }

    public static /* synthetic */ void sort$default(Object[] objArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != null) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = objArr.length;
        }
        sort(objArr, i, i2);
    }

    public static final <T> void sort(@NotNull T[] tArr, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(tArr, "receiver$0");
        Arrays.sort(tArr, i, i2);
    }

    public static /* synthetic */ void sort$default(byte[] bArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != null) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = bArr.length;
        }
        sort(bArr, i, i2);
    }

    public static final void sort(@NotNull byte[] bArr, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(bArr, "receiver$0");
        Arrays.sort(bArr, i, i2);
    }

    public static /* synthetic */ void sort$default(short[] sArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != null) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = sArr.length;
        }
        sort(sArr, i, i2);
    }

    public static final void sort(@NotNull short[] sArr, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(sArr, "receiver$0");
        Arrays.sort(sArr, i, i2);
    }

    public static /* synthetic */ void sort$default(int[] iArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != null) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = iArr.length;
        }
        sort(iArr, i, i2);
    }

    public static final void sort(@NotNull int[] iArr, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(iArr, "receiver$0");
        Arrays.sort(iArr, i, i2);
    }

    public static /* synthetic */ void sort$default(long[] jArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != null) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = jArr.length;
        }
        sort(jArr, i, i2);
    }

    public static final void sort(@NotNull long[] jArr, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(jArr, "receiver$0");
        Arrays.sort(jArr, i, i2);
    }

    public static /* synthetic */ void sort$default(float[] fArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != null) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = fArr.length;
        }
        sort(fArr, i, i2);
    }

    public static final void sort(@NotNull float[] fArr, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(fArr, "receiver$0");
        Arrays.sort(fArr, i, i2);
    }

    public static /* synthetic */ void sort$default(double[] dArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != null) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = dArr.length;
        }
        sort(dArr, i, i2);
    }

    public static final void sort(@NotNull double[] dArr, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(dArr, "receiver$0");
        Arrays.sort(dArr, i, i2);
    }

    public static /* synthetic */ void sort$default(char[] cArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != null) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = cArr.length;
        }
        sort(cArr, i, i2);
    }

    public static final void sort(@NotNull char[] cArr, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(cArr, "receiver$0");
        Arrays.sort(cArr, i, i2);
    }

    public static final <T> void sortWith(@NotNull T[] tArr, @NotNull Comparator<? super T> comparator) {
        Intrinsics.checkParameterIsNotNull(tArr, "receiver$0");
        Intrinsics.checkParameterIsNotNull(comparator, "comparator");
        if (tArr.length > 1) {
            Arrays.sort(tArr, comparator);
        }
    }

    public static /* synthetic */ void sortWith$default(Object[] objArr, Comparator comparator, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != null) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = objArr.length;
        }
        sortWith(objArr, comparator, i, i2);
    }

    public static final <T> void sortWith(@NotNull T[] tArr, @NotNull Comparator<? super T> comparator, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(tArr, "receiver$0");
        Intrinsics.checkParameterIsNotNull(comparator, "comparator");
        Arrays.sort(tArr, i, i2, comparator);
    }

    @NotNull
    public static final Byte[] toTypedArray(@NotNull byte[] bArr) {
        Intrinsics.checkParameterIsNotNull(bArr, "receiver$0");
        Byte[] bArr2 = new Byte[bArr.length];
        int length = bArr.length;
        for (int i = 0; i < length; i++) {
            bArr2[i] = Byte.valueOf(bArr[i]);
        }
        return bArr2;
    }

    @NotNull
    public static final Short[] toTypedArray(@NotNull short[] sArr) {
        Intrinsics.checkParameterIsNotNull(sArr, "receiver$0");
        Short[] shArr = new Short[sArr.length];
        int length = sArr.length;
        for (int i = 0; i < length; i++) {
            shArr[i] = Short.valueOf(sArr[i]);
        }
        return shArr;
    }

    @NotNull
    public static final Integer[] toTypedArray(@NotNull int[] iArr) {
        Intrinsics.checkParameterIsNotNull(iArr, "receiver$0");
        Integer[] numArr = new Integer[iArr.length];
        int length = iArr.length;
        for (int i = 0; i < length; i++) {
            numArr[i] = Integer.valueOf(iArr[i]);
        }
        return numArr;
    }

    @NotNull
    public static final Long[] toTypedArray(@NotNull long[] jArr) {
        Intrinsics.checkParameterIsNotNull(jArr, "receiver$0");
        Long[] lArr = new Long[jArr.length];
        int length = jArr.length;
        for (int i = 0; i < length; i++) {
            lArr[i] = Long.valueOf(jArr[i]);
        }
        return lArr;
    }

    @NotNull
    public static final Float[] toTypedArray(@NotNull float[] fArr) {
        Intrinsics.checkParameterIsNotNull(fArr, "receiver$0");
        Float[] fArr2 = new Float[fArr.length];
        int length = fArr.length;
        for (int i = 0; i < length; i++) {
            fArr2[i] = Float.valueOf(fArr[i]);
        }
        return fArr2;
    }

    @NotNull
    public static final Double[] toTypedArray(@NotNull double[] dArr) {
        Intrinsics.checkParameterIsNotNull(dArr, "receiver$0");
        Double[] dArr2 = new Double[dArr.length];
        int length = dArr.length;
        for (int i = 0; i < length; i++) {
            dArr2[i] = Double.valueOf(dArr[i]);
        }
        return dArr2;
    }

    @NotNull
    public static final Boolean[] toTypedArray(@NotNull boolean[] zArr) {
        Intrinsics.checkParameterIsNotNull(zArr, "receiver$0");
        Boolean[] boolArr = new Boolean[zArr.length];
        int length = zArr.length;
        for (int i = 0; i < length; i++) {
            boolArr[i] = Boolean.valueOf(zArr[i]);
        }
        return boolArr;
    }

    @NotNull
    public static final Character[] toTypedArray(@NotNull char[] cArr) {
        Intrinsics.checkParameterIsNotNull(cArr, "receiver$0");
        Character[] chArr = new Character[cArr.length];
        int length = cArr.length;
        for (int i = 0; i < length; i++) {
            chArr[i] = Character.valueOf(cArr[i]);
        }
        return chArr;
    }

    @NotNull
    public static final <T extends Comparable<? super T>> SortedSet<T> toSortedSet(@NotNull T[] tArr) {
        Intrinsics.checkParameterIsNotNull(tArr, "receiver$0");
        return (SortedSet) ArraysKt___ArraysKt.toCollection((Object[]) tArr, (Collection) new TreeSet());
    }

    @NotNull
    public static final SortedSet<Byte> toSortedSet(@NotNull byte[] bArr) {
        Intrinsics.checkParameterIsNotNull(bArr, "receiver$0");
        return (SortedSet) ArraysKt___ArraysKt.toCollection(bArr, (Collection) new TreeSet());
    }

    @NotNull
    public static final SortedSet<Short> toSortedSet(@NotNull short[] sArr) {
        Intrinsics.checkParameterIsNotNull(sArr, "receiver$0");
        return (SortedSet) ArraysKt___ArraysKt.toCollection(sArr, (Collection) new TreeSet());
    }

    @NotNull
    public static final SortedSet<Integer> toSortedSet(@NotNull int[] iArr) {
        Intrinsics.checkParameterIsNotNull(iArr, "receiver$0");
        return (SortedSet) ArraysKt___ArraysKt.toCollection(iArr, (Collection) new TreeSet());
    }

    @NotNull
    public static final SortedSet<Long> toSortedSet(@NotNull long[] jArr) {
        Intrinsics.checkParameterIsNotNull(jArr, "receiver$0");
        return (SortedSet) ArraysKt___ArraysKt.toCollection(jArr, (Collection) new TreeSet());
    }

    @NotNull
    public static final SortedSet<Float> toSortedSet(@NotNull float[] fArr) {
        Intrinsics.checkParameterIsNotNull(fArr, "receiver$0");
        return (SortedSet) ArraysKt___ArraysKt.toCollection(fArr, (Collection) new TreeSet());
    }

    @NotNull
    public static final SortedSet<Double> toSortedSet(@NotNull double[] dArr) {
        Intrinsics.checkParameterIsNotNull(dArr, "receiver$0");
        return (SortedSet) ArraysKt___ArraysKt.toCollection(dArr, (Collection) new TreeSet());
    }

    @NotNull
    public static final SortedSet<Boolean> toSortedSet(@NotNull boolean[] zArr) {
        Intrinsics.checkParameterIsNotNull(zArr, "receiver$0");
        return (SortedSet) ArraysKt___ArraysKt.toCollection(zArr, (Collection) new TreeSet());
    }

    @NotNull
    public static final SortedSet<Character> toSortedSet(@NotNull char[] cArr) {
        Intrinsics.checkParameterIsNotNull(cArr, "receiver$0");
        return (SortedSet) ArraysKt___ArraysKt.toCollection(cArr, (Collection) new TreeSet());
    }

    @NotNull
    public static final <T> SortedSet<T> toSortedSet(@NotNull T[] tArr, @NotNull Comparator<? super T> comparator) {
        Intrinsics.checkParameterIsNotNull(tArr, "receiver$0");
        Intrinsics.checkParameterIsNotNull(comparator, "comparator");
        return (SortedSet) ArraysKt___ArraysKt.toCollection((Object[]) tArr, (Collection) new TreeSet(comparator));
    }
}
