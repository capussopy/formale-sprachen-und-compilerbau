package instruction;

import java.math.BigDecimal;

public class InstructionNumber extends Instruction {

    private final BigDecimal value;


    public InstructionNumber(BigDecimal value) {
        this.value = value;
    }


    public BigDecimal getValue() {
        return value;
    }

    @Override
    public <T> T acceptVisitor(InstructionVisitor<T> visitor) {
        return visitor.visitNumber(this);
    }

}
