package com.delivery.system.delivery;

import com.delivery.system.delivery.Models.Order;
import com.delivery.system.delivery.Services.OrdersService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.CollectionUtils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


@lombok.Data
@SpringBootApplication
public class DeliveryApplication {

	@Autowired
	public static OrdersService ordersService = new OrdersService();

	@Autowired
	public static ObjectMapper objectMapper = new ObjectMapper();

	public static void main(String[] args) {
		try (FileReader reader = new FileReader("test.json"))
		{
			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.setDateFormat("M/d/yy hh:mm a");
			Gson gson = gsonBuilder.create();
			List<Order> orderList = Arrays.asList(gson.fromJson(reader, Order[].class));
			if (CollectionUtils.isEmpty(orderList)){
				System.out.println("No orders to process.");
				return;
			}
			ordersService.processOrders(orderList);    //Process orders
//			ordersService.printSlotList();			//Print Slot List
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
