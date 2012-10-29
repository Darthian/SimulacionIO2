package Negocio;

import java.util.ArrayList;

public class BusinessSimulation {

    private Obstruction obstructionMap[][];
    private Transport transportMap[][];
    private ArrayList<Obstruction> obstructions;
    private ArrayList<Transport> transports;
    private ArrayList<ServicePoint> servicePoint;
    private int round;
    private ArrayList transportType;
    private static BusinessSimulation instance;
    private int points;

    //Constructor
    public BusinessSimulation() {
        obstructionMap = new Obstruction[18][24];
        transportMap = new Transport[18][24];
        obstructions = new ArrayList();
        transports = new ArrayList();
        servicePoint = new ArrayList();
        round = 0;
        points = 0;
        transportType = new ArrayList();
        initRound();
    }

    //Create an instance of Game o return the existing
    public synchronized static BusinessSimulation getInstance() {
        if (instance == null) {
            instance = new BusinessSimulation();
        }
        return instance;
    }

    //Return defenses from actual game instance
    public ArrayList<Obstruction> getObstructions() {
        return obstructions;
    }

    //Return enemies from actual game instance
    public ArrayList<Transport> getTransports() {
        return transports;
    }

    //Return tower from actual game instance
    public ArrayList<ServicePoint> getServicePoint() {
        return servicePoint;
    }

    //Set defenses as newDefenses
    public void setDefenses(ArrayList<Obstruction> newDefenses) {
        this.obstructions = newDefenses;
    }

    //Set enemies as newEnemies
    public void setEnemies(ArrayList<Transport> newEnemies) {
        this.transports = newEnemies;
    }

    //Update the defense's map according to existing defenses
    private void setDefensesMap() {
        obstructionMap = new Obstruction[18][24];
        for (int i = 0; i < obstructions.size(); i++) {
            obstructionMap[obstructions.get(i).getPosY()][obstructions.get(i).getPosX()] = obstructions.get(i);
        }
    }

    //Update the enemy's map according to existing enemies
    private void setEnemiesMap() {
        transportMap = new Transport[18][24];
        for (int i = 0; i < transports.size(); i++) {
            transportMap[transports.get(i).getPosY()][transports.get(i).getPosX()] = transports.get(i);
        }
    }

    //Validate if available position to set new defense
    private boolean validateNewTowerPosition(int newPosX, int newPosY, int destPosX, int destPosY) {
        int[][] tempObstructionMap = new int[18][24];
        ArrayList arrayPosX = new ArrayList();
        ArrayList arrayPosY = new ArrayList();
        int tempPosX;
        int tempPosY;
        arrayPosX.add(23);
        arrayPosY.add(0);
        if (newPosX == 23 && newPosY == 0) {
            return false;
        }
        boolean find = false;
        for (int i = 0; i < obstructions.size(); i++) {
            tempObstructionMap[obstructions.get(i).getPosY()][obstructions.get(i).getPosX()] = 1;
        }
        tempObstructionMap[newPosY][newPosX] = 1;

        while (!arrayPosX.isEmpty() && !find) {
            tempPosX = Integer.parseInt(arrayPosX.remove(0).toString());
            tempPosY = Integer.parseInt(arrayPosY.remove(0).toString());
            if (tempPosX == destPosX && tempPosY == destPosY) {
                find = true;
            } else {
                if (tempPosX < 23 && tempObstructionMap[tempPosY][tempPosX + 1] == 0 && tempObstructionMap[tempPosY][tempPosX + 1] != 2) {
                    arrayPosX.add(tempPosX + 1);
                    arrayPosY.add(tempPosY);
                    tempObstructionMap[tempPosY][tempPosX + 1] = 2;
                }
                if (tempPosX > 0 && tempObstructionMap[tempPosY][tempPosX - 1] == 0 && tempObstructionMap[tempPosY][tempPosX - 1] != 2) {
                    arrayPosX.add(tempPosX - 1);
                    arrayPosY.add(tempPosY);
                    tempObstructionMap[tempPosY][tempPosX - 1] = 2;
                }
                if (tempPosY < 17 && tempObstructionMap[tempPosY + 1][tempPosX] == 0 && tempObstructionMap[tempPosY + 1][tempPosX] != 2) {
                    arrayPosX.add(tempPosX);
                    arrayPosY.add(tempPosY + 1);
                    tempObstructionMap[tempPosY + 1][tempPosX] = 2;
                }
                if (tempPosY > 0 && tempObstructionMap[tempPosY - 1][tempPosX] == 0 && tempObstructionMap[tempPosY - 1][tempPosX] != 2) {
                    arrayPosX.add(tempPosX);
                    arrayPosY.add(tempPosY - 1);
                    tempObstructionMap[tempPosY - 1][tempPosX] = 2;
                }
            }
        }
        return find;
    }

