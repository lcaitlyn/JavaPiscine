package edu.school21.ChaseLogic;

public interface GameObjectInterface extends VectorInt {
    int getX();
    int getY();
    void setCoords(int x, int y);
    public char getObjectChar();
}
