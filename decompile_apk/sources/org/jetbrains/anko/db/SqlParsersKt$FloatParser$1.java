package org.jetbrains.anko.db;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0006\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "p1", "", "invoke"}, k = 3, mv = {1, 1, 13})
/* compiled from: SqlParsers.kt */
final class SqlParsersKt$FloatParser$1 extends FunctionReference implements Function1<Double, Float> {
    public static final SqlParsersKt$FloatParser$1 INSTANCE = new SqlParsersKt$FloatParser$1();

    SqlParsersKt$FloatParser$1() {
        super(1);
    }

    public final String getName() {
        return "toFloat";
    }

    public final KDeclarationContainer getOwner() {
        return Reflection.getOrCreateKotlinClass(Double.TYPE);
    }

    public final String getSignature() {
        return "floatValue()F";
    }

    public final float invoke(double d) {
        return (float) d;
    }
}
