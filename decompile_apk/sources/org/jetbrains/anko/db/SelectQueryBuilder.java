package org.jetbrains.anko.db;

import android.database.Cursor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.PublishedApi;
import kotlin.ReplaceWith;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.anko.AnkoException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\b&\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0017\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u0003J\u001f\u0010\u0005\u001a\u00020\u00002\u0012\u0010\u0019\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00030\u000f\"\u00020\u0003¢\u0006\u0002\u0010\u001aJ\u0006\u0010\b\u001a\u00020\u0000J\b\u0010\u001b\u001a\u00020\u001cH\u0001J*\u0010\u001d\u001a\u0002H\u001e\"\u0004\b\u0000\u0010\u001e2\u0017\u0010\u001f\u001a\u0013\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u0002H\u001e0 ¢\u0006\u0002\b!¢\u0006\u0002\u0010\"Jk\u0010#\u001a\u00020\u001c2\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u000f2\b\u0010\u0012\u001a\u0004\u0018\u00010\u00032\u0010\u0010$\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0003\u0018\u00010\u000f2\u0006\u0010\n\u001a\u00020\u00032\b\u0010\u000b\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0011\u001a\u00020\u00032\b\u0010\r\u001a\u0004\u0018\u00010\u0003H$¢\u0006\u0002\u0010%J\u000e\u0010\n\u001a\u00020\u00002\u0006\u0010&\u001a\u00020\u0003J\u000e\u0010\u000b\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\u0003J?\u0010\u000b\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\u00032*\u0010'\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010(0\u000f\"\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010(¢\u0006\u0002\u0010)J\u000e\u0010\r\u001a\u00020\u00002\u0006\u0010*\u001a\u00020+J\u0016\u0010\r\u001a\u00020\u00002\u0006\u0010,\u001a\u00020+2\u0006\u0010*\u001a\u00020+J\u0018\u0010\u0011\u001a\u00020\u00002\u0006\u0010&\u001a\u00020\u00032\b\b\u0002\u0010-\u001a\u00020.J'\u0010/\u001a\b\u0012\u0004\u0012\u0002H\u001e00\"\b\b\u0000\u0010\u001e*\u00020\u00012\f\u00101\u001a\b\u0012\u0004\u0012\u0002H\u001e02H\bJ'\u0010/\u001a\b\u0012\u0004\u0012\u0002H\u001e00\"\b\b\u0000\u0010\u001e*\u00020\u00012\f\u00101\u001a\b\u0012\u0004\u0012\u0002H\u001e03H\bJ(\u00104\u001a\u0004\u0018\u0001H\u001e\"\b\b\u0000\u0010\u001e*\u00020\u00012\f\u00101\u001a\b\u0012\u0004\u0012\u0002H\u001e02H\b¢\u0006\u0002\u00105J(\u00104\u001a\u0004\u0018\u0001H\u001e\"\b\b\u0000\u0010\u001e*\u00020\u00012\f\u00101\u001a\b\u0012\u0004\u0012\u0002H\u001e03H\b¢\u0006\u0002\u00106J&\u00107\u001a\u0002H\u001e\"\b\b\u0000\u0010\u001e*\u00020\u00012\f\u00101\u001a\b\u0012\u0004\u0012\u0002H\u001e02H\b¢\u0006\u0002\u00105J&\u00107\u001a\u0002H\u001e\"\b\b\u0000\u0010\u001e*\u00020\u00012\f\u00101\u001a\b\u0012\u0004\u0012\u0002H\u001e03H\b¢\u0006\u0002\u00106J\u0010\u00108\u001a\u00020\u00002\u0006\u00109\u001a\u00020\u0003H\u0007JA\u00108\u001a\u00020\u00002\u0006\u00109\u001a\u00020\u00032*\u0010'\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010(0\u000f\"\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010(H\u0007¢\u0006\u0002\u0010)J\u000e\u0010:\u001a\u00020\u00002\u0006\u00109\u001a\u00020\u0003J?\u0010:\u001a\u00020\u00002\u0006\u00109\u001a\u00020\u00032*\u0010'\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010(0\u000f\"\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010(¢\u0006\u0002\u0010)J'\u0010;\u001a\u00020\u00002\u0006\u00109\u001a\u00020\u00032\u0012\u0010'\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00030\u000f\"\u00020\u0003¢\u0006\u0002\u0010<J)\u0010=\u001a\u00020\u00002\u0006\u00109\u001a\u00020\u00032\u0012\u0010'\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00030\u000f\"\u00020\u0003H\u0007¢\u0006\u0002\u0010<R\u001e\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u00030\u0006j\b\u0012\u0004\u0012\u00020\u0003`\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\n\u001a\u0012\u0012\u0004\u0012\u00020\u00030\u0006j\b\u0012\u0004\u0012\u00020\u0003`\u0007X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\tX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u000e\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0003\u0018\u00010\u000fX\u000e¢\u0006\u0004\n\u0002\u0010\u0010R\u001e\u0010\u0011\u001a\u0012\u0012\u0004\u0012\u00020\u00030\u0006j\b\u0012\u0004\u0012\u00020\u0003`\u0007X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\tX\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u000e\u0010\u0016\u001a\u00020\tX\u000e¢\u0006\u0002\n\u0000¨\u0006>"}, d2 = {"Lorg/jetbrains/anko/db/SelectQueryBuilder;", "", "tableName", "", "(Ljava/lang/String;)V", "columns", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "distinct", "", "groupBy", "having", "havingApplied", "limit", "nativeSelectionArgs", "", "[Ljava/lang/String;", "orderBy", "selection", "selectionApplied", "getTableName", "()Ljava/lang/String;", "useNativeSelection", "column", "name", "names", "([Ljava/lang/String;)Lorg/jetbrains/anko/db/SelectQueryBuilder;", "doExec", "Landroid/database/Cursor;", "exec", "T", "f", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "execQuery", "selectionArgs", "(ZLjava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;", "value", "args", "Lkotlin/Pair;", "(Ljava/lang/String;[Lkotlin/Pair;)Lorg/jetbrains/anko/db/SelectQueryBuilder;", "count", "", "offset", "direction", "Lorg/jetbrains/anko/db/SqlOrderDirection;", "parseList", "", "parser", "Lorg/jetbrains/anko/db/MapRowParser;", "Lorg/jetbrains/anko/db/RowParser;", "parseOpt", "(Lorg/jetbrains/anko/db/MapRowParser;)Ljava/lang/Object;", "(Lorg/jetbrains/anko/db/RowParser;)Ljava/lang/Object;", "parseSingle", "where", "select", "whereArgs", "whereSimple", "(Ljava/lang/String;[Ljava/lang/String;)Lorg/jetbrains/anko/db/SelectQueryBuilder;", "whereSupport", "sqlite-base_release"}, k = 1, mv = {1, 1, 13})
/* compiled from: SelectQueryBuilder.kt */
public abstract class SelectQueryBuilder {
    private final ArrayList<String> columns = new ArrayList();
    private boolean distinct;
    private final ArrayList<String> groupBy = new ArrayList();
    private String having;
    private boolean havingApplied;
    private String limit;
    private String[] nativeSelectionArgs;
    private final ArrayList<String> orderBy = new ArrayList();
    private String selection;
    private boolean selectionApplied;
    @NotNull
    private final String tableName;
    private boolean useNativeSelection;

