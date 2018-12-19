package buildings.office;

import exceptions.SpaceIndexOutOfBoundsException;
import interfaces.Floor;
import interfaces.Space;

import java.io.*;
import java.util.Iterator;

public class OfficeFloor implements Floor, Serializable, Cloneable {

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

    private void betrayTail() {
        if (head != null && head.next != null) {
            Node current = head;
            while (current.next != head) {
                current = current.next;

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
        head = new Node(null, head);
        tail = head;
        for (int i = 0; i < offices.length; i++) {
            tail.next = new Node(offices[i], head);
            size++;
            betrayTail();
        }
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
    private void addNode(Node node, int index) {
        if(checkIndex(index)){
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
    }

    //метод удаления узла из списка по его номеру
    private void removeNode(int index) {
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
            }
        }
    }

    //метод получения количества офисов на этаже
    public int getSize() {
       return size;
    }

    //метод получения общей площади помещений этажа
    public int getSquare() {
        int result = 0;
        Node current = head;
        do{
            current = current.next;
            result += current.office.getSquare();
        } while (current.next != head);
        return result;
    }

    //метод получения общего количества комнат этажа
    public int getRooms() {
        int result = 0;
        Node current = head;
        do{
            current = current.next;
            result += current.office.getRooms();
        } while (current.next != head);
        return result;
    }

    //метод получения массива офисов этажа
    public Space[] getArraySpaces() {
        Space offices[] = new Space[getSize()];
        Node current = head;
        for (int i = 0; i < size; i++) {
            current = current.next;
            offices[i] = current.office;
        }
        return offices;
    }

    //проверка допустимого значения
    //todo нужно его делать как процедуру. Если выбрасывается исключение то в вызывающем методе дальше процесс все равно не пойдет
    //и, там где у тебя метод этот вызывался не нужно будет null возвращать
    private boolean checkIndex(int index){
        if(index > size && index < 0){
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
        int bestSpace = 0;
        Space officeBestSpace = null;
        Node current = head;
        for (int i = 0; i < size; i++) {
            current = current.next;
            if(current.office.getSquare() > bestSpace) {
                bestSpace = current.office.getSquare();
                officeBestSpace = current.office;
            }
        }
        return officeBestSpace;

    }

    @Override
    public String toString(){
        Space[] offices = getArraySpaces(); //todo не надо массивы юзать. Ходи по нодам
        StringBuffer s = new StringBuffer();
        //todo StringBuilder
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
        //todo проверяешь сначала size, а потом каждый элемент последовательно
        if (head == null) {
            if (other.head != null)
                return false;
        } else if (!head.equals(other.head))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
<<<<<<< HEAD
        int hashCode = size;
        for (int i = 0; i < size; i++) {
            hashCode ^= getNode(i).office.hashCode();
        }
        return hashCode;
    }

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

    public String getClassName(){
        return "OfficeFloor";
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
            private Node currentNode = head.next;
            @Override
            public boolean hasNext() {
                return currentNode != head;
            }

            @Override
            public Space next() {
                Space toReturn = currentNode.office;
                currentNode = currentNode.next;
                return toReturn;
            }
        };
    }

=======
        final int prime = 31;
        int result = 1;
        result = prime * result + ((head == null) ? 0 : head.hashCode());
        //todo в вычислении хэшкода участвуют все элементы
        return result;
    }
    //todo clone()
>>>>>>> ad9b837a97c31a232edcdd253468da34758c7593
}
