package kotlin.jvm.internal;

import kotlin.SinceKotlin;
import kotlin.reflect.KMutableProperty;

public abstract class MutablePropertyReference extends PropertyReference implements KMutableProperty {
    @SinceKotlin(version = "1.1")
    public MutablePropertyReference(Object obj) {
        super(obj);
    }
}
