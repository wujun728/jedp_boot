package com.chen.service;

import com.chen.pojo.Books;

import java.util.List;

/**
 * @Description: BookService:底下需要去实现,调用dao层
 * @Author: Mr.Chen
 * @CreateTime: 2020-02-28  15:02
 */
public interface BookService {

    //增加一个Book
    int addBook(Books book);
    //根据id删除一个Book
    int deleteBookById(int id);
    //更新Book
    int updateBook(Books books);
    //根据id查询,返回一个Book
    Books queryBookById(int id);
    //查询全部Book,返回list集合
    List<Books> queryAllBook();
    // 根据书名查询数据
    Books queryBooksByName(String bookName);
}
