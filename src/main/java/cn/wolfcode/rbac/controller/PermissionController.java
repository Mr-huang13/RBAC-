package cn.wolfcode.rbac.controller;

import cn.wolfcode.rbac.domain.Permission;
import cn.wolfcode.rbac.query.PageResult;
import cn.wolfcode.rbac.query.QueryObjet;
import cn.wolfcode.rbac.service.IPermissionService;
import cn.wolfcode.rbac.util.RequiredPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/permission")

public class PermissionController {
    @Autowired
    private IPermissionService permissionService;
    @RequestMapping("/list")
    @RequiredPermission({"权限列表","permission:list"})
    public String list(Model model, QueryObjet qo){
        PageResult pageResult = permissionService.query(qo);
        model.addAttribute("pageResult",pageResult);
        return "forward:/WEB-INF/views/permission/list.jsp";
    }
    @RequestMapping("/delete")
    @RequiredPermission({"权限删除","permission:delete"})
    public String delete(Long id){
        permissionService.deleteById(id);
        return "redirect:/permission/list";
    }
    @RequestMapping("/reload")
    @RequiredPermission({"权限加载","permission:delete"})
    public String reload(){
        permissionService.reload();//扫描各个controller类中的方法上的注解中的权限名字+权限表达式，保存到permission表中
        return "redirect:/permission/list";
    }

}
