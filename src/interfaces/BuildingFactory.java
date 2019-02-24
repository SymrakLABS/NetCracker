package interfaces;

public interface BuildingFactory {
    Space createSpace(double square);
    Space createSpace(int roomsCount, double square);
    Floor createFloor(int spacesCount);
    Floor createFloor(Space[] spaces);
    Building createBuilding(int floorsCount, int[] spacesCounts);
    Building createBuilding(Floor[] floors);
}
