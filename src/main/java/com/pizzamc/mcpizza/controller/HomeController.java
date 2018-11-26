package com.pizzamc.mcpizza.controller;

import com.pizzamc.mcpizza.entity.*;
import com.pizzamc.mcpizza.repository.*;
import com.pizzamc.mcpizza.service.CreatePizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;

@Controller
public class HomeController {

    @Autowired
    ToppingRepository toppingRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    MenuItemRepository menuItemRepository;
    @Autowired
    CreatePizzaService createPizzaService;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    PizzaRepository pizzaRepository;
    @Autowired
    DoughRepository doughRepository;

    @GetMapping("/")
    public String homePage() {
        return "homepage";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/create")
    public String createPizza(Model model) {

        model.addAttribute("pizza", createPizzaService.pizzaObj());
        model.addAttribute("toppings", toppingRepository.findAll());
        return "createpizza";
    }

    @PostMapping("/create")
    public String createProcess(Pizza pizza, HttpServletRequest request, Model model, HttpSession session) {
        pizza.getDough().setSize(request.getParameter("pizza_size_name"));
        Pizza ordFil = createPizzaService.orderFilter(pizza);
        session.setAttribute("piz_req", ordFil);
        model.addAttribute("pizza", ordFil);
        model.addAttribute("order", new Order());
        return "placeorder";
    }

    @RequestMapping("/menu")
    public String menu(Model model) {
        model.addAttribute("menuItems", menuItemRepository.findAll());
        return "menu";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerProcess(User user) {
        Role role = roleRepository.findByRole("user");
        if (role == null) {
            Role newRole = new Role();
            newRole.setRole("user");
            roleRepository.save(newRole);
            user.setRoles(Arrays.asList(newRole));
            userRepository.save(user);
        } else {
            user.setRoles(Arrays.asList(role));
            userRepository.save(user);
        }
        return "redirect:/login";
    }

    @RequestMapping("/location")
    public String location() {
        return "location";
    }

    @PostMapping("/placeorder")
    public String placeOrder(Order order, HttpSession session){
        Pizza pizza = (Pizza)session.getAttribute("piz_req");
        doughRepository.save(pizza.getDough());
        toppingRepository.saveAll(pizza.getToppings());
        pizzaRepository.save(pizza);
        order.setPizza(pizza);
        order.setUser(getUser());
        System.out.println(order);
        orderRepository.save(order);
        createPizzaService.sendMessage(order.getPhoneNumber(), pizza);
        session.removeAttribute("piz_req");
       return "receipt";
    }

    public User getUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentusername = authentication.getName();
        User user = userRepository.findByUsername(currentusername);
        return user;
    }
}
