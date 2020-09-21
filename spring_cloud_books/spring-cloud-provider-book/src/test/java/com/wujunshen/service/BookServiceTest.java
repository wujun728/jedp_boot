package com.wujunshen.service;

import com.wujunshen.ProviderBookApplication;
import com.wujunshen.dao.BookMapper;
import com.wujunshen.entity.Book;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * User:frankwoo(吴峻申) <br>
 * Date:2016-10-18 <br>
 * Time:10:36 <br>
 * Mail:frank_wjs@hotmail.com <br>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {ProviderBookApplication.class})
//相当于  --spring.profiles.active=dev
@ActiveProfiles(value = "dev")
public class BookServiceTest {
    @Autowired
    private BookMapper bookMapper;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void saveBook() throws Exception {
        Book book = new Book();
        book.setBookName("fuck");
        book.setPublisher("wujunshen");
        bookMapper.insert(book);
    }

}