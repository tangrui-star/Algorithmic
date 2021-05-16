package DataStructures.linkedList;

import java.lang.management.ThreadInfo;

public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        // 双向链表的测试
        System.out.println("双向链表的测试");
        // 先创建节点
        HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 hero4 = new HeroNode2(4, "林冲", "豹子头");
        // 创建一个双向链表
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.add(hero1);
        doubleLinkedList.add(hero2);
        doubleLinkedList.add(hero3);
        doubleLinkedList.add(hero4);

        doubleLinkedList.list();

        // 修改
        HeroNode2 newHeroNode = new HeroNode2(4, "公孙胜", "入云龙");
        doubleLinkedList.update(newHeroNode);
        System.out.println("修改之后的");
        doubleLinkedList.list();

        // 删除
        doubleLinkedList.delete(3);
        System.out.println("删除之后的");
        doubleLinkedList.list();

        // 按编号新增
        // 加入按照编号顺序
        doubleLinkedList.addByOrder(hero1);
        doubleLinkedList.addByOrder(hero3);
        doubleLinkedList.addByOrder(hero4);
        doubleLinkedList.addByOrder(hero2);

        doubleLinkedList.list();

    }
}


// 创建 一个双向链表的类
class DoubleLinkedList {
    // 先初始化一个头节点，头节点不要动，不存放具体的数据
    private HeroNode2 head = new HeroNode2(0, "", "");

    // 返回头节点
    public HeroNode2 getHead() {
        return head;
    }

    // 遍历双向链表的方法
    public void list() {
        // 判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        // 因为 head 节点不能动，因此我们需要一个辅助遍历 temp 获得 头节点
        HeroNode2 temp = head.next;
        while (true) {
            // 判断是否到链表最后
            if (temp == null) {
                break;
            }
            // 输出节点的信息
            System.out.println(temp);
            // 将temp后移，一定小心
            temp = temp.next;
        }
    }

    // 双向链表 加入
    public void add(HeroNode2 heroNode) {
        // 因为 head 节点不能动，因此我们需要一个辅助遍历 temp 获得 头节点
        HeroNode2 temp = head;
        while (true) {
            // 找到链表的最后 next = null
            if (temp.next == null) {
                break;
            }
            // 如果没有找到，将temp.next 后移一节点
            temp = temp.next;
        }

        // 当退出while循环时，temp就指向了链表的最后
        // 将最后这个节点的next，指向新的节点 且将 pre
        temp.next = heroNode;
        heroNode.pre = temp;

    }

    // 第二种方式： 按照编号添加
    public void addByOrder(HeroNode2 heroNode) {
        // 因为 head 节点 任然不能动，因此我们需要一个辅助遍历 temp 获得 头节点
        // 找到要加入链表位置前一个节点
        HeroNode2 temp = head;
        boolean flag = false; // 标识添加的编号是否存在，默认为false 不存在
        while (true) {
            if (temp.next == null) { // 说明temp已经在链表的最后
                break;
            }
            if (temp.next.no > heroNode.no) { // 位置找到，就在temp的后面插入
                break;
            } else if (temp.next.no == heroNode.no) { //准备插入的英雄编号已经存在
                flag = true;
                break;
            }
            temp = temp.next; // 后移，遍历当前的链表
        }
        if (flag) { // 不能加入
            System.out.printf("准备插入的英雄编号 %d 已经存在，不能加入 \n",heroNode.no);
        } else {
            // 插入到链表中，temp的后面
            heroNode.next = temp.next;
            temp.next = heroNode;

            heroNode.pre = temp.pre;
            temp.next.pre = heroNode;

        }
    }

    // 修改 双向链表  和单向链表的修改 是一样的
    public void update(HeroNode2 newHeroNode) {
        // 判断是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //找到要改的节点，根据no编号修改,同样定义辅助变量
        HeroNode2 temp = head.next;
        boolean flag = false; // 表示是否找到该节点
        while (true) {
            if (temp == null) {
                break;// 遍历结束
            }
            if (temp.no == newHeroNode.no) {
                // 找到目标节点
                flag = true;
                break;
            }
            temp = temp.next;
            // 后移遍历
        }
        // 根据 flag 判断是否找到目标节点
        if (flag) {
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
        } else {
            System.out.printf("没有找到 编号 %d 的节点，不能修改\n", newHeroNode.no);
        }
    }


    // 从双向链表中删除一个节点
    public void delete(int no) {
        // 判断是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }

        HeroNode2 temp = head.next;
        boolean flag = false;
        while (true) {
            if (temp.next == null) { // 已经到链表的最后了
                break;
            }
            if (temp.no == no) {
                // 找到待删除节点的前一个节点
                flag = true;
                break;
            }
            temp = temp.next;//遍历后移
        }
        // 判断flag
        if (flag) {
            // 找到
            temp.pre.next = temp.next;
            // bug
            // 如果是最后一个节点，就不需要执行下面这句话，否则空指针异常
            if (temp.next != null) {
                temp.next.pre = temp.pre;
            }
        } else {
            System.out.printf("要删除的 %d 节点不存在\n", no);
        }
    }


}


// 定义 HeroNode2，每个heroNode 对象就是一个节点
class HeroNode2 {
    public int no;
    public String name;
    public String nickname;
    public HeroNode2 next;  // 指向下一个节点 默认 null
    public HeroNode2 pre;  // 指向上一个节点  默认 null

    // 构造器
    public HeroNode2(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
