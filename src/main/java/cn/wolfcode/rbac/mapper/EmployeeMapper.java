package cn.wolfcode.rbac.mapper;

import cn.wolfcode.rbac.domain.Employee;
import cn.wolfcode.rbac.query.EmployeeQueryObject;
import cn.wolfcode.rbac.query.QueryObjet;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeMapper {
    List<Employee> selectAll(EmployeeQueryObject qo);
    void deleteById(Long id);
    void saceEmployee(Employee employee);
    void updateById(Employee employee);
    Employee getById(Long id);

    void saceEmpAndRolesRelation(@Param("empId") Long id,@Param("roleId") Long roleId);

    void deleteEmpAndRelation(Long id);

    int queryForCount(EmployeeQueryObject qo);

    List<Employee> queryForData(EmployeeQueryObject qo);


    Employee queryLogin(Employee employee);
}
