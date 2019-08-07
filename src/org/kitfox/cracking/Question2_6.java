package org.kitfox.cracking;

import org.junit.Test;
import org.kitfox.cracking.library.LinkedListNode;

/**
 * @Author Jean-Francois Larouche (resolutech) on 2019-08-06
 */
public class Question2_6 {
    public static boolean isPalindrome(LinkedListNode<Integer> head) {
        LinkedListNode reversed = reverseAndClone(head);
        return isEqual(head, reversed);
    }

    public static LinkedListNode<Integer> reverseAndClone(LinkedListNode<Integer> node) {
        LinkedListNode head = null;
        while (node != null) {
            LinkedListNode newNode = new LinkedListNode(node.data); // Clone
            newNode.next = head;
            head = newNode;
            node = node.next;
        }
        return head;
    }

    public static LinkedListNode reverse(LinkedListNode<Integer> node) {
        if(node == null) {
            return null;
        }
        LinkedListNode current = null;
        LinkedListNode next = node.next;
        while (next != null) {
            node.next = current;
            current = node;
            node = next;
            next = next.next;
        }
        node.next = current;
        return node;
    }

    public static boolean isEqual(LinkedListNode<Integer> one, LinkedListNode<Integer> two) {
        while (one != null && two != null) {
            if (one.data != two.data) {
                return false;
            }
            one = one.next;
            two = two.next;
        }
        return one == null && two == null;
    }

    @Test
    public void testPalindrome() {
        int length = 9;
        LinkedListNode<Integer>[] nodes = new LinkedListNode[length];
        for (int i = 0; i < length; i++) {
            nodes[i] = new LinkedListNode<>(i >= length / 2 ? length - i - 1 : i, null, null);
        }

        setPointers(nodes);
        // nodes[length - 2].data = 9; // Uncomment to ruin palindrome

        LinkedListNode<Integer> head = nodes[0];

        System.out.println(head.printForward());
        System.out.println(isPalindrome(head));
    }

    @Test
    public void testReverse() {
        int length = 9;
        LinkedListNode<Integer>[] nodes = new LinkedListNode[length];
        for (int i = 0; i < length; i++) {
            nodes[i] = new LinkedListNode<>(i, null, null);
        }

        setPointers(nodes);

        LinkedListNode<Integer> head = nodes[0];

        System.out.println(head.printForward());

        System.out.println(reverse(head).printForward());
    }

    @Test
    public void testReverseOne() {
        int length = 1;
        LinkedListNode<Integer>[] nodes = new LinkedListNode[1];
        nodes[0] = new LinkedListNode<>(0, null, null);

        setPointers(nodes);

        LinkedListNode<Integer> head = nodes[0];

        System.out.println(head.printForward());

        System.out.println(reverse(head).printForward());
    }

    private void setPointers(LinkedListNode<Integer>[] nodes) {
        for (int i = 0; i < nodes.length; i++) {
            if (i < nodes.length - 1) {
                nodes[i].setNext(nodes[i + 1]);
            }
            if (i > 0) {
                nodes[i].setPrevious(nodes[i - 1]);
            }
        }
    }
}
