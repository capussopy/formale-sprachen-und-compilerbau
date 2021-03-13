import instruction.Instruction;
import java_cup.runtime.DefaultSymbolFactory;
import java_cup.runtime.Symbol;
import org.junit.Before;

import java.io.StringReader;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;


public class ShellTest {

    private Map<String, Object> context;

    @Before
    public void init(){
        context = new HashMap<>();
    }

    protected void addToContext(String key, BigDecimal value){
        context.put(key, value);
    }


    protected Object parseExpression(String expression) throws Exception {
        StringReader reader = new StringReader(expression);
        Scanner scanner = new Scanner(reader);
        Symbol symbol = new parser(scanner, new DefaultSymbolFactory()).parse();
        Instruction instruction = (Instruction) symbol.value;
        return instruction.acceptVisitor(new Evaluator(context));
    }
}
