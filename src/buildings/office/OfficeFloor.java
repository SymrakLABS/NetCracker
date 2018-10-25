package buildings.office;

import exceptions.SpaceIndexOutOfBoundsException;
import interfaces.Floor;
import interfaces.Space;

public class OfficeFloor implements Floor {

    private static class Node{
        Node next;
        Space office;

        Node(Space space, Node next){
            this.next = next;
            this.office = space;
        }
    }

    private Node head;
    private int size;

    //конструктор может принимать количество офисов на этаже
    public OfficeFloor(int countOfOffices) {
        if(countOfOffices >= 0){
            for (int i = 0; i < countOfOffices; i++) {
                this.addNode(new Node(new Office(), null), i);
                this.size = countOfOffices;
            }
        }
        else{
            throw new SpaceIndexOutOfBoundsException();
        }
    }

    //конструктор может принимать массив офисов этажа
    public OfficeFloor(Space[] offices) {
        for (int i = 0; i < offices.length; i++){
            addNode(new Node(offices[i], null), i);
        }
        this.size = offices.length;
    }

    //метод получения узла по его номеру
    private Node getNode(int index) {
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
        return null;
    }

    //метод добавления узла в список по номеру
    private void addNode(Node node, int index) {
        if (index == 0 || (head == null)) {
            if (head == null) {
                head = new Node(null, null);
                head.next = new Node(node.office, head);
            }
            else {
                head.next = new Node(node.office, head.next);
            }
        }
        else {
            Node current = this.getNode(index - 1);
            current.next = new Node(node.office, current.next);
        }
    }

    //метод удаления узла из списка по его номеру
    private void removeNode(int index) {
        if (head != null) {
            if (index != 0) {
                Node current = getNode(index - 1);
                current.next = current.next.next;
            }
            else if (head.next.next == head){
                head.next = head;
            }
            else {
                Node current = head.next;
                head.next = current.next;
            }
        }
    }

    //метод получения количества офисов на этаже
    public int getSize() {
       return size;
    }

    //метод получения общей площади помещений этажа
    public int getSquare() {
        if (head != null && head.next != head) {
            int result = getNode(0).office.getSquare();
            for (int i = 1; i < size; i++){
                result += getNode(i).office.getSquare();
            }
            return result;
        }
        else {
            return 0;
        }
    }

    //метод получения общего количества комнат этажа
    public int getRooms() {
        if (head != null && head.next != head) {
            int result = getNode(0).office.getRooms();
            for (int i = 1; i < size; i++) {
                result += getNode(i).office.getRooms();
            }
            return result;
        }
        else {
            return 0;
        }
    }

    //метод получения массива офисов этажа
    public Space[] getArraySpaces() {
        if (head != null && size != 0) {
            Space[] result = new Space[this.getSize()];
            for (int i = 0; i < size; i++) {
                result[i] = getNode(i).office;
            }
            return result;
        }
        else {
            return null;
        }
    }

    //метод получения офиса по его номеру на этаже
    public Space getSpace(int number) {
        if ((number > size)||(number < 0)) {
            throw new SpaceIndexOutOfBoundsException();
        }
        return getNode(number).office;
    }

    //метод изменения офиса по его номеру на этаже и ссылке на обновленный офис
    public void setSpace(int index, Space newOffice) {
        if ((index > size)||(index < 0)) {
            throw new SpaceIndexOutOfBoundsException();
        }
        getNode(index).office = newOffice;
    }

    //метод добавления нового офиса на этаже по будущему номеру офиса
    public void addSpace(int index, Space newOffice) {
        if ((index > size) ||(index < 0)) {
            throw new SpaceIndexOutOfBoundsException();
        }
        addNode(new Node(newOffice,null), index);
        size++;
    }

    //метод удаления офиса по его номеру на этаже
    public void deleteSpace(int index) {
        if ((index > size)||(index < 0)) {
            throw new SpaceIndexOutOfBoundsException();
        }
        removeNode(index);
        size--;
    }

    //метод получения самого большого по площади офиса
    public Space getBestSpace() {
        if (head != null && head.next != head) {
            Space bestSpace = getSpace(0);
            for (int i = 1; i < size; i++) {
                if (bestSpace.getSquare() < getNode(i).office.getSquare()) {
                    bestSpace = getNode(i).office;
                }
            }
            return bestSpace;
        }
        else {
            return null;
        }
    }
}