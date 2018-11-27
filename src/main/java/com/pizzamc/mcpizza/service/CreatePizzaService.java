package com.pizzamc.mcpizza.service;

import com.pizzamc.mcpizza.entity.Pizza;
import com.pizzamc.mcpizza.entity.Topping;
import com.pizzamc.mcpizza.entity.ToppingCounter;
import com.pizzamc.mcpizza.repository.ToppingCounterRepository;
import com.pizzamc.mcpizza.repository.ToppingRepository;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.*;


@Service
public class CreatePizzaService {
    @Autowired
    ToppingRepository toppingRepository;
    @Autowired
    ToppingCounterRepository toppingCounterRepository;


        public static final String ACCOUNT_SID =
                "ACadc844ed9cd4985f1501b5e6e5a01893";
        public static final String AUTH_TOKEN =
                "57507eba7deb1ff052d01c39a3740d95";
    public Pizza orderFilter(Pizza pizza) {

        pizza.getToppings().removeIf(n -> (n.getName() == null));


        if (!pizza.getDough().isSauce()) {
            pizza.getDough().setSauceType("no_sauce");
        }
        switch (pizza.getDough().getSize()) {
            case "Small":
                pizza.setPrice(7.99);
                break;
            case "Medium":
                pizza.setPrice(8.99);
                break;
            case "Large":
                pizza.setPrice(9.99);
                break;
        }
        if (pizza.getToppings().size() >= 1) {
            if (pizza.getToppings().size() > 2) {
                pizza.setPrice(pizza.getPrice() + (pizza.getToppings().size() - 2) * 0.75);
                pizza.setPrice(Double.parseDouble(new DecimalFormat("##.##").format(pizza.getPrice())));
            }
        }
        return pizza;
    }

    public Pizza pizzaObj() {
        Pizza pizza = new Pizza();
        long count = toppingRepository.count();
        for (int i = 1; i <= count; i++) {
            pizza.addTopping(new Topping());
        }
        return pizza;
    }

    public void sendMessage(String number, Pizza pizza){

        String string = "";
        for (Topping topping :pizza.getToppings()) {
            string += topping.getName()+" ";
        }
        Twilio.init(ACCOUNT_SID,AUTH_TOKEN);
        Message message = Message.creator(new PhoneNumber(number),
        new PhoneNumber("+15712004614"),"Thank you for ordering at MC PIZZA, your order "+pizza.getDough().getSize()+" "+pizza.getDough().getCrust()+" with "+string+" toppings "+" will be ready in few minutes. ").create();

    }

    public void toppingCounter(List<Topping> toppingList){

        toppingCounterRepository.findAll();

        for (Topping topping: toppingList) {
            if(toppingCounterRepository.findByName(topping.getName())!=null){
                ToppingCounter byName = toppingCounterRepository.findByName(topping.getName());
                byName.setCount(byName.getCount()+1);
                toppingCounterRepository.save(byName);
            } else {

            ToppingCounter toppingCounter = new ToppingCounter();
            toppingCounter.setName(topping.getName());
            toppingCounter.setCount(1);
            toppingCounterRepository.save(toppingCounter);

            }
        }
    }
}
