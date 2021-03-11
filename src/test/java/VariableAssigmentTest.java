import org.junit.Test;

import java.math.BigDecimal;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class VariableAssigmentTest extends ShellTest {

    @Test
    public void setVariable() throws Exception {
        assertThat(parseExpression("set 20.5 as amount")).isEqualTo(new BigDecimal("20.5"));
        assertThat(parseExpression("set 10 as amount")).isEqualTo(new BigDecimal("10."));
        assertThat(parseExpression("set -3.2 as amount")).isEqualTo(new BigDecimal("-3.2"));
    }


    @Test(expected = RuntimeException.class)
    public void getInvalidVariable() throws Exception {
        parseExpression("amount");
    }

    @Test
    public void getVariable() throws Exception {
        Map<String, BigDecimal> context = Map.of("amount", new BigDecimal("11.1"));
        assertThat(parseExpression("amount", context)).isEqualTo(new BigDecimal("11.1"));
    }

    @Test
    public void getVariableWithMultipleAssignments() throws Exception {
        Map<String, BigDecimal> context = Map.of("amount", new BigDecimal("11.1"), "amount2", new BigDecimal("-10"));
        assertThat(parseExpression("amount", context)).isEqualTo(new BigDecimal("11.1"));
    }


}
