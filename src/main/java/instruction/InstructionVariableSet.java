package instruction;

public class InstructionVariableSet extends Instruction {

    private final String name;
    private final Instruction value;

    public InstructionVariableSet(String name, Instruction value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public <T> T acceptVisitor(InstructionVisitor<T> visitor) {
        return visitor.visitVariableSet(this);
    }


    public String getName() {
        return name;
    }

    public Instruction getValue() {
        return value;
    }
}
