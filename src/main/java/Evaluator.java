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
    public Double handleNumberInstruction(NumberInstruction numberInstruction) {
        return numberInstruction.getValue();
    }

    @Override
    public Double handleVariableAssigment(InstructionVariableAssigment variableAssigment) {
        Double value = variableAssigment.getValue().acceptVisitor(this);
        context.put(variableAssigment.getIdentifier(), value);
        return value;
    }

    @Override
    public Double handleBinaryOperation(BinaryOperation operation) {
        Double left = operation.getLeftOperand().acceptVisitor(this);
        Double right = operation.getRightOperand().acceptVisitor(this);
        switch (operation.getOperator()) {
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
    public Double handleProgram(InstructionProgram instructionProgram) {
        instructionProgram.getAssignments().forEach(instruction -> instruction.acceptVisitor(this));
        final int lastInstruction = instructionProgram.getAssignments().size() -1;
        return instructionProgram.getAssignments().get(lastInstruction).acceptVisitor(this);
    }
}
