package edu.lawrence.puzzle;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class PuzzlePane extends Pane {

    private ArrayList<PuzzlePiece> pieces;
    PuzzlePiece dragging;
    double lastX, lastY;

    public PuzzlePane() {
        pieces = new ArrayList<PuzzlePiece>();
        readFromFile();

        this.setOnMousePressed(e -> startDrag(e));
        this.setOnMouseDragged(e -> dragPiece(e));
        this.setOnMouseReleased(e -> endDrag(e));
        dragging = null;
    }

    public void readFromFile() {
        pieces.clear();
        this.getChildren().clear();
        /**
         * Image downloaded from
         * https://www.flickr.com/photos/84220074@N07/50409613346 "Lawrence
         * University" by hongwuphoto is licensed with CC BY-NC-SA 2.0. To view
         * a copy of this license, visit
         * https://creativecommons.org/licenses/by-nc-sa/2.0/ *
         */
        Image i = new Image(getClass().getResourceAsStream("/images/picture.jpg"));
        try {
            Scanner input = new Scanner(new File("pieces.txt"));
            while (input.hasNextInt()) {
                int origX = input.nextInt();
                int origY = input.nextInt();
                int x = input.nextInt();
                int y = input.nextInt();
                PuzzlePiece p = new PuzzlePiece(origX, origY, i);
                p.moveBy(x - origX, y - origY);
                pieces.add(p);
                this.getChildren().add(p.getShape());
            }

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public void save() {
        try {
            PrintWriter writer = new PrintWriter(new File("pieces.txt"));
            for (PuzzlePiece p : pieces) {
                writer.println(p.getOrigCol() * 100 + " " + p.getOrigRow() * 100
                        + " " + p.getCol() * 100 + " " + p.getRow() * 100);
            }
            writer.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public void scramble() {
        Collections.shuffle(pieces);
        Iterator<PuzzlePiece> iter = pieces.iterator();
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 8; col++) {
                int leftX = col * 100;
                int topY = row * 100;
                PuzzlePiece p = iter.next();
                p.snapToGrid(leftX, topY);
            }
        }
    }

    public void startDrag(MouseEvent evt) {
        double x = evt.getX();
        double y = evt.getY();
        for (PuzzlePiece p : pieces) {
            if (p.containsLocation(x, y)) {
                dragging = p;
                break;
            }
        }
        lastX = x;
        lastY = y;
    }

    public void dragPiece(MouseEvent evt) {
        if (dragging != null) {
            double x = evt.getX();
            double y = evt.getY();
            dragging.moveBy(x - lastX, y - lastY);
            lastX = x;
            lastY = y;
        }
    }

    public void endDrag(MouseEvent evt) {
        if (dragging != null) {
            double x = evt.getX();
            double y = evt.getY();
            dragging.snapToGrid(x, y);
            dragging = null;
        }
    }
}
