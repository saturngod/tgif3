package org.jetbrains.anko.db;

import java.lang.reflect.Constructor;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.anko.AnkoException;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u001d\u0010\u0007\u001a\u00028\u00002\u000e\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0003H\u0016¢\u0006\u0002\u0010\nRF\u0010\u0002\u001a8\u0012\u0014\u0012\u0012\u0012\u0002\b\u0003 \u0005*\b\u0012\u0002\b\u0003\u0018\u00010\u00040\u0004 \u0005*\u001c\u0012\u0016\b\u0001\u0012\u0012\u0012\u0002\b\u0003 \u0005*\b\u0012\u0002\b\u0003\u0018\u00010\u00040\u0004\u0018\u00010\u00030\u0003X\u0004¢\u0006\u0004\n\u0002\u0010\u0006¨\u0006\u000b"}, d2 = {"org/jetbrains/anko/db/ClassParserKt$classParser$1", "Lorg/jetbrains/anko/db/RowParser;", "parameterTypes", "", "Ljava/lang/Class;", "kotlin.jvm.PlatformType", "[Ljava/lang/Class;", "parseRow", "columns", "", "([Ljava/lang/Object;)Ljava/lang/Object;", "sqlite-base_release"}, k = 1, mv = {1, 1, 13})
/* compiled from: ClassParser.kt */
public final class ClassParserKt$classParser$1 implements RowParser<T> {
    final /* synthetic */ Constructor $preferredConstructor;
    private final Class<?>[] parameterTypes;

    ClassParserKt$classParser$1(Constructor constructor) {
        this.$preferredConstructor = constructor;
        Intrinsics.checkExpressionValueIsNotNull(constructor, "preferredConstructor");
        this.parameterTypes = constructor.getParameterTypes();
    }

    public T parseRow(@NotNull Object[] objArr) {
        Intrinsics.checkParameterIsNotNull(objArr, "columns");
        if (this.parameterTypes.length == objArr.length) {
            int i = 0;
            int length = this.parameterTypes.length - 1;
            if (length >= 0) {
                while (true) {
                    Class cls = this.parameterTypes[i];
                    Object obj = objArr[i];
                    if (!cls.isInstance(obj)) {
                        Intrinsics.checkExpressionValueIsNotNull(cls, "type");
                        objArr[i] = ClassParserKt.castValue(obj, cls);
                    }
                    if (i == length) {
                        break;
                    }
                    i++;
                }
            }
            return JavaSqliteUtils.newInstance(this.$preferredConstructor, objArr);
        }
        objArr = ArraysKt___ArraysKt.joinToString$default(objArr, null, (CharSequence) "[", (CharSequence) "]", 0, null, null, 57, null);
        Object obj2 = this.parameterTypes;
        Intrinsics.checkExpressionValueIsNotNull(obj2, "parameterTypes");
        String joinToString$default = ArraysKt___ArraysKt.joinToString$default((Object[]) obj2, null, (CharSequence) "[", (CharSequence) "]", 0, null, (Function1) ClassParserKt$classParser$1$parseRow$parameterTypesRendered$1.INSTANCE, 25, null);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Class parser for ");
        Constructor constructor = this.$preferredConstructor;
        Intrinsics.checkExpressionValueIsNotNull(constructor, "preferredConstructor");
        stringBuilder.append(constructor.getName());
        stringBuilder.append(' ');
        stringBuilder.append("failed to parse the row: ");
        stringBuilder.append(objArr);
        stringBuilder.append(" (constructor parameter types: ");
        stringBuilder.append(joinToString$default);
        stringBuilder.append(41);
        throw new AnkoException(stringBuilder.toString());
    }
}
