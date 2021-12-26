package com.delivery.system.delivery.Models;

import com.delivery.system.delivery.Enums.OrderType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private Integer orderId;
    private List<OrderType> meals;
    private Double distance;
}
