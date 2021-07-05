import core.ContextStore;
import core.VoidObject;
import core.exception.BinaryOperationException;
import core.exception.BooleanConditionException;
import core.exception.FunctionException;
import core.exception.LogicalException;
import enumeration.Logical;
import instruction.*;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        BigDecimal left = (BigDecimal) instructionNumberOperation.getLeftOperand().acceptVisitor(this);
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
                throw new BinaryOperationException("Binary Operation not found");
        }
    }

    @Override
    public BigDecimal visitVariableGet(InstructionVariableGet instructionVariableGet) {
        return getVariable(instructionVariableGet.getName());
    }

    @Override
    public Boolean visitBooleanCondition(InstructionBooleanCondition instructionBooleanCondition) {
        BigDecimal left = (BigDecimal) instructionBooleanCondition.getLeft().acceptVisitor(this);
        BigDecimal right = (BigDecimal) instructionBooleanCondition.getRight().acceptVisitor(this);
        switch (instructionBooleanCondition.getCondition()) {
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
                throw new BooleanConditionException("Boolean Condition not found");
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
    public VoidObject visitLoop(InstructionLoop instructionLoop) {
        while ((Boolean) instructionLoop.getCondition().acceptVisitor(this)) {
            instructionLoop.getBlock().acceptVisitor(this);
        }
        return VoidObject.instance();
    }

    @Override
    public VoidObject visitFunctionDefinition(InstructionFunctionDefinition instructionFunctionDefinition) {
        addFunction(instructionFunctionDefinition.getName(), instructionFunctionDefinition);
        return VoidObject.instance();
    }

    @Override
    public Object visitFunctionCall(InstructionFunctionCall instructionFunctionCall) {
        InstructionFunctionDefinition function = getFunction(instructionFunctionCall.getName());
        List<String> names = function.getValues();
        List<Instruction> values = instructionFunctionCall.getValues();

        if (names.size() != values.size()) {
            throw new FunctionException("Number of params not matching");
        }

        Map<String, Object> context = new HashMap<>();

        for (int i = 0; i < names.size(); i++) {
            context.put(names.get(i), values.get(i).acceptVisitor(this));
        }

        createContext(context);

        Object value = function.getBlock().acceptVisitor(this);

        destroyContext();

        return value;
    }

    @Override
    public Boolean visitLogicalCondition(InstructionLogicalCondition instructionLogicalCondition) {
        Boolean firstCondition = (Boolean) instructionLogicalCondition.getCondition().acceptVisitor(this);
        if (instructionLogicalCondition.getLogical().equals(Logical.NOT)) {
            return !firstCondition;
        }
        Boolean otherCondition = (Boolean) instructionLogicalCondition.getOtherCondition().acceptVisitor(this);
        switch (instructionLogicalCondition.getLogical()) {
            case AND:
                return firstCondition && otherCondition;
            case OR:
                return firstCondition || otherCondition;
            case NOT_OR:
                return !(firstCondition || otherCondition);
            case NOT_AND:
                return !(firstCondition && otherCondition);
            default:
                throw new LogicalException("Logical not found");
        }
    }

    @Override
    public String visitConsoleOutput(InstructionConsoleOutput instructionConsoleOutput) {
        String output = instructionConsoleOutput.getOutput().substring(1, instructionConsoleOutput.getOutput().length() - 1);
        final Matcher matcher = Pattern.compile("\\$.[^\\s]+").matcher(output);
        while (matcher.find()) {
            String variable = matcher.group().replace("$", "");
            output = output.replace(matcher.group(), getVariable(variable).toString());
        }
        System.out.println(output);
        return output;

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
        return VoidObject.instance();
    }


}
