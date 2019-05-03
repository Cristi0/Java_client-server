package Repository;

import Domain.Domain;

import java.util.Map;
import java.util.TreeMap;

public class RepositoryOf<K,V extends Domain> implements Repository<K,V> {

    private Map<K,V> elements;

    public RepositoryOf(Map<K, V> elements) {
        this.elements = elements;
    }
    public RepositoryOf() {
        this.elements = new TreeMap<K,V>();
    }

    @Override
    public void add(V element) {
        elements.put((K) element.getID(),element);
    }

    @Override
    public void update(V element) {
        elements.replace((K) element.getID(),element);
    }

    @Override
    public void remove(K id) {
        elements.remove(id);
    }

    @Override
    public V getFrom(K id) {
        return elements.get(id);
    }

    @Override
    public Map<K, V> getAll() {
        return elements;
    }

    public void addAll( RepositoryOf<K,V> all){
        elements.putAll(all.getAll());
    }
    public int size(){
        return elements.size();
    }

}
