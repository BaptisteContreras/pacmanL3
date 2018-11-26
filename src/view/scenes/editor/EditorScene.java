package view.scenes.editor;

import controller.modes.Cursor;
import controller.EditorController;
import javafx.event.EventHandler;
import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.SceneAntialiasing;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import model.effects.Effect;
import model.entities.cells.Cell;
import model.entities.cells.Corridor;
import model.entities.cells.Wall;
import model.entities.characters.Character;
import model.entities.characters.Ennemy;
import model.entities.characters.PacMan;
import model.entities.consumables.NoEffectConsumable;
import view.AssetLoader;
import view.components.IdRectangle;
import view.scenes.GameScene;

import java.util.Map;

public class EditorScene extends GameScene {

    private int caseW;
    private int caseH;
    private ListView selectEffects;

    public EditorScene(Parent root) {
        super(root);
    }

    public EditorScene(Parent root, double width, double height) {
        super(root, width, height);
    }

    public EditorScene(Parent root, Paint fill) {
        super(root, fill);
    }

    public EditorScene(Parent root, double width, double height, Paint fill) {
        super(root, width, height, fill);
    }

    public EditorScene(Parent root, double width, double height, boolean depthBuffer) {
        super(root, width, height, depthBuffer);
    }

    public EditorScene(Parent root, double width, double height, boolean depthBuffer, SceneAntialiasing antiAliasing) {
        super(root, width, height, depthBuffer, antiAliasing);
    }

    public void initScene(int width, int height, Pane map){
        caseH = height;
        caseW = width;
        assetLoader = new AssetLoader("/assets/game");
       assets = assetLoader.loadAsset();
        initSquareScreen(map,null,width,height);
    }

    public void setCursor(Cursor cursor){
        Image img;
        System.out.println("tile size : " + tileWidth + " / " + tileHeight);
        switch (cursor){
            case WALL:
                img = new Image("/assets/game/border.jpg");
                setCursor(new ImageCursor(img,img.getWidth()/2,img.getHeight()/2));break;
            case CORRIDOR:
                img = new Image("/assets/game/corridor.jpg");
                setCursor(new ImageCursor(img,img.getWidth()/2,img.getHeight()/2));break;
            case PACGUM:
                img = new Image("/assets/game/pacgum2.jpg");
                setCursor(new ImageCursor(img,img.getWidth()/2,img.getHeight()/2));break;
            default:
                setCursor(new ImageCursor());
                System.out.println("default cursor set");break;
        }
    }
    public void setController(EditorController c){
        for (int i = 0; i < caseH; i++) {
            for (int j = 0; j < caseW; j++) {
                IdRectangle rec = (IdRectangle) cases[i][j];
                cases[i][j].addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        c.clickOnRect(rec);
                    }
                });
                cases[i][j].addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        c.clickOnRect(rec);
                    }
                });
            }
        }
    }

    public void updateScreen(Cell[][] grille){
        for (int i = 0; i < caseH; i++) {
            for (int j = 0; j < caseW; j++) {
                String path = "/assets/game/border.jpg";
                Cell cell = grille[i][j];
                //   System.out.println(grille[i][j]);

                if (cell instanceof Wall){ // WALL
                    path = cell.getSkin();
                }else{ // Corridor
                    Corridor corri = (Corridor) cell;
                    path = corri.getSkin();
                    if (corri.getConsumable() != null){
                        if (corri.getConsumable() instanceof NoEffectConsumable){
                               path = "/assets/game/pacgum2.jpg";
                        }else{

                            path = corri.getConsumable().getSkin();
                        }
                    }
                    boolean ghosted = false;
                    boolean invu = false;
                    for (Character c:corri.getPersos()){
                        if (c instanceof Ennemy && !invu){
                            ghosted = true;
                            path = c.getSkin();
                        }
                        if ((c instanceof PacMan) && (!ghosted || c.isInvulnerability()) ){
                            path = c.getSkin();
                            invu = true;
                        }
                    }
                }
               // System.out.println("paint :  "+assets.get(path));
                cases[i][j].setFill(assets.get(path));
            }
        }
    }

    public void initListView(Map<String,Effect> effects, ListView selectEffects) {
        this.selectEffects = selectEffects;
        selectEffects.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        for (Map.Entry<String, Effect> entry : effects.entrySet()) {
           this.selectEffects.getItems().add(entry.getKey());
        }

    }

    public void updateList(Map<String,Effect> effects) {
        selectEffects.getItems().clear();
        for (Map.Entry<String, Effect> entry : effects.entrySet()) {
            this.selectEffects.getItems().add(entry.getKey());
        }
    }
}
