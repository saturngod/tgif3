package kotlin.jvm.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class SpreadBuilder {
    private final ArrayList<Object> list;

    public SpreadBuilder(int i) {
        this.list = new ArrayList(i);
    }

    public void addSpread(Object obj) {
        if (obj != null) {
            if (obj instanceof Object[]) {
                Object[] objArr = (Object[]) obj;
                if (objArr.length > 0) {
                    this.list.ensureCapacity(this.list.size() + objArr.length);
                    for (Object add : objArr) {
                        this.list.add(add);
                    }
                }
            } else if (obj instanceof Collection) {
                this.list.addAll((Collection) obj);
            } else if (obj instanceof Iterable) {
                for (Object add2 : (Iterable) obj) {
                    this.list.add(add2);
                }
            } else if (obj instanceof Iterator) {
                Iterator it = (Iterator) obj;
                while (it.hasNext()) {
                    this.list.add(it.next());
                }
            } else {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Don't know how to spread ");
                stringBuilder.append(obj.getClass());
                throw new UnsupportedOperationException(stringBuilder.toString());
            }
        }
    }

    public int size() {
        return this.list.size();
    }

    public void add(Object obj) {
        this.list.add(obj);
    }

    public Object[] toArray(Object[] objArr) {
        return this.list.toArray(objArr);
    }
}
