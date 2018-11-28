package controller;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.util.Duration;
import view.MainFrame;
import view.scenes.editor.EditorScene;
import view.scenes.game.PlayScene;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MenuController extends Controller {


    @FXML
    private AnchorPane root;

    @FXML
    private Button quit;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            if (!MainFrame.init)
                loadSplashCreen();
        } catch (IOException e) {
            e.printStackTrace();
        }
        quit.setOnAction((evt) -> quit(null));
    }


    private void loadSplashCreen() throws IOException {

        MainFrame.init = true;
        Pane pane = FXMLLoader.load(getClass().getResource(("../view/fxml/splash.fxml")));
        root.getChildren().setAll(pane);


        FadeTransition fadeIn = new FadeTransition(Duration.seconds(3), pane);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        fadeIn.setCycleCount(1);

        FadeTransition fadeOut = new FadeTransition(Duration.seconds(3), pane);
        fadeOut.setFromValue(1);
        fadeOut.setToValue(0);
        fadeOut.setCycleCount(1);

        fadeIn.play();

        fadeIn.setOnFinished((e) -> {
            fadeOut.play();
        });

        fadeOut.setOnFinished((e) -> {
            try {
                AnchorPane parentContent = FXMLLoader.load(getClass().getResource(("../view/fxml/menu.fxml")));
                MainFrame.currentStage.setMaximized(true);
                root.setStyle("-fx-background-color: #" + "000000");
                root.getChildren().setAll(parentContent);
            } catch (IOException ex) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    public void play(){
        System.out.println("play");
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../view/fxml/play.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

       // Scene scene = this.root.getScene();
        //root.translateYProperty().set(scene.getHeight());
        PlayScene ps = new PlayScene(root,MainFrame.currentStage.getWidth(),MainFrame.currentStage.getHeight());
        ps.getRoot().setStyle("-fx-background-color: #" + "000000");

        MainFrame.currentStage.setScene(ps);

        //parentContainer.getChildren().add(root);
        /*
        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(t -> {
            //parentContainer.getChildren().remove(anchorRoot);
        });
        timeline.play();
        */
    }

    public void editor(){
        System.out.println("editor");
        Parent root = null;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/fxml/editor.fxml"));
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        EditorScene ps = new EditorScene(root,MainFrame.currentStage.getWidth(),MainFrame.currentStage.getHeight());
        ps.getRoot().setStyle("-fx-background-color: #" + "AAAAAA");
        ((EditorController)loader.getController()).prepareEditor(); // methode a faire
        ((EditorController)loader.getController()).setEditorScene(ps); // methode a faire

        MainFrame.currentStage.setScene(ps);

    }
}
