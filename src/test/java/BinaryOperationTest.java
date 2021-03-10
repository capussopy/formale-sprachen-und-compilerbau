
import org.junit.Test;



import static org.assertj.core.api.Assertions.assertThat;


public class BinaryOperationTest extends ShellTest {


    @Test
    public void addition() throws Exception {
        assertThat(parseExpression("10 add 10")).isEqualTo(20.);
        assertThat(parseExpression("1 add 2")).isEqualTo(3.);
        assertThat(parseExpression("2.3 add 1.2")).isEqualTo(3.5);
    }

    @Test
    public void substract() throws Exception{
        assertThat(parseExpression("10 substract 10")).isEqualTo(0.);
        assertThat(parseExpression("1 substract 2")).isEqualTo(-1.);
        assertThat(parseExpression("2.3 substract 1.2")).isEqualTo(1.0999999999999999);
    }

    @Test
    public void multiply() throws Exception{
        assertThat(parseExpression("10 multiply 10")).isEqualTo(100.);
        assertThat(parseExpression("1 multiply 2")).isEqualTo(2.);
        assertThat(parseExpression("2.3 multiply 1.2")).isEqualTo(2.76);
    }

    @Test
    public void division() throws Exception{
        assertThat(parseExpression("10 divide 10")).isEqualTo(1.);
        assertThat(parseExpression("1 divide 2")).isEqualTo(.5);
        assertThat(parseExpression("2.3 divide 1.2")).isEqualTo(1.9166666666666665);
    }


}
