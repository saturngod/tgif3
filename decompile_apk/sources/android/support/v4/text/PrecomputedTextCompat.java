package android.support.v4.text;

import android.os.Build.VERSION;
import android.support.annotation.GuardedBy;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.annotation.UiThread;
import android.support.v4.os.TraceCompat;
import android.support.v4.util.ObjectsCompat;
import android.support.v4.util.Preconditions;
import android.text.Layout.Alignment;
import android.text.PrecomputedText;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.StaticLayout;
import android.text.StaticLayout.Builder;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.MetricAffectingSpan;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class PrecomputedTextCompat implements Spannable {
    private static final char LINE_FEED = '\n';
    @GuardedBy("sLock")
    @NonNull
    private static Executor sExecutor = null;
    private static final Object sLock = new Object();
    @NonNull
    private final int[] mParagraphEnds;
    @NonNull
    private final Params mParams;
    @NonNull
    private final Spannable mText;
    @Nullable
    private final PrecomputedText mWrapped;

    public static final class Params {
        private final int mBreakStrategy;
        private final int mHyphenationFrequency;
        @NonNull
        private final TextPaint mPaint;
        @Nullable
        private final TextDirectionHeuristic mTextDir;
        final android.text.PrecomputedText.Params mWrapped;

        public static class Builder {
            private int mBreakStrategy;
            private int mHyphenationFrequency;
            @NonNull
            private final TextPaint mPaint;
            private TextDirectionHeuristic mTextDir;

            public Builder(@NonNull TextPaint textPaint) {
                this.mPaint = textPaint;
                if (VERSION.SDK_INT >= 23) {
                    this.mBreakStrategy = 1;
                    this.mHyphenationFrequency = 1;
                } else {
                    this.mHyphenationFrequency = 0;
                    this.mBreakStrategy = 0;
                }
                if (VERSION.SDK_INT >= 18) {
                    this.mTextDir = TextDirectionHeuristics.FIRSTSTRONG_LTR;
                } else {
                    this.mTextDir = null;
                }
            }

            @RequiresApi(23)
            public Builder setBreakStrategy(int i) {
                this.mBreakStrategy = i;
                return this;
            }

            @RequiresApi(23)
            public Builder setHyphenationFrequency(int i) {
                this.mHyphenationFrequency = i;
                return this;
            }

            @RequiresApi(18)
            public Builder setTextDirection(@NonNull TextDirectionHeuristic textDirectionHeuristic) {
                this.mTextDir = textDirectionHeuristic;
                return this;
            }

            @NonNull
            public Params build() {
                return new Params(this.mPaint, this.mTextDir, this.mBreakStrategy, this.mHyphenationFrequency);
            }
        }

        Params(@NonNull TextPaint textPaint, @NonNull TextDirectionHeuristic textDirectionHeuristic, int i, int i2) {
            if (VERSION.SDK_INT >= 28) {
                this.mWrapped = new android.text.PrecomputedText.Params.Builder(textPaint).setBreakStrategy(i).setHyphenationFrequency(i2).setTextDirection(textDirectionHeuristic).build();
            } else {
                this.mWrapped = null;
            }
            this.mPaint = textPaint;
            this.mTextDir = textDirectionHeuristic;
            this.mBreakStrategy = i;
            this.mHyphenationFrequency = i2;
        }

        @RequiresApi(28)
        public Params(@NonNull android.text.PrecomputedText.Params params) {
            this.mPaint = params.getTextPaint();
            this.mTextDir = params.getTextDirection();
            this.mBreakStrategy = params.getBreakStrategy();
            this.mHyphenationFrequency = params.getHyphenationFrequency();
            this.mWrapped = params;
        }

        @NonNull
        public TextPaint getTextPaint() {
            return this.mPaint;
        }

        @Nullable
        @RequiresApi(18)
        public TextDirectionHeuristic getTextDirection() {
            return this.mTextDir;
        }

        @RequiresApi(23)
        public int getBreakStrategy() {
            return this.mBreakStrategy;
        }

        @RequiresApi(23)
        public int getHyphenationFrequency() {
            return this.mHyphenationFrequency;
        }

        public boolean equals(@Nullable Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj != null) {
                if (obj instanceof Params) {
                    Params params = (Params) obj;
                    if (this.mWrapped != null) {
                        return this.mWrapped.equals(params.mWrapped);
                    }
                    if (VERSION.SDK_INT >= 23 && (this.mBreakStrategy != params.getBreakStrategy() || this.mHyphenationFrequency != params.getHyphenationFrequency())) {
                        return false;
                    }
                    if ((VERSION.SDK_INT >= 18 && this.mTextDir != params.getTextDirection()) || this.mPaint.getTextSize() != params.getTextPaint().getTextSize() || this.mPaint.getTextScaleX() != params.getTextPaint().getTextScaleX() || this.mPaint.getTextSkewX() != params.getTextPaint().getTextSkewX()) {
                        return false;
                    }
                    if ((VERSION.SDK_INT >= 21 && (this.mPaint.getLetterSpacing() != params.getTextPaint().getLetterSpacing() || !TextUtils.equals(this.mPaint.getFontFeatureSettings(), params.getTextPaint().getFontFeatureSettings()))) || this.mPaint.getFlags() != params.getTextPaint().getFlags()) {
                        return false;
                    }
                    if (VERSION.SDK_INT >= 24) {
                        if (!this.mPaint.getTextLocales().equals(params.getTextPaint().getTextLocales())) {
                            return false;
                        }
                    } else if (VERSION.SDK_INT >= 17 && !this.mPaint.getTextLocale().equals(params.getTextPaint().getTextLocale())) {
                        return false;
                    }
                    if (this.mPaint.getTypeface() == null) {
                        if (params.getTextPaint().getTypeface() != null) {
                            return false;
                        }
                    } else if (this.mPaint.getTypeface().equals(params.getTextPaint().getTypeface()) == null) {
                        return false;
                    }
                    return true;
                }
            }
            return false;
        }

        public int hashCode() {
            if (VERSION.SDK_INT >= 24) {
                return ObjectsCompat.hash(Float.valueOf(this.mPaint.getTextSize()), Float.valueOf(this.mPaint.getTextScaleX()), Float.valueOf(this.mPaint.getTextSkewX()), Float.valueOf(this.mPaint.getLetterSpacing()), Integer.valueOf(this.mPaint.getFlags()), this.mPaint.getTextLocales(), this.mPaint.getTypeface(), Boolean.valueOf(this.mPaint.isElegantTextHeight()), this.mTextDir, Integer.valueOf(this.mBreakStrategy), Integer.valueOf(this.mHyphenationFrequency));
            } else if (VERSION.SDK_INT >= 21) {
                return ObjectsCompat.hash(Float.valueOf(this.mPaint.getTextSize()), Float.valueOf(this.mPaint.getTextScaleX()), Float.valueOf(this.mPaint.getTextSkewX()), Float.valueOf(this.mPaint.getLetterSpacing()), Integer.valueOf(this.mPaint.getFlags()), this.mPaint.getTextLocale(), this.mPaint.getTypeface(), Boolean.valueOf(this.mPaint.isElegantTextHeight()), this.mTextDir, Integer.valueOf(this.mBreakStrategy), Integer.valueOf(this.mHyphenationFrequency));
            } else if (VERSION.SDK_INT >= 18) {
                return ObjectsCompat.hash(Float.valueOf(this.mPaint.getTextSize()), Float.valueOf(this.mPaint.getTextScaleX()), Float.valueOf(this.mPaint.getTextSkewX()), Integer.valueOf(this.mPaint.getFlags()), this.mPaint.getTextLocale(), this.mPaint.getTypeface(), this.mTextDir, Integer.valueOf(this.mBreakStrategy), Integer.valueOf(this.mHyphenationFrequency));
            } else if (VERSION.SDK_INT >= 17) {
                return ObjectsCompat.hash(Float.valueOf(this.mPaint.getTextSize()), Float.valueOf(this.mPaint.getTextScaleX()), Float.valueOf(this.mPaint.getTextSkewX()), Integer.valueOf(this.mPaint.getFlags()), this.mPaint.getTextLocale(), this.mPaint.getTypeface(), this.mTextDir, Integer.valueOf(this.mBreakStrategy), Integer.valueOf(this.mHyphenationFrequency));
            } else {
                return ObjectsCompat.hash(Float.valueOf(this.mPaint.getTextSize()), Float.valueOf(this.mPaint.getTextScaleX()), Float.valueOf(this.mPaint.getTextSkewX()), Integer.valueOf(this.mPaint.getFlags()), this.mPaint.getTypeface(), this.mTextDir, Integer.valueOf(this.mBreakStrategy), Integer.valueOf(this.mHyphenationFrequency));
            }
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder("{");
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("textSize=");
            stringBuilder2.append(this.mPaint.getTextSize());
            stringBuilder.append(stringBuilder2.toString());
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append(", textScaleX=");
            stringBuilder2.append(this.mPaint.getTextScaleX());
            stringBuilder.append(stringBuilder2.toString());
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append(", textSkewX=");
            stringBuilder2.append(this.mPaint.getTextSkewX());
            stringBuilder.append(stringBuilder2.toString());
            if (VERSION.SDK_INT >= 21) {
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append(", letterSpacing=");
                stringBuilder2.append(this.mPaint.getLetterSpacing());
                stringBuilder.append(stringBuilder2.toString());
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append(", elegantTextHeight=");
                stringBuilder2.append(this.mPaint.isElegantTextHeight());
                stringBuilder.append(stringBuilder2.toString());
            }
            if (VERSION.SDK_INT >= 24) {
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append(", textLocale=");
                stringBuilder2.append(this.mPaint.getTextLocales());
                stringBuilder.append(stringBuilder2.toString());
            } else if (VERSION.SDK_INT >= 17) {
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append(", textLocale=");
                stringBuilder2.append(this.mPaint.getTextLocale());
                stringBuilder.append(stringBuilder2.toString());
            }
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append(", typeface=");
            stringBuilder2.append(this.mPaint.getTypeface());
            stringBuilder.append(stringBuilder2.toString());
            if (VERSION.SDK_INT >= 26) {
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append(", variationSettings=");
                stringBuilder2.append(this.mPaint.getFontVariationSettings());
                stringBuilder.append(stringBuilder2.toString());
            }
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append(", textDir=");
            stringBuilder2.append(this.mTextDir);
            stringBuilder.append(stringBuilder2.toString());
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append(", breakStrategy=");
            stringBuilder2.append(this.mBreakStrategy);
            stringBuilder.append(stringBuilder2.toString());
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append(", hyphenationFrequency=");
            stringBuilder2.append(this.mHyphenationFrequency);
            stringBuilder.append(stringBuilder2.toString());
            stringBuilder.append("}");
            return stringBuilder.toString();
        }
    }

    private static class PrecomputedTextFutureTask extends FutureTask<PrecomputedTextCompat> {

        private static class PrecomputedTextCallback implements Callable<PrecomputedTextCompat> {
            private Params mParams;
            private CharSequence mText;

            PrecomputedTextCallback(@NonNull Params params, @NonNull CharSequence charSequence) {
                this.mParams = params;
                this.mText = charSequence;
            }

            public PrecomputedTextCompat call() throws Exception {
                return PrecomputedTextCompat.create(this.mText, this.mParams);
            }
        }

        PrecomputedTextFutureTask(@NonNull Params params, @NonNull CharSequence charSequence) {
            super(new PrecomputedTextCallback(params, charSequence));
        }
    }

    public static PrecomputedTextCompat create(@NonNull CharSequence charSequence, @NonNull Params params) {
        Preconditions.checkNotNull(charSequence);
        Preconditions.checkNotNull(params);
        try {
            TraceCompat.beginSection("PrecomputedText");
            PrecomputedTextCompat precomputedTextCompat = VERSION.SDK_INT;
            if (precomputedTextCompat >= 28) {
                precomputedTextCompat = params.mWrapped;
                if (precomputedTextCompat != 0) {
                    precomputedTextCompat = new PrecomputedTextCompat(PrecomputedText.create(charSequence, params.mWrapped), params);
                    return precomputedTextCompat;
                }
            }
            ArrayList arrayList = new ArrayList();
            int length = charSequence.length();
            int i = 0;
            while (i < length) {
                i = TextUtils.indexOf(charSequence, LINE_FEED, i, length);
                i = i < 0 ? length : i + 1;
                arrayList.add(Integer.valueOf(i));
            }
            int[] iArr = new int[arrayList.size()];
            for (i = 0; i < arrayList.size(); i++) {
                iArr[i] = ((Integer) arrayList.get(i)).intValue();
            }
            if (VERSION.SDK_INT >= 23) {
                Builder.obtain(charSequence, 0, charSequence.length(), params.getTextPaint(), Integer.MAX_VALUE).setBreakStrategy(params.getBreakStrategy()).setHyphenationFrequency(params.getHyphenationFrequency()).setTextDirection(params.getTextDirection()).build();
            } else if (VERSION.SDK_INT >= 21) {
                StaticLayout staticLayout = new StaticLayout(charSequence, params.getTextPaint(), Integer.MAX_VALUE, Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
            }
            precomputedTextCompat = new PrecomputedTextCompat(charSequence, params, iArr);
            TraceCompat.endSection();
            return precomputedTextCompat;
        } finally {
            TraceCompat.endSection();
        }
    }

    private PrecomputedTextCompat(@NonNull CharSequence charSequence, @NonNull Params params, @NonNull int[] iArr) {
        this.mText = new SpannableString(charSequence);
        this.mParams = params;
        this.mParagraphEnds = iArr;
        this.mWrapped = null;
    }

    @RequiresApi(28)
    private PrecomputedTextCompat(@NonNull PrecomputedText precomputedText, @NonNull Params params) {
        this.mText = precomputedText;
        this.mParams = params;
        this.mParagraphEnds = null;
        this.mWrapped = precomputedText;
    }

    @Nullable
    @RequiresApi(28)
    @RestrictTo({Scope.LIBRARY_GROUP})
    public PrecomputedText getPrecomputedText() {
        return this.mText instanceof PrecomputedText ? (PrecomputedText) this.mText : null;
    }

    @NonNull
    public Params getParams() {
        return this.mParams;
    }

    @IntRange(from = 0)
    public int getParagraphCount() {
        if (VERSION.SDK_INT >= 28) {
            return this.mWrapped.getParagraphCount();
        }
        return this.mParagraphEnds.length;
    }

    @IntRange(from = 0)
    public int getParagraphStart(@IntRange(from = 0) int i) {
        int i2 = 0;
        Preconditions.checkArgumentInRange(i, 0, getParagraphCount(), "paraIndex");
        if (VERSION.SDK_INT >= 28) {
            return this.mWrapped.getParagraphStart(i);
        }
        if (i != 0) {
            i2 = this.mParagraphEnds[i - 1];
        }
        return i2;
    }

    @IntRange(from = 0)
    public int getParagraphEnd(@IntRange(from = 0) int i) {
        Preconditions.checkArgumentInRange(i, 0, getParagraphCount(), "paraIndex");
        if (VERSION.SDK_INT >= 28) {
            return this.mWrapped.getParagraphEnd(i);
        }
        return this.mParagraphEnds[i];
    }

    private int findParaIndex(@IntRange(from = 0) int i) {
        for (int i2 = 0; i2 < this.mParagraphEnds.length; i2++) {
            if (i < this.mParagraphEnds[i2]) {
                return i2;
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("pos must be less than ");
        stringBuilder.append(this.mParagraphEnds[this.mParagraphEnds.length - 1]);
        stringBuilder.append(", gave ");
        stringBuilder.append(i);
        throw new IndexOutOfBoundsException(stringBuilder.toString());
    }

    @UiThread
    public static Future<PrecomputedTextCompat> getTextFuture(@NonNull CharSequence charSequence, @NonNull Params params, @Nullable Executor executor) {
        Object precomputedTextFutureTask = new PrecomputedTextFutureTask(params, charSequence);
        if (executor == null) {
            synchronized (sLock) {
                if (sExecutor == null) {
                    sExecutor = Executors.newFixedThreadPool(1);
                }
                executor = sExecutor;
            }
        }
        executor.execute(precomputedTextFutureTask);
        return precomputedTextFutureTask;
    }

    public void setSpan(Object obj, int i, int i2, int i3) {
        if (obj instanceof MetricAffectingSpan) {
            throw new IllegalArgumentException("MetricAffectingSpan can not be set to PrecomputedText.");
        } else if (VERSION.SDK_INT >= 28) {
            this.mWrapped.setSpan(obj, i, i2, i3);
        } else {
            this.mText.setSpan(obj, i, i2, i3);
        }
    }

    public void removeSpan(Object obj) {
        if (obj instanceof MetricAffectingSpan) {
            throw new IllegalArgumentException("MetricAffectingSpan can not be removed from PrecomputedText.");
        } else if (VERSION.SDK_INT >= 28) {
            this.mWrapped.removeSpan(obj);
        } else {
            this.mText.removeSpan(obj);
        }
    }

    public <T> T[] getSpans(int i, int i2, Class<T> cls) {
        if (VERSION.SDK_INT >= 28) {
            return this.mWrapped.getSpans(i, i2, cls);
        }
        return this.mText.getSpans(i, i2, cls);
    }

    public int getSpanStart(Object obj) {
        return this.mText.getSpanStart(obj);
    }

    public int getSpanEnd(Object obj) {
        return this.mText.getSpanEnd(obj);
    }

    public int getSpanFlags(Object obj) {
        return this.mText.getSpanFlags(obj);
    }

    public int nextSpanTransition(int i, int i2, Class cls) {
        return this.mText.nextSpanTransition(i, i2, cls);
    }

    public int length() {
        return this.mText.length();
    }

    public char charAt(int i) {
        return this.mText.charAt(i);
    }

    public CharSequence subSequence(int i, int i2) {
        return this.mText.subSequence(i, i2);
    }

    public String toString() {
        return this.mText.toString();
    }
}
