package com.endava.grocy.entity.product;

import com.endava.grocy.TestBaseClass;
import com.endava.grocy.model.EntityType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

public class DeleteProductTest extends TestBaseClass {

    @Test
    public void shouldDeleteProduct() {
        //GIVEN
        grocyFixture.createEntity(EntityType.LOCATIONS)
                .createEntity(EntityType.QUANTITY_UNIT)
                .createEntity(EntityType.PRODUCTS);
        Integer id = grocyFixture.getProduct().getId();

        //WHEN
        Response response = entityClient.deleteEntityById(EntityType.PRODUCTS, id);

        //THEN
        response.then().statusCode(HttpStatus.SC_NO_CONTENT);
    }
}
