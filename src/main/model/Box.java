package main.model;

import java.awt.*;

public class Box extends CollisionObject implements Movable{

    public Box(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.ORANGE);
        graphics.drawRect(x - width / 2, y - height / 2, width, height);
        graphics.drawLine(x - width / 2, y - height / 2, x + width / 2, y + height / 2);
        graphics.drawLine(x - width / 2, y + height / 2, x + width / 2, y - width / 2);
    }

    @Override
    public void move(int x, int y) {
        this.x += x;
        this.y += y;
    }
}
