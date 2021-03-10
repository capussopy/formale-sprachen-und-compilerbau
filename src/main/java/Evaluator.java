import instruction.*;

import java.util.HashMap;
import java.util.Map;

public class Evaluator implements InstructionVisitor<Double> {
    private final Map<String, Double> context;

    public Evaluator(Map<String, Double> context) {
        this.context = context;
    }

    public Evaluator() {
        this(new HashMap<>());
    }



    @Override
    public Double visitVariableSet(InstructionVariableSet instructionVariableSet) {
        Double value = instructionVariableSet.getValue().acceptVisitor(this);
        context.put(instructionVariableSet.getName(), value);
        return value;
    }

    @Override
    public Double visitBinaryOperation(InstructionBinaryOperation instructionBinaryOperation) {
        Double left = instructionBinaryOperation.getLeftOperand().acceptVisitor(this);
        Double right = instructionBinaryOperation.getRightOperand().acceptVisitor(this);
        switch (instructionBinaryOperation.getOperator()) {
            case PLUS:
                return left + right;
            case MINUS:
                return left - right;
            case MULTIPLY:
                return left * right;
            case DIVIDE:
                return left / right;
            default:
                assert false;
                return null;
        }
    }

    @Override
    public Double visitProgram(InstructionProgram instructionProgram) {
        instructionProgram.getAssignments().forEach(instruction -> instruction.acceptVisitor(this));
        final int lastInstruction = instructionProgram.getAssignments().size() -1;
        return instructionProgram.getAssignments().get(lastInstruction).acceptVisitor(this);
    }

    @Override
    public Double visitNumber(InstructionNumber instructionValue) {
        return  instructionValue.getValue();
    }
}
