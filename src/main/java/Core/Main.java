package Core;

import javax.annotation.PostConstruct;

import Core.CachePackage.Key;
import Core.DataStructures.*;
import Core.Services.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
public class Main extends SpringBootServletInitializer{
    @Autowired
    private CacheService cacheService;
    @PostConstruct
    public void startUp(){
        cacheService.init();
    }
    public static void main(String[] args)
    {
        SpringApplication.run(Main.class, args);
    }
}