package instruction;

import java.util.List;

public class InstructionProgram extends Instruction {

    private  final List<Instruction> assignments;

    public InstructionProgram(List<Instruction> assignments) {
        this.assignments = assignments;
    }

    @Override
    public <T> T acceptVisitor(InstructionVisitor<T> visitor) {
        return visitor.handleProgram(this);
    }

    public List<Instruction> getAssignments() {
        return assignments;
    }
}
