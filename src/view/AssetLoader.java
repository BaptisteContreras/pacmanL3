package view;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class AssetLoader {

    private String base;


    public AssetLoader(String base) {
        this.base = base;
    }

    public Map<String,ImagePattern> loadAsset(){
        Map<String,ImagePattern> assets = new HashMap<>();

        File folder = new File("./src/"+base);
        System.out.println(folder);
        File[] files = folder.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].isFile()) {
                System.out.println("File " + files[i].getName());
                assets.put(base+"/"+files[i].getName(),new ImagePattern(new Image(base+"/"+files[i].getName())));
            }
        }
        /*
        assets.put("/assets/game/pacman.png",new ImagePattern(new Image("/assets/game/pacman.png")));
        assets.put("/assets/game/pacman_left.png",new ImagePattern(new Image("/assets/game/pacman_left.png")));
        assets.put("/assets/game/pacman_down.png",new ImagePattern(new Image("/assets/game/pacman_down.png")));
        assets.put("/assets/game/pacman_top.png",new ImagePattern(new Image("/assets/game/pacman_top.png")));
        assets.put("/assets/game/ghost.png",new ImagePattern(new Image("/assets/game/ghost.png")));
        assets.put("/assets/game/ghost_green.png",new ImagePattern(new Image("/assets/game/ghost_green.png")));
        assets.put("/assets/game/pacgum.png",new ImagePattern(new Image("/assets/game/pacgum.png")));
        assets.put("/assets/game/superpacgum.png",new ImagePattern(new Image("/assets/game/superpacgum.png")));
        assets.put("/assets/game/border.jpg",new ImagePattern(new Image("/assets/game/border.jpg")));
        assets.put("/assets/game/corridor.jpg",new ImagePattern(new Image("/assets/game/corridor.jpg")));
        */
        return assets;
    }
}
