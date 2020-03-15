package Core.CachePackage;

import com.google.common.cache.CacheLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.Serializable;

@Component
public class CustomCacheLoader extends CacheLoader<Key, Serializable> {
    @Resource
    CacheStorage cacheStorage;
    @Override
    public Serializable load(Key key){
        return cacheStorage.get(key);
    }
}
