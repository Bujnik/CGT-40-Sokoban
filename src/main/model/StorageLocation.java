package main.model;

import java.awt.*;

public class StorageLocation extends GameObject{
    public StorageLocation(int x, int y) {
        super(x, y, 2, 2);
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.red);
        graphics.drawOval(x - width / 2, y - width / 2, width, height);
    }

}
