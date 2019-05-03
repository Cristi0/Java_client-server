package Domain;

import java.io.Serializable;

public class User implements Domain {

    private Integer id;
    private String nume;
    private String parola;
    private String tip;

    @Override
    public String toString() {
        return
                "id=" + id +
                " nume=" + nume +
                " parola=" + parola +
                " tip=" + tip ;
    }

    public User(Integer id, String nume, String parola, String tip) {
        this.id = id;
        this.nume = nume;
        this.parola = parola;
        this.tip = tip;
    }

    @Override
    public Integer getID() {
        return id;
    }

    @Override
    public String getName() {
        return nume;
    }

    public String getParola() {
        return parola;
    }

    public String getTip() {
        return tip;
    }



    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }
}
