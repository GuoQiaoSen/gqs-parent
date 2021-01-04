package com.gqs.test;

import com.google.gson.Gson;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 郭乔森
 * @create 2020-08-19 10:54
 */
public class shuage {
    public static void main(String[] args) {
        String str = "18879169225\tShan246810\n" +
                "15367634223\tqwer123456\n" +
                "18639403088\tszy123456\n" +
                "18785795758\tzh980601\n" +
                "15805657411\tmusic15805657411\n" +
                "19936633006\tsong1998hai\n" +
                "13236892259\txuhongtao1\n" +
                "15964446698\tdongchen759a\n" +
                "18618241175\tqwq123123\n" +
                "18642027048\t0.00.00.\n" +
                "18258860324\t123456\n" +
                "18048379267\tqwe5858056\n" +
                "18718975848\tpsm9490324\n" +
                "18638338903\t38708167a\n" +
                "16607616754\t520liuzhihao...\n" +
                "18006774059\t8219bila";

        String[] split = str.split("\n");
        List<Account> accounts = new ArrayList<Account>();
        for (String sp : split) {
            Account account = new Account();
            String[] split1 = sp.split("\t");
            account.setAccount(split1[0]);
            account.setPassword(split1[1]);
            accounts.add(account);
        }

        Gson gson = new Gson();
        String s = gson.toJson(accounts);
        System.out.println(s);

    }
}

@Data
class Account {
    private String account;
    private String password;
    private String sckey;
}

