package cn.wolfcode.rbac.controller;
import cn.wolfcode.rbac.domain.Permission;
import cn.wolfcode.rbac.domain.Role;
import cn.wolfcode.rbac.query.PageResult;
import cn.wolfcode.rbac.query.QueryObjet;
import cn.wolfcode.rbac.service.IPermissionService;
import cn.wolfcode.rbac.service.IRoleSercice;
import cn.wolfcode.rbac.util.RequiredPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
//srting MVC框架
//SSM
@Controller
@RequestMapping("/role")
public class RoleController{
    @Autowired
    private IRoleSercice roleService;
    @Autowired
    private IPermissionService permissionService;

    @RequestMapping("/list")
    @RequiredPermission({"角色列表","role:list"})
    public String list(Model model, QueryObjet qo){
//        List<Role> roles =roleService.selectAll();
        PageResult pageResult = roleService.query(qo);//带有分页效果的结果集
        model.addAttribute("pageResult",pageResult);
        //请求转发  特点：需要携带参数
        //重定向 特点：不用携带参数
        return "forward:/WEB-INF/views/role/list.jsp";
    }
    @RequestMapping("/delete")
    @RequiredPermission({"角色删除","role:delete"})
    public String delete(Long id){
        roleService.deleteById(id);
        //重定向
        return "redirect:/role/list";
    }
    @RequestMapping("/input")
    @RequiredPermission({"角色编辑","role:input"})
    public String input(Model model,Long id){
        if(id!=null){
            Role role=roleService.getById(id);
            model.addAttribute("role",role);
        }
        List<Permission> permissions = permissionService.selectAll();
        model.addAttribute("permissions",permissions);
        return "forward:/WEB-INF/views/role/input.jsp";
    }
    @RequestMapping("/saveOrUpdate")
    @RequiredPermission({"角色保存或修改","role:saveOrUpdate"})
    public String saveOrUpdate(Role role,Long[] permissionIds){
        roleService.saveOrUpdate(role,permissionIds);
        return "redirect:/role/list";
    }
}
