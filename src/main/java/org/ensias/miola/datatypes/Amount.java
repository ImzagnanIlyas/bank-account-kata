package org.ensias.miola.datatypes;

import org.ensias.miola.utils.AppUtils;

public class Amount {
    public static final int AMOUNT_MINIMUM_VALUE = 10;

    public double value;

    public Amount(double value) {
        this.value = value;
        isValid();
    }

    public void isValid(){
        checkSign();
        checkMinimum();
    }

    public void checkSign(){
        if(isNegative()) throw new RuntimeException("The amount can't be negative");
    }

    public void checkMinimum(){
        if(value < AMOUNT_MINIMUM_VALUE)
            throw new RuntimeException("The amount is under the minimum " + AMOUNT_MINIMUM_VALUE + AppUtils.CURRENNCY);
    }

    public boolean isNegative(){
        return value < 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;

        if (!(obj instanceof Amount)) return false;

        Amount other = (Amount) obj;
        return value == other.value;
    }
}
