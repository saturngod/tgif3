package android.support.constraint.solver;

import android.support.constraint.solver.SolverVariable.Type;
import java.io.PrintStream;
import java.util.Arrays;

public class ArrayLinkedVariables {
    private static final boolean DEBUG = false;
    private static final boolean FULL_NEW_CHECK = false;
    private static final int NONE = -1;
    private int ROW_SIZE = 8;
    private SolverVariable candidate = null;
    int currentSize = 0;
    private int[] mArrayIndices = new int[this.ROW_SIZE];
    private int[] mArrayNextIndices = new int[this.ROW_SIZE];
    private float[] mArrayValues = new float[this.ROW_SIZE];
    private final Cache mCache;
    private boolean mDidFillOnce = false;
    private int mHead = -1;
    private int mLast = -1;
    private final ArrayRow mRow;

    ArrayLinkedVariables(ArrayRow arrayRow, Cache cache) {
        this.mRow = arrayRow;
        this.mCache = cache;
    }

    public final void put(SolverVariable solverVariable, float f) {
        if (f == 0.0f) {
            remove(solverVariable, true);
        } else if (this.mHead == -1) {
            this.mHead = 0;
            this.mArrayValues[this.mHead] = f;
            this.mArrayIndices[this.mHead] = solverVariable.id;
            this.mArrayNextIndices[this.mHead] = -1;
            solverVariable.usageInRowCount += Float.MIN_VALUE;
            solverVariable.addToRow(this.mRow);
            this.currentSize += 1;
            if (this.mDidFillOnce == null) {
                this.mLast += 1;
                if (this.mLast >= this.mArrayIndices.length) {
                    this.mDidFillOnce = true;
                    this.mLast = this.mArrayIndices.length - 1;
                }
            }
        } else {
            int i = this.mHead;
            int i2 = 0;
            int i3 = -1;
            while (i != -1 && i2 < this.currentSize) {
                if (this.mArrayIndices[i] == solverVariable.id) {
                    this.mArrayValues[i] = f;
                    return;
                }
                if (this.mArrayIndices[i] < solverVariable.id) {
                    i3 = i;
                }
                i = this.mArrayNextIndices[i];
                i2++;
            }
            i = this.mLast + 1;
            if (this.mDidFillOnce) {
                if (this.mArrayIndices[this.mLast] == -1) {
                    i = this.mLast;
                } else {
                    i = this.mArrayIndices.length;
                }
            }
            if (i >= this.mArrayIndices.length && this.currentSize < this.mArrayIndices.length) {
                for (i2 = 0; i2 < this.mArrayIndices.length; i2++) {
                    if (this.mArrayIndices[i2] == -1) {
                        i = i2;
                        break;
                    }
                }
            }
            if (i >= this.mArrayIndices.length) {
                i = this.mArrayIndices.length;
                this.ROW_SIZE *= 2;
                this.mDidFillOnce = false;
                this.mLast = i - 1;
                this.mArrayValues = Arrays.copyOf(this.mArrayValues, this.ROW_SIZE);
                this.mArrayIndices = Arrays.copyOf(this.mArrayIndices, this.ROW_SIZE);
                this.mArrayNextIndices = Arrays.copyOf(this.mArrayNextIndices, this.ROW_SIZE);
            }
            this.mArrayIndices[i] = solverVariable.id;
            this.mArrayValues[i] = f;
            if (i3 != -1) {
                this.mArrayNextIndices[i] = this.mArrayNextIndices[i3];
                this.mArrayNextIndices[i3] = i;
            } else {
                this.mArrayNextIndices[i] = this.mHead;
                this.mHead = i;
            }
            solverVariable.usageInRowCount += Float.MIN_VALUE;
            solverVariable.addToRow(this.mRow);
            this.currentSize += 1;
            if (this.mDidFillOnce == null) {
                this.mLast += 1;
            }
            if (this.currentSize >= this.mArrayIndices.length) {
                this.mDidFillOnce = true;
            }
            if (this.mLast >= this.mArrayIndices.length) {
                this.mDidFillOnce = true;
                this.mLast = this.mArrayIndices.length - 1;
            }
        }
    }

