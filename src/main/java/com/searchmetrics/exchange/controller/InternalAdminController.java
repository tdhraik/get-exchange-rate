package com.searchmetrics.exchange.controller;

import com.searchmetrics.exchange.interactor.AdminInteractor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class InternalAdminController {

    private AdminInteractor interactor;

    public InternalAdminController(AdminInteractor interactor) {
        this.interactor = interactor;
    }


    @GetMapping("/refresh")
    public void refreshConfigurations() {
        interactor.refreshConfigurations();
    }
}
