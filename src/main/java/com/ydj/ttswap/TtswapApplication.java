package com.ydj.ttswap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
public class TtswapApplication {
//
//    @PostConstruct
//    void started(){
//        TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
//    }

    public static void main(String[] args) {
        SpringApplication.run(TtswapApplication.class, args);
    }

}
