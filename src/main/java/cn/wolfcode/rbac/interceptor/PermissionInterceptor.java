package cn.wolfcode.rbac.interceptor;

import cn.wolfcode.rbac.domain.Employee;
import cn.wolfcode.rbac.util.RequiredPermission;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.List;

public class PermissionInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //先去session获取当前登陆的用户
        Employee currentEmp = (Employee)request.getSession().getAttribute("EMPLOYEE_IN_SESSION");
        if (currentEmp==null){
            return false;
        }

        if (currentEmp.getAdmin()){
            return true;
        }

        HandlerMethod hm = (HandlerMethod) handler;
        Method method = hm.getMethod();
        if (!method.isAnnotationPresent(RequiredPermission.class)){
            return true;
        }
        RequiredPermission annotation = method.getAnnotation(RequiredPermission.class);
        String expression = annotation.value()[1];
        List<String> expressions = (List<String>) request.getSession().getAttribute("EXPRESSION_IN_SESSION");
        System.out.println(expression);
        System.out.println(expressions);
        if (expressions.contains(expression)){
            return true;
        }
        request.getRequestDispatcher("/WEB-INF/views/common/nopermission.jsp").forward(request,response);
        return false;
    }
}
