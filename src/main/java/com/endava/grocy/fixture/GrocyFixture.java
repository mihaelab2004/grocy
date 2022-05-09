package com.endava.grocy.fixture;

import com.endava.grocy.client.EntityClient;
import com.endava.grocy.data.DataProvider;
import com.endava.grocy.model.Entity;
import com.endava.grocy.model.EntityType;
import com.endava.grocy.model.Location;
import com.endava.grocy.model.Product;
import com.endava.grocy.model.QuantityUnit;
import io.restassured.response.Response;
import lombok.Getter;
import lombok.Setter;
import org.apache.http.HttpStatus;

public class GrocyFixture {

    private EntityClient entityClient = new EntityClient();
    private DataProvider dataProvider = new DataProvider();

    @Getter
    private Location location;
    @Getter
    private QuantityUnit quantityUnit;
    @Getter
    private Product product;

    public GrocyFixture createEntity(EntityType entityType) {

        Entity entity;
        switch (entityType) {
            case LOCATIONS: {
                location = dataProvider.getLocation();
                entity = location;
                break;
            }
            case QUANTITY_UNIT: {
                quantityUnit = dataProvider.getQuantityUnit();
                entity = quantityUnit;
                break;
            }
            case PRODUCTS: {
                if (location == null) {
                    throw new RuntimeException("Please create first a location!");
                }
                product = dataProvider.getProduct(location.getId(), quantityUnit.getId(), quantityUnit.getId());
                entity = product;
                break;
            }
            default: {
                throw new IllegalArgumentException("Don't know how to create " + entityType);
            }
        }
        Response response = entityClient.createEntity(entityType, entity);
        response.then().statusCode(HttpStatus.SC_OK);
        int id = response.body().jsonPath().getInt("created_object_id");
        entity.setId(id);

        return this;
    }
}
