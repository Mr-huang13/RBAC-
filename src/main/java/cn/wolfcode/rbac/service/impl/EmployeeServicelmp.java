package cn.wolfcode.rbac.service.impl;

import cn.wolfcode.rbac.domain.Employee;
import cn.wolfcode.rbac.domain.Permission;
import cn.wolfcode.rbac.mapper.EmployeeMapper;
import cn.wolfcode.rbac.mapper.PermissionMapper;
import cn.wolfcode.rbac.query.EmployeeQueryObject;
import cn.wolfcode.rbac.query.PageResult;
import cn.wolfcode.rbac.query.QueryObjet;
import cn.wolfcode.rbac.service.iEmployeeService;
import cn.wolfcode.rbac.util.LogicException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;

@Service
public class EmployeeServicelmp implements iEmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private PermissionMapper permissionMapper;
    public List<Employee> selectAll(EmployeeQueryObject qo){
        return employeeMapper.selectAll(qo);
    }

    public void deleteById(Long id){
        employeeMapper.deleteById(id);//删除员工信息
        employeeMapper.deleteEmpAndRelation(id);//删除员工和角色关联表的信息
    }

    public void saveOrUpdate(Employee employee,Long[] roleIds){
        if(employee.getId()!=null){
            employeeMapper.updateById(employee);
        }else {
            employeeMapper.saceEmployee(employee);
        }

        //保存当前员工所对应的角色信息----》保存到 员工-角色 的中间表
        if(roleIds!=null){
            for (Long roleId : roleIds) {
                employeeMapper.saceEmpAndRolesRelation(employee.getId(),roleId);
            }
        }
    }
    @Override
    public Employee getById(Long id){
        return employeeMapper.getById(id);
    }

    public PageResult query(EmployeeQueryObject qo){
        //带有分页效果的数据集，总条数
        int totalCount = employeeMapper.queryForCount(qo);
        if(totalCount != 0){
            List<Employee> data = employeeMapper.queryForData(qo);
            return new PageResult(data,totalCount,qo.getCurrentPage(),qo.getPageSize());//全参构造器，有数据的情况
        }
        return new PageResult(qo.getCurrentPage(),qo.getPageSize());//缺参构造器，没有满足条件的数据的情况
    }

    @Override
    public void queryLogin(Employee employee) {
        Employee employee1 =employeeMapper.queryLogin(employee);
        //如果 employee 不为null，说明用户的账号和密码正确
        //把当前的用户信息存到session
        //如果employee为null，去定义一个统一的异常处理
        if(employee1!=null){
            ServletRequestAttributes rqa = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            rqa.getRequest().getSession().setAttribute("EMPLOYEE_IN_SESSION",employee1);

            //1.查询当前用户所拥有的权限表达式
            List<String> expressions=permissionMapper.selectExpressionsByEmpId(employee1.getId());
            //2.把所有的权限表达式存入到session中去
            rqa.getRequest().getSession().setAttribute("EXPRESSION_IN_SESSION",expressions);
        }else {
            throw new LogicException("账号或密码输入错误");
        }

    }

    @Override
    public void logout() {
        ServletRequestAttributes rqa = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        rqa.getRequest().getSession().removeAttribute("EMPLOYEE_IN_SESSION");
    }
}
