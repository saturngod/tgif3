package android.support.constraint.solver.widgets;

import android.support.constraint.solver.Cache;
import android.support.constraint.solver.LinearSystem;
import android.support.constraint.solver.SolverVariable;
import android.support.constraint.solver.widgets.ConstraintAnchor.Strength;
import android.support.constraint.solver.widgets.ConstraintAnchor.Type;
import java.util.ArrayList;

public class ConstraintWidget {
    protected static final int ANCHOR_BASELINE = 4;
    protected static final int ANCHOR_BOTTOM = 3;
    protected static final int ANCHOR_LEFT = 0;
    protected static final int ANCHOR_RIGHT = 1;
    protected static final int ANCHOR_TOP = 2;
    private static final boolean AUTOTAG_CENTER = false;
    public static final int CHAIN_PACKED = 2;
    public static final int CHAIN_SPREAD = 0;
    public static final int CHAIN_SPREAD_INSIDE = 1;
    public static float DEFAULT_BIAS = 0.5f;
    static final int DIMENSION_HORIZONTAL = 0;
    static final int DIMENSION_VERTICAL = 1;
    protected static final int DIRECT = 2;
    public static final int GONE = 8;
    public static final int HORIZONTAL = 0;
    public static final int INVISIBLE = 4;
    public static final int MATCH_CONSTRAINT_PERCENT = 2;
    public static final int MATCH_CONSTRAINT_RATIO = 3;
    public static final int MATCH_CONSTRAINT_RATIO_RESOLVED = 4;
    public static final int MATCH_CONSTRAINT_SPREAD = 0;
    public static final int MATCH_CONSTRAINT_WRAP = 1;
    protected static final int SOLVER = 1;
    public static final int UNKNOWN = -1;
    public static final int VERTICAL = 1;
    public static final int VISIBLE = 0;
    private static final int WRAP = -2;
    protected ArrayList<ConstraintAnchor> mAnchors;
    ConstraintAnchor mBaseline;
    int mBaselineDistance;
    ConstraintWidgetGroup mBelongingGroup;
    ConstraintAnchor mBottom;
    boolean mBottomHasCentered;
    ConstraintAnchor mCenter;
    ConstraintAnchor mCenterX;
    ConstraintAnchor mCenterY;
    private float mCircleConstraintAngle;
    private Object mCompanionWidget;
    private int mContainerItemSkip;
    private String mDebugName;
    protected float mDimensionRatio;
    protected int mDimensionRatioSide;
    int mDistToBottom;
    int mDistToLeft;
    int mDistToRight;
    int mDistToTop;
    private int mDrawHeight;
    private int mDrawWidth;
    private int mDrawX;
    private int mDrawY;
    boolean mGroupsToSolver;
    int mHeight;
    float mHorizontalBiasPercent;
    boolean mHorizontalChainFixedPosition;
    int mHorizontalChainStyle;
    ConstraintWidget mHorizontalNextWidget;
    public int mHorizontalResolution;
    boolean mHorizontalWrapVisited;
    boolean mIsHeightWrapContent;
    boolean mIsWidthWrapContent;
    ConstraintAnchor mLeft;
    boolean mLeftHasCentered;
    protected ConstraintAnchor[] mListAnchors;
    protected DimensionBehaviour[] mListDimensionBehaviors;
    protected ConstraintWidget[] mListNextMatchConstraintsWidget;
    int mMatchConstraintDefaultHeight;
    int mMatchConstraintDefaultWidth;
    int mMatchConstraintMaxHeight;
    int mMatchConstraintMaxWidth;
    int mMatchConstraintMinHeight;
    int mMatchConstraintMinWidth;
    float mMatchConstraintPercentHeight;
    float mMatchConstraintPercentWidth;
    private int[] mMaxDimension;
    protected int mMinHeight;
    protected int mMinWidth;
    protected ConstraintWidget[] mNextChainWidget;
    protected int mOffsetX;
    protected int mOffsetY;
    boolean mOptimizerMeasurable;
    boolean mOptimizerMeasured;
    ConstraintWidget mParent;
    int mRelX;
    int mRelY;
    ResolutionDimension mResolutionHeight;
    ResolutionDimension mResolutionWidth;
    float mResolvedDimensionRatio;
    int mResolvedDimensionRatioSide;
    int[] mResolvedMatchConstraintDefault;
    ConstraintAnchor mRight;
    boolean mRightHasCentered;
    ConstraintAnchor mTop;
    boolean mTopHasCentered;
    private String mType;
    float mVerticalBiasPercent;
    boolean mVerticalChainFixedPosition;
    int mVerticalChainStyle;
    ConstraintWidget mVerticalNextWidget;
    public int mVerticalResolution;
    boolean mVerticalWrapVisited;
    private int mVisibility;
    float[] mWeight;
    int mWidth;
    private int mWrapHeight;
    private int mWrapWidth;
    protected int mX;
    protected int mY;

    public enum ContentAlignment {
        BEGIN,
        MIDDLE,
        END,
        TOP,
        VERTICAL_MIDDLE,
        BOTTOM,
        LEFT,
        RIGHT
    }

    public enum DimensionBehaviour {
        FIXED,
        WRAP_CONTENT,
        MATCH_CONSTRAINT,
        MATCH_PARENT
    }

    public void connectedTo(ConstraintWidget constraintWidget) {
    }

    public void resolve() {
    }

    public int getMaxHeight() {
        return this.mMaxDimension[1];
    }

    public int getMaxWidth() {
        return this.mMaxDimension[0];
    }

    public void setMaxWidth(int i) {
        this.mMaxDimension[0] = i;
    }

    public void setMaxHeight(int i) {
        this.mMaxDimension[1] = i;
    }

    public boolean isSpreadWidth() {
        return this.mMatchConstraintDefaultWidth == 0 && this.mDimensionRatio == 0.0f && this.mMatchConstraintMinWidth == 0 && this.mMatchConstraintMaxWidth == 0 && this.mListDimensionBehaviors[0] == DimensionBehaviour.MATCH_CONSTRAINT;
    }

    public boolean isSpreadHeight() {
        return this.mMatchConstraintDefaultHeight == 0 && this.mDimensionRatio == 0.0f && this.mMatchConstraintMinHeight == 0 && this.mMatchConstraintMaxHeight == 0 && this.mListDimensionBehaviors[1] == DimensionBehaviour.MATCH_CONSTRAINT;
    }

    public void reset() {
        this.mLeft.reset();
        this.mTop.reset();
        this.mRight.reset();
        this.mBottom.reset();
        this.mBaseline.reset();
        this.mCenterX.reset();
        this.mCenterY.reset();
        this.mCenter.reset();
        this.mParent = null;
        this.mCircleConstraintAngle = 0.0f;
        this.mWidth = 0;
        this.mHeight = 0;
        this.mDimensionRatio = 0.0f;
        this.mDimensionRatioSide = -1;
        this.mX = 0;
        this.mY = 0;
        this.mDrawX = 0;
        this.mDrawY = 0;
        this.mDrawWidth = 0;
        this.mDrawHeight = 0;
        this.mOffsetX = 0;
        this.mOffsetY = 0;
        this.mBaselineDistance = 0;
        this.mMinWidth = 0;
        this.mMinHeight = 0;
        this.mWrapWidth = 0;
        this.mWrapHeight = 0;
        this.mHorizontalBiasPercent = DEFAULT_BIAS;
        this.mVerticalBiasPercent = DEFAULT_BIAS;
        this.mListDimensionBehaviors[0] = DimensionBehaviour.FIXED;
        this.mListDimensionBehaviors[1] = DimensionBehaviour.FIXED;
        this.mCompanionWidget = null;
        this.mContainerItemSkip = 0;
        this.mVisibility = 0;
        this.mType = null;
        this.mHorizontalWrapVisited = false;
        this.mVerticalWrapVisited = false;
        this.mHorizontalChainStyle = 0;
        this.mVerticalChainStyle = 0;
        this.mHorizontalChainFixedPosition = false;
        this.mVerticalChainFixedPosition = false;
        this.mWeight[0] = -1.0f;
        this.mWeight[1] = -1.0f;
        this.mHorizontalResolution = -1;
        this.mVerticalResolution = -1;
        this.mMaxDimension[0] = Integer.MAX_VALUE;
        this.mMaxDimension[1] = Integer.MAX_VALUE;
        this.mMatchConstraintDefaultWidth = 0;
        this.mMatchConstraintDefaultHeight = 0;
        this.mMatchConstraintPercentWidth = 1.0f;
        this.mMatchConstraintPercentHeight = 1.0f;
        this.mMatchConstraintMaxWidth = Integer.MAX_VALUE;
        this.mMatchConstraintMaxHeight = Integer.MAX_VALUE;
        this.mMatchConstraintMinWidth = 0;
        this.mMatchConstraintMinHeight = 0;
        this.mResolvedDimensionRatioSide = -1;
        this.mResolvedDimensionRatio = 1.0f;
        if (this.mResolutionWidth != null) {
            this.mResolutionWidth.reset();
        }
        if (this.mResolutionHeight != null) {
            this.mResolutionHeight.reset();
        }
        this.mBelongingGroup = null;
        this.mOptimizerMeasurable = false;
        this.mOptimizerMeasured = false;
        this.mGroupsToSolver = false;
    }

    public void resetResolutionNodes() {
        for (int i = 0; i < 6; i++) {
            this.mListAnchors[i].getResolutionNode().reset();
        }
    }

    public void updateResolutionNodes() {
        for (int i = 0; i < 6; i++) {
            this.mListAnchors[i].getResolutionNode().update();
        }
    }

    public void analyze(int i) {
        Optimizer.analyze(i, this);
    }

    public boolean isFullyResolved() {
        if (this.mLeft.getResolutionNode().state == 1 && this.mRight.getResolutionNode().state == 1 && this.mTop.getResolutionNode().state == 1 && this.mBottom.getResolutionNode().state == 1) {
            return true;
        }
        return false;
    }

    public ResolutionDimension getResolutionWidth() {
        if (this.mResolutionWidth == null) {
            this.mResolutionWidth = new ResolutionDimension();
        }
        return this.mResolutionWidth;
    }

    public ResolutionDimension getResolutionHeight() {
        if (this.mResolutionHeight == null) {
            this.mResolutionHeight = new ResolutionDimension();
        }
        return this.mResolutionHeight;
    }

