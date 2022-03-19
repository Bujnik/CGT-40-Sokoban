package main.view;

import main.controller.Controller;
import main.controller.EventListener;
import main.model.GameObjects;

import javax.swing.*;
import java.awt.*;

public class View extends JFrame {
    private Controller controller;
    private Board board;

    public View(Controller controller) {
        this.controller = controller;
    }

    public void setEventListener(EventListener eventListener) {
        board.setEventListener(eventListener);
    }

    public void init() {
        board = new Board(this);
        add(board);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setTitle("Sokoban");
        setVisible(true);
    }

    public void update(){
        board.repaint();
    }

    public GameObjects getGameObjects(){
        return controller.getGameObjects();
    }

    public void completed(int level) {
        update();
        JOptionPane.showMessageDialog(this, "Completed level " + level);
        controller.startNextLevel();
    }
}
