import instruction.Instruction;
import java_cup.runtime.DefaultSymbolFactory;
import java_cup.runtime.Symbol;

import java.io.*;
import java.util.HashMap;

public class Shell {

    private static final String HEADER = "\n\n------------------------------------------\nExquisite Shell\n------------------------------------------";
    private static final String PROMPT = "> ";
    private final PrintStream output;
    private final BufferedReader inputReader;
    private final StringBuilder stringBuilder;

    public Shell(InputStream input, PrintStream output) {
        this.output = output;
        this.inputReader = new BufferedReader(new InputStreamReader(input));
        this.stringBuilder = new StringBuilder();
        this.output.println(HEADER);
    }


    public void execute() throws Exception {
        this.readInput();
    }

    private boolean inputComplete(String receivedInput) {
        return receivedInput == null || receivedInput.trim().length() == 0;
    }

    private void parseInput(String input) throws Exception {
        StringReader reader = new StringReader(input.trim());
        Scanner scanner = new Scanner(reader);
        Symbol symbol = new parser(scanner, new DefaultSymbolFactory()).parse();
        Instruction instruction = (Instruction) symbol.value;
        Evaluator evaluator = new Evaluator(new HashMap<>());
        instruction.acceptVisitor(evaluator);
    }


    private void readInput() throws Exception {
        String line;

        while (true) {
            this.output.print(PROMPT);

            try {
                line = inputReader.readLine();
                if (inputComplete(line)) {
                    break;
                }
                stringBuilder.append(String.format("%s\n", line));

            } catch (IOException ioe) {
                output.format("ERROR: %s", ioe);
                return;
            }
        }

        parseInput(stringBuilder.toString());
    }

    public static void main(String[] args) {

        try {
            Shell shell = new Shell(System.in, System.out);
            shell.execute();
            System.exit(0);

        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }
    }
}
