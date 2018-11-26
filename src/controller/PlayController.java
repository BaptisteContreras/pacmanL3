package controller;

import controller.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Direction;
import model.JeuModel;
import model.ThreadRunner;
import model.coordonates.Coord2D;
import model.entities.players.HumanPlayer;
import view.scenes.game.PlayScene;

import java.net.URL;
import java.util.List;
import java.util.Optional;
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

    private List<HumanPlayer> players;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnBack.setOnAction((evt) -> back(null));

    }

    public void startGame(){
        String pseudo = "Player 1";
        System.out.println("game start");
        TextInputDialog dialog = new TextInputDialog("Pacman");
        Stage dialogStage = (Stage) dialog.getDialogPane().getScene().getWindow();
        dialogStage.getIcons().add(new Image("/assets/interface/icon.png"));
        dialog.setTitle(" Pacman L3");
        dialog.setHeaderText("");
        dialog.setContentText("Entrez votre pseudo (max 15 caractères):");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            if (result.get().length() < 15){
                pseudo = result.get();
            }
        }
        if (model != null){
            model.setFinish(true);
            try {
                sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        model = new JeuModel();
        model.initV1(5,5,10,new Coord2D(4,3),pseudo);

        players = model.getHumanPlayers();
        ((PlayScene)btnBack.getScene()).initScene(gameroot,scoreroot,model,5,5,players);
        ThreadRunner runner = new ThreadRunner(model);
        Thread handler = new Thread(runner);
        handler.start();
       // model.mainTurn();
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
//        System.out.println("Direction : " + (players.get(0).getDirection()));

    }



}
