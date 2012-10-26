package Negocio;

public class FactoryObstruction {

    public static Obstruction createDefense(int newPosX, int newPosY) {
        return new Obstruction(newPosX, newPosY);
    }
}
