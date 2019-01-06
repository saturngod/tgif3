package android.support.constraint;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build.VERSION;
import android.support.constraint.solver.Metrics;
import android.support.constraint.solver.widgets.Analyzer;
import android.support.constraint.solver.widgets.ConstraintAnchor.Type;
import android.support.constraint.solver.widgets.ConstraintWidget;
import android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour;
import android.support.constraint.solver.widgets.ConstraintWidgetContainer;
import android.support.constraint.solver.widgets.Guideline;
import android.support.constraint.solver.widgets.ResolutionAnchor;
import android.support.v4.internal.view.SupportMenu;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import java.util.ArrayList;
import java.util.HashMap;

public class ConstraintLayout extends ViewGroup {
    static final boolean ALLOWS_EMBEDDED = false;
    private static final boolean CACHE_MEASURED_DIMENSION = false;
    private static final boolean DEBUG = false;
    public static final int DESIGN_INFO_ID = 0;
    private static final String TAG = "ConstraintLayout";
    private static final boolean USE_CONSTRAINTS_HELPER = true;
    public static final String VERSION = "ConstraintLayout-1.1.3";
    SparseArray<View> mChildrenByIds = new SparseArray();
    private ArrayList<ConstraintHelper> mConstraintHelpers = new ArrayList(4);
    private ConstraintSet mConstraintSet = null;
    private int mConstraintSetId = -1;
    private HashMap<String, Integer> mDesignIds = new HashMap();
    private boolean mDirtyHierarchy = USE_CONSTRAINTS_HELPER;
    private int mLastMeasureHeight = -1;
    int mLastMeasureHeightMode = 0;
    int mLastMeasureHeightSize = -1;
    private int mLastMeasureWidth = -1;
    int mLastMeasureWidthMode = 0;
    int mLastMeasureWidthSize = -1;
    ConstraintWidgetContainer mLayoutWidget = new ConstraintWidgetContainer();
    private int mMaxHeight = Integer.MAX_VALUE;
    private int mMaxWidth = Integer.MAX_VALUE;
    private Metrics mMetrics;
    private int mMinHeight = 0;
    private int mMinWidth = 0;
    private int mOptimizationLevel = 7;
    private final ArrayList<ConstraintWidget> mVariableDimensionsWidgets = new ArrayList(100);

    public static class LayoutParams extends MarginLayoutParams {
        public static final int BASELINE = 5;
        public static final int BOTTOM = 4;
        public static final int CHAIN_PACKED = 2;
        public static final int CHAIN_SPREAD = 0;
        public static final int CHAIN_SPREAD_INSIDE = 1;
        public static final int END = 7;
        public static final int HORIZONTAL = 0;
        public static final int LEFT = 1;
        public static final int MATCH_CONSTRAINT = 0;
        public static final int MATCH_CONSTRAINT_PERCENT = 2;
        public static final int MATCH_CONSTRAINT_SPREAD = 0;
        public static final int MATCH_CONSTRAINT_WRAP = 1;
        public static final int PARENT_ID = 0;
        public static final int RIGHT = 2;
        public static final int START = 6;
        public static final int TOP = 3;
        public static final int UNSET = -1;
        public static final int VERTICAL = 1;
        public int baselineToBaseline = -1;
        public int bottomToBottom = -1;
        public int bottomToTop = -1;
        public float circleAngle = 0.0f;
        public int circleConstraint = -1;
        public int circleRadius = 0;
        public boolean constrainedHeight = false;
        public boolean constrainedWidth = false;
        public String dimensionRatio = null;
        int dimensionRatioSide = 1;
        float dimensionRatioValue = 0.0f;
        public int editorAbsoluteX = -1;
        public int editorAbsoluteY = -1;
        public int endToEnd = -1;
        public int endToStart = -1;
        public int goneBottomMargin = -1;
        public int goneEndMargin = -1;
        public int goneLeftMargin = -1;
        public int goneRightMargin = -1;
        public int goneStartMargin = -1;
        public int goneTopMargin = -1;
        public int guideBegin = -1;
        public int guideEnd = -1;
        public float guidePercent = -1.0f;
        public boolean helped = false;
        public float horizontalBias = 0.5f;
        public int horizontalChainStyle = 0;
        boolean horizontalDimensionFixed = ConstraintLayout.USE_CONSTRAINTS_HELPER;
        public float horizontalWeight = -1.0f;
        boolean isGuideline = false;
        boolean isHelper = false;
        boolean isInPlaceholder = false;
        public int leftToLeft = -1;
        public int leftToRight = -1;
        public int matchConstraintDefaultHeight = 0;
        public int matchConstraintDefaultWidth = 0;
        public int matchConstraintMaxHeight = 0;
        public int matchConstraintMaxWidth = 0;
        public int matchConstraintMinHeight = 0;
        public int matchConstraintMinWidth = 0;
        public float matchConstraintPercentHeight = 1.0f;
        public float matchConstraintPercentWidth = 1.0f;
        boolean needsBaseline = false;
        public int orientation = -1;
        int resolveGoneLeftMargin = -1;
        int resolveGoneRightMargin = -1;
        int resolvedGuideBegin;
        int resolvedGuideEnd;
        float resolvedGuidePercent;
        float resolvedHorizontalBias = 0.5f;
        int resolvedLeftToLeft = -1;
        int resolvedLeftToRight = -1;
        int resolvedRightToLeft = -1;
        int resolvedRightToRight = -1;
        public int rightToLeft = -1;
        public int rightToRight = -1;
        public int startToEnd = -1;
        public int startToStart = -1;
        public int topToBottom = -1;
        public int topToTop = -1;
        public float verticalBias = 0.5f;
        public int verticalChainStyle = 0;
        boolean verticalDimensionFixed = ConstraintLayout.USE_CONSTRAINTS_HELPER;
        public float verticalWeight = -1.0f;
        ConstraintWidget widget = new ConstraintWidget();

        private static class Table {
            public static final int ANDROID_ORIENTATION = 1;
            public static final int LAYOUT_CONSTRAINED_HEIGHT = 28;
            public static final int LAYOUT_CONSTRAINED_WIDTH = 27;
            public static final int LAYOUT_CONSTRAINT_BASELINE_CREATOR = 43;
            public static final int LAYOUT_CONSTRAINT_BASELINE_TO_BASELINE_OF = 16;
            public static final int LAYOUT_CONSTRAINT_BOTTOM_CREATOR = 42;
            public static final int LAYOUT_CONSTRAINT_BOTTOM_TO_BOTTOM_OF = 15;
            public static final int LAYOUT_CONSTRAINT_BOTTOM_TO_TOP_OF = 14;
            public static final int LAYOUT_CONSTRAINT_CIRCLE = 2;
            public static final int LAYOUT_CONSTRAINT_CIRCLE_ANGLE = 4;
            public static final int LAYOUT_CONSTRAINT_CIRCLE_RADIUS = 3;
            public static final int LAYOUT_CONSTRAINT_DIMENSION_RATIO = 44;
            public static final int LAYOUT_CONSTRAINT_END_TO_END_OF = 20;
            public static final int LAYOUT_CONSTRAINT_END_TO_START_OF = 19;
            public static final int LAYOUT_CONSTRAINT_GUIDE_BEGIN = 5;
            public static final int LAYOUT_CONSTRAINT_GUIDE_END = 6;
            public static final int LAYOUT_CONSTRAINT_GUIDE_PERCENT = 7;
            public static final int LAYOUT_CONSTRAINT_HEIGHT_DEFAULT = 32;
            public static final int LAYOUT_CONSTRAINT_HEIGHT_MAX = 37;
            public static final int LAYOUT_CONSTRAINT_HEIGHT_MIN = 36;
            public static final int LAYOUT_CONSTRAINT_HEIGHT_PERCENT = 38;
            public static final int LAYOUT_CONSTRAINT_HORIZONTAL_BIAS = 29;
            public static final int LAYOUT_CONSTRAINT_HORIZONTAL_CHAINSTYLE = 47;
            public static final int LAYOUT_CONSTRAINT_HORIZONTAL_WEIGHT = 45;
            public static final int LAYOUT_CONSTRAINT_LEFT_CREATOR = 39;
            public static final int LAYOUT_CONSTRAINT_LEFT_TO_LEFT_OF = 8;
            public static final int LAYOUT_CONSTRAINT_LEFT_TO_RIGHT_OF = 9;
            public static final int LAYOUT_CONSTRAINT_RIGHT_CREATOR = 41;
            public static final int LAYOUT_CONSTRAINT_RIGHT_TO_LEFT_OF = 10;
            public static final int LAYOUT_CONSTRAINT_RIGHT_TO_RIGHT_OF = 11;
            public static final int LAYOUT_CONSTRAINT_START_TO_END_OF = 17;
            public static final int LAYOUT_CONSTRAINT_START_TO_START_OF = 18;
            public static final int LAYOUT_CONSTRAINT_TOP_CREATOR = 40;
            public static final int LAYOUT_CONSTRAINT_TOP_TO_BOTTOM_OF = 13;
            public static final int LAYOUT_CONSTRAINT_TOP_TO_TOP_OF = 12;
            public static final int LAYOUT_CONSTRAINT_VERTICAL_BIAS = 30;
            public static final int LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE = 48;
            public static final int LAYOUT_CONSTRAINT_VERTICAL_WEIGHT = 46;
            public static final int LAYOUT_CONSTRAINT_WIDTH_DEFAULT = 31;
            public static final int LAYOUT_CONSTRAINT_WIDTH_MAX = 34;
            public static final int LAYOUT_CONSTRAINT_WIDTH_MIN = 33;
            public static final int LAYOUT_CONSTRAINT_WIDTH_PERCENT = 35;
            public static final int LAYOUT_EDITOR_ABSOLUTEX = 49;
            public static final int LAYOUT_EDITOR_ABSOLUTEY = 50;
            public static final int LAYOUT_GONE_MARGIN_BOTTOM = 24;
            public static final int LAYOUT_GONE_MARGIN_END = 26;
            public static final int LAYOUT_GONE_MARGIN_LEFT = 21;
            public static final int LAYOUT_GONE_MARGIN_RIGHT = 23;
            public static final int LAYOUT_GONE_MARGIN_START = 25;
            public static final int LAYOUT_GONE_MARGIN_TOP = 22;
            public static final int UNUSED = 0;
            public static final SparseIntArray map = new SparseIntArray();

            private Table() {
            }

            static {
                map.append(C0016R.styleable.ConstraintLayout_Layout_layout_constraintLeft_toLeftOf, 8);
                map.append(C0016R.styleable.ConstraintLayout_Layout_layout_constraintLeft_toRightOf, 9);
                map.append(C0016R.styleable.ConstraintLayout_Layout_layout_constraintRight_toLeftOf, 10);
                map.append(C0016R.styleable.ConstraintLayout_Layout_layout_constraintRight_toRightOf, 11);
                map.append(C0016R.styleable.ConstraintLayout_Layout_layout_constraintTop_toTopOf, 12);
                map.append(C0016R.styleable.ConstraintLayout_Layout_layout_constraintTop_toBottomOf, 13);
                map.append(C0016R.styleable.ConstraintLayout_Layout_layout_constraintBottom_toTopOf, 14);
                map.append(C0016R.styleable.ConstraintLayout_Layout_layout_constraintBottom_toBottomOf, 15);
                map.append(C0016R.styleable.ConstraintLayout_Layout_layout_constraintBaseline_toBaselineOf, 16);
                map.append(C0016R.styleable.ConstraintLayout_Layout_layout_constraintCircle, 2);
                map.append(C0016R.styleable.ConstraintLayout_Layout_layout_constraintCircleRadius, 3);
                map.append(C0016R.styleable.ConstraintLayout_Layout_layout_constraintCircleAngle, 4);
                map.append(C0016R.styleable.ConstraintLayout_Layout_layout_editor_absoluteX, 49);
                map.append(C0016R.styleable.ConstraintLayout_Layout_layout_editor_absoluteY, 50);
                map.append(C0016R.styleable.ConstraintLayout_Layout_layout_constraintGuide_begin, 5);
                map.append(C0016R.styleable.ConstraintLayout_Layout_layout_constraintGuide_end, 6);
                map.append(C0016R.styleable.ConstraintLayout_Layout_layout_constraintGuide_percent, 7);
                map.append(C0016R.styleable.ConstraintLayout_Layout_android_orientation, 1);
                map.append(C0016R.styleable.ConstraintLayout_Layout_layout_constraintStart_toEndOf, 17);
                map.append(C0016R.styleable.ConstraintLayout_Layout_layout_constraintStart_toStartOf, 18);
                map.append(C0016R.styleable.ConstraintLayout_Layout_layout_constraintEnd_toStartOf, 19);
                map.append(C0016R.styleable.ConstraintLayout_Layout_layout_constraintEnd_toEndOf, 20);
                map.append(C0016R.styleable.ConstraintLayout_Layout_layout_goneMarginLeft, 21);
                map.append(C0016R.styleable.ConstraintLayout_Layout_layout_goneMarginTop, 22);
                map.append(C0016R.styleable.ConstraintLayout_Layout_layout_goneMarginRight, 23);
                map.append(C0016R.styleable.ConstraintLayout_Layout_layout_goneMarginBottom, 24);
                map.append(C0016R.styleable.ConstraintLayout_Layout_layout_goneMarginStart, 25);
                map.append(C0016R.styleable.ConstraintLayout_Layout_layout_goneMarginEnd, 26);
                map.append(C0016R.styleable.ConstraintLayout_Layout_layout_constraintHorizontal_bias, 29);
                map.append(C0016R.styleable.ConstraintLayout_Layout_layout_constraintVertical_bias, 30);
                map.append(C0016R.styleable.ConstraintLayout_Layout_layout_constraintDimensionRatio, 44);
                map.append(C0016R.styleable.ConstraintLayout_Layout_layout_constraintHorizontal_weight, 45);
                map.append(C0016R.styleable.ConstraintLayout_Layout_layout_constraintVertical_weight, 46);
                map.append(C0016R.styleable.ConstraintLayout_Layout_layout_constraintHorizontal_chainStyle, 47);
                map.append(C0016R.styleable.ConstraintLayout_Layout_layout_constraintVertical_chainStyle, 48);
                map.append(C0016R.styleable.ConstraintLayout_Layout_layout_constrainedWidth, 27);
                map.append(C0016R.styleable.ConstraintLayout_Layout_layout_constrainedHeight, 28);
                map.append(C0016R.styleable.ConstraintLayout_Layout_layout_constraintWidth_default, 31);
                map.append(C0016R.styleable.ConstraintLayout_Layout_layout_constraintHeight_default, 32);
                map.append(C0016R.styleable.ConstraintLayout_Layout_layout_constraintWidth_min, 33);
                map.append(C0016R.styleable.ConstraintLayout_Layout_layout_constraintWidth_max, 34);
                map.append(C0016R.styleable.ConstraintLayout_Layout_layout_constraintWidth_percent, 35);
                map.append(C0016R.styleable.ConstraintLayout_Layout_layout_constraintHeight_min, 36);
                map.append(C0016R.styleable.ConstraintLayout_Layout_layout_constraintHeight_max, 37);
                map.append(C0016R.styleable.ConstraintLayout_Layout_layout_constraintHeight_percent, 38);
                map.append(C0016R.styleable.ConstraintLayout_Layout_layout_constraintLeft_creator, 39);
                map.append(C0016R.styleable.ConstraintLayout_Layout_layout_constraintTop_creator, 40);
                map.append(C0016R.styleable.ConstraintLayout_Layout_layout_constraintRight_creator, 41);
                map.append(C0016R.styleable.ConstraintLayout_Layout_layout_constraintBottom_creator, 42);
                map.append(C0016R.styleable.ConstraintLayout_Layout_layout_constraintBaseline_creator, 43);
            }
        }

        public void reset() {
            if (this.widget != null) {
                this.widget.reset();
            }
        }

