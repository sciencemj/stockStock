package com.sciencemj.stock.web;

import com.sciencemj.stock.domain.user.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class ViewController {
    private final HttpSession httpSession;
    @GetMapping("/")
    public String Index(Model model){
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if (user != null){
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }
    @GetMapping("/login")
    public String Login(Model model){
        return "login";
    }
    @GetMapping("/signup")
    public String SignUp(Model model){
        return "signup";
    }

    @GetMapping("/api/logout")
    public RedirectView LogOut(){
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if (user != null){
            httpSession.removeAttribute("user");
            return new RedirectView("/");
        }
        return new RedirectView("/");
    }
    @GetMapping("/app/bank")
    public String Bank(Model model){
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if (user != null){
            model.addAttribute("userName", user.getName());
            /*model.addAttribute("money", user.getMoney());
            model.addAttribute("debt", user.getDebt());*/
        }
        return "bank";
    }
    @GetMapping("/app/bank/{bank}")
    public String Banks(Model model, @PathVariable String bank){
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if (user != null){
            model.addAttribute("userName", user.getName());
            model.addAttribute("money", user.getMoney());
            model.addAttribute("debt", user.getDebt());
        }
        return "banks/"+bank;
    }
}
