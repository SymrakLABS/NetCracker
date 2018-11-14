package interfaces;

public interface BuildingFactory {
    public Space createSpace(int square);
    public Space createSpace(int roomsCount, int square);
    public Floor createFloor(int spacesCount);
    public Floor createFloor(Space[] spaces);
    public Building createBuilding(int floorsCount, int[] spacesCounts);
    public Building createBuilding(Floor[] floors);
}
