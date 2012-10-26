package Negocio;

public class FactoryTransport {

    private static int Transport1Capacity = 2;
    
    private static int Transport2Capacity = 4;
    
    private static int Transport3Capacity = 6;
    
    private static int Transport4Capacity = 8;
    
    private static int Transport5Capacity = 10;

    public static Transport createTransport(int newPosX, int newPosY, int newRound, int newType, Obstruction[][] obstructionMap, int destPosX, int destPosY) {
        switch (newType) {
            case 1:
                return new Transport(newPosX, newPosY, newRound, newType, obstructionMap, destPosX, destPosY, Transport1Capacity);
            case 2:
                return new Transport(newPosX, newPosY, newRound, newType, obstructionMap, destPosX, destPosY, Transport2Capacity);
            case 3:
                return new Transport(newPosX, newPosY, newRound, newType, obstructionMap, destPosX, destPosY, Transport3Capacity);
            case 4:
                return new Transport(newPosX, newPosY, newRound, newType, obstructionMap, destPosX, destPosY, Transport4Capacity);
            case 5:
                return new Transport(newPosX, newPosY, newRound, newType, obstructionMap, destPosX, destPosY, Transport5Capacity);
            default:
                return null;
        }
    }

    public static int getCapacity(int newType) {
        switch (newType) {
            case 1:
                return Transport1Capacity;
            case 2:
                return Transport2Capacity;
            case 3:
                return Transport3Capacity;
            case 4:
                return Transport4Capacity;
            case 5:
                return Transport5Capacity;
            default:
                return 0;
        }
    }
}
