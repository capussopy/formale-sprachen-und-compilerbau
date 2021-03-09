package instruction;

public interface InstructionVisitor<T> {
    T handleNumberInstruction(NumberInstruction numberInstruction);

    T handleBinaryOperation(BinaryOperation operation);

    T handleProgram(InstructionProgram instructionProgram);
}
