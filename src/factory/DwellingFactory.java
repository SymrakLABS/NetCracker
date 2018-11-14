package factory;

import buildings.dwelling.Dwelling;
import buildings.dwelling.DwellingFloor;
import buildings.dwelling.Flat;
import interfaces.Building;
import interfaces.BuildingFactory;
import interfaces.Floor;
import interfaces.Space;

public class DwellingFactory implements BuildingFactory {

    public Space createSpace(int square) {
        return new Flat(square);
    }

    public Space createSpace(int roomsCount, int square) {
        return new Flat(roomsCount, square);
    }

    public Floor createFloor(int spacesCount) {
        return new DwellingFloor(spacesCount);
    }

    public Floor createFloor(Space[] spaces) {
        return new DwellingFloor((Flat[])spaces);
    }

    public Building createBuilding(int floorsCount, int[] spacesCounts) {
        return new Dwelling(floorsCount, spacesCounts);
    }

    public Building createBuilding(Floor[] floors) {
        return new Dwelling((DwellingFloor[]) floors);
    }
}
