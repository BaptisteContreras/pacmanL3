package model;

import controller.Controller;
import controller.PlayController;

public class ThreadRunner implements Runnable{

    private JeuModel model;
    private Controller ctrl;

    public ThreadRunner(JeuModel model, Controller ctrl) {
        this.model = model;
        this.ctrl = ctrl;
    }

    @Override
    public void run() {
        model.mainTurn();
        ((PlayController)ctrl).endGame();
    }
}
