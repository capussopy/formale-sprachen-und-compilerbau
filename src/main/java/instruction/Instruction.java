package instruction;

public abstract class Instruction {
   public abstract  <T> T acceptVisitor(InstructionVisitor<T> visitor);
}
