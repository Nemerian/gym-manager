package com.radu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
/* import com.radu.utils.StudentUtils; */

@Controller
public class FragmentsController {
	private final int ROW_PER_PAGE = 5;

    @GetMapping("/fragments")
    public String getHome() {
        return "fragments.html";
    }

    @GetMapping("/markup")
    public String markupPage() {
        return "markup.html";
    }

    @GetMapping("/params")
    public String paramsPage() {
        return "params.html";
    }

    /*
    @GetMapping("/istoric_abonati")
    public String istoric_Abonati_Page() {
        return "istoric_abonati.html";
    }
*/
}
	