package com.chen.service;

import com.chen.mapper.BookMapper;
import com.chen.pojo.Books;

import java.util.List;

/**
 * @Description:
 * @Author: Mr.Chen
 * @CreateTime: 2020-02-28  15:02
 */
public class BookServiceImpl implements BookService {
    //调用dao层的操作，设置一个set接口，方便Spring管理
    // @Autowired
    private BookMapper bookMapper;

    public void setBookMapper(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    public int addBook(Books book) {
        return bookMapper.addBook(book);
    }

    public int deleteBookById(int id) {
        return bookMapper.deleteBookById(id);
    }

    public int updateBook(Books books) {
        return bookMapper.updateBook(books);
    }

    public Books queryBookById(int id) {
        return bookMapper.queryBookById(id);
    }

    public List<Books> queryAllBook() {
        return bookMapper.queryAllBook();
    }

    // 业务层干嘛的？
    public Books queryBooksByName(String bookName) {
        return bookMapper.queryBooksByName(bookName);
    }
}
