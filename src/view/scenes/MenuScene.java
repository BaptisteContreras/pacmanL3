package view.scenes;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.paint.Paint;

public class MenuScene extends Scene {


    public MenuScene(Parent root) {
        super(root);
    }

    public MenuScene(Parent root, double width, double height) {
        super(root, width, height);
    }

    public MenuScene(Parent root, Paint fill) {
        super(root, fill);
    }

    public MenuScene(Parent root, double width, double height, Paint fill) {
        super(root, width, height, fill);
    }

    public MenuScene(Parent root, double width, double height, boolean depthBuffer) {
        super(root, width, height, depthBuffer);
    }

    public MenuScene(Parent root, double width, double height, boolean depthBuffer, SceneAntialiasing antiAliasing) {
        super(root, width, height, depthBuffer, antiAliasing);
    }


    public void test(){
        System.out.println("tested");
    }
}
