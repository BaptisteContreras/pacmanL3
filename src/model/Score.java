package model;

import java.io.Serializable;

public class Score implements Serializable, Comparable<Score> {

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



    @Override
    public String toString() {
        return "Score{" +
                "valeur=" + valeur +
                '}';
    }

    @Override
    public int compareTo(Score o) {
        if (this.valeur < o.getScore())
            return -1;//si this plus petit que o
        if (this.valeur == o.getScore())
            return 0;
        else
            return 1;
    }
}
