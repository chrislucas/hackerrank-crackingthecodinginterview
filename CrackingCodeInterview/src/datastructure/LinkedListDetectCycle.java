package datastructure;


/**
 * https://www.hackerrank.com/challenges/ctci-linked-list-cycle/problem
 * DONE
 * */
public class LinkedListDetectCycle {

    class Node {
        int data;
        Node next;
    }

    private static LinkedListDetectCycle linkedListDetectCycle = new LinkedListDetectCycle();

    public Node head, tail;

    boolean hasCycle(Node head) {
        return s1(head);
    }

    public boolean s1(Node head) {
        Node slow = head, fast = head;
        while( slow != null && fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast)
                return true;
        }
        return false;
    }

    public void add(Node node) {
        if(head == null) {
            tail = node;
            tail.data = node.data;
            head = tail;
        }
        else {
            Node copy = tail;
            tail = node;
            tail.data = node.data;
            copy.next = tail;
        }
    }

    public void removeAll() {
        while (head != null)
            head = head.next;
        tail = null;
    }


    public void start() {

        Node [] nodesA = {
             new Node()
            ,new Node()
            ,new Node()
        };
        nodesA[0].data = 1;
        nodesA[1].data = 2;
        nodesA[2].data = 3;
        for(Node node : nodesA)
            linkedListDetectCycle.add(node);
        System.out.println(linkedListDetectCycle.hasCycle(head));
        removeAll();

        Node [] nodesB = new Node[3];
        nodesB[0] = new Node();
        nodesB[0].data = 1;
        nodesB[1] = new Node();
        nodesB[1].data = 2;
        nodesB[2] = nodesB[0];

        for(Node node : nodesB)
            linkedListDetectCycle.add(node);
        System.out.println(linkedListDetectCycle.hasCycle(head));
    }


    public static void main(String[] args) {
        linkedListDetectCycle.start();
    }

}
