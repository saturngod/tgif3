package android.support.constraint.solver.widgets;

import android.support.constraint.solver.LinearSystem;

class Chain {
    private static final boolean DEBUG = false;

    Chain() {
    }

    static void applyChainConstraints(ConstraintWidgetContainer constraintWidgetContainer, LinearSystem linearSystem, int i) {
        int i2;
        ChainHead[] chainHeadArr;
        int i3;
        int i4 = 0;
        if (i == 0) {
            i2 = constraintWidgetContainer.mHorizontalChainsSize;
            chainHeadArr = constraintWidgetContainer.mHorizontalChainsArray;
            i3 = i2;
            i2 = 0;
        } else {
            i2 = 2;
            int i5 = constraintWidgetContainer.mVerticalChainsSize;
            i3 = i5;
            chainHeadArr = constraintWidgetContainer.mVerticalChainsArray;
        }
        while (i4 < i3) {
            ChainHead chainHead = chainHeadArr[i4];
            chainHead.define();
            if (!constraintWidgetContainer.optimizeFor(4)) {
                applyChainConstraints(constraintWidgetContainer, linearSystem, i, i2, chainHead);
            } else if (!Optimizer.applyChainOptimized(constraintWidgetContainer, linearSystem, i, i2, chainHead)) {
                applyChainConstraints(constraintWidgetContainer, linearSystem, i, i2, chainHead);
            }
            i4++;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static void applyChainConstraints(android.support.constraint.solver.widgets.ConstraintWidgetContainer r48, android.support.constraint.solver.LinearSystem r49, int r50, int r51, android.support.constraint.solver.widgets.ChainHead r52) {
        /*
        r0 = r48;
        r9 = r49;
        r1 = r52;
        r11 = r1.mFirst;
        r12 = r1.mLast;
        r13 = r1.mFirstVisibleWidget;
        r14 = r1.mLastVisibleWidget;
        r2 = r1.mHead;
        r3 = r1.mTotalWeight;
        r4 = r1.mFirstMatchConstraintWidget;
        r4 = r1.mLastMatchConstraintWidget;
        r4 = r0.mListDimensionBehaviors;
        r4 = r4[r50];
        r5 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        r7 = 1;
        if (r4 != r5) goto L_0x0021;
    L_0x001f:
        r4 = 1;
        goto L_0x0022;
    L_0x0021:
        r4 = 0;
    L_0x0022:
        r5 = 2;
        if (r50 != 0) goto L_0x0042;
    L_0x0025:
        r8 = r2.mHorizontalChainStyle;
        if (r8 != 0) goto L_0x002b;
    L_0x0029:
        r8 = 1;
        goto L_0x002c;
    L_0x002b:
        r8 = 0;
    L_0x002c:
        r6 = r2.mHorizontalChainStyle;
        if (r6 != r7) goto L_0x0032;
    L_0x0030:
        r6 = 1;
        goto L_0x0033;
    L_0x0032:
        r6 = 0;
    L_0x0033:
        r7 = r2.mHorizontalChainStyle;
        if (r7 != r5) goto L_0x0039;
    L_0x0037:
        r5 = 1;
        goto L_0x003a;
    L_0x0039:
        r5 = 0;
    L_0x003a:
        r7 = r5;
        r18 = r6;
        r17 = r8;
        r6 = r11;
        r5 = 0;
        goto L_0x0056;
    L_0x0042:
        r6 = r2.mVerticalChainStyle;
        if (r6 != 0) goto L_0x0048;
    L_0x0046:
        r8 = 1;
        goto L_0x0049;
    L_0x0048:
        r8 = 0;
    L_0x0049:
        r6 = r2.mVerticalChainStyle;
        r7 = 1;
        if (r6 != r7) goto L_0x0050;
    L_0x004e:
        r6 = 1;
        goto L_0x0051;
    L_0x0050:
        r6 = 0;
    L_0x0051:
        r7 = r2.mVerticalChainStyle;
        if (r7 != r5) goto L_0x0039;
    L_0x0055:
        goto L_0x0037;
    L_0x0056:
        r22 = 0;
        if (r5 != 0) goto L_0x0135;
    L_0x005a:
        r8 = r6.mListAnchors;
        r8 = r8[r51];
        if (r4 != 0) goto L_0x0066;
    L_0x0060:
        if (r7 == 0) goto L_0x0063;
    L_0x0062:
        goto L_0x0066;
    L_0x0063:
        r24 = 4;
        goto L_0x0068;
    L_0x0066:
        r24 = 1;
    L_0x0068:
        r25 = r8.getMargin();
        r26 = r3;
        r3 = r8.mTarget;
        if (r3 == 0) goto L_0x007c;
    L_0x0072:
        if (r6 == r11) goto L_0x007c;
    L_0x0074:
        r3 = r8.mTarget;
        r3 = r3.getMargin();
        r25 = r25 + r3;
    L_0x007c:
        r3 = r25;
        if (r7 == 0) goto L_0x008a;
    L_0x0080:
        if (r6 == r11) goto L_0x008a;
    L_0x0082:
        if (r6 == r13) goto L_0x008a;
    L_0x0084:
        r28 = r2;
        r27 = r5;
        r5 = 6;
        goto L_0x009a;
    L_0x008a:
        if (r17 == 0) goto L_0x0094;
    L_0x008c:
        if (r4 == 0) goto L_0x0094;
    L_0x008e:
        r28 = r2;
        r27 = r5;
        r5 = 4;
        goto L_0x009a;
    L_0x0094:
        r28 = r2;
        r27 = r5;
        r5 = r24;
    L_0x009a:
        r2 = r8.mTarget;
        if (r2 == 0) goto L_0x00c7;
    L_0x009e:
        if (r6 != r13) goto L_0x00af;
    L_0x00a0:
        r2 = r8.mSolverVariable;
        r29 = r11;
        r11 = r8.mTarget;
        r11 = r11.mSolverVariable;
        r30 = r7;
        r7 = 5;
        r9.addGreaterThan(r2, r11, r3, r7);
        goto L_0x00bd;
    L_0x00af:
        r30 = r7;
        r29 = r11;
        r2 = r8.mSolverVariable;
        r7 = r8.mTarget;
        r7 = r7.mSolverVariable;
        r11 = 6;
        r9.addGreaterThan(r2, r7, r3, r11);
    L_0x00bd:
        r2 = r8.mSolverVariable;
        r7 = r8.mTarget;
        r7 = r7.mSolverVariable;
        r9.addEquality(r2, r7, r3, r5);
        goto L_0x00cb;
    L_0x00c7:
        r30 = r7;
        r29 = r11;
    L_0x00cb:
        if (r4 == 0) goto L_0x0102;
    L_0x00cd:
        r2 = r6.getVisibility();
        r3 = 8;
        if (r2 == r3) goto L_0x00f1;
    L_0x00d5:
        r2 = r6.mListDimensionBehaviors;
        r2 = r2[r50];
        r3 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        if (r2 != r3) goto L_0x00f1;
    L_0x00dd:
        r2 = r6.mListAnchors;
        r3 = r51 + 1;
        r2 = r2[r3];
        r2 = r2.mSolverVariable;
        r3 = r6.mListAnchors;
        r3 = r3[r51];
        r3 = r3.mSolverVariable;
        r5 = 0;
        r7 = 5;
        r9.addGreaterThan(r2, r3, r5, r7);
        goto L_0x00f2;
    L_0x00f1:
        r5 = 0;
    L_0x00f2:
        r2 = r6.mListAnchors;
        r2 = r2[r51];
        r2 = r2.mSolverVariable;
        r3 = r0.mListAnchors;
        r3 = r3[r51];
        r3 = r3.mSolverVariable;
        r7 = 6;
        r9.addGreaterThan(r2, r3, r5, r7);
    L_0x0102:
        r2 = r6.mListAnchors;
        r3 = r51 + 1;
        r2 = r2[r3];
        r2 = r2.mTarget;
        if (r2 == 0) goto L_0x0123;
    L_0x010c:
        r2 = r2.mOwner;
        r3 = r2.mListAnchors;
        r3 = r3[r51];
        r3 = r3.mTarget;
        if (r3 == 0) goto L_0x0123;
    L_0x0116:
        r3 = r2.mListAnchors;
        r3 = r3[r51];
        r3 = r3.mTarget;
        r3 = r3.mOwner;
        if (r3 == r6) goto L_0x0121;
    L_0x0120:
        goto L_0x0123;
    L_0x0121:
        r22 = r2;
    L_0x0123:
        if (r22 == 0) goto L_0x012a;
    L_0x0125:
        r6 = r22;
        r5 = r27;
        goto L_0x012b;
    L_0x012a:
        r5 = 1;
    L_0x012b:
        r3 = r26;
        r2 = r28;
        r11 = r29;
        r7 = r30;
        goto L_0x0056;
    L_0x0135:
        r28 = r2;
        r26 = r3;
        r30 = r7;
        r29 = r11;
        if (r14 == 0) goto L_0x0161;
    L_0x013f:
        r2 = r12.mListAnchors;
        r3 = r51 + 1;
        r2 = r2[r3];
        r2 = r2.mTarget;
        if (r2 == 0) goto L_0x0161;
    L_0x0149:
        r2 = r14.mListAnchors;
        r2 = r2[r3];
        r5 = r2.mSolverVariable;
        r6 = r12.mListAnchors;
        r3 = r6[r3];
        r3 = r3.mTarget;
        r3 = r3.mSolverVariable;
        r2 = r2.getMargin();
        r2 = -r2;
        r8 = 5;
        r9.addLowerThan(r5, r3, r2, r8);
        goto L_0x0162;
    L_0x0161:
        r8 = 5;
    L_0x0162:
        if (r4 == 0) goto L_0x017e;
    L_0x0164:
        r0 = r0.mListAnchors;
        r2 = r51 + 1;
        r0 = r0[r2];
        r0 = r0.mSolverVariable;
        r3 = r12.mListAnchors;
        r3 = r3[r2];
        r3 = r3.mSolverVariable;
        r4 = r12.mListAnchors;
        r2 = r4[r2];
        r2 = r2.getMargin();
        r4 = 6;
        r9.addGreaterThan(r0, r3, r2, r4);
    L_0x017e:
        r0 = r1.mWeightedMatchConstraintsWidgets;
        if (r0 == 0) goto L_0x022e;
    L_0x0182:
        r2 = r0.size();
        r7 = 1;
        if (r2 <= r7) goto L_0x022e;
    L_0x0189:
        r3 = r1.mHasUndefinedWeights;
        if (r3 == 0) goto L_0x0196;
    L_0x018d:
        r3 = r1.mHasComplexMatchWeights;
        if (r3 != 0) goto L_0x0196;
    L_0x0191:
        r3 = r1.mWidgetsMatchCount;
        r3 = (float) r3;
        r26 = r3;
    L_0x0196:
        r3 = 0;
        r5 = r22;
        r4 = 0;
        r32 = 0;
    L_0x019c:
        if (r4 >= r2) goto L_0x022e;
    L_0x019e:
        r6 = r0.get(r4);
        r6 = (android.support.constraint.solver.widgets.ConstraintWidget) r6;
        r11 = r6.mWeight;
        r11 = r11[r50];
        r16 = (r11 > r3 ? 1 : (r11 == r3 ? 0 : -1));
        if (r16 >= 0) goto L_0x01cc;
    L_0x01ac:
        r11 = r1.mHasComplexMatchWeights;
        if (r11 == 0) goto L_0x01c6;
    L_0x01b0:
        r11 = r6.mListAnchors;
        r16 = r51 + 1;
        r11 = r11[r16];
        r11 = r11.mSolverVariable;
        r6 = r6.mListAnchors;
        r6 = r6[r51];
        r6 = r6.mSolverVariable;
        r7 = 0;
        r8 = 4;
        r9.addEquality(r11, r6, r7, r8);
        r8 = 6;
        r11 = 0;
        goto L_0x01e4;
    L_0x01c6:
        r8 = 4;
        r7 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        r11 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        goto L_0x01cd;
    L_0x01cc:
        r8 = 4;
    L_0x01cd:
        r7 = (r11 > r3 ? 1 : (r11 == r3 ? 0 : -1));
        if (r7 != 0) goto L_0x01e7;
    L_0x01d1:
        r7 = r6.mListAnchors;
        r11 = r51 + 1;
        r7 = r7[r11];
        r7 = r7.mSolverVariable;
        r6 = r6.mListAnchors;
        r6 = r6[r51];
        r6 = r6.mSolverVariable;
        r8 = 6;
        r11 = 0;
        r9.addEquality(r7, r6, r11, r8);
    L_0x01e4:
        r40 = r0;
        goto L_0x0225;
    L_0x01e7:
        r7 = 0;
        r8 = 6;
        if (r5 == 0) goto L_0x0220;
    L_0x01eb:
        r3 = r5.mListAnchors;
        r3 = r3[r51];
        r3 = r3.mSolverVariable;
        r5 = r5.mListAnchors;
        r15 = r51 + 1;
        r5 = r5[r15];
        r5 = r5.mSolverVariable;
        r7 = r6.mListAnchors;
        r7 = r7[r51];
        r7 = r7.mSolverVariable;
        r8 = r6.mListAnchors;
        r8 = r8[r15];
        r8 = r8.mSolverVariable;
        r40 = r0;
        r0 = r49.createRow();
        r31 = r0;
        r33 = r26;
        r34 = r11;
        r35 = r3;
        r36 = r5;
        r37 = r7;
        r38 = r8;
        r31.createRowEqualMatchDimensions(r32, r33, r34, r35, r36, r37, r38);
        r9.addConstraint(r0);
        goto L_0x0222;
    L_0x0220:
        r40 = r0;
    L_0x0222:
        r5 = r6;
        r32 = r11;
    L_0x0225:
        r4 = r4 + 1;
        r0 = r40;
        r3 = 0;
        r7 = 1;
        r8 = 5;
        goto L_0x019c;
    L_0x022e:
        if (r13 == 0) goto L_0x029b;
    L_0x0230:
        if (r13 == r14) goto L_0x0234;
    L_0x0232:
        if (r30 == 0) goto L_0x029b;
    L_0x0234:
        r11 = r29;
        r0 = r11.mListAnchors;
        r0 = r0[r51];
        r1 = r12.mListAnchors;
        r2 = r51 + 1;
        r1 = r1[r2];
        r3 = r11.mListAnchors;
        r3 = r3[r51];
        r3 = r3.mTarget;
        if (r3 == 0) goto L_0x0251;
    L_0x0248:
        r3 = r11.mListAnchors;
        r3 = r3[r51];
        r3 = r3.mTarget;
        r3 = r3.mSolverVariable;
        goto L_0x0253;
    L_0x0251:
        r3 = r22;
    L_0x0253:
        r4 = r12.mListAnchors;
        r4 = r4[r2];
        r4 = r4.mTarget;
        if (r4 == 0) goto L_0x0265;
    L_0x025b:
        r4 = r12.mListAnchors;
        r4 = r4[r2];
        r4 = r4.mTarget;
        r4 = r4.mSolverVariable;
        r5 = r4;
        goto L_0x0267;
    L_0x0265:
        r5 = r22;
    L_0x0267:
        if (r13 != r14) goto L_0x0271;
    L_0x0269:
        r0 = r13.mListAnchors;
        r0 = r0[r51];
        r1 = r13.mListAnchors;
        r1 = r1[r2];
    L_0x0271:
        if (r3 == 0) goto L_0x0508;
    L_0x0273:
        if (r5 == 0) goto L_0x0508;
    L_0x0275:
        if (r50 != 0) goto L_0x027d;
    L_0x0277:
        r2 = r28;
        r2 = r2.mHorizontalBiasPercent;
    L_0x027b:
        r4 = r2;
        goto L_0x0282;
    L_0x027d:
        r2 = r28;
        r2 = r2.mVerticalBiasPercent;
        goto L_0x027b;
    L_0x0282:
        r6 = r0.getMargin();
        r7 = r1.getMargin();
        r2 = r0.mSolverVariable;
        r8 = r1.mSolverVariable;
        r10 = 5;
        r0 = r49;
        r1 = r2;
        r2 = r3;
        r3 = r6;
        r6 = r8;
        r8 = r10;
        r0.addCentering(r1, r2, r3, r4, r5, r6, r7, r8);
        goto L_0x0508;
    L_0x029b:
        r11 = r29;
        if (r17 == 0) goto L_0x03bf;
    L_0x029f:
        if (r13 == 0) goto L_0x03bf;
    L_0x02a1:
        r0 = r1.mWidgetsMatchCount;
        if (r0 <= 0) goto L_0x02ae;
    L_0x02a5:
        r0 = r1.mWidgetsCount;
        r1 = r1.mWidgetsMatchCount;
        if (r0 != r1) goto L_0x02ae;
    L_0x02ab:
        r39 = 1;
        goto L_0x02b0;
    L_0x02ae:
        r39 = 0;
    L_0x02b0:
        r7 = r13;
        r8 = r7;
    L_0x02b2:
        if (r8 == 0) goto L_0x0506;
    L_0x02b4:
        r0 = r8.mNextChainWidget;
        r0 = r0[r50];
        r6 = r0;
    L_0x02b9:
        if (r6 == 0) goto L_0x02c8;
    L_0x02bb:
        r0 = r6.getVisibility();
        r5 = 8;
        if (r0 != r5) goto L_0x02ca;
    L_0x02c3:
        r0 = r6.mNextChainWidget;
        r6 = r0[r50];
        goto L_0x02b9;
    L_0x02c8:
        r5 = 8;
    L_0x02ca:
        if (r6 != 0) goto L_0x02dd;
    L_0x02cc:
        if (r8 != r14) goto L_0x02cf;
    L_0x02ce:
        goto L_0x02dd;
    L_0x02cf:
        r19 = r6;
        r20 = r7;
        r16 = r8;
    L_0x02d5:
        r9 = 8;
        r21 = 6;
        r23 = 4;
        goto L_0x03ae;
    L_0x02dd:
        r0 = r8.mListAnchors;
        r0 = r0[r51];
        r1 = r0.mSolverVariable;
        r2 = r0.mTarget;
        if (r2 == 0) goto L_0x02ec;
    L_0x02e7:
        r2 = r0.mTarget;
        r2 = r2.mSolverVariable;
        goto L_0x02ee;
    L_0x02ec:
        r2 = r22;
    L_0x02ee:
        if (r7 == r8) goto L_0x02f9;
    L_0x02f0:
        r2 = r7.mListAnchors;
        r3 = r51 + 1;
        r2 = r2[r3];
        r2 = r2.mSolverVariable;
        goto L_0x0310;
    L_0x02f9:
        if (r8 != r13) goto L_0x0310;
    L_0x02fb:
        if (r7 != r8) goto L_0x0310;
    L_0x02fd:
        r2 = r11.mListAnchors;
        r2 = r2[r51];
        r2 = r2.mTarget;
        if (r2 == 0) goto L_0x030e;
    L_0x0305:
        r2 = r11.mListAnchors;
        r2 = r2[r51];
        r2 = r2.mTarget;
        r2 = r2.mSolverVariable;
        goto L_0x0310;
    L_0x030e:
        r2 = r22;
    L_0x0310:
        r0 = r0.getMargin();
        r3 = r8.mListAnchors;
        r4 = r51 + 1;
        r3 = r3[r4];
        r3 = r3.getMargin();
        if (r6 == 0) goto L_0x0336;
    L_0x0320:
        r5 = r6.mListAnchors;
        r5 = r5[r51];
        r41 = r6;
        r6 = r5.mSolverVariable;
        r42 = r5;
        r5 = r8.mListAnchors;
        r5 = r5[r4];
        r5 = r5.mSolverVariable;
    L_0x0330:
        r47 = r6;
        r6 = r5;
        r5 = r47;
        goto L_0x0352;
    L_0x0336:
        r41 = r6;
        r5 = r12.mListAnchors;
        r5 = r5[r4];
        r5 = r5.mTarget;
        if (r5 == 0) goto L_0x0345;
    L_0x0340:
        r6 = r5.mSolverVariable;
        r43 = r5;
        goto L_0x0349;
    L_0x0345:
        r43 = r5;
        r6 = r22;
    L_0x0349:
        r5 = r8.mListAnchors;
        r5 = r5[r4];
        r5 = r5.mSolverVariable;
        r42 = r43;
        goto L_0x0330;
    L_0x0352:
        if (r42 == 0) goto L_0x0359;
    L_0x0354:
        r15 = r42.getMargin();
        r3 = r3 + r15;
    L_0x0359:
        if (r7 == 0) goto L_0x0367;
    L_0x035b:
        r44 = r3;
        r3 = r7.mListAnchors;
        r3 = r3[r4];
        r3 = r3.getMargin();
        r0 = r0 + r3;
        goto L_0x0369;
    L_0x0367:
        r44 = r3;
    L_0x0369:
        if (r1 == 0) goto L_0x03a6;
    L_0x036b:
        if (r2 == 0) goto L_0x03a6;
    L_0x036d:
        if (r5 == 0) goto L_0x03a6;
    L_0x036f:
        if (r6 == 0) goto L_0x03a6;
    L_0x0371:
        if (r8 != r13) goto L_0x037b;
    L_0x0373:
        r0 = r13.mListAnchors;
        r0 = r0[r51];
        r0 = r0.getMargin();
    L_0x037b:
        r3 = r0;
        if (r8 != r14) goto L_0x0388;
    L_0x037e:
        r0 = r14.mListAnchors;
        r0 = r0[r4];
        r0 = r0.getMargin();
        r44 = r0;
    L_0x0388:
        if (r39 == 0) goto L_0x038c;
    L_0x038a:
        r15 = 6;
        goto L_0x038d;
    L_0x038c:
        r15 = 4;
    L_0x038d:
        r4 = 1056964608; // 0x3f000000 float:0.5 double:5.222099017E-315;
        r0 = r49;
        r16 = 8;
        r19 = r41;
        r20 = r7;
        r7 = r44;
        r16 = r8;
        r9 = 8;
        r21 = 6;
        r23 = 4;
        r8 = r15;
        r0.addCentering(r1, r2, r3, r4, r5, r6, r7, r8);
        goto L_0x03ae;
    L_0x03a6:
        r20 = r7;
        r16 = r8;
        r19 = r41;
        goto L_0x02d5;
    L_0x03ae:
        r0 = r16.getVisibility();
        if (r0 == r9) goto L_0x03b7;
    L_0x03b4:
        r7 = r16;
        goto L_0x03b9;
    L_0x03b7:
        r7 = r20;
    L_0x03b9:
        r8 = r19;
        r9 = r49;
        goto L_0x02b2;
    L_0x03bf:
        r9 = 8;
        r21 = 6;
        r23 = 4;
        if (r18 == 0) goto L_0x0506;
    L_0x03c7:
        if (r13 == 0) goto L_0x0506;
    L_0x03c9:
        r0 = r1.mWidgetsMatchCount;
        if (r0 <= 0) goto L_0x03d6;
    L_0x03cd:
        r0 = r1.mWidgetsCount;
        r1 = r1.mWidgetsMatchCount;
        if (r0 != r1) goto L_0x03d6;
    L_0x03d3:
        r39 = 1;
        goto L_0x03d8;
    L_0x03d6:
        r39 = 0;
    L_0x03d8:
        r7 = r13;
        r8 = r7;
    L_0x03da:
        if (r8 == 0) goto L_0x04a0;
    L_0x03dc:
        r0 = r8.mNextChainWidget;
        r0 = r0[r50];
    L_0x03e0:
        if (r0 == 0) goto L_0x03ed;
    L_0x03e2:
        r1 = r0.getVisibility();
        if (r1 != r9) goto L_0x03ed;
    L_0x03e8:
        r0 = r0.mNextChainWidget;
        r0 = r0[r50];
        goto L_0x03e0;
    L_0x03ed:
        if (r8 == r13) goto L_0x048b;
    L_0x03ef:
        if (r8 == r14) goto L_0x048b;
    L_0x03f1:
        if (r0 == 0) goto L_0x048b;
    L_0x03f3:
        if (r0 != r14) goto L_0x03f8;
    L_0x03f5:
        r6 = r22;
        goto L_0x03f9;
    L_0x03f8:
        r6 = r0;
    L_0x03f9:
        r0 = r8.mListAnchors;
        r0 = r0[r51];
        r1 = r0.mSolverVariable;
        r2 = r0.mTarget;
        if (r2 == 0) goto L_0x0407;
    L_0x0403:
        r2 = r0.mTarget;
        r2 = r2.mSolverVariable;
    L_0x0407:
        r2 = r7.mListAnchors;
        r3 = r51 + 1;
        r2 = r2[r3];
        r2 = r2.mSolverVariable;
        r0 = r0.getMargin();
        r4 = r8.mListAnchors;
        r4 = r4[r3];
        r4 = r4.getMargin();
        if (r6 == 0) goto L_0x0436;
    L_0x041d:
        r5 = r6.mListAnchors;
        r5 = r5[r51];
        r9 = r5.mSolverVariable;
        r45 = r6;
        r6 = r5.mTarget;
        if (r6 == 0) goto L_0x042e;
    L_0x0429:
        r6 = r5.mTarget;
        r6 = r6.mSolverVariable;
        goto L_0x0430;
    L_0x042e:
        r6 = r22;
    L_0x0430:
        r47 = r9;
        r9 = r6;
        r6 = r47;
        goto L_0x044b;
    L_0x0436:
        r45 = r6;
        r5 = r8.mListAnchors;
        r5 = r5[r3];
        r5 = r5.mTarget;
        if (r5 == 0) goto L_0x0443;
    L_0x0440:
        r6 = r5.mSolverVariable;
        goto L_0x0445;
    L_0x0443:
        r6 = r22;
    L_0x0445:
        r9 = r8.mListAnchors;
        r9 = r9[r3];
        r9 = r9.mSolverVariable;
    L_0x044b:
        if (r5 == 0) goto L_0x0452;
    L_0x044d:
        r5 = r5.getMargin();
        r4 = r4 + r5;
    L_0x0452:
        r15 = r4;
        if (r7 == 0) goto L_0x045e;
    L_0x0455:
        r4 = r7.mListAnchors;
        r3 = r4[r3];
        r3 = r3.getMargin();
        r0 = r0 + r3;
    L_0x045e:
        r3 = r0;
        if (r39 == 0) goto L_0x0464;
    L_0x0461:
        r16 = 6;
        goto L_0x0466;
    L_0x0464:
        r16 = 4;
    L_0x0466:
        if (r1 == 0) goto L_0x0482;
    L_0x0468:
        if (r2 == 0) goto L_0x0482;
    L_0x046a:
        if (r6 == 0) goto L_0x0482;
    L_0x046c:
        if (r9 == 0) goto L_0x0482;
    L_0x046e:
        r4 = 1056964608; // 0x3f000000 float:0.5 double:5.222099017E-315;
        r0 = r49;
        r5 = r6;
        r19 = r45;
        r6 = r9;
        r9 = r7;
        r7 = r15;
        r15 = r8;
        r46 = r9;
        r9 = 5;
        r8 = r16;
        r0.addCentering(r1, r2, r3, r4, r5, r6, r7, r8);
        goto L_0x0488;
    L_0x0482:
        r46 = r7;
        r15 = r8;
        r19 = r45;
        r9 = 5;
    L_0x0488:
        r8 = r19;
        goto L_0x0490;
    L_0x048b:
        r46 = r7;
        r15 = r8;
        r9 = 5;
        r8 = r0;
    L_0x0490:
        r0 = r15.getVisibility();
        r1 = 8;
        if (r0 == r1) goto L_0x049a;
    L_0x0498:
        r7 = r15;
        goto L_0x049c;
    L_0x049a:
        r7 = r46;
    L_0x049c:
        r9 = 8;
        goto L_0x03da;
    L_0x04a0:
        r9 = 5;
        r0 = r13.mListAnchors;
        r0 = r0[r51];
        r1 = r11.mListAnchors;
        r1 = r1[r51];
        r1 = r1.mTarget;
        r2 = r14.mListAnchors;
        r3 = r51 + 1;
        r10 = r2[r3];
        r2 = r12.mListAnchors;
        r2 = r2[r3];
        r11 = r2.mTarget;
        if (r1 == 0) goto L_0x04f2;
    L_0x04b9:
        if (r13 == r14) goto L_0x04ca;
    L_0x04bb:
        r2 = r0.mSolverVariable;
        r1 = r1.mSolverVariable;
        r0 = r0.getMargin();
        r8 = r49;
        r8.addEquality(r2, r1, r0, r9);
    L_0x04c8:
        r9 = r8;
        goto L_0x04f4;
    L_0x04ca:
        r8 = r49;
        if (r11 == 0) goto L_0x04c8;
    L_0x04ce:
        r2 = r0.mSolverVariable;
        r3 = r1.mSolverVariable;
        r4 = r0.getMargin();
        r5 = 1056964608; // 0x3f000000 float:0.5 double:5.222099017E-315;
        r6 = r10.mSolverVariable;
        r7 = r11.mSolverVariable;
        r15 = r10.getMargin();
        r16 = 5;
        r0 = r49;
        r1 = r2;
        r2 = r3;
        r3 = r4;
        r4 = r5;
        r5 = r6;
        r6 = r7;
        r7 = r15;
        r9 = r8;
        r8 = r16;
        r0.addCentering(r1, r2, r3, r4, r5, r6, r7, r8);
        goto L_0x04f4;
    L_0x04f2:
        r9 = r49;
    L_0x04f4:
        if (r11 == 0) goto L_0x0508;
    L_0x04f6:
        if (r13 == r14) goto L_0x0508;
    L_0x04f8:
        r0 = r10.mSolverVariable;
        r1 = r11.mSolverVariable;
        r2 = r10.getMargin();
        r2 = -r2;
        r3 = 5;
        r9.addEquality(r0, r1, r2, r3);
        goto L_0x0508;
    L_0x0506:
        r9 = r49;
    L_0x0508:
        if (r17 != 0) goto L_0x050c;
    L_0x050a:
        if (r18 == 0) goto L_0x056f;
    L_0x050c:
        if (r13 == 0) goto L_0x056f;
    L_0x050e:
        r0 = r13.mListAnchors;
        r0 = r0[r51];
        r1 = r14.mListAnchors;
        r2 = r51 + 1;
        r1 = r1[r2];
        r3 = r0.mTarget;
        if (r3 == 0) goto L_0x0521;
    L_0x051c:
        r3 = r0.mTarget;
        r3 = r3.mSolverVariable;
        goto L_0x0523;
    L_0x0521:
        r3 = r22;
    L_0x0523:
        r4 = r1.mTarget;
        if (r4 == 0) goto L_0x052c;
    L_0x0527:
        r4 = r1.mTarget;
        r4 = r4.mSolverVariable;
        goto L_0x052e;
    L_0x052c:
        r4 = r22;
    L_0x052e:
        if (r12 == r14) goto L_0x053f;
    L_0x0530:
        r4 = r12.mListAnchors;
        r4 = r4[r2];
        r5 = r4.mTarget;
        if (r5 == 0) goto L_0x053d;
    L_0x0538:
        r4 = r4.mTarget;
        r4 = r4.mSolverVariable;
        goto L_0x053f;
    L_0x053d:
        r4 = r22;
    L_0x053f:
        r5 = r4;
        if (r13 != r14) goto L_0x054a;
    L_0x0542:
        r0 = r13.mListAnchors;
        r0 = r0[r51];
        r1 = r13.mListAnchors;
        r1 = r1[r2];
    L_0x054a:
        if (r3 == 0) goto L_0x056f;
    L_0x054c:
        if (r5 == 0) goto L_0x056f;
    L_0x054e:
        r4 = 1056964608; // 0x3f000000 float:0.5 double:5.222099017E-315;
        r6 = r0.getMargin();
        if (r14 != 0) goto L_0x0557;
    L_0x0556:
        goto L_0x0558;
    L_0x0557:
        r12 = r14;
    L_0x0558:
        r7 = r12.mListAnchors;
        r2 = r7[r2];
        r7 = r2.getMargin();
        r2 = r0.mSolverVariable;
        r8 = r1.mSolverVariable;
        r10 = 5;
        r0 = r49;
        r1 = r2;
        r2 = r3;
        r3 = r6;
        r6 = r8;
        r8 = r10;
        r0.addCentering(r1, r2, r3, r4, r5, r6, r7, r8);
    L_0x056f:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.solver.widgets.Chain.applyChainConstraints(android.support.constraint.solver.widgets.ConstraintWidgetContainer, android.support.constraint.solver.LinearSystem, int, int, android.support.constraint.solver.widgets.ChainHead):void");
    }
}
