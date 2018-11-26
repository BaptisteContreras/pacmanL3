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
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.coordonates.Coord2D;
import model.map.Map2dBuilder;
import view.components.CustomReturn;
import view.scenes.editor.DialogEffectScene;
import view.stages.EditDialogEffect;
import view.components.IdRectangle;
import view.scenes.editor.EditorScene;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnBack.setOnAction((evt) -> back(null));
        builder = new Map2dBuilder();
        builder.resize(20,20);
        currentAction = Action.NO_ACTION;
        currentMode = Mode.MOVE;
        currentCursor = Cursor.DEFAULT;
        cursorswitched=false;

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
                    if (tmp >= 1){
                        newpts = tmp;

                    }
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
                    editorScene.updateScreen(builder.getTab().getGrille());
                }
                break;
            case ADD_CORRIDOR:
                if (currentMode == Mode.CHANGE) {
                    builder.replaceByCorridor(new Coord2D(rec.getJ(),rec.getI()));
                    editorScene.updateScreen(builder.getTab().getGrille());
                    System.out.println("add corridor");
                } break;
            case ADD_PACGUM:
                if (currentMode == Mode.CHANGE) {
                    builder.addPacGum(new Coord2D(rec.getJ(),rec.getI()));
                    editorScene.updateScreen(builder.getTab().getGrille());
                    System.out.println("add consumable");
                }
            break;
            default:System.out.println("No action");
        }
    }

    public void doAction(){

    }

    public void keyHandler(javafx.scene.input.KeyEvent event){
        System.out.println("keyboard input detected");
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
        builder.addEffect(retValue.getName(),retValue.getEffect());
        editorScene.updateList(builder.getEffects());

    }

    public void setEditorScene(EditorScene editorScene) {
        this.editorScene = editorScene;
    }
}
