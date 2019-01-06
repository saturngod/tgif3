package android.support.constraint.solver;

import android.support.constraint.solver.SolverVariable.Type;
import android.support.constraint.solver.widgets.ConstraintAnchor;
import android.support.constraint.solver.widgets.ConstraintWidget;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.HashMap;

public class LinearSystem {
    private static final boolean DEBUG = false;
    public static final boolean FULL_DEBUG = false;
    private static int POOL_SIZE = 1000;
    public static Metrics sMetrics;
    private int TABLE_SIZE;
    public boolean graphOptimizer;
    private boolean[] mAlreadyTestedCandidates;
    final Cache mCache;
    private Row mGoal;
    private int mMaxColumns;
    private int mMaxRows;
    int mNumColumns;
    int mNumRows;
    private SolverVariable[] mPoolVariables;
    private int mPoolVariablesCount;
    ArrayRow[] mRows;
    private final Row mTempGoal;
    private HashMap<String, SolverVariable> mVariables;
    int mVariablesID;
    private ArrayRow[] tempClientsCopy;

    interface Row {
        void addError(SolverVariable solverVariable);

        void clear();

        SolverVariable getKey();

        SolverVariable getPivotCandidate(LinearSystem linearSystem, boolean[] zArr);

        void initFromRow(Row row);

        boolean isEmpty();
    }

    private String getDisplayStrength(int i) {
        return i == 1 ? "LOW" : i == 2 ? "MEDIUM" : i == 3 ? "HIGH" : i == 4 ? "HIGHEST" : i == 5 ? "EQUALITY" : i == 6 ? "FIXED" : "NONE";
    }

    public LinearSystem() {
        this.mVariablesID = 0;
        this.mVariables = null;
        this.TABLE_SIZE = 32;
        this.mMaxColumns = this.TABLE_SIZE;
        this.mRows = null;
        this.graphOptimizer = false;
        this.mAlreadyTestedCandidates = new boolean[this.TABLE_SIZE];
        this.mNumColumns = 1;
        this.mNumRows = 0;
        this.mMaxRows = this.TABLE_SIZE;
        this.mPoolVariables = new SolverVariable[POOL_SIZE];
        this.mPoolVariablesCount = 0;
        this.tempClientsCopy = new ArrayRow[this.TABLE_SIZE];
        this.mRows = new ArrayRow[this.TABLE_SIZE];
        releaseRows();
        this.mCache = new Cache();
        this.mGoal = new GoalRow(this.mCache);
        this.mTempGoal = new ArrayRow(this.mCache);
    }

    public void fillMetrics(Metrics metrics) {
        sMetrics = metrics;
    }

    public static Metrics getMetrics() {
        return sMetrics;
    }

    private void increaseTableSize() {
        this.TABLE_SIZE *= 2;
        this.mRows = (ArrayRow[]) Arrays.copyOf(this.mRows, this.TABLE_SIZE);
        this.mCache.mIndexedVariables = (SolverVariable[]) Arrays.copyOf(this.mCache.mIndexedVariables, this.TABLE_SIZE);
        this.mAlreadyTestedCandidates = new boolean[this.TABLE_SIZE];
        this.mMaxColumns = this.TABLE_SIZE;
        this.mMaxRows = this.TABLE_SIZE;
        if (sMetrics != null) {
            Metrics metrics = sMetrics;
            metrics.tableSizeIncrease++;
            sMetrics.maxTableSize = Math.max(sMetrics.maxTableSize, (long) this.TABLE_SIZE);
            sMetrics.lastTableSize = sMetrics.maxTableSize;
        }
    }

    private void releaseRows() {
        for (int i = 0; i < this.mRows.length; i++) {
            Object obj = this.mRows[i];
            if (obj != null) {
                this.mCache.arrayRowPool.release(obj);
            }
            this.mRows[i] = null;
        }
    }

    public void reset() {
        int i;
        for (SolverVariable solverVariable : this.mCache.mIndexedVariables) {
            if (solverVariable != null) {
                solverVariable.reset();
            }
        }
        this.mCache.solverVariablePool.releaseAll(this.mPoolVariables, this.mPoolVariablesCount);
        this.mPoolVariablesCount = 0;
        Arrays.fill(this.mCache.mIndexedVariables, null);
        if (this.mVariables != null) {
            this.mVariables.clear();
        }
        this.mVariablesID = 0;
        this.mGoal.clear();
        this.mNumColumns = 1;
        for (i = 0; i < this.mNumRows; i++) {
            this.mRows[i].used = false;
        }
        releaseRows();
        this.mNumRows = 0;
    }

