package main.controller;

import main.model.Model;
import main.view.View;

public class Controller {
    private View view;
    private Model model;

    public Controller() {
        view = new View(this);
        model = new Model();
        view.init();
    }

    public static void main(String[] args) {
        Controller controller = new Controller();

    }
}
