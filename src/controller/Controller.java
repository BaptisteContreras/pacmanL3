package controller;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.MainFrame;
import view.scenes.MenuScene;

import java.io.IOException;

public abstract class Controller implements Initializable {

    public void quit(Stage s){
        if (s == null)
            s = MainFrame.currentStage;
        System.out.println("Quit !");
        s.close();
    }


    public void back(Stage s){
        if (s == null)
            s = MainFrame.currentStage;
        if (s.getScene() instanceof MenuScene)
            return;
        System.out.println("back to menu");
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../view/fxml/menu.fxml")); // Dégueulasse
            root.setStyle("-fx-background-color: #" + "000000");
        } catch (IOException e) {
            e.printStackTrace();
        }
        MenuScene ps = new MenuScene(root,MainFrame.currentStage.getWidth(),MainFrame.currentStage.getHeight());
        MainFrame.currentStage.setScene(ps);
    }
}
