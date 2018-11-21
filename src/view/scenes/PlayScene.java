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
    private Text timeLabel;
    private Text titleLabel;
    private Text score1Label;
    private Text score2Label;

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

                Platform.runLater(() ->{ updateScreen(model.getCells(),game,score,width,height);updateInfos(players,timeLabel,score1Label,score2Label);} );

               // System.out.println("up to date");
            }
        });

     //   Rectangle rect = new Rectangle(100,50);
       // Image img = new Image("/assets/game/pacman.png");
      //  rect.setFill(new ImagePattern(img));
        //game.getChildren().add(rect);

    }

    public void initInfos(List<HumanPlayer> players){
        timeLabel = new Text("Time : ");
        timeLabel.setLayoutX(0);
        timeLabel.setLayoutY(150);
        timeLabel.getStyleClass().add("infos");
        timeLabel.setId("time");
        titleLabel = new Text("Scores ");
        titleLabel.setLayoutX(25);
        titleLabel.setLayoutY(300);
        titleLabel.getStyleClass().add("infos");
        titleLabel.setId("scoretitle");
        score1Label = new Text(players.get(0).getPseudo()+" : 0");
        score1Label.setLayoutX(0);
        score1Label.setLayoutY(400);
        score1Label.getStyleClass().add("infos");
        score1Label.getStyleClass().add("score");
        if (players.size() > 1){
            score2Label = new Text(players.get(1).getPseudo()+" : 0 ");
            score2Label.setLayoutX(0);
            score2Label.setLayoutY(450);
            score2Label.getStyleClass().add("infos");
            score2Label.getStyleClass().add("score");
            rootScore.getChildren().add(score2Label);
        }
        rootScore.getChildren().add(timeLabel);
        rootScore.getChildren().add(titleLabel);
        rootScore.getChildren().add(score1Label);
    }

    public void updateInfos(List<HumanPlayer> players, Text time, Text score1, Text score2){
        HumanPlayer p1 = players.get(0);
        score1.setText(p1.getPseudo()+" : "+p1.getScore());
        if (players.size()>1){
            HumanPlayer p2 = players.get(1);
            score2.setText(p2.getPseudo()+" : "+p2.getScore());
        }
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
