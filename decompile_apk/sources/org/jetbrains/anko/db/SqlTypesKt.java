package org.jetbrains.anko.db;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u000e\u0010\u0016\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0018\u001aC\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u00050\u001a2\u0006\u0010\u001b\u001a\u00020\u00182\u0006\u0010\u001c\u001a\u00020\u00182\u0006\u0010\u001d\u001a\u00020\u00182\u0012\u0010\u001e\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u001f\"\u00020\u0001¢\u0006\u0002\u0010 \u001a\u000e\u0010!\u001a\u00020\u00012\u0006\u0010\"\u001a\u00020#\u001a\u000e\u0010$\u001a\u00020\u00012\u0006\u0010\"\u001a\u00020#\u001a\u000e\u0010\u0014\u001a\u00020\u00012\u0006\u0010%\u001a\u00020&\"\u0011\u0010\u0000\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003\"\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007\"\u0011\u0010\n\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0003\"\u0011\u0010\f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u0007\"\u0011\u0010\u000e\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0003\"\u0011\u0010\u0010\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0007\"\u0011\u0010\u0012\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0007\"\u0011\u0010\u0014\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0003¨\u0006'"}, d2 = {"AUTOINCREMENT", "Lorg/jetbrains/anko/db/SqlTypeModifier;", "getAUTOINCREMENT", "()Lorg/jetbrains/anko/db/SqlTypeModifier;", "BLOB", "Lorg/jetbrains/anko/db/SqlType;", "getBLOB", "()Lorg/jetbrains/anko/db/SqlType;", "INTEGER", "getINTEGER", "NOT_NULL", "getNOT_NULL", "NULL", "getNULL", "PRIMARY_KEY", "getPRIMARY_KEY", "REAL", "getREAL", "TEXT", "getTEXT", "UNIQUE", "getUNIQUE", "DEFAULT", "value", "", "FOREIGN_KEY", "Lkotlin/Pair;", "columnName", "referenceTable", "referenceColumn", "actions", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;[Lorg/jetbrains/anko/db/SqlTypeModifier;)Lkotlin/Pair;", "ON_DELETE", "constraintActions", "Lorg/jetbrains/anko/db/ConstraintActions;", "ON_UPDATE", "conflictClause", "Lorg/jetbrains/anko/db/ConflictClause;", "sqlite-base_release"}, k = 2, mv = {1, 1, 13})
/* compiled from: sqlTypes.kt */
public final class SqlTypesKt {
    @NotNull
    private static final SqlTypeModifier AUTOINCREMENT = new SqlTypeModifierImpl("AUTOINCREMENT");
    @NotNull
    private static final SqlType BLOB = new SqlTypeImpl("BLOB", null, 2, null);
    @NotNull
    private static final SqlType INTEGER = new SqlTypeImpl("INTEGER", null, 2, null);
    @NotNull
    private static final SqlTypeModifier NOT_NULL = new SqlTypeModifierImpl("NOT NULL");
    @NotNull
    private static final SqlType NULL = new SqlTypeImpl("NULL", null, 2, null);
    @NotNull
    private static final SqlTypeModifier PRIMARY_KEY = new SqlTypeModifierImpl("PRIMARY KEY");
    @NotNull
    private static final SqlType REAL = new SqlTypeImpl("REAL", null, 2, null);
    @NotNull
    private static final SqlType TEXT = new SqlTypeImpl("TEXT", null, 2, null);
    @NotNull
    private static final SqlTypeModifier UNIQUE = new SqlTypeModifierImpl("UNIQUE");

    @NotNull
    public static final SqlType getNULL() {
        return NULL;
    }

    @NotNull
    public static final SqlType getINTEGER() {
        return INTEGER;
    }

    @NotNull
    public static final SqlType getREAL() {
        return REAL;
    }

    @NotNull
    public static final SqlType getTEXT() {
        return TEXT;
    }

    @NotNull
    public static final SqlType getBLOB() {
        return BLOB;
    }

    @NotNull
    public static final SqlTypeModifier ON_UPDATE(@NotNull ConstraintActions constraintActions) {
        Intrinsics.checkParameterIsNotNull(constraintActions, "constraintActions");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("ON UPDATE ");
        stringBuilder.append(constraintActions);
        return new SqlTypeModifierImpl(stringBuilder.toString());
    }

    @NotNull
    public static final SqlTypeModifier ON_DELETE(@NotNull ConstraintActions constraintActions) {
        Intrinsics.checkParameterIsNotNull(constraintActions, "constraintActions");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("ON DELETE ");
        stringBuilder.append(constraintActions);
        return new SqlTypeModifierImpl(stringBuilder.toString());
    }

    @NotNull
    public static final Pair<String, SqlType> FOREIGN_KEY(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull SqlTypeModifier... sqlTypeModifierArr) {
        Intrinsics.checkParameterIsNotNull(str, "columnName");
        Intrinsics.checkParameterIsNotNull(str2, "referenceTable");
        Intrinsics.checkParameterIsNotNull(str3, "referenceColumn");
        Intrinsics.checkParameterIsNotNull(sqlTypeModifierArr, "actions");
        String str4 = "";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("FOREIGN KEY(");
        stringBuilder.append(str);
        stringBuilder.append(") REFERENCES ");
        stringBuilder.append(str2);
        stringBuilder.append('(');
        stringBuilder.append(str3);
        stringBuilder.append(')');
        Collection collection = (Collection) new ArrayList(sqlTypeModifierArr.length);
        for (SqlTypeModifier modifier : sqlTypeModifierArr) {
            collection.add(modifier.getModifier());
        }
        stringBuilder.append(CollectionsKt___CollectionsKt.joinToString$default((List) collection, "", null, null, 0, null, (Function1) SqlTypesKt$FOREIGN_KEY$2.INSTANCE, 30, null));
        return TuplesKt.to(str4, new SqlTypeImpl(stringBuilder.toString(), null, 2, null));
    }

    @NotNull
    public static final SqlTypeModifier getPRIMARY_KEY() {
        return PRIMARY_KEY;
    }

    @NotNull
    public static final SqlTypeModifier getNOT_NULL() {
        return NOT_NULL;
    }

    @NotNull
    public static final SqlTypeModifier getAUTOINCREMENT() {
        return AUTOINCREMENT;
    }

    @NotNull
    public static final SqlTypeModifier getUNIQUE() {
        return UNIQUE;
    }

    @NotNull
    public static final SqlTypeModifier UNIQUE(@NotNull ConflictClause conflictClause) {
        Intrinsics.checkParameterIsNotNull(conflictClause, "conflictClause");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("UNIQUE ON CONFLICT ");
        stringBuilder.append(conflictClause);
        return new SqlTypeModifierImpl(stringBuilder.toString());
    }

    @NotNull
    public static final SqlTypeModifier DEFAULT(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "value");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("DEFAULT ");
        stringBuilder.append(str);
        return new SqlTypeModifierImpl(stringBuilder.toString());
    }
}
