package instruction;

import java.math.BigDecimal;

public interface InstructionVisitor<T> {
    T visitProgram(InstructionProgram instructionProgram);

    T visitNumber(InstructionNumber instructionValue);

    T visitVariableSet(InstructionVariableSet instructionVariableSet);

    T visitBinaryOperation(InstructionNumberOperation instructionNumberOperation);

    T visitVariableGet(InstructionVariableGet instructionVariableGet);

    T visitBooleanCondition(InstructionBooleanCondition instructionBooleanCondition);

    T visitConditional(InstructionConditional instructionConditional);

    T visitLoop(InstructionLoop instructionLoop);

    T visitFunctionDefinition(InstructionFunctionDefinition instructionFunctionDefinition);

    T visitFunctionCall(InstructionFunctionCall instructionFunctionCall);

    T visitLogicalCondition(InstructionLogicalCondition instructionLogicalCondition);

    T visitConsoleOutput(InstructionConsoleOutput instructionConsoleOutput);
}
