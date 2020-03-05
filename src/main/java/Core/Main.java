package Core;

import javax.annotation.PostConstruct;

import Core.Services.DBService;
import Core.Services.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class Main extends SpringBootServletInitializer{
    @Autowired
    DBService service;


    @PostConstruct
    public void startUp() {

    }
    public static void main(String[] args)
    {
        SpringApplication.run(Main.class, args);
    }
}