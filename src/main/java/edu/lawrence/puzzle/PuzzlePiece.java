package edu.lawrence.puzzle;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class PuzzlePiece {
    Image image;
    Rectangle rect;
    private double x,y;
    
    public PuzzlePiece(int leftX,int topY,Image i) {
        x = leftX;
        y = topY;
        rect = new Rectangle(x,y,100,100);
        image = i;
        resetImage();
    }
    
    public Shape getShape() { return rect; }
    
    public boolean containsLocation(double x,double y) {
        return rect.contains(x,y);
    }
    
    public void moveBy(double deltaX,double deltaY) {
        rect.setX(rect.getX() + deltaX);
        rect.setY(rect.getY()+deltaY);
        resetImage();
    }
    
    public void snapToGrid(double x,double y) {
        rect.setX(Math.floor(x/100)*100);
        rect.setY(Math.floor(y/100)*100);
        resetImage();
    }
    
    private void resetImage() {
        ImagePattern ip = new ImagePattern(image,rect.getX()-x,rect.getY()-y,800,600,false);
        rect.setFill(ip);
    }
    
    public int getOrigRow() { return (int) Math.floor(y/100); }
    public int getOrigCol() { return (int) Math.floor(x/100); }
    public int getRow() { return (int) Math.floor(rect.getY()/100); }
    public int getCol() { return (int) Math.floor(rect.getX()/100); }
}
