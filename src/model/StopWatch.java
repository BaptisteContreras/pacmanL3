package model;

import java.util.Timer;
import java.util.TimerTask;

public class StopWatch extends Thread{

    protected int seconds;

    protected boolean running;

    public StopWatch() {
        this.seconds = 0;
    }

    public int getSeconds() {
        return seconds;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    @Override
    public void interrupt() {
        this.setRunning(false);
        super.interrupt();
    }

    @Override
    public void run() {
        this.running =true;

        while (this.running){
            this.seconds++;
            try {
                sleep(1000);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }

    @Override
    public String toString() {
        return "StopWatch{" + "seconds=" + seconds + ", running=" + running + '}';
    }

    public String getHeureFormat (){
        if (this.seconds != 0)
            return ((this.seconds/60)+ ":" + (this.seconds%60));
        else
            return (this.seconds + "");
    }
}
