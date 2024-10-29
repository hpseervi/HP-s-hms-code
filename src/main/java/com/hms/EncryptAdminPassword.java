package com.hms;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class EncryptAdminPassword {
    public static void main(String[] args) {
        System.out.println(BCrypt.hashpw("admin", BCrypt.gensalt()));
    }
}
