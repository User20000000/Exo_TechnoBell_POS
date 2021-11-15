package com.pos2.pos2;

import com.pos2.pos2.Exeptions.AlreadyExist;
import com.pos2.pos2.Exeptions.ElementNotFound;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Pos2Application {

    public static void main(String[] args) throws ElementNotFound, AlreadyExist {
        //SpringApplication.run(Pos2Application.class, args);

        ApplicationContext ctx = SpringApplication.run(Pos2Application.class, args);

        //test t = ctx.getBean(test.class);

        //t.login();

        //t.delete();

        //t.test();

        //t.addUserTest();

        //t.deleteUserTest();

    }

}
