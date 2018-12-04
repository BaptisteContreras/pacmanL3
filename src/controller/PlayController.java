package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Direction;
import model.JeuModel;
import model.ThreadRunner;
import model.entities.players.HumanPlayer;
import model.map.Map2dBuilder;
import model.map.MapBuilder;
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

    private String currentMap;

    protected Thread handler;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnBack.setOnAction((evt) -> back(null));
        currentMap = "default.map";

    }

    @Override
    public void back(Stage s) {
        this.shutModelThreadDownIfAlive();
        super.back(s);
    }

    public void startGame(){

        this.shutModelThreadDownIfAlive();
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
       // model.initV1(5,5,10,new Coord2D(4,3),pseudo);
        model.init(new Map2dBuilder(),pseudo,currentMap,200);

        players = model.getHumanPlayers();
        ((PlayScene)btnBack.getScene()).initScene(gameroot,scoreroot,model,model.getCells().length,model.getCells()[0].length,players);
        ThreadRunner runner = new ThreadRunner(model);
        handler = new Thread(runner);
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

    public void changeMap(){
        Map2dBuilder builder = new Map2dBuilder();
        List<String> maps = builder.getMaps();

        ChoiceDialog<String> dialogList = new ChoiceDialog<>(maps.get(0), maps);
        dialogList.setTitle("Charger une map");
        dialogList.setHeaderText("");
        dialogList.setContentText("Choisissez une map à charger :");
        Stage dialogStage2 = (Stage) dialogList.getDialogPane().getScene().getWindow();
        dialogStage2.getIcons().add(new Image("/assets/interface/icon.png"));

        Optional<String> res = dialogList.showAndWait();

        if (res.isPresent()){
            currentMap = res.get();
            startGame();
        }

    }

    private void shutModelThreadDownIfAlive(){
        if (this.handler != null){
            if (this.handler.isAlive())
                this.handler.stop();
            System.out.println("game stopped");
        }
    }

    public static class ScoreController {
    }
}
