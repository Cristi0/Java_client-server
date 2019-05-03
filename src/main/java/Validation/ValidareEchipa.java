package Validation;

import Domain.Echipa;

public class ValidareEchipa extends ValidDomain<Echipa> {

    @Override
    public String validate(Echipa entity) {
        String s= validateDomain(entity);
        if(entity.getIdCursa() <0){
            s+="Echipa nu exista\n";
        }
        if(s.isEmpty()){
            return null;
        }
        return s;
    }



    @Override
    public boolean valid(Echipa entity) {
        if(validDomain(entity) || entity.getIdCursa() <0){
            return false;
        }
        return true;
    }
}


