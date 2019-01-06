package org.jetbrains.anko.db;

import android.database.Cursor;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\n\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a\u001d\u0010\u0017\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00190\u00182\u0006\u0010\u001a\u001a\u00020\u001bH\u0002¢\u0006\u0002\u0010\u001c\u001a\u001e\u0010\u001d\u001a\u0010\u0012\u0004\u0012\u00020\u0015\u0012\u0006\u0012\u0004\u0018\u00010\u00190\u001e2\u0006\u0010\u001a\u001a\u00020\u001bH\u0002\u001a\u001e\u0010\u001f\u001a\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0015\u0012\u0006\u0012\u0004\u0018\u00010\u00190\u001e0 *\u00020\u001b\u001a\u0018\u0010!\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00190\u00180 *\u00020\u001b\u001a\u0016\u0010\"\u001a\u0004\u0018\u00010\u0019*\u00020\u001b2\u0006\u0010#\u001a\u00020\fH\u0002\u001a \u0010$\u001a\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0015\u0012\u0006\u0012\u0004\u0018\u00010\u00190\u001e0 *\u00020\u001bH\u0007\u001a(\u0010%\u001a\b\u0012\u0004\u0012\u0002H'0&\"\b\b\u0000\u0010'*\u00020\u0019*\u00020\u001b2\f\u0010(\u001a\b\u0012\u0004\u0012\u0002H'0)\u001a(\u0010%\u001a\b\u0012\u0004\u0012\u0002H'0&\"\b\b\u0000\u0010'*\u00020\u0019*\u00020\u001b2\f\u0010(\u001a\b\u0012\u0004\u0012\u0002H'0\u0001\u001a)\u0010*\u001a\u0004\u0018\u0001H'\"\b\b\u0000\u0010'*\u00020\u0019*\u00020\u001b2\f\u0010(\u001a\b\u0012\u0004\u0012\u0002H'0)¢\u0006\u0002\u0010+\u001a)\u0010*\u001a\u0004\u0018\u0001H'\"\b\b\u0000\u0010'*\u00020\u0019*\u00020\u001b2\f\u0010(\u001a\b\u0012\u0004\u0012\u0002H'0\u0001¢\u0006\u0002\u0010,\u001a'\u0010-\u001a\u0002H'\"\b\b\u0000\u0010'*\u00020\u0019*\u00020\u001b2\f\u0010(\u001a\b\u0012\u0004\u0012\u0002H'0)¢\u0006\u0002\u0010+\u001a'\u0010-\u001a\u0002H'\"\b\b\u0000\u0010'*\u00020\u0019*\u00020\u001b2\f\u0010(\u001a\b\u0012\u0004\u0012\u0002H'0\u0001¢\u0006\u0002\u0010,\u001a\u001a\u0010.\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00190\u00180 *\u00020\u001bH\u0007\"\u0017\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004\"\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u0004\"\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0001¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0004\"\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0001¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u0004\"\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0004\"\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0004\"\u0017\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0004¨\u0006/"}, d2 = {"BlobParser", "Lorg/jetbrains/anko/db/RowParser;", "", "getBlobParser", "()Lorg/jetbrains/anko/db/RowParser;", "DoubleParser", "", "getDoubleParser", "FloatParser", "", "getFloatParser", "IntParser", "", "getIntParser", "LongParser", "", "getLongParser", "ShortParser", "", "getShortParser", "StringParser", "", "getStringParser", "readColumnsArray", "", "", "cursor", "Landroid/database/Cursor;", "(Landroid/database/Cursor;)[Ljava/lang/Object;", "readColumnsMap", "", "asMapSequence", "Lkotlin/sequences/Sequence;", "asSequence", "getColumnValue", "index", "mapSequence", "parseList", "", "T", "parser", "Lorg/jetbrains/anko/db/MapRowParser;", "parseOpt", "(Landroid/database/Cursor;Lorg/jetbrains/anko/db/MapRowParser;)Ljava/lang/Object;", "(Landroid/database/Cursor;Lorg/jetbrains/anko/db/RowParser;)Ljava/lang/Object;", "parseSingle", "sequence", "sqlite-base_release"}, k = 2, mv = {1, 1, 13})
/* compiled from: SqlParsers.kt */
public final class SqlParsersKt {
    @NotNull
    private static final RowParser<byte[]> BlobParser = new SingleColumnParser();
    @NotNull
    private static final RowParser<Double> DoubleParser = new SingleColumnParser();
    @NotNull
    private static final RowParser<Float> FloatParser = new ScalarColumnParser(SqlParsersKt$FloatParser$1.INSTANCE);
    @NotNull
    private static final RowParser<Integer> IntParser = new ScalarColumnParser(SqlParsersKt$IntParser$1.INSTANCE);
    @NotNull
    private static final RowParser<Long> LongParser = new SingleColumnParser();
    @NotNull
    private static final RowParser<Short> ShortParser = new ScalarColumnParser(SqlParsersKt$ShortParser$1.INSTANCE);
    @NotNull
    private static final RowParser<String> StringParser = new SingleColumnParser();

