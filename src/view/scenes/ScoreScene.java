package view.scenes;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.paint.Paint;

public class ScoreScene extends Scene
{
    public ScoreScene(Parent root) {
        super(root);
    }

    public ScoreScene(Parent root, double width, double height) {
        super(root, width, height);
        //getStylesheets().add("assets/css/score.css");
    }

    public ScoreScene(Parent root, Paint fill) {
        super(root, fill);
    }

    public ScoreScene(Parent root, double width, double height, Paint fill) {
        super(root, width, height, fill);
    }

    public ScoreScene(Parent root, double width, double height, boolean depthBuffer) {
        super(root, width, height, depthBuffer);
    }

    public ScoreScene(Parent root, double width, double height, boolean depthBuffer, SceneAntialiasing antiAliasing) {
        super(root, width, height, depthBuffer, antiAliasing);
    }
}
