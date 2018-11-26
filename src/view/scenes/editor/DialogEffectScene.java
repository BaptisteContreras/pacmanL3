package view.scenes.editor;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.control.ListView;
import javafx.scene.paint.Paint;
import model.effects.Effect;

import java.util.Map;

public class DialogEffectScene extends Scene {

    private ListView lview;

    public DialogEffectScene(Parent root) {
        super(root);
    }

    public DialogEffectScene(Parent root, double width, double height) {
        super(root, width, height);
    }

    public DialogEffectScene(Parent root, Paint fill) {
        super(root, fill);
    }

    public DialogEffectScene(Parent root, double width, double height, Paint fill) {
        super(root, width, height, fill);
    }

    public DialogEffectScene(Parent root, double width, double height, boolean depthBuffer) {
        super(root, width, height, depthBuffer);
    }

    public DialogEffectScene(Parent root, double width, double height, boolean depthBuffer, SceneAntialiasing antiAliasing) {
        super(root, width, height, depthBuffer, antiAliasing);
    }

    public void initListe(Map<String,Effect> effectMap, ListView listView){
        lview = listView;
        for (Map.Entry<String,Effect> entry : effectMap.entrySet()){
            listView.getItems().add(entry.getKey());
        }
    }
}
