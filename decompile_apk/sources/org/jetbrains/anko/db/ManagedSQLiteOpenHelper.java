package org.jetbrains.anko.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\b\u0010\u000f\u001a\u00020\u0010H\u0002J\b\u0010\u0011\u001a\u00020\u000eH\u0002J*\u0010\u0012\u001a\u0002H\u0013\"\u0004\b\u0000\u0010\u00132\u0017\u0010\u0014\u001a\u0013\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u0002H\u00130\u0015¢\u0006\u0002\b\u0016¢\u0006\u0002\u0010\u0017R\u000e\u0010\u000b\u001a\u00020\fX\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u000e¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lorg/jetbrains/anko/db/ManagedSQLiteOpenHelper;", "Landroid/database/sqlite/SQLiteOpenHelper;", "ctx", "Landroid/content/Context;", "name", "", "factory", "Landroid/database/sqlite/SQLiteDatabase$CursorFactory;", "version", "", "(Landroid/content/Context;Ljava/lang/String;Landroid/database/sqlite/SQLiteDatabase$CursorFactory;I)V", "counter", "Ljava/util/concurrent/atomic/AtomicInteger;", "db", "Landroid/database/sqlite/SQLiteDatabase;", "closeDatabase", "", "openDatabase", "use", "T", "f", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "sqlite-base_release"}, k = 1, mv = {1, 1, 13})
/* compiled from: Database.kt */
public abstract class ManagedSQLiteOpenHelper extends SQLiteOpenHelper {
    private final AtomicInteger counter;
    private SQLiteDatabase db;

    public /* synthetic */ ManagedSQLiteOpenHelper(Context context, String str, CursorFactory cursorFactory, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i2 & 4) != null) {
            cursorFactory = null;
        }
        if ((i2 & 8) != 0) {
            i = 1;
        }
        this(context, str, cursorFactory, i);
    }

    public ManagedSQLiteOpenHelper(@NotNull Context context, @Nullable String str, @Nullable CursorFactory cursorFactory, int i) {
        Intrinsics.checkParameterIsNotNull(context, "ctx");
        super(context, str, cursorFactory, i);
        this.counter = new AtomicInteger();
    }

    public final <T> T use(@NotNull Function1<? super SQLiteDatabase, ? extends T> function1) {
        Intrinsics.checkParameterIsNotNull(function1, "f");
        try {
            function1 = function1.invoke(openDatabase());
            return function1;
        } finally {
            closeDatabase();
        }
    }

    private final synchronized SQLiteDatabase openDatabase() {
        SQLiteDatabase sQLiteDatabase;
        if (this.counter.incrementAndGet() == 1) {
            this.db = getWritableDatabase();
        }
        sQLiteDatabase = this.db;
        if (sQLiteDatabase == null) {
            Intrinsics.throwNpe();
        }
        return sQLiteDatabase;
    }

    private final synchronized void closeDatabase() {
        if (this.counter.decrementAndGet() == 0) {
            SQLiteDatabase sQLiteDatabase = this.db;
            if (sQLiteDatabase != null) {
                sQLiteDatabase.close();
            }
        }
    }
}
