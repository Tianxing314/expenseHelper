package com.tianxing_li.expense.adt;

import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.LockSupport;

//public class PhotoList {
//    private int size;
//    private Node head;
//    private Node tail;
//
//    public PhotoList() {
//        this.size = 0;
//    }
//
//    class Node {
//        Photo photo;
//        Node next;
//    }
//
//    public void add(Photo p) {
//        if (this.head==null) {
//            this.head = new Node();
//            this.head.photo = p;
//            this.tail = head;
//            this.size++;
//        } else if (this.size() == 3) {
//            this.tail.photo = p;
//        } else {
//            Node newNode = new Node();
//            newNode.photo = p;
//
//            Node current = this.head;
//            while (current.photo.getStatus()!=1) {
//                current = current.next;
//            }
//            current.photo = p;
//            current.next = new Node();
//            current.next.photo = new Photo();
//            this.tail = current.next;
//            size++;
//        }
//    }
//
//    public Photo get(int position) {
//        Node temp = head;
//        for (int i=0; i<position; i++) {
//            temp = temp.next;
//        }
//        return temp.photo;
//    }
//
//    public void remove(int position) {
//        if (position==2) {
//            this.tail = new Node();
//            this.tail.photo = new Photo();
//        } else if (position==0) {
//            this.head = this.head.next;
//            Node newTail = new Node();
//            newTail.photo = new Photo();
//            this.tail.next = newTail;
//            this.tail = this.tail.next;
//        } else {
//
//        }
//    }
//
//    public int size() { return this.size; }
//
//    public static void main(String[] args) {
//        String s = new String();
//        System.out.println(s);
//        ArrayList<Integer> list = new ArrayList<>();
//        System.out.println(list.toString());
//
//    }
//}

public class PhotoList {
    private int size;
    private Photo[] list;

    public PhotoList() {
        this.list = new Photo[3];
        this.list[0] = new Photo();
        this.size = 1;
    }

    public int size() {
        return this.size;
    }

    public Photo get(int position) {
        return this.list[position];
    }

    public void add(Photo p) {
        switch (size) {
            case 1:
                list[0] = p;
                list[1] = new Photo();
                size++;
                break;
            case 2:
                list[1] = p;
                list[2] = new Photo();
                size++;
                break;
            case 3:
                list[2] = p;
                break;
        }
    }

    public void add(int position, Photo p) {

    }

    public void remove(int position) {
        switch (position) {
            case 2:
                list[position] = new Photo();
                break;
            case 1:
                list[position] = list[position + 1];
                if (list[position].getStatus()==Photo.NEW) {
                    list[position + 1] = null;
                    size--;
                }
                else
                    list[position+1] = new Photo();
                break;
            case 0:
                if (list[position+1].getStatus()==Photo.NEW) {
                    list[position] = new Photo();
                    list[position+1] = null;
                    size--;
                } else {
                    for (int i=0; i<2; i++)
                        list[i]=list[i+1];
                    if (list[2].getStatus()==Photo.NEW) {
                        size--;
                    }
                    else
                        list[2] = new Photo();
                }
        }
    }
}
