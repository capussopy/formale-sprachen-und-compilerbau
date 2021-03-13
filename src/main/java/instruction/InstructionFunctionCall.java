package instruction;

import java.util.List;

public class InstructionFunctionCall extends Instruction {

    private final String name;
    private final List<Instruction> values;

    public InstructionFunctionCall(String name, List<Instruction> values) {
        this.name = name;
        this.values = values;
    }

    public String getName() {
        return name;
    }

    public List<Instruction> getValues() {
        return values;
    }

    @Override
    public <T> T acceptVisitor(InstructionVisitor<T> visitor) {
        return visitor.visitFunctionCall(this);
    }
}
