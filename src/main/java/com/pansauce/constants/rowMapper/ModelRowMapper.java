package com.pansauce.constants.rowMapper;

import com.pansauce.model.analysis.TotalAmount;
import com.pansauce.model.analysis.TotalIncome;
import com.pansauce.model.basic.*;
import com.pansauce.model.customer.Customer;
import com.pansauce.model.customer.CustomerOrderData;
import com.pansauce.model.customer.CustomerWithOrders;
import com.pansauce.model.order.Order;
import com.pansauce.model.order.OrderWithCustomerData;
import com.pansauce.model.sauce.Sauce;
import com.pansauce.model.sauce.SauceWithIncome;
import com.pansauce.model.sauce.SauceWithRecipe;
import com.pansauce.model.sauce.SauceWithSalesCount;
import com.pansauce.security.User;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import java.util.*;

public class ModelRowMapper {

    public static final RowMapper<Sauce> SAUCE_ROW_MAPPER = (r, i) -> {
        Sauce sauce = new Sauce();
        sauce.setNumber(r.getString("sauce_number"));
        sauce.setName(r.getString("sauce_name"));
        sauce.setTypeNumber(r.getString("type_number"));
        sauce.setShelfLife(r.getInt("shelf_life"));
        sauce.setWeight(r.getDouble("sauce_weight"));
        sauce.setCost(r.getBigDecimal("sauce_cost"));
        sauce.setTypeName(r.getString("type_name"));
        return sauce;
    };

    public static final RowMapper<SauceWithIncome> SAUCE_WITH_INCOME_ROW_MAPPER = (r, i) -> {
        SauceWithIncome sauce = new SauceWithIncome();
        sauce.setSauceNumber(r.getString("sauce_number"));
        sauce.setSauceName(r.getString("sauce_name"));
        sauce.setSauceIncome(r.getBigDecimal("sauce_income"));
        return sauce;
    };

    public static final RowMapper<SauceWithSalesCount> SAUCE_WITH_SALES_COUNT_ROW_MAPPER = (r, i) -> {
        SauceWithSalesCount sauce = new SauceWithSalesCount();
        sauce.setSauceNumber(r.getString("sauce_number"));
        sauce.setSauceName(r.getString("sauce_name"));
        sauce.setSalesCount(r.getInt("total_batches_sold"));
        return sauce;
    };

    public static final RowMapper<Ingredient> INGREDIENT_ROW_MAPPER = (r, i) -> {
        Ingredient ingredient = new Ingredient();
        ingredient.setGti(r.getString("gti_number"));
        ingredient.setName(r.getString("ingredient_name"));
        return ingredient;
    };

    public static final RowMapper<SauceIngredient> SAUCE_INGREDIENT_ROW_MAPPER = (r, i) -> {
        SauceIngredient ingredient = new SauceIngredient();
        ingredient.setGti(r.getString("gti_number"));
        ingredient.setName(r.getString("ingredient_name"));
        ingredient.setWeight(r.getInt("ing_weight"));
        return ingredient;
    };

    public static final RowMapper<Batch> BATCH_ROW_MAPPER = (r, i) -> {
        Batch batch = new Batch();
        batch.setNumber(r.getString("batch_number"));
        batch.setQuantity(r.getInt("sauce_quantity"));
        batch.setProductionDate(r.getDate("production_date"));
        batch.setExpirationDate(r.getDate("expiration_date"));
        batch.setSauceCost(r.getBigDecimal("sauce_cost_at_that_time"));
        batch.setCost(r.getBigDecimal("batch_cost"));
        batch.setStatus(r.getString("batch_status"));
        batch.setSauceNumber(r.getString("sauce_number"));
        batch.setOrderNumber(r.getString("order_number"));
        batch.setSauceName(r.getString("sauce_name"));
        return batch;
    };

    public static final RowMapper<Order> ORDER_ROW_MAPPER = (r, i) -> {
        Order order = new Order();
        order.setNumber(r.getString("order_number"));
        order.setRegistrationDate(r.getDate("registration_date"));
        order.setExpectedDate(r.getDate("expected_date"));
        order.setRealDate(r.getDate("real_date"));
        order.setDeliveryCost(r.getBigDecimal("delivery_cost"));
        order.setTotalCost(r.getBigDecimal("total_order_cost"));
        order.setCustomerNumber(r.getString("customer_number"));
        return order;
    };

