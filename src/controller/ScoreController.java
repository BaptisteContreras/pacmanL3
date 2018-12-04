package controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Callback;
import model.Score;
import model.ScoreManager;
import model.entities.players.HumanPlayer;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ScoreController extends Controller{

    @FXML
    private Button btnBack;

    @FXML
    private TableView<HumanPlayer> scoreList;

    @FXML
    private TableColumn<HumanPlayer, String> scoreColumn;

    @FXML
    private  TableColumn<HumanPlayer, String> pseudoColumn;

    @FXML
    private TableColumn<HumanPlayer, String> lineColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnBack.setOnAction((evt) -> back(null));
        scoreColumn.setCellValueFactory(new customScoreFactory());
        pseudoColumn.setCellValueFactory(new CustomPseudoFactory());
        lineColumn.setCellValueFactory(new CustomLineFactory());
        //charge les donnees des scores dans le tableView
        this.scoreList.setItems(loadScoreList());

    }

    protected ObservableList<HumanPlayer> loadScoreList(){
        ScoreManager sm = new ScoreManager();
        ObservableList<HumanPlayer> playersToReturn = FXCollections.observableArrayList();
        ArrayList<HumanPlayer> humanPlayers = sm.readAllScores();

        int size;
        if (humanPlayers.size()<10)
            size = humanPlayers.size();
        else
            size = 10;
        for (int i=0; i<size ; i++){
            playersToReturn.add(humanPlayers.get(i));
        }
        return playersToReturn;
    }


    private class customScoreFactory implements Callback<TableColumn.CellDataFeatures<HumanPlayer, String>, ObservableValue<String>> {
        @Override
        public ObservableValue<String> call(TableColumn.CellDataFeatures<HumanPlayer, String> param) {
            return new SimpleStringProperty(new String(param.getValue().getScore() + ""));
        }
    }

    private class CustomPseudoFactory implements Callback<TableColumn.CellDataFeatures<HumanPlayer, String>, ObservableValue<String>> {
        @Override
        public ObservableValue<String> call(TableColumn.CellDataFeatures<HumanPlayer, String> param) {
            return new SimpleStringProperty(param.getValue().getPseudo().toString());
        }
    }

    private class CustomLineFactory implements Callback<TableColumn.CellDataFeatures<HumanPlayer, String>, ObservableValue<String>> {
        @Override
        public ObservableValue<String> call(TableColumn.CellDataFeatures<HumanPlayer, String> param) {
            return new SimpleStringProperty("-----------");
        }
    }
}



