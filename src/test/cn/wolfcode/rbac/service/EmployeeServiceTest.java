package cn.wolfcode.rbac.service;

import cn.wolfcode.rbac.domain.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class EmployeeServiceTest {
    @Autowired
    private iEmployeeService employeeService;
    @Test
    public void selectAll(){
        List<Employee> employeeList = employeeService.selectAll();
        for (Employee employee:employeeList){
            System.out.println(employee);
        }
    }

    @Test
    public void delete(){
        employeeService.deleteById(6L);
    }
    @Test
    public void saceOrUpdate(){
        Employee employee = new Employee();
        employee.setId(2L);
        employee.setName("杨总");
        employee.setEmail("yang@qq.com");
//        employee.setAge(18L);
//        employee.setDept_id(5L);
        employeeService.saveOrUpdate(employee);
    }

}