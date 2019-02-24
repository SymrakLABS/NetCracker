import buildings.dwelling.Flat;
import buildings.dwelling.hotel.Hotel;
import buildings.dwelling.hotel.HotelFloor;
import buildings.office.Office;
import buildings.office.OfficeBuilding;
import buildings.office.OfficeFloor;
import interfaces.Building;

import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {


        Office officeA = new Office(1,3);
        Office officeB = new Office(1,12);
        Office officeC = new Office(1,3);

        Office officeD = new Office(1,3);
        Office officeI = new Office(1,12);
        Office officeF = new Office(1,3);

        Office[] arrOfficeA = { officeA, officeB, officeC };
        Office[] arrOfficeB = { officeD, officeI, officeF };

        OfficeFloor officeFloorA = new OfficeFloor(arrOfficeA);
        OfficeFloor officeFloorB = new OfficeFloor(arrOfficeB);

        OfficeFloor[] arr = { officeFloorA, officeFloorB };

        OfficeBuilding officeBuilding = new OfficeBuilding(arr);

        System.out.println(officeBuilding);


        try {
            Buildings.writeBuildingFormat(officeBuilding, new FileWriter(new File("formatWrite.txt")));
            Building office = Buildings.readBuilding(new Scanner(new FileReader(new File("formatWrite.txt"))));
            System.out.println((office));
        } catch (IOException e) {
            e.printStackTrace();
        }




    }

}
