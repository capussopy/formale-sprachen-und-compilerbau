package instruction;

public interface InstructionVisitor<T> {
    T visitProgram(InstructionProgram instructionProgram);

    T visitNumber(InstructionNumber instructionValue);

    T visitVariableSet(InstructionVariableSet instructionVariableSet);

    T visitBinaryOperation(InstructionBinaryOperation instructionBinaryOperation);

    T visitVariableGet(InstructionVariableGet instructionVariableGet);
}
