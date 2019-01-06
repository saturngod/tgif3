package kotlin.jvm.internal;

import kotlin.SinceKotlin;
import kotlin.reflect.KCallable;
import kotlin.reflect.KProperty1;
import kotlin.reflect.KProperty1.Getter;

public abstract class PropertyReference1 extends PropertyReference implements KProperty1 {
    @SinceKotlin(version = "1.1")
    public PropertyReference1(Object obj) {
        super(obj);
    }

    protected KCallable computeReflected() {
        return Reflection.property1(this);
    }

    public Object invoke(Object obj) {
        return get(obj);
    }

    public Getter getGetter() {
        return ((KProperty1) getReflected()).getGetter();
    }

    @SinceKotlin(version = "1.1")
    public Object getDelegate(Object obj) {
        return ((KProperty1) getReflected()).getDelegate(obj);
    }
}
