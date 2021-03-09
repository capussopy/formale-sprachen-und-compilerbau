
import instruction.Instruction;
import java_cup.runtime.DefaultSymbolFactory;
import java_cup.runtime.Symbol;

import java.io.StringReader;


public class ShellTest {

    protected Object parseExpression(String expression) throws Exception {
        StringReader reader = new StringReader(expression);
        Scanner scanner = new Scanner(reader);
        Symbol symbol = new parser(scanner, new DefaultSymbolFactory()).parse();
        Instruction instruction = (Instruction) symbol.value;
        return instruction.acceptVisitor(new Evaluator());
    }


}
