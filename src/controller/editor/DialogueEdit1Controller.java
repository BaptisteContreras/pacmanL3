package controller.editor;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.effects.CustomPlayerEffect;
import model.effects.Effect;
import model.effects.PlayerEffect;
import view.components.CustomReturn;
import view.scenes.editor.DialogEffectScene;

import javax.swing.text.html.ListView;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class DialogueEdit1Controller implements Initializable {


    @FXML
    private javafx.scene.control.ListView liste;

    @FXML
    private javafx.scene.control.TextField duree;

    @FXML
    private TextField name;

    private Map<String,Effect> effectMap;

    private CustomReturn toReturn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    public void create(ActionEvent event){
        boolean isvalide = validate();
        if (isvalide){
            ((Stage)((Scene)liste.getScene()).getWindow()).close();
            List<PlayerEffect> tmpLst = new ArrayList<>();
            for (Object selection:liste.getSelectionModel().getSelectedItems()){
                try {
                    tmpLst.add(((PlayerEffect) effectMap.get((String)selection).clone()));
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
            }
            int realDuree = Integer.parseInt(duree.getText());
            for (PlayerEffect p:tmpLst){
                p.setDuree(realDuree);
                p.setTime(realDuree);
            }
            toReturn = new CustomReturn(name.getText(),new CustomPlayerEffect(tmpLst,realDuree));


        }
    }


    private boolean validate(){
        if (name.getText().length() > 0 && name.getText().length() <= 25){
            try {
                int realDuree = Integer.parseInt(duree.getText());
                if (realDuree > 0){
                    if (!liste.getSelectionModel().getSelectedItems().isEmpty() && effectMap.get(name.getText())==null)
                        return true;
                }
            }catch (NumberFormatException ignored){

            }
        }
        return false;
    }

    public CustomReturn getReturn(){
        return toReturn;
    }


    public void initDialogue(Map<String,Effect> effects, DialogEffectScene scene) {
        effectMap = effects;
        scene.initListe(effects,liste);
        liste.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }
    public void test(){

    }
}
