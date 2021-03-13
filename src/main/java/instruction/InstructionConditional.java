package instruction;


import java.util.ArrayList;

public class InstructionConditional extends Instruction {

    private final Instruction condition;
    private final Instruction trueBlock;
    private final Instruction falseBlock;

    public InstructionConditional(Instruction condition, Instruction trueBlock) {
        this.condition = condition;
        this.trueBlock = trueBlock;
        this.falseBlock = new InstructionProgram(new ArrayList<>());
    }

    public InstructionConditional(Instruction condition, Instruction trueBlock, Instruction falseBlock) {
        this.condition = condition;
        this.trueBlock = trueBlock;
        this.falseBlock = falseBlock;
    }

    public Instruction getCondition() {
        return condition;
    }

    public Instruction getTrueBlock() {
        return trueBlock;
    }

    public Instruction getFalseBlock() {
        return falseBlock;
    }

    @Override
    public <T> T acceptVisitor(InstructionVisitor<T> visitor) {
       return  visitor.visitConditional(this);
    }
}
