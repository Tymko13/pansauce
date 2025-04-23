package com.pansauce.service;

import com.pansauce.dao.BatchDao;
import com.pansauce.dao.OrderDao;
import com.pansauce.exception.order.*;
import com.pansauce.model.basic.Batch;
import com.pansauce.model.dto.BatchDTO;
import com.pansauce.model.order.Order;
import com.pansauce.model.dto.OrderDTO;
import com.pansauce.model.order.OrderWithBatchKeys;
import com.pansauce.model.order.OrderWithCustomerData;
import com.pansauce.util.RandomKeyGenerator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.pansauce.constants.keyLength.KeyLength.ORDER_KEY_LENGTH;

@Service
public class OrderService {

    private final OrderDao orderRepository;
    private final BatchDao batchRepository;

    public OrderService(
            @Qualifier(value = "orderRepo")
            OrderDao orderRepository,
            @Qualifier(value = "batchRepo")
            BatchDao batchRepository
    ) {
        this.orderRepository = orderRepository;
        this.batchRepository = batchRepository;
    }

    public List<OrderWithCustomerData> getAllOrdersSortedBy(String attribute) {
        return switch (attribute) {
          case "reg_date" -> orderRepository.getAllOrdersSortedByRegDate();
          case "price" -> orderRepository.getAllOrdersSortedByPrice();
          case "real_date" -> orderRepository.getAllOrdersSortedByExpDate();
          default -> new ArrayList<>();
        };
    }

    public List<OrderWithCustomerData> getOrdersWithNumberStartingWithSortedBy(String prefix, String attribute) {
        return switch (attribute) {
            case "reg_date" -> orderRepository.getOrdersSortedByRegDateWithNumberStartingWith(prefix + "%");
            case "price" -> orderRepository.getOrdersSortedByPriceWithNumberStartingWith(prefix + "%");
            case "real_date" -> orderRepository.getOrdersSortedByExpDateWithNumberStartingWith(prefix + "%");
            default -> new ArrayList<>();
        };
    }

    public List<Batch> getBatchesOfOrderSortedBy(String attribute, String orderNumber) {
        return switch (attribute) {
            case "price" -> orderRepository.getAllBatchesOfOrderByNumberSortedByPrice(orderNumber + "%");
            case "prod_date" -> orderRepository.getAllBatchesOfOrderByNumberSortedByProdDate(orderNumber + "%");
            case "status" -> orderRepository.getAllBatchesOfOrderByNumberSortedByBatchStatus(orderNumber + "%");
            case "sauce_quantity" -> orderRepository.getAllBatchesOfOrderByNumberSortedBySauceQuantity(orderNumber + "%");
            case "number" -> orderRepository.getAllBatchesOfOrderByNumberSortedByNumber(orderNumber + "%");
            default -> new ArrayList<>();
        };
    }

    public List<OrderWithCustomerData> getAllOrders() {
        List<OrderWithCustomerData> orders = orderRepository.findAll();
        if (orders.isEmpty())
            throw new NoOrdersFoundException();
        return orders;
    }

    public Order getOrderByKey(String key) {
        validateOrderExistence(key);
        return orderRepository.findByKey(key);
    }

    public void addOrder(OrderWithBatchKeys order) {
        RandomKeyGenerator keyGenerator = new RandomKeyGenerator(ORDER_KEY_LENGTH);
        String orderKey = keyGenerator.nextString();
        BigDecimal totalCost = calculateTotalCost(order.getBatchKeys(), order.getDeliveryCost());
        order.setTotalCost(totalCost);
        orderRepository.insert(order, orderKey);
        for (String batchKey : order.getBatchKeys()) {
            Batch batch = batchRepository.findByKey(batchKey);
            BatchDTO batchDTO = new BatchDTO();
            batchDTO.setNumber(batchKey);
            batchDTO.setQuantity(batch.getQuantity());
            batchDTO.setSauceCost(batch.getSauceCost());
            batchDTO.setOrderNumber(orderKey);
            batchRepository.updateBatch(batchDTO);
        }
    }

    public void deleteOrder(String key) {
        throw new UnsupportedOperationException();
    }

    public void updateOrder(OrderDTO order) {
        String orderNumber = order.getNumber();
        validateOrderExistence(orderNumber);
        orderRepository.updateOrder(order);
    }

    private void validateOrderExistence(String key){
        if (!orderRepository.exists(key))
            throw new NonExistingOrderException();
    }

    private BigDecimal calculateTotalCost(List<String> batchKeys, BigDecimal deliveryCost) {
        BigDecimal totalCost = deliveryCost == null ? BigDecimal.ZERO : deliveryCost;
        for (String batchKey : batchKeys) {
            Batch batch = batchRepository.findByKey(batchKey);
            totalCost = totalCost.add(batch.getCost());
        }
        return totalCost;
    }

}