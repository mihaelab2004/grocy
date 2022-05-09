package com.endava.grocy.entity.product;

import com.endava.grocy.TestBaseClass;
import com.endava.grocy.client.EntityClient;
import com.endava.grocy.model.EntityType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

public class GetProductByIdTest extends TestBaseClass {

    @Test
    public void shouldGetAnEntityById() {
        //GIVEN
        grocyFixture.createEntity(EntityType.LOCATIONS)
                .createEntity(EntityType.QUANTITY_UNIT)
                .createEntity(EntityType.PRODUCTS);
        Integer id = grocyFixture.getProduct().getId();

        //WHEN
        Response response = entityClient.getEntityById(EntityType.PRODUCTS, id);

        //THEN
        response.then().statusCode(HttpStatus.SC_OK);

    }

}
