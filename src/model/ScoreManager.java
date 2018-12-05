package model;

import model.entities.characters.PacMan;
import model.entities.players.HumanPlayer;
import model.entities.players.Player;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class ScoreManager {

    protected File scoreFile;

    public ScoreManager() {
        this.scoreFile = new File("src/assets/score/scores.txt");
        /*if (this.scoreFile.exists())
            System.out.println("le fichier score.css existe");*/
    }

    public void writeOneScore(HumanPlayer player){
        ArrayList<HumanPlayer> allScores = this.readAllScores();
        allScores.add(player);
        allScores = this.sortAllScore(allScores);
        FileWriter fw;

        try{
            fw = new FileWriter(this.scoreFile.getPath(), false);
            for (HumanPlayer hPlayer : allScores){
                String str = this.castPlayerToString(hPlayer) + "\n";
                fw.write(str);
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public ArrayList<HumanPlayer> readAllScores(){
        Scanner reader ;
        ArrayList<HumanPlayer> allScores = new ArrayList<>();

        try {
            reader = new Scanner(this.scoreFile);
            String str = null;
            while (reader.hasNextLine()){
                str = reader.nextLine();
                HumanPlayer playerTmp = this.castStringToPlayer(str);
                if (playerTmp != null)
                    allScores.add(playerTmp);
            }
            reader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return allScores;
    }

    private HumanPlayer castStringToPlayer(String str){
        if (str.isEmpty())
            return null;
        String values[] = str.split(";");
        HumanPlayer playerTmp = new HumanPlayer(null, values[0]);
        playerTmp.setScore(new Score(Integer.parseInt(values[1])));
        return playerTmp;
    }

    /**
     * on peut la supprimer si on prends des SortedArray
     * @param allScores
     * @return
     */
    private ArrayList<HumanPlayer> sortAllScore(ArrayList<HumanPlayer> allScores){
        ArrayList<HumanPlayer> allScoreToReturn = allScores;
        Collections.sort(allScoreToReturn, new Comparator<HumanPlayer>() {
            @Override
            public int compare(HumanPlayer o1, HumanPlayer o2) {
                if (o1.getScore()<o2.getScore())
                    return 1;
                if(o1.getScore()==o2.getScore())
                    return 0;
                else return -1;
            }
        });
        return allScoreToReturn;

    }

    public String castPlayerToString (HumanPlayer player){
        return (new String(player.getPseudo() + ";" + player.getScore()));
    }
}
