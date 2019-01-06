package android.support.v4.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

public class LruCache<K, V> {
    private int createCount;
    private int evictionCount;
    private int hitCount;
    private final LinkedHashMap<K, V> map;
    private int maxSize;
    private int missCount;
    private int putCount;
    private int size;

    @Nullable
    protected V create(@NonNull K k) {
        return null;
    }

    protected void entryRemoved(boolean z, @NonNull K k, @NonNull V v, @Nullable V v2) {
    }

    protected int sizeOf(@NonNull K k, @NonNull V v) {
        return 1;
    }

    public LruCache(int i) {
        if (i > 0) {
            this.maxSize = i;
            this.map = new LinkedHashMap(0, 0.75f, true);
            return;
        }
        throw new IllegalArgumentException("maxSize <= 0");
    }

    public void resize(int i) {
        if (i > 0) {
            synchronized (this) {
                this.maxSize = i;
            }
            trimToSize(i);
            return;
        }
        throw new IllegalArgumentException("maxSize <= 0");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @android.support.annotation.Nullable
    public final V get(@android.support.annotation.NonNull K r5) {
        /*
        r4 = this;
        if (r5 == 0) goto L_0x0054;
    L_0x0002:
        monitor-enter(r4);
        r0 = r4.map;	 Catch:{ all -> 0x0051 }
        r0 = r0.get(r5);	 Catch:{ all -> 0x0051 }
        if (r0 == 0) goto L_0x0013;
    L_0x000b:
        r5 = r4.hitCount;	 Catch:{ all -> 0x0051 }
        r5 = r5 + 1;
        r4.hitCount = r5;	 Catch:{ all -> 0x0051 }
        monitor-exit(r4);	 Catch:{ all -> 0x0051 }
        return r0;
    L_0x0013:
        r0 = r4.missCount;	 Catch:{ all -> 0x0051 }
        r0 = r0 + 1;
        r4.missCount = r0;	 Catch:{ all -> 0x0051 }
        monitor-exit(r4);	 Catch:{ all -> 0x0051 }
        r0 = r4.create(r5);
        if (r0 != 0) goto L_0x0022;
    L_0x0020:
        r5 = 0;
        return r5;
    L_0x0022:
        monitor-enter(r4);
        r1 = r4.createCount;	 Catch:{ all -> 0x004e }
        r1 = r1 + 1;
        r4.createCount = r1;	 Catch:{ all -> 0x004e }
        r1 = r4.map;	 Catch:{ all -> 0x004e }
        r1 = r1.put(r5, r0);	 Catch:{ all -> 0x004e }
        if (r1 == 0) goto L_0x0037;
    L_0x0031:
        r2 = r4.map;	 Catch:{ all -> 0x004e }
        r2.put(r5, r1);	 Catch:{ all -> 0x004e }
        goto L_0x0040;
    L_0x0037:
        r2 = r4.size;	 Catch:{ all -> 0x004e }
        r3 = r4.safeSizeOf(r5, r0);	 Catch:{ all -> 0x004e }
        r2 = r2 + r3;
        r4.size = r2;	 Catch:{ all -> 0x004e }
    L_0x0040:
        monitor-exit(r4);	 Catch:{ all -> 0x004e }
        if (r1 == 0) goto L_0x0048;
    L_0x0043:
        r2 = 0;
        r4.entryRemoved(r2, r5, r0, r1);
        return r1;
    L_0x0048:
        r5 = r4.maxSize;
        r4.trimToSize(r5);
        return r0;
    L_0x004e:
        r5 = move-exception;
        monitor-exit(r4);	 Catch:{ all -> 0x004e }
        throw r5;
    L_0x0051:
        r5 = move-exception;
        monitor-exit(r4);	 Catch:{ all -> 0x0051 }
        throw r5;
    L_0x0054:
        r5 = new java.lang.NullPointerException;
        r0 = "key == null";
        r5.<init>(r0);
        throw r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.util.LruCache.get(java.lang.Object):V");
    }

    @Nullable
    public final V put(@NonNull K k, @NonNull V v) {
        if (k == null || v == null) {
            throw new NullPointerException("key == null || value == null");
        }
        V put;
        synchronized (this) {
            this.putCount++;
            this.size += safeSizeOf(k, v);
            put = this.map.put(k, v);
            if (put != null) {
                this.size -= safeSizeOf(k, put);
            }
        }
        if (put != null) {
            entryRemoved(false, k, put, v);
        }
        trimToSize(this.maxSize);
        return put;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void trimToSize(int r5) {
        /*
        r4 = this;
    L_0x0000:
        monitor-enter(r4);
        r0 = r4.size;	 Catch:{ all -> 0x0071 }
        if (r0 < 0) goto L_0x0052;
    L_0x0005:
        r0 = r4.map;	 Catch:{ all -> 0x0071 }
        r0 = r0.isEmpty();	 Catch:{ all -> 0x0071 }
        if (r0 == 0) goto L_0x0011;
    L_0x000d:
        r0 = r4.size;	 Catch:{ all -> 0x0071 }
        if (r0 != 0) goto L_0x0052;
    L_0x0011:
        r0 = r4.size;	 Catch:{ all -> 0x0071 }
        if (r0 <= r5) goto L_0x0050;
    L_0x0015:
        r0 = r4.map;	 Catch:{ all -> 0x0071 }
        r0 = r0.isEmpty();	 Catch:{ all -> 0x0071 }
        if (r0 == 0) goto L_0x001e;
    L_0x001d:
        goto L_0x0050;
    L_0x001e:
        r0 = r4.map;	 Catch:{ all -> 0x0071 }
        r0 = r0.entrySet();	 Catch:{ all -> 0x0071 }
        r0 = r0.iterator();	 Catch:{ all -> 0x0071 }
        r0 = r0.next();	 Catch:{ all -> 0x0071 }
        r0 = (java.util.Map.Entry) r0;	 Catch:{ all -> 0x0071 }
        r1 = r0.getKey();	 Catch:{ all -> 0x0071 }
        r0 = r0.getValue();	 Catch:{ all -> 0x0071 }
        r2 = r4.map;	 Catch:{ all -> 0x0071 }
        r2.remove(r1);	 Catch:{ all -> 0x0071 }
        r2 = r4.size;	 Catch:{ all -> 0x0071 }
        r3 = r4.safeSizeOf(r1, r0);	 Catch:{ all -> 0x0071 }
        r2 = r2 - r3;
        r4.size = r2;	 Catch:{ all -> 0x0071 }
        r2 = r4.evictionCount;	 Catch:{ all -> 0x0071 }
        r3 = 1;
        r2 = r2 + r3;
        r4.evictionCount = r2;	 Catch:{ all -> 0x0071 }
        monitor-exit(r4);	 Catch:{ all -> 0x0071 }
        r2 = 0;
        r4.entryRemoved(r3, r1, r0, r2);
        goto L_0x0000;
    L_0x0050:
        monitor-exit(r4);	 Catch:{ all -> 0x0071 }
        return;
    L_0x0052:
        r5 = new java.lang.IllegalStateException;	 Catch:{ all -> 0x0071 }
        r0 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0071 }
        r0.<init>();	 Catch:{ all -> 0x0071 }
        r1 = r4.getClass();	 Catch:{ all -> 0x0071 }
        r1 = r1.getName();	 Catch:{ all -> 0x0071 }
        r0.append(r1);	 Catch:{ all -> 0x0071 }
        r1 = ".sizeOf() is reporting inconsistent results!";
        r0.append(r1);	 Catch:{ all -> 0x0071 }
        r0 = r0.toString();	 Catch:{ all -> 0x0071 }
        r5.<init>(r0);	 Catch:{ all -> 0x0071 }
        throw r5;	 Catch:{ all -> 0x0071 }
    L_0x0071:
        r5 = move-exception;
        monitor-exit(r4);	 Catch:{ all -> 0x0071 }
        throw r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.util.LruCache.trimToSize(int):void");
    }

    @Nullable
    public final V remove(@NonNull K k) {
        if (k != null) {
            V remove;
            synchronized (this) {
                remove = this.map.remove(k);
                if (remove != null) {
                    this.size -= safeSizeOf(k, remove);
                }
            }
            if (remove != null) {
                entryRemoved(false, k, remove, null);
            }
            return remove;
        }
        throw new NullPointerException("key == null");
    }

    private int safeSizeOf(K k, V v) {
        int sizeOf = sizeOf(k, v);
        if (sizeOf >= 0) {
            return sizeOf;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Negative size: ");
        stringBuilder.append(k);
        stringBuilder.append("=");
        stringBuilder.append(v);
        throw new IllegalStateException(stringBuilder.toString());
    }

    public final void evictAll() {
        trimToSize(-1);
    }

    public final synchronized int size() {
        return this.size;
    }

    public final synchronized int maxSize() {
        return this.maxSize;
    }

    public final synchronized int hitCount() {
        return this.hitCount;
    }

    public final synchronized int missCount() {
        return this.missCount;
    }

    public final synchronized int createCount() {
        return this.createCount;
    }

    public final synchronized int putCount() {
        return this.putCount;
    }

    public final synchronized int evictionCount() {
        return this.evictionCount;
    }

    public final synchronized Map<K, V> snapshot() {
        return new LinkedHashMap(this.map);
    }

    public final synchronized String toString() {
        int i;
        i = this.hitCount + this.missCount;
        i = i != 0 ? (this.hitCount * 100) / i : 0;
        return String.format(Locale.US, "LruCache[maxSize=%d,hits=%d,misses=%d,hitRate=%d%%]", new Object[]{Integer.valueOf(this.maxSize), Integer.valueOf(this.hitCount), Integer.valueOf(this.missCount), Integer.valueOf(i)});
    }
}
