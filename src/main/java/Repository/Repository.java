package Repository;

import Domain.Domain;

import java.util.Collections;
import java.util.Map;

/**
 * Interfata pentru repository
 * @param <K> cheie unica
 * @param <V> elementul
 */
public interface Repository<K,V extends Domain > {
    /**
     * Adauga un element
     * @param element : E
     */
    void add(V element);
    /**
     * Modificare pe baza de id-ul unic
     * @param element : E
     */
    void update(V element);

    /**
     * Elimina un element
     * @param id : K
     */
    void remove(K id);

    /**
     * Returneaza elementul cu Id-ul dat
     * @param id : int
     * @return E elementul cu id-ul id
     */
    V getFrom(K id);

    /**
     * Returneaza toate elementele
     * @return Collections cu elemente de tipul E
     */
    Map<K,V> getAll();
}
