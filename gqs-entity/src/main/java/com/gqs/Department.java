package com.gqs;

import lombok.Data;

import java.util.List;

/**
 * 描述:
 * 部门bean
 *
 * @author 郭乔森
 * @create 2019-05-18 13:37
 */
@Data
public class Department {
    private Long deptId;
    private String deptName;

    // ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ 非持久化字段 ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    private List<User> userList;
}
