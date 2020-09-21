package com.chen.controller;

import com.chen.pojo.Books;
import com.chen.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    @Qualifier("BookServiceImpl")
    private BookService bookService;


    // 查询书籍  前端--> 接收请求---> service--> mapper ---> 操作数据库
    @RequestMapping("/queryBook")
    public String queryBook(String queryBookName,Model model){
        System.out.println("要查询的数据名称为："+queryBookName);
        // 差一个查询的业务逻辑
        Books books = bookService.queryBooksByName(queryBookName.trim());
        List<Books> list = new ArrayList<Books>();
        list.add(books);

        if (books==null){
            list = bookService.queryAllBook();
            model.addAttribute("error","没有找到这本书");
        }

        model.addAttribute("list", list);
        return "allBook";
    }

    // 查询出来的数据库的全部书籍
    @RequestMapping("/allBook")
    public String list(Model model) {
        List<Books> list = bookService.queryAllBook();
        model.addAttribute("list", list);
        return "allBook";
    }

    // 视图控制只和 Controller挂钩
    // /book/toAddBook  跳转到 添加书籍页面
    // /book/addBook    添加书籍
    @RequestMapping("/toAddBook")
    public String toAddPaper() {
        return "addBook";
    }

    @RequestMapping("/addBook")
    public String addPaper(Books books) {
        System.out.println(books);
        bookService.addBook(books);
        return "redirect:/book/allBook";
    }

    // 视图控制只和 Controller挂钩
    // 跳转到修改页面 (携带上原来的数据，回显到前端!)
    // 修改数据
    @RequestMapping("/toUpdateBook")
    public String toUpdateBook(Model model, int id) {
        Books books = bookService.queryBookById(id);
        System.out.println(books);
        model.addAttribute("book",books );
        return "updateBook";
    }

    @RequestMapping("/updateBook")
    public String updateBook(Model model, Books book) {
        System.out.println(book);
        bookService.updateBook(book);
        Books books = bookService.queryBookById(book.getBookID());
        model.addAttribute("books", books);
        return "redirect:/book/allBook";
    }

    // 路径变量，restfulAPI 风格
    // 路径变量的请求格式： 直接使用 {} 获取参数
    // 如何让参数识别这个请求中的位置，@PathVariable("bookId") 绑定请求过来的变量
    @RequestMapping("/del/{bookId}")
    public String deleteBook(@PathVariable("bookId") int id) {
        bookService.deleteBookById(id);
        return "redirect:/book/allBook";
    }
}