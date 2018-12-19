package buildings.office;

import buildings.dwelling.FinderFlat;
import exceptions.FloorIndexOutOfBoundsException;
import exceptions.SpaceIndexOutOfBoundsException;
import interfaces.Building;
import interfaces.Floor;
import interfaces.Space;

import java.io.*;
import java.util.Iterator;
import java.util.function.Function;

public class OfficeBuilding implements Building, Serializable, Cloneable {

    private static class Node implements Serializable {
        Node next;
        Node previous;
        Floor officeFloor;

        Node(Floor officeFloor, Node next, Node previous){
            this.next = next;
            this.previous = previous;
            this.officeFloor = officeFloor;
        }
    }

    private Node head;
    private int size;

    //конструктор принимает количество этажей и массив количества офисов по этажам
    public OfficeBuilding(int countDwelling, int[] countOfficesOnFloor){
        if(countDwelling >= 0){
            this.size = countDwelling;
            for (int i = 0; i < countDwelling; i++) {
                this.addNode(i, new Node(new OfficeFloor(countOfficesOnFloor[i]), null, null));
            }
        }
        else {
            throw new SpaceIndexOutOfBoundsException();
        }
    }

    //конструктор принимает массив этажей офисного здания
    public OfficeBuilding(Floor[] officeFloors){
        this.size = officeFloors.length;
        head = new Node(null, null, null);
        head.next = head;
        head.previous = head;
        Node current = head;
        for (int i = 0; i < officeFloors.length ; i++) {
            Node x = new Node(officeFloors[i], head.next, head.previous);
            current.next = x;
            x.previous = current;
            x.officeFloor = officeFloors[i];
        }
        current.next = head.next;
        head.next.previous = current;
    }

    //метод получения узла по его номеру
    private Node getNode(int index) {
        if(checkIndex(index)){
            if(head != null) {
                Node current = head.next;
                int j = 0;
                while (current != head) {
                    if (j++ == index) {
                        return current;
                    }
                    current = current.next;
                }
            }
        }
        return null;
    }

    //метод добавления узла в список по номеру
    private void addNode(int index, Node newNode){
        if(checkIndex(index)){
            if (index == 0 || (head == null)) {
                if (head == null) {
                    head = new Node(null, null, null);
                    head.next = new Node(newNode.officeFloor, head, head);
                    head.previous = head.next;
                }
                else {
                    head.next = new Node(newNode.officeFloor, head.next, head);
                }
            }
            else {
                Node current = this.getNode(index - 1);
                current.next = new Node(newNode.officeFloor, current.next, current.previous);
            }
        }
    }

    //метод удаления узла из списка по его номеру
    private void removeNode(int index){
        if(checkIndex(index)){
            if (head != null) {
                if (index != 0) {
                    Node current = getNode(index - 1);
                    current.next = current.next.next;
                    current.next.previous = current;
                }
                else if (head.next.next == head){
                    head.next = head;
                    head.previous = head;
                }
                else {
                    Node current = head.next;
                    head.next = current.next;
                    head.next.previous = head;
                }
            }
        }
    }

    //метод получения общего количества этажей здания
    public int getSize() {
        return size;
    }

    //вспомогательный метод поиска количества оффисов, площади и количества комнат
    public int findParameter(Function<Floor, Integer> function){
        int param = 0;
        Node current = head;
        do {
            current = current.next;
            param += function.apply(current.officeFloor);

        } while (current.next != head);
        return param;
    }

    //метод получения количества оффисов здания
    public int getSpaces() {
        return findParameter(new Function<Floor, Integer>() {
            @Override
            public Integer apply(Floor officeFloor) {
                return officeFloor.getSize();
            }
        });
    }

    //метод получения общей площади помещений здания
    public int getSquare() {
       return findParameter((p) -> p.getSquare() );
    }

    //метод получения общего количества комнат здания
    public int getRooms() {
        return findParameter((p) -> p.getRooms());
    }

    //метод получения массива этажей офисного здания
    public Floor[] getArrayOfFloors() {
        Floor officeFloor[] = new Floor[size];
        Node current = head;
        for (int i = 0; i < size; i++) {
            current = current.next;
            officeFloor[i] = current.officeFloor;
        }
        return officeFloor;
    }

    private boolean checkIndex(int index){
        if(index > size || index < 0){
            throw new FloorIndexOutOfBoundsException();
        }
        return true;
    }

    //метод получения объекта этажа, по его номеру в здании
    public Floor getFloor(int index) {
        return getNode(index).officeFloor;
    }

