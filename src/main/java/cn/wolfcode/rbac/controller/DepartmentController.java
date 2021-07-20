package cn.wolfcode.rbac.controller;

import cn.wolfcode.rbac.domain.Department;
import cn.wolfcode.rbac.query.PageResult;
import cn.wolfcode.rbac.query.QueryObjet;
import cn.wolfcode.rbac.service.iDepartmentService;
import cn.wolfcode.rbac.util.RequiredPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
//srting MVC框架
//SSM
@Controller
@RequestMapping("/department")
public class DepartmentController{
    @Autowired
    private iDepartmentService departmentService;
    @RequestMapping("/list")
    @RequiredPermission({"部门列表","department:list"})
    public String list(Model model, QueryObjet qo){
//        List<Department> departments =departmentService.selectAll();
        PageResult pageResult = departmentService.query(qo);//带有分页效果的结果集
        model.addAttribute("pageResult",pageResult);
        //请求转发  特点：需要携带参数

        //重定向 特点：不用携带参数
        return "forward:/WEB-INF/views/department/list.jsp";
    }

    @RequestMapping("/delete")
    @RequiredPermission({"部门删除","department:delete"})
    public String delete(Long id){
        departmentService.deleteById(id);
        //重定向
        return "redirect:/department/list";
    }

    @RequestMapping("/input")
    @RequiredPermission({"部门编辑","department:input"})
    public String input(Model model,Long id){
        if(id!=null){
            Department department=departmentService.getById(id);
            model.addAttribute("d",department);
        }
        return "forward:/WEB-INF/views/department/input.jsp";
    }

    @RequestMapping("/saveOrUpdate")
    @RequiredPermission({"部门保存或修改","department:saveOrUpdate"})
    public String saveOrUpdate(Department department){
        departmentService.saveOrUpdate(department);
        return "redirect:/department/list";
    }
}
