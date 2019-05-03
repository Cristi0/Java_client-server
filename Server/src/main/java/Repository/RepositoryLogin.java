package Repository;

import Domain.User;
import Domain.Domain;

public interface RepositoryLogin<K,V extends Domain> extends Repository<K,V>{
    String getType(User user);
}
