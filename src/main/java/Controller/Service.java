package Controller;

import Domain.Curse;
import Domain.Echipa;
import Domain.Participant;
import Domain.User;
import Networking.Network;
import Repository.RepoSqlCurse;
import Repository.RepoSqlEchipa;
import Repository.RepoSqlLogin;
import Repository.RepoSqlParticipant;
import Validation.ValidareConcurent;
import Validation.ValidareEchipa;

import java.util.Map;
import java.util.Vector;

public class Service implements IntService {
    private RepoSqlParticipant participanti;
    private RepoSqlEchipa echipe;
    private RepoSqlCurse curse;
    private RepoSqlLogin repoLogin;
    private ValidareConcurent validareConcurent;
    private ValidareEchipa validareEchipa;
    private Network network;

    public Service() {
        this.participanti = new RepoSqlParticipant();
        this.echipe = new RepoSqlEchipa();
        this.curse = new RepoSqlCurse();
        this.repoLogin=new RepoSqlLogin();
        this.validareConcurent=new ValidareConcurent();
        this.validareEchipa= new ValidareEchipa();
        network= new Network();
    }

    public Map<Integer, Curse> viewAllCurse(Integer capacitateMotor){
        return network.sendAllCurseRequest(capacitateMotor);
    }
    public Vector<String> viewAllCapacitateMotor(){
       /* Vector<String> text=new Vector<String>();
        for (Curse c:curse.getAll().values()) {
            if(!text.contains(c.getCapacitateMotor().toString())){
                text.add(c.getCapacitateMotor().toString());
            }
        }*/
        return network.sendAllCapacitateMotor();
    }
    public Vector<Echipa> viewAllEchipa(){
       /* Vector<Echipa> text=new Vector<>();
        for (Echipa c:echipe.getAll().values()) {
            if(!text.contains(c.toString())){
                text.add(c);
            }
        }*/
        return network.sendAllEchipe();
    }

    public Map<Integer, Participant> viewAllParticipanti(Integer idEchipa){
        return network.sendAllParticipantiRequest(idEchipa);
    }
    public Map<Integer, Participant> viewAllParticipanti(){
        return network.sendAllParticipantiRequest();
    }




    public String login(User user){

        //User user =new User(0,nume,parola," ");
        return network.sendLoginRequest(user);
        //return repoLogin.getType(nume,parola);
    }

    public String addParticipant(Participant participant){
        String err;
        err=validareConcurent.validate(participant);
       // if(err.isEmpty()){
        network.sendAddRequest(participant);
        //    participanti.add(participant);
      //  }
        return err;
    }


    public int getNotify() {
        return network.getNotify();
    }
    public void addToNotify(){
        network.addToNotify();
    }

    public void logout() {
        network.sendLogoutRequest();
    }
}
