package core.cache;

import com.google.common.cache.CacheLoader;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class CustomCacheLoader extends CacheLoader<Key, Cachable> {
    @Resource
    CacheStorage cacheStorage;
    @Override
    public Cachable load(Key key){
        return cacheStorage.get(key);
    }
}
