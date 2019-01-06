package android.support.constraint.solver.widgets;

import android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour;
import java.util.ArrayList;

public class ChainHead {
    private boolean mDefined;
    protected ConstraintWidget mFirst;
    protected ConstraintWidget mFirstMatchConstraintWidget;
    protected ConstraintWidget mFirstVisibleWidget;
    protected boolean mHasComplexMatchWeights;
    protected boolean mHasDefinedWeights;
    protected boolean mHasUndefinedWeights;
    protected ConstraintWidget mHead;
    private boolean mIsRtl = false;
    protected ConstraintWidget mLast;
    protected ConstraintWidget mLastMatchConstraintWidget;
    protected ConstraintWidget mLastVisibleWidget;
    private int mOrientation;
    protected float mTotalWeight = 0.0f;
    protected ArrayList<ConstraintWidget> mWeightedMatchConstraintsWidgets;
    protected int mWidgetsCount;
    protected int mWidgetsMatchCount;

    public ChainHead(ConstraintWidget constraintWidget, int i, boolean z) {
        this.mFirst = constraintWidget;
        this.mOrientation = i;
        this.mIsRtl = z;
    }

    private static boolean isMatchConstraintEqualityCandidate(ConstraintWidget constraintWidget, int i) {
        return (constraintWidget.getVisibility() != 8 && constraintWidget.mListDimensionBehaviors[i] == DimensionBehaviour.MATCH_CONSTRAINT && (constraintWidget.mResolvedMatchConstraintDefault[i] == 0 || constraintWidget.mResolvedMatchConstraintDefault[i] == 3)) ? true : null;
    }

    private void defineChainProperties() {
        int i = this.mOrientation * 2;
        ConstraintWidget constraintWidget = this.mFirst;
        ConstraintWidget constraintWidget2 = this.mFirst;
        ConstraintWidget constraintWidget3 = this.mFirst;
        boolean z = false;
        ConstraintWidget constraintWidget4 = constraintWidget;
        Object obj = null;
        while (obj == null) {
            this.mWidgetsCount++;
            ConstraintWidget constraintWidget5 = null;
            constraintWidget2.mNextChainWidget[this.mOrientation] = null;
            constraintWidget2.mListNextMatchConstraintsWidget[this.mOrientation] = null;
            if (constraintWidget2.getVisibility() != 8) {
                if (this.mFirstVisibleWidget == null) {
                    this.mFirstVisibleWidget = constraintWidget2;
                }
                this.mLastVisibleWidget = constraintWidget2;
                if (constraintWidget2.mListDimensionBehaviors[this.mOrientation] == DimensionBehaviour.MATCH_CONSTRAINT && (constraintWidget2.mResolvedMatchConstraintDefault[this.mOrientation] == 0 || constraintWidget2.mResolvedMatchConstraintDefault[this.mOrientation] == 3 || constraintWidget2.mResolvedMatchConstraintDefault[this.mOrientation] == 2)) {
                    this.mWidgetsMatchCount++;
                    float f = constraintWidget2.mWeight[this.mOrientation];
                    if (f > 0.0f) {
                        this.mTotalWeight += constraintWidget2.mWeight[this.mOrientation];
                    }
                    if (isMatchConstraintEqualityCandidate(constraintWidget2, this.mOrientation)) {
                        if (f < 0.0f) {
                            this.mHasUndefinedWeights = true;
                        } else {
                            this.mHasDefinedWeights = true;
                        }
                        if (this.mWeightedMatchConstraintsWidgets == null) {
                            this.mWeightedMatchConstraintsWidgets = new ArrayList();
                        }
                        this.mWeightedMatchConstraintsWidgets.add(constraintWidget2);
                    }
                    if (this.mFirstMatchConstraintWidget == null) {
                        this.mFirstMatchConstraintWidget = constraintWidget2;
                    }
                    if (this.mLastMatchConstraintWidget != null) {
                        this.mLastMatchConstraintWidget.mListNextMatchConstraintsWidget[this.mOrientation] = constraintWidget2;
                    }
                    this.mLastMatchConstraintWidget = constraintWidget2;
                }
            }
            if (constraintWidget4 != constraintWidget2) {
                constraintWidget4.mNextChainWidget[this.mOrientation] = constraintWidget2;
            }
            ConstraintAnchor constraintAnchor = constraintWidget2.mListAnchors[i + 1].mTarget;
            if (constraintAnchor != null) {
                constraintWidget4 = constraintAnchor.mOwner;
                if (constraintWidget4.mListAnchors[i].mTarget != null) {
                    if (constraintWidget4.mListAnchors[i].mTarget.mOwner == constraintWidget2) {
                        constraintWidget5 = constraintWidget4;
                    }
                }
            }
            if (constraintWidget5 == null) {
                constraintWidget5 = constraintWidget2;
                obj = 1;
            }
            constraintWidget4 = constraintWidget2;
            constraintWidget2 = constraintWidget5;
        }
        this.mLast = constraintWidget2;
        if (this.mOrientation == 0 && this.mIsRtl) {
            this.mHead = this.mLast;
        } else {
            this.mHead = this.mFirst;
        }
        if (this.mHasDefinedWeights && this.mHasUndefinedWeights) {
            z = true;
        }
        this.mHasComplexMatchWeights = z;
    }

    public ConstraintWidget getFirst() {
        return this.mFirst;
    }

    public ConstraintWidget getFirstVisibleWidget() {
        return this.mFirstVisibleWidget;
    }

    public ConstraintWidget getLast() {
        return this.mLast;
    }

    public ConstraintWidget getLastVisibleWidget() {
        return this.mLastVisibleWidget;
    }

    public ConstraintWidget getHead() {
        return this.mHead;
    }

    public ConstraintWidget getFirstMatchConstraintWidget() {
        return this.mFirstMatchConstraintWidget;
    }

    public ConstraintWidget getLastMatchConstraintWidget() {
        return this.mLastMatchConstraintWidget;
    }

    public float getTotalWeight() {
        return this.mTotalWeight;
    }

    public void define() {
        if (!this.mDefined) {
            defineChainProperties();
        }
        this.mDefined = true;
    }
}
