package buildings.office;

import exceptions.SpaceIndexOutOfBoundsException;
import interfaces.Floor;
import interfaces.Space;

import java.io.Serializable;

public class OfficeFloor implements Floor, Serializable {

    private static class Node implements Serializable{
        Node next;
        Space office;

        Node(Space space, Node next){
            this.next = next;
            this.office = space;
        }
    }

    private Node head;
    private Node tail;
    private int size;

<<<<<<< HEAD
    //конструктор может принимать количество офисов на этаже
    public OfficeFloor(int countOfOffices) {
        //todo в отличие от массива, здесь не нужно ноды создавать, просто конструктор, который ничего не делает, только head создает.
        //ты же все равно при добавлении space-а создаешь новый нод (листэлемент)
        if(countOfOffices >= 0){
            for (int i = 0; i < countOfOffices; i++) {
                this.addNode(new Node(new Office(), null), i);
                this.size = countOfOffices;
=======
    private void betrayTail() {
        if (head != null && head.next != null) {
            Node current = head;
            while (current.next != head) {
                current = current.next;
>>>>>>> fourth laba with a factory
            }
            tail = current;
        }
    }

    //конструктор может принимать количество офисов на этаже
    public OfficeFloor(int countOfOffices) {
        head = new Node(null, null);
    }

    //конструктор может принимать массив офисов этажа
    public OfficeFloor(Space[] offices) {
<<<<<<< HEAD
        //не эффективно - каждый раз добавляя по i-му номеру ты гуляешь в поисках предыдущего нода i-1 раз.
        //todo лушче бы хранить tail - ссылку на последний нод, и добавлять нод после него
        for (int i = 0; i < offices.length; i++){
            addNode(new Node(offices[i], null), i);
=======
        head = new Node(null, head);
        tail = head;
        for (int i = 0; i < offices.length; i++) {
            tail.next = new Node(offices[i], head);
            size++;
            betrayTail();
>>>>>>> fourth laba with a factory
        }
    }

    //метод получения узла по его номеру
    private Node getNode(int index) {
<<<<<<< HEAD
        //todo вот тут нужно проверку индекса делать и выбрасывать исключение, а не в методах, работающих со Space-ом
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
    private void addNode(Node node, int index) {
<<<<<<< HEAD
        //todo вот тут нужно проверку индекса делать и выбрасывать исключение, а не в методах, работающих со Space-ом
        if (index == 0 || (head == null)) {
            if (head == null) {
                head = new Node(null, null);
                head.next = new Node(node.office, head);
=======
        if(checkIndex(index)){
            if (index == 0 || (head == null)) {
                if (head == null) {
                    head = new Node(null, null);
                    head.next = new Node(node.office, head);
                }
                else {
                    head.next = new Node(node.office, head.next);
                }
>>>>>>> fourth laba with a factory
            }
            else {
                Node current = this.getNode(index - 1);
                current.next = new Node(node.office, current.next);
            }
        }
    }

    //метод удаления узла из списка по его номеру
    private void removeNode(int index) {
<<<<<<< HEAD
        //todo вот тут нужно проверку индекса делать и выбрасывать исключение, а не в методах, работающих со Space-ом
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
=======
        if(checkIndex(index)){
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
>>>>>>> fourth laba with a factory
            }
        }
    }

    //метод получения количества офисов на этаже
    public int getSize() {
       return size;
    }

    //метод получения общей площади помещений этажа
    public int getSquare() {
<<<<<<< HEAD
        //todo нененене getNode(i) здесь вообще не эффективно. Гуляешь по нодам в цикле и каждый раз идешь к следующему по ссылке next. За один проход, а не за n*n как у тебя
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
=======
        int result = 0;
        Node current = head;
        do{
            current = current.next;
            result += current.office.getSquare();
        } while (current.next != head);
        return result;
>>>>>>> fourth laba with a factory
    }

    //метод получения общего количества комнат этажа
    public int getRooms() {
<<<<<<< HEAD
        //todo нененене getNode(i) здесь вообще не эффективно. Гуляешь по нодам в цикле и каждый раз идешь к следующему по ссылке next. За один проход, а не за n*n как у тебя
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
=======
        int result = 0;
        Node current = head;
        do{
            current = current.next;
            result += current.office.getRooms();
        } while (current.next != head);
        return result;
>>>>>>> fourth laba with a factory
    }

    //метод получения массива офисов этажа
    public Space[] getArraySpaces() {
<<<<<<< HEAD
        //todo нененене getNode(i) здесь вообще не эффективно. Гуляешь по нодам в цикле и каждый раз идешь к следующему по ссылке next. За один проход, а не за n*n как у тебя
        if (head != null && size != 0) {
            Space[] result = new Space[this.getSize()];
            for (int i = 0; i < size; i++) {
                result[i] = getNode(i).office;
            }
            return result;
        }
        else {
            return null;
=======
        Space offices[] = new Space[getSize()]; //Space +
        Node current = head;
        for (int i = 0; i < size; i++) {
            current = current.next;
            offices[i] = current.office;
>>>>>>> fourth laba with a factory
        }
        return offices;
    }

<<<<<<< HEAD
    //метод получения офиса по его номеру на этаже
    //todo и почему у тебя в этом методе number, а в следующих index? =))))
    public Space getSpace(int number) {
        //todo одну и ту же проверку даже не дублировал, а квадрировал =))))) вынеси ее в отдельный void метод checkIndex
        if ((number > size)||(number < 0)) {
=======

    //проверка допустимого значения
    private boolean checkIndex(int index){
        if(index > size && index < 0){
>>>>>>> fourth laba with a factory
            throw new SpaceIndexOutOfBoundsException();
        }
        return true;
    }

    //метод получения офиса по его номеру на этаже
    public Space getSpace(int index) {
        return getNode(index).office;
    }

    //метод изменения офиса по его номеру на этаже и ссылке на обновленный офис
    public void setSpace(int index, Space newOffice) {
           getNode(index).office = newOffice;
    }

    //метод добавления нового офиса на этаже по будущему номеру офиса
    public void addSpace(int index, Space newOffice) {
            addNode(new Node(newOffice,null), index);
            size++;
    }

    //метод удаления офиса по его номеру на этаже
    public void deleteSpace(int index) {
            removeNode(index);
            size--;
    }

    //метод получения самого большого по площади офиса
    public Space getBestSpace() {
<<<<<<< HEAD
        if (head != null && head.next != head) {
            Space bestSpace = getSpace(0);
            //todo нененене getNode(i) здесь вообще не эффективно. Гуляешь по нодам в цикле и каждый раз идешь к следующему по ссылке next. За один проход, а не за n*n как у тебя
            for (int i = 1; i < size; i++) {
                if (bestSpace.getSquare() < getNode(i).office.getSquare()) {
                    bestSpace = getNode(i).office;
                }
=======
        int bestSpace = 0;
        Space officeBestSpace = null;
        Node current = head;
        for (int i = 0; i < size; i++) {
            current = current.next;
            if(current.office.getSquare() > bestSpace) {
                bestSpace = current.office.getSquare();
                officeBestSpace = current.office;
>>>>>>> fourth laba with a factory
            }
        }
        return officeBestSpace;

    }

    @Override
    public String toString(){
        Space[] offices = getArraySpaces();
        StringBuffer s = new StringBuffer();
        s.append("OfficeFloor (").append(getArraySpaces().length).append(", ");
        for(int i = 0; i < size; i++) {
            if (i > 0 ){
                s.append(", ");
            }
            s.append(offices[i].toString());
        }
        s.append(")");
        return s.toString();
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof OfficeFloor))
            return false;
        OfficeFloor other = (OfficeFloor) obj;
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
