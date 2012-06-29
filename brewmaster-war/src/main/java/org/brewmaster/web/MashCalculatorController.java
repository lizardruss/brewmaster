package org.brewmaster.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("mashcalculator")
public class MashCalculatorController {

    @RequestMapping("/")
    public String welcome()
    {
        return "mashcalculator/index";
    }

}
