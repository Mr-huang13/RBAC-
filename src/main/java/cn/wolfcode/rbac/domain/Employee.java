package cn.wolfcode.rbac.domain;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Employee {
    private Long id;
    private String name;
    private String password;
    private String email;
    private Integer age;
    private Boolean admin = false;
//    private Long deptId;
    private Department dept;
//    private String dept;
    private List<Role> roles;//角色类型字段
}
