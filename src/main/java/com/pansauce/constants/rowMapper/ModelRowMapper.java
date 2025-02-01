package com.pansauce.constants.rowMapper;

import com.pansauce.model.Ingredient;
import com.pansauce.model.Sauce;
import org.springframework.jdbc.core.RowMapper;

public class ModelRowMapper {

    public static final RowMapper<Sauce> SAUCE_ROW_MAPPER = (r, i) -> {
        Sauce rowObject = new Sauce();
        rowObject.setNumber(r.getString("sauce_number"));
        rowObject.setName(r.getString("sauce_name"));
        rowObject.setType(r.getString("sauce_type"));
        rowObject.setShelfLife(r.getInt("shelf_life"));
        rowObject.setWeight(r.getDouble("sauce_weight"));
        rowObject.setCost(r.getBigDecimal("sauce_cost"));
        return rowObject;
    };

    public static final RowMapper<Ingredient> INGREDIENT_ROW_MAPPER = (r, i) -> {
        Ingredient rowObject = new Ingredient();
        rowObject.setGti(r.getString("gti_number"));
        rowObject.setName(r.getString("ingredient_name"));
        return rowObject;
    };

}
