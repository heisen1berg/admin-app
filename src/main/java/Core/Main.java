package Core;

import javax.annotation.PostConstruct;

import Core.CachePackage.Key;
import Core.DataStructures.*;
import Core.MainLogic.ControlPanel;
import Core.Services.CacheService;
import Core.UI.CommandConsole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;

import javax.annotation.Resource;
import javax.swing.*;


@SpringBootApplication
public class Main extends SpringBootServletInitializer{
    @Resource
    private ControlPanel controlPanel;
    @PostConstruct
    public void onStartUp(){
        controlPanel.init();
    }
    public static void main(String[] args)
    {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(Main.class);
        builder.headless(false);
        ConfigurableApplicationContext context = builder.run(args);
    }
}