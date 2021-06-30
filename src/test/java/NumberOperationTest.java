import org.junit.Test;

import java.math.BigDecimal;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;


public class NumberOperationTest extends ShellTest {


    @Test
    public void addition() throws Exception {
        assertThat(parseExpression("set 10 add 10 as result")).isEqualTo(new BigDecimal("20"));
        assertThat(parseExpression("set 1 add 2 as result")).isEqualTo(new BigDecimal("3"));
        assertThat(parseExpression("set 2.3 add 1.2 as result")).isEqualTo(new BigDecimal("3.5"));
    }

    @Test
    public void variableAddition() throws Exception{
        addToContext("amount1", new BigDecimal("20.2"));
        addToContext("amount2", new BigDecimal("-2.4"));
        assertThat(parseExpression("set amount1 add amount2 as result")).isEqualTo(new BigDecimal("17.8"));
    }


    @Test
    public void substract() throws Exception{
        assertThat(parseExpression("set 10 substract 10 as result")).isEqualTo(new BigDecimal("0"));
        assertThat(parseExpression("set 1 substract 2 as result")).isEqualTo(new BigDecimal("-1"));
        assertThat(parseExpression("set 2.3 substract 1.2 as result")).isEqualTo(new BigDecimal("1.1"));
    }

    @Test
    public void variableSubstract() throws Exception{
        addToContext("amount1", new BigDecimal("20.2"));
        addToContext("amount2", new BigDecimal("2.4"));
        assertThat(parseExpression( "set amount1 substract amount2 as result")).isEqualTo(new BigDecimal("17.8"));
    }


    @Test
    public void multiply() throws Exception{
        assertThat(parseExpression("set 10 multiply 10 as result")).isEqualTo(new BigDecimal("100."));
        assertThat(parseExpression("set 1 multiply 2 as result")).isEqualTo(new BigDecimal("2."));
        assertThat(parseExpression("set 2.3 multiply 1.2 as result")).isEqualTo(new BigDecimal("2.76"));
    }

    @Test
    public void variableMultiply() throws Exception{
        addToContext("amount1", new BigDecimal("20"));
        addToContext("amount2", new BigDecimal("-3"));
        assertThat(parseExpression( "set amount1 multiply amount2 as result")).isEqualTo(new BigDecimal("-60"));
    }

    @Test
    public void division() throws Exception{
        assertThat(parseExpression("set 10 divide 10 as result")).isEqualTo(new BigDecimal("1"));
        assertThat(parseExpression("set 1 divide 2 as result")).isEqualTo(new BigDecimal("0.5"));
        assertThat(parseExpression("set 2.3 divide 1.2 as result")).isEqualTo(new BigDecimal("1.916666666666666666666666666666667"));
    }

    @Test
    public void variableDivision() throws Exception{
        addToContext("amount1", new BigDecimal("200"));
        addToContext("amount2", new BigDecimal("20"));
        assertThat(parseExpression("set amount1 divide amount2 as result")).isEqualTo(new BigDecimal("10"));
    }


}
