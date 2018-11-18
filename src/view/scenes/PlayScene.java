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
        cases = new Rectangle[height][width];
        System.out.println(score);
        setModel(model);
        setRootGame(game);
        setRootScore(score);
        System.out.println("Game pane size = " + game.getWidth() + " / " + game.getHeight());
        ScreenRatio = game.getWidth() >= game.getHeight() ? game.getWidth()/game.getHeight() : game.getHeight()/game.getWidth();
        System.out.println(ScreenRatio);
        adaptedWidth = game.getWidth() >= game.getHeight() ? game.getWidth()/ScreenRatio : game.getWidth();
        adaptedHeight = game.getHeight() >= game.getWidth() ? game.getHeight()/ScreenRatio : game.getHeight();
        tileWidth = adaptedWidth/width;
        tileHeight = adaptedHeight/height;
        //startX = ((game.getWidth() - adaptedWidth)); // pour centrer
        startX = ((getWidth()-game.getWidth())/2) + ((game.getWidth() - adaptedWidth)/2); // pour centrer
        //startY = (game.getHeight() - adaptedHeight)/2;
        startY = ((getHeight()-game.getHeight())/2)+(game.getHeight() - adaptedHeight)/2;
        System.out.println(startX + " :: " + startY);
        System.out.println(adaptedWidth + " == " + adaptedHeight);
        System.out.println(tileWidth + " :: " + tileHeight);
        System.out.println("---------------");
        Image img = new Image("/assets/game/border.jpg");
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Rectangle tmpRect = new Rectangle(tileWidth,tileHeight);
                System.out.println(startX+(tileWidth*j)+" @@ " + (startY+(tileHeight*i)));
                tmpRect.setLayoutX(startX+(tileWidth*j));
                tmpRect.setLayoutY(startY+(tileHeight*i));
                tmpRect.setFill(new ImagePattern(img));
                cases[i][j] = tmpRect;
                game.getChildren().add(tmpRect);
            }
        }
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
}
