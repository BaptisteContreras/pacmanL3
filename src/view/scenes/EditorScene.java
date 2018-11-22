package view.scenes;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.paint.Paint;

public class EditorScene extends Scene {
    public EditorScene(Parent root) {
        super(root);
    }

    public EditorScene(Parent root, double width, double height) {
        super(root, width, height);
    }

    public EditorScene(Parent root, Paint fill) {
        super(root, fill);
    }

    public EditorScene(Parent root, double width, double height, Paint fill) {
        super(root, width, height, fill);
    }

    public EditorScene(Parent root, double width, double height, boolean depthBuffer) {
        super(root, width, height, depthBuffer);
    }

    public EditorScene(Parent root, double width, double height, boolean depthBuffer, SceneAntialiasing antiAliasing) {
        super(root, width, height, depthBuffer, antiAliasing);
    }
}
