package instruction;

import enumeration.Condition;

public class InstructionBooleanCondition extends Instruction {

    private final Instruction left;
    private final Condition condition;
    private final Instruction right;


    public InstructionBooleanCondition(Instruction left,Condition condition, Instruction right ) {
        this.left = left;
        this.condition = condition;
        this.right = right;
    }

    public Instruction getLeft() {
        return left;
    }

    public Condition getCondition() {
        return condition;
    }

    public Instruction getRight() {
        return right;
    }


    @Override
    public <T> T acceptVisitor(InstructionVisitor<T> visitor) {
        return visitor.visitBooleanCondition(this);
    }
}
