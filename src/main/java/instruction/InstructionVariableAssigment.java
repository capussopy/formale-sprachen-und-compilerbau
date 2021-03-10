package instruction;

public class InstructionVariableAssigment extends Instruction {

    private final String identifier;
    private final Instruction value;

    public InstructionVariableAssigment(String identifier, Instruction value) {
        this.identifier = identifier;
        this.value = value;
    }

    @Override
    public <T> T acceptVisitor(InstructionVisitor<T> visitor) {
        return visitor.handleVariableAssigment(this);
    }


    public String getIdentifier() {
        return identifier;
    }

    public Instruction getValue() {
        return value;
    }
}
