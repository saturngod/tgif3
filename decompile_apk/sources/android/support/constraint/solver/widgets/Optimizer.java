package android.support.constraint.solver.widgets;

import android.support.constraint.solver.LinearSystem;
import android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour;

public class Optimizer {
    static final int FLAG_CHAIN_DANGLING = 1;
    static final int FLAG_RECOMPUTE_BOUNDS = 2;
    static final int FLAG_USE_OPTIMIZE = 0;
    public static final int OPTIMIZATION_BARRIER = 2;
    public static final int OPTIMIZATION_CHAIN = 4;
    public static final int OPTIMIZATION_DIMENSIONS = 8;
    public static final int OPTIMIZATION_DIRECT = 1;
    public static final int OPTIMIZATION_GROUPS = 32;
    public static final int OPTIMIZATION_NONE = 0;
    public static final int OPTIMIZATION_RATIO = 16;
    public static final int OPTIMIZATION_STANDARD = 7;
    static boolean[] flags = new boolean[3];

    static void checkMatchParent(ConstraintWidgetContainer constraintWidgetContainer, LinearSystem linearSystem, ConstraintWidget constraintWidget) {
        if (constraintWidgetContainer.mListDimensionBehaviors[0] != DimensionBehaviour.WRAP_CONTENT && constraintWidget.mListDimensionBehaviors[0] == DimensionBehaviour.MATCH_PARENT) {
            int i = constraintWidget.mLeft.mMargin;
            int width = constraintWidgetContainer.getWidth() - constraintWidget.mRight.mMargin;
            constraintWidget.mLeft.mSolverVariable = linearSystem.createObjectVariable(constraintWidget.mLeft);
            constraintWidget.mRight.mSolverVariable = linearSystem.createObjectVariable(constraintWidget.mRight);
            linearSystem.addEquality(constraintWidget.mLeft.mSolverVariable, i);
            linearSystem.addEquality(constraintWidget.mRight.mSolverVariable, width);
            constraintWidget.mHorizontalResolution = 2;
            constraintWidget.setHorizontalDimension(i, width);
        }
        if (constraintWidgetContainer.mListDimensionBehaviors[1] != DimensionBehaviour.WRAP_CONTENT && constraintWidget.mListDimensionBehaviors[1] == DimensionBehaviour.MATCH_PARENT) {
            i = constraintWidget.mTop.mMargin;
            constraintWidgetContainer = constraintWidgetContainer.getHeight() - constraintWidget.mBottom.mMargin;
            constraintWidget.mTop.mSolverVariable = linearSystem.createObjectVariable(constraintWidget.mTop);
            constraintWidget.mBottom.mSolverVariable = linearSystem.createObjectVariable(constraintWidget.mBottom);
            linearSystem.addEquality(constraintWidget.mTop.mSolverVariable, i);
            linearSystem.addEquality(constraintWidget.mBottom.mSolverVariable, constraintWidgetContainer);
            if (constraintWidget.mBaselineDistance > 0 || constraintWidget.getVisibility() == 8) {
                constraintWidget.mBaseline.mSolverVariable = linearSystem.createObjectVariable(constraintWidget.mBaseline);
                linearSystem.addEquality(constraintWidget.mBaseline.mSolverVariable, constraintWidget.mBaselineDistance + i);
            }
            constraintWidget.mVerticalResolution = 2;
            constraintWidget.setVerticalDimension(i, constraintWidgetContainer);
        }
    }

    private static boolean optimizableMatchConstraint(ConstraintWidget constraintWidget, int i) {
        if (constraintWidget.mListDimensionBehaviors[i] != DimensionBehaviour.MATCH_CONSTRAINT) {
            return false;
        }
        int i2 = 1;
        if (constraintWidget.mDimensionRatio != 0.0f) {
            constraintWidget = constraintWidget.mListDimensionBehaviors;
            if (i != 0) {
                i2 = 0;
            }
            return constraintWidget[i2] == DimensionBehaviour.MATCH_CONSTRAINT ? false : false;
        } else {
            if (i != 0) {
                if (constraintWidget.mMatchConstraintDefaultHeight == 0 && constraintWidget.mMatchConstraintMinHeight == 0) {
                    if (constraintWidget.mMatchConstraintMaxHeight != null) {
                    }
                }
                return false;
            } else if (constraintWidget.mMatchConstraintDefaultWidth == 0 && constraintWidget.mMatchConstraintMinWidth == 0 && constraintWidget.mMatchConstraintMaxWidth == null) {
                return true;
            } else {
                return false;
            }
            return true;
        }
    }

