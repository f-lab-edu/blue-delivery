package com.delivery.user.controller;

import com.delivery.user.dto.LoginDto;
import com.delivery.user.model.User;
import com.delivery.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final Logger logger = LoggerFactory.getLogger(LoggerFactory.class);

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/loginPost")
    public void login(LoginDto loginDto, HttpServletRequest request) throws Exception {

        User user = userService.login(loginDto);
        HttpSession httpSession = request.getSession();

        if (user == null || loginDto.getUserPassword() != user.getUserPassword()) {
            return;
        } else {
            logger.info("login success");
            httpSession.setAttribute("login", user);
        }
    }
}
