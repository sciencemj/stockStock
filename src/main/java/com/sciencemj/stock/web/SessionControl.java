package com.sciencemj.stock.web;

import com.sciencemj.stock.domain.user.SessionUser;
import com.sciencemj.stock.domain.user.User;
import com.sciencemj.stock.service.UserService;
import com.sciencemj.stock.web.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@RestController
public class SessionControl {
    private final UserService service;
    private final HttpSession httpSession;

    @PostMapping(value = "/modify")
    public SessionUser modify(@RequestBody SessionUser request){
        Long id = service.save(User.builder().name(request.getName()).money(request.getMoney())
                .password(request.getPassword()).debt(request.getDebt()).build());
        httpSession.setAttribute("user", request);
        return request;
    }

    @GetMapping("/api/login")
    public boolean login(@RequestParam String name,@RequestParam String password){
        UserResponseDto user;
        //System.out.println("logging");
        try {
            user = service.findByName(name);
            //System.out.println("logging2");
        }catch (IllegalArgumentException e){
            e.printStackTrace();
            //System.out.println("logging5");
            return false;
        }
        //System.out.println(password);
        //System.out.println(user.getPassword());
        if (password.equals(user.getPassword())){
            httpSession.setAttribute("user", new SessionUser(User.builder().name(user.getName()).money(user.getMoney())
            .password(user.getPassword()).debt(user.getDebt()).build()));
            //System.out.println("logging4");
            return true;
        }
        //System.out.println("logging3");
        return false;
    }

}
