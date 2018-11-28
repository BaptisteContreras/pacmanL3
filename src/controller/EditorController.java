package controller;


import controller.Controller;
import controller.editor.DialogueEdit1Controller;
import controller.modes.Action;
import controller.modes.Cursor;
import controller.modes.Mode;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Direction;
import model.coordonates.Coord2D;
import model.effects.Effect;
import model.entities.characters.Ghost;
import model.entities.players.AIPlayer;
import model.map.Map2dBuilder;
import model.map.MapLoader;
import view.components.CustomReturn;
import view.scenes.editor.DialogEffectScene;
import view.stages.EditDialogEffect;
import view.components.IdRectangle;
import view.scenes.editor.EditorScene;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class EditorController extends Controller {

    @FXML
    private Button btnBack;

    @FXML
    private Button write;


    @FXML
    private Button load;

    @FXML
    private ListView selectEffects;


    @FXML
    private Pane map;

    @FXML
    private Button resize;

    @FXML
    private Button wall;

    private Cursor currentCursor;

    @FXML
    private Button corridor;
    private Map2dBuilder builder;
    private Action currentAction;
    private Mode currentMode;
    private EditorScene editorScene;
    private boolean cursorswitched;
    private Effect currentEffect;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnBack.setOnAction((evt) -> back(null));
        builder = new Map2dBuilder();
        builder.resize(20,20);
        currentAction = Action.NO_ACTION;
        currentMode = Mode.MOVE;
        currentCursor = Cursor.DEFAULT;
        cursorswitched=false;
        currentEffect = null;

    }

    public void prepareEditor(){
        // TODO prepare l'application pour editer une map
        System.out.println("Prepare editor to be used !");
        ((EditorScene)map.getScene()).initScene(20,20,map);
        ((EditorScene)map.getScene()).setController(this);
        builder.registerBasicEffect();
        ((EditorScene)map.getScene()).initListView(builder.getEffects(),selectEffects);



    }
    public void writeTest(){
        System.out.println("write");
    }
    public void loadTest(){
        System.out.println("load");
    }

    public void resize(){
        int newWidth = 20;
        int newHeight = 20;
        TextInputDialog dialog = new TextInputDialog("20");
        Stage dialogStage = (Stage) dialog.getDialogPane().getScene().getWindow();
        dialogStage.getIcons().add(new Image("/assets/interface/icon.png"));
        dialog.setTitle(" Pacman L3");
        dialog.setHeaderText("");
        dialog.setContentText("Nouvelle taille :");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            if (result.get().length() < 15){
                String res = result.get();
                try {
                   int tmp = Integer.parseInt(res);
                    if (tmp >= 5 && tmp <= 30){
                        newHeight = tmp;
                        newWidth = tmp;
                    }
                }catch (NumberFormatException ignored){

                }


            }
        }
        ((EditorScene)map.getScene()).initScene(newWidth,newHeight,map);
        editorScene.setController(this);
        builder.resize(newWidth,newHeight);
        System.out.println(builder);

    }

    public void addCorridor(){
        currentAction = Action.ADD_CORRIDOR;
        currentCursor = Cursor.CORRIDOR;
        editorScene.setCursor(currentCursor);
    }
    public void addWall(){

        currentAction = Action.ADD_WALL;
        currentCursor = Cursor.WALL;
        editorScene.setCursor(currentCursor);


    }

    public void addPacGum(){
        int newpts = 5;
        TextInputDialog dialog = new TextInputDialog("5");
        Stage dialogStage = (Stage) dialog.getDialogPane().getScene().getWindow();
        dialogStage.getIcons().add(new Image("/assets/interface/icon.png"));
        dialog.setTitle(" Pacman L3");
        dialog.setHeaderText("");
        dialog.setContentText("Point par pacgum :");

        Optional<String> result = dialog.showAndWait();

        if (result.isPresent()){
            if (result.get().length() < 15){
                String res = result.get();
                try {
                    int tmp = Integer.parseInt(res);

                    newpts = tmp;


                }catch (NumberFormatException ignored){

                }


            }
        }
        builder.setPtsPacGum(newpts);
        currentAction = Action.ADD_PACGUM;
        currentCursor = Cursor.PACGUM;
        editorScene.setCursor(currentCursor);
    }

    public void clickOnRect(IdRectangle rec){
        switch (currentAction){
            case NO_ACTION: if (currentMode == Mode.CHANGE)System.out.println(rec.getRectId());break;
            case ADD_WALL:
                if (currentMode == Mode.CHANGE){
                    builder.rewall(new Coord2D(rec.getJ(),rec.getI()));
                    System.out.println("add wall");
                }
                break;
            case ADD_CORRIDOR:
                if (currentMode == Mode.CHANGE) {
                    builder.replaceByCorridor(new Coord2D(rec.getJ(),rec.getI()));
                    System.out.println("add corridor");
                } break;
            case ADD_PACGUM:
                if (currentMode == Mode.CHANGE) {
                    builder.addPacGum(new Coord2D(rec.getJ(),rec.getI()));
                    System.out.println("add consumable");
                }
            break;
            case ADD_CUSTOM:
                if (currentMode == Mode.CHANGE) {
                    builder.addSuperPacgum(new Coord2D(rec.getJ(),rec.getI()),currentEffect);
                    System.out.println("add custom");

                }
                break;
            case ADD_GHOST:
                if (currentMode == Mode.CHANGE) {
                    builder.addEnnemy(new Coord2D(rec.getJ(),rec.getI()));
                    System.out.println("add ghost");

                }
                break;
            case ADD_SPAWN:
                if (currentMode == Mode.CHANGE) {
                    builder.addHumanSpawn(new Coord2D(rec.getJ(),rec.getI()));
                    System.out.println("add spawn");

                }
                break;
            default:System.out.println("No action");
        }
        editorScene.updateScreen(builder.getTab().getGrille());
        editorScene.updateSpawn(builder.getAllCoord(),builder.getSpawn());

    }

    public void doAction(){

    }

    public void keyHandler(javafx.scene.input.KeyEvent event){
        //System.out.println("keyboard input detected");
        switch (event.getCode()){
            case Z:currentMode=Mode.CHANGE;
                System.out.println("MODE EDIT TILES ON");break;
            case A:currentMode=Mode.MOVE;
                System.out.println("MODE EDIT TILES OFF");break;
            case P: if (!cursorswitched){
                editorScene.setCursor(Cursor.DEFAULT);
                cursorswitched = true;
            }else{
                editorScene.setCursor(currentCursor);
                cursorswitched = false;
            }
            break;

        }

    }

    public void createEffect(){

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/fxml/dialogEdit1.fxml"));
        Parent parent = null;
        try {
            parent = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        DialogEffectScene scene = new DialogEffectScene(parent, 400, 400);
        EditDialogEffect stage = new EditDialogEffect();
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.setScene(scene);
        DialogueEdit1Controller ctrl = fxmlLoader.getController();
        ctrl.initDialogue(builder.getEffects(),scene);
        CustomReturn retValue=stage.showAndReturn(ctrl);

        System.out.println(retValue);
        if (retValue != null)
            builder.addEffect(retValue.getName(),retValue.getEffect());
        editorScene.updateList(builder.getEffects());

    }

    public void setEditorScene(EditorScene editorScene) {
        this.editorScene = editorScene;
    }


    public void addSuperPacGum(){
        int newpts = 10;
        TextInputDialog dialog = new TextInputDialog("10");
        Stage dialogStage = (Stage) dialog.getDialogPane().getScene().getWindow();
        dialogStage.getIcons().add(new Image("/assets/interface/icon.png"));
        dialog.setTitle(" Pacman L3");
        dialog.setHeaderText("");
        dialog.setContentText("Points pour les super pacgum :");

        Optional<String> result = dialog.showAndWait();

        if (result.isPresent()){
            if (result.get().length() < 15){
                String res = result.get();
                try {
                    int tmp = Integer.parseInt(res);
                    if (tmp >= 1){
                        newpts = tmp;

                    }
                }catch (NumberFormatException ignored){

                }


            }
        }
        builder.setPtsSuper(newpts);
        // select effect
        List<String> choices = new ArrayList<>();
        for (Map.Entry<String,Effect> entry:builder.getEffects().entrySet()){
            choices.add(entry.getKey());
        }


        ChoiceDialog<String> dialogList = new ChoiceDialog<>(choices.get(0), choices);
        dialogList.setTitle("Effet de votre SuperPacgum");
        dialogList.setHeaderText("");
        dialogList.setContentText("Choisissez un effet :");
        Stage dialogStage2 = (Stage) dialogList.getDialogPane().getScene().getWindow();
        dialogStage2.getIcons().add(new Image("/assets/interface/icon.png"));

        Optional<String> res = dialogList.showAndWait();
        if (res.isPresent()){
            Effect tmpEffect = builder.getEffects().get(res.get());
            if (tmpEffect != null){
                currentAction = Action.ADD_CUSTOM;
                currentCursor = Cursor.SUPER;
                editorScene.setCursor(currentCursor);
                currentEffect = tmpEffect;
            }
        }
    }

    public void addGhost(){
        int newpts = 10;
        TextInputDialog dialog = new TextInputDialog("20");
        Stage dialogStage = (Stage) dialog.getDialogPane().getScene().getWindow();
        dialogStage.getIcons().add(new Image("/assets/interface/icon.png"));
        dialog.setTitle(" Pacman L3");
        dialog.setHeaderText("");
        dialog.setContentText("Points pour les fantomes :");

        Optional<String> result = dialog.showAndWait();

        if (result.isPresent()){
            if (result.get().length() < 15){
                String res = result.get();
                try {
                    int tmp = Integer.parseInt(res);
                    if (tmp >= 1){
                        newpts = tmp;

                    }
                }catch (NumberFormatException ignored){

                }


            }
        }
        builder.setPtsGhost(newpts);
        // select effect
        List<String> choices = new ArrayList<>();
        choices.add("HAUT");
        choices.add("GAUCHE");
        choices.add("BAS");
        choices.add("DROITE");


        ChoiceDialog<String> dialogList = new ChoiceDialog<>(choices.get(0), choices);
        dialogList.setTitle("Direction de base");
        dialogList.setHeaderText("");
        dialogList.setContentText("Choisissez la direction de base du fantome :");
        Stage dialogStage2 = (Stage) dialogList.getDialogPane().getScene().getWindow();
        dialogStage2.getIcons().add(new Image("/assets/interface/icon.png"));

        Optional<String> res = dialogList.showAndWait();
        if (res.isPresent()){
            if (res.get() != null){
                Direction newDirection = Direction.RIGHT;
                switch (res.get()){
                    case "HAUT": newDirection=Direction.TOP;break;
                    case "GAUCHE": newDirection=Direction.LEFT;break;
                    case "BAS": newDirection=Direction.BOTTOM;break;
                }
                builder.setGhostDirection(newDirection);
            }
        }
        currentAction = Action.ADD_GHOST;
        currentCursor = Cursor.GHOST;
        editorScene.setCursor(currentCursor);

    }

    public void addSpawn(){
        currentAction = Action.ADD_SPAWN;
        currentCursor = Cursor.SPAWN;
        editorScene.setCursor(currentCursor);

    }

    public void save(){
        String mapName = "newMap01";
        boolean ok = true;
        boolean save = true;
        do {
            TextInputDialog dialog = new TextInputDialog("");
            Stage dialogStage = (Stage) dialog.getDialogPane().getScene().getWindow();
            dialogStage.getIcons().add(new Image("/assets/interface/icon.png"));
            dialog.setTitle(" Pacman L3");
            dialog.setHeaderText("");
            dialog.setContentText("Nom de la map :");

            Optional<String> result = dialog.showAndWait();

            if (result.isPresent()){
                if (result.get().length() < 20){
                    String res = result.get();
                    mapName = res;
                    boolean exist = builder.isMapNameValide(res);
                    if (exist){
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Ecraser ancienne map");
                        alert.setHeaderText("");
                        alert.setContentText("Cette map existe deja, voulez vous l'écraser ?");

                        Optional<ButtonType> r = alert.showAndWait();
                        if (r.get() == ButtonType.OK){
                            ok = false;
                        }
                    }else {
                        ok = false;
                    }

                }
            }else {
                ok = false;
                save = false;
            }
        }while (ok);
        if (save)
            builder.saveMap(mapName);
    }

    public void load(){
        List<String> maps = builder.getMaps();

        ChoiceDialog<String> dialogList = new ChoiceDialog<>(maps.get(0), maps);
        dialogList.setTitle("Charger une map");
        dialogList.setHeaderText("");
        dialogList.setContentText("Choisissez une map à charger :");
        Stage dialogStage2 = (Stage) dialogList.getDialogPane().getScene().getWindow();
        dialogStage2.getIcons().add(new Image("/assets/interface/icon.png"));

        Optional<String> res = dialogList.showAndWait();

        if (res.isPresent()){
            builder.loadMap(res.get());
            model.map.Map newMap = builder.getMap();
            editorScene.initScene(newMap.getWidth(),newMap.getHeight(),map);
            editorScene.setController(this);

            editorScene.initListView(builder.getEffects(),selectEffects);
            editorScene.updateScreen(builder.getTab().getGrille());
            editorScene.updateSpawn(builder.getAllCoord(),builder.getSpawn());
        }
    }
}
