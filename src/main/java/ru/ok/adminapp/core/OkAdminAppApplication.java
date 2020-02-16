package ru.ok.adminapp.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OkAdminAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(OkAdminAppApplication.class, args);
        System.err.println("here");
    }
}
