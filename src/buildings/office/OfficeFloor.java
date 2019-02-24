package buildings.office;

import exceptions.SpaceIndexOutOfBoundsException;
import interfaces.Floor;
import interfaces.Space;

import java.io.*;
import java.util.Iterator;

public class OfficeFloor implements Floor, Serializable, Cloneable {

    private Node<Space> head = new Node<>();
    private Node<Space> tail;
    private int size;

    //конструктор может принимать количество офисов на этаже
    public OfficeFloor(int countOfOffices) {
        head.next = head;
        tail = head.next;
    }

    //конструктор может принимать массив офисов этажа
    public OfficeFloor(Space[] offices) {
        head.next = head;
        tail = head.next;
        for (int i = 0; i < offices.length; i++) {
            if (offices[i] != null) {
                Node<Space> newNode = new Node<>(offices[i]);
                newNode.next = head;
                tail.next = newNode;
                tail = newNode;
                size++;
            }
        }
    }

    //метод получения узла по его номеру
    private Node<Space> getNode(int index) {
        checkIndex(index);
        int numberOfNode = 0;
        Node<Space> currentNode = head.next;
        while(numberOfNode != index){
            currentNode = currentNode.next;
            numberOfNode++;
        }
        return currentNode;
    }

    //метод добавления узла в список по номеру
    private void addNode(int index, Space newOffice) {
        checkIndex(index);
        Node<Space> newNode = new Node<>(newOffice);
        if(index == size) {
            tail.next = newNode;
            newNode.next = head;
            tail = newNode;
            size++;
        }  else {
            if (index >= 1) {
                Node<Space> previousNode = getNode(index - 1);
                Node<Space> currentNode = previousNode.next;
                previousNode.next = newNode;
                newNode.next = currentNode;
                size++;
            } else {
                if(index == 0) {
                    newNode.next = head.next;
                    head.next = newNode;
                    size++;
                }
            }
        }
    }


    //метод удаления узла из списка по его номеру
    private void removeNode(int index) {
        checkIndex(index);
        if(index >= 1) {
            Node<Space> previousNode = getNode(index - 1);
            Node<Space> currentNode = previousNode.next;
            previousNode.next = currentNode.next;
        } else {
            if (index == 0) {
                head.next = head.next.next;
            }
        }
    }

    //метод получения количества офисов на этаже
    public int getSize() {
        return size;
    }

    //метод получения общей площади помещений этажа
    public double getSquare() {
        Node<Space> currentNode = head.next;
        double sum = currentNode.data.getSquare();
        while (currentNode.next != head) {
            currentNode = currentNode.next;
            sum += currentNode.data.getSquare();
        }
        return sum;
    }

    //метод получения общего количества комнат этажа
    public int getRooms() {
        Node<Space> currentNode = head.next;
        int countRooms = currentNode.data.getRooms();
        while (currentNode.next != head) {
            currentNode = currentNode.next;
            countRooms += currentNode.data.getRooms();
        }
        return countRooms;
    }

    //метод получения массива офисов этажа
    public Space[] getArraySpaces() {
        Space[] offices = new Space[size];
        Node<Space> tmp = head.next;
        for (int i = 0; i < offices.length; i++) {
            offices[i] = tmp.data;
            tmp = tmp.next;
        }
        return offices;
    }

    //проверка допустимого значения
    private void checkIndex(int index){
        if(index > size && index < 0){
            throw new SpaceIndexOutOfBoundsException();
        }
    }

    //метод получения офиса по его номеру на этаже
    public Space getSpace(int index) {
        checkIndex(index);
        return getNode(index).data;
    }

    //метод изменения офиса по его номеру на этаже и ссылке на обновленный офис
    public void setSpace(int index, Space newOffice) {
        getNode(index).data = newOffice;
    }

    //метод добавления нового офиса на этаже по будущему номеру офиса
    public void addSpace(int index, Space newOffice) {
        addNode(index, newOffice);
    }

    //метод удаления офиса по его номеру на этаже
    public void deleteSpace(int index) {
        removeNode(index);
    }

    //метод получения самого большого по площади офиса
    public Space getBestSpace() {
        Node<Space> bestOffice = head.next;
        Node<Space> currentOffice = bestOffice.next;
        while (currentOffice.next != head) {
            if (currentOffice.data.getSquare() > bestOffice.data.getSquare()) {
                bestOffice = currentOffice;
            }
            currentOffice = currentOffice.next;
        }
        return bestOffice.data;
    }

    @Override
    public String toString(){
        //todo не надо массивы юзать. Ходи по нодам +++
        //todo StringBuilder +++
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("OfficeFloor (").append(size);
        for (int i = 0; i < size; i++) {
            stringBuilder.append(", ").append(getNode(i).data);
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj)
            return true;
        if (!(obj instanceof OfficeFloor)) {
            return false;
        }
        //todo проверяешь сначала size, а потом каждый элемент последовательно +++
        OfficeFloor officeFloor = (OfficeFloor) obj;
        if (officeFloor.size != size) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!officeFloor.getNode(i).data.equals(getNode(i).data)) {
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
    public Object clone() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(0xFFFF);
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
             ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()))) {
            objectOutputStream.writeObject(this);
            OfficeFloor clone = (OfficeFloor) objectInputStream.readObject();
            return clone;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int compareTo(Floor o) {
        if (o instanceof OfficeFloor) {
            if (this.equals(o)) {
                return 0;
            } else {
                return this.getSize() - o.getSize();
            }
        }
        return Integer.MIN_VALUE;
    }

    @Override
    public Iterator<Space> iterator() {
        return new Iterator<Space>() {
            private Node<Space> currentNode = head.next;
            @Override
            public boolean hasNext() {
                return currentNode != head;
            }

            @Override
            public Space next() {
                Space toReturn = currentNode.data;
                currentNode = currentNode.next;
                return toReturn;
            }
        };
    }
}