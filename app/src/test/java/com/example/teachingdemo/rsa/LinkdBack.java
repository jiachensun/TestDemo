package com.example.teachingdemo.rsa;

/**
 * @Author sjc
 * @Date 2020/7/28
 * Description：链表反转
 * 要求：A->B->C->D，反转：D->C->B->A
 * 思路：重新定义新的链表，遍历
 */
public class LinkdBack {

    public static Node ReverseIteratively(Node head){
        Node pre = null;
        Node next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }
}
