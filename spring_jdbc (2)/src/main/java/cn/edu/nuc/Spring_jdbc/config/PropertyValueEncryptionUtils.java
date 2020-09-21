package cn.edu.nuc.Spring_jdbc.config;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.util.text.TextEncryptor;

public final class PropertyValueEncryptionUtils {

    private static final String ENCRYPTED_VALUE_PREFIX = "kl(";
    private static final String ENCRYPTED_VALUE_SUFFIX = "##";

    
    public static boolean isEncryptedValues(final String value) {
        if (value == null) {
            return false;
        }
        final String trimmedValue = value.trim();
        return (trimmedValue.startsWith(ENCRYPTED_VALUE_PREFIX) && 
                trimmedValue.endsWith(ENCRYPTED_VALUE_SUFFIX));
    }
    
    private static String getInnerEncryptedValue(final String value) {
        return value.substring(
                ENCRYPTED_VALUE_PREFIX.length(),
                (value.length() - ENCRYPTED_VALUE_SUFFIX.length()));
    }

    
    public static String decrypt(
            final String encodedValue, final StringEncryptor encryptor) {
        return encryptor.decrypt(getInnerEncryptedValue(encodedValue.trim()));
    }

    
    public static String decrypt(
            final String encodedValue, final TextEncryptor encryptor) {
        return encryptor.decrypt(getInnerEncryptedValue(encodedValue.trim()));
    }

    
    public static String encrypt(
            final String decodedValue, final StringEncryptor encryptor) {
        return 
            ENCRYPTED_VALUE_PREFIX + 
            encryptor.encrypt(decodedValue) +
            ENCRYPTED_VALUE_SUFFIX;
    }

    
    public static String encrypt(
            final String decodedValue, final TextEncryptor encryptor) {
        return 
            ENCRYPTED_VALUE_PREFIX + 
            encryptor.encrypt(decodedValue) +
            ENCRYPTED_VALUE_SUFFIX;
    }
    
    
    private PropertyValueEncryptionUtils() {
        super();
    }

    
}

