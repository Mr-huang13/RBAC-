package cn.wolfcode.rbac.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //先去session获取当前登陆的用户
        Object currentEmp = request.getSession().getAttribute("EMPLOYEE_IN_SESSION");
        if (currentEmp != null){
            return true;
        }
        response.sendRedirect("/login.jsp");
        return false;//没有登陆不放行
    }
}
