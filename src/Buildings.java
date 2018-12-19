import factory.DwellingFactory;
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

    private static BuildingFactory buildingFactory;

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

        dos.writeUTF(building.getClassName());
        dos.writeInt(building.getSize());
        //System.out.println(building.getClassName());
        for (Object floorObj : building.getArrayOfFloors()) {
            Floor floor = (Floor) floorObj;
            dos.writeUTF(floor.getClassName());
            dos.writeInt(floor.getSize());
            for (Object spaceObj : floor.getArraySpaces()) {
                Space space = (Space) spaceObj;
                dos.writeUTF(space.getClassName());
                dos.writeInt(space.getSquare());
                dos.writeInt(space.getRooms());
            }
        }
        dos.close();
    }

    //метод чтения данных о здании из байтового потока
    public static Building inputBuilding (InputStream in) throws IOException {
        DataInputStream dis = new DataInputStream(in);
        String className;
        className = dis.readUTF();
        if(className.equals("OfficeBuilding")){ //todo надо короч в зависимости от классИмя вызывать определенные методы фабрика гвгыггыгыгы
            setBuildingFactory(new OfficeFactory());
        } else if (className.equals("Dwelling")){
            setBuildingFactory(new DwellingFactory());
        }
        Floor[] floors = new Floor[dis.readInt()];
        for(int i = 0, sizeFloors = floors.length; i < sizeFloors; i++) {
            className = dis.readUTF();
            Space[] flats = new Space[dis.readInt()];
            for (int j = 0, sizeFlats = flats.length; j < sizeFlats; j++) {
                className = dis.readUTF();
                flats[j] = buildingFactory.createSpace(dis.readInt(), dis.readInt());
            }
            floors[i] = buildingFactory.createFloor(flats);
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
        return (Building) building;
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
