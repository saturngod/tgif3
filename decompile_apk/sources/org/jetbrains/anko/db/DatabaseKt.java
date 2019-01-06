package org.jetbrains.anko.db;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000r\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001aA\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032*\u0010\u0005\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\b0\u00070\u0006\"\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\b0\u0007H\u0000¢\u0006\u0002\u0010\t\u001a$\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\b0\nH\u0000\u001aG\u0010\u000b\u001a\u00020\f*\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u00032\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00112\u0012\u0010\u0013\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00030\u0006\"\u00020\u0003¢\u0006\u0002\u0010\u0014\u001aM\u0010\u0015\u001a\u00020\f*\u00020\r2\u0006\u0010\u000f\u001a\u00020\u00032\b\b\u0002\u0010\u0012\u001a\u00020\u00112*\u0010\u0013\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00160\u00070\u0006\"\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00160\u0007¢\u0006\u0002\u0010\u0017\u001aM\u0010\u0018\u001a\u00020\u0019*\u00020\r2\u0006\u0010\u000f\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032*\u0010\u0005\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\b0\u00070\u0006\"\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0002\u0010\u001a\u001a\u001c\u0010\u001b\u001a\u00020\f*\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00032\b\b\u0002\u0010\u001c\u001a\u00020\u0011\u001a\u001c\u0010\u001d\u001a\u00020\f*\u00020\r2\u0006\u0010\u000f\u001a\u00020\u00032\b\b\u0002\u0010\u001c\u001a\u00020\u0011\u001aG\u0010\u001e\u001a\u00020\u001f*\u00020\r2\u0006\u0010\u000f\u001a\u00020\u00032.\u0010 \u001a\u0018\u0012\u0014\b\u0001\u0012\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00070\u0006\"\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007¢\u0006\u0002\u0010!\u001aG\u0010\"\u001a\u00020\u001f*\u00020\r2\u0006\u0010\u000f\u001a\u00020\u00032.\u0010 \u001a\u0018\u0012\u0014\b\u0001\u0012\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00070\u0006\"\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007¢\u0006\u0002\u0010!\u001aG\u0010#\u001a\u00020\u001f*\u00020\r2\u0006\u0010\u000f\u001a\u00020\u00032.\u0010 \u001a\u0018\u0012\u0014\b\u0001\u0012\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00070\u0006\"\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007¢\u0006\u0002\u0010!\u001aG\u0010$\u001a\u00020\u001f*\u00020\r2\u0006\u0010\u000f\u001a\u00020\u00032.\u0010 \u001a\u0018\u0012\u0014\b\u0001\u0012\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00070\u0006\"\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007¢\u0006\u0002\u0010!\u001a\u0012\u0010%\u001a\u00020&*\u00020\r2\u0006\u0010\u000f\u001a\u00020\u0003\u001a+\u0010%\u001a\u00020&*\u00020\r2\u0006\u0010\u000f\u001a\u00020\u00032\u0012\u0010\u0013\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00030\u0006\"\u00020\u0003¢\u0006\u0002\u0010'\u001a'\u0010(\u001a\u00020)*\u0018\u0012\u0014\b\u0001\u0012\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00070\u0006H\u0000¢\u0006\u0002\u0010*\u001a#\u0010+\u001a\u00020\f*\u00020\r2\u0017\u0010,\u001a\u0013\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\f0-¢\u0006\u0002\b.\u001aG\u0010/\u001a\u000200*\u00020\r2\u0006\u0010\u000f\u001a\u00020\u00032.\u0010 \u001a\u0018\u0012\u0014\b\u0001\u0012\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00070\u0006\"\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007¢\u0006\u0002\u00101\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0004¢\u0006\u0002\n\u0000¨\u00062"}, d2 = {"ARG_PATTERN", "Ljava/util/regex/Pattern;", "applyArguments", "", "whereClause", "args", "", "Lkotlin/Pair;", "", "(Ljava/lang/String;[Lkotlin/Pair;)Ljava/lang/String;", "", "createIndex", "", "Landroid/database/sqlite/SQLiteDatabase;", "indexName", "tableName", "unique", "", "ifNotExists", "columns", "(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Ljava/lang/String;ZZ[Ljava/lang/String;)V", "createTable", "Lorg/jetbrains/anko/db/SqlType;", "(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Z[Lkotlin/Pair;)V", "delete", "", "(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Ljava/lang/String;[Lkotlin/Pair;)I", "dropIndex", "ifExists", "dropTable", "insert", "", "values", "(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;[Lkotlin/Pair;)J", "insertOrThrow", "replace", "replaceOrThrow", "select", "Lorg/jetbrains/anko/db/SelectQueryBuilder;", "(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;[Ljava/lang/String;)Lorg/jetbrains/anko/db/SelectQueryBuilder;", "toContentValues", "Landroid/content/ContentValues;", "([Lkotlin/Pair;)Landroid/content/ContentValues;", "transaction", "code", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "update", "Lorg/jetbrains/anko/db/UpdateQueryBuilder;", "(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;[Lkotlin/Pair;)Lorg/jetbrains/anko/db/UpdateQueryBuilder;", "sqlite-base_release"}, k = 2, mv = {1, 1, 13})
/* compiled from: Database.kt */
public final class DatabaseKt {
    private static final Pattern ARG_PATTERN;

