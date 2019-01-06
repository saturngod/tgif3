package org.jetbrains.anko.db;

import android.database.sqlite.SQLiteException;
import kotlin.Metadata;
import kotlin.jvm.functions.Function6;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u001d\u0010\u0002\u001a\u00028\u00002\u000e\u0010\u0003\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0004H\u0016¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, d2 = {"org/jetbrains/anko/db/SQLiteParserHelpersKt__SqlParserHelpersKt$rowParser$6", "Lorg/jetbrains/anko/db/RowParser;", "parseRow", "columns", "", "", "([Ljava/lang/Object;)Ljava/lang/Object;", "anko-sqlite_release"}, k = 1, mv = {1, 1, 13})
/* compiled from: SqlParserHelpers.kt */
public final class SQLiteParserHelpersKt__SqlParserHelpersKt$rowParser$6 implements RowParser<R> {
    final /* synthetic */ Function6 $parser;

    SQLiteParserHelpersKt__SqlParserHelpersKt$rowParser$6(Function6 function6) {
        this.$parser = function6;
    }

    public R parseRow(@NotNull Object[] objArr) {
        Intrinsics.checkParameterIsNotNull(objArr, "columns");
        if (objArr.length == 6) {
            return this.$parser.invoke(objArr[0], objArr[1], objArr[2], objArr[3], objArr[4], objArr[5]);
        }
        throw ((Throwable) new SQLiteException("Invalid row: 6 columns required"));
    }
}
