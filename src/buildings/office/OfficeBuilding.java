package buildings.office;

import buildings.dwelling.FinderFlat;
import exceptions.FloorIndexOutOfBoundsException;
import exceptions.SpaceIndexOutOfBoundsException;
import interfaces.Building;
import interfaces.Floor;
import interfaces.Space;

import java.io.Serializable;
import java.util.function.Function;

public class OfficeBuilding implements Building, Serializable {

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
        //не эффективно - каждый раз добавляя по i-му номеру ты гуляешь в поисках предыдущего нода i-1 раз.
        //todo лушче бы хранить tail - ссылку на последний нод (либо, поскольку список двусвязный, через head.previous), и добавлять нод после него
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
<<<<<<< HEAD
        //todo вот тут нужно проверку индекса делать и выбрасывать исключение, а не в методах, работающих со Floor-ом. И чтоб null не возвращать
        if(head != null) {
            Node current = head.next;
            int j = 0;
            while (current != head) {
                if (j++ == index) {
                    return current;
=======
        if(checkIndex(index)){
            if(head != null) {
                Node current = head.next;
                int j = 0;
                while (current != head) {
                    if (j++ == index) {
                        return current;
                    }
                    current = current.next;
>>>>>>> fourth laba with a factory
                }
            }
        }
        return null;
    }

    //метод добавления узла в список по номеру
    private void addNode(int index, Node newNode){
<<<<<<< HEAD
        //todo вот тут нужно проверку индекса делать и выбрасывать исключение, а не в методах, работающих со Floor-ом
        if (index == 0 || (head == null)) {
            if (head == null) {
                head = new Node(null, null, null);
                head.next = new Node(newNode.officeFloor, head, head);
                head.previous = head.next;
=======
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
>>>>>>> fourth laba with a factory
            }
            else {
                Node current = this.getNode(index - 1);
                current.next = new Node(newNode.officeFloor, current.next, current.previous);
            }
        }
    }

    //метод удаления узла из списка по его номеру
    private void removeNode(int index){
<<<<<<< HEAD
        //todo вот тут нужно проверку индекса делать и выбрасывать исключение, а не в методах, работающих со Space-ом
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
=======
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
>>>>>>> fourth laba with a factory
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
<<<<<<< HEAD
        if (head != null && head.next != head) {
            //todo нененене getNode(i) здесь вообще не эффективно. Гуляешь по нодам в цикле и каждый раз идешь к следующему по ссылке next. За один проход, а не за n*n как у тебя
            for (int i = 0; i < size; i++) {
                param += function.apply(getNode(i).officeFloor);
            }
        }
=======
        Node current = head;
        do {
            current = current.next;
            param += function.apply(current.officeFloor);

        } while (current.next != head);
>>>>>>> fourth laba with a factory
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
<<<<<<< HEAD
        if (head != null && head.next != head) {
            Floor[] result = new Floor[size];
            //todo нененене getNode(i) здесь вообще не эффективно. Гуляешь по нодам в цикле и каждый раз идешь к следующему по ссылке next. За один проход, а не за n*n как у тебя
            for (int i = 0; i < size; i++) {
                result[i] = getNode(i).officeFloor;
            }
            return result;
=======
        OfficeFloor officeFloor[] = new OfficeFloor[size];
        Node current = head;
        for (int i = 0; i < size; i++) {
            current = current.next;
            officeFloor[i] = (OfficeFloor) current.officeFloor;
>>>>>>> fourth laba with a factory
        }
        return officeFloor;
    }

    private boolean checkIndex(int index){
        if(index > size || index < 0){
            throw new FloorIndexOutOfBoundsException();
        }
        return true;
    }

    //todo вынеси дублирующиеся проверки в приватные методы, например void checkFloorIndex()
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
<<<<<<< HEAD
            throw new IndexOutOfBoundsException(); //todo может все же SpaceIndexOutOfBoundsException?
=======
            throw new SpaceIndexOutOfBoundsException();
>>>>>>> fourth laba with a factory
        }
    }

    //метод изменения объекта офиса по его номеру в доме и ссылке типа офиса
    public void setSpace(int index, Space newOffice) {
        if(index >= 0) {
            FinderFlat temp = findFlat(index);
            getFloor(temp.getFloorNumber()).setSpace(temp.getFlatNumber(), newOffice);
        }
        else{
<<<<<<< HEAD
            throw new IndexOutOfBoundsException(); //todo может все же SpaceIndexOutOfBoundsException?
=======
            throw new SpaceIndexOutOfBoundsException();
>>>>>>> fourth laba with a factory
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
<<<<<<< HEAD
                throw new FloorIndexOutOfBoundsException(); //todo может все же SpaceIndexOutOfBoundsException?
            }
        }
        else{
            throw new FloorIndexOutOfBoundsException(); //todo может все же SpaceIndexOutOfBoundsException?
=======
                throw new SpaceIndexOutOfBoundsException();
            }
        }
        else{
            throw new SpaceIndexOutOfBoundsException();
>>>>>>> fourth laba with a factory
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
<<<<<<< HEAD
        if (head != null && size != 0) {
            Space bestArea = getSpace(0);
            //todo getSpace 2 раза вызываешь - выноси в переменную. Нефиг выполнять одни и те же вычисления по нескольку раз.
            for (int i = 1; i < size; i++)
                if (bestArea.getSquare() < getSpace(i).getSquare()) {
                    bestArea = getSpace(i);
                }
            return bestArea;
        }
        else {
            return null;
=======
        int bestSpace = 0;
        Space officeBestSpace = null;
        Node current = head;
        for (int i = 1; i < size; i++) {
            current = current.next;
            if(current.officeFloor.getSquare() > bestSpace) { //saqare space
                bestSpace = current.officeFloor.getSquare();
                officeBestSpace = current.officeFloor.getBestSpace();
            }
>>>>>>> fourth laba with a factory
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
        final int prime = 31;
        int result = 1;
        result = prime * result + ((head == null) ? 0 : head.hashCode());
        return result;
    }
}
