package com.gqs.test;

/**
 * 测试恒达出号几率
 *
 * @author 郭乔森
 * @create 2020-02-1522:55
 */
public class HengDa {

    /*
    万千01,12    万百01,23  万十01,34  万个01,45
    千百12, 23  千十12,34  千个12,45
    百十23,34  百个23,45
    十个34,45
     */
    private static final int[][] MODELARR = {{0, 1, 1, 2},
            {0, 1, 2, 3},
            {0, 1, 3, 4},
            {0, 1, 4, 5},
            {1, 2, 2, 3},
            {1, 2, 3, 4},
            {1, 2, 4, 5},
            {2, 3, 3, 4},
            {2, 3, 4, 5},
            {3, 4, 4, 5}};

    private static final String[] MODELNAME = {"万千", "万百", "万十", "万个", "千百", "千十", "千个", "百十", "百个", "十个"};

    public static void main(String[] args) {
        // 分析 和 出现的次数
//        a();
        // 分析 连续交错式出号频率
//        b();
        c();
    }

    /**
     * 分析 始终固定金额押龙的胜率
     */
    private static void c() {
        String[] data = HengDaData.a;


        System.out.println("分析了" + data.length + "组号码");
        System.out.println();

        for (int j = 0; j < MODELARR.length; j++) {
            int count1 = 0;
            int count2 = 0;
            int count3 = 0;
            for (int i = 0; i < 200; i++) {
                String number = data[i];
                int shi = Integer.parseInt(number.substring(MODELARR[j][0], MODELARR[j][1]));
                int ge = Integer.parseInt(number.substring(MODELARR[j][2], MODELARR[j][3]));

                // 龙
                if (shi > ge) {
                    count1 += 1;
                }
                // 和
                else if (shi == ge) {
                    count2 += 1;
                }
                // 龙
                else if (shi < ge) {
                    count3 += 1;
                }
            }
            System.out.println(MODELNAME[j] + " 龙 " + count1 + " 次;  "+ " 和 " + count2 + " 次;  "+ " 虎 " + count3 + " 次;  ");
        }
    }

    /**
     * 分析 连续交错式出号频率
     * 判断一把龙、一把虎、龙、虎。。。这样交错着投连叼多少轮
     */
    private static void b() {
        String[] data = HengDaData.a;

        int interval = 0;
        int max = 0;

        System.out.println("分析了" + data.length + "组号码");
        System.out.println();

        for (int j = 0; j < MODELARR.length; j++) {
            for (int i = 0; i < data.length; i++) {
                String number = data[i];
                int shi = Integer.parseInt(number.substring(MODELARR[j][0], MODELARR[j][1]));
                int ge = Integer.parseInt(number.substring(MODELARR[j][2], MODELARR[j][3]));

                // 龙
                if (i % 2 == 0) {
                    if (shi > ge) {
                        // 计算叼了多少轮
//                        System.out.println("从第" + (interval + 1) + "行开始下注跟了 " + (i - interval + 1) + " 轮才中");
                        // 记录最大叼数
                        if ((i - interval + 1) > max) {
                            max = i - interval + 1;
                        }
                        interval = i + 1;

                    }
                }
                // 虎
                else {
                    if (shi < ge) {
                        // 计算叼了多少轮
//                        System.out.println("从第" + (interval + 1) + "行开始下注跟了 " + (i - interval + 1) + " 轮才中");
                        // 记录最大叼数
                        if ((i - interval + 1) > max) {
                            max = i - interval + 1;
                        }
                        interval = i + 1;

                    }
                }
            }
            System.out.println(MODELNAME[j] + " 最多叼 " + max + "轮");
        }
    }

    /**
     * 分析 和 出现的次数
     */
    private static void a() {
        String[] data = HengDaData.a;
        int repeat = 0;
        int interval = 0;

        System.out.println("分析" + data.length + "组号码");
        for (int i = 0; i < data.length; i++) {
            String number = data[i];
            String shi = number.substring(3, 4);
            String ge = number.substring(4, 5);
            if (shi.equals(ge)) {
                repeat += 1;
//                System.out.println("第" + i + "组，和");

                // 分析出现和的间隔
                System.out.println("出现和的间隔是 " + (i - interval));
                interval = i;
            }

        }
        System.out.println("共" + repeat + "组和");
    }


}


