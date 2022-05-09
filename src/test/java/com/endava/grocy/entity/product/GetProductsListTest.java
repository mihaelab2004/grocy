package com.endava.grocy.entity.product;

import com.endava.grocy.TestBaseClass;
import com.endava.grocy.model.EntityType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.withArgs;
import static org.hamcrest.Matchers.is;

public class GetProductsListTest extends TestBaseClass {

    @Test
    public void shouldGetAllTheProductsInAnEntity() {
        //GIVEN
        grocyFixture.createEntity(EntityType.LOCATIONS)
                .createEntity(EntityType.QUANTITY_UNIT)
                .createEntity(EntityType.PRODUCTS);
        Integer id = grocyFixture.getProduct().getId();
        String name = grocyFixture.getProduct().getName();

        //WHEN
        Response response = entityClient.getTheListOfObjectsInEntity(EntityType.PRODUCTS);

        //THEN
        response.then().statusCode(HttpStatus.SC_OK)
                .body("find{it -> it.id == '%s'}.name", withArgs(grocyFixture.getProduct().getId()), is(name));

    }
}
