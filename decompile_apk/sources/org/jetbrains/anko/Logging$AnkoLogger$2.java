package org.jetbrains.anko;

import kotlin.Metadata;
import kotlin._Assertions;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"org/jetbrains/anko/Logging$AnkoLogger$2", "Lorg/jetbrains/anko/AnkoLogger;", "loggerTag", "", "getLoggerTag", "()Ljava/lang/String;", "commons-base_release"}, k = 1, mv = {1, 1, 13})
/* compiled from: Logging.kt */
public final class Logging$AnkoLogger$2 implements AnkoLogger {
    final /* synthetic */ String $tag;
    @NotNull
    private final String loggerTag;

    Logging$AnkoLogger$2(String str) {
        this.$tag = str;
        Object obj = str.length() <= 23 ? 1 : null;
        if (_Assertions.ENABLED) {
            if (obj == null) {
                str = new StringBuilder();
                str.append("The maximum tag length is 23, got ");
                str.append(this.$tag);
                throw new AssertionError(str.toString());
            }
        }
        this.loggerTag = str;
    }

    @NotNull
    public String getLoggerTag() {
        return this.loggerTag;
    }
}
