package com.wujunshen.controller;

import com.wujunshen.entity.Book;
import com.wujunshen.entity.Books;
import com.wujunshen.exception.ResultStatusCode;
import com.wujunshen.service.BookService;
import com.wujunshen.vo.BaseResultVo;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.constraints.NotNull;

/**
 * User:frankwoo(吴峻申) <br>
 * Date:2016-10-17 <br>
 * Time:17:21 <br>
 * Mail:frank_wjs@hotmail.com <br>
 */
@RestController
@Api(value = "/", description = "有关书籍的操作")
public class BookController {
    private static final Logger LOGGER = LoggerFactory.getLogger(BookController.class);
    @Autowired
    private BookService bookService;
    @Autowired
    private DiscoveryClient discoveryClient;

    /**
     * 本地服务实例的信息
     *
     * @return
     */
    @GetMapping("/instance-info")
    @ApiIgnore
    public ServiceInstance showInfo() {
        ServiceInstance localServiceInstance = this.discoveryClient.
                getLocalServiceInstance();
        return localServiceInstance;
    }

    @PostMapping(value = "/api/books", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "添加某本书籍", httpMethod = "POST",
            notes = "添加成功返回bookId",
            response = BaseResultVo.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = BaseResultVo.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    public BaseResultVo saveBook(@Validated @ApiParam(value = "添加的某本书籍信息", required = true) @RequestBody Book book) {
        BaseResultVo baseResultVo = new BaseResultVo();
        baseResultVo.setData(bookService.saveBook(book));
        baseResultVo.setCode(ResultStatusCode.OK.getCode());
        baseResultVo.setMessage(ResultStatusCode.OK.getMessage());
        return baseResultVo;
    }

    @GetMapping(value = "/api/books", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "查询所有书籍", httpMethod = "GET",
            notes = "查询所有书籍",
            response = BaseResultVo.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = BaseResultVo.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    public BaseResultVo getBooks() {
        Books books = bookService.getBooks();
        BaseResultVo baseResultVo = new BaseResultVo();
        if (books != null && books.getBookList().size() != 0) {
            baseResultVo.setData(books);
            baseResultVo.setCode(ResultStatusCode.OK.getCode());
            baseResultVo.setMessage(ResultStatusCode.OK.getMessage());
        } else {
            baseResultVo.setCode(ResultStatusCode.DATA_QUERY_ERROR.getCode());
            baseResultVo.setData("Query books failed");
            baseResultVo.setMessage(ResultStatusCode.DATA_QUERY_ERROR.getMessage());
        }

        return baseResultVo;
    }

    @GetMapping(value = "/api/books/{bookId:[0-9]*}")
    @ApiOperation(value = "查询某本书籍", httpMethod = "GET",
            notes = "根据bookId，查询到某本书籍",
            response = BaseResultVo.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = BaseResultVo.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    public BaseResultVo getBook(@ApiParam(value = "书籍ID", required = true) @PathVariable("bookId") Integer bookId) {
        LOGGER.info("请求参数bookId值：" + bookId);
        Book book = bookService.getBook(bookId);
        BaseResultVo baseResultVo = new BaseResultVo();
        if (book != null) {
            LOGGER.info("查询到书籍ID为" + bookId + "的书籍");
            baseResultVo.setData(book);
            baseResultVo.setCode(ResultStatusCode.OK.getCode());
            baseResultVo.setMessage(ResultStatusCode.OK.getMessage());
        } else {
            LOGGER.info("没有查询到书籍ID为" + bookId + "的书籍");
            baseResultVo.setCode(ResultStatusCode.DATA_QUERY_ERROR.getCode());
            baseResultVo.setData("Query book failed id=" + bookId);
            baseResultVo.setMessage(ResultStatusCode.DATA_QUERY_ERROR.getMessage());
        }

        return baseResultVo;
    }

    @PutMapping(value = "/api/books/{bookId:[0-9]*}")
    @ApiOperation(value = "更新某本书籍", httpMethod = "PUT",
            notes = "更新的某本书籍信息",
            response = BaseResultVo.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = BaseResultVo.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    public BaseResultVo updateBook(@NotNull @ApiParam(value = "要更新的某本书籍ID", required = true) @PathVariable("bookId") Integer bookId, @Validated @NotNull @ApiParam(value = "要更新的某本书籍信息", required = true) @RequestBody Book book) {
        LOGGER.info("请求参数bookId值：" + bookId);
        BaseResultVo baseResultVo = new BaseResultVo();
        if (bookId == null && book == null) {
            baseResultVo.setCode(ResultStatusCode.DATA_INPUT_ERROR.getCode());
            baseResultVo.setMessage(ResultStatusCode.DATA_INPUT_ERROR.getMessage());
            return baseResultVo;
        }

        if (bookService.getBook(bookId) == null) {
            baseResultVo.setCode(ResultStatusCode.DATA_QUERY_ERROR.getCode());
            baseResultVo.setData("book id=" + bookId + " not existed");
            baseResultVo.setMessage(ResultStatusCode.DATA_QUERY_ERROR.getMessage());
            return baseResultVo;
        }

        Book updatedBook = bookService.updateBook(book);
        if (updatedBook != null) {
            baseResultVo.setData(updatedBook);
            baseResultVo.setCode(ResultStatusCode.OK.getCode());
            baseResultVo.setMessage(ResultStatusCode.OK.getMessage());
        } else {
            baseResultVo.setCode(ResultStatusCode.DATA_UPDATED_ERROR.getCode());
            baseResultVo.setData("Update book failed id=" + book.getBookId());
            baseResultVo.setMessage(ResultStatusCode.DATA_UPDATED_ERROR.getMessage());
        }

        return baseResultVo;
    }

    @DeleteMapping(value = "/api/books/{bookId:[0-9]*}")
    @ApiOperation(value = "删除某本书籍信息", httpMethod = "DELETE",
            notes = "删除某本书籍信息",
            response = BaseResultVo.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = BaseResultVo.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    public BaseResultVo deleteBook(@ApiParam(value = "要删除的某本书籍ID", required = true) @PathVariable("bookId") Integer bookId) {
        BaseResultVo baseResultVo = new BaseResultVo();
        if (bookService.deleteBook(bookId) == 1) {
            baseResultVo.setData("Deleted book id=" + bookId);
            baseResultVo.setCode(ResultStatusCode.OK.getCode());
            baseResultVo.setMessage(ResultStatusCode.OK.getMessage());
        } else {
            baseResultVo.setCode(ResultStatusCode.DATA_DELETED_ERROR.getCode());
            baseResultVo.setData("Deleted book failed id=" + bookId);
            baseResultVo.setMessage(ResultStatusCode.DATA_DELETED_ERROR.getMessage());
        }
        return baseResultVo;
    }
}