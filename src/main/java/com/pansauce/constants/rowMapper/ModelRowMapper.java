package com.pansauce.constants.rowMapper;

import com.pansauce.model.*;
import org.springframework.jdbc.core.RowMapper;

public class ModelRowMapper {

    public static final RowMapper<Sauce> SAUCE_ROW_MAPPER = (r, i) -> {
        Sauce rowObject = new Sauce();
        rowObject.setNumber(r.getString("sauce_number"));
        rowObject.setName(r.getString("sauce_name"));
        rowObject.setTypeNumber(r.getString("type_number"));
        rowObject.setShelfLife(r.getInt("shelf_life"));
        rowObject.setWeight(r.getDouble("sauce_weight"));
        rowObject.setCost(r.getBigDecimal("sauce_cost"));
        return rowObject;
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
        return order;
    };

    public static final RowMapper<Type> TYPE_ROW_MAPPER = (r, i) -> {
        Type type = new Type();
        type.setTypeNumber(r.getString("type_number"));
        type.setTypeName(r.getString("type_name"));
        return type;
    };

}
