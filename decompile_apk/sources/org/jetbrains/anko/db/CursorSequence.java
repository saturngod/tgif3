package org.jetbrains.anko.db;

import android.database.Cursor;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u00020\u0001B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\t\u001a\u00020\nH\u0002R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/anko/db/CursorSequence;", "Lkotlin/sequences/Sequence;", "", "", "cursor", "Landroid/database/Cursor;", "(Landroid/database/Cursor;)V", "getCursor", "()Landroid/database/Cursor;", "iterator", "Lorg/jetbrains/anko/db/CursorIterator;", "sqlite-base_release"}, k = 1, mv = {1, 1, 13})
/* compiled from: SqlParsers.kt */
final class CursorSequence implements Sequence<Object[]> {
    @NotNull
    private final Cursor cursor;

    public CursorSequence(@NotNull Cursor cursor) {
        Intrinsics.checkParameterIsNotNull(cursor, "cursor");
        this.cursor = cursor;
    }

    @NotNull
    public final Cursor getCursor() {
        return this.cursor;
    }

    @NotNull
    public CursorIterator iterator() {
        return new CursorIterator(this.cursor);
    }
}
