package com.flixview.flixview;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.flixview"})
public class FlixviewApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlixviewApplication.class, args);
    }

}
