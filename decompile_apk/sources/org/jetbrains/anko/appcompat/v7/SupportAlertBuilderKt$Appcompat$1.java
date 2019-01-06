package org.jetbrains.anko.appcompat.v7;

import android.content.Context;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u0015\u0010\u0002\u001a\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "Lorg/jetbrains/anko/appcompat/v7/AppcompatAlertBuilder;", "p1", "Landroid/content/Context;", "Lkotlin/ParameterName;", "name", "ctx", "invoke"}, k = 3, mv = {1, 1, 13})
/* compiled from: SupportAlertBuilder.kt */
final class SupportAlertBuilderKt$Appcompat$1 extends FunctionReference implements Function1<Context, AppcompatAlertBuilder> {
    public static final SupportAlertBuilderKt$Appcompat$1 INSTANCE = new SupportAlertBuilderKt$Appcompat$1();

    SupportAlertBuilderKt$Appcompat$1() {
        super(1);
    }

    public final String getName() {
        return "<init>";
    }

    public final KDeclarationContainer getOwner() {
        return Reflection.getOrCreateKotlinClass(AppcompatAlertBuilder.class);
    }

    public final String getSignature() {
        return "<init>(Landroid/content/Context;)V";
    }

    @NotNull
    public final AppcompatAlertBuilder invoke(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "p1");
        return new AppcompatAlertBuilder(context);
    }
}
