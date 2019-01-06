package kotlin.jvm.internal;

import kotlin.SinceKotlin;
import kotlin.reflect.KCallable;
import kotlin.reflect.KProperty0;
import kotlin.reflect.KProperty0.Getter;

public abstract class PropertyReference0 extends PropertyReference implements KProperty0 {
    @SinceKotlin(version = "1.1")
    public PropertyReference0(Object obj) {
        super(obj);
    }

    protected KCallable computeReflected() {
        return Reflection.property0(this);
    }

    public Object invoke() {
        return get();
    }

    public Getter getGetter() {
        return ((KProperty0) getReflected()).getGetter();
    }

    @SinceKotlin(version = "1.1")
    public Object getDelegate() {
        return ((KProperty0) getReflected()).getDelegate();
    }
}
