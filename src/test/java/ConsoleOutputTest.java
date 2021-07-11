import core.exception.ContextException;
import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class ConsoleOutputTest extends ShellTest{

    @Test
    public void consoleTest() throws Exception{
        assertThat(parseExpression("print('Hello World')")).isEqualTo("Hello World");
    }

    @Test
    public void consoleTestWithOneVariable() throws Exception {
        addToContext("foo", new BigDecimal("2"));
        assertThat(parseExpression("print('Foo: $foo')")).isEqualTo("Foo: 2");
    }

    @Test
    public void consoleTestWithTwoVariables() throws Exception {
        addToContext("foo", new BigDecimal("2"));
        addToContext("bar", new BigDecimal("3"));
        assertThat(parseExpression("print('Foo: $foo, Bar: $bar')")).isEqualTo("Foo: 2, Bar: 3");
    }

    @Test
    public void consoleTestWithTwoOutputs() throws Exception {
        addToContext("foo", new BigDecimal("2"));
        assertThat(parseExpression("print('Foo: $foo, Foo: $foo')")).isEqualTo("Foo: 2, Foo: 2");
    }


    @Test
    public void consoleTestWithTwoOutputsTogether() throws Exception {
        addToContext("foo", new BigDecimal("2"));
        addToContext("bar", new BigDecimal("3"));
        assertThat(parseExpression("print('Result: $foo$bar')")).isEqualTo("Result: 23");
    }

    @Test(expected = ContextException.class)
    public void consoleTestWithUndefinedVariable() throws Exception {
       parseExpression("print('Foo: $foo')");
    }


}
