package org.jetbrains.anko.db;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\n\n\u0000\n\u0002\u0010\t\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "p1", "", "invoke"}, k = 3, mv = {1, 1, 13})
/* compiled from: SqlParsers.kt */
final class SqlParsersKt$ShortParser$1 extends FunctionReference implements Function1<Long, Short> {
    public static final SqlParsersKt$ShortParser$1 INSTANCE = new SqlParsersKt$ShortParser$1();

    SqlParsersKt$ShortParser$1() {
        super(1);
    }

    public final String getName() {
        return "toShort";
    }

    public final KDeclarationContainer getOwner() {
        return Reflection.getOrCreateKotlinClass(Long.TYPE);
    }

    public final String getSignature() {
        return "shortValue()S";
    }

    public final short invoke(long j) {
        return (short) ((int) j);
    }
}