    static void analyze(int i, ConstraintWidget constraintWidget) {
        ConstraintWidget constraintWidget2 = constraintWidget;
        constraintWidget.updateResolutionNodes();
        ResolutionAnchor resolutionNode = constraintWidget2.mLeft.getResolutionNode();
        ResolutionAnchor resolutionNode2 = constraintWidget2.mTop.getResolutionNode();
        ResolutionAnchor resolutionNode3 = constraintWidget2.mRight.getResolutionNode();
        ResolutionAnchor resolutionNode4 = constraintWidget2.mBottom.getResolutionNode();
        Object obj = (i & 8) == 8 ? 1 : null;
        Object obj2 = (constraintWidget2.mListDimensionBehaviors[0] == DimensionBehaviour.MATCH_CONSTRAINT && optimizableMatchConstraint(constraintWidget2, 0)) ? 1 : null;
        if (!(resolutionNode.type == 4 || resolutionNode3.type == 4)) {
            if (constraintWidget2.mListDimensionBehaviors[0] != DimensionBehaviour.FIXED) {
                if (obj2 == null || constraintWidget.getVisibility() != 8) {
                    if (obj2 != null) {
                        int width = constraintWidget.getWidth();
                        resolutionNode.setType(1);
                        resolutionNode3.setType(1);
                        if (constraintWidget2.mLeft.mTarget == null && constraintWidget2.mRight.mTarget == null) {
                            if (obj != null) {
                                resolutionNode3.dependsOn(resolutionNode, 1, constraintWidget.getResolutionWidth());
                            } else {
                                resolutionNode3.dependsOn(resolutionNode, width);
                            }
                        } else if (constraintWidget2.mLeft.mTarget == null || constraintWidget2.mRight.mTarget != null) {
                            if (constraintWidget2.mLeft.mTarget != null || constraintWidget2.mRight.mTarget == null) {
                                if (!(constraintWidget2.mLeft.mTarget == null || constraintWidget2.mRight.mTarget == null)) {
                                    if (obj != null) {
                                        constraintWidget.getResolutionWidth().addDependent(resolutionNode);
                                        constraintWidget.getResolutionWidth().addDependent(resolutionNode3);
                                    }
                                    if (constraintWidget2.mDimensionRatio == 0.0f) {
                                        resolutionNode.setType(3);
                                        resolutionNode3.setType(3);
                                        resolutionNode.setOpposite(resolutionNode3, 0.0f);
                                        resolutionNode3.setOpposite(resolutionNode, 0.0f);
                                    } else {
                                        resolutionNode.setType(2);
                                        resolutionNode3.setType(2);
                                        resolutionNode.setOpposite(resolutionNode3, (float) (-width));
                                        resolutionNode3.setOpposite(resolutionNode, (float) width);
                                        constraintWidget2.setWidth(width);
                                    }
                                }
                            } else if (obj != null) {
                                resolutionNode.dependsOn(resolutionNode3, -1, constraintWidget.getResolutionWidth());
                            } else {
                                resolutionNode.dependsOn(resolutionNode3, -width);
                            }
                        } else if (obj != null) {
                            resolutionNode3.dependsOn(resolutionNode, 1, constraintWidget.getResolutionWidth());
                        } else {
                            resolutionNode3.dependsOn(resolutionNode, width);
                        }
                    }
                }
            }
            if (constraintWidget2.mLeft.mTarget == null && constraintWidget2.mRight.mTarget == null) {
                resolutionNode.setType(1);
                resolutionNode3.setType(1);
                if (obj != null) {
                    resolutionNode3.dependsOn(resolutionNode, 1, constraintWidget.getResolutionWidth());
                } else {
                    resolutionNode3.dependsOn(resolutionNode, constraintWidget.getWidth());
                }
            } else if (constraintWidget2.mLeft.mTarget != null && constraintWidget2.mRight.mTarget == null) {
                resolutionNode.setType(1);
                resolutionNode3.setType(1);
                if (obj != null) {
                    resolutionNode3.dependsOn(resolutionNode, 1, constraintWidget.getResolutionWidth());
                } else {
                    resolutionNode3.dependsOn(resolutionNode, constraintWidget.getWidth());
                }
            } else if (constraintWidget2.mLeft.mTarget == null && constraintWidget2.mRight.mTarget != null) {
                resolutionNode.setType(1);
                resolutionNode3.setType(1);
                resolutionNode.dependsOn(resolutionNode3, -constraintWidget.getWidth());
                if (obj != null) {
                    resolutionNode.dependsOn(resolutionNode3, -1, constraintWidget.getResolutionWidth());
                } else {
                    resolutionNode.dependsOn(resolutionNode3, -constraintWidget.getWidth());
                }
            } else if (!(constraintWidget2.mLeft.mTarget == null || constraintWidget2.mRight.mTarget == null)) {
                resolutionNode.setType(2);
                resolutionNode3.setType(2);
                if (obj != null) {
                    constraintWidget.getResolutionWidth().addDependent(resolutionNode);
                    constraintWidget.getResolutionWidth().addDependent(resolutionNode3);
                    resolutionNode.setOpposite(resolutionNode3, -1, constraintWidget.getResolutionWidth());
                    resolutionNode3.setOpposite(resolutionNode, 1, constraintWidget.getResolutionWidth());
                } else {
                    resolutionNode.setOpposite(resolutionNode3, (float) (-constraintWidget.getWidth()));
                    resolutionNode3.setOpposite(resolutionNode, (float) constraintWidget.getWidth());
                }
            }
        }
        Object obj3 = (constraintWidget2.mListDimensionBehaviors[1] == DimensionBehaviour.MATCH_CONSTRAINT && optimizableMatchConstraint(constraintWidget2, 1)) ? 1 : null;
        if (resolutionNode2.type != 4 && resolutionNode4.type != 4) {
            if (constraintWidget2.mListDimensionBehaviors[1] != DimensionBehaviour.FIXED) {
                if (obj3 == null || constraintWidget.getVisibility() != 8) {
                    if (obj3 != null) {
                        int height = constraintWidget.getHeight();
                        resolutionNode2.setType(1);
                        resolutionNode4.setType(1);
                        if (constraintWidget2.mTop.mTarget == null && constraintWidget2.mBottom.mTarget == null) {
                            if (obj != null) {
                                resolutionNode4.dependsOn(resolutionNode2, 1, constraintWidget.getResolutionHeight());
                                return;
                            } else {
                                resolutionNode4.dependsOn(resolutionNode2, height);
                                return;
                            }
                        } else if (constraintWidget2.mTop.mTarget == null || constraintWidget2.mBottom.mTarget != null) {
                            if (constraintWidget2.mTop.mTarget != null || constraintWidget2.mBottom.mTarget == null) {
                                if (constraintWidget2.mTop.mTarget != null && constraintWidget2.mBottom.mTarget != null) {
                                    if (obj != null) {
                                        constraintWidget.getResolutionHeight().addDependent(resolutionNode2);
                                        constraintWidget.getResolutionWidth().addDependent(resolutionNode4);
                                    }
                                    if (constraintWidget2.mDimensionRatio == 0.0f) {
                                        resolutionNode2.setType(3);
                                        resolutionNode4.setType(3);
                                        resolutionNode2.setOpposite(resolutionNode4, 0.0f);
                                        resolutionNode4.setOpposite(resolutionNode2, 0.0f);
                                        return;
                                    }
                                    resolutionNode2.setType(2);
                                    resolutionNode4.setType(2);
                                    resolutionNode2.setOpposite(resolutionNode4, (float) (-height));
                                    resolutionNode4.setOpposite(resolutionNode2, (float) height);
                                    constraintWidget2.setHeight(height);
                                    if (constraintWidget2.mBaselineDistance > 0) {
                                        constraintWidget2.mBaseline.getResolutionNode().dependsOn(1, resolutionNode2, constraintWidget2.mBaselineDistance);
                                        return;
                                    }
                                    return;
                                }
                                return;
                            } else if (obj != null) {
                                resolutionNode2.dependsOn(resolutionNode4, -1, constraintWidget.getResolutionHeight());
                                return;
                            } else {
                                resolutionNode2.dependsOn(resolutionNode4, -height);
                                return;
                            }
                        } else if (obj != null) {
                            resolutionNode4.dependsOn(resolutionNode2, 1, constraintWidget.getResolutionHeight());
                            return;
                        } else {
                            resolutionNode4.dependsOn(resolutionNode2, height);
                            return;
                        }
                    }
                    return;
                }
            }
            if (constraintWidget2.mTop.mTarget == null && constraintWidget2.mBottom.mTarget == null) {
                resolutionNode2.setType(1);
                resolutionNode4.setType(1);
                if (obj != null) {
                    resolutionNode4.dependsOn(resolutionNode2, 1, constraintWidget.getResolutionHeight());
                } else {
                    resolutionNode4.dependsOn(resolutionNode2, constraintWidget.getHeight());
                }
                if (constraintWidget2.mBaseline.mTarget != null) {
                    constraintWidget2.mBaseline.getResolutionNode().setType(1);
                    resolutionNode2.dependsOn(1, constraintWidget2.mBaseline.getResolutionNode(), -constraintWidget2.mBaselineDistance);
                }
            } else if (constraintWidget2.mTop.mTarget != null && constraintWidget2.mBottom.mTarget == null) {
                resolutionNode2.setType(1);
                resolutionNode4.setType(1);
                if (obj != null) {
                    resolutionNode4.dependsOn(resolutionNode2, 1, constraintWidget.getResolutionHeight());
                } else {
                    resolutionNode4.dependsOn(resolutionNode2, constraintWidget.getHeight());
                }
                if (constraintWidget2.mBaselineDistance > 0) {
                    constraintWidget2.mBaseline.getResolutionNode().dependsOn(1, resolutionNode2, constraintWidget2.mBaselineDistance);
                }
            } else if (constraintWidget2.mTop.mTarget == null && constraintWidget2.mBottom.mTarget != null) {
                resolutionNode2.setType(1);
                resolutionNode4.setType(1);
                if (obj != null) {
                    resolutionNode2.dependsOn(resolutionNode4, -1, constraintWidget.getResolutionHeight());
                } else {
                    resolutionNode2.dependsOn(resolutionNode4, -constraintWidget.getHeight());
                }
                if (constraintWidget2.mBaselineDistance > 0) {
                    constraintWidget2.mBaseline.getResolutionNode().dependsOn(1, resolutionNode2, constraintWidget2.mBaselineDistance);
                }
            } else if (constraintWidget2.mTop.mTarget != null && constraintWidget2.mBottom.mTarget != null) {
                resolutionNode2.setType(2);
                resolutionNode4.setType(2);
                if (obj != null) {
                    resolutionNode2.setOpposite(resolutionNode4, -1, constraintWidget.getResolutionHeight());
                    resolutionNode4.setOpposite(resolutionNode2, 1, constraintWidget.getResolutionHeight());
                    constraintWidget.getResolutionHeight().addDependent(resolutionNode2);
                    constraintWidget.getResolutionWidth().addDependent(resolutionNode4);
                } else {
                    resolutionNode2.setOpposite(resolutionNode4, (float) (-constraintWidget.getHeight()));
                    resolutionNode4.setOpposite(resolutionNode2, (float) constraintWidget.getHeight());
                }
                if (constraintWidget2.mBaselineDistance > 0) {
                    constraintWidget2.mBaseline.getResolutionNode().dependsOn(1, resolutionNode2, constraintWidget2.mBaselineDistance);
                }
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static boolean applyChainOptimized(android.support.constraint.solver.widgets.ConstraintWidgetContainer r22, android.support.constraint.solver.LinearSystem r23, int r24, int r25, android.support.constraint.solver.widgets.ChainHead r26) {
        /*
        r0 = r23;
        r1 = r24;
        r2 = r26;
        r3 = r2.mFirst;
        r4 = r2.mLast;
        r5 = r2.mFirstVisibleWidget;
        r6 = r2.mLastVisibleWidget;
        r7 = r2.mHead;
        r8 = r2.mTotalWeight;
        r9 = r2.mFirstMatchConstraintWidget;
        r2 = r2.mLastMatchConstraintWidget;
        r9 = r22;
        r2 = r9.mListDimensionBehaviors;
        r2 = r2[r1];
        r9 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        r2 = 2;
        r10 = 1;
        if (r1 != 0) goto L_0x0038;
    L_0x0022:
        r11 = r7.mHorizontalChainStyle;
        if (r11 != 0) goto L_0x0028;
    L_0x0026:
        r11 = 1;
        goto L_0x0029;
    L_0x0028:
        r11 = 0;
    L_0x0029:
        r12 = r7.mHorizontalChainStyle;
        if (r12 != r10) goto L_0x002f;
    L_0x002d:
        r12 = 1;
        goto L_0x0030;
    L_0x002f:
        r12 = 0;
    L_0x0030:
        r7 = r7.mHorizontalChainStyle;
        if (r7 != r2) goto L_0x0036;
    L_0x0034:
        r2 = 1;
        goto L_0x004b;
    L_0x0036:
        r2 = 0;
        goto L_0x004b;
    L_0x0038:
        r11 = r7.mVerticalChainStyle;
        if (r11 != 0) goto L_0x003e;
    L_0x003c:
        r11 = 1;
        goto L_0x003f;
    L_0x003e:
        r11 = 0;
    L_0x003f:
        r12 = r7.mVerticalChainStyle;
        if (r12 != r10) goto L_0x0045;
    L_0x0043:
        r12 = 1;
        goto L_0x0046;
    L_0x0045:
        r12 = 0;
    L_0x0046:
        r7 = r7.mVerticalChainStyle;
        if (r7 != r2) goto L_0x0036;
    L_0x004a:
        goto L_0x0034;
    L_0x004b:
        r14 = r3;
        r7 = 0;
        r10 = 0;
        r13 = 0;
        r15 = 0;
        r17 = 0;
    L_0x0052:
        r9 = 8;
        if (r13 != 0) goto L_0x010f;
    L_0x0056:
        r18 = r13;
        r13 = r14.getVisibility();
        if (r13 == r9) goto L_0x00a1;
    L_0x005e:
        r10 = r10 + 1;
        if (r1 != 0) goto L_0x0069;
    L_0x0062:
        r13 = r14.getWidth();
        r13 = (float) r13;
        r15 = r15 + r13;
        goto L_0x006f;
    L_0x0069:
        r13 = r14.getHeight();
        r13 = (float) r13;
        r15 = r15 + r13;
    L_0x006f:
        if (r14 == r5) goto L_0x007b;
    L_0x0071:
        r13 = r14.mListAnchors;
        r13 = r13[r25];
        r13 = r13.getMargin();
        r13 = (float) r13;
        r15 = r15 + r13;
    L_0x007b:
        if (r14 == r6) goto L_0x0089;
    L_0x007d:
        r13 = r14.mListAnchors;
        r19 = r25 + 1;
        r13 = r13[r19];
        r13 = r13.getMargin();
        r13 = (float) r13;
        r15 = r15 + r13;
    L_0x0089:
        r13 = r14.mListAnchors;
        r13 = r13[r25];
        r13 = r13.getMargin();
        r13 = (float) r13;
        r17 = r17 + r13;
        r13 = r14.mListAnchors;
        r19 = r25 + 1;
        r13 = r13[r19];
        r13 = r13.getMargin();
        r13 = (float) r13;
        r17 = r17 + r13;
    L_0x00a1:
        r13 = r14.mListAnchors;
        r13 = r13[r25];
        r13 = r14.getVisibility();
        if (r13 == r9) goto L_0x00e0;
    L_0x00ab:
        r9 = r14.mListDimensionBehaviors;
        r9 = r9[r1];
        r13 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        if (r9 != r13) goto L_0x00e0;
    L_0x00b3:
        r7 = r7 + 1;
        if (r1 != 0) goto L_0x00c7;
    L_0x00b7:
        r9 = r14.mMatchConstraintDefaultWidth;
        if (r9 == 0) goto L_0x00bd;
    L_0x00bb:
        r9 = 0;
        return r9;
    L_0x00bd:
        r9 = 0;
        r13 = r14.mMatchConstraintMinWidth;
        if (r13 != 0) goto L_0x00c6;
    L_0x00c2:
        r13 = r14.mMatchConstraintMaxWidth;
        if (r13 == 0) goto L_0x00d6;
    L_0x00c6:
        return r9;
    L_0x00c7:
        r9 = 0;
        r13 = r14.mMatchConstraintDefaultHeight;
        if (r13 == 0) goto L_0x00cd;
    L_0x00cc:
        return r9;
    L_0x00cd:
        r13 = r14.mMatchConstraintMinHeight;
        if (r13 != 0) goto L_0x00df;
    L_0x00d1:
        r13 = r14.mMatchConstraintMaxHeight;
        if (r13 == 0) goto L_0x00d6;
    L_0x00d5:
        goto L_0x00df;
    L_0x00d6:
        r13 = r14.mDimensionRatio;
        r16 = 0;
        r13 = (r13 > r16 ? 1 : (r13 == r16 ? 0 : -1));
        if (r13 == 0) goto L_0x00e0;
    L_0x00de:
        return r9;
    L_0x00df:
        return r9;
    L_0x00e0:
        r9 = r14.mListAnchors;
        r13 = r25 + 1;
        r9 = r9[r13];
        r9 = r9.mTarget;
        if (r9 == 0) goto L_0x0102;
    L_0x00ea:
        r9 = r9.mOwner;
        r13 = r9.mListAnchors;
        r13 = r13[r25];
        r13 = r13.mTarget;
        if (r13 == 0) goto L_0x0102;
    L_0x00f4:
        r13 = r9.mListAnchors;
        r13 = r13[r25];
        r13 = r13.mTarget;
        r13 = r13.mOwner;
        if (r13 == r14) goto L_0x00ff;
    L_0x00fe:
        goto L_0x0102;
    L_0x00ff:
        r20 = r9;
        goto L_0x0104;
    L_0x0102:
        r20 = 0;
    L_0x0104:
        if (r20 == 0) goto L_0x010c;
    L_0x0106:
        r13 = r18;
        r14 = r20;
        goto L_0x0052;
    L_0x010c:
        r13 = 1;
        goto L_0x0052;
    L_0x010f:
        r13 = r3.mListAnchors;
        r13 = r13[r25];
        r13 = r13.getResolutionNode();
        r9 = r4.mListAnchors;
        r18 = r25 + 1;
        r9 = r9[r18];
        r9 = r9.getResolutionNode();
        r21 = r3;
        r3 = r13.target;
        if (r3 == 0) goto L_0x039d;
    L_0x0127:
        r3 = r9.target;
        if (r3 != 0) goto L_0x012d;
    L_0x012b:
        goto L_0x039d;
    L_0x012d:
        r3 = r13.target;
        r3 = r3.state;
        r0 = 1;
        if (r3 != r0) goto L_0x039b;
    L_0x0134:
        r3 = r9.target;
        r3 = r3.state;
        if (r3 == r0) goto L_0x013c;
    L_0x013a:
        goto L_0x039b;
    L_0x013c:
        if (r7 <= 0) goto L_0x0142;
    L_0x013e:
        if (r7 == r10) goto L_0x0142;
    L_0x0140:
        r0 = 0;
        return r0;
    L_0x0142:
        if (r2 != 0) goto L_0x014b;
    L_0x0144:
        if (r11 != 0) goto L_0x014b;
    L_0x0146:
        if (r12 == 0) goto L_0x0149;
    L_0x0148:
        goto L_0x014b;
    L_0x0149:
        r0 = 0;
        goto L_0x0164;
    L_0x014b:
        if (r5 == 0) goto L_0x0157;
    L_0x014d:
        r0 = r5.mListAnchors;
        r0 = r0[r25];
        r0 = r0.getMargin();
        r0 = (float) r0;
        goto L_0x0158;
    L_0x0157:
        r0 = 0;
    L_0x0158:
        if (r6 == 0) goto L_0x0164;
    L_0x015a:
        r3 = r6.mListAnchors;
        r3 = r3[r18];
        r3 = r3.getMargin();
        r3 = (float) r3;
        r0 = r0 + r3;
    L_0x0164:
        r3 = r13.target;
        r3 = r3.resolvedOffset;
        r6 = r9.target;
        r6 = r6.resolvedOffset;
        r9 = (r3 > r6 ? 1 : (r3 == r6 ? 0 : -1));
        if (r9 >= 0) goto L_0x0173;
    L_0x0170:
        r6 = r6 - r3;
        r6 = r6 - r15;
        goto L_0x0176;
    L_0x0173:
        r6 = r3 - r6;
        r6 = r6 - r15;
    L_0x0176:
        r19 = 1;
        if (r7 <= 0) goto L_0x022f;
    L_0x017a:
        if (r7 != r10) goto L_0x022f;
    L_0x017c:
        r0 = r14.getParent();
        if (r0 == 0) goto L_0x0190;
    L_0x0182:
        r0 = r14.getParent();
        r0 = r0.mListDimensionBehaviors;
        r0 = r0[r1];
        r2 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        if (r0 != r2) goto L_0x0190;
    L_0x018e:
        r0 = 0;
        return r0;
    L_0x0190:
        r6 = r6 + r15;
        r6 = r6 - r17;
        r0 = r21;
    L_0x0195:
        if (r0 == 0) goto L_0x022d;
    L_0x0197:
        r2 = android.support.constraint.solver.LinearSystem.sMetrics;
        if (r2 == 0) goto L_0x01b3;
    L_0x019b:
        r2 = android.support.constraint.solver.LinearSystem.sMetrics;
        r9 = r2.nonresolvedWidgets;
        r9 = r9 - r19;
        r2.nonresolvedWidgets = r9;
        r2 = android.support.constraint.solver.LinearSystem.sMetrics;
        r9 = r2.resolvedWidgets;
        r9 = r9 + r19;
        r2.resolvedWidgets = r9;
        r2 = android.support.constraint.solver.LinearSystem.sMetrics;
        r9 = r2.chainConnectionResolved;
        r9 = r9 + r19;
        r2.chainConnectionResolved = r9;
    L_0x01b3:
        r2 = r0.mNextChainWidget;
        r2 = r2[r1];
        if (r2 != 0) goto L_0x01bf;
    L_0x01b9:
        if (r0 != r4) goto L_0x01bc;
    L_0x01bb:
        goto L_0x01bf;
    L_0x01bc:
        r9 = r23;
        goto L_0x022a;
    L_0x01bf:
        r5 = (float) r7;
        r5 = r6 / r5;
        r9 = 0;
        r10 = (r8 > r9 ? 1 : (r8 == r9 ? 0 : -1));
        if (r10 <= 0) goto L_0x01da;
    L_0x01c7:
        r5 = r0.mWeight;
        r5 = r5[r1];
        r9 = -1082130432; // 0xffffffffbf800000 float:-1.0 double:NaN;
        r5 = (r5 > r9 ? 1 : (r5 == r9 ? 0 : -1));
        if (r5 != 0) goto L_0x01d3;
    L_0x01d1:
        r5 = 0;
        goto L_0x01da;
    L_0x01d3:
        r5 = r0.mWeight;
        r5 = r5[r1];
        r5 = r5 * r6;
        r5 = r5 / r8;
    L_0x01da:
        r9 = r0.getVisibility();
        r10 = 8;
        if (r9 != r10) goto L_0x01e3;
    L_0x01e2:
        r5 = 0;
    L_0x01e3:
        r9 = r0.mListAnchors;
        r9 = r9[r25];
        r9 = r9.getMargin();
        r9 = (float) r9;
        r3 = r3 + r9;
        r9 = r0.mListAnchors;
        r9 = r9[r25];
        r9 = r9.getResolutionNode();
        r10 = r13.resolvedTarget;
        r9.resolve(r10, r3);
        r9 = r0.mListAnchors;
        r9 = r9[r18];
        r9 = r9.getResolutionNode();
        r10 = r13.resolvedTarget;
        r3 = r3 + r5;
        r9.resolve(r10, r3);
        r5 = r0.mListAnchors;
        r5 = r5[r25];
        r5 = r5.getResolutionNode();
        r9 = r23;
        r5.addResolvedValue(r9);
        r5 = r0.mListAnchors;
        r5 = r5[r18];
        r5 = r5.getResolutionNode();
        r5.addResolvedValue(r9);
        r0 = r0.mListAnchors;
        r0 = r0[r18];
        r0 = r0.getMargin();
        r0 = (float) r0;
        r3 = r3 + r0;
    L_0x022a:
        r0 = r2;
        goto L_0x0195;
    L_0x022d:
        r0 = 1;
        return r0;
    L_0x022f:
        r9 = r23;
        r7 = 0;
        r7 = (r6 > r7 ? 1 : (r6 == r7 ? 0 : -1));
        if (r7 >= 0) goto L_0x0239;
    L_0x0236:
        r2 = 1;
        r11 = 0;
        r12 = 0;
    L_0x0239:
        if (r2 == 0) goto L_0x02c2;
    L_0x023b:
        r6 = r6 - r0;
        r2 = r21;
        r0 = r2.getBiasPercent(r1);
        r6 = r6 * r0;
        r3 = r3 + r6;
    L_0x0245:
        if (r2 == 0) goto L_0x02bf;
    L_0x0247:
        r0 = android.support.constraint.solver.LinearSystem.sMetrics;
        if (r0 == 0) goto L_0x0263;
    L_0x024b:
        r0 = android.support.constraint.solver.LinearSystem.sMetrics;
        r5 = r0.nonresolvedWidgets;
        r5 = r5 - r19;
        r0.nonresolvedWidgets = r5;
        r0 = android.support.constraint.solver.LinearSystem.sMetrics;
        r5 = r0.resolvedWidgets;
        r5 = r5 + r19;
        r0.resolvedWidgets = r5;
        r0 = android.support.constraint.solver.LinearSystem.sMetrics;
        r5 = r0.chainConnectionResolved;
        r5 = r5 + r19;
        r0.chainConnectionResolved = r5;
    L_0x0263:
        r0 = r2.mNextChainWidget;
        r0 = r0[r1];
        if (r0 != 0) goto L_0x026b;
    L_0x0269:
        if (r2 != r4) goto L_0x02bd;
    L_0x026b:
        if (r1 != 0) goto L_0x0273;
    L_0x026d:
        r5 = r2.getWidth();
        r5 = (float) r5;
        goto L_0x0278;
    L_0x0273:
        r5 = r2.getHeight();
        r5 = (float) r5;
    L_0x0278:
        r6 = r2.mListAnchors;
        r6 = r6[r25];
        r6 = r6.getMargin();
        r6 = (float) r6;
        r3 = r3 + r6;
        r6 = r2.mListAnchors;
        r6 = r6[r25];
        r6 = r6.getResolutionNode();
        r7 = r13.resolvedTarget;
        r6.resolve(r7, r3);
        r6 = r2.mListAnchors;
        r6 = r6[r18];
        r6 = r6.getResolutionNode();
        r7 = r13.resolvedTarget;
        r3 = r3 + r5;
        r6.resolve(r7, r3);
        r5 = r2.mListAnchors;
        r5 = r5[r25];
        r5 = r5.getResolutionNode();
        r5.addResolvedValue(r9);
        r5 = r2.mListAnchors;
        r5 = r5[r18];
        r5 = r5.getResolutionNode();
        r5.addResolvedValue(r9);
        r2 = r2.mListAnchors;
        r2 = r2[r18];
        r2 = r2.getMargin();
        r2 = (float) r2;
        r3 = r3 + r2;
    L_0x02bd:
        r2 = r0;
        goto L_0x0245;
    L_0x02bf:
        r0 = 1;
        goto L_0x039a;
    L_0x02c2:
        r2 = r21;
        if (r11 != 0) goto L_0x02c8;
    L_0x02c6:
        if (r12 == 0) goto L_0x02bf;
    L_0x02c8:
        if (r11 == 0) goto L_0x02cc;
    L_0x02ca:
        r6 = r6 - r0;
        goto L_0x02cf;
    L_0x02cc:
        if (r12 == 0) goto L_0x02cf;
    L_0x02ce:
        r6 = r6 - r0;
    L_0x02cf:
        r0 = r10 + 1;
        r0 = (float) r0;
        r0 = r6 / r0;
        if (r12 == 0) goto L_0x02e3;
    L_0x02d6:
        r7 = 1;
        if (r10 <= r7) goto L_0x02df;
    L_0x02d9:
        r0 = r10 + -1;
        r0 = (float) r0;
        r0 = r6 / r0;
        goto L_0x02e3;
    L_0x02df:
        r0 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r0 = r6 / r0;
    L_0x02e3:
        r6 = r2.getVisibility();
        r7 = 8;
        if (r6 == r7) goto L_0x02ee;
    L_0x02eb:
        r6 = r3 + r0;
        goto L_0x02ef;
    L_0x02ee:
        r6 = r3;
    L_0x02ef:
        if (r12 == 0) goto L_0x02fe;
    L_0x02f1:
        r7 = 1;
        if (r10 <= r7) goto L_0x02fe;
    L_0x02f4:
        r6 = r5.mListAnchors;
        r6 = r6[r25];
        r6 = r6.getMargin();
        r6 = (float) r6;
        r6 = r6 + r3;
    L_0x02fe:
        if (r11 == 0) goto L_0x030c;
    L_0x0300:
        if (r5 == 0) goto L_0x030c;
    L_0x0302:
        r3 = r5.mListAnchors;
        r3 = r3[r25];
        r3 = r3.getMargin();
        r3 = (float) r3;
        r6 = r6 + r3;
    L_0x030c:
        if (r2 == 0) goto L_0x02bf;
    L_0x030e:
        r3 = android.support.constraint.solver.LinearSystem.sMetrics;
        if (r3 == 0) goto L_0x032a;
    L_0x0312:
        r3 = android.support.constraint.solver.LinearSystem.sMetrics;
        r7 = r3.nonresolvedWidgets;
        r7 = r7 - r19;
        r3.nonresolvedWidgets = r7;
        r3 = android.support.constraint.solver.LinearSystem.sMetrics;
        r7 = r3.resolvedWidgets;
        r7 = r7 + r19;
        r3.resolvedWidgets = r7;
        r3 = android.support.constraint.solver.LinearSystem.sMetrics;
        r7 = r3.chainConnectionResolved;
        r7 = r7 + r19;
        r3.chainConnectionResolved = r7;
    L_0x032a:
        r3 = r2.mNextChainWidget;
        r3 = r3[r1];
        if (r3 != 0) goto L_0x0336;
    L_0x0330:
        if (r2 != r4) goto L_0x0333;
    L_0x0332:
        goto L_0x0336;
    L_0x0333:
        r7 = 8;
        goto L_0x0397;
    L_0x0336:
        if (r1 != 0) goto L_0x033e;
    L_0x0338:
        r7 = r2.getWidth();
        r7 = (float) r7;
        goto L_0x0343;
    L_0x033e:
        r7 = r2.getHeight();
        r7 = (float) r7;
    L_0x0343:
        if (r2 == r5) goto L_0x034f;
    L_0x0345:
        r8 = r2.mListAnchors;
        r8 = r8[r25];
        r8 = r8.getMargin();
        r8 = (float) r8;
        r6 = r6 + r8;
    L_0x034f:
        r8 = r2.mListAnchors;
        r8 = r8[r25];
        r8 = r8.getResolutionNode();
        r10 = r13.resolvedTarget;
        r8.resolve(r10, r6);
        r8 = r2.mListAnchors;
        r8 = r8[r18];
        r8 = r8.getResolutionNode();
        r10 = r13.resolvedTarget;
        r11 = r6 + r7;
        r8.resolve(r10, r11);
        r8 = r2.mListAnchors;
        r8 = r8[r25];
        r8 = r8.getResolutionNode();
        r8.addResolvedValue(r9);
        r8 = r2.mListAnchors;
        r8 = r8[r18];
        r8 = r8.getResolutionNode();
        r8.addResolvedValue(r9);
        r2 = r2.mListAnchors;
        r2 = r2[r18];
        r2 = r2.getMargin();
        r2 = (float) r2;
        r7 = r7 + r2;
        r6 = r6 + r7;
        if (r3 == 0) goto L_0x0333;
    L_0x038e:
        r2 = r3.getVisibility();
        r7 = 8;
        if (r2 == r7) goto L_0x0397;
    L_0x0396:
        r6 = r6 + r0;
    L_0x0397:
        r2 = r3;
        goto L_0x030c;
    L_0x039a:
        return r0;
    L_0x039b:
        r0 = 0;
        return r0;
    L_0x039d:
        r0 = 0;
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.solver.widgets.Optimizer.applyChainOptimized(android.support.constraint.solver.widgets.ConstraintWidgetContainer, android.support.constraint.solver.LinearSystem, int, int, android.support.constraint.solver.widgets.ChainHead):boolean");
    }

    static void setOptimizedWidget(ConstraintWidget constraintWidget, int i, int i2) {
        int i3 = i * 2;
        int i4 = i3 + 1;
        constraintWidget.mListAnchors[i3].getResolutionNode().resolvedTarget = constraintWidget.getParent().mLeft.getResolutionNode();
        constraintWidget.mListAnchors[i3].getResolutionNode().resolvedOffset = (float) i2;
        constraintWidget.mListAnchors[i3].getResolutionNode().state = 1;
        constraintWidget.mListAnchors[i4].getResolutionNode().resolvedTarget = constraintWidget.mListAnchors[i3].getResolutionNode();
        constraintWidget.mListAnchors[i4].getResolutionNode().resolvedOffset = (float) constraintWidget.getLength(i);
        constraintWidget.mListAnchors[i4].getResolutionNode().state = 1;
    }
}
