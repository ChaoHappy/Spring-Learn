package com.chaohappy.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
    @RequestMapping(value="/login")
    public ModelAndView showHomePage(@RequestParam(value = "error", required = false) String error,
                                     @RequestParam(value = "logout", required = false) String logout) {
        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", "无效的用户名和密码！");
        }
        if (logout != null) {
            model.addObject("msg", "您已成功注销。");
        }
        model.setViewName("login");
        return model;
    }

    @RequestMapping(value="/success")
    public String showSuccessPage() {
        return "success";
    }

    @RequestMapping(value="/welcome")
    public String showWelcomePage() {
        return "hello";
    }

    @RequestMapping(value="/admin")
    public String showAdminPage() {
        return "admin";
    }
}
