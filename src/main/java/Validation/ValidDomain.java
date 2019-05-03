package Validation;

import Domain.Domain;

public abstract class ValidDomain<E extends Domain> implements Validation<E> {

    static String validateDomain(Domain entity) {
        String s=new String();

        if(entity.getID() ==null){
            s+="id invalid\n";
        }
        if(entity.getName() ==null){
            s+="nume invalid\n";
        }
        return s;
    }
    static boolean validDomain(Domain entity) {

        if(entity.getID() ==null || entity.getName() ==null){
            return false;
        }
        return true;
    }
}
