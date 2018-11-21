package view.scenes;

import javafx.application.Platform;
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
import javafx.scene.text.Text;
import model.JeuModel;
import model.entities.cells.Cell;
import model.entities.cells.Corridor;
import model.entities.cells.Wall;
import model.entities.characters.Character;
import model.entities.characters.Ennemy;
import model.entities.characters.Ghost;
import model.entities.characters.PacMan;
import model.entities.players.HumanPlayer;
import view.AssetLoader;

import java.util.List;
import java.util.Map;
import java.util.Observer;

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
    private Map<String,ImagePattern> assets;
    private AssetLoader assetLoader;

    public PlayScene(Parent root) {
        super(root);
    }

    public PlayScene(Parent root, double width, double height) {
        super(root, width, height);
        getStylesheets().add("assets/css/game.css");
        System.out.println("Stylesheets for the game screen have been loaded with success !");
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

    public Pane getRootGame() {
        return rootGame;
    }

    public void initScene(Pane game, Pane score, JeuModel model, int width, int height, List<HumanPlayer> players){
        // TODO affichage pour map rectangle
        assetLoader = new AssetLoader("/assets/game");
        assets = assetLoader.loadAsset();
        cases = new Rectangle[height][width];
        setModel(model);
        setRootGame(game);
        setRootScore(score);
       // getRoot().setStyle("-fx-background-color: #" + "000000");
        if (width == height)
            initSquareScreen(game,score,width,height);
        else
            initRectScreen(game,score,width,height);

        updateScreen(model.getCells(),game,score,width,height);
        initInfos(players);
        model.addObserver(new Observer() {
            @Override
            public void update(java.util.Observable o, Object arg) {

                Platform.runLater(() ->{ updateScreen(model.getCells(),game,score,width,height);} );

               // System.out.println("up to date");
            }
        });

     //   Rectangle rect = new Rectangle(100,50);
       // Image img = new Image("/assets/game/pacman.png");
      //  rect.setFill(new ImagePattern(img));
        //game.getChildren().add(rect);

    }

    public void initInfos(List<HumanPlayer> players){
        Text time = new Text("0:00");
        time.setLayoutX(0);
        time.setLayoutY(50);
        time.getStyleClass().add("infos");
        Text score = new Text("Scores :");
        score.setLayoutX(0);
        score.setLayoutY(80);
        score.getStyleClass().add("infos");
        Text score1 = new Text(players.get(0).getPseudo()+" : 4 ");
        score1.setLayoutX(0);
        score1.setLayoutY(110);
        score1.getStyleClass().add("infos");
        if (players.size() > 1){
            Text score2 = new Text(players.get(1).getPseudo()+" : 0 ");
            score2.setLayoutX(0);
            score2.setLayoutY(130);
            score2.getStyleClass().add("infos");
            rootScore.getChildren().add(score2);
        }
        rootScore.getChildren().add(time);
        rootScore.getChildren().add(score);
        rootScore.getChildren().add(score1);
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

    public void updateScreen(Cell[][] grille,Pane game, Pane score, int width, int height){
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                String path = "/assets/game/border.jpg";
                Cell cell = grille[i][j];
             //   System.out.println(grille[i][j]);

                if (cell instanceof Wall){ // WALL
                    path = cell.getSkin();
                }else{ // Corridor
                    Corridor corri = (Corridor) cell;
                    path = corri.getSkin();
                    if (corri.getConsumable() != null){
                        path = corri.getConsumable().getSkin();
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
                // Create image
               // Image img = new Image(path);
              //  cases[i][j].setFill(new ImagePattern(img));
                cases[i][j].setFill(assets.get(path));
            }
        }
    }
}
