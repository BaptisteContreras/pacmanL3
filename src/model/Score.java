package model;

import java.io.Serializable;

public class Score implements Serializable {

    private int valeur;


    public Score(int valeur) {
        this.valeur = valeur;
    }

    public Score() {
        this(0);
    }

    public void up(int toup){
        valeur += toup;
    }

    public int getScore(){
        return valeur;
    }

    public void reset(){
        valeur = 0;
    }
}
