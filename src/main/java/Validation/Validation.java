package Validation;

import Domain.Domain;

public interface Validation<E extends Domain> {
    String validate(E entity);
    boolean valid(E entity);

}
