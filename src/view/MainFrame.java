package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
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
        primaryStage.getIcons().add(new Image("/assets/interface/icon.png"));
        // load the pacman font.
       // System.out.println(Font.loadFont(getClass().getResourceAsStream("../assets/font/pac/pac.ttf"), 16));
        Font.loadFont(getClass().getResourceAsStream("../assets/font/pac/pac.ttf"), 16);
        Parent root = FXMLLoader.load(getClass().getResource("fxml/menu.fxml")); // Chargement de la vue du menu et de son controlleur
        MenuScene menuScene = new MenuScene(root, WIDTH, HEIGHT);
        primaryStage.setScene(menuScene); // définie la scene principale
        primaryStage.show(); // Affiche la fenêtre

        //  primaryStage.setMaximized(true);

    }



}
