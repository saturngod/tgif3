package kotlin.jvm.internal;

import kotlin.SinceKotlin;
import kotlin.reflect.KCallable;
import kotlin.reflect.KMutableProperty2;
import kotlin.reflect.KMutableProperty2.Setter;
import kotlin.reflect.KProperty2.Getter;

public abstract class MutablePropertyReference2 extends MutablePropertyReference implements KMutableProperty2 {
    protected KCallable computeReflected() {
        return Reflection.mutableProperty2(this);
    }

    public Object invoke(Object obj, Object obj2) {
        return get(obj, obj2);
    }

    public Getter getGetter() {
        return ((KMutableProperty2) getReflected()).getGetter();
    }

    public Setter getSetter() {
        return ((KMutableProperty2) getReflected()).getSetter();
    }

    @SinceKotlin(version = "1.1")
    public Object getDelegate(Object obj, Object obj2) {
        return ((KMutableProperty2) getReflected()).getDelegate(obj, obj2);
    }
}
