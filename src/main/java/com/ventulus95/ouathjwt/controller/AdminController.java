package com.ventulus95.ouathjwt.controller;

import com.ventulus95.ouathjwt.security.CurrentUser;
import com.ventulus95.ouathjwt.security.UserPrincipal;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/checker")
    public String checker(@CurrentUser UserPrincipal userPrincipal){
        return "와와옹우ㅜ앙";
    }
}
