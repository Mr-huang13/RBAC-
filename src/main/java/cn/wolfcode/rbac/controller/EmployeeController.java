package cn.wolfcode.rbac.controller;
import cn.wolfcode.rbac.domain.Department;
import cn.wolfcode.rbac.domain.Employee;
import cn.wolfcode.rbac.domain.Role;
import cn.wolfcode.rbac.query.EmployeeQueryObject;
import cn.wolfcode.rbac.query.PageResult;
import cn.wolfcode.rbac.service.IRoleSercice;
import cn.wolfcode.rbac.service.iDepartmentService;
import cn.wolfcode.rbac.service.iEmployeeService;
import cn.wolfcode.rbac.util.RequiredPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private iEmployeeService employeeService;
    @Autowired
    private iDepartmentService departmentService;
    @Autowired
    private IRoleSercice roleSercice;
    @RequestMapping("/list")
    @RequiredPermission({"员工列表","employee:list"})
    public String list(Model model, @ModelAttribute("qo") EmployeeQueryObject qo){
        PageResult pageResult = employeeService.query(qo);
        List<Department> department =departmentService.selectAll();
        model.addAttribute("pageResult",pageResult);
//        model.addAttribute("qo",qo);
        model.addAttribute("departments",department);
//        请求转发
        return "forward:/WEB-INF/views/employee/list.jsp";
    }
    @RequestMapping("/delete")
    @RequiredPermission({"员工删除","employee:delete"})
    public String delete(Long id){
        employeeService.deleteById(id);

//        重定向
        return "redirect:/employee/list";
    }
    @RequestMapping("/input")
    @RequiredPermission({"员工编辑","employee:input"})
    public String input(Model model,Long id){
        List<Department> department =departmentService.selectAll();
        model.addAttribute("departments",department);
        //查询所有角色信息
        List<Role> roles = roleSercice.selectAll();
        model.addAttribute("roles",roles);
        if(id!=null){
            Employee employee = employeeService.getById(id);
            model.addAttribute("employee",employee);
        }
        return "/employee/input";
    }
    @RequestMapping("/saveOrUpdate")
    @RequiredPermission({"员工保存或修改","employee:saceOrUpdate"})
    public String saceOrUpdate(Employee employee,Long [] roleIds){
        employeeService.saveOrUpdate(employee,roleIds);
        return "redirect:/employee/list";
    }

    @RequestMapping("/login")
    public String login(Employee employee,Model model){
        try {
            employeeService.queryLogin(employee);
            //说明没有报错--》账号密码正确
            return "redirect:/department/list";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMsg",e.getMessage());
            //请求转发，把报错信息显示给用户看
            return "forward:/login.jsp";
        }

    }
    @RequestMapping("/logout")
    public String logout(){
        //1.清除session中的当前登陆记录
        employeeService.logout();
        //2.返回login.jsp登陆界面
        return "forward:/login.jsp";
    }

}
