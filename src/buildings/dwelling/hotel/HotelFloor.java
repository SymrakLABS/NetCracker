package buildings.dwelling.hotel;

import buildings.dwelling.DwellingFloor;
import buildings.dwelling.Flat;
import interfaces.Space;

public class HotelFloor extends DwellingFloor {

    private static final int DEFAULT_STARS = 1;
    private int stars;


    public HotelFloor(Space[] spaces) {
        super(spaces);
        this.stars = DEFAULT_STARS;
    }

    public HotelFloor(int countSpaces) {
        super(countSpaces);
        this.stars = DEFAULT_STARS;
    }

    public int getStars(){
        return stars;
    }

    public void setStars(int stars){
        this.stars = stars;
    }

    
    //super
    @Override
    public String toString() {
        Space[] spaces = toArray();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("HotelFloor (").append(stars).append(", ").append(spaces.length);
        for (int i = 0; i < spaces.length; i++) {
            stringBuilder.append(", ").append(spaces[i]);
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof HotelFloor)) {
            return false;
        }
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return stars ^ super.hashCode();
    }
}
