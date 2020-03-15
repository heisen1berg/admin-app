package Core.CachePackage;

import org.springframework.stereotype.Component;
import java.io.*;
import java.util.HashMap;

@Component
public class CacheStorage {
    //TODO eject inactive
    private HashMap<Key,Serializable> cache=new HashMap();

    public void put(Key key, Serializable ser) {
        cache.put(key,ser);
    }

    public Serializable get(Key key) {
        return cache.get(key);
    }
}
