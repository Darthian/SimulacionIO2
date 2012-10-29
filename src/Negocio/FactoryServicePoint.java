package Negocio;

public class FactoryServicePoint {

    public static ServicePoint createServicePoint(int PacksInStack, int newPosX, int newPosY) {
        return new ServicePoint(PacksInStack, newPosX, newPosY);
    }
}
