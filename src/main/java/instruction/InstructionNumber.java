package instruction;

public class InstructionNumber extends Instruction {

    private final Double value;


    public InstructionNumber(Double value) {
        this.value = value;
    }


    public Double getValue() {
        return value;
    }

    @Override
    public <T> T acceptVisitor(InstructionVisitor<T> visitor) {
        return visitor.visitNumber(this);
    }

}
