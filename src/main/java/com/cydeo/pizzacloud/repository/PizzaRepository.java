package com.cydeo.pizzacloud.repository;

import com.cydeo.pizzacloud.exception.PizzaNotFoundException;
import com.cydeo.pizzacloud.model.Pizza;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class PizzaRepository {

    private static List<Pizza> pizzaList = new ArrayList<>();

    public Pizza createPizza(Pizza pizzaToSave) {
        pizzaList.add(pizzaToSave);
        return pizzaToSave;
    }

    public List<Pizza> readAll() {
        return pizzaList;
    }

    public Pizza getById(UUID pizzaId) throws PizzaNotFoundException{
        for (Pizza pizza:
             pizzaList) {
            if (pizza.getId().equals(pizzaId)) return pizza;

        }
        throw new PizzaNotFoundException("PizzaId "+pizzaId.toString()+" not found");
    }

}
