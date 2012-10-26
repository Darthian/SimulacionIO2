package Negocio;

public class Obstruction {

    private int posX;
    private int posY;

    //Constructor para recuperar datos guardados
    public Obstruction(int newPosX, int newPosY) {
        posX = newPosX;
        posY = newPosY;
    }

    //Return X coordinate
    public int getPosX() {
        return posX;
    }

    //Return Y coordinate
    public int getPosY() {
        return posY;
    }
}
