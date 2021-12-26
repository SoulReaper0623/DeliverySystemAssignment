package com.delivery.system.delivery.Models;

import com.delivery.system.delivery.Enums.OrderType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Slots {
    private Integer id;
    private Integer orderId;
    private OrderType orderType;
    private Double timeToCook;
}