        public LayoutParams(LayoutParams layoutParams) {
            super(layoutParams);
            this.guideBegin = layoutParams.guideBegin;
            this.guideEnd = layoutParams.guideEnd;
            this.guidePercent = layoutParams.guidePercent;
            this.leftToLeft = layoutParams.leftToLeft;
            this.leftToRight = layoutParams.leftToRight;
            this.rightToLeft = layoutParams.rightToLeft;
            this.rightToRight = layoutParams.rightToRight;
            this.topToTop = layoutParams.topToTop;
            this.topToBottom = layoutParams.topToBottom;
            this.bottomToTop = layoutParams.bottomToTop;
            this.bottomToBottom = layoutParams.bottomToBottom;
            this.baselineToBaseline = layoutParams.baselineToBaseline;
            this.circleConstraint = layoutParams.circleConstraint;
            this.circleRadius = layoutParams.circleRadius;
            this.circleAngle = layoutParams.circleAngle;
            this.startToEnd = layoutParams.startToEnd;
            this.startToStart = layoutParams.startToStart;
            this.endToStart = layoutParams.endToStart;
            this.endToEnd = layoutParams.endToEnd;
            this.goneLeftMargin = layoutParams.goneLeftMargin;
            this.goneTopMargin = layoutParams.goneTopMargin;
            this.goneRightMargin = layoutParams.goneRightMargin;
            this.goneBottomMargin = layoutParams.goneBottomMargin;
            this.goneStartMargin = layoutParams.goneStartMargin;
            this.goneEndMargin = layoutParams.goneEndMargin;
            this.horizontalBias = layoutParams.horizontalBias;
            this.verticalBias = layoutParams.verticalBias;
            this.dimensionRatio = layoutParams.dimensionRatio;
            this.dimensionRatioValue = layoutParams.dimensionRatioValue;
            this.dimensionRatioSide = layoutParams.dimensionRatioSide;
            this.horizontalWeight = layoutParams.horizontalWeight;
            this.verticalWeight = layoutParams.verticalWeight;
            this.horizontalChainStyle = layoutParams.horizontalChainStyle;
            this.verticalChainStyle = layoutParams.verticalChainStyle;
            this.constrainedWidth = layoutParams.constrainedWidth;
            this.constrainedHeight = layoutParams.constrainedHeight;
            this.matchConstraintDefaultWidth = layoutParams.matchConstraintDefaultWidth;
            this.matchConstraintDefaultHeight = layoutParams.matchConstraintDefaultHeight;
            this.matchConstraintMinWidth = layoutParams.matchConstraintMinWidth;
            this.matchConstraintMaxWidth = layoutParams.matchConstraintMaxWidth;
            this.matchConstraintMinHeight = layoutParams.matchConstraintMinHeight;
            this.matchConstraintMaxHeight = layoutParams.matchConstraintMaxHeight;
            this.matchConstraintPercentWidth = layoutParams.matchConstraintPercentWidth;
            this.matchConstraintPercentHeight = layoutParams.matchConstraintPercentHeight;
            this.editorAbsoluteX = layoutParams.editorAbsoluteX;
            this.editorAbsoluteY = layoutParams.editorAbsoluteY;
            this.orientation = layoutParams.orientation;
            this.horizontalDimensionFixed = layoutParams.horizontalDimensionFixed;
            this.verticalDimensionFixed = layoutParams.verticalDimensionFixed;
            this.needsBaseline = layoutParams.needsBaseline;
            this.isGuideline = layoutParams.isGuideline;
            this.resolvedLeftToLeft = layoutParams.resolvedLeftToLeft;
            this.resolvedLeftToRight = layoutParams.resolvedLeftToRight;
            this.resolvedRightToLeft = layoutParams.resolvedRightToLeft;
            this.resolvedRightToRight = layoutParams.resolvedRightToRight;
            this.resolveGoneLeftMargin = layoutParams.resolveGoneLeftMargin;
            this.resolveGoneRightMargin = layoutParams.resolveGoneRightMargin;
            this.resolvedHorizontalBias = layoutParams.resolvedHorizontalBias;
            this.widget = layoutParams.widget;
        }

