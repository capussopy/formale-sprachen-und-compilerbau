import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NumberConditionTest extends ShellTest {



    @Test
    public void equalsTrue() throws Exception{
        assertThat(parseExpression("10 equal 10")).isEqualTo(true);
    }

    @Test
    public void equalsFalse() throws Exception {
        assertThat(parseExpression("10 equal 3")).isEqualTo(false);
    }

    @Test
    public void lowerTrue() throws Exception {
        assertThat(parseExpression("2 lower 5")).isEqualTo(true);
    }

    @Test
    public void lowerFalse() throws Exception {
        assertThat(parseExpression("5 lower 2")).isEqualTo(false);
    }

    @Test
    public void greaterTrue() throws Exception {
        assertThat(parseExpression("5 greater 2")).isEqualTo(true);
    }

    @Test
    public void greaterFalse() throws Exception {
        assertThat(parseExpression("2 greater 5")).isEqualTo(false);
    }

    @Test
    public void lowerOrEqualsTrue() throws Exception {
        assertThat(parseExpression("2 lower or equal 5")).isEqualTo(true);
        assertThat(parseExpression("5 lower or equal 5")).isEqualTo(true);
    }

    @Test
    public void lowerOrEqualsFalse() throws Exception {
        assertThat(parseExpression("5 lower or equal 2")).isEqualTo(false);
    }


    @Test
    public void greaterOrEqualsTrue() throws Exception {
        assertThat(parseExpression("5 greater or equal 2")).isEqualTo(true);
        assertThat(parseExpression("5 greater or equal 5")).isEqualTo(true);
    }

    @Test
    public void greaterOrEqualsFalse() throws Exception {
        assertThat(parseExpression("2 greater or equal 5")).isEqualTo(false);
    }

    @Test
    public void notEqualsTrue() throws Exception {
        assertThat(parseExpression("5 not equal 10")).isEqualTo(true);
    }

    @Test
    public void notEqualsFalse() throws Exception {
        assertThat(parseExpression("5 not equal 5")).isEqualTo(false);
    }
}
