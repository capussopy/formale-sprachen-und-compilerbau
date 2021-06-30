import core.VoidObject;
import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class NumberConditionTest extends ShellTest {



    @Test
    public void equalsTrue() throws Exception{
        assertThat(parseExpression("in case that 10 equal 10 [set 1 as var]")).isEqualTo(new BigDecimal("1"));
    }

    @Test
    public void equalsFalse() throws Exception {
        assertThat(parseExpression("in case that 10 equal 3 [set 1 as var]")).isInstanceOf(VoidObject.class);
    }

    @Test
    public void lowerTrue() throws Exception {
        assertThat(parseExpression("in case that 2 lower 5 [set 1 as var]")).isEqualTo(new BigDecimal("1"));
    }

    @Test
    public void lowerFalse() throws Exception {
        assertThat(parseExpression("in case that 5 lower 2 [set 1 as var]")).isInstanceOf(VoidObject.class);
    }

    @Test
    public void greaterTrue() throws Exception {
        assertThat(parseExpression("in case that 5 greater 2 [set 1 as var]")).isEqualTo(new BigDecimal("1"));
    }

    @Test
    public void greaterFalse() throws Exception {
        assertThat(parseExpression("in case that 2 greater 5 [set 1 as var]")).isInstanceOf(VoidObject.class);
    }

    @Test
    public void lowerOrEqualsTrue() throws Exception {
        assertThat(parseExpression("in case that 2 lower or equal 5 [set 1 as var]")).isEqualTo(new BigDecimal("1"));
        assertThat(parseExpression("in case that 5 lower or equal 5 [set 1 as var]")).isEqualTo(new BigDecimal("1"));
    }

    @Test
    public void lowerOrEqualsFalse() throws Exception {
        assertThat(parseExpression("in case that 5 lower or equal 2 [set 1 as var]")).isInstanceOf(VoidObject.class);
    }


    @Test
    public void greaterOrEqualsTrue() throws Exception {
        assertThat(parseExpression("in case that 5 greater or equal 2 [set 1 as var]")).isEqualTo(new BigDecimal("1"));
        assertThat(parseExpression("in case that 5 greater or equal 5 [set 1 as var]")).isEqualTo(new BigDecimal("1"));
    }

    @Test
    public void greaterOrEqualsFalse() throws Exception {
        assertThat(parseExpression("in case that 2 greater or equal 5 [set 1 as var]")).isInstanceOf(VoidObject.class);
    }

    @Test
    public void notEqualsTrue() throws Exception {
        assertThat(parseExpression("in case that 5 not equal 10 [set 1 as var]")).isEqualTo(new BigDecimal("1"));
    }

    @Test
    public void notEqualsFalse() throws Exception {
        assertThat(parseExpression("in case that 5 not equal 5 [set 1 as var]")).isInstanceOf(VoidObject.class);
    }
}
