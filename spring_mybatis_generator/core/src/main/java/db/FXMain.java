package db;

import db.mysql.env.RuntimeEnv;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * db
 *
 * @author ASUS
 * @date 2017/10/21 10:15
 */
public class FXMain extends Application {
    public static void main(String[] args) throws IOException {
        RuntimeEnv.reader();
//        MainFrame mainFrame=new MainFrame();
        launch(args);
        RuntimeEnv.storage();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/generator.fxml"));
        primaryStage.setTitle("Mybatis Generator");
        primaryStage.setScene(new Scene(root, 500, 480));
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/images/bird.png")));
        Platform.setImplicitExit(false);
        primaryStage.show();
    }
}