    final void add(SolverVariable solverVariable, float f, boolean z) {
        if (f != 0.0f) {
            if (this.mHead == -1) {
                this.mHead = 0;
                this.mArrayValues[this.mHead] = f;
                this.mArrayIndices[this.mHead] = solverVariable.id;
                this.mArrayNextIndices[this.mHead] = -1;
                solverVariable.usageInRowCount += Float.MIN_VALUE;
                solverVariable.addToRow(this.mRow);
                this.currentSize += 1;
                if (this.mDidFillOnce == null) {
                    this.mLast += 1;
                    if (this.mLast >= this.mArrayIndices.length) {
                        this.mDidFillOnce = true;
                        this.mLast = this.mArrayIndices.length - 1;
                    }
                }
                return;
            }
            int i = this.mHead;
            int i2 = 0;
            int i3 = -1;
            while (i != -1 && i2 < this.currentSize) {
                if (this.mArrayIndices[i] == solverVariable.id) {
                    float[] fArr = this.mArrayValues;
                    fArr[i] = fArr[i] + f;
                    if (this.mArrayValues[i] == 0.0f) {
                        if (i == this.mHead) {
                            this.mHead = this.mArrayNextIndices[i];
                        } else {
                            this.mArrayNextIndices[i3] = this.mArrayNextIndices[i];
                        }
                        if (z) {
                            solverVariable.removeFromRow(this.mRow);
                        }
                        if (this.mDidFillOnce != null) {
                            this.mLast = i;
                        }
                        solverVariable.usageInRowCount -= Float.MIN_VALUE;
                        this.currentSize -= 1;
                    }
                    return;
                }
                if (this.mArrayIndices[i] < solverVariable.id) {
                    i3 = i;
                }
                i = this.mArrayNextIndices[i];
                i2++;
            }
            z = this.mLast + true;
            if (this.mDidFillOnce) {
                if (this.mArrayIndices[this.mLast]) {
                    z = this.mLast;
                } else {
                    z = this.mArrayIndices.length;
                }
            }
            if (z >= this.mArrayIndices.length && this.currentSize < this.mArrayIndices.length) {
                for (boolean z2 = false; z2 < this.mArrayIndices.length; z2++) {
                    if (this.mArrayIndices[z2] == -1) {
                        z = z2;
                        break;
                    }
                }
            }
            if (z >= this.mArrayIndices.length) {
                z = this.mArrayIndices.length;
                this.ROW_SIZE *= 2;
                this.mDidFillOnce = false;
                this.mLast = z - 1;
                this.mArrayValues = Arrays.copyOf(this.mArrayValues, this.ROW_SIZE);
                this.mArrayIndices = Arrays.copyOf(this.mArrayIndices, this.ROW_SIZE);
                this.mArrayNextIndices = Arrays.copyOf(this.mArrayNextIndices, this.ROW_SIZE);
            }
            this.mArrayIndices[z] = solverVariable.id;
            this.mArrayValues[z] = f;
            if (i3 != -1) {
                this.mArrayNextIndices[z] = this.mArrayNextIndices[i3];
                this.mArrayNextIndices[i3] = z;
            } else {
                this.mArrayNextIndices[z] = this.mHead;
                this.mHead = z;
            }
            solverVariable.usageInRowCount += Float.MIN_VALUE;
            solverVariable.addToRow(this.mRow);
            this.currentSize += 1;
            if (this.mDidFillOnce == null) {
                this.mLast += 1;
            }
            if (this.mLast >= this.mArrayIndices.length) {
                this.mDidFillOnce = true;
                this.mLast = this.mArrayIndices.length - 1;
            }
        }
    }

    public final float remove(SolverVariable solverVariable, boolean z) {
        if (this.candidate == solverVariable) {
            this.candidate = null;
        }
        if (this.mHead == -1) {
            return 0.0f;
        }
        int i = this.mHead;
        int i2 = 0;
        int i3 = -1;
        while (i != -1 && i2 < this.currentSize) {
            if (this.mArrayIndices[i] == solverVariable.id) {
                if (i == this.mHead) {
                    this.mHead = this.mArrayNextIndices[i];
                } else {
                    this.mArrayNextIndices[i3] = this.mArrayNextIndices[i];
                }
                if (z) {
                    solverVariable.removeFromRow(this.mRow);
                }
                solverVariable.usageInRowCount--;
                this.currentSize--;
                this.mArrayIndices[i] = -1;
                if (this.mDidFillOnce != null) {
                    this.mLast = i;
                }
                return this.mArrayValues[i];
            }
            i2++;
            i3 = i;
            i = this.mArrayNextIndices[i];
        }
        return 0.0f;
    }

