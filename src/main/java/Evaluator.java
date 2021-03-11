import instruction.*;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.HashMap;
import java.util.Map;

public class Evaluator implements InstructionVisitor<BigDecimal> {
    private final Map<String, BigDecimal> context;

    public Evaluator(Map<String, BigDecimal> context) {
        this.context = context;
    }

    public Evaluator() {
        this(new HashMap<>());
    }


    @Override
    public BigDecimal visitVariableSet(InstructionVariableSet instructionVariableSet) {
        BigDecimal value = instructionVariableSet.getValue().acceptVisitor(this);
        context.put(instructionVariableSet.getName(), value);
        return value;
    }

    @Override
    public BigDecimal visitBinaryOperation(InstructionNumberOperation instructionNumberOperation) {
        BigDecimal left = instructionNumberOperation.getLeftOperand().acceptVisitor(this);
        BigDecimal right = instructionNumberOperation.getRightOperand().acceptVisitor(this);
        switch (instructionNumberOperation.getOperator()) {
            case PLUS:
                return left.add(right);
            case MINUS:
                return left.subtract(right);
            case MULTIPLY:
                return left.multiply(right);
            case DIVIDE:
                return left.divide(right, MathContext.DECIMAL128);
            default:
                assert false;
                return null;
        }
    }

    @Override
    public BigDecimal visitVariableGet(InstructionVariableGet instructionVariableGet) {
        context.computeIfAbsent(instructionVariableGet.getName(), s -> {
            throw new RuntimeException();
        });
        return context.get(instructionVariableGet.getName());
    }

    @Override
    public BigDecimal visitProgram(InstructionProgram instructionProgram) {
        instructionProgram.getAssignments().forEach(instruction -> instruction.acceptVisitor(this));
        final int lastInstruction = instructionProgram.getAssignments().size() - 1;
        return instructionProgram.getAssignments().get(lastInstruction).acceptVisitor(this);
    }

    @Override
    public BigDecimal visitNumber(InstructionNumber instructionValue) {
        return instructionValue.getValue();
    }
}
