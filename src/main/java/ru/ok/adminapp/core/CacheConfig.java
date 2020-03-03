package ru.ok.adminapp.core;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutionException;

@Configuration
public class CacheConfig {

    // <id подписки, время последнего действия>
    private LoadingCache<Long, Subscription> loadingCache = CacheBuilder.newBuilder().maximumSize(10000).build(new CacheLoader<Long, Subscription>() {
        @Override
        public Subscription load(Long subID) throws Exception {
            Subscription sub = null;
            // sub = DataBase.getSubByID(subID);
            return sub;
        }
    });

    public Subscription getSubscription(long subID) throws ExecutionException {
        return loadingCache.get(subID);
    }
}
