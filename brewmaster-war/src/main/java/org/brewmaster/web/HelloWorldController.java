package org.brewmaster.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/helloworld")
public class HelloWorldController {

    @RequestMapping("/")
    public String helloworld(Model model)
    {
        model.addAttribute("name", "World");
        return "index";
    }

    @RequestMapping("/{name}")
    public String helloworldName(@PathVariable String name, Model model)
    {
        model.addAttribute("name", name);
        return "index";
    }

}
