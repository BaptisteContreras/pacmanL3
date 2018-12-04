package view.stages;

import controller.editor.DialogueEdit1Controller;
import javafx.stage.Stage;
import view.components.CustomReturn;

public class EditDialogEffect extends Stage {

    public CustomReturn showAndReturn(DialogueEdit1Controller controll) {
        super.showAndWait();
        return controll.getReturn();
    }

}