    public static final RowMapper<Type> TYPE_ROW_MAPPER = (r, i) -> {
        Type type = new Type();
        type.setTypeNumber(r.getString("type_number"));
        type.setTypeName(r.getString("type_name"));
        return type;
    };

    public static final RowMapper<User> USER_ROW_MAPPER = (r, i) -> {
        User user = new User();
        user.setUsername(r.getString("username"));
        user.setPassword(r.getString("password"));
        user.setRole(r.getString("role"));
        return user;
    };

    public static final RowMapper<Customer> CUSTOMER_ROW_MAPPER = (r, i) -> {
        Customer customer = new Customer();
        customer.setNumber(r.getString("customer_number"));
        customer.setName(r.getString("customer_name"));
        customer.setSurname(r.getString("customer_surname"));
        customer.setPatronymic(r.getString("customer_patronymic"));
        customer.setAddress(r.getString("customer_address"));
        return customer;
    };

    public static final RowMapper<Phone> PHONE_ROW_MAPPER = (r, i) -> {
        Phone phone = new Phone();
        phone.setPhoneNumber(r.getString("contact_number"));
        phone.setCustomerNumber(r.getString("customer_number"));
        return phone;
    };

    public static final ResultSetExtractor<List<SauceWithRecipe>> SAUCE_WITH_RECIPE_EXTRACTOR = rs -> {
        Map<String, SauceWithRecipe> sauceMap = new LinkedHashMap<>();
        while (rs.next()) {
            String sauceNumber = rs.getString("sauce_number");
            SauceWithRecipe sauce = sauceMap.get(sauceNumber);
            if (sauce == null) {
                sauce = new SauceWithRecipe();
                sauce.setNumber(sauceNumber);
                sauce.setName(rs.getString("sauce_name"));
                sauce.setTypeNumber(rs.getString("type_number"));
                sauce.setShelfLife(rs.getInt("shelf_life"));
                sauce.setWeight(rs.getDouble("sauce_weight"));
                sauce.setCost(rs.getBigDecimal("sauce_cost"));
                sauce.setTypeName(rs.getString("type_name"));
                sauce.setRecipe(new ArrayList<>());

                sauceMap.put(sauceNumber, sauce);
            }
            String ingredientName = rs.getString("ingredient_name");
            if (ingredientName != null) {
                SauceIngredient ingredient = new SauceIngredient();
                ingredient.setName(ingredientName);
                ingredient.setGti(rs.getString("gti_number"));
                ingredient.setWeight(rs.getInt("ing_weight"));
                sauce.getRecipe().add(ingredient);
            }
        }
        return new ArrayList<>(sauceMap.values());
    };

    public static final ResultSetExtractor<List<CustomerWithOrders>> CUSTOMER_WITH_ORDERS_EXTRACTOR = rs -> {
        Map<String, CustomerWithOrders> ordersMap = new LinkedHashMap<>();
        while (rs.next()) {
            String sauceNumber = rs.getString("customer_number");
            CustomerWithOrders customer = ordersMap.get(sauceNumber);
            if (customer == null) {
                customer = new CustomerWithOrders();
                customer.setNumber(sauceNumber);
                customer.setName(rs.getString("customer_name"));
                customer.setSurname(rs.getString("customer_surname"));
                customer.setPatronymic(rs.getString("customer_patronymic"));
                customer.setAddress(rs.getString("customer_address"));
                customer.setOrders(new ArrayList<>());
                customer.setPhones(new ArrayList<>());
                ordersMap.put(sauceNumber, customer);
            }
            String orderNumber = rs.getString("order_number");
            if (orderNumber != null) {
                Order order = new Order();
                order.setNumber(orderNumber);
                order.setRegistrationDate(rs.getDate("registration_date"));
                order.setCustomerNumber(rs.getString("customer_number"));
                order.setDeliveryCost(rs.getBigDecimal("delivery_cost"));
                order.setTotalCost(rs.getBigDecimal("total_order_cost"));
                order.setExpectedDate(rs.getDate("expected_date"));
                order.setRealDate(rs.getDate("real_date"));
                if (!customer.getOrders().contains(order))
                    customer.getOrders().add(order);
            }
            String phoneNumber = rs.getString("contact_number");
            if (phoneNumber != null) {
                customer.getPhones().add(phoneNumber);
            }
        }
        return new ArrayList<>(ordersMap.values());
    };

