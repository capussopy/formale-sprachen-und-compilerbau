package instruction;

import enumeration.Logical;


public class InstructionLogicalCondition extends Instruction {

    private final Instruction condition;
    private final Logical logical;
    private final Instruction otherCondition;

    public InstructionLogicalCondition(Instruction condition, Logical logical, Instruction otherCondition) {
        this.condition = condition;
        this.logical = logical;
        this.otherCondition = otherCondition;
    }

    public Instruction getCondition() {
        return condition;
    }

    public Logical getLogical() {
        return logical;
    }

    public Instruction getOtherCondition() {
        return otherCondition;
    }

    @Override
    public <T> T acceptVisitor(InstructionVisitor<T> visitor) {
        return visitor.visitLogicalCondition(this);
    }
}
