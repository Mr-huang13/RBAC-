package cn.wolfcode.rbac.query;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeQueryObject extends QueryObjet{

    private String keyword;//关键字
    private Long deptId = -1L;//用于查询的部门id
}
