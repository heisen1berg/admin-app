package Core;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import Core.DBPackage.*;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
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