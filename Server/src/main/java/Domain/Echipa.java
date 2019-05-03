package Domain;

/**
 * O echipa formata din mai multi participanti
 */
public class Echipa implements Domain {
    private Integer id;
    private String nume;
    private int idCursa;

    /**
     * Constructor unei echipe cu mai multi participanti
     * @param id : int, identificatorul unic al echipei
     * @param nume : string, numele echipei
     * @param idCursa : int, id-ul cursei
     */
    public Echipa(Integer id, String nume, int idCursa) {
        this.id = id;
        this.nume = nume;
        this.idCursa = idCursa;
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
     * Cere numele echipei
     * @return string
     */
    @Override
    public String getName() {
        return nume;
    }
    /**
     * Cere vectorul de participanti
     * @return vector<Participant>
     */
    public int getIdCursa() {
        return idCursa;
    }

    /**********************************************************************/
    /**
     * Seteaza numele echipei
     * @param nume string
     */
    public void setNume(String nume) {
        this.nume = nume;
    }

    /**
     * Seteaza toti participantii echipei
     * @param idCursa vector<Participant>
     */
    public void setIdCursa(int idCursa) {
        this.idCursa = idCursa;
    }


    @Override
    public String toString() {
        return  "id=" + id +
                " nume=" + nume +
                " idCursa=" + idCursa;

    }
}
