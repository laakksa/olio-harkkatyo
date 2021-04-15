package com.juuh.ht;


import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

//lähde: https://www.codeproject.com/Articles/704865/Salted-Password-Hashing-Doing-it-Right
//https://www.geeksforgeeks.org/sha-512-hash-in-java/
public class HashSalt {
    public static String encrypt(String input, byte[] salt) {
        // getInstance() method is called with algorithm SHA-512
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-512");


            // digest() method is called
            // to calculate message digest of the input string
            // returned as array of byte
            md.update(salt);
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);

            // Add preceding 0s to make it 32 bit
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }

            // return the HashText
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

    }

    public static byte[] getSalt() {
        try {
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            byte[] salt = new byte[30];
            secureRandom.nextBytes(salt);
            return salt;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

    }

}
