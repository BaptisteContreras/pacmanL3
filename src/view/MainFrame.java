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
    public final static int WIDTH = 645;
    public final static int HEIGHT = 312;

    @Override
    public void start(Stage primaryStage) throws Exception{

        primaryStage.initStyle(StageStyle.UNDECORATED);
        currentStage = primaryStage;
        primaryStage.setTitle("Pac-Man");
        Parent root = FXMLLoader.load(getClass().getResource("fxml/menu.fxml")); // Chargement de la vue du menu et de son controlleur
        MenuScene menuScene = new MenuScene(root, WIDTH, HEIGHT);
        primaryStage.setScene(menuScene); // définie la scene principale
        primaryStage.show(); // Affiche la fenêtre

        //  primaryStage.setMaximized(true);

    }



}
