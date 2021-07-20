package cn.wolfcode.rbac.service;

import cn.wolfcode.rbac.domain.Department;
import cn.wolfcode.rbac.mapper.DepartmentMapper;
import cn.wolfcode.rbac.service.impl.DepartmentServicelmpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class DepartmentServiceTest {
    @Autowired
    private iDepartmentService departmentService;
    @Test
    public void delete(){
        departmentService.deleteById(4L);
    }
    @Test
    public void saceOrUpdate(){
        Department department = new Department();
//        department.setId(2L);
        department.setName("山山部");
        department.setSn("008");
        departmentService.saveOrUpdate(department);
    }
}