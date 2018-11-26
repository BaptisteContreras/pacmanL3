package view.scenes;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import view.AssetLoader;
import view.components.IdRectangle;

import java.util.Map;

public abstract class GameScene extends Scene {


    protected double ScreenRatio;
    protected double adaptedWidth;
    protected double adaptedHeight;
    protected double tileWidth;
    protected double tileHeight;
    protected double startX;
    protected Rectangle[][] cases;
    protected double startY;
    protected Map<String,ImagePattern> assets;
    protected AssetLoader assetLoader;

    protected void initSquareScreen(Pane game, Pane score, int width, int height){
        cases = new IdRectangle[height][width];
        ScreenRatio = getWidth() >= getHeight() ? getWidth()/getHeight() : getHeight()/getWidth();
        adaptedWidth = getWidth() >= getHeight() ? getWidth()/ScreenRatio : getWidth();
        adaptedHeight = getHeight() >= getWidth() ? getHeight()/ScreenRatio : getHeight();
        tileWidth = adaptedWidth/width;
        tileHeight = adaptedHeight/height;
        startX = (getWidth()-(adaptedWidth))/2; // pour centrer

        Image img = new Image("/assets/game/border.jpg");

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                IdRectangle tmpRect = new IdRectangle(tileWidth,tileHeight,(j+(height*i)),height);
                tmpRect.setLayoutX(startX+(tileWidth*j));
                tmpRect.setLayoutY((tileHeight*i));
                tmpRect.setFill(new ImagePattern(img));
                cases[i][j] = tmpRect;
                game.getChildren().add(tmpRect);
            }
        }

    }

    protected void initRectScreen(Pane game, Pane score, int width, int height){
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
    public GameScene(Parent root) {
        super(root);
    }

    public GameScene(Parent root, double width, double height) {
        super(root, width, height);
    }

    public GameScene(Parent root, Paint fill) {
        super(root, fill);
    }

    public GameScene(Parent root, double width, double height, Paint fill) {
        super(root, width, height, fill);
    }

    public GameScene(Parent root, double width, double height, boolean depthBuffer) {
        super(root, width, height, depthBuffer);
    }

    public GameScene(Parent root, double width, double height, boolean depthBuffer, SceneAntialiasing antiAliasing) {
        super(root, width, height, depthBuffer, antiAliasing);
    }
}
