import org.junit.Test;

import java.math.BigDecimal;
import static org.assertj.core.api.Assertions.assertThat;

public class NumberLoopTest extends ShellTest {

    @Test
    public void loopTestWithTenIterations() throws Exception{
        addToContext("amount", new BigDecimal("1"));
        addToContext("result", new BigDecimal("0"));
        String exp = "as long as amount not equal 2 [" +
                "set amount add 0.1 as amount " +
                "set result add 5 as result"+
                "]"+
                "result";

        assertThat(parseExpression(exp)).isEqualTo(new BigDecimal("100"));
    }

    @Test
    public void loopTestWithoutIteration() throws Exception{
        addToContext("amount", new BigDecimal("1"));
        addToContext("result", new BigDecimal("0"));
        String exp = "as long as amount equal 2 [" +
                "set amount add 0.1 as amount " +
                "set result add 5 as result"+
                "]"+
                "result";

        assertThat(parseExpression(exp)).isEqualTo(new BigDecimal("0"));
    }




}
