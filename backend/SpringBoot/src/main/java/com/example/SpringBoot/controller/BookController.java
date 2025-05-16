package com.example.SpringBoot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.PathVariable;

import com.example.SpringBoot.common.Result;
import com.example.SpringBoot.entity.Book;
import com.example.SpringBoot.service.BookService;

@RestController
@RequestMapping("/api/book")
public class BookController {

    @Autowired
    private BookService bookService;

    // 添加新书
    @PostMapping("/add")
    public Result<Book> addBook(@RequestBody Book book, @RequestHeader("userId") Long operatorId) {
        return bookService.addBook(book, operatorId);
    }
    
    // 修改书籍信息
    @PostMapping("/update")
    public Result<Book> updateBook(@RequestBody Book book, @RequestHeader("userId") Long operatorId) {
        return bookService.updateBook(book, operatorId);
    }
    
    // 根据ID查询书籍
    // @GetMapping("/id/{id}")
    // public Result<Book> getBookById(@PathVariable Long id) {
    //     return bookService.getBookById(id);
    // }
    
    // 根据ISBN查询书籍
    @GetMapping("/isbn")
    public Result<Book> getBookByIsbn(@RequestParam String isbn) {
        return bookService.getBookByIsbn(isbn);
    }
    
    // 根据书名查询书籍
    @GetMapping("/title")
    public Result<List<Book>> getBooksByTitle(@RequestParam String title) {
        return bookService.getBooksByTitle(title);
    }
    
    // 根据作者查询书籍
    @GetMapping("/author")
    public Result<List<Book>> getBooksByAuthor(@RequestParam String author) {
        return bookService.getBooksByAuthor(author);
    }
    
    // 根据出版社查询书籍
    @GetMapping("/publisher")
    public Result<List<Book>> getBooksByPublisher(@RequestParam String publisher) {
        return bookService.getBooksByPublisher(publisher);
    }
    
    // 获取所有书籍
    @GetMapping("/all")
    public Result<List<Book>> getAllBooks() {
        return bookService.getAllBooks();
    }
}
