package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import model.Direction;
import model.JeuModel;
import model.coordonates.Coord2D;
import model.entities.players.Player;
import view.MainFrame;
import view.scenes.MenuScene;
import view.scenes.PlayScene;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static java.lang.Thread.sleep;

public class PlayController extends Controller {


    @FXML
    private Button btnBack;

    @FXML
    private Pane gameroot;

    @FXML
    private Pane scoreroot;


    private JeuModel model;

    private List<Player> players;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnBack.setOnAction((evt) -> back(null));

    }

    public void startGame(){
        System.out.println("game start");
        model = new JeuModel();
        model.initV1(5,5,23,new Coord2D(2,5),"bapt");

        ((PlayScene)btnBack.getScene()).initScene(gameroot,scoreroot,model,5,5);
        players = model.getPlayerList();
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        model.mainTurn();
        //((PlayScene)btnBack.getScene()).test();
    }

    public void keyHandler(KeyEvent event){

        if (event.getCode() == KeyCode.A && model == null)
            startGame();
        switch (event.getCode()){
            case Z: model.setDirection(players.get(0),Direction.TOP);break;
            case Q: model.setDirection(players.get(0),Direction.LEFT);break;
            case S: model.setDirection(players.get(0),Direction.BOTTOM);break;
            case D: model.setDirection(players.get(0),Direction.RIGHT);break;

        }


        System.out.println("key pressed : " + (event.getCode()));

    }



}
