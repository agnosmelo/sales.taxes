package com.liferay.sales.taxes.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //Definindo aplicação REST
public class AppCheckController {

    @GetMapping(path = "api/status")//metodo executado quando tiver execução get
    public String check() {
        return "Working well :)";
    }
}
