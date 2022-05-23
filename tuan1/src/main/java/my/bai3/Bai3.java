package my.bai3;

import java.util.*;

public class Bai3 {
    public static void main(String[] args) {
        int size = 10;
        Node head = new Node(11);
        generNode(head, size);
        print(head);

        System.out.println();

        Set<Integer> set = new HashSet<>();
        Node t = head;
        set.add(t.key);
        int i = 0;
        while (t.next.next != null) {
            if (set.contains(t.next.key)) {
                t.next = t.next.next;
//                System.gc();
            } else {
                set.add(t.next.key);
                t = t.next;
            }
        }
        print(head);
    }

    public static void generNode(Node head, int n) {
        Random random = new Random();
        Node p = head;
        Node temp;
        int i = 0, tmp;
        while (i < n) {
            tmp = Math.abs(random.nextInt() % n + 1);
            temp = new Node(tmp);
            p.next = temp;
            p = p.next;
            i++;
        }
    }

    public static void print(Node head) {
        Node p = head;
        while (p.next != null) {
            System.out.print(p.key + " ");
            p = p.next;
        }
    }
}