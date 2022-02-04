package com.jpmorgan.hometaskdemo;

import com.jpmorgan.hometaskdemo.account.Account;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication

public class HometaskdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(HometaskdemoApplication.class, args);

    }

}
