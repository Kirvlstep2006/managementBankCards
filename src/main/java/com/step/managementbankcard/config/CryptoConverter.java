package com.step.managementbankcard.config;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Converter
public class CryptoConverter implements AttributeConverter<String, String> {

    @Value("${jwt.secret}")
    private String secretKey;


    @Override
    public String convertToDatabaseColumn(String s) {
        if (s == null)
            return null;
        return encrypt(s);
    }

    @Override
    public String convertToEntityAttribute(String s) {
        if(s == null)
            return null;
        return decrypt(s);
    }

    private String encrypt(String data) {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(secretKey);
        return encryptor.encrypt(data);
    }

    private String decrypt(String data) {
        StandardPBEStringEncryptor decryptor = new StandardPBEStringEncryptor();
        decryptor.setPassword(secretKey);
        return decryptor.decrypt(data);
    }
}