        public LayoutParams(android.content.Context r10, android.util.AttributeSet r11) {
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
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1603198149.run(Unknown Source)
*/
            /*
            r9 = this;
            r9.<init>(r10, r11);
            r0 = -1;
            r9.guideBegin = r0;
            r9.guideEnd = r0;
            r1 = -1082130432; // 0xffffffffbf800000 float:-1.0 double:NaN;
            r9.guidePercent = r1;
            r9.leftToLeft = r0;
            r9.leftToRight = r0;
            r9.rightToLeft = r0;
            r9.rightToRight = r0;
            r9.topToTop = r0;
            r9.topToBottom = r0;
            r9.bottomToTop = r0;
            r9.bottomToBottom = r0;
            r9.baselineToBaseline = r0;
            r9.circleConstraint = r0;
            r2 = 0;
            r9.circleRadius = r2;
            r3 = 0;
            r9.circleAngle = r3;
            r9.startToEnd = r0;
            r9.startToStart = r0;
            r9.endToStart = r0;
            r9.endToEnd = r0;
            r9.goneLeftMargin = r0;
            r9.goneTopMargin = r0;
            r9.goneRightMargin = r0;
            r9.goneBottomMargin = r0;
            r9.goneStartMargin = r0;
            r9.goneEndMargin = r0;
            r4 = 1056964608; // 0x3f000000 float:0.5 double:5.222099017E-315;
            r9.horizontalBias = r4;
            r9.verticalBias = r4;
            r5 = 0;
            r9.dimensionRatio = r5;
            r9.dimensionRatioValue = r3;
            r5 = 1;
            r9.dimensionRatioSide = r5;
            r9.horizontalWeight = r1;
            r9.verticalWeight = r1;
            r9.horizontalChainStyle = r2;
            r9.verticalChainStyle = r2;
            r9.matchConstraintDefaultWidth = r2;
            r9.matchConstraintDefaultHeight = r2;
            r9.matchConstraintMinWidth = r2;
            r9.matchConstraintMinHeight = r2;
            r9.matchConstraintMaxWidth = r2;
            r9.matchConstraintMaxHeight = r2;
            r1 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
            r9.matchConstraintPercentWidth = r1;
            r9.matchConstraintPercentHeight = r1;
            r9.editorAbsoluteX = r0;
            r9.editorAbsoluteY = r0;
            r9.orientation = r0;
            r9.constrainedWidth = r2;
            r9.constrainedHeight = r2;
            r9.horizontalDimensionFixed = r5;
            r9.verticalDimensionFixed = r5;
            r9.needsBaseline = r2;
            r9.isGuideline = r2;
            r9.isHelper = r2;
            r9.isInPlaceholder = r2;
            r9.resolvedLeftToLeft = r0;
            r9.resolvedLeftToRight = r0;
            r9.resolvedRightToLeft = r0;
            r9.resolvedRightToRight = r0;
            r9.resolveGoneLeftMargin = r0;
            r9.resolveGoneRightMargin = r0;
            r9.resolvedHorizontalBias = r4;
            r1 = new android.support.constraint.solver.widgets.ConstraintWidget;
            r1.<init>();
            r9.widget = r1;
            r9.helped = r2;
            r1 = android.support.constraint.C0016R.styleable.ConstraintLayout_Layout;
            r10 = r10.obtainStyledAttributes(r11, r1);
            r11 = r10.getIndexCount();
            r1 = 0;
        L_0x009a:
            if (r1 >= r11) goto L_0x03e9;
        L_0x009c:
            r4 = r10.getIndex(r1);
            r6 = android.support.constraint.ConstraintLayout.LayoutParams.Table.map;
            r6 = r6.get(r4);
            r7 = -2;
            switch(r6) {
                case 0: goto L_0x03e5;
                case 1: goto L_0x03dd;
                case 2: goto L_0x03ca;
                case 3: goto L_0x03c1;
                case 4: goto L_0x03a8;
                case 5: goto L_0x039f;
                case 6: goto L_0x0396;
                case 7: goto L_0x038d;
                case 8: goto L_0x037a;
                case 9: goto L_0x0367;
                case 10: goto L_0x0353;
                case 11: goto L_0x033f;
                case 12: goto L_0x032b;
                case 13: goto L_0x0317;
                case 14: goto L_0x0303;
                case 15: goto L_0x02ef;
                case 16: goto L_0x02db;
                case 17: goto L_0x02c7;
                case 18: goto L_0x02b3;
                case 19: goto L_0x029f;
                case 20: goto L_0x028b;
                case 21: goto L_0x0281;
                case 22: goto L_0x0277;
                case 23: goto L_0x026d;
                case 24: goto L_0x0263;
                case 25: goto L_0x0259;
                case 26: goto L_0x024f;
                case 27: goto L_0x0245;
                case 28: goto L_0x023b;
                case 29: goto L_0x0231;
                case 30: goto L_0x0227;
                case 31: goto L_0x0214;
                case 32: goto L_0x0201;
                case 33: goto L_0x01ea;
                case 34: goto L_0x01d3;
                case 35: goto L_0x01c5;
                case 36: goto L_0x01ae;
                case 37: goto L_0x0197;
                case 38: goto L_0x0189;
                case 39: goto L_0x03e5;
                case 40: goto L_0x03e5;
                case 41: goto L_0x03e5;
                case 42: goto L_0x03e5;
                case 43: goto L_0x00aa;
                case 44: goto L_0x00e4;
                case 45: goto L_0x00da;
                case 46: goto L_0x00d0;
                case 47: goto L_0x00c8;
                case 48: goto L_0x00c0;
                case 49: goto L_0x00b6;
                case 50: goto L_0x00ac;
                default: goto L_0x00aa;
            };
        L_0x00aa:
            goto L_0x03e5;
        L_0x00ac:
            r6 = r9.editorAbsoluteY;
            r4 = r10.getDimensionPixelOffset(r4, r6);
            r9.editorAbsoluteY = r4;
            goto L_0x03e5;
        L_0x00b6:
            r6 = r9.editorAbsoluteX;
            r4 = r10.getDimensionPixelOffset(r4, r6);
            r9.editorAbsoluteX = r4;
            goto L_0x03e5;
        L_0x00c0:
            r4 = r10.getInt(r4, r2);
            r9.verticalChainStyle = r4;
            goto L_0x03e5;
        L_0x00c8:
            r4 = r10.getInt(r4, r2);
            r9.horizontalChainStyle = r4;
            goto L_0x03e5;
        L_0x00d0:
            r6 = r9.verticalWeight;
            r4 = r10.getFloat(r4, r6);
            r9.verticalWeight = r4;
            goto L_0x03e5;
        L_0x00da:
            r6 = r9.horizontalWeight;
            r4 = r10.getFloat(r4, r6);
            r9.horizontalWeight = r4;
            goto L_0x03e5;
        L_0x00e4:
            r4 = r10.getString(r4);
            r9.dimensionRatio = r4;
            r4 = 2143289344; // 0x7fc00000 float:NaN double:1.058925634E-314;
            r9.dimensionRatioValue = r4;
            r9.dimensionRatioSide = r0;
            r4 = r9.dimensionRatio;
            if (r4 == 0) goto L_0x03e5;
        L_0x00f4:
            r4 = r9.dimensionRatio;
            r4 = r4.length();
            r6 = r9.dimensionRatio;
            r7 = 44;
            r6 = r6.indexOf(r7);
            if (r6 <= 0) goto L_0x0126;
        L_0x0104:
            r7 = r4 + -1;
            if (r6 >= r7) goto L_0x0126;
        L_0x0108:
            r7 = r9.dimensionRatio;
            r7 = r7.substring(r2, r6);
            r8 = "W";
            r8 = r7.equalsIgnoreCase(r8);
            if (r8 == 0) goto L_0x0119;
        L_0x0116:
            r9.dimensionRatioSide = r2;
            goto L_0x0123;
        L_0x0119:
            r8 = "H";
            r7 = r7.equalsIgnoreCase(r8);
            if (r7 == 0) goto L_0x0123;
        L_0x0121:
            r9.dimensionRatioSide = r5;
        L_0x0123:
            r6 = r6 + 1;
            goto L_0x0127;
        L_0x0126:
            r6 = 0;
        L_0x0127:
            r7 = r9.dimensionRatio;
            r8 = 58;
            r7 = r7.indexOf(r8);
            if (r7 < 0) goto L_0x0175;
        L_0x0131:
            r4 = r4 + -1;
            if (r7 >= r4) goto L_0x0175;
        L_0x0135:
            r4 = r9.dimensionRatio;
            r4 = r4.substring(r6, r7);
            r6 = r9.dimensionRatio;
            r7 = r7 + 1;
            r6 = r6.substring(r7);
            r7 = r4.length();
            if (r7 <= 0) goto L_0x03e5;
        L_0x0149:
            r7 = r6.length();
            if (r7 <= 0) goto L_0x03e5;
        L_0x014f:
            r4 = java.lang.Float.parseFloat(r4);	 Catch:{ NumberFormatException -> 0x03e5 }
            r6 = java.lang.Float.parseFloat(r6);	 Catch:{ NumberFormatException -> 0x03e5 }
            r7 = (r4 > r3 ? 1 : (r4 == r3 ? 0 : -1));	 Catch:{ NumberFormatException -> 0x03e5 }
            if (r7 <= 0) goto L_0x03e5;	 Catch:{ NumberFormatException -> 0x03e5 }
        L_0x015b:
            r7 = (r6 > r3 ? 1 : (r6 == r3 ? 0 : -1));	 Catch:{ NumberFormatException -> 0x03e5 }
            if (r7 <= 0) goto L_0x03e5;	 Catch:{ NumberFormatException -> 0x03e5 }
        L_0x015f:
            r7 = r9.dimensionRatioSide;	 Catch:{ NumberFormatException -> 0x03e5 }
            if (r7 != r5) goto L_0x016c;	 Catch:{ NumberFormatException -> 0x03e5 }
        L_0x0163:
            r6 = r6 / r4;	 Catch:{ NumberFormatException -> 0x03e5 }
            r4 = java.lang.Math.abs(r6);	 Catch:{ NumberFormatException -> 0x03e5 }
            r9.dimensionRatioValue = r4;	 Catch:{ NumberFormatException -> 0x03e5 }
            goto L_0x03e5;	 Catch:{ NumberFormatException -> 0x03e5 }
        L_0x016c:
            r4 = r4 / r6;	 Catch:{ NumberFormatException -> 0x03e5 }
            r4 = java.lang.Math.abs(r4);	 Catch:{ NumberFormatException -> 0x03e5 }
            r9.dimensionRatioValue = r4;	 Catch:{ NumberFormatException -> 0x03e5 }
            goto L_0x03e5;
        L_0x0175:
            r4 = r9.dimensionRatio;
            r4 = r4.substring(r6);
            r6 = r4.length();
            if (r6 <= 0) goto L_0x03e5;
        L_0x0181:
            r4 = java.lang.Float.parseFloat(r4);	 Catch:{ NumberFormatException -> 0x03e5 }
            r9.dimensionRatioValue = r4;	 Catch:{ NumberFormatException -> 0x03e5 }
            goto L_0x03e5;
        L_0x0189:
            r6 = r9.matchConstraintPercentHeight;
            r4 = r10.getFloat(r4, r6);
            r4 = java.lang.Math.max(r3, r4);
            r9.matchConstraintPercentHeight = r4;
            goto L_0x03e5;
        L_0x0197:
            r6 = r9.matchConstraintMaxHeight;	 Catch:{ Exception -> 0x01a1 }
            r6 = r10.getDimensionPixelSize(r4, r6);	 Catch:{ Exception -> 0x01a1 }
            r9.matchConstraintMaxHeight = r6;	 Catch:{ Exception -> 0x01a1 }
            goto L_0x03e5;
            r6 = r9.matchConstraintMaxHeight;
            r4 = r10.getInt(r4, r6);
            if (r4 != r7) goto L_0x03e5;
        L_0x01aa:
            r9.matchConstraintMaxHeight = r7;
            goto L_0x03e5;
        L_0x01ae:
            r6 = r9.matchConstraintMinHeight;	 Catch:{ Exception -> 0x01b8 }
            r6 = r10.getDimensionPixelSize(r4, r6);	 Catch:{ Exception -> 0x01b8 }
            r9.matchConstraintMinHeight = r6;	 Catch:{ Exception -> 0x01b8 }
            goto L_0x03e5;
            r6 = r9.matchConstraintMinHeight;
            r4 = r10.getInt(r4, r6);
            if (r4 != r7) goto L_0x03e5;
        L_0x01c1:
            r9.matchConstraintMinHeight = r7;
            goto L_0x03e5;
        L_0x01c5:
            r6 = r9.matchConstraintPercentWidth;
            r4 = r10.getFloat(r4, r6);
            r4 = java.lang.Math.max(r3, r4);
            r9.matchConstraintPercentWidth = r4;
            goto L_0x03e5;
        L_0x01d3:
            r6 = r9.matchConstraintMaxWidth;	 Catch:{ Exception -> 0x01dd }
            r6 = r10.getDimensionPixelSize(r4, r6);	 Catch:{ Exception -> 0x01dd }
            r9.matchConstraintMaxWidth = r6;	 Catch:{ Exception -> 0x01dd }
            goto L_0x03e5;
            r6 = r9.matchConstraintMaxWidth;
            r4 = r10.getInt(r4, r6);
            if (r4 != r7) goto L_0x03e5;
        L_0x01e6:
            r9.matchConstraintMaxWidth = r7;
            goto L_0x03e5;
        L_0x01ea:
            r6 = r9.matchConstraintMinWidth;	 Catch:{ Exception -> 0x01f4 }
            r6 = r10.getDimensionPixelSize(r4, r6);	 Catch:{ Exception -> 0x01f4 }
            r9.matchConstraintMinWidth = r6;	 Catch:{ Exception -> 0x01f4 }
            goto L_0x03e5;
            r6 = r9.matchConstraintMinWidth;
            r4 = r10.getInt(r4, r6);
            if (r4 != r7) goto L_0x03e5;
        L_0x01fd:
            r9.matchConstraintMinWidth = r7;
            goto L_0x03e5;
        L_0x0201:
            r4 = r10.getInt(r4, r2);
            r9.matchConstraintDefaultHeight = r4;
            r4 = r9.matchConstraintDefaultHeight;
            if (r4 != r5) goto L_0x03e5;
        L_0x020b:
            r4 = "ConstraintLayout";
            r6 = "layout_constraintHeight_default=\"wrap\" is deprecated.\nUse layout_height=\"WRAP_CONTENT\" and layout_constrainedHeight=\"true\" instead.";
            android.util.Log.e(r4, r6);
            goto L_0x03e5;
        L_0x0214:
            r4 = r10.getInt(r4, r2);
            r9.matchConstraintDefaultWidth = r4;
            r4 = r9.matchConstraintDefaultWidth;
            if (r4 != r5) goto L_0x03e5;
        L_0x021e:
            r4 = "ConstraintLayout";
            r6 = "layout_constraintWidth_default=\"wrap\" is deprecated.\nUse layout_width=\"WRAP_CONTENT\" and layout_constrainedWidth=\"true\" instead.";
            android.util.Log.e(r4, r6);
            goto L_0x03e5;
        L_0x0227:
            r6 = r9.verticalBias;
            r4 = r10.getFloat(r4, r6);
            r9.verticalBias = r4;
            goto L_0x03e5;
        L_0x0231:
            r6 = r9.horizontalBias;
            r4 = r10.getFloat(r4, r6);
            r9.horizontalBias = r4;
            goto L_0x03e5;
        L_0x023b:
            r6 = r9.constrainedHeight;
            r4 = r10.getBoolean(r4, r6);
            r9.constrainedHeight = r4;
            goto L_0x03e5;
        L_0x0245:
            r6 = r9.constrainedWidth;
            r4 = r10.getBoolean(r4, r6);
            r9.constrainedWidth = r4;
            goto L_0x03e5;
        L_0x024f:
            r6 = r9.goneEndMargin;
            r4 = r10.getDimensionPixelSize(r4, r6);
            r9.goneEndMargin = r4;
            goto L_0x03e5;
        L_0x0259:
            r6 = r9.goneStartMargin;
            r4 = r10.getDimensionPixelSize(r4, r6);
            r9.goneStartMargin = r4;
            goto L_0x03e5;
        L_0x0263:
            r6 = r9.goneBottomMargin;
            r4 = r10.getDimensionPixelSize(r4, r6);
            r9.goneBottomMargin = r4;
            goto L_0x03e5;
        L_0x026d:
            r6 = r9.goneRightMargin;
            r4 = r10.getDimensionPixelSize(r4, r6);
            r9.goneRightMargin = r4;
            goto L_0x03e5;
        L_0x0277:
            r6 = r9.goneTopMargin;
            r4 = r10.getDimensionPixelSize(r4, r6);
            r9.goneTopMargin = r4;
            goto L_0x03e5;
        L_0x0281:
            r6 = r9.goneLeftMargin;
            r4 = r10.getDimensionPixelSize(r4, r6);
            r9.goneLeftMargin = r4;
            goto L_0x03e5;
        L_0x028b:
            r6 = r9.endToEnd;
            r6 = r10.getResourceId(r4, r6);
            r9.endToEnd = r6;
            r6 = r9.endToEnd;
            if (r6 != r0) goto L_0x03e5;
        L_0x0297:
            r4 = r10.getInt(r4, r0);
            r9.endToEnd = r4;
            goto L_0x03e5;
        L_0x029f:
            r6 = r9.endToStart;
            r6 = r10.getResourceId(r4, r6);
            r9.endToStart = r6;
            r6 = r9.endToStart;
            if (r6 != r0) goto L_0x03e5;
        L_0x02ab:
            r4 = r10.getInt(r4, r0);
            r9.endToStart = r4;
            goto L_0x03e5;
        L_0x02b3:
            r6 = r9.startToStart;
            r6 = r10.getResourceId(r4, r6);
            r9.startToStart = r6;
            r6 = r9.startToStart;
            if (r6 != r0) goto L_0x03e5;
        L_0x02bf:
            r4 = r10.getInt(r4, r0);
            r9.startToStart = r4;
            goto L_0x03e5;
        L_0x02c7:
            r6 = r9.startToEnd;
            r6 = r10.getResourceId(r4, r6);
            r9.startToEnd = r6;
            r6 = r9.startToEnd;
            if (r6 != r0) goto L_0x03e5;
        L_0x02d3:
            r4 = r10.getInt(r4, r0);
            r9.startToEnd = r4;
            goto L_0x03e5;
        L_0x02db:
            r6 = r9.baselineToBaseline;
            r6 = r10.getResourceId(r4, r6);
            r9.baselineToBaseline = r6;
            r6 = r9.baselineToBaseline;
            if (r6 != r0) goto L_0x03e5;
        L_0x02e7:
            r4 = r10.getInt(r4, r0);
            r9.baselineToBaseline = r4;
            goto L_0x03e5;
        L_0x02ef:
            r6 = r9.bottomToBottom;
            r6 = r10.getResourceId(r4, r6);
            r9.bottomToBottom = r6;
            r6 = r9.bottomToBottom;
            if (r6 != r0) goto L_0x03e5;
        L_0x02fb:
            r4 = r10.getInt(r4, r0);
            r9.bottomToBottom = r4;
            goto L_0x03e5;
        L_0x0303:
            r6 = r9.bottomToTop;
            r6 = r10.getResourceId(r4, r6);
            r9.bottomToTop = r6;
            r6 = r9.bottomToTop;
            if (r6 != r0) goto L_0x03e5;
        L_0x030f:
            r4 = r10.getInt(r4, r0);
            r9.bottomToTop = r4;
            goto L_0x03e5;
        L_0x0317:
            r6 = r9.topToBottom;
            r6 = r10.getResourceId(r4, r6);
            r9.topToBottom = r6;
            r6 = r9.topToBottom;
            if (r6 != r0) goto L_0x03e5;
        L_0x0323:
            r4 = r10.getInt(r4, r0);
            r9.topToBottom = r4;
            goto L_0x03e5;
        L_0x032b:
            r6 = r9.topToTop;
            r6 = r10.getResourceId(r4, r6);
            r9.topToTop = r6;
            r6 = r9.topToTop;
            if (r6 != r0) goto L_0x03e5;
        L_0x0337:
            r4 = r10.getInt(r4, r0);
            r9.topToTop = r4;
            goto L_0x03e5;
        L_0x033f:
            r6 = r9.rightToRight;
            r6 = r10.getResourceId(r4, r6);
            r9.rightToRight = r6;
            r6 = r9.rightToRight;
            if (r6 != r0) goto L_0x03e5;
        L_0x034b:
            r4 = r10.getInt(r4, r0);
            r9.rightToRight = r4;
            goto L_0x03e5;
        L_0x0353:
            r6 = r9.rightToLeft;
            r6 = r10.getResourceId(r4, r6);
            r9.rightToLeft = r6;
            r6 = r9.rightToLeft;
            if (r6 != r0) goto L_0x03e5;
        L_0x035f:
            r4 = r10.getInt(r4, r0);
            r9.rightToLeft = r4;
            goto L_0x03e5;
        L_0x0367:
            r6 = r9.leftToRight;
            r6 = r10.getResourceId(r4, r6);
            r9.leftToRight = r6;
            r6 = r9.leftToRight;
            if (r6 != r0) goto L_0x03e5;
        L_0x0373:
            r4 = r10.getInt(r4, r0);
            r9.leftToRight = r4;
            goto L_0x03e5;
        L_0x037a:
            r6 = r9.leftToLeft;
            r6 = r10.getResourceId(r4, r6);
            r9.leftToLeft = r6;
            r6 = r9.leftToLeft;
            if (r6 != r0) goto L_0x03e5;
        L_0x0386:
            r4 = r10.getInt(r4, r0);
            r9.leftToLeft = r4;
            goto L_0x03e5;
        L_0x038d:
            r6 = r9.guidePercent;
            r4 = r10.getFloat(r4, r6);
            r9.guidePercent = r4;
            goto L_0x03e5;
        L_0x0396:
            r6 = r9.guideEnd;
            r4 = r10.getDimensionPixelOffset(r4, r6);
            r9.guideEnd = r4;
            goto L_0x03e5;
        L_0x039f:
            r6 = r9.guideBegin;
            r4 = r10.getDimensionPixelOffset(r4, r6);
            r9.guideBegin = r4;
            goto L_0x03e5;
        L_0x03a8:
            r6 = r9.circleAngle;
            r4 = r10.getFloat(r4, r6);
            r6 = 1135869952; // 0x43b40000 float:360.0 double:5.611943214E-315;
            r4 = r4 % r6;
            r9.circleAngle = r4;
            r4 = r9.circleAngle;
            r4 = (r4 > r3 ? 1 : (r4 == r3 ? 0 : -1));
            if (r4 >= 0) goto L_0x03e5;
        L_0x03b9:
            r4 = r9.circleAngle;
            r4 = r6 - r4;
            r4 = r4 % r6;
            r9.circleAngle = r4;
            goto L_0x03e5;
        L_0x03c1:
            r6 = r9.circleRadius;
            r4 = r10.getDimensionPixelSize(r4, r6);
            r9.circleRadius = r4;
            goto L_0x03e5;
        L_0x03ca:
            r6 = r9.circleConstraint;
            r6 = r10.getResourceId(r4, r6);
            r9.circleConstraint = r6;
            r6 = r9.circleConstraint;
            if (r6 != r0) goto L_0x03e5;
        L_0x03d6:
            r4 = r10.getInt(r4, r0);
            r9.circleConstraint = r4;
            goto L_0x03e5;
        L_0x03dd:
            r6 = r9.orientation;
            r4 = r10.getInt(r4, r6);
            r9.orientation = r4;
        L_0x03e5:
            r1 = r1 + 1;
            goto L_0x009a;
        L_0x03e9:
            r10.recycle();
            r9.validate();
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.ConstraintLayout.LayoutParams.<init>(android.content.Context, android.util.AttributeSet):void");
        }

        public void validate() {
            this.isGuideline = false;
            this.horizontalDimensionFixed = ConstraintLayout.USE_CONSTRAINTS_HELPER;
            this.verticalDimensionFixed = ConstraintLayout.USE_CONSTRAINTS_HELPER;
            if (this.width == -2 && this.constrainedWidth) {
                this.horizontalDimensionFixed = false;
                this.matchConstraintDefaultWidth = 1;
            }
            if (this.height == -2 && this.constrainedHeight) {
                this.verticalDimensionFixed = false;
                this.matchConstraintDefaultHeight = 1;
            }
            if (this.width == 0 || this.width == -1) {
                this.horizontalDimensionFixed = false;
                if (this.width == 0 && this.matchConstraintDefaultWidth == 1) {
                    this.width = -2;
                    this.constrainedWidth = ConstraintLayout.USE_CONSTRAINTS_HELPER;
                }
            }
            if (this.height == 0 || this.height == -1) {
                this.verticalDimensionFixed = false;
                if (this.height == 0 && this.matchConstraintDefaultHeight == 1) {
                    this.height = -2;
                    this.constrainedHeight = ConstraintLayout.USE_CONSTRAINTS_HELPER;
                }
            }
            if (this.guidePercent != -1.0f || this.guideBegin != -1 || this.guideEnd != -1) {
                this.isGuideline = ConstraintLayout.USE_CONSTRAINTS_HELPER;
                this.horizontalDimensionFixed = ConstraintLayout.USE_CONSTRAINTS_HELPER;
                this.verticalDimensionFixed = ConstraintLayout.USE_CONSTRAINTS_HELPER;
                if (!(this.widget instanceof Guideline)) {
                    this.widget = new Guideline();
                }
                ((Guideline) this.widget).setOrientation(this.orientation);
            }
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }

        @TargetApi(17)
        public void resolveLayoutDirection(int i) {
            int i2 = this.leftMargin;
            int i3 = this.rightMargin;
            super.resolveLayoutDirection(i);
            this.resolvedRightToLeft = -1;
            this.resolvedRightToRight = -1;
            this.resolvedLeftToLeft = -1;
            this.resolvedLeftToRight = -1;
            this.resolveGoneLeftMargin = -1;
            this.resolveGoneRightMargin = -1;
            this.resolveGoneLeftMargin = this.goneLeftMargin;
            this.resolveGoneRightMargin = this.goneRightMargin;
            this.resolvedHorizontalBias = this.horizontalBias;
            this.resolvedGuideBegin = this.guideBegin;
            this.resolvedGuideEnd = this.guideEnd;
            this.resolvedGuidePercent = this.guidePercent;
            Object obj = null;
            if ((1 == getLayoutDirection() ? 1 : null) != null) {
                if (this.startToEnd != -1) {
                    this.resolvedRightToLeft = this.startToEnd;
                } else {
                    if (this.startToStart != -1) {
                        this.resolvedRightToRight = this.startToStart;
                    }
                    if (this.endToStart != -1) {
                        this.resolvedLeftToRight = this.endToStart;
                        obj = 1;
                    }
                    if (this.endToEnd != -1) {
                        this.resolvedLeftToLeft = this.endToEnd;
                        obj = 1;
                    }
                    if (this.goneStartMargin != -1) {
                        this.resolveGoneRightMargin = this.goneStartMargin;
                    }
                    if (this.goneEndMargin != -1) {
                        this.resolveGoneLeftMargin = this.goneEndMargin;
                    }
                    if (obj != null) {
                        this.resolvedHorizontalBias = 1.0f - this.horizontalBias;
                    }
                    if (this.isGuideline && this.orientation == 1) {
                        if (this.guidePercent != -1.0f) {
                            this.resolvedGuidePercent = 1.0f - this.guidePercent;
                            this.resolvedGuideBegin = -1;
                            this.resolvedGuideEnd = -1;
                        } else if (this.guideBegin != -1) {
                            this.resolvedGuideEnd = this.guideBegin;
                            this.resolvedGuideBegin = -1;
                            this.resolvedGuidePercent = -1.0f;
                        } else if (this.guideEnd != -1) {
                            this.resolvedGuideBegin = this.guideEnd;
                            this.resolvedGuideEnd = -1;
                            this.resolvedGuidePercent = -1.0f;
                        }
                    }
                }
                obj = 1;
                if (this.endToStart != -1) {
                    this.resolvedLeftToRight = this.endToStart;
                    obj = 1;
                }
                if (this.endToEnd != -1) {
                    this.resolvedLeftToLeft = this.endToEnd;
                    obj = 1;
                }
                if (this.goneStartMargin != -1) {
                    this.resolveGoneRightMargin = this.goneStartMargin;
                }
                if (this.goneEndMargin != -1) {
                    this.resolveGoneLeftMargin = this.goneEndMargin;
                }
                if (obj != null) {
                    this.resolvedHorizontalBias = 1.0f - this.horizontalBias;
                }
                if (this.guidePercent != -1.0f) {
                    this.resolvedGuidePercent = 1.0f - this.guidePercent;
                    this.resolvedGuideBegin = -1;
                    this.resolvedGuideEnd = -1;
                } else if (this.guideBegin != -1) {
                    this.resolvedGuideEnd = this.guideBegin;
                    this.resolvedGuideBegin = -1;
                    this.resolvedGuidePercent = -1.0f;
                } else if (this.guideEnd != -1) {
                    this.resolvedGuideBegin = this.guideEnd;
                    this.resolvedGuideEnd = -1;
                    this.resolvedGuidePercent = -1.0f;
                }
            } else {
                if (this.startToEnd != -1) {
                    this.resolvedLeftToRight = this.startToEnd;
                }
                if (this.startToStart != -1) {
                    this.resolvedLeftToLeft = this.startToStart;
                }
                if (this.endToStart != -1) {
                    this.resolvedRightToLeft = this.endToStart;
                }
                if (this.endToEnd != -1) {
                    this.resolvedRightToRight = this.endToEnd;
                }
                if (this.goneStartMargin != -1) {
                    this.resolveGoneLeftMargin = this.goneStartMargin;
                }
                if (this.goneEndMargin != -1) {
                    this.resolveGoneRightMargin = this.goneEndMargin;
                }
            }
            if (this.endToStart == -1 && this.endToEnd == -1 && this.startToStart == -1 && this.startToEnd == -1) {
                if (this.rightToLeft != -1) {
                    this.resolvedRightToLeft = this.rightToLeft;
                    if (this.rightMargin <= 0 && i3 > 0) {
                        this.rightMargin = i3;
                    }
                } else if (this.rightToRight != -1) {
                    this.resolvedRightToRight = this.rightToRight;
                    if (this.rightMargin <= 0 && i3 > 0) {
                        this.rightMargin = i3;
                    }
                }
                if (this.leftToLeft != -1) {
                    this.resolvedLeftToLeft = this.leftToLeft;
                    if (this.leftMargin <= 0 && i2 > 0) {
                        this.leftMargin = i2;
                    }
                } else if (this.leftToRight != -1) {
                    this.resolvedLeftToRight = this.leftToRight;
                    if (this.leftMargin <= 0 && i2 > 0) {
                        this.leftMargin = i2;
                    }
                }
            }
        }
    }

    public boolean shouldDelayChildPressedState() {
        return false;
    }

    public void setDesignInformation(int i, Object obj, Object obj2) {
        if (i == 0 && (obj instanceof String) != 0 && (obj2 instanceof Integer) != 0) {
            if (this.mDesignIds == 0) {
                this.mDesignIds = new HashMap();
            }
            obj = (String) obj;
            i = obj.indexOf("/");
            if (i != -1) {
                obj = obj.substring(i + 1);
            }
            this.mDesignIds.put(obj, Integer.valueOf(((Integer) obj2).intValue()));
        }
    }

    public Object getDesignInformation(int i, Object obj) {
        if (i == 0 && (obj instanceof String) != 0) {
            String str = (String) obj;
            if (!(this.mDesignIds == 0 || this.mDesignIds.containsKey(str) == 0)) {
                return this.mDesignIds.get(str);
            }
        }
        return 0;
    }

    public ConstraintLayout(Context context) {
        super(context);
        init(null);
    }

    public ConstraintLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(attributeSet);
    }

    public ConstraintLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(attributeSet);
    }

    public void setId(int i) {
        this.mChildrenByIds.remove(getId());
        super.setId(i);
        this.mChildrenByIds.put(getId(), this);
    }

    private void init(android.util.AttributeSet r8) {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1603198149.run(Unknown Source)
*/
        /*
        r7 = this;
        r0 = r7.mLayoutWidget;
        r0.setCompanionWidget(r7);
        r0 = r7.mChildrenByIds;
        r1 = r7.getId();
        r0.put(r1, r7);
        r0 = 0;
        r7.mConstraintSet = r0;
        if (r8 == 0) goto L_0x008d;
    L_0x0013:
        r1 = r7.getContext();
        r2 = android.support.constraint.C0016R.styleable.ConstraintLayout_Layout;
        r8 = r1.obtainStyledAttributes(r8, r2);
        r1 = r8.getIndexCount();
        r2 = 0;
        r3 = 0;
    L_0x0023:
        if (r3 >= r1) goto L_0x008a;
    L_0x0025:
        r4 = r8.getIndex(r3);
        r5 = android.support.constraint.C0016R.styleable.ConstraintLayout_Layout_android_minWidth;
        if (r4 != r5) goto L_0x0036;
    L_0x002d:
        r5 = r7.mMinWidth;
        r4 = r8.getDimensionPixelOffset(r4, r5);
        r7.mMinWidth = r4;
        goto L_0x0087;
    L_0x0036:
        r5 = android.support.constraint.C0016R.styleable.ConstraintLayout_Layout_android_minHeight;
        if (r4 != r5) goto L_0x0043;
    L_0x003a:
        r5 = r7.mMinHeight;
        r4 = r8.getDimensionPixelOffset(r4, r5);
        r7.mMinHeight = r4;
        goto L_0x0087;
    L_0x0043:
        r5 = android.support.constraint.C0016R.styleable.ConstraintLayout_Layout_android_maxWidth;
        if (r4 != r5) goto L_0x0050;
    L_0x0047:
        r5 = r7.mMaxWidth;
        r4 = r8.getDimensionPixelOffset(r4, r5);
        r7.mMaxWidth = r4;
        goto L_0x0087;
    L_0x0050:
        r5 = android.support.constraint.C0016R.styleable.ConstraintLayout_Layout_android_maxHeight;
        if (r4 != r5) goto L_0x005d;
    L_0x0054:
        r5 = r7.mMaxHeight;
        r4 = r8.getDimensionPixelOffset(r4, r5);
        r7.mMaxHeight = r4;
        goto L_0x0087;
    L_0x005d:
        r5 = android.support.constraint.C0016R.styleable.ConstraintLayout_Layout_layout_optimizationLevel;
        if (r4 != r5) goto L_0x006a;
    L_0x0061:
        r5 = r7.mOptimizationLevel;
        r4 = r8.getInt(r4, r5);
        r7.mOptimizationLevel = r4;
        goto L_0x0087;
    L_0x006a:
        r5 = android.support.constraint.C0016R.styleable.ConstraintLayout_Layout_constraintSet;
        if (r4 != r5) goto L_0x0087;
    L_0x006e:
        r4 = r8.getResourceId(r4, r2);
        r5 = new android.support.constraint.ConstraintSet;	 Catch:{ NotFoundException -> 0x0083 }
        r5.<init>();	 Catch:{ NotFoundException -> 0x0083 }
        r7.mConstraintSet = r5;	 Catch:{ NotFoundException -> 0x0083 }
        r5 = r7.mConstraintSet;	 Catch:{ NotFoundException -> 0x0083 }
        r6 = r7.getContext();	 Catch:{ NotFoundException -> 0x0083 }
        r5.load(r6, r4);	 Catch:{ NotFoundException -> 0x0083 }
        goto L_0x0085;
    L_0x0083:
        r7.mConstraintSet = r0;
    L_0x0085:
        r7.mConstraintSetId = r4;
    L_0x0087:
        r3 = r3 + 1;
        goto L_0x0023;
    L_0x008a:
        r8.recycle();
    L_0x008d:
        r8 = r7.mLayoutWidget;
        r0 = r7.mOptimizationLevel;
        r8.setOptimizationLevel(r0);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.ConstraintLayout.init(android.util.AttributeSet):void");
    }

    public void addView(View view, int i, android.view.ViewGroup.LayoutParams layoutParams) {
        super.addView(view, i, layoutParams);
        if (VERSION.SDK_INT < 14) {
            onViewAdded(view);
        }
    }

    public void removeView(View view) {
        super.removeView(view);
        if (VERSION.SDK_INT < 14) {
            onViewRemoved(view);
        }
    }

    public void onViewAdded(View view) {
        if (VERSION.SDK_INT >= 14) {
            super.onViewAdded(view);
        }
        ConstraintWidget viewWidget = getViewWidget(view);
        if ((view instanceof Guideline) && !(viewWidget instanceof Guideline)) {
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            layoutParams.widget = new Guideline();
            layoutParams.isGuideline = USE_CONSTRAINTS_HELPER;
            ((Guideline) layoutParams.widget).setOrientation(layoutParams.orientation);
        }
        if (view instanceof ConstraintHelper) {
            ConstraintHelper constraintHelper = (ConstraintHelper) view;
            constraintHelper.validateParams();
            ((LayoutParams) view.getLayoutParams()).isHelper = USE_CONSTRAINTS_HELPER;
            if (!this.mConstraintHelpers.contains(constraintHelper)) {
                this.mConstraintHelpers.add(constraintHelper);
            }
        }
        this.mChildrenByIds.put(view.getId(), view);
        this.mDirtyHierarchy = USE_CONSTRAINTS_HELPER;
    }

    public void onViewRemoved(View view) {
        if (VERSION.SDK_INT >= 14) {
            super.onViewRemoved(view);
        }
        this.mChildrenByIds.remove(view.getId());
        ConstraintWidget viewWidget = getViewWidget(view);
        this.mLayoutWidget.remove(viewWidget);
        this.mConstraintHelpers.remove(view);
        this.mVariableDimensionsWidgets.remove(viewWidget);
        this.mDirtyHierarchy = USE_CONSTRAINTS_HELPER;
    }

    public void setMinWidth(int i) {
        if (i != this.mMinWidth) {
            this.mMinWidth = i;
            requestLayout();
        }
    }

    public void setMinHeight(int i) {
        if (i != this.mMinHeight) {
            this.mMinHeight = i;
            requestLayout();
        }
    }

    public int getMinWidth() {
        return this.mMinWidth;
    }

    public int getMinHeight() {
        return this.mMinHeight;
    }

    public void setMaxWidth(int i) {
        if (i != this.mMaxWidth) {
            this.mMaxWidth = i;
            requestLayout();
        }
    }

    public void setMaxHeight(int i) {
        if (i != this.mMaxHeight) {
            this.mMaxHeight = i;
            requestLayout();
        }
    }

    public int getMaxWidth() {
        return this.mMaxWidth;
    }

    public int getMaxHeight() {
        return this.mMaxHeight;
    }

    private void updateHierarchy() {
        int childCount = getChildCount();
        Object obj = null;
        for (int i = 0; i < childCount; i++) {
            if (getChildAt(i).isLayoutRequested()) {
                obj = 1;
                break;
            }
        }
        if (obj != null) {
            this.mVariableDimensionsWidgets.clear();
            setChildrenConstraints();
        }
    }

    private void setChildrenConstraints() {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1603198149.run(Unknown Source)
*/
        /*
        r26 = this;
        r0 = r26;
        r1 = r26.isInEditMode();
        r2 = r26.getChildCount();
        r3 = 0;
        r4 = -1;
        if (r1 == 0) goto L_0x0048;
    L_0x000e:
        r5 = 0;
    L_0x000f:
        if (r5 >= r2) goto L_0x0048;
    L_0x0011:
        r6 = r0.getChildAt(r5);
        r7 = r26.getResources();	 Catch:{ NotFoundException -> 0x0045 }
        r8 = r6.getId();	 Catch:{ NotFoundException -> 0x0045 }
        r7 = r7.getResourceName(r8);	 Catch:{ NotFoundException -> 0x0045 }
        r8 = r6.getId();	 Catch:{ NotFoundException -> 0x0045 }
        r8 = java.lang.Integer.valueOf(r8);	 Catch:{ NotFoundException -> 0x0045 }
        r0.setDesignInformation(r3, r7, r8);	 Catch:{ NotFoundException -> 0x0045 }
        r8 = 47;	 Catch:{ NotFoundException -> 0x0045 }
        r8 = r7.indexOf(r8);	 Catch:{ NotFoundException -> 0x0045 }
        if (r8 == r4) goto L_0x003a;	 Catch:{ NotFoundException -> 0x0045 }
    L_0x0034:
        r8 = r8 + 1;	 Catch:{ NotFoundException -> 0x0045 }
        r7 = r7.substring(r8);	 Catch:{ NotFoundException -> 0x0045 }
    L_0x003a:
        r6 = r6.getId();	 Catch:{ NotFoundException -> 0x0045 }
        r6 = r0.getTargetWidget(r6);	 Catch:{ NotFoundException -> 0x0045 }
        r6.setDebugName(r7);	 Catch:{ NotFoundException -> 0x0045 }
    L_0x0045:
        r5 = r5 + 1;
        goto L_0x000f;
    L_0x0048:
        r5 = 0;
    L_0x0049:
        if (r5 >= r2) goto L_0x005c;
    L_0x004b:
        r6 = r0.getChildAt(r5);
        r6 = r0.getViewWidget(r6);
        if (r6 != 0) goto L_0x0056;
    L_0x0055:
        goto L_0x0059;
    L_0x0056:
        r6.reset();
    L_0x0059:
        r5 = r5 + 1;
        goto L_0x0049;
    L_0x005c:
        r5 = r0.mConstraintSetId;
        if (r5 == r4) goto L_0x007e;
    L_0x0060:
        r5 = 0;
    L_0x0061:
        if (r5 >= r2) goto L_0x007e;
    L_0x0063:
        r6 = r0.getChildAt(r5);
        r7 = r6.getId();
        r8 = r0.mConstraintSetId;
        if (r7 != r8) goto L_0x007b;
    L_0x006f:
        r7 = r6 instanceof android.support.constraint.Constraints;
        if (r7 == 0) goto L_0x007b;
    L_0x0073:
        r6 = (android.support.constraint.Constraints) r6;
        r6 = r6.getConstraintSet();
        r0.mConstraintSet = r6;
    L_0x007b:
        r5 = r5 + 1;
        goto L_0x0061;
    L_0x007e:
        r5 = r0.mConstraintSet;
        if (r5 == 0) goto L_0x0087;
    L_0x0082:
        r5 = r0.mConstraintSet;
        r5.applyToInternal(r0);
    L_0x0087:
        r5 = r0.mLayoutWidget;
        r5.removeAllChildren();
        r5 = r0.mConstraintHelpers;
        r5 = r5.size();
        if (r5 <= 0) goto L_0x00a5;
    L_0x0094:
        r6 = 0;
    L_0x0095:
        if (r6 >= r5) goto L_0x00a5;
    L_0x0097:
        r7 = r0.mConstraintHelpers;
        r7 = r7.get(r6);
        r7 = (android.support.constraint.ConstraintHelper) r7;
        r7.updatePreLayout(r0);
        r6 = r6 + 1;
        goto L_0x0095;
    L_0x00a5:
        r5 = 0;
    L_0x00a6:
        if (r5 >= r2) goto L_0x00b8;
    L_0x00a8:
        r6 = r0.getChildAt(r5);
        r7 = r6 instanceof android.support.constraint.Placeholder;
        if (r7 == 0) goto L_0x00b5;
    L_0x00b0:
        r6 = (android.support.constraint.Placeholder) r6;
        r6.updatePreLayout(r0);
    L_0x00b5:
        r5 = r5 + 1;
        goto L_0x00a6;
    L_0x00b8:
        r5 = 0;
    L_0x00b9:
        if (r5 >= r2) goto L_0x03f9;
    L_0x00bb:
        r6 = r0.getChildAt(r5);
        r13 = r0.getViewWidget(r6);
        if (r13 != 0) goto L_0x00c7;
    L_0x00c5:
        goto L_0x03f5;
    L_0x00c7:
        r7 = r6.getLayoutParams();
        r14 = r7;
        r14 = (android.support.constraint.ConstraintLayout.LayoutParams) r14;
        r14.validate();
        r7 = r14.helped;
        if (r7 == 0) goto L_0x00d8;
    L_0x00d5:
        r14.helped = r3;
        goto L_0x010a;
    L_0x00d8:
        if (r1 == 0) goto L_0x010a;
    L_0x00da:
        r7 = r26.getResources();	 Catch:{ NotFoundException -> 0x0109 }
        r8 = r6.getId();	 Catch:{ NotFoundException -> 0x0109 }
        r7 = r7.getResourceName(r8);	 Catch:{ NotFoundException -> 0x0109 }
        r8 = r6.getId();	 Catch:{ NotFoundException -> 0x0109 }
        r8 = java.lang.Integer.valueOf(r8);	 Catch:{ NotFoundException -> 0x0109 }
        r0.setDesignInformation(r3, r7, r8);	 Catch:{ NotFoundException -> 0x0109 }
        r8 = "id/";	 Catch:{ NotFoundException -> 0x0109 }
        r8 = r7.indexOf(r8);	 Catch:{ NotFoundException -> 0x0109 }
        r8 = r8 + 3;	 Catch:{ NotFoundException -> 0x0109 }
        r7 = r7.substring(r8);	 Catch:{ NotFoundException -> 0x0109 }
        r8 = r6.getId();	 Catch:{ NotFoundException -> 0x0109 }
        r8 = r0.getTargetWidget(r8);	 Catch:{ NotFoundException -> 0x0109 }
        r8.setDebugName(r7);	 Catch:{ NotFoundException -> 0x0109 }
        goto L_0x010a;
    L_0x010a:
        r7 = r6.getVisibility();
        r13.setVisibility(r7);
        r7 = r14.isInPlaceholder;
        if (r7 == 0) goto L_0x011a;
    L_0x0115:
        r7 = 8;
        r13.setVisibility(r7);
    L_0x011a:
        r13.setCompanionWidget(r6);
        r6 = r0.mLayoutWidget;
        r6.add(r13);
        r6 = r14.verticalDimensionFixed;
        if (r6 == 0) goto L_0x012a;
    L_0x0126:
        r6 = r14.horizontalDimensionFixed;
        if (r6 != 0) goto L_0x012f;
    L_0x012a:
        r6 = r0.mVariableDimensionsWidgets;
        r6.add(r13);
    L_0x012f:
        r6 = r14.isGuideline;
        r7 = 17;
        if (r6 == 0) goto L_0x0160;
    L_0x0135:
        r13 = (android.support.constraint.solver.widgets.Guideline) r13;
        r6 = r14.resolvedGuideBegin;
        r8 = r14.resolvedGuideEnd;
        r9 = r14.resolvedGuidePercent;
        r10 = android.os.Build.VERSION.SDK_INT;
        if (r10 >= r7) goto L_0x0147;
    L_0x0141:
        r6 = r14.guideBegin;
        r8 = r14.guideEnd;
        r9 = r14.guidePercent;
    L_0x0147:
        r7 = -1082130432; // 0xffffffffbf800000 float:-1.0 double:NaN;
        r7 = (r9 > r7 ? 1 : (r9 == r7 ? 0 : -1));
        if (r7 == 0) goto L_0x0152;
    L_0x014d:
        r13.setGuidePercent(r9);
        goto L_0x03f5;
    L_0x0152:
        if (r6 == r4) goto L_0x0159;
    L_0x0154:
        r13.setGuideBegin(r6);
        goto L_0x03f5;
    L_0x0159:
        if (r8 == r4) goto L_0x03f5;
    L_0x015b:
        r13.setGuideEnd(r8);
        goto L_0x03f5;
    L_0x0160:
        r6 = r14.leftToLeft;
        if (r6 != r4) goto L_0x01a8;
    L_0x0164:
        r6 = r14.leftToRight;
        if (r6 != r4) goto L_0x01a8;
    L_0x0168:
        r6 = r14.rightToLeft;
        if (r6 != r4) goto L_0x01a8;
    L_0x016c:
        r6 = r14.rightToRight;
        if (r6 != r4) goto L_0x01a8;
    L_0x0170:
        r6 = r14.startToStart;
        if (r6 != r4) goto L_0x01a8;
    L_0x0174:
        r6 = r14.startToEnd;
        if (r6 != r4) goto L_0x01a8;
    L_0x0178:
        r6 = r14.endToStart;
        if (r6 != r4) goto L_0x01a8;
    L_0x017c:
        r6 = r14.endToEnd;
        if (r6 != r4) goto L_0x01a8;
    L_0x0180:
        r6 = r14.topToTop;
        if (r6 != r4) goto L_0x01a8;
    L_0x0184:
        r6 = r14.topToBottom;
        if (r6 != r4) goto L_0x01a8;
    L_0x0188:
        r6 = r14.bottomToTop;
        if (r6 != r4) goto L_0x01a8;
    L_0x018c:
        r6 = r14.bottomToBottom;
        if (r6 != r4) goto L_0x01a8;
    L_0x0190:
        r6 = r14.baselineToBaseline;
        if (r6 != r4) goto L_0x01a8;
    L_0x0194:
        r6 = r14.editorAbsoluteX;
        if (r6 != r4) goto L_0x01a8;
    L_0x0198:
        r6 = r14.editorAbsoluteY;
        if (r6 != r4) goto L_0x01a8;
    L_0x019c:
        r6 = r14.circleConstraint;
        if (r6 != r4) goto L_0x01a8;
    L_0x01a0:
        r6 = r14.width;
        if (r6 == r4) goto L_0x01a8;
    L_0x01a4:
        r6 = r14.height;
        if (r6 != r4) goto L_0x03f5;
    L_0x01a8:
        r6 = r14.resolvedLeftToLeft;
        r8 = r14.resolvedLeftToRight;
        r9 = r14.resolvedRightToLeft;
        r10 = r14.resolvedRightToRight;
        r11 = r14.resolveGoneLeftMargin;
        r12 = r14.resolveGoneRightMargin;
        r15 = r14.resolvedHorizontalBias;
        r3 = android.os.Build.VERSION.SDK_INT;
        if (r3 >= r7) goto L_0x01f3;
    L_0x01ba:
        r3 = r14.leftToLeft;
        r6 = r14.leftToRight;
        r9 = r14.rightToLeft;
        r10 = r14.rightToRight;
        r7 = r14.goneLeftMargin;
        r8 = r14.goneRightMargin;
        r15 = r14.horizontalBias;
        if (r3 != r4) goto L_0x01d9;
    L_0x01ca:
        if (r6 != r4) goto L_0x01d9;
    L_0x01cc:
        r11 = r14.startToStart;
        if (r11 == r4) goto L_0x01d3;
    L_0x01d0:
        r3 = r14.startToStart;
        goto L_0x01d9;
    L_0x01d3:
        r11 = r14.startToEnd;
        if (r11 == r4) goto L_0x01d9;
    L_0x01d7:
        r6 = r14.startToEnd;
    L_0x01d9:
        r25 = r6;
        r6 = r3;
        r3 = r25;
        if (r9 != r4) goto L_0x01ef;
    L_0x01e0:
        if (r10 != r4) goto L_0x01ef;
    L_0x01e2:
        r11 = r14.endToStart;
        if (r11 == r4) goto L_0x01e9;
    L_0x01e6:
        r9 = r14.endToStart;
        goto L_0x01ef;
    L_0x01e9:
        r11 = r14.endToEnd;
        if (r11 == r4) goto L_0x01ef;
    L_0x01ed:
        r10 = r14.endToEnd;
    L_0x01ef:
        r12 = r7;
        r16 = r8;
        goto L_0x01f7;
    L_0x01f3:
        r3 = r8;
        r16 = r12;
        r12 = r11;
    L_0x01f7:
        r11 = r10;
        r10 = r15;
        r15 = r9;
        r7 = r14.circleConstraint;
        if (r7 == r4) goto L_0x020f;
    L_0x01fe:
        r3 = r14.circleConstraint;
        r3 = r0.getTargetWidget(r3);
        if (r3 == 0) goto L_0x0343;
    L_0x0206:
        r6 = r14.circleAngle;
        r7 = r14.circleRadius;
        r13.connectCircularConstraint(r3, r6, r7);
        goto L_0x0343;
    L_0x020f:
        if (r6 == r4) goto L_0x022c;
    L_0x0211:
        r9 = r0.getTargetWidget(r6);
        if (r9 == 0) goto L_0x0227;
    L_0x0217:
        r8 = android.support.constraint.solver.widgets.ConstraintAnchor.Type.LEFT;
        r3 = android.support.constraint.solver.widgets.ConstraintAnchor.Type.LEFT;
        r6 = r14.leftMargin;
        r7 = r13;
        r17 = r10;
        r10 = r3;
        r3 = r11;
        r11 = r6;
        r7.immediateConnect(r8, r9, r10, r11, r12);
        goto L_0x022a;
    L_0x0227:
        r17 = r10;
        r3 = r11;
    L_0x022a:
        r6 = r3;
        goto L_0x0241;
    L_0x022c:
        r17 = r10;
        r6 = r11;
        if (r3 == r4) goto L_0x0241;
    L_0x0231:
        r9 = r0.getTargetWidget(r3);
        if (r9 == 0) goto L_0x0241;
    L_0x0237:
        r8 = android.support.constraint.solver.widgets.ConstraintAnchor.Type.LEFT;
        r10 = android.support.constraint.solver.widgets.ConstraintAnchor.Type.RIGHT;
        r11 = r14.leftMargin;
        r7 = r13;
        r7.immediateConnect(r8, r9, r10, r11, r12);
    L_0x0241:
        if (r15 == r4) goto L_0x0256;
    L_0x0243:
        r9 = r0.getTargetWidget(r15);
        if (r9 == 0) goto L_0x026a;
    L_0x0249:
        r8 = android.support.constraint.solver.widgets.ConstraintAnchor.Type.RIGHT;
        r10 = android.support.constraint.solver.widgets.ConstraintAnchor.Type.LEFT;
        r11 = r14.rightMargin;
        r7 = r13;
        r12 = r16;
        r7.immediateConnect(r8, r9, r10, r11, r12);
        goto L_0x026a;
    L_0x0256:
        if (r6 == r4) goto L_0x026a;
    L_0x0258:
        r9 = r0.getTargetWidget(r6);
        if (r9 == 0) goto L_0x026a;
    L_0x025e:
        r8 = android.support.constraint.solver.widgets.ConstraintAnchor.Type.RIGHT;
        r10 = android.support.constraint.solver.widgets.ConstraintAnchor.Type.RIGHT;
        r11 = r14.rightMargin;
        r7 = r13;
        r12 = r16;
        r7.immediateConnect(r8, r9, r10, r11, r12);
    L_0x026a:
        r3 = r14.topToTop;
        if (r3 == r4) goto L_0x0283;
    L_0x026e:
        r3 = r14.topToTop;
        r9 = r0.getTargetWidget(r3);
        if (r9 == 0) goto L_0x029b;
    L_0x0276:
        r8 = android.support.constraint.solver.widgets.ConstraintAnchor.Type.TOP;
        r10 = android.support.constraint.solver.widgets.ConstraintAnchor.Type.TOP;
        r11 = r14.topMargin;
        r12 = r14.goneTopMargin;
        r7 = r13;
        r7.immediateConnect(r8, r9, r10, r11, r12);
        goto L_0x029b;
    L_0x0283:
        r3 = r14.topToBottom;
        if (r3 == r4) goto L_0x029b;
    L_0x0287:
        r3 = r14.topToBottom;
        r9 = r0.getTargetWidget(r3);
        if (r9 == 0) goto L_0x029b;
    L_0x028f:
        r8 = android.support.constraint.solver.widgets.ConstraintAnchor.Type.TOP;
        r10 = android.support.constraint.solver.widgets.ConstraintAnchor.Type.BOTTOM;
        r11 = r14.topMargin;
        r12 = r14.goneTopMargin;
        r7 = r13;
        r7.immediateConnect(r8, r9, r10, r11, r12);
    L_0x029b:
        r3 = r14.bottomToTop;
        if (r3 == r4) goto L_0x02b4;
    L_0x029f:
        r3 = r14.bottomToTop;
        r9 = r0.getTargetWidget(r3);
        if (r9 == 0) goto L_0x02cc;
    L_0x02a7:
        r8 = android.support.constraint.solver.widgets.ConstraintAnchor.Type.BOTTOM;
        r10 = android.support.constraint.solver.widgets.ConstraintAnchor.Type.TOP;
        r11 = r14.bottomMargin;
        r12 = r14.goneBottomMargin;
        r7 = r13;
        r7.immediateConnect(r8, r9, r10, r11, r12);
        goto L_0x02cc;
    L_0x02b4:
        r3 = r14.bottomToBottom;
        if (r3 == r4) goto L_0x02cc;
    L_0x02b8:
        r3 = r14.bottomToBottom;
        r9 = r0.getTargetWidget(r3);
        if (r9 == 0) goto L_0x02cc;
    L_0x02c0:
        r8 = android.support.constraint.solver.widgets.ConstraintAnchor.Type.BOTTOM;
        r10 = android.support.constraint.solver.widgets.ConstraintAnchor.Type.BOTTOM;
        r11 = r14.bottomMargin;
        r12 = r14.goneBottomMargin;
        r7 = r13;
        r7.immediateConnect(r8, r9, r10, r11, r12);
    L_0x02cc:
        r3 = r14.baselineToBaseline;
        if (r3 == r4) goto L_0x0322;
    L_0x02d0:
        r3 = r0.mChildrenByIds;
        r6 = r14.baselineToBaseline;
        r3 = r3.get(r6);
        r3 = (android.view.View) r3;
        r6 = r14.baselineToBaseline;
        r6 = r0.getTargetWidget(r6);
        if (r6 == 0) goto L_0x0322;
    L_0x02e2:
        if (r3 == 0) goto L_0x0322;
    L_0x02e4:
        r7 = r3.getLayoutParams();
        r7 = r7 instanceof android.support.constraint.ConstraintLayout.LayoutParams;
        if (r7 == 0) goto L_0x0322;
    L_0x02ec:
        r3 = r3.getLayoutParams();
        r3 = (android.support.constraint.ConstraintLayout.LayoutParams) r3;
        r7 = 1;
        r14.needsBaseline = r7;
        r3.needsBaseline = r7;
        r3 = android.support.constraint.solver.widgets.ConstraintAnchor.Type.BASELINE;
        r18 = r13.getAnchor(r3);
        r3 = android.support.constraint.solver.widgets.ConstraintAnchor.Type.BASELINE;
        r19 = r6.getAnchor(r3);
        r20 = 0;
        r21 = -1;
        r22 = android.support.constraint.solver.widgets.ConstraintAnchor.Strength.STRONG;
        r23 = 0;
        r24 = 1;
        r18.connect(r19, r20, r21, r22, r23, r24);
        r3 = android.support.constraint.solver.widgets.ConstraintAnchor.Type.TOP;
        r3 = r13.getAnchor(r3);
        r3.reset();
        r3 = android.support.constraint.solver.widgets.ConstraintAnchor.Type.BOTTOM;
        r3 = r13.getAnchor(r3);
        r3.reset();
    L_0x0322:
        r3 = 1056964608; // 0x3f000000 float:0.5 double:5.222099017E-315;
        r6 = 0;
        r15 = r17;
        r7 = (r15 > r6 ? 1 : (r15 == r6 ? 0 : -1));
        if (r7 < 0) goto L_0x0332;
    L_0x032b:
        r7 = (r15 > r3 ? 1 : (r15 == r3 ? 0 : -1));
        if (r7 == 0) goto L_0x0332;
    L_0x032f:
        r13.setHorizontalBiasPercent(r15);
    L_0x0332:
        r7 = r14.verticalBias;
        r6 = (r7 > r6 ? 1 : (r7 == r6 ? 0 : -1));
        if (r6 < 0) goto L_0x0343;
    L_0x0338:
        r6 = r14.verticalBias;
        r3 = (r6 > r3 ? 1 : (r6 == r3 ? 0 : -1));
        if (r3 == 0) goto L_0x0343;
    L_0x033e:
        r3 = r14.verticalBias;
        r13.setVerticalBiasPercent(r3);
    L_0x0343:
        if (r1 == 0) goto L_0x0354;
    L_0x0345:
        r3 = r14.editorAbsoluteX;
        if (r3 != r4) goto L_0x034d;
    L_0x0349:
        r3 = r14.editorAbsoluteY;
        if (r3 == r4) goto L_0x0354;
    L_0x034d:
        r3 = r14.editorAbsoluteX;
        r6 = r14.editorAbsoluteY;
        r13.setOrigin(r3, r6);
    L_0x0354:
        r3 = r14.horizontalDimensionFixed;
        if (r3 != 0) goto L_0x0380;
    L_0x0358:
        r3 = r14.width;
        if (r3 != r4) goto L_0x0376;
    L_0x035c:
        r3 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_PARENT;
        r13.setHorizontalDimensionBehaviour(r3);
        r3 = android.support.constraint.solver.widgets.ConstraintAnchor.Type.LEFT;
        r3 = r13.getAnchor(r3);
        r6 = r14.leftMargin;
        r3.mMargin = r6;
        r3 = android.support.constraint.solver.widgets.ConstraintAnchor.Type.RIGHT;
        r3 = r13.getAnchor(r3);
        r6 = r14.rightMargin;
        r3.mMargin = r6;
        goto L_0x038a;
    L_0x0376:
        r3 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        r13.setHorizontalDimensionBehaviour(r3);
        r3 = 0;
        r13.setWidth(r3);
        goto L_0x038a;
    L_0x0380:
        r3 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.FIXED;
        r13.setHorizontalDimensionBehaviour(r3);
        r3 = r14.width;
        r13.setWidth(r3);
    L_0x038a:
        r3 = r14.verticalDimensionFixed;
        if (r3 != 0) goto L_0x03b7;
    L_0x038e:
        r3 = r14.height;
        if (r3 != r4) goto L_0x03ad;
    L_0x0392:
        r3 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_PARENT;
        r13.setVerticalDimensionBehaviour(r3);
        r3 = android.support.constraint.solver.widgets.ConstraintAnchor.Type.TOP;
        r3 = r13.getAnchor(r3);
        r6 = r14.topMargin;
        r3.mMargin = r6;
        r3 = android.support.constraint.solver.widgets.ConstraintAnchor.Type.BOTTOM;
        r3 = r13.getAnchor(r3);
        r6 = r14.bottomMargin;
        r3.mMargin = r6;
        r3 = 0;
        goto L_0x03c2;
    L_0x03ad:
        r3 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        r13.setVerticalDimensionBehaviour(r3);
        r3 = 0;
        r13.setHeight(r3);
        goto L_0x03c2;
    L_0x03b7:
        r3 = 0;
        r6 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.FIXED;
        r13.setVerticalDimensionBehaviour(r6);
        r6 = r14.height;
        r13.setHeight(r6);
    L_0x03c2:
        r6 = r14.dimensionRatio;
        if (r6 == 0) goto L_0x03cb;
    L_0x03c6:
        r6 = r14.dimensionRatio;
        r13.setDimensionRatio(r6);
    L_0x03cb:
        r6 = r14.horizontalWeight;
        r13.setHorizontalWeight(r6);
        r6 = r14.verticalWeight;
        r13.setVerticalWeight(r6);
        r6 = r14.horizontalChainStyle;
        r13.setHorizontalChainStyle(r6);
        r6 = r14.verticalChainStyle;
        r13.setVerticalChainStyle(r6);
        r6 = r14.matchConstraintDefaultWidth;
        r7 = r14.matchConstraintMinWidth;
        r8 = r14.matchConstraintMaxWidth;
        r9 = r14.matchConstraintPercentWidth;
        r13.setHorizontalMatchStyle(r6, r7, r8, r9);
        r6 = r14.matchConstraintDefaultHeight;
        r7 = r14.matchConstraintMinHeight;
        r8 = r14.matchConstraintMaxHeight;
        r9 = r14.matchConstraintPercentHeight;
        r13.setVerticalMatchStyle(r6, r7, r8, r9);
    L_0x03f5:
        r5 = r5 + 1;
        goto L_0x00b9;
    L_0x03f9:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.ConstraintLayout.setChildrenConstraints():void");
    }

    private final ConstraintWidget getTargetWidget(int i) {
        if (i == 0) {
            return this.mLayoutWidget;
        }
        View view = (View) this.mChildrenByIds.get(i);
        if (view == null) {
            view = findViewById(i);
            if (!(view == null || view == this || view.getParent() != this)) {
                onViewAdded(view);
            }
        }
        if (view == this) {
            return this.mLayoutWidget;
        }
        if (view == null) {
            i = 0;
        } else {
            i = ((LayoutParams) view.getLayoutParams()).widget;
        }
        return i;
    }

    public final ConstraintWidget getViewWidget(View view) {
        if (view == this) {
            return this.mLayoutWidget;
        }
        if (view == null) {
            view = null;
        } else {
            view = ((LayoutParams) view.getLayoutParams()).widget;
        }
        return view;
    }

    private void internalMeasureChildren(int i, int i2) {
        ConstraintLayout constraintLayout = this;
        int i3 = i;
        int i4 = i2;
        int paddingTop = getPaddingTop() + getPaddingBottom();
        int paddingLeft = getPaddingLeft() + getPaddingRight();
        int childCount = getChildCount();
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            if (childAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                ConstraintWidget constraintWidget = layoutParams.widget;
                if (!layoutParams.isGuideline) {
                    if (!layoutParams.isHelper) {
                        Object obj;
                        int childMeasureSpec;
                        Object obj2;
                        int childMeasureSpec2;
                        Object obj3;
                        Metrics metrics;
                        constraintWidget.setVisibility(childAt.getVisibility());
                        int i6 = layoutParams.width;
                        int i7 = layoutParams.height;
                        if (!(layoutParams.horizontalDimensionFixed || layoutParams.verticalDimensionFixed || ((!layoutParams.horizontalDimensionFixed && layoutParams.matchConstraintDefaultWidth == 1) || layoutParams.width == -1))) {
                            if (!layoutParams.verticalDimensionFixed) {
                                if (layoutParams.matchConstraintDefaultHeight != 1) {
                                    if (layoutParams.height == -1) {
                                    }
                                }
                            }
                            obj = null;
                            if (obj == null) {
                                if (i6 == 0) {
                                    childMeasureSpec = getChildMeasureSpec(i3, paddingLeft, -2);
                                    obj2 = 1;
                                } else if (i6 != -1) {
                                    childMeasureSpec = getChildMeasureSpec(i3, paddingLeft, -1);
                                    obj2 = null;
                                } else {
                                    obj2 = i6 != -2 ? 1 : null;
                                    childMeasureSpec = getChildMeasureSpec(i3, paddingLeft, i6);
                                }
                                if (i7 == 0) {
                                    childMeasureSpec2 = getChildMeasureSpec(i4, paddingTop, -2);
                                    obj3 = 1;
                                } else if (i7 != -1) {
                                    childMeasureSpec2 = getChildMeasureSpec(i4, paddingTop, -1);
                                    obj3 = null;
                                } else {
                                    obj3 = i7 != -2 ? 1 : null;
                                    childMeasureSpec2 = getChildMeasureSpec(i4, paddingTop, i7);
                                }
                                childAt.measure(childMeasureSpec, childMeasureSpec2);
                                if (constraintLayout.mMetrics != null) {
                                    metrics = constraintLayout.mMetrics;
                                    metrics.measures++;
                                }
                                constraintWidget.setWidthWrapContent(i6 != -2 ? USE_CONSTRAINTS_HELPER : false);
                                constraintWidget.setHeightWrapContent(i7 != -2 ? USE_CONSTRAINTS_HELPER : false);
                                i6 = childAt.getMeasuredWidth();
                                i7 = childAt.getMeasuredHeight();
                            } else {
                                obj2 = null;
                                obj3 = null;
                            }
                            constraintWidget.setWidth(i6);
                            constraintWidget.setHeight(i7);
                            if (obj2 != null) {
                                constraintWidget.setWrapWidth(i6);
                            }
                            if (obj3 != null) {
                                constraintWidget.setWrapHeight(i7);
                            }
                            if (layoutParams.needsBaseline) {
                                childMeasureSpec = childAt.getBaseline();
                                if (childMeasureSpec != -1) {
                                    constraintWidget.setBaselineDistance(childMeasureSpec);
                                }
                            }
                        }
                        obj = 1;
                        if (obj == null) {
                            obj2 = null;
                            obj3 = null;
                        } else {
                            if (i6 == 0) {
                                childMeasureSpec = getChildMeasureSpec(i3, paddingLeft, -2);
                                obj2 = 1;
                            } else if (i6 != -1) {
                                if (i6 != -2) {
                                }
                                childMeasureSpec = getChildMeasureSpec(i3, paddingLeft, i6);
                            } else {
                                childMeasureSpec = getChildMeasureSpec(i3, paddingLeft, -1);
                                obj2 = null;
                            }
                            if (i7 == 0) {
                                childMeasureSpec2 = getChildMeasureSpec(i4, paddingTop, -2);
                                obj3 = 1;
                            } else if (i7 != -1) {
                                if (i7 != -2) {
                                }
                                childMeasureSpec2 = getChildMeasureSpec(i4, paddingTop, i7);
                            } else {
                                childMeasureSpec2 = getChildMeasureSpec(i4, paddingTop, -1);
                                obj3 = null;
                            }
                            childAt.measure(childMeasureSpec, childMeasureSpec2);
                            if (constraintLayout.mMetrics != null) {
                                metrics = constraintLayout.mMetrics;
                                metrics.measures++;
                            }
                            if (i6 != -2) {
                            }
                            constraintWidget.setWidthWrapContent(i6 != -2 ? USE_CONSTRAINTS_HELPER : false);
                            if (i7 != -2) {
                            }
                            constraintWidget.setHeightWrapContent(i7 != -2 ? USE_CONSTRAINTS_HELPER : false);
                            i6 = childAt.getMeasuredWidth();
                            i7 = childAt.getMeasuredHeight();
                        }
                        constraintWidget.setWidth(i6);
                        constraintWidget.setHeight(i7);
                        if (obj2 != null) {
                            constraintWidget.setWrapWidth(i6);
                        }
                        if (obj3 != null) {
                            constraintWidget.setWrapHeight(i7);
                        }
                        if (layoutParams.needsBaseline) {
                            childMeasureSpec = childAt.getBaseline();
                            if (childMeasureSpec != -1) {
                                constraintWidget.setBaselineDistance(childMeasureSpec);
                            }
                        }
                    }
                }
            }
        }
    }

    private void updatePostMeasures() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt instanceof Placeholder) {
                ((Placeholder) childAt).updatePostMeasure(this);
            }
        }
        childCount = this.mConstraintHelpers.size();
        if (childCount > 0) {
            for (int i2 = 0; i2 < childCount; i2++) {
                ((ConstraintHelper) this.mConstraintHelpers.get(i2)).updatePostMeasure(this);
            }
        }
    }

    private void internalMeasureDimensions(int i, int i2) {
        int i3;
        int i4;
        ConstraintLayout constraintLayout = this;
        int i5 = i;
        int i6 = i2;
        int paddingTop = getPaddingTop() + getPaddingBottom();
        int paddingLeft = getPaddingLeft() + getPaddingRight();
        int childCount = getChildCount();
        int i7 = 0;
        while (true) {
            long j = 1;
            int i8 = 8;
            if (i7 >= childCount) {
                break;
            }
            View childAt = getChildAt(i7);
            if (childAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                ConstraintWidget constraintWidget = layoutParams.widget;
                if (!layoutParams.isGuideline) {
                    if (!layoutParams.isHelper) {
                        constraintWidget.setVisibility(childAt.getVisibility());
                        int i9 = layoutParams.width;
                        i3 = layoutParams.height;
                        if (i9 != 0) {
                            if (i3 != 0) {
                                Object obj = i9 == -2 ? 1 : null;
                                int childMeasureSpec = getChildMeasureSpec(i5, paddingLeft, i9);
                                Object obj2 = i3 == -2 ? 1 : null;
                                childAt.measure(childMeasureSpec, getChildMeasureSpec(i6, paddingTop, i3));
                                if (constraintLayout.mMetrics != null) {
                                    Metrics metrics = constraintLayout.mMetrics;
                                    i4 = paddingTop;
                                    metrics.measures++;
                                } else {
                                    i4 = paddingTop;
                                }
                                constraintWidget.setWidthWrapContent(i9 == -2 ? USE_CONSTRAINTS_HELPER : false);
                                constraintWidget.setHeightWrapContent(i3 == -2 ? USE_CONSTRAINTS_HELPER : false);
                                i6 = childAt.getMeasuredWidth();
                                paddingTop = childAt.getMeasuredHeight();
                                constraintWidget.setWidth(i6);
                                constraintWidget.setHeight(paddingTop);
                                if (obj != null) {
                                    constraintWidget.setWrapWidth(i6);
                                }
                                if (obj2 != null) {
                                    constraintWidget.setWrapHeight(paddingTop);
                                }
                                if (layoutParams.needsBaseline) {
                                    i9 = childAt.getBaseline();
                                    if (i9 != -1) {
                                        constraintWidget.setBaselineDistance(i9);
                                    }
                                }
                                if (layoutParams.horizontalDimensionFixed && layoutParams.verticalDimensionFixed) {
                                    constraintWidget.getResolutionWidth().resolve(i6);
                                    constraintWidget.getResolutionHeight().resolve(paddingTop);
                                }
                                i7++;
                                paddingTop = i4;
                                i6 = i2;
                            }
                        }
                        i4 = paddingTop;
                        constraintWidget.getResolutionWidth().invalidate();
                        constraintWidget.getResolutionHeight().invalidate();
                        i7++;
                        paddingTop = i4;
                        i6 = i2;
                    }
                }
            }
            i4 = paddingTop;
            i7++;
            paddingTop = i4;
            i6 = i2;
        }
        i4 = paddingTop;
        constraintLayout.mLayoutWidget.solveGraph();
        i6 = 0;
        while (i6 < childCount) {
            int i10;
            int i11;
            int i12;
            int i13;
            long j2;
            View childAt2 = getChildAt(i6);
            if (childAt2.getVisibility() != i8) {
                LayoutParams layoutParams2 = (LayoutParams) childAt2.getLayoutParams();
                ConstraintWidget constraintWidget2 = layoutParams2.widget;
                if (!layoutParams2.isGuideline) {
                    if (!layoutParams2.isHelper) {
                        constraintWidget2.setVisibility(childAt2.getVisibility());
                        childMeasureSpec = layoutParams2.width;
                        int i14 = layoutParams2.height;
                        if (childMeasureSpec == 0 || i14 == 0) {
                            ResolutionAnchor resolutionNode = constraintWidget2.getAnchor(Type.LEFT).getResolutionNode();
                            ResolutionAnchor resolutionNode2 = constraintWidget2.getAnchor(Type.RIGHT).getResolutionNode();
                            Object obj3 = (constraintWidget2.getAnchor(Type.LEFT).getTarget() == null || constraintWidget2.getAnchor(Type.RIGHT).getTarget() == null) ? null : 1;
                            ResolutionAnchor resolutionNode3 = constraintWidget2.getAnchor(Type.TOP).getResolutionNode();
                            ResolutionAnchor resolutionNode4 = constraintWidget2.getAnchor(Type.BOTTOM).getResolutionNode();
                            Object obj4 = (constraintWidget2.getAnchor(Type.TOP).getTarget() == null || constraintWidget2.getAnchor(Type.BOTTOM).getTarget() == null) ? null : 1;
                            if (childMeasureSpec != 0 || i14 != 0 || obj3 == null || obj4 == null) {
                                Object obj5;
                                int childMeasureSpec2;
                                Object obj6;
                                Metrics metrics2;
                                i10 = childCount;
                                i11 = i6;
                                Object obj7 = constraintLayout.mLayoutWidget.getHorizontalDimensionBehaviour() != DimensionBehaviour.WRAP_CONTENT ? 1 : null;
                                LayoutParams layoutParams3 = layoutParams2;
                                Object obj8 = constraintLayout.mLayoutWidget.getVerticalDimensionBehaviour() != DimensionBehaviour.WRAP_CONTENT ? 1 : null;
                                if (obj7 == null) {
                                    constraintWidget2.getResolutionWidth().invalidate();
                                }
                                if (obj8 == null) {
                                    constraintWidget2.getResolutionHeight().invalidate();
                                }
                                if (childMeasureSpec == 0) {
                                    if (obj7 != null && constraintWidget2.isSpreadWidth() && obj3 != null && resolutionNode.isResolved() && resolutionNode2.isResolved()) {
                                        childMeasureSpec = (int) (resolutionNode2.getResolvedValue() - resolutionNode.getResolvedValue());
                                        constraintWidget2.getResolutionWidth().resolve(childMeasureSpec);
                                        i3 = getChildMeasureSpec(i5, paddingLeft, childMeasureSpec);
                                    } else {
                                        i3 = getChildMeasureSpec(i5, paddingLeft, -2);
                                        obj7 = null;
                                        obj5 = 1;
                                        if (i14 != 0) {
                                            i12 = i4;
                                            i13 = i2;
                                            if (i14 == -1) {
                                                childMeasureSpec2 = getChildMeasureSpec(i13, i12, -1);
                                            } else {
                                                obj6 = i14 == -2 ? 1 : null;
                                                childMeasureSpec2 = getChildMeasureSpec(i13, i12, i14);
                                                childAt2.measure(i3, childMeasureSpec2);
                                                if (constraintLayout.mMetrics != null) {
                                                    j2 = 1;
                                                } else {
                                                    metrics2 = constraintLayout.mMetrics;
                                                    j2 = 1;
                                                    metrics2.measures++;
                                                }
                                                if (childMeasureSpec == -2) {
                                                }
                                                constraintWidget2.setWidthWrapContent(childMeasureSpec == -2 ? USE_CONSTRAINTS_HELPER : false);
                                                if (i14 == -2) {
                                                }
                                                constraintWidget2.setHeightWrapContent(i14 == -2 ? USE_CONSTRAINTS_HELPER : false);
                                                childMeasureSpec = childAt2.getMeasuredWidth();
                                                i14 = childAt2.getMeasuredHeight();
                                                constraintWidget2.setWidth(childMeasureSpec);
                                                constraintWidget2.setHeight(i14);
                                                if (obj5 != null) {
                                                    constraintWidget2.setWrapWidth(childMeasureSpec);
                                                }
                                                if (obj6 != null) {
                                                    constraintWidget2.setWrapHeight(i14);
                                                }
                                                if (obj7 != null) {
                                                    constraintWidget2.getResolutionWidth().remove();
                                                } else {
                                                    constraintWidget2.getResolutionWidth().resolve(childMeasureSpec);
                                                }
                                                if (obj8 != null) {
                                                    constraintWidget2.getResolutionHeight().remove();
                                                } else {
                                                    constraintWidget2.getResolutionHeight().resolve(i14);
                                                }
                                                if (layoutParams3.needsBaseline) {
                                                    i6 = childAt2.getBaseline();
                                                    if (i6 != -1) {
                                                        constraintWidget2.setBaselineDistance(i6);
                                                    }
                                                }
                                                i6 = i11 + 1;
                                                i4 = i12;
                                                j = j2;
                                                childCount = i10;
                                                i8 = 8;
                                            }
                                        } else if (obj8 == null && constraintWidget2.isSpreadHeight() && obj4 != null && resolutionNode3.isResolved() && resolutionNode4.isResolved()) {
                                            i14 = (int) (resolutionNode4.getResolvedValue() - resolutionNode3.getResolvedValue());
                                            constraintWidget2.getResolutionHeight().resolve(i14);
                                            i12 = i4;
                                            childMeasureSpec2 = getChildMeasureSpec(i2, i12, i14);
                                        } else {
                                            i12 = i4;
                                            childMeasureSpec2 = getChildMeasureSpec(i2, i12, -2);
                                            obj8 = null;
                                            obj6 = 1;
                                            childAt2.measure(i3, childMeasureSpec2);
                                            if (constraintLayout.mMetrics != null) {
                                                metrics2 = constraintLayout.mMetrics;
                                                j2 = 1;
                                                metrics2.measures++;
                                            } else {
                                                j2 = 1;
                                            }
                                            constraintWidget2.setWidthWrapContent(childMeasureSpec == -2 ? USE_CONSTRAINTS_HELPER : false);
                                            constraintWidget2.setHeightWrapContent(i14 == -2 ? USE_CONSTRAINTS_HELPER : false);
                                            childMeasureSpec = childAt2.getMeasuredWidth();
                                            i14 = childAt2.getMeasuredHeight();
                                            constraintWidget2.setWidth(childMeasureSpec);
                                            constraintWidget2.setHeight(i14);
                                            if (obj5 != null) {
                                                constraintWidget2.setWrapWidth(childMeasureSpec);
                                            }
                                            if (obj6 != null) {
                                                constraintWidget2.setWrapHeight(i14);
                                            }
                                            if (obj7 != null) {
                                                constraintWidget2.getResolutionWidth().resolve(childMeasureSpec);
                                            } else {
                                                constraintWidget2.getResolutionWidth().remove();
                                            }
                                            if (obj8 != null) {
                                                constraintWidget2.getResolutionHeight().resolve(i14);
                                            } else {
                                                constraintWidget2.getResolutionHeight().remove();
                                            }
                                            if (layoutParams3.needsBaseline) {
                                                i6 = childAt2.getBaseline();
                                                if (i6 != -1) {
                                                    constraintWidget2.setBaselineDistance(i6);
                                                }
                                            }
                                            i6 = i11 + 1;
                                            i4 = i12;
                                            j = j2;
                                            childCount = i10;
                                            i8 = 8;
                                        }
                                        obj6 = null;
                                        childAt2.measure(i3, childMeasureSpec2);
                                        if (constraintLayout.mMetrics != null) {
                                            metrics2 = constraintLayout.mMetrics;
                                            j2 = 1;
                                            metrics2.measures++;
                                        } else {
                                            j2 = 1;
                                        }
                                        if (childMeasureSpec == -2) {
                                        }
                                        constraintWidget2.setWidthWrapContent(childMeasureSpec == -2 ? USE_CONSTRAINTS_HELPER : false);
                                        if (i14 == -2) {
                                        }
                                        constraintWidget2.setHeightWrapContent(i14 == -2 ? USE_CONSTRAINTS_HELPER : false);
                                        childMeasureSpec = childAt2.getMeasuredWidth();
                                        i14 = childAt2.getMeasuredHeight();
                                        constraintWidget2.setWidth(childMeasureSpec);
                                        constraintWidget2.setHeight(i14);
                                        if (obj5 != null) {
                                            constraintWidget2.setWrapWidth(childMeasureSpec);
                                        }
                                        if (obj6 != null) {
                                            constraintWidget2.setWrapHeight(i14);
                                        }
                                        if (obj7 != null) {
                                            constraintWidget2.getResolutionWidth().resolve(childMeasureSpec);
                                        } else {
                                            constraintWidget2.getResolutionWidth().remove();
                                        }
                                        if (obj8 != null) {
                                            constraintWidget2.getResolutionHeight().resolve(i14);
                                        } else {
                                            constraintWidget2.getResolutionHeight().remove();
                                        }
                                        if (layoutParams3.needsBaseline) {
                                            i6 = childAt2.getBaseline();
                                            if (i6 != -1) {
                                                constraintWidget2.setBaselineDistance(i6);
                                            }
                                        }
                                        i6 = i11 + 1;
                                        i4 = i12;
                                        j = j2;
                                        childCount = i10;
                                        i8 = 8;
                                    }
                                } else if (childMeasureSpec == -1) {
                                    i3 = getChildMeasureSpec(i5, paddingLeft, -1);
                                } else {
                                    obj5 = childMeasureSpec == -2 ? 1 : null;
                                    i3 = getChildMeasureSpec(i5, paddingLeft, childMeasureSpec);
                                    if (i14 != 0) {
                                        i12 = i4;
                                        i13 = i2;
                                        if (i14 == -1) {
                                            if (i14 == -2) {
                                            }
                                            childMeasureSpec2 = getChildMeasureSpec(i13, i12, i14);
                                            childAt2.measure(i3, childMeasureSpec2);
                                            if (constraintLayout.mMetrics != null) {
                                                j2 = 1;
                                            } else {
                                                metrics2 = constraintLayout.mMetrics;
                                                j2 = 1;
                                                metrics2.measures++;
                                            }
                                            if (childMeasureSpec == -2) {
                                            }
                                            constraintWidget2.setWidthWrapContent(childMeasureSpec == -2 ? USE_CONSTRAINTS_HELPER : false);
                                            if (i14 == -2) {
                                            }
                                            constraintWidget2.setHeightWrapContent(i14 == -2 ? USE_CONSTRAINTS_HELPER : false);
                                            childMeasureSpec = childAt2.getMeasuredWidth();
                                            i14 = childAt2.getMeasuredHeight();
                                            constraintWidget2.setWidth(childMeasureSpec);
                                            constraintWidget2.setHeight(i14);
                                            if (obj5 != null) {
                                                constraintWidget2.setWrapWidth(childMeasureSpec);
                                            }
                                            if (obj6 != null) {
                                                constraintWidget2.setWrapHeight(i14);
                                            }
                                            if (obj7 != null) {
                                                constraintWidget2.getResolutionWidth().remove();
                                            } else {
                                                constraintWidget2.getResolutionWidth().resolve(childMeasureSpec);
                                            }
                                            if (obj8 != null) {
                                                constraintWidget2.getResolutionHeight().remove();
                                            } else {
                                                constraintWidget2.getResolutionHeight().resolve(i14);
                                            }
                                            if (layoutParams3.needsBaseline) {
                                                i6 = childAt2.getBaseline();
                                                if (i6 != -1) {
                                                    constraintWidget2.setBaselineDistance(i6);
                                                }
                                            }
                                            i6 = i11 + 1;
                                            i4 = i12;
                                            j = j2;
                                            childCount = i10;
                                            i8 = 8;
                                        } else {
                                            childMeasureSpec2 = getChildMeasureSpec(i13, i12, -1);
                                        }
                                    } else {
                                        if (obj8 == null) {
                                        }
                                        i12 = i4;
                                        childMeasureSpec2 = getChildMeasureSpec(i2, i12, -2);
                                        obj8 = null;
                                        obj6 = 1;
                                        childAt2.measure(i3, childMeasureSpec2);
                                        if (constraintLayout.mMetrics != null) {
                                            metrics2 = constraintLayout.mMetrics;
                                            j2 = 1;
                                            metrics2.measures++;
                                        } else {
                                            j2 = 1;
                                        }
                                        if (childMeasureSpec == -2) {
                                        }
                                        constraintWidget2.setWidthWrapContent(childMeasureSpec == -2 ? USE_CONSTRAINTS_HELPER : false);
                                        if (i14 == -2) {
                                        }
                                        constraintWidget2.setHeightWrapContent(i14 == -2 ? USE_CONSTRAINTS_HELPER : false);
                                        childMeasureSpec = childAt2.getMeasuredWidth();
                                        i14 = childAt2.getMeasuredHeight();
                                        constraintWidget2.setWidth(childMeasureSpec);
                                        constraintWidget2.setHeight(i14);
                                        if (obj5 != null) {
                                            constraintWidget2.setWrapWidth(childMeasureSpec);
                                        }
                                        if (obj6 != null) {
                                            constraintWidget2.setWrapHeight(i14);
                                        }
                                        if (obj7 != null) {
                                            constraintWidget2.getResolutionWidth().resolve(childMeasureSpec);
                                        } else {
                                            constraintWidget2.getResolutionWidth().remove();
                                        }
                                        if (obj8 != null) {
                                            constraintWidget2.getResolutionHeight().resolve(i14);
                                        } else {
                                            constraintWidget2.getResolutionHeight().remove();
                                        }
                                        if (layoutParams3.needsBaseline) {
                                            i6 = childAt2.getBaseline();
                                            if (i6 != -1) {
                                                constraintWidget2.setBaselineDistance(i6);
                                            }
                                        }
                                        i6 = i11 + 1;
                                        i4 = i12;
                                        j = j2;
                                        childCount = i10;
                                        i8 = 8;
                                    }
                                    obj6 = null;
                                    childAt2.measure(i3, childMeasureSpec2);
                                    if (constraintLayout.mMetrics != null) {
                                        j2 = 1;
                                    } else {
                                        metrics2 = constraintLayout.mMetrics;
                                        j2 = 1;
                                        metrics2.measures++;
                                    }
                                    if (childMeasureSpec == -2) {
                                    }
                                    constraintWidget2.setWidthWrapContent(childMeasureSpec == -2 ? USE_CONSTRAINTS_HELPER : false);
                                    if (i14 == -2) {
                                    }
                                    constraintWidget2.setHeightWrapContent(i14 == -2 ? USE_CONSTRAINTS_HELPER : false);
                                    childMeasureSpec = childAt2.getMeasuredWidth();
                                    i14 = childAt2.getMeasuredHeight();
                                    constraintWidget2.setWidth(childMeasureSpec);
                                    constraintWidget2.setHeight(i14);
                                    if (obj5 != null) {
                                        constraintWidget2.setWrapWidth(childMeasureSpec);
                                    }
                                    if (obj6 != null) {
                                        constraintWidget2.setWrapHeight(i14);
                                    }
                                    if (obj7 != null) {
                                        constraintWidget2.getResolutionWidth().remove();
                                    } else {
                                        constraintWidget2.getResolutionWidth().resolve(childMeasureSpec);
                                    }
                                    if (obj8 != null) {
                                        constraintWidget2.getResolutionHeight().remove();
                                    } else {
                                        constraintWidget2.getResolutionHeight().resolve(i14);
                                    }
                                    if (layoutParams3.needsBaseline) {
                                        i6 = childAt2.getBaseline();
                                        if (i6 != -1) {
                                            constraintWidget2.setBaselineDistance(i6);
                                        }
                                    }
                                    i6 = i11 + 1;
                                    i4 = i12;
                                    j = j2;
                                    childCount = i10;
                                    i8 = 8;
                                }
                                obj5 = null;
                                if (i14 != 0) {
                                    if (obj8 == null) {
                                    }
                                    i12 = i4;
                                    childMeasureSpec2 = getChildMeasureSpec(i2, i12, -2);
                                    obj8 = null;
                                    obj6 = 1;
                                    childAt2.measure(i3, childMeasureSpec2);
                                    if (constraintLayout.mMetrics != null) {
                                        metrics2 = constraintLayout.mMetrics;
                                        j2 = 1;
                                        metrics2.measures++;
                                    } else {
                                        j2 = 1;
                                    }
                                    if (childMeasureSpec == -2) {
                                    }
                                    constraintWidget2.setWidthWrapContent(childMeasureSpec == -2 ? USE_CONSTRAINTS_HELPER : false);
                                    if (i14 == -2) {
                                    }
                                    constraintWidget2.setHeightWrapContent(i14 == -2 ? USE_CONSTRAINTS_HELPER : false);
                                    childMeasureSpec = childAt2.getMeasuredWidth();
                                    i14 = childAt2.getMeasuredHeight();
                                    constraintWidget2.setWidth(childMeasureSpec);
                                    constraintWidget2.setHeight(i14);
                                    if (obj5 != null) {
                                        constraintWidget2.setWrapWidth(childMeasureSpec);
                                    }
                                    if (obj6 != null) {
                                        constraintWidget2.setWrapHeight(i14);
                                    }
                                    if (obj7 != null) {
                                        constraintWidget2.getResolutionWidth().resolve(childMeasureSpec);
                                    } else {
                                        constraintWidget2.getResolutionWidth().remove();
                                    }
                                    if (obj8 != null) {
                                        constraintWidget2.getResolutionHeight().resolve(i14);
                                    } else {
                                        constraintWidget2.getResolutionHeight().remove();
                                    }
                                    if (layoutParams3.needsBaseline) {
                                        i6 = childAt2.getBaseline();
                                        if (i6 != -1) {
                                            constraintWidget2.setBaselineDistance(i6);
                                        }
                                    }
                                    i6 = i11 + 1;
                                    i4 = i12;
                                    j = j2;
                                    childCount = i10;
                                    i8 = 8;
                                } else {
                                    i12 = i4;
                                    i13 = i2;
                                    if (i14 == -1) {
                                        childMeasureSpec2 = getChildMeasureSpec(i13, i12, -1);
                                    } else {
                                        if (i14 == -2) {
                                        }
                                        childMeasureSpec2 = getChildMeasureSpec(i13, i12, i14);
                                        childAt2.measure(i3, childMeasureSpec2);
                                        if (constraintLayout.mMetrics != null) {
                                            j2 = 1;
                                        } else {
                                            metrics2 = constraintLayout.mMetrics;
                                            j2 = 1;
                                            metrics2.measures++;
                                        }
                                        if (childMeasureSpec == -2) {
                                        }
                                        constraintWidget2.setWidthWrapContent(childMeasureSpec == -2 ? USE_CONSTRAINTS_HELPER : false);
                                        if (i14 == -2) {
                                        }
                                        constraintWidget2.setHeightWrapContent(i14 == -2 ? USE_CONSTRAINTS_HELPER : false);
                                        childMeasureSpec = childAt2.getMeasuredWidth();
                                        i14 = childAt2.getMeasuredHeight();
                                        constraintWidget2.setWidth(childMeasureSpec);
                                        constraintWidget2.setHeight(i14);
                                        if (obj5 != null) {
                                            constraintWidget2.setWrapWidth(childMeasureSpec);
                                        }
                                        if (obj6 != null) {
                                            constraintWidget2.setWrapHeight(i14);
                                        }
                                        if (obj7 != null) {
                                            constraintWidget2.getResolutionWidth().remove();
                                        } else {
                                            constraintWidget2.getResolutionWidth().resolve(childMeasureSpec);
                                        }
                                        if (obj8 != null) {
                                            constraintWidget2.getResolutionHeight().remove();
                                        } else {
                                            constraintWidget2.getResolutionHeight().resolve(i14);
                                        }
                                        if (layoutParams3.needsBaseline) {
                                            i6 = childAt2.getBaseline();
                                            if (i6 != -1) {
                                                constraintWidget2.setBaselineDistance(i6);
                                            }
                                        }
                                        i6 = i11 + 1;
                                        i4 = i12;
                                        j = j2;
                                        childCount = i10;
                                        i8 = 8;
                                    }
                                }
                                obj6 = null;
                                childAt2.measure(i3, childMeasureSpec2);
                                if (constraintLayout.mMetrics != null) {
                                    metrics2 = constraintLayout.mMetrics;
                                    j2 = 1;
                                    metrics2.measures++;
                                } else {
                                    j2 = 1;
                                }
                                if (childMeasureSpec == -2) {
                                }
                                constraintWidget2.setWidthWrapContent(childMeasureSpec == -2 ? USE_CONSTRAINTS_HELPER : false);
                                if (i14 == -2) {
                                }
                                constraintWidget2.setHeightWrapContent(i14 == -2 ? USE_CONSTRAINTS_HELPER : false);
                                childMeasureSpec = childAt2.getMeasuredWidth();
                                i14 = childAt2.getMeasuredHeight();
                                constraintWidget2.setWidth(childMeasureSpec);
                                constraintWidget2.setHeight(i14);
                                if (obj5 != null) {
                                    constraintWidget2.setWrapWidth(childMeasureSpec);
                                }
                                if (obj6 != null) {
                                    constraintWidget2.setWrapHeight(i14);
                                }
                                if (obj7 != null) {
                                    constraintWidget2.getResolutionWidth().resolve(childMeasureSpec);
                                } else {
                                    constraintWidget2.getResolutionWidth().remove();
                                }
                                if (obj8 != null) {
                                    constraintWidget2.getResolutionHeight().resolve(i14);
                                } else {
                                    constraintWidget2.getResolutionHeight().remove();
                                }
                                if (layoutParams3.needsBaseline) {
                                    i6 = childAt2.getBaseline();
                                    if (i6 != -1) {
                                        constraintWidget2.setBaselineDistance(i6);
                                    }
                                }
                                i6 = i11 + 1;
                                i4 = i12;
                                j = j2;
                                childCount = i10;
                                i8 = 8;
                            } else {
                                i11 = i6;
                                i10 = childCount;
                                i12 = i4;
                                i13 = i2;
                                j2 = 1;
                                i6 = i11 + 1;
                                i4 = i12;
                                j = j2;
                                childCount = i10;
                                i8 = 8;
                            }
                        }
                    }
                }
            }
            i11 = i6;
            i10 = childCount;
            j2 = j;
            i12 = i4;
            i13 = i2;
            i6 = i11 + 1;
            i4 = i12;
            j = j2;
            childCount = i10;
            i8 = 8;
        }
    }

    public void fillMetrics(Metrics metrics) {
        this.mMetrics = metrics;
        this.mLayoutWidget.fillMetrics(metrics);
    }

    protected void onMeasure(int i, int i2) {
        int height;
        Object obj;
        Object obj2;
        int i3;
        ConstraintWidget constraintWidget;
        int i4;
        View view;
        int i5;
        Object obj3;
        int i6;
        LayoutParams layoutParams;
        Object obj4;
        ConstraintWidget constraintWidget2;
        View view2;
        int i7 = i;
        int i8 = i2;
        System.currentTimeMillis();
        int mode = MeasureSpec.getMode(i);
        int size = MeasureSpec.getSize(i);
        int mode2 = MeasureSpec.getMode(i2);
        int size2 = MeasureSpec.getSize(i2);
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        this.mLayoutWidget.setX(paddingLeft);
        this.mLayoutWidget.setY(paddingTop);
        this.mLayoutWidget.setMaxWidth(this.mMaxWidth);
        this.mLayoutWidget.setMaxHeight(this.mMaxHeight);
        if (VERSION.SDK_INT >= 17) {
            r0.mLayoutWidget.setRtl(getLayoutDirection() == 1 ? USE_CONSTRAINTS_HELPER : false);
        }
        setSelfDimensionBehaviour(i, i2);
        int width = r0.mLayoutWidget.getWidth();
        int height2 = r0.mLayoutWidget.getHeight();
        Object obj5;
        if (r0.mDirtyHierarchy) {
            r0.mDirtyHierarchy = false;
            updateHierarchy();
            obj5 = 1;
        } else {
            obj5 = null;
        }
        Object obj6 = (r0.mOptimizationLevel & 8) == 8 ? 1 : null;
        if (obj6 != null) {
            r0.mLayoutWidget.preOptimize();
            r0.mLayoutWidget.optimizeForDimensions(width, height2);
            internalMeasureDimensions(i, i2);
        } else {
            internalMeasureChildren(i, i2);
        }
        updatePostMeasures();
        if (getChildCount() > 0 && r13 != null) {
            Analyzer.determineGroups(r0.mLayoutWidget);
        }
        if (r0.mLayoutWidget.mGroupsWrapOptimized) {
            if (r0.mLayoutWidget.mHorizontalWrapOptimized && mode == Integer.MIN_VALUE) {
                if (r0.mLayoutWidget.mWrapFixedWidth < size) {
                    r0.mLayoutWidget.setWidth(r0.mLayoutWidget.mWrapFixedWidth);
                }
                r0.mLayoutWidget.setHorizontalDimensionBehaviour(DimensionBehaviour.FIXED);
            }
            if (r0.mLayoutWidget.mVerticalWrapOptimized && mode2 == Integer.MIN_VALUE) {
                if (r0.mLayoutWidget.mWrapFixedHeight < size2) {
                    r0.mLayoutWidget.setHeight(r0.mLayoutWidget.mWrapFixedHeight);
                }
                r0.mLayoutWidget.setVerticalDimensionBehaviour(DimensionBehaviour.FIXED);
            }
        }
        if ((r0.mOptimizationLevel & 32) == 32) {
            int width2 = r0.mLayoutWidget.getWidth();
            height = r0.mLayoutWidget.getHeight();
            if (r0.mLastMeasureWidth != width2 && mode == 1073741824) {
                Analyzer.setPosition(r0.mLayoutWidget.mWidgetGroups, 0, width2);
            }
            if (r0.mLastMeasureHeight != height && mode2 == 1073741824) {
                Analyzer.setPosition(r0.mLayoutWidget.mWidgetGroups, 1, height);
            }
            if (r0.mLayoutWidget.mHorizontalWrapOptimized && r0.mLayoutWidget.mWrapFixedWidth > size) {
                Analyzer.setPosition(r0.mLayoutWidget.mWidgetGroups, 0, size);
            }
            if (r0.mLayoutWidget.mVerticalWrapOptimized && r0.mLayoutWidget.mWrapFixedHeight > size2) {
                Analyzer.setPosition(r0.mLayoutWidget.mWidgetGroups, 1, size2);
                if (getChildCount() > 0) {
                    solveLinearSystem("First pass");
                }
                mode = r0.mVariableDimensionsWidgets.size();
                paddingTop += getPaddingBottom();
                paddingLeft += getPaddingRight();
                if (mode <= 0) {
                    Object obj7 = r0.mLayoutWidget.getHorizontalDimensionBehaviour() != DimensionBehaviour.WRAP_CONTENT ? 1 : null;
                    obj = r0.mLayoutWidget.getVerticalDimensionBehaviour() != DimensionBehaviour.WRAP_CONTENT ? 1 : null;
                    height = Math.max(r0.mLayoutWidget.getWidth(), r0.mMinWidth);
                    mode2 = Math.max(r0.mLayoutWidget.getHeight(), r0.mMinHeight);
                    size = 0;
                    obj2 = null;
                    i3 = 0;
                    while (size < mode) {
                        constraintWidget = (ConstraintWidget) r0.mVariableDimensionsWidgets.get(size);
                        i4 = mode;
                        view = (View) constraintWidget.getCompanionWidget();
                        if (view != null) {
                            i5 = width;
                            obj3 = obj2;
                            i6 = height2;
                        } else {
                            i6 = height2;
                            layoutParams = (LayoutParams) view.getLayoutParams();
                            i5 = width;
                            if (!layoutParams.isHelper) {
                                if (layoutParams.isGuideline) {
                                    obj3 = obj2;
                                    if (view.getVisibility() != 8) {
                                        if (obj6 != null || !constraintWidget.getResolutionWidth().isResolved() || !constraintWidget.getResolutionHeight().isResolved()) {
                                            if (layoutParams.width == -2 || !layoutParams.horizontalDimensionFixed) {
                                                width = MeasureSpec.makeMeasureSpec(constraintWidget.getWidth(), 1073741824);
                                            } else {
                                                width = getChildMeasureSpec(i7, paddingLeft, layoutParams.width);
                                            }
                                            if (layoutParams.height == -2 || !layoutParams.verticalDimensionFixed) {
                                                i7 = MeasureSpec.makeMeasureSpec(constraintWidget.getHeight(), 1073741824);
                                            } else {
                                                i7 = getChildMeasureSpec(i8, paddingTop, layoutParams.height);
                                            }
                                            view.measure(width, i7);
                                            if (r0.mMetrics != null) {
                                                Metrics metrics = r0.mMetrics;
                                                metrics.additionalMeasures++;
                                            }
                                            i7 = view.getMeasuredWidth();
                                            width = view.getMeasuredHeight();
                                            if (i7 != constraintWidget.getWidth()) {
                                                constraintWidget.setWidth(i7);
                                                if (obj6 != null) {
                                                    constraintWidget.getResolutionWidth().resolve(i7);
                                                }
                                                if (obj7 != null && constraintWidget.getRight() > height) {
                                                    height = Math.max(height, constraintWidget.getRight() + constraintWidget.getAnchor(Type.RIGHT).getMargin());
                                                }
                                                obj3 = 1;
                                            }
                                            if (width != constraintWidget.getHeight()) {
                                                constraintWidget.setHeight(width);
                                                if (obj6 != null) {
                                                    constraintWidget.getResolutionHeight().resolve(width);
                                                }
                                                if (obj != null && constraintWidget.getBottom() > mode2) {
                                                    mode2 = Math.max(mode2, constraintWidget.getBottom() + constraintWidget.getAnchor(Type.BOTTOM).getMargin());
                                                }
                                                obj3 = 1;
                                            }
                                            if (layoutParams.needsBaseline) {
                                                i7 = view.getBaseline();
                                                if (!(i7 == -1 || i7 == constraintWidget.getBaselineDistance())) {
                                                    constraintWidget.setBaselineDistance(i7);
                                                    obj3 = 1;
                                                }
                                            }
                                            if (VERSION.SDK_INT < 11) {
                                                i3 = combineMeasuredStates(i3, view.getMeasuredState());
                                            } else {
                                                mode = i3;
                                            }
                                            obj2 = obj3;
                                            size++;
                                            mode = i4;
                                            height2 = i6;
                                            width = i5;
                                            i7 = i;
                                        }
                                    }
                                }
                            }
                            obj3 = obj2;
                        }
                        i3 = i3;
                        obj2 = obj3;
                        size++;
                        mode = i4;
                        height2 = i6;
                        width = i5;
                        i7 = i;
                    }
                    i4 = mode;
                    i5 = width;
                    i6 = height2;
                    mode = i3;
                    if (obj2 != null) {
                        r0.mLayoutWidget.setWidth(i5);
                        r0.mLayoutWidget.setHeight(i6);
                        if (obj6 != null) {
                            r0.mLayoutWidget.solveGraph();
                        }
                        solveLinearSystem("2nd pass");
                        if (r0.mLayoutWidget.getWidth() >= height) {
                            r0.mLayoutWidget.setWidth(height);
                            obj = 1;
                        } else {
                            obj = null;
                        }
                        if (r0.mLayoutWidget.getHeight() >= mode2) {
                            r0.mLayoutWidget.setHeight(mode2);
                            obj4 = 1;
                        } else {
                            obj4 = obj;
                        }
                        if (obj4 != null) {
                            solveLinearSystem("3rd pass");
                        }
                    }
                    i7 = i4;
                    for (size = 0; size < i7; size++) {
                        constraintWidget2 = (ConstraintWidget) r0.mVariableDimensionsWidgets.get(size);
                        view2 = (View) constraintWidget2.getCompanionWidget();
                        if (view2 != null && (view2.getMeasuredWidth() != constraintWidget2.getWidth() || view2.getMeasuredHeight() != constraintWidget2.getHeight())) {
                            if (constraintWidget2.getVisibility() != 8) {
                                view2.measure(MeasureSpec.makeMeasureSpec(constraintWidget2.getWidth(), 1073741824), MeasureSpec.makeMeasureSpec(constraintWidget2.getHeight(), 1073741824));
                                if (r0.mMetrics != null) {
                                    Metrics metrics2 = r0.mMetrics;
                                    metrics2.additionalMeasures++;
                                }
                            }
                        }
                    }
                } else {
                    mode = 0;
                }
                i7 = r0.mLayoutWidget.getWidth() + paddingLeft;
                size = r0.mLayoutWidget.getHeight() + paddingTop;
                if (VERSION.SDK_INT < 11) {
                    i8 = resolveSizeAndState(size, i8, mode << 16) & ViewCompat.MEASURED_SIZE_MASK;
                    i7 = Math.min(r0.mMaxWidth, resolveSizeAndState(i7, i, mode) & ViewCompat.MEASURED_SIZE_MASK);
                    i8 = Math.min(r0.mMaxHeight, i8);
                    if (r0.mLayoutWidget.isWidthMeasuredTooSmall()) {
                        i7 |= 16777216;
                    }
                    if (r0.mLayoutWidget.isHeightMeasuredTooSmall()) {
                        i8 |= 16777216;
                    }
                    setMeasuredDimension(i7, i8);
                    r0.mLastMeasureWidth = i7;
                    r0.mLastMeasureHeight = i8;
                }
                setMeasuredDimension(i7, size);
                r0.mLastMeasureWidth = i7;
                r0.mLastMeasureHeight = size;
                return;
            }
        }
        if (getChildCount() > 0) {
            solveLinearSystem("First pass");
        }
        mode = r0.mVariableDimensionsWidgets.size();
        paddingTop += getPaddingBottom();
        paddingLeft += getPaddingRight();
        if (mode <= 0) {
            mode = 0;
        } else {
            if (r0.mLayoutWidget.getHorizontalDimensionBehaviour() != DimensionBehaviour.WRAP_CONTENT) {
            }
            if (r0.mLayoutWidget.getVerticalDimensionBehaviour() != DimensionBehaviour.WRAP_CONTENT) {
            }
            height = Math.max(r0.mLayoutWidget.getWidth(), r0.mMinWidth);
            mode2 = Math.max(r0.mLayoutWidget.getHeight(), r0.mMinHeight);
            size = 0;
            obj2 = null;
            i3 = 0;
            while (size < mode) {
                constraintWidget = (ConstraintWidget) r0.mVariableDimensionsWidgets.get(size);
                i4 = mode;
                view = (View) constraintWidget.getCompanionWidget();
                if (view != null) {
                    i6 = height2;
                    layoutParams = (LayoutParams) view.getLayoutParams();
                    i5 = width;
                    if (layoutParams.isHelper) {
                        if (layoutParams.isGuideline) {
                            obj3 = obj2;
                            if (view.getVisibility() != 8) {
                                if (obj6 != null) {
                                }
                                if (layoutParams.width == -2) {
                                }
                                width = MeasureSpec.makeMeasureSpec(constraintWidget.getWidth(), 1073741824);
                                if (layoutParams.height == -2) {
                                }
                                i7 = MeasureSpec.makeMeasureSpec(constraintWidget.getHeight(), 1073741824);
                                view.measure(width, i7);
                                if (r0.mMetrics != null) {
                                    Metrics metrics3 = r0.mMetrics;
                                    metrics3.additionalMeasures++;
                                }
                                i7 = view.getMeasuredWidth();
                                width = view.getMeasuredHeight();
                                if (i7 != constraintWidget.getWidth()) {
                                    constraintWidget.setWidth(i7);
                                    if (obj6 != null) {
                                        constraintWidget.getResolutionWidth().resolve(i7);
                                    }
                                    height = Math.max(height, constraintWidget.getRight() + constraintWidget.getAnchor(Type.RIGHT).getMargin());
                                    obj3 = 1;
                                }
                                if (width != constraintWidget.getHeight()) {
                                    constraintWidget.setHeight(width);
                                    if (obj6 != null) {
                                        constraintWidget.getResolutionHeight().resolve(width);
                                    }
                                    mode2 = Math.max(mode2, constraintWidget.getBottom() + constraintWidget.getAnchor(Type.BOTTOM).getMargin());
                                    obj3 = 1;
                                }
                                if (layoutParams.needsBaseline) {
                                    i7 = view.getBaseline();
                                    constraintWidget.setBaselineDistance(i7);
                                    obj3 = 1;
                                }
                                if (VERSION.SDK_INT < 11) {
                                    mode = i3;
                                } else {
                                    i3 = combineMeasuredStates(i3, view.getMeasuredState());
                                }
                                obj2 = obj3;
                                size++;
                                mode = i4;
                                height2 = i6;
                                width = i5;
                                i7 = i;
                            }
                        }
                    }
                    obj3 = obj2;
                } else {
                    i5 = width;
                    obj3 = obj2;
                    i6 = height2;
                }
                i3 = i3;
                obj2 = obj3;
                size++;
                mode = i4;
                height2 = i6;
                width = i5;
                i7 = i;
            }
            i4 = mode;
            i5 = width;
            i6 = height2;
            mode = i3;
            if (obj2 != null) {
                r0.mLayoutWidget.setWidth(i5);
                r0.mLayoutWidget.setHeight(i6);
                if (obj6 != null) {
                    r0.mLayoutWidget.solveGraph();
                }
                solveLinearSystem("2nd pass");
                if (r0.mLayoutWidget.getWidth() >= height) {
                    obj = null;
                } else {
                    r0.mLayoutWidget.setWidth(height);
                    obj = 1;
                }
                if (r0.mLayoutWidget.getHeight() >= mode2) {
                    obj4 = obj;
                } else {
                    r0.mLayoutWidget.setHeight(mode2);
                    obj4 = 1;
                }
                if (obj4 != null) {
                    solveLinearSystem("3rd pass");
                }
            }
            i7 = i4;
            for (size = 0; size < i7; size++) {
                constraintWidget2 = (ConstraintWidget) r0.mVariableDimensionsWidgets.get(size);
                view2 = (View) constraintWidget2.getCompanionWidget();
                if (view2 != null) {
                    if (constraintWidget2.getVisibility() != 8) {
                        view2.measure(MeasureSpec.makeMeasureSpec(constraintWidget2.getWidth(), 1073741824), MeasureSpec.makeMeasureSpec(constraintWidget2.getHeight(), 1073741824));
                        if (r0.mMetrics != null) {
                            Metrics metrics22 = r0.mMetrics;
                            metrics22.additionalMeasures++;
                        }
                    }
                }
            }
        }
        i7 = r0.mLayoutWidget.getWidth() + paddingLeft;
        size = r0.mLayoutWidget.getHeight() + paddingTop;
        if (VERSION.SDK_INT < 11) {
            setMeasuredDimension(i7, size);
            r0.mLastMeasureWidth = i7;
            r0.mLastMeasureHeight = size;
            return;
        }
        i8 = resolveSizeAndState(size, i8, mode << 16) & ViewCompat.MEASURED_SIZE_MASK;
        i7 = Math.min(r0.mMaxWidth, resolveSizeAndState(i7, i, mode) & ViewCompat.MEASURED_SIZE_MASK);
        i8 = Math.min(r0.mMaxHeight, i8);
        if (r0.mLayoutWidget.isWidthMeasuredTooSmall()) {
            i7 |= 16777216;
        }
        if (r0.mLayoutWidget.isHeightMeasuredTooSmall()) {
            i8 |= 16777216;
        }
        setMeasuredDimension(i7, i8);
        r0.mLastMeasureWidth = i7;
        r0.mLastMeasureHeight = i8;
    }

    private void setSelfDimensionBehaviour(int i, int i2) {
        int mode = MeasureSpec.getMode(i);
        i = MeasureSpec.getSize(i);
        int mode2 = MeasureSpec.getMode(i2);
        i2 = MeasureSpec.getSize(i2);
        int paddingTop = getPaddingTop() + getPaddingBottom();
        int paddingLeft = getPaddingLeft() + getPaddingRight();
        DimensionBehaviour dimensionBehaviour = DimensionBehaviour.FIXED;
        DimensionBehaviour dimensionBehaviour2 = DimensionBehaviour.FIXED;
        getLayoutParams();
        if (mode != Integer.MIN_VALUE) {
            if (mode == 0) {
                dimensionBehaviour = DimensionBehaviour.WRAP_CONTENT;
            } else if (mode == 1073741824) {
                i = Math.min(this.mMaxWidth, i) - paddingLeft;
            }
            i = 0;
        } else {
            dimensionBehaviour = DimensionBehaviour.WRAP_CONTENT;
        }
        if (mode2 != Integer.MIN_VALUE) {
            if (mode2 == 0) {
                dimensionBehaviour2 = DimensionBehaviour.WRAP_CONTENT;
            } else if (mode2 == 1073741824) {
                i2 = Math.min(this.mMaxHeight, i2) - paddingTop;
            }
            i2 = 0;
        } else {
            dimensionBehaviour2 = DimensionBehaviour.WRAP_CONTENT;
        }
        this.mLayoutWidget.setMinWidth(0);
        this.mLayoutWidget.setMinHeight(0);
        this.mLayoutWidget.setHorizontalDimensionBehaviour(dimensionBehaviour);
        this.mLayoutWidget.setWidth(i);
        this.mLayoutWidget.setVerticalDimensionBehaviour(dimensionBehaviour2);
        this.mLayoutWidget.setHeight(i2);
        this.mLayoutWidget.setMinWidth((this.mMinWidth - getPaddingLeft()) - getPaddingRight());
        this.mLayoutWidget.setMinHeight((this.mMinHeight - getPaddingTop()) - getPaddingBottom());
    }

    protected void solveLinearSystem(String str) {
        this.mLayoutWidget.layout();
        if (this.mMetrics != null) {
            str = this.mMetrics;
            str.resolutions++;
        }
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        z = getChildCount();
        i = isInEditMode();
        boolean z2 = false;
        for (boolean z3 = false; z3 < z; z3++) {
            i4 = getChildAt(z3);
            LayoutParams layoutParams = (LayoutParams) i4.getLayoutParams();
            ConstraintWidget constraintWidget = layoutParams.widget;
            if (i4.getVisibility() != 8 || layoutParams.isGuideline || layoutParams.isHelper || i != 0) {
                if (!layoutParams.isInPlaceholder) {
                    int drawX = constraintWidget.getDrawX();
                    int drawY = constraintWidget.getDrawY();
                    int width = constraintWidget.getWidth() + drawX;
                    int height = constraintWidget.getHeight() + drawY;
                    i4.layout(drawX, drawY, width, height);
                    if (i4 instanceof Placeholder) {
                        i4 = ((Placeholder) i4).getContent();
                        if (i4 != 0) {
                            i4.setVisibility(0);
                            i4.layout(drawX, drawY, width, height);
                        }
                    }
                }
            }
        }
        z = this.mConstraintHelpers.size();
        if (z <= false) {
            while (z2 < z) {
                ((ConstraintHelper) this.mConstraintHelpers.get(z2)).updatePostLayout(this);
                z2++;
            }
        }
    }

    public void setOptimizationLevel(int i) {
        this.mLayoutWidget.setOptimizationLevel(i);
    }

    public int getOptimizationLevel() {
        return this.mLayoutWidget.getOptimizationLevel();
    }

    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    protected LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-2, -2);
    }

    protected android.view.ViewGroup.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        return new LayoutParams(layoutParams);
    }

    protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    public void setConstraintSet(ConstraintSet constraintSet) {
        this.mConstraintSet = constraintSet;
    }

    public View getViewById(int i) {
        return (View) this.mChildrenByIds.get(i);
    }

    public void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (isInEditMode()) {
            int childCount = getChildCount();
            float width = (float) getWidth();
            float height = (float) getHeight();
            for (int i = 0; i < childCount; i++) {
                View childAt = getChildAt(i);
                if (childAt.getVisibility() != 8) {
                    Object tag = childAt.getTag();
                    if (tag != null && (tag instanceof String)) {
                        String[] split = ((String) tag).split(",");
                        if (split.length == 4) {
                            int parseInt = Integer.parseInt(split[0]);
                            int parseInt2 = Integer.parseInt(split[1]);
                            parseInt = (int) ((((float) parseInt) / 1080.0f) * width);
                            parseInt2 = (int) ((((float) parseInt2) / 1920.0f) * height);
                            int parseInt3 = (int) ((((float) Integer.parseInt(split[2])) / 1080.0f) * width);
                            int parseInt4 = (int) ((((float) Integer.parseInt(split[3])) / 1920.0f) * height);
                            Paint paint = new Paint();
                            paint.setColor(SupportMenu.CATEGORY_MASK);
                            float f = (float) parseInt;
                            float f2 = (float) (parseInt + parseInt3);
                            Canvas canvas2 = canvas;
                            float f3 = (float) parseInt2;
                            float f4 = f;
                            float f5 = f;
                            f = f3;
                            Paint paint2 = paint;
                            float f6 = f2;
                            Paint paint3 = paint2;
                            canvas2.drawLine(f4, f, f6, f3, paint3);
                            float f7 = (float) (parseInt2 + parseInt4);
                            f4 = f2;
                            float f8 = f7;
                            canvas2.drawLine(f4, f, f6, f8, paint3);
                            f = f7;
                            f6 = f5;
                            canvas2.drawLine(f4, f, f6, f8, paint3);
                            f4 = f5;
                            canvas2.drawLine(f4, f, f6, f3, paint3);
                            paint = paint2;
                            paint.setColor(-16711936);
                            Paint paint4 = paint;
                            f6 = f2;
                            paint3 = paint4;
                            canvas2.drawLine(f4, f3, f6, f7, paint3);
                            canvas2.drawLine(f4, f7, f6, f3, paint3);
                        }
                    }
                }
            }
        }
        ConstraintLayout constraintLayout = this;
    }

    public void requestLayout() {
        super.requestLayout();
        this.mDirtyHierarchy = USE_CONSTRAINTS_HELPER;
        this.mLastMeasureWidth = -1;
        this.mLastMeasureHeight = -1;
        this.mLastMeasureWidthSize = -1;
        this.mLastMeasureHeightSize = -1;
        this.mLastMeasureWidthMode = 0;
        this.mLastMeasureHeightMode = 0;
    }
}
