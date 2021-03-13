
import core.ContextStore;
import instruction.*;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Evaluator extends ContextStore implements InstructionVisitor<Object> {

    public Evaluator(Map<String, Object> context) {
        super(context);
    }

    public Evaluator() {
        this(new HashMap<>());
    }


    @Override
    public BigDecimal visitVariableSet(InstructionVariableSet instructionVariableSet) {
        BigDecimal value = (BigDecimal) instructionVariableSet.getValue().acceptVisitor(this);
        addVariable(instructionVariableSet.getName(), value);
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
        return getVariable(instructionVariableGet.getName()) ;
    }

    @Override
    public Boolean visitBooleanCondition(InstructionBooleanCondition instructionBooleanCondition) {
        BigDecimal left =  (BigDecimal) instructionBooleanCondition.getLeft().acceptVisitor(this);
        BigDecimal right = (BigDecimal) instructionBooleanCondition.getRight().acceptVisitor(this);
        switch (instructionBooleanCondition.getCondition()){
            case EQUAL:
                return left.compareTo(right) == 0;
            case LOWER:
                return left.compareTo(right) < 0;
            case GREATER:
                return left.compareTo(right) > 0;
            case NOT_EQUAL:
                return left.compareTo(right) != 0;
            case GREATER_OR_EQUAL:
                return left.compareTo(right) >= 0;
            case LOWER_OR_EQUAL:
                return left.compareTo(right) <= 0;
            default:
                assert false;
                return null;
        }
    }

    @Override
    public Object visitConditional(InstructionConditional instructionConditional) {
        Boolean condition = (Boolean) instructionConditional.getCondition().acceptVisitor(this);
        if (condition) {
            return instructionConditional.getTrueBlock().acceptVisitor(this);
        }
        return instructionConditional.getFalseBlock().acceptVisitor(this);
    }

    @Override
    public Object visitLoop(InstructionLoop instructionLoop) {
        while ((Boolean) instructionLoop.getCondition().acceptVisitor(this)) {
            instructionLoop.getBlock().acceptVisitor(this);
        }
        return null;
    }

    @Override
    public Object visitFunctionDefinition(InstructionFunctionDefinition instructionFunctionDefinition) {
        addFunction(instructionFunctionDefinition.getName(), instructionFunctionDefinition);
        return null;
    }

    @Override
    public Object visitFunctionCall(InstructionFunctionCall instructionFunctionCall) {
        InstructionFunctionDefinition function = getFunction(instructionFunctionCall.getName());
        List<String> names = function.getValues();
        List<Instruction> values = instructionFunctionCall.getValues();

        if(names.size() != values.size()){
            throw new RuntimeException("The parameter size not matching for this function");
        }

        Map<String, Object> context = new HashMap<>();

        for(int i=0; i< names.size(); i++){
            context.put(names.get(i), values.get(i).acceptVisitor(this));
        }

        createContext(context);

        Object value = function.getBlock().acceptVisitor(this);

        destroyContext();

        return value;
    }

    @Override
    public BigDecimal visitNumber(InstructionNumber instructionValue) {
        return instructionValue.getValue();
    }

    @Override
    public Object visitProgram(InstructionProgram instructionProgram) {
        instructionProgram.getAssignments().forEach(instruction -> instruction.acceptVisitor(this));
        if (!instructionProgram.getAssignments().isEmpty()) {
            final int lastInstruction = instructionProgram.getAssignments().size() - 1;
            return instructionProgram.getAssignments().get(lastInstruction).acceptVisitor(this);
        }
        return null;
    }


}
