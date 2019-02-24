package buildings.dwelling.hotel;

import buildings.dwelling.Dwelling;
import buildings.dwelling.DwellingFloor;
import buildings.dwelling.Flat;
import interfaces.Floor;
import interfaces.Space;

public class Hotel extends Dwelling {

    public Hotel(Floor[] floors) {
        super(floors);
    }

    public Hotel(int countOfFloors, int[] spaces) {
        super(countOfFloors, spaces);
    }

    public int getStars() {
        int starCount = 1;
        Floor[] floors = toArray();
        for (Floor floor : floors) {
            if (floor instanceof HotelFloor) {
                HotelFloor hotelFloor = (HotelFloor) floor;
                if (starCount < hotelFloor.getStars()) {
                    starCount = hotelFloor.getStars();
                }
            }
        }
        return starCount;
    }

    @Override
    public Space getBestSpace() {
        Space bestSpace = getSpace(0);
        double coeffOfBestSpace = 0;
        Floor[] floors = toArray();
        for (Floor floor : floors) {
            if (floor instanceof HotelFloor) {
                HotelFloor hotelFloor = (HotelFloor) floor;
                double coeff = getCoeff(hotelFloor.getStars());
                Space[] spaces = hotelFloor.toArray();
                for (Space space : spaces) {
                    if (space.getSquare() * coeff > bestSpace.getSquare() * coeffOfBestSpace) {
                        bestSpace = space;
                        coeffOfBestSpace = coeff;
                    }
                }
            }
        }
        return bestSpace;
    }

    private double getCoeff(int stars) {
        switch (stars) {
            case 1:
                return 0.25;
            case 2:
                return 0.5;
            case 3:
                return 1;
            case 4:
                return 1.25;
            case 5:
                return 1.5;
        }
        return 0;
    }

    @Override
    public String toString() {
        Floor[] floors = toArray();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Hotel (").append(getStars()).append(", ").append(floors.length);
        for (int i = 0; i < floors.length; i++) {
            stringBuilder.append(", ").append(floors[i]);
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this != o) {
            return false;
        }
        if (!(o instanceof Hotel)) {
            return false;
        }
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
    
}
