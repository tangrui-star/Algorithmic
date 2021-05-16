package DataStructures.linkedList;

public class Josephu {
    public static void main(String[] args) {
        // 测试
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(5);
        circleSingleLinkedList.showBoy();

        //测试出圈
        circleSingleLinkedList.countBoy(1, 2, 5); // 2 4 1 5 3
    }
}


// 创建一个环形的单向链表
class CircleSingleLinkedList {
    // 创建一个first 节点，当前没有编号
    private Boy first = null;
    // 添加节点，构建环形链表
    public void addBoy(int nums) {
        // nums 做一个数据校验
        if(nums < 1) {
            System.out.println("nums的值不正确");
            return;
        }
        Boy curBoy = null; // 辅助指针，帮助构建环形链表
        // 使用for来创建我们的环形链表
        for (int i = 1; i <= nums; i++ ) {
            // 根据编号，创建节点
            Boy boy = new Boy(i);
            // if 是第一个节点
            if (i == 1) {
                first = boy;
                first.setNext(first);  // 构成环
                curBoy = first;        // 让curBoy
            } else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
    }


    // 遍历当前环形链表
    public void showBoy() {
        // 判断链表是否为空
        if (first == null) {
            System.out.println("没有任何节点~~");
            return;
        }
        // first 不能动，因此我们仍然使用一个辅助指针完成遍历
        Boy curBoy = first;
        while (true) {
            System.out.printf("boy节点编号 %d \n",curBoy.getNo());
            if (curBoy.getNext() == first) {  // 说明已经遍历完毕
                break;
            }
            curBoy = curBoy.getNext(); // curBoy 后移
        }
    }


    //  根据用户的输入，计算出小孩出圈的顺序
    /*
    * @paran startNo 表示从第几个开始数数
    *        countNum 数几下
    *        nums 表示最初有多少个节点在圈中
    * */
    public void countBoy(int starNo, int countNum , int nums) {
        // 先对数据进行校验
        if (first == null || starNo < 1 || starNo > nums) {
            System.out.println("参数输入有误，请重新输入");
            return;
        }
        // 创建要给辅助 指针 ，帮助完成小孩出圈
        Boy helper = first;
        // 需求创建一个辅助指针 helper ，事先应该指向环形链表的最后这个节点
        while (true) {
            if (helper.getNext() == first) {
                break;
            }
            helper = helper.getNext();
        }
        // 小孩报数前，先让first和helper移动 k-1次
        for (int j = 0; j < starNo -1;j++){
            first = first.getNext();
            helper = helper.getNext();
        }
        // 出圈 循环操作
        while(true) {
            if (helper == first) { // 说明圈中只有一个节点
                break;
            }
            // 让 first 和 helper 指针同时 移动 countNum -1
            for (int j = 0; j < countNum -1;j++){
                first = first.getNext();
                helper = helper.getNext();
            }
            // 这时first指向的节点，就是要出圈的小孩节点
            System.out.printf("小孩 %d 出圈\n",first.getNo());
            // 将该小孩节点出圈
            first = first.getNext();
            helper.setNext(first);
        }
        // 遍历结束
        System.out.printf("圈中剩下小孩编号 %d \n",first.getNo());

    }

}




// 创建一个 Boy类，表示一个节点
class Boy {
    private int no; // 编号
    private Boy next;  // 指向下一个节点，默认null

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}