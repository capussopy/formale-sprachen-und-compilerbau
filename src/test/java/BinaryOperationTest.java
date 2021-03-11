import org.junit.Test;

import java.math.BigDecimal;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;


public class BinaryOperationTest extends ShellTest {


    @Test
    public void addition() throws Exception {
        assertThat(parseExpression("10 add 10")).isEqualTo(new BigDecimal("20"));
        assertThat(parseExpression("1 add 2")).isEqualTo(new BigDecimal("3"));
        assertThat(parseExpression("2.3 add 1.2")).isEqualTo(new BigDecimal("3.5"));
    }

    @Test
    public void variableAddition() throws Exception{
        addToContext("amount1", new BigDecimal("20.2"));
        addToContext("amount2", new BigDecimal("-2.4"));
        assertThat(parseExpression("amount1 add amount2")).isEqualTo(new BigDecimal("17.8"));
    }


    @Test
    public void substract() throws Exception{
        assertThat(parseExpression("10 substract 10")).isEqualTo(new BigDecimal("0"));
        assertThat(parseExpression("1 substract 2")).isEqualTo(new BigDecimal("-1"));
        assertThat(parseExpression("2.3 substract 1.2")).isEqualTo(new BigDecimal("1.1"));
    }

    @Test
    public void variableSubstract() throws Exception{
        addToContext("amount1", new BigDecimal("20.2"));
        addToContext("amount2", new BigDecimal("2.4"));
        assertThat(parseExpression( "amount1 substract amount2")).isEqualTo(new BigDecimal("17.8"));
    }


    @Test
    public void multiply() throws Exception{
        assertThat(parseExpression("10 multiply 10")).isEqualTo(new BigDecimal("100."));
        assertThat(parseExpression("1 multiply 2")).isEqualTo(new BigDecimal("2."));
        assertThat(parseExpression("2.3 multiply 1.2")).isEqualTo(new BigDecimal("2.76"));
    }

    @Test
    public void variableMultiply() throws Exception{
        addToContext("amount1", new BigDecimal("20"));
        addToContext("amount2", new BigDecimal("-3"));
        assertThat(parseExpression( "amount1 multiply amount2")).isEqualTo(new BigDecimal("-60"));
    }

    @Test
    public void division() throws Exception{
        assertThat(parseExpression("10 divide 10")).isEqualTo(new BigDecimal("1"));
        assertThat(parseExpression("1 divide 2")).isEqualTo(new BigDecimal("0.5"));
        assertThat(parseExpression("2.3 divide 1.2")).isEqualTo(new BigDecimal("1.916666666666666666666666666666667"));
    }

    @Test
    public void variableDivision() throws Exception{
        addToContext("amount1", new BigDecimal("200"));
        addToContext("amount2", new BigDecimal("20"));
        assertThat(parseExpression("amount1 divide amount2")).isEqualTo(new BigDecimal("10"));
    }


}
