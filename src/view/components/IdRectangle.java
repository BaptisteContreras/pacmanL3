package view.components;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class IdRectangle extends Rectangle {

    private int rectId;
    private int rectW;


    public IdRectangle(int rectId, int rectW) {
        this.rectId = rectId;
        this.rectW = rectW;
    }

    public IdRectangle(double width, double height, int rectId, int rectW) {
        super(width, height);
        this.rectId = rectId;
        this.rectW = rectW;
    }

    public IdRectangle(double width, double height, Paint fill, int rectId, int rectW) {
        super(width, height, fill);
        this.rectId = rectId;
        this.rectW = rectW;
    }

    public IdRectangle(double x, double y, double width, double height, int rectId, int rectW) {
        super(x, y, width, height);
        this.rectId = rectId;
        this.rectW = rectW;
    }

    public int getRectId() {
        return rectId;
    }

    public void setRectId(int rectId) {
        this.rectId = rectId;
    }

    public int getI(){
        return rectId/rectW;
    }

    public int getJ(){
        return rectId%rectW;
    }
}
