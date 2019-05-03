package Domain;


/**
 * O persoana care poate participa la o cursa
 */
public class Participant implements Domain {

    private Integer id;
    private String name;
    private Integer capacitateMotor;
    private Integer idEchipa;

    /**
     * Constructorul
     * @param id : int, identificator unic
     * @param name : string, numele, prenume persoana
     * @param capacitateMotor : int, capacitatea motorului motocicletei sale
     * @param idEchipa : int, id-ul echipei din care face parte
     */
    public Participant(Integer id, String name, Integer capacitateMotor, Integer idEchipa) {
        this.id = id;
        this.name = name;
        this.capacitateMotor = capacitateMotor;
        this.idEchipa = idEchipa;
    }

    /**
     * Constructorul fara echipa
     * @param id : int, identificator unic
     * @param name : string, numele, prenume persoana
     * @param capacitateMotor : int, capacitatea motorului motocicletei sale
     */
    public Participant(Integer id, String name, Integer capacitateMotor) {
        this.id = id;
        this.name = name;
        this.capacitateMotor = capacitateMotor;
        idEchipa=-1;
    }

    /**
     * Cere ID-ul
     * @return int
     */
    @Override
    public Integer getID() {
        return id;
    }

    /**
     * Cere numele/prenume
     * @return string
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Cere capacitatea motorului
     * @return int
     */
    public Integer getCapacitateMotor() {
        return capacitateMotor;
    }

    /**
     * Cere id-ul echipei
     * @return int
     */
    public Integer getIdEchipa() {
        return idEchipa;
    }
    /***************************************************************************************/

    /**
     * Modifica numele persoanei
     * @param name : string
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Modifica capacitatea motorului
     * @param capacitateMotor : int
     */
    public void setCapacitateMotor(Integer capacitateMotor) {
        this.capacitateMotor = capacitateMotor;
    }

    /**
     * Modifica id-ul echipei
     * @param idEchipa : int
     */
    public void setIdEchipa(Integer idEchipa) {
        this.idEchipa = idEchipa;
    }

    @Override
    public String toString() {
        return "id= "+id+" nume= "+name+" capacitateMotor= "+capacitateMotor+" idEchipa= "+idEchipa;
    }
}
