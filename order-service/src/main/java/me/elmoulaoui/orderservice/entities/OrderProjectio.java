package me.elmoulaoui.orderservice.entities;

import me.elmoulaoui.orderservice.enums.OrderStatus;
import me.elmoulaoui.orderservice.models.Customer;
import org.springframework.data.rest.core.config.Projection;

import java.util.Collection;
import java.util.Date;

@Projection(types = Order.class, name = "full")
public interface OrderProjectio {
     String getId();
     Date getCreated();
     OrderStatus getStatus();
     Long getCustomerId();
     Customer getCustomer();
     Collection<ProductItem> getGroducts();
}

