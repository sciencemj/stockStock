package com.sciencemj.stock.web;

import com.sciencemj.stock.domain.user.User;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession httpSession = request.getSession();
        User sessionItem = (User) httpSession.getAttribute("user");
        if (sessionItem == null){
            response.getOutputStream().println("NOT LOGINED!");
            return false;
        }
        return true;
    }
}
