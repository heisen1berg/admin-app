package Core.CachePackage;

import com.google.common.cache.CacheLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class CustomCacheLoader extends CacheLoader<Key, Serializable> {
    @Autowired
    Serializer ser;

    @Override
    public Serializable load(Key key){
        return ser.deserialize(key);
    }
}
