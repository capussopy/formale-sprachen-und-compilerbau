package instruction;

public class InstructionConsoleOutput  extends  Instruction{

    private final String output;

    public InstructionConsoleOutput(String output){
        this.output = output;
    }

    @Override
    public <T> T acceptVisitor(InstructionVisitor<T> visitor) {
       return  visitor.visitConsoleOutput(this);
    }

    public String getOutput() {
        return output;
    }
}