    public SolverVariable createObjectVariable(Object obj) {
        SolverVariable solverVariable = null;
        if (obj == null) {
            return null;
        }
        if (this.mNumColumns + 1 >= this.mMaxColumns) {
            increaseTableSize();
        }
        if (obj instanceof ConstraintAnchor) {
            ConstraintAnchor constraintAnchor = (ConstraintAnchor) obj;
            solverVariable = constraintAnchor.getSolverVariable();
            if (solverVariable == null) {
                constraintAnchor.resetSolverVariable(this.mCache);
                solverVariable = constraintAnchor.getSolverVariable();
            }
            if (solverVariable.id == -1 || solverVariable.id > this.mVariablesID || this.mCache.mIndexedVariables[solverVariable.id] == null) {
                if (solverVariable.id != -1) {
                    solverVariable.reset();
                }
                this.mVariablesID++;
                this.mNumColumns++;
                solverVariable.id = this.mVariablesID;
                solverVariable.mType = Type.UNRESTRICTED;
                this.mCache.mIndexedVariables[this.mVariablesID] = solverVariable;
            }
        }
        return solverVariable;
    }

    public ArrayRow createRow() {
        ArrayRow arrayRow = (ArrayRow) this.mCache.arrayRowPool.acquire();
        if (arrayRow == null) {
            arrayRow = new ArrayRow(this.mCache);
        } else {
            arrayRow.reset();
        }
        SolverVariable.increaseErrorId();
        return arrayRow;
    }

    public SolverVariable createSlackVariable() {
        if (sMetrics != null) {
            Metrics metrics = sMetrics;
            metrics.slackvariables++;
        }
        if (this.mNumColumns + 1 >= this.mMaxColumns) {
            increaseTableSize();
        }
        SolverVariable acquireSolverVariable = acquireSolverVariable(Type.SLACK, null);
        this.mVariablesID++;
        this.mNumColumns++;
        acquireSolverVariable.id = this.mVariablesID;
        this.mCache.mIndexedVariables[this.mVariablesID] = acquireSolverVariable;
        return acquireSolverVariable;
    }

    public SolverVariable createExtraVariable() {
        if (sMetrics != null) {
            Metrics metrics = sMetrics;
            metrics.extravariables++;
        }
        if (this.mNumColumns + 1 >= this.mMaxColumns) {
            increaseTableSize();
        }
        SolverVariable acquireSolverVariable = acquireSolverVariable(Type.SLACK, null);
        this.mVariablesID++;
        this.mNumColumns++;
        acquireSolverVariable.id = this.mVariablesID;
        this.mCache.mIndexedVariables[this.mVariablesID] = acquireSolverVariable;
        return acquireSolverVariable;
    }

    private void addError(ArrayRow arrayRow) {
        arrayRow.addError(this, 0);
    }

    private void addSingleError(ArrayRow arrayRow, int i) {
        addSingleError(arrayRow, i, 0);
    }

    void addSingleError(ArrayRow arrayRow, int i, int i2) {
        arrayRow.addSingleError(createErrorVariable(i2, null), i);
    }

    private SolverVariable createVariable(String str, Type type) {
        if (sMetrics != null) {
            Metrics metrics = sMetrics;
            metrics.variables++;
        }
        if (this.mNumColumns + 1 >= this.mMaxColumns) {
            increaseTableSize();
        }
        type = acquireSolverVariable(type, null);
        type.setName(str);
        this.mVariablesID++;
        this.mNumColumns++;
        type.id = this.mVariablesID;
        if (this.mVariables == null) {
            this.mVariables = new HashMap();
        }
        this.mVariables.put(str, type);
        this.mCache.mIndexedVariables[this.mVariablesID] = type;
        return type;
    }

    public SolverVariable createErrorVariable(int i, String str) {
        if (sMetrics != null) {
            Metrics metrics = sMetrics;
            metrics.errors++;
        }
        if (this.mNumColumns + 1 >= this.mMaxColumns) {
            increaseTableSize();
        }
        str = acquireSolverVariable(Type.ERROR, str);
        this.mVariablesID++;
        this.mNumColumns++;
        str.id = this.mVariablesID;
        str.strength = i;
        this.mCache.mIndexedVariables[this.mVariablesID] = str;
        this.mGoal.addError(str);
        return str;
    }

