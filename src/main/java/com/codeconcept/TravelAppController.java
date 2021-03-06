package com.codeconcept;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TravelAppController {

    @GetMapping("/")
    public String print(){
        return "index";
    }
}
