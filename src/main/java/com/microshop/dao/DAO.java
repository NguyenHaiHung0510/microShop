/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.microshop.dao;

import java.sql.SQLException;
import java.util.List;

public interface DAO<T>{
    
    // Đọc: Tìm một đối tượng T (ví dụ: Account, User) bằng ID
    T getById(String id) throws SQLException;
    
    // Đọc: Lấy tất cả các đối tượng T theo một điều kiện lọc
    List<T> getByPrefix(String prefix) throws SQLException;
    
    // Đọc: Trả về tất cả đối tượng T
    List<T> getAll() throws SQLException;
    
    // Tạo: Thêm một đối tượng mới vào DB
    boolean create(T entity) throws SQLException;
    
    // Cập nhật: Cập nhật một đối tượng
    boolean update(T entity) throws SQLException;
    
    // Xóa: Xóa một đối tượng bằng ID
    boolean delete(String id) throws SQLException;
}