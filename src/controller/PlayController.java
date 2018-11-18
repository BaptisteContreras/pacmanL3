package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import model.JeuModel;
import model.coordonates.Coord2D;
import view.MainFrame;
import view.scenes.MenuScene;
import view.scenes.PlayScene;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PlayController extends Controller {


    @FXML
    private Button btnBack;

    @FXML
    private Pane gameroot;

    @FXML
    private Pane scoreroot;


    private JeuModel model;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnBack.setOnAction((evt) -> back(null));

    }

    public void startGame(){
        System.out.println("game start");
        model = new JeuModel();
        model.initV1(10,5,23,new Coord2D(2,5),"bapt");

        ((PlayScene)btnBack.getScene()).initScene(gameroot,scoreroot,model,25,25);
        //((PlayScene)btnBack.getScene()).test();
    }



}