    public final void clear() {
        int i = this.mHead;
        int i2 = 0;
        while (i != -1 && i2 < this.currentSize) {
            SolverVariable solverVariable = this.mCache.mIndexedVariables[this.mArrayIndices[i]];
            if (solverVariable != null) {
                solverVariable.removeFromRow(this.mRow);
            }
            i = this.mArrayNextIndices[i];
            i2++;
        }
        this.mHead = -1;
        this.mLast = -1;
        this.mDidFillOnce = false;
        this.currentSize = 0;
    }

    final boolean containsKey(SolverVariable solverVariable) {
        if (this.mHead == -1) {
            return false;
        }
        int i = this.mHead;
        int i2 = 0;
        while (i != -1 && i2 < this.currentSize) {
            if (this.mArrayIndices[i] == solverVariable.id) {
                return true;
            }
            i = this.mArrayNextIndices[i];
            i2++;
        }
        return false;
    }

    boolean hasAtLeastOnePositiveVariable() {
        int i = this.mHead;
        int i2 = 0;
        while (i != -1 && i2 < this.currentSize) {
            if (this.mArrayValues[i] > 0.0f) {
                return true;
            }
            i = this.mArrayNextIndices[i];
            i2++;
        }
        return false;
    }

    void invert() {
        int i = this.mHead;
        int i2 = 0;
        while (i != -1 && i2 < this.currentSize) {
            float[] fArr = this.mArrayValues;
            fArr[i] = fArr[i] * -1.0f;
            i = this.mArrayNextIndices[i];
            i2++;
        }
    }

    void divideByAmount(float f) {
        int i = this.mHead;
        int i2 = 0;
        while (i != -1 && i2 < this.currentSize) {
            float[] fArr = this.mArrayValues;
            fArr[i] = fArr[i] / f;
            i = this.mArrayNextIndices[i];
            i2++;
        }
    }

    private boolean isNew(SolverVariable solverVariable, LinearSystem linearSystem) {
        return solverVariable.usageInRowCount <= 1 ? true : null;
    }

