package Test;

public class Knapsack {

    public int maxSumValue = 0;        //定义满足背包问题子集的最大承重所得的总价值，初始化为0
    /*
     * 数组A的行数为2^n，代表n个物品共有2^n个子集，列数为n。即每一行的排列为一个背包实例
     * 数组weight存放每个物品的具体重量
     * 数组value存放每个物品的具体价值
     * n代表共有n个物品
     * maxWeight表示背包最大承重量
     */
    public void bruteForce(int[][] A,int[] weight,int[] value,int n,int maxWeight){

        for(int i = 0;i < Math.pow(2, n);i++){  //总共有2^n个子集，需要进行2^n次循环，及数组A有2^n行
            int temp1 = i;
            for(int j = 0;j < n;j++){    //数组A有n列，每一列代表一个物品
                int temp2 = temp1%2;
                A[i][j] = temp2;
                temp1 = temp1/2;
            }
        }

        printArray(A,weight,value,maxWeight);

    }

    //输出穷举方案的背包实例的选择物品（0代表不包含该物品，1表示包含该物品）的总重量及总价值，并输出最优实例的总价值
    public void printArray(int[][] A,int[] weight,int[] value,int maxWeight){
        int len1 = A.length;         //二维数组的行数
        int len2 = A[0].length;      //二维数组的列数
        for(int i = 0;i < len1;i++){
            int tempWeight = 0;      //暂时计算当前选中背包实例物品的总重量，初始化为0
            int tempSumValue = 0;    //暂时计算当前选中背包实例物品的总价值，初始化为0
            for(int j = 0;j < len2;j++){
                System.out.print(A[i][j]+" ");
//                if(A[i][j] != 0)
//                    System.out.print(" 物品"+j);
                tempWeight += A[i][j]*weight[j];
                tempSumValue += A[i][j]*value[j];
            }
            System.out.print("\t"+"总重量为："+tempWeight);
            if(tempWeight <= maxWeight)
                System.out.print("\t"+"总价值为："+tempSumValue);
            else
                System.out.print("\t"+"不可行（超出背包最大承重）");
            if(tempWeight <= maxWeight && tempSumValue > maxSumValue)
                maxSumValue = tempSumValue;
            System.out.println();
        }
        System.out.println("穷举查找得知，最优解的总价值为："+maxSumValue);
    }

    public static void main(String[] args){
        Knapsack test = new Knapsack();
        int[][] A = new int[16][4];
        int[] weight = {7,3,4,5};
        int[] value = {42,12,40,25};
        test.bruteForce(A,weight,value,4,10);      //背包的承重最大为10
    }

}