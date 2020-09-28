/* In this project we are trying to test the two way linked list
 * by adding an extra data field in the node class. By adding the
 * "previous" data field, we are basically enabling a two way connections
 * between each of the nodes compered to a single forward way.
 * Each node is able to communicate with both the previous and the next element
 * of their neighbour nodes.
 * Arman Sadeghi
 */
package mylinkedlist;

import java.util.Scanner;

public class TwoWayLinkedList<E>{

    private Node<E> head,tail;
    private int size = 0;

    /** Create an empty list */
    public TwoWayLinkedList(){
    }

    /** Create a linked list form an array of objects */
    public TwoWayLinkedList(E[] objects) {
        for (int i = 0; i < objects.length; i++)
            add(size - 1, objects[i]);
    }

    /** Return the head element in the list */
    public E getFirst(){
        if (size == 0){
            return null;
        }
        else
            return head.element;
    }

    /** Return the last element in the list */
    public E getLast(){
        if (size == 0){
            return null;
        }
        else
            return tail.element;
    }

    /** Add an element to the beginning of the list */
    public void addFirst(E e){
        Node<E> newNode = new Node<>(e); // create a new node
        newNode.next = head; // link the new node with the head
        head = newNode; // head points to the new node
        size ++; // increment the size

        if (tail == null){ // if the new node is the only node in the list
            tail = head;
        }

        if (head != tail){
            head.next.previous = head; // update the head previous
        }
    }

    /** Add an element to the end of the list */
    public void addLast(E e){
        Node<E> newNode = new Node<>(e); // create a new node
        Node<E> temp = tail;

        if (tail == null){
            head = tail = newNode; // only one node in the list
        }
        else {
            tail.next = newNode; // link the new node with the last node
            tail = tail.next; // tail now points to the last node
        }
        size ++;
        tail.previous = temp;
    }

    /** Add a new element at the specified index in this list.
     *  The index of the head element is 0 */
    public void add(int index, E e){
        if (index == 0){ // insert first
            addFirst(e);
        }
        else if (index >= size){ // insert last
            addLast(e);
        }
        else { // insert middle
            Node<E> current = head;
            for (int i = 1; i < index; i++){
                current = current.next;
            }
            Node<E> temp = current.next;
            current.next = new Node<>(e);
            (current.next).next = temp;
            size ++;
            temp.previous = current.next;
            current.next.previous = current;
        }
    }

    /**
     * Remove the head node and return the object
     * that is contained in the removed node. */
    public E removeFirst(){
        if (size == 0){
            return null;
        }
        else {
            Node<E> temp = head;
            head = head.next;
            size --;
            if (head == null){
                tail = null;
            }
            return temp.element;
        }
    }

    /** Remove the last node and return the object
     * that is contained in the removed node. */
    public E removeLast(){
        if (size == 0){ // nothing to remove
            return null;
        }
        else if (size == 1){ // only one element in the list
            Node<E> temp = head;
            head = tail = null;
            size = 0;
            return temp.element;
        }
        else { // size > 1
            Node<E> current = head;
            for (int i = 0; i < size - 2; i++){
                current = current.next;
            }
            Node<E> temp = tail;
            tail = current; // move tail to second to last
            tail.next = null; // last node is destroyed
            size--;
            return temp.element;
        }
    }

    /** Remove the element at the specified position in this list. Return the element
     * that was removed from the list. */
    public E remove(int index){
        if (index < 0 || index >= size){
            return null;
        }
        if (index == 0){
            return removeFirst();
        }
        else if (index == size -1){
            return removeLast();
        }
        else {
            Node<E> previous = head;
            for (int i = 1; i < index; i++){
                previous = previous.next;
            }
            Node<E> current = previous.next;
            previous.next = current.next;
            current.next.previous = previous;
            size --;
            return current.element;
        }
    }

