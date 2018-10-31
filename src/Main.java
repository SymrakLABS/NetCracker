import buildings.office.Office;
import buildings.office.OfficeBuilding;
import buildings.office.OfficeFloor;
import exceptions.InexchangeableFloorsException;

import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Office a = new Office(1,6);
        Office b = new Office(1,6);
        Office c = new Office(5,89);

        Office d = new Office(14,52);
        Office e = new Office(85,67);
        Office f = new Office(1,839);

        Office arr1[] = {a, b, c};
        Office arr2[] = {d, e ,f};

        OfficeFloor dwellingFloor1 = new OfficeFloor(arr1);
        OfficeFloor dwellingFloor2 = new OfficeFloor(arr2);


        OfficeFloor of[] = {dwellingFloor1, dwellingFloor2};
        OfficeFloor of1[] = {dwellingFloor2, dwellingFloor1};


        OfficeBuilding building1 = new OfficeBuilding(of1);
        OfficeBuilding building = new OfficeBuilding(of);

        System.out.println();

        try{
            FileOutputStream fos = new FileOutputStream("C:\\Users\\Symrak\\Desktop\\q.dat");
            Buildings.outputBuilding(building, fos);
        } catch (FileNotFoundException exp){
            exp.printStackTrace();
        } catch (IOException exp){
            exp.printStackTrace();
        }


        try{
            InputStream reader = new FileInputStream("C:\\Users\\Symrak\\Desktop\\q.dat");
            OfficeBuilding ob = (OfficeBuilding) Buildings.inputBuilding(reader);

            for(int i = 0; i < ob.getSize(); i++){
                for(int j = 0; j < ob.getFloor(i).getSize(); j++){
                    System.out.println(ob.getFloor(i).getSpace(j).getRooms());
                }
            }


        } catch (FileNotFoundException exp){
            exp.printStackTrace();
        } catch (IOException exp) {
            exp.printStackTrace();
        }

        try {
            FileWriter os = new FileWriter("C:\\Users\\Symrak\\Desktop\\q.txt");
            Buildings.writeBuildingFormat(building, os);

        } catch (IOException exp){
            exp.printStackTrace();
        }


        try{
            File file = new File("C:\\Users\\Symrak\\Desktop\\q.txt");
            Scanner in = new Scanner(file);
            OfficeBuilding ob = (OfficeBuilding) Buildings.readBuilding(in);
            System.out.println(ob.getFloor(0).getSpace(0).getRooms());
        } catch (FileNotFoundException exp){
            exp.printStackTrace();
        }
    }
}