    @NotNull
    public static final RowParser<Short> getShortParser() {
        return ShortParser;
    }

    @NotNull
    public static final RowParser<Integer> getIntParser() {
        return IntParser;
    }

    @NotNull
    public static final RowParser<Long> getLongParser() {
        return LongParser;
    }

    @NotNull
    public static final RowParser<Float> getFloatParser() {
        return FloatParser;
    }

    @NotNull
    public static final RowParser<Double> getDoubleParser() {
        return DoubleParser;
    }

    @NotNull
    public static final RowParser<String> getStringParser() {
        return StringParser;
    }

    @NotNull
    public static final RowParser<byte[]> getBlobParser() {
        return BlobParser;
    }

    @NotNull
    @Deprecated(message = "Use asSequence() instead", replaceWith = @ReplaceWith(expression = "asSequence()", imports = {}))
    public static final Sequence<Object[]> sequence(@NotNull Cursor cursor) {
        Intrinsics.checkParameterIsNotNull(cursor, "receiver$0");
        return new CursorSequence(cursor);
    }

    @NotNull
    @Deprecated(message = "Use asMapSequence() instead", replaceWith = @ReplaceWith(expression = "asMapSequence()", imports = {}))
    public static final Sequence<Map<String, Object>> mapSequence(@NotNull Cursor cursor) {
        Intrinsics.checkParameterIsNotNull(cursor, "receiver$0");
        return new CursorMapSequence(cursor);
    }

    @NotNull
    public static final Sequence<Object[]> asSequence(@NotNull Cursor cursor) {
        Intrinsics.checkParameterIsNotNull(cursor, "receiver$0");
        return new CursorSequence(cursor);
    }

    @NotNull
    public static final Sequence<Map<String, Object>> asMapSequence(@NotNull Cursor cursor) {
        Intrinsics.checkParameterIsNotNull(cursor, "receiver$0");
        return new CursorMapSequence(cursor);
    }

    private static final Object getColumnValue(@NotNull Cursor cursor, int i) {
        Object obj = null;
        if (cursor.isNull(i)) {
            return null;
        }
        switch (cursor.getType(i)) {
            case 1:
                obj = Long.valueOf(cursor.getLong(i));
                break;
            case 2:
                obj = Double.valueOf(cursor.getDouble(i));
                break;
            case 3:
                obj = cursor.getString(i);
                break;
            case 4:
                obj = (Serializable) cursor.getBlob(i);
                break;
            default:
                break;
        }
        return obj;
    }

    private static final Object[] readColumnsArray(Cursor cursor) {
        int columnCount = cursor.getColumnCount();
        Object[] objArr = new Object[columnCount];
        columnCount--;
        if (columnCount >= 0) {
            int i = 0;
            while (true) {
                objArr[i] = getColumnValue(cursor, i);
                if (i == columnCount) {
                    break;
                }
                i++;
            }
        }
        return objArr;
    }

