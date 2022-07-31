package com.thalasoft.learnintouch.data.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.joda.time.LocalDateTime;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.thalasoft.learnintouch.data.dao.domain.UserAccount;
import com.thalasoft.learnintouch.data.service.UserAccountService;

public class App {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = null;
        try {
            context = new ClassPathXmlApplicationContext("spring-hibernate.xml", "spring-data-source.xml", "spring-service.xml");
            
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            String password = "";
            System.out.print("Give your password: ");
            try {
                password = in.readLine();
            } catch (Exception e) {
                System.out.println("Caught an exception!");
            }
            System.out.println("Password: " + password);

            UserAccount user0 = new UserAccount();
            user0.setEmail("stephane@thalasoft.com");
            user0.setPassword("gfgg");
            user0.setPasswordSalt("frere");
            user0.setReadablePassword("toto");
            user0.setFirstname("Stephane");
            user0.setLastname("Eybert");
            user0.setValidUntil(new LocalDateTime().minusDays(1));
            user0.setLastLogin(new LocalDateTime());

            UserAccountService userService = (UserAccountService) context.getBean("userService");
            user0 = userService.savePassword(user0, password);
            System.out.println("Stored password: " + user0.getPassword());
        } finally {
            if (context != null) {
                context.close();
            }
        }
    }

}
