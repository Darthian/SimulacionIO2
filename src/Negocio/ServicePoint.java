package Negocio;

public class ServicePoint {

    private int posX;
    private int posY;
    private int packsInStack;

    //Contructor
    public ServicePoint() {
    }

    //Constructor 2
    public ServicePoint(int newPacksInStack, int newX, int newY) {
        packsInStack = newPacksInStack;
        posX = newX;
        posY = newY;
    }

    public int getPacksInStack() {
        return packsInStack;
    }

    public void setPacksInStack(int packsInStack) {
        this.packsInStack = packsInStack;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
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
