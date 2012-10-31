package Negocio;

public class FactoryServicePoint {

    public static ServicePoint createServicePoint(int PacksInStack, int newPosX, int newPosY, int newType) {
        return new ServicePoint(PacksInStack, newPosX, newPosY, newType);
    }
}
