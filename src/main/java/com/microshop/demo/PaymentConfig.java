package com.microshop.demo;
//Mồi cho GitLeaks: Hardcoded Secrets
public class PaymentConfig {
    // Dev cũ hardcode key thẳng vào source code
    public static final String STRIPE_SECRET_KEY = "sk_live_51MabcdeFghIjklMnOpQrStUvWxYz123456789";
    public static final String GITHUB_OAUTH_TOKEN = "ghp_1234567890abcdefghijklmnopqrstuvwxyz1234";

    public String getPaymentToken() {
        return STRIPE_SECRET_KEY;
    }
}