package com.sciencemj.stock.web;

import com.sciencemj.stock.domain.stock.Stock;
import com.sciencemj.stock.domain.user.SessionUser;
import com.sciencemj.stock.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.util.Comparator;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class ViewController {
    private final StockService stockService;
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
    @GetMapping("/market")
    public String Market(Model model){
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if (user != null){
            model.addAttribute("userName", user.getName());
        }
        List<Stock> stocks = stockService.all();
        stocks.sort(new Comparator<Stock>() { //내림차순
            @Override
            public int compare(Stock arg0, Stock arg1) {
                Double price0 = arg0.getPrice();
                Double price1 = arg1.getPrice();
                if (price0.equals(price1))
                    return 0;
                else if (price0 > price1)
                    return -1;
                else
                    return 1;
            }
        });
        System.out.println(stocks);
        model.addAttribute("stocks", stocks);
        return "market";
    }

    @GetMapping("/test")
    public RedirectView Test(){
        for (int i = 0;i < 10;i++) {
            Stock s = new Stock("abc" + i, 100D + 10*i);
            stockService.save(s);
        }
        return new RedirectView("/market");
    }
    @GetMapping("/buysell/{name}")
    public String BuySell(@PathVariable String name, Model model){
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if (user != null){
            model.addAttribute("userName", user.getName());
        }
        model.addAttribute("name",stockService.findByName(name).getName());
        return "buysell";
    }
}
