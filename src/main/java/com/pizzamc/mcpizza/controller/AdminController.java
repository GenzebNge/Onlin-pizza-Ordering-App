package com.pizzamc.mcpizza.controller;


import com.cloudinary.utils.ObjectUtils;
import com.pizzamc.mcpizza.cloudinary.CloudinaryConfig;
import com.pizzamc.mcpizza.entity.MenuItem;
import com.pizzamc.mcpizza.entity.Order;
import com.pizzamc.mcpizza.entity.Pizza;
import com.pizzamc.mcpizza.entity.Topping;
import com.pizzamc.mcpizza.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

@Controller
public class AdminController {

    @Autowired
    ToppingRepository toppingRepository;
    @Autowired
    MenuItemRepository menuItemRepository;
    @Autowired
    CloudinaryConfig config;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PizzaRepository pizzaRepository;

    @RequestMapping("/admin")
    public String admin(Model model){
        double total = 0;
        for (Pizza pizza : pizzaRepository.findAll()) {
            total += pizza.getPrice();
        }
        total = Double.parseDouble(new DecimalFormat("##.##").format(total));
        model.addAttribute("total",total);
        model.addAttribute("orders",orderRepository.findAll());
        return "admin";
    }

    @RequestMapping("/admin/toppings")
    public String toppings(Model model){
        model.addAttribute("toppings", toppingRepository.findAll());
        return "toppings";
    }

    @GetMapping("/admin/addtopping")
    public String addTopping(Model model){
        model.addAttribute("topping", new Topping());
        return "addtopping";
    }

    @PostMapping("/admin/addtopping")
    public String addToppingProcess(Topping topping){
            toppingRepository.save(topping);
        return "redirect:/admin/toppings";
    }

    @RequestMapping("/admin/edittopping/{id}")
    public String editTopping(Model model, @PathVariable("id") long id){
        model.addAttribute("topping", toppingRepository.findById(id));
        return"addtopping";
    }

    @RequestMapping("/admin/deletetopping/{id}")
    public String deleteTopping(@PathVariable("id")long id){
        toppingRepository.deleteById(id);
        return "redirect:/admin/toppings";
    }

    @RequestMapping("/admin/menu")
    public String menu(Model model){
        model.addAttribute("menuitem", menuItemRepository.findAll());
        return "adminmenu";
    }
    @RequestMapping("/admin/addmenuitem")
    public String addMenuItem(Model model){
        model.addAttribute("item", new MenuItem());
        return "addmenuitem";
    }
    @PostMapping("/admin/addmenuitem")
    public String addMenuItem(MenuItem menuItem, @RequestParam("file") MultipartFile file){

        if(file.isEmpty()){
            return "redirect:/admin/addmenuitem";
        }
        try {
            Map uploadResult = config.upload(file.getBytes(),
                    ObjectUtils.asMap("resourcetype", "auto"));
            menuItem.setImage(uploadResult.get("url").toString());
            menuItemRepository.save(menuItem);
        } catch (IOException e){
            e.printStackTrace();
            return "redirect:/admin/addmenuitem";
        }
        return "redirect:/admin/menu";

    }

    @RequestMapping("/admin/editmenuitem/{id}")
    public String editMenuItem(Model model,@PathVariable("id")long id){
        model.addAttribute("item", menuItemRepository.findById(id));
        return "addmenuitem";
    }

    @RequestMapping("/admin/deletemenuitem/{id}")
    public String deleteMenuItem(@PathVariable("id")long id){
        menuItemRepository.deleteById(id);
        return "redirect:/admin/menu";
    }
    @RequestMapping("/admin/customers")
    public String customers(Model model){
        model.addAttribute("customers", userRepository.findAll());
        return "customers";
    }
}
