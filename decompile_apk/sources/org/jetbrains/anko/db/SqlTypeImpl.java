package org.jetbrains.anko.db;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0012\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005J\u0011\u0010\t\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u000bH\u0002J\b\u0010\f\u001a\u00020\u0003H\u0016R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\r"}, d2 = {"Lorg/jetbrains/anko/db/SqlTypeImpl;", "Lorg/jetbrains/anko/db/SqlType;", "name", "", "modifiers", "(Ljava/lang/String;Ljava/lang/String;)V", "getModifiers", "()Ljava/lang/String;", "getName", "plus", "m", "Lorg/jetbrains/anko/db/SqlTypeModifier;", "render", "sqlite-base_release"}, k = 1, mv = {1, 1, 13})
/* compiled from: sqlTypes.kt */
class SqlTypeImpl implements SqlType {
    @Nullable
    private final String modifiers;
    @NotNull
    private final String name;

    public SqlTypeImpl(@NotNull String str, @Nullable String str2) {
        Intrinsics.checkParameterIsNotNull(str, "name");
        this.name = str;
        this.modifiers = str2;
    }

    public /* synthetic */ SqlTypeImpl(String str, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 2) != 0) {
            str2 = null;
        }
        this(str, str2);
    }

    @Nullable
    public final String getModifiers() {
        return this.modifiers;
    }

    @NotNull
    public String getName() {
        return this.name;
    }

    @NotNull
    public String render() {
        if (this.modifiers == null) {
            return getName();
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getName());
        stringBuilder.append(' ');
        stringBuilder.append(this.modifiers);
        return stringBuilder.toString();
    }

    @NotNull
    public SqlType plus(@NotNull SqlTypeModifier sqlTypeModifier) {
        Intrinsics.checkParameterIsNotNull(sqlTypeModifier, "m");
        String name = getName();
        if (this.modifiers == null) {
            sqlTypeModifier = sqlTypeModifier.getModifier();
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.modifiers);
            stringBuilder.append(' ');
            stringBuilder.append(sqlTypeModifier.getModifier());
            sqlTypeModifier = stringBuilder.toString();
        }
        return new SqlTypeImpl(name, sqlTypeModifier);
    }
}
