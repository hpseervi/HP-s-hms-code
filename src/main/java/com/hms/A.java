package com.hms;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class A {
    public static void main(String[] args) {
        //first way to encrypt password:-
        //PasswordEncoder en = new BCryptPasswordEncoder();
        //System.out.println(en.encode("testing"));

        //second way to encrypt password:-
        //gensalt() can take value of log_rounds from 4 to 31
        String enPwd = BCrypt.hashpw("testing", BCrypt.gensalt(5));

    }
}
