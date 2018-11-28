package com.pizzamc.mcpizza.controller;

import com.pizzamc.mcpizza.entity.*;
import com.pizzamc.mcpizza.repository.*;
import com.pizzamc.mcpizza.service.CreatePizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
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

    @RequestMapping("/order{id}")
    public String order(@PathVariable("id")long id,Model model, HttpServletRequest request){
        model.addAttribute("menuItem",menuItemRepository.findById(id));
        MenuItem menuItem = menuItemRepository.findById(id);
        Pizza pizza = new Pizza();
        pizza.setPrice(menuItem.getPrice());
        pizza.setName(menuItem.getName());
        request.getSession().setAttribute("mPizza", pizza);
        model.addAttribute("order", new Order());
        model.addAttribute("dough", new Dough());
        return "orderfrommenu";
    }
    @PostMapping("/order")
    public String orderProcess(Dough dough, Order order, HttpServletRequest request, Model model){
        Pizza mPizza = (Pizza)request.getSession().getAttribute("mPizza");
        doughRepository.save(dough);
        mPizza.setDough(dough);
        pizzaRepository.save(mPizza);
        order.setPizza(mPizza);
        order.setUser(getUser());
        orderRepository.save(order);
        //createPizzaService.sendMessage(order.getPhoneNumber(), mPizza.getName());
        model.addAttribute("order", order);
        request.getSession().removeAttribute("mPizza");
        return "receipt";
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
    public String registerProcess(@Valid User user, BindingResult result) {
        if(result.hasErrors()){
            return "/register";
        }
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
    @RequestMapping("/account")
    public String account(Model model){
        model.addAttribute("orders", orderRepository.findByUser(getUser()));
        return "account";
    }

    @RequestMapping("/location")
    public String location() {
        return "location";
    }

    @PostMapping("/placeorder")
    public String placeOrder(Order order, HttpSession session, Model model){
        Pizza pizza = (Pizza)session.getAttribute("piz_req");
        doughRepository.save(pizza.getDough());
        toppingRepository.saveAll(pizza.getToppings());
        pizzaRepository.save(pizza);
        order.setPizza(pizza);
        order.setUser(getUser());
        orderRepository.save(order);
        createPizzaService.toppingCounter(pizza.getToppings());
        try {
            createPizzaService.sendMessage(order.getPhoneNumber(), pizza);
        } catch (Exception e){

        };
        model.addAttribute("order", order);
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
