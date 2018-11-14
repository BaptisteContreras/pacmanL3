package model;

import model.coordonates.Coord;
import model.entities.cells.Cell;
import model.entities.players.Player;

import java.util.HashMap;

public class Grid {

    private Cell[][] grille;
    private HashMap<Player,Coord> playersCoord;
    private int width; // x = j
    private int height; // y = i

    public Grid(int gridWidth, int gridHeight) {
        width = gridWidth;
        height = gridHeight;

        grille = new Cell[height][width];
    }

    public Grid(Cell[][] grid) {
        height = grid.length;
        if (height > 0)
            width = grid[0].length;
        else
            width = 0;

        grille = grid;
    }

    public boolean applyMove(Player player){
        return false;
    }
}
