package DataStructures.linkedList;

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
        // 加入数据
/*        singleLinkedList.add(hero1);
        singleLinkedList.add(hero2);
        singleLinkedList.add(hero3);
        singleLinkedList.add(hero4);*/

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
        singleLinkedList.delete(4);
        singleLinkedList.delete(4);
        singleLinkedList.delete(2);



        singleLinkedList.list();

    }
}

// 定义SingleLinkedList 管理英雄节点
class SingleLinkedList {
    // 先初始化一个头节点，头节点不要动，不存放具体的数据
    private HeroNode head = new HeroNode(0, "","");

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
