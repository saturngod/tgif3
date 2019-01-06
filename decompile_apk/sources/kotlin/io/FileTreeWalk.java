package kotlin.io;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Stack;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin._Assertions;
import kotlin.collections.AbstractIterator;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010(\n\u0002\b\u0006\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0003\u001a\u001b\u001cB\u0019\b\u0010\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B\u0001\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\u0014\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\t\u0018\u00010\b\u0012\u0014\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u000b\u0018\u00010\b\u00128\u0010\f\u001a4\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0010\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0012\u0012\u0004\u0012\u00020\u000b\u0018\u00010\r\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0014¢\u0006\u0002\u0010\u0015J\u000f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00020\u0017H\u0002J\u000e\u0010\u0013\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u0014J\u001a\u0010\u0007\u001a\u00020\u00002\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\t0\bJ \u0010\f\u001a\u00020\u00002\u0018\u0010\u0019\u001a\u0014\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u000b0\rJ\u001a\u0010\n\u001a\u00020\u00002\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u000b0\bR\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\t\u0018\u00010\bX\u0004¢\u0006\u0002\n\u0000R@\u0010\f\u001a4\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0010\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0012\u0012\u0004\u0012\u00020\u000b\u0018\u00010\rX\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u000b\u0018\u00010\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0002X\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lkotlin/io/FileTreeWalk;", "Lkotlin/sequences/Sequence;", "Ljava/io/File;", "start", "direction", "Lkotlin/io/FileWalkDirection;", "(Ljava/io/File;Lkotlin/io/FileWalkDirection;)V", "onEnter", "Lkotlin/Function1;", "", "onLeave", "", "onFail", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "f", "Ljava/io/IOException;", "e", "maxDepth", "", "(Ljava/io/File;Lkotlin/io/FileWalkDirection;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;I)V", "iterator", "", "depth", "function", "DirectoryState", "FileTreeWalkIterator", "WalkState", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
/* compiled from: FileTreeWalk.kt */
public final class FileTreeWalk implements Sequence<File> {
    private final FileWalkDirection direction;
    private final int maxDepth;
    private final Function1<File, Boolean> onEnter;
    private final Function2<File, IOException, Unit> onFail;
    private final Function1<File, Unit> onLeave;
    private final File start;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\"\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\n\u0010\u0007\u001a\u0004\u0018\u00010\u0003H&R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\b"}, d2 = {"Lkotlin/io/FileTreeWalk$WalkState;", "", "root", "Ljava/io/File;", "(Ljava/io/File;)V", "getRoot", "()Ljava/io/File;", "step", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
    /* compiled from: FileTreeWalk.kt */
    private static abstract class WalkState {
        @NotNull
        private final File root;

        @Nullable
        public abstract File step();

        public WalkState(@NotNull File file) {
            Intrinsics.checkParameterIsNotNull(file, "root");
            this.root = file;
        }

        @NotNull
        public final File getRoot() {
            return this.root;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\"\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lkotlin/io/FileTreeWalk$DirectoryState;", "Lkotlin/io/FileTreeWalk$WalkState;", "rootDir", "Ljava/io/File;", "(Ljava/io/File;)V", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
    /* compiled from: FileTreeWalk.kt */
    private static abstract class DirectoryState extends WalkState {
        public DirectoryState(@NotNull File file) {
            Intrinsics.checkParameterIsNotNull(file, "rootDir");
            super(file);
            if (_Assertions.ENABLED) {
                file = file.isDirectory();
                if (!_Assertions.ENABLED) {
                    return;
                }
                if (file == null) {
                    throw ((Throwable) new AssertionError("rootDir must be verified to be directory beforehand."));
                }
            }
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0003\r\u000e\u000fB\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0007\u001a\u00020\bH\u0014J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0002H\u0002J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0002H\u0010R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lkotlin/io/FileTreeWalk$FileTreeWalkIterator;", "Lkotlin/collections/AbstractIterator;", "Ljava/io/File;", "(Lkotlin/io/FileTreeWalk;)V", "state", "Ljava/util/Stack;", "Lkotlin/io/FileTreeWalk$WalkState;", "computeNext", "", "directoryState", "Lkotlin/io/FileTreeWalk$DirectoryState;", "root", "gotoNext", "BottomUpDirectoryState", "SingleFileState", "TopDownDirectoryState", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
    /* compiled from: FileTreeWalk.kt */
    private final class FileTreeWalkIterator extends AbstractIterator<File> {
        private final Stack<WalkState> state = new Stack();

        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\n\u0010\u0007\u001a\u0004\u0018\u00010\u0003H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lkotlin/io/FileTreeWalk$FileTreeWalkIterator$SingleFileState;", "Lkotlin/io/FileTreeWalk$WalkState;", "rootFile", "Ljava/io/File;", "(Lkotlin/io/FileTreeWalk$FileTreeWalkIterator;Ljava/io/File;)V", "visited", "", "step", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
        /* compiled from: FileTreeWalk.kt */
        private final class SingleFileState extends WalkState {
            final /* synthetic */ FileTreeWalkIterator this$0;
            private boolean visited;

            public SingleFileState(@NotNull FileTreeWalkIterator fileTreeWalkIterator, File file) {
                Intrinsics.checkParameterIsNotNull(file, "rootFile");
                this.this$0 = fileTreeWalkIterator;
                super(file);
                if (_Assertions.ENABLED != null) {
                    fileTreeWalkIterator = file.isFile();
                    if (_Assertions.ENABLED == null) {
                        return;
                    }
                    if (fileTreeWalkIterator == null) {
                        throw ((Throwable) new AssertionError("rootFile must be verified to be file beforehand."));
                    }
                }
            }

            @Nullable
            public File step() {
                if (this.visited) {
                    return null;
                }
                this.visited = true;
                return getRoot();
            }
        }

        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0004\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\n\u0010\r\u001a\u0004\u0018\u00010\u0003H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000R\u0018\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\nX\u000e¢\u0006\u0004\n\u0002\u0010\u000bR\u000e\u0010\f\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lkotlin/io/FileTreeWalk$FileTreeWalkIterator$BottomUpDirectoryState;", "Lkotlin/io/FileTreeWalk$DirectoryState;", "rootDir", "Ljava/io/File;", "(Lkotlin/io/FileTreeWalk$FileTreeWalkIterator;Ljava/io/File;)V", "failed", "", "fileIndex", "", "fileList", "", "[Ljava/io/File;", "rootVisited", "step", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
        /* compiled from: FileTreeWalk.kt */
        private final class BottomUpDirectoryState extends DirectoryState {
            private boolean failed;
            private int fileIndex;
            private File[] fileList;
            private boolean rootVisited;
            final /* synthetic */ FileTreeWalkIterator this$0;

            public BottomUpDirectoryState(@NotNull FileTreeWalkIterator fileTreeWalkIterator, File file) {
                Intrinsics.checkParameterIsNotNull(file, "rootDir");
                this.this$0 = fileTreeWalkIterator;
                super(file);
            }

            @Nullable
            public File step() {
                Function1 access$getOnEnter$p;
                if (!this.failed && this.fileList == null) {
                    access$getOnEnter$p = FileTreeWalk.this.onEnter;
                    if (access$getOnEnter$p != null && !((Boolean) access$getOnEnter$p.invoke(getRoot())).booleanValue()) {
                        return null;
                    }
                    this.fileList = getRoot().listFiles();
                    if (this.fileList == null) {
                        Function2 access$getOnFail$p = FileTreeWalk.this.onFail;
                        if (access$getOnFail$p != null) {
                            Unit unit = (Unit) access$getOnFail$p.invoke(getRoot(), new AccessDeniedException(getRoot(), null, "Cannot list files in a directory", 2, null));
                        }
                        this.failed = true;
                    }
                }
                if (this.fileList != null) {
                    int i = this.fileIndex;
                    File[] fileArr = this.fileList;
                    if (fileArr == null) {
                        Intrinsics.throwNpe();
                    }
                    if (i < fileArr.length) {
                        File[] fileArr2 = this.fileList;
                        if (fileArr2 == null) {
                            Intrinsics.throwNpe();
                        }
                        int i2 = this.fileIndex;
                        this.fileIndex = i2 + 1;
                        return fileArr2[i2];
                    }
                }
                if (this.rootVisited) {
                    access$getOnEnter$p = FileTreeWalk.this.onLeave;
                    if (access$getOnEnter$p != null) {
                        unit = (Unit) access$getOnEnter$p.invoke(getRoot());
                    }
                    return null;
                }
                this.rootVisited = true;
                return getRoot();
            }
        }

        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\n\u0010\f\u001a\u0004\u0018\u00010\u0003H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u0018\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\bX\u000e¢\u0006\u0004\n\u0002\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u000e¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lkotlin/io/FileTreeWalk$FileTreeWalkIterator$TopDownDirectoryState;", "Lkotlin/io/FileTreeWalk$DirectoryState;", "rootDir", "Ljava/io/File;", "(Lkotlin/io/FileTreeWalk$FileTreeWalkIterator;Ljava/io/File;)V", "fileIndex", "", "fileList", "", "[Ljava/io/File;", "rootVisited", "", "step", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
        /* compiled from: FileTreeWalk.kt */
        private final class TopDownDirectoryState extends DirectoryState {
            private int fileIndex;
            private File[] fileList;
            private boolean rootVisited;
            final /* synthetic */ FileTreeWalkIterator this$0;

            public TopDownDirectoryState(@NotNull FileTreeWalkIterator fileTreeWalkIterator, File file) {
                Intrinsics.checkParameterIsNotNull(file, "rootDir");
                this.this$0 = fileTreeWalkIterator;
                super(file);
            }

            /* JADX WARNING: inconsistent code. */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            @org.jetbrains.annotations.Nullable
            public java.io.File step() {
                /*
                r10 = this;
                r0 = r10.rootVisited;
                r1 = 0;
                if (r0 != 0) goto L_0x0028;
            L_0x0005:
                r0 = r10.this$0;
                r0 = kotlin.io.FileTreeWalk.this;
                r0 = r0.onEnter;
                if (r0 == 0) goto L_0x0020;
            L_0x000f:
                r2 = r10.getRoot();
                r0 = r0.invoke(r2);
                r0 = (java.lang.Boolean) r0;
                r0 = r0.booleanValue();
                if (r0 != 0) goto L_0x0020;
            L_0x001f:
                return r1;
            L_0x0020:
                r0 = 1;
                r10.rootVisited = r0;
                r0 = r10.getRoot();
                return r0;
            L_0x0028:
                r0 = r10.fileList;
                if (r0 == 0) goto L_0x004e;
            L_0x002c:
                r0 = r10.fileIndex;
                r2 = r10.fileList;
                if (r2 != 0) goto L_0x0035;
            L_0x0032:
                kotlin.jvm.internal.Intrinsics.throwNpe();
            L_0x0035:
                r2 = r2.length;
                if (r0 >= r2) goto L_0x0039;
            L_0x0038:
                goto L_0x004e;
            L_0x0039:
                r0 = r10.this$0;
                r0 = kotlin.io.FileTreeWalk.this;
                r0 = r0.onLeave;
                if (r0 == 0) goto L_0x004d;
            L_0x0043:
                r2 = r10.getRoot();
                r0 = r0.invoke(r2);
                r0 = (kotlin.Unit) r0;
            L_0x004d:
                return r1;
            L_0x004e:
                r0 = r10.fileList;
                if (r0 != 0) goto L_0x00a6;
            L_0x0052:
                r0 = r10.getRoot();
                r0 = r0.listFiles();
                r10.fileList = r0;
                r0 = r10.fileList;
                if (r0 != 0) goto L_0x0083;
            L_0x0060:
                r0 = r10.this$0;
                r0 = kotlin.io.FileTreeWalk.this;
                r0 = r0.onFail;
                if (r0 == 0) goto L_0x0083;
            L_0x006a:
                r2 = r10.getRoot();
                r9 = new kotlin.io.AccessDeniedException;
                r4 = r10.getRoot();
                r5 = 0;
                r6 = "Cannot list files in a directory";
                r7 = 2;
                r8 = 0;
                r3 = r9;
                r3.<init>(r4, r5, r6, r7, r8);
                r0 = r0.invoke(r2, r9);
                r0 = (kotlin.Unit) r0;
            L_0x0083:
                r0 = r10.fileList;
                if (r0 == 0) goto L_0x0091;
            L_0x0087:
                r0 = r10.fileList;
                if (r0 != 0) goto L_0x008e;
            L_0x008b:
                kotlin.jvm.internal.Intrinsics.throwNpe();
            L_0x008e:
                r0 = r0.length;
                if (r0 != 0) goto L_0x00a6;
            L_0x0091:
                r0 = r10.this$0;
                r0 = kotlin.io.FileTreeWalk.this;
                r0 = r0.onLeave;
                if (r0 == 0) goto L_0x00a5;
            L_0x009b:
                r2 = r10.getRoot();
                r0 = r0.invoke(r2);
                r0 = (kotlin.Unit) r0;
            L_0x00a5:
                return r1;
            L_0x00a6:
                r0 = r10.fileList;
                if (r0 != 0) goto L_0x00ad;
            L_0x00aa:
                kotlin.jvm.internal.Intrinsics.throwNpe();
            L_0x00ad:
                r1 = r10.fileIndex;
                r2 = r1 + 1;
                r10.fileIndex = r2;
                r0 = r0[r1];
                return r0;
                */
                throw new UnsupportedOperationException("Method not decompiled: kotlin.io.FileTreeWalk.FileTreeWalkIterator.TopDownDirectoryState.step():java.io.File");
            }
        }

        public FileTreeWalkIterator() {
            if (FileTreeWalk.this.start.isDirectory()) {
                this.state.push(directoryState(FileTreeWalk.this.start));
            } else if (FileTreeWalk.this.start.isFile()) {
                this.state.push(new SingleFileState(this, FileTreeWalk.this.start));
            } else {
                done();
            }
        }

        protected void computeNext() {
            File gotoNext = gotoNext();
            if (gotoNext != null) {
                setNext(gotoNext);
            } else {
                done();
            }
        }

        private final DirectoryState directoryState(File file) {
            switch (FileTreeWalk.this.direction) {
                case TOP_DOWN:
                    return new TopDownDirectoryState(this, file);
                case BOTTOM_UP:
                    return new BottomUpDirectoryState(this, file);
                default:
                    throw new NoWhenBranchMatchedException();
            }
        }

        private final File gotoNext() {
            while (!this.state.empty()) {
                Object peek = this.state.peek();
                if (peek == null) {
                    Intrinsics.throwNpe();
                }
                WalkState walkState = (WalkState) peek;
                Object step = walkState.step();
                if (step == null) {
                    this.state.pop();
                } else {
                    if (!Intrinsics.areEqual(step, walkState.getRoot()) && step.isDirectory()) {
                        if (this.state.size() < FileTreeWalk.this.maxDepth) {
                            this.state.push(directoryState(step));
                        }
                    }
                    return step;
                }
            }
            return null;
        }
    }

    private FileTreeWalk(File file, FileWalkDirection fileWalkDirection, Function1<? super File, Boolean> function1, Function1<? super File, Unit> function12, Function2<? super File, ? super IOException, Unit> function2, int i) {
        this.start = file;
        this.direction = fileWalkDirection;
        this.onEnter = function1;
        this.onLeave = function12;
        this.onFail = function2;
        this.maxDepth = i;
    }

    /* synthetic */ FileTreeWalk(File file, FileWalkDirection fileWalkDirection, Function1 function1, Function1 function12, Function2 function2, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i2 & 2) != null) {
            fileWalkDirection = FileWalkDirection.TOP_DOWN;
        }
        this(file, fileWalkDirection, function1, function12, function2, (i2 & 32) != null ? Integer.MAX_VALUE : i);
    }

    public FileTreeWalk(@NotNull File file, @NotNull FileWalkDirection fileWalkDirection) {
        Intrinsics.checkParameterIsNotNull(file, "start");
        Intrinsics.checkParameterIsNotNull(fileWalkDirection, "direction");
        this(file, fileWalkDirection, null, null, null, 0, 32, null);
    }

    public /* synthetic */ FileTreeWalk(File file, FileWalkDirection fileWalkDirection, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 2) != 0) {
            fileWalkDirection = FileWalkDirection.TOP_DOWN;
        }
        this(file, fileWalkDirection);
    }

    @NotNull
    public Iterator<File> iterator() {
        return new FileTreeWalkIterator();
    }

    @NotNull
    public final FileTreeWalk onEnter(@NotNull Function1<? super File, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(function1, "function");
        return new FileTreeWalk(this.start, this.direction, function1, this.onLeave, this.onFail, this.maxDepth);
    }

    @NotNull
    public final FileTreeWalk onLeave(@NotNull Function1<? super File, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(function1, "function");
        return new FileTreeWalk(this.start, this.direction, this.onEnter, function1, this.onFail, this.maxDepth);
    }

    @NotNull
    public final FileTreeWalk onFail(@NotNull Function2<? super File, ? super IOException, Unit> function2) {
        Intrinsics.checkParameterIsNotNull(function2, "function");
        return new FileTreeWalk(this.start, this.direction, this.onEnter, this.onLeave, function2, this.maxDepth);
    }

    @NotNull
    public final FileTreeWalk maxDepth(int i) {
        if (i > 0) {
            return new FileTreeWalk(this.start, this.direction, this.onEnter, this.onLeave, this.onFail, i);
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("depth must be positive, but was ");
        stringBuilder.append(i);
        stringBuilder.append(46);
        throw new IllegalArgumentException(stringBuilder.toString());
    }
}
