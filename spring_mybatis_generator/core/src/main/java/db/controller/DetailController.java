package db.controller;

import db.mysql.env.RuntimeEnv;
import db.mysql.process.MysqlCommon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

/**
 * @author mymx.jlh
 * @version 1.0.0 2018/9/2 13:11
 */
public class DetailController {

    @FXML
    public Button cancel;
    @FXML
    public ListView<String> tableList;

    @FXML
    public void initialize() {
        ObservableList<String> dataList = FXCollections.observableArrayList();
        tableList.setItems(dataList);
        try {
            dataList.addAll(RuntimeEnv.mc.getTableList());
        } catch (SQLException | ClassNotFoundException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("数据获取失败");
            alert.setHeaderText(e.toString());
            alert.showAndWait();
            e.printStackTrace();
        }

    }

    public void cancelAction(ActionEvent actionEvent) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/fxml/generator.fxml"));
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Mybatis Generator");
            primaryStage.setScene(new Scene(root, 600, 480));
            primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/images/bird.png")));
            primaryStage.show();
            Stage stage = (Stage)cancel.getScene().getWindow();
            stage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
