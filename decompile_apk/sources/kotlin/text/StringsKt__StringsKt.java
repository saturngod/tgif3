package kotlin.text;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ReplaceWith;
import kotlin.SinceKotlin;
import kotlin.TypeCastException;
import kotlin.collections.CharIterator;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.sequences.Sequence;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.HTTP;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000|\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\r\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\f\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0019\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\b\n\u0002\u0010\u0011\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u001b\u001a\u001c\u0010\t\u001a\u00020\n*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u00022\b\b\u0002\u0010\f\u001a\u00020\r\u001a\u001c\u0010\u000e\u001a\u00020\n*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u00022\b\b\u0002\u0010\f\u001a\u00020\r\u001a\u001f\u0010\u000f\u001a\u00020\r*\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\f\u001a\u00020\rH\u0002\u001a\u001f\u0010\u000f\u001a\u00020\r*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u00022\b\b\u0002\u0010\f\u001a\u00020\rH\u0002\u001a\u0015\u0010\u000f\u001a\u00020\r*\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u0013H\n\u001a\u001c\u0010\u0014\u001a\u00020\r*\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\f\u001a\u00020\r\u001a\u001c\u0010\u0014\u001a\u00020\r*\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u00022\b\b\u0002\u0010\f\u001a\u00020\r\u001a:\u0010\u0016\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\n\u0018\u00010\u0017*\u00020\u00022\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\n0\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\r\u001aE\u0010\u0016\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\n\u0018\u00010\u0017*\u00020\u00022\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\n0\u00192\u0006\u0010\u001a\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u001b\u001a\u00020\rH\u0002¢\u0006\u0002\b\u001c\u001a:\u0010\u001d\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\n\u0018\u00010\u0017*\u00020\u00022\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\n0\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\r\u001a\u0012\u0010\u001e\u001a\u00020\r*\u00020\u00022\u0006\u0010\u001f\u001a\u00020\u0006\u001a4\u0010 \u001a\u0002H!\"\f\b\u0000\u0010\"*\u00020\u0002*\u0002H!\"\u0004\b\u0001\u0010!*\u0002H\"2\f\u0010#\u001a\b\u0012\u0004\u0012\u0002H!0$H\b¢\u0006\u0002\u0010%\u001a4\u0010&\u001a\u0002H!\"\f\b\u0000\u0010\"*\u00020\u0002*\u0002H!\"\u0004\b\u0001\u0010!*\u0002H\"2\f\u0010#\u001a\b\u0012\u0004\u0012\u0002H!0$H\b¢\u0006\u0002\u0010%\u001a&\u0010'\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u001a\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\r\u001a;\u0010'\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u00062\u0006\u0010(\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u001b\u001a\u00020\rH\u0002¢\u0006\u0002\b)\u001a&\u0010'\u001a\u00020\u0006*\u00020\u00022\u0006\u0010*\u001a\u00020\n2\b\b\u0002\u0010\u001a\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\r\u001a&\u0010+\u001a\u00020\u0006*\u00020\u00022\u0006\u0010,\u001a\u00020-2\b\b\u0002\u0010\u001a\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\r\u001a,\u0010+\u001a\u00020\u0006*\u00020\u00022\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\n0\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\r\u001a\r\u0010.\u001a\u00020\r*\u00020\u0002H\b\u001a\r\u0010/\u001a\u00020\r*\u00020\u0002H\b\u001a\r\u00100\u001a\u00020\r*\u00020\u0002H\b\u001a \u00101\u001a\u00020\r*\u0004\u0018\u00010\u0002H\b\u0002\u000e\n\f\b\u0000\u0012\u0002\u0018\u0001\u001a\u0004\b\u0003\u0010\u0000\u001a \u00102\u001a\u00020\r*\u0004\u0018\u00010\u0002H\b\u0002\u000e\n\f\b\u0000\u0012\u0002\u0018\u0001\u001a\u0004\b\u0003\u0010\u0000\u001a\r\u00103\u001a\u000204*\u00020\u0002H\u0002\u001a&\u00105\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u001a\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\r\u001a&\u00105\u001a\u00020\u0006*\u00020\u00022\u0006\u0010*\u001a\u00020\n2\b\b\u0002\u0010\u001a\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\r\u001a&\u00106\u001a\u00020\u0006*\u00020\u00022\u0006\u0010,\u001a\u00020-2\b\b\u0002\u0010\u001a\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\r\u001a,\u00106\u001a\u00020\u0006*\u00020\u00022\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\n0\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\r\u001a\u0010\u00107\u001a\b\u0012\u0004\u0012\u00020\n08*\u00020\u0002\u001a\u0010\u00109\u001a\b\u0012\u0004\u0012\u00020\n0:*\u00020\u0002\u001a\u0015\u0010;\u001a\u00020\r*\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u0013H\f\u001a\u000f\u0010<\u001a\u00020\n*\u0004\u0018\u00010\nH\b\u001a\u001c\u0010=\u001a\u00020\u0002*\u00020\u00022\u0006\u0010>\u001a\u00020\u00062\b\b\u0002\u0010?\u001a\u00020\u0011\u001a\u001c\u0010=\u001a\u00020\n*\u00020\n2\u0006\u0010>\u001a\u00020\u00062\b\b\u0002\u0010?\u001a\u00020\u0011\u001a\u001c\u0010@\u001a\u00020\u0002*\u00020\u00022\u0006\u0010>\u001a\u00020\u00062\b\b\u0002\u0010?\u001a\u00020\u0011\u001a\u001c\u0010@\u001a\u00020\n*\u00020\n2\u0006\u0010>\u001a\u00020\u00062\b\b\u0002\u0010?\u001a\u00020\u0011\u001aG\u0010A\u001a\b\u0012\u0004\u0012\u00020\u000108*\u00020\u00022\u000e\u0010B\u001a\n\u0012\u0006\b\u0001\u0012\u00020\n0C2\b\b\u0002\u0010\u001a\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010D\u001a\u00020\u0006H\u0002¢\u0006\u0004\bE\u0010F\u001a=\u0010A\u001a\b\u0012\u0004\u0012\u00020\u000108*\u00020\u00022\u0006\u0010B\u001a\u00020-2\b\b\u0002\u0010\u001a\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010D\u001a\u00020\u0006H\u0002¢\u0006\u0002\bE\u001a4\u0010G\u001a\u00020\r*\u00020\u00022\u0006\u0010H\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u00022\u0006\u0010I\u001a\u00020\u00062\u0006\u0010>\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\rH\u0000\u001a\u0012\u0010J\u001a\u00020\u0002*\u00020\u00022\u0006\u0010K\u001a\u00020\u0002\u001a\u0012\u0010J\u001a\u00020\n*\u00020\n2\u0006\u0010K\u001a\u00020\u0002\u001a\u001a\u0010L\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u00062\u0006\u0010(\u001a\u00020\u0006\u001a\u0012\u0010L\u001a\u00020\u0002*\u00020\u00022\u0006\u0010M\u001a\u00020\u0001\u001a\u001d\u0010L\u001a\u00020\n*\u00020\n2\u0006\u0010\u001a\u001a\u00020\u00062\u0006\u0010(\u001a\u00020\u0006H\b\u001a\u0015\u0010L\u001a\u00020\n*\u00020\n2\u0006\u0010M\u001a\u00020\u0001H\b\u001a\u0012\u0010N\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u0002\u001a\u0012\u0010N\u001a\u00020\n*\u00020\n2\u0006\u0010\u0015\u001a\u00020\u0002\u001a\u0012\u0010O\u001a\u00020\u0002*\u00020\u00022\u0006\u0010P\u001a\u00020\u0002\u001a\u001a\u0010O\u001a\u00020\u0002*\u00020\u00022\u0006\u0010K\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u0002\u001a\u0012\u0010O\u001a\u00020\n*\u00020\n2\u0006\u0010P\u001a\u00020\u0002\u001a\u001a\u0010O\u001a\u00020\n*\u00020\n2\u0006\u0010K\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u0002\u001a+\u0010Q\u001a\u00020\n*\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u00132\u0014\b\b\u0010R\u001a\u000e\u0012\u0004\u0012\u00020T\u0012\u0004\u0012\u00020\u00020SH\b\u001a\u001d\u0010Q\u001a\u00020\n*\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010U\u001a\u00020\nH\b\u001a$\u0010V\u001a\u00020\n*\u00020\n2\u0006\u0010P\u001a\u00020\u00112\u0006\u0010U\u001a\u00020\n2\b\b\u0002\u0010W\u001a\u00020\n\u001a$\u0010V\u001a\u00020\n*\u00020\n2\u0006\u0010P\u001a\u00020\n2\u0006\u0010U\u001a\u00020\n2\b\b\u0002\u0010W\u001a\u00020\n\u001a$\u0010X\u001a\u00020\n*\u00020\n2\u0006\u0010P\u001a\u00020\u00112\u0006\u0010U\u001a\u00020\n2\b\b\u0002\u0010W\u001a\u00020\n\u001a$\u0010X\u001a\u00020\n*\u00020\n2\u0006\u0010P\u001a\u00020\n2\u0006\u0010U\u001a\u00020\n2\b\b\u0002\u0010W\u001a\u00020\n\u001a$\u0010Y\u001a\u00020\n*\u00020\n2\u0006\u0010P\u001a\u00020\u00112\u0006\u0010U\u001a\u00020\n2\b\b\u0002\u0010W\u001a\u00020\n\u001a$\u0010Y\u001a\u00020\n*\u00020\n2\u0006\u0010P\u001a\u00020\n2\u0006\u0010U\u001a\u00020\n2\b\b\u0002\u0010W\u001a\u00020\n\u001a$\u0010Z\u001a\u00020\n*\u00020\n2\u0006\u0010P\u001a\u00020\u00112\u0006\u0010U\u001a\u00020\n2\b\b\u0002\u0010W\u001a\u00020\n\u001a$\u0010Z\u001a\u00020\n*\u00020\n2\u0006\u0010P\u001a\u00020\n2\u0006\u0010U\u001a\u00020\n2\b\b\u0002\u0010W\u001a\u00020\n\u001a\u001d\u0010[\u001a\u00020\n*\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010U\u001a\u00020\nH\b\u001a\"\u0010\\\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u00062\u0006\u0010(\u001a\u00020\u00062\u0006\u0010U\u001a\u00020\u0002\u001a\u001a\u0010\\\u001a\u00020\u0002*\u00020\u00022\u0006\u0010M\u001a\u00020\u00012\u0006\u0010U\u001a\u00020\u0002\u001a%\u0010\\\u001a\u00020\n*\u00020\n2\u0006\u0010\u001a\u001a\u00020\u00062\u0006\u0010(\u001a\u00020\u00062\u0006\u0010U\u001a\u00020\u0002H\b\u001a\u001d\u0010\\\u001a\u00020\n*\u00020\n2\u0006\u0010M\u001a\u00020\u00012\u0006\u0010U\u001a\u00020\u0002H\b\u001a=\u0010]\u001a\b\u0012\u0004\u0012\u00020\n0:*\u00020\u00022\u0012\u0010B\u001a\n\u0012\u0006\b\u0001\u0012\u00020\n0C\"\u00020\n2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010D\u001a\u00020\u0006¢\u0006\u0002\u0010^\u001a0\u0010]\u001a\b\u0012\u0004\u0012\u00020\n0:*\u00020\u00022\n\u0010B\u001a\u00020-\"\u00020\u00112\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010D\u001a\u00020\u0006\u001a/\u0010]\u001a\b\u0012\u0004\u0012\u00020\n0:*\u00020\u00022\u0006\u0010P\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010D\u001a\u00020\u0006H\u0002¢\u0006\u0002\b_\u001a%\u0010]\u001a\b\u0012\u0004\u0012\u00020\n0:*\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010D\u001a\u00020\u0006H\b\u001a=\u0010`\u001a\b\u0012\u0004\u0012\u00020\n08*\u00020\u00022\u0012\u0010B\u001a\n\u0012\u0006\b\u0001\u0012\u00020\n0C\"\u00020\n2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010D\u001a\u00020\u0006¢\u0006\u0002\u0010a\u001a0\u0010`\u001a\b\u0012\u0004\u0012\u00020\n08*\u00020\u00022\n\u0010B\u001a\u00020-\"\u00020\u00112\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010D\u001a\u00020\u0006\u001a\u001c\u0010b\u001a\u00020\r*\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\f\u001a\u00020\r\u001a\u001c\u0010b\u001a\u00020\r*\u00020\u00022\u0006\u0010K\u001a\u00020\u00022\b\b\u0002\u0010\f\u001a\u00020\r\u001a$\u0010b\u001a\u00020\r*\u00020\u00022\u0006\u0010K\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\r\u001a\u0012\u0010c\u001a\u00020\u0002*\u00020\u00022\u0006\u0010M\u001a\u00020\u0001\u001a\u001d\u0010c\u001a\u00020\u0002*\u00020\n2\u0006\u0010d\u001a\u00020\u00062\u0006\u0010e\u001a\u00020\u0006H\b\u001a\u001f\u0010f\u001a\u00020\n*\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u00062\b\b\u0002\u0010(\u001a\u00020\u0006H\b\u001a\u0012\u0010f\u001a\u00020\n*\u00020\u00022\u0006\u0010M\u001a\u00020\u0001\u001a\u0012\u0010f\u001a\u00020\n*\u00020\n2\u0006\u0010M\u001a\u00020\u0001\u001a\u001c\u0010g\u001a\u00020\n*\u00020\n2\u0006\u0010P\u001a\u00020\u00112\b\b\u0002\u0010W\u001a\u00020\n\u001a\u001c\u0010g\u001a\u00020\n*\u00020\n2\u0006\u0010P\u001a\u00020\n2\b\b\u0002\u0010W\u001a\u00020\n\u001a\u001c\u0010h\u001a\u00020\n*\u00020\n2\u0006\u0010P\u001a\u00020\u00112\b\b\u0002\u0010W\u001a\u00020\n\u001a\u001c\u0010h\u001a\u00020\n*\u00020\n2\u0006\u0010P\u001a\u00020\n2\b\b\u0002\u0010W\u001a\u00020\n\u001a\u001c\u0010i\u001a\u00020\n*\u00020\n2\u0006\u0010P\u001a\u00020\u00112\b\b\u0002\u0010W\u001a\u00020\n\u001a\u001c\u0010i\u001a\u00020\n*\u00020\n2\u0006\u0010P\u001a\u00020\n2\b\b\u0002\u0010W\u001a\u00020\n\u001a\u001c\u0010j\u001a\u00020\n*\u00020\n2\u0006\u0010P\u001a\u00020\u00112\b\b\u0002\u0010W\u001a\u00020\n\u001a\u001c\u0010j\u001a\u00020\n*\u00020\n2\u0006\u0010P\u001a\u00020\n2\b\b\u0002\u0010W\u001a\u00020\n\u001a\n\u0010k\u001a\u00020\u0002*\u00020\u0002\u001a!\u0010k\u001a\u00020\u0002*\u00020\u00022\u0012\u0010l\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\r0SH\b\u001a\u0016\u0010k\u001a\u00020\u0002*\u00020\u00022\n\u0010,\u001a\u00020-\"\u00020\u0011\u001a\r\u0010k\u001a\u00020\n*\u00020\nH\b\u001a!\u0010k\u001a\u00020\n*\u00020\n2\u0012\u0010l\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\r0SH\b\u001a\u0016\u0010k\u001a\u00020\n*\u00020\n2\n\u0010,\u001a\u00020-\"\u00020\u0011\u001a\n\u0010m\u001a\u00020\u0002*\u00020\u0002\u001a!\u0010m\u001a\u00020\u0002*\u00020\u00022\u0012\u0010l\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\r0SH\b\u001a\u0016\u0010m\u001a\u00020\u0002*\u00020\u00022\n\u0010,\u001a\u00020-\"\u00020\u0011\u001a\r\u0010m\u001a\u00020\n*\u00020\nH\b\u001a!\u0010m\u001a\u00020\n*\u00020\n2\u0012\u0010l\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\r0SH\b\u001a\u0016\u0010m\u001a\u00020\n*\u00020\n2\n\u0010,\u001a\u00020-\"\u00020\u0011\u001a\n\u0010n\u001a\u00020\u0002*\u00020\u0002\u001a!\u0010n\u001a\u00020\u0002*\u00020\u00022\u0012\u0010l\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\r0SH\b\u001a\u0016\u0010n\u001a\u00020\u0002*\u00020\u00022\n\u0010,\u001a\u00020-\"\u00020\u0011\u001a\r\u0010n\u001a\u00020\n*\u00020\nH\b\u001a!\u0010n\u001a\u00020\n*\u00020\n2\u0012\u0010l\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\r0SH\b\u001a\u0016\u0010n\u001a\u00020\n*\u00020\n2\n\u0010,\u001a\u00020-\"\u00020\u0011\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0015\u0010\u0005\u001a\u00020\u0006*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006o"}, d2 = {"indices", "Lkotlin/ranges/IntRange;", "", "getIndices", "(Ljava/lang/CharSequence;)Lkotlin/ranges/IntRange;", "lastIndex", "", "getLastIndex", "(Ljava/lang/CharSequence;)I", "commonPrefixWith", "", "other", "ignoreCase", "", "commonSuffixWith", "contains", "char", "", "regex", "Lkotlin/text/Regex;", "endsWith", "suffix", "findAnyOf", "Lkotlin/Pair;", "strings", "", "startIndex", "last", "findAnyOf$StringsKt__StringsKt", "findLastAnyOf", "hasSurrogatePairAt", "index", "ifBlank", "R", "C", "defaultValue", "Lkotlin/Function0;", "(Ljava/lang/CharSequence;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "ifEmpty", "indexOf", "endIndex", "indexOf$StringsKt__StringsKt", "string", "indexOfAny", "chars", "", "isEmpty", "isNotBlank", "isNotEmpty", "isNullOrBlank", "isNullOrEmpty", "iterator", "Lkotlin/collections/CharIterator;", "lastIndexOf", "lastIndexOfAny", "lineSequence", "Lkotlin/sequences/Sequence;", "lines", "", "matches", "orEmpty", "padEnd", "length", "padChar", "padStart", "rangesDelimitedBy", "delimiters", "", "limit", "rangesDelimitedBy$StringsKt__StringsKt", "(Ljava/lang/CharSequence;[Ljava/lang/String;IZI)Lkotlin/sequences/Sequence;", "regionMatchesImpl", "thisOffset", "otherOffset", "removePrefix", "prefix", "removeRange", "range", "removeSuffix", "removeSurrounding", "delimiter", "replace", "transform", "Lkotlin/Function1;", "Lkotlin/text/MatchResult;", "replacement", "replaceAfter", "missingDelimiterValue", "replaceAfterLast", "replaceBefore", "replaceBeforeLast", "replaceFirst", "replaceRange", "split", "(Ljava/lang/CharSequence;[Ljava/lang/String;ZI)Ljava/util/List;", "split$StringsKt__StringsKt", "splitToSequence", "(Ljava/lang/CharSequence;[Ljava/lang/String;ZI)Lkotlin/sequences/Sequence;", "startsWith", "subSequence", "start", "end", "substring", "substringAfter", "substringAfterLast", "substringBefore", "substringBeforeLast", "trim", "predicate", "trimEnd", "trimStart", "kotlin-stdlib"}, k = 5, mv = {1, 1, 13}, xi = 1, xs = "kotlin/text/StringsKt")
/* compiled from: Strings.kt */
class StringsKt__StringsKt extends StringsKt__StringsJVMKt {
    @InlineOnly
    private static final String orEmpty(@Nullable String str) {
        return str != null ? str : "";
    }

    @NotNull
    public static final CharSequence trim(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "predicate");
        int length = charSequence.length() - 1;
        int i = 0;
        Object obj = null;
        while (i <= length) {
            boolean booleanValue = ((Boolean) function1.invoke(Character.valueOf(charSequence.charAt(obj == null ? i : length)))).booleanValue();
            if (obj == null) {
                if (booleanValue) {
                    i++;
                } else {
                    obj = 1;
                }
            } else if (!booleanValue) {
                break;
            } else {
                length--;
            }
        }
        return charSequence.subSequence(i, length + 1);
    }

    @NotNull
    public static final String trim(@NotNull String str, @NotNull Function1<? super Character, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(str, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "predicate");
        CharSequence charSequence = str;
        int length = charSequence.length() - 1;
        int i = 0;
        Object obj = null;
        while (i <= length) {
            boolean booleanValue = ((Boolean) function1.invoke(Character.valueOf(charSequence.charAt(obj == null ? i : length)))).booleanValue();
            if (obj == null) {
                if (booleanValue) {
                    i++;
                } else {
                    obj = 1;
                }
            } else if (!booleanValue) {
                break;
            } else {
                length--;
            }
        }
        return charSequence.subSequence(i, length + 1).toString();
    }

    @NotNull
    public static final CharSequence trimStart(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, Boolean> function1) {
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
    public static final String trimStart(@NotNull String str, @NotNull Function1<? super Character, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(str, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "predicate");
        CharSequence charSequence = str;
        int length = charSequence.length();
        for (int i = 0; i < length; i++) {
            if (!((Boolean) function1.invoke(Character.valueOf(charSequence.charAt(i)))).booleanValue()) {
                str = charSequence.subSequence(i, charSequence.length());
                break;
            }
        }
        str = "";
        return str.toString();
    }

    @NotNull
    public static final CharSequence trimEnd(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "predicate");
        int length = charSequence.length();
        do {
            length--;
            if (length < 0) {
                return "";
            }
        } while (((Boolean) function1.invoke(Character.valueOf(charSequence.charAt(length)))).booleanValue());
        return charSequence.subSequence(null, length + 1);
    }

    @NotNull
    public static final String trimEnd(@NotNull String str, @NotNull Function1<? super Character, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(str, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "predicate");
        CharSequence charSequence = str;
        int length = charSequence.length();
        do {
            length--;
            if (length < 0) {
                str = "";
                break;
            }
        } while (((Boolean) function1.invoke(Character.valueOf(charSequence.charAt(length)))).booleanValue());
        str = charSequence.subSequence(null, length + 1);
        return str.toString();
    }

    @InlineOnly
    private static final String trim(@NotNull String str) {
        if (str != null) {
            return trim((CharSequence) str).toString();
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
    }

    @InlineOnly
    private static final String trimStart(@NotNull String str) {
        if (str != null) {
            return trimStart((CharSequence) str).toString();
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
    }

    @InlineOnly
    private static final String trimEnd(@NotNull String str) {
        if (str != null) {
            return trimEnd((CharSequence) str).toString();
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
    }

    @NotNull
    public static /* synthetic */ CharSequence padStart$default(CharSequence charSequence, int i, char c, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            c = ' ';
        }
        return padStart(charSequence, i, c);
    }

    @NotNull
    public static final CharSequence padStart(@NotNull CharSequence charSequence, int i, char c) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        if (i < 0) {
            c = new StringBuilder();
            c.append("Desired length ");
            c.append(i);
            c.append(" is less than zero.");
            throw ((Throwable) new IllegalArgumentException(c.toString()));
        } else if (i <= charSequence.length()) {
            return charSequence.subSequence(0, charSequence.length());
        } else {
            StringBuilder stringBuilder = new StringBuilder(i);
            i -= charSequence.length();
            int i2 = 1;
            if (1 <= i) {
                while (true) {
                    stringBuilder.append(c);
                    if (i2 == i) {
                        break;
                    }
                    i2++;
                }
            }
            stringBuilder.append(charSequence);
            return stringBuilder;
        }
    }

    @NotNull
    public static /* synthetic */ String padStart$default(String str, int i, char c, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            c = ' ';
        }
        return padStart(str, i, c);
    }

    @NotNull
    public static final String padStart(@NotNull String str, int i, char c) {
        Intrinsics.checkParameterIsNotNull(str, "receiver$0");
        return padStart((CharSequence) str, i, c).toString();
    }

    @NotNull
    public static /* synthetic */ CharSequence padEnd$default(CharSequence charSequence, int i, char c, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            c = ' ';
        }
        return padEnd(charSequence, i, c);
    }

    @NotNull
    public static final CharSequence padEnd(@NotNull CharSequence charSequence, int i, char c) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        if (i < 0) {
            c = new StringBuilder();
            c.append("Desired length ");
            c.append(i);
            c.append(" is less than zero.");
            throw ((Throwable) new IllegalArgumentException(c.toString()));
        } else if (i <= charSequence.length()) {
            return charSequence.subSequence(0, charSequence.length());
        } else {
            StringBuilder stringBuilder = new StringBuilder(i);
            stringBuilder.append(charSequence);
            i -= charSequence.length();
            charSequence = true;
            if (1 <= i) {
                while (true) {
                    stringBuilder.append(c);
                    if (charSequence == i) {
                        break;
                    }
                    charSequence++;
                }
            }
            return stringBuilder;
        }
    }

    @NotNull
    public static /* synthetic */ String padEnd$default(String str, int i, char c, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            c = ' ';
        }
        return padEnd(str, i, c);
    }

    @NotNull
    public static final String padEnd(@NotNull String str, int i, char c) {
        Intrinsics.checkParameterIsNotNull(str, "receiver$0");
        return padEnd((CharSequence) str, i, c).toString();
    }

    @InlineOnly
    private static final boolean isNullOrEmpty(@Nullable CharSequence charSequence) {
        if (charSequence != null) {
            if (charSequence.length() != null) {
                return null;
            }
        }
        return true;
    }

    @InlineOnly
    private static final boolean isEmpty(@NotNull CharSequence charSequence) {
        return charSequence.length() == null ? true : null;
    }

    @InlineOnly
    private static final boolean isNotEmpty(@NotNull CharSequence charSequence) {
        return charSequence.length() > null ? true : null;
    }

    @InlineOnly
    private static final boolean isNotBlank(@NotNull CharSequence charSequence) {
        return StringsKt__StringsJVMKt.isBlank(charSequence) ^ 1;
    }

    @InlineOnly
    private static final boolean isNullOrBlank(@Nullable CharSequence charSequence) {
        if (charSequence != null) {
            if (StringsKt__StringsJVMKt.isBlank(charSequence) == null) {
                return null;
            }
        }
        return true;
    }

    @NotNull
    public static final CharIterator iterator(@NotNull CharSequence charSequence) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        return new StringsKt__StringsKt$iterator$1(charSequence);
    }

    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final <C extends CharSequence & R, R> R ifEmpty(C c, Function0<? extends R> function0) {
        return (c.length() == 0 ? 1 : null) != null ? function0.invoke() : c;
    }

    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final <C extends CharSequence & R, R> R ifBlank(C c, Function0<? extends R> function0) {
        return StringsKt__StringsJVMKt.isBlank(c) ? function0.invoke() : c;
    }

    @NotNull
    public static final IntRange getIndices(@NotNull CharSequence charSequence) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        return new IntRange(0, charSequence.length() - 1);
    }

    public static final int getLastIndex(@NotNull CharSequence charSequence) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        return charSequence.length() - 1;
    }

    public static final boolean hasSurrogatePairAt(@NotNull CharSequence charSequence, int i) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        int length = charSequence.length() - 2;
        if (i >= 0) {
            if (length >= i && Character.isHighSurrogate(charSequence.charAt(i)) && Character.isLowSurrogate(charSequence.charAt(i + 1)) != null) {
                return true;
            }
        }
        return false;
    }

    @NotNull
    public static final String substring(@NotNull String str, @NotNull IntRange intRange) {
        Intrinsics.checkParameterIsNotNull(str, "receiver$0");
        Intrinsics.checkParameterIsNotNull(intRange, "range");
        str = str.substring(intRange.getStart().intValue(), intRange.getEndInclusive().intValue() + 1);
        Intrinsics.checkExpressionValueIsNotNull(str, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return str;
    }

    @NotNull
    public static final CharSequence subSequence(@NotNull CharSequence charSequence, @NotNull IntRange intRange) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        Intrinsics.checkParameterIsNotNull(intRange, "range");
        return charSequence.subSequence(intRange.getStart().intValue(), intRange.getEndInclusive().intValue() + 1);
    }

    @Deprecated(message = "Use parameters named startIndex and endIndex.", replaceWith = @ReplaceWith(expression = "subSequence(startIndex = start, endIndex = end)", imports = {}))
    @InlineOnly
    private static final CharSequence subSequence(@NotNull String str, int i, int i2) {
        return str.subSequence(i, i2);
    }

    @InlineOnly
    private static final String substring(@NotNull CharSequence charSequence, int i, int i2) {
        return charSequence.subSequence(i, i2).toString();
    }

    @InlineOnly
    static /* synthetic */ String substring$default(CharSequence charSequence, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i2 = charSequence.length();
        }
        return charSequence.subSequence(i, i2).toString();
    }

    @NotNull
    public static final String substring(@NotNull CharSequence charSequence, @NotNull IntRange intRange) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        Intrinsics.checkParameterIsNotNull(intRange, "range");
        return charSequence.subSequence(intRange.getStart().intValue(), intRange.getEndInclusive().intValue() + 1).toString();
    }

    @NotNull
    public static /* synthetic */ String substringBefore$default(String str, char c, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = str;
        }
        return substringBefore(str, c, str2);
    }

    @NotNull
    public static final String substringBefore(@NotNull String str, char c, @NotNull String str2) {
        Intrinsics.checkParameterIsNotNull(str, "receiver$0");
        Intrinsics.checkParameterIsNotNull(str2, "missingDelimiterValue");
        c = indexOf$default((CharSequence) str, c, 0, false, 6, null);
        if (c == '￿') {
            return str2;
        }
        str2 = str.substring(null, c);
        Intrinsics.checkExpressionValueIsNotNull(str2, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return str2;
    }

    @NotNull
    public static /* synthetic */ String substringBefore$default(String str, String str2, String str3, int i, Object obj) {
        if ((i & 2) != 0) {
            str3 = str;
        }
        return substringBefore(str, str2, str3);
    }

    @NotNull
    public static final String substringBefore(@NotNull String str, @NotNull String str2, @NotNull String str3) {
        Intrinsics.checkParameterIsNotNull(str, "receiver$0");
        Intrinsics.checkParameterIsNotNull(str2, "delimiter");
        Intrinsics.checkParameterIsNotNull(str3, "missingDelimiterValue");
        str2 = indexOf$default((CharSequence) str, str2, 0, false, 6, null);
        if (str2 == -1) {
            return str3;
        }
        str3 = str.substring(null, str2);
        Intrinsics.checkExpressionValueIsNotNull(str3, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return str3;
    }

    @NotNull
    public static /* synthetic */ String substringAfter$default(String str, char c, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = str;
        }
        return substringAfter(str, c, str2);
    }

    @NotNull
    public static final String substringAfter(@NotNull String str, char c, @NotNull String str2) {
        Intrinsics.checkParameterIsNotNull(str, "receiver$0");
        Intrinsics.checkParameterIsNotNull(str2, "missingDelimiterValue");
        c = indexOf$default((CharSequence) str, c, 0, false, 6, null);
        if (c == '￿') {
            return str2;
        }
        str2 = str.substring(c + 1, str.length());
        Intrinsics.checkExpressionValueIsNotNull(str2, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return str2;
    }

    @NotNull
    public static /* synthetic */ String substringAfter$default(String str, String str2, String str3, int i, Object obj) {
        if ((i & 2) != 0) {
            str3 = str;
        }
        return substringAfter(str, str2, str3);
    }

    @NotNull
    public static final String substringAfter(@NotNull String str, @NotNull String str2, @NotNull String str3) {
        Intrinsics.checkParameterIsNotNull(str, "receiver$0");
        Intrinsics.checkParameterIsNotNull(str2, "delimiter");
        Intrinsics.checkParameterIsNotNull(str3, "missingDelimiterValue");
        int indexOf$default = indexOf$default((CharSequence) str, str2, 0, false, 6, null);
        if (indexOf$default == -1) {
            return str3;
        }
        str3 = str.substring(indexOf$default + str2.length(), str.length());
        Intrinsics.checkExpressionValueIsNotNull(str3, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return str3;
    }

    @NotNull
    public static /* synthetic */ String substringBeforeLast$default(String str, char c, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = str;
        }
        return substringBeforeLast(str, c, str2);
    }

    @NotNull
    public static final String substringBeforeLast(@NotNull String str, char c, @NotNull String str2) {
        Intrinsics.checkParameterIsNotNull(str, "receiver$0");
        Intrinsics.checkParameterIsNotNull(str2, "missingDelimiterValue");
        c = lastIndexOf$default((CharSequence) str, c, 0, false, 6, null);
        if (c == '￿') {
            return str2;
        }
        str2 = str.substring(null, c);
        Intrinsics.checkExpressionValueIsNotNull(str2, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return str2;
    }

    @NotNull
    public static /* synthetic */ String substringBeforeLast$default(String str, String str2, String str3, int i, Object obj) {
        if ((i & 2) != 0) {
            str3 = str;
        }
        return substringBeforeLast(str, str2, str3);
    }

    @NotNull
    public static final String substringBeforeLast(@NotNull String str, @NotNull String str2, @NotNull String str3) {
        Intrinsics.checkParameterIsNotNull(str, "receiver$0");
        Intrinsics.checkParameterIsNotNull(str2, "delimiter");
        Intrinsics.checkParameterIsNotNull(str3, "missingDelimiterValue");
        str2 = lastIndexOf$default((CharSequence) str, str2, 0, false, 6, null);
        if (str2 == -1) {
            return str3;
        }
        str3 = str.substring(null, str2);
        Intrinsics.checkExpressionValueIsNotNull(str3, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return str3;
    }

    @NotNull
    public static /* synthetic */ String substringAfterLast$default(String str, char c, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = str;
        }
        return substringAfterLast(str, c, str2);
    }

    @NotNull
    public static final String substringAfterLast(@NotNull String str, char c, @NotNull String str2) {
        Intrinsics.checkParameterIsNotNull(str, "receiver$0");
        Intrinsics.checkParameterIsNotNull(str2, "missingDelimiterValue");
        c = lastIndexOf$default((CharSequence) str, c, 0, false, 6, null);
        if (c == '￿') {
            return str2;
        }
        str2 = str.substring(c + 1, str.length());
        Intrinsics.checkExpressionValueIsNotNull(str2, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return str2;
    }

    @NotNull
    public static /* synthetic */ String substringAfterLast$default(String str, String str2, String str3, int i, Object obj) {
        if ((i & 2) != 0) {
            str3 = str;
        }
        return substringAfterLast(str, str2, str3);
    }

    @NotNull
    public static final String substringAfterLast(@NotNull String str, @NotNull String str2, @NotNull String str3) {
        Intrinsics.checkParameterIsNotNull(str, "receiver$0");
        Intrinsics.checkParameterIsNotNull(str2, "delimiter");
        Intrinsics.checkParameterIsNotNull(str3, "missingDelimiterValue");
        int lastIndexOf$default = lastIndexOf$default((CharSequence) str, str2, 0, false, 6, null);
        if (lastIndexOf$default == -1) {
            return str3;
        }
        str3 = str.substring(lastIndexOf$default + str2.length(), str.length());
        Intrinsics.checkExpressionValueIsNotNull(str3, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return str3;
    }

    @NotNull
    public static final CharSequence replaceRange(@NotNull CharSequence charSequence, int i, int i2, @NotNull CharSequence charSequence2) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        Intrinsics.checkParameterIsNotNull(charSequence2, "replacement");
        if (i2 >= i) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(charSequence, 0, i);
            stringBuilder.append(charSequence2);
            stringBuilder.append(charSequence, i2, charSequence.length());
            return stringBuilder;
        }
        charSequence2 = new StringBuilder();
        charSequence2.append("End index (");
        charSequence2.append(i2);
        charSequence2.append(") is less than start index (");
        charSequence2.append(i);
        charSequence2.append(").");
        throw ((Throwable) new IndexOutOfBoundsException(charSequence2.toString()));
    }

    @InlineOnly
    private static final String replaceRange(@NotNull String str, int i, int i2, CharSequence charSequence) {
        if (str != null) {
            return replaceRange((CharSequence) str, i, i2, charSequence).toString();
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
    }

    @NotNull
    public static final CharSequence replaceRange(@NotNull CharSequence charSequence, @NotNull IntRange intRange, @NotNull CharSequence charSequence2) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        Intrinsics.checkParameterIsNotNull(intRange, "range");
        Intrinsics.checkParameterIsNotNull(charSequence2, "replacement");
        return replaceRange(charSequence, intRange.getStart().intValue(), (int) intRange.getEndInclusive().intValue() + 1, charSequence2);
    }

    @InlineOnly
    private static final String replaceRange(@NotNull String str, IntRange intRange, CharSequence charSequence) {
        if (str != null) {
            return replaceRange((CharSequence) str, intRange, charSequence).toString();
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
    }

    @NotNull
    public static final CharSequence removeRange(@NotNull CharSequence charSequence, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        if (i2 < i) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("End index (");
            stringBuilder.append(i2);
            stringBuilder.append(") is less than start index (");
            stringBuilder.append(i);
            stringBuilder.append(").");
            throw ((Throwable) new IndexOutOfBoundsException(stringBuilder.toString()));
        } else if (i2 == i) {
            return charSequence.subSequence(0, charSequence.length());
        } else {
            StringBuilder stringBuilder2 = new StringBuilder(charSequence.length() - (i2 - i));
            stringBuilder2.append(charSequence, 0, i);
            stringBuilder2.append(charSequence, i2, charSequence.length());
            return stringBuilder2;
        }
    }

    @InlineOnly
    private static final String removeRange(@NotNull String str, int i, int i2) {
        if (str != null) {
            return removeRange((CharSequence) str, i, i2).toString();
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
    }

    @NotNull
    public static final CharSequence removeRange(@NotNull CharSequence charSequence, @NotNull IntRange intRange) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        Intrinsics.checkParameterIsNotNull(intRange, "range");
        return removeRange(charSequence, intRange.getStart().intValue(), (int) intRange.getEndInclusive().intValue() + 1);
    }

    @InlineOnly
    private static final String removeRange(@NotNull String str, IntRange intRange) {
        if (str != null) {
            return removeRange((CharSequence) str, intRange).toString();
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
    }

    @NotNull
    public static final CharSequence removePrefix(@NotNull CharSequence charSequence, @NotNull CharSequence charSequence2) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        Intrinsics.checkParameterIsNotNull(charSequence2, "prefix");
        if (startsWith$default(charSequence, charSequence2, false, 2, null)) {
            return charSequence.subSequence(charSequence2.length(), charSequence.length());
        }
        return charSequence.subSequence(0, charSequence.length());
    }

    @NotNull
    public static final String removePrefix(@NotNull String str, @NotNull CharSequence charSequence) {
        Intrinsics.checkParameterIsNotNull(str, "receiver$0");
        Intrinsics.checkParameterIsNotNull(charSequence, "prefix");
        if (!startsWith$default((CharSequence) str, charSequence, false, 2, null)) {
            return str;
        }
        str = str.substring(charSequence.length());
        Intrinsics.checkExpressionValueIsNotNull(str, "(this as java.lang.String).substring(startIndex)");
        return str;
    }

    @NotNull
    public static final CharSequence removeSuffix(@NotNull CharSequence charSequence, @NotNull CharSequence charSequence2) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        Intrinsics.checkParameterIsNotNull(charSequence2, "suffix");
        if (endsWith$default(charSequence, charSequence2, false, 2, null)) {
            return charSequence.subSequence(0, charSequence.length() - charSequence2.length());
        }
        return charSequence.subSequence(0, charSequence.length());
    }

    @NotNull
    public static final String removeSuffix(@NotNull String str, @NotNull CharSequence charSequence) {
        Intrinsics.checkParameterIsNotNull(str, "receiver$0");
        Intrinsics.checkParameterIsNotNull(charSequence, "suffix");
        if (!endsWith$default((CharSequence) str, charSequence, false, 2, null)) {
            return str;
        }
        str = str.substring(0, str.length() - charSequence.length());
        Intrinsics.checkExpressionValueIsNotNull(str, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return str;
    }

    @NotNull
    public static final CharSequence removeSurrounding(@NotNull CharSequence charSequence, @NotNull CharSequence charSequence2, @NotNull CharSequence charSequence3) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        Intrinsics.checkParameterIsNotNull(charSequence2, "prefix");
        Intrinsics.checkParameterIsNotNull(charSequence3, "suffix");
        if (charSequence.length() >= charSequence2.length() + charSequence3.length() && startsWith$default(charSequence, charSequence2, false, 2, null) && endsWith$default(charSequence, charSequence3, false, 2, null)) {
            return charSequence.subSequence(charSequence2.length(), charSequence.length() - charSequence3.length());
        }
        return charSequence.subSequence(0, charSequence.length());
    }

    @NotNull
    public static final String removeSurrounding(@NotNull String str, @NotNull CharSequence charSequence, @NotNull CharSequence charSequence2) {
        Intrinsics.checkParameterIsNotNull(str, "receiver$0");
        Intrinsics.checkParameterIsNotNull(charSequence, "prefix");
        Intrinsics.checkParameterIsNotNull(charSequence2, "suffix");
        if (str.length() >= charSequence.length() + charSequence2.length()) {
            CharSequence charSequence3 = str;
            if (startsWith$default(charSequence3, charSequence, false, 2, null) && endsWith$default(charSequence3, charSequence2, false, 2, null)) {
                str = str.substring(charSequence.length(), str.length() - charSequence2.length());
                Intrinsics.checkExpressionValueIsNotNull(str, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                return str;
            }
        }
        return str;
    }

    @NotNull
    public static final CharSequence removeSurrounding(@NotNull CharSequence charSequence, @NotNull CharSequence charSequence2) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        Intrinsics.checkParameterIsNotNull(charSequence2, "delimiter");
        return removeSurrounding(charSequence, charSequence2, charSequence2);
    }

    @NotNull
    public static final String removeSurrounding(@NotNull String str, @NotNull CharSequence charSequence) {
        Intrinsics.checkParameterIsNotNull(str, "receiver$0");
        Intrinsics.checkParameterIsNotNull(charSequence, "delimiter");
        return removeSurrounding(str, charSequence, charSequence);
    }

    @NotNull
    public static /* synthetic */ String replaceBefore$default(String str, char c, String str2, String str3, int i, Object obj) {
        if ((i & 4) != 0) {
            str3 = str;
        }
        return replaceBefore(str, c, str2, str3);
    }

    @NotNull
    public static final String replaceBefore(@NotNull String str, char c, @NotNull String str2, @NotNull String str3) {
        Intrinsics.checkParameterIsNotNull(str, "receiver$0");
        Intrinsics.checkParameterIsNotNull(str2, "replacement");
        Intrinsics.checkParameterIsNotNull(str3, "missingDelimiterValue");
        CharSequence charSequence = str;
        c = indexOf$default(charSequence, c, 0, false, 6, null);
        return c == '￿' ? str3 : replaceRange(charSequence, (int) null, (int) c, (CharSequence) str2).toString();
    }

    @NotNull
    public static /* synthetic */ String replaceBefore$default(String str, String str2, String str3, String str4, int i, Object obj) {
        if ((i & 4) != 0) {
            str4 = str;
        }
        return replaceBefore(str, str2, str3, str4);
    }

    @NotNull
    public static final String replaceBefore(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4) {
        Intrinsics.checkParameterIsNotNull(str, "receiver$0");
        Intrinsics.checkParameterIsNotNull(str2, "delimiter");
        Intrinsics.checkParameterIsNotNull(str3, "replacement");
        Intrinsics.checkParameterIsNotNull(str4, "missingDelimiterValue");
        CharSequence charSequence = str;
        str2 = indexOf$default(charSequence, str2, 0, false, 6, null);
        return str2 == -1 ? str4 : replaceRange(charSequence, (int) null, (int) str2, (CharSequence) str3).toString();
    }

    @NotNull
    public static /* synthetic */ String replaceAfter$default(String str, char c, String str2, String str3, int i, Object obj) {
        if ((i & 4) != 0) {
            str3 = str;
        }
        return replaceAfter(str, c, str2, str3);
    }

    @NotNull
    public static final String replaceAfter(@NotNull String str, char c, @NotNull String str2, @NotNull String str3) {
        Intrinsics.checkParameterIsNotNull(str, "receiver$0");
        Intrinsics.checkParameterIsNotNull(str2, "replacement");
        Intrinsics.checkParameterIsNotNull(str3, "missingDelimiterValue");
        CharSequence charSequence = str;
        c = indexOf$default(charSequence, c, 0, false, 6, null);
        return c == '￿' ? str3 : replaceRange(charSequence, (int) c + 1, (int) str.length(), (CharSequence) str2).toString();
    }

    @NotNull
    public static /* synthetic */ String replaceAfter$default(String str, String str2, String str3, String str4, int i, Object obj) {
        if ((i & 4) != 0) {
            str4 = str;
        }
        return replaceAfter(str, str2, str3, str4);
    }

    @NotNull
    public static final String replaceAfter(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4) {
        Intrinsics.checkParameterIsNotNull(str, "receiver$0");
        Intrinsics.checkParameterIsNotNull(str2, "delimiter");
        Intrinsics.checkParameterIsNotNull(str3, "replacement");
        Intrinsics.checkParameterIsNotNull(str4, "missingDelimiterValue");
        CharSequence charSequence = str;
        int indexOf$default = indexOf$default(charSequence, str2, 0, false, 6, null);
        return indexOf$default == -1 ? str4 : replaceRange(charSequence, indexOf$default + str2.length(), (int) str.length(), (CharSequence) str3).toString();
    }

    @NotNull
    public static /* synthetic */ String replaceAfterLast$default(String str, String str2, String str3, String str4, int i, Object obj) {
        if ((i & 4) != 0) {
            str4 = str;
        }
        return replaceAfterLast(str, str2, str3, str4);
    }

    @NotNull
    public static final String replaceAfterLast(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4) {
        Intrinsics.checkParameterIsNotNull(str, "receiver$0");
        Intrinsics.checkParameterIsNotNull(str2, "delimiter");
        Intrinsics.checkParameterIsNotNull(str3, "replacement");
        Intrinsics.checkParameterIsNotNull(str4, "missingDelimiterValue");
        CharSequence charSequence = str;
        int lastIndexOf$default = lastIndexOf$default(charSequence, str2, 0, false, 6, null);
        return lastIndexOf$default == -1 ? str4 : replaceRange(charSequence, lastIndexOf$default + str2.length(), (int) str.length(), (CharSequence) str3).toString();
    }

    @NotNull
    public static /* synthetic */ String replaceAfterLast$default(String str, char c, String str2, String str3, int i, Object obj) {
        if ((i & 4) != 0) {
            str3 = str;
        }
        return replaceAfterLast(str, c, str2, str3);
    }

    @NotNull
    public static final String replaceAfterLast(@NotNull String str, char c, @NotNull String str2, @NotNull String str3) {
        Intrinsics.checkParameterIsNotNull(str, "receiver$0");
        Intrinsics.checkParameterIsNotNull(str2, "replacement");
        Intrinsics.checkParameterIsNotNull(str3, "missingDelimiterValue");
        CharSequence charSequence = str;
        c = lastIndexOf$default(charSequence, c, 0, false, 6, null);
        return c == '￿' ? str3 : replaceRange(charSequence, (int) c + 1, (int) str.length(), (CharSequence) str2).toString();
    }

    @NotNull
    public static /* synthetic */ String replaceBeforeLast$default(String str, char c, String str2, String str3, int i, Object obj) {
        if ((i & 4) != 0) {
            str3 = str;
        }
        return replaceBeforeLast(str, c, str2, str3);
    }

    @NotNull
    public static final String replaceBeforeLast(@NotNull String str, char c, @NotNull String str2, @NotNull String str3) {
        Intrinsics.checkParameterIsNotNull(str, "receiver$0");
        Intrinsics.checkParameterIsNotNull(str2, "replacement");
        Intrinsics.checkParameterIsNotNull(str3, "missingDelimiterValue");
        CharSequence charSequence = str;
        c = lastIndexOf$default(charSequence, c, 0, false, 6, null);
        return c == '￿' ? str3 : replaceRange(charSequence, (int) null, (int) c, (CharSequence) str2).toString();
    }

    @NotNull
    public static /* synthetic */ String replaceBeforeLast$default(String str, String str2, String str3, String str4, int i, Object obj) {
        if ((i & 4) != 0) {
            str4 = str;
        }
        return replaceBeforeLast(str, str2, str3, str4);
    }

    @NotNull
    public static final String replaceBeforeLast(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4) {
        Intrinsics.checkParameterIsNotNull(str, "receiver$0");
        Intrinsics.checkParameterIsNotNull(str2, "delimiter");
        Intrinsics.checkParameterIsNotNull(str3, "replacement");
        Intrinsics.checkParameterIsNotNull(str4, "missingDelimiterValue");
        CharSequence charSequence = str;
        str2 = lastIndexOf$default(charSequence, str2, 0, false, 6, null);
        return str2 == -1 ? str4 : replaceRange(charSequence, (int) null, (int) str2, (CharSequence) str3).toString();
    }

    @InlineOnly
    private static final String replace(@NotNull CharSequence charSequence, Regex regex, String str) {
        return regex.replace(charSequence, str);
    }

    @InlineOnly
    private static final String replace(@NotNull CharSequence charSequence, Regex regex, Function1<? super MatchResult, ? extends CharSequence> function1) {
        return regex.replace(charSequence, (Function1) function1);
    }

    @InlineOnly
    private static final String replaceFirst(@NotNull CharSequence charSequence, Regex regex, String str) {
        return regex.replaceFirst(charSequence, str);
    }

    @InlineOnly
    private static final boolean matches(@NotNull CharSequence charSequence, Regex regex) {
        return regex.matches(charSequence);
    }

    public static final boolean regionMatchesImpl(@NotNull CharSequence charSequence, int i, @NotNull CharSequence charSequence2, int i2, int i3, boolean z) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        Intrinsics.checkParameterIsNotNull(charSequence2, "other");
        if (i2 >= 0 && i >= 0 && i <= charSequence.length() - i3) {
            if (i2 <= charSequence2.length() - i3) {
                for (int i4 = 0; i4 < i3; i4++) {
                    if (!CharsKt__CharKt.equals(charSequence.charAt(i + i4), charSequence2.charAt(i2 + i4), z)) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    public static /* synthetic */ boolean startsWith$default(CharSequence charSequence, char c, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return startsWith(charSequence, c, z);
    }

    public static final boolean startsWith(@NotNull CharSequence charSequence, char c, boolean z) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        return charSequence.length() > 0 && CharsKt__CharKt.equals(charSequence.charAt(0), c, z) != null;
    }

    public static /* synthetic */ boolean endsWith$default(CharSequence charSequence, char c, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return endsWith(charSequence, c, z);
    }

    public static final boolean endsWith(@NotNull CharSequence charSequence, char c, boolean z) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        return (charSequence.length() <= 0 || CharsKt__CharKt.equals(charSequence.charAt(getLastIndex(charSequence)), c, z) == null) ? null : true;
    }

    public static /* synthetic */ boolean startsWith$default(CharSequence charSequence, CharSequence charSequence2, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return startsWith(charSequence, charSequence2, z);
    }

    public static final boolean startsWith(@NotNull CharSequence charSequence, @NotNull CharSequence charSequence2, boolean z) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        Intrinsics.checkParameterIsNotNull(charSequence2, "prefix");
        if (!z && (charSequence instanceof String) && (charSequence2 instanceof String)) {
            return StringsKt__StringsJVMKt.startsWith$default((String) charSequence, (String) charSequence2, false, 2, null);
        }
        return regionMatchesImpl(charSequence, 0, charSequence2, 0, charSequence2.length(), z);
    }

    public static /* synthetic */ boolean startsWith$default(CharSequence charSequence, CharSequence charSequence2, int i, boolean z, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            z = false;
        }
        return startsWith(charSequence, charSequence2, i, z);
    }

    public static final boolean startsWith(@NotNull CharSequence charSequence, @NotNull CharSequence charSequence2, int i, boolean z) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        Intrinsics.checkParameterIsNotNull(charSequence2, "prefix");
        if (!z && (charSequence instanceof String) && (charSequence2 instanceof String)) {
            return StringsKt__StringsJVMKt.startsWith$default((String) charSequence, (String) charSequence2, i, false, 4, null);
        }
        return regionMatchesImpl(charSequence, i, charSequence2, 0, charSequence2.length(), z);
    }

    public static /* synthetic */ boolean endsWith$default(CharSequence charSequence, CharSequence charSequence2, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return endsWith(charSequence, charSequence2, z);
    }

    public static final boolean endsWith(@NotNull CharSequence charSequence, @NotNull CharSequence charSequence2, boolean z) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        Intrinsics.checkParameterIsNotNull(charSequence2, "suffix");
        if (!z && (charSequence instanceof String) && (charSequence2 instanceof String)) {
            return StringsKt__StringsJVMKt.endsWith$default((String) charSequence, (String) charSequence2, false, 2, null);
        }
        return regionMatchesImpl(charSequence, charSequence.length() - charSequence2.length(), charSequence2, 0, charSequence2.length(), z);
    }

    @NotNull
    public static /* synthetic */ String commonPrefixWith$default(CharSequence charSequence, CharSequence charSequence2, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return commonPrefixWith(charSequence, charSequence2, z);
    }

    @NotNull
    public static final String commonPrefixWith(@NotNull CharSequence charSequence, @NotNull CharSequence charSequence2, boolean z) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        Intrinsics.checkParameterIsNotNull(charSequence2, "other");
        int min = Math.min(charSequence.length(), charSequence2.length());
        int i = 0;
        while (i < min && CharsKt__CharKt.equals(charSequence.charAt(i), charSequence2.charAt(i), z)) {
            i++;
        }
        z = i - 1;
        if (hasSurrogatePairAt(charSequence, z) || hasSurrogatePairAt(charSequence2, z) != null) {
            i--;
        }
        return charSequence.subSequence(0, i).toString();
    }

    @NotNull
    public static /* synthetic */ String commonSuffixWith$default(CharSequence charSequence, CharSequence charSequence2, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return commonSuffixWith(charSequence, charSequence2, z);
    }

    @NotNull
    public static final String commonSuffixWith(@NotNull CharSequence charSequence, @NotNull CharSequence charSequence2, boolean z) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        Intrinsics.checkParameterIsNotNull(charSequence2, "other");
        int length = charSequence.length();
        int length2 = charSequence2.length();
        int min = Math.min(length, length2);
        int i = 0;
        while (i < min && CharsKt__CharKt.equals(charSequence.charAt((length - i) - 1), charSequence2.charAt((length2 - i) - 1), z)) {
            i++;
        }
        if (hasSurrogatePairAt(charSequence, (length - i) - 1) || hasSurrogatePairAt(charSequence2, (length2 - i) - 1) != null) {
            i--;
        }
        return charSequence.subSequence(length - i, length).toString();
    }

    public static /* synthetic */ int indexOfAny$default(CharSequence charSequence, char[] cArr, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != null) {
            i = 0;
        }
        if ((i2 & 4) != 0) {
            z = false;
        }
        return indexOfAny(charSequence, cArr, i, z);
    }

    public static final int indexOfAny(@NotNull CharSequence charSequence, @NotNull char[] cArr, int i, boolean z) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        Intrinsics.checkParameterIsNotNull(cArr, "chars");
        if (!z && cArr.length == 1 && (charSequence instanceof String)) {
            return ((String) charSequence).indexOf(ArraysKt___ArraysKt.single(cArr), i);
        }
        i = RangesKt___RangesKt.coerceAtLeast(i, 0);
        int lastIndex = getLastIndex(charSequence);
        if (i <= lastIndex) {
            while (true) {
                Object obj;
                char charAt = charSequence.charAt(i);
                for (char equals : cArr) {
                    if (CharsKt__CharKt.equals(equals, charAt, z)) {
                        obj = 1;
                        break;
                    }
                }
                obj = null;
                if (obj == null) {
                    if (i == lastIndex) {
                        break;
                    }
                    i++;
                } else {
                    return i;
                }
            }
        }
        return -1;
    }

    public static /* synthetic */ int lastIndexOfAny$default(CharSequence charSequence, char[] cArr, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != null) {
            i = getLastIndex(charSequence);
        }
        if ((i2 & 4) != 0) {
            z = false;
        }
        return lastIndexOfAny(charSequence, cArr, i, z);
    }

    public static final int lastIndexOfAny(@NotNull CharSequence charSequence, @NotNull char[] cArr, int i, boolean z) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        Intrinsics.checkParameterIsNotNull(cArr, "chars");
        if (!z && cArr.length == 1 && (charSequence instanceof String)) {
            return ((String) charSequence).lastIndexOf(ArraysKt___ArraysKt.single(cArr), i);
        }
        for (i = RangesKt___RangesKt.coerceAtMost(i, getLastIndex(charSequence)); i >= 0; i--) {
            char charAt = charSequence.charAt(i);
            Object obj = null;
            for (char equals : cArr) {
                if (CharsKt__CharKt.equals(equals, charAt, z)) {
                    obj = 1;
                    break;
                }
            }
            if (obj != null) {
                return i;
            }
        }
        return -1;
    }

    static /* synthetic */ int indexOf$StringsKt__StringsKt$default(CharSequence charSequence, CharSequence charSequence2, int i, int i2, boolean z, boolean z2, int i3, Object obj) {
        return indexOf$StringsKt__StringsKt(charSequence, charSequence2, i, i2, z, (i3 & 16) != 0 ? false : z2);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final int indexOf$StringsKt__StringsKt(@org.jetbrains.annotations.NotNull java.lang.CharSequence r6, java.lang.CharSequence r7, int r8, int r9, boolean r10, boolean r11) {
        /*
        r0 = 0;
        if (r11 != 0) goto L_0x0017;
    L_0x0003:
        r8 = kotlin.ranges.RangesKt___RangesKt.coerceAtLeast(r8, r0);
        r11 = new kotlin.ranges.IntRange;
        r0 = r6.length();
        r9 = kotlin.ranges.RangesKt___RangesKt.coerceAtMost(r9, r0);
        r11.<init>(r8, r9);
        r11 = (kotlin.ranges.IntProgression) r11;
        goto L_0x0027;
    L_0x0017:
        r11 = getLastIndex(r6);
        r8 = kotlin.ranges.RangesKt___RangesKt.coerceAtMost(r8, r11);
        r9 = kotlin.ranges.RangesKt___RangesKt.coerceAtLeast(r9, r0);
        r11 = kotlin.ranges.RangesKt___RangesKt.downTo(r8, r9);
    L_0x0027:
        r8 = r6 instanceof java.lang.String;
        if (r8 == 0) goto L_0x005a;
    L_0x002b:
        r8 = r7 instanceof java.lang.String;
        if (r8 == 0) goto L_0x005a;
    L_0x002f:
        r8 = r11.getFirst();
        r9 = r11.getLast();
        r11 = r11.getStep();
        if (r11 <= 0) goto L_0x0040;
    L_0x003d:
        if (r8 > r9) goto L_0x0081;
    L_0x003f:
        goto L_0x0042;
    L_0x0040:
        if (r8 < r9) goto L_0x0081;
    L_0x0042:
        r0 = r7;
        r0 = (java.lang.String) r0;
        r1 = 0;
        r2 = r6;
        r2 = (java.lang.String) r2;
        r4 = r7.length();
        r3 = r8;
        r5 = r10;
        r0 = kotlin.text.StringsKt__StringsJVMKt.regionMatches(r0, r1, r2, r3, r4, r5);
        if (r0 == 0) goto L_0x0056;
    L_0x0055:
        return r8;
    L_0x0056:
        if (r8 == r9) goto L_0x0081;
    L_0x0058:
        r8 = r8 + r11;
        goto L_0x0042;
    L_0x005a:
        r8 = r11.getFirst();
        r9 = r11.getLast();
        r11 = r11.getStep();
        if (r11 <= 0) goto L_0x006b;
    L_0x0068:
        if (r8 > r9) goto L_0x0081;
    L_0x006a:
        goto L_0x006d;
    L_0x006b:
        if (r8 < r9) goto L_0x0081;
    L_0x006d:
        r1 = 0;
        r4 = r7.length();
        r0 = r7;
        r2 = r6;
        r3 = r8;
        r5 = r10;
        r0 = regionMatchesImpl(r0, r1, r2, r3, r4, r5);
        if (r0 == 0) goto L_0x007d;
    L_0x007c:
        return r8;
    L_0x007d:
        if (r8 == r9) goto L_0x0081;
    L_0x007f:
        r8 = r8 + r11;
        goto L_0x006d;
    L_0x0081:
        r6 = -1;
        return r6;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.text.StringsKt__StringsKt.indexOf$StringsKt__StringsKt(java.lang.CharSequence, java.lang.CharSequence, int, int, boolean, boolean):int");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final kotlin.Pair<java.lang.Integer, java.lang.String> findAnyOf$StringsKt__StringsKt(@org.jetbrains.annotations.NotNull java.lang.CharSequence r10, java.util.Collection<java.lang.String> r11, int r12, boolean r13, boolean r14) {
        /*
        r0 = 0;
        if (r13 != 0) goto L_0x0035;
    L_0x0003:
        r1 = r11.size();
        r2 = 1;
        if (r1 != r2) goto L_0x0035;
    L_0x000a:
        r11 = (java.lang.Iterable) r11;
        r11 = kotlin.collections.CollectionsKt___CollectionsKt.single(r11);
        r11 = (java.lang.String) r11;
        if (r14 != 0) goto L_0x001f;
    L_0x0014:
        r4 = 0;
        r5 = 4;
        r6 = 0;
        r1 = r10;
        r2 = r11;
        r3 = r12;
        r10 = indexOf$default(r1, r2, r3, r4, r5, r6);
        goto L_0x0029;
    L_0x001f:
        r4 = 0;
        r5 = 4;
        r6 = 0;
        r1 = r10;
        r2 = r11;
        r3 = r12;
        r10 = lastIndexOf$default(r1, r2, r3, r4, r5, r6);
    L_0x0029:
        if (r10 >= 0) goto L_0x002c;
    L_0x002b:
        goto L_0x0034;
    L_0x002c:
        r10 = java.lang.Integer.valueOf(r10);
        r0 = kotlin.TuplesKt.to(r10, r11);
    L_0x0034:
        return r0;
    L_0x0035:
        r1 = 0;
        if (r14 != 0) goto L_0x0048;
    L_0x0038:
        r12 = kotlin.ranges.RangesKt___RangesKt.coerceAtLeast(r12, r1);
        r14 = new kotlin.ranges.IntRange;
        r1 = r10.length();
        r14.<init>(r12, r1);
        r14 = (kotlin.ranges.IntProgression) r14;
        goto L_0x0054;
    L_0x0048:
        r14 = getLastIndex(r10);
        r12 = kotlin.ranges.RangesKt___RangesKt.coerceAtMost(r12, r14);
        r14 = kotlin.ranges.RangesKt___RangesKt.downTo(r12, r1);
    L_0x0054:
        r12 = r10 instanceof java.lang.String;
        if (r12 == 0) goto L_0x00a2;
    L_0x0058:
        r12 = r14.getFirst();
        r1 = r14.getLast();
        r14 = r14.getStep();
        if (r14 <= 0) goto L_0x0069;
    L_0x0066:
        if (r12 > r1) goto L_0x00ef;
    L_0x0068:
        goto L_0x006b;
    L_0x0069:
        if (r12 < r1) goto L_0x00ef;
    L_0x006b:
        r2 = r11;
        r2 = (java.lang.Iterable) r2;
        r8 = r2.iterator();
    L_0x0072:
        r2 = r8.hasNext();
        if (r2 == 0) goto L_0x0090;
    L_0x0078:
        r9 = r8.next();
        r2 = r9;
        r2 = (java.lang.String) r2;
        r3 = 0;
        r4 = r10;
        r4 = (java.lang.String) r4;
        r6 = r2.length();
        r5 = r12;
        r7 = r13;
        r2 = kotlin.text.StringsKt__StringsJVMKt.regionMatches(r2, r3, r4, r5, r6, r7);
        if (r2 == 0) goto L_0x0072;
    L_0x008f:
        goto L_0x0091;
    L_0x0090:
        r9 = r0;
    L_0x0091:
        r9 = (java.lang.String) r9;
        if (r9 == 0) goto L_0x009e;
    L_0x0095:
        r10 = java.lang.Integer.valueOf(r12);
        r10 = kotlin.TuplesKt.to(r10, r9);
        return r10;
    L_0x009e:
        if (r12 == r1) goto L_0x00ef;
    L_0x00a0:
        r12 = r12 + r14;
        goto L_0x006b;
    L_0x00a2:
        r12 = r14.getFirst();
        r1 = r14.getLast();
        r14 = r14.getStep();
        if (r14 <= 0) goto L_0x00b3;
    L_0x00b0:
        if (r12 > r1) goto L_0x00ef;
    L_0x00b2:
        goto L_0x00b5;
    L_0x00b3:
        if (r12 < r1) goto L_0x00ef;
    L_0x00b5:
        r2 = r11;
        r2 = (java.lang.Iterable) r2;
        r8 = r2.iterator();
    L_0x00bc:
        r2 = r8.hasNext();
        if (r2 == 0) goto L_0x00dd;
    L_0x00c2:
        r9 = r8.next();
        r2 = r9;
        r2 = (java.lang.String) r2;
        r3 = r2;
        r3 = (java.lang.CharSequence) r3;
        r4 = 0;
        r6 = r2.length();
        r2 = r3;
        r3 = r4;
        r4 = r10;
        r5 = r12;
        r7 = r13;
        r2 = regionMatchesImpl(r2, r3, r4, r5, r6, r7);
        if (r2 == 0) goto L_0x00bc;
    L_0x00dc:
        goto L_0x00de;
    L_0x00dd:
        r9 = r0;
    L_0x00de:
        r9 = (java.lang.String) r9;
        if (r9 == 0) goto L_0x00eb;
    L_0x00e2:
        r10 = java.lang.Integer.valueOf(r12);
        r10 = kotlin.TuplesKt.to(r10, r9);
        return r10;
    L_0x00eb:
        if (r12 == r1) goto L_0x00ef;
    L_0x00ed:
        r12 = r12 + r14;
        goto L_0x00b5;
    L_0x00ef:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.text.StringsKt__StringsKt.findAnyOf$StringsKt__StringsKt(java.lang.CharSequence, java.util.Collection, int, boolean, boolean):kotlin.Pair<java.lang.Integer, java.lang.String>");
    }

    @Nullable
    public static /* synthetic */ Pair findAnyOf$default(CharSequence charSequence, Collection collection, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != null) {
            i = 0;
        }
        if ((i2 & 4) != 0) {
            z = false;
        }
        return findAnyOf(charSequence, collection, i, z);
    }

    @Nullable
    public static final Pair<Integer, String> findAnyOf(@NotNull CharSequence charSequence, @NotNull Collection<String> collection, int i, boolean z) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        Intrinsics.checkParameterIsNotNull(collection, "strings");
        return findAnyOf$StringsKt__StringsKt(charSequence, collection, i, z, false);
    }

    @Nullable
    public static /* synthetic */ Pair findLastAnyOf$default(CharSequence charSequence, Collection collection, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != null) {
            i = getLastIndex(charSequence);
        }
        if ((i2 & 4) != 0) {
            z = false;
        }
        return findLastAnyOf(charSequence, collection, i, z);
    }

    @Nullable
    public static final Pair<Integer, String> findLastAnyOf(@NotNull CharSequence charSequence, @NotNull Collection<String> collection, int i, boolean z) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        Intrinsics.checkParameterIsNotNull(collection, "strings");
        return findAnyOf$StringsKt__StringsKt(charSequence, collection, i, z, true);
    }

    public static /* synthetic */ int indexOfAny$default(CharSequence charSequence, Collection collection, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != null) {
            i = 0;
        }
        if ((i2 & 4) != 0) {
            z = false;
        }
        return indexOfAny(charSequence, collection, i, z);
    }

    public static final int indexOfAny(@NotNull CharSequence charSequence, @NotNull Collection<String> collection, int i, boolean z) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        Intrinsics.checkParameterIsNotNull(collection, "strings");
        charSequence = findAnyOf$StringsKt__StringsKt(charSequence, collection, i, z, false);
        if (charSequence != null) {
            Integer num = (Integer) charSequence.getFirst();
            if (num != null) {
                return num.intValue();
            }
        }
        return -1;
    }

    public static /* synthetic */ int lastIndexOfAny$default(CharSequence charSequence, Collection collection, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != null) {
            i = getLastIndex(charSequence);
        }
        if ((i2 & 4) != 0) {
            z = false;
        }
        return lastIndexOfAny(charSequence, collection, i, z);
    }

    public static final int lastIndexOfAny(@NotNull CharSequence charSequence, @NotNull Collection<String> collection, int i, boolean z) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        Intrinsics.checkParameterIsNotNull(collection, "strings");
        charSequence = findAnyOf$StringsKt__StringsKt(charSequence, collection, i, z, true);
        if (charSequence != null) {
            Integer num = (Integer) charSequence.getFirst();
            if (num != null) {
                return num.intValue();
            }
        }
        return -1;
    }

    public static /* synthetic */ int indexOf$default(CharSequence charSequence, char c, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != null) {
            i = 0;
        }
        if ((i2 & 4) != 0) {
            z = false;
        }
        return indexOf(charSequence, c, i, z);
    }

    public static final int indexOf(@NotNull CharSequence charSequence, char c, int i, boolean z) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        if (!z) {
            if (charSequence instanceof String) {
                return ((String) charSequence).indexOf(c, i);
            }
        }
        return indexOfAny(charSequence, new char[]{c}, i, z);
    }

    public static /* synthetic */ int indexOf$default(CharSequence charSequence, String str, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != null) {
            i = 0;
        }
        if ((i2 & 4) != 0) {
            z = false;
        }
        return indexOf(charSequence, str, i, z);
    }

    public static final int indexOf(@NotNull CharSequence charSequence, @NotNull String str, int i, boolean z) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        Intrinsics.checkParameterIsNotNull(str, "string");
        if (!z) {
            if (charSequence instanceof String) {
                return ((String) charSequence).indexOf(str, i);
            }
        }
        return indexOf$StringsKt__StringsKt$default(charSequence, str, i, charSequence.length(), z, false, 16, null);
    }

    public static /* synthetic */ int lastIndexOf$default(CharSequence charSequence, char c, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != null) {
            i = getLastIndex(charSequence);
        }
        if ((i2 & 4) != 0) {
            z = false;
        }
        return lastIndexOf(charSequence, c, i, z);
    }

    public static final int lastIndexOf(@NotNull CharSequence charSequence, char c, int i, boolean z) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        if (!z) {
            if (charSequence instanceof String) {
                return ((String) charSequence).lastIndexOf(c, i);
            }
        }
        return lastIndexOfAny(charSequence, new char[]{c}, i, z);
    }

    public static /* synthetic */ int lastIndexOf$default(CharSequence charSequence, String str, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != null) {
            i = getLastIndex(charSequence);
        }
        if ((i2 & 4) != 0) {
            z = false;
        }
        return lastIndexOf(charSequence, str, i, z);
    }

    public static final int lastIndexOf(@NotNull CharSequence charSequence, @NotNull String str, int i, boolean z) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        Intrinsics.checkParameterIsNotNull(str, "string");
        if (!z) {
            if (charSequence instanceof String) {
                return ((String) charSequence).lastIndexOf(str, i);
            }
        }
        return indexOf$StringsKt__StringsKt(charSequence, str, i, 0, z, true);
    }

    public static /* synthetic */ boolean contains$default(CharSequence charSequence, CharSequence charSequence2, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return contains(charSequence, charSequence2, z);
    }

    public static final boolean contains(@NotNull CharSequence charSequence, @NotNull CharSequence charSequence2, boolean z) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        Intrinsics.checkParameterIsNotNull(charSequence2, "other");
        if (charSequence2 instanceof String) {
            if (indexOf$default(charSequence, (String) charSequence2, 0, z, 2, null) < null) {
                return false;
            }
        }
        if (indexOf$StringsKt__StringsKt$default(charSequence, charSequence2, 0, charSequence.length(), z, false, 16, null) < null) {
            return false;
        }
        return true;
    }

    public static /* synthetic */ boolean contains$default(CharSequence charSequence, char c, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return contains(charSequence, c, z);
    }

    public static final boolean contains(@NotNull CharSequence charSequence, char c, boolean z) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        return indexOf$default(charSequence, c, 0, z, 2, null) >= null ? true : null;
    }

    @InlineOnly
    private static final boolean contains(@NotNull CharSequence charSequence, Regex regex) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        return regex.containsMatchIn(charSequence);
    }

    static /* synthetic */ Sequence rangesDelimitedBy$StringsKt__StringsKt$default(CharSequence charSequence, char[] cArr, int i, boolean z, int i2, int i3, Object obj) {
        if ((i3 & 2) != null) {
            i = 0;
        }
        if ((i3 & 4) != null) {
            z = false;
        }
        if ((i3 & 8) != 0) {
            i2 = 0;
        }
        return rangesDelimitedBy$StringsKt__StringsKt(charSequence, cArr, i, z, i2);
    }

    private static final Sequence<IntRange> rangesDelimitedBy$StringsKt__StringsKt(@NotNull CharSequence charSequence, char[] cArr, int i, boolean z, int i2) {
        if ((i2 >= 0 ? 1 : null) != null) {
            return new DelimitedRangesSequence(charSequence, i, i2, new StringsKt__StringsKt$rangesDelimitedBy$2(cArr, z));
        }
        charSequence = new StringBuilder();
        charSequence.append("Limit must be non-negative, but was ");
        charSequence.append(i2);
        charSequence.append(46);
        throw ((Throwable) new IllegalArgumentException(charSequence.toString().toString()));
    }

    static /* synthetic */ Sequence rangesDelimitedBy$StringsKt__StringsKt$default(CharSequence charSequence, String[] strArr, int i, boolean z, int i2, int i3, Object obj) {
        if ((i3 & 2) != null) {
            i = 0;
        }
        if ((i3 & 4) != null) {
            z = false;
        }
        if ((i3 & 8) != 0) {
            i2 = 0;
        }
        return rangesDelimitedBy$StringsKt__StringsKt(charSequence, strArr, i, z, i2);
    }

    private static final Sequence<IntRange> rangesDelimitedBy$StringsKt__StringsKt(@NotNull CharSequence charSequence, String[] strArr, int i, boolean z, int i2) {
        if ((i2 >= 0 ? 1 : null) != null) {
            return new DelimitedRangesSequence(charSequence, i, i2, new StringsKt__StringsKt$rangesDelimitedBy$4(ArraysKt___ArraysJvmKt.asList((Object[]) strArr), z));
        }
        charSequence = new StringBuilder();
        charSequence.append("Limit must be non-negative, but was ");
        charSequence.append(i2);
        charSequence.append(46);
        throw ((Throwable) new IllegalArgumentException(charSequence.toString().toString()));
    }

    @NotNull
    public static /* synthetic */ Sequence splitToSequence$default(CharSequence charSequence, String[] strArr, boolean z, int i, int i2, Object obj) {
        if ((i2 & 2) != null) {
            z = false;
        }
        if ((i2 & 4) != 0) {
            i = 0;
        }
        return splitToSequence(charSequence, strArr, z, i);
    }

    @NotNull
    public static final Sequence<String> splitToSequence(@NotNull CharSequence charSequence, @NotNull String[] strArr, boolean z, int i) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        Intrinsics.checkParameterIsNotNull(strArr, "delimiters");
        return SequencesKt___SequencesKt.map(rangesDelimitedBy$StringsKt__StringsKt$default(charSequence, strArr, 0, z, i, 2, null), (Function1) new StringsKt__StringsKt$splitToSequence$1(charSequence));
    }

    @NotNull
    public static /* synthetic */ List split$default(CharSequence charSequence, String[] strArr, boolean z, int i, int i2, Object obj) {
        if ((i2 & 2) != null) {
            z = false;
        }
        if ((i2 & 4) != 0) {
            i = 0;
        }
        return split(charSequence, strArr, z, i);
    }

    @org.jetbrains.annotations.NotNull
    public static final java.util.List<java.lang.String> split(@org.jetbrains.annotations.NotNull java.lang.CharSequence r7, @org.jetbrains.annotations.NotNull java.lang.String[] r8, boolean r9, int r10) {
        /* JADX: method processing error */
/*
Error: java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$ArrayArg
	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:410)
	at jadx.core.dex.visitors.regions.LoopRegionVisitor.fixIterableType(LoopRegionVisitor.java:308)
	at jadx.core.dex.visitors.regions.LoopRegionVisitor.checkIterableForEach(LoopRegionVisitor.java:249)
	at jadx.core.dex.visitors.regions.LoopRegionVisitor.processLoopRegion(LoopRegionVisitor.java:68)
	at jadx.core.dex.visitors.regions.LoopRegionVisitor.enterRegion(LoopRegionVisitor.java:52)
	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:56)
	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:58)
	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverse(DepthRegionTraversal.java:18)
	at jadx.core.dex.visitors.regions.LoopRegionVisitor.visit(LoopRegionVisitor.java:46)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
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
        r0 = "receiver$0";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r7, r0);
        r0 = "delimiters";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r8, r0);
        r0 = r8.length;
        r1 = 1;
        if (r0 != r1) goto L_0x0022;
    L_0x000e:
        r0 = 0;
        r2 = r8[r0];
        r3 = r2;
        r3 = (java.lang.CharSequence) r3;
        r3 = r3.length();
        if (r3 != 0) goto L_0x001b;
    L_0x001a:
        r0 = 1;
    L_0x001b:
        if (r0 != 0) goto L_0x0022;
    L_0x001d:
        r7 = split$StringsKt__StringsKt(r7, r2, r9, r10);
        return r7;
    L_0x0022:
        r2 = 0;
        r5 = 2;
        r6 = 0;
        r0 = r7;
        r1 = r8;
        r3 = r9;
        r4 = r10;
        r8 = rangesDelimitedBy$StringsKt__StringsKt$default(r0, r1, r2, r3, r4, r5, r6);
        r8 = kotlin.sequences.SequencesKt___SequencesKt.asIterable(r8);
        r9 = new java.util.ArrayList;
        r10 = 10;
        r10 = kotlin.collections.CollectionsKt__IterablesKt.collectionSizeOrDefault(r8, r10);
        r9.<init>(r10);
        r9 = (java.util.Collection) r9;
        r8 = r8.iterator();
    L_0x0042:
        r10 = r8.hasNext();
        if (r10 == 0) goto L_0x0056;
    L_0x0048:
        r10 = r8.next();
        r10 = (kotlin.ranges.IntRange) r10;
        r10 = substring(r7, r10);
        r9.add(r10);
        goto L_0x0042;
    L_0x0056:
        r9 = (java.util.List) r9;
        return r9;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.text.StringsKt__StringsKt.split(java.lang.CharSequence, java.lang.String[], boolean, int):java.util.List<java.lang.String>");
    }

    @NotNull
    public static /* synthetic */ Sequence splitToSequence$default(CharSequence charSequence, char[] cArr, boolean z, int i, int i2, Object obj) {
        if ((i2 & 2) != null) {
            z = false;
        }
        if ((i2 & 4) != 0) {
            i = 0;
        }
        return splitToSequence(charSequence, cArr, z, i);
    }

    @NotNull
    public static final Sequence<String> splitToSequence(@NotNull CharSequence charSequence, @NotNull char[] cArr, boolean z, int i) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        Intrinsics.checkParameterIsNotNull(cArr, "delimiters");
        return SequencesKt___SequencesKt.map(rangesDelimitedBy$StringsKt__StringsKt$default(charSequence, cArr, 0, z, i, 2, null), (Function1) new StringsKt__StringsKt$splitToSequence$2(charSequence));
    }

    @NotNull
    public static /* synthetic */ List split$default(CharSequence charSequence, char[] cArr, boolean z, int i, int i2, Object obj) {
        if ((i2 & 2) != null) {
            z = false;
        }
        if ((i2 & 4) != 0) {
            i = 0;
        }
        return split(charSequence, cArr, z, i);
    }

    @org.jetbrains.annotations.NotNull
    public static final java.util.List<java.lang.String> split(@org.jetbrains.annotations.NotNull java.lang.CharSequence r7, @org.jetbrains.annotations.NotNull char[] r8, boolean r9, int r10) {
        /* JADX: method processing error */
/*
Error: java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$ArrayArg
	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:410)
	at jadx.core.dex.visitors.regions.LoopRegionVisitor.fixIterableType(LoopRegionVisitor.java:308)
	at jadx.core.dex.visitors.regions.LoopRegionVisitor.checkIterableForEach(LoopRegionVisitor.java:249)
	at jadx.core.dex.visitors.regions.LoopRegionVisitor.processLoopRegion(LoopRegionVisitor.java:68)
	at jadx.core.dex.visitors.regions.LoopRegionVisitor.enterRegion(LoopRegionVisitor.java:52)
	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:56)
	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:58)
	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:58)
	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:58)
	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverse(DepthRegionTraversal.java:18)
	at jadx.core.dex.visitors.regions.LoopRegionVisitor.visit(LoopRegionVisitor.java:46)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
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
        r0 = "receiver$0";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r7, r0);
        r0 = "delimiters";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r8, r0);
        r0 = r8.length;
        r1 = 1;
        if (r0 != r1) goto L_0x001a;
    L_0x000e:
        r0 = 0;
        r8 = r8[r0];
        r8 = java.lang.String.valueOf(r8);
        r7 = split$StringsKt__StringsKt(r7, r8, r9, r10);
        return r7;
    L_0x001a:
        r2 = 0;
        r5 = 2;
        r6 = 0;
        r0 = r7;
        r1 = r8;
        r3 = r9;
        r4 = r10;
        r8 = rangesDelimitedBy$StringsKt__StringsKt$default(r0, r1, r2, r3, r4, r5, r6);
        r8 = kotlin.sequences.SequencesKt___SequencesKt.asIterable(r8);
        r9 = new java.util.ArrayList;
        r10 = 10;
        r10 = kotlin.collections.CollectionsKt__IterablesKt.collectionSizeOrDefault(r8, r10);
        r9.<init>(r10);
        r9 = (java.util.Collection) r9;
        r8 = r8.iterator();
    L_0x003a:
        r10 = r8.hasNext();
        if (r10 == 0) goto L_0x004e;
    L_0x0040:
        r10 = r8.next();
        r10 = (kotlin.ranges.IntRange) r10;
        r10 = substring(r7, r10);
        r9.add(r10);
        goto L_0x003a;
    L_0x004e:
        r9 = (java.util.List) r9;
        return r9;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.text.StringsKt__StringsKt.split(java.lang.CharSequence, char[], boolean, int):java.util.List<java.lang.String>");
    }

    private static final List<String> split$StringsKt__StringsKt(@NotNull CharSequence charSequence, String str, boolean z, int i) {
        int i2 = 0;
        if ((i >= 0 ? 1 : null) != null) {
            int indexOf = indexOf(charSequence, str, 0, z);
            if (indexOf != -1) {
                if (i != 1) {
                    Object obj = i > 0 ? 1 : null;
                    int i3 = 10;
                    if (obj != null) {
                        i3 = RangesKt___RangesKt.coerceAtMost(i, 10);
                    }
                    ArrayList arrayList = new ArrayList(i3);
                    do {
                        arrayList.add(charSequence.subSequence(i2, indexOf).toString());
                        i2 = str.length() + indexOf;
                        if (obj != null && arrayList.size() == i - 1) {
                            break;
                        }
                        indexOf = indexOf(charSequence, str, i2, z);
                    } while (indexOf != -1);
                    arrayList.add(charSequence.subSequence(i2, charSequence.length()).toString());
                    return arrayList;
                }
            }
            return CollectionsKt__CollectionsJVMKt.listOf(charSequence.toString());
        }
        charSequence = new StringBuilder();
        charSequence.append("Limit must be non-negative, but was ");
        charSequence.append(i);
        charSequence.append('.');
        throw ((Throwable) new IllegalArgumentException(charSequence.toString().toString()));
    }

    @InlineOnly
    private static final List<String> split(@NotNull CharSequence charSequence, Regex regex, int i) {
        return regex.split(charSequence, i);
    }

    @NotNull
    public static final Sequence<String> lineSequence(@NotNull CharSequence charSequence) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        return splitToSequence$default(charSequence, new String[]{HTTP.CRLF, "\n", "\r"}, false, 0, 6, null);
    }

    @NotNull
    public static final List<String> lines(@NotNull CharSequence charSequence) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        return SequencesKt___SequencesKt.toList(lineSequence(charSequence));
    }

    @NotNull
    public static final CharSequence trim(@NotNull CharSequence charSequence, @NotNull char... cArr) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        Intrinsics.checkParameterIsNotNull(cArr, "chars");
        int length = charSequence.length() - 1;
        int i = 0;
        Object obj = null;
        while (i <= length) {
            boolean contains = ArraysKt___ArraysKt.contains(cArr, charSequence.charAt(obj == null ? i : length));
            if (obj == null) {
                if (contains) {
                    i++;
                } else {
                    obj = 1;
                }
            } else if (!contains) {
                break;
            } else {
                length--;
            }
        }
        return charSequence.subSequence(i, length + 1);
    }

    @NotNull
    public static final String trim(@NotNull String str, @NotNull char... cArr) {
        Intrinsics.checkParameterIsNotNull(str, "receiver$0");
        Intrinsics.checkParameterIsNotNull(cArr, "chars");
        CharSequence charSequence = str;
        int length = charSequence.length() - 1;
        int i = 0;
        Object obj = null;
        while (i <= length) {
            boolean contains = ArraysKt___ArraysKt.contains(cArr, charSequence.charAt(obj == null ? i : length));
            if (obj == null) {
                if (contains) {
                    i++;
                } else {
                    obj = 1;
                }
            } else if (!contains) {
                break;
            } else {
                length--;
            }
        }
        return charSequence.subSequence(i, length + 1).toString();
    }

    @NotNull
    public static final CharSequence trimStart(@NotNull CharSequence charSequence, @NotNull char... cArr) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        Intrinsics.checkParameterIsNotNull(cArr, "chars");
        int length = charSequence.length();
        for (int i = 0; i < length; i++) {
            if (!ArraysKt___ArraysKt.contains(cArr, charSequence.charAt(i))) {
                return charSequence.subSequence(i, charSequence.length());
            }
        }
        return "";
    }

    @NotNull
    public static final String trimStart(@NotNull String str, @NotNull char... cArr) {
        Intrinsics.checkParameterIsNotNull(str, "receiver$0");
        Intrinsics.checkParameterIsNotNull(cArr, "chars");
        CharSequence charSequence = str;
        int length = charSequence.length();
        for (int i = 0; i < length; i++) {
            if (!ArraysKt___ArraysKt.contains(cArr, charSequence.charAt(i))) {
                str = charSequence.subSequence(i, charSequence.length());
                break;
            }
        }
        str = "";
        return str.toString();
    }

    @NotNull
    public static final CharSequence trimEnd(@NotNull CharSequence charSequence, @NotNull char... cArr) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        Intrinsics.checkParameterIsNotNull(cArr, "chars");
        int length = charSequence.length();
        do {
            length--;
            if (length < 0) {
                return "";
            }
        } while (ArraysKt___ArraysKt.contains(cArr, charSequence.charAt(length)));
        return charSequence.subSequence(null, length + 1);
    }

    @NotNull
    public static final String trimEnd(@NotNull String str, @NotNull char... cArr) {
        Intrinsics.checkParameterIsNotNull(str, "receiver$0");
        Intrinsics.checkParameterIsNotNull(cArr, "chars");
        CharSequence charSequence = str;
        int length = charSequence.length();
        do {
            length--;
            if (length < 0) {
                str = "";
                break;
            }
        } while (ArraysKt___ArraysKt.contains(cArr, charSequence.charAt(length)));
        str = charSequence.subSequence(null, length + 1);
        return str.toString();
    }

    @NotNull
    public static final CharSequence trim(@NotNull CharSequence charSequence) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        int length = charSequence.length() - 1;
        int i = 0;
        Object obj = null;
        while (i <= length) {
            boolean isWhitespace = CharsKt__CharJVMKt.isWhitespace(charSequence.charAt(obj == null ? i : length));
            if (obj == null) {
                if (isWhitespace) {
                    i++;
                } else {
                    obj = 1;
                }
            } else if (!isWhitespace) {
                break;
            } else {
                length--;
            }
        }
        return charSequence.subSequence(i, length + 1);
    }

    @NotNull
    public static final CharSequence trimStart(@NotNull CharSequence charSequence) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        int length = charSequence.length();
        for (int i = 0; i < length; i++) {
            if (!CharsKt__CharJVMKt.isWhitespace(charSequence.charAt(i))) {
                return charSequence.subSequence(i, charSequence.length());
            }
        }
        return "";
    }

    @NotNull
    public static final CharSequence trimEnd(@NotNull CharSequence charSequence) {
        Intrinsics.checkParameterIsNotNull(charSequence, "receiver$0");
        int length = charSequence.length();
        do {
            length--;
            if (length < 0) {
                return "";
            }
        } while (CharsKt__CharJVMKt.isWhitespace(charSequence.charAt(length)));
        return charSequence.subSequence(0, length + 1);
    }

    @InlineOnly
    static /* synthetic */ List split$default(CharSequence charSequence, Regex regex, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        return regex.split(charSequence, i);
    }
}
