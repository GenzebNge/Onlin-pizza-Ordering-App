package com.pizzamc.mcpizza.dataloader;

import com.pizzamc.mcpizza.entity.MenuItem;
import com.pizzamc.mcpizza.entity.Role;
import com.pizzamc.mcpizza.entity.Topping;
import com.pizzamc.mcpizza.entity.User;
import com.pizzamc.mcpizza.repository.MenuItemRepository;
import com.pizzamc.mcpizza.repository.RoleRepository;
import com.pizzamc.mcpizza.repository.ToppingRepository;
import com.pizzamc.mcpizza.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {

        @Autowired
        UserRepository userRepository;
        @Autowired
        RoleRepository roleRepository;
        @Autowired
        ToppingRepository  toppingRepository;
        @Autowired
        MenuItemRepository menuItemRepository;

    @Override
    public void run(String... args) throws Exception {

        Role userRole = new Role();
        userRole.setRole("user");
        roleRepository.save(userRole);

        Role adminRole = new Role();
        adminRole.setRole("admin");
        roleRepository.save(adminRole);


        User user = new User();
        user.setEmail("user@user.com");
        user.setEnabled(true);
        user.setRoles(Arrays.asList(userRole));
        user.setFirstName("user_first");
        user.setLastName("user_last");
        user.setPassword("user");
        user.setUsername("user");

        userRepository.save(user);

        User admin = new User();
        admin.setEmail("admin@admin.com");
        admin.setEnabled(true);
        admin.setRoles(Arrays.asList(adminRole));
        admin.setFirstName("admin_first");
        admin.setLastName("admin_last");
        admin.setPassword("admin");
        admin.setUsername("admin");

        userRepository.save(admin);

        Topping pepperoni = new Topping();
        pepperoni.setName("pepperoni");
        Topping salami = new Topping();
        salami.setName("salami");
        Topping spinach = new Topping();
        spinach.setName("spinach");
        Topping black_olive = new Topping();
        black_olive.setName("black_olive");

        Topping ham = new Topping();
        ham.setName("ham");
        Topping beef = new Topping();
        beef.setName("beef");
        Topping italian_sausage = new Topping();
        italian_sausage.setName("italian_sausage");
        Topping premium_chicken = new Topping();
        premium_chicken.setName("premium_chicken");

        Topping bacon = new Topping();
        bacon.setName("bacon");
        Topping philly_steak = new Topping();
        philly_steak.setName("philly_steak");
        Topping banana_pepper = new Topping();
        banana_pepper.setName("banana_pepper");
        Topping garlic = new Topping();
        garlic.setName("garlic");

        Topping hot_sauce = new Topping();
        hot_sauce.setName("hot_sauce");
        Topping jalepino_pepper = new Topping();
        jalepino_pepper.setName("jalepino_pepper");
        Topping mushroom = new Topping();
        mushroom.setName("mushroom");
        Topping pineapple = new Topping();
        pineapple.setName("pineapple");

        Topping green_pepper = new Topping();
        green_pepper.setName("green_pepper");
        Topping roasted_red_pepper = new Topping();
        roasted_red_pepper.setName("roasted_red_pepper");
        Topping feta_cheese = new Topping();
        feta_cheese.setName("feta_cheese");
        Topping onion = new Topping();
        onion.setName("onion");

        toppingRepository.save(pepperoni);
        toppingRepository.save(beef);
        toppingRepository.save(ham);
        toppingRepository.save(salami);
        toppingRepository.save(philly_steak);
        toppingRepository.save(bacon);
        toppingRepository.save(premium_chicken);
        toppingRepository.save(italian_sausage);
        toppingRepository.save(black_olive);
        toppingRepository.save(spinach);
        toppingRepository.save(onion);
        toppingRepository.save(feta_cheese);
        toppingRepository.save(roasted_red_pepper);
        toppingRepository.save(green_pepper);
        toppingRepository.save(pineapple);
        toppingRepository.save(mushroom);
        toppingRepository.save(jalepino_pepper);
        toppingRepository.save(hot_sauce);
        toppingRepository.save(garlic);
        toppingRepository.save(banana_pepper);


        MenuItem buffaloChicken = new MenuItem();
        buffaloChicken.setName("Buffalo Chicken");
        buffaloChicken.setDescription("Grilled chicken breast, fresh onions, provolone, American cheese, cheddar,and drizzled with a hot sauce.");
        buffaloChicken.setImage("https://res.cloudinary.com/dlkfcmw1x/image/upload/v1542946044/Pizza%20Image/buffalo_chicken.png");
        buffaloChicken.setPrice(11);

        MenuItem caliChichenBacon = new MenuItem();
        caliChichenBacon.setName("Cali Chicken Bacon");
        caliChichenBacon.setDescription("Grilled chicken breast, garlic Parmesan white sauce, smoked bacon, tomatoes, provolone and cheese made with 100% real mozzarella.");
        caliChichenBacon.setImage("https://res.cloudinary.com/dlkfcmw1x/image/upload/v1542946044/Pizza%20Image/cali_chicken_bacon_ranch.png");
        caliChichenBacon.setPrice(14);

        MenuItem deluxe = new MenuItem();
        deluxe.setName("Deluxe");
        deluxe.setDescription("Pepperoni, Italian sausage, fresh green peppers, fresh mushrooms, fresh onions and cheese made with 100% real mozzarella.");
        deluxe.setImage("https://res.cloudinary.com/dlkfcmw1x/image/upload/v1542946043/Pizza%20Image/deluxe.png");
        deluxe.setPrice(15);

        MenuItem extravaganza = new MenuItem();
        extravaganza.setName("Extravaganza");
        extravaganza.setDescription("Pepperoni, ham, Italian sausage, beef, fresh onions, fresh green peppers, fresh mushrooms and black olives.");
        extravaganza.setImage("https://res.cloudinary.com/dlkfcmw1x/image/upload/v1542946044/Pizza%20Image/extravagan_pizza.png");
        extravaganza.setPrice(16);

        MenuItem honoluluHawalian = new MenuItem();
        honoluluHawalian.setName("Honolulu Hawalian");
        honoluluHawalian.setDescription("Sliced ham, smoked bacon, pineapple, roasted red peppers, provolone and cheese made with 100% real mozzarella.");
        honoluluHawalian.setImage("https://res.cloudinary.com/dlkfcmw1x/image/upload/v1542946041/Pizza%20Image/honolulu_hawalian.png");
        honoluluHawalian.setPrice(12);

        MenuItem meatPizza = new MenuItem();
        meatPizza.setName("Meat Pizza");
        meatPizza.setDescription("Pepperoni, ham, Italian sausage and beef, all sandwiched between two layers of cheese made with 100% real mozzarella.");
        meatPizza.setImage("https://res.cloudinary.com/dlkfcmw1x/image/upload/v1542946042/Pizza%20Image/meat_pizza.png");
        meatPizza.setPrice(13);

        MenuItem memphisBbqChicken = new MenuItem();
        memphisBbqChicken.setName("Memphis BBQ Chicken");
        memphisBbqChicken.setDescription("Grilled chicken breast, BBQ sauce, fresh onions, cheddar, provolone and cheese made with 100% real mozzarella.");
        memphisBbqChicken.setImage("https://res.cloudinary.com/dlkfcmw1x/image/upload/v1542946043/Pizza%20Image/memphis_bbq_chicken.png");
        memphisBbqChicken.setPrice(15);

        MenuItem pacificVeggie = new MenuItem();
        pacificVeggie.setName("Pacific Veggie");
        pacificVeggie.setDescription("Roasted red peppers, fresh baby spinach, fresh onions, fresh mushrooms, tomatoes, black olives, feta, provolone.");
        pacificVeggie.setImage("https://res.cloudinary.com/dlkfcmw1x/image/upload/v1542946044/Pizza%20Image/pacific_veggie.png");
        pacificVeggie.setPrice(11);

        MenuItem phillyCheeseSteak = new MenuItem();
        phillyCheeseSteak.setName("Philly Cheese Steak");
        phillyCheeseSteak.setDescription("Tender slices of steak, fresh onions, fresh green peppers, fresh mushrooms, provolone and American cheese.");
        phillyCheeseSteak.setImage("https://res.cloudinary.com/dlkfcmw1x/image/upload/v1542946045/Pizza%20Image/philly_cheese_steak.png");
        phillyCheeseSteak.setPrice(13);

        MenuItem spinach_n_feta = new MenuItem();
        spinach_n_feta.setName("Spinach & Feta");
        spinach_n_feta.setDescription("Creamy Alfredo sauce, fresh spinach, fresh onions, feta, Parmesan-Asiago, provolone and cheese made with 100% real mozzarella.");
        spinach_n_feta.setImage("https://res.cloudinary.com/dlkfcmw1x/image/upload/v1542946047/Pizza%20Image/spinach_feta.png");
        spinach_n_feta.setPrice(14);

        MenuItem ultimatePepperoni = new MenuItem();
        ultimatePepperoni.setName("Ultimate Pepperoni");
        ultimatePepperoni.setDescription("Two layers of pepperoni sandwiched between provolone, Parmesan-Asiago and cheese, sprinkled with oregano.");
        ultimatePepperoni.setImage("https://res.cloudinary.com/dlkfcmw1x/image/upload/v1542946046/Pizza%20Image/ultimate_pepperoni.png");
        ultimatePepperoni.setPrice(11);

        MenuItem wisconsin6Cheese = new MenuItem();
        wisconsin6Cheese.setName("Wisconsin 6 Cheese");
        wisconsin6Cheese.setDescription("Feta, provolone, cheddar, Parmesan-Asiago, cheese made with 100% real mozzarella and sprinkled with oregano.");
        wisconsin6Cheese.setImage("https://res.cloudinary.com/dlkfcmw1x/image/upload/v1542946047/Pizza%20Image/wisconsin_6_cheese.png");
        wisconsin6Cheese.setPrice(13);

        menuItemRepository.save(buffaloChicken);
        menuItemRepository.save(caliChichenBacon);
        menuItemRepository.save(deluxe);
        menuItemRepository.save(extravaganza);
        menuItemRepository.save(honoluluHawalian);
        menuItemRepository.save(meatPizza);
        menuItemRepository.save(memphisBbqChicken);
        menuItemRepository.save(pacificVeggie);
        menuItemRepository.save(phillyCheeseSteak);
        menuItemRepository.save(spinach_n_feta);
        menuItemRepository.save(ultimatePepperoni);
        menuItemRepository.save(wisconsin6Cheese);

    }
}
