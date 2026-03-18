package com.microshop.demo;
import java.util.Random;
//Mồi cho SpotBugs (FindSecBugs): Insecure Randomness
public class TokenGenerator {
    public String createResetToken() {
        try {
            // LỖI (SpotBugs): Random cơ bản có thể bị đoán trước hạt giống (Predictable Random)
            Random rand = new Random();
            return "RESET-" + rand.nextInt(999999);
        } catch (Exception e) {
            return null;
        }
    }
}