    public ConstraintWidget() {
        this.mHorizontalResolution = -1;
        this.mVerticalResolution = -1;
        this.mMatchConstraintDefaultWidth = 0;
        this.mMatchConstraintDefaultHeight = 0;
        this.mResolvedMatchConstraintDefault = new int[2];
        this.mMatchConstraintMinWidth = 0;
        this.mMatchConstraintMaxWidth = 0;
        this.mMatchConstraintPercentWidth = 1.0f;
        this.mMatchConstraintMinHeight = 0;
        this.mMatchConstraintMaxHeight = 0;
        this.mMatchConstraintPercentHeight = 1.0f;
        this.mResolvedDimensionRatioSide = -1;
        this.mResolvedDimensionRatio = 1.0f;
        this.mBelongingGroup = null;
        this.mMaxDimension = new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE};
        this.mCircleConstraintAngle = 0.0f;
        this.mLeft = new ConstraintAnchor(this, Type.LEFT);
        this.mTop = new ConstraintAnchor(this, Type.TOP);
        this.mRight = new ConstraintAnchor(this, Type.RIGHT);
        this.mBottom = new ConstraintAnchor(this, Type.BOTTOM);
        this.mBaseline = new ConstraintAnchor(this, Type.BASELINE);
        this.mCenterX = new ConstraintAnchor(this, Type.CENTER_X);
        this.mCenterY = new ConstraintAnchor(this, Type.CENTER_Y);
        this.mCenter = new ConstraintAnchor(this, Type.CENTER);
        this.mListAnchors = new ConstraintAnchor[]{this.mLeft, this.mRight, this.mTop, this.mBottom, this.mBaseline, this.mCenter};
        this.mAnchors = new ArrayList();
        this.mListDimensionBehaviors = new DimensionBehaviour[]{DimensionBehaviour.FIXED, DimensionBehaviour.FIXED};
        this.mParent = null;
        this.mWidth = 0;
        this.mHeight = 0;
        this.mDimensionRatio = 0.0f;
        this.mDimensionRatioSide = -1;
        this.mX = 0;
        this.mY = 0;
        this.mRelX = 0;
        this.mRelY = 0;
        this.mDrawX = 0;
        this.mDrawY = 0;
        this.mDrawWidth = 0;
        this.mDrawHeight = 0;
        this.mOffsetX = 0;
        this.mOffsetY = 0;
        this.mBaselineDistance = 0;
        this.mHorizontalBiasPercent = DEFAULT_BIAS;
        this.mVerticalBiasPercent = DEFAULT_BIAS;
        this.mContainerItemSkip = 0;
        this.mVisibility = 0;
        this.mDebugName = null;
        this.mType = null;
        this.mOptimizerMeasurable = false;
        this.mOptimizerMeasured = false;
        this.mGroupsToSolver = false;
        this.mHorizontalChainStyle = 0;
        this.mVerticalChainStyle = 0;
        this.mWeight = new float[]{-1.0f, -1.0f};
        this.mListNextMatchConstraintsWidget = new ConstraintWidget[]{null, null};
        this.mNextChainWidget = new ConstraintWidget[]{null, null};
        this.mHorizontalNextWidget = null;
        this.mVerticalNextWidget = null;
        addAnchors();
    }

    public ConstraintWidget(int i, int i2, int i3, int i4) {
        this.mHorizontalResolution = -1;
        this.mVerticalResolution = -1;
        this.mMatchConstraintDefaultWidth = 0;
        this.mMatchConstraintDefaultHeight = 0;
        this.mResolvedMatchConstraintDefault = new int[2];
        this.mMatchConstraintMinWidth = 0;
        this.mMatchConstraintMaxWidth = 0;
        this.mMatchConstraintPercentWidth = 1.0f;
        this.mMatchConstraintMinHeight = 0;
        this.mMatchConstraintMaxHeight = 0;
        this.mMatchConstraintPercentHeight = 1.0f;
        this.mResolvedDimensionRatioSide = -1;
        this.mResolvedDimensionRatio = 1.0f;
        this.mBelongingGroup = null;
        this.mMaxDimension = new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE};
        this.mCircleConstraintAngle = 0.0f;
        this.mLeft = new ConstraintAnchor(this, Type.LEFT);
        this.mTop = new ConstraintAnchor(this, Type.TOP);
        this.mRight = new ConstraintAnchor(this, Type.RIGHT);
        this.mBottom = new ConstraintAnchor(this, Type.BOTTOM);
        this.mBaseline = new ConstraintAnchor(this, Type.BASELINE);
        this.mCenterX = new ConstraintAnchor(this, Type.CENTER_X);
        this.mCenterY = new ConstraintAnchor(this, Type.CENTER_Y);
        this.mCenter = new ConstraintAnchor(this, Type.CENTER);
        this.mListAnchors = new ConstraintAnchor[]{this.mLeft, this.mRight, this.mTop, this.mBottom, this.mBaseline, this.mCenter};
        this.mAnchors = new ArrayList();
        this.mListDimensionBehaviors = new DimensionBehaviour[]{DimensionBehaviour.FIXED, DimensionBehaviour.FIXED};
        this.mParent = null;
        this.mWidth = 0;
        this.mHeight = 0;
        this.mDimensionRatio = 0.0f;
        this.mDimensionRatioSide = -1;
        this.mX = 0;
        this.mY = 0;
        this.mRelX = 0;
        this.mRelY = 0;
        this.mDrawX = 0;
        this.mDrawY = 0;
        this.mDrawWidth = 0;
        this.mDrawHeight = 0;
        this.mOffsetX = 0;
        this.mOffsetY = 0;
        this.mBaselineDistance = 0;
        this.mHorizontalBiasPercent = DEFAULT_BIAS;
        this.mVerticalBiasPercent = DEFAULT_BIAS;
        this.mContainerItemSkip = 0;
        this.mVisibility = 0;
        this.mDebugName = null;
        this.mType = null;
        this.mOptimizerMeasurable = false;
        this.mOptimizerMeasured = false;
        this.mGroupsToSolver = false;
        this.mHorizontalChainStyle = 0;
        this.mVerticalChainStyle = 0;
        this.mWeight = new float[]{-1.0f, -1.0f};
        this.mListNextMatchConstraintsWidget = new ConstraintWidget[]{null, null};
        this.mNextChainWidget = new ConstraintWidget[]{null, null};
        this.mHorizontalNextWidget = null;
        this.mVerticalNextWidget = null;
        this.mX = i;
        this.mY = i2;
        this.mWidth = i3;
        this.mHeight = i4;
        addAnchors();
        forceUpdateDrawPosition();
    }

    public ConstraintWidget(int i, int i2) {
        this(0, 0, i, i2);
    }

    public void resetSolverVariables(Cache cache) {
        this.mLeft.resetSolverVariable(cache);
        this.mTop.resetSolverVariable(cache);
        this.mRight.resetSolverVariable(cache);
        this.mBottom.resetSolverVariable(cache);
        this.mBaseline.resetSolverVariable(cache);
        this.mCenter.resetSolverVariable(cache);
        this.mCenterX.resetSolverVariable(cache);
        this.mCenterY.resetSolverVariable(cache);
    }

    private void addAnchors() {
        this.mAnchors.add(this.mLeft);
        this.mAnchors.add(this.mTop);
        this.mAnchors.add(this.mRight);
        this.mAnchors.add(this.mBottom);
        this.mAnchors.add(this.mCenterX);
        this.mAnchors.add(this.mCenterY);
        this.mAnchors.add(this.mCenter);
        this.mAnchors.add(this.mBaseline);
    }

    public boolean isRoot() {
        return this.mParent == null;
    }

    public boolean isRootContainer() {
        return (this instanceof ConstraintWidgetContainer) && (this.mParent == null || !(this.mParent instanceof ConstraintWidgetContainer));
    }

    public boolean isInsideConstraintLayout() {
        ConstraintWidget parent = getParent();
        if (parent == null) {
            return false;
        }
        while (parent != null) {
            if (parent instanceof ConstraintWidgetContainer) {
                return true;
            }
            parent = parent.getParent();
        }
        return false;
    }

    public boolean hasAncestor(ConstraintWidget constraintWidget) {
        ConstraintWidget parent = getParent();
        if (parent == constraintWidget) {
            return true;
        }
        if (parent == constraintWidget.getParent()) {
            return false;
        }
        while (parent != null) {
            if (parent == constraintWidget || parent == constraintWidget.getParent()) {
                return true;
            }
            parent = parent.getParent();
        }
        return false;
    }

    public WidgetContainer getRootWidgetContainer() {
        ConstraintWidget constraintWidget = this;
        while (constraintWidget.getParent() != null) {
            constraintWidget = constraintWidget.getParent();
        }
        return constraintWidget instanceof WidgetContainer ? (WidgetContainer) constraintWidget : null;
    }

    public ConstraintWidget getParent() {
        return this.mParent;
    }

    public void setParent(ConstraintWidget constraintWidget) {
        this.mParent = constraintWidget;
    }

    public void setWidthWrapContent(boolean z) {
        this.mIsWidthWrapContent = z;
    }

    public boolean isWidthWrapContent() {
        return this.mIsWidthWrapContent;
    }

    public void setHeightWrapContent(boolean z) {
        this.mIsHeightWrapContent = z;
    }

    public boolean isHeightWrapContent() {
        return this.mIsHeightWrapContent;
    }

    public void connectCircularConstraint(ConstraintWidget constraintWidget, float f, int i) {
        immediateConnect(Type.CENTER, constraintWidget, Type.CENTER, i, 0);
        this.mCircleConstraintAngle = f;
    }

    public String getType() {
        return this.mType;
    }

    public void setType(String str) {
        this.mType = str;
    }

    public void setVisibility(int i) {
        this.mVisibility = i;
    }

    public int getVisibility() {
        return this.mVisibility;
    }

    public String getDebugName() {
        return this.mDebugName;
    }

    public void setDebugName(String str) {
        this.mDebugName = str;
    }

    public void setDebugSolverName(LinearSystem linearSystem, String str) {
        this.mDebugName = str;
        SolverVariable createObjectVariable = linearSystem.createObjectVariable(this.mLeft);
        SolverVariable createObjectVariable2 = linearSystem.createObjectVariable(this.mTop);
        SolverVariable createObjectVariable3 = linearSystem.createObjectVariable(this.mRight);
        SolverVariable createObjectVariable4 = linearSystem.createObjectVariable(this.mBottom);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append(".left");
        createObjectVariable.setName(stringBuilder.toString());
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append(str);
        stringBuilder2.append(".top");
        createObjectVariable2.setName(stringBuilder2.toString());
        stringBuilder2 = new StringBuilder();
        stringBuilder2.append(str);
        stringBuilder2.append(".right");
        createObjectVariable3.setName(stringBuilder2.toString());
        stringBuilder2 = new StringBuilder();
        stringBuilder2.append(str);
        stringBuilder2.append(".bottom");
        createObjectVariable4.setName(stringBuilder2.toString());
        if (this.mBaselineDistance > 0) {
            linearSystem = linearSystem.createObjectVariable(this.mBaseline);
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append(str);
            stringBuilder2.append(".baseline");
            linearSystem.setName(stringBuilder2.toString());
        }
    }

    public void createObjectVariables(LinearSystem linearSystem) {
        linearSystem.createObjectVariable(this.mLeft);
        linearSystem.createObjectVariable(this.mTop);
        linearSystem.createObjectVariable(this.mRight);
        linearSystem.createObjectVariable(this.mBottom);
        if (this.mBaselineDistance > 0) {
            linearSystem.createObjectVariable(this.mBaseline);
        }
    }

    public String toString() {
        String stringBuilder;
        StringBuilder stringBuilder2 = new StringBuilder();
        if (this.mType != null) {
            StringBuilder stringBuilder3 = new StringBuilder();
            stringBuilder3.append("type: ");
            stringBuilder3.append(this.mType);
            stringBuilder3.append(" ");
            stringBuilder = stringBuilder3.toString();
        } else {
            stringBuilder = "";
        }
        stringBuilder2.append(stringBuilder);
        if (this.mDebugName != null) {
            stringBuilder3 = new StringBuilder();
            stringBuilder3.append("id: ");
            stringBuilder3.append(this.mDebugName);
            stringBuilder3.append(" ");
            stringBuilder = stringBuilder3.toString();
        } else {
            stringBuilder = "";
        }
        stringBuilder2.append(stringBuilder);
        stringBuilder2.append("(");
        stringBuilder2.append(this.mX);
        stringBuilder2.append(", ");
        stringBuilder2.append(this.mY);
        stringBuilder2.append(") - (");
        stringBuilder2.append(this.mWidth);
        stringBuilder2.append(" x ");
        stringBuilder2.append(this.mHeight);
        stringBuilder2.append(") wrap: (");
        stringBuilder2.append(this.mWrapWidth);
        stringBuilder2.append(" x ");
        stringBuilder2.append(this.mWrapHeight);
        stringBuilder2.append(")");
        return stringBuilder2.toString();
    }

    int getInternalDrawX() {
        return this.mDrawX;
    }

    int getInternalDrawY() {
        return this.mDrawY;
    }

    public int getInternalDrawRight() {
        return this.mDrawX + this.mDrawWidth;
    }

    public int getInternalDrawBottom() {
        return this.mDrawY + this.mDrawHeight;
    }

    public int getX() {
        return this.mX;
    }

    public int getY() {
        return this.mY;
    }

    public int getWidth() {
        if (this.mVisibility == 8) {
            return 0;
        }
        return this.mWidth;
    }

    public int getOptimizerWrapWidth() {
        int i = this.mWidth;
        if (this.mListDimensionBehaviors[0] != DimensionBehaviour.MATCH_CONSTRAINT) {
            return i;
        }
        if (this.mMatchConstraintDefaultWidth == 1) {
            i = Math.max(this.mMatchConstraintMinWidth, i);
        } else if (this.mMatchConstraintMinWidth > 0) {
            i = this.mMatchConstraintMinWidth;
            this.mWidth = i;
        } else {
            i = 0;
        }
        return (this.mMatchConstraintMaxWidth <= 0 || this.mMatchConstraintMaxWidth >= i) ? i : this.mMatchConstraintMaxWidth;
    }

    public int getOptimizerWrapHeight() {
        int i = this.mHeight;
        if (this.mListDimensionBehaviors[1] != DimensionBehaviour.MATCH_CONSTRAINT) {
            return i;
        }
        if (this.mMatchConstraintDefaultHeight == 1) {
            i = Math.max(this.mMatchConstraintMinHeight, i);
        } else if (this.mMatchConstraintMinHeight > 0) {
            i = this.mMatchConstraintMinHeight;
            this.mHeight = i;
        } else {
            i = 0;
        }
        return (this.mMatchConstraintMaxHeight <= 0 || this.mMatchConstraintMaxHeight >= i) ? i : this.mMatchConstraintMaxHeight;
    }

    public int getWrapWidth() {
        return this.mWrapWidth;
    }

    public int getHeight() {
        if (this.mVisibility == 8) {
            return 0;
        }
        return this.mHeight;
    }

    public int getWrapHeight() {
        return this.mWrapHeight;
    }

    public int getLength(int i) {
        if (i == 0) {
            return getWidth();
        }
        return i == 1 ? getHeight() : 0;
    }

    public int getDrawX() {
        return this.mDrawX + this.mOffsetX;
    }

    public int getDrawY() {
        return this.mDrawY + this.mOffsetY;
    }

    public int getDrawWidth() {
        return this.mDrawWidth;
    }

    public int getDrawHeight() {
        return this.mDrawHeight;
    }

    public int getDrawBottom() {
        return getDrawY() + this.mDrawHeight;
    }

    public int getDrawRight() {
        return getDrawX() + this.mDrawWidth;
    }

    protected int getRootX() {
        return this.mX + this.mOffsetX;
    }

    protected int getRootY() {
        return this.mY + this.mOffsetY;
    }

    public int getMinWidth() {
        return this.mMinWidth;
    }

    public int getMinHeight() {
        return this.mMinHeight;
    }

    public int getLeft() {
        return getX();
    }

    public int getTop() {
        return getY();
    }

    public int getRight() {
        return getX() + this.mWidth;
    }

    public int getBottom() {
        return getY() + this.mHeight;
    }

    public float getHorizontalBiasPercent() {
        return this.mHorizontalBiasPercent;
    }

    public float getVerticalBiasPercent() {
        return this.mVerticalBiasPercent;
    }

    public float getBiasPercent(int i) {
        if (i == 0) {
            return this.mHorizontalBiasPercent;
        }
        return i == 1 ? this.mVerticalBiasPercent : -1082130432;
    }

    public boolean hasBaseline() {
        return this.mBaselineDistance > 0;
    }

    public int getBaselineDistance() {
        return this.mBaselineDistance;
    }

    public Object getCompanionWidget() {
        return this.mCompanionWidget;
    }

    public ArrayList<ConstraintAnchor> getAnchors() {
        return this.mAnchors;
    }

    public void setX(int i) {
        this.mX = i;
    }

    public void setY(int i) {
        this.mY = i;
    }

    public void setOrigin(int i, int i2) {
        this.mX = i;
        this.mY = i2;
    }

    public void setOffset(int i, int i2) {
        this.mOffsetX = i;
        this.mOffsetY = i2;
    }

    public void setGoneMargin(Type type, int i) {
        switch (type) {
            case LEFT:
                this.mLeft.mGoneMargin = i;
                return;
            case TOP:
                this.mTop.mGoneMargin = i;
                return;
            case RIGHT:
                this.mRight.mGoneMargin = i;
                return;
            case BOTTOM:
                this.mBottom.mGoneMargin = i;
                return;
            default:
                return;
        }
    }

    public void updateDrawPosition() {
        int i = this.mX;
        int i2 = this.mY;
        int i3 = this.mX + this.mWidth;
        int i4 = this.mY + this.mHeight;
        this.mDrawX = i;
        this.mDrawY = i2;
        this.mDrawWidth = i3 - i;
        this.mDrawHeight = i4 - i2;
    }

    public void forceUpdateDrawPosition() {
        int i = this.mX;
        int i2 = this.mY;
        int i3 = this.mX + this.mWidth;
        int i4 = this.mY + this.mHeight;
        this.mDrawX = i;
        this.mDrawY = i2;
        this.mDrawWidth = i3 - i;
        this.mDrawHeight = i4 - i2;
    }

    public void setDrawOrigin(int i, int i2) {
        this.mDrawX = i - this.mOffsetX;
        this.mDrawY = i2 - this.mOffsetY;
        this.mX = this.mDrawX;
        this.mY = this.mDrawY;
    }

    public void setDrawX(int i) {
        this.mDrawX = i - this.mOffsetX;
        this.mX = this.mDrawX;
    }

    public void setDrawY(int i) {
        this.mDrawY = i - this.mOffsetY;
        this.mY = this.mDrawY;
    }

    public void setDrawWidth(int i) {
        this.mDrawWidth = i;
    }

    public void setDrawHeight(int i) {
        this.mDrawHeight = i;
    }

    public void setWidth(int i) {
        this.mWidth = i;
        if (this.mWidth < this.mMinWidth) {
            this.mWidth = this.mMinWidth;
        }
    }

    public void setHeight(int i) {
        this.mHeight = i;
        if (this.mHeight < this.mMinHeight) {
            this.mHeight = this.mMinHeight;
        }
    }

    public void setLength(int i, int i2) {
        if (i2 == 0) {
            setWidth(i);
        } else if (i2 == 1) {
            setHeight(i);
        }
    }

    public void setHorizontalMatchStyle(int i, int i2, int i3, float f) {
        this.mMatchConstraintDefaultWidth = i;
        this.mMatchConstraintMinWidth = i2;
        this.mMatchConstraintMaxWidth = i3;
        this.mMatchConstraintPercentWidth = f;
        if (f < 1065353216 && this.mMatchConstraintDefaultWidth == 0) {
            this.mMatchConstraintDefaultWidth = 2;
        }
    }

    public void setVerticalMatchStyle(int i, int i2, int i3, float f) {
        this.mMatchConstraintDefaultHeight = i;
        this.mMatchConstraintMinHeight = i2;
        this.mMatchConstraintMaxHeight = i3;
        this.mMatchConstraintPercentHeight = f;
        if (f < 1065353216 && this.mMatchConstraintDefaultHeight == 0) {
            this.mMatchConstraintDefaultHeight = 2;
        }
    }

    public void setDimensionRatio(java.lang.String r9) {
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
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1603198149.run(Unknown Source)
*/
        /*
        r8 = this;
        r0 = 0;
        if (r9 == 0) goto L_0x008e;
    L_0x0003:
        r1 = r9.length();
        if (r1 != 0) goto L_0x000b;
    L_0x0009:
        goto L_0x008e;
    L_0x000b:
        r1 = -1;
        r2 = r9.length();
        r3 = 44;
        r3 = r9.indexOf(r3);
        r4 = 0;
        r5 = 1;
        if (r3 <= 0) goto L_0x0037;
    L_0x001a:
        r6 = r2 + -1;
        if (r3 >= r6) goto L_0x0037;
    L_0x001e:
        r6 = r9.substring(r4, r3);
        r7 = "W";
        r7 = r6.equalsIgnoreCase(r7);
        if (r7 == 0) goto L_0x002c;
    L_0x002a:
        r1 = 0;
        goto L_0x0035;
    L_0x002c:
        r4 = "H";
        r4 = r6.equalsIgnoreCase(r4);
        if (r4 == 0) goto L_0x0035;
    L_0x0034:
        r1 = 1;
    L_0x0035:
        r4 = r3 + 1;
    L_0x0037:
        r3 = 58;
        r3 = r9.indexOf(r3);
        if (r3 < 0) goto L_0x0075;
    L_0x003f:
        r2 = r2 - r5;
        if (r3 >= r2) goto L_0x0075;
    L_0x0042:
        r2 = r9.substring(r4, r3);
        r3 = r3 + r5;
        r9 = r9.substring(r3);
        r3 = r2.length();
        if (r3 <= 0) goto L_0x0084;
    L_0x0051:
        r3 = r9.length();
        if (r3 <= 0) goto L_0x0084;
    L_0x0057:
        r2 = java.lang.Float.parseFloat(r2);	 Catch:{ NumberFormatException -> 0x0084 }
        r9 = java.lang.Float.parseFloat(r9);	 Catch:{ NumberFormatException -> 0x0084 }
        r3 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1));	 Catch:{ NumberFormatException -> 0x0084 }
        if (r3 <= 0) goto L_0x0084;	 Catch:{ NumberFormatException -> 0x0084 }
    L_0x0063:
        r3 = (r9 > r0 ? 1 : (r9 == r0 ? 0 : -1));	 Catch:{ NumberFormatException -> 0x0084 }
        if (r3 <= 0) goto L_0x0084;	 Catch:{ NumberFormatException -> 0x0084 }
    L_0x0067:
        if (r1 != r5) goto L_0x006f;	 Catch:{ NumberFormatException -> 0x0084 }
    L_0x0069:
        r9 = r9 / r2;	 Catch:{ NumberFormatException -> 0x0084 }
        r9 = java.lang.Math.abs(r9);	 Catch:{ NumberFormatException -> 0x0084 }
        goto L_0x0085;	 Catch:{ NumberFormatException -> 0x0084 }
    L_0x006f:
        r2 = r2 / r9;	 Catch:{ NumberFormatException -> 0x0084 }
        r9 = java.lang.Math.abs(r2);	 Catch:{ NumberFormatException -> 0x0084 }
        goto L_0x0085;
    L_0x0075:
        r9 = r9.substring(r4);
        r2 = r9.length();
        if (r2 <= 0) goto L_0x0084;
    L_0x007f:
        r9 = java.lang.Float.parseFloat(r9);	 Catch:{ NumberFormatException -> 0x0084 }
        goto L_0x0085;
    L_0x0084:
        r9 = 0;
    L_0x0085:
        r0 = (r9 > r0 ? 1 : (r9 == r0 ? 0 : -1));
        if (r0 <= 0) goto L_0x008d;
    L_0x0089:
        r8.mDimensionRatio = r9;
        r8.mDimensionRatioSide = r1;
    L_0x008d:
        return;
    L_0x008e:
        r8.mDimensionRatio = r0;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.solver.widgets.ConstraintWidget.setDimensionRatio(java.lang.String):void");
    }

    public void setDimensionRatio(float f, int i) {
        this.mDimensionRatio = f;
        this.mDimensionRatioSide = i;
    }

    public float getDimensionRatio() {
        return this.mDimensionRatio;
    }

    public int getDimensionRatioSide() {
        return this.mDimensionRatioSide;
    }

    public void setHorizontalBiasPercent(float f) {
        this.mHorizontalBiasPercent = f;
    }

    public void setVerticalBiasPercent(float f) {
        this.mVerticalBiasPercent = f;
    }

    public void setMinWidth(int i) {
        if (i < 0) {
            this.mMinWidth = 0;
        } else {
            this.mMinWidth = i;
        }
    }

    public void setMinHeight(int i) {
        if (i < 0) {
            this.mMinHeight = 0;
        } else {
            this.mMinHeight = i;
        }
    }

    public void setWrapWidth(int i) {
        this.mWrapWidth = i;
    }

    public void setWrapHeight(int i) {
        this.mWrapHeight = i;
    }

    public void setDimension(int i, int i2) {
        this.mWidth = i;
        if (this.mWidth < this.mMinWidth) {
            this.mWidth = this.mMinWidth;
        }
        this.mHeight = i2;
        if (this.mHeight < this.mMinHeight) {
            this.mHeight = this.mMinHeight;
        }
    }

    public void setFrame(int i, int i2, int i3, int i4) {
        i3 -= i;
        i4 -= i2;
        this.mX = i;
        this.mY = i2;
        if (this.mVisibility == 8) {
            this.mWidth = 0;
            this.mHeight = 0;
            return;
        }
        if (this.mListDimensionBehaviors[0] == DimensionBehaviour.FIXED && i3 < this.mWidth) {
            i3 = this.mWidth;
        }
        if (this.mListDimensionBehaviors[1] == DimensionBehaviour.FIXED && i4 < this.mHeight) {
            i4 = this.mHeight;
        }
        this.mWidth = i3;
        this.mHeight = i4;
        if (this.mHeight < this.mMinHeight) {
            this.mHeight = this.mMinHeight;
        }
        if (this.mWidth < this.mMinWidth) {
            this.mWidth = this.mMinWidth;
        }
        this.mOptimizerMeasured = true;
    }

    public void setFrame(int i, int i2, int i3) {
        if (i3 == 0) {
            setHorizontalDimension(i, i2);
        } else if (i3 == 1) {
            setVerticalDimension(i, i2);
        }
        this.mOptimizerMeasured = true;
    }

    public void setHorizontalDimension(int i, int i2) {
        this.mX = i;
        this.mWidth = i2 - i;
        if (this.mWidth < this.mMinWidth) {
            this.mWidth = this.mMinWidth;
        }
    }

    public void setVerticalDimension(int i, int i2) {
        this.mY = i;
        this.mHeight = i2 - i;
        if (this.mHeight < this.mMinHeight) {
            this.mHeight = this.mMinHeight;
        }
    }

    int getRelativePositioning(int i) {
        if (i == 0) {
            return this.mRelX;
        }
        return i == 1 ? this.mRelY : 0;
    }

    void setRelativePositioning(int i, int i2) {
        if (i2 == 0) {
            this.mRelX = i;
        } else if (i2 == 1) {
            this.mRelY = i;
        }
    }

    public void setBaselineDistance(int i) {
        this.mBaselineDistance = i;
    }

    public void setCompanionWidget(Object obj) {
        this.mCompanionWidget = obj;
    }

    public void setContainerItemSkip(int i) {
        if (i >= 0) {
            this.mContainerItemSkip = i;
        } else {
            this.mContainerItemSkip = 0;
        }
    }

    public int getContainerItemSkip() {
        return this.mContainerItemSkip;
    }

    public void setHorizontalWeight(float f) {
        this.mWeight[0] = f;
    }

    public void setVerticalWeight(float f) {
        this.mWeight[1] = f;
    }

    public void setHorizontalChainStyle(int i) {
        this.mHorizontalChainStyle = i;
    }

    public int getHorizontalChainStyle() {
        return this.mHorizontalChainStyle;
    }

    public void setVerticalChainStyle(int i) {
        this.mVerticalChainStyle = i;
    }

    public int getVerticalChainStyle() {
        return this.mVerticalChainStyle;
    }

    public boolean allowedInBarrier() {
        return this.mVisibility != 8;
    }

    public void immediateConnect(Type type, ConstraintWidget constraintWidget, Type type2, int i, int i2) {
        getAnchor(type).connect(constraintWidget.getAnchor(type2), i, i2, Strength.STRONG, 0, true);
    }

    public void connect(ConstraintAnchor constraintAnchor, ConstraintAnchor constraintAnchor2, int i, int i2) {
        connect(constraintAnchor, constraintAnchor2, i, Strength.STRONG, i2);
    }

    public void connect(ConstraintAnchor constraintAnchor, ConstraintAnchor constraintAnchor2, int i) {
        connect(constraintAnchor, constraintAnchor2, i, Strength.STRONG, 0);
    }

    public void connect(ConstraintAnchor constraintAnchor, ConstraintAnchor constraintAnchor2, int i, Strength strength, int i2) {
        if (constraintAnchor.getOwner() == this) {
            connect(constraintAnchor.getType(), constraintAnchor2.getOwner(), constraintAnchor2.getType(), i, strength, i2);
        }
    }

    public void connect(Type type, ConstraintWidget constraintWidget, Type type2, int i) {
        connect(type, constraintWidget, type2, i, Strength.STRONG);
    }

    public void connect(Type type, ConstraintWidget constraintWidget, Type type2) {
        connect(type, constraintWidget, type2, 0, Strength.STRONG);
    }

    public void connect(Type type, ConstraintWidget constraintWidget, Type type2, int i, Strength strength) {
        connect(type, constraintWidget, type2, i, strength, 0);
    }

    public void connect(Type type, ConstraintWidget constraintWidget, Type type2, int i, Strength strength, int i2) {
        ConstraintWidget constraintWidget2 = this;
        Type type3 = type;
        ConstraintWidget constraintWidget3 = constraintWidget;
        Type type4 = type2;
        int i3 = i2;
        int i4 = 0;
        ConstraintAnchor anchor;
        ConstraintAnchor anchor2;
        if (type3 == Type.CENTER) {
            ConstraintWidget constraintWidget4;
            Strength strength2;
            int i5;
            if (type4 == Type.CENTER) {
                Object obj;
                anchor = getAnchor(Type.LEFT);
                anchor2 = getAnchor(Type.RIGHT);
                ConstraintAnchor anchor3 = getAnchor(Type.TOP);
                ConstraintAnchor anchor4 = getAnchor(Type.BOTTOM);
                Object obj2 = 1;
                if ((anchor == null || !anchor.isConnected()) && (anchor2 == null || !anchor2.isConnected())) {
                    constraintWidget4 = constraintWidget;
                    strength2 = strength;
                    i5 = i2;
                    connect(Type.LEFT, constraintWidget4, Type.LEFT, 0, strength2, i5);
                    connect(Type.RIGHT, constraintWidget4, Type.RIGHT, 0, strength2, i5);
                    obj = 1;
                } else {
                    obj = null;
                }
                if ((anchor3 == null || !anchor3.isConnected()) && (anchor4 == null || !anchor4.isConnected())) {
                    constraintWidget4 = constraintWidget;
                    strength2 = strength;
                    i5 = i2;
                    connect(Type.TOP, constraintWidget4, Type.TOP, 0, strength2, i5);
                    connect(Type.BOTTOM, constraintWidget4, Type.BOTTOM, 0, strength2, i5);
                } else {
                    obj2 = null;
                }
                if (obj != null && obj2 != null) {
                    getAnchor(Type.CENTER).connect(constraintWidget3.getAnchor(Type.CENTER), 0, i3);
                    return;
                } else if (obj != null) {
                    getAnchor(Type.CENTER_X).connect(constraintWidget3.getAnchor(Type.CENTER_X), 0, i3);
                    return;
                } else if (obj2 != null) {
                    getAnchor(Type.CENTER_Y).connect(constraintWidget3.getAnchor(Type.CENTER_Y), 0, i3);
                    return;
                } else {
                    return;
                }
            }
            if (type4 != Type.LEFT) {
                if (type4 != Type.RIGHT) {
                    if (type4 == Type.TOP || type4 == Type.BOTTOM) {
                        constraintWidget4 = constraintWidget;
                        Type type5 = type2;
                        strength2 = strength;
                        i5 = i2;
                        connect(Type.TOP, constraintWidget4, type5, 0, strength2, i5);
                        connect(Type.BOTTOM, constraintWidget4, type5, 0, strength2, i5);
                        getAnchor(Type.CENTER).connect(constraintWidget.getAnchor(type2), 0, i3);
                        return;
                    }
                    return;
                }
            }
            connect(Type.LEFT, constraintWidget, type2, 0, strength, i2);
            try {
                connect(Type.RIGHT, constraintWidget, type2, 0, strength, i2);
                getAnchor(Type.CENTER).connect(constraintWidget.getAnchor(type2), 0, i3);
            } catch (Throwable th) {
                Throwable th2 = th;
            }
        } else if (type3 == Type.CENTER_X && (type4 == Type.LEFT || type4 == Type.RIGHT)) {
            anchor = getAnchor(Type.LEFT);
            anchor2 = constraintWidget.getAnchor(type2);
            r2 = getAnchor(Type.RIGHT);
            anchor.connect(anchor2, 0, i3);
            r2.connect(anchor2, 0, i3);
            getAnchor(Type.CENTER_X).connect(anchor2, 0, i3);
        } else if (type3 == Type.CENTER_Y && (type4 == Type.TOP || type4 == Type.BOTTOM)) {
            anchor = constraintWidget.getAnchor(type2);
            getAnchor(Type.TOP).connect(anchor, 0, i3);
            getAnchor(Type.BOTTOM).connect(anchor, 0, i3);
            getAnchor(Type.CENTER_Y).connect(anchor, 0, i3);
        } else if (type3 == Type.CENTER_X && type4 == Type.CENTER_X) {
            getAnchor(Type.LEFT).connect(constraintWidget3.getAnchor(Type.LEFT), 0, i3);
            getAnchor(Type.RIGHT).connect(constraintWidget3.getAnchor(Type.RIGHT), 0, i3);
            getAnchor(Type.CENTER_X).connect(constraintWidget.getAnchor(type2), 0, i3);
        } else if (type3 == Type.CENTER_Y && type4 == Type.CENTER_Y) {
            getAnchor(Type.TOP).connect(constraintWidget3.getAnchor(Type.TOP), 0, i3);
            getAnchor(Type.BOTTOM).connect(constraintWidget3.getAnchor(Type.BOTTOM), 0, i3);
            getAnchor(Type.CENTER_Y).connect(constraintWidget.getAnchor(type2), 0, i3);
        } else {
            anchor2 = getAnchor(type);
            r2 = constraintWidget.getAnchor(type2);
            if (anchor2.isValidConnection(r2)) {
                ConstraintAnchor anchor5;
                if (type3 == Type.BASELINE) {
                    anchor = getAnchor(Type.TOP);
                    anchor5 = getAnchor(Type.BOTTOM);
                    if (anchor != null) {
                        anchor.reset();
                    }
                    if (anchor5 != null) {
                        anchor5.reset();
                    }
                } else {
                    if (type3 != Type.TOP) {
                        if (type3 != Type.BOTTOM) {
                            if (type3 == Type.LEFT || type3 == Type.RIGHT) {
                                anchor5 = getAnchor(Type.CENTER);
                                if (anchor5.getTarget() != r2) {
                                    anchor5.reset();
                                }
                                anchor = getAnchor(type).getOpposite();
                                anchor5 = getAnchor(Type.CENTER_X);
                                if (anchor5.isConnected()) {
                                    anchor.reset();
                                    anchor5.reset();
                                }
                            }
                            i4 = i;
                        }
                    }
                    anchor5 = getAnchor(Type.BASELINE);
                    if (anchor5 != null) {
                        anchor5.reset();
                    }
                    anchor5 = getAnchor(Type.CENTER);
                    if (anchor5.getTarget() != r2) {
                        anchor5.reset();
                    }
                    anchor = getAnchor(type).getOpposite();
                    anchor5 = getAnchor(Type.CENTER_Y);
                    if (anchor5.isConnected()) {
                        anchor.reset();
                        anchor5.reset();
                    }
                    i4 = i;
                }
                anchor2.connect(r2, i4, strength, i3);
                r2.getOwner().connectedTo(anchor2.getOwner());
            }
        }
    }

    public void resetAllConstraints() {
        resetAnchors();
        setVerticalBiasPercent(DEFAULT_BIAS);
        setHorizontalBiasPercent(DEFAULT_BIAS);
        if (!(this instanceof ConstraintWidgetContainer)) {
            if (getHorizontalDimensionBehaviour() == DimensionBehaviour.MATCH_CONSTRAINT) {
                if (getWidth() == getWrapWidth()) {
                    setHorizontalDimensionBehaviour(DimensionBehaviour.WRAP_CONTENT);
                } else if (getWidth() > getMinWidth()) {
                    setHorizontalDimensionBehaviour(DimensionBehaviour.FIXED);
                }
            }
            if (getVerticalDimensionBehaviour() == DimensionBehaviour.MATCH_CONSTRAINT) {
                if (getHeight() == getWrapHeight()) {
                    setVerticalDimensionBehaviour(DimensionBehaviour.WRAP_CONTENT);
                } else if (getHeight() > getMinHeight()) {
                    setVerticalDimensionBehaviour(DimensionBehaviour.FIXED);
                }
            }
        }
    }

    public void resetAnchor(ConstraintAnchor constraintAnchor) {
        if (getParent() == null || !(getParent() instanceof ConstraintWidgetContainer) || !((ConstraintWidgetContainer) getParent()).handlesInternalConstraints()) {
            ConstraintAnchor anchor = getAnchor(Type.LEFT);
            ConstraintAnchor anchor2 = getAnchor(Type.RIGHT);
            ConstraintAnchor anchor3 = getAnchor(Type.TOP);
            ConstraintAnchor anchor4 = getAnchor(Type.BOTTOM);
            ConstraintAnchor anchor5 = getAnchor(Type.CENTER);
            ConstraintAnchor anchor6 = getAnchor(Type.CENTER_X);
            ConstraintAnchor anchor7 = getAnchor(Type.CENTER_Y);
            if (constraintAnchor == anchor5) {
                if (anchor.isConnected() && anchor2.isConnected() && anchor.getTarget() == anchor2.getTarget()) {
                    anchor.reset();
                    anchor2.reset();
                }
                if (anchor3.isConnected() && anchor4.isConnected() && anchor3.getTarget() == anchor4.getTarget()) {
                    anchor3.reset();
                    anchor4.reset();
                }
                this.mHorizontalBiasPercent = 0.5f;
                this.mVerticalBiasPercent = 0.5f;
            } else if (constraintAnchor == anchor6) {
                if (anchor.isConnected() && anchor2.isConnected() && anchor.getTarget().getOwner() == anchor2.getTarget().getOwner()) {
                    anchor.reset();
                    anchor2.reset();
                }
                this.mHorizontalBiasPercent = 0.5f;
            } else if (constraintAnchor == anchor7) {
                if (anchor3.isConnected() && anchor4.isConnected() && anchor3.getTarget().getOwner() == anchor4.getTarget().getOwner()) {
                    anchor3.reset();
                    anchor4.reset();
                }
                this.mVerticalBiasPercent = 0.5f;
            } else {
                if (constraintAnchor != anchor) {
                    if (constraintAnchor != anchor2) {
                        if ((constraintAnchor == anchor3 || constraintAnchor == anchor4) && anchor3.isConnected() && anchor3.getTarget() == anchor4.getTarget()) {
                            anchor5.reset();
                        }
                    }
                }
                if (anchor.isConnected() && anchor.getTarget() == anchor2.getTarget()) {
                    anchor5.reset();
                }
            }
            constraintAnchor.reset();
        }
    }

    public void resetAnchors() {
        ConstraintWidget parent = getParent();
        if (parent == null || !(parent instanceof ConstraintWidgetContainer) || !((ConstraintWidgetContainer) getParent()).handlesInternalConstraints()) {
            int size = this.mAnchors.size();
            for (int i = 0; i < size; i++) {
                ((ConstraintAnchor) this.mAnchors.get(i)).reset();
            }
        }
    }

    public void resetAnchors(int i) {
        ConstraintWidget parent = getParent();
        if (parent == null || !(parent instanceof ConstraintWidgetContainer) || !((ConstraintWidgetContainer) getParent()).handlesInternalConstraints()) {
            int size = this.mAnchors.size();
            for (int i2 = 0; i2 < size; i2++) {
                ConstraintAnchor constraintAnchor = (ConstraintAnchor) this.mAnchors.get(i2);
                if (i == constraintAnchor.getConnectionCreator()) {
                    if (constraintAnchor.isVerticalAnchor()) {
                        setVerticalBiasPercent(DEFAULT_BIAS);
                    } else {
                        setHorizontalBiasPercent(DEFAULT_BIAS);
                    }
                    constraintAnchor.reset();
                }
            }
        }
    }

    public void disconnectWidget(ConstraintWidget constraintWidget) {
        ArrayList anchors = getAnchors();
        int size = anchors.size();
        for (int i = 0; i < size; i++) {
            ConstraintAnchor constraintAnchor = (ConstraintAnchor) anchors.get(i);
            if (constraintAnchor.isConnected() && constraintAnchor.getTarget().getOwner() == constraintWidget) {
                constraintAnchor.reset();
            }
        }
    }

    public void disconnectUnlockedWidget(ConstraintWidget constraintWidget) {
        ArrayList anchors = getAnchors();
        int size = anchors.size();
        for (int i = 0; i < size; i++) {
            ConstraintAnchor constraintAnchor = (ConstraintAnchor) anchors.get(i);
            if (constraintAnchor.isConnected() && constraintAnchor.getTarget().getOwner() == constraintWidget && constraintAnchor.getConnectionCreator() == 2) {
                constraintAnchor.reset();
            }
        }
    }

    public ConstraintAnchor getAnchor(Type type) {
        switch (type) {
            case LEFT:
                return this.mLeft;
            case TOP:
                return this.mTop;
            case RIGHT:
                return this.mRight;
            case BOTTOM:
                return this.mBottom;
            case BASELINE:
                return this.mBaseline;
            case CENTER:
                return this.mCenter;
            case CENTER_X:
                return this.mCenterX;
            case CENTER_Y:
                return this.mCenterY;
            case NONE:
                return null;
            default:
                throw new AssertionError(type.name());
        }
    }

    public DimensionBehaviour getHorizontalDimensionBehaviour() {
        return this.mListDimensionBehaviors[0];
    }

    public DimensionBehaviour getVerticalDimensionBehaviour() {
        return this.mListDimensionBehaviors[1];
    }

    public DimensionBehaviour getDimensionBehaviour(int i) {
        if (i == 0) {
            return getHorizontalDimensionBehaviour();
        }
        return i == 1 ? getVerticalDimensionBehaviour() : 0;
    }

    public void setHorizontalDimensionBehaviour(DimensionBehaviour dimensionBehaviour) {
        this.mListDimensionBehaviors[0] = dimensionBehaviour;
        if (dimensionBehaviour == DimensionBehaviour.WRAP_CONTENT) {
            setWidth(this.mWrapWidth);
        }
    }

    public void setVerticalDimensionBehaviour(DimensionBehaviour dimensionBehaviour) {
        this.mListDimensionBehaviors[1] = dimensionBehaviour;
        if (dimensionBehaviour == DimensionBehaviour.WRAP_CONTENT) {
            setHeight(this.mWrapHeight);
        }
    }

    public boolean isInHorizontalChain() {
        return (this.mLeft.mTarget != null && this.mLeft.mTarget.mTarget == this.mLeft) || (this.mRight.mTarget != null && this.mRight.mTarget.mTarget == this.mRight);
    }

    public ConstraintWidget getHorizontalChainControlWidget() {
        if (!isInHorizontalChain()) {
            return null;
        }
        ConstraintWidget constraintWidget = this;
        ConstraintWidget constraintWidget2 = null;
        while (constraintWidget2 == null && constraintWidget != null) {
            ConstraintWidget constraintWidget3;
            ConstraintAnchor anchor = constraintWidget.getAnchor(Type.LEFT);
            if (anchor == null) {
                anchor = null;
            } else {
                anchor = anchor.getTarget();
            }
            if (anchor == null) {
                constraintWidget3 = null;
            } else {
                constraintWidget3 = anchor.getOwner();
            }
            if (constraintWidget3 == getParent()) {
                return constraintWidget;
            }
            ConstraintAnchor constraintAnchor;
            if (constraintWidget3 == null) {
                constraintAnchor = null;
            } else {
                constraintAnchor = constraintWidget3.getAnchor(Type.RIGHT).getTarget();
            }
            if (constraintAnchor == null || constraintAnchor.getOwner() == constraintWidget) {
                constraintWidget = constraintWidget3;
            } else {
                constraintWidget2 = constraintWidget;
            }
        }
        return constraintWidget2;
    }

    public boolean isInVerticalChain() {
        return (this.mTop.mTarget != null && this.mTop.mTarget.mTarget == this.mTop) || (this.mBottom.mTarget != null && this.mBottom.mTarget.mTarget == this.mBottom);
    }

    public ConstraintWidget getVerticalChainControlWidget() {
        if (!isInVerticalChain()) {
            return null;
        }
        ConstraintWidget constraintWidget = this;
        ConstraintWidget constraintWidget2 = null;
        while (constraintWidget2 == null && constraintWidget != null) {
            ConstraintWidget constraintWidget3;
            ConstraintAnchor anchor = constraintWidget.getAnchor(Type.TOP);
            if (anchor == null) {
                anchor = null;
            } else {
                anchor = anchor.getTarget();
            }
            if (anchor == null) {
                constraintWidget3 = null;
            } else {
                constraintWidget3 = anchor.getOwner();
            }
            if (constraintWidget3 == getParent()) {
                return constraintWidget;
            }
            ConstraintAnchor constraintAnchor;
            if (constraintWidget3 == null) {
                constraintAnchor = null;
            } else {
                constraintAnchor = constraintWidget3.getAnchor(Type.BOTTOM).getTarget();
            }
            if (constraintAnchor == null || constraintAnchor.getOwner() == constraintWidget) {
                constraintWidget = constraintWidget3;
            } else {
                constraintWidget2 = constraintWidget;
            }
        }
        return constraintWidget2;
    }

    private boolean isChainHead(int i) {
        i *= 2;
        if (!(this.mListAnchors[i].mTarget == null || this.mListAnchors[i].mTarget.mTarget == this.mListAnchors[i])) {
            i++;
            if (this.mListAnchors[i].mTarget != null && this.mListAnchors[i].mTarget.mTarget == this.mListAnchors[i]) {
                return true;
            }
        }
        return false;
    }

    public void addToSolver(LinearSystem linearSystem) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        SolverVariable solverVariable;
        int i;
        int i2;
        int i3;
        int i4;
        Object obj;
        boolean z6;
        boolean z7;
        boolean isConnected;
        boolean z8;
        SolverVariable solverVariable2;
        SolverVariable solverVariable3;
        boolean z9;
        SolverVariable solverVariable4;
        LinearSystem linearSystem2;
        SolverVariable solverVariable5;
        boolean z10;
        boolean z11;
        SolverVariable solverVariable6;
        ConstraintWidget constraintWidget;
        LinearSystem linearSystem3 = linearSystem;
        SolverVariable createObjectVariable = linearSystem3.createObjectVariable(this.mLeft);
        SolverVariable createObjectVariable2 = linearSystem3.createObjectVariable(this.mRight);
        SolverVariable createObjectVariable3 = linearSystem3.createObjectVariable(this.mTop);
        SolverVariable createObjectVariable4 = linearSystem3.createObjectVariable(this.mBottom);
        SolverVariable createObjectVariable5 = linearSystem3.createObjectVariable(this.mBaseline);
        if (this.mParent != null) {
            boolean z12;
            z = r15.mParent != null && r15.mParent.mListDimensionBehaviors[0] == DimensionBehaviour.WRAP_CONTENT;
            boolean z13 = r15.mParent != null && r15.mParent.mListDimensionBehaviors[1] == DimensionBehaviour.WRAP_CONTENT;
            if (isChainHead(0)) {
                ((ConstraintWidgetContainer) r15.mParent).addChain(r15, 0);
                z12 = true;
            } else {
                z12 = isInHorizontalChain();
            }
            if (isChainHead(1)) {
                ((ConstraintWidgetContainer) r15.mParent).addChain(r15, 1);
                z2 = true;
            } else {
                z2 = isInVerticalChain();
            }
            if (z && r15.mVisibility != 8 && r15.mLeft.mTarget == null && r15.mRight.mTarget == null) {
                linearSystem3.addGreaterThan(linearSystem3.createObjectVariable(r15.mParent.mRight), createObjectVariable2, 0, 1);
            }
            if (z13 && r15.mVisibility != 8 && r15.mTop.mTarget == null && r15.mBottom.mTarget == null && r15.mBaseline == null) {
                linearSystem3.addGreaterThan(linearSystem3.createObjectVariable(r15.mParent.mBottom), createObjectVariable4, 0, 1);
            }
            z3 = z13;
            z4 = z12;
            z5 = z2;
        } else {
            z = false;
            z3 = false;
            z4 = false;
            z5 = false;
        }
        int i5 = r15.mWidth;
        if (i5 < r15.mMinWidth) {
            i5 = r15.mMinWidth;
        }
        int i6 = r15.mHeight;
        if (i6 < r15.mMinHeight) {
            i6 = r15.mMinHeight;
        }
        z2 = r15.mListDimensionBehaviors[0] != DimensionBehaviour.MATCH_CONSTRAINT;
        boolean z14 = r15.mListDimensionBehaviors[1] != DimensionBehaviour.MATCH_CONSTRAINT;
        r15.mResolvedDimensionRatioSide = r15.mDimensionRatioSide;
        r15.mResolvedDimensionRatio = r15.mDimensionRatio;
        int i7 = r15.mMatchConstraintDefaultWidth;
        int i8 = r15.mMatchConstraintDefaultHeight;
        if (r15.mDimensionRatio <= 0.0f || r15.mVisibility == 8) {
            solverVariable = createObjectVariable5;
            i = i8;
            i2 = i5;
            i3 = i6;
            i4 = i7;
        } else {
            solverVariable = createObjectVariable5;
            if (r15.mListDimensionBehaviors[0] == DimensionBehaviour.MATCH_CONSTRAINT && i7 == 0) {
                i7 = 3;
            }
            if (r15.mListDimensionBehaviors[1] == DimensionBehaviour.MATCH_CONSTRAINT && i8 == 0) {
                i8 = 3;
            }
            if (r15.mListDimensionBehaviors[0] == DimensionBehaviour.MATCH_CONSTRAINT && r15.mListDimensionBehaviors[1] == DimensionBehaviour.MATCH_CONSTRAINT && i7 == 3 && i8 == 3) {
                setupDimensionRatio(z, z3, z2, z14);
            } else if (r15.mListDimensionBehaviors[0] == DimensionBehaviour.MATCH_CONSTRAINT && i7 == 3) {
                r15.mResolvedDimensionRatioSide = 0;
                r1 = (int) (r15.mResolvedDimensionRatio * ((float) r15.mHeight));
                if (r15.mListDimensionBehaviors[1] != DimensionBehaviour.MATCH_CONSTRAINT) {
                    i2 = r1;
                    i = i8;
                    i3 = i6;
                    i4 = 4;
                } else {
                    i2 = r1;
                    i = i8;
                    i3 = i6;
                    i4 = i7;
                    obj = 1;
                    r15.mResolvedMatchConstraintDefault[0] = i4;
                    r15.mResolvedMatchConstraintDefault[1] = i;
                    if (obj == null) {
                        if (r15.mResolvedDimensionRatioSide == 0) {
                            if (r15.mResolvedDimensionRatioSide == -1) {
                            }
                        }
                        z6 = true;
                        if (r15.mListDimensionBehaviors[0] != DimensionBehaviour.WRAP_CONTENT) {
                        }
                        isConnected = r15.mCenter.isConnected() ^ 1;
                        if (r15.mHorizontalResolution == 2) {
                            if (r15.mParent == null) {
                            }
                            if (r15.mParent == null) {
                            }
                            z8 = z3;
                            solverVariable2 = solverVariable;
                            solverVariable = createObjectVariable4;
                            solverVariable3 = createObjectVariable3;
                            z9 = z7;
                            solverVariable4 = createObjectVariable2;
                            applyConstraints(linearSystem, z, r15.mParent == null ? linearSystem3.createObjectVariable(r15.mParent.mLeft) : null, r15.mParent == null ? linearSystem3.createObjectVariable(r15.mParent.mRight) : null, r15.mListDimensionBehaviors[0], z9, r15.mLeft, r15.mRight, r15.mX, i2, r15.mMinWidth, r15.mMaxDimension[0], r15.mHorizontalBiasPercent, z6, z4, i4, r15.mMatchConstraintMinWidth, r15.mMatchConstraintMaxWidth, r15.mMatchConstraintPercentWidth, isConnected);
                        } else {
                            solverVariable3 = createObjectVariable3;
                            solverVariable4 = createObjectVariable2;
                            z8 = z3;
                            solverVariable2 = solverVariable;
                            solverVariable = createObjectVariable4;
                        }
                        if (this.mVerticalResolution != 2) {
                            if (r15.mListDimensionBehaviors[1] != DimensionBehaviour.WRAP_CONTENT) {
                            }
                            if (obj == null) {
                            }
                            if (r15.mBaselineDistance <= 0) {
                                createObjectVariable4 = solverVariable3;
                                linearSystem2 = linearSystem;
                            } else if (r15.mBaseline.getResolutionNode().state != 1) {
                                linearSystem2 = linearSystem;
                                r15.mBaseline.getResolutionNode().addResolvedValue(linearSystem2);
                                createObjectVariable4 = solverVariable3;
                            } else {
                                linearSystem2 = linearSystem;
                                solverVariable5 = solverVariable2;
                                createObjectVariable4 = solverVariable3;
                                linearSystem2.addEquality(solverVariable5, createObjectVariable4, getBaselineDistance(), 6);
                                if (r15.mBaseline.mTarget != null) {
                                    linearSystem2.addEquality(solverVariable5, linearSystem2.createObjectVariable(r15.mBaseline.mTarget), 0, 6);
                                    z10 = false;
                                    if (r15.mParent == null) {
                                    }
                                    if (r15.mParent == null) {
                                    }
                                    z11 = z8;
                                    solverVariable6 = createObjectVariable4;
                                    applyConstraints(linearSystem, z11, r15.mParent == null ? linearSystem2.createObjectVariable(r15.mParent.mTop) : null, r15.mParent == null ? linearSystem2.createObjectVariable(r15.mParent.mBottom) : null, r15.mListDimensionBehaviors[1], z9, r15.mTop, r15.mBottom, r15.mY, i3, r15.mMinHeight, r15.mMaxDimension[1], r15.mVerticalBiasPercent, z4, z5, i, r15.mMatchConstraintMinHeight, r15.mMatchConstraintMaxHeight, r15.mMatchConstraintPercentHeight, z10);
                                    if (obj == null) {
                                        constraintWidget = this;
                                    } else if (this.mResolvedDimensionRatioSide != 1) {
                                        linearSystem.addRatio(solverVariable, solverVariable6, solverVariable4, createObjectVariable, constraintWidget.mResolvedDimensionRatio, 6);
                                    } else {
                                        linearSystem.addRatio(solverVariable4, createObjectVariable, solverVariable, solverVariable6, constraintWidget.mResolvedDimensionRatio, 6);
                                    }
                                    if (constraintWidget.mCenter.isConnected()) {
                                        linearSystem.addCenterPoint(constraintWidget, constraintWidget.mCenter.getTarget().getOwner(), (float) Math.toRadians((double) (constraintWidget.mCircleConstraintAngle + 90.0f)), constraintWidget.mCenter.getMargin());
                                    }
                                }
                            }
                            z10 = isConnected;
                            if (r15.mParent == null) {
                            }
                            if (r15.mParent == null) {
                            }
                            z11 = z8;
                            solverVariable6 = createObjectVariable4;
                            applyConstraints(linearSystem, z11, r15.mParent == null ? linearSystem2.createObjectVariable(r15.mParent.mTop) : null, r15.mParent == null ? linearSystem2.createObjectVariable(r15.mParent.mBottom) : null, r15.mListDimensionBehaviors[1], z9, r15.mTop, r15.mBottom, r15.mY, i3, r15.mMinHeight, r15.mMaxDimension[1], r15.mVerticalBiasPercent, z4, z5, i, r15.mMatchConstraintMinHeight, r15.mMatchConstraintMaxHeight, r15.mMatchConstraintPercentHeight, z10);
                            if (obj == null) {
                                constraintWidget = this;
                            } else if (this.mResolvedDimensionRatioSide != 1) {
                                linearSystem.addRatio(solverVariable4, createObjectVariable, solverVariable, solverVariable6, constraintWidget.mResolvedDimensionRatio, 6);
                            } else {
                                linearSystem.addRatio(solverVariable, solverVariable6, solverVariable4, createObjectVariable, constraintWidget.mResolvedDimensionRatio, 6);
                            }
                            if (constraintWidget.mCenter.isConnected()) {
                                linearSystem.addCenterPoint(constraintWidget, constraintWidget.mCenter.getTarget().getOwner(), (float) Math.toRadians((double) (constraintWidget.mCircleConstraintAngle + 90.0f)), constraintWidget.mCenter.getMargin());
                            }
                        }
                    }
                    z6 = false;
                    if (r15.mListDimensionBehaviors[0] != DimensionBehaviour.WRAP_CONTENT) {
                    }
                    isConnected = r15.mCenter.isConnected() ^ 1;
                    if (r15.mHorizontalResolution == 2) {
                        solverVariable3 = createObjectVariable3;
                        solverVariable4 = createObjectVariable2;
                        z8 = z3;
                        solverVariable2 = solverVariable;
                        solverVariable = createObjectVariable4;
                    } else {
                        if (r15.mParent == null) {
                        }
                        if (r15.mParent == null) {
                        }
                        z8 = z3;
                        solverVariable2 = solverVariable;
                        solverVariable = createObjectVariable4;
                        solverVariable3 = createObjectVariable3;
                        z9 = z7;
                        solverVariable4 = createObjectVariable2;
                        applyConstraints(linearSystem, z, r15.mParent == null ? linearSystem3.createObjectVariable(r15.mParent.mLeft) : null, r15.mParent == null ? linearSystem3.createObjectVariable(r15.mParent.mRight) : null, r15.mListDimensionBehaviors[0], z9, r15.mLeft, r15.mRight, r15.mX, i2, r15.mMinWidth, r15.mMaxDimension[0], r15.mHorizontalBiasPercent, z6, z4, i4, r15.mMatchConstraintMinWidth, r15.mMatchConstraintMaxWidth, r15.mMatchConstraintPercentWidth, isConnected);
                    }
                    if (this.mVerticalResolution != 2) {
                        if (r15.mListDimensionBehaviors[1] != DimensionBehaviour.WRAP_CONTENT) {
                        }
                        if (obj == null) {
                        }
                        if (r15.mBaselineDistance <= 0) {
                            createObjectVariable4 = solverVariable3;
                            linearSystem2 = linearSystem;
                        } else if (r15.mBaseline.getResolutionNode().state != 1) {
                            linearSystem2 = linearSystem;
                            solverVariable5 = solverVariable2;
                            createObjectVariable4 = solverVariable3;
                            linearSystem2.addEquality(solverVariable5, createObjectVariable4, getBaselineDistance(), 6);
                            if (r15.mBaseline.mTarget != null) {
                                linearSystem2.addEquality(solverVariable5, linearSystem2.createObjectVariable(r15.mBaseline.mTarget), 0, 6);
                                z10 = false;
                                if (r15.mParent == null) {
                                }
                                if (r15.mParent == null) {
                                }
                                z11 = z8;
                                solverVariable6 = createObjectVariable4;
                                applyConstraints(linearSystem, z11, r15.mParent == null ? linearSystem2.createObjectVariable(r15.mParent.mTop) : null, r15.mParent == null ? linearSystem2.createObjectVariable(r15.mParent.mBottom) : null, r15.mListDimensionBehaviors[1], z9, r15.mTop, r15.mBottom, r15.mY, i3, r15.mMinHeight, r15.mMaxDimension[1], r15.mVerticalBiasPercent, z4, z5, i, r15.mMatchConstraintMinHeight, r15.mMatchConstraintMaxHeight, r15.mMatchConstraintPercentHeight, z10);
                                if (obj == null) {
                                    constraintWidget = this;
                                } else if (this.mResolvedDimensionRatioSide != 1) {
                                    linearSystem.addRatio(solverVariable, solverVariable6, solverVariable4, createObjectVariable, constraintWidget.mResolvedDimensionRatio, 6);
                                } else {
                                    linearSystem.addRatio(solverVariable4, createObjectVariable, solverVariable, solverVariable6, constraintWidget.mResolvedDimensionRatio, 6);
                                }
                                if (constraintWidget.mCenter.isConnected()) {
                                    linearSystem.addCenterPoint(constraintWidget, constraintWidget.mCenter.getTarget().getOwner(), (float) Math.toRadians((double) (constraintWidget.mCircleConstraintAngle + 90.0f)), constraintWidget.mCenter.getMargin());
                                }
                            }
                        } else {
                            linearSystem2 = linearSystem;
                            r15.mBaseline.getResolutionNode().addResolvedValue(linearSystem2);
                            createObjectVariable4 = solverVariable3;
                        }
                        z10 = isConnected;
                        if (r15.mParent == null) {
                        }
                        if (r15.mParent == null) {
                        }
                        z11 = z8;
                        solverVariable6 = createObjectVariable4;
                        applyConstraints(linearSystem, z11, r15.mParent == null ? linearSystem2.createObjectVariable(r15.mParent.mTop) : null, r15.mParent == null ? linearSystem2.createObjectVariable(r15.mParent.mBottom) : null, r15.mListDimensionBehaviors[1], z9, r15.mTop, r15.mBottom, r15.mY, i3, r15.mMinHeight, r15.mMaxDimension[1], r15.mVerticalBiasPercent, z4, z5, i, r15.mMatchConstraintMinHeight, r15.mMatchConstraintMaxHeight, r15.mMatchConstraintPercentHeight, z10);
                        if (obj == null) {
                            constraintWidget = this;
                        } else if (this.mResolvedDimensionRatioSide != 1) {
                            linearSystem.addRatio(solverVariable4, createObjectVariable, solverVariable, solverVariable6, constraintWidget.mResolvedDimensionRatio, 6);
                        } else {
                            linearSystem.addRatio(solverVariable, solverVariable6, solverVariable4, createObjectVariable, constraintWidget.mResolvedDimensionRatio, 6);
                        }
                        if (constraintWidget.mCenter.isConnected()) {
                            linearSystem.addCenterPoint(constraintWidget, constraintWidget.mCenter.getTarget().getOwner(), (float) Math.toRadians((double) (constraintWidget.mCircleConstraintAngle + 90.0f)), constraintWidget.mCenter.getMargin());
                        }
                    }
                }
            } else if (r15.mListDimensionBehaviors[1] == DimensionBehaviour.MATCH_CONSTRAINT && i8 == 3) {
                r15.mResolvedDimensionRatioSide = 1;
                if (r15.mDimensionRatioSide == -1) {
                    r15.mResolvedDimensionRatio = 1.0f / r15.mResolvedDimensionRatio;
                }
                r1 = (int) (r15.mResolvedDimensionRatio * ((float) r15.mWidth));
                if (r15.mListDimensionBehaviors[0] != DimensionBehaviour.MATCH_CONSTRAINT) {
                    i3 = r1;
                    i2 = i5;
                    i4 = i7;
                    i = 4;
                } else {
                    i3 = r1;
                    i = i8;
                    i2 = i5;
                    i4 = i7;
                    obj = 1;
                    r15.mResolvedMatchConstraintDefault[0] = i4;
                    r15.mResolvedMatchConstraintDefault[1] = i;
                    if (obj == null) {
                        if (r15.mResolvedDimensionRatioSide == 0) {
                            if (r15.mResolvedDimensionRatioSide == -1) {
                            }
                        }
                        z6 = true;
                        z7 = r15.mListDimensionBehaviors[0] != DimensionBehaviour.WRAP_CONTENT && (r15 instanceof ConstraintWidgetContainer);
                        isConnected = r15.mCenter.isConnected() ^ 1;
                        if (r15.mHorizontalResolution == 2) {
                            z8 = z3;
                            solverVariable2 = solverVariable;
                            solverVariable = createObjectVariable4;
                            solverVariable3 = createObjectVariable3;
                            z9 = z7;
                            solverVariable4 = createObjectVariable2;
                            applyConstraints(linearSystem, z, r15.mParent == null ? linearSystem3.createObjectVariable(r15.mParent.mLeft) : null, r15.mParent == null ? linearSystem3.createObjectVariable(r15.mParent.mRight) : null, r15.mListDimensionBehaviors[0], z9, r15.mLeft, r15.mRight, r15.mX, i2, r15.mMinWidth, r15.mMaxDimension[0], r15.mHorizontalBiasPercent, z6, z4, i4, r15.mMatchConstraintMinWidth, r15.mMatchConstraintMaxWidth, r15.mMatchConstraintPercentWidth, isConnected);
                        } else {
                            solverVariable3 = createObjectVariable3;
                            solverVariable4 = createObjectVariable2;
                            z8 = z3;
                            solverVariable2 = solverVariable;
                            solverVariable = createObjectVariable4;
                        }
                        if (this.mVerticalResolution != 2) {
                            z9 = r15.mListDimensionBehaviors[1] != DimensionBehaviour.WRAP_CONTENT && (r15 instanceof ConstraintWidgetContainer);
                            z4 = obj == null && (r15.mResolvedDimensionRatioSide == 1 || r15.mResolvedDimensionRatioSide == -1);
                            if (r15.mBaselineDistance <= 0) {
                                createObjectVariable4 = solverVariable3;
                                linearSystem2 = linearSystem;
                            } else if (r15.mBaseline.getResolutionNode().state != 1) {
                                linearSystem2 = linearSystem;
                                r15.mBaseline.getResolutionNode().addResolvedValue(linearSystem2);
                                createObjectVariable4 = solverVariable3;
                            } else {
                                linearSystem2 = linearSystem;
                                solverVariable5 = solverVariable2;
                                createObjectVariable4 = solverVariable3;
                                linearSystem2.addEquality(solverVariable5, createObjectVariable4, getBaselineDistance(), 6);
                                if (r15.mBaseline.mTarget != null) {
                                    linearSystem2.addEquality(solverVariable5, linearSystem2.createObjectVariable(r15.mBaseline.mTarget), 0, 6);
                                    z10 = false;
                                    z11 = z8;
                                    solverVariable6 = createObjectVariable4;
                                    applyConstraints(linearSystem, z11, r15.mParent == null ? linearSystem2.createObjectVariable(r15.mParent.mTop) : null, r15.mParent == null ? linearSystem2.createObjectVariable(r15.mParent.mBottom) : null, r15.mListDimensionBehaviors[1], z9, r15.mTop, r15.mBottom, r15.mY, i3, r15.mMinHeight, r15.mMaxDimension[1], r15.mVerticalBiasPercent, z4, z5, i, r15.mMatchConstraintMinHeight, r15.mMatchConstraintMaxHeight, r15.mMatchConstraintPercentHeight, z10);
                                    if (obj == null) {
                                        constraintWidget = this;
                                    } else if (this.mResolvedDimensionRatioSide != 1) {
                                        linearSystem.addRatio(solverVariable, solverVariable6, solverVariable4, createObjectVariable, constraintWidget.mResolvedDimensionRatio, 6);
                                    } else {
                                        linearSystem.addRatio(solverVariable4, createObjectVariable, solverVariable, solverVariable6, constraintWidget.mResolvedDimensionRatio, 6);
                                    }
                                    if (constraintWidget.mCenter.isConnected()) {
                                        linearSystem.addCenterPoint(constraintWidget, constraintWidget.mCenter.getTarget().getOwner(), (float) Math.toRadians((double) (constraintWidget.mCircleConstraintAngle + 90.0f)), constraintWidget.mCenter.getMargin());
                                    }
                                }
                            }
                            z10 = isConnected;
                            if (r15.mParent == null) {
                            }
                            if (r15.mParent == null) {
                            }
                            z11 = z8;
                            solverVariable6 = createObjectVariable4;
                            applyConstraints(linearSystem, z11, r15.mParent == null ? linearSystem2.createObjectVariable(r15.mParent.mTop) : null, r15.mParent == null ? linearSystem2.createObjectVariable(r15.mParent.mBottom) : null, r15.mListDimensionBehaviors[1], z9, r15.mTop, r15.mBottom, r15.mY, i3, r15.mMinHeight, r15.mMaxDimension[1], r15.mVerticalBiasPercent, z4, z5, i, r15.mMatchConstraintMinHeight, r15.mMatchConstraintMaxHeight, r15.mMatchConstraintPercentHeight, z10);
                            if (obj == null) {
                                constraintWidget = this;
                            } else if (this.mResolvedDimensionRatioSide != 1) {
                                linearSystem.addRatio(solverVariable4, createObjectVariable, solverVariable, solverVariable6, constraintWidget.mResolvedDimensionRatio, 6);
                            } else {
                                linearSystem.addRatio(solverVariable, solverVariable6, solverVariable4, createObjectVariable, constraintWidget.mResolvedDimensionRatio, 6);
                            }
                            if (constraintWidget.mCenter.isConnected()) {
                                linearSystem.addCenterPoint(constraintWidget, constraintWidget.mCenter.getTarget().getOwner(), (float) Math.toRadians((double) (constraintWidget.mCircleConstraintAngle + 90.0f)), constraintWidget.mCenter.getMargin());
                            }
                        }
                    }
                    z6 = false;
                    if (r15.mListDimensionBehaviors[0] != DimensionBehaviour.WRAP_CONTENT) {
                    }
                    isConnected = r15.mCenter.isConnected() ^ 1;
                    if (r15.mHorizontalResolution == 2) {
                        solverVariable3 = createObjectVariable3;
                        solverVariable4 = createObjectVariable2;
                        z8 = z3;
                        solverVariable2 = solverVariable;
                        solverVariable = createObjectVariable4;
                    } else {
                        if (r15.mParent == null) {
                        }
                        if (r15.mParent == null) {
                        }
                        z8 = z3;
                        solverVariable2 = solverVariable;
                        solverVariable = createObjectVariable4;
                        solverVariable3 = createObjectVariable3;
                        z9 = z7;
                        solverVariable4 = createObjectVariable2;
                        applyConstraints(linearSystem, z, r15.mParent == null ? linearSystem3.createObjectVariable(r15.mParent.mLeft) : null, r15.mParent == null ? linearSystem3.createObjectVariable(r15.mParent.mRight) : null, r15.mListDimensionBehaviors[0], z9, r15.mLeft, r15.mRight, r15.mX, i2, r15.mMinWidth, r15.mMaxDimension[0], r15.mHorizontalBiasPercent, z6, z4, i4, r15.mMatchConstraintMinWidth, r15.mMatchConstraintMaxWidth, r15.mMatchConstraintPercentWidth, isConnected);
                    }
                    if (this.mVerticalResolution != 2) {
                        if (r15.mListDimensionBehaviors[1] != DimensionBehaviour.WRAP_CONTENT) {
                        }
                        if (obj == null) {
                        }
                        if (r15.mBaselineDistance <= 0) {
                            createObjectVariable4 = solverVariable3;
                            linearSystem2 = linearSystem;
                        } else if (r15.mBaseline.getResolutionNode().state != 1) {
                            linearSystem2 = linearSystem;
                            solverVariable5 = solverVariable2;
                            createObjectVariable4 = solverVariable3;
                            linearSystem2.addEquality(solverVariable5, createObjectVariable4, getBaselineDistance(), 6);
                            if (r15.mBaseline.mTarget != null) {
                                linearSystem2.addEquality(solverVariable5, linearSystem2.createObjectVariable(r15.mBaseline.mTarget), 0, 6);
                                z10 = false;
                                if (r15.mParent == null) {
                                }
                                if (r15.mParent == null) {
                                }
                                z11 = z8;
                                solverVariable6 = createObjectVariable4;
                                applyConstraints(linearSystem, z11, r15.mParent == null ? linearSystem2.createObjectVariable(r15.mParent.mTop) : null, r15.mParent == null ? linearSystem2.createObjectVariable(r15.mParent.mBottom) : null, r15.mListDimensionBehaviors[1], z9, r15.mTop, r15.mBottom, r15.mY, i3, r15.mMinHeight, r15.mMaxDimension[1], r15.mVerticalBiasPercent, z4, z5, i, r15.mMatchConstraintMinHeight, r15.mMatchConstraintMaxHeight, r15.mMatchConstraintPercentHeight, z10);
                                if (obj == null) {
                                    constraintWidget = this;
                                } else if (this.mResolvedDimensionRatioSide != 1) {
                                    linearSystem.addRatio(solverVariable, solverVariable6, solverVariable4, createObjectVariable, constraintWidget.mResolvedDimensionRatio, 6);
                                } else {
                                    linearSystem.addRatio(solverVariable4, createObjectVariable, solverVariable, solverVariable6, constraintWidget.mResolvedDimensionRatio, 6);
                                }
                                if (constraintWidget.mCenter.isConnected()) {
                                    linearSystem.addCenterPoint(constraintWidget, constraintWidget.mCenter.getTarget().getOwner(), (float) Math.toRadians((double) (constraintWidget.mCircleConstraintAngle + 90.0f)), constraintWidget.mCenter.getMargin());
                                }
                            }
                        } else {
                            linearSystem2 = linearSystem;
                            r15.mBaseline.getResolutionNode().addResolvedValue(linearSystem2);
                            createObjectVariable4 = solverVariable3;
                        }
                        z10 = isConnected;
                        if (r15.mParent == null) {
                        }
                        if (r15.mParent == null) {
                        }
                        z11 = z8;
                        solverVariable6 = createObjectVariable4;
                        applyConstraints(linearSystem, z11, r15.mParent == null ? linearSystem2.createObjectVariable(r15.mParent.mTop) : null, r15.mParent == null ? linearSystem2.createObjectVariable(r15.mParent.mBottom) : null, r15.mListDimensionBehaviors[1], z9, r15.mTop, r15.mBottom, r15.mY, i3, r15.mMinHeight, r15.mMaxDimension[1], r15.mVerticalBiasPercent, z4, z5, i, r15.mMatchConstraintMinHeight, r15.mMatchConstraintMaxHeight, r15.mMatchConstraintPercentHeight, z10);
                        if (obj == null) {
                            constraintWidget = this;
                        } else if (this.mResolvedDimensionRatioSide != 1) {
                            linearSystem.addRatio(solverVariable4, createObjectVariable, solverVariable, solverVariable6, constraintWidget.mResolvedDimensionRatio, 6);
                        } else {
                            linearSystem.addRatio(solverVariable, solverVariable6, solverVariable4, createObjectVariable, constraintWidget.mResolvedDimensionRatio, 6);
                        }
                        if (constraintWidget.mCenter.isConnected()) {
                            linearSystem.addCenterPoint(constraintWidget, constraintWidget.mCenter.getTarget().getOwner(), (float) Math.toRadians((double) (constraintWidget.mCircleConstraintAngle + 90.0f)), constraintWidget.mCenter.getMargin());
                        }
                    }
                }
            }
            i = i8;
            i2 = i5;
            i3 = i6;
            i4 = i7;
            obj = 1;
            r15.mResolvedMatchConstraintDefault[0] = i4;
            r15.mResolvedMatchConstraintDefault[1] = i;
            if (obj == null) {
                if (r15.mResolvedDimensionRatioSide == 0) {
                    if (r15.mResolvedDimensionRatioSide == -1) {
                    }
                }
                z6 = true;
                if (r15.mListDimensionBehaviors[0] != DimensionBehaviour.WRAP_CONTENT) {
                }
                isConnected = r15.mCenter.isConnected() ^ 1;
                if (r15.mHorizontalResolution == 2) {
                    if (r15.mParent == null) {
                    }
                    if (r15.mParent == null) {
                    }
                    z8 = z3;
                    solverVariable2 = solverVariable;
                    solverVariable = createObjectVariable4;
                    solverVariable3 = createObjectVariable3;
                    z9 = z7;
                    solverVariable4 = createObjectVariable2;
                    applyConstraints(linearSystem, z, r15.mParent == null ? linearSystem3.createObjectVariable(r15.mParent.mLeft) : null, r15.mParent == null ? linearSystem3.createObjectVariable(r15.mParent.mRight) : null, r15.mListDimensionBehaviors[0], z9, r15.mLeft, r15.mRight, r15.mX, i2, r15.mMinWidth, r15.mMaxDimension[0], r15.mHorizontalBiasPercent, z6, z4, i4, r15.mMatchConstraintMinWidth, r15.mMatchConstraintMaxWidth, r15.mMatchConstraintPercentWidth, isConnected);
                } else {
                    solverVariable3 = createObjectVariable3;
                    solverVariable4 = createObjectVariable2;
                    z8 = z3;
                    solverVariable2 = solverVariable;
                    solverVariable = createObjectVariable4;
                }
                if (this.mVerticalResolution != 2) {
                    if (r15.mListDimensionBehaviors[1] != DimensionBehaviour.WRAP_CONTENT) {
                    }
                    if (obj == null) {
                    }
                    if (r15.mBaselineDistance <= 0) {
                        createObjectVariable4 = solverVariable3;
                        linearSystem2 = linearSystem;
                    } else if (r15.mBaseline.getResolutionNode().state != 1) {
                        linearSystem2 = linearSystem;
                        r15.mBaseline.getResolutionNode().addResolvedValue(linearSystem2);
                        createObjectVariable4 = solverVariable3;
                    } else {
                        linearSystem2 = linearSystem;
                        solverVariable5 = solverVariable2;
                        createObjectVariable4 = solverVariable3;
                        linearSystem2.addEquality(solverVariable5, createObjectVariable4, getBaselineDistance(), 6);
                        if (r15.mBaseline.mTarget != null) {
                            linearSystem2.addEquality(solverVariable5, linearSystem2.createObjectVariable(r15.mBaseline.mTarget), 0, 6);
                            z10 = false;
                            if (r15.mParent == null) {
                            }
                            if (r15.mParent == null) {
                            }
                            z11 = z8;
                            solverVariable6 = createObjectVariable4;
                            applyConstraints(linearSystem, z11, r15.mParent == null ? linearSystem2.createObjectVariable(r15.mParent.mTop) : null, r15.mParent == null ? linearSystem2.createObjectVariable(r15.mParent.mBottom) : null, r15.mListDimensionBehaviors[1], z9, r15.mTop, r15.mBottom, r15.mY, i3, r15.mMinHeight, r15.mMaxDimension[1], r15.mVerticalBiasPercent, z4, z5, i, r15.mMatchConstraintMinHeight, r15.mMatchConstraintMaxHeight, r15.mMatchConstraintPercentHeight, z10);
                            if (obj == null) {
                                constraintWidget = this;
                            } else if (this.mResolvedDimensionRatioSide != 1) {
                                linearSystem.addRatio(solverVariable, solverVariable6, solverVariable4, createObjectVariable, constraintWidget.mResolvedDimensionRatio, 6);
                            } else {
                                linearSystem.addRatio(solverVariable4, createObjectVariable, solverVariable, solverVariable6, constraintWidget.mResolvedDimensionRatio, 6);
                            }
                            if (constraintWidget.mCenter.isConnected()) {
                                linearSystem.addCenterPoint(constraintWidget, constraintWidget.mCenter.getTarget().getOwner(), (float) Math.toRadians((double) (constraintWidget.mCircleConstraintAngle + 90.0f)), constraintWidget.mCenter.getMargin());
                            }
                        }
                    }
                    z10 = isConnected;
                    if (r15.mParent == null) {
                    }
                    if (r15.mParent == null) {
                    }
                    z11 = z8;
                    solverVariable6 = createObjectVariable4;
                    applyConstraints(linearSystem, z11, r15.mParent == null ? linearSystem2.createObjectVariable(r15.mParent.mTop) : null, r15.mParent == null ? linearSystem2.createObjectVariable(r15.mParent.mBottom) : null, r15.mListDimensionBehaviors[1], z9, r15.mTop, r15.mBottom, r15.mY, i3, r15.mMinHeight, r15.mMaxDimension[1], r15.mVerticalBiasPercent, z4, z5, i, r15.mMatchConstraintMinHeight, r15.mMatchConstraintMaxHeight, r15.mMatchConstraintPercentHeight, z10);
                    if (obj == null) {
                        constraintWidget = this;
                    } else if (this.mResolvedDimensionRatioSide != 1) {
                        linearSystem.addRatio(solverVariable4, createObjectVariable, solverVariable, solverVariable6, constraintWidget.mResolvedDimensionRatio, 6);
                    } else {
                        linearSystem.addRatio(solverVariable, solverVariable6, solverVariable4, createObjectVariable, constraintWidget.mResolvedDimensionRatio, 6);
                    }
                    if (constraintWidget.mCenter.isConnected()) {
                        linearSystem.addCenterPoint(constraintWidget, constraintWidget.mCenter.getTarget().getOwner(), (float) Math.toRadians((double) (constraintWidget.mCircleConstraintAngle + 90.0f)), constraintWidget.mCenter.getMargin());
                    }
                }
            }
            z6 = false;
            if (r15.mListDimensionBehaviors[0] != DimensionBehaviour.WRAP_CONTENT) {
            }
            isConnected = r15.mCenter.isConnected() ^ 1;
            if (r15.mHorizontalResolution == 2) {
                solverVariable3 = createObjectVariable3;
                solverVariable4 = createObjectVariable2;
                z8 = z3;
                solverVariable2 = solverVariable;
                solverVariable = createObjectVariable4;
            } else {
                if (r15.mParent == null) {
                }
                if (r15.mParent == null) {
                }
                z8 = z3;
                solverVariable2 = solverVariable;
                solverVariable = createObjectVariable4;
                solverVariable3 = createObjectVariable3;
                z9 = z7;
                solverVariable4 = createObjectVariable2;
                applyConstraints(linearSystem, z, r15.mParent == null ? linearSystem3.createObjectVariable(r15.mParent.mLeft) : null, r15.mParent == null ? linearSystem3.createObjectVariable(r15.mParent.mRight) : null, r15.mListDimensionBehaviors[0], z9, r15.mLeft, r15.mRight, r15.mX, i2, r15.mMinWidth, r15.mMaxDimension[0], r15.mHorizontalBiasPercent, z6, z4, i4, r15.mMatchConstraintMinWidth, r15.mMatchConstraintMaxWidth, r15.mMatchConstraintPercentWidth, isConnected);
            }
            if (this.mVerticalResolution != 2) {
                if (r15.mListDimensionBehaviors[1] != DimensionBehaviour.WRAP_CONTENT) {
                }
                if (obj == null) {
                }
                if (r15.mBaselineDistance <= 0) {
                    createObjectVariable4 = solverVariable3;
                    linearSystem2 = linearSystem;
                } else if (r15.mBaseline.getResolutionNode().state != 1) {
                    linearSystem2 = linearSystem;
                    solverVariable5 = solverVariable2;
                    createObjectVariable4 = solverVariable3;
                    linearSystem2.addEquality(solverVariable5, createObjectVariable4, getBaselineDistance(), 6);
                    if (r15.mBaseline.mTarget != null) {
                        linearSystem2.addEquality(solverVariable5, linearSystem2.createObjectVariable(r15.mBaseline.mTarget), 0, 6);
                        z10 = false;
                        if (r15.mParent == null) {
                        }
                        if (r15.mParent == null) {
                        }
                        z11 = z8;
                        solverVariable6 = createObjectVariable4;
                        applyConstraints(linearSystem, z11, r15.mParent == null ? linearSystem2.createObjectVariable(r15.mParent.mTop) : null, r15.mParent == null ? linearSystem2.createObjectVariable(r15.mParent.mBottom) : null, r15.mListDimensionBehaviors[1], z9, r15.mTop, r15.mBottom, r15.mY, i3, r15.mMinHeight, r15.mMaxDimension[1], r15.mVerticalBiasPercent, z4, z5, i, r15.mMatchConstraintMinHeight, r15.mMatchConstraintMaxHeight, r15.mMatchConstraintPercentHeight, z10);
                        if (obj == null) {
                            constraintWidget = this;
                        } else if (this.mResolvedDimensionRatioSide != 1) {
                            linearSystem.addRatio(solverVariable, solverVariable6, solverVariable4, createObjectVariable, constraintWidget.mResolvedDimensionRatio, 6);
                        } else {
                            linearSystem.addRatio(solverVariable4, createObjectVariable, solverVariable, solverVariable6, constraintWidget.mResolvedDimensionRatio, 6);
                        }
                        if (constraintWidget.mCenter.isConnected()) {
                            linearSystem.addCenterPoint(constraintWidget, constraintWidget.mCenter.getTarget().getOwner(), (float) Math.toRadians((double) (constraintWidget.mCircleConstraintAngle + 90.0f)), constraintWidget.mCenter.getMargin());
                        }
                    }
                } else {
                    linearSystem2 = linearSystem;
                    r15.mBaseline.getResolutionNode().addResolvedValue(linearSystem2);
                    createObjectVariable4 = solverVariable3;
                }
                z10 = isConnected;
                if (r15.mParent == null) {
                }
                if (r15.mParent == null) {
                }
                z11 = z8;
                solverVariable6 = createObjectVariable4;
                applyConstraints(linearSystem, z11, r15.mParent == null ? linearSystem2.createObjectVariable(r15.mParent.mTop) : null, r15.mParent == null ? linearSystem2.createObjectVariable(r15.mParent.mBottom) : null, r15.mListDimensionBehaviors[1], z9, r15.mTop, r15.mBottom, r15.mY, i3, r15.mMinHeight, r15.mMaxDimension[1], r15.mVerticalBiasPercent, z4, z5, i, r15.mMatchConstraintMinHeight, r15.mMatchConstraintMaxHeight, r15.mMatchConstraintPercentHeight, z10);
                if (obj == null) {
                    constraintWidget = this;
                } else if (this.mResolvedDimensionRatioSide != 1) {
                    linearSystem.addRatio(solverVariable4, createObjectVariable, solverVariable, solverVariable6, constraintWidget.mResolvedDimensionRatio, 6);
                } else {
                    linearSystem.addRatio(solverVariable, solverVariable6, solverVariable4, createObjectVariable, constraintWidget.mResolvedDimensionRatio, 6);
                }
                if (constraintWidget.mCenter.isConnected()) {
                    linearSystem.addCenterPoint(constraintWidget, constraintWidget.mCenter.getTarget().getOwner(), (float) Math.toRadians((double) (constraintWidget.mCircleConstraintAngle + 90.0f)), constraintWidget.mCenter.getMargin());
                }
            }
        }
        obj = null;
        r15.mResolvedMatchConstraintDefault[0] = i4;
        r15.mResolvedMatchConstraintDefault[1] = i;
        if (obj == null) {
            if (r15.mResolvedDimensionRatioSide == 0) {
                if (r15.mResolvedDimensionRatioSide == -1) {
                }
            }
            z6 = true;
            if (r15.mListDimensionBehaviors[0] != DimensionBehaviour.WRAP_CONTENT) {
            }
            isConnected = r15.mCenter.isConnected() ^ 1;
            if (r15.mHorizontalResolution == 2) {
                if (r15.mParent == null) {
                }
                if (r15.mParent == null) {
                }
                z8 = z3;
                solverVariable2 = solverVariable;
                solverVariable = createObjectVariable4;
                solverVariable3 = createObjectVariable3;
                z9 = z7;
                solverVariable4 = createObjectVariable2;
                applyConstraints(linearSystem, z, r15.mParent == null ? linearSystem3.createObjectVariable(r15.mParent.mLeft) : null, r15.mParent == null ? linearSystem3.createObjectVariable(r15.mParent.mRight) : null, r15.mListDimensionBehaviors[0], z9, r15.mLeft, r15.mRight, r15.mX, i2, r15.mMinWidth, r15.mMaxDimension[0], r15.mHorizontalBiasPercent, z6, z4, i4, r15.mMatchConstraintMinWidth, r15.mMatchConstraintMaxWidth, r15.mMatchConstraintPercentWidth, isConnected);
            } else {
                solverVariable3 = createObjectVariable3;
                solverVariable4 = createObjectVariable2;
                z8 = z3;
                solverVariable2 = solverVariable;
                solverVariable = createObjectVariable4;
            }
            if (this.mVerticalResolution != 2) {
                if (r15.mListDimensionBehaviors[1] != DimensionBehaviour.WRAP_CONTENT) {
                }
                if (obj == null) {
                }
                if (r15.mBaselineDistance <= 0) {
                    createObjectVariable4 = solverVariable3;
                    linearSystem2 = linearSystem;
                } else if (r15.mBaseline.getResolutionNode().state != 1) {
                    linearSystem2 = linearSystem;
                    r15.mBaseline.getResolutionNode().addResolvedValue(linearSystem2);
                    createObjectVariable4 = solverVariable3;
                } else {
                    linearSystem2 = linearSystem;
                    solverVariable5 = solverVariable2;
                    createObjectVariable4 = solverVariable3;
                    linearSystem2.addEquality(solverVariable5, createObjectVariable4, getBaselineDistance(), 6);
                    if (r15.mBaseline.mTarget != null) {
                        linearSystem2.addEquality(solverVariable5, linearSystem2.createObjectVariable(r15.mBaseline.mTarget), 0, 6);
                        z10 = false;
                        if (r15.mParent == null) {
                        }
                        if (r15.mParent == null) {
                        }
                        z11 = z8;
                        solverVariable6 = createObjectVariable4;
                        applyConstraints(linearSystem, z11, r15.mParent == null ? linearSystem2.createObjectVariable(r15.mParent.mTop) : null, r15.mParent == null ? linearSystem2.createObjectVariable(r15.mParent.mBottom) : null, r15.mListDimensionBehaviors[1], z9, r15.mTop, r15.mBottom, r15.mY, i3, r15.mMinHeight, r15.mMaxDimension[1], r15.mVerticalBiasPercent, z4, z5, i, r15.mMatchConstraintMinHeight, r15.mMatchConstraintMaxHeight, r15.mMatchConstraintPercentHeight, z10);
                        if (obj == null) {
                            constraintWidget = this;
                        } else if (this.mResolvedDimensionRatioSide != 1) {
                            linearSystem.addRatio(solverVariable, solverVariable6, solverVariable4, createObjectVariable, constraintWidget.mResolvedDimensionRatio, 6);
                        } else {
                            linearSystem.addRatio(solverVariable4, createObjectVariable, solverVariable, solverVariable6, constraintWidget.mResolvedDimensionRatio, 6);
                        }
                        if (constraintWidget.mCenter.isConnected()) {
                            linearSystem.addCenterPoint(constraintWidget, constraintWidget.mCenter.getTarget().getOwner(), (float) Math.toRadians((double) (constraintWidget.mCircleConstraintAngle + 90.0f)), constraintWidget.mCenter.getMargin());
                        }
                    }
                }
                z10 = isConnected;
                if (r15.mParent == null) {
                }
                if (r15.mParent == null) {
                }
                z11 = z8;
                solverVariable6 = createObjectVariable4;
                applyConstraints(linearSystem, z11, r15.mParent == null ? linearSystem2.createObjectVariable(r15.mParent.mTop) : null, r15.mParent == null ? linearSystem2.createObjectVariable(r15.mParent.mBottom) : null, r15.mListDimensionBehaviors[1], z9, r15.mTop, r15.mBottom, r15.mY, i3, r15.mMinHeight, r15.mMaxDimension[1], r15.mVerticalBiasPercent, z4, z5, i, r15.mMatchConstraintMinHeight, r15.mMatchConstraintMaxHeight, r15.mMatchConstraintPercentHeight, z10);
                if (obj == null) {
                    constraintWidget = this;
                } else if (this.mResolvedDimensionRatioSide != 1) {
                    linearSystem.addRatio(solverVariable4, createObjectVariable, solverVariable, solverVariable6, constraintWidget.mResolvedDimensionRatio, 6);
                } else {
                    linearSystem.addRatio(solverVariable, solverVariable6, solverVariable4, createObjectVariable, constraintWidget.mResolvedDimensionRatio, 6);
                }
                if (constraintWidget.mCenter.isConnected()) {
                    linearSystem.addCenterPoint(constraintWidget, constraintWidget.mCenter.getTarget().getOwner(), (float) Math.toRadians((double) (constraintWidget.mCircleConstraintAngle + 90.0f)), constraintWidget.mCenter.getMargin());
                }
            }
        }
        z6 = false;
        if (r15.mListDimensionBehaviors[0] != DimensionBehaviour.WRAP_CONTENT) {
        }
        isConnected = r15.mCenter.isConnected() ^ 1;
        if (r15.mHorizontalResolution == 2) {
            solverVariable3 = createObjectVariable3;
            solverVariable4 = createObjectVariable2;
            z8 = z3;
            solverVariable2 = solverVariable;
            solverVariable = createObjectVariable4;
        } else {
            if (r15.mParent == null) {
            }
            if (r15.mParent == null) {
            }
            z8 = z3;
            solverVariable2 = solverVariable;
            solverVariable = createObjectVariable4;
            solverVariable3 = createObjectVariable3;
            z9 = z7;
            solverVariable4 = createObjectVariable2;
            applyConstraints(linearSystem, z, r15.mParent == null ? linearSystem3.createObjectVariable(r15.mParent.mLeft) : null, r15.mParent == null ? linearSystem3.createObjectVariable(r15.mParent.mRight) : null, r15.mListDimensionBehaviors[0], z9, r15.mLeft, r15.mRight, r15.mX, i2, r15.mMinWidth, r15.mMaxDimension[0], r15.mHorizontalBiasPercent, z6, z4, i4, r15.mMatchConstraintMinWidth, r15.mMatchConstraintMaxWidth, r15.mMatchConstraintPercentWidth, isConnected);
        }
        if (this.mVerticalResolution != 2) {
            if (r15.mListDimensionBehaviors[1] != DimensionBehaviour.WRAP_CONTENT) {
            }
            if (obj == null) {
            }
            if (r15.mBaselineDistance <= 0) {
                createObjectVariable4 = solverVariable3;
                linearSystem2 = linearSystem;
            } else if (r15.mBaseline.getResolutionNode().state != 1) {
                linearSystem2 = linearSystem;
                solverVariable5 = solverVariable2;
                createObjectVariable4 = solverVariable3;
                linearSystem2.addEquality(solverVariable5, createObjectVariable4, getBaselineDistance(), 6);
                if (r15.mBaseline.mTarget != null) {
                    linearSystem2.addEquality(solverVariable5, linearSystem2.createObjectVariable(r15.mBaseline.mTarget), 0, 6);
                    z10 = false;
                    if (r15.mParent == null) {
                    }
                    if (r15.mParent == null) {
                    }
                    z11 = z8;
                    solverVariable6 = createObjectVariable4;
                    applyConstraints(linearSystem, z11, r15.mParent == null ? linearSystem2.createObjectVariable(r15.mParent.mTop) : null, r15.mParent == null ? linearSystem2.createObjectVariable(r15.mParent.mBottom) : null, r15.mListDimensionBehaviors[1], z9, r15.mTop, r15.mBottom, r15.mY, i3, r15.mMinHeight, r15.mMaxDimension[1], r15.mVerticalBiasPercent, z4, z5, i, r15.mMatchConstraintMinHeight, r15.mMatchConstraintMaxHeight, r15.mMatchConstraintPercentHeight, z10);
                    if (obj == null) {
                        constraintWidget = this;
                    } else if (this.mResolvedDimensionRatioSide != 1) {
                        linearSystem.addRatio(solverVariable, solverVariable6, solverVariable4, createObjectVariable, constraintWidget.mResolvedDimensionRatio, 6);
                    } else {
                        linearSystem.addRatio(solverVariable4, createObjectVariable, solverVariable, solverVariable6, constraintWidget.mResolvedDimensionRatio, 6);
                    }
                    if (constraintWidget.mCenter.isConnected()) {
                        linearSystem.addCenterPoint(constraintWidget, constraintWidget.mCenter.getTarget().getOwner(), (float) Math.toRadians((double) (constraintWidget.mCircleConstraintAngle + 90.0f)), constraintWidget.mCenter.getMargin());
                    }
                }
            } else {
                linearSystem2 = linearSystem;
                r15.mBaseline.getResolutionNode().addResolvedValue(linearSystem2);
                createObjectVariable4 = solverVariable3;
            }
            z10 = isConnected;
            if (r15.mParent == null) {
            }
            if (r15.mParent == null) {
            }
            z11 = z8;
            solverVariable6 = createObjectVariable4;
            applyConstraints(linearSystem, z11, r15.mParent == null ? linearSystem2.createObjectVariable(r15.mParent.mTop) : null, r15.mParent == null ? linearSystem2.createObjectVariable(r15.mParent.mBottom) : null, r15.mListDimensionBehaviors[1], z9, r15.mTop, r15.mBottom, r15.mY, i3, r15.mMinHeight, r15.mMaxDimension[1], r15.mVerticalBiasPercent, z4, z5, i, r15.mMatchConstraintMinHeight, r15.mMatchConstraintMaxHeight, r15.mMatchConstraintPercentHeight, z10);
            if (obj == null) {
                constraintWidget = this;
            } else if (this.mResolvedDimensionRatioSide != 1) {
                linearSystem.addRatio(solverVariable4, createObjectVariable, solverVariable, solverVariable6, constraintWidget.mResolvedDimensionRatio, 6);
            } else {
                linearSystem.addRatio(solverVariable, solverVariable6, solverVariable4, createObjectVariable, constraintWidget.mResolvedDimensionRatio, 6);
            }
            if (constraintWidget.mCenter.isConnected()) {
                linearSystem.addCenterPoint(constraintWidget, constraintWidget.mCenter.getTarget().getOwner(), (float) Math.toRadians((double) (constraintWidget.mCircleConstraintAngle + 90.0f)), constraintWidget.mCenter.getMargin());
            }
        }
    }

    public void setupDimensionRatio(boolean z, boolean z2, boolean z3, boolean z4) {
        if (this.mResolvedDimensionRatioSide == -1) {
            if (z3 && !z4) {
                this.mResolvedDimensionRatioSide = 0;
            } else if (!z3 && z4) {
                this.mResolvedDimensionRatioSide = 1;
                if (this.mDimensionRatioSide) {
                    this.mResolvedDimensionRatio = true / this.mResolvedDimensionRatio;
                }
            }
        }
        if (!this.mResolvedDimensionRatioSide && (!this.mTop.isConnected() || !this.mBottom.isConnected())) {
            this.mResolvedDimensionRatioSide = 1;
        } else if (this.mResolvedDimensionRatioSide && !(this.mLeft.isConnected() && this.mRight.isConnected())) {
            this.mResolvedDimensionRatioSide = 0;
        }
        if (this.mResolvedDimensionRatioSide && !(this.mTop.isConnected() && this.mBottom.isConnected() && this.mLeft.isConnected() && this.mRight.isConnected())) {
            if (this.mTop.isConnected() && this.mBottom.isConnected()) {
                this.mResolvedDimensionRatioSide = 0;
            } else if (this.mLeft.isConnected() && this.mRight.isConnected()) {
                this.mResolvedDimensionRatio = true / this.mResolvedDimensionRatio;
                this.mResolvedDimensionRatioSide = 1;
            }
        }
        if (this.mResolvedDimensionRatioSide) {
            if (z && !z2) {
                this.mResolvedDimensionRatioSide = 0;
            } else if (!z && z2) {
                this.mResolvedDimensionRatio = true / this.mResolvedDimensionRatio;
                this.mResolvedDimensionRatioSide = 1;
            }
        }
        if (this.mResolvedDimensionRatioSide) {
            if (this.mMatchConstraintMinWidth <= false && !this.mMatchConstraintMinHeight) {
                this.mResolvedDimensionRatioSide = 0;
            } else if (!this.mMatchConstraintMinWidth && this.mMatchConstraintMinHeight <= false) {
                this.mResolvedDimensionRatio = true / this.mResolvedDimensionRatio;
                this.mResolvedDimensionRatioSide = 1;
            }
        }
        if (this.mResolvedDimensionRatioSide && z && z2) {
            this.mResolvedDimensionRatio = 1.0f / this.mResolvedDimensionRatio;
            this.mResolvedDimensionRatioSide = 1;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void applyConstraints(android.support.constraint.solver.LinearSystem r31, boolean r32, android.support.constraint.solver.SolverVariable r33, android.support.constraint.solver.SolverVariable r34, android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour r35, boolean r36, android.support.constraint.solver.widgets.ConstraintAnchor r37, android.support.constraint.solver.widgets.ConstraintAnchor r38, int r39, int r40, int r41, int r42, float r43, boolean r44, boolean r45, int r46, int r47, int r48, float r49, boolean r50) {
        /*
        r30 = this;
        r0 = r30;
        r10 = r31;
        r11 = r33;
        r12 = r34;
        r13 = r37;
        r14 = r38;
        r1 = r41;
        r2 = r42;
        r9 = r10.createObjectVariable(r13);
        r8 = r10.createObjectVariable(r14);
        r6 = r37.getTarget();
        r7 = r10.createObjectVariable(r6);
        r6 = r38.getTarget();
        r6 = r10.createObjectVariable(r6);
        r14 = r10.graphOptimizer;
        r15 = 1;
        if (r14 == 0) goto L_0x0066;
    L_0x002e:
        r14 = r37.getResolutionNode();
        r14 = r14.state;
        r13 = 1;
        if (r14 != r13) goto L_0x0066;
    L_0x0037:
        r14 = r38.getResolutionNode();
        r14 = r14.state;
        if (r14 != r13) goto L_0x0066;
    L_0x003f:
        r1 = android.support.constraint.solver.LinearSystem.getMetrics();
        if (r1 == 0) goto L_0x004e;
    L_0x0045:
        r1 = android.support.constraint.solver.LinearSystem.getMetrics();
        r2 = r1.resolvedWidgets;
        r2 = r2 + r15;
        r1.resolvedWidgets = r2;
    L_0x004e:
        r1 = r37.getResolutionNode();
        r1.addResolvedValue(r10);
        r1 = r38.getResolutionNode();
        r1.addResolvedValue(r10);
        if (r45 != 0) goto L_0x0065;
    L_0x005e:
        if (r32 == 0) goto L_0x0065;
    L_0x0060:
        r1 = 0;
        r2 = 6;
        r10.addGreaterThan(r12, r8, r1, r2);
    L_0x0065:
        return;
    L_0x0066:
        r13 = android.support.constraint.solver.LinearSystem.getMetrics();
        if (r13 == 0) goto L_0x0075;
    L_0x006c:
        r13 = android.support.constraint.solver.LinearSystem.getMetrics();
        r11 = r13.nonresolvedWidgets;
        r11 = r11 + r15;
        r13.nonresolvedWidgets = r11;
    L_0x0075:
        r11 = r37.isConnected();
        r12 = r38.isConnected();
        r13 = r0.mCenter;
        r13 = r13.isConnected();
        if (r11 == 0) goto L_0x0087;
    L_0x0085:
        r14 = 1;
        goto L_0x0088;
    L_0x0087:
        r14 = 0;
    L_0x0088:
        if (r12 == 0) goto L_0x008c;
    L_0x008a:
        r14 = r14 + 1;
    L_0x008c:
        if (r13 == 0) goto L_0x0090;
    L_0x008e:
        r14 = r14 + 1;
    L_0x0090:
        r21 = r6;
        if (r44 == 0) goto L_0x0096;
    L_0x0094:
        r6 = 3;
        goto L_0x0098;
    L_0x0096:
        r6 = r46;
    L_0x0098:
        r15 = android.support.constraint.solver.widgets.ConstraintWidget.C00191.f2x27577131;
        r16 = r35.ordinal();
        r15 = r15[r16];
        r3 = 4;
        switch(r15) {
            case 1: goto L_0x00a4;
            case 2: goto L_0x00a4;
            case 3: goto L_0x00a4;
            case 4: goto L_0x00a6;
            default: goto L_0x00a4;
        };
    L_0x00a4:
        r15 = 0;
        goto L_0x00aa;
    L_0x00a6:
        if (r6 != r3) goto L_0x00a9;
    L_0x00a8:
        goto L_0x00a4;
    L_0x00a9:
        r15 = 1;
    L_0x00aa:
        r3 = r0.mVisibility;
        r22 = r14;
        r14 = 8;
        if (r3 != r14) goto L_0x00b5;
    L_0x00b2:
        r3 = 0;
        r15 = 0;
        goto L_0x00b7;
    L_0x00b5:
        r3 = r40;
    L_0x00b7:
        if (r50 == 0) goto L_0x00d4;
    L_0x00b9:
        if (r11 != 0) goto L_0x00c5;
    L_0x00bb:
        if (r12 != 0) goto L_0x00c5;
    L_0x00bd:
        if (r13 != 0) goto L_0x00c5;
    L_0x00bf:
        r14 = r39;
        r10.addEquality(r9, r14);
        goto L_0x00d4;
    L_0x00c5:
        if (r11 == 0) goto L_0x00d4;
    L_0x00c7:
        if (r12 != 0) goto L_0x00d4;
    L_0x00c9:
        r14 = r37.getMargin();
        r23 = r13;
        r13 = 6;
        r10.addEquality(r9, r7, r14, r13);
        goto L_0x00d7;
    L_0x00d4:
        r23 = r13;
        r13 = 6;
    L_0x00d7:
        if (r15 != 0) goto L_0x0100;
    L_0x00d9:
        if (r36 == 0) goto L_0x00f1;
    L_0x00db:
        r13 = 3;
        r14 = 0;
        r10.addEquality(r8, r9, r14, r13);
        if (r1 <= 0) goto L_0x00e7;
    L_0x00e2:
        r13 = 6;
        r10.addGreaterThan(r8, r9, r1, r13);
        goto L_0x00e8;
    L_0x00e7:
        r13 = 6;
    L_0x00e8:
        r3 = 2147483647; // 0x7fffffff float:NaN double:1.060997895E-314;
        if (r2 >= r3) goto L_0x00f4;
    L_0x00ed:
        r10.addLowerThan(r8, r9, r2, r13);
        goto L_0x00f4;
    L_0x00f1:
        r10.addEquality(r8, r9, r3, r13);
    L_0x00f4:
        r27 = r47;
        r14 = r48;
        r24 = r7;
    L_0x00fa:
        r2 = r22;
    L_0x00fc:
        r16 = 4;
        goto L_0x01cc;
    L_0x0100:
        r2 = -2;
        r14 = r47;
        if (r14 != r2) goto L_0x0109;
    L_0x0105:
        r14 = r48;
        r13 = r3;
        goto L_0x010c;
    L_0x0109:
        r13 = r14;
        r14 = r48;
    L_0x010c:
        if (r14 != r2) goto L_0x010f;
    L_0x010e:
        r14 = r3;
    L_0x010f:
        if (r13 <= 0) goto L_0x011a;
    L_0x0111:
        r2 = 6;
        r10.addGreaterThan(r8, r9, r13, r2);
        r3 = java.lang.Math.max(r3, r13);
        goto L_0x011b;
    L_0x011a:
        r2 = 6;
    L_0x011b:
        if (r14 <= 0) goto L_0x0124;
    L_0x011d:
        r10.addLowerThan(r8, r9, r14, r2);
        r3 = java.lang.Math.min(r3, r14);
    L_0x0124:
        r2 = 1;
        if (r6 != r2) goto L_0x013c;
    L_0x0127:
        if (r32 == 0) goto L_0x012f;
    L_0x0129:
        r2 = 6;
        r10.addEquality(r8, r9, r3, r2);
        goto L_0x01a2;
    L_0x012f:
        if (r45 == 0) goto L_0x0137;
    L_0x0131:
        r2 = 4;
        r10.addEquality(r8, r9, r3, r2);
        goto L_0x01a2;
    L_0x0137:
        r2 = 1;
        r10.addEquality(r8, r9, r3, r2);
        goto L_0x01a2;
    L_0x013c:
        r2 = 2;
        if (r6 != r2) goto L_0x01a2;
    L_0x013f:
        r2 = r37.getType();
        r24 = r7;
        r7 = android.support.constraint.solver.widgets.ConstraintAnchor.Type.TOP;
        if (r2 == r7) goto L_0x0171;
    L_0x0149:
        r2 = r37.getType();
        r7 = android.support.constraint.solver.widgets.ConstraintAnchor.Type.BOTTOM;
        if (r2 != r7) goto L_0x0152;
    L_0x0151:
        goto L_0x0171;
    L_0x0152:
        r2 = r0.mParent;
        r7 = android.support.constraint.solver.widgets.ConstraintAnchor.Type.LEFT;
        r2 = r2.getAnchor(r7);
        r2 = r10.createObjectVariable(r2);
        r7 = r0.mParent;
        r25 = r2;
        r2 = android.support.constraint.solver.widgets.ConstraintAnchor.Type.RIGHT;
        r2 = r7.getAnchor(r2);
        r2 = r10.createObjectVariable(r2);
        r18 = r2;
        r19 = r25;
        goto L_0x018f;
    L_0x0171:
        r2 = r0.mParent;
        r7 = android.support.constraint.solver.widgets.ConstraintAnchor.Type.TOP;
        r2 = r2.getAnchor(r7);
        r2 = r10.createObjectVariable(r2);
        r7 = r0.mParent;
        r26 = r2;
        r2 = android.support.constraint.solver.widgets.ConstraintAnchor.Type.BOTTOM;
        r2 = r7.getAnchor(r2);
        r2 = r10.createObjectVariable(r2);
        r18 = r2;
        r19 = r26;
    L_0x018f:
        r15 = r31.createRow();
        r16 = r8;
        r17 = r9;
        r20 = r49;
        r2 = r15.createRowDimensionRatio(r16, r17, r18, r19, r20);
        r10.addConstraint(r2);
        r15 = 0;
        goto L_0x01a4;
    L_0x01a2:
        r24 = r7;
    L_0x01a4:
        if (r15 == 0) goto L_0x01c8;
    L_0x01a6:
        r2 = r22;
        r7 = 2;
        if (r2 == r7) goto L_0x01c4;
    L_0x01ab:
        r16 = 4;
        if (r44 != 0) goto L_0x01c1;
    L_0x01af:
        r3 = java.lang.Math.max(r13, r3);
        if (r14 <= 0) goto L_0x01b9;
    L_0x01b5:
        r3 = java.lang.Math.min(r14, r3);
    L_0x01b9:
        r27 = r13;
        r13 = 6;
        r10.addEquality(r8, r9, r3, r13);
        r15 = 0;
        goto L_0x01cc;
    L_0x01c1:
        r27 = r13;
        goto L_0x01cc;
    L_0x01c4:
        r27 = r13;
        goto L_0x00fc;
    L_0x01c8:
        r27 = r13;
        goto L_0x00fa;
    L_0x01cc:
        if (r50 == 0) goto L_0x0313;
    L_0x01ce:
        if (r45 == 0) goto L_0x01db;
    L_0x01d0:
        r0 = r8;
        r5 = r9;
        r1 = 0;
        r3 = 6;
        r4 = r34;
        r6 = 2;
        r11 = r33;
        goto L_0x031c;
    L_0x01db:
        r13 = 5;
        if (r11 != 0) goto L_0x01f2;
    L_0x01de:
        if (r12 != 0) goto L_0x01f2;
    L_0x01e0:
        if (r23 != 0) goto L_0x01f2;
    L_0x01e2:
        if (r32 == 0) goto L_0x01eb;
    L_0x01e4:
        r2 = 0;
        r5 = r34;
        r10.addGreaterThan(r5, r8, r2, r13);
        goto L_0x01ed;
    L_0x01eb:
        r5 = r34;
    L_0x01ed:
        r0 = r8;
    L_0x01ee:
        r1 = 0;
        r3 = 6;
        goto L_0x030b;
    L_0x01f2:
        r2 = 0;
        r5 = r34;
        if (r11 == 0) goto L_0x01ff;
    L_0x01f7:
        if (r12 != 0) goto L_0x01ff;
    L_0x01f9:
        if (r32 == 0) goto L_0x01ed;
    L_0x01fb:
        r10.addGreaterThan(r5, r8, r2, r13);
        goto L_0x01ed;
    L_0x01ff:
        if (r11 != 0) goto L_0x0216;
    L_0x0201:
        if (r12 == 0) goto L_0x0216;
    L_0x0203:
        r1 = r38.getMargin();
        r1 = -r1;
        r4 = r21;
        r3 = 6;
        r10.addEquality(r8, r4, r1, r3);
        if (r32 == 0) goto L_0x01ed;
    L_0x0210:
        r3 = r33;
        r10.addGreaterThan(r9, r3, r2, r13);
        goto L_0x01ed;
    L_0x0216:
        r4 = r21;
        r3 = r33;
        if (r11 == 0) goto L_0x01ed;
    L_0x021c:
        if (r12 == 0) goto L_0x01ed;
    L_0x021e:
        if (r15 == 0) goto L_0x027f;
    L_0x0220:
        if (r32 == 0) goto L_0x0228;
    L_0x0222:
        if (r1 != 0) goto L_0x0228;
    L_0x0224:
        r1 = 6;
        r10.addGreaterThan(r8, r9, r2, r1);
    L_0x0228:
        if (r6 != 0) goto L_0x0252;
    L_0x022a:
        if (r14 > 0) goto L_0x0232;
    L_0x022c:
        if (r27 <= 0) goto L_0x022f;
    L_0x022e:
        goto L_0x0232;
    L_0x022f:
        r1 = 6;
        r2 = 0;
        goto L_0x0234;
    L_0x0232:
        r1 = 4;
        r2 = 1;
    L_0x0234:
        r6 = r37.getMargin();
        r11 = r24;
        r10.addEquality(r9, r11, r6, r1);
        r6 = r38.getMargin();
        r6 = -r6;
        r10.addEquality(r8, r4, r6, r1);
        if (r14 > 0) goto L_0x024c;
    L_0x0247:
        if (r27 <= 0) goto L_0x024a;
    L_0x0249:
        goto L_0x024c;
    L_0x024a:
        r1 = 0;
        goto L_0x024d;
    L_0x024c:
        r1 = 1;
    L_0x024d:
        r16 = r2;
        r12 = 1;
        r14 = 5;
        goto L_0x0286;
    L_0x0252:
        r11 = r24;
        r12 = 1;
        if (r6 != r12) goto L_0x025c;
    L_0x0257:
        r1 = 1;
        r14 = 6;
    L_0x0259:
        r16 = 1;
        goto L_0x0286;
    L_0x025c:
        r1 = 3;
        if (r6 != r1) goto L_0x027d;
    L_0x025f:
        if (r44 != 0) goto L_0x026a;
    L_0x0261:
        r1 = r0.mResolvedDimensionRatioSide;
        r2 = -1;
        if (r1 == r2) goto L_0x026a;
    L_0x0266:
        if (r14 > 0) goto L_0x026a;
    L_0x0268:
        r1 = 6;
        goto L_0x026b;
    L_0x026a:
        r1 = 4;
    L_0x026b:
        r2 = r37.getMargin();
        r10.addEquality(r9, r11, r2, r1);
        r2 = r38.getMargin();
        r2 = -r2;
        r10.addEquality(r8, r4, r2, r1);
        r1 = 1;
        r14 = 5;
        goto L_0x0259;
    L_0x027d:
        r1 = 0;
        goto L_0x0283;
    L_0x027f:
        r11 = r24;
        r12 = 1;
        r1 = 1;
    L_0x0283:
        r14 = 5;
        r16 = 0;
    L_0x0286:
        if (r1 == 0) goto L_0x02cf;
    L_0x0288:
        r6 = r37.getMargin();
        r17 = r38.getMargin();
        r1 = r31;
        r2 = r9;
        r7 = r3;
        r3 = r11;
        r18 = r4;
        r4 = r6;
        r6 = r5;
        r5 = r43;
        r12 = r6;
        r28 = r18;
        r6 = r28;
        r13 = r11;
        r11 = r7;
        r7 = r8;
        r0 = r8;
        r8 = r17;
        r12 = r9;
        r9 = r14;
        r1.addCentering(r2, r3, r4, r5, r6, r7, r8, r9);
        r1 = r37;
        r2 = 1;
        r3 = r1.mTarget;
        r3 = r3.mOwner;
        r3 = r3 instanceof android.support.constraint.solver.widgets.Barrier;
        r4 = r38;
        r5 = r4.mTarget;
        r5 = r5.mOwner;
        r5 = r5 instanceof android.support.constraint.solver.widgets.Barrier;
        if (r3 == 0) goto L_0x02c5;
    L_0x02be:
        if (r5 != 0) goto L_0x02c5;
    L_0x02c0:
        r2 = r32;
        r3 = 1;
        r5 = 6;
        goto L_0x02dd;
    L_0x02c5:
        if (r3 != 0) goto L_0x02d9;
    L_0x02c7:
        if (r5 == 0) goto L_0x02d9;
    L_0x02c9:
        r3 = r32;
        r5 = 5;
        r29 = 6;
        goto L_0x02df;
    L_0x02cf:
        r28 = r4;
        r0 = r8;
        r12 = r9;
        r13 = r11;
        r1 = r37;
        r4 = r38;
        r11 = r3;
    L_0x02d9:
        r2 = r32;
        r3 = r2;
        r5 = 5;
    L_0x02dd:
        r29 = 5;
    L_0x02df:
        if (r16 == 0) goto L_0x02e4;
    L_0x02e1:
        r5 = 6;
        r6 = 6;
        goto L_0x02e7;
    L_0x02e4:
        r6 = r5;
        r5 = r29;
    L_0x02e7:
        if (r15 != 0) goto L_0x02eb;
    L_0x02e9:
        if (r2 != 0) goto L_0x02ed;
    L_0x02eb:
        if (r16 == 0) goto L_0x02f4;
    L_0x02ed:
        r1 = r37.getMargin();
        r10.addGreaterThan(r12, r13, r1, r5);
    L_0x02f4:
        if (r15 != 0) goto L_0x02f8;
    L_0x02f6:
        if (r3 != 0) goto L_0x02fa;
    L_0x02f8:
        if (r16 == 0) goto L_0x0304;
    L_0x02fa:
        r1 = r38.getMargin();
        r1 = -r1;
        r2 = r28;
        r10.addLowerThan(r0, r2, r1, r6);
    L_0x0304:
        if (r32 == 0) goto L_0x01ee;
    L_0x0306:
        r1 = 0;
        r3 = 6;
        r10.addGreaterThan(r12, r11, r1, r3);
    L_0x030b:
        if (r32 == 0) goto L_0x0312;
    L_0x030d:
        r4 = r34;
        r10.addGreaterThan(r4, r0, r1, r3);
    L_0x0312:
        return;
    L_0x0313:
        r0 = r8;
        r5 = r9;
        r1 = 0;
        r3 = 6;
        r4 = r34;
        r11 = r33;
        r6 = 2;
    L_0x031c:
        if (r2 >= r6) goto L_0x0326;
    L_0x031e:
        if (r32 == 0) goto L_0x0326;
    L_0x0320:
        r10.addGreaterThan(r5, r11, r1, r3);
        r10.addGreaterThan(r4, r0, r1, r3);
    L_0x0326:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.solver.widgets.ConstraintWidget.applyConstraints(android.support.constraint.solver.LinearSystem, boolean, android.support.constraint.solver.SolverVariable, android.support.constraint.solver.SolverVariable, android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour, boolean, android.support.constraint.solver.widgets.ConstraintAnchor, android.support.constraint.solver.widgets.ConstraintAnchor, int, int, int, int, float, boolean, boolean, int, int, int, float, boolean):void");
    }

    public void updateFromSolver(LinearSystem linearSystem) {
        int objectVariableValue = linearSystem.getObjectVariableValue(this.mLeft);
        int objectVariableValue2 = linearSystem.getObjectVariableValue(this.mTop);
        int objectVariableValue3 = linearSystem.getObjectVariableValue(this.mRight);
        linearSystem = linearSystem.getObjectVariableValue(this.mBottom);
        int i = linearSystem - objectVariableValue2;
        if (objectVariableValue3 - objectVariableValue < 0 || i < 0 || objectVariableValue == Integer.MIN_VALUE || objectVariableValue == Integer.MAX_VALUE || objectVariableValue2 == Integer.MIN_VALUE || objectVariableValue2 == Integer.MAX_VALUE || objectVariableValue3 == Integer.MIN_VALUE || objectVariableValue3 == Integer.MAX_VALUE || linearSystem == -2147483648 || linearSystem == 2147483647) {
            linearSystem = null;
            objectVariableValue = 0;
            objectVariableValue2 = 0;
            objectVariableValue3 = 0;
        }
        setFrame(objectVariableValue, objectVariableValue2, objectVariableValue3, linearSystem);
    }
}
