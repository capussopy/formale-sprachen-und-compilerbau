package instruction;

public interface InstructionVisitor<T> {
    T handleProgram(InstructionProgram instructionProgram);

    T handleNumberInstruction(NumberInstruction numberInstruction);

    T handleVariableAssigment(InstructionVariableAssigment variableAssigment);

    T handleBinaryOperation(BinaryOperation operation);
}
