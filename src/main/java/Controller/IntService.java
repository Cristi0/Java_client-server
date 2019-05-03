package Controller;

import Domain.Curse;
import Domain.Echipa;
import Domain.Participant;
import Domain.User;

import java.util.Map;
import java.util.Vector;

public interface IntService {
    public Map<Integer, Curse> viewAllCurse(Integer capacitateMotor);
    public Vector<String> viewAllCapacitateMotor();
    public Vector<Echipa> viewAllEchipa();
    public Map<Integer, Participant> viewAllParticipanti(Integer idEchipa);
    public Map<Integer, Participant> viewAllParticipanti();
    public String login(User user);
    public String addParticipant(Participant participant);
}
