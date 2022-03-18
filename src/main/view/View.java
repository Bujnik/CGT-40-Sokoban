package main.view;

import main.controller.Controller;

import javax.swing.*;
import java.awt.*;

public class View extends JFrame {
    private Controller controller;

    public View(Controller controller) throws HeadlessException {
        this.controller = controller;
    }
}
