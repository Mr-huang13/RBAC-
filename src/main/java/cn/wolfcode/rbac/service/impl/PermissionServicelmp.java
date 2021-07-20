package cn.wolfcode.rbac.service.impl;

import cn.wolfcode.rbac.domain.Department;
import cn.wolfcode.rbac.domain.Permission;
import cn.wolfcode.rbac.mapper.PermissionMapper;
import cn.wolfcode.rbac.query.PageResult;
import cn.wolfcode.rbac.query.QueryObjet;
import cn.wolfcode.rbac.service.IPermissionService;
import cn.wolfcode.rbac.util.RequiredPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Service
public class PermissionServicelmp implements IPermissionService {
    //1.获取Spring容器对象
    @Autowired
    private ApplicationContext ctx;
    @Autowired
    private PermissionMapper permissionMapper;
    public List<Permission> selectAll(){
        return permissionMapper.selectAll();
    }
    public void deleteById(Long id){
        permissionMapper.deleteById(id);
    }

    @Override
    public PageResult query(QueryObjet qo) {
        //带有分页效果的数据集，总条数
        int totalCount = permissionMapper.queryForCount();
        if(totalCount != 0){
            List<Permission> data = permissionMapper.queryForData(qo);
            return new PageResult(data,totalCount,qo.getCurrentPage(),qo.getPageSize());//全参构造器，有数据的情况
        }
        return new PageResult(qo.getCurrentPage(),qo.getPageSize());//缺参构造器，没有满足条件的数据的情况
    }

    @Override
    public void reload() {
        permissionMapper.deleteAll();
        //因为权限注解是贴在Controller类中的方法上，而Controller类的bean对象又存在与Spring容器中，
        // 所以步骤如下：
        // 1.获取Spring容器对象
        // 2.获取容器中所有的Controller类的bean对象，这里更具控制器上贴有的controller注解获取这一对象
        Map<String, Object> beansWithAnnotation = ctx.getBeansWithAnnotation(Controller.class);
//        System.out.println(beansWithAnnotation);
        //获取map集合的bean对象
        Collection<Object> values = beansWithAnnotation.values();
        //循环遍历values中的各个bean对象
        // 3.获取每一个controller类的所有方法
        for (Object controller:values){
            Method[] methods = controller.getClass().getDeclaredMethods();
            // 4.获取方法上贴着的所有注解，然后筛选出我们需要的权限注解
            // 5.循环遍历方法，获取每一个方法上的注解
            for (Method method : methods) {
                //判断当前方法上是否贴有我们需要的@RequiredPermission
                if (method.isAnnotationPresent(RequiredPermission.class)){
                    //获取方法上贴着的自定义注解
                    RequiredPermission annotation = method.getAnnotation(RequiredPermission.class);
//                    System.out.println(annotation);
                    // 5.获取权限注解括号中传递的参数
                    String[] value = annotation.value();
                    //value中包含权限和权限表达式
                    String name =value[0];//权限名字
                    String expression = value[1];//权限表达式
                    // 6.将获取到的参数值保存到数据库中的permission表中
                    //保存时需要去重
                    //方法： 1.保存前清空权限表
                    //      2.
                    Permission permission = new Permission();
                    permission.setName(name);
                    permission.setExpression(expression);
                    permissionMapper.insert(permission);
                }
            }
        }






    }

}
