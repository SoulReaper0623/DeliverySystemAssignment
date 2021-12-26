package com.delivery.system.delivery.Repositories;

import com.delivery.system.delivery.Models.OrderHistory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderHistoryRepo {
    public List<OrderHistory> orderHistoryList = new ArrayList<>();
}
