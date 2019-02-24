package buildings.office;

import java.io.Serializable;

public class Node<T> implements Serializable, Cloneable {

    Node<T> next;
    Node<T> prev;
    T data;

    Node(T data) {
        this.data = data;
    }

    public Node() {

    }

    protected Object clone() throws CloneNotSupportedException {
        Node<T> node = (Node<T>) super.clone();
        node.next = null;
        node.prev = null;
        return node;
    }
}
