package android.support.v4.content;

import android.content.SharedPreferences.Editor;
import android.support.annotation.NonNull;

@Deprecated
public final class SharedPreferencesCompat {

    @Deprecated
    public static final class EditorCompat {
        private static EditorCompat sInstance;
        private final Helper mHelper = new Helper();

        private static class Helper {
            Helper() {
            }

            public void apply(@android.support.annotation.NonNull android.content.SharedPreferences.Editor r1) {
                /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1603198149.run(Unknown Source)
*/
                /*
                r0 = this;
                r1.apply();	 Catch:{ AbstractMethodError -> 0x0004 }
                goto L_0x0007;
            L_0x0004:
                r1.commit();
            L_0x0007:
                return;
                */
                throw new UnsupportedOperationException("Method not decompiled: android.support.v4.content.SharedPreferencesCompat.EditorCompat.Helper.apply(android.content.SharedPreferences$Editor):void");
            }
        }

        private EditorCompat() {
        }

        @Deprecated
        public static EditorCompat getInstance() {
            if (sInstance == null) {
                sInstance = new EditorCompat();
            }
            return sInstance;
        }

        @Deprecated
        public void apply(@NonNull Editor editor) {
            this.mHelper.apply(editor);
        }
    }

    private SharedPreferencesCompat() {
    }
}