    SolverVariable chooseSubject(LinearSystem linearSystem) {
        int i = this.mHead;
        SolverVariable solverVariable = null;
        int i2 = 0;
        SolverVariable solverVariable2 = null;
        float f = 0.0f;
        boolean z = false;
        float f2 = 0.0f;
        boolean z2 = false;
        while (i != -1 && i2 < this.currentSize) {
            boolean isNew;
            boolean isNew2;
            float f3 = this.mArrayValues[i];
            SolverVariable solverVariable3 = this.mCache.mIndexedVariables[this.mArrayIndices[i]];
            if (f3 < 0.0f) {
                if (f3 > -0.001f) {
                    this.mArrayValues[i] = 0.0f;
                    solverVariable3.removeFromRow(this.mRow);
                }
                if (f3 != 0.0f) {
                    if (solverVariable3.mType == Type.UNRESTRICTED) {
                        if (solverVariable != null) {
                            isNew = isNew(solverVariable3, linearSystem);
                        } else if (f > f3) {
                            isNew = isNew(solverVariable3, linearSystem);
                        } else if (!z && isNew(solverVariable3, linearSystem)) {
                            f = f3;
                            solverVariable = solverVariable3;
                            z = true;
                        }
                        z = isNew;
                        f = f3;
                        solverVariable = solverVariable3;
                    } else if (solverVariable == null && f3 < 0.0f) {
                        if (solverVariable2 != null) {
                            isNew2 = isNew(solverVariable3, linearSystem);
                        } else if (f2 > f3) {
                            isNew2 = isNew(solverVariable3, linearSystem);
                        } else if (!z2 && isNew(solverVariable3, linearSystem)) {
                            f2 = f3;
                            solverVariable2 = solverVariable3;
                            z2 = true;
                        }
                        z2 = isNew2;
                        f2 = f3;
                        solverVariable2 = solverVariable3;
                    }
                }
                i = this.mArrayNextIndices[i];
                i2++;
            } else {
                if (f3 < 0.001f) {
                    this.mArrayValues[i] = 0.0f;
                    solverVariable3.removeFromRow(this.mRow);
                }
                if (f3 != 0.0f) {
                    if (solverVariable3.mType == Type.UNRESTRICTED) {
                        if (solverVariable2 != null) {
                            isNew2 = isNew(solverVariable3, linearSystem);
                        } else if (f2 > f3) {
                            f2 = f3;
                            solverVariable2 = solverVariable3;
                            z2 = true;
                        } else {
                            isNew2 = isNew(solverVariable3, linearSystem);
                        }
                        z2 = isNew2;
                        f2 = f3;
                        solverVariable2 = solverVariable3;
                    } else {
                        if (solverVariable != null) {
                            isNew = isNew(solverVariable3, linearSystem);
                        } else if (f > f3) {
                            f = f3;
                            solverVariable = solverVariable3;
                            z = true;
                        } else {
                            isNew = isNew(solverVariable3, linearSystem);
                        }
                        z = isNew;
                        f = f3;
                        solverVariable = solverVariable3;
                    }
                }
                i = this.mArrayNextIndices[i];
                i2++;
            }
            f3 = 0.0f;
            if (f3 != 0.0f) {
                if (solverVariable3.mType == Type.UNRESTRICTED) {
                    if (solverVariable != null) {
                        isNew = isNew(solverVariable3, linearSystem);
                    } else if (f > f3) {
                        isNew = isNew(solverVariable3, linearSystem);
                    } else {
                        f = f3;
                        solverVariable = solverVariable3;
                        z = true;
                    }
                    z = isNew;
                    f = f3;
                    solverVariable = solverVariable3;
                } else {
                    if (solverVariable2 != null) {
                        isNew2 = isNew(solverVariable3, linearSystem);
                    } else if (f2 > f3) {
                        isNew2 = isNew(solverVariable3, linearSystem);
                    } else {
                        f2 = f3;
                        solverVariable2 = solverVariable3;
                        z2 = true;
                    }
                    z2 = isNew2;
                    f2 = f3;
                    solverVariable2 = solverVariable3;
                }
            }
            i = this.mArrayNextIndices[i];
            i2++;
        }
        return solverVariable != null ? solverVariable : solverVariable2;
    }

    final void updateFromRow(ArrayRow arrayRow, ArrayRow arrayRow2, boolean z) {
        int i = this.mHead;
        while (true) {
            int i2 = 0;
            while (i != -1 && i2 < this.currentSize) {
                if (this.mArrayIndices[i] == arrayRow2.variable.id) {
                    float f = this.mArrayValues[i];
                    remove(arrayRow2.variable, z);
                    ArrayLinkedVariables arrayLinkedVariables = arrayRow2.variables;
                    int i3 = arrayLinkedVariables.mHead;
                    int i4 = 0;
                    while (i3 != -1 && i4 < arrayLinkedVariables.currentSize) {
                        add(this.mCache.mIndexedVariables[arrayLinkedVariables.mArrayIndices[i3]], arrayLinkedVariables.mArrayValues[i3] * f, z);
                        i3 = arrayLinkedVariables.mArrayNextIndices[i3];
                        i4++;
                    }
                    arrayRow.constantValue += arrayRow2.constantValue * f;
                    if (z) {
                        arrayRow2.variable.removeFromRow(arrayRow);
                    }
                    i = this.mHead;
                } else {
                    i = this.mArrayNextIndices[i];
                    i2++;
                }
            }
            return;
        }
    }

    void updateFromSystem(ArrayRow arrayRow, ArrayRow[] arrayRowArr) {
        int i = this.mHead;
        while (true) {
            int i2 = 0;
            while (i != -1 && i2 < this.currentSize) {
                SolverVariable solverVariable = this.mCache.mIndexedVariables[this.mArrayIndices[i]];
                if (solverVariable.definitionId != -1) {
                    float f = this.mArrayValues[i];
                    remove(solverVariable, true);
                    ArrayRow arrayRow2 = arrayRowArr[solverVariable.definitionId];
                    if (!arrayRow2.isSimpleDefinition) {
                        ArrayLinkedVariables arrayLinkedVariables = arrayRow2.variables;
                        int i3 = arrayLinkedVariables.mHead;
                        int i4 = 0;
                        while (i3 != -1 && i4 < arrayLinkedVariables.currentSize) {
                            add(this.mCache.mIndexedVariables[arrayLinkedVariables.mArrayIndices[i3]], arrayLinkedVariables.mArrayValues[i3] * f, true);
                            i3 = arrayLinkedVariables.mArrayNextIndices[i3];
                            i4++;
                        }
                    }
                    arrayRow.constantValue += arrayRow2.constantValue * f;
                    arrayRow2.variable.removeFromRow(arrayRow);
                    i = this.mHead;
                } else {
                    i = this.mArrayNextIndices[i];
                    i2++;
                }
            }
            return;
        }
    }

