package buildings.dwelling;

public class FinderFlat {

    private int floorNumber;
    private int flatNumber;

    public FinderFlat(int floor, int flatNumber){
        this.floorNumber = floor;
        this.flatNumber = flatNumber;
    }

    public int getFloorNumber(){
        return floorNumber;
    }

    public int getFlatNumber(){
        return flatNumber;
    }

    public void setFloorNumber(int floorNumber){
        this.floorNumber = floorNumber;
    }

    public void setFlatNumber(int flatNumber){
        this.flatNumber = flatNumber;
    }
}