    //Create new defense in coordinates newPosX newPosY and newType type if available position
    public boolean newObstruction(int newPosX, int newPosY, int newType, int currentServicePoint) {
        if (validateNewTowerPosition(newPosX, newPosY, servicePoint.get(currentServicePoint).getPosX(), servicePoint.get(currentServicePoint).getPosY())) {
            Obstruction newDefense = FactoryObstruction.createDefense(newPosX, newPosY);
            obstructions.add(newDefense);
            setDefensesMap();
            for (int i = 0; i < transports.size(); i++) {
                transports.get(i).setRoad(obstructionMap, servicePoint.get(currentServicePoint).getPosX(), servicePoint.get(currentServicePoint).getPosY());
            }
            return true;
        } else {
            return false;
        }
    }

    //Create new enemy in coordinates newPosX newPosY and newType type if available position and specifying the round
    public boolean newTransport(int newPosX, int newPosY, int newRound, int newType, int currentServicePoint) {
        if (transportMap[newPosY][newPosX] == null && obstructionMap[newPosY][newPosX] == null) {
            Transport newEnemy = FactoryTransport.createTransport(newPosX, newPosY, newRound, newType, obstructionMap, servicePoint.get(currentServicePoint).getPosX(), servicePoint.get(currentServicePoint).getPosY());
            transports.add(newEnemy);
            setEnemiesMap();
            return true;
        }
        return false;
    }

    //If tower is dead, return true, otherwise false
    public boolean nextStep(int currentServicePoint) {
        createTransport(currentServicePoint);
        moveTrasport();
        leavePacks(currentServicePoint);
        //if(validateRound()){}
        if (validateServicePoint(currentServicePoint)) {
            return true;
        }
        return false;
    }

    //Create enemies for actual round
    private void createTransport(int currentServicePoint) {
        if (!transportType.isEmpty()) {
            if (newTransport(23, 0, round, Integer.parseInt(transportType.get(0).toString()), currentServicePoint)) {
                transportType.remove(0);
            }
        }
    }

    //Move enemies
    private void moveTrasport() {
        for (int i = 0; i < transports.size(); i++) {
            transports.get(i).move(transportMap);
            setEnemiesMap();
        }
    }

    //Attack tower
    private void leavePacks(int currentServicePoint) {
        for (int i = 0; i < transports.size(); i++) {
            if (transports.get(i).reachTower(servicePoint.get(currentServicePoint))) {
                servicePoint.get(currentServicePoint).setPacksInStack(transports.get(i).getCapacity());
                transports.remove(i);
                i--;
            }
        }
    }

    //Validate tower life
    private boolean validateServicePoint(int currentServicePoint) {
        if (servicePoint.get(currentServicePoint).getPacksInStack() <= 0) {
            return true;
        }
        return false;
    }

    //Validate if round over
    public boolean isRoundOver() {
        if (transports.isEmpty() && transportType.isEmpty()) {
            return true;
        }
        return false;
    }

    public void initRound() {
        round++;
        int types[] = {1, 1, 1, 2, 2, 2, 3, 3, 4, 5};
        for (int i = 0; i < types.length; i++) {
            transportType.add(types[i]);
        }
    }

    public void updateRoad(int currentServicePoint) {
        for (int i = 0; i < transports.size(); i++) {
            transports.get(i).setRoad(obstructionMap, servicePoint.get(currentServicePoint).getPosX(), servicePoint.get(currentServicePoint).getPosY());
        }
    }

    public int getPoints() {
        return points;
    }

    public static void reset() {
        instance = new BusinessSimulation();
    }
}
