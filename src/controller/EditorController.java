package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import model.map.MapBuilder;

import java.net.URL;
import java.util.ResourceBundle;

public class EditorController extends Controller {

    @FXML
    private Button btnBack;

    @FXML
    private Button write;


    @FXML
    private Button load;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnBack.setOnAction((evt) -> back(null));
    }

    public void prepareEditor(){
        // TODO prepare l'application pour editer une map
        System.out.println("Prepare editor to be used !");
    }
    public void writeTest(){
        System.out.println("write");
        MapBuilder builder = new MapBuilder();
    }
    public void loadTest(){
        System.out.println("load");
    }
}
