package com.delivery.system.delivery.Services;

import com.delivery.system.delivery.Enums.OrderType;
import com.delivery.system.delivery.Models.Order;
import com.delivery.system.delivery.Models.OrderHistory;
import com.delivery.system.delivery.Models.Slots;
import com.delivery.system.delivery.Repositories.OrderHistoryRepo;
import com.delivery.system.delivery.Repositories.SlotListRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class OrdersService {

    private AtomicInteger ID_GENERATOR = new AtomicInteger(1000);
    @Autowired
    SlotListRepo slotListRepo = new SlotListRepo();

    @Autowired
    OrderHistoryRepo orderHistoryRepo = new OrderHistoryRepo();

    public void processOrders(List<Order> orderList) {
        for (Order orders : orderList) {
            double minimum = 0;
            double feasibleTime = calculateOrderTime(orders);
            if (feasibleTime > 150.0) {
                System.out.println("Order " + orders.getOrderId()
                        + " is denied because the restaurant cannot accommodate it.");
                continue;
            }
            if (slotListRepo.slotsList.size() >= 7) {
                minimum = 151;
                for (OrderHistory orderHistory : orderHistoryRepo.orderHistoryList) {
                    minimum = Math.min(orderHistory.getTime(), minimum);
                }
            }
            double timeToCook = 0;
            double maxTime = 0;
            List<OrderType> orderTypes = orders.getMeals();
            for (OrderType orderType : orderTypes) {
                if (OrderType.M.equals(orderType))
                    timeToCook = 29;
                else
                    timeToCook = 17;
                Integer id = ID_GENERATOR.getAndIncrement();
                Slots slots = new Slots(id, orders.getOrderId(), orderType, timeToCook);
                slotListRepo.slotsList.add(slots);

                //If order is main type we take two slots.
                if (OrderType.M.equals(orderType)) {
                    slotListRepo.slotsList.add(slots);
                }

                maxTime = Math.max(maxTime, timeToCook);
            }
            if (orders.getDistance() < 0) {
                System.out.println("The distance can not be negative");
                return;
            }
            double timeToDeliver = minimum + maxTime + (orders.getDistance() * 8);
            OrderHistory orderHistory = new OrderHistory(orders.getOrderId(), timeToDeliver);
            orderHistoryRepo.orderHistoryList.add(orderHistory);
            System.out.println(
                    "Order " + orders.getOrderId() + " will get delivered in " + timeToDeliver
                            + " minutes.");

        }
    }

    private Double calculateOrderTime(Order orders) {
        Double time = 0.0;
        for (OrderType orderType : orders.getMeals()) {
            if (OrderType.M.equals(orderType))
                time += 29;
            else
                time += 17;
        }
        return time;
    }

    public void printSlotList() {
        for (Slots slots : slotListRepo.slotsList) {
            System.out.println(
                    slots.getId() + " " + slots.getOrderId() + " " + slots.getTimeToCook() + " "
                            + slots.getOrderType());
        }
    }
}
