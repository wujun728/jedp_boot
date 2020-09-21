CREATE TABLE
    user
    (
        id INT NOT NULL AUTO_INCREMENT,
        name VARCHAR(255),
        password VARCHAR(255),
        salt VARCHAR(255),
        role VARCHAR(255),
        PRIMARY KEY (id)
    )
    ENGINE=MyISAM DEFAULT CHARSET=utf-8;

CREATE TABLE
    simple_book
    (
        bookName VARCHAR(255),
        publisher VARCHAR(255) COLLATE utf8_general_ci,
        bookId INT NOT NULL AUTO_INCREMENT,
        PRIMARY KEY (bookId)
    )
    ENGINE=MyISAM DEFAULT CHARSET=utf-8;

