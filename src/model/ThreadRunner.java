package model;

public class ThreadRunner implements Runnable{

    private JeuModel model;

    public ThreadRunner(JeuModel model) {
        this.model = model;
    }

    @Override
    public void run() {
        model.mainTurn();
    }
}
