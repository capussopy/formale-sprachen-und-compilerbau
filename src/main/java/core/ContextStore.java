package core;

import core.exception.ContextException;
import instruction.InstructionFunctionDefinition;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public abstract class ContextStore {
    private static final String FUNCTION = "#function_";

    private final Map<String, Object> globalContext;
    private Map<String, Object> context;


    public ContextStore(Map<String, Object> context) {
        this.globalContext = context;
        this.context = context;
    }

    public ContextStore() {
        this(new HashMap<>());
    }

    protected void addVariable(String name, BigDecimal value) {
        context.put(name, value);
    }

    protected void addFunction(String name, InstructionFunctionDefinition function){
        this.context.put(FUNCTION+name, function);
    }


    protected BigDecimal getVariable(String name) {
        context.computeIfAbsent(name, s -> { throw new ContextException("Variable "+ name +" not defined"); });
        return (BigDecimal) context.get(name);
    }

    protected InstructionFunctionDefinition getFunction(String name){
        context.computeIfAbsent(FUNCTION+name, s -> { throw new ContextException("Function "+ name + "not defined"); });
        return (InstructionFunctionDefinition) context.get(FUNCTION+name);
    }



    protected void createContext(Map<String, Object> context) {
            this.context = context;
    }

    protected void destroyContext() {
        if (!this.globalContext.equals(context)) {
            this.context = globalContext;
        }
    }


}
