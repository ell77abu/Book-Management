package com.example.SpringBoot.mapper;

import com.example.SpringBoot.entity.Book;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface BookMapper {
    // 根据ID查询书籍
    @Select("SELECT * FROM book WHERE id = #{id}")
    Book findById(Long id);
    
    // 根据ISBN查询书籍
    @Select("SELECT * FROM book WHERE isbn = #{isbn}")
    Book findByIsbn(String isbn);
    
    // 根据书名模糊查询
    @Select("SELECT * FROM book WHERE title LIKE CONCAT('%', #{title}, '%')")
    List<Book> findByTitle(String title);
    
    // 根据作者模糊查询
    @Select("SELECT * FROM book WHERE author LIKE CONCAT('%', #{author}, '%')")
    List<Book> findByAuthor(String author);
    
    // 根据出版社模糊查询
    @Select("SELECT * FROM book WHERE publisher LIKE CONCAT('%', #{publisher}, '%')")
    List<Book> findByPublisher(String publisher);
    
    // 查询所有书籍
    @Select("SELECT * FROM book")
    List<Book> findAll();
    
    // 插入新书
    @Insert("INSERT INTO book(isbn, title, author, publisher, retail_price, stock_quantity) " +
            "VALUES(#{isbn}, #{title}, #{author}, #{publisher}, #{retailPrice}, #{stockQuantity})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Book book);
    
    // 更新书籍信息（使用动态SQL）
    @Update("<script>" +
            "UPDATE book " +
            "<set>" +
            "  <if test='title != null'>title = #{title},</if>" +
            "  <if test='author != null'>author = #{author},</if>" +
            "  <if test='publisher != null'>publisher = #{publisher},</if>" +
            "  <if test='retailPrice != null'>retail_price = #{retailPrice},</if>" +
            "  <if test='stockQuantity != null'>stock_quantity = #{stockQuantity},</if>" +
            "</set>" +
            "WHERE id = #{id}" +
            "</script>")
    int update(Book book);
}
