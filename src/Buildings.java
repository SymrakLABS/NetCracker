import factories.DwellingFactory;
import factories.OfficeFactory;
import interfaces.Building;
import interfaces.BuildingFactory;
import interfaces.Floor;
import interfaces.Space;

import java.io.*;
import java.util.Arrays;
import java.util.Formatter;
import java.util.Locale;
import java.util.Scanner;

public class Buildings {

    private static BuildingFactory buildingFactory = new OfficeFactory();

    public static void setBuildingFactory(BuildingFactory buildingFactory) {
        Buildings.buildingFactory = buildingFactory;
    }

    private static Space createSpace(double square, int roomsCount) {
        return buildingFactory.createSpace(roomsCount, square);
    }

    private static Floor createFloor(int spacesCount) {
        return buildingFactory.createFloor(spacesCount);
    }

    private static Space createSpace(int square) {
        return buildingFactory.createSpace(square);
    }


    private static Floor createFloor(Space[] spaces) {
        return buildingFactory.createFloor(spaces);
    }

    private static Building createBuilding(int floorsCount, int[] spacesCounts) {
        return buildingFactory.createBuilding(floorsCount, spacesCounts);
    }

    private static Building createBuilding(Floor[] floors) {
        return buildingFactory.createBuilding(floors);
    }


    //метод записи данных о здании в байтовый поток
    public static void outputBuilding(Building building, OutputStream outputStream) throws IOException {
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        int sizeOfBuilding = building.getSize();
        dataOutputStream.writeInt(sizeOfBuilding);
        for (int i = 0; i < sizeOfBuilding; i++) {
            Floor floor = building.getFloor(i);
            int sizeOfFloor = floor.getSize();
            dataOutputStream.writeInt(sizeOfFloor);
            for (int j = 0; j < sizeOfFloor; j++) {
                Space space = floor.getSpace(j);
                dataOutputStream.writeInt(space.getRooms());
                dataOutputStream.writeDouble(space.getSquare());
            }
        }
        dataOutputStream.flush();
    }

    //метод чтения данных о здании из байтового потока
    public static Building inputBuilding (InputStream in) throws IOException {
        DataInputStream dataInputStream = new DataInputStream(in);
        int sizeOfBuilding = dataInputStream.readInt();
        Floor[] floors = new Floor[sizeOfBuilding];
        for (int i = 0; i < sizeOfBuilding; i++) {
            int sizeOfFloor = dataInputStream.readInt();
            floors[i] = createFloor(sizeOfFloor);
            for (int j = 0; j < sizeOfFloor; j++) {
                int roomCount = dataInputStream.readInt();
                int square = dataInputStream.readInt();
                floors[i].setSpace(j, createSpace(square, roomCount));
            }
        }
        return createBuilding(floors);
    }

    //метод записи здания в символьный поток
    public static void writeBuilding (Building building, Writer out) {
        PrintWriter printWriter = new PrintWriter(out);
        int sizeOfBuilding = building.getSize();
        printWriter.println(sizeOfBuilding);
        for (int i = 0; i < sizeOfBuilding; i++) {
            Floor floor = building.getFloor(i);
            int sizeOfFloor = floor.getSize();
            printWriter.println(sizeOfFloor);
            for (int j = 0; j < sizeOfFloor; j++) {
                Space space = floor.getSpace(j);
                printWriter.println(space.getRooms());
                printWriter.println(space.getSquare());
            }
        }
        printWriter.flush();
    }

    //метод чтения здания из символьного потока
    public static Building readBuilding (Reader in) throws IOException {
        StreamTokenizer st = new StreamTokenizer(in);
        Floor [] floors = new Floor[(int)st.nextToken()];
        for(int i = 0, sizeFloors = floors.length; i < sizeFloors; i++) {
            Space[] flats = new Space[(int)st.nextToken()];
            for (int j = 0, sizeFlats = flats.length; j < sizeFlats; j++) {
                flats[j] = buildingFactory.createSpace((int)st.nextToken(), (int)st.nextToken());
            }
            floors[i] = buildingFactory.createFloor(flats);
        }
        return buildingFactory.createBuilding(floors);
    }

