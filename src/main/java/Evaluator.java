import instruction.*;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.HashMap;
import java.util.Map;

public class Evaluator implements InstructionVisitor<Object> {
    private final Map<String, Object> context;

    public Evaluator(Map<String, Object> context) {
        this.context = context;
    }

    public Evaluator() {
        this(new HashMap<>());
    }


    @Override
    public BigDecimal visitVariableSet(InstructionVariableSet instructionVariableSet) {
        BigDecimal value = (BigDecimal) instructionVariableSet.getValue().acceptVisitor(this);
        context.put(instructionVariableSet.getName(), value);
        return value;
    }

    @Override
    public BigDecimal visitBinaryOperation(InstructionNumberOperation instructionNumberOperation) {
        BigDecimal left =  (BigDecimal) instructionNumberOperation.getLeftOperand().acceptVisitor(this);
        BigDecimal right = (BigDecimal) instructionNumberOperation.getRightOperand().acceptVisitor(this);
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
        return (BigDecimal) context.get(instructionVariableGet.getName());
    }

    @Override
    public Boolean visitBooleanCondition(InstructionBooleanCondition instructionBooleanCondition) {
        BigDecimal left =  (BigDecimal) instructionBooleanCondition.getLeft().acceptVisitor(this);
        BigDecimal right = (BigDecimal) instructionBooleanCondition.getRight().acceptVisitor(this);
        switch (instructionBooleanCondition.getCondition()){
            case EQUALS:
                return left.compareTo(right) == 0;
            case LOWER:
                return left.compareTo(right) < 0;
            case GREATER:
                return left.compareTo(right) > 0;
            case NOT_EQUALS:
                return left.compareTo(right) != 0;
            case GREATER_OR_EQUALS:
                return left.compareTo(right) >= 0;
            case LOWER_OR_EQUALS:
                return left.compareTo(right) <= 0;
            default:
                assert false;
                return null;
        }
    }

    @Override
    public Object visitConditional(InstructionConditional instructionConditional) {
       Boolean condition = (Boolean) instructionConditional.getCondition().acceptVisitor(this);
       if(condition){
           return instructionConditional.getTrueBlock().acceptVisitor(this);
       }
       return instructionConditional.getFalseBlock().acceptVisitor(this);
    }

    @Override
    public Object visitProgram(InstructionProgram instructionProgram) {
        instructionProgram.getAssignments().forEach(instruction -> instruction.acceptVisitor(this));
        final int lastInstruction = instructionProgram.getAssignments().size() - 1;
        return instructionProgram.getAssignments().get(lastInstruction).acceptVisitor(this);
    }

    @Override
    public BigDecimal visitNumber(InstructionNumber instructionValue) {
        return instructionValue.getValue();
    }
}
