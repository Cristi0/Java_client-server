package pizzax;

public class ValidPizza {
    private Pizza p;
    private int felii;
    public ValidPizza(Pizza a, int nrFelii) {
        p=a;
        felii=nrFelii;
    }

    public void valid(Pizza p){
        System.out.println("Pizza a trecut testul cu "+felii+" felii!");
    }
}
