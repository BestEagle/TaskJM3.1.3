package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.Role;
import web.model.User;
import web.service.UserService;

import java.util.HashSet;
import java.util.Set;

@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(@Qualifier("userServiceImpl") UserService userService) {
        this.userService = userService;
    }

    @GetMapping("admin")
    public String listUsers(Model model){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user);
        model.addAttribute("listUsers", userService.findAll());
        return "admin";
    }

    @PostMapping("admin/add")
    public String addUser(User user, String newRoles) {
        Set<Role> role = new HashSet<>();
        role.add(new Role(newRoles));
        user.setRoles(role);
        userService.save(user);
        return "redirect:/admin";
    }

    @PostMapping("admin/delete")
    public String deleteUser(int id) {
        userService.deleteById(id);
        return "redirect:/admin";
    }



    @GetMapping("login")
    public  String LoginPage(){
        return "login";
    }

    @GetMapping("user")
    public String getUsers(Model model){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user);
        return "user";
    }
}
