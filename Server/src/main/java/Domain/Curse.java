package Domain;

public class Curse implements Domain {
    private Integer id;
    private String nume;
    private Integer nrParticipanti;

    public Curse(Integer id, String nume, Integer nrParticipanti, int capacitateMotor) {
        this.id = id;
        this.nume = nume;
        this.nrParticipanti = nrParticipanti;
        this.capacitateMotor = capacitateMotor;

    }

    private int capacitateMotor;

    @Override
    public Integer getID() {
        return id;
    }

    @Override
    public String getName() {
        return nume;
    }

    public Integer getCapacitateMotor() {
        return capacitateMotor;
    }


    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setCapacitateMotor(int capacitateMotor) {
        this.capacitateMotor = capacitateMotor;
    }


    @Override
    public String toString() {
        return  "id=" + id +
                " nume=" + nume +
                " capacitateMotor=" + capacitateMotor;
    }

    public Integer getNrParticipanti() {
        return nrParticipanti;
    }

    public void setNrParticipanti(Integer nrParticipanti) {
        this.nrParticipanti = nrParticipanti;
    }
}
