package com.gqs.example;

import java.util.Arrays;

/**
 * 描述:
 * 算法
 *
 * @author 郭乔森
 * @create 2019-06-16 11:36
 */
public class AlgoExample {

    public static void main(String[] args) {
//        greedyGiveMoney(5);

//        String str = "12,60,-8,99,15,35,17,18,8,10,11,12";
//        int sum = 35;
        String str = "300,821,288,195,140,138,399,700";
        int sum = 1000;
        diguiSum(str,sum);
    }

    /**
     * 钱币找零问题
     *
     * @param money the money
     */
    public static void greedyGiveMoney(int money) {
        System.out.println("需要找零: " + money);
        int[] moneyLevel = {1, 5, 10, 20, 50, 100};
        for (int i = moneyLevel.length - 1; i >= 0; i--) {
            int num = money/ moneyLevel[i];
            int mod = money % moneyLevel[i];
            money = mod;
            if (num > 0) {
                System.out.println("需要" + num + "张" + moneyLevel[i] + "块的");
            }
        }
    }

    public static void diguiSum(String str,int sum) {
        String[] x = str.split(",");
        int[] array = arrayTransform(x,sum);
        for (int i = 0; i < array.length; i++) {
            int[] cache = new int[i + 1];
            cir(-1, i, 0, array, cache, sum);
        }
    }

    // 递归求结果
    public static void cir(int ceng, int cengQuit, int startPiont, int[] array, int[] cache, int sum) {
        ceng++;
        for (int i = startPiont; i < array.length; i++) {
            cache[ceng] = array[i];
            if (ceng == cengQuit) {
                if (getSum(cache) == sum) {
                    printcache(cache);
                }
                if (getSum(cache) > sum) {
                    break;
                }
            }
            if (ceng < cengQuit) {
                startPiont = i + 1;
                cir(ceng, cengQuit, startPiont, array, cache,sum);
            }
        }
    }

    // 获取组合数字之和
    public static int getSum(int[] cache) {
        int sum = 0;
        for (int i = 0; i < cache.length; i++) {
            sum = sum + cache[i];
        }
        return sum;
    }

    // 打印组合的可能
    public static void printcache(int[] cache) {
        for (int i = 0; i < cache.length; i++) {
            System.out.print(cache[i] + ",");
        }
        System.out.println();
    }

    // 转换数组类型 且为提高效率做准备
    public static int[] arrayTransform(String[] strArray,int sum) {
        int length = 0;

        int[] array = new int[strArray.length];
        for (int i = 0; i < strArray.length; i++) {
            array[i] = Integer.valueOf(strArray[i]);
        }
        Arrays.sort(array);
        for (int i = 0; i < array.length; i++) {
            if (array[i] > sum) {
                length = i;
                break;
            }
        }
        int[] dest = new int[length];
        System.arraycopy(array, 0, dest, 0, length);
        return dest;
    }
}
