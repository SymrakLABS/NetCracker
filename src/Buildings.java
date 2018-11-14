import factory.OfficeFactory;
import interfaces.Building;
import interfaces.BuildingFactory;
import interfaces.Floor;
import interfaces.Space;

import java.io.*;
import java.util.Formatter;
import java.util.Locale;
import java.util.Scanner;

public class Buildings {

    private static BuildingFactory buildingFactory = new OfficeFactory();

    public static void setBuildingFactory(BuildingFactory buildingFactory) {
        Buildings.buildingFactory = buildingFactory;
    }

    public Space createSpace(int square) {
        return buildingFactory.createSpace(square);
    }

    public Space createSpace(int roomsCount, int square) {
        return buildingFactory.createSpace(roomsCount, square);
    }

    public Floor createFloor(int spacesCount) {
        return buildingFactory.createFloor(spacesCount);
    }

    public Floor createFloor(Space[] spaces) {
        return buildingFactory.createFloor(spaces);
    }

    public Building createBuilding(int floorsCount, int[] spacesCounts) {
        return buildingFactory.createBuilding(floorsCount, spacesCounts);
    }

    public Building createBuilding(Floor[] floors) {
        return buildingFactory.createBuilding(floors);
    }

    //метод записи данных о здании в байтовый поток
    public static void outputBuilding(Building building, OutputStream out) throws IOException {
        DataOutputStream dos = new DataOutputStream(out);
        dos.writeInt(building.getSize());
        for (Object floorObj : building.getArrayOfFloors()) {
            Floor floor = (Floor) floorObj;
            dos.writeInt(floor.getSize());
            for (Object spaceObj : floor.getArraySpaces()) {
                Space space = (Space) spaceObj;
                dos.writeInt(space.getSquare());
                dos.writeInt(space.getRooms());
            }
        }
        dos.close();
    }

    /* todo а с чего это ты решил, что там office, а не Flat, officeFloor, а не DwellingFloor, officeBuilding, а не Dwelling?
     * хотяб добавил в выводе в самом начале строку - имя класса здания, здесь сначала считываешь его и в зависимости от считанного имени уже создаешь соответсвующие классы
     * а чтоб сделать это по-человечески, реализуй BuildingsFactory (пока без HotelFactory) из 6-й лабы и используй ее =)))))))))*/
    //метод чтения данных о здании из байтового потока
    public static Building inputBuilding (InputStream in) throws IOException {
        DataInputStream dis = new DataInputStream(in);
<<<<<<< HEAD
        Building building;
        int floorCount = dis.readInt();
        Floor[] floors = new Floor[floorCount];
        for (int i = 0; i < floorCount; i++) {
            int spacesCount = dis.readInt();
            /* todo массив здесь создавать не нужно. Создай экземпляр класса officeFloor или DwellingFloor используя конструктор, принимающий число помещений
             * и в цикле используй метод add()*/
            Space[] spaces = new Space[spacesCount];
            for (int j = 0; j < spacesCount; j++) {
                int roomCount = dis.readInt();
                int area = dis.readInt();
                spaces[j] = new Office(area, roomCount);
            }
            floors[i] = new OfficeFloor(spaces);
=======
        Floor[] floors = new Floor[dis.readInt()];
        for(int i = 0, sizeFloors = floors.length; i < sizeFloors; i++) {
            Space[] flats = new Space[dis.readInt()];
            for (int j = 0, sizeFlats = flats.length; j < sizeFlats; j++) {
                flats[j] = buildingFactory.createSpace(dis.readInt(), dis.readInt());
            }
            floors[i] = buildingFactory.createFloor(flats);
>>>>>>> fourth laba with a factory
        }
        dis.close();
        return buildingFactory.createBuilding(floors);
    }

    //метод записи здания в символьный поток
    public static void writeBuilding (Building building, Writer out) {
        PrintWriter pwo = new PrintWriter(out);
        Floor floor;
        Space space;
        pwo.print(building.getSize() + " ");
        for(int i = 0, floorsAmount = building.getSize(); i < floorsAmount; i++) {
            floor = building.getFloor(i);
            pwo.print(floor.getSize() + " ");
            for (int j = 0, spacesAmount = floor.getSize(); j < spacesAmount; j++) {
                space = floor.getSpace(j);
                pwo.print(space.getSquare() + " ");
                pwo.print(space.getRooms() + " ");
            }
        }
        pwo.close();
    }

    // todo аналогично методу inputBuilding()
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
<<<<<<< HEAD
        //todo поток забыл закрыть
=======
        outputStream.close();
>>>>>>> fourth laba with a factory
    }

    //метод десериализации здания из байтового потока
    public static Building deserialaizeBuilding (InputStream in) throws IOException, ClassNotFoundException{
        ObjectInputStream inputStream = new ObjectInputStream(in);
<<<<<<< HEAD
        Object building = inputStream.readObject(); //todo поток забыл закрыть


        //todo внимательно посмотри на этот if и познай всю свою рукожопость =)))))))))))
        if (building != null) {
            return (Building) building;
        }
        return null;
=======
        Object building = inputStream.readObject();
        return (Building) building;
>>>>>>> fourth laba with a factory
    }

    //метод текстовой форматированной записи
    public static void writeBuildingFormat (Building building, Writer out) throws IOException {
        Formatter formatter = new Formatter(new Locale("English"));
        Floor floors[] =  building.getArrayOfFloors();
        formatter.format("%d ", floors.length);
        for (Floor floor : building.getArrayOfFloors()) {
            formatter.format("%d ", floor.getSize());
            for (Space space : floor.getArraySpaces()) {
                formatter.format("%d %d ", space.getRooms(), space.getSquare());
            }
        }

        out.write(formatter.format("\n").toString());
        out.close();
    }

    // todo аналогично методу inputBuilding()
    //метод текстового чтения
    public static Building readBuilding(Scanner in) {
        Building building;
        int floorCount = in.nextInt();
        Floor[] floors = new Floor[floorCount];
        for (int i = 0; i < floorCount; i++) {
            int spacesCount = in.nextInt();
            Space[] spaces = new Space[spacesCount];
            for (int j = 0; j < spacesCount; j++) {
                int roomCount = in.nextInt();
                int area = in.nextInt();
                spaces[j] = buildingFactory.createSpace(roomCount, area);
            }

            floors[i] = buildingFactory.createFloor(spaces);
        }
        building = buildingFactory.createBuilding(floors);
        in.close();
        return building;
    }
}
