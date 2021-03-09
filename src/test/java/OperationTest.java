
import org.junit.Test;



import static org.assertj.core.api.Assertions.assertThat;


public class OperationTest extends ShellTest {


    @Test
    public void addition() throws Exception {
        assertThat(parseExpression("5 add to 5")).isEqualTo(10);
    }

    @Test
    public void substract() throws Exception{
        assertThat(parseExpression("10 substract from 9")).isEqualTo(1);
    }

}
