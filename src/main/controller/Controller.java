package main.controller;

import main.model.Direction;
import main.model.GameObjects;
import main.model.Model;
import main.view.View;

public class Controller implements EventListener{
    private View view;
    private Model model;

    public Controller() {
        view = new View(this);
        model = new Model();
        view.init();
        model.restart();
    }

    public static void main(String[] args) {
        new Controller();
    }

    public GameObjects getGameObjects(){
        return model.getGameObjects();
    }

    @Override
    public void move(Direction direction) {

    }

    @Override
    public void restart() {

    }

    @Override
    public void startNextLevel() {

    }

    @Override
    public void levelCompleted(int level) {

    }
}
