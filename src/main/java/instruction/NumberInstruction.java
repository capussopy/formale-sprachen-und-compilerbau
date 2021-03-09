package instruction;

public class NumberInstruction extends Instruction {
    private final Double value;


    public NumberInstruction(String value) {
        this.value = Double.parseDouble(value);
    }

    @Override
    public <T> T acceptVisitor(InstructionVisitor<T> visitor) {
        return null;
    }

    public Double getValue() {
        return value;
    }
}
