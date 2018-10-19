import buildings.Dwelling;
import buildings.DwellingFloor;
import buildings.Flat;

public class Main {

    public static void main(String[] args) {

        Flat a = new Flat(1,5);
        Flat b = new Flat(8,6);
        Flat c = new Flat(5,89);

        Flat d = new Flat(14,52);
        Flat e = new Flat(85,67);
        Flat f = new Flat(51,839);

        Flat arr1[] = {a, b, c};
        Flat arr2[] = {d, e ,f};

        DwellingFloor dwellingFloor1 = new DwellingFloor(arr1);
        DwellingFloor dwellingFloor2 = new DwellingFloor(arr2);

        DwellingFloor arr3[] = {dwellingFloor1, dwellingFloor2};

        Dwelling dwelling = new Dwelling(arr3);

        Flat y[] =  dwelling.sortingFlats();

        for(int i = 0; i < y.length; i++)
            System.out.println(y[i].getSquare());
    }
}
