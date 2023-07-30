package com.gqs.test;

/**
 * @author guoqiaosen
 * @date 2022/7/7
 */
public class MyLinkedList {

    MyLinkedList() {
        this.size = 0;
        this.head = new Node(1);
    }

    private int size;
    private Node head;// 哨兵节点

    public static void main(String[] args) {
        final MyLinkedList myLinkedList = new MyLinkedList();
        System.out.println("get() ---> " + myLinkedList.get(0));
    }

    public int get(int index) {
        if (index < 0 || index >= size) {
            return -1;
        }
        Node curr = head;
        for (int i = 0; i < index + 1; i++) {
            curr = curr.next;
        }
        return curr.val;
    }

    public void addAtHead(int val) {
        addAtIndex(0, val);
    }

    public void addAtTail(int val) {
        addAtIndex(size, val);
    }

    public void addAtIndex(int index, int val) {
        if (index > size) {
            return;
        }
        if (index < 0) {
            index = 0;
        }
        ++size;

        Node pred = head;
        for (int i = 0; i < index; i++) {
            pred = pred.next;
        }
        Node toAdd = new Node(val);
        toAdd.next = pred.next;
        pred.next = toAdd;
    }

    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) {
            return;
        }

        Node pre = head;
        for (int i = 0; i < index; i++) {
            pre = pre.next;
        }
        pre.next = pre.next.next;
        --size;
    }

}

class Node {
    int val;
    Node next;

    Node(int val) {
        this.val = val;
    }
}