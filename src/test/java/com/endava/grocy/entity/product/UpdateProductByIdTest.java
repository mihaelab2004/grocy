package com.endava.grocy.entity.product;

import com.endava.grocy.TestBaseClass;
import com.endava.grocy.model.EntityType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

public class UpdateProductByIdTest extends TestBaseClass {

    @Test
    public void shouldUpdateAProduct() {
        //GIVEN
        grocyFixture.createEntity(EntityType.LOCATIONS)
                .createEntity(EntityType.QUANTITY_UNIT)
                .createEntity(EntityType.PRODUCTS);

        Integer productId = grocyFixture.getProduct().getId();
        grocyFixture.getProduct().setName("ModifiedProductName");


        //WHEN
        Response response = entityClient.updateEntityById(EntityType.PRODUCTS, grocyFixture.getProduct(), productId);

        //THEN
        response.then().statusCode(HttpStatus.SC_NO_CONTENT);
    }
}
