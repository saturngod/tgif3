package android.support.v4.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class ArrayMap<K, V> extends SimpleArrayMap<K, V> implements Map<K, V> {
    @Nullable
    MapCollections<K, V> mCollections;

    /* renamed from: android.support.v4.util.ArrayMap$1 */
    class C02931 extends MapCollections<K, V> {
        C02931() {
        }

        protected int colGetSize() {
            return ArrayMap.this.mSize;
        }

        protected Object colGetEntry(int i, int i2) {
            return ArrayMap.this.mArray[(i << 1) + i2];
        }

        protected int colIndexOfKey(Object obj) {
            return ArrayMap.this.indexOfKey(obj);
        }

        protected int colIndexOfValue(Object obj) {
            return ArrayMap.this.indexOfValue(obj);
        }

        protected Map<K, V> colGetMap() {
            return ArrayMap.this;
        }

        protected void colPut(K k, V v) {
            ArrayMap.this.put(k, v);
        }

        protected V colSetValue(int i, V v) {
            return ArrayMap.this.setValueAt(i, v);
        }

        protected void colRemoveAt(int i) {
            ArrayMap.this.removeAt(i);
        }

        protected void colClear() {
            ArrayMap.this.clear();
        }
    }

    public ArrayMap(int i) {
        super(i);
    }

    public ArrayMap(SimpleArrayMap simpleArrayMap) {
        super(simpleArrayMap);
    }

    private MapCollections<K, V> getCollection() {
        if (this.mCollections == null) {
            this.mCollections = new C02931();
        }
        return this.mCollections;
    }

    public boolean containsAll(@NonNull Collection<?> collection) {
        return MapCollections.containsAllHelper(this, collection);
    }

    public void putAll(Map<? extends K, ? extends V> map) {
        ensureCapacity(this.mSize + map.size());
        map = map.entrySet().iterator();
        while (map.hasNext()) {
            Entry entry = (Entry) map.next();
            put(entry.getKey(), entry.getValue());
        }
    }

    public boolean removeAll(@NonNull Collection<?> collection) {
        return MapCollections.removeAllHelper(this, collection);
    }

    public boolean retainAll(@NonNull Collection<?> collection) {
        return MapCollections.retainAllHelper(this, collection);
    }

    public Set<Entry<K, V>> entrySet() {
        return getCollection().getEntrySet();
    }

    public Set<K> keySet() {
        return getCollection().getKeySet();
    }

    public Collection<V> values() {
        return getCollection().getValues();
    }
}
