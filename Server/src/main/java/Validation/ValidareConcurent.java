package Validation;

import Domain.Participant;

public class ValidareConcurent extends ValidDomain<Participant> {
    /**
     * Valideaza un participant punand erorile intr-un string
     * @param entity : Participant
     * @return null daca e valid, un string cu erorile altfel
     */
    @Override
    public String validate(Participant entity) {

        String s= validateDomain(entity);
        if(entity.getCapacitateMotor()<=0 || entity.getCapacitateMotor()%100!=0){
            s+="capacitate motor incorecta\n";
        }

        if(s.isEmpty()){
           return null;
        }
        return s;
    }

    @Override
    public boolean valid(Participant entity) {
        if (validDomain(entity) || entity.getCapacitateMotor()<=0 || entity.getCapacitateMotor()%100!=0){
            return false;
        }
        return true;
    }
}


