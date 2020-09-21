package db;

import db.mframe.MainFrame;
import db.mysql.env.RuntimeEnv;
import db.mysql.process.DataTypeSwitch;

import java.io.IOException;

/**
 * db
 *
 * @author ASUS
 * @date 2017/10/21 10:15
 */
public class Main {
    public static void main(String[] args) throws IOException {
        RuntimeEnv.reader();
        RuntimeEnv.readerSwitch(DataTypeSwitch.defaultJSON);
        MainFrame mainFrame=new MainFrame();
        RuntimeEnv.storage();
    }
}

