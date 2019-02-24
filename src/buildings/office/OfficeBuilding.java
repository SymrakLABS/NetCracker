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

    private Node<Floor> head = new Node<>();
    private Node<Floor> tail;
    private int size;

    //конструктор принимает количество этажей и массив количества офисов по этажам
    public OfficeBuilding(int countOfficeFloors, int[] countOffices){
        head.next = head;
        head.prev = head;
        if (countOfficeFloors == countOffices.length) {
            for (int i = 0; i < countOffices.length; i++) {
                addNode(i, new OfficeFloor(countOffices[i]));
            }
        }
    }

    //конструктор принимает массив этажей офисного здания
    public OfficeBuilding(Floor[] officeFloors){
        head.next = head;
        head.prev = head;
        tail = head.next;
        for (int i = 0; i < officeFloors.length; i++) {
            if (officeFloors[i] != null) {
                Node<Floor> newNode = new Node<>(officeFloors[i]);
                newNode.next = head;
                head.prev = newNode;
                newNode.prev = tail;
                tail.next = newNode;
                tail = newNode;
                size++;
            }
        }
    }

    //метод получения узла по его номеру
    private Node<Floor> getNode(int index) {
        checkIndex(index); //todo просто вызываю
        Node<Floor> currentNode = head.next;
        int countNode = 0;
        while (countNode != index) {
            currentNode = currentNode.next;
            countNode++;
        }
        return currentNode;
    }

    //метод добавления узла в список по номеру
    private void addNode(int index, Floor newOfficeFloor) {
        checkIndex(index);
        Node<Floor> newNode = new Node<>(newOfficeFloor);
        if (index == size) {
            newNode.prev = head.prev;
            head.prev.next = newNode;
            newNode.next = head;
            head.prev = newNode;
            size++;
        } else {
            if (index < size) {
                Node<Floor> currentNode;
                currentNode = getNode(index);
                newNode.prev = currentNode.prev;
                currentNode.prev.next = newNode;
                newNode.next = currentNode;
                currentNode.prev = newNode;
                size++;
            }
        }
    }


    //метод удаления узла из списка по его номеру
    private void removeNode(int index){
        checkIndex(index);
        Node<Floor> currentNode;
        currentNode = getNode(index);
        currentNode.prev.next = currentNode.next;
        currentNode.next.prev = currentNode.prev;
        size--;
    }


    //метод получения общего количества этажей здания
    public int getSize() {
        return size;
    }

    //вспомогательный метод поиска количества оффисов, площади и количества комнат
    public double findParameter(Function<Floor, Double> function){
        double param = 0;
        Node<Floor> current = head;
        do {
            current = current.next;
            param += function.apply(current.data);

        } while (current.next != head);
        return param;
    }

    //метод получения количества оффисов здания
    public int getSpaces() {
        return (int) findParameter(new Function<Floor, Double>() {
            @Override
            public Double apply(Floor officeFloor) {
                return Double.valueOf(officeFloor.getSize());
            }
        });
    }

    //метод получения общей площади помещений здания
    public double getSquare() {
        return findParameter((p) -> p.getSquare() );
    }

    //метод получения общего количества комнат здания
    public int getRooms() {
        return (int) findParameter((p) -> Double.valueOf(p.getRooms()));
    }

    //метод получения массива этажей офисного здания
    public Floor[] getArrayOfFloors() {
        Floor officeFloor[] = new Floor[size];
        Node<Floor> current = head;
        for (int i = 0; i < size; i++) {
            current = current.next;
            officeFloor[i] = current.data;
        }
        return officeFloor;
    }

    //todo сдделал иначе
    private void checkIndex(int index){
        if(index > size || index < 0){
            throw new FloorIndexOutOfBoundsException();
        }
    }

    //метод получения объекта этажа, по его номеру в здании
    public Floor getFloor(int index) {
        return getNode(index).data;
    }

    //метод изменения этажа по его номеру в здании и ссылке на обновленный этаж
    public void setFloor(int index, Floor newOfficeFloor) {
        getNode(index).data = newOfficeFloor;
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
        Space bestOffice = head.next.data.getBestSpace();
        for (int i = 0; i < size; i++) {
            Space tmp = getNode(i).data.getBestSpace();
            if (tmp.getSquare() > bestOffice.getRooms()) {
                bestOffice = tmp;
            }
        }
        return bestOffice;
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
        //todo не надо массивы юзать. Ходи по нодам +++
        //todo StringBuilder +++
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("OfficeBuilding (").append(size);
        for (int i = 0; i < size; i++) {
            stringBuilder.append(", ").append(getNode(i).data); //iterator
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof OfficeBuilding)) {
            return false;
        }
        //todo проверяешь сначала size, а потом каждый элемент последовательно +++
        OfficeBuilding officeBuilding = (OfficeBuilding) obj;
        if (officeBuilding.size != size) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!officeBuilding.getNode(i).data.equals(getNode(i).data)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        //todo в вычислении хэшкода участвуют все элементы +++
        int hashCode = size;
        for (int i = 0; i < size; i++) {
            hashCode ^= getNode(i).data.hashCode();
        }
        return hashCode;
    }

    //todo clone() +++
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
            private Node<Floor> node = head.next;
            @Override
            public boolean hasNext() {
                return node != head;
            }

            @Override
            public Floor next() {
                Floor toReturn = node.data;
                node = node.next;
                return toReturn;
            }
        };
    }
}
