package com.example.sheep.domain.model.gateway;

/**
 * Puerto para cifrado reversible y verificaci?n simple.
 */
public interface EncrypterGateway {
 String encrypt(String password);
 Boolean checkPass(String raw, String encrypted);
}

