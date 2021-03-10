import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class VariableAssigmentTest  extends ShellTest{

    @Test
    public void assignDouble() throws Exception {
        assertThat(parseExpression("set 20.5 as amount")).isEqualTo(20.5);
    }

}