    @Override
    /** Override toString() to return elements in the list */
    public String toString() {
        StringBuilder result = new StringBuilder("[");

        Node<E> current = head;
        for (int i = 0; i < size; i++) {
            result.append(current.element);
            current = current.next;
            if (current != null) {
                result.append(", "); // Separate two elements with a comma
            } else {
                result.append("]"); // Insert the closing ] in the string
            }
        }
        return result.toString();
    }

    /** Clear the list */
    public void clear(){
        head = tail = null;
    }

    /** Return true if this list contains the element e */
    public boolean contains(E e){
        Node<E> current = head;
        while (current != null){
            if (e.equals(current.element)){
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /** Return the element at the specified index */
    public E get (int index){
        Node<E> current = head; // set the current node to head
        for (int i = 0; i < index; i ++){
            current = current.next;
        }
        if (current != null){
            return current.element;
        }
        else
            return null;
    }

    /** Return the index of the head matching element in this list.
     * Return −1 if no match. */
    public int indexOf(E e){
        Node<E> current = head;
        int index = 0;
        while (current != null){
            if (current.element.equals(e)){
                return index;
            } else { // if not found go to the next element
                current = current.next;
                index++;
            }
        }
        return -1;
    }

    /** Return the index of the last matching element in this list.
     * Return −1 if no match. */
    public int lastIndexOf(E e){
        Node<E> current = tail;
        int index = size - 1;
        while (current != null && e.equals(current.element)){
            current = current.previous;
            index --;
        }
        if (current == null){
            return -1;
        }
        return index;
    }

    /** Replace the element at the specified position in this
     * list with the specified element. */
    public E set(int index, E e){
        if (index > size){
            return null;
        }
        Node<E> current = head;
        while (index > 0){
            current = current.next;
            index--;
        }
        E temp = current.element;
        current.element = e;
        return temp;
    }

    public java.util.ListIterator<E> iterator(){
        return new TwoWayLinkedListIterator();
    }

    private class TwoWayLinkedListIterator implements java.util.ListIterator<E> {
        private Node<E> current = head;
        int index = 0;

        //Constructor
        public TwoWayLinkedListIterator(){}

        public TwoWayLinkedListIterator(int index){
            if (index < 0 || index > size){
                throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
            }
            for (int idx1 = 0; idx1 < index; idx1++){
                current = current.next;
            }
        }

        @Override
        public boolean hasNext() {
            return (current != null);
        }

        @Override
        public boolean hasPrevious(){
            return (current != head);
        }

        @Override
        public E next() {
            E e = current.element;
            current = current.next;
            return e;
        }

        @Override
        public E previous() {
            E e = current.element;
            current = current.previous;
            return e;
        }

        @Override
        public int nextIndex() {
            return index;
        }

        @Override
        public int previousIndex() {
            return index - 1;
        }

        @Override
        public void remove(){
            Node<E> temp1 = current.previous;
            Node<E> temp2 = current.next;
            temp1.next = temp2; //Update next
            temp2.previous = temp1; //Update previous
            current = temp1; //Update present
            index--;
        }

        @Override
        public void set(E e) {
            current.element = e;
        }

        @Override
        public void add(E e) {
            Node<E> temp1 = current.previous;
            Node<E> temp2 = new Node(e);
            Node<E> temp3 = current;
            temp1.next = temp2;
            temp2.next = temp3;
            temp3.previous = temp2; //Update previous
            temp2.previous = temp1; //Update previous
            index++;
        }
    }

    private static class Node<E> {
        E element;
        Node<E> next;
        Node<E> previous;
        public Node(E element){
            this.element = element;
        }
    }

    public static void main(String[] args) {
        //TwoWayLinkedList<Object> list = new TwoWayLinkedList<>();
        //java.util.ListIterator<Object> iterator = new list.iterator();
        TwoWayLinkedList<Object> list = new TwoWayLinkedList<Object>();
        java.util.ListIterator<Object> TwoWayList = list.iterator();

        /* you can uncomment these if you wish to have a not empty list
        list.addFirst("America");
        list.addFirst("Brazil");
        list.addFirst("Argentina");
        list.addFirst("Italy");
        list.addFirst("Japan");
        list.addFirst("Australia");
        list.addFirst("China");
        list.addFirst("Mexico");
        list.addFirst("Iran");
        list.addFirst("Turkey");
        System.out.println("The current list is:");
        System.out.println(list.toString());
        */

        Scanner input = new Scanner(System.in);
        System.out.println("\nEnter (1) to getFirst, (2) to getLast, (3) to addFirst, (4) to addLast\n" +
                "Enter (5) to add, (6) to removeFirst, (7) to removeLast, (8) to remove\n" +
                "Enter (9) to clear list, (10) to see if the list contains element\n" +
                "Enter (11) to getIndex of element, (12) to replace the element at a specific index\n" +
                "Enter (13) get element at a specific index, (14) to get size of the list\n");
        System.out.print("Choose what you would like to do with you list (1 - 14) enter -1 to quit: ");
        int option = input.nextInt();

        while (option != -1){ // continue until the user enters -1
            if (option == 1){
                System.out.println("First element is: " + list.getFirst());
            } else if (option == 2){
                System.out.println("Last element is: " + list.getLast());
            } else if (option == 3){
                System.out.print("What value or String would you wish to add to the beginning of the list: ");
                Object object = input.next();
                list.addFirst(object);
                System.out.println("List has been updated.");
            } else if (option == 4){
                System.out.print("What value or String would you wish to add to the end of your list: ");
                Object object = input.next();
                list.addLast(object);
                System.out.println("List has been updated.");
            } else if (option == 5){
                System.out.print("What would you like to add, provide index and element respectively: ");
                int index = input.nextInt();
                Object object = input.next();
                System.out.print(object + " has been added at index " + index);
                list.add(index, object);
            } else if (option == 6){
                System.out.println(list.getFirst() + " has been removed from list.");
                list.removeFirst();
            } else if (option == 7){
                System.out.println(list.getLast() + " has been removed from list.");
                list.removeLast();
            } else if (option == 8){
                System.out.print("Enter the index of the element you wish to remove: ");
                int index = input.nextInt();
                while (index > list.size - 1){
                    System.out.print("Entered index does not exist! Try again: ");
                    index = input.nextInt();
                }
                System.out.println(list.get(index) + " hes been removed from list");
                list.remove(index);
            } else if (option == 9){
                list.clear();
                System.out.println("Your list has been cleared.");
            } else if (option == 10){
                System.out.print("Enter the element you wish to see if the list contains it: ");
                Object element = input.next();
                if (list.contains(element)){
                    System.out.println("Yes! The element you searched for exists in the list.");
                } else
                    System.out.println("No! The element you searched for was not found");
            } else if (option == 11){
                System.out.print("Enter the element you wish to know the index: ");
                Object element = input.next();
                System.out.printf("Index of entered element is: %d\n" , list.indexOf(element));
            } else if (option == 12){
                System.out.print("Enter the index and the element you wish to replace with respectively: ");
                int index = input.nextInt();
                Object element = input.next();
                System.out.print("Element at index " + index + " (" + list.get(index) + ")" +
                        " was replaced with (" + element + ")");
                list.set(index, element);
            } else if (option == 13){
                System.out.print("Enter the index of the element you wish to find: ");
                int index = input.nextInt();
                System.out.println("The element at the specified index is: " + list.get(index));
            } else if (option == 14){
                System.out.println("Size of the list is: " + list.size);
            } else if (option == 15){
                System.out.print("Enter an element you wish to know the last index: ");
                Object object = input.next();
                System.out.println("Last index of element: " + list.lastIndexOf(object));
            }
            else if (option == 0){
                System.out.println("The result list is: \n" + list.toString());
            } else {
                System.out.print("Invalid input, try again ");
            }
            System.out.print("\nChoose what you would like to do with you list (1 - 14) enter -1 to quit\n");
            System.out.print("or enter 0 to show list: ");
            option = input.nextInt();
        }
        System.out.println("\nFinal list is: \n" + list.toString());
    }
}