    public static final long insert(@NotNull SQLiteDatabase sQLiteDatabase, @NotNull String str, @NotNull Pair<String, ? extends Object>... pairArr) {
        Intrinsics.checkParameterIsNotNull(sQLiteDatabase, "receiver$0");
        Intrinsics.checkParameterIsNotNull(str, "tableName");
        Intrinsics.checkParameterIsNotNull(pairArr, "values");
        return sQLiteDatabase.insert(str, null, toContentValues(pairArr));
    }

    public static final long insertOrThrow(@NotNull SQLiteDatabase sQLiteDatabase, @NotNull String str, @NotNull Pair<String, ? extends Object>... pairArr) {
        Intrinsics.checkParameterIsNotNull(sQLiteDatabase, "receiver$0");
        Intrinsics.checkParameterIsNotNull(str, "tableName");
        Intrinsics.checkParameterIsNotNull(pairArr, "values");
        return sQLiteDatabase.insertOrThrow(str, null, toContentValues(pairArr));
    }

    public static final long replace(@NotNull SQLiteDatabase sQLiteDatabase, @NotNull String str, @NotNull Pair<String, ? extends Object>... pairArr) {
        Intrinsics.checkParameterIsNotNull(sQLiteDatabase, "receiver$0");
        Intrinsics.checkParameterIsNotNull(str, "tableName");
        Intrinsics.checkParameterIsNotNull(pairArr, "values");
        return sQLiteDatabase.replace(str, null, toContentValues(pairArr));
    }

    public static final long replaceOrThrow(@NotNull SQLiteDatabase sQLiteDatabase, @NotNull String str, @NotNull Pair<String, ? extends Object>... pairArr) {
        Intrinsics.checkParameterIsNotNull(sQLiteDatabase, "receiver$0");
        Intrinsics.checkParameterIsNotNull(str, "tableName");
        Intrinsics.checkParameterIsNotNull(pairArr, "values");
        return sQLiteDatabase.replaceOrThrow(str, null, toContentValues(pairArr));
    }

