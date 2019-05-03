package Service;

import java.net.Socket;
import java.util.*;
import java.util.function.Consumer;

public class Observer<T> {

    List<T> subiecti;

    public Observer() {
        subiecti= Collections.synchronizedList(new ArrayList<T>());
    }
    public void add(T subiect){
        subiecti.add(subiect);
    }
    public void notficaAll(Consumer<T> function){
        for(T sub: subiecti){
            function.accept(sub);
        }
    }
    public void rmv(T subiect){
        subiecti.remove(subiect);
    }
}
