package instruction;

import java.util.List;

public class InstructionFunctionDefinition extends Instruction {

    private final String name;
    private final List<String> values;
    private final Instruction block;


    public InstructionFunctionDefinition(String name, List<String> values, Instruction block) {
        this.name = name;
        this.values = values;
        this.block = block;
    }

    public String getName() {
        return name;
    }

    public List<String> getValues() {
        return values;
    }

    public Instruction getBlock() {
        return block;
    }

    @Override
    public <T> T acceptVisitor(InstructionVisitor<T> visitor) {
        return visitor.visitFunctionDefinition(this);
    }
}
