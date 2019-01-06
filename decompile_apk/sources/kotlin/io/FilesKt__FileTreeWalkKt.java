package kotlin.io;

import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u0004\u001a\n\u0010\u0005\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0006\u001a\u00020\u0001*\u00020\u0002¨\u0006\u0007"}, d2 = {"walk", "Lkotlin/io/FileTreeWalk;", "Ljava/io/File;", "direction", "Lkotlin/io/FileWalkDirection;", "walkBottomUp", "walkTopDown", "kotlin-stdlib"}, k = 5, mv = {1, 1, 13}, xi = 1, xs = "kotlin/io/FilesKt")
/* compiled from: FileTreeWalk.kt */
class FilesKt__FileTreeWalkKt extends FilesKt__FileReadWriteKt {
    @NotNull
    public static /* synthetic */ FileTreeWalk walk$default(File file, FileWalkDirection fileWalkDirection, int i, Object obj) {
        if ((i & 1) != 0) {
            fileWalkDirection = FileWalkDirection.TOP_DOWN;
        }
        return walk(file, fileWalkDirection);
    }

    @NotNull
    public static final FileTreeWalk walk(@NotNull File file, @NotNull FileWalkDirection fileWalkDirection) {
        Intrinsics.checkParameterIsNotNull(file, "receiver$0");
        Intrinsics.checkParameterIsNotNull(fileWalkDirection, "direction");
        return new FileTreeWalk(file, fileWalkDirection);
    }

    @NotNull
    public static final FileTreeWalk walkTopDown(@NotNull File file) {
        Intrinsics.checkParameterIsNotNull(file, "receiver$0");
        return walk(file, FileWalkDirection.TOP_DOWN);
    }

    @NotNull
    public static final FileTreeWalk walkBottomUp(@NotNull File file) {
        Intrinsics.checkParameterIsNotNull(file, "receiver$0");
        return walk(file, FileWalkDirection.BOTTOM_UP);
    }
}
