package com.microshop.demo;
import java.net.URL;
//Mồi cho Codacy: SSRF (Server-Side Request Forgery)
public class AvatarSyncService {
    public void syncFromSocialMedia(String profileUrl) {
        try {
            // LỖI (Codacy): Mở luồng đọc từ URL bất kỳ, có thể bị ép quét mạng nội bộ
            new URL(profileUrl).openConnection().getInputStream();
        } catch (Exception e) {}
    }
}