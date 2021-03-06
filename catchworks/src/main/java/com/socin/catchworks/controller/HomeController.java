package com.socin.catchworks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/home")
public class HomeController {

	@RequestMapping(method = RequestMethod.GET)
    public String home(Model model) {
        model.addAttribute("mensagem",
                "Minha primeira aplicação utilizando o Spring Framework MVC!");
        return "home";
    }
	
}
