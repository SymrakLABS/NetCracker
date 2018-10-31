import buildings.office.Office;
import buildings.office.OfficeBuilding;
import buildings.office.OfficeFloor;
import interfaces.Building;
import interfaces.Floor;
import interfaces.Space;

import java.io.*;
import java.util.Formatter;
import java.util.Locale;
import java.util.Scanner;

public class Buildings {

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
        }
        building = new OfficeBuilding(floors);
        dis.close();
        return building;
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
        StreamTokenizer tokenizer = new StreamTokenizer(in);
        tokenizer.nextToken();
        OfficeFloor[] floors = new OfficeFloor[(int) tokenizer.nval];
        if (floors.length == 0){
            return null;
        }
        for (int floorIndex = 0; floorIndex < floors.length; floorIndex++) {
            tokenizer.nextToken();
            Office[] spaces = new Office[(int) tokenizer.nval];
            for (int spaceIndex = 0; spaceIndex < spaces.length; spaceIndex++) {
                tokenizer.nextToken();
                int roomsCount = (int) tokenizer.nval;
                tokenizer.nextToken();
                int area = (int)tokenizer.nval;
                spaces[spaceIndex] = new Office(roomsCount, area);
            }
            floors[floorIndex] = new OfficeFloor(spaces);
        }
        return new OfficeBuilding(floors);
    }

    //метод сериализации здания в байтовый поток
    public static void serializeBuilding (Building building, OutputStream out) throws IOException{
        ObjectOutputStream outputStream = new ObjectOutputStream(out);
        outputStream.writeObject(building);
        //todo поток забыл закрыть
    }

    //метод десериализации здания из байтового потока
    public static Building deserialaizeBuilding (InputStream in) throws IOException, ClassNotFoundException{
        ObjectInputStream inputStream = new ObjectInputStream(in);
        Object building = inputStream.readObject(); //todo поток забыл закрыть


        //todo внимательно посмотри на этот if и познай всю свою рукожопость =)))))))))))
        if (building != null) {
            return (Building) building;
        }
        return null;
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
                spaces[j] = new Office(area, roomCount);
            }

            floors[i] = new OfficeFloor(spaces);
        }
        building = new OfficeBuilding(floors);
        in.close();
        return building;
    }
}
