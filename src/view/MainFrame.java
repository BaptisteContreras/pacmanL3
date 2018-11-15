package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import view.scenes.MenuScene;

public class MainFrame extends Application {
    public static Stage currentStage = null;
    public static boolean init = false;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("fxml/menu.fxml"));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        currentStage = primaryStage;

        primaryStage.setTitle("Pac-Man");
        primaryStage.setMaximized(true);
        MenuScene menuScene = new MenuScene(root, 600, 600);
        primaryStage.setScene(menuScene);

        primaryStage.show();
    }



}
