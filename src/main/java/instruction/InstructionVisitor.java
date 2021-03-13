package instruction;

public interface InstructionVisitor<T> {
    T visitProgram(InstructionProgram instructionProgram);

    T visitNumber(InstructionNumber instructionValue);

    T visitVariableSet(InstructionVariableSet instructionVariableSet);

    T visitBinaryOperation(InstructionNumberOperation instructionNumberOperation);

    T visitVariableGet(InstructionVariableGet instructionVariableGet);

    T visitBooleanCondition(InstructionBooleanCondition instructionBooleanCondition);

    T visitConditional(InstructionConditional instructionConditional);
}