    //метод сериализации здания в байтовый поток
    public static void serializeBuilding (Building building, OutputStream out) throws IOException{
        ObjectOutputStream outputStream = new ObjectOutputStream(out);
        outputStream.writeObject(building);
        outputStream.flush();
    }

    //метод десериализации здания из байтового потока
    public static Building deserialaizeBuilding (InputStream in) throws IOException, ClassNotFoundException{
        ObjectInputStream inputStream = new ObjectInputStream(in);
        Object building = inputStream.readObject();
        inputStream.close();
        return (Building) building;
    }

    //метод текстовой форматированной записи
    public static void writeBuildingFormat (Building building, Writer out) throws IOException {
        PrintWriter printWriter = new PrintWriter(out);
        int sizeOfBuilding = building.getSize();
        printWriter.printf("%d ", sizeOfBuilding);
        for (int i = 0; i < sizeOfBuilding; i++) {
            Floor floor = building.getFloor(i);
            int sizeOfFloor = floor.getSize();
            printWriter.printf("%d ", sizeOfFloor);
            for (int j = 0; j < sizeOfFloor; j++) {
                Space space = floor.getSpace(i);
                printWriter.printf("%d ", space.getRooms());
                printWriter.printf("%f ", space.getSquare());
            }
        }
        printWriter.flush();
    }

    //метод текстового чтения
    public static Building readBuilding(Scanner scanner) {
        int sizeOfBuilding = scanner.nextInt();
        Floor[] floors = new Floor[sizeOfBuilding];
        for (int i = 0; i < sizeOfBuilding; i++) {
            int sizeOfFloor = scanner.nextInt();
            floors[i] = createFloor(sizeOfFloor);
            for (int j = 0; j < sizeOfFloor; j++) {
                int roomCount = scanner.nextInt();
                double area = scanner.nextDouble();
                floors[i].addSpace(j, createSpace(area, roomCount));
            }
        }
        return createBuilding(floors);
    }

    //метод сортировки помещений этажа по возрастанию площадей помещений
    private static Space[] sortSpace(Floor floor) {
        Space[] sortedSpaces = floor.getArraySpaces();
        Arrays.sort(sortedSpaces);
        return sortedSpaces;
    }

    //метод сортировки этажей здания по возрастанию количества помещений на этаже
    private static Floor[] sortFloor(Building building) {
        Floor[] sortedFloors = building.getArrayOfFloors();
        Arrays.sort(sortedFloors);
        return sortedFloors;
    }

    //параметризованный метод
    public static <T, V> V[] sortByAscending(T object) {
        if (object instanceof Floor) {
            Floor floor = (Floor) object;
            return (V[]) sortSpace(floor);
        } else {
            if (object instanceof Building) {
                Building building = (Building) object;
                return (V[]) sortFloor(building);
            }
        }
        return null;
    }

    private static Space[] sortSpaceWithCriterion(Floor floor) {
        Space[] sortedSpaces = floor.getArraySpaces();
        Arrays.sort(sortedSpaces,(space1, space2) -> (space2.compareTo(space1)));
        return sortedSpaces;
    }

    private static Floor[] sortFloorWithCriterion(Building building) {
        Floor[] floors = building.getArrayOfFloors();
        Arrays.sort(floors, (floor1, floor2) -> (floor2.compareTo(floor1)));
        return floors;
    }

    public static <T, V> V[] sortByDescending(T object) {
        if (object instanceof Floor) {
            Floor floor = (Floor) object;
            return (V[]) sortSpaceWithCriterion(floor);
        } else {
            if (object instanceof Building) {
                Building building = (Building) object;
                return (V[]) sortFloorWithCriterion(building);
            }
        }
        return null;
    }

    public static SynchronizedFloor synchronizedFloor(Floor floor) {
        return new SynchronizedFloor(floor);
    }
}
