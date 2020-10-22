package core;

import javax.annotation.PostConstruct;

import core.logic.Control;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;

import javax.annotation.Resource;


@SpringBootApplication
public class Main extends SpringBootServletInitializer{
    @Resource
    private Control control;
    @PostConstruct
    public void onStartUp(){
        control.init();
    }
    public static void main(String[] args)
    {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(Main.class);
        builder.headless(false);
        ConfigurableApplicationContext context = builder.run(args);
    }


}