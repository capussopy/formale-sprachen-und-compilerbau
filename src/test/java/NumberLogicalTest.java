import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class NumberLogicalTest extends ShellTest {


    @Test
    public void logicalTrueAndTrue() throws Exception{
        assertThat(parseExpression("in case that 10 equal 10 and 1 equal 1 [set 1 as result]")).isEqualTo(new BigDecimal("1"));
    }

    @Test
    public void logicalTrueAndFalse() throws Exception{
        assertThat(parseExpression("in case that 10 equal 10 and 1 equal 2 [set 1 as result] fallback [set -1 as result]"))
                .isEqualTo(new BigDecimal("-1"));
    }

    @Test
    public void logicalFalseAndTrue() throws Exception{
        assertThat(parseExpression("in case that 10 equal 9 and 1 equal 1 [set 1 as result] fallback [set -1 as result]"))
                .isEqualTo(new BigDecimal("-1"));
    }

    @Test
    public void logicalFalseAndFalse() throws Exception{
        assertThat(parseExpression("in case that 10 equal 9 and 1 equal 2 [set 1 as result] fallback [set -1 as result]"))
                .isEqualTo(new BigDecimal("-1"));
    }

    @Test
    public void logicalTrueOrTrue() throws Exception{
        assertThat(parseExpression("in case that 10 equal 10 or 1 equal 1 [set 1 as result] fallback [set -1 as result]"))
                .isEqualTo(new BigDecimal("1"));
    }

    @Test
    public void logicalTrueOrFalse() throws Exception{
        assertThat(parseExpression("in case that 10 equal 10 or 1 equal 2 [set 1 as result] fallback [set -1 as result]"))
                .isEqualTo(new BigDecimal("1"));
    }

    @Test
    public void logicalFalseOrTrue() throws Exception{
        assertThat(parseExpression("in case that 10 equal 9 or 1 equal 1 [set 1 as result] fallback [set -1 as result]"))
                .isEqualTo(new BigDecimal("1"));
    }

    @Test
    public void logicalFalseOrFalse() throws Exception{
        assertThat(parseExpression("in case that 10 equal 9 or 1 equal 2 [set 1 as result] fallback [set -1 as result]"))
                .isEqualTo(new BigDecimal("-1"));
    }


    @Test
    public void logicalNotTrue() throws Exception{
        assertThat(parseExpression("in case that not 10 equal 1 [set 1 as result] fallback [set -1 as result]"))
                .isEqualTo(new BigDecimal("1"));
    }

    @Test
    public void logicalNotFalse() throws Exception{
        assertThat(parseExpression("in case that not 10 equal 10 [set 1 as result] fallback [set -1 as result]"))
                .isEqualTo(new BigDecimal("-1"));
    }


    @Test
    public void logicalNotAndTrueAndTrue() throws Exception{
        assertThat(parseExpression("in case that 10 equal 10 not and 1 equal 1  [set 1 as result] fallback [set -1 as result]"))
                .isEqualTo(new BigDecimal("-1"));
    }

    @Test
    public void logicalNotAndTrueAndFalse() throws Exception{
        assertThat(parseExpression("in case that 10 equal 10 not and 1 equal 2  [set 1 as result] fallback [set -1 as result]"))
                .isEqualTo(new BigDecimal("1"));
    }

    @Test
    public void logicalNotAndFalseAndTrue() throws Exception{
        assertThat(parseExpression("in case that 10 equal 9 not and 1 equal 1  [set 1 as result] fallback [set -1 as result]"))
                .isEqualTo(new BigDecimal("1"));
    }

    @Test
    public void logicalNotAndFalseAndFalse() throws Exception{
        assertThat(parseExpression("in case that 10 equal 9 not and 1 equal 2  [set 1 as result] fallback [set -1 as result]"))
                .isEqualTo(new BigDecimal("1"));
    }


    @Test
    public void logicalNotOrTrueAndTrue() throws Exception{
        assertThat(parseExpression("in case that 10 equal 10 not or 1 equal 1  [set 1 as result] fallback [set -1 as result]"))
                .isEqualTo(new BigDecimal("-1"));
    }

    @Test
    public void logicalNotOrTrueAndFalse() throws Exception{
        assertThat(parseExpression("in case that 10 equal 10 not or 1 equal 2  [set 1 as result] fallback [set -1 as result]"))
                .isEqualTo(new BigDecimal("-1"));
    }

    @Test
    public void logicalNotOrFalseAndTrue() throws Exception{
        assertThat(parseExpression("in case that 10 equal 9 not or 1 equal 1  [set 1 as result] fallback [set -1 as result]"))
                .isEqualTo(new BigDecimal("-1"));
    }

    @Test
    public void logicalNotOrFalseAndFalse() throws Exception{
        assertThat(parseExpression("in case that 10 equal 9 not or 1 equal 2  [set 1 as result] fallback [set -1 as result]"))
                .isEqualTo(new BigDecimal("1"));
    }



}
