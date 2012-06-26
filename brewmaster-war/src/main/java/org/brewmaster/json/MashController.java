package org.brewmaster.json;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/mash")
public class MashController {

    @RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Double calculateInfusionTemperature()
    {
        return 0.0;
    }

}
