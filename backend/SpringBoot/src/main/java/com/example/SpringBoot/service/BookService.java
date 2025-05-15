package com.example.SpringBoot.service;

import java.util.List;
import java.util.Map;

import com.example.SpringBoot.common.Result;
import com.example.SpringBoot.entity.Book;

public interface BookService {
    // 添加新书
    Result<Book> addBook(Book book, Long operatorId);
    
    // 修改书籍信息
    Result<Book> updateBook(Book book, Long operatorId);
    
    // 根据ID查询书籍
    Result<Book> getBookById(Long id);
    
    // 根据ISBN查询书籍
    Result<Book> getBookByIsbn(String isbn);
    
    // 根据书名查询书籍
    Result<List<Book>> getBooksByTitle(String title);
    
    // 根据作者查询书籍
    Result<List<Book>> getBooksByAuthor(String author);
    
    // 根据出版社查询书籍
    Result<List<Book>> getBooksByPublisher(String publisher);
    
    // 获取所有书籍
    Result<List<Book>> getAllBooks();
}
