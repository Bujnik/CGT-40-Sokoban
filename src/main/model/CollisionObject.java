package main.model;

public abstract class CollisionObject extends GameObject{
    public CollisionObject(int x, int y) {
        super(x, y);
    }

    public boolean isCollision(GameObject gameObject, Direction direction) {
        //Check if coordinates of this object matches gameObject coordinates
        //after this object moves in passed direction
        //Move UP means y coordinate is decreased by Model.BOARD_CELL_SIZE
        switch (direction) {
            case UP:
                return isPositionEqual(this.x, this.y - Model.BOARD_CELL_SIZE, gameObject.getX(), gameObject.getY());
            case DOWN:
                return isPositionEqual(this.x, this.y + Model.BOARD_CELL_SIZE, gameObject.getX(), gameObject.getY());
            case LEFT:
                return isPositionEqual(this.x - Model.BOARD_CELL_SIZE, this.y, gameObject.getX(), gameObject.getY());
            case RIGHT:
                return isPositionEqual(this.x + Model.BOARD_CELL_SIZE, this.y, gameObject.getX(), gameObject.getY());
        }
        return false;
    }

    protected boolean isPositionEqual(int x, int y, int x2, int y2) {
        return x == x2 && y == y2;
    }


}
