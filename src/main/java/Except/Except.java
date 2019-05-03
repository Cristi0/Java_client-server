package Except;

public class Except extends RuntimeException {
    String s;
    public Except(String s) {
        this.s=s;
    }
    public String getMessage(){
        return s;
    }
}
