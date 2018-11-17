package view.scenes;

import javafx.beans.Observable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Paint;
import model.JeuModel;

public class PlayScene extends Scene {

    private JeuModel model;
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
            System.out.println(a);
        }
        System.out.println("debug");
        GridPane gridpane = new GridPane();
        for (int i = 0; i < 10; i++) {
            RowConstraints row = new RowConstraints(50);
            gridpane.getRowConstraints().add(row);
        }


        root.getChildren().add(gridpane);
    }
}
