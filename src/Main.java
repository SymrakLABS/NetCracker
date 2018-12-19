import buildings.dwelling.hotel.Hotel;
import buildings.dwelling.hotel.HotelFloor;
import buildings.office.Office;
import buildings.office.OfficeBuilding;
import buildings.office.OfficeFloor;

import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {





        Office a = new Office(1,6);
        Office b = new Office(90,6);
        Office c = new Office(1000,89);

        Office d = new Office(14,52);
        Office e = new Office(85,67);
        Office f = new Office(900,839);

        Office arr1[] = {a, b, c};
        Office arr2[] = {d, e ,f};

        OfficeFloor dwellingFloor1 = new OfficeFloor(arr1);
        OfficeFloor dwellingFloor2 = new OfficeFloor(arr2);


        OfficeFloor of[] = {dwellingFloor1, dwellingFloor2};
        OfficeFloor of1[] = {dwellingFloor2, dwellingFloor1};


        OfficeBuilding building1 = new OfficeBuilding(of1);
        OfficeBuilding building = new OfficeBuilding(of);

        OfficeFloor officeFloor123 = (OfficeFloor) dwellingFloor1.clone();

        System.out.println(officeFloor123);


        OfficeFloor n = new OfficeFloor(2);
        n.addSpace(0, a);
        n.addSpace(1, b);
        //System.out.println(n);

        for(int i = 0; i < building1.getSize(); i++){
            for(int j = 0; j < building1.getFloor(i).getSize(); j++){
                //System.out.println(building1.getFloor(i).getSpace(j).getRooms());
            }
        }



        try{
            FileOutputStream fos = new FileOutputStream("C:\\Users\\Symrak\\Desktop\\q.dat");
            Buildings.outputBuilding(building1, fos);
        } catch (FileNotFoundException exp){
            exp.printStackTrace();
        } catch (IOException exp) {
            exp.printStackTrace();
        }

        try{
            InputStream reader = new FileInputStream("C:\\Users\\Symrak\\Desktop\\q.dat");
            OfficeBuilding ob = (OfficeBuilding) Buildings.inputBuilding(reader);

            System.out.println(ob);

        } catch (FileNotFoundException exp){
            exp.printStackTrace();
        } catch (IOException exp) {
            exp.printStackTrace();
        }

        /*

        try{
            InputStream reader = new FileInputStream("C:\\Users\\Symrak\\Desktop\\q.dat");
            OfficeBuilding ob = (OfficeBuilding) Buildings.inputBuilding(reader);

            for(int i = 0; i < ob.getSize(); i++){
                for(int j = 0; j < ob.getFloor(i).getSize(); j++){
                    //System.out.println(ob.getFloor(i).getSpace(j).getRooms());
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
            //System.out.println(ob);
        } catch (FileNotFoundException exp){
            exp.printStackTrace();
        }

     */

    }

}
