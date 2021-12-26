package com.delivery.system.delivery.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderHistory {
    private Integer orderId;
    private Double time;
}
