package DataStructures.linkedList;

import java.awt.*;
import java.util.Stack;

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        // 进行测试
        // 先创建节点
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        // 创建 单链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero3);
        // 测试反转单链表
        System.out.println("原来单链表情况");
        singleLinkedList.list();

//        System.out.println("反转之后的情况");
//        reversetList(singleLinkedList.getHead());
//        singleLinkedList.list();

        // 测试逆序打印
        System.out.println("测试逆序打印单链表");
        reversePrint(singleLinkedList.getHead());


        // 加入数据
/*        singleLinkedList.add(hero1);
        singleLinkedList.add(hero2);
        singleLinkedList.add(hero3);
        singleLinkedList.add(hero4);*/

        /*
        // 加入按照编号顺序
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero3);

        System.out.println("修改前");
        singleLinkedList.list();
        HeroNode newHero2 = new HeroNode(2, "卢", "玉麒麟~~");
        singleLinkedList.update(newHero2);
        System.out.println("修改后");
        // 显示
        singleLinkedList.list();
        System.out.println("删除后的节点");
        singleLinkedList.delete(1);
//        singleLinkedList.delete(4);
//        singleLinkedList.delete(4);
//        singleLinkedList.delete(2);

        singleLinkedList.list();

        // 测试，求单链表的有效节点个数
        System.out.println(getLength(singleLinkedList.getHead()));
        // 测试，查找单链表中的倒数第 k 个节点
        System.out.println("倒数第 2 个元素是："+findLastIndexNode(singleLinkedList.getHead(), 2));
*/
    }


    // 从尾到头打印单链表
    // 方式2：利用栈这个数据结构，将各个节点压入到栈中，然后利用栈的先进后出的特点，就实现了逆序打印的效果
    public static void reversePrint(HeroNode head) {
        if (head.next == null) {
            return; // 空 不可打印
        }
        // 创建一个栈，将各个节点压入栈
        Stack<HeroNode> stack = new Stack<HeroNode>();
        HeroNode cur = head.next;
        // 将链表的所有节点压入栈
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;// cur 后移，进而压入下一个节点
        }
        // 将栈打印输出，pop
        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }
    }

    // 将单链表反转：
    public static void reversetList(HeroNode head) {
        // 判断为空 或者 只有一个节点
        if (head.next == null || head.next.next == null) {
            return ;
        }
        // 定义一个辅助变量，遍历
        HeroNode cur = head.next;
        HeroNode next = null;  // 指向当前节点【cur】 的下一个节点
        HeroNode reverseHead = new HeroNode(0, "", "");
        // 遍历原链表，每遍历一个节点，取出一个节点，并放在新链表的最前端
        while (cur != null) {
            next = cur.next; // 先暂时保存当前节点的下一个节点，因为后面需要使用
            cur.next = reverseHead.next; // 将cur的下一个节点指向新的链表的最前端
            reverseHead.next = cur;  // 将 cur 连接到新的节点上
            cur = next; // cur 后移
        }
        // 将head.next 指向reverseHead.next 实现单链表的反转
        head.next = reverseHead.next;

    }




    // 查找单链表中的倒数第 k 个节点
    // 思路：1.编写一个方法，接收head节点，同时接收一个index
    // 2. index表示是倒数第index个节点
    // 3. 先把链表从头到尾遍历，得到链表的长度 getLength
    // 4. 得到size，遍历找到 size-index的节点
    // 5. 如果找到给节点，返回该节点。否则返回空
    public static HeroNode findLastIndexNode(HeroNode head,int index) {
        // 判断 链表是否 空 空-null
        if (head.next == null) {
            return null;
        }
        // 第一次遍历得到链表长度（节点个数）
        int size = getLength(head);
        // 第二次遍历 size-index 位置，既 倒数第k个节点
        // 先做校验：index
        if (index <= 0 || index > size) {
            return null;
        }
        // 定义辅助变量
        HeroNode cur = head.next;
        for (int i = 0; i < size - index; i++) {
            cur = cur.next;
        }
        return cur;
    }



    // 方法：获取到单链表的节点的个数（如果是带头节点的链表不计头节点）
    public static int getLength(HeroNode head) {
        if (head.next == null) {// 空链表
            return 0;
        }
        int length = 0;
        //辅助变量
        HeroNode cur = head.next;
        while (cur != null) {
            length++;
            cur = cur.next; // 遍历
        }
        return length;
    }

}

// 定义SingleLinkedList 管理英雄节点
class SingleLinkedList {
    // 先初始化一个头节点，头节点不要动，不存放具体的数据
    private HeroNode head = new HeroNode(0, "","");

    // 返回头节点
    public HeroNode getHead() {
        return head;
    }

    // 添加节点到单向链表
    // 思路<不考虑编号顺序>：1. 找到当前链表最后节点 2. 将最后这个节点的next，指向新的节点
    public void add(HeroNode heroNode) {
        // 因为 head 节点不能动，因此我们需要一个辅助遍历 temp 获得 头节点
        HeroNode temp = head;
        while (true) {
            // 找到链表的最后 next = null
            if (temp.next == null ) {
                break;
            }
            // 如果没有找到，将temp.next 后移一节点
            temp = temp.next;
        }
        // 当退出while循环时，temp就指向了链表的最后
        // 将最后这个节点的next，指向新的节点
        temp.next = heroNode;

    }

    // 第二种方式添加英雄：排名加到对应的位置（排名存在，提示添加失败）
    public void addByOrder(HeroNode heroNode) {
        // 因为 head 节点 任然不能动，因此我们需要一个辅助遍历 temp 获得 头节点
        // 找到要加入链表位置前一个节点
        HeroNode temp = head;
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
        }
    }


    // 修改节点的信息，根据no编号来修改，no编号不能改
    public void update(HeroNode newHeroNode) {
        // 判断是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //找到要改的节点，根据no编号修改,同样定义辅助变量
        HeroNode temp = head.next;
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
            System.out.printf("没有找到 编号 %d 的节点，不能修改\n",newHeroNode.no);
        }
    }

    // 删除节点
    // 思路：1. head 不能动，辅助变量temp，找到待删除节点的前一个节点
    //      2. 说明我们在比较时，是temp.next.no 和需要删除的节点的no 比较
    public void delete(int no) {
        HeroNode temp = head;
        boolean flag = false;
        while (true) {
            if (temp.next == null) { // 已经到链表的最后了
                break;
            }
            if (temp.next.no == no) {
                // 找到待删除节点的前一个节点
                flag = true;
                break;
            }
            temp = temp.next;//遍历后移
        }
        // 判断flag
        if (flag) {
            // 找到
            temp.next = temp.next.next;
        } else {
            System.out.printf("要删除的 %d 节点不存在\n",no);
        }
    }

    // 显示链表[遍历]
    public void list() {
        // 判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        // 因为 head 节点不能动，因此我们需要一个辅助遍历 temp 获得 头节点
        HeroNode temp = head.next;
        while (true) {
            // 判断是否到链表最后
            if (temp == null){
                break;
            }
            // 输出节点的信息
            System.out.println(temp);
            // 将temp后移，一定小心
            temp = temp.next;
        }
    }


}

// 定义 HeroNode，每个heroNode 对象就是一个节点
class HeroNode {
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;  // 指向下一个节点

    // 构造器
    public HeroNode(int no, String name, String nickname) {
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
