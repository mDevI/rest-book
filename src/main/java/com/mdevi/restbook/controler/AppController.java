package com.mdevi.restbook.controler;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AppController {

    @RequestMapping("/dashboard")
    private String showAppForm() {
        return "index";
    }
}