    private SolverVariable acquireSolverVariable(Type type, String str) {
        SolverVariable solverVariable = (SolverVariable) this.mCache.solverVariablePool.acquire();
        if (solverVariable == null) {
            solverVariable = new SolverVariable(type, str);
            solverVariable.setType(type, str);
        } else {
            solverVariable.reset();
            solverVariable.setType(type, str);
        }
        if (this.mPoolVariablesCount >= POOL_SIZE) {
            POOL_SIZE *= 2;
            this.mPoolVariables = (SolverVariable[]) Arrays.copyOf(this.mPoolVariables, POOL_SIZE);
        }
        type = this.mPoolVariables;
        str = this.mPoolVariablesCount;
        this.mPoolVariablesCount = str + 1;
        type[str] = solverVariable;
        return solverVariable;
    }

    Row getGoal() {
        return this.mGoal;
    }

    ArrayRow getRow(int i) {
        return this.mRows[i];
    }

    float getValueFor(String str) {
        str = getVariable(str, Type.UNRESTRICTED);
        if (str == null) {
            return null;
        }
        return str.computedValue;
    }

    public int getObjectVariableValue(Object obj) {
        obj = ((ConstraintAnchor) obj).getSolverVariable();
        return obj != null ? (int) (obj.computedValue + 0.5f) : null;
    }

    SolverVariable getVariable(String str, Type type) {
        if (this.mVariables == null) {
            this.mVariables = new HashMap();
        }
        SolverVariable solverVariable = (SolverVariable) this.mVariables.get(str);
        return solverVariable == null ? createVariable(str, type) : solverVariable;
    }

    public void minimize() throws Exception {
        if (sMetrics != null) {
            Metrics metrics = sMetrics;
            metrics.minimize++;
        }
        if (this.graphOptimizer) {
            if (sMetrics != null) {
                metrics = sMetrics;
                metrics.graphOptimizer++;
            }
            Object obj = null;
            for (int i = 0; i < this.mNumRows; i++) {
                if (!this.mRows[i].isSimpleDefinition) {
                    break;
                }
            }
            obj = 1;
            if (obj == null) {
                minimizeGoal(this.mGoal);
                return;
            }
            if (sMetrics != null) {
                metrics = sMetrics;
                metrics.fullySolved++;
            }
            computeValues();
            return;
        }
        minimizeGoal(this.mGoal);
    }

    void minimizeGoal(Row row) throws Exception {
        if (sMetrics != null) {
            Metrics metrics = sMetrics;
            metrics.minimizeGoal++;
            sMetrics.maxVariables = Math.max(sMetrics.maxVariables, (long) this.mNumColumns);
            sMetrics.maxRows = Math.max(sMetrics.maxRows, (long) this.mNumRows);
        }
        updateRowFromVariables((ArrayRow) row);
        enforceBFS(row);
        optimize(row, false);
        computeValues();
    }

    private final void updateRowFromVariables(ArrayRow arrayRow) {
        if (this.mNumRows > 0) {
            arrayRow.variables.updateFromSystem(arrayRow, this.mRows);
            if (arrayRow.variables.currentSize == 0) {
                arrayRow.isSimpleDefinition = true;
            }
        }
    }

    public void addConstraint(ArrayRow arrayRow) {
        if (arrayRow != null) {
            if (sMetrics != null) {
                Metrics metrics = sMetrics;
                metrics.constraints++;
                if (arrayRow.isSimpleDefinition) {
                    metrics = sMetrics;
                    metrics.simpleconstraints++;
                }
            }
            if (this.mNumRows + 1 >= this.mMaxRows || this.mNumColumns + 1 >= this.mMaxColumns) {
                increaseTableSize();
            }
            Object obj = null;
            if (!arrayRow.isSimpleDefinition) {
                updateRowFromVariables(arrayRow);
                if (!arrayRow.isEmpty()) {
                    arrayRow.ensurePositiveConstant();
                    if (arrayRow.chooseSubject(this)) {
                        SolverVariable createExtraVariable = createExtraVariable();
                        arrayRow.variable = createExtraVariable;
                        addRow(arrayRow);
                        this.mTempGoal.initFromRow(arrayRow);
                        optimize(this.mTempGoal, true);
                        if (createExtraVariable.definitionId == -1) {
                            if (arrayRow.variable == createExtraVariable) {
                                createExtraVariable = arrayRow.pickPivot(createExtraVariable);
                                if (createExtraVariable != null) {
                                    if (sMetrics != null) {
                                        Metrics metrics2 = sMetrics;
                                        metrics2.pivots++;
                                    }
                                    arrayRow.pivot(createExtraVariable);
                                }
                            }
                            if (!arrayRow.isSimpleDefinition) {
                                arrayRow.variable.updateReferencesWithNewDefinition(arrayRow);
                            }
                            this.mNumRows--;
                        }
                        obj = 1;
                    }
                    if (!arrayRow.hasKeyVariable()) {
                        return;
                    }
                }
                return;
            }
            if (obj == null) {
                addRow(arrayRow);
            }
        }
    }

