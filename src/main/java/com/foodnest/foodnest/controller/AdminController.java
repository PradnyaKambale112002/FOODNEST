package com.foodnest.foodnest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.foodnest.foodnest.dto.DashboardDTO;
import com.foodnest.foodnest.service.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/dashboard")
    public DashboardDTO getDashboard() {
        return adminService.getDashboard();
    }
}
