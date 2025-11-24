package com.example.sheep.domain.model.gateway;

public interface EncrypterGateway {

    String encrypt(String password);

    Boolean checkPass(String raw, String encrypted);
}