    private final void addRow(ArrayRow arrayRow) {
        if (this.mRows[this.mNumRows] != null) {
            this.mCache.arrayRowPool.release(this.mRows[this.mNumRows]);
        }
        this.mRows[this.mNumRows] = arrayRow;
        arrayRow.variable.definitionId = this.mNumRows;
        this.mNumRows++;
        arrayRow.variable.updateReferencesWithNewDefinition(arrayRow);
    }

    private final int optimize(Row row, boolean z) {
        if (sMetrics) {
            z = sMetrics;
            z.optimize++;
        }
        for (int i = 0; i < this.mNumColumns; i++) {
            this.mAlreadyTestedCandidates[i] = false;
        }
        Object obj = null;
        int i2 = 0;
        while (obj == null) {
            if (sMetrics != null) {
                Metrics metrics = sMetrics;
                metrics.iterations++;
            }
            i2++;
            if (i2 >= this.mNumColumns * 2) {
                return i2;
            }
            if (row.getKey() != null) {
                this.mAlreadyTestedCandidates[row.getKey().id] = true;
            }
            SolverVariable pivotCandidate = row.getPivotCandidate(this, this.mAlreadyTestedCandidates);
            if (pivotCandidate != null) {
                if (this.mAlreadyTestedCandidates[pivotCandidate.id]) {
                    return i2;
                }
                this.mAlreadyTestedCandidates[pivotCandidate.id] = true;
            }
            if (pivotCandidate != null) {
                int i3 = -1;
                float f = Float.MAX_VALUE;
                for (int i4 = 0; i4 < this.mNumRows; i4++) {
                    ArrayRow arrayRow = this.mRows[i4];
                    if (arrayRow.variable.mType != Type.UNRESTRICTED) {
                        if (!arrayRow.isSimpleDefinition) {
                            if (arrayRow.hasVariable(pivotCandidate)) {
                                float f2 = arrayRow.variables.get(pivotCandidate);
                                if (f2 < 0.0f) {
                                    float f3 = (-arrayRow.constantValue) / f2;
                                    if (f3 < f) {
                                        i3 = i4;
                                        f = f3;
                                    }
                                }
                            }
                        }
                    }
                }
                if (i3 > -1) {
                    ArrayRow arrayRow2 = this.mRows[i3];
                    arrayRow2.variable.definitionId = -1;
                    if (sMetrics != null) {
                        Metrics metrics2 = sMetrics;
                        metrics2.pivots++;
                    }
                    arrayRow2.pivot(pivotCandidate);
                    arrayRow2.variable.definitionId = i3;
                    arrayRow2.variable.updateReferencesWithNewDefinition(arrayRow2);
                }
            }
            obj = 1;
        }
        return i2;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int enforceBFS(android.support.constraint.solver.LinearSystem.Row r19) throws java.lang.Exception {
        /*
        r18 = this;
        r0 = r18;
        r2 = 0;
    L_0x0003:
        r3 = r0.mNumRows;
        r4 = 0;
        if (r2 >= r3) goto L_0x0024;
    L_0x0008:
        r3 = r0.mRows;
        r3 = r3[r2];
        r3 = r3.variable;
        r3 = r3.mType;
        r6 = android.support.constraint.solver.SolverVariable.Type.UNRESTRICTED;
        if (r3 != r6) goto L_0x0015;
    L_0x0014:
        goto L_0x0021;
    L_0x0015:
        r3 = r0.mRows;
        r3 = r3[r2];
        r3 = r3.constantValue;
        r3 = (r3 > r4 ? 1 : (r3 == r4 ? 0 : -1));
        if (r3 >= 0) goto L_0x0021;
    L_0x001f:
        r2 = 1;
        goto L_0x0025;
    L_0x0021:
        r2 = r2 + 1;
        goto L_0x0003;
    L_0x0024:
        r2 = 0;
    L_0x0025:
        if (r2 == 0) goto L_0x00db;
    L_0x0027:
        r2 = 0;
        r3 = 0;
    L_0x0029:
        if (r2 != 0) goto L_0x00d8;
    L_0x002b:
        r6 = sMetrics;
        r7 = 1;
        if (r6 == 0) goto L_0x0038;
    L_0x0031:
        r6 = sMetrics;
        r9 = r6.bfs;
        r9 = r9 + r7;
        r6.bfs = r9;
    L_0x0038:
        r3 = r3 + 1;
        r6 = 2139095039; // 0x7f7fffff float:3.4028235E38 double:1.056853372E-314;
        r9 = -1;
        r6 = 0;
        r10 = -1;
        r11 = -1;
        r12 = 2139095039; // 0x7f7fffff float:3.4028235E38 double:1.056853372E-314;
        r13 = 0;
    L_0x0045:
        r14 = r0.mNumRows;
        if (r6 >= r14) goto L_0x00a3;
    L_0x0049:
        r14 = r0.mRows;
        r14 = r14[r6];
        r15 = r14.variable;
        r15 = r15.mType;
        r1 = android.support.constraint.solver.SolverVariable.Type.UNRESTRICTED;
        if (r15 != r1) goto L_0x0056;
    L_0x0055:
        goto L_0x009d;
    L_0x0056:
        r1 = r14.isSimpleDefinition;
        if (r1 == 0) goto L_0x005b;
    L_0x005a:
        goto L_0x009d;
    L_0x005b:
        r1 = r14.constantValue;
        r1 = (r1 > r4 ? 1 : (r1 == r4 ? 0 : -1));
        if (r1 >= 0) goto L_0x009d;
    L_0x0061:
        r1 = 1;
    L_0x0062:
        r15 = r0.mNumColumns;
        if (r1 >= r15) goto L_0x009d;
    L_0x0066:
        r15 = r0.mCache;
        r15 = r15.mIndexedVariables;
        r15 = r15[r1];
        r5 = r14.variables;
        r5 = r5.get(r15);
        r17 = (r5 > r4 ? 1 : (r5 == r4 ? 0 : -1));
        if (r17 > 0) goto L_0x0077;
    L_0x0076:
        goto L_0x0097;
    L_0x0077:
        r4 = r13;
        r13 = r12;
        r12 = r11;
        r11 = r10;
        r10 = 0;
    L_0x007c:
        r7 = 7;
        if (r10 >= r7) goto L_0x0093;
    L_0x007f:
        r7 = r15.strengthVector;
        r7 = r7[r10];
        r7 = r7 / r5;
        r8 = (r7 > r13 ? 1 : (r7 == r13 ? 0 : -1));
        if (r8 >= 0) goto L_0x008a;
    L_0x0088:
        if (r10 == r4) goto L_0x008c;
    L_0x008a:
        if (r10 <= r4) goto L_0x0090;
    L_0x008c:
        r12 = r1;
        r11 = r6;
        r13 = r7;
        r4 = r10;
    L_0x0090:
        r10 = r10 + 1;
        goto L_0x007c;
    L_0x0093:
        r10 = r11;
        r11 = r12;
        r12 = r13;
        r13 = r4;
    L_0x0097:
        r1 = r1 + 1;
        r4 = 0;
        r7 = 1;
        goto L_0x0062;
    L_0x009d:
        r6 = r6 + 1;
        r4 = 0;
        r7 = 1;
        goto L_0x0045;
    L_0x00a3:
        if (r10 == r9) goto L_0x00cd;
    L_0x00a5:
        r1 = r0.mRows;
        r1 = r1[r10];
        r4 = r1.variable;
        r4.definitionId = r9;
        r4 = sMetrics;
        if (r4 == 0) goto L_0x00ba;
    L_0x00b1:
        r4 = sMetrics;
        r5 = r4.pivots;
        r7 = 1;
        r5 = r5 + r7;
        r4.pivots = r5;
    L_0x00ba:
        r4 = r0.mCache;
        r4 = r4.mIndexedVariables;
        r4 = r4[r11];
        r1.pivot(r4);
        r4 = r1.variable;
        r4.definitionId = r10;
        r4 = r1.variable;
        r4.updateReferencesWithNewDefinition(r1);
        goto L_0x00ce;
    L_0x00cd:
        r2 = 1;
    L_0x00ce:
        r1 = r0.mNumColumns;
        r1 = r1 / 2;
        if (r3 <= r1) goto L_0x00d5;
    L_0x00d4:
        r2 = 1;
    L_0x00d5:
        r4 = 0;
        goto L_0x0029;
    L_0x00d8:
        r16 = r3;
        goto L_0x00dd;
    L_0x00db:
        r16 = 0;
    L_0x00dd:
        return r16;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.solver.LinearSystem.enforceBFS(android.support.constraint.solver.LinearSystem$Row):int");
    }

    private void computeValues() {
        for (int i = 0; i < this.mNumRows; i++) {
            ArrayRow arrayRow = this.mRows[i];
            arrayRow.variable.computedValue = arrayRow.constantValue;
        }
    }

    private void displayRows() {
        displaySolverVariables();
        String str = "";
        for (int i = 0; i < this.mNumRows; i++) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append(this.mRows[i]);
            str = stringBuilder.toString();
            stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append("\n");
            str = stringBuilder.toString();
        }
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append(str);
        stringBuilder2.append(this.mGoal);
        stringBuilder2.append("\n");
        System.out.println(stringBuilder2.toString());
    }

    void displayReadableRows() {
        displaySolverVariables();
        String str = " #  ";
        for (int i = 0; i < this.mNumRows; i++) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append(this.mRows[i].toReadableString());
            str = stringBuilder.toString();
            stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append("\n #  ");
            str = stringBuilder.toString();
        }
        if (this.mGoal != null) {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append(str);
            stringBuilder2.append(this.mGoal);
            stringBuilder2.append("\n");
            str = stringBuilder2.toString();
        }
        System.out.println(str);
    }

    public void displayVariablesReadableRows() {
        displaySolverVariables();
        String str = "";
        for (int i = 0; i < this.mNumRows; i++) {
            if (this.mRows[i].variable.mType == Type.UNRESTRICTED) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(str);
                stringBuilder.append(this.mRows[i].toReadableString());
                str = stringBuilder.toString();
                stringBuilder = new StringBuilder();
                stringBuilder.append(str);
                stringBuilder.append("\n");
                str = stringBuilder.toString();
            }
        }
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append(str);
        stringBuilder2.append(this.mGoal);
        stringBuilder2.append("\n");
        System.out.println(stringBuilder2.toString());
    }

    public int getMemoryUsed() {
        int i = 0;
        for (int i2 = 0; i2 < this.mNumRows; i2++) {
            if (this.mRows[i2] != null) {
                i += this.mRows[i2].sizeInBytes();
            }
        }
        return i;
    }

    public int getNumEquations() {
        return this.mNumRows;
    }

    public int getNumVariables() {
        return this.mVariablesID;
    }

    void displaySystemInformations() {
        int i;
        int i2 = 0;
        for (i = 0; i < this.TABLE_SIZE; i++) {
            if (this.mRows[i] != null) {
                i2 += this.mRows[i].sizeInBytes();
            }
        }
        int i3 = 0;
        for (i = 0; i < this.mNumRows; i++) {
            if (this.mRows[i] != null) {
                i3 += this.mRows[i].sizeInBytes();
            }
        }
        PrintStream printStream = System.out;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Linear System -> Table size: ");
        stringBuilder.append(this.TABLE_SIZE);
        stringBuilder.append(" (");
        stringBuilder.append(getDisplaySize(this.TABLE_SIZE * this.TABLE_SIZE));
        stringBuilder.append(") -- row sizes: ");
        stringBuilder.append(getDisplaySize(i2));
        stringBuilder.append(", actual size: ");
        stringBuilder.append(getDisplaySize(i3));
        stringBuilder.append(" rows: ");
        stringBuilder.append(this.mNumRows);
        stringBuilder.append("/");
        stringBuilder.append(this.mMaxRows);
        stringBuilder.append(" cols: ");
        stringBuilder.append(this.mNumColumns);
        stringBuilder.append("/");
        stringBuilder.append(this.mMaxColumns);
        stringBuilder.append(" ");
        stringBuilder.append(0);
        stringBuilder.append(" occupied cells, ");
        stringBuilder.append(getDisplaySize(0));
        printStream.println(stringBuilder.toString());
    }

    private void displaySolverVariables() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Display Rows (");
        stringBuilder.append(this.mNumRows);
        stringBuilder.append("x");
        stringBuilder.append(this.mNumColumns);
        stringBuilder.append(")\n");
        System.out.println(stringBuilder.toString());
    }

    private String getDisplaySize(int i) {
        i *= 4;
        int i2 = i / 1024;
        int i3 = i2 / 1024;
        if (i3 > 0) {
            i = new StringBuilder();
            i.append("");
            i.append(i3);
            i.append(" Mb");
            return i.toString();
        } else if (i2 > 0) {
            i = new StringBuilder();
            i.append("");
            i.append(i2);
            i.append(" Kb");
            return i.toString();
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("");
            stringBuilder.append(i);
            stringBuilder.append(" bytes");
            return stringBuilder.toString();
        }
    }

    public Cache getCache() {
        return this.mCache;
    }

    public void addGreaterThan(SolverVariable solverVariable, SolverVariable solverVariable2, int i, int i2) {
        ArrayRow createRow = createRow();
        SolverVariable createSlackVariable = createSlackVariable();
        createSlackVariable.strength = 0;
        createRow.createRowGreaterThan(solverVariable, solverVariable2, createSlackVariable, i);
        if (i2 != 6) {
            addSingleError(createRow, (int) (createRow.variables.get(createSlackVariable) * -1082130432), i2);
        }
        addConstraint(createRow);
    }

    public void addGreaterThan(SolverVariable solverVariable, int i) {
        ArrayRow createRow = createRow();
        SolverVariable createSlackVariable = createSlackVariable();
        createSlackVariable.strength = 0;
        createRow.createRowGreaterThan(solverVariable, i, createSlackVariable);
        addConstraint(createRow);
    }

    public void addGreaterBarrier(SolverVariable solverVariable, SolverVariable solverVariable2, boolean z) {
        ArrayRow createRow = createRow();
        SolverVariable createSlackVariable = createSlackVariable();
        createSlackVariable.strength = 0;
        createRow.createRowGreaterThan(solverVariable, solverVariable2, createSlackVariable, 0);
        if (z) {
            addSingleError(createRow, (int) (createRow.variables.get(createSlackVariable) * -1082130432), 1);
        }
        addConstraint(createRow);
    }

    public void addLowerThan(SolverVariable solverVariable, SolverVariable solverVariable2, int i, int i2) {
        ArrayRow createRow = createRow();
        SolverVariable createSlackVariable = createSlackVariable();
        createSlackVariable.strength = 0;
        createRow.createRowLowerThan(solverVariable, solverVariable2, createSlackVariable, i);
        if (i2 != 6) {
            addSingleError(createRow, (int) (createRow.variables.get(createSlackVariable) * -1082130432), i2);
        }
        addConstraint(createRow);
    }

    public void addLowerBarrier(SolverVariable solverVariable, SolverVariable solverVariable2, boolean z) {
        ArrayRow createRow = createRow();
        SolverVariable createSlackVariable = createSlackVariable();
        createSlackVariable.strength = 0;
        createRow.createRowLowerThan(solverVariable, solverVariable2, createSlackVariable, 0);
        if (z) {
            addSingleError(createRow, (int) (createRow.variables.get(createSlackVariable) * -1082130432), 1);
        }
        addConstraint(createRow);
    }

    public void addCentering(SolverVariable solverVariable, SolverVariable solverVariable2, int i, float f, SolverVariable solverVariable3, SolverVariable solverVariable4, int i2, int i3) {
        LinearSystem linearSystem = this;
        int i4 = i3;
        ArrayRow createRow = createRow();
        createRow.createRowCentering(solverVariable, solverVariable2, i, f, solverVariable3, solverVariable4, i2);
        if (i4 != 6) {
            createRow.addError(this, i4);
        }
        addConstraint(createRow);
    }

    public void addRatio(SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, SolverVariable solverVariable4, float f, int i) {
        ArrayRow createRow = createRow();
        createRow.createRowDimensionRatio(solverVariable, solverVariable2, solverVariable3, solverVariable4, f);
        if (i != 6) {
            createRow.addError(this, i);
        }
        addConstraint(createRow);
    }

    public ArrayRow addEquality(SolverVariable solverVariable, SolverVariable solverVariable2, int i, int i2) {
        ArrayRow createRow = createRow();
        createRow.createRowEquals(solverVariable, solverVariable2, i);
        if (i2 != 6) {
            createRow.addError(this, i2);
        }
        addConstraint(createRow);
        return createRow;
    }

    public void addEquality(SolverVariable solverVariable, int i) {
        int i2 = solverVariable.definitionId;
        ArrayRow arrayRow;
        if (solverVariable.definitionId != -1) {
            arrayRow = this.mRows[i2];
            if (arrayRow.isSimpleDefinition) {
                arrayRow.constantValue = (float) i;
                return;
            } else if (arrayRow.variables.currentSize == 0) {
                arrayRow.isSimpleDefinition = true;
                arrayRow.constantValue = (float) i;
                return;
            } else {
                arrayRow = createRow();
                arrayRow.createRowEquals(solverVariable, i);
                addConstraint(arrayRow);
                return;
            }
        }
        arrayRow = createRow();
        arrayRow.createRowDefinition(solverVariable, i);
        addConstraint(arrayRow);
    }

    public void addEquality(SolverVariable solverVariable, int i, int i2) {
        int i3 = solverVariable.definitionId;
        ArrayRow arrayRow;
        if (solverVariable.definitionId != -1) {
            arrayRow = this.mRows[i3];
            if (arrayRow.isSimpleDefinition) {
                arrayRow.constantValue = (float) i;
                return;
            }
            arrayRow = createRow();
            arrayRow.createRowEquals(solverVariable, i);
            arrayRow.addError(this, i2);
            addConstraint(arrayRow);
            return;
        }
        arrayRow = createRow();
        arrayRow.createRowDefinition(solverVariable, i);
        arrayRow.addError(this, i2);
        addConstraint(arrayRow);
    }

    public static ArrayRow createRowEquals(LinearSystem linearSystem, SolverVariable solverVariable, SolverVariable solverVariable2, int i, boolean z) {
        ArrayRow createRow = linearSystem.createRow();
        createRow.createRowEquals(solverVariable, solverVariable2, i);
        if (z) {
            linearSystem.addSingleError(createRow, 1);
        }
        return createRow;
    }

    public static ArrayRow createRowDimensionPercent(LinearSystem linearSystem, SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, float f, boolean z) {
        ArrayRow createRow = linearSystem.createRow();
        if (z) {
            linearSystem.addError(createRow);
        }
        return createRow.createRowDimensionPercent(solverVariable, solverVariable2, solverVariable3, f);
    }

    public static ArrayRow createRowGreaterThan(LinearSystem linearSystem, SolverVariable solverVariable, SolverVariable solverVariable2, int i, boolean z) {
        SolverVariable createSlackVariable = linearSystem.createSlackVariable();
        ArrayRow createRow = linearSystem.createRow();
        createRow.createRowGreaterThan(solverVariable, solverVariable2, createSlackVariable, i);
        if (z) {
            linearSystem.addSingleError(createRow, (int) (createRow.variables.get(createSlackVariable) * -1082130432));
        }
        return createRow;
    }

    public static ArrayRow createRowLowerThan(LinearSystem linearSystem, SolverVariable solverVariable, SolverVariable solverVariable2, int i, boolean z) {
        SolverVariable createSlackVariable = linearSystem.createSlackVariable();
        ArrayRow createRow = linearSystem.createRow();
        createRow.createRowLowerThan(solverVariable, solverVariable2, createSlackVariable, i);
        if (z) {
            linearSystem.addSingleError(createRow, (int) (createRow.variables.get(createSlackVariable) * -1082130432));
        }
        return createRow;
    }

    public static ArrayRow createRowCentering(LinearSystem linearSystem, SolverVariable solverVariable, SolverVariable solverVariable2, int i, float f, SolverVariable solverVariable3, SolverVariable solverVariable4, int i2, boolean z) {
        ArrayRow createRow = linearSystem.createRow();
        createRow.createRowCentering(solverVariable, solverVariable2, i, f, solverVariable3, solverVariable4, i2);
        if (z) {
            LinearSystem linearSystem2 = linearSystem;
            createRow.addError(linearSystem, 4);
        }
        return createRow;
    }

    public void addCenterPoint(ConstraintWidget constraintWidget, ConstraintWidget constraintWidget2, float f, int i) {
        ConstraintWidget constraintWidget3 = constraintWidget;
        ConstraintWidget constraintWidget4 = constraintWidget2;
        SolverVariable createObjectVariable = createObjectVariable(constraintWidget3.getAnchor(ConstraintAnchor.Type.LEFT));
        SolverVariable createObjectVariable2 = createObjectVariable(constraintWidget3.getAnchor(ConstraintAnchor.Type.TOP));
        SolverVariable createObjectVariable3 = createObjectVariable(constraintWidget3.getAnchor(ConstraintAnchor.Type.RIGHT));
        SolverVariable createObjectVariable4 = createObjectVariable(constraintWidget3.getAnchor(ConstraintAnchor.Type.BOTTOM));
        SolverVariable createObjectVariable5 = createObjectVariable(constraintWidget4.getAnchor(ConstraintAnchor.Type.LEFT));
        SolverVariable createObjectVariable6 = createObjectVariable(constraintWidget4.getAnchor(ConstraintAnchor.Type.TOP));
        SolverVariable createObjectVariable7 = createObjectVariable(constraintWidget4.getAnchor(ConstraintAnchor.Type.RIGHT));
        SolverVariable createObjectVariable8 = createObjectVariable(constraintWidget4.getAnchor(ConstraintAnchor.Type.BOTTOM));
        ArrayRow createRow = createRow();
        double d = (double) f;
        SolverVariable solverVariable = createObjectVariable3;
        double d2 = (double) i;
        SolverVariable solverVariable2 = createObjectVariable7;
        createRow.createRowWithAngle(createObjectVariable2, createObjectVariable4, createObjectVariable6, createObjectVariable8, (float) (Math.sin(d) * d2));
        addConstraint(createRow);
        createRow = createRow();
        createRow.createRowWithAngle(createObjectVariable, solverVariable, createObjectVariable5, solverVariable2, (float) (Math.cos(d) * d2));
        addConstraint(createRow);
    }
}