    //метод изменения этажа по его номеру в здании и ссылке на обновленный этаж
    public void setFloor(int index, Floor newOfficeFloor) {
        getNode(index).officeFloor = newOfficeFloor;
    }

    //метод получения объекта офиса по его номеру в офисном здании
    public Space getSpace(int index) {
        if(index >= 0) {
            FinderFlat temp = findFlat(index);
            return getFloor(temp.getFloorNumber()).getSpace(temp.getFlatNumber());
        }
        else{
            throw new SpaceIndexOutOfBoundsException();
        }
    }

    //метод изменения объекта офиса по его номеру в доме и ссылке типа офиса
    public void setSpace(int index, Space newOffice) {
        if(index >= 0) {
            FinderFlat temp = findFlat(index);
            getFloor(temp.getFloorNumber()).setSpace(temp.getFlatNumber(), newOffice);
        }
        else{
            throw new SpaceIndexOutOfBoundsException();
        }
    }

    //вспомогательный метод поиска квартиры в здании
    public FinderFlat findFlat(int number){
        if(number >= 0){
            if(number <= size){
                int temp = number;
                for (int i = 0; i < size; i++)
                    for (int j = 0; j < getFloor(i).getSize(); j++) {
                        if ((temp--) == 0) {
                            return new FinderFlat(i, j);
                        }
                    }
                return null;
            }
            else{
                throw new SpaceIndexOutOfBoundsException();
            }
        }
        else{
            throw new SpaceIndexOutOfBoundsException();

        }
    }

    //метод добавления офиса в здание по номеру офиса в здании и ссылке на оффис
    public void addSpace(int index, Space newOffice) {
        if(index >= 0) {
            FinderFlat temp = findFlat(index);
            getFloor(temp.getFloorNumber()).addSpace(temp.getFlatNumber(), newOffice);
        }
        else{
            throw new IndexOutOfBoundsException();
        }
    }

    //метод удаления офиса по его номеру в здании
    public void deleteSpace(int index) {
        if(index >= 0) {
            FinderFlat temp = findFlat(index);
            getFloor(temp.getFloorNumber()).deleteSpace(temp.getFlatNumber());
        }
        else{
            throw new IndexOutOfBoundsException();
        }
    }

    //метод получения самого большого по площади офиса здания
    public Space getBestSpace() {
        int bestSpace = 0;
        Space officeBestSpace = null;
        Node current = head;
        for (int i = 1; i < size; i++) {
            current = current.next;
            if(current.officeFloor.getSquare() > bestSpace) { //saqare space
                bestSpace = current.officeFloor.getSquare();
                officeBestSpace = current.officeFloor.getBestSpace();
            }
        }
        return officeBestSpace;
    }

    //метод получения отсортированного по убыванию площадей массива офисов
    public Space[] sortSpaces() {
        Space[] temp = new Space[size];
        for (int i = 0; i < temp.length; i++) {
            temp[i] = this.getSpace(i);
        }
        for (int i = 0; i < temp.length; i++)
            for (int j = 0; j < temp.length - 1; j++)
                if (temp[j].getSquare() < temp[j + 1].getSquare()) {
                    Space tempNext = temp[j];
                    temp[j] = temp[j + 1];
                    temp[j + 1] = tempNext;
                }
        return temp;
    }

    public String getClassName(){
        return "OfficeBuilding";
    }


    @Override
    public String toString() {
        StringBuffer s = new StringBuffer();
        Floor[] floors = getArrayOfFloors();
        s.append("OfficeBuilding (").append(size).append(", ");
        for (int i = 0; i < size; i++) {
            if (i > 0) s.append(", ");
            s.append(floors[i].toString());
        }
        s.append(")");
        return s.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof OfficeBuilding))
            return false;
        OfficeBuilding other = (OfficeBuilding) obj;
        if (head == null) {
            if (other.head != null)
                return false;
        } else if (!head.equals(other.head))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        int hashCode = size;
        for (int i = 0; i < size; i++) {
            hashCode ^= getNode(i).officeFloor.hashCode();
        }
        return hashCode;
    }

    @Override
    public Object clone(){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(0xFFFF);
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
             ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()))) {
            objectOutputStream.writeObject(this);
            OfficeBuilding clone = (OfficeBuilding) objectInputStream.readObject();
            return clone;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Iterator<Floor> iterator() {
        return new Iterator<Floor>() {
            private Node node = head.next;

            @Override
            public boolean hasNext() {
                return node != head;
            }

            @Override
            public Floor next() {
                Floor toReturn = node.officeFloor;
                node = node.next;
                return toReturn;
            }
        };
    }
}
