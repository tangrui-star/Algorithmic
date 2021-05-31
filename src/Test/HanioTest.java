package Test;

public class HanioTest {
    public static void main(String[] args) {
        int n = 4;
        char a = 'A',b = 'B',c = 'C';
        hanio(n,a,b,c);
    }

    /**
     *
     * @param n  一共需要移动的盘子
     * @param a  盘子移动的起始柱子
     * @param b  借助的柱子
     * @param c  盘子需要移动到的目标柱子
     */
    public static void hanio(int n,char a, char b, char c){
        //只有一个盘子的时候，就直接从A移到C
        if(n == 1){
            move(n,a,c);
        }else{
            //三步曲，注意观察，a,b,c三个的位置变化
            //1.把 n-1 个盘子看成一个整体，借助 C 从 A 移动到 B
            hanio(n-1,a,c,b);
            //2.把第 n 个盘子从 A 移动到 C
            move(n,a,c);
            //3.再把 n-1 盘子整体，借助 A 从 B 移动到 C
            hanio(n-1,b,a,c);
        }
    }

    public static void move(int n , char a, char b){
        System.out.println("把第"+ n +"个盘子从"+ a +"移到"+ b);
    }
}