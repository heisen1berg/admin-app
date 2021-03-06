package core.cache;

import core.data.Subscription;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class CacheStorage {
    private HashMap<Key,Cachable> cache=new HashMap();

    public void put(Cachable obj) {
        cache.put(obj.getKey(),obj);
    }
    public Cachable get(Key key) {
        return cache.get(key);
    }
    public void removeSub(Subscription s){
        cache.remove(s.getKey());
    }
}
