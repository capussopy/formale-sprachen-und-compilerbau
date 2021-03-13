import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class NumberFunctionTest extends ShellTest {


    @Test
    public void defineFunction() throws Exception {
        assertThat(parseExpression("task calculate takes amount, quantity [ amount multiply quantity ]")).isEqualTo(null);
    }

    @Test(expected = RuntimeException.class)
    public void defineVariableAsFunction() throws Exception {
        parseExpression("set 10 as #function_test");
    }


    @Test
    public void executeFunction() throws Exception {
        String expr = "task calculate takes amount, quantity [ " +
                "amount multiply quantity " +
                "]" +
                "execute calculate with 10,20";
        assertThat(parseExpression(expr)).isEqualTo(new BigDecimal("200"));
    }


    @Test
    public void executeFunctionWithVariables() throws Exception {
        addToContext("value1", new BigDecimal("5"));
        addToContext("value2", new BigDecimal("10"));
        String expr = "task calculate takes amount, quantity [ amount multiply quantity ]" +
                "execute calculate with value1,value2";
        assertThat(parseExpression(expr)).isEqualTo(new BigDecimal("50"));
    }


    @Test(expected = RuntimeException.class)
    public void executeFunctionWithToManyParams() throws Exception {
        String expr = "task calculate takes amount, quantity [ amount multiply quantity ]" +
                "execute calculate with 10,20,30";
       parseExpression(expr);
    }

    @Test(expected = RuntimeException.class)
    public void executeFunctionWithFewParams() throws Exception {
        String expr = "task calculate takes amount, quantity [ amount multiply quantity ]" +
                "execute calculate with 10";
        parseExpression(expr);
    }



}
