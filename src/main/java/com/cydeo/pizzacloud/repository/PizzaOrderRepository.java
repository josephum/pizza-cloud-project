package com.cydeo.pizzacloud.repository;

import com.cydeo.pizzacloud.model.Pizza;
import com.cydeo.pizzacloud.model.PizzaOrder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class PizzaOrderRepository {

    private static List<PizzaOrder> pizzaOrderList = new ArrayList<>();

    public PizzaOrder createPizzaOrder(PizzaOrder pizzaOrderToSave) {
        pizzaOrderList.add(pizzaOrderToSave);
        System.out.println("New pizza order has been added. #Order = "+pizzaOrderList.size());
        System.out.println("With pizzaId: "+pizzaOrderToSave.getPizza().getId());
        return pizzaOrderToSave;
    }

    public List<PizzaOrder> readAll() {
        return pizzaOrderList;
    }

    public PizzaOrder getById(UUID pizzaId){
        for (PizzaOrder pizzaOrder:
                pizzaOrderList) {
            if (pizzaOrder.getPizza().getId().equals(pizzaId)) return pizzaOrder;

        }
        return null;
    }
}
