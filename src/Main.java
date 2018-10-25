import buildings.office.Office;
import buildings.office.OfficeBuilding;
import buildings.office.OfficeFloor;

public class Main {

    public static void main(String[] args) {

        Office a = new Office(1,5);
        Office b = new Office(8,6);
        Office c = new Office(5,89);

        Office d = new Office(14,52);
        Office e = new Office(85,67);
        Office f = new Office(51,839);

        Office arr1[] = {a, b, c};
        Office arr2[] = {d, e ,f};

        OfficeFloor dwellingFloor1 = new OfficeFloor(arr1);
        OfficeFloor dwellingFloor2 = new OfficeFloor(arr2);


        OfficeFloor of[] = {dwellingFloor1, dwellingFloor2};

        OfficeBuilding building = new OfficeBuilding(of);

        System.out.println(building.getRooms());
        for(int i = 0; i < building.getSize(); i++)
            System.out.println(building.getFloor(i).getBestSpace().getSquare());
    }
}
