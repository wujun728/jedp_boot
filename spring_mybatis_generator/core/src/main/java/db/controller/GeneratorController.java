package db.controller;

import db.model.SelectModel;
import db.mysql.env.RuntimeEnv;
import db.mysql.model.DataBaseTypeEnum;
import db.mysql.process.MysqlCommon;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.EventObject;

/**
 * Created with IntelliJ IDEA.
 * User: jiluohao@yixin.im
 * Date: 2018-08-27 14:07
 * Description:
 */
public class GeneratorController {
    @FXML
    public ChoiceBox<String> jdbcSelector;
    @FXML
    public ChoiceBox<SelectModel<Integer,String>> jdbcJarSelector;
    @FXML
    public Button jdbcJarUrlBtn;
    @FXML
    public TextField jdbcJarUrl;
    @FXML
    public HBox jdbcJarUrlHBox;
    @FXML
    public TextField urlText;
    @FXML
    public TextField schemaText;
    @FXML
    public TextField userText;
    @FXML
    public TextField passwordText;

    @FXML
    public void initialize() {
        // jdbc 类型选择初始化
        jdbcSelector.setItems(FXCollections.observableArrayList(DataBaseTypeEnum.Mysql.getDataBaseTypeName(),DataBaseTypeEnum.Postgres.getDataBaseTypeName()));
        jdbcSelector.setValue(RuntimeEnv.pp.getDataBaseType());
        // jdbcJar 选择初始化
        SelectModel<Integer, String> selectModel = new SelectModel<>(1, "插件自带");
        jdbcJarSelector.setItems(FXCollections.observableArrayList(selectModel, new SelectModel<>(2, "自己选择")));
        jdbcJarSelector.setValue(selectModel);

        jdbcJarUrl.setDisable(!jdbcJarSelector.getValue().getKey().equals(2));
        jdbcJarUrlBtn.setDisable(!jdbcJarSelector.getValue().getKey().equals(2));

        urlText.setText(RuntimeEnv.pp.getUrl());
        userText.setText(RuntimeEnv.pp.getUser());
        schemaText.setText(RuntimeEnv.pp.getSchema());
        passwordText.setText(RuntimeEnv.pp.getPassword());

    }

    public void jdbcJarAction(ActionEvent actionEvent) {
        jdbcJarUrl.setDisable(!jdbcJarSelector.getValue().getKey().equals(2));
        jdbcJarUrlBtn.setDisable(!jdbcJarSelector.getValue().getKey().equals(2));
    }

    public void linkDataBaseAction(ActionEvent actionEvent) {

        if (StringUtils.isBlank(jdbcSelector.getValue()) || StringUtils.isBlank(urlText.getText()) || StringUtils.isBlank(userText.getText()) || StringUtils.isBlank(schemaText.getText()) || StringUtils.isBlank(passwordText.getText())){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("参数有误");
            alert.setHeaderText("参数不能为空");
            alert.showAndWait();
            return;
        }


        RuntimeEnv.pp.setDataBaseType(jdbcSelector.getValue());
        RuntimeEnv.pp.setUrl(urlText.getText().trim());
        RuntimeEnv.pp.setUser(userText.getText().trim());
        RuntimeEnv.pp.setSchema(schemaText.getText().trim());
        RuntimeEnv.pp.setPassword(passwordText.getText().trim());
        try {
            RuntimeEnv.mc = new MysqlCommon();
            Parent anotherRoot = FXMLLoader.load(getClass().getResource("/fxml/detail.fxml"));
            Stage anotherStage = new Stage();
            anotherStage.setTitle("Mybatis Generator");
            anotherStage.setScene(new Scene(anotherRoot, 800, 480));
            anotherStage.getIcons().add(new Image(getClass().getResourceAsStream("/images/bird.png")));
            anotherStage.show();
            Stage stage = (Stage)jdbcSelector.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("数据连接失败");
            alert.setHeaderText(e.toString());
            alert.showAndWait();
            e.printStackTrace();
        }
        RuntimeEnv.storage();
    }
}
