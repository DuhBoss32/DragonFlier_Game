/**
 * This is my Arraylist Class, this is the super/ parent class of my Stack and Queue.
 * Creates a separate class within the class for Nodes of our linked list.
 * Created by: Travis Huynh
 *
 */
public class Arraylist <T> {

    /**
     * This is my inner (private) Node class that creates the data within our nodes and creates the next pointer.
     * This class helps us do everything we need for the Linked List.
     */
    private class Node <T> {
        private T data; //instance variables
        private Node <T> next;

        /**
         * The Constructor for the Node Class.
         *
         * pre: the objects data that we are planting in the node of our linked list.
         * post: value of the data is whatever we put in.
         *
         * @param data
         */

        private Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node <T> head; //instance variable from Node called the Head of the linked List.

    /**
     * Constructor for the Quack Class
     *
     * pre: nothing
     * post: initializes the head as null.
     */

    public Arraylist() {
        this.head = null;
    }

    /**
     * This is the insert method that inserts a data piece for a specific index of our linked list.
     *
     * pre: The specific Object you want and the index
     * post: Checks whether index is valid and places the data within the node, and setups list for new insets.
     *
     * @param newData
     * @param index
     */

    protected void insert(T newData, int index) {
        if (index < 0 || index > size()) {
            System.out.println("Index is out of bounds.");
            return; //return to leave the code
        }
        Node<T> node = new Node<>(newData);
        if (index == 0) {
            node.next = head;
            head = node;
            return;
        }

        Node<T> tempNode = head;
        Node<T> previousNode = null;

        for (int i = index; i > 0; i--) { //iterate the index to see how big the list
            previousNode = tempNode;
            tempNode = tempNode.next;
        }
        previousNode.next = node;
        node.next = tempNode;
    }

    /**
     * This is the remove method, that removes the object at a specific index.
     *
     * pre: the specific index
     * post: removes the object from the list at the specific index, by setting the node to next it does this.
     *
     * @param index
     * @return
     */

    protected Object remove(int index) {
        if (index < 0 || index >= size()) {
            System.out.println("Index is out of bounds.");
        }
        if (index == 0) {
            Node<T> node = this.head;
            this.head = head.next; //removes
            return node.data;
        }
        Node<T> tempNode = head; //node that needs to be deleted
        Node<T> previousNode = null;

        for (int i = index; i > 0; i--) {
            previousNode = tempNode;
            tempNode = tempNode.next;
        }
        previousNode.next = tempNode.next;
        return tempNode.data;
    }

    /**
     * Append method that places the object at the end of the list.
     *
     * pre: Object that you want at the end of the list
     * post: calls the insert method to set the data to the index of the size of the list.
     *
     * @param newData
     */

    protected void add(T newData) {
        this.insert(newData,size());
    }

    /**
     * delete method that asks for specific index to delete
     *
     * pre: the index you want
     * post: deletes the object you want at the specific index.
     *
     * @param index
     */

    protected void delete(int index) {
        if (isEmpty()) {
            System.out.println("List is Empty");
            return;
        }
        if (size() <= index) {
            System.out.println("Invalid Index");
            return;
        }
        if (index == 0) {
            head = head.next;
        }
        else {
            Node<T> temp = head;
            int count = 0;
            while (count < index - 1) {
                temp = temp.next;
                count++;
            }
            temp.next = temp.next.next;
        }
    }

    /**
     * The get method, to grab what object we want from the index
     *
     * pre: the index you want to get at.
     * post: Checks the entire list to check if the index is valid, then returns the data of what matches
     * the index point we want.
     *
     * @param index
     * @return
     */

    protected Object get(int index) {
        int size = size();
        if(index < 0) {
            System.out.println("Index out of bounds.");
        }
        if (head == null) {
            System.out.println("List is Empty");
        }
        if (index < size) {
            Node<T> temp = head;
            int counter = 0;
            while (temp != null) {
                if (counter == index) {
                    return temp.data; //if the counter is equal to index, that is our index
                }
                counter++;
                temp = temp.next;
            }
        }
        else {
            System.out.println("Index out of bounds");
        }
        return null; //return null if the index doesn't exist.
    }

    /**
     * The size method to find out how big our linked list is.
     *
     * pre: nothing.
     * post: returns the size of linked list by counting amount of heads that we have that is not null.
     *
     * @return
     */

    public int size() {
        int size = 0;
        Node<T> tempNode = head;
        while (tempNode != null) {
            tempNode = tempNode.next;
            size++;
        }
        return size;
    }

    /**
     * The toString method, that outputs the linked list for user.
     *
     * pre: nothing
     * post: returns string value for what our list is.
     *
     * @return
     */

    public String toString() {
        String string = "";
        Node<T> current = head;
        while (current != null) {
            string += current.data + " ";
            current = current.next;
        }
        return string;
    }

    /**
     * This is the isEmpty method that checks if the linked list is empty or not.
     *
     * pre: nothing
     * post: returns true or false in whether the list is empty or not.
     *
     * @return
     */

    public boolean isEmpty() {
        if (size() == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * This is the indexOf method, that allows us to grab the index of an item using the object.
     *
     * pre: The object that we are looking for.
     * post: returns the index that the object is in.
     *
     * @param target
     * @return
     */

    protected int indexOf(T target) {
        int index = 0;
        Node<T> temp = head;
        while (temp != null) {
            index++;
            if (temp.data.equals(target)) {
                return index;
            }
            temp = temp.next;
        }
        return -1;
    }

    /**
     * This is my equals method that compares to linked lists together.
     *
     * pre: The other linked list that we are comparing.
     * post: returns true or false if the lists are the same.
     *
     * @param other
     * @return
     */

    public boolean equals(Object other) {
        if (other == null) return false;
        else if (this.getClass() != other.getClass()) return false; //compares the runtime
        else {
            Arraylist o = (Arraylist) other; //initilizes another quack object.
            Node<T> thisPos = this.head;
            Node otherPos = o.head;
            while (thisPos != null && otherPos != null) {
                if (thisPos.data != otherPos.data) {
                    return false;
                }
                else {
                    thisPos = thisPos.next;
                    otherPos = otherPos.next;
                }
            }
            if (thisPos == null && otherPos != null) {
                return false;
            }
            else if (thisPos != null && otherPos == null) {
                return false;
            }
            else {
                return true;
            }
        }
    }

    public void clear() {

    }

    /**
     * This is my main method that tests all the methods of my Quack Class.
     *
     * pre: The preexisting methods that we want to test.
     * post: Outputs the test.
     *
     * @param args
     */

    public static void main(String [] args) {
        Arraylist empty = new Arraylist();
        Arraylist<Integer> one = new Arraylist<>();
        Arraylist<Integer> multiple = new Arraylist<>();

        one.add(5);
        multiple.add(10);
        multiple.add(20);
        multiple.add(30);

        System.out.println("Get method should get 10" +  " : " + multiple.get(0));
        System.out.println(multiple.indexOf(20));
        System.out.println(one.indexOf(5));



        System.out.println("Empty (should print nothing): " + empty);     // ( note the implicit call to toString()! )
        System.out.println("One (should print '5'): " + one);
        System.out.println("Multiple (should print '10, 20, 30'): " + multiple);

        one.delete(0);
        multiple.delete(1);
        System.out.println("One (upon delete) (should be empty): " + one);
        System.out.println("Multiple (upon delete) (should be '10, 30'): " + multiple);

        System.out.println("Attempting an illicit insert at index 5. Error message should print: ");
        one.insert(400, 5);
        System.out.println("One (on insert) (should still be empty): " + one);

    }
}