    private static final Map<String, Object> readColumnsMap(Cursor cursor) {
        int columnCount = cursor.getColumnCount();
        HashMap hashMap = new HashMap();
        columnCount--;
        if (columnCount >= 0) {
            int i = 0;
            while (true) {
                hashMap.put(cursor.getColumnName(i), getColumnValue(cursor, i));
                if (i == columnCount) {
                    break;
                }
                i++;
            }
        }
        return hashMap;
    }

    @org.jetbrains.annotations.NotNull
    public static final <T> T parseSingle(@org.jetbrains.annotations.NotNull android.database.Cursor r4, @org.jetbrains.annotations.NotNull org.jetbrains.anko.db.RowParser<? extends T> r5) {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
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
        r0 = "receiver$0";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r4, r0);
        r0 = "parser";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r5, r0);
        r0 = android.os.Build.VERSION.SDK_INT;
        r1 = 1;
        r2 = 16;
        if (r0 < r2) goto L_0x0042;
    L_0x0011:
        r0 = r4;
        r0 = (java.io.Closeable) r0;
        r2 = 0;
        r2 = (java.lang.Throwable) r2;
        r3 = r0;	 Catch:{ Throwable -> 0x003b }
        r3 = (android.database.Cursor) r3;	 Catch:{ Throwable -> 0x003b }
        r3 = r4.getCount();	 Catch:{ Throwable -> 0x003b }
        if (r3 != r1) goto L_0x002f;	 Catch:{ Throwable -> 0x003b }
    L_0x0020:
        r4.moveToFirst();	 Catch:{ Throwable -> 0x003b }
        r4 = readColumnsArray(r4);	 Catch:{ Throwable -> 0x003b }
        r4 = r5.parseRow(r4);	 Catch:{ Throwable -> 0x003b }
        kotlin.io.CloseableKt.closeFinally(r0, r2);
        return r4;
    L_0x002f:
        r4 = new android.database.sqlite.SQLiteException;	 Catch:{ Throwable -> 0x003b }
        r5 = "parseSingle accepts only cursors with a single entry";	 Catch:{ Throwable -> 0x003b }
        r4.<init>(r5);	 Catch:{ Throwable -> 0x003b }
        r4 = (java.lang.Throwable) r4;	 Catch:{ Throwable -> 0x003b }
        throw r4;	 Catch:{ Throwable -> 0x003b }
    L_0x0039:
        r4 = move-exception;
        goto L_0x003e;
    L_0x003b:
        r4 = move-exception;
        r2 = r4;
        throw r2;	 Catch:{ all -> 0x0039 }
    L_0x003e:
        kotlin.io.CloseableKt.closeFinally(r0, r2);
        throw r4;
    L_0x0042:
        r0 = r4.getCount();	 Catch:{ all -> 0x0061 }
        if (r0 != r1) goto L_0x0057;	 Catch:{ all -> 0x0061 }
    L_0x0048:
        r4.moveToFirst();	 Catch:{ all -> 0x0061 }
        r0 = readColumnsArray(r4);	 Catch:{ all -> 0x0061 }
        r5 = r5.parseRow(r0);	 Catch:{ all -> 0x0061 }
        r4.close();	 Catch:{ Exception -> 0x0056 }
    L_0x0056:
        return r5;
    L_0x0057:
        r5 = new android.database.sqlite.SQLiteException;	 Catch:{ all -> 0x0061 }
        r0 = "parseSingle accepts only cursors with a single entry";	 Catch:{ all -> 0x0061 }
        r5.<init>(r0);	 Catch:{ all -> 0x0061 }
        r5 = (java.lang.Throwable) r5;	 Catch:{ all -> 0x0061 }
        throw r5;	 Catch:{ all -> 0x0061 }
    L_0x0061:
        r5 = move-exception;
        r4.close();	 Catch:{ Exception -> 0x0065 }
    L_0x0065:
        throw r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jetbrains.anko.db.SqlParsersKt.parseSingle(android.database.Cursor, org.jetbrains.anko.db.RowParser):T");
    }

    @org.jetbrains.annotations.Nullable
    public static final <T> T parseOpt(@org.jetbrains.annotations.NotNull android.database.Cursor r5, @org.jetbrains.annotations.NotNull org.jetbrains.anko.db.RowParser<? extends T> r6) {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
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
        r0 = "receiver$0";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r5, r0);
        r0 = "parser";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r6, r0);
        r0 = android.os.Build.VERSION.SDK_INT;
        r1 = 1;
        r2 = 0;
        r3 = 16;
        if (r0 < r3) goto L_0x004d;
    L_0x0012:
        r0 = r5;
        r0 = (java.io.Closeable) r0;
        r3 = r2;
        r3 = (java.lang.Throwable) r3;
        r4 = r0;	 Catch:{ Throwable -> 0x0046 }
        r4 = (android.database.Cursor) r4;	 Catch:{ Throwable -> 0x0046 }
        r4 = r5.getCount();	 Catch:{ Throwable -> 0x0046 }
        if (r4 > r1) goto L_0x003a;	 Catch:{ Throwable -> 0x0046 }
    L_0x0021:
        r1 = r5.getCount();	 Catch:{ Throwable -> 0x0046 }
        if (r1 != 0) goto L_0x002b;
    L_0x0027:
        kotlin.io.CloseableKt.closeFinally(r0, r3);
        return r2;
    L_0x002b:
        r5.moveToFirst();	 Catch:{ Throwable -> 0x0046 }
        r5 = readColumnsArray(r5);	 Catch:{ Throwable -> 0x0046 }
        r5 = r6.parseRow(r5);	 Catch:{ Throwable -> 0x0046 }
        kotlin.io.CloseableKt.closeFinally(r0, r3);
        return r5;
    L_0x003a:
        r5 = new android.database.sqlite.SQLiteException;	 Catch:{ Throwable -> 0x0046 }
        r6 = "parseSingle accepts only cursors with a single entry or empty cursors";	 Catch:{ Throwable -> 0x0046 }
        r5.<init>(r6);	 Catch:{ Throwable -> 0x0046 }
        r5 = (java.lang.Throwable) r5;	 Catch:{ Throwable -> 0x0046 }
        throw r5;	 Catch:{ Throwable -> 0x0046 }
    L_0x0044:
        r5 = move-exception;
        goto L_0x0049;
    L_0x0046:
        r5 = move-exception;
        r3 = r5;
        throw r3;	 Catch:{ all -> 0x0044 }
    L_0x0049:
        kotlin.io.CloseableKt.closeFinally(r0, r3);
        throw r5;
    L_0x004d:
        r0 = r5.getCount();	 Catch:{ all -> 0x0076 }
        if (r0 > r1) goto L_0x006c;	 Catch:{ all -> 0x0076 }
    L_0x0053:
        r0 = r5.getCount();	 Catch:{ all -> 0x0076 }
        if (r0 != 0) goto L_0x005d;
    L_0x0059:
        r5.close();	 Catch:{ Exception -> 0x005c }
    L_0x005c:
        return r2;
    L_0x005d:
        r5.moveToFirst();	 Catch:{ all -> 0x0076 }
        r0 = readColumnsArray(r5);	 Catch:{ all -> 0x0076 }
        r6 = r6.parseRow(r0);	 Catch:{ all -> 0x0076 }
        r5.close();	 Catch:{ Exception -> 0x006b }
    L_0x006b:
        return r6;
    L_0x006c:
        r6 = new android.database.sqlite.SQLiteException;	 Catch:{ all -> 0x0076 }
        r0 = "parseSingle accepts only cursors with a single entry or empty cursors";	 Catch:{ all -> 0x0076 }
        r6.<init>(r0);	 Catch:{ all -> 0x0076 }
        r6 = (java.lang.Throwable) r6;	 Catch:{ all -> 0x0076 }
        throw r6;	 Catch:{ all -> 0x0076 }
    L_0x0076:
        r6 = move-exception;
        r5.close();	 Catch:{ Exception -> 0x007a }
    L_0x007a:
        throw r6;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jetbrains.anko.db.SqlParsersKt.parseOpt(android.database.Cursor, org.jetbrains.anko.db.RowParser):T");
    }

    @org.jetbrains.annotations.NotNull
    public static final <T> java.util.List<T> parseList(@org.jetbrains.annotations.NotNull android.database.Cursor r4, @org.jetbrains.annotations.NotNull org.jetbrains.anko.db.RowParser<? extends T> r5) {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
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
        r0 = "receiver$0";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r4, r0);
        r0 = "parser";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r5, r0);
        r0 = android.os.Build.VERSION.SDK_INT;
        r1 = 16;
        if (r0 < r1) goto L_0x0049;
    L_0x0010:
        r0 = r4;
        r0 = (java.io.Closeable) r0;
        r1 = 0;
        r1 = (java.lang.Throwable) r1;
        r2 = r0;	 Catch:{ Throwable -> 0x0042 }
        r2 = (android.database.Cursor) r2;	 Catch:{ Throwable -> 0x0042 }
        r2 = new java.util.ArrayList;	 Catch:{ Throwable -> 0x0042 }
        r3 = r4.getCount();	 Catch:{ Throwable -> 0x0042 }
        r2.<init>(r3);	 Catch:{ Throwable -> 0x0042 }
        r4.moveToFirst();	 Catch:{ Throwable -> 0x0042 }
    L_0x0025:
        r3 = r4.isAfterLast();	 Catch:{ Throwable -> 0x0042 }
        if (r3 != 0) goto L_0x003a;	 Catch:{ Throwable -> 0x0042 }
    L_0x002b:
        r3 = readColumnsArray(r4);	 Catch:{ Throwable -> 0x0042 }
        r3 = r5.parseRow(r3);	 Catch:{ Throwable -> 0x0042 }
        r2.add(r3);	 Catch:{ Throwable -> 0x0042 }
        r4.moveToNext();	 Catch:{ Throwable -> 0x0042 }
        goto L_0x0025;	 Catch:{ Throwable -> 0x0042 }
    L_0x003a:
        r2 = (java.util.List) r2;	 Catch:{ Throwable -> 0x0042 }
        kotlin.io.CloseableKt.closeFinally(r0, r1);
        return r2;
    L_0x0040:
        r4 = move-exception;
        goto L_0x0045;
    L_0x0042:
        r4 = move-exception;
        r1 = r4;
        throw r1;	 Catch:{ all -> 0x0040 }
    L_0x0045:
        kotlin.io.CloseableKt.closeFinally(r0, r1);
        throw r4;
    L_0x0049:
        r0 = new java.util.ArrayList;	 Catch:{ all -> 0x0070 }
        r1 = r4.getCount();	 Catch:{ all -> 0x0070 }
        r0.<init>(r1);	 Catch:{ all -> 0x0070 }
        r4.moveToFirst();	 Catch:{ all -> 0x0070 }
    L_0x0055:
        r1 = r4.isAfterLast();	 Catch:{ all -> 0x0070 }
        if (r1 != 0) goto L_0x006a;	 Catch:{ all -> 0x0070 }
    L_0x005b:
        r1 = readColumnsArray(r4);	 Catch:{ all -> 0x0070 }
        r1 = r5.parseRow(r1);	 Catch:{ all -> 0x0070 }
        r0.add(r1);	 Catch:{ all -> 0x0070 }
        r4.moveToNext();	 Catch:{ all -> 0x0070 }
        goto L_0x0055;	 Catch:{ all -> 0x0070 }
    L_0x006a:
        r0 = (java.util.List) r0;	 Catch:{ all -> 0x0070 }
        r4.close();	 Catch:{ Exception -> 0x006f }
    L_0x006f:
        return r0;
    L_0x0070:
        r5 = move-exception;
        r4.close();	 Catch:{ Exception -> 0x0074 }
    L_0x0074:
        throw r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jetbrains.anko.db.SqlParsersKt.parseList(android.database.Cursor, org.jetbrains.anko.db.RowParser):java.util.List<T>");
    }

    @org.jetbrains.annotations.NotNull
    public static final <T> T parseSingle(@org.jetbrains.annotations.NotNull android.database.Cursor r4, @org.jetbrains.annotations.NotNull org.jetbrains.anko.db.MapRowParser<? extends T> r5) {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
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
        r0 = "receiver$0";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r4, r0);
        r0 = "parser";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r5, r0);
        r0 = android.os.Build.VERSION.SDK_INT;
        r1 = 1;
        r2 = 16;
        if (r0 < r2) goto L_0x0042;
    L_0x0011:
        r0 = r4;
        r0 = (java.io.Closeable) r0;
        r2 = 0;
        r2 = (java.lang.Throwable) r2;
        r3 = r0;	 Catch:{ Throwable -> 0x003b }
        r3 = (android.database.Cursor) r3;	 Catch:{ Throwable -> 0x003b }
        r3 = r4.getCount();	 Catch:{ Throwable -> 0x003b }
        if (r3 != r1) goto L_0x002f;	 Catch:{ Throwable -> 0x003b }
    L_0x0020:
        r4.moveToFirst();	 Catch:{ Throwable -> 0x003b }
        r4 = readColumnsMap(r4);	 Catch:{ Throwable -> 0x003b }
        r4 = r5.parseRow(r4);	 Catch:{ Throwable -> 0x003b }
        kotlin.io.CloseableKt.closeFinally(r0, r2);
        return r4;
    L_0x002f:
        r4 = new android.database.sqlite.SQLiteException;	 Catch:{ Throwable -> 0x003b }
        r5 = "parseSingle accepts only cursors with getCount() == 1";	 Catch:{ Throwable -> 0x003b }
        r4.<init>(r5);	 Catch:{ Throwable -> 0x003b }
        r4 = (java.lang.Throwable) r4;	 Catch:{ Throwable -> 0x003b }
        throw r4;	 Catch:{ Throwable -> 0x003b }
    L_0x0039:
        r4 = move-exception;
        goto L_0x003e;
    L_0x003b:
        r4 = move-exception;
        r2 = r4;
        throw r2;	 Catch:{ all -> 0x0039 }
    L_0x003e:
        kotlin.io.CloseableKt.closeFinally(r0, r2);
        throw r4;
    L_0x0042:
        r0 = r4.getCount();	 Catch:{ all -> 0x0061 }
        if (r0 != r1) goto L_0x0057;	 Catch:{ all -> 0x0061 }
    L_0x0048:
        r4.moveToFirst();	 Catch:{ all -> 0x0061 }
        r0 = readColumnsMap(r4);	 Catch:{ all -> 0x0061 }
        r5 = r5.parseRow(r0);	 Catch:{ all -> 0x0061 }
        r4.close();	 Catch:{ Exception -> 0x0056 }
    L_0x0056:
        return r5;
    L_0x0057:
        r5 = new android.database.sqlite.SQLiteException;	 Catch:{ all -> 0x0061 }
        r0 = "parseSingle accepts only cursors with getCount() == 1";	 Catch:{ all -> 0x0061 }
        r5.<init>(r0);	 Catch:{ all -> 0x0061 }
        r5 = (java.lang.Throwable) r5;	 Catch:{ all -> 0x0061 }
        throw r5;	 Catch:{ all -> 0x0061 }
    L_0x0061:
        r5 = move-exception;
        r4.close();	 Catch:{ Exception -> 0x0065 }
    L_0x0065:
        throw r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jetbrains.anko.db.SqlParsersKt.parseSingle(android.database.Cursor, org.jetbrains.anko.db.MapRowParser):T");
    }

    @org.jetbrains.annotations.Nullable
    public static final <T> T parseOpt(@org.jetbrains.annotations.NotNull android.database.Cursor r5, @org.jetbrains.annotations.NotNull org.jetbrains.anko.db.MapRowParser<? extends T> r6) {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
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
        r0 = "receiver$0";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r5, r0);
        r0 = "parser";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r6, r0);
        r0 = android.os.Build.VERSION.SDK_INT;
        r1 = 1;
        r2 = 0;
        r3 = 16;
        if (r0 < r3) goto L_0x004d;
    L_0x0012:
        r0 = r5;
        r0 = (java.io.Closeable) r0;
        r3 = r2;
        r3 = (java.lang.Throwable) r3;
        r4 = r0;	 Catch:{ Throwable -> 0x0046 }
        r4 = (android.database.Cursor) r4;	 Catch:{ Throwable -> 0x0046 }
        r4 = r5.getCount();	 Catch:{ Throwable -> 0x0046 }
        if (r4 > r1) goto L_0x003a;	 Catch:{ Throwable -> 0x0046 }
    L_0x0021:
        r1 = r5.getCount();	 Catch:{ Throwable -> 0x0046 }
        if (r1 != 0) goto L_0x002b;
    L_0x0027:
        kotlin.io.CloseableKt.closeFinally(r0, r3);
        return r2;
    L_0x002b:
        r5.moveToFirst();	 Catch:{ Throwable -> 0x0046 }
        r5 = readColumnsMap(r5);	 Catch:{ Throwable -> 0x0046 }
        r5 = r6.parseRow(r5);	 Catch:{ Throwable -> 0x0046 }
        kotlin.io.CloseableKt.closeFinally(r0, r3);
        return r5;
    L_0x003a:
        r5 = new android.database.sqlite.SQLiteException;	 Catch:{ Throwable -> 0x0046 }
        r6 = "parseSingle accepts only cursors with getCount() == 1 or empty cursors";	 Catch:{ Throwable -> 0x0046 }
        r5.<init>(r6);	 Catch:{ Throwable -> 0x0046 }
        r5 = (java.lang.Throwable) r5;	 Catch:{ Throwable -> 0x0046 }
        throw r5;	 Catch:{ Throwable -> 0x0046 }
    L_0x0044:
        r5 = move-exception;
        goto L_0x0049;
    L_0x0046:
        r5 = move-exception;
        r3 = r5;
        throw r3;	 Catch:{ all -> 0x0044 }
    L_0x0049:
        kotlin.io.CloseableKt.closeFinally(r0, r3);
        throw r5;
    L_0x004d:
        r0 = r5.getCount();	 Catch:{ all -> 0x0076 }
        if (r0 > r1) goto L_0x006c;	 Catch:{ all -> 0x0076 }
    L_0x0053:
        r0 = r5.getCount();	 Catch:{ all -> 0x0076 }
        if (r0 != 0) goto L_0x005d;
    L_0x0059:
        r5.close();	 Catch:{ Exception -> 0x005c }
    L_0x005c:
        return r2;
    L_0x005d:
        r5.moveToFirst();	 Catch:{ all -> 0x0076 }
        r0 = readColumnsMap(r5);	 Catch:{ all -> 0x0076 }
        r6 = r6.parseRow(r0);	 Catch:{ all -> 0x0076 }
        r5.close();	 Catch:{ Exception -> 0x006b }
    L_0x006b:
        return r6;
    L_0x006c:
        r6 = new android.database.sqlite.SQLiteException;	 Catch:{ all -> 0x0076 }
        r0 = "parseSingle accepts only cursors with getCount() == 1 or empty cursors";	 Catch:{ all -> 0x0076 }
        r6.<init>(r0);	 Catch:{ all -> 0x0076 }
        r6 = (java.lang.Throwable) r6;	 Catch:{ all -> 0x0076 }
        throw r6;	 Catch:{ all -> 0x0076 }
    L_0x0076:
        r6 = move-exception;
        r5.close();	 Catch:{ Exception -> 0x007a }
    L_0x007a:
        throw r6;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jetbrains.anko.db.SqlParsersKt.parseOpt(android.database.Cursor, org.jetbrains.anko.db.MapRowParser):T");
    }

    @org.jetbrains.annotations.NotNull
    public static final <T> java.util.List<T> parseList(@org.jetbrains.annotations.NotNull android.database.Cursor r4, @org.jetbrains.annotations.NotNull org.jetbrains.anko.db.MapRowParser<? extends T> r5) {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
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
        r0 = "receiver$0";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r4, r0);
        r0 = "parser";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r5, r0);
        r0 = android.os.Build.VERSION.SDK_INT;
        r1 = 16;
        if (r0 < r1) goto L_0x0049;
    L_0x0010:
        r0 = r4;
        r0 = (java.io.Closeable) r0;
        r1 = 0;
        r1 = (java.lang.Throwable) r1;
        r2 = r0;	 Catch:{ Throwable -> 0x0042 }
        r2 = (android.database.Cursor) r2;	 Catch:{ Throwable -> 0x0042 }
        r2 = new java.util.ArrayList;	 Catch:{ Throwable -> 0x0042 }
        r3 = r4.getCount();	 Catch:{ Throwable -> 0x0042 }
        r2.<init>(r3);	 Catch:{ Throwable -> 0x0042 }
        r4.moveToFirst();	 Catch:{ Throwable -> 0x0042 }
    L_0x0025:
        r3 = r4.isAfterLast();	 Catch:{ Throwable -> 0x0042 }
        if (r3 != 0) goto L_0x003a;	 Catch:{ Throwable -> 0x0042 }
    L_0x002b:
        r3 = readColumnsMap(r4);	 Catch:{ Throwable -> 0x0042 }
        r3 = r5.parseRow(r3);	 Catch:{ Throwable -> 0x0042 }
        r2.add(r3);	 Catch:{ Throwable -> 0x0042 }
        r4.moveToNext();	 Catch:{ Throwable -> 0x0042 }
        goto L_0x0025;	 Catch:{ Throwable -> 0x0042 }
    L_0x003a:
        r2 = (java.util.List) r2;	 Catch:{ Throwable -> 0x0042 }
        kotlin.io.CloseableKt.closeFinally(r0, r1);
        return r2;
    L_0x0040:
        r4 = move-exception;
        goto L_0x0045;
    L_0x0042:
        r4 = move-exception;
        r1 = r4;
        throw r1;	 Catch:{ all -> 0x0040 }
    L_0x0045:
        kotlin.io.CloseableKt.closeFinally(r0, r1);
        throw r4;
    L_0x0049:
        r0 = new java.util.ArrayList;	 Catch:{ all -> 0x0070 }
        r1 = r4.getCount();	 Catch:{ all -> 0x0070 }
        r0.<init>(r1);	 Catch:{ all -> 0x0070 }
        r4.moveToFirst();	 Catch:{ all -> 0x0070 }
    L_0x0055:
        r1 = r4.isAfterLast();	 Catch:{ all -> 0x0070 }
        if (r1 != 0) goto L_0x006a;	 Catch:{ all -> 0x0070 }
    L_0x005b:
        r1 = readColumnsMap(r4);	 Catch:{ all -> 0x0070 }
        r1 = r5.parseRow(r1);	 Catch:{ all -> 0x0070 }
        r0.add(r1);	 Catch:{ all -> 0x0070 }
        r4.moveToNext();	 Catch:{ all -> 0x0070 }
        goto L_0x0055;	 Catch:{ all -> 0x0070 }
    L_0x006a:
        r0 = (java.util.List) r0;	 Catch:{ all -> 0x0070 }
        r4.close();	 Catch:{ Exception -> 0x006f }
    L_0x006f:
        return r0;
    L_0x0070:
        r5 = move-exception;
        r4.close();	 Catch:{ Exception -> 0x0074 }
    L_0x0074:
        throw r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jetbrains.anko.db.SqlParsersKt.parseList(android.database.Cursor, org.jetbrains.anko.db.MapRowParser):java.util.List<T>");
    }
}
