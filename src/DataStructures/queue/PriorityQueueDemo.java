package DataStructures.queue;

import java.util.Scanner;

public class PriorityQueueDemo {
    public static void main(String[] args) {
        // 目的 求无序序列中第K小的元素；编写一个实验程序，利用STL中的priority_queue(优先队列)求出一个无序整数序列中第K小的元素；
        // 实现步骤：unversioned files
        // 1. 将无序的数据 插入优先队列 中<按照由小到大顺序>
        // 2. 取出第k小的元素-既连续出队 k次
        PriorityQueue priorityQueue = new PriorityQueue();
        // 定义无序数据：
         int unorderData[] = {1,2,9,6,46,67,23,4,234};
         for (int i = 0;i < unorderData.length;i++){
             priorityQueue.push(unorderData[i],unorderData[i]);
         }
         priorityQueue.list();
        System.out.println("请输入 k");
        Scanner sc = new Scanner(System.in);
        int nextInt = sc.nextInt();
        priorityQueue.list(nextInt);
    }
}

class PriorityQueue {
    class Node {
        int value;     // 节点的值
        int priority;  // 节点的优先级
        Node next;     // 节点下一个位置

        public Node(int value, int priority) {
            this.value = value;
            this.priority = priority;
        }
    }

        Node head = null;

        // push :插入一个新的元素
        public void push(int value, int priority) {
            if (head == null) {
                head = new Node(value, priority);
                return;
            }
            // 过度节点
            Node cur = head;
            Node newNode = new Node(value, priority);
            if (head.priority > priority) {
                newNode.next = head;
                this.head = newNode;
            } else {
                while (cur.next != null && cur.next.priority < priority) {
                    cur = cur.next;
                }
                newNode.next = cur.next;
                cur.next = newNode;
            }
        }

        // pop :将优先级最高的元素弹出（删除）
        public Node pop() {
            if (head == null) {
                return null;
            }
            Node temp = head;
            head = head.next;
            return temp;
        }

    // 显示链表[遍历]
    public void list() {
        // 判断链表是否为空
        if (head == null) {
            System.out.println("链表为空");
            return;
        }
        // 因为 head 节点不能动，因此我们需要一个辅助遍历 temp 获得 头节点
        Node temp = head;
        while (true) {
            // 判断是否到链表最后
            if (temp == null){
                break;
            }
            // 输出节点的信息
            System.out.println(temp.value);
            // 将temp后移，一定小心
            temp = temp.next;
        }
    }

    // 显示链表[遍历]
    public void list(int k) {
        // 判断链表是否为空
        if (head == null) {
            System.out.println("链表为空");
            return;
        }
        // 因为 head 节点不能动，因此我们需要一个辅助遍历 temp 获得 头节点
        Node temp = head;
        for (int i=1;i<k;i++){
            // 判断是否到链表最后
            if (temp == null){
                System.out.println("没有第 k 小的元素");
                break;
            }
            // 输出节点的信息
            // 将temp后移，一定小心
            temp = temp.next;
        }
        System.out.printf("第%d小的元素是：%d",k,temp.value);

    }
        // peek: 查看优先级最高的元素数组
        public Node peek() {
            return head;
        }
        public boolean isEmpty() {
            return head == null;
        }

}