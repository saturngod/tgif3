package android.support.constraint.solver.widgets;

import android.support.constraint.solver.LinearSystem;
import android.support.constraint.solver.SolverVariable;
import android.support.constraint.solver.widgets.ConstraintAnchor.Type;

public class ResolutionAnchor extends ResolutionNode {
    public static final int BARRIER_CONNECTION = 5;
    public static final int CENTER_CONNECTION = 2;
    public static final int CHAIN_CONNECTION = 4;
    public static final int DIRECT_CONNECTION = 1;
    public static final int MATCH_CONNECTION = 3;
    public static final int UNCONNECTED = 0;
    float computedValue;
    private ResolutionDimension dimension = null;
    private int dimensionMultiplier = 1;
    ConstraintAnchor myAnchor;
    float offset;
    private ResolutionAnchor opposite;
    private ResolutionDimension oppositeDimension = null;
    private int oppositeDimensionMultiplier = 1;
    private float oppositeOffset;
    float resolvedOffset;
    ResolutionAnchor resolvedTarget;
    ResolutionAnchor target;
    int type = 0;

    String sType(int i) {
        return i == 1 ? "DIRECT" : i == 2 ? "CENTER" : i == 3 ? "MATCH" : i == 4 ? "CHAIN" : i == 5 ? "BARRIER" : "UNCONNECTED";
    }

    public ResolutionAnchor(ConstraintAnchor constraintAnchor) {
        this.myAnchor = constraintAnchor;
    }

    public void remove(ResolutionDimension resolutionDimension) {
        if (this.dimension == resolutionDimension) {
            this.dimension = null;
            this.offset = (float) this.dimensionMultiplier;
        } else if (this.dimension == this.oppositeDimension) {
            this.oppositeDimension = null;
            this.oppositeOffset = (float) this.oppositeDimensionMultiplier;
        }
        resolve();
    }

