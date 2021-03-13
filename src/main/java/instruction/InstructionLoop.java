package instruction;

public class InstructionLoop  extends Instruction{

    private final Instruction condition;
    private final Instruction block;

    public InstructionLoop(Instruction condition, Instruction block) {
        this.condition = condition;
        this.block = block;
    }

    public Instruction getCondition() {
        return condition;
    }

    public Instruction getBlock() {
        return block;
    }

    @Override
    public <T> T acceptVisitor(InstructionVisitor<T> visitor) {
        return visitor.visitLoop(this);
    }
}
