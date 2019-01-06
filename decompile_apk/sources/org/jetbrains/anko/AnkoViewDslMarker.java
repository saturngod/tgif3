package org.jetbrains.anko;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import kotlin.DslMarker;
import kotlin.Metadata;
import kotlin.annotation.AnnotationTarget;

@Target({})
@kotlin.annotation.Target(allowedTargets = {AnnotationTarget.TYPE})
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0000¨\u0006\u0002"}, d2 = {"Lorg/jetbrains/anko/AnkoViewDslMarker;", "", "commons-base_release"}, k = 1, mv = {1, 1, 13})
@DslMarker
@Retention(RetentionPolicy.RUNTIME)
/* compiled from: Ui.kt */
public @interface AnkoViewDslMarker {
}
