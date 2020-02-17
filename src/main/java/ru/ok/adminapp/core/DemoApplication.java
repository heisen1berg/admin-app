package ru.ok.adminapp.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Queue;

@SpringBootApplication
public class DemoApplication {

    private static int APPLICATION_PORT = 8080;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
