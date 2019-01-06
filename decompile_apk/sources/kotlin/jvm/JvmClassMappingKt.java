package kotlin.jvm;

import java.lang.annotation.Annotation;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.TypeCastException;
import kotlin.jvm.internal.ClassBasedDeclarationContainer;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\u0010\u0011\n\u0002\b\u0002\u001a\u001f\u0010\u0018\u001a\u00020\u0019\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\r*\u0006\u0012\u0002\b\u00030\u001a¢\u0006\u0002\u0010\u001b\"'\u0010\u0000\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u0002H\u00028F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005\"-\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0007\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00018G¢\u0006\f\u0012\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000b\"&\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0007\"\b\b\u0000\u0010\u0002*\u00020\r*\u0002H\u00028Æ\u0002¢\u0006\u0006\u001a\u0004\b\n\u0010\u000e\";\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00010\u0007\"\b\b\u0000\u0010\u0002*\u00020\r*\b\u0012\u0004\u0012\u0002H\u00020\u00018Ç\u0002X\u0004¢\u0006\f\u0012\u0004\b\u000f\u0010\t\u001a\u0004\b\u0010\u0010\u000b\"+\u0010\u0011\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0007\"\b\b\u0000\u0010\u0002*\u00020\r*\b\u0012\u0004\u0012\u0002H\u00020\u00018F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u000b\"-\u0010\u0013\u001a\n\u0012\u0004\u0012\u0002H\u0002\u0018\u00010\u0007\"\b\b\u0000\u0010\u0002*\u00020\r*\b\u0012\u0004\u0012\u0002H\u00020\u00018F¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u000b\"+\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\r*\b\u0012\u0004\u0012\u0002H\u00020\u00078G¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017¨\u0006\u001c"}, d2 = {"annotationClass", "Lkotlin/reflect/KClass;", "T", "", "getAnnotationClass", "(Ljava/lang/annotation/Annotation;)Lkotlin/reflect/KClass;", "java", "Ljava/lang/Class;", "java$annotations", "(Lkotlin/reflect/KClass;)V", "getJavaClass", "(Lkotlin/reflect/KClass;)Ljava/lang/Class;", "javaClass", "", "(Ljava/lang/Object;)Ljava/lang/Class;", "javaClass$annotations", "getRuntimeClassOfKClassInstance", "javaObjectType", "getJavaObjectType", "javaPrimitiveType", "getJavaPrimitiveType", "kotlin", "getKotlinClass", "(Ljava/lang/Class;)Lkotlin/reflect/KClass;", "isArrayOf", "", "", "([Ljava/lang/Object;)Z", "kotlin-stdlib"}, k = 2, mv = {1, 1, 13})
@JvmName(name = "JvmClassMappingKt")
/* compiled from: JvmClassMapping.kt */
public final class JvmClassMappingKt {
    public static /* synthetic */ void java$annotations(KClass kClass) {
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use 'java' property to get Java class corresponding to this Kotlin class or cast this instance to Any if you really want to get the runtime Java class of this implementation of KClass.", replaceWith = @ReplaceWith(expression = "(this as Any).javaClass", imports = {}))
    public static /* synthetic */ void javaClass$annotations(KClass kClass) {
    }

    @NotNull
    @JvmName(name = "getJavaClass")
    public static final <T> Class<T> getJavaClass(@NotNull KClass<T> kClass) {
        Intrinsics.checkParameterIsNotNull(kClass, "receiver$0");
        kClass = ((ClassBasedDeclarationContainer) kClass).getJClass();
        if (kClass != null) {
            return kClass;
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.Class<T>");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @org.jetbrains.annotations.Nullable
    public static final <T> java.lang.Class<T> getJavaPrimitiveType(@org.jetbrains.annotations.NotNull kotlin.reflect.KClass<T> r1) {
        /*
        r0 = "receiver$0";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r1, r0);
        r1 = (kotlin.jvm.internal.ClassBasedDeclarationContainer) r1;
        r1 = r1.getJClass();
        r0 = r1.isPrimitive();
        if (r0 == 0) goto L_0x001c;
    L_0x0011:
        if (r1 == 0) goto L_0x0014;
    L_0x0013:
        return r1;
    L_0x0014:
        r1 = new kotlin.TypeCastException;
        r0 = "null cannot be cast to non-null type java.lang.Class<T>";
        r1.<init>(r0);
        throw r1;
    L_0x001c:
        r1 = r1.getName();
        if (r1 != 0) goto L_0x0024;
    L_0x0022:
        goto L_0x0090;
    L_0x0024:
        r0 = r1.hashCode();
        switch(r0) {
            case -2056817302: goto L_0x0085;
            case -527879800: goto L_0x007a;
            case -515992664: goto L_0x006f;
            case 155276373: goto L_0x0064;
            case 344809556: goto L_0x0059;
            case 398507100: goto L_0x004e;
            case 398795216: goto L_0x0043;
            case 399092968: goto L_0x0038;
            case 761287205: goto L_0x002d;
            default: goto L_0x002b;
        };
    L_0x002b:
        goto L_0x0090;
    L_0x002d:
        r0 = "java.lang.Double";
        r1 = r1.equals(r0);
        if (r1 == 0) goto L_0x0090;
    L_0x0035:
        r1 = java.lang.Double.TYPE;
        goto L_0x0091;
    L_0x0038:
        r0 = "java.lang.Void";
        r1 = r1.equals(r0);
        if (r1 == 0) goto L_0x0090;
    L_0x0040:
        r1 = java.lang.Void.TYPE;
        goto L_0x0091;
    L_0x0043:
        r0 = "java.lang.Long";
        r1 = r1.equals(r0);
        if (r1 == 0) goto L_0x0090;
    L_0x004b:
        r1 = java.lang.Long.TYPE;
        goto L_0x0091;
    L_0x004e:
        r0 = "java.lang.Byte";
        r1 = r1.equals(r0);
        if (r1 == 0) goto L_0x0090;
    L_0x0056:
        r1 = java.lang.Byte.TYPE;
        goto L_0x0091;
    L_0x0059:
        r0 = "java.lang.Boolean";
        r1 = r1.equals(r0);
        if (r1 == 0) goto L_0x0090;
    L_0x0061:
        r1 = java.lang.Boolean.TYPE;
        goto L_0x0091;
    L_0x0064:
        r0 = "java.lang.Character";
        r1 = r1.equals(r0);
        if (r1 == 0) goto L_0x0090;
    L_0x006c:
        r1 = java.lang.Character.TYPE;
        goto L_0x0091;
    L_0x006f:
        r0 = "java.lang.Short";
        r1 = r1.equals(r0);
        if (r1 == 0) goto L_0x0090;
    L_0x0077:
        r1 = java.lang.Short.TYPE;
        goto L_0x0091;
    L_0x007a:
        r0 = "java.lang.Float";
        r1 = r1.equals(r0);
        if (r1 == 0) goto L_0x0090;
    L_0x0082:
        r1 = java.lang.Float.TYPE;
        goto L_0x0091;
    L_0x0085:
        r0 = "java.lang.Integer";
        r1 = r1.equals(r0);
        if (r1 == 0) goto L_0x0090;
    L_0x008d:
        r1 = java.lang.Integer.TYPE;
        goto L_0x0091;
    L_0x0090:
        r1 = 0;
    L_0x0091:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.jvm.JvmClassMappingKt.getJavaPrimitiveType(kotlin.reflect.KClass):java.lang.Class<T>");
    }

    @NotNull
    public static final <T> Class<T> getJavaObjectType(@NotNull KClass<T> kClass) {
        Intrinsics.checkParameterIsNotNull(kClass, "receiver$0");
        kClass = ((ClassBasedDeclarationContainer) kClass).getJClass();
        if (kClass.isPrimitive()) {
            String name = kClass.getName();
            if (name != null) {
                switch (name.hashCode()) {
                    case -1325958191:
                        if (name.equals("double")) {
                            kClass = Double.class;
                            break;
                        }
                        break;
                    case 104431:
                        if (name.equals("int")) {
                            kClass = Integer.class;
                            break;
                        }
                        break;
                    case 3039496:
                        if (name.equals("byte")) {
                            kClass = Byte.class;
                            break;
                        }
                        break;
                    case 3052374:
                        if (name.equals("char")) {
                            kClass = Character.class;
                            break;
                        }
                        break;
                    case 3327612:
                        if (name.equals("long")) {
                            kClass = Long.class;
                            break;
                        }
                        break;
                    case 3625364:
                        if (name.equals("void")) {
                            kClass = Void.class;
                            break;
                        }
                        break;
                    case 64711720:
                        if (name.equals("boolean")) {
                            kClass = Boolean.class;
                            break;
                        }
                        break;
                    case 97526364:
                        if (name.equals("float")) {
                            kClass = Float.class;
                            break;
                        }
                        break;
                    case 109413500:
                        if (name.equals("short")) {
                            kClass = Short.class;
                            break;
                        }
                        break;
                    default:
                        break;
                }
            }
            if (kClass != null) {
                return kClass;
            }
            throw new TypeCastException("null cannot be cast to non-null type java.lang.Class<T>");
        } else if (kClass != null) {
            return kClass;
        } else {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.Class<T>");
        }
    }

    @NotNull
    @JvmName(name = "getKotlinClass")
    public static final <T> KClass<T> getKotlinClass(@NotNull Class<T> cls) {
        Intrinsics.checkParameterIsNotNull(cls, "receiver$0");
        return Reflection.getOrCreateKotlinClass(cls);
    }

    @NotNull
    public static final <T> Class<T> getJavaClass(@NotNull T t) {
        Intrinsics.checkParameterIsNotNull(t, "receiver$0");
        t = t.getClass();
        if (t != null) {
            return t;
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.Class<T>");
    }

    @NotNull
    @JvmName(name = "getRuntimeClassOfKClassInstance")
    public static final <T> Class<KClass<T>> getRuntimeClassOfKClassInstance(@NotNull KClass<T> kClass) {
        Intrinsics.checkParameterIsNotNull(kClass, "receiver$0");
        kClass = kClass.getClass();
        if (kClass != null) {
            return kClass;
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.Class<kotlin.reflect.KClass<T>>");
    }

    private static final <T> boolean isArrayOf(@NotNull Object[] objArr) {
        Intrinsics.reifiedOperationMarker(4, "T");
        return Object.class.isAssignableFrom(objArr.getClass().getComponentType());
    }

    @NotNull
    public static final <T extends Annotation> KClass<? extends T> getAnnotationClass(@NotNull T t) {
        Intrinsics.checkParameterIsNotNull(t, "receiver$0");
        t = t.annotationType();
        Intrinsics.checkExpressionValueIsNotNull(t, "(this as java.lang.annot…otation).annotationType()");
        t = getKotlinClass(t);
        if (t != null) {
            return t;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.reflect.KClass<out T>");
    }
}
