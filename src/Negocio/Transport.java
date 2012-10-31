package Negocio;

import java.util.ArrayList;

public class Transport {

    private int posX;
    private int posY;
    private int round;
    private int type;
    private int capacity;
    private ArrayList roadX = new ArrayList();
    private ArrayList roadY = new ArrayList();

    //Constructor
    public Transport(int newPosX, int newPosY, int newRound, int newType, Obstruction[][] obstructionMap, int destPosX, int destPosY, int newCapacity) {
        posX = newPosX;
        posY = newPosY;
        round = newRound;
        type = newType;
        capacity = newCapacity;
        setRoad(obstructionMap, destPosX, destPosY);
    }

    //Return actual X position
    public int getPosX() {
        return posX;
    }

    //Return actual Y position
    public int getPosY() {
        return posY;
    }

    //Set actual X position ass newPosX
    public void setPosX(int newPosX) {
        posX = newPosX;
    }

    //Set actual Y position ass newPosX
    public void setPosY(int newPosY) {
        posY = newPosY;
    }

    //Move unit according to road
    public boolean move(Transport[][] TransportMap) {
        if (!roadX.isEmpty()) {
            int tempPosX = (int) roadX.get(0);
            int tempPosY = (int) roadY.get(0);
            if (TransportMap[tempPosY][tempPosX] == null) {
                TransportMap[tempPosY][tempPosY] = this;
                TransportMap[posY][posY] = null;
                posX = (int) roadX.remove(0);
                posY = (int) roadY.remove(0);
            }
        } else {
            return true;
        }
        return false;
    }

    //Set road to move
    public void setRoad(Obstruction[][] obstructionMap, int destPosX, int destPosY) {
        roadX = new ArrayList();
        roadY = new ArrayList();
        Object[][] roadMap = new Object[18][24];
        ArrayList arrayPosX = new ArrayList();
        ArrayList arrayPosY = new ArrayList();
        int tempPosX = posX;
        int tempPosY = posY;
        arrayPosX.add(posX);
        arrayPosY.add(posY);
        int cont = 0;
        boolean find = false;
        roadMap[tempPosY][tempPosX] = cont;

        while (!arrayPosX.isEmpty() && !find) {
            cont++;
            tempPosX = (int) arrayPosX.remove(0);
            tempPosY = (int) arrayPosY.remove(0);
            if (tempPosX == destPosX && tempPosY == destPosY) {
                find = true;
            } else {
                if (tempPosX < 23 && obstructionMap[tempPosY][tempPosX + 1] == null && roadMap[tempPosY][tempPosX + 1] == null) {
                    arrayPosX.add(tempPosX + 1);
                    arrayPosY.add(tempPosY);
                    roadMap[tempPosY][tempPosX + 1] = cont;
                }
                if (tempPosX > 0 && obstructionMap[tempPosY][tempPosX - 1] == null && roadMap[tempPosY][tempPosX - 1] == null) {
                    arrayPosX.add(tempPosX - 1);
                    arrayPosY.add(tempPosY);
                    roadMap[tempPosY][tempPosX - 1] = cont;
                }
                if (tempPosY < 17 && obstructionMap[tempPosY + 1][tempPosX] == null && roadMap[tempPosY + 1][tempPosX] == null) {
                    arrayPosX.add(tempPosX);
                    arrayPosY.add(tempPosY + 1);
                    roadMap[tempPosY + 1][tempPosX] = cont;
                }
                if (tempPosY > 0 && obstructionMap[tempPosY - 1][tempPosX] == null && roadMap[tempPosY - 1][tempPosX] == null) {
                    arrayPosX.add(tempPosX);
                    arrayPosY.add(tempPosY - 1);
                    roadMap[tempPosY - 1][tempPosX] = cont;
                }
            }
        }

        for (int i = 0; i < 18; i++) {
            for (int j = 0; j < 24; j++) {
                if (roadMap[i][j] == null) {
                    roadMap[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        while (roadMap[tempPosY][tempPosX] != 0) {
            this.roadX.add(0, tempPosX);
            this.roadY.add(0, tempPosY);
            if (tempPosX < 23 && (int) roadMap[tempPosY][tempPosX + 1] < (int) roadMap[tempPosY][tempPosX]) {
                tempPosX = tempPosX + 1;
            } else {
                if (tempPosX > 0 && (int) roadMap[tempPosY][tempPosX - 1] < (int) roadMap[tempPosY][tempPosX]) {
                    tempPosX = tempPosX - 1;
                } else {
                    if (tempPosY < 17 && (int) roadMap[tempPosY + 1][tempPosX] < (int) roadMap[tempPosY][tempPosX]) {
                        tempPosY = tempPosY + 1;
                    } else {
                        tempPosY = tempPosY - 1;
                    }
                }
            }
        }
    }

    //Return enemy type
    public int getType() {
        return type;
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean reachDestination(ServicePoint destination) {
        if (posX == destination.getPosX() && posY == destination.getPosY()) {
            return true;
        }
        return false;
    }
}
