package khttp.responses;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import khttp.extensions.ExtensionsKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 0}, d1 = {"\u0000!\n\u0000\n\u0002\u0010(\n\u0002\u0010\u0012\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\t\u0010\u0010\u001a\u00020\u0011H\u0002J\t\u0010\u0012\u001a\u00020\u0002H\u0002R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u0002X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0013"}, d2 = {"khttp/responses/GenericResponse$lineIterator$1", "", "", "(Lkhttp/responses/GenericResponse;[BI)V", "byteArrays", "getByteArrays", "()Ljava/util/Iterator;", "leftOver", "getLeftOver", "()[B", "setLeftOver", "([B)V", "overflow", "Ljava/util/ArrayList;", "getOverflow", "()Ljava/util/ArrayList;", "hasNext", "", "next", "khttp"}, k = 1, mv = {1, 1, 1})
/* compiled from: GenericResponse.kt */
public final class GenericResponse$lineIterator$1 implements Iterator<byte[]>, KMappedMarker {
    final /* synthetic */ int $chunkSize;
    final /* synthetic */ byte[] $delimiter;
    @NotNull
    private final Iterator<byte[]> byteArrays;
    @Nullable
    private byte[] leftOver;
    @NotNull
    private final ArrayList<byte[]> overflow = CollectionsKt__CollectionsKt.arrayListOf(new byte[null][]);
    final /* synthetic */ GenericResponse this$0;

    public void remove() {
        throw new UnsupportedOperationException("Mutating immutable collection");
    }

    GenericResponse$lineIterator$1(GenericResponse genericResponse, byte[] bArr, int i) {
        this.this$0 = genericResponse;
        this.$delimiter = bArr;
        this.$chunkSize = i;
        this.byteArrays = genericResponse.contentIterator(i);
    }

    @NotNull
    public final Iterator<byte[]> getByteArrays() {
        return this.byteArrays;
    }

    @Nullable
    public final byte[] getLeftOver() {
        return this.leftOver;
    }

    public final void setLeftOver(@Nullable byte[] bArr) {
        this.leftOver = bArr;
    }

    @NotNull
    public final ArrayList<byte[]> getOverflow() {
        return this.overflow;
    }

    @NotNull
    public byte[] next() {
        if ((this.overflow.isEmpty() ^ 1) != 0) {
            byte[] bArr = (byte[]) this.overflow.remove(0);
            Intrinsics.checkExpressionValueIsNotNull(bArr, "overflow.removeAt(0)");
            return bArr;
        }
        while (this.byteArrays.hasNext()) {
            Object obj;
            do {
                bArr = this.leftOver;
                byte[] bArr2 = (byte[]) this.byteArrays.next();
                if ((bArr2.length == 0 ? 1 : null) != null) {
                    break;
                }
                if (bArr != null) {
                    bArr2 = ArraysKt___ArraysJvmKt.plus(bArr, bArr2);
                }
                this.leftOver = bArr2;
                List splitLines = this.$delimiter == null ? ExtensionsKt.splitLines(bArr2) : ExtensionsKt.split(bArr2, this.$delimiter);
                if (splitLines.size() >= 2) {
                    this.leftOver = (byte[]) CollectionsKt___CollectionsKt.last(splitLines);
                    this.overflow.addAll(splitLines.subList(1, splitLines.size() - 1));
                    return (byte[]) splitLines.get(0);
                } else if (splitLines.size() < 2) {
                    obj = 1;
                    continue;
                } else {
                    obj = null;
                    continue;
                }
            } while (obj != null);
        }
        bArr = this.leftOver;
        if (bArr == null) {
            Intrinsics.throwNpe();
        }
        return bArr;
    }

    public boolean hasNext() {
        if ((this.overflow.isEmpty() ^ 1) == 0) {
            return this.byteArrays.hasNext();
        } else {
            return true;
        }
    }
}
