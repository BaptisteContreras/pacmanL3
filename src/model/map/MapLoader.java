package model.map;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MapLoader {


    private String base;
    private File mapDir;
    private File[] files;
    private String mapExtension;
    private String prefixe;


    public MapLoader(String base) {
        this.base = base;
        prefixe = "./src/";
        mapDir = new File(prefixe+base);
        files = mapDir.listFiles();
        mapExtension = ".map";
        if (mapDir.isDirectory())
            System.out.println("MapLoader up and ready in : " + base);
        else
            System.out.println("Maploader not ready, cannot load path : " + base);
    }

    public String getBase() {
        return base;
    }

    public List<String> getMapList(){
        files = mapDir.listFiles();
        List<String> names = new ArrayList<>();
        for (int i = 0; i < files.length; i++) {
            if (files[i].isFile()) {
                names.add(files[i].getName());
            }
        }
        return names;
    }


    public boolean isMapExist(String name){
        File testExist = new File(prefixe+base+"/"+(name+mapExtension));
        return testExist.exists();
    }

    public boolean saveMap(Map map, String name) throws IOException {
        FileOutputStream fout = new FileOutputStream(prefixe+base+"/"+name+mapExtension);
        ObjectOutputStream oos = new ObjectOutputStream(fout);
        oos.writeObject(map);
        oos.close();
        System.out.println("Saved");
        return true;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Map loadMap(String name) throws IOException {
        ObjectInputStream ois = null;
        Map mapLoaded = null;
        try {
            FileInputStream streamIn = new FileInputStream(prefixe+base+"/"+name);
            ois = new ObjectInputStream(streamIn);
            mapLoaded = (Map) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // on ferme le stream si il a été ouvert
            if(ois != null){
                ois .close();
            }
        }
        return mapLoaded;
    }
}