    public String toString() {
        StringBuilder stringBuilder;
        if (this.state != 1) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("{ ");
            stringBuilder.append(this.myAnchor);
            stringBuilder.append(" UNRESOLVED} type: ");
            stringBuilder.append(sType(this.type));
            return stringBuilder.toString();
        } else if (this.resolvedTarget == this) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("[");
            stringBuilder.append(this.myAnchor);
            stringBuilder.append(", RESOLVED: ");
            stringBuilder.append(this.resolvedOffset);
            stringBuilder.append("]  type: ");
            stringBuilder.append(sType(this.type));
            return stringBuilder.toString();
        } else {
            stringBuilder = new StringBuilder();
            stringBuilder.append("[");
            stringBuilder.append(this.myAnchor);
            stringBuilder.append(", RESOLVED: ");
            stringBuilder.append(this.resolvedTarget);
            stringBuilder.append(":");
            stringBuilder.append(this.resolvedOffset);
            stringBuilder.append("] type: ");
            stringBuilder.append(sType(this.type));
            return stringBuilder.toString();
        }
    }

    public void resolve(ResolutionAnchor resolutionAnchor, float f) {
        if (this.state == 0 || !(this.resolvedTarget == resolutionAnchor || this.resolvedOffset == f)) {
            this.resolvedTarget = resolutionAnchor;
            this.resolvedOffset = f;
            if (this.state == Float.MIN_VALUE) {
                invalidate();
            }
            didResolve();
        }
    }

    public void resolve() {
        Object obj = 1;
        if (this.state != 1 && this.type != 4) {
            if (this.dimension != null) {
                if (this.dimension.state == 1) {
                    this.offset = ((float) this.dimensionMultiplier) * this.dimension.value;
                } else {
                    return;
                }
            }
            if (this.oppositeDimension != null) {
                if (this.oppositeDimension.state == 1) {
                    this.oppositeOffset = ((float) this.oppositeDimensionMultiplier) * this.oppositeDimension.value;
                } else {
                    return;
                }
            }
            if (this.type == 1 && (this.target == null || this.target.state == 1)) {
                if (this.target == null) {
                    this.resolvedTarget = this;
                    this.resolvedOffset = this.offset;
                } else {
                    this.resolvedTarget = this.target.resolvedTarget;
                    this.resolvedOffset = this.target.resolvedOffset + this.offset;
                }
                didResolve();
            } else if (this.type == 2 && this.target != null && this.target.state == 1 && this.opposite != null && this.opposite.target != null && this.opposite.target.state == 1) {
                float f;
                float f2;
                int margin;
                int margin2;
                float f3;
                float f4;
                if (LinearSystem.getMetrics() != null) {
                    r0 = LinearSystem.getMetrics();
                    r0.centerConnectionResolved++;
                }
                this.resolvedTarget = this.target.resolvedTarget;
                this.opposite.resolvedTarget = this.opposite.target.resolvedTarget;
                int i = 0;
                if (this.myAnchor.mType != Type.RIGHT) {
                    if (this.myAnchor.mType != Type.BOTTOM) {
                        obj = null;
                    }
                }
                if (obj != null) {
                    f = this.target.resolvedOffset - this.opposite.target.resolvedOffset;
                } else {
                    f = this.opposite.target.resolvedOffset - this.target.resolvedOffset;
                }
                if (this.myAnchor.mType != Type.LEFT) {
                    if (this.myAnchor.mType != Type.RIGHT) {
                        f -= (float) this.myAnchor.mOwner.getHeight();
                        f2 = this.myAnchor.mOwner.mVerticalBiasPercent;
                        margin = this.myAnchor.getMargin();
                        margin2 = this.opposite.myAnchor.getMargin();
                        if (this.myAnchor.getTarget() != this.opposite.myAnchor.getTarget()) {
                            f2 = 0.5f;
                            margin2 = 0;
                        } else {
                            i = margin;
                        }
                        f3 = (float) i;
                        f4 = (float) margin2;
                        f = (f - f3) - f4;
                        if (obj == null) {
                            this.opposite.resolvedOffset = (this.opposite.target.resolvedOffset + f4) + (f * f2);
                            this.resolvedOffset = (this.target.resolvedOffset - f3) - (f * (1.0f - f2));
                        } else {
                            this.resolvedOffset = (this.target.resolvedOffset + f3) + (f * f2);
                            this.opposite.resolvedOffset = (this.opposite.target.resolvedOffset - f4) - (f * (1.0f - f2));
                        }
                        didResolve();
                        this.opposite.didResolve();
                    }
                }
                f -= (float) this.myAnchor.mOwner.getWidth();
                f2 = this.myAnchor.mOwner.mHorizontalBiasPercent;
                margin = this.myAnchor.getMargin();
                margin2 = this.opposite.myAnchor.getMargin();
                if (this.myAnchor.getTarget() != this.opposite.myAnchor.getTarget()) {
                    i = margin;
                } else {
                    f2 = 0.5f;
                    margin2 = 0;
                }
                f3 = (float) i;
                f4 = (float) margin2;
                f = (f - f3) - f4;
                if (obj == null) {
                    this.resolvedOffset = (this.target.resolvedOffset + f3) + (f * f2);
                    this.opposite.resolvedOffset = (this.opposite.target.resolvedOffset - f4) - (f * (1.0f - f2));
                } else {
                    this.opposite.resolvedOffset = (this.opposite.target.resolvedOffset + f4) + (f * f2);
                    this.resolvedOffset = (this.target.resolvedOffset - f3) - (f * (1.0f - f2));
                }
                didResolve();
                this.opposite.didResolve();
            } else if (this.type == 3 && this.target != null && this.target.state == 1 && this.opposite != null && this.opposite.target != null && this.opposite.target.state == 1) {
                if (LinearSystem.getMetrics() != null) {
                    r0 = LinearSystem.getMetrics();
                    r0.matchConnectionResolved++;
                }
                this.resolvedTarget = this.target.resolvedTarget;
                this.opposite.resolvedTarget = this.opposite.target.resolvedTarget;
                this.resolvedOffset = this.target.resolvedOffset + this.offset;
                this.opposite.resolvedOffset = this.opposite.target.resolvedOffset + this.opposite.offset;
                didResolve();
                this.opposite.didResolve();
            } else if (this.type == 5) {
                this.myAnchor.mOwner.resolve();
            }
        }
    }

    public void setType(int i) {
        this.type = i;
    }

    public void reset() {
        super.reset();
        this.target = null;
        this.offset = 0.0f;
        this.dimension = null;
        this.dimensionMultiplier = 1;
        this.oppositeDimension = null;
        this.oppositeDimensionMultiplier = 1;
        this.resolvedTarget = null;
        this.resolvedOffset = 0.0f;
        this.computedValue = 0.0f;
        this.opposite = null;
        this.oppositeOffset = 0.0f;
        this.type = 0;
    }

    public void update() {
        ConstraintAnchor target = this.myAnchor.getTarget();
        if (target != null) {
            if (target.getTarget() == this.myAnchor) {
                this.type = 4;
                target.getResolutionNode().type = 4;
            }
            int margin = this.myAnchor.getMargin();
            if (this.myAnchor.mType == Type.RIGHT || this.myAnchor.mType == Type.BOTTOM) {
                margin = -margin;
            }
            dependsOn(target.getResolutionNode(), margin);
        }
    }

    public void dependsOn(int i, ResolutionAnchor resolutionAnchor, int i2) {
        this.type = i;
        this.target = resolutionAnchor;
        this.offset = (float) i2;
        this.target.addDependent(this);
    }

    public void dependsOn(ResolutionAnchor resolutionAnchor, int i) {
        this.target = resolutionAnchor;
        this.offset = (float) i;
        this.target.addDependent(this);
    }

    public void dependsOn(ResolutionAnchor resolutionAnchor, int i, ResolutionDimension resolutionDimension) {
        this.target = resolutionAnchor;
        this.target.addDependent(this);
        this.dimension = resolutionDimension;
        this.dimensionMultiplier = i;
        this.dimension.addDependent(this);
    }

    public void setOpposite(ResolutionAnchor resolutionAnchor, float f) {
        this.opposite = resolutionAnchor;
        this.oppositeOffset = f;
    }

    public void setOpposite(ResolutionAnchor resolutionAnchor, int i, ResolutionDimension resolutionDimension) {
        this.opposite = resolutionAnchor;
        this.oppositeDimension = resolutionDimension;
        this.oppositeDimensionMultiplier = i;
    }

    void addResolvedValue(LinearSystem linearSystem) {
        SolverVariable solverVariable = this.myAnchor.getSolverVariable();
        if (this.resolvedTarget == null) {
            linearSystem.addEquality(solverVariable, (int) (this.resolvedOffset + 0.5f));
        } else {
            linearSystem.addEquality(solverVariable, linearSystem.createObjectVariable(this.resolvedTarget.myAnchor), (int) (this.resolvedOffset + 0.5f), 6);
        }
    }

    public float getResolvedValue() {
        return this.resolvedOffset;
    }
}
