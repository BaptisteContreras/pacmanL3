package view.scenes;

import javafx.beans.Observable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import model.JeuModel;

public class PlayScene extends Scene {

    private JeuModel model;
    private Pane rootGame;
    private Pane rootScore;
    private Double ScreenRatio;
    private Double tileWidth;
    private Double tileHeight;
    private Double adaptedWidth;
    private Double adaptedHeight;
    private Rectangle[][] cases;
    private Double startX;
    private Double startY;

    public PlayScene(Parent root) {
        super(root);
    }

    public PlayScene(Parent root, double width, double height) {
        super(root, width, height);
    }

    public PlayScene(Parent root, Paint fill) {
        super(root, fill);
    }

    public PlayScene(Parent root, double width, double height, Paint fill) {
        super(root, width, height, fill);
    }

    public PlayScene(Parent root, double width, double height, boolean depthBuffer) {
        super(root, width, height, depthBuffer);
    }

    public PlayScene(Parent root, double width, double height, boolean depthBuffer, SceneAntialiasing antiAliasing) {
        super(root, width, height, depthBuffer, antiAliasing);
    }

    public JeuModel getModel() {
        return model;
    }

    public void setModel(JeuModel model) {
        this.model = model;
    }

    public void test(){
        AnchorPane root = (AnchorPane) getRoot();
        for (Node a : root.getChildren()){
           // System.out.println(a);
        }
        System.out.println("debug");
        System.out.println(rootGame);
        System.out.println(rootScore);

    }

    public Pane getRootGame() {
        return rootGame;
    }

    public void initScene(Pane game, Pane score, JeuModel model, int width, int height){
        // TODO affichage pour map rectangle
        cases = new Rectangle[height][width];
        setModel(model);
        setRootGame(game);
        setRootScore(score);
       // getRoot().setStyle("-fx-background-color: #" + "000000");
        if (width == height)
            initSquareScreen(game,score,width,height);
        else
            initRectScreen(game,score,width,height);

     //   Rectangle rect = new Rectangle(100,50);
       // Image img = new Image("/assets/game/pacman.png");
      //  rect.setFill(new ImagePattern(img));
        //game.getChildren().add(rect);

    }

    public void setRootGame(Pane rootGame) {
        this.rootGame = rootGame;
    }

    public Pane getRootScore() {
        return rootScore;
    }

    public void setRootScore(Pane rootScore) {
        this.rootScore = rootScore;
    }

    private void initSquareScreen(Pane game, Pane score, int width, int height){

        ScreenRatio = getWidth() >= getHeight() ? getWidth()/getHeight() : getHeight()/getWidth();
        adaptedWidth = getWidth() >= getHeight() ? getWidth()/ScreenRatio : getWidth();
        adaptedHeight = getHeight() >= getWidth() ? getHeight()/ScreenRatio : getHeight();
        tileWidth = adaptedWidth/width;
        tileHeight = adaptedHeight/height;
        startX = (getWidth()-(adaptedWidth))/2; // pour centrer

        Image img = new Image("/assets/game/border.jpg");

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Rectangle tmpRect = new Rectangle(tileWidth,tileHeight);
                tmpRect.setLayoutX(startX+(tileWidth*j));
                tmpRect.setLayoutY((tileHeight*i));
                tmpRect.setFill(new ImagePattern(img));
                cases[i][j] = tmpRect;
                game.getChildren().add(tmpRect);
            }
        }

    }

    private void initRectScreen(Pane game, Pane score, int width, int height){
        System.out.println("rect");
        int caseRatio = width >= height ?  width/height : height/width;
        ScreenRatio = 1.;
        adaptedWidth = getWidth()-((getWidth()*10)/100);
        adaptedHeight = getHeight();
        tileWidth = width >= height ? (adaptedWidth/width)/2 : (adaptedWidth/width)/2;
        tileHeight = height >= width ? adaptedHeight/height : (adaptedHeight/height)/caseRatio;
        startX = (getWidth()-(adaptedWidth)); // pour centrer
        startY = (getHeight()-(adaptedHeight)); // pour centrer

        Image img = new Image("/assets/game/border.jpg");

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Rectangle tmpRect = new Rectangle(tileWidth,tileHeight);
                tmpRect.setLayoutX(startX+(tileWidth*j));
                tmpRect.setLayoutY(startY+(tileHeight*i));
                tmpRect.setFill(new ImagePattern(img));
                cases[i][j] = tmpRect;
                game.getChildren().add(tmpRect);
            }
        }
    }
}
