package instruction;

public class InstructionVariableGet extends Instruction {
    private final String name;

    public InstructionVariableGet(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public <T> T acceptVisitor(InstructionVisitor<T> visitor) {
        return visitor.visitVariableGet(this);
    }
}
