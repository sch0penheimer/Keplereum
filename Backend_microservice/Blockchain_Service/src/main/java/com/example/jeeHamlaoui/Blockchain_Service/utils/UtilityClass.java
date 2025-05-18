package com.example.jeeHamlaoui.Blockchain_Service.utils;
import java.util.Base64;

public class UtilityClass {
    /**
     * Checks if the provided string is a valid Base64-encoded value.
     * @param str the string to validate as Base64
     * @return {@code true} if the input string is valid Base64; {@code false} otherwise
     */
    public static boolean isValidBase64(String str) {
        try {
            Base64.getDecoder().decode(str);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
