import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class VariableAssigmentTest  extends ShellTest{

    @Test
    public void setVariable() throws Exception {
        assertThat(parseExpression("set 20.5 as amount")).isEqualTo(20.5);
        assertThat(parseExpression("set 10 as amount")).isEqualTo(10.);
        assertThat(parseExpression("set -3.2 as amount")).isEqualTo(-3.2);
    }

}
