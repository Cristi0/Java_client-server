package Service;

import Domain.Curse;
import Domain.Echipa;
import Domain.Participant;
import Domain.User;
import Repository.RepoSqlCurse;
import Repository.RepoSqlEchipa;
import Repository.RepoSqlLogin;
import Repository.RepoSqlParticipant;
import Validation.ValidareConcurent;
import Validation.ValidareEchipa;

import java.util.Map;
import java.util.Vector;

public class Service implements IntService{
    private RepoSqlParticipant participanti;
    private RepoSqlEchipa echipe;
    private RepoSqlCurse curse;
    private RepoSqlLogin repoLogin;
    private ValidareConcurent validareConcurent;
    private ValidareEchipa validareEchipa;

    public Service() {
        this.participanti = new RepoSqlParticipant();
        this.echipe = new RepoSqlEchipa();
        this.curse = new RepoSqlCurse();
        this.repoLogin=new RepoSqlLogin();
        this.validareConcurent=new ValidareConcurent();
        this.validareEchipa= new ValidareEchipa();
    }

    public Map<Integer, Curse> viewAllCurse(Integer capacitateMotor){
        return curse.getAll("where CapacitateMotor="+capacitateMotor.toString());
    }
    public Vector<String> viewAllCapacitateMotor(){
        Vector<String> text=new Vector<String>();
        for (Curse c:curse.getAll().values()) {
            if(!text.contains(c.getCapacitateMotor().toString())){
                text.add(c.getCapacitateMotor().toString());
            }
        }
        return text;
    }
    public Vector<Echipa> viewAllEchipa(){
        Vector<Echipa> text=new Vector<>();
        for (Echipa c:echipe.getAll().values()) {
            if(!text.contains(c.toString())){
                text.add(c);
            }
        }
        return text;
    }

    public Map<Integer, Participant> viewAllParticipanti(Integer idEchipa){
        return participanti.getAll("where idEchipa="+idEchipa.toString());
    }
    public Map<Integer, Participant> viewAllParticipanti(){
        return participanti.getAll();
    }



    public String login(User user){

        return repoLogin.getType(user);
        //return repoLogin.getType(nume,parola);
    }

    public String addParticipant(Participant participant){
        String err;
        err=validareConcurent.validate(participant);
       // if(err.isEmpty()){
            participanti.add(participant);
      //  }
        return err;
    }


}