    public static final ResultSetExtractor<List<Customer>> CUSTOMER_WITH_PHONES_EXTRACTOR = rs -> {
        Map<String, Customer> customerMap = new LinkedHashMap<>();
        while (rs.next()) {
            String customerNumber = rs.getString("customer_number");
            Customer customer = customerMap.get(customerNumber);
            if (customer == null) {
                customer = new Customer();
                customer.setNumber(customerNumber);
                customer.setName(rs.getString("customer_name"));
                customer.setSurname(rs.getString("customer_surname"));
                customer.setPatronymic(rs.getString("customer_patronymic"));
                customer.setAddress(rs.getString("customer_address"));
                customer.setPhones(new ArrayList<>());
                customerMap.put(customerNumber, customer);
            }
            String phoneNumber = rs.getString("contact_number");
            if (phoneNumber != null) {
                customer.getPhones().add(phoneNumber);
            }
        }
        return new ArrayList<>(customerMap.values());
    };

    public static final ResultSetExtractor<List<OrderWithCustomerData>> ORDER_WITH_CUSTOMER_DATA_EXTRACTOR = rs -> {
        Map<String, OrderWithCustomerData> orderMap = new LinkedHashMap<>();
        while (rs.next()) {
            String orderNumber = rs.getString("order_number");
            OrderWithCustomerData order = orderMap.get(orderNumber);
            if (order == null) {
                order = new OrderWithCustomerData();
                order.setNumber(orderNumber);
                order.setRegistrationDate(rs.getDate("registration_date"));
                order.setExpectedDate(rs.getDate("expected_date"));
                order.setRealDate(rs.getDate("real_date"));
                order.setDeliveryCost(rs.getBigDecimal("delivery_cost"));
                order.setTotalCost(rs.getBigDecimal("total_order_cost"));
                order.setCustomerNumber(rs.getString("customer_number"));
                order.setCustomerName(rs.getString("customer_name"));
                order.setCustomerSurname(rs.getString("customer_surname"));
                order.setCustomerPatronymic(rs.getString("customer_patronymic"));
                order.setCustomerNumber((rs.getString("customer_number")));
                order.setCustomerPhoneNumbers(new ArrayList<>());
                orderMap.put(orderNumber, order);
            }
            String phoneNumber = rs.getString("contact_number");
            if (phoneNumber != null) {
                order.getCustomerPhoneNumbers().add(phoneNumber);
            }
        }
        return new ArrayList<>(orderMap.values());
    };

    public static final RowMapper<TotalIncome> TOTAL_INCOME_ROW_MAPPER = (r, i) -> {
        TotalIncome totalIncome = new TotalIncome();
        totalIncome.setIncome(r.getBigDecimal("total_income"));
        return totalIncome;
    };

    public static final RowMapper<TotalAmount> TOTAL_AMOUNT_ROW_MAPPER = (r, i) -> {
        TotalAmount totalAmount = new TotalAmount();
        totalAmount.setTotalAmount(r.getInt("total_amount"));
        return totalAmount;
    };

    public static final RowMapper<CustomerOrderData> CUSTOMER_ORDER_DATA_ROW_MAPPER = (rs, i) -> {
        CustomerOrderData customerOrderData = new CustomerOrderData();
        customerOrderData.setCustomerNumber(rs.getString("customer_number"));
        customerOrderData.setCustomerSurname(rs.getString("customer_surname"));
        customerOrderData.setTotalOrdersCount(rs.getInt("total_orders_count"));
        customerOrderData.setTotalOrdersPrice(rs.getBigDecimal("total_orders_price"));
        return customerOrderData;
    };

}
