package kotlin.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.PublishedApi;
import kotlin.SinceKotlin;
import kotlin.TuplesKt;
import kotlin.UByteArray;
import kotlin.UIntArray;
import kotlin.ULongArray;
import kotlin.UShortArray;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000H\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a1\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u00032\u000e\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0003H\u0001¢\u0006\u0004\b\u0005\u0010\u0006\u001a!\u0010\u0007\u001a\u00020\b\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0003H\u0001¢\u0006\u0004\b\t\u0010\n\u001a?\u0010\u000b\u001a\u00020\f\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u00032\n\u0010\r\u001a\u00060\u000ej\u0002`\u000f2\u0010\u0010\u0010\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00030\u0011H\u0002¢\u0006\u0004\b\u0012\u0010\u0013\u001a+\u0010\u0014\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0015\"\u0004\b\u0000\u0010\u0002*\u0012\u0012\u000e\b\u0001\u0012\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u00030\u0003¢\u0006\u0002\u0010\u0016\u001a8\u0010\u0017\u001a\u0002H\u0018\"\u0010\b\u0000\u0010\u0019*\u0006\u0012\u0002\b\u00030\u0003*\u0002H\u0018\"\u0004\b\u0001\u0010\u0018*\u0002H\u00192\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u0002H\u00180\u001bH\b¢\u0006\u0002\u0010\u001c\u001a)\u0010\u001d\u001a\u00020\u0001*\b\u0012\u0002\b\u0003\u0018\u00010\u0003H\b\u0002\u000e\n\f\b\u0000\u0012\u0002\u0018\u0001\u001a\u0004\b\u0003\u0010\u0000¢\u0006\u0002\u0010\u001e\u001aG\u0010\u001f\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0015\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00180\u00150 \"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0018*\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00180 0\u0003¢\u0006\u0002\u0010!¨\u0006\""}, d2 = {"contentDeepEqualsImpl", "", "T", "", "other", "contentDeepEquals", "([Ljava/lang/Object;[Ljava/lang/Object;)Z", "contentDeepToStringImpl", "", "contentDeepToString", "([Ljava/lang/Object;)Ljava/lang/String;", "contentDeepToStringInternal", "", "result", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "processed", "", "contentDeepToStringInternal$ArraysKt__ArraysKt", "([Ljava/lang/Object;Ljava/lang/StringBuilder;Ljava/util/List;)V", "flatten", "", "([[Ljava/lang/Object;)Ljava/util/List;", "ifEmpty", "R", "C", "defaultValue", "Lkotlin/Function0;", "([Ljava/lang/Object;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "isNullOrEmpty", "([Ljava/lang/Object;)Z", "unzip", "Lkotlin/Pair;", "([Lkotlin/Pair;)Lkotlin/Pair;", "kotlin-stdlib"}, k = 5, mv = {1, 1, 13}, xi = 1, xs = "kotlin/collections/ArraysKt")
/* compiled from: Arrays.kt */
class ArraysKt__ArraysKt extends ArraysKt__ArraysJVMKt {
    @NotNull
    public static final <T> List<T> flatten(@NotNull T[][] tArr) {
        Intrinsics.checkParameterIsNotNull(tArr, "receiver$0");
        int i = 0;
        for (Object obj : (Object[]) tArr) {
            i += ((Object[]) obj).length;
        }
        ArrayList arrayList = new ArrayList(i);
        for (Object[] addAll : tArr) {
            CollectionsKt__MutableCollectionsKt.addAll((Collection) arrayList, addAll);
        }
        return arrayList;
    }

    @NotNull
    public static final <T, R> Pair<List<T>, List<R>> unzip(@NotNull Pair<? extends T, ? extends R>[] pairArr) {
        Intrinsics.checkParameterIsNotNull(pairArr, "receiver$0");
        ArrayList arrayList = new ArrayList(pairArr.length);
        ArrayList arrayList2 = new ArrayList(pairArr.length);
        for (Pair pair : pairArr) {
            arrayList.add(pair.getFirst());
            arrayList2.add(pair.getSecond());
        }
        return TuplesKt.to(arrayList, arrayList2);
    }

    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final boolean isNullOrEmpty(@Nullable Object[] objArr) {
        if (objArr != null) {
            if ((objArr.length == null ? 1 : null) == null) {
                return false;
            }
        }
        return true;
    }

    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final <C extends 
/*
Method generation error in method: kotlin.collections.ArraysKt__ArraysKt.ifEmpty(java.lang.Object[], kotlin.jvm.functions.Function0):R, dex: classes.dex
java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$ArrayArg
	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:410)
	at jadx.core.dex.info.ClassInfo.extCls(ClassInfo.java:58)
	at jadx.core.codegen.ClassGen.useClass(ClassGen.java:438)
	at jadx.core.codegen.ClassGen.addGenericMap(ClassGen.java:201)
	at jadx.core.codegen.MethodGen.addDefinition(MethodGen.java:90)
	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:311)
	at jadx.core.codegen.ClassGen.addMethods(ClassGen.java:257)
	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:220)
	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:110)
	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:75)
	at jadx.core.codegen.CodeGen.visit(CodeGen.java:12)
	at jadx.core.ProcessClass.process(ProcessClass.java:40)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1603198149.run(Unknown Source)

*/

    @SinceKotlin(version = "1.3")
    @PublishedApi
    @JvmName(name = "contentDeepEquals")
    public static final <T> boolean contentDeepEquals(@NotNull T[] tArr, @NotNull T[] tArr2) {
        Intrinsics.checkParameterIsNotNull(tArr, "receiver$0");
        Intrinsics.checkParameterIsNotNull(tArr2, "other");
        if (tArr == tArr2) {
            return true;
        }
        if (tArr.length != tArr2.length) {
            return false;
        }
        int length = tArr.length;
        for (int i = 0; i < length; i++) {
            Object obj = tArr[i];
            Object obj2 = tArr2[i];
            if (obj != obj2) {
                if (obj != null) {
                    if (obj2 != null) {
                        if ((obj instanceof Object[]) && (obj2 instanceof Object[])) {
                            if (!contentDeepEquals((Object[]) obj, (Object[]) obj2)) {
                                return false;
                            }
                        } else if ((obj instanceof byte[]) && (obj2 instanceof byte[])) {
                            if (!Arrays.equals((byte[]) obj, (byte[]) obj2)) {
                                return false;
                            }
                        } else if ((obj instanceof short[]) && (obj2 instanceof short[])) {
                            if (!Arrays.equals((short[]) obj, (short[]) obj2)) {
                                return false;
                            }
                        } else if ((obj instanceof int[]) && (obj2 instanceof int[])) {
                            if (!Arrays.equals((int[]) obj, (int[]) obj2)) {
                                return false;
                            }
                        } else if ((obj instanceof long[]) && (obj2 instanceof long[])) {
                            if (!Arrays.equals((long[]) obj, (long[]) obj2)) {
                                return false;
                            }
                        } else if ((obj instanceof float[]) && (obj2 instanceof float[])) {
                            if (!Arrays.equals((float[]) obj, (float[]) obj2)) {
                                return false;
                            }
                        } else if ((obj instanceof double[]) && (obj2 instanceof double[])) {
                            if (!Arrays.equals((double[]) obj, (double[]) obj2)) {
                                return false;
                            }
                        } else if ((obj instanceof char[]) && (obj2 instanceof char[])) {
                            if (!Arrays.equals((char[]) obj, (char[]) obj2)) {
                                return false;
                            }
                        } else if ((obj instanceof boolean[]) && (obj2 instanceof boolean[])) {
                            if (!Arrays.equals((boolean[]) obj, (boolean[]) obj2)) {
                                return false;
                            }
                        } else if ((obj instanceof UByteArray) && (obj2 instanceof UByteArray)) {
                            if (!UArraysKt___UArraysKt.contentEquals-kdPth3s(((UByteArray) obj).unbox-impl(), ((UByteArray) obj2).unbox-impl())) {
                                return false;
                            }
                        } else if ((obj instanceof UShortArray) && (obj2 instanceof UShortArray)) {
                            if (!UArraysKt___UArraysKt.contentEquals-mazbYpA(((UShortArray) obj).unbox-impl(), ((UShortArray) obj2).unbox-impl())) {
                                return false;
                            }
                        } else if ((obj instanceof UIntArray) && (obj2 instanceof UIntArray)) {
                            if (!UArraysKt___UArraysKt.contentEquals-ctEhBpI(((UIntArray) obj).unbox-impl(), ((UIntArray) obj2).unbox-impl())) {
                                return false;
                            }
                        } else if ((obj instanceof ULongArray) && (obj2 instanceof ULongArray)) {
                            if (!UArraysKt___UArraysKt.contentEquals-us8wMrg(((ULongArray) obj).unbox-impl(), ((ULongArray) obj2).unbox-impl())) {
                                return false;
                            }
                        } else if ((Intrinsics.areEqual(obj, obj2) ^ 1) != 0) {
                            return false;
                        }
                    }
                }
                return false;
            }
        }
        return true;
    }

    @SinceKotlin(version = "1.3")
    @PublishedApi
    @NotNull
    @JvmName(name = "contentDeepToString")
    public static final <T> String contentDeepToString(@NotNull T[] tArr) {
        Intrinsics.checkParameterIsNotNull(tArr, "receiver$0");
        StringBuilder stringBuilder = new StringBuilder((RangesKt___RangesKt.coerceAtMost(tArr.length, 429496729) * 5) + 2);
        contentDeepToStringInternal$ArraysKt__ArraysKt(tArr, stringBuilder, new ArrayList());
        tArr = stringBuilder.toString();
        Intrinsics.checkExpressionValueIsNotNull(tArr, "StringBuilder(capacity).…builderAction).toString()");
        return tArr;
    }

    private static final <T> void contentDeepToStringInternal$ArraysKt__ArraysKt(@NotNull T[] tArr, StringBuilder stringBuilder, List<Object[]> list) {
        if (list.contains(tArr)) {
            stringBuilder.append("[...]");
            return;
        }
        list.add(tArr);
        stringBuilder.append('[');
        int length = tArr.length;
        for (int i = 0; i < length; i++) {
            if (i != 0) {
                stringBuilder.append(", ");
            }
            Object obj = tArr[i];
            if (obj == null) {
                stringBuilder.append("null");
            } else if (obj instanceof Object[]) {
                contentDeepToStringInternal$ArraysKt__ArraysKt((Object[]) obj, stringBuilder, list);
            } else if (obj instanceof byte[]) {
                r2 = Arrays.toString((byte[]) obj);
                Intrinsics.checkExpressionValueIsNotNull(r2, "java.util.Arrays.toString(this)");
                stringBuilder.append(r2);
            } else if (obj instanceof short[]) {
                r2 = Arrays.toString((short[]) obj);
                Intrinsics.checkExpressionValueIsNotNull(r2, "java.util.Arrays.toString(this)");
                stringBuilder.append(r2);
            } else if (obj instanceof int[]) {
                r2 = Arrays.toString((int[]) obj);
                Intrinsics.checkExpressionValueIsNotNull(r2, "java.util.Arrays.toString(this)");
                stringBuilder.append(r2);
            } else if (obj instanceof long[]) {
                r2 = Arrays.toString((long[]) obj);
                Intrinsics.checkExpressionValueIsNotNull(r2, "java.util.Arrays.toString(this)");
                stringBuilder.append(r2);
            } else if (obj instanceof float[]) {
                r2 = Arrays.toString((float[]) obj);
                Intrinsics.checkExpressionValueIsNotNull(r2, "java.util.Arrays.toString(this)");
                stringBuilder.append(r2);
            } else if (obj instanceof double[]) {
                r2 = Arrays.toString((double[]) obj);
                Intrinsics.checkExpressionValueIsNotNull(r2, "java.util.Arrays.toString(this)");
                stringBuilder.append(r2);
            } else if (obj instanceof char[]) {
                r2 = Arrays.toString((char[]) obj);
                Intrinsics.checkExpressionValueIsNotNull(r2, "java.util.Arrays.toString(this)");
                stringBuilder.append(r2);
            } else if (obj instanceof boolean[]) {
                r2 = Arrays.toString((boolean[]) obj);
                Intrinsics.checkExpressionValueIsNotNull(r2, "java.util.Arrays.toString(this)");
                stringBuilder.append(r2);
            } else if (obj instanceof UByteArray) {
                stringBuilder.append(UArraysKt___UArraysKt.contentToString-GBYM_sE(((UByteArray) obj).unbox-impl()));
            } else if (obj instanceof UShortArray) {
                stringBuilder.append(UArraysKt___UArraysKt.contentToString-rL5Bavg(((UShortArray) obj).unbox-impl()));
            } else if (obj instanceof UIntArray) {
                stringBuilder.append(UArraysKt___UArraysKt.contentToString--ajY-9A(((UIntArray) obj).unbox-impl()));
            } else if (obj instanceof ULongArray) {
                stringBuilder.append(UArraysKt___UArraysKt.contentToString-QwZRm1k(((ULongArray) obj).unbox-impl()));
            } else {
                stringBuilder.append(obj.toString());
            }
        }
        stringBuilder.append(93);
        list.remove(CollectionsKt__CollectionsKt.getLastIndex(list));
    }
}
