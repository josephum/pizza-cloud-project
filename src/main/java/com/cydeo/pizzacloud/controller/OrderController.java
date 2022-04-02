package com.cydeo.pizzacloud.controller;

import com.cydeo.pizzacloud.exception.PizzaNotFoundException;
import com.cydeo.pizzacloud.model.Pizza;
import com.cydeo.pizzacloud.model.PizzaOrder;
import com.cydeo.pizzacloud.repository.PizzaOrderRepository;
import com.cydeo.pizzacloud.repository.PizzaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private final PizzaRepository pizzaRepository;
    private final PizzaOrderRepository pizzaOrderRepository;

    public OrderController(PizzaRepository pizzaRepository, PizzaOrderRepository pizzaOrderRepository) {
        this.pizzaRepository = pizzaRepository;
        this.pizzaOrderRepository = pizzaOrderRepository;
    }

    @GetMapping("/current")
    public String orderForm(@RequestParam(name = "pizzaId") UUID pizzaId, Model model) {

        PizzaOrder pizzaOrder = new PizzaOrder();

        // Fix the getPizza method below in line 49.
        pizzaOrder.setPizza(getPizza(pizzaId));

        model.addAttribute("pizzaOrder", pizzaOrder);

        return "orderForm";
    }

    @PostMapping("/create-order")
    public String processOrder(@RequestParam(name = "pizzaId") UUID pizzaId, @ModelAttribute("pizzaOrder") PizzaOrder pizzaOrder) {

        // Save the order
        pizzaOrder.setPizza(getPizza(pizzaId));
        pizzaOrderRepository.createPizzaOrder(pizzaOrder);
        return "redirect:/home";
    }

    //TODO
    private Pizza getPizza(UUID pizzaId) {
        // Get the pizza from repository based on it's id
//        return pizzaRepository.getById(pizzaId);
        return pizzaRepository.readAll().stream()
                .filter(pizza -> pizza.getId().equals(pizzaId))
                .findFirst().orElseThrow(() -> new PizzaNotFoundException("Pizza not found!!!"));
    }

}
