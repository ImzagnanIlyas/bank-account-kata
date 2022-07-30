package org.ensias.miola.datatypes;

public class Pin {
    public static final int PIN_LENGTH = 4;

    public String value;

    public Pin(String value) {
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
            throw new RuntimeException("PIN invalid format");
        }
    }

    public void checkLength(){
        if(value.length() != PIN_LENGTH) throw new RuntimeException("PIN invalid length");
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;

        if (!(obj instanceof Pin)) return false;

        Pin other = (Pin) obj;
        return value.equals(other.value);
    }
}
