package Repository;

import Domain.Domain;

public interface RepositoryLogin<K,V extends Domain> extends Repository<K,V>{
    String getType(String nume,String parola);
}