    @NotNull
    protected abstract Cursor execQuery(boolean z, @NotNull String str, @NotNull String[] strArr, @Nullable String str2, @Nullable String[] strArr2, @NotNull String str3, @Nullable String str4, @NotNull String str5, @Nullable String str6);

    public SelectQueryBuilder(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "tableName");
        this.tableName = str;
    }

    @NotNull
    public final String getTableName() {
        return this.tableName;
    }

    public final <T> T exec(@org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super android.database.Cursor, ? extends T> r5) {
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
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1603198149.run(Unknown Source)
*/
        /*
        r4 = this;
        r0 = "f";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r5, r0);
        r0 = r4.doExec();
        r1 = android.os.Build.VERSION.SDK_INT;
        r2 = 16;
        if (r1 < r2) goto L_0x0029;
    L_0x000f:
        r1 = r0;
        r1 = (java.io.Closeable) r1;
        r2 = 0;
        r2 = (java.lang.Throwable) r2;
        r3 = r1;	 Catch:{ Throwable -> 0x0022 }
        r3 = (android.database.Cursor) r3;	 Catch:{ Throwable -> 0x0022 }
        r5 = r5.invoke(r0);	 Catch:{ Throwable -> 0x0022 }
        kotlin.io.CloseableKt.closeFinally(r1, r2);
        goto L_0x0030;
    L_0x0020:
        r5 = move-exception;
        goto L_0x0025;
    L_0x0022:
        r5 = move-exception;
        r2 = r5;
        throw r2;	 Catch:{ all -> 0x0020 }
    L_0x0025:
        kotlin.io.CloseableKt.closeFinally(r1, r2);
        throw r5;
    L_0x0029:
        r5 = r5.invoke(r0);	 Catch:{ all -> 0x0031 }
        r0.close();	 Catch:{ Exception -> 0x0030 }
    L_0x0030:
        return r5;
    L_0x0031:
        r5 = move-exception;
        r0.close();	 Catch:{ Exception -> 0x0035 }
    L_0x0035:
        throw r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jetbrains.anko.db.SelectQueryBuilder.exec(kotlin.jvm.functions.Function1):T");
    }

    @org.jetbrains.annotations.NotNull
    public final <T> T parseSingle(@org.jetbrains.annotations.NotNull org.jetbrains.anko.db.RowParser<? extends T> r5) {
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
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1603198149.run(Unknown Source)
*/
        /*
        r4 = this;
        r0 = "parser";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r5, r0);
        r0 = r4.doExec();
        r1 = android.os.Build.VERSION.SDK_INT;
        r2 = 1;
        r3 = 16;
        if (r1 < r3) goto L_0x0035;
    L_0x0010:
        r0 = (java.io.Closeable) r0;
        r1 = 0;
        r1 = (java.lang.Throwable) r1;
        r3 = r0;	 Catch:{ Throwable -> 0x0028 }
        r3 = (android.database.Cursor) r3;	 Catch:{ Throwable -> 0x0028 }
        r5 = org.jetbrains.anko.db.SqlParsersKt.parseSingle(r3, r5);	 Catch:{ Throwable -> 0x0028 }
        kotlin.jvm.internal.InlineMarker.finallyStart(r2);
        kotlin.io.CloseableKt.closeFinally(r0, r1);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r2);
        goto L_0x0042;
    L_0x0026:
        r5 = move-exception;
        goto L_0x002b;
    L_0x0028:
        r5 = move-exception;
        r1 = r5;
        throw r1;	 Catch:{ all -> 0x0026 }
    L_0x002b:
        kotlin.jvm.internal.InlineMarker.finallyStart(r2);
        kotlin.io.CloseableKt.closeFinally(r0, r1);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r2);
        throw r5;
    L_0x0035:
        r5 = org.jetbrains.anko.db.SqlParsersKt.parseSingle(r0, r5);	 Catch:{ all -> 0x0043 }
        kotlin.jvm.internal.InlineMarker.finallyStart(r2);
        r0.close();	 Catch:{ Exception -> 0x003f }
    L_0x003f:
        kotlin.jvm.internal.InlineMarker.finallyEnd(r2);
    L_0x0042:
        return r5;
    L_0x0043:
        r5 = move-exception;
        kotlin.jvm.internal.InlineMarker.finallyStart(r2);
        r0.close();	 Catch:{ Exception -> 0x004a }
    L_0x004a:
        kotlin.jvm.internal.InlineMarker.finallyEnd(r2);
        throw r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jetbrains.anko.db.SelectQueryBuilder.parseSingle(org.jetbrains.anko.db.RowParser):T");
    }

    @org.jetbrains.annotations.Nullable
    public final <T> T parseOpt(@org.jetbrains.annotations.NotNull org.jetbrains.anko.db.RowParser<? extends T> r5) {
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
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1603198149.run(Unknown Source)
*/
        /*
        r4 = this;
        r0 = "parser";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r5, r0);
        r0 = r4.doExec();
        r1 = android.os.Build.VERSION.SDK_INT;
        r2 = 1;
        r3 = 16;
        if (r1 < r3) goto L_0x0035;
    L_0x0010:
        r0 = (java.io.Closeable) r0;
        r1 = 0;
        r1 = (java.lang.Throwable) r1;
        r3 = r0;	 Catch:{ Throwable -> 0x0028 }
        r3 = (android.database.Cursor) r3;	 Catch:{ Throwable -> 0x0028 }
        r5 = org.jetbrains.anko.db.SqlParsersKt.parseOpt(r3, r5);	 Catch:{ Throwable -> 0x0028 }
        kotlin.jvm.internal.InlineMarker.finallyStart(r2);
        kotlin.io.CloseableKt.closeFinally(r0, r1);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r2);
        goto L_0x0042;
    L_0x0026:
        r5 = move-exception;
        goto L_0x002b;
    L_0x0028:
        r5 = move-exception;
        r1 = r5;
        throw r1;	 Catch:{ all -> 0x0026 }
    L_0x002b:
        kotlin.jvm.internal.InlineMarker.finallyStart(r2);
        kotlin.io.CloseableKt.closeFinally(r0, r1);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r2);
        throw r5;
    L_0x0035:
        r5 = org.jetbrains.anko.db.SqlParsersKt.parseOpt(r0, r5);	 Catch:{ all -> 0x0043 }
        kotlin.jvm.internal.InlineMarker.finallyStart(r2);
        r0.close();	 Catch:{ Exception -> 0x003f }
    L_0x003f:
        kotlin.jvm.internal.InlineMarker.finallyEnd(r2);
    L_0x0042:
        return r5;
    L_0x0043:
        r5 = move-exception;
        kotlin.jvm.internal.InlineMarker.finallyStart(r2);
        r0.close();	 Catch:{ Exception -> 0x004a }
    L_0x004a:
        kotlin.jvm.internal.InlineMarker.finallyEnd(r2);
        throw r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jetbrains.anko.db.SelectQueryBuilder.parseOpt(org.jetbrains.anko.db.RowParser):T");
    }

    @org.jetbrains.annotations.NotNull
    public final <T> java.util.List<T> parseList(@org.jetbrains.annotations.NotNull org.jetbrains.anko.db.RowParser<? extends T> r5) {
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
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1603198149.run(Unknown Source)
*/
        /*
        r4 = this;
        r0 = "parser";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r5, r0);
        r0 = r4.doExec();
        r1 = android.os.Build.VERSION.SDK_INT;
        r2 = 1;
        r3 = 16;
        if (r1 < r3) goto L_0x0035;
    L_0x0010:
        r0 = (java.io.Closeable) r0;
        r1 = 0;
        r1 = (java.lang.Throwable) r1;
        r3 = r0;	 Catch:{ Throwable -> 0x0028 }
        r3 = (android.database.Cursor) r3;	 Catch:{ Throwable -> 0x0028 }
        r5 = org.jetbrains.anko.db.SqlParsersKt.parseList(r3, r5);	 Catch:{ Throwable -> 0x0028 }
        kotlin.jvm.internal.InlineMarker.finallyStart(r2);
        kotlin.io.CloseableKt.closeFinally(r0, r1);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r2);
        goto L_0x0042;
    L_0x0026:
        r5 = move-exception;
        goto L_0x002b;
    L_0x0028:
        r5 = move-exception;
        r1 = r5;
        throw r1;	 Catch:{ all -> 0x0026 }
    L_0x002b:
        kotlin.jvm.internal.InlineMarker.finallyStart(r2);
        kotlin.io.CloseableKt.closeFinally(r0, r1);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r2);
        throw r5;
    L_0x0035:
        r5 = org.jetbrains.anko.db.SqlParsersKt.parseList(r0, r5);	 Catch:{ all -> 0x0043 }
        kotlin.jvm.internal.InlineMarker.finallyStart(r2);
        r0.close();	 Catch:{ Exception -> 0x003f }
    L_0x003f:
        kotlin.jvm.internal.InlineMarker.finallyEnd(r2);
    L_0x0042:
        return r5;
    L_0x0043:
        r5 = move-exception;
        kotlin.jvm.internal.InlineMarker.finallyStart(r2);
        r0.close();	 Catch:{ Exception -> 0x004a }
    L_0x004a:
        kotlin.jvm.internal.InlineMarker.finallyEnd(r2);
        throw r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jetbrains.anko.db.SelectQueryBuilder.parseList(org.jetbrains.anko.db.RowParser):java.util.List<T>");
    }

    @org.jetbrains.annotations.NotNull
    public final <T> T parseSingle(@org.jetbrains.annotations.NotNull org.jetbrains.anko.db.MapRowParser<? extends T> r5) {
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
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1603198149.run(Unknown Source)
*/
        /*
        r4 = this;
        r0 = "parser";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r5, r0);
        r0 = r4.doExec();
        r1 = android.os.Build.VERSION.SDK_INT;
        r2 = 1;
        r3 = 16;
        if (r1 < r3) goto L_0x0035;
    L_0x0010:
        r0 = (java.io.Closeable) r0;
        r1 = 0;
        r1 = (java.lang.Throwable) r1;
        r3 = r0;	 Catch:{ Throwable -> 0x0028 }
        r3 = (android.database.Cursor) r3;	 Catch:{ Throwable -> 0x0028 }
        r5 = org.jetbrains.anko.db.SqlParsersKt.parseSingle(r3, r5);	 Catch:{ Throwable -> 0x0028 }
        kotlin.jvm.internal.InlineMarker.finallyStart(r2);
        kotlin.io.CloseableKt.closeFinally(r0, r1);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r2);
        goto L_0x0042;
    L_0x0026:
        r5 = move-exception;
        goto L_0x002b;
    L_0x0028:
        r5 = move-exception;
        r1 = r5;
        throw r1;	 Catch:{ all -> 0x0026 }
    L_0x002b:
        kotlin.jvm.internal.InlineMarker.finallyStart(r2);
        kotlin.io.CloseableKt.closeFinally(r0, r1);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r2);
        throw r5;
    L_0x0035:
        r5 = org.jetbrains.anko.db.SqlParsersKt.parseSingle(r0, r5);	 Catch:{ all -> 0x0043 }
        kotlin.jvm.internal.InlineMarker.finallyStart(r2);
        r0.close();	 Catch:{ Exception -> 0x003f }
    L_0x003f:
        kotlin.jvm.internal.InlineMarker.finallyEnd(r2);
    L_0x0042:
        return r5;
    L_0x0043:
        r5 = move-exception;
        kotlin.jvm.internal.InlineMarker.finallyStart(r2);
        r0.close();	 Catch:{ Exception -> 0x004a }
    L_0x004a:
        kotlin.jvm.internal.InlineMarker.finallyEnd(r2);
        throw r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jetbrains.anko.db.SelectQueryBuilder.parseSingle(org.jetbrains.anko.db.MapRowParser):T");
    }

    @org.jetbrains.annotations.Nullable
    public final <T> T parseOpt(@org.jetbrains.annotations.NotNull org.jetbrains.anko.db.MapRowParser<? extends T> r5) {
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
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1603198149.run(Unknown Source)
*/
        /*
        r4 = this;
        r0 = "parser";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r5, r0);
        r0 = r4.doExec();
        r1 = android.os.Build.VERSION.SDK_INT;
        r2 = 1;
        r3 = 16;
        if (r1 < r3) goto L_0x0035;
    L_0x0010:
        r0 = (java.io.Closeable) r0;
        r1 = 0;
        r1 = (java.lang.Throwable) r1;
        r3 = r0;	 Catch:{ Throwable -> 0x0028 }
        r3 = (android.database.Cursor) r3;	 Catch:{ Throwable -> 0x0028 }
        r5 = org.jetbrains.anko.db.SqlParsersKt.parseOpt(r3, r5);	 Catch:{ Throwable -> 0x0028 }
        kotlin.jvm.internal.InlineMarker.finallyStart(r2);
        kotlin.io.CloseableKt.closeFinally(r0, r1);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r2);
        goto L_0x0042;
    L_0x0026:
        r5 = move-exception;
        goto L_0x002b;
    L_0x0028:
        r5 = move-exception;
        r1 = r5;
        throw r1;	 Catch:{ all -> 0x0026 }
    L_0x002b:
        kotlin.jvm.internal.InlineMarker.finallyStart(r2);
        kotlin.io.CloseableKt.closeFinally(r0, r1);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r2);
        throw r5;
    L_0x0035:
        r5 = org.jetbrains.anko.db.SqlParsersKt.parseOpt(r0, r5);	 Catch:{ all -> 0x0043 }
        kotlin.jvm.internal.InlineMarker.finallyStart(r2);
        r0.close();	 Catch:{ Exception -> 0x003f }
    L_0x003f:
        kotlin.jvm.internal.InlineMarker.finallyEnd(r2);
    L_0x0042:
        return r5;
    L_0x0043:
        r5 = move-exception;
        kotlin.jvm.internal.InlineMarker.finallyStart(r2);
        r0.close();	 Catch:{ Exception -> 0x004a }
    L_0x004a:
        kotlin.jvm.internal.InlineMarker.finallyEnd(r2);
        throw r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jetbrains.anko.db.SelectQueryBuilder.parseOpt(org.jetbrains.anko.db.MapRowParser):T");
    }

    @org.jetbrains.annotations.NotNull
    public final <T> java.util.List<T> parseList(@org.jetbrains.annotations.NotNull org.jetbrains.anko.db.MapRowParser<? extends T> r5) {
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
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1603198149.run(Unknown Source)
*/
        /*
        r4 = this;
        r0 = "parser";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r5, r0);
        r0 = r4.doExec();
        r1 = android.os.Build.VERSION.SDK_INT;
        r2 = 1;
        r3 = 16;
        if (r1 < r3) goto L_0x0035;
    L_0x0010:
        r0 = (java.io.Closeable) r0;
        r1 = 0;
        r1 = (java.lang.Throwable) r1;
        r3 = r0;	 Catch:{ Throwable -> 0x0028 }
        r3 = (android.database.Cursor) r3;	 Catch:{ Throwable -> 0x0028 }
        r5 = org.jetbrains.anko.db.SqlParsersKt.parseList(r3, r5);	 Catch:{ Throwable -> 0x0028 }
        kotlin.jvm.internal.InlineMarker.finallyStart(r2);
        kotlin.io.CloseableKt.closeFinally(r0, r1);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r2);
        goto L_0x0042;
    L_0x0026:
        r5 = move-exception;
        goto L_0x002b;
    L_0x0028:
        r5 = move-exception;
        r1 = r5;
        throw r1;	 Catch:{ all -> 0x0026 }
    L_0x002b:
        kotlin.jvm.internal.InlineMarker.finallyStart(r2);
        kotlin.io.CloseableKt.closeFinally(r0, r1);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r2);
        throw r5;
    L_0x0035:
        r5 = org.jetbrains.anko.db.SqlParsersKt.parseList(r0, r5);	 Catch:{ all -> 0x0043 }
        kotlin.jvm.internal.InlineMarker.finallyStart(r2);
        r0.close();	 Catch:{ Exception -> 0x003f }
    L_0x003f:
        kotlin.jvm.internal.InlineMarker.finallyEnd(r2);
    L_0x0042:
        return r5;
    L_0x0043:
        r5 = move-exception;
        kotlin.jvm.internal.InlineMarker.finallyStart(r2);
        r0.close();	 Catch:{ Exception -> 0x004a }
    L_0x004a:
        kotlin.jvm.internal.InlineMarker.finallyEnd(r2);
        throw r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jetbrains.anko.db.SelectQueryBuilder.parseList(org.jetbrains.anko.db.MapRowParser):java.util.List<T>");
    }

    @NotNull
    @PublishedApi
    public final Cursor doExec() {
        String str = this.selectionApplied ? r10.selection : null;
        String[] strArr = (r10.selectionApplied && r10.useNativeSelection) ? r10.nativeSelectionArgs : null;
        boolean z = r10.distinct;
        String str2 = r10.tableName;
        Collection collection = r10.columns;
        if (collection != null) {
            Object[] toArray = collection.toArray(new String[0]);
            if (toArray != null) {
                return execQuery(z, str2, (String[]) toArray, str, strArr, CollectionsKt___CollectionsKt.joinToString$default(r10.groupBy, ", ", null, null, 0, null, null, 62, null), r10.having, CollectionsKt___CollectionsKt.joinToString$default(r10.orderBy, ", ", null, null, 0, null, null, 62, null), r10.limit);
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
        }
        throw new TypeCastException("null cannot be cast to non-null type java.util.Collection<T>");
    }

    @NotNull
    public final SelectQueryBuilder distinct() {
        this.distinct = true;
        return this;
    }

    @NotNull
    public final SelectQueryBuilder column(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "name");
        this.columns.add(str);
        return this;
    }

    @NotNull
    public final SelectQueryBuilder groupBy(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "value");
        this.groupBy.add(str);
        return this;
    }

    @NotNull
    public static /* synthetic */ SelectQueryBuilder orderBy$default(SelectQueryBuilder selectQueryBuilder, String str, SqlOrderDirection sqlOrderDirection, int i, Object obj) {
        if (obj == null) {
            if ((i & 2) != 0) {
                sqlOrderDirection = SqlOrderDirection.ASC;
            }
            return selectQueryBuilder.orderBy(str, sqlOrderDirection);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: orderBy");
    }

    @NotNull
    public final SelectQueryBuilder orderBy(@NotNull String str, @NotNull SqlOrderDirection sqlOrderDirection) {
        Intrinsics.checkParameterIsNotNull(str, "value");
        Intrinsics.checkParameterIsNotNull(sqlOrderDirection, "direction");
        if (sqlOrderDirection == SqlOrderDirection.DESC) {
            sqlOrderDirection = this.orderBy;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append(" DESC");
            sqlOrderDirection.add(stringBuilder.toString());
        } else {
            this.orderBy.add(str);
        }
        return this;
    }

    @NotNull
    public final SelectQueryBuilder limit(int i) {
        this.limit = String.valueOf(i);
        return this;
    }

    @NotNull
    public final SelectQueryBuilder limit(int i, int i2) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(i);
        stringBuilder.append(", ");
        stringBuilder.append(i2);
        this.limit = stringBuilder.toString();
        return this;
    }

    @NotNull
    public final SelectQueryBuilder columns(@NotNull String... strArr) {
        Intrinsics.checkParameterIsNotNull(strArr, "names");
        CollectionsKt__MutableCollectionsKt.addAll((Collection) this.columns, (Object[]) strArr);
        return this;
    }

    @NotNull
    public final SelectQueryBuilder having(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "having");
        if (this.havingApplied) {
            throw ((Throwable) new AnkoException("Query having was already applied."));
        }
        this.havingApplied = true;
        this.having = str;
        return this;
    }

    @NotNull
    public final SelectQueryBuilder having(@NotNull String str, @NotNull Pair<String, ? extends Object>... pairArr) {
        Intrinsics.checkParameterIsNotNull(str, "having");
        Intrinsics.checkParameterIsNotNull(pairArr, "args");
        if (this.selectionApplied) {
            throw ((Throwable) new AnkoException("Query having was already applied."));
        }
        this.havingApplied = true;
        this.having = DatabaseKt.applyArguments(str, (Pair[]) Arrays.copyOf(pairArr, pairArr.length));
        return this;
    }

    @NotNull
    @Deprecated(message = "Use whereArgs(select, args) instead.", replaceWith = @ReplaceWith(expression = "whereArgs(select, args)", imports = {}))
    public final SelectQueryBuilder where(@NotNull String str, @NotNull Pair<String, ? extends Object>... pairArr) {
        Intrinsics.checkParameterIsNotNull(str, "select");
        Intrinsics.checkParameterIsNotNull(pairArr, "args");
        return whereArgs(str, (Pair[]) Arrays.copyOf(pairArr, pairArr.length));
    }

    @NotNull
    public final SelectQueryBuilder whereArgs(@NotNull String str, @NotNull Pair<String, ? extends Object>... pairArr) {
        Intrinsics.checkParameterIsNotNull(str, "select");
        Intrinsics.checkParameterIsNotNull(pairArr, "args");
        if (this.selectionApplied) {
            throw ((Throwable) new AnkoException("Query selection was already applied."));
        }
        this.selectionApplied = true;
        this.useNativeSelection = false;
        this.selection = DatabaseKt.applyArguments(str, (Pair[]) Arrays.copyOf(pairArr, pairArr.length));
        return this;
    }

    @NotNull
    @Deprecated(message = "Use whereArgs(select) instead.", replaceWith = @ReplaceWith(expression = "whereArgs(select)", imports = {}))
    public final SelectQueryBuilder where(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "select");
        return whereArgs(str);
    }

    @NotNull
    public final SelectQueryBuilder whereArgs(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "select");
        if (this.selectionApplied) {
            throw ((Throwable) new AnkoException("Query selection was already applied."));
        }
        this.selectionApplied = true;
        this.useNativeSelection = false;
        this.selection = str;
        return this;
    }

    @NotNull
    public final SelectQueryBuilder whereSimple(@NotNull String str, @NotNull String... strArr) {
        Intrinsics.checkParameterIsNotNull(str, "select");
        Intrinsics.checkParameterIsNotNull(strArr, "args");
        if (this.selectionApplied) {
            throw ((Throwable) new AnkoException("Query selection was already applied."));
        }
        this.selectionApplied = true;
        this.useNativeSelection = true;
        this.selection = str;
        this.nativeSelectionArgs = strArr;
        return this;
    }

    @NotNull
    @Deprecated(message = "Use whereSimple() instead", replaceWith = @ReplaceWith(expression = "whereSimple(select, *args)", imports = {}))
    public final SelectQueryBuilder whereSupport(@NotNull String str, @NotNull String... strArr) {
        Intrinsics.checkParameterIsNotNull(str, "select");
        Intrinsics.checkParameterIsNotNull(strArr, "args");
        return whereSimple(str, (String[]) Arrays.copyOf(strArr, strArr.length));
    }
}
