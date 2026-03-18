package com.microshop.demo;
import java.io.*;
//Mồi cho SpotBugs (FindSecBugs): Insecure Deserialization
public class SessionCacheManager {
    public void restoreSession(byte[] serializedData) {
        try {
            // LỖI (SpotBugs): Giải mã trực tiếp Object từ nguồn không tin cậy
            ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(serializedData));
            Object userSession = ois.readObject();
        } catch (Exception e) {}
    }
}