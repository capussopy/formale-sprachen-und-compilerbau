package instruction;

public class BinaryOperation extends Instruction {

    private final BinaryOperator operator;
    private final Instruction leftOperand;
    private final Instruction rightOperand;

    public BinaryOperation(BinaryOperator operator, Instruction leftOperand, Instruction rightOperand) {
        this.operator = operator;
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;
    }


    @Override
    public <T> T acceptVisitor(InstructionVisitor<T> visitor) {
        return visitor.handleBinaryOperation(this);
    }

    public BinaryOperator getOperator() {
        return operator;
    }

    public Instruction getLeftOperand() {
        return leftOperand;
    }

    public Instruction getRightOperand() {
        return rightOperand;
    }
}
