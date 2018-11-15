package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import model.JeuModel;
import view.MainFrame;
import view.scenes.MenuScene;
import view.scenes.PlayScene;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PlayController extends Controller {


    @FXML
    private Button btnBack;

    private JeuModel model;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnBack.setOnAction((evt) -> back(null));

    }

    public void startGame(){
        System.out.println("game start");
        model = new JeuModel();
        ((PlayScene)btnBack.getScene()).setModel(model);
        ((PlayScene)btnBack.getScene()).test();
    }



}