    SolverVariable getPivotCandidate() {
        if (this.candidate != null) {
            return this.candidate;
        }
        int i = this.mHead;
        int i2 = 0;
        SolverVariable solverVariable = null;
        while (i != -1 && i2 < this.currentSize) {
            if (this.mArrayValues[i] < 0.0f) {
                SolverVariable solverVariable2 = this.mCache.mIndexedVariables[this.mArrayIndices[i]];
                if (solverVariable == null || solverVariable.strength < solverVariable2.strength) {
                    solverVariable = solverVariable2;
                }
            }
            i = this.mArrayNextIndices[i];
            i2++;
        }
        return solverVariable;
    }

    SolverVariable getPivotCandidate(boolean[] zArr, SolverVariable solverVariable) {
        int i = this.mHead;
        int i2 = 0;
        SolverVariable solverVariable2 = null;
        float f = 0.0f;
        while (i != -1 && i2 < this.currentSize) {
            if (this.mArrayValues[i] < 0.0f) {
                SolverVariable solverVariable3 = this.mCache.mIndexedVariables[this.mArrayIndices[i]];
                if ((zArr == null || !zArr[solverVariable3.id]) && solverVariable3 != solverVariable && (solverVariable3.mType == Type.SLACK || solverVariable3.mType == Type.ERROR)) {
                    float f2 = this.mArrayValues[i];
                    if (f2 < f) {
                        solverVariable2 = solverVariable3;
                        f = f2;
                    }
                }
            }
            i = this.mArrayNextIndices[i];
            i2++;
        }
        return solverVariable2;
    }

    final SolverVariable getVariable(int i) {
        int i2 = this.mHead;
        int i3 = 0;
        while (i2 != -1 && i3 < this.currentSize) {
            if (i3 == i) {
                return this.mCache.mIndexedVariables[this.mArrayIndices[i2]];
            }
            i2 = this.mArrayNextIndices[i2];
            i3++;
        }
        return 0;
    }

    final float getVariableValue(int i) {
        int i2 = this.mHead;
        int i3 = 0;
        while (i2 != -1 && i3 < this.currentSize) {
            if (i3 == i) {
                return this.mArrayValues[i2];
            }
            i2 = this.mArrayNextIndices[i2];
            i3++;
        }
        return 0;
    }

    public final float get(SolverVariable solverVariable) {
        int i = this.mHead;
        int i2 = 0;
        while (i != -1 && i2 < this.currentSize) {
            if (this.mArrayIndices[i] == solverVariable.id) {
                return this.mArrayValues[i];
            }
            i = this.mArrayNextIndices[i];
            i2++;
        }
        return null;
    }

    int sizeInBytes() {
        return (((this.mArrayIndices.length * 4) * 3) + 0) + 36;
    }

    public void display() {
        int i = this.currentSize;
        System.out.print("{ ");
        for (int i2 = 0; i2 < i; i2++) {
            SolverVariable variable = getVariable(i2);
            if (variable != null) {
                PrintStream printStream = System.out;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(variable);
                stringBuilder.append(" = ");
                stringBuilder.append(getVariableValue(i2));
                stringBuilder.append(" ");
                printStream.print(stringBuilder.toString());
            }
        }
        System.out.println(" }");
    }

    public String toString() {
        String str = "";
        int i = this.mHead;
        int i2 = 0;
        while (i != -1 && i2 < this.currentSize) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append(" -> ");
            str = stringBuilder.toString();
            stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append(this.mArrayValues[i]);
            stringBuilder.append(" : ");
            str = stringBuilder.toString();
            stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append(this.mCache.mIndexedVariables[this.mArrayIndices[i]]);
            str = stringBuilder.toString();
            i = this.mArrayNextIndices[i];
            i2++;
        }
        return str;
    }
}
