package selab.threetier.storage;
import selab.threetier.logic.Entity;

import java.util.ArrayList;
import java.util.HashMap;

public class EntityStorage<T extends Entity> {

    private HashMap<Integer, T> list = new HashMap<Integer, T>();
    private int counter = 0;

    public int addOrUpdate(T item) {
        int id = item.getId();

        if (id == 0) {
            item.setId(++counter);
            id = counter;
        }

        if (list.containsKey(id))
            list.replace(id, item);
        else
            list.put(id, item);
        return id;
    }



    public int remove(T item){
        list.remove(item.getId());
        return item.getId();
    }

    public ArrayList<T> getAll() {
        return new ArrayList<T>(list.values());
    }
    public int getsize() {
        return list.size();
    }

    public T get(int id) {
        return list.get(id);
    }
}
