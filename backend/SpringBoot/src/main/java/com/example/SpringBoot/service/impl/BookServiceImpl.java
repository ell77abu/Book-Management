package com.example.SpringBoot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SpringBoot.common.Result;
import com.example.SpringBoot.entity.Book;
import com.example.SpringBoot.entity.User;
import com.example.SpringBoot.mapper.BookMapper;
import com.example.SpringBoot.mapper.UserMapper;
import com.example.SpringBoot.service.BookService;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    
    @Autowired
    private BookMapper bookMapper;
    
    @Autowired
    private UserMapper userMapper;
    
    @Override
    public Result<Book> addBook(Book book, Long operatorId) {
        // 检查操作者是否存在
        User operator = userMapper.findById(operatorId);
        if (operator == null) {
            return Result.error("2001", "操作者不存在");
        }
        
        // 检查ISBN是否已存在
        Book existBook = bookMapper.findByIsbn(book.getIsbn());
        if (existBook != null) {
            return Result.error("2002", "ISBN已存在");
        }
        
        // 保存书籍
        bookMapper.insert(book);
        
        return Result.success(book);
    }
    
    @Override
    public Result<Book> updateBook(Book book, Long operatorId) {
        // 检查操作者是否存在
        User operator = userMapper.findById(operatorId);
        if (operator == null) {
            return Result.error("2003", "操作者不存在");
        }
        
        // 检查书籍是否存在
        Book existBook = bookMapper.findById(book.getId());
        if (existBook == null) {
            return Result.error("2004", "书籍不存在");
        }
        
        // 更新书籍信息
        bookMapper.update(book);
        
        // 返回更新后的书籍
        return Result.success(bookMapper.findById(book.getId()));
    }
    
    @Override
    public Result<Book> getBookById(Long id) {
        Book book = bookMapper.findById(id);
        if (book == null) {
            return Result.error("2005", "书籍不存在");
        }
        
        return Result.success(book);
    }
    
    @Override
    public Result<Book> getBookByIsbn(String isbn) {
        Book book = bookMapper.findByIsbn(isbn);
        if (book == null) {
            return Result.error("2006", "一本书也没有找到QwQ");
        }
        
        return Result.success(book);
    }
    
    @Override
    public Result<List<Book>> getBooksByTitle(String title) {
        List<Book> books = bookMapper.findByTitle(title);
        if (books.isEmpty()) {
            return Result.error("2007", "一本书也没有找到QwQ");
        }
        return Result.success(books);
    }
    
    @Override
    public Result<List<Book>> getBooksByAuthor(String author) {
        List<Book> books = bookMapper.findByAuthor(author);
        if (books.isEmpty()) {
            return Result.error("2008", "一本书也没有找到QwQ");
        }
        return Result.success(books);
    }
    
    @Override
    public Result<List<Book>> getBooksByPublisher(String publisher) {
        List<Book> books = bookMapper.findByPublisher(publisher);
        if (books.isEmpty()) {
            return Result.error("2009", "一本书也没有找到QwQ");
        }
        return Result.success(books);
    }
    
    @Override
    public Result<List<Book>> getAllBooks() {
        List<Book> books = bookMapper.findAll();
        return Result.success(books);
    }
}
