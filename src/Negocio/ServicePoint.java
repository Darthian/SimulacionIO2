package Negocio;

public class ServicePoint {

    private int posX;
    private int posY;
    private int packsInStack;
    private int type;

    //Contructor
    public ServicePoint() {
    }

    //Constructor 2
    public ServicePoint(int newPacksInStack, int newX, int newY,int newType) {
        packsInStack = newPacksInStack;
        posX = newX;
        posY = newY;
        type = newType;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