    public static final void transaction(@org.jetbrains.annotations.NotNull android.database.sqlite.SQLiteDatabase r1, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super android.database.sqlite.SQLiteDatabase, kotlin.Unit> r2) {
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
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r1, r0);
        r0 = "code";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r2, r0);
        r1.beginTransaction();	 Catch:{ TransactionAbortException -> 0x0019, all -> 0x0014 }
        r2.invoke(r1);	 Catch:{ TransactionAbortException -> 0x0019, all -> 0x0014 }
        r1.setTransactionSuccessful();	 Catch:{ TransactionAbortException -> 0x0019, all -> 0x0014 }
        goto L_0x0019;
    L_0x0014:
        r2 = move-exception;
        r1.endTransaction();
        throw r2;
    L_0x0019:
        r1.endTransaction();
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jetbrains.anko.db.DatabaseKt.transaction(android.database.sqlite.SQLiteDatabase, kotlin.jvm.functions.Function1):void");
    }

    @NotNull
    public static final SelectQueryBuilder select(@NotNull SQLiteDatabase sQLiteDatabase, @NotNull String str) {
        Intrinsics.checkParameterIsNotNull(sQLiteDatabase, "receiver$0");
        Intrinsics.checkParameterIsNotNull(str, "tableName");
        return new AndroidSdkDatabaseSelectQueryBuilder(sQLiteDatabase, str);
    }

    @NotNull
    public static final SelectQueryBuilder select(@NotNull SQLiteDatabase sQLiteDatabase, @NotNull String str, @NotNull String... strArr) {
        Intrinsics.checkParameterIsNotNull(sQLiteDatabase, "receiver$0");
        Intrinsics.checkParameterIsNotNull(str, "tableName");
        Intrinsics.checkParameterIsNotNull(strArr, "columns");
        AndroidSdkDatabaseSelectQueryBuilder androidSdkDatabaseSelectQueryBuilder = new AndroidSdkDatabaseSelectQueryBuilder(sQLiteDatabase, str);
        androidSdkDatabaseSelectQueryBuilder.columns((String[]) Arrays.copyOf(strArr, strArr.length));
        return androidSdkDatabaseSelectQueryBuilder;
    }

    @NotNull
    public static final UpdateQueryBuilder update(@NotNull SQLiteDatabase sQLiteDatabase, @NotNull String str, @NotNull Pair<String, ? extends Object>... pairArr) {
        Intrinsics.checkParameterIsNotNull(sQLiteDatabase, "receiver$0");
        Intrinsics.checkParameterIsNotNull(str, "tableName");
        Intrinsics.checkParameterIsNotNull(pairArr, "values");
        return new AndroidSdkDatabaseUpdateQueryBuilder(sQLiteDatabase, str, pairArr);
    }

    public static /* synthetic */ int delete$default(SQLiteDatabase sQLiteDatabase, String str, String str2, Pair[] pairArr, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = "";
        }
        return delete(sQLiteDatabase, str, str2, pairArr);
    }

    public static final int delete(@NotNull SQLiteDatabase sQLiteDatabase, @NotNull String str, @NotNull String str2, @NotNull Pair<String, ? extends Object>... pairArr) {
        Intrinsics.checkParameterIsNotNull(sQLiteDatabase, "receiver$0");
        Intrinsics.checkParameterIsNotNull(str, "tableName");
        Intrinsics.checkParameterIsNotNull(str2, "whereClause");
        Intrinsics.checkParameterIsNotNull(pairArr, "args");
        return sQLiteDatabase.delete(str, applyArguments(str2, (Pair[]) Arrays.copyOf(pairArr, pairArr.length)), null);
    }

    public static /* synthetic */ void createTable$default(SQLiteDatabase sQLiteDatabase, String str, boolean z, Pair[] pairArr, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        createTable(sQLiteDatabase, str, z, pairArr);
    }

    public static final void createTable(@NotNull SQLiteDatabase sQLiteDatabase, @NotNull String str, boolean z, @NotNull Pair<String, ? extends SqlType>... pairArr) {
        Intrinsics.checkParameterIsNotNull(sQLiteDatabase, "receiver$0");
        Intrinsics.checkParameterIsNotNull(str, "tableName");
        Intrinsics.checkParameterIsNotNull(pairArr, "columns");
        str = StringsKt__StringsJVMKt.replace$default(str, "`", "``", false, 4, null);
        z = z ? "IF NOT EXISTS" : "";
        Collection arrayList = new ArrayList(pairArr.length);
        for (Pair pair : pairArr) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append((String) pair.getFirst());
            stringBuilder.append(' ');
            stringBuilder.append(((SqlType) pair.getSecond()).render());
            arrayList.add(stringBuilder.toString());
        }
        pairArr = new StringBuilder();
        pairArr.append("CREATE TABLE ");
        pairArr.append(z);
        pairArr.append(" `");
        pairArr.append(str);
        pairArr.append("`(");
        sQLiteDatabase.execSQL(CollectionsKt___CollectionsKt.joinToString$default((List) arrayList, ", ", pairArr.toString(), ");", 0, null, null, 56, null));
    }

    public static /* synthetic */ void dropTable$default(SQLiteDatabase sQLiteDatabase, String str, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        dropTable(sQLiteDatabase, str, z);
    }

    public static final void dropTable(@NotNull SQLiteDatabase sQLiteDatabase, @NotNull String str, boolean z) {
        Intrinsics.checkParameterIsNotNull(sQLiteDatabase, "receiver$0");
        Intrinsics.checkParameterIsNotNull(str, "tableName");
        str = StringsKt__StringsJVMKt.replace$default(str, "`", "``", false, 4, null);
        z = z ? "IF EXISTS" : "";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("DROP TABLE ");
        stringBuilder.append(z);
        stringBuilder.append(" `");
        stringBuilder.append(str);
        stringBuilder.append("`;");
        sQLiteDatabase.execSQL(stringBuilder.toString());
    }

    public static /* synthetic */ void createIndex$default(SQLiteDatabase sQLiteDatabase, String str, String str2, boolean z, boolean z2, String[] strArr, int i, Object obj) {
        createIndex(sQLiteDatabase, str, str2, (i & 4) != null ? false : z, i & 8 ? false : z2, strArr);
    }

    public static final void createIndex(@NotNull SQLiteDatabase sQLiteDatabase, @NotNull String str, @NotNull String str2, boolean z, boolean z2, @NotNull String... strArr) {
        Intrinsics.checkParameterIsNotNull(sQLiteDatabase, "receiver$0");
        Intrinsics.checkParameterIsNotNull(str, "indexName");
        Intrinsics.checkParameterIsNotNull(str2, "tableName");
        Intrinsics.checkParameterIsNotNull(strArr, "columns");
        str = StringsKt__StringsJVMKt.replace$default(str, "`", "``", false, 4, null);
        str2 = StringsKt__StringsJVMKt.replace$default(str2, "`", "``", false, 4, null);
        z2 = z2 ? "IF NOT EXISTS" : "";
        z = z ? "UNIQUE" : "";
        CharSequence charSequence = ",";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("CREATE ");
        stringBuilder.append(z);
        stringBuilder.append(" INDEX ");
        stringBuilder.append(z2);
        stringBuilder.append(" `");
        stringBuilder.append(str);
        stringBuilder.append("` ON `");
        stringBuilder.append(str2);
        stringBuilder.append("`(");
        sQLiteDatabase.execSQL(ArraysKt___ArraysKt.joinToString$default((Object[]) strArr, charSequence, (CharSequence) stringBuilder.toString(), (CharSequence) ");", 0, null, null, 56, null));
    }

    public static /* synthetic */ void dropIndex$default(SQLiteDatabase sQLiteDatabase, String str, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        dropIndex(sQLiteDatabase, str, z);
    }

    public static final void dropIndex(@NotNull SQLiteDatabase sQLiteDatabase, @NotNull String str, boolean z) {
        Intrinsics.checkParameterIsNotNull(sQLiteDatabase, "receiver$0");
        Intrinsics.checkParameterIsNotNull(str, "indexName");
        str = StringsKt__StringsJVMKt.replace$default(str, "`", "``", false, 4, null);
        z = z ? "IF EXISTS" : "";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("DROP INDEX ");
        stringBuilder.append(z);
        stringBuilder.append(" `");
        stringBuilder.append(str);
        stringBuilder.append("`;");
        sQLiteDatabase.execSQL(stringBuilder.toString());
    }

    static {
        Pattern compile = Pattern.compile("([^\\\\])\\{([^{}]+)\\}");
        Intrinsics.checkExpressionValueIsNotNull(compile, "Pattern.compile(\"([^\\\\\\\\])\\\\{([^{}]+)\\\\}\")");
        ARG_PATTERN = compile;
    }

    @NotNull
    public static final String applyArguments(@NotNull String str, @NotNull Pair<String, ? extends Object>... pairArr) {
        Intrinsics.checkParameterIsNotNull(str, "whereClause");
        Intrinsics.checkParameterIsNotNull(pairArr, "args");
        HashMap hashMap = new HashMap();
        for (Pair pair : pairArr) {
            hashMap.put(pair.getFirst(), pair.getSecond());
        }
        return applyArguments(str, (Map) hashMap);
    }

    @NotNull
    public static final String applyArguments(@NotNull String str, @NotNull Map<String, ? extends Object> map) {
        Intrinsics.checkParameterIsNotNull(str, "whereClause");
        Intrinsics.checkParameterIsNotNull(map, "args");
        Matcher matcher = ARG_PATTERN.matcher(str);
        StringBuffer stringBuffer = new StringBuffer(str.length());
        while (matcher.find() != null) {
            str = matcher.group(2);
            Object obj = map.get(str);
            if (obj != null) {
                StringBuilder stringBuilder;
                if ((obj instanceof Integer) == null && (obj instanceof Long) == null && (obj instanceof Byte) == null) {
                    if ((obj instanceof Short) == null) {
                        if ((obj instanceof Boolean) != null) {
                            str = ((Boolean) obj).booleanValue() != null ? "1" : "0";
                        } else {
                            if ((obj instanceof Float) == null) {
                                if ((obj instanceof Double) == null) {
                                    str = new StringBuilder();
                                    String replace$default = StringsKt__StringsJVMKt.replace$default(obj.toString(), "'", "''", false, 4, null);
                                    StringBuilder stringBuilder2 = new StringBuilder();
                                    stringBuilder2.append(String.valueOf('\''));
                                    stringBuilder2.append(replace$default);
                                    str.append(stringBuilder2.toString());
                                    str.append('\'');
                                    str = str.toString();
                                }
                            }
                            str = obj.toString();
                        }
                        stringBuilder = new StringBuilder();
                        stringBuilder.append(matcher.group(1));
                        stringBuilder.append(str);
                        matcher.appendReplacement(stringBuffer, stringBuilder.toString());
                    }
                }
                str = obj.toString();
                stringBuilder = new StringBuilder();
                stringBuilder.append(matcher.group(1));
                stringBuilder.append(str);
                matcher.appendReplacement(stringBuffer, stringBuilder.toString());
            } else {
                StringBuilder stringBuilder3 = new StringBuilder();
                stringBuilder3.append("Can't find a value for key ");
                stringBuilder3.append(str);
                throw ((Throwable) new IllegalStateException(stringBuilder3.toString()));
            }
        }
        matcher.appendTail(stringBuffer);
        str = stringBuffer.toString();
        Intrinsics.checkExpressionValueIsNotNull(str, "buffer.toString()");
        return str;
    }

    @NotNull
    public static final ContentValues toContentValues(@NotNull Pair<String, ? extends Object>[] pairArr) {
        Intrinsics.checkParameterIsNotNull(pairArr, "receiver$0");
        ContentValues contentValues = new ContentValues();
        for (Pair pair : pairArr) {
            String str = (String) pair.component1();
            Object component2 = pair.component2();
            if (component2 == null) {
                contentValues.putNull(str);
            } else if (component2 instanceof Boolean) {
                contentValues.put(str, (Boolean) component2);
            } else if (component2 instanceof Byte) {
                contentValues.put(str, (Byte) component2);
            } else if (component2 instanceof byte[]) {
                contentValues.put(str, (byte[]) component2);
            } else if (component2 instanceof Double) {
                contentValues.put(str, (Double) component2);
            } else if (component2 instanceof Float) {
                contentValues.put(str, (Float) component2);
            } else if (component2 instanceof Integer) {
                contentValues.put(str, (Integer) component2);
            } else if (component2 instanceof Long) {
                contentValues.put(str, (Long) component2);
            } else if (component2 instanceof Short) {
                contentValues.put(str, (Short) component2);
            } else if (component2 instanceof String) {
                contentValues.put(str, (String) component2);
            } else {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Non-supported value type: ");
                stringBuilder.append(component2.getClass().getName());
                throw ((Throwable) new IllegalArgumentException(stringBuilder.toString()));
            }
        }
        return contentValues;
    }
}
