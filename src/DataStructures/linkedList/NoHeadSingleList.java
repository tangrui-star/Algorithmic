package DataStructures.linkedList;

import javax.swing.*;

public class NoHeadSingleList {
    public static void main(String[] args) {
        // 创建节点
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);


    }
}

// 定义节点 Node
class Node {
    // 节点数据
    int data;
    // 下一节点地址
    Node next;

    public Node(int data) {
        this.data = data;
    }
    
}