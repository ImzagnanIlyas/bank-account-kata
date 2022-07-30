package org.ensias.miola.datatypes;

public class AccountNumber {
    public static final int NUMBER_LENGTH = 8;

    public String value;

    public AccountNumber(String value) {
        this.value = value;
        isValid();
    }

    public void isValid(){
        checkFormat();
        checkLength();
    }

    public void checkFormat(){
        try {
            Integer.parseInt(value);
        }catch (NumberFormatException e){
            throw new RuntimeException("Account Number invalid format");
        }
    }

    public void checkLength(){
        if(value.length() != NUMBER_LENGTH) throw new RuntimeException("Account Number invalid length");
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;

        if (!(obj instanceof AccountNumber)) return false;

        AccountNumber other = (AccountNumber) obj;
        return value.equals(other.value);
    }
}
