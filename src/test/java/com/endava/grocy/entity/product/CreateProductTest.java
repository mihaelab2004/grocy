package com.endava.grocy.entity.product;

import com.endava.grocy.TestBaseClass;
import com.endava.grocy.model.EntityType;
import com.endava.grocy.model.Product;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.notNullValue;

public class CreateProductTest extends TestBaseClass {

    @Test
    public void shouldCreateProduct() {
        //GIVEN
        grocyFixture.createEntity(EntityType.LOCATIONS)
                .createEntity(EntityType.QUANTITY_UNIT);

        Integer locationId = grocyFixture.getLocation().getId();
        Integer quantityId = grocyFixture.getQuantityUnit().getId();

        Product product = dataProvider.getProduct(locationId, quantityId, quantityId);

        //WHEN
        Response response = entityClient.createEntity(EntityType.PRODUCTS, product);

        //THEN
        response.then().statusCode(HttpStatus.SC_OK)
                .body("created_object_id", notNullValue());

    }
}
