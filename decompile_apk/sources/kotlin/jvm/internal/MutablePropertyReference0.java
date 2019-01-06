package kotlin.jvm.internal;

import kotlin.SinceKotlin;
import kotlin.reflect.KCallable;
import kotlin.reflect.KMutableProperty0;
import kotlin.reflect.KMutableProperty0.Setter;
import kotlin.reflect.KProperty0.Getter;

public abstract class MutablePropertyReference0 extends MutablePropertyReference implements KMutableProperty0 {
    @SinceKotlin(version = "1.1")
    public MutablePropertyReference0(Object obj) {
        super(obj);
    }

    protected KCallable computeReflected() {
        return Reflection.mutableProperty0(this);
    }

    public Object invoke() {
        return get();
    }

    public Getter getGetter() {
        return ((KMutableProperty0) getReflected()).getGetter();
    }

    public Setter getSetter() {
        return ((KMutableProperty0) getReflected()).getSetter();
    }

    @SinceKotlin(version = "1.1")
    public Object getDelegate() {
        return ((KMutableProperty0) getReflected()).getDelegate();
    }
}
