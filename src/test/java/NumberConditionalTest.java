import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class NumberConditionalTest extends ShellTest{

    @Test
    public void conditionalIfWithNumber() throws Exception{
        assertThat(parseExpression("in case that 10 equal 10 [ set 10 as amount ]")).isEqualTo(new BigDecimal("10"));
    }

    @Test
    public void conditionalIfWithVariable() throws Exception{
        addToContext("amount", new BigDecimal("20.5"));
        addToContext("amount2", new BigDecimal("20.0"));
        assertThat(parseExpression("in case that amount not equal amount2 [ set 30 as amount3 ]")).isEqualTo(new BigDecimal("30"));
    }


    @Test
    public void conditionalElseWithNumber() throws Exception{
        String exp = "in case that 5 greater 10 [ set 1 as amount ] fallback [set 0.5 as amount]";
        assertThat(parseExpression(exp)).isEqualTo(new BigDecimal("0.5"));
    }

    @Test
    public void conditionalElseWithVariable() throws Exception{
        addToContext("amount", new BigDecimal("20.5"));
        addToContext("amount2", new BigDecimal("20.0"));
        String exp = "in case that amount lower amount2 [ set 1 as amount2 ] fallback [set 0.5 as amount3]";
        assertThat(parseExpression(exp)).isEqualTo(new BigDecimal("0.5"));
    }



}
