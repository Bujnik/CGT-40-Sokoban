package main.view;

import main.controller.EventListener;
import main.model.GameObject;
import main.model.GameObjects;

import javax.swing.*;
import java.awt.*;
import java.util.Set;

public class Board extends JPanel {
    private View view;
    private EventListener eventListener;

    public Board(View view) {
        this.view = view;
    }

    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    public void paint(Graphics g){
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        GameObjects gameObjects = view.getGameObjects();
        Set<GameObject> objectSet = gameObjects.getAll();
        for (GameObject object : objectSet) {
            object.draw(g);
        }
    }

}
