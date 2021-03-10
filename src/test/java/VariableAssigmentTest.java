import java.util.List;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class VariableAssigmentTest extends ShellTest {

    @Test
    public void setVariable() throws Exception {
        assertThat(parseExpression("set 20.5 as amount")).isEqualTo(20.5);
        assertThat(parseExpression("set 10 as amount")).isEqualTo(10.);
        assertThat(parseExpression("set -3.2 as amount")).isEqualTo(-3.2);
    }


    @Test(expected = RuntimeException.class)
    public void getInvalidVariable() throws Exception {
        parseExpression("amount");
    }

    @Test
    public void getVariable() throws Exception {
        List<String> expressions = List.of("set 11.1 as amount", "amount");
        assertThat(parseExpression(expressions)).isEqualTo(11.1);
    }

    @Test
    public void getVariableWithMultipleAssignments() throws Exception {
        List<String> expressions = List.of("set 11.1 as amount", "set -10 as amount2", "amount");
        assertThat(parseExpression(expressions)).isEqualTo(11.1);
    }



}
