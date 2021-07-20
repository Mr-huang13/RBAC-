package cn.wolfcode.rbac.service;

import cn.wolfcode.rbac.domain.Employee;
import cn.wolfcode.rbac.query.EmployeeQueryObject;
import cn.wolfcode.rbac.query.PageResult;
import cn.wolfcode.rbac.query.QueryObjet;

import java.util.List;

public interface iEmployeeService {
    List<Employee> selectAll(EmployeeQueryObject qo);
    void deleteById(Long id);
    void saveOrUpdate(Employee employee,Long[] roleIds);

    Employee getById(Long id);
    PageResult query(EmployeeQueryObject qo);

    void queryLogin(Employee employee);

    void logout();
}
