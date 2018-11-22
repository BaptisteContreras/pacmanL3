package model.map;

import model.Grid2dBuilder;

public class Map2D extends Map {

    public Map2D(int width, int height) {
        super(width, height);
        gridBuilder = new Grid2dBuilder(width,height);
    }

    public Map2D() {
        super(1, 1);
        gridBuilder = new Grid2dBuilder(1,1);
    }
}